package com.userbo;

import java.sql.Timestamp;
import java.util.Date;

import com.ach.service.PolicyIssueOptionDelegate;
import com.ach.service.PolicyIssueOptionServiceLocator;
import com.util.InetLogger;
import com.util.SystemProperties;

public class AchProcessorImpl {
	private static InetLogger logger = InetLogger.getInetLogger(AchProcessorImpl.class);
	private static final String SUCCESS_MESSAGE = "SUCCESS";
	private static final String ERROR_MESSAGE = "ERROR : ";	

	public static void main(String[] a) {
		logger.debug("ACH Batch Started....." + new Timestamp(new Date().getTime()));
		
//		a = new String[4];
//		a[0] = "LawyersIns";
//		a[1] = "C:/eclipse/Lawyers.properties";
//		a[2] = "C:/EclipseWorkSpace/New_LawyersIns_Workspace08/LawyersIns/web/XML";
//		a[3] = "-120";

		String project = a[0];
		String propertyPathMain = a[1];
		String xmlPath = a[2];
		
		logger.debug("project " + project + " " + new Timestamp(new Date().getTime()));
		logger.debug("propertyPathMain " + propertyPathMain + " " + new Timestamp(new Date().getTime()));
		logger.debug("xmlPath " + xmlPath + " " + new Timestamp(new Date().getTime()));
		try {
			SystemProperties.setPropertyFileName(propertyPathMain);
		} catch (Exception e1) {
			logger.error("Unexpected error", e1);
		}
		
		String out = SUCCESS_MESSAGE;
		out = callPolicyIssueACHWebService(a[3], "ACH");
		if(!SUCCESS_MESSAGE.equals(out)){
			logger.debug("Problem in AchProcessorImpl DocumentManagment...." + out);
		} else {
			logger.debug("AchProcessorImpl DocumentManagment Successfully complete...." + out);
		}
		logger.debug("ACH Batch Ended......" + new Timestamp(new Date().getTime()));
	}
	
	public static String callPolicyIssueACHWebService(String context, String paymentType){
		try {
			String achWSUrl = SystemProperties.getInstance().getString("appl.LawyersIns.policyissueservice.webserviceurl");
			
			PolicyIssueOptionServiceLocator policyIssueOptionServiceLocator = new PolicyIssueOptionServiceLocator();
			
			policyIssueOptionServiceLocator.setPolicyIssueOptionPortEndpointAddress(achWSUrl);
			
			PolicyIssueOptionDelegate policyIssueOptionDelegate = policyIssueOptionServiceLocator.getPolicyIssueOptionPort();
			return policyIssueOptionDelegate.receiveIssuePolicy(context,paymentType);
		}catch(Throwable t){
			logger.error("Unexpected error", t);
			return ERROR_MESSAGE + t.getMessage();
		}
	}
}
