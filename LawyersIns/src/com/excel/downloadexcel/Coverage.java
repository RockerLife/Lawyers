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

public class Coverage {
	String IsFirmCarryProInsuranceLW = "" ;
	public void getDataForCoverage(Context ctx) throws Exception {
		
		
		Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey("FirmLW.findByKey", ctx);	
		ctx.put(Constants.FIRM_FREEFORM_01, objFirm);
		
		Object listClaims = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetProfLiabClmAgntList", ctx);	
		ctx.put(Constants.COVERAGE_CLAIMS_LIST, listClaims);
		
		if(ctx.get(Constants.FIRM_FREEFORM_01)!= null){
			Map firmMap = null ;
			
			firmMap =  (Map)ctx.get(Constants.FIRM_FREEFORM_01) ;
			if(firmMap != null){
				
				IsFirmCarryProInsuranceLW = firmMap.get("IsFirmHaveLawyersLiabilityInsurance") != null ? firmMap.get("IsFirmHaveLawyersLiabilityInsurance").toString() : "" ; 
				if(!"Y".equals(IsFirmCarryProInsuranceLW)){
					
					Object objProfLiab = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.coverageviewgetProfessionalLiabilityInsDetailLW", ctx);	
					ctx.put(Constants.PROFESSIONALLIABILITYINS_FREEFORM_01, objProfLiab);
					
					
				}else{
					Object objProfLiab = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetProfessionalLiabilityInsDetailLWFullDetails", ctx);	
					ctx.put(Constants.PROFESSIONALLIABILITYINS_FREEFORM_01, objProfLiab);
				}
				
			}			
		}
		
		
		if(ctx.get("manualrating") == null || (ctx.get("manualrating")!= null && "N".equals(ctx.get("manualrating")))){
			
			
			Object objPolicyCov = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsCoveragePolicyforexcel", ctx);	
			ctx.put(Constants.POLICYCOVERAGE_FREEFORM_01, objPolicyCov);
			
			if(objPolicyCov == null){			
			
				objPolicyCov = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementpdfgetPolicyCoverageFullDetails", ctx);	
				ctx.put(Constants.POLICYCOVERAGE_FREEFORM_01, objPolicyCov);
			}
		
		}else{
			Map mapPolicyCoverage = new HashMap();
			mapPolicyCoverage.put(Constants.IsClaimExpensesType, ctx.get(Constants.IsClaimExpensesType) != null ? ctx.get(Constants.IsClaimExpensesType) : "N");
			mapPolicyCoverage.put(Constants.IsClaimOptionType, ctx.get(Constants.IsClaimOptionType) != null ? ctx.get(Constants.IsClaimOptionType) : "N");
			mapPolicyCoverage.put(Constants.IsDollarDefense, ctx.get(Constants.IsDollarDefense) != null ? ctx.get(Constants.IsDollarDefense) : "N");
			
			Object objLimit =SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtspopulateLimit", ctx);
			Object objDeductible =SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtspopulateDeductible", ctx);
			
			if(objLimit != null && objLimit instanceof Map)
			{
				mapPolicyCoverage.put(Constants.AggregateLimit, ((Map)objLimit).get(Constants.AggregateLimit));
				mapPolicyCoverage.put(Constants.OccuranceLimit, ((Map)objLimit).get(Constants.OccuranceLimit));
				mapPolicyCoverage.put(Constants.LimitAmount, ((Map)objLimit).get(Constants.AggregateLimit) + "/" + ((Map)objLimit).get(Constants.OccuranceLimit));
			}
			if(objDeductible != null && objDeductible instanceof Map)
			{
				mapPolicyCoverage.put(Constants.DeductibleAmount, ((Map)objDeductible).get(Constants.DeductibleAmount));
			}
			
			mapPolicyCoverage.put(Constants.Premium, ctx.get("ManualPremium"));
			ctx.put(Constants.POLICYCOVERAGE_FREEFORM_01, mapPolicyCoverage);
		}
		
		Object listERP = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetProdecessorERPList", ctx);
		ctx.put(Constants.COVERAGE_ERP_LIST, listERP);
		
		Object objPolicy = SqlResources.getSqlMapProcessor(ctx).findByKey("Policy.findByKey", ctx);	
		ctx.put(Constants.POLICY_FREEFORM_01, objPolicy);
	}
	
	public void populateCoverageData(Context ctx, HSSFWorkbook wb) throws Exception{
		Sheet sheet = wb.getSheet("Coverage");
		populateCoverage(ctx, sheet);
		populateERPList(ctx, sheet);
		populateClaimsList(ctx, sheet);
	}
	
	public void populateERPList(Context ctx, Sheet sheet) throws Exception{
		Object erpObj = ctx.get(Constants.COVERAGE_ERP_LIST);
		if(erpObj == null)
			return;
		
		List erpList = null;
		if (erpObj != null)
			erpList = (ArrayList) erpObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.ERPFirmName);
		
		for(int i = rb_federal.getRownum() + erpList.size(); i< rb_federal.getRownum() + 16; i++){
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		int rowtoshiftup_bld = -15 + erpList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		for(int i = 0; i < erpList.size(); i++) {
			Map map = (HashMap) erpList.get(i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.FirmName, false), excelFieldMapping.get(Constants.ERPFirmName), i);
			
			String IsERPPurchased = ExcelUtility.getValueFromMap(map, Constants.IsERPPurchased, false);
			if(IsERPPurchased != null && IsERPPurchased.equals("Y")){
				IsERPPurchased = "Yes";
			}else if(IsERPPurchased != null && IsERPPurchased.equals("N")){
				IsERPPurchased = "No";
			}
			ExcelUtility.setCellValue(sheet, IsERPPurchased, excelFieldMapping.get(Constants.IsERPPurchased), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.ERPExpDate, false), excelFieldMapping.get(Constants.ERPExpDate), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.InsurerAtDissolution, false), excelFieldMapping.get(Constants.InsurerAtDissolution), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.TypeOfEntity, false), excelFieldMapping.get(Constants.TypeOfEntity), i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(map.get(Constants.DateOfAcquisation)), excelFieldMapping.get(Constants.DateOfAcquisation), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.NumberOfAttorneyAtDiss, false), excelFieldMapping.get(Constants.NumberOfAttorneyAtDiss), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.NumberOfAttorneyAtApplFirm, false), excelFieldMapping.get(Constants.NumberOfAttorneyAtApplFirm), i);
		}
	}
	
	public void populateClaimsList(Context ctx, Sheet sheet) throws Exception{
		Object claimsObj = ctx.get(Constants.COVERAGE_CLAIMS_LIST);
		if(claimsObj == null)
			return;
		
		List claimsList = null;
		if (claimsObj != null)
			claimsList = (ArrayList) claimsObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.NameOfClaimant);
		
		for(int i = rb_federal.getRownum() + claimsList.size(); i< rb_federal.getRownum() + 16; i++){
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		int rowtoshiftup_bld = -15 + claimsList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		for(int i = 0; i < claimsList.size(); i++) {
			Map map = (HashMap) claimsList.get(i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.NameOfClaimant, false), excelFieldMapping.get(Constants.NameOfClaimant), i);
			
			String IsClaimReportedToInsComp = ExcelUtility.getValueFromMap(map, Constants.IsClaimReportedToInsComp, false);
			if(IsClaimReportedToInsComp != null && IsClaimReportedToInsComp.equals("Y")){
				IsClaimReportedToInsComp = "Yes";
			}else if(IsClaimReportedToInsComp != null && IsClaimReportedToInsComp.equals("N")){
				IsClaimReportedToInsComp = "No";
			}
			ExcelUtility.setCellValue(sheet, IsClaimReportedToInsComp, excelFieldMapping.get(Constants.IsClaimReportedToInsComp), i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.NameOfInsComp, false), excelFieldMapping.get(Constants.NameOfInsComp), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.DateOfAllegedError, false), excelFieldMapping.get(Constants.DateOfAllegedError), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.DateReportedToInsComp, false), excelFieldMapping.get(Constants.DateReportedToInsComp), i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(map.get(Constants.ClaimNotifiedDate)), excelFieldMapping.get(Constants.ClaimNotifiedDate), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.NameOfPersonnelINClaim, false), excelFieldMapping.get(Constants.NameOfPersonnelINClaim), i);

			String IsClient = ExcelUtility.getValueFromMap(map, Constants.IsClient, false);
			if(IsClient != null && IsClient.equals("Y")){
				IsClient = "Yes";
			}else if(IsClient != null && IsClient.equals("N")){
				IsClient = "No";
			}
			ExcelUtility.setCellValue(sheet, IsClient, excelFieldMapping.get(Constants.IsClient), i);
			
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.ClaimantLastDemand, false), excelFieldMapping.get(Constants.ClaimantLastDemand), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.ClaimClosingDate, false), excelFieldMapping.get(Constants.ClaimClosingDate), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.TotalAmountPaid, false), excelFieldMapping.get(Constants.TotalAmountPaid), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.DescOfClaim, false), excelFieldMapping.get(Constants.DescOfClaim), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.StepsTakenToPreventClaims, false), excelFieldMapping.get(Constants.StepsTakenToPreventClaims), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.CurrentStatus, false), excelFieldMapping.get(Constants.CurrentStatus), i);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getValueFromMap(map, Constants.InsurerLoss, false), excelFieldMapping.get(Constants.InsurerLoss), i);
		}
	}
	
	public void populateCoverage(Context ctx, Sheet sheet) throws Exception{
	    Object objFirm = ctx.get(Constants.FIRM_FREEFORM_01);
		Map firmMap = objFirm instanceof Map ? (Map) objFirm : new HashMap();

		Object objProfLiab = ctx.get(Constants.PROFESSIONALLIABILITYINS_FREEFORM_01);
		Map profLiabMap = objProfLiab instanceof Map ? (Map) objProfLiab : new HashMap();
		
		Object objPolicyCov = ctx.get(Constants.POLICYCOVERAGE_FREEFORM_01);
		Map policyCovMap = objPolicyCov instanceof Map ? (Map) objPolicyCov : new HashMap();
		
		Object objPolicy = ctx.get(Constants.POLICY_FREEFORM_01);
		Map policyMap = objPolicy instanceof Map ? (Map) objPolicy : new HashMap();
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		
		if(policyMap.get(Constants.PolicyEffectiveDate) != null && 
				!policyMap.get(Constants.PolicyEffectiveDate).toString().equals("")){
			ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(policyMap.get(Constants.PolicyEffectiveDate)), excelFieldMapping.get(Constants.PolicyEffectiveDate), 0);
		}else{
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.PolicyEffectiveDate), 0);
		}
		
		String IsPersonnelBeSubOfAnyInvest = ExcelUtility.getValueFromMap(firmMap, Constants.IsPersonnelBeSubOfAnyInvest, false);
		if(IsPersonnelBeSubOfAnyInvest != null && IsPersonnelBeSubOfAnyInvest.equals("Y")){
			IsPersonnelBeSubOfAnyInvest = "Yes";
		}else if(IsPersonnelBeSubOfAnyInvest != null && IsPersonnelBeSubOfAnyInvest.equals("N")){
			IsPersonnelBeSubOfAnyInvest = "No";
		}
		ExcelUtility.setCellValue(sheet, IsPersonnelBeSubOfAnyInvest, excelFieldMapping.get(Constants.IsPersonnelBeSubOfAnyInvest), 0);
		
		//String PersonnelBeSubOfAnyInvestDate = ExcelUtility.getValueFromMap(firmMap, Constants.PersonnelBeSubOfAnyInvestDate, false);
		//ExcelUtility.setCellValue(sheet, PersonnelBeSubOfAnyInvestDate, excelFieldMapping.get(Constants.PersonnelBeSubOfAnyInvestDate), 0);
		ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(firmMap.get(Constants.PersonnelBeSubOfAnyInvestDate)), excelFieldMapping.get(Constants.PersonnelBeSubOfAnyInvestDate), 0);
		
		String PersonnelBeSubOfAnyInvestDetails = ExcelUtility.getValueFromMap(firmMap, Constants.PersonnelBeSubOfAnyInvestDetails, false);
		ExcelUtility.setCellValue(sheet, PersonnelBeSubOfAnyInvestDetails, excelFieldMapping.get(Constants.PersonnelBeSubOfAnyInvestDetails), 0);

		String IsLawyerProfLiabClaimAgntAppl = ExcelUtility.getValueFromMap(firmMap, Constants.IsLawyerProfLiabClaimAgntAppl, false);
		if(IsLawyerProfLiabClaimAgntAppl != null && IsLawyerProfLiabClaimAgntAppl.equals("Y")){
			IsLawyerProfLiabClaimAgntAppl = "Yes";
		}else if(IsLawyerProfLiabClaimAgntAppl != null && IsLawyerProfLiabClaimAgntAppl.equals("N")){
			IsLawyerProfLiabClaimAgntAppl = "No";
		}
		ExcelUtility.setCellValue(sheet, IsLawyerProfLiabClaimAgntAppl, excelFieldMapping.get(Constants.IsLawyerProfLiabClaimAgntAppl), 0);
		
		String IsAnyActOmmBecomeClaimAgntFirm = ExcelUtility.getValueFromMap(firmMap, Constants.IsAnyActOmmBecomeClaimAgntFirm, false);
		if(IsAnyActOmmBecomeClaimAgntFirm != null && IsAnyActOmmBecomeClaimAgntFirm.equals("Y")){
			IsAnyActOmmBecomeClaimAgntFirm = "Yes";
		}else if(IsAnyActOmmBecomeClaimAgntFirm != null && IsAnyActOmmBecomeClaimAgntFirm.equals("N")){
			IsAnyActOmmBecomeClaimAgntFirm = "No";
		}
		ExcelUtility.setCellValue(sheet, IsAnyActOmmBecomeClaimAgntFirm, excelFieldMapping.get(Constants.IsAnyActOmmBecomeClaimAgntFirm), 0);
		
		/*String NameOfClaimant = ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.NameOfClaimant, false);
		ExcelUtility.setCellValue(sheet, NameOfClaimant, excelFieldMapping.get(Constants.NameOfClaimant), 0);
		
		String IsClient = ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.IsClient, false);
		if(IsClient != null && IsClient.equals("Y")){
			IsClient = "Yes";
		}else if(IsClient != null && IsClient.equals("N")){
			IsClient = "No";
		}
		ExcelUtility.setCellValue(sheet, IsClient, excelFieldMapping.get(Constants.IsClient), 0);
		
		if(ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.ClaimNotifiedDate, false) != null){
			//ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.ClaimNotifiedDate, false)), excelFieldMapping.get(Constants.ClaimNotifiedDate), 0);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(profLCAFirmLWMap.get(Constants.ClaimNotifiedDate)), excelFieldMapping.get(Constants.ClaimNotifiedDate), 0);
		}
		
		if(ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.DateOfAllegedError, false) != null){
//			ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.DateOfAllegedError, false)), excelFieldMapping.get(Constants.DateOfAllegedError), 0);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(profLCAFirmLWMap.get(Constants.DateOfAllegedError)), excelFieldMapping.get(Constants.DateOfAllegedError), 0);
		}
		
		String NameOfPersonnelINClaim = ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.NameOfPersonnelINClaim, false);
		ExcelUtility.setCellValue(sheet, NameOfPersonnelINClaim, excelFieldMapping.get(Constants.NameOfPersonnelINClaim), 0);
		
		String DescOfClaim = ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.DescOfClaim, false);
		ExcelUtility.setCellValue(sheet, DescOfClaim, excelFieldMapping.get(Constants.DescOfClaim), 0);
		
		String IsClaimReportedToInsComp = ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.IsClaimReportedToInsComp, false);
		if(IsClaimReportedToInsComp != null && IsClaimReportedToInsComp.equals("Y")){
			IsClaimReportedToInsComp = "Yes";
		}else if(IsClaimReportedToInsComp != null && IsClaimReportedToInsComp.equals("N")){
			IsClaimReportedToInsComp = "No";
		}
		ExcelUtility.setCellValue(sheet, IsClaimReportedToInsComp, excelFieldMapping.get(Constants.IsClaimReportedToInsComp), 0);
	
		String NameOfInsComp = ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.NameOfInsComp, false);
		ExcelUtility.setCellValue(sheet, NameOfInsComp, excelFieldMapping.get(Constants.NameOfInsComp), 0);
		
		if(ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.DateReportedToInsComp, false) != null){
			ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(profLCAFirmLWMap.get(Constants.DateReportedToInsComp)), excelFieldMapping.get(Constants.DateReportedToInsComp), 0);
		}
		
		String InsurerLoss = ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.InsurerLoss, false);
		ExcelUtility.setCellValue(sheet, InsurerLoss, excelFieldMapping.get(Constants.InsurerLoss), 0);
		
		String ClaimantLastDemand = ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.ClaimantLastDemand, false);
		ExcelUtility.setCellValue(sheet, ClaimantLastDemand, excelFieldMapping.get(Constants.ClaimantLastDemand), 0);
		
		String CurrentStatus = ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.CurrentStatus, false);
		ExcelUtility.setCellValue(sheet, CurrentStatus, excelFieldMapping.get(Constants.CurrentStatus), 0);
		
		if(ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.ClaimClosingDate, false) != null){
			ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(profLCAFirmLWMap.get(Constants.ClaimClosingDate)), excelFieldMapping.get(Constants.ClaimClosingDate), 0);
		}
		
		String TotalAmountPaid = ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.TotalAmountPaid, false);
		ExcelUtility.setCellValue(sheet, TotalAmountPaid, excelFieldMapping.get(Constants.TotalAmountPaid), 0);
		
		String StepsTakenToPreventClaims = ExcelUtility.getValueFromMap(profLCAFirmLWMap, Constants.StepsTakenToPreventClaims, false);
		ExcelUtility.setCellValue(sheet, StepsTakenToPreventClaims, excelFieldMapping.get(Constants.StepsTakenToPreventClaims), 0);
		*/
		String IsAttorneyDeclineForProfLiability = ExcelUtility.getValueFromMap(firmMap, Constants.IsAttorneyDeclineForProfLiability, false);
		if(IsAttorneyDeclineForProfLiability != null && IsAttorneyDeclineForProfLiability.equals("Y")){
			IsAttorneyDeclineForProfLiability = "Yes";
		}else if(IsAttorneyDeclineForProfLiability != null && IsAttorneyDeclineForProfLiability.equals("N")){
			IsAttorneyDeclineForProfLiability = "No";
		}
		ExcelUtility.setCellValue(sheet, IsAttorneyDeclineForProfLiability, excelFieldMapping.get(Constants.IsAttorneyDeclineForProfLiability), 0);
	
		if(ExcelUtility.getValueFromMap(firmMap, Constants.AttorneyDeclineForProfLiabilityDates, false) != null){
			ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(firmMap.get(Constants.AttorneyDeclineForProfLiabilityDates)), excelFieldMapping.get(Constants.AttorneyDeclineForProfLiabilityDates), 0);
		}
		
		String AttorneyDeclineForProfLiabilityReasons = ExcelUtility.getValueFromMap(firmMap, Constants.AttorneyDeclineForProfLiabilityReasons, false);
		ExcelUtility.setCellValue(sheet, AttorneyDeclineForProfLiabilityReasons, excelFieldMapping.get(Constants.AttorneyDeclineForProfLiabilityReasons), 0);
		
		String IsFirmHaveLawyersLiabilityInsurance = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmHaveLawyersLiabilityInsurance, false);
		if(IsFirmHaveLawyersLiabilityInsurance != null && IsFirmHaveLawyersLiabilityInsurance.equals("Y")){
			IsFirmHaveLawyersLiabilityInsurance = "Yes";
		}else if(IsFirmHaveLawyersLiabilityInsurance != null && IsFirmHaveLawyersLiabilityInsurance.equals("N")){
			IsFirmHaveLawyersLiabilityInsurance = "No";
		}
		ExcelUtility.setCellValue(sheet, IsFirmHaveLawyersLiabilityInsurance, excelFieldMapping.get(Constants.IsFirmHaveLawyersLiabilityInsurance), 0);
	
		if(profLiabMap != null && "Yes".equals(IsFirmHaveLawyersLiabilityInsurance)){
			String InsuranceCompanyNamePross = ExcelUtility.getValueFromMap(profLiabMap, Constants.InsuranceCompanyNamePross, false);
			ExcelUtility.setCellValue(sheet, InsuranceCompanyNamePross, excelFieldMapping.get(Constants.InsuranceCompanyNamePross), 0);
			
			if(ExcelUtility.getValueFromMap(profLiabMap, Constants.PolicyExpirationDatePross, false) != null){
				ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(profLiabMap.get(Constants.PolicyExpirationDatePross)), excelFieldMapping.get(Constants.PolicyExpirationDatePross), 0);
			}		
			
			String LimitSequencePross = ExcelUtility.getValueFromMap(profLiabMap, Constants.LimitAmount, false);
			ExcelUtility.setCellValue(sheet, LimitSequencePross, excelFieldMapping.get(Constants.LimitSequencePross), 0);
			
			String IsClaimExpLiability = ExcelUtility.getValueFromMap(profLiabMap, Constants.IsClaimExpLiability, false);
			String IsClaimExpLiabilityDR = "";
			String IsClaimExpLiabilityDA = "";
			if(IsClaimExpLiability != null && IsClaimExpLiability.equals("DR")){
				IsClaimExpLiabilityDR = "Yes";
				IsClaimExpLiabilityDA = "No";
			}else if(IsClaimExpLiability != null && IsClaimExpLiability.equals("DA")){
				IsClaimExpLiabilityDA = "Yes";
				IsClaimExpLiabilityDR = "No";
			}
			ExcelUtility.setCellValue(sheet, IsClaimExpLiabilityDR, excelFieldMapping.get(Constants.IsClaimExpLiabilityDR), 0);
			ExcelUtility.setCellValue(sheet, IsClaimExpLiabilityDA, excelFieldMapping.get(Constants.IsClaimExpLiabilityDA), 0);
			
			String DeductibleSequencePross = ExcelUtility.getValueFromMap(profLiabMap, Constants.DeductibleAmount, false);
			ExcelUtility.setCellValue(sheet, DeductibleSequencePross, excelFieldMapping.get(Constants.DeductibleSequencePross), 0);
			
			String IsPerClaim = ExcelUtility.getValueFromMap(profLiabMap, Constants.IsPerClaim, false);
			String IsPerClaimPC = "";
			String IsPerClaimAA = "";
			if(IsPerClaim != null && IsPerClaim.equals("PC")){
				IsPerClaimPC = "Yes";
				IsPerClaimAA = "No";
			}else if(IsPerClaim != null && IsPerClaim.equals("AA")){
				IsPerClaimAA = "Yes";
				IsPerClaimPC = "No";
			}
			ExcelUtility.setCellValue(sheet, IsPerClaimPC, excelFieldMapping.get(Constants.IsPerClaimPC), 0);
			ExcelUtility.setCellValue(sheet, IsPerClaimAA, excelFieldMapping.get(Constants.IsPerClaimAA), 0);
			
			String IsProfDefenceExpense = ExcelUtility.getValueFromMap(profLiabMap, Constants.IsProfDefenceExpense, false);
			if(IsProfDefenceExpense != null && IsProfDefenceExpense.equals("Y")){
				IsProfDefenceExpense = "Yes";
			}else if(IsProfDefenceExpense != null && IsProfDefenceExpense.equals("N")){
				IsProfDefenceExpense = "No";
			}
			ExcelUtility.setCellValue(sheet, IsProfDefenceExpense, excelFieldMapping.get(Constants.IsProfDefenceExpense), 0);
		
			String ProInsPremium = ExcelUtility.getValueFromMap(profLiabMap, Constants.ProInsPremium, false);
			ExcelUtility.setCellValue(sheet, ProInsPremium, excelFieldMapping.get(Constants.ProInsPremium), 0);
			
			String IsPriorActDateFullYes = "";
			String IsPriorActDateFullNo = "";
			if(ExcelUtility.getValueFromMap(profLiabMap, Constants.IsPriorActDateFull, false) != null){
				
				String IsPriorActDateFull = ExcelUtility.getValueFromMap(profLiabMap, Constants.IsPriorActDateFull, false);
				if(IsPriorActDateFull != null && !"Y".equals(IsPriorActDateFull)){
					IsPriorActDateFullNo = "Yes";
					IsPriorActDateFullYes = "No";
				}else{
					IsPriorActDateFullYes = "Yes";
					IsPriorActDateFullNo = "No";
				}				
				ExcelUtility.setCellValue(sheet, IsPriorActDateFullYes ,excelFieldMapping.get(Constants.IsPriorActDateFullYes), 0);
				ExcelUtility.setCellValue(sheet, IsPriorActDateFullNo ,excelFieldMapping.get(Constants.IsPriorActDateFullNo), 0);
			}
			
			if(ExcelUtility.getValueFromMap(profLiabMap, Constants.FirmYear, false) != null){
				String FirmYear =  ExcelUtility.getValueFromMap(profLiabMap, Constants.FirmYear, false);
				ExcelUtility.setCellValue(sheet, FirmYear ,excelFieldMapping.get(Constants.FirmYear), 0);
			}
			
			if("No".equals(IsPriorActDateFullYes)){
			
				if(ExcelUtility.getValueFromMap(profLiabMap, Constants.PriorActDatePross, false) != null){
					ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(profLiabMap.get(Constants.PriorActDatePross)), excelFieldMapping.get(Constants.PriorActDatePross), 0);
				}
			}else{
				ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.PriorActDatePross), 0);
			}
		}else{
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.InsuranceCompanyNamePross), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.PolicyExpirationDatePross), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.LimitSequencePross), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsClaimExpLiabilityDR), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsClaimExpLiabilityDA), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.DeductibleSequencePross), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsPerClaimPC), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsPerClaimAA), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsProfDefenceExpense), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.ProInsPremium), 0);
			ExcelUtility.setCellValue(sheet, ExcelUtility.getFormattedDateFromObject(profLiabMap != null ?  profLiabMap.get(Constants.PriorActDatePross) : ""), excelFieldMapping.get(Constants.PriorActDatePross), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsPriorActDateFullYes), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsPriorActDateFullNo), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.FirmYear), 0);
		}
		
		String IsPolicyIncludeLateralHireCov = ExcelUtility.getValueFromMap(firmMap, Constants.IsPolicyIncludeLateralHireCov, false);
		if(IsPolicyIncludeLateralHireCov != null && IsPolicyIncludeLateralHireCov.equals("Y")){
			IsPolicyIncludeLateralHireCov = "Yes";
		}else if(IsPolicyIncludeLateralHireCov != null && IsPolicyIncludeLateralHireCov.equals("N")){
			IsPolicyIncludeLateralHireCov = "No";
		}
		ExcelUtility.setCellValue(sheet, IsPolicyIncludeLateralHireCov, excelFieldMapping.get(Constants.IsPolicyIncludeLateralHireCov), 0);
	
		String IsPolicyExcludesCoverage = ExcelUtility.getValueFromMap(firmMap, Constants.IsPolicyExcludesCoverage, false);
		if(IsPolicyExcludesCoverage != null && IsPolicyExcludesCoverage.equals("Y")){
			IsPolicyExcludesCoverage = "Yes";
		}else if(IsPolicyExcludesCoverage != null && IsPolicyExcludesCoverage.equals("N")){
			IsPolicyExcludesCoverage = "No";
		}
		ExcelUtility.setCellValue(sheet, IsPolicyExcludesCoverage, excelFieldMapping.get(Constants.IsPolicyExcludesCoverage), 0);
	
		String PolicyExcludeCoverageForAffiliatesDesc = ExcelUtility.getValueFromMap(firmMap, Constants.PolicyExcludeCoverageForAffiliatesDesc, false);
		ExcelUtility.setCellValue(sheet, PolicyExcludeCoverageForAffiliatesDesc, excelFieldMapping.get(Constants.PolicyExcludeCoverageForAffiliatesDesc), 0);
		
		if(policyCovMap != null){
			String LimitSequence = ExcelUtility.getValueFromMap(policyCovMap, Constants.LimitAmount, false);
			ExcelUtility.setCellValue(sheet, LimitSequence, excelFieldMapping.get(Constants.LimitSequence), 0);
			
			String IsClaimExpensesType = ExcelUtility.getValueFromMap(policyCovMap, Constants.IsClaimExpensesType, false);
			String IsClaimExpensesTypeDR = "";
			String IsClaimExpensesTypeDA = "";
			if(IsClaimExpensesType != null && IsClaimExpensesType.equals("N")){
				IsClaimExpensesTypeDR = "Yes";
				IsClaimExpensesTypeDA = "No";
			}else if(IsClaimExpensesType != null && IsClaimExpensesType.equals("Y")){
				IsClaimExpensesTypeDA = "Yes";
				IsClaimExpensesTypeDR = "No";
			}
			ExcelUtility.setCellValue(sheet, IsClaimExpensesTypeDR, excelFieldMapping.get(Constants.IsClaimExpensesTypeDR), 0);
			ExcelUtility.setCellValue(sheet, IsClaimExpensesTypeDA, excelFieldMapping.get(Constants.IsClaimExpensesTypeDA), 0);
			
			String DeductibleSequence = ExcelUtility.getValueFromMap(policyCovMap, Constants.DeductibleAmount, false);
			if(DeductibleSequence != null){
				DeductibleSequence = removeFormat(DeductibleSequence);
			}
			ExcelUtility.setCellValue(sheet, DeductibleSequence, excelFieldMapping.get(Constants.DeductibleSequence), 0);
			
			String IsClaimOptionType = ExcelUtility.getValueFromMap(policyCovMap, Constants.IsClaimOptionType, false);
			String IsClaimOptionTypePC = "";
			String IsClaimOptionTypeAA = "";
			if(IsClaimOptionType != null && IsClaimOptionType.equals("N")){
				IsClaimOptionTypePC = "Yes";
				IsClaimOptionTypeAA = "No";
			}else if(IsClaimOptionType != null && IsClaimOptionType.equals("Y")){
				IsClaimOptionTypeAA = "Yes";
				IsClaimOptionTypePC = "No";
			}
			ExcelUtility.setCellValue(sheet, IsClaimOptionTypePC, excelFieldMapping.get(Constants.IsClaimOptionTypePC), 0);
			ExcelUtility.setCellValue(sheet, IsClaimOptionTypeAA, excelFieldMapping.get(Constants.IsClaimOptionTypeAA), 0);
			
			String IsDollarDefense = ExcelUtility.getValueFromMap(policyCovMap, Constants.IsDollarDefense, false);
			if(IsDollarDefense != null && IsDollarDefense.equals("Y")){
				IsDollarDefense = "Yes";
			}else if(IsDollarDefense != null && IsDollarDefense.equals("N")){
				IsDollarDefense = "No";
			}
			ExcelUtility.setCellValue(sheet, IsDollarDefense, excelFieldMapping.get(Constants.IsDollarDefense), 0);
		}else{
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.LimitSequence), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsClaimExpensesTypeDR), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsClaimExpensesTypeDA), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.DeductibleSequence), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsClaimOptionTypePC), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsClaimOptionTypeAA), 0);
			ExcelUtility.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsDollarDefense), 0);
		}
		
		String IsFirmMergedWithOtherFirm = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmMergedWithOtherFirm, false);
		if(IsFirmMergedWithOtherFirm != null && IsFirmMergedWithOtherFirm.equals("Y")){
			IsFirmMergedWithOtherFirm = "Yes";
		}else if(IsFirmMergedWithOtherFirm != null && IsFirmMergedWithOtherFirm.equals("N")){
			IsFirmMergedWithOtherFirm = "No";
		}
		ExcelUtility.setCellValue(sheet, IsFirmMergedWithOtherFirm, excelFieldMapping.get(Constants.IsFirmMergedWithOtherFirm), 0);
	
		String IsApplIntToFinanAssests = ExcelUtility.getValueFromMap(firmMap, Constants.IsApplIntToFinanAssests, false);
		if(IsApplIntToFinanAssests != null && IsApplIntToFinanAssests.equals("Y")){
			IsApplIntToFinanAssests = "Yes";
		}else if(IsApplIntToFinanAssests != null && IsApplIntToFinanAssests.equals("N")){
			IsApplIntToFinanAssests = "No";
		}
		ExcelUtility.setCellValue(sheet, IsApplIntToFinanAssests, excelFieldMapping.get(Constants.IsApplIntToFinanAssests), 0);
	
		String IsFirmCoverageForPreceedorFirms = ExcelUtility.getValueFromMap(firmMap, Constants.IsFirmCoverageForPreceedorFirms, false);
		if(IsFirmCoverageForPreceedorFirms != null && IsFirmCoverageForPreceedorFirms.equals("Y")){
			IsFirmCoverageForPreceedorFirms = "Yes";
		}else if(IsFirmCoverageForPreceedorFirms != null && IsFirmCoverageForPreceedorFirms.equals("N")){
			IsFirmCoverageForPreceedorFirms = "No";
		}
		ExcelUtility.setCellValue(sheet, IsFirmCoverageForPreceedorFirms, excelFieldMapping.get(Constants.IsFirmCoverageForPreceedorFirms), 0);
	
		//Set Ajax List Count
		String CoverageClaimCount = "0";
		String CoverageERPCount = "0";
		Object claimObj = ctx.get(Constants.COVERAGE_CLAIMS_LIST);
		List claimList = null;
		if(claimObj != null && claimObj instanceof List){
			claimList = (ArrayList) claimObj;
			CoverageClaimCount = String.valueOf(claimList.size());
		}
		Object erpObj = ctx.get(Constants.COVERAGE_ERP_LIST);
		List erpList = null;
		if(erpObj != null && erpObj instanceof List){
			erpList = (ArrayList) erpObj;
			CoverageERPCount = String.valueOf(erpList.size());
		}
		ExcelUtility.setCellValue(sheet, CoverageClaimCount, excelFieldMapping.get(Constants.CoverageClaimCount), 0);
		ExcelUtility.setCellValue(sheet, CoverageERPCount, excelFieldMapping.get(Constants.CoverageERPCount), 0);
		
	}
	
	private String removeFormat(String amt){
		amt = amt.replaceAll(",", "");
		amt = amt.replaceAll("$", "");
		return amt;
	}
}
