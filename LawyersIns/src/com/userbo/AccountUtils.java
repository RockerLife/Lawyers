package com.userbo;

import com.util.InetLogger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.mail.Mail;
import com.manage.managemetadata.functions.commonfunctions.DBUtils;
import com.manage.managemetadata.functions.commonfunctions.DateUtils;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.manage.managemetadata.util.exception.ValidationException;
import com.ormapping.ibatis.SqlResources;
import com.util.Constants;
import com.util.Context;
import com.util.IContext;
import com.util.SystemProperties;

public class AccountUtils {
	private static final InetLogger logger = InetLogger.getInetLogger(AccountUtils.class);

	public static String DATE_PATTERN = "MM-dd-yyyy hh:mm:ss";

	@SuppressWarnings("unchecked")
	protected static void populateError(IContext ctx, String field, String msg)
			throws Exception {
		List errorList = new ArrayList();
		if (ctx.get(Constants.INET_ERRORS_LIST) != null)
			errorList = (List) ctx.get(Constants.INET_ERRORS_LIST);

		ValidationException e = new ValidationException(msg);
		e.setField(field);
		errorList.add(e);
		ctx.put(Constants.INET_ERRORS_LIST, errorList);
		ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
	}

	@SuppressWarnings("unchecked")
	public void generateQuoteNumber(IContext ctx) throws Exception {

		logger.debug("hello");
		/*
		 * String policykey =
		 * ctx.get(com.userbo.Constants.POLICY_KEY).toString() .trim(); if
		 * (ctx.get(com.userbo.Constants.QUOTE_NUMBER) == null)
		 * ctx.put(com.userbo.Constants.QUOTE_NUMBER,
		 * getNewQuoteNumber(policykey));
		 */

		if (ctx.get(com.userbo.Constants.POLICY_KEY) != null) {
			String PolicyKey = ctx.get(com.userbo.Constants.POLICY_KEY).toString().trim();
			ctx.put(com.userbo.Constants.QUOTE_NUMBER,getNewQuoteNumber(PolicyKey));
		}

		if (ctx.get(com.userbo.Constants.ACCOUNT_KEY) != null) {
			String AccountKey = ctx.get(com.userbo.Constants.ACCOUNT_KEY)
					.toString().trim();
			ctx.put(com.userbo.Constants.ACCOUNT_ID, AccountKey);
		}

	}

	public String getNewQuoteNumber(Object submissionID) {

		if (submissionID == null)
			return null;

		String policyNum = "";

		for (int i = 0; i < 7 - submissionID.toString().length(); i++)
			policyNum = policyNum + "0";

		policyNum = "QN-" + policyNum + submissionID.toString();

		return policyNum;
	}

	public void checkTotalPercentage(IContext ctx) throws Exception {
		Set set = ctx.keySet();
		int total = 0;

		for (Iterator it = set.iterator(); it.hasNext();) {

			String key = it.next().toString();
			if (key.contains("NumberOfPersonnel_")) {
				if (ctx.get(key) != null && !"".equals(ctx.get(key)))
					total = total + Integer.parseInt(ctx.get(key).toString());
			}
		}

		// if(total <= 0)
		// {
		// populateError(ctx, "TotalFirmPersonnel", "Total Firm Personnel should
		// be equal or greater than 1");
		// }

		if (total >= 1) {
			Object obj = DBUtils.executeDBOperation(ctx, "PersonnelType", "15");
			if (obj != null) {
				savePersonnelAffiliationMOMList(ctx, total, obj);
			}
		}

	}

	@SuppressWarnings("unchecked")
	private void savePersonnelAffiliationMOMList(IContext ctx, int total,
			Object obj) throws Exception {
		List personnelList = (List) obj;
		for (int i = 0; i < personnelList.size(); i++) {
			Map map = (Map) personnelList.get(i);

			Context newctx = new Context();
			newctx.setProject("AccountantIns");
			newctx.put("PersonnelTypeKey", ctx
					.get("PersonnelTypeKey" + "_" + i));
			newctx.put("PolicyKey", ctx.get("PolicyKey"));
			newctx.put("VersionSequence", ctx.get("VersionSequence"));
			newctx.put("TransactionSeq", ctx.get("TransactionSeq"));
			newctx.put("NumberOfPersonnel", ctx.get("NumberOfPersonnel" + "_"
					+ i));
			Object obj2 = DBUtils.executeDBOperation(newctx,
					"PersonnelAffiliation", "3");

			if (obj2 == null) {
				DBUtils.executeDBOperation(newctx, "PersonnelAffiliation", "1");

			} else {

				DBUtils.executeDBOperation(newctx, "PersonnelAffiliation", "2");
				DBUtils.executeDBOperation(newctx, "PersonnelAffiliation", "1");
			}

		}

		ctx.put("TotalFirmPersonnel", total);
		DBUtils.executeDBOperation(ctx, "Firm", "4");
	}

	// @SuppressWarnings("unchecked")
	// protected void noOfPersonnelError(IContext ctx, int total, String
	// percentage) throws Exception
	// {
	// List errorList = new ArrayList();
	// if (ctx.get(Constants.INET_ERRORS_LIST) != null)
	// errorList = (List) ctx.get(Constants.INET_ERRORS_LIST);
	//        
	// ValidationException e = new ValidationException("Total Firm Personnel
	// should be equal or greater than 1");
	// e.setField("TotalFirmPersonnel");
	// errorList.add(e);
	// ctx.put(Constants.INET_ERRORS_LIST, errorList);
	// ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
	// }

	@SuppressWarnings("unchecked")
	protected static void percentageError(IContext ctx, int total,
			String percentage) throws Exception {
		populateError(ctx, "TotalAOP_Percentage",
				"The total AOP percentage is required to equal 100%, current total is "
						+ total + "%");

		// List errorList = new ArrayList();
		// if (ctx.get(Constants.INET_ERRORS_LIST) != null)
		// errorList = (List) ctx.get(Constants.INET_ERRORS_LIST);
		//        
		// // errorList.add(new ValidationException("The total percentage should
		// be equal to"+percentage+"%."));
		// // errorList.add(new ValidationException("Current total percentage is
		// : "+total + "%"));
		//             
		// ValidationException e = new ValidationException("Total percentage
		// should be equal to "+percentage+ "% and Current total percentage is
		// "+total + "%");
		// e.setField("TotalAOP_Percentage");
		// errorList.add(e);
		// ctx.put(Constants.INET_ERRORS_LIST, errorList);
		// ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
	}

	public static void validateAOPComment(IContext ctx) throws Exception {

		Map map = new HashMap();
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select(
				"AOP.findAll", ctx);

		if (limtTypes != null) {
			for (int i = 0; i < limtTypes.size(); i++) {
				map = (HashMap) limtTypes.get(i);
				// if(map.get("AOPKey").toString().equals("9") ||
				// map.get("AOPKey").toString().equals("10") ||
				// map.get("AOPKey").toString().equals("12") ||
				// map.get("AOPKey").toString().equals("19") ||
				// map.get("AOPKey").toString().equals("20"))
				if (map.get("AOPKey").toString().equals("9")
						|| map.get("AOPKey").toString().equals("10")
						|| map.get("AOPKey").toString().equals("12")
						|| map.get("AOPKey").toString().equals("20")
						|| map.get("AOPKey").toString().equals("21")) {
					String percentageValue = ctx.get("AOP_Percentage_"
							+ map.get("AOPKey").toString()) == null ? null
							: ctx.get(
									"AOP_Percentage_"
											+ map.get("AOPKey").toString())
									.toString();
					String comment = ctx.get("AOPCommentDesc_"
							+ map.get("AOPKey").toString()) == null ? null
							: ctx.get(
									"AOPCommentDesc_"
											+ map.get("AOPKey").toString())
									.toString();

					if (!(percentageValue == null
							|| "".equals(percentageValue.trim()) || "0"
							.equals(percentageValue))) {
						if (comment == null || "".equals(comment.trim())) {
							String msg = "Fill in please describe";
							if (map.get("AOPKey").toString().equals("10"))
								msg = "Please describe the Services";
							else if (map.get("AOPKey").toString().equals("12"))
								msg = "Please describe the Consulting services";
							else if (map.get("AOPKey").toString().equals("20"))
								msg = "Please describe the Other Services";

							populateError(ctx, "AOPCommentDesc_"
									+ map.get("AOPKey").toString(), msg);
						}
					} else {
						if (comment != null && !"".equals(comment)) {
							ctx.put("AOPCommentDesc_"
									+ map.get("AOPKey").toString(), "");
						}

					}
				}
			}
		}
	}

	public static void validateAOPPercentage(IContext ctx) throws Exception {

		int total = 0;
		Map map = new HashMap();
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select(
				"AOP.findAll", ctx);

		if (limtTypes != null) {
			for (int i = 0; i < limtTypes.size(); i++) {
				map = (HashMap) limtTypes.get(i);
				String percentageValue = ctx.get("AOP_Percentage_"
						+ map.get("AOPKey").toString()) == null ? null : ctx
						.get("AOP_Percentage_" + map.get("AOPKey").toString())
						.toString();
				if (percentageValue == null
						|| "".equals(percentageValue.trim()))
					percentageValue = "0";

				total = total + Integer.parseInt(percentageValue);
			}
		}

		if (total < 100 || total > 100) {
			percentageError(ctx, total, "100");
		}
	}

	public static void validateClientList(IContext ctx) throws Exception {

		if (ctx.get("IsCommissionRecieved") != null
				&& "Y".equals(ctx.get("IsCommissionRecieved").toString())) {
			List list = SqlResources
					.getSqlMapProcessor(ctx)
					.select(
							"SqlStmts.UserStatementcheckListSizeListPublicPrivateOfferingSupp",
							ctx);

			if (list == null)
				populateError(ctx, "IsCommissionRecieved",
						"Client list is empty, atleast one record is required");
			else if (list != null && list.size() == 0)
				populateError(ctx, "IsCommissionRecieved",
						"Client list is empty, atleast one record is required");
		} else if (ctx.get("IsCommissionRecieved") != null
				&& "N".equals(ctx.get("IsCommissionRecieved").toString())) {
			SqlResources
					.getSqlMapProcessor(ctx)
					.delete(
							"SqlStmts.UserStatementdeleteListPublicPrivateOfferingSupp",
							ctx);
		}
	}

	public static void validateClaimList(IContext ctx) throws Exception {

		if ((ctx.get("IsAwarenessOfAnyProfLiability") != null && "Y".equals(ctx
				.get("IsAwarenessOfAnyProfLiability").toString()))
				|| (ctx.get("IsAwarenessOfAnyProfLiabilitySuitAgainst") != null && "Y"
						.equals(ctx.get(
								"IsAwarenessOfAnyProfLiabilitySuitAgainst")
								.toString()))) {
			List list = SqlResources.getSqlMapProcessor(ctx).select(
					"SqlStmts.UserStatementcheckListSizeListClaimIncidentSupp",
					ctx);

			if (ctx.get("IsAwarenessOfAnyProfLiability") != null
					&& "Y".equals(ctx.get("IsAwarenessOfAnyProfLiability")
							.toString())) {
				if (list == null)
					populateError(ctx, "IsAwarenessOfAnyProfLiability",
							"Claim list is empty, atleast one record is required");
				else if (list != null && list.size() == 0)
					populateError(ctx, "IsAwarenessOfAnyProfLiability",
							"Claim list is empty, atleast one record is required");
			}
			if (ctx.get("IsAwarenessOfAnyProfLiabilitySuitAgainst") != null
					&& "Y".equals(ctx.get(
							"IsAwarenessOfAnyProfLiabilitySuitAgainst")
							.toString())) {
				if (list == null)
					populateError(ctx,
							"IsAwarenessOfAnyProfLiabilitySuitAgainst",
							"Claim list is empty, atleast one record is required");
				else if (list != null && list.size() == 0)
					populateError(ctx,
							"IsAwarenessOfAnyProfLiabilitySuitAgainst",
							"Claim list is empty, atleast one record is required");
			}
		} else if ((ctx.get("IsAwarenessOfAnyProfLiability") != null && "N"
				.equals(ctx.get("IsAwarenessOfAnyProfLiability").toString()))
				&& (ctx.get("IsAwarenessOfAnyProfLiabilitySuitAgainst") != null && "N"
						.equals(ctx.get(
								"IsAwarenessOfAnyProfLiabilitySuitAgainst")
								.toString()))) {
			SqlResources.getSqlMapProcessor(ctx).delete(
					"SqlStmts.UserStatementdeleteListClaimIncidentSupp", ctx);
		}
	}

	public static void validatePolicyCoverage(IContext ctx) throws Exception {

		if (ctx.get("IsClaimExpensesType") == null)
			ctx.put("IsClaimExpensesType", "No");
		else if (ctx.get("IsClaimExpensesType") != null
				&& "".equals(ctx.get("IsClaimExpensesType").toString()))
			ctx.put("IsClaimExpensesType", "No");

		if (ctx.get("IsClaimOptionType") == null)
			ctx.put("IsClaimOptionType", "No");
		else if (ctx.get("IsClaimOptionType") != null
				&& "".equals(ctx.get("IsClaimOptionType").toString()))
			ctx.put("IsClaimOptionType", "No");
	}

	public static void validateQuoteByEmailAgentCall(IContext ctx)
			throws Exception {

		if ("general".equals(ctx.get("page").toString())
				|| "BasicInfo".equals(ctx.get("page").toString())) {
			if (ctx.get("IsQuickQuoteByEmail") == null)
				ctx.put("IsQuickQuoteByEmail", "N");
			else if (ctx.get("IsQuickQuoteByEmail") != null
					&& "".equals(ctx.get("IsQuickQuoteByEmail").toString()))
				ctx.put("IsQuickQuoteByEmail", "N");

			if (ctx.get("IsAnAgentCall") == null)
				ctx.put("IsAnAgentCall", "N");
			else if (ctx.get("IsAnAgentCall") != null
					&& "".equals(ctx.get("IsAnAgentCall").toString()))
				ctx.put("IsAnAgentCall", "N");

			if ("N".equals(ctx.get("IsQuickQuoteByEmail").toString())
					&& "N".equals(ctx.get("IsAnAgentCall").toString()))
				populateError(ctx, "IsAnAgentCall",
						"Either an email or call from specialist is reqiured");
		}
	}

	public static void validatePersonnelNumber(IContext ctx) throws Exception {

		Set set = ctx.keySet();
		int total = 0;
		int NumberOfPersonnel_0 = 0;
		int NumberOfPersonnel_1 = 0;

		for (Iterator it = set.iterator(); it.hasNext();) {

			String key = it.next().toString();
			if (key.contains("NumberOfPersonnel_")) {
				if (key.equals("NumberOfPersonnel_1")) {
					if (ctx.get(key) != null
							&& !"".equals(ctx.get(key).toString().trim()))
						NumberOfPersonnel_1 = Integer.parseInt(ctx.get(key)
								.toString().trim());
					if (ctx.get("NumberOfPersonnel_0") != null
							&& !"".equals(ctx.get("NumberOfPersonnel_0")
									.toString().trim()))
						NumberOfPersonnel_0 = Integer.parseInt(ctx.get(
								"NumberOfPersonnel_0").toString().trim());
				}

				if (ctx.get(key) != null
						&& !"".equals(ctx.get(key).toString().trim()))
					total = total
							+ Integer.parseInt(ctx.get(key).toString().trim());
			}
		}

		if (total <= 0) {
			populateError(ctx, "TotalFirmPersonnel",
					"Total Firm Personnel should be equal or greater than 1");
		} else if (NumberOfPersonnel_1 > NumberOfPersonnel_0) {
			populateError(
					ctx,
					"TotalFirmPersonnel",
					"\"Number above that are CPA's\" should be equal or less than \"Owners Partners or Officers\"");
		}
	}

	public static void validateBusinessState(IContext ctx) throws Exception {

		if (ctx.get("StateCode") != null
				&& !"".equals(ctx.get("StateCode").toString())) {
			Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey(
					"State.findByKey", ctx);
			if (obj != null && obj instanceof Map) {
				Map map = (Map) obj;
				ctx.put("IsBusinessActive", map.get("IsBusinessActive"));

				// if(map.get("IsBusinessActive") == null)
				// {
				// populateError(ctx, "StateCode", "We currently don't write
				// business for this state, please contact at 1.888.803.9898");
				// }
				// else if(map.get("IsBusinessActive") != null &&
				// "N".equals(map.get("IsBusinessActive").toString().trim()))
				// {
				// populateError(ctx, "StateCode", "We currently don't write
				// business for this state, please contact at 1.888.803.9898");
				// }
			}
		}

	}

	public void validateBusinessStateOnContinue(IContext ctx) throws Exception {

		if (ctx.get("StateCode") != null
				&& !"".equals(ctx.get("StateCode").toString())) {
			Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey(
					"State.findByKey", ctx);
			if (obj != null && obj instanceof Map) {
				Map map = (Map) obj;
				ctx.put("IsBusinessActive", map.get("IsBusinessActive"));

				// if(map.get("IsBusinessActive") == null)
				// {
				// populateError(ctx, "StateCode", "We currently don't write
				// business for this state, please contact at 1.888.803.9898");
				// }
				// else if(map.get("IsBusinessActive") != null &&
				// "N".equals(map.get("IsBusinessActive").toString().trim()))
				// {
				// populateError(ctx, "StateCode", "We currently don't write
				// business for this state, please contact at 1.888.803.9898");
				// }
			}
		}

	}

	public static boolean validateBasicinfo(Context ctx) throws Exception {
		validateBusinessState(ctx);
		validatePersonnelNumber(ctx);
		validateEstimateForCurrentYear(ctx);
		validateAOPPercentage(ctx);
		validateAOPComment(ctx);
		validateClientList(ctx);
		validateClaimList(ctx);
		validatePolicyCoverage(ctx);
		validateQuoteByEmailAgentCall(ctx);
		validateBasicinfoDateField(ctx);
		return true;
	}

	public static void validateTrusteeList(IContext ctx) throws Exception {
		if (ctx.get("IsServedAsTrustee") != null
				&& "Y".equals(ctx.get("IsServedAsTrustee").toString())) {
			List list = SqlResources
					.getSqlMapProcessor(ctx)
					.select(
							"SqlStmts.UserStatementcheckListSizeListTrusteeOrEstateSupp",
							ctx);

			if (list == null)
				populateError(ctx, "IsServedAsTrustee",
						"Served trustee list is empty, atleast one record is required");
			else if (list != null && list.size() == 0)
				populateError(ctx, "IsServedAsTrustee",
						"Served trustee list is empty, atleast one record is required");
		} else if (ctx.get("IsServedAsTrustee") != null
				&& "N".equals(ctx.get("IsServedAsTrustee").toString())) {
			SqlResources.getSqlMapProcessor(ctx).delete(
					"SqlStmts.UserStatementdeleteListTrusteeOrEstateSupp", ctx);
		}
	}

	public static void validateFirmRenderedList(IContext ctx) throws Exception {
		if (ctx.get("IsFirmRenderedServiceForAnyClient") != null
				&& "Y".equals(ctx.get("IsFirmRenderedServiceForAnyClient")
						.toString())) {
			List list = SqlResources
					.getSqlMapProcessor(ctx)
					.select(
							"SqlStmts.UserStatementcheckListSizeListOutsideInterestSupp",
							ctx);

			if (list == null)
				populateError(ctx, "IsFirmRenderedServiceForAnyClient",
						"Firm list is empty, atleast one record is required");
			else if (list != null && list.size() == 0)
				populateError(ctx, "IsFirmRenderedServiceForAnyClient",
						"Firm list is empty, atleast one record is required");
		} else if (ctx.get("IsFirmRenderedServiceForAnyClient") != null
				&& "N".equals(ctx.get("IsFirmRenderedServiceForAnyClient")
						.toString())) {
			SqlResources.getSqlMapProcessor(ctx).delete(
					"SqlStmts.UserStatementdeleteListOutsideInterestSupp", ctx);
		}
	}

	public static boolean validateGeneral(Context ctx) throws Exception {
		validateTrusteeList(ctx);
		validateFirmRenderedList(ctx);
		validateGeneralDateField(ctx);
		validateQuoteByEmailAgentCall(ctx);
		return true;
	}

	public void checkAOPPercentage(IContext ctx) throws Exception {
		Set set = ctx.keySet();
		int total = 0;
		for (Iterator it = set.iterator(); it.hasNext();) {
			String key = it.next().toString();
			if (key.contains("PercentageValue_")) {
				if (ctx.get(key) != null
						&& !"".equals(ctx.get(key).toString().trim()))
					total = total
							+ Integer.parseInt(ctx.get(key).toString().trim());
			}
		}

		Object obj = DBUtils.executeDBOperation(ctx, "AOP", "15");
		if (obj != null) {
			saveAOPMOMList(ctx, total, obj);
		}
	}

	@SuppressWarnings("unchecked")
	private void saveAOPMOMList(IContext ctx, int total, Object obj)
			throws Exception {
		String value = "";
		List personnelList = (List) obj;
		for (int i = 0; i < personnelList.size(); i++) {
			Map map = (Map) personnelList.get(i);

			Context newctx = new Context();
			newctx.setProject("AccountantIns");
			newctx.put("AOPKey", ctx.get("AOPKey" + "_" + i));
			newctx.put("PolicyKey", ctx.get("PolicyKey"));
			newctx.put("VersionSequence", ctx.get("VersionSequence"));
			newctx.put("TransactionSeq", ctx.get("TransactionSeq"));

			if (ctx.get("PercentageValue" + "_" + i) != null)
				value = ctx.get("PercentageValue" + "_" + i).toString().trim();

			if ("".equals(value))
				value = "0";

			newctx.put("PercentageValue", value);
			Object obj2 = DBUtils.executeDBOperation(newctx, "AreaOfPractice",
					"3");

			if (obj2 == null) {
				DBUtils.executeDBOperation(newctx, "AreaOfPractice", "1");

			} else {

				DBUtils.executeDBOperation(newctx, "AreaOfPractice", "2");
				DBUtils.executeDBOperation(newctx, "AreaOfPractice", "1");
			}

		}

		ctx.put("TotalAOP", total);
		if (null != ctx.get("FirmKey"))
			DBUtils.executeDBOperation(ctx, "Firm", "4");
	}

	public void checkInsertGrossRevenue(IContext ctx) throws Exception {
		long total = 0;
		int count = 0;

		boolean bool = false; // validateEstimateForCurrentYear(ctx);
		if (!bool) {
			Object obj = DBUtils.executeDBOperation(ctx, "GrossRevenue", "15");
			if (obj != null) {
				List personnelList = (List) obj;
				for (int i = 0; i < personnelList.size(); i++) {
					Map map = (Map) personnelList.get(i);
					Context newctx = new Context();
					newctx.setProject("AccountantIns");
					newctx.put("GrossRevenueKey", ctx.get("GrossRevenueKey"
							+ "_" + i));
					newctx.put("PolicyKey", ctx.get("PolicyKey"));
					newctx.put("VersionSequence", ctx.get("VersionSequence"));
					newctx.put("TransactionSeq", ctx.get("TransactionSeq"));
					newctx.put("YearEndDate", ctx.get("YearEndDate" + "_" + i));
					// newctx.put("Amount" , ctx.get( "Amount"+"_"+i) );

					Object objAmount = removeAmountFormat(ctx.get("Amount"
							+ "_" + i));
					if (objAmount != null && !"".equals(objAmount.toString())
							&& !"0".equals(objAmount.toString())) {
						total = total + Long.parseLong(objAmount.toString());
						count = count + 1;
					}

					newctx.put("Amount", objAmount);

					Object obj2 = DBUtils.executeDBOperation(newctx,
							"GrossRevenueForFirm", "3");
					if (obj2 == null) {
						DBUtils.executeDBOperation(newctx,
								"GrossRevenueForFirm", "1");
					} else {
						DBUtils.executeDBOperation(newctx,
								"GrossRevenueForFirm", "2");
						DBUtils.executeDBOperation(newctx,
								"GrossRevenueForFirm", "1");
					}

				}

				if (count > 0) {
					long AverageRevenue = total / count;
					ctx.put("AverageRevenue", AverageRevenue);
				} else
					ctx.put("AverageRevenue", 0);

			}
		}
	}

	public static boolean validateAttestation(Context ctx) throws Exception {

		int total = 0;
		Object obj = DBUtils.executeDBOperation(ctx, "ClientIndustry", "15");
		if (obj != null) {
			List personnelList = (List) obj;
			for (int i = 0; i < personnelList.size(); i++) {
				if (ctx.get("AuditClients" + "_" + i) != null
						&& !"".equals(ctx.get("AuditClients" + "_" + i)
								.toString().trim()))
					total += Integer.parseInt(ctx.get("AuditClients" + "_" + i)
							.toString().trim());
			}
		}

		if (total == 0)
			populateError(ctx, "TotalAuditClients",
					"Audit clients needs to be greater than zero");
		return true;
	}

	public void insertAttestationAuditWork(IContext ctx) throws Exception {
		boolean bool = true; // isEstimateForCurrentYear(ctx);
		if (bool) {
			Object obj = DBUtils
					.executeDBOperation(ctx, "ClientIndustry", "15");
			if (obj != null) {
				List personnelList = (List) obj;
				for (int i = 0; i < personnelList.size(); i++) {
					Map map = (Map) personnelList.get(i);
					Context newctx = new Context();
					newctx.setProject("AccountantIns");
					newctx.put("ClientIndustryKey", ctx.get("ClientIndustryKey"
							+ "_" + i));
					newctx.put("PolicyKey", ctx.get("PolicyKey"));
					newctx.put("VersionSequence", ctx.get("VersionSequence"));
					newctx.put("TransactionSeq", ctx.get("TransactionSeq"));
					newctx.put("AuditClients", ctx
							.get("AuditClients" + "_" + i));
					newctx.put("ClientsAssetsOver", removeAmountFormat(ctx
							.get("ClientsAssetsOver" + "_" + i)));
					newctx.put("NetLossForLastFYE", removeAmountFormat(ctx
							.get("NetLossForLastFYE" + "_" + i)));

					Object obj2 = DBUtils.executeDBOperation(newctx,
							"AttestationAuditWork", "3");
					if (obj2 == null) {
						DBUtils.executeDBOperation(newctx,
								"AttestationAuditWork", "1");
					} else {
						DBUtils.executeDBOperation(newctx,
								"AttestationAuditWork", "2");
						DBUtils.executeDBOperation(newctx,
								"AttestationAuditWork", "1");
					}

				}
			}
		}
	}

	public void validateITServices(IContext ctx) throws Exception {

		long total = 0;
		long otherLastYearRevenue = 0;
		long otherEstimatedRevenue = 0;

		Object obj = DBUtils.executeDBOperation(ctx, "ITServiceCategory", "15");
		if (obj != null) {
			List personnelList = (List) obj;
			for (int i = 0; i < personnelList.size(); i++) {
				if (ctx.get("RevenuesFromLastYear" + "_" + i) != null
						&& !"".equals(ctx.get("RevenuesFromLastYear" + "_" + i)
								.toString().trim())) {
					total += Long.parseLong(removeAmountFormat(
							ctx.get("RevenuesFromLastYear" + "_" + i))
							.toString());
					if (i == 4)
						otherLastYearRevenue += Long
								.parseLong(removeAmountFormat(
										ctx.get("RevenuesFromLastYear" + "_"
												+ i)).toString());
				}
				if (ctx.get("EstimatedRevenue" + "_" + i) != null
						&& !"".equals(ctx.get("EstimatedRevenue" + "_" + i)
								.toString().trim())) {
					total += Long.parseLong(removeAmountFormat(
							ctx.get("EstimatedRevenue" + "_" + i)).toString());
					if (i == 4)
						otherEstimatedRevenue += Long
								.parseLong(removeAmountFormat(
										ctx.get("EstimatedRevenue" + "_" + i))
										.toString());
				}
			}
		}

		if (total == 0)
			populateError(
					ctx,
					"TotalITRevenue",
					"Current or Prior fiscal year for one of the items needs to be greater than zero");

		if (otherLastYearRevenue > 0 || otherEstimatedRevenue > 0) {
			if (ctx.get("OtherITServiceComment") == null)
				populateError(ctx, "OtherITServiceComment",
						"Please describe the IT services provided");
			else if (ctx.get("OtherITServiceComment") != null
					&& "".equals(ctx.get("OtherITServiceComment").toString()))
				populateError(ctx, "OtherITServiceComment",
						"Please describe the IT services provided");
		} else {
			ctx.put("OtherITServiceComment", "");
		}

	}

	public void insertITServiceSupp(IContext ctx) throws Exception {
		Object obj = DBUtils.executeDBOperation(ctx, "ITServiceCategory", "15");
		if (obj != null) {
			List personnelList = (List) obj;
			for (int i = 0; i < personnelList.size(); i++) {
				Map map = (Map) personnelList.get(i);
				Context newctx = new Context();
				newctx.putAll(map);
				newctx.setProject(ctx.getProject());
				newctx.put("ITServiceCategoryKey", ctx
						.get("ITServiceCategoryKey" + "_" + i));
				newctx.put("PolicyKey", ctx.get("PolicyKey"));
				newctx.put("VersionSequence", ctx.get("VersionSequence"));
				newctx.put("TransactionSeq", ctx.get("TransactionSeq"));
				newctx.put("EstimatedRevenue", removeAmountFormat(ctx
						.get("EstimatedRevenue" + "_" + i)));
				newctx.put("RevenuesFromLastYear", removeAmountFormat(ctx
						.get("RevenuesFromLastYear" + "_" + i)));

				if (ctx.get("IsAnnually" + "_" + i) != null
						&& ("on".equals(ctx.get("IsAnnually" + "_" + i)
								.toString()) || "Y".equals(ctx.get(
								"IsAnnually" + "_" + i).toString())))
					newctx.put("IsAnnually", "Y");
				else
					newctx.put("IsAnnually", ctx.get("IsAnnually" + "_" + i));

				Object obj2 = DBUtils.executeDBOperation(newctx,
						"ITServicesSupp", "3");
				if (obj2 == null) {
					DBUtils.executeDBOperation(newctx, "ITServicesSupp", "1");
				} else {
					DBUtils.executeDBOperation(newctx, "ITServicesSupp", "2");
					DBUtils.executeDBOperation(newctx, "ITServicesSupp", "1");
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static boolean validateEstimateForCurrentYear(IContext ctx)
			throws Exception {

		boolean flag = false;
		if (ctx.get("YearEndDate_0") == null && ctx.get("Amount_0") == null) {
			populateError(ctx, "estimate_year",
					"Fiscal Year End date and amount are required");
			flag = true;
		} else {

			if ((ctx.get("Amount_0") != null && "".equals(ctx.get("Amount_0")
					.toString().trim()))
					&& (ctx.get("YearEndDate_0") != null && "".equals(ctx.get(
							"YearEndDate_0").toString().trim()))) {
				populateError(ctx, "estimate_year",
						"Fiscal Year End date and amount are required");
				flag = true;
			} else if (ctx.get("YearEndDate_0") == null) {
				populateError(ctx, "estimate_year",
						"Fiscal Year End date is required");
				flag = true;
			} else if (ctx.get("YearEndDate_0") != null
					&& "".equals(ctx.get("YearEndDate_0").toString().trim())) {
				populateError(ctx, "estimate_year",
						"Fiscal Year End date is required");
				flag = true;
			} else if (ctx.get("Amount_0") == null) {
				populateError(ctx, "estimate_year", "Amount is required");
				flag = true;
			} else if (ctx.get("Amount_0") != null
					&& "".equals(ctx.get("Amount_0").toString().trim())) {
				populateError(ctx, "estimate_year", "Amount is required");
				flag = true;
			}
		}

		return flag;
	}

	public void sendMail(IContext ctx) throws Exception {
		String mailingOnOFF = null;
		try {
			mailingOnOFF = SystemProperties.getInstance().getString(
					"Insured.sendmail");
		} catch (Exception e) {
			logger.error("Unable to read insured mail setting", e);
		}

		if (mailingOnOFF != null && "Y".equals(mailingOnOFF)
				&& "insured".equals(ctx.get("User")))
			if (ctx.get("IsQuickQuoteByEmail") != null
					&& "Y".equals(ctx.get("IsQuickQuoteByEmail"))) {
				Mail mail = new Mail();
				mail.setToAdd(ctx.get("AccountEmail").toString());
				mail.setCcAdd("madhu@outlinesys.com");
				mail.setSubject("Sucessfully Created Your Quote");
				mail.setBody("Quote Number :" + ctx.get("QuoteNumber")
						+ " \n Account Name: " + ctx.get("AccountName") + "\n");
				mail.sendMail((Context) ctx);
			}
	}

	@SuppressWarnings("unchecked")
	public void currentPage(IContext ctx) throws Exception {

		if (ctx.get("page") != null) {
			if ("BasicInfo".equals(ctx.get("page"))) {
				if ("".equals(ctx.get("IsBasicPage"))
						|| ctx.get("IsBasicPage") == null)
					ctx.put("IsBasicPage", "N");
				else
					ctx.put("IsBasicPage", ctx.get("IsBasicPage"));
			}
			if ("general".equals(ctx.get("page"))) {
				if ("".equals(ctx.get("IsGeneralPage"))
						|| ctx.get("IsGeneralPage") == null)
					ctx.put("IsBasicPage", "N");
				else
					ctx.put("IsBasicPage", ctx.get("IsGeneralPage"));
			}
			if ("invest-finan-planing".equals(ctx.get("page"))) {
				if ("".equals(ctx.get("IsSupplementPage"))
						|| ctx.get("IsSupplementPage") == null)
					ctx.put("IsBasicPage", "N");
				else
					ctx.put("IsBasicPage", ctx.get("IsSupplementPage"));
			}
			if ("attestation".equals(ctx.get("page"))) {
				if ("".equals(ctx.get("IsAttesationPage"))
						|| ctx.get("IsAttesationPage") == null)
					ctx.put("IsBasicPage", "N");
				else
					ctx.put("IsBasicPage", ctx.get("IsAttesationPage"));
			}
			if ("computer-services".equals(ctx.get("page"))) {
				if ("".equals(ctx.get("IsComputerServicePage"))
						|| ctx.get("IsComputerServicePage") == null)
					ctx.put("IsBasicPage", "N");
				else
					ctx.put("IsBasicPage", ctx.get("IsComputerServicePage"));
			}
			if ("Business-funds-control".equals(ctx.get("page"))) {
				if ("".equals(ctx.get("IsBusinessFundPage"))
						|| ctx.get("IsBusinessFundPage") == null)
					ctx.put("IsBasicPage", "N");
				else
					ctx.put("IsBasicPage", ctx.get("IsBusinessFundPage"));
			}
			if ("Practice-Managemen".equals(ctx.get("page"))) {
				if ("".equals(ctx.get("IsPracriceMangPage"))
						|| ctx.get("IsPracriceMangPage") == null)
					ctx.put("IsBasicPage", "N");
				else
					ctx.put("IsBasicPage", ctx.get("IsPracriceMangPage"));
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void checkIssuedAndOtherStateAgent(IContext ctx) throws Exception {
		ctx.put("isPolicyIssued", "N");
		ctx.put("isOtherStateAgent", "N");

		if (ctx.get("StatusKey") != null) {
			if ("5".equals(ctx.get("StatusKey"))) {
				// ctx.put("User", "insured");
				ctx.put("isPolicyIssued", "Y");

			}
		}

		if (ctx.get("AgentState") != null && ctx.get("StateCode") != null) {
			if (!ctx.get("StateCode").toString().equals(ctx.get("AgentState"))) {
				// ctx.put("User", "insured");
				ctx.put("isOtherStateAgent", "Y");
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void checkAgentExist(IContext ctx) throws Exception {
		Object obj = ctx.get("userdetailsInfoList");
		if (obj != null) {
			List list = (List) obj;
			int count = 0;
			boolean emailMatched = false;
			boolean pwdMatched = false;

			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				if (ctx.get("AgentEmail") != null
						&& !"".equals(ctx.get("AgentEmail"))) {
					if (map.get("AgentEmail").toString().equalsIgnoreCase(
							ctx.get("AgentEmail").toString())
							|| map.get("PassWord").equals(ctx.get("PassWord"))) {

						if (map.get("AgentEmail").toString().equalsIgnoreCase(
								ctx.get("AgentEmail").toString()))
							emailMatched = true;
						if (map.get("PassWord").toString().equalsIgnoreCase(
								ctx.get("PassWord").toString()))
							pwdMatched = true;

						if (map.get("AgentEmail").toString().equalsIgnoreCase(
								ctx.get("AgentEmail").toString())
								&& map.get("PassWord").equals(
										ctx.get("PassWord"))) {
							ctx.put("AgentState", map.get("AgentState"));
							ctx.put("AgentEmail", map.get("AgentEmail"));
							ctx.put("LastUpdateUserID", map.get("UserNo"));
							ctx.put("UserNo", map.get("UserNo"));
							ctx.put("AgentName", map.get("AgentName"));
							ctx.put("LicenseNumber", map.get("LicenseNumber"));
							count++;
							return;
						}
					}
				}
			}

			if (count == 0) {
				List errorList = new ArrayList();
				if (ctx.get(Constants.INET_ERRORS_LIST) != null)
					errorList = (List) ctx.get(Constants.INET_ERRORS_LIST);

				if (!emailMatched && !pwdMatched)
					errorList.add(new ValidationException(
							"User ID/Password are wrong"));
				else if (!emailMatched)
					errorList.add(new ValidationException(
							"User ID does not exist"));
				else if (!pwdMatched)
					errorList.add(new ValidationException("Password is wrong"));

				ctx.put(Constants.INET_ERRORS_LIST, errorList);
				ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
			}

		}
	}

	@SuppressWarnings("unchecked")
	public void isFirmExist(IContext ctx) throws Exception {
		Object obj = ctx.get("Account_Info");
		if (obj != null && ((List) obj).size() != 0 && ((List) obj).size() == 1) {

			Map map = (Map) ((List) obj).get(0);
			if (ctx.get("InsuredLoginID") != null
					&& !"".equals(ctx.get("InsuredLoginID"))) {
				if (ctx.get("InsuredPassword") != null
						&& !"".equals(ctx.get("InsuredPassword"))) {
					if (map.get("WorkPhone").equals(ctx.get("InsuredPassword"))
							&& map.get("AccountEmail").equals(
									ctx.get("InsuredLoginID"))) {
						ctx.putAll(map);
						return;
					} else {
						List errorList = new ArrayList();
						if (ctx.get(Constants.INET_ERRORS_LIST) != null)
							errorList = (List) ctx
									.get(Constants.INET_ERRORS_LIST);

						errorList
								.add(new ValidationException(
										"Email/Phone do not match. Please contact 1-877-569-4111 for any assiatance"));
						ctx.put(Constants.INET_ERRORS_LIST, errorList);
						ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
					}
				}
			}
		} else {
			List errorList = new ArrayList();
			if (ctx.get(Constants.INET_ERRORS_LIST) != null)
				errorList = (List) ctx.get(Constants.INET_ERRORS_LIST);

			errorList
					.add(new ValidationException(
							"Email/Phone do not match. Please contact 1.888.803.9898 for any assiatance"));
			ctx.put(Constants.INET_ERRORS_LIST, errorList);
			ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
		}
	}

	public static boolean checkReferRuleforClaims1(List claimsList,
			Date effectiveDate) throws Exception {

		if (claimsList == null || claimsList.isEmpty() || effectiveDate == null)
			return false;
		Map map = new HashMap();
		for (int i = 0; i < claimsList.size(); i++) {
			map = (HashMap) claimsList.get(i);
			if (map.get("ClaimNotifiedCiDate") != null
					&& DateUtils.calculateDiffInDays(DateUtils.addMonthsToDate(
							effectiveDate, -12), (Date) map
							.get("ClaimNotifiedCiDate")) <= 0
					&& DateUtils.calculateDiffInDays(effectiveDate, (Date) map
							.get("ClaimNotifiedCiDate")) >= 0) {
				if (map.get("TotalClaimExpensesCi") != null
						&& Long.parseLong(map.get("TotalClaimExpensesCi")
								.toString()) > 0)
					return true;
			}

		}

		return false;
	}

	public static boolean checkReferRuleforClaims2(List claimsList,
			Date effectiveDate) throws Exception {

		if (claimsList == null || claimsList.isEmpty() || effectiveDate == null)
			return false;
		Map map = new HashMap();
		int count = 0;
		boolean flag = false;
		for (int i = 0; i < claimsList.size(); i++) {
			map = (HashMap) claimsList.get(i);
			if (map.get("ClaimNotifiedCiDate") != null
					&& DateUtils.calculateDiffInDays(DateUtils.addMonthsToDate(
							effectiveDate, -60), (Date) map
							.get("ClaimNotifiedCiDate")) <= 0
					&& DateUtils.calculateDiffInDays(effectiveDate, (Date) map
							.get("ClaimNotifiedCiDate")) >= 0) {
				count = count + 1;
			}

			if (map.get("ClaimNotifiedCiDate") != null
					&& DateUtils.calculateDiffInDays(DateUtils.addMonthsToDate(
							effectiveDate, -60), (Date) map
							.get("ClaimNotifiedCiDate")) <= 0
					&& DateUtils.calculateDiffInDays(DateUtils.addMonthsToDate(
							effectiveDate, -12), (Date) map
							.get("ClaimNotifiedCiDate")) >= 0
					&& map.get("TotalClaimExpensesCi") != null
					&& Long.parseLong(map.get("TotalClaimExpensesCi")
							.toString()) >= 50000)
				flag = true;
		}
		if (flag || count >= 3)
			return true;

		return false;
	}

	public static long calculateMeanForCompleteList(List datalist,
			String property) {
		if (datalist == null || datalist.isEmpty())
			return 0;

		List list = new ArrayList();
		for (int i = 0; i < datalist.size(); i++) {
			Map map = (Map) datalist.get(i);
			if (null != map.get(property)
					&& !"".equals(map.get(property).toString()))
				list.add(Long.parseLong(map.get(property).toString()));

		}
		return calculateMean(list);
	}

	private static long calculateMean(List list) {

		if (list == null || list.isEmpty())
			return 0;

		long sumtotal = 0;

		for (int i = 0; i < list.size(); i++) {

			sumtotal = sumtotal + (Long) list.get(i);
		}

		if (sumtotal == 0)
			return 0;

		return sumtotal / list.size();
	}

	public void getAverageRevenue(IContext ctx) throws Exception {

		int count = 0;
		long total = 0;
		long average = 0;

		Object obj0 = removeAmountFormat(ctx.get("Amount_0"));
		Object obj1 = removeAmountFormat(ctx.get("Amount_1"));
		Object obj2 = removeAmountFormat(ctx.get("Amount_2"));

		if (obj0 != null && !"".equals(obj0) && !"0".equals(obj0)) {
			count = count + 1;
			total = total + Long.parseLong(obj0.toString());
		}
		if (obj1 != null && !"".equals(obj1) && !"0".equals(obj1)) {
			count = count + 1;
			total = total + Long.parseLong(obj1.toString());
		}
		if (obj2 != null && !"".equals(obj2) && !"0".equals(obj2)) {
			count = count + 1;
			total = total + Long.parseLong(obj2.toString());
		}

		if (count != 0 && total != 0) {
			average = total / count;
		}

		ctx.put("AverageRevFromAjax", average);
	}

	public Object removeAmountFormat(Object arg1) {
		if (arg1 == null || "".equals(arg1.toString().trim()))
			return 0;

		String amount = arg1.toString();
		return amount.replace("$", "").replace(",", "");
	}

	public void processLimitDeductibleDropDown(IContext ctx) throws Exception {
		processDeductibeDropDown(ctx);
		processLimitDropDown(ctx);
	}

	public void processDeductibeDropDown(IContext ctx) throws Exception {

		Object obj = ctx.get("getLimitDeductibleDropDownPross");
		List listDeductible = null;
		if (obj != null)
			listDeductible = (List) obj;

		if (listDeductible == null)
			return;

		if (ctx.get("general_freeform_01") == null
				|| ctx.get("BasicInfo_freeform_00") == null)
			return;

		if (((Map) ctx.get("general_freeform_01"))
				.get("DeductibleSequencePross") == null
				|| ((Map) ctx.get("BasicInfo_freeform_00"))
						.get("DeductibleSequence") == null)
			return;

		String strDeductibleSequencePross = ((Map) ctx
				.get("general_freeform_01")).get("DeductibleSequencePross")
				.toString();
		String strDeductibleSequence = ((Map) ctx.get("BasicInfo_freeform_00"))
				.get("DeductibleSequence").toString();

		int indexPross = 0;
		int indexPolicyCov = 0;
		long amountPross = 0;
		long amountPolicyCov = 0;

		for (int i = 0; i < listDeductible.size(); i++) {
			Map map = (Map) listDeductible.get(i);

			String strDeductibleSeq = map.get("DeductibleSequence").toString();
			String strDeductibleAmount = removeAmountFormat(
					map.get("DeductibleAmount")).toString();
			if (strDeductibleSequencePross.equals(strDeductibleSeq)) {
				indexPross = i;
				// amountPross = Long.parseLong(strDeductibleAmount);
			}
			if (strDeductibleSequence.equals(strDeductibleSeq)) {
				indexPolicyCov = i;
				// amountPolicyCov = Long.parseLong(strDeductibleAmount);
			}
		}

		indexPross = indexPross - 2;

		if (indexPross < 0)
			ctx.put("referDeductible", "N");
		else if (indexPolicyCov < indexPross)
			ctx.put("referDeductible", "Y");
		else
			ctx.put("referDeductible", "N");

	}

	public void processLimitDropDown(IContext ctx) throws Exception {

		Object obj = ctx.get("getDropDownLimitsPross");
		List listLimit = null;
		if (obj != null)
			listLimit = (List) obj;

		if (listLimit == null)
			return;

		if (ctx.get("general_freeform_01") == null
				|| ctx.get("BasicInfo_freeform_00") == null)
			return;

		if (((Map) ctx.get("general_freeform_01")).get("LimitSequencePross") == null
				|| ((Map) ctx.get("BasicInfo_freeform_00"))
						.get("LimitSequence") == null)
			return;

		String strLimitSequencePross = ((Map) ctx.get("general_freeform_01"))
				.get("LimitSequencePross").toString();
		String strLimitSequence = ((Map) ctx.get("BasicInfo_freeform_00")).get(
				"LimitSequence").toString();

		long amountPross = 0;
		long amountPolicyCov = 0;
		long uperLimitAmt = 0;
		long lowerLimtAmt = 0;
		int amountProssIndex = 0;
		boolean upperFlag = false;
		boolean amountProssFlag = false;

		for (int i = 0; i < listLimit.size(); i++) {
			Map map = (Map) listLimit.get(i);

			String strLimitSeq = map.get("LimitSequence").toString();
			String strLimitAmount = removeAmountFormat(map.get("LimitAmount"))
					.toString();
			strLimitAmount = strLimitAmount.substring(0, strLimitAmount
					.indexOf("/"));
			if (strLimitSequencePross.equals(strLimitSeq)) {
				amountPross = Long.parseLong(strLimitAmount);
				amountProssFlag = true;
				amountProssIndex = i;
			}
			if (!upperFlag && amountProssFlag
					&& Long.parseLong(strLimitAmount) > amountPross) {
				uperLimitAmt = Long.parseLong(strLimitAmount);
				upperFlag = true;
			}
			if (strLimitSequence.equals(strLimitSeq)) {

				amountPolicyCov = Long.parseLong(strLimitAmount);
			}
		}

		for (; amountProssIndex >= 0; amountProssIndex--) {
			Map map = (Map) listLimit.get(amountProssIndex);

			String strLimitSeq = map.get("LimitSequence").toString();
			String strLimitAmount = removeAmountFormat(map.get("LimitAmount"))
					.toString();
			strLimitAmount = strLimitAmount.substring(0, strLimitAmount
					.indexOf("/"));

			if (Long.parseLong(strLimitAmount) < amountPross) {
				lowerLimtAmt = Long.parseLong(strLimitAmount);
				break;
			}

		}

		if (amountPolicyCov > uperLimitAmt)
			ctx.put("referLimit", "Y");
		else if (amountPolicyCov < lowerLimtAmt)
			ctx.put("referLimit", "Y");
		else
			ctx.put("referLimit", "N");

	}

	public static void validateBasicinfoDateField(IContext ctx)
			throws Exception {
		String strDate = null;
		if (ctx.get("PolicyEffectiveDate") != null
				&& !"".equals(ctx.get("PolicyEffectiveDate").toString())) {
			strDate = ctx.get("PolicyEffectiveDate").toString();
			validateLeapYearDate(ctx, "PolicyEffectiveDate", strDate);
		}

		if (ctx.get("IsFirmCarryingProfLiabilityIns") != null
				&& "Y".equals(ctx.get("IsFirmCarryingProfLiabilityIns")
						.toString())) {
			if (ctx.get("PolicyExpirationDatePross") != null
					&& !"".equals(ctx.get("PolicyExpirationDatePross")
							.toString())) {
				strDate = ctx.get("PolicyExpirationDatePross").toString();
				validateLeapYearDate(ctx, "PolicyExpirationDatePross", strDate);
			}

			if (ctx.get("IsPriorActDateFull") != null
					&& "N".equals(ctx.get("IsPriorActDateFull").toString())) {
				if (ctx.get("PriorActDatePross") != null
						&& !"".equals(ctx.get("PriorActDatePross").toString())) {
					strDate = ctx.get("PriorActDatePross").toString();
					validateLeapYearDate(ctx, "PriorActDatePross", strDate);
				}

			}
		}

		if (ctx.get("YearEndDate_0") != null
				&& !"".equals(ctx.get("YearEndDate_0").toString())) {
			strDate = ctx.get("YearEndDate_0").toString();
			validateMMYYYYDate(ctx, "estimate_year", strDate);
		}
		if (ctx.get("YearEndDate_1") != null
				&& !"".equals(ctx.get("YearEndDate_1").toString())) {
			strDate = ctx.get("YearEndDate_1").toString();
			validateMMYYYYDate(ctx, "estimate_year", strDate);
		}
		if (ctx.get("YearEndDate_2") != null
				&& !"".equals(ctx.get("YearEndDate_2").toString())) {
			strDate = ctx.get("YearEndDate_2").toString();
			validateMMYYYYDate(ctx, "estimate_year", strDate);
		}
	}

	public static void validateGeneralDateField(IContext ctx) throws Exception {
		String strDate = null;
		if (ctx.get("IsFirmRenderingServices") != null
				&& "Y".equals(ctx.get("IsFirmRenderingServices").toString())) {
			if (ctx.get("EstimateCurrentYearSeEndDate") != null
					&& !"".equals(ctx.get("EstimateCurrentYearSeEndDate")
							.toString())) {
				strDate = ctx.get("EstimateCurrentYearSeEndDate").toString();
				validateMMYYYYDate(ctx, "EstimateCurrentYearSeEndDate", strDate);
			}

			if (ctx.get("LastFiscalYearSeEndDate") != null
					&& !""
							.equals(ctx.get("LastFiscalYearSeEndDate")
									.toString())) {
				strDate = ctx.get("LastFiscalYearSeEndDate").toString();
				validateMMYYYYDate(ctx, "LastFiscalYearSeEndDate", strDate);
			}
		}
	}

	public static void validateLeapYearDate(IContext ctx, String field,
			String strDate) throws Exception {
		try {
			if (strDate != null && !strDate.isEmpty()) {
				strDate = strDate.trim();
				String strMonth = strDate.substring(0, strDate.indexOf("/"));
				String cursubstr = strDate.substring(strDate.indexOf("/") + 1,
						strDate.length());
				String strDay = cursubstr.substring(0, cursubstr.indexOf("/"));
				String strYear = cursubstr.substring(
						cursubstr.indexOf("/") + 1, cursubstr.length());

				int curMonth = Integer.parseInt(strMonth);
				int curDay = Integer.parseInt(strDay);
				int curYear = Integer.parseInt(strYear);

				if (strYear.trim().length() < 4) {
					populateError(ctx, field, "Invalid date format");
				} else if (curMonth == 0 || curDay == 0 || curYear == 0) {
					populateError(ctx, field, "Invalid date format");
				} else if (isLeapYear(curYear)) {
					if (curMonth == 2 && curDay > 29) {
						populateError(ctx, field, "Invalid date");
					} else if (curMonth != 2 && curDay > 31) {
						populateError(ctx, field, "Invalid date");
					} else if (curMonth > 13) {
						populateError(ctx, field, "Invalid date");
					}
				} else if (curMonth == 2 && curDay > 28) {
					populateError(ctx, field, "Invalid date");
				} else if (curMonth != 2 && curDay > 31) {
					populateError(ctx, field, "Invalid date");
				} else if (curMonth > 13) {
					populateError(ctx, field, "Invalid date");
				}
			}
		} catch (Exception e) {
			populateError(ctx, field, "Invalid date format");
		}
	}

	public static void validateMMYYYYDate(IContext ctx, String field,
			String strDate) throws Exception {
		try {
			if (strDate != null && !strDate.isEmpty()) {
				String strMonth = strDate.substring(0, strDate.indexOf("/"));
				String strYear = strDate.substring(strDate.indexOf("/") + 1,
						strDate.length());
				int curMonth = Integer.parseInt(strMonth);
				int curYear = Integer.parseInt(strYear);
				if (strYear.trim().length() < 4) {
					populateError(ctx, field, "Invalid date format");
				} else if (curMonth == 0 || curYear == 0) {
					populateError(ctx, field, "Invalid date");
				} else if (curMonth > 12) {
					populateError(ctx, field, "Invalid date");
				}

			}
		} catch (Exception e) {
			populateError(ctx, field, "Invalid date format");
		}
	}

	public static boolean isLeapYear(int dtYear) {
		if (dtYear % 4 == 0) {
			if (dtYear % 100 != 0) {
				return true;
			} else {
				if (dtYear % 400 == 0)
					return true;
				else
					return false;
			}
		}
		return false;
	}

	public static boolean validateTrustee(Context ctx) throws Exception {
		String strDate = null;
		if (ctx.get("StartEngagemenTrusttDate") != null
				&& !"".equals(ctx.get("StartEngagemenTrusttDate").toString())) {
			strDate = ctx.get("StartEngagemenTrusttDate").toString();
			validateLeapYearDate(ctx, "StartEngagemenTrusttDate", strDate);
		}

		return true;
	}

	public static boolean validateClaim(Context ctx) throws Exception {
		String strDate = null;
		if (ctx.get("AllegedErrorCiDate") != null
				&& !"".equals(ctx.get("AllegedErrorCiDate").toString())) {
			strDate = ctx.get("AllegedErrorCiDate").toString();
			validateLeapYearDate(ctx, "AllegedErrorCiDate", strDate);
		}

		if (ctx.get("ReportedCiDate") != null
				&& !"".equals(ctx.get("ReportedCiDate").toString())) {
			strDate = ctx.get("ReportedCiDate").toString();
			validateLeapYearDate(ctx, "ReportedCiDate", strDate);
		}

		if (ctx.get("ClaimNotifiedCiDate") != null
				&& !"".equals(ctx.get("ClaimNotifiedCiDate").toString())) {
			strDate = ctx.get("ClaimNotifiedCiDate").toString();
			validateLeapYearDate(ctx, "ClaimNotifiedCiDate", strDate);
		}

		if (ctx.get("ClosingCiDate") != null
				&& !"".equals(ctx.get("ClosingCiDate").toString())) {
			strDate = ctx.get("ClosingCiDate").toString();
			validateLeapYearDate(ctx, "ClosingCiDate", strDate);
		}

		return true;
	}

	public void processSupplement(IContext ctx) throws Exception {

		isPMSupplementExist(ctx);
		isInvestmentSupplementExist(ctx);
		isAuditSupplementExist(ctx);
		isComputerServiceSupplementExist(ctx);
		isBusinessSupplementExist(ctx);
		validateCompleteness(ctx);
	}

	public static void validateCompleteness(IContext ctx) throws Exception {

		if (ctx.get("AverageRevFromAjax") != null)
			;
		{
			long avg = Long.parseLong(ctx.get("AverageRevFromAjax").toString());
			if (avg <= 500000) {
				ctx.put("IsPracriceMangPage", "N");
				((HttpServletRequest) ctx.get(Constants.HTTPREQUEST))
						.getSession().removeAttribute("IsPracriceMangPage");
			}
		}

		if (ctx.get("AOP_Percentage_19") == null
				|| "".equals(ctx.get("AOP_Percentage_19").toString().trim())
				|| Integer.parseInt(ctx.get("AOP_Percentage_19").toString()
						.trim()) == 0) {
			ctx.put("IsSupplementPage", "N");
			((HttpServletRequest) ctx.get(Constants.HTTPREQUEST)).getSession()
					.removeAttribute("IsSupplementPage");
		}

		if ((ctx.get("AOP_Percentage_5") == null
				|| "".equals(ctx.get("AOP_Percentage_5").toString().trim()) || Integer
				.parseInt(ctx.get("AOP_Percentage_5").toString().trim()) == 0)
				&& (ctx.get("AOP_Percentage_6") == null
						|| "".equals(ctx.get("AOP_Percentage_6").toString()
								.trim()) || Integer.parseInt(ctx.get(
						"AOP_Percentage_6").toString().trim()) != 0)) {
			ctx.put("IsAttesationPage", "N");
			((HttpServletRequest) ctx.get(Constants.HTTPREQUEST)).getSession()
					.removeAttribute("IsAttesationPage");
		}

		if (ctx.get("AOP_Percentage_14") == null
				|| "".equals(ctx.get("AOP_Percentage_14").toString().trim())
				|| Integer.parseInt(ctx.get("AOP_Percentage_14").toString()
						.trim()) == 0) {
			ctx.put("IsComputerServicePage", "N");
			((HttpServletRequest) ctx.get(Constants.HTTPREQUEST)).getSession()
					.removeAttribute("IsComputerServicePage");
		}

		String IsControlClientFunds = "";
		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"Firm.findByKey", ctx);
		if (obj != null) {
			Map map = (Map) obj;
			if (map.get("IsControlClientFunds") != null
					&& !"".equals(map.get("IsControlClientFunds").toString()))
				IsControlClientFunds = map.get("IsControlClientFunds")
						.toString();
		}

		if ((ctx.get("AOP_Percentage_18") == null
				|| "".equals(ctx.get("AOP_Percentage_18").toString().trim()) || Integer
				.parseInt(ctx.get("AOP_Percentage_18").toString().trim()) == 0)
				&& ("".equals(IsControlClientFunds) || "N"
						.equals(IsControlClientFunds))) {
			ctx.put("IsBusinessFundPage", "N");
			((HttpServletRequest) ctx.get(Constants.HTTPREQUEST)).getSession()
					.removeAttribute("IsBusinessFundPage");
		}

		SqlResources.getSqlMapProcessor(ctx).update("Firm.update", ctx);

	}

	public void isPMSupplementExist(IContext ctx) throws Exception {

		getAverageRevenue(ctx);
		if (ctx.get("AverageRevFromAjax") != null)
			;
		{
			long avg = Long.parseLong(ctx.get("AverageRevFromAjax").toString());
			if (avg <= 500000) {
				ctx.put("IsSuitInstituted", "");
				ctx.put("AgainstHowManyClients", "");
				ctx.put("IsNonMonetaryCompensationRecieved", "");
				ctx.put("CurrentValueNmc", "");
				ctx.put("ClientNameNmc", "");
				ctx.put("ClientIndustryNmcKey", 0);
				ctx.put("ClientIndustryNmcKeyOthers", "");
				ctx.put("ServiceIndustryNmcKey", 0);
				ctx.put("ServiceIndustryNmcKeyOthers", "");
				ctx.put("IsReducedNumberOfOwners", "");
				ctx.put("MergedOrNumberComment", "");
				ctx.put("IsClientTerminated", "");
				ctx.put("IsAnyDeclination", "");
				ctx.put("IsPolicyProhibiting", "");
				ctx.put("IsWrittenAnyQualityControlDocument", "");

				SqlResources.getSqlMapProcessor(ctx).update("Firm.update", ctx);
			}
		}
	}

	public void isInvestmentSupplementExist(IContext ctx) throws Exception {

		if (ctx.get("AOP_Percentage_19") == null
				|| "".equals(ctx.get("AOP_Percentage_19").toString().trim())
				|| Integer.parseInt(ctx.get("AOP_Percentage_19").toString()
						.trim()) == 0) {
			ctx.put("IsFinancialPlansPreparing", "");
			ctx.put("IsDiscretionaryAssetMangt", "");
			ctx.put("IsNonDiscretionaryAssetMangt", "");
			ctx.put("IsSecuritiesSales", "");
			ctx.put("IsContractualRelationShip", "");
			ctx.put("IsOmissionsPolicy", "");
			ctx.put("IsAnyNonPublicInvestments", "");
			ctx.put("IsAnyFirmAffiliateLicensed", "");
			ctx.put("IsAnyEmployeeBenefitPlan", "");

			SqlResources.getSqlMapProcessor(ctx).update(
					"FinancialPlanningAndIAServiceSupp.update", ctx);
		}
	}

	public void isAuditSupplementExist(IContext ctx) throws Exception {

		if ((ctx.get("AOP_Percentage_5") == null
				|| "".equals(ctx.get("AOP_Percentage_5").toString().trim()) || Integer
				.parseInt(ctx.get("AOP_Percentage_5").toString().trim()) == 0)
				&& (ctx.get("AOP_Percentage_6") == null
						|| "".equals(ctx.get("AOP_Percentage_6").toString()
								.trim()) || Integer.parseInt(ctx.get(
						"AOP_Percentage_6").toString().trim()) != 0)) {
			ctx.put("FirmRenderedAuditAtt", "");
			ctx.put("IsFirmHaveAWrittenPolicyAtt", "");
			ctx.put("IsSecondPartnerReviewAtt", "");
			ctx.put("IsAuditEngagementsAtt", "");
			ctx.put("IsAualityReviewAtt", "");
			ctx.put("IsLastPeerReviewAtt", "");
			ctx.put("LastPeerReviewAttComment", "");

			SqlResources.getSqlMapProcessor(ctx).update("Attestation.update",
					ctx);
			SqlResources
					.getSqlMapProcessor(ctx)
					.delete(
							"SqlStmts.UserStatementdeleteListAttestationAuditWork",
							ctx);

		}
	}

	public void isComputerServiceSupplementExist(IContext ctx) throws Exception {

		if (ctx.get("AOP_Percentage_14") == null
				|| "".equals(ctx.get("AOP_Percentage_14").toString().trim())
				|| Integer.parseInt(ctx.get("AOP_Percentage_14").toString()
						.trim()) == 0) {

			ctx.put("OtherITServiceComment", "");

			SqlResources.getSqlMapProcessor(ctx).update("Firm.update", ctx);
			SqlResources.getSqlMapProcessor(ctx).delete(
					"SqlStmts.UserStatementdeleteListITServicesSupp", ctx);

		}

	}

	public void isBusinessSupplementExist(IContext ctx) throws Exception {

		String IsControlClientFunds = "";
		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"Firm.findByKey", ctx);
		if (obj != null) {
			Map map = (Map) obj;
			if (map.get("IsControlClientFunds") != null
					&& !"".equals(map.get("IsControlClientFunds").toString()))
				IsControlClientFunds = map.get("IsControlClientFunds")
						.toString();
		}

		if ((ctx.get("AOP_Percentage_18") == null
				|| "".equals(ctx.get("AOP_Percentage_18").toString().trim()) || Integer
				.parseInt(ctx.get("AOP_Percentage_18").toString().trim()) == 0)
				&& ("".equals(IsControlClientFunds) || "N"
						.equals(IsControlClientFunds))) {
			ctx.put("TotalAmountOfClientFundsFc", "");
			ctx.put("IsCountersignatureRequiredFc", "");
			ctx.put("IsBankAccountReconciledFc", "");
			ctx.put("IsFirmPersonnelBusinessManagerFc", "");
			ctx.put("NumberOfClientsFc", "");
			ctx.put("NumberOfFirmEmployeesFc", "");
			ctx.put("IsEmployeeDishonestyCoverageFc", "");
			ctx.put("TotlaNumberOfClientsFc", "");
			ctx.put("IsControlFundsForBusinessAndEntimentFc", "");
			ctx.put("ProvideNumberOfClientFc", "");
			ctx.put("ClientIndustryFc", "");
			ctx.put("ClientIndustryFcOthers", "");

			SqlResources.getSqlMapProcessor(ctx).update(
					"FundsControlledSupp.update", ctx);
		}
	}

	public void processSearchList(IContext ctx) throws Exception {

		Map map = new HashMap();
		map.put("paginationaction", "setrecordcount");
		map.put("pagenumber", "1");
		map.put("recordsperpage", "20");
		map.put("listKey", "Search_firm_list_01");
		map.put("list", ctx.get("Search_firm_list_01"));
		map.put("datadisplayid", "ajax_sorting");

		ctx.put("paginationinfo", map);
		//new PaginationUtils().populatePaginatedList((Context) ctx, "");

	}

	public static void populateLastUpdateTimeStamp(IContext ctx)
			throws Exception {
		RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");
		if (ctx.get("LastUpdateTimeStamp") != null)
			ctx.put("UploadedTime", new SimpleDateFormat(DATE_PATTERN)
					.format(ctx.get("LastUpdateTimeStamp")));
	}

	public void populateAgentDetail(IContext ctx) throws Exception {

		Object obj1 = RuleUtils.executeRule(ctx, "LawyersRule.isAgent");
		boolean flag = false;
		if (obj1 != null && obj1 instanceof Boolean)
			flag = (Boolean) obj1;

		if (flag) {
			Object obj = ctx.get("userdetailsInfoList");
			if (obj != null) {
				List list = (List) obj;
				int count = 0;
				boolean emailMatched = false;
				boolean pwdMatched = false;

				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					{
						if (map.get("AgentEmail").toString().equalsIgnoreCase(
								ctx.get("AccountEmail").toString())) {
							ctx.put("AgentState", map.get("AgentState"));
							ctx.put("AgentEmail", map.get("AgentEmail"));
							ctx.put("LastUpdateUserID", map.get("UserNo"));
							ctx.put("UserNo", map.get("UserNo"));
							ctx.put("AgentName", map.get("AgentName"));
							ctx.put("LicenseNumber", map.get("LicenseNumber"));
							ctx.put("Org_LicenseNumber", map
									.get("LicenseNumber"));
							break;
						}
					}
				}
			}
		} else
			ctx.put("UserNo", ctx.get("FirstReviewUserID"));

	}

}
