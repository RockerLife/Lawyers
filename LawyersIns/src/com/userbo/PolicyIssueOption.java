package com.userbo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mail.MailSender;
import com.ormapping.ibatis.SqlResources;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.util.Context;
import com.util.InetLogger;
import com.util.SystemProperties;

import sun.misc.BASE64Encoder;

public class PolicyIssueOption {
	
	private static final String SUCCESS_MESSAGE = "SUCCESS";
	private static final String ERROR_MESSAGE = "ERROR : ";	
	private static InetLogger logger = InetLogger.getInetLogger(PolicyIssueOption.class);
	private static String projectName="LawyersIns";
	public  static String DATE_PATTERN = "MM-dd-yyyy hh:mm";
	
	public String receiveIssuePolicy(String backDate, String paymentType){
		
		logger.debug("Going To Issue Policy from the Web Service Call ACH");
		logger.debug("Your context is ---> " + backDate);
		logger.debug("ACH Batch Started....." + new Timestamp(new Date().getTime()));
		logger.debug("project " + projectName + " " + new Timestamp(new Date().getTime()));
		
		if("ACH".equals(paymentType)){
			try {
				Context ctx = new Context();
				ctx.setProject(projectName);
				ctx.put("LastUpdateTimeStamp", new Timestamp(new Date().getTime()));
				setCutoffDate(ctx);
				checkACHStatus(ctx, Integer.parseInt(backDate));
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("Unexpected error", e);
				return ERROR_MESSAGE + e.getMessage();
			}
		}
		return SUCCESS_MESSAGE;
	}
	
	public static void setCutoffDate(Context ctx){
		ctx.put("CutOffDate", LawyersConstants.CUT_OFF_DATE);
		ctx.put("CutOffDateGroup2", LawyersConstants.CUT_OFF_DATE_GROUP2);
		ctx.put("NewAppFlowCutOffDate", LawyersConstants.NEWAPPFLOW_CUT_OFF_DATE);
		ctx.put("CutOffDateGroup3", LawyersConstants.CUT_OFF_DATE_GROUP3);
		ctx.put("CutOffDateGroup4", LawyersConstants.CUT_OFF_DATE_GROUP4);
		ctx.put("CutOffDateGroup5", LawyersConstants.CUT_OFF_DATE_GROUP5);
		ctx.put("CutOffDateGroup6", LawyersConstants.CUT_OFF_DATE_GROUP6);
		ctx.put("CutOffDateGroup7", LawyersConstants.CUT_OFF_DATE_GROUP7);
		ctx.put("CutOffDateGroup8", LawyersConstants.CUT_OFF_DATE_GROUP8);
		ctx.put("CutOffDateCCBSupp", LawyersConstants.CUT_OFF_DATE_CCBSupp);
		ctx.put("CutOffDate2020", LawyersConstants.CUT_OFF_DATE_2020);
	}
	
	public static String checkACHStatus(Context ctx, int backDate){
		try{
			String out = SUCCESS_MESSAGE;
			ArrayList approvedStatusList = new ArrayList();
			ArrayList processingStatusList = new ArrayList();
			ArrayList manualApprovedStatusList = new ArrayList();
			ArrayList declinedStatusList = new ArrayList();
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, backDate);
			String backTransactionDate = new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());			
			ctx.put("BackTransactionDate", backTransactionDate);
			
			List getPaymentDetailsGreaterThanThreeList = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetACHPaymentDetailGreaterThanThree", ctx);
    		
			if(getPaymentDetailsGreaterThanThreeList != null && getPaymentDetailsGreaterThanThreeList.size() > 0 
					&& !getPaymentDetailsGreaterThanThreeList.isEmpty()){
    			logger.debug("Policy Count...." + getPaymentDetailsGreaterThanThreeList.size());
    			
    			System.setProperty("javax.net.ssl.trustStore", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStore"));
    			System.setProperty("javax.net.ssl.keyStorePassword", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStorePassword"));
    			System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
    			
    			final String webServiceURI = SystemProperties.getInstance().getString("appl.LawyersIns.ach.checkstatus.webserviceurl");
    			final String username = SystemProperties.getInstance().getString("appl.LawyersIns.ach.username");
    			final String password = SystemProperties.getInstance().getString("appl.LawyersIns.ach.password");
    			final String merchid = SystemProperties.getInstance().getString("appl.LawyersIns.ach.checkstatus.merchid");   			
    			
//				String webServiceURI = "https://fts.cardconnect.com:6443/cardconnect/rest/funding";
//				String username = "testing";
//				String password = "testing123";
//				String merchid = "542041";
    			
    			//Creating map having multiple payment done on particular transaction date
    			Map achDetailObj = new HashMap<String, ArrayList>();
    			
    			for(int i = 0; i < getPaymentDetailsGreaterThanThreeList.size(); i++){
		        	Map getPaymentDetailsObj = (Map)getPaymentDetailsGreaterThanThreeList.get(i);		        	
		        	String getPaymentTransactionDate = (getPaymentDetailsObj.get("TransactionDate") == null ? "" : getPaymentDetailsObj.get("TransactionDate").toString());
		        	logger.debug("TransactionDate....." + getPaymentTransactionDate);
		        	
		        	Date date = new SimpleDateFormat("MM/dd/yyyy").parse(getPaymentTransactionDate); // your date
	        		// Choose time zone in which you want to interpret your Date
		        	
		        	Date date2 = new Date();
				    long diff = date2.getTime() - date.getTime();
				    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		        	
		        	for(int j=0; j<days; j++){
		        		Calendar cal1 = Calendar.getInstance();
			        	logger.debug("Cal Date....." + cal1.getTime());
		        		cal1.setTime(date);
		        		cal1.add(Calendar.DATE, j+1);
		        		
		                int year = cal1.get(Calendar.YEAR);
		                int month = cal1.get(Calendar.MONTH);
		                int day = cal1.get(Calendar.DAY_OF_MONTH);
		                String submitedDate = (String.format("%02d", month+1)) + String.format("%02d", day) + (char)(65+i);
		                logger.debug("SubmitedDate " + submitedDate);
		                
		                if(i == 0 || !achDetailObj.containsKey(submitedDate)){
		                	ArrayList achDetailList = new ArrayList();
			                achDetailList.add(getPaymentDetailsObj);
			        		achDetailObj.put(submitedDate, achDetailList);
			        	} else if(achDetailObj.containsKey(submitedDate)){
			        		ArrayList oldACHDetailList = (ArrayList)achDetailObj.get(submitedDate);
			        		oldACHDetailList.add(getPaymentDetailsObj);
			        		achDetailObj.put(submitedDate, oldACHDetailList);
			        	}
		        	}
    			}
    			
    			Set keySet = achDetailObj.keySet();
	        	Iterator keys = keySet.iterator();
	        	while (keys.hasNext()) {
			        String keyValue = (String)keys.next();
			        ArrayList getACHDetailList = (ArrayList)achDetailObj.get(keyValue);
	    	        
					String authRequestURL = webServiceURI + "?merchid=" + merchid;
					authRequestURL += "&date=" + keyValue.substring(0,4);
					
					logger.debug("Going to call web service URL with date parameter...." + authRequestURL);			
					JSONObject authResponse = authorizeTransaction(authRequestURL,username,password);
					logger.debug("Web service has been called.");
					
			        if(authResponse.has("txns")){
			        	JSONArray jsonArray = new JSONArray(authResponse.get("txns").toString());
			        	if(jsonArray.length() > 0){
			        		for(int i = 0 ; i < getACHDetailList.size(); i++){		    		        	
		    		        	Map getPaymentDetailsObj = (Map)getACHDetailList.get(i);
		    	    			String getPaymentPolicyKey = (getPaymentDetailsObj.get("PolicyKey") == null ? "" : getPaymentDetailsObj.get("PolicyKey").toString());
		    	    			String getPaymentTransactionSequence = (getPaymentDetailsObj.get("TransactionSequence") == null ? "" : getPaymentDetailsObj.get("TransactionSequence").toString());
		    	    			String getPaymentDetail = (getPaymentDetailsObj.get("Detail") == null ? "" : getPaymentDetailsObj.get("Detail").toString());
		    	    			String getPaymentAmount = (getPaymentDetailsObj.get("Amount") == null ? "" : getPaymentDetailsObj.get("Amount").toString());
		    	    			String getPaymentTransactionID = (getPaymentDetailsObj.get("TransactionID") == null ? "" : getPaymentDetailsObj.get("TransactionID").toString());
		    	    			String getPaymentTransactionDate = (getPaymentDetailsObj.get("TransactionDate") == null ? "" : getPaymentDetailsObj.get("TransactionDate").toString());
		    	    			String getPaymentTransactionStatus = (getPaymentDetailsObj.get("TransactionStatus") == null ? "" : getPaymentDetailsObj.get("TransactionStatus").toString());
		    	    			String getPaymentPaymentMode = (getPaymentDetailsObj.get("PaymentMode") == null ? "" : getPaymentDetailsObj.get("PaymentMode").toString());
		    	    			String getLastUpdateUserID = (getPaymentDetailsObj.get("LastUpdateUserID") == null ? "" : getPaymentDetailsObj.get("LastUpdateUserID").toString());
		    	    			String quoteNumber = (getPaymentDetailsObj.get("QuoteNumber") == null ? "" : getPaymentDetailsObj.get("QuoteNumber").toString());
		    	    			String accountName = (getPaymentDetailsObj.get("AccountName") == null ? "" : getPaymentDetailsObj.get("AccountName").toString());
		    	    			
				        		for(int j=0; j<jsonArray.length(); j++){
				        			JSONObject object = jsonArray.getJSONObject(j);
				    		        String retrefCode = (object.isNull("retref") ? "" : object.getString("retref"));
				    		        
				    		        if(!"".equals(retrefCode)){	
				    		        	
				    		        	logger.debug("retref :    " + retrefCode);
				    	    			if(retrefCode.equals(getPaymentTransactionID)){
				    	    				String statusValue = (object.isNull("status") ? "" : object.getString("status"));
						    		        String achreturnCode = (object.isNull("achreturncode") ? "" : object.getString("achreturncode"));
						    		        logger.debug("Status    :    " + statusValue);
						    		        logger.debug("AchreturnCode   :    " + achreturnCode);
						    		        
				    	    				logger.debug("Match retref Code " + getPaymentTransactionID + "  "+ new Timestamp(new Date().getTime()));
				    	    				logger.debug("Match policy " + quoteNumber + "  "+ new Timestamp(new Date().getTime()));
				    	    				
				    	    				ctx.put("TransactionID", getPaymentTransactionID);
				    	    				ctx.put("Amount", getPaymentAmount);
				    	    				ctx.put("PaymentMode", getPaymentPaymentMode);
				    	    				ctx.put("TransactionDate", getPaymentTransactionDate);
				    	    				ctx.put("PolicyKey", getPaymentPolicyKey);
				    	    				ctx.put("TransactionSequence", getPaymentTransactionSequence);
				    	    				ctx.put("LastUpdateUserID", getLastUpdateUserID);
				    	    				
				    	    				//Going to issue policy
				    	    				if("".equals(achreturnCode) || achreturnCode == null){
					    	    				ctx.put("TransactionStatus", "Approved");
					    	    				ctx.put("Detail", "This transaction is successfully completed");
					    	    				
						    					List list = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetFirmDataFromACH", ctx);
						    					if(list != null && list.size() == 1){
						    						logger.debug("Got Firm data from ach " + new Timestamp(new Date().getTime()));
						    						Map firmMap = (Map)list.get(0);
						    						ctx.putAll(firmMap);
						    					
						    						
						    						DocumentManagment mgt = new DocumentManagment();
						    						out = mgt.processDocumentManagment(ctx);
						    						if(!SUCCESS_MESSAGE.equals(out)){
						    							logger.debug("Problem in DocumentManagment...." + out);
						    							return ERROR_MESSAGE + out;
						    						} else {
						    							logger.debug("Policy " + quoteNumber + "  " + " is successfully issued " + new Timestamp(new Date().getTime()));
						    						}
							    					
							    					SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdatePaymentDetailPayment", ctx);
							    					SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriespolicyLastUpdateTimeStamp", ctx);
							    					logger.debug("PaymentDetail " + quoteNumber + "is updated with status approved " + new Timestamp(new Date().getTime()));
						    					}
						    					Map approvedStatusObj = new HashMap<String, ArrayList>();
						    					approvedStatusObj.put("QuoteNumber", quoteNumber);
						    					approvedStatusObj.put("AccountName", accountName);
						    					approvedStatusList.add(approvedStatusObj);
				    	    				} else if(!"".equals(achreturnCode) && achreturnCode != null){
				    	    					Map processedStatusObj = new HashMap<String, ArrayList>();
				    	    					processedStatusObj.put("QuoteNumber", quoteNumber);
				    	    					processedStatusObj.put("AccountName", accountName);
				    	    					processingStatusList.add(processedStatusObj);
									        } 
				    	    				break;
				    	    			}
				    	    		}
						        }
			        		}
			        	}
			        }
	        	}
			}
			
    		//Policies could not completed in certain time period
			List getPaymentDetailsLessThanThreeList = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetACHPaymentDetailLessThanThree", ctx);
    		if(getPaymentDetailsLessThanThreeList != null && getPaymentDetailsLessThanThreeList.size() > 0 
    				&& !getPaymentDetailsLessThanThreeList.isEmpty()){
    			logger.debug("Policies count " + getPaymentDetailsLessThanThreeList.size());
    			
    			for(int k = 0 ; k < getPaymentDetailsLessThanThreeList.size(); k++){
	    			Map getPaymentDetailsObj = (Map)getPaymentDetailsLessThanThreeList.get(k);
	    			String getPaymentPolicyKey = (getPaymentDetailsObj.get("PolicyKey") == null ? "" : getPaymentDetailsObj.get("PolicyKey").toString());
	    			String getPaymentTransactionSequence = (getPaymentDetailsObj.get("TransactionSequence") == null ? "" : getPaymentDetailsObj.get("TransactionSequence").toString());
	    			String getPaymentDetail = (getPaymentDetailsObj.get("Detail") == null ? "" : getPaymentDetailsObj.get("Detail").toString());
	    			String getPaymentAmount = (getPaymentDetailsObj.get("Amount") == null ? "" : getPaymentDetailsObj.get("Amount").toString());
	    			String getPaymentTransactionID = (getPaymentDetailsObj.get("TransactionID") == null ? "" : getPaymentDetailsObj.get("TransactionID").toString());
	    			String getPaymentTransactionDate = (getPaymentDetailsObj.get("TransactionDate") == null ? "" : getPaymentDetailsObj.get("TransactionDate").toString());
	    			String getPaymentTransactionStatus = (getPaymentDetailsObj.get("TransactionStatus") == null ? "" : getPaymentDetailsObj.get("TransactionStatus").toString());
	    			String getPaymentPaymentMode = (getPaymentDetailsObj.get("PaymentMode") == null ? "" : getPaymentDetailsObj.get("PaymentMode").toString());
	    			String getLastUpdateUserID = (getPaymentDetailsObj.get("LastUpdateUserID") == null ? "" : getPaymentDetailsObj.get("LastUpdateUserID").toString());
	    			String quoteNumber = (getPaymentDetailsObj.get("QuoteNumber") == null ? "" : getPaymentDetailsObj.get("QuoteNumber").toString());
	    			String accountName = (getPaymentDetailsObj.get("AccountName") == null ? "" : getPaymentDetailsObj.get("AccountName").toString());
					
	    			ctx.put("TransactionID", getPaymentTransactionID);
					ctx.put("Amount", getPaymentAmount);
					ctx.put("PaymentMode", getPaymentPaymentMode);
					ctx.put("TransactionDate", getPaymentTransactionDate);
					ctx.put("PolicyKey", getPaymentPolicyKey);
					ctx.put("TransactionSequence", getPaymentTransactionSequence);
					ctx.put("LastUpdateUserID", getLastUpdateUserID);
					ctx.put("TransactionStatus", "Declined");
					ctx.put("Detail", "This transaction is declined");
					Map declinedStatusObj = new HashMap<String, ArrayList>();
					declinedStatusObj.put("QuoteNumber", quoteNumber);
					declinedStatusObj.put("AccountName", accountName);
					declinedStatusList.add(declinedStatusObj);
					
					logger.debug("Policy " + quoteNumber + " is declined " + new Timestamp(new Date().getTime()));
					SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdatePaymentDetailPayment", ctx);
					SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriespolicyLastUpdateTimeStamp", ctx);
					logger.debug("PaymentDetail " + quoteNumber + " is updated with status Declined " + new Timestamp(new Date().getTime()));
    			}
    		}
    		
    		//To issue policy manually 
    		List getPaymentDetailsManualApprovedList = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetACHPaymentDetailManual", ctx);
    		
			if(getPaymentDetailsManualApprovedList != null && getPaymentDetailsManualApprovedList.size() > 0 
					&& !getPaymentDetailsManualApprovedList.isEmpty()){
				logger.debug("Policies count " + getPaymentDetailsManualApprovedList.size());
    			
    			for(int k = 0 ; k < getPaymentDetailsManualApprovedList.size(); k++){
	    			Map getPaymentDetailsObj = (Map)getPaymentDetailsManualApprovedList.get(k);
	    			String getPaymentPolicyKey = (getPaymentDetailsObj.get("PolicyKey") == null ? "" : getPaymentDetailsObj.get("PolicyKey").toString());
	    			String getPaymentTransactionSequence = (getPaymentDetailsObj.get("TransactionSequence") == null ? "" : getPaymentDetailsObj.get("TransactionSequence").toString());
	    			String getPaymentDetail = (getPaymentDetailsObj.get("Detail") == null ? "" : getPaymentDetailsObj.get("Detail").toString());
	    			String getPaymentAmount = (getPaymentDetailsObj.get("Amount") == null ? "" : getPaymentDetailsObj.get("Amount").toString());
	    			String getPaymentTransactionID = (getPaymentDetailsObj.get("TransactionID") == null ? "" : getPaymentDetailsObj.get("TransactionID").toString());
	    			String getPaymentTransactionDate = (getPaymentDetailsObj.get("TransactionDate") == null ? "" : getPaymentDetailsObj.get("TransactionDate").toString());
	    			String getPaymentTransactionStatus = (getPaymentDetailsObj.get("TransactionStatus") == null ? "" : getPaymentDetailsObj.get("TransactionStatus").toString());
	    			String getPaymentPaymentMode = (getPaymentDetailsObj.get("PaymentMode") == null ? "" : getPaymentDetailsObj.get("PaymentMode").toString());
	    			String getLastUpdateUserID = (getPaymentDetailsObj.get("LastUpdateUserID") == null ? "" : getPaymentDetailsObj.get("LastUpdateUserID").toString());
	    			String quoteNumber = (getPaymentDetailsObj.get("QuoteNumber") == null ? "" : getPaymentDetailsObj.get("QuoteNumber").toString());
	    			String accountName = (getPaymentDetailsObj.get("AccountName") == null ? "" : getPaymentDetailsObj.get("AccountName").toString());
					
	    			ctx.put("PolicyKey", getPaymentPolicyKey);
					ctx.put("TransactionSequence", getPaymentTransactionSequence);
					
					Map manualApprovedStatusObj = new HashMap<String, ArrayList>();
					manualApprovedStatusObj.put("QuoteNumber", quoteNumber);
					manualApprovedStatusObj.put("AccountName", accountName);
					manualApprovedStatusList.add(manualApprovedStatusObj);
					
					List list = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetFirmDataFromACH", ctx);
					if(list != null && list.size() == 1){
						logger.debug("Got Firm data from ach " + new Timestamp(new Date().getTime()));
						Map firmMap = (Map)list.get(0);
						ctx.putAll(firmMap);
					
    					DocumentManagment mgt = new DocumentManagment();
    					out = mgt.processDocumentManagment(ctx);
						if(!SUCCESS_MESSAGE.equals(out)){
							logger.debug("Problem in DocumentManagment...." + out);
							return ERROR_MESSAGE + out;
						} else {
							logger.debug("Policy " + quoteNumber + "  " + " is successfully issued " + new Timestamp(new Date().getTime()));
						}
    					
    					ctx.put("TransactionID", getPaymentTransactionID);
    					ctx.put("Amount", getPaymentAmount);
    					ctx.put("PaymentMode", getPaymentPaymentMode);
    					ctx.put("TransactionDate", getPaymentTransactionDate);
    					ctx.put("LastUpdateUserID", getLastUpdateUserID);
    					ctx.put("TransactionStatus", getPaymentTransactionStatus);
    					ctx.put("Detail", "This transaction is completed manually");
    					
    					SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdatePaymentDetailPayment", ctx);
    					SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriespolicyLastUpdateTimeStamp", ctx);
    					logger.debug("PaymentDetail " + quoteNumber + "is updated with status Manual " + new Timestamp(new Date().getTime()));
					}
    			}
			}
			if(SUCCESS_MESSAGE.equals(out)){
				sendStatusMail(approvedStatusList, processingStatusList, manualApprovedStatusList, declinedStatusList);
			}
		}catch(Exception e){
			logger.error("Unexpected error", e);
		}
		return SUCCESS_MESSAGE;
	}
	
	public static JSONObject authorizeTransaction(String webServiceURI, String username, String password) {
		try {
			ClientConfig clientConfig = new DefaultClientConfig();

			Client client = Client.create(clientConfig);

			WebResource webResource = client.resource(webServiceURI);

			String authString = username + ":" + password;
			
			String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
			
			ClientResponse response = webResource
					.accept(MediaType.APPLICATION_JSON)
					.type(MediaType.APPLICATION_JSON)
					.header("Authorization", "Basic " + authStringEnc)
					.get(ClientResponse.class);
			
			if (response.getStatus() != 200) {
				return null;
			}
			
			String result = response.getEntity(String.class);
			logger.debug(result);			
			return new JSONObject(result);
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
		return null;
	}
	
	public static void sendStatusMail(ArrayList approvedStatus, ArrayList processingStatus, ArrayList manualApprovedStatus, ArrayList declinedStatus){
		try{
			MailSender mailSender = new MailSender();				
			mailSender.setToAdd(SystemProperties.getInstance().getString("appl.LawyersIns.ach.mail.to.address"));
			mailSender.setCcAdd(SystemProperties.getInstance().getString("appl.LawyersIns.ach.mail.cc.address"));
			mailSender.setContentType("text/html");			
			mailSender.setSubject("ACH Status Email");
			mailSender.setBody(generatePolicyIssueBody(approvedStatus,processingStatus, manualApprovedStatus, declinedStatus));
			mailSender.sendMail();
		}catch(Exception e){
			logger.error("Unexpected error", e);
		}
	}
	
	private static String generatePolicyIssueBody(ArrayList approvedStatus, ArrayList processingStatus, ArrayList manualApprovedStatus, ArrayList declinedStatus) throws Exception
    {
		StringBuilder msg = new StringBuilder(1024);
		msg.append("<table><tr><td>");
		msg.append("ACH transactions has been processed on date ")
				.append(new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(new Timestamp(new Date().getTime())))
				.append("<br/>");
		msg.append("Please find status below<br/><br/>");
		
		if(approvedStatus != null && approvedStatus.size() > 0 && !approvedStatus.isEmpty()){
			msg.append("Approved Policies : <br/>");
			msg.append(getStatusTable(approvedStatus)).append("<br/>");
		} else {
			msg.append("<br/>");
		}
		if(processingStatus != null && processingStatus.size() > 0 && !processingStatus.isEmpty()){
			msg.append("Under Processing Policies : <br/>");
			msg.append(getStatusTable(processingStatus)).append("<br/>");
		} else {
			msg.append("<br/>");
		}
		if(manualApprovedStatus != null && manualApprovedStatus.size() > 0 && !manualApprovedStatus.isEmpty()){
			msg.append("Manually Approved Policies : <br/>");
			msg.append(getStatusTable(manualApprovedStatus)).append("<br/>");
		}
		if(declinedStatus != null && declinedStatus.size() > 0 && !declinedStatus.isEmpty()){
			msg.append("Declined Policies : <br/>");
			msg.append(getStatusTable(declinedStatus)).append("<br/>");
		} else {
			msg.append("<br/>");
		}
		 
		msg.append("Best Regards,<br/><br/>");
        msg.append("<b>Protexure Lawyers</b><br/>");
        msg.append("<b>T:  877-569-4111  ext 1</b><br/>");
        msg.append("<b>F:  (440) 333-3214</b><br/>");
        msg.append("<b>www.protexurelawyers.com</b><br/><br/>");
		msg.append("</td></tr></table>");
		
		return msg.toString();
    }
	
	public static String getStatusTable(ArrayList approvedStatus){
		StringBuilder msg = new StringBuilder(512);
		if(approvedStatus != null && approvedStatus.size() > 0 && !approvedStatus.isEmpty()){
			msg.append("<table border=\"1\"><tr><td>QuoteNumber</td><td>Account Name</td></tr>");
			for(int paymentDetailCount = 0 ; paymentDetailCount < approvedStatus.size(); paymentDetailCount++){

	        	Map approvedStatusObj = (Map)approvedStatus.get(paymentDetailCount);
	        	String quoteNumber = (approvedStatusObj.get("QuoteNumber") == null ? "" : approvedStatusObj.get("QuoteNumber").toString());
	        	String accountName = (approvedStatusObj.get("AccountName") == null ? "" : approvedStatusObj.get("AccountName").toString());
	        	
		        msg.append("<tr><td>").append(quoteNumber).append("</td><td>").append(accountName).append("</td></tr>");
	        }
			msg.append("</table>");
		}
		return msg.toString();
	}
}
