package com.userbo;

import com.util.InetLogger;

import java.util.List;
import java.util.Map;

import org.jdom.Element;

import com.manage.managecomponent.components.WebservicecallImpl;
import com.manage.managemetadata.functions.commonfunctions.Math;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.processor.RequestProcessor;
import com.util.Context;
import com.util.IContext;
import com.util.SystemProperties;

public class RatingBatch {
	private static final InetLogger logger = InetLogger.getInetLogger(RatingBatch.class);
	
	public static void callingRatingEngine(Map quoteMap) throws Exception {
		WebservicecallImpl webSer = null;
		IContext newCtx = new Context();

		newCtx.setProject("LawyersIns");
		newCtx.put("PolicyKey", quoteMap.get("PolicyKey"));
		newCtx.put("VersionSequence", quoteMap.get("VersionSequence"));
		newCtx.put("TransactionSequence", quoteMap.get("TransactionSequence"));
		newCtx.put("PolicyEffectiveDate", quoteMap.get("PolicyEffectiveDate"));
		newCtx.put("CompanyKey", 1);
		newCtx.put("LOBKey", 2);
		newCtx.put("LookupStateCode", "ALL");
		newCtx.put("ERPYear", 0);	
		newCtx.put("CutOffDate", LawyersConstants.CUT_OFF_DATE);
		newCtx.put("CutOffDateGroup2", LawyersConstants.CUT_OFF_DATE_GROUP2);
		newCtx.put("NewAppFlowCutOffDate", LawyersConstants.NEWAPPFLOW_CUT_OFF_DATE);
		newCtx.put("CutOffDateGroup3", LawyersConstants.CUT_OFF_DATE_GROUP3);
		newCtx.put("CutOffDateGroup4", LawyersConstants.CUT_OFF_DATE_GROUP4);
		newCtx.put("CutOffDateGroup5", LawyersConstants.CUT_OFF_DATE_GROUP5);
		newCtx.put("CutOffDateGroup6", LawyersConstants.CUT_OFF_DATE_GROUP6);
		newCtx.put("CutOffDateGroup7", LawyersConstants.CUT_OFF_DATE_GROUP7);
		newCtx.put("CutOffDateGroup8", LawyersConstants.CUT_OFF_DATE_GROUP8);
		newCtx.put("CutOffDateCCBSupp", LawyersConstants.CUT_OFF_DATE_CCBSupp);
		newCtx.put("CutOffDate2020", LawyersConstants.CUT_OFF_DATE_2020);
		/*newCtx.put("FullTimeEquivalent", ctx.get("FullTimeEquivalent"));		
		
		
		newCtx.put("AddressKey", ctx.get("AddressKey"));
		newCtx.put("StateCode", quoteMap.get("StateCode"));
		newCtx.put("AddressTypeKey", ctx.get("AddressTypeKey"));*/;;
		//newCtx.putAll(quoteMap);
		Context newCtx1 = new Context();

		newCtx1.setProject("LawyersIns");
		newCtx1.putAll(quoteMap);
		new LawyersUtils().calculateClaimAgeRequote(newCtx1);
		populateDataInRequest(newCtx, quoteMap);

		webSer = new WebservicecallImpl();
		webSer.setInput("LawyersInfo");
		webSer.setOutput("PremiumInfoLW");
		webSer.setName("calculatePremiumForLawyers");

		String url = null;
		try {
		
			url = SystemProperties.getInstance().getString("appl.LawyersIns.webserviceurl");
		} 
		catch (Exception e) {

		}

		if (url == null)
			return;

		webSer.setServiceurl(url);
		newCtx.put("responseMap", webSer.execute(newCtx));

		Element outElementError = new LawyersValidationUtils().checkIfErrorOutput(newCtx.get("responseMap"));

		if (outElementError != null) {

			List responseErrorList = new LawyersValidationUtils().getErrorListFromResponse(outElementError);
			StringBuffer buffer = new LawyersValidationUtils().addListToInetErrorList(responseErrorList);
			//LawyersUtils.populateError(ctx, "isQuoteLetterSentView", buffer != null ? buffer.toString() : null);
			//newCtx.put("Validation", buffer.toString());
			quoteMap.put("Validation", buffer.toString());
		} else {
			
			populateOuputResponse(newCtx);	
			
			logger.debug("Rating request and response populated");
			quoteMap.put("Premium", newCtx.get("Premium"));
			quoteMap.put("RatingInput", newCtx.get("XmlInputDatatoRating"));
			quoteMap.put("RatingOutput", newCtx.get("XmlOutputDatafromRating"));
			
		}
	}
	
	protected static void populateDataInRequest(IContext ctx, Map inputMap) throws Exception {	
		
		//Common Data
		ctx.put("PolicyEffectiveDate", inputMap.get("PolicyEffectiveDate"));
		ctx.put("LimitAmount", inputMap.get("LimitAmount"));
		ctx.put("DeductibleAmount", inputMap.get("DeductibleAmount"));
		ctx.put("IsClaimOptionType", inputMap.get("IsClaimOptionType"));
		ctx.put("IsClaimExpensesType", inputMap.get("IsClaimExpensesType"));
		ctx.put("NumberOfLawyers", inputMap.get("NumberOfLawyers"));
		ctx.put("StateCode", inputMap.get("StateCode"));
		
		ctx.put("NumberOfLawyers1000", inputMap.get("NumberOfLawyers1000"));
		ctx.put("NumberOfLawyers500", inputMap.get("NumberOfLawyers500"));
		ctx.put("LimitSequence",inputMap.get("LimitSequence"));
		ctx.put("IsDollarDefense", inputMap.get("IsDollarDefense"));
		RuleUtils.executeRule(ctx,"LawyersRule.AssignClaimExpensesAndDollarDefense");
		
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

		
		int mods=0;
		if(inputMap.get("Modifier")!=null && !"".equals(inputMap.get("Modifier").toString())) 
			mods=Integer.parseInt(inputMap.get("Modifier").toString());
		if(mods<-5)
			mods=-5;
		ctx.put("SchduleRatingModifier1", mods);
		ctx.put("ManualPremium",inputMap.get("ManualPremium") != null ? inputMap.get("ManualPremium") : null);
		
		//Modifier Dataint 
		
		/*ctx.put("SchduleRatingModifier2", inputMap.get("SchduleRatingModifier2"));		
		int schduleRatingModifier3=inputMap.get("SchduleRatingModifier3")!=null && !inputMap.get("SchduleRatingModifier3").equals(HtmlConstants.EMPTY)? Integer.parseInt(inputMap.get("SchduleRatingModifier3").toString()):0;
		int schduleRatingModifier4=inputMap.get("SchduleRatingModifier4")!=null && !inputMap.get("SchduleRatingModifier4").equals(HtmlConstants.EMPTY)? Integer.parseInt(inputMap.get("SchduleRatingModifier4").toString()):0;
		int schduleRatingModifier5=inputMap.get("SchduleRatingModifier5")!=null && !inputMap.get("SchduleRatingModifier5").equals(HtmlConstants.EMPTY)? Integer.parseInt(inputMap.get("SchduleRatingModifier5").toString()):0;
		int schduleRatingModifier6=inputMap.get("SchduleRatingModifier6")!=null && !inputMap.get("SchduleRatingModifier6").equals(HtmlConstants.EMPTY)? Integer.parseInt(inputMap.get("SchduleRatingModifier6").toString()):0;
		ctx.put("SchduleRatingModifier3",schduleRatingModifier3+schduleRatingModifier4+schduleRatingModifier5+schduleRatingModifier6 );
		*/
		//AOP Data //18, 26 missing AOP
		//17, 23, 60 as per Pat sheet
		ctx.put("AdministrativeorRegulatoryPercentage", inputMap.get("Administrative_Regulatory"));
		ctx.put("AdmiraltyPercentage", inputMap.get("Admiralty_Maritime"));
		ctx.put("ArbitrationPercentage", inputMap.get("Alternative_Dispute_Resolution"));
		ctx.put("AppellatePracticePercentage", inputMap.get("Appellate_Practice"));
		ctx.put("TradeRegulationPercentage", inputMap.get("Antitrust"));
		ctx.put("AviationPercentage", inputMap.get("Aviation"));
		
		ctx.put("BankruptcyPercentage",inputMap.get("Bankruptcy"));
		
		ctx.put("CivilDefensePercentage", inputMap.get("Civil_Litigation_Defense"));
		ctx.put("CivilPlaintiffPercentage", inputMap.get("Civil_Litigation_Plaintiff"));
		ctx.put("CivilRightsPercentage", inputMap.get("Civil_Rights_Discrimination"));
		
		ctx.put("CollectionsPercentage",inputMap.get("Collections_Repossession"));
		ctx.put("CommunicationsPercentage", inputMap.get("Communications_Media"));
		ctx.put("ConstructionPercentage", inputMap.get("Construction"));
		
		ctx.put("ConstructionDefensePercentage", inputMap.get("Construction_Litigation_Defense"));
		ctx.put("ConstructionPlaintiffPercentage", inputMap.get("Construction_Litigation_Plaintiff"));
		ctx.put("CopyrightTrademarkPercentage", inputMap.get("Copyright_Trademark"));
		ctx.put("CorporatePercentage", inputMap.get("Corporate_Commercial_Business"));
		ctx.put("CorporateDefensePercentage", inputMap.get("Corporate_Commercial_Litigation_Defense"));
		ctx.put("CorporatePlaintiffPercentage", inputMap.get("Corporate_Commercial_Litigation_ Plaintiff"));
		ctx.put("CreditorDebtorRightsPercentage", inputMap.get("Creditor_Debtor_Rights"));
		ctx.put("CriminalDefensePercentage", inputMap.get("Criminal_Defense"));
		
		//ctx.put("DefensePercentage", inputMap.get("AOP_Percentage_7"));
		
		ctx.put("EducationPercentage", inputMap.get("Education"));
		ctx.put("ElderLawPercentage", inputMap.get("Elder_Law"));
		ctx.put("ErisaPercentage", inputMap.get("Employee_Benefits_ERISA"));
		ctx.put("EmploymentPercentage", inputMap.get("Employment"));
		ctx.put("EntertainmentPercentage", inputMap.get("Entertainment_Sports"));
		ctx.put("EnvironmentalPercentage", inputMap.get("Environmental"));			
		
		
		ctx.put("DomesticRelationsPercentage", inputMap.get("Family_Law"));
		
		ctx.put("FinancialInstitutionPercentage", inputMap.get("Financial_Institution_Banking_Finance"));		
		ctx.put("GovernmentPercentage", inputMap.get("Government_Municipal_Zoning"));
		ctx.put("HealthcarePercentage", inputMap.get("Healthcare"));	
		
		ctx.put("InsuranceDefensePercentage", inputMap.get("Insurance_Defense"));
		ctx.put("ImmigrationPercentage", inputMap.get("Immigration"));
		ctx.put("InternationalPercentage", inputMap.get("International"));
		ctx.put("InvestmentCounselingPercentage", inputMap.get("Investment_Counseling_Money_Management"));	
		
		ctx.put("JuvenilePercentage", inputMap.get("Juvenile_Guardianship"));
		ctx.put("LaborManagementPercentage", inputMap.get("Labor_Management"));
		ctx.put("LaborUnionPercentage", inputMap.get("Labor_Union"));
		
		ctx.put("MassTortDefensePercentage", inputMap.get("Mass_Tort_Class_Actions_Litigation_Defense"));
		ctx.put("MassTortPlaintiffPercentage", inputMap.get("Mass_Tort_Class_Actions_Litigation_Plaintiff"));
		ctx.put("MedicalMalpracticeDefensePercentage", inputMap.get("Medical_Malpractice_Defense"));
		ctx.put("MedicalMalpracticePlaintiffPercentage", inputMap.get("Medical_Malpractice_Plaintiff"));
		ctx.put("MergersAcquisitionsPercentage", inputMap.get("Mergers_Acquisitions"));
		
		ctx.put("NaturalResourcePercentage", inputMap.get("Natural_Resources"));
		ctx.put("NonMedicalMalpracticeDefensePercentage",inputMap.get("Non_Medical_Malpractice_Defense"));
		ctx.put("NonMedicalMalpracticePlaintiffPercentage", inputMap.get("Non_Medical_Malpractice_Plaintiff"));
		ctx.put("NonProfitPercentage", inputMap.get("Non_Profit_Charities"));
		
		ctx.put("PlaintiffOtherDefesePercentage", inputMap.get("Other_Litigation_Defense"));
		ctx.put("PlaintiffOtherPlaintiffPercentage", inputMap.get("Other_Litigation_Plaintiff"));
		
		//ctx.put("PersonalContractsPercentage", inputMap.get("AOP_Percentage_17"));
		ctx.put("PatentPercentage", inputMap.get("Patent"));
		ctx.put("PersonalInjuryDefensePercentage", inputMap.get("Personal_Injury_Litigation_Defense"));
		ctx.put("PersonalInjuryPlaintiffPercentage", inputMap.get("Personal_Injury_Litigation_Plaintiff"));
		ctx.put("ProductLiabilityDefensePercentage", inputMap.get("Product_Liability_Litigation_Defense"));
		ctx.put("ProductLiabilityPlaintiffPercentage", inputMap.get("Product_Liability_Litigation_Plaintiff"));
		
		ctx.put("RealEstateCommercialPercentage", inputMap.get("Real_Estate_Commercial"));
		ctx.put("RealEstateResidentialPercentage", inputMap.get("Real_Estate_Residential"));
		
		ctx.put("SecuritiesPercentage", inputMap.get("Securities"));
		ctx.put("SocialSecurityPercentage", inputMap.get("Social_Security_Disability"));
		
		ctx.put("TaxPercentage", inputMap.get("Tax"));
		ctx.put("TrafficDUIPercentage", inputMap.get("Traffic_DUI_DWI_Defense"));
		//ctx.put("TitlePercentage", inputMap.get("AOP_Percentage_23"));		
		//ctx.put("PublicUtilitiesPercentage", inputMap.get("Utilities"));
		
		ctx.put("UtilitiesPercentage", inputMap.get("Utilities"));
		ctx.put("WillsPercentage", inputMap.get("Trusts_Estates_Wills_Probate"));
		ctx.put("WorkersDefensePercentage", inputMap.get("Workers_Compensation_Defense"));
		ctx.put("WorkersPlaintiffPercentage", inputMap.get("Workers_Compensation_Plaintiff"));
		
		ctx.put("OtherPercentage", inputMap.get("Other"));
		
		//Claim Age Data
		ctx.put("ClaimAge_1", inputMap.get("ClaimAge_1") != null ? inputMap.get("ClaimAge_1") : null);
		ctx.put("ClaimAge_2", inputMap.get("ClaimAge_2") != null ? inputMap.get("ClaimAge_2") : null);
		ctx.put("ClaimAge_3", inputMap.get("ClaimAge_3") != null ? inputMap.get("ClaimAge_3") : null);
		ctx.put("ClaimAge_4", inputMap.get("ClaimAge_4") != null ? inputMap.get("ClaimAge_4") : null);
		ctx.put("ClaimAge_5", inputMap.get("ClaimAge_5") != null ? inputMap.get("ClaimAge_5") : null);
		ctx.put("ClaimAge_6", inputMap.get("ClaimAge_6") != null ? inputMap.get("ClaimAge_6") : null);
		ctx.put("ClaimAge_7", inputMap.get("ClaimAge_7") != null ? inputMap.get("ClaimAge_7") : null);
		ctx.put("ClaimAge_8", inputMap.get("ClaimAge_8") != null ? inputMap.get("ClaimAge_8") : null);
		ctx.put("ClaimAge_9", inputMap.get("ClaimAge_9") != null ? inputMap.get("ClaimAge_9") : null);
		ctx.put("ClaimAge_10", inputMap.get("ClaimAge_10") != null ? inputMap.get("ClaimAge_10") : null);
		
		Rating.processFTE(ctx);
		
		ctx.put("requestxml", new RequestProcessor().generateRequestXml((Context) ctx));
		
	}

	public static void populateOuputResponse(IContext ctx) throws Exception {
		Map responseMap = null;
		if (ctx.get("responseMap") != null)
			responseMap = (Map) ctx.get("responseMap");
		
		if (responseMap != null) {
			double mTCountyTax = 0.0;
			// logic to check if Tax is allowed
			boolean doNotCalculateTax = false;
			/*Object obj = RuleUtils.executeRule(ctx,
					"LawyersRule.doNotCalculateTax");
			if (obj != null && obj instanceof Boolean)
				doNotCalculateTax = (Boolean) obj;*/

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
			ctx.put("InitialCovPremium", Math.round(responseMap.get("ActualFinalBaseLimitPremium")));
			ctx.put("Premium", Math.round(responseMap.get("FinalBaseLimitPremiumWithTaxRatingEngine")));

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

	public static void callingRatingEngineForRerate(Map quoteMap) throws Exception {
		WebservicecallImpl webSer = null;
		IContext newCtx = new Context();

		newCtx.setProject("LawyersIns");
		newCtx.put("PolicyKey", quoteMap.get("PolicyKey"));
		newCtx.put("VersionSequence", quoteMap.get("VersionSequence"));
		newCtx.put("TransactionSequence", quoteMap.get("TransactionSequence"));
		newCtx.put("PolicyEffectiveDate", quoteMap.get("NewPolicyEffectiveDate"));
		quoteMap.put("PolicyEffectiveDate", quoteMap.get("NewPolicyEffectiveDate"));
		newCtx.put("CompanyKey", 1);
		newCtx.put("LOBKey", 2);
		newCtx.put("LookupStateCode", "ALL");
		newCtx.put("ERPYear", 0);	
		newCtx.put("CutOffDate", LawyersConstants.CUT_OFF_DATE);
		newCtx.put("CutOffDateGroup2", LawyersConstants.CUT_OFF_DATE_GROUP2);
		newCtx.put("NewAppFlowCutOffDate", LawyersConstants.NEWAPPFLOW_CUT_OFF_DATE);
		newCtx.put("CutOffDateGroup3", LawyersConstants.CUT_OFF_DATE_GROUP3);
		newCtx.put("CutOffDateGroup4", LawyersConstants.CUT_OFF_DATE_GROUP4);
		newCtx.put("CutOffDateGroup5", LawyersConstants.CUT_OFF_DATE_GROUP5);
		newCtx.put("CutOffDateGroup6", LawyersConstants.CUT_OFF_DATE_GROUP6);
		newCtx.put("CutOffDateGroup7", LawyersConstants.CUT_OFF_DATE_GROUP7);
		newCtx.put("CutOffDateGroup8", LawyersConstants.CUT_OFF_DATE_GROUP8);
		newCtx.put("CutOffDateCCBSupp", LawyersConstants.CUT_OFF_DATE_CCBSupp);
		newCtx.put("CutOffDate2020", LawyersConstants.CUT_OFF_DATE_2020);
		newCtx.put("FullTimeEquivalent", quoteMap.get("FullTimeEquivalent"));
		/*		
		
		
		newCtx.put("AddressKey", ctx.get("AddressKey"));
		newCtx.put("StateCode", quoteMap.get("StateCode"));
		newCtx.put("AddressTypeKey", ctx.get("AddressTypeKey"));*/;;
		//newCtx.putAll(quoteMap);
		Context newCtx1 = new Context();

		newCtx1.setProject("LawyersIns");
		newCtx1.putAll(quoteMap);
		new LawyersUtils().calculateClaimAgeRerate(newCtx1);
		List objList = SqlResources.getSqlMapProcessor(newCtx1).select("SqlStmts.UserStatementManualBoQueriesgetAllClaimAgeLWList", newCtx1);
		if (objList != null && objList.size() > 0) {
			for (int i = 0; i < objList.size(); i++) {
				Map objMap = (Map) objList.get(i);
				quoteMap.put("ClaimAge_" + (i + 1), objMap.get("Year"));
			}
		}
		populateDataInRequestForRerate(newCtx, quoteMap);

		webSer = new WebservicecallImpl();
		webSer.setInput("LawyersInfo");
		webSer.setOutput("PremiumInfoLW");
		webSer.setName("calculatePremiumForLawyers");

		String url = null;
		try {
		
			url = SystemProperties.getInstance().getString("appl.LawyersIns.webserviceurl");
		} 
		catch (Exception e) {

		}

		if (url == null)
			return;

		webSer.setServiceurl(url);
		newCtx.put("responseMap", webSer.execute(newCtx));

		Element outElementError = new LawyersValidationUtils().checkIfErrorOutput(newCtx.get("responseMap"));

		if (outElementError != null) {

			List responseErrorList = new LawyersValidationUtils().getErrorListFromResponse(outElementError);
			StringBuffer buffer = new LawyersValidationUtils().addListToInetErrorList(responseErrorList);
			//LawyersUtils.populateError(ctx, "isQuoteLetterSentView", buffer != null ? buffer.toString() : null);
			//newCtx.put("Validation", buffer.toString());
			quoteMap.put("Validation", buffer.toString());
		} else {
			
			populateOuputResponse(newCtx);	
			
			logger.debug("Rating request and response populated");
			quoteMap.put("Premium", newCtx.get("Premium"));
			quoteMap.put("RatingInput", newCtx.get("XmlInputDatatoRating"));
			quoteMap.put("RatingOutput", newCtx.get("XmlOutputDatafromRating"));
			
		}
	}
protected static void populateDataInRequestForRerate(IContext ctx, Map inputMap) throws Exception {	
		
		//Common Data
		ctx.put("PolicyEffectiveDate", inputMap.get("NewPolicyEffectiveDate"));
		ctx.put("LimitAmount", inputMap.get("LimitAmount"));
		ctx.put("DeductibleAmount", inputMap.get("DeductibleAmount"));
		ctx.put("IsClaimOptionType", inputMap.get("IsClaimOptionType"));
		ctx.put("IsClaimExpensesType", inputMap.get("IsClaimExpensesType"));
		ctx.put("NumberOfLawyers", inputMap.get("NumberOfLawyers"));
		ctx.put("StateCode", inputMap.get("StateCode"));
		
		ctx.put("NumberOfLawyers1000", inputMap.get("NumberOfLawyers1000"));
		ctx.put("NumberOfLawyers500", inputMap.get("NumberOfLawyers500"));
		ctx.put("LimitSequence",inputMap.get("LimitSequence"));
		ctx.put("IsDollarDefense", inputMap.get("IsDollarDefense"));
		RuleUtils.executeRule(ctx,"LawyersRule.AssignClaimExpensesAndDollarDefense");
		
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

		
		int mods=0;
		if(inputMap.get("Modifier")!=null && !"".equals(inputMap.get("Modifier").toString())) 
			mods=Integer.parseInt(inputMap.get("Modifier").toString());
		if(mods<-5)
			mods=-5;
		ctx.put("SchduleRatingModifier1", mods);
		ctx.put("ManualPremium",inputMap.get("ManualPremium") != null ? inputMap.get("ManualPremium") : null);
		
		//Modifier Dataint 
		
		/*ctx.put("SchduleRatingModifier2", inputMap.get("SchduleRatingModifier2"));		
		int schduleRatingModifier3=inputMap.get("SchduleRatingModifier3")!=null && !inputMap.get("SchduleRatingModifier3").equals(HtmlConstants.EMPTY)? Integer.parseInt(inputMap.get("SchduleRatingModifier3").toString()):0;
		int schduleRatingModifier4=inputMap.get("SchduleRatingModifier4")!=null && !inputMap.get("SchduleRatingModifier4").equals(HtmlConstants.EMPTY)? Integer.parseInt(inputMap.get("SchduleRatingModifier4").toString()):0;
		int schduleRatingModifier5=inputMap.get("SchduleRatingModifier5")!=null && !inputMap.get("SchduleRatingModifier5").equals(HtmlConstants.EMPTY)? Integer.parseInt(inputMap.get("SchduleRatingModifier5").toString()):0;
		int schduleRatingModifier6=inputMap.get("SchduleRatingModifier6")!=null && !inputMap.get("SchduleRatingModifier6").equals(HtmlConstants.EMPTY)? Integer.parseInt(inputMap.get("SchduleRatingModifier6").toString()):0;
		ctx.put("SchduleRatingModifier3",schduleRatingModifier3+schduleRatingModifier4+schduleRatingModifier5+schduleRatingModifier6 );
		*/
		//AOP Data //18, 26 missing AOP
		//17, 23, 60 as per Pat sheet
		ctx.put("AdministrativeorRegulatoryPercentage", inputMap.get("Administrative_Regulatory"));
		ctx.put("AdmiraltyPercentage", inputMap.get("Admiralty_Maritime"));
		ctx.put("ArbitrationPercentage", inputMap.get("Alternative_Dispute_Resolution"));
		ctx.put("AppellatePracticePercentage", inputMap.get("Appellate_Practice"));
		ctx.put("TradeRegulationPercentage", inputMap.get("Antitrust"));
		ctx.put("AviationPercentage", inputMap.get("Aviation"));
		
		ctx.put("BankruptcyPercentage",inputMap.get("Bankruptcy"));
		
		ctx.put("CivilDefensePercentage", inputMap.get("Civil_Litigation_Defense"));
		ctx.put("CivilPlaintiffPercentage", inputMap.get("Civil_Litigation_Plaintiff"));
		ctx.put("CivilRightsPercentage", inputMap.get("Civil_Rights_Discrimination"));
		
		ctx.put("CollectionsPercentage",inputMap.get("Collections_Repossession"));
		ctx.put("CommunicationsPercentage", inputMap.get("Communications_Media"));
		ctx.put("ConstructionPercentage", inputMap.get("Construction"));
		
		ctx.put("ConstructionDefensePercentage", inputMap.get("Construction_Litigation_Defense"));
		ctx.put("ConstructionPlaintiffPercentage", inputMap.get("Construction_Litigation_Plaintiff"));
		ctx.put("CopyrightTrademarkPercentage", inputMap.get("Copyright_Trademark"));
		ctx.put("CorporatePercentage", inputMap.get("Corporate_Commercial_Business"));
		ctx.put("CorporateDefensePercentage", inputMap.get("Corporate_Commercial_Litigation_Defense"));
		ctx.put("CorporatePlaintiffPercentage", inputMap.get("Corporate_Commercial_Litigation_ Plaintiff"));
		ctx.put("CreditorDebtorRightsPercentage", inputMap.get("Creditor_Debtor_Rights"));
		ctx.put("CriminalDefensePercentage", inputMap.get("Criminal_Defense"));
		
		//ctx.put("DefensePercentage", inputMap.get("AOP_Percentage_7"));
		
		ctx.put("EducationPercentage", inputMap.get("Education"));
		ctx.put("ElderLawPercentage", inputMap.get("Elder_Law"));
		ctx.put("ErisaPercentage", inputMap.get("Employee_Benefits_ERISA"));
		ctx.put("EmploymentPercentage", inputMap.get("Employment"));
		ctx.put("EntertainmentPercentage", inputMap.get("Entertainment_Sports"));
		ctx.put("EnvironmentalPercentage", inputMap.get("Environmental"));			
		
		
		ctx.put("DomesticRelationsPercentage", inputMap.get("Family_Law"));
		
		ctx.put("FinancialInstitutionPercentage", inputMap.get("Financial_Institution_Banking_Finance"));		
		ctx.put("GovernmentPercentage", inputMap.get("Government_Municipal_Zoning"));
		ctx.put("HealthcarePercentage", inputMap.get("Healthcare"));	
		
		ctx.put("InsuranceDefensePercentage", inputMap.get("Insurance_Defense"));
		ctx.put("ImmigrationPercentage", inputMap.get("Immigration"));
		ctx.put("InternationalPercentage", inputMap.get("International"));
		ctx.put("InvestmentCounselingPercentage", inputMap.get("Investment_Counseling_Money_Management"));	
		
		ctx.put("JuvenilePercentage", inputMap.get("Juvenile_Guardianship"));
		ctx.put("LaborManagementPercentage", inputMap.get("Labor_Management"));
		ctx.put("LaborUnionPercentage", inputMap.get("Labor_Union"));
		
		ctx.put("MassTortDefensePercentage", inputMap.get("Mass_Tort_Class_Actions_Litigation_Defense"));
		ctx.put("MassTortPlaintiffPercentage", inputMap.get("Mass_Tort_Class_Actions_Litigation_Plaintiff"));
		ctx.put("MedicalMalpracticeDefensePercentage", inputMap.get("Medical_Malpractice_Defense"));
		ctx.put("MedicalMalpracticePlaintiffPercentage", inputMap.get("Medical_Malpractice_Plaintiff"));
		ctx.put("MergersAcquisitionsPercentage", inputMap.get("Mergers_Acquisitions"));
		
		ctx.put("NaturalResourcePercentage", inputMap.get("Natural_Resources"));
		ctx.put("NonMedicalMalpracticeDefensePercentage",inputMap.get("Non_Medical_Malpractice_Defense"));
		ctx.put("NonMedicalMalpracticePlaintiffPercentage", inputMap.get("Non_Medical_Malpractice_Plaintiff"));
		ctx.put("NonProfitPercentage", inputMap.get("Non_Profit_Charities"));
		
		ctx.put("PlaintiffOtherDefesePercentage", inputMap.get("Other_Litigation_Defense"));
		ctx.put("PlaintiffOtherPlaintiffPercentage", inputMap.get("Other_Litigation_Plaintiff"));
		
		//ctx.put("PersonalContractsPercentage", inputMap.get("AOP_Percentage_17"));
		ctx.put("PatentPercentage", inputMap.get("Patent"));
		ctx.put("PersonalInjuryDefensePercentage", inputMap.get("Personal_Injury_Litigation_Defense"));
		ctx.put("PersonalInjuryPlaintiffPercentage", inputMap.get("Personal_Injury_Litigation_Plaintiff"));
		ctx.put("ProductLiabilityDefensePercentage", inputMap.get("Product_Liability_Litigation_Defense"));
		ctx.put("ProductLiabilityPlaintiffPercentage", inputMap.get("Product_Liability_Litigation_Plaintiff"));
		
		ctx.put("RealEstateCommercialPercentage", inputMap.get("Real_Estate_Commercial"));
		ctx.put("RealEstateResidentialPercentage", inputMap.get("Real_Estate_Residential"));
		
		ctx.put("SecuritiesPercentage", inputMap.get("Securities"));
		ctx.put("SocialSecurityPercentage", inputMap.get("Social_Security_Disability"));
		
		ctx.put("TaxPercentage", inputMap.get("Tax"));
		ctx.put("TrafficDUIPercentage", inputMap.get("Traffic_DUI_DWI_Defense"));
		//ctx.put("TitlePercentage", inputMap.get("AOP_Percentage_23"));		
		//ctx.put("PublicUtilitiesPercentage", inputMap.get("Utilities"));
		
		ctx.put("UtilitiesPercentage", inputMap.get("Utilities"));
		ctx.put("WillsPercentage", inputMap.get("Trusts_Estates_Wills_Probate"));
		ctx.put("WorkersDefensePercentage", inputMap.get("Workers_Compensation_Defense"));
		ctx.put("WorkersPlaintiffPercentage", inputMap.get("Workers_Compensation_Plaintiff"));
		
		ctx.put("OtherPercentage", inputMap.get("Other"));
		
		//Claim Age Data
		ctx.put("ClaimAge_1", inputMap.get("ClaimAge_1") != null ? inputMap.get("ClaimAge_1") : null);
		ctx.put("ClaimAge_2", inputMap.get("ClaimAge_2") != null ? inputMap.get("ClaimAge_2") : null);
		ctx.put("ClaimAge_3", inputMap.get("ClaimAge_3") != null ? inputMap.get("ClaimAge_3") : null);
		ctx.put("ClaimAge_4", inputMap.get("ClaimAge_4") != null ? inputMap.get("ClaimAge_4") : null);
		ctx.put("ClaimAge_5", inputMap.get("ClaimAge_5") != null ? inputMap.get("ClaimAge_5") : null);
		ctx.put("ClaimAge_6", inputMap.get("ClaimAge_6") != null ? inputMap.get("ClaimAge_6") : null);
		ctx.put("ClaimAge_7", inputMap.get("ClaimAge_7") != null ? inputMap.get("ClaimAge_7") : null);
		ctx.put("ClaimAge_8", inputMap.get("ClaimAge_8") != null ? inputMap.get("ClaimAge_8") : null);
		ctx.put("ClaimAge_9", inputMap.get("ClaimAge_9") != null ? inputMap.get("ClaimAge_9") : null);
		ctx.put("ClaimAge_10", inputMap.get("ClaimAge_10") != null ? inputMap.get("ClaimAge_10") : null);
		
		Rating.processFTE(ctx);
		
		ctx.put("requestxml", new RequestProcessor().generateRequestXml((Context) ctx));
		
	}
}
