package com.userbo;

import com.util.InetLogger;

import java.util.HashMap;
import java.util.Map;

import com.mail.Mail;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;
import com.util.IContext;
import com.util.SystemProperties;

public class MaillingObj {
	private static final InetLogger logger = InetLogger.getInetLogger(MaillingObj.class);

	public void mailingToInsured(IContext ctx) throws Exception
	{
		String mailingOnOFF = null;
		try {
			mailingOnOFF = SystemProperties.getInstance().getString("Insured.sendmail");
		} catch (Exception e) {
			
		}
		
		if(mailingOnOFF==null || !"N".equals(mailingOnOFF))
			return;
		
		Map	map = getAccountInfo(ctx);
		if("4".equals(ctx.get("StatusKey").toString()))
		{
			if(map!=null && !map.isEmpty())
			{
				mailingContent(ctx,map,"Your Quote has been Declined");
			}
		}
		
		if("5".equals(ctx.get("StatusKey").toString()))
		{
			if(map!=null && !map.isEmpty())
			{
				mailingContent(ctx,map,"Your Policy has been Issued");
			}
		}
		
		if("3".equals(ctx.get("StatusKey").toString()))
		{
			if(map!=null && !map.isEmpty())
			{
				mailingContent(ctx,map,"Your QuoteNumber is Quoted");
			}
		}
	}
	
	private void mailingContent(IContext ctx, Map map,String sub) throws Exception
	{
		Mail mail = new Mail();
		mail.setToAdd(map.get("AccountEmail").toString());
		mail.setCcAdd("madhu@outlinesys.com"); 
		mail.setSubject(sub);
		mail.setBody("Quote Number :"+map.get("QuoteNumber")+" \n Account Name: "+map.get("AccountName")+"\n" );
		mail.sendMail((Context) ctx);
	}
	
	
	public Map getAccountInfo(IContext ctx) throws Exception{
		Map maxAccountMap=null;
		try {
			maxAccountMap = (HashMap) SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.BasicInfoviewgetAccountAndAddressdetails", ctx);
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
		return maxAccountMap;
	}
}
