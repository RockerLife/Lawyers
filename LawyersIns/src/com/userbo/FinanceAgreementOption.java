package com.userbo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ipfs.accountant.service.FinanceAgreementOptionDelegate;
import com.ipfs.accountant.service.FinanceAgreementOptionServiceLocator;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;
import com.util.IContext;
import com.util.InetLogger;
import com.util.SystemProperties;


public class FinanceAgreementOption {
	
	private static final String SUCCESS_MESSAGE = "SUCCESS";
	private static final String ERROR_MESSAGE = "ERROR : ";	
	private static InetLogger logger = InetLogger.getInetLogger(FinanceAgreementOption.class);
	private static String projectName="LawyersIns";
	public  static String DATE_PATTERN = "MM-dd-yyyy hh:mm";
	
	public String receiveAgreementQuoteNumber(String ipfsQuoteNumber, String paymentType){
		
		logger.debug("Going To Issue Policy from the Web Service Call Finance");
		logger.debug("Your Agreement Quote Number is ---> " + ipfsQuoteNumber + " paymentType is ---> " + paymentType);
		//return ERROR_MESSAGE ;	
		
		if(ipfsQuoteNumber.isEmpty() || ipfsQuoteNumber == null){
			logger.debug("Empty ipfsQuoteNumber - ");
			return "IPFS Quote Number can't be blank" ;
		}
		
		IContext wsCtx = new Context();
		wsCtx.setProject(projectName);
		wsCtx.put("financeAgreementQuoteNumber", ipfsQuoteNumber);
		try{
			List list = SqlResources.getSqlMapProcessor(wsCtx).select("SqlStmts.UserStatementManualBoQueriesgetIPFSQuoteNumberForPolicyIssued", wsCtx);
			if(list != null && list.size() == 1){
				return receiveLawyersAgreementQuoteNumber(wsCtx, ipfsQuoteNumber, paymentType);
			} else {
				return receiveAccountantAgreementQuoteNumber(ipfsQuoteNumber, paymentType);
			}
		}catch(Throwable t){
			logger.error("Unexpected error", t);
			logger.debug("Problem in FinanceAgreementOption " + t.getMessage());
			return ERROR_MESSAGE + t.getMessage();
		}
	}
	public String receiveLawyersAgreementQuoteNumber(IContext wsCtx, String ipfsQuoteNumber, String paymentType){
		wsCtx.put("CutOffDate", LawyersConstants.CUT_OFF_DATE);
		wsCtx.put("CutOffDateGroup2", LawyersConstants.CUT_OFF_DATE_GROUP2);
		wsCtx.put("NewAppFlowCutOffDate", LawyersConstants.NEWAPPFLOW_CUT_OFF_DATE);
		wsCtx.put("CutOffDateGroup3", LawyersConstants.CUT_OFF_DATE_GROUP3);
		wsCtx.put("CutOffDateGroup4", LawyersConstants.CUT_OFF_DATE_GROUP4);
		wsCtx.put("CutOffDateGroup5", LawyersConstants.CUT_OFF_DATE_GROUP5);
		wsCtx.put("CutOffDateGroup6", LawyersConstants.CUT_OFF_DATE_GROUP6);
		wsCtx.put("CutOffDateGroup7", LawyersConstants.CUT_OFF_DATE_GROUP7);
		wsCtx.put("CutOffDateGroup8", LawyersConstants.CUT_OFF_DATE_GROUP8);
		wsCtx.put("CutOffDateCCBSupp", LawyersConstants.CUT_OFF_DATE_CCBSupp);
		wsCtx.put("CutOffDate2020", LawyersConstants.CUT_OFF_DATE_2020);
		
		try{
			wsCtx.put("LastUpdateTimeStamp", new Timestamp(new Date().getTime()));
			if(wsCtx.get("LastUpdateTimeStamp") != null){
				logger.debug("LastUpdateTimeStamp--   "+wsCtx.get("LastUpdateTimeStamp"));
				wsCtx.put("TransactionDate",new SimpleDateFormat(DATE_PATTERN).format(wsCtx.get("LastUpdateTimeStamp")));
				logger.debug("TransactionDate ---->    "+wsCtx.get("TransactionDate"));	
			}
		}
		catch (Exception e){			
			logger.debug("AssignLastUpdateTimeStamp------"+e.getMessage());
		}		
		
		try{
			logger.debug("Get Data from Finance Quote number");
			String baseUrl = SystemProperties.getInstance().getString("baseUrl");
			wsCtx.put("baseUrl", baseUrl);
			
			
			List list = SqlResources.getSqlMapProcessor(wsCtx).select("SqlStmts.UserStatementManualBoQueriesgetFirmDataFromFinanceQuoteNumber", wsCtx);
			if(list != null && list.size() == 1){
				logger.debug("Got Firm data from ipfsQuoteNumber");
				Map firmMap = (Map)list.get(0);
				wsCtx.putAll(firmMap);
				
				List paymentDetailList = SqlResources.getSqlMapProcessor(wsCtx).select("SqlStmts.UserStatementManualBoQueriesgetPaymentDetail", wsCtx);
				if(paymentDetailList != null && paymentDetailList.size() > 0){
					Map paymentDetailMap = (Map)paymentDetailList.get(0);
					if("ManualCheck".equals(paymentDetailMap.get("FinanceDownPayment"))){
						logger.debug("IPFS Manual Check Quote Number (" + ipfsQuoteNumber + ") can not be issued");
						return "IPFS Manual Check Quote Number (" + ipfsQuoteNumber + ") can not be issued";
					}
				}
				logger.debug("Going to call Document Management for Policy Issuance...");
				try{
					DocumentManagment mgt = new DocumentManagment();
					String out = mgt.processDocumentManagment(wsCtx);
					if(!SUCCESS_MESSAGE.equals(out)){
						logger.debug("Problem in DocumentManagment...." + out);
						return "IPFS Quote Number (" + ipfsQuoteNumber + ") " + out;
					}
					
					wsCtx.put("PaymentMode", "finance");
					wsCtx.put("TransactionID", ipfsQuoteNumber);
					wsCtx.put("TransactionStatus", "Approved");				
					wsCtx.put("Detail", paymentType);					
					wsCtx.put("Amount", wsCtx.get("InvoicedPremium"));
					
					if(paymentDetailList != null && paymentDetailList.size() > 0){
						SqlResources.getSqlMapProcessor(wsCtx).update("SqlStmts.UserStatementManualBoQueriesupdatePaymentDetailPayment", wsCtx);
					} else {
						SqlResources.getSqlMapProcessor(wsCtx).insert("PaymentDetail.insert", wsCtx);
					}					
					
				} catch(Exception e){
					logger.error("Unexpected error", e);
					logger.debug("Problem in FinanceAgreementOption " + e.getMessage());
					return ERROR_MESSAGE + e.getMessage();
				}
				logger.debug("Called the document Management.");
			} else {
				logger.debug("Could Not find Data for ipfsQuoteNumber - " + ipfsQuoteNumber );
				return "IPFS Quote Number (" + ipfsQuoteNumber + ") is invalid" ;
			}
						
		}catch(Throwable t){
			logger.error("Unexpected error", t);
			logger.debug("Problem in FinanceAgreementOption " + t.getMessage());
			return ERROR_MESSAGE + t.getMessage();
		}		
		return SUCCESS_MESSAGE;
	}
	
	public String receiveAccountantAgreementQuoteNumber(String ipfsQuoteNumber, String paymentType){
		try {

			String ipfsWSUrl = SystemProperties.getInstance().getString("appl.AccountantIns.financeagreementservice.webserviceurl");
			
			FinanceAgreementOptionServiceLocator financeAgreementOptionServiceLocator = new FinanceAgreementOptionServiceLocator();
			
			financeAgreementOptionServiceLocator.setFinanceAgreementOptionPortEndpointAddress(ipfsWSUrl);
			
			FinanceAgreementOptionDelegate financeAgreementOptionDelegate = financeAgreementOptionServiceLocator.getFinanceAgreementOptionPort();
			return financeAgreementOptionDelegate.receiveAgreementQuoteNumber(ipfsQuoteNumber,paymentType);
		}catch(Throwable t){
			logger.error("Unexpected error", t);
			logger.debug("Problem in FinanceAgreementOption " + t.getMessage());
			return ERROR_MESSAGE + t.getMessage();
		}
	}
	
	public static void main(String[] arg){
		new FinanceAgreementOption().receiveAgreementQuoteNumber("11753151", "Complete");
	}

}
