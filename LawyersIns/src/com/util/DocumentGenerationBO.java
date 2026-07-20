package com.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.fop.servlet.ServletContextURIResolver;
import org.jdom.Element;

import com.excel.downloadexcel.DownloadExcel;
import com.formgenerator.DownloadForm;
import com.manage.docmanagement.DocManagementUtil;
import com.manage.managecomponent.components.SetParametersForStoredProcedures;
import com.manage.managemetadata.functions.commonfunctions.Math;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.userbo.DocumentManagment;
import com.userbo.LawyersConstants;
import com.userbo.LawyersUtilities;
import com.userbo.LawyersUtils;
import com.userbo.OffRiskLawyersUtils;

public class DocumentGenerationBO {
	
	private static InetLogger logger = InetLogger
			.getInetLogger(DocumentGenerationBO.class);
	private static final String ERROR_MESSAGE = "ERROR : ";
	private static final String SUCCESS_MESSAGE = "SUCCESS";

	
	public static Object fetchDataForQuoteLetter(Context ctx,String filename) {
		try {
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
			LawyersUtils.populatePolicyForm(ctx);			
			Object objPoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey(
					"SqlStmts.UserStatementpdfquotelettergetPriorPolicyInfo",
					ctx);
			
			
			RuleUtils.executeRule(ctx, "LawyersRule.fillQuoteDates");
			Map objPoilcyMap=(Map)objPoilcy;
			objPoilcyMap.put("QuoteEffectiveDate", ctx.get("QuoteEffectiveDate"));
			objPoilcyMap.put("QuoteExpiryDate", ctx.get("QuoteExpiryDate"));
			ctx.put("policy_freeform_01", objPoilcyMap);
			
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

    		
			Object listQuoteOption = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfquotelettergetQuoteOptedList",ctx);
			ctx.put("quoteOpted_list_01", listQuoteOption);
			
			 listQuoteOption = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfquotelettergetQuoteOptedListNew",ctx);
			ctx.put("quoteOptedUpdated_list_01", listQuoteOption);

			Object objProLiabIns = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquotelettergetProfessionalLiabilityInsDetail",ctx);
			ctx.put("professionalliabilityinsdetail_freeform_01",
							objProLiabIns);

			Object objFooterTime = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfquotelettergetQuoteFooterTime",ctx);
			ctx.put("time_freeform_01", objFooterTime);
			
			Object listStateForm = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesPolicyFormLW", ctx);
			ctx.put("stateform_list_01", listStateForm);
			
			Object objsubproducerFormData = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfquotelettergetSubproducerFormData", ctx);
			logger.debug("sukhi"+ctx.get("subproducerFormData_freeform_01"));
			List li=(List) objsubproducerFormData;
			for(int i=0;i<li.size();i++){
				Map<String,String> mp=(Map) li.get(i);
				Set keys=mp.keySet();
				for (Iterator i2 = keys.iterator(); i2.hasNext(); ) {
				       String key = (String) i2.next();
				       String value = (String) mp.get(key);
				       logger.debug("mp "+key+":"+value);
				       if(key.equalsIgnoreCase("SPZip") || key.equalsIgnoreCase("CompleteAddress")){
				    	   if(value.length()<7){
				    		   String spZip=value.replace("-","");
				    		   logger.debug("SPZip==="+spZip);
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
				    	   ImageDownld(value);
				       }
				}
			}
			if(filename.equals("quoteletter")){
				ctx.put("quoteletterfilename", "QuoteLetterView");
			}else{
				ctx.put("quoteletterfilename", "QuoteLetterSend");
			}
			ctx.put("subproducerFormData_freeform_01", objsubproducerFormData);
		} catch (Exception e) {
			logger.error("Problem in fetching data for Quote Number : "
					+ ctx.get("QuoteNumber") + " " + e.getMessage());
		}
		
		return null;
	}
	
	
	
	public static Object fetchDataForApplicationPDF(Context ctx) {		
		try{
			
			String homePath=SystemProperties.getInstance().getString("appl.home.dir");
			String checkboxYPath=homePath+"image//check-btn6.png";
			String checkboxNPath=homePath+"image//check-btn4.png";
			String cfLogoPath=homePath+"image//crum_logo.png";
			
			ctx.put("checkboxYPath",checkboxYPath);
			ctx.put("checkboxNPath",checkboxNPath);
			ctx.put("cfLogoPath",cfLogoPath);
			logger.error("Gathering sdata for  Docu sign PDF for Policy "+ctx.get("PolicyKey")+" 1");
			
			
			
			Object objPoilcyCCBdata = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfccbSupplementDetail", ctx);
			if(objPoilcyCCBdata == null)
				ctx.put("policy_CCB_01", "");
			else
				ctx.put("policy_CCB_01", objPoilcyCCBdata);
			
			Object objCannabisflag = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetFirmCannabisDetails", ctx);
			ctx.put("policy_Cannabis_flag", objCannabisflag);
			
			
			
			Object objCannabisSuppldata = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetCannabisSupplementDetails", ctx);
			ctx.put("policy_Cannabis_01", objCannabisSuppldata);
			
			
			
			Object objPoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetPriorPolicyInfo", ctx);
			ctx.put("policy_freeform_01", objPoilcy);
			
			Object objSubProducerNumber = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetSubProducerNumber", ctx);
			ctx.put("subproducernumber_freeform_02", objSubProducerNumber);
			
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
				if((map.get("IsLawyerProfLiabClaimAgntAppl")!=null && map.get("IsLawyerProfLiabClaimAgntAppl").toString().equals("Y"))||(map.get("IsAnyActOmmBecomeClaimAgntFirm")!=null && map.get("IsAnyActOmmBecomeClaimAgntFirm").toString().equals("Y"))){
					
					map.put("FlagForClaim", "Y");
				}else{
					map.put("FlagForClaim", "N");
				}
				if(map.get("IsAOPChange") == null){
					map.put("IsAOPChange", "");
				}
				map.put("haveBackupAttorney", map.get("IsFirmHaveBackupAttorney"));
			}			
			ctx.put("firm_freeform_01", objFirmLW);
			
			Object objAttFirmLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetAttorneyDetailsLWFullDetails", ctx);
			ctx.put("attorneys_firm_list_01", objAttFirmLW);
			
			new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,litigationNewImplDate");
			List aopDataList =(List)SqlResources.getSqlMapProcessor(ctx).select("AreaOfPracticeLW.DisplayAOPDetailsForPDF_p", ctx);
			
			if(aopDataList!=null && aopDataList.size()>0)
			{
			ctx.put("aopData_list_01",  aopDataList.get(0));
			ctx.put("aopData_list_02",  aopDataList.get(1));
			}
			
			
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
                              ctx.put("aopPercentage_" + map.get("AOPKey"), map.get("PercentageValue"));
                              total = total + Integer.parseInt(map.get("PercentageValue").toString());
                              
                              ctx.put("AOP_PercentageValue_" + map.get("AOPKey"), map.get("PercentageValue").toString());
                        }
                        if(map.get("AOPCommentDesc") != null && !map.get("AOPCommentDesc").equals("")){
                        	ctx.put("AOP_CommentDesc_" + map.get("AOPKey"), map.get("AOPCommentDesc"));
                        }
                  }
                  
                  ctx.put("aop_total", total);
            }

			
			
			
			if(objAopFirmLW == null || (objAopFirmLW != null && objAopFirmLW instanceof List && ((List)objAopFirmLW).size()<=0)){
				objAopFirmLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetAOPLWFullDetails", ctx);
				List objAopFirmLWList = (List)objAopFirmLW;
				for(int i=0; i<objAopFirmLWList.size(); i++){
					ctx.put("AOP_PercentageValue_"+i, "0");
					ctx.put("AOP_CommentDesc_"+i, "");
				}
				
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
			
			if((ctx.get("CompanyKey") instanceof Integer ? (Integer)ctx.get("CompanyKey") == 3 : "3".equals(ctx.get("CompanyKey"))) && !"Issued".equals(ctx.get("StatusDesc")) && (objFirmLawsuitPracticeLW == null || ((List)objFirmLawsuitPracticeLW).size() < 1 || ((List)objFirmLawsuitPracticeLW).isEmpty())) {
				List objFirmLawsuitPracticeLWList = new ArrayList();
				Map objFirmLawsuitPracticeMap = new HashMap();
				objFirmLawsuitPracticeMap.put("AmountOfFeesSued", "");
				objFirmLawsuitPracticeMap.put("DueDateFees", "");
				objFirmLawsuitPracticeMap.put("SuitFilesDateFees", "");
				objFirmLawsuitPracticeMap.put("IsFavOutInUnderMatter", "");
				objFirmLawsuitPracticeMap.put("IsAllegOfLegalMalPrac", "");
				objFirmLawsuitPracticeMap.put("FeesAreaOfPractice", "");
				objFirmLawsuitPracticeMap.put("FeeSuitDesc", "");
				objFirmLawsuitPracticeLWList.add(objFirmLawsuitPracticeMap);
				ctx.put("firmlawsuit_practice_list_01", objFirmLawsuitPracticeLWList);
			}
			
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
			
			
			logger.error("Gathering sdata for  Docu sign PDF for Policy "+ctx.get("PolicyKey")+" 2");
			
			if(objAopRELW == null || (objAopRELW != null && objAopRELW instanceof List && ((List)objAopRELW).size()<=0)){
				objAopRELW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementAOPSupplementsQueriespopulateAOPREResidential", ctx);
				List objAopRELWList = (List)objAopRELW;
				for(int i=0; i<objAopRELWList.size(); i++){
					Map map = (Map)objAopRELWList.get(i);
					ctx.put("AOPRE_PercentageValue_"+map.get("AOPREKey"), "0");
				}
				ctx.put("aopre_total", "0");
				
			}
			
			
			
			
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
			if(objPlaintiffLW == null)
				ctx.put("plaintiff_freeform_01", null);
			else
				ctx.put("plaintiff_freeform_01", objPlaintiffLW);
			
			int totalintPercentageOfRevenue = 0;
			
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
				totalintPercentageOfRevenue=percentageOfRevenue;
				ctx.put("aol_percentageOfRevenue", percentageOfRevenue);
				ctx.put("aol_averageCaseSize", applyAmountFormat(averageCaseSize));
				ctx.put("aol_largestCaseSize", applyAmountFormat(largestCaseSize));
				//ctx.put("aol_plaintiff_list_01", objAolPlaintiffLWList);
				ctx.put("aol_plaintiffMap", objMap);
			}
			
			
			
			
			
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
			
			
			
		
			
			
			
			Object objPredecessorLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetProdecessorFirmLWFullDetails", ctx);
			ctx.put("prodecessor_list_1", objPredecessorLW);
			
			if((ctx.get("CompanyKey") instanceof Integer ? (Integer)ctx.get("CompanyKey") == 3 : "3".equals(ctx.get("CompanyKey"))) && !"Issued".equals(ctx.get("StatusDesc")) && (objPredecessorLW == null || ((List)objPredecessorLW).size() < 1 || ((List)objPredecessorLW).isEmpty())) {
				List objPredecessorLWList = new ArrayList();
				Map objPredecessorMap = new HashMap();
				objPredecessorMap.put("NumberOfAttorneyAtDiss", "");
				objPredecessorMap.put("InsurerAtDissolution", "");
				objPredecessorMap.put("FirmName", "");
				objPredecessorMap.put("TypeOfEntity", "");
				objPredecessorMap.put("IsERPPurchased", "");
				objPredecessorMap.put("DateOfAcquisation", "");
				objPredecessorMap.put("NumberOfAttorneyAtApplFirm", "");
				objPredecessorMap.put("ERPExpDate", "");
				objPredecessorLWList.add(objPredecessorMap);
				ctx.put("prodecessor_list_1", objPredecessorLWList);
			}
			
			Object objClaimsLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetProfLiabClaimAgnstFirmLWFullDetails", ctx);
			ctx.put("claims_list_1", objClaimsLW);
			
			if((ctx.get("CompanyKey") instanceof Integer ? (Integer)ctx.get("CompanyKey") == 3 : "3".equals(ctx.get("CompanyKey"))) && !"Issued".equals(ctx.get("StatusDesc")) && (objClaimsLW == null || ((List)objClaimsLW).size() < 1 || ((List)objClaimsLW).isEmpty())) {
				List objClaimsLWList = new ArrayList();
				Map objClaimsMap = new HashMap();
				objClaimsMap.put("ClaimNotifiedDate", "");
				objClaimsMap.put("IsClaimReportedToInsComp", "");
				objClaimsMap.put("ClaimClosingDate", "");
				objClaimsMap.put("CurrentStatus", "");
				objClaimsMap.put("NameOfInsComp", "");
				objClaimsMap.put("ClaimantLastDemand", "");
				objClaimsMap.put("InsurerLoss", "");
				objClaimsMap.put("IsClient", "");
				objClaimsMap.put("NameOfPersonnelINClaim", "");
				objClaimsMap.put("NameOfClaimant", "");
				objClaimsMap.put("DateOfAllegedError", "");
				objClaimsMap.put("StepsTakenToPreventClaims", "");
				objClaimsMap.put("DescOfClaim", "");
				objClaimsMap.put("IsClaimInSuit", "");
				objClaimsMap.put("TotalAmountPaid", "");
				objClaimsMap.put("DateReportedToInsComp", "");
				objClaimsLWList.add(objClaimsMap);
				ctx.put("claims_list_1", objClaimsLWList);
			}
			
			Object objWillsEstLW = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetWillsTrustsEstateSuppLWFullDetails", ctx);
			if(objWillsEstLW == null)
				ctx.put("willsEstate_freeform_01", "");
			else
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
			Object objAttorneysInsurance = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetAttorneysInsurance", ctx);	
			if(objAttorneysInsurance!=null )
			{
			ctx.put("AttorneysInsurance_list_01",objAttorneysInsurance);
			
			}
			Object objBankruptcyDetails = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetBankruptcyDetailspdf", ctx);	
			if(objBankruptcyDetails!=null )
			{
			ctx.put("Bankruptcy_list_01",objBankruptcyDetails);
			
			}
			Object objcollections = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetCollectionsDetailspdf", ctx);	
			if(objcollections!=null )
			{
			ctx.put("Collections_list_01",objcollections);
			
			}
			Object objCopyRightTrademarkDetails = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetCopyRightTrademarkDetailspdf", ctx);	
			if(objCopyRightTrademarkDetails!=null )
			{
			ctx.put("CopyRightTrademark_list_01",objCopyRightTrademarkDetails);
			
			}
			Object objFamilyLawDetailsLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetFamilyLawDetailspdf", ctx);
				
			if(objFamilyLawDetailsLW != null && objFamilyLawDetailsLW instanceof List){
				List objFamilyLawDetailsList = (List)objFamilyLawDetailsLW;
				int total = 0;
				for(int i=0; i<objFamilyLawDetailsList.size(); i++){
					Map map = (Map)objFamilyLawDetailsList.get(i);
					if(map.get("PercentageValue") != null && !map.get("PercentageValue").equals("")){
						total = total + Integer.parseInt(map.get("PercentageValue").toString());
						ctx.put("FLAOP_PercentageValue_"+map.get("FLAOPKey"), map.get("PercentageValue").toString());
					}else{
						ctx.put("FLAOP_PercentageValue_"+map.get("FLAOPKey"), "0");
					}
					ctx.put("FLAO_CommentDesc_"+map.get("FLAOPKey"), map.get("FLAOPCommentDesc"));
				}
				
				ctx.put("FLAOP_total", total);
			}
			
			
		/*	
			if(objFamilyLawDetailsLW!=null )
			{
			ctx.put("FamilyLaw_list_01",objFamilyLawDetailsLW);
			
			}*/
			
			Object objgetFinancialInstitution = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetFinancialInstitutionDetailspdf", ctx);	
			if(objgetFinancialInstitution!=null )
			{
			ctx.put("FinancialInstitution_list_01",objgetFinancialInstitution);
			
			}
			
			Object objGovernment = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetGovernmentSupplementDetailspdf", ctx);	
			if(objGovernment!=null )
			{
			ctx.put("Government_list_01",objGovernment);
			
			}
			Object objRealEastateDetails = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetRealEastateDetailspdf", ctx);	
			if(objRealEastateDetails!=null )
			{
			ctx.put("RealEastate_list_01",objRealEastateDetails);
			
			}
		/*	Object objAreaOfLitigation = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetAreaOfLitigationForFirmpdf", ctx);	
			if(objAreaOfLitigation!=null )
			{
			ctx.put("Litigation_list_01",objAreaOfLitigation);
			
			}*/
		
			
			List governmentServices =(List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetGovernmentServicesDetailspdf", ctx);
			
			if(governmentServices!=null && governmentServices.size()>0)
			{
				
			ctx.put("governmentServices_list_01",  governmentServices.get(0));
				
			ctx.put("governmentServices_list_02",  governmentServices.get(1));
				
			ctx.put("governmentServices_list_03",  governmentServices.get(2));
				
			}
			Object objAopRELWNew = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementAOPSupplementsQueriespopulateAOPREpdf", ctx);
			if(objAopRELWNew != null && objAopRELWNew instanceof List){
				List objAopRELWNewList = (List)objAopRELWNew;
				int total = 0;
				for(int i=0; i<objAopRELWNewList.size(); i++){
					Map map = (Map)objAopRELWNewList.get(i);
					if(map.get("PercentageValue") != null && !map.get("PercentageValue").equals("")){
						total = total + Integer.parseInt(map.get("PercentageValue").toString());
						ctx.put("AOPRE_PercentageValueRes_"+map.get("AOPREKey"), map.get("PercentageValue").toString());
					}else{
						ctx.put("AOPRE_PercentageValueRes_"+map.get("AOPREKey"), "0");
					}
					ctx.put("AOPRE_CommentDescRes_"+map.get("AOPREKey"), map.get("AOPRECommentDesc"));
				}
				
				ctx.put("aopreRes_total", total);
			}
			
			boolean isAfterbankruptcySupply=(Boolean) RuleUtils.executeRule(ctx, "LawyersRule.bankruptcyImplementationDate");
			if(isAfterbankruptcySupply)
				ctx.put("isAfterbankruptcySupply","Y");
			else
				ctx.put("isAfterbankruptcySupply","N");
			
			
			Object objAopRECLWNew = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementAOPSupplementsQueriespopulateAOPREpdf", ctx);
			if(objAopRECLWNew != null && objAopRECLWNew instanceof List){
				List objAopRECLWListNew = (List)objAopRECLWNew;
				int total = 0;
				for(int i=0; i<objAopRECLWListNew.size(); i++){
					Map map = (Map)objAopRECLWListNew.get(i);
					if(map.get("PercentageValue") != null && !map.get("CommercialPercentageValue").equals("")){
						total = total + Integer.parseInt(map.get("CommercialPercentageValue").toString());
						ctx.put("AOPRE_PercentageValueCom_"+map.get("AOPREKey"), map.get("CommercialPercentageValue").toString());
					}else{
						ctx.put("AOPRE_PercentageValueCom_"+map.get("AOPREKey"), "0");
					}
					ctx.put("AOPRE_CommentDescCom_"+map.get("AOPREKey"), map.get("AOPRECommCommentDesc"));
				}
				
				ctx.put("aoprecCom_total", total);
			}
			List bankruptcyCasesDetails =(List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetBankruptcyCasesDetailspdf", ctx);
			
			if(bankruptcyCasesDetails!=null && bankruptcyCasesDetails.size()>0)
			{
				if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("1"))
				{
					if(bankruptcyCasesDetails.size()>=1)
						ctx.put("bankruptcyCasesDetails_list_01",  bankruptcyCasesDetails.get(0));
					if(bankruptcyCasesDetails.size()>=2)
						ctx.put("bankruptcyCasesDetails_list_02",  bankruptcyCasesDetails.get(1));
					if(bankruptcyCasesDetails.size()==3)
						ctx.put("bankruptcyCasesDetails_list_03",  bankruptcyCasesDetails.get(2));
				}
				if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("2"))
					
				{
					ctx.put("bankruptcyCasesDetails_list_01",  bankruptcyCasesDetails.get(0));
				}
				
			}
			
		List bankruptcyCasesDetails_new =(List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetBankruptcyCasesDetailspdf_new", ctx);
			
			if(bankruptcyCasesDetails_new!=null && bankruptcyCasesDetails_new.size()>0)
			{
				for(int i=0; i<bankruptcyCasesDetails_new.size(); i++){
					Map map = (Map)bankruptcyCasesDetails_new.get(i);
					if(map.get("AverageCaseValue") != null && !map.get("AverageCaseValue").equals(""))
					{
						map.put("AverageCaseValue",applyAmountFormat(map.get("AverageCaseValue")));
						bankruptcyCasesDetails_new.set(i,map);
					}
					
					
				}
				
				
				if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("1"))
				{
			ctx.put("bankruptcyCasesDetails_new_list_01",  bankruptcyCasesDetails_new.get(0));
			ctx.put("bankruptcyCasesDetails_new_list_02",  bankruptcyCasesDetails_new.get(1));
			ctx.put("bankruptcyCasesDetails_new_list_03",  bankruptcyCasesDetails_new.get(2));
			ctx.put("bankruptcyCasesDetails_new_list_04",  bankruptcyCasesDetails_new.get(3));
				}
				if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("2"))
					
				{
					ctx.put("bankruptcyCasesDetails_new_list_01",  bankruptcyCasesDetails_new.get(0));
					ctx.put("bankruptcyCasesDetails_new_list_02",  bankruptcyCasesDetails_new.get(1));
					ctx.put("bankruptcyCasesDetails_new_list_03",  bankruptcyCasesDetails_new.get(2));
					ctx.put("bankruptcyCasesDetails_new_list_04",  bankruptcyCasesDetails_new.get(3));
				}
				
			}
			
			
			Object objTaxSuppDetails = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetTaxSuppDetailspdf", ctx);
			if(objTaxSuppDetails != null && objTaxSuppDetails instanceof List){
				List objTaxSuppDetailsNew = (List)objTaxSuppDetails;
				int total = 0;
				for(int i=0; i<objTaxSuppDetailsNew.size(); i++){
					Map map = (Map)objTaxSuppDetailsNew.get(i);
					if(map.get("revenuePercentage") != null && !map.get("revenuePercentage").equals("")){
						total = total + Integer.parseInt(map.get("revenuePercentage").toString());
						ctx.put("Tax_PercentageValue_"+map.get("TaxAOPKey"), map.get("revenuePercentage").toString());
					}else{
						ctx.put("Tax_PercentageValue_"+map.get("TaxAOPKey"), "0");
					}
					ctx.put("Tax_CommentDesc_"+map.get("TaxAOPKey"), map.get("TaxCommentDesc"));
				}
				
				ctx.put("Tax_total", total);
			}
			Object objWillsEstateServicesLW = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetWillsEstateServicespdf", ctx);
			if(objWillsEstateServicesLW != null && objWillsEstateServicesLW instanceof List){
				List objWillsEstateService = (List)objWillsEstateServicesLW;
				int total = 0;
				for(int i=0; i<objWillsEstateService.size(); i++){
					Map map = (Map)objWillsEstateService.get(i);
					if(map.get("PercentageValue") != null && !map.get("PercentageValue").equals("")){
						total = total + Integer.parseInt(map.get("PercentageValue").toString());
						ctx.put("WESKey_PercentageValue_"+map.get("WESKey"), map.get("PercentageValue").toString());
					}else{
						ctx.put("WESKey_PercentageValue_"+map.get("WESKey"), "0");
					}
					ctx.put("WESKey_CommentDesc_"+map.get("WESKey"), map.get("WESCommentDesc"));
				}
				
				ctx.put("WESKey_total", total);
			}
			
			
			
			List trustdata =(List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetWillSTrustEstateTrustspdf", ctx);
			
		if(trustdata!=null && trustdata.size()>0)
		{
			if(trustdata!=null && trustdata.get(0)!=null)
			{
			ctx.put("Trust_list_01",  trustdata.get(0));
			
			}
			if(trustdata!=null && trustdata.get(1)!=null)
			{
			ctx.put("Trust_list_02",  trustdata.get(1));
			
			}
			if(trustdata!=null && trustdata.get(2)!=null)
			{
			ctx.put("Trust_list_03",  trustdata.get(2));
			
			}
		}
			List objWillSTrustEstateLargestTrust =(List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetWillSTrustEstateLargestTrustspdf", ctx);
			
			if(objWillSTrustEstateLargestTrust!=null && objWillSTrustEstateLargestTrust.size()>0)
			{
				if(objWillSTrustEstateLargestTrust.get(0)!=null){
						ctx.put("largestTrust_list_01",  objWillSTrustEstateLargestTrust.get(0));
				}
				if(objWillSTrustEstateLargestTrust.get(1)!=null)
				{
			ctx.put("largestTrust_list_02",  objWillSTrustEstateLargestTrust.get(1));
				}
				if(objWillSTrustEstateLargestTrust.get(2)!=null)
				{
			ctx.put("largestTrust_list_03",  objWillSTrustEstateLargestTrust.get(2));
				}
			}
			Object objAreaOfLitigationForFirm = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetAreaOfLitigationForFirmpdf", ctx);
			if(objAreaOfLitigationForFirm != null && objAreaOfLitigationForFirm instanceof List){
				List objAreaOfLitigation = (List)objAreaOfLitigationForFirm;
				int total = 0;
				for(int i=0; i<objAreaOfLitigation.size(); i++){
					Map map = (Map)objAreaOfLitigation.get(i);
					if(map.get("PercentageOfDefense") != null && !map.get("PercentageOfDefense").equals("")){
						total = total + Integer.parseInt(map.get("PercentageOfDefense").toString());
						ctx.put("AOLKey_PercentageValue_"+map.get("AOLKey"), map.get("PercentageOfDefense").toString());
					}else{
						ctx.put("AOLKey_PercentageValue_"+map.get("AOLKey"), "0");
					}
					ctx.put("AOLKey_CommentDesc_"+map.get("AOLKey"), map.get("DefenseCommentDesc"));
				}
				
				//ctx.put("AOLKey_total", total);
			}
			
			
			
			
			
			
				
				
			
			
			/*int total = 0,litigationPercentage=0,percentageOfDefense=0;
			int defenseTotal=0,percentageValue=0;
			long largestCasesSize=0;
			long combinedTotal=0;
			Map map = new HashMap();
			List limtTypes =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAOLLWFullDetailsNewApp", ctx);
			if (limtTypes != null) {
				for (int i = 0; i < limtTypes.size(); i++) {
					map = (HashMap) limtTypes.get(i);
					 percentageValue = ctx.get("AOL_PercentageOfRevenue_"+ map.get("AOLKey").toString())!= null && !ctx.get("AOL_PercentageOfRevenue_"+ map.get("AOLKey")).equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("AOL_PercentageOfRevenue_"+ map.get("AOLKey")).toString()):0;
					 percentageOfDefense = ctx.get("AOL_PercentageOfDefense_"+ map.get("AOLKey").toString()) != null && !ctx.get("AOL_PercentageOfDefense_"+ map.get("AOLKey")).equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("AOL_PercentageOfDefense_"+ map.get("AOLKey")).toString()):0;
				
						total = total + percentageValue;
						defenseTotal=defenseTotal+percentageOfDefense;
					} 
			*/
			
			
			
			
			
			
			
			
			int totalLitAop=0;
			Object objAopValue = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetAreaOfPracticeLWFullDetails", ctx);
			if(objAopValue != null && objAopValue instanceof List){
				List objAopValueList = (List)objAopValue;
				int total = 0;
				
				
				for(int i=0; i<objAopValueList.size(); i++){
					Map map = (Map)objAopValueList.get(i);
					if(map.get("PercentageValue") != null && !map.get("PercentageValue").equals("")){
						total = total + Integer.parseInt(map.get("PercentageValue").toString());
						ctx.put("AOP_PercentageValueCheck_"+map.get("AOPKey"), map.get("PercentageValue").toString());
						//ctx.put("AOP_PercentageValueCheck_"+i, map.get("PercentageValue").toString());
						int aopKey=Integer.parseInt(map.get("AOPKey").toString());
						//55,56,57,59,60,61,62,63,67
						if(aopKey==55 ||aopKey==56 ||aopKey==57 ||aopKey==59 ||aopKey==60 ||aopKey==61 ||aopKey==62 ||aopKey==63 ||aopKey==67 )
						{
							totalLitAop = totalLitAop + Integer.parseInt(map.get("PercentageValue").toString());
						}
					}
					//ctx.put("AOP_CommentDescCheck_"+i, map.get("AOPCommentDesc"));
				}
				
				ctx.put("aop_totalCheck", total);
			}
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
							ctx.put("isBankruptcyPage",map.get("isBankruptcy"));
							ctx.put("isCollectionPage",map.get("isCollection"));
							ctx.put("isCopyrightPage",map.get("isCopyright"));
							ctx.put("isFinancialPage",map.get("isFinancial"));
							ctx.put("isFamilyLawPage",map.get("isFamilyLaw"));
							ctx.put("isGovermentPage",map.get("isGoverment"));
							ctx.put("isLitigationPage",map.get("isLitigation"));
							ctx.put("isRealestatePage",map.get("isRealestate"));
							ctx.put("isTaxPage",map.get("isTax"));
							ctx.put("isWillsTrustPage",map.get("isWillsTrust"));
							ctx.put("isCCB",map.get("isCCB"));
						}
				}	
					
				
			}
			
		
			
			List objRealEstaeSupp =(List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetRealEstateValuepdf", ctx);
			
			if(objRealEstaeSupp != null && objRealEstaeSupp instanceof List){
				List objRealEstaeSuppList = (List)objRealEstaeSupp;
				int total = 0;
				for(int i=0; i<objRealEstaeSuppList.size(); i++){
					Map map = (Map)objRealEstaeSuppList.get(i);
					
						total = total + Integer.parseInt(map.get("PercentageValue").toString());
						ctx.put("AOP_PercentageRes_"+map.get("AOPKey"), map.get("PercentageValue").toString());
						//ctx.put("AOP_PercentageValueCheck_"+i, map.get("PercentageValue").toString());
					
				}
				
				
			}
			
			if((ctx.get("AOP_PercentageRes_20")!=null && Integer.valueOf(ctx.get("AOP_PercentageRes_20").toString())>0)||(ctx.get("AOP_PercentageRes_27")!=null && Integer.valueOf(ctx.get("AOP_PercentageRes_27").toString())>0))
			{
				
				ctx.put("FlagForRealEstate", "Y");
			}
			else
			{
				ctx.put("FlagForRealEstate", "N");
			}
			
			List amountForRealEstate =(List)SqlResources.getSqlMapProcessor(ctx).select("RealEastateDetailsLW.DisplayRealEstateAmountForPDF_p", ctx);
			
			if(amountForRealEstate!=null && amountForRealEstate.size()>0)
			{
			ctx.put("amountForRealEstate_list_01",  amountForRealEstate.get(0));
			
			}
			
			List amountForCollection =(List)SqlResources.getSqlMapProcessor(ctx).select("collectionsSuplimentDetailsLW.DisplayCollectionAmountForPDF_p", ctx);
			
			if(amountForCollection!=null && amountForCollection.size()>0)
			{
			ctx.put("collectionAmount_list_01",  amountForCollection.get(0));
			
			}
			
			int total1 = 0,litigationPercentage=0,percentageOfDefense=0;
			int defenseTotal=0,percentageValue=0;
			long largestCasesSize=0;
			long combinedTotal=0;
			Map map1 = new HashMap();
			Object obj = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAOLLWFullDetailsNewApp", ctx);
			List limtTypes=(List)obj;
			if (limtTypes != null) {
				for (int i = 0; i < limtTypes.size(); i++) {
					map1 = (HashMap) limtTypes.get(i);
					 //percentageValue = ctx.get("PercentageOfRevenue_"+ map1.get("AOLKey").toString())!= null && !ctx.get("PercentageOfRevenue_"+ map1.get("AOLKey")).equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("PercentageOfRevenue_"+ map1.get("AOLKey")).toString()):0;
					 percentageOfDefense = ctx.get("AOLKey_PercentageValue_"+ map1.get("AOLKey").toString()) != null && !ctx.get("AOLKey_PercentageValue_"+ map1.get("AOLKey")).equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("AOLKey_PercentageValue_"+ map1.get("AOLKey")).toString()):0;
					 //total1 = total1 + percentageValue;
					defenseTotal=defenseTotal+percentageOfDefense;
					} 
				int totalPlaintiffPercentage=totalintPercentageOfRevenue+defenseTotal;
				ctx.put("AOLKey_total", totalPlaintiffPercentage);
			}
			
			//totalLitAop
			char isShowLitigationSupplement='\u0000';
			boolean isBeforeLitigation=(Boolean) RuleUtils.executeRule(ctx, "LawyersRule.beforeLitigationImplementationDate");
			if(isBeforeLitigation)
				ctx.put("isBeforeLitigation","Y");
			else
				ctx.put("isBeforeLitigation","N");
			if(isBeforeLitigation)
			{
				int plaintiffmain=ctx.get("AOP_PercentageValueCheck_18") != null && !HtmlConstants.EMPTY.equals(ctx.get("AOP_PercentageValueCheck_18").toString())?Integer.parseInt(ctx.get("AOP_PercentageValueCheck_18").toString()):0;
				if(plaintiffmain > 0)
				{
					isShowLitigationSupplement='Y';
				}
				else
					isShowLitigationSupplement='N';
				
			}
			else
			{
				if(totalLitAop>0)
				{
					isShowLitigationSupplement='Y';
				}
				else
					isShowLitigationSupplement='N';
				
			}
			
			ctx.put("isShowLitigationSupplement",isShowLitigationSupplement);
			
			
			Object objElectronicInsurance = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetElectronicInsuranceDetailspdf", ctx);	
			if(objElectronicInsurance != null )
			{
				ctx.put("ElectronicInsurance_list_01",objElectronicInsurance);
			
			}
				
			logger.error("Gathering sdata for  Docu sign PDF for Policy "+ctx.get("PolicyKey")+" completed");
			
		}catch(Exception e){
			logger.error("Unexpected error", e);
			logger.error("Problem in fetching data for Quote Number : " +ctx.get("QuoteNumber") + " and exception is " + e.getMessage());
		}
		
		return null;
	}

	private static String setPercentage(String str){
		return str = str.replace(".00", "");
	}
	
	public static Object removeAmountFormat(Object arg1){
	      if(arg1 == null || "".equals(arg1.toString().trim()))
	          return 0;
	      
	      String amount = arg1.toString();      
	      return amount.replace("$", "").replace(",", "");
	}
	
	private static Object applyAmountFormat(Object obj){
		if(obj != null && !obj.toString().equals("")){
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			String val = nf.format(obj);
			val = val.replace(".00", "");
			return val;
		}else{
			return obj;
		}
	}
	
	
public static Object fetchDataForQuoteRatingPdf(Context ctx) {		
		
		try{
			
			Object objRatingData = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsgetRatingData", ctx);
			if(objRatingData !=null)
			{
				
				ctx.put("RatingData", objRatingData);
			}
			
			Object objFirmLW = SqlResources.getSqlMapProcessor(ctx).findByKey("FirmLW.findByKey", ctx);
			if(objFirmLW != null){
				ctx.put("FirmLW", objFirmLW);
			}
			
			
			Object objRatingTrace = SqlResources.getSqlMapProcessor(ctx).findByKey("RatingTrace.findByKey", ctx);
			if(objRatingTrace != null)
			{
				Map mapRatingTrace = (Map)objRatingTrace;
				if(mapRatingTrace.get("XmlOutputDatafromRating") != null)
				{
					
					String outputString = mapRatingTrace.get("XmlOutputDatafromRating").toString();
					Element root = XMLUtils.parseXMLRootElement(outputString);					
					Element outputElement = root.getChild("PremiumInfoLW");
					Map out = populateOutput( outputElement);
					logger.debug("MTTaxAmmount-------"+out.get("MTTaxAmmount"));
					
					Map outData = calculateTax(out, ctx);
					
					ctx.put("PremiumInfo", outData);
					
				}
				
			}
				
		}
		catch(Exception e){
			logger.error("Unexpected error", e);
			logger.error("Problem in fetching data for Quote Number : " +ctx.get("QuoteNumber"));
		}
		return null;
	}
	
	public static Map populateOutput(Element element)
	{
		StringBuffer buffer = new StringBuffer();    	
		List children = element.getChildren();
		if(children == null)
			return new HashMap();
		Map out = new HashMap();
		for(int i=0; i<children.size(); i++)
	    {
			Element child = (Element) children.get(i);
			String name = child.getName();
			String str = child.getTextTrim();			
			out.put(name, str);      
	    }  	
		
		return out;
	}
	
	public static Map calculateTax(Map out, Context ctx) throws Exception {
		
		
		Object obj = RuleUtils.executeRule(ctx, "LawyersRule.doNotCalculateTax");			
			boolean flag = true;
			if(obj != null && obj instanceof Boolean )
				flag =(Boolean) obj;
			
			double mTCountyTax = 0.0;		
			double finalBaseLimitPrem = 0.0;
			
			
			
			// Skip No
			if (!flag) {
                if (out.get("MTTaxAmmount") != null
                          && !"".equals(out.get("MTTaxAmmount")
                                   .toString())) {
                     mTCountyTax = Double.parseDouble(out.get(
                               "MTTaxAmmount").toString());
                     
                    
                     
                }
           }else
           {
        	   
        	   out.put("MTTaxAmmount",0.0);
           }
			
           if (out.get("CountyTaxAmmount") != null
                     && !"".equals(out.get("CountyTaxAmmount")
                               .toString())) {
                if (mTCountyTax <= 0) {
                     mTCountyTax = mTCountyTax
                               + Double.parseDouble(out.get(
                                        "CountyTaxAmmount").toString());
                     logger.debug("countrytaxamount------"+Double.parseDouble(out.get("CountyTaxAmmount").toString()));
                     
                     out.put("CountyTaxAmmount", Double.parseDouble(out.get("CountyTaxAmmount").toString()));
                }else{
             	   out.put("CountyTaxAmmount", 0.0);
                }
           }

			
			if(out.get("State1TaxAmmount") !=null && !"".equals(out.get("State1TaxAmmount").toString()))
			{
				mTCountyTax = mTCountyTax + Double.parseDouble(out.get("State1TaxAmmount").toString());
				out.put("State1TaxAmmount", Math.round(out.get("State1TaxAmmount")));
			}
			if(out.get("State2TaxAmmount") !=null && !"".equals(out.get("State2TaxAmmount").toString()))
			{
				mTCountyTax = mTCountyTax + Double.parseDouble(out.get("State2TaxAmmount").toString());
				out.put("State2TaxAmmount", Double.parseDouble(out.get("State2TaxAmmount").toString()));
			}
		
			
			out.put("Total_tax", Math.round(mTCountyTax));
			out.put("TotalCoverageTaxAmount", Math.round(mTCountyTax));
			
			double premiumWithoutTax = Double.parseDouble(out.get(
			"ActualFinalBaseLimitPremium").toString());
				double totalTax =  Double.parseDouble(out.get("Total_tax").toString()) ;
				double premiumWithTax = premiumWithoutTax + totalTax;
				
				out.put("FinalBaseLimitPremiumWithTax", premiumWithTax);
						
			
		
		
		return out;
	}

	


	public void downloadDocument(IContext ctx) throws Exception {
		HttpServletRequest req=(HttpServletRequest)ctx.get("DocumentRequest");
		HttpServletResponse res=(HttpServletResponse)ctx.get("DocumentResponse");
		ServletContext servletContext=(ServletContext)ctx.get("DocumentServletContext");
		
		logger.debug("DownloadDocument Start for Document : "
				+ ctx.get("DocFileName"));
		String contentType = servletContext.getMimeType(
				(String) ctx.get("DocFileName"));
		res.setContentType(contentType);
		String newFileName = (String) ctx.get("DocFileName");

		res.setHeader("Content-disposition", "attachment; filename="
				+ newFileName);
		new DocumentManagment().downloadDocument(ctx, req, res);
		logger.debug("DownloadDocument End for Document : "
				+ ctx.get("DocFileName"));
	}	
	
	public static Object  fetchDataForInvoiceDownload(Context ctx) throws Exception{
		try{
			ctx.put("PolicyKey_Invoice", ctx.get("PolicyKey"));
			 new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey_Invoice");
			Object objInvoiceData = SqlResources.getSqlMapProcessor(ctx).select("Account.getInvoicedData", ctx);
			if(objInvoiceData !=null)
			{				
				ctx.put("InvoiceDataLW", objInvoiceData);
			}			
		}
		catch(Exception e){
			logger.error("Unexpected error", e);
			logger.error("Problem in fetching Invoiced  data for Quote Number : " +ctx.get("QuoteNumber"));
		}
		return null;
	}
	public static Object downloadAndPrintOffRiskDocs(IContext ctx)
	{
		
		try {
			OffRiskLawyersUtils offrisk = new OffRiskLawyersUtils();
			offrisk.OffRiskUpload(ctx);
			offrisk.downloadOffRiskLetter(ctx);
		} catch (Exception e) {
			
			logger.error("Unexpected error", e);
		}
		return null;
	}
	
	public static Object fetchPolicyDataInExcel(Context ctx)throws Exception
	{
		HttpServletRequest req=(HttpServletRequest)ctx.get("DocumentRequest");
		HttpServletResponse res=(HttpServletResponse)ctx.get("DocumentResponse");
		ServletContext servletContext=(ServletContext)ctx.get("DocumentServletContext");
		logger.debug("DownloadExcel Start for Quote Number : "+ ctx.get("QuoteNumber"));
		res.setContentType("application/vnd.ms-excel");
		String newFileName = "Lawyers_" + ctx.get("QuoteNumber") + ".xls";
		res.setHeader("Content-disposition", "attachment; filename=\""+ newFileName + "\"");
		new DownloadExcel().downloadExcel(ctx, req, res);
		logger.debug("DownloadExcel End for Quote Number : "+ ctx.get("QuoteNumber"));
		
		return null;
	}
	

public static Object downloadUpcomingEvents(Context ctx) throws Exception {
	
	ServletOutputStream outputStream  = null;
	File file = null;
	byte[] rb = null;
	ByteArrayOutputStream bout = null;
	
	try {
		HttpServletRequest req=(HttpServletRequest)ctx.get("DocumentRequest");
		HttpServletResponse res=(HttpServletResponse)ctx.get("DocumentResponse");
		ServletContext servletContext=(ServletContext)ctx.get("DocumentServletContext");
		String contentType = servletContext.getMimeType("upcomingEvents.pdf");
		res.setContentType(contentType);
		String DocFileName = "upcomingEvents.pdf";
		res.setHeader("Content-disposition","attachment; filename=" + DocFileName);				
		logger.debug("DownloadDocument End for Document : " +DocFileName);		
		outputStream = res.getOutputStream();
		String htmlDir = SystemProperties.getInstance().getString("html.basedir");
		String filePath = htmlDir + "download//" + DocFileName;
		file = new File(filePath);
		rb = DocManagementUtil.getBytesFromFile(file);
		bout = new ByteArrayOutputStream();
		bout.write(rb);
		bout.writeTo(outputStream);
		outputStream.flush();
	}catch(Exception ex){
		logger.error("Unexpected error", ex);
		logger.error("error in downloadUpcominEvents method:::::"+ex.getMessage());
	}finally{
		/*code by sukhi 26/09/2018*/
		if(bout != null){
			bout.close();
			bout = null;
		}
		if(outputStream != null){
			outputStream.close();
			outputStream = null;
		}
		file = null;
		rb = null;
	}
	
	return null;
}
	public static void viewAttornies(Context ctx) 
	{
		try {
			List AttorneysDetailsList_list_01 = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAllAttorneyList",ctx);
			//logger.debug(AttorneysDetailsList_list_01);
			if(AttorneysDetailsList_list_01.size()==0)
			{	HashMap<Object,Object> hm1=null;
				for(int i=1;i<4;i++)
				{
					hm1=new HashMap<Object,Object>();
					hm1.put("insuranceCarrier_"+i, null);
					hm1.put("policyPeriod_"+i, null);
					hm1.put("limitDeductible_"+i, null);
					hm1.put("numberOfAttorneys_"+i, null);
					hm1.put("premium_"+i, null);
					AttorneysDetailsList_list_01.add(hm1);
				}
				
			}
			logger.debug(AttorneysDetailsList_list_01);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		}
		
		/*if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("1") )
		{
			if(allAttornieslist!=null && allAttornieslist instanceof List && allAttornieslist.size()>1)
			{		ruleid="multipleAttorneyAtleastHaveThousandHours_Lawyers";	
					logger.debug("going to debug"+ ruleid);
					int count=0;
					for (int i = 0; i < allAttornieslist.size(); i++) 
					{
						Map dataMap = (Map) allAttornieslist.get(i);
						if(dataMap.get("AnnualWorkedHours")!=null && Integer.valueOf(dataMap.get("AnnualWorkedHours").toString())>=1000 )
							count++;
					}	
					ctx.put("attorneysCountHave1000Hours", count);
					Firm firm=(Firm)initlizePOJO(ctx, new Firm());
					ctx.put("RuleObject",firm);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm,ruleid,firm.getDescription(),firm.getTooltip());
			}*/
		
	}
	
	public static Object fetchDataForPolicyEndorsement(Context ctx) {
		logger.debug("fetchDataForPolicyEndorsement start  ........");
		HttpServletRequest req=(HttpServletRequest)ctx.get("DocumentRequest");
		HttpServletResponse res=(HttpServletResponse)ctx.get("DocumentResponse");
		ServletContext servletContext=(ServletContext)ctx.get("DocumentServletContext");
		logger.debug("View endorsement started");
		List attachments  = new ArrayList();		
		OutputStream out = null;
		File pdfFile = null;
		List listFormID=null;
		String fileName = null, file = null,baseUrl = null, outFile=null;
		
		logger.debug("processEndorementDocument started  ........ ");
		LawyersUtils.getPolicyData((Context)ctx);
		
		if(ctx.get("TransactionTypeKey") == null) {
			logger.error("Endorsement Type for Policy Number : " + ctx.get("PolicyNumber") + " was not defined");
			return null;
		}
		String TransactionTypeKey = ctx.get("TransactionTypeKey").toString();
		
		try {			
			new LawyersUtils().setImagesPathToTemplate(ctx);			
			
//			String TransactionTypeKey = ctx.get("TransactionTypeKey").toString();
			//Populating all keys in Context
			Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesGetPolicyKeys", ctx);
			Map keysMap = null;		
 			if(obj != null && obj instanceof Map){
				keysMap = (Map) obj;
				ctx.putAll(keysMap);
			}
 			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
 			Date todaydt=new Date();
 			String todaydate=sdf.format(todaydt);
 			ctx.put("CurrentDate", todaydate);
 			
 			Object obj1 = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetcancelexpirydate", ctx);
 			if(obj1 != null && obj1 instanceof Map){
				keysMap = (Map) obj1;
				ctx.putAll(keysMap);
			}
			Object objPoilcy = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesGetPolicyEndorsment", ctx);
			ctx.put("policy_freeform_01", objPoilcy);
//			ctx.put("policy_freeform_01", ((Map) objPoilcy).get("CurrentDate"));
			
			Object objCoveragePoilcyNew = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesGetEndorsementCoverage", ctx);
			if(objCoveragePoilcyNew != null)
				ctx.put("PolicyExpirationDate", ((Map) objCoveragePoilcyNew).get("PolicyExpirationDate"));	
			
			Object objQuote = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetCalculateLimitDeductiblePremium", ctx);
			if(objQuote != null)
			{
				ctx.put("ProrataPremium", ((Map) objQuote).get("ProrataPremium"));
				ctx.put("PremiumVariation", ((Map) objQuote).get("PremiumVariation"));
				ctx.put("TaxVariation", ((Map) objQuote).get("TaxVariation"));
			}
			new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey");
			List objectList  =(List) SqlResources.getSqlMapProcessor(ctx).select("AttorneyDetailsLW.GetAttorneyData_p",ctx);
			ctx.put("ListOfAttorney_list_01", objectList);
			ctx.put("ListOfAttorney_list_101", objectList);
			Map map = new HashMap();
			List list = new ArrayList();
			list.add(map);
			
			Map mapEnd = new HashMap();
			List listEnd = new ArrayList();
			listEnd.add(mapEnd);
			Map mapEndNew = new HashMap();
			List listEndNew = new ArrayList();
			listEndNew.add(mapEndNew);		
			if(TransactionTypeKey.equals(LawyersConstants.CANCELLATION_ENDORSEMENT)){
				//ctx.put("endorsementname", "CancellationEndorsement");				
				if(ctx.get("cancellationReason") != null)
					map.put("CancellationReasonID", ctx.get("cancellationReason").toString());									
				if(ctx.get("CancellationEffectiveDate") != null)
					map.put("TransactionEffectiveDate", ctx.get("CancellationEffectiveDate").toString());
				if(ctx.get("cancellationTotalReturnPremium") != null)
					map.put("InvoicedPremium", LawyersUtilities.amountFormatLawyers(ctx.get("cancellationTotalReturnPremium")));
				ctx.put("endorsement_freeform_01",list);
			} 
			if(TransactionTypeKey.equals(LawyersConstants.REINSTATEMENT_ENDORSEMENT)){
				//ctx.put("endorsementname", "ReinstatmentEndorsement");				
				if(ctx.get("ReinstatementEffectiveDate") != null)
					map.put("TransactionEffectiveDate", ctx.get("ReinstatementEffectiveDate").toString());
				if(ctx.get("reinstatementReturnPremium") != null)
					map.put("InvoicedPremium", LawyersUtilities.amountFormatLawyers(ctx.get("reinstatementReturnPremium")));
				ctx.put("endorsement_freeform_01",list);
			} 
			if(TransactionTypeKey.equals(LawyersConstants.LIMIT_ENDORSEMENT)){
				//ctx.put("endorsementname", "LimitEndorsement");
				ctx.put("TypeOfEndorsement","9");
				if(ctx.get("AggregateLimit") != null)
					map.put("AggregateLimitText", LawyersUtilities.amountFormatLawyers(ctx.get("AggregateLimit")));
				if(ctx.get("OccuranceLimit") != null)
					map.put("OccuranceLimitText", LawyersUtilities.amountFormatLawyers(ctx.get("OccuranceLimit")));	
				/*if(ctx.get("litmitsEndorsementTotalPremium") != null){
					map.put("InvoicedPremium", LawyersUtilities.amountFormatLawyers(ctx.get("litmitsEndorsementTotalPremium")));
					ctx.put("NewInvoicedPremium", LawyersUtilities.amountFormatLawyers(ctx.get("litmitsEndorsementTotalPremium")));
				}*/
				if(ctx.get("ProrataPremium") != null){
					map.put("InvoicedPremium", LawyersUtilities.amountFormatLawyers(ctx.get("ProrataPremium")));
					ctx.put("NewInvoicedPremium", ctx.get("ProrataPremium"));
				}
				// Need LimitSequence, IsClaimExpenseType, IsDollarDefense				
				if(ctx.get("LimitSequence") != null)
					map.put("LimitSequence", ctx.get("LimitSequence").toString());
				if(ctx.get("IsClaimExpenseType") != null)
					map.put("IsClaimExpensesType", ctx.get("IsClaimExpenseType").toString());
				if(ctx.get("IsDollarDefense") != null)
					map.put("IsDollarDefense", ctx.get("IsDollarDefense").toString());
				
				if(ctx.get("limitEndorsementEffectiveDate") != null)
					map.put("TransactionEffectiveDate", ctx.get("limitEndorsementEffectiveDate").toString());
				if(ctx.get("PolicyExpirationDate") != null)
					map.put("PolicyExpirationDate", ctx.get("PolicyExpirationDate").toString());
				
				ctx.put("policycoverage_freeform_01",list);
				ctx.put("endorsement_freeform_01",listEnd);				
			}
			 if(TransactionTypeKey.equals(LawyersConstants.DEDUCTIBLE_ENDORSEMENT)){
				//ctx.put("endorsementname", "DeductibleEndorsement");
				ctx.put("TypeOfEndorsement","10");
				if(ctx.get("AggregateDeductible") != null)
					map.put("DeductibleAmount", LawyersUtilities.amountFormatLawyers(ctx.get("AggregateDeductible")));
				/*if(ctx.get("deductibleEndorsementPremium") != null){					
					ctx.put("NewInvoicedPremium", LawyersUtilities.amountFormatLawyers(ctx.get("deductibleEndorsementPremium")));
				}*/
				if(ctx.get("ProrataPremium") != null){					
					ctx.put("InvoicedPremium", LawyersUtilities.amountFormatLawyers(ctx.get("ProrataPremium")));
					ctx.put("NewInvoicedPremium", ctx.get("ProrataPremium"));
				}
				// Need DeductibleSequence, IsClaimOptionType				
				if(ctx.get("DeductibleSequence") != null)
					map.put("DeductibleSequence", ctx.get("DeductibleSequence").toString());
				if(ctx.get("IsClaimOptionType") != null)
					map.put("IsClaimOptionType", ctx.get("IsClaimOptionType").toString());
				
				if(ctx.get("deductibleEndorsementEffectiveDate") != null)
					map.put("TransactionEffectiveDate", ctx.get("deductibleEndorsementEffectiveDate"));
				if(ctx.get("PolicyExpirationDate") != null)
					map.put("PolicyExpirationDate", ctx.get("PolicyExpirationDate").toString());
				
				ctx.put("policycoverage_freeform_01",list);
				ctx.put("endorsement_freeform_01",listEnd);
			}
			 if(TransactionTypeKey.equals(LawyersConstants.NAMEADDRESS_ENDORSEMENT)){
				//ctx.put("endorsementname", "ReinstatmentEndorsement");	
				ctx.put("TypeOfEndorsement","11");
				Map policyMap=(Map)ctx.get("policy_freeform_01");
				
				if(ctx.get("nameAddressEndorsementDate") != null)
				{
					mapEnd.put("TransactionEffectiveDate", ctx.get("nameAddressEndorsementDate").toString());
					ctx.put("endorsement_freeform_01",listEnd);
				}if(ctx.get("nameAddressEndorsementAccountName") != null)
					policyMap.put("AccountName", ctx.get("nameAddressEndorsementAccountName").toString());
				if(ctx.get("nameAddressEndorsementAddress1") != null)
					policyMap.put("Address1", ctx.get("nameAddressEndorsementAddress1").toString());
				if(ctx.get("nameAddressEndorsementCity") != null)
					policyMap.put("City", ctx.get("nameAddressEndorsementCity").toString());
				if(ctx.get("nameAddressEndorsementZipCode") != null)
				{
					String zipCode=ctx.get("nameAddressEndorsementZipCode").toString();
					if(zipCode.length()<7)
						zipCode=zipCode.replace("-", "");
						policyMap.put("Zip", zipCode);
				
				}
					/*if(ctx.get("nameAddressEndorsementCity") != null)
					map.put("City", ctx.get("nameAddressEndorsementCity").toString());
				ctx.put("policy_freeform_01",list);*/
				if(ctx.get("nameAddressEndorsementDate") != null)
					map.put("TransactionEffectiveDate", ctx.get("nameAddressEndorsementDate"));
				map.put("PolicyExpirationDate", ctx.get("PolicyExpirationDate"));
				
				ctx.put("policycoverage_freeform_01",list);
				ctx.put("policy_freeform_01", policyMap);
			}
			if(TransactionTypeKey.equals(LawyersConstants.PRIORACTDATE_ENDORSEMENT))
			{
				if("Y".equals(ctx.get("isEnforcedPolicy").toString()))
				{
					if(ctx.get("ProrataPremium") != null){
						map.put("InvoicedPremium", LawyersUtilities.amountFormatLawyers(ctx.get("ProrataPremium")));
						ctx.put("NewInvoicedPremium", ctx.get("ProrataPremium"));
					}
				}
				else
				{
					ctx.put("NewInvoicedPremium", "0.0");
				}
				ctx.put("TypeOfEndorsement","12");
				String isPremiumCalculated=ctx.get("premiumCalulated")!=null && !ctx.get("premiumCalulated").equals(HtmlConstants.EMPTY) ?ctx.get("premiumCalulated").toString():"N";
				
				ctx.put("isPremiumCalculated",isPremiumCalculated);
				
				if(ctx.get("priorActEndorsementDate") != null)
					map.put("TransactionEffectiveDate", ctx.get("priorActEndorsementDate"));
				map.put("PolicyExpirationDate", ctx.get("PolicyExpirationDate"));
				
				ctx.put("policycoverage_freeform_01",list);
				//if(ctx.get())
				Map objPriorActDetail = (Map) SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfquotelettergetProfessionalLiabilityInsDetail", ctx);
				//ctx.put("professionalliabilityinsdetail_freeform_01", objPriorActDetail);
				//List dataList=new ArrayList();
			//	Map map1=(Map) objPriorActDetail.get(0);
				
				objPriorActDetail.put("PriorActDatePross", ctx.get("priorActEndorsementPriorActDate"));
				//((List) objPriorActDetail).add(map1);
				ctx.put("professionalliabilityinsdetail_freeform_01",objPriorActDetail);
			}
			if(TransactionTypeKey.equals(LawyersConstants.POLICYEXTENSION_ENDORSEMENT))
			{	
				//policy_freeform_01
				if(ctx.get("objCoveragePoilcyNew")==null)
				{	
					if(ctx.get("policyExtendEndorsementEffectiveDate") != null)
						map.put("TransactionEffectiveDate", ctx.get("policyExtendEndorsementEffectiveDate").toString());
					if(ctx.get("policyExtendExpirationDate") != null)
						map.put("PolicyExpirationDate", ctx.get("policyExtendExpirationDate").toString());
					ctx.put("policycoverage_freeform_01",list);
				}
				
				
				if(ctx.get("ProrataPremium") != null){
					map.put("InvoicedPremium", LawyersUtilities.amountFormatLawyers(ctx.get("ProrataPremium")));
					ctx.put("NewInvoicedPremium", ctx.get("ProrataPremium"));
				}
				ctx.put("TypeOfEndorsement","13");
			}
			
			if(TransactionTypeKey.equals(LawyersConstants.ADDCHANGEATTORNEY_ENDORSEMENT) || TransactionTypeKey.equals(LawyersConstants.DELETEATTORNEY_ENDORSEMENT))
			{	
				//policy_freeform_01
				
				if(ctx.get("objCoveragePoilcyNew")==null)
				{	
					if(ctx.get("endorsementEffectiveDate") != null)
						map.put("TransactionEffectiveDate", ctx.get("endorsementEffectiveDate").toString());
					
					ctx.put("policycoverage_freeform_01",list);
					
					List obj10=(List)ctx.get("AttorneysDetailsList_list_01");
					if(obj10!=null)
					{
					Map map1=new HashMap();
					for(int i=0;i<obj10.size();i++)
					{
						map1=(HashMap)obj10.get(i);
						map1.put("TransactionEffectiveDate",ctx.get("endorsementEffectiveDate"));
						map1.put("EffectiveDateOfPolicy",ctx.get("PolicyEffectiveDate"));
						if(map1.get("AttorneyKey")==null)
						{
							map1.put("AttorneyName", ctx.get("AttorneyName_"+i));
							map1.put("DesignationId", ctx.get("DesignationId_"+i));
							map1.put("AnnualWorkedHours", ctx.get("AnnualWorkedHours_"+i));
							map1.put("AttorneyPriorActDate", ctx.get("AttorneyPriorActDate_"+i));
							map1.put("NumberOfYearsInPractice", ctx.get("NumberOfYearsInPractice_"+i));
						
						}
					}
					}
					
					
				}
				if(TransactionTypeKey.equals(LawyersConstants.DELETEATTORNEY_ENDORSEMENT))
				{
					ctx.put("TypeOfEndorsement","14");
					ArrayList additionalAttorneys=new ArrayList();
					
					 if(ctx.get("deletedAttorneys") != null){
                         StringTokenizer tokens = new StringTokenizer(ctx.get("deletedAttorneys").toString(), ",");
                   
                         String newTokens = null;
							while (tokens.hasMoreTokens()) {
								String token = tokens.nextToken().trim();

								List obj10 = (List) ctx.get("ListOfAttorney_list_01");
								if (obj10 != null) {
									Map map1 = new HashMap();
									for (int i = 0; i < obj10.size(); i++) {
										map1 = (HashMap) obj10.get(i);
										if (map1.get("AttorneyKey") != null && map1.get("AttorneyKey").toString()
												.equals(token)) {
											map1.put("TransactionEffectiveDate", ctx.get("endorsementEffectiveDate"));
											map1.put("EffectiveDateOfPolicy", ctx.get("PolicyEffectiveDate"));
											additionalAttorneys.add(map1);

										}
									}
								}

							}
                 
                 
               }
					
					ctx.put("ListOfAttorneys_list_01",additionalAttorneys);
			}
				if(TransactionTypeKey.equals(LawyersConstants.ADDCHANGEATTORNEY_ENDORSEMENT) )
				{ 
					
						ctx.put("ListOfAttorneys_list_01",new LawyersUtils().manageAttoneyEndorsementChanges(ctx));
				}
			}
			new DownloadForm().processEndorsementData(ctx);
			
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
				fileName =file+"_DEFAULT";
			
			ctx.put("endorsementname", fileName);
			outFile = SystemProperties.getInstance().getString("html.basedir") + "data//" + fileName +".pdf";		
			pdfFile = new File(outFile);
			out = new java.io.FileOutputStream(pdfFile);
			out = new java.io.BufferedOutputStream(out);			
			if(ctx.get("baseUrl") != null)
				baseUrl = ctx.get("baseUrl").toString();

			if(ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT) != null)
				baseUrl = "file:///" + ((ServletContext)ctx.get(HtmlConstants.DOCUMENTSERVLETCONTEXT)).getRealPath("/");
			
			ServletContextURIResolver uriResolver = null;
			
			if(ctx.get("DocumentUriResolver") != null)
				uriResolver = (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
			
			new DownloadForm().generateEndorsement((Context)ctx, out, baseUrl, uriResolver);
			out.close();
			out = null;
			LawyersUtils.populateLastUpdateTimeStamp(ctx);
			new DownloadExcel().downloadEndorsementPdf(ctx, req, res,outFile,fileName);
			/*String foFile = SystemProperties.getInstance().getString("html.basedir") + "foxsl2017//wrapendorsement.xsl";
			String quoteNumber=ctx.get("QuoteNumber").toString();
			String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//Endorsement_Form_"+quoteNumber+".pdf";		
			File pdfFile = new File(outFile);
			OutputStream out = new java.io.FileOutputStream(pdfFile);
			out = new java.io.BufferedOutputStream(out);
			new XML2PDF().convertPOToPDF(foFile, new StringBuffer(new DownloadForm().generateDataXml(ctx)), outFile);
			*/
		} catch (Exception e) {
			logger.error("Problem in fetching data for Policy Number : "	+ ctx.get("PolicyNumber") + " " + e.getMessage());
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					logger.error("Problem closing endorsement PDF output stream for Policy Number : " + ctx.get("PolicyNumber") + " " + e.getMessage());
				}
			}
		}
		logger.debug("fetchDataForPolicyEndorsement end  ........");		
		return null;
	}
	
	public static void ImageDownld(String fileURL){
		
		ByteArrayOutputStream bout = null;
		File filedlt = null;
		FileOutputStream outputStream = null;
		
		try {
			String filePath=SystemProperties.getInstance().getString("appl.LawyersIns.subproducerlogopath");
			filedlt = new File(filePath+ "LogoImage.png");
			
			if(filedlt.exists()){
				filedlt.delete();
			}
			
			String fileName = filePath + fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
			outputStream = new FileOutputStream(new File(fileName));
			
			String userName = (new DocumentManagment()).getUserName();
			String password = (new DocumentManagment()).getUserPassword();
			String domain = (new DocumentManagment()).getDomainName();
			String baseDir = (new DocumentManagment()).getSharePointBaseDirectory();
	
			byte[] barr = new DocManagementUtil().downloadDocFromSharePoint(fileURL,
					userName, password, domain);
	
			bout = new ByteArrayOutputStream();
			bout.write(barr);
			bout.writeTo(outputStream);
			
			outputStream.flush();
			
			logger.debug("File downloaded");
	        String inputImage = fileName;
	        String oututImage = filePath + "LogoImage.png";
	        String formatName = "PNG";
	        try {
	            boolean result = convertFormat(inputImage,oututImage, formatName);
	            if (result) {
	                logger.debug("Image converted successfully.");
	                logger.debug("Image converted successfully1.");
	            } else {
	                logger.debug("Could not convert image.");
	                logger.debug("Could not convert image1.");
	            }
	        } catch (IOException ex) {
	            logger.debug("Error during converting image.");
	            logger.error("Error during converting image1."	+ ex.getMessage());
	            logger.error("Unexpected error", ex);
	        }
	        
	       
		}catch(Exception e){
			logger.debug("Error in ImageDownload For QuoteLetter");
			logger.error("Error in ImageDownload For QuoteLetter1"	+ e.getMessage());
			logger.error("Unexpected error", e);
		}finally{
			/*code by sukhi 26/09/2018*/
			try {
			if(bout != null){
				bout.close();
				bout = null;
			}
			if(outputStream != null){
				outputStream.close();
				outputStream = null;
			}
			 filedlt=null;
			}catch(Exception ex){
				logger.error("Unexpected error", ex);
				logger.error("Error in ImageDownload For closing connection 1"	+ ex.getMessage());
			}
			
		}
	}
	public static boolean convertFormat(String inputImagePath,
            String outputImagePath, String formatName) throws IOException {
		boolean result= false;
		
		try {
			try (FileInputStream inputStream = new FileInputStream(inputImagePath);
					FileOutputStream outputStream = new FileOutputStream(outputImagePath)) {
				BufferedImage inputImage = ImageIO.read(inputStream);
				result = ImageIO.write(inputImage, formatName, outputStream);
			}

			BufferedImage image = ImageIO.read(new File(outputImagePath));
	        BufferedImage resized = resizeImg(image, 75, 300);
	        ImageIO.write(resized, "png", new File(outputImagePath));
        
        }catch(Exception ex){
        	logger.error("Unexpected error", ex);
        	logger.error("Error during converting format image1."	+ ex.getMessage());
        }
         
        return result;
    }
	
	public static void setDataForInsurenceCertificate(Context ctx)
	{
		try {
			List dataList = (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementpdfgetInsuranceCertificateData",ctx);
			ctx.putAll((Map) dataList.get(0));
			logger.debug("certificate data successfully fetched from database and populated in context");
			/*LawyersUtils.downloadInsurenceCertificate((IContext)ctx);*/
		} catch(Exception e) {
			logger.debug("exception in generating Insurance certificate");
			logger.error("Unexpected error", e);
		}
	}
	
	public static String imageDownload(String fileURL){
		String filePath="",oututImage="";
		File filedlt = null;
		FileOutputStream outputStream = null;;
		ByteArrayOutputStream bout = null;
		
		try {
			filePath=SystemProperties.getInstance().getString("appl.LawyersIns.subproducerlogopath");
			filedlt=new File(filePath+ "LogoImage.png");
			
			if(filedlt.exists()){
				filedlt.delete();
			}
		
			String fileName = filePath + fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
			outputStream = new FileOutputStream(new File(fileName));
			
			String userName = (new DocumentManagment()).getUserName();
			String password = (new DocumentManagment()).getUserPassword();
			String domain = (new DocumentManagment()).getDomainName();
			String baseDir = (new DocumentManagment()).getSharePointBaseDirectory();
	
			byte[] barr = new DocManagementUtil().downloadDocFromSharePoint(fileURL,
					userName, password, domain);
	
			bout = new ByteArrayOutputStream();
			bout.write(barr);
			bout.writeTo(outputStream);
			
			outputStream.flush();
			
		    logger.debug("File downloaded");
	        String inputImage = fileName;
	        oututImage = filePath + "LogoImage.png";
	        String formatName = "PNG";
	        
	        try {
	            boolean result = convertFormatSig(inputImage,oututImage, formatName);
	            if (result) {
	                logger.debug("Image converted successfully.");
	                logger.debug("Image converted successfully.");
	            } else {
	                logger.debug("Could not convert image.");
	                logger.debug("Could not convert image.");
	            }
	        } catch (IOException ex) {
	            logger.debug("Error during converting image.");
	            logger.error("Error during converting image."+ex.getMessage());
	            logger.error("Unexpected error", ex);
	      }
		}catch(Exception e){
			logger.debug("Error in ImageDownload For QuoteLetter");
			logger.error("Error in ImageDownload For QuoteLetter"	+ e.getMessage());
			logger.error("Unexpected error", e);
		}finally{
			/*code by sukhi 26/09/2018*/
			try {
				if(bout != null){
					bout.close();
					bout = null;
				}
				
				if(outputStream != null){
					outputStream.close();
					outputStream = null;
				}
				
				filedlt=null;
			}catch(Exception e){
				logger.error("Unexpected error", e);
				logger.error("Error in ImageDownload For closing connection"	+ e.getMessage());
			}
		 }
		return oututImage;
	}
	
	public static boolean convertFormatSig(String inputImagePath,
            String outputImagePath, String formatName) throws IOException {
		boolean result;
		try (FileInputStream inputStream = new FileInputStream(inputImagePath);
				FileOutputStream outputStream = new FileOutputStream(outputImagePath)) {
			BufferedImage inputImage = ImageIO.read(inputStream);
			result = ImageIO.write(inputImage, formatName, outputStream);
		}
        
        try {
			BufferedImage image = ImageIO.read(new File(outputImagePath));
	        BufferedImage resized = resizeImgSig(image,68, 168);
	        ImageIO.write(resized, "png", new File(outputImagePath));
        
		}catch(Exception ex) {
        	logger.error("Unexpected error", ex);
        	logger.error("Error during converting format image."+ex.getMessage());
        }
        return result;
    }

public static BufferedImage resizeImg(BufferedImage img, int height, int width) {
    Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = resized.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();
    return resized;
}

public static BufferedImage resizeImgSig(BufferedImage img, int height, int width) {
    Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = resized.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();
    return resized;
}

public static Object fetchDataEndorsementCalPdf(Context ctx) {		
	
	try{
		
		Object objRatingData = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsgetRatingData", ctx);
		if(objRatingData !=null)
		{
			
			ctx.put("RatingData", objRatingData);
		}
		
		Object objFirmLW = SqlResources.getSqlMapProcessor(ctx).findByKey("FirmLW.findByKey", ctx);
		if(objFirmLW != null){
			ctx.put("FirmLW", objFirmLW);
		}
		
		/*
		Object objRatingTrace = SqlResources.getSqlMapProcessor(ctx).findByKey("RatingTrace.findByKey", ctx);
		if(objRatingTrace != null)
		{
			Map mapRatingTrace = (Map)objRatingTrace;
			if(mapRatingTrace.get("XmlOutputDatafromRating") != null)
			{*/
				
				String outputString = ctx.get("endorsementDataList").toString();
				Element root = XMLUtils.parseXMLRootElement(outputString);					
				Element outputElement = root.getChild("Root1");
				Map out = populateOutput( outputElement);
				/*logger.debug("MTTaxAmmount-------"+out.get("MTTaxAmmount"));
				
				Map outData = calculateTax(out, ctx);
				*/
				ctx.put("PremiumInfo", out);
				
		/*	}
			
		}*/
			
	}
	catch(Exception e){
		logger.error("Unexpected error", e);
		logger.error("Problem in fetching data for Quote Number : " +ctx.get("QuoteNumber"));
	}
	return null;
}
	public static Object fetchDataInExcel(Context ctx)throws Exception
	{
		HttpServletRequest req=(HttpServletRequest)ctx.get("DocumentRequest");
		HttpServletResponse res=(HttpServletResponse)ctx.get("DocumentResponse");
		ServletContext servletContext=(ServletContext)ctx.get("DocumentServletContext");
		logger.debug("DownloadExcel Start for excel");
		res.setContentType("application/vnd.ms-excel");
		String newFileName = "Testdatasheet.xlsx";
		res.setHeader("Content-disposition", "attachment; filename=\""+ newFileName + "\"");
		new DownloadExcel().downloadAutomationExcel(ctx, req, res);
		logger.debug("DownloadExcel End for Quote Number : "+ ctx.get("QuoteNumber"));
		
		return null;
	}

	public static Object downloadBulkAppsDocument(IContext ctx)
	{
		File file = null;
		try {
			String uploaddirectorypath = SystemProperties.getInstance().getString("application.Document.uploaddirectory");
			file = new File(uploaddirectorypath);
			if (!file.exists())
				file.mkdir();
			OffRiskLawyersUtils offrisk = new OffRiskLawyersUtils();
			offrisk.getBulkApps(ctx,uploaddirectorypath);
			offrisk.downloadApplicationDocument(ctx,uploaddirectorypath);
		} catch (Exception e) {
			
			logger.error("Unexpected error", e);
		}
		return null;
	}
	
	public static Object fetchDataInPdf(Context ctx)throws Exception
	{
		
		HttpServletRequest req=(HttpServletRequest)ctx.get("DocumentRequest");
		HttpServletResponse res=(HttpServletResponse)ctx.get("DocumentResponse");
		ServletContext servletContext=(ServletContext)ctx.get("DocumentServletContext");
		logger.debug("DownloadExcel Start for PDF");
		res.setContentType("application/vnd.ms-excel");
		res.setHeader("Content-disposition", "attachment; filename=\"Policy Form_"+ ctx.get("QuoteNumber").toString() + "\"");
		
		logger.debug("processDocumentManagment started  ........ " + ctx.get("QuoteNumber"));
		boolean issuePolicy=false,IsManualPremium=false,alreadyIssuePolicy=false;
 		String policyNumber ="";
		List attachments  = new ArrayList();		
		OutputStream out = null;
		File pdfFile = null;
		//PolicyNumber generation and Update
		
		
		String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//Policy Form_" + ctx.get("QuoteNumber").toString() + ".pdf";		
		pdfFile = new File(outFile);
		out = new java.io.FileOutputStream(pdfFile);
		out = new java.io.BufferedOutputStream(out);
		List listFormID = LawyersUtils.populateFinalisedPolicyForm(ctx);
		
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
		
		new DownloadExcel().downloadPolicyPdf(ctx, req, res);
		
			result = "File added successfully!";
		
		logger.debug("Result from uploadFormDocument(ctx) in processDocumentManagment " + result);
		
		if(result == null || (result != null && !result.equals("File added successfully!"))){
			LawyersUtils.populateError(ctx, "DocUploadError", "Policy could not be issued");
			
			return ERROR_MESSAGE + "DocUploadError";
		}
		return SUCCESS_MESSAGE;
	}
	
	public static void fetchFillableApplicationPdf(Context ctx)throws Exception
	{
		HttpServletResponse res=(HttpServletResponse)ctx.get("DocumentResponse");
		String quoteNumber = ctx.get("QuoteNumber").toString();
		String fileName = "ApplicationForm_" + quoteNumber + ".pdf";
		String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//" + fileName;		
		File pdfFile = null;
		FileInputStream inputStream = null;
		OutputStream outputStream = null;

		logger.debug("Fillable application PDF download started for Quote Number : " + quoteNumber);
		pdfFile = new File(outFile);
		logger.debug("going to generate pdf.");
		fetchDataForApplicationPDF(ctx);
		int policyStatuskey=ctx.get("PolicyStatusKey")!=null && !ctx.get("PolicyStatusKey").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("PolicyStatusKey").toString()):99;
	if(policyStatuskey==1)
		GeneratePDFFormNew.getFillablePDFDoc(ctx,outFile);
	else
		GenerateRenewalApplicationAcroForm.generateFillablePdf(ctx,outFile);
	
		if(!pdfFile.exists() || pdfFile.length() == 0) {
			throw new IOException("Fillable application PDF was not generated: " + outFile);
		}

		res.setContentType("application/pdf");
		res.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
		res.setContentLength((int)pdfFile.length());

		try {
			inputStream = new FileInputStream(pdfFile);
			outputStream = res.getOutputStream();
			byte[] buffer = new byte[4096];
			int length;
			while ((length = inputStream.read(buffer)) > 0){
				outputStream.write(buffer, 0, length);
			}
			outputStream.flush();
			logger.debug("Fillable application PDF download completed for Quote Number : " + quoteNumber);
		} finally {
			if(inputStream != null) {
				inputStream.close();
			}
		}
	}
	
}
