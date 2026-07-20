package com.ipfs.service;

public class IIPFSServiceProxy implements IIPFSService {
  private String _endpoint = null;
  private IIPFSService iIPFSService = null;
  
  public IIPFSServiceProxy() {
    _initIIPFSServiceProxy();
  }
  
  public IIPFSServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initIIPFSServiceProxy();
  }
  
  private void _initIIPFSServiceProxy() {
    try {
      iIPFSService = (new IPFSServiceLocator()).getBasicHttpBinding_IIPFSService();
      if (iIPFSService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iIPFSService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iIPFSService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iIPFSService != null)
      ((javax.xml.rpc.Stub)iIPFSService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public IIPFSService getIIPFSService() {
    if (iIPFSService == null)
      _initIIPFSServiceProxy();
    return iIPFSService;
  }
  
  public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkPFARetrieveResponse PFARetrieval(java.lang.String quoteKey) throws java.rmi.RemoteException{
    if (iIPFSService == null)
      _initIIPFSServiceProxy();
    return iIPFSService.PFARetrieval(quoteKey);
  }
  
  public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkQuoteListResponse getQuoteData(java.util.Calendar startDate, java.util.Calendar endDate, java.lang.Integer premiumFrom, java.lang.Integer premiumTo) throws java.rmi.RemoteException{
    if (iIPFSService == null)
      _initIIPFSServiceProxy();
    return iIPFSService.getQuoteData(startDate, endDate, premiumFrom, premiumTo);
  }
  
  public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkQuoteListResponse getQuoteDataByQuoteNumber(java.lang.String quoteNumber) throws java.rmi.RemoteException{
    if (iIPFSService == null)
      _initIIPFSServiceProxy();
    return iIPFSService.getQuoteDataByQuoteNumber(quoteNumber);
  }
  
  public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_CustomLinkSubmitStartEsignResponse startESign(java.lang.String quoteNumber, java.lang.String batchID, org.datacontract.schemas._2004._07.IntegrationSDK_Model.RecurringACH recurringACH, org.datacontract.schemas._2004._07.IntegrationSDK_Model.InsuredEForms insuredEForms, org.datacontract.schemas._2004._07.IntegrationSDK_Model.ESign eSign) throws java.rmi.RemoteException{
    if (iIPFSService == null)
      _initIIPFSServiceProxy();
    return iIPFSService.startESign(quoteNumber, batchID, recurringACH, insuredEForms, eSign);
  }
  
  public java.lang.String submitQuote(org.datacontract.schemas._2004._07.IntegrationSDK_Model.Insured insured, org.datacontract.schemas._2004._07.IntegrationSDK_Model.Agent agent, org.datacontract.schemas._2004._07.IntegrationSDK_Model.Policy[] policies, org.datacontract.schemas._2004._07.IntegrationSDK_Model.Details details, org.datacontract.schemas._2004._07.IntegrationSDK_Model.Communication comm) throws java.rmi.RemoteException{
    if (iIPFSService == null)
      _initIIPFSServiceProxy();
    return iIPFSService.submitQuote(insured, agent, policies, details, comm);
  }
  
  public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkAgreementListResponse getAgreementDataByDate(java.util.Calendar startDate, java.util.Calendar endDate, java.lang.Boolean showTransactionHistory, java.lang.Boolean showPaymentSchedule, java.lang.Boolean showPolices, java.lang.Boolean showDisbursements, java.lang.Boolean showMailings) throws java.rmi.RemoteException{
    if (iIPFSService == null)
      _initIIPFSServiceProxy();
    return iIPFSService.getAgreementDataByDate(startDate, endDate, showTransactionHistory, showPaymentSchedule, showPolices, showDisbursements, showMailings);
  }
  
  public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkAgreementListResponse getAgreementData(java.lang.Boolean showTransactionHistory, java.lang.Boolean showPaymentSchedule, java.lang.Boolean showPolices, java.lang.Boolean showDisbursements, java.lang.Boolean showMailings, java.lang.String quoteNumber) throws java.rmi.RemoteException{
    if (iIPFSService == null)
      _initIIPFSServiceProxy();
    return iIPFSService.getAgreementData(showTransactionHistory, showPaymentSchedule, showPolices, showDisbursements, showMailings, quoteNumber);
  }
  
  public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkFundingListResponse getFundingData(java.util.Calendar startDate, java.util.Calendar endDate, org.datacontract.schemas._2004._07.IntegrationSDK.EnumsFundingDateRangeFilter dateRangeFilter, org.datacontract.schemas._2004._07.IntegrationSDK.EnumsFundingMethod method, org.datacontract.schemas._2004._07.IntegrationSDK.EnumsFundingSource source, java.lang.Boolean getAssociatedEntities) throws java.rmi.RemoteException{
    if (iIPFSService == null)
      _initIIPFSServiceProxy();
    return iIPFSService.getFundingData(startDate, endDate, dateRangeFilter, method, source, getAssociatedEntities);
  }
  
  public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkFundingListResponse getFundingDataByQuoteNumber(java.lang.String quoteNumber) throws java.rmi.RemoteException{
    if (iIPFSService == null)
      _initIIPFSServiceProxy();
    return iIPFSService.getFundingDataByQuoteNumber(quoteNumber);
  }
  
  public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkMailingImageResponse getMailingImage(java.lang.String accountNumber, java.lang.String mailingName, java.lang.String sentTo, java.util.Calendar sentDate) throws java.rmi.RemoteException{
    if (iIPFSService == null)
      _initIIPFSServiceProxy();
    return iIPFSService.getMailingImage(accountNumber, mailingName, sentTo, sentDate);
  }
  
  public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkUpdatePolicyNumberResponse updatePolicyNumbe(java.lang.String quoteKey, java.lang.String newPolicyNumber, java.lang.String coverageType, java.math.BigDecimal premium) throws java.rmi.RemoteException{
    if (iIPFSService == null)
      _initIIPFSServiceProxy();
    return iIPFSService.updatePolicyNumbe(quoteKey, newPolicyNumber, coverageType, premium);
  }
  
  
}