package com.manage.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.fop.servlet.ServletContextURIResolver;

import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.userbo.DocumentManagment;
import com.userbo.LawyersUtils;
import com.util.Context;
import com.util.IContext;
import com.util.InetLogger;
import com.util.SystemProperties;

public class LAWPayPalIPNListener extends HttpServlet {
	
	private static InetLogger logger = InetLogger.getInetLogger(LAWPayPalIPNListener.class);	
	private static String projectName="LawyersIns";
	public  static String DATE_PATTERN = "MM-dd-yyyy hh:mm";
	protected ServletContextURIResolver uriResolver;
	protected ServletContext servletContext;
	
	
	public void init() throws ServletException
	{
	    try 
	    {
	    	uriResolver = new ServletContextURIResolver(getServletContext());
	    	servletContext = getServletContext();	    	
	        
        } 
	    catch (Exception e)
	    {
            logger.error("Project resources are not initialized properly");
        }	    		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) {

		uriResolver = new ServletContextURIResolver(getServletContext());
    	servletContext = getServletContext();
    	
		// read post from PayPal system and add 'cmd'
		Enumeration en = request.getParameterNames();
		String str = "cmd=_notify-validate";
		while (en.hasMoreElements()) {
			String paramName = (String) en.nextElement();
			String paramValue = request.getParameter(paramName);
			str = str + "&" + paramName + "=" + URLEncoder.encode(paramValue);
		}
		
		logger.debug("IPNListener started"); 
		
		// post back to PayPal system to validate
		// NOTE: change http: to https: in the following URL to verify using SSL
		// (for increased security).
		// using HTTPS requires either Java 1.4 or greater, or Java Secure
		// Socket Extension (JSSE)
		// and configured for older versions.
		
		URL u = null;
		String res = "";
		URLConnection uc = null;
		PrintWriter pw = null;
		String payPalUrl = "https://www.sandbox.paypal.com/cgi-bin/webscr";
		
		try{
			payPalUrl = SystemProperties.getInstance().getString("payment.gateway.url");
		}
		catch(Exception se){
			logger.error("Unexpected error", se);
		}
		
		try {
			u = new URL(payPalUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		}

		try {
			uc = u.openConnection();

			uc.setDoOutput(true);
			uc.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			pw = new PrintWriter(uc.getOutputStream());

			pw.println(str);
			pw.close();

			BufferedReader in = null;

			in = new BufferedReader(new InputStreamReader(uc.getInputStream()));

			res = in.readLine();

			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		}

		// assign posted variables to local variables
		String itemName = request.getParameter("item_name");
		//String itemNumber = request.getParameter("item_number");
		String paymentStatus = request.getParameter("payment_status");
		String paymentAmount = request.getParameter("mc_gross");
		String paymentCurrency = request.getParameter("mc_currency");
		String customVar = request.getParameter("custom");
		String txnId = request.getParameter("txn_id");
		String receiverEmail = request.getParameter("receiver_email");
		String payerEmail = request.getParameter("payer_email");
		
		IContext ctx = new Context();
		ctx.setProject(projectName);
		ctx.put("baseUrl", "file:///" + servletContext.getRealPath("/"));
		ctx.put("uriResolver", uriResolver);
		
		String txnIdDB = null;
		String PaymentMode = null;
		
		String[] policydetails = customVar.split(";"); //15126;22736;22736
		
		String PolicyKey = policydetails[0];
		String TransactionSequence = policydetails[1];
		String FinalisedQuote_TransactionSequence = policydetails[2];
		ctx.put("PolicyKey", PolicyKey);
		//ctx.put("TransactionSequence", TransactionSequence);
		//ctx.put("FinalisedQuote_TransactionSequence", FinalisedQuote_TransactionSequence);
		ctx.put("TransactionSequence", FinalisedQuote_TransactionSequence);
		ctx.put("PaymentMode", "paypal");		
		
		boolean flagIsDocumentProcessed = false;
		
		try{
		RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");
		if(ctx.get("LastUpdateTimeStamp") != null)
			logger.debug("LastUpdateTimeStamp--   "+ctx.get("LastUpdateTimeStamp"));
			ctx.put("TransactionDate",new SimpleDateFormat(DATE_PATTERN).format(ctx.get("LastUpdateTimeStamp")));
			logger.debug("TransactionDate--    "+ctx.get("TransactionDate"));
			
		}
		catch (Exception e){			
			logger.error("Unable to assign payment timestamp", e);
		}
		
		logger.debug("Before verify-------");
		// check notification validation
		if (res.equals("VERIFIED")) {
			
			// check that paymentStatus=Completed
			// check that txnId has not been previously processed
			// check that receiverEmail is your Primary PayPal email
			// check that paymentAmount/paymentCurrency are correct
			// process payment
			logger.debug("payment Status------"+paymentStatus);
			if(paymentStatus != null && "Completed".equals(paymentStatus)){				
				logger.debug("customVar -----"+customVar);
				if(customVar != null && !"".equals(customVar))
				{
					logger.debug("IPNListener verified and completed");					
					
					try
					{
						Object objPT = SqlResources.getSqlMapProcessor(ctx).findByKey("PolicyTransaction.findByKey", ctx);
						Map policyTransactionMap = null;
						
						if(objPT != null && objPT instanceof Map)
							policyTransactionMap = (Map) objPT;
						
						if(policyTransactionMap != null && policyTransactionMap.get("StatusKey") != null) {
							if("6".equals(policyTransactionMap.get("StatusKey").toString())){
								
								logger.debug("Policy has been issued already");	
								return;
							}								
						}
						
						
						
						Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("PaymentDetail.findByKey", ctx);
						Map paymentDetailMap = null;
						
						if(obj != null && obj instanceof Map)
							paymentDetailMap = (Map) obj;
						
						new DocumentManagment().processDocumentManagment(ctx);
						
						if(paymentDetailMap == null )
						{
							//new DocumentManagment().processDocumentManagment(ctx);
							
							Object objIsDocumentProcessed = RuleUtils.executeRule(ctx, "LawyersRule.IsDocumentProcessed");
							if(objIsDocumentProcessed != null && objIsDocumentProcessed instanceof Boolean)
								flagIsDocumentProcessed = (Boolean) objIsDocumentProcessed;
							
							if(flagIsDocumentProcessed){
								
								logger.debug("Forms has been uploaded");								
								ctx.put("TransactionStatus", paymentStatus);
								ctx.put("TransactionID", txnId);
								String amount = ctx.get("Amount") != null ? ctx.get("Amount").toString() : "0" ;
								logger.debug("String Amount is - - > " + amount);
								Object intamount = LawyersUtils.removeAmountFormat(amount);
								logger.debug("Amount Format Removed - - > " + amount);
								ctx.put("Amount", intamount);
								SqlResources.getSqlMapProcessor(ctx).insert("PaymentDetail.insert", ctx);
								
								logger.debug("IPNListener is finished");								
							}
							
							return;
						}							
						else if(paymentDetailMap != null)
						{
							if(paymentDetailMap.get("TransactionID") != null)
								txnIdDB = paymentDetailMap.get("TransactionID").toString();
							
							if(txnIdDB == null || (txnIdDB != null && !txnIdDB.equals(txnId)) || (txnIdDB != null && txnIdDB.equals(txnId) && !"Completed".equals(paymentDetailMap.get("TransactionID").toString())))							
							{
								Object objIsDocumentProcessed = RuleUtils.executeRule(ctx, "LawyersRule.IsDocumentProcessed");
								if(objIsDocumentProcessed != null && objIsDocumentProcessed instanceof Boolean)
									flagIsDocumentProcessed = (Boolean) objIsDocumentProcessed;
								
								if(flagIsDocumentProcessed){
//									new DocumentManagment().processDocumentManagment(ctx);
									logger.debug("Forms has been uploaded");
									
									ctx.put("TransactionStatus", paymentStatus);
									ctx.put("TransactionID", txnId);
									SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdatePaymentDetail", ctx);
									
									logger.debug("IPNListener is finished");
								}
							}
						}
						
					}catch (Exception e) {
						logger.error("Unexpected error", e);
						logger.debug("Error in processing IPNListener");
						
					}						
					
				}
			
			}
			else
			{
				logger.debug("payment status is not completed---");
				try{
					
					ctx.put("TransactionStatus", "UnCompleted");
					ctx.put("TransactionID", txnId);
					
					Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("PaymentDetail.findByKey", ctx);
					Map paymentDetailMap = null;
					
					if(obj != null && obj instanceof Map)
						paymentDetailMap = (Map) obj;					
					
					if(paymentDetailMap == null )
						SqlResources.getSqlMapProcessor(ctx).insert("PaymentDetail.insert", ctx);
					
//					else if(paymentDetailMap != null)
//					{
//						if(paymentDetailMap.get("TransactionID") != null)
//							txnIdDB = paymentDetailMap.get("TransactionID").toString();
//						
//						if(txnIdDB == null || (txnIdDB != null && !txnIdDB.equals(txnId)) )							
//							ctx.put("TransactionID", txnId);
//						
//						SqlResources.getSqlMapProcessor(ctx).update("PaymentDetail.update", ctx);
//					}
											
					
				}catch (Exception e) {
					logger.error("Unable to process payment notification", e);
				}
			}
			
		} else if (res.equals("INVALID")) {
			
			logger.debug("IPNListener invalid"); 
			// log for investigation
			try{
				
				ctx.put("TransactionStatus", "Invalid");
				ctx.put("TransactionID", txnId);
				
				Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("PaymentDetail.findByKey", ctx);
				Map paymentDetailMap = null;
				
				if(obj != null && obj instanceof Map)
					paymentDetailMap = (Map) obj;					
				
				if(paymentDetailMap == null )
					SqlResources.getSqlMapProcessor(ctx).insert("PaymentDetail.insert", ctx);	
				
//				else if(paymentDetailMap != null)
//				{
//					if(paymentDetailMap.get("TransactionID") != null)
//						txnIdDB = paymentDetailMap.get("TransactionID").toString();
//					
//					if(txnIdDB == null || (txnIdDB != null && !txnIdDB.equals(txnId)) )							
//						ctx.put("TransactionID", txnId);
//					
//					SqlResources.getSqlMapProcessor(ctx).update("PaymentDetail.update", ctx);
//				}
				
			}catch (Exception e) {
				logger.error("Unable to process payment notification", e);
			}
			
			
		} else {
			logger.debug("IPNListener failed"); 
			// error
			try{
				ctx.put("TransactionStatus", "failed");
				ctx.put("TransactionID", txnId);			
				
				Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("PaymentDetail.findByKey", ctx);
				Map paymentDetailMap = null;
				
				if(obj != null && obj instanceof Map)
					paymentDetailMap = (Map) obj;					
				
				if(paymentDetailMap == null )
					SqlResources.getSqlMapProcessor(ctx).insert("PaymentDetail.insert", ctx);		
				
//				else if(paymentDetailMap != null)
//				{
//					if(paymentDetailMap.get("TransactionID") != null)
//						txnIdDB = paymentDetailMap.get("TransactionID").toString();
//					
//					if(txnIdDB == null || (txnIdDB != null && !txnIdDB.equals(txnId)) )							
//						ctx.put("TransactionID", txnId);
//					
//					SqlResources.getSqlMapProcessor(ctx).update("PaymentDetail.update", ctx);
//				}
				
			}catch (Exception e) {
				logger.error("Unable to process payment notification", e);
			}
		}

	}
	
	
}
