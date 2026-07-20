package com.userbo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

import com.manage.managecomponent.components.WebservicecallImpl;
import com.manage.managemetadata.functions.commonfunctions.DBUtils;
import com.manage.managemetadata.functions.commonfunctions.Math;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.processor.RequestProcessor;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.IContext;
import com.util.InetLogger;
import com.util.SystemProperties;

public class Rating {
	private static InetLogger logger = InetLogger.getInetLogger(Rating.class);		
	
	public static void processFTE(IContext ctx) throws Exception { 	
		
		boolean isRatingNew = false;        
		Object objRatingRule = RuleUtils.executeRule(ctx,"LawyersRule.checkNewFiling");
        if (objRatingRule != null && objRatingRule instanceof Boolean) {
        	isRatingNew = (Boolean) objRatingRule;
        }	   
        //Fill FTE for new filing only
		if(isRatingNew){
	        Object obj = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAllAttorneyList", ctx);
			 if(obj == null)
				 return;
			 
			List attorneyList = (List) obj;;	
			Map  mapAttorney = null;
			int noFTLawyer = 0;
			int no500Lawyers = 0;
			int no1000Lawyers = 0;
			int number = 0;
			//Single attorney will be treated as full time
			if(attorneyList.size() == 1){
				noFTLawyer = 1;
			} else {
				for(int i = 0; i < attorneyList.size(); i++){
					mapAttorney = (Map) attorneyList.get(i);
					number = Integer.parseInt(mapAttorney.get("AnnualWorkedHours").toString());
					if(number > 1000)
						noFTLawyer++;
					else if(number > 500 && number <= 1000)
						no1000Lawyers++;
					else if(number <= 500 && number > 0)
						no500Lawyers++;
				}
			}
			ctx.put("NumberOfLawyers", noFTLawyer);
			ctx.put("NumberOfLawyers1000", no1000Lawyers);
			ctx.put("NumberOfLawyers500", no500Lawyers);
			
			populateFTE(ctx);
			ctx.put("isNewFiling", "Y");
		} else {
			ctx.put("isNewFiling", "N");
		}
		
	}
	
	public static void processIndicationQuote(IContext ctx, Map quoteMap) throws Exception {
		WebservicecallImpl webSer = null;
		IContext newCtx = new Context();

		newCtx.setProject(ctx.getProject());
		newCtx.putAll(quoteMap);
		newCtx.put("PolicyKey", ctx.get("PolicyKey"));
		newCtx.put("VersionSequence", ctx.get("VersionSequence"));
		newCtx.put("AddressKey", ctx.get("AddressKey"));
		newCtx.put("StateCode", ctx.get("StateCode"));
		newCtx.put("AddressTypeKey", ctx.get("AddressTypeKey"));
		newCtx.put("CompanyKey", "1");
		newCtx.put("LOBKey", ctx.get("LOBKey"));
		newCtx.put("LookupStateCode", ctx.get("StateCode"));
		
		populateInputRequestIndication(newCtx);

		webSer = new WebservicecallImpl();
		if (ctx.get("inputMO") != null)
			webSer.setInput(ctx.get("inputMO").toString());
		if (ctx.get("outputMO") != null)
			webSer.setOutput(ctx.get("outputMO").toString());
		if (ctx.get("webservicename") != null)
			webSer.setName(ctx.get("webservicename").toString());

		String url = null;
		try {
			url = SystemProperties.getInstance().getString("appl.LawyersIns.webserviceurl");
		} catch (Exception e) {

		}

		if (url == null)
			return;

		webSer.setServiceurl(url);
		newCtx.put("responseMap", webSer.execute(newCtx));

		Element outElementError = new LawyersValidationUtils().checkIfErrorOutput(newCtx.get("responseMap"));

		if (outElementError != null) {

			List responseErrorList = new LawyersValidationUtils().getErrorListFromResponse(outElementError);
			StringBuffer buffer = new LawyersValidationUtils().addListToInetErrorList(responseErrorList);
			LawyersUtils.populateError(ctx, "SchduleRatingModifier1", buffer != null ? buffer.toString() : null);
		} else {
			populateOuputResponse(newCtx);

	    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
	    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
			SqlResources.getSqlMapProcessor(newCtx).update("PolicyTransaction.update", newCtx);
			SqlResources.getSqlMapProcessor(newCtx).update("PolicyCoverage.update", newCtx);
			SqlResources.getSqlMapProcessor(newCtx).update("RatingTrace.update", newCtx);
			SqlResources.getSqlMapProcessor(newCtx).update("Quote.update",newCtx);			
		}
	}

	
	public static void processQuotesList(IContext ctx) throws Exception {
		List quotesList = null;
		Object obj = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.quoteOptionviewgetQuoteListAll", ctx);

		if (obj != null && obj instanceof List)
			quotesList = (List) obj;

		if (quotesList == null)
			return;
		
		processFTE(ctx);

		for (int i = 0; i < quotesList.size(); i++) {
			Map quoteMap = (HashMap) quotesList.get(i);
			callingRatingEngine(ctx, quoteMap);

		}
	}

	public static void callingRatingEngine(IContext ctx, Map quoteMap)
			throws Exception {
		WebservicecallImpl webSer = null;
		IContext newCtx = new Context();

		newCtx.setProject(ctx.getProject());
		newCtx.putAll(quoteMap);
		newCtx.put("PolicyKey", ctx.get("PolicyKey"));
		newCtx.put("VersionSequence", ctx.get("VersionSequence"));
		newCtx.put("VersionKey", ctx.get("VersionKey"));
		newCtx.put("AddressKey", ctx.get("AddressKey"));
		newCtx.put("StateCode", ctx.get("StateCode"));
		newCtx.put("AddressTypeKey", ctx.get("AddressTypeKey"));
		newCtx.put("CompanyKey", "1");
		newCtx.put("LOBKey", ctx.get("LOBKey"));
		newCtx.put("LookupStateCode", ctx.get("LookupStateCode"));
		newCtx.put("ERPYear", ctx.get("ERPYear"));
		newCtx.put("FullTimeEquivalent", ctx.get("FullTimeEquivalent"));
		newCtx.put("isNewFiling", ctx.get("isNewFiling"));
    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
		
		populateInputRequest(newCtx);

		webSer = new WebservicecallImpl();
		if (ctx.get("inputMO") != null)
			webSer.setInput(ctx.get("inputMO").toString());
		if (ctx.get("outputMO") != null)
			webSer.setOutput(ctx.get("outputMO").toString());
		if (ctx.get("webservicename") != null)
			webSer.setName(ctx.get("webservicename").toString());

		String url = null;
		try {
			url = SystemProperties.getInstance().getString(
					"appl.LawyersIns.webserviceurl");
		} catch (Exception e) {

		}

		if (url == null)
			return;

		webSer.setServiceurl(url);
		newCtx.put("responseMap", webSer.execute(newCtx));
		ctx.put("ratingMapValues", newCtx.get("responseMap"));

		Element outElementError = new LawyersValidationUtils()
				.checkIfErrorOutput(newCtx.get("responseMap"));

		if (outElementError != null) {

			List responseErrorList = new LawyersValidationUtils()
					.getErrorListFromResponse(outElementError);
			StringBuffer buffer = new LawyersValidationUtils()
					.addListToInetErrorList(responseErrorList);
			LawyersUtils.populateError(ctx, "isQuoteLetterSentView",
					buffer != null ? buffer.toString() : null);
		} else {
			populateOuputResponse(newCtx);

			SqlResources.getSqlMapProcessor(newCtx).update(
					"PolicyTransaction.update", newCtx);
			SqlResources.getSqlMapProcessor(newCtx).update(
					"PolicyCoverage.update", newCtx);
			SqlResources.getSqlMapProcessor(newCtx).update(
					"RatingTrace.update", newCtx);
			SqlResources.getSqlMapProcessor(newCtx).update("Quote.update",
					newCtx);
			/*
			 * populateSurchargeTax(newCtx);
			 * 
			 */
		}
	}

	public static void populateInputRequest(IContext ctx) throws Exception {
		ctx.put("OLDCompanyKey", ctx.get("CompanyKey"));
		
		Map inputMap = new HashMap();
		fetchData(ctx, inputMap);
		populateDataInRequest(ctx, inputMap);
	}

	protected static void fetchData(IContext ctx, Map inputMap)
			throws Exception {

		Context ruleCtx = new Context();
		Object objAddress = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"Address.findByKey", ctx);
		if (objAddress != null) {
			Map mapAddress = (Map) objAddress;
			inputMap.put("City", mapAddress.get("City"));
			inputMap.put("CountyDesc", mapAddress.get("CountyDesc"));
			inputMap.put("StateCode", mapAddress.get("StateCode"));

			// This new context is only for the below rule

			ruleCtx.setProject(ctx.getProject());
			ruleCtx.put("StateCode", mapAddress.get("StateCode"));
		}

		// This code will populate Modifiers only for States that allow
		// Modifiers

		Boolean isMofifierState = false;
		Object obj = RuleUtils.executeRule(ruleCtx,
				"LawyersRule.IsModifierState");
		if (obj != null && obj instanceof Boolean) {
			isMofifierState = (Boolean) obj;
		}
		boolean isMissouriAndNewFiling = false;        
		Object objRatingRule = RuleUtils.executeRule(ctx,"LawyersRule.isMissouriAndNewFiling");
        if (objRatingRule != null && objRatingRule instanceof Boolean) {
        	isMissouriAndNewFiling = (Boolean) objRatingRule;
        }	
		if (isMofifierState)
			new Modifiers().populateCoverageModifiers(ctx);

		inputMap.put("SchduleRatingModifier1", ctx.get("SchduleRatingModifier1"));
		inputMap.put("SchduleRatingModifier2", ctx.get("SchduleRatingModifier2"));
		inputMap.put("SchduleRatingModifier3", ctx.get("SchduleRatingModifier3"));
		
		if(isMissouriAndNewFiling)
		{
			inputMap.put("SchduleRatingModifier4", ctx.get("SchduleRatingModifier4"));
			inputMap.put("SchduleRatingModifier5", ctx.get("SchduleRatingModifier5"));
			inputMap.put("SchduleRatingModifier6", ctx.get("SchduleRatingModifier6"));
		}

		// fetch policy effective date
		Object objPolicy = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"Policy.findByKey", ctx);
		if (objPolicy != null) {
			Map mapPolicy = (Map) objPolicy;
			inputMap.put("PolicyEffectiveDate", mapPolicy
					.get("PolicyEffectiveDate"));
		}

		// fetch PolicyCoverage data
		Object objPolicyCoverage = SqlResources.getSqlMapProcessor(ctx)
				.findByKey(
						"SqlStmts.UserStatementsaveAccountstmtsCoveragePolicy",
						ctx);

		if (objPolicyCoverage != null) {
			Map mapPolicyCoverage = (Map) objPolicyCoverage;
			inputMap.put("LimitAmount", mapPolicyCoverage.get("LimitAmount"));
			inputMap.put("DeductibleAmount", mapPolicyCoverage
					.get("DeductibleAmount"));

			inputMap.put("IsClaimOptionType", mapPolicyCoverage
					.get("IsClaimOptionType"));
			inputMap.put("IsClaimExpensesType", mapPolicyCoverage
					.get("IsClaimExpensesType"));

			inputMap.put("IsDollarDefense", mapPolicyCoverage
					.get("IsDollarDefense"));

			inputMap.put("ManualPremium", mapPolicyCoverage
					.get("ManualPremium"));

		}

		// fetch AOP data
		List aoplist = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields", ctx);
		if (aoplist != null) {
			Map map = new HashMap();
			for (int i = 0; i < aoplist.size(); i++) {
				map = (HashMap) aoplist.get(i);
				inputMap.put("AOP_Percentage_" + map.get("AOPKey"), map
						.get("PercentageValue"));
			}
		}

		// fetch plaintiff data if plaintiff AOP is filled
		/*
		 * We will be calculating Plaintiff and plaintiff others data depending
		 * upon the data in Plaintiff Supplement
		 */

		// check if Plaintiff AOP is taken in the application
		if (inputMap.get("AOP_Percentage_18") != null
				&& !"".equals(inputMap.get("AOP_Percentage_18").toString())) {

			List aopPlaintiffList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOLFields",ctx);
			LawyersValidationUtils objLawUtl = new LawyersValidationUtils();
			if (aopPlaintiffList != null) {
				Map map = new HashMap();
				for (int i = 0; i < aopPlaintiffList.size(); i++) {
					map = (HashMap) aopPlaintiffList.get(i);
					inputMap.put("AOL_PercentageOfRevenue_" + map.get("AOLKey"),LawyersValidationUtils.convertStringToInteger(map.get("PercentageOfRevenue") != null ? map.get("PercentageOfRevenue").toString() : ""));
					inputMap.put("AOL_PercentageOfDefense_" + map.get("AOLKey"),LawyersValidationUtils.convertStringToInteger(map.get("PercentageOfDefense") != null ? map.get("PercentageOfDefense").toString() : ""));
				}

				

				String plaintiffPercentage = objLawUtl.getPlaintiffPercentage(inputMap);
				String plaintiffOtherPercentage = objLawUtl.getPlaintiffOtherPercentage(inputMap);

				inputMap.put("PlaintiffPercentage",plaintiffPercentage);
				inputMap.put("PlaintiffOtherPercentage",plaintiffOtherPercentage);

			}
		}

		// fetch Real Estate Data If real Estate Aop is filled

		/*
		 * Now RealEstateResidential and RealEstateCommercial percentage will be
		 * captured from the application on AOP page. Earlier it has to be
		 * calculated by using the data in its supplement pages.
		 * 
		 * 
		 * if (inputMap.get("AOP_Percentage_20") != null &&
		 * !"".equals(inputMap.get("AOP_Percentage_20").toString())) {
		 * 
		 * List aoprelist = SqlResources .getSqlMapProcessor(ctx) .select(
		 * "SqlStmts.UserStatementsaveAccountstmtspopulateAOPREFields", ctx); if
		 * (aoprelist != null) { Map map = new HashMap(); for (int i = 0; i <
		 * aoprelist.size(); i++) { map = (HashMap) aoprelist.get(i);
		 * inputMap.put("AOPRE_Percentage_" + map.get("AOPREKey"), map
		 * .get("PercentageValue")); }
		 * 
		 * 
		 * 
		 * LawyersValidationUtils objLawUtl = new LawyersValidationUtils();
		 * 
		 * String RealEstateResidentialPercentage = objLawUtl
		 * .getRealEstateResidentialPercentage(inputMap); String
		 * RealEstateCommercialPercentage = objLawUtl
		 * .getRealEstateCommercialPercentage(inputMap);
		 * 
		 * inputMap.put("RealEstateResidentialPercentage",
		 * RealEstateResidentialPercentage);
		 * inputMap.put("RealEstateCommercialPercentage",
		 * RealEstateCommercialPercentage);
		 * 
		 *  } }
		 */

		// fetch FirmLW data
		Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"FirmLW.findByKey", ctx);
		if (objFirm != null) {
			Map mapFirm = (Map) objFirm;
			if (mapFirm.get("NumberOfLawyers") != null) {
				inputMap.put("NumberOfLawyers", mapFirm.get("NumberOfLawyers"));
				
			}

			inputMap.put("IsTaxCalculation",
					mapFirm.get("IsTaxCalculation") != null ? mapFirm
							.get("IsTaxCalculation") : "");

		}

		//To get full time lawyers for newFiling
		List objClaimAge = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAllClaimAgeLWList", ctx);		
		if(ctx.get("isNewFiling") != null && "Y".equals(ctx.get("isNewFiling").toString())){
			List listAttrys = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAllAttorneyList", ctx);
			int num = 0;
			if (listAttrys != null) {
				Map attryMap = new HashMap();
				if(listAttrys.size() == 1){
					num = 1;
					attryMap = (Map) listAttrys.get(0);
					if(attryMap.get("AnnualWorkedHours") != null){
						if (objClaimAge != null) {
							Map claimMap = new HashMap();
							claimMap = (Map) objClaimAge.get(0);
							inputMap.put("ClaimAge_" + (num), claimMap.get("Year"));
						}
					}
					inputMap.put("NumberOfLawyers", String.valueOf(num));
				} else {
					for (int i = 0; i < listAttrys.size(); i++) {
						attryMap = (Map) listAttrys.get(i);
						if(attryMap.get("AnnualWorkedHours") != null){
							int annualWorkedHours = Integer.parseInt(attryMap.get("AnnualWorkedHours").toString());
							if(annualWorkedHours > 1000){
								num++;
								if (objClaimAge != null) {
									Map claimMap = new HashMap();
									claimMap = (Map) objClaimAge.get(i);
									inputMap.put("ClaimAge_" + (num), claimMap.get("Year"));
								}
							}
						}
					}			
					inputMap.put("NumberOfLawyers", String.valueOf(num));
				}
			}
		} else {
			//old logic
			List attList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.quoteOptionviewgetAttorneysList", ctx);
			if (attList != null && attList.size() > 0) {
				String num = String.valueOf(attList.size());
				inputMap.put("NumberOfLawyers", num);
			}			
			
			if (objClaimAge != null) {
				Map claimMap = new HashMap();
				for (int i = 0; i < objClaimAge.size(); i++) {
					claimMap = (Map) objClaimAge.get(i);
					inputMap.put("ClaimAge_" + (i + 1), claimMap.get("Year"));

				}
			}
			
		}
		
	}

	protected static void populateDataInRequest(IContext ctx, Map inputMap)
			throws Exception {		
		Context ruleCtx = new Context();
		Object objAddress = SqlResources.getSqlMapProcessor(ctx).findByKey("Address.findByKey", ctx);
		if (objAddress != null) {
			Map mapAddress = (Map) objAddress;
			inputMap.put("City", mapAddress.get("City"));
			inputMap.put("CountyDesc", mapAddress.get("CountyDesc"));
			inputMap.put("StateCode", mapAddress.get("StateCode"));
			ruleCtx.setProject(ctx.getProject());
			ruleCtx.put("StateCode", mapAddress.get("StateCode"));
		}

		// This code will populate Modifiers only for States that allow
		// Modifiers

		Boolean isMofifierState = false;
		Object objs = RuleUtils.executeRule(ruleCtx,"LawyersRule.IsModifierState");
		if (objs != null && objs instanceof Boolean) {
			isMofifierState = (Boolean) objs;
		}

		if (isMofifierState)
		new Modifiers().populateCoverageModifiers(ctx);

		ctx.put("SchduleRatingModifier1", inputMap.get("SchduleRatingModifier1"));
		ctx.put("SchduleRatingModifier2", inputMap.get("SchduleRatingModifier2"));
		
		int schduleRatingModifier3=inputMap.get("SchduleRatingModifier3")!=null && !inputMap.get("SchduleRatingModifier3").equals(HtmlConstants.EMPTY)? Integer.parseInt(inputMap.get("SchduleRatingModifier3").toString()):0;
		int schduleRatingModifier4=inputMap.get("SchduleRatingModifier4")!=null && !inputMap.get("SchduleRatingModifier4").equals(HtmlConstants.EMPTY)? Integer.parseInt(inputMap.get("SchduleRatingModifier4").toString()):0;
		int schduleRatingModifier5=inputMap.get("SchduleRatingModifier5")!=null && !inputMap.get("SchduleRatingModifier5").equals(HtmlConstants.EMPTY)? Integer.parseInt(inputMap.get("SchduleRatingModifier5").toString()):0;
		int schduleRatingModifier6=inputMap.get("SchduleRatingModifier6")!=null && !inputMap.get("SchduleRatingModifier6").equals(HtmlConstants.EMPTY)? Integer.parseInt(inputMap.get("SchduleRatingModifier6").toString()):0;
		
		ctx.put("SchduleRatingModifier3",schduleRatingModifier3+schduleRatingModifier4+schduleRatingModifier5+schduleRatingModifier6 );
		ctx.remove("SchduleRatingModifier4");
		ctx.remove("SchduleRatingModifier5");
		ctx.remove("SchduleRatingModifier6");
		
		
		ctx.put("PolicyEffectiveDate", inputMap.get("PolicyEffectiveDate"));

		ctx.put("TradeRegulationPercentage", inputMap.get("AOP_Percentage_1"));
		ctx.put("ArbitrationPercentage", inputMap.get("AOP_Percentage_2"));
		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementdroolQueriesgetPolicyData", ctx);
		if(obj != null && obj instanceof Map )
		{
			Map map = (Map)obj;
			if(map != null && map.get("AppCreatedDate")!= null && !HtmlConstants.EMPTY.equals(map.get("AppCreatedDate").toString())){
				ctx.put("AppCreatedDate",map.get("AppCreatedDate").toString());
			}
		}
		boolean followOld=false;
		Object newObj = RuleUtils.executeRule(ctx, "LawyersRule.isOldAppFlow");	
		if(newObj != null && newObj instanceof Boolean )
			followOld =(Boolean) newObj;
		if(followOld==true)
		{
		ctx.put("BankruptcyPercentage", inputMap.get("AOP_Percentage_3"));
		ctx.put("PlaintiffPercentage", inputMap.get("PlaintiffPercentage"));
		ctx.put("PlaintiffOtherPercentage", inputMap.get("PlaintiffOtherPercentage"));
		}
		else
		ctx.put("BankruptcyNewAppPercentage",inputMap.get("AOP_Percentage_3"));
		
		ctx.put("CivilRightsPercentage", inputMap.get("AOP_Percentage_4"));
		ctx.put("CorporatePercentage", inputMap.get("AOP_Percentage_5"));
		ctx.put("CopyrightTrademarkPercentage", inputMap
				.get("AOP_Percentage_6"));
		ctx.put("DefensePercentage", inputMap.get("AOP_Percentage_7"));
		ctx
				.put("DomesticRelationsPercentage", inputMap
						.get("AOP_Percentage_8"));
		ctx.put("ElderLawPercentage", inputMap.get("AOP_Percentage_9"));
		ctx.put("EnvironmentalPercentage", inputMap.get("AOP_Percentage_10"));
		ctx.put("ErisaPercentage", inputMap.get("AOP_Percentage_11"));
		ctx.put("FinancialInstitutionPercentage", inputMap
				.get("AOP_Percentage_12"));
		ctx.put("GovernmentPercentage", inputMap.get("AOP_Percentage_13"));
		ctx.put("ImmigrationPercentage", inputMap.get("AOP_Percentage_14"));
		ctx.put("InvestmentCounselingPercentage", inputMap
				.get("AOP_Percentage_15"));
		ctx.put("NaturalResourcePercentage", inputMap.get("AOP_Percentage_16"));
		ctx.put("PersonalContractsPercentage", inputMap
				.get("AOP_Percentage_17"));
		
		ctx.put("PublicUtilitiesPercentage", inputMap.get("AOP_Percentage_19"));
		// ctx.put("RealEstatePercentage", inputMap.get("AOP_Percentage_20"));
		ctx.put("SecuritiesPercentage", inputMap.get("AOP_Percentage_21"));
		ctx.put("TaxPercentage", inputMap.get("AOP_Percentage_22"));
		ctx.put("TitlePercentage", inputMap.get("AOP_Percentage_23"));
		ctx.put("WillsPercentage", inputMap.get("AOP_Percentage_24"));
		ctx.put("OtherPercentage", inputMap.get("AOP_Percentage_25"));
		ctx.put("RealEstateCommercialPercentage", inputMap.get("AOP_Percentage_20"));
		ctx.put("RealEstateResidentialPercentage", inputMap.get("AOP_Percentage_27"));
		/*******************Addition Of new Aops*************************************/
		if(followOld==false)
		{
		ctx.put("AdmiraltyPercentage", inputMap.get("AOP_Percentage_28"));
		ctx.put("AdministrativeorRegulatoryPercentage", inputMap.get("AOP_Percentage_54"));
		ctx.put("AppellatePracticePercentage", inputMap.get("AOP_Percentage_29"));
		ctx.put("AviationPercentage", inputMap.get("AOP_Percentage_30"));
		ctx.put("CommunicationsPercentage", inputMap.get("AOP_Percentage_32"));
		ctx.put("ConstructionPercentage", inputMap.get("AOP_Percentage_33"));
		ctx.put("CreditorDebtorRightsPercentage", inputMap.get("AOP_Percentage_34"));
		ctx.put("CriminalDefensePercentage", inputMap.get("AOP_Percentage_35"));
		ctx.put("EducationPercentage", inputMap.get("AOP_Percentage_36"));
		ctx.put("EmploymentPercentage", inputMap.get("AOP_Percentage_37"));
		ctx.put("EntertainmentPercentage", inputMap.get("AOP_Percentage_38"));
		ctx.put("HealthcarePercentage", inputMap.get("AOP_Percentage_39"));
		ctx.put("ImmigrationPercentage", inputMap.get("AOP_Percentage_14"));
		ctx.put("InsuranceDefensePercentage", inputMap.get("AOP_Percentage_41"));
		ctx.put("InternationalPercentage", inputMap.get("AOP_Percentage_42"));
		ctx.put("JuvenilePercentage", inputMap.get("AOP_Percentage_43"));
		ctx.put("LaborManagementPercentage", inputMap.get("AOP_Percentage_44"));
		ctx.put("LaborUnionPercentage", inputMap.get("AOP_Percentage_45"));
		ctx.put("MergersAcquisitionsPercentage", inputMap.get("AOP_Percentage_46"));
		ctx.put("NonProfitPercentage", inputMap.get("AOP_Percentage_47"));
		ctx.put("PatentPercentage", inputMap.get("AOP_Percentage_48"));
		ctx.put("SocialSecurityPercentage", inputMap.get("AOP_Percentage_49"));
		ctx.put("TrafficDUIPercentage", inputMap.get("AOP_Percentage_50"));
		ctx.put("UtilitiesPercentage", inputMap.get("AOP_Percentage_51"));
		ctx.put("WorkersDefensePercentage", inputMap.get("AOP_Percentage_52"));
		ctx.put("WorkersPlaintiffPercentage", inputMap.get("AOP_Percentage_53"));
		ctx.put("CollectionsPercentage",inputMap.get("AOP_Percentage_31"));
		/*****************************Litigation AOPS********************************/
		boolean isBeforeLitigation=(Boolean) RuleUtils.executeRule(ctx, "LawyersRule.beforeLitigationImplementationDate");
		if(isBeforeLitigation)
		{
			
			ctx.put("CivilPlaintiffPercentage", LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfRevenue_14")));
			ctx.put("ConstructionPlaintiffPercentage", LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfRevenue_15")));
			ctx.put("CorporatePlaintiffPercentage",LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfRevenue_4")));
			ctx.put("MassTortPlaintiffPercentage", LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfRevenue_11")));
			ctx.put("MedicalMalpracticePlaintiffPercentage", LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfRevenue_5")));
			ctx.put("PersonalInjuryPlaintiffPercentage", LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfRevenue_6")));
			ctx.put("PlaintiffOtherPlaintiffPercentage",  LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfRevenue_13")));
			ctx.put("ProductLiabilityPlaintiffPercentage",  LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfRevenue_8")));
			ctx.put("NonMedicalMalpracticePlaintiffPercentage", LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfRevenue_9")));
			
			ctx.put("CivilDefensePercentage",LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfDefense_14")));
			ctx.put("ConstructionDefensePercentage", LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfDefense_15")));
			ctx.put("CorporateDefensePercentage", LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfDefense_4")));
			ctx.put("MassTortDefensePercentage", LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfDefense_11")));
			ctx.put("MedicalMalpracticeDefensePercentage", LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfDefense_5")));
			ctx.put("PersonalInjuryDefensePercentage", LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfDefense_6")));
			ctx.put("PlaintiffOtherDefesePercentage", LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfDefense_13")));
			ctx.put("ProductLiabilityDefensePercentage",LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfDefense_8")));
			ctx.put("NonMedicalMalpracticeDefensePercentage",LawyersValidationUtils.getPlaintiffSup(inputMap.get("AOP_Percentage_18"),inputMap.get("AOL_PercentageOfDefense_9")));
	

		}
		else
		{
			

			
			ctx.put("CivilPlaintiffPercentage", inputMap.get("AOP_Percentage_55"));
			ctx.put("ConstructionPlaintiffPercentage", inputMap.get("AOP_Percentage_56"));
			ctx.put("CorporatePlaintiffPercentage", inputMap.get("AOP_Percentage_57"));
			ctx.put("MassTortPlaintiffPercentage", inputMap.get("AOP_Percentage_59"));
			ctx.put("MedicalMalpracticePlaintiffPercentage", inputMap.get("AOP_Percentage_60"));
			ctx.put("PersonalInjuryPlaintiffPercentage", inputMap.get("AOP_Percentage_62"));
			ctx.put("PlaintiffOtherPlaintiffPercentage", inputMap.get("AOP_Percentage_67"));
			ctx.put("ProductLiabilityPlaintiffPercentage", inputMap.get("AOP_Percentage_63"));
			ctx.put("NonMedicalMalpracticePlaintiffPercentage", inputMap.get("AOP_Percentage_61"));
			
			
			ctx.put("CivilDefensePercentage", inputMap.get("AOP_Percentage_64"));
			ctx.put("ConstructionDefensePercentage", inputMap.get("AOP_Percentage_65"));
			ctx.put("CorporateDefensePercentage", inputMap.get("AOP_Percentage_66"));
			ctx.put("MassTortDefensePercentage", inputMap.get("AOP_Percentage_68"));
			ctx.put("MedicalMalpracticeDefensePercentage", inputMap.get("AOP_Percentage_69"));
			ctx.put("PersonalInjuryDefensePercentage", inputMap.get("AOP_Percentage_71"));
			ctx.put("PlaintiffOtherDefesePercentage", inputMap.get("AOP_Percentage_58"));
			ctx.put("ProductLiabilityDefensePercentage", inputMap.get("AOP_Percentage_72"));
			ctx.put("NonMedicalMalpracticeDefensePercentage",inputMap.get("AOP_Percentage_70"));
			
		}
		}
		/*
		 * ctx.put("RealEstateCommercialPercentage", inputMap
		 * .get("RealEstateCommercialPercentage"));
		 * ctx.put("RealEstateResidentialPercentage", inputMap
		 * .get("RealEstateResidentialPercentage"));
		 */

		ctx.put("LimitAmount", inputMap.get("LimitAmount"));
		ctx.put("DeductibleAmount", inputMap.get("DeductibleAmount"));
		ctx.put("IsClaimOptionType", inputMap.get("IsClaimOptionType"));
		ctx.put("IsClaimExpensesType", inputMap.get("IsClaimExpensesType"));
		ctx.put("NumberOfLawyers", inputMap.get("NumberOfLawyers"));
		ctx.put("IsTaxCalculation", inputMap.get("IsTaxCalculation"));

		ctx.put("StateCode", inputMap.get("StateCode"));

		// For County and City
		if (inputMap.get("City") != null) {
			String city = inputMap.get("City").toString();
			city = city.replace("&", "#AMP#");
			ctx.put("City", city);
		}
		if (inputMap.get("CountyDesc") != null) {
			String county = inputMap.get("CountyDesc").toString();
			county = county.replace("&", "#AMP#");
			ctx.put("CountyDesc", county);
		}
		ctx.put("CompanyKey", "1");
		ctx.put("IsDollarDefense", inputMap.get("IsDollarDefense"));
		ctx.put("ManualPremium",
				inputMap.get("ManualPremium") != null ? inputMap
						.get("ManualPremium") : null);

		int endorsementKey=ctx.get("EndorsementType")!=null && !ctx.get("EndorsementType").equals(HtmlConstants.EMPTY) ?Integer.parseInt(ctx.get("EndorsementType").toString()):0;
		
		if( endorsementKey!=12)
		{
		ctx.put("ClaimAge_1", inputMap.get("ClaimAge_1") != null ? inputMap
				.get("ClaimAge_1") : null);
		ctx.put("ClaimAge_2", inputMap.get("ClaimAge_2") != null ? inputMap
				.get("ClaimAge_2") : null);
		ctx.put("ClaimAge_3", inputMap.get("ClaimAge_3") != null ? inputMap
				.get("ClaimAge_3") : null);
		ctx.put("ClaimAge_4", inputMap.get("ClaimAge_4") != null ? inputMap
				.get("ClaimAge_4") : null);
		ctx.put("ClaimAge_5", inputMap.get("ClaimAge_5") != null ? inputMap
				.get("ClaimAge_5") : null);
		ctx.put("ClaimAge_6", inputMap.get("ClaimAge_6") != null ? inputMap
				.get("ClaimAge_6") : null);
		ctx.put("ClaimAge_7", inputMap.get("ClaimAge_7") != null ? inputMap
				.get("ClaimAge_7") : null);
		ctx.put("ClaimAge_8", inputMap.get("ClaimAge_8") != null ? inputMap
				.get("ClaimAge_8") : null);
		ctx.put("ClaimAge_9", inputMap.get("ClaimAge_9") != null ? inputMap
				.get("ClaimAge_9") : null);
		ctx.put("ClaimAge_10", inputMap.get("ClaimAge_10") != null ? inputMap
				.get("ClaimAge_10") : null);
		ctx.put("ClaimAge_11", inputMap.get("ClaimAge_11") != null ? inputMap
				.get("ClaimAge_11") : null);
		ctx.put("ClaimAge_12", inputMap.get("ClaimAge_12") != null ? inputMap
				.get("ClaimAge_12") : null);
		ctx.put("ClaimAge_13", inputMap.get("ClaimAge_13") != null ? inputMap
				.get("ClaimAge_7") : null);
		ctx.put("ClaimAge_14", inputMap.get("ClaimAge_14") != null ? inputMap
				.get("ClaimAge_14") : null);
		ctx.put("ClaimAge_15", inputMap.get("ClaimAge_15") != null ? inputMap
				.get("ClaimAge_15") : null);
		ctx.put("ClaimAge_16", inputMap.get("ClaimAge_16") != null ? inputMap
				.get("ClaimAge_16") : null);
		ctx.put("ClaimAge_17", inputMap.get("ClaimAge_17") != null ? inputMap
				.get("ClaimAge_17") : null);
		ctx.put("ClaimAge_18", inputMap.get("ClaimAge_18") != null ? inputMap
				.get("ClaimAge_18") : null);
		ctx.put("ClaimAge_19", inputMap.get("ClaimAge_19") != null ? inputMap
				.get("ClaimAge_19") : null);
		ctx.put("ClaimAge_20", inputMap.get("ClaimAge_20") != null ? inputMap
				.get("ClaimAge_20") : null);
		ctx.put("ClaimAge_21", inputMap.get("ClaimAge_21") != null ? inputMap
				.get("ClaimAge_21") : null);
		ctx.put("ClaimAge_22", inputMap.get("ClaimAge_22") != null ? inputMap
				.get("ClaimAge_22") : null);
		ctx.put("ClaimAge_23", inputMap.get("ClaimAge_23") != null ? inputMap
				.get("ClaimAge_23") : null);
		ctx.put("ClaimAge_24", inputMap.get("ClaimAge_24") != null ? inputMap
				.get("ClaimAge_24") : null);
		ctx.put("ClaimAge_25", inputMap.get("ClaimAge_25") != null ? inputMap
				.get("ClaimAge_25") : null);
		}
		
		ctx.put("requestxml", new RequestProcessor()
				.generateRequestXml((Context) ctx));

	}

	public static void populateOuputResponse(IContext ctx) throws Exception {
		Map responseMap = null;
		if (ctx.get("responseMap") != null)
			responseMap = (Map) ctx.get("responseMap");

		/**
		 * <PolicyPremiumWithoutSM>7282.148097599999</PolicyPremiumWithoutSM>
		 * <ActualFinalBaseLimitPremium>8374.0</ActualFinalBaseLimitPremium>
		 * <MTTaxAmmount>837.4</MTTaxAmmount> <CountyTaxAmmount>837.4</CountyTaxAmmount>
		 * <State1TaxAmmount>150.73</State1TaxAmmount> <State2TaxAmmount>0.0</State2TaxAmmount>
		 * <FinalBaseLimitPremiumWithTax>10199.53</FinalBaseLimitPremiumWithTax>
		 */
		if (responseMap != null) {

			double mTCountyTax = 0.0;
			// login to check if Tax is allowed
			boolean doNotCalculateTax = false;
			Object obj = RuleUtils.executeRule(ctx,
					"LawyersRule.doNotCalculateTax");
			if (obj != null && obj instanceof Boolean)
				doNotCalculateTax = (Boolean) obj;

			if (!doNotCalculateTax) {
				if (responseMap.get("MTTaxAmmount") != null
						&& !"".equals(responseMap.get("MTTaxAmmount")
								.toString())) {
					mTCountyTax = Double.parseDouble(responseMap.get(
							"MTTaxAmmount").toString());
					ctx.put("MTTaxAmmount", Math.round(responseMap
							.get("MTTaxAmmount")));
				}
			}
			if (responseMap.get("CountyTaxAmmount") != null
					&& !"".equals(responseMap.get("CountyTaxAmmount")
							.toString())) {
				if (mTCountyTax <= 0) {
					mTCountyTax = mTCountyTax
							+ Double.parseDouble(responseMap.get(
									"CountyTaxAmmount").toString());
					ctx.put("CountyTaxAmmount", Math.round(responseMap
							.get("CountyTaxAmmount")));
				}
			}

			if (responseMap.get("State1TaxAmmount") != null
					&& !"".equals(responseMap.get("State1TaxAmmount")
							.toString())) {
				mTCountyTax = mTCountyTax
						+ Double.parseDouble(responseMap
								.get("State1TaxAmmount").toString());
				ctx.put("State1TaxAmmount", Math.round(responseMap
						.get("State1TaxAmmount")));
			}
			if (responseMap.get("State2TaxAmmount") != null
					&& !"".equals(responseMap.get("State2TaxAmmount")
							.toString())) {
				mTCountyTax = mTCountyTax
						+ Double.parseDouble(responseMap
								.get("State2TaxAmmount").toString());
				ctx.put("State2TaxAmmount", Math.round(responseMap
						.get("State2TaxAmmount")));
			}

			ctx.put("TotalPolicyTaxAmount", Math.round(mTCountyTax));
			ctx.put("TotalCoverageTaxAmount", Math.round(mTCountyTax));

			// calculating Invoiced premium with Tax

			String premium = responseMap.get("ActualFinalBaseLimitPremium") != null
					&& !"".equals(responseMap
							.get("ActualFinalBaseLimitPremium").toString()) ? responseMap
					.get("ActualFinalBaseLimitPremium").toString()
					: "0";

			double premiumWithoutTax = Double.parseDouble(premium);
			double totalTax = mTCountyTax;
			double premiumWithTax = premiumWithoutTax + totalTax;

			// For policy Transaction
			ctx.put("TotalPremium", Math.round(responseMap
					.get("ActualFinalBaseLimitPremium")));
			ctx.put("InvoicedPremium", Math.round(responseMap
					.get("FinalBaseLimitPremiumWithTaxRatingEngine")));

			// for policy Coverage
			ctx.put("InitialCovPremium", Math.round(responseMap
					.get("ActualFinalBaseLimitPremium")));
			ctx.put("Premium", Math.round(responseMap
					.get("FinalBaseLimitPremiumWithTaxRatingEngine")));

			ctx.put("IsMinimumPremium", responseMap.get("IsMinimumPremium"));

			ctx.put("MinimumPremiumValue", responseMap.get("MinimumPremiumValue"));

			if (ctx.get("InvoicedPremium") != null) {
				int divider = 10; // Default

				if (SystemProperties.getInstance().getString(
						"appl.LawyersIns.firstrangedividerFQ") != null)
					divider = new Integer(SystemProperties.getInstance()
							.getString("appl.LawyersIns.firstrangedividerFQ"));

				double FirstRange = Double.parseDouble(ctx.get(
						"InvoicedPremium").toString())
						/ divider;

				if (ctx.get("IsMinimumPremium") != null
						&& "Y".equals(ctx.get("IsMinimumPremium").toString())) {
					ctx.put("TotalPremiumFirstRange", ctx
							.get("InvoicedPremium"));
				} else {
					FirstRange = Double.parseDouble(ctx.get("InvoicedPremium")
							.toString())
							- FirstRange;
					if (ctx.get("MinimumPremiumValue") != null) {
						double minPreVal = new Double(ctx.get(
								"MinimumPremiumValue").toString());
						if (FirstRange < minPreVal)
							FirstRange = minPreVal;
					}

					Object TotalPremiumFirstRange = Math.decimalFormat(
							FirstRange, 0);
					ctx.put("TotalPremiumFirstRange", TotalPremiumFirstRange);
				}
			} else
				ctx.put("TotalPremiumFirstRange", 0);

			if (responseMap.get("InputXML") != null)
				ctx.put("XmlInputDatatoRating", responseMap.get("InputXML")
						.toString());
			if (responseMap.get("OutputXML") != null)
				ctx.put("XmlOutputDatafromRating", responseMap.get("OutputXML")
						.toString());
		}

		int totalPMP = 0;
		if (ctx.get("SchduleRatingModifier1") != null
				&& !"".equals(ctx.get("SchduleRatingModifier1").toString()))
			totalPMP = Integer.parseInt((ctx.get("SchduleRatingModifier1")
					.toString()));
		if (ctx.get("SchduleRatingModifier2") != null
				&& !"".equals(ctx.get("SchduleRatingModifier2").toString()))
			totalPMP = totalPMP
					+ Integer.parseInt((ctx.get("SchduleRatingModifier2")
							.toString()));
		if (ctx.get("SchduleRatingModifier3") != null
				&& !"".equals(ctx.get("SchduleRatingModifier3").toString()))
			totalPMP = totalPMP
					+ Integer.parseInt((ctx.get("SchduleRatingModifier3")
							.toString()));

		ctx.put("TotalPolicyModifierPercentage", totalPMP);
		ctx.put("TotalCovModifierPercentage", totalPMP);
		ctx.put("CompanyKey", ctx.get("OLDCompanyKey"));
	}

	public static void populateSurchargeTax(IContext ctx) throws Exception {
		List chrgsList = null;
		Object obj1 = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsSurchargeTax", ctx);

		if (obj1 != null && obj1 instanceof List)
			chrgsList = (List) obj1;

		if (chrgsList == null || (chrgsList != null && chrgsList.size() == 0)) {
			if (ctx.get("MTTaxAmmount") != null) {
				ctx.put("SurchargeTaxTypeKey", 1);
				ctx.put("TaxAmount", ctx.get("MTTaxAmmount"));
				SqlResources.getSqlMapProcessor(ctx).insert(
						"SurchargeTax.insert", ctx);
			} else if (ctx.get("CountyTaxAmmount") != null) {
				ctx.put("SurchargeTaxTypeKey", 2);
				ctx.put("TaxAmount", ctx.get("CountyTaxAmmount"));
				SqlResources.getSqlMapProcessor(ctx).insert(
						"SurchargeTax.insert", ctx);
			}

			if (ctx.get("State1TaxAmmount") != null) {
				ctx.put("SurchargeTaxTypeKey", 3);
				ctx.put("TaxAmount", ctx.get("State1TaxAmmount"));
				SqlResources.getSqlMapProcessor(ctx).insert(
						"SurchargeTax.insert", ctx);
			}
			if (ctx.get("State2TaxAmmount") != null) {
				ctx.put("SurchargeTaxTypeKey", 4);
				ctx.put("TaxAmount", ctx.get("State2TaxAmmount"));
				SqlResources.getSqlMapProcessor(ctx).insert(
						"SurchargeTax.insert", ctx);
			}
		}

		IContext newCtx = null;
		if (chrgsList != null && chrgsList.size() > 0) {
			for (int i = 0; i < chrgsList.size(); i++) {
				Map chrgsMap = (HashMap) chrgsList.get(i);
				newCtx = new Context();
				newCtx.putAll(chrgsMap);
				newCtx.setProject(ctx.getProject());
		    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
		    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));

				if (newCtx.get("SurchargeTaxTypeKey") != null
						&& "1".equals(newCtx.get("SurchargeTaxTypeKey")
								.toString())) {
					newCtx.put("TaxAmount", ctx.get("MTTaxAmmount"));
					SqlResources.getSqlMapProcessor(newCtx).update(
							"SurchargeTax.update", newCtx);
				} else if (newCtx.get("SurchargeTaxTypeKey") != null
						&& "2".equals(newCtx.get("SurchargeTaxTypeKey")
								.toString())) {
					newCtx.put("TaxAmount", ctx.get("CountyTaxAmmount"));
					SqlResources.getSqlMapProcessor(newCtx).update(
							"SurchargeTax.update", newCtx);
				} else if (newCtx.get("SurchargeTaxTypeKey") != null
						&& "3".equals(newCtx.get("SurchargeTaxTypeKey")
								.toString())) {
					newCtx.put("TaxAmount", ctx.get("State1TaxAmmount"));
					SqlResources.getSqlMapProcessor(newCtx).update(
							"SurchargeTax.update", newCtx);
				} else if (newCtx.get("SurchargeTaxTypeKey") != null
						&& "4".equals(newCtx.get("SurchargeTaxTypeKey")
								.toString())) {
					newCtx.put("TaxAmount", ctx.get("State2TaxAmmount"));
					SqlResources.getSqlMapProcessor(newCtx).update(
							"SurchargeTax.update", newCtx);
				}
			}
		}

	}

	public static void processQuotesListQuickQuote(IContext ctx)
			throws Exception {
		List quotesList = null;
		Object obj = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetQuoteListAll", ctx);

		if (obj != null && obj instanceof List)
			quotesList = (List) obj;

		if (quotesList == null)
			return;

		for (int i = 0; i < quotesList.size(); i++) {
			Map quoteMap = (HashMap) quotesList.get(i);
			callingRatingEngineQuickQuote(ctx, quoteMap);
		}
	}

	public static void callingRatingEngineQuickQuote(IContext ctx, Map quoteMap)
			throws Exception {
		WebservicecallImpl webSer = null;
		IContext newCtx = new Context();

		newCtx.setProject(ctx.getProject());
		newCtx.putAll(quoteMap);
		newCtx.put("PolicyKey", ctx.get("PolicyKey"));
		newCtx.put("VersionKey", ctx.get("VersionKey"));
		newCtx.put("VersionSequence", ctx.get("VersionSequence"));
		newCtx.put("AddressKey", ctx.get("AddressKey"));
		newCtx.put("AddressTypeKey", ctx.get("AddressTypeKey"));
		newCtx.put("CompanyKey", "1");
		newCtx.put("LOBKey", ctx.get("LOBKey"));
		newCtx.put("LookupStateCode", ctx.get("LookupStateCode"));
		newCtx.put("ERPYear", ctx.get("ERPYear"));
		if(!"Y".equals(ctx.get("IsIndicationPage"))) {
			newCtx.put("AggregateLimit", ctx.get("AggregateLimit"));
			newCtx.put("OccuranceLimit", ctx.get("OccuranceLimit"));
		}
		
		newCtx.put("CutOffDate", ctx.get("CutOffDate"));
		newCtx.put("NewAppFlowCutOffDate", ctx.get("NewAppFlowCutOffDate"));
		newCtx.put("CutOffDateGroup2", ctx.get("CutOffDateGroup2"));
		newCtx.put("CutOffDateGroup3", ctx.get("CutOffDateGroup3"));
		newCtx.put("CutOffDateGroup4", ctx.get("CutOffDateGroup4"));
		newCtx.put("CutOffDateGroup5", ctx.get("CutOffDateGroup5"));
		newCtx.put("CutOffDateGroup6", ctx.get("CutOffDateGroup6"));
		newCtx.put("CutOffDateGroup7", ctx.get("CutOffDateGroup7"));
		newCtx.put("NumberOfLawyers", ctx.get("NumberOfLawyers"));
		newCtx.put("QQCurrentEffectiveDateFlag", ctx.get("QQCurrentEffectiveDateFlag"));
		newCtx.put("CutOffDateGroup8", ctx.get("CutOffDateGroup8"));
		newCtx.put("CutOffDateCCBSupp", ctx.get("CutOffDateCCBSupp"));
		newCtx.put("CutOffDate2020", ctx.get("CutOffDate2020"));
		populateInputRequestQuickQuote(newCtx);
		webSer = new WebservicecallImpl();
		if (ctx.get("inputMO") != null)
			webSer.setInput(ctx.get("inputMO").toString());
		if (ctx.get("outputMO") != null)
			webSer.setOutput(ctx.get("outputMO").toString());
		if (ctx.get("webservicename") != null)
			webSer.setName(ctx.get("webservicename").toString());

		String url = null;
		try {
			url = SystemProperties.getInstance().getString(
					"appl.LawyersIns.webserviceurl");
		} catch (Exception e) {

		}

		if (url == null)
			return;

		webSer.setServiceurl(url);
		newCtx.put("responseMap", webSer.execute(newCtx));
		newCtx.get("responseMap");
		populateOuputResponseQuickQuote(newCtx);

    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
		SqlResources.getSqlMapProcessor(newCtx).update(
				"PolicyTransaction.update", newCtx);
		SqlResources.getSqlMapProcessor(newCtx).update("PolicyCoverage.update",
				newCtx);
		SqlResources.getSqlMapProcessor(newCtx).update("RatingTrace.update",
				newCtx);
		SqlResources.getSqlMapProcessor(newCtx).update("Quote.update", newCtx);
		RuleUtils.executeRule(newCtx,"LawyersRule.AssignCreatedByAndCreatedTimeStamp");
		DBUtils.executeDBOperation(newCtx, "QuotationEstimation", "1");
	}

	public static void populateInputRequestQuickQuote(IContext ctx)
			throws Exception {
		Map inputMap = new HashMap();
		fetchDataQuickQuote(ctx, inputMap);
		populateDataInRequest(ctx, inputMap);
	}

	protected static void fetchDataQuickQuote(IContext ctx, Map inputMap)
			throws Exception {

		Context ruleCtx = new Context();
		
		ruleCtx.put("PolicyKey", ctx.get("PolicyKey"));
		ruleCtx.put("VersionSequence", ctx.get("VersionSequence"));
		ruleCtx.put("AddressKey", ctx.get("AddressKey"));
		ruleCtx.put("AddressTypeKey", ctx.get("AddressTypeKey"));
		ruleCtx.put("CompanyKey", "1");
		ruleCtx.put("LOBKey", ctx.get("LOBKey"));
		ruleCtx.put("LookupStateCode", ctx.get("LookupStateCode"));
		ruleCtx.put("ERPYear", ctx.get("ERPYear"));
		ruleCtx.put("CutOffDate", ctx.get("CutOffDate"));
		ruleCtx.put("NewAppFlowCutOffDate", ctx.get("NewAppFlowCutOffDate"));
		ruleCtx.put("CutOffDateGroup2", ctx.get("CutOffDateGroup2"));
		ruleCtx.put("CutOffDateGroup3", ctx.get("CutOffDateGroup3"));
		ruleCtx.put("CutOffDateGroup4", ctx.get("CutOffDateGroup4"));
		ruleCtx.put("CutOffDateGroup5", ctx.get("CutOffDateGroup5"));
		ruleCtx.put("CutOffDateGroup6", ctx.get("CutOffDateGroup6"));
		ruleCtx.put("CutOffDateGroup7", ctx.get("CutOffDateGroup7"));
		ruleCtx.put("NumberOfLawyers", ctx.get("NumberOfLawyers"));
		ruleCtx.put("CutOffDateGroup8", ctx.get("CutOffDateGroup8"));
		ruleCtx.put("CutOffDateCCBSupp", ctx.get("CutOffDateCCBSupp"));
		ruleCtx.put("CutOffDate2020", ctx.get("CutOffDate2020"));
		Object objAddress = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"Address.findByKey", ctx);
		if (objAddress != null) {
			Map mapAddress = (Map) objAddress;
			inputMap.put("City", mapAddress.get("City"));
			inputMap.put("CountyDesc", mapAddress.get("CountyDesc"));
			inputMap.put("StateCode", mapAddress.get("StateCode"));

			// This new context is only for the below rule

			ruleCtx.setProject(ctx.getProject());
			ruleCtx.put("StateCode", mapAddress.get("StateCode"));
		}

		// This code will populate Modifiers only for States that allow
		// Modifiers

		Boolean isMofifierState = false;
		Object obj = RuleUtils.executeRule(ruleCtx, "LawyersRule.IsModifierState");
		if (obj != null && obj instanceof Boolean) {
			isMofifierState = (Boolean) obj;
		}

		if (isMofifierState)
			new Modifiers().populateCoverageModifiers(ctx);

		inputMap.put("SchduleRatingModifier1", ctx.get("SchduleRatingModifier1"));
		inputMap.put("SchduleRatingModifier2", ctx.get("SchduleRatingModifier2"));
		inputMap.put("SchduleRatingModifier3", ctx.get("SchduleRatingModifier3"));

		Object objPolicy = SqlResources.getSqlMapProcessor(ctx).findByKey("Policy.findByKey", ctx);
		if (objPolicy != null) {
			Map mapPolicy = (Map) objPolicy;
			inputMap.put("PolicyEffectiveDate", mapPolicy.get("PolicyEffectiveDate"));
			ruleCtx.put("PolicyEffectiveDate", mapPolicy.get("PolicyEffectiveDate"));
		}

		Object objPolicyCoverage = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsCoveragePolicy",ctx);
		
		if (objPolicyCoverage != null) {
			Map mapPolicyCoverage = (Map) objPolicyCoverage;
			inputMap.put("LimitAmount", mapPolicyCoverage.get("LimitAmount"));
			inputMap.put("DeductibleAmount", mapPolicyCoverage.get("DeductibleAmount"));

			inputMap.put("IsClaimOptionType", mapPolicyCoverage.get("IsClaimOptionType"));
			inputMap.put("IsClaimExpensesType", mapPolicyCoverage.get("IsClaimExpensesType"));

			inputMap.put("IsDollarDefense", mapPolicyCoverage.get("IsDollarDefense"));
			inputMap.put("ManualPremium", mapPolicyCoverage.get("ManualPremium"));
		}

		List aoplist = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOPFieldsQuickQuote",ctx);
		
		if (aoplist != null) {
			Map map = new HashMap();
			for (int i = 0; i < aoplist.size(); i++) {
				map = (HashMap) aoplist.get(i);
				inputMap.put("AOP_Percentage_" + map.get("AOPKey"), map.get("PercentageValue"));
			}
		}

		Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey("FirmLW.findByKey", ctx);
		if (objFirm != null) {
			Map mapFirm = (Map) objFirm;
			if (mapFirm.get("NumberOfLawyers") != null) {
				inputMap.put("NumberOfLawyers", mapFirm.get("NumberOfLawyers"));
				ctx.put("NumberOfLawyers", mapFirm.get("NumberOfLawyers"));
			}
		}

		/*Map objFirmYearMap = null;
		int FirmYear = 0;
		Object objFirmYear = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"ProfessionalLiabilityInsDetailLW.findByKey", ctx);
		if (objFirmYear != null && objFirmYear instanceof Map)
			objFirmYearMap = (Map) objFirmYear;

		if (objFirmYearMap != null)
			FirmYear = objFirmYearMap.get("FirmYear") != null ? Integer
					.parseInt(objFirmYearMap.get("FirmYear").toString()) : 0;

		List objClaimAge = SqlResources.getSqlMapProcessor(ctx).select(
				"ClaimAgeLW.findAllByPartialKey", ctx);
		if (objClaimAge != null) {
			Map claimMap = new HashMap();
			for (int i = 0; i < objClaimAge.size(); i++) {
				claimMap = (Map) objClaimAge.get(i);
				int claimAgeLawyers = Integer.parseInt(claimMap.get("Year")
						.toString());

				int ClaimAge = new LawyersUtils().checkNumberAndFirmYear(
						claimAgeLawyers, FirmYear);

				// inputMap.put("ClaimAge_" + (i + 1), claimMap.get("Year"));
				inputMap.put("ClaimAge_" + (i + 1), ClaimAge);

			}
		}*/
		List objList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAllClaimAgeLWList", ctx);

		if (objList != null && objList.size() > 0) {
			for (int i = 0; i < objList.size(); i++) {
				Map objMap = (Map) objList.get(i);
				if("Yes".equals(ctx.get("QQCurrentEffectiveDateFlag"))){
					inputMap.put("ClaimAge_" + (i + 1), 0);
				} else {
					inputMap.put("ClaimAge_" + (i + 1), (Integer.parseInt(objMap.get("Year").toString()) > 5) ? 5 : objMap.get("Year"));
				}
			}
		}
		
		boolean isRatingNew = false;        
		Object objRatingRule = RuleUtils.executeRule(ruleCtx,"LawyersRule.checkNewFiling");
        if (objRatingRule != null && objRatingRule instanceof Boolean) {
        	isRatingNew = (Boolean) objRatingRule;
        }	   
        //Fill FTE for new filing only
		if(isRatingNew){
        	populateFTE(ctx);
        	inputMap.put("FullTimeEquivalent", ctx.get("FullTimeEquivalent"));
		}

	}

	public static void populateOuputResponseQuickQuote(IContext ctx)
			throws Exception {
		Map responseMap = null;
		if (ctx.get("responseMap") != null)
			responseMap = (Map) ctx.get("responseMap");

		if (responseMap != null) {
			// For policy Transaction

			ctx.put("IsMinimumPremium", responseMap.get("IsMinimumPremium"));

			if (responseMap.get("IsMinimumPremium") != null
					&& "Y".equals(responseMap.get("IsMinimumPremium")
							.toString())) {
				ctx.put("TotalPremium", Math.round(responseMap
						.get("ActualFinalBaseLimitPremium")));
				ctx.put("InvoicedPremium", Math.round(responseMap
						.get("FinalBaseLimitPremiumWithTaxRatingEngine")));

				ctx.put("InitialCovPremium", Math.round(responseMap
						.get("ActualFinalBaseLimitPremium")));
				ctx.put("Premium", Math.round(responseMap
						.get("ActualFinalBaseLimitPremium")));
				ctx.put("TotalPremiumFirstRange", Math.round(responseMap
						.get("MinimumPremiumValue")));
			} else {

				ctx.put("TotalPremium", Math.round(responseMap
						.get("ActualFinalBaseLimitPremium")));
				ctx.put("InvoicedPremium", Math.round(responseMap
						.get("FinalBaseLimitPremiumWithTaxRatingEngine")));

				ctx.put("InitialCovPremium", Math.round(responseMap
						.get("FinalBaseLimitPremiumWithTaxRatingEngine")));
				ctx.put("Premium", Math.round(responseMap
						.get("ActualFinalBaseLimitPremium")));
				ctx.put("TotalPremiumFirstRange", Math.round(responseMap
						.get("ActualFinalBaseLimitPremium")));
			}
			ctx.put("MinimumPremiumValue", Math.round(responseMap
					.get("MinimumPremiumValue")));

			// ctx.put("TotalPremiumFirstRange",Math.round(responseMap.get("PolicyPremiumWithTaxWithoutModifier"))
			// );

			// ctx.put("TotalPremiumFirstRange",Math.round(responseMap.get("FinalBaseLimitPremiumWithTax"))
			// );

			/*
			 * if (ctx.get("InvoicedPremium") != null) { int divider = 15; //
			 * Default
			 * 
			 * if (SystemProperties.getInstance().getString(
			 * "appl.LawyersIns.firstrangedividerQQ") != null) divider = new
			 * Integer(SystemProperties.getInstance()
			 * .getString("appl.LawyersIns.firstrangedividerQQ"));
			 * 
			 * double FirstRange = (Double.parseDouble(ctx.get(
			 * "InvoicedPremium").toString()) * divider) / 100; // double
			 * FirstRange = //
			 * Double.parseDouble(ctx.get("InvoicedPremium").toString()) - //
			 * percentDeduct ; if (ctx.get("IsMinimumPremium") != null &&
			 * "Y".equals(ctx.get("IsMinimumPremium").toString())) {
			 * ctx.put("TotalPremiumFirstRange", ctx .get("InvoicedPremium")); }
			 * else { FirstRange = Double.parseDouble(ctx.get("InvoicedPremium")
			 * .toString()) - FirstRange; if (ctx.get("MinimumPremiumValue") !=
			 * null) { double minPreVal = new Double(ctx.get(
			 * "MinimumPremiumValue").toString()); if (FirstRange < minPreVal)
			 * FirstRange = minPreVal; }
			 * 
			 * Object TotalPremiumFirstRange = Math.decimalFormat( FirstRange,
			 * 0); ctx.put("TotalPremiumFirstRange", TotalPremiumFirstRange); } }
			 * else ctx.put("TotalPremiumFirstRange", 0);
			 * 
			 */

			double mTCountyTax = 0.0;
			if (responseMap.get("MTTaxAmmount") != null
					&& !"".equals(responseMap.get("MTTaxAmmount").toString())) {
				mTCountyTax = Double.parseDouble(responseMap
						.get("MTTaxAmmount").toString());
				ctx.put("MTTaxAmmount", Math.round(responseMap
						.get("MTTaxAmmount")));
			}
			if (responseMap.get("CountyTaxAmmount") != null
					&& !"".equals(responseMap.get("CountyTaxAmmount")
							.toString())) {
				mTCountyTax = Double.parseDouble(responseMap.get(
						"CountyTaxAmmount").toString());
				ctx.put("CountyTaxAmmount", Math.round(responseMap
						.get("CountyTaxAmmount")));
			}

			if (responseMap.get("State1TaxAmmount") != null
					&& !"".equals(responseMap.get("State1TaxAmmount")
							.toString())) {
				mTCountyTax = mTCountyTax
						+ Double.parseDouble(responseMap
								.get("State1TaxAmmount").toString());
				ctx.put("State1TaxAmmount", Math.round(responseMap
						.get("State1TaxAmmount")));
			}
			if (responseMap.get("State2TaxAmmount") != null
					&& !"".equals(responseMap.get("State2TaxAmmount")
							.toString())) {
				mTCountyTax = mTCountyTax
						+ Double.parseDouble(responseMap
								.get("State2TaxAmmount").toString());
				ctx.put("State2TaxAmmount", Math.round(responseMap
						.get("State2TaxAmmount")));
			}

			ctx.put("TotalPolicyTaxAmount", Math.round(mTCountyTax));
			ctx.put("TotalCoverageTaxAmount", Math.round(mTCountyTax));

			if (responseMap.get("InputXML") != null)
				ctx.put("XmlInputDatatoRating", responseMap.get("InputXML")
						.toString());
			if (responseMap.get("OutputXML") != null)
				ctx.put("XmlOutputDatafromRating", responseMap.get("OutputXML")
						.toString());
		}

		int totalPMP = 0;
		if (ctx.get("SchduleRatingModifier1") != null
				&& !"".equals(ctx.get("SchduleRatingModifier1").toString()))
			totalPMP = Integer.parseInt((ctx.get("SchduleRatingModifier1")
					.toString()));
		if (ctx.get("SchduleRatingModifier2") != null
				&& !"".equals(ctx.get("SchduleRatingModifier2").toString()))
			totalPMP = totalPMP
					+ Integer.parseInt((ctx.get("SchduleRatingModifier2")
							.toString()));
		if (ctx.get("SchduleRatingModifier3") != null
				&& !"".equals(ctx.get("SchduleRatingModifier3").toString()))
			totalPMP = totalPMP
					+ Integer.parseInt((ctx.get("SchduleRatingModifier3")
							.toString()));

		ctx.put("TotalPolicyModifierPercentage", totalPMP);
		ctx.put("TotalCovModifierPercentage", totalPMP);

	}

	public void updateQuote(IContext ctx) throws Exception {

		List quoteList = null;
		Object obj = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetQuoteList", ctx);

		if (obj != null && obj instanceof List)
			quoteList = (List) obj;

		if (quoteList == null)
			return;

		if (quoteList != null) {
			for (int i = 0; i < quoteList.size(); i++) {
				Map map = (Map) quoteList.get(i);
				Context newctx = new Context();
				newctx.putAll(map);
				newctx.setProject(ctx.getProject());
				newctx.put("PolicyKey", map.get("PolicyKey"));
				newctx.put("TransactionSequence", map
						.get("TransactionSequence"));

				if (ctx.get("IsQuoteSelected" + "_" + i) != null
						&& ("on".equals(ctx.get("IsQuoteSelected" + "_" + i)
								.toString()) || "Y".equals(ctx.get(
								"IsQuoteSelected" + "_" + i).toString()))) {
					newctx.put("IsQuoteSelected", "Y");
				} else if (ctx.get("IsQuoteSelected" + "_" + i) != null
						&& "N".equals(ctx.get("IsQuoteSelected" + "_" + i)
								.toString())) {
					newctx.put("IsQuoteSelected", "Y");
				} else {
					//newctx.put("IsQuoteSelected", null);
					newctx.put("IsThisOptionFinalised", null);
				}
				newctx.put("isShowQuote", map.get("isShowQuote"));
		    	newctx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
		    	newctx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
				Object obj2 = DBUtils.executeDBOperation(newctx, "Quote", "3");

				if (obj2 == null) {
					DBUtils.executeDBOperation(newctx, "Quote", "1");
				} else {
					DBUtils.executeDBOperation(newctx, "Quote", "2");
					DBUtils.executeDBOperation(newctx, "Quote", "1");
					
				}
			}
		}
	}
	
	public static void prepareDataForRatingEngine(IContext ctx) {
		try {
			Context newCtx = new Context();
			newCtx.putAll(ctx);
			logger.debug("Going to Prepare Data For Rating Engine For State " + ctx.get("StateCode"));
			newCtx.put("PolicyKey_rule", ctx.get("PolicyKey").toString());
			newCtx.put("StateCode_rule", ctx.get("StateCode").toString());
			SqlResources.getSqlMapProcessor(newCtx).select("RatingTrace.StateSpecificRatingData_proc", newCtx);
			Object objPolicyCoverage = SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementsaveAccountstmtsgetPolicyListAll",newCtx);
			logger.debug("Data Prepared for PolicyKey - " + ctx.get("PolicyKey"));
			newCtx.clear();
		} catch (Exception e) {
			logger.debug("Exception in Method prepareDataForRatingEngine" + e.getMessage());
			logger.error("Unexpected error", e);
		}
	}
	
	public static void populateInputRequestIndication(IContext ctx) throws Exception {		
		IContext reqCtx = new Context();
		reqCtx.setProject(ctx.getProject());		
		Map inputMap = new HashMap();

		ctx.put("OLDCompanyKey", ctx.get("CompanyKey"));
		
		fetchDataIndication(ctx);
		populateIndicationDataInRequest(ctx);
	}
	
	protected static void fetchDataIndication(IContext ctx) throws Exception {		
		// For County and City
		if (ctx.get("City") != null) {
			String city = ctx.get("City").toString();
			city = city.replace("&", "#AMP#");
			ctx.put("City", city);
		}
		if (ctx.get("CountyDesc") != null) {
			String county = ctx.get("CountyDesc").toString();
			county = county.replace("&", "#AMP#");
			ctx.put("CountyDesc", county);
		}	
		List modifierList =(List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetIndicationModifiersDetails", ctx);
		if(modifierList != null && modifierList instanceof List)
		{
			Map modifierMap=null;
			for(int i=0;i<modifierList.size();i++)
			{
				modifierMap=(Map)modifierList.get(i);
				ctx.put("SchduleRatingModifier"+modifierMap.get("ModifierKey"),modifierMap.get("Percentage"));
			}
			
		}
		populateIndicationClaimAgeData(ctx);
		populateIndicationAOP(ctx);		
	}
	
	public static void populateIndicationClaimAgeData(IContext ctx) throws Exception {

		List objList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAllClaimAgeLWList", ctx);

		if (objList != null && objList.size() > 0) {
			for (int i = 0; i < objList.size(); i++) {
				Map objMap = (Map) objList.get(i);
				ctx.put("ClaimAge_" + (i + 1), objMap.get("Year"));
			}
		}
	}
	
	public static void populateIndicationAOP(IContext ctx) throws Exception {
		List aoplist = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields", ctx);
		
		if (aoplist != null) {
			Map map = new HashMap();
			for (int i = 0; i < aoplist.size(); i++) {
				map = (HashMap) aoplist.get(i);
				if(map.get("PercentageValue") == null || (map.get("PercentageValue") != null && ("".equals(map.get("PercentageValue").toString().trim()) || "0".equals(map.get("PercentageValue").toString().trim()))))
					ctx.put(mapperRatingPercenatge(Integer.parseInt(map.get("AOPKey").toString())), 0);		
				else {
					ctx.put(mapperRatingPercenatge(Integer.parseInt(map.get("AOPKey").toString())), map.get("PercentageValue"));	
				}	
					
			}
		}	
	}
	
	public static String mapperRatingPercenatge(int aopKey) throws Exception {
		String percentage = "DefaultPercentage";
		switch (aopKey) {
        	case 1:	percentage = "TradeRegulationPercentage";
            		break;
        	case 2:	percentage = "ArbitrationPercentage";
    				break;
        	case 3:	percentage = "BankruptcyNewAppPercentage";
    				break;
        	case 4:	percentage = "CivilRightsPercentage";
    				break;
        	case 5:	percentage = "CorporatePercentage"; 
    				break;
        	case 6:	percentage = "CopyrightTrademarkPercentage";
    				break;
        	case 7:	percentage = "DefensePercentage";
    				break;
        	case 8:	percentage = "DomesticRelationsPercentage";
    				break;
        	case 9:	percentage = "ElderLawPercentage";
    				break;
        	case 10:percentage = "EnvironmentalPercentage";
    				break;
    				
        	case 11:percentage = "ErisaPercentage";
    				break;
        	case 12:percentage = "FinancialInstitutionPercentage";
    				break;    				
        	case 13:percentage = "GovernmentPercentage";
					break;
			case 14:percentage = "ImmigrationPercentage";
					break;
			case 15:percentage = "InvestmentCounselingPercentage";
					break;
			case 16:percentage = "NaturalResourcePercentage";
					break;
			case 17:percentage = "PersonalContractsPercentage";
					break;
			case 18:percentage = "CorporateDefensePercentage";
					break;
			case 19:percentage = "PublicUtilitiesPercentage";
					break;		
			case 20:percentage = "RealEstateCommercialPercentage";
					break;
					
			case 21:percentage = "SecuritiesPercentage";
					break;
			case 22:percentage = "TaxPercentage";
					break;
			case 23:percentage = "TitlePercentage";
					break;
			case 24:percentage = "WillsPercentage";
					break;
			case 25:percentage = "OtherPercentage";
					break;	
			//Commented for DB Error 
			/*case 26:percentage = "RealEstateCommercialPercentage";
					break;*/
			case 27:percentage = "RealEstateResidentialPercentage";
					break;
			case 28:percentage = "AdmiraltyPercentage";
					break;
			case 29:percentage = "AppellatePracticePercentage";
					break;					
			case 30:percentage = "AviationPercentage";
					break;
					
			case 31:percentage = "CollectionsPercentage";
					break;		
			case 32:percentage = "CommunicationsPercentage";
					break;			
			case 33:percentage = "ConstructionPercentage";
					break;
			case 34:percentage = "CreditorDebtorRightsPercentage";
					break;
			case 35:percentage = "CriminalDefensePercentage";
					break;
			case 36:percentage = "EducationPercentage";
					break;
			case 37:percentage = "EmploymentPercentage";
					break;
			case 38:percentage = "EntertainmentPercentage";
					break;					
			case 39:percentage = "HealthcarePercentage";
					break;
			/*case 40:percentage = "CreditorDebtorRightsPercentage";
					break;*/
					
			case 41:percentage = "InsuranceDefensePercentage";
					break;
			case 42:percentage = "InternationalPercentage";
					break;
			case 43:percentage = "JuvenilePercentage";
					break;
			case 44:percentage = "LaborManagementPercentage";
					break;						
			case 45:percentage = "LaborUnionPercentage";
					break;
			case 46:percentage = "MergersAcquisitionsPercentage";
					break;
			case 47:percentage = "NonProfitPercentage";
					break;
			case 48:percentage = "PatentPercentage";
					break;
			case 49:percentage = "SocialSecurityPercentage";
					break;
			case 50:percentage = "TrafficDUIPercentage";
					break;
			
			case 51:percentage = "UtilitiesPercentage";
					break;
			case 52:percentage = "WorkersDefensePercentage";
					break;
			case 53:percentage = "WorkersPlaintiffPercentage";
					break;
			case 54:percentage = "AdministrativeorRegulatoryPercentage";
					break;
					
			case 55:percentage = "CivilPlaintiffPercentage";
				break;
			case 56:percentage = "ConstructionPlaintiffPercentage";
				break;
			case 57:percentage = "CorporatePlaintiffPercentage";
				break;
			case 58:percentage = "PlaintiffOtherDefesePercentage";
				break;	
	
			case 59:percentage = "MassTortPlaintiffPercentage";
				break;
			case 60:percentage = "MedicalMalpracticePlaintiffPercentage";
				break;
			case 61:percentage = "NonMedicalMalpracticePlaintiffPercentage";
				break;
			case 62:percentage = "PersonalInjuryPlaintiffPercentage";
				break;	
			
			case 63:percentage = "ProductLiabilityPlaintiffPercentage";
				break;
			case 64:percentage = "CivilDefensePercentage";
				break;
			case 65:percentage = "ConstructionDefensePercentage";
				break;
			case 66:percentage = "CorporateDefensePercentage";
				break;	
				
			case 67:percentage = "PlaintiffOtherPlaintiffPercentage";
				break;
			case 68:percentage = "MassTortDefensePercentage";
				break;
			case 69:percentage = "MedicalMalpracticeDefensePercentage";
				break;
			case 70:percentage = "NonMedicalMalpracticeDefensePercentage";
				break;	
			case 71:percentage = "PersonalInjuryDefensePercentage";
				break;
			case 72:percentage = "ProductLiabilityDefensePercentage";
				break;
		        
            default: break;
            			
        }
		
		return percentage;
	}
	

	
	protected static void fillClaimAgeIndication(IContext ctx, Map inputMap)
			throws Exception {
		
		inputMap.put("ClaimAge_1", ctx.get("ClaimAge_1"));
		inputMap.put("ClaimAge_2", ctx.get("ClaimAge_2"));
		inputMap.put("ClaimAge_3", ctx.get("ClaimAge_3"));
		inputMap.put("ClaimAge_4", ctx.get("ClaimAge_4"));
		inputMap.put("ClaimAge_5", ctx.get("ClaimAge_5"));
		inputMap.put("ClaimAge_6", ctx.get("ClaimAge_6"));
		inputMap.put("ClaimAge_7", ctx.get("ClaimAge_7"));
		inputMap.put("ClaimAge_8", ctx.get("ClaimAge_8"));
		inputMap.put("ClaimAge_9", ctx.get("ClaimAge_9"));
		inputMap.put("ClaimAge_10", ctx.get("ClaimAge_10"));
		
		inputMap.put("ClaimAge_11", ctx.get("ClaimAge_11"));
		inputMap.put("ClaimAge_12", ctx.get("ClaimAge_12"));
		inputMap.put("ClaimAge_13", ctx.get("ClaimAge_13"));
		inputMap.put("ClaimAge_14", ctx.get("ClaimAge_14"));
		inputMap.put("ClaimAge_15", ctx.get("ClaimAge_15"));
		inputMap.put("ClaimAge_16", ctx.get("ClaimAge_16"));		
	}
	
	protected static void fillAOPIndication(IContext ctx, Map inputMap)
			throws Exception {
		inputMap.put("AdmiraltyPercentage",ctx.get("AOP_Percentage_1"));
		inputMap.put("AdministrativeorRegulatoryPercentage",ctx.get("AOP_Percentage_2"));
		inputMap.put("TradeRegulationPercentage",ctx.get("AOP_Percentage_3"));
		inputMap.put("AppellatePracticePercentage",ctx.get("AOP_Percentage_4"));
		inputMap.put("ArbitrationPercentage",ctx.get("AOP_Percentage_5"));
		inputMap.put("AviationPercentage",ctx.get("AOP_Percentage_6"));
		inputMap.put("BankruptcyPercentage",ctx.get("AOP_Percentage_7"));
		inputMap.put("BankruptcyNewAppPercentage",ctx.get("AOP_Percentage_8"));
		inputMap.put("CivilRightsPercentage",ctx.get("AOP_Percentage_9"));
		inputMap.put("CivilDefensePercentage",ctx.get("AOP_Percentage_10"));
		
		inputMap.put("CivilPlaintiffPercentage",ctx.get("AOP_Percentage_11"));
		inputMap.put("CollectionsPercentage",ctx.get("AOP_Percentage_12"));
		inputMap.put("CommunicationsPercentage",ctx.get("AOP_Percentage_13"));
		inputMap.put("ConstructionPercentage",ctx.get("AOP_Percentage_14"));
		inputMap.put("ConstructionDefensePercentage",ctx.get("AOP_Percentage_15"));
		inputMap.put("ConstructionPlaintiffPercentage",ctx.get("AOP_Percentage_16"));
		inputMap.put("CorporatePlaintiffPercentage",ctx.get("AOP_Percentage_17"));
		inputMap.put("CorporateDefensePercentage",ctx.get("AOP_Percentage_18"));
		inputMap.put("CorporatePercentage",ctx.get("AOP_Percentage_19"));
		inputMap.put("CopyrightTrademarkPercentage",ctx.get("AOP_Percentage_20"));
		
		inputMap.put("CreditorDebtorRightsPercentage",ctx.get("AOP_Percentage_21"));
		inputMap.put("CriminalDefensePercentage",ctx.get("AOP_Percentage_22"));
		inputMap.put("DefensePercentage",ctx.get("AOP_Percentage_23"));
		inputMap.put("DomesticRelationsPercentage",ctx.get("AOP_Percentage_24"));
		inputMap.put("EducationPercentage",ctx.get("AOP_Percentage_25"));
		inputMap.put("ElderLawPercentage",ctx.get("AOP_Percentage_26"));
		inputMap.put("EmploymentPercentage",ctx.get("AOP_Percentage_27"));
		inputMap.put("EntertainmentPercentage",ctx.get("AOP_Percentage_28"));
		inputMap.put("EnvironmentalPercentage",ctx.get("AOP_Percentage_29"));
		inputMap.put("ErisaPercentage",ctx.get("AOP_Percentage_30"));
		
		inputMap.put("FinancialInstitutionPercentage",ctx.get("AOP_Percentage_31"));
		inputMap.put("GovernmentPercentage",ctx.get("AOP_Percentage_32"));
		inputMap.put("HealthcarePercentage",ctx.get("AOP_Percentage_33"));
		inputMap.put("ImmigrationPercentage",ctx.get("AOP_Percentage_34"));
		inputMap.put("InsuranceDefensePercentage",ctx.get("AOP_Percentage_35"));
		inputMap.put("InternationalPercentage",ctx.get("AOP_Percentage_36"));
		inputMap.put("InvestmentCounselingPercentage",ctx.get("AOP_Percentage_37"));
		inputMap.put("JuvenilePercentage",ctx.get("AOP_Percentage_38"));
		inputMap.put("LaborManagementPercentage",ctx.get("AOP_Percentage_39"));
		inputMap.put("LaborUnionPercentage",ctx.get("AOP_Percentage_40"));
		
		inputMap.put("MassTortDefensePercentage",ctx.get("AOP_Percentage_41"));
		inputMap.put("MassTortPlaintiffPercentage",ctx.get("AOP_Percentage_42"));
		inputMap.put("MedicalMalpracticeDefensePercentage",ctx.get("AOP_Percentage_43"));
		inputMap.put("MedicalMalpracticePlaintiffPercentage",ctx.get("AOP_Percentage_44"));
		inputMap.put("MergersAcquisitionsPercentage",ctx.get("AOP_Percentage_45"));
		inputMap.put("NaturalResourcePercentage",ctx.get("AOP_Percentage_46"));
		inputMap.put("NonProfitPercentage",ctx.get("AOP_Percentage_47"));
		inputMap.put("NonMedicalMalpracticeDefensePercentage",ctx.get("AOP_Percentage_48"));
		inputMap.put("NonMedicalMalpracticePlaintiffPercentage",ctx.get("AOP_Percentage_49"));
		inputMap.put("PatentPercentage",ctx.get("AOP_Percentage_50"));
		
		inputMap.put("PersonalContractsPercentage",ctx.get("AOP_Percentage_51"));
		inputMap.put("PersonalInjuryDefensePercentage",ctx.get("AOP_Percentage_52"));
		inputMap.put("PersonalInjuryPlaintiffPercentage",ctx.get("AOP_Percentage_53"));
		inputMap.put("PlaintiffOtherPercentage",ctx.get("AOP_Percentage_54"));
		inputMap.put("PlaintiffPercentage",ctx.get("AOP_Percentage_55"));
		inputMap.put("PlaintiffOtherDefesePercentage",ctx.get("AOP_Percentage_56"));
		inputMap.put("PlaintiffOtherPlaintiffPercentage",ctx.get("AOP_Percentage_57"));
		inputMap.put("ProductLiabilityDefensePercentage",ctx.get("AOP_Percentage_58"));
		inputMap.put("ProductLiabilityPlaintiffPercentage",ctx.get("AOP_Percentage_59"));
		inputMap.put("PublicUtilitiesPercentage",ctx.get("AOP_Percentage_60"));
		
		inputMap.put("RealEstateResidentialPercentage",ctx.get("AOP_Percentage_61"));
		inputMap.put("RealEstateCommercialPercentage",ctx.get("AOP_Percentage_62"));
		inputMap.put("SecuritiesPercentage",ctx.get("AOP_Percentage_63"));
		inputMap.put("SocialSecurityPercentage",ctx.get("AOP_Percentage_64"));
		inputMap.put("TaxPercentage",ctx.get("AOP_Percentage_65"));
		inputMap.put("TitlePercentage",ctx.get("AOP_Percentage_66"));
		inputMap.put("TrafficDUIPercentage",ctx.get("AOP_Percentage_67"));
		inputMap.put("UtilitiesPercentage",ctx.get("AOP_Percentage_68"));
		inputMap.put("WillsPercentage",ctx.get("AOP_Percentage_69"));
		inputMap.put("WorkersDefensePercentage",ctx.get("AOP_Percentage_70"));
		
		inputMap.put("WorkersPlaintiffPercentage",ctx.get("AOP_Percentage_71"));
		inputMap.put("OtherPercentage",ctx.get("AOP_Percentage_72"));
	}

	protected static void populateIndicationDataInRequest(IContext ctx) throws Exception {
		boolean isRatingNew = false;        
		Object objRatingRule = RuleUtils.executeRule(ctx,"LawyersRule.checkNewFiling");
        if (objRatingRule != null && objRatingRule instanceof Boolean) {
        	isRatingNew = (Boolean) objRatingRule;
        }	   
        //Fill FTE for new filing only
		if(isRatingNew)
        	populateFTE(ctx);	
		

		ctx.put("CompanyKey", "1");
		String requestxml = new RequestProcessor().generateRequestXml((Context) ctx);
		//System.out.println(requestxml);
		ctx.put("requestxml", requestxml);
	}
	
	public static void populateFTE(IContext ctx) throws Exception {
		int noFTLawyer = 0;
		int no500Lawyers = 0;
		int no1000Lawyers = 0;
		double fteFactor = 0.0;
		
		if(ctx.get("NumberOfLawyers") != null && !"".equals(ctx.get("NumberOfLawyers"))){
			noFTLawyer = Integer.parseInt(ctx.get("NumberOfLawyers").toString());
			fteFactor = LawyersConstants.FACTRORFULL * noFTLawyer;
		}
		if(ctx.get("NumberOfLawyers500") != null && !"".equals(ctx.get("NumberOfLawyers500"))){
			no500Lawyers = Integer.parseInt(ctx.get("NumberOfLawyers500").toString());
			fteFactor = fteFactor + (LawyersConstants.FACTROR500 * no500Lawyers);
		}
		if(ctx.get("NumberOfLawyers1000") != null && !"".equals(ctx.get("NumberOfLawyers1000"))){
			no1000Lawyers = Integer.parseInt(ctx.get("NumberOfLawyers1000").toString());
			fteFactor = fteFactor + (LawyersConstants.FACTROR1000 * no1000Lawyers);
		}
		
		ctx.put("FullTimeEquivalent", LawyersUtils.roundCeiling(fteFactor, 1));		
	}
	
	public static void populateOuputResponseIndication(IContext ctx)
			throws Exception {
		Map responseMap = null;
		if (ctx.get("responseMap") != null)
			responseMap = (Map) ctx.get("responseMap");		
		
		if (responseMap != null) {

			double mTCountyTax = 0.0;
						
			if (responseMap.get("MTTaxAmmount") != null
					&& !"".equals(responseMap.get("MTTaxAmmount")
							.toString())) {
				mTCountyTax = Double.parseDouble(responseMap.get(
						"MTTaxAmmount").toString());
				ctx.put("MTTaxAmmount", Math.round(responseMap
						.get("MTTaxAmmount")));
			}
			if (responseMap.get("CountyTaxAmmount") != null
					&& !"".equals(responseMap.get("CountyTaxAmmount")
							.toString())) {
				//if (mTCountyTax <= 0) {
					mTCountyTax = mTCountyTax
							+ Double.parseDouble(responseMap.get(
									"CountyTaxAmmount").toString());
					ctx.put("CountyTaxAmmount", Math.round(responseMap
							.get("CountyTaxAmmount")));
				//}
			}

			if (responseMap.get("State1TaxAmmount") != null
					&& !"".equals(responseMap.get("State1TaxAmmount")
							.toString())) {
				mTCountyTax = mTCountyTax
						+ Double.parseDouble(responseMap
								.get("State1TaxAmmount").toString());
				ctx.put("State1TaxAmmount", Math.round(responseMap
						.get("State1TaxAmmount")));
			}
			if (responseMap.get("State2TaxAmmount") != null
					&& !"".equals(responseMap.get("State2TaxAmmount")
							.toString())) {
				mTCountyTax = mTCountyTax
						+ Double.parseDouble(responseMap
								.get("State2TaxAmmount").toString());
				ctx.put("State2TaxAmmount", Math.round(responseMap
						.get("State2TaxAmmount")));
			}

			ctx.put("TotalPolicyTaxAmount", Math.round(mTCountyTax));
			ctx.put("TotalCoverageTaxAmount", Math.round(mTCountyTax));
			
			// For policy Transaction
			ctx.put("TotalPremium", responseMap
					.get("ActualFinalBaseLimitPremium"));
			ctx.put("InvoicedPremium", responseMap
					.get("FinalBaseLimitPremiumWithTaxRatingEngine"));

			// For policy Coverage
			ctx.put("InitialCovPremium", responseMap
					.get("ActualFinalBaseLimitPremium"));
			ctx.put("Premium", responseMap
					.get("FinalBaseLimitPremiumWithTaxRatingEngine"));

			ctx.put("IsMinimumPremium", responseMap.get("IsMinimumPremium"));
			ctx.put("MinimumPremiumValue", responseMap.get("MinimumPremiumValue"));

			

			if (responseMap.get("InputXML") != null)
				ctx.put("XmlInputDatatoRating", responseMap.get("InputXML")
						.toString());
			if (responseMap.get("OutputXML") != null)
				ctx.put("XmlOutputDatafromRating", responseMap.get("OutputXML")
						.toString());
		}

		int totalPMP = 0;
		if (ctx.get("SchduleRatingModifier1") != null
				&& !"".equals(ctx.get("SchduleRatingModifier1").toString()))
			totalPMP = Integer.parseInt((ctx.get("SchduleRatingModifier1")
					.toString()));

		ctx.put("TotalPolicyModifierPercentage", totalPMP);
		ctx.put("TotalCovModifierPercentage", totalPMP);
		ctx.put("CompanyKey", ctx.get("OLDCompanyKey"));
	}

	
	public static void dataPreprationForEndorsementRating(Context ctx)
	{
		try
		{
			logger.debug("Going to preparing data for rating engine for endorsement ");
			Object objRatingRule = RuleUtils.executeRule(ctx,"LawyersRule.AssignClaimExpensesAndDollarDefense");
			
			String IsClaimExpenseType = ctx.get("IsClaimExpenseType") != null ? ctx.get("IsClaimExpenseType").toString() : "N";
			String IsDollarDefense = ctx.get("IsDollarDefense") != null ? ctx.get("IsDollarDefense").toString() : "N";
			
	        
			
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
			
		}
	}

	public static void populateEndorsementInputRequestData(Context ctx) throws Exception {
		Map inputMap = new HashMap();
		ctx.put("OLDCompanyKey", ctx.get("CompanyKey"));
		fetchDataForEndorsementInputRequest(ctx, inputMap);
		populateDataInRequest((IContext)ctx, inputMap);
	}
	public static void fetchDataForEndorsementInputRequest(Context ctx, Map inputMap)
	{
		try
		{
			int endorsementType=ctx.get("EndorsementType")!=null &&!ctx.get("EndorsementType").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("EndorsementType").toString()):0;
			Context ruleCtx = new Context();
			Object objAddress = SqlResources.getSqlMapProcessor(ctx).findByKey("Address.findByKey", ctx);
			if (objAddress != null) 
			{
				Map mapAddress = (Map) objAddress;
				inputMap.put("City", mapAddress.get("City"));
				inputMap.put("CountyDesc", mapAddress.get("CountyDesc"));
				inputMap.put("StateCode", mapAddress.get("StateCode"));
				// This new context is only for the below rule
				ruleCtx.setProject(ctx.getProject());
				ruleCtx.put("StateCode", mapAddress.get("StateCode"));
			}

			// This code will populate Modifiers only for States that allow
			// Modifiers

			Boolean isMofifierState = false;
			Object obj = RuleUtils.executeRule(ruleCtx,"LawyersRule.IsModifierState");
			if (obj != null && obj instanceof Boolean) 
			{
				isMofifierState = (Boolean) obj;
			}

			if (isMofifierState)
				new Modifiers().populateCoverageModifiers(ctx);

			inputMap.put("SchduleRatingModifier1", ctx.get("SchduleRatingModifier1"));
			inputMap.put("SchduleRatingModifier2", ctx.get("SchduleRatingModifier2"));
			inputMap.put("SchduleRatingModifier3", ctx.get("SchduleRatingModifier3"));

			// fetch policy effective date
			Object objPolicy = SqlResources.getSqlMapProcessor(ctx).findByKey("Policy.findByKey", ctx);
			if (objPolicy != null) {
				Map mapPolicy = (Map) objPolicy;
				inputMap.put("PolicyEffectiveDate", mapPolicy.get("PolicyEffectiveDate"));
			}

			
			// fetch PolicyCoverage data and put in request
			if(endorsementType==9)
			{
				Object objPolicyCoverage = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsgetDeductiblesDataForEndorsement",ctx);
				
				if (objPolicyCoverage != null) 
				{
					Map mapPolicyCoverage = (Map) objPolicyCoverage;					
					inputMap.put("DeductibleAmount", mapPolicyCoverage.get("DeductibleAmount"));
					inputMap.put("IsClaimOptionType", mapPolicyCoverage.get("IsClaimOptionType"));
					inputMap.put("IsDollarDefense",  mapPolicyCoverage.get("IsDollarDefense"));					
				}
				inputMap.put("LimitAmount", ctx.get("LimitAmount"));
				inputMap.put("IsClaimExpensesType", ctx.get("IsClaimExpenseType"));
				inputMap.put("ManualPremium", "N");
				
				if (((ctx.get("IsNewFiling") != null && "Y".equals(ctx.get("IsNewFiling").toString()))) ||
				((ctx.get("IsNewFiling2020") != null && "Y".equals(ctx.get("IsNewFiling2020").toString())))){
					inputMap.put("IsDollarDefense",  ctx.get("IsDollarDefense"));
				}				
			}
			else if(endorsementType==10)
			{
				Object objPolicyCoverage = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsSetCoverageData",ctx);
	
				if (objPolicyCoverage != null) {
					Map mapPolicyCoverage = (Map) objPolicyCoverage;
					inputMap.put("LimitAmount", mapPolicyCoverage.get("LimitAmount"));
					inputMap.put("IsClaimExpensesType", mapPolicyCoverage.get("IsClaimExpensesType"));
					inputMap.put("IsDollarDefense", mapPolicyCoverage.get("IsDollarDefense"));
				}
				inputMap.put("IsClaimOptionType", ctx.get("IsClaimOptionType1"));
				inputMap.put("DeductibleAmount", ctx.get("DeductibleAmount"));
				
			}
			else if(endorsementType==13)
			{
				Object objPolicyCoverage = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsSetCoverageData",ctx);
	
				if (objPolicyCoverage != null) {
					Map mapPolicyCoverage = (Map) objPolicyCoverage;
					inputMap.put("LimitAmount", mapPolicyCoverage.get("LimitAmount"));
					inputMap.put("IsClaimExpensesType", mapPolicyCoverage.get("IsClaimExpensesType"));
					inputMap.put("IsDollarDefense", mapPolicyCoverage.get("IsDollarDefense"));
					inputMap.put("ManualPremium", mapPolicyCoverage.get("ManualPremium"));
					inputMap.put("IsClaimOptionType", mapPolicyCoverage.get("IsClaimOptionType"));
				}
				inputMap.put("DeductibleAmount", ctx.get("DeductibleAmount"));
				
			}
			else
			{
				Object objPolicyCoverage = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsCoveragePolicy",ctx);
	
				if (objPolicyCoverage != null) {
					Map mapPolicyCoverage = (Map) objPolicyCoverage;
					inputMap.put("LimitAmount", mapPolicyCoverage.get("LimitAmount"));
					inputMap.put("DeductibleAmount", mapPolicyCoverage.get("DeductibleAmount"));
					inputMap.put("IsClaimOptionType", mapPolicyCoverage.get("IsClaimOptionType"));
					inputMap.put("IsClaimExpensesType", mapPolicyCoverage.get("IsClaimExpensesType"));
					inputMap.put("IsDollarDefense", mapPolicyCoverage.get("IsDollarDefense"));
					inputMap.put("ManualPremium", mapPolicyCoverage.get("ManualPremium"));
	
				}
			}
			// fetch AOP data
			List aoplist = SqlResources.getSqlMapProcessor(ctx).select(	"SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields", ctx);
			if (aoplist != null) {
				Map map = new HashMap();
				for (int i = 0; i < aoplist.size(); i++) {
					map = (HashMap) aoplist.get(i);
					inputMap.put("AOP_Percentage_" + map.get("AOPKey"), map.get("PercentageValue"));
				}
			}

			// fetch plaintiff data if plaintiff AOP is filled
			/*
			 * We will be calculating Plaintiff and plaintiff others data depending
			 * upon the data in Plaintiff Supplement
			 */

			// check if Plaintiff AOP is taken in the application
			if (inputMap.get("AOP_Percentage_18") != null && !"".equals(inputMap.get("AOP_Percentage_18").toString())) {

				List aopPlaintiffList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOLFields",ctx);
				LawyersValidationUtils objLawUtl = new LawyersValidationUtils();
				if (aopPlaintiffList != null) {
					Map map = new HashMap();
					for (int i = 0; i < aopPlaintiffList.size(); i++) {
						map = (HashMap) aopPlaintiffList.get(i);
						inputMap.put("AOL_PercentageOfRevenue_" + map.get("AOLKey"),LawyersValidationUtils.convertStringToInteger(map.get("PercentageOfRevenue") != null ? map.get("PercentageOfRevenue").toString() : ""));
						inputMap.put("AOL_PercentageOfDefense_" + map.get("AOLKey"),LawyersValidationUtils.convertStringToInteger(map.get("PercentageOfDefense") != null ? map.get("PercentageOfDefense").toString() : ""));
					}
					String plaintiffPercentage = objLawUtl.getPlaintiffPercentage(inputMap);
					String plaintiffOtherPercentage = objLawUtl.getPlaintiffOtherPercentage(inputMap);

					inputMap.put("PlaintiffPercentage",plaintiffPercentage);
					inputMap.put("PlaintiffOtherPercentage",plaintiffOtherPercentage);

				}
			}

			// fetch Real Estate Data If real Estate Aop is filled

			/*
			 * Now RealEstateResidential and RealEstateCommercial percentage will be
			 * captured from the application on AOP page. Earlier it has to be
			 * calculated by using the data in its supplement pages.
			 * 
			 * 
			 * if (inputMap.get("AOP_Percentage_20") != null &&
			 * !"".equals(inputMap.get("AOP_Percentage_20").toString())) {
			 * 
			 * List aoprelist = SqlResources .getSqlMapProcessor(ctx) .select(
			 * "SqlStmts.UserStatementsaveAccountstmtspopulateAOPREFields", ctx); if
			 * (aoprelist != null) { Map map = new HashMap(); for (int i = 0; i <
			 * aoprelist.size(); i++) { map = (HashMap) aoprelist.get(i);
			 * inputMap.put("AOPRE_Percentage_" + map.get("AOPREKey"), map
			 * .get("PercentageValue")); }
			 * 
			 * 
			 * 
			 * LawyersValidationUtils objLawUtl = new LawyersValidationUtils();
			 * 
			 * String RealEstateResidentialPercentage = objLawUtl
			 * .getRealEstateResidentialPercentage(inputMap); String
			 * RealEstateCommercialPercentage = objLawUtl
			 * .getRealEstateCommercialPercentage(inputMap);
			 * 
			 * inputMap.put("RealEstateResidentialPercentage",
			 * RealEstateResidentialPercentage);
			 * inputMap.put("RealEstateCommercialPercentage",
			 * RealEstateCommercialPercentage);
			 * 
			 *  } }
			 */

			// fetch FirmLW data
			Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey("FirmLW.findByKey", ctx);
			if (objFirm != null) {
				Map mapFirm = (Map) objFirm;
				if (mapFirm.get("NumberOfLawyers") != null) {
					inputMap.put("NumberOfLawyers", mapFirm.get("NumberOfLawyers"));
					
				}
				else {
					List attList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtsgetAttorneysList",ctx);
					int num = 0;
					if (attList != null && attList.size()>0)
					{
					Map attryMap = new HashMap();
					for (int i = 0; i < attList.size(); i++)
					{
						attryMap = (Map) attList.get(i);
						if(attryMap.get("AnnualWorkedHours") != null){
							int annualWorkedHours = Integer.parseInt(attryMap.get("AnnualWorkedHours").toString());
							if(annualWorkedHours > 1000){
								num++;
								}
						}
					}	
				}
					inputMap.put("NumberOfLawyers", String.valueOf(num));
				}
					
				
				inputMap.put("IsTaxCalculation",mapFirm.get("IsTaxCalculation") != null ? mapFirm.get("IsTaxCalculation") : "");

			}
//			if(endorsementType==14 || endorsementType==15)
//			{
//				List attList = (List)ctx.get("AttorneysDetailsList_list_01");
//				if (attList != null && attList.size() > 0) {
//					String num = String.valueOf(attList.size());
//					inputMap.put("NumberOfLawyers", num);
//				}
//				
//			}
//			else
//			{
//				List attList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.quoteOptionviewgetAttorneysList", ctx);
//				if (attList != null && attList.size() > 0) {
//					String num = String.valueOf(attList.size());
//					inputMap.put("NumberOfLawyers", num);
//				}	
//			}
			
			int endorsementKey=ctx.get("EndorsementType")!=null && !ctx.get("EndorsementType").equals(HtmlConstants.EMPTY) ?Integer.parseInt(ctx.get("EndorsementType").toString()):0;
			if(endorsementKey==12 || endorsementKey==14 || endorsementKey==15)
			{
				List attList = (List)ctx.get("AttorneysDetailsList_list_01");
				/*if (attList != null && attList.size() > 0) {
					String num = String.valueOf(attList.size());
					inputMap.put("NumberOfLawyers", num);
				}*/
				
				List objClaimAge = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetClaimAgeLWEndorsement", ctx);
								   
				/*if (objClaimAge != null)
				{
					
					Map claimMap = new HashMap(); 
					for (int i = 0; i < objClaimAge.size(); i++) 
					{
						claimMap = (Map) objClaimAge.get(i);
						inputMap.put("ClaimAge_" + (i + 1), claimMap.get("Year"));

					}
				}*/
				int num = 0;
				if (attList != null) {
					Map attryMap = new HashMap();
					if(attList.size() == 1){
						num = 1;
						attryMap = (Map) attList.get(0);
						if(attryMap.get("AnnualWorkedHours") != null){
							if (objClaimAge != null && !objClaimAge.isEmpty()) {
								Map claimMap = new HashMap();
								claimMap = (Map) objClaimAge.get(0);
								inputMap.put("ClaimAge_" + (num), claimMap.get("Year"));
							}
						}
						inputMap.put("NumberOfLawyers", String.valueOf(num));
					} else {
						for (int i = 0; i < attList.size(); i++) {
							attryMap = (Map) attList.get(i);
							if(attryMap.get("AnnualWorkedHours") != null){
								int annualWorkedHours = Integer.parseInt(attryMap.get("AnnualWorkedHours").toString());
								if(annualWorkedHours > 1000){
									num++;
									if (objClaimAge != null && i < objClaimAge.size()) {
										Map claimMap = new HashMap();
										claimMap = (Map) objClaimAge.get(i);
										inputMap.put("ClaimAge_" + (num), claimMap.get("Year"));
									}
								}
							}
						}			
						inputMap.put("NumberOfLawyers", String.valueOf(num));
					}
				}
				
				
			}
			else
			{
				// fetch Claim AGe data
				List objClaimAge = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAllClaimAgeLWList", ctx);
				if (objClaimAge != null)
				{
					
					Map claimMap = new HashMap();
					for (int i = 0; i < objClaimAge.size(); i++) 
					{
						claimMap = (Map) objClaimAge.get(i);
						inputMap.put("ClaimAge_" + (i + 1), claimMap.get("Year"));

					}
				}
				
			}
			processFTEEndorsement(ctx,endorsementKey);
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
	}
public static void processFTEEndorsement(IContext ctx,int endorsementKey) throws Exception { 	
	List attorneyList=null;
		boolean isRatingNew = false;        
		Object objRatingRule = RuleUtils.executeRule(ctx,"LawyersRule.checkNewFiling");
        if (objRatingRule != null && objRatingRule instanceof Boolean) {
        	isRatingNew = (Boolean) objRatingRule;
        }	   
        //Fill FTE for new filing only
		if(isRatingNew){
	       /* Object obj = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAllAttorneyList", ctx);
			 if(obj == null)
				 return;*/
			if(endorsementKey==14 || endorsementKey==15)
			{
			 attorneyList = (List) ctx.get("AttorneysDetailsList_list_01");	
			 if(attorneyList == null)
				 return;
			}
			 else
			{
				 attorneyList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAllAttorneyList", ctx);
				 if(attorneyList == null)
					 return;
				 }
				
			Map  mapAttorney = null;
			int noFTLawyer = 0;
			int no500Lawyers = 0;
			int no1000Lawyers = 0;
			int number = 0;
			//Single attorney will be treated as full time
			if(attorneyList.size() == 1){
				noFTLawyer = 1;
			} else {
				for(int i = 0; i < attorneyList.size(); i++){
					mapAttorney = (Map) attorneyList.get(i);
					number = Integer.parseInt(mapAttorney.get("AnnualWorkedHours").toString());
					if(number > 1000)
						noFTLawyer++;
					else if(number > 500 && number <= 1000)
						no1000Lawyers++;
					else if(number <= 500 && number > 0)
						no500Lawyers++;
				}
			}
			ctx.put("NumberOfLawyers", noFTLawyer);
			ctx.put("NumberOfLawyers1000", no1000Lawyers);
			ctx.put("NumberOfLawyers500", no500Lawyers);
			
			populateFTE(ctx);
			ctx.put("isNewFiling", "Y");
		} else {
			ctx.put("isNewFiling", "N");
		}
		
	}
	

}
