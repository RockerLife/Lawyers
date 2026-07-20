package com.userbo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.fop.servlet.ServletContextURIResolver;

import com.fop.DownloadFOP;
import com.fop.DownloadPDF;
import com.fop.XML2PDF;
import com.formgenerator.DownloadForm;
import com.mail.MailSender;
import com.manage.docmanagement.DocManagementUtil;
import com.manage.esign.EsignUtil;
import com.manage.managecomponent.components.SetParametersForStoredProcedures;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.util.Constants;
import com.util.Context;
import com.util.DocumentGenerationBO;
import com.util.HtmlConstants;
import com.util.IContext;
import com.util.IOUtils;
import com.util.InetLogger;
import com.util.PolicyNumberException;
import com.util.ResourceLoader;
import com.util.SubproducerMailer;
import com.util.SystemProperties;

public class DocumentManagment {

	private static final String ByteArrayOutputStream = null;
	public static String DATE_PATTERN = "MM-dd-yyyy";
	private static InetLogger logger = InetLogger.getInetLogger(DocumentManagment.class);
	private static final String ERROR_MESSAGE = "ERROR : ";	
	private static final String SUCCESS_MESSAGE = "SUCCESS";

	public void processDocument(IContext ctx) throws Exception {
		logger.debug("processDocument has started");
		if(ctx.get("fileItems") == null || !(ctx.get("fileItems") instanceof List))
			return;
		
		String fileName = null;
		String DocTitle = null;
		String Comment = null;
		String DocFileName = null;
		FileItem uploadFile = null;
		String SearchType = null;
		String DocType = null;

		
		try {
			/*DiskFileItemFactory factory = new DiskFileItemFactory();

			// maximum size that will be stored in memory
			factory.setSizeThreshold(25 * 1024 * 1024);

			// the location for saving data that is larger than
			// getSizeThreshold()
			String tempDirectory = SystemProperties.getInstance().getString(
					"fileupload.tempdirectory");
			factory.setRepository(new File(tempDirectory));

			ServletFileUpload upload = new ServletFileUpload(factory);

			// maximum size before a FileUploadException will be thrown
			Long size = new Long(25 * 1024 * 1024);
			upload.setSizeMax(size.longValue());*/

			//List fileItems = upload.parseRequest((HttpServletRequest) ctx.get(HtmlConstants.DOCUMENTREQUEST));
			List fileItems = (List)ctx.get("fileItems");

			// assume we know there are two files. The first file is a small
			// text file, the second is unknown and is written to a file on
			// the server
			Iterator i = fileItems.iterator();

			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();

				if (fi.isFormField()) {
					String name = fi.getFieldName();
					logger.debug(name);
					if (name.equalsIgnoreCase("DocTitle")) {
						DocTitle = fi.getString();
						DocTitle = DocTitle.replace("&", "$AND$");
						DocTitle = DocTitle.replace("%", "$PER$");
						DocTitle = DocTitle.replace("'", "$APH$");

					} else if (name.equalsIgnoreCase("Comment")) {
						Comment = fi.getString();
						Comment = Comment.replace("&", "$AND$");
						Comment = Comment.replace("%", "$PER$");
						Comment = Comment.replace("'", "$APH$");
					} else if (name.equalsIgnoreCase("SearchType")) {
						SearchType = fi.getString();
						logger.debug("SearchType - " + SearchType);
					}

					// String value = fi.getString();
				} else {

					// filename on the client
					fileName = fi.getName();
					uploadFile = fi;
					if (fileName == null) {
						continue;
					}
				}
				/*code by sukhi 26/09/2018*/
				fi=null;
				// break;
			}
		} catch (Exception e) {
			logger.debug("Error in file process");
			logger.error("Unexpected error", e);
			throw e;
		}

		if (fileName == null || "".equals(fileName))
			LawyersUtils.populateError(ctx, "DocFileName",
					"Document has not been selected");
		else if (fileName != null && !validateFileName(fileName))
			LawyersUtils.populateError(ctx, "DocFileName",
					"Selected file is not correct");

		if (SearchType == null || "".equals(SearchType))
			LawyersUtils.populateError(ctx, "SearchType",
					"Please select document type");

		if (Comment == null || "".equals(Comment))
			LawyersUtils.populateError(ctx, "Comment", "Enter comment");

		if (SearchType != null && SearchType.equals("other")) {
			if (DocTitle == null || "".equals(DocTitle))
				LawyersUtils.populateError(ctx, "DocTitle", "Enter title");
		}

		List errorList = null;
		if (ctx.get(Constants.INET_ERRORS_LIST) != null) {
			errorList = (List) ctx.get(Constants.INET_ERRORS_LIST);
			if (!errorList.isEmpty()) {
				ctx.put("SearchType", SearchType);
				ctx.put("SearchHidden", SearchType);

				if (Comment != null && !"".equals(Comment)) {

					Comment = Comment.replace("$AND$","&");
					Comment = Comment.replace("$PER$","%");
					Comment = Comment.replace("$APH$","'");
				}
				if (DocTitle != null && !"".equals(DocTitle)) {

					DocTitle = DocTitle.replace("$AND$","&");
					DocTitle = DocTitle.replace("$PER$","%");
					DocTitle = DocTitle.replace("$APH$","'");
				}

				ctx.put("Comment", Comment);
				ctx.put("DocTitle", DocTitle);
			}
		}

		if ((fileName == null || "".equals(fileName))
				|| (Comment == null || "".equals(Comment))
				|| (SearchType == null || "".equals(SearchType)))
			return;

		if (SearchType != null && SearchType.equals("other")) {
			if (DocTitle == null || "".equals(DocTitle)) {
				return;
			}
		}
		String isBrokerPolicy=ctx.get("isBrokerPolicy")!=null?ctx.get("isBrokerPolicy").toString():"N";
		String isBrokerageDocument=ctx.get("isBrokerageDocument")!=null?ctx.get("isBrokerageDocument").toString():"N";
		if("Y".equals(isBrokerPolicy) || "Y".equals(isBrokerageDocument))
		{
			if(ctx.get("BrokeragePolicyKey")==null )
				return;
			String brokerQN="BR-"+ctx.get("BrokeragePolicyKey");
			ctx.put("QuoteNumber", brokerQN);
		}
		int fileIndex = fileName.indexOf(".");
		String extension = fileName.substring(fileIndex);
		extension=extension.substring(extension.lastIndexOf('.'),extension.length());
		logger.debug(extension);
		if (SearchType != null && SearchType.equals("policyform")) {
			logger.debug("ctx -----" + ctx.get("QuoteNumber").toString());
			fileName = "Policy Form_" + ctx.get("QuoteNumber").toString();
			DocType = "PF";

		} else if (SearchType != null && SearchType.equals("quoteletter")) {
			fileName = "QuoteLetter_" + ctx.get("QuoteNumber").toString();
			DocType = "QL";
		} else if (SearchType != null && SearchType.equals("signedform")) {
			fileName = "Application Form_" + ctx.get("QuoteNumber").toString();
			DocType = "SF";
		} else if (SearchType != null && SearchType.equals("lossRun")) {
			fileName = "LossRun_" + ctx.get("QuoteNumber").toString();
			DocType = "LR";
		} else if (SearchType != null && SearchType.equals("priorPolicy")) {
			fileName = "PriorPolicy_" + ctx.get("QuoteNumber").toString();
			DocType = "PP";
		} else if (SearchType != null && SearchType.equals("other")) {
			// fileName = DocTitle + ctx.get("QuoteNumber").toString();
			if (!LawyersValidationUtils.validateFileNameForSpecialChar(DocTitle))
			{
				LawyersUtils.populateError(ctx, "DocTitle","Invalid File Name." );
				return;
			}
			fileName = DocTitle + "_" + ctx.get("QuoteNumber").toString();
			DocType = "OT";
		}else{
			
		}
		try{
		String org_fileName = fileName;
		fileName = fileName + extension;
		logger.debug(fileName);

		DocFileName = fileName.substring(fileName.lastIndexOf("\\") + 1,
				fileName.length());
		File file = new File(fileName);
		String docName = file.getName();
		String docNameTemp = file.getName();
		
		LawyersUtils.populateLastUpdateTimeStamp(ctx);
		
		String uploaddirectory = SystemProperties.getInstance().getString(
				"fileupload.uploaddirectory");

		try {
			uploadFile.write(new File(uploaddirectory, DocFileName));
		} catch (Exception e) {
			logger.debug("Exception " + e );
			LawyersUtils.populateError(ctx, "DocUploadError",
					"Document could not be write to file" );
			

			return;
		}

		String result = null;
		try {
			if(!"DEV".equals(ctx.get("environmentVar").toString()))
			result = uploadDocument(ctx, uploaddirectory + docName);
			else
				result="File added successfully!";
			//result = "File added successfully!";
			logger.debug("Result from uploadDocument(ctx, uploaddirectory + docName)" + result);
			
		} catch (Exception e) {
			logger.debug("Exception --->" + e);
			LawyersUtils.populateError(ctx, "DocUploadError",
					"Document could not be uploaded");
			return;
		}
		if (result == null
				|| (result != null && !result
						.equals("File added successfully!"))) {
			
			LawyersUtils.populateError(ctx, "DocUploadError",
					"Document could not be uploaded");

			return;
		}

		if (result != null && result.equals("File added successfully!")) {
			logger.debug("processDocument has done");
			// ctx.put("DocTitle", DocTitle);
			
			if (Comment != null && !"".equals(Comment)) {

				Comment = Comment.replace("$AND$","&");
				Comment = Comment.replace("$PER$","%");
				Comment = Comment.replace("$APH$","'");
			}
			if (DocTitle != null && !"".equals(DocTitle)) {

				DocTitle = DocTitle.replace("$AND$","&");
				DocTitle = DocTitle.replace("$PER$","%");
				DocTitle = DocTitle.replace("$APH$","'");
			}
			
			ctx.put("DocTitle", org_fileName);

			ctx.put("Comment", Comment);
			// ctx.put("DocFileName", DocFileName);

			int index = docName.indexOf(".");
			String fileName1 = docName.substring(0, index);
			String fileExt = docName.substring(index + 1, docName.length());
			docName = fileName1 + "_" + ctx.get("UploadedTime").toString()
					+ "." + fileExt;
			docName = docName.replace(" ", "").replace(":", "")
					.replace("-", "");

			ctx.put("DocFileName", docName);
			ctx.put("DocType", DocType);

			ctx.put("UploadedType", "Manual");

			setUrl(ctx, docName, "DocUrl");
			RuleUtils.executeRule(ctx, "LawyersRule.assignDateTimeNUser");
			LawyersUtils.getPolicyData((Context)ctx);
			if("Y".equals(isBrokerPolicy) || "Y".equals(isBrokerageDocument))
				insertInBrokerageDocumentLW(ctx);
			else
			insertInDocumentArchive(ctx);
		}

		File tempFile = new File(uploaddirectory + docNameTemp);
		if (tempFile.exists())
			tempFile.delete();
		
		/*code by sukhi 26/09/2018*/
		tempFile=null;
		file=null;
		}catch(Exception e){
			logger.debug("Exception in DocFileName" + e);
		}
	}

	public String uploadDocument(IContext ctx, String url) throws Exception {
		
		String userName = getUserName();
		String password = getUserPassword();
		String domain = getDomainName();
		String baseDir =  getSharePointBaseDirectory();
		/*String isBrokerPolicy=ctx.get("isBrokerPolicy")!=null?ctx.get("isBrokerPolicy").toString():"N";
		String isBrokerageDocument=ctx.get("isBrokerageDocument")!=null?ctx.get("isBrokerageDocument").toString():"N";
		if("Y".equals(isBrokerPolicy) || "Y".equals(isBrokerageDocument))
			baseDir=getSharePointBaseDirectoryBrokerage();
		else
			baseDir=getSharePointBaseDirectory();*/
		String spUrl = getSharePointURL();

		byte[] docContent = new EsignUtil().getDocument(url);

		File file = new File(url);
		String docName = file.getName();

		int index = docName.indexOf(".");
		String fileName = docName.substring(0, index);
		String fileExt = docName.substring(index + 1, docName.length());

		String DocFileName = fileName + "_"
				+ ctx.get("UploadedTime").toString() + "." + fileExt;
		DocFileName = DocFileName.replace(" ", "").replace(":", "").replace(
				"-", "");
		String docLibPathName = spUrl + baseDir;
		String folderName = ctx.get("QuoteNumber").toString();

		// String userName = "sanjayr";
		// String password = "Sanr55.bgp";
		// String domain = "in";
		// String baseDir = "TestJava";
		
		//		return new DocManagementUtil().uploadDocToSharePoint(docLibPathName,
		//		folderName, baseDir, DocFileName, docContent, userName,
		//		password, domain);
		//Raghu
		return new DocManagementUtil().uploadDocToSharePoint(docLibPathName,
		folderName, baseDir, DocFileName, url, userName,
		password, domain, spUrl);
		
	}

	public void downloadDocument(IContext ctx, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		String userName = getUserName();
		String password = getUserPassword();
		String domain = getDomainName();
		String baseDir = getSharePointBaseDirectory();
		/*String isBrokerPolicy=ctx.get("isBrokerPolicy")!=null?ctx.get("isBrokerPolicy").toString():"N";
		String isBrokerageDocument=ctx.get("isBrokerageDocument")!=null?ctx.get("isBrokerageDocument").toString():"N";
		if("Y".equals(isBrokerPolicy) || "Y".equals(isBrokerageDocument))
			baseDir=getSharePointBaseDirectoryBrokerage();
		else
			baseDir=getSharePointBaseDirectory();*/

		String docUrl = null;
		if (ctx.get("DocUrl") != null)
			docUrl = ctx.get("DocUrl").toString();

		if (docUrl == null)
			throw new Exception("Document URL is missing.");

		byte[] barr = new DocManagementUtil().downloadDocFromSharePoint(docUrl,
				userName, password, domain);

		if (barr == null || barr.length == 0)
			throw new Exception("No document content returned from SharePoint for " + docUrl);

		ServletOutputStream outputStream = null;
		ByteArrayOutputStream bout = null;
		try {
			outputStream = res.getOutputStream();
			bout = new ByteArrayOutputStream();
			bout.write(barr);
			bout.writeTo(outputStream);

			outputStream.flush();
		} finally {
			if (bout != null)
				bout.close();
			if (outputStream != null)
				outputStream.close();
		}
	}

	public void uploadQuoteLetter(IContext ctx) throws Exception {

		String htmlDir = SystemProperties.getInstance().getString(
				"html.basedir");
		String outFile = htmlDir + "data//QuoteLetter_"
				+ ctx.get("QuoteNumber").toString() + ".pdf";

		// Uploading Quote letter
		LawyersUtils.populateLastUpdateTimeStamp(ctx);
		String result = new DocumentManagment().uploadDocument(ctx, outFile);
		String DocFileName = outFile.substring(outFile.lastIndexOf("//") + 2,
				outFile.length());
		if (result != null) {
			// ctx.put("DocTitle", DocFileName);

			int index = DocFileName.indexOf(".");
			String fileName1 = DocFileName.substring(0, index);
			ctx.put("DocTitle", fileName1);

			String fileExt = DocFileName.substring(index + 1, DocFileName
					.length());
			DocFileName = fileName1 + "_" + ctx.get("UploadedTime").toString()
					+ "." + fileExt;
			DocFileName = DocFileName.replace(" ", "").replace(":", "")
					.replace("-", "");

			ctx.put("Comment", "");
			ctx.put("DocFileName", DocFileName);

			ctx.put("UploadedType", "Auto");

			ctx.put("DocType", "QL");

			setUrl(ctx, DocFileName, "DocUrl");
			
		}
		// End uploading Quote letter
	}
	
	public void uploadQuickQuoteLetter (IContext ctx) throws Exception {
        
        String htmlDir = SystemProperties.getInstance().getString("html.basedir");
        String outFile = htmlDir + "data//QuickQuoteOptions_" + ctx.get("QuoteNumber").toString() + ".pdf";                 
        
        // Uploading Quote letter
        LawyersUtils.populateLastUpdateTimeStamp(ctx);              
        String result = new DocumentManagment().uploadDocument(ctx, outFile);
        String DocFileName = outFile.substring(outFile.lastIndexOf("//")+2, outFile.length());
        if(result != null)
        {     
              //ctx.put("DocTitle", DocFileName);
              
              
              int index = DocFileName.indexOf(".");
          String fileName1 = DocFileName.substring(0, index);
          ctx.put("DocTitle", fileName1);

          
          String fileExt = DocFileName.substring(index+1, DocFileName.length());
          DocFileName = fileName1 + "_" + ctx.get("UploadedTime").toString() + "."+ fileExt;
          DocFileName = DocFileName.replace(" ", "").replace(":", "").replace("-", "");
          
          ctx.put("Comment", "");                 
              ctx.put("DocFileName", DocFileName);
              
              ctx.put("UploadedType", "Auto");
              
              ctx.put("DocType", "QQL");
              
              setUrl(ctx, DocFileName, "DocUrl");
              insertInDocumentArchive(ctx);
        }
        // End uploading Quick Quote option
  }


	
	public void processSignedDocument(IContext ctx) throws Exception {

		logger.debug("processSignedDocument has started");
		// Populating all keys in Context
		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"SqlStmts.UserStatementprocessQuotegetPolicyKeys", ctx);
		Map keysMap = null;

		if (obj != null && obj instanceof Map) {
			keysMap = (Map) obj;
			ctx.putAll(keysMap);
		}

		LawyersUtils.populateLastUpdateTimeStamp(ctx);
		
		String result = "";
		String skipUpload = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".skipupload");
		if(!"Y".equals(skipUpload)){
			result = uploadSignedDocument(ctx);
		}else{
			result = "File added successfully!";
		}

		logger.debug("Result from uploadSignedDocument(ctx) " + result);

		if (result != null) {
			logger.debug("processSignedDocument has done");

			String DocFileName = "Signed_Form_"
					+ ctx.get("QuoteNumber").toString() + "_"
					+ ctx.get("UploadedTime").toString() + ".pdf";
			String DocTitle = "Signed_Form_"
					+ ctx.get("QuoteNumber").toString();
			ctx.put("DocTitle", DocTitle);
			DocFileName = DocFileName.replace(" ", "").replace(":", "")
					.replace("-", "");
			ctx.put("DocFileName", DocFileName);
			ctx.put("DocType", "SF");

			ctx.put("UploadedType", "Auto");

			setSignedPDFUrl(ctx, "DocUrl");
			insertInDocumentArchive(ctx);
		}
	}

	public String uploadSignedDocument(IContext ctx) throws Exception {

		String assuresignURL = SystemProperties.getInstance().getString(
				"esign.doc.retrieve.url");
		String SignedDocumentID = null;
		if (ctx.get("SignedDocumentID") != null)
			SignedDocumentID = ctx.get("SignedDocumentID").toString();

		String SignedDocumentToken = null;
		if (ctx.get("SignedDocumentToken") != null)
			SignedDocumentToken = ctx.get("SignedDocumentToken").toString();

		if (assuresignURL == null || SignedDocumentID == null
				|| SignedDocumentToken == null)
			return null;

		byte[] docContent = new EsignUtil().getDocument(assuresignURL, ctx.get(
				"SignedDocumentID").toString(), ctx.get("SignedDocumentToken")
				.toString());
		
		String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//Signed_Form_" + ctx.get("QuoteNumber").toString() + ".pdf";
		FileOutputStream stream = new FileOutputStream(outFile);
	    try {
	        stream.write(docContent);
	    } finally {
	        stream.close();
	    }

		String userName = getUserName();
		String password = getUserPassword();
		String domain = getDomainName();
		String baseDir = getSharePointBaseDirectory();
		String spUrl = getSharePointURL();

		String docName = "Signed_Form_" + ctx.get("QuoteNumber").toString()
				+ "_" + ctx.get("UploadedTime").toString() + ".pdf";
		docName = docName.replace(" ", "").replace(":", "").replace("-", "");
		String docLibPathName = spUrl + baseDir;
		String folderName = ctx.get("QuoteNumber").toString();

		// String userName = "sanjayr";
		// String password = "Sanr55.bgp";
		// String domain = "in";
		// String baseDir = "TestJava";

		/*return new DocManagementUtil().uploadDocToSharePoint(docLibPathName,
		folderName, baseDir, docName, docContent, userName, password,
		domain);*/

		//Raghu
		return new DocManagementUtil().uploadDocToSharePoint(docLibPathName,
				folderName, baseDir, docName, outFile, userName,
				password, domain, spUrl);

	}
	  
	public void setSignedPDFUrl(IContext ctx, String field) throws Exception {

		String docName = "Signed_Form_" + ctx.get("QuoteNumber").toString()
				+ "_" + ctx.get("UploadedTime").toString() + ".pdf";
		docName = docName.replace(" ", "").replace(":", "").replace("-", "");

		String spUrl = getSharePointURL();
		String baseDir = getSharePointBaseDirectory();
		String docLibPathName = spUrl + baseDir;
		String folderName = ctx.get("QuoteNumber").toString();
		String url = docLibPathName + "/" + folderName + "/" + docName;
		ctx.put(field, url);
	}
	  
	  

	public void setFormUrl(IContext ctx, String field) throws Exception {

		String docName = "Policy Form_" + ctx.get("QuoteNumber").toString()
				+ "_" + ctx.get("UploadedTime").toString() + ".pdf";
		docName = docName.replace(" ", "").replace(":", "").replace("-", "");

		String spUrl = getSharePointURL();
		String baseDir = getSharePointBaseDirectory();
		String docLibPathName = spUrl + baseDir;
		String folderName = ctx.get("QuoteNumber").toString();
		String url = docLibPathName + "/" + folderName + "/" + docName;
		ctx.put(field, url);

	}

	public boolean validateFileName(String filePath) throws Exception {
		if (filePath == null)
			return true;

		if (filePath != null && "".equals(filePath.trim()))
			return true;

		int index = filePath.lastIndexOf("\\");
		// if(index <= 0)
		// return false;

		String fileName = null;
		if (index > 0) {
			fileName = filePath.substring(index + 1, filePath.length());
			if (fileName.indexOf(".") <= 0)
				return false;
		} else
			fileName = filePath;

		int indexExt = fileName.lastIndexOf(".");
		String ext = fileName.substring(indexExt + 1, fileName.length());

		if (ext.length() < 3)
			return false;

		return true;
	}

	public void setUrl(IContext ctx, String docName, String field)
			throws Exception {

		String spUrl = getSharePointURL();
		String baseDir = getSharePointBaseDirectory();
		String docLibPathName = spUrl + baseDir;
		String folderName = ctx.get("QuoteNumber").toString();
		String url = docLibPathName + "/" + folderName + "/" + docName;
		ctx.put(field, url);

	}

	public void insertInDocumentArchive(IContext ctx) throws Exception {
		
		SqlResources.getSqlMapProcessor(ctx).insert("DocumentArchiveLW.insert", ctx);
	}
public void insertInBrokerageDocumentLW(IContext ctx) throws Exception {
		
		SqlResources.getSqlMapProcessor(ctx).insert("BrokerageDocumentLW.insert", ctx);
	}
	public String getSharePointURL() throws Exception {

		return SystemProperties.getInstance().getString("sharepoint.url");
	}

	public String getUserName() throws Exception {

		return SystemProperties.getInstance().getString("sharepoint.user.name");
	}

	public String getUserPassword() throws Exception {

		return SystemProperties.getInstance().getString("sharepoint.password");
	}

	public String getDomainName() throws Exception {

		return SystemProperties.getInstance().getString("sharepoint.domain");
	}

	public String getSharePointBaseDirectory() throws Exception {

		return SystemProperties.getInstance().getString("sharepoint.basedir");
	}
	public String getSharePointBaseDirectoryBrokerage() throws Exception {

		return SystemProperties.getInstance().getString("sharepoint.brokeragebasedir");
	}
	/*
	 * The code below is used in Polulation of PolicyForm
	 */
	

	public String processDocumentManagment (IContext ctx) throws Exception {
		logger.debug("processDocumentManagment started  ........ " + ctx.get("QuoteNumber"));
		boolean issuePolicy=false,IsManualPremium=false,alreadyIssuePolicy=false;
 		String policyNumber ="";
		List attachments  = new ArrayList();		
		OutputStream out = null;
		File pdfFile = null;
		//PolicyNumber generation and Update
		
		try{
			boolean errorFlag=LawyersUtils.validateProtexureHoldSubproducer((Context)ctx);
			if(errorFlag)
				return "";
			
				new LawyersUtils().setImagesPathToTemplate(ctx);
				logger.debug("Checking if the Policy is not already Issued " + ctx.get("PolicyKey"));
	
				Object objcheck = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetStatusKeyFinalisedQuote",ctx);
				if (objcheck != null && objcheck instanceof List) 
				{
					List objList = (List) objcheck;
					if (objList != null && objList.size() > 0) {
						Map objMap = (Map) objList.get(0);
						String statusKey = objMap.get("StatusKey") != null ? objMap.get("StatusKey").toString() : "";
						 policyNumber = objMap.get("PolicyNumber") != null ? objMap.get("PolicyNumber").toString() : "";
						logger.info("StatusKey of PolicyKey " + ctx.get("PolicyKey") + "and TransactionSequence " + ctx.get("FinalisedQuote_TransactionSequence") + " is " + statusKey);
						logger.info("PolicyNumber of PolicyKey " + ctx.get("PolicyKey") + " is " + policyNumber);
	
						if ("6".equals(statusKey) || !"".equals(policyNumber)) {
							logger.info("Going to Return as Policy is already Issued");
							alreadyIssuePolicy = true;
							return "Already Issued";
						} else if (!"6".equals(statusKey) && !"7".equals(statusKey)) {
							logger.info("Going to Return as Policy can't be Issued");
							alreadyIssuePolicy = true;
							return "Can't be Issued";
						}
					}
				}
				if("1".equals(ctx.get("CompanyKey").toString()))
					policyNumber = LawyersUtils.generatePolicyNumber(ctx);
				if("3".equals(ctx.get("CompanyKey").toString()))
					policyNumber = LawyersUtils.generatePolicyNumberISMIE(ctx);
				
				ctx.put("PolicyNumber", policyNumber);
				logger.debug("Policy Number Generated is --- " + policyNumber);
				LawyersUtils.populateLastUpdateTimeStamp(ctx);
			
				new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,VersionSequence,PolicyNumber,UserNo");
				SqlResources.getSqlMapProcessor(ctx).update("Policy.IssuePolicyUpdateStateus_p",ctx);
				
				logger.debug("Policy Number to "+policyNumber+" updated and Policy Status updated to ISSUED");

				try{
					LawyersUtils.updateZOHORecords((Context) ctx);
				} catch(Exception e) {
					logger.debug("Exception occured during ZOHO update "+e);
					logger.debug("Exception occured during ZOHO update Issue "+e);
				}
				
				List getPolicyNumber=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyNumber",ctx);
				ctx.putAll((Map) getPolicyNumber.get(0));
				String dbPolicyNumber=ctx.get("DBPolicyNumber")!=null && !ctx.get("DBPolicyNumber").equals(HtmlConstants.EMPTY)?ctx.get("DBPolicyNumber").toString():"";
				if("".equals(dbPolicyNumber) ){
					issuePolicy=false;
					throw new PolicyNumberException();
					
				}
				//SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementformupdatePolicyNumber", ctx);
				
				//Populating all keys in Context
				Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformgetPolicyKeys", ctx);
				Map keysMap = null;		
				if(obj != null && obj instanceof Map){
					keysMap = (Map) obj;
					ctx.putAll(keysMap);
				}		
					
				obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformCoveragePolicy", ctx);
				Map policyCovMap = null;
				
				if(obj != null && obj instanceof Map){
					policyCovMap = (Map) obj;
					ctx.putAll(policyCovMap);
				}		
				
				obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformgetPriorPolicyInfo", ctx);
				Map policyInfo = null;
				
				if(obj != null && obj instanceof Map){
					policyInfo = (Map) obj;
					ctx.putAll(policyInfo);
				}
				
				logger.debug("Populated all keys in Context...............");
				
				// Get Finalised Quote
				QuoteLetter.getFinalisedQuote(ctx);
				logger.debug("Get Finalised Quote.............................");
				
				List listFormID = LawyersUtils.populateFinalisedPolicyForm(ctx);
				
				// Form
				//List listFormID = getPolicyFormID(ctx);		
				
				//List listFormID = new ArrayList();
				//listFormID.add("APL - 102 - 2009");
				//listFormID.add("APL - 103 - 2009");
				//listFormID.add("APL - 104 (12/09)");
				//listFormID.add("APL - 105 (12/09)");
				//listFormID.add("APL - 106 - 2010");
				
				
				IsManualPremium = checkManualFlagWithFinalisedOption(ctx);
				
			 	//if(!(dollarDefenseFlag )){
				
				String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//Policy Form_" + ctx.get("QuoteNumber").toString() + ".pdf";		
				pdfFile = new File(outFile);
				out = new java.io.FileOutputStream(pdfFile);
				out = new java.io.BufferedOutputStream(out);
				
				String baseUrl = null;
				if(ctx.get("baseUrl") != null)
					baseUrl = ctx.get("baseUrl").toString();

				if(ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT) != null)
					baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
				
				ServletContextURIResolver uriResolver = null;
				
				if(ctx.get("DocumentUriResolver") != null)
					uriResolver = (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
				
				new DownloadForm().generateForm((Context)ctx, listFormID, out, baseUrl, uriResolver);			
				
				LawyersUtils.populateLastUpdateTimeStamp(ctx);
				String result = "";
				String skipUpload = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".skipupload");
				if(!"Y".equals(skipUpload)){
					result = uploadFormDocument(ctx);
				}else{
					result = "File added successfully!";
				}
				logger.debug("Result from uploadFormDocument(ctx) in processDocumentManagment " + result);
				
				if(result == null || (result != null && !result.equals("File added successfully!"))){
					LawyersUtils.populateError(ctx, "DocUploadError", "Policy could not be issued");
					issuePolicy=false;
					return ERROR_MESSAGE + "DocUploadError";
				}
						
				if(result != null && result.equals("File added successfully!")){		
					String DocFileName = "Policy Form_" + ctx.get("QuoteNumber").toString()+ "_" + ctx.get("UploadedTime").toString() + ".pdf";	
					String DocTitle = "Policy Form_" + ctx.get("QuoteNumber").toString();
					ctx.put("DocTitle", DocTitle);
					DocFileName = DocFileName.replace(" ", "").replace(":", "").replace("-", "");
					ctx.put("DocFileName", DocFileName);
					ctx.put("DocType", "PF");
					ctx.put("UploadedType", "Auto");
					setFormUrl(ctx, "DocUrl");			
					insertInDocumentArchive(ctx);
				}
				
				
				if(out != null)
					out.close();
				attachments.add(outFile);
				
				//Application PDF
				String htmlDir = SystemProperties.getInstance().getString("html.basedir");
				outFile = htmlDir + "data//Lawyers_" + ctx.get("QuoteNumber").toString() + ".pdf";
				//pdfFile = new File(outFile);
				out = new java.io.FileOutputStream(outFile);
				out = new java.io.BufferedOutputStream(out);
				
				
				if(ctx.get("PolicyStatusKey") != null && "2".equals(ctx.get("PolicyStatusKey").toString())){
					new DownloadPDF().makeRenewPdf((Context) ctx, out, baseUrl, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER));
				}else{
					new DownloadPDF().makePdf((Context) ctx, out, baseUrl, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER));
				}
				
				
				//ByteArrayOutputStream baos = new PrintPDF().makePdf((Context)ctx);
				//baos.writeTo(out);
				if("NJ".equals(ctx.get("StateCode").toString()))
				{
					String fileName="InsuranceCertificate";
					
					outFile = SystemProperties.getInstance().getString("html.basedir") + "data//" + fileName +".pdf";		
					pdfFile = new File(outFile);
					out = new java.io.FileOutputStream(pdfFile);
					out = new java.io.BufferedOutputStream(out);			
					generateInsurenceCertificateForNJ((Context)ctx,out,pdfFile);					
					attachments.add(outFile);
					
					if(out != null)
						out.close();				
				}
				
			//	RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");
			/*	if(ctx.get("LastUpdateTimeStamp") != null)
					ctx.put("IssuedDate",ctx.get("LastUpdateTimeStamp"));		
				else*/
				//	ctx.put("IssuedDate",new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
				
				logger.debug("Policy Issue Date: "+ new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
				//SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementformupdateIssuedDate", ctx);
				
				ctx.remove("IssuedDate");
				ctx.remove("PolicyNumber");		
				ctx.put("IsDocumentProcessed","Y");
				issuePolicy=true;
				//Commention this as No longer application will be attached with the Issued mail 10 October 2015				
				//attachments.add(outFile);
				//Asked to remove by client JIRA ID PA-1191
				/*if("NJ".equals(ctx.get("StateCode").toString()))
				{
					String DocFileName = "NJ_COVID19_GracePeriodNotice.pdf";
					String sourcePath  =htmlDir + "download//" + DocFileName;
					String destinationPath=htmlDir + "data//"+DocFileName;
					LawyersUtils.copyFile(sourcePath,destinationPath);
					attachments.add(destinationPath);
					
				}*/
				
				
		}
		catch (Exception e){
			logger.error("Unexpected error", e);
			issuePolicy=false;
			ctx.put("IsDocumentProcessed", "N");
			logger.debug("processDocumentManagment failed  ........" + e);
			throw e;
		}finally {
			/*code by sukhi 26/09/2018*/
			pdfFile = null;
			if(out != null){
				out.close();
				out = null;
			}
				
			if(!issuePolicy && !alreadyIssuePolicy){
				logger.debug(issuePolicy);
				try{
					logger.debug("Going to rollback all database changes for issuing policy having quote number"+ctx.get("QuoteNumber"));
					new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,VersionSequence,policyNumber,UserNo");
					SqlResources.getSqlMapProcessor(ctx).update("Policy.RollbackIssuePolicyUpdateStateus_p",ctx);
				} 
				catch (Exception e1){
					issuePolicy=false;
					logger.debug("Exception occured during rollback all database changes for issuing policy ");
					logger.error("Unexpected error", e1);
				}
			}
			logger.debug("issuing policy :  "+issuePolicy+" for policynumber :"+policyNumber+" having quote number"+ctx.get("QuoteNumber"));

			try{
				LawyersUtils.updateZOHORecords((Context) ctx);
			} catch(Exception e) {
				logger.debug("Exception occured during ZOHO update "+e);
				logger.debug("Exception occured during ZOHO update Issue "+e);
			}
		}
		if(issuePolicy){
			File file = null;
			
			try{
				logger.debug("issuing policy :  "+issuePolicy+" for policynumber :"+policyNumber+" having quote number"+ctx.get("QuoteNumber"));
				String insuredEmail = "";
				String ccAddress="";
				boolean isPAAndDefenseInside = false ;
				boolean producerCodeExist =false;
				boolean isSpecialIssuance =false;
				String adminEmail = LawyersUtils.getAdminEmailID (ctx);
				
				// Do not send mail to insured if producer code is there
				producerCodeExist = new LawyersValidationUtils().checkIfSubProducerExist(ctx);
				logger.debug("Producer Code Exist ? " + producerCodeExist);
				if(!producerCodeExist){					
					insuredEmail = LawyersUtils.getInsuredEmail(ctx); 
				}
				logger.debug("Check is special issuance ");
				isSpecialIssuance = LawyersValidationUtils.checkIfSpecialIssuance(ctx);
				logger.debug("Is Special Issuance ? " + isSpecialIssuance);
				
				// Asked By Pat 10/16/2017				
				//logger.debug("Check if PA and defense Inside");				
				// Commented for JIRA #1670-21/03/2022
				/*
				 * if(ctx.get("StateCode") != null &&
				 * "PA".equals(ctx.get("StateCode").toString())){ isPAAndDefenseInside =
				 * LawyersValidationUtils.checkForFinalisedQuoteWithInsideLimit(ctx); }
				 */
				logger.debug("isPAAndDefenseInside ? " +  isPAAndDefenseInside);				
				if(isSpecialIssuance || producerCodeExist || isPAAndDefenseInside){
					logger.debug("Inside Special Issuance and Producer Code exist block . Getting Special Issuance Email");
					String specialIssuanceMail = LawyersValidationUtils.getSpecialIssuanceEmail(ctx);
					insuredEmail = specialIssuanceMail ;
				}
				boolean subProducerPolicyMailSend = false;
				if(producerCodeExist ){
					logger.debug("Inside  Producer Code exist block. Checking for eligibility of sending emails to this subproducer");
					Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesSubproducerFormData1", ctx);
					Map keysMap = null;		
					if(obj != null && obj instanceof Map){
						keysMap = (Map) obj;
						ctx.putAll(keysMap);
					}	
					String sendQuoteLetter=ctx.get("SendQuoteLetter")!=null  && !ctx.get("SendQuoteLetter").equals(HtmlConstants.EMPTY) ?ctx.get("SendQuoteLetter").toString():"N";
					if("Y".equalsIgnoreCase(sendQuoteLetter))
					{
						subProducerPolicyMailSend = true;
						insuredEmail = LawyersUtils.getInsuredEmail(ctx); 
						ccAddress=LawyersUtils.getProducerEmail(ctx);
					}
					if("Y".equals(ctx.get("IsDriect"))){
						insuredEmail = LawyersUtils.getEmailID(ctx);
					}
				}
				
				
				if("".equals(insuredEmail))
					insuredEmail = adminEmail;			
				
				logger.debug("Going to send mail to " + insuredEmail);
				String policy ="Issued Policy Number  " + policyNumber;
				
				if("".equals(adminEmail))
					return ERROR_MESSAGE + "AdminEmailError";	
				
				if(!subProducerPolicyMailSend){
					MailSender mailSender = new MailSender();				
					mailSender.setSubject(policy);
	
					mailSender.setIsSentToCC("Y");
					logger.debug("Is Manual Quote  " + IsManualPremium);
					if("N".equals(ctx.get("SendQuoteLetter")) && "Y".equals(ctx.get("SubProducerAccess"))){
	        			insuredEmail = SystemProperties.getInstance().getString("mail.admin.to.address");
					}
					
					if(isSpecialIssuance ){
						logger.debug("Inside Special Issuance  block. Getting Special Issuance Email to subproduced insured.");
						String specialIssuanceMail = LawyersValidationUtils.getSpecialIssuanceEmail(ctx);
						insuredEmail = specialIssuanceMail ;
						
					}
					if(!IsManualPremium && !producerCodeExist){						
						mailSender.setToAdd(insuredEmail);
					}
					if(isSpecialIssuance || producerCodeExist || isPAAndDefenseInside)
						mailSender.setToAdd(insuredEmail);
					
					String productionEnv = "N";
					try {
						productionEnv = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production");
					} catch (Exception e) {
						logger.error("error in geeting production environment");
					}
					String bccAddress="";
					if ("N".equals(productionEnv)){
						//ccAddress = SystemProperties.getInstance().getString("mail.admin.cc.address");
						bccAddress = SystemProperties.getInstance().getString("mail.admin.cc.address");
						logger.debug("email id------->"+ccAddress);
					}
					/*
					 * if("".equals(ccAddress))
					 * ccAddress=SystemProperties.getInstance().getString("appl." + ctx.getProject()
					 * + ".admin.cc.email");
					 */

					mailSender.setCcAdd(LawyersUtils.getFirstReviewerAgentEmailID(ctx));
					mailSender.setBccAdd(bccAddress);
					mailSender.setContentType("text/html");			
					mailSender.setBody(generatePolicyIssueBody(ctx));	
					mailSender.setAttachments(attachments);				
					mailSender.sendMail();
				} else {
//	        		String productionEnv = "Y";
//	        		try {
//	        			productionEnv = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production");
//	        		} 
//	        		catch (Exception e) 
//	        		{
//	        			logger.error("error in geeting production environment");
//	        		}
//	        		if ("N".equals(productionEnv))
//	        			ccAddress=SystemProperties.getInstance().getString("mail.admin.cc.address");
					if("Y".equals(ctx.get("SendQuoteLetter")) && "Y".equals(ctx.get("SubProducerAccess"))){
	        			insuredEmail = LawyersUtils.getSubProducerEmailID(ctx);
	        			ccAddress = LawyersUtils.getProducerEmail(ctx);
					}
	        		if("N".equals(ctx.get("SendQuoteLetter")) && "Y".equals(ctx.get("SubProducerAccess"))){
//						toAddress = LawyersUtils.getProducerEmail(ctx);
	        			insuredEmail = SystemProperties.getInstance().getString("mail.adminsub.to.address");
					}
	        		if(isSpecialIssuance ){
						logger.debug("Inside Special Issuance  block. Getting Special Issuance Email to direct insured.");
						String specialIssuanceMail = LawyersValidationUtils.getSpecialIssuanceEmail(ctx);
						insuredEmail = specialIssuanceMail ;
						
					}
	        		
					ctx.put("toAddress",insuredEmail);
	        		ctx.put("bccAddress",ccAddress);
	        		ctx.put("subject",policy); 
					ctx.put("body",generateSubproducerPolicyIssueBody(ctx));
					ctx.put("emailAttachment",attachments);
					SubproducerMailer.sendEmailAsSubProducer((Context)ctx);
				}
				for (int i=0; i<attachments.size(); i++){
					//File Delete
					file = new File(attachments.get(i).toString());
					if(file.exists())
						file.delete();
					
					
				}				
		
			}
			catch(Exception e)
			{
				logger.error("Unexpected error", e);
				logger.debug("Exception occured during sending policy to insured "+e);
				return ERROR_MESSAGE + "MailSendingError";
			}finally {
				/*code by sukhi 26/09/2018*/
				file=null;
			
				try{
					LawyersUtils.updateZOHORecords((Context) ctx);
					EPICDataUpdate.insertEPICData((Context) ctx);
				}catch(Exception e){
					logger.debug("Exception occured during ZOHO update "+e);
				}
			}
		}
		else
			logger.debug("Unable to send policy form due to Exception occured during policy generation ");
		
		logger.debug("processDocumentManagment end  ........");
		return SUCCESS_MESSAGE;
		
	}
	
	/*public void processDocumentManagment (IContext ctx) throws Exception {
		logger.debug("processDocumentManagment started  ........ " + ctx.get("QuoteNumber"));
		boolean issuePolicy=false,IsManualPremium=false ;
		String policyNumber ="";
		List attachments  = new ArrayList();		
		//PolicyNumber generation and Update
		try{
				new LawyersUtils().setImagesPathToTemplate(ctx);
				logger.debug("Checking if the Policy is not already Issued " + ctx.get("PolicyKey"));
	
				Object objcheck = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetStatusKeyFinalisedQuote",ctx);
				if (objcheck != null && objcheck instanceof List) 
				{
					List objList = (List) objcheck;
					if (objList != null && objList.size() > 0) {
						Map objMap = (Map) objList.get(0);
						String statusKey = objMap.get("StatusKey") != null ? objMap.get("StatusKey").toString() : "";
						 policyNumber = objMap.get("PolicyNumber") != null ? objMap.get("PolicyNumber").toString() : "";
						logger.info("StatusKey of PolicyKey " + ctx.get("PolicyKey") + "and TransactionSequence " + ctx.get("FinalisedQuote_TransactionSequence") + " is " + statusKey);
						logger.info("PolicyNumber of PolicyKey " + ctx.get("PolicyKey") + " is " + policyNumber);
	
						if ("6".equals(statusKey) || !"".equals(policyNumber)) {
							logger.info("Going to Return as Policy is already Issued");
							return;
						}
					}
				}
				
				policyNumber = LawyersUtils.generatePolicyNumber(ctx);
				ctx.put("PolicyNumber", policyNumber);
				logger.debug("Policy Number Generated is --- " + policyNumber);
				LawyersUtils.populateLastUpdateTimeStamp(ctx);
			
				new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,VersionSequence,PolicyNumber,UserNo");
				SqlResources.getSqlMapProcessor(ctx).update("Policy.IssuePolicyUpdateStateus_p",ctx);
				
				logger.debug("Policy Number to "+policyNumber+" updated and Policy Status updated to ISSUED");
				
				List getPolicyNumber=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyNumber",ctx);
				ctx.putAll((Map) getPolicyNumber.get(0));
				String dbPolicyNumber=ctx.get("DBPolicyNumber")!=null && !ctx.get("DBPolicyNumber").equals(HtmlConstants.EMPTY)?ctx.get("DBPolicyNumber").toString():"";
				if("".equals(dbPolicyNumber) ){
					throw new PolicyNumberException();
					
				}
				//SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementformupdatePolicyNumber", ctx);
				
				//Populating all keys in Context
				Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformgetPolicyKeys", ctx);
				Map keysMap = null;		
				if(obj != null && obj instanceof Map){
					keysMap = (Map) obj;
					ctx.putAll(keysMap);
				}		
					
				obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformCoveragePolicy", ctx);
				Map policyCovMap = null;
				
				if(obj != null && obj instanceof Map){
					policyCovMap = (Map) obj;
					ctx.putAll(policyCovMap);
				}		
				
				obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformgetPriorPolicyInfo", ctx);
				Map policyInfo = null;
				
				if(obj != null && obj instanceof Map){
					policyInfo = (Map) obj;
					ctx.putAll(policyInfo);
				}
				
				logger.debug("Populated all keys in Context...............");
				
				// Get Finalised Quote
				QuoteLetter.getFinalisedQuote(ctx);
				logger.debug("Get Finalised Quote.............................");
				
				List listFormID = LawyersUtils.populateFinalisedPolicyForm(ctx);
				
				// Form
				//List listFormID = getPolicyFormID(ctx);		
				
				//List listFormID = new ArrayList();
				//listFormID.add("APL - 102 - 2009");
				//listFormID.add("APL - 103 - 2009");
				//listFormID.add("APL - 104 (12/09)");
				//listFormID.add("APL - 105 (12/09)");
				//listFormID.add("APL - 106 - 2010");
				
				
				IsManualPremium = checkManualFlagWithFinalisedOption(ctx);
				
			 	//if(!(dollarDefenseFlag )){
				
				String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//Policy Form_" + ctx.get("QuoteNumber").toString() + ".pdf";		
				File pdfFile = new File(outFile);
				OutputStream out = new java.io.FileOutputStream(pdfFile);
				out = new java.io.BufferedOutputStream(out);
				
				String baseUrl = null;
				if(ctx.get("baseUrl") != null)
					baseUrl = ctx.get("baseUrl").toString();

				
				baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
				
				ServletContextURIResolver uriResolver = null;
				
				if(ctx.get("DocumentUriResolver") != null)
					uriResolver = (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
				
				new DownloadForm().generateForm((Context)ctx, listFormID, out, baseUrl, uriResolver);			
				
				LawyersUtils.populateLastUpdateTimeStamp(ctx);
				String result = "";
				String skipUpload = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".skipupload");
				if(!"Y".equals(skipUpload)){
					result = uploadFormDocument(ctx);
				}else{
					result = "File added successfully!";
				}
				logger.debug("Result from uploadFormDocument(ctx) in processDocumentManagment " + result);
				
				if(result == null || (result != null && !result.equals("File added successfully!"))){
					LawyersUtils.populateError(ctx, "DocUploadError", "Policy could not be issued");
					issuePolicy=false;
					return;
				}
						
				if(result != null && result.equals("File added successfully!")){		
					String DocFileName = "Policy Form_" + ctx.get("QuoteNumber").toString()+ "_" + ctx.get("UploadedTime").toString() + ".pdf";	
					String DocTitle = "Policy Form_" + ctx.get("QuoteNumber").toString();
					ctx.put("DocTitle", DocTitle);
					DocFileName = DocFileName.replace(" ", "").replace(":", "").replace("-", "");
					ctx.put("DocFileName", DocFileName);
					ctx.put("DocType", "PF");
					ctx.put("UploadedType", "Auto");
					setFormUrl(ctx, "DocUrl");			
					insertInDocumentArchive(ctx);
				}
				
				
				if(out != null)
					out.close();
				attachments.add(outFile);
				
				//Application PDF
				String htmlDir = SystemProperties.getInstance().getString("html.basedir");
				outFile = htmlDir + "data//Lawyers_" + ctx.get("QuoteNumber").toString() + ".pdf";
				//pdfFile = new File(outFile);
				out = new java.io.FileOutputStream(outFile);
				out = new java.io.BufferedOutputStream(out);
				
				
				if(ctx.get("PolicyStatusKey") != null && "2".equals(ctx.get("PolicyStatusKey").toString())){
					new DownloadPDF().makeRenewPdf((Context) ctx, out, baseUrl, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER));
				}else{
					new DownloadPDF().makePdf((Context) ctx, out, baseUrl, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER));
				}
				
				
				//ByteArrayOutputStream baos = new PrintPDF().makePdf((Context)ctx);
				//baos.writeTo(out);
				if(out != null)
					out.close();				
				
				RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");
				if(ctx.get("LastUpdateTimeStamp") != null)
					ctx.put("IssuedDate",new SimpleDateFormat(DATE_PATTERN).format(ctx.get("LastUpdateTimeStamp")));
					
				logger.debug("Policy Issue Date: "+ctx.get("IssuedDate"));
				//SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementformupdateIssuedDate", ctx);
				
				ctx.remove("IssuedDate");
				ctx.remove("PolicyNumber");		
				ctx.put("IsDocumentProcessed","Y");
				issuePolicy=true;
				//Commention this as No longer application will be attached with the Issued mail 10 October 2015				
				//attachments.add(outFile);
				
		}
		catch (Exception e){
			issuePolicy=false;
			ctx.put("IsDocumentProcessed", "N");
			logger.debug("processDocumentManagment failed  ........" + e);
		}
				
		if(!issuePolicy){
			logger.debug(issuePolicy);
			try{
				if(true)
					throw new PolicyNumberException();
					logger.debug("Going to rollback all database changes for issuing policy");
					new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,VersionSequence,policyNumber,UserNo");
					SqlResources.getSqlMapProcessor(ctx).update("Policy.RollbackIssuePolicyUpdateStateus_p",ctx);
			} 
			catch (Exception e1){
				issuePolicy=false;
				logger.debug("Exception occured during rollback all database changes for issuing policy ");
				e1.printStackTrace();
			}
		}
		logger.debug("issuing policy :  "+issuePolicy+" for policynumber :"+policyNumber+" having quote number"+ctx.get("QuoteNumber"));
		
		if(issuePolicy){
			
			try{
				String insuredEmail = "";
				boolean isPAAndDefenseInside = false ;
				boolean producerCodeExist =false;
				boolean isSpecialIssuance =false;
				String adminEmail = LawyersUtils.getAdminEmailID (ctx);
				
				// Do not send mail to insured if producer code is there
				producerCodeExist = new LawyersValidationUtils().checkIfSubProducerExist(ctx);
				logger.debug("Producer Code Exist ? " + producerCodeExist);
				if(!producerCodeExist){					
					insuredEmail = LawyersUtils.getInsuredEmail(ctx); 
				}
				logger.debug("Check is special issuance ");
				isSpecialIssuance = LawyersValidationUtils.checkIfSpecialIssuance(ctx);
				logger.debug("Is Special Issuance ? " + isSpecialIssuance);
				
				// Asked By Pat 10/16/2017				
				logger.debug("Check if PA and defense Inside");				
				if(ctx.get("StateCode") != null && "PA".equals(ctx.get("StateCode").toString())){
					isPAAndDefenseInside = LawyersValidationUtils.checkForFinalisedQuoteWithInsideLimit(ctx);
				}
				logger.debug("isPAAndDefenseInside ? " +  isPAAndDefenseInside);				
				if(isSpecialIssuance || producerCodeExist || isPAAndDefenseInside){
					logger.debug("Inside Special Issuance and Producer Code exist block . Getting Special Issuance Email");
					String specialIssuanceMail = LawyersValidationUtils.getSpecialIssuanceEmail(ctx);
					insuredEmail = specialIssuanceMail ;
				}
				
				if(isSpecialIssuance || producerCodeExist){
					logger.debug("Inside Special Issuance and Producer Code exist block. Getting Special Issuance Email");
					String specialIssuanceMail = LawyersValidationUtils.getSpecialIssuanceEmail(ctx);
					insuredEmail = specialIssuanceMail ;
				}
				
				if("".equals(insuredEmail))
					insuredEmail = adminEmail;			
				
				logger.debug("Going to send mail to " + insuredEmail);
				String policy ="Issued PolicyNumber  " + policyNumber;
				
				if("".equals(adminEmail))
					return;	
				
				MailSender mailSender = new MailSender();				
				mailSender.setSubject(policy);

				mailSender.setIsSentToCC("Y");
				logger.debug("Is Manual Quote  " + IsManualPremium);
				if(!IsManualPremium && !producerCodeExist){						
					mailSender.setToAdd(insuredEmail);
				}
				if(isSpecialIssuance || producerCodeExist || isPAAndDefenseInside)
					mailSender.setToAdd(insuredEmail);
				
				mailSender.setCcAdd(adminEmail);
				mailSender.setContentType("text/html");			
				mailSender.setBody(generatePolicyIssueBody(ctx));	
				mailSender.setAttachments(attachments);				
				mailSender.sendMail();
				
				for (int i=0; i<attachments.size(); i++){
					//File Delete
					File file = new File(attachments.get(i).toString());
					if(file.exists())
						file.delete();
				}				
		
			}
			catch(Exception e){
			logger.debug("Exception occured during sending policy to insured ");
			}
		}
		else
			logger.debug("Unable to send policy form due to Exception occured during policy generation ");
		
		logger.debug("processDocumentManagment end  ........");
	}
	*/
	public void processSignedDocumentManagment (IContext ctx) throws Exception {
		logger.debug("processSignedDocumentManagment started  ........ " + ctx.get("QuoteNumber"));
		boolean issuePolicy=false,IsManualPremium=false ;
		String policyNumber ="";
		List attachments  = new ArrayList();
		File pdfFile = null;
		//PolicyNumber generation and Update
		try{
				new LawyersUtils().setImagesPathToTemplate(ctx);
				logger.debug("Checking if the Policy is not already Issued " + ctx.get("PolicyKey"));
	
				Object objcheck = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetStatusKeyFinalisedQuote",ctx);
				if (objcheck != null && objcheck instanceof List) 
				{
					List objList = (List) objcheck;
					if (objList != null && objList.size() > 0) {
						Map objMap = (Map) objList.get(0);
						String statusKey = objMap.get("StatusKey") != null ? objMap.get("StatusKey").toString() : "";
						 policyNumber = objMap.get("PolicyNumber") != null ? objMap.get("PolicyNumber").toString() : "";
						logger.info("StatusKey of PolicyKey " + ctx.get("PolicyKey") + "and TransactionSequence " + ctx.get("FinalisedQuote_TransactionSequence") + " is " + statusKey);
						logger.info("PolicyNumber of PolicyKey " + ctx.get("PolicyKey") + " is " + policyNumber);
	
						if ("6".equals(statusKey) || !"".equals(policyNumber)) {
							logger.info("Going to Return as Policy is already Issued");
							return;
						}
					}
				}
				
				policyNumber = LawyersUtils.generatePolicyNumber(ctx);
				ctx.put("PolicyNumber", policyNumber);
				logger.debug("Policy Number Generated is --- " + policyNumber);
				LawyersUtils.populateLastUpdateTimeStamp(ctx);
				
				/*new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,VersionSequence,PolicyNumber,UserNo");
				SqlResources.getSqlMapProcessor(ctx).update("Policy.IssuePolicyUpdateStateus_p",ctx);*/
				
				logger.debug("Policy Number to "+policyNumber+" updated and Policy Status updated to ISSUED");
				
				/*List getPolicyNumber=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyNumber",ctx);
				ctx.putAll((Map) getPolicyNumber.get(0));
				String dbPolicyNumber=ctx.get("DBPolicyNumber")!=null && !ctx.get("DBPolicyNumber").equals(HtmlConstants.EMPTY)?ctx.get("DBPolicyNumber").toString():"";
				if("".equals(dbPolicyNumber) ){
					throw new PolicyNumberException();
					
				}*/
				//SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementformupdatePolicyNumber", ctx);
				
				//Populating all keys in Context
				Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformgetPolicyKeys", ctx);
				Map keysMap = null;		
				if(obj != null && obj instanceof Map){
					keysMap = (Map) obj;
					ctx.putAll(keysMap);
				}		
					
				obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformCoveragePolicy", ctx);
				Map policyCovMap = null;
				
				if(obj != null && obj instanceof Map){
					policyCovMap = (Map) obj;
					ctx.putAll(policyCovMap);
				}		
				
				obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformgetPriorPolicyInfo", ctx);
				Map policyInfo = null;
				
				if(obj != null && obj instanceof Map){
					policyInfo = (Map) obj;
					ctx.putAll(policyInfo);
				}
				
				logger.debug("Populated all keys in Context...............");
				
				// Get Finalised Quote
				QuoteLetter.getFinalisedQuote(ctx);
				logger.debug("Get Finalised Quote.............................");
				
				List listFormID = LawyersUtils.populateFinalisedPolicyForm(ctx);
				
				//List listFormID = getPolicyFormID(ctx);	
				IsManualPremium = checkManualFlagWithFinalisedOption(ctx);
				
			 	//if(!(dollarDefenseFlag )){
				
				String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//Policy Form_" + ctx.get("QuoteNumber").toString() + ".pdf";		
				pdfFile = new File(outFile);
				//String outFile = "D://Policy Form_QN-0069303.pdf";
				
				String baseUrl = null;
				if(ctx.get("baseUrl") != null)
					baseUrl = ctx.get("baseUrl").toString();

				
				baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
				
				ServletContextURIResolver uriResolver = null;
				
				if(ctx.get("DocumentUriResolver") != null)
					uriResolver = (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
				
				try (OutputStream out = new java.io.BufferedOutputStream(new FileOutputStream(pdfFile))) {
					new DownloadForm().generateForm((Context)ctx, listFormID, out, baseUrl, uriResolver);
				}
				
				//Start Form Signing ByteArrayOutputStream
				HttpServletRequest request= (HttpServletRequest) ctx.get("DocumentRequest");
				HttpServletResponse response=(HttpServletResponse) ctx.get("DocumentResponse");
				
				byte[] pdfBytes = IOUtils.readFileInBinary(outFile);
				int pageNo = LawyersUtils.getPageNumber(outFile, "SIGNATURE*");
		        Map<String, Double> xyMap = LawyersUtils.getCoordinateXY (outFile, "SIGNATURE*", pageNo);        
		        if(pdfFile.exists())
		        	pdfFile.delete();
		        
		        String assureURL = processFormSign((Context)ctx, request, response, pdfBytes, pageNo, xyMap);			    
			    request.getSession().putValue("IsFormSigned", "N");
			    
			    if(assureURL != null && !"".equals(assureURL))
			    {    
			         request.getSession().putValue("IsFormSigned", "Y");
			         ctx.put("IsFormSigned", "Y");
			         response.sendRedirect(assureURL);                              
			         return;			         
			    }
			
		}
		catch (Exception e){
			issuePolicy=false;
			ctx.put("IsDocumentProcessed", "N");
			logger.debug("processSignedDocumentManagment failed  ........" + e);
		}
				
		logger.debug("processSignedDocumentManagment end  ........");
	}
	
	public void processSignedDocumentManagmentSubmitted(IContext ctx) throws Exception {
		logger.debug("processSignedDocumentManagmentSubmitted started  ........ " + ctx.get("isSignedForm"));
		boolean issuePolicy=false,IsManualPremium=false ;
		String policyNumber ="";
		List attachments  = new ArrayList();		
		//PolicyNumber generation and Update
		try{
				new LawyersUtils().setImagesPathToTemplate(ctx);
				logger.debug("Checking if the Policy is not already Issued " + ctx.get("PolicyKey"));
	
				/*Object objcheck = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetStatusKeyFinalisedQuote",ctx);
				if (objcheck != null && objcheck instanceof List) 
				{
					List objList = (List) objcheck;
					if (objList != null && objList.size() > 0) {
						Map objMap = (Map) objList.get(0);
						String statusKey = objMap.get("StatusKey") != null ? objMap.get("StatusKey").toString() : "";
						 policyNumber = objMap.get("PolicyNumber") != null ? objMap.get("PolicyNumber").toString() : "";
						logger.info("StatusKey of PolicyKey " + ctx.get("PolicyKey") + "and TransactionSequence " + ctx.get("FinalisedQuote_TransactionSequence") + " is " + statusKey);
						logger.info("PolicyNumber of PolicyKey " + ctx.get("PolicyKey") + " is " + policyNumber);
	
						if ("6".equals(statusKey) || !"".equals(policyNumber)) {
							logger.info("Going to Return as Policy is already Issued");
							//return;
						}
					}
				}
				
				policyNumber = LawyersUtils.generatePolicyNumber(ctx);
				ctx.put("PolicyNumber", policyNumber);
				logger.debug("Policy Number Generated is --- " + policyNumber);
				*/
				/*LawyersUtils.populateLastUpdateTimeStamp(ctx);
			
				new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,VersionSequence,PolicyNumber,UserNo");
				SqlResources.getSqlMapProcessor(ctx).update("Policy.IssuePolicyUpdateStateus_p",ctx);
				
				logger.debug("Policy Number to "+policyNumber+" updated and Policy Status updated to ISSUED");*/
				
				/*List getPolicyNumber=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyNumber",ctx);
				ctx.putAll((Map) getPolicyNumber.get(0));
				String dbPolicyNumber=ctx.get("DBPolicyNumber")!=null && !ctx.get("DBPolicyNumber").equals(HtmlConstants.EMPTY)?ctx.get("DBPolicyNumber").toString():"";
				if("".equals(dbPolicyNumber) ){
					throw new PolicyNumberException();
					
				}*/
				//SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementformupdatePolicyNumber", ctx);
				
				//Populating all keys in Context
				Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformgetPolicyKeys", ctx);
				Map keysMap = null;		
				if(obj != null && obj instanceof Map){
					keysMap = (Map) obj;
					ctx.putAll(keysMap);
				}		
					
				obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformCoveragePolicy", ctx);
				Map policyCovMap = null;
				
				if(obj != null && obj instanceof Map){
					policyCovMap = (Map) obj;
					ctx.putAll(policyCovMap);
				}		
				
				obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformgetPriorPolicyInfo", ctx);
				Map policyInfo = null;
				
				if(obj != null && obj instanceof Map){
					policyInfo = (Map) obj;
					ctx.putAll(policyInfo);
				}
				
				logger.debug("Populated all keys in Context...............");
				
				uploadSignedFormDocument(ctx, attachments);
				
				/*String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//Policy Form_" + ctx.get("QuoteNumber").toString() + ".pdf";		
				File pdfFile = new File(outFile);
				OutputStream out = new java.io.FileOutputStream(pdfFile);
				out = new java.io.BufferedOutputStream(out);				
				
				String baseUrl = null;
				if(ctx.get("baseUrl") != null)
					baseUrl = ctx.get("baseUrl").toString();
				
				baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
				
				ServletContextURIResolver uriResolver = null;				
				if(ctx.get("DocumentUriResolver") != null)
					uriResolver = (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
				
				//Application PDF
				String htmlDir = SystemProperties.getInstance().getString("html.basedir");
				outFile = htmlDir + "data//Lawyers_" + ctx.get("QuoteNumber").toString() + ".pdf";
				out = new java.io.FileOutputStream(outFile);							
				
				if(ctx.get("PolicyStatusKey") != null && "2".equals(ctx.get("PolicyStatusKey").toString())){
					new DownloadPDF().makeRenewPdf((Context) ctx, out, baseUrl, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER));
				}else{
					new DownloadPDF().makePdf((Context) ctx, out, baseUrl, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER));
				}
				
				if(out != null)
					out.close();*/				
				
		/*		RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");
				if(ctx.get("LastUpdateTimeStamp") != null)*/
					ctx.put("IssuedDate",new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
					
				logger.debug("Policy Issue Date: "+ctx.get("IssuedDate"));
				//SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementformupdateIssuedDate", ctx);
				
				ctx.remove("IssuedDate");
				ctx.remove("PolicyNumber");		
				ctx.put("IsDocumentProcessed","Y");
				issuePolicy=true;
				//Commention this as No longer application will be attached with the Issued mail 10 October 2015				
				//attachments.add(outFile);
				
		}
		catch (Exception e){
			issuePolicy=false;
			ctx.put("IsDocumentProcessed", "N");
			logger.debug("processDocumentManagment failed  ........" + e);
		}
				
		if(!issuePolicy){
			logger.debug(issuePolicy);
			try{
				logger.debug("Going to rollback all database changes for issuing policy");
				new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,VersionSequence,policyNumber,UserNo");
				SqlResources.getSqlMapProcessor(ctx).update("Policy.RollbackIssuePolicyUpdateStateus_p",ctx);
			} 
			catch (Exception e1){
				issuePolicy=false;
				logger.debug("Exception occured during rollback all database changes for issuing policy ");
				logger.error("Unexpected error", e1);
			}
		}
		logger.debug("issuing policy :  "+issuePolicy+" for policynumber :"+policyNumber+" having quote number"+ctx.get("QuoteNumber"));
		
		if(issuePolicy){			
			try{
				String insuredEmail = "";
				boolean isPAAndDefenseInside = false ;
				boolean producerCodeExist =false;
				boolean isSpecialIssuance =false;
				String adminEmail = LawyersUtils.getAdminEmailID (ctx);
				
				// Do not send mail to insured if producer code is there
				producerCodeExist = new LawyersValidationUtils().checkIfSubProducerExist(ctx);
				logger.debug("Producer Code Exist ? " + producerCodeExist);
				if(!producerCodeExist){					
					insuredEmail = LawyersUtils.getInsuredEmail(ctx); 
				}
				logger.debug("Check is special issuance ");
				isSpecialIssuance = LawyersValidationUtils.checkIfSpecialIssuance(ctx);
				logger.debug("Is Special Issuance ? " + isSpecialIssuance);
				
				// Asked By Pat 10/16/2017				
				logger.debug("Check if PA and defense Inside");				
				if(ctx.get("StateCode") != null && "PA".equals(ctx.get("StateCode").toString())){
					isPAAndDefenseInside = LawyersValidationUtils.checkForFinalisedQuoteWithInsideLimit(ctx);
				}
				logger.debug("isPAAndDefenseInside ? " +  isPAAndDefenseInside);				
				if(isSpecialIssuance || producerCodeExist || isPAAndDefenseInside){
					logger.debug("Inside Special Issuance and Producer Code exist block . Getting Special Issuance Email");
					String specialIssuanceMail = LawyersValidationUtils.getSpecialIssuanceEmail(ctx);
					insuredEmail = specialIssuanceMail ;
				}
				
				if(isSpecialIssuance || producerCodeExist){
					logger.debug("Inside Special Issuance and Producer Code exist block. Getting Special Issuance Email");
					String specialIssuanceMail = LawyersValidationUtils.getSpecialIssuanceEmail(ctx);
					insuredEmail = specialIssuanceMail ;
				}
				
				if("".equals(insuredEmail))
					insuredEmail = adminEmail;			
				
				logger.debug("Going to send mail to " + insuredEmail);
				String policy ="Issued PolicyNumber  " + policyNumber;
				
				if("".equals(adminEmail))
					return;	
				
				MailSender mailSender = new MailSender();				
				mailSender.setSubject(policy);

				mailSender.setIsSentToCC("Y");
				logger.debug("Is Manual Quote  " + IsManualPremium);
				if(!IsManualPremium && !producerCodeExist){						
					mailSender.setToAdd(insuredEmail);
				}
				if(isSpecialIssuance || producerCodeExist || isPAAndDefenseInside)
					mailSender.setToAdd(insuredEmail);
				
				mailSender.setCcAdd(adminEmail);
				mailSender.setContentType("text/html");			
				mailSender.setBody(generatePolicyIssueBody(ctx));	
				mailSender.setAttachments(attachments);				
				mailSender.sendMail();
				
				/*for (int i=0; i<attachments.size(); i++){
					//File Delete
					File file = new File(attachments.get(i).toString());
					if(file.exists())
						file.delete();
				}*/				
		
			}
			catch(Exception e){
			logger.debug("Exception occured during sending policy to insured ");
			}
		}
		else
			logger.debug("Unable to send policy form due to Exception occured during policy generation ");
		
		logger.debug("processDocumentManagment end  ........");
	}

	

	private boolean checkManualFlagWithFinalisedOption(IContext ctx) throws Exception {
		
		boolean dollarDefenseFlag = false;	
		
		Map mapDollarDefense = null;
		int dollarDefenceSize=0;
		Object objDollarDefense = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetManualFinalised", ctx);
		
		if(objDollarDefense != null && objDollarDefense instanceof Map)
			mapDollarDefense = (Map) objDollarDefense;							
		
		if( mapDollarDefense.get("IsManualPremium")!= null)
		{			
			if(Integer.parseInt(mapDollarDefense.get("IsManualPremium").toString()) > 0)				
				 dollarDefenseFlag = true;
		}
		
		return dollarDefenseFlag;
	}

	public String uploadFormDocument (IContext ctx) throws Exception {
		
		String userName = getUserName();
		String password = getUserPassword();
		String domain = getDomainName();
		String baseDir = getSharePointBaseDirectory();
		String url = getSharePointURL();
		
		String docName = "Policy Form_" + ctx.get("QuoteNumber").toString()+ "_" + ctx.get("UploadedTime").toString() + ".pdf";
		docName = docName.replace(" ", "").replace(":", "").replace("-", "");
		String docLibPathName = url + baseDir;
		String folderName = ctx.get("QuoteNumber").toString();		
		
//		String userName = "sanjayr";
//		String password = "Sanr55.bgp";
//		String domain = "in";
//		String baseDir = "TestJava";
		
		String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//Policy Form_" + ctx.get("QuoteNumber").toString() + ".pdf";
		//byte[] docContent = DocManagementUtil.getBytesFromFile(new File(SystemProperties.getInstance().getString("html.basedir") + "data//Policy Form_" + ctx.get("QuoteNumber").toString() + ".pdf"));
		//return new DocManagementUtil().uploadDocToSharePoint(docLibPathName, folderName, baseDir, docName, docContent, userName, password, domain);
		//Raghu
		return new DocManagementUtil().uploadDocToSharePoint(docLibPathName,
				folderName, baseDir, docName, outFile, userName,
				password, domain, url);
	}
	
	public String uploadSignedFormDocument(IContext ctx, List attachments) throws Exception {

		String assuresignURL = SystemProperties.getInstance().getString("esign.doc.retrieve.url");
		String SignedDocumentID = null;
		if (ctx.get("SignedDocumentID") != null)
			SignedDocumentID = ctx.get("SignedDocumentID").toString();

		String SignedDocumentToken = null;
		if (ctx.get("SignedDocumentToken") != null)
			SignedDocumentToken = ctx.get("SignedDocumentToken").toString();

		if (assuresignURL == null || SignedDocumentID == null || SignedDocumentToken == null)
			return null;

		byte[] docContent = new EsignUtil().getDocument(assuresignURL, ctx.get("SignedDocumentID").toString(), ctx.get("SignedDocumentToken").toString());
		
		LawyersUtils.populateLastUpdateTimeStamp(ctx);
		String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//Policy Form_" + ctx.get("QuoteNumber").toString() + ".pdf";
		FileOutputStream stream = new FileOutputStream(outFile);
	    try {
	        stream.write(docContent);
	    } finally {
	        stream.close();
	    }
	    attachments.add(outFile);
	    
		String userName = getUserName();
		String password = getUserPassword();
		String domain = getDomainName();
		String baseDir = getSharePointBaseDirectory();
		String spUrl = getSharePointURL();

		String docName = "Policy_Form_" + ctx.get("QuoteNumber").toString() + "_" + ctx.get("UploadedTime").toString() + ".pdf";
		docName = docName.replace(" ", "").replace(":", "").replace("-", "");
		String docLibPathName = spUrl + baseDir;
		String folderName = ctx.get("QuoteNumber").toString();		
		String url = docLibPathName + "/" + folderName + "/" + docName;
				
		String result = "";
		String skipUpload = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".skipupload");
		if(!"Y".equals(skipUpload)){
			//result = new DocManagementUtil().uploadDocToSharePoint(docLibPathName, folderName, baseDir, docName, docContent, userName, password, domain);
			//Raghu
			result = new DocManagementUtil().uploadDocToSharePoint(docLibPathName,
					folderName, baseDir, docName, outFile, userName,
					password, domain, spUrl);
		}else{
			result = "File added successfully!";
		}
		logger.debug("Result from uploadFormDocument(ctx) in processDocumentManagment " + result);
		
		if(result != null && result.equals("File added successfully!")){		
			String DocFileName = "Policy Form_" + ctx.get("QuoteNumber").toString()+ "_" + ctx.get("UploadedTime").toString() + ".pdf";	
			String DocTitle = "Policy Form_" + ctx.get("QuoteNumber").toString();
			ctx.put("DocTitle", DocTitle);
			DocFileName = DocFileName.replace(" ", "").replace(":", "").replace("-", "");
			ctx.put("DocFileName", DocFileName);
			ctx.put("DocType", "PF");
			ctx.put("UploadedType", "Auto");
			ctx.put("DocUrl", url);
			//setFormUrl(ctx, "DocUrl");			
			insertInDocumentArchive(ctx);
		}	
		return result;
	}
	
	public static List getPolicyFormID(IContext ctx)throws Exception{
		
		// To be converted into he proc
		List listFormID = new ArrayList();
		List listPolicyForm = null;
		
		List listPolicyCommonForm = null;
		List listPolicyStateForm = null;
		
		Object objPolicyCommonForm = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementformgetCommonForms", ctx);
		
		Object objPolicyStateForm = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementformgetStateForms", ctx);
		
		if(objPolicyCommonForm != null)
			listPolicyCommonForm = (List) objPolicyCommonForm;
		
		if(objPolicyStateForm != null)
			listPolicyStateForm = (List) objPolicyStateForm;
		
		if(listPolicyCommonForm == null && listPolicyStateForm==null)			
			return listFormID;			
		
		listFormID.addAll(listPolicyCommonForm);		
		listFormID.addAll(listPolicyStateForm);
		
		return listFormID;		
	}
	
	private String generatePolicyIssueBody(IContext ctx) throws Exception
    {
		StringBuilder msg = new StringBuilder(1536);
		int companyKey=Integer.parseInt(ctx.get("CompanyKey").toString());
		msg.append("<table><tr><td>");
		
		msg.append("Dear ").append(ctx.get("AccountName")).append(",<br/><br/>");
		msg.append("Thank you for choosing Protexure Lawyers for your insurance needs.  I am pleased to attach your Lawyers Professional Liability Policy, underwritten by ");
		if(companyKey!=3)
			msg.append("United States Fire Insurance Company. <br/><br/>");
		if(companyKey==3)
			msg.append("ISMIE Mutual Insurance Company.<br/><br/>");
		msg.append("Please review the attached policy carefully and advise us immediately if there are any conflicts between the actual policy and the information provided by us throughout the process.<br/><br/>");
		
		if(companyKey!=3)
		{
			msg.append("As a policyholder, you have access to our Lawyers' Risk Management Hotline - at no charge.  We provide confidential advice on questions about malpractice risks and other day-to-day legal issues that may affect your business.  Call 1-888-959-2786 and have your policy number available.<br/><br/>");
		}
		msg.append("If you have any additional questions, contact us at 877-569-4111 and one of our experienced Underwriting Specialists will be happy to assist you.<br/><br/>");
		msg.append("We appreciate your business and look forward to renewing your policy for many years to come.<br/><br/>");
		
		msg.append("Best Regards,<br/><br/>");
		msg.append("Kyle Nieman<br/>");
		msg.append("President/CEO<br/>");
		msg.append("Protexure<br/>");
		msg.append("</td></tr></table>");
		
		return msg.toString();
		
    }
	
	private String generateSubproducerPolicyIssueBody(IContext ctx) throws Exception
    {
		String SignatureText=ctx.get("SignatureText")!=null?ctx.get("SignatureText").toString():"";
		String fax=null;
		int companyKey=Integer.parseInt(ctx.get("CompanyKey").toString());
		
		if(ctx.get("Fax") != null && !"".equals(ctx.get("Fax")) && !"null".equals(ctx.get("Fax"))){
			fax="F:&nbsp;"+ctx.get("Fax").toString();
		}
		else{
			fax="";
		}
		String emailsig="";
		 if(ctx.get("EmailSignature")!=null && !"".equals(ctx.get("EmailSignature"))){
			 emailsig="<img src=\"cid:image1\">";
		 }
		 
		 String SignatureText2="";
		 if(ctx.get("SignatureText2")!=null && !"".equals(ctx.get("SignatureText2"))){
			 SignatureText2=ctx.get("SignatureText2").toString();
		 }
		 
		 String SignatureText3="";
		 if(ctx.get("SignatureText3")!=null && !"".equals(ctx.get("SignatureText3"))){
			 SignatureText3="<td style='border-left: 3px solid green; padding-left: 5px'><img src=\"cid:image2\"></td>";
			}
		 
		 String Signature="";
		 if(ctx.get("Signature")!=null && !"".equals(ctx.get("Signature"))){
			 Signature="<tr><td><img src=\"cid:image\"></td></tr>";
		 }
		 
		 String subProducerCode = SystemProperties.getInstance().getString("appl.LawyersIns.subproducer.signaturedisplay");
	     	String[] subProducerCodeList = subProducerCode.split("~");
	     	for(int subProducerCodeCount = 0; subProducerCodeCount < subProducerCodeList.length; subProducerCodeCount++){
	     		if(subProducerCodeList[subProducerCodeCount].equalsIgnoreCase(ctx.get("ProducerCode").toString())){
	     			Signature = "";
	     		}
	     	}
		 
		StringBuilder msg = new StringBuilder(2048);
		msg.append("<table><tr><td>");
		
		msg.append("Dear ").append(ctx.get("AccountName")).append(",<br/><br/>");
		msg.append("Thank you for choosing ").append(ctx.get("ProducerName")).append(" lawyers for your insurance needs.  I am pleased to attach your Lawyers Professional Liability Policy, underwritten by ");
		if(companyKey==3)
			msg.append("ISMIE Mutual Insurance Company.<br/><br/>");
		if(companyKey!=3)
			msg.append("United States Fire Insurance Company.<br/><br/>");
		
		msg.append("Please review the attached policy carefully and advise us immediately if there are any conflicts between the actual policy and the information provided by us throughout the process.<br/><br/>");
		if(companyKey!=3)
		msg.append("As a policyholder, you have access to our Lawyers' Risk Management Hotline - at no charge.  We provide confidential advice on questions about malpractice risks and other day-to-day legal issues that may affect your business.  Call  1-888-959-2786 and have your policy number available.<br/><br/>");
		
		msg.append("If you have any additional questions, contact us at ").append(ctx.get("SPPhoneNumber")).append(" and one of our experienced Underwriting Specialists will be happy to assist you.<br/><br/>");
		msg.append("We appreciate your business and look forward to renewing your policy for many years to come.<br/><br/>");
		
		msg.append("Best Regards,<br/><br/>");
		if("P0000012".equals(ctx.get("ProducerCode"))) {
			msg.append("<table cellpadding='0' cellspacing='0'>").append(Signature).append("<tr><td>").append(SignatureText).append("P:&nbsp; (866)940-1101  &nbsp;</br>").append(fax).append("</td>").append(SignatureText3).append("</tr></table>");
		} else {
			msg.append("<table cellpadding='0' cellspacing='0'>").append(Signature).append("<tr><td>").append(SignatureText).append("P:&nbsp; (314)808-6865 &nbsp;</br>").append(fax).append("</td>").append(SignatureText3).append("</tr></table>");
		}
		msg.append(emailsig).append("</br>");
        msg.append("</br>");
        msg.append("<p style='color:#999999'>").append(SignatureText2).append("</p>");
		msg.append("</td></tr></table>");
		
		return msg.toString();
		
    }
	
	
	public void uploadOffRiskLetter(IContext ctx) throws Exception {

		String htmlDir = SystemProperties.getInstance().getString(
				"html.basedir");
		String outFile = htmlDir + "data//OffRiskLetters_" + ctx.get("AccountNameOffRisk") +".pdf";
				

		// Uploading Quote letter
		LawyersUtils.populateLastUpdateTimeStamp(ctx);
		String result = new DocumentManagment().uploadDocument(ctx, outFile);
		logger.debug("Result from DocumentManagment().uploadDocument(ctx, outFile); " + result);
		String DocFileName = outFile.substring(outFile.lastIndexOf("//") + 2,
				outFile.length());
		logger.debug("DocFileName - - " + DocFileName);
		if (result != null) {
			// ctx.put("DocTitle", DocFileName);
			
			int index = DocFileName.lastIndexOf(".");
			String fileName1 = DocFileName.substring(0, index);
			ctx.put("DocTitle", fileName1);
			logger.debug("DocTitle - - " + ctx.get("DocTitle"));
			String fileExt = DocFileName.substring(index + 1, DocFileName
					.length());
			DocFileName = fileName1 + "_" + ctx.get("UploadedTime").toString()
					+ "." + fileExt;
			DocFileName = DocFileName.replace(" ", "").replace(":", "")
					.replace("-", "");

			ctx.put("Comment", "");
			ctx.put("DocFileName", DocFileName);
			logger.debug("DocFileName - - " + ctx.get("DocFileName"));
			ctx.put("UploadedType", "Auto");

			ctx.put("DocType", "OR");

			setUrl(ctx, DocFileName, "DocUrl");
			logger.debug("DocUrl - - " + ctx.get("DocUrl"));
		}
		// End uploading Quote letter
	}
	
	public void downloadDocumentOffRisk(IContext ctx) throws Exception {
		
		FileOutputStream outputStream = null;
		ByteArrayOutputStream bout = null;
		
		try {
			String uploaddirectorypath = SystemProperties.getInstance().getString("offriskupload.uploaddirectory");
			String fileName = uploaddirectorypath + ctx.get("DocFileName").toString();
			outputStream = new FileOutputStream(new File(fileName));
			
			String userName = getUserName();
			String password = getUserPassword();
			String domain = getDomainName();
			String baseDir = getSharePointBaseDirectory();
	
			String docUrl = null;
			if (ctx.get("DocUrl") != null)
				docUrl = ctx.get("DocUrl").toString();
	
			if (docUrl == null)
				return;
	
			byte[] barr = new DocManagementUtil().downloadDocFromSharePoint(docUrl,
					userName, password, domain);
	
			bout = new ByteArrayOutputStream();
			bout.write(barr);
			bout.writeTo(outputStream);
			
			outputStream.flush();
			bout.close();
			outputStream.close();
		}catch(Exception ex){
			logger.error("Unexpected error", ex);
			logger.error("error in downloadDocumentOffRisk in DocumentManagement::::"+ex.getMessage());
		}finally{
			if(bout != null){
				bout.close();
				bout = null;
			}
			if(outputStream != null){
				outputStream.close();
				outputStream = null;
			}
		}
		

	}
	
	public static void processESignature(IContext ctx)throws Exception
	{
		
		HttpServletRequest request= (HttpServletRequest) ctx.get("DocumentRequest");
		HttpServletResponse response=(HttpServletResponse) ctx.get("DocumentResponse");
		try {
			 Object objLicenseNumberRequired = RuleUtils.executeRule(ctx, "LawyersRule.fillLicenseNumberInsured");
             boolean flagLicenseNumberRequired = false;              
             if(objLicenseNumberRequired instanceof Boolean)
                  flagLicenseNumberRequired = (Boolean) objLicenseNumberRequired;   
             
             
             
             if(flagLicenseNumberRequired) {
                  Context newCtx = new Context();
                  newCtx.setProject(ctx.getProject());
                  newCtx.put("PolicyKey", ctx.get("PolicyKey"));
                  newCtx.put("UserNo", ctx.get("UserNo"));
                  newCtx.put("FirstReviewUserID", ctx.get("UserNo"));
                  newCtx.put("IsLicenseNumberUpdated", "Y");
		          newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
		          newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
                  
                  Object objUser = SqlResources.getSqlMapProcessor(newCtx).findByKey("UserDetails.findByKey", newCtx);
                  if(objUser != null)
                  {
                       if(((Map)objUser).get("LicenseNumber") != null){
                            newCtx.put("LicenseNumber", ((Map)objUser).get("LicenseNumber"));
                            SqlResources.getSqlMapProcessor(newCtx).update("SqlStmts.UserStatementprocessQuoteupdateLicenseNumberInsured", newCtx);
                       }
                  }
             }
         }    
         catch (Exception e){
             logger.error("Error in updating LicenseNumber for Insured------"+e.getMessage());
             throw e;
         }
         
		    String assureURL = processAssureSign((Context)ctx,request,response);
		    
		    request.getSession().putValue("isSigned", "N");
		    
		    if(assureURL != null && !"".equals(assureURL))
		    {    
		         request.getSession().putValue("ESModeCopy", ctx.get("ESModeCopy"));
		         request.getSession().removeValue("ESMode"); 
		         request.getSession().putValue("isSigned", "Y");
		         response.sendRedirect(assureURL);                              
		         return;
		         
		    }
		} 
	
	private static  String processAssureSign(Context ctx, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ByteArrayOutputStream bufferout = null;
		OutputStream out = null;
		File file = null;
		String assuresignurl = "";
		
		try { 
	        logger.debug("ES Value: " + ctx.get("ESMode").toString());
	        logger.debug("AccountName: " + ctx.get("AccountName").toString());
	        logger.debug("AccountEmail: " + ctx.get("AccountEmail").toString());
	        logger.debug("PolicyEffectiveDate: "
	                 + ctx.get("PolicyEffectiveDate").toString());
	        ServletContext servletContext = (ServletContext) ctx.get("DocumentServletContext");
	        
	        Map<String, String> data = new HashMap();
	
	        data.put("AccountName", ctx.get("AccountName").toString());
	        data.put("AccountEmail", ctx.get("AccountEmail").toString());
	        data.put("PolicyEffectiveDate", ctx.get("PolicyEffectiveDate")
	                 .toString());
	
	        
	        String htmlDir = SystemProperties.getInstance().getString(
	                 "html.basedir");
	        String outFile = htmlDir + "data//Lawyers_Application_"
	                 + ctx.get("QuoteNumber").toString() + ".pdf";
	        // out = new java.io.FileOutputStream(outFile);
	        // out = new java.io.FileOutputStream(outFile);
	        bufferout = new java.io.ByteArrayOutputStream();
	        String baseUrl = null;
	        if (ctx.get("baseUrl") != null)
	            baseUrl = ctx.get("baseUrl").toString();
	
	        ServletContextURIResolver uriResolver = null;
	        if (ctx.get("uriResolver") != null)
	            uriResolver = (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
	        
	        
	        byte[] pdfBytes = null;
	        if (ctx.get("PolicyStatusKey") != null && "2".equals(ctx.get("PolicyStatusKey").toString())) {
	        	logger.error("Going to generate New app RB PDF for Doc sign for Policy "+ctx.get("PolicyKey"));
	            new DownloadFOP().makeRenewPdfAssureSign(ctx, bufferout, baseUrl,(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER), servletContext);
	            pdfBytes = bufferout.toByteArray();
	        	
	        } else {
	        	logger.error("Going to generate New app NB PDF for Doc sign for Policy "+ctx.get("PolicyKey"));
	            new DownloadFOP().makePdfAssureSign(ctx, bufferout, baseUrl,(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER), servletContext);
	            pdfBytes = bufferout.toByteArray();
	        }        
	       
	        //To get page no and find x and y co-ordinate for signature block
	        out = new java.io.FileOutputStream(outFile);
	        out.write(pdfBytes);
	                
	        int pageNo = LawyersUtils.getPageNumber(outFile, "SIGNATURE*");
	        Map<String, Double> xyMap = LawyersUtils.getCoordinateXY (outFile, "SIGNATURE*", pageNo);        
	        file = new File(outFile);
	        if(file.exists())
	        	file.delete();
	        //End
	        
	        String contextIdentifier = null;
	        String userName = null;
	        String webServiceUrl = null;
	        String returnUrl = null;
	        
	                 
	            contextIdentifier = SystemProperties.getInstance().getString("appl."+ ctx.getProject() +".assuresign.contextIdentifier");
	            userName = SystemProperties.getInstance().getString("appl."+ ctx.getProject() +".assuresign.userName");
	            webServiceUrl = SystemProperties.getInstance().getString("esign.ws.url");
	            returnUrl = SystemProperties.getInstance().getString("esign.return.url");
	            returnUrl = returnUrl + "?jsessionid="+ctx.get("jsessionid");
	            logger.debug("Do ESign for PolicyStatusKey" + ctx.get("PolicyStatusKey"));
	            boolean oldApp=false;
				Object obj = RuleUtils.executeRule(ctx, "LawyersRule.isOldAppFlow");	
				if(obj != null && obj instanceof Boolean )
					oldApp =(Boolean) obj;
				assuresignurl = new EsignUtil().doSignature(contextIdentifier, userName, data, pdfBytes, pageNo, xyMap, returnUrl);
				/*if(oldApp==true)
				{
	            if(ctx.get("PolicyStatusKey") != null && "1".equals(ctx.get("PolicyStatusKey").toString()))
	                 assuresignurl = new EsignUtil().doSignature(contextIdentifier, userName, data, pdfBytes,4, returnUrl);
	            else
	                 assuresignurl = new EsignUtil().doSignature(contextIdentifier, userName, data, pdfBytes,3, returnUrl);
				}
				else
				{
					 if(ctx.get("PolicyStatusKey") != null && "1".equals(ctx.get("PolicyStatusKey").toString()))
		                 assuresignurl = new EsignUtil().doSignature(contextIdentifier, userName, data, pdfBytes,5, returnUrl);
		            else
		            	assuresignurl = new EsignUtil().doSignature(contextIdentifier, userName, data, pdfBytes,5, returnUrl);
				}*/
	            logger.debug("Signing document has completed");
            
        } catch (Exception e) {      
            logger.error("Unexpected error", e);
            logger.debug(e.getMessage());
            logger.debug("Problem in signing document");
        } finally {
        	/*code by sukhi 26/09/2018*/
        	if(out != null){
        		out.close();
        		out = null;
        	}
        	if(bufferout != null){
        		bufferout.close();
        		bufferout = null;
        	}
        	file = null;
        }
        return assuresignurl;
	}
	
	private static  String processFormSign(Context ctx, HttpServletRequest request, HttpServletResponse response, byte[] pdfBytes, int pageNo, Map<String, Double> xyMap) throws Exception {

        logger.debug("AccountName: " + ctx.get("AccountName").toString());
        logger.debug("AccountEmail: " + ctx.get("AccountEmail").toString());
        logger.debug("PolicyEffectiveDate: "+ ctx.get("PolicyEffectiveDate").toString());
        
        ServletContext servletContext = (ServletContext) ctx.get("DocumentServletContext");
        String assuresignurl = "";
        Map<String, String> data = new HashMap();

        data.put("AccountName", ctx.get("AccountName").toString());
        data.put("AccountEmail", ctx.get("AccountEmail").toString());
        data.put("PolicyEffectiveDate", ctx.get("PolicyEffectiveDate").toString());

        String contextIdentifier = null;
        String userName = null;
        String webServiceUrl = null;
        String returnUrl = null;
        
        try{          
            contextIdentifier = SystemProperties.getInstance().getString("appl."+ ctx.getProject() +".assuresign.contextIdentifier");
            userName = SystemProperties.getInstance().getString("appl."+ ctx.getProject() +".assuresign.userName");
            webServiceUrl = SystemProperties.getInstance().getString("esign.ws.url");
            returnUrl = SystemProperties.getInstance().getString("esign.form.return.url");
            returnUrl = returnUrl + "?jsessionid="+ctx.get("jsessionid");
            logger.debug("Do ESign for PolicyStatusKey" + ctx.get("PolicyStatusKey"));
            assuresignurl = new EsignUtil().doFormSignature(contextIdentifier, userName, data, pdfBytes, pageNo, xyMap, returnUrl);
			logger.debug("Signing document has completed");
            
        } catch (Exception e) {      
            logger.error("Unexpected error", e);
            logger.debug(e.getMessage());
            logger.debug("Problem in signing document");
        }        
        return assuresignurl;
	}

	
	public void processIndicationDocument (IContext ctx) throws Exception {		
		logger.debug("processIndicationDocument started  ........ ");			
		
		File pdfFile = null;
		OutputStream out = null;
		
		try{
			new LawyersUtils().setImagesPathToTemplate(ctx);
			
			Object objIndication = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetMaxIndicationDocumentID", ctx);
			Map mapIndication = null;	
			int version = 0;
			if(objIndication != null && objIndication instanceof Map)
			{
				mapIndication = (Map) objIndication;
				version = Integer.parseInt(mapIndication.get("IndicationVersion").toString());
				version++;
			}
			
			String formNumber ="Indication_" + version;				
			String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//" + formNumber + ".pdf";		
			pdfFile = new File(outFile);
			
			out = new java.io.FileOutputStream(pdfFile);
			out = new java.io.BufferedOutputStream(out);			
			String baseUrl = null;
			if(ctx.get("baseUrl") != null)
				baseUrl = ctx.get("baseUrl").toString();			
			baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			
			ServletContextURIResolver uriResolver = null;
			
			if(ctx.get("DocumentUriResolver") != null)
				uriResolver = (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
			
			new DownloadForm().processIndicationForm((Context)ctx, out, baseUrl, uriResolver);			
			String skipUpload = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".skipupload");
			if(!"Y".equals(skipUpload)){
				uploadIndication(ctx, outFile);
			}
			if(pdfFile.exists())
				pdfFile.delete();
					
			logger.debug("processIndicationDocument end  ........");
			pdfFile=null;
		}catch (Exception e){
			throw e;
		} finally {
			/*code by sukhi 26/09/2018*/
			if(out != null){
				out.close();
				out = null;
			}
			pdfFile=null;
		}
	}
	
	public void uploadIndication (IContext ctx, String outFile) throws Exception {
        
        LawyersUtils.populateLastUpdateTimeStamp(ctx);              
        String result = new DocumentManagment().uploadDocument(ctx, outFile);
        String DocFileName = outFile.substring(outFile.lastIndexOf("//")+2, outFile.length());
        
        if(result != null)
        {     
          int index = DocFileName.indexOf(".");
          String fileName1 = DocFileName.substring(0, index);
          ctx.put("DocTitle", fileName1);

          
          String fileExt = DocFileName.substring(index+1, DocFileName.length());
          DocFileName = fileName1 + "_" + ctx.get("UploadedTime").toString() + "."+ fileExt;
          DocFileName = DocFileName.replace(" ", "").replace(":", "").replace("-", "");
          
          ctx.put("Comment", "");                 
          ctx.put("DocFileName", DocFileName);
          
          ctx.put("UploadedType", "Auto");          
          ctx.put("DocType", "INJ");
          
          setUrl(ctx, DocFileName, "DocUrl");
          insertInDocumentArchive(ctx);
        }
        // End uploading Indication
	}

	public void processEndorementDocument (IContext ctx) {		
		logger.debug("processEndorementDocument started  ........ ");
		if(ctx.get("TransactionTypeKey") == null) {
			logger.error("Endorsement Type for Policy Number : " + ctx.get("PolicyNumber") + " was not defined");
			return;
		}
		LawyersUtils.getPolicyData((Context)ctx);
		File pdfFile = null;
		String TransactionTypeKey = ctx.get("TransactionTypeKey").toString();
		
		try{
			new LawyersUtils().setImagesPathToTemplate(ctx);			
			List getPolicyNumber=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyNumber",ctx);
			ctx.putAll((Map) getPolicyNumber.get(0));
			String dbPolicyNumber=ctx.get("DBPolicyNumber")!=null?ctx.get("DBPolicyNumber").toString():"";
			if("".equals(dbPolicyNumber)) {
				throw new PolicyNumberException();
			}
			
			
			
			//Populating all keys in Context
			Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesGetPolicyKeys", ctx);
			Map keysMap = null;		
			if(obj != null && obj instanceof Map){
				keysMap = (Map) obj;
				ctx.putAll(keysMap);
			}	
			
			

			Object obj1 = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetcancelexpirydate", ctx);
			if(obj1 != null && obj1 instanceof Map){
				keysMap = (Map) obj1;
				ctx.putAll(keysMap);
			}
				
			logger.debug("Populated all keys in Context...............");
			LawyersUtils.getPolicyData((Context)ctx);
//			boolean isRatingNew = false;
//	        Object objRatingRule = RuleUtils.executeRule(ctx,"LawyersRule.checkNewFiling");
//	        if (objRatingRule != null && objRatingRule instanceof Boolean) {
//	               isRatingNew = (Boolean) objRatingRule;
//	        }
//	        if(isRatingNew)
//	        	ctx.put("IsNewFiling", "Y");
			
			List listFormID = null;
			String fileName = null;
			String file = null;
			new DownloadForm().fetchDataForXmlEndorsement((Context)ctx);
			
			if(ctx.get("listFormID") != null){
				listFormID = (List)ctx.get("listFormID");
				if(!listFormID.isEmpty()){
					if("3".equals(ctx.get("CompanyKey").toString()))
					 file = "ALA";
					else
					 file = "LPL";
					for(int i=0; i<listFormID.size(); i++)
						file = file +"_" + (String) listFormID.get(i);
					fileName = file;
				}
			}
			if(fileName == null)
				fileName ="DEFAULT";
			
			if(TransactionTypeKey.equals(LawyersConstants.CANCELLATION_ENDORSEMENT))
				fileName=fileName+"_Cancellation_Endorsement";
			if(TransactionTypeKey.equals(LawyersConstants.REINSTATEMENT_ENDORSEMENT))
				fileName=fileName+"_Reinstatement_Endorsement";
			if(TransactionTypeKey.equals(LawyersConstants.DEDUCTIBLE_ENDORSEMENT))
				fileName=fileName+"_Deductible_Endorsement";
			if(TransactionTypeKey.equals(LawyersConstants.LIMIT_ENDORSEMENT))
				fileName=fileName+"_Limit_Endorsement";
			if(TransactionTypeKey.equals(LawyersConstants.NAMEADDRESS_ENDORSEMENT))
				fileName=fileName+"_NameAddress_Endorsement";
			if(TransactionTypeKey.equals(LawyersConstants.PRIORACTDATE_ENDORSEMENT))
				fileName=fileName+"_PriorActDate_Endorsement";
			if(TransactionTypeKey.equals(LawyersConstants.POLICYEXTENSION_ENDORSEMENT))
				fileName=fileName+"_PolicyExtension_Endorsement";
			if(TransactionTypeKey.equals(LawyersConstants.ADDCHANGEATTORNEY_ENDORSEMENT))
				fileName=fileName+"_AddChangeAttorney_ENDORSEMENT";
			if(TransactionTypeKey.equals(LawyersConstants.DELETEATTORNEY_ENDORSEMENT))
				fileName=fileName+"_DeleteAttorney_ENDORSEMENT";
			if(TransactionTypeKey.equals(LawyersConstants.ERP_ENDORSEMENT))
				fileName=fileName+"_ERP_ENDORSEMENT";
			String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//" + fileName +".pdf";		
			pdfFile = new File(outFile);
			
			OutputStream out = new java.io.FileOutputStream(pdfFile);
			out = new java.io.BufferedOutputStream(out);			
			String baseUrl = null;
			if(ctx.get("baseUrl") != null)
				baseUrl = ctx.get("baseUrl").toString();			
			baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			
			ServletContextURIResolver uriResolver = null;
			
			if(ctx.get("DocumentUriResolver") != null)
				uriResolver = (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
			
			new DownloadForm().generateEndorsement((Context)ctx, out, baseUrl, uriResolver);			
			//new DownloadForm().generateEndorsement((Context)ctx, outFile);			
			//new DownloadForm().testForm((Context)ctx, listFormID, outFile);
			
			LawyersUtils.populateLastUpdateTimeStamp(ctx);
			String result = "";
			String skipUpload = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".skipupload");
			if(!"Y".equals(skipUpload)){
				//result = uploadEndorsementFormDocument(ctx,quoteNumber,formNumber);
				result = uploadEndorsementFormDocument(ctx, pdfFile.getName());
			}else{
				result = "File added successfully!";
			}
			
						
			if(result == null || (result != null && !result.equals("File added successfully!") )) {
				LawyersUtils.populateError(ctx, "DocUploadError", "Endorsement could not be uploaded");
				return;
			}
				
			if(result != null && result.equals("File added successfully!")) {
				String docTitle = pdfFile.getName();				
				int index = docTitle.lastIndexOf(".");
				String docName = docTitle.substring(0, index);
				String docExt = docTitle.substring(index + 1, docTitle.length());
				docName = docName + "_" + ctx.get("UploadedTime").toString() + "." + docExt;				
				docName = docName.replace(" ", "").replace(":", "").replace("-", "");
//				String DocFileName = "Endorsement_Form_"+quoteNumber+"_"+formNumber+"_" +ctx.get("UploadedTime").toString() + ".pdf";	
//				DocFileName = DocFileName.replace(" ", "").replace(":", "").replace("-", "");				
//				String docTitle = "Endorsement_Form_"+quoteNumber+"_"+formNumber+".pdf";
				
				ctx.put("DocTitle", docTitle);
				ctx.put("DocFileName", docName);				
				ctx.put("DocType", "EF");				
				ctx.put("UploadedType", "Auto");
				setEndorsementFormUrl(ctx, "DocUrl",docName);	
				Object objAgentRule = RuleUtils.executeRule(ctx,"LawyersRule.assignDateTimeNUser");
				insertInDocumentArchive(ctx);
			}
			
			if(TransactionTypeKey.equals(LawyersConstants.CANCELLATION_ENDORSEMENT)){
				ctx.put("TransactionDate", ctx.get("CancellationEffectiveDate"));
				ctx.put("event_name", LawyersConstants.CANCELLATIONENDORSEMENT_EMAIL);
			}else if(TransactionTypeKey.equals(LawyersConstants.REINSTATEMENT_ENDORSEMENT)){
				ctx.put("TransactionDate", ctx.get("ReinstatementEffectiveDate"));
				ctx.put("event_name", LawyersConstants.REINSTATEMENTENDORSEMENT_EMAIL);
			}else
				ctx.put("event_name", LawyersConstants.ENDORSEMENT_EMAIL);
			
			if(TransactionTypeKey.equals(LawyersConstants.ERP_ENDORSEMENT))
				EndorsementUtilities.sendErpQuoteLetter((Context)ctx,outFile);
			else
			EndorsementUtilities.fetchDataForEmail((Context)ctx, outFile);
			if(pdfFile.exists())
				pdfFile.delete();
						
			logger.debug("processEndorsementDocument end  ........");	
			
			
		}catch (Exception e){
			logger.error("Unexpected error", e);
		} finally {
			/*code by sukhi 26/09/2018*/
			pdfFile=null;
		}
	}

	
	public void testDocumentManagment (IContext ctx) throws Exception {
		
		boolean issuePolicy=false,IsManualPremium=false ;
		String policyNumber ="";
		List attachments  = new ArrayList();
		//To set Cut-Off Date 
		ctx.put("CutOffDate", LawyersConstants.CUT_OFF_DATE);
		ctx.put("CutOffDateGroup2", LawyersConstants.CUT_OFF_DATE_GROUP2);
		ctx.put("NewAppFlowCutOffDate", LawyersConstants.NEWAPPFLOW_CUT_OFF_DATE);
		ctx.put("CutOffDateGroup3", LawyersConstants.CUT_OFF_DATE_GROUP3);
		ctx.put("CutOffDateGroup4", LawyersConstants.CUT_OFF_DATE_GROUP4);
		
		File pdfFile = null;
		OutputStream out = null;
		//PolicyNumber generation and Update
		try{
			new LawyersUtils().setImagesPathToTemplate(ctx);
			Object objcheck = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetStatusKeyFinalisedQuote",ctx);
			
			 if (objcheck != null && objcheck instanceof List) {
				List objList = (List) objcheck;
				if (objList != null && objList.size() > 0) {
					Map objMap = (Map) objList.get(0);
					ctx.putAll(objMap);					
				}
			}
			
			logger.debug("processDocumentManagment started  ........ " + ctx.get("QuoteNumber"));
			
			//Populating all keys in Context
			Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformgetPolicyKeys", ctx);
			Map keysMap = null;		
			if(obj != null && obj instanceof Map)
			{
				keysMap = (Map) obj;
				ctx.putAll(keysMap);
			}		
				
			obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformCoveragePolicy", ctx);
			Map policyCovMap = null;
			
			if(obj != null && obj instanceof Map)
			{
				policyCovMap = (Map) obj;
				ctx.putAll(policyCovMap);
			}		
			
			obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformgetPriorPolicyInfo", ctx);
			Map policyInfo = null;
			
			if(obj != null && obj instanceof Map)
			{
				policyInfo = (Map) obj;
				ctx.putAll(policyInfo);
			}
			
			logger.debug("Populated all keys in Context...............");
			// Form			
			List listFormID = LawyersUtils.populateFinalisedPolicyForm(ctx);			
			//List listFormID = getPolicyFormID(ctx);		
						
			String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//Policy Form_" + ctx.get("QuoteNumber").toString() + ".pdf";		
			pdfFile = new File(outFile);
			out = new java.io.FileOutputStream(pdfFile);
			out = new java.io.BufferedOutputStream(out);			
			
			new DownloadForm().testForm((Context)ctx, listFormID, outFile);			
			
			/*LawyersUtils.populateLastUpdateTimeStamp(ctx);
			String result = "";
			String skipUpload = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".skipupload");
			if(!"Y".equals(skipUpload)){
				result = uploadFormDocument(ctx);
			}else{
				result = "File added successfully!";
			}
			logger.debug("Result from uploadFormDocument(ctx) in processDocumentManagment " + result);			
			
			if(result == null || (result != null && !result.equals("File added successfully!") ))			
			{
				LawyersUtils.populateError(ctx, "DocUploadError", "Policy could not be issued");
				return;
			}
					
			if(result != null && result.equals("File added successfully!"))
			{		
			
				String DocFileName = "Policy Form_" + ctx.get("QuoteNumber").toString()+ "_" + ctx.get("UploadedTime").toString() + ".pdf";	
				String DocTitle = "Policy Form_" + ctx.get("QuoteNumber").toString();
				ctx.put("DocTitle", DocTitle);
				DocFileName = DocFileName.replace(" ", "").replace(":", "").replace("-", "");
				
				ctx.put("DocFileName", DocFileName);
				
				ctx.put("DocType", "PF");
				
				ctx.put("UploadedType", "Auto");
				setFormUrl(ctx, "DocUrl");			
				
				insertInDocumentArchive(ctx);
			}*/
			
//			List attachments  = new ArrayList();
//			if(out != null)
//				out.close();
//			attachments.add(outFile);			
//			
			// Application PDF
//			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
//			
//			outFile = htmlDir + "data//Lawyers_" + ctx.get("QuoteNumber").toString() + ".pdf";
//			//pdfFile = new File(outFile);
//			out = new java.io.FileOutputStream(outFile);
//			out = new java.io.BufferedOutputStream(out);
			
			/*if(ctx.get("PolicyStatusKey") != null && "2".equals(ctx.get("PolicyStatusKey").toString())){
				new DownloadPDF().makeRenewPdf((Context) ctx, out, baseUrl, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER));
			}else{
				new DownloadPDF().makePdf((Context) ctx, out, baseUrl, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER));
			}*/
//			if(ctx.get("PolicyStatusKey") != null && "2".equals(ctx.get("PolicyStatusKey").toString())){
//				new DownloadPDF().testRenewPdf((Context) ctx, outFile);
//			}else{
//				new DownloadPDF().testmakePdf((Context) ctx, outFile);
//			}
			
//			if(ctx.get("PolicyStatusKey") != null && "2".equals(ctx.get("PolicyStatusKey").toString())){
//				new DownloadPDF().makeRenewPdf((Context) ctx, out, baseUrl, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER));
//			}else{
//				new DownloadPDF().makePdf((Context) ctx, out, baseUrl, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER));
//			}
			
			
			//ByteArrayOutputStream baos = new PrintPDF().makePdf((Context)ctx);
			//baos.writeTo(out);
			if(out != null)
				out.close();
//			
//			// Commention this as No longer application will be attached with the Issued mail 10 October 2015
//			
//			//attachments.add(outFile);
//			
//		
//			
//			
//					String insuredEmail = "";
//					boolean isPAAndDefenseInside = false ;
//					boolean producerCodeExist =false;
//					boolean isSpecialIssuance =false;
//					String adminEmail = LawyersUtils.getAdminEmailID (ctx);
//					   
//					
//					// Do not send mail to insured if producer code is there
//						producerCodeExist = new LawyersValidationUtils().checkIfSubProducerExist(ctx);
//						logger.debug("Producer Code Exist ? " + producerCodeExist);
//						if(!producerCodeExist){					
//						insuredEmail = LawyersUtils.getInsuredEmail(ctx); 
//						}
//						logger.debug("Check is special issuance ");
//						 isSpecialIssuance = LawyersValidationUtils.checkIfSpecialIssuance(ctx);
//						logger.debug("Is Special Issuance ? " + isSpecialIssuance);
//						logger.debug("Check if PA and defense Inside");
//						
//						if(ctx.get("StateCode") != null && "PA".equals(ctx.get("StateCode").toString())){
//							isPAAndDefenseInside = LawyersValidationUtils.checkForFinalisedQuoteWithInsideLimit(ctx);
//						}
//						logger.debug("isPAAndDefenseInside ? " +  isPAAndDefenseInside);
//						if(isSpecialIssuance || producerCodeExist || isPAAndDefenseInside){
//							logger.debug("Inside Special Issuance and Producer Code exist block . Getting Special Issuance Email");
//							String specialIssuanceMail = LawyersValidationUtils.getSpecialIssuanceEmail(ctx);
//							insuredEmail = specialIssuanceMail ;
//						}
//					
//					if("".equals(insuredEmail))
//						insuredEmail = adminEmail ;
//					
//					
//					logger.debug("Going to send mail to " + insuredEmail);
//					String policy ="Issued PolicyNumber  " + policyNumber;
//					if("".equals(adminEmail))
//						return;	
//		
//						MailSender mailSender = new MailSender();				
//						mailSender.setSubject(policy);
//
//						mailSender.setIsSentToCC("Y");
//						logger.debug("Is Manual Quote  " + IsManualPremium);
//						if(!IsManualPremium && !producerCodeExist){						
//							mailSender.setToAdd(insuredEmail);
//						}
//						if(isSpecialIssuance || producerCodeExist || isPAAndDefenseInside)
//							mailSender.setToAdd(insuredEmail);
//						
//						mailSender.setCcAdd(adminEmail);
//						mailSender.setContentType("text/html");			
//						mailSender.setBody(generatePolicyIssueBody(ctx));	
//						mailSender.setAttachments(attachments);				
//						mailSender.sendMail();
//						
//						for (int i=0; i<attachments.size(); i++)
//						{
//							// File Delete
//							File file = new File(attachments.get(i).toString());
//							if(file.exists())
//								file.delete();
//						}				
//		
//			
//					
//			// Status Issued
//			//SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementformupdateStatus", ctx);
//			try{
//				RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");
//				if(ctx.get("LastUpdateTimeStamp") != null)
//					ctx.put("IssuedDate",new SimpleDateFormat(DATE_PATTERN).format(ctx.get("LastUpdateTimeStamp")));
//				}
//				catch (Exception e){			
//					logger.debug("IssuedDate------"+e.getMessage());
//					throw e;
//				}
//				
//			//SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementformupdateIssuedDate", ctx);
//			
//			ctx.remove("IssuedDate");
//			ctx.remove("PolicyNumber");		
//			ctx.put("IsDocumentProcessed", "Y");
			logger.debug("processDocumentManagment end  ........");
			
			
			
		}catch (Exception e){
//			ctx.put("IsDocumentProcessed", "N");
//			logger.debug("processDocumentManagment failed  ........" + e);
//			logger.debug("Going to rollback all database changes for issuing policy            " + e);
//			try {
//				new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,VersionSequence,UserNo");
//				SqlResources.getSqlMapProcessor(ctx).update("Policy.RollbackIssuePolicyUpdateStateus_p",ctx);
//			} catch (Exception e1) {
//				e.printStackTrace();
//			}
			throw e;
		} finally {
			/*code by sukhi 26/09/2018*/
			if(out != null){
				out.close();
				out = null;
			}
			pdfFile=null;
		}
		
	}
	
	public String uploadEndorsementFormDocument (IContext ctx, String fileName) throws Exception {
		
		String userName = getUserName();
		String password = getUserPassword();
		String domain = getDomainName();
		String baseDir = getSharePointBaseDirectory();
		String url = getSharePointURL();
		
		//String docName = "Endorsement_Form_"+quoteNumber+"_"+formNumber+"_"+ctx.get("UploadedTime").toString() + ".pdf";
		//byte[] docContent = DocManagementUtil.getBytesFromFile(new File(SystemProperties.getInstance().getString("html.basedir") + "data//" + fileName));
		String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//" + fileName;
		int index = fileName.lastIndexOf(".");
		String docName = fileName.substring(0, index);
		String docExt = fileName.substring(index + 1, fileName.length());
		docName = docName + "_" +ctx.get("UploadedTime").toString() + "." + docExt;
		
		docName = docName.replace(" ", "").replace(":", "").replace("-", "");
		String docLibPathName = url + baseDir;
		String folderName = ctx.get("QuoteNumber").toString();		
		
//		String userName = "sanjayr";
//		String password = "Sanr55.bgp";
//		String domain = "in";
//		String baseDir = "TestJava";
		
		//return new DocManagementUtil().uploadDocToSharePoint(docLibPathName, folderName, baseDir, docName, docContent, userName, password, domain);
		//Raghu
		return new DocManagementUtil().uploadDocToSharePoint(docLibPathName,
				folderName, baseDir, docName, outFile, userName,
				password, domain, url);
		
	}
	
	public void setEndorsementFormUrl(IContext ctx, String field, String docName) throws Exception {			
		docName = docName.replace(" ", "").replace(":", "").replace("-", "");
		String spUrl = getSharePointURL();
		String baseDir = getSharePointBaseDirectory();
		String docLibPathName = spUrl + baseDir;
		String folderName = ctx.get("QuoteNumber").toString();
		String url = docLibPathName + "/" + folderName + "/" + docName;
		ctx.put(field, url);		
	}
	
	public static void main(String args[]){
		
		Context ctx = new Context();
		//String project = args[0]; 
		//String propertyPath = args[1];
	//	String xmlPath = args[2];
	//	String excelPath = args[3];
		
		String project = "LawyersIns"; 
		String propertyPath = "C:\\EclipseNew\\eclipse\\Lawyers.properties";
		String xmlPath = "C:\\Tomcat9\\webapps\\LawyersIns\\XML";	
//		String excelPath = "C:/LawyersUtility/PolicyList.xls";	
		
//		ctx.put("PolicyKey", "69150");		
//		ctx.put("QuoteNumber", "QN-0069303");
//		ctx.put("TransactionTypeKey", LawyersConstants.LIMIT_ENDORSEMENT);
//		ctx.put("PolicyEffectiveDate", "05/01/2020");
//		ctx.put("StateCode", "AR");
//		ctx.put("CutOffDate", "01/10/2018");		
//		ctx.put("AccountKey", "62141");
//		ctx.put("AddressKey", "62131");
//		ctx.put("MarketableProductKey", "2");
//		ctx.put("TransactionSequence", "94727");
//		ctx.put("FinalisedQuote_TransactionSequence", "103527");
//		ctx.put("VersionSequence", "39082");
//		ctx.put("CoverageSequence", "84594");		
//		ctx.put("PolicyNumber", "580-316151-6");
//		ctx.put("UserNo", "3");
		
//		String project = "LawyersIns"; 
//		String propertyPath ="C:/OneDriveData/WorkSpace/SvnProtexureWorking/LawyersIns/LawyersInsMain.properties";
		ctx.setProject(project);		
		
		try {	
			SystemProperties.setPropertyFileName(propertyPath);
			/*ResourceLoader.load("C:/OneDriveData/WorkSpace/SvnProtexureWorking/LawyersIns/web/XML/LawyersIns/rules/rules.xml", "rules", project);
			ResourceLoader.load("C:/OneDriveData/WorkSpace/SvnProtexureWorking/LawyersIns/web/XML/LawyersIns/functions.xml", "functions", project);
			ResourceLoader.load("C:/OneDriveData/WorkSpace/SvnProtexureWorking/LawyersIns/web/XML/LawyersIns/metadata/metadata.xml", "metadata", project);
			*/			
			SystemProperties.setPropertyFileName(propertyPath);			
			SqlResources.load(project, xmlPath + "/LawyersIns/ibatis/maps/SqlMapConfig.xml");
			ResourceLoader.load(xmlPath + "/LawyersIns/rules/rules.xml", "rules", project);
			ResourceLoader.load(xmlPath + "/LawyersIns/functions.xml", "functions", project);
			ResourceLoader.load(xmlPath + "/XML/LawyersIns/metadata/metadata.xml", "metadata", project);
		} catch (Exception e1) {
			logger.error("Unexpected error", e1);
		}
		setCutoffDate(ctx);
		/*try {
			LawyersUtils.checkNewFiling(ctx);
			System.out.println(ctx.get("IsNewFiling2020"));
			System.out.println(ctx.get("IsNewFiling"));
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		DocumentManagment dm = new DocumentManagment();		
		/*try{			
			FileInputStream fileInputStream = new FileInputStream(excelPath);
            Workbook wb = Workbook.getWorkbook(fileInputStream);
            // TO get the access to the sheet
      		Sheet sh = wb.getSheet("Data");
      		// To get the number of rows present in sheet
      		int totalNoOfRows = sh.getRows();
      		// To get the number of columns present in sheet
      		int totalNoOfCols = sh.getColumns();

      		for (int row = 1; row < totalNoOfRows; row++) {
      			String policyKey = sh.getCell(0, row).getContents();
      			if("".equals(policyKey))
      				continue;
      			
	            ctx.put("PolicyKey", policyKey);
	            System.out.println("Document generation started... " + policyKey);
	            dm.testDocumentManagment(ctx);
	            System.out.println("Document generation end... " + policyKey);
      		}

        } catch (FileNotFoundException e) {
              e.printStackTrace();
        } catch (IOException e) {
              e.printStackTrace();
        } catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
//			dm.testDocumentManagment(ctx);
//			dm.processEndorementDocument(ctx);
//			dm.processIndicationDocument(ctx);			
//			Map map = new HashMap();
//			List list = new ArrayList();
//			map.put("CurrentDate", LawyersDateUtils.getCurrentDate());
//			list.add(map);
//			ctx.put("general_freeform_01",list);
//			
//			String dataXML = new RequestProcessor().generateResponseXml(ctx,false);	
//			System.out.println(dataXML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		try
		{
			OutputStream out = null;
			File file = null;
			String htmlDir = SystemProperties.getInstance().getString(
					"html.basedir");
			String baseUrl = null;
			if (ctx.get("baseUrl") != null)
				baseUrl = ctx.get("baseUrl").toString();

			ServletContextURIResolver uriResolver = null;
			if (ctx.get("uriResolver") != null)
				uriResolver = (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
			
			
			List dataList =  SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetSpoildData",ctx);
			if (dataList != null) {
				for (int i = 0; i < dataList.size(); i++)
				{
					Map map = (HashMap) dataList.get(i);
					ctx.putAll(map);
					
						String outFile = htmlDir + "data//Lawyers_"+ ctx.get("QuoteNumber").toString() + ".pdf";
						out = new java.io.FileOutputStream(outFile);
						out = new java.io.BufferedOutputStream(out);
			
						if (ctx.get("PolicyStatusKey") != null
								&& "2".equals(ctx.get("PolicyStatusKey").toString())) {
							new DownloadPDF().makeRenewPdf((Context) ctx, out, baseUrl, uriResolver);
						} else {
							new DownloadPDF().makePdf((Context) ctx, out, baseUrl, uriResolver);
						}
			
				}
			}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
	}
	
	public static void setCutoffDate(Context ctx){
		ctx.put("CutOffDate", LawyersConstants.CUT_OFF_DATE);
		ctx.put("CutOffDateGroup2", LawyersConstants.CUT_OFF_DATE_GROUP2);
		ctx.put("NewAppFlowCutOffDate", LawyersConstants.NEWAPPFLOW_CUT_OFF_DATE);
		ctx.put("CutOffDateGroup3", LawyersConstants.CUT_OFF_DATE_GROUP3);
		ctx.put("CutOffDateGroup4", LawyersConstants.CUT_OFF_DATE_GROUP4);
		ctx.put("CutOffDateGroup5", LawyersConstants.CUT_OFF_DATE_GROUP5);
		ctx.put("CutOffDateGroup6", LawyersConstants.CUT_OFF_DATE_GROUP6);
		ctx.put("CutOffDateGroup7", LawyersConstants.CUT_OFF_DATE_GROUP7);
		ctx.put("CutOffDateGroup8", LawyersConstants.CUT_OFF_DATE_GROUP8);
		ctx.put("CutOffDateCCBSupp", LawyersConstants.CUT_OFF_DATE_CCBSupp);
		ctx.put("CutOffDate2020", LawyersConstants.CUT_OFF_DATE_2020);
		ctx.put("CutOffDateCannibsSupp", LawyersConstants.CUT_OFF_DATE_CANNIBSSUPP);
	}
	
	public String uploadDocumentForSharePoint(IContext ctx, String folderName, String url, String updateFile) throws Exception {

		String userName = getUserName();
		String password = getUserPassword();
		String domain = getDomainName();
		String baseDir = getSharePointBaseDirectory();
		String spUrl = getSharePointURL();

		byte[] docContent = new EsignUtil().getDocument(url);

		File file = new File(url);
		String docName = file.getName();

		String docLibPathName = spUrl + baseDir;

		return new DocManagementUtil().uploadDocToSharePoint(docLibPathName,
				folderName, baseDir, docName, url, userName,
				password, domain, spUrl);

	}
	
	public void setUrlForDBSave(IContext ctx, String folderName, String docName, String field)
			throws Exception {

		String spUrl = getSharePointURL();
		String baseDir = getSharePointBaseDirectory();
		String docLibPathName = spUrl + baseDir;
		String url = docLibPathName + "/" + folderName + "/" + docName;
		ctx.put(field, url);

	}
	
	public void insertInSubproducerTable(IContext ctx) throws Exception {

		/*SqlResources.getSqlMapProcessor(ctx).insert("SubProducer.insert", ctx);
		*/
		try {
			
			new SetParametersForStoredProcedures().setParametersInContext(ctx, "ProducerName,NewCommission,RenewalCommission,State,StreetAddress,Zip,City,Producer_email,SPPhoneNumber,ProducerCode,Date_added,SendQuoteLetter,SendRenewalSolicitation,Archive,SubProducerAccess,sig_path,SPContactName,SPWebsite,Fax,file_path,IndicationAccess,BillingContactName,BillingContactNumber,BillingContactEmail,creditCardAccess,IsDriect,SubProducerPolicyIssuedAccess,ProducerAbbr");
			SqlResources.getSqlMapProcessor(ctx).update("FirmLW.SubproducerFormDetailsLW_p",ctx);
		} catch (Exception e) {
			logger.error("Unexpected error", e);
			throw e;
		}
	}
	
	public static void uploadSignature(IContext ctx) {
		logger.debug("processDocument has started");
		if(ctx.get("fileItems") == null || !(ctx.get("fileItems") instanceof List))
			return;
		
		String signature_path="",spLogo_Path="";
		String DocTitle = null;
		String Comment = null;
		String DocFileName = null;
		FileItem uploadFile_sig = null;
		FileItem uploadFile_logo = null;
		String SearchType = null;
		String DocType = null;
		String updateFile = "";
		String docNameTemp = "";
		String uploaddirectory="";
		File file = null;
		File tempFile1 = null;
		File tempFile = null;
		
		try {
			List fileItems = (List)ctx.get("fileItems");
			Iterator i = fileItems.iterator();
			
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// filename on the client
				
					if("sig_path".equals(fi.getFieldName())){
						signature_path=fi.getName();
						uploadFile_sig = fi;
					}
					if("file_path".equals(fi.getFieldName())){
						spLogo_Path=fi.getName();
						uploadFile_logo = fi;
					}
					
					if (signature_path.equals("") && spLogo_Path.equals("")) {
						continue;
					}
				}
			}
			logger.debug(signature_path+"\t"+spLogo_Path);	
		} catch (Exception e) {
			logger.debug("Error in file process");
			logger.error("Unexpected error", e);
		}
		if(ctx.get("s_path") != null && !"".equals(ctx.get("s_path"))){
			
			if(signature_path == null || "".equals(signature_path)){
				signature_path  = (String)ctx.get("s_path");
			}
			updateFile = "UPDATE";
		} else {
			updateFile = "INSERT";
		}
		
		if(ctx.get("path") != null && !"".equals(ctx.get("path"))){
			if(spLogo_Path == null || "".equals(spLogo_Path)){
				spLogo_Path  = (String)ctx.get("path");
			}
			updateFile = "UPDATE";
		} else {
			updateFile = "INSERT";
		}
		
		ctx.put("UPLOADDOCUMENT", "Y");
		String filedName="";
		String result = null;
		String folderName = "Subproducer";
		try{
			if(signature_path != null && !"".equals(signature_path) && spLogo_Path != null && !"".equals(spLogo_Path)){
				for( int imageCount = 0; imageCount < 2; imageCount++){
					String fileName=null;
					if(imageCount==0){
						fileName=signature_path;
						filedName="sig_path";
					}
					if(imageCount==1){
						fileName=spLogo_Path;
						filedName="file_path";
					}
					
					int fileIndex = fileName.indexOf(".");
					String extension = fileName.substring(fileIndex);
					logger.debug(extension);
				
					try{
						String org_fileName = fileName;
						//fileName = fileName + extension;
						logger.debug(fileName);
			
						if(fileName.contains("\\")){
							DocFileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
						} else {
							DocFileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
						}
						file = new File(fileName);
						String docName = file.getName();
						docNameTemp = file.getName();
						
						LawyersUtils.populateLastUpdateTimeStamp(ctx);
			
						uploaddirectory = SystemProperties.getInstance().getString("fileupload.uploaddirectory");
			
						tempFile1 = new File(uploaddirectory);
						if (!tempFile1.exists()){
							tempFile1.mkdir();
						}
						try {
							if(imageCount==0){
								uploadFile_sig.write(new File(uploaddirectory, DocFileName));
							}
							if(imageCount==1){
								uploadFile_logo.write(new File(uploaddirectory, DocFileName));
							}
						} catch (Exception e) {
							logger.debug("Exception " + e );
							LawyersUtils.populateError(ctx, "DocUploadError",
									"Document could not be write to file" );
							return;
						}
			
						
						try {
							result = new DocumentManagment().uploadDocumentForSharePoint(ctx, folderName, uploaddirectory + docName, updateFile);
							//result = "File added successfully!";
							logger.debug("Result from uploadDocument(ctx, uploaddirectory + docName)" + result);
						} catch (Exception e) {
							logger.debug("Exception --->" + e);
							LawyersUtils.populateError(ctx, "DocUploadError",
									"Document could not be uploaded");
							return;
						}
						if (result == null || (result != null && (!result.equals("File added successfully!") && !result.contains("already exists")))) {
							LawyersUtils.populateError(ctx, "DocUploadError", "Document could not be uploaded");
							return;
						} else {
							logger.debug("processDocument has done");
							// ctx.put("DocTitle", DocTitle);
						
							ctx.put("DocTitle", org_fileName);
							// ctx.put("DocFileName", DocFileName);
			
							int index = docName.indexOf(".");
							String fileName1 = docName.substring(0, index);
							String fileExt = docName.substring(index + 1, docName.length());
							if("UPDATE".equals(updateFile)){
								docName = fileName1 + "." + fileExt;
							} else {
								docName = fileName1 + "_" + ctx.get("UploadedTime").toString() + "." + fileExt;
							}
							docName = docName.replace(" ", "").replace(":", "").replace("-", "");
			
							ctx.put("DocFileName", docName);
							ctx.put("DocType", DocType);
			
							ctx.put("UploadedType", "Manual");
							
							new DocumentManagment().setUrlForDBSave(ctx, folderName, docName, filedName);
							new DocumentManagment().insertInSubproducerTable(ctx);
						}
						file=null;
					}catch(Exception e){
						logger.debug("Exception in DocFileName" + e);
					}
				}
				tempFile = new File(uploaddirectory + docNameTemp);
				if (tempFile.exists()){
					tempFile.delete();
				}
				
			}
		}catch(Exception e){
			logger.debug("Exception in DocFileName" + e);
		} finally {
			/*code by sukhi 26/09/2018*/
			tempFile = null;
			tempFile1 = null;
			file = null;
		}
	}
	
	
	public static void generateInsurenceCertificate (Context ctx) {		
		logger.debug("Insurance certificate generation started started  ........ ");
		LawyersUtils.getPolicyData(ctx);
		File pdfFile = null;
		OutputStream out = null;
		
		String TransactionTypeKey = ctx.get("TransactionTypeKey").toString();
		DocumentManagment docManage = new DocumentManagment();
		try{
			String fileName="InsuranceCertificate";
			
			String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//" + fileName +".pdf";		
			pdfFile = new File(outFile);
			
			out = new java.io.FileOutputStream(pdfFile);
			out = new java.io.BufferedOutputStream(out);			
			String baseUrl = null;
			if(ctx.get("baseUrl") != null)
				baseUrl = ctx.get("baseUrl").toString();			
			baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			
			ServletContextURIResolver uriResolver = null;
			
			if(ctx.get("DocumentUriResolver") != null)
				uriResolver = (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
			
			
			String foFile = "";		
			uriResolver=(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
			try{	
				LawyersUtils.getPolicyData(ctx);
				if("3".equals(ctx.get("CompanyKey").toString()))	
					foFile = SystemProperties.getInstance().getString("xsl.filepath.COI") + "ISMIE2022//foxslnew/endorsement/LiabilityInsCertificate.xsl";
				else
					foFile = SystemProperties.getInstance().getString("xsl.filepath.COI") + "foxsl2017//foxslnew/endorsement/LiabilityInsCertificate.xsl";
				
				String environment=SystemProperties.getInstance().getString("appl.LawyersIns.environment.production");
				ctx.put("environmentproduction",environment);
				logger.debug("generateForm--------"+foFile);	
				new XML2PDF().convertPOToPDF(foFile, new StringBuffer(new com.formgenerator.DownloadForm().generateDataXml(ctx)), out, baseUrl, uriResolver);
			}
			catch(Exception e){
				logger.error("Problem in generating Insurance Certificate for Policy Number : " +ctx.get("PolicyNumber") + " " + e.getMessage());
				throw e;
			}
			//new DownloadForm().generateEndorsement((Context)ctx, outFile);			
			//new DownloadForm().testForm((Context)ctx, listFormID, outFile);
			
			LawyersUtils.populateLastUpdateTimeStamp(ctx);
			String result = "";
			String skipUpload = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".skipupload");
			if(!"Y".equals(skipUpload)){
				//result = uploadEndorsementFormDocument(ctx,quoteNumber,formNumber);
				result = docManage.uploadEndorsementFormDocument(ctx, pdfFile.getName());
			}else{
				result = "File added successfully!";
			}
			
			if(result == null || (result != null && !result.equals("File added successfully!") )) {
				LawyersUtils.populateError(ctx, "DocUploadError", "Endorsement could not be uploaded");
				return;
			}
				
			if(result != null && result.equals("File added successfully!")) {
				logger.debug("Endorsement added successfully!");
				String docTitle = pdfFile.getName();				
				int index = docTitle.lastIndexOf(".");
				String docName = docTitle.substring(0, index);
				String docExt = docTitle.substring(index + 1, docTitle.length());
				docName = docName + "_" + ctx.get("UploadedTime").toString() + "." + docExt;				
				docName = docName.replace(" ", "").replace(":", "").replace("-", "");
//				String DocFileName = "Endorsement_Form_"+quoteNumber+"_"+formNumber+"_" +ctx.get("UploadedTime").toString() + ".pdf";	
//				DocFileName = DocFileName.replace(" ", "").replace(":", "").replace("-", "");				
//				String docTitle = "Endorsement_Form_"+quoteNumber+"_"+formNumber+".pdf";
				
				ctx.put("DocTitle", docTitle);
				ctx.put("DocFileName", docName);				
				ctx.put("DocType", "EF");				
				ctx.put("UploadedType", "Auto");
				docManage.setEndorsementFormUrl(ctx, "DocUrl",docName);	
				docManage.insertInDocumentArchive(ctx);
			}
			
			if(TransactionTypeKey.equals(LawyersConstants.CANCELLATION_ENDORSEMENT)){
				ctx.put("TransactionDate", ctx.get("CancellationEffectiveDate"));
				ctx.put("event_name", LawyersConstants.CANCELLATIONENDORSEMENT_EMAIL);
			}else if(TransactionTypeKey.equals(LawyersConstants.REINSTATEMENT_ENDORSEMENT)){
				ctx.put("TransactionDate", ctx.get("ReinstatementEffectiveDate"));
				ctx.put("event_name", LawyersConstants.REINSTATEMENTENDORSEMENT_EMAIL);
			}
			else if(TransactionTypeKey.equals(LawyersConstants.INSURANCE_CERTIFICATE)){
				ctx.put("TransactionDate", ctx.get("CertificateRequestedDate"));
				ctx.put("event_name", LawyersConstants.INSURANCECERTIFICATE_EMAIL);
			}
			else
				ctx.put("event_name", LawyersConstants.ENDORSEMENT_EMAIL);
			
			EndorsementUtilities.fetchDataForEmail((Context)ctx, outFile);
			if(pdfFile.exists())
				pdfFile.delete();
						
			logger.debug("processEndorsementDocument end  ........");	
			
			
		}catch (Exception e){
			logger.error("Unexpected error", e);
		} finally {
			/*code by sukhi 26/09/2018*/
			try {
				if(out != null){
					out.close();
					out = null;
				}
			} catch(Exception ex){
				logger.error("Unexpected error", ex);
			}
			pdfFile=null;
		}
	}

	public static void generateERPQuoteLetter (Context ctx) {		
		logger.debug("Erp letter generation has started  ........ ");
	
		File pdfFile = null;
		OutputStream out = null;
		LawyersUtils.getPolicyData(ctx);
		DocumentManagment docManage = new DocumentManagment();
		try{
			EndorsementUtilities.fetchERPLetterData(ctx);
			String fileName="ERPQuoteLetter";
			
			String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//" + fileName +".pdf";		
			pdfFile = new File(outFile);
			
			out = new java.io.FileOutputStream(pdfFile);
			out = new java.io.BufferedOutputStream(out);			
			String baseUrl = null;
			if(ctx.get("baseUrl") != null)
				baseUrl = ctx.get("baseUrl").toString();			
			baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			
			ServletContextURIResolver uriResolver = null;
			
			if(ctx.get("DocumentUriResolver") != null)
				uriResolver = (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
			
			
			String foFile = "";		
			uriResolver=(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
			try{	
				if(!"3".equals(ctx.get("CompanyKey").toString()))
					foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl2017//foxslnew//endorsement//ERPQuoteLetter.xsl";
				else
					foFile = SystemProperties.getInstance().getString("html.basedir") + "ISMIE2022//foxslnew//endorsement//ERPQuoteLetter.xsl";
				String environment=SystemProperties.getInstance().getString("appl.LawyersIns.environment.production");
				ctx.put("environmentproduction",environment);
				logger.debug("generateForm--------"+foFile);	
				new XML2PDF().convertPOToPDF(foFile, new StringBuffer(new com.formgenerator.DownloadForm().generateDataXml(ctx)), out, baseUrl, uriResolver);
			}
			catch(Exception e){
				logger.error("Problem in generating Insurance Certificate for Policy Number : " +ctx.get("PolicyNumber") + " " + e.getMessage());
				throw e;
			}
			//new DownloadForm().generateEndorsement((Context)ctx, outFile);			
			//new DownloadForm().testForm((Context)ctx, listFormID, outFile);
			
			LawyersUtils.populateLastUpdateTimeStamp(ctx);
			String result = "";
			String skipUpload = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".skipupload");
			if(!"Y".equals(skipUpload)){
				//result = uploadEndorsementFormDocument(ctx,quoteNumber,formNumber);
				result = docManage.uploadEndorsementFormDocument(ctx, pdfFile.getName());
			}else{
				result = "File added successfully!";
			}
			
						
			if(result == null || (result != null && !result.equals("File added successfully!") )) {
				LawyersUtils.populateError(ctx, "DocUploadError", "ERP Letter could not be uploaded");
				return;
			}
				
			if(result != null && result.equals("File added successfully!")) {
				String docTitle = pdfFile.getName();				
				int index = docTitle.lastIndexOf(".");
				String docName = docTitle.substring(0, index);
				String docExt = docTitle.substring(index + 1, docTitle.length());
				docName = docName + "_" + ctx.get("UploadedTime").toString() + "." + docExt;				
				docName = docName.replace(" ", "").replace(":", "").replace("-", "");
//				String DocFileName = "Endorsement_Form_"+quoteNumber+"_"+formNumber+"_" +ctx.get("UploadedTime").toString() + ".pdf";	
//				DocFileName = DocFileName.replace(" ", "").replace(":", "").replace("-", "");				
//				String docTitle = "Endorsement_Form_"+quoteNumber+"_"+formNumber+".pdf";
				
				ctx.put("DocTitle", docTitle);
				ctx.put("DocFileName", docName);				
				ctx.put("DocType", "EF");				
				ctx.put("UploadedType", "Auto");
				docManage.setEndorsementFormUrl(ctx, "DocUrl",docName);	
				docManage.insertInDocumentArchive(ctx);
			}
			
			
				ctx.put("event_name", LawyersConstants.QUOTELETTER_EMAIL);
			
			EndorsementUtilities.fetchDataForEmail((Context)ctx, outFile);
			if(pdfFile.exists())
				pdfFile.delete();
						
			logger.debug("processEndorsementDocument end  ........");	
			
		}catch (Exception e){
			logger.error("Unexpected error", e);
		} finally {
			/*code by sukhi 26/09/2018*/
			try {
				if(out != null){
					out.close();
					out = null;
				}
			} catch(Exception ex){
				logger.error("Unexpected error", ex);
			}
			pdfFile=null;
		}	
	}

	
	
	public void processUploadExcel(IContext ctx) throws Exception {

		logger.debug("processDocument has started");
		if(ctx.get("fileItems") == null || !(ctx.get("fileItems") instanceof List))
			return;
		
		String fileName = null;
		String DocTitle = null;
		String Comment = null;
		String DocFileName = null;
		FileItem uploadFile = null;
		String SearchType = null;
		String DocType = null;

		
		try {
			
			List fileItems = (List)ctx.get("fileItems");

		
			Iterator i = fileItems.iterator();

			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();

				if (fi.isFormField()) {
					String name = fi.getFieldName();
					logger.debug(name);
					if (name.equalsIgnoreCase("DocTitle")) {
						DocTitle = fi.getString();
						DocTitle = DocTitle.replace("&", "$AND$");
						DocTitle = DocTitle.replace("%", "$PER$");
						DocTitle = DocTitle.replace("'", "$APH$");

					} else if (name.equalsIgnoreCase("Comment")) {
						Comment = fi.getString();
						Comment = Comment.replace("&", "$AND$");
						Comment = Comment.replace("%", "$PER$");
						Comment = Comment.replace("'", "$APH$");
					} else if (name.equalsIgnoreCase("SearchType")) {
						SearchType = fi.getString();
						logger.debug("SearchType - " + SearchType);
					}

					// String value = fi.getString();
				} else {

					// filename on the client
					fileName = fi.getName();
					uploadFile = fi;
					if (fileName == null) {
						continue;
					}
				}
				/*code by sukhi 26/09/2018*/
				fi=null;
				// break;
			}
		} catch (Exception e) {
			logger.debug("Error in file process");
			logger.error("Unexpected error", e);
			throw e;
		}

		if (fileName == null || "".equals(fileName))
			LawyersUtils.populateError(ctx, "DocFileName",
					"Document has not been selected");
		else if (fileName != null && !validateFileName(fileName))
			LawyersUtils.populateError(ctx, "DocFileName",
					"Selected file is not correct");


		try{
		String org_fileName = fileName;
		fileName = fileName;
		logger.debug(fileName);

		DocFileName = fileName.substring(fileName.lastIndexOf("\\") + 1,
				fileName.length());
		File file = new File(fileName);
		String docName = file.getName();
		String docNameTemp = file.getName();
		
		LawyersUtils.populateLastUpdateTimeStamp(ctx);

		String uploaddirectory = SystemProperties.getInstance().getString(
				"file.downloadfile");

		try {
			uploadFile.write(new File(uploaddirectory, DocFileName));
		} catch (Exception e) {
			logger.debug("Exception " + e );
			LawyersUtils.populateError(ctx, "DocUploadError",
					"Document could not be write to file" );
			

			return;
		}
		}catch(Exception e){
			logger.debug("Exception in DocFileName" + e);
		}
	}
	
	public void processDepoyOnJenkins(IContext ctx) throws Exception {

		logger.debug("processDocument has started");
			URL obj;
			String url = null;
			
		    if(ctx.get("ProjectType_admin").equals("Lwy"))
		    {
		       if(ctx.get("test_Module").equals("NBIssue")){
		    	  url="http://site1.rscube.com/Jenkins/job/NB_Issue_Policy_Complete_Flow_Lawyers/build?token=NBIssueCompleteflowLawyers";
					//String url="http://localhost:8080/jenkins/job/Lawyers_Automation//build?token=11076a335937c5f7cd9f5cc7483da68402";
				}
		       else if(ctx.get("test_Module").equals("RBIssue"))
		       {
		    	    url="http://site1.rscube.com/Jenkins/job/RB_Issue_Policy_Complete_Flow_Lawyers/build?token=RBIssueCompleteflowLawyers";
				     
		       }
		       else if(ctx.get("test_Module").equals("Indi"))
		       {
		    	    url="http://site1.rscube.com/Jenkins/job/Indication_Lawyers/build?token=LawyersIndication";
			     
		       }
		       else if(ctx.get("test_Module").equals("Quick"))
		       {
		    	    url="http://site1.rscube.com/Jenkins/job/QuickQuote_Lawyers/build?token=QuickQuoteLawyers";
			     
		       }
		       else if(ctx.get("test_Module").equals("Endorse"))
		       {
		    	    url="http://site1.rscube.com/Jenkins/job/Endorsement_Lawyers/build?token=EndorsementLawyers";
							
			       
		       }
		       else if(ctx.get("test_Module").equals("testBed"))
		       {
		    	    url="http://site1.rscube.com/Jenkins/job/TestBed_lawyers/build?token=TestBedLawyers";
							
			       
		       }
		       else
		       {
		    	    url="http://site1.rscube.com/Jenkins/job/Test_Full_Lawyers_Application/build?token=FullLawyersApplication";
		       }   
		    }
		    if(ctx.get("ProjectType_admin").equals("Acc"))
		    {
		       
		       if(ctx.get("test_Module_Acc").equals("Indi"))
		       {
		    	    url="http://site1.rscube.com/Jenkins/job/Indication_Accountants/build?token=indicationAccountant";
			     
		       }
		       else if(ctx.get("test_Module_Acc").equals("Quick"))
		       {
		    	    url="http://site1.rscube.com/Jenkins/job/QuickQuote_Accountant/build?token=QuickQuoteAccountant";
			     
		       }
		       else if(ctx.get("test_Module_Acc").equals("NBIssue"))
		       {
		    	    url="http://site1.rscube.com/Jenkins/job/NB_Issue_Policy_Complete_Flow_Accountant/build?token=NBCompleteFlowAccountant";
			     
		       }
		       else if(ctx.get("test_Module_Acc").equals("testBed"))
		       {
		    	    url="http://site1.rscube.com/Jenkins/job/TestBed_Accountant/build?token=TestBedAccounatant";
			     
		       }
		       else if(ctx.get("test_Module_Acc").equals("RBIssue"))
		       {
		    	    url="http://site1.rscube.com/Jenkins/job/RB_Issue_Policy_Complete_Flow_Accountant/build?token=RBIssueCompleteflowAccountant";
			     
		       }
		       else if(ctx.get("test_Module_Acc").equals("Endorse"))
		       {
		    	    url="http://site1.rscube.com/Jenkins/job/Endorsement_Accountant/build?token=EndorsementAccountant";
			     
		       }
		       else if(ctx.get("test_Module_Acc").equals("all"))
		       {
		    	    url="http://site1.rscube.com/Jenkins/job/Test_Full_Accountant_Application/build?token=FullAccountantApplication";
			     
		       }
		       
		       
		    }  
		       
		       obj=new URL(url);
				try{
					
				
				HttpURLConnection con=(HttpURLConnection) obj.openConnection();
				
				Thread.sleep(5000);
				logger.debug("Job status is " +con.getResponseCode());
				logger.debug("execution ended");
				con.disconnect();
				}catch(Exception e){
					logger.debug("Exception " + e );
				}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void uploadBrokeragePolicyDocument(IContext ctx) {
		logger.debug("uploadBrokeragePolicyDocument has started");
		if(ctx.get("fileItems") == null || !(ctx.get("fileItems") instanceof List))
			return;
		
		String file_name="";
		
		FileItem uploadFile_path = null;
		
		String updateFile = "";
		String uploaddirectory="";
		String filedName = "";
		String folderName = "BrokeragePolicy";
		
		try {
			List fileItems = (List)ctx.get("fileItems");
			Iterator i = fileItems.iterator();

			uploaddirectory = SystemProperties.getInstance().getString("fileupload.uploaddirectory");
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// filename on the client
				
					if("BPolicy_path".equals(fi.getFieldName())){
						file_name=fi.getName();
						uploadFile_path = fi;
						filedName = "BPolicy_path";
						
						if(ctx.get("BHPolicy_path") != null && !"".equals(ctx.get("BHPolicy_path"))){
							if(file_name == null || "".equals(file_name)){
								file_name  = (String)ctx.get("BHPolicy_path");
							}
							updateFile = "UPDATE";
						} else {
							file_name = LawyersUtilities.generateUniqueId() + "_" + file_name;
							updateFile = "INSERT";
						}
					}
					if("BQuote_path".equals(fi.getFieldName())){
						file_name=fi.getName();
						uploadFile_path = fi;
						filedName = "BQuote_path";

						if(ctx.get("BHQuote_path") != null && !"".equals(ctx.get("BHQuote_path"))){
							if(file_name == null || "".equals(file_name)){
								file_name  = (String)ctx.get("BHQuote_path");
							}
							updateFile = "UPDATE";
						} else {
							file_name = LawyersUtilities.generateUniqueId() + "_" + file_name;
							updateFile = "INSERT";
						}
					}
					if("BLossRun_path".equals(fi.getFieldName())){
						file_name=fi.getName();
						uploadFile_path = fi;
						filedName = "BLossRun_path";

						if(ctx.get("BHLossRun_path") != null && !"".equals(ctx.get("BHLossRun_path"))){
							if(file_name == null || "".equals(file_name)){
								file_name  = (String)ctx.get("BHLossRun_path");
							}
							updateFile = "UPDATE";
						} else {
							file_name = LawyersUtilities.generateUniqueId() + "_" + file_name;
							updateFile = "INSERT";
						}
					}
					
					logger.debug(file_name);	
					fileWrite(uploadFile_path, uploaddirectory, file_name);
					uploadBrokeragePolicyDocumentSharePoint(ctx, folderName, uploaddirectory, file_name, updateFile, filedName);
					LawyersUtilities.fileDelete(uploaddirectory, file_name);
				}
			}
		} catch (Exception e) {
			logger.debug("Error in file process");
			logger.error("Unexpected error", e);
		}
		
		ctx.put("UPLOADDOCUMENT", "Y");
		
	}
	
	public static void fileWrite(FileItem uploadFile, String uploaddirectory,String fileName){
		File file = new File(uploaddirectory);
		if (!file.exists()){
			file.mkdir();
		}
		try {
			uploadFile.write(new File(uploaddirectory, fileName));
		} catch (Exception e) {
			logger.debug("Exception " + e );
			logger.error("Unexpected error", e);
		}
	}
	
	public static void uploadBrokeragePolicyDocumentSharePoint(IContext ctx, String folderName, String uploaddirectory, String fileName, String updateFile, String filedName){
		String result = "";
		try {
			result = new DocumentManagment().uploadDocumentForSharePoint(ctx, folderName, uploaddirectory + fileName,  updateFile);
			//result = "File added successfully!";
			logger.debug("Result from uploadBrokeragePolicyDocumentSharePoint(ctx, uploaddirectory + docName)" + result);
		
			if (result == null || (result != null && (!result.equals("File added successfully!") && !result.contains("already exists")))) {
				LawyersUtils.populateError(ctx, "DocUploadError", "Document could not be uploaded");
				return;
			} else {
				logger.debug("uploadBrokeragePolicyDocumentSharePoint has done");
				
				String docName = fileName;
	
				String DocFileName = "";
				if(!"UPDATE".equals(updateFile)){
					DocFileName = LawyersUtilities.generateUniqueId() + "_" + docName;
				}
				DocFileName = DocFileName.replace(" ", "").replace(":", "").replace("-", "");
				
				new DocumentManagment().setUrlForDBSave(ctx, "Subproducer", DocFileName, filedName);
			}
		
		} catch (Exception e) {
			logger.debug("Exception --->" + e);
			LawyersUtils.populateError(ctx, "DocUploadError",
					"Document could not be uploaded");
			return;
		}
	}
	
	public void processLossRunAndPriorDocument (IContext ctx) throws Exception {		
		logger.debug("processLossRunAndPriorDocument started  ........ ");			
		
		File pdfFile = null;
		FileItem uploadFile = null;
		String fileName = null;
		String docTitle = "T";
		String searchType = null;
		String docType = null;
		String docFileName = null;
		
		try{
			try {
				List fileItems = (List)ctx.get("fileItems");

				Iterator i = fileItems.iterator();

				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();

					if (fi.isFormField()) {
						String name = fi.getFieldName();
						logger.debug(name);
						if (name.equalsIgnoreCase("DocTitle")) {
							docTitle = fi.getString();
							docTitle = docTitle.replace("&", "$AND$");
							docTitle = docTitle.replace("%", "$PER$");
							docTitle = docTitle.replace("'", "$APH$");

						} else if (name.equalsIgnoreCase("SearchType")) {
							searchType = fi.getString();
							logger.debug("SearchType - " + searchType);
						}

						// String value = fi.getString();
					} else {

						// filename on the client
						fileName = fi.getName();
						uploadFile = fi;
						if (fileName == null) {
							continue;
						}
					}
					/*code by sukhi 26/09/2018*/
					fi=null;
					// break;
				}
			} catch (Exception e) {
				logger.debug("Error in file process");
				logger.error("Unexpected error", e);
				throw e;
			}
			
//			if (!docTitle.isEmpty() && (fileName == null || "".equals(fileName))) {
//				LawyersUtils.populateError(ctx, "DocFileName", "Document has not been selected");
//				ctx.put("DocumentID", 0);
//				return;
//			}
			
//			if (!fileName.isEmpty() && (docTitle == null || "".equals(docTitle))) {
//				LawyersUtils.populateError(ctx, "DocTitle", "Enter title");
//				ctx.put("DocumentID", 0);
//				return;
//			}
			
			if (!docTitle.isEmpty() && !fileName.isEmpty()) {
				
				int fileIndex = fileName.indexOf(".");
				String extension = fileName.substring(fileIndex);
				extension=extension.substring(extension.lastIndexOf('.'),extension.length());
				logger.debug(extension);
				
				if (!".pdf".equalsIgnoreCase(extension)) {
					LawyersUtils.populateError(ctx, "DocUploadError", "Please select PDF file only" );
					ctx.put("DocumentID", 0);
					return;
				}
				
				if (searchType != null && searchType.equals("lossRun")) {
					fileName = "LossRun_" + ctx.get("QuoteNumber").toString();
					docType = "LR";
				} else if (searchType != null && searchType.equals("priorPolicy")) {
					fileName = "PriorPolicy_" + ctx.get("QuoteNumber").toString();
					docType = "PP";
				}
				
				String org_fileName = fileName;
				fileName = fileName + extension;
				logger.debug(fileName);
				
				docFileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
			
				File file = new File(fileName);
				String docName = file.getName();
				String docNameTemp = file.getName();
				
				LawyersUtils.populateLastUpdateTimeStamp(ctx);
				
				String uploaddirectory = SystemProperties.getInstance().getString("fileupload.uploaddirectory");
	
				try {
					uploadFile.write(new File(uploaddirectory, docFileName));
				} catch (Exception e) {
					logger.debug("Exception " + e );
					LawyersUtils.populateError(ctx, "DocUploadError", "Document could not be write to file" );
					ctx.put("DocumentID", 0);
					return;
				}
				
				String result = null;
				try {
					String environmentVar  = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment");
					if(!"DEV".equals(environmentVar))
						result = uploadDocument(ctx, uploaddirectory + docName);
					else
						result="File added successfully!";
					//result = "File added successfully!";
					logger.debug("Result from uploadDocument(ctx, uploaddirectory + docName)" + result);
					
				} catch (Exception e) {
					logger.debug("Exception --->" + e);
					LawyersUtils.populateError(ctx, "DocUploadError", "Document could not be uploaded");
					ctx.put("DocumentID", 0);
					return;
				}
				if (result == null || (result != null && !result.equals("File added successfully!"))) {
					LawyersUtils.populateError(ctx, "DocUploadError", "Document could not be uploaded");
					ctx.put("DocumentID", 0);
					return;
				}
				
				if (result != null && result.equals("File added successfully!")) {
					logger.debug("processDocument has done");
					// ctx.put("DocTitle", DocTitle);
					
					
					if (docTitle != null && !"".equals(docTitle)) {
	
						docTitle = docTitle.replace("$AND$","&");
						docTitle = docTitle.replace("$PER$","%");
						docTitle = docTitle.replace("$APH$","'");
					}
					
					ctx.put("DocTitle", org_fileName);
					// ctx.put("DocFileName", DocFileName);
	
					int index = docName.indexOf(".");
					String fileName1 = docName.substring(0, index);
					String fileExt = docName.substring(index + 1, docName.length());
					docName = fileName1 + "_" + ctx.get("UploadedTime").toString() + "." + fileExt;
					docName = docName.replace(" ", "").replace(":", "").replace("-", "");
	
					ctx.put("DocFileName", docName);
					ctx.put("DocType", docType);
	
					ctx.put("UploadedType", "Manual (I)");
	
					setUrl(ctx, docName, "DocUrl");
					RuleUtils.executeRule(ctx, "LawyersRule.assignDateTimeNUser");
					LawyersUtils.getPolicyData((Context)ctx);
					Object insertDocumentArchiveLW =  SqlResources.getSqlMapProcessor(ctx).insert("DocumentArchiveLW.insert", ctx);
					logger.debug(insertDocumentArchiveLW);
					ctx.put("DocumentID", insertDocumentArchiveLW);
				}
				
				File tempFile = new File(uploaddirectory + docNameTemp);
				if (tempFile.exists())
					tempFile.delete();
				
				/*code by sukhi 26/09/2018*/
				tempFile=null;
				file=null;
				
			} else {
				ctx.put("DocumentID", 0);
			}
		}catch (Exception e){
			throw e;
		} finally {
			
		}
	}
	public static void generateInsurenceCertificateForNJ (Context ctx, OutputStream out,File pdfFile) {		
		logger.debug("Insurance certificate generation started started  ........ ");
		String foFile = "";		
		LawyersUtils.getPolicyData(ctx);
		DocumentGenerationBO.setDataForInsurenceCertificate((Context)ctx);
		String TransactionTypeKey = ctx.get("TransactionTypeKey").toString();
		DocumentManagment docManage = new DocumentManagment();
		try{
			
			String baseUrl = null;
			if(ctx.get("baseUrl") != null)
				baseUrl = ctx.get("baseUrl").toString();			
			baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			
			ServletContextURIResolver uriResolver = null;
			
			if(ctx.get("DocumentUriResolver") != null)
				uriResolver = (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
						
			
			uriResolver=(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
			try{	
				foFile = SystemProperties.getInstance().getString("xsl.filepath.COI") + "ISMIE2022//foxslnew/additionalforms/LiabilityInsCertificate_NJ.xsl";
							
				String environment=SystemProperties.getInstance().getString("appl.LawyersIns.environment.production");
				ctx.put("environmentproduction",environment);
				logger.debug("generateForm--------"+foFile);	
				new XML2PDF().convertPOToPDF(foFile, new StringBuffer(new com.formgenerator.DownloadForm().generateDataXml(ctx)), out, baseUrl, uriResolver);
			}
			catch(Exception e){
				logger.error("Problem in generating Insurance Certificate for Policy Number : " +ctx.get("PolicyNumber") + " " + e.getMessage());
				throw e;
			}
			//new DownloadForm().generateEndorsement((Context)ctx, outFile);			
			//new DownloadForm().testForm((Context)ctx, listFormID, outFile);
			LawyersUtils.populateLastUpdateTimeStamp(ctx);
			String result = "";
			String skipUpload = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".skipupload");
			if (!"Y".equals(skipUpload))
			{
				// result = uploadEndorsementFormDocument(ctx,quoteNumber,formNumber);
				result = docManage.uploadEndorsementFormDocument(ctx, pdfFile.getName());
			} else
			{
				result = "File added successfully!";
			}

			if (result == null || (result != null && !result.equals("File added successfully!"))) {
				LawyersUtils.populateError(ctx, "DocUploadError", pdfFile.getName()+" could not be uploaded");
				return;
			}

			if (result != null && result.equals("File added successfully!")) {
				logger.debug(pdfFile.getName()+" added successfully!");
				String docTitle = pdfFile.getName();
				int index = docTitle.lastIndexOf(".");
				String docName = docTitle.substring(0, index);
				String docExt = docTitle.substring(index + 1, docTitle.length());
				docName = docName + "_" + ctx.get("UploadedTime").toString() + "." + docExt;
				docName = docName.replace(" ", "").replace(":", "").replace("-", "");
				ctx.put("DocTitle", docTitle);
				ctx.put("DocFileName", docName);
				ctx.put("DocType", "EF");
				ctx.put("UploadedType", "Auto");
				docManage.setEndorsementFormUrl(ctx, "DocUrl", docName);
				docManage.insertInDocumentArchive(ctx);
			}

			/*
			 * if (pdfFile.exists()) pdfFile.delete();
			 */

			logger.debug("processEndorsementDocument end  ........");

			
			
		}catch (Exception e){
			logger.error("Unexpected error", e);
		} 
	}

}
