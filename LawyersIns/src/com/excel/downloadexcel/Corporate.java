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

public class Corporate {
	public void getDataForCorporate(Context ctx) throws Exception {
		Object ccbList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateCCBFields", ctx);	
		ctx.put(Constants.CORPORATE_CCB_LIST, ccbList);
	}
	
	public void populateCorporateData(Context ctx, HSSFWorkbook wb) throws Exception{
		Sheet sheet = wb.getSheet("Corporate");
		populateCCBList(ctx, sheet);
	}
	
	public void populateCCBList(Context ctx, Sheet sheet) throws Exception {
		Object objCCB = ctx.get(Constants.CORPORATE_CCB_LIST);
		if(objCCB == null)
			return;
		
		List ccbList = null;
		if (objCCB != null)
			ccbList = (ArrayList) objCCB;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		for(int i=0; i<ccbList.size(); i++) {
			Map map = (HashMap) ccbList.get(i);
			String CCBCheckedValue = ExcelUtility.getValueFromMap(map, Constants.CCBCheckedValue, false);
			if(CCBCheckedValue != null && CCBCheckedValue.equals("Y")){
				CCBCheckedValue = "Yes";
			}else if(CCBCheckedValue != null && CCBCheckedValue.equals("N")){
				CCBCheckedValue = "No";
			}else{
				CCBCheckedValue = "";
			}
			ExcelUtility.setCellValue(sheet, CCBCheckedValue, excelFieldMapping.get(Constants.CCB_CheckedValue +"_" + i), 0);
			if(excelFieldMapping.get(Constants.CCB_CommentDesc +"_" + i) != null){
				String comment = ExcelUtility.getValueFromMap(map, Constants.CCBCommentDesc, false);
				if(comment != null && !comment.equals("")){
					ExcelUtility.setCellValue(sheet, comment, excelFieldMapping.get(Constants.CCB_CommentDesc +"_" + i), 0);
				}else{
					ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.CCB_CommentDesc +"_" + i), 0);
				}
			}	
		}
		if(ccbList.size() == 0){
			for(int i=0; i<12; i++) {
				ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.CCB_CheckedValue +"_" + i), 0);
				ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.CCB_CommentDesc +"_" + i), 0);
			}
		}
	}
}
