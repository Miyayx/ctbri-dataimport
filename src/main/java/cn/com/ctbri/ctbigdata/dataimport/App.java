package cn.com.ctbri.ctbigdata.dataimport;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws FileNotFoundException, IOException {

		Map<String, String> fieldMap = new HashMap<String, String>();

		fieldMap.put("company", "厂商");
		fieldMap.put("", "级别");
		fieldMap.put("", "发动机");
		fieldMap.put("", "变速箱");
		fieldMap.put("", "长*宽*高(mm)");
		fieldMap.put("", "车身结构");
		fieldMap.put("", "最高车速(km/h)");
		fieldMap.put("", "官方0-100km/h加速(s)");
		fieldMap.put("", "实测0-100km/h加速(s)");
		fieldMap.put("", "实测100-0km/h制动(m)");
		fieldMap.put("", "实测油耗(L/100km)");
		fieldMap.put("", "工信部综合油耗(L/100km)");
		fieldMap.put("", "实测离地间隙(mm)");
		fieldMap.put("", "整车质保");

		fieldMap.put("", "长度(mm)");
		fieldMap.put("", "宽度(mm)");
		fieldMap.put("", "高度(mm)");
		fieldMap.put("", "轴距(mm)");
		fieldMap.put("", "前轮距(mm)");
		fieldMap.put("", "后轮距(mm)");
		fieldMap.put("", "最小离地间隙(mm)");
		fieldMap.put("", "整备质量(kg)");
		fieldMap.put("", "车身结构");
		fieldMap.put("", "车门数(个)");
		fieldMap.put("", "座位数(个)");
		fieldMap.put("", "油箱容积(L)");
		fieldMap.put("", "行李厢容积(L)");

		fieldMap.put("", "发动机型号");
		fieldMap.put("", "排量(mL)");
		fieldMap.put("", "进气形式");
		fieldMap.put("", "气缸排列形式");
		fieldMap.put("", "气缸数(个)");
		fieldMap.put("", "每缸气门数(个)");
		fieldMap.put("", "压缩比");

		
		Properties properties = new Properties();

		for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
			properties.put(entry.getKey(), entry.getValue());
		}

		properties.store(new FileOutputStream("data.properties"), null);
	}
}
