package com.ipfs;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.datacontract.schemas._2004._07.IPFS_DTO.OSI_CustomLinkSubmitStartEsignResponse;
import org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteCancelDays;
import org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentMethod;
import org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentNoReason;
import org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteDownPaymentPaidBy;
import org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteESignOption;
import org.datacontract.schemas._2004._07.IntegrationSDK.EnumsQuoteInterval;
import org.datacontract.schemas._2004._07.IntegrationSDK_Model.Agent;
import org.datacontract.schemas._2004._07.IntegrationSDK_Model.Communication;
import org.datacontract.schemas._2004._07.IntegrationSDK_Model.Company;
import org.datacontract.schemas._2004._07.IntegrationSDK_Model.Details;
import org.datacontract.schemas._2004._07.IntegrationSDK_Model.ESign;
import org.datacontract.schemas._2004._07.IntegrationSDK_Model.Insured;
import org.datacontract.schemas._2004._07.IntegrationSDK_Model.Policy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ow2.util.base64.Base64;

import com.ipfs.service.IIPFSService;
import com.ipfs.service.IPFSServiceLocator;
import com.ormapping.ibatis.SqlResources;
import com.userbo.LawyersUtils;
import com.userbo.LawyersValidationUtils;
import com.util.Context;
import com.util.InetLogger;
import com.util.SystemProperties;

public class IPFSServiceClient {
	
	private static InetLogger logger = InetLogger.getInetLogger(IPFSServiceClient.class);
	
	public static void main(String[] args) {
//		Context ctx = new Context();
//		getIPFSPFAData(ctx);
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
	
	public static void getIPFSPFAData(Context ctx, boolean manualCheckFlag){
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
			String agentName = (ctx.get("AgentName") == null ? "Protexure Insurance Agency Inc" : ctx.get("AgentName").toString());
			String agentAddress1 = "4200 Commerce Ct. Suite 102";
			String agentAddress2 = "address2";
			String agentCity = "Lisle";
			String agentState = (ctx.get("AgentState") == null ? "IL" : ctx.get("AgentState").toString());
			String agentZip = "60532";
			String agentEmail = (ctx.get("AgentEmail") == null ? "test@test.com" : ctx.get("AgentEmail").toString());
			String agentPhone = "1239874560";
			
			String policyEffectiveDate = (ctx.get("PolicyEffectiveDate") == null ? "08/02/2017" : ctx.get("PolicyEffectiveDate").toString());
			policyEffectiveDate = policyEffectiveDate.substring(0,10);
			policyEffectiveDate = new SimpleDateFormat("MM/dd/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(policyEffectiveDate));
			
			String policyExpirationDate = "";
			
			try {
				if(ctx.get("PolicyEffectiveDate") != null && !"".equals(ctx.get("PolicyEffectiveDate")) && !"SPACE".equals(ctx.get("PolicyEffectiveDate")) && (ctx.get("PolicyExpirationDate") == null || "".equals(ctx.get("PolicyExpirationDate")))){
					Calendar date = Calendar.getInstance();
					Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse(policyEffectiveDate);
				    date.setTime(date1);
				    date.add(Calendar.YEAR,1);
				    SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
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
			
			String companyUniqueid = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.companyuniqueid");
			String companyName = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.companyname");
			String policyCoverage = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.policycoverage");
			String thanksPageUrl = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.thankspage.url");
			String cancelPageUrl = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.cancelpage.url");
			String declinePageUrl = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.declinepage.url");
			String ipfsWSUrl = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.webserviceurl");
			
			logger.debug("Insured data is going to set in request");
			Insured insured = new Insured();
			insured.setUniqueID(insuredUniqueID);
			insured.setName(insuredName);
			insured.setAddress1(insuredAddress1);
			insured.setAddress2(insuredAddress2);
			insured.setCity(insuredCity);
			insured.setState(insuredState);
			insured.setZip(insuredZip);
			insured.setEmail(insuredEmail);
			insured.setPhone(insuredPhone);		    
		
			Agent agent = new Agent();
			agent.setUniqueID(agentUniqueID);
			agent.setName(agentName);
			agent.setAddress1(agentAddress1);
			agent.setAddress2(agentAddress2);
			agent.setCity(agentCity);
			agent.setState(agentState);
			agent.setZip(agentZip);
			agent.setEmail(agentEmail);
			agent.setPhone(agentPhone);
			
			Company company = new Company();
			company.setUniqueID(companyUniqueid);
			company.setName(companyName);
			
			Policy policy = new Policy();
			policy.setCompany(company);
			policy.setCoverage(policyCoverage);
			policy.setPremium(new BigDecimal(policyTotalPremium));
			policy.setMinLiability(new BigDecimal(policyAggregateLimit));
			policy.setMaxLiability(new BigDecimal(policyOccuranceLimit));
			Date date = new SimpleDateFormat("MM/dd/yyyy").parse(policyEffectiveDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, 1);
			policy.setEffectiveDate(cal);//DateTime, 10 characters (format: MM/DD/YYYY)
			date = new SimpleDateFormat("MM/dd/yyyy").parse(policyExpirationDate);
			cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, 1);
			policy.setExpirationDate(cal);//DateTime, 10 characters (format: MM/DD/YYYY)
			policy.setAuditable(false);
			policy.setCancelDays(EnumsQuoteCancelDays.Item0);
			policy.setLossPayeeRequested(false);
		
			Policy[] policies = new Policy[1];
			policies[0] = policy;		
			Details details = new Details();
			if(ctx.get("IPFSFinanceQurMon") != null && "IPFSFinanceQuarterly".equals(ctx.get("IPFSFinanceQurMon"))){
				details.setInterval(EnumsQuoteInterval.Y);
			} else {
				details.setInterval(EnumsQuoteInterval.M);
			}
			details.setReturnPFA(true);			
			
//			details.setESignOpton(EnumsQuoteESignOption.Item3);
//			details.setBatchID(detailsBatchID);

			String policyKey = ""+ctx.get("PolicyKey");
			Communication comm = new Communication();
			//For Manual Check only
			if(manualCheckFlag)
				comm.setInsuredESignCompletionURL(thanksPageUrl +"?PolicyKey="+policyKey+"&User="+ctx.get("User")+"&role_id="+ctx.get("role_id"));
			
			comm.setPaymentPortalCancelRedirectURL(cancelPageUrl +"?PolicyKey="+policyKey+"&User="+ctx.get("User")+"&role_id="+ctx.get("role_id"));
			comm.setPaymentPortalDeclineOfferRedirectURL(declinePageUrl +"?PolicyKey="+policyKey+"&User="+ctx.get("User")+"&role_id="+ctx.get("role_id"));
			comm.setSuppressInsuredESignEmail(true);
			comm.setReturn_InsuredESignURL(true);
			comm.setESignStartPickOptions(true);			
			comm.setESignAutoStart(true);			
			
			logger.debug("IPFS SubmitQuote for Policy  " + policyKey + " is going to call.");
			IPFSServiceLocator serviceLocator = new IPFSServiceLocator();
			IIPFSService ipfsService = serviceLocator.getBasicHttpBinding_IIPFSService(new java.net.URL(ipfsWSUrl));
			String submitQuote = ipfsService.submitQuote(insured, agent, policies, details, comm);
			logger.debug("IPFS SubmitQuote for Policy  " + policyKey + " has been called.");
			
			JSONObject json = new JSONObject(submitQuote);
		    logger.debug("IPFS SubmitQuote for Policy  " + policyKey + " Response..." + json);
		    
			Iterator<String> keys= json.keys();
			while (keys.hasNext()) {
		        String keyValue = (String)keys.next();
		        String valueString = json.getString(keyValue);
		        logger.debug(keyValue + "   :    " + valueString);
		        
		        if("PFA".equals(keyValue)){
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
		        } else if("QuoteNumber".equals(keyValue)){
		        	ctx.put("ipfsQuoteNumberResponse", valueString);		        	
		        } else if(!"Errors".equals(keyValue)){
//		        	System.out.println(keyValue + " : " + valueString);
		        } else if("Errors".equals(keyValue)){
		        	JSONArray jsonArray = new JSONArray(json.get("Errors").toString());
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
		        
//		        if("Response".equals(keyValue)){
//				    logger.debug("Response " + valueString);
//		        	String IPFSInsuredURL = readXML(valueString, "PremiumFinance", "InsuredURL");
//		        	ctx.put("IPFSInsuredURL", IPFSInsuredURL);
//		        	logger.debug("IPFSInsuredURL " + IPFSInsuredURL);
//		        }
			}
			logger.debug("IPFS SubmitQuote Response has been processed");
//			ctx.put("IPFSBatchID", detailsBatchID);
		} catch (RemoteException e) {
			logger.error("Unexpected error", e);
			logger.error("Error in IPFS getIPFSPFAData : " + e.getMessage());
			isError = true;
		} catch (ServiceException e) {
			logger.error("Unexpected error", e);
			logger.error("Error in IPFS getIPFSPFAData : " + e.getMessage());
			isError = true;
		} catch (JSONException e) {
			logger.error("Unexpected error", e);
			logger.error("Error in IPFS getIPFSPFAData : " + e.getMessage());
			isError = true;
		} catch (ParseException e) {
			logger.error("Unexpected error", e);
			logger.error("Error in IPFS getIPFSPFAData : " + e.getMessage());
			isError = true;
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
	
	public static void getIPFSPFAData(Context ctx){
		getIPFSPFAData(ctx, true);
	}
	
	/*public static String readXML(String xml, String rootNode, String nodeKay){
		try{
			InputSource source = new InputSource(new StringReader(xml));
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		    DocumentBuilder db = dbf.newDocumentBuilder();
		    Document document = db.parse(source);
		
		    NodeList flowList = document.getElementsByTagName(rootNode);
		    
		    for (int nodes = 0; nodes < flowList.getLength(); nodes++) {
		    	
		    	NodeList childList = flowList.item(nodes).getChildNodes();
            	
            	for (int innerNodes = 0; innerNodes < childList.getLength(); innerNodes++) {
            		
            		NodeList innerChildList = childList.item(innerNodes).getChildNodes();
            		
            		for (int innerInnerNodes = 0; innerInnerNodes < innerChildList.getLength(); innerInnerNodes++) {

        		    	if("e-SignResult".equals(innerChildList.item(innerInnerNodes).getNodeName())){
        		    		
        		    		NodeList innerInnerChildList = innerChildList.item(innerInnerNodes).getChildNodes();
                    		
                    		for (int innerInnerInnerNodes = 0; innerInnerInnerNodes < innerInnerChildList.getLength(); innerInnerInnerNodes++) {

                		    	if(nodeKay.equals(innerInnerChildList.item(innerInnerInnerNodes).getNodeName())){
                		    		
                		    		String nodeValue = innerInnerChildList.item(innerInnerInnerNodes).getTextContent();
                		    		return nodeValue;
                		    	}
                        	}
        		    	}
                	}
            	}
		    }
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error in IPFS readXML : " + e.getMessage());
		}
		return null;
	}*/
	
	public static void submitIPFSESign(Context ctx){
		try{			
			
			if(ctx.get("IPFSFinanceCCACHMC") != null && "IPFSFinanceCCACH".equals(ctx.get("IPFSFinanceCCACHMC"))){
				getIPFSPFAData(ctx, false);				
			} else if(ctx.get("IPFSFinanceCCACHMC") != null && "IPFSFinanceManualCheck".equals(ctx.get("IPFSFinanceCCACHMC"))){
				getIPFSPFAData(ctx, true);
			}			
			
			String ipfsQuoteNumber = ctx.get("ipfsQuoteNumberResponse") != null ? ctx.get("ipfsQuoteNumberResponse").toString() : "";
			ctx.put("ipfsQuoteNumber",ipfsQuoteNumber);
			
//			String detailsBatchID = ctx.get("IPFSBatchID").toString();
			ESign eSign = new ESign();
			eSign.setESignOpton(EnumsQuoteESignOption.Item4);
			
			if(ctx.get("IPFSFinanceCCACHMC") != null && "IPFSFinanceCCACH".equals(ctx.get("IPFSFinanceCCACHMC"))){
				eSign.setProcessDownPayment(true);
				eSign.setDownPaymentMethod(EnumsQuoteDownPaymentMethod.BOTH);				
			} else if(ctx.get("IPFSFinanceCCACHMC") != null && "IPFSFinanceManualCheck".equals(ctx.get("IPFSFinanceCCACHMC"))){
				eSign.setProcessDownPayment(false);
				eSign.setProcessDownPaymentNoReason(EnumsQuoteDownPaymentNoReason.ManualCheck);
			}
			eSign.setDownPaymentPaidBy(EnumsQuoteDownPaymentPaidBy.Insured);
			
			logger.debug("IPFS StartESign is going to call.");
			String ipfsWSUrl = SystemProperties.getInstance().getString("appl.LawyersIns.ipfs.webserviceurl");
			IPFSServiceLocator serviceLocator = new IPFSServiceLocator();
			IIPFSService ipfsService = serviceLocator.getBasicHttpBinding_IIPFSService(new java.net.URL(ipfsWSUrl));
			
	    	OSI_CustomLinkSubmitStartEsignResponse startESign = ipfsService.startESign(ipfsQuoteNumber, null, null, null, eSign);
	    	logger.debug("IPFS StartESign has been called.");
	    	
	    	/*System.out.println("dd" + startESign.getAgentURL());
	    	System.out.println("dd" + startESign.getInsuredUrl());
	    	System.out.println("dd" + startESign.getRequest());
	    	System.out.println("dd" + startESign.getResponse());
	    	System.out.println("dd" + startESign.getBatchID());
	    	System.out.println("dd" + startESign.getMessage());
	    	System.out.println("dd" + startESign.getSuccess());*/

    		if(startESign.getInsuredUrl() != null && !"".equals(startESign.getInsuredUrl())){
    			
    			int status = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateipfsQuoteNumber", ctx);
    			ctx.remove("ipfsQuoteNumberResponse");   			
	        	logger.debug("Firm has been updated by ipfsQuoteNumber..." + ipfsQuoteNumber);
				ctx.put("TransactionStatus", "Pending");
	        	
	        	logger.debug("Going to redirect to IPFS Insured URL...." + startESign.getInsuredUrl());
    			HttpServletResponse response = (HttpServletResponse) ctx.get("DocumentResponse");
    			response.sendRedirect(startESign.getInsuredUrl());
    		} else {
//	    			new LawyersUtils().populateError(ctx, "insuredError", "Email/Phone do not match. Please contact 1-877-569-4111 for any assistance");
    		}
		} catch (RemoteException e) {
			logger.error("Unexpected error", e);
			logger.error("Error in IPFS submitIPFSESign : " + e.getMessage());
		} catch (ServiceException e) {
			logger.error("Unexpected error", e);
			logger.error("Error in IPFS submitIPFSESign : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Unexpected error", e);
			logger.error("Error in IPFS submitIPFSESign : " + e.getMessage());
		}
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
