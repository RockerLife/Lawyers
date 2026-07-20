package com.ach.service;

public class PolicyIssueOptionDelegateProxy implements com.ach.service.PolicyIssueOptionDelegate {
  private String _endpoint = null;
  private com.ach.service.PolicyIssueOptionDelegate policyIssueOptionDelegate = null;
  
  public PolicyIssueOptionDelegateProxy() {
    _initPolicyIssueOptionDelegateProxy();
  }
  
  public PolicyIssueOptionDelegateProxy(String endpoint) {
    _endpoint = endpoint;
    _initPolicyIssueOptionDelegateProxy();
  }
  
  private void _initPolicyIssueOptionDelegateProxy() {
    try {
      policyIssueOptionDelegate = (new com.ach.service.PolicyIssueOptionServiceLocator()).getPolicyIssueOptionPort();
      if (policyIssueOptionDelegate != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)policyIssueOptionDelegate)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)policyIssueOptionDelegate)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (policyIssueOptionDelegate != null)
      ((javax.xml.rpc.Stub)policyIssueOptionDelegate)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.ach.service.PolicyIssueOptionDelegate getPolicyIssueOptionDelegate() {
    if (policyIssueOptionDelegate == null)
      _initPolicyIssueOptionDelegateProxy();
    return policyIssueOptionDelegate;
  }
  
  public java.lang.String receiveIssuePolicy(java.lang.String context, java.lang.String paymentType) throws java.rmi.RemoteException{
    if (policyIssueOptionDelegate == null)
      _initPolicyIssueOptionDelegateProxy();
    return policyIssueOptionDelegate.receiveIssuePolicy(context, paymentType);
  }
  
  
}