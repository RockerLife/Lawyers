package com.userbo;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.fop.servlet.ServletContextURIResolver;

import com.fop.DownloadFOP;
import com.fop.DownloadPDF;
import com.mail.MailSender;
import com.manage.managemetadata.functions.commonfunctions.DBUtils;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.IContext;
import com.util.InetLogger;
import com.util.SubproducerMailer;
import com.util.SystemProperties;

public class QuoteLetter {
	private static InetLogger logger = InetLogger
			.getInetLogger(QuoteLetter.class);

	public void generateQuoteLetter(IContext ctx) throws Exception {

		OutputStream out = null;
		File file = null;
		String bccAddress="";
		try {

			List attachments = new ArrayList();

			// PDF Writer
			String htmlDir = SystemProperties.getInstance().getString(
					"html.basedir");
			String outFile = htmlDir + "data//QuoteLetter_"
					+ ctx.get("QuoteNumber").toString() + ".pdf";
			out = new java.io.FileOutputStream(outFile);
			out = new java.io.BufferedOutputStream(out);

			String baseUrl = null;
			if (ctx.get("baseUrl") != null)
				baseUrl = ctx.get("baseUrl").toString();

			ServletContextURIResolver uriResolver = null;
			if (ctx.get("uriResolver") != null)
				uriResolver = (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
			
			LawyersUtils.populatePolicyForm(ctx);
			new DownloadFOP().makePdf((Context) ctx, out, baseUrl, uriResolver);

			if (out != null)
				out.close();
			attachments.add(outFile);

			outFile = htmlDir + "data//Lawyers_"
					+ ctx.get("QuoteNumber").toString() + ".pdf";
			out = new java.io.FileOutputStream(outFile);
			out = new java.io.BufferedOutputStream(out);

			if (ctx.get("PolicyStatusKey") != null
					&& "2".equals(ctx.get("PolicyStatusKey").toString())) {
				new DownloadPDF().makeRenewPdf((Context) ctx, out, baseUrl, uriResolver);
			} else {
				new DownloadPDF().makePdf((Context) ctx, out, baseUrl, uriResolver);
			}

			if (out != null)
				out.close();
			
			attachments.add(outFile);
			
			
			//Asked tyo remove by client JIRA ID PA-1191
			/*if("NJ".equals(ctx.get("StateCode").toString()))
			{
				String DocFileName = "NJ_COVID19_GracePeriodNotice.pdf";
				String sourcePath  =htmlDir + "download//" + DocFileName;
				String destinationPath=htmlDir + "data//"+DocFileName;
				LawyersUtils.copyFile(sourcePath,destinationPath);
				
					
				attachments.add(destinationPath);
				
			}*/
			// Asked By Pat 10/18/2017
			/*if (ctx.get("StateCode") != null && "PA".equals(ctx.get("StateCode").toString())) {
				logger.debug("This is PA state | Check if any  quote is defense inside.");
				boolean isInsideLimit = new LawyersValidationUtils()
						.checkForAnyQuoteWithInsideLimit(ctx);
				logger.debug("Is defense inside ? " + isInsideLimit);
				if (isInsideLimit) {
					outFile = htmlDir + "foxsl//PENNSYLVANIA_NOTICE.pdf";
					out = new java.io.FileOutputStream(outFile);
					out = new java.io.BufferedOutputStream(out);
					logger.debug("Going to attach PA form with quote letter");
					new DownloadPDF().makePAPdf((Context) ctx, out, baseUrl,
							uriResolver);

					if (out != null)
						out.close();

					attachments.add(outFile);
				}
			}*/
			
			// Asked By Pat 03/18/2018
			LawyersUtils.checkNewFiling(ctx); 
			
			if (((ctx.get("IsNewFiling") != null && "N".equals(ctx.get("IsNewFiling").toString()))) &&
			((ctx.get("IsNewFiling2020") != null && "N".equals(ctx.get("IsNewFiling2020").toString())))){
				
				if(ctx.get("StateCode") != null && "ND".equals(ctx.get("StateCode").toString())){
					logger.debug("This is ND state | Check if any  quote is defense inside.");
					ctx.put("RuleRequestType","NDRuleForFormAttachmentQuoteletter");
					ctx.put("PolicyKey_rule",ctx.get("PolicyKey").toString());
					ctx.put("StateCode_rule",ctx.get("StateCode"));
					Object ruleMapObject = SqlResources.getSqlMapProcessor(ctx).findByKey("Account.StateSpecificRulesLawyers_proc", ctx); 
					if(ruleMapObject != null && ruleMapObject instanceof Map){						
						Map ruleMap = (Map)ruleMapObject;
						String IsDefensePaidWithinRuleND = ruleMap.get("IsDefensePaidWithinRuleND") != null ? ruleMap.get("IsDefensePaidWithinRuleND").toString() : "";
						logger.debug("Is IsDefensePaidWithinRuleND ? " + IsDefensePaidWithinRuleND);
						String StateCode = ctx.get("StateCode").toString();
						if("Y".equals(IsDefensePaidWithinRuleND) && "AR".equals(StateCode)){
							if("ND".equals(ctx.get("StateCode").toString()))
								outFile = htmlDir + "data//MO_42_001_06_15.pdf";
							
							out = new java.io.FileOutputStream(outFile);
							out = new java.io.BufferedOutputStream(out);
							logger.debug("Going to attach State Specific Notice form with quote letter");
							new DownloadFOP().makeNDPdf((Context)ctx, out, baseUrl, uriResolver);
							
							if (out != null)
								out.close();
	
							attachments.add(outFile);
						}
					}
				}
			} else if (((ctx.get("IsNewFiling") != null && "Y".equals(ctx.get("IsNewFiling").toString()))) ||
				((ctx.get("IsNewFiling2020") != null && "Y".equals(ctx.get("IsNewFiling2020").toString())))){
				//if(ctx.get("StateCode") != null && "ND".equals(ctx.get("StateCode").toString())){
				logger.debug("To check if any  quote is defense inside.");
				ctx.put("RuleRequestType","NDRuleForFormAttachmentQuoteletter");
				ctx.put("PolicyKey_rule",ctx.get("PolicyKey").toString());
				ctx.put("StateCode_rule",ctx.get("StateCode"));
				Object ruleMapObject = SqlResources.getSqlMapProcessor(ctx).findByKey("Account.StateSpecificRulesLawyers_proc", ctx); 
				if(ruleMapObject != null && ruleMapObject instanceof Map){						
					Map ruleMap = (Map)ruleMapObject;
					String IsDefensePaidWithinRuleND = ruleMap.get("IsDefensePaidWithinRuleND") != null ? ruleMap.get("IsDefensePaidWithinRuleND").toString() : "";
					logger.debug("Is IsDefensePaidWithinRuleND ? " + IsDefensePaidWithinRuleND);	
					String StateCode = ctx.get("StateCode").toString();
					/*int aggregateLimitCount  = (Integer)ctx.get("AggregateLimitCount");*/
					
					
					if("Y".equals(IsDefensePaidWithinRuleND) 
							&& ("AR".equals(StateCode) || "MT".equals(StateCode) || "NM".equals(StateCode) || "ND".equals(StateCode) || "WY".equals(StateCode))
						){
						if("AR".equals(StateCode))
							outFile = htmlDir + "data//MO_42_003_04_17.pdf";
						else if("MT".equals(StateCode))
							outFile = htmlDir + "data//MO_42_004_04_17.pdf";
						else if("NM".equals(StateCode) && "NM1".equals(ctx.get("NMRatingVersion").toString()) )
							outFile = htmlDir + "data//MO_42_002_04_17.pdf";
						else if("NM".equals(StateCode) && "NM2".equals(ctx.get("NMRatingVersion").toString()) )
							outFile = htmlDir + "data//MO-42_002_04_19.pdf";
						else if("ND".equals(StateCode))
							outFile = htmlDir + "data//MO_42_001_04_17.pdf"; 
						else if("WY".equals(StateCode))
							outFile = htmlDir + "data//MO_42_005_04_17.pdf";
						
						out = new java.io.FileOutputStream(outFile);
						out = new java.io.BufferedOutputStream(out);
						logger.debug("Going to attach State Specific Notice form with quote letter");
						new DownloadFOP().makeSpecialNoticePdf((Context)ctx, out, baseUrl, uriResolver);
						
						if (out != null)
							out.close();

						attachments.add(outFile);
					}
				}
			//}
		}
			
			// new DocumentManagment().uploadQuoteLetter(ctx);
			// Object objRule = RuleUtils.executeRule(ctx,
			// "LawyersRule.doNotSendMail");
			// boolean flag = false;
			// if (objRule != null && objRule instanceof Boolean) {
			// flag = (Boolean) objRule;
			//
			// }
			
			boolean documentUploaded = false;
			try {
				logger.debug("Going to upload QuoteLetter");
				String skipUpload = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".skipupload");
				if(!"Y".equals(skipUpload)){
					new DocumentManagment().uploadQuoteLetter(ctx);
				}
				documentUploaded = true;
			} catch (Exception e) {
				logger.debug("Exception in upload QuoteLetter . . " + e);
				ctx.put("QuoteLetterSent", "N");
				throw e;

			}
			logger.debug("Document is uploaded successfully "
					+ documentUploaded);
			if (documentUploaded) {
			 RuleUtils.executeRule(ctx,"LawyersRule.AssignCreatedByAndCreatedTimeStamp");
				new DocumentManagment().insertInDocumentArchive(ctx);
				//
				// boolean isMailToInsured = new LawyersValidationUtils()
				// .isMailSendToInsured(ctx);

				String dstId = "";
				// Do not send quote letter mail to insured if sub producer code exist
				boolean producerCodeExist = new LawyersValidationUtils()
						.checkIfSubProducerExist(ctx);

				if (producerCodeExist) {
					try {
						dstId = SystemProperties.getInstance().getString(
								"appl." + ctx.getProject() + ".admin.email");
//						if("Y".equals(ctx.get("SendQuoteLetter")) && "Y".equals(ctx.get("SubProducerAccess"))){
//							dstId = LawyersUtils.getEmailID(ctx);
//						}
						if("Y".equals(ctx.get("IsDriect"))){
							dstId = LawyersUtils.getEmailID(ctx);
						}
//						if("N".equals(ctx.get("SendQuoteLetter")) && "Y".equals(ctx.get("SubProducerAccess"))){
//							dstId = ctx.get("Producer_email").toString();
//						}
					} catch (Exception e) {
					}
				} else {
					dstId = LawyersUtils.getEmailID(ctx);
				}
				
				if ("".equals(dstId))
					return;

				String isMailing = SystemProperties.getInstance().getString(
						"Insured.sendmail");

				// Check Mailing is allowed If Y then Send
				
				if (isMailing != null && "Y".equals(isMailing) && !"".equals(dstId)) {
					if(!"Y".equals(ctx.get("SendQuoteLetter"))){
						MailSender mailSender = new MailSender();
						mailSender.setSubject("Quotation for Lawyers Professional Liability Insurance");
						if("N".equals(ctx.get("SendQuoteLetter")) && "Y".equals(ctx.get("SubProducerAccess"))){
							dstId = SystemProperties.getInstance().getString("mail.admin.to.address");
						}
						
						mailSender.setToAdd(dstId);
						
						mailSender.setIsSentToCC("Y");
						logger.debug("Send Quote Letter Email id  " + dstId);
//						if("Y".equals(ctx.get("SendQuoteLetter")) && "Y".equals(ctx.get("SubProducerAccess"))){
//							String ccAddress = ctx.get("Producer_email").toString();
//							ccAddress += "," + SystemProperties.getInstance().getString("mail.admin.cc.address");
//							mailSender.setCcAdd(ccAddress);
//						}
						String imgPath = htmlDir + "image//kyle.png";
						if("".equals(bccAddress))
							bccAddress=SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.bcc.email");
						mailSender.setBccAdd(bccAddress);
						mailSender.setCcAdd(LawyersUtils.getFirstReviewerAgentEmailID(ctx));
						mailSender.setContentType("text/html");
						// mailSender.setImgPath(imgPath);
						mailSender.setBody(generateQuoteLetterBody(ctx));
						mailSender.setAttachments(attachments);
						mailSender.sendMail();
						
					} else {
						
						String toAddress= "";
						String ccAddress= "";
		        		String productionEnv = "Y";
		        		try {
		        			productionEnv = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production");
		        		} 
		        		catch (Exception e) 
		        		{
		        			logger.error("error in geeting production environment");
		        		}
		        		if ("N".equals(productionEnv))
		        			bccAddress=SystemProperties.getInstance().getString("mail.admin.cc.address");
		        		else 
		        			bccAddress = SystemProperties.getInstance().getString("mail.adminsub.cc.address");
		        		
		        		if("Y".equals(ctx.get("SendQuoteLetter")) && "Y".equals(ctx.get("SubProducerAccess"))){
		        			toAddress = LawyersUtils.getSubProducerEmailID(ctx);
		        			bccAddress = LawyersUtils.getProducerEmail(ctx);
						}
		        		if("N".equals(ctx.get("SendQuoteLetter")) && "Y".equals(ctx.get("SubProducerAccess"))){
//							toAddress = LawyersUtils.getProducerEmail(ctx);
		        			toAddress = SystemProperties.getInstance().getString("mail.adminsub.to.address");
						}
		        		
		        		logger.debug("TOAddress==="+toAddress+"=====ccAddress====="+ccAddress);
		        		
						ctx.put("toAddress",toAddress);
		        		ctx.put("bccAddress",bccAddress);
		        		ctx.put("subject","Quotation for Lawyers Professional Liability Insurance"); 
						ctx.put("body",generateQuoteLetterBodyforSubProducer(ctx));
						ctx.put("emailAttachment",attachments);
						SubproducerMailer.sendEmailAsSubProducer((Context)ctx);
						
					
						/*
						
					    Multipart mpart = new MimeMultipart("mixed");        
					    BodyPart attachPart = null;
					    String emailID = "";
						String productionEnv = "Y";
					    String subject = "Quotation for Lawyers Professional Liability Insurance"; 
						String body = generateQuoteLetterBodyforSubProducer(ctx);
						String smtpHost =SystemProperties.getInstance().getString("mail.hostsub.server");
						String from = SystemProperties.getInstance().getString("mail.adminsub.userid");
						String password = SystemProperties.getInstance().getString("mail.adminsub.password");
						String to ="";
						String ccAdd ="";
						
						
						try {
							productionEnv = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production");
						} 
						catch (Exception e) 
						{
							logger.error("error in geeting production environment");
						}
						if ("N".equals(productionEnv))
						{
							to = SystemProperties.getInstance().getString("mail.admin.to.address");
							ccAdd=SystemProperties.getInstance().getString("mail.admin.cc.address");
						}
						else 
						{
							to = ctx.get("AccountEmail").toString();
							ccAdd = SystemProperties.getInstance().getString("mail.adminsub.cc.address");
						
						}
						
						String displayID = ctx.get("Producer_email").toString();
						String displayName = ctx.get("ProducerName").toString(); 
						String contentType = "text/html";
						
						
						
						
						

					    if(attachments != null && attachments.size()>0)
						{
					    	for(int i=0; i<attachments.size(); i++)
					    	{
					    		String filePath = attachments.get(i).toString();
					    		String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
					    		
					        	File file = new File(filePath);		
					    		FileDataSource fds = new FileDataSource(file);
					    		
					    		attachPart = new MimeBodyPart();
					    		attachPart.setDataHandler(new DataHandler(fds));	    		
					    		attachPart.setFileName(fileName);
					    		
					    		attachPart.setDisposition(Part.ATTACHMENT);     		
					            mpart.addBodyPart(attachPart);	
					    	}
						}
					    
					    if(body != null )
						{
							BodyPart bodyPart = new MimeBodyPart();
							if(contentType != null && !"".equals(contentType))		    
								bodyPart.setContent(body,contentType);
							else
								bodyPart.setContent(body,"text/plain");
							
							mpart.addBodyPart(bodyPart);
							
//							if(imgPath != null && !"".equals(imgPath))
//							{
//								bodyPart = new MimeBodyPart();
//								DataSource fds = new FileDataSource(getImgPath());
//								bodyPart.setDataHandler(new DataHandler(fds));				
//								bodyPart.setHeader("Content-Type", "image/jpeg;name=image.jpg");
//								bodyPart.setHeader("Content-ID","<memememe>");	
//								bodyPart.setHeader("Content-Disposition", "inline");
//								mpart.addBodyPart(bodyPart);
//							}			
						}
						
						new MailSender().send(smtpHost, from,password,to,ccAdd,subject,body,displayID,displayName,mpart);
					*/}
					for (int i = 0; i < attachments.size(); i++) {
						// File Delete
						file = new File(attachments.get(i).toString());
						if (file.exists())
							file.delete();
						
					}
					ctx.put("QuoteLetterSent", "Y");
					// SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementpdfquoteletterupdateFirm",
					// ctx);
				}
			}
		} catch (Exception e) {
			logger.error("Unexpected error", e);
			ctx.put("QuoteLetterSent", "N");
		} finally{
			/*code by sukhi 26/09/2018*/
			if(out != null){
				out.close();
				out = null;
			}
			file=null;
		}

		/*
		 * OutputStream out = null; try { // PDF Writer String htmlDir =
		 * SystemProperties.getInstance().getString( "html.basedir"); String
		 * outFile = htmlDir + "data//quoteletter.pdf"; String baseUrl =
		 * "file:///" + htmlDir.substring(0, (htmlDir.length() - 2)); out = new
		 * java.io.FileOutputStream(outFile); out = new
		 * java.io.BufferedOutputStream(out); new
		 * DownloadFOP().makePdf((Context) ctx, out, baseUrl, null);
		 * out.close(); // Mail Sender MailSender mailSender = new MailSender();
		 * mailSender.setPdfFile(outFile);
		 * mailSender.setName("quoteletter.pdf"); //
		 * mailSender.setContentType("application/octet-stream");
		 * mailSender.sendMail(); // File Delete // File file = new
		 * File(outFile); // if(file.exists()) // file.delete(); } finally {
		 */
		/*
		 * if(out != null) try { out.close(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		// }
	}

	public static void populateQuote(IContext ctx) throws Exception {
		
		String IsClaimExpensesTypeOld = ctx.get("IsClaimExpensesTypeOld") != null ? ctx.get("IsClaimExpensesTypeOld").toString() : "N";
		String IsDollarDefenseOld = ctx.get("IsDollarDefenseOld") != null ? ctx.get("IsDollarDefenseOld").toString() : "N";
		String IsClaimOptionTypeOld = ctx.get("IsClaimOptionType") != null ? ctx.get("IsClaimOptionType").toString() : "N";
		ctx.put("IsClaimExpensesType", IsClaimExpensesTypeOld);
		ctx.put("IsDollarDefense", IsDollarDefenseOld);
		ctx.put("IsClaimOptionType", IsClaimOptionTypeOld);
		
		ctx.put("StateCode", ctx.get("StateCode"));
		ctx.put("LimitSequence", ctx.get("LimitSequence"));		
		
		logger.debug("Going to set dollar defense and claim expense type for quick quote");
		RuleUtils.executeRule(ctx,"LawyersRule.AssignClaimExpensesAndDollarDefense");
		String IsClaimExpensesType = ctx.get("IsClaimExpensesType") != null ? ctx.get("IsClaimExpensesType").toString() : "N";
		String IsDollarDefense = ctx.get("IsDollarDefense") != null ? ctx.get("IsDollarDefense").toString() : "N";
		
		ctx.put("IsClaimExpensesType", IsClaimExpensesType);
		ctx.put("IsDollarDefense", IsDollarDefense);
		
		
		logger.debug("Dollar defense and claim expense has been updated");			
		
		DBUtils.executeDBOperation(ctx, "PolicyTransaction", "1");
		DBUtils.executeDBOperation(ctx, "PolicyCoverage", "1");
		
		DBUtils.executeDBOperation(ctx, "Quote", "1");
		DBUtils.executeDBOperation(ctx, "RatingTrace", "1");
		
		logger.debug("Going insert Nevada Double Quote");
		
		Object objRatingRule = RuleUtils.executeRule(ctx,"LawyersRule.isStateNevadaDoubleQuote");
        if (objRatingRule != null && objRatingRule instanceof Boolean && ((Boolean)objRatingRule) == true) {

    		Context newCtx=new Context();
			newCtx.setProject(ctx.getProject());
			newCtx.putAll(ctx);
			
			RuleUtils.executeRule(newCtx,"LawyersRule.isStateNevadaDoubleQuoteClaimExpenses");
			RuleUtils.executeRule(newCtx,"LawyersRule.isStateNevadaDoubleQuoteRatingDate");
			
			newCtx.put("IsFirstQuote", "N");
			DBUtils.executeDBOperation(newCtx, "PolicyTransaction", "1");
			DBUtils.executeDBOperation(newCtx, "PolicyCoverage", "1");
			
			DBUtils.executeDBOperation(newCtx, "Quote", "1");
			DBUtils.executeDBOperation(newCtx, "RatingTrace", "1");
        }
		ctx.put("Org_CoverageSequence", ctx.get("CoverageSequence"));
		ctx.put("Org_TransactionSequence", ctx.get("TransactionSequence"));
		
	}

	public static void saveQuoteSentToPDF(IContext ctx) throws Exception {

		LawyersValidationUtils validation = new LawyersValidationUtils();
		String homePath=SystemProperties.getInstance().getString("appl.home.dir");
		String checkboxYPath=homePath+"image//check-btn6.png";
		String checkboxNPath=homePath+"image//check-btn4.png";
		String cfLogoPath=homePath+"image//crum_logo.png";
		String protexureaccountantsLogoPath=homePath+"image//logo_protexureaccountants1.png";
		String headerBottomPath=homePath+"image//header_bottom.png";
		String spacerPath=homePath+"image//spacer.png";
		ctx.put("checkboxYPath",checkboxYPath);
		ctx.put("checkboxNPath",checkboxNPath);
		ctx.put("cfLogoPath",cfLogoPath);
		ctx.put("protexureaccountantsLogoPath",protexureaccountantsLogoPath);
		ctx.put("headerBottomPath",headerBottomPath);
		ctx.put("spacerPath",spacerPath);
		boolean flag = true;
		if (!finalizeQuoteSelected(ctx)) {
			LawyersUtils.populateError(ctx, "isQuoteLetterSentView",
					"Finalised quote should be selected.");
			flag = false;

		}
		boolean oneQuoteIsManual = validation
				.checkIfAnyOptionManualFullQuote(ctx);

		if (oneQuoteIsManual) {
			LawyersUtils
					.populateError(
							ctx,
							"isQuoteLetterSentView",
							"Quote letters cannot be sent automatically for Manual quotes, please create a Manual quote letter.");
			flag = false;
		}

		boolean is9010andAbove1M2M = validation.checkIfSelectedQuote9010(ctx);
		if (is9010andAbove1M2M) {
			LawyersUtils.populateError(ctx, "FinalisedQuote",
					"90/10 does not allow for limits above 1m/2m");
			flag = false;
		}

		if (flag) {
			viewSaveQuotePDF(ctx);
		}

	}

	public static void viewSaveQuotePDF(IContext ctx) throws Exception {

		List quoteList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetAllQuotes", ctx);

		if (quoteList != null) {
			for (int i = 0; i < quoteList.size(); i++) {
				Map map = (Map) quoteList.get(i);
				Context newctx = new Context();
				newctx.putAll(map);
				newctx.setProject(ctx.getProject());
				newctx.put("PolicyKey", map.get("PolicyKey"));
				newctx.put("TransactionSequence",
						map.get("TransactionSequence"));

				if (ctx.get("IsQuoteSelected" + "_" + i) != null
						&& ("on".equals(ctx.get("IsQuoteSelected" + "_" + i)
								.toString()) || "Y".equals(ctx.get(
								"IsQuoteSelected" + "_" + i).toString()))) {
					newctx.put("IsQuoteSelected", "Y");
				} else if (ctx.get("IsQuoteSelected" + "_" + i) != null
						&& "N".equals(ctx.get("IsQuoteSelected" + "_" + i)
								.toString())) {
					newctx.put("IsQuoteSelected", "Y");
				} else {
					newctx.put("IsQuoteSelected", null);
					newctx.put("IsThisOptionFinalised", null);
				}

		    	newctx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
		    	newctx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
				Object obj2 = DBUtils.executeDBOperation(newctx, "Quote", "3");

				if (obj2 == null) {
					DBUtils.executeDBOperation(newctx, "Quote", "1");
				} else {
					DBUtils.executeDBOperation(newctx, "Quote", "2");
					DBUtils.executeDBOperation(newctx, "Quote", "1");
				}
			}
		}

	}

	public static void saveCommentToPDF(IContext ctx) throws Exception {
		SqlResources.getSqlMapProcessor(ctx).update(
				"SqlStmts.UserStatementManualBoQueriesupdateQuoteComment", ctx);
	}

	public static void processViewSendQuoteLetter(IContext ctx)
			throws Exception {

		if (!isViewSendQuoteLetter(ctx)) {
			ctx.put("isQuoteLetterSentView", "N");
			ctx.remove("IsQuoteSelected");
			LawyersUtils.populateError(ctx, "isQuoteLetterSentView",
					"Select a quote option below.");
		} else
			ctx.put("isQuoteLetterSentView", "Y");

	}

	public static boolean finalizeQuoteSelected(IContext ctx) throws Exception {

		List quoteList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.quoteOptionviewgetQuoteListAll", ctx);
		if (quoteList == null)
			return true;

		for (int i = 0; i < quoteList.size(); i++) {
			Map map = (Map) quoteList.get(i);

			if ((map.get("IsThisOptionFinalised") != null && "Y".equals(map
					.get("IsThisOptionFinalised").toString()))) {
				if (ctx.get("IsQuoteSelected" + "_" + i) != null
						&& "".equals(ctx.get("IsQuoteSelected" + "_" + i)
								.toString()))
					return false;

				if (ctx.get("IsQuoteSelected_" + i) == null)
					return false;

			}
		}

		return true;
	}

	public static boolean isViewSendQuoteLetter(IContext ctx) throws Exception {
		int countSelectedQuote = 0;
		List quoteList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.quoteOptionviewgetQuoteListAll", ctx);
		if (quoteList == null)
			return true;

		for (int i = 0; i < quoteList.size(); i++) {
			Map map = (Map) quoteList.get(i);

			if ((map.get("IsQuoteSelected") != null && "Y".equals(map.get(
					"IsQuoteSelected").toString())))
				countSelectedQuote++;

			// if((map.get("IsThisOptionFinalised") != null &&
			// "Y".equals(map.get("IsThisOptionFinalised").toString())) &&
			// (map.get("IsDollarDefense") != null &&
			// "Y".equals(map.get("IsDollarDefense").toString())))
			// return false;
		}

		if (countSelectedQuote == 0)
			return false;
		else
			return true;

	}

	private String getProjectUrl(IContext ctx) throws Exception {
		String resourcePath = null;
		resourcePath = SystemProperties.getInstance().getString(
				"appl." + ctx.getProject() + ".url");
		return resourcePath;
	}

	private String generateQuoteLetterBody(IContext ctx) throws Exception {
		Object objPoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"SqlStmts.UserStatementpdfquotelettergetPriorPolicyInfo", ctx);
		Map policyMap = null;
		if (objPoilcy != null && objPoilcy instanceof Map)
			policyMap = (Map) objPoilcy;

		LawyersValidationUtils utils = new LawyersValidationUtils();
		String encrytedPolicyKey = utils.encrypt(ctx.get("PolicyKey")
				.toString());
		encrytedPolicyKey = URLEncoder.encode(encrytedPolicyKey,""+ StandardCharsets.UTF_8);
		/*encrytedPolicyKey = encrytedPolicyKey.replace("=", "%3D");
		encrytedPolicyKey = encrytedPolicyKey.replace("+", "%2B");*/

		String resourcePath = SystemProperties.getInstance().getString(
				"appl." + ctx.getProject() + ".url");

		StringBuilder msg = new StringBuilder(4096);
		if (policyMap != null) {
			
			if(!"3".equals(ctx.get("CompanyKey").toString()))
			{
				msg.append("<table>");
	
				msg.append("<tr>");
				msg.append("<td>");
				if (policyMap.get("AccountName") != null	&& !"".equals(policyMap.get("AccountName").toString()))
					msg.append(policyMap.get("AccountName").toString()).append("<br/>");
				if (policyMap.get("Address1") != null
						&& !"".equals(policyMap.get("Address1").toString()))
					msg.append(policyMap.get("Address1").toString()).append("<br/>");
				;
				if (policyMap.get("Address2") != null
						&& !"".equals(policyMap.get("Address2").toString()))
					msg.append(policyMap.get("Address2").toString()).append("<br/>");
				;
	
				if (policyMap.get("City") != null
						&& !"".equals(policyMap.get("City").toString()))
					msg.append(policyMap.get("City").toString());
				if (policyMap.get("StateDesc") != null && !"".equals(policyMap.get("StateDesc").toString()))
					msg.append(", ").append(policyMap.get("StateDesc").toString());
				if (policyMap.get("Zip") != null
						&& !"".equals(policyMap.get("Zip").toString()))
					msg.append(", ").append(policyMap.get("Zip").toString());
				msg.append("</td>");
				msg.append("</tr>");
	
				msg.append("<tr>");
				msg.append("<td>");
				msg.append("<br/>Dear ");
				if (policyMap.get("ContactPerson") != null
						&& !"".equals(policyMap.get("ContactPerson").toString()))
					msg.append(policyMap.get("ContactPerson").toString());
				msg.append(":");
				msg.append("</td>");
				msg.append("</tr>");
	
				msg.append("<tr>");
				msg.append("<td>");
				msg.append("<br/>Thank you for completing your Protexure Lawyers application. ")
						.append("Attached is your quotation and completed application for review and signature.<br/><br/> ")
						.append(" Your quotation expires ");
	
				if (policyMap.get("QuoteExpiryDate") != null
						&& !"".equals(policyMap.get("QuoteExpiryDate").toString()))
					msg.append(policyMap.get("QuoteExpiryDate").toString()).append(".<br/><br/>");
	
				msg.append("To finalize your coverage electronically please follow the link below: ")
				       .append("<br/><br/>");
				msg.append(" <a href=")
						.append(resourcePath)
						.append("/signandpay.jsp?PolicyKey=")
						.append(encrytedPolicyKey)
						.append(">Finalize Your Coverage</a>")
						.append("<br/><br/> ");
	
				msg.append("This link is specifically for your account; you will be able to securely review and sign your application. Once coverage has been accepted, you will have the opportunity to continue with your payment option.")
						.append("<br/><br/> ");
						
				msg.append("Another option to accept coverage is to follow the instructions in the attached quote letter.<br/><br/>");
				
				
				if(ctx.get("PolicyStatusKey").toString().equals("1") && ctx.get("CompanyKey").toString().equals("1")){
					
				msg.append("Protexure Lawyers is underwritten by Crum & Forster rated A ''Excellent'' by A.M. Best. Crum & Forster has over 25 years of experience offering professional liability insurance products and services.<br/><br/>");
				}
				
				msg.append("To help you better evaluate your options we have included some additional information on our <a href='http://info.protexurelawyers.com/protexure-lawyers-professional-liability-insurance#Features'> policy features</a>,<a href='http://info.protexurelawyers.com/protexure-lawyers-professional-liability-insurance#Claims'> claims handling </a>, and a <a href='http://info.protexurelawyers.com/protexure-lawyers-professional-liability-insurance#Specimen'>specimen policy</a>.<br/><br/>");
				
				msg.append("If you have any additional questions, please contact us at our toll-free number 877-569-4111 and one of our licensed professional liability specialists will be happy to assist you.<br/><br/>");
				
				msg.append("</td>");
				msg.append("</tr>");
			
				msg.append("<tr>");
				msg.append("<td>");
				msg.append("Regards,<br/><br/>");
				msg.append("The Protexure Lawyers Team<br/>");
				msg.append("Phone 877-569-4111<br/>");
				msg.append("Fax (440) 333-3214<br/>");
				msg.append("</td>");
				msg.append("</tr>");
				
				msg.append("<tr><td><br/><br/></td></tr>");
				
				msg.append("<tr style=\"font-weight:bold;color:#787878;font-size:14px;font-style:Arial;\"><td>P.S. Protexure also offers a Property and General Liability program tailored to small firms.  Privacy/Cyber Protection, Employee Dishonesty, Workers Comp and a Business Owners Policy for a home based business are available as well.  Please email or call to inquire about any of our offerings.</td></tr>");
				
				
				msg.append("</table>");
			}
			if("3".equals(ctx.get("CompanyKey").toString()))
			{
				msg.append("<table border='0' style='font-size: 16px;'> ")
						.append("<tr> ")
						.append("<td colspan='2' align='left' valign='top'>").append(policyMap.get("CurrentDate")).append("</td> ")
						.append("</tr> ")
						.append("<tr> ")
						.append("<td colspan='2' align='right' valign='top'>Via Electronic Mail</td> ")
						.append("</tr> ")
						.append("<tr> ")
						.append("<td colspan='2'>").append(policyMap.get("AccountName")).append("</td> ")
						.append("</tr> ")
						.append("<tr> ")
						.append("<td colspan='2'>").append(policyMap.get("Address1")).append("</td> ")
						.append("</tr> ")
						.append("<tr> ")
						.append("<td colspan='2'>").append(policyMap.get("Address2")).append("</td> ")
						.append("</tr> ")
						.append("<tr> ")
						.append("<td colspan='2'>").append(policyMap.get("City")).append(", ").append(policyMap.get("StateDesc")).append(", ").append(policyMap.get("Zip")).append("</td> ")
						.append("</tr> ")
						.append("<tr> ")
						.append("<td colspan='2'>&nbsp;</td> ")
						.append("</tr> ")
						.append("<tr> ")
						.append("<td colspan='2'>&nbsp;</td>")
						.append("</tr> ")
						.append("<tr> ")
						.append("<td colspan='2'>Dear ").append(policyMap.get("ContactPerson")).append("</td> ")
						.append("</tr> ")
						.append("<tr> ")
						.append("<td colspan='2'>&nbsp;</td> ")
						.append("</tr>\r\n");
				msg.append("<tr> <td colspan='2' align='justify'>Thank you for allowing Protexure Insurance Agency, Inc. the opportunity to provide a quote for your firm's consideration of lawyers Professional Liability options. </td> </tr>");
				
				Context newCtx=new Context();
				newCtx.setProject(ctx.getProject());
				newCtx.putAll(policyMap);

				Map map = (Map) SqlResources.getSqlMapProcessor(newCtx).findByKey("FirmLW.findByKey", newCtx);
				if (map != null && map.size() > 0) {
					newCtx.put("QuoteEffectiveDate", map.get("QuoteEffectiveDate"));
					newCtx.put("QuoteExpiryDate", map.get("QuoteExpiryDate"));
					newCtx.put("QuoteSentDate", map.get("QuoteSentDate"));
				}
				
				Object objQuoteExp = RuleUtils.executeRule(newCtx, "LawyersRule.isPEDLessFrmQED");
				boolean flag = false;
				if (objQuoteExp != null && objQuoteExp instanceof Boolean)
					flag = (Boolean) objQuoteExp;

				boolean flagQuoteDate = false;
				if(flag) {
					//Less QuoteEffectiveDate
					Object objQuoteExpDate = RuleUtils.executeRule(newCtx, "LawyersRule.checkQuoteLatterEmailPolicyEffectiveDate");
					if (objQuoteExpDate != null && objQuoteExpDate instanceof Boolean)
						flagQuoteDate = (Boolean) objQuoteExpDate;
					if(flagQuoteDate)
						flagQuoteDate = false;
				} else {
					//Greater QuoteEffectiveDate
					Object objPolciyEffDate = RuleUtils.executeRule(newCtx, "LawyersRule.isPolicyEffDate5Days");
					if (objPolciyEffDate != null && objPolciyEffDate instanceof Boolean)
						flagQuoteDate = (Boolean) objPolciyEffDate;
					
					if(flagQuoteDate)
						flagQuoteDate = isQuoteEffDateGreater5DaysLess29DaysFrmPED(newCtx, newCtx.get("PolicyEffectiveDate").toString(), newCtx.get("QuoteSentDate").toString());
					
				}

				if (!flagQuoteDate){
					msg.append("<tr> <td colspan='2'>&nbsp;</td> </tr> <tr> <td colspan='2' align='justify'>Attached, you will find a comprehensive proposal with terms and conditions offered for your firm's upcoming annual Professional Liability policy.</td> </tr>");
					msg.append("<tr> <td colspan='2'>&nbsp;</td> </tr><tr> <td colspan='2' align='justify'>To finalize your coverage electronically please follow the link below:  </td> </tr>");
					msg.append("<tr> <td colspan='2' align='justify'><a href=").append(resourcePath).append("/signandpay.jsp?PolicyKey=").append(encrytedPolicyKey).append(">Finalize Your Coverage</a> </td> </tr>");
				} else {
					msg.append("<tr> <td colspan='2'>&nbsp;</td> </tr> <tr> <td colspan='2' align='justify'>Please sign and return both the quote letter and application documents attached.</td> </tr>");
				}
				
				msg.append("<tr> <td colspan='2'>&nbsp;</td> </tr><tr> <td colspan='2' align='justify'>This link is specifically for your account, you will be able to securely review and sign your application. Once coverage has been accepted, you will have the opportunity to continue with your payment option. </td> </tr>");
				msg.append("<tr> <td colspan='2'>&nbsp;</td> </tr><tr> <td colspan='2' align='justify'> Protexure Insurance Agency, Inc. is a full-service insurance agency specializing in the insurance needs of solo attorneys and small law firms. Our agency is built on establishing long-term relationships with our clients and providing comprehensive professional liability insurance solutions in a timely fashion. We look forward to continuing our business relationship. </td> </tr>");
				msg.append("<tr> <td colspan='2'>&nbsp;</td> </tr> <tr> <td colspan='2' align='justify'>To bind coverage, please follow the instructions on the quote acceptance page.</td> </tr> <tr> <td colspan='2'>&nbsp;</td> </tr> <tr> <td colspan='2' align='justify'>If you have any additional questions, please contact us at our toll-free number 877-569-4111 and one of our licensed Professional Liability Specialists will be happy to assist you.</td> </tr> <tr> <td colspan='2'>&nbsp;</td> </tr> <tr> <td colspan='2' align='left'>Regards,</td> </tr> <tr> <td colspan='2' align='left'>The Protexure Lawyers Team</td> </tr> <tr> <td align='left'>PO Box 773197</td> <td align='right'>(877) 569-4111<b> / T</b></td> </tr> <tr> <td align='left'>Detroit, Michigan 48277-3197</td> <td align='right'>(440) 333-3214<b> / F</b></td> </tr> <tr> <td align='left'> </td> <td align='right'>LAW.MAIL@PROTEXURE.COM<b> / E</b></td> </tr> <tr> <td colspan='2' align='right'>PROTEXURELAWYERS.COM<b> / W</b></td> </tr> </table>");
			}

		}

		return msg.toString();
	}

	public void deleteQuotes(IContext ctx) throws Exception {

		Object objRule = RuleUtils.executeRule(ctx,
				"LawyersRule.callRatingOnce");
		if (objRule != null && objRule instanceof Boolean) {
			Boolean stateRule = (Boolean) objRule;
			if (stateRule) {

				ctx.put("IsClaimExpensesType", "Y");
				DBUtils.executeDBOperation(ctx, "PolicyCoverage", "4");
			}
		}

	}

	public void generateQuickQuoteListLetter(IContext ctx) throws Exception {
		OutputStream out = null;
		File file = null;

		try {

			List attachments = new ArrayList();

			// PDF Writer
			String htmlDir = SystemProperties.getInstance().getString(
					"html.basedir");
			String outFileQuickQuote = htmlDir + "data//QuickQuoteOptions_"
					+ ctx.get("QuoteNumber").toString() + ".pdf";
			out = new java.io.FileOutputStream(outFileQuickQuote);
			out = new java.io.BufferedOutputStream(out);

			String baseUrl = null;
			if (ctx.get("baseUrl") != null)
				baseUrl = ctx.get("baseUrl").toString();

			ServletContextURIResolver uriResolver = null;
			if (ctx.get("uriResolver") != null)
				uriResolver = (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);

			new DownloadFOP().makeQuickQuoteListPdf((Context) ctx, out,
					baseUrl, uriResolver);

			// String result = new DocumentManagment().uploadDocument(ctx,
			// outFileQuickQuote);
			// System.out.println(" value of result---"+result);
			// String DocFileName =
			// outFileQuickQuote.substring(outFileQuickQuote.lastIndexOf("//")+2,
			// outFileQuickQuote.length());
			// if(result != null)
			// {
			//
			//
			// int index = DocFileName.indexOf(".");
			// String fileName1 = DocFileName.substring(0, index);
			// ctx.put("DocTitle", fileName1);
			//
			//
			// String fileExt = DocFileName.substring(index+1,
			// DocFileName.length());
			// DocFileName = fileName1 + "_" +
			// ctx.get("UploadedTime").toString() + "."+ fileExt;
			// DocFileName = DocFileName.replace(" ", "").replace(":",
			// "").replace("-", "");
			//
			// ctx.put("Comment", "");
			// ctx.put("DocFileName", DocFileName);
			//
			// ctx.put("UploadedType", "Auto");
			//
			// ctx.put("DocType", "QQL");
			//
			// new DocumentManagment().setUrl(ctx, DocFileName, "DocUrl");
			// new DocumentManagment().insertInDocumentArchive(ctx);
			//
			// }

			if (out != null)
				out.close();

			attachments.add(outFileQuickQuote);

			try {
				String skipUpload = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".skipupload");
				if(!"Y".equals(skipUpload)){
					new DocumentManagment().uploadQuickQuoteLetter(ctx);
				}
			} catch (Exception e) {
				logger.debug("Error in uploading QuickQuoteLetter");
				logger.error("Unexpected error", e);
			}

			MailSender mailSender = new MailSender();
			if(ctx.get("role_id").toString().equals("7"))
			{
				mailSender.setSubject("Thank You from "+ctx.get("ProducerName"));
			}
			else{
				mailSender.setSubject("Thank You from Protexure");
				
			}
			String bccAddress="";
			if("".equals(bccAddress))
				bccAddress=SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.bcc.email");
			mailSender.setIsSentToCC("Y");
			mailSender.setContentType("text/html");
			String insuredEmail = LawyersUtils.getInsuredEmail(ctx); 
			logger.debug("Going to send mail to " + insuredEmail);
			mailSender.setToAdd(insuredEmail);
			mailSender.setBody(new LawyersUtils().generateLinkBody(ctx));
			mailSender.setAttachments(attachments);

			mailSender.sendMail();

			for (int i = 0; i < attachments.size(); i++) {
				// File Delete
				file = new File(attachments.get(i).toString());
				if (file.exists())
					file.delete();
				
				
				
			}

			SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateIsQuickQuoteMailSent",ctx);

		} catch (Exception e) {
			logger.error("Unexpected error", e);
			logger.debug("");

		} finally {
			/*code by sukhi 26/09/2018*/
			if(out != null){
				out.close();
				out = null;
			}
			file=null;
		}
	}

	/**
	 * The code below is used during Polulation of Policy Form in aatachment
	 */

	public static void getFinalisedQuote(IContext ctx) throws Exception {
		Object obj = RuleUtils.executeRule(ctx, "LawyersRule.showAllQuote");
		boolean flag = false;
		if (obj != null && obj instanceof Boolean)
			flag = (Boolean) obj;

		List quoteList = null;
		if (flag)
			quoteList = SqlResources.getSqlMapProcessor(ctx).select(
					"SqlStmts.quoteOptionviewgetQuoteListAll", ctx);
		else
			quoteList = SqlResources.getSqlMapProcessor(ctx).select(
					"SqlStmts.UserStatementManualBoQueriesgetQuoteListAllInsured", ctx);

		boolean finalisedFlag = false;
		int noOfFinalisedQuotes = 0;

		if (quoteList != null) {
			for (int i = 0; i < quoteList.size(); i++) {
				Map quote = (Map) quoteList.get(i);
				if (quote.get("IsThisOptionFinalised") != null
						&& quote.get("IsQuoteSelected") != null) {
					if ("Y".equals(quote.get("IsThisOptionFinalised")
							.toString())
							&& "Y".equals(quote.get("IsQuoteSelected")
									.toString())) {
						finalisedFlag = true;
						ctx.put("FinalisedQuote_TransactionSequence",
								quote.get("TransactionSequence"));
						noOfFinalisedQuotes++;
					}
				}
			}
		}
	}

	public static void processFinalisedQuote(IContext ctx) throws Exception {
		// Show All quotes to Insured and Agent ..
		/*
		 * Object obj = RuleUtils.executeRule(ctx, "LawyersRule.showAllQuote");
		 * boolean flag = false; if (obj != null && obj instanceof Boolean) flag
		 * = (Boolean) obj;
		 * 
		 * List quoteList = null; if (flag) quoteList =
		 * SqlResources.getSqlMapProcessor(ctx).select(
		 * "SqlStmts.quoteOptionviewgetQuoteListAll", ctx); else quoteList =
		 * SqlResources.getSqlMapProcessor(ctx).select(
		 * "SqlStmts.quoteOptionviewgetQuoteListAllNonRatingState", ctx);
		 */

		String Is9010Policy = "N";

		Map map = (Map) SqlResources.getSqlMapProcessor(ctx).findByKey(
				"FirmLW.findByKey", ctx);
		if (map != null && map.size() > 0) {
			Is9010Policy = map.get("Is9010Policy") != null ? map.get(
					"Is9010Policy").toString() : "N";
			ctx.put("QuoteEffectiveDate", map.get("QuoteEffectiveDate"));
			ctx.put("QuoteExpiryDate", map.get("QuoteExpiryDate"));
			ctx.put("QuoteSentDate", map.get("QuoteSentDate"));
		}

		Object obj = RuleUtils.executeRule(ctx, "LawyersRule.isAgent");
		boolean isAgent = false;
		if (obj != null && obj instanceof Boolean)
			isAgent = (Boolean) obj;

		List quoteList = null;
		quoteList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.quoteOptionviewgetQuoteListAll", ctx);

		boolean finalisedFlag = false;
		int noOfFinalisedQuotes = 0;

		if (quoteList != null) {
			for (int i = 0; i < quoteList.size(); i++) {
				Map quote = (Map) quoteList.get(i);
				if (quote.get("IsThisOptionFinalised") != null
						&& quote.get("IsQuoteSelected") != null) {
					if ("Y".equals(quote.get("IsThisOptionFinalised")
							.toString())
							&& "Y".equals(quote.get("IsQuoteSelected")
									.toString())) {
						finalisedFlag = true;
						ctx.put("FinalisedQuote_TransactionSequence",
								quote.get("TransactionSequence"));
						noOfFinalisedQuotes++;
					}
				}
			}
		}

		if (!finalisedFlag){
			LawyersUtils.populateError(ctx, "FinalisedQuote",
					"To continue, please select a quote option below");
			LawyersUtils.populateError(ctx, "FinalisedQuoteError",
					"Please Finalise quote");
			ctx.put("FinalisedQuote", "Error");
		}
		else if (noOfFinalisedQuotes > 1){
			LawyersUtils.populateError(ctx, "FinalisedQuote",
					"Finalise one quote only");
			LawyersUtils.populateError(ctx, "FinalisedQuoteError",
					"Please Finalise quote");
			ctx.put("FinalisedQuote", "Error");
		}

		if (isAgent) {
			List finalquote = SqlResources
					.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementManualBoQueriesgetLimitAndDeductibleContinue",
							ctx);
			if (finalquote != null && finalquote.size() > 0) {

				for (int i = 0; i < finalquote.size(); i++) {

					Map quoteMap = (Map) finalquote.get(i);
					String aggregateLimit = quoteMap.get("AggregateLimit") != null ? quoteMap
							.get("AggregateLimit").toString() : "0";
					String occuranceLimit = quoteMap.get("OccuranceLimit") != null ? quoteMap
							.get("OccuranceLimit").toString() : "0";

					int agglimit = Integer.parseInt(aggregateLimit);
					int occlimit = Integer.parseInt(occuranceLimit);

					if (agglimit > 1000000 || occlimit > 2000000) {

						if (Is9010Policy.equals("Y")){
							LawyersUtils
									.populateError(ctx, "FinalisedQuote",
											"90/10 does not allow for limits above 1m/2m");
							LawyersUtils.populateError(ctx, "FinalisedQuoteError",
									"90/10 does not allow for limits above 1m/2m");
							ctx.put("FinalisedQuote", "Error");
						}
					}

				}
			}

		}

		if (ctx.get("User") != null
				&& !"Agent".equals(ctx.get("User").toString())
				&& ctx.get("StatusKey") != null
				&& !"3".equals(ctx.get("StatusKey").toString())
				&& !"7".equals(ctx.get("StatusKey").toString())){
			LawyersUtils.populateError(ctx, "FinalisedQuote",
					"Change status of policy to quoted");
			LawyersUtils.populateError(ctx, "FinalisedQuoteError",
					"Change status of policy to quoted");
			ctx.put("FinalisedQuote", "Error");
		}
		
		Object objQuoteExp = RuleUtils.executeRule(ctx, "LawyersRule.isPEDLessFrmQEDAndInsured");
		boolean flag = false;
		if (objQuoteExp != null && objQuoteExp instanceof Boolean)
			flag = (Boolean) objQuoteExp;

		boolean flagQuoteDate = false;
		if(flag) {
			//Less QuoteEffectiveDate
			Object objQuoteExpDate = RuleUtils.executeRule(ctx, "LawyersRule.isQuotationExpiredAndInsured");
			if (objQuoteExpDate != null && objQuoteExpDate instanceof Boolean)
				flagQuoteDate = (Boolean) objQuoteExpDate;
		} else {
			//Greater QuoteEffectiveDate
			Object objPolciyEffDate = RuleUtils.executeRule(ctx, "LawyersRule.isPolicyEffDate5DaysAndInsured");
			if (objPolciyEffDate != null && objPolciyEffDate instanceof Boolean)
				flagQuoteDate = (Boolean) objPolciyEffDate;
			
			if(flagQuoteDate)
				flagQuoteDate = isQuoteEffDateGreater5DaysLess29DaysFrmPED(ctx, ctx.get("PolicyEffectiveDate").toString(), ctx.get("QuoteSentDate").toString());
		}

		if (flagQuoteDate)
			SqlResources.getSqlMapProcessor(ctx).update( "SqlStmts.UserStatementManualBoQueriesUpdateStatusUnderreview", ctx);

		objQuoteExp = RuleUtils.executeRule(ctx, "LawyersRule.isQuotationExpiredAndAgent");
		flag = false;
		if (objQuoteExp != null && objQuoteExp instanceof Boolean)
			flag = (Boolean) objQuoteExp;

		if (flag) {
			LawyersUtils.populateError(ctx, "isQuoteExpired", "Quote has been expired");
			ctx.put("isQuoteExpired", "Error");
			ctx.remove("IsQuoteSelected");
		}

		objQuoteExp = RuleUtils.executeRule(ctx,"LawyersRule.isAgentAndUnderreview");
		flag = false;
		if (objQuoteExp != null && objQuoteExp instanceof Boolean)
			flag = (Boolean) objQuoteExp;
		if (flag){
			LawyersUtils.populateError(ctx, "isQuoteExpired", "Policy must be quoted");
			ctx.put("isQuoteExpired", "Error");
		}
		
		objQuoteExp = RuleUtils.executeRule(ctx,"LawyersRule.isAgentAndNotQuoted");
		flag = false;
		if (objQuoteExp != null && objQuoteExp instanceof Boolean)
			flag = (Boolean) objQuoteExp;
		if (flag){
			LawyersUtils.populateError(ctx, "isQuoteExpired", "Policy must be quoted");
			ctx.put("isQuoteExpired", "Error");
		}
		
		
	}
	
	public static boolean isQuoteEffDateGreater5DaysLess29DaysFrmPED(IContext ctx, String policyEffectiveDateG, String quoteSentDateG){
		
		try {

			Calendar currentDate = Calendar.getInstance();
			
		    Date quoteSentDate = new SimpleDateFormat("MM/dd/yyyy").parse(quoteSentDateG);
		    Date policyEffDate = new SimpleDateFormat("yyyy-MM-dd").parse(policyEffectiveDateG);
		    
		    Calendar quoteSentCal = Calendar.getInstance();
		    quoteSentCal.setTime(quoteSentDate);
		    
		    Calendar quoteSentCal1Days = Calendar.getInstance();
		    quoteSentCal1Days.setTime(quoteSentDate);
		    quoteSentCal1Days.add(Calendar.DATE, 1);
		    
		    Calendar policyEffCal5Days = Calendar.getInstance();
		    policyEffCal5Days.setTime(policyEffDate);
		    policyEffCal5Days.add(Calendar.DATE, 5);
		    
		    Calendar policyEffCal29Days = Calendar.getInstance();
		    policyEffCal29Days.setTime(policyEffDate);
		    policyEffCal29Days.add(Calendar.DATE, 30);
		    
		    if(quoteSentCal.after(policyEffCal5Days) && quoteSentCal.before(policyEffCal29Days)) {
		    	
		    	if(currentDate.after(quoteSentCal) && currentDate.before(quoteSentCal1Days)){

		    		return false;
		    	} else {
		    		return true;
		    	}
		    } else {
	    		return true;
	    	}

		} catch (Exception e) {
		    logger.error("Unexpected error", e);
		}
		return false;

	}
	public static void setQuoteDates(Context ctx)
	{
		try
		{
			List previousPolicyKey= (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAppCretaedDate",ctx);
			ctx.putAll((Map) previousPolicyKey.get(0));
			logger.debug("hello");
			Boolean isMofifierState = false;
			Object obj = RuleUtils.executeRule(ctx,"LawyersRule.fillQuoteDatesIfNoError");
			logger.debug("hello1");
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
	}
	
	
	private String generateQuoteLetterBodyforSubProducer(IContext ctx) throws Exception {
		//String filePath=new DocumentGenerationBO().imageDownload(ctx.get("Signature").toString());
		Object objPoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"SqlStmts.UserStatementpdfquotelettergetPriorPolicyInfo", ctx);
		Map policyMap = null;
		if (objPoilcy != null && objPoilcy instanceof Map)
			policyMap = (Map) objPoilcy;

		LawyersValidationUtils utils = new LawyersValidationUtils();
		String encrytedPolicyKey = utils.encrypt(ctx.get("PolicyKey")
				.toString());
		encrytedPolicyKey = URLEncoder.encode(encrytedPolicyKey,""+ StandardCharsets.UTF_8);
		/*encrytedPolicyKey = encrytedPolicyKey.replace("=", "%3D");
		encrytedPolicyKey = encrytedPolicyKey.replace("+", "%2B");*/
		
		String SignatureText=ctx.get("SignatureText")!=null?ctx.get("SignatureText").toString():"";
		
		String resourcePath = SystemProperties.getInstance().getString(
				"appl." + ctx.getProject() + ".url");
		String ACHPath = SystemProperties.getInstance().getString(
				"appl." + ctx.getProject() + ".ach.url");
		String fax=null;
		if(ctx.get("Fax") != null && ctx.get("Fax") != "" && ctx.get("Fax")!="null"){
			fax="F:&nbsp;"+ctx.get("Fax").toString();
		}
		else{
			fax="";
		}
		
		String emailsig="";
		 if(ctx.get("EmailSignature")!=null && ctx.get("EmailSignature")!=""){
			 emailsig="<img src=\"cid:image1\">";
		 }
		 
		 String SignatureText2="";
		 if(ctx.get("SignatureText2")!=null && ctx.get("SignatureText2")!=""){
			 SignatureText2=ctx.get("SignatureText2").toString();
		 }
		
		 String SignatureText3="";
		 if(ctx.get("SignatureText3")!=null && ctx.get("SignatureText3")!=""){
			 SignatureText3="<td style='border-left: 3px solid green; padding-left: 5px'><img src=\"cid:image2\"></td>";
			}
		 
		 String Signature="";
		 if(ctx.get("Signature")!=null && ctx.get("Signature")!=""){
			 Signature="<tr><td><img src=\"cid:image\"></td></tr>";
		 }
		 
		 String subProducerCode = SystemProperties.getInstance().getString("appl.LawyersIns.subproducer.signaturedisplay");
	     	String[] subProducerCodeList = subProducerCode.split("~");
	     	for(int subProducerCodeCount = 0; subProducerCodeCount < subProducerCodeList.length; subProducerCodeCount++){
	     		if(subProducerCodeList[subProducerCodeCount].equalsIgnoreCase(ctx.get("ProducerCode").toString())){
	     			Signature = "";
	     		}
	     	}
		 
		StringBuilder msg = new StringBuilder(4096);
		if (policyMap != null) {

			msg.append("<table>");

			msg.append("<tr>");
			msg.append("<td>");
			if (policyMap.get("AccountName") != null
					&& !"".equals(policyMap.get("AccountName").toString()))
				msg.append(policyMap.get("AccountName").toString()).append("<br/>");
			if (policyMap.get("Address1") != null
					&& !"".equals(policyMap.get("Address1").toString()))
				msg.append(policyMap.get("Address1").toString()).append("<br/>");
			;
			if (policyMap.get("Address2") != null
					&& !"".equals(policyMap.get("Address2").toString()))
				msg.append(policyMap.get("Address2").toString()).append("<br/>");
			;

			if (policyMap.get("City") != null
					&& !"".equals(policyMap.get("City").toString()))
				msg.append(policyMap.get("City").toString());
			if (policyMap.get("City") != null
					&& !"".equals(policyMap.get("StateDesc").toString()))
				msg.append(", ").append(policyMap.get("StateDesc").toString());
			if (policyMap.get("City") != null
					&& !"".equals(policyMap.get("Zip").toString()))
				msg.append(", ").append(policyMap.get("Zip").toString());
			msg.append("</td>");
			msg.append("</tr>");

			msg.append("<tr>");
			msg.append("<td>");
			msg.append("<br/>Dear ");
			if (policyMap.get("ContactPerson") != null
					&& !"".equals(policyMap.get("ContactPerson").toString()))
				msg.append(policyMap.get("ContactPerson").toString());
			msg.append(":");
			msg.append("</td>");
			msg.append("</tr>");

			msg.append("<tr>");
			msg.append("<td>");
			msg.append("<br/>Thank you for completing your ").append(ctx.get("ProducerName")).append(" Lawyers application. ")
					.append("Attached is your quotation and completed application for review and signature.<br/><br/> ")
					.append(" Your quotation expires ");

			if (policyMap.get("QuoteExpiryDate") != null
					&& !"".equals(policyMap.get("QuoteExpiryDate").toString()))
				msg.append(policyMap.get("QuoteExpiryDate").toString()).append(".<br/><br/>");

			msg.append("To finalize your coverage electronically please follow the link below: ")
			       .append("<br/><br/>");
			msg.append(" <a href=")
					.append(resourcePath)
					.append("/signandpay.jsp?PolicyKey=")
					.append(encrytedPolicyKey)
					.append(">Finalize Your Coverage</a>")
					.append("<br/><br/>");
			
			       boolean flag = false;
					Object obj = RuleUtils.executeRule(ctx,
							"LawyersRule.isLSquared");
					if (obj != null && obj instanceof Boolean) {
						flag = (Boolean) obj;

				if (flag) {
					
					msg.append(" <a href=")
							.append(ACHPath)
							.append(">ACH Payment Link</a>")
							.append("<br/>");
				}
				
				msg.append("<br/>");

			}

			msg.append("This link is specifically for your account; you will be able to securely review and sign your application. Once coverage has been accepted, you will have the opportunity to continue with your payment option.")
					.append("<br/><br/> ");
					
			msg.append("Another option to accept coverage is to follow the instructions in the attached quote letter.<br/><br/>");
			if(ctx.get("PolicyStatusKey").toString().equals("1")){
				
				if(!"3".equals(ctx.get("CompanyKey").toString()))
				{
					msg.append(ctx.get("ProducerName")).append(" Lawyers is underwritten by Crum & Forster rated A ''Excellent'' by A.M. Best. Crum & Forster has over 25 years of experience offering professional liability insurance products and services.<br/><br/>");
				}
				if("3".equals(ctx.get("CompanyKey").toString()))
				{
					msg.append(ctx.get("ProducerName")).append(" Lawyers is underwritten by ISMIE Mutual Insurance Company. With 45 years in the industry, ISMIE has earned a nationwide reputation for supporting their policyholders and providing peace of mind even in some of the most litigious environments in the country.<br/><br/>");
				}
			}
					
			msg.append("If you have any additional questions, please contact us at our number ").append(ctx.get("SPPhoneNumber")).append(" and one of our licensed professional liability specialists will be happy to assist you.<br/><br/>");
			
			
			
			msg.append("</td>");
			msg.append("</tr>");
		
			msg.append("<tr>");
			msg.append("<td>");
			msg.append("Regards,<br/>");
			
			msg.append("<table cellpadding='0' cellspacing='0'>").append(Signature).append("<tr><td>").append(SignatureText).append("P:&nbsp;").append(ctx.get("SPPhoneNumber")).append("&nbsp;</br>").append(fax).append("</td>").append(SignatureText3).append("</tr></table>");
			msg.append(emailsig).append("</br>");
	        msg.append("</br>");
	        msg.append("<p style='color:#999999'>").append(SignatureText2).append("</p>");
			msg.append("</td>");
			msg.append("</tr>");
			
			msg.append("<tr><td><br/><br/></td></tr>");
			
			msg.append("</table>");

		}

		return msg.toString();
	}
}
