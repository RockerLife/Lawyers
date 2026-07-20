package com.excel.downloadexcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;

import com.ormapping.ibatis.SqlResources;
import com.util.Context;

public class General {
	
	public void getDataForGeneral(Context ctx) throws Exception
	{
		Object objFirm =SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.generalviewgetPriorPolicyInfo", ctx);	
		ctx.put(Constants.LABELS_FREEFORM_01, objFirm);
		
		Object objSeperateEntitySupp =SqlResources.getSqlMapProcessor(ctx).findByKey("SeperateEntitySupp.findByKey", ctx);	
		ctx.put(Constants.GENERAL_FREEFORM_02, objSeperateEntitySupp);
		
		Object objFundsControlledSupp =SqlResources.getSqlMapProcessor(ctx).findByKey("FundsControlledSupp.findByKey", ctx);
		ctx.put(Constants.GENERAL_FREEFORM_03, objFundsControlledSupp);	
		
		Object objClientServiceDescFundControl =SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsgeClientServiceDescFundControl", ctx);
		ctx.put(Constants.CLIENTSERVICEDESC_FUNDCONTROL_FREEFORM_01, objClientServiceDescFundControl);
				
		Object listTrusteeOrEstateSupp =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.generalviewListTrusteeOrEstateSupp", ctx);
		ctx.put(Constants.GENERAL_LIST_01, listTrusteeOrEstateSupp);
		
		Object listOutsideInterestSupp =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.generalviewgetOutsideInterestSupp", ctx);
		ctx.put(Constants.GENERAL_LIST_04, listOutsideInterestSupp);
			
	}
	
	
	public void populateGeneralData(Context ctx, HSSFWorkbook wb) throws Exception
	{
		Sheet sheet = wb.getSheet("General");
        CellStyle string= wb.createCellStyle();
        string.setDataFormat((short) 38);
        CellStyle numeric= wb.createCellStyle();
        numeric.setDataFormat((short) 38);
        
        populateFirmInfo(ctx, sheet);
        populateSeperateEntity(ctx, sheet);
        populateBusinessManagment(ctx, sheet);
        
        populateFirmRenderedList(ctx, sheet);
        populateTrusteeList(ctx, sheet);
	}
	
	public void populateFirmInfo(Context ctx, Sheet sheet) throws Exception
	{
		Object objFirm = ctx.get(Constants.FIRM_FREEFORM_01);
		Map firmMap = null;
		if(objFirm != null)
			firmMap = (Map)objFirm;
		
//		if(firmMap == null)
//			return;

		/*Map excelFieldMapping = (HashMap) ctx.get("EXCEL_FIELD_MAPPING");		
				
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsPolicyExcludesCoverage, true), excelFieldMapping.get(Constants.IsPolicyExcludesCoverage), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.RetroFirmName, false), excelFieldMapping.get(Constants.RetroFirmName), 0);		
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsFirmRenderingServices, true), excelFieldMapping.get(Constants.IsFirmRenderingServices), 0);		
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsInvestmentVenture, true), excelFieldMapping.get(Constants.IsInvestmentVenture), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsSyndicationManagers, true), excelFieldMapping.get(Constants.IsSyndicationManagers), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsFirmRenderedAuditOrAttestService, true), excelFieldMapping.get(Constants.IsFirmRenderedAuditOrAttestService), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsManagementServiceProvided, true), excelFieldMapping.get(Constants.IsManagementServiceProvided), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsAnyFirmMeargedOrAcquired, true), excelFieldMapping.get(Constants.IsAnyFirmMeargedOrAcquired), 0);
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsControlClientFunds, true), excelFieldMapping.get(Constants.IsControlClientFunds), 0);
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsDebtOrEquityFinancing, true), excelFieldMapping.get(Constants.IsDebtOrEquityFinancing), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsNonPublicInvestmentDone, true), excelFieldMapping.get(Constants.IsNonPublicInvestmentDone), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsServedAsTrustee, true), excelFieldMapping.get(Constants.IsServedAsTrustee), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsFirmRenderedServiceForAnyClient, true), excelFieldMapping.get(Constants.IsFirmRenderedServiceForAnyClient), 0);
	*/		
	}
	
	public void populateSeperateEntity(Context ctx, Sheet sheet) throws Exception
	{
		Object objSeperateEntity = ctx.get(Constants.GENERAL_FREEFORM_02);
		Map seperateEntityMap = null;
		if(objSeperateEntity != null)
			seperateEntityMap = (Map)objSeperateEntity;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		
//		if(seperateEntityMap == null)
//			return;
		
		/*ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(seperateEntityMap, Constants.EntityNameSe, false), excelFieldMapping.get(Constants.EntityNameSe), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(seperateEntityMap, Constants.EstablisedSeDate, false), excelFieldMapping.get(Constants.EstablisedSeDate), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(seperateEntityMap, Constants.PercentOfOwnershipSe, false), excelFieldMapping.get(Constants.PercentOfOwnershipSe), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(seperateEntityMap, Constants.TotalProfessionalStaffSe, false), excelFieldMapping.get(Constants.TotalProfessionalStaffSe), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(seperateEntityMap, Constants.TotalSupportStaffSe, false), excelFieldMapping.get(Constants.TotalSupportStaffSe), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(seperateEntityMap, Constants.DetailedDescSe, false), excelFieldMapping.get(Constants.DetailedDescSe), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(seperateEntityMap, Constants.EstimateCurrentYearSeEndDate, false), excelFieldMapping.get(Constants.EstimateCurrentYearSeEndDate), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(seperateEntityMap, Constants.EstimateCurrentYearGrossRevenueSe, false), excelFieldMapping.get(Constants.EstimateCurrentYearGrossRevenueSe), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(seperateEntityMap, Constants.LastFiscalYearSeEndDate, false), excelFieldMapping.get(Constants.LastFiscalYearSeEndDate), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(seperateEntityMap, Constants.LastFiscalYearGrossRevenueSe, false), excelFieldMapping.get(Constants.LastFiscalYearGrossRevenueSe), 0);
	*/	
	}
	
	public void populateBusinessManagment(Context ctx, Sheet sheet) throws Exception
	{
		Object objFirm = ctx.get(Constants.GENERAL_FREEFORM_03);
		
//		if (objFirm == null){
//			return;
//		}
		
		Map firmMap = null;
		if (objFirm != null)
			firmMap = (HashMap) objFirm;

	/*	Map excelFieldMapping = (HashMap) ctx.get("EXCEL_FIELD_MAPPING");
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.TotlaNumberOfClientsFc, false), excelFieldMapping.get(Constants.TotlaNumberOfClientsFc), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.TotalAmountOfClientFundsFc, false), excelFieldMapping.get(Constants.TotalAmountOfClientFundsFc), 0);
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsControlFundsForBusinessAndEntimentFc, true), excelFieldMapping.get(Constants.IsControlFundsForBusinessAndEntimentFc), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.NumberOfClientsFc, false), excelFieldMapping.get(Constants.NumberOfClientsFc), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsCountersignatureRequiredFc, true), excelFieldMapping.get(Constants.IsCountersignatureRequiredFc), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsBankAccountReconciledFc, true), excelFieldMapping.get(Constants.IsBankAccountReconciledFc), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsFirmPersonnelBusinessManagerFc, true), excelFieldMapping.get(Constants.IsFirmPersonnelBusinessManagerFc), 0);
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.ProvideNumberOfClientFc, false), excelFieldMapping.get(Constants.ProvideNumberOfClientFc), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.NumberOfFirmEmployeesFc, false), excelFieldMapping.get(Constants.NumberOfFirmEmployeesFc), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsEmployeeDishonestyCoverageFc, true), excelFieldMapping.get(Constants.IsEmployeeDishonestyCoverageFc), 0);
		*/
		populateClientServiceDescData(ctx, sheet);
	}
	
	public void populateClientServiceDescData(Context ctx, Sheet sheet) throws Exception
	{
		Object objClientServiceDesc = ctx.get(Constants.CLIENTSERVICEDESC_FUNDCONTROL_FREEFORM_01);		
//		if (objClientServiceDesc == null){
//			return;
//		}
		
		Map clientServiceDescMap = null;
		if (objClientServiceDesc != null)
			clientServiceDescMap = (HashMap) objClientServiceDesc;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");		
	//	ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(clientServiceDescMap, Constants.ClientIndustryDescFc, false), excelFieldMapping.get(Constants.ClientIndustryDescFc), 0);
		
	}
	
	public void populateTrusteeList(Context ctx, Sheet sheet) throws Exception	
	{
		Object attestationObj = ctx.get(Constants.GENERAL_LIST_01);
		if(attestationObj == null)
			return;
		
		List attestationList = null;
		if (attestationObj != null)
			attestationList = (ArrayList) attestationObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.NameOfTrust);
		
		for(int i = rb_federal.getRownum() + attestationList.size(); i< rb_federal.getRownum() + 16; i++)
        {
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		
		int rowtoshiftup_bld = -15 + attestationList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		
		/*for (int i = 0; i < attestationList.size(); i++) {

			Map map = (HashMap) attestationList.get(i);
			
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.NameOfTrust, false), excelFieldMapping.get(Constants.NameOfTrust), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getFormattedDateFromObject(map.get(Constants.StartEngagemenTrusttDate)), excelFieldMapping.get(Constants.StartEngagemenTrusttDate), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.TypeofTrustDesc, false), excelFieldMapping.get(Constants.TypeofTrustDesc), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.ValueOfAssetsTrust, false), excelFieldMapping.get(Constants.ValueOfAssetsTrust), i);
			
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.AnnualIncomeofAssetsTrust, false), excelFieldMapping.get(Constants.AnnualIncomeofAssetsTrust), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.NumberOfBeneficiariesTrust, false), excelFieldMapping.get(Constants.NumberOfBeneficiariesTrust), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.IsBeneficiaryInterestTrust, true), excelFieldMapping.get(Constants.IsBeneficiaryInterestTrust), i);
			
		}*/
	}
	
	public void populateFirmRenderedList(Context ctx, Sheet sheet) throws Exception	
	{
		Object attestationObj = ctx.get(Constants.GENERAL_LIST_04);
		if(attestationObj == null)
			return;
		
		List attestationList = null;
		if (attestationObj != null)
			attestationList = (ArrayList) attestationObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.ClientNameOsi);
		
		for(int i = rb_federal.getRownum() + attestationList.size(); i< rb_federal.getRownum() + 16; i++)
        {
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		
		int rowtoshiftup_bld = -15 + attestationList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		/*
		for (int i = 0; i < attestationList.size(); i++) {

			Map map = (HashMap) attestationList.get(i);
			
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.ClientNameOsi, false), excelFieldMapping.get(Constants.ClientNameOsi), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getFormattedDateFromObject(map.get(Constants.StartEngagemenTrusttDate)), excelFieldMapping.get(Constants.StartEngagemenTrusttDate), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.ClientIndustryDesc, false), excelFieldMapping.get(Constants.ClientIndustryOsiKey), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.ServiceIndustryDesc, false), excelFieldMapping.get(Constants.ServiceIndustryOsiKey), i);
			
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.PositionHeldOsi, false), excelFieldMapping.get(Constants.PositionHeldOsi), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.DOInsuranceOsi, true), excelFieldMapping.get(Constants.DOInsuranceOsi), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.PercentEquityInterestofOsi, false), excelFieldMapping.get(Constants.PercentEquityInterestofOsi), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.IndividualListedServicesOsi, true), excelFieldMapping.get(Constants.IndividualListedServicesOsi), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.EntityNameOsi, false), excelFieldMapping.get(Constants.EntityNameOsi), i);
			
			
		}*/
	}


}
