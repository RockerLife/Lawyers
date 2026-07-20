package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.BeanUtils;

import com.manage.managebusinessrules.rules.RulesResources;
import com.manage.managecomponent.components.SetParametersForStoredProcedures;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.outline.rules.pojo.AOP;
import com.outline.rules.pojo.AopNew;
import com.outline.rules.pojo.BankruptcySupplement;
import com.outline.rules.pojo.CollectionSupplement;
import com.outline.rules.pojo.CopyRightTrademarkSupplement;
import com.outline.rules.pojo.Coverage;
import com.outline.rules.pojo.FamilyLawSupplement;
import com.outline.rules.pojo.FinancialSupplement;
import com.outline.rules.pojo.Firm;
import com.outline.rules.pojo.GovernmentSupplement;
import com.outline.rules.pojo.LitigationSupplement;
import com.outline.rules.pojo.MorePracticeManagementNewApp;
import com.outline.rules.pojo.PlaintiffSupp;
import com.outline.rules.pojo.PracticeManagement;
import com.outline.rules.pojo.PublicFiguresAjaxExpansions;
import com.outline.rules.pojo.RealEstateSupplementsNewApp;
import com.outline.rules.pojo.ResidentialSupplement;
import com.outline.rules.pojo.Tax;
import com.outline.rules.pojo.UnderwritingInformation;
import com.outline.rules.pojo.WillsTrustsEstates;
import com.outline.rules.pojo.willsEstateNewApp;
import com.userbo.LawyersUtils;

public class RuleEngineUtilityNewApp {
	private static InetLogger logger = InetLogger.getInetLogger(RuleEngineUtilityNewApp.class);

	public static void evaluateFirmDetailNewApp(Context ctx) throws Exception {
		String ruleid = "";
		/* Rule no:-2Firm Name and Address common rule for NB and RB */

		ruleid = "validateFirmState";
		logger.debug("going to debug : " + ruleid);
		Firm f1 = (Firm) initlizePOJO(ctx, new Firm());
		ctx.put("RuleObject", f1);
		insertRulesToDatabase(callRuleEngine(ctx, ruleid), f1, ruleid, f1.getDescription(), f1.getTooltip());

		ruleid = "validateFirmAddress_Lawyers";
		boolean containsPOBOX = false;
		logger.debug("going to debug : " + ruleid);
		String address1 = "";
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {
			address1 = ctx.get("Street") != null ? ctx.get("Street").toString().trim().toLowerCase() : "";
		}
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("2")) {
			address1 = ctx.get("Address1") != null ? ctx.get("Address1").toString().trim().toLowerCase() : "";

		}
		/*
		 * String[] poPatterns=getTokens(ctx.get("poBoxNamePatterns").toString().trim().
		 * toLowerCase(),","); for(int i=0;i<poPatterns.length;i++) {
		 * if(address1.contains(poPatterns[i]) ||address2.contains(poPatterns[i])) {
		 * containsPOBOX=true; }
		 * 
		 * }
		 */

		if (address1.contains(" "))
			address1 = address1.replace(" ", "");
		if (address1.contains("-"))
			address1 = address1.replace("-", "");
		if (address1.contains("."))
			address1 = address1.replace(".", "");
		address1=address1.toUpperCase();
		if (address1.contains("POBOX") /* ||address2.contains(poPatterns[i]) */) {
			containsPOBOX = true;
		}

		ctx.put("containsPOBOXInAddress", containsPOBOX);
		Firm firm = (Firm) initlizePOJO(ctx, new Firm());
		ctx.put("RuleObject", firm);
		insertRulesToDatabase(callRuleEngine(ctx, ruleid), firm, ruleid, firm.getDescription(), firm.getTooltip());

		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {

			/* Rule no:-3 Desired Eff Date */
			ctx.put("currentDate", new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
			String effectiveDateOfPolicyString = new SimpleDateFormat("MM/dd/yyyy")
					.format(new Date(ctx.get("PolicyEffectiveDate").toString()));
			ruleid = "validateEffectiveDateWithCurrentDateNewBusiness_Lawyers";
			logger.debug("going to debug : " + ruleid);
			ctx.put("isCurrentDateAterEfectiveDate",
					DateUtils.isDateBefore(ctx.get("PolicyEffectiveDate"), ctx.get("currentDate")));
			Coverage coverage = (Coverage) initlizePOJO(ctx, new Coverage());
			ctx.put("RuleObject", coverage);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), coverage, ruleid, coverage.getDescription(),
					coverage.getTooltip());

			long daysDifferenceForEfectiveDate = (DateUtils.calculateDiffInDays(new Date(effectiveDateOfPolicyString),
					new Date(ctx.get("currentDate").toString())) + 1);
			ctx.put("daysDifferenceForEfectiveDate", daysDifferenceForEfectiveDate);
			logger.debug("daysDifferenceForEfectiveDate: " + daysDifferenceForEfectiveDate);
			ruleid = "validateEffectiveDateWithDaysNewBusiness_Lawyers";
			logger.debug("going to debug : " + ruleid);
			Coverage coverage1 = (Coverage) initlizePOJO(ctx, new Coverage());
			ctx.put("RuleObject", coverage1);

			insertRulesToDatabase(callRuleEngine(ctx, ruleid), coverage1, ruleid, coverage1.getDescription(),
					coverage1.getTooltip());
		}
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("2")) {
			List previousPolicyKey = (List) SqlResources.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey", ctx);
			ctx.putAll((Map) previousPolicyKey.get(0));
			Context oldPolicyContext = new Context();
			oldPolicyContext.setProject(ctx.getProject());
			oldPolicyContext.put("PolicyKey", ctx.get("PreviousPolicykey"));
			oldPolicyContext.put("VersionSequence", ctx.get("previousVersionSequence"));
			List oldPolicyAllAttornieslist = SqlResources.getSqlMapProcessor(oldPolicyContext)
					.select("SqlStmts.UserStatementdroolQueriesgetAllAttorneys", oldPolicyContext);
			int odlRatedAttorneyCount = 0, currentRatedAttorneyCount = 0;
			List getAddressdetailsForAll = SqlResources.getSqlMapProcessor(oldPolicyContext)
					.select("SqlStmts.UserStatementManualBoQueriesgetAddressdetailsForAll", oldPolicyContext);
			oldPolicyContext.putAll((Map) getAddressdetailsForAll.get(0));

			ctx.put("currentDate", new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
			ctx.put("effectiveDateOfPolicy",
					new SimpleDateFormat("MM/dd/yyyy").format(new Date(ctx.get("PolicyEffectiveDate").toString())));

			long daysDifferenceForEfectiveDate = 0;
			daysDifferenceForEfectiveDate = (DateUtils.calculateDiffInDays(
					new Date(ctx.get("effectiveDateOfPolicy").toString()),
					new Date(ctx.get("currentDate").toString())));

			ruleid = "validateEffectiveDateWithDaysReNewBusiness_Lawyers";
			logger.debug("going to debug : " + ruleid);
			ctx.put("daysDifferenceForEfectiveDate", daysDifferenceForEfectiveDate);
			Firm firm1 = (Firm) initlizePOJO(ctx, new Firm());
			ctx.put("RuleObject", firm1);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), firm1, ruleid, firm1.getDescription(),
					firm1.getTooltip());
		}
	}

	public static void evaluateAopWorkFlowNewApp(Context ctx) throws Exception {
		String ruleid = "";

		List areasOfPracticeDataList = SqlResources.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementdroolQueriesgetAopData", ctx);
		if (areasOfPracticeDataList != null) {

			ruleid = "investmentCounselingMoneyMgtAOP_Lawyers";
			archiveFlag(ctx, ruleid);
			ruleid = "securitiesBonds_Lawyers";
			archiveFlag(ctx, ruleid);

			for (int i = 0; i < areasOfPracticeDataList.size(); i++) {
				Map dataMap = (Map) areasOfPracticeDataList.get(i);

				if (dataMap.get("AOPKey").toString().equals("15")) {
					ruleid = "investmentCounselingMoneyMgtAOP_Lawyers";
					logger.debug("going to debug" + ruleid);
					ctx.put("investmentCounselingMoneyMgtPercentage", dataMap.get("PercentageValue"));
					AOP areaOfPractice = (AOP) initlizePOJO(ctx, new AOP());
					ctx.put("RuleObject", areaOfPractice);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), areaOfPractice, ruleid,
							areaOfPractice.getDescription(), areaOfPractice.getTooltip());
				}
				if (dataMap.get("AOPKey").toString().equals("21")) {
					ruleid = "securitiesBonds_Lawyers";
					logger.debug("going to debug" + ruleid);
					ctx.put("securitiesBondsAOPPercentage", dataMap.get("PercentageValue"));
					AOP areaOfPractice = (AOP) initlizePOJO(ctx, new AOP());
					ctx.put("RuleObject", areaOfPractice);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), areaOfPractice, ruleid,
							areaOfPractice.getDescription(), areaOfPractice.getTooltip());
				}
				if (dataMap.get("AOPKey").toString().equals("38")) {
					ruleid = "EntertainmentSports_Lawyers";
					logger.debug("going to debug" + ruleid);
					ctx.put("entertainmentSportsAOPPercentage", dataMap.get("PercentageValue"));
					AopNew areaOfPractice = (AopNew) initlizePOJO(ctx, new AopNew());
					ctx.put("RuleObject", areaOfPractice);
					// public static void insertRulesToDatabase(Context ctx,Object obj,String
					// ruleId,String description,String toolTip )
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), areaOfPractice, ruleid,
							areaOfPractice.getDescription(), areaOfPractice.getTooltip());
				}
				if (dataMap.get("AOPKey").toString().equals("42")) {
					ruleid = "InternationalAOP_Lawyers";
					logger.debug("going to debug" + ruleid);

					ctx.put("patentAOPPercentage", dataMap.get("PercentageValue"));
					AopNew areaOfPractice = (AopNew) initlizePOJO(ctx, new AopNew());
					ctx.put("RuleObject", areaOfPractice);

					insertRulesToDatabase(callRuleEngine(ctx, ruleid), areaOfPractice, ruleid,
							areaOfPractice.getDescription(), areaOfPractice.getTooltip());

				}
				if (dataMap.get("AOPKey").toString().equals("46")) {
					ruleid = "MergersAcquisitions_Lawyers";
					logger.debug("going to debug" + ruleid);
					ctx.put("mergersAcquisitionsAOPPercentage", dataMap.get("PercentageValue"));
					AopNew areaOfPractice = (AopNew) initlizePOJO(ctx, new AopNew());
					ctx.put("RuleObject", areaOfPractice);

					insertRulesToDatabase(callRuleEngine(ctx, ruleid), areaOfPractice, ruleid,
							areaOfPractice.getDescription(), areaOfPractice.getTooltip());
				}

				if (dataMap.get("AOPKey").toString().equals("67")) {
					ruleid = "LitigationOtherPlaintiff_Lawyers";
					logger.debug("going to debug" + ruleid);
					ctx.put("litigationOtherPlaintiffAOPPercentage", dataMap.get("PercentageValue"));
					AopNew areaOfPractice = (AopNew) initlizePOJO(ctx, new AopNew());
					ctx.put("RuleObject", areaOfPractice);

					insertRulesToDatabase(callRuleEngine(ctx, ruleid), areaOfPractice, ruleid,
							areaOfPractice.getDescription(), areaOfPractice.getTooltip());
				}

				if (dataMap.get("AOPKey").toString().equals("59")) {
					ruleid = "MassTortLitigationPlaintiff_Lawyers";
					logger.debug("going to debug" + ruleid);
					ctx.put("massTortLitigationPlaintiffAOPPercentage", dataMap.get("PercentageValue"));
					AopNew areaOfPractice = (AopNew) initlizePOJO(ctx, new AopNew());
					ctx.put("RuleObject", areaOfPractice);

					insertRulesToDatabase(callRuleEngine(ctx, ruleid), areaOfPractice, ruleid,
							areaOfPractice.getDescription(), areaOfPractice.getTooltip());
				}

				if (dataMap.get("AOPKey").toString().equals("60")) {
					ruleid = "MedicalMalpracticePlaintiff_Lawyers";
					logger.debug("going to debug" + ruleid);
					ctx.put("medicalMalpracticePlaintiffAOPPercentage", dataMap.get("PercentageValue"));
					AopNew areaOfPractice = (AopNew) initlizePOJO(ctx, new AopNew());
					ctx.put("RuleObject", areaOfPractice);

					insertRulesToDatabase(callRuleEngine(ctx, ruleid), areaOfPractice, ruleid,
							areaOfPractice.getDescription(), areaOfPractice.getTooltip());
				}

				if (dataMap.get("AOPKey").toString().equals("63")) {
					ruleid = "ProductLiabilityPlaintiff_Lawyers";
					logger.debug("going to debug" + ruleid);
					ctx.put("productLiabilityPlaintiffAOPPercentage", dataMap.get("PercentageValue"));
					AopNew areaOfPractice = (AopNew) initlizePOJO(ctx, new AopNew());
					ctx.put("RuleObject", areaOfPractice);

					insertRulesToDatabase(callRuleEngine(ctx, ruleid), areaOfPractice, ruleid,
							areaOfPractice.getDescription(), areaOfPractice.getTooltip());
				}

				if (dataMap.get("AOPKey").toString().equals("48")) {
					ruleid = "Patent_Lawyers";
					logger.debug("going to debug" + ruleid);
					ctx.put("patentAOPPercentage", dataMap.get("PercentageValue"));
					AopNew areaOfPractice = (AopNew) initlizePOJO(ctx, new AopNew());
					ctx.put("RuleObject", areaOfPractice);

					insertRulesToDatabase(callRuleEngine(ctx, ruleid), areaOfPractice, ruleid,
							areaOfPractice.getDescription(), areaOfPractice.getTooltip());
				}
				if (dataMap.get("AOPKey").toString().equals("68")) {
					ruleid = "MassTortLitigationDefense_Lawyers";
					logger.debug("going to debug" + ruleid);
					int patentAOPPercentage = dataMap.get("PercentageValue") != null
							? Integer.parseInt(dataMap.get("PercentageValue").toString())
							: 0;
					/*
					 * ctx.put("patentAOPPercentage",dataMap.get("PercentageValue")); AopNew
					 * areaOfPractice=(AopNew)initlizePOJO(ctx, new AopNew());
					 * ctx.put("RuleObject",areaOfPractice);
					 */
					if (patentAOPPercentage > 0)
						insertRulesToDatabaseTemp(ctx, ruleid,
								"Mass Tort Litigation / Class Actions – Defense Greater than zero",
								"Decline (Outside of C&F Authority Guidelines)");
					else
						archiveFlag(ctx, ruleid);
					// insertRulesToDatabase(callRuleEngine(ctx,ruleid),areaOfPractice,ruleid,areaOfPractice.getDescription(),areaOfPractice.getTooltip());
				}
			}
		} else {
			ruleid = "investmentCounselingMoneyMgtAOP_Lawyers";
			archiveFlag(ctx, ruleid);
			ruleid = "securitiesBonds_Lawyers";
			archiveFlag(ctx, ruleid);
			ruleid = "entertainmentSportsAOPPercentage";
			archiveFlag(ctx, ruleid);
			ruleid = "International_Lawyers";
			archiveFlag(ctx, ruleid);
			ruleid = "MergersAcquisitions_Lawyers";
			archiveFlag(ctx, ruleid);
			ruleid = "MassTortLitigationDefense_Lawyers";
			archiveFlag(ctx, ruleid);
		}

		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {
			if (areasOfPracticeDataList != null) {

				ruleid = "environmentalAOP_Lawyers";
				archiveFlag(ctx, ruleid);
				ruleid = "otherAop_Lawyers";
				archiveFlag(ctx, ruleid);
				ruleid = "governmentNBAOP_Lawyers";
				archiveFlag(ctx, ruleid);
				ruleid = "bankruptcyAOP_Lawyers";
				archiveFlag(ctx, ruleid);
				ruleid = "copyrightTrademarkPatentAOP_Lawyers";
				archiveFlag(ctx, ruleid);

				for (int i = 0; i < areasOfPracticeDataList.size(); i++) {
					Map dataMap = (Map) areasOfPracticeDataList.get(i);

					if (dataMap.get("AOPKey").toString().equals("6")) {
						ruleid = "copyrightTrademarkPatentAOP_Lawyers";
						logger.debug("going to debug" + ruleid);
						ctx.put("ctpPercentage", dataMap.get("PercentageValue"));
						AOP areaOfPractice = (AOP) initlizePOJO(ctx, new AOP());
						ctx.put("RuleObject", areaOfPractice);
						insertRulesToDatabase(callRuleEngine(ctx, ruleid), areaOfPractice, ruleid,
								areaOfPractice.getDescription(), areaOfPractice.getTooltip());
					}
					if (dataMap.get("AOPKey").toString().equals("10")) {
						ruleid = "environmentalAOP_Lawyers";
						logger.debug("going to debug" + ruleid);
						ctx.put("environmentalAOPPercentage", dataMap.get("PercentageValue"));
						AOP areaOfPractice = (AOP) initlizePOJO(ctx, new AOP());
						ctx.put("RuleObject", areaOfPractice);
						insertRulesToDatabase(callRuleEngine(ctx, ruleid), areaOfPractice, ruleid,
								areaOfPractice.getDescription(), areaOfPractice.getTooltip());
					}

					if (dataMap.get("AOPKey").toString().equals("25")) {
						ruleid = "otherAop_Lawyers";
						logger.debug("going to debug" + ruleid);
						ctx.put("otherAOPPercentage", dataMap.get("PercentageValue"));
						AOP areaOfPractice = (AOP) initlizePOJO(ctx, new AOP());
						ctx.put("RuleObject", areaOfPractice);
						insertRulesToDatabase(callRuleEngine(ctx, ruleid), areaOfPractice, ruleid,
								areaOfPractice.getDescription(), areaOfPractice.getTooltip());
					}

					if (dataMap.get("AOPKey").toString().equals("13")) {
						ruleid = "governmentNBAOP_Lawyers";
						logger.debug("going to debug" + ruleid);
						ctx.put("governmentAOPPercentage", dataMap.get("PercentageValue"));
						AOP areaOfPractice = (AOP) initlizePOJO(ctx, new AOP());
						ctx.put("RuleObject", areaOfPractice);
						insertRulesToDatabase(callRuleEngine(ctx, ruleid), areaOfPractice, ruleid,
								areaOfPractice.getDescription(), areaOfPractice.getTooltip());
					}
				}
			}
		}
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("2")) {
			List previousPolicyKey = (List) SqlResources.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey", ctx);
			ctx.putAll((Map) previousPolicyKey.get(0));
			Context oldPolicyContext = new Context();
			oldPolicyContext.setProject(ctx.getProject());
			oldPolicyContext.put("PolicyKey", ctx.get("PreviousPolicykey"));
			oldPolicyContext.put("VersionSequence", ctx.get("previousVersionSequence"));

			List areasOfPracticeDataListOld = SqlResources.getSqlMapProcessor(oldPolicyContext)
					.select("SqlStmts.UserStatementdroolQueriesgetAopData", oldPolicyContext);
			if (areasOfPracticeDataList != null) {

				ruleid = "copyrightAOPRenew_Lawyers";
				archiveFlag(ctx, ruleid);
				ruleid = "environmentalAOPNewApp_Lawyers";
				archiveFlag(ctx, ruleid);

				ruleid = "otherAOPRenew_Lawyers";
				archiveFlag(ctx, ruleid);

				String aopKey = "";
				for (int i = 0; i < areasOfPracticeDataList.size(); i++) {
					Map dataMap = (Map) areasOfPracticeDataList.get(i);
					aopKey = dataMap.get("AOPKey").toString();
					for (int j = 0; j < areasOfPracticeDataListOld.size(); j++) {
						Map dataMapOld = (Map) areasOfPracticeDataListOld.get(j);

						if (aopKey.equals("6") && aopKey.equals(dataMapOld.get("AOPKey").toString())) {
							ruleid = "copyrightAOPRenew_Lawyers";
							logger.debug("going to debug" + ruleid);
							ctx.put("currentPercentage", dataMap.get("PercentageValue"));
							ctx.put("ctpPercentage", dataMapOld.get("PercentageValue"));
							AOP aop = (AOP) initlizePOJO(ctx, new AOP());
							ctx.put("RuleObject", aop);
							insertRulesToDatabase(callRuleEngine(ctx, ruleid), aop, ruleid, aop.getDescription(),
									aop.getTooltip());
						}

						if (aopKey.equals("10") && aopKey.equals(dataMapOld.get("AOPKey").toString())) {
							ruleid = "environmentalAOPNewApp_Lawyers";
							logger.debug("going to debug" + ruleid);
							ctx.put("currentPercentage", dataMap.get("PercentageValue"));
							ctx.put("environmentalAOPPercentage", dataMapOld.get("PercentageValue"));
							AOP aop = (AOP) initlizePOJO(ctx, new AOP());
							ctx.put("RuleObject", aop);
							insertRulesToDatabase(callRuleEngine(ctx, ruleid), aop, ruleid, aop.getDescription(),
									aop.getTooltip());
						}

						if (aopKey.equals("25") && aopKey.equals(dataMapOld.get("AOPKey").toString())) {
							if ((dataMapOld.get("AOPCommentDesc") != null && (dataMap.get("AOPCommentDesc") != null))) {

								if (dataMapOld.get("AOPCommentDesc") != null)
									ctx.put("AOPCommentDescOld",
											dataMapOld.get("AOPCommentDesc").toString().toLowerCase());
								else
									ctx.put("AOPCommentDescOld", "N");

								if (dataMap.get("AOPCommentDesc") != null)
									ctx.put("AOPCommentDescNew",
											dataMap.get("AOPCommentDesc").toString().toLowerCase());
								else
									ctx.put("AOPCommentDescNew", "N");

								if ((!ctx.get("AOPCommentDescOld").toString().equals(ctx.get("AOPCommentDescNew")))
										|| (Integer.valueOf(dataMap.get("PercentageValue").toString()) > Integer
												.valueOf(dataMap.get("PercentageValue").toString()))) {

									ruleid = "otherAOPRenew_Lawyers";
									logger.debug("going to debug" + ruleid);
									ctx.put("otherDescPrior", ctx.get("AOPCommentDescOld").toString());
									ctx.put("otherDescNew", ctx.get("AOPCommentDescNew").toString());
									ctx.put("otherAopNew", dataMap.get("PercentageValue"));
									ctx.put("otherAopPrior", dataMapOld.get("PercentageValue"));
									AopNew aopNew = (AopNew) initlizePOJO(ctx, new AopNew());
									ctx.put("RuleObject", aopNew);
									insertRulesToDatabase(callRuleEngine(ctx, ruleid), aopNew, ruleid,
											aopNew.getDescription(), aopNew.getTooltip());
								} else {
									ruleid = "otherAOPRenew_Lawyers";
									archiveFlag(ctx, ruleid);
								}
							}
						}
					}
				}
			}

		}
		com.userbo.AOP.getAopsValues(ctx);
		Boolean beforeLitigationImplementationDate = false;
		Object obj = RuleUtils.executeRule(ctx, "LawyersRule.beforeLitigationImplementationDate");
		if (obj != null && obj instanceof Boolean) {
			beforeLitigationImplementationDate = (Boolean) obj;
		}
		try {
			new SetParametersForStoredProcedures().setParametersInContext(ctx,
					"PolicyKey,litigationNewImplDate,RenewalSupplementNewImplDate");
			List getSupplementFlagDetails = (List) SqlResources.getSqlMapProcessor(ctx)
					.select("FirmLW.ClearSupplementFlagsLW_p", ctx);

			if (getSupplementFlagDetails != null && getSupplementFlagDetails.size() > 0)
				ctx.putAll((Map) getSupplementFlagDetails.get(0));
		} catch (Exception etab) {
			logger.error("Unexpected error", etab);
		}

		String[] plaintiffFlags = { "validateAreaOfLitigation_Lawyers", "validateOtherPlantiff_Lawyers",
				"plaintiffLitigationAOP_Lawyers", "validatePlaintiffLargestCase_Lawyers",
				"validateAuthorizationsForSettlement_Lawyers", "massTortOrClassActionNewApp_Lawyers",
				"numberOfInjuryCasesIn12MonthNewApp_Lawyers", "validateAuthorizationsForSettlementRenew_Lawyers",
				"numberOfInjuryCasesIn12MonthNewApp_Lawyers" };
		String[] financialSuppFlags = { "EquityInterestRule_Lawyers", "ServicesRelatedRegulatoryRule_Lawyers",
				"UnderRegulatoryReviewRule_Lawyers", "HaveBecomeBankruptInsolventRule_Lawyers" };
		String[] bankruptcyFlags = { "RepresentedDebtors_Lawyers", "PreBankruptcyServices_Lawyers",
				"DisclosureStatementExplaining_Lawyers", "ConspicuousStatement_Lawyers" };
		String[] copyTradeMarkFlags = { "OtherServices_Lawyers" };
		String[] governmentFlags = { "EminentDomainServicesRule_Lawyers", "ProvidingBondWorkRule_Lawyers" };
		String[] realEstateFlags = { "checkTitleOpinionsNewApp_Lawyers", "checkOtherResidentialAop_Lawyers",
				"checkForeclosuresAndLoanWorkoutsAOPResidential_Lawyers", "TitleOpinionsIndivisual_Lawyers",
				"TitleOpinionsSalePurchaseRule_Lawyers", "ForeClosuresRule_Lawyers", "AttendclosingsRule_Lawyers",
				"SecuringFinancingRule_Lawyers", "DisclosureFormRule_Lawyers", "checkCommercialCombineAop_Lawyers",
				"checkSpeculativeAop_Lawyers", "checkForeclosuresAndLoanWorkoutsAOPCommercial_Lawyers",
				"transactionsHandled12MonthsResiNewApp_Lawyers", "checkSpeculativeRenew_Lawyers",
				"checkRealEstateRenew_Lawyers", "otherRealEstateRenew_Lawyers",
				"transactionsHandled12MonthsRenewNewApp_Lawyers" };
		String[] willsStateTrustFlags = { "EstatesHandledInYearNewApp_Lawyers", "checkAssetProtectionNewApp_Lawyers",
				"othersWillsEstateNewApp_Lawyers", "TaxOpinionsWillsEstate_Lawyers",
				"inPurchaseSaleSecuritiesNewApp_Lawyers", "checkAssetProtectionRenew_Lawyers",
				"othersWillsValueRenew_Lawyers", "inPurchaseSaleSecuritiesNewApp_Lawyers" };
		String[] taxSuppFlags = { "checkinvestmentCounselorServices_Lawyers", "checksubchapterSElections_Lawyers",
				"checkliquidationofCorporations_Lawyers", "opinonTax_Lawyers", "checkprivatePlacementMemoranda_Lawyers",
				"checkotherTax_Lawyers", "checkinvestmentCounselorServicesRenew_Lawyers",
				"checksubchapterSElectionsRenew_Lawyers", "checkliquidationofCorporationsRenew_Lawyers",
				"opinonTaxRenew_Lawyers", "checkotherTaxRenew_Lawyers" };
		String[] collectionSuppReferrals = { "IndividualCollectionsRule_Lawyers", "ServicesToPurchasersRule_Lawyers" };
		String[] familySuppReferrals = { "validateFamilyLaw_Lawyers", "validateFamilyLawRenew_Lawyers" };
		if ("Y".equals(ctx.get("CleanlitigationSuppReferrals").toString()))
			clearReferralFlags(plaintiffFlags, ctx, "Plaintiff Supplement");
		if ("Y".equals(ctx.get("CleanBankruptcySuppReferrals").toString()))
			clearReferralFlags(bankruptcyFlags, ctx, "Bankruptcy Supplement");
		if ("Y".equals(ctx.get("CleanCopyrightSuppReferrals").toString()))
			clearReferralFlags(copyTradeMarkFlags, ctx, "Copy righttrademark Supplement");
		if ("Y".equals(ctx.get("CleanFinancialSuppReferrals").toString()))
			clearReferralFlags(financialSuppFlags, ctx, "Financial Supplement");
		if ("Y".equals(ctx.get("CleanGovernmentSuppReferrals").toString()))
			clearReferralFlags(governmentFlags, ctx, "Government Supplement");
		if ("Y".equals(ctx.get("CleanRealEstateSuppReferrals").toString()))
			clearReferralFlags(realEstateFlags, ctx, "RealEstate Supplement");
		if ("Y".equals(ctx.get("CleanWillStateSuppTrustReferrals").toString()))
			clearReferralFlags(willsStateTrustFlags, ctx, "WillsStateTrust Supplement");
		if ("Y".equals(ctx.get("CleanTaxSuppReferrals").toString()))
			clearReferralFlags(taxSuppFlags, ctx, "Tax Supplement");
		if ("Y".equals(ctx.get("CleancollectionSuppReferrals").toString()))
			clearReferralFlags(collectionSuppReferrals, ctx, "collections Supplement");
		if ("Y".equals(ctx.get("CleanFamilyLawSuppReferrals").toString()))
			clearReferralFlags(familySuppReferrals, ctx, "FamilyLaw Supplement");

	}

	public static void evaluateAttorniesAboutFirmNewApp(Context ctx) throws Exception {
		String ruleid = "";
		List allAttornieslist = SqlResources.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementdroolQueriesgetAllAttorneys", ctx);
		List<Context> ruleSetContextList = new ArrayList<Context>();

		if (ctx.get("isCannibSuppPage") != null && ctx.get("isCannibSuppPage").toString().equals("Y")) {
			ruleid = "IsCannibSuppPage_Lawyers";
			logger.debug("going to debug : " + ruleid);
			ruleid = "ValidateCannibSuppPage_Lawyers";
			logger.debug("going to debug : " + ruleid);
			RuleEngineUtilityNewApp.insertRulesToDatabaseTemp(ctx, ruleid,
					"Cannabis question is answered Yes and is outside of C & F authority.",
					"Cannabis work is outside of C & F authority.");

		} else {
			ruleid = "IsCannibSuppPage_Lawyers";
			archiveFlag(ctx, ruleid);

		}

		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {

			/* rule no 24 legal service */
			if (ctx.get("IsApplicantProvidesLegalServices") != null
					&& ctx.get("IsApplicantProvidesLegalServices").toString().equals("Y")) {
				ruleid = "isApplicantProvidesLegalServices_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("isApplicantProvidesLegalServices", ctx.get("IsApplicantProvidesLegalServices"));
				Firm firm1 = (Firm) initlizePOJO(ctx, new Firm());
				ctx.put("RuleObject", firm1);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), firm1, ruleid, firm1.getDescription(),
						firm1.getTooltip());

			} else {
				ruleid = "isApplicantProvidesLegalServices_Lawyers";
				archiveFlag(ctx, ruleid);

			}
			/* Rule no 26 about your firm */

			/*
			 * if(ctx.get("IsFirmMergedWithOtherFirm")!=null) {
			 * ruleid="checkIsFirmMergedWithOtherFirm_Lawyers";
			 * logger.debug("going to debug : "+ ruleid);
			 * ctx.put("isFirmMergedWithOtherFirm", ctx.get("IsFirmMergedWithOtherFirm"));
			 * Coverage coverage=(Coverage)initlizePOJO(ctx, new Coverage());
			 * ctx.put("RuleObject",coverage);
			 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage,ruleid,coverage.
			 * getDescription(),coverage.getTooltip()); } else {
			 * ruleid="checkIsFirmMergedWithOtherFirm_Lawyers"; archiveFlag(ctx,ruleid);
			 * 
			 * }
			 */
			/*
			 * List allAttornieslist = SqlResources.getSqlMapProcessor(ctx).select(
			 * "SqlStmts.UserStatementdroolQueriesgetAllAttorneys",ctx);
			 */
			if (allAttornieslist != null && allAttornieslist instanceof List) {
				int count = allAttornieslist.size();
				ruleid = "attorneySupportStaffRatio_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("totalAttornyes", count * 3);
				ctx.put("totalNumOfNonAttorneyStaff", ctx.get("TotalNumOfNonAttorneyStaff"));
				Firm firm = (Firm) initlizePOJO(ctx, new Firm());
				ctx.put("RuleObject", firm);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), firm, ruleid, firm.getDescription(),
						firm.getTooltip());
			}

			else {
				ruleid = "attorneySupportStaffRatio_Lawyers";
				archiveFlag(ctx, ruleid);
			}

			/*
			 * if(allAttornieslist!=null && allAttornieslist.size()==1 ) { List
			 * attornieslistsolo= (List) SqlResources.getSqlMapProcessor(ctx).select(
			 * "SqlStmts.UserStatementdroolQueriesgetSoloAttorneys",ctx); Context
			 * attorniesctx=new Context(); attorniesctx.putAll((Map)
			 * attornieslistsolo.get(0)); ruleid="soloAttorneyAndNoBackup_Lawyers";
			 * logger.debug("going to debug "+ ruleid); ctx.put("isFirmHaveBackupAttorney",
			 * ctx.get("IsFirmHaveBackupAttorney")); Firm firm1=(Firm)initlizePOJO(ctx, new
			 * Firm()); ctx.put("RuleObject",firm1);
			 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm1,ruleid,firm1.
			 * getDescription(),firm1.getTooltip()); } else {
			 * ruleid="soloAttorneyAndNoBackup_Lawyers"; archiveFlag(ctx,ruleid); }
			 */

			if (ctx.get("IsFirmCoverageForPreceedorFirms") != null
					&& !ctx.get("IsFirmCoverageForPreceedorFirms").equals(HtmlConstants.EMPTY)) {
				// AboutFirm Rule 7B
				ruleid = "DoesFirmCoverageForPreceedorFirms_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("doesFirmCoverageForPreceedorFirms", ctx.get("IsFirmCoverageForPreceedorFirms"));
				Firm firm = (Firm) initlizePOJO(ctx, new Firm());
				ctx.put("RuleObject", firm);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), firm, ruleid, firm.getDescription(),
						firm.getTooltip());

			} else {
				ruleid = "DoesFirmCoverageForPreceedorFirms_Lawyers";
				archiveFlag(ctx, ruleid);

			}

		}
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("2")) {
			if (ctx.get("IsFirmMergedWithOtherFirm") != null) {
				ruleid = "checkIsFirmMergedWithOtherFirmRenewNew_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("isFirmMergedWithOtherFirm", ctx.get("IsFirmMergedWithOtherFirm"));
				Coverage coverage = (Coverage) initlizePOJO(ctx, new Coverage());
				ctx.put("RuleObject", coverage);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), coverage, ruleid, coverage.getDescription(),
						coverage.getTooltip());
			} else {
				ruleid = "checkIsFirmMergedWithOtherFirmRenewNew_Lawyers";
				archiveFlag(ctx, ruleid);

			}
		}

	}

	public static void evaluateCoverageNewAppWorkFlow(Context ctx) throws Exception {
		String ruleid = "";

		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {
			if (ctx.get("IsFirmHaveLawyersLiabilityInsurance") != null
					&& ctx.get("IsFirmHaveLawyersLiabilityInsurance").toString().equals("Y")) {

				ruleid = "ValidateForPriorCopyofPolicy_Lawyers";
				logger.debug("going to debug : " + ruleid);
				RuleEngineUtilityNewApp.insertRulesToDatabaseTemp(ctx, ruleid,
						"Confirm that copy of current policy is received", " ");

				if (ctx.get("PolicyExpirationDatePross") != null) {
					List policyDetails = SqlResources.getSqlMapProcessor(ctx)
							.select("SqlStmts.UserStatementdroolQueriesgetPolicyData", ctx);

					if (policyDetails != null && policyDetails instanceof List) {
						ctx.putAll((Map) policyDetails.get(0));

						ctx.put("policyExpiryDate",
								new Date(ctx.get("PolicyExpirationDatePross").toString()).getTime());
						ctx.put("effectiveDateOfPolicy", new Date(ctx.get("PolicyEffectiveDate").toString()).getTime());
						logger.debug("ccc...." + ctx.get("policyExpiryDate"));
						ruleid = "validateExpirationDateNewBusiness_Lawyers";
						logger.debug("going to debug : " + ruleid);
						Coverage coverage = (Coverage) initlizePOJO(ctx, new Coverage());
						ctx.put("RuleObject", coverage);
						insertRulesToDatabase(callRuleEngine(ctx, ruleid), coverage, ruleid, coverage.getDescription(),
								coverage.getTooltip());

					} else {
						ruleid = "validateExpirationDateNewBusiness_Lawyers";
						archiveFlag(ctx, ruleid);
					}
				} else {
					ruleid = "validateExpirationDateNewBusiness_Lawyers";
					archiveFlag(ctx, ruleid);
				}

				int currentLimit = 0, requestedLimit = 0;
				try {
					List limits = (List) SqlResources.getSqlMapProcessor(ctx)
							.select("SqlStmts.UserStatementdroolQueriesgetLimits", ctx);
					Context limitctx = new Context();
					limitctx.putAll((Map) limits.get(0));
					logger.debug(limitctx.get("CurrentLimit"));
					currentLimit = Integer.valueOf(limitctx.get("CurrentLimit").toString());
					requestedLimit = Integer.valueOf(limitctx.get("RequestedLimit").toString());
					ctx.put("limitSequenceDifference", 0);
					ctx.put("currentLimit", limitctx.get("CurrentLimit"));
					ctx.put("requestedLimit", limitctx.get("RequestedLimit"));
				} catch (Exception e) {
					logger.error("Unexpected error", e);
				}

				if (ctx.get("currentLimit") != null && ctx.get("requestedLimit") != null) {
					if ((currentLimit == 100000
							&& (requestedLimit == 750000 || requestedLimit == 1000000 || requestedLimit == 2000000))
							|| (currentLimit == 200000 && (requestedLimit == 100000 || requestedLimit == 750000
									|| requestedLimit == 1000000 || requestedLimit == 2000000))
							|| (currentLimit == 250000
									&& (requestedLimit == 100000 || requestedLimit == 200000 || requestedLimit == 750000
											|| requestedLimit == 1000000 || requestedLimit == 2000000))
							|| (currentLimit == 300000 && (requestedLimit == 100000 || requestedLimit == 200000
									|| requestedLimit == 250000 || requestedLimit == 750000 || requestedLimit == 1000000
									|| requestedLimit == 2000000))
							|| (currentLimit == 500000
									&& (requestedLimit == 100000 || requestedLimit == 200000 || requestedLimit == 250000
											|| requestedLimit == 300000 || requestedLimit == 2000000))
							|| (currentLimit == 750000 && (requestedLimit == 100000 || requestedLimit == 200000
									|| requestedLimit == 250000 || requestedLimit == 300000 || requestedLimit == 500000
									|| requestedLimit == 2000000))
							|| (currentLimit == 1000000 && (requestedLimit == 100000 || requestedLimit == 200000
									|| requestedLimit == 250000 || requestedLimit == 300000 || requestedLimit == 500000
									|| requestedLimit == 750000))
							|| (currentLimit == 2000000 && (requestedLimit == 100000 || requestedLimit == 200000
									|| requestedLimit == 250000 || requestedLimit == 300000 || requestedLimit == 500000
									|| requestedLimit == 750000 || requestedLimit == 1000000))) {
						int limitSequenceDifference = 1;
						ctx.put("limitSequenceDifference", limitSequenceDifference);
					}

					ruleid = "CheckLimits_Lawyers";
					logger.debug("going to debug : " + ruleid);
					Coverage coverage = (Coverage) initlizePOJO(ctx, new Coverage());
					ctx.put("RuleObject", coverage);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), coverage, ruleid, coverage.getDescription(),
							coverage.getTooltip());

				} else {

					ruleid = "CheckLimits_Lawyers";
					archiveFlag(ctx, ruleid);
				}
				Map dataMap = null;
				int LimitSequencePross = 0, LimitSequence = 0;
				List professionalLiabilityDetailsList = SqlResources.getSqlMapProcessor(ctx)
						.select("SqlStmts.UserStatementdroolQueriesgetProfessionalLiabilityDetailList", ctx);
				if (professionalLiabilityDetailsList != null && professionalLiabilityDetailsList instanceof List) {
					dataMap = (Map) professionalLiabilityDetailsList.get(0);
					LimitSequencePross = dataMap.get("LimitSequencePross") != null
							? Integer.parseInt(dataMap.get("LimitSequencePross").toString())
							: 0;
				}
				List policyCoverageDetailsList = SqlResources.getSqlMapProcessor(ctx)
						.select("SqlStmts.UserStatementdroolQueriesgetPolicyCoverageDetailsWithFirstQuote", ctx);
				if (policyCoverageDetailsList != null && policyCoverageDetailsList instanceof List) {
					dataMap = (Map) policyCoverageDetailsList.get(0);
					LimitSequence = dataMap.get("LimitSequence") != null
							? Integer.parseInt(dataMap.get("LimitSequence").toString())
							: 0;

				}
				if (LimitSequence < LimitSequencePross) {
					ruleid = "ValidateReductionOfLimit_Lawyers";
					logger.debug("going to debug : " + ruleid);
					RuleEngineUtilityNewApp.insertRulesToDatabaseTemp(ctx, ruleid,
							"Requesting limit below expiring limit", "Requires reduction in limit advisory signature");
				} else {
					ruleid = "ValidateReductionOfLimit_Lawyers";
					archiveFlag(ctx, ruleid);
				}
			} else {
				ruleid = "ValidateForPriorCopyofPolicy_Lawyers";
				archiveFlag(ctx, ruleid);
			}
		}
	}

	public static void evaluatePracticeManagementWorkFlowNewApp(Context ctx) throws Exception {
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {
			String ruleid = "";
			/* Rule no :- 27 Diary system */
			/*
			 * if(ctx.get("IsFirmHaveIndepDockets")!=null &&
			 * ctx.get("IsFirmHaveIndepDockets").toString().equalsIgnoreCase("N")) {
			 * ctx.put("isFirmHaveIndepDockets", ctx.get("IsFirmHaveIndepDockets"));
			 * ruleid="IsFirmHaveCenteralDocketOrDairySystem_Lawyers";
			 * logger.debug("going to debug : "+ ruleid); PracticeManagement
			 * practiceManagement=(PracticeManagement)initlizePOJO(ctx, new
			 * PracticeManagement()); ctx.put("RuleObject",practiceManagement);
			 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),practiceManagement,ruleid,
			 * practiceManagement.getDescription(),practiceManagement.getTooltip());
			 * 
			 * } else { ruleid="IsFirmHaveCenteralDocketOrDairySystem_Lawyers";
			 * archiveFlag(ctx,ruleid);
			 * 
			 * } // Rule no :- 28 firm have procedures
			 * if(ctx.get("IsFirmHaveProcForFormersClients")!=null &&
			 * ctx.get("IsFirmHaveProcForFormersClients").toString().equalsIgnoreCase("N"))
			 * { ctx.put("isFirmHaveProcForFormersClients",
			 * ctx.get("IsFirmHaveProcForFormersClients"));
			 * ruleid="IsFirmHaveProcForFormersClientsNewApp_Lawyers";
			 * logger.debug("going to debug : "+ ruleid); PracticeManagementNewApp
			 * practiceManagementNew=(PracticeManagementNewApp)initlizePOJO(ctx, new
			 * PracticeManagementNewApp()); ctx.put("RuleObject",practiceManagementNew);
			 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),practiceManagementNew,ruleid
			 * ,practiceManagementNew.getDescription(),practiceManagementNew.getTooltip());
			 * 
			 * } else { ruleid="IsFirmHaveProcForFormersClientsNewApp_Lawyers";
			 * archiveFlag(ctx,ruleid);
			 * 
			 * }
			 */
		}
	}

	public static void evaluateMorePracticeManagementNewApp(Context ctx) throws Exception {
		String ruleid = "";
		int policyStatusKey = ctx.get("PolicyStatusKey") != null
				? Integer.parseInt(ctx.get("PolicyStatusKey").toString())
				: 0;
		if (policyStatusKey == 1) {

			/* Rule no 33 Client Funds */
			if (ctx.get("IsFirmProvideInvestmentAdvice") != null
					&& ctx.get("IsFirmProvideInvestmentAdvice").toString().equalsIgnoreCase("Y")) {
				ctx.put("isFirmProvideInvestmentAdvice", ctx.get("IsFirmProvideInvestmentAdvice"));
				ruleid = "IsFirmProvideInvestmentAdvice_Lawyers";
				logger.debug("going to debug : " + ruleid);
				PracticeManagement practiceManagement = (PracticeManagement) initlizePOJO(ctx,
						new PracticeManagement());
				ctx.put("RuleObject", practiceManagement);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), practiceManagement, ruleid,
						practiceManagement.getDescription(), practiceManagement.getTooltip());

			} else {
				ruleid = "IsFirmProvideInvestmentAdvice_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			if (ctx.get("IsFirmProvidedLegalService") != null) {
				ctx.put("isFirmProvidedLegalService", ctx.get("IsFirmProvidedLegalService"));
				ruleid = "IsFirmProvidedLegalServiceNewApp_Lawyers";
				logger.debug("going to debug : " + ruleid);
				MorePracticeManagementNewApp morePracticeManagement = (MorePracticeManagementNewApp) initlizePOJO(ctx,
						new MorePracticeManagementNewApp());
				ctx.put("RuleObject", morePracticeManagement);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), morePracticeManagement, ruleid,
						morePracticeManagement.getDescription(), morePracticeManagement.getTooltip());

			} else {
				ruleid = "IsFirmProvidedLegalServiceNewApp_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			if (ctx.get("isServedAsCEOChairmanPresident") != null
					&& ctx.get("isServedAsCEOChairmanPresident").toString().equalsIgnoreCase("Y")) {
				ctx.put("isFirmServedCEO", ctx.get("isServedAsCEOChairmanPresident"));
				ruleid = "IsFirmServedCEO_Lawyers";
				logger.debug("going to debug : " + ruleid);
				MorePracticeManagementNewApp morePracticeManagement = (MorePracticeManagementNewApp) initlizePOJO(ctx,
						new MorePracticeManagementNewApp());
				ctx.put("RuleObject", morePracticeManagement);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), morePracticeManagement, ruleid,
						morePracticeManagement.getDescription(), morePracticeManagement.getTooltip());

			} else {
				ruleid = "IsFirmServedCEO_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			List Firmlist = SqlResources.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementdroolQueriesgetFirmDetails", ctx);
			if (Firmlist != null) {
				Map dataMap = (Map) Firmlist.get(0);

				ctx.put("PercentFromFirstLargestRevenueClientNew", dataMap.get("PercentFromFirstLargestRevenueClient"));
				ctx.put("PercentFromSecondLargestRevenueClientNew",
						dataMap.get("PercentFromSecondLargestRevenueClient"));
			}
			if (ctx.get("PercentFromFirstLargestRevenueClientNew") != null)
				ctx.put("firstLargestRevenueClientNew", ctx.get("PercentFromFirstLargestRevenueClientNew"));
			else
				ctx.put("firstLargestRevenueClientNew", 0);

			if (ctx.get("PercentFromSecondLargestRevenueClientNew") != null)
				ctx.put("secondLargestRevenueClientNew", ctx.get("PercentFromSecondLargestRevenueClientNew"));

			else
				ctx.put("secondLargestRevenueClientNew", 0);

			if ((ctx.get("firstLargestRevenueClientNew") != null)
					|| (ctx.get("secondLargestRevenueClientNew") != null)) {
				ruleid = "TopTwoRevenueClientsNB_Lawyers";
				logger.debug("going to debug : " + ruleid);
				MorePracticeManagementNewApp morePracticeManagement = (MorePracticeManagementNewApp) initlizePOJO(ctx,
						new MorePracticeManagementNewApp());
				ctx.put("RuleObject", morePracticeManagement);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), morePracticeManagement, ruleid,
						morePracticeManagement.getDescription(), morePracticeManagement.getTooltip());

			} else {
				ruleid = "TopTwoRevenueClientsNB_Lawyers";
				archiveFlag(ctx, ruleid);
			}

		}
		if (policyStatusKey == 2) {
			String isServedAsCEOChairmanPresidentOld = "";
			List previousPolicyKey = (List) SqlResources.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey", ctx);
			ctx.putAll((Map) previousPolicyKey.get(0));
			Context oldPolicyContext = new Context();
			oldPolicyContext.setProject(ctx.getProject());
			oldPolicyContext.put("PolicyKey", ctx.get("PreviousPolicykey"));
			oldPolicyContext.put("VersionSequence", ctx.get("previousVersionSequence"));
			List oldFirmlist = SqlResources.getSqlMapProcessor(oldPolicyContext)
					.select("SqlStmts.UserStatementdroolQueriesgetFirmDetails", oldPolicyContext);
			if (oldFirmlist != null) {
				Map dataMapOld = (Map) oldFirmlist.get(0);
				isServedAsCEOChairmanPresidentOld = dataMapOld.get("isServedAsCEOChairmanPresident") != null
						&& !dataMapOld.get("isServedAsCEOChairmanPresident").equals(HtmlConstants.EMPTY)
								? dataMapOld.get("isServedAsCEOChairmanPresident").toString()
								: "N";
				ctx.put("PercentFromFirstLargestRevenueClientOld", isServedAsCEOChairmanPresidentOld);
				ctx.put("PercentFromFirstLargestRevenueClientOld1",
						dataMapOld.get("PercentFromFirstLargestRevenueClient"));
				ctx.put("PercentFromSecondLargestRevenueClientOld",
						dataMapOld.get("PercentFromSecondLargestRevenueClient"));
			}
			/*
			 * if(ctx.get("isServedAsCEOChairmanPresident")!=null &&
			 * ((ctx.get("isServedAsCEOChairmanPresidentOld")==null)||(ctx.get(
			 * "isServedAsCEOChairmanPresident")!=ctx.get(
			 * "isServedAsCEOChairmanPresidentOld")))) {
			 * if((ctx.get("isServedAsCEOChairmanPresidentOld")==null)||(!ctx.get(
			 * "isServedAsCEOChairmanPresidentOld").toString().equals(ctx.get(
			 * "isServedAsCEOChairmanPresident").toString()))) { ctx.put("isFirmServedCEO",
			 * "Y"); } else { ctx.put("isFirmServedCEO", "N"); }
			 * 
			 * ruleid="IsFirmServedCEORenewNew_Lawyers"; logger.debug("going to debug : "+
			 * ruleid); MorePracticeManagementNewApp
			 * morePracticeManagement=(MorePracticeManagementNewApp)initlizePOJO(ctx, new
			 * MorePracticeManagementNewApp());
			 * ctx.put("RuleObject",morePracticeManagement);
			 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),morePracticeManagement,
			 * ruleid,morePracticeManagement.getDescription(),morePracticeManagement.
			 * getTooltip());
			 * 
			 * } else { ruleid="IsFirmServedCEORenewNew_Lawyers"; archiveFlag(ctx,ruleid); }
			 */
			if (ctx.get("isServedAsCEOChairmanPresident") != null
					&& !ctx.get("isServedAsCEOChairmanPresident").equals(HtmlConstants.EMPTY)) {
				if (!ctx.get("isServedAsCEOChairmanPresident").toString().equals(isServedAsCEOChairmanPresidentOld)) {
					ctx.put("isFirmServedCEO", "Y");
					ruleid = "IsFirmServedCEORenewNew_Lawyers";
					logger.debug("going to debug : " + ruleid);
					MorePracticeManagementNewApp morePracticeManagement = (MorePracticeManagementNewApp) initlizePOJO(
							ctx, new MorePracticeManagementNewApp());
					ctx.put("RuleObject", morePracticeManagement);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), morePracticeManagement, ruleid,
							morePracticeManagement.getDescription(), morePracticeManagement.getTooltip());
				} else {
					ruleid = "IsFirmServedCEORenewNew_Lawyers";
					archiveFlag(ctx, ruleid);
				}
			} else {
				ruleid = "IsFirmServedCEORenewNew_Lawyers";
				archiveFlag(ctx, ruleid);
			}

			if (ctx.get("IsFirmHaveClientMoreThan25PercentOfBilling") != null
					&& ctx.get("IsFirmHaveClientMoreThan25PercentOfBilling").toString().equalsIgnoreCase("Y")) {
				List Firmlist = SqlResources.getSqlMapProcessor(ctx)
						.select("SqlStmts.UserStatementdroolQueriesgetFirmDetails", ctx);
				if (Firmlist != null) {
					Map dataMap = (Map) Firmlist.get(0);

					ctx.put("PercentFromFirstLargestRevenueClientNew",
							dataMap.get("PercentFromFirstLargestRevenueClient"));
					ctx.put("PercentFromSecondLargestRevenueClientNew",
							dataMap.get("PercentFromSecondLargestRevenueClient"));
				}
				if (ctx.get("PercentFromFirstLargestRevenueClientOld1") != null)
					ctx.put("firstLargestRevenueClientOld", ctx.get("PercentFromFirstLargestRevenueClientOld1"));
				else
					ctx.put("firstLargestRevenueClientOld", 0);

				if (ctx.get("PercentFromSecondLargestRevenueClientOld") != null)
					ctx.put("secondLargestRevenueClientOld", ctx.get("PercentFromSecondLargestRevenueClientOld"));

				else
					ctx.put("secondLargestRevenueClientOld", 0);

				if (ctx.get("PercentFromFirstLargestRevenueClientNew") != null)
					ctx.put("firstLargestRevenueClientNew", ctx.get("PercentFromFirstLargestRevenueClientNew"));
				else
					ctx.put("firstLargestRevenueClientNew", 0);

				if (ctx.get("PercentFromSecondLargestRevenueClientNew") != null)
					ctx.put("secondLargestRevenueClientNew", ctx.get("PercentFromSecondLargestRevenueClientNew"));

				else
					ctx.put("secondLargestRevenueClientNew", 0);

				if ((ctx.get("firstLargestRevenueClientNew") != null
						&& (Integer.valueOf(ctx.get("firstLargestRevenueClientNew").toString()) > Integer
								.valueOf(ctx.get("firstLargestRevenueClientOld").toString() + 5)))
						|| (ctx.get("secondLargestRevenueClientNew") != null
								&& (Integer.valueOf(ctx.get("secondLargestRevenueClientNew").toString()) > Integer
										.valueOf(ctx.get("secondLargestRevenueClientOld").toString() + 5)))) {
					ruleid = "TopTwoRevenueClients_Lawyers";
					logger.debug("going to debug : " + ruleid);
					MorePracticeManagementNewApp morePracticeManagement = (MorePracticeManagementNewApp) initlizePOJO(
							ctx, new MorePracticeManagementNewApp());
					ctx.put("RuleObject", morePracticeManagement);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), morePracticeManagement, ruleid,
							morePracticeManagement.getDescription(), morePracticeManagement.getTooltip());

				} else {
					ruleid = "TopTwoRevenueClients_Lawyers";
					archiveFlag(ctx, ruleid);
				}

			} else {
				ruleid = "IsFirmSeryyyyyvedCEO_Lawyers";
				archiveFlag(ctx, ruleid);
			}

		}
	}

	public static void pastClaimsWorkFlowNewApp(Context ctx) throws Exception {
		String ruleid = "";
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {
			/* rule no:-51,52,53 past Claims */

			if (ctx.get("IsPersonnelBeSubOfAnyInvest") != null) {
				ruleid = "validateRegulatroyIssues_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("isPersonnelBeSubOfAnyInvest", ctx.get("IsPersonnelBeSubOfAnyInvest").toString().trim());
				Coverage coverage = (Coverage) initlizePOJO(ctx, new Coverage());
				ctx.put("RuleObject", coverage);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), coverage, ruleid, coverage.getDescription(),
						coverage.getTooltip());
			} else {
				ruleid = "validateRegulatroyIssues_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			if (ctx.get("IsLawyerProfLiabClaimAgntAppl") != null) {
				ruleid = "validateClaimAgainstFirm_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("isLawyerProfLiabClaimAgntAppl", ctx.get("IsLawyerProfLiabClaimAgntAppl").toString().trim());
				Coverage coverage = (Coverage) initlizePOJO(ctx, new Coverage());
				ctx.put("RuleObject", coverage);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), coverage, ruleid, coverage.getDescription(),
						coverage.getTooltip());
			} else {
				ruleid = "validateClaimAgainstFirm_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			if (ctx.get("IsAnyActOmmBecomeClaimAgntFirm") != null) {
				ruleid = "validateActOmmissionFeeDispute_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("isAnyActOmmBecomeClaimAgntFirm", ctx.get("IsAnyActOmmBecomeClaimAgntFirm").toString().trim());
				Coverage coverage = (Coverage) initlizePOJO(ctx, new Coverage());
				ctx.put("RuleObject", coverage);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), coverage, ruleid, coverage.getDescription(),
						coverage.getTooltip());
			} else {
				ruleid = "validateActOmmissionFeeDispute_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			if ((ctx.get("IsAttorneyDeclineForProfLiability") != null
					&& ctx.get("IsAttorneyDeclineForProfLiability").toString().equals("Y"))
					&& !ctx.get("StateCode").toString().equals("MO")) {
				ruleid = "checkAttorneyDeclineForProfLiability_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("isAttorneyDeclineForProfLiability", ctx.get("IsAttorneyDeclineForProfLiability"));
				ctx.put("stateCode", ctx.get("StateCode"));
				Coverage coverage = (Coverage) initlizePOJO(ctx, new Coverage());
				ctx.put("RuleObject", coverage);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), coverage, ruleid, coverage.getDescription(),
						coverage.getTooltip());
			} else {
				ruleid = "checkAttorneyDeclineForProfLiability_Lawyers";
				archiveFlag(ctx, ruleid);
			}
		}

		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("2")) {
			if (ctx.get("IsPersonnelBeSubOfAnyInvest") != null) {
				ruleid = "IsPersonnelBeSubOfAnyInvestRenewalNew_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("isPersonnelBeSubOfAnyInvest", ctx.get("IsPersonnelBeSubOfAnyInvest").toString().trim());
				Coverage coverage = (Coverage) initlizePOJO(ctx, new Coverage());
				ctx.put("RuleObject", coverage);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), coverage, ruleid, coverage.getDescription(),
						coverage.getTooltip());
			} else {
				ruleid = "IsPersonnelBeSubOfAnyInvestRenewalNew_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			if (ctx.get("IsLawyerProfLiabClaimAgntAppl") != null) {
				ruleid = "IsLawyerProfLiabClaimAgntApplRenewalNew_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("isLawyerProfLiabClaimAgntAppl", ctx.get("IsLawyerProfLiabClaimAgntAppl").toString().trim());
				Coverage coverage = (Coverage) initlizePOJO(ctx, new Coverage());
				ctx.put("RuleObject", coverage);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), coverage, ruleid, coverage.getDescription(),
						coverage.getTooltip());
			} else {
				ruleid = "IsLawyerProfLiabClaimAgntApplRenewalNew_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			if (ctx.get("IsAnyActOmmBecomeClaimAgntFirm") != null) {
				ruleid = "IsAnyActOmmBecomeClaimAgntFirmRenewalNew_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("isAnyActOmmBecomeClaimAgntFirm", ctx.get("IsAnyActOmmBecomeClaimAgntFirm").toString().trim());
				Coverage coverage = (Coverage) initlizePOJO(ctx, new Coverage());
				ctx.put("RuleObject", coverage);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), coverage, ruleid, coverage.getDescription(),
						coverage.getTooltip());
			} else {
				ruleid = "IsAnyActOmmBecomeClaimAgntFirmRenewalNew_Lawyers";
				archiveFlag(ctx, ruleid);
			}

		}
	}

	public static void evaluateAttorneysNewApp(Context ctx) throws Exception {
		String ruleid = "";
		List allAttornieslist = SqlResources.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementdroolQueriesgetAllAttorneys", ctx);
		List<Context> ruleSetContextList = new ArrayList<Context>();
		List policyCoverageDetailsList = SqlResources.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementdroolQueriesgetPolicyCoverageDetailsList", ctx);
		if (policyCoverageDetailsList != null && policyCoverageDetailsList instanceof List)
			ctx.putAll((Map) policyCoverageDetailsList.get(0));

		if (allAttornieslist != null && allAttornieslist instanceof List && allAttornieslist.size() > 0) {
			ruleid = "attorneysNotLicensedIndomicile_Lawyers";
			logger.debug("going to debug : " + ruleid);
			for (int i = 0; i < allAttornieslist.size(); i++) {
				Map dataMap = (Map) allAttornieslist.get(i);
				Context newCtx = new Context();
				newCtx.setProject(ctx.getProject());
				if (dataMap.get("LicensedStates") != null && dataMap.get("LicensedStates").toString().length() > 0) {
					if (dataMap.get("LicensedStates").toString().contains(ctx.get("StateCode").toString()))
						ctx.put("isLiscenseIndomicile", "Y");
					else
						ctx.put("isLiscenseIndomicile", "N");
				}
				Firm firm = (Firm) initlizePOJO(ctx, new Firm());
				newCtx.put("RuleObject", firm);
				newCtx = callRuleEngine(newCtx, ruleid);
				if (firm.getDescription() != null && firm.getTooltip() != null && !firm.getDescription().equals("")
						&& !firm.getTooltip().equals("")) {
					newCtx.put("question", firm.getDescription());
					newCtx.put("hint", firm.getTooltip());
				}
				ruleSetContextList.add(newCtx);
			}
			insertRulesToDatabase(ctx, ruleSetContextList, ruleid);
			clearRuleSetContextList(ruleid, ruleSetContextList);
		} else {
			ruleid = "attorneysNotLicensedIndomicile_Lawyers";
			archiveFlag(ctx, ruleid);
		}
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {
			if (allAttornieslist != null && allAttornieslist instanceof List && allAttornieslist.size() > 1) {
				ruleid = "multipleAttorneyAtleastHaveThousandHours_Lawyers";
				logger.debug("going to debug" + ruleid);
				int count = 0;
				for (int i = 0; i < allAttornieslist.size(); i++) {
					Map dataMap = (Map) allAttornieslist.get(i);
					if (dataMap.get("AnnualWorkedHours") != null
							&& Integer.valueOf(dataMap.get("AnnualWorkedHours").toString()) >= 1000)
						count++;
				}
				ctx.put("attorneysCountHave1000Hours", count);
				Firm firm = (Firm) initlizePOJO(ctx, new Firm());
				ctx.put("RuleObject", firm);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), firm, ruleid, firm.getDescription(),
						firm.getTooltip());
			} else {
				ruleid = "multipleAttorneyAtleastHaveThousandHours_Lawyers";
				archiveFlag(ctx, ruleid);

			}
			if (allAttornieslist != null && allAttornieslist.size() == 1) {
				List attornieslistsolo = (List) SqlResources.getSqlMapProcessor(ctx)
						.select("SqlStmts.UserStatementdroolQueriesgetSoloAttorneys", ctx);

				Context attorniesctx = new Context();
				attorniesctx.putAll((Map) attornieslistsolo.get(0));

				ruleid = "soloAtorneyHoursLessThanFiveHundred_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("annualWorkedHours", attorniesctx.get("AnnualWorkedHours"));
				Firm firm = (Firm) initlizePOJO(ctx, new Firm());
				ctx.put("RuleObject", firm);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), firm, ruleid, firm.getDescription(),
						firm.getTooltip());

				int limitSequence = ctx.get("LimitSequence") != null
						? Integer.parseInt(ctx.get("LimitSequence").toString())
						: 0;
				if (limitSequence == 36) {
					ruleid = "ValidateLimitFor2M_Lawyers";
					logger.debug("going to debug : " + ruleid);
					insertRulesToDatabaseTemp(ctx, ruleid, "Requesting 2M in limit.", "Requesting 2M in limit.");
				}

				else {
					ruleid = "ValidateLimitFor2M_Lawyers";
					archiveFlag(ctx, ruleid);
				}

			} else {
				ruleid = "soloAtorneyHoursLessThanFiveHundred_Lawyers";
				archiveFlag(ctx, ruleid);
				ruleid = "ValidateLimitFor2M_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			if (allAttornieslist != null && allAttornieslist instanceof List && allAttornieslist.size() > 1) {
				int count = 0;
				ruleid = "moreThanFiveRateableAttorneys_Lawyers";
				logger.debug("going to debug : " + ruleid);
				for (int i = 0; i < allAttornieslist.size(); i++) {
					Map dataMap = (Map) allAttornieslist.get(i);
					if (dataMap.get("IsNonRatedAttorney") == null
							|| !dataMap.get("IsNonRatedAttorney").toString().equals("Y"))
						count++;
				}
				ctx.put("totalRatedAttornyes", count);
				Firm firm = (Firm) initlizePOJO(ctx, new Firm());
				ctx.put("RuleObject", firm);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), firm, ruleid, firm.getDescription(),
						firm.getTooltip());
			} else {
				ruleid = "moreThanFiveRateableAttorneys_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			// requested by client on JIRA ID PA-764
			/*
			 * if(allAttornieslist!=null && allAttornieslist instanceof List &&
			 * allAttornieslist.size()>1) { ruleid="anyNonRatedAttorney_Lawyers";
			 * logger.debug("going to debug : "+ ruleid); int nonRateAttorneyCount=0; for
			 * (int i = 0; i < allAttornieslist.size(); i++) { Map dataMap = (Map)
			 * allAttornieslist.get(i); if(dataMap.get("IsNonRatedAttorney")!=null &&
			 * dataMap.get("IsNonRatedAttorney").toString().equals("Y"))
			 * nonRateAttorneyCount++; } if(nonRateAttorneyCount>0)
			 * ctx.put("isNonRatedAttorney", "Y"); Firm firm=(Firm)initlizePOJO(ctx, new
			 * Firm()); ctx.put("RuleObject",firm);
			 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm,ruleid,firm.
			 * getDescription(),firm.getTooltip()); } else {
			 * ruleid="anyNonRatedAttorney_Lawyers"; archiveFlag(ctx,ruleid); }
			 */

			List attorneyJoiningDate = SqlResources.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementdroolQueriesgetAllAttorneys", ctx);

		}
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("2")) {
			List previousPolicyKey = (List) SqlResources.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey", ctx);
			ctx.putAll((Map) previousPolicyKey.get(0));
			Context oldPolicyContext = new Context();
			oldPolicyContext.setProject(ctx.getProject());
			oldPolicyContext.put("PolicyKey", ctx.get("PreviousPolicykey"));
			oldPolicyContext.put("VersionSequence", ctx.get("previousVersionSequence"));
			List oldPolicyAllAttornieslist = SqlResources.getSqlMapProcessor(oldPolicyContext)
					.select("SqlStmts.UserStatementdroolQueriesgetAllAttorneys", oldPolicyContext);
			int odlRatedAttorneyCount = 0, currentRatedAttorneyCount = 0;
			List getAddressdetailsForAll = SqlResources.getSqlMapProcessor(oldPolicyContext)
					.select("SqlStmts.UserStatementManualBoQueriesgetAddressdetailsForAll", oldPolicyContext);
			oldPolicyContext.putAll((Map) getAddressdetailsForAll.get(0));

			if (allAttornieslist.size() == 1) {

				for (int i = 0; i < allAttornieslist.size(); i++) {
					Map dataMapOld = (Map) oldPolicyAllAttornieslist.get(i);
					Map dataMap = (Map) allAttornieslist.get(i);
					/*
					 * if(dataMap.get("AttorneyName").toString().equals(dataMapOld.get(
					 * "AttorneyName").toString()) &
					 * dataMap.get("LicensedStates").toString().equals(dataMapOld.get(
					 * "LicensedStates").toString()) &
					 * dataMap.get("AttorneyPriorActDate").toString().equals(dataMapOld.get(
					 * "AttorneyPriorActDate").toString()))
					 * 
					 * {
					 */
					/*
					 * if(!dataMap.get("AnnualWorkedHours").toString().equals(dataMapOld.get(
					 * "AnnualWorkedHours").toString())) {
					 */
					ruleid = "soloAttorneyAndHoursChange_Lawyers";
					logger.debug("going to debug : " + ruleid);
					ctx.put("annualWorkedHours", dataMap.get("AnnualWorkedHours"));
					Firm firm2 = (Firm) initlizePOJO(ctx, new Firm());
					ctx.put("RuleObject", firm2);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), firm2, ruleid, firm2.getDescription(),
							firm2.getTooltip());
					// }

				}
			} else {
				ruleid = "soloAttorneyAndHoursChange_Lawyers";
				archiveFlag(ctx, ruleid);

			}
			int hiringDAteChnageCount = 0;
			int oldListSize = oldPolicyAllAttornieslist.size();
			if (allAttornieslist != null && allAttornieslist.size() > 0) {
				for (int i = 0; i < allAttornieslist.size(); i++) {
					try {
						Map dataMapOld = (Map) oldPolicyAllAttornieslist.get(i);
						Map dataMap = (Map) allAttornieslist.get(i);
						if (dataMap.get("AttorneyName").toString().equals(dataMapOld.get("AttorneyName").toString())
								&& dataMap.get("LicensedStates").toString()
										.equals(dataMapOld.get("LicensedStates").toString())
								&& dataMap.get("AnnualWorkedHours").toString()
										.equals(dataMapOld.get("AnnualWorkedHours").toString()))

						{
							if (!dataMap.get("AttorneyPriorActDate").toString()
									.equals(dataMapOld.get("AttorneyPriorActDate").toString()))
								hiringDAteChnageCount++;

						}
					} catch (Exception e) {
						logger.error("Current and prior attorney list size differs");
						break;
					}
				}

			}
			if (hiringDAteChnageCount > 0) {
				ruleid = "ValidateAttorneyPriorActDate_Lawyers";
				logger.debug("going to debug : " + ruleid);
				insertRulesToDatabaseTemp(ctx, ruleid, " Change in Date of hire, review reason",
						" Change in Date of hire, review reason.");
			} else {
				ruleid = "ValidateAttorneyPriorActDate_Lawyers";
				archiveFlag(ctx, ruleid);
			}

			int isOld = 0, isNewAttorney = 0, attorneyDesgModified = 0, licensedStatesModified = 0,
					annualWorkedHoursModified = 0, isNonRatedAttorneyModified = 0, attorneyCount = 0;
			int nonratedAttorneyCount = 0;
			ctx.put("isLiscenseIndomicile", "Y");
			for (int i = 0; i < allAttornieslist.size(); i++) {
				isOld = 0;
				isNonRatedAttorneyModified = 0;

				Map dataMap = (Map) allAttornieslist.get(i);
				String attorneyName = dataMap.get("AttorneyName") != null ? dataMap.get("AttorneyName").toString() : "";
				String attorneyPriorActDate = dataMap.get("AttorneyPriorActDate") != null
						? dataMap.get("AttorneyPriorActDate").toString()
						: "";
				String isNonRatedAttorney = dataMap.get("IsNonRatedAttorney") != null
						? dataMap.get("IsNonRatedAttorney").toString()
						: "N";

				for (int j = 0; j < oldPolicyAllAttornieslist.size(); j++) {
					Map dataMapOld = (Map) oldPolicyAllAttornieslist.get(j);
					String attorneyNameOld = dataMapOld.get("AttorneyName") != null
							? dataMapOld.get("AttorneyName").toString()
							: "";
					String attorneyPriorActDateOld = dataMapOld.get("AttorneyPriorActDate") != null
							? dataMapOld.get("AttorneyPriorActDate").toString()
							: "";
					String isNonRatedAttorneyOld = dataMapOld.get("IsNonRatedAttorney") != null
							? dataMapOld.get("IsNonRatedAttorney").toString()
							: "N";
					/*
					 * if(attorneyName.equals(attorneyNameOld) &&
					 * attorneyPriorActDate.equals(attorneyPriorActDateOld)) {
					 */
					isOld++;
					if (!dataMap.get("DesignationId").toString().equals(dataMapOld.get("DesignationId").toString()))
						attorneyDesgModified++;
					if (!dataMap.get("LicensedStates").toString().equals(dataMapOld.get("LicensedStates").toString())) {
						licensedStatesModified++;
						List stateCode = (List) SqlResources.getSqlMapProcessor(ctx)
								.select("SqlStmts.UserStatementdroolQueriesgetStateCode", ctx);
						ctx.putAll((Map) stateCode.get(0));

						if (dataMap.get("LicensedStates") != null
								&& dataMap.get("LicensedStates").toString().length() > 0) {
							if (!dataMap.get("LicensedStates").toString().contains(ctx.get("StateCode").toString())) {
								ctx.put("isLiscenseIndomicile", "N");

							}
							/*
							 * else { ctx.put("isLiscenseIndomicile","Y"); }
							 */

							/*
							 * ruleid="addedAttorneyIsLiscenseIndomicile_Lawyers";
							 * logger.debug("going to debug "+ ruleid); Firm firm2=(Firm)initlizePOJO(ctx,
							 * new Firm()); ctx.put("RuleObject",firm2);
							 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm2,ruleid,firm2.
							 * getDescription(),firm2.getTooltip());
							 */
						}
					}
					if (!dataMap.get("AnnualWorkedHours").toString()
							.equals(dataMapOld.get("AnnualWorkedHours").toString())) {
						annualWorkedHoursModified++;
						// attorneyCount++;
					}
					if (!isNonRatedAttorney.equals(isNonRatedAttorneyOld)) {
						isNonRatedAttorneyModified++;

					}
					if (dataMap.get("AnnualWorkedHours") != null
							&& Integer.valueOf(dataMap.get("AnnualWorkedHours").toString()) > 1000)

					{

						attorneyCount++;
					}
					/* } */
				}

				if (isOld == 0)
					isNewAttorney++;

				if (isNewAttorney > 0 || isNonRatedAttorneyModified > 0) {
					if (dataMap.get("AnnualWorkedHours") != null
							&& Integer.valueOf(dataMap.get("AnnualWorkedHours").toString()) >= 1000
							&& "N".equals(dataMap.get("IsNonRatedAttorney") != null
									? dataMap.get("IsNonRatedAttorney").toString()
									: "N"))
						attorneyCount++;

				}
				if (isNonRatedAttorneyModified > 0 || oldPolicyAllAttornieslist.size() != allAttornieslist.size()
						|| isNewAttorney > 0) {
					if ("Y".equals(
							dataMap.get("IsNonRatedAttorney") != null ? dataMap.get("IsNonRatedAttorney").toString()
									: "N"))
						nonratedAttorneyCount++;
				}

			}
			if (isNewAttorney > 0 || isNonRatedAttorneyModified > 0
					|| oldPolicyAllAttornieslist.size() != allAttornieslist.size()) {
				for (int i = 0; i < allAttornieslist.size(); i++) {
					Map dataMap = (Map) allAttornieslist.get(i);
					if (dataMap.get("IsNonRatedAttorney") != null) {
						currentRatedAttorneyCount++;
					}
				}
				ruleid = "ratedAttorneysRenew_Lawyers";
				logger.debug("going to debug " + ruleid);
				ctx.put("currentRatedAttorneyCount", currentRatedAttorneyCount);
				Firm firm2 = (Firm) initlizePOJO(ctx, new Firm());
				ctx.put("RuleObject", firm2);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), firm2, ruleid, firm2.getDescription(),
						firm2.getTooltip());
			} else {
				ruleid = "ratedAttorneysRenew_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			// Requeste dby client to disable this rule. JIRA ID:PA-764
			/*
			 * if(nonratedAttorneyCount>0) {
			 * ctx.put("nonratedAttorneyCount",nonratedAttorneyCount);
			 * ruleid="nonRatedAttorneyAdded_Lawyers"; logger.debug("going to debug "+
			 * ruleid);
			 * 
			 * Firm firm2=(Firm)initlizePOJO(ctx, new Firm()); ctx.put("RuleObject",firm2);
			 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm2,ruleid,firm2.
			 * getDescription(),firm2.getTooltip()); } else {
			 * ruleid="nonRatedAttorneyAdded_Lawyers"; archiveFlag(ctx,ruleid); }
			 */

			if (allAttornieslist.size() > 1 && ctx.get("IsAttorneyAddedDeleted") != null
					&& ctx.get("IsAttorneyAddedDeleted").toString().equals("Y")) {
				if (isNewAttorney > 0 || annualWorkedHoursModified > 0) {
					ruleid = "attorneyHoursChange_Lawyers";
					logger.debug("going to debug : " + ruleid);
					ctx.put("attorneyCount", attorneyCount);
					Firm firm2 = (Firm) initlizePOJO(ctx, new Firm());
					ctx.put("RuleObject", firm2);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), firm2, ruleid, firm2.getDescription(),
							firm2.getTooltip());
				} else {
					ruleid = "attorneyHoursChange_Lawyers";
					archiveFlag(ctx, ruleid);
				}

			} else {
				ruleid = "attorneyHoursChange_Lawyers";
				archiveFlag(ctx, ruleid);
			}

			ruleid = "averageRevenuePerAttorney_Lawyers";
			logger.debug("going to debug : " + ruleid);
			double grossRevenue = Long.valueOf(ctx.get("GrossFeesPaidRenew").toString());
			double totalAttornyes = allAttornieslist.size();
			double averageRevenuePerAttorney = grossRevenue / totalAttornyes;
			ctx.put("averageRevenuePerAttorney", averageRevenuePerAttorney);
			Firm firm2 = (Firm) initlizePOJO(ctx, new Firm());
			ctx.put("RuleObject", firm2);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), firm2, ruleid, firm2.getDescription(),
					firm2.getTooltip());
			List firmPracticeLocationOld = SqlResources.getSqlMapProcessor(oldPolicyContext)
					.select("SqlStmts.firmviewgetPrimaryLocationList1", oldPolicyContext);
			List firmPracticeLocation = SqlResources.getSqlMapProcessor(ctx)
					.select("SqlStmts.firmviewgetPrimaryLocationList1", ctx);
			boolean isMOdified = false;
			if (allAttornieslist != null && allAttornieslist instanceof List && allAttornieslist.size() > 1) {
				ruleid = "multipleAttorneyAtleastHaveThousandHours_Lawyers";
				logger.debug("going to debug" + ruleid);
				int count = 0;
				for (int i = 0; i < allAttornieslist.size(); i++) {
					Map dataMap = (Map) allAttornieslist.get(i);
					if (dataMap.get("AnnualWorkedHours") != null
							&& Integer.valueOf(dataMap.get("AnnualWorkedHours").toString()) >= 1000)
						count++;
				}
				ctx.put("attorneysCountHave1000Hours", count);
				Firm firm = (Firm) initlizePOJO(ctx, new Firm());
				ctx.put("RuleObject", firm);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), firm, ruleid, firm.getDescription(),
						firm.getTooltip());
			} else {
				ruleid = "multipleAttorneyAtleastHaveThousandHours_Lawyers";
				archiveFlag(ctx, ruleid);
			}
		}
	}

	public static void evaluatePlaintiffSuppNewApp(Context ctx) throws Exception {

		String ruleid = "";
		List<Context> ruleSetContextList = new ArrayList<Context>();
		List plantiffSuppList = SqlResources.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementdroolQueriesgetPlantiffSuppList", ctx);
		if (plantiffSuppList != null && plantiffSuppList.size() > 0) {
			ctx.putAll((Map) plantiffSuppList.get(0));
		}
		List areaOfLitigationList = SqlResources.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementdroolQueriesgetAreaOfLitigation", ctx);
		float commercial = 0, wc = 0;
		int medicalMalpractice = 0, productsLiability = 0, toxicTort = 0, other = 0, property = 0, professional = 0,
				totalRevenuePercentage = 0;

		logger.debug("going to debug : " + ruleid);
		int largetCaseSize = 0;
		boolean largestcaseFlag = false;
		for (int i = 0; i < areaOfLitigationList.size(); i++) {
			Map dataMap = (Map) areaOfLitigationList.get(i);
			// ctx.put("plaintiffLargestCaseSize",dataMap.get("LargestCaseSize"));
			largetCaseSize = dataMap.get("LargestCaseSize") != null
					? Integer.parseInt(dataMap.get("LargestCaseSize").toString())
					: 0;
			if (largetCaseSize > 3000000)
				largestcaseFlag = true;
		}
		if (largestcaseFlag) {
			ruleid = "validatePlaintiffLargestCase_Lawyers";
			logger.debug("going to debug : " + ruleid);
			insertRulesToDatabaseTemp(ctx, ruleid, "Largest plaintiff case size above 3M, outside of C & F authority",
					"Large plaintiff case size – needs to be referred to C & F. Provide the next 3 largest case sizes.");
		}

		else {
			ruleid = "validatePlaintiffLargestCase_Lawyers";
			archiveFlag(ctx, ruleid);
		}
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {

			if (areaOfLitigationList != null && areaOfLitigationList.size() > 0) {
				for (int i = 0; i < areaOfLitigationList.size(); i++) {
					/* boolean flag=true; */
					Map dataMap = (Map) areaOfLitigationList.get(i);
					if (dataMap.get("AOLKey").toString().equals("4")) {
						commercial = Float.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
					}
					if (dataMap.get("AOLKey").toString().equals("5")) {
						ctx.put("medicalMalpracticeAOLPercentage", dataMap.get("PercentageOfRevenue"));
						medicalMalpractice = Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
					}
					if (dataMap.get("AOLKey").toString().equals("8")) {
						ctx.put("productsLiabilityAOLPercentage", dataMap.get("PercentageOfRevenue"));
						productsLiability = Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
					}
					if (dataMap.get("AOLKey").toString().equals("11")) {
						ctx.put("toxicTortAOLRevPercentage", dataMap.get("PercentageOfRevenue"));
						ctx.put("toxicTortAOLDefPercentage", dataMap.get("PercentageOfDefense"));
						if (ctx.get("toxicTortAOLRevPercentage") != null
								&& Integer.valueOf(ctx.get("toxicTortAOLRevPercentage").toString()) > 0) {
							toxicTort = Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
						}
						if (ctx.get("toxicTortAOLDefPercentage") != null
								&& Integer.valueOf(ctx.get("toxicTortAOLDefPercentage").toString()) > 0) {
							toxicTort = Integer.valueOf(dataMap.get("PercentageOfDefense").toString().trim());
						}

					}
					if (dataMap.get("AOLKey").toString().equals("12")) {
						wc = Float.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
					}
					if (dataMap.get("AOLKey").toString().equals("13")) {
						int oRevenue = dataMap.get("PercentageOfRevenue") != null
								? Integer.parseInt(dataMap.get("PercentageOfRevenue").toString())
								: 0;
						int oDefense = dataMap.get("PercentageOfDefense") != null
								? Integer.parseInt(dataMap.get("PercentageOfDefense").toString())
								: 0;
						if (oRevenue > 0) {
							ctx.put("OtherAOLPercentage", oRevenue);
							other = oRevenue;
						}
						if (oDefense > 0) {
							ctx.put("OtherAOLPercentage", oDefense);
							other = oDefense;
						}

					}
					if (dataMap.get("AOLKey").toString().equals("6")) {
						property = Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
					}
					if (dataMap.get("AOLKey").toString().equals("9")) {
						professional = Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
					}
					/*
					 * if(Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim())>0) {
					 * if(Integer.valueOf(dataMap.get("LargestCaseSize").toString().trim())<100000)
					 * { flag=false; break; }
					 * 
					 * 
					 * 
					 * } ctx.put("flagForLargestCase", flag);
					 */
					totalRevenuePercentage = totalRevenuePercentage + (dataMap.get("PercentageOfRevenue") != null
							&& !dataMap.get("PercentageOfRevenue").equals(HtmlConstants.EMPTY)
									? Integer.parseInt(dataMap.get("PercentageOfRevenue").toString())
									: 0);

				}
				if (areaOfLitigationList != null && areaOfLitigationList.size() > 0) {
					for (int i = 0; i < areaOfLitigationList.size(); i++) {
						boolean flag = true;
						Map dataMap = (Map) areaOfLitigationList.get(i);
						if (Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim()) > 0) {
							if (Integer.valueOf(dataMap.get("LargestCaseSize").toString().trim()) < 100000) {
								flag = false;
								ctx.put("flagForLargestCase", flag);
								break;

							}

						}
						ctx.put("flagForLargestCase", flag);
					}
				}
				List areasOfPracticeDataList = SqlResources.getSqlMapProcessor(ctx)
						.select("SqlStmts.UserStatementdroolQueriesgetAopDataNewApp", ctx);
				if (areasOfPracticeDataList != null) {
					for (int i = 0; i < areasOfPracticeDataList.size(); i++) {
						Map dataMap = (Map) areasOfPracticeDataList.get(i);

						if (dataMap.get("AOPKey").toString().equals("18")) {
							ctx.put("isLargestCaseMissing", ctx.get("flagForLargestCase"));
							String plaintiffPercentage = dataMap.get("Percentage").toString();

							ctx.put("plaintiffPercentage", plaintiffPercentage);

							/*
							 * ruleid="missingLargestCase_Lawyers"; logger.debug("going to debug "+ ruleid);
							 * PlaintiffSupp plaintiffSupp=(PlaintiffSupp)initlizePOJO(ctx, new
							 * PlaintiffSupp()); ctx.put("RuleObject",plaintiffSupp);
							 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),plaintiffSupp,ruleid,
							 * plaintiffSupp.getDescription(),plaintiffSupp.getTooltip());
							 */
						}
					}
				}

				if (toxicTort > 0) {
					ruleid = "validateAreaOfLitigation_Lawyers";
					logger.debug("going to debug : " + ruleid);
					ctx.put("otherAol", other);
					ctx.put("medicalMalpracticeAol", medicalMalpractice);
					ctx.put("productsLiabilityAol", productsLiability);
					ctx.put("toxicTortAol", toxicTort);
					PlaintiffSupp plaintiffSupp = (PlaintiffSupp) initlizePOJO(ctx, new PlaintiffSupp());
					ctx.put("RuleObject", plaintiffSupp);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), plaintiffSupp, ruleid,
							plaintiffSupp.getDescription(), plaintiffSupp.getTooltip());
				} else {
					ruleid = "validateAreaOfLitigation_Lawyers";
					archiveFlag(ctx, ruleid);
				}
				if (other > 0) {
					ruleid = "validateOtherPlantiff_Lawyers";
					logger.debug("going to debug : " + ruleid);
					ctx.put("otherAol", other);
					PlaintiffSupp plaintiffSupp = (PlaintiffSupp) initlizePOJO(ctx, new PlaintiffSupp());
					ctx.put("RuleObject", plaintiffSupp);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), plaintiffSupp, ruleid,
							plaintiffSupp.getDescription(), plaintiffSupp.getTooltip());
				} else {
					ruleid = "validateOtherPlantiff_Lawyers";
					archiveFlag(ctx, ruleid);
				}

				/*
				 * float mainPlaintiff=0; if(ctx.get("AOP_Percentage_18")!=null)
				 * mainPlaintiff=Float.valueOf(ctx.get("AOP_Percentage_18").toString())/100;
				 * float result=mainPlaintiff*((commercial/100)+(wc/100));
				 */
				// int difference=Math.round((mainPlaintiff-result)*100);

				int toxic = Integer.valueOf(ctx.get("toxicTortAOLRevPercentage").toString().trim());
				int difference = property + medicalMalpractice + productsLiability + professional + toxic + other;
				ruleid = "plaintiffLitigationAOP_Lawyers";
				logger.debug("going to debug" + ruleid);
				ctx.put("difference", difference);
				ctx.put("stateCode", ctx.get("StateCode"));
				PlaintiffSupp plaintiffSupp = (PlaintiffSupp) initlizePOJO(ctx, new PlaintiffSupp());
				ctx.put("RuleObject", plaintiffSupp);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), plaintiffSupp, ruleid,
						plaintiffSupp.getDescription(), plaintiffSupp.getTooltip());

				ruleid = "validatePlaintiffLargestCase_Lawyers";
				logger.debug("going to debug : " + ruleid);
				for (int i = 0; i < areaOfLitigationList.size(); i++) {
					Map dataMap = (Map) areaOfLitigationList.get(i);
					Context newCtx = new Context();
					newCtx.setProject(ctx.getProject());
					ctx.put("plaintiffLargestCaseSize", dataMap.get("LargestCaseSize"));
					PlaintiffSupp plaintiffSupp1 = (PlaintiffSupp) initlizePOJO(ctx, new PlaintiffSupp());
					newCtx.put("RuleObject", plaintiffSupp1);
					newCtx = callRuleEngine(newCtx, ruleid);
					if (plaintiffSupp1.getDescription() != null && plaintiffSupp1.getTooltip() != null
							&& !plaintiffSupp1.getDescription().equals("") && !plaintiffSupp1.getTooltip().equals("")) {
						newCtx.put("question", plaintiffSupp1.getDescription());
						newCtx.put("hint", plaintiffSupp1.getTooltip());
					}
					ruleSetContextList.add(newCtx);
				}
				insertRulesToDatabase(ctx, ruleSetContextList, ruleid);
				clearRuleSetContextList(ruleid, ruleSetContextList);

				/* largest case size */

			} else {
				ruleid = "validateAreaOfLitigation_Lawyers";
				archiveFlag(ctx, ruleid);
				ruleid = "validatePlaintiffLargestCase_Lawyers";
				archiveFlag(ctx, ruleid);

			}
			/*
			 * if(totalRevenuePercentage>0) {
			 */
			if (ctx.get("IsSettlementAggrementsUsed") != null
					&& ctx.get("IsSettlementAggrementsUsed").toString().equals("N")) {
				ruleid = "validateAuthorizationsForSettlement_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("isSettlementAggrementsUsed", ctx.get("IsSettlementAggrementsUsed"));
				PlaintiffSupp plaintiffSupp = (PlaintiffSupp) initlizePOJO(ctx, new PlaintiffSupp());
				ctx.put("RuleObject", plaintiffSupp);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), plaintiffSupp, ruleid,
						plaintiffSupp.getDescription(), plaintiffSupp.getTooltip());
			} else {
				ruleid = "validateAuthorizationsForSettlement_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			/* rule no:-106 in the past 5 years */
			if (ctx.get("massTortOrClassAction") != null && !ctx.get("massTortOrClassAction").toString().equals("")) {
				ruleid = "massTortOrClassActionNewApp_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("massTortOrClassAction", ctx.get("massTortOrClassAction"));
				LitigationSupplement litigationSup = (LitigationSupplement) initlizePOJO(ctx,
						new LitigationSupplement());
				ctx.put("RuleObject", litigationSup);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), litigationSup, ruleid,
						litigationSup.getDescription(), litigationSup.getTooltip());
			} else {
				ruleid = "massTortOrClassActionNewApp_Lawyers";
				archiveFlag(ctx, ruleid);
			}

			/* rule no :-total njo of plantiff cases */
			List allAttornieslist = SqlResources.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementdroolQueriesgetAllAttorneys", ctx);

			if (allAttornieslist != null && allAttornieslist instanceof List && allAttornieslist.size() > 0) {
				int count = 0;
				for (int i = 0; i < allAttornieslist.size(); i++) {
					Map dataMap = (Map) allAttornieslist.get(i);
					if (dataMap.get("IsNonRatedAttorney") == null
							|| !dataMap.get("IsNonRatedAttorney").toString().equals("Y"))
						count++;
				}
				ctx.put("totalRatedAttornyes", count);

			}

			if (ctx.get("NumberOfInjuryCasesIn12Month") != null
					&& !ctx.get("NumberOfInjuryCasesIn12Month").toString().equals("")) {
				int noOfInjurycases = 0, count = 0;
				ruleid = "numberOfInjuryCasesIn12MonthNewApp_Lawyers";
				logger.debug("going to debug : " + ruleid);
				if (Integer.valueOf(ctx.get("NumberOfInjuryCasesIn12Month").toString()) != null) {
					noOfInjurycases = Integer.valueOf(ctx.get("NumberOfInjuryCasesIn12Month").toString());
				}

				if (Integer.valueOf(ctx.get("totalRatedAttornyes").toString()) != null) {
					count = Integer.valueOf(ctx.get("totalRatedAttornyes").toString());
				}

				// Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim())
				ctx.put("numberOfInjuryCasesIn", noOfInjurycases / count);
				LitigationSupplement litigationSup = (LitigationSupplement) initlizePOJO(ctx,
						new LitigationSupplement());
				ctx.put("RuleObject", litigationSup);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), litigationSup, ruleid,
						litigationSup.getDescription(), litigationSup.getTooltip());
			} else {
				ruleid = "numberOfInjuryCasesIn12MonthNewApp_Lawyers";
				archiveFlag(ctx, ruleid);

			}

			if (ctx.get("PerOfCasesSettledBeforeTrail") != null
					&& !ctx.get("PerOfCasesSettledBeforeTrail").equals(HtmlConstants.EMPTY)) {
				ruleid = "CasesSettledBeforeTrail_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("perOfCasesSettledBeforeTrail", ctx.get("PerOfCasesSettledBeforeTrail"));
				LitigationSupplement plaintiffSupp = (LitigationSupplement) initlizePOJO(ctx,
						new LitigationSupplement());
				ctx.put("RuleObject", plaintiffSupp);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), plaintiffSupp, ruleid,
						plaintiffSupp.getDescription(), plaintiffSupp.getTooltip());
			} else {
				ruleid = "CasesSettledBeforeTrail_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			/*
			 * } else { ruleid="validateAuthorizationsForSettlement_Lawyers";
			 * archiveFlag(ctx,ruleid); ruleid="numberOfInjuryCasesIn12MonthNewApp_Lawyers";
			 * archiveFlag(ctx,ruleid); ruleid="massTortOrClassActionNewApp_Lawyers";
			 * archiveFlag(ctx,ruleid); }
			 */
		}
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("2")) {
			totalRevenuePercentage = 0;
			if (areaOfLitigationList != null && areaOfLitigationList.size() > 0) {
				for (int i = 0; i < areaOfLitigationList.size(); i++) {
					Map dataMap = (Map) areaOfLitigationList.get(i);
					if (dataMap.get("AOLKey").toString().equals("11")) {
						ctx.put("toxicTortAOLPercentage", dataMap.get("PercentageOfRevenue"));
						toxicTort = Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
					}
					if (dataMap.get("AOLKey").toString().equals("13")) {
						ctx.put("OtherAOLPercentage", dataMap.get("PercentageOfRevenue"));
						other = Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
					}
					totalRevenuePercentage = totalRevenuePercentage + (dataMap.get("PercentageOfRevenue") != null
							&& !dataMap.get("PercentageOfRevenue").equals(HtmlConstants.EMPTY)
									? Integer.parseInt(dataMap.get("PercentageOfRevenue").toString())
									: 0);
				}

				if (toxicTort > 0) {
					ruleid = "validateAreaOfLitigationNew_Lawyers";
					logger.debug("going to debug : " + ruleid);
					ctx.put("toxicTortAol", toxicTort);
					PlaintiffSupp plaintiffSupp = (PlaintiffSupp) initlizePOJO(ctx, new PlaintiffSupp());
					ctx.put("RuleObject", plaintiffSupp);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), plaintiffSupp, ruleid,
							plaintiffSupp.getDescription(), plaintiffSupp.getTooltip());
				} else {
					ruleid = "validateAreaOfLitigationNew_Lawyers";
					archiveFlag(ctx, ruleid);
				}
				if (other > 0) {
					ruleid = "validateOtherPlantiff_Lawyers";
					logger.debug("going to debug : " + ruleid);
					ctx.put("otherAol", other);
					PlaintiffSupp plaintiffSupp = (PlaintiffSupp) initlizePOJO(ctx, new PlaintiffSupp());
					ctx.put("RuleObject", plaintiffSupp);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), plaintiffSupp, ruleid,
							plaintiffSupp.getDescription(), plaintiffSupp.getTooltip());
				} else {
					ruleid = "validateOtherPlantiff_Lawyers";
					archiveFlag(ctx, ruleid);
				}

			} else {
				ruleid = "validateAreaOfLitigationNew_Lawyers";
				archiveFlag(ctx, ruleid);
				ruleid = "validateOtherPlantiff_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			List previousPolicyKey = (List) SqlResources.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey", ctx);
			ctx.putAll((Map) previousPolicyKey.get(0));
			Context oldPolicyContext = new Context();
			oldPolicyContext.setProject(ctx.getProject());
			oldPolicyContext.put("PolicyKey", ctx.get("PreviousPolicykey"));
			oldPolicyContext.put("VersionSequence", ctx.get("previousVersionSequence"));
			List oldPolicyPlantifflist = SqlResources.getSqlMapProcessor(oldPolicyContext)
					.select("SqlStmts.UserStatementdroolQueriesgetPlantiffSuppList", oldPolicyContext);
			if (oldPolicyPlantifflist.size() > 0) {
				Map dataMapOld = (Map) oldPolicyPlantifflist.get(0);

				if (dataMapOld.get("massTortOrClassAction") != null) {
					ctx.put("massTortOrClassActionold", dataMapOld.get("massTortOrClassAction"));
				} else {
					ctx.put("massTortOrClassActionold", "");
				}
				if (dataMapOld.get("massTortOrClassAction") != null) {
					ctx.put("IsSettlementAggrementsUsedold", dataMapOld.get("IsSettlementAggrementsUsed"));
				} else {
					ctx.put("IsSettlementAggrementsUsedold", "");
				}

			} else {
				ctx.put("massTortOrClassActionold", "");
				ctx.put("IsSettlementAggrementsUsedold", "");
			}
			/*
			 * if(dataMapOld.get("FLAOPKey").toString().equals("2")) { assistedold=(Integer)
			 * dataMapOld.get("PercentageValue"); }
			 */

			/*
			 * if(totalRevenuePercentage>0) {
			 */
			if (((ctx.get("IsSettlementAggrementsUsed") != null
					&& ctx.get("IsSettlementAggrementsUsed").toString().equals("N"))
					&& (ctx.get("IsSettlementAggrementsUsedold").toString().equals("Y")))
					|| ((ctx.get("IsSettlementAggrementsUsed") != null
							&& ctx.get("IsSettlementAggrementsUsed").toString().equals("Y"))
							&& (ctx.get("IsSettlementAggrementsUsedold").toString().equals("")))) {
				ruleid = "validateAuthorizationsForSettlementRenew_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("isSettlementAggrementsUsed", ctx.get("IsSettlementAggrementsUsed"));
				PlaintiffSupp plaintiffSupp = (PlaintiffSupp) initlizePOJO(ctx, new PlaintiffSupp());
				ctx.put("RuleObject", plaintiffSupp);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), plaintiffSupp, ruleid,
						plaintiffSupp.getDescription(), plaintiffSupp.getTooltip());
			} else {
				ruleid = "validateAuthorizationsForSettlementRenew_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			/* rule no:-106 in the past 5 years */
			if ((ctx.get("massTortOrClassAction") != null && !ctx.get("massTortOrClassAction").toString().equals(""))
					&& !ctx.get("massTortOrClassAction").toString().equals("N")) {
				ruleid = "massTortOrClassActionNewAppRenew_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("massTortOrClassAction", ctx.get("massTortOrClassAction"));
				LitigationSupplement litigationSup = (LitigationSupplement) initlizePOJO(ctx,
						new LitigationSupplement());
				ctx.put("RuleObject", litigationSup);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), litigationSup, ruleid,
						litigationSup.getDescription(), litigationSup.getTooltip());
			} else {
				ruleid = "massTortOrClassActionNewAppRenew_Lawyers";
				archiveFlag(ctx, ruleid);
			}

			/* rule no :-total njo of plantiff cases */
			int numberOfInjuryCasesIn12Month = ctx.get("NumberOfInjuryCasesIn12Month") != null
					? Integer.parseInt(ctx.get("NumberOfInjuryCasesIn12Month").toString())
					: 0;
			if (numberOfInjuryCasesIn12Month > 100) {
				ruleid = "numberOfInjuryCasesIn12MonthNewApp_Lawyers";
				logger.debug("going to debug : " + ruleid);
				// Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim())
				/*
				 * ctx.put("numberOfInjuryCasesIn", ctx.get("NumberOfInjuryCasesIn12Month"));
				 * LitigationSupplement litigationSup=(LitigationSupplement)initlizePOJO(ctx,
				 * new LitigationSupplement()); ctx.put("RuleObject",litigationSup);
				 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),litigationSup,ruleid,
				 * litigationSup.getDescription(),litigationSup.getTooltip());
				 */
				insertRulesToDatabaseTemp(ctx, "numberOfInjuryCasesIn12MonthNewApp_Lawyers",
						"Large number of Litigation cases per attorney.", "No explanation needed.");
			} else {
				ruleid = "numberOfInjuryCasesIn12MonthNewApp_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			/*
			 * } else { ruleid="validateAuthorizationsForSettlementRenew_Lawyers";
			 * archiveFlag(ctx,ruleid); ruleid="massTortOrClassActionNewAppRenew_Lawyers";
			 * archiveFlag(ctx,ruleid); ruleid="numberOfInjuryCasesIn12MonthNewApp_Lawyers";
			 * archiveFlag(ctx,ruleid); }
			 */

		}

	}

	public static void evaluateWillsTrustsEstatesNewApp(Context ctx) throws Exception {
		List<Context> ruleSetContextList = new ArrayList<Context>();
		List willsTrustsEstateDataList = SqlResources.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementdroolQueriesgetWillsTrustsEstateData", ctx);
		List willsEstateServicesList = SqlResources.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementdroolQueriesgetWillsEstateServicesList", ctx);
		String ruleid = "";

		if (ctx.get("IsAttorneyServeAsExecutorTrustee") != null
				&& !ctx.get("IsAttorneyServeAsExecutorTrustee").equals(HtmlConstants.EMPTY)
				&& "Y".equals(ctx.get("IsAttorneyServeAsExecutorTrustee").toString())) {
			ruleid = "IsAttorneyServeAsExecutorTrusteeRule_Lawyers";
			logger.debug("going to debug : " + ruleid);

			insertRulesToDatabaseTemp(ctx, ruleid,
					"Attorney acting as as Personal Representative/Administrator or Trustee.  Review question a and b to determine if they are involved in investment decisions and if a financial advisor is used.",
					"Get additional information on when they do this, for how many clients and size of will/estate/trust.");
		}

		else {
			ruleid = "IsAttorneyServeAsExecutorTrusteeRule_Lawyers";
			archiveFlag(ctx, ruleid);
		}

		if (ctx.get("ActingAsTrustee") != null && !ctx.get("ActingAsTrustee").equals(HtmlConstants.EMPTY)
				&& "Y".equals(ctx.get("ActingAsTrustee").toString())) {
			ruleid = "inPurchaseSaleSecuritiesRule_Lawyers";
			logger.debug("going to debug : " + ruleid);

			insertRulesToDatabaseTemp(ctx, ruleid, "Trustee authority to sell investment vehicles - Decline",
					"Trustee responsibilities include selling investment vehicles is outside of C & F authority – Decline.");
		}

		else {
			ruleid = "inPurchaseSaleSecuritiesRule_Lawyers";
			archiveFlag(ctx, ruleid);
		}

		if (ctx.get("CertifiedInvestmentDecisions") != null
				&& !ctx.get("CertifiedInvestmentDecisions").equals(HtmlConstants.EMPTY)
				&& "N".equals(ctx.get("CertifiedInvestmentDecisions").toString())) {
			ruleid = "CertifiedInvestmentDecisionsRule_Lawyers";
			logger.debug("going to debug : " + ruleid);

			insertRulesToDatabaseTemp(ctx, ruleid,
					"Wills/Estates and Trusts investment decisions made without certified professional.  Outside of C & F authority. ",
					"Wills/Estates and Trusts investment decisions are being made without a certified professional.  Obtain explanation of what they are doing and amount of investments.");
		}

		else {
			ruleid = "CertifiedInvestmentDecisionsRule_Lawyers";
			archiveFlag(ctx, ruleid);
		}

		if (ctx.get("beneficiaryInterestWillsTrust") != null
				&& !ctx.get("beneficiaryInterestWillsTrust").equals(HtmlConstants.EMPTY)
				&& "Y".equals(ctx.get("beneficiaryInterestWillsTrust").toString())) {
			ruleid = "BeneficiaryInterestWillsTrustRule_Lawyers";
			logger.debug("going to debug : " + ruleid);

			insertRulesToDatabaseTemp(ctx, ruleid,
					"Firm has a beneficiary interest in wills/estates/trusts for which they are providing services.",
					"Will/trusts/estates firm has a beneficiary interest. Obtain details on the will/estate/trust, amount and conditions.");
		}

		else {
			ruleid = "BeneficiaryInterestWillsTrustRule_Lawyers";
			archiveFlag(ctx, ruleid);
		}
		LawyersUtils.getWillTrustMattersValued(ctx);
		int numberOfMattersValued_1 = ctx.get("NumberOfMattersValued_1") != null
				&& !ctx.get("NumberOfMattersValued_1").equals(HtmlConstants.EMPTY)
						? Integer.parseInt(ctx.get("NumberOfMattersValued_1").toString())
						: 0;
		int numberOfMattersValued_2 = ctx.get("NumberOfMattersValued_2") != null
				&& !ctx.get("NumberOfMattersValued_2").equals(HtmlConstants.EMPTY)
						? Integer.parseInt(ctx.get("NumberOfMattersValued_2").toString())
						: 0;
		int numberOfMattersValued_3 = ctx.get("NumberOfMattersValued_3") != null
				&& !ctx.get("NumberOfMattersValued_3").equals(HtmlConstants.EMPTY)
						? Integer.parseInt(ctx.get("NumberOfMattersValued_3").toString())
						: 0;
		if (numberOfMattersValued_1 > 10) {
			ruleid = "numberOfMatters1M5M_Lawyers";
			logger.debug("going to debug : " + ruleid);
			RuleEngineUtilityNewApp.insertRulesToDatabaseTemp(ctx, ruleid,
					"Number of will / trusts / estates with value between 1M and 5M greater than 10.",
					"More than 10 will/trusts/estates between 1M – 5M is outside of C & F authority.");

		} else {
			ruleid = "numberOfMatters5MM_Lawyers";
			archiveFlag(ctx, ruleid);
		}
		if (numberOfMattersValued_2 > 5) {
			ruleid = "numberOfMatters5M10M_Lawyers";
			logger.debug("going to debug : " + ruleid);
			RuleEngineUtilityNewApp.insertRulesToDatabaseTemp(ctx, ruleid,
					"Number of will / trusts / estates with value between 5M and 10M greater than 5",
					"More than 5 will/trusts/estates between 5M – 10M is outside of C & F authority.");

		} else {
			ruleid = "numberOfMatters5M10M_Lawyers";
			archiveFlag(ctx, ruleid);
		}
		if (numberOfMattersValued_3 > 0) {
			ruleid = "numberOfMatters10MAndOver_Lawyers";
			logger.debug("going to debug : " + ruleid);
			RuleEngineUtilityNewApp.insertRulesToDatabaseTemp(ctx, ruleid,
					"Will / trusts / estates with value greater than 10M",
					"Will/trusts/estates with value greater than 10M is outside of C & F authority.");

		} else {
			ruleid = "numberOfMatters10MAndOver_Lawyers";
			archiveFlag(ctx, ruleid);
		}

		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {
			com.userbo.AOP.getAopsValues(ctx);
			int willTrustAopPercentage = Integer.parseInt(ctx.get("aopPercentage_24").toString());
			int attorneyCount = Integer.parseInt(ctx.get("attorneyCount").toString());
			int result = (willTrustAopPercentage * 150 * attorneyCount) / 100;
			ruleid = "EstatesHandledInYearNewApp_Lawyers";
			logger.debug("going to debug" + ruleid);
			ctx.put("willTrustPercentageCount", result);
			willsEstateNewApp willsEstate = (willsEstateNewApp) initlizePOJO(ctx, new willsEstateNewApp());
			ctx.put("RuleObject", willsEstate);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), willsEstate, ruleid, willsEstate.getDescription(),
					willsEstate.getTooltip());

			if (willsTrustsEstateDataList != null && willsTrustsEstateDataList.size() > 0) {

				if (willsEstateServicesList != null) {
					for (int i = 0; i < willsEstateServicesList.size(); i++) {
						Map dataMap = (Map) willsEstateServicesList.get(i);

						if (dataMap.get("WESKey").toString().equals("8")) {
							ruleid = "checkAssetProtectionNewApp_Lawyers";
							logger.debug("going to debug" + ruleid);
							ctx.put("assetProtectionValue", dataMap.get("PercentageValue"));
							WillsTrustsEstates willsTrustsEstates3 = (WillsTrustsEstates) initlizePOJO(ctx,
									new WillsTrustsEstates());
							ctx.put("RuleObject", willsTrustsEstates3);
							insertRulesToDatabase(callRuleEngine(ctx, ruleid), willsTrustsEstates3, ruleid,
									willsTrustsEstates3.getDescription(), willsTrustsEstates3.getTooltip());
						}

						if (dataMap.get("WESKey").toString().equals("12")) {
							ruleid = "othersWillsEstateNewApp_Lawyers";
							logger.debug("going to debug" + ruleid);
							ctx.put("otherWillsEstate", dataMap.get("PercentageValue"));
							willsEstate = (willsEstateNewApp) initlizePOJO(ctx, new willsEstateNewApp());
							ctx.put("RuleObject", willsEstate);
							insertRulesToDatabase(callRuleEngine(ctx, ruleid), willsEstate, ruleid,
									willsEstate.getDescription(), willsEstate.getTooltip());
						}
						if (dataMap.get("WESKey").toString().equals("6")) {
							ruleid = "TaxOpinionsWillsEstate_Lawyers";
							logger.debug("going to debug" + ruleid);
							ctx.put("taxOpinionsPercentage", dataMap.get("PercentageValue"));
							willsEstate = (willsEstateNewApp) initlizePOJO(ctx, new willsEstateNewApp());
							ctx.put("RuleObject", willsEstate);
							insertRulesToDatabase(callRuleEngine(ctx, ruleid), willsEstate, ruleid,
									willsEstate.getDescription(), willsEstate.getTooltip());
						}

					}
				}
			} else {

				ruleid = "checkAssetProtectionChecked_Lawyers";
				archiveFlag(ctx, ruleid);
				ruleid = "othersWillsEstateNewApp_Lawyers";
				archiveFlag(ctx, ruleid);

			}

			/* rule no:-106 in the past 5 years */
			if (ctx.get("inPurchaseSaleSecurities") != null
					&& !ctx.get("inPurchaseSaleSecurities").toString().equals("")
					&& ctx.get("IsAttorneyServeAsExecutorTrustee") != null
					&& ctx.get("IsAttorneyServeAsExecutorTrustee").toString().equals("Y")) {
				ruleid = "inPurchaseSaleSecuritiesNewApp_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("inPurchaseSaleSecurities", ctx.get("inPurchaseSaleSecurities"));
				willsEstate = (willsEstateNewApp) initlizePOJO(ctx, new willsEstateNewApp());
				ctx.put("RuleObject", willsEstate);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), willsEstate, ruleid, willsEstate.getDescription(),
						willsEstate.getTooltip());
			} else {
				ruleid = "inPurchaseSaleSecuritiesNewApp_Lawyers";
				archiveFlag(ctx, ruleid);
			}

		}
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("2")) {
			List previousPolicyKey = (List) SqlResources.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey", ctx);
			ctx.putAll((Map) previousPolicyKey.get(0));
			Context oldPolicyContext = new Context();
			oldPolicyContext.setProject(ctx.getProject());
			oldPolicyContext.put("PolicyKey", ctx.get("PreviousPolicykey"));
			oldPolicyContext.put("VersionSequence", ctx.get("previousVersionSequence"));
			List oldPolicywillslist = SqlResources.getSqlMapProcessor(oldPolicyContext)
					.select("SqlStmts.UserStatementdroolQueriesgetWillsTrustsEstateData", oldPolicyContext);
			List oldwillsEstateServiceslist = SqlResources.getSqlMapProcessor(oldPolicyContext)
					.select("SqlStmts.UserStatementdroolQueriesgetWillsEstateServicesList", oldPolicyContext);
			if (oldPolicywillslist != null && oldPolicywillslist.size() > 0) {
				Map dataMapOld = (Map) oldPolicywillslist.get(0);
				String inPurchaseSaleSecuritiesOld = dataMapOld.get("inPurchaseSaleSecurities") != null
						&& !dataMapOld.get("inPurchaseSaleSecurities").equals(HtmlConstants.EMPTY)
								? dataMapOld.get("inPurchaseSaleSecurities").toString()
								: "N";
				ctx.put("inPurchaseSaleSecuritiesOld", inPurchaseSaleSecuritiesOld);
			}

			if (oldwillsEstateServiceslist != null && oldwillsEstateServiceslist.size() > 0) {
				for (int k = 0; k < oldwillsEstateServiceslist.size(); k++) {
					Map dataMapOld = (Map) oldwillsEstateServiceslist.get(k);
					if (dataMapOld.get("WESKey").toString().equals("8")) {
						ctx.put("assetProtectionOldValue",
								dataMapOld.get("PercentageValue") != null ? dataMapOld.get("PercentageValue") : 0);

					}
					if (dataMapOld.get("WESKey").toString().equals("12")) {
						ctx.put("otharWillsOldValue",
								dataMapOld.get("PercentageValue") != null ? dataMapOld.get("PercentageValue") : 0);

						ctx.put("otharWillsDescOldValue",
								dataMapOld.get("WESCommentDesc") != null ? dataMapOld.get("WESCommentDesc") : "nodesc");

					}

				}
			}

			if (willsEstateServicesList != null) {
				for (int i = 0; i < willsEstateServicesList.size(); i++) {
					Map dataMap = (Map) willsEstateServicesList.get(i);

					if (dataMap.get("WESKey").toString().equals("8")) {
						ctx.put("assetProtectionNewValue", dataMap.get("PercentageValue"));

						if (ctx.get("assetProtectionOldValue") == null) {
							ctx.put("assetProtectionOldValue", 0);
						}

						if (ctx.get("assetProtectionNewValue") != null) {
							ruleid = "checkAssetProtectionRenew_Lawyers";
							logger.debug("going to debug : " + ruleid);
							ctx.put("assetProtectionNew",
									Integer.valueOf(ctx.get("assetProtectionNewValue").toString()));
							ctx.put("assetProtectionPrior",
									Integer.valueOf(ctx.get("assetProtectionOldValue").toString()));

							willsEstateNewApp willsEstate = (willsEstateNewApp) initlizePOJO(ctx,
									new willsEstateNewApp());
							ctx.put("RuleObject", willsEstate);
							insertRulesToDatabase(callRuleEngine(ctx, ruleid), willsEstate, ruleid,
									willsEstate.getDescription(), willsEstate.getTooltip());
						} else {
							ruleid = "checkAssetProtectionRenew_Lawyers";
							archiveFlag(ctx, ruleid);
						}
					}
					if (dataMap.get("WESKey").toString().equals("12")) {
						ctx.put("otherWillsNewValue", dataMap.get("PercentageValue"));
						ctx.put("otherWillsDescNewValue", dataMap.get("WESCommentDesc"));
						if (ctx.get("otharWillsOldValue") == null) {
							ctx.put("otharWillsOldValue", 0);
						}
						if (ctx.get("otherWillsNewValue") != null) {
							ruleid = "othersWillsValueRenew_Lawyers";
							logger.debug("going to debug : " + ruleid);
							ctx.put("otherWillsEstate", Integer.valueOf(ctx.get("otherWillsNewValue").toString()));
							ctx.put("otherWillsEstatePrior", Integer.valueOf(ctx.get("otharWillsOldValue").toString()));
							ctx.put("otherDescPrior", ctx.get("otharWillsDescOldValue"));
							ctx.put("otherDescNew", ctx.get("otherWillsDescNewValue"));

							willsEstateNewApp willsEstate = (willsEstateNewApp) initlizePOJO(ctx,
									new willsEstateNewApp());
							ctx.put("RuleObject", willsEstate);
							insertRulesToDatabase(callRuleEngine(ctx, ruleid), willsEstate, ruleid,
									willsEstate.getDescription(), willsEstate.getTooltip());
						} else {
							ruleid = "othersWillsValueRenew_Lawyers";
							archiveFlag(ctx, ruleid);
						}
					}
				}
			}

			if (ctx.get("inPurchaseSaleSecurities") != null
					&& !ctx.get("inPurchaseSaleSecurities").equals(HtmlConstants.EMPTY)
					&& ctx.get("IsAttorneyServeAsExecutorTrustee") != null
					&& !ctx.get("IsAttorneyServeAsExecutorTrustee").equals(HtmlConstants.EMPTY))

			{
				String inPurchaseSaleSecuritiesOld = ctx.get("inPurchaseSaleSecuritiesOld") != null
						&& !ctx.get("inPurchaseSaleSecurities").equals(HtmlConstants.EMPTY)
								? ctx.get("inPurchaseSaleSecuritiesOld").toString()
								: "N";
				if (ctx.get("IsAttorneyServeAsExecutorTrustee").toString().equals("Y")
						&& inPurchaseSaleSecuritiesOld.equals("N")) {

					ruleid = "inPurchaseSaleSecuritiesNewApp_Lawyers";
					logger.debug("going to debug : " + ruleid);
					ctx.put("inPurchaseSaleSecurities", ctx.get("inPurchaseSaleSecurities"));
					willsEstateNewApp willsEstate = (willsEstateNewApp) initlizePOJO(ctx, new willsEstateNewApp());
					ctx.put("RuleObject", willsEstate);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), willsEstate, ruleid,
							willsEstate.getDescription(), willsEstate.getTooltip());
				} else {
					ruleid = "PurchaseSaleSecuritiesRenewal_Lawyers";
					archiveFlag(ctx, ruleid);
				}

			} else {
				ruleid = "PurchaseSaleSecuritiesRenewal_Lawyers";
				archiveFlag(ctx, ruleid);
			}

		}

	}

	public static void evaluateResidentialSupplementNewApp(Context ctx) throws Exception {
		com.userbo.AOP.getRealEstateAopsValues(ctx);
		List getRealEstateResiCommList = SqlResources.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementdroolQueriesgetRealEstateCommListNew", ctx);
		String ruleid = "";
		int totalAttorneyCount = ctx.get("totalAttorneyCount") != null
				&& !ctx.get("totalAttorneyCount").equals(HtmlConstants.EMPTY)
						? Integer.parseInt(ctx.get("totalAttorneyCount").toString())
						: 0;
		int transactionsHandled12MonthsResi = ctx.get("transactionsHandled12MonthsResi") != null
				? Integer.parseInt(ctx.get("transactionsHandled12MonthsResi").toString())
				: 0;
		int transactionsHandled12MonthsComm = ctx.get("transactionsHandled12MonthsComm") != null
				? Integer.parseInt(ctx.get("transactionsHandled12MonthsComm").toString())
				: 0;
		if (transactionsHandled12MonthsResi > 0 || transactionsHandled12MonthsComm > 0) {
			int casePerAttorney = (transactionsHandled12MonthsResi + transactionsHandled12MonthsComm)
					/ totalAttorneyCount;
			if (casePerAttorney > 100) {
				ruleid = "ValidateCasesPerAttorneyRule_Lawyers";
				logger.debug("going to debug : " + ruleid);
				RuleEngineUtilityNewApp.insertRulesToDatabaseTemp(ctx, ruleid, "Large Real Estate Volume",
						"No explanation needed.");
			} else
				archiveFlag(ctx, "ValidateCasesPerAttorneyRule_Lawyers");
		} else {
			ruleid = "ValidateCasesPerAttorneyRule_Lawyers";
			archiveFlag(ctx, ruleid);

		}

		int largestTransaction12MonthsResi = ctx.get("largestTransaction12MonthsResi") != null
				? Integer.parseInt(ctx.get("largestTransaction12MonthsResi").toString())
				: 0;
		int largestTransaction12MonthsComm = ctx.get("largestTransaction12MonthsComm") != null
				? Integer.parseInt(ctx.get("largestTransaction12MonthsComm").toString())
				: 0;
		if (largestTransaction12MonthsResi > 2000000 || largestTransaction12MonthsComm > 2000000) {
			ruleid = "ValidateTransactionAbove2MRule_Lawyers";
			logger.debug("going to debug : " + ruleid);
			RuleEngineUtilityNewApp.insertRulesToDatabaseTemp(ctx, ruleid,
					"Largest real estate transaction above 2M - Refer to C & F",
					"Largest real estate transaction above 2M - Refer to C & F");

		} else {
			ruleid = "ValidateTransactionAbove2MRule_Lawyers";
			archiveFlag(ctx, ruleid);

		}

		int aopCommPercentage_11 = ctx.get("aopCommPercentage_11") != null
				&& !ctx.get("aopCommPercentage_11").equals(HtmlConstants.EMPTY)
						? Integer.parseInt(ctx.get("aopCommPercentage_11").toString())
						: 0;
		int aopResiPercentage_19 = ctx.get("aopResiPercentage_11") != null
				&& !ctx.get("aopResiPercentage_11").equals(HtmlConstants.EMPTY)
						? Integer.parseInt(ctx.get("aopResiPercentage_11").toString())
						: 0;
		ruleid = "checkLandDevploment_Lawyers";
		logger.debug("going to debug" + ruleid);
		if (aopCommPercentage_11 > 0 || aopResiPercentage_19 > 0)
			insertRulesToDatabaseTemp(ctx, ruleid,
					"Real Estate practice includes Land Development and is outside of C & F authority",
					"Requires referral to C & F");

		else {
			ruleid = "checkLandDevploment_Lawyers";
			archiveFlag(ctx, ruleid);
		}
		RealEstateSupplementsNewApp resiSupp = (RealEstateSupplementsNewApp) initlizePOJO(ctx,
				new RealEstateSupplementsNewApp());
		insertRulesToDatabase(callRuleEngine(ctx, ruleid), resiSupp, ruleid, resiSupp.getDescription(),
				resiSupp.getTooltip());

		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {
			com.userbo.AOP.getAopsValues(ctx);
			ruleid = "checkTitleOpinionsNewApp_Lawyers";
			logger.debug("going to debug" + ruleid);
			// Integer.valueOf((String)
			// (dataMapCo.get("PercentageValue")!=null?dataMapCo.get("PercentageValue"):0));
			if (Integer.parseInt(ctx.get("aopCommPercentage_22").toString()) > Integer
					.parseInt(ctx.get("aopCommPercentage_1").toString()))
				ctx.put("titleOpinion", ctx.get("aopCommPercentage_22") != null ? ctx.get("aopCommPercentage_22") : 0);
			// ctx.put("titleOpinion",Integer.valueOf((String)
			// (dataMapCo.get("PercentageValue")!=null?dataMapCo.get("PercentageValue"):0)));
			RealEstateSupplementsNewApp residentialSupplementNew = (RealEstateSupplementsNewApp) initlizePOJO(ctx,
					new RealEstateSupplementsNewApp());
			ctx.put("RuleObject", residentialSupplementNew);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplementNew, ruleid,
					residentialSupplementNew.getDescription(), residentialSupplementNew.getTooltip());

			ruleid = "checkOtherResidentialAop_Lawyers";
			logger.debug("going to debug" + ruleid);
			if (Integer.parseInt(ctx.get("aopCommPercentage_20").toString()) > 0)
				ctx.put("otherAopResidentialPercentage", Integer.valueOf(ctx.get("aopResiPercentage_20").toString()));
			if (Integer.parseInt(ctx.get("aopResiPercentage_20").toString()) > 0)
				ctx.put("otherAopResidentialPercentage", Integer.valueOf(ctx.get("aopResiPercentage_20").toString()));
			ResidentialSupplement residentialSupplement = (ResidentialSupplement) initlizePOJO(ctx,
					new ResidentialSupplement());
			ctx.put("RuleObject", residentialSupplement);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplement, ruleid,
					residentialSupplement.getDescription(), residentialSupplement.getTooltip());

			ruleid = "checkForeclosuresAndLoanWorkoutsAOPResidential_Lawyers";
			logger.debug("going to debug" + ruleid);

			ctx.put("loanResidentialPercentage", ctx.get("aopResiPercentage_19"));
			ctx.put("loanCommPercentage", ctx.get("aopCommPercentage_19"));

			if (ctx.get("loanResidentialPercentage") != null
					&& !ctx.get("loanResidentialPercentage").equals(HtmlConstants.EMPTY))
				ctx.put("loanWorkoutsAopResidentialPercentage", ctx.get("loanResidentialPercentage"));
			if (ctx.get("loanCommPercentage") != null && !ctx.get("loanCommPercentage").equals(HtmlConstants.EMPTY))
				ctx.put("loanWorkoutsAopResidentialPercentage", ctx.get("loanCommPercentage"));

			residentialSupplement = (ResidentialSupplement) initlizePOJO(ctx, new ResidentialSupplement());
			ctx.put("RuleObject", residentialSupplement);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplement, ruleid,
					residentialSupplement.getDescription(), residentialSupplement.getTooltip());

			ctx.put("foreclosuresCommercialPercentage", ctx.get("aopCommPercentage_19"));
			ctx.put("foreclosuresResidentialPercentage", ctx.get("aopResiPercentage_19"));

			ruleid = "TitleOpinionsIndivisual_Lawyers";
			logger.debug("going to debug" + ruleid);
			double realEstateResi = ctx.get("aopPercentage_27") != null
					? Double.parseDouble(ctx.get("aopPercentage_27").toString())
					: 0.0;
			double realEstateComm = ctx.get("aopPercentage_20") != null
					? Double.parseDouble(ctx.get("aopPercentage_20").toString())
					: 0.0;
			double titleResi = ctx.get("aopResiPercentage_22") != null
					? Double.parseDouble(ctx.get("aopResiPercentage_22").toString())
					: 0.0;
			double titleComm = ctx.get("aopCommPercentage_22") != null
					? Double.parseDouble(ctx.get("aopCommPercentage_22").toString())
					: 0.0;
			double result = ((realEstateResi * (titleResi / 100)) + (realEstateComm * (titleComm / 100)));
			ctx.put("titleOpinionResiAop", Math.round(result));
			ctx.put("titleOpinionCommAop", 0);

			RealEstateSupplementsNewApp residentialSupplementNewApp = (RealEstateSupplementsNewApp) initlizePOJO(ctx,
					new RealEstateSupplementsNewApp());
			ctx.put("RuleObject", residentialSupplementNewApp);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplementNewApp, ruleid,
					residentialSupplementNewApp.getDescription(), residentialSupplementNewApp.getTooltip());

			if (Integer.parseInt(ctx.get("aopResiPercentage_1").toString()) > 0) {
				ruleid = "TitleOpinionsSalePurchaseRule_Lawyers";
				logger.debug("going to debug" + ruleid);
				ctx.put("salePurchaseResiAop", ctx.get("aopResiPercentage_1"));
				ctx.put("salePurchaseCommAop", ctx.get("aopCommPercentage_1"));
				residentialSupplementNewApp = (RealEstateSupplementsNewApp) initlizePOJO(ctx,
						new RealEstateSupplementsNewApp());
				ctx.put("RuleObject", residentialSupplementNewApp);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplementNewApp, ruleid,
						residentialSupplementNewApp.getDescription(), residentialSupplementNewApp.getTooltip());
			}
			ruleid = "ForeClosuresRule_Lawyers";
			logger.debug("going to debug" + ruleid);
			residentialSupplement = (ResidentialSupplement) initlizePOJO(ctx, new ResidentialSupplement());
			ctx.put("RuleObject", residentialSupplement);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplement, ruleid,
					residentialSupplement.getDescription(), residentialSupplement.getTooltip());

			ruleid = "AttendclosingsRule_Lawyers";
			logger.debug("going to debug" + ruleid);
			residentialSupplementNewApp = (RealEstateSupplementsNewApp) initlizePOJO(ctx,
					new RealEstateSupplementsNewApp());
			ctx.put("RuleObject", residentialSupplementNewApp);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplementNewApp, ruleid,
					residentialSupplementNewApp.getDescription(), residentialSupplementNewApp.getTooltip());

			ruleid = "SecuringFinancingRule_Lawyers";
			logger.debug("going to debug" + ruleid);
			residentialSupplementNewApp = (RealEstateSupplementsNewApp) initlizePOJO(ctx,
					new RealEstateSupplementsNewApp());
			ctx.put("RuleObject", residentialSupplementNewApp);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplementNewApp, ruleid,
					residentialSupplementNewApp.getDescription(), residentialSupplementNewApp.getTooltip());

			ruleid = "DisclosureFormRule_Lawyers";
			logger.debug("going to debug" + ruleid);
			residentialSupplementNewApp = (RealEstateSupplementsNewApp) initlizePOJO(ctx,
					new RealEstateSupplementsNewApp());
			ctx.put("RuleObject", residentialSupplementNewApp);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplementNewApp, ruleid,
					residentialSupplementNewApp.getDescription(), residentialSupplementNewApp.getTooltip());

			ruleid = "checkTitleOpinionsNewApp_Lawyers";
			logger.debug("going to debug" + ruleid);

			Context oldPolicyContext = null;
			List<Context> ruleSetContextList = new ArrayList<Context>();

			List getRealEstateResiList = SqlResources.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementdroolQueriesgetRealEstateResiList", ctx);
			List getRealEstateCommList = SqlResources.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementdroolQueriesgetRealEstateCommList", ctx);
			/*
			 * List getRealEstateCommList=SqlResources.getSqlMapProcessor(ctx).select(
			 * "SqlStmts.UserStatementdroolQueriesgetRealEstateCommList",ctx);
			 */

			if ((getRealEstateCommList != null && getRealEstateCommList.size() > 0)
					|| (getRealEstateResiCommList != null && getRealEstateResiCommList.size() > 0)) {
				for (int i = 0; i < getRealEstateCommList.size(); i++) {
					Map dataMap = (Map) getRealEstateCommList.get(i);
					Map dataMapOld = null;

					if (dataMap.get("AOPREKey").toString().equals("8")) {
						ctx.put("foreclosuresCommercialPercentage",
								dataMap.get("PercentageValue") != null ? dataMap.get("PercentageValue") : 0);
					}

					if (dataMap.get("AOPREKey").toString().equals("16")) {

						ctx.put("estateSyndicationsResidentialPercentage",
								dataMap.get("PercentageValue") != null ? dataMap.get("PercentageValue") : 0);
						ctx.put("estateSyndicationsCommPercentage",
								dataMap.get("CommercialPercentageValue") != null
										? dataMap.get("CommercialPercentageValue")
										: 0);
						if (ctx.get("estateSyndicationsResidentialPercentage") != null
								&& Integer.valueOf(ctx.get("estateSyndicationsResidentialPercentage").toString()) > 0) {
							ctx.put("estateSyndicationsAopCommercialPercentage",
									dataMap.get("PercentageValue") != null ? dataMap.get("PercentageValue") : 0);
						}
						if (ctx.get("estateSyndicationsCommPercentage") != null
								&& Integer.valueOf(ctx.get("estateSyndicationsCommPercentage").toString()) > 0) {
							ctx.put("estateSyndicationsAopCommercialPercentage",
									dataMap.get("CommercialPercentageValue") != null
											? dataMap.get("CommercialPercentageValue")
											: 0);
						}

					}
					if (dataMap.get("AOPREKey").toString().equals("15")) {
						ctx.put("investmentTrustsPercentage",
								dataMap.get("PercentageValue") != null ? dataMap.get("PercentageValue") : 0);

					}
					if (dataMap.get("AOPREKey").toString().equals("13")) {
						ctx.put("SpeculativeAopResPercentage",
								dataMap.get("PercentageValue") != null ? dataMap.get("PercentageValue") : 0);
						ctx.put("SpeculativeAopCommPercentage",
								dataMap.get("CommercialPercentageValue") != null
										? dataMap.get("CommercialPercentageValue")
										: 0);
						if (ctx.get("SpeculativeAopResPercentage") != null
								&& Integer.valueOf(ctx.get("SpeculativeAopResPercentage").toString()) > 0) {
							ctx.put("SpeculativeAopCommercialPercentage",
									dataMap.get("PercentageValue") != null ? dataMap.get("PercentageValue") : 0);
						}
						if (ctx.get("SpeculativeAopCommPercentage") != null
								&& Integer.valueOf(ctx.get("SpeculativeAopCommPercentage").toString()) > 0) {
							ctx.put("SpeculativeAopCommercialPercentage",
									dataMap.get("CommercialPercentageValue") != null
											? dataMap.get("CommercialPercentageValue")
											: 0);
						}

					}

				}
				if ((ctx.get("investmentTrustsPercentage") != null
						&& Integer.valueOf(ctx.get("investmentTrustsPercentage").toString()) > 0)
						|| (ctx.get("estateSyndicationsAopCommercialPercentage") != null && Integer
								.valueOf(ctx.get("estateSyndicationsAopCommercialPercentage").toString()) > 0)) {
					ruleid = "checkCommercialCombineAop_Lawyers";
					logger.debug("going to debug" + ruleid);
					ctx.put("speculativeAopCommercialPercentage", ctx.get("SpeculativeAopCommercialPercentage"));
					residentialSupplement = (ResidentialSupplement) initlizePOJO(ctx, new ResidentialSupplement());
					ctx.put("RuleObject", residentialSupplement);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplement, ruleid,
							residentialSupplement.getDescription(), residentialSupplement.getTooltip());

				} else {
					ruleid = "checkCommercialCombineAop_Lawyers";
					archiveFlag(ctx, ruleid);
				}
				if (ctx.get("SpeculativeAopCommercialPercentage") != null
						&& Integer.valueOf(ctx.get("SpeculativeAopCommercialPercentage").toString()) > 0) {
					ruleid = "checkSpeculativeAop_Lawyers";
					logger.debug("going to debug" + ruleid);
					ctx.put("speculativeAopCommercialPercentage", ctx.get("SpeculativeAopCommercialPercentage"));
					residentialSupplement = (ResidentialSupplement) initlizePOJO(ctx, new ResidentialSupplement());
					ctx.put("RuleObject", residentialSupplement);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplement, ruleid,
							residentialSupplement.getDescription(), residentialSupplement.getTooltip());

				} else {
					ruleid = "checkSpeculativeAop_Lawyers";
					archiveFlag(ctx, ruleid);
				}
				for (int i = 0; i < getRealEstateResiCommList.size(); i++) {
					Map dataMapCo = (Map) getRealEstateResiCommList.get(i);

					if (dataMapCo.get("AOPREKey").toString().equals("16")) {
						ctx.put("otherCommPercentage",
								dataMapCo.get("CommercialPercentageValue") != null
										? dataMapCo.get("CommercialPercentageValue")
										: 0);
						/*
						 * ctx.put("SpeculativeAopCommercialPercentage",
						 * ctx.get("otherCommPercentage"));
						 */
					}
				}

				if ((ctx.get("foreclosuresCommercialPercentage") != null
						&& Integer.valueOf(ctx.get("foreclosuresCommercialPercentage").toString()) > 0))

				{
					ruleid = "checkForeclosuresAndLoanWorkoutsAOPCommercial_Lawyers";
					logger.debug("going to debug" + ruleid);
					residentialSupplement = (ResidentialSupplement) initlizePOJO(ctx, new ResidentialSupplement());
					ctx.put("RuleObject", residentialSupplement);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplement, ruleid,
							residentialSupplement.getDescription(), residentialSupplement.getTooltip());
				}

			} else {
				ruleid = "checkForeclosuresAndLoanWorkoutsAOPCommercial_Lawyers";
				archiveFlag(ctx, ruleid);
				ruleid = "checkCommercialCombineAop_Lawyers";
				archiveFlag(ctx, ruleid);
			}

			if (ctx.get("transactionsHandled12MonthsResi") != null
					&& Integer.valueOf(ctx.get("transactionsHandled12MonthsResi").toString()) > 0) {
				ruleid = "transactionsHandled12MonthsResiNewApp_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("transactionsHandled12Months",
						Long.parseLong(ctx.get("transactionsHandled12MonthsResi").toString()));
				residentialSupplementNew = (RealEstateSupplementsNewApp) initlizePOJO(ctx,
						new RealEstateSupplementsNewApp());
				ctx.put("RuleObject", residentialSupplementNew);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplementNew, ruleid,
						residentialSupplementNew.getDescription(), residentialSupplementNew.getTooltip());

			} else {
				ruleid = "transactionsHandled12MonthsResiNewApp_Lawyers";
				archiveFlag(ctx, ruleid);
			}

		}
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("2")) {
			List previousPolicyKey = (List) SqlResources.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey", ctx);
			ctx.putAll((Map) previousPolicyKey.get(0));
			Context oldPolicyContext = new Context();
			oldPolicyContext.setProject(ctx.getProject());
			oldPolicyContext.put("PolicyKey", ctx.get("PreviousPolicykey"));
			oldPolicyContext.put("VersionSequence", ctx.get("previousVersionSequence"));
			com.userbo.AOP.getAopsValues(oldPolicyContext);
			List oldRealEstateResiCommlist = SqlResources.getSqlMapProcessor(oldPolicyContext)
					.select("SqlStmts.UserStatementdroolQueriesgetRealEstateCommListNew", oldPolicyContext);

			ctx.put("titleOpinionResiAop",
					ctx.get("aopCommPercentage_11") != null ? ctx.get("aopCommPercentage_11") : 0);
			ResidentialSupplement residentialSupplementNew1 = (ResidentialSupplement) initlizePOJO(ctx,
					new ResidentialSupplement());
			ctx.put("RuleObject", residentialSupplementNew1);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplementNew1, ruleid,
					residentialSupplementNew1.getDescription(), residentialSupplementNew1.getTooltip());

			int sum = 0;
			if (getRealEstateResiCommList != null && getRealEstateResiCommList.size() > 0) {
				for (int i = 0; i < getRealEstateResiCommList.size(); i++) {
					Map dataMapCo = (Map) getRealEstateResiCommList.get(i);

					if (dataMapCo.get("AOPREKey").toString().equals("19")) {

						ruleid = "checkForeclosuresRenewal_Lawyers";
						logger.debug("going to debug" + ruleid);

						ctx.put("loanResidentialPercentage",
								dataMapCo.get("PercentageValue") != null ? dataMapCo.get("PercentageValue") : 0);
						ctx.put("loanCommPercentage",
								dataMapCo.get("CommercialPercentageValue") != null
										? dataMapCo.get("CommercialPercentageValue")
										: 0);
						sum = Integer.valueOf(dataMapCo.get("PercentageValue").toString())
								+ Integer.valueOf(dataMapCo.get("CommercialPercentageValue").toString());
						ctx.put("loanWorkoutsAopResidentialPercentage", sum);
						ResidentialSupplement residentialSupplement = (ResidentialSupplement) initlizePOJO(ctx,
								new ResidentialSupplement());
						ctx.put("RuleObject", residentialSupplement);
						insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplement, ruleid,
								residentialSupplement.getDescription(), residentialSupplement.getTooltip());

					}

					if (dataMapCo.get("AOPREKey").toString().equals("13")) {

						ruleid = "checkSpeculativeRenew_Lawyers";
						logger.debug("going to debug" + ruleid);

						ctx.put("SpeculativeResidePercentage",
								dataMapCo.get("PercentageValue") != null ? dataMapCo.get("PercentageValue") : 0);
						ctx.put("SpeculativeCommPercentage",
								dataMapCo.get("CommercialPercentageValue") != null
										? dataMapCo.get("CommercialPercentageValue")
										: 0);

						if (ctx.get("SpeculativeResidePercentage") != null
								&& Integer.valueOf(ctx.get("SpeculativeResidePercentage").toString()) > 0) {
							ctx.put("speculativeAopCommercialPercentage",
									dataMapCo.get("PercentageValue") != null ? dataMapCo.get("PercentageValue") : 0);

						}
						if (ctx.get("SpeculativeCommPercentage") != null
								&& Integer.valueOf(ctx.get("SpeculativeCommPercentage").toString()) > 0) {
							ctx.put("speculativeAopCommercialPercentage",
									dataMapCo.get("CommercialPercentageValue") != null
											? dataMapCo.get("CommercialPercentageValue")
											: 0);

						}
						ResidentialSupplement residentialSupplement = (ResidentialSupplement) initlizePOJO(ctx,
								new ResidentialSupplement());
						ctx.put("RuleObject", residentialSupplement);
						insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplement, ruleid,
								residentialSupplement.getDescription(), residentialSupplement.getTooltip());

					}

					if (dataMapCo.get("AOPREKey").toString().equals("16")) {

						ruleid = "checkRealEstateRenew_Lawyers";
						logger.debug("going to debug" + ruleid);
						ctx.put("estateResPercentage",
								dataMapCo.get("PercentageValue") != null ? dataMapCo.get("PercentageValue") : 0);
						ctx.put("estateCommPercentage",
								dataMapCo.get("CommercialPercentageValue") != null
										? dataMapCo.get("CommercialPercentageValue")
										: 0);

						if (ctx.get("estateResPercentage") != null
								&& Integer.valueOf(ctx.get("estateResPercentage").toString()) > 0) {
							ctx.put("estateSyndicationsAopCommercialPercentage",
									dataMapCo.get("PercentageValue") != null ? dataMapCo.get("PercentageValue") : 0);

						}
						if (ctx.get("estateCommPercentage") != null
								&& Integer.valueOf(ctx.get("estateCommPercentage").toString()) > 0) {
							ctx.put("estateSyndicationsAopCommercialPercentage",
									dataMapCo.get("CommercialPercentageValue") != null
											? dataMapCo.get("CommercialPercentageValue")
											: 0);

						}
						ResidentialSupplement residentialSupplement = (ResidentialSupplement) initlizePOJO(ctx,
								new ResidentialSupplement());
						ctx.put("RuleObject", residentialSupplement);
						insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplement, ruleid,
								residentialSupplement.getDescription(), residentialSupplement.getTooltip());

					}

					if (oldRealEstateResiCommlist != null && oldRealEstateResiCommlist.size() > 0) {
						for (int k = 0; k < oldRealEstateResiCommlist.size(); k++) {
							Map dataMapOld = (Map) oldRealEstateResiCommlist.get(k);
							if (dataMapOld.get("AOPREKey").toString().equals("12")) {
								ctx.put("eminentDomainlResPercentage",
										dataMapOld.get("PercentageValue") != null ? dataMapOld.get("PercentageValue")
												: 0);
								ctx.put("eminentDomainlCommPercentage",
										dataMapOld.get("CommercialPercentageValue") != null
												? dataMapOld.get("CommercialPercentageValue")
												: 0);
								if (ctx.get("eminentDomainlResPercentage") != null
										&& Integer.valueOf(ctx.get("eminentDomainlResPercentage").toString()) > 0) {
									ctx.put("eminentDomainlResOldPercentage",
											dataMapOld.get("PercentageValue") != null
													? dataMapOld.get("PercentageValue")
													: 0);

								}
								if (ctx.get("eminentDomainlCommPercentage") != null
										&& Integer.valueOf(ctx.get("eminentDomainlCommPercentage").toString()) > 0) {
									ctx.put("eminentDomainlCommOldPercentage",
											dataMapOld.get("CommercialPercentageValue") != null
													? dataMapOld.get("CommercialPercentageValue")
													: 0);

								}

							}
							if (dataMapOld.get("AOPREKey").toString().equals("20")) {
								ctx.put("otherResPercentage",
										dataMapOld.get("PercentageValue") != null ? dataMapOld.get("PercentageValue")
												: 0);
								ctx.put("otherCommPercentage",
										dataMapOld.get("CommercialPercentageValue") != null
												? dataMapOld.get("CommercialPercentageValue")
												: 0);
								if (ctx.get("otherResPercentage") != null
										&& Integer.valueOf(ctx.get("otherResPercentage").toString()) > 0) {
									ctx.put("otherResOldPercentage",
											dataMapOld.get("PercentageValue") != null
													? dataMapOld.get("PercentageValue")
													: 0);
									ctx.put("otherResOldDesc", dataMapOld.get("AOPRECommentDesc"));
									/*
									 * ctx.put("otherResOldPercentage",dataMapOld.get("AOPRECommentDesc")!=null?
									 * dataMapOld.get("AOPRECommentDesc"):0);
									 */

								}
								if (ctx.get("otherCommPercentage") != null
										&& Integer.valueOf(ctx.get("eminentDomainlCommPercentage").toString()) > 0) {
									ctx.put("otherCommOldPercentage",
											dataMapOld.get("CommercialPercentageValue") != null
													? dataMapOld.get("CommercialPercentageValue")
													: 0);
									ctx.put("otherCommOldDesc", dataMapOld.get("AOPRECommCommentDesc"));

								}

							}
							if (dataMapOld.get("AOPREKey").toString().equals("22")) {
								ruleid = "TitleOpinionsIndivisual_Lawyers";
								logger.debug("going to debug" + ruleid);
								double realEstateResi = ctx.get("aopPercentage_27") != null
										? Double.parseDouble(ctx.get("aopPercentage_27").toString())
										: 0.0;
								double realEstateComm = ctx.get("aopPercentage_20") != null
										? Double.parseDouble(ctx.get("aopPercentage_20").toString())
										: 0.0;
								double titleResi = ctx.get("aopResiPercentage_22") != null
										? Double.parseDouble(ctx.get("aopResiPercentage_22").toString())
										: 0.0;
								double titleComm = ctx.get("aopCommPercentage_22") != null
										? Double.parseDouble(ctx.get("aopCommPercentage_22").toString())
										: 0.0;
								double result = ((realEstateResi * (titleResi / 100))
										+ (realEstateComm * (titleComm / 100)));
								/*
								 * ctx.put("titleOpinionResiAop",Math.round(result));
								 * ctx.put("titleOpinionCommAop",0);
								 */

								double realEstateResiOld = oldPolicyContext.get("aopPercentage_27") != null
										? Double.parseDouble(oldPolicyContext.get("aopPercentage_27").toString())
										: 0.0;
								double realEstateCommOld = oldPolicyContext.get("aopPercentage_20") != null
										? Double.parseDouble(oldPolicyContext.get("aopPercentage_20").toString())
										: 0.0;
								double titleResiOld = oldPolicyContext.get("aopResiPercentage_22") != null
										? Double.parseDouble(dataMapOld.get("aopResiPercentage_22").toString())
										: 0.0;
								double titleCommOld = oldPolicyContext.get("aopCommPercentage_22") != null
										? Double.parseDouble(dataMapOld.get("aopCommPercentage_22").toString())
										: 0.0;
								double resultOld = ((realEstateResiOld * (titleResiOld / 100))
										+ (realEstateCommOld * (titleCommOld / 100)));

								if (result > resultOld + 15) {
									ctx.put("titleOpinionResiAop", Math.round(result));
									ctx.put("titleOpinionCommAop", 0);
									RealEstateSupplementsNewApp residentialSupplementNewApp = (RealEstateSupplementsNewApp) initlizePOJO(
											ctx, new RealEstateSupplementsNewApp());
									ctx.put("RuleObject", residentialSupplementNewApp);
									insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplementNewApp,
											ruleid, residentialSupplementNewApp.getDescription(),
											residentialSupplementNewApp.getTooltip());

								}

							}

						}
					}

					/*
					 * if(dataMapCo.get("AOPREKey").toString().equals("12")) {
					 * ctx.put("eminentDomainlResNewPercentage",dataMapCo.get("PercentageValue")!=
					 * null?dataMapCo.get("PercentageValue"):0);
					 * ctx.put("eminentDomainlCommNewPercentage",dataMapCo.get(
					 * "CommercialPercentageValue")!=null?dataMapCo.get("CommercialPercentageValue")
					 * :0); if(ctx.get("eminentDomainlCommOldPercentage")==null) {
					 * ctx.put("eminentDomainlCommOldPercentage",0); }
					 * if(ctx.get("eminentDomainlResOldPercentage")==null) {
					 * ctx.put("eminentDomainlResOldPercentage",0); }
					 * if(((ctx.get("eminentDomainlResNewPercentage")!=null &&
					 * (Integer.valueOf(ctx.get("eminentDomainlResNewPercentage").toString())>0 &&
					 * Integer.valueOf(ctx.get("eminentDomainlResOldPercentage").toString())==0)) ||
					 * ((Integer.valueOf(ctx.get("eminentDomainlResNewPercentage").toString())!=
					 * Integer.valueOf(ctx.get("eminentDomainlResOldPercentage").toString())) &&
					 * (ctx.get("eminentDomainlResNewPercentage")!=null) &&
					 * (Integer.valueOf(ctx.get("eminentDomainlResNewPercentage").toString())>0)))
					 * || ((ctx.get("eminentDomainlCommNewPercentage")!=null &&
					 * (Integer.valueOf(ctx.get("eminentDomainlCommNewPercentage").toString())>0 &&
					 * Integer.valueOf(ctx.get("eminentDomainlCommOldPercentage").toString())==0))
					 * || ((Integer.valueOf(ctx.get("eminentDomainlCommNewPercentage").toString())!=
					 * Integer.valueOf(ctx.get("eminentDomainlCommOldPercentage").toString())) &&
					 * (ctx.get("eminentDomainlCommNewPercentage")!=null) &&
					 * (Integer.valueOf(ctx.get("eminentDomainlCommNewPercentage").toString())>0))))
					 * { ruleid="checkEminentDomainRenew_Lawyers"; logger.debug("going to debug"+
					 * ruleid);
					 * 
					 * 
					 * if(ctx.get("eminentDomainlResNewPercentage")!=null &&
					 * Integer.valueOf(ctx.get("eminentDomainlResNewPercentage").toString())>0) {
					 * ctx.put("eminentDomain",dataMapCo.get("PercentageValue")!=null?dataMapCo.get(
					 * "PercentageValue"):0);
					 * 
					 * } if(ctx.get("eminentDomainlCommNewPercentage")!=null &&
					 * Integer.valueOf(ctx.get("eminentDomainlCommNewPercentage").toString())>0) {
					 * ctx.put("eminentDomain",dataMapCo.get("CommercialPercentageValue")!=null?
					 * dataMapCo.get("CommercialPercentageValue"):0);
					 * 
					 * }
					 * 
					 * RealEstateSupplementsNewApp
					 * residentialSupplementNew=(RealEstateSupplementsNewApp)initlizePOJO(ctx, new
					 * RealEstateSupplementsNewApp());
					 * ctx.put("RuleObject",residentialSupplementNew);
					 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),residentialSupplementNew,
					 * ruleid,residentialSupplementNew.getDescription(),residentialSupplementNew.
					 * getTooltip());
					 * 
					 * } else { ruleid="checkEminentDomainRenew_Lawyers"; archiveFlag(ctx,ruleid); }
					 * 
					 * }
					 */

					if (dataMapCo.get("AOPREKey").toString().equals("20")) {
						ctx.put("otherResNewPercentage",
								dataMapCo.get("PercentageValue") != null ? dataMapCo.get("PercentageValue") : 0);
						ctx.put("otherCommNewPercentage",
								dataMapCo.get("CommercialPercentageValue") != null
										? dataMapCo.get("CommercialPercentageValue")
										: 0);

						ctx.put("otherResNewDesc", ctx.get("AOPREOtherCommentDesc"));
						ctx.put("otherCommNewDesc", ctx.get("AOPRECommOtherCommentDesc"));
						if (ctx.get("otherCommOldPercentage") == null) {
							ctx.put("otherCommOldPercentage", 0);
						}
						if (ctx.get("otherResOldPercentage") == null) {
							ctx.put("otherResOldPercentage", 0);
						}
						if (((ctx.get("otherResNewPercentage") != null
								&& (Integer.valueOf(ctx.get("otherResNewPercentage").toString()) > 0
										&& Integer.valueOf(ctx.get("otherResOldPercentage").toString()) == 0)
								&& (Integer.valueOf(ctx.get("otherResNewPercentage").toString()) != Integer
										.valueOf(ctx.get("otherResOldPercentage").toString())))
								|| (ctx.get("otherResNewDesc") != ctx.get("otherResOldDesc")))
								|| ((ctx.get("otherCommNewPercentage") != null
										&& (Integer.valueOf(ctx.get("otherCommNewPercentage").toString()) > 0
												&& Integer.valueOf(ctx.get("otherCommOldPercentage").toString()) == 0)
										&& (Integer.valueOf(ctx.get("otherCommNewPercentage").toString()) != Integer
												.valueOf(ctx.get("otherCommOldPercentage").toString()))))
								|| (ctx.get("otherCommNewDesc") != ctx.get("otherCommOldDesc"))) {
							ruleid = "otherRealEstateRenew_Lawyers";
							logger.debug("going to debug" + ruleid);

							if (ctx.get("otherResNewPercentage") != null
									&& Integer.valueOf(ctx.get("otherResNewPercentage").toString()) > 0) {
								ctx.put("otherAopResidentialPercentage",
										dataMapCo.get("PercentageValue") != null ? dataMapCo.get("PercentageValue")
												: 0);

							}
							if (ctx.get("otherCommNewPercentage") != null
									&& Integer.valueOf(ctx.get("otherCommNewPercentage").toString()) > 0) {
								ctx.put("otherAopCommercialPercentage",
										dataMapCo.get("CommercialPercentageValue") != null
												? dataMapCo.get("CommercialPercentageValue")
												: 0);

							}
							ctx.put("otherAopResidentialPercentageOld",
									Integer.valueOf(ctx.get("otherResOldPercentage").toString()));
							ctx.put("otherAopCommercialPercentageOld",
									Integer.valueOf(ctx.get("otherCommOldPercentage").toString()));

							ResidentialSupplement residentialSupplement = (ResidentialSupplement) initlizePOJO(ctx,
									new ResidentialSupplement());
							ctx.put("RuleObject", residentialSupplement);
							insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplement, ruleid,
									residentialSupplement.getDescription(), residentialSupplement.getTooltip());
						} else {
							ruleid = "otherRealEstateRenew_Lawyers";
							archiveFlag(ctx, ruleid);
						}
					}
				}
			}
			if ((ctx.get("transactionsHandled12MonthsResi") != null
					&& Integer.valueOf(ctx.get("transactionsHandled12MonthsResi").toString()) > 0)
					|| (ctx.get("transactionsHandled12MonthsComm") != null
							&& Integer.valueOf(ctx.get("transactionsHandled12MonthsComm").toString()) > 0)) {
				List allAttornieslist = SqlResources.getSqlMapProcessor(ctx)
						.select("SqlStmts.UserStatementdroolQueriesgetAllAttorneys", ctx);
				int count = allAttornieslist.size();
				ruleid = "transactionsHandled12MonthsRenewNewApp_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("transactionsHandled12Months", 0);
				if (ctx.get("transactionsHandled12MonthsResi") != null
						&& Integer.valueOf(ctx.get("transactionsHandled12MonthsResi").toString()) > 200) {
					ctx.put("transactionsHandled12Months",
							Long.parseLong(ctx.get("transactionsHandled12MonthsResi").toString()) / count);
				}
				if (ctx.get("transactionsHandled12MonthsComm") != null
						&& Integer.valueOf(ctx.get("transactionsHandled12MonthsComm").toString()) > 200) {
					ctx.put("transactionsHandled12Months",
							Long.parseLong(ctx.get("transactionsHandled12MonthsComm").toString()) / count);
				}
				/*
				 * ctx.put("transactionsHandled12Months",
				 * Long.parseLong(ctx.get("transactionsHandled12MonthsResi").toString()));
				 */
				RealEstateSupplementsNewApp residentialSupplementNew = (RealEstateSupplementsNewApp) initlizePOJO(ctx,
						new RealEstateSupplementsNewApp());
				ctx.put("RuleObject", residentialSupplementNew);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), residentialSupplementNew, ruleid,
						residentialSupplementNew.getDescription(), residentialSupplementNew.getTooltip());

			} else {
				ruleid = "transactionsHandled12MonthsRenewNewApp_Lawyers";
				archiveFlag(ctx, ruleid);
			}
		}
	}

	public static void evaluateUnderwritingInfoNewApp(Context ctx) throws Exception {
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {
			String ruleid = "";
			if (ctx.get("IsPolicyIncludeLateralHireCov") != null
					&& !ctx.get("IsPolicyIncludeLateralHireCov").toString().equals("")) {
				ruleid = "checkPolicyIncludeLateralHireCoverage_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("isPolicyIncludeLateralHireCov", ctx.get("IsPolicyIncludeLateralHireCov"));
				Coverage coverage = (Coverage) initlizePOJO(ctx, new Coverage());
				ctx.put("RuleObject", coverage);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), coverage, ruleid, coverage.getDescription(),
						coverage.getTooltip());
			} else {
				ruleid = "checkPolicyIncludeLateralHireCoverage_Lawyers";
				archiveFlag(ctx, ruleid);
			}

			/* rule no:-42 */
			if (ctx.get("isPolicyExcludeCoverage") != null
					&& !ctx.get("isPolicyExcludeCoverage").toString().equals("")) {
				ruleid = "isFirmCoverageForPreceedorFirmsNewApp_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("isFirmCoverageForPreceedorFirms", ctx.get("isPolicyExcludeCoverage"));
				/* ctx.put("isFirmCoverageForPreceedorFirms", "Y"); */
				UnderwritingInformation underwritingInfo = (UnderwritingInformation) initlizePOJO(ctx,
						new UnderwritingInformation());
				ctx.put("RuleObject", underwritingInfo);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), underwritingInfo, ruleid,
						underwritingInfo.getDescription(), underwritingInfo.getTooltip());
			} else {
				ruleid = "isFirmCoverageForPreceedorFirmsNewApp_Lawyers";
				archiveFlag(ctx, ruleid);
			}

		}
	}

	public static void evaluateTaxSupplementNewApp(Context ctx) throws Exception {
		String ruleid = "";
		Context newCtx = new Context();
		newCtx.setProject(ctx.getProject());
		newCtx.put("PolicyKey", ctx.get("PolicyKey"));
		newCtx.put("VersionSequence", ctx.get("VersionSequence"));
		com.userbo.AOP.getTaxAopsValues(newCtx, "New");
		ctx.putAll(newCtx);

		int policyStatusKey = ctx.get("PolicyStatusKey") != null
				&& !ctx.get("PolicyStatusKey").equals(HtmlConstants.EMPTY)
						? Integer.parseInt(ctx.get("PolicyStatusKey").toString())
						: 0;
		if (policyStatusKey == 1) {
			ruleid = "checkinvestmentCounselorServices_Lawyers";
			logger.debug("going to debug" + ruleid);
			ctx.put("investmentCounselorServices", ctx.get("taxNewAopPercentage_4"));
			Tax taxSupplement = (Tax) initlizePOJO(ctx, new Tax());
			ctx.put("RuleObject", taxSupplement);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), taxSupplement, ruleid, taxSupplement.getDescription(),
					taxSupplement.getTooltip());

			ruleid = "checksubchapterSElections_Lawyers";
			logger.debug("going to debug" + ruleid);
			ctx.put("subchapterSElections", ctx.get("taxNewAopPercentage_5"));
			taxSupplement = (Tax) initlizePOJO(ctx, new Tax());
			ctx.put("RuleObject", taxSupplement);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), taxSupplement, ruleid, taxSupplement.getDescription(),
					taxSupplement.getTooltip());

			ruleid = "checkliquidationofCorporations_Lawyers";
			logger.debug("going to debug" + ruleid);
			ctx.put("liquidationofCorporations", ctx.get("taxNewAopPercentage_6"));
			taxSupplement = (Tax) initlizePOJO(ctx, new Tax());
			ctx.put("RuleObject", taxSupplement);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), taxSupplement, ruleid, taxSupplement.getDescription(),
					taxSupplement.getTooltip());

			ruleid = "opinonTax_Lawyers";
			logger.debug("going to debug" + ruleid);
			ctx.put("opinionsTaxShelters", ctx.get("taxNewAopPercentage_7"));
			taxSupplement = (Tax) initlizePOJO(ctx, new Tax());
			ctx.put("RuleObject", taxSupplement);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), taxSupplement, ruleid, taxSupplement.getDescription(),
					taxSupplement.getTooltip());

			ruleid = "checkprivatePlacementMemoranda_Lawyers";
			logger.debug("going to debug" + ruleid);
			ctx.put("privatePlacementMemoranda", ctx.get("taxNewAopPercentage_8"));
			taxSupplement = (Tax) initlizePOJO(ctx, new Tax());
			ctx.put("RuleObject", taxSupplement);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), taxSupplement, ruleid, taxSupplement.getDescription(),
					taxSupplement.getTooltip());

			ruleid = "checkotherTax_Lawyers";
			logger.debug("going to debug" + ruleid);
			ctx.put("otherTax", ctx.get("taxNewAopPercentage_9"));
			taxSupplement = (Tax) initlizePOJO(ctx, new Tax());
			ctx.put("RuleObject", taxSupplement);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), taxSupplement, ruleid, taxSupplement.getDescription(),
					taxSupplement.getTooltip());
		}
		if (policyStatusKey == 2) {
			List previousPolicyKey = (List) SqlResources.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey", ctx);
			ctx.putAll((Map) previousPolicyKey.get(0));
			Context oldPolicyContext = new Context();
			oldPolicyContext.setProject(ctx.getProject());
			oldPolicyContext.put("PolicyKey", ctx.get("PreviousPolicykey"));
			oldPolicyContext.put("VersionSequence", ctx.get("previousVersionSequence"));
			com.userbo.AOP.getTaxAopsValues(oldPolicyContext, "Old");
			oldPolicyContext.remove("PolicyKey");
			oldPolicyContext.remove("VersionSequence");
			ctx.putAll(oldPolicyContext);

			ruleid = "checkinvestmentCounselorServicesRenew_Lawyers";
			logger.debug("going to debug" + ruleid);
			ctx.put("investmentCounselorServices", ctx.get("taxNewAopPercentage_4"));
			ctx.put("investmentCounselorServicesOld", ctx.get("taxOldAopPercentage_4"));
			Tax taxSupplement = (Tax) initlizePOJO(ctx, new Tax());
			ctx.put("RuleObject", taxSupplement);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), taxSupplement, ruleid, taxSupplement.getDescription(),
					taxSupplement.getTooltip());

			ruleid = "checksubchapterSElectionsRenew_Lawyers";
			logger.debug("going to debug" + ruleid);
			ctx.put("subchapterSElections", ctx.get("taxNewAopPercentage_5"));
			ctx.put("subchapterSElectionsOld", ctx.get("taxOldAopPercentage_5"));
			taxSupplement = (Tax) initlizePOJO(ctx, new Tax());
			ctx.put("RuleObject", taxSupplement);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), taxSupplement, ruleid, taxSupplement.getDescription(),
					taxSupplement.getTooltip());

			ruleid = "checkliquidationofCorporationsRenew_Lawyers";
			logger.debug("going to debug" + ruleid);
			ctx.put("liquidationofCorporations", ctx.get("taxNewAopPercentage_6"));
			ctx.put("liquidationofCorporationsOld", ctx.get("taxOldAopPercentage_6"));
			taxSupplement = (Tax) initlizePOJO(ctx, new Tax());
			ctx.put("RuleObject", taxSupplement);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), taxSupplement, ruleid, taxSupplement.getDescription(),
					taxSupplement.getTooltip());

			ruleid = "opinonTaxRenew_Lawyers";
			logger.debug("going to debug" + ruleid);
			ctx.put("opinionsTaxShelters", ctx.get("taxNewAopPercentage_7"));
			ctx.put("opinionsTaxSheltersOld", ctx.get("taxOldAopPercentage_7"));
			taxSupplement = (Tax) initlizePOJO(ctx, new Tax());
			ctx.put("RuleObject", taxSupplement);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), taxSupplement, ruleid, taxSupplement.getDescription(),
					taxSupplement.getTooltip());

			ruleid = "checkprivatePlacementMemorandaRenew_Lawyers";
			logger.debug("going to debug" + ruleid);
			ctx.put("privatePlacementMemoranda", ctx.get("taxNewAopPercentage_8"));
			ctx.put("privatePlacementMemorandaOld", ctx.get("taxOldAopPercentage_8"));
			taxSupplement = (Tax) initlizePOJO(ctx, new Tax());
			ctx.put("RuleObject", taxSupplement);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), taxSupplement, ruleid, taxSupplement.getDescription(),
					taxSupplement.getTooltip());

			ruleid = "checkotherTaxRenew_Lawyers";
			logger.debug("going to debug" + ruleid);
			ctx.put("otherTax", ctx.get("taxNewAopPercentage_9"));
			ctx.put("otherTaxOld", ctx.get("taxOldAopPercentage_9"));
			taxSupplement = (Tax) initlizePOJO(ctx, new Tax());
			ctx.put("RuleObject", taxSupplement);
			insertRulesToDatabase(callRuleEngine(ctx, ruleid), taxSupplement, ruleid, taxSupplement.getDescription(),
					taxSupplement.getTooltip());

		}
	}

	public static void evaluatePublicExpAjaxNewApp(Context ctx) throws Exception {
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {
			String ruleid = "";
			if (ctx.get("IsFirmHaveClientInEntertainmentInd") != null
					&& ctx.get("IsFirmHaveClientInEntertainmentInd").toString().equals("Y")) {
				if (ctx.get("actAgentForPublicFigures") != null
						&& !ctx.get("actAgentForPublicFigures").toString().equals("")) {
					ruleid = "actAgentForPublicFigures_Lawyers";
					logger.debug("going to debug : " + ruleid);
					ctx.put("actAgentForPublicFigures", ctx.get("actAgentForPublicFigures"));
					PublicFiguresAjaxExpansions publicFig = (PublicFiguresAjaxExpansions) initlizePOJO(ctx,
							new PublicFiguresAjaxExpansions());
					ctx.put("RuleObject", publicFig);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), publicFig, ruleid, publicFig.getDescription(),
							publicFig.getTooltip());
				} else {
					ruleid = "actAgentForPublicFigures_Lawyers";
					archiveFlag(ctx, ruleid);
				}
				if (ctx.get("provideProfessionalContract") != null
						&& !ctx.get("provideProfessionalContract").toString().equals("")) {
					ruleid = "provideProfessionalContract_Lawyers";
					logger.debug("going to debug : " + ruleid);
					ctx.put("provideProfessionalContract", ctx.get("provideProfessionalContract"));
					PublicFiguresAjaxExpansions publicFig = (PublicFiguresAjaxExpansions) initlizePOJO(ctx,
							new PublicFiguresAjaxExpansions());
					ctx.put("RuleObject", publicFig);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), publicFig, ruleid, publicFig.getDescription(),
							publicFig.getTooltip());
				} else {
					ruleid = "provideProfessionalContract_Lawyers";
					archiveFlag(ctx, ruleid);
				}
				if (ctx.get("provideMoneyManagementServices") != null
						&& !ctx.get("provideMoneyManagementServices").toString().equals("")) {
					ruleid = "provideMoneyManagementServices_Lawyers";
					logger.debug("going to debug : " + ruleid);
					ctx.put("provideMoneyManagementServices", ctx.get("provideMoneyManagementServices"));
					PublicFiguresAjaxExpansions publicFig = (PublicFiguresAjaxExpansions) initlizePOJO(ctx,
							new PublicFiguresAjaxExpansions());
					ctx.put("RuleObject", publicFig);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), publicFig, ruleid, publicFig.getDescription(),
							publicFig.getTooltip());
				}

				else {
					ruleid = "provideMoneyManagementServices_Lawyers";
					archiveFlag(ctx, ruleid);
				}
			} else {
				ruleid = "actAgentForPublicFigures_Lawyers";
				archiveFlag(ctx, ruleid);
				ruleid = "provideProfessionalContract_Lawyers";
				archiveFlag(ctx, ruleid);
				ruleid = "provideMoneyManagementServices_Lawyers";
				archiveFlag(ctx, ruleid);

			}
		}
	}

	public static void evaluateFamilyLawNewApp(Context ctx) throws Exception {

		String ruleid = "";
		List<Context> ruleSetContextList = new ArrayList<Context>();
		/*
		 * List plantiffSuppList = SqlResources.getSqlMapProcessor(ctx).select(
		 * "SqlStmts.UserStatementdroolQueriesgetPlantiffSuppList",ctx);
		 * if(plantiffSuppList !=null && plantiffSuppList.size()>0) { ctx.putAll((Map)
		 * plantiffSuppList.get(0)); }
		 */
		List familyLawList = SqlResources.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementdroolQueriesgetFamilyLawSuppDetails", ctx);

		int surrogacy = 0, divorce = 0, assisted = 0;
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {
			if (familyLawList != null && familyLawList.size() > 0) {
				for (int i = 0; i < familyLawList.size(); i++) {
					Map dataMap = (Map) familyLawList.get(i);
					if (dataMap.get("FLAOPKey").toString().equals("2")) {
						assisted = (Integer) dataMap.get("PercentageValue");
					}
					if (dataMap.get("FLAOPKey").toString().equals("6")) {

						// surrogacy=Integer.valueOf(dataMap.get("PercentageValue").toString().trim());
						surrogacy = (Integer) dataMap.get("PercentageValue");
					}
					if (dataMap.get("FLAOPKey").toString().equals("5")) {

						divorce = (Integer) dataMap.get("PercentageValue");
					}

				}

				if (assisted > 0 || surrogacy > 0 || divorce > 0) {
					ruleid = "validateFamilyLaw_Lawyers";
					logger.debug("going to debug : " + ruleid);
					ctx.put("assistedReproductive", assisted);
					ctx.put("surrogacy", surrogacy);
					ctx.put("divorceAssetsOver", divorce);
					FamilyLawSupplement familyLaw = (FamilyLawSupplement) initlizePOJO(ctx, new FamilyLawSupplement());
					ctx.put("RuleObject", familyLaw);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), familyLaw, ruleid, familyLaw.getDescription(),
							familyLaw.getTooltip());
				} else {
					ruleid = "validateFamilyLaw_Lawyers";
					archiveFlag(ctx, ruleid);
				}

			} else {
				ruleid = "validateFamilyLaw_Lawyers";
				archiveFlag(ctx, ruleid);

			}

		}
		if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("2")) {
			List previousPolicyKey = (List) SqlResources.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey", ctx);
			ctx.putAll((Map) previousPolicyKey.get(0));
			Context oldPolicyContext = new Context();
			oldPolicyContext.setProject(ctx.getProject());
			oldPolicyContext.put("PolicyKey", ctx.get("PreviousPolicykey"));
			oldPolicyContext.put("VersionSequence", ctx.get("previousVersionSequence"));
			List oldPolicyFamilyLawlist = SqlResources.getSqlMapProcessor(oldPolicyContext)
					.select("SqlStmts.UserStatementdroolQueriesgetFamilyLawSuppDetails", oldPolicyContext);
			int surrogacyold = 0, divorceold = 0, assistedold = 0;
			if (familyLawList != null && familyLawList.size() > 0) {
				for (int i = 0; i < familyLawList.size(); i++) {
					Map dataMap = (Map) familyLawList.get(i);
					if (dataMap.get("FLAOPKey").toString().equals("2")) {
						assisted = (Integer) dataMap.get("PercentageValue");
					}
					if (dataMap.get("FLAOPKey").toString().equals("6")) {

						surrogacy = (Integer) dataMap.get("PercentageValue");
					}
					if (dataMap.get("FLAOPKey").toString().equals("5")) {

						divorce = (Integer) dataMap.get("PercentageValue");
					}

				}
			}
			if (oldPolicyFamilyLawlist != null && oldPolicyFamilyLawlist.size() > 0) {
				for (int i = 0; i < oldPolicyFamilyLawlist.size(); i++) {
					Map dataMapOld = (Map) oldPolicyFamilyLawlist.get(i);
					if (dataMapOld.get("FLAOPKey").toString().equals("2")) {
						assistedold = (Integer) dataMapOld.get("PercentageValue");
					}
					if (dataMapOld.get("FLAOPKey").toString().equals("6")) {

						surrogacyold = (Integer) dataMapOld.get("PercentageValue");
					}
					if (dataMapOld.get("FLAOPKey").toString().equals("5")) {

						divorceold = (Integer) dataMapOld.get("PercentageValue");
					}

				}
			}
			if ((assisted > 0 && assistedold == 0) || (surrogacy > 0 && surrogacyold == 0)
					|| (divorce > 0 && divorceold == 0)) {
				ruleid = "validateFamilyLawRenew_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("assistedReproductive", assisted);
				ctx.put("surrogacy", surrogacy);
				ctx.put("divorceAssetsOver", divorce);
				FamilyLawSupplement familyLaw = (FamilyLawSupplement) initlizePOJO(ctx, new FamilyLawSupplement());
				ctx.put("RuleObject", familyLaw);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), familyLaw, ruleid, familyLaw.getDescription(),
						familyLaw.getTooltip());
			} else {
				ruleid = "validateFamilyLawRenew_Lawyers";
				archiveFlag(ctx, ruleid);
			}

		}

	}

	public static void evaluateFeeSuitwNewApp(Context ctx) throws Exception {

		String ruleid = "";
		List firmInitLawsuits = SqlResources.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementdroolQueriesgetFirmInitLawsuits", ctx);
		int sum = 0;
		int count = 0;
		if (firmInitLawsuits != null && firmInitLawsuits.size() > 0) {
			for (int i = 0; i < firmInitLawsuits.size(); i++) {
				Map dataMap = (Map) firmInitLawsuits.get(i);
				String str = dataMap.get("SuitFilesDateFees").toString();
				int feeSuedDate = Integer.valueOf(str.substring(str.length() - 4, str.length()));
				int feeSuedMonth = Integer.valueOf(str.substring(str.length() - 10, str.length() - 8));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
				SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
				int policyEffectiveYear = Integer.valueOf(sdf.format(new Date(
						new SimpleDateFormat("MM/dd/yyyy").format(new Date(ctx.get("PolicyEffectiveDate").toString()))))
						.toString());
				int policyEffectiveMonth = Integer.valueOf(sdf1.format(new Date(
						new SimpleDateFormat("MM/dd/yyyy").format(new Date(ctx.get("PolicyEffectiveDate").toString()))))
						.toString());
				// new SimpleDateFormat("MM/dd/yyyy").format(new
				// Date(ctx.get("PolicyEffectiveDate").toString()))
				sum = sum + Integer.valueOf(dataMap.get("AmountOfFeesSued").toString());
				if (feeSuedDate == policyEffectiveYear) {
					long daysDifference = DateUtils.calculateDiffInDays(
							new Date(dataMap.get("SuitFilesDateFees").toString()),
							new Date(ctx.get("PolicyEffectiveDate").toString().toString()));
					if (daysDifference < 0)
						count++;

				}
				if (feeSuedDate == (policyEffectiveYear - 1) || feeSuedDate == (policyEffectiveYear - 2))
					count++;
				if (feeSuedDate == (policyEffectiveYear - 3)) {
					if (feeSuedMonth >= policyEffectiveMonth)
						count++;
				}
			}
			/*
			 * ctx.put("amountOfFeesSuedTotal",sum); ctx.put("coutOfFeeSuit", count);
			 * ruleid="checkCountAndAmountOfFeeSuitNewApp_Lawyers";
			 * logger.debug("going to debug : "+ ruleid); Coverage
			 * coverage3=(Coverage)initlizePOJO(ctx, new Coverage());
			 * ctx.put("RuleObject",coverage3);
			 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage3,ruleid,coverage3.
			 * getDescription(),coverage3.getTooltip());
			 */
			if ("1".equals(ctx.get("PolicyStatusKey").toString())) {
				// amountOfFeesSuedTotal > 25000 || coutOfFeeSuit > 9
				if (sum > 25000 || count > 3) {
					ruleid = "checkCountAndAmountOfFeeSuitNB_Lawyers";
					logger.debug("going to debug : " + ruleid);

					insertRulesToDatabaseTemp(ctx, ruleid,
							"More than 1 fee suit per year in the past 3 years or fee suit amount great than $25,000 in any one year",
							"More than 1 fee suit per year in the past 3 years.");
				}

				else
					archiveFlag(ctx, "checkCountAndAmountOfFeeSuitNB_Lawyers");

			}
			if ("2".equals(ctx.get("PolicyStatusKey").toString())) {
				if (sum > 25000 || count > 1) {
					ruleid = "checkCountAndAmountOfFeeSuitRB_Lawyers";
					logger.debug("going to debug : " + ruleid);

					insertRulesToDatabaseTemp(ctx, ruleid, "More than 1 fee suit in the past year",
							"More than 1 fee suit in the past year.  Refer to C & F");
				}

				else
					archiveFlag(ctx, "checkCountAndAmountOfFeeSuitRB_Lawyers");

			}

		}

		/*
		 * ruleid="checkAmountOfFeesSued_Lawyers"; logger.debug("going to debug : "+
		 * ruleid); Coverage coverage2=(Coverage)initlizePOJO(ctx, new Coverage());
		 * ctx.put("RuleObject",coverage2);
		 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage2,ruleid,coverage2.
		 * getDescription(),coverage2.getTooltip());
		 */

		/*
		 * else {
		 * 
		 * ruleid="checkCountOfFeeSuitInPastYears_Lawyers"; archiveFlag(ctx,ruleid);
		 * ruleid="checkAmountOfFeesSued_Lawyers"; archiveFlag(ctx,ruleid);
		 * 
		 * }
		 */

	}

	// BankRuptcy Supplement
	public static void evaluateBankruptcySupplement(Context ctx) throws Exception {
		logger.debug("Going to execute evaluateBankruptcySupplement");
		String ruleid = "";
		try {
			if (ctx.get("representedDebtors") != null && !ctx.get("representedDebtors").equals(HtmlConstants.EMPTY)) {
				ruleid = "RepresentedDebtors_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("representedDebtors", ctx.get("representedDebtors"));
				BankruptcySupplement bankRuptcy = (BankruptcySupplement) initlizePOJO(ctx, new BankruptcySupplement());
				ctx.put("RuleObject", bankRuptcy);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), bankRuptcy, ruleid, bankRuptcy.getDescription(),
						bankRuptcy.getTooltip());
			}

			else {
				ruleid = "RepresentedDebtors_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			if (ctx.get("preBankruptcyServices") != null
					&& !ctx.get("preBankruptcyServices").equals(HtmlConstants.EMPTY)) {
				ruleid = "PreBankruptcyServices_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("preBankruptcyServices", ctx.get("preBankruptcyServices"));
				BankruptcySupplement bankRuptcy = (BankruptcySupplement) initlizePOJO(ctx, new BankruptcySupplement());
				ctx.put("RuleObject", bankRuptcy);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), bankRuptcy, ruleid, bankRuptcy.getDescription(),
						bankRuptcy.getTooltip());
			}

			else {
				ruleid = "PreBankruptcyServices_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			if (ctx.get("disclosureStatementExplaining") != null
					&& !ctx.get("disclosureStatementExplaining").equals(HtmlConstants.EMPTY)) {
				ruleid = "DisclosureStatementExplaining_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("disclosureStatementExplaining", ctx.get("disclosureStatementExplaining"));
				BankruptcySupplement bankRuptcy = (BankruptcySupplement) initlizePOJO(ctx, new BankruptcySupplement());
				ctx.put("RuleObject", bankRuptcy);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), bankRuptcy, ruleid, bankRuptcy.getDescription(),
						bankRuptcy.getTooltip());
			}

			else {
				ruleid = "DisclosureStatementExplaining_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			if (ctx.get("conspicuousStatement") != null
					&& !ctx.get("conspicuousStatement").equals(HtmlConstants.EMPTY)) {
				ruleid = "ConspicuousStatement_Lawyers";
				logger.debug("going to debug : " + ruleid);
				ctx.put("conspicuousStatement", ctx.get("conspicuousStatement"));
				BankruptcySupplement bankRuptcy = (BankruptcySupplement) initlizePOJO(ctx, new BankruptcySupplement());
				ctx.put("RuleObject", bankRuptcy);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), bankRuptcy, ruleid, bankRuptcy.getDescription(),
						bankRuptcy.getTooltip());
			}

			else {
				ruleid = "ConspicuousStatement_Lawyers";
				archiveFlag(ctx, ruleid);
			}
			if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {
			}
			if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("2")) {
			}
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
	}

	public static void evaluateCollectionsSupplement(Context ctx) {
		String ruleid = "";
		logger.debug("Going to execute evaluateCollectionsSupplement");
		try {
			Object obj = SqlResources.getSqlMapProcessor(ctx)
					.findByKey("SqlStmts.UserStatementManualBoQueriesgetCollectionsData", ctx);
			if (obj != null && obj instanceof Map) {
				ctx.putAll((Map) obj);
			}

			if (ctx.get("individualCollections") != null
					&& !ctx.get("individualCollections").equals(HtmlConstants.EMPTY)) {
				ruleid = "IndividualCollectionsRule_Lawyers";
				logger.debug("going to debug : " + ruleid);
				// ctx.put("otherServicesSup", ctx.get("otherServices"));
				CollectionSupplement cpSupplement = (CollectionSupplement) initlizePOJO(ctx,
						new CollectionSupplement());
				ctx.put("RuleObject", cpSupplement);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), cpSupplement, ruleid, cpSupplement.getDescription(),
						cpSupplement.getTooltip());
			}

			else {
				ruleid = "IndividualCollectionsRule_Lawyers";
				archiveFlag(ctx, ruleid);
			}

			if (ctx.get("servicesToPurchasers") != null
					&& !ctx.get("servicesToPurchasers").equals(HtmlConstants.EMPTY)) {
				ruleid = "ServicesToPurchasersRule_Lawyers";
				logger.debug("going to debug : " + ruleid);
				CollectionSupplement cpSupplement = (CollectionSupplement) initlizePOJO(ctx,
						new CollectionSupplement());
				ctx.put("RuleObject", cpSupplement);
				insertRulesToDatabase(callRuleEngine(ctx, ruleid), cpSupplement, ruleid, cpSupplement.getDescription(),
						cpSupplement.getTooltip());
			}

			else {
				ruleid = "ServicesToPurchasersRule_Lawyers";
				archiveFlag(ctx, ruleid);
			}

		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
	}

	public static void evaluateCopyRightTradeMarkSupplement(Context ctx) {
		logger.debug("Going to execute evaluateCopyRightTradeMarkSupplement");
		String ruleid = "";
		try {

			if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {
				if (ctx.get("otherServices") != null && !ctx.get("otherServices").equals(HtmlConstants.EMPTY)) {
					ruleid = "OtherServices_Lawyers";
					logger.debug("going to debug : " + ruleid);
					ctx.put("otherServicesSup", ctx.get("otherServices"));
					CopyRightTrademarkSupplement cpSupplement = (CopyRightTrademarkSupplement) initlizePOJO(ctx,
							new CopyRightTrademarkSupplement());
					ctx.put("RuleObject", cpSupplement);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), cpSupplement, ruleid,
							cpSupplement.getDescription(), cpSupplement.getTooltip());
				}

				else {
					ruleid = "OtherServices_Lawyers";
					archiveFlag(ctx, ruleid);
				}
			}
			if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("2")) {
				List previousPolicyKey = (List) SqlResources.getSqlMapProcessor(ctx)
						.select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey", ctx);
				ctx.putAll((Map) previousPolicyKey.get(0));
				Context oldPolicyContext = new Context();
				oldPolicyContext.setProject(ctx.getProject());
				oldPolicyContext.put("PolicyKey", ctx.get("PreviousPolicykey"));
				oldPolicyContext.put("VersionSequence", ctx.get("previousVersionSequence"));
				List copyTrademarkDetails = SqlResources.getSqlMapProcessor(oldPolicyContext)
						.select("SqlStmts.UserStatementdroolQueriesgetCopyRightTrademarkDetails", oldPolicyContext);

				if (copyTrademarkDetails != null && copyTrademarkDetails instanceof List) {
					oldPolicyContext.putAll((Map) copyTrademarkDetails.get(0));
				}
				String oldOtherServices = oldPolicyContext.get("otherServices") != null
						&& !oldPolicyContext.get("otherServices").equals(HtmlConstants.EMPTY)
								? oldPolicyContext.get("otherServices").toString()
								: "S";
				String otherServices = ctx.get("otherServices") != null
						&& !ctx.get("otherServices").equals(HtmlConstants.EMPTY) ? ctx.get("otherServices").toString()
								: "S";
				if ("N".equalsIgnoreCase(oldOtherServices) && "Y".equalsIgnoreCase(otherServices)) {
					ruleid = "OtherServices_Lawyers";
					logger.debug("going to debug : " + ruleid);
					ctx.put("otherServicesSup", ctx.get("otherServices"));
					CopyRightTrademarkSupplement cpSupplement = (CopyRightTrademarkSupplement) initlizePOJO(ctx,
							new CopyRightTrademarkSupplement());
					ctx.put("RuleObject", cpSupplement);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), cpSupplement, ruleid,
							cpSupplement.getDescription(), cpSupplement.getTooltip());
				}

				else {
					ruleid = "OtherServices_Lawyers";
					archiveFlag(ctx, ruleid);
				}
			}
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}

	}

	public static void evaluateFinancialSupplement(Context ctx) {
		int policyStatusKey = ctx.get("PolicyStatusKey") != null
				? Integer.parseInt(ctx.get("PolicyStatusKey").toString())
				: 0;

		logger.debug("Going to debug Financial Supplement");
		String ruleid = "";
		try {

			if (policyStatusKey == 1) {
				if (ctx.get("equityInterest") != null && !ctx.get("equityInterest").equals(HtmlConstants.EMPTY)) {
					ruleid = "EquityInterestRule_Lawyers";
					logger.debug("going to debug : " + ruleid);
					// ctx.put("equityInterest", ctx.get("equityInterest"));
					FinancialSupplement financialSupp = (FinancialSupplement) initlizePOJO(ctx,
							new FinancialSupplement());
					ctx.put("RuleObject", financialSupp);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), financialSupp, ruleid,
							financialSupp.getDescription(), financialSupp.getTooltip());
				}

				else {
					ruleid = "EquityInterestRule_Lawyers";
					archiveFlag(ctx, ruleid);
				}

				if (ctx.get("servicesRelatedRegulatory") != null
						&& !ctx.get("servicesRelatedRegulatory").equals(HtmlConstants.EMPTY)) {
					ruleid = "ServicesRelatedRegulatoryRule_Lawyers";
					logger.debug("going to debug : " + ruleid);
					// ctx.put("servicesRelatedRegulatory", ctx.get("servicesRelatedRegulatory"));
					FinancialSupplement financialSupp = (FinancialSupplement) initlizePOJO(ctx,
							new FinancialSupplement());
					ctx.put("RuleObject", financialSupp);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), financialSupp, ruleid,
							financialSupp.getDescription(), financialSupp.getTooltip());
				}

				else {
					ruleid = "ServicesRelatedRegulatoryRule_Lawyers";
					archiveFlag(ctx, ruleid);
				}

				if (ctx.get("underRegulatoryReview") != null
						&& !ctx.get("underRegulatoryReview").equals(HtmlConstants.EMPTY)) {
					ruleid = "UnderRegulatoryReviewRule_Lawyers";
					logger.debug("going to debug : " + ruleid);
					// ctx.put("equityInterest", ctx.get("equityInterest"));
					FinancialSupplement financialSupp = (FinancialSupplement) initlizePOJO(ctx,
							new FinancialSupplement());
					ctx.put("RuleObject", financialSupp);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), financialSupp, ruleid,
							financialSupp.getDescription(), financialSupp.getTooltip());
				}

				else {
					ruleid = "UnderRegulatoryReviewRule_Lawyers";
					archiveFlag(ctx, ruleid);
				}

				if (ctx.get("haveBecomeBankruptInsolvent") != null
						&& !ctx.get("haveBecomeBankruptInsolvent").equals(HtmlConstants.EMPTY)) {
					ruleid = "BankruptInsolventRule_Lawyers";
					logger.debug("going to debug : " + ruleid);
					// ctx.put("equityInterest", ctx.get("equityInterest"));
					FinancialSupplement financialSupp = (FinancialSupplement) initlizePOJO(ctx,
							new FinancialSupplement());
					ctx.put("RuleObject", financialSupp);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), financialSupp, ruleid,
							financialSupp.getDescription(), financialSupp.getTooltip());
				}

				else {
					ruleid = "HaveBecomeBankruptInsolventRule_Lawyers";
					archiveFlag(ctx, ruleid);
				}

			}
			// using existing rules for rebewal flow to update
			if (policyStatusKey == 2) {

				List previousPolicyKey = (List) SqlResources.getSqlMapProcessor(ctx)
						.select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey", ctx);
				ctx.putAll((Map) previousPolicyKey.get(0));
				Context oldPolicyContext = new Context();
				oldPolicyContext.setProject(ctx.getProject());
				oldPolicyContext.put("PolicyKey", ctx.get("PreviousPolicykey"));
				oldPolicyContext.put("VersionSequence", ctx.get("previousVersionSequence"));

				List oldFirmlist = SqlResources.getSqlMapProcessor(oldPolicyContext)
						.select("SqlStmts.UserStatementdroolQueriesgetFirmDetails", oldPolicyContext);
				String equityInterestOld = null, servicesRelatedRegulatoryOld = null, underRegulatoryReviewOld = null,
						haveBecomeBankruptInsolventOld = null;

				if (oldFirmlist != null) {
					Map dataMapOld = (Map) oldFirmlist.get(0);

					equityInterestOld = dataMapOld.get("equityInterest") != null
							&& !dataMapOld.get("equityInterest").equals(HtmlConstants.EMPTY)
									? dataMapOld.get("equityInterest").toString()
									: "N";
					servicesRelatedRegulatoryOld = dataMapOld.get("servicesRelatedRegulatory") != null
							&& !dataMapOld.get("servicesRelatedRegulatory").equals(HtmlConstants.EMPTY)
									? dataMapOld.get("servicesRelatedRegulatory").toString()
									: "N";
					underRegulatoryReviewOld = dataMapOld.get("underRegulatoryReview") != null
							&& !dataMapOld.get("underRegulatoryReview").equals(HtmlConstants.EMPTY)
									? dataMapOld.get("underRegulatoryReview").toString()
									: "N";
					haveBecomeBankruptInsolventOld = dataMapOld.get("haveBecomeBankruptInsolvent") != null
							&& !dataMapOld.get("haveBecomeBankruptInsolvent").equals(HtmlConstants.EMPTY)
									? dataMapOld.get("haveBecomeBankruptInsolvent").toString()
									: "N";

				}

				if (ctx.get("equityInterest") != null && !ctx.get("equityInterest").equals(HtmlConstants.EMPTY)) {
					if ("Y".equals(ctx.get("equityInterest").toString()) && equityInterestOld.equals("N")) {
						ruleid = "EquityInterestRule_Lawyers";
						logger.debug("going to debug : " + ruleid);
						// ctx.put("equityInterest", ctx.get("equityInterest"));
						FinancialSupplement financialSupp = (FinancialSupplement) initlizePOJO(ctx,
								new FinancialSupplement());
						ctx.put("RuleObject", financialSupp);
						insertRulesToDatabase(callRuleEngine(ctx, ruleid), financialSupp, ruleid,
								financialSupp.getDescription(), financialSupp.getTooltip());
					} else {
						ruleid = "EquityInterestRule_Lawyers";
						archiveFlag(ctx, ruleid);
					}
				}

				else {
					ruleid = "EquityInterestRule_Lawyers";
					archiveFlag(ctx, ruleid);
				}

				if (ctx.get("servicesRelatedRegulatory") != null
						&& !ctx.get("servicesRelatedRegulatory").equals(HtmlConstants.EMPTY)) {
					if ("Y".equals(ctx.get("servicesRelatedRegulatory").toString())
							&& servicesRelatedRegulatoryOld.equals("N")) {
						ruleid = "ServicesRelatedRegulatoryRule_Lawyers";
						logger.debug("going to debug : " + ruleid);
						// ctx.put("servicesRelatedRegulatory", ctx.get("servicesRelatedRegulatory"));
						FinancialSupplement financialSupp = (FinancialSupplement) initlizePOJO(ctx,
								new FinancialSupplement());
						ctx.put("RuleObject", financialSupp);
						insertRulesToDatabase(callRuleEngine(ctx, ruleid), financialSupp, ruleid,
								financialSupp.getDescription(), financialSupp.getTooltip());
					} else {
						ruleid = "ServicesRelatedRegulatoryRule_Lawyers";
						archiveFlag(ctx, ruleid);
					}

				}

				else {
					ruleid = "ServicesRelatedRegulatoryRule_Lawyers";
					archiveFlag(ctx, ruleid);
				}

				if (ctx.get("underRegulatoryReview") != null
						&& !ctx.get("underRegulatoryReview").equals(HtmlConstants.EMPTY)) {
					if ("Y".equals(ctx.get("underRegulatoryReview").toString())
							&& underRegulatoryReviewOld.equals("N")) {
						ruleid = "UnderRegulatoryReviewRule_Lawyers";
						logger.debug("going to debug : " + ruleid);
						// ctx.put("equityInterest", ctx.get("equityInterest"));
						FinancialSupplement financialSupp = (FinancialSupplement) initlizePOJO(ctx,
								new FinancialSupplement());
						ctx.put("RuleObject", financialSupp);
						insertRulesToDatabase(callRuleEngine(ctx, ruleid), financialSupp, ruleid,
								financialSupp.getDescription(), financialSupp.getTooltip());
					} else {
						ruleid = "UnderRegulatoryReviewRule_Lawyers";
						archiveFlag(ctx, ruleid);
					}
				}

				else {
					ruleid = "UnderRegulatoryReviewRule_Lawyers";
					archiveFlag(ctx, ruleid);
				}

				if (ctx.get("haveBecomeBankruptInsolvent") != null
						&& !ctx.get("haveBecomeBankruptInsolvent").equals(HtmlConstants.EMPTY)) {
					if ("Y".equals(ctx.get("haveBecomeBankruptInsolvent").toString())
							&& haveBecomeBankruptInsolventOld.equals("N")) {
						ruleid = "BankruptInsolventRule_Lawyers";
						logger.debug("going to debug : " + ruleid);
						// ctx.put("equityInterest", ctx.get("equityInterest"));
						FinancialSupplement financialSupp = (FinancialSupplement) initlizePOJO(ctx,
								new FinancialSupplement());
						ctx.put("RuleObject", financialSupp);
						insertRulesToDatabase(callRuleEngine(ctx, ruleid), financialSupp, ruleid,
								financialSupp.getDescription(), financialSupp.getTooltip());
					}

					else {
						ruleid = "HaveBecomeBankruptInsolventRule_Lawyers";
						archiveFlag(ctx, ruleid);
					}

				}

				else {
					ruleid = "HaveBecomeBankruptInsolventRule_Lawyers";
					archiveFlag(ctx, ruleid);
				}

			}

		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
	}

	public static void evaluateGovernmentSupplement(Context ctx) {
		logger.debug("Going to debug Government Supplement");
		String ruleid = "";
		try {

			if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("1")) {
				if (ctx.get("providingBondWork") != null && !ctx.get("providingBondWork").equals(HtmlConstants.EMPTY)) {
					ruleid = "ProvidingBondWorkRule_Lawyers";
					logger.debug("going to debug : " + ruleid);
					// ctx.put("equityInterest", ctx.get("equityInterest"));
					GovernmentSupplement governmentSupp = (GovernmentSupplement) initlizePOJO(ctx,
							new GovernmentSupplement());
					ctx.put("RuleObject", governmentSupp);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), governmentSupp, ruleid,
							governmentSupp.getDescription(), governmentSupp.getTooltip());
				}

				else {
					ruleid = "ProvidingBondWorkRule_Lawyers";
					archiveFlag(ctx, ruleid);
				}

				if (ctx.get("eminentDomainServices") != null
						&& !ctx.get("eminentDomainServices").equals(HtmlConstants.EMPTY)) {
					ruleid = "EminentDomainServicesRule_Lawyers";
					logger.debug("going to debug : " + ruleid);
					// ctx.put("equityInterest", ctx.get("equityInterest"));
					GovernmentSupplement governmentSupp = (GovernmentSupplement) initlizePOJO(ctx,
							new GovernmentSupplement());
					ctx.put("RuleObject", governmentSupp);
					insertRulesToDatabase(callRuleEngine(ctx, ruleid), governmentSupp, ruleid,
							governmentSupp.getDescription(), governmentSupp.getTooltip());
				}

				else {
					ruleid = "EminentDomainServicesRule_Lawyers";
					archiveFlag(ctx, ruleid);
				}

			}
			if (ctx.get("PolicyStatusKey") != null && ctx.get("PolicyStatusKey").toString().equals("2")) {
				List previousPolicyKey = (List) SqlResources.getSqlMapProcessor(ctx)
						.select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey", ctx);
				ctx.putAll((Map) previousPolicyKey.get(0));
				Context oldPolicyContext = new Context();
				oldPolicyContext.setProject(ctx.getProject());
				oldPolicyContext.put("PolicyKey", ctx.get("PreviousPolicykey"));
				oldPolicyContext.put("VersionSequence", ctx.get("previousVersionSequence"));

				List oldFirmlist = SqlResources.getSqlMapProcessor(oldPolicyContext)
						.select("SqlStmts.UserStatementdroolQueriesgetFirmDetails", oldPolicyContext);
				String providingBondWorkOld = null, eminentDomainServicesOld = null;

				if (oldFirmlist != null) {
					Map dataMapOld = (Map) oldFirmlist.get(0);

					providingBondWorkOld = dataMapOld.get("providingBondWork") != null
							&& !dataMapOld.get("providingBondWork").equals(HtmlConstants.EMPTY)
									? dataMapOld.get("providingBondWork").toString()
									: "N";
					eminentDomainServicesOld = dataMapOld.get("eminentDomainServices") != null
							&& !dataMapOld.get("eminentDomainServices").equals(HtmlConstants.EMPTY)
									? dataMapOld.get("eminentDomainServices").toString()
									: "N";

				}

				if (ctx.get("providingBondWork") != null && !ctx.get("providingBondWork").equals(HtmlConstants.EMPTY)) {
					if ("Y".equals(ctx.get("providingBondWork").toString()) && providingBondWorkOld.equals("N")) {

						ruleid = "ProvidingBondWorkRule_Lawyers";
						logger.debug("going to debug : " + ruleid);
						// ctx.put("equityInterest", ctx.get("equityInterest"));
						GovernmentSupplement governmentSupp = (GovernmentSupplement) initlizePOJO(ctx,
								new GovernmentSupplement());
						ctx.put("RuleObject", governmentSupp);
						insertRulesToDatabase(callRuleEngine(ctx, ruleid), governmentSupp, ruleid,
								governmentSupp.getDescription(), governmentSupp.getTooltip());
					} else {
						ruleid = "ProvidingBondWorkRule_Lawyers";
						archiveFlag(ctx, ruleid);
					}
				}

				else {
					ruleid = "ProvidingBondWorkRule_Lawyers";
					archiveFlag(ctx, ruleid);
				}

				if (ctx.get("eminentDomainServices") != null
						&& !ctx.get("eminentDomainServices").equals(HtmlConstants.EMPTY)) {
					if ("Y".equals(ctx.get("eminentDomainServices").toString())
							&& eminentDomainServicesOld.equals("N")) {

						ruleid = "EminentDomainServicesRule_Lawyers";
						logger.debug("going to debug : " + ruleid);
						// ctx.put("equityInterest", ctx.get("equityInterest"));
						GovernmentSupplement governmentSupp = (GovernmentSupplement) initlizePOJO(ctx,
								new GovernmentSupplement());
						ctx.put("RuleObject", governmentSupp);
						insertRulesToDatabase(callRuleEngine(ctx, ruleid), governmentSupp, ruleid,
								governmentSupp.getDescription(), governmentSupp.getTooltip());

					} else {
						ruleid = "EminentDomainServicesRule_Lawyers";
						archiveFlag(ctx, ruleid);
					}

				} else {
					ruleid = "EminentDomainServicesRule_Lawyers";
					archiveFlag(ctx, ruleid);
				}

			}
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
	}

	public static List<Context> initilizeRuleEngineContext(Context ctx, List dataList, String ruleId,
			String dataForRuleEngine) {
		try {
			String[] tokens = getTokens(dataForRuleEngine, ",");
			List<Context> ruleSetContextList = new ArrayList<Context>();
			if (dataList != null && dataList instanceof List) {
				for (int i = 0; i < dataList.size(); i++) {
					Map dataMap = (Map) dataList.get(i);
					Context newCtx = new Context();
					newCtx.setProject(ctx.getProject());
					// newCtx.put(ruleId,dataMap.get(ruleId));
					/*
					 * for(int j=0;j<tokens.length;j++) {
					 * newCtx.put(tokens[j],dataMap.get(tokens[j]));
					 * 
					 * }
					 */
					newCtx = callRuleEngine(ctx, ruleId);
					ruleSetContextList.add(newCtx);
				}

			}
			return ruleSetContextList;
		} catch (Exception e) {
			logger.error("Error Occured while initilizing attorney data  : " + e.getMessage());
			return null;
		}
	}

	public static List<Context> initilizeRuleEngineContext(Context ctx, String ruleId, String dataForRuleEngine) {
		try {
			String[] tokens = getTokens(dataForRuleEngine, ",");
			List<Context> ruleSetContextList = new ArrayList<Context>();
			Context newCtx = new Context();
			newCtx.setProject(ctx.getProject());
			/*
			 * for(int j=0;j<tokens.length;j++) { newCtx.put(tokens[j],ctx.get(tokens[j]));
			 * }
			 */
			newCtx = callRuleEngine(ctx, ruleId);
			ruleSetContextList.add(newCtx);
			return ruleSetContextList;

		} catch (Exception e) {
			logger.error("Error Occured while initilizing attorney data  : " + e.getMessage());
			return null;
		}
	}

	public static List<Context> initilizeRuleEngineContext(Context ctx, String ruleId) {
		try {
			List<Context> ruleSetContextList = new ArrayList<Context>();
			Context newCtx = new Context();
			newCtx.setProject(ctx.getProject());
			newCtx = callRuleEngine(ctx, ruleId);
			ruleSetContextList.add(newCtx);
			return ruleSetContextList;

		} catch (Exception e) {
			logger.error("Error Occured while initilizing attorney data  : " + e.getMessage());
			return null;
		}
	}

	public static Context callRuleEngine(Context ctx, String ruleId) throws Exception {
		RulesResources.getInstance(ctx).findRule("TestRule." + ruleId).execute(ctx, null);
		// RulesResources.getInstance(ctx).findRule("TestRule."+ruleId).executeByObject(ctx,
		// null);
		return ctx;
	}

	public static void insertRulesToDatabase(Context ctx, List ruleSetContextList, String ruleId) {
		String question = "", hint = "";
		if (ruleSetContextList != null && ruleSetContextList.size() > 0) {
			for (int i = 0; i < ruleSetContextList.size(); i++) {
				Map listMap = (Map) ruleSetContextList.get(i);
				if (listMap != null && !listMap.isEmpty()) {

					if (listMap.get("question") != null && listMap.get("hint") != null) {
						if (listMap.get("question") != null && listMap.get("hint") != null)
							question = listMap.get("question").toString();
						hint = listMap.get("hint").toString();
						// break;
					}
				}
			}

			logger.debug("Question= " + question + " Question Hint=" + hint);
			if (!question.equals("") && !hint.equals("")) {
				ctx.put("ruleId", ruleId);
				ctx.put("question", question);
				ctx.put("hint", hint);
				ctx.put("ActionTakenBy", ctx.get("user_id"));
				try {
					new SetParametersForStoredProcedures().setParametersInContext(ctx,
							"PolicyKey,ruleId,question,hint,ActionTakenBy");
					SqlResources.getSqlMapProcessor(ctx).update("rulepolicytransaction.SETFlagsLawyers_p", ctx);
				} catch (Exception e) {
					logger.error("Unexpected error", e);
				}
			} else {
				ctx.put("ruleId", ruleId);
				try {
					new SetParametersForStoredProcedures().setParametersInContext(ctx,
							"PolicyKey,ruleId,question,hint,ActionTakenBy");
					SqlResources.getSqlMapProcessor(ctx).update("rulepolicytransaction.RESETFlagsLawyers_p", ctx);
				} catch (Exception e) {
					logger.error("Unexpected error", e);
				}

			}
		}
	}

	public static void insertRulesToDatabase(Context ctx, Object obj, String ruleId, String description,
			String toolTip) {

		if (description != null && toolTip != null && !description.equals("") && !toolTip.equals("")) {
			ctx.put("ruleId", ruleId);
			ctx.put("question", description);
			ctx.put("hint", toolTip);
			ctx.put("ActionTakenBy", ctx.get("user_id"));
			try {
				new SetParametersForStoredProcedures().setParametersInContext(ctx,
						"PolicyKey,ruleId,question,hint,ActionTakenBy");
				SqlResources.getSqlMapProcessor(ctx).update("rulepolicytransaction.SETFlagsLawyers_p", ctx);
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}
		} else {
			ctx.put("ruleId", ruleId);
			ctx.put("question", "");
			ctx.put("hint", "");
			try {
				new SetParametersForStoredProcedures().setParametersInContext(ctx,
						"PolicyKey,ruleId,question,hint,ActionTakenBy");
				SqlResources.getSqlMapProcessor(ctx).update("rulepolicytransaction.RESETFlagsLawyers_p", ctx);
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}

		}
	}

	public static String[] getTokens(String str, String delim) {
		StringTokenizer stok = new StringTokenizer(str, delim);
		String tokens[] = new String[stok.countTokens()];
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = stok.nextToken();
			tokens[i] = tokens[i].trim();
		}
		return tokens;
	}

	public static void archiveFlag(Context ctx, String ruleId) {
		ctx.put("ruleId", ruleId);
		try {
			new SetParametersForStoredProcedures().setParametersInContext(ctx,
					"PolicyKey,ruleId,question,hint,ActionTakenBy");
			SqlResources.getSqlMapProcessor(ctx).update("rulepolicytransaction.RESETFlagsLawyers_p", ctx);
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
	}

	public static void clearRuleSetContextList(String ruleId, List ruleSetContextList) {
		try {
			if (ruleSetContextList != null)
				ruleSetContextList.clear();

		} catch (Exception e) {
			logger.debug("Rule Id: '" + ruleId + "' is not found on drool rule file.");
			logger.error("Unexpected error", e);
		}
	}

	public static String formatDate(Object obj, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			String dateString = obj.toString();
			Date date = formatter.parse(dateString);
			return formatter.format(date);
		} catch (Exception e) {
			logger.error("Unexpected error", e);
			return null;
		}

	}

	public static Object initlizePOJO(Context ctx, Object obj) {
		String methodName = "";

		try {

			Class c = obj.getClass();
			obj = c.newInstance();
			Method method = null;
			Field[] fields = c.getDeclaredFields();
			for (Field field : fields) {

				if (field.getType().toString().equalsIgnoreCase("class java.lang.Integer")) {
					methodName = field.getName();
					int fieldValue = (ctx.get(field.getName()) != null
							? Integer.valueOf(ctx.get(field.getName()).toString().trim())
							: 0);
					BeanUtils.setProperty(obj, field.getName(), fieldValue);

				}
				if (field.getType().toString().equalsIgnoreCase("class java.lang.String")) {
					methodName = field.getName();
					String fieldValue = (ctx.get(field.getName()) != null ? ctx.get(field.getName()).toString().trim()
							: "");
					BeanUtils.setProperty(obj, field.getName(), fieldValue);

				}
				if (field.getType().toString().equalsIgnoreCase("class java.lang.Long")) {
					methodName = field.getName();
					long fieldValue = (ctx.get(field.getName()) != null
							? Long.valueOf(ctx.get(field.getName()).toString().trim())
							: 0);
					BeanUtils.setProperty(obj, field.getName(), fieldValue);

				}
				if (field.getType().toString().equalsIgnoreCase("class java.lang.Float")) {
					methodName = field.getName();
					float fieldValue = (ctx.get(field.getName()) != null
							? Float.valueOf(ctx.get(field.getName()).toString().trim())
							: 0.0F);
					BeanUtils.setProperty(obj, field.getName(), fieldValue);

				}
				if (field.getType().toString().equalsIgnoreCase("class java.lang.Character")) {
					methodName = field.getName();
					char fieldValue = (ctx.get(field.getName()) != null
							? Character.valueOf(ctx.get(field.getName()).toString().charAt(0))
							: ' ');
					BeanUtils.setProperty(obj, field.getName(), fieldValue);

				}
				if (field.getType().toString().equalsIgnoreCase("class java.lang.Double")) {
					methodName = field.getName();
					double fieldValue = (ctx.get(field.getName()) != null
							? Double.valueOf(ctx.get(field.getName()).toString().trim())
							: 0.0);
					BeanUtils.setProperty(obj, field.getName(), fieldValue);

				}
				/*
				 * if(!methodName.equals("")) {
				 * method=c.getMethod("get"+""+Character.toUpperCase(methodName.charAt(0)) +
				 * methodName.substring(1)); }
				 */
			}

		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
		return obj;
	}

	public static void clearReferralFlags(String[] array, Context ctx, String page) {
		try {
			for (int i = 0; i < array.length; i++)
				archiveFlag(ctx, array[i]);

		} catch (Exception e) {
			logger.error("Error occured while clearing flags for " + page + " :" + e.getMessage());
		}
	}

	public static void evaluateCorporateCommercialBusiness(Context ctx) {
		logger.debug("Going to execute evaluateCorporateCommercialBusiness");
		String ruleid = "";
		try {

			if (ctx.get("IsFirmProvidedLegalServiceCCB") != null
					&& !ctx.get("IsFirmProvidedLegalServiceCCB").equals(HtmlConstants.EMPTY)
					&& "Y".equals(ctx.get("IsFirmProvidedLegalServiceCCB").toString())) {
				ruleid = "IsFirmProvidedLegalServiceCCB_Lawyers";
				logger.debug("going to debug : " + ruleid);

				insertRulesToDatabaseTemp(ctx, ruleid, "Cannabis exposure, review services provided",
						"Cannabis exposure");
			}

			else {
				ruleid = "IsFirmProvidedLegalServiceCCB_Lawyers";
				archiveFlag(ctx, ruleid);
			}

			/* Rule no:-2Firm Name and Address common rule for NB and RB */
			/*
			 * CopyRightTrademarkSupplement
			 * f1=(CopyRightTrademarkSupplement)initlizePOJO(ctx, new
			 * CopyRightTrademarkSupplement());
			 * 
			 * ruleid="LegalServicesBankruptLiquidationRule_Lawyers";
			 * logger.debug("going to debug : "+ ruleid); ctx.put("RuleObject",f1);
			 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),f1,ruleid,f1.getDescription(
			 * ),f1.getTooltip());
			 * 
			 * ruleid="OtherLegalCorporateServicesRule_Lawyers";
			 * logger.debug("going to debug : "+ ruleid); ctx.put("RuleObject",f1);
			 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),f1,ruleid,f1.getDescription(
			 * ),f1.getTooltip());
			 * 
			 * ruleid="PubliclyRenderedServicesRule_Lawyers";
			 * logger.debug("going to debug : "+ ruleid); ctx.put("RuleObject",f1);
			 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),f1,ruleid,f1.getDescription(
			 * ),f1.getTooltip());
			 * 
			 * ruleid="RegardToCorporateClientsRule_Lawyers";
			 * logger.debug("going to debug : "+ ruleid); ctx.put("RuleObject",f1);
			 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),f1,ruleid,f1.getDescription(
			 * ),f1.getTooltip());
			 */
			if (ctx.get("OtherLegalCorporateServices") != null
					&& ctx.get("OtherLegalCorporateServices").toString().equals("Y")) {
				ruleid = "OtherLegalCorporateServicesRule_Lawyers";
				logger.debug("going to debug : " + ruleid);
				RuleEngineUtilityNewApp.insertRulesToDatabaseTemp(ctx, ruleid,
						"Review corporate services that are being provided",
						"Review corporate services that are being provided");

			} else {
				ruleid = "OtherLegalCorporateServicesRule_Lawyers";
				archiveFlag(ctx, ruleid);

			}
			if (ctx.get("PubliclyRenderedServices") != null
					&& ctx.get("PubliclyRenderedServices").toString().equals("Y")) {
				ruleid = "PubliclyRenderedServicesRule_Lawyers";
				logger.debug("going to debug : " + ruleid);
				RuleEngineUtilityNewApp.insertRulesToDatabaseTemp(ctx, ruleid,
						"Providing services to publicly traded companies - Decline",
						"Firms providing services to publicly traded companies are outside of C & F underwriting authority.");

			} else {
				ruleid = "PubliclyRenderedServicesRule_Lawyers";
				archiveFlag(ctx, ruleid);

			}
			if (ctx.get("LegalBusinessRelationshipServices") != null
					&& ctx.get("LegalBusinessRelationshipServices").toString().equals("Y")
					|| ctx.get("AuthorityToDisburseFunds") != null
							&& ctx.get("AuthorityToDisburseFunds").toString().equals("Y")
					|| ctx.get("AcceptCompensation") != null && ctx.get("AcceptCompensation").toString().equals("Y")
					|| ctx.get("LegalServicesSecuritiesPayment") != null
							&& ctx.get("LegalServicesSecuritiesPayment").toString().equals("Y")
					|| ctx.get("PerformDueDiligence") != null
							&& ctx.get("PerformDueDiligence").toString().equals("Y")) {
				ruleid = "RegardToCorporateClientsRule_Lawyers";
				logger.debug("going to debug : " + ruleid);
				RuleEngineUtilityNewApp.insertRulesToDatabaseTemp(ctx, ruleid,
						"Firm Corporate commercial practices outside of the common compensation or relationship practices.",
						"Ask for additional information on the items where they answered yes, when it happened, how often and with what client.");

			} else {
				ruleid = "RegardToCorporateClientsRule_Lawyers";
				archiveFlag(ctx, ruleid);

			}
			if (ctx.get("LegalServicesBankruptLiquidation") != null
					&& ctx.get("LegalServicesBankruptLiquidation").toString().equals("Y")) {
				ruleid = "LegalServicesBankruptLiquidationRule_Lawyers";
				logger.debug("going to debug : " + ruleid);
				RuleEngineUtilityNewApp.insertRulesToDatabaseTemp(ctx, ruleid, "Insolvent client in the past 2 years. ",
						"Ask for additional information on client, name, amount, and current status.");

			} else {
				ruleid = "LegalServicesBankruptLiquidationRule_Lawyers";
				archiveFlag(ctx, ruleid);

			}
			/*
			 * if(ctx.get("PolicyStatusKey")!=null &&
			 * ctx.get("PolicyStatusKey").toString().equals("1")) {
			 * if(ctx.get("IsFirmProvidedLegalServiceCCB")!=null &&
			 * !ctx.get("IsFirmProvidedLegalServiceCCB").equals(HtmlConstants.EMPTY) ) {
			 * ruleid="IsFirmProvidedLegalServiceCCB_Lawyers";
			 * logger.debug("going to debug : "+ ruleid); // ctx.put("otherServicesSup",
			 * ctx.get("otherServices")); //CopyRightTrademarkSupplement
			 * cpSupplement=(CopyRightTrademarkSupplement)initlizePOJO(ctx, new
			 * CopyRightTrademarkSupplement()); //ctx.put("RuleObject",cpSupplement);
			 * //insertRulesToDatabase(callRuleEngine(ctx,ruleid),cpSupplement,ruleid,
			 * cpSupplement.getDescription(),cpSupplement.getTooltip());
			 * insertRulesToDatabase(ctx,ruleid,"Corporate commercial rule description"
			 * ,"Corporate commercial rule tool tip"); }
			 * 
			 * else { ruleid="IsFirmProvidedLegalServiceCCB_Lawyers";
			 * archiveFlag(ctx,ruleid); } } if(ctx.get("PolicyStatusKey")!=null &&
			 * ctx.get("PolicyStatusKey").toString().equals("2")) { List previousPolicyKey=
			 * (List) SqlResources.getSqlMapProcessor(ctx).select(
			 * "SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey",ctx);
			 * ctx.putAll((Map) previousPolicyKey.get(0)); Context oldPolicyContext=new
			 * Context(); oldPolicyContext.setProject(ctx.getProject());
			 * oldPolicyContext.put("PolicyKey",ctx.get("PreviousPolicykey"));
			 * oldPolicyContext.put("VersionSequence",ctx.get("previousVersionSequence"));
			 * List copyTrademarkDetails =
			 * SqlResources.getSqlMapProcessor(oldPolicyContext).select(
			 * "SqlStmts.UserStatementdroolQueriesgetCopyRightTrademarkDetails",
			 * oldPolicyContext);
			 * 
			 * if(copyTrademarkDetails!=null && copyTrademarkDetails instanceof List ) {
			 * oldPolicyContext.putAll((Map) copyTrademarkDetails.get(0)); } String
			 * oldOtherServices=oldPolicyContext.get("otherServices")!=null &&
			 * !oldPolicyContext.get("otherServices").equals(HtmlConstants.EMPTY)?
			 * oldPolicyContext.get("otherServices").toString():"S"; String
			 * otherServices=ctx.get("otherServices")!=null &&
			 * !ctx.get("otherServices").equals(HtmlConstants.EMPTY)?ctx.get("otherServices"
			 * ).toString():"S"; if("N".equalsIgnoreCase(oldOtherServices) &&
			 * "Y".equalsIgnoreCase(otherServices)) {
			 * ruleid="IsFirmProvidedLegalServiceCCB_Lawyers";
			 * logger.debug("going to debug : "+ ruleid); ctx.put("otherServicesSup",
			 * ctx.get("otherServices")); CopyRightTrademarkSupplement
			 * cpSupplement=(CopyRightTrademarkSupplement)initlizePOJO(ctx, new
			 * CopyRightTrademarkSupplement()); ctx.put("RuleObject",cpSupplement);
			 * //insertRulesToDatabase(callRuleEngine(ctx,ruleid),cpSupplement,ruleid,
			 * cpSupplement.getDescription(),cpSupplement.getTooltip());
			 * insertRulesToDatabase(ctx,ruleid,"Corporate commercial rule description"
			 * ,"Corporate commercial rule tool tip"); }
			 * 
			 * else { ruleid="OtherServices_Lawyers"; archiveFlag(ctx,ruleid); } }
			 */
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}

	}

//temporary solution till drool fixed
	public static void insertRulesToDatabaseTemp(Context ctx, String ruleId, String description, String toolTip) {

		if (description != null && toolTip != null && !description.equals("") && !toolTip.equals("")) {
			ctx.put("ruleId", ruleId);
			ctx.put("question", description);
			ctx.put("hint", toolTip);
			ctx.put("ActionTakenBy", ctx.get("user_id"));
			try {
				new SetParametersForStoredProcedures().setParametersInContext(ctx,
						"PolicyKey,ruleId,question,hint,ActionTakenBy");
				SqlResources.getSqlMapProcessor(ctx).update("rulepolicytransaction.SETFlagsLawyers_p", ctx);
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}
		} else {
			ctx.put("ruleId", ruleId);
			ctx.put("question", "");
			ctx.put("hint", "");
			try {
				new SetParametersForStoredProcedures().setParametersInContext(ctx,
						"PolicyKey,ruleId,question,hint,ActionTakenBy");
				SqlResources.getSqlMapProcessor(ctx).update("rulepolicytransaction.RESETFlagsLawyers_p", ctx);
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}

		}
	}
	/*
	 * public static void evaluateCorporateComerricialBusinessSupp(Context ctx)
	 * throws Exception { try { String ruleid=""; Rule no:-2Firm Name and Address
	 * common rule for NB and RB CorporateComerricialBusinbess
	 * f1=(CorporateComerricialBusinbess)initlizePOJO(ctx, new
	 * CorporateComerricialBusinbess());
	 * 
	 * ruleid="LegalServicesBankruptLiquidationRule_Lawyers";
	 * logger.debug("going to debug : "+ ruleid); ctx.put("RuleObject",f1);
	 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),f1,ruleid,f1.getDescription(
	 * ),f1.getTooltip());
	 * 
	 * ruleid="OtherLegalCorporateServicesRule_Lawyers";
	 * logger.debug("going to debug : "+ ruleid); ctx.put("RuleObject",f1);
	 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),f1,ruleid,f1.getDescription(
	 * ),f1.getTooltip());
	 * 
	 * ruleid="PubliclyRenderedServicesRule_Lawyers";
	 * logger.debug("going to debug : "+ ruleid); ctx.put("RuleObject",f1);
	 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),f1,ruleid,f1.getDescription(
	 * ),f1.getTooltip());
	 * 
	 * ruleid="RegardToCorporateClientsRule_Lawyers";
	 * logger.debug("going to debug : "+ ruleid); ctx.put("RuleObject",f1);
	 * insertRulesToDatabase(callRuleEngine(ctx,ruleid),f1,ruleid,f1.getDescription(
	 * ),f1.getTooltip()); } catch(Exception e) { logger.
	 * error("exception occured while executing evaluateCorporateComerricialBusinessSup method"
	 * +e); } }
	 */
}
