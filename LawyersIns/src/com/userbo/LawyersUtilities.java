package com.userbo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Clob;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.commons.fileupload.FileItem;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.net.FileRetrieve;
import com.itextpdf.tool.xml.net.FileRetrieveImpl;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.itextpdf.tool.xml.pipeline.html.LinkProvider;
import com.manage.managecomponent.components.SetParametersForStoredProcedures;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.IContext;
import com.util.InetLogger;
import com.util.RuleEngineUtility;
import com.util.SystemProperties;


public class LawyersUtilities {
	
	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int RANDOM_STRING_LENGTH = 10;
	public static String DATE_PATTERN = "MM-dd-yyyy hh:mm:ss";
	private static InetLogger logger = InetLogger.getInetLogger(LawyersUtilities.class);
	
	public static String IMG_PATH = "C://Tomcat//webapps//LawyersIns//image//";
	public static String RELATIVE_PATH = "C://Tomcat//webapps//LawyersIns//";
	public static String CSS_DIR = "C://Tomcat//webapps//LawyersIns//css//";
//	
	static{
		try {
			IMG_PATH = SystemProperties.getInstance().getString("appl.LawyersIns.email.IMG_PATH");
			RELATIVE_PATH = SystemProperties.getInstance().getString("appl.LawyersIns.email.RELATIVE_PATH");
			CSS_DIR = SystemProperties.getInstance().getString("appl.LawyersIns.email.CSS_DIR");
		}catch(Exception e){
			logger.error("Unexpected error", e);
		}
	}
	    
	public static void savePaymentDetail(IContext ctx) throws Exception {
		boolean finalisedFlag = false;

		List quoteList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.quoteOptionviewgetQuoteListAll", ctx);
		if (quoteList != null) {
			for (int i = 0; i < quoteList.size(); i++)
			{
				Map quote = (Map) quoteList.get(i);
				if (quote.get("IsThisOptionFinalised") != null && quote.get("IsQuoteSelected") != null) 
				{
					if ("Y".equals(quote.get("IsThisOptionFinalised").toString())&& "Y".equals(quote.get("IsQuoteSelected").toString())) 
					{
						ctx.put("TransactionSequence", quote.get("TransactionSequence"));
						finalisedFlag = true;
					}
				}
			}
		}

		if (!finalisedFlag)
			return;

		Object objPaymentExist = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsgetPaymentDetail",ctx);
		//Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("PaymentDetail.findByKey", ctx);
		Map paymentDetailMap = null;

		if (objPaymentExist != null && objPaymentExist instanceof Map) {
			paymentDetailMap = (Map) objPaymentExist;
			ctx.putAll(paymentDetailMap);
		}

		boolean finalisedOptionChanged = false;
		/*if (objPaymentExist != null && obj == null)
			finalisedOptionChanged = true;*/

		//RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");
		
		/*if (ctx.get("LastUpdateTimeStamp")!= null)
			ctx.put("TransactionDate",ctx.get("LastUpdateTimeStamp"));
		else*/
			ctx.put("TransactionDate",new SimpleDateFormat(DATE_PATTERN).format(new Date()));
		
		if (ctx.get("PaymentMode") == null)
			ctx.put("PaymentMode", "eft");
		if (ctx.get("TransactionStatus") == null)
			ctx.put("TransactionStatus", "Completed");
		Object objLastUpdateUserID = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsgetLastUpdateUserID",ctx);
		if (objLastUpdateUserID != null && objLastUpdateUserID instanceof Map) {
			Map mapLastUpdateUserID = (Map) objLastUpdateUserID;
			ctx.put("LastUpdateUserID", mapLastUpdateUserID.get("LastUpdateUserID"));
		}

		if (finalisedOptionChanged) {
/*			SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementsaveAccountstmtsdeletePaymentDetail",ctx);
*/			SqlResources.getSqlMapProcessor(ctx).insert("PaymentDetail.insert",ctx);

		} else {
			if (paymentDetailMap == null)
				SqlResources.getSqlMapProcessor(ctx).insert("PaymentDetail.insert", ctx);
			else if (paymentDetailMap != null)
				SqlResources.getSqlMapProcessor(ctx).update("PaymentDetail.update", ctx);
		}

		ctx.remove("IsDocumentProcessed");		
	}
	
	public static void evaluateWorkFlow(Context ctx) throws Exception
	{
		RuleEngineUtility.evaluateAttornies(ctx);
		
	}
	
	/*public static int getListSize()
	{
		int number;
		Random rnum=new Random();
		number=(rnum.nextInt(9)*1)+1;
		return number;
		
	}*/
	public static void getQuestionsList(Context ctx) throws Exception
	{
		List questionsList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementUWReviewQuestionsAndCommentsgetAllQuestions", ctx);
		if (questionsList != null) 
		{
		Element qTable=new Element("table");
		qTable.setAttribute("border","0");
		qTable.setAttribute("cellpadding","0");
		qTable.setAttribute("cellspacing","0");
		qTable.setAttribute("width","100%");
		for (int i = 0; i < questionsList.size(); i++) {
		Map questions = (Map) questionsList.get(i);
			Element qTr=new Element("tr");
			Element qTd=new Element("td");
			qTd.setAttribute("class","tdViewNumber");
			Element span11=new Element("span");
			span11.addContent(questions.get("rownum").toString()+".");
			qTd.addContent(span11);
			//Element span1=new Element("span");
			//span1.addContent(".");
			//qTd.addContent(span1);
			qTr.addContent(qTd.detach());
			qTd=new Element("td");
			qTd.setAttribute("class","tdViewTitle");
			qTd.addContent(LawyersUtils.clobStringConversion((Clob)questions.get("Description")));
			qTr.addContent(qTd.detach());
			qTable.addContent(qTr.detach());
			qTr=new Element("tr");
			qTd=new Element("td");
			qTd.setAttribute("colspan","2");
			ctx.put("rulepolicyid", questions.get("rulepolicyid"));
			qTd.addContent(getCommentsList(ctx,questions.get("ruleid").toString()).detach());
			qTr.addContent(qTd.detach());
			qTable.addContent(qTr.detach());
			
		}
		String str = new XMLOutputter(Format.getPrettyFormat()).outputString(qTable);
		ctx.put("#dynamicContent#", str);
		//System.out.println(str);
		}
		else
			logger.debug("********** ***********************");
	}
	public static Element getCommentsList(Context ctx,String ruleid) throws Exception
	{	Element commentsTable=null;
		/*List commentsList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementUWReviewQuestionsAndCommentsgetAllCommentsList", ctx);*/
	List commentsList=null;
	try {
		ctx.put("RuleId",ruleid);
		new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,RuleId");
		commentsList=SqlResources.getSqlMapProcessor(ctx).select("RuleComment.GetAllCommentList_p",ctx);
	} catch (Exception e) {
				logger.error("Unexpected error", e);
			}
			
		if (commentsList != null) 
		{	if(commentsList.size()>0)
			{
				 commentsTable=new Element("table");
				 commentsTable.setAttribute("class","tdViewComments");
			for (int i = 0; i < commentsList.size(); i++)
			{
				Map comments = (Map) commentsList.get(i);
				Element commentsTr=new Element("tr");
				Element commentsTd=new Element("td");
				Element blockquote=new Element("blockquote");
				blockquote.setAttribute("class","commentor");
				Element span1=new Element("span");
				span1.setAttribute("class","commentorName");
				span1.addContent(comments.get("UploadedBy").toString());
				blockquote.addContent(span1);
				Element span2=new Element("span");
				span2.setAttribute("class","commentorTime");
				span2.addContent(comments.get("TimeStamp").toString());
				blockquote.addContent(span2);
				Element div1=new Element("div");
				div1.addContent(comments.get("comment").toString());
				blockquote.addContent(div1);
			
				commentsTd.addContent(blockquote.detach());
				commentsTr.addContent(commentsTd.detach());
				commentsTable.addContent(commentsTr.detach());
			}
		}
		else
		{
			 commentsTable=new Element("table");
			 Element commentsTr=new Element("tr");
			 Element commentsTd=new Element("td");
			 commentsTd.addContent("  ");
			 commentsTr.addContent(commentsTd.detach());
			 commentsTable.addContent(commentsTr.detach());
			
		}
		}
		String str = new XMLOutputter(Format.getPrettyFormat()).outputString(commentsTable);
		logger.debug(str);
		return commentsTable;
	}
	
	// To make call to RuleEngine
	
	
	public static void validateAllPagesSaved(Context ctx) throws Exception
	{
		boolean flag=false;
		
		List objFirm = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.firmviewgetAllPageFlags", ctx);
		/*List objPolicyTransactionFirm = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetPolicyTransaction", ctx);*/
		List objPolicy= SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetPolicyData", ctx);
		ctx.putAll((Map) objFirm.get(0));
		/*ctx.putAll((Map) objPolicyTransactionFirm.get(0));*/
		ctx.putAll((Map) objPolicy.get(0));
		List aoplist = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields", ctx);
		Map covMap = new HashMap();
		if (aoplist != null)
		{
			Map map = new HashMap();
			for (int i = 0; i < aoplist.size(); i++)
			{
				map = (HashMap) aoplist.get(i);
				covMap.put("AOP_Percentage_" + map.get("AOPKey"),map.get("PercentageValue"));
			}
		}
		 if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("1") )
			{
			
			if( (ctx.get("IsFirmPage")!=null && !ctx.get("IsFirmPage").toString().equals("Y")) 
				||(ctx.get("IsAopPage")!=null && !ctx.get("IsAopPage").toString().equals("Y"))
				||(ctx.get("IsPracticePage")!=null && !ctx.get("IsPracticePage").toString().equals("Y"))
				||(ctx.get("IsCoveragePage")!=null && !ctx.get("IsCoveragePage").toString().equals("Y")))
					flag=true;
			}
		 if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("2") )
		{
			 if( ctx.get("IsRenewFirmPage")!=null && !ctx.get("IsRenewFirmPage").toString().equals("Y"))
				flag=true;
		}
		if (covMap != null) 
		{
			String percentageValue1 = covMap.get("AOP_Percentage_20") != null ? covMap.get("AOP_Percentage_20").toString() : "0";
			int percentageVal1 = Integer.parseInt(percentageValue1);
			String percentageValue2 = covMap.get("AOP_Percentage_27") != null ? covMap.get("AOP_Percentage_27").toString() : "0";
			int percentageVal2 = Integer.parseInt(percentageValue2);
			String percentageValue3 = covMap.get("AOP_Percentage_18") != null ? covMap.get("AOP_Percentage_18").toString() : "0";
			int percentageVal3 = Integer.parseInt(percentageValue3);
			String percentageValue4 = covMap.get("AOP_Percentage_24") != null ? covMap.get("AOP_Percentage_24").toString() : "0";
			int percentageVal4 = Integer.parseInt(percentageValue4);
			if (percentageVal1 > 0 || percentageVal2 > 0 || percentageVal3 > 0 || percentageVal4 > 0) 
			{
				if(percentageVal1 > 0 )
				{ 
					if(ctx.get("IsRealEstateCommercialPage")!=null && !ctx.get("IsRealEstateCommercialPage").toString().equals("Y") )
					flag=true;
				}	
				if(percentageVal2 > 0 )
				{
					if(ctx.get("IsRealEstateResidentialPage")!=null && !ctx.get("IsRealEstateResidentialPage").toString().equals("Y") )
						flag=true;
				}
				if(percentageVal3 > 0 )
				{
					if(ctx.get("IsPlaintiffPage")!=null && !ctx.get("IsPlaintiffPage").toString().equals("Y") )
					flag=true;
				}	
				if(percentageVal4 > 0 )
				{
					if(ctx.get("IsWillsEstatePage")!=null && !ctx.get("IsWillsEstatePage").toString().equals("Y") )
					flag=true;
				}
			}	
			if(flag)
			{
				LawyersUtils.populateError(ctx, "StatusKey","Please complete all  pages for Quote Options.");
				
			}
			
		}
		RuleUtils.executeRule(ctx,"LawyersRule.AssignLastUpdateTimeStamp");
	}
	public static void validateChangeStatus(Context ctx) throws Exception
	{
		if(ctx.get("StatusKey")!=null && ctx.get("StatusKey").toString().equals("9"))
		{
			validateAllPagesSaved(ctx);
			RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");
			if((Boolean) RuleUtils.executeRule(ctx, "LawyersRule.isInetErrorListEmpty"))
			{	
				 SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementdroolQueriesupdateStatusToUWreview",ctx);
			}
			/*else
			{
				List objPolicyTransactionFirm = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetPolicyTransaction", ctx);
				Context newCtx= new Context();
				newCtx.putAll(ctx);
				newCtx.putAll((Map) objPolicyTransactionFirm.get(0));
				 try {
					SqlResources
							.getSqlMapProcessor(newCtx)
							.update("SqlStmts.UserStatementdroolQueriesupdateStatusToUWreview",
									newCtx);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				List <Object> li=new ArrayList<Object>();
				ctx.put("inet_errors_list",li);	
			}*/
		}
		else
		{
			SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementdroolQueriesupdateStatusToUWreview",ctx);
		}
	}
	public static void evaluateUnderWriterWorkFlow(Context ctx) throws Exception
	{
		
		try {
			
			int statusKey=ctx.get("StatusKey")==null?0:Integer.valueOf(ctx.get("StatusKey").toString().trim());
			
					if (statusKey==2)
					{
						new SetParametersForStoredProcedures().setParametersInContext(ctx,"PolicyKey,PolicyStatusKey");
			            List obj = SqlResources.getSqlMapProcessor(ctx).select("Quote.SkipOrFollowUWFlow_p", ctx);
			            ctx.putAll((Map) obj.get(0));
			      	}
					else if(statusKey==9)
					{
						ctx.put("FollowUnderwriterFlow","Y");
						SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementunderwriterFlowExecutionupdateFirmFollowUWFlow", ctx);
					}
					else 
					{
						ctx.put("FollowUnderwriterFlow","N");
						SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementunderwriterFlowExecutionupdateFirmFollowUWFlow", ctx);
					}
			}
			catch (Exception e)
			{
				logger.error("Unexpected error", e);
			}
	}
	public static void getAopsValuesForRenewal(Context ctx)
	{
		try
		{	
			//By Raghu CCB Supplement
			RuleUtils.executeRule(ctx, "LawyersRule.checkCCBSupp");
			
			new SetParametersForStoredProcedures().setParametersInContext(ctx,"PolicyKey,litigationNewImplDate,RenewalSupplementNewImplDate");
			Object objInvoiceData = SqlResources.getSqlMapProcessor(ctx).select("FirmLW.SupplementFlowControlRenewal_p", ctx);
			if(objInvoiceData !=null)
			{				
				List finalList=(List)objInvoiceData;
				if(finalList.size()>0)
				{
					
						Map map = new HashMap();
						for (int i = 0; i < finalList.size(); i++) 
						{
							map = (HashMap) finalList.get(i);
							ctx.put("isBankruptcy",map.get("isBankruptcy"));
							ctx.put("isCollection",map.get("isCollection"));
							ctx.put("isCopyright",map.get("isCopyright"));
							ctx.put("isFinancial",map.get("isFinancial"));
							ctx.put("isFamilyLaw",map.get("isFamilyLaw"));
							ctx.put("isGoverment",map.get("isGoverment"));
							ctx.put("isLitigation",map.get("isLitigation"));
							ctx.put("isRealestate",map.get("isRealestate"));
							ctx.put("isTax",map.get("isTax"));
							ctx.put("isWillsTrust",map.get("isWillsTrust"));
							ctx.put("isCCB",map.get("isCCB"));
						}
				}	
					
				
			}
			AOP.getAopsValues(ctx);
			
		}
		catch(Exception e)
		{
		logger.error("Unexpected error", e);
		}
	}




	public static void loadLawPracticeData(Context ctx)
	{
		try
		{
			HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
	        HttpSession sess = req.getSession();
	        if(ctx.get("aoplist_list_2")==null)
	        ctx.put("aoplist_list_2",sess.getAttribute("aoplist_list_2"));
	     
			List finalList =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields",ctx);
	        if(finalList != null &&  finalList instanceof List)
	        {
	           Map map = new HashMap();
	            for (int i = 0; i < finalList.size(); i++) 
	    		{	
	           	map = (HashMap) finalList.get(i);
	           	int key=map.get("AOPKey")!=null && !map.get("AOPKey").equals(HtmlConstants.EMPTY)?Integer.parseInt(map.get("AOPKey").toString()):0;
	           		if(key==25)
					{
						int aopPercentage=map.get("PercentageValue")!=null && !map.get("PercentageValue").equals(HtmlConstants.EMPTY)?Integer.parseInt(map.get("PercentageValue").toString()):0;
						if(aopPercentage>0)
						{
							ctx.put("otherAopDescLaw",map.get("AOPCommentDesc"));
						}
						else
							ctx.put("otherAopDescLaw",null);
					}
					if(key==58)
					{
						int aopPercentage=map.get("PercentageValue")!=null && !map.get("PercentageValue").equals(HtmlConstants.EMPTY)?Integer.parseInt(map.get("PercentageValue").toString()):0;
						if(aopPercentage>0)
						{
							ctx.put("litDefenseOtherDesc",map.get("AOPCommentDesc"));
						}
						else
							ctx.put("litDefenseOtherDesc",null);
					}
					if(key==67)
					{
						int aopPercentage=map.get("PercentageValue")!=null && !map.get("PercentageValue").equals(HtmlConstants.EMPTY)?Integer.parseInt(map.get("PercentageValue").toString()):0;
						if(aopPercentage>0)
						{
							ctx.put("litPlaintiffotherDesc",map.get("AOPCommentDesc"));
						}
						else
							ctx.put("litPlaintiffotherDesc",null);
					}	
	    		}
			} 
			if("2".equals(ctx.get("PolicyStatusKey").toString()))
			{
				List previousPolicyKey= (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey",ctx);
				ctx.putAll((Map) previousPolicyKey.get(0));
				Context oldPolicyContext=new Context();
				oldPolicyContext.setProject(ctx.getProject());
				oldPolicyContext.put("PolicyKey",ctx.get("PreviousPolicykey"));
				oldPolicyContext.put("VersionSequence",ctx.get("previousVersionSequence"));
				
				List finalList1 =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields",oldPolicyContext);
		        if(finalList1 != null &&  finalList1 instanceof List)
		        {
		           Map map = new HashMap();
		            for (int i = 0; i < finalList1.size(); i++) 
		    		{	
		           	map = (HashMap) finalList1.get(i);
		           	int key=map.get("AOPKey")!=null && !map.get("AOPKey").equals(HtmlConstants.EMPTY)?Integer.parseInt(map.get("AOPKey").toString()):0;
		           	if(key==25)
					{
						int aopPercentage=map.get("PercentageValue")!=null && !map.get("PercentageValue").equals(HtmlConstants.EMPTY)?Integer.parseInt(map.get("PercentageValue").toString()):0;
						if(aopPercentage>0)
						{
							ctx.put("otherAopDescPriorYear",map.get("AOPCommentDesc"));
						}
						else
							ctx.put("otherAopDesc",null);
					}
		           	
		           	
	   				if(key==58)
	   				{
	   					int aopPercentage=map.get("PercentageValue")!=null && !map.get("PercentageValue").equals(HtmlConstants.EMPTY)?Integer.parseInt(map.get("PercentageValue").toString()):0;
	   					if(aopPercentage>0)
	   					{
	   						ctx.put("litDefensePriorOtherDesc",map.get("AOPCommentDesc"));
	   					}
	   					else
	   						ctx.put("litDefensePriorOtherDesc",null);
	   				}
	   				if(key==67)
	   				{
	   					int aopPercentage=map.get("PercentageValue")!=null && !map.get("PercentageValue").equals(HtmlConstants.EMPTY)?Integer.parseInt(map.get("PercentageValue").toString()):0;
	   					if(aopPercentage>0)
	   					{
	   						ctx.put("litPlaintiffPriorOtherDesc",map.get("AOPCommentDesc"));
	   					}
	   					else
	   						ctx.put("litPlaintiffPriorOtherDesc",null);
	   				}	
		           }
				}
			}
			
		}
		catch(Exception e)
		{
		logger.error("Unexpected error", e);
		logger.error("Error in LawyerUtilities.loadLawPracticeData..."+e.getMessage());
		}
	}

	public static void setLicensedStates(Context ctx)
	{
		HttpServletRequest req = (HttpServletRequest) ctx
				.get("DocumentRequest");
		HttpSession sess = req.getSession();

		List finalList = null;
		try {
			int index = ctx.get("rownum") != null && !ctx.get("rownum").equals(HtmlConstants.EMPTY) ? Integer.parseInt(ctx.get("rownum").toString()) : 100;
			index = index - 1;
			if (sess.getAttribute("AttorneysDetailsList_list_01") != null && sess.getAttribute("AttorneysDetailsList_list_01") instanceof List) {
				finalList = (List) sess.getAttribute("AttorneysDetailsList_list_01");

				Map map = new HashMap();

				for (int i = 0; i < finalList.size(); i++) {
					map = (HashMap) finalList.get(i);
					if (i == index) 
					{
						map.put("LicensedStates", ctx.get("newLicensedStates"));
					}

				}
				ctx.put("AttorneysDetailsList_list_01", finalList);
				sess.setAttribute("AttorneysDetailsList_list_01", finalList);
			}
		} catch (Exception e) {
			logger.error("Unable to populate attorney details", e);
		}
	}

	public static Object removeAmountFormatLawyers(Object arg1){
	    if(arg1 == null || arg1.equals(HtmlConstants.EMPTY))
	        return 0.0;
	    
	    String amount = arg1.toString();     
	    amount=amount.replace("$", "").replace(",", "");
	    if(amount.contains("%"))
	    	amount=amount.substring(0, amount.length()-1);
	    	
	    return amount;
	}
	
	public static void validateClaimSupplement(Context ctx)
	{
		try
		{
			List claimList=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetProfLiabClmAgntList",ctx);
			if(claimList.size()<1)
				LawyersUtils.populateError(ctx, "ClaimSupplemnts","Claims information is empty, atleast one record is required.");
		}
		catch(Exception e)
		{
			logger.error("error occured while validating claim supplement page");
		}
	}
	/*public static Object removeAmountFormatLawyers(Object arg1){
	    if(arg1 == null || arg1.equals(HtmlConstants.EMPTY))
	        return 0.0;
	    
	    String amount = arg1.toString();      
	    return amount.replace("$", "").replace(",", "");
	}*/

	public static Object amountFormatLawyers(Object arg1){
	    if(arg1 == null || arg1.equals(HtmlConstants.EMPTY))
	        return 0;
	    
	    String amount = arg1.toString();      
		DecimalFormat df = new DecimalFormat("$###,###.###"); 
	    return df.format(Double.parseDouble(amount));
	}
	public static String generateUniqueId()
	{
		//System.out.println("ASDADASDASSADASD");
		String uniqueId ="";
		  try {
			  uniqueId = new Timestamp(new Date().getTime()).toString().replaceAll("[.,:\\ ]", "");
		//System.out.println("uniqueId" + uniqueId);
		  }
		  catch (Exception e) {
			  uniqueId="Not Found";
		  }
		  return uniqueId;
	}
	
	public static String generateRandomString(){
	    
	    StringBuffer randStr = new StringBuffer();
	    for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	        int number = getRandomNumber();
	        char ch = CHAR_LIST.charAt(number);
	        randStr.append(ch);
	    }
	    return randStr.toString();
	}
	
	private static int getRandomNumber() {
	    int randomInt = 0;
	    Random randomGenerator = new Random();
	    randomInt = randomGenerator.nextInt(CHAR_LIST.length());
	    if (randomInt - 1 == -1) {
	        return randomInt;
	    } else {
	        return randomInt - 1;
	    }
	}
	public static String getCurrentDate() {
		return new  SimpleDateFormat("MM/dd/yyyy").format(new Date());
	}
	
	public static String uploadFile(Context ctx, String uploaddirectory) throws Exception
	{
		String filePath=null;
		FileItem uploadFile = null;
		File tempFile1 = null;
		
		try {
			List fileItems = (List)ctx.get("fileItems");
			Iterator i = fileItems.iterator();
			
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// filename on the client
					if("file_path".equals(fi.getFieldName())){
						filePath=fi.getName();
						uploadFile = fi;
						ctx.put("ReportName",filePath);
					}
				}
			}
			logger.debug(filePath);	
		} catch (Exception e) {
			logger.error("Unable to write document to file", e);
			logger.error("Unexpected error", e);
		}
		
		filePath = generateUniqueId() + "_" + filePath;
		
		tempFile1 = new File(uploaddirectory);
		if (!tempFile1.exists()){
			tempFile1.mkdir();
		}
		try {
			uploadFile.write(new File(uploaddirectory, filePath));
		} catch (Exception e) {
			logger.error("Unable to write document to file", e);
			LawyersUtils.populateError(ctx, "DocUploadError","Document could not be write to file" );
			
		}
		return filePath;
	}
	
	public static void fileDelete(String uploaddirectory, String fileName){
		File deleteFile = new File(uploaddirectory, fileName);
		if (deleteFile.exists()){
			deleteFile.delete();
		}
	}
	
	@SuppressWarnings("finally")
	public static String dateFormat(Object o) {
		StringBuilder sb=null;
		String dateString =null,formatedDate=null;
		DateFormat formatter=null;
		try
		{
			 sb=new StringBuilder();
		 dateString = o.toString();
		 formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			Date date = (Date)formatter.parse(dateString);
			logger.debug(date);        

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int month=cal.get(Calendar.MONTH) + 1;
			if(month<10)
				sb.append("0"+month+"/");
			else
				sb.append(month+"/");
			
			int date1=cal.get(Calendar.DATE);
			if(date1<10)
				sb.append("0"+date1+"/"+  cal.get(Calendar.YEAR));
			else
				sb.append(date1+"/"+  cal.get(Calendar.YEAR));
			formatedDate=sb.toString();
		} catch(Exception e)
		{
			logger.error("Unexpected error", e);
			formatedDate= dateString;
		}
		finally
		{
			return formatedDate;
		}
	}
	
	public static List<Map<String, Object>> jsonArrayStringToList(Object data) throws JSONException {
		if (data instanceof String) {
			String value = "{\"data\": " + data + "}";
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			JSONObject jsnobject = new JSONObject(value);
			JSONArray jsonArray = jsnobject.getJSONArray("data");
			for (int i = 0; i < jsonArray.length(); i++) {
			    JSONObject explrObject = jsonArray.getJSONObject(i);
			    list.add(jsonStringToMap(explrObject.toString()));
			}
			return list;
		}
		return null;
	}

	public static Map<String, Object> jsonStringToMap(Object data) throws JSONException {
		if (data instanceof String) {
			String jsonValue =  (String) data;
			Map<String, Object> params = new HashMap<String, Object>();
			JSONObject jObject = new JSONObject(jsonValue);
			Iterator<?> keys = jObject.keys();
	        while(keys.hasNext()){
	            String key = (String)keys.next();
	            String value = String.valueOf(jObject.get(key)); 
	            params.put(key, value);
	        }
			return params;
		}
		return null;
	}

	public static IContext jsonStringToIContext(Object data) throws JSONException {
		if (data instanceof String) {
			String jsonValue =  (String) data;
			IContext params = new Context();
			JSONObject jObject = new JSONObject(jsonValue);
			Iterator<?> keys = jObject.keys();
	        while(keys.hasNext()){
	            String key = (String)keys.next();
	            String value = String.valueOf(jObject.get(key)); 
	            params.put(key, value);
	        }
			return params;
		}
		return null;
	}
	
	public static String authorizeTransaction(JSONObject request, String webServiceURI) {
		try {
			ClientConfig clientConfig = new DefaultClientConfig();

			Client client = Client.create(clientConfig);

			WebResource webResource = client.resource(webServiceURI);
			ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, request.toString());
			
			if (response.getStatus() != 200) {
				return null;
			}
			
			String result = response.getEntity(String.class);
			
			result = result.replace("\\", "");
			result=result.substring(1, result.length() - 1); 
			result=result.replace("\"[", "[");
			result=result.replace("]\"", "]");
			return result;
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getEmailBodyData(String event_Name, IContext ctx) throws Exception{
		
		Context newCtx = new Context();
		newCtx.putAll(ctx);
		newCtx.put("event_name", event_Name);
		List getEmailData = (List)SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementManualBoQueriesgetEmailSendBodyData", newCtx);
		
		return getEmailData;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getEmailAttachmentData(String event_Name, IContext ctx) throws Exception{
		
		Context newCtx = new Context();
		newCtx.putAll(ctx);
		newCtx.put("event_name", event_Name);
		List getEmailData = (List)SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementManualBoQueriesgetEmailSendAttachmentData", newCtx);
		
		return getEmailData;
	}
	
	public static String replaceEmailParameterValues(Map<String, Object> parameters, String html) throws Exception{
		
		if(parameters != null){
			for (Map.Entry<String, Object> entry : parameters.entrySet()) {
				
				html = html.replace(entry.getKey(),entry.getValue().toString());
//			    System.out.println(entry.getKey() + "/" + entry.getValue());
			}
		}
		return html;
	}
		
	public static void createPdf(String file, String HTMLData) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        // step 3
        document.open();
        // step 4

        // CSS
        CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(false);
        FileRetrieve retrieve = new FileRetrieveImpl(CSS_DIR);
        cssResolver.setFileRetrieve(retrieve);

        // HTML
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        htmlContext.setImageProvider(new AbstractImageProvider() {
            public String getImageRootPath() {
                return IMG_PATH;
            }
        });
        htmlContext.setLinkProvider(new LinkProvider() {
            public String getLinkRoot() {
                return RELATIVE_PATH;
            }
        });

        // Pipelines
        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

        // XML Worker
        XMLWorker worker = new XMLWorker(css, true);
        XMLParser p = new XMLParser(worker);
//        p.parse(new FileInputStream(HTML));
        
        p.parse(new ByteArrayInputStream(HTMLData.getBytes(StandardCharsets.UTF_8)));

        // step 5
        document.close();
    }
}

