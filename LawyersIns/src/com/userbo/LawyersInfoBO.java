package com.userbo;

import com.util.InetLogger;

import java.util.HashMap;
import java.util.Map;

import com.ormapping.ibatis.SqlResources;
import com.util.IContext;

public class LawyersInfoBO {
	private static final InetLogger logger = InetLogger.getInetLogger(LawyersInfoBO.class);
	@SuppressWarnings("unchecked")
	public void populateAccountID(IContext ctx) throws Exception
	{   
		int accountID=0;
 		Map maxAccountIDMap = getMaxAccountID(ctx);
		if(!maxAccountIDMap.isEmpty())
			if(maxAccountIDMap.get(com.userbo.Constants.MAXACCOUNTID)==null)
				accountID=0;
			else
				accountID = Integer.parseInt(maxAccountIDMap.get(com.userbo.Constants.MAXACCOUNTID).toString() );
			
		accountID = accountID+1;
		ctx.put(com.userbo.Constants.ACCOUNT_ID, accountID);
		ctx.put(com.userbo.Constants.ACOCUNT_VERSION, "1");
		
//		if(ctx.get(com.userbo.Constants.ACCOUNTNAME)!=null && ctx.get(com.userbo.Constants.ACCOUNTNAME)!="")
//			ctx.put("isUncompletedData", "N");
	}
	
	public Map getMaxAccountID(IContext ctx) throws Exception{
		Map maxAccountMap=null;
		try {
			maxAccountMap = (HashMap) SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsgetMaxAccountID", ctx);
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
		return maxAccountMap;
	}
}
