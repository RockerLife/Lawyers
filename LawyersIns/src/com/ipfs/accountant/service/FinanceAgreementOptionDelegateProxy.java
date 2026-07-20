package com.ipfs.accountant.service;

public class FinanceAgreementOptionDelegateProxy implements com.ipfs.accountant.service.FinanceAgreementOptionDelegate {
  private String _endpoint = null;
  private com.ipfs.accountant.service.FinanceAgreementOptionDelegate financeAgreementOptionDelegate = null;
  
  public FinanceAgreementOptionDelegateProxy() {
    _initFinanceAgreementOptionDelegateProxy();
  }
  
  public FinanceAgreementOptionDelegateProxy(String endpoint) {
    _endpoint = endpoint;
    _initFinanceAgreementOptionDelegateProxy();
  }
  
  private void _initFinanceAgreementOptionDelegateProxy() {
    try {
      financeAgreementOptionDelegate = (new com.ipfs.accountant.service.FinanceAgreementOptionServiceLocator()).getFinanceAgreementOptionPort();
      if (financeAgreementOptionDelegate != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)financeAgreementOptionDelegate)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)financeAgreementOptionDelegate)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (financeAgreementOptionDelegate != null)
      ((javax.xml.rpc.Stub)financeAgreementOptionDelegate)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.ipfs.accountant.service.FinanceAgreementOptionDelegate getFinanceAgreementOptionDelegate() {
    if (financeAgreementOptionDelegate == null)
      _initFinanceAgreementOptionDelegateProxy();
    return financeAgreementOptionDelegate;
  }
  
  public java.lang.String receiveAgreementQuoteNumber(java.lang.String ipfsQuoteNumber, java.lang.String paymentType) throws java.rmi.RemoteException{
    if (financeAgreementOptionDelegate == null)
      _initFinanceAgreementOptionDelegateProxy();
    return financeAgreementOptionDelegate.receiveAgreementQuoteNumber(ipfsQuoteNumber, paymentType);
  }
  
  
}