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

public class Firm {
	
	public void getDataForFirm(Context ctx) throws Exception{
		Object objAccount = SqlResources.getSqlMapProcessor(ctx).findByKey("Account.findByKey", ctx);	
		ctx.put(Constants.ACCOUNT_FREEFORM_01, objAccount);
		
		Object objAddress = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.firmviewgetAddressdetails", ctx);	
		ctx.put(Constants.ADDRESS_FREEFORM_01, objAddress);
		
		Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey("FirmLW.findByKey", ctx);	
		ctx.put(Constants.FIRM_FREEFORM_01, objFirm);
		
		Object objPolicy = SqlResources.getSqlMapProcessor(ctx).findByKey("Policy.findByKey", ctx);	
		ctx.put(Constants.POLICY_FREEFORM_01, objPolicy);
		
		Object listGrossRevenueForFirm = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.firmviewmomgrossrevenue", ctx);
		ctx.put(Constants.FIRM_GROSSREVENUE_LIST, listGrossRevenueForFirm);
		
		//Object listAttorney = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetAttorneysList", ctx);
		Object listAttorney = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.firmviewgetAttorneysList", ctx);		
		ctx.put(Constants.FIRM_ATTORNEY_LIST, listAttorney);
		
		Object listPrimaryLoc = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetPrimaryLocationList", ctx);
		ctx.put(Constants.FIRM_PRIMARYLOC_LIST, listPrimaryLoc);
	}
	
	public void populateFirmData(Context ctx, HSSFWorkbook wb) throws Exception{
		Sheet sheet = wb.getSheet("Firm");
		populateFirm(ctx, sheet);
		populateGrossRevenue(ctx, sheet);
		populatePrimaryLocationList(ctx, sheet);
		populateAttorneyList(ctx, sheet);
		
	}
	
	public void populateFirm(Context ctx, Sheet sheet) throws Exception{
		Object objFirm = ctx.get(Constants.FIRM_FREEFORM_01);
		Map firmMap = null;
		if(objFirm != null)
			firmMap = (Map)objFirm;
		
		Object objAccount = ctx.get(Constants.ACCOUNT_FREEFORM_01);
		Map accountMap = null;
		if(objAccount != null)
			accountMap = (Map)objAccount;
		
		Object objAddress = ctx.get(Constants.ADDRESS_FREEFORM_01);
		Map addressMap = null;
		if(objAddress != null)
			addressMap = (Map)objAddress;
		
		Object objPolicy = ctx.get(Constants.POLICY_FREEFORM_01);
		Map policyMap = null;
		if(objPolicy != null)
			policyMap = (Map)objPolicy;	
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		
		ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(policyMap, Constants.QuoteNumber, false), excelFieldMapping.get(Constants.QuoteNumber), 0);
		ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(policyMap, Constants.PolicyNumber, false), excelFieldMapping.get(Constants.PolicyNumber), 0);
		
		ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(policyMap.get(Constants.PolicyEffectiveDate)), excelFieldMapping.get(Constants.PolicyEffectiveDate), 0);
		
		String str = ExcelUtility.getValueFromMap(policyMap, Constants.IsFullQuote, true);
		if("Yes".equals(str))
			str = "Full Quote";
		else if("No".equals(str))
			str = "Quick Quote";		
		ExcelUtility.setCellValue(sheet, str, excelFieldMapping.get(Constants.IsFullQuote), 0);
		
		ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(accountMap, Constants.AccountName, false), excelFieldMapping.get(Constants.AccountName), 0);
		ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(accountMap, Constants.AccountEmail, false), excelFieldMapping.get(Constants.AccountEmail), 0);
		ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(accountMap, Constants.Website, false), excelFieldMapping.get(Constants.Website), 0);
		
		ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(firmMap, Constants.ContactPerson, false), excelFieldMapping.get(Constants.ContactPerson), 0);
		
		String ContactPhone = ExcelUtility.getValueFromMap(firmMap, Constants.ContactPhone, false);
		String ContactPhoneExt = ExcelUtility.getValueFromMap(firmMap, Constants.ContactPhoneExt, false);
		if(!"".equals(ContactPhoneExt))
			ContactPhone = ContactPhone + " Ext " + ContactPhoneExt;
		ExcelUtility.setCellValue(sheet, ContactPhone, excelFieldMapping.get(Constants.ContactPhone), 0);
		
		ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(addressMap, Constants.Address1, false) + "  " + ExcelUtility.getValueFromMap(addressMap, Constants.Address2, false), excelFieldMapping.get(Constants.Address), 0);
		ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(addressMap, Constants.City, false), excelFieldMapping.get(Constants.City), 0);
		ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(addressMap, Constants.CountyDesc, false), excelFieldMapping.get(Constants.CountyDesc), 0);
		
		String stateDesc = ExcelUtility.getValueFromMap(addressMap, Constants.StateDesc, false);
		if(stateDesc.contains(" ")){
			stateDesc = stateDesc.replaceAll(" ", "_");
		}
		
		ExcelUtility.setCellValue(sheet, stateDesc, excelFieldMapping.get(Constants.StateDesc), 0);
		ExcelUtility.setCellValue(sheet, stateDesc, excelFieldMapping.get(Constants.State), 0);
		ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(addressMap, Constants.ContextPremium, false), excelFieldMapping.get(Constants.ContextPremium), 0);
		
		String Zip = ExcelUtility.getValueFromMap(addressMap, Constants.Zip, false);
		String Zip4 = ExcelUtility.getValueFromMap(addressMap, Constants.Zip4, false);
		if(!"".equals(Zip4))
			Zip = Zip +"-" + Zip4;
		
		String WorkPhone = ExcelUtility.getValueFromMap(addressMap, Constants.WorkPhone, false);
		String WorkExt = ExcelUtility.getValueFromMap(addressMap, Constants.WorkExt, false);
		if(!"".equals(WorkExt))
			WorkPhone = WorkPhone + " Ext " + WorkExt;
		
		ExcelUtility.setCellValue(sheet,  Zip, excelFieldMapping.get(Constants.Zip), 0);
		ExcelUtility.setCellValue(sheet,  WorkPhone, excelFieldMapping.get(Constants.WorkPhone), 0);
		
		String YearOfFirmEstablished = ExcelUtility.getValueFromMap(firmMap, Constants.YearOfFirmEstablished, false);
		ExcelUtility.setCellValue(sheet, YearOfFirmEstablished, excelFieldMapping.get(Constants.YearOfFirmEstablished), 0);
		
		String IsIndependentContractor = ExcelUtility.getValueFromMap(firmMap, Constants.IsIndependentContractor, false);
		if(IsIndependentContractor != null && IsIndependentContractor.equals("Y")){
			IsIndependentContractor = "Yes";
		}else if(IsIndependentContractor != null && IsIndependentContractor.equals("N")){
			IsIndependentContractor = "No";
		}
		ExcelUtility.setCellValue(sheet, IsIndependentContractor, excelFieldMapping.get(Constants.IsIndependentContractor), 0);
		
		String PerOfGrossBillingsUnderContract = ExcelUtility.getValueFromMap(firmMap, Constants.PerOfGrossBillingsUnderContract, false);
		ExcelUtility.setCellValue(sheet, PerOfGrossBillingsUnderContract, excelFieldMapping.get(Constants.PerOfGrossBillingsUnderContract), 0);
		
		String IsFirmHaveBackupAttorney = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmHaveBackupAttorney, false);
		if(IsFirmHaveBackupAttorney != null && IsFirmHaveBackupAttorney.equals("Y")){
			IsFirmHaveBackupAttorney = "Yes";
		}else if(IsFirmHaveBackupAttorney != null && IsFirmHaveBackupAttorney.equals("N")){
			IsFirmHaveBackupAttorney = "No";
		}
		ExcelUtility.setCellValue(sheet, IsFirmHaveBackupAttorney, excelFieldMapping.get(Constants.IsFirmHaveBackupAttorney), 0);
		
		String BackupAttorneyName = ExcelUtility.getValueFromMap(firmMap, Constants.BackupAttorneyName, false);
		ExcelUtility.setCellValue(sheet, BackupAttorneyName, excelFieldMapping.get(Constants.BackupAttorneyName), 0);
		
		String BackupAttorneyAddress = ExcelUtility.getValueFromMap(firmMap, Constants.BackupAttorneyAddress, false);
		ExcelUtility.setCellValue(sheet, BackupAttorneyAddress, excelFieldMapping.get(Constants.BackupAttorneyAddress), 0);
		
		String BackupAttorneyPhoneNumber = ExcelUtility.getValueFromMap(firmMap, Constants.BackupAttorneyPhoneNumber, false);
		ExcelUtility.setCellValue(sheet, BackupAttorneyPhoneNumber, excelFieldMapping.get(Constants.BackupAttorneyPhoneNumber), 0);
		
		String TotalNumOfNonAttorneyStaff = ExcelUtility.getValueFromMap(firmMap, Constants.TotalNumOfNonAttorneyStaff, false);
		ExcelUtility.setCellValue(sheet, TotalNumOfNonAttorneyStaff, excelFieldMapping.get(Constants.TotalNumOfNonAttorneyStaff), 0);
		
		String IsFirmPracticeInOtherState = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmPracticeInOtherState, false);
		if(IsFirmPracticeInOtherState != null && IsFirmPracticeInOtherState.equals("Y")){
			IsFirmPracticeInOtherState = "Yes";
		}else if(IsFirmPracticeInOtherState != null && IsFirmPracticeInOtherState.equals("N")){
			IsFirmPracticeInOtherState = "No";
		}
		ExcelUtility.setCellValue(sheet, IsFirmPracticeInOtherState, excelFieldMapping.get(Constants.IsFirmPracticeInOtherState), 0);
		
		String IsApplRestWithManagement = ExcelUtility.getValueFromMap(firmMap, Constants.IsApplRestWithManagement, false);
		if(IsApplRestWithManagement != null && IsApplRestWithManagement.equals("Y")){
			IsApplRestWithManagement = "Yes";
		}else if(IsApplRestWithManagement != null && IsApplRestWithManagement.equals("N")){
			IsApplRestWithManagement = "No";
		}
		ExcelUtility.setCellValue(sheet, IsApplRestWithManagement, excelFieldMapping.get(Constants.IsApplRestWithManagement), 0);
		
		String ApplRestWithManagementDesc = ExcelUtility.getValueFromMap(firmMap, Constants.ApplRestWithManagementDesc, false);
		ExcelUtility.setCellValue(sheet, ApplRestWithManagementDesc, excelFieldMapping.get(Constants.ApplRestWithManagementDesc), 0);
		
		String IsAppOfficeSharedWithAttorney = ExcelUtility.getValueFromMap(firmMap, Constants.IsAppOfficeSharedWithAttorney, false);
		if(IsAppOfficeSharedWithAttorney != null && IsAppOfficeSharedWithAttorney.equals("Y")){
			IsAppOfficeSharedWithAttorney = "Yes";
		}else if(IsAppOfficeSharedWithAttorney != null && IsAppOfficeSharedWithAttorney.equals("N")){
			IsAppOfficeSharedWithAttorney = "No";
		}
		ExcelUtility.setCellValue(sheet, IsAppOfficeSharedWithAttorney, excelFieldMapping.get(Constants.IsAppOfficeSharedWithAttorney), 0);
		
		String IsApplicantProvidesLegalServices = ExcelUtility.getValueFromMap(firmMap, Constants.IsApplicantProvidesLegalServices, false);
		if(IsApplicantProvidesLegalServices != null && IsApplicantProvidesLegalServices.equals("Y")){
			IsApplicantProvidesLegalServices = "Yes";
		}else if(IsApplicantProvidesLegalServices != null && IsApplicantProvidesLegalServices.equals("N")){
			IsApplicantProvidesLegalServices = "No";
		}
		ExcelUtility.setCellValue(sheet, IsApplicantProvidesLegalServices, excelFieldMapping.get(Constants.IsApplicantProvidesLegalServices), 0);
		
		String ApplicantProvidesLegalServicesDesc = ExcelUtility.getValueFromMap(firmMap, Constants.ApplicantProvidesLegalServicesDesc, false);
		ExcelUtility.setCellValue(sheet, ApplicantProvidesLegalServicesDesc, excelFieldMapping.get(Constants.ApplicantProvidesLegalServicesDesc), 0);
		
		//Set Ajax List Size
		String FirmAttorneyCount = "0";
		String FirmPrimaryCount = "0";
		Object primarylocObj = ctx.get(Constants.FIRM_PRIMARYLOC_LIST);
		List primarylocList = null;
		if(primarylocObj != null && primarylocObj instanceof List){
			primarylocList = (ArrayList) primarylocObj;
			FirmPrimaryCount = String.valueOf(primarylocList.size());
		}
		Object attObj = ctx.get(Constants.FIRM_ATTORNEY_LIST);
		List attList = null;
		if(attObj != null && attObj instanceof List){
			attList = (ArrayList) attObj;
			FirmAttorneyCount = String.valueOf(attList.size());
		}
		ExcelUtility.setCellValue(sheet, FirmAttorneyCount, excelFieldMapping.get(Constants.FirmAttorneyCount), 0);
		ExcelUtility.setCellValue(sheet, FirmPrimaryCount, excelFieldMapping.get(Constants.FirmPrimaryCount), 0);
	}
	
	public void populateGrossRevenue(Context ctx, Sheet sheet) throws Exception{
		Object grossRevObj = ctx.get(Constants.FIRM_GROSSREVENUE_LIST);
		if(grossRevObj == null)
			return;
		
		List grossRevList = null;
		if (grossRevObj != null)
			grossRevList = (ArrayList) grossRevObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		for(int i=0; i<grossRevList.size(); i++) {
			Map map = (HashMap) grossRevList.get(i);			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.YearEndDate, false), excelFieldMapping.get(Constants.YearEndDate +"_" + i), 0);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.Amount, false), excelFieldMapping.get(Constants.Amount +"_" + i), 0);
		}
	}
	
	public void populateAttorneyList(Context ctx, Sheet sheet) throws Exception{
		Object attorneyObj = ctx.get(Constants.FIRM_ATTORNEY_LIST);
		if(attorneyObj == null)
			return;
		
		List attorneyList = null;
		if (attorneyObj != null)
			attorneyList = (ArrayList) attorneyObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.AttorneyName);
		
		for(int i = rb_federal.getRownum() + attorneyList.size(); i< rb_federal.getRownum() + 21; i++){
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		int rowtoshiftup_bld = -21 + attorneyList.size();
		sheet.shiftRows(rb_federal.getRownum() + 21, sheet.getLastRowNum(), rowtoshiftup_bld);
		for(int i = 0; i < attorneyList.size(); i++) {
			Map map = (HashMap) attorneyList.get(i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.AttorneyName, false), excelFieldMapping.get(Constants.AttorneyName), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.AttorneyDesg, false), excelFieldMapping.get(Constants.AttorneyDesg), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.LicensedStates, false), excelFieldMapping.get(Constants.LicensedStates), i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.AnnualWorkedHours, false), excelFieldMapping.get(Constants.AnnualWorkedHours), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.NumberOfYearsWithFirm, false), excelFieldMapping.get(Constants.NumberOfYearsWithFirm), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.NumberOfYearsInPractice, false), excelFieldMapping.get(Constants.NumberOfYearsInPractice), i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.NumberOfYearsMalpracticeCov, false), excelFieldMapping.get(Constants.NumberOfYearsMalpracticeCov), i);
			
			if(ExcelUtility.getValueFromMap(map, Constants.AttorneyPriorActDate, false) != null){
				ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(map.get(Constants.AttorneyPriorActDate)), excelFieldMapping.get(Constants.AttorneyPriorActDate), i);	
			}
		}
	}
	
	public void populatePrimaryLocationList(Context ctx, Sheet sheet) throws Exception{
		Object primarylocObj = ctx.get(Constants.FIRM_PRIMARYLOC_LIST);
		if(primarylocObj == null)
			return;
		
		List primarylocList = null;
		if (primarylocObj != null)
			primarylocList = (ArrayList) primarylocObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.FPLAddress);
		
		for(int i = rb_federal.getRownum() + primarylocList.size(); i< rb_federal.getRownum() + 16; i++){
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		int rowtoshiftup_bld = -15 + primarylocList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		for(int i = 0; i < primarylocList.size(); i++) {
			Map map = (HashMap) primarylocList.get(i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.FPLAddress, false), excelFieldMapping.get(Constants.FPLAddress), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.FPLCity, false), excelFieldMapping.get(Constants.FPLCity), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.FPLStateCode, false), excelFieldMapping.get(Constants.FPLStateCode), i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.FPLZip, false), excelFieldMapping.get(Constants.FPLZip), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.NumberOfAttorneys, false), excelFieldMapping.get(Constants.NumberOfAttorneys), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.NumberOfOtherEmployees, false), excelFieldMapping.get(Constants.NumberOfOtherEmployees), i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.NumberOfBillabeHours, false), excelFieldMapping.get(Constants.NumberOfBillabeHours), i);
		}
	}
}
