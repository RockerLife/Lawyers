package com.userbo;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.jdom.Element;

import com.mail.MailSender;
import com.manage.managecomponent.components.SetParametersForStoredProcedures;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.IContext;
import com.util.InetLogger;
import com.util.SystemProperties;
import com.util.XMLUtils;


public class LawyersValidationUtils {

	final String key = "Acc";

	private static InetLogger logger = InetLogger
			.getInetLogger(LawyersValidationUtils.class);

	private final String SPECIALCHARACTER = "!@#$%^&*.()_";

	/*
	 * public void checkIfAllPagesSaved(IContext ctx) throws Exception {
	 * 
	 * Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey(
	 * "SqlStmts.firmviewgetAllPageFlags", ctx);
	 * 
	 * if (objFirm != null && objFirm instanceof Map) {
	 * 
	 * Map objMap = (Map) objFirm;
	 * 
	 * String isFirmSaved = objMap.get("IsFirmPage") != null ? objMap.get(
	 * "IsFirmPage").toString() : ""; String isAOPSaved =
	 * objMap.get("IsAopPage") != null ? objMap.get( "IsAopPage").toString() :
	 * ""; String isPracticeSaved = objMap.get("IsPracticePage") != null ?
	 * objMap .get("IsPracticePage").toString() : ""; String isCoverageSaved =
	 * objMap.get("IsCoveragePage") != null ? objMap
	 * .get("IsCoveragePage").toString() : "";
	 * 
	 * if (isFirmSaved.equals("Y") && isAOPSaved.equals("Y") &&
	 * isPracticeSaved.equals("Y") && isCoverageSaved.equals("Y")) { } else {
	 * 
	 * LawyersUtils.populateError(ctx, "AllPagesNotSaved", "Please complete all
	 * of the pages for Quote Options."); } } else { return; } }
	 */

	public void checkIfAllPagesSaved(IContext ctx) throws Exception {

		Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"SqlStmts.firmviewgetAllPageFlags", ctx);

		boolean isRealEstateResiRequired = false;
		boolean isRealEstateCommRequired = false;
		boolean bothRequired = false;
		boolean isPlaintiffRequired = false;

		if (objFirm != null && objFirm instanceof Map) {

			Map objMap = (Map) objFirm;

			String isFirmSaved = objMap.get("IsFirmPage") != null ? objMap.get(
					"IsFirmPage").toString() : "";
			String isAOPSaved = objMap.get("IsAopPage") != null ? objMap.get(
					"IsAopPage").toString() : "";
			String isCoverageSaved = objMap.get("IsCoveragePage") != null ? objMap
					.get("IsCoveragePage").toString() : "";
			String isPlaintiffSaved = objMap.get("IsPlaintiffPage") != null ? objMap
					.get("IsPlaintiffPage").toString() : "";
			String isRenewFirmSaved = objMap.get("IsRenewFirmPage") != null ? objMap
							.get("IsRenewFirmPage").toString() : "";

			// check here if Plaintiff Percentage required
			if (isAOPSaved.equals("Y")) {

				List aoplist = SqlResources
						.getSqlMapProcessor(ctx)
						.select("SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields",
								ctx);

				Map covMap = new HashMap();
				if (aoplist != null) {
					Map map = new HashMap();
					for (int i = 0; i < aoplist.size(); i++) {
						map = (HashMap) aoplist.get(i);
						covMap.put("AOP_Percentage_" + map.get("AOPKey"),
								map.get("PercentageValue"));
					}
				}

				if (covMap != null) {

					String plaintiffAOPPercentageString = covMap
							.get("AOP_Percentage_18") != null ? covMap.get(
							"AOP_Percentage_18").toString() : "0";
					int plaintiffPercentage = Integer
							.parseInt(plaintiffAOPPercentageString);

					if (plaintiffPercentage > 0)
						isPlaintiffRequired = true;
				}
			}

			if (ctx.get("PolicyStatusKey") != null
					&& "1".equals(ctx.get("PolicyStatusKey").toString())) {
				if (isPlaintiffRequired) {
					if (isFirmSaved.equals("Y") && isAOPSaved.equals("Y")
							&& isCoverageSaved.equals("Y")
							&& isPlaintiffSaved.equals("Y")) {

					} else {
						LawyersUtils
								.populateError(ctx, "AllPagesNotSaved",
										"Please complete all of the pages for Quote Options.");
					}

				} else {
					if (isFirmSaved.equals("Y") && isAOPSaved.equals("Y")
							&& isCoverageSaved.equals("Y")) {
					} else {
						LawyersUtils
								.populateError(ctx, "AllPagesNotSaved",
										"Please complete all of the pages for Quote Options.");
					}
				}
			}else if(ctx.get("PolicyStatusKey") != null
					&& "2".equals(ctx.get("PolicyStatusKey").toString())){
				
				if (isPlaintiffRequired) {
					if (isRenewFirmSaved.equals("Y") && isPlaintiffSaved.equals("Y")
							) {

					} else {
						LawyersUtils
								.populateError(ctx, "AllPagesNotSaved",
										"Please complete all of the pages for Quote Options.");
					}

				} else {
					if (isRenewFirmSaved.equals("Y")) {
					} else {
						LawyersUtils
								.populateError(ctx, "AllPagesNotSaved",
										"Please complete all of the pages for Quote Options.");
					}
				}
				
			}

			// String isRealEstateSaved = objMap.get("IsRealEstatePage") != null
			// ? objMap
			// .get("IsRealEstatePage").toString()
			// : "";

			// String isRealEstateCommercialSaved = objMap
			// .get("IsRealEstateCommercialPage") != null ? objMap.get(
			// "IsRealEstateCommercialPage").toString() : "";
			//
			// String isRealEstateResidentialSaved = objMap
			// .get("IsRealEstateResidentialPage") != null ? objMap.get(
			// "IsRealEstateResidentialPage").toString() : "";
			//
			// if (isAOPSaved.equals("Y")) {
			//
			// List aoplist = SqlResources
			// .getSqlMapProcessor(ctx)
			// .select(
			// "SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields",
			// ctx);
			//
			// Map covMap = new HashMap();
			// if (aoplist != null) {
			// Map map = new HashMap();
			// for (int i = 0; i < aoplist.size(); i++) {
			// map = (HashMap) aoplist.get(i);
			// covMap.put("AOP_Percentage_" + map.get("AOPKey"), map
			// .get("PercentageValue"));
			// }
			// }
			//
			// if (covMap != null) {
			//
			// String percentageValue1 = covMap.get("AOP_Percentage_20") != null
			// ? covMap
			// .get("AOP_Percentage_20").toString()
			// : "0";
			// int percentageVal1 = Integer.parseInt(percentageValue1);
			//
			// String percentageValue2 = covMap.get("AOP_Percentage_27") != null
			// ? covMap
			// .get("AOP_Percentage_27").toString()
			// : "0";
			// int percentageVal2 = Integer.parseInt(percentageValue2);
			//
			// if (percentageVal1 > 0)
			// isRealEstateCommRequired = true;
			//
			// if (percentageVal2 > 0)
			// isRealEstateResiRequired = true;
			//
			// if (percentageVal1 > 0 && percentageVal2 > 0)
			// bothRequired = true;
			//
			// }
			// }
			//
			// if (bothRequired) {
			//
			// if (isFirmSaved.equals("Y") && isAOPSaved.equals("Y")
			// && isCoverageSaved.equals("Y")
			// && isRealEstateCommercialSaved.equals("Y")
			// && isRealEstateResidentialSaved.equals("Y")) {
			//
			// } else {
			//
			// LawyersUtils
			// .populateError(ctx, "AllPagesNotSaved",
			// "Please complete all of the pages for Quote Options.");
			// }
			//
			// } else if (isRealEstateCommRequired) {
			//
			// if (isFirmSaved.equals("Y") && isAOPSaved.equals("Y")
			// && isCoverageSaved.equals("Y")
			// && isRealEstateCommercialSaved.equals("Y")) {
			//
			// } else {
			//
			// LawyersUtils
			// .populateError(ctx, "AllPagesNotSaved",
			// "Please complete all of the pages for Quote Options.");
			// }
			//
			// } else if (isRealEstateResiRequired) {
			//
			// if (isFirmSaved.equals("Y") && isAOPSaved.equals("Y")
			// && isCoverageSaved.equals("Y")
			// && isRealEstateResidentialSaved.equals("Y")) {
			//
			// } else {
			//
			// LawyersUtils
			// .populateError(ctx, "AllPagesNotSaved",
			// "Please complete all of the pages for Quote Options.");
			// }
			//
			// } else {
			//
			// if (isFirmSaved.equals("Y") && isAOPSaved.equals("Y")
			// && isCoverageSaved.equals("Y")) {
			//
			// } else {
			//
			// LawyersUtils
			// .populateError(ctx, "AllPagesNotSaved",
			// "Please complete all of the pages for Quote Options.");
			// }
			//
			// }
			//
		} else {
			return;
		}

	}

	public void checkIfAllPagesSavedIfNoSupplements(IContext ctx)
			throws Exception {

		List aoplist = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields", ctx);

		Map covMap = new HashMap();
		if (aoplist != null) {
			Map map = new HashMap();
			for (int i = 0; i < aoplist.size(); i++) {
				map = (HashMap) aoplist.get(i);
				covMap.put("AOP_Percentage_" + map.get("AOPKey"),
						map.get("PercentageValue"));
			}
		}

		if (covMap != null) {

			String percentageValue1 = covMap.get("AOP_Percentage_20") != null ? covMap
					.get("AOP_Percentage_20").toString() : "0";
			int percentageVal1 = Integer.parseInt(percentageValue1);

			String percentageValue2 = covMap.get("AOP_Percentage_27") != null ? covMap
					.get("AOP_Percentage_27").toString() : "0";
			int percentageVal2 = Integer.parseInt(percentageValue2);

			String percentageValue3 = covMap.get("AOP_Percentage_18") != null ? covMap
					.get("AOP_Percentage_18").toString() : "0";
			int percentageVal3 = Integer.parseInt(percentageValue3);

			String percentageValue4 = covMap.get("AOP_Percentage_24") != null ? covMap
					.get("AOP_Percentage_24").toString() : "0";
			int percentageVal4 = Integer.parseInt(percentageValue4);

			if (percentageVal1 > 0 || percentageVal2 > 0 || percentageVal3 > 0 || percentageVal4 > 0) {

			} else {
				checkIfAllPagesSaved(ctx);
			}

		}

	}

	public void checkIfAnyOptionManual(IContext ctx) throws Exception {

		List quoteList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetAllQuotes", ctx);

		ctx.remove("OneQuoteIsManual");

		boolean flag = false;
		if (quoteList != null && quoteList.size() > 0) {

			for (int i = 0; i < quoteList.size(); i++) {

				Map quoteMap = (Map) quoteList.get(i);

				if (quoteMap != null) {

					String isManualPremium = quoteMap.get("IsManualPremium") != null ? quoteMap
							.get("IsManualPremium").toString() : "";

					if (isManualPremium.equals("Y")) {
						flag = true;
						break;
					}
				}
			}

			if (flag) {
				ctx.put("OneQuoteIsManual", "Y");
			}

		}
	}

	public boolean isMailSendToInsured(IContext ctx) throws Exception {

		boolean flag = true;

		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"State.findByKey", ctx);

		Map stateMap = new HashMap();
		if (obj != null && obj instanceof Map)
			stateMap = (Map) obj;

		String issueMailLwy = stateMap.get("IssueMailLwy") != null ? stateMap
				.get("IssueMailLwy").toString() : "";

		if (issueMailLwy.equals("N"))
			flag = false;

		return flag;

	}

	public boolean isNonRatingState(IContext ctx) throws Exception {

		ctx.remove("IsNonRatingState");

		Object objState = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"State.findByKey", ctx);

		Map stateMap = new HashMap();
		if (objState != null && objState instanceof Map)
			stateMap = (Map) objState;

		String isReferredState = stateMap.get("IsStateReferredFQ") != null ? stateMap
				.get("IsStateReferredFQ").toString() : "";

		if (isReferredState.equals("Y")) {
			ctx.put("IsNonRatingState", "Y");
			return true;
		} else {
			ctx.put("IsNonRatingState", "N");
			return false;
		}

	}

	public static int convertStringToInteger(String str) throws Exception {

		int intVar = 0;
		if (str != null && !"".equals(str)) {

			intVar = Integer.parseInt(str);
		}
		return intVar;
	}

	public String getPlaintiffPercentage(Map inputMap) throws Exception {

		int plaintiffSum = LawyersValidationUtils.convertStringToInteger(inputMap.get("AOL_PercentageOfRevenue_4") != null ? inputMap.get("AOL_PercentageOfRevenue_4").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOL_PercentageOfRevenue_12") != null ? inputMap
						.get("AOL_PercentageOfRevenue_12").toString() : "");

		float plaintiff = plaintiffSum
				* LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOP_Percentage_18") != null ? inputMap.get(
						"AOP_Percentage_18").toString() : "");

		plaintiff = plaintiff / 100;
		int plaintiffPercentage = (int) Math.round(plaintiff);
		return String.valueOf(plaintiffPercentage);

	}

	public String getPlaintiffOtherPercentage(Map inputMap) throws Exception {

		int plaintiffOtherSum = LawyersValidationUtils
				.convertStringToInteger(inputMap
						.get("AOL_PercentageOfRevenue_5") != null ? inputMap
						.get("AOL_PercentageOfRevenue_5").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOL_PercentageOfRevenue_6") != null ? inputMap
						.get("AOL_PercentageOfRevenue_6").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOL_PercentageOfRevenue_8") != null ? inputMap
						.get("AOL_PercentageOfRevenue_8").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOL_PercentageOfRevenue_9") != null ? inputMap
						.get("AOL_PercentageOfRevenue_9").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOL_PercentageOfRevenue_11") != null ? inputMap
						.get("AOL_PercentageOfRevenue_11").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOL_PercentageOfRevenue_13") != null ? inputMap
						.get("AOL_PercentageOfRevenue_13").toString() : "");

		float plaintiffOther = plaintiffOtherSum
				* LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOP_Percentage_18") != null ? inputMap.get(
						"AOP_Percentage_18").toString() : "");

		plaintiffOther = plaintiffOther / 100;
		int plaintiffOtherPercentage = (int) Math.round(plaintiffOther);
		return String.valueOf(plaintiffOtherPercentage);

	}

	public String getRealEstateResidentialPercentage(Map inputMap)
			throws Exception {

		int residentialFactor = LawyersValidationUtils
				.convertStringToInteger(inputMap.get("AOPRE_Percentage_1") != null ? inputMap
						.get("AOPRE_Percentage_1").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOPRE_Percentage_3") != null ? inputMap.get(
						"AOPRE_Percentage_3").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOPRE_Percentage_4") != null ? inputMap.get(
						"AOPRE_Percentage_4").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOPRE_Percentage_5") != null ? inputMap.get(
						"AOPRE_Percentage_5").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOPRE_Percentage_7") != null ? inputMap.get(
						"AOPRE_Percentage_7").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOPRE_Percentage_11") != null ? inputMap.get(
						"AOPRE_Percentage_11").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOPRE_Percentage_12") != null ? inputMap.get(
						"AOPRE_Percentage_12").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOPRE_Percentage_13") != null ? inputMap.get(
						"AOPRE_Percentage_13").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOPRE_Percentage_14") != null ? inputMap.get(
						"AOPRE_Percentage_14").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOPRE_Percentage_16") != null ? inputMap.get(
						"AOPRE_Percentage_16").toString() : "");

		float realEstateResi = residentialFactor
				* LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOP_Percentage_20") != null ? inputMap.get(
						"AOP_Percentage_20").toString() : "");

		realEstateResi = realEstateResi / 100;
		int realEstateResi1 = (int) Math.round(realEstateResi);
		return String.valueOf(realEstateResi1);

	}

	public String getRealEstateCommercialPercentage(Map inputMap)
			throws Exception {

		int commercialFactor = LawyersValidationUtils
				.convertStringToInteger(inputMap.get("AOPRE_Percentage_2") != null ? inputMap
						.get("AOPRE_Percentage_2").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOPRE_Percentage_6") != null ? inputMap.get(
						"AOPRE_Percentage_6").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOPRE_Percentage_8") != null ? inputMap.get(
						"AOPRE_Percentage_8").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOPRE_Percentage_9") != null ? inputMap.get(
						"AOPRE_Percentage_9").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOPRE_Percentage_10") != null ? inputMap.get(
						"AOPRE_Percentage_10").toString() : "")
				+ LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOPRE_Percentage_15") != null ? inputMap.get(
						"AOPRE_Percentage_15").toString() : "");

		float realEstateComm = commercialFactor
				* LawyersValidationUtils.convertStringToInteger(inputMap
						.get("AOP_Percentage_20") != null ? inputMap.get(
						"AOP_Percentage_20").toString() : "");

		realEstateComm = realEstateComm / 100;
		int realEstateComm1 = (int) Math.round(realEstateComm);

		return String.valueOf(realEstateComm1);

	}

	public boolean checkLimitsOfLiability(IContext ctx) throws Exception {

		boolean flag = false;

		Object limitSequence = ctx.get("LimitSequence");
		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"SqlStmts.quickquoteoptionsaveQuotepopulateLimit", ctx);

		if (obj != null && obj instanceof Map) {

			Map objMap = (Map) obj;
			int AggregateLimit = objMap.get("AggregateLimit") != null ? convertStringToInteger(objMap
					.get("AggregateLimit").toString()) : 0;
			int OccuranceLimit = objMap.get("OccuranceLimit") != null ? convertStringToInteger(objMap
					.get("OccuranceLimit").toString()) : 0;

			if (AggregateLimit > 1000000 && OccuranceLimit > 1000000) {

				String ClaimExpenseType = ctx.get("IsClaimExpensesType") != null ? ctx
						.get("IsClaimExpensesType").toString() : "N";

			}

		}

		return false;

	}

	public void setClaimExpensesForStates(IContext ctx) throws Exception {

		Object objRule = RuleUtils.executeRule(ctx,
				"LawyersRule.IsClaimExpenseOutsideOnly");
		if (objRule != null && objRule instanceof Boolean) {
			Boolean stateRule = (Boolean) objRule;
			if (stateRule) {
				ctx.put("IsClaimExpensesType", "Y");

			}

		}

		if (ctx.get("StateCode") != null
				&& ctx.get("StateCode").toString()
						.equals(LawyersConstants.SOUTH_DAKHOTA)) {
			ctx.put("IsClaimExpensesType", "N");
		}
	}

	public void updatePolicyCoverageForNonRating(IContext ctx) throws Exception {

		Object obj = RuleUtils.executeRule(ctx,
				"LawyersRule.IsClaimExpenseOutsideOnly");
		boolean flag = false;
		if (obj != null && obj instanceof Boolean) {

			flag = (Boolean) obj;
		}

		if (flag) {

			Context newCtx = new Context();
			newCtx.putAll(ctx);
			newCtx.put("IsClaimExpensesType", "Y");
	    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
	    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));

			SqlResources.getSqlMapProcessor(newCtx).update(
					"SqlStmts.UserStatementManualBoQueriesupdateClaimExpense",
					newCtx);

		}

		Object obj1 = RuleUtils.executeRule(ctx,
				"LawyersRule.IsClaimExpenseInsideOnly");
		boolean flag1 = false;
		if (obj != null && obj instanceof Boolean) {

			flag1 = (Boolean) obj1;
		}

		if (flag1) {

			Context newCtx = new Context();
			newCtx.putAll(ctx);
			newCtx.put("IsClaimExpensesType", "N");
	    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
	    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));

			SqlResources.getSqlMapProcessor(newCtx).update(
					"SqlStmts.UserStatementManualBoQueriesupdateClaimExpense",
					newCtx);

		}

	}

	public void updatePolicyCoverageForSD(IContext ctx) throws Exception {

		Object obj = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetPolicyListAll", ctx);
		List policyList = new ArrayList();
		if (obj != null && obj instanceof List) {

			policyList = (List) obj;

		}
		Map policyMap = new HashMap();
		if (policyList != null && policyList.size() > 0) {

			for (int i = 0; i < policyList.size(); i++) {

				policyMap = (Map) policyList.get(i);

				updateLimitsForSD(policyMap, ctx);

			}

		}

	}

	public void updateLimitsForSD(Map policyMap, IContext ctx) throws Exception {

		if (policyMap != null) {

			int AggregateLimit = policyMap.get("AggregateLimit") != null ? LawyersValidationUtils
					.convertStringToInteger(policyMap.get("AggregateLimit")
							.toString()) : 0;
			int OccuranceLimit = policyMap.get("OccuranceLimit") != null ? LawyersValidationUtils
					.convertStringToInteger(policyMap.get("OccuranceLimit")
							.toString()) : 0;
			int AggregateDeductible = policyMap.get("AggregateDeductible") != null ? LawyersValidationUtils
					.convertStringToInteger(policyMap
							.get("AggregateDeductible").toString()) : 0;
			int OccuranceDeductible = policyMap.get("OccuranceDeductible") != null ? LawyersValidationUtils
					.convertStringToInteger(policyMap
							.get("OccuranceDeductible").toString()) : 0;

			if (AggregateLimit < 1000000 || OccuranceLimit < 1000000) {

				Context newCtx = new Context();
				newCtx.setProject(ctx.getProject());
				newCtx.putAll(policyMap);
				newCtx.put("AggregateLimit", 1000000);
				newCtx.put("OccuranceLimit", 1000000);
		    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
		    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));

				SqlResources
						.getSqlMapProcessor(newCtx)
						.update("SqlStmts.UserStatementManualBoQueriesupdatePolicyCoverage",
								newCtx);
			}
		}

	}

	public void updatePolicyCoverageForNJ(IContext ctx) throws Exception {

		Object obj = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetPolicyListAll", ctx);
		List policyList = new ArrayList();
		if (obj != null && obj instanceof List) {

			policyList = (List) obj;

		}
		Map policyMap = new HashMap();
		if (policyList != null && policyList.size() > 0) {

			for (int i = 0; i < policyList.size(); i++) {

				policyMap = (Map) policyList.get(i);

				updateClaimExpenseForNJ(policyMap, ctx);

			}

		}

	}

	public void updateClaimExpenseForNJ(Map policyMap, IContext ctx)
			throws Exception {

		if (policyMap != null) {

			String IsClaimExpensesType = policyMap.get("IsClaimExpensesType") != null ? policyMap
					.get("IsClaimExpensesType").toString() : "";

			if (!IsClaimExpensesType.equals("Y")) {

				Context newCtx = new Context();
				newCtx.setProject(ctx.getProject());
				newCtx.putAll(policyMap);
				newCtx.put("IsClaimExpensesType", "Y");
		    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
		    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));

				SqlResources
						.getSqlMapProcessor(newCtx)
						.update("SqlStmts.UserStatementManualBoQueriesupdateClaimExpense",
								newCtx);
			}
		}

	}

	public static void checkAccountEmailExist(IContext ctx) throws Exception {

		boolean isAccountEmailExist = false;

		List listFirm = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetRegistrationDetails",
				ctx);

		if (listFirm != null && listFirm.size() >= 1)
			isAccountEmailExist = true;

		if (isAccountEmailExist)
			LawyersUtils
					.populateError(ctx, "AccountEmail",
							"This user already exists, please contact  for any assistance");

	}

	public void checkForOldPolicy(IContext ctx) throws Exception {

		ctx.get("fromRegistrationLink");
		ctx.get("PolicyKey");
		ctx.get("AccountEmail");

		if (ctx.get("fromRegistrationLink") != null
				&& ctx.get("fromRegistrationLink").toString().equals("Y")) {

			if (ctx.get("PolicyKey") != null
					&& !"null".equals(ctx.get("PolicyKey").toString())) {

				ctx.put("IsOldPolicy", "N");

			} else {
				ctx.put("IsOldPolicy", "Y");
			}

		} else {
			ctx.put("IsOldPolicy", "N");
		}

	}

	@SuppressWarnings("unchecked")
	public void validateWorkQSearch(IContext ctx) throws Exception {

		if (ctx.get("WQPolicyEffectiveDate_search") != null
				&& !"".equals(ctx.get("WQPolicyEffectiveDate_search").toString())) {
			String strDate = ctx.get("WQPolicyEffectiveDate_search").toString();
			LawyersUtils.validateLeapYearDate(ctx,
					"WQPolicyEffectiveDate_search", strDate);
			
			String d = ctx.get("WQPolicyEffectiveDate_searchTo") != null ? ctx.get("WQPolicyEffectiveDate_searchTo").toString() : "";
			if("".equals(d)){				
				LawyersUtils.populateError(ctx, "PolicyEffectiveDate_searchTo", "Date is a required field");				
			}			
		}
		
		if(ctx.get("WQPolicyEffectiveDate_searchTo") != null
				&& !"".equals(ctx.get("WQPolicyEffectiveDate_searchTo").toString())){
			String strDate = ctx.get("WQPolicyEffectiveDate_searchTo").toString();
			LawyersUtils.validateLeapYearDate(ctx, "WQPolicyEffectiveDate_searchTo", strDate);
			
			
			String d = ctx.get("WQPolicyEffectiveDate_search") != null ? ctx.get("WQPolicyEffectiveDate_search").toString() : "";
			if("".equals(d)){				
				LawyersUtils.populateError(ctx, "PolicyEffectiveDate_search", "Date is a required field");				
			}
			
		}
		String AccountNamesearch=(ctx.get("AccountNamesearch") == null || "".equals(ctx.get("AccountNamesearch"))) ? "" : ctx.get("AccountNamesearch").toString().trim();
		if(AccountNamesearch.contains("'"))
			AccountNamesearch=AccountNamesearch.replace("'", "''");
		ctx.put("AccountNamesearch", AccountNamesearch);
		ctx.put("WQEmailSearch", (ctx.get("WQEmailSearch") == null || "".equals(ctx.get("WQEmailSearch"))) ? "" : ctx.get("WQEmailSearch").toString().trim());
		ctx.put("WQPolicyEffectiveDate_search", (ctx.get("WQPolicyEffectiveDate_search") == null || "".equals(ctx.get("WQPolicyEffectiveDate_search"))) ? "" : ctx.get("WQPolicyEffectiveDate_search").toString().trim());
		ctx.put("WQPolicyEffectiveDate_searchTo", (ctx.get("WQPolicyEffectiveDate_searchTo") == null || "".equals(ctx.get("WQPolicyEffectiveDate_searchTo"))) ? "" : ctx.get("WQPolicyEffectiveDate_searchTo").toString().trim());
		ctx.put("WQStateCode_search", (ctx.get("WQStateCode_search") == null || "".equals(ctx.get("WQStateCode_search"))) ? "" : ctx.get("WQStateCode_search").toString().trim());
		ctx.put("WQStatusKey_search", (ctx.get("WQStatusKey_search") == null || "".equals(ctx.get("WQStatusKey_search"))) ? "" : ctx.get("WQStatusKey_search").toString().trim());
		ctx.put("WQQuoteNumberSearch", (ctx.get("WQQuoteNumberSearch") == null || "".equals(ctx.get("WQQuoteNumberSearch"))) ? "" : ctx.get("WQQuoteNumberSearch").toString().trim());
		ctx.put("WQPolicyNumberSearch", (ctx.get("WQPolicyNumberSearch") == null || "".equals(ctx.get("WQPolicyNumberSearch"))) ? "" : ctx.get("WQPolicyNumberSearch").toString().trim());
		ctx.put("WQNumberOfDays_search", (ctx.get("WQNumberOfDays_search") == null || "".equals(ctx.get("WQNumberOfDays_search"))) ? "" : ctx.get("WQNumberOfDays_search").toString().trim());
	}

	public static void validateEmail(IContext ctx, String field, String strEmail)
			throws Exception {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if (strEmail != null && !"".equals(strEmail)) {

			// Set the email pattern string
			// Pattern p =
			// Pattern.compile(".+@.+\\.[a-z]",Pattern.CASE_INSENSITIVE);
			Pattern p = Pattern.compile(EMAIL_PATTERN);

			// Match the given string with the pattern
			Matcher m = p.matcher(strEmail);

			// check whether match is found
			boolean matchFound = m.matches();

			if (!matchFound)
				LawyersUtils.populateError(ctx, field, "Invalid Email Address");

		} else {
			LawyersUtils.populateError(ctx, field, "Invalid Email Address");
		}
	}

	public void checkforNegativeValue(String str) throws Exception {

	}

	public void checkforSpecialCharacterData(IContext ctx, String str,
			String field) throws Exception {

		if (str != null && !"".equals(str)) {

			if (SPECIALCHARACTER.indexOf(str) != -1) {

				LawyersUtils.populateError(ctx, field, "Invalid data for AOP");

			}
		}

	}

	public static void validateDateFormat(String date, String field,
			IContext ctx, String message) throws Exception {

		logger.debug("Date format is MM/YYYY");
		logger.debug("Date is ---> " + date);

		if (date != null && !"".equals(date)) {

			String[] str = date.split("/");

			int month = Integer.parseInt(str[0]);
			int year = Integer.parseInt(str[1]);

			if (date.length() < 7)
				LawyersUtils.populateError(ctx, field, message);

			if (month > 12 || month < 1)
				LawyersUtils.populateError(ctx, field, message);

		}

	}

	public boolean checkIfAnyOptionManualFullQuote(IContext ctx)
			throws Exception {

		List quoteList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetAllQuotes", ctx);

		boolean flag = false;
		if (quoteList != null && quoteList.size() > 0) {

			for (int i = 0; i < quoteList.size(); i++) {

				Map quoteMap = (Map) quoteList.get(i);

				if (quoteMap != null) {

					String isManualPremium = quoteMap.get("IsManualPremium") != null ? quoteMap
							.get("IsManualPremium").toString() : "";
					String isQuoteSelected = ctx.get("IsQuoteSelected_" + i) != null ? ctx
							.get("IsQuoteSelected_" + i).toString() : "";

					if (isManualPremium.equals("Y")
							&& (isQuoteSelected.equals("Y")
									|| "on".equals(isQuoteSelected) || "N"
										.equals(isQuoteSelected))) {
						flag = true;
						break;
					}
				}
			}

			if (flag) {
				return true;
			}

		}
		return flag;
	}

	public boolean checkForManualQuote(IContext ctx) throws Exception {

		List quoteList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetAllQuotes", ctx);

		boolean flag = false;
		if (quoteList != null && quoteList.size() > 0) {

			for (int i = 0; i < quoteList.size(); i++) {

				Map quoteMap = (Map) quoteList.get(i);

				if (quoteMap != null) {

					String isManualPremium = quoteMap.get("IsManualPremium") != null ? quoteMap
							.get("IsManualPremium").toString() : "";
					String isQuoteSelected = quoteMap.get("IsQuoteSelected") != null ? quoteMap
							.get("IsQuoteSelected").toString() : "";

					if (isManualPremium.equals("Y")
							&& isQuoteSelected.equals("Y")) {
						flag = true;
						break;
					}
				}
			}

			if (flag) {
				return true;
			}

		}
		return flag;
	}

	public static void validatePolicyEffectiveDateCoverage(IContext ctx)
			throws Exception {

		String date = ctx.get("PolicyEffectiveDate") != null ? ctx.get(
				"PolicyEffectiveDate").toString() : "";

		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);

		if (date != null && !"".equals(date)) {

			String[] str = date.split("/");

			int month = Integer.parseInt(str[0]);
			int day = Integer.parseInt(str[1]);
			int year = Integer.parseInt(str[2]);

			if (year > currentYear) {
				if (year - currentYear != 1)
					LawyersUtils
							.populateError(
									ctx,
									"PolicyEffectiveDate",
									"Policy Effective Date should be current year or one year less than the current year or one year greater than the current year");
			} else if (currentYear > year) {
				if (currentYear - year != 1)
					LawyersUtils
							.populateError(
									ctx,
									"PolicyEffectiveDate",
									"Policy Effective Date should be current year or one year less than the current year or one year greater than the current year");
			}
		}
	}

	public static void validatePriorActDatePross(IContext ctx) throws Exception {

		if (ctx.get("PolicyEffectiveDate") != null
				&& ctx.get("PriorActDatePross") != null) {

			String strPolicyEffDate = ctx.get("PolicyEffectiveDate").toString();

			String strPriorActDate = ctx.get("PriorActDatePross").toString();

			Date policyDate = new Date(strPolicyEffDate);
			Date priorDate = new Date(strPriorActDate);

			Calendar policyCal = Calendar.getInstance();
			policyCal.set(policyDate.getYear(), policyDate.getMonth(),
					policyDate.getDate());

			Calendar priorCal = Calendar.getInstance();
			priorCal.set(priorDate.getYear(), priorDate.getMonth(),
					priorDate.getDate());

			if (priorCal.after(policyCal))
				LawyersUtils
						.populateError(ctx, "PriorActDatePross",
								"Prior Act Date cannot be later than Policy Effective Date");

		}

	}

	public List getErrorListFromResponse(Element element) {
		List children = element.getChildren();
		if (children == null)
			return new ArrayList();
		List errorList = new ArrayList();
		for (int i = 0; i < children.size(); i++) {
			Element child = (Element) children.get(i);
			String name = child.getName();
			String str = child.getTextTrim();
			errorList.add(str);
		}

		return errorList;
	}

	public Element checkIfErrorOutput(Object obj) throws Exception {
		Element outputElement = null;

		if (obj != null && obj instanceof Map) {

			Map responseMap = (Map) obj;
			if (responseMap != null) {

				String outputString = responseMap.get("OutputXML") != null ? responseMap
						.get("OutputXML").toString() : "";
				Element root = XMLUtils.parseXMLRootElement(outputString);
				outputElement = root.getChild("ValidationErrors");

				return outputElement;

			}

		}

		return outputElement;
	}

	public StringBuffer addListToInetErrorList(List list) throws Exception {

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			String errorStr = (String) list.get(i);
			buffer.append(errorStr);
			if (i < list.size() - 1)
				buffer.append(",");
		}
		return buffer;
	}

	public boolean checkForAnyQuoteWithInsideLimit(IContext ctx)
			throws Exception {

		boolean flag = false;

		List quotesList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementpdfquotelettergetQuoteOptedList", ctx);

		boolean IsClaimExpensesTypeFlag = false;

		if (quotesList != null) {
			for (int i = 0; i < quotesList.size(); i++) {
				Map map = (Map) quotesList.get(i);

				if (map.get("IsClaimExpensesType") != null
						&& "N".equals(map.get("IsClaimExpensesType").toString()))
					IsClaimExpensesTypeFlag = true;
			}
		}

		if (IsClaimExpensesTypeFlag)
			flag = true;

		return flag;

	}

	public boolean checkForFinalizedQuoteWithInsideLimit(IContext ctx)
			throws Exception {

		boolean flag = false;

		List quotesList = SqlResources
				.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementManualBoQueriesgetFinalizedDataForPolicyForm",
						ctx);

		boolean IsClaimExpensesTypeFlag = false;

		if (quotesList != null) {
			for (int i = 0; i < quotesList.size(); i++) {
				Map map = (Map) quotesList.get(i);

				if (map.get("IsClaimExpensesType") != null
						&& "N".equals(map.get("IsClaimExpensesType").toString()))
					IsClaimExpensesTypeFlag = true;
			}
		}

		if (IsClaimExpensesTypeFlag)
			flag = true;

		return flag;

	}

	public static boolean checkForKYMuncipalityTax(IContext ctx)
			throws Exception {
		boolean flag = false;

		Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"SqlStmts.firmviewgetAllPageFlags", ctx);

		if (objFirm != null && objFirm instanceof Map) {

			Map objMap = (Map) objFirm;

			String IsTaxCalculation = objMap.get("IsTaxCalculation") != null ? objMap
					.get("IsTaxCalculation").toString() : "";

			if ("Y".equals(IsTaxCalculation)) {
				flag = false;
			} else {

				boolean flag1 = checkIfMTTaxAmountPresent(ctx);

				if (flag1)
					flag = true;
			}
		}

		return flag;

	}

	public static boolean checkIfMTTaxAmountPresent(IContext ctx)
			throws Exception {

		boolean flag = false;

		Object objRatingTrace = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsRatingTrace", ctx);

		if (objRatingTrace != null && objRatingTrace instanceof List) {

			List ratingList = (List) objRatingTrace;

			for (int i = 0; i < ratingList.size(); i++) {

				Map objRatingMap = (Map) ratingList.get(0);

				boolean mtTaxpre = checkForMTTax(objRatingMap
						.get("XmlOutputDatafromRating") != null ? objRatingMap
						.get("XmlOutputDatafromRating").toString() : "");

				if (mtTaxpre) {
					flag = true;
					break;
				}

			}

		}

		return flag;

	}

	public static boolean checkForMTTax(String outXML) throws Exception {

		boolean taxPresent = false;

		if (outXML == null || outXML.equals(""))
			return taxPresent;

		Element out = getOutPutElement(outXML);

		double mtTax = getOutput(out);

		if (mtTax > 0)
			taxPresent = true;

		return taxPresent;

	}

	public static Element getOutPutElement(String outXML) throws Exception {
		Element outputElement = null;

		Element root = XMLUtils.parseXMLRootElement(outXML);
		outputElement = root.getChild("PremiumInfoLW");

		return outputElement;

	}

	public static double getOutput(Element element) {
		double mtTax = 0.0;
		List children = element.getChildren();
		if (children == null)
			return 0.0;
		List errorList = new ArrayList();
		for (int i = 0; i < children.size(); i++) {
			Element child = (Element) children.get(i);
			String name = child.getName();
			if (name.equals("MTTaxAmmount")) {
				String str = child.getTextTrim();
				mtTax = Double.parseDouble(str);
			}

		}

		return mtTax;
	}

	public static void validateComment(IContext ctx) throws Exception {
		if (ctx.get("CommentUploaded") == null)
			LawyersUtils.populateError(ctx, "CommentUploaded", "Enter comment");
		else if (ctx.get("CommentUploaded") != null
				&& "".equals(ctx.get("CommentUploaded").toString()))
			LawyersUtils.populateError(ctx, "CommentUploaded", "Enter comment");
		/*RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");*/
		RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");
		LawyersUtils.populateLastUpdateTimeStamp(ctx);

		if (ctx.get("LastUpdateUserID") != null)
			ctx.put("CommentUploadedBy", ctx.get("LastUpdateUserID"));

	}

	public static void checkIfApplicationIsComplete(IContext ctx)
			throws Exception {

		boolean flag = false;

		Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"SqlStmts.UserStatementpdfgetFirmLWFullDetails", ctx);
		
		String policyStatusKey = ctx.get("PolicyStatusKey") != null ? ctx.get("PolicyStatusKey").toString() : "0"; 
		// boolean isRealEstateRequired = false;
		// boolean isCorporateRequired = false ;
		// boolean isEvnironmentalRequired = false;
		// boolean isFinancialRequired = false ;
		// boolean isPlaintiffRequired = false;
		// boolean isTaxRequired = false ;
		// boolean isTitleRequired = false;
		// boolean isWillsRequired = false ;

		if (objFirm != null && objFirm instanceof Map) {

			Map objMap = (Map) objFirm;

			String isFirmSaved = objMap.get("IsFirmPage") != null ? objMap.get(
					"IsFirmPage").toString() : "";
			String isAOPSaved = objMap.get("IsAopPage") != null ? objMap.get(
					"IsAopPage").toString() : "";
			String isPracticePage = objMap.get("IsPracticePage") != null ? objMap
					.get("IsPracticePage").toString() : "";

			String isCoverageSaved = objMap.get("IsCoveragePage") != null ? objMap
					.get("IsCoveragePage").toString() : "";

			String isRealEstateResidentialPage = objMap
					.get("IsRealEstateResidentialPage") != null ? objMap.get(
					"IsRealEstateResidentialPage").toString() : "";

			String isRealEstateCommercialPage = objMap
					.get("IsRealEstateCommercialPage") != null ? objMap.get(
					"IsRealEstateCommercialPage").toString() : "";

			String isPlaintiffPage = objMap.get("IsPlaintiffPage") != null ? objMap
					.get("IsPlaintiffPage").toString() : "";

			String isWillsEstatePage = objMap.get("IsWillsEstatePage") != null ? objMap
					.get("IsWillsEstatePage").toString() : "";
					
			String isRenewFirmPage = objMap.get("IsRenewFirmPage") != null ? objMap
					.get("IsRenewFirmPage").toString() : "";

			/*
			 * String isCorporatePage = objMap.get("IsCorporatePage") != null ?
			 * objMap .get("IsCorporatePage").toString() : ""; String
			 * isEnvironmentalPage = objMap.get("IsEnvironmentalPage") != null ?
			 * objMap .get("IsEnvironmentalPage").toString() : ""; String
			 * isFinancialInsPage = objMap.get("IsFinancialInsPage") != null ?
			 * objMap .get("IsFinancialInsPage").toString() : "";
			 * 
			 * 
			 * String isTitlePage = objMap.get("IsTitlePage") != null ? objMap
			 * .get("IsTitlePage").toString() : ""; String isTaxPage =
			 * objMap.get("IsTaxPage") != null ? objMap.get(
			 * "IsTaxPage").toString() : "";
			 */
			if("1".equals(policyStatusKey)){
				if (isFirmSaved.equals("Y") && isAOPSaved.equals("Y")
						&& isPracticePage.equals("Y")
						&& isCoverageSaved.equals("Y")) {

				} else {
					LawyersUtils.populateError(ctx, "AcceptanceDate",
							"To continue, please complete the full application");
				}
			}else{
				
				if (isRenewFirmPage.equals("Y")) {

				} else {
					LawyersUtils.populateError(ctx, "AcceptanceDate",
							"To continue, please complete the full application");
				}
				
			}
					
			

			if (("1".equals(policyStatusKey) &&  isAOPSaved.equals("Y")) || ("2".equals(policyStatusKey) && "Y".equals(isRenewFirmPage))) {

				List aoplist = SqlResources
						.getSqlMapProcessor(ctx)
						.select("SqlStmts.UserStatementManualBoQueriespopulateAOPFields",
								ctx);

				Map covMap = new HashMap();
				if (aoplist != null) {
					Map map = new HashMap();
					for (int i = 0; i < aoplist.size(); i++) {
						map = (HashMap) aoplist.get(i);
						covMap.put("AOP_Percentage_" + map.get("AOPKey"),
								map.get("PercentageValue"));
					}
				}

				if (covMap != null) {

					String realCommercialPercentage = covMap
							.get("AOP_Percentage_20") != null ? covMap.get(
							"AOP_Percentage_20").toString() : "0";
					int realCommPerValue = Integer
							.parseInt(realCommercialPercentage);

					if (realCommPerValue > 0) {

						if (!"Y".equals(isRealEstateCommercialPage))
							LawyersUtils
									.populateError(ctx, "AcceptanceDate",
											"To continue, please complete the full application");
					}

					String realResiPercentage = covMap.get("AOP_Percentage_27") != null ? covMap
							.get("AOP_Percentage_27").toString() : "0";
					int realResiPerValue = Integer.parseInt(realResiPercentage);

					if (realResiPerValue > 0) {

						if (!"Y".equals(isRealEstateResidentialPage))
							LawyersUtils
									.populateError(ctx, "AcceptanceDate",
											"To continue, please complete the full application");
					}

					String plaintiffPercentage = covMap
							.get("AOP_Percentage_18") != null ? covMap.get(
							"AOP_Percentage_18").toString() : "0";
					int plainPerValue = Integer.parseInt(plaintiffPercentage);

					if (plainPerValue > 0) {

						if (!"Y".equals(isPlaintiffPage))
							LawyersUtils
									.populateError(ctx, "AcceptanceDate",
											"To continue, please complete the full application");
					}

					String willsPercentage = covMap.get("AOP_Percentage_24") != null ? covMap
							.get("AOP_Percentage_24").toString() : "0";
					int willsPerValue = Integer.parseInt(willsPercentage);

					if (willsPerValue > 0) {

						if (!"Y".equals(isWillsEstatePage))
							LawyersUtils
									.populateError(ctx, "AcceptanceDate",
											"To continue, please complete the full application");
					}
				}
			}

		}

	}

	public static void handleRequestParams(IContext ctx) throws Exception {

		ctx.remove("IsSubProducerFlow");

		if (ctx.get("ProducerCode") != null
				&& "SPACE".equals(ctx.get("ProducerCode").toString())) {

			ctx.put("ProducerCode", "");
			ctx.put("IsSubProducerPolicy", "N");
			ctx.put("ComingFromSubProducerLink", "N");

		} else if (ctx.get("ProducerCode") != null
				&& !"SPACE".equals(ctx.get("ProducerCode").toString())
				&& !"".equals(ctx.get("ProducerCode").toString())) {

			String producerCode = ctx.get("ProducerCode").toString();
			if (producerCode.length() > 50) {

				producerCode = producerCode.substring(0, 48);
				ctx.put("ProducerCode", producerCode);

			} else {
				ctx.put("ProducerCode", producerCode);
			}

			Object object = SqlResources
					.getSqlMapProcessor(ctx)
					.findByKey(
							"SqlStmts.UserStatementManualBoQueriesSubProducerData",
							ctx);
			if (object != null && object instanceof Map) {

				Map producerMap = (Map) object;
				if (producerMap != null) {

					if (producerMap.get("IsActive") != null
							&& "Y".equals(producerMap.get("IsActive")
									.toString())) {

						ctx.put("IsSubProducerFlow", "Y");

					}

				}

			}
		}
	}

	public void updateLicenseNumber(IContext ctx) throws Exception {

		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"UserDetails.findByKey", ctx);
		if (obj != null && obj instanceof Map) {

			Map objMap = (Map) obj;

			String licenseNumber = objMap.get("LicenseNumber") != null ? objMap
					.get("LicenseNumber").toString() : "";

//			Context newCtx = new Context();
//			newCtx.putAll(ctx);
			ctx.put("LicenseNumber", licenseNumber);
//	    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
//	    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));

//			SqlResources.getSqlMapProcessor(newCtx).update(
//					"SqlStmts.UserStatementManualBoQueriesupdateLicenseNumber",
//					newCtx);

		}

	}

	public static void validatePayment(IContext ctx) throws Exception {
		LawyersUtils.populateLastUpdateTimeStamp(ctx);
		if (ctx.get("PaymentMode") == null)
			LawyersUtils.populateError(ctx, "PaymentMode",
					"Choose any payment option");
		else if (ctx.get("PaymentMode") != null
				&& "".equals(ctx.get("PaymentMode").toString()))
			LawyersUtils.populateError(ctx, "PaymentMode",
					"Choose any payment option");		
				
		if(ctx.get("User") != null && ctx.get("User").toString().equals("insured")){
			return;
		}
		if(ctx.get("PaymentMode") != null && "finance".equals(ctx.get("PaymentMode"))){
			
			String ipfsQuoteNumber = ctx.get("ipfsQuoteNumber") != null ? ctx.get("ipfsQuoteNumber").toString() : "";
			if("".equals(ipfsQuoteNumber)){
				LawyersUtils.populateError(ctx, "PaymentMode",
						"Please enter the IPFS Quote Number");
			}
		}

	}

	public static void showSupplementsTabMethod(IContext ctx) throws Exception {

		List aoplist = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields", ctx);
		ctx.put("showSupplementsTab", null);

		Map covMap = new HashMap();
		if (aoplist != null) {
			Map map = new HashMap();
			for (int i = 0; i < aoplist.size(); i++) {
				map = (HashMap) aoplist.get(i);
				covMap.put("AOP_Percentage_" + map.get("AOPKey"),
						map.get("PercentageValue"));
				covMap.put("AOPCommentDesc_" + map.get("AOPKey"),
						map.get("AOPCommentDesc"));
			}
		}

		if ((covMap.get("AOP_Percentage_18") != null && (Integer
				.parseInt(covMap.get("AOP_Percentage_18").toString()) > 0))
				|| (covMap.get("AOP_Percentage_20") != null && (Integer
						.parseInt(covMap.get("AOP_Percentage_20").toString()) > 0))
				|| (covMap.get("AOP_Percentage_24") != null && (Integer
						.parseInt(covMap.get("AOP_Percentage_24").toString()) > 0))
				|| (covMap.get("AOP_Percentage_27") != null && (Integer
						.parseInt(covMap.get("AOP_Percentage_27").toString()) > 0))) {
			ctx.put("showSupplementsTab", "Y");
		} else {
			ctx.put("showSupplementsTab", null);
		}
	}

	public static boolean checkIfSubProducerExist(IContext ctx)
			throws Exception {
		//Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("PolicyTransaction.findByKey", ctx);
		
		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetProducerCode", ctx);		
		boolean producerCodeExist = false;

		if (obj != null && obj instanceof Map) {

			Map objMap = (Map) obj;
			ctx.putAll(objMap);
			if (objMap.get("ProducerCode") != null && !"".equals(objMap.get("ProducerCode").toString())) {
				producerCodeExist = true;
				ctx.put("ProducerCode",objMap.get("ProducerCode"));
			} else 
				producerCodeExist = false;
			
		} else 
			producerCodeExist = false;
		obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesSubProducerData", ctx);		

		if (obj != null && obj instanceof Map) {

			Map objMap = (Map) obj;
			ctx.putAll(objMap);
		}
		return producerCodeExist;
		
	}

	public static void validateNumberFields(IContext ctx, String field)
			throws Exception {

		if (field != null && !"".equals(field)) {
			try {
				Integer.parseInt(field);
			} catch (Exception e) {
				LawyersUtils.populateError(ctx, field, "Invalid number");
			}
		}

	}

	public static void showCityDropDown(IContext ctx) throws Exception {

		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"State.findByKey", ctx);

		if (obj != null && obj instanceof Map) {

			Map objMap = (Map) obj;

			String showCityDropDown = objMap.get("ShowCityDropDown") != null ? objMap
					.get("ShowCityDropDown").toString() : "";

			if (showCityDropDown.equals("Y")) {
				ctx.put("ShowCityDropDown", "Y");
			} else {
				ctx.put("ShowCityDropDown", "N");
			}
			//ctx.put("ShowCityDropDown", "Y");

		}

	}

	public static boolean isLeapYear() {
		GregorianCalendar cal = new GregorianCalendar();
		return cal.isLeapYear(Calendar.getInstance().get(Calendar.YEAR));
	}

	public static boolean isLeapYear(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		return cal.isLeapYear(date.getYear());
	}

	public static boolean validatePageOnAcceptIssue(IContext ctx)
			throws Exception {

		LawyersValidationUtils utils = new LawyersValidationUtils();

		utils.checkIfApplicationIsComplete(ctx);
		utils.checkIfOneOptionIsFinalised(ctx);

		return true;
	}

	public void checkIfOneOptionIsFinalised(IContext ctx) throws Exception {

		List quotesList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementpdfquotelettergetQuoteOptedList", ctx);

		if (quotesList != null && quotesList.size() > 0) {
			boolean flag = false;
			int count = 0;
			for (int i = 0; i < quotesList.size(); i++) {

				Map map = (Map) quotesList.get(i);
				String isThisOptionFinalised = map.get("IsThisOptionFinalised") != null ? map
						.get("IsThisOptionFinalised").toString() : "";
				if (isThisOptionFinalised.equals("Y")) {
					flag = true;
					count++;
				}
			}
			if (flag && count == 1) {

			} else {
				LawyersUtils.populateError(ctx, "ApplicationComplete",
						"Atleast one option should be finalised.");
			}

		}

	}

	public String decrypt(String message) throws Exception {
		if (message == null || message.trim().length() == 0)
			throw new IllegalArgumentException("Encrypted value is required");

		final MessageDigest md = MessageDigest.getInstance("md5");
		final byte[] digestOfPassword = md.digest(key.getBytes("utf-8"));
		final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		for (int j = 0, k = 16; j < 8;) {
			keyBytes[k++] = keyBytes[j++];
		}
		final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		final Cipher decipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		decipher.init(Cipher.DECRYPT_MODE, key);
		final String normalizedMessage = message.replace(' ', '+').replace("\r", "").replace("\n", "");
		final byte[] encData = Base64.getDecoder().decode(normalizedMessage);
		if (encData.length == 0 || encData.length % 8 != 0)
			throw new IllegalArgumentException("Invalid encrypted value");
		final byte[] plainText = decipher.doFinal(encData);
		return new String(plainText, "UTF-8");
	}

	public String encryptForUrl(String message) throws Exception {
		return URLEncoder.encode(encrypt(message), StandardCharsets.UTF_8.name());
	}

	public static void decryptyPolicyKey(IContext ctx) throws Exception {
		LawyersValidationUtils o = new LawyersValidationUtils();
		String policyKey = ctx.get("EncryptedPolicyKey") != null ? ctx.get(
				"EncryptedPolicyKey").toString() : "";
		String decryptedPolicyKey = o.decrypt(policyKey);
		Integer.parseInt(decryptedPolicyKey);
		ctx.put("PolicyKey", decryptedPolicyKey);
		ctx.put("ParentPolicyKey", decryptedPolicyKey);
	}
	
	public static boolean validateRenewalReview(Context ctx) throws Exception {

		if (ctx.get("IsRenewalReview") == null)
			LawyersUtils.populateError(ctx, "IsRenewalReview",
					"Please Indicate Yes or No");

		if (ctx.get("IsRenewalReview") != null
				&& "".equals(ctx.get("IsRenewalReview").toString()))
			LawyersUtils.populateError(ctx, "IsRenewalReview",
					"Please Indicate Yes or No");

		if (ctx.get("IsRenewalReview") != null
				&& "Y".equals(ctx.get("IsRenewalReview").toString())) {

			if (ctx.get("RenewalReviewComments") == null)
				LawyersUtils.populateError(ctx, "RenewalReviewComments",
						"Please provide comments.");

			if (ctx.get("RenewalReviewComments") != null
					&& "".equals(ctx.get("RenewalReviewComments").toString()))
				LawyersUtils.populateError(ctx, "RenewalReviewComments",
						"Please provide comments.");

		}

		return true;
	}

	public void checkIsValidQuoteNumber(IContext ctx) throws Exception {

		Map policyMap = new HashMap();

		String quoteNumber_admin = ctx.get("QuoteNumber_admin") != null ? ctx.get("QuoteNumber_admin").toString() : "";
		ctx.put("quoteNumber_admin", quoteNumber_admin);
		if("Lwy".equals(ctx.get("ProjectType_admin")))
			policyMap = (Map) SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetPolicyFromQuoteNumberLW",ctx);
		else
			policyMap = (Map) SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetPolicyFromQuoteNumberACC",ctx);
		
		if (policyMap != null && policyMap.size() > 0) {
			/*String accountname = policyMap.get("AccountName_admin") != null ? policyMap
					.get("AccountName_admin").toString() : "";
			ctx.put("AccountName_admin", accountname);*/
			ctx.putAll(policyMap);
		} else {

			LawyersUtils.populateError(ctx, "InvaliQuoteNum","Invalid Quote Number");

		}

	}

	/*public static void main(String mm[]) {
		Date date = new Date();

		LawyersValidationUtils.isLeapYear(date);
	}*/

	public boolean checkIsQuoteAbove1M1MAnd9010Policy(IContext ctx)
			throws Exception {

		boolean flag = false;

		String Is9010Policy = "N";

		Map map = (Map) SqlResources.getSqlMapProcessor(ctx).findByKey(
				"FirmLW.findByKey", ctx);
		if (map != null && map.size() > 0) {
			Is9010Policy = map.get("Is9010Policy") != null ? map.get(
					"Is9010Policy").toString() : "N";
		}

		Object obj = SqlResources
				.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementManualBoQueriesgetLimitAndDeductibleAllQuotes",
						ctx);
		if (obj != null && obj instanceof List) {

			List quoteList = (List) obj;
			if (quoteList.size() > 0) {

				for (int i = 0; i < quoteList.size(); i++) {

					Map quoteMap = (Map) quoteList.get(i);
					String aggregateLimit = quoteMap.get("AggregateLimit") != null ? quoteMap
							.get("AggregateLimit").toString() : "0";
					String occuranceLimit = quoteMap.get("OccuranceLimit") != null ? quoteMap
							.get("OccuranceLimit").toString() : "0";

					int agglimit = Integer.parseInt(aggregateLimit);
					int occlimit = Integer.parseInt(occuranceLimit);

					if (agglimit > 1000000 || occlimit > 1000000) {

						if (Is9010Policy.equals("Y")) {
							flag = true;
							break;
						}
					}

				}

			}

		}

		return flag;
	}

	public boolean checkIfSelectedQuote9010(IContext ctx) throws Exception {
		boolean flag = false;
		String Is9010Policy = "N";

		Map map = (Map) SqlResources.getSqlMapProcessor(ctx).findByKey(
				"FirmLW.findByKey", ctx);
		if (map != null && map.size() > 0) {
			Is9010Policy = map.get("Is9010Policy") != null ? map.get(
					"Is9010Policy").toString() : "N";
		}

		if (Is9010Policy.equals("Y")) {
			
			List quoteList = SqlResources
					.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementManualBoQueriesgetLimitAndDeductibleAllQuotes",
							ctx);
			
//			List quoteList = SqlResources.getSqlMapProcessor(ctx).select(
//					"SqlStmts.UserStatementsaveAccountstmtsgetAllQuotes", ctx);

			if (quoteList != null && quoteList.size() > 0) {

				for (int i = 0; i < quoteList.size(); i++) {

					Map quoteMap = (Map) quoteList.get(i);

					if (quoteMap != null) {

						String isQuoteSelected = ctx
								.get("IsQuoteSelected_" + i) != null ? ctx.get(
								"IsQuoteSelected_" + i).toString() : "";

						if (isQuoteSelected.equals("Y")
								|| "on".equals(isQuoteSelected)
								|| "N".equals(isQuoteSelected)) {

							String aggregateLimit = quoteMap
									.get("AggregateLimit") != null ? quoteMap
									.get("AggregateLimit").toString() : "0";
							String occuranceLimit = quoteMap
									.get("OccuranceLimit") != null ? quoteMap
									.get("OccuranceLimit").toString() : "0";

							int agglimit = Integer.parseInt(aggregateLimit);
							int occlimit = Integer.parseInt(occuranceLimit);

							if (agglimit > 1000000 || occlimit > 2000000) {
								flag = true;
								break;
							}
						}

					}
				}

			}
		}
		return flag;
	}
	
	public static String getSpecialIssuanceEmail(IContext ctx) {
		
		String email = "";
		try {
			email = SystemProperties.getInstance().getString("appl." +ctx.getProject()+ ".specialissue.emailid");
		} catch (Exception e) {
			logger.debug("Exception in getting special issuance email id " + e);
		}
		return email ;
		
	}
	
	public static boolean checkIfSpecialIssuance(IContext ctx) throws Exception{
		boolean specialIssuance = false;
		Object ob = SqlResources.getSqlMapProcessor(ctx).findByKey("FirmLW.findByKey", ctx);
		if(ob != null && ob instanceof Map){
			Map map = (Map)ob;
			String strSpecialIssuance = map.get("IsSpecialIssuance") != null ? map.get("IsSpecialIssuance").toString() : "N"; 			
			if(strSpecialIssuance.equals("Y")){
				specialIssuance = true ;
			}			
		}
		return specialIssuance ;
	}
	
	public static boolean validateAdditionalForms(IContext ctx) throws Exception{
		
		if(ctx.get("IsPredecessorFormSelected") == null){
			
			ctx.put("PriorActsDate_1", null);
			ctx.put("PriorActsDate_2", null);
			ctx.put("PriorActsDate_3", null);
			
		}else if(ctx.get("IsPredecessorFormSelected") != null && !"Y".equals(ctx.get("IsPredecessorFormSelected").toString())){
			
			ctx.put("PriorActsDate_1", null);
			ctx.put("PriorActsDate_2", null);
			ctx.put("PriorActsDate_3", null);
		}
		
		if(ctx.get("PriorActsDate_1") == null ){
			ctx.put("PriorActsDate_1", null);
		}
		
		if(ctx.get("PriorActsDate_1") != null && "".equals(ctx.get("PriorActsDate_1").toString())){
			ctx.put("PriorActsDate_1", null);
		}
		if(ctx.get("PriorActsDate_2") == null ){
			ctx.put("PriorActsDate_2", null);
		}
		
		if(ctx.get("PriorActsDate_2") != null && "".equals(ctx.get("PriorActsDate_2").toString())){
			ctx.put("PriorActsDate_2", null);
		}
		if(ctx.get("PriorActsDate_3") == null ){
			ctx.put("PriorActsDate_3", null);
		}
		
		if(ctx.get("PriorActsDate_3") != null && "".equals(ctx.get("PriorActsDate_3").toString())){
			ctx.put("PriorActsDate_3", null);
		}
		
		return true;
		
	}
	
	public static boolean checkForFinalisedQuoteWithInsideLimit(IContext ctx)
			throws Exception {

		boolean flag = false;
		List quotesList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementManualBoQueriesgetIsFinalisedQuoteWithInsideLimit", ctx);

		boolean IsClaimExpensesTypeFlag = false;
		if (quotesList != null) {
			for (int i = 0; i < quotesList.size(); i++) {
				Map map = (Map) quotesList.get(i);
				if (map.get("IsClaimExpensesType") != null
						&& "N"
								.equals(map.get("IsClaimExpensesType")
										.toString()))
					IsClaimExpensesTypeFlag = true;
			}
		}

		if (IsClaimExpensesTypeFlag)
			flag = true;

		return flag;
	}
	public String encrypt(String message) throws Exception {
		final MessageDigest md = MessageDigest.getInstance("md5");
		final byte[] digestOfPassword = md.digest(key.getBytes("utf-8"));
		final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		for (int j = 0, k = 16; j < 8;) {
			keyBytes[k++] = keyBytes[j++];
		}

		final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
		final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		final byte[] plainTextBytes = message.getBytes("utf-8");
		final byte[] cipherText = cipher.doFinal(plainTextBytes);
		final String encodedCipherText = Base64.getEncoder().encodeToString(cipherText);

		return encodedCipherText;
	}
	
	/*public String handleTabs(IContext ctx) throws Exception {
		Context localCtx = new Context();
		localCtx.setProject(ctx.getProject());
		
		ComponentImpl compImpl = ComponentResources.getInstance(ctx).getComponent(ctx.get("from_page").toString());
		localCtx.putAll(ctx);
		localCtx.put("inet_method", "save");
		compImpl.validate(localCtx);
		                        
		compImpl.performAction(localCtx);
		
		if(localCtx.get("inet_errors_list") == null)
			ctx.put("WORKFLOW_FORWARD", ctx.get("object_name"));
		else
			ctx.put("WORKFLOW_FORWARD", ctx.get("from_page"));
		
		return SPECIALCHARACTER;
	}*/
	
	public static String getPlaintiffSup(Object mainPlaintiff,Object plaintiffSuppAop) throws Exception {

		int plaintiffSum =LawyersValidationUtils.convertStringToInteger(plaintiffSuppAop != null ?
						plaintiffSuppAop.toString() : "");

		float plaintiff = plaintiffSum * LawyersValidationUtils.convertStringToInteger(mainPlaintiff != null ? mainPlaintiff.toString() : "");

		plaintiff = plaintiff / 100;
		//int plaintiffPercentage = (int) Math.round(plaintiff);
		return String.valueOf(plaintiff);

	}
	
	/**
	 * This method validates the input email address with EMAIL_REGEX pattern
	 * 
	 * @param email
	 * @return boolean
	 */
	public static boolean validateEmail(String email){
		// Email Regex java
		final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

		// static Pattern object, since pattern is fixed
		Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

		// non-static Matcher object because it's created from the input String
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public static void main(String ...arg)
	{
		/*try
		{
			String str="Z/Kc FgR41Q=";
			if(str.contains(" "))
			str=str.replace(' ','+');
			System.out.println(new LawyersValidationUtils().decrypt(str));
		}
		catch(Exception e)
		{e.printStackTrace();}*/
	}
	
	public static void validatePremiumCalulationForFinancialEndorsement(Context ctx)
	{
		try
			{
			if(!"calculatePremium".equalsIgnoreCase(ctx.get("inet_method").toString()))
			{
				int transactionTypeKey=ctx.get("TransactionTypeKey")!=null ?Integer.parseInt(ctx.get("TransactionTypeKey").toString()):0;
				if(transactionTypeKey==14)
				{
					Object objRule = RuleUtils.executeRule(ctx, "LawyersRule.validatePremiumCalculationAddchaneAttorney");
					logger.debug("Going to validate Premium Calculation for AddchaneAttorney ");
					if(objRule != null)
					{
						Boolean rule = (Boolean)objRule;
						if(rule==false)
							LawyersUtils.populateError(ctx, "premiumCalculationError","Premium calulation required for financial endorsement.");
					}
					
				}
				if(transactionTypeKey==15)
				{
					Object objRule = RuleUtils.executeRule(ctx, "LawyersRule.validatePremiumCalculationdeleteAttorney");
					logger.debug("Going to validate Premium Calculation for Delete attorney ");
					if(objRule != null)
					{
						Boolean rule = (Boolean)objRule;
						if(rule==false)
							LawyersUtils.populateError(ctx, "premiumCalculationError","Premium calulation required for financial endorsement.");
					}
					
				}
			}
				
			}
		catch(Exception e)
		{
			logger.debug("Error occured while validate Premium Calulation For Financial Endorsement");
		}
	}
	
	public static void ValidateStatus(Context ctx)
	{String policyKeyString=null;
		try {
			if(ctx.get("PolicyKey")!=null) {
				
				try
				{
					 policyKeyString=ctx.get("PolicyKey").toString();
					 if(policyKeyString.contains(" "))
							policyKeyString=policyKeyString.replace(' ','+');
					int policyKey=Integer.parseInt(ctx.get("PolicyKey").toString());
					ctx.put("PolicyKey",policyKey);
				}
				catch(NumberFormatException nfe)
				{
					
						String policyKey= new LawyersValidationUtils().decrypt(policyKeyString);
						ctx.put("PolicyKey",policyKey);
					
				}
				Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetStatusOfPolicy", ctx);
				Map statusDetails = (Map)obj;
				int statusKey=statusDetails.get("StatusKey")!=null && !statusDetails.get("StatusKey").equals(HtmlConstants.EMPTY)?Integer.parseInt(statusDetails.get("StatusKey").toString()):0;
				String statusDesc=statusDetails.get("StatusDesc")!=null && !statusDetails.get("StatusDesc").equals(HtmlConstants.EMPTY)?statusDetails.get("StatusDesc").toString():"No Status";
				String headerStatusDescUpdated=ctx.get("headerStatusDescUpdated")!=null && !ctx.get("headerStatusDescUpdated").equals(HtmlConstants.EMPTY)?ctx.get("headerStatusDescUpdated").toString():"No Status";
				if(!"No Status".equals(headerStatusDescUpdated) && headerStatusDescUpdated.contains("(")) {
					headerStatusDescUpdated=headerStatusDescUpdated.substring(0,headerStatusDescUpdated.indexOf('(')).trim();
					if(!statusDesc.equals(headerStatusDescUpdated)){
						/*LawyersUtils.populateError(ctx, "StatusUpdate","Policy status has updated already.");*/	
						ctx.put("IsPolicyStatusUpdated", "Y");
					}
				}
			}
		} catch(Exception e) {
			logger.debug("error occured while validating status :"+e);
		}
	}

	public static boolean validateZohoStatusChange(Context ctx) throws Exception 
	{
		try {
			if(ctx.get("PolicyKey")!=null) {
				
				ctx.put("ZohoNameClearancePolicyKey",ctx.get("PolicyKey"));
				Map nameClearanceDetails = (Map)SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetZohoNameClearanceReferralCountTriggered", ctx);
				String nameClearanceFlag = "";
				if(nameClearanceDetails != null){
					nameClearanceFlag = nameClearanceDetails.get("ClearStatusFlag")!=null && !HtmlConstants.EMPTY.equals(nameClearanceDetails.get("ClearStatusFlag").toString())?nameClearanceDetails.get("ClearStatusFlag").toString():"";
				}
				if(!"N".equals(nameClearanceFlag)){
					
//					boolean isInsured = false;
//					Object objInsured = RuleUtils.executeRule(ctx, "LawyersRule.isInsured");
//					if (objInsured != null && objInsured instanceof Boolean) {
//						isInsured = (Boolean) objInsured;
//					}
				
					Map statusDetails = (Map)SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetStatusOfPolicy", ctx);
					int statusKey=statusDetails.get("StatusKey")!=null && !statusDetails.get("StatusKey").equals(HtmlConstants.EMPTY)?Integer.parseInt(statusDetails.get("StatusKey").toString()):0;
					String statusDesc=statusDetails.get("StatusDesc")!=null && !statusDetails.get("StatusDesc").equals(HtmlConstants.EMPTY)?statusDetails.get("StatusDesc").toString():"No Status";
					String headerStatusDescUpdated=ctx.get("headerStatusDescUpdated")!=null && !ctx.get("headerStatusDescUpdated").equals(HtmlConstants.EMPTY)?ctx.get("headerStatusDescUpdated").toString():"No Status";
					headerStatusDescUpdated=(headerStatusDescUpdated.contains("(")) ? headerStatusDescUpdated.substring(0,headerStatusDescUpdated.indexOf('(')).trim() : headerStatusDescUpdated;
					
					if("Y".equals(ctx.get("isRequotePolicy") == null ? "" : ctx.get("isRequotePolicy").toString()) && "New".equals(statusDesc))
						return false;
					
					if((!"Issued".equals(statusDesc)) && (!statusDesc.equals(headerStatusDescUpdated) || ("Indication".equals(headerStatusDescUpdated) && "Y".equals(ctx.get("IsAccountDetailChanged") == null ? "" : ctx.get("IsAccountDetailChanged").toString())))) //isInsured && (ctx.get("isNameClearanceRequired") == null || "".equalsIgnoreCase(ctx.get("isNameClearanceRequired").toString().trim()) || ctx.get("isNameClearanceRequired").toString().isEmpty())
						return true;
				
				}
			}
		} catch(Exception e) {
			logger.debug("error occured while validating status :"+e);
		}
		return false;
	}
	
	public static void validateNameClearanceQQIndicationFlagsCleared(Context ctx) {

		try {
			ctx.put("ZohoNameClearancePolicyKey",ctx.get("PolicyKey"));
			Map nameClearanceDetails = (Map)SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetZohoNameClearanceReferralCountTriggered", ctx);
			if(nameClearanceDetails != null){
				ctx.putAll(nameClearanceDetails);
			}
			
			boolean isZohoNameClearanceQQIndicationPopupFlag = false;
			Object objNameClearanceQQIndicationPopupFlag = RuleUtils.executeRule(ctx, "LawyersRule.isNameClearanceQQIndicationPopupFlag");
			if (objNameClearanceQQIndicationPopupFlag != null && objNameClearanceQQIndicationPopupFlag instanceof Boolean) {
				isZohoNameClearanceQQIndicationPopupFlag = (Boolean) objNameClearanceQQIndicationPopupFlag;
			}

			if (isZohoNameClearanceQQIndicationPopupFlag) {
				LawyersUtils.populateError(ctx, "flagsNotCleard","All Referrals flags are not cleared for the policy.");
			}

		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}

	}
	
	public static boolean validateExistingZohoRecordInProdDB(Context ctx){
  		try{
  			if(ctx.get("MarketableProductKey") instanceof String)
  				ctx.put("MarketableProductKey",Integer.parseInt(ctx.get("MarketableProductKey").toString()));

    		ctx.put("PolicyKey",Integer.parseInt(ctx.get("PolicyKey").toString()));
    		List existingNameClearanceDetails = SqlResources.getSqlMapProcessor(ctx).select("Policy.GetZohoNameClearanceFlagInJack_p", ctx);
    		ctx.put("PolicyKey",String.valueOf(ctx.get("PolicyKey")));
			if(existingNameClearanceDetails != null && !existingNameClearanceDetails.isEmpty()){
				return true;
			}
    		
  		} catch(Exception e){
  			logger.error("Unexpected error", e);
  		}
		return false;
  	}
	
	public static void validateUserLogin(Context ctx) throws Exception 
	{
		try {
			if(ctx.get("AccountEmail") != null){
				ctx.put("UserEmail", ctx.get("AccountEmail").toString());
			}
			List existingUserSessionLoginDetails = SqlResources.getSqlMapProcessor(ctx).select("UserSessionLogin.ValidateUserLogin_p", ctx);
    		if(existingUserSessionLoginDetails != null && !existingUserSessionLoginDetails.isEmpty()){
				ctx.put("IS_SESSION_NEW", "N");
				Map map = (HashMap) existingUserSessionLoginDetails.get(0);
				ctx.put("UserLoginID", map.get("UserLoginID"));
				ctx.put("UserEmail", null);
				ctx.put("UserLoginKey", null);
				SqlResources.getSqlMapProcessor(ctx).update("UserSessionLogin.update", ctx);
			}
		} catch(Exception e) {
			logger.error("Unexpected error", e);
			logger.debug("error occured while validating status :"+e);
		}
	}
	public static boolean validateFileNameForSpecialChar(String inputString) {
		boolean result=false;
	     if (inputString == null || inputString.trim().isEmpty()) 
	           return result;
	     else if(inputString.length()>25)
	    	 return result;
	     else
	     {
	        Pattern p = Pattern.compile ("[!@#$%&*()+=|<>?{}\\[\\]~-]");
	     Matcher m = p.matcher(inputString);
	 	 boolean flag = m.find();
	     if (flag == true )
	    	 result=false;
	     else
	    	 result=true;
	     return result;
	     }
	     
	 }

	public static void validatePLPolicynumberForBrokerage(Context ctx)
	{	
		boolean result=false;
		try
		{ 
			String plPolicyNumber=!ctx.get("PLPolicyNumber").equals(HtmlConstants.EMPTY) && ctx.get("PLPolicyNumber")!=null?ctx.get("PLPolicyNumber").toString():"";
			if("".equals(plPolicyNumber) || plPolicyNumber.length()<10)
			{
				LawyersUtils.populateError(ctx, "InvalidPLPolicyNumberError","Policy number not found.");
				return;
			}
			//new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,AccountKey,litigationNewImplDate,IsCCBFlag,RenewalSupplementNewImplDate");
			List validatePolicyNumber =(List)SqlResources.getSqlMapProcessor(ctx).select("BrokeragePolicy.ValidatePLPolicyNumberForBrokerage_p", ctx);
			
			if(validatePolicyNumber!=null && validatePolicyNumber.size()>0)
			ctx.putAll((Map) validatePolicyNumber.get(0));
			String isValidPLPolicyNumber=!ctx.get("isValidPLPolicyNumber").equals(HtmlConstants.EMPTY) && ctx.get("isValidPLPolicyNumber")!=null?ctx.get("isValidPLPolicyNumber").toString():"";
			if("Y".equals(isValidPLPolicyNumber))
			{
				result=true;
			//ctx.put("PLEffectiveDateNew", "2020-04-21");
			}
			if("N".equals(isValidPLPolicyNumber))
			{
				ctx.put("PolicyEffectiveDate", null);
				ctx.put("PolicyExpirationDate", null);
			}
			
		}
		catch(Exception e)
		{
			logger.error("Error occured while validating PL Policy Number for Brokerage");
			
		}
		if(result==false)
		{
			LawyersUtils.populateError(ctx, "InvalidPLPolicyNumberError","Policy number not found.");
		}
		//return result;
	}
	
	public static void validateQuoteLimitIncrease(Context ctx)
	{
		
		try
		{
			if("saveQuote".equals(ctx.get("inet_method").toString()))
			{
				List dataList =(List)SqlResources.getSqlMapProcessor(ctx).select("FirmLW.ValidateQuoteLimitIncrease_p", ctx);
				
				if(dataList!=null && dataList.size()>0)
				ctx.putAll((Map) dataList.get(0));
				String limitIncreaseFlag=!ctx.get("limitIncreaseFlag").equals(HtmlConstants.EMPTY) && ctx.get("limitIncreaseFlag")!=null?ctx.get("limitIncreaseFlag").toString():"";
			if("2".equals(ctx.get("PolicyStatusKey").toString()) && "Y".equals(limitIncreaseFlag))
			{
				LawyersUtils.populateError(ctx, "limitIncreaseFlagError"," C & F is not allowing any increase in limit.");
			}
			}
		}
		catch(Exception e)
		{
		logger.error("exception occurred while validating imit increase"+e);	
		}
		
		
	}

	@SuppressWarnings("unchecked")
	public void validateBrokerageSearch(IContext ctx) throws Exception {

		if (ctx.get("BRPolicyEffectiveDate_search") != null && !"".equals(ctx.get("BRPolicyEffectiveDate_search").toString())) {
			String strDate = ctx.get("BRPolicyEffectiveDate_search").toString();
			LawyersUtils.validateLeapYearDate(ctx, "BRPolicyEffectiveDate_search", strDate);
			
			String d = ctx.get("BRPolicyEffectiveDate_searchTo") != null ? ctx.get("BRPolicyEffectiveDate_searchTo").toString() : "";
			if("".equals(d)){				
				LawyersUtils.populateError(ctx, "PolicyEffectiveDate_searchTo", "Date is a required field");				
			}			
		}
		
		if(ctx.get("BRPolicyEffectiveDate_searchTo") != null && !"".equals(ctx.get("BRPolicyEffectiveDate_searchTo").toString())){
			String strDate = ctx.get("BRPolicyEffectiveDate_searchTo").toString();
			LawyersUtils.validateLeapYearDate(ctx, "BRPolicyEffectiveDate_searchTo", strDate);
			
			
			String d = ctx.get("BRPolicyEffectiveDate_search") != null ? ctx.get("BRPolicyEffectiveDate_search").toString() : "";
			if("".equals(d)){				
				LawyersUtils.populateError(ctx, "PolicyEffectiveDate_search", "Date is a required field");				
			}
			
		}
		String brAccountNamesearch=(ctx.get("BRAccountNameSearch") == null || "".equals(ctx.get("BRAccountNameSearch"))) ? "" : ctx.get("BRAccountNameSearch").toString().trim();
		
		if(brAccountNamesearch.contains("'"))
			brAccountNamesearch=brAccountNamesearch.replace("'", "''");
		
		ctx.put("BRAccountNameSearch", brAccountNamesearch);
		ctx.put("BRPolicyEffectiveDate_search", (ctx.get("BRPolicyEffectiveDate_search") == null || "".equals(ctx.get("BRPolicyEffectiveDate_search"))) ? "" : ctx.get("BRPolicyEffectiveDate_search").toString().trim());
		ctx.put("BRPolicyEffectiveDate_searchTo", (ctx.get("BRPolicyEffectiveDate_searchTo") == null || "".equals(ctx.get("BRPolicyEffectiveDate_searchTo"))) ? "" : ctx.get("BRPolicyEffectiveDate_searchTo").toString().trim());
		ctx.put("BRStateCode_search", (ctx.get("BRStateCode_search") == null || "".equals(ctx.get("BRStateCode_search"))) ? "" : ctx.get("BRStateCode_search").toString().trim());
		ctx.put("BRStatusKey_search", (ctx.get("BRStatusKey_search") == null || "".equals(ctx.get("BRStatusKey_search"))) ? "" : ctx.get("BRStatusKey_search").toString().trim());
		ctx.put("BRPolicyNumberSearch", (ctx.get("BRPolicyNumberSearch") == null || "".equals(ctx.get("BRPolicyNumberSearch"))) ? "" : ctx.get("BRPolicyNumberSearch").toString().trim());
	}

	 public static void mailSentBeyondWorkQCovrageDays(Context ctx) throws Exception {

		try{
			String emailID = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".mailidbeyondworkqcovragedays");
			String subject = "";
			String body = "";
			
			if ("".equals(emailID))
				return;
			
			String productionEnv = "N";
			try {
				productionEnv = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production");
			} catch (Exception e) {
				logger.error("error in geeting production environment");
			}
			
			if ("N".equals(productionEnv)){
				emailID = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.email");
				logger.debug("email id------->"+emailID);
			}
			
			Context newCtx = new Context();
	    	newCtx.setProject(ctx.getProject());
	    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
	    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
	    	newCtx.put("UserNo", ctx.get("UserNo"));
	    	newCtx.put("PolicyKey", ctx.get("PolicyKey"));
	    	newCtx.put("mailSentBeyondWorkQCovrageDays", ctx.get("mailSentBeyondWorkQCovrageDays").toString());
			
			Object obj = SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementManualBoQueriesmailSentBeyondWorkQCovrageDays", newCtx);
			
			if(obj != null && obj instanceof List){
	    		List list = (List)obj;
	    		for(int i=0; i<list.size(); i++){
	    			Map map = (Map)list.get(i);
	    			subject = map.get("subject").toString();
	    			body = map.get("body").toString();
	    		}
			}
			
			body = body.replace("#Firm_Name#", ctx.get("AccountName").toString());
			body = body.replace("#Quote_Number#", ctx.get("QuoteNumber").toString());
			body = body.replace("#Effective_Date#", ctx.get("PolicyEffectiveDate").toString().split(" ")[0]);

			logger.debug("Mail is going---- ");
			MailSender mail = new MailSender();
			mail.setToAdd(emailID);
			mail.setSubject(subject);
			mail.setContentType("text/html");
			mail.setBody(body);
			mail.sendMail((Context) ctx);
			logger.debug("Mail has sent---- ");
			
			SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesUpdateMailSentBeyondWorkQCovrageDaysFlag", ctx);
			
		} catch(Exception e) {
			logger.error("Error occured while Update MailSentBeyondWorkQCovrageDays Flag");
		}

	}
	 
	 public static void mailSentAfterPayment(Context ctx) throws Exception {

		try{
			String emailID = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".mailsentafterpayment");
			String subject = "";
			String body = "";
			String paymentMode = "";
			String firstReviewUserEmail = "";
			
			if ("".equals(emailID))
				return;
			
			String productionEnv = "N";
			try {
				productionEnv = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production");
			} catch (Exception e) {
				logger.error("error in geeting production environment");
			}
			
			if ("N".equals(productionEnv)){
				emailID = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.email");
				logger.debug("email id------->"+emailID);
			}
			
			Context newCtx = new Context();
	    	newCtx.setProject(ctx.getProject());
	    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
	    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
	    	newCtx.put("UserNo", ctx.get("UserNo"));
	    	newCtx.put("PolicyKey", ctx.get("PolicyKey"));
	    	newCtx.put("mailSentAfterPayment", ctx.get("mailSentAfterPayment").toString());
	    	
	    	Object getFirstReviewUserEmailObj = SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementManualBoQueriesgetFirstReviewUserEmail", newCtx);
			
			if(getFirstReviewUserEmailObj != null && getFirstReviewUserEmailObj instanceof List){
	    		List getFirstReviewUserEmailList = (List)getFirstReviewUserEmailObj;
	    		for(int i=0; i<getFirstReviewUserEmailList.size(); i++){
	    			Map getFirstReviewUserEmailMap = (Map)getFirstReviewUserEmailList.get(i);
	    			firstReviewUserEmail = getFirstReviewUserEmailMap.get("AliasEmail").toString();
	    		}
			}
			
			Object obj = SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementManualBoQueriesmailSentAfterPayment", newCtx);
			
			if(obj != null && obj instanceof List){
	    		List list = (List)obj;
	    		for(int i=0; i<list.size(); i++){
	    			Map map = (Map)list.get(i);
	    			subject = map.get("subject").toString();
	    			body = map.get("body").toString();
	    		}
			}
			
			body = body.replace("#Firm_Name#", ctx.get("AccountName").toString());
			body = body.replace("#Quote_Number#", ctx.get("QuoteNumber").toString());
			body = body.replace("#Effective_Date#", ctx.get("PolicyEffectiveDate").toString().split(" ")[0]);
			
			if("eft".equals(ctx.get("PaymentMode"))) {
				paymentMode = "Manual Check";
			} else if("paypal".equals(ctx.get("PaymentMode"))) {
				paymentMode = "Debit/Credit Card";
			} else if("ach".equals(ctx.get("PaymentMode"))) {
				paymentMode = "ACH";
			} else if("finance".equals(ctx.get("PaymentMode"))) {
				paymentMode = "Financing";
				
				if("EFT".equals(ctx.get("FinanceDownPayment"))) {
					paymentMode += " (" + "Credit Card/ACH, " + ctx.get("FinanceInterval") + ")";
				} else {
					paymentMode += " (" + "Manual Check, " + ctx.get("FinanceInterval") + ")";
				}
			}
			
			body = body.replace("#Payment_Mode#", paymentMode);
			
			subject = ctx.get("QuoteNumber").toString() + ", " + ctx.get("AccountName").toString() + ", " + paymentMode;

			logger.debug("Mail is going---- ");
			MailSender mail = new MailSender();
			mail.setToAdd(firstReviewUserEmail);
			mail.setBccAdd(emailID);
			mail.setSubject(subject);
			mail.setContentType("text/html");
			mail.setBody(body);
			mail.sendMail((Context) ctx);
			logger.debug("Mail has sent---- ");
			
		} catch(Exception e) {
			logger.error("Error occured while Update mailSentAfterPayment Flag");
		}

	}
	 
	public static boolean isCFAllowedToIssuePolicy(Context ctx) throws Exception 
	{
		try {
			new SetParametersForStoredProcedures().setParametersInContext(ctx, "StateCode,MarketableProductKey,PolicyEffectiveDate");
			Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("Policy.GetCompanyList_p", ctx);
			if (obj != null && obj instanceof Map) {
				Map objMap = (Map) obj;
				if("3".equals(objMap.get("CompanyKey").toString())) {
					return true;
				}
			}
		} catch(Exception e) {
			logger.debug("error occured while validating status :"+e);
		}
		return false;
	}
	
	public static boolean validateModifiersStateWise(Context ctx, int totalModifierPercentage, String msg) throws Exception {

		int ngtValidPercent=-25;
		int pvtValidPercent=25;
		
		if("14".equals(ctx.get("UserNo").toString()) || "1100".equals(ctx.get("UserNo").toString()) || "1106".equals(ctx.get("UserNo").toString()) || "1203".equals(ctx.get("UserNo").toString())) {
				
			if("AZ".equals(ctx.get("StateCode").toString()) || "CO".equals(ctx.get("StateCode").toString()) 
					|| "MA".equals(ctx.get("StateCode").toString()) || "MI".equals(ctx.get("StateCode").toString()) 
					|| "NV".equals(ctx.get("StateCode").toString()) || "UT".equals(ctx.get("StateCode").toString())) {
				ngtValidPercent = -35; pvtValidPercent = 35;
			} else if("GA".equals(ctx.get("StateCode").toString())) {
				ngtValidPercent = -50; pvtValidPercent = 40;
			} else if("IL".equals(ctx.get("StateCode").toString()) || "IN".equals(ctx.get("StateCode").toString()) 
					|| "NC".equals(ctx.get("StateCode").toString()) || "TN".equals(ctx.get("StateCode").toString()) 
					|| "VA".equals(ctx.get("StateCode").toString()) || "WI".equals(ctx.get("StateCode").toString())) {
				ngtValidPercent = -50; pvtValidPercent = 50;
			} else if("KS".equals(ctx.get("StateCode").toString()) || "MN".equals(ctx.get("StateCode").toString()) 
					|| "PA".equals(ctx.get("StateCode").toString()) || "TX".equals(ctx.get("StateCode").toString())) {
				ngtValidPercent = -40; pvtValidPercent = 40;
			} else if("SC".equals(ctx.get("StateCode").toString())) {
				ngtValidPercent = -40; pvtValidPercent = 25;
			} else if("WA".equals(ctx.get("StateCode").toString())) {
				ngtValidPercent = -20; pvtValidPercent = 25;
			}
		}
		
		if (totalModifierPercentage < ngtValidPercent || totalModifierPercentage > pvtValidPercent) {
			LawyersUtils.populateError(ctx, "SchduleRatingModifier1", msg + " " + ngtValidPercent + " to +" + pvtValidPercent);
		}
		return true;
	}
	
	public static boolean validateIPFSPolicyEffDate29Days(IContext ctx)
			throws Exception {

		return validatePaymentPolicyEffDate29Days(ctx, "LawyersRule.isIPFSShowToInsured");
	}
	public static boolean validatePaypalPolicyEffDate29Days(IContext ctx)
			throws Exception {

		return validatePaymentPolicyEffDate29Days(ctx, "LawyersRule.showPaypalDiv");
	}
	public static boolean validateACHPolicyEffDate29Days(IContext ctx)
			throws Exception {

		return validatePaymentPolicyEffDate29Days(ctx, "LawyersRule.showACHDiv");
	}
	
	public static boolean validatePaymentPolicyEffDate29Days(IContext ctx, String existingRule)
			throws Exception {

		Context newCtx=new Context();
		newCtx.setProject(ctx.getProject());
		newCtx.putAll(ctx);

		Map map = (Map) SqlResources.getSqlMapProcessor(newCtx).findByKey("FirmLW.findByKey", newCtx);
		if (map != null && map.size() > 0) {
			newCtx.put("QuoteEffectiveDate", map.get("QuoteEffectiveDate"));
			newCtx.put("QuoteExpiryDate", map.get("QuoteExpiryDate"));
			newCtx.put("QuoteSentDate", map.get("QuoteSentDate"));
		}
		
		Object objQuoteExp = RuleUtils.executeRule(newCtx, "LawyersRule.isPEDLessFrmQEDAndInsured");
		boolean flag = false;
		if (objQuoteExp != null && objQuoteExp instanceof Boolean)
			flag = (Boolean) objQuoteExp;

		boolean flagQuoteDate = false;
		if(flag) {
			//Less QuoteEffectiveDate
			Object objQuoteExpDate = RuleUtils.executeRule(newCtx, existingRule);
			if (objQuoteExpDate != null && objQuoteExpDate instanceof Boolean)
				flagQuoteDate = (Boolean) objQuoteExpDate;
		} else {
			//Greater QuoteEffectiveDate
			Object objPolciyEffDate = RuleUtils.executeRule(newCtx, "LawyersRule.isPolicyEffDate5DaysAndInsured");
			if (objPolciyEffDate != null && objPolciyEffDate instanceof Boolean)
				flagQuoteDate = (Boolean) objPolciyEffDate;
			
			if(flagQuoteDate)
				flagQuoteDate = QuoteLetter.isQuoteEffDateGreater5DaysLess29DaysFrmPED(newCtx, newCtx.get("PolicyEffectiveDate").toString(), newCtx.get("QuoteSentDate").toString());
		}

		if (!flagQuoteDate){
			return true;
		} else {
			return false;
		}
	}
	
}
