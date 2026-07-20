package com.fop;

import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.fop.servlet.ServletContextURIResolver;

import com.formgenerator.DownloadForm;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.processor.RequestProcessor;
import com.util.Context;
import com.util.DocumentGenerationBO;
import com.util.HtmlConstants;
import com.util.IOUtils;
import com.util.InetLogger;
import com.util.SystemProperties;

public class DownloadPDF {
private static InetLogger logger = InetLogger.getInetLogger(DownloadPDF.class);
	
	public void testRenewPdf(Context ctx, String out) {
		String xmlFile = "";
		String foFile = "";		
		
		try{			
			boolean oldApp = false;
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
			List appCreatedDate = (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAppCretaedDate",ctx);
			ctx.putAll((Map) appCreatedDate.get(0));
		
			Object obj = RuleUtils.executeRule(ctx, "LawyersRule.isOldAppFlow");	
			if(obj != null && obj instanceof Boolean )
				oldApp =(Boolean) obj;
			
			xmlFile = htmlDir + "data//quoteletter.xml";
			if(oldApp){
				foFile = htmlDir + "foxsl//renewapplication.xsl";
				fetchDataForXml(ctx);
			}else {
				foFile = htmlDir + "foxsl//NewAppRenewapplication.xsl";
				DocumentGenerationBO.fetchDataForApplicationPDF(ctx);
			}
			
			new XML2PDF().convertPOToPDF(foFile, new StringBuffer(new DownloadForm().generateDataXml(ctx)), out);
		}
		catch(Exception e){
			logger.error("Problem in generating quote letter for Quote Number : " +ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}
	public void testmakePdf(Context ctx, String out) {
		String xmlFile = "";
		String foFile = "";		
		
		try{
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
			boolean oldApp = false;
			List appCreatedDate = (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAppCretaedDate",ctx);
			ctx.putAll((Map) appCreatedDate.get(0));
			Object obj = RuleUtils.executeRule(ctx, "LawyersRule.isOldAppFlow");	
			if(obj != null && obj instanceof Boolean )
				oldApp =(Boolean) obj;
			
			xmlFile = htmlDir + "data//quoteletter.xml";
			//isNewAppFlow
			if(oldApp){
				foFile = htmlDir + "foxsl//application.xsl";
				fetchDataForXml(ctx);
			}else{
				foFile = htmlDir + "foxsl//NewAppApplication.xsl";
				DocumentGenerationBO.fetchDataForApplicationPDF(ctx);
			}
			
			new XML2PDF().convertPOToPDF(foFile, new StringBuffer(new DownloadForm().generateDataXml(ctx)), out);			
		}
		catch(Exception e){
			logger.error("Problem in generating quote letter for Quote Number : " +ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}

	public void makePdf(Context ctx, HttpServletRequest req, HttpServletResponse res, ServletContextURIResolver uriResolver, ServletContext servletContext) {
		String xmlFile = "";
		String foFile = "";
		try{
			//String htmlDir = "D:\\LawyersIns_workspace\\LawyersIns\\web\\";//SystemProperties.getInstance().getString("html.basedir");
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
			
			xmlFile = htmlDir + "data//quoteletter.xml";
			foFile = htmlDir + "foxsl//application.xsl";
			
			fetchDataForXml(ctx);
			populateDataXml(ctx, xmlFile);
			new XML2PDF().convertPOToPDFApplication(foFile, xmlFile, ctx, req, res, uriResolver, servletContext);
		}
		catch(Exception e){
			logger.error("Problem in generating quote letter for Quote Number : " +ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}
	
	public void makeRenewPdf(Context ctx, HttpServletRequest req, HttpServletResponse res, ServletContextURIResolver uriResolver, ServletContext servletContext) {
		String xmlFile = "";
		String foFile = "";
		try{
			//String htmlDir = "D:\\LawyersIns_workspace\\LawyersIns\\web\\";//SystemProperties.getInstance().getString("html.basedir");
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
			
			xmlFile = htmlDir + "data//quoteletter.xml";
			foFile = htmlDir + "foxsl//renewapplication.xsl";
			
			fetchDataForXml(ctx);
			populateDataXml(ctx, xmlFile);
			new XML2PDF().convertPOToPDFApplication(foFile, xmlFile, ctx, req, res, uriResolver, servletContext);
		}
		catch(Exception e){
			logger.error("Problem in generating quote letter for Quote Number : " +ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}
	
	public void makePdf(Context ctx, OutputStream out, String baseUrl, ServletContextURIResolver uriResolver) {
		String xmlFile = "";
		String foFile = "";		
		
		try{
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
			boolean oldApp = false,flowFlag=false;
			List appCreatedDate = (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAppCretaedDate",ctx);
			ctx.putAll((Map) appCreatedDate.get(0));
			Object obj = RuleUtils.executeRule(ctx, "LawyersRule.isOldAppFlow");	
			if(obj != null && obj instanceof Boolean )
				oldApp =(Boolean) obj;
			
			obj = RuleUtils.executeRule(ctx, "LawyersRule.setNewFilingConditions");
			boolean flag = false;
			
			xmlFile = htmlDir + "data//quoteletter.xml";
			//isNewAppFlow
			if(oldApp){
				foFile = htmlDir + "foxsl//application.xsl";
				fetchDataForXml(ctx);
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
				
			}
			
			populateDataXml(ctx, xmlFile);	
			if(ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT) != null)
				baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			
			new XML2PDF().convertPOToPDF(foFile, xmlFile, out, baseUrl, uriResolver,ctx);			
		}
		catch(Exception e){
			logger.error("Problem in generating quote letter for Quote Number : " +ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}
	
	public void makeRenewPdf(Context ctx, OutputStream out, String baseUrl, ServletContextURIResolver uriResolver) {
		String xmlFile = "";
		String foFile = "";		
		
		try{			
			boolean oldApp = false,flowFlag=false;
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
			List appCreatedDate = (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAppCretaedDate",ctx);
			ctx.putAll((Map) appCreatedDate.get(0));
		
			Object obj = RuleUtils.executeRule(ctx, "LawyersRule.isOldAppFlow");	
			if(obj != null && obj instanceof Boolean )
				oldApp =(Boolean) obj;
			
			obj = RuleUtils.executeRule(ctx, "LawyersRule.setNewFilingConditions");
			boolean flag = false;
			
			xmlFile = htmlDir + "data//quoteletter.xml";
			if(oldApp){
				foFile = htmlDir + "foxsl//renewapplication.xsl";
				fetchDataForXml(ctx);
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
				//DocumentGenerationBO.fetchDataForApplicationPDF(ctx);
			
			}
			
			populateDataXml(ctx, xmlFile);
			if(ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT) != null)
				baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			
			new XML2PDF().convertPOToPDF(foFile, xmlFile, out, baseUrl, uriResolver,ctx);
		}
		catch(Exception e){
			logger.error("Problem in generating quote letter for Quote Number : " +ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}
	
	public void makePdfForESign(Context ctx, OutputStream out, String baseUrl, ServletContextURIResolver uriResolver) {
		
		String xmlFile = "";
		String foFile = "";		
		
		try{			
			
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
			
			xmlFile = htmlDir + "data//quoteletter.xml";
			foFile = htmlDir + "foxsl//application.xsl";
			//foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl//quoteletter.xsl";
			
			fetchDataForXml(ctx);
			populateDataXml(ctx, xmlFile);
			baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			new XML2PDF().convertPOToPDF(foFile, xmlFile, out, baseUrl, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER),ctx);			
		}
		catch(Exception e){
			logger.error("Problem in generating quote letter for Quote Number : " +ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}
	
	public void makePdfQuoteLetter(Context ctx, HttpServletRequest req, HttpServletResponse res, ServletContextURIResolver uriResolver, ServletContext servletContext) {
		String xmlFile = "";
		String foFile = "";
		try{
			//String htmlDir = "D:\\LawyersIns_workspace\\LawyersIns\\web\\";//SystemProperties.getInstance().getString("html.basedir");
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
			
			xmlFile = htmlDir + "data//quoteletter.xml";
			foFile = htmlDir + "foxsl//quoteletter.xsl";
			
			fetchDataForXmlQuoteLetter(ctx);
			populateDataXml(ctx, xmlFile);
		
			new XML2PDF().convertPOToPDFQuoteLetter(foFile, xmlFile, ctx, req, res, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER), servletContext);
		}
		catch(Exception e){
			logger.error("Problem in generating quote letter for Quote Number : " +ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}
	/*
	public void makePdf(Context ctx, OutputStream out, String baseUrl, ServletContextURIResolver uriResolver) {
		String xmlFile = "";
		String foFile = "";		
		try{			
			xmlFile = SystemProperties.getInstance().getString("html.basedir") + "data//quoteletter.xml";
			foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl//quoteletter.xsl";
			
			fetchDataForXml(ctx);
			populateDataXml(ctx, xmlFile);
			new XML2PDF().convertPOToPDF(foFile, xmlFile, out, baseUrl, uriResolver);			
		}
		catch(Exception e){
			logger.error("Problem in generating quote letter for Quote Number : " +ctx.get("QuoteNumber"));
		}
	}
*/
	public void fetchDataForXml(Context ctx) {		
		try{
			Object objPoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetPriorPolicyInfo", ctx);
			ctx.put("policy_freeform_01", objPoilcy);
			
//			String s = null;
//			s.toLowerCase();
			
			Object objFirmLW = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetFirmLWFullDetails", ctx);
			if(objFirmLW != null && objFirmLW instanceof Map){
				Map map = (HashMap)objFirmLW;
				if(map.get("PerOfGrossBillingsUnderContract") != null){
					String PerOfGrossBillingsUnderContract = map.get("PerOfGrossBillingsUnderContract").toString();
					PerOfGrossBillingsUnderContract = setPercentage(PerOfGrossBillingsUnderContract);
					map.put("PerOfGrossBillingsUnderContract", PerOfGrossBillingsUnderContract);
				}else{
					map.put("PerOfGrossBillingsUnderContract", "0");
				}
				if(map.get("PercentofApplAcctRcbl") != null){
					String PercentofApplAcctRcbl = map.get("PercentofApplAcctRcbl").toString();
					PercentofApplAcctRcbl = setPercentage(PercentofApplAcctRcbl);
					map.put("PercentofApplAcctRcbl", PercentofApplAcctRcbl);
				}else{
					map.put("PercentofApplAcctRcbl", "0");
				}
			}			
			ctx.put("firm_freeform_01", objFirmLW);
			
			Object objAttFirmLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetAttorneyDetailsLWFullDetails", ctx);
			ctx.put("attorneys_firm_list_01", objAttFirmLW);
			
			Object objPrimLocFirmLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetFirmPracticeLocationLWFullDetails", ctx);
			if(objPrimLocFirmLW != null && objPrimLocFirmLW instanceof List){
				List primLocList = (List)objPrimLocFirmLW;
				for(int i=0; i<primLocList.size(); i++){
					Map map = (HashMap)primLocList.get(i);
					if(map.get("NumberOfBillabeHours") != null){
						String NumberOfBillabeHours = map.get("NumberOfBillabeHours").toString();
						NumberOfBillabeHours = setPercentage(NumberOfBillabeHours);
						map.put("NumberOfBillabeHours", NumberOfBillabeHours);
					}
				}
			}
			ctx.put("primaryloc_firm_list_01", objPrimLocFirmLW);
			
			Object objGrossRevFirmLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetGrossRevenueForFirmLWFullDetails", ctx);
			if(objGrossRevFirmLW != null && objGrossRevFirmLW instanceof List){
				List objGrossRevFirmLWList = (List)objGrossRevFirmLW;
				for(int i=0; i<objGrossRevFirmLWList.size(); i++){
					Map map = (HashMap)objGrossRevFirmLWList.get(i);
					ctx.put("YearEndDate_"+i, map.get("YearEndDate"));
					ctx.put("Amount_"+i, map.get("Amount"));
				}
			}
			
			Object objAopFirmLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetAreaOfPracticeLWFullDetails", ctx);
			if(objAopFirmLW != null && objAopFirmLW instanceof List){
				List objAopFirmLWList = (List)objAopFirmLW;
				int total = 0;
				for(int i=0; i<objAopFirmLWList.size(); i++){
					Map map = (Map)objAopFirmLWList.get(i);
					if(map.get("PercentageValue") != null && !map.get("PercentageValue").equals("")){
						total = total + Integer.parseInt(map.get("PercentageValue").toString());
						ctx.put("AOP_PercentageValue_"+i, map.get("PercentageValue").toString());
					}
					ctx.put("AOP_CommentDesc_"+i, map.get("AOPCommentDesc"));
				}
				/*float totalSize = objAopFirmLWList.size();
				float size = totalSize/2;
				int firsthalf = Math.round(size);
				size = totalSize - firsthalf;
				int secondhalf = Math.round(size);
				List list1 = new ArrayList();
				List list2 = new ArrayList();
				int total = 0;
				for(int i=0; i<firsthalf; i++){
					Map map = (Map)objAopFirmLWList.get(i);
					if(map.get("PercentageValue") != null && !map.get("PercentageValue").equals("")){
						total = total + Integer.parseInt(map.get("PercentageValue").toString());
					}
					list1.add(map);
				}
				for(int i=secondhalf+1; i<objAopFirmLWList.size(); i++){
					Map map = (Map)objAopFirmLWList.get(i);
					if(map.get("PercentageValue") != null && !map.get("PercentageValue").equals("")){
						total = total + Integer.parseInt(map.get("PercentageValue").toString());
						
					}
					list2.add(map);
				}
				ctx.put("aop_aop_list_01", list1);
				ctx.put("aop_aop_list_02", list2);*/
				ctx.put("aop_total", total);
			}
			if(objAopFirmLW == null || (objAopFirmLW != null && objAopFirmLW instanceof List && ((List)objAopFirmLW).size()<=0)){
				objAopFirmLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetAOPLWFullDetails", ctx);
				List objAopFirmLWList = (List)objAopFirmLW;
				for(int i=0; i<objAopFirmLWList.size(); i++){
					ctx.put("AOP_PercentageValue_"+i, "0");
					ctx.put("AOP_CommentDesc_"+i, "");
				}
				/*
				float totalSize = objAopFirmLWList.size();
				float size = totalSize/2;
				int firsthalf = Math.round(size);
				size = totalSize - firsthalf;
				int secondhalf = Math.round(size);
				List list1 = new ArrayList();
				List list2 = new ArrayList();
				for(int i=0; i<firsthalf; i++){
					Map map = (Map)objAopFirmLWList.get(i);
					map.put("PercentageValue", "0");
					list1.add(map);
				}
				for(int i=secondhalf+1; i<objAopFirmLWList.size(); i++){
					Map map = (Map)objAopFirmLWList.get(i);
					map.put("PercentageValue", "0");
					list2.add(map);
				}
				ctx.put("aop_aop_list_01", list1);
				ctx.put("aop_aop_list_02", list2);*/
				ctx.put("aop_total", "0");
			}
			
			Object objFirmAnnPracticeLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetFirmAnnualRevenueLWFullDetails", ctx);
			if(objFirmAnnPracticeLW != null && objFirmAnnPracticeLW instanceof List){
				List objFirmAnnPracticeLWList = (List)objFirmAnnPracticeLW;
				for(int i=0; i<objFirmAnnPracticeLWList.size(); i++){
					Map map = (HashMap)objFirmAnnPracticeLWList.get(i);
					if(map.get("PercentageOfAnnlRevenue") != null){
						String PercentageOfAnnlRevenue = map.get("PercentageOfAnnlRevenue").toString();
						PercentageOfAnnlRevenue = setPercentage(PercentageOfAnnlRevenue);
						map.put("PercentageOfAnnlRevenue", PercentageOfAnnlRevenue);
					}
				}
			}
			ctx.put("firmannual_practice_list_01", objFirmAnnPracticeLW);
			
			Object objPersonnelPracticeLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetFirmPersonnelServedLWFullDetails", ctx);
			if(objPersonnelPracticeLW != null && objPersonnelPracticeLW instanceof List){
				List objPersonnelPracticeLWList = (List)objPersonnelPracticeLW;
				for(int i=0; i<objPersonnelPracticeLWList.size(); i++){
					Map map = (HashMap)objPersonnelPracticeLWList.get(i);
					if(map.get("PercentEquityInterest") != null){
						String PercentEquityInterest = map.get("PercentEquityInterest").toString();
						PercentEquityInterest = setPercentage(PercentEquityInterest);
						map.put("PercentEquityInterest", PercentEquityInterest);
					}
				}
			}
			ctx.put("personnel_practice_list_01", objPersonnelPracticeLW);
			
			Object objFirmLawsuitPracticeLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetFirmInitLawsuitsLWFullDetails", ctx);
			ctx.put("firmlawsuit_practice_list_01", objFirmLawsuitPracticeLW);
			
			Object objProfLiabCovLW = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetProfessionalLiabilityInsDetailLWFullDetails", ctx);
			ctx.put("coverage_freeform_1", objProfLiabCovLW);
			
			Object objPolCovLW = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetPolicyCoverageFullDetails", ctx);
			ctx.put("coverage_freeform_2", objPolCovLW);
			
			Object objRealEstateLW = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetRealEstateSuppLWFullDetails", ctx);
			
			ctx.put("realEstate_freeform_01", objRealEstateLW);
			
			Object objAopRELW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementAOPSupplementsQueriespopulateAOPREResidential", ctx);
			if(objAopRELW != null && objAopRELW instanceof List){
				List objAopRELWList = (List)objAopRELW;
				int total = 0;
				for(int i=0; i<objAopRELWList.size(); i++){
					Map map = (Map)objAopRELWList.get(i);
					if(map.get("PercentageValue") != null && !map.get("PercentageValue").equals("")){
						total = total + Integer.parseInt(map.get("PercentageValue").toString());
						ctx.put("AOPRE_PercentageValue_"+map.get("AOPREKey"), map.get("PercentageValue").toString());
					}else{
						ctx.put("AOPRE_PercentageValue_"+map.get("AOPREKey"), "0");
					}
					ctx.put("AOPRE_CommentDesc_"+map.get("AOPREKey"), map.get("AOPRECommentDesc"));
				}
				
				ctx.put("aopre_total", total);
			}
			
			
			Object objAopRECLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementAOPSupplementsQueriespopulateAOPRECommercial", ctx);
			if(objAopRECLW != null && objAopRECLW instanceof List){
				List objAopRECLWList = (List)objAopRECLW;
				int total = 0;
				for(int i=0; i<objAopRECLWList.size(); i++){
					Map map = (Map)objAopRECLWList.get(i);
					if(map.get("PercentageValue") != null && !map.get("PercentageValue").equals("")){
						total = total + Integer.parseInt(map.get("PercentageValue").toString());
						ctx.put("AOPRE_PercentageValue_"+map.get("AOPREKey"), map.get("PercentageValue").toString());
					}else{
						ctx.put("AOPRE_PercentageValue_"+map.get("AOPREKey"), "0");
					}
					ctx.put("AOPRE_CommentDesc_"+map.get("AOPREKey"), map.get("AOPRECommentDesc"));
				}
				
				ctx.put("aoprec_total", total);
			}
			
			
			
			
			if(objAopRELW == null || (objAopRELW != null && objAopRELW instanceof List && ((List)objAopRELW).size()<=0)){
				objAopRELW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementAOPSupplementsQueriespopulateAOPREResidential", ctx);
				List objAopRELWList = (List)objAopRELW;
				for(int i=0; i<objAopRELWList.size(); i++){
					Map map = (Map)objAopRELWList.get(i);
					ctx.put("AOPRE_PercentageValue_"+map.get("AOPREKey"), "0");
				}
				ctx.put("aopre_total", "0");
				
			}
			
			/*
			Object objEnvLW = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetEnvironmentalSuppLWFullDetails", ctx);
			ctx.put("environmental_freeform_01", objEnvLW);
			
			Object objAttEnvLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetAttorneyEnvWorkLWFullDetails", ctx);
			if(objAttEnvLW != null && objAttEnvLW instanceof List){
				List objAttEnvLWList = (List)objAttEnvLW;
				for(int i=0; i<objAttEnvLWList.size(); i++){
					Map map = (HashMap)objAttEnvLWList.get(i);
					if(map.get("PercentOfTimeDevoted") != null){
						String PercentOfTimeDevoted = map.get("PercentOfTimeDevoted").toString();
						PercentOfTimeDevoted = setPercentage(PercentOfTimeDevoted);
						map.put("PercentOfTimeDevoted", PercentOfTimeDevoted);
					}
				}
			}
			ctx.put("attorneys_environmental_list_01", objAttEnvLW);
			
			Object objClientEnvLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetFirmEnvClientsLWFullDetails", ctx);
			ctx.put("clients_environmental_list_01", objClientEnvLW);
			
			Object objContrEnvLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetContractorEnvLWFullDetails", ctx);
			if(objContrEnvLW != null && objContrEnvLW instanceof List){
				List objContrEnvLWList = (List)objContrEnvLW;
				for(int i=0; i<objContrEnvLWList.size(); i++){
					Map map = (HashMap)objContrEnvLWList.get(i);
					if(map.get("PercentOfEnvGBillings") != null){
						String PercentOfEnvGBillings = map.get("PercentOfEnvGBillings").toString();
						PercentOfEnvGBillings = setPercentage(PercentOfEnvGBillings);
						map.put("PercentOfEnvGBillings", PercentOfEnvGBillings);
					}
				}
			}
			ctx.put("contractors_environmental_list_01", objContrEnvLW);
			
			Object objTitleLW = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetTitleSuppLWFullDetails", ctx);
			if(objTitleLW != null && objTitleLW instanceof Map){
				Map map = (HashMap)objTitleLW;
				if(map.get("PercentageOfEquityInt") != null){
					String PercentageOfEquityInt = map.get("PercentageOfEquityInt").toString();
					PercentageOfEquityInt = setPercentage(PercentageOfEquityInt);
					map.put("PercentageOfEquityInt", PercentageOfEquityInt);
				}else{
					map.put("PercentageOfEquityInt", "0");
				}
				if(map.get("PercentOfTitleFromResi") != null){
					String PercentOfTitleFromResi = map.get("PercentOfTitleFromResi").toString();
					PercentOfTitleFromResi = setPercentage(PercentOfTitleFromResi);
					map.put("PercentOfTitleFromResi", PercentOfTitleFromResi);
				}else{
					map.put("PercentOfTitleFromResi", "0");
				}
				if(map.get("PercentOfTitleFromComm") != null){
					String PercentOfTitleFromComm = map.get("PercentOfTitleFromComm").toString();
					PercentOfTitleFromComm = setPercentage(PercentOfTitleFromComm);
					map.put("PercentOfTitleFromComm", PercentOfTitleFromComm);
				}else{
					map.put("PercentOfTitleFromComm", "0");
				}
				if(map.get("PercentOfTitleFromAgri") != null){
					String PercentOfTitleFromAgri = map.get("PercentOfTitleFromAgri").toString();
					PercentOfTitleFromAgri = setPercentage(PercentOfTitleFromAgri);
					map.put("PercentOfTitleFromAgri", PercentOfTitleFromAgri);
				}else{
					map.put("PercentOfTitleFromAgri", "0");
				}
				if(map.get("PercentOfTitleFromOther") != null){
					String PercentOfTitleFromOther = map.get("PercentOfTitleFromOther").toString();
					PercentOfTitleFromOther = setPercentage(PercentOfTitleFromOther);
					map.put("PercentOfTitleFromOther", PercentOfTitleFromOther);
				}else{
					map.put("PercentOfTitleFromOther", "0");
				}
			}
			ctx.put("title_freeform_01", objTitleLW);
			*/
			
			
			Object objPlaintiffLW = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetPlantiffSuppLWFullDetails", ctx);
			if(objPlaintiffLW != null && objPlaintiffLW instanceof Map){
				Map map = (HashMap)objPlaintiffLW;
				if(map.get("PerOfCasesBeforeTrail") != null){
					String PerOfCasesBeforeTrail = map.get("PerOfCasesBeforeTrail").toString();
					PerOfCasesBeforeTrail = setPercentage(PerOfCasesBeforeTrail);
					map.put("PerOfCasesBeforeTrail", PerOfCasesBeforeTrail);
				}else{
					map.put("PerOfCasesBeforeTrail", "0");
				}
				if(map.get("PerOfCasesTriedToConclusion") != null){
					String PerOfCasesTriedToConclusion = map.get("PerOfCasesTriedToConclusion").toString();
					PerOfCasesTriedToConclusion = setPercentage(PerOfCasesTriedToConclusion);
					map.put("PerOfCasesTriedToConclusion", PerOfCasesTriedToConclusion);
				}else{
					map.put("PerOfCasesTriedToConclusion", "0");
				}
				if(map.get("PerOfCasesReferToApplicant") != null){
					String PerOfCasesReferToApplicant = map.get("PerOfCasesReferToApplicant").toString();
					PerOfCasesReferToApplicant = setPercentage(PerOfCasesReferToApplicant);
					map.put("PerOfCasesReferToApplicant", PerOfCasesReferToApplicant);
				}else{
					map.put("PerOfCasesReferToApplicant", "0");
				}
			}
			ctx.put("plaintiff_freeform_01", objPlaintiffLW);
			
			
			Object objAolPlaintiffLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetAreaOfLitigationForFirmLWFullDetails", ctx);
			if(objAolPlaintiffLW != null && objAolPlaintiffLW instanceof List){
				List objAolPlaintiffLWList = (List)objAolPlaintiffLW;
				long averageCaseSize = 0;
				long largestCaseSize = 0;
				int percentageOfRevenue = 0;
				Map objMap = new HashMap();
				for(int i=0; i<objAolPlaintiffLWList.size(); i++){
					Map map = (HashMap)objAolPlaintiffLWList.get(i);
					if(map.get("PercentageOfRevenue") != null && !map.get("PercentageOfRevenue").equals("")){
						percentageOfRevenue = percentageOfRevenue + Integer.parseInt(map.get("PercentageOfRevenue").toString());
					}else{
						map.put("PercentageOfRevenue", "0");
					}
					if(map.get("AverageCaseSize") != null && !map.get("AverageCaseSize").equals("")){
						Object AverageCaseSize = removeAmountFormat(map.get("AverageCaseSize").toString());
						averageCaseSize = averageCaseSize + Long.parseLong(AverageCaseSize.toString());
					}else{
						map.put("AverageCaseSize", "0");
					}
					if(map.get("LargestCaseSize") != null && !map.get("LargestCaseSize").equals("")){
						Object LargestCaseSize = removeAmountFormat(map.get("LargestCaseSize").toString());
						largestCaseSize = largestCaseSize + Long.parseLong(LargestCaseSize.toString());
					}else{
						map.put("LargestCaseSize", "0");
					}
					
					objMap.put("PercentageOfRevenue_"+(i+1), map.get("PercentageOfRevenue") != null ? map.get("PercentageOfRevenue") : 0);
					objMap.put("AverageCaseSize_"+(i+1), map.get("AverageCaseSize") != null ? map.get("AverageCaseSize") : 0);
					objMap.put("LargestCaseSize_"+(i+1), map.get("LargestCaseSize") != null ? map.get("LargestCaseSize") : 0);
					objMap.put("CommentDesc_"+(i+1), map.get("AOLCommentDesc") != null ? map.get("AOLCommentDesc") : "");
				}
				ctx.put("aol_percentageOfRevenue", percentageOfRevenue);
				ctx.put("aol_averageCaseSize", applyAmountFormat(averageCaseSize));
				ctx.put("aol_largestCaseSize", applyAmountFormat(largestCaseSize));
				//ctx.put("aol_plaintiff_list_01", objAolPlaintiffLWList);
				ctx.put("aol_plaintiffMap", objMap);
			}
			
			
			
			/*
			Object objAolPlaintiffLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetAreaOfLitigationForFirmLWFullDetails", ctx);
			if(objAolPlaintiffLW != null && objAolPlaintiffLW instanceof List){
				List objAolPlaintiffLWList = (List)objAolPlaintiffLW;
				long averageCaseSize = 0;
				long largestCaseSize = 0;
				int percentageOfRevenue = 0;
				for(int i=0; i<objAolPlaintiffLWList.size(); i++){
					Map map = (HashMap)objAolPlaintiffLWList.get(i);
					if(map.get("PercentageOfRevenue") != null && !map.get("PercentageOfRevenue").equals("")){
						percentageOfRevenue = percentageOfRevenue + Integer.parseInt(map.get("PercentageOfRevenue").toString());
					}else{
						map.put("PercentageOfRevenue", "0");
					}
					if(map.get("AverageCaseSize") != null && !map.get("AverageCaseSize").equals("")){
						Object AverageCaseSize = removeAmountFormat(map.get("AverageCaseSize").toString());
						averageCaseSize = averageCaseSize + Long.parseLong(AverageCaseSize.toString());
					}else{
						map.put("AverageCaseSize", "0");
					}
					if(map.get("LargestCaseSize") != null && !map.get("LargestCaseSize").equals("")){
						Object LargestCaseSize = removeAmountFormat(map.get("LargestCaseSize").toString());
						largestCaseSize = largestCaseSize + Long.parseLong(LargestCaseSize.toString());
					}else{
						map.put("LargestCaseSize", "0");
					}
				}
				ctx.put("aol_percentageOfRevenue", percentageOfRevenue);
				ctx.put("aol_averageCaseSize", applyAmountFormat(averageCaseSize));
				ctx.put("aol_largestCaseSize", applyAmountFormat(largestCaseSize));
				ctx.put("aol_plaintiff_list_01", objAolPlaintiffLWList);
			}
			*/
			
			if(objAolPlaintiffLW == null || (objAolPlaintiffLW != null && objAolPlaintiffLW instanceof List && ((List)objAolPlaintiffLW).size()<=0)){
				objAolPlaintiffLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetAOLLWFullDetails", ctx);
				List objAolPlaintiffLWList = (List)objAolPlaintiffLW;
				ctx.put("aol_plaintiff_list_01", objAolPlaintiffLWList);
				ctx.put("aol_percentageOfRevenue", "");
				ctx.put("aol_averageCaseSize", "");
				ctx.put("aol_largestCaseSize", "");
			}
			
			Object objAttrPlainLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetAttorneyInPlaintiffForFirmLWFullDetails", ctx);
			if(objAttrPlainLW != null && objAttrPlainLW instanceof List){
				List objAttrPlainLWList = (List)objAttrPlainLW;
				for(int i=0; i<objAttrPlainLWList.size(); i++){
					Map map = (HashMap)objAttrPlainLWList.get(i);
					if(map.get("PlaintiffPecOfTimeDev") != null){
						String PlaintiffPecOfTimeDev = map.get("PlaintiffPecOfTimeDev").toString();
						PlaintiffPecOfTimeDev = setPercentage(PlaintiffPecOfTimeDev);
						map.put("PlaintiffPecOfTimeDev", PlaintiffPecOfTimeDev);
					}else{
						map.put("PlaintiffPecOfTimeDev", "0");
					}
				}
			}
			ctx.put("attorneys_plaintiff_list_01", objAttrPlainLW);
			
			
			
			/*
			Object objFinancialLW = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetFinancialInstSuppLWFullDetails", ctx);
			ctx.put("financial_freeform_01", objFinancialLW);
			
			Object objAttrFinanLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetFinancialInstitutionLWFullDetails", ctx);
			ctx.put("attorneys_financial_list_01", objAttrFinanLW);
			*/
			
			
			
			Object objPredecessorLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetProdecessorFirmLWFullDetails", ctx);
			ctx.put("prodecessor_list_1", objPredecessorLW);
			
//			Object objClaimsLW = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetProfLiabClaimAgnstFirmLWFullDetails", ctx);
//			ctx.put("claims_freeform_01", objClaimsLW);
			
			Object objClaimsLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetProfLiabClaimAgnstFirmLWFullDetails", ctx);
			ctx.put("claims_list_1", objClaimsLW);
			
			/*
			Object objPublicLW = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetEntertainmentSportSuppLWFullDetails", ctx);
			ctx.put("public_freeform_01", objPublicLW);
			
			Object objAttrPublicLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetAttorneyInEntertainForFirmLWFullDetails", ctx);
			if(objAttrPublicLW != null && objAttrPublicLW instanceof List){
				List objAttrPublicLWList = (List)objAttrPublicLW;
				for(int i=0; i<objAttrPublicLWList.size(); i++){
					Map map = (HashMap)objAttrPublicLWList.get(i);
					if(map.get("PercentageOfTimeDev") != null){
						String PercentageOfTimeDev = map.get("PercentageOfTimeDev").toString();
						PercentageOfTimeDev = setPercentage(PercentageOfTimeDev);
						map.put("PercentageOfTimeDev", PercentageOfTimeDev);
					}else{
						map.put("PercentageOfTimeDev", "0");
					}
				}
			}
			ctx.put("attorneys_public_list_1", objAttrPublicLW);
			
			Object objClntPublicLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetEnterSportsClientOfFirmLWFullDetails", ctx);
			ctx.put("clients_public_list_1", objClntPublicLW);
			
			Object objAotpTaxLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetAreaOfTaxPracticeLWFullDetails", ctx);
			if(objAotpTaxLW != null && objAotpTaxLW instanceof List){
				List objAotpTaxLWList = (List)objAotpTaxLW;
				int total = 0;
				for(int i=0; i<objAotpTaxLWList.size(); i++){
					Map map = (Map)objAotpTaxLWList.get(i);
					if(map.get("PercentageValue") != null && !map.get("PercentageValue").equals("")){
						total = total + Integer.parseInt(map.get("PercentageValue").toString());
						ctx.put("AOTP_PercentageValue_"+i, map.get("PercentageValue").toString());
					}else{
						ctx.put("AOTP_PercentageValue_"+i, "0");
					}
					ctx.put("AOTP_CommentDesc_"+i, map.get("AOTPCommentDesc"));
				}
				ctx.put("aotp_total", total);
			}
			if(objAotpTaxLW == null || (objAotpTaxLW != null && objAotpTaxLW instanceof List && ((List)objAotpTaxLW).size()<=0)){
				objAotpTaxLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetAOTPLWFullDetails", ctx);
				List objAotpTaxLWList = (List)objAotpTaxLW;
				for(int i=0; i<objAotpTaxLWList.size(); i++){
					Map map = (Map)objAotpTaxLWList.get(i);
					ctx.put("AOTP_PercentageValue_"+i, "0");
					ctx.put("AOTP_CommentDesc_"+i, map.get("AOTPCommentDesc"));
				}
				ctx.put("aotp_total", "0");
			}
			
			Object objAttrTaxLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetAttorneyHaveTaxWorkLWFullDetails", ctx);
			if(objAttrTaxLW != null && objAttrTaxLW instanceof List){
				List objAttrTaxLWList = (List)objAttrTaxLW;
				for(int i=0; i<objAttrTaxLWList.size(); i++){
					Map map = (HashMap)objAttrTaxLWList.get(i);
					if(map.get("PercentOfTimeDevoted") != null){
						String PercentOfTimeDevoted = map.get("PercentOfTimeDevoted").toString();
						PercentOfTimeDevoted = setPercentage(PercentOfTimeDevoted);
						map.put("PercentOfTimeDevoted", PercentOfTimeDevoted);
					}else{
						map.put("PercentOfTimeDevoted", "0");
					}
				}
			}
			ctx.put("attorneys_tax_list_01", objAttrTaxLW);
			*/
			
			
			
			Object objWillsEstLW = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetWillsTrustsEstateSuppLWFullDetails", ctx);
			ctx.put("willsEstate_freeform_01", objWillsEstLW);
			
			Object objWillsEstServLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetWillsEstateServicesLWFullDetails", ctx);
			if(objWillsEstServLW != null && objWillsEstServLW instanceof List){
				List objWillsEstServLWList = (List)objWillsEstServLW;
				int size1 = objWillsEstServLWList.size()/4;
				List list1 = new ArrayList();
				List list2 = new ArrayList();
				List list3 = new ArrayList();
				List list4 = new ArrayList();
				for(int i=0; i<size1;i++){
					Map map = (HashMap)objWillsEstServLWList.get(i);
					list1.add(map);
				}
				for(int i=size1; i<objWillsEstServLWList.size()/2; i++){
					Map map = (HashMap)objWillsEstServLWList.get(i);
					list2.add(map);
				}
				size1 = objWillsEstServLWList.size()/2 + size1;
				for(int i=objWillsEstServLWList.size()/2; i<size1; i++){
					Map map = (HashMap)objWillsEstServLWList.get(i);
					list3.add(map);
				}
				for(int i=size1; i<objWillsEstServLWList.size(); i++){
					Map map = (HashMap)objWillsEstServLWList.get(i);
					list4.add(map);
				}
				ctx.put("services_willsEstate_list_01", list1);
				ctx.put("services_willsEstate_list_02", list2);
				ctx.put("services_willsEstate_list_03", list3);
				ctx.put("services_willsEstate_list_04", list4);
			}
			if(objWillsEstServLW == null || (objWillsEstServLW != null && objWillsEstServLW instanceof List && ((List)objWillsEstServLW).size()<=0)){
				objWillsEstServLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetWESLWFullDetails", ctx);
				List objWillsEstServLWList = (List)objWillsEstServLW;
				int size1 = objWillsEstServLWList.size()/4;
				List list1 = new ArrayList();
				List list2 = new ArrayList();
				List list3 = new ArrayList();
				List list4 = new ArrayList();
				for(int i=0; i<size1;i++){
					Map map = (HashMap)objWillsEstServLWList.get(i);
					list1.add(map);
				}
				for(int i=size1; i<objWillsEstServLWList.size()/2; i++){
					Map map = (HashMap)objWillsEstServLWList.get(i);
					list2.add(map);
				}
				size1 = objWillsEstServLWList.size()/2 + size1;
				for(int i=objWillsEstServLWList.size()/2; i<size1; i++){
					Map map = (HashMap)objWillsEstServLWList.get(i);
					list3.add(map);
				}
				for(int i=size1; i<objWillsEstServLWList.size(); i++){
					Map map = (HashMap)objWillsEstServLWList.get(i);
					list4.add(map);
				}
				ctx.put("services_willsEstate_list_01", list1);
				ctx.put("services_willsEstate_list_02", list2);
				ctx.put("services_willsEstate_list_03", list3);
				ctx.put("services_willsEstate_list_04", list4);
			}	
			
			String agentemail = SystemProperties.getInstance().getString(
					"appl.agent.licensenumber");
			ctx.put("agentemaillicense", agentemail);
			if(agentemail != null && !"".equals(agentemail)){
				
				Map obj = (Map)SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesagentemaillicense", ctx);
				if(obj!= null){
					
					String agentlicensenumber = obj.get("LicenseNumber") != null ? obj.get("LicenseNumber").toString() : "";
					ctx.put("agentlicensenumber", agentlicensenumber);
					
				}
			}
			logger.debug("Fetch data for XML XML2PDF: "); 
			/*
			Object objCorpLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetCorpCommBusinessLWFullDetails", ctx);
			if(objCorpLW != null && objCorpLW instanceof List){
				List objCorpLWList = (List)objCorpLW;
				int size1 = objCorpLWList.size()/4;
				List list1 = new ArrayList();
				List list2 = new ArrayList();
				List list3 = new ArrayList();
				List list4 = new ArrayList();
				for(int i=0; i<size1;i++){
					Map map = (HashMap)objCorpLWList.get(i);
					list1.add(map);
				}
				for(int i=size1; i<objCorpLWList.size()/2; i++){
					Map map = (HashMap)objCorpLWList.get(i);
					list2.add(map);
				}
				size1 = objCorpLWList.size()/2 + size1;
				for(int i=objCorpLWList.size()/2; i<size1; i++){
					Map map = (HashMap)objCorpLWList.get(i);
					list3.add(map);
				}
				for(int i=size1; i<objCorpLWList.size(); i++){
					Map map = (HashMap)objCorpLWList.get(i);
					list4.add(map);
				}
				ctx.put("areas_corp_list_01", list1);
				ctx.put("areas_corp_list_02", list2);
				ctx.put("areas_corp_list_03", list3);
				ctx.put("areas_corp_list_04", list4);
			}
			if(objCorpLW == null || (objCorpLW != null && objCorpLW instanceof List && ((List)objCorpLW).size()<=0)){
				objCorpLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetCCBLWFullDetails", ctx);
				List objCorpLWList = (List)objCorpLW;
				int size1 = objCorpLWList.size()/4;
				List list1 = new ArrayList();
				List list2 = new ArrayList();
				List list3 = new ArrayList();
				List list4 = new ArrayList();
				for(int i=0; i<size1;i++){
					Map map = (HashMap)objCorpLWList.get(i);
					list1.add(map);
				}
				for(int i=size1; i<objCorpLWList.size()/2; i++){
					Map map = (HashMap)objCorpLWList.get(i);
					list2.add(map);
				}
				size1 = objCorpLWList.size()/2 + size1;
				for(int i=objCorpLWList.size()/2; i<size1; i++){
					Map map = (HashMap)objCorpLWList.get(i);
					list3.add(map);
				}
				for(int i=size1; i<objCorpLWList.size(); i++){
					Map map = (HashMap)objCorpLWList.get(i);
					list4.add(map);
				}
				ctx.put("areas_corp_list_01", list1);
				ctx.put("areas_corp_list_02", list2);
				ctx.put("areas_corp_list_03", list3);
				ctx.put("areas_corp_list_04", list4);
			}*/
			
		}catch(Exception e){
			logger.error("Unexpected error", e);
			logger.error("Problem in fetching data for Quote Number : " +ctx.get("QuoteNumber") + " and exception is " + e.getMessage());
		}
	}
	
	public void populateDataXml(Context ctx, String xmlFile) {
		StringBuffer buffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		try{			
			// Data writer
			logger.debug("Fetch data for XML XML2PDF 100: "); 
			IOUtils.writeToFile(xmlFile, buffer.append(new RequestProcessor().generateResponseXml(ctx, false)));
			logger.debug("Fetch data for XML XML2PDF: 200 "); 
		}
		catch(Exception e){
			logger.error("Unexpected error", e);
			logger.error("Problem in generating data xml for Quote Number : " +ctx.get("QuoteNumber") + " and exception is " + e.getMessage());
		}
	}

	public static void main(String ss[]) throws Exception{
		Context ctx = new Context();
		String projectName = "LawyersIns";
		String resourcePath = SystemProperties.getInstance().getString("xml.basedir") + projectName + "//ibatis//maps//SqlMapConfig.xml";
		SqlResources.load(projectName, resourcePath);
		ctx.setProject(projectName);
		String xmlFile = "";
		String foFile = "";
		String outFile = "C://pdf//testResult.pdf";
		try{
			//String htmlDir = "D:\\LawyersIns_workspace\\LawyersIns\\web\\";//SystemProperties.getInstance().getString("html.basedir");
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
			
			xmlFile = htmlDir + "data//quoteletter.xml";
			
			foFile = htmlDir + "foxsl//quoteletter.xsl";
			ctx.put("PolicyKey", "933");
			ctx.put("TransactionSequence", "933");
			ctx.put("VersionSequence", "933");
			ctx.put("CoverageSequence", "933");
			ctx.put("AccountKey", "933");
			ctx.put("AddressKey", "933");
			ctx.put("QuoteNumber", "QN-0000933");	
			new DownloadPDF().fetchDataForXml(ctx);
			new DownloadPDF().populateDataXml(ctx, xmlFile);
			new XML2PDF().convertPOToPDF(foFile, xmlFile, outFile);
		}
		catch(Exception e){
			logger.error("Unexpected error", e);
			logger.error("Problem in generating quote letter for Quote Number : " +ctx.get("QuoteNumber"));
		}
	}
	
	private String setPercentage(String str){
		return str = str.replace(".00", "");
	}
	
	public Object removeAmountFormat(Object arg1){
	      if(arg1 == null || "".equals(arg1.toString().trim()))
	          return 0;
	      
	      String amount = arg1.toString();      
	      return amount.replace("$", "").replace(",", "");
	}
	
	private Object applyAmountFormat(Object obj){
		if(obj != null && !obj.toString().equals("")){
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			String val = nf.format(obj);
			val = val.replace(".00", "");
			return val;
		}else{
			return obj;
		}
	}
	
	public void fetchDataForXmlQuoteLetter(Context ctx) {		
		try{
			
			Object objPoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquotelettergetPriorPolicyInfo", ctx);
			ctx.put("policy_freeform_01", objPoilcy);
			
			Object objAddress = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquotelettergetAddressdetails", ctx);
			ctx.put("address_freeform_01", objAddress);
			
			Object listQuoteOption = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfquotelettergetQuoteOptedList", ctx);
			ctx.put("quoteOpted_list_01", listQuoteOption);
			
	//		Object objCoveragePoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquoteletterCoveragePolicy", ctx);
	//		ctx.put("policycoverage_freeform_01", objCoveragePoilcy);
			
			
			
			Object objProLiabIns = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquotelettergetProfessionalLiabilityInsDetail", ctx);
			ctx.put("professionalliabilityinsdetail_freeform_01", objProLiabIns);
			
			Object objFooterTime = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfquotelettergetQuoteFooterTime", ctx);
            ctx.put("time_freeform_01", objFooterTime);
			
			
			
			Object listStateForm = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesPolicyFormLW", ctx);
			ctx.put("stateform_list_01", listStateForm);
			
		}catch(Exception e){
			logger.error("Problem in fetching data for Quote Number : " +ctx.get("QuoteNumber"));
		}
	}
	
	public void makePAPdf(Context ctx, OutputStream out, String baseUrl, ServletContextURIResolver uriResolver) {
		
		String xmlFile = "";
		String foFile = "";		
		
		try{			
			
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
			
			xmlFile = htmlDir + "data//quoteletter.xml";
			foFile = htmlDir + "foxsl//LPLNOTICEPA2.xsl";
			baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			new XML2PDF().convertPOToPDF(foFile, xmlFile, out, baseUrl, (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER),ctx);			
		}
		catch(Exception e){
			logger.error("Problem in generating PA Notice Form for Quote Number : " +ctx.get("QuoteNumber") + " " + e.getMessage());
		}
	}
}
