/**
 * IIPFSService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ipfs.service;

public interface IIPFSService extends java.rmi.Remote {
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkPFARetrieveResponse PFARetrieval(java.lang.String quoteKey) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkQuoteListResponse getQuoteData(java.util.Calendar startDate, java.util.Calendar endDate, java.lang.Integer premiumFrom, java.lang.Integer premiumTo) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkQuoteListResponse getQuoteDataByQuoteNumber(java.lang.String quoteNumber) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_CustomLinkSubmitStartEsignResponse startESign(java.lang.String quoteNumber, java.lang.String batchID, org.datacontract.schemas._2004._07.IntegrationSDK_Model.RecurringACH recurringACH, org.datacontract.schemas._2004._07.IntegrationSDK_Model.InsuredEForms insuredEForms, org.datacontract.schemas._2004._07.IntegrationSDK_Model.ESign eSign) throws java.rmi.RemoteException;
    public java.lang.String submitQuote(org.datacontract.schemas._2004._07.IntegrationSDK_Model.Insured insured, org.datacontract.schemas._2004._07.IntegrationSDK_Model.Agent agent, org.datacontract.schemas._2004._07.IntegrationSDK_Model.Policy[] policies, org.datacontract.schemas._2004._07.IntegrationSDK_Model.Details details, org.datacontract.schemas._2004._07.IntegrationSDK_Model.Communication comm) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkAgreementListResponse getAgreementDataByDate(java.util.Calendar startDate, java.util.Calendar endDate, java.lang.Boolean showTransactionHistory, java.lang.Boolean showPaymentSchedule, java.lang.Boolean showPolices, java.lang.Boolean showDisbursements, java.lang.Boolean showMailings) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkAgreementListResponse getAgreementData(java.lang.Boolean showTransactionHistory, java.lang.Boolean showPaymentSchedule, java.lang.Boolean showPolices, java.lang.Boolean showDisbursements, java.lang.Boolean showMailings, java.lang.String quoteNumber) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkFundingListResponse getFundingData(java.util.Calendar startDate, java.util.Calendar endDate, org.datacontract.schemas._2004._07.IntegrationSDK.EnumsFundingDateRangeFilter dateRangeFilter, org.datacontract.schemas._2004._07.IntegrationSDK.EnumsFundingMethod method, org.datacontract.schemas._2004._07.IntegrationSDK.EnumsFundingSource source, java.lang.Boolean getAssociatedEntities) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkFundingListResponse getFundingDataByQuoteNumber(java.lang.String quoteNumber) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkMailingImageResponse getMailingImage(java.lang.String accountNumber, java.lang.String mailingName, java.lang.String sentTo, java.util.Calendar sentDate) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.IPFS_DTO.OSI_DataLinkUpdatePolicyNumberResponse updatePolicyNumbe(java.lang.String quoteKey, java.lang.String newPolicyNumber, java.lang.String coverageType, java.math.BigDecimal premium) throws java.rmi.RemoteException;
}
