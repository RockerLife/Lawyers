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

public class FinancialInstitution {

	public void getDataForFinancial(Context ctx) throws Exception {
		Object objFinancialInst = SqlResources.getSqlMapProcessor(ctx).findByKey("FinancialInstSuppLW.findByKey", ctx);	
		ctx.put(Constants.FINANCIALINST_FREEFORM_01, objFinancialInst);
		
		Object listInst = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetFinancialInstitutionLWList", ctx);
		ctx.put(Constants.FINANCIAL_INST_LIST, listInst);
	}
	
	public void populateFinancialData(Context ctx, HSSFWorkbook wb) throws Exception{
		Sheet sheet = wb.getSheet("Financial");
	    populateFinancial(ctx, sheet);
	    populateInstitutionList(ctx, sheet);
	}
	
	public void populateInstitutionList(Context ctx, Sheet sheet) throws Exception{
		Object instObj = ctx.get(Constants.FINANCIAL_INST_LIST);
		if(instObj == null)
			return;
		
		List instList = null;
		if (instObj != null)
			instList = (ArrayList) instObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.InstitutionName);
		
		for(int i = rb_federal.getRownum() + instList.size(); i< rb_federal.getRownum() + 16; i++){
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		int rowtoshiftup_bld = -15 + instList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		for(int i = 0; i < instList.size(); i++) {
			Map map = (HashMap) instList.get(i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.InstitutionName, false), excelFieldMapping.get(Constants.InstitutionName), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.InstitutionState, false), excelFieldMapping.get(Constants.InstitutionState), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.FirmMember, false), excelFieldMapping.get(Constants.FinanFirmMember), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.NatureOfCapOfServices, false), excelFieldMapping.get(Constants.NatureOfCapOfServices), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(map.get(Constants.DateInitiated)), excelFieldMapping.get(Constants.DateInitiated), 0);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(map.get(Constants.DateEnded)), excelFieldMapping.get(Constants.DateEnded), 0);
			
			String IsClient = ExcelUtility.getValueFromMap(map, Constants.IsClient, false);
			if(IsClient != null && IsClient.equals("Y")){
				IsClient = "Yes";
			}else if(IsClient != null && IsClient.equals("N")){
				IsClient = "No";
			}
			ExcelUtility.setCellValue(sheet, IsClient, excelFieldMapping.get(Constants.IsFinanClient), i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(map.get(Constants.DateOfInsolvency)), excelFieldMapping.get(Constants.DateOfInsolvency), 0);
		}
	}
	
	public void populateFinancial(Context ctx, Sheet sheet) throws Exception{
	    Object objFinancialInst = ctx.get(Constants.FINANCIALINST_FREEFORM_01);
		Map financialMap = null;
		if (objFinancialInst != null)
			financialMap = (HashMap) objFinancialInst;

		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		
		if(financialMap != null){
			String IsApplPerformServiceToBankruptcy = ExcelUtility.getValueFromMap(financialMap, Constants.IsApplPerformServiceToBankruptcy, false);
			if(IsApplPerformServiceToBankruptcy != null && IsApplPerformServiceToBankruptcy.equals("Y")){
				IsApplPerformServiceToBankruptcy = "Yes";
			}else if(IsApplPerformServiceToBankruptcy != null && IsApplPerformServiceToBankruptcy.equals("N")){
				IsApplPerformServiceToBankruptcy = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplPerformServiceToBankruptcy, excelFieldMapping.get(Constants.IsApplPerformServiceToBankruptcy), 0);
			
			String IsApplServedAsCEOCouncel = ExcelUtility.getValueFromMap(financialMap, Constants.IsApplServedAsCEOCouncel, false);
			if(IsApplServedAsCEOCouncel != null && IsApplServedAsCEOCouncel.equals("Y")){
				IsApplServedAsCEOCouncel = "Yes";
			}else if(IsApplServedAsCEOCouncel != null && IsApplServedAsCEOCouncel.equals("N")){
				IsApplServedAsCEOCouncel = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplServedAsCEOCouncel, excelFieldMapping.get(Constants.IsApplServedAsCEOCouncel), 0);
			
			String IsFinanInstProvideAggrement = ExcelUtility.getValueFromMap(financialMap, Constants.IsFinanInstProvideAggrement, false);
			if(IsFinanInstProvideAggrement != null && IsFinanInstProvideAggrement.equals("Y")){
				IsFinanInstProvideAggrement = "Yes";
			}else if(IsFinanInstProvideAggrement != null && IsFinanInstProvideAggrement.equals("N")){
				IsFinanInstProvideAggrement = "No";
			}
			ExcelUtility.setCellValue(sheet, IsFinanInstProvideAggrement, excelFieldMapping.get(Constants.IsFinanInstProvideAggrement), 0);
			
			String IsApplHadEquityInterest = ExcelUtility.getValueFromMap(financialMap, Constants.IsApplHadEquityInterest, false);
			if(IsApplHadEquityInterest != null && IsApplHadEquityInterest.equals("Y")){
				IsApplHadEquityInterest = "Yes";
			}else if(IsApplHadEquityInterest != null && IsApplHadEquityInterest.equals("N")){
				IsApplHadEquityInterest = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplHadEquityInterest, excelFieldMapping.get(Constants.IsApplHadEquityInterest), 0);
			
			String InterestOrCommAmounts = ExcelUtility.getValueFromMap(financialMap, Constants.InterestOrCommAmounts, false);
			ExcelUtility.setCellValue(sheet, InterestOrCommAmounts, excelFieldMapping.get(Constants.InterestOrCommAmounts), 0);
			
			String IsApplClientDeclaredInsolvent = ExcelUtility.getValueFromMap(financialMap, Constants.IsApplClientDeclaredInsolvent, false);
			if(IsApplClientDeclaredInsolvent != null && IsApplClientDeclaredInsolvent.equals("Y")){
				IsApplClientDeclaredInsolvent = "Yes";
			}else if(IsApplClientDeclaredInsolvent != null && IsApplClientDeclaredInsolvent.equals("N")){
				IsApplClientDeclaredInsolvent = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplClientDeclaredInsolvent, excelFieldMapping.get(Constants.IsApplClientDeclaredInsolvent), 0);
			
			String IsApplProvideServToCompliance = ExcelUtility.getValueFromMap(financialMap, Constants.IsApplProvideServToCompliance, false);
			if(IsApplProvideServToCompliance != null && IsApplProvideServToCompliance.equals("Y")){
				IsApplProvideServToCompliance = "Yes";
			}else if(IsApplProvideServToCompliance != null && IsApplProvideServToCompliance.equals("N")){
				IsApplProvideServToCompliance = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplProvideServToCompliance, excelFieldMapping.get(Constants.IsApplProvideServToCompliance), 0);
			
			String IsAppHaveKnwOfFidilityOff = ExcelUtility.getValueFromMap(financialMap, Constants.IsAppHaveKnwOfFidilityOff, false);
			if(IsAppHaveKnwOfFidilityOff != null && IsAppHaveKnwOfFidilityOff.equals("Y")){
				IsAppHaveKnwOfFidilityOff = "Yes";
			}else if(IsAppHaveKnwOfFidilityOff != null && IsAppHaveKnwOfFidilityOff.equals("N")){
				IsAppHaveKnwOfFidilityOff = "No";
			}
			ExcelUtility.setCellValue(sheet, IsAppHaveKnwOfFidilityOff, excelFieldMapping.get(Constants.IsAppHaveKnwOfFidilityOff), 0);
		}else{
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplPerformServiceToBankruptcy), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplServedAsCEOCouncel), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsFinanInstProvideAggrement), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplHadEquityInterest), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.InterestOrCommAmounts), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplClientDeclaredInsolvent), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplProvideServToCompliance), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsAppHaveKnwOfFidilityOff), 0);
		}
		//Set Ajax List Size
		String FinancialInsCount = "0";
		Object finanObj = ctx.get(Constants.FINANCIAL_INST_LIST);
		List finanList = null;
		if(finanObj != null && finanObj instanceof List){
			finanList = (ArrayList) finanObj;
			FinancialInsCount = String.valueOf(finanList.size());
		}
		ExcelUtility.setCellValue(sheet, FinancialInsCount, excelFieldMapping.get(Constants.FinancialInsCount), 0);
	}
}
