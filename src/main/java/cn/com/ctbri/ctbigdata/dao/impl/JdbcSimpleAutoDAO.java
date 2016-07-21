package cn.com.ctbri.ctbigdata.dao.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.util.StringUtils;

import com.mysql.jdbc.CharsetMapping;

import cn.com.ctbri.ctbigdata.autohome.model.SimpleAuto;
import cn.com.ctbri.ctbigdata.dao.SimpleAutoDAO;

public class JdbcSimpleAutoDAO extends JdbcDaoSupport implements SimpleAutoDAO {

	private boolean can_run = false;

	// insert example
	public void insert(SimpleAuto auto) {

		String sql = "INSERT INTO AUTO" + "(page_string, url, price, score)" + "VALUES" + "(?, ?, ?, ?)";

		getJdbcTemplate().update(sql,
				new Object[] { auto.getPageId(), auto.getUrl(), auto.getPrice(), auto.getScore() });

	}

	private String generateInsertSql(JSONObject JO, String sqlTabelName, int auto_id)
			throws NumberFormatException, UnsupportedEncodingException {

		if (JO == null) {
			return null;
		}

		Map<String, String> cType = columnType(sqlTabelName);

		List<String> keys = new ArrayList<String>();
		List<String> fields = new ArrayList<String>();

		for (Object jo : JO.keySet()) {
			String k = (String) jo;
			String field = k.split("\\(")[0].trim().replace(" ", "");

			// System.out.println(field);
			if (cType.containsKey(field)) {
				keys.add(k);
				if (field.contains("/")) {

					cType.put("`" + field + "`", cType.get(field));
					field = "`" + field + "`";
				}
				fields.add(field);
			}
		}

		List<Object> properties = new ArrayList<Object>();

		for (int i = 0; i < keys.size(); i++) {
			String k = keys.get(i);
			String field = fields.get(i);

			Object o = JO.get(k);
			if (cType.containsKey(field)) {
				//System.out.println(field);
				if (cType.get(field).contains("int")) {
					try {
						properties.add(Integer.parseInt((String) o));
					} catch (Exception e) {
						if(((String)o).contains("○")){
							properties.add(0);
						}else{
						System.out.println(e.getMessage());
						properties.add(-1);
						}
					}
				} else if (cType.get(field).contains("double")) {
					try {
						properties.add(Double.parseDouble((String) o));
					} catch (Exception e) {
						System.out.println(e.getMessage());
						properties.add(-1.0);
					}
				} else if (cType.get(field).contains("float")) {
					try {
						properties.add(Float.parseFloat((String) o));
					} catch (Exception e) {
						System.out.println(e.getMessage());
						properties.add(-1.0);
					}
				} else if (cType.get(field).contains("varchar")) {
					properties.add("'" + (String) o + "'");
				} else {
					properties.add(o);
				}
			} else {
				properties.add(o);
			}
		}

		fields.add(0, "auto_id");
		properties.add(0, auto_id);

		String insertSql = "INSERT IGNORE INTO " + sqlTabelName + " ("
				+ StringUtils.collectionToDelimitedString(fields, ",") + " )" + "VALUES("
				+ StringUtils.collectionToDelimitedString(properties, ",") + ")";

		return insertSql;

	}

	public Map<String, String> columnType(String table) {
		final Map<String, String> cType = new HashMap<String, String>();
		// getJdbcTemplate().query("select * from "+table,new
		// ResultSetExtractor() {
		getJdbcTemplate().query("desc " + table, new ResultSetExtractor() {

			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					cType.put(rs.getString(1), rs.getString(2));
				}
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();

				return columnCount;
			}
		});
		return cType;
	}

	public void insertConfig(List<JSONObject> jos) throws NumberFormatException, UnsupportedEncodingException {

		List<String> sqls = new ArrayList<String>();

		// Statement stmt = new Statement();
		// ResultSet rs = stmt.executeQuery("select * from emp");
		// ResultSetMetaData rsmd = rs.getMetaData();

		String lastPageid = "";
		//if (!this.can_run) {
		//	String lastSql = "SELECT auto_id FROM auto_carbody WHERE id=(SELECT max(id) FROM auto_carbody)";
		//	int lastAutoid = getJdbcTemplate().queryForObject(lastSql, Integer.class);

		//	String pageidSql = "SELECT Page_id FROM auto WHERE ID = ?";
		//	lastPageid = (String) getJdbcTemplate().queryForObject(pageidSql, new Object[] { lastAutoid },
		//			String.class);
		//}
		for (JSONObject jo : jos) {
			try{
			String pageString = (String) jo.get("page_string");

			if (lastPageid.equals(pageString)) {
				this.can_run = true;
			}
			//if (!this.can_run) {
			//	continue;
			//}
			int id = findIdByPage(pageString);

			// 基本参数
			JSONObject basicJO = (JSONObject) jo.get("基本参数");

			Object[] properties = new Object[] { id, "'" + (String) basicJO.get("厂商") + "'",
					"'" + (String) basicJO.get("级别") + "'", "'" + (String) basicJO.get("发动机") + "'",
					"'" + (String) basicJO.get("变速箱") + "'", "'" + (String) basicJO.get("长*宽*高(mm)") + "'",
					"'" + (String) basicJO.get("车身结构") + "'", basicJO.get("最高车速(km/h)"), basicJO.get("实测油耗(L/100km)"),
					basicJO.get("工信部综合油耗(L/100km)"), basicJO.get("实测离地间隙(mm)"),
					"'" + (String) basicJO.get("整车质保") + "'" };

			String insertSql = "INSERT IGNORE INTO AUTO_BASIC (auto_id, 厂商, 级别, 发动机, 变速箱, 长宽高, 车身结构, 最高车速, 实测油耗, 工信部综合油耗, 实测离地间隙, 整车质保)"
					+ " VALUES (" + StringUtils.arrayToDelimitedString(properties, ",") + ")";

			sqls.add(insertSql);

			JSONObject JO = null;
			String sql = null;

			JO = (JSONObject) jo.get("车身");
			sql = generateInsertSql(JO, "Auto_CarBody", id);
			if (!(sql == null)) {
				sqls.add(sql);
			}

			JO = (JSONObject) jo.get("发动机");
			sql = generateInsertSql(JO, "Auto_Engine", id);
			if (!(sql == null)) {
				sqls.add(sql);
			}

			JO = (JSONObject) jo.get("变速箱");
			sql = generateInsertSql(JO, "Auto_Gearbox", id);
			if (!(sql == null)) {
				sqls.add(sql);
			}

			JO = (JSONObject) jo.get("底盘转向");
			sql = generateInsertSql(JO, "Auto_chassis", id);
			if (!(sql == null)) {
				sqls.add(sql);
			}

			JO = (JSONObject) jo.get("车轮制动");
			sql = generateInsertSql(JO, "Auto_WheelBrake", id);
			if (!(sql == null)) {
				sqls.add(sql);
			}

			JO = (JSONObject) jo.get("安全装备");
			sql = generateInsertSql(JO, "Auto_SafetyEquipment", id);
			if (!(sql == null)) {
				sqls.add(sql);
			}

			JO = (JSONObject) jo.get("操控配置");
			sql = generateInsertSql(JO, "Auto_ControlConfig", id);
			if (!(sql == null)) {
				sqls.add(sql);
			}

			JO = (JSONObject) jo.get("外部配置");
			sql = generateInsertSql(JO, "Auto_ExternalConfig", id);
			if (!(sql == null)) {
				sqls.add(sql);
			}

			JO = (JSONObject) jo.get("内部配置");
			sql = generateInsertSql(JO, "Auto_InternalConfig", id);
			if (!(sql == null)) {
				sqls.add(sql);
			}

			JO = (JSONObject) jo.get("座椅配置");
			sql = generateInsertSql(JO, "Auto_SeatConfig", id);
			if (!(sql == null)) {
				sqls.add(sql);
			}

			JO = (JSONObject) jo.get("多媒体配置");
			sql = generateInsertSql(JO, "Auto_MediaConfig", id);
			if (!(sql == null)) {
				sqls.add(sql);
			}

			JO = (JSONObject) jo.get("灯光配置");
			sql = generateInsertSql(JO, "Auto_LightConfig", id);
			if (!(sql == null)) {
				sqls.add(sql);
			}

			JO = (JSONObject) jo.get("玻璃/后视镜");
			sql = generateInsertSql(JO, "Auto_GlassConfig", id);
			if (!(sql == null)) {
				sqls.add(sql);
			}

			JO = (JSONObject) jo.get("空调/冰箱");
			sql = generateInsertSql(JO, "Auto_Aircon", id);
			if (!(sql == null)) {
				sqls.add(sql);
			}

			JO = (JSONObject) jo.get("高科技配置");
			sql = generateInsertSql(JO, "Auto_HighTechConfig", id);
			if (!(sql == null)) {
				sqls.add(sql);
			}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}

			// break;

		}

		if (sqls.size() == 0) {
			return;
		}
		String[] sqlArr = new String[sqls.size()];
		try {
			System.out.println("Insert ...");
			getJdbcTemplate().batchUpdate(sqls.toArray(sqlArr));
		} catch (Exception e) {
			System.out.println("Insert"+e.getMessage());
		}

	}

	// insert batch example
	public void insertBatch(final List<SimpleAuto> autos) {

		String sql = "INSERT IGNORE INTO AUTO" + " (page_id, url, price, score) " + "VALUES" + " (?, ?, ?, ?);";

		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				SimpleAuto auto = autos.get(i);
				ps.setString(1, auto.getPageId());
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

	public int findIdByPage(String pageString) {

		String sql = "SELECT id FROM AUTO WHERE page_id = ?";

		int id = (int) getJdbcTemplate().queryForObject(sql, new Object[] { pageString }, Integer.class);
		return id;

	}

	public int findTotalAuto() {

		String sql = "SELECT COUNT(*) FROM AUTO";

		int total = getJdbcTemplate().queryForInt(sql);

		return total;
	}

}
