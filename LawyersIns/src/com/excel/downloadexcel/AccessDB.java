package com.excel.downloadexcel;

import java.util.HashMap;
import java.util.Map;

import com.ormapping.ibatis.SqlResources;
import com.util.Context;
import com.util.IContext;

public class AccessDB {

	private IContext context = null;
	private Map dbValues = new HashMap();
	
	public AccessDB(String project) {
		context = new Context();
		context.setProject(project);
	}

	
	public Map populateDBValues() throws Exception {
		
		populatePolicyContextDetail();
		
		
		
		return dbValues;
	}
	
	public void populatePolicyContextDetail() throws Exception {
		Object obj = SqlResources.getSqlMapProcessor(context).findByKey("SqlStmts.BasicInfoviewgetAccountAndAddressdetails", context);
		if(obj != null && obj instanceof Map)
		{
			dbValues.putAll((Map)obj);
		}
	}
	
	public void populatePolicyContextDetail1() throws Exception {
		Object obj = SqlResources.getSqlMapProcessor(context).findByKey("SqlStmts.BasicInfoviewgetAccountAndAddressdetails", context);
		if(obj != null && obj instanceof Map)
		{
			dbValues.putAll((Map)obj);
		}
	}
	
}
