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

public class Environmental {
	public void getDataForEnvironmental(Context ctx) throws Exception {
		Object objEnv = SqlResources.getSqlMapProcessor(ctx).findByKey("EnvironmentalSuppLW.findByKey", ctx);	
		ctx.put(Constants.ENVIRONMENTAL_FREEFORM_01, objEnv);
		
		Object listAttorney = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetAttorneyEnvWorkLWList", ctx);
		ctx.put(Constants.ENV_ATTORNEY_LIST, listAttorney); 
		
		Object listClients = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetFirmEnvClientsLWList", ctx);
		ctx.put(Constants.ENV_CLIENT_LIST, listClients); 
		
		Object listContr = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetContractorEnvLWList", ctx);
		ctx.put(Constants.ENV_CONTRACTOR_LIST, listContr);
	}
	
	public void populateEnvironmentalData(Context ctx, HSSFWorkbook wb) throws Exception{
		Sheet sheet = wb.getSheet("Environmental");
		populateEnvironmental(ctx, sheet);
		populateContractorList(ctx, sheet);
		populateClientsList(ctx, sheet);
		populateAttorneyList(ctx, sheet);
	}
	
	public void populateAttorneyList(Context ctx, Sheet sheet) throws Exception{
		Object attorneyObj = ctx.get(Constants.ENV_ATTORNEY_LIST);
		if(attorneyObj == null)
			return;
		
		List attorneyList = null;
		if (attorneyObj != null)
			attorneyList = (ArrayList) attorneyObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.EnvAttorneyName);
		
		for(int i = rb_federal.getRownum() + attorneyList.size(); i< rb_federal.getRownum() + 16; i++){
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		int rowtoshiftup_bld = -15 + attorneyList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		for(int i = 0; i < attorneyList.size(); i++) {
			Map map = (HashMap) attorneyList.get(i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.EnvAttorneyName, false), excelFieldMapping.get(Constants.EnvAttorneyName), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.NumberOfYrOfEnvExp, false), excelFieldMapping.get(Constants.NumberOfYrOfEnvExp), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.PercentOfTimeDevoted, false), excelFieldMapping.get(Constants.PercentOfTimeDevoted), i);
		}
	}
	
	public void populateClientsList(Context ctx, Sheet sheet) throws Exception{
		Object clientObj = ctx.get(Constants.ENV_CLIENT_LIST);
		if(clientObj == null)
			return;
		
		List clientList = null;
		if (clientObj != null)
			clientList = (ArrayList) clientObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.EnvClientName);
		
		for(int i = rb_federal.getRownum() + clientList.size(); i< rb_federal.getRownum() + 16; i++){
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	if(row != null)
        		sheet.removeRow(row);
        }
		int rowtoshiftup_bld = -15 + clientList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		for(int i = 0; i < clientList.size(); i++) {
			Map map = (HashMap) clientList.get(i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.EnvClientName, false), excelFieldMapping.get(Constants.EnvClientName), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.TypesOfWork, false), excelFieldMapping.get(Constants.TypesOfWork), i);
		}
	}
	
	public void populateContractorList(Context ctx, Sheet sheet) throws Exception{
		Object contObj = ctx.get(Constants.ENV_CONTRACTOR_LIST);
		if(contObj == null)
			return;
		
		List contList = null;
		if (contObj != null)
			contList = (ArrayList) contObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.ContractorName);
		
		for(int i = rb_federal.getRownum() + contList.size(); i< rb_federal.getRownum() + 16; i++){
        	HSSFRow row = (HSSFRow) sheet.getRow(i);
        	if(row != null)
        		sheet.removeRow(row);
        }
		
		int rowtoshiftup_bld = -15 + contList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		for(int i = 0; i < contList.size(); i++) {
			Map map = (HashMap) contList.get(i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.ContractorName, false), excelFieldMapping.get(Constants.ContractorName), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.ContServicesPerformed, false), excelFieldMapping.get(Constants.ContServicesPerformed), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.PercentOfEnvGBillings, false), excelFieldMapping.get(Constants.PercentOfEnvGBillings), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.IsEvidenceRequired, false), excelFieldMapping.get(Constants.IsEvidenceRequired), i);
		}
	}
	
	public void populateEnvironmental(Context ctx, Sheet sheet) throws Exception{
		Object objEnv = ctx.get(Constants.ENVIRONMENTAL_FREEFORM_01);
		Map envMap = null;
		if (objEnv != null)
			envMap = (HashMap) objEnv;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		
		if(envMap != null){
			String IsApplRenderLiabForCleanupExp = ExcelUtility.getValueFromMap(envMap, Constants.IsApplRenderLiabForCleanupExp, false);
			if(IsApplRenderLiabForCleanupExp != null && IsApplRenderLiabForCleanupExp.equals("Y")){
				IsApplRenderLiabForCleanupExp = "Yes";
			}else if(IsApplRenderLiabForCleanupExp != null && IsApplRenderLiabForCleanupExp.equals("N")){
				IsApplRenderLiabForCleanupExp = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplRenderLiabForCleanupExp, excelFieldMapping.get(Constants.IsApplRenderLiabForCleanupExp), 0);
		
			String IsApplRenderEnvLaws = ExcelUtility.getValueFromMap(envMap, Constants.IsApplRenderEnvLaws, false);
			if(IsApplRenderEnvLaws != null && IsApplRenderEnvLaws.equals("Y")){
				IsApplRenderEnvLaws = "Yes";
			}else if(IsApplRenderEnvLaws != null && IsApplRenderEnvLaws.equals("N")){
				IsApplRenderEnvLaws = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplRenderEnvLaws, excelFieldMapping.get(Constants.IsApplRenderEnvLaws), 0);
		
			String IsApplRecomEnvAudits = ExcelUtility.getValueFromMap(envMap, Constants.IsApplRecomEnvAudits, false);
			if(IsApplRecomEnvAudits != null && IsApplRecomEnvAudits.equals("Y")){
				IsApplRecomEnvAudits = "Yes";
			}else if(IsApplRecomEnvAudits != null && IsApplRecomEnvAudits.equals("N")){
				IsApplRecomEnvAudits = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplRecomEnvAudits, excelFieldMapping.get(Constants.IsApplRecomEnvAudits), 0);
		
			String IsApplRefOfEnvConsultant = ExcelUtility.getValueFromMap(envMap, Constants.IsApplRefOfEnvConsultant, false);
			if(IsApplRefOfEnvConsultant != null && IsApplRefOfEnvConsultant.equals("Y")){
				IsApplRefOfEnvConsultant = "Yes";
			}else if(IsApplRefOfEnvConsultant != null && IsApplRefOfEnvConsultant.equals("N")){
				IsApplRefOfEnvConsultant = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplRefOfEnvConsultant, excelFieldMapping.get(Constants.IsApplRefOfEnvConsultant), 0);
		
			String IsCleintRespForEngagementRisks = ExcelUtility.getValueFromMap(envMap, Constants.IsCleintRespForEngagementRisks, false);
			if(IsCleintRespForEngagementRisks != null && IsCleintRespForEngagementRisks.equals("Y")){
				IsCleintRespForEngagementRisks = "Yes";
			}else if(IsCleintRespForEngagementRisks != null && IsCleintRespForEngagementRisks.equals("N")){
				IsCleintRespForEngagementRisks = "No";
			}
			ExcelUtility.setCellValue(sheet, IsCleintRespForEngagementRisks, excelFieldMapping.get(Constants.IsCleintRespForEngagementRisks), 0);
		
			String IsApplInterpretEnvCompAudits = ExcelUtility.getValueFromMap(envMap, Constants.IsApplInterpretEnvCompAudits, false);
			if(IsApplInterpretEnvCompAudits != null && IsApplInterpretEnvCompAudits.equals("Y")){
				IsApplInterpretEnvCompAudits = "Yes";
			}else if(IsApplInterpretEnvCompAudits != null && IsApplInterpretEnvCompAudits.equals("N")){
				IsApplInterpretEnvCompAudits = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplInterpretEnvCompAudits, excelFieldMapping.get(Constants.IsApplInterpretEnvCompAudits), 0);
		
			String IsClientsDiscloseEnvLiability = ExcelUtility.getValueFromMap(envMap, Constants.IsClientsDiscloseEnvLiability, false);
			if(IsClientsDiscloseEnvLiability != null && IsClientsDiscloseEnvLiability.equals("Y")){
				IsClientsDiscloseEnvLiability = "Yes";
			}else if(IsClientsDiscloseEnvLiability != null && IsClientsDiscloseEnvLiability.equals("N")){
				IsClientsDiscloseEnvLiability = "No";
			}
			ExcelUtility.setCellValue(sheet, IsClientsDiscloseEnvLiability, excelFieldMapping.get(Constants.IsClientsDiscloseEnvLiability), 0);
		
			String IsClientFinedForViolatingStateFedLaws = ExcelUtility.getValueFromMap(envMap, Constants.IsClientFinedForViolatingStateFedLaws, false);
			if(IsClientFinedForViolatingStateFedLaws != null && IsClientFinedForViolatingStateFedLaws.equals("Y")){
				IsClientFinedForViolatingStateFedLaws = "Yes";
			}else if(IsClientFinedForViolatingStateFedLaws != null && IsClientFinedForViolatingStateFedLaws.equals("N")){
				IsClientFinedForViolatingStateFedLaws = "No";
			}
			ExcelUtility.setCellValue(sheet, IsClientFinedForViolatingStateFedLaws, excelFieldMapping.get(Constants.IsClientFinedForViolatingStateFedLaws), 0);
		}else{
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsClientFinedForViolatingStateFedLaws), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsClientsDiscloseEnvLiability), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplInterpretEnvCompAudits), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsCleintRespForEngagementRisks), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplRefOfEnvConsultant), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplRecomEnvAudits), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplRenderEnvLaws), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplRenderLiabForCleanupExp), 0);
		}
		//Set Ajax List Size
		String EnvAttorneyCount = "0";
		String EnvClientCount = "0";
		String EnvContCount = "0";
		Object clientObj = ctx.get(Constants.ENV_CLIENT_LIST);
		List clientList = null;
		if(clientObj != null && clientObj instanceof List){
			clientList = (ArrayList) clientObj;
			EnvClientCount = String.valueOf(clientList.size());
		}
		Object attObj = ctx.get(Constants.ENV_ATTORNEY_LIST);
		List attList = null;
		if(attObj != null && attObj instanceof List){
			attList = (ArrayList) attObj;
			EnvAttorneyCount = String.valueOf(attList.size());
		}
		Object contObj = ctx.get(Constants.ENV_CONTRACTOR_LIST);
		List contList = null;
		if(contObj != null && contObj instanceof List){
			contList = (ArrayList) contObj;
			EnvContCount = String.valueOf(contList.size());
		}
		ExcelUtility.setCellValue(sheet, EnvAttorneyCount, excelFieldMapping.get(Constants.EnvAttorneyCount), 0);
		ExcelUtility.setCellValue(sheet, EnvClientCount, excelFieldMapping.get(Constants.EnvClientCount), 0);
		ExcelUtility.setCellValue(sheet, EnvContCount, excelFieldMapping.get(Constants.EnvContCount), 0);
	}	
	
}
