package com.userbo;

import javax.jws.WebParam;

@javax.jws.WebService(targetNamespace = "http://userbo.com/", serviceName = "FinanceAgreementOptionService", portName = "FinanceAgreementOptionPort", wsdlLocation = "WEB-INF/wsdl/FinanceAgreementOptionService.wsdl")
public class FinanceAgreementOptionDelegate {

	com.userbo.FinanceAgreementOption financeAgreementOption = new com.userbo.FinanceAgreementOption();

	public String receiveAgreementQuoteNumber(@WebParam(name="ipfsQuoteNumber") String ipfsQuoteNumber, @WebParam(name="paymentType") String paymentType) {
		return financeAgreementOption
				.receiveAgreementQuoteNumber(ipfsQuoteNumber, paymentType);
	}

}