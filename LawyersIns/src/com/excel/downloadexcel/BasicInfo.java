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


public class BasicInfo {
	
	public void getDataForBasicInfo(Context ctx) throws Exception
	{
		Object objAccount =SqlResources.getSqlMapProcessor(ctx).findByKey("Account.findByKey", ctx);	
		ctx.put(Constants.ACCOUNT_FREEFORM_01, objAccount);
		
		Object objAddress =SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.BasicInfoviewgetAddressdetails", ctx);	
		ctx.put(Constants.ADDRESS_FREEFORM_01, objAddress);
		
		Object objPolicy =SqlResources.getSqlMapProcessor(ctx).findByKey("Policy.findByKey", ctx);	
		ctx.put(Constants.POLICY_FREEFORM_01, objPolicy);
		
		Object objFirm =SqlResources.getSqlMapProcessor(ctx).findByKey("Firm.findByKey", ctx);	
		ctx.put(Constants.FIRM_FREEFORM_01, objFirm);
		
		Object objPolicyCoverage =SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.BasicInfoviewCoveragePolicy", ctx);
		ctx.put(Constants.POLICYCOVERAGE_FREEFORM_01, objPolicyCoverage);
		
		Object objProfessionalLiabilityInsDetail =SqlResources.getSqlMapProcessor(ctx).findByKey("ProfessionalLiabilityInsDetail.findByKey", ctx);
		ctx.put(Constants.PROFESSIONALLIABILITYINS_FREEFORM_01, objProfessionalLiabilityInsDetail);		
		
		Object objLimitDeductibleProfessionalLiabilityInsDetail = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsgeLimitDeductibleProfessionalLiabilityInsDetail", ctx);
		ctx.put(Constants.LIMITDEDUCTIBLEPROFESSIONALLIABILITYINS_FREEFORM_01, objLimitDeductibleProfessionalLiabilityInsDetail);
		
		Object listClaimIncidentSupp =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.BasicInfoviewgetClaimIncidentSupp", ctx);
		ctx.put(Constants.BASICINFO_LIST_04, listClaimIncidentSupp);
		
		Object listPersonnelAffiliation =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.BasicInfoviewmomfirmaffiliates", ctx);
		ctx.put(Constants.BASICINFO_LIST_01, listPersonnelAffiliation);
		
		Object listGrossRevenueForFirm =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.BasicInfoviewmomgrossrevenue", ctx);
		ctx.put(Constants.BASICINFO_LIST_03, listGrossRevenueForFirm);
		
		Object listPublicPrivateOfferingSupp =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.BasicInfoviewListPublicPrivateOfferingSupp", ctx);
		ctx.put(Constants.BASICINFO_LIST_06, listPublicPrivateOfferingSupp);
		
		Object listAOP =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields", ctx);
		ctx.put(Constants.BASICINFO_LIST_AOP, listAOP);
		
		//new General().getDataForGeneral(ctx);
			
	}	
	
	public void populateBasicInfoData(Context ctx, HSSFWorkbook wb) throws Exception
	{
		Sheet sheet = wb.getSheet("Basic Info");
        CellStyle string= wb.createCellStyle();
        string.setDataFormat((short) 38);
        CellStyle numeric= wb.createCellStyle();
        numeric.setDataFormat((short) 38);
        
        populateFirmInfo(ctx, sheet);
        populatePeronnalAffiliation(ctx, sheet);
        populateGrossRevenue(ctx, sheet);
        populateAOPList(ctx, sheet);
        populateClaimList(ctx, sheet);
        populatePublicPrivateOfferingList(ctx, sheet);
	}
	
	public void populateFirmInfo(Context ctx, Sheet sheet) throws Exception
	{
		Object objFirm = ctx.get(Constants.FIRM_FREEFORM_01);
		Map firmMap = null;
		if(objFirm != null)
			firmMap = (Map)objFirm;
		
		Object objAccount = ctx.get(Constants.ACCOUNT_FREEFORM_01);
		Map accountMap = null;
		if(objAccount != null)
			accountMap = (Map)objAccount;
		
		Object objAddress = ctx.get(Constants.ADDRESS_FREEFORM_01);
		Map addressMap = null;
		if(objAddress != null)
			addressMap = (Map)objAddress;
		
		Object objPolicy = ctx.get(Constants.POLICY_FREEFORM_01);
		Map policyMap = null;
		if(objPolicy != null)
			policyMap = (Map)objPolicy;		

		Object objPolicyCoverage = ctx.get(Constants.POLICYCOVERAGE_FREEFORM_01);
		Map policyCoverageMap = null;
		if(objPolicyCoverage != null)
			policyCoverageMap = (Map)objPolicyCoverage;
		
//		if(firmMap == null && accountMap == null && addressMap == null && policyMap == null && policyCoverageMap ==null)
//			return;

		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		
		/*ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(policyMap, Constants.QuoteNumber, false), excelFieldMapping.get(Constants.QuoteNumber), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(policyMap, Constants.PolicyNumber, false), excelFieldMapping.get(Constants.PolicyNumber), 0);
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getFormattedDateFromObject(policyMap.get(Constants.PolicyEffectiveDate)), excelFieldMapping.get(Constants.PolicyEffectiveDate), 0);
		
		String str = ExcelUtil.getValueFromMap(policyMap, Constants.IsFullQuote, true);
		if("Yes".equals(str))
			str = "Full Quote";
		else if("No".equals(str))
			str = "Quick Quote";		
		ExcelUtil.setCellValue(sheet, str, excelFieldMapping.get(Constants.IsFullQuote), 0);
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(accountMap, Constants.AccountName, false), excelFieldMapping.get(Constants.AccountName), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(accountMap, Constants.AccountEmail, false), excelFieldMapping.get(Constants.AccountEmail), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(accountMap, Constants.Website, false), excelFieldMapping.get(Constants.Website), 0);
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.ContactPerson, false), excelFieldMapping.get(Constants.ContactPerson), 0);
		
		String ContactPhone = ExcelUtil.getValueFromMap(firmMap, Constants.ContactPhone, false);
		String ContactPhoneExt = ExcelUtil.getValueFromMap(firmMap, Constants.ContactPhoneExt, false);
		if(!"".equals(ContactPhoneExt))
			ContactPhone = ContactPhone + " Ext " + ContactPhoneExt;
		ExcelUtil.setCellValue(sheet, ContactPhone, excelFieldMapping.get(Constants.ContactPhone), 0);
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(addressMap, Constants.Address1, false) + "  " + ExcelUtil.getValueFromMap(addressMap, Constants.Address2, false), excelFieldMapping.get(Constants.Address), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(addressMap, Constants.City, false), excelFieldMapping.get(Constants.City), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(addressMap, Constants.StateDesc, false), excelFieldMapping.get(Constants.StateDesc), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(addressMap, Constants.StateDesc, false), excelFieldMapping.get(Constants.State), 0);
		
		String Zip = ExcelUtil.getValueFromMap(addressMap, Constants.Zip, false);
		String Zip4 = ExcelUtil.getValueFromMap(addressMap, Constants.Zip4, false);
		if(!"".equals(Zip4))
			Zip = Zip +"-" + Zip4;
		
		String WorkPhone = ExcelUtil.getValueFromMap(addressMap, Constants.WorkPhone, false);
		String WorkExt = ExcelUtil.getValueFromMap(addressMap, Constants.WorkExt, false);
		if(!"".equals(WorkExt))
			WorkPhone = WorkPhone + " Ext " + WorkExt;
		
		ExcelUtil.setCellValue(sheet,  Zip, excelFieldMapping.get(Constants.Zip), 0);
		ExcelUtil.setCellValue(sheet,  WorkPhone, excelFieldMapping.get(Constants.WorkPhone), 0);
				
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsFirmsRevenueMorethan25Percent, true), excelFieldMapping.get(Constants.IsFirmsRevenueMorethan25Percent), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.PercentOfRevenueFromLargestClient, false), excelFieldMapping.get(Constants.PercentOfRevenueFromLargestClient), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.PercentOfRevenueFromSecondLargestClient, false), excelFieldMapping.get(Constants.PercentOfRevenueFromSecondLargestClient), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.ServiceIndustryofLargestClient, false), excelFieldMapping.get(Constants.ServiceIndustryofLargestClient), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.ServiceIndustryofSecondLargestClient, false), excelFieldMapping.get(Constants.ServiceIndustryofSecondLargestClient), 0);
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsFirmCarryingProfLiabilityIns, true), excelFieldMapping.get(Constants.IsFirmCarryingProfLiabilityIns), 0);
		populateProLiabilityIns(ctx, sheet);		
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsSignedEngagement, true), excelFieldMapping.get(Constants.IsSignedEngagement), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsFirmAttendedRiskMagtSeminarIn3Years, true), excelFieldMapping.get(Constants.IsFirmAttendedRiskMagtSeminarIn3Years), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsPublicTradedAudited, true), excelFieldMapping.get(Constants.IsPublicTradedAudited), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsCommissionRecieved, true), excelFieldMapping.get(Constants.IsCommissionRecieved), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsPublicPrivateOfferingsDone, true), excelFieldMapping.get(Constants.IsPublicPrivateOfferingsDone), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsAnyRegulatoryInquiry, true), excelFieldMapping.get(Constants.IsAnyRegulatoryInquiry), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.RegulatoryInquiryExaplain, false), excelFieldMapping.get(Constants.RegulatoryInquiryExaplain), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.RegulatoryInquiryComment, false), excelFieldMapping.get(Constants.RegulatoryInquiryComment), 0);
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsAwarenessOfAnyProfLiability, true), excelFieldMapping.get(Constants.IsAwarenessOfAnyProfLiability), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsAwarenessOfAnyProfLiabilitySuitAgainst, true), excelFieldMapping.get(Constants.IsAwarenessOfAnyProfLiabilitySuitAgainst), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.IsFirmDeclinedOtherthanNonPayment, true), excelFieldMapping.get(Constants.IsFirmDeclinedOtherthanNonPayment), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.ReasonForDecline, false), excelFieldMapping.get(Constants.ReasonForDecline), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(firmMap, Constants.ReasonForDeclineComment, false), excelFieldMapping.get(Constants.ReasonForDeclineComment), 0);
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(policyCoverageMap, Constants.Premium, false), excelFieldMapping.get(Constants.Premium), 0);
		
		String strAggregateLimit = ExcelUtil.getValueFromMap(policyCoverageMap, Constants.AggregateLimit, false);
		String strOccuranceLimit = ExcelUtil.getValueFromMap(policyCoverageMap, Constants.OccuranceLimit, false);		
		String strLimitAmount = "$" + strAggregateLimit + "/$" + strOccuranceLimit;		
		ExcelUtil.setCellValue(sheet, strLimitAmount, excelFieldMapping.get(Constants.LimitAmount), 0);
		
//		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(policyCoverageMap, Constants.LimitAmount, false), excelFieldMapping.get(Constants.LimitAmount), 0);
		ExcelUtil.setCellValue(sheet, Math.removeAmountFormat(policyCoverageMap.get(Constants.DeductibleAmount)), excelFieldMapping.get(Constants.DeductibleAmount), 0);
		
		String IsClaimExpensesType = ExcelUtil.getValueFromMap(policyCoverageMap, Constants.IsClaimExpensesType, false);
		if("Claimpaid".equals(IsClaimExpensesType))
			IsClaimExpensesType = "Yes";
		else
			IsClaimExpensesType = "No";
		ExcelUtil.setCellValue(sheet, IsClaimExpensesType, excelFieldMapping.get(Constants.IsClaimExpensesType), 0);
		
		String IsClaimOptionType = ExcelUtil.getValueFromMap(policyCoverageMap, Constants.IsClaimOptionType, false);
		if("AnnualAg".equals(IsClaimOptionType))
			IsClaimOptionType = "Yes";
		else
			IsClaimOptionType = "No";
		ExcelUtil.setCellValue(sheet, IsClaimOptionType, excelFieldMapping.get(Constants.IsClaimOptionType), 0);
		*/
	}
	
	public void populateProLiabilityIns(Context ctx, Sheet sheet) throws Exception
	{
		Object objProLiabilityIns = ctx.get(Constants.PROFESSIONALLIABILITYINS_FREEFORM_01);
		Map proLiabilityInsMap = null;
		if(objProLiabilityIns != null)
			proLiabilityInsMap = (Map)objProLiabilityIns;
		
		Object objLimitDeductibleProLiabilityIns = ctx.get(Constants.LIMITDEDUCTIBLEPROFESSIONALLIABILITYINS_FREEFORM_01);
		Map proLimitDeductibleLiabilityInsMap = null;
		if(objLimitDeductibleProLiabilityIns != null)
			proLimitDeductibleLiabilityInsMap = (Map)objLimitDeductibleProLiabilityIns;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");		
		
		/*String strLimitAmount = ExcelUtil.getValueFromMap(proLimitDeductibleLiabilityInsMap, Constants.LimitAmount, false);
		Object objDeductibleAmount = ExcelUtil.getValueFromMap(proLimitDeductibleLiabilityInsMap, Constants.DeductibleAmount, false);
		
		if(!"".equals(strLimitAmount))
			strLimitAmount = "$" + strLimitAmount;
		ExcelUtil.setCellValue(sheet, strLimitAmount, excelFieldMapping.get(Constants.LimitAmountProIns), 0);
		
		String strDeductibleAmount = "";
		if(objDeductibleAmount != null && !"".equals(objDeductibleAmount.toString()))
			strDeductibleAmount = ExcelUtil.removeAmountFormat(objDeductibleAmount).toString();
		ExcelUtil.setCellValue(sheet, strDeductibleAmount, excelFieldMapping.get(Constants.DeductibleAmountProIns), 0);
		
		ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(proLiabilityInsMap, Constants.InsuranceCompanyNamePross, false), excelFieldMapping.get(Constants.InsuranceCompanyNamePross), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getFormattedDateFromObject(proLiabilityInsMap.get(Constants.PolicyExpirationDatePross)), excelFieldMapping.get(Constants.PolicyExpirationDatePross), 0);
		ExcelUtil.setCellValue(sheet, ExcelUtil.getFormattedDateFromObject(proLiabilityInsMap.get(Constants.ProInsPremium)), excelFieldMapping.get(Constants.ProInsPremium), 0);
		
		String strExp = ExcelUtil.getValueFromMap(proLiabilityInsMap, Constants.IsClaimExpLiability, true);
		if("Yes".equals(strExp))
		{
			ExcelUtil.setCellValue(sheet, "Yes", excelFieldMapping.get(Constants.IsClaimExpLiabilityYes), 0);
			ExcelUtil.setCellValue(sheet, "No", excelFieldMapping.get(Constants.IsClaimExpLiabilityNo), 0);
		}
		else if("No".equals(strExp))
		{
			ExcelUtil.setCellValue(sheet, "No", excelFieldMapping.get(Constants.IsClaimExpLiabilityYes), 0);
			ExcelUtil.setCellValue(sheet, "Yes", excelFieldMapping.get(Constants.IsClaimExpLiabilityNo), 0);	
		}
		else
		{
			ExcelUtil.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsClaimExpLiabilityYes), 0);
			ExcelUtil.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsClaimExpLiabilityNo), 0);
		}
		
		String strPerClaim = ExcelUtil.getValueFromMap(proLiabilityInsMap, Constants.IsPerClaim, true);
		if("Yes".equals(strPerClaim))
		{
			ExcelUtil.setCellValue(sheet, "Yes", excelFieldMapping.get(Constants.IsPerClaimYes), 0);
			ExcelUtil.setCellValue(sheet, "No", excelFieldMapping.get(Constants.IsPerClaimNo), 0);
		}
		else if("No".equals(strPerClaim))
		{
			ExcelUtil.setCellValue(sheet, "No", excelFieldMapping.get(Constants.IsPerClaimYes), 0);
			ExcelUtil.setCellValue(sheet, "Yes", excelFieldMapping.get(Constants.IsPerClaimNo), 0);	
		}
		else
		{
			ExcelUtil.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsPerClaimYes), 0);
			ExcelUtil.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsPerClaimNo), 0);
		}
		
		String strPriorDateFull = ExcelUtil.getValueFromMap(proLiabilityInsMap, Constants.IsPriorActDateFull, true);
		if("Yes".equals(strPriorDateFull))
		{
			ExcelUtil.setCellValue(sheet, "Yes", excelFieldMapping.get(Constants.IsPriorActDateFullYes), 0);
			ExcelUtil.setCellValue(sheet, "No", excelFieldMapping.get(Constants.IsPriorActDateFullNo), 0);
			ExcelUtil.setCellValue(sheet, "", excelFieldMapping.get(Constants.PriorActDatePross), 0);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(proLiabilityInsMap, Constants.FirmYear, false), excelFieldMapping.get(Constants.FirmYear), 0);
			
		}
		else if("No".equals(strPriorDateFull))
		{
			ExcelUtil.setCellValue(sheet, "No", excelFieldMapping.get(Constants.IsPriorActDateFullYes), 0);
			ExcelUtil.setCellValue(sheet, "Yes", excelFieldMapping.get(Constants.IsPriorActDateFullNo), 0);	
			ExcelUtil.setCellValue(sheet, ExcelUtil.getFormattedDateFromObject(proLiabilityInsMap.get(Constants.PriorActDatePross)), excelFieldMapping.get(Constants.PriorActDatePross), 0);
			ExcelUtil.setCellValue(sheet, "", excelFieldMapping.get(Constants.FirmYear), 0);
			
		}
		else
		{
			ExcelUtil.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsPriorActDateFullYes), 0);
			ExcelUtil.setCellValue(sheet, "", excelFieldMapping.get(Constants.IsPriorActDateFullNo), 0);
			ExcelUtil.setCellValue(sheet, "", excelFieldMapping.get(Constants.FirmYear), 0);
			ExcelUtil.setCellValue(sheet, "", excelFieldMapping.get(Constants.PriorActDatePross), 0);			
		}	*/		
		
	}
	
	public void populatePeronnalAffiliation(Context ctx, Sheet sheet) throws Exception
	{
		Object attestationObj = ctx.get(Constants.BASICINFO_LIST_01);
		if(attestationObj == null)
			return;
		
		List attestationList = null;
		if (attestationObj != null)
			attestationList = (ArrayList) attestationObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		
		for (int i = 0; i < attestationList.size(); i++) {

			Map map = (HashMap) attestationList.get(i);			
			//ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.NumberOfPersonnel, false), excelFieldMapping.get(Constants.NumberOfPersonnel +"_" + i), 0);
			
		}

	}
	
	public void populateGrossRevenue(Context ctx, Sheet sheet) throws Exception
	{
		Object attestationObj = ctx.get(Constants.BASICINFO_LIST_03);
		if(attestationObj == null)
			return;
		
		List attestationList = null;
		if (attestationObj != null)
			attestationList = (ArrayList) attestationObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		
		for (int i = 0; i < attestationList.size(); i++) {

			Map map = (HashMap) attestationList.get(i);			
			//ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.YearEndDate, false), excelFieldMapping.get(Constants.YearEndDate +"_" + i), 0);
			//ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.Amount, false), excelFieldMapping.get(Constants.Amount +"_" + i), 0);
			
		}
	}
	
	public void populateAOPList(Context ctx, Sheet sheet) throws Exception	
	{
		Object attestationObj = ctx.get(Constants.BASICINFO_LIST_AOP);
		if(attestationObj == null)
			return;
		
		List attestationList = null;
		if (attestationObj != null)
			attestationList = (ArrayList) attestationObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		
		for (int i = 0; i < attestationList.size(); i++) {

			Map map = (HashMap) attestationList.get(i);			
			/*ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.PercentageValue, false), excelFieldMapping.get(Constants.AOP_Percentage +"_" + i), 0);
			if(excelFieldMapping.get(Constants.AOPCommentDesc +"_" + i) != null)
				ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.AOPCommentDesc, false), excelFieldMapping.get(Constants.AOPCommentDesc +"_" + i), 0);
			*/
		}
	}
	
	public void populatePublicPrivateOfferingList(Context ctx, Sheet sheet) throws Exception	
	{
		Object attestationObj = ctx.get(Constants.BASICINFO_LIST_06);
		if(attestationObj == null)
			return;
		
		List attestationList = null;
		if (attestationObj != null)
			attestationList = (ArrayList) attestationObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.ClientNamePpo);
		
		for(int i = rb_federal.getRownum() + attestationList.size(); i< rb_federal.getRownum() + 16; i++)
        {
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		
		int rowtoshiftup_bld = -15 + attestationList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		
		for (int i = 0; i < attestationList.size(); i++) {

			Map map = (HashMap) attestationList.get(i);
			
			/*ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.ClientNamePpo, false), excelFieldMapping.get(Constants.ClientNamePpo), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.ClientIndustryDesc, false), excelFieldMapping.get(Constants.ClientIndustryDesc), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.ServiceIndustryDesc, false), excelFieldMapping.get(Constants.ServiceIndustryDesc), i);
			
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.YearServiceRenderedPpo, false), excelFieldMapping.get(Constants.YearServiceRenderedPpo), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.SizeOfOfferingPpo, false), excelFieldMapping.get(Constants.SizeOfOfferingPpo), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.FeesChargedPpo, false), excelFieldMapping.get(Constants.FeesChargedPpo), i);
			
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.TypeOfOfferingDesc, false), excelFieldMapping.get(Constants.TypeOfOfferingDesc), i);
			*/
		}
	}
	
	public void populateClaimList(Context ctx, Sheet sheet) throws Exception	
	{
		Object attestationObj = ctx.get(Constants.BASICINFO_LIST_04);
		if(attestationObj == null)
			return;
		
		List attestationList = null;
		if (attestationObj != null)
			attestationList = (ArrayList) attestationObj;
		
		Map excelFieldMapping = ExcelContextUtils.getMapIfPresent(ctx, "EXCEL_FIELD_MAPPING");
		RowColumnBean rb_federal = (RowColumnBean) excelFieldMapping.get(Constants.NameOfClaimantCi);
		
		for(int i = rb_federal.getRownum() + attestationList.size(); i< rb_federal.getRownum() + 16; i++)
        {
        	HSSFRow row = (HSSFRow) sheet.getRow(i);    		
        	sheet.removeRow(row);
        }
		
		int rowtoshiftup_bld = -15 + attestationList.size();
		sheet.shiftRows(rb_federal.getRownum() + 16, sheet.getLastRowNum(), rowtoshiftup_bld);
		
		for (int i = 0; i < attestationList.size(); i++) {

			Map map = (HashMap) attestationList.get(i);
			
			/*ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.NameOfClaimantCi, false), excelFieldMapping.get(Constants.NameOfClaimantCi), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.IsThisClaimReportedCi, true), excelFieldMapping.get(Constants.IsThisClaimReportedCi), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.NameOfInsuranceCompanyCi, false), excelFieldMapping.get(Constants.NameOfInsuranceCompanyCi), i);
			
			ExcelUtil.setCellValue(sheet, ExcelUtil.getFormattedDateFromObject(map.get(Constants.AllegedErrorCiDate)), excelFieldMapping.get(Constants.AllegedErrorCiDate), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getFormattedDateFromObject(map.get(Constants.ReportedCiDate)), excelFieldMapping.get(Constants.ReportedCiDate), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getFormattedDateFromObject(map.get(Constants.ClaimNotifiedCiDate)), excelFieldMapping.get(Constants.ClaimNotifiedCiDate), i);
			
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.NameOfFirmPersonnelCi, false), excelFieldMapping.get(Constants.NameOfFirmPersonnelCi), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.InsurerLossReserveCi, false), excelFieldMapping.get(Constants.InsurerLossReserveCi), i);
			*/
			//ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.LastSettlementOfferCi, false), excelFieldMapping.get(Constants.LastSettlementOfferCi), i);
			//String str = ExcelUtil.getValueFromMap(map, Constants.IsClientCi, true);
			/*if("Yes".equals(str))
				str = "Client";
			else if("No".equals(str))
				str = "Non-Client";		
			*/
			/*ExcelUtil.setCellValue(sheet, str, excelFieldMapping.get(Constants.IsClientCi), i);
			
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.ClaimantLastDemandCi, false), excelFieldMapping.get(Constants.ClaimantLastDemandCi), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getFormattedDateFromObject(map.get(Constants.ClosingCiDate)), excelFieldMapping.get(Constants.ClosingCiDate), i);
			
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.TotalClaimExpensesCi, false), excelFieldMapping.get(Constants.TotalClaimExpensesCi), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.DescOfClaimCi, false), excelFieldMapping.get(Constants.DescOfClaimCi), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.StepsTakenToPreventClaimCi, false), excelFieldMapping.get(Constants.StepsTakenToPreventClaimCi), i);
			ExcelUtil.setCellValue(sheet, ExcelUtil.getValueFromMap(map, Constants.CurrentStatus, false), excelFieldMapping.get(Constants.CurrentStatus), i);
			*/
		}
	}
	
	
	


}
