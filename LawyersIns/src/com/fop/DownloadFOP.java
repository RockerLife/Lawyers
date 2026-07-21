package com.fop;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.fop.servlet.ServletContextURIResolver;

import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.processor.RequestProcessor;
import com.util.Context;
import com.util.DocumentGenerationBO;
import com.util.HtmlConstants;
import com.util.IOUtils;
import com.util.InetLogger;
import com.util.SystemProperties;

public class DownloadFOP {

	private static InetLogger logger = InetLogger
			.getInetLogger(DownloadFOP.class);

	public void makePdf(Context ctx, HttpServletRequest req,
			HttpServletResponse res, ServletContextURIResolver uriResolver,
			ServletContext servletContext) {

		String xmlFile = "";
		String foFile = "";

		try {

			xmlFile = SystemProperties.getInstance().getString("html.basedir")
					+ "data//quoteletter.xml";
			foFile = SystemProperties.getInstance().getString("html.basedir")
					+ "foxsl//quoteletter.xsl";

			fetchDataForXml(ctx);
			populateDataXml(ctx, xmlFile);
			
			new XML2PDF().convertPOToPDFQuoteLetter(foFile, xmlFile, ctx, req,
					res, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER), servletContext);

		} catch (Exception e) {
			logger
					.error("Problem in generating quote letter for Quote Number : "
							+ ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}

	public void makePdf(Context ctx, OutputStream out, String baseUrl,
			ServletContextURIResolver uriResolver) {

		String xmlFile = "";
		String foFile = "";

		try {
			xmlFile = SystemProperties.getInstance().getString("html.basedir")
					+ "data//quoteletter.xml";
			if(!"3".equals(ctx.get("CompanyKey").toString()))
			{
				
				if("Y".equals(ctx.get("SubProducerAccess")))
				{
	
					foFile = SystemProperties.getInstance().getString("html.basedir")
							+ "foxsl//quotelettersub.xsl";
				}
				else
				{
					
					foFile = SystemProperties.getInstance().getString("html.basedir")
							+ "foxsl//quoteletter.xsl";
				}
			
			}
			if("3".equals(ctx.get("CompanyKey").toString())) {
				if("Y".equals(ctx.get("SubProducerAccess")))
				{
					boolean ruleFlag=false;
					Object obj = RuleUtils.executeRule(ctx, "LawyersRule.StatesWithUpdatedQuoteLetter");	
					if(obj != null && obj instanceof Boolean )
						ruleFlag =(Boolean) obj;
					if(ruleFlag==true)
						foFile = SystemProperties.getInstance().getString("html.basedir")+ "ISMIE2022//quotelettersub_new.xsl";
					else
					foFile = SystemProperties.getInstance().getString("html.basedir")+ "ISMIE2022//quotelettersub.xsl";
				} else {
//					if("CA".equals(ctx.get("StateCode").toString()) ||"IL".equals(ctx.get("StateCode").toString())) {
//						foFile = SystemProperties.getInstance().getString("html.basedir")+ "ISMIE2022//quoteletter_CA_IL.xsl";
//					}
//					if("AZ".equals(ctx.get("StateCode").toString()) ||"GA".equals(ctx.get("StateCode").toString()) || "IN".equals(ctx.get("StateCode").toString())
//						||"MI".equals(ctx.get("StateCode").toString()) ||"MN".equals(ctx.get("StateCode").toString()) || "NC".equals(ctx.get("StateCode").toString())
//						||"TX".equals(ctx.get("StateCode").toString()) ||"UT".equals(ctx.get("StateCode").toString())	) {
//						foFile = SystemProperties.getInstance().getString("html.basedir")+ "ISMIE2022//quoteletter_AZ_GA_IN_MI_MN_NC_TX_UT.xsl";
//					}
//					else {
//						foFile = SystemProperties.getInstance().getString("html.basedir")+ "ISMIE2022//quoteletter.xsl";
//					}
					
					
					boolean ruleFlag1=false,ruleFlag2=false,ruleFlag3=false;
					Object obj = RuleUtils.executeRule(ctx, "LawyersRule.quoteLetterNotSub_CA_IL");	
					if(obj != null && obj instanceof Boolean )
						ruleFlag1 =(Boolean) obj;
					if(ruleFlag1)
						foFile = SystemProperties.getInstance().getString("html.basedir")+ "ISMIE2022//quoteletter_CA_IL.xsl";
					
					obj = RuleUtils.executeRule(ctx, "LawyersRule.quoteLetterNotSub_AZ_GA_IN_MI_MN_NC_TX_UT");	
					if(obj != null && obj instanceof Boolean )
						ruleFlag2 =(Boolean) obj;
					if(ruleFlag2)
						foFile = SystemProperties.getInstance().getString("html.basedir")+ "ISMIE2022//quoteletter_AZ_GA_IN_MI_MN_NC_TX_UT.xsl";
						
					obj = RuleUtils.executeRule(ctx, "LawyersRule.quoteLetterNotSub_New");	
					if(obj != null && obj instanceof Boolean )
						ruleFlag3 =(Boolean) obj;	
					if(ruleFlag3)
						foFile = SystemProperties.getInstance().getString("html.basedir")+ "ISMIE2022//quoteletter.xsl";
				}
			}
			fetchDataForXml(ctx);
			populateDataXml(ctx, xmlFile);
			
			baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			
			new XML2PDF().convertPOToPDF(foFile, xmlFile, out, baseUrl,
					(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER),ctx);
		} catch (Exception e) {
			logger
					.error("Problem in generating quote letter for Quote Number : "
							+ ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}
	
	public void makePdfAssureSign(Context ctx, OutputStream out, String baseUrl,
			ServletContextURIResolver uriResolver,ServletContext servletContext) {

		String xmlFile = "";
		String foFile = "";

		try {

			/*xmlFile = SystemProperties.getInstance().getString("html.basedir")
					+ "data//quoteletter.xml";
			
			boolean ispdfNew = false;
			Object objpdfRule = RuleUtils.executeRule(ctx,"LawyersRule.IsNotRenewalPolicyNewApp");
			if (objpdfRule != null && objpdfRule instanceof Boolean) {
				ispdfNew = (Boolean) objpdfRule;
			}
			if(ispdfNew)
			{
				foFile = SystemProperties.getInstance().getString("html.basedir")+ "foxsl//NewAppApplication.xsl";
				logger.error("Docu sign PDF path  for Policy "+ctx.get("PolicyKey")+" is "+foFile);
				new DocumentGenerationBO().fetchDataForApplicationPDF(ctx);
				logger.error("completed Gathering data for  Docu sign PDF for Policy "+ctx.get("PolicyKey"));
				Object objPolCovLW = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetPolicyCoverageFullDetails", ctx);
				ctx.put("coverage_freeform_2", objPolCovLW);
			}
			
			else
			{
				foFile = SystemProperties.getInstance().getString("html.basedir")+ "foxsl//application.xsl";
				logger.error("Docu sign PDF path  for Policy "+ctx.get("PolicyKey")+" is "+foFile);
				new DownloadPDF().fetchDataForXml(ctx);
			
			}*/
			
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
			boolean oldApp = false,flowFlag=false;
			Object obj = RuleUtils.executeRule(ctx, "LawyersRule.isOldAppFlow");	
			if(obj != null && obj instanceof Boolean )
				oldApp =(Boolean) obj;
			//LawyersRule.ApplicationDocumentRule
			obj = RuleUtils.executeRule(ctx, "LawyersRule.setNewFilingConditions");
			
			boolean flag = false;
			
			xmlFile = htmlDir + "data//quoteletter.xml";
			//isNewAppFlow
			if(oldApp){
				foFile = htmlDir + "foxsl//application.xsl";
				new DownloadPDF().fetchDataForXml(ctx);
			}else{		
				obj = RuleUtils.executeRule(ctx, "LawyersRule.ApplicationDocumentRule");
				
				obj = RuleUtils.executeRule(ctx, "LawyersRule.checkNewApplicationDoc2021");
				if(obj != null && obj instanceof Boolean )
					flowFlag =(Boolean) obj;
				
				if(flowFlag)
				{
					
					obj = RuleUtils.executeRule(ctx, "LawyersRule.IsNewPolicyNewAppDoc2021");
					if(obj != null && obj instanceof Boolean )
						flowFlag =(Boolean) obj;
						if(flowFlag)
							foFile = htmlDir+ "foxsl2021//NewAppApplication.xsl";
						
					obj = RuleUtils.executeRule(ctx, "LawyersRule.IsNewPolicyNewAppDoc2021FL");
					if(obj != null && obj instanceof Boolean )
						flowFlag =(Boolean) obj;
						if(flowFlag)
							foFile = htmlDir+ "foxsl2021/NewAppApplication_FL.xsl";
						
					obj = RuleUtils.executeRule(ctx, "LawyersRule.IsNewPolicyNewAppDoc2021MT");
					if(obj != null && obj instanceof Boolean )
						flowFlag =(Boolean) obj;
						if(flowFlag)
							foFile = htmlDir+ "foxsl2021/NewAppApplication_MT.xsl";
						
					obj = RuleUtils.executeRule(ctx, "LawyersRule.IsNewPolicyNewAppDoc2021KS");
					if(obj != null && obj instanceof Boolean )
						flowFlag =(Boolean) obj;
						if(flowFlag)
							foFile = htmlDir+ "foxsl2021/NewAppApplication_KS.xsl";
				}
				else
				{
					obj = RuleUtils.executeRule(ctx,
							"LawyersRule.IsNewPolicyNewAppDoc2020FL");
					if (obj != null && obj instanceof Boolean)
						flag = (Boolean) obj;
					if (flag)
						foFile = htmlDir
								+ "foxsl2020//NewAppApplication_FL.xsl";
					else {
						obj = RuleUtils.executeRule(ctx,
								"LawyersRule.IsNewPolicyNewAppDoc2020");
						if (obj != null && obj instanceof Boolean)
							flag = (Boolean) obj;
						if (flag)
							foFile = htmlDir
									+ "foxsl2020//NewAppApplication.xsl";
						else {
							foFile = htmlDir + "foxsl//NewAppApplication.xsl";
						}
					} 	
				}
				DocumentGenerationBO.fetchDataForApplicationPDF(ctx);
				logger.error("completed Gathering data for  Docu sign PDF for Policy "+ctx.get("PolicyKey"));
				Object objPolCovLW = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetPolicyCoverageFullDetails", ctx);
				ctx.put("coverage_freeform_2", objPolCovLW);
			}
			
			
			/*new DownloadPDF().fetchDataForXml(ctx);*/
			
		/*	foFile = SystemProperties.getInstance().getString("html.basedir")
					+ "foxsl//application.xsl";*/

			// foFile = SystemProperties.getInstance().getString("html.basedir")
			// + "foxsl//quoteletter.xsl";

			/*new DownloadPDF().fetchDataForXml(ctx);*/
			populateDataXml(ctx, xmlFile);
			
			logger.debug("Problem in generating quote letter for Quote Number : ");
					
			
			new XML2PDF().convertPOToPDF(foFile, xmlFile, out, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER), servletContext,ctx);
		} catch (Exception e) {
			logger
					.error("Problem in generating quote letter for Quote Number : "
							+ ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}	
	
	public void makeRenewPdfAssureSign(Context ctx, OutputStream out, String baseUrl,
			ServletContextURIResolver uriResolver,ServletContext servletContext) {

		String xmlFile = "";
		String foFile = "";
		boolean oldApp = false;
		try {

			xmlFile = SystemProperties.getInstance().getString("html.basedir")
					+ "data//quoteletter.xml";
			boolean ispdfNew = false;
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
			/*Object objpdfRule = RuleUtils.executeRule(ctx,"LawyersRule.IsRenewalPolicyNewApp");
			if (objpdfRule != null && objpdfRule instanceof Boolean) {
				ispdfNew = (Boolean) objpdfRule;
			}
			
			if(ispdfNew)
			{
				foFile = SystemProperties.getInstance().getString("html.basedir")
				+ "foxsl//NewAppRenewapplication.xsl";
				new DocumentGenerationBO().fetchDataForApplicationPDF(ctx);
			Object objPolCovLW = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetPolicyCoverageFullDetails", ctx);
			ctx.put("coverage_freeform_2", objPolCovLW);
			}
			
			else
			{
			foFile = SystemProperties.getInstance().getString("html.basedir")
					+ "foxsl//renewapplication.xsl";
			new DownloadPDF().fetchDataForXml(ctx);
			}*/
			
			
			
			Object obj = RuleUtils.executeRule(ctx, "LawyersRule.isOldAppFlow");	
			if(obj != null && obj instanceof Boolean )
				oldApp =(Boolean) obj;
			
			obj = RuleUtils.executeRule(ctx, "LawyersRule.setNewFilingConditions");
			boolean flag = false,flowFlag=false;
			
			xmlFile = htmlDir + "data//quoteletter.xml";
			if(oldApp){
				foFile = htmlDir + "foxsl//renewapplication.xsl";
				new DownloadPDF().fetchDataForXml(ctx);
			}else {
				
						obj = RuleUtils.executeRule(ctx, "LawyersRule.ApplicationDocumentRule");
					
					obj = RuleUtils.executeRule(ctx, "LawyersRule.checkNewApplicationDoc2021");
					if(obj != null && obj instanceof Boolean )
						flowFlag =(Boolean) obj;
					
					if(flowFlag)
					{
						
						obj = RuleUtils.executeRule(ctx, "LawyersRule.IsRenewalPolicyNewAppDoc2021");
						if(obj != null && obj instanceof Boolean )
							flowFlag =(Boolean) obj;
							if(flowFlag)
								foFile = htmlDir+ "foxsl2021/NewAppRenewapplication.xsl";
							
						obj = RuleUtils.executeRule(ctx, "LawyersRule.IsRenewalPolicyNewAppDoc2021FL");
						if(obj != null && obj instanceof Boolean )
							flowFlag =(Boolean) obj;
							if(flowFlag)
								foFile = htmlDir+ "foxsl2021/NewAppRenewapplication_FL.xsl";
							
						obj = RuleUtils.executeRule(ctx, "LawyersRule.IsRenewalPolicyNewAppDoc2021MT");
						if(obj != null && obj instanceof Boolean )
							flowFlag =(Boolean) obj;
							if(flowFlag)
								foFile = htmlDir+ "foxsl2021/NewAppRenewapplication_MT.xsl";
							
						obj = RuleUtils.executeRule(ctx, "LawyersRule.IsRenewalPolicyNewAppDoc2021KS");
						if(obj != null && obj instanceof Boolean )
							flowFlag =(Boolean) obj;
							if(flowFlag)
								foFile = htmlDir+ "foxsl2021/NewAppRenewapplication_KS.xsl";
				}
				else
				{
					
					
					foFile = htmlDir + "foxsl//NewAppRenewapplication.xsl";
					
					obj = RuleUtils.executeRule(ctx, "LawyersRule.IsRenewalPolicyNewAppDoc2020FL");
					if(obj != null && obj instanceof Boolean )
						flag =(Boolean) obj;
					if(flag)
						foFile = htmlDir + "foxsl2020//NewAppRenewapplication_FL.xsl";
					else {
						obj = RuleUtils.executeRule(ctx, "LawyersRule.IsRenewalPolicyNewAppDoc2020");
						if(obj != null && obj instanceof Boolean )
							flag =(Boolean) obj;
						if(flag)
							foFile = htmlDir + "foxsl2020//NewAppRenewapplication.xsl";
						else{
							foFile = htmlDir + "foxsl//NewAppRenewapplication.xsl";					
						}
						} 		
					}			
				DocumentGenerationBO.fetchDataForApplicationPDF(ctx);
				Object objPolCovLW = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetPolicyCoverageFullDetails", ctx);
				ctx.put("coverage_freeform_2", objPolCovLW);
			}
			
			
			
			
			// foFile = SystemProperties.getInstance().getString("html.basedir")
			// + "foxsl//quoteletter.xsl";

			/*new DownloadPDF().fetchDataForXml(ctx);*/
			populateDataXml(ctx, xmlFile);
			
			baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			new XML2PDF().convertPOToPDF(foFile, xmlFile, out,(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER), servletContext,ctx);
		} catch (Exception e) {
			logger
					.error("Problem in generating quote letter for Quote Number : "
							+ ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}	
	

	public void fetchDataForXml(Context ctx) {
		try {
			Object objPoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey(
					"SqlStmts.UserStatementpdfquotelettergetPriorPolicyInfo",
					ctx);
			ctx.put("policy_freeform_01", objPoilcy);

			
			String attorneyDetails = "";
    		List getAttorneyDetails = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoAttorneyDetails", ctx);
    		if(getAttorneyDetails != null && getAttorneyDetails.size() > 0 && !getAttorneyDetails.isEmpty()){
    			Map getAttorneyDetailsList = (Map)getAttorneyDetails.get(0);
    			attorneyDetails = (getAttorneyDetailsList.get("addAttorneys") == null ? "" : getAttorneyDetailsList.get("addAttorneys").toString());
    		}
    		
    		ctx.put("NumberOfLawyers", attorneyDetails);
    		
			Object objAddress = SqlResources.getSqlMapProcessor(ctx).findByKey(
					"SqlStmts.UserStatementpdfquotelettergetAddressdetails",
					ctx);
			ctx.put("address_freeform_01", objAddress);

			// Object objCoveragePoilcy =
			// SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquoteletterCoveragePolicy",
			// ctx);
			// ctx.put("policycoverage_freeform_01", objCoveragePoilcy);

			Object listQuoteOption = SqlResources
					.getSqlMapProcessor(ctx)
					.select(
							"SqlStmts.UserStatementpdfquotelettergetQuoteOptedList",
							ctx);
			ctx.put("quoteOpted_list_01", listQuoteOption);
			
			 listQuoteOption = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfquotelettergetQuoteOptedListNew",ctx);
				ctx.put("quoteOptedUpdated_list_01", listQuoteOption);


			Object objProLiabIns = SqlResources
					.getSqlMapProcessor(ctx)
					.findByKey(
							"SqlStmts.UserStatementpdfquotelettergetProfessionalLiabilityInsDetail",
							ctx);
			ctx
					.put("professionalliabilityinsdetail_freeform_01",
							objProLiabIns);

			Object objFooterTime = SqlResources.getSqlMapProcessor(ctx).select(
					"SqlStmts.UserStatementpdfquotelettergetQuoteFooterTime",
					ctx);
			ctx.put("time_freeform_01", objFooterTime);

			Object listStateForm = SqlResources.getSqlMapProcessor(ctx).select(
					"SqlStmts.UserStatementManualBoQueriesPolicyFormLW", ctx);
			ctx.put("stateform_list_01", listStateForm);
			
			Object objsubproducerFormData = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfquotelettergetSubproducerFormData", ctx);
			List li=(List) objsubproducerFormData;
			for(int i=0;i<li.size();i++){
				Map<String,String> mp=(Map) li.get(i);
				Set keys=mp.keySet();
				for (Iterator i2 = keys.iterator(); i2.hasNext(); ) {
				       String key = (String) i2.next();
				       String value = (String) mp.get(key);
				       if(key.equalsIgnoreCase("SPZip") || key.equalsIgnoreCase("CompleteAddress")){
				    	   if(value.length()<7){
				    		   String spZip=value.replace("-","");
				    		   ((Map)((List)objsubproducerFormData).get(0)).put(key,spZip);
				    		   ctx.put(key,spZip);
				    	   }
				       } else if(key.equalsIgnoreCase("ProducerName")){
				    	   if(((String)((Map)((List)objsubproducerFormData).get(0)).get("ProducerCode")).equalsIgnoreCase("P0000012")) {
				    		   ((Map)((List)objsubproducerFormData).get(0)).put(key,value.replace(" LLC", ""));
				    	   }
				    	   if(((String)((Map)((List)objsubproducerFormData).get(0)).get("ProducerCode")).equalsIgnoreCase("P0000005")) {
				    		   ((Map)((List)objsubproducerFormData).get(0)).put(key,value.replace(" Services", " for Lawyers"));
				    	   }
				       } else if(key.equalsIgnoreCase("LogoImagePath")){
				    	   DocumentGenerationBO dgbo=new DocumentGenerationBO();
				    	   dgbo.ImageDownld(value);
				       }
				}
			}
			ctx.put("subproducerFormData_freeform_01", objsubproducerFormData);
		} catch (Exception e) {
			logger.error("Problem in fetching data for Quote Number : "
					+ ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}

	public void populateDataXml(Context ctx, String xmlFile) {

		StringBuffer buffer = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

		try {
			// Data writer
			//logger.debug("Test : "+xmlFile);
			IOUtils.writeToFile(xmlFile, buffer.append(new RequestProcessor()
					.generateResponseXml(ctx,false)));
			//logger.debug("Test after:");
		} catch (Exception e) {
			logger.error("Problem in generating data xml for Quote Number : "
					+ ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}

	/* Below content is for downloading quick quote list */

	public void makeQuickQuoteListPdf(Context ctx, OutputStream out,
			String baseUrl, ServletContextURIResolver uriResolver) {

		String xmlFile = "";
		String foFile = "";

		try {

			xmlFile = SystemProperties.getInstance().getString("html.basedir")
					+ "data//quoteletter.xml";
			foFile = SystemProperties.getInstance().getString("html.basedir")
					+ "foxsl//quickquoteletter.xsl";

			// foFile = SystemProperties.getInstance().getString("html.basedir")
			// + "foxsl//quoteletter.xsl";

			fetchDataForQuickQuoteXml(ctx);
			populateDataXml(ctx, xmlFile);
			
			baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			new XML2PDF().convertPOToPDF(foFile, xmlFile, out, baseUrl,
					 (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER),ctx);
		} catch (Exception e) {
			logger
					.error("Problem in generating quote letter for Quote Number : "
							+ ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}

	public void fetchDataForQuickQuoteXml(Context ctx) {

		try {

			Object objPoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfQuickQuoteLettergetDataForPdfQuickQuote",	ctx);			
			ctx.put("policy_freeform_01", objPoilcy);

			Object objAddress = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquotelettergetAddressdetails",	ctx);
			ctx.put("address_freeform_01", objAddress);

			Object objFooterDate = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfquotelettergetQuoteFooterDate",ctx);
			ctx.put("date_freeform_01", objFooterDate);
			
			Object objPriorPolicyData = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfquotelettergetProfessionalLiabilityInsDetail",ctx);
			ctx.put("priorPolicyData_freeform_01", objPriorPolicyData);
			
			Object objFooterTime = SqlResources.getSqlMapProcessor(ctx).select(	"SqlStmts.UserStatementpdfquotelettergetQuoteFooterTime",
					ctx);
			ctx.put("time_freeform_01", objFooterTime);

			Object listQuoteOption = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetQuoteListSelected",ctx);
			//Object listQuoteOption = (List) SqlResources.getSqlMapProcessor(ctx).select("Policy.GetDatesDifferenceInDays_p",ctx);
			((Map)((List)listQuoteOption).get(0)).put("EstimatePremiumRange",ctx.get("EstimatePremiumRange"));
			ctx.put("quoteOpted_list_01", listQuoteOption);
			
			List li=(List) listQuoteOption;
			for(int i=0;i<li.size();i++){
				Map<String,String> mp=(Map) li.get(i);
				Set keys=mp.keySet();
				for (Iterator i2 = keys.iterator(); i2.hasNext(); ) {
			       String key = (String) i2.next();
			       if(key.equals("TotalPremiumFirstRange")){
			       String value =mp.get(key);
			       logger.debug("TotalPremiumFirstRange"+key+":"+value);
			       ctx.put("TotalPremiumFirstRange",value);
			    	   }
			       if(key.equals("EstimatePremiumRange")){
				       String value =mp.get(key);
				       logger.debug("EstimatePremiumRange"+key+":"+value);
				       ctx.put("EstimatePremiumRange",value);
				    	   }
			       }
			 }
			/*
			Object objProfessionalLiability = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquotelettergetFirmYear", ctx);
			ctx.put("firmyear_freeform_01", objProfessionalLiability);
			
			Object objGrossRevenueForFirm = SqlResources.getSqlMapProcessor(ctx).findByKey(
							"SqlStmts.UserStatementpdfquotelettergetLastRevenue",ctx);
			ctx.put("lastyearrevenue_freeform_01", objGrossRevenueForFirm);
			 */
			List aoplist = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOPFieldsLawyeresQuickQuote",
							ctx);
			
			ctx.put("aoplist", aoplist);
			Map covMap = new HashMap();
			if (aoplist != null)
			{
				Map map = new HashMap();
				int total = 0;
				for (int i = 0; i < aoplist.size(); i++)
				{
					map = (Map) aoplist.get(i);
					ctx.put("AOP_Percentage_" + map.get("AOPKey"), map.get("PercentageValue"));
					if (map.get("PercentageValue") != null	&& !map.get("PercentageValue").equals("")) {
						total = total + Integer.parseInt(map.get("PercentageValue").toString());
						ctx.put("AOP_PercentageValue_" + i, map.get("PercentageValue").toString());
					}
					ctx.put("AOPCommentDesc_" + map.get("AOPKey"), map.get("AOPCommentDesc"));
				}
				ctx.put("aop_total", total);
			}
			
			

				List objList = SqlResources.getSqlMapProcessor(ctx).select(
						"ClaimAgeLW.findAllByPartialKey", ctx);

				if (objList != null && objList.size() > 0) {
					for (int i = 0; i < objList.size(); i++) {
						Map objMap = (Map) objList.get(i);
						ctx.put("PartTime_" + (i + 1), objMap.get("PartTime"));
						ctx.put("NumberOfYearsWithFirm_" + (i + 1), objMap.get("Year"));

					}
				}
			
			
		} catch (Exception e) {
			logger.error("Problem in fetching data for  quick quote insured : " + " " + e.getMessage());
		}
	}

	/**
	 * This code is is during policy form attachment
	 */
	
	public static Map populateFooter(Object listPolicyFormFooter) throws Exception {
		List listFormFooter = new ArrayList();
				
		if (listPolicyFormFooter != null && listPolicyFormFooter instanceof List)
			listFormFooter = (List) listPolicyFormFooter;
		
		Map mapFooter = new HashMap();
		for (int i = 0; i < listFormFooter.size(); i++) {
		
			Map map = (Map) listFormFooter.get(i);
			if (map.get("FormFooter") == null)
				continue;
		
			String formID = map.get("FormFooter").toString();
		
			if ("LPL 100-106-2010".equals(formID))
				mapFooter.put("LPL1062010", formID);
			else if (formID.indexOf("(") < 0)
				mapFooter.put(formID.replace("-", "").replace(" ", "").replace("#", ""), formID);
			else if (formID.indexOf("(") > 0)
				mapFooter.put(formID.replace("-", "").replace(" ", "").replace("(", "").replace("/", "").replace(")", "").replace("#",""), formID);
		}
		
		logger.debug("generating footer for Quote  : ");
		return mapFooter;
	}
	
	public void makeNDPdf(Context ctx, OutputStream out, String baseUrl, ServletContextURIResolver uriResolver) {

		String xmlFile = "";
		String foFile = "";

		try {

			xmlFile = SystemProperties.getInstance().getString("html.basedir") + "data//quoteletter.xml";
			foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl//foxslold//group3//MO_42_001_06_15.xsl";
			
			baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			new XML2PDF().convertPOToPDF(foFile, xmlFile, out,baseUrl,(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER),ctx);
		} catch (Exception e) {
			logger.error("Problem in generating Special Notice for Quote Number : " + ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}
	
	public void makeSpecialNoticePdf(Context ctx, OutputStream out, String baseUrl, ServletContextURIResolver uriResolver) {

		String xmlFile = "";
		String foFile = "";

		try {

			xmlFile = SystemProperties.getInstance().getString("html.basedir") + "data//quoteletter.xml";
			foFile = SystemProperties.getInstance().getString("html.basedir");
			if("AR".equals(ctx.get("StateCode").toString()))
				foFile = foFile + "foxsl2017//foxslnew//notices//notice_AR.xsl";
			else if("MT".equals(ctx.get("StateCode").toString()))
				foFile = foFile + "foxsl2017//foxslnew//notices//notice_MT.xsl";
			else if("NM".equals(ctx.get("StateCode").toString()))
				foFile = foFile + "foxsl2017//foxslnew//notices//notice_NM.xsl";
		/*	else if("NM".equals(ctx.get("StateCode").toString()) && "NM2".equals(ctx.get("NMRatingVersion").toString()) )
				foFile = foFile + "foxsl2017//foxslnew//notices//group8//Notice_0020419_NM.xsl";
			*/
			else if("NM".equals(ctx.get("StateCode").toString()))
				foFile = foFile + "foxsl2017//foxslnew//notices//notice_NM.xsl";
			else if("ND".equals(ctx.get("StateCode").toString()))
				foFile = foFile + "foxsl2017//foxslnew//notices//notice_ND.xsl";
			else if("WY".equals(ctx.get("StateCode").toString()))
				foFile = foFile + "foxsl2017//foxslnew//notices//notice_WY.xsl";		
			
			baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			new XML2PDF().convertPOToPDF(foFile, xmlFile, out,baseUrl,(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER),ctx);
		} catch (Exception e) {
			logger.error("Problem in generating Special Notice for Quote Number : " + ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}
	
	public void makeOffRiskPDF(Context ctx, OutputStream out, String baseUrl,
			ServletContextURIResolver uriResolver) {

		String xmlFile = "";
		String foFile = "";

		try {

			xmlFile = SystemProperties.getInstance().getString("html.basedir")
					+ "data//quoteletter.xml";
			foFile = SystemProperties.getInstance().getString("html.basedir")
					+ "foxsl//OffRiskLetterLawyers.xsl";

			fetchDataForXmlOffRisk(ctx);
			populateDataXml(ctx, xmlFile);
			
			baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			new XML2PDF().convertPOToPDF(foFile, xmlFile, out, baseUrl,
					(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER),ctx);
		} catch (Exception e) {
			logger
					.error("Problem in generating quote letter for Quote Number : "
							+ ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}
	
	public void fetchDataForXmlOffRisk(Context ctx) {
		try {
			Object objPoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey(
					"Policy.FetchAllPolicyDataFromPolicyKey",
					ctx);
			ctx.put("policy_freeform_01", objPoilcy);
			
		} catch (Exception e) {
			logger.error("Problem in fetching data for Quote Number : "
					+ ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}
	
	public void makeApplicationPDF(Context ctx, OutputStream out, String baseUrl,
			ServletContextURIResolver uriResolver) {

		String xmlFile = "";
		String foFile = "";

		try {
			RuleUtils.executeRule(ctx, "LawyersRule.newFilingConditionForApp");
			
			xmlFile = SystemProperties.getInstance().getString("html.basedir")
					+ "data//quoteletter.xml";
			if((Boolean) RuleUtils.executeRule(ctx, "LawyersRule.IsRenewalPolicyOldApp"))
				foFile = SystemProperties.getInstance().getString("html.basedir")+ "foxsl/renewapplication.xsl";
			if((Boolean) RuleUtils.executeRule(ctx, "LawyersRule.IsNotRenewalPolicyOldApp"))
				foFile = SystemProperties.getInstance().getString("html.basedir")+ "foxsl/applicationn.xsl";
			if((Boolean) RuleUtils.executeRule(ctx, "LawyersRule.IsNewPolicyNewAppDoc2017"))
				foFile = SystemProperties.getInstance().getString("html.basedir")+ "foxsl/NewAppApplication.xsl";
			if((Boolean) RuleUtils.executeRule(ctx, "LawyersRule.IsRenewalPolicyNewAppDoc2017"))
				foFile = SystemProperties.getInstance().getString("html.basedir")+ "foxsl/NewAppRenewapplicationAppDownload.xsl";
			if((Boolean) RuleUtils.executeRule(ctx, "LawyersRule.IsNewPolicyNewAppDoc2020"))
				foFile = SystemProperties.getInstance().getString("html.basedir")+ "foxsl2020/NewAppApplication.xsl";
			if((Boolean) RuleUtils.executeRule(ctx, "LawyersRule.IsRenewalPolicyNewAppDoc2020"))
				foFile = SystemProperties.getInstance().getString("html.basedir")+ "foxsl2020/NewAppRenewapplicationAppDownload.xsl";
			if((Boolean) RuleUtils.executeRule(ctx, "LawyersRule.IsNewPolicyNewAppDoc2020FL"))
				foFile = SystemProperties.getInstance().getString("html.basedir")+ "foxsl2020/NewAppRenewapplicationAppDownLoad_FL.xsl";
			fetchDataForXmlApplication(ctx);
			populateDataXml(ctx, xmlFile);
			
			baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			new XML2PDF().convertPOToPDF(foFile, xmlFile, out, baseUrl,
					(ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER),ctx);
		} catch (Exception e) {
			logger
					.error("Problem in generating quote letter for Quote Number : "
							+ ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}
	
	
	public void fetchDataForXmlApplication(Context ctx) {
		try {
			DocumentGenerationBO.fetchDataForApplicationPDF(ctx);


		} catch (Exception e) {
			logger.error("Problem in fetching data for Quote Number : "
					+ ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}
	
}
