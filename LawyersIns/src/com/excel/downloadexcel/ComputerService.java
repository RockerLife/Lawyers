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

public class ComputerService {
	
	public void getDataForComputerService(Context ctx) throws Exception
	{
		Object listITServicesSupp =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.computer-servicesviewgetITServicesSupp", ctx);
		ctx.put(Constants.COMPUTERSERVICE_LIST_03, listITServicesSupp);
	}
	
	
	public void populateComputerServiceData(Context ctx, HSSFWorkbook wb) throws Exception
	{
		Sheet sheet = wb.getSheet("Supp Computer Related Services");
        CellStyle string= wb.createCellStyle();
        string.setDataFormat((short) 38);
        CellStyle numeric= wb.createCellStyle();
        numeric.setDataFormat((short) 38);
        
        Object objFirm = ctx.get(Constants.FIRM_FREEFORM_01);
		
//		if (objFirm == null){
//			return;
//		}
		
		Map firmMap = null;
		if (objFirm != null)
			firmMap = (HashMap) objFirm;

		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		
		//ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.OtherITServiceComment, false), excelFieldMapping.get(Constants.OtherITServiceComment), 0);
		populateComputerServiceList(ctx, sheet);		

	}
	
	public void populateComputerServiceList(Context ctx, Sheet sheet) throws Exception
	{
		Object attestationObj = ctx.get(Constants.COMPUTERSERVICE_LIST_03);
		if(attestationObj == null)
			return;
		
		List attestationList = null;
		if (attestationObj != null)
			attestationList = (ArrayList) attestationObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.ITServiceCategoryDesc);
		
		for(int i = rb_federal.getRownum() + attestationList.size(); i< rb_federal.getRownum() + 11; i++)
        {
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		
		int rowtoshiftup_bld = -10 + attestationList.size();
		sheet.shiftRows(rb_federal.getRownum() + 11, sheet.getLastRowNum(), rowtoshiftup_bld);
		
		for (int i = 0; i < attestationList.size(); i++) {

			Map map = (HashMap) attestationList.get(i);
			
			/*ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.ITServiceCategoryDesc, false), excelFieldMapping.get(Constants.ITServiceCategoryDesc), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.RevenuesFromLastYear, false), excelFieldMapping.get(Constants.RevenuesFromLastYear), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.EstimatedRevenue, false), excelFieldMapping.get(Constants.EstimatedRevenue), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.IsAnnually, true), excelFieldMapping.get(Constants.IsAnnually), i);
			*/
		}
	}
}
