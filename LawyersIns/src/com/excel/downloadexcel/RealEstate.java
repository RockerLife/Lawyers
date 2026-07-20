package com.excel.downloadexcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import com.excel.oldpim.ExcelUtility;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;

public class RealEstate {
	public void getDataForRealEstate(Context ctx) throws Exception {
		Object objRealEstate = SqlResources.getSqlMapProcessor(ctx).findByKey("RealEstateSuppLW.findByKey", ctx);	
		ctx.put(Constants.REALESTATE_FREEFORM_01, objRealEstate);
	
		Object listAOS = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOPREFields", ctx);
		ctx.put(Constants.REALESTATE_AOS_LIST, listAOS);
	}
	
	public void populateRealEstateData(Context ctx, HSSFWorkbook wb) throws Exception{
		Sheet sheet = wb.getSheet("Real Estate");
		populateRealEstate(ctx, sheet);
		populateAOSList(ctx, sheet);
	}
	
	public void populateAOSList(Context ctx, Sheet sheet) throws Exception {
		Object objAos = ctx.get(Constants.REALESTATE_AOS_LIST);
		if(objAos == null)
			return;
		
		List aosList = null;
		if (objAos != null)
			aosList = (ArrayList) objAos;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		for(int i=0; i<aosList.size(); i++) {
			Map map = (HashMap) aosList.get(i);			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.PercentageValue, false), excelFieldMapping.get(Constants.AOS_PercentageOfRevenue +"_" + i), 0);
			if(excelFieldMapping.get(Constants.AOPRE_CommentDesc +"_" + i) != null)
				ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.AOPRECommentDesc, false), excelFieldMapping.get(Constants.AOPRE_CommentDesc +"_" + i), 0);
		}
		if(aosList.size() == 0){
			for(int i=0; i<17; i++) {
				ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.AOS_PercentageOfRevenue +"_" + i), 0);
				if(excelFieldMapping.get(Constants.AOPRE_CommentDesc +"_" + i) != null)
					ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.AOPRE_CommentDesc +"_" + i), 0);
			}
		}
	}
	
	public void populateRealEstate(Context ctx, Sheet sheet) throws Exception{
		Object objRealEstate = ctx.get(Constants.REALESTATE_FREEFORM_01);
		Map realEstateMap = null;
		if (objRealEstate != null)
			realEstateMap = (HashMap) objRealEstate;
	
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		
		if(realEstateMap != null){
			String ApproxNumberOfPurchaseForResi = ExcelUtility.getValueFromMap(realEstateMap, Constants.ApproxNumberOfPurchaseForResi, false);
			ExcelUtility.setCellValue(sheet, ApproxNumberOfPurchaseForResi, excelFieldMapping.get(Constants.ApproxNumberOfPurchaseForResi), 0);
	
			String ApproxNumberOfPurchaseForComm = ExcelUtility.getValueFromMap(realEstateMap, Constants.ApproxNumberOfPurchaseForComm, false);
			ExcelUtility.setCellValue(sheet, ApproxNumberOfPurchaseForComm, excelFieldMapping.get(Constants.ApproxNumberOfPurchaseForComm), 0);
	
			String LargestPurchaseForResi = ExcelUtility.getValueFromMap(realEstateMap, Constants.LargestPurchaseForResi, false);
			ExcelUtility.setCellValue(sheet, "$"+LargestPurchaseForResi, excelFieldMapping.get(Constants.LargestPurchaseForResi), 0);
	
			String LasrgestPurchaseForComm = ExcelUtility.getValueFromMap(realEstateMap, Constants.LasrgestPurchaseForComm, false);
			ExcelUtility.setCellValue(sheet, "$"+LasrgestPurchaseForComm, excelFieldMapping.get(Constants.LasrgestPurchaseForComm), 0);
	
			String IsApplPerfEscowServices = ExcelUtility.getValueFromMap(realEstateMap, Constants.IsApplPerfEscowServices, false);
			if(IsApplPerfEscowServices != null && IsApplPerfEscowServices.equals("Y")){
				IsApplPerfEscowServices = "Yes";
			}else if(IsApplPerfEscowServices != null && IsApplPerfEscowServices.equals("N")){
				IsApplPerfEscowServices = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplPerfEscowServices, excelFieldMapping.get(Constants.IsApplPerfEscowServices), 0);
			
			String IsApplRenderedRealPropMatters = ExcelUtility.getValueFromMap(realEstateMap, Constants.IsApplRenderedRealPropMatters, false);
			if(IsApplRenderedRealPropMatters != null && IsApplRenderedRealPropMatters.equals("Y")){
				IsApplRenderedRealPropMatters = "Yes";
			}else if(IsApplRenderedRealPropMatters != null && IsApplRenderedRealPropMatters.equals("N")){
				IsApplRenderedRealPropMatters = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplRenderedRealPropMatters, excelFieldMapping.get(Constants.IsApplRenderedRealPropMatters), 0);
			
			String IsFirmRequireInvestOfEnvRisks = ExcelUtility.getValueFromMap(realEstateMap, Constants.IsFirmRequireInvestOfEnvRisks, false);
			if(IsFirmRequireInvestOfEnvRisks != null && IsFirmRequireInvestOfEnvRisks.equals("Y")){
				IsFirmRequireInvestOfEnvRisks = "Yes";
			}else if(IsFirmRequireInvestOfEnvRisks != null && IsFirmRequireInvestOfEnvRisks.equals("N")){
				IsFirmRequireInvestOfEnvRisks = "No";
			}
			ExcelUtility.setCellValue(sheet, IsFirmRequireInvestOfEnvRisks, excelFieldMapping.get(Constants.IsFirmRequireInvestOfEnvRisks), 0);
		}else{
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.ApproxNumberOfPurchaseForResi), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.ApproxNumberOfPurchaseForComm), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.LargestPurchaseForResi), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.LasrgestPurchaseForComm), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplPerfEscowServices), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplRenderedRealPropMatters), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsFirmRequireInvestOfEnvRisks), 0);
		}
	}
}
