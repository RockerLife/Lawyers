package com.excel.downloadexcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import com.excel.oldpim.ExcelUtility;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;

public class PracticeMangement {
	
	public void getDataForPracticeMangement(Context ctx) throws Exception {
		Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey("FirmLW.findByKey", ctx);	
		ctx.put(Constants.FIRM_FREEFORM_01, objFirm);
		
		Object listRevenue = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetFirmAnnualRevList", ctx);
		ctx.put(Constants.PRACTICE_REVENUE_LIST, listRevenue); 
		
		Object listPersonel = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetPersonnelList", ctx);
		ctx.put(Constants.PRACTICE_PERSONEL_LIST, listPersonel); 
		
		//Object listFees = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetFeesSuedList", ctx);
		Object listFees = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.practiceviewgetFeesSuedList", ctx);
		
		ctx.put(Constants.PRACTICE_FEESUED_LIST, listFees);
	}
	
	public void populatePracticeMangementData(Context ctx, HSSFWorkbook wb) throws Exception{
		Sheet sheet = wb.getSheet("Practice Management");
        populatePractice(ctx, sheet);
        populateFeesList(ctx, sheet);
        populatePersonelList(ctx, sheet);
        populateRevenueList(ctx, sheet);
	}
	
	public void populateRevenueList(Context ctx, Sheet sheet) throws Exception{
		Object revenueObj = ctx.get(Constants.PRACTICE_REVENUE_LIST);
		if(revenueObj == null)
			return;
		
		List revenueList = null;
		if (revenueObj != null)
			revenueList = (ArrayList) revenueObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.ClientNameForAnnlRevenue);
		
		for(int i = rb_federal.getRownum() + revenueList.size(); i< rb_federal.getRownum() + 16; i++){
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		int rowtoshiftup_bld = -15 + revenueList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		for(int i = 0; i < revenueList.size(); i++) {
			Map map = (HashMap) revenueList.get(i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.ClientNameForAnnlRevenue, false), excelFieldMapping.get(Constants.ClientNameForAnnlRevenue), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.PercentageOfAnnlRevenue, false), excelFieldMapping.get(Constants.PercentageOfAnnlRevenue), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.ServicesRendered, false), excelFieldMapping.get(Constants.ServicesRendered), i);
		}
	}
	
	public void populateFeesList(Context ctx, Sheet sheet) throws Exception{
		Object revenueObj = ctx.get(Constants.PRACTICE_FEESUED_LIST);
		if(revenueObj == null)
			return;
		
		List revenueList = null;
		if (revenueObj != null)
			revenueList = (ArrayList) revenueObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.AmountOfFeesSued);
		
		for(int i = rb_federal.getRownum() + revenueList.size(); i< rb_federal.getRownum() + 16; i++){
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		int rowtoshiftup_bld = -15 + revenueList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		for(int i = 0; i < revenueList.size(); i++) {
			Map map = (HashMap) revenueList.get(i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.AmountOfFeesSued, false), excelFieldMapping.get(Constants.AmountOfFeesSued), i);
			
			if(ExcelUtility.getValueFromMap(map, Constants.DueDateFees, false) != null){
				ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(map.get(Constants.DueDateFees)), excelFieldMapping.get(Constants.DueDateFees), i);	
			}
			if(ExcelUtility.getValueFromMap(map, Constants.SuitFilesDateFees, false) != null){
				ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(map.get(Constants.SuitFilesDateFees)), excelFieldMapping.get(Constants.SuitFilesDateFees), i);	
			}
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.FeeSuitDesc, false), excelFieldMapping.get(Constants.DispositionOfFeeSuit), i);
			
			String IsAllegOfLegalMalPrac = ExcelUtility.getValueFromMap(map, Constants.IsAllegOfLegalMalPrac, false);
			if(IsAllegOfLegalMalPrac != null && IsAllegOfLegalMalPrac.equals("Y")){
				IsAllegOfLegalMalPrac = "Yes";
			}else if(IsAllegOfLegalMalPrac != null && IsAllegOfLegalMalPrac.equals("N")){
				IsAllegOfLegalMalPrac = "No";
			}
			ExcelUtility.setCellValue(sheet, IsAllegOfLegalMalPrac, excelFieldMapping.get(Constants.IsAllegOfLegalMalPrac), i);
			
			String IsFavOutInUnderMatter = ExcelUtility.getValueFromMap(map, Constants.IsFavOutInUnderMatter, false);
			if(IsFavOutInUnderMatter != null && IsFavOutInUnderMatter.equals("Y")){
				IsFavOutInUnderMatter = "Yes";
			}else if(IsFavOutInUnderMatter != null && IsFavOutInUnderMatter.equals("N")){
				IsFavOutInUnderMatter = "No";
			}
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.FeesAreaOfPractice, false), excelFieldMapping.get(Constants.FeesAreaOfPractice), i);
		}
	}
	
	public void populatePersonelList(Context ctx, Sheet sheet) throws Exception{
		Object personelObj = ctx.get(Constants.PRACTICE_PERSONEL_LIST);
		if(personelObj == null)
			return;
		
		List personelList = null;
		if (personelObj != null)
			personelList = (ArrayList) personelObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.FirmMember);
		
		for(int i = rb_federal.getRownum() + personelList.size(); i< rb_federal.getRownum() + 16; i++){
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		int rowtoshiftup_bld = -15 + personelList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		for(int i = 0; i < personelList.size(); i++) {
			Map map = (HashMap) personelList.get(i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.FirmMember, false), excelFieldMapping.get(Constants.FirmMember), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.EntityName, false), excelFieldMapping.get(Constants.EntityName), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.NatureOfClientBusiness, false), excelFieldMapping.get(Constants.NatureOfClientBusiness), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.PositionHeld, false), excelFieldMapping.get(Constants.PositionHeld), i);
			
			String IsDirOffInsInForce = ExcelUtility.getValueFromMap(map, Constants.IsDirOffInsInForce, false);
			if(IsDirOffInsInForce != null && IsDirOffInsInForce.equals("Y")){
				IsDirOffInsInForce = "Yes";
			}else if(IsDirOffInsInForce != null && IsDirOffInsInForce.equals("N")){
				IsDirOffInsInForce = "No";
			}
			ExcelUtility.setCellValue(sheet, IsDirOffInsInForce, excelFieldMapping.get(Constants.IsDirOffInsInForce), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.FirmServices, false), excelFieldMapping.get(Constants.FirmServices), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.PercentEquityInterest, false), excelFieldMapping.get(Constants.PercentEquityInterest), i);
			
			String IsIndividualPerformServices = ExcelUtility.getValueFromMap(map, Constants.IsIndividualPerformServices, false);
			if(IsIndividualPerformServices != null && IsIndividualPerformServices.equals("Y")){
				IsIndividualPerformServices = "Yes";
			}else if(IsIndividualPerformServices != null && IsIndividualPerformServices.equals("N")){
				IsIndividualPerformServices = "No";
			}
			ExcelUtility.setCellValue(sheet, IsIndividualPerformServices, excelFieldMapping.get(Constants.IsIndividualPerformServices), i);
		}
	}
	
	public void populatePractice(Context ctx, Sheet sheet) throws Exception{
		Object objFirm = ctx.get(Constants.FIRM_FREEFORM_01);
		
		Map firmMap = null;
		if (objFirm != null)
			firmMap = (HashMap) objFirm;

		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		
		if(firmMap != null){
			String IsFirmHaveClientMoreThan25PercentOfBilling = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmHaveClientMoreThan25PercentOfBilling, false);
			if(IsFirmHaveClientMoreThan25PercentOfBilling != null && IsFirmHaveClientMoreThan25PercentOfBilling.equals("Y")){
				IsFirmHaveClientMoreThan25PercentOfBilling = "Yes";
			}else if(IsFirmHaveClientMoreThan25PercentOfBilling != null && IsFirmHaveClientMoreThan25PercentOfBilling.equals("N")){
				IsFirmHaveClientMoreThan25PercentOfBilling = "No";
			}
			ExcelUtility.setCellValue(sheet, IsFirmHaveClientMoreThan25PercentOfBilling, excelFieldMapping.get(Constants.IsFirmHaveClientMoreThan25PercentOfBilling), 0);
			
			String IsFirmHaveEquityIntGrtThan10 = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmHaveEquityIntGrtThan10, false);
			if(IsFirmHaveEquityIntGrtThan10 != null && IsFirmHaveEquityIntGrtThan10.equals("Y")){
				IsFirmHaveEquityIntGrtThan10 = "Yes";
			}else if(IsFirmHaveEquityIntGrtThan10 != null && IsFirmHaveEquityIntGrtThan10.equals("N")){
				IsFirmHaveEquityIntGrtThan10 = "No";
			}
			ExcelUtility.setCellValue(sheet, IsFirmHaveEquityIntGrtThan10, excelFieldMapping.get(Constants.IsFirmHaveEquityIntGrtThan10), 0);
			
			String IsFirmPersServedAsOfficerInJointVenture = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmPersServedAsOfficerInJointVenture, false);
			if(IsFirmPersServedAsOfficerInJointVenture != null && IsFirmPersServedAsOfficerInJointVenture.equals("Y")){
				IsFirmPersServedAsOfficerInJointVenture = "Yes";
			}else if(IsFirmPersServedAsOfficerInJointVenture != null && IsFirmPersServedAsOfficerInJointVenture.equals("N")){
				IsFirmPersServedAsOfficerInJointVenture = "No";
			}
			ExcelUtility.setCellValue(sheet, IsFirmPersServedAsOfficerInJointVenture, excelFieldMapping.get(Constants.IsFirmPersServedAsOfficerInJointVenture), 0);
			
			String IsFirmHaveProcForFormersClients = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmHaveProcForFormersClients, false);
			if(IsFirmHaveProcForFormersClients != null && IsFirmHaveProcForFormersClients.equals("Y")){
				IsFirmHaveProcForFormersClients = "Yes";
			}else if(IsFirmHaveProcForFormersClients != null && IsFirmHaveProcForFormersClients.equals("N")){
				IsFirmHaveProcForFormersClients = "No";
			}
			ExcelUtility.setCellValue(sheet, IsFirmHaveProcForFormersClients, excelFieldMapping.get(Constants.IsFirmHaveProcForFormersClients), 0);
			
			String IsFirmHaveIndepDockets = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmHaveIndepDockets, false);
			if(IsFirmHaveIndepDockets != null && IsFirmHaveIndepDockets.equals("Y")){
				IsFirmHaveIndepDockets = "Yes";
			}else if(IsFirmHaveIndepDockets != null && IsFirmHaveIndepDockets.equals("N")){
				IsFirmHaveIndepDockets = "No";
			}
			ExcelUtility.setCellValue(sheet, IsFirmHaveIndepDockets, excelFieldMapping.get(Constants.IsFirmHaveIndepDockets), 0);
			
			String IsFirmRequireEngagementLetter = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmRequireEngagementLetter, false);
			if(IsFirmRequireEngagementLetter != null && IsFirmRequireEngagementLetter.equals("Y")){
				IsFirmRequireEngagementLetter = "Yes";
			}else if(IsFirmRequireEngagementLetter != null && IsFirmRequireEngagementLetter.equals("N")){
				IsFirmRequireEngagementLetter = "No";
			}
			ExcelUtility.setCellValue(sheet, IsFirmRequireEngagementLetter, excelFieldMapping.get(Constants.IsFirmRequireEngagementLetter), 0);
			
			String IsNonEngagementLetterIssueToFirm = ExcelUtility.getValueFromMap(firmMap, Constants.IsNonEngagementLetterIssueToFirm, false);
			if(IsNonEngagementLetterIssueToFirm != null && IsNonEngagementLetterIssueToFirm.equals("Y")){
				IsNonEngagementLetterIssueToFirm = "Yes";
			}else if(IsNonEngagementLetterIssueToFirm != null && IsNonEngagementLetterIssueToFirm.equals("N")){
				IsNonEngagementLetterIssueToFirm = "No";
			}
			ExcelUtility.setCellValue(sheet, IsNonEngagementLetterIssueToFirm, excelFieldMapping.get(Constants.IsNonEngagementLetterIssueToFirm), 0);
			
			String IsPolicyProhibitAttorneyWithInvestment = ExcelUtility.getValueFromMap(firmMap, Constants.IsPolicyProhibitAttorneyWithInvestment, false);
			if(IsPolicyProhibitAttorneyWithInvestment != null && IsPolicyProhibitAttorneyWithInvestment.equals("Y")){
				IsPolicyProhibitAttorneyWithInvestment = "Yes";
			}else if(IsPolicyProhibitAttorneyWithInvestment != null && IsPolicyProhibitAttorneyWithInvestment.equals("N")){
				IsPolicyProhibitAttorneyWithInvestment = "No";
			}
			ExcelUtility.setCellValue(sheet, IsPolicyProhibitAttorneyWithInvestment, excelFieldMapping.get(Constants.IsPolicyProhibitAttorneyWithInvestment), 0);
			
			String IsEmplRelativeToConfInfomation = ExcelUtility.getValueFromMap(firmMap, Constants.IsEmplRelativeToConfInfomation, false);
			if(IsEmplRelativeToConfInfomation != null && IsEmplRelativeToConfInfomation.equals("Y")){
				IsEmplRelativeToConfInfomation = "Yes";
			}else if(IsEmplRelativeToConfInfomation != null && IsEmplRelativeToConfInfomation.equals("N")){
				IsEmplRelativeToConfInfomation = "No";
			}
			ExcelUtility.setCellValue(sheet, IsEmplRelativeToConfInfomation, excelFieldMapping.get(Constants.IsEmplRelativeToConfInfomation), 0);
			
			String PercentofApplAcctRcbl = ExcelUtility.getValueFromMap(firmMap, Constants.PercentofApplAcctRcbl, false);
			ExcelUtility.setCellValue(sheet, PercentofApplAcctRcbl, excelFieldMapping.get(Constants.PercentofApplAcctRcbl), 0);
			
			String IsApplInitiatedLawsuitForFirm = ExcelUtility.getValueFromMap(firmMap, Constants.IsApplInitiatedLawsuitForFirm, false);
			if(IsApplInitiatedLawsuitForFirm != null && IsApplInitiatedLawsuitForFirm.equals("Y")){
				IsApplInitiatedLawsuitForFirm = "Yes";
			}else if(IsApplInitiatedLawsuitForFirm != null && IsApplInitiatedLawsuitForFirm.equals("N")){
				IsApplInitiatedLawsuitForFirm = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplInitiatedLawsuitForFirm, excelFieldMapping.get(Constants.IsApplInitiatedLawsuitForFirm), 0);
		}
		//Set Ajax List Count
		String PracticeRevenueCount = "0";
		String PracticePersonelCount = "0";
		String PracticeFeesCount = "0";
		Object revObj = ctx.get(Constants.PRACTICE_REVENUE_LIST);
		List revList = null;
		if(revObj != null && revObj instanceof List){
			revList = (ArrayList) revObj;
			PracticeRevenueCount = String.valueOf(revList.size());
		}
		Object perObj = ctx.get(Constants.PRACTICE_PERSONEL_LIST);
		List perList = null;
		if(perObj != null && perObj instanceof List){
			perList = (ArrayList) perObj;
			PracticePersonelCount = String.valueOf(perList.size());
		}
		Object feesObj = ctx.get(Constants.PRACTICE_FEESUED_LIST);
		List feesList = null;
		if(feesObj != null && feesObj instanceof List){
			feesList = (ArrayList) feesObj;
			PracticeFeesCount = String.valueOf(feesList.size());
		}
		ExcelUtility.setCellValue(sheet, PracticeRevenueCount, excelFieldMapping.get(Constants.PracticeRevenueCount), 0);
		ExcelUtility.setCellValue(sheet, PracticePersonelCount, excelFieldMapping.get(Constants.PracticePersonelCount), 0);
		ExcelUtility.setCellValue(sheet, PracticeFeesCount, excelFieldMapping.get(Constants.PracticeFeesCount), 0);
	}

}
