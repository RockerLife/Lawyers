package com.userbo;

import java.util.Iterator;

import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.InetLogger;
import com.util.SystemProperties;

import sun.misc.BASE64Encoder;

public class CreditCardDetail {
	private static InetLogger logger = InetLogger.getInetLogger(CreditCardDetail.class);	

	public static void main(String[]a) {
		authCreditCardDetails(null);
	}
	
	public static void authCreditCardDetails (Context ctx) {
		logger.debug("Starting authCreditCardDetails.....");
		try {
			int count = 0;
			if(ctx.get("creditCardName") == null || "".equals(ctx.get("creditCardName"))){
				LawyersUtils.populateError(ctx, "creditCardName", "Please enter card holder name");
				count = 1;
			} 
			if(ctx.get("creditCardNumber") == null || "".equals(ctx.get("creditCardNumber"))){
				LawyersUtils.populateError(ctx, "creditCardNumber", "Please enter card number");
				count = 1;
			} 
			if(ctx.get("creditCardCVV") == null || "".equals(ctx.get("creditCardCVV"))){
				LawyersUtils.populateError(ctx, "creditCardCVV", "Please enter cvv number");
				count = 1;
			}
			if(count == 1){
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		}
		
		try {
			System.setProperty("javax.net.ssl.trustStore", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStore"));
			System.setProperty("javax.net.ssl.keyStorePassword", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStorePassword"));
			System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
			
			final String webServiceURI = SystemProperties.getInstance().getString("appl.LawyersIns.creditcard.webserviceurl");
			final String username = SystemProperties.getInstance().getString("appl.LawyersIns.creditcard.username");
			final String password = SystemProperties.getInstance().getString("appl.LawyersIns.creditcard.password");
			
			String merchid = SystemProperties.getInstance().getString("appl.LawyersIns.creditcard.merchid");
			String currency = SystemProperties.getInstance().getString("appl.LawyersIns.creditcard.currency");
			String tokenize = SystemProperties.getInstance().getString("appl.LawyersIns.creditcard.tokenize");
			String country = SystemProperties.getInstance().getString("appl.LawyersIns.creditcard.country");
			
			String accttype = ((ctx.get("creditCardAcctType") == null || "".equals(ctx.get("creditCardAcctType"))) ? "VI" : ctx.get("creditCardAcctType").toString());
			String account = ((ctx.get("creditCardNumber") == null || "".equals(ctx.get("creditCardNumber"))) ? "4788250000121443" : ctx.get("creditCardNumber").toString());
			String expiry = (((ctx.get("creditCardExMonth") == null || "".equals(ctx.get("creditCardExMonth"))) && (ctx.get("creditCardExYear") == null || "".equals(ctx.get("creditCardExYear")))) ? "0918" : ctx.get("creditCardExMonth").toString() + ctx.get("creditCardExYear").toString());
			String cvv2 = ((ctx.get("creditCardCVV") == null || "".equals(ctx.get("creditCardCVV"))) ? "112" : ctx.get("creditCardCVV").toString()); 
			
			float amount = Float.parseFloat((ctx.get("InvoicedPremium") == null ? 1999.00 : ctx.get("InvoicedPremium")).toString().replace("$", "").replace(",", ""));
			String name = (ctx.get("creditCardName") == null ? "Test" : ctx.get("creditCardName").toString());
			String accname = (ctx.get("AccountName") != null && !ctx.get("AccountName").equals(HtmlConstants.EMPTY) ? ctx.get("AccountName").toString(): "Test");
			String address = (ctx.get("Address1") == null ? "Address1" : ctx.get("Address1").toString());
			String city = (ctx.get("City") == null ? "City" : ctx.get("City").toString());
			String region = (ctx.get("StateCode") == null ? "CA" : ctx.get("StateCode").toString());
			String postal = ((ctx.get("BillingZipCode") == null || "".equals(ctx.get("BillingZipCode"))) ? "12345" : ctx.get("BillingZipCode").toString());
			
			String orderid = LawyersUtilities.generateUniqueId();

			// Create Authorization Transaction request
			JSONObject authRequest = new JSONObject();
			// Merchant ID
			authRequest.put("merchid", merchid);
			// Card Type
			authRequest.put("accttype", accttype);
			// Card Number
			authRequest.put("account", account);
			// Card Expiry
			authRequest.put("expiry", expiry);
			// Card CCV2
			authRequest.put("cvv2", cvv2);
			// Transaction amount Need to discuss
			authRequest.put("amount", amount*100);
			// Transaction currency
			authRequest.put("currency", currency);
			// Order ID
			authRequest.put("orderid", orderid);
			// Cardholder Name
			authRequest.put("name", name);
			// Cardholder Address
			authRequest.put("address", address);
			// Cardholder City
			authRequest.put("city", city);
			// Cardholder State
			authRequest.put("region", region);
			// Cardholder Country
			authRequest.put("country", country);
			// Cardholder Zip-Code
			authRequest.put("postal", postal);
			// Return a token for this card number
			authRequest.put("tokenize", tokenize);
			// Create the REST client
			
			// Create custom field
			JSONObject userfields = new JSONObject();	       
			userfields.put("Insured Name", accname);			
			authRequest.put("userfields", userfields);

			logger.debug("Submitting credit-card authorization request");
			logger.debug("Going to call credit card WS.");
			String subFolder = "auth";
			JSONObject authResponse = authorizeTransaction(authRequest,webServiceURI,username,password,subFolder);
			logger.debug("Credit card WS has been called.");
			
			if(authResponse == null){
				LawyersUtils.populateError(ctx, "authorizationFailed", "Authorization failed");
				return;
			}
			logger.debug("Credit-card authorization response received");
			
        	JSONObject captureRequest = new JSONObject();
			// Merchant ID
			captureRequest.put("merchid", merchid);
			// Transaction amount
			captureRequest.put("amount", amount*100);
			
			Iterator<String> authKeys= authResponse.keys();
			int approvalStatus = 0;
			while (authKeys.hasNext()) {
				String authKeyValue = (String)authKeys.next();
		        String authValueString = authResponse.getString(authKeyValue);
		        logger.debug(authKeyValue + "   :    " + authValueString);
		        if("respstat".equals(authKeyValue) && "A".equals(authValueString)){
		        	approvalStatus++;
		        }
		        if("resptext".equals(authKeyValue) && "Approval".equals(authValueString)){
		        	approvalStatus++;
		        }
		        if("retref".equals(authKeyValue)){
					// retref
					captureRequest.put("retref", authValueString);
		        }
		        if("authcode".equals(authKeyValue)){
					// auth code
					captureRequest.put("authcode", authValueString);
		        }
			}
			
			if(approvalStatus == 2){

	        	subFolder = "capture";				
	        	JSONObject captureResponse = authorizeTransaction(captureRequest, webServiceURI, username, password, subFolder);
	        	if(captureResponse == null){
					LawyersUtils.populateError(ctx, "authorizationFailed", "Authorization failed");
					return;
				}
	        	
	        	Iterator<String> captureKeys = captureResponse.keys();
				while (captureKeys.hasNext()) {
					String captureKeyValue = (String)captureKeys.next();
			        String captureValueString = captureResponse.getString(captureKeyValue);
			        if("setlstat".equals(captureKeyValue)){
			        	if("Queued for Capture".equals(captureValueString) || "Accepted".equals(captureValueString)){
							ctx.put("creditcardrespstat", "A");
			        	} else {
							LawyersUtils.populateError(ctx, "authorizationFailed", "Authorization failed");
							return;
						}
			        }
			        if("retref".equals(captureKeyValue)){
						ctx.put("creditCardTransactionID", captureValueString);
						ctx.put("creditCardAmount", amount);
			        }
				}
				logger.debug("Credit Card payment has been processed.");
			} else {
				LawyersUtils.populateError(ctx, "authorizationFailed", "Credit card authorization has failed");
				return;
			}
			
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
	}
	
	public static void authACHDetails(Context ctx) {
		logger.debug("Starting authACHDetails.....");
		try {
			int count = 0;
			if(ctx.get("AccountNumber") == null || "".equals(ctx.get("AccountNumber"))){
				LawyersUtils.populateError(ctx, "AccountNumber", "Please enter account number");
				count = 1;
			}
			if(ctx.get("RoutingNumber") == null || "".equals(ctx.get("RoutingNumber"))){
				LawyersUtils.populateError(ctx, "RoutingNumber", "Please enter routing number");
				count = 1;
			}
			if(ctx.get("BankerName") == null || "".equals(ctx.get("BankerName"))){
				LawyersUtils.populateError(ctx, "BankerName", "Please enter bank name");
				count = 1;
			}
			if(count == 1){
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		}
		
		try {
			System.setProperty("javax.net.ssl.trustStore", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStore"));
			System.setProperty("javax.net.ssl.keyStorePassword", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStorePassword"));
			System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
			
			final String webServiceURI = SystemProperties.getInstance().getString("appl.LawyersIns.creditcard.webserviceurl");
			final String username = SystemProperties.getInstance().getString("appl.LawyersIns.ach.username");
			final String password = SystemProperties.getInstance().getString("appl.LawyersIns.ach.password");
			
			String merchid = SystemProperties.getInstance().getString("appl.LawyersIns.ach.merchid");
			String currency = SystemProperties.getInstance().getString("appl.LawyersIns.creditcard.currency");
			String tokenize = SystemProperties.getInstance().getString("appl.LawyersIns.creditcard.tokenize");
			String country = SystemProperties.getInstance().getString("appl.LawyersIns.creditcard.country");
			
			String account = ((ctx.get("AccountNumber") == null || "".equals(ctx.get("AccountNumber"))) ? "1234567812345678" : ctx.get("AccountNumber").toString());
			String bankaba = ((ctx.get("RoutingNumber") == null || "".equals(ctx.get("RoutingNumber"))) ? "123456789" : ctx.get("RoutingNumber").toString());
			String ecomind = "E";
			
			float amount = Float.parseFloat((ctx.get("InvoicedPremium") == null ? 1999.00 : ctx.get("InvoicedPremium")).toString().replace("$", "").replace(",", ""));
			String name = (ctx.get("BankerName") == null ? "Test" : ctx.get("BankerName").toString());
			String accname = (ctx.get("AccountName") == null ? "Test" : ctx.get("AccountName").toString());
			
			String orderid = LawyersUtilities.generateUniqueId();
			
			String environmentVar  = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment");
			
			if(environmentVar.equals("QA")|| environmentVar.equals("UAT"))
			{
				amount = 19f;
			}

			// Create Authorization Transaction request
			JSONObject authRequest = new JSONObject();
			// Merchant ID
			authRequest.put("merchid", merchid);
			// ACH Account Number		
			authRequest.put("account", account);
			// ACH Routing Number
			authRequest.put("bankaba", bankaba);	
			// Bank Name
			authRequest.put("name", name);	
			// Account Type
			authRequest.put("accttype", "ECHK");
			// Transaction amount Need to discuss
			authRequest.put("amount", amount*100);
			// Transaction currency
			authRequest.put("currency", currency);
			// Order ID
			authRequest.put("orderid", orderid);
			// Return a token for this card number
			authRequest.put("tokenize", tokenize);			
			// Create custom field
			JSONObject userfields = new JSONObject();	       
			userfields.put("Insured Name", accname);			
			authRequest.put("userfields", userfields);
			// Create the REST client

			logger.debug("Submitting credit-card connection request");
			logger.debug("Going to call credit card connect WS.");
			String subFolder = "auth";
			JSONObject authResponse = authorizeTransaction(authRequest,webServiceURI,username,password,subFolder);
			logger.debug("Credit card connect WS has been called.");
			
			if(authResponse == null){
				LawyersUtils.populateError(ctx, "authorizationFailedACH", "Authorization failed");
				return;
			}
			logger.debug("Credit-card connection response received");
			
        	JSONObject captureRequest = new JSONObject();
			// Merchant ID
			captureRequest.put("merchid", merchid);
			// Transaction amount
			captureRequest.put("amount", amount*100);
			
			Iterator<String> authKeys= authResponse.keys();
			int approvalStatus = 0;
			while (authKeys.hasNext()) {
				String authKeyValue = (String)authKeys.next();
		        String authValueString = authResponse.getString(authKeyValue);
		        logger.debug(authKeyValue + "   :    " + authValueString);
		        if("respstat".equals(authKeyValue) && "A".equals(authValueString)){
		        	approvalStatus++;
		        }
		        if("resptext".equals(authKeyValue) && "Success".equals(authValueString)){
		        	approvalStatus++;
		        }
		        if("retref".equals(authKeyValue)){
					// retref
					captureRequest.put("retref", authValueString);
		        }
		        if("authcode".equals(authKeyValue)){
					// auth code
					captureRequest.put("authcode", authValueString);
		        }
			}
			
			if(approvalStatus == 2){
	        	subFolder = "capture";				
	        	JSONObject captureResponse = authorizeTransaction(captureRequest, webServiceURI, username, password, subFolder);
	        	if(captureResponse == null){
					LawyersUtils.populateError(ctx, "authorizationFailedACH", "ACH Authorization has been failed");
					return;
				}
	        	
	        	Iterator<String> captureKeys= captureResponse.keys();
				while (captureKeys.hasNext()) {
					String captureKeyValue = (String)captureKeys.next();
			        String captureValueString = captureResponse.getString(captureKeyValue);
			        if("setlstat".equals(captureKeyValue)){
			        	if("Queued for Capture".equals(captureValueString) || "Accepted".equals(captureValueString)){
							ctx.put("achrespstat", "A");
			        	} else {
							LawyersUtils.populateError(ctx, "authorizationFailedACH", "ACH Authorization has been failed");
							return;
						}
			        }
			        if("retref".equals(captureKeyValue)){
						ctx.put("achTransactionID", captureValueString);
						ctx.put("achAmount", amount);
			        }
				}
				ctx.put("TransactionID", ctx.get("achTransactionID"));
				ctx.put("Amount", ctx.get("achAmount"));
				
				if("A".equals(ctx.get("achrespstat"))){
					ctx.put("TransactionStatus", "Pending");				
					ctx.put("Detail", "This transaction is under processing.");
				} else {
					ctx.put("TransactionStatus", "Declined");				
					ctx.put("Detail", "This transaction has been declined.");
				}				
				logger.debug("ACH payment has been processed.");
			} else {
				LawyersUtils.populateError(ctx, "authorizationFailedACH", "ACH Authorization has been failed");
				return;
			}
			
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
	}
	
	public static JSONObject authorizeTransaction(JSONObject request, String webServiceURI, String username, String password, String subFolder) {
		try {
			ClientConfig clientConfig = new DefaultClientConfig();

			Client client = Client.create(clientConfig);

			WebResource webResource = client.resource(webServiceURI);

			String authString = username + ":" + password;
			
			String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
			
			ClientResponse response = webResource.path(subFolder)
					.accept(MediaType.APPLICATION_JSON)
					.type(MediaType.APPLICATION_JSON)
					.header("Authorization", "Basic " + authStringEnc)
					.put(ClientResponse.class, request.toString());
			
			if (response.getStatus() != 200) {
				return null;
			}
			
			String result = response.getEntity(String.class);

			return new JSONObject(result);
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
		return null;
	}
	
}
