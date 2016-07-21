package cn.com.ctbri.ctbigdata.dao;

import java.util.List;

import org.json.simple.JSONObject;

import cn.com.ctbri.ctbigdata.autohome.model.SimpleAuto;

public interface SimpleAutoDAO 
{
	public void insert(SimpleAuto auto);
	
	public void insertBatch(List<SimpleAuto> autos);
			
	public void insertBatchSQL(String sql);
	
	public void insertConfig(List<JSONObject> jos) throws Exception;
	
	public String findAutoById(int id);
	
	public int findTotalAuto();
	
}




