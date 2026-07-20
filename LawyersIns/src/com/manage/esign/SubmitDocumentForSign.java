package com.manage.esign;

import com.util.InetLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;

import org.tempuri.DocumentNowWcfService;
import org.tempuri.IListSignatoriesService;
import org.tempuri.ISubmitService;

import com.manage.managemetadata.functions.commonfunctions.DateUtils;
import com.util.SystemProperties;

import https.www_assuresign_net.services.documentnow.listsignatories.ArrayOfSignatoryItem;
import https.www_assuresign_net.services.documentnow.listsignatories.SignatoryItem;
import https.www_assuresign_net.services.documentnow.listsignatories.SignatoryListQuery;
import https.www_assuresign_net.services.documentnow.listsignatories.SignatoryListQueryResult;
import https.www_assuresign_net.services.documentnow.submit.ArrayOfDocumentResult;
import https.www_assuresign_net.services.documentnow.submit.ArrayOfFileDocument;
import https.www_assuresign_net.services.documentnow.submit.ArrayOfJotBlock;
import https.www_assuresign_net.services.documentnow.submit.ArrayOfParameter;
import https.www_assuresign_net.services.documentnow.submit.FileDocument;
import https.www_assuresign_net.services.documentnow.submit.JotBlock;
import https.www_assuresign_net.services.documentnow.submit.JotBlockBorderType;
import https.www_assuresign_net.services.documentnow.submit.JotBlockInputType;
import https.www_assuresign_net.services.documentnow.submit.Metadata;
import https.www_assuresign_net.services.documentnow.submit.Parameter;
import https.www_assuresign_net.services.documentnow.submit.SupportedFileType;
import https.www_assuresign_net.services.documentnow.submit.Template;
import https.www_assuresign_net.services.documentnow.submit.TermsAndConditions;
import https.www_assuresign_net.services.documentnow.submit.TypedJotBlockFontNameType;
import https.www_assuresign_net.services.documentnow.submit.TypedJotBlockFontSizeType;
import https.www_assuresign_net.services.documentnow.submit.TypedJotBlockHorzAlignmentType;
import https.www_assuresign_net.services.documentnow.submit.TypedJotBlockProperties;
import https.www_assuresign_net.services.documentnow.submit.TypedJotBlockVertAlignmentType;
import https.www_assuresign_net.services.documentnow.submit.UnderlyingFile;

/**
 * 
 * @author Amit Jain, Outline Systems.
 * Created on 26 Sep, 2010
 *
 */


public class SubmitDocumentForSign {
	private static final InetLogger logger = InetLogger.getInetLogger(SubmitDocumentForSign.class);

	/* Submit */
	public ArrayOfDocumentResult submit(String contextIdentifier,
			String userName, String templateId,
			HashMap<String, String> parameterMapping, byte[] fileContent,int pageNumber, Map<String, Double> xyMap,
			String signatoryEmail, String policyEffectiveDate) throws DatatypeConfigurationException {

		/*// Initialize the metadata
		Metadata metadata = getMetadata(userName, policyEffectiveDate);
		
//		File file = new File("D:\\sample.pdf");
//		java.io.FileOutputStream fout;
//		try {
//			fout = new java.io.FileOutputStream(file);
//			fout.write(fileContent);
//			fout.close();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		

		// Initialize parameters
		ArrayOfParameter parameters = null;
		if (parameterMapping != null) {
			parameters = new ArrayOfParameter();
			for (String parameterName : parameterMapping.keySet()) {
				Parameter parameter = new Parameter();
				parameter.setName(parameterName);
				parameter.setValue(parameterMapping.get(parameterName));
				parameters.getParameter().add(parameter);
			}
		}
		// Initialize template
		Template template = new Template();
		template.setId(templateId);
		if (parameterMapping != null) {
			template.setParameters(parameters);
		}

		// Initialize underlying file information
		UnderlyingFile underlyingFile = new UnderlyingFile();
		underlyingFile.setData(fileContent);
		underlyingFile.setFileType(SupportedFileType.PDF);

		// Initialize document
		FileDocument document = new FileDocument();
		document.setUnderlyingFile(underlyingFile);
		document.setContextIdentifier(contextIdentifier);
		document.setMetadata(metadata);
		document.setTemplate(template);
		document.setParseDocument(false);

		// Specify dynamic JotBlock information
		ArrayOfJotBlock dynamicJotBlocks = getJotBlocks(signatoryEmail, pageNumber, xyMap);

		document.setJotBlocks(dynamicJotBlocks);

		// Initialize document array
		ArrayOfFileDocument documents = new ArrayOfFileDocument();
		documents.getFileDocument().add(document);

		ISubmitService service = null;

		// Instantiate mtom-enabled submit service

		try {
			service = new DocumentNowWcfService()
					.getBasicHttpBindingISubmitService();
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}

		// Enable MTOM
		Object port = service;
		SOAPBinding binding = (SOAPBinding) ((BindingProvider) port)
				.getBinding();
		binding.setMTOMEnabled(true);
		// Submit request via MTOM ArrayOfDocumentResult

		ArrayOfDocumentResult results = service.submitWithFile(documents);
		return results;*/
		return submit(contextIdentifier, userName, templateId, parameterMapping, fileContent, pageNumber,  xyMap, signatoryEmail, policyEffectiveDate, null);
	}
	
	public ArrayOfDocumentResult submit(String contextIdentifier,
			String userName, String templateId,
			HashMap<String, String> parameterMapping, byte[] fileContent,int pageNumber, Map<String, Double> xyMap,
			String signatoryEmail, String policyEffectiveDate, String docType) throws DatatypeConfigurationException {

		// Initialize the metadata
		Metadata metadata = getMetadata(userName, policyEffectiveDate);
		
//		File file = new File("D:\\sample.pdf");
//		java.io.FileOutputStream fout;
//		try {
//			fout = new java.io.FileOutputStream(file);
//			fout.write(fileContent);
//			fout.close();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		

		// Initialize parameters
		ArrayOfParameter parameters = null;
		if (parameterMapping != null) {
			parameters = new ArrayOfParameter();
			for (String parameterName : parameterMapping.keySet()) {
				Parameter parameter = new Parameter();
				parameter.setName(parameterName);
				parameter.setValue(parameterMapping.get(parameterName));
				parameters.getParameter().add(parameter);
			}
		}
		// Initialize template
		Template template = new Template();
		template.setId(templateId);
		if (parameterMapping != null) {
			template.setParameters(parameters);
		}

		// Initialize underlying file information
		UnderlyingFile underlyingFile = new UnderlyingFile();
		underlyingFile.setData(fileContent);
		underlyingFile.setFileType(SupportedFileType.PDF);

		// Initialize document
		FileDocument document = new FileDocument();
		document.setUnderlyingFile(underlyingFile);
		document.setContextIdentifier(contextIdentifier);
		document.setMetadata(metadata);
		document.setTemplate(template);
		document.setParseDocument(false);

		// Specify dynamic JotBlock information
		ArrayOfJotBlock dynamicJotBlocks = null;
		if(null == docType)
			dynamicJotBlocks = getJotBlocks(signatoryEmail, pageNumber, xyMap);
		else 
			dynamicJotBlocks = getFormJotBlocks(signatoryEmail, pageNumber, xyMap);
		
		document.setJotBlocks(dynamicJotBlocks);

		// Initialize document array
		ArrayOfFileDocument documents = new ArrayOfFileDocument();
		documents.getFileDocument().add(document);

		ISubmitService service = null;

		// Instantiate mtom-enabled submit service

		try {
			service = new DocumentNowWcfService()
					.getBasicHttpBindingISubmitService();
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}

		// Enable MTOM
		Object port = service;
		SOAPBinding binding = (SOAPBinding) ((BindingProvider) port)
				.getBinding();
		binding.setMTOMEnabled(true);
		// Submit request via MTOM ArrayOfDocumentResult

		ArrayOfDocumentResult results = service.submitWithFile(documents);
		return results;
	}


	public String buildAssureSignURL(String contextIdentifier,
			String documentId, String docAuthToken, String redirectUrl) throws Exception {

		ArrayOfSignatoryItem signatoryItems = null;

		signatoryItems = getSignatoryItems(contextIdentifier, documentId,
				docAuthToken);

		// If the result is NULL, throw an exception
		if (signatoryItems == null) {
			throw new Exception("ListSignatoryItems query return NULL.");
		}
		// If the result did not include any templates, throw an exception
		if ((signatoryItems.getSignatoryItem() == null)
				|| (signatoryItems.getSignatoryItem().size() == 0)) {
			throw new Exception(
					"No SignatoryItem were found for specific user.");
		}

		SignatoryItem signItem = signatoryItems.getSignatoryItem().get(0);

		String signatoryID = signItem.getSignatoryId();
		String signatoryAuthToken = signItem.getSignatoryAuthToken();
		String assuresignBaseURL = SystemProperties.getInstance().getString("esign.doc.url");		

		StringBuffer assuresignURL = new StringBuffer();
		
		assuresignURL
				.append(assuresignBaseURL)
				.append("/Landing.aspx?i=")
				.append(documentId)
				.append("&a=")
				.append(docAuthToken)
				.append("&s=")
				.append(signatoryID)
				.append("&sa=")
				.append(signatoryAuthToken)
				.append("&redirect=")
				.append(redirectUrl);

		return assuresignURL.toString();

	}

	private ArrayOfSignatoryItem getSignatoryItems(String contextIdentifier,
			String documentId, String docAuthToken) throws Exception {

		ArrayOfSignatoryItem signItems = null;
		SignatoryListQueryResult queryResult = null;
		IListSignatoriesService signatoryService = null;

		SignatoryListQuery query = new SignatoryListQuery();

		query.setContextIdentifier(contextIdentifier);
		query.setId(documentId);
		query.setAuthToken(docAuthToken);

		try {
			signatoryService = new DocumentNowWcfService()
					.getBasicHttpBindingIListSignatoriesService();
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}

		queryResult = signatoryService.listSignatories(query);
		signItems = queryResult.getSignatories();

		return signItems;

	}

	private Metadata getMetadata(String userName, String policyEffectiveDate) {

		DatatypeFactory factory = null;
		try {
			factory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			
			logger.error("Unexpected error", e);
		}
		// Initialize additional terms and conditions
		TermsAndConditions termsAndConditions = new TermsAndConditions();
		termsAndConditions
				.setAdditionalAgreementStatement("Additional agreement statement");
		termsAndConditions
				.setAdditionalComplianceStatement("Additional compliance statement");
		termsAndConditions
				.setAdditionalExtendedDisclosures("Additional extended disclosures");
		// Initialize metadata
		Metadata metadata = new Metadata();
		metadata.setUserName(userName);
		metadata.setDocumentName("LawyerPolicy");
		
		Date dtDocumentExpiryDate = null;
		int docExpiryYear = 0;
		int docExpiryMonth = 0;
		int docExpiryDay = 0;
		
		if(policyEffectiveDate != null){
			
			Date dtPolicyEffectiveDate= DateUtils.convertStringToDate(policyEffectiveDate, "MM/dd/yyyy");
			dtDocumentExpiryDate = DateUtils.addYearsToDate(dtPolicyEffectiveDate, 1);			
		}else{
			dtDocumentExpiryDate = DateUtils.addYearsToDate(DateUtils.getTodaysDate(), 1);
		}
		
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(dtDocumentExpiryDate);
        docExpiryYear = cal.get(Calendar.YEAR);
        docExpiryMonth = cal.get(Calendar.MONTH) + 1;
        docExpiryDay = cal.get(Calendar.DAY_OF_MONTH);
		
		
		metadata.setExpirationDate(factory.newXMLGregorianCalendar(docExpiryYear, docExpiryMonth,
				docExpiryDay, 23, 59, 59, 0, 0));
		
//		metadata.setExpirationDate(factory.newXMLGregorianCalendar(2012, 12,
//				31, 23, 59, 59, 0, 0));
		metadata.setOrderNumber("1234");
		metadata.setTermsAndConditions(termsAndConditions);

		return metadata;
	}

	private ArrayOfJotBlock getJotBlocks(String signatoryEmail,int pageNumber, Map<String, Double> xyMap) {

		// Specify dynamic JotBlock information
		ArrayOfJotBlock dynamicJotBlocks = new ArrayOfJotBlock();

		String signedDate = new SimpleDateFormat("MM/dd/yyyy").format(DateUtils.getTodaysDate());
		// Specify the LotBlock for signature.
		
		float yPos = (float) Double.parseDouble(xyMap.get("yPos").toString());
		float xPos = (float) Double.parseDouble(xyMap.get("xPos").toString());
		
		JotBlock customerJotBlock = new JotBlock();
		customerJotBlock.setName("CustomerSign");
		customerJotBlock.setInputType(JotBlockInputType.SIGNATORY);
		customerJotBlock.setCertified(true);
		customerJotBlock.setRequired(true);
		short s = (short)pageNumber;
		customerJotBlock.setPage(s);
		customerJotBlock.setHeight(0.04f);
		customerJotBlock.setWidth(0.30f);	
		
		customerJotBlock.setPositionY(yPos);
		if(s==3 || s==4)
			customerJotBlock.setPositionX(0.14f);
		else
			customerJotBlock.setPositionX(0.20f);
		
		/*if(s==3 || s==4)
		{
			customerJotBlock.setPositionX(0.14f);
			customerJotBlock.setPositionY(0.59f);
		}
		if(s==5)
		{
			customerJotBlock.setPositionX(0.20f);
			customerJotBlock.setPositionY(0.70f); //36->602 42->640 divide 1650
		}
		if(s==7)
		{
			customerJotBlock.setPositionX(0.20f);
			customerJotBlock.setPositionY(0.36f);	
		}*/
		
		
		customerJotBlock.setSignatoryEmail(signatoryEmail);
		customerJotBlock.setBorder(JotBlockBorderType.ALL);
		TypedJotBlockProperties typeJotBlock = new TypedJotBlockProperties();
		typeJotBlock.setFontName(TypedJotBlockFontNameType.TIMES_NEW_ROMAN);
		typeJotBlock.setFontBold(true);		
		typeJotBlock.setFontSize(TypedJotBlockFontSizeType.TWELVE);
		typeJotBlock
				.setHorizontalAlignment(TypedJotBlockHorzAlignmentType.CENTER);
		typeJotBlock
				.setVerticalAlignment(TypedJotBlockVertAlignmentType.CENTER);
		typeJotBlock.setWordWrap(true);
		customerJotBlock.setTypedProperties(typeJotBlock);

		dynamicJotBlocks.getJotBlock().add(customerJotBlock);

		// second jot block for date.
		JotBlock customerJotBlock1 = new JotBlock();
		customerJotBlock1.setName("SignDate");
		customerJotBlock1.setInputType(JotBlockInputType.FIXED);
		customerJotBlock1.setCertified(false);
		customerJotBlock1.setRequired(false);
		customerJotBlock1.setPage(s);
		customerJotBlock1.setHeight(0.03f);
		customerJotBlock1.setWidth(0.17f);
//		customerJotBlock1.setPositionX(0.78f);
//		customerJotBlock1.setPositionY(0.67f);
		yPos = yPos + 0.07f;
		customerJotBlock1.setPositionY(yPos);
		if(s==3 || s==4)
			customerJotBlock1.setPositionX(0.60f);
		else
			customerJotBlock1.setPositionX(0.73f);
		
		/*if(s==3 || s==4)
		{
			customerJotBlock1.setPositionX(0.60f);
			customerJotBlock1.setPositionY(0.71f);
		}
		if(s==5)
		{
			customerJotBlock1.setPositionX(0.73f);
			customerJotBlock1.setPositionY(0.70f);
		}
		if(s==7)
		{
			customerJotBlock1.setPositionX(0.73f);
			customerJotBlock1.setPositionY(0.42f);
		}*/
		customerJotBlock1.setSignatoryEmail(signatoryEmail);
		customerJotBlock1.setBorder(JotBlockBorderType.ALL);
		TypedJotBlockProperties typeJotBlock1 = new TypedJotBlockProperties();
		typeJotBlock1.setFontName(TypedJotBlockFontNameType.TIMES_NEW_ROMAN);
		typeJotBlock1.setFontBold(true);
		typeJotBlock1.setFontSize(TypedJotBlockFontSizeType.TWELVE);
		typeJotBlock1
				.setHorizontalAlignment(TypedJotBlockHorzAlignmentType.CENTER);
		typeJotBlock1
				.setVerticalAlignment(TypedJotBlockVertAlignmentType.CENTER);
		typeJotBlock1.setValue(signedDate);
		typeJotBlock1.setWordWrap(false);
		customerJotBlock1.setTypedProperties(typeJotBlock1);
		dynamicJotBlocks.getJotBlock().add(customerJotBlock1);

		return dynamicJotBlocks;
	}

	public byte[] getBytesFromFile(File file) {
		java.io.InputStream is = null;
		try {
			is = new java.io.FileInputStream(file);
		} catch (FileNotFoundException e) {
			logger.error("Unexpected error", e);
		}
		// Get the size of the file
		long length = file.length();
		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];
		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		try {
			while (offset < bytes.length
					&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
		} catch (IOException e) {
			logger.error("Unexpected error", e);
		}
		// Close the input stream and return bytes
		try {
			is.close();
		} catch (IOException e) {
			logger.error("Unexpected error", e);
		}

		return bytes;

	}
	
	private ArrayOfJotBlock getFormJotBlocks(String signatoryEmail,int pageNumber, Map<String, Double> xyMap) {

		// Specify dynamic JotBlock information
		ArrayOfJotBlock dynamicJotBlocks = new ArrayOfJotBlock();

		String signedDate = new SimpleDateFormat("MM/dd/yyyy").format(DateUtils.getTodaysDate());
		// Specify the otBlock for signature.
		
		float yPos = (float) Double.parseDouble(xyMap.get("yPos").toString());
		float xPos = (float) Double.parseDouble(xyMap.get("xPos").toString());
		
		JotBlock customerJotBlock = new JotBlock();
		customerJotBlock.setName("CustomerSign");
		customerJotBlock.setInputType(JotBlockInputType.SIGNATORY);
		customerJotBlock.setCertified(true);
		customerJotBlock.setRequired(true);
		short s = (short)pageNumber;
		customerJotBlock.setPage(s);
		customerJotBlock.setHeight(0.04f);
		customerJotBlock.setWidth(0.30f);	
		
		//yPos = yPos + 0.05f;
		customerJotBlock.setPositionY(yPos);
		customerJotBlock.setPositionX(0.20f);
		//customerJotBlock.setPositionX(xPos);
				
		customerJotBlock.setSignatoryEmail(signatoryEmail);
		customerJotBlock.setBorder(JotBlockBorderType.ALL);
		TypedJotBlockProperties typeJotBlock = new TypedJotBlockProperties();
		typeJotBlock.setFontName(TypedJotBlockFontNameType.TIMES_NEW_ROMAN);
		typeJotBlock.setFontBold(true);		
		typeJotBlock.setFontSize(TypedJotBlockFontSizeType.TWELVE);
		typeJotBlock
				.setHorizontalAlignment(TypedJotBlockHorzAlignmentType.CENTER);
		typeJotBlock
				.setVerticalAlignment(TypedJotBlockVertAlignmentType.CENTER);
		typeJotBlock.setWordWrap(true);
		customerJotBlock.setTypedProperties(typeJotBlock);

		dynamicJotBlocks.getJotBlock().add(customerJotBlock);

		// second jot block for date.
		JotBlock customerJotBlock1 = new JotBlock();
		customerJotBlock1.setName("SignDate");
		customerJotBlock1.setInputType(JotBlockInputType.FIXED);
		customerJotBlock1.setCertified(false);
		customerJotBlock1.setRequired(false);
		customerJotBlock1.setPage(s);
		customerJotBlock1.setHeight(0.03f);
		customerJotBlock1.setWidth(0.17f);
		
		yPos = yPos + 0.06f;
		customerJotBlock1.setPositionY(yPos);
		customerJotBlock1.setPositionX(0.73f);
		
		customerJotBlock1.setSignatoryEmail(signatoryEmail);
		customerJotBlock1.setBorder(JotBlockBorderType.ALL);
		TypedJotBlockProperties typeJotBlock1 = new TypedJotBlockProperties();
		typeJotBlock1.setFontName(TypedJotBlockFontNameType.TIMES_NEW_ROMAN);
		typeJotBlock1.setFontBold(true);
		typeJotBlock1.setFontSize(TypedJotBlockFontSizeType.TWELVE);
		typeJotBlock1
				.setHorizontalAlignment(TypedJotBlockHorzAlignmentType.CENTER);
		typeJotBlock1
				.setVerticalAlignment(TypedJotBlockVertAlignmentType.CENTER);
		typeJotBlock1.setValue(signedDate);
		typeJotBlock1.setWordWrap(false);
		customerJotBlock1.setTypedProperties(typeJotBlock1);
		dynamicJotBlocks.getJotBlock().add(customerJotBlock1);

		return dynamicJotBlocks;
	}

}
