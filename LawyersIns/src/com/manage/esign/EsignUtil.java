package com.manage.esign;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tempuri.DocumentNowWcfService;
import org.tempuri.IListParametersService;
import org.tempuri.IListTemplatesService;

import com.util.InetLogger;

import https.www_assuresign_net.services.documentnow.listparameters.ParameterItemBase;
import https.www_assuresign_net.services.documentnow.listparameters.ParameterListException;
import https.www_assuresign_net.services.documentnow.listparameters.ParameterListQuery;
import https.www_assuresign_net.services.documentnow.listparameters.ParameterListQueryResult;
import https.www_assuresign_net.services.documentnow.listparameters.TypedParameterItem;
import https.www_assuresign_net.services.documentnow.listtemplates.ListTemplates;
import https.www_assuresign_net.services.documentnow.listtemplates.TemplateItem;
import https.www_assuresign_net.services.documentnow.listtemplates.TemplateListException;
import https.www_assuresign_net.services.documentnow.listtemplates.TemplateListQuery;
import https.www_assuresign_net.services.documentnow.listtemplates.TemplateListQueryResult;
import https.www_assuresign_net.services.documentnow.submit.ArrayOfDocumentResult;

public class EsignUtil {

	private static InetLogger logger = InetLogger.getInetLogger(EsignUtil.class);
	
	
	public byte[] getDocument(String url) throws Exception
    {
		
          File file = new File(url);
		  try (InputStream in = new FileInputStream(file);
				  ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			  final int BUF_SIZE = 1 << 8; //1KiB buffer
			  byte[] buffer = new byte[BUF_SIZE];
			  int bytesRead;
			  while((bytesRead = in.read(buffer)) > -1) {
					out.write(buffer, 0, bytesRead);
			  }
			  return out.toByteArray();
		  }

    }
	
	
	public byte[] getDocument(String assuresignURL, String docID, String docAuthToken) throws Exception
    {
          URL u = new URL(assuresignURL);
          URLConnection uc = u.openConnection();
          
          PrintWriter pw = null;          
          String str = "i="+docID+"&a="+docAuthToken;          

          uc.setDoOutput(true);
          uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

          pw = new PrintWriter(uc.getOutputStream());
          pw.println(str);
          pw.close();

          InputStream in = null;
          in =uc.getInputStream();
          
          ByteArrayOutputStream out = new ByteArrayOutputStream();
          final int BUF_SIZE = 1 << 8; //1KiB buffer
          byte[] buffer = new byte[BUF_SIZE];
          int bytesRead = -1;
          while((bytesRead = in.read(buffer)) > -1) {
                out.write(buffer, 0, bytesRead);
          }
          
          in.close();
          byte[] fileBytes = out.toByteArray();          
          out.close();
         
          
          
//        FileOutputStream fout = new FileOutputStream("C://result.pdf");
//        fout.write(fileBytes);
//        fout.close();
          
        return fileBytes;       

    }
	
	
	public String doSignature(String contextIdentifier,String userName,Map<String, String> data, byte[] fileContent,int pageNumber, Map<String, Double> xyMap, String redirectUrl)
			throws Exception {

		System.clearProperty("java.protocol.handler.pkgs");

		// Make call to retrieve a list of available templates for the specified
		// user
		TemplateListQueryResult templateResult;
		try {
			templateResult = listTemplates(contextIdentifier, userName);
		} catch (Exception e) {
			logger.error("Unexpected error", e);
			throw e;
		}
		// If the result is NULL, throw an exception
		if (templateResult == null) {
			throw new Exception("Template list query return NULL.");
		}
		// Process any exceptions returned from the call
		if (templateResult.getExceptions() != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Exceptions returned from template list query:/r/n");
			for (TemplateListException e : templateResult.getExceptions()
					.getTemplateListException()) {
				String exceptionMessage = e.getSeverity() + " Exception: '"
						+ e.getValue() + "'/r/n";
				sb.append(exceptionMessage);
			}
			throw new Exception(sb.toString());
		}

		// If the result did not include any templates, throw an exception
		if ((templateResult.getTemplates() == null)
				|| (templateResult.getTemplates().getTemplateItem().size() == 0)) {
			throw new Exception(
					"No available templates were found for the specified user.");
		}

		int i = 0;
		TemplateItem selectedTemplate = null;
		String templateName = "LawyerPolicy";
		List<TemplateItem> templates = templateResult.getTemplates()
				.getTemplateItem();
		while ((selectedTemplate == null) && (i < templates.size())) {
			TemplateItem currentTemplate = templates.get(i);
			if (currentTemplate.getName().equalsIgnoreCase(templateName)) {
				selectedTemplate = currentTemplate;
			}
			i++;
		}

		TemplateItem templateItem = (selectedTemplate == null) ? templateResult
				.getTemplates().getTemplateItem().get(0) : selectedTemplate;

		// Get a reference to the list of parameters returned from the query (if
		// any were returned)

		List<ParameterItemBase> parameterItems = null;

		parameterItems = getTemplateParam(contextIdentifier, userName,
				templateItem);

		// Populate parameter values for the parameters (typically these would
		// be populated dynamically from a Form or database
		// but for simplicity the mapping has been hard-coded to pull from a
		// properties file in this example
		HashMap<String, String> parameterMapping = null;
		if (parameterItems != null) {
			parameterMapping = new HashMap<String, String>();
			try {
				parameterMapping = populateParameters(parameterItems, data);
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}
		}
		// Submit document using the template file as the underlying document
		ArrayOfDocumentResult submitResults = null;
		try {
			submitResults = new SubmitDocumentForSign().submit(
					contextIdentifier, userName, templateItem.getId(),
					parameterMapping, fileContent, pageNumber,  xyMap, data
							.get("AccountEmail"), data.get("PolicyEffectiveDate"));
			
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}

		// If the result is NULL, throw an exception
		if (submitResults == null) {
			throw new Exception("Submitwith file query return NULL.");
		}
		// If the result did not include any templates, throw an exception
		if ((submitResults.getDocumentResult() == null)
				|| (submitResults.getDocumentResult().size() == 0)) {
			throw new Exception("No file were submited.");
		}

		String documentId = submitResults.getDocumentResult().get(0).getId();
		String docAuthToken = submitResults.getDocumentResult().get(0)
				.getAuthToken();

		String assureSignURL = new SubmitDocumentForSign().buildAssureSignURL(
				contextIdentifier, documentId, docAuthToken, redirectUrl);
		
		logger.debug(assureSignURL);
		return assureSignURL;

	}

	private TemplateListQueryResult listTemplates(String contextIdentifier,
			String userName) {
		// Initialize template list query
		TemplateListQuery query = new TemplateListQuery();
		query.setContextIdentifier(contextIdentifier);
		query.setUserName(userName);
		// Initialize request
		ListTemplates request = new ListTemplates();
		request.setTemplateListQuery(query);
		// Initialize DocumentNow service
		IListTemplatesService service = null;
		try {
			service = new DocumentNowWcfService()
					.getBasicHttpBindingIListTemplatesService();
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
		// Make list templates request
		TemplateListQueryResult result = service.listTemplates(query);
		return result;

	}
	

	/* List Parameters */
	
	
	private ParameterListQueryResult listParameters(String contextIdentifier,
			String userName, String templateId) {
		// Initialize parameter list query
		ParameterListQuery query = new ParameterListQuery();
		query.setContextIdentifier(contextIdentifier);
		query.setUserName(userName);
		query.setTemplateId(templateId);
		// Initialize DocumentNow service
		IListParametersService service = null;
		try {
			service = new DocumentNowWcfService()
					.getBasicHttpBindingIListParametersService();
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
		// Make list parameters request
		ParameterListQueryResult result = service.listParameters(query);
		return result;
	}

	private List<ParameterItemBase> getTemplateParam(String contextIdentifier,
			String userName, TemplateItem templateItem) throws Exception {

		// Make a call to retrieve a list of the parameters for the selected
		// template
		ParameterListQueryResult parameterResult;
		try {
			parameterResult = listParameters(contextIdentifier, userName,
					templateItem.getId());
		} catch (Exception e) {
			logger.error("Unexpected error", e);
			throw e;
		}

		// If the result is NULL, throw an exception
		if (parameterResult == null) {
			throw new Exception("Parameter list query returned NULL.");
		}
		// Process any exceptions returned from the call
		if (parameterResult.getExceptions() != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Exceptions returned from parameter list query:/r/n");
			for (ParameterListException e : parameterResult.getExceptions()
					.getParameterListException()) {
				String exceptionMessage = e.getSeverity() + "Exception: '"
						+ e.getValue() + "'/r/n";
				sb.append(exceptionMessage);
			}
			throw new Exception(sb.toString());
		}
		// Get a reference to the list of parameters returned from the query (if
		// any were returned)

		List<ParameterItemBase> parameterItems = null;
		if (parameterResult.getParameters() != null) {
			parameterItems = parameterResult.getParameters()
					.getWrittenParameterItemOrTypedParameterItem();
		}

		return parameterItems;
	}

	private HashMap<String, String> populateParameters(
			List<ParameterItemBase> parameterItems, Map<String, String> data)
			throws Exception {
		HashMap<String, String> parameterMapping = new HashMap<String, String>();
		for (int i = 0; i < parameterItems.size(); i++) {
			ParameterItemBase parameterItem = parameterItems.get(i);
			if (parameterItem instanceof TypedParameterItem) {
				parameterMapping.put(parameterItem.getName(),
						getParameterValue(parameterItem.getName(), data));
			} else {
				// Written parameters cannot be populated via the DocumentNOW
				// interface, so if a template contains //a required written
				// parameter, processing cannot continue.
				if (parameterItem.isRequired()) {
					throw new Exception("Required written parameter detected.");
				}
			}
		}
		return parameterMapping;

	}

	private String getParameterValue(String parameterName,
			Map<String, String> data) {
		String parameterValue = "";
		if (parameterName.matches("Signatory 1 First Name")) {
			parameterValue = data.get("signatoryFirstName");
		} else if (parameterName.matches("Signatory 1 Last Name")) {
			parameterValue = data.get("signatoryLastName");
		} else if (parameterName.matches("Signatory 1 Full Name")) {
			parameterValue = data.get("AccountName");
		} else if (parameterName.matches("Signatory 1 Email Address")) {
			parameterValue = data.get("AccountEmail");
		} 
		
		return parameterValue;

	}
	
	public String doFormSignature(String contextIdentifier,String userName,Map<String, String> data, byte[] fileContent,int pageNumber, Map<String, Double> xyMap, String redirectUrl)
			throws Exception {

		System.clearProperty("java.protocol.handler.pkgs");

		// Make call to retrieve a list of available templates for the specified
		// user
		TemplateListQueryResult templateResult;
		try {
			templateResult = listTemplates(contextIdentifier, userName);
		} catch (Exception e) {
			logger.error("Unexpected error", e);
			throw e;
		}
		// If the result is NULL, throw an exception
		if (templateResult == null) {
			throw new Exception("Template list query return NULL.");
		}
		// Process any exceptions returned from the call
		if (templateResult.getExceptions() != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Exceptions returned from template list query:/r/n");
			for (TemplateListException e : templateResult.getExceptions()
					.getTemplateListException()) {
				String exceptionMessage = e.getSeverity() + " Exception: '"
						+ e.getValue() + "'/r/n";
				sb.append(exceptionMessage);
			}
			throw new Exception(sb.toString());
		}

		// If the result did not include any templates, throw an exception
		if ((templateResult.getTemplates() == null)
				|| (templateResult.getTemplates().getTemplateItem().size() == 0)) {
			throw new Exception(
					"No available templates were found for the specified user.");
		}

		int i = 0;
		TemplateItem selectedTemplate = null;
		String templateName = "LawyerPolicy";
		List<TemplateItem> templates = templateResult.getTemplates()
				.getTemplateItem();
		while ((selectedTemplate == null) && (i < templates.size())) {
			TemplateItem currentTemplate = templates.get(i);
			if (currentTemplate.getName().equalsIgnoreCase(templateName)) {
				selectedTemplate = currentTemplate;
			}
			i++;
		}

		TemplateItem templateItem = (selectedTemplate == null) ? templateResult
				.getTemplates().getTemplateItem().get(0) : selectedTemplate;

		// Get a reference to the list of parameters returned from the query (if
		// any were returned)

		List<ParameterItemBase> parameterItems = null;

		parameterItems = getTemplateParam(contextIdentifier, userName,
				templateItem);

		// Populate parameter values for the parameters (typically these would
		// be populated dynamically from a Form or database
		// but for simplicity the mapping has been hard-coded to pull from a
		// properties file in this example
		HashMap<String, String> parameterMapping = null;
		if (parameterItems != null) {
			parameterMapping = new HashMap<String, String>();
			try {
				parameterMapping = populateParameters(parameterItems, data);
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}
		}
		// Submit document using the template file as the underlying document
		ArrayOfDocumentResult submitResults = null;
		try {
			submitResults = new SubmitDocumentForSign().submit(
					contextIdentifier, userName, templateItem.getId(),
					parameterMapping, fileContent, pageNumber,  xyMap, data
							.get("AccountEmail"), data.get("PolicyEffectiveDate"), "Form");
			
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}

		// If the result is NULL, throw an exception
		if (submitResults == null) {
			throw new Exception("Submitwith file query return NULL.");
		}
		// If the result did not include any templates, throw an exception
		if ((submitResults.getDocumentResult() == null)
				|| (submitResults.getDocumentResult().size() == 0)) {
			throw new Exception("No file were submited.");
		}

		String documentId = submitResults.getDocumentResult().get(0).getId();
		String docAuthToken = submitResults.getDocumentResult().get(0)
				.getAuthToken();

		String assureSignURL = new SubmitDocumentForSign().buildAssureSignURL(
				contextIdentifier, documentId, docAuthToken, redirectUrl);
		
		logger.debug(assureSignURL);
		return assureSignURL;

	}
	
	


	/**
	 * @param args
	 */
	
	/*
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String filePath = "c:\\Accountant_QN-0001177.pdf";

		String contextIdentifier = "5201ec02-5330-4852-8278-b1b5c55407d3";
		String userName = "sunit@outlinesys.com";
		String redirectUrl = "";
		Map<String, String> data = new HashMap<String, String>();

		data.put("AccountName", "Amit Jain");
		data.put("AccountEmail", "amitj@outlinesys.com");
		
		String newURL = new EsignUtil().doSignature(contextIdentifier,userName,data, new SubmitDocumentForSign().getBytesFromFile(new File(filePath)),5, redirectUrl);

	}
	*/
	

}
