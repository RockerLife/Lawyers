package com.userbo;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.mail.MailSender;
import com.manage.managecomponent.components.SetParametersForStoredProcedures;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.IContext;
import com.util.InetLogger;
import com.util.MessageStringFormat;
import com.util.StringUtils;
import com.util.SubproducerMailer;
import com.util.SystemProperties;

public class EndorsementUtilities {
	private static InetLogger logger = InetLogger.getInetLogger(EndorsementUtilities.class);
	public static void calculateCancellationPremium(Context ctx) throws Exception
	{
		DecimalFormat  formatter = new DecimalFormat("#0.00");   
		List<Map<String, Object>> calculationDataList=new ArrayList<Map<String, Object>>();
		int cReason=ctx.get("cancellationReason")!=null && !ctx.get("cancellationReason").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("cancellationReason").toString()):0;
		int cType=ctx.get("cancellationType")!=null && !ctx.get("cancellationType").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("cancellationType").toString()):0;
		if(cReason==1 || cReason==2)
		{
			cType=2;
			ctx.put("cancellationType",2);
		}
		if(cType==1)
		{
			ctx.put("cancellationReturnPremium", ctx.get("TotalPremium"));
			ctx.put("cancellationReturnTax", ctx.get("TotalPolicyTaxAmount"));
			ctx.put("cancellationTotalReturnPremium", ctx.get("InvoicedPremium"));
			ctx.put("premimDifference", ctx.get("InvoicedPremium"));
			ctx.put("returnPremium","-"+ctx.get("TotalPremium"));
			ctx.put("returnTax","-"+ctx.get("TotalPolicyTaxAmount"));
		}
		if(cType==2)
		{
			ctx.put("EndorsementEffectiveDate",ctx.get("CancellationEffectiveDate"));
			Map<String,Object> premiumCalculationMap=calculateEndorsementPremium(ctx);
			premiumCalculationMap.put("EndorsementType",ctx.get("EndorsementType"));
			premiumCalculationMap.put("CancellationType",2);
  			calculationDataList.add(premiumCalculationMap);
  			ctx.put("calculationDataList", calculationDataList);
  			LawyersUtils.convertListDataToXML(ctx,"calculationDataList","endorsementCalculationList");
  			ctx.put("endorsementDataList", ctx.get("endorsementCalculationList"));
			
			ctx.put("cancellationReturnPremium", premiumCalculationMap.get("unearnedPremium"));
			ctx.put("cancellationReturnTax",  premiumCalculationMap.get("unearnedTax"));
			ctx.put("cancellationTotalReturnPremium", premiumCalculationMap.get("totalunearnedPremium"));
			ctx.put("totalOldPremium", premiumCalculationMap.get("totalOldPremium"));
			ctx.put("premimDifference", premiumCalculationMap.get("premimDifference"));
			ctx.put("returnPremium","-"+ premiumCalculationMap.get("unearnedPremium"));
			ctx.put("returnTax","-"+ premiumCalculationMap.get("unearnedTax"));
			
			
		}
		
		if(cType==3)
		{
			ctx.put("EndorsementEffectiveDate",ctx.get("CancellationEffectiveDate"));
			Map<String,Object> premiumCalculationMap=calculateEndorsementPremium(ctx);
			premiumCalculationMap.put("EndorsementType",ctx.get("EndorsementType"));
			premiumCalculationMap.put("CancellationType",2);
  			calculationDataList.add(premiumCalculationMap);
  			ctx.put("calculationDataList", calculationDataList);
  			LawyersUtils.convertListDataToXML(ctx,"calculationDataList","endorsementCalculationList");
  			ctx.put("endorsementDataList", ctx.get("endorsementCalculationList"));
			
			ctx.put("cancellationReturnPremium", premiumCalculationMap.get("unearnedPremium"));
			ctx.put("cancellationReturnTax",  premiumCalculationMap.get("unearnedTax"));
			ctx.put("cancellationTotalReturnPremium", premiumCalculationMap.get("totalunearnedPremium"));
			ctx.put("totalOldPremium", premiumCalculationMap.get("totalOldPremium"));
			ctx.put("premimDifference", premiumCalculationMap.get("premimDifference"));
			ctx.put("returnPremium","-"+ premiumCalculationMap.get("unearnedPremium"));
			ctx.put("returnTax","-"+ premiumCalculationMap.get("unearnedTax"));
			
		}
		
		
	}
	public static void validateCancelEndorsementManually(Context ctx) throws Exception
	{
		//int cReason=ctx.get("cancellationReason")!=null && !ctx.get("cancellationReason").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("cancellationReason").toString()):0;
		int cType=ctx.get("cancellationType")!=null && !ctx.get("cancellationType").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("cancellationType").toString()):0;
		try	
		{
		if(cType==1)
		{
			Object objRule = RuleUtils.executeRule(ctx, "LawyersRule.cancelFlatEndorsementDateValidation");
			logger.debug("Going to validate Cancel Reinstate Endorsement ");
			if(objRule != null)
			{
				Boolean rule = (Boolean)objRule;
				if(rule==false)
					LawyersUtils.populateError(ctx, "CancellationEffectiveDate","Cancellation effective Date should be equal to policy effective date.");
			}
			
		}
		
		int transactionTypeKey=ctx.get("TransactionTypeKey")!=null?Integer.parseInt(ctx.get("TransactionTypeKey").toString()):0;
		if(transactionTypeKey==7 && "Y".equals(ctx.get("isEnforcedPolicy"))  && !"calculateCancellationPremium".equalsIgnoreCase(ctx.get("inet_method").toString()))
		{
			String cancellationReturnPremium=ctx.get("cancellationReturnPremium")!=null && !ctx.get("cancellationReturnPremium").equals(HtmlConstants.EMPTY)?ctx.get("cancellationReturnPremium").toString():"BLANK";
			if(cancellationReturnPremium.equals("BLANK"))
				LawyersUtils.populateError(ctx, "premiumError","You need to calculate premium for policy.");
				
		}
		}
		catch(Exception e)
		{
			logger.error("Unable to validate cancel/reinstate endorsement", e);
			
		}
	}
	public static void calculateReinstatementPremium(Context ctx)
	{
		
		DecimalFormat  formatter = new DecimalFormat("#0.00");  
		double premium=ctx.get("TotalPremium")!=null && !ctx.get("TotalPremium").equals(HtmlConstants.EMPTY)?Double.parseDouble(ctx.get("TotalPremium").toString()):0.0;
		double tax=ctx.get("TotalPolicyTaxAmount")!=null && !ctx.get("TotalPolicyTaxAmount").equals(HtmlConstants.EMPTY)?Double.parseDouble(ctx.get("TotalPolicyTaxAmount").toString()):0.0;
		
		ctx.put("reinstatementReturnPremium", formatter.format(premium));
		ctx.put("reinstatementReturnTax", formatter.format(tax));
		ctx.put("reinstatementTotalReturnPremium", formatter.format(premium+tax));
		ctx.put("returnPremium",premium);
		ctx.put("returnTax",tax);
		
	}
	public static void setCurrentLimitData(Context ctx)
	{
	
		
		try {
					
			new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey");
			List dataList=SqlResources.getSqlMapProcessor(ctx).select("PolicyTransaction.GetCurrentLimitDeductiblesData_p",ctx);
			if(dataList!=null && dataList.size() > 0)
			{
				//ctx.putAll((Map) dataList.get(0));
				Map map = (Map) dataList.get(0);
				ctx.put("currentPolicyLimit", map.get("currentLimit"));
				ctx.put("currentPolicyClaimExpensesType", map.get("currentClaimExpensesType"));
				ctx.put("currentPolicyDollarDefense", map.get("currentDollarDefense"));
				ctx.put("currentPolicyDeductible", map.get("currentDeductible"));
				ctx.put("currentPolicyClaimOptionType", map.get("currentClaimOptionType"));
				/*ctx.put("IsClaimOptionType1", map.get("IsClaimOptionType"));
				 ctx.put("IsDollarDefense", map.get("IsDollarDefense")); */		
			}
			/*ctx.put("currentPolicyLimit", ctx.get("currentLimit"));
			ctx.put("currentPolicyClaimExpensesType", ctx.get("currentClaimExpensesType"));
			ctx.put("currentPolicyDollarDefense", ctx.get("currentDollarDefense"));
			ctx.put("currentPolicyDeductible", ctx.get("currentDeductible"));
			ctx.put("currentPolicyClaimOptionType", ctx.get("currentClaimOptionType"));
			ctx.put("IsClaimOptionType1", ctx.get("IsClaimOptionType"));
			ctx.put("IsDollarDefense1", ctx.get("IsDollarDefense"));*/
			
			}
		
			catch (Exception e) {
					logger.error("Unexpected error", e);
				}
	}
	public static void setRatingReponseValues(Context ctx)
	{
	
		
		try {
			
				/*new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,AccountKey,AddressKey,LastUpdateUserID,DateOfEndorsement,TypeOfEndorsement");
				List TransactionList=SqlResources.getSqlMapProcessor(ctx).select("FirmLW.LimitDeductiblesEndorsementLW_p",ctx);
				if(TransactionList!=null && TransactionList.size()>0)
					ctx.putAll((Map) TransactionList.get(0));*/
				
				new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey");
				List dataList=SqlResources.getSqlMapProcessor(ctx).select("PolicyTransaction.ParseXMLToRequest_p",ctx);
				if(dataList!=null && dataList.size()>0)
					ctx.putAll((Map) dataList.get(0));
				
			/*	SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdatePolicyTransaction",ctx);
				SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdatePolicyCoverage",ctx);*/
				
			}
		
			catch (Exception e) {
					logger.error("Unexpected error", e);
				}
	}
	
	public static void fetchDataForEmail(Context ctx,String outFile)
	{
		
		
		try{
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
	    	String email_notification_event_name = ctx.get("event_name").toString();
	    	Context newCtx = new Context();
	    	newCtx.setProject(ctx.getProject());
	    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
	    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
	    	newCtx.put("UserNo", ctx.get("UserNo"));
	    	newCtx.put("PolicyKey", ctx.get("PolicyKey"));
	    	newCtx.put("event_name", email_notification_event_name);
	    	
	    	//going to get event description from database
	    	Object obj = SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementdroolQueriesgetEmailNotificationData", newCtx);
	    	if(obj != null && obj instanceof List){
	    		List list = (List)obj;
	    		for(int i=0; i<list.size(); i++){
	    			Map map = (Map)list.get(i);
	    			
	    			if(i > 0){
		    			ctx.put(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_FROMEMAIL_ADDRESS, ctx.get(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_FROMEMAIL_ADDRESS + "_"+i));
		    			ctx.put(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_TOEMAIL_ADDRESS, ctx.get(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_TOEMAIL_ADDRESS + "_"+i));
		    			ctx.put(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_CCEMAIL_ADDRESS, ctx.get(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_CCEMAIL_ADDRESS + "_"+i));
	    			}
	    			
	    			//sendEmailNotification(ctx, newCtx, map);
	    			sendEmailNotification(ctx, map,outFile);
	    			ctx.put("isEmailSent", "Y");
	    		}
	    	}
    	}catch (Exception e) {
    		logger.error("Unable to send email notification due to error : " + e.getMessage());
    		ctx.put("isEmailSent", "N");
		}
		
	}
	private static void sendEmailNotification(Context ctx, Map map, String outFile) throws Exception {
		Object  obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetEndorsementAccountDetails", ctx);
	
	if(obj != null && obj instanceof Map){
        Map map1 = (Map)obj;
        ctx.putAll(map1);
    }
	
	
	
		List<String> attachments = new ArrayList<String>();
		attachments.add(outFile);	
		String htmlDir = SystemProperties.getInstance().getString("html.basedir");
		String to_email_address = null;
		String cc_email_address = null;
		String from_email_address = null;
		String subject = null;
		String bodyContent = null;
		
		String from_role_desc = map.get("from_role_desc") != null ? map.get("from_role_desc").toString() : null;
		String to_role_desc = map.get("to_role_desc") != null ? map.get("to_role_desc").toString() : null;
		String cc_role_desc = map.get("cc_role_desc") != null ? map.get("cc_role_desc").toString() : null;
		String email_template = map.get("email_template") != null ? map.get("email_template").toString() : null;
		
		String attachment_path = map.get("attachment_path") != null ? map.get("attachment_path").toString() : null;
		String attachment_content = map.get("attachment_content") != null ? map.get("attachment_content").toString() : null;
		ctx.put("Firm_Name", ctx.get("AccountName"));
		ctx.put("Quote_no", ctx.get("QuoteNumber"));
		ctx.put("PolicyNumber", ctx.get("PolicyNumber"));
		ctx.put("EffectiveDate", ctx.get("PolicyEffectiveDate"));
		ctx.put("ExpirationDate", ctx.get("PolicyExpirationDate"));
		ctx.put("CancellationDate",ctx.get("TransactionEffectiveDate"));
		String environmentVar = null;
		environmentVar = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment");
		//environmentVar="PD";
		if(environmentVar.equals("QA")|| environmentVar.equals("UAT"))
		{
			from_role_desc=SystemProperties.getInstance().getString("mail.admin.from.address");
			to_role_desc = SystemProperties.getInstance().getString("mail.admin.to.address");
			cc_role_desc = SystemProperties.getInstance().getString("mail.admin.cc.address");
		}
		from_role_desc=SystemProperties.getInstance().getString("mail.admin.from.address");
		cc_role_desc = SystemProperties.getInstance().getString("mail.admin.cc.address");
			subject = map.get("subject") != null ? map.get("subject").toString() : HtmlConstants.EMPTY;
			if(!StringUtils.isBlank(subject)){
				subject = new MessageStringFormat(ctx).format(subject);
			}
			
			bodyContent = map.get("body") != null ? map.get("body").toString() : HtmlConstants.EMPTY;
			if(!StringUtils.isBlank(bodyContent)){
				bodyContent = new MessageStringFormat(ctx).format(bodyContent);
			}
			
			
			if(ctx.get("isSendMailTrue") != null && !HtmlConstants.EMPTY.equals(ctx.get("isSendMailTrue").toString())&& "Y".equals(ctx.get("isSendMailTrue"))){
					
				boolean producerCodeExist = new LawyersValidationUtils().checkIfSubProducerExist(ctx);
				if (producerCodeExist)
				{
					try 
					{
						
						Object  objSub = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesSubProducerData", ctx);
						
						if(objSub != null && objSub instanceof Map){
					        Map map1 = (Map)objSub;
					        ctx.putAll(map1);
					    }
						String isDriect = ctx.get("IsDriect") != null ? ctx.get("IsDriect").toString() : "N";
					if("Y".equalsIgnoreCase(isDriect))	
						to_role_desc = LawyersUtils.getEmailID(ctx);
					
					else
						to_role_desc = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.email");
					
				}catch (Exception e) 
					{logger.error("error in getting admin email address");}

				} else {
					to_role_desc = LawyersUtils.getEmailID(ctx);
				}

				if ("".equals(to_role_desc))
					return;
				
					MailSender mailSender = new MailSender();
					mailSender.setContentType("text/html");
					
					mailSender.setFromAdd(from_role_desc);
					mailSender.setToAdd(to_role_desc);
					mailSender.setCcAdd(cc_role_desc);
					mailSender.setSubject(subject);
					mailSender.setBody(bodyContent);
					if(attachments!=null && attachments.size()>0)
					mailSender.setAttachments(attachments);
					mailSender.sendMail(ctx);
					
					logger.debug("Mail Sent");
					ctx.remove("email_notification_event_name");
				}
			
			
			
			
		}
	
  public static void setEndorsementPremium(Context ctx)
  {
	  DecimalFormat  formatter = new DecimalFormat("#0.00"); 
	  List<Map<String, Object>> calculationDataList=new ArrayList<Map<String, Object>>();
	  try
	  {
		  List dataList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetFinilizedPremium", ctx);
		  if(dataList!=null && dataList.size()>0)
			{
				ctx.putAll((Map) dataList.get(0));
				
			}
		 
		 
		  	if("9".equals(ctx.get("EndorsementType").toString()))
		  			{
		  			
				  double litmitsEndorsementPremium=ctx.get("litmitsEndorsementPremium")!=null && !ctx.get("litmitsEndorsementPremium").equals(HtmlConstants.EMPTY)? Double.parseDouble(ctx.get("litmitsEndorsementPremium").toString()):0;
				  double litmitsEndorsementTax=ctx.get("litmitsEndorsementTax")!=null && !ctx.get("litmitsEndorsementTax").equals(HtmlConstants.EMPTY)? Double.parseDouble(ctx.get("litmitsEndorsementTax").toString()):0;
				  ctx.put("EndorsementEffectiveDate", ctx.get("limitEndorsementEffectiveDate"));
				  Map<String,Object> premiumCalculationMap= calculateEndorsementPremium(ctx,litmitsEndorsementPremium, litmitsEndorsementTax);
				   calculationDataList.add(premiumCalculationMap);
		  			ctx.put("calculationDataList", calculationDataList);
		  			LawyersUtils.convertListDataToXML(ctx,"calculationDataList","endorsementCalculationList");
		  			ctx.put("endorsementDataList", ctx.get("endorsementCalculationList"));
				  
					ctx.put("returnPremium",premiumCalculationMap.get("unearnedPremium"));
		  			ctx.put("returnTax",premiumCalculationMap.get("unearnedTax"));
		  			ctx.put("litmitsEndorsementPremiumNew",premiumCalculationMap.get("unearnedPremium"));
					ctx.put("litmitsEndorsementTaxNew",premiumCalculationMap.get("unearnedTax"));
					ctx.put("litmitsEndorsementTotalPremiumNew",premiumCalculationMap.get("totalunearnedPremium"));
					ctx.put("litmitsEndorsementPremiumNew1",premiumCalculationMap.get("totalunearnedPremium"));
		  			ctx.put("FYEndorsementPremium", litmitsEndorsementPremium);
		  			ctx.put("FYEndorsementTax", litmitsEndorsementTax);
		  			
		  			
		  			}
		  	else if("10".equals(ctx.get("EndorsementType").toString()))
		  	{
		  		double deductibleEndorsementPremium=ctx.get("deductibleEndorsementPremium")!=null && !ctx.get("deductibleEndorsementPremium").equals(HtmlConstants.EMPTY)? Double.parseDouble(ctx.get("deductibleEndorsementPremium").toString()):0;
		  		double deductibleEndorsementdTax=ctx.get("deductibleEndorsementdTax")!=null && !ctx.get("deductibleEndorsementdTax").equals(HtmlConstants.EMPTY)? Double.parseDouble(ctx.get("deductibleEndorsementdTax").toString()):0;
		  		ctx.put("EndorsementEffectiveDate", ctx.get("deductibleEndorsementEffectiveDate"));
				Map<String,Object> premiumCalculationMap= calculateEndorsementPremium(ctx,deductibleEndorsementPremium, deductibleEndorsementdTax);
				calculationDataList.add(premiumCalculationMap);
		  		ctx.put("calculationDataList", calculationDataList);
		  		LawyersUtils.convertListDataToXML(ctx,"calculationDataList","endorsementCalculationList");
		  		ctx.put("endorsementDataList", ctx.get("endorsementCalculationList"));
				ctx.put("returnPremium",premiumCalculationMap.get("unearnedPremium"));
	  			ctx.put("returnTax",premiumCalculationMap.get("unearnedTax"));
				ctx.put("deductibleEndorsementPremium",premiumCalculationMap.get("totalunearnedPremium"));
				ctx.put("deductibleEndorsementPremiumNew",premiumCalculationMap.get("totalunearnedPremium"));
				ctx.put("deductibleEndorsementPremiumNew1",premiumCalculationMap.get("totalunearnedPremium"));
				ctx.put("FYEndorsementPremium", deductibleEndorsementPremium);
	  			ctx.put("FYEndorsementTax", deductibleEndorsementdTax);
		
		  	}
		  	else if("12".equals(ctx.get("EndorsementType").toString()))
	  		{
		  	  double endorsementPremium=ctx.get("litmitsEndorsementPremium")!=null && !ctx.get("litmitsEndorsementPremium").equals(HtmlConstants.EMPTY)? Double.parseDouble(ctx.get("litmitsEndorsementPremium").toString()):0;
			  double endorsementTax=ctx.get("litmitsEndorsementTax")!=null && !ctx.get("litmitsEndorsementTax").equals(HtmlConstants.EMPTY)? Double.parseDouble(ctx.get("litmitsEndorsementTax").toString()):0;
			  ctx.put("EndorsementEffectiveDate",ctx.get("priorActEndorsementDate"));
			  Map<String,Object> premiumCalculationMap= calculateEndorsementPremium(ctx,endorsementPremium, endorsementTax);
				calculationDataList.add(premiumCalculationMap);
		  		ctx.put("calculationDataList", calculationDataList);
		  		LawyersUtils.convertListDataToXML(ctx,"calculationDataList","endorsementCalculationList");
		  		ctx.put("endorsementDataList", ctx.get("endorsementCalculationList"));
				ctx.put("returnPremium",premiumCalculationMap.get("unearnedPremium"));
	  			ctx.put("returnTax",premiumCalculationMap.get("unearnedTax"));
	  			ctx.put("totalReturnPremium",premiumCalculationMap.get("totalunearnedPremium"));
	  			ctx.put("FYEndorsementPremium", endorsementPremium);
	  			ctx.put("FYEndorsementTax", endorsementTax);
	
		  		}
		  	else if("13".equals(ctx.get("EndorsementType").toString()))
	  		{
				
				ctx.put("EndorsementEffectiveDate",ctx.get("policyExtendExpirationDate"));
				Map<String,Object> premiumCalculationMap=calculateEndorsementPremium(ctx);
				premiumCalculationMap.put("EndorsementType",ctx.get("EndorsementType"));
				calculationDataList.add(premiumCalculationMap);
	  			ctx.put("calculationDataList", calculationDataList);
	  			LawyersUtils.convertListDataToXML(ctx,"calculationDataList","endorsementCalculationList");
	  			ctx.put("endorsementDataList", ctx.get("endorsementCalculationList"));
	  			ctx.put("returnPremium",premiumCalculationMap.get("earnedPremium"));
				ctx.put("returnTax",premiumCalculationMap.get("earnedTax"));
				ctx.put("totalReturnPremium",premiumCalculationMap.get("totalEarnedPremium"));
				ctx.put("TotalPremium",premiumCalculationMap.get("totalEarnedPremium"));
//		  		ctx.put("TotalPolicyTaxAmount",premiumCalculationMap.get("totalOldPremium"));
//		  		ctx.put("totalUnearnedPremium",premiumCalculationMap.get("totalunearnedPremium"));
		  		ctx.put("prorataPremium",premiumCalculationMap.get("ProrataPremium"));
		  	
	  		}
			else if("14".equals(ctx.get("EndorsementType").toString()) || "15".equals(ctx.get("EndorsementType").toString()))
	  		{
				ctx.put("EndorsementEffectiveDate",ctx.get("endorsementEffectiveDate"));
				
				double endorsementPremium=ctx.get("endorsementPremium")!=null && !ctx.get("endorsementPremium").equals(HtmlConstants.EMPTY)? Math.round(Double.parseDouble(ctx.get("endorsementPremium").toString())):0;
				double endorsementTax=ctx.get("endorsementTax")!=null && !ctx.get("endorsementTax").equals(HtmlConstants.EMPTY)? Math.round(Double.parseDouble(ctx.get("endorsementTax").toString())):0;
				 Map<String,Object> premiumCalculationMap= calculateEndorsementPremium(ctx,endorsementPremium, endorsementTax);
				calculationDataList.add(premiumCalculationMap);
		  		ctx.put("calculationDataList", calculationDataList);
		  		LawyersUtils.convertListDataToXML(ctx,"calculationDataList","endorsementCalculationList");
		  		ctx.put("endorsementDataList", ctx.get("endorsementCalculationList"));
				ctx.put("endorsementPremium",premiumCalculationMap.get("unearnedPremium"));
				ctx.put("endorsementTotalPremium",premiumCalculationMap.get("totalunearnedPremium"));
				ctx.put("endorsementTaxes",premiumCalculationMap.get("unearnedTax"));
				ctx.put("returnPremium",premiumCalculationMap.get("unearnedPremium"));
	  			ctx.put("returnTax",premiumCalculationMap.get("unearnedTax"));
	  			ctx.put("FYEndorsementPremium", endorsementPremium);
	  			ctx.put("FYEndorsementTax", endorsementTax);
	  		
	  		}
			else if("16".equals(ctx.get("EndorsementType").toString()))
	  		{
				
				ctx.put("EndorsementEffectiveDate",ctx.get("policyExtendExpirationDate"));
				Map<String,Object> premiumCalculationMap=calculateEndorsementPremium(ctx);
				premiumCalculationMap.put("EndorsementType",ctx.get("EndorsementType"));
				calculationDataList.add(premiumCalculationMap);
	  			ctx.put("calculationDataList", calculationDataList);
	  			LawyersUtils.convertListDataToXML(ctx,"calculationDataList","endorsementCalculationList");
	  			ctx.put("endorsementDataList", ctx.get("endorsementCalculationList"));
				ctx.put("returnPremium",premiumCalculationMap.get("unearnedPremium"));
				ctx.put("totalReturnPremium",premiumCalculationMap.get("totalunearnedPremium"));
				ctx.put("returnTax",premiumCalculationMap.get("unearnedTax"));
	  		
	  		}
		  	
	  }
	  catch(Exception e)
	  {
		  logger.error("Unexpected error", e);
	  }
	  
  }
public static void validateManualPremium(Context ctx)
{
	try
	{
		 List dataList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetFinilizedPremium", ctx);
		  if(dataList!=null && dataList.size()>0)
			{
				ctx.putAll((Map) dataList.get(0));
				
			}
		  String isManualPremiumSelected=ctx.get("IsManualPremium")!=null && !ctx.get("IsManualPremium").equals(HtmlConstants.EMPTY)?ctx.get("IsManualPremium").toString():"N";
		  if("Y".equals(isManualPremiumSelected))
			  new LawyersUtils().populateError(ctx, "endorsementError","Cannot endorse for limit/deductible for manual premium.");
		  
		  
		  
			if("Y".equals(ctx.get("isEnforcedPolicy")) &&  "N".equals(ctx.get("anyDecendentPolicy").toString()) && !"calculatePremium".equalsIgnoreCase(ctx.get("inet_method").toString()) && !"calculatePremiumDeductible".equalsIgnoreCase(ctx.get("inet_method").toString()))
			{
				
				
					if("LC".equals(ctx.get("limitDeuctibleEndorsementType").toString()))
					{
						String litmitsEndorsementPremiumNew=ctx.get("litmitsEndorsementPremiumNew")!=null && !ctx.get("litmitsEndorsementPremiumNew").equals(HtmlConstants.EMPTY)?ctx.get("litmitsEndorsementPremiumNew").toString():"BLANK";
						if(litmitsEndorsementPremiumNew.equals("BLANK"))
						LawyersUtils.populateError(ctx, "LimitpremiumError","You need to calculate premium for policy.");
					
					}
					if("DC".equals(ctx.get("limitDeuctibleEndorsementType").toString()))
					{
						String deductibleEndorsementPremiumNew=ctx.get("deductibleEndorsementPremiumNew")!=null && !ctx.get("deductibleEndorsementPremiumNew").equals(HtmlConstants.EMPTY)?ctx.get("deductibleEndorsementPremiumNew").toString():"BLANK";
						if(deductibleEndorsementPremiumNew.equals("BLANK"))
						LawyersUtils.populateError(ctx, "DeductiblepremiumError","You need to calculate premium for policy.");
				
					}
				
			}
	}
	catch(Exception e)
	{
		logger.error("errro in validating manual quote");
	}
}
public static  void calculateClaimAgeEndorsement(Context ctx) throws Exception {
	Object obj=null;
	//removing previous cm factors without versionsequence from ClaimAgeLWEndorsement
	SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesupdateClaimAgeLWEndorsement",ctx);
	
	com.userbo.LawyersUtils utils=new com.userbo.LawyersUtils();
	Object objPolicy = SqlResources.getSqlMapProcessor(ctx).findByKey("Policy.findByKey", ctx);
	Map poliMap = objPolicy != null && objPolicy instanceof Map ? (Map) objPolicy: null;
	
	
	/*String IsFirmCarryingProfLiabilityIns = "N";
	Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetIsFirmCarryingProfLiabilityIns",ctx);
	if(obj != null && obj instanceof Map){
		Map map = (Map) obj;
		IsFirmCarryingProfLiabilityIns = map.get("IsFirmHaveLawyersLiabilityInsurance") == null ? "N" : map.get("IsFirmHaveLawyersLiabilityInsurance").toString();
	}*/
	
	int firmStepRateYears=0;
	boolean isPriorActEndorsement = false;        
	Object objRule = RuleUtils.executeRule(ctx,"LawyersRule.isPriorActDateEndorsement");
    if (objRule != null && objRule instanceof Boolean) {
     	isPriorActEndorsement = (Boolean) objRule;
    }	
	Date policyEffectiveDate = ctx.get("PolicyEffectiveDate") != null ? LawyersDateUtils.convertStringToDate(ctx.get("PolicyEffectiveDate").toString(), "yyyy-MM-dd") : null;
	Date priorActDate =null;
	if(isPriorActEndorsement)
	{
    	 priorActDate = ctx.get("priorActEndorsementPriorActDate") != null ? LawyersDateUtils.convertStringToDate(ctx.get("priorActEndorsementPriorActDate").toString(), "yyyy-MM-dd") : null;
    	 if (priorActDate != null && policyEffectiveDate != null) 
    			firmStepRateYears = utils.getStepRateYearBetweenTwoDate(priorActDate,policyEffectiveDate);
	}
    	 else
    		 firmStepRateYears = utils.calculateFirmStepRateYears(ctx);

  
	
    	
    int endorsementType=ctx.get("EndorsementType")!=null?Integer.parseInt(ctx.get("EndorsementType").toString()):0;
    Object objAttorney =null;
    if(endorsementType==14 || endorsementType==15)
    	objAttorney = ctx.get("firm_list_4");
    
    else
    {
    	utils.checkNewFiling(ctx);
    	boolean isNewFiling = false;        
    	 objRule = RuleUtils.executeRule(ctx,"LawyersRule.checkAttorneyNewFiling");
        if (objRule != null && objRule instanceof Boolean) 
        	isNewFiling = (Boolean) objRule;
        
        if(isNewFiling)
        	objAttorney = (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAttorneysListNewFiling",ctx);
        else
        	objAttorney = (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAttorneysListOldFiling",ctx);
    }
	if (objAttorney != null && objAttorney instanceof List) {
		List attorneyList = (List) objAttorney;
		for (int i = 0; i < attorneyList.size(); i++) {
			Map attornMap = (Map) attorneyList.get(i);
			int attorneyKey=attornMap.get("AttorneyKey")!=null?Integer.parseInt(attornMap.get("AttorneyKey").toString()):1;
			int attorneyStepRateYears = utils.calculateAttorneyStepRateYears(attornMap, poliMap);	
			int claimAge = utils.getClaimAge(attorneyStepRateYears, firmStepRateYears);				
			new LawyersUtils().saveClaimAgeDataFullQuoteEndorsement(ctx, claimAge,attorneyKey);
			int j=i+1;
			ctx.put("ClaimAge_"+j,claimAge);
		}
		
	}
}
public static void fetchERPLetterData(Context ctx)
{
	 DecimalFormat  formatter = new DecimalFormat("0,000"); 
	 Map<String,Long> map=null;
	 Object obj1=null;
	logger.debug("fetching data for erp letter");
	try
	{
		String stateCode=ctx.get("StateCode").toString();
		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetERPQuoteLetterData", ctx);
		if(obj != null && obj instanceof Map)
			ctx.putAll((Map)obj);
		new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey");
		new SetParametersForStoredProcedures().setParametersInContext(ctx, "endorsementEffectiveDate");
		
		List data= (List) SqlResources.getSqlMapProcessor(ctx).select("PolicyTransaction.SetQuoteExpirationDate",ctx);
		ctx.putAll((Map) data.get(0));
		
		String isAttorneyRetiring=ctx.get("isAttorneyRetiring")!=null && !ctx.get("isAttorneyRetiring").equals(HtmlConstants.EMPTY)?ctx.get("isAttorneyRetiring").toString():"N";
		String isFirmERP=ctx.get("isFirmERP")!=null && !ctx.get("isFirmERP").equals(HtmlConstants.EMPTY)?ctx.get("isFirmERP").toString():"N";
		int practicePortion=ctx.get("practicePortion")!=null &&  !ctx.get("practicePortion").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("practicePortion").toString()):0;
		
		int statusKey=ctx.get("StatusKey")!=null && !ctx.get("StatusKey").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("StatusKey").toString()):0;
		if(statusKey==8)
		{
			 obj1 = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetFinalizedInvoicedPremium", ctx);
			if(obj1!= null && obj1 instanceof Map)
			{
				ctx.putAll((Map)obj1);
			}
		}
		else
		{
			obj1 = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetFinalizedInvoicedPremiumForNonCancelled", ctx);
			if(obj1!= null && obj1 instanceof Map)
			{
				ctx.putAll((Map)obj1);
			}
		}
		double totalPremium=ctx.get("TotalPremium")!=null && !ctx.get("TotalPremium").equals(HtmlConstants.EMPTY)?Double.parseDouble(ctx.get("TotalPremium").toString()):0;
		double totalPolicyTaxAmount=ctx.get("TotalPolicyTaxAmount")!=null && !ctx.get("TotalPolicyTaxAmount").equals(HtmlConstants.EMPTY)?Double.parseDouble(ctx.get("TotalPolicyTaxAmount").toString()):0;
		double invoicePremium=ctx.get("InvoicedPremium")!=null && !ctx.get("InvoicedPremium").equals(HtmlConstants.EMPTY)?Double.parseDouble(ctx.get("InvoicedPremium").toString()):0;
		
		invoicePremium=Math.round(invoicePremium);
		NumberFormat numberFormat =NumberFormat.getCurrencyInstance(Locale.US);
		String endorsementPremiumpdf=numberFormat.format(invoicePremium);
		
		
		if("Y".equals(isAttorneyRetiring))
		{
			ctx.put("InsuredName", ctx.get("AttorneyName"));
			double insuredPercentage=(practicePortion*invoicePremium)/100;
			double unlimitedYearQuote=0.0;
			if("AK".equals(stateCode))
				 unlimitedYearQuote=Math.round((insuredPercentage*200)/100);	
			else
			 unlimitedYearQuote=Math.round((insuredPercentage*300)/100);
			ctx.put("UnlimitedERPForAttorney",numberFormat.format(unlimitedYearQuote).split("\\.")[0]);
		}
			if("Y".equals(isFirmERP))
			{
				ctx.put("InsuredName", ctx.get("AccountName"));
				
				if(ctx.get("oneYear")!=null && "1".equals(ctx.get("oneYear").toString()))
					ctx.put("oneYearERP", endorsementPremiumpdf.split("\\.")[0]);
				if(ctx.get("threeYear")!=null && "3".equals(ctx.get("threeYear").toString()))
				{
						map=setERPPremiumAsPerYears(totalPremium,totalPolicyTaxAmount,185);
					ctx.put("threeYearsERP", numberFormat.format(map.get("ERPTotalPremium")).split("\\.")[0]);
				}
				if(ctx.get("fiveYear")!=null && "5".equals(ctx.get("fiveYear").toString()))
				{
					if("AK".equals(stateCode))
					{
						map=setERPPremiumAsPerYears(totalPremium,totalPolicyTaxAmount,195);
						ctx.put("fiveYearsERP", numberFormat.format(map.get("ERPTotalPremium")).split("\\.")[0]);
					}
					else
					{
						map=setERPPremiumAsPerYears(totalPremium,totalPolicyTaxAmount,225);
						ctx.put("fiveYearsERP", numberFormat.format(map.get("ERPTotalPremium")).split("\\.")[0]);
					}
				}
					if(ctx.get("sixYear")!=null && "6".equals(ctx.get("sixYear").toString()))
					{
						map=setERPPremiumAsPerYears(totalPremium,totalPolicyTaxAmount,250);
					ctx.put("sixYearsERP", numberFormat.format(map.get("ERPTotalPremium")).split("\\.")[0]);
					}
				if(ctx.get("unlimitedYears")!=null && "10".equals(ctx.get("unlimitedYears").toString()))
				{
					map=setERPPremiumAsPerYears(totalPremium,totalPolicyTaxAmount,300);
					ctx.put("unlimitedYearsERP", numberFormat.format(map.get("ERPTotalPremium")).split("\\.")[0]);
				}
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			@SuppressWarnings("deprecation")
			Date expirationDate = new Date(ctx.get("PolicyExpirationDate").toString());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(expirationDate);
			calendar.add(Calendar.DAY_OF_YEAR, 60);
			Date erpExpirationdate = calendar.getTime();
			Date todaydt=new Date();
			String todaydate=sdf.format(todaydt);
			ctx.put("currentdate", todaydate);
	}
	catch(Exception e)
	{
		logger.error("exception occured while fetching data for quote letter : "+e.getMessage());
	}
	logger.debug("fetching data for erp letter ended");
}


public static void sendErpQuoteLetter(Context ctx,String outFile)
{
try
{
	List<String> attachments = new ArrayList<String>();
	attachments.add(outFile);	
		Object  obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetEndorsementAccountDetails", ctx);
		
		if(obj != null && obj instanceof Map){
		    Map map1 = (Map)obj;
		    ctx.putAll(map1);
		}
		 List obj1 = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetEmailNotificationData", ctx);
		 if(obj1 != null && obj1 instanceof List){
			    ctx.putAll((Map) obj1.get(0));
			}
		String subject = null;
		String bodyContent = null;
		
		String from_role_desc = ctx.get("from_role_desc") != null ? ctx.get("from_role_desc").toString() : null;
		String to_role_desc = ctx.get("to_role_desc") != null ? ctx.get("to_role_desc").toString() : null;
		String cc_role_desc = ctx.get("cc_role_desc") != null ? ctx.get("cc_role_desc").toString() : null;
		ctx.put("Firm_Name", ctx.get("AccountName"));
		ctx.put("Quote_no", ctx.get("QuoteNumber"));
		ctx.put("PolicyNumber", ctx.get("PolicyNumber"));
		ctx.put("EffectiveDate", ctx.get("PolicyEffectiveDate"));
		ctx.put("ExpirationDate", ctx.get("PolicyExpirationDate"));
		ctx.put("CancellationDate",ctx.get("TransactionEffectiveDate"));
		String environmentVar = null;
		environmentVar = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment");
		//environmentVar="PD";
		if(environmentVar.equals("QA")|| environmentVar.equals("UAT"))
		{
			from_role_desc=SystemProperties.getInstance().getString("mail.admin.from.address");
			to_role_desc = SystemProperties.getInstance().getString("mail.admin.to.address");
			cc_role_desc = SystemProperties.getInstance().getString("mail.admin.cc.address");
		}
		from_role_desc=SystemProperties.getInstance().getString("mail.admin.from.address");
		cc_role_desc = SystemProperties.getInstance().getString("mail.admin.cc.address");
		subject = ctx.get("subject") != null ? ctx.get("subject").toString() : HtmlConstants.EMPTY;
		if(!StringUtils.isBlank(subject)){
			subject = new MessageStringFormat(ctx).format(subject);
		}
		bodyContent = ctx.get("body") != null ? ctx.get("body").toString() : HtmlConstants.EMPTY;
		if(!StringUtils.isBlank(bodyContent)){
			bodyContent = new MessageStringFormat(ctx).format(bodyContent);
		}
		if(ctx.get("isSendMailTrue") != null && !HtmlConstants.EMPTY.equals(ctx.get("isSendMailTrue").toString())&& "Y".equals(ctx.get("isSendMailTrue"))){
			
		boolean producerCodeExist =  LawyersValidationUtils.checkIfSubProducerExist(ctx);
		if (producerCodeExist) 
		{
				try {
					Object  objsp = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesSubProducerData", ctx);
					
					if(objsp != null && objsp instanceof Map){
				        Map map1 = (Map)objsp;
				        ctx.putAll(map1);
				    }
					to_role_desc = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.email");
								
					if("Y".equals(ctx.get("IsDriect")))
						to_role_desc = LawyersUtils.getEmailID(ctx);
				} 
				catch (Exception e) 
				{ logger.error("Unable to resolve endorsement recipient", e); }
			} 
			else 
				to_role_desc = LawyersUtils.getEmailID(ctx);
			

			if ("".equals(to_role_desc))
			return;

			String isMailing = SystemProperties.getInstance().getString("Insured.sendmail");

			// Check Mailing is allowed If Y then Send

			if (isMailing != null && "Y".equals(isMailing) && !"".equals(to_role_desc))
			{
				if(!"Y".equals(ctx.get("SubProducerAccess")))
				{
					MailSender mailSender = new MailSender();
					mailSender.setSubject("Endorsement of "+ctx.get("PolicyNumber")+" "+"for"+" "+ ctx.get("AccountName"));
					mailSender.setToAdd(to_role_desc);
					mailSender.setIsSentToCC("Y");
					
					mailSender.setContentType("text/html");
	
				
					// mailSender.setImgPath(imgPath);
					mailSender.setBody(bodyContent);
					mailSender.setAttachments(attachments);
					mailSender.sendMail();
					
				}
				else {
					
					String toAddress= "";
					String ccAddress= "";
				String productionEnv = "Y";
				try {
					productionEnv = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production");
				} 
				catch (Exception e) 
				{
					logger.error("error in geeting production environment");
				}
				if ("N".equals(productionEnv))
					ccAddress=SystemProperties.getInstance().getString("mail.admin.cc.address");
				else 
				ccAddress = SystemProperties.getInstance().getString("mail.adminsub.cc.address");
	
				if("Y".equals(ctx.get("SendQuoteLetter")) && "Y".equals(ctx.get("SubProducerAccess"))){
					toAddress = LawyersUtils.getEmailID(ctx);
					ccAddress = LawyersUtils.getProducerEmail(ctx);
					}
				if("N".equals(ctx.get("SendQuoteLetter")) && "Y".equals(ctx.get("SubProducerAccess"))){
						  toAddress = LawyersUtils.getProducerEmail(ctx);
					}
	
	
					ctx.put("toAddress",toAddress);
				ctx.put("ccAddress",ccAddress);
				ctx.put("subject","Endorsement of "+ctx.get("PolicyNumber") +"for"+ ctx.get("AccountName")); 
					ctx.put("body",bodyContent);
					ctx.put("emailAttachment",attachments);
					SubproducerMailer.sendEmailAsSubProducer((Context)ctx);
	
	
			
			/*if ("".equals(to_role_desc))
			return;*/
				}
			
		
		}
			logger.debug("Mail Sent");
			ctx.remove("email_notification_event_name");
		}
}	
catch(Exception e)
{ logger.error("Unable to send endorsement mail", e); }
		
		
	}

public static void calculateERPPremium(Context ctx) {
	Map<String,Long> map=null;
	logger.debug("going to calculate erp endorsement premium");
	DecimalFormat formatter = new DecimalFormat("0,000");
	
	long premium = 0,tax=0;
	double calculatedPremium=0.0,calculatedTax=0.0;
	try {
		
		String stateCode = ctx.get("StateCode").toString();
		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetERPQuoteLetterData", ctx);
		if (obj != null && obj instanceof Map)
			ctx.putAll((Map) obj);
		
		int statusKey = ctx.get("StatusKey") != null && !ctx.get("StatusKey").equals(HtmlConstants.EMPTY) ? Integer.parseInt(ctx.get("StatusKey").toString()) : 0;
		if (statusKey == 8) {
			Object obj1 = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetFinalizedInvoicedPremium",ctx);
			if (obj1 != null && obj1 instanceof Map) {
				ctx.putAll((Map) obj1);
			}
		}
		double invoicePremium = ctx.get("TotalPremium") != null && !ctx.get("TotalPremium").equals(HtmlConstants.EMPTY) ? Double.parseDouble(ctx.get("TotalPremium").toString()) : 0;
		double invoicetax = ctx.get("TotalPolicyTaxAmount") != null && !ctx.get("TotalPolicyTaxAmount").equals(HtmlConstants.EMPTY) ? Double.parseDouble(ctx.get("TotalPolicyTaxAmount").toString()) : 0;
		
		if (ctx.get("isAttorneyRetiring") != null && "Y".equals(ctx.get("isAttorneyRetiring").toString())) {
			if (ctx.get("isInsuredWithUsFromPast") != null	&& "N".equals(ctx.get("isInsuredWithUsFromPast").toString())) {
				int practicePortion = ctx.get("practicePortitonOfAttorney") != null   	 && !ctx.get("practicePortitonOfAttorney").equals(HtmlConstants.EMPTY) ? Integer.parseInt(ctx.get("practicePortitonOfAttorney").toString()) : 0;
				double insuredPercentage = (practicePortion * invoicePremium) / 100;
				double taxWithInsuredPercentage = Math.round((practicePortion * invoicetax) / 100);
				if ("AK".equals(stateCode))
				{
					premium = Math.round((insuredPercentage * 200) / 100);
					tax= Math.round((taxWithInsuredPercentage * 200) / 100);
				}
				else
				{
					premium = Math.round((insuredPercentage * 300) / 100);
					tax= Math.round((taxWithInsuredPercentage * 300) / 100);
				}		
			}
		}
		
		if( ctx.get("isFirmERP")!=null &&  "Y".equals(ctx.get("isFirmERP").toString()))
		 {
		 
		int yearsOfERP = ctx.get("yearsOfERP") != null 	&& !ctx.get("yearsOfERP").equals(HtmlConstants.EMPTY) ? Integer.parseInt(ctx.get("yearsOfERP").toString()) : 0;
		switch (yearsOfERP) {
		case 1:
			premium = Math.round(invoicePremium);
			tax = Math.round(invoicetax);
			break;
		case 3:
			map=setERPPremiumAsPerYears(invoicePremium,invoicetax,185);
			premium = Long.parseLong(map.get("ERPPremium").toString());
			tax = Long.parseLong(map.get("ERPTax").toString());
			break;
		case 5:
			if ("AK".equals(stateCode))
			{
				map=setERPPremiumAsPerYears(invoicePremium,invoicetax,195);
				premium = Long.parseLong(map.get("ERPPremium").toString());
				tax = Long.parseLong(map.get("ERPTax").toString());
			}
			else
			{
				map=setERPPremiumAsPerYears(invoicePremium,invoicetax,225);
				premium = Long.parseLong(map.get("ERPPremium").toString());
				tax = Long.parseLong(map.get("ERPTax").toString());
			}
			break;
		case 6:
			map=setERPPremiumAsPerYears(invoicePremium,invoicetax,250);
			premium = Long.parseLong(map.get("ERPPremium").toString());
			tax = Long.parseLong(map.get("ERPTax").toString());
			break;
		case 10:
			map=setERPPremiumAsPerYears(invoicePremium,invoicetax,300);
			premium = Long.parseLong(map.get("ERPPremium").toString());
			tax = Long.parseLong(map.get("ERPTax").toString());
			break;

		default:
			premium = 0;
			tax=0;
			break;

		}
		 } 
	
	} catch (Exception e) {
		logger.error("Error occured while calculating ERP premium : " + e);
	}

//	premium = Math.round(premium);
//	tax = Math.round(tax);
	long totalPremium=Math.round(premium+tax);
	NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
	String formattedEndorsementPremium = numberFormat.format(premium);
	String formattedEndorsementTax = numberFormat.format(tax);
	String formattedTotalEndorsementPremium = numberFormat.format(totalPremium);
	
	ctx.put("endorsementPremiumExcludeTax", formattedEndorsementPremium.split("\\.")[0]);
	ctx.put("endorsementTax", formattedEndorsementTax.split("\\.")[0]);
	ctx.put("endorsementPremium", formattedTotalEndorsementPremium.split("\\.")[0]);

	ctx.put("ERPPremium",premium);
	ctx.put("ERPTax",tax);
	ctx.put("ERPTotalPremium",totalPremium);
	ctx.put("returnPremium",premium);
	ctx.put("returnTax",tax);
}


public static void getdateforERP(IContext ctx){
	try{
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	Date todaydt=new Date();
	String todaydate=sdf.format(todaydt);
	ctx.put("CurrentDate", todaydate);
	}catch(Exception ex){
		logger.error("Error occured while calculating ERP date : "+ex);
	}
	
	
}

@SuppressWarnings({ "unchecked", "rawtypes" })
public static void getDaysDifference(Context ctx)
{
	
	try
	{		
		new SetParametersForStoredProcedures().setParametersInContext(ctx, "Date1,Date2,PolicyEffectiveDate,PolicyExpirationDate");
		List dataList= (List) SqlResources.getSqlMapProcessor(ctx).select("Policy.GetDatesDifferenceInDays_p",ctx);
		ctx.putAll((Map) dataList.get(0));	
				
	}
	catch(Exception e)
	{
			logger.error("Unable to calculate endorsement day difference", e);
			logger.error("Unexpected error", e);
			
	}
	
}
/*public static double roundDecimalValue(double value)
{

    BigDecimal b1 = new BigDecimal(value) ;
    MathContext m = new MathContext(1); // 4 precision 
    BigDecimal b2 = b1.round(m); 
    return b2.doubleValue();
}*/
@SuppressWarnings("deprecation")
public static Map<String,Object> calculateEndorsementPremium(Context ctx)
{
	logger.debug("Calculating endorsement unearned premium for Policy :"+ctx.get("PolicyKey"));
	HashMap<String,Object> map=new HashMap<String,Object>();
	double unearnedPremiumC=0.0,unearnedTaxC=0.0;
	try
	{	
		int cancellationType=ctx.get("cancellationType")!=null && !ctx.get("cancellationType").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("cancellationType").toString()):0;
		int endorsementType=ctx.get("EndorsementType")!=null && !ctx.get("EndorsementType").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("EndorsementType").toString()):0;
		if(endorsementType==13)
		{
			ctx.put("Date1", new SimpleDateFormat("MM/dd/yyyy").format(new Date(ctx.get("PolicyExpirationDate").toString())));
		}
		else
		{
			ctx.put("Date1", new SimpleDateFormat("MM/dd/yyyy").format(new Date(ctx.get("PolicyEffectiveDate").toString())));
		}
		ctx.put("Date2", new SimpleDateFormat("MM/dd/yyyy").format(new Date(ctx.get("EndorsementEffectiveDate").toString())));
		getDaysDifference(ctx);
		int termDays=ctx.get("TermDays")!=null && !ctx.get("TermDays").equals(HtmlConstants.EMPTY)?Math.round(Integer.parseInt(ctx.get("TermDays").toString())):0;
		int enndorsementDays=ctx.get("EndorsementDays")!=null && !ctx.get("EndorsementDays").equals(HtmlConstants.EMPTY)?Math.round(Integer.parseInt(ctx.get("EndorsementDays").toString())):0;
		
		double premium=ctx.get("TotalPremium")!=null && !ctx.get("TotalPremium").equals(HtmlConstants.EMPTY)?Math.round(Double.parseDouble(ctx.get("TotalPremium").toString())):0;
		double tax=ctx.get("TotalPolicyTaxAmount")!=null && !ctx.get("TotalPolicyTaxAmount").equals(HtmlConstants.EMPTY)?Math.round(Double.parseDouble(ctx.get("TotalPolicyTaxAmount").toString())):0;
		double earnedPremium=(premium/termDays)*enndorsementDays;
		double earnedTax=(tax/termDays)*enndorsementDays;
		double totalEarnedPremium=earnedPremium+earnedTax;
		if(cancellationType==3)
		{
		 double shortRatePercentage=0.90;
		 unearnedPremiumC=(premium- earnedPremium)*shortRatePercentage;
		 unearnedTaxC=(tax- earnedTax)*shortRatePercentage;
		}
		else
		{
			unearnedPremiumC=premium-earnedPremium;
			unearnedTaxC=tax-earnedTax;
		}
		long unearnedPremium=Math.round(unearnedPremiumC);
		long unearnedTax=Math.round(unearnedTaxC);
		long totalOldPremium=Math.round(premium+tax);
		long totalunearnedPremium=unearnedPremium+unearnedTax;
		long premimDifference=totalOldPremium-totalunearnedPremium;
		long result=Math.round((premium+tax)+(earnedPremium+earnedTax));		
		map.put("earnedPremium",Math.round(earnedPremium));
		map.put("earnedTax",Math.round(earnedTax));
		map.put("totalEarnedPremium",Math.round(totalEarnedPremium));
		map.put("unearnedPremium",unearnedPremium);
		map.put("unearnedTax",unearnedTax);
		map.put("totalOldPremium",totalOldPremium);
		map.put("totalunearnedPremium",totalunearnedPremium);
		map.put("premimDifference",premimDifference);
		map.put("isPremiumChange",unearnedPremium!=0?'Y':'N');
		map.put("Premium", premium);
		map.put("Tax", tax);
		map.put("Days", enndorsementDays);
		map.put("EarnedPremium", earnedPremium);
		map.put("EarnedTax", earnedTax);
		map.put("UnearnedPremium", unearnedPremium);
		map.put("UnearnedTax", unearnedTax);
		map.put("EndorsementType",ctx.get("EndorsementType"));
		map.put("ProrataPremium", result);
	}
	catch(Exception e)
	{
		logger.error("Error occured while calculating endorsement uneraned premium for Policy :"+ctx.get("PolicyKey"));
		logger.error("Unexpected error", e);
	}
	return map;
}
@SuppressWarnings("deprecation")
public static Map<String,Object> calculateEndorsementPremium(Context ctx, double endorsementPremium,double endorsementTax)
{
	logger.debug("Calculating endorsement unearned premium for Policy :"+ctx.get("PolicyKey"));
	int enndorsementDays=0;
	int termDays=0;
	HashMap<String,Object> map=new HashMap<String,Object>();
	
	double unearnedPremiumC=0.0,unearnedTaxC=0.0;
	try
	{	
	
		ctx.put("Date1", new SimpleDateFormat("MM/dd/yyyy").format(new Date(ctx.get("PolicyEffectiveDate").toString())));
		ctx.put("Date2", new SimpleDateFormat("MM/dd/yyyy").format(new Date(ctx.get("EndorsementEffectiveDate").toString())));
		getDaysDifference(ctx);
		termDays=ctx.get("TermDays")!=null && !ctx.get("TermDays").equals(HtmlConstants.EMPTY)?Math.round(Integer.parseInt(ctx.get("TermDays").toString())):0;
		enndorsementDays=ctx.get("EndorsementDays")!=null && !ctx.get("EndorsementDays").equals(HtmlConstants.EMPTY)?Math.round(Integer.parseInt(ctx.get("EndorsementDays").toString())):0;
		double premium=ctx.get("TotalPremium")!=null && !ctx.get("TotalPremium").equals(HtmlConstants.EMPTY)?Math.round(Double.parseDouble(ctx.get("TotalPremium").toString())):0;
		double tax=ctx.get("TotalPolicyTaxAmount")!=null && !ctx.get("TotalPolicyTaxAmount").equals(HtmlConstants.EMPTY)?Math.round(Double.parseDouble(ctx.get("TotalPolicyTaxAmount").toString())):0;
		double premiumDifferenceForCalculation=endorsementPremium-premium;
		double taxDifference= endorsementTax-tax;
		double earnedPremium=(premiumDifferenceForCalculation/termDays)*enndorsementDays;
		double earnedTax=(taxDifference/termDays)*enndorsementDays;
		
		double totalEarnedPremium=Math.round(earnedPremium+earnedTax);
		unearnedPremiumC=premiumDifferenceForCalculation-earnedPremium;
		unearnedTaxC=taxDifference-earnedTax;
		long unearnedPremium=Math.round(unearnedPremiumC);
		long unearnedTax=Math.round(unearnedTaxC);
		long totalOldPremium=Math.round(premium+tax);
		long totalunearnedPremium=unearnedPremium+unearnedTax;
		long result=Math.round((premium+tax)+(earnedPremium+earnedTax));		
		map.put("earnedPremium",Math.round(earnedPremium));
		map.put("earnedTax",Math.round(earnedTax));
		map.put("totalEarnedTax",totalEarnedPremium);
		map.put("unearnedPremium",unearnedPremium);
		map.put("unearnedTax",unearnedTax);
		map.put("totalOldPremium",totalOldPremium);
		map.put("totalunearnedPremium",totalunearnedPremium);
		map.put("Premium", premium);
		map.put("isPremiumChange",unearnedPremium!=0?'Y':'N');
		map.put("EndorsementType",ctx.get("EndorsementType"));
		map.put("EndorsementPremium", endorsementPremium);
		map.put("EndorsementTax", endorsementTax);
		map.put("PreviousPremium", premium);
		map.put("PreviousTax", tax);
		map.put("PremiumDifference", premiumDifferenceForCalculation);
		map.put("TaxDifference", taxDifference);
		map.put("Days", enndorsementDays);
		map.put("EarnedPremium", earnedPremium);
		map.put("EarnedTax", earnedTax);
		map.put("UnearnedPremium", unearnedPremium);
		map.put("UnearnedTax", unearnedTax);
		map.put("ProrataPremium", result);
	}
	catch(Exception e)
	{
		logger.error("Error occured while calculating endorsement uneraned premium for Policy :"+ctx.get("PolicyKey"));
		logger.error("Unexpected error", e);
	}
	return map;
}
public static double getEndorsementPremium(Context ctx)
{
	double totalPremium=0.0;
	try
	{
		 	String premium=ctx.get("PremiumVariation")!=null && !ctx.get("PremiumVariation").equals(HtmlConstants.EMPTY)?ctx.get("PremiumVariation").toString():"0";
	        String premiumTax=ctx.get("TaxVariation")!=null && !ctx.get("TaxVariation").equals(HtmlConstants.EMPTY)?ctx.get("TaxVariation").toString():"0";
	        if(premium.contains("-"))
	            premium=premium.replace("-","");
	        if(premiumTax.contains("-"))
	            premiumTax=premiumTax.replace("-","");

	       double premiumValue=Double.parseDouble(premium);
	       double premiumTaxValue=Double.parseDouble(premiumTax);
	        totalPremium=premiumValue+premiumTaxValue;
	        
	    
	}
	catch(Exception e)
	{
		logger.error("Error occured while getting endorsement premium"+e);
	}
	return totalPremium;
}

public static Map<String,Long> setERPPremiumAsPerYears(double totalPremium,double totalPolicyTaxAmount,int amountForYears)
{	
	
	HashMap<String,Long> map=new HashMap<String,Long>();
	logger.debug("Going to calculate ERP premium");
	try
	{
		long premium=Math.round((totalPremium*amountForYears)/100);
		long tax=Math.round((totalPolicyTaxAmount*amountForYears)/100);
		map.put("ERPPremium", premium);
		map.put("ERPTax", tax);
		map.put("ERPTotalPremium", premium+tax);
	}
	catch(Exception e)
	{
		logger.error("Going to calculate ERP premium"+e.getMessage());
		logger.error("Unexpected error", e);
	}
	return map;
}
}
