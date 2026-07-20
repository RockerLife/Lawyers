package com.userbo;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.manage.epayment.paypal.ButtonEncryptionUtil;
import com.util.IContext;
import com.util.InetLogger;
import com.util.SystemProperties;

public class PaymentUtils {
	private static InetLogger logger = InetLogger
			.getInetLogger(PaymentUtils.class);

	public void processPaymentAuth(IContext ctx) throws Exception {
		try {	
			
			logger.debug("Going to get data for Payment Request.");
			logger.debug("Policy Key is " + ctx.get("PolicyKey"));
			logger.debug("Transaction Sequence " + ctx.get("FinalisedQuote_TransactionSequence"));
			
			long timeStamp = System.currentTimeMillis()/1000;
			String ts = String.valueOf(timeStamp);
			String amount = ctx.get("InvoicedPremium") != null ? ctx.get("InvoicedPremium").toString() : "";
			amount =(String) LawyersUtils.removeAmountFormat(amount);			
			String loginid = SystemProperties.getInstance().getString("authorize.payment.gateway.loginid");
			String transactionkey = SystemProperties.getInstance().getString("authorize.payment.gateway.transactionkey");
			String x_version = SystemProperties.getInstance().getString("authorize.payment.gateway.version");
			String x_show_form = SystemProperties.getInstance().getString("authorize.payment.gateway.showform");
			String x_test_mode = SystemProperties.getInstance().getString("authorize.payment.gateway.testmode");
			String x_cancel_url = SystemProperties.getInstance().getString("authorize.payment.gateway.cancel");
			String x_relay_response = SystemProperties.getInstance().getString("authorize.payment.gateway.relayresponse");
			String bgcolor = SystemProperties.getInstance().getString("authorize.payment.gateway.bgcolor");
			String x_relay_url= SystemProperties.getInstance().getString("authorize.payment.gateway.relayresponseurl"); 
			String x_logo_url=SystemProperties.getInstance().getString("authorize.payment.gateway.logoimageurl");
			
			int sequence = ctx.get("FinalisedQuote_TransactionSequence") != null ? Integer.parseInt(ctx.get("FinalisedQuote_TransactionSequence").toString()) : 0;
			
			String fingerprint = generateFingerPrint(loginid,sequence,ts,amount,transactionkey);
			logger.debug("Got the value");
			
			//populate data in context
			ctx.put("x_login", loginid);
			ctx.put("x_fp_sequence", ctx.get("FinalisedQuote_TransactionSequence"));
			ctx.put("x_amount", amount);			
			ctx.put("x_email", ctx.get("AccountEmail"));
			ctx.put("x_first_name", ctx.get("ContactPerson"));
			ctx.put("x_fp_hash", fingerprint);
			ctx.put("x_version", x_version);
			ctx.put("x_cust_id", ctx.get("AccountName"));
			ctx.put("x_fp_sequence", sequence);
			ctx.put("x_fp_timestamp", ts);
			ctx.put("x_show_form",x_show_form);
			ctx.put("x_invoice_num", ctx.get("QuoteNumber"));
			ctx.put("x_relay_response", x_relay_response);
			ctx.put("x_relay_url", x_relay_url);
			ctx.put("x_cancel_url", x_cancel_url);
			ctx.put("x_test_request", x_test_mode);
			ctx.put("x_logo_url", x_logo_url);
			
			//String x_header_html_payment_form = "<style type='text/css' media='all'>TD{font-family: arial, verdana,trebuchet,helvetica,geneva,sansserif;font-size:12px; color:#000000;margin-left:1px;}	INPUT{font-family:Arial,Verdana, Trebuchet,Helvetica,Geneva,sansserif;	font-size:12px;color: #000000;margin-left:1px;}</style>	Please enter your payment information.";
			
			String x_header_html_payment_form = "Please enter your payment information.";
			ctx.put("x_header_html_payment_form", x_header_html_payment_form);
			ctx.put("x_color_background", bgcolor);
			
			logger.debug("Got the data for Payment request");
			
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}

	}
	
	private String generateFingerPrint(String loginID,int sequence,String timeStamp,String amount,String transactionKey) throws Exception{
		KeyGenerator kg = KeyGenerator.getInstance("HmacMD5");
		SecretKey key = new SecretKeySpec(transactionKey.getBytes(), "HmacMD5");
		// A MAC object is created to generate the hash using the HmacMD5 algorithm
		Mac mac = Mac.getInstance("HmacMD5");
		mac.init(key);
		String inputstring = loginID + "^" + sequence + "^" + timeStamp + "^" + amount + "^";
		byte[] result = mac.doFinal(inputstring.getBytes());
		// Convert the result from byte[] to hexadecimal format
		StringBuffer strbuf = new StringBuffer(result.length * 2);
		for(int i=0; i< result.length; i++)
		{
			if(((int) result[i] & 0xff) < 0x10)
				strbuf.append("0");
			strbuf.append(Long.toString((int) result[i] & 0xff, 16));
		}
		String fingerprint = strbuf.toString();
		return fingerprint ;
	}

	public void processPayment(IContext ctx) throws Exception {
		String returnUrl = null;
		String cancelUrl = null;
		String gatewayUrl = null;
		String businessId = null;
		String publiccertPath = null;
		String privatekeyPath = null;
		String keyPass = null;
		String paypalcertPath = null;
		String certId = null;
		String notify_URL = null;
		String customVar = null;
		String textForReturnButton = null;

		// ctx.put("Amount", ctx.get("TotalPremium"));
		ctx.put("Amount", ctx.get("InvoicedPremium"));
		// ctx.put("Amount", "$1");
		// make custom var including PolicyKey, TransactionKey, Current Date
		if (ctx.get("PolicyKey") != null && !"".equals(ctx.get("PolicyKey")))
			customVar = ctx.get("PolicyKey").toString() + ";";
		if (ctx.get("TransactionSequence") != null
				&& !"".equals(ctx.get("TransactionSequence")))
			customVar = customVar + ctx.get("TransactionSequence").toString()
					+ ";";
		if (ctx.get("FinalisedQuote_TransactionSequence") != null
				&& !"".equals(ctx.get("FinalisedQuote_TransactionSequence")))
			customVar = customVar
					+ ctx.get("FinalisedQuote_TransactionSequence").toString()
					+ ";";
		if (ctx.get("PaymentMode") != null
				&& !"".equals(ctx.get("PaymentMode")))
			customVar = customVar + ctx.get("PaymentMode").toString() + ";";

		try {

			returnUrl = SystemProperties.getInstance().getString(
					"payment.return.url")
					+ "?jsessionid=" + ctx.get("jsessionid");
			cancelUrl = SystemProperties.getInstance().getString(
					"payment.cancel.url");
			gatewayUrl = SystemProperties.getInstance().getString(
					"payment.gateway.url");
			businessId = SystemProperties.getInstance().getString(
					"payment.gateway.business.id");
			publiccertPath = SystemProperties.getInstance().getString(
					"payment.gateway.publiccert.path");
			privatekeyPath = SystemProperties.getInstance().getString(
					"payment.gateway.privatekey.path");
			keyPass = SystemProperties.getInstance().getString(
					"payment.gateway.privatekey.keypass");
			paypalcertPath = SystemProperties.getInstance().getString(
					"payment.gateway.paypalcert.path");
			certId = SystemProperties.getInstance().getString(
					"payment.gateway.cert.id");
			notify_URL = SystemProperties.getInstance().getString(
					"payment.gateway.notify.url");
			textForReturnButton = SystemProperties.getInstance().getString(
					"payment.gateway.return.text");
			String encrypted = ButtonEncryptionUtil.getEncryptedButton(
					certId,
					publiccertPath,
					privatekeyPath,
					paypalcertPath,
					keyPass,
					businessId,
					"Insurance Policy",
					Double.parseDouble(LawyersUtils.removeAmountFormat(
							ctx.get("Amount")).toString()), "USD", cancelUrl,
					returnUrl, notify_URL, customVar, textForReturnButton);
			ctx.put("encrypted", encrypted);
			ctx.put("cmd", "_s-xclick");

		} catch (Exception e) {

			logger.debug("Paypal credential have not been generated---- ");
			logger.error("Unexpected error", e);
			throw e;
		}
	}

}
