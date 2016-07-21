package cn.com.ctbri.ctbigdata.dataimport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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

	public static void insertAutoSpecific(SimpleAutoDAO simpleAutoDAO, String filename)
			throws IOException, ParseException {

		File file = new File(filename);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(new FileInputStream(file.toPath().toString()), "UTF8"));

		String s;
		List<SimpleAuto> autos = new ArrayList<SimpleAuto>();
		JSONParser parser = new JSONParser();

		int count = 0;
		while ((s = in.readLine()) != null) {
			count++;
			JSONObject jo = (JSONObject) parser.parse(s);

			if (((Long) jo.get("type")) == 1) {
				SimpleAuto auto = new SimpleAuto((String) jo.get("page_string"), (String) jo.get("title"),
						(String) jo.get("url"), (Double) jo.get("全款购车"), (Double) jo.get("用户评分"));
				autos.add(auto);
				if (autos.size() == 30) {
					simpleAutoDAO.insertBatch(autos);
					autos = new ArrayList<SimpleAuto>();
				}
			}
		}
		simpleAutoDAO.insertBatch(autos);
		in.close();

	}

	public static void insertAutoConfig(SimpleAutoDAO simpleAutoDAO, String filename)
			throws IOException, ParseException, Exception {

		File file = new File(filename);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(new FileInputStream(file.toPath().toString()), "UTF8"));

		List<JSONObject> jos = new ArrayList<JSONObject>();

		String s;
		JSONParser parser = new JSONParser();

		int c = 0;
		while ((s = in.readLine()) != null) {
			c++;
			JSONObject jo = (JSONObject) parser.parse(s);
			if ((Long) jo.get("type") == 2) {
				jos.add(jo);
				if (jos.size() == 20) {
					simpleAutoDAO.insertConfig(jos);
					jos = new ArrayList<JSONObject>();
				}
			}
		}
		try {
			simpleAutoDAO.insertConfig(jos);
		} catch (Exception e) {
			System.out.println(c + e.getMessage());
		}
		in.close();

	}

	public static void main(String[] args) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

		SimpleAutoDAO simpleAutoDAO = (SimpleAutoDAO) context.getBean("simpleAutoDAO");

		insertAutoSpecific(simpleAutoDAO, "xaa");
		insertAutoConfig(simpleAutoDAO, "xaa");

		System.out.println("Batch Insert Done!");

	}
}
