package cn.com.ctbri.ctbigdata.dataimport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import cn.com.ctbri.ctbigdata.autohome.model.SimpleAuto;
import cn.com.ctbri.ctbigdata.dao.SimpleAutoDAO;

public class JdbcTemplateApp {
	public static void main(String[] args) throws IOException, ParseException {
		// if you have time,
		// it's better to create an unit test rather than testing like this :)

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

		SimpleAutoDAO simpleAutoDAO = (SimpleAutoDAO) context.getBean("simpleAutoDAO");

		File file = new File("/Users/Miyayx/Documents/workspace/pageparser-autohome/autohome_result.dat");

		BufferedReader in = new BufferedReader(
				new InputStreamReader(new FileInputStream(file.toPath().toString()), "UTF8"));

		String s;
		List<SimpleAuto> autos = new ArrayList<SimpleAuto>();
		JSONParser parser = new JSONParser();

		while ((s = in.readLine()) != null) {
			JSONObject jo = (JSONObject) parser.parse(s);
			if (jo.get("type").equals(1)) {
				SimpleAuto auto = new SimpleAuto((Long) jo.get("page_string"), (String) jo.get("title"),
						(String) jo.get("url"), (Double) jo.get("全款购车"), (Double) jo.get("用户评分"));
				autos.add(auto);
				if(autos.size() == 100){
					simpleAutoDAO.insertBatch(autos);
					autos = new ArrayList<SimpleAuto>();
				}
			}
		}
		in.close();

		in = new BufferedReader(new InputStreamReader(new FileInputStream(file.toPath().toString()), "UTF8"));

		List<JSONObject> jos = new ArrayList<JSONObject>();

		while ((s = in.readLine()) != null) {
			JSONObject jo = (JSONObject) parser.parse(s);
			if (jo.get("type").equals(2)) {
				jos.add(jo);
				if (jos.size() == 100) {
					simpleAutoDAO.insertConfig(jos);
					jos = new ArrayList<JSONObject>();
				}

			}

		}

		String sql = "INSERT INFO AUTO" + "(page_string, url, price, score)" + "VALUES" + "(?, ?, ?, ?)";
		simpleAutoDAO.insertBatchSQL(sql);

		System.out.println("Batch Insert Done!");

		int total = simpleAutoDAO.findTotalAuto();
		System.out.println("Total : " + total);

	}
}
