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

public class WillsEstate {
	public void getDataForWillsEstate(Context ctx) throws Exception {
		Object objWills = SqlResources.getSqlMapProcessor(ctx).findByKey("WillsTrustsEstateSuppLW.findByKey", ctx);	
		ctx.put(Constants.WILLSESTATE_FREEFORM_01, objWills);
	
		Object wesList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateWESFields", ctx);	
		ctx.put(Constants.WILLSESTATE_WES_LIST, wesList);
	}
	
	public void populateWillsEstateData(Context ctx, HSSFWorkbook wb) throws Exception{
		Sheet sheet = wb.getSheet("Wills & Estate");
		populateWillsEstate(ctx, sheet);
		populateWESList(ctx, sheet);
	}
	
	public void populateWillsEstate(Context ctx, Sheet sheet) throws Exception {
		Object objWills = ctx.get(Constants.WILLSESTATE_FREEFORM_01);
		Map willsMap = null;
		if (objWills != null)
			willsMap = (HashMap) objWills;
	
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		
		if(willsMap != null){
			String NumberOfEstateOver1Million = ExcelUtility.getValueFromMap(willsMap, Constants.NumberOfEstateOver1Million, false);
			ExcelUtility.setCellValue(sheet, NumberOfEstateOver1Million, excelFieldMapping.get(Constants.NumberOfEstateOver1Million), 0);
			
			String NumberOfEstateOver5Million = ExcelUtility.getValueFromMap(willsMap, Constants.NumberOfEstateOver5Million, false);
			ExcelUtility.setCellValue(sheet, NumberOfEstateOver5Million, excelFieldMapping.get(Constants.NumberOfEstateOver5Million), 0);
			
			String NumberOfEstateOver10Million = ExcelUtility.getValueFromMap(willsMap, Constants.NumberOfEstateOver10Million, false);
			ExcelUtility.setCellValue(sheet, NumberOfEstateOver10Million, excelFieldMapping.get(Constants.NumberOfEstateOver10Million), 0);
			
			String IsAttorneyServeAsExecutorTrustee = ExcelUtility.getValueFromMap(willsMap, Constants.IsAttorneyServeAsExecutorTrustee, false);
			if(IsAttorneyServeAsExecutorTrustee != null && IsAttorneyServeAsExecutorTrustee.equals("Y")){
				IsAttorneyServeAsExecutorTrustee = "Yes";
			}else if(IsAttorneyServeAsExecutorTrustee != null && IsAttorneyServeAsExecutorTrustee.equals("N")){
				IsAttorneyServeAsExecutorTrustee = "No";
			}
			ExcelUtility.setCellValue(sheet, IsAttorneyServeAsExecutorTrustee, excelFieldMapping.get(Constants.IsAttorneyServeAsExecutorTrustee), 0);
			
			String ListOfAttWithNameServicesClient = ExcelUtility.getValueFromMap(willsMap, Constants.ListOfAttWithNameServicesClient, false);
			ExcelUtility.setCellValue(sheet, ListOfAttWithNameServicesClient, excelFieldMapping.get(Constants.ListOfAttWithNameServicesClient), 0);
			
			String IsApplProvideInvestAdviceToClient = ExcelUtility.getValueFromMap(willsMap, Constants.IsApplProvideInvestAdviceToClient, false);
			if(IsApplProvideInvestAdviceToClient != null && IsApplProvideInvestAdviceToClient.equals("Y")){
				IsApplProvideInvestAdviceToClient = "Yes";
			}else if(IsApplProvideInvestAdviceToClient != null && IsApplProvideInvestAdviceToClient.equals("N")){
				IsApplProvideInvestAdviceToClient = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplProvideInvestAdviceToClient, excelFieldMapping.get(Constants.IsApplProvideInvestAdviceToClient), 0);
			
			String IsMemCertLegalSpecialist = ExcelUtility.getValueFromMap(willsMap, Constants.IsMemCertLegalSpecialist, false);
			if(IsMemCertLegalSpecialist != null && IsMemCertLegalSpecialist.equals("Y")){
				IsMemCertLegalSpecialist = "Yes";
			}else if(IsMemCertLegalSpecialist != null && IsMemCertLegalSpecialist.equals("N")){
				IsMemCertLegalSpecialist = "No";
			}
			ExcelUtility.setCellValue(sheet, IsMemCertLegalSpecialist, excelFieldMapping.get(Constants.IsMemCertLegalSpecialist), 0);
			
			String IsApplPolicyReviewBySecondAttorney = ExcelUtility.getValueFromMap(willsMap, Constants.IsApplPolicyReviewBySecondAttorney, false);
			if(IsApplPolicyReviewBySecondAttorney != null && IsApplPolicyReviewBySecondAttorney.equals("Y")){
				IsApplPolicyReviewBySecondAttorney = "Yes";
			}else if(IsApplPolicyReviewBySecondAttorney != null && IsApplPolicyReviewBySecondAttorney.equals("N")){
				IsApplPolicyReviewBySecondAttorney = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplPolicyReviewBySecondAttorney, excelFieldMapping.get(Constants.IsApplPolicyReviewBySecondAttorney), 0);
			
			String IsApplTaxInConjucWithTrustWork = ExcelUtility.getValueFromMap(willsMap, Constants.IsApplTaxInConjucWithTrustWork, false);
			if(IsApplTaxInConjucWithTrustWork != null && IsApplTaxInConjucWithTrustWork.equals("Y")){
				IsApplTaxInConjucWithTrustWork = "Yes";
			}else if(IsApplTaxInConjucWithTrustWork != null && IsApplTaxInConjucWithTrustWork.equals("N")){
				IsApplTaxInConjucWithTrustWork = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplTaxInConjucWithTrustWork, excelFieldMapping.get(Constants.IsApplTaxInConjucWithTrustWork), 0);
			
			String IsApplObtainCertificate = ExcelUtility.getValueFromMap(willsMap, Constants.IsApplObtainCertificate, false);
			if(IsApplObtainCertificate != null && IsApplObtainCertificate.equals("Y")){
				IsApplObtainCertificate = "Yes";
			}else if(IsApplObtainCertificate != null && IsApplObtainCertificate.equals("N")){
				IsApplObtainCertificate = "No";
			}
			ExcelUtility.setCellValue(sheet, IsApplObtainCertificate, excelFieldMapping.get(Constants.IsApplObtainCertificate), 0);
		}else{
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.NumberOfEstateOver1Million), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.NumberOfEstateOver5Million), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.NumberOfEstateOver10Million), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsAttorneyServeAsExecutorTrustee), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.ListOfAttWithNameServicesClient), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplProvideInvestAdviceToClient), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsMemCertLegalSpecialist), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplPolicyReviewBySecondAttorney), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplTaxInConjucWithTrustWork), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsApplObtainCertificate), 0);
		}
	}
	
	public void populateWESList(Context ctx, Sheet sheet) throws Exception {
		Object objWES = ctx.get(Constants.WILLSESTATE_WES_LIST);
		if(objWES == null)
			return;
		
		List wesList = null;
		if (objWES != null)
			wesList = (ArrayList) objWES;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		for(int i=0; i<wesList.size(); i++) {
			Map map = (HashMap) wesList.get(i);
			String CheckedValue = ExcelUtility.getValueFromMap(map, Constants.CheckedValue, false);
			if(CheckedValue != null && CheckedValue.equals("Y")){
				CheckedValue = "Yes";
			}else if(CheckedValue != null && CheckedValue.equals("N")){
				CheckedValue = "No";
			} 
			ExcelUtility.setCellValue(sheet, CheckedValue, excelFieldMapping.get(Constants.WES_CheckedValue +"_" + i), 0);
			if(excelFieldMapping.get(Constants.WES_CommentDesc +"_" + i) != null)
				ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.WESCommentDesc, false), excelFieldMapping.get(Constants.WES_CommentDesc +"_" + i), 0);
		}
		if(wesList.size() == 0){
			for(int i=0; i<12; i++) {
				ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.WES_CheckedValue +"_" + i), 0);
				if(excelFieldMapping.get(Constants.WES_CommentDesc +"_" + i) != null)
					ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.WES_CommentDesc +"_" + i), 0);
			}
		}
	}
}
