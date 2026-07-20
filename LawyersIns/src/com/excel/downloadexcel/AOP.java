package com.excel.downloadexcel;

import com.util.InetLogger;

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

public class AOP {
	private static final InetLogger logger = InetLogger.getInetLogger(AOP.class);
	public void getDataForAop(Context ctx) throws Exception{
		Object objEntertainment = SqlResources.getSqlMapProcessor(ctx).findByKey("EntertainmentSportSuppLW.findByKey", ctx);	
		ctx.put(Constants.ENTERTAINMENT_FREEFORM_01, objEntertainment);
		
		Object listAOP = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields", ctx);
		ctx.put(Constants.AOP_AOP_LIST, listAOP);
		
		Object listAttorney = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetAttorneyInEntertainForFirmLWList", ctx);
		ctx.put(Constants.AOP_ATTORNEY_LIST, listAttorney); 
		
		Object listClients = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetEnterSportsClientOfFirmLWList", ctx);
		ctx.put(Constants.AOP_CLIENT_LIST, listClients); 
	}
	
	public void populateAopData(Context ctx, HSSFWorkbook wb) throws Exception{
		Sheet sheet = wb.getSheet("Aop");
		populateAop(ctx, sheet);
		populateAOPList(ctx, sheet);
		populateClientsList(ctx, sheet);
		populateAttorneyList(ctx, sheet);
	}
	
	public void populateAttorneyList(Context ctx, Sheet sheet) throws Exception{
		Object attorneyObj = ctx.get(Constants.AOP_ATTORNEY_LIST);
		if(attorneyObj == null)
			return;
		
		List attorneyList = null;
		if (attorneyObj != null)
			attorneyList = (ArrayList) attorneyObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.EntertainAttorneyName);
		
		for(int i = rb_federal.getRownum() + attorneyList.size(); i< rb_federal.getRownum() + 16; i++){
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		
		int rowtoshiftup_bld = -15 + attorneyList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		for(int i = 0; i < attorneyList.size(); i++) {
			Map map = (HashMap) attorneyList.get(i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.AttorneyName, false), excelFieldMapping.get(Constants.EntertainAttorneyName), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.NumberOfYrsInSpec, false), excelFieldMapping.get(Constants.NumberOfYrsInSpec), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.PercentageOfTimeDev, false), excelFieldMapping.get(Constants.PercentageOfTimeDev), i);
		}
	}
	
	public void populateClientsList(Context ctx, Sheet sheet) throws Exception{
		Object clientObj = ctx.get(Constants.AOP_CLIENT_LIST);
		if(clientObj == null)
			return;
		
		List clientList = null;
		if (clientObj != null)
			clientList = (ArrayList) clientObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.ClientName);
		
		for(int i = rb_federal.getRownum() + clientList.size(); i< rb_federal.getRownum() + 16; i++){
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		
		int rowtoshiftup_bld = -15 + clientList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		for(int i = 0; i < clientList.size(); i++) {
			Map map = (HashMap) clientList.get(i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.ClientName, false), excelFieldMapping.get(Constants.ClientName), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.FieldOfEntertainment, false), excelFieldMapping.get(Constants.FieldOfEntertainment), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.TypeOfServices, false), excelFieldMapping.get(Constants.TypeOfServices), i);
			
			if(map.get(Constants.ToDate) != null){
				String FromDate = map.get(Constants.FromDate).toString();
				FromDate = FromDate.substring(0, 11).replace("-", "/");
				logger.debug("from date " + FromDate);
				String ToDate = map.get(Constants.ToDate).toString();
				ToDate = ToDate.substring(0, 11).replace("-", "/");
				logger.debug("to date " + ToDate);
				FromDate = FromDate + " - " + ToDate;
				
			ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(FromDate), excelFieldMapping.get(Constants.FromDate), i);
			}else{
				ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(map.get(Constants.FromDate)), excelFieldMapping.get(Constants.FromDate), i);
			}
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.FromDate, false), excelFieldMapping.get(Constants.FromDate), i);
			String IsCurrentClient = ExcelUtility.getValueFromMap(map, Constants.IsCurrentClient, false);
			if(IsCurrentClient != null && IsCurrentClient.equals("Y")){
				IsCurrentClient = "Yes";
			}else if(IsCurrentClient != null && IsCurrentClient.equals("N")){
				IsCurrentClient = "No";
			}
			ExcelUtility.setCellValue(sheet, IsCurrentClient, excelFieldMapping.get(Constants.IsCurrentClient), i);
		}
	}
	
	public void populateAop(Context ctx, Sheet sheet) throws Exception{
		Object objFirm = ctx.get(Constants.FIRM_FREEFORM_01);
		Map firmMap = null;
		if(objFirm != null)
			firmMap = (Map)objFirm;
		
		Object objEntertain = ctx.get(Constants.ENTERTAINMENT_FREEFORM_01);
		Map entertainMap = null;
		if(objEntertain != null)
			entertainMap = (Map)objEntertain;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		
		if(firmMap != null){
			String IsFirmInvolvedInClassActionCase = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmInvolvedInClassActionCase, false);
			if(IsFirmInvolvedInClassActionCase != null && IsFirmInvolvedInClassActionCase.equals("Y")){
				IsFirmInvolvedInClassActionCase = "Yes";
			}else if(IsFirmInvolvedInClassActionCase != null && IsFirmInvolvedInClassActionCase.equals("N")){
				IsFirmInvolvedInClassActionCase = "No";
			}
			ExcelUtility.setCellValue(sheet, IsFirmInvolvedInClassActionCase, excelFieldMapping.get(Constants.IsFirmInvolvedInClassActionCase), 0);
			
			String IsFirmHaveClientInEntertainmentInd = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmHaveClientInEntertainmentInd, false);
			if(IsFirmHaveClientInEntertainmentInd != null && IsFirmHaveClientInEntertainmentInd.equals("Y")){
				IsFirmHaveClientInEntertainmentInd = "Yes";
			}else if(IsFirmHaveClientInEntertainmentInd != null && IsFirmHaveClientInEntertainmentInd.equals("N")){
				IsFirmHaveClientInEntertainmentInd = "No";
			}
			ExcelUtility.setCellValue(sheet, IsFirmHaveClientInEntertainmentInd, excelFieldMapping.get(Constants.IsFirmHaveClientInEntertainmentInd), 0);
			
			String IsFirmProvidedLegalServiceToSecurity = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmProvidedLegalServiceToSecurity, false);
			if(IsFirmProvidedLegalServiceToSecurity != null && IsFirmProvidedLegalServiceToSecurity.equals("Y")){
				IsFirmProvidedLegalServiceToSecurity = "Yes";
			}else if(IsFirmProvidedLegalServiceToSecurity != null && IsFirmProvidedLegalServiceToSecurity.equals("N")){
				IsFirmProvidedLegalServiceToSecurity = "No";
			}
			ExcelUtility.setCellValue(sheet, IsFirmProvidedLegalServiceToSecurity, excelFieldMapping.get(Constants.IsFirmProvidedLegalServiceToSecurity), 0);
			
			String IsFirmProvidedLegalServiceToInvestment = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmProvidedLegalServiceToInvestment, false);
			if(IsFirmProvidedLegalServiceToInvestment != null && IsFirmProvidedLegalServiceToInvestment.equals("Y")){
				IsFirmProvidedLegalServiceToInvestment = "Yes";
			}else if(IsFirmProvidedLegalServiceToInvestment != null && IsFirmProvidedLegalServiceToInvestment.equals("N")){
				IsFirmProvidedLegalServiceToInvestment = "No";
			}
			ExcelUtility.setCellValue(sheet, IsFirmProvidedLegalServiceToInvestment, excelFieldMapping.get(Constants.IsFirmProvidedLegalServiceToInvestment), 0);
			
			String IsFirmProvidedLegalServiceToMoney = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmProvidedLegalServiceToMoney, false);
			if(IsFirmProvidedLegalServiceToMoney != null && IsFirmProvidedLegalServiceToMoney.equals("Y")){
				IsFirmProvidedLegalServiceToMoney = "Yes";
			}else if(IsFirmProvidedLegalServiceToMoney != null && IsFirmProvidedLegalServiceToMoney.equals("N")){
				IsFirmProvidedLegalServiceToMoney = "No";
			}
			ExcelUtility.setCellValue(sheet, IsFirmProvidedLegalServiceToMoney, excelFieldMapping.get(Constants.IsFirmProvidedLegalServiceToMoney), 0);
			
			String IsFirmProvidedLegalServiceToInvClient = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmProvidedLegalServiceToInvClient, false);
			if(IsFirmProvidedLegalServiceToInvClient != null && IsFirmProvidedLegalServiceToInvClient.equals("Y")){
				IsFirmProvidedLegalServiceToInvClient = "Yes";
			}else if(IsFirmProvidedLegalServiceToInvClient != null && IsFirmProvidedLegalServiceToInvClient.equals("N")){
				IsFirmProvidedLegalServiceToInvClient = "No";
			}
			ExcelUtility.setCellValue(sheet, IsFirmProvidedLegalServiceToInvClient, excelFieldMapping.get(Constants.IsFirmProvidedLegalServiceToInvClient), 0);
		}
		
		if(entertainMap != null){
			String IsBusinessRelationWithApplEnt = ExcelUtility.getValueFromMap(entertainMap, Constants.IsBusinessRelationWithApplEnt, false);
			if(IsBusinessRelationWithApplEnt != null && IsBusinessRelationWithApplEnt.equals("Y")){
				IsBusinessRelationWithApplEnt = "Yes";
			}else if(IsBusinessRelationWithApplEnt != null && IsBusinessRelationWithApplEnt.equals("N")){
				IsBusinessRelationWithApplEnt = "No";
			}
			ExcelUtility.setCellValue(sheet, IsBusinessRelationWithApplEnt, excelFieldMapping.get(Constants.IsBusinessRelationWithApplEnt), 0);
			
			String IsApplSoughtHaveAuthForEntertain = ExcelUtility.getValueFromMap(entertainMap, Constants.IsApplSoughtHaveAuthForEntertain, false);
			if(IsApplSoughtHaveAuthForEntertain != null && IsApplSoughtHaveAuthForEntertain.equals("Y")){
				IsApplSoughtHaveAuthForEntertain = "Yes";
			}else if(IsApplSoughtHaveAuthForEntertain != null && IsApplSoughtHaveAuthForEntertain.equals("N")){
				IsApplSoughtHaveAuthForEntertain = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplSoughtHaveAuthForEntertain, excelFieldMapping.get(Constants.IsApplSoughtHaveAuthForEntertain), 0);
			
			String IsApplProvideInvestForEntertain = ExcelUtility.getValueFromMap(entertainMap, Constants.IsApplProvideInvestForEntertain, false);
			if(IsApplProvideInvestForEntertain != null && IsApplProvideInvestForEntertain.equals("Y")){
				IsApplProvideInvestForEntertain = "Yes";
			}else if(IsApplProvideInvestForEntertain != null && IsApplProvideInvestForEntertain.equals("N")){
				IsApplProvideInvestForEntertain = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplProvideInvestForEntertain, excelFieldMapping.get(Constants.IsApplProvideInvestForEntertain), 0);
			
			String IsApplServedAsTrustee = ExcelUtility.getValueFromMap(entertainMap, Constants.IsApplServedAsTrustee, false);
			if(IsApplServedAsTrustee != null && IsApplServedAsTrustee.equals("Y")){
				IsApplServedAsTrustee = "Yes";
			}else if(IsApplServedAsTrustee != null && IsApplServedAsTrustee.equals("N")){
				IsApplServedAsTrustee = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplServedAsTrustee, excelFieldMapping.get(Constants.IsApplServedAsTrustee), 0);
			
			String IsApplNagotiatePersonnelApp = ExcelUtility.getValueFromMap(entertainMap, Constants.IsApplNagotiatePersonnelApp, false);
			if(IsApplNagotiatePersonnelApp != null && IsApplNagotiatePersonnelApp.equals("Y")){
				IsApplNagotiatePersonnelApp = "Yes";
			}else if(IsApplNagotiatePersonnelApp != null && IsApplNagotiatePersonnelApp.equals("N")){
				IsApplNagotiatePersonnelApp = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplNagotiatePersonnelApp, excelFieldMapping.get(Constants.IsApplNagotiatePersonnelApp), 0);
			
			String ApplDetailsForCoverageSought = ExcelUtility.getValueFromMap(entertainMap, Constants.ApplDetailsForCoverageSought, false);
			ExcelUtility.setCellValue(sheet, ApplDetailsForCoverageSought, excelFieldMapping.get(Constants.ApplDetailsForCoverageSought), 0);
			
			String IsApplCovSoughtServeAsManager = ExcelUtility.getValueFromMap(entertainMap, Constants.IsApplCovSoughtServeAsManager, false);
			if(IsApplCovSoughtServeAsManager != null && IsApplCovSoughtServeAsManager.equals("Y")){
				IsApplCovSoughtServeAsManager = "Yes";
			}else if(IsApplCovSoughtServeAsManager != null && IsApplCovSoughtServeAsManager.equals("N")){
				IsApplCovSoughtServeAsManager = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplCovSoughtServeAsManager, excelFieldMapping.get(Constants.IsApplCovSoughtServeAsManager), 0);
			
			String ApplCovSoughtServeAsManagerDetails = ExcelUtility.getValueFromMap(entertainMap, Constants.ApplCovSoughtServeAsManagerDetails, false);
			ExcelUtility.setCellValue(sheet, ApplCovSoughtServeAsManagerDetails, excelFieldMapping.get(Constants.ApplCovSoughtServeAsManagerDetails), 0);
			
			String IsApplCoverageAcceptPercForFees = ExcelUtility.getValueFromMap(entertainMap, Constants.IsApplCoverageAcceptPercForFees, false);
			if(IsApplCoverageAcceptPercForFees != null && IsApplCoverageAcceptPercForFees.equals("Y")){
				IsApplCoverageAcceptPercForFees = "Yes";
			}else if(IsApplCoverageAcceptPercForFees != null && IsApplCoverageAcceptPercForFees.equals("N")){
				IsApplCoverageAcceptPercForFees = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplCoverageAcceptPercForFees, excelFieldMapping.get(Constants.IsApplCoverageAcceptPercForFees), 0);
			
			String IsApplCoverageAcceptCompensation = ExcelUtility.getValueFromMap(entertainMap, Constants.IsApplCoverageAcceptCompensation, false);
			if(IsApplCoverageAcceptCompensation != null && IsApplCoverageAcceptCompensation.equals("Y")){
				IsApplCoverageAcceptCompensation = "Yes";
			}else if(IsApplCoverageAcceptCompensation != null && IsApplCoverageAcceptCompensation.equals("N")){
				IsApplCoverageAcceptCompensation = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplCoverageAcceptCompensation, excelFieldMapping.get(Constants.IsApplCoverageAcceptCompensation), 0);
			
			String ApplCoverageAcceptDetails = ExcelUtility.getValueFromMap(entertainMap, Constants.ApplCoverageAcceptDetails, false);
			ExcelUtility.setCellValue(sheet, ApplCoverageAcceptDetails, excelFieldMapping.get(Constants.ApplCoverageAcceptDetails), 0);
		}else{
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsBusinessRelationWithApplEnt), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplSoughtHaveAuthForEntertain), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplProvideInvestForEntertain), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplServedAsTrustee), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplNagotiatePersonnelApp), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.ApplDetailsForCoverageSought), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplCovSoughtServeAsManager), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.ApplCovSoughtServeAsManagerDetails), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplCoverageAcceptPercForFees), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplCoverageAcceptCompensation), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.ApplCoverageAcceptDetails), 0);
		}
		
		//Set Ajax List Count
		String AOPAttorneyCount = "0";
		String AOPClientCount = "0";
		Object clientObj = ctx.get(Constants.AOP_CLIENT_LIST);
		List clientList = null;
		if(clientObj != null && clientObj instanceof List){
			clientList = (ArrayList) clientObj;
			AOPClientCount = String.valueOf(clientList.size());
		}
		Object attObj = ctx.get(Constants.AOP_ATTORNEY_LIST);
		List attList = null;
		if(attObj != null && attObj instanceof List){
			attList = (ArrayList) attObj;
			AOPAttorneyCount = String.valueOf(attList.size());
		}
		ExcelUtility.setCellValue(sheet, AOPAttorneyCount, excelFieldMapping.get(Constants.AOPAttorneyCount), 0);
		ExcelUtility.setCellValue(sheet, AOPClientCount, excelFieldMapping.get(Constants.AOPClientCount), 0);
	}
	
	public void populateAOPList(Context ctx, Sheet sheet) throws Exception {
		Object objAop = ctx.get(Constants.AOP_AOP_LIST);
		if(objAop == null)
			return;
		
		List aopList = null;
		if (objAop != null)
			aopList = (ArrayList) objAop;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		for(int i=0; i<aopList.size(); i++) {
			Map map = (HashMap) aopList.get(i);			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.PercentageValue, false), excelFieldMapping.get(Constants.AOP_Percentage +"_" + map.get("AOPKey")), 0);
			if(excelFieldMapping.get(Constants.AOPCommentDesc +"_" + i) != null){
				String comment = ExcelUtility.getValueFromMap(map, Constants.AOPCommentDesc, false);
				if(comment != null && !comment.equals("")){
					ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.AOPCommentDesc, false), excelFieldMapping.get(Constants.AOPCommentDesc +"_" + map.get("AOPKey")), 0);
				}else{
					ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.AOPCommentDesc +"_" + i), 0);
				}
			}
		}
		if(aopList.size() == 0){
			for(int i=0; i<26; i++) {
				ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.AOP_Percentage +"_" + i), 0);
				if(excelFieldMapping.get(Constants.AOPCommentDesc +"_" + i) != null){
					ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.AOPCommentDesc +"_" + i), 0);
				}
			}
		}
	}
}
