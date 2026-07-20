package com.excel.downloadexcel;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import com.excel.oldpim.ExcelUtility;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;

public class Title {
	public void getDataForTitle(Context ctx) throws Exception {
		Object objTitle = SqlResources.getSqlMapProcessor(ctx).findByKey("TitleSuppLW.findByKey", ctx);	
		ctx.put(Constants.TITLE_FREEFORM_01, objTitle);
	
	}
	
	public void populateTitleData(Context ctx, HSSFWorkbook wb) throws Exception{
		Sheet sheet = wb.getSheet("Title");
        Object objTitle = ctx.get(Constants.TITLE_FREEFORM_01);
		Map titleMap = null;
		if (objTitle != null)
			titleMap = (HashMap) objTitle;
	
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
	
		if(titleMap != null){
			String IsApplMaintEquityInTitle = ExcelUtility.getValueFromMap(titleMap, Constants.IsApplMaintEquityInTitle, false);
			if(IsApplMaintEquityInTitle != null && IsApplMaintEquityInTitle.equals("Y")){
				IsApplMaintEquityInTitle = "Yes";
			}else if(IsApplMaintEquityInTitle != null && IsApplMaintEquityInTitle.equals("N")){
				IsApplMaintEquityInTitle = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplMaintEquityInTitle, excelFieldMapping.get(Constants.IsApplMaintEquityInTitle), 0);
			
			String PercentageOfEquityInt = ExcelUtility.getValueFromMap(titleMap, Constants.PercentageOfEquityInt, false);
			PercentageOfEquityInt = PercentageOfEquityInt + "%";
			ExcelUtility.setCellValue(sheet, PercentageOfEquityInt, excelFieldMapping.get(Constants.PercentageOfEquityInt), 0);
	
			String IsTitleHaveProfLiabCoverage = ExcelUtility.getValueFromMap(titleMap, Constants.IsTitleHaveProfLiabCoverage, false);
			if(IsTitleHaveProfLiabCoverage != null && IsTitleHaveProfLiabCoverage.equals("Y")){
				IsTitleHaveProfLiabCoverage = "Yes";
			}else if(IsTitleHaveProfLiabCoverage != null && IsTitleHaveProfLiabCoverage.equals("N")){
				IsTitleHaveProfLiabCoverage = "No";
			}
			ExcelUtility.setCellValue(sheet, IsTitleHaveProfLiabCoverage, excelFieldMapping.get(Constants.IsTitleHaveProfLiabCoverage), 0);
			
			String IsApplCovProfLiabPolicy = ExcelUtility.getValueFromMap(titleMap, Constants.IsApplCovProfLiabPolicy, false);
			if(IsApplCovProfLiabPolicy != null && IsApplCovProfLiabPolicy.equals("Y")){
				IsApplCovProfLiabPolicy = "Yes";
			}else if(IsApplCovProfLiabPolicy != null && IsApplCovProfLiabPolicy.equals("N")){
				IsApplCovProfLiabPolicy = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplCovProfLiabPolicy, excelFieldMapping.get(Constants.IsApplCovProfLiabPolicy), 0);
			
			String IsTitleInsAgencyIssuePolicy = ExcelUtility.getValueFromMap(titleMap, Constants.IsTitleInsAgencyIssuePolicy, false);
			if(IsTitleInsAgencyIssuePolicy != null && IsTitleInsAgencyIssuePolicy.equals("Y")){
				IsTitleInsAgencyIssuePolicy = "Yes";
			}else if(IsTitleInsAgencyIssuePolicy != null && IsTitleInsAgencyIssuePolicy.equals("N")){
				IsTitleInsAgencyIssuePolicy = "No";
			}
			ExcelUtility.setCellValue(sheet, IsTitleInsAgencyIssuePolicy, excelFieldMapping.get(Constants.IsTitleInsAgencyIssuePolicy), 0);
			
			String NumberOfTitleAttInFirm = ExcelUtility.getValueFromMap(titleMap, Constants.NumberOfTitleAttInFirm, false);
			ExcelUtility.setCellValue(sheet, NumberOfTitleAttInFirm, excelFieldMapping.get(Constants.NumberOfTitleAttInFirm), 0);
	
			String NumberOfTitleNonAttInFirm = ExcelUtility.getValueFromMap(titleMap, Constants.NumberOfTitleNonAttInFirm, false);
			ExcelUtility.setCellValue(sheet, NumberOfTitleNonAttInFirm, excelFieldMapping.get(Constants.NumberOfTitleNonAttInFirm), 0);
	
			String NumberOfTitleAttNotInFirm = ExcelUtility.getValueFromMap(titleMap, Constants.NumberOfTitleAttNotInFirm, false);
			ExcelUtility.setCellValue(sheet, NumberOfTitleAttNotInFirm, excelFieldMapping.get(Constants.NumberOfTitleAttNotInFirm), 0);
	
			String NumberOfTitleNonAttSubCont = ExcelUtility.getValueFromMap(titleMap, Constants.NumberOfTitleNonAttSubCont, false);
			ExcelUtility.setCellValue(sheet, NumberOfTitleNonAttSubCont, excelFieldMapping.get(Constants.NumberOfTitleNonAttSubCont), 0);
	
			String IsApplObtCertOfInsOfTitle = ExcelUtility.getValueFromMap(titleMap, Constants.IsApplObtCertOfInsOfTitle, false);
			if(IsApplObtCertOfInsOfTitle != null && IsApplObtCertOfInsOfTitle.equals("Y")){
				IsApplObtCertOfInsOfTitle = "Yes";
			}else if(IsApplObtCertOfInsOfTitle != null && IsApplObtCertOfInsOfTitle.equals("N")){
				IsApplObtCertOfInsOfTitle = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplObtCertOfInsOfTitle, excelFieldMapping.get(Constants.IsApplObtCertOfInsOfTitle), 0);
			
			String NumberOfRealEstTitleIns = ExcelUtility.getValueFromMap(titleMap, Constants.NumberOfRealEstTitleIns, false);
			ExcelUtility.setCellValue(sheet, NumberOfRealEstTitleIns, excelFieldMapping.get(Constants.NumberOfRealEstTitleIns), 0);
			
			String PercentOfTitleFromResi = ExcelUtility.getValueFromMap(titleMap, Constants.PercentOfTitleFromResi, false);
			PercentOfTitleFromResi = PercentOfTitleFromResi + "%";
			ExcelUtility.setCellValue(sheet, PercentOfTitleFromResi, excelFieldMapping.get(Constants.PercentOfTitleFromResi), 0);
			
			String PercentOfTitleFromAgri = ExcelUtility.getValueFromMap(titleMap, Constants.PercentOfTitleFromAgri, false);
			PercentOfTitleFromAgri = PercentOfTitleFromAgri + "%";
			ExcelUtility.setCellValue(sheet, PercentOfTitleFromAgri, excelFieldMapping.get(Constants.PercentOfTitleFromAgri), 0);
			
			String PercentOfTitleFromComm = ExcelUtility.getValueFromMap(titleMap, Constants.PercentOfTitleFromComm, false);
			PercentOfTitleFromComm = PercentOfTitleFromComm + "%";
			ExcelUtility.setCellValue(sheet, PercentOfTitleFromComm, excelFieldMapping.get(Constants.PercentOfTitleFromComm), 0);
			
			String PercentOfTitleFromOther = ExcelUtility.getValueFromMap(titleMap, Constants.PercentOfTitleFromOther, false);
			PercentOfTitleFromOther = PercentOfTitleFromOther + "%";
			ExcelUtility.setCellValue(sheet, PercentOfTitleFromOther, excelFieldMapping.get(Constants.PercentOfTitleFromOther), 0);
			
			String IsFirmUseEngageLetterOfTitle = ExcelUtility.getValueFromMap(titleMap, Constants.IsFirmUseEngageLetterOfTitle, false);
			if(IsFirmUseEngageLetterOfTitle != null && IsFirmUseEngageLetterOfTitle.equals("Y")){
				IsFirmUseEngageLetterOfTitle = "Yes";
			}else if(IsFirmUseEngageLetterOfTitle != null && IsFirmUseEngageLetterOfTitle.equals("N")){
				IsFirmUseEngageLetterOfTitle = "No";
			}
			ExcelUtility.setCellValue(sheet, IsFirmUseEngageLetterOfTitle, excelFieldMapping.get(Constants.IsFirmUseEngageLetterOfTitle), 0);
			
			String NumberOfTitleIssued = ExcelUtility.getValueFromMap(titleMap, Constants.NumberOfTitleIssued, false);
			ExcelUtility.setCellValue(sheet, NumberOfTitleIssued, excelFieldMapping.get(Constants.NumberOfTitleIssued), 0);
			
			String TitleInsuranceCompany = ExcelUtility.getValueFromMap(titleMap, Constants.TitleInsuranceCompany, false);
			ExcelUtility.setCellValue(sheet, TitleInsuranceCompany, excelFieldMapping.get(Constants.TitleInsuranceCompany), 0);
			
			String IsTitleInsDeclineToAppl = ExcelUtility.getValueFromMap(titleMap, Constants.IsTitleInsDeclineToAppl, false);
			if(IsTitleInsDeclineToAppl != null && IsTitleInsDeclineToAppl.equals("Y")){
				IsTitleInsDeclineToAppl = "Yes";
			}else if(IsTitleInsDeclineToAppl != null && IsTitleInsDeclineToAppl.equals("N")){
				IsTitleInsDeclineToAppl = "No";
			}
			ExcelUtility.setCellValue(sheet, IsTitleInsDeclineToAppl, excelFieldMapping.get(Constants.IsTitleInsDeclineToAppl), 0);
			
			String NameAndReasonOfTitle = ExcelUtility.getValueFromMap(titleMap, Constants.NameAndReasonOfTitle, false);
			ExcelUtility.setCellValue(sheet, NameAndReasonOfTitle, excelFieldMapping.get(Constants.NameAndReasonOfTitle), 0);
		}else{
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplMaintEquityInTitle), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.PercentageOfEquityInt), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsTitleHaveProfLiabCoverage), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplCovProfLiabPolicy), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsTitleInsAgencyIssuePolicy), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.NumberOfTitleAttInFirm), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.NumberOfTitleNonAttInFirm), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.NumberOfTitleAttNotInFirm), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.NumberOfTitleNonAttSubCont), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplObtCertOfInsOfTitle), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.NumberOfRealEstTitleIns), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.PercentOfTitleFromResi), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.PercentOfTitleFromAgri), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.PercentOfTitleFromComm), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.PercentOfTitleFromOther), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsFirmUseEngageLetterOfTitle), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.NumberOfTitleIssued), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.TitleInsuranceCompany), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsTitleInsDeclineToAppl), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.NameAndReasonOfTitle), 0);
		}
	}	
}
