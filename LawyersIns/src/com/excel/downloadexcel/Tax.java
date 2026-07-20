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

public class Tax {
	public void getDataForTax(Context ctx) throws Exception {
		Object listAttorney = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetAttorneyHaveTaxWorkLWList", ctx);
		ctx.put(Constants.TAX_ATTORNEY_LIST, listAttorney); 
		
		Object listAOTP = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOTPFields", ctx);
		ctx.put(Constants.TAX_AOTP_LIST, listAOTP);
	}
	
	public void populateTaxData(Context ctx, HSSFWorkbook wb) throws Exception{
		Sheet sheet = wb.getSheet("Tax");
		populateTax(ctx, sheet);
		populateAOTPList(ctx, sheet);
		populateAttorneyList(ctx, sheet);
	}
	
	public void populateTax(Context ctx, Sheet sheet) throws Exception {
		//Set Ajax List Count
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		String TaxAttorneyCount = "0";
		Object attObj = ctx.get(Constants.TAX_ATTORNEY_LIST);
		List attList = null;
		if(attObj != null && attObj instanceof List){
			attList = (ArrayList) attObj;
			TaxAttorneyCount = String.valueOf(attList.size());
		}
		ExcelUtility.setCellValue(sheet, TaxAttorneyCount, excelFieldMapping.get(Constants.TaxAttorneyCount), 0);
	}
	
	public void populateAOTPList(Context ctx, Sheet sheet) throws Exception {
		Object objAol = ctx.get(Constants.TAX_AOTP_LIST);
		if(objAol == null)
			return;
		
		List aolList = null;
		if (objAol != null)
			aolList = (ArrayList) objAol;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		for(int i=0; i<aolList.size(); i++) {
			Map map = (HashMap) aolList.get(i);			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.PercentageValue, false), excelFieldMapping.get(Constants.AOTP_PercentageOfRevenue +"_" + i), 0);
			if(excelFieldMapping.get(Constants.AOTP_CommentDesc +"_" + i) != null)
				ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.AOTPCommentDesc, false), excelFieldMapping.get(Constants.AOTP_CommentDesc +"_" + i), 0);
		}
		if(aolList.size() == 0){
			for(int i=0; i<9; i++) {
				ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.AOTP_PercentageOfRevenue +"_" + i), 0);
				if(excelFieldMapping.get(Constants.AOTP_CommentDesc +"_" + i) != null)
					ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.AOTP_CommentDesc +"_" + i), 0);
			}
		}
	}
	
	public void populateAttorneyList(Context ctx, Sheet sheet) throws Exception{
		Object instObj = ctx.get(Constants.TAX_ATTORNEY_LIST);
		if(instObj == null)
			return;
		
		List instList = null;
		if (instObj != null)
			instList = (ArrayList) instObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.TaxAttorneyName);
		
		for(int i = rb_federal.getRownum() + instList.size(); i< rb_federal.getRownum() + 16; i++){
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		int rowtoshiftup_bld = -15 + instList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		for(int i = 0; i < instList.size(); i++) {
			Map map = (HashMap) instList.get(i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.TaxAttorneyName, false), excelFieldMapping.get(Constants.TaxAttorneyName), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.NumberOfYrOfTaxExp, false), excelFieldMapping.get(Constants.NumberOfYrOfTaxExp), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.PercentOfTimeDevoted, false), excelFieldMapping.get(Constants.PercentOfTaxTimeDevoted), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.IsCertifiedTax, false), excelFieldMapping.get(Constants.IsCertifiedTax), i);
		}
	}
}
