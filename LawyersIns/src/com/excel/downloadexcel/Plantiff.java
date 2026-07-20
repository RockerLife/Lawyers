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

public class Plantiff {
	public void getDataForPlantiff(Context ctx) throws Exception {
		Object objPlantiff = SqlResources.getSqlMapProcessor(ctx).findByKey("PlantiffSuppLW.findByKey", ctx);	
		ctx.put(Constants.PLANTIFF_FREEFORM_01, objPlantiff);
	
		Object listAOL = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOLFields", ctx);
		ctx.put(Constants.PLANTIFF_AOL_LIST, listAOL);
		
		Object listAttorney = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetAttorneyInPlaintiffForFirmLWList", ctx);
		ctx.put(Constants.PLANTIFF_ATTORNEY_LIST, listAttorney); 
	}
	
	public void populatePlantiffData(Context ctx, HSSFWorkbook wb) throws Exception{
		Sheet sheet = wb.getSheet("Plantiff");
        populateAol(ctx, sheet);
		populateAOLList(ctx, sheet);
		populateAttorneyList(ctx, sheet);
	}
	
	
	public void populateAttorneyList(Context ctx, Sheet sheet) throws Exception{
		Object instObj = ctx.get(Constants.PLANTIFF_ATTORNEY_LIST);
		if(instObj == null)
			return;
		
		List instList = null;
		if (instObj != null)
			instList = (ArrayList) instObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.PlaintiffAttorneyName);
		
		for(int i = rb_federal.getRownum() + instList.size(); i< rb_federal.getRownum() + 16; i++){
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		int rowtoshiftup_bld = -15 + instList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		for(int i = 0; i < instList.size(); i++) {
			Map map = (HashMap) instList.get(i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.PlaintiffAttorneyName, false), excelFieldMapping.get(Constants.PlaintiffAttorneyName), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.PlaintiffYrsExp, false), excelFieldMapping.get(Constants.PlaintiffYrsExp), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.PlaintiffPecOfTimeDev, false), excelFieldMapping.get(Constants.PlaintiffPecOfTimeDev), i);
		}
	}
	
	public void populateAol(Context ctx, Sheet sheet) throws Exception{
		Object objPlantiff = ctx.get(Constants.PLANTIFF_FREEFORM_01);
		Map plantiffMap = null;
		if (objPlantiff != null)
			plantiffMap = (HashMap) objPlantiff;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		
		if(plantiffMap != null){
			String NumberOfSuppotedStaffToPlantiff = ExcelUtility.getValueFromMap(plantiffMap, Constants.NumberOfSuppotedStaffToPlantiff, false);
			ExcelUtility.setCellValue(sheet, NumberOfSuppotedStaffToPlantiff, excelFieldMapping.get(Constants.NumberOfSuppotedStaffToPlantiff), 0);
			
			String NumberOfInjuryCasesIn12Month = ExcelUtility.getValueFromMap(plantiffMap, Constants.NumberOfInjuryCasesIn12Month, false);
			ExcelUtility.setCellValue(sheet, NumberOfInjuryCasesIn12Month, excelFieldMapping.get(Constants.NumberOfInjuryCasesIn12Month), 0);
			
			String NumberOfInjuryHandleByAttorney = ExcelUtility.getValueFromMap(plantiffMap, Constants.NumberOfInjuryHandleByAttorney, false);
			ExcelUtility.setCellValue(sheet, NumberOfInjuryHandleByAttorney, excelFieldMapping.get(Constants.NumberOfInjuryHandleByAttorney), 0);
			
			String PerOfCasesBeforeTrail = ExcelUtility.getValueFromMap(plantiffMap, Constants.PerOfCasesBeforeTrail, false);
			ExcelUtility.setCellValue(sheet, PerOfCasesBeforeTrail, excelFieldMapping.get(Constants.PerOfCasesBeforeTrail), 0);
			
			String PerOfCasesTriedToConclusion = ExcelUtility.getValueFromMap(plantiffMap, Constants.PerOfCasesTriedToConclusion, false);
			ExcelUtility.setCellValue(sheet, PerOfCasesTriedToConclusion, excelFieldMapping.get(Constants.PerOfCasesTriedToConclusion), 0);
			
			String PerOfCasesReferToApplicant = ExcelUtility.getValueFromMap(plantiffMap, Constants.PerOfCasesReferToApplicant, false);
			ExcelUtility.setCellValue(sheet, PerOfCasesReferToApplicant, excelFieldMapping.get(Constants.PerOfCasesReferToApplicant), 0);
			
			String IsApplAcceptCasesToStatuteOfLim = ExcelUtility.getValueFromMap(plantiffMap, Constants.IsApplAcceptCasesToStatuteOfLim, false);
			if(IsApplAcceptCasesToStatuteOfLim != null && IsApplAcceptCasesToStatuteOfLim.equals("Y")){
				IsApplAcceptCasesToStatuteOfLim = "Yes";
			}else if(IsApplAcceptCasesToStatuteOfLim != null && IsApplAcceptCasesToStatuteOfLim.equals("N")){
				IsApplAcceptCasesToStatuteOfLim = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplAcceptCasesToStatuteOfLim, excelFieldMapping.get(Constants.IsApplAcceptCasesToStatuteOfLim), 0);
			
			String IsApplAdvertiseByTelevision = ExcelUtility.getValueFromMap(plantiffMap, Constants.IsApplAdvertiseByTelevision, false);
			if(IsApplAdvertiseByTelevision != null && IsApplAdvertiseByTelevision.equals("Y")){
				IsApplAdvertiseByTelevision = "Yes";
			}else if(IsApplAdvertiseByTelevision != null && IsApplAdvertiseByTelevision.equals("N")){
				IsApplAdvertiseByTelevision = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplAdvertiseByTelevision, excelFieldMapping.get(Constants.IsApplAdvertiseByTelevision), 0);
			
			String IsApplAdvertiseByYellowpages = ExcelUtility.getValueFromMap(plantiffMap, Constants.IsApplAdvertiseByYellowpages, false);
			if(IsApplAdvertiseByYellowpages != null && IsApplAdvertiseByYellowpages.equals("Y")){
				IsApplAdvertiseByYellowpages = "Yes";
			}else if(IsApplAdvertiseByYellowpages != null && IsApplAdvertiseByYellowpages.equals("N")){
				IsApplAdvertiseByYellowpages = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplAdvertiseByYellowpages, excelFieldMapping.get(Constants.IsApplAdvertiseByYellowpages), 0);
			
			String IsApplAdvertiseByRadio = ExcelUtility.getValueFromMap(plantiffMap, Constants.IsApplAdvertiseByRadio, false);
			if(IsApplAdvertiseByRadio != null && IsApplAdvertiseByRadio.equals("Y")){
				IsApplAdvertiseByRadio = "Yes";
			}else if(IsApplAdvertiseByRadio != null && IsApplAdvertiseByRadio.equals("N")){
				IsApplAdvertiseByRadio = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplAdvertiseByRadio, excelFieldMapping.get(Constants.IsApplAdvertiseByRadio), 0);
			
			String IsApplAdvertiseByInternet = ExcelUtility.getValueFromMap(plantiffMap, Constants.IsApplAdvertiseByInternet, false);
			if(IsApplAdvertiseByInternet != null && IsApplAdvertiseByInternet.equals("Y")){
				IsApplAdvertiseByInternet = "Yes";
			}else if(IsApplAdvertiseByInternet != null && IsApplAdvertiseByInternet.equals("N")){
				IsApplAdvertiseByInternet = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplAdvertiseByInternet, excelFieldMapping.get(Constants.IsApplAdvertiseByInternet), 0);
			
			String IsApplAdvertiseByMagazine = ExcelUtility.getValueFromMap(plantiffMap, Constants.IsApplAdvertiseByMagazine, false);
			if(IsApplAdvertiseByMagazine != null && IsApplAdvertiseByMagazine.equals("Y")){
				IsApplAdvertiseByMagazine = "Yes";
			}else if(IsApplAdvertiseByMagazine != null && IsApplAdvertiseByMagazine.equals("N")){
				IsApplAdvertiseByMagazine = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplAdvertiseByMagazine, excelFieldMapping.get(Constants.IsApplAdvertiseByMagazine), 0);
			
			String IsApplAdvertiseByNewspaper = ExcelUtility.getValueFromMap(plantiffMap, Constants.IsApplAdvertiseByNewspaper, false);
			if(IsApplAdvertiseByNewspaper != null && IsApplAdvertiseByNewspaper.equals("Y")){
				IsApplAdvertiseByNewspaper = "Yes";
			}else if(IsApplAdvertiseByNewspaper != null && IsApplAdvertiseByNewspaper.equals("N")){
				IsApplAdvertiseByNewspaper = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplAdvertiseByNewspaper, excelFieldMapping.get(Constants.IsApplAdvertiseByNewspaper), 0);
			
			String IsAppAcceptRefersFromOtherFirms = ExcelUtility.getValueFromMap(plantiffMap, Constants.IsAppAcceptRefersFromOtherFirms, false);
			if(IsAppAcceptRefersFromOtherFirms != null && IsAppAcceptRefersFromOtherFirms.equals("Y")){
				IsAppAcceptRefersFromOtherFirms = "Yes";
			}else if(IsAppAcceptRefersFromOtherFirms != null && IsAppAcceptRefersFromOtherFirms.equals("N")){
				IsAppAcceptRefersFromOtherFirms = "No";
			}
			ExcelUtility.setCellValue(sheet, IsAppAcceptRefersFromOtherFirms, excelFieldMapping.get(Constants.IsAppAcceptRefersFromOtherFirms), 0);
			
			String IsRefAggrementReferToAppl = ExcelUtility.getValueFromMap(plantiffMap, Constants.IsRefAggrementReferToAppl, false);
			if(IsRefAggrementReferToAppl != null && IsRefAggrementReferToAppl.equals("Y")){
				IsRefAggrementReferToAppl = "Yes";
			}else if(IsRefAggrementReferToAppl != null && IsRefAggrementReferToAppl.equals("N")){
				IsRefAggrementReferToAppl = "No";
			}
			ExcelUtility.setCellValue(sheet, IsRefAggrementReferToAppl, excelFieldMapping.get(Constants.IsRefAggrementReferToAppl), 0);
			
			String IsRefAggrementReferToApplRefersOut = ExcelUtility.getValueFromMap(plantiffMap, Constants.IsRefAggrementReferToApplRefersOut, false);
			if(IsRefAggrementReferToApplRefersOut != null && IsRefAggrementReferToApplRefersOut.equals("Y")){
				IsRefAggrementReferToApplRefersOut = "Yes";
			}else if(IsRefAggrementReferToApplRefersOut != null && IsRefAggrementReferToApplRefersOut.equals("N")){
				IsRefAggrementReferToApplRefersOut = "No";
			}
			ExcelUtility.setCellValue(sheet, IsRefAggrementReferToApplRefersOut, excelFieldMapping.get(Constants.IsRefAggrementReferToApplRefersOut), 0);
			
			String IsAppConfirmCarryProfLiabIns = ExcelUtility.getValueFromMap(plantiffMap, Constants.IsAppConfirmCarryProfLiabIns, false);
			if(IsAppConfirmCarryProfLiabIns != null && IsAppConfirmCarryProfLiabIns.equals("Y")){
				IsAppConfirmCarryProfLiabIns = "Yes";
			}else if(IsAppConfirmCarryProfLiabIns != null && IsAppConfirmCarryProfLiabIns.equals("N")){
				IsAppConfirmCarryProfLiabIns = "No";
			}
			ExcelUtility.setCellValue(sheet, IsAppConfirmCarryProfLiabIns, excelFieldMapping.get(Constants.IsAppConfirmCarryProfLiabIns), 0);
			
			String IsSettlementAggrementsUsed = ExcelUtility.getValueFromMap(plantiffMap, Constants.IsSettlementAggrementsUsed, false);
			if(IsSettlementAggrementsUsed != null && IsSettlementAggrementsUsed.equals("Y")){
				IsSettlementAggrementsUsed = "Yes";
			}else if(IsSettlementAggrementsUsed != null && IsSettlementAggrementsUsed.equals("N")){
				IsSettlementAggrementsUsed = "No";
			}
			ExcelUtility.setCellValue(sheet, IsSettlementAggrementsUsed, excelFieldMapping.get(Constants.IsSettlementAggrementsUsed), 0);
		}else{
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.NumberOfSuppotedStaffToPlantiff), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.NumberOfInjuryCasesIn12Month), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.NumberOfInjuryHandleByAttorney), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.PerOfCasesBeforeTrail), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.PerOfCasesTriedToConclusion), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.PerOfCasesReferToApplicant), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplAcceptCasesToStatuteOfLim), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplAdvertiseByTelevision), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplAdvertiseByYellowpages), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplAdvertiseByRadio), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplAdvertiseByInternet), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplAdvertiseByMagazine), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplAdvertiseByNewspaper), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsAppAcceptRefersFromOtherFirms), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsRefAggrementReferToAppl), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsRefAggrementReferToApplRefersOut), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsAppConfirmCarryProfLiabIns), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsSettlementAggrementsUsed), 0);
		}
		//Set Ajax List Count
		String PlantiffAttorneyCount = "0";
		Object attObj = ctx.get(Constants.PLANTIFF_ATTORNEY_LIST);
		List attList = null;
		if(attObj != null && attObj instanceof List){
			attList = (ArrayList) attObj;
			PlantiffAttorneyCount = String.valueOf(attList.size());
		}
		ExcelUtility.setCellValue(sheet, PlantiffAttorneyCount, excelFieldMapping.get(Constants.PlantiffAttorneyCount), 0);
	}
	
	public void populateAOLList(Context ctx, Sheet sheet) throws Exception {
		Object objAol = ctx.get(Constants.PLANTIFF_AOL_LIST);
		if(objAol == null)
			return;
		
		List aolList = null;
		if (objAol != null)
			aolList = (ArrayList) objAol;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		for(int i=0; i<aolList.size(); i++) {
			Map map = (HashMap) aolList.get(i);			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.PercentageOfRevenue, false), excelFieldMapping.get(Constants.AOL_PercentageOfRevenue +"_" + i), 0);
			if(excelFieldMapping.get(Constants.AOL_CommentDesc +"_" + i) != null)
				ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.AOLCommentDesc, false), excelFieldMapping.get(Constants.AOL_CommentDesc +"_" + i), 0);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.AverageCaseSize, false), excelFieldMapping.get(Constants.AOL_AverageCaseSize +"_" + i), 0);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.LargestCaseSize, false), excelFieldMapping.get(Constants.AOL_LargestCaseSize +"_" + i), 0);
		}
		if(aolList.size() == 0){
			for(int i=0; i<13; i++) {
				ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.AOL_PercentageOfRevenue +"_" + i), 0);
				if(excelFieldMapping.get(Constants.AOL_CommentDesc +"_" + i) != null)
					ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.AOL_CommentDesc +"_" + i), 0);
				ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.AOL_AverageCaseSize +"_" + i), 0);
				ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.AOL_LargestCaseSize +"_" + i), 0);
			}
		}
	}
}
