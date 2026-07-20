package com.userbo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONObject;

import com.util.Context;
import com.util.HtmlConstants;
import com.util.InetLogger;
import com.util.SystemProperties;

public class CreatePaymentIntent {
	private static InetLogger logger = InetLogger.getInetLogger(CreatePaymentIntent.class);
	private static final String SUCCESS_MESSAGE = "SUCCESS";
	private static final String ERROR_MESSAGE = "ERROR";
	private static final String DOAGAIN_MESSAGE = "DOAGAIN";

    public static void main(String[]a) {
    	getAndDoneToken(null);
    }
    
    public static void getAndDoneToken(Context ctx) {
		logger.debug("Starting getAndDoneToken.....");

		try {
			System.setProperty("javax.net.ssl.trustStore", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStore"));
			System.setProperty("javax.net.ssl.keyStorePassword", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStorePassword"));
			System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
			
			final String webServiceURL = SystemProperties.getInstance().getString("appl.LawyersIns.anddone.webserviceurl");
			final String paymentintent = SystemProperties.getInstance().getString("appl.LawyersIns.anddone.suburl.paymentintent");
			final String apiKey = SystemProperties.getInstance().getString("appl.LawyersIns.anddone.apikey");
			final String appKey = SystemProperties.getInstance().getString("appl.LawyersIns.anddone.appkey");
			final String crossOriginURL = SystemProperties.getInstance().getString("appl.LawyersIns.anddone.crossorigin.url");
			final String userAgentKey = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";
			
			float amount = Float.parseFloat((ctx.get("InvoicedPremium") == null ? 1999.00 : ctx.get("InvoicedPremium")).toString().replace("$", "").replace(",", ""));
			String accname = (ctx.get("AccountName") != null && !ctx.get("AccountName").equals(HtmlConstants.EMPTY) ? ctx.get("AccountName").toString(): "Test");
			String address = (ctx.get("Address1") == null ? "Address1" : ctx.get("Address1").toString());
			String city = (ctx.get("City") == null ? "City" : ctx.get("City").toString());
			String region = (ctx.get("StateCode") == null ? "CA" : ctx.get("StateCode").toString());
			String postal = ((ctx.get("BillingZipCode") == null || "".equals(ctx.get("BillingZipCode"))) ? "12345" : ctx.get("BillingZipCode").toString());
			
			String environmentVar  = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment");
			
			if(environmentVar.equals("QA")|| environmentVar.equals("UAT"))
			{
				amount = 1;
			}
			
			String orderid = LawyersUtilities.generateUniqueId();
			String subURL = "";

			String[] paymentTypes = null;
			
			if("paypal".equals(ctx.get("PaymentMode"))) {
				paymentTypes = new String[2];
				paymentTypes[0]= "DebitCard";
				paymentTypes[1]= "CreditCard";
			} else if("ach".equals(ctx.get("PaymentMode"))) {
				paymentTypes = new String[1];
				paymentTypes[0]= "ACH";
			}
			
			//  payment type

	    	JSONObject authRequest = new JSONObject();
			// Merchant ID
			authRequest.put("expiresIn", "3000");
			// Card Type
			authRequest.put("amount", amount);//amount
			// Card Number
			authRequest.put("title", "Title_" + orderid);
			// Card Expiry
			
			// Create custom field
			JSONObject intent = new JSONObject();	       
			intent.put("paymentTypes", paymentTypes);
			
			authRequest.put("intent", intent);
			
			logger.debug("Submitting AndDone authorization request");
			logger.debug("Going to call AndDone WS.");
			subURL = paymentintent;
			JSONObject authResponse = authorizeTransaction(authRequest, webServiceURL, subURL, apiKey, appKey, userAgentKey, crossOriginURL);
			logger.debug("AndDone WS has been called.");
			
			if(authResponse == null){
				LawyersUtils.populateError(ctx, "authorizationFailed", "PaymentIntent Authorization failed");
				return;
			}
			logger.debug("AndDone authorization response received");
			
			if(!authResponse.isNull("paymentToken")){
				String paymentToken = (String) authResponse.get("paymentToken");
				logger.debug("AndDone payment token received");
				ctx.put("paymentToken", paymentToken);
				ctx.put("isvalidid", paymentToken);
				ctx.put("Amount", amount);
				
				HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
		        HttpSession sess = req.getSession();
		        sess.setAttribute("paymentToken", paymentToken);
			}
			
			logger.debug("AndDone payment has been processed.");
		} catch (Exception e) {
		    logger.error("Unexpected error", e);
		}

    }
    
    public String getAndDoneResponse(HttpSession session) {
		logger.debug("Starting getAndDoneResponse.....");
		
		String out = "";

		try {
			System.setProperty("javax.net.ssl.trustStore", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStore"));
			System.setProperty("javax.net.ssl.keyStorePassword", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStorePassword"));
			System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
			
			final String webServiceURL = SystemProperties.getInstance().getString("appl.LawyersIns.anddone.webserviceurl");
			final String paymentDetails = SystemProperties.getInstance().getString("appl.LawyersIns.anddone.suburl.paymentdetails");
			final String apiKey = SystemProperties.getInstance().getString("appl.LawyersIns.anddone.apikey");
			final String appKey = SystemProperties.getInstance().getString("appl.LawyersIns.anddone.appkey");
			final String crossOriginURL = SystemProperties.getInstance().getString("appl.LawyersIns.anddone.crossorigin.url");
			final String userAgentKey = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";
			
			String subURL = "";
			
			String paymentToken =  (String) session.getAttribute("paymentToken");
			
			if(!"".equals(paymentToken)) {
				
				JSONObject paymentDetailsRequest = new JSONObject();
				// paymentDetails ID
				paymentDetailsRequest.put("Type", "PaymentIntent");
				// Transaction amount
				paymentDetailsRequest.put("PaymentToken", paymentToken);
				
				
				logger.debug("Requesting AndDone payment details");
				subURL = paymentDetails;
	        	JSONObject paymentDetailsResponse = authorizeTransaction(paymentDetailsRequest, webServiceURL, subURL, apiKey, appKey, userAgentKey, crossOriginURL);
	        	
	        	if(paymentDetailsResponse == null){
	        		out = DOAGAIN_MESSAGE;
				}
	        	
	        	if(paymentDetailsResponse != null && !paymentDetailsResponse.isNull("transactionStatus")){
					String transactionStatus = (String) paymentDetailsResponse.get("transactionStatus");
					if("Authorized".equals(transactionStatus) || "Posted".equals(transactionStatus)) {
						out = SUCCESS_MESSAGE;
					} else if("Failed".equals(transactionStatus)) {
						if(!paymentDetailsResponse.isNull("transactionResult")){
							JSONObject paymentDetailsResponseSub = (JSONObject) paymentDetailsResponse.get("transactionResult");
							String additionResultData = (String) paymentDetailsResponseSub.get("additionResultData");
							if("Invalid Card Number".equals(additionResultData)) {
								out = DOAGAIN_MESSAGE;
							} else {
								out = ERROR_MESSAGE;
							}
						}
					}
				}
	        	session.setAttribute("transactionStatus", out);
	        	logger.debug("AndDone payment status: " + out);
			}
			
			logger.debug("AndDone payment has been processed.");
		} catch (Exception e) {
		    logger.error("Unexpected error", e);
		}
		return out;

    }
	
	public static JSONObject authorizeTransaction(JSONObject authRequest, String webServiceURL,  String subURL, String apiKey, String appKey, String userAgentKey, String crossOriginURL) {
		PostMethod post = null; // Execute http request
		try {
    	
		    post = new PostMethod(webServiceURL + subURL);
		    post.setRequestHeader("x-api-key", apiKey);
		    post.setRequestHeader("x-app-key", appKey);
		    post.setRequestHeader("Origin", crossOriginURL);
		    post.setRequestHeader("Content-Type", MediaType.APPLICATION_JSON);
		    post.setRequestHeader("User-Agent", userAgentKey);
		    post.setRequestBody(authRequest.toString());

		    HttpClient httpclient = new HttpClient();

		    int result = httpclient.executeMethod(post);
		    
	    	logger.debug("AndDone HTTP status: " + result);
		    
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
	
	public static void andDonePaymentSuccess(Context ctx) {
		try{
			logger.debug("Going to process CC payment response");
			
			if(SUCCESS_MESSAGE.equals(ctx.get("transactionStatus"))){
				ctx.put("TransactionStatus", "Approved");				
				ctx.put("Detail", "This transaction has been approved.");
			
				if(ctx.get("StatusKey") != null) {
					if("6".equals(ctx.get("StatusKey").toString())){
						logger.debug("Policy has been issued already");	
						return;
					} else {						
						logger.debug("Policy is not issued ... Generating Document and PolicyNumber");
						new DocumentManagment().processDocumentManagment(ctx);
					}								
				}
			} else {
				ctx.put("TransactionStatus", "Declined");				
				ctx.put("Detail", "This transaction has been declined.");
			}
			logger.debug("Processing CC payment response is completed.");
		}catch(Exception e){
			logger.debug("There was an error processing the transaction response " + e.getMessage());
		}
	}
}
