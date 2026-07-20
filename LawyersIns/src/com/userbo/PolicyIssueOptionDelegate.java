 package com.userbo;

import javax.jws.WebParam;

@javax.jws.WebService(targetNamespace = "http://userbo.com/", serviceName = "PolicyIssueOptionService", portName = "PolicyIssueOptionPort", wsdlLocation = "WEB-INF/wsdl/PolicyIssueOptionService.wsdl")
public class PolicyIssueOptionDelegate {

	com.userbo.PolicyIssueOption policyIssueOption = new com.userbo.PolicyIssueOption();

	public String receiveIssuePolicy(@WebParam(name="context") String context, @WebParam(name="paymentType") String paymentType) {
		return policyIssueOption.receiveIssuePolicy(context, paymentType);
	}

}
