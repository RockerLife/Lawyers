package com.manage.epayment.authorize;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.userbo.DocumentManagment;
import com.util.Context;
import com.util.IContext;
import com.util.InetLogger;

public class AuthorizePaymentResponse {
	
	private static InetLogger logger = InetLogger.getInetLogger(AuthorizePaymentResponse.class);
	public  static String DATE_PATTERN = "MM-dd-yyyy hh:mm";
	public  static String DATE_PATTERN_NEW = "yyyy-MM-dd hh:mm";
	
	public void processPaymentResponse(IContext ctx) throws Exception{
		try{
			logger.debug("Going to process payment response");
			String x_response_code = ctx.get("x_response_code") != null ? ctx.get("x_response_code").toString() : "";
			
			logger.debug("Response Code is -------- >" + x_response_code);
			
			Context nctx = new Context();
			nctx.putAll(ctx);
			nctx.put("TransactionSequence", ctx.get("FinalisedQuote_TransactionSequence")); 
			
			String x_trans_id = ctx.get("x_trans_id")!=null ? ctx.get("x_trans_id").toString() : "";
			nctx.put("TransactionID", x_trans_id);
			String x_response_reason_code = ctx.get("x_response_reason_code") != null ? ctx.get("x_response_reason_code").toString() : "";
			String x_response_reason_text = ctx.get("x_response_reason_text") != null ? ctx.get("x_response_reason_text").toString() : "";
			
			logger.debug("Response Reason Code - - > " + x_response_reason_code);
			logger.debug("Response Reason Text - - > " + x_response_reason_text);
			
			nctx.put("TransactionStatus", x_response_code.equals("1") ? "Approved" : "Declined");
			nctx.put("Amount", ctx.get("x_amount"));				
			nctx.put("PaymentMode", "AuthorizeDotNet");
			nctx.put("Detail", x_response_reason_text);
			try{
				RuleUtils.executeRule(nctx, "LawyersRule.AssignLastUpdateTimeStamp");
				if(nctx.get("LastUpdateTimeStamp") != null){
						logger.debug("LastUpdateTimeStamp--   "+nctx.get("LastUpdateTimeStamp"));
						nctx.put("TransactionDate",new SimpleDateFormat(DATE_PATTERN_NEW).format(nctx.get("LastUpdateTimeStamp")));
						logger.debug("TransactionDate ---->    "+nctx.get("TransactionDate"));	
					}
				}
				catch (Exception e){			
					logger.error("Unable to assign payment timestamp", e);
				}
			Object obj = SqlResources.getSqlMapProcessor(nctx).findByKey("PaymentDetail.findByKey", nctx);
			Map paymentDetailMap = null;
			
			if(obj != null && obj instanceof Map)
				paymentDetailMap = (Map) obj;			
			SqlResources.getSqlMapProcessor(nctx).insert("PaymentDetail.insert", nctx);
			logger.debug("Payment Details inserted");
			/*if(paymentDetailMap == null){
				logger.debug("No data exist in Payment detail. Insert query will be executed");
				SqlResources.getSqlMapProcessor(nctx).insert("PaymentDetail.insert", nctx);
			}else if(paymentDetailMap != null){
				logger.debug("Data exist in Payment detail. Update query will be executed.");
				SqlResources.getSqlMapProcessor(nctx).update("SqlStmts.UserStatementManualBoQueriesupdatePaymentDetailPayment", nctx);
			}*/
			
			if("1".equals(x_response_code) && "1".equals(x_response_reason_code))
			{			
				logger.debug("Valid Response reason code, going to process further");
				logger.debug("Check if Status is not already Issued ");
				Object objPT = SqlResources.getSqlMapProcessor(ctx).findByKey("PolicyTransaction.findByKey", ctx);
				Map policyTransactionMap = null;
				if(objPT != null && objPT instanceof Map)
					policyTransactionMap = (Map) objPT;
				
				if(policyTransactionMap != null && policyTransactionMap.get("StatusKey") != null) {
					if("6".equals(policyTransactionMap.get("StatusKey").toString()))
					{
						logger.debug("Policy has been issued already");	
						return;
					}
					else
					{						
						logger.debug("Policy is not issued ... Generating Document and PolicyNumber");
						new DocumentManagment().processDocumentManagment(ctx);
						String IsDocumentProcessed = ctx.get("IsDocumentProcessed") != null ? ctx.get("IsDocumentProcessed").toString() : "";
						if("Y".equals(IsDocumentProcessed)){
							
							logger.debug("Document was processed successfully.");
							logger.debug("Ending Process : Authorize Payment");
						}
					}								
				}
				
			}else{				
				logger.debug("Payment did not processed");
			}
		}catch(Exception e){
			logger.error("Unable to process transaction response", e);
		}
		
	}
	
	public void processPaymentCreditCardResponse(IContext ctx) throws Exception{
		try{
			logger.debug("Going to process CC payment response");
			
			ctx.put("TransactionID", ctx.get("creditCardTransactionID"));
			ctx.put("Amount", ctx.get("creditCardAmount"));	
			ctx.put("PaymentMode", "paypal");
			
			if("A".equals(ctx.get("creditcardrespstat"))){
				ctx.put("TransactionStatus", "Approved");				
				ctx.put("Detail", "This transaction has been approved.");
			} else {
				ctx.put("TransactionStatus", "Declined");				
				ctx.put("Detail", "This transaction has been declined.");
			}
			
			try{
				RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");
				if(ctx.get("LastUpdateTimeStamp") != null){
					logger.debug("LastUpdateTimeStamp--   "+ctx.get("LastUpdateTimeStamp"));
					ctx.put("TransactionDate",ctx.get("LastUpdateTimeStamp"));
					logger.debug("TransactionDate ---->    "+ctx.get("TransactionDate"));	
				}
			}
			catch (Exception e){			
				logger.error("Unable to assign payment timestamp", e);
			}
			
			List paymentDetailList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPaymentDetail", ctx);
			if(paymentDetailList != null && paymentDetailList.size() > 0){
				SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdatePaymentDetailPayment", ctx);
			} else {
				SqlResources.getSqlMapProcessor(ctx).insert("PaymentDetail.insert", ctx);
			}
			
			if("A".equals(ctx.get("creditcardrespstat"))){
				Object objPT = SqlResources.getSqlMapProcessor(ctx).findByKey("PolicyTransaction.findByKey", ctx);
				Map policyTransactionMap = null;
				if(objPT != null && objPT instanceof Map)
					policyTransactionMap = (Map) objPT;
				
				if(policyTransactionMap != null && policyTransactionMap.get("StatusKey") != null) {
					if("6".equals(policyTransactionMap.get("StatusKey").toString())){
						logger.debug("Policy has been issued already");	
						return;
					} else {						
						logger.debug("Policy is not issued ... Generating Document and PolicyNumber");
						new DocumentManagment().processDocumentManagment(ctx);
					}								
				}
			}			
			logger.debug("Processing CC payment response is completed.");
		}catch(Exception e){
			logger.error("Unable to process transaction response", e);
		}
	}

	public void processPaymentACHResponse(IContext ctx) throws Exception{
		try{
			logger.debug("Going to process ACH payment response");
			
			ctx.put("TransactionID", ctx.get("achTransactionID"));
			ctx.put("Amount", ctx.get("achAmount"));	
			ctx.put("PaymentMode", "ach");
			
			if("A".equals(ctx.get("achrespstat"))){
				ctx.put("TransactionStatus", "Approved");				
				ctx.put("Detail", "This transaction has been approved.");
			} else {
				ctx.put("TransactionStatus", "Declined");				
				ctx.put("Detail", "This transaction has been declined.");
			}
			
			try{
				RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");
				if(ctx.get("LastUpdateTimeStamp") != null){
					logger.debug("LastUpdateTimeStamp--   "+ctx.get("LastUpdateTimeStamp"));
					ctx.put("TransactionDate",ctx.get("LastUpdateTimeStamp"));
					logger.debug("TransactionDate ---->    "+ctx.get("TransactionDate"));	
				}
			}
			catch (Exception e){			
				logger.error("Unable to assign payment timestamp", e);
			}
			
			List paymentDetailList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPaymentDetail", ctx);
			if(paymentDetailList != null && paymentDetailList.size() > 0){
				SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdatePaymentDetailPayment", ctx);
			} else {
				SqlResources.getSqlMapProcessor(ctx).insert("PaymentDetail.insert", ctx);
			}
			
			if("A".equals(ctx.get("achrespstat"))){
				Object objPT = SqlResources.getSqlMapProcessor(ctx).findByKey("PolicyTransaction.findByKey", ctx);
				Map policyTransactionMap = null;
				if(objPT != null && objPT instanceof Map)
					policyTransactionMap = (Map) objPT;
				
				if(policyTransactionMap != null && policyTransactionMap.get("StatusKey") != null) {
					if("6".equals(policyTransactionMap.get("StatusKey").toString())){
						logger.debug("Policy has been issued already");	
						return;
					} else {						
						logger.debug("Policy is not issued ... Generating Document and PolicyNumber");
						new DocumentManagment().processDocumentManagment(ctx);
					}								
				}
			}			
			logger.debug("Processing ACH payment response is completed.");
		}catch(Exception e){
			logger.error("Unable to process transaction response", e);
		}
	}
}
