package com.userbo;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONObject;
import org.ow2.util.base64.Base64;

import com.ormapping.ibatis.SqlResources;
import com.util.Context;
import com.util.InetLogger;
import com.util.SystemProperties;

public class IPFSServiceClient {
	
	private static InetLogger logger = InetLogger.getInetLogger(IPFSServiceClient.class);
	
	public static void main(String[] args) {
//		Context ctx = new Context();
//		getIPFSPFAData(ctx);
//		try {
//			String webServiceURL = "http://localhost:8080/paymentGatewayIPFS";
//			webServiceURL = "http://www.protexureuat.com/WebhookPG/paymentGatewayIPFS";
//			webServiceURL = "https://portal.protexurelawyers.com/WebhookPG/paymentGatewayIPFS";
//			String subURL = "";
//			
//			String userAgentKey = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";
//			
//			JSONObject messageBodyJsonObject = new JSONObject();
//			messageBodyJsonObject.put("QuoteKey", "24060069");
//			messageBodyJsonObject.put("AccountNumber", "377235");
//			messageBodyJsonObject.put("WebAccessCode", "HR66BGJ");
//			
//			
//			JSONObject authRequest = new JSONObject();
//			authRequest.put("MessageType", "AGREEMENT_BOOKED");
//			authRequest.put("MessageBody", messageBodyJsonObject);
//			
//			JSONObject json = authorizeTransaction(authRequest, webServiceURL,  subURL, userAgentKey);
//			
//			System.out.println(json);
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
		
	}
	
	public static boolean checkIPFSFinanceInterval(Context ctx){
		try{
			if(ctx.get("IPFSFinanceQurMon") == null){
				LawyersUtils.populateError(ctx, "IPFSFinanceQurMon", "Please Select Interval");
				return false;
			}
			logger.debug("IPFSFinanceQurMon : " + ctx.get("IPFSFinanceQurMon"));	
		} catch (Exception e) {
			logger.error("Unexpected error", e);
			logger.error("Error in IPFS checkIPFSFinanceInterval : " + e.getMessage());
		}
		return true;
	}
	
	public static boolean checkIPFSDownPayment(Context ctx){
		try{
			boolean errorFlag = false;
			if(ctx.get("IPFSFinanceCCACHMC") == null){
				LawyersUtils.populateError(ctx, "IPFSFinanceCCACHMC", "Please Select Down Payment Method");
				errorFlag = true;
			}
			if(ctx.get("isIPFSPFA") == null || "".equals(ctx.get("isIPFSPFA"))){
				LawyersUtils.populateError(ctx, "isIPFSPFA", "Please Select Premium Finance Agreement (PFA)");
				errorFlag = true;
			}
			if(errorFlag){
				return false;
			}
		} catch (Exception e) {
			logger.error("Unexpected error", e);
			logger.error("Error in IPFS checkIPFSDownPayment : " + e.getMessage());
		}
		return true;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void getIPFSPFAData(Context ctx){
		boolean isError = false;
		try{
			logger.debug("Starting getIPFSPFAData.....");
					
			List nbPolicyAddressData = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetInsuredData", ctx);
			Map nbPolicyAddressList = (Map)nbPolicyAddressData.get(0);
			ctx.putAll(nbPolicyAddressList);
			
			ctx.put("IsThisOptionFinalisedQuote", "Y");
			List nbPolicyCoverageData = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyCoverageData", ctx);
			Map nbPolicyCoverageList = (Map)nbPolicyCoverageData.get(0);
			ctx.putAll(nbPolicyCoverageList);
			
			String errorMsg = "";
			
			String insuredNameLength = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.insuredNameLength");
			String insuredAddress1Length = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.insuredAddress1Length");
			String insuredAddress2Length = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.insuredAddress2Length");
			String insuredCityLength = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.insuredCityLength");
			
			String insuredUniqueID = "INS-" + ctx.get("AccountID");
			String insuredName = (ctx.get("AccountName") == null ? "AccountName" : ctx.get("AccountName").toString());
			if(insuredName.length() > Integer.parseInt(insuredNameLength)){
				insuredName = insuredName.substring(0, insuredName.substring(0,Integer.parseInt(insuredNameLength)).lastIndexOf(" "));
			}
			String insuredAddress1 = (ctx.get("Address1") == null ? "Address1" : ctx.get("Address1").toString());
			String insuredAddress2 = "";
			if(insuredAddress1.length() > Integer.parseInt(insuredAddress1Length)){
				insuredAddress2 = insuredAddress1.substring(insuredAddress1.substring(0,Integer.parseInt(insuredAddress1Length)).lastIndexOf(" "), insuredAddress1.length());
				insuredAddress1 = insuredAddress1.substring(0, insuredAddress1.substring(0,Integer.parseInt(insuredAddress1Length)).lastIndexOf(" "));
			}
			if(insuredAddress2.isEmpty()){
				insuredAddress2 = (ctx.get("Address2") == null ? "Address2" : ctx.get("Address2").toString());
			}
			if(insuredAddress2.length() > Integer.parseInt(insuredAddress2Length)){
				insuredAddress2 = insuredAddress2.substring(0, insuredAddress2.substring(0,Integer.parseInt(insuredAddress2Length)).lastIndexOf(" "));
			}
			String insuredCity = (ctx.get("City") == null ? "City" : ctx.get("City").toString());
			if(insuredCity.length() > Integer.parseInt(insuredCityLength)){
				insuredCity = insuredCity.substring(0, insuredCity.substring(0,Integer.parseInt(insuredCityLength)).lastIndexOf(" "));
			}
			String insuredState = (ctx.get("StateCode") == null ? "CA" : ctx.get("StateCode").toString());
			String insuredZip = (ctx.get("Zip") == null ? "12345" : ctx.get("Zip").toString() + (ctx.get("Zip4") == null ? "" : ctx.get("Zip4").toString()));
			insuredZip = insuredZip.replace("-", "");
			if(insuredZip.length() != 5 && insuredZip.length() != 9){
				if(!errorMsg.isEmpty()){
					errorMsg += "\n";
				}
				errorMsg += "Zip Code length should be 5 or 9";
			}
			String insuredEmail = (ctx.get("AccountEmail") == null ? "test@outlinesys.com" : ctx.get("AccountEmail").toString());
			if(!LawyersValidationUtils.validateEmail(insuredEmail)){
				if(!errorMsg.isEmpty()){
					errorMsg += "\n";
				}
				errorMsg += "Invalid Email";
			}
			String insuredPhone = (ctx.get("WorkPhone") == null ? "9876543210" : ctx.get("WorkPhone").toString().replaceAll("\\D+",""));
			
			if(!"".equals(errorMsg)){
				LawyersUtils.populateError(ctx, "ipfsErrorsDesc", errorMsg);
	        	ctx.put("IPFSERRORS", "ipfserrors");
				return;
			}
			//Default Value
			//String agentUniqueID = "AGN-" + LawyersUtilities.generateUniqueId() + "-" + LawyersUtilities.generateRandomString();
			String agentUniqueID = "ISMIE";
//			String agentName = (ctx.get("AgentName") == null ? "McGowan Program Administrators dba Protexure Insurance Agency, Inc." : ctx.get("AgentName").toString());
			String agentName = "Protexure Insurance/MPA.";
			String agentCareOf = "";
			String agentAddress1 = "PO Box 773197";
			String agentAddress2 = "";
			String agentCity = "Detroit";
//			String agentState = (ctx.get("AgentState") == null ? "OH" : ctx.get("AgentState").toString());
			String agentState = "MI";
			String agentZip = "48277";
			String agentEmail = (ctx.get("AgentEmail") == null ? "test@test.com" : ctx.get("AgentEmail").toString());
			String agentPhone = "6307992000";
			
			String policyEffectiveDate = (ctx.get("PolicyEffectiveDate") == null ? "08/02/2017" : ctx.get("PolicyEffectiveDate").toString());
			policyEffectiveDate = policyEffectiveDate.substring(0,10);
			
			String policyExpirationDate = "";
			
			try {
				if(ctx.get("PolicyEffectiveDate") != null && !"".equals(ctx.get("PolicyEffectiveDate")) && !"SPACE".equals(ctx.get("PolicyEffectiveDate")) && (ctx.get("PolicyExpirationDate") == null || "".equals(ctx.get("PolicyExpirationDate")))){
					Calendar date = Calendar.getInstance();
					Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(policyEffectiveDate);
				    date.setTime(date1);
				    date.add(Calendar.YEAR,1);
				    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
				    policyExpirationDate = f.format(date.getTime());
				    logger.debug("Get policyExpirationDate " + policyExpirationDate);
				} else {
					 policyExpirationDate = ctx.get("PolicyExpirationDate").toString();
				}
			} catch(Exception e) {
				logger.error("Unexpected error", e);
			}
			
			float policyTotalPremium = Float.parseFloat((ctx.get("InvoicedPremium") == null ? 1999.00 : ctx.get("InvoicedPremium")).toString().replace("$", "").replace(",", ""));
			float policyAggregateLimit = Float.parseFloat((ctx.get("AggregateLimit") == null ? 100000 : ctx.get("AggregateLimit")).toString().replace("$", "").replace(",", ""));
			float policyOccuranceLimit = Float.parseFloat((ctx.get("OccuranceLimit") == null ? 100000 : ctx.get("OccuranceLimit")).toString().replace("$", "").replace(",", ""));
			
//			String detailsBatchID = "BAT-" + generateUniqueId() + "-" + generateRandomString();
			
			String thanksPageUrl = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.thankspage.url");
			String cancelPageUrl = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.cancelpage.url");
			String declinePageUrl = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.declinepage.url");
			
			String webServiceURL = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.webserviceurl");
			String companyUniqueID = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.companyuniqueid");
			String companyName = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.companyname");
			String policyCoverage = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.policycoverage");
			String subURL = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.suburl.quotingpfa");
			String login = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.suburl.login");
			String accessCode = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.suburl.accessCode");
			String systemCode = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.suburl.systemCode");
			String userAgentKey = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";
			
			logger.debug("Insured data is going to set in request");
			
			JSONObject insuredJsonObject = new JSONObject();
			insuredJsonObject.put("uniqueID", insuredUniqueID);
			insuredJsonObject.put("name", insuredName);
			insuredJsonObject.put("address1", insuredAddress1);
			insuredJsonObject.put("address2", insuredAddress2);
			insuredJsonObject.put("city", insuredCity);
			insuredJsonObject.put("state", insuredState);
			insuredJsonObject.put("zip", insuredZip);
			insuredJsonObject.put("email", insuredEmail);
			insuredJsonObject.put("phone", insuredPhone);

			JSONObject agentJsonObject = new JSONObject();
			agentJsonObject.put("uniqueID", agentUniqueID);
			agentJsonObject.put("name", agentName);
//			agentJsonObject.put("careOf", agentCareOf);
			agentJsonObject.put("address1", agentAddress1);
//			agentJsonObject.put("address2", agentAddress2);
			agentJsonObject.put("city", agentCity);
			agentJsonObject.put("state", agentState);
			agentJsonObject.put("zip", agentZip);
			agentJsonObject.put("email", agentEmail);
			agentJsonObject.put("phone", agentPhone);
			
			JSONObject companyJsonObject = new JSONObject();
			companyJsonObject.put("uniqueID", companyUniqueID);
			companyJsonObject.put("name", companyName);
			
			JSONObject policyJsonObject = new JSONObject();
			policyJsonObject.put("company", companyJsonObject);
			policyJsonObject.put("coverage", policyCoverage);
			policyJsonObject.put("premium", new BigDecimal(policyTotalPremium));
			policyJsonObject.put("minLiability", new BigDecimal(policyAggregateLimit));
			policyJsonObject.put("maxLiability", new BigDecimal(policyOccuranceLimit));
			policyJsonObject.put("effectiveDate", policyEffectiveDate);//DateTime, 10 characters (format: yyyy-MM-dd)
			policyJsonObject.put("expirationDate", policyExpirationDate);//DateTime, 10 characters (format: yyyy-MM-dd)
			policyJsonObject.put("auditable", false);
			policyJsonObject.put("cancelDays", "0");
			policyJsonObject.put("lossPayeeRequested", false);
			
			JSONArray policiesJsonArray = new JSONArray();
			policiesJsonArray.put(policyJsonObject);
			
			JSONObject detailsJsonObject = new JSONObject();
			if(ctx.get("IPFSFinanceQurMon") != null && "IPFSFinanceQuarterly".equals(ctx.get("IPFSFinanceQurMon"))){
				detailsJsonObject.put("interval", "Y");
			} else {
				detailsJsonObject.put("interval", "M");
			}
			detailsJsonObject.put("returnPFA", true);
			
			String policyKey = ""+ctx.get("PolicyKey");
			JSONObject commJsonObject = new JSONObject();
			commJsonObject.put("insuredESignCompletionURL", thanksPageUrl +"?PolicyKey="+policyKey+"&User="+ctx.get("User")+"&role_id="+ctx.get("role_id"));
			commJsonObject.put("paymentPortalCancelRedirectURL", cancelPageUrl +"?PolicyKey="+policyKey+"&User="+ctx.get("User")+"&role_id="+ctx.get("role_id"));
			commJsonObject.put("paymentPortalDeclineOfferRedirectURL", declinePageUrl +"?PolicyKey="+policyKey+"&User="+ctx.get("User")+"&role_id="+ctx.get("role_id"));
			commJsonObject.put("suppressInsuredESignEmail", true);
			commJsonObject.put("return_InsuredESignURL", true);
			commJsonObject.put("eSignStartPickOptions", true);
			commJsonObject.put("eSignAutoStart", true);

			JSONObject securityJsonObject = new JSONObject();
			securityJsonObject.put("login", login);
			securityJsonObject.put("accessCode", accessCode);
			
			JSONObject headerJsonObject = new JSONObject();
			headerJsonObject.put("systemCode", systemCode);
			
			JSONObject quoteJsonObject = new JSONObject();
			quoteJsonObject.put("insured", insuredJsonObject);
			quoteJsonObject.put("agent", agentJsonObject);
			quoteJsonObject.put("policies", policiesJsonArray);
			quoteJsonObject.put("details", detailsJsonObject);
			quoteJsonObject.put("comm", commJsonObject);
			
			JSONObject authRequest = new JSONObject();
			authRequest.put("quote", quoteJsonObject);
			authRequest.put("security", securityJsonObject);
			authRequest.put("header", headerJsonObject);
			
			
			logger.debug("IPFS SubmitQuote for Policy  " + policyKey + " is going to call.");
			
			JSONObject json = authorizeTransaction(authRequest, webServiceURL,  subURL, userAgentKey);
			
			logger.debug("IPFS SubmitQuote for Policy  " + policyKey + " has been called.");

			json = json.getJSONObject("item");
		    logger.debug("IPFS SubmitQuote for Policy  " + policyKey + " Response..." + json);
		    
			Iterator<String> keys= json.keys();
			while (keys.hasNext()) {
		        String keyValue = (String)keys.next();
		        String valueString = json.getString(keyValue);
		        logger.debug(keyValue + "   :    " + valueString);
		        
		        if("pfa".equals(keyValue)){
		        	logger.debug("PFA is found from json response");
					//Decode data on other side, by processing encoded data
					try{
			        	byte[] valueDecoded = Base64.decode(valueString.trim().toCharArray());
						logger.debug("PFA data  :    " + valueDecoded);
						String IPFSPFAURL = SystemProperties.getInstance().getString("appl.home.dir") + "data/IPFSPFA_" + policyKey + ".pdf";
			            FileOutputStream fos = new FileOutputStream(IPFSPFAURL);
			            fos.write(valueDecoded);
			            fos.close();
			            ctx.put("IPFSPFAURL", IPFSPFAURL);
			            logger.debug("IPFSPFAURL" + IPFSPFAURL);
					} catch (Exception e) {
						logger.error("Unexpected error", e);
						logger.error("Error in IPFS getIPFSPFAData : " + e.getMessage());
					}
		        } else if("quoteKey".equals(keyValue)){
		        	ctx.put("ipfsQuoteNumberResponse", valueString);		        	
		        } else if("errors".equals(keyValue) && !"null".equals(valueString)){
		        	JSONArray jsonArray = new JSONArray(json.get("errors").toString());
		        	if(jsonArray.length() > 0){	
		        		for(int i=0; i<jsonArray.length(); i++){
		        			JSONObject object = jsonArray.getJSONObject(i);
		        			Iterator<String> errKeys= object.keys();
			    			while (errKeys.hasNext()) {
			    		        String errKey = (String)errKeys.next();
			    		        String errValue = object.getString(errKey);
			    		        logger.debug(errKey + "   :    " + errValue);
			    		        if("Description".equals(errKey)){
			    		        	//LawyersUtils.populateError(ctx, "ipfs", errValue);
//			    		        	ctx.put("ipfsErrorsDesc", "fdfdf");
			    		        	errValue = errValue.substring(50, errValue.indexOf("If you want to contact the IPFS")-1);
			    		        	LawyersUtils.populateError(ctx, "ipfsErrorsDesc", errValue);
			    		        	ctx.put("IPFSERRORS", "ipfserrors");
									return;
			    		        }
			    			}
		        		}
		        	}
				}
		        
			}
			logger.debug("IPFS SubmitQuote Response has been processed");
		} catch (Exception e) {
			logger.error("Unexpected error", e);
			logger.error("Error in IPFS getIPFSPFAData : " + e.getMessage());
			isError = true;
		}
		if(isError){
			try {
				LawyersUtils.populateError(ctx, "ipfsErrorsDesc", "There is some issue in payment integration. Please contact administrator.");
				ctx.put("IPFSERRORS", "ipfserrors");
				return;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				logger.error("Unexpected error", e1);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void submitIPFSESign(Context ctx){
		try{			
			
			String webServiceURL = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.webserviceurl");
			String subURL = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.suburl.startesign");
			String login = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.suburl.login");
			String accessCode = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.suburl.accessCode");
			String systemCode = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.suburl.systemCode");
			String userAgentKey = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";
			
			String ipfsQuoteNumber = ctx.get("ipfsQuoteNumberResponse") != null ? ctx.get("ipfsQuoteNumberResponse").toString() : "";
			ctx.put("ipfsQuoteNumber",ipfsQuoteNumber);
			
			String policyKey = ""+ctx.get("PolicyKey");

			JSONObject authRequest = new JSONObject();
			authRequest.put("quoteKey", ipfsQuoteNumber);

			authRequest.put("eSignOptionNumber", "4");
			
			if(ctx.get("IPFSFinanceCCACHMC") != null && "IPFSFinanceCCACH".equals(ctx.get("IPFSFinanceCCACHMC"))){
				authRequest.put("isProcessDownPayment", true);
				authRequest.put("downPaymentMethod", "BOTH");
			} else if(ctx.get("IPFSFinanceCCACHMC") != null && "IPFSFinanceManualCheck".equals(ctx.get("IPFSFinanceCCACHMC"))){
				authRequest.put("isProcessDownPayment", false);
				authRequest.put("processDownPaymentNoReason", "ManualCheck");
			}
			authRequest.put("downPaymentPaidBy", "Insured");
			
			JSONObject securityJsonObject = new JSONObject();
			securityJsonObject.put("login", login);
			securityJsonObject.put("accessCode", accessCode);
			
			JSONObject headerJsonObject = new JSONObject();
			headerJsonObject.put("systemCode", systemCode);
			
			authRequest.put("security", securityJsonObject);
			authRequest.put("header", headerJsonObject);
			
	    	
	    	logger.debug("IPFS StartESign for Policy  " + policyKey + " is going to call.");
			
			JSONObject json = authorizeTransaction(authRequest, webServiceURL,  subURL, userAgentKey);
			
			logger.debug("IPFS StartESign for Policy  " + policyKey + " has been called.");
		    logger.debug("IPFS StartESign for Policy  " + policyKey + " Response..." + json);
		    
		    if(json.getBoolean("success") == true) {
		    	JSONArray jsonArray = json.getJSONArray("results");
			    json = jsonArray.getJSONObject(0);
				Iterator<String> keys= json.keys();
				while (keys.hasNext()) {
			        String keyValue = (String)keys.next();
			        String valueString = json.getString(keyValue);
			        logger.debug(keyValue + "   :    " + valueString);
			        
			        if("insuredURL".equals(keyValue)){
			        	
		    			int status = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateipfsQuoteNumber", ctx);
		    			ctx.remove("ipfsQuoteNumberResponse");   			
			        	logger.debug("Firm has been updated by status... " + status + "ipfsQuoteNumber..." + ipfsQuoteNumber);
						ctx.put("TransactionStatus", "Pending");
			        	
			        	logger.debug("Going to redirect to IPFS Insured URL...." + valueString);
		    			HttpServletResponse response = (HttpServletResponse) ctx.get("DocumentResponse");
		    			response.sendRedirect(valueString);
			        }
				}
		    }
		} catch (Exception e) {
			logger.error("Unexpected error", e);
			logger.error("Error in IPFS submitIPFSESign : " + e.getMessage());
		}
	}
	
	@SuppressWarnings("deprecation")
	public static JSONObject authorizeTransaction(JSONObject authRequest, String webServiceURL,  String subURL, String userAgentKey) {
		PostMethod post = null; // Execute http request
		try {
    	
		    post = new PostMethod(webServiceURL + subURL);
		    post.setRequestHeader("Content-Type", MediaType.APPLICATION_JSON);
		    post.setRequestHeader("User-Agent", userAgentKey);
		    post.setRequestBody(authRequest.toString());

		    HttpClient httpclient = new HttpClient();

		    int result = httpclient.executeMethod(post);
		    
    		logger.debug(result);
		    logger.debug("IPFS service response received");
		    
		    if (result != 201 && result != 200) {
				return null;
			}
		    
		    if(post.getResponseBodyAsString() != null && !"".equals(post.getResponseBodyAsString())){
		    	return new JSONObject(post.getResponseBodyAsString());
  			}
		} catch (Exception e) {
		    logger.error("Unexpected error", e);
		} finally {
		    post.releaseConnection();
		}
		return null;
	}
	
	public static void downloadIPFSPFA(Context ctx){
    	logger.debug("method in downloadIPFSPFA");
		HttpServletResponse response = (HttpServletResponse) ctx.get("DocumentResponse");
		try{
			String filePath = ctx.get("IPFSPFAURL").toString();
	    	logger.debug("method in downloadIPFSPFA  " + filePath);
			LawyersUtils.exportFileDownLoad(filePath, response);
		}catch(Exception e){
 			logger.error("Unexpected error", e);
			logger.error("Error in IPFS downloadIPFSPFA : " + e.getMessage());
		}
	}
}
