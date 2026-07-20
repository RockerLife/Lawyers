package com.excel.downloadexcel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;

import com.ormapping.ibatis.SqlResources;
import com.util.Context;

public class BusinessManagment {
	
	public void getDataForBusinessManagment(Context ctx) throws Exception
	{
		Object objFundsControlledSupp =SqlResources.getSqlMapProcessor(ctx).findByKey("FundsControlledSupp.findByKey", ctx);
		ctx.put(Constants.GENERAL_FREEFORM_03, objFundsControlledSupp);	
		
		Object objClientServiceDescFundControl =SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsgeClientServiceDescFundControl", ctx);
		ctx.put(Constants.CLIENTSERVICEDESC_FUNDCONTROL_FREEFORM_01, objClientServiceDescFundControl);
		
	}
	
	public void populateBusinessManagmentData(Context ctx, HSSFWorkbook wb) throws Exception
	{
		Sheet sheet = wb.getSheet("Supp Business Management");
        CellStyle string= wb.createCellStyle();
        string.setDataFormat((short) 38);
        CellStyle numeric= wb.createCellStyle();
        numeric.setDataFormat((short) 38);  
        
        populateBusinessManagment(ctx, sheet);
        
	}
	
	public void populateBusinessManagment(Context ctx, Sheet sheet) throws Exception
	{/*
		Object objFirm = ctx.get(Constants.GENERAL_FREEFORM_03);
		
//		if (objFirm == null){
//			return;
//		}
		
		Map firmMap = null;
		if (objFirm != null)
			firmMap = (HashMap) objFirm;

		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.TotlaNumberOfClientsFc, false), excelFieldMapping.get(Constants.TotlaNumberOfClientsFcBM), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.TotalAmountOfClientFundsFc, false), excelFieldMapping.get(Constants.TotalAmountOfClientFundsFcBM), 0);
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsControlFundsForBusinessAndEntimentFc, true), excelFieldMapping.get(Constants.IsControlFundsForBusinessAndEntimentFcBM), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.NumberOfClientsFc, false), excelFieldMapping.get(Constants.NumberOfClientsFcBM), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsCountersignatureRequiredFc, true), excelFieldMapping.get(Constants.IsCountersignatureRequiredFcBM), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsBankAccountReconciledFc, true), excelFieldMapping.get(Constants.IsBankAccountReconciledFcBM), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsFirmPersonnelBusinessManagerFc, true), excelFieldMapping.get(Constants.IsFirmPersonnelBusinessManagerFcBM), 0);
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.ProvideNumberOfClientFc, false), excelFieldMapping.get(Constants.ProvideNumberOfClientFcBM), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.NumberOfFirmEmployeesFc, false), excelFieldMapping.get(Constants.NumberOfFirmEmployeesFcBM), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsEmployeeDishonestyCoverageFc, true), excelFieldMapping.get(Constants.IsEmployeeDishonestyCoverageFcBM), 0);
		
		populateClientServiceDescData(ctx, sheet);
	*/}
	
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
		//ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(clientServiceDescMap, Constants.ClientIndustryDescFc, false), excelFieldMapping.get(Constants.ClientIndustryDescFcBM), 0);
		
	}
	
}
