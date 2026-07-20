package com.excel.downloadexcel;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;

import com.ormapping.ibatis.SqlResources;
import com.util.Context;

public class Investment {
	
	public void getDataForInvestment(Context ctx) throws Exception
	{
		Object objFinancialPlanningAndIAServiceSupp =SqlResources.getSqlMapProcessor(ctx).findByKey("FinancialPlanningAndIAServiceSupp.findByKey", ctx);
		ctx.put(Constants.INVEST_FINAN_PLANING_FREEFORM_01, objFinancialPlanningAndIAServiceSupp);
			
	}
		
	public void populateInvestmentData(Context ctx, HSSFWorkbook wb) throws Exception
	{
		Sheet sheet = wb.getSheet("Supp Invest Financial Planning");
        CellStyle string= wb.createCellStyle();
        string.setDataFormat((short) 38);
        CellStyle numeric= wb.createCellStyle();
        numeric.setDataFormat((short) 38);
        
        Object objFirm = ctx.get(Constants.INVEST_FINAN_PLANING_FREEFORM_01);
		
//		if (objFirm == null){
//			return;
//		}
		
		Map firmMap = null;
		if (objFirm != null)
			firmMap = (HashMap) objFirm;

		/*Map excelFieldMapping = (HashMap) ctx.get("EXCEL_FIELD_MAPPING");
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsFinancialPlansPreparing, true), excelFieldMapping.get(Constants.IsFinancialPlansPreparing), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsDiscretionaryAssetMangt, true), excelFieldMapping.get(Constants.IsDiscretionaryAssetMangt), 0);
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsNonDiscretionaryAssetMangt, true), excelFieldMapping.get(Constants.IsNonDiscretionaryAssetMangt), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsSecuritiesSales, true), excelFieldMapping.get(Constants.IsSecuritiesSales), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsContractualRelationShip, true), excelFieldMapping.get(Constants.IsContractualRelationShip), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsOmissionsPolicy, true), excelFieldMapping.get(Constants.IsOmissionsPolicy), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsAnyNonPublicInvestments, true), excelFieldMapping.get(Constants.IsAnyNonPublicInvestments), 0);
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsAnyFirmAffiliateLicensed, true), excelFieldMapping.get(Constants.IsAnyFirmAffiliateLicensed), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsAnyEmployeeBenefitPlan, true), excelFieldMapping.get(Constants.IsAnyEmployeeBenefitPlan), 0);
		*/
	}

}
