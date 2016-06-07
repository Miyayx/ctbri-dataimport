package cn.com.ctbri.ctbigdata.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.util.StringUtils;

import cn.com.ctbri.ctbigdata.autohome.model.SimpleAuto;
import cn.com.ctbri.ctbigdata.dao.SimpleAutoDAO;

public class JdbcSimpleAutoDAO extends JdbcDaoSupport implements SimpleAutoDAO {
	// insert example
	public void insert(SimpleAuto auto) {

		String sql = "INSERT INFO AUTO" + "(page_string, url, price, score)" + "VALUES" + "(?, ?, ?, ?)";

		getJdbcTemplate().update(sql,
				new Object[] { auto.getPageId(), auto.getUrl(), auto.getPrice(), auto.getScore() });

	}
	
	private String generateInsertSql(JSONObject JO,  String sqlTabelName, int auto_id){

			List<String> keys = new ArrayList<String>();
			keys.addAll(JO.keySet());

			List<String> fields = new ArrayList<String>();
			fields.add("auto_id");
			for(String k: keys){
				fields.add(k.split("(")[0]);
			}
			
			List<Object> properties = new ArrayList<Object>();
			properties.add(auto_id);
			for(String k:keys){
				properties.add(JO.get(k));
			}

			String insertSql = "INSERT INTO"+ sqlTabelName +" ("+StringUtils.collectionToDelimitedString(fields, ",")+" )"+
					"VALUES("+ StringUtils.collectionToDelimitedString(properties, ",") +")";
			
			return insertSql;
		
	}
	
	public void insertConfig(List<JSONObject> jos) {

		List<String> sqls = new ArrayList<String>();

		for (JSONObject jo : jos) {
			Long pageString = (Long) jo.get("page_string");
			int id = findIdByPage(pageString);
			
			// 基本参数
			JSONObject basicJO = (JSONObject) jo.get("基本参数");

			Object[] properties = new Object[]{id, basicJO.get("厂商"), basicJO.get("级别"), basicJO.get("发动机"),
					basicJO.get("变速箱"), basicJO.get("长*宽*高(mm)"), basicJO.get("车身结构"), 
					basicJO.get("最高车速(km/h)"), basicJO.get("实测油耗(L/100km)"), 
					basicJO.get("工信部综合油耗(L/100km)"), basicJO.get("实测离地间隙(mm)"), basicJO.get("整车质保")};

			String insertSql = "INSERT INTO AUTO_BASIC (auto_id, 厂商, 级别, 发动机, 变速箱, 长宽高, 车身结构, 最高车速, 实测油耗, 工信部综合油耗, 实测离地间隙, 整车质保)"+
					"VALUES("+ StringUtils.arrayToDelimitedString(properties, ",") +")";

			sqls.add(insertSql);

			JSONObject JO = (JSONObject) jo.get("车身");
			String sql = generateInsertSql(JO, "CarBody", id);
			sqls.add(sql);

			JO = (JSONObject) jo.get("发动机");
			sql = generateInsertSql(JO, "Engine", id);
			sqls.add(sql);

			JO = (JSONObject) jo.get("变速箱");
			sql = generateInsertSql(JO, "Gearbox", id);
			sqls.add(sql);

			JO = (JSONObject) jo.get("底盘转向");
			sql = generateInsertSql(JO, "chassis", id);
			sqls.add(sql);

			JO = (JSONObject) jo.get("车轮制动");
			sql = generateInsertSql(JO, "WheelBrake", id);
			sqls.add(sql);

			JO = (JSONObject) jo.get("安全装备");
			sql = generateInsertSql(JO, "SafetyEquipment", id);
			sqls.add(sql);

			JO = (JSONObject) jo.get("操控配置");
			sql = generateInsertSql(JO, "ControlConfig", id);
			sqls.add(sql);

			JO = (JSONObject) jo.get("外部配置");
			sql = generateInsertSql(JO, "ExternalConfig", id);
			sqls.add(sql);

			JO = (JSONObject) jo.get("内部配置");
			sql = generateInsertSql(JO, "InternalConfig", id);
			sqls.add(sql);

			JO = (JSONObject) jo.get("座椅配置");
			sql = generateInsertSql(JO, "SeatConfig", id);
			sqls.add(sql);

			JO = (JSONObject) jo.get("多媒体配置");
			sql = generateInsertSql(JO, "MediaConfig", id);
			sqls.add(sql);

			JO = (JSONObject) jo.get("灯光配置");
			sql = generateInsertSql(JO, "LightConfig", id);
			sqls.add(sql);

			JO = (JSONObject) jo.get("玻璃/后视镜");
			sql = generateInsertSql(JO, "Glass", id);
			sqls.add(sql);

			JO = (JSONObject) jo.get("空调/冰箱");
			sql = generateInsertSql(JO, "Aircon", id);
			sqls.add(sql);

			JO = (JSONObject) jo.get("高科技配置");
			sql = generateInsertSql(JO, "HighTechConfig", id);
			sqls.add(sql);
		}
		
		String[] sqlArr = new String[sqls.size()];
		getJdbcTemplate().batchUpdate(sqls.toArray(sqlArr));

	}

	// insert batch example
	public void insertBatch(final List<SimpleAuto> autos) {

		String sql = "INSERT INFO AUTO" + "(page_string, url, price, score)" + "VALUES" + "(?, ?, ?, ?)";

		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

			public void setValues(PreparedStatement ps, int i) throws SQLException {
				SimpleAuto auto = autos.get(i);
				ps.setLong(1, auto.getPageId());
				ps.setString(2, auto.getUrl());
				ps.setDouble(3, auto.getPrice());
				ps.setDouble(4, auto.getScore());
			}

			public int getBatchSize() {
				return autos.size();
			}
		});
	}

	// insert batch example with SQL
	public void insertBatchSQL(final String sql) {

		getJdbcTemplate().batchUpdate(new String[] { sql });

	}

	// insert batch example with SQL
	public void insertBatchSQL(final List<String> sqls) {

		getJdbcTemplate().batchUpdate(sqls.toArray(new String[sqls.size()]));

	}

	public String findAutoById(int id) {

		String sql = "SELECT NAME FROM CUSTOMER WHERE CUST_ID = ?";
		String name = (String) getJdbcTemplate().queryForObject(sql, new Object[] { id }, String.class);
		return name;

	}

	public int findIdByPage(long pageString) {

		String sql = "SELECT id FROM AUTO WHERE page_string = ?";
		int id = (int) getJdbcTemplate().queryForObject(sql, new Object[] { pageString }, Integer.class);
		return id;

	}

	public int findTotalAuto() {

		String sql = "SELECT COUNT(*) FROM AUTO";

		int total = getJdbcTemplate().queryForInt(sql);

		return total;
	}


}
