package com.userbo;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;

import com.fop.XML2PDF;
import com.formgenerator.DownloadForm;
import com.mail.MailSender;
import com.manage.docmanagement.DocManagementUtil;
import com.manage.managecomponent.components.ComponentImpl;
import com.manage.managecomponent.components.ComponentResources;
import com.manage.managecomponent.components.SetParametersForStoredProcedures;
import com.manage.managecomponent.components.WebservicecallImpl;
import com.manage.managemetadata.functions.commonfunctions.DBUtils;
import com.manage.managemetadata.functions.commonfunctions.DataUtils;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.manage.managemetadata.util.exception.ValidationException;
import com.ormapping.ibatis.SqlResources;
import com.ormapping.ibatis.SqlMapProcessor;
import com.pdf.PrintTextLocations;
import com.util.Constants;
import com.util.Context;
import com.util.DateUtils;
import com.util.DocumentGenerationBO;
import com.util.HtmlConstants;
import com.util.IContext;
import com.util.InetLogger;
import com.util.MessageStringFormat;
import com.util.RuleEngineUtility;
import com.util.RuleEngineUtilityNewApp;
import com.util.StringUtils;
import com.util.SubproducerMailer;
import com.util.SystemProperties;
import com.userbo.DocumentManagment;

public class LawyersUtils {

	private static final int MAXNUMBEROFATTORNEY = 20;

	private static String[] referredStates = { "AR", "AK", "FL", "LA", "NY", "OK", "VA", "SD", "VT" };
	private static String[] countyRequiredStates = { "CA", "KY", "FL", "NJ", "NY", "PA", "MI", "TX", "IL" };
	public static String DATE_PATTERN = "MM-dd-yyyy hh:mm:ss";
	private static final String ISMIE_POLICY_PREFIX = "ALA-04";
	private static final int ISMIE_POLICY_SEQUENCE_LENGTH = 6;
	private static final int ISMIE_MAX_SUFFIX_ATTEMPTS = 100;
	private static int lastGeneratedISMIEPolicySequence = 0;
	private static final Map lastGeneratedISMIESuffixByBasePolicyNumber = new HashMap();
	private static final String PROTEXURE_ESTIMATE_GENERAL_PARAMS = "LimitSequence,DeductibleSequence,IsClaimExpensesType,IsClaimOptionType,IsDollarDefense,AccountEmail,InsuranceCompanyNamePross,PolicyExpirationDatePross,ProInsPremium,IsPriorActDateFull,PriorActDatePross,FirmYear,hasAnyFirmPersonnelReported,AccountName,ContactPerson,Address1,StateCode,CountyDesc,City,Zip,WorkPhone,LastUpdateTimeStamp,LastUpdateUserID,addAttorneys,outputList1,outputList,MarketableProductKey";
	private static final String PROTEXURE_ESTIMATE_LOG_DIR_PROPERTY = "appl.LawyersIns.estimateform.log.dir";
	private static final String PROTEXURE_ESTIMATE_REVIEWER_RS_STATES = "CT#GA#FL#IL";
	private static final String PROTEXURE_ESTIMATE_REVIEWER_QB_STATES = "CA#CO#IN#TX#WA#ID#AZ#MA#NJ#MN#WI#NV#PA#AK#KY#MT#ND#NM#SD#WY#MD#ME#NY#NH#VT#KS#VA#TN#UT#NC#SC#OH#MO#MI#AL#AR#HI#IA#MS#NE#OK#DE#DC#LA#WV#RI";
	private static final String UNDERWRITING_RULE_PARAMS = "PolicyKey,AccountKey,AccountID,AddressKey,VersionSequence,PolicyStatusKey,LastUpdateUserID";
	private static final String COMPANY_LOOKUP_CACHE_KEY = "__CompanyLookupCacheKey";
	private static final String COMPANY_LOOKUP_CACHE_RESULT = "__CompanyLookupCacheResult";
	private static final String[] UNDERWRITING_RULE_STATEMENTS = {
			"rulepolicytransaction.UWRules_AboutYou_LW",
			"rulepolicytransaction.UWRules_InsuranceHistory_LW",
			"rulepolicytransaction.UWRules_Attorneys_LW",
			"rulepolicytransaction.UWRules_LawPractice_LW",
			"rulepolicytransaction.UWRules_FirmInfo_LW",
			"rulepolicytransaction.UWRules_BankruptcySupplement_LW",
			"rulepolicytransaction.UWRules_CollectionsSupplement_LW",
			"rulepolicytransaction.UWRules_CopyrightTrademarkSupplement_LW",
			"rulepolicytransaction.UWRules_CorporateCommercialSupplement_LW",
			"rulepolicytransaction.UWRules_FamilyLawSupplement_LW",
			"rulepolicytransaction.UWRules_FinancialInstitutionSupplement_LW",
			"rulepolicytransaction.UWRules_GovernmentSupplement_LW",
			"rulepolicytransaction.UWRules_PLaintiffSupplement_LW",
			"rulepolicytransaction.UWRules_RealEstateSupplement_LW",
			"rulepolicytransaction.UWRules_WillsTrustsEstatesSupplement_LW",
			"rulepolicytransaction.UWRules_TaxSupplement_LW",
			"rulepolicytransaction.UWRules_QuoteOptions_LW"
	};

	private static InetLogger logger = InetLogger
			.getInetLogger(LawyersUtils.class);

	private static boolean hasProtexureEstimateValue(Object value) {
		return value != null && !value.equals(HtmlConstants.EMPTY) && value.toString().trim().length() > 0;
	}

	private static synchronized void logProtexureEstimateSubmit(Context ctx, String status, String message, Throwable throwable) {
		PrintWriter writer = null;
		try {
			File logFile = getProtexureEstimateLogFile();
			File logDirectory = logFile.getParentFile();
			if (logDirectory != null && !logDirectory.exists() && !logDirectory.mkdirs()) {
				logger.error("Unable to create Protexure estimate submit log directory " + logDirectory.getAbsolutePath());
				return;
			}

			writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(logFile, true), StandardCharsets.UTF_8));
			writer.println("================================================================================");
			writer.println("ESTIMATEFORM SUBMIT LOG");
			appendEstimateLogValue(writer, "Timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
			appendEstimateLogValue(writer, "Status", status);
			appendEstimateLogValue(writer, "Message", message);

			if (shouldWriteProtexureEstimateFormDetails(status)) {
				appendProtexureEstimateValidationErrors(writer, ctx);
				appendEstimateLogSection(writer, "SPECIAL CONTACT SECTION - USE IF DATABASE SAVE FAILS");
				appendEstimateLogField(writer, "Law Firm Name", ctx, "AccountName");
				appendEstimateLogField(writer, "Contact Person", ctx, "ContactPerson");
				appendEstimateLogField(writer, "Email", ctx, "AccountEmail");
				appendEstimateLogField(writer, "Phone", ctx, "WorkPhone");
				appendEstimateLogField(writer, "Address", ctx, "Address1");
				appendEstimateLogField(writer, "City", ctx, "City");
				appendEstimateLogField(writer, "State", ctx, "StateCode");
				appendEstimateLogField(writer, "County", ctx, "CountyDesc");
				appendEstimateLogField(writer, "Zip", ctx, "Zip");

				appendEstimateLogSection(writer, "ESTIMATE DETAILS");
				appendEstimateLogField(writer, "Limit Sequence", ctx, "LimitSequence");
				appendEstimateLogField(writer, "Aggregate Limit", ctx, "AggregateLimit");
				appendEstimateLogField(writer, "Occurrence Limit", ctx, "OccuranceLimit");
				appendEstimateLogField(writer, "Deductible Sequence", ctx, "DeductibleSequence");
				appendEstimateLogField(writer, "Occurrence Deductible", ctx, "OccuranceDeductible");
				appendEstimateLogField(writer, "Expenses Outside Limit", ctx, "IsClaimExpensesType");
				appendEstimateLogField(writer, "Claim Option Type", ctx, "IsClaimOptionType");
				appendEstimateLogField(writer, "First Dollar Defense", ctx, "IsDollarDefense");
				appendEstimateLogField(writer, "Marketable Product Key", ctx, "MarketableProductKey");

				appendEstimateLogSection(writer, "PRIOR POLICY / CLAIMS");
				appendEstimateLogField(writer, "Current Carrier", ctx, "InsuranceCompanyNamePross");
				appendEstimateLogField(writer, "Policy Expiration Date", ctx, "PolicyExpirationDatePross");
				appendEstimateLogField(writer, "Current Premium", ctx, "ProInsPremium");
				appendEstimateLogField(writer, "Has Prior Acts Date", ctx, "IsPriorActDateFull");
				appendEstimateLogField(writer, "Prior Acts Date", ctx, "PriorActDatePross");
				appendEstimateLogField(writer, "Firm Year", ctx, "FirmYear");
				appendEstimateLogField(writer, "Claim/Discipline Reported", ctx, "hasAnyFirmPersonnelReported");

				appendProtexureEstimateAttorneyLogFields(writer, ctx);
				appendProtexureEstimateAopLogFields(writer, ctx);
			}

			if (throwable != null) {
				appendEstimateLogSection(writer, "EXCEPTION");
				writer.println("Exception=" + throwable.toString());
				StringWriter stackTrace = new StringWriter();
				throwable.printStackTrace(new PrintWriter(stackTrace));
				writer.println(stackTrace.toString());
			}
			writer.flush();
		} catch (Exception e) {
			logger.error("Unable to write Protexure estimate submit log: " + e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	private static void appendEstimateLogSection(PrintWriter writer, String sectionName) {
		writer.println();
		writer.println("-- " + sectionName + " --");
	}

	private static void appendEstimateLogField(PrintWriter writer, String label, Context ctx, String key) {
		appendEstimateLogValue(writer, label, getEstimateLogValue(ctx, key));
	}

	private static void appendEstimateLogValue(PrintWriter writer, String label, Object value) {
		writer.println(String.format("%-34s : %s", label, sanitizeEstimateLogValue(value)));
	}

	private static boolean shouldWriteProtexureEstimateFormDetails(String status) {
		return !"SUCCESS".equals(status) && !"END".equals(status);
	}

	private static void appendProtexureEstimateValidationErrors(PrintWriter writer, Context ctx) {
		List errorList = getProtexureEstimateErrorList(ctx);
		if (errorList == null || errorList.isEmpty()) {
			return;
		}

		appendEstimateLogSection(writer, "VALIDATION ERRORS");
		for (int i = 0; i < errorList.size(); i++) {
			Object error = errorList.get(i);
			if (error instanceof ValidationException) {
				ValidationException validationException = (ValidationException) error;
				writer.println((i + 1) + ". " + sanitizeEstimateLogValue(validationException.getField())
						+ " : " + sanitizeEstimateLogValue(validationException.getMessage()));
			} else {
				writer.println((i + 1) + ". " + sanitizeEstimateLogValue(error));
			}
		}
	}

	private static List getProtexureEstimateErrorList(Context ctx) {
		if (ctx == null || !(ctx.get(Constants.INET_ERRORS_LIST) instanceof List)) {
			return null;
		}
		return (List) ctx.get(Constants.INET_ERRORS_LIST);
	}

	private static void appendProtexureEstimateAttorneyLogFields(PrintWriter writer, Context ctx) {
		appendEstimateLogSection(writer, "ATTORNEY DETAILS FROM SUBMITTED FORM");
		appendEstimateLogField(writer, "Attorney Count", ctx, "addAttorneys");
		int attorneyCount = getEstimateLogInteger(ctx, "addAttorneys");
		if (attorneyCount <= 0) {
			writer.println("No submitted attorney rows found.");
			return;
		}

		for (int i = 0; i < attorneyCount; i++) {
			writer.println("Attorney Row " + (i + 1));
			appendEstimateLogField(writer, "  Name", ctx, "AttorneyName_" + i);
			appendEstimateLogField(writer, "  Annual Hours", ctx, "AnnualHours_" + i);
			appendEstimateLogField(writer, "  Date Joined Firm", ctx, "AttorneyPriorActDate_" + i);
			appendEstimateLogField(writer, "  Existing Attorney Key", ctx, "EstimateFormAttorneyKey_" + i);
		}
	}

	private static void appendProtexureEstimateAopLogFields(PrintWriter writer, Context ctx) {
		if (ctx == null) {
			return;
		}

		List aopKeys = new ArrayList();
		try {
			Iterator iterator = ctx.keySet().iterator();
			while (iterator.hasNext()) {
				Object key = iterator.next();
				if (key != null && key.toString().startsWith("AOP_Percentage_")) {
					aopKeys.add(key.toString());
				}
			}
		} catch (Exception e) {
			logger.error("Unable to collect Protexure estimate AOP log values: " + e);
		}

		if (aopKeys.isEmpty()) {
			return;
		}

		Collections.sort(aopKeys);
		appendEstimateLogSection(writer, "AOP PERCENTAGES FROM SUBMITTED FORM");
		for (int i = 0; i < aopKeys.size(); i++) {
			String key = aopKeys.get(i).toString();
			appendEstimateLogField(writer, key, ctx, key);
		}
		appendEstimateLogField(writer, "Other AOP Description", ctx, "otherAopDesc");
	}

	private static File getProtexureEstimateLogFile() {
		String logBasePath = null;
		try {
			Object configuredPath = SystemProperties.getInstance().getProperty(PROTEXURE_ESTIMATE_LOG_DIR_PROPERTY);
			if (configuredPath != null && configuredPath.toString().trim().length() > 0) {
				logBasePath = configuredPath.toString();
			}
		} catch (Exception e) {
			logger.error("Unable to read Protexure estimate submit log directory property: " + e);
		}

		if (logBasePath == null || logBasePath.trim().length() == 0) {
			String tomcatHome = null;
			try {
				Object tomcatHomeProperty = SystemProperties.getInstance().getProperty("tomcat.home.dir");
				if (tomcatHomeProperty != null) {
					tomcatHome = tomcatHomeProperty.toString();
				}
			} catch (Exception e) {
				logger.error("Unable to read tomcat.home.dir for Protexure estimate submit logs: " + e);
			}
			if (tomcatHome == null || tomcatHome.trim().length() == 0) {
				tomcatHome = System.getProperty("java.io.tmpdir");
			}
			logBasePath = tomcatHome + File.separator + "logs" + File.separator + "LawyersIns" + File.separator + "EstimateForm";
		}

		File datedDirectory = new File(logBasePath, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return new File(datedDirectory, "EstimateFormSubmit.log");
	}

	private static String getEstimateLogValue(Context ctx, String key) {
		if (ctx == null) {
			return "";
		}
		try {
			if ("Project".equals(key)) {
				return sanitizeEstimateLogValue(ctx.getProject());
			}
			return sanitizeEstimateLogValue(ctx.get(key));
		} catch (Exception e) {
			return "";
		}
	}

	private static String sanitizeEstimateLogValue(Object value) {
		if (value == null) {
			return "";
		}
		return value.toString().replace('\r', ' ').replace('\n', ' ').trim();
	}

	private static int getEstimateLogInteger(Context ctx, String key) {
		try {
			String value = getEstimateLogValue(ctx, key);
			if (value.length() > 0) {
				return Integer.parseInt(value);
			}
		} catch (Exception e) {
			logger.error("Unable to parse Protexure estimate log integer for " + key + ": " + e);
		}
		return 0;
	}

	private static boolean isProtexureEstimateReviewerState(String stateList, String stateCode) {
		return hasProtexureEstimateValue(stateCode) && ("#" + stateList + "#").contains("#" + stateCode + "#");
	}

	private static void executeUnderwritingRule(SqlMapProcessor sqlProcessor, Context ctx, String statementId) {
		try {
			sqlProcessor.update(statementId, ctx);
		} catch (Exception e) {
			logger.error("Error while executing underwriting rule " + statementId + ": " + e);
			logger.error("Unexpected error", e);
		}
	}

	public void checkInsertGrossRevenue(IContext ctx) throws Exception {
		long total = 0;
		int count = 0;

		boolean bool = false; // validateEstimateForCurrentYear(ctx);
		if (!bool) {
			Object obj = DBUtils
					.executeDBOperation(ctx, "GrossRevenueLW", "15");
			if (obj != null) {
				List personnelList = (List) obj;
				for (int i = 0; i < personnelList.size(); i++) {
					Map map = (Map) personnelList.get(i);
					Context newctx = new Context();
					newctx.setProject(ctx.getProject());
			    	newctx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
			    	newctx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
					newctx.put("GrossRevenueKey",
							ctx.get("GrossRevenueKey" + "_" + i));
					newctx.put("PolicyKey", ctx.get("PolicyKey"));
					newctx.put("VersionSequence", ctx.get("VersionSequence"));
					newctx.put("TransactionSeq", ctx.get("TransactionSeq"));
					newctx.put("YearEndDate", ctx.get("YearEndDate" + "_" + i));

					Object objAmount = removeAmountFormat(ctx.get("Amount"
							+ "_" + i));
					if (objAmount != null && !"".equals(objAmount.toString())
							&& !"0".equals(objAmount.toString())) {
						total = total + Long.parseLong(objAmount.toString());
						count = count + 1;
					}

					newctx.put("Amount", objAmount);
					DBUtils.executeDBOperation(newctx,
							"GrossRevenueForFirmLW", "2");
					DBUtils.executeDBOperation(newctx,
							"GrossRevenueForFirmLW", "1");
					/*Object obj2 = DBUtils.executeDBOperation(newctx,
							"GrossRevenueForFirmLW", "3");
					if (obj2 == null) {
						DBUtils.executeDBOperation(newctx,
								"GrossRevenueForFirmLW", "1");
					} else {
						DBUtils.executeDBOperation(newctx,
								"GrossRevenueForFirmLW", "2");
						DBUtils.executeDBOperation(newctx,
								"GrossRevenueForFirmLW", "1");
					}*/
				}
				if (count > 0) {
					long AverageRevenue = total / count;
					ctx.put("AverageRevenue", AverageRevenue);
				} else
					ctx.put("AverageRevenue", 0);
			}
		}
	}

	public static Object removeAmountFormat(Object arg1) {
		if (arg1 == null || "".equals(arg1.toString().trim()))
			return 0;

		String amount = arg1.toString();
		return amount.replace("$", "").replace(",", "");
	}

	@SuppressWarnings("unchecked")
	public void generateQuoteNumber(IContext ctx) throws Exception {

//		System.out.println("hello");
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
						populateError(ctx, "insuredError",
								"Email/Phone do not match. Please contact 1-877-569-4111 for any assistance");
						/*
						 * List errorList = new ArrayList();
						 * if(ctx.get(Constants.INET_ERRORS_LIST) != null)
						 * errorList = (List)
						 * ctx.get(Constants.INET_ERRORS_LIST);
						 * 
						 * errorList.add(new ValidationException("Email/Phone do
						 * not match. Please contact 1-877-569-4111 for any
						 * assistance")); ctx.put(Constants.INET_ERRORS_LIST,
						 * errorList); ctx.put(Constants.INET_FORM_DIRTY,
						 * Constants.YES);
						 */
					}
				}
			}
		} else {
			/*
			 * List errorList = new ArrayList(); if
			 * (ctx.get(Constants.INET_ERRORS_LIST) != null) errorList = (List)
			 * ctx.get(Constants.INET_ERRORS_LIST);
			 * 
			 * errorList.add(new ValidationException("Email/Phone do not match.
			 * Please contact 1.877.569.4111 for any assistance"));
			 * ctx.put(Constants.INET_ERRORS_LIST, errorList);
			 * ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
			 */
			populateError(ctx, "insuredError",
					"Email/Phone do not match. Please contact 1-877-569-4111 for any assistance");
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

	public void processLimitDeductibleDropDown(IContext ctx) throws Exception {
		processDeductibeDropDown(ctx);
		processLimitDropDown(ctx);
	}

	public void processDeductibeDropDown(IContext ctx) throws Exception {

		Object obj = ctx.get("getDeductiblesList");
		List listDeductible = null;
		if (obj != null)
			listDeductible = (List) obj;

		if (listDeductible == null)
			return;

		String strDeductibleSequencePross = ctx.get("DeductibleSequencePross") != null ? ctx
				.get("DeductibleSequencePross").toString() : "";
		String strDeductibleSequence = ((Map) ctx.get("coverage_freeform_6"))
				.get("DeductibleSequence").toString();

		String aggAmount = null;
		if (ctx.get("coverage_freeform_6") != null
				&& ((Map) ctx.get("coverage_freeform_6")).get("aggDeductible") != null) {

			aggAmount = ((Map) ctx.get("coverage_freeform_6")).get(
					"aggDeductible").toString();

		}

		int intAggAmount = 0;
		if (aggAmount != null) {
			intAggAmount = Integer.parseInt(aggAmount);
		}

		List attorneyList = null;
		int size = 0;
		if (ctx.get("firm_list_4") != null) {
			attorneyList = (List) ctx.get("firm_list_4");
			size = attorneyList.size();
		}

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

		// indexPross = indexPross - 2;

		if (indexPross > indexPolicyCov) {
			indexPross = indexPross - indexPolicyCov;
		} else if (indexPross < indexPolicyCov) {
			indexPross = indexPolicyCov - indexPross;
		} else {
			indexPross = indexPross - indexPolicyCov;
		}

		if (indexPross > 2)
			ctx.put("referDeductible", "Y");
		else if (size == 1 && intAggAmount > 10000)
			ctx.put("referDeductible", "Y");
		else
			ctx.put("referDeductible", "N");
		/*
		 * Object obj = ctx.get("getDeductiblesList"); List listDeductible =
		 * null; if (obj != null) listDeductible = (List) obj;
		 * 
		 * if (listDeductible == null) return;
		 * 
		 * String strDeductibleSequencePross =
		 * ctx.get("DeductibleSequencePross") .toString(); String
		 * strDeductibleSequence = ((Map) ctx.get("coverage_freeform_6"))
		 * .get("DeductibleSequence").toString();
		 * 
		 * String aggAmount = null; if (ctx.get("coverage_freeform_6") != null
		 * && ((Map) ctx.get("coverage_freeform_6")).get("aggDeductible") !=
		 * null) {
		 * 
		 * aggAmount = ((Map) ctx.get("coverage_freeform_6")).get(
		 * "aggDeductible").toString(); }
		 * 
		 * int intAggAmount = 0; if (aggAmount != null) { intAggAmount =
		 * Integer.parseInt(aggAmount); }
		 * 
		 * List attorneyList = null; int size = 0; if (ctx.get("firm_list_4") !=
		 * null) { attorneyList = (List) ctx.get("firm_list_4"); size =
		 * attorneyList.size(); }
		 * 
		 * int indexPross = 0; int indexPolicyCov = 0; long amountPross = 0;
		 * long amountPolicyCov = 0;
		 * 
		 * for (int i = 0; i < listDeductible.size(); i++) { Map map = (Map)
		 * listDeductible.get(i);
		 * 
		 * String strDeductibleSeq = map.get("DeductibleSequence").toString();
		 * String strDeductibleAmount = removeAmountFormat(
		 * map.get("DeductibleAmount")).toString(); if
		 * (strDeductibleSequencePross.equals(strDeductibleSeq)) { indexPross =
		 * i; // amountPross = Long.parseLong(strDeductibleAmount); } if
		 * (strDeductibleSequence.equals(strDeductibleSeq)) { indexPolicyCov =
		 * i; // amountPolicyCov = Long.parseLong(strDeductibleAmount); } }
		 * 
		 * indexPross = indexPross - 2;
		 * 
		 * if (indexPross < 0) ctx.put("referDeductible", "N"); else if
		 * ((indexPolicyCov < indexPross) || (size == 0 && intAggAmount >
		 * 10000)) ctx.put("referDeductible", "Y"); else
		 * ctx.put("referDeductible", "N");
		 */
	}

	public void processLimitDropDown(IContext ctx) throws Exception {

		Object obj = ctx.get("getLimitsList");
		List listLimit = null;
		if (obj != null)
			listLimit = (List) obj;

		if (listLimit == null)
			return;

		List attorneyList = null;
		int size = 0;
		if (ctx.get("firm_list_4") != null) {
			attorneyList = (List) ctx.get("firm_list_4");
			size = attorneyList.size();
		}

		String strLimitSequencePross = ctx.get("LimitSequencePross") != null ? ctx
				.get("LimitSequencePross").toString() : "";

		String strLimitSequence = null;
		String strOcLimit = null;
		String strAggLimit = null;
		boolean limitMatched = false;
		if (ctx.get("coverage_freeform_6") != null) {
			strLimitSequence = ((Map) ctx.get("coverage_freeform_6")).get(
					"LimitSequence").toString();
			Object obj1 = ((Map) ctx.get("coverage_freeform_6")).get("ocLimit");
			Object obj2 = ((Map) ctx.get("coverage_freeform_6"))
					.get("aggLimit");
			if (obj1 != null && obj2 != null) {
				strOcLimit = obj1.toString();
				strAggLimit = obj2.toString();
			}
			int intOcLimit = 0;
			int intAggLimit = 0;
			if (strOcLimit != null && strAggLimit != null) {
				intOcLimit = Integer.parseInt(strOcLimit);
				intAggLimit = Integer.parseInt(strAggLimit);
			}

			if ((intOcLimit > 1000000 || intAggLimit > 1000000) && size == 1) {
				limitMatched = true;
			}
		}

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
			strLimitAmount = strLimitAmount.substring(0,
					strLimitAmount.indexOf("/"));
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
			strLimitAmount = strLimitAmount.substring(0,
					strLimitAmount.indexOf("/"));

			if (Long.parseLong(strLimitAmount) < amountPross) {
				lowerLimtAmt = Long.parseLong(strLimitAmount);
				break;
			}

		}

		if (amountPolicyCov > uperLimitAmt || limitMatched)
			ctx.put("referLimit", "Y");
		else if (amountPolicyCov < lowerLimtAmt || limitMatched)
			ctx.put("referLimit", "Y");
		else
			ctx.put("referLimit", "N");

	}

	@SuppressWarnings("unchecked")
	public void checkAgentExist(IContext ctx) throws Exception {

		// if(ctx.get("AccountEmail") == null)
		// LawyersUtils.populateError(ctx, "AccountEmail", "Email ID is
		// required");
		// else if(ctx.get("AccountEmail") != null &&
		// "".equals(ctx.get("AccountEmail").toString().trim()))
		// LawyersUtils.populateError(ctx, "AccountEmail", "Email ID is
		// required");
		//
		// if(ctx.get("PassWord") == null)
		// LawyersUtils.populateError(ctx, "PassWord", "Password is required");
		// else if(ctx.get("PassWord") != null &&
		// "".equals(ctx.get("PassWord").toString().trim()))
		// LawyersUtils.populateError(ctx, "PassWord", "Password is required");
		// Object obj = ctx.get("userdetailsInfoList");
		//
		// if (obj != null) {
		// List list = (List) obj;
		// int count = 0;
		// boolean emailMatched = false;
		// boolean pwdMatched = false;
		//
		// for (int i = 0; i < list.size(); i++) {
		// Map map = (Map) list.get(i);
		// if (ctx.get("AgentEmail") != null
		// && !"".equals(ctx.get("AgentEmail"))) {
		// if (map.get("AgentEmail").toString().equalsIgnoreCase(
		// ctx.get("AgentEmail").toString())
		// || (map.get("PassWord") != null && map.get(
		// "PassWord").equals(ctx.get("PassWord")))) {
		//
		// if (map.get("AgentEmail").toString().equalsIgnoreCase(
		// ctx.get("AccountEmail").toString()))
		// emailMatched = true;
		//
		//
		// if (map.get("AgentEmail").toString().equalsIgnoreCase(
		// ctx.get("AccountEmail").toString())
		// && map.get("PassWord").equals(
		// ctx.get("PassWord"))) {
		// ctx.put("AgentState", map.get("AgentState"));
		// ctx.put("AgentEmail", map.get("AgentEmail"));
		// ctx.put("LastUpdateUserID", map.get("UserNo"));
		// ctx.put("UserNo", map.get("UserNo"));
		// ctx.put("AgentName", map.get("AgentName"));
		// ctx.put("LicenseNumber", map.get("LicenseNumber"));
		// count++;
		// return;
		// }
		// }
		// }
		// }
		//
		// if (count == 0) {
		// List errorList = new ArrayList();
		// if (ctx.get(Constants.INET_ERRORS_LIST) != null)
		// errorList = (List) ctx.get(Constants.INET_ERRORS_LIST);
		//
		// if (!emailMatched && !pwdMatched)
		// errorList.add(new ValidationException(
		// "User ID/Password are wrong"));
		// else if (!emailMatched)
		// errorList.add(new ValidationException(
		// "User ID does not exist"));
		// else if (!pwdMatched)
		// errorList.add(new ValidationException("Password is wrong"));
		//
		// ctx.put(Constants.INET_ERRORS_LIST, errorList);
		// ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
		// }
		//
		// }

		if (ctx.get("AccountEmail") == null)
			LawyersUtils.populateError(ctx, "AccountEmail",
					"User ID is required");
		else if (ctx.get("AccountEmail") != null
				&& "".equals(ctx.get("AccountEmail").toString().trim()))
			LawyersUtils.populateError(ctx, "AccountEmail",
					"User ID is required");

		if (ctx.get("PassWord") == null)
			LawyersUtils.populateError(ctx, "PassWord", "Password is requiredd");
		else if (ctx.get("PassWord") != null
				&& "".equals(ctx.get("PassWord").toString().trim()))
			LawyersUtils.populateError(ctx, "PassWord", "Password is requiredd");

		Object obj = ctx.get("userdetailsInfoList");
		if (obj != null) {
			List list = (List) obj;
			int count = 0;
			boolean emailMatched = false;
			boolean pwdMatched = false;

			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				if (map.get("AgentEmail").toString()
						.equalsIgnoreCase(ctx.get("AccountEmail").toString())) {
					ctx.put("AgentState", map.get("AgentState"));
					ctx.put("AgentEmail", map.get("AgentEmail"));
					ctx.put("LastUpdateUserID", map.get("UserNo"));
					ctx.put("UserNo", map.get("UserNo"));
					ctx.put("AgentName", map.get("AgentName"));
					ctx.put("LicenseNumber", map.get("LicenseNumber"));

					break;
				}
			}
		}
	}

	public void sendLoginMail(IContext ctx) throws Exception {
		String emailID = getEmailID(ctx);
		if ("".equals(emailID))
			return;

		logger.debug("Mail is going---- ");
		MailSender mail = new MailSender();
		mail.setToAdd(emailID);
		mail.setSubject("Thank You from Protexure");
		mail.setContentType("text/html");
		mail.setBody(generateLinkBody(ctx));
		mail.sendMail((Context) ctx);
		logger.debug("Mail has sent---- ");

	}
	
	
	
	
	public String generateLinkBody(IContext ctx) throws Exception {
		StringBuilder msg = new StringBuilder(2048);

		msg.append("<table><tr><td>");
		msg.append("Dear ").append(ctx.get("AccountName")).append(",<br/><br/>");
	    msg.append("Thank you for completing our quick quote. We appreciate your interest in Protexure Lawyers");
		msg.append("<br/><br/>For a Limit of liability of :").append(ctx.get("LimitAmount"));
		msg.append("<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Deductible :").append(ctx.get("DeductibleAmount"));
		
		
		if( "Y".equals(ctx.get("IsMinimumPremium").toString() )){
			msg.append("<br/><br/><h2><center>Your Premium is :").append(ctx.get("TotalPremium")).append("</h2></center>");
		} else {
			msg.append("<br/><br/><h2><center>Your Premium Estimate is :").append(ctx.get("EstimatePremiumRange")).append("</h2></center>");
		}
		
		
		msg.append("To continue on to our quick and easy full application please click below:");
		
		msg.append("<br><br><a href=\"").append(getProjectUrl(ctx)).append("/qqlogin.jsp?PolicyKey=").append(ctx.get("PolicyKey")).append("&AccountKey=").append(ctx.get("AccountKey")).append("&TransactionSequence=").append(ctx.get("TransactionSequence")).append("&VersionSequence=").append(ctx.get("VersionSequence")).append("&CoverageSequence=").append(ctx.get("CoverageSequence")).append("\">Continue to your Full Online Application</a>");
		
		msg.append("<br/><br/>The information you provided has been pre-entered into the application to make the process easier.");
		
		
		msg.append("<br/><br/>At Protexure Lawyers we are committed to helping firms like yours manage their professional liability risk. To help you better evaluate your options we have included some additional information on our <a href='http://info.protexurelawyers.com/protexure-lawyers-professional-liability-insurance#Features'> policy features</a>,<a href='http://info.protexurelawyers.com/protexure-lawyers-professional-liability-insurance#Claims'> claims handling </a>, and a <a href='http://info.protexurelawyers.com/protexure-lawyers-professional-liability-insurance#Specimen'>specimen policy</a>.<br/>");
		msg.append("<br/>If you have any additional questions, please contact us at our toll-free number 877-569-4111 and one of our licensed professional liability specialists will be happy to assist you.");
		
		msg.append("<br/><br/><b>Sincerely,</b>");
		
		msg.append("<br/><b>The Protexure Team</b>");
		msg.append("<br/><b>877-569-4111</b>");
		msg.append("<br/><b>Fax: (440) 333-3214</b>");
		msg.append("<br/><b>www.protexurelawyers.com</b>");
		
		/*msg += "<br/><b>www.protexurelawyers.com</b>";*/
		
		/*msg += "<a href=\"" + getProjectUrl(ctx) + "/qqlogin.jsp?PolicyKey=" + ctx.get("PolicyKey").toString() + "&AccountKey=" + ctx.get("AccountKey").toString() + "&TransactionSequence=" + ctx.get("TransactionSequence").toString() + "&VersionSequence=" + ctx.get("VersionSequence").toString() + "&CoverageSequence=" + ctx.get("CoverageSequence").toString() + "\"> link</a>";
		msg += "<br/><br/>At Protexure Accountants we are committed to helping firms like yours manage their professional liability risk. Based on the specific area(s) of practice you selected, we have created a risk management assessment. Attached below you will find a personalized risk assessment complete with claims scenarios and best practices to help you minimize your exposure.";
		msg += "<br/><br/>If you have any questions please contact one of our experienced professional liability specialists at 888-803-9898.";
		
		
		msg += "<br/><br/>Sincerely,";
		msg += "<br/><br/><b>The Protexure Accountants Team</b>" + "<br/>";*/

		msg.append("</td></tr></table>");

		return msg.toString();
	}

	/*public String generateLinkBody(IContext ctx) throws Exception {
		String msg = "";

		msg = msg + "<table>";
		msg = msg + "<tr>";
		msg = msg + "<td>";

		msg = msg + "Dear (" + ctx.get("AccountName").toString() + "),"
				+ "<br/><br/>";
		msg = msg
				+ "Thank you for completing our premium estimate. We appreciate your interest in Protexure Lawyers. We at Protexure Lawyers strive to provide the highest levels of security for your information. To review your information or to continue on to our quick and easy full application, if you have not"
				+ "<br/>";
		msg = msg + " done so, please " + "<a href=" + getProjectUrl(ctx)
				+ "/login.jsp?PolicyKey=" + ctx.get("PolicyKey").toString()
				+ ">click this link</a>"
				+ " to register and receive a secure id and password. "
				+ "If you have already registered please " + "<a href="
				+ getProjectUrl(ctx) + "/lawyersrep.jsp?PolicyKey="
				+ ctx.get("PolicyKey").toString() + ">click here </a>"
				+ " to review your estimate." + "<br/><br/>";

		// msg = msg + "<a href=" + getProjectUrl(ctx) +
		// "/accountrep.jsp?AccountEmail="+ctx.get("AccountEmail").toString() +
		// ">click this link</a>" + "<br/>";
		// msg = msg + "If you have already registered please " + "<a href=" +
		// getProjectUrl(ctx) +
		// "/accountrep.jsp?AccountEmail="+ctx.get("AccountEmail").toString() +
		// ">click here </a>" + " to review your estimate." + "<br/>";
		// msg = msg + getProjectUrl(ctx) +
		// "/accountrep.jsp?AccountEmail="+ctx.get("AccountEmail").toString() +
		// "<br/><br/>";

		msg = msg
				+ "If you have any questions please contact one of our experienced professional liability specialists at 1-877-569-4111."
				+ "<br/><br/>";
		msg = msg + "Sincerely," + "<br/><br/>";
		msg = msg + "<b>The Protexure Lawyers Team</b>" + "<br/>";

		msg = msg + "</td>";
		msg = msg + "</tr>";
		msg = msg + "</table>";

		return msg;

	}*/

	public void sendWorkQMail(IContext ctx) throws Exception {
		String bccAddress="";
		String emailID = getEmailID(ctx);
		if ("".equals(emailID))
			return;

		logger.debug("Mail is going---- ");
		MailSender mail = new MailSender();
		mail.setToAdd(emailID);
	
		if("".equals(bccAddress))
			bccAddress=SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.bcc.email");
		mail.setBccAdd(bccAddress);
		mail.setSubject("Thank You from Protexure");
		mail.setContentType("text/html");
		mail.setBody(generateWorkQMailBody(ctx));
		mail.sendMail((Context) ctx);
		logger.debug("Mail has sent---- ");

	}

	private String generateWorkQMailBody(IContext ctx) throws Exception {
		StringBuilder msg = new StringBuilder(512);

		msg.append("<table><tr><td>");
		msg.append("Dear (").append(ctx.get("AccountName")).append("),<br/><br/>");
		msg.append("Thank you for contacting Protexure. <a href=")
				.append(getProjectUrl(ctx)).append("/login.jsp?PolicyKey=")
				.append(ctx.get("PolicyKey")).append(">Click here</a>")
				.append(" for the registration link to set up your account with us.<br/><br/> ");
		// msg = msg + " done so, please " + "<a href=" + getProjectUrl(ctx) +
		// "/login.jsp?AccountEmail="+ctx.get("AccountEmail").toString() +
		// ">click this link</a>" + " to register and receive a secure id and
		// password. " + "If you have already registered please " + "<a href=" +
		// getProjectUrl(ctx) +
		// "/accountrep.jsp?AccountEmail="+ctx.get("AccountEmail").toString() +
		// ">click here </a>" + " to review your estimate." + "<br/><br/>";

		msg.append("If you have any questions please contact us at 1-877-569-4111. <br/><br/>");
		msg.append("Sincerely,<br/><br/>");
		msg.append("<b>The Protexure Lawyers Team</b><br/>");
		msg.append("</td></tr></table>");

		return msg.toString();
	}

	public void sendMail(IContext ctx) throws Exception {
		String mailingOnOFF = null;
		String bccAddress="";
		try {
			mailingOnOFF = SystemProperties.getInstance().getString(
					"Insured.sendmail");
		} catch (Exception e) {
			logger.error("Unable to read insured mail setting", e);
		}
		if (mailingOnOFF != null && "Y".equals(mailingOnOFF)) {
			String emailID = getEmailID(ctx);
			
			if ("".equals(emailID))
				return;

			MailSender mail = new MailSender();
			mail.setToAdd(emailID);
			if("".equals(bccAddress))
				bccAddress=SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.bcc.email");
			mail.setSubject("Thank you for your interest in Protexure Lawyers");
			mail.setContentType("text/html");
			mail.setBody(generateThanksLetterBody(ctx));
			mail.sendMail();
			logger.debug("Mail has sent---- ");

		}
	}

	private String generateThanksLetterBody(IContext ctx) throws Exception {
		StringBuilder msg = new StringBuilder(1024);
		msg.append("<table><tr><td>");
		msg.append("Dear ").append(ctx.get("AccountName")).append(",<br/><br/>");
		msg.append("Thank you for beginning the Protexure Lawyers application. We appreciate the opportunity to provide you with a quote. Choosing the right plan is very important and we believe that the options we offer will provide you and your firm with peace of mind.<br/><br/>");
		msg.append("Once you have completed your application, our underwriters will begin the review process and will contact you within one business day.<br/><br/>");
		msg.append("The security of your information is important to us. If you have not already registered please <a href=")
				.append(getProjectUrl(ctx)).append("/login.jsp?PolicyKey=").append(ctx.get("PolicyKey"))
				.append(">click here</a>. To review your application securely you will need to register.<br/><br/>");
		msg.append("If you have already registered, <a href=").append(getProjectUrl(ctx))
				.append("/lawyersrep.jsp?PolicyKey=").append(ctx.get("PolicyKey")).append(">click here</a>.<br/><br/>");
		msg.append("If you have any questions or concerns, please call us toll free at 1-877-569-4111, and one of our Account Executives will assist you.<br/><br/>");
		msg.append("Thank you again for considering Protexure Lawyers for your Professional Liability needs.<br/><br/>");
		msg.append("Best regards,<br/><br/>");
		msg.append("<b>Protexure Lawyers</b><br/>");
		msg.append("<b>1-877-569-4111</b>");
		msg.append("</td></tr></table>");
		return msg.toString();
	}

	public static void validateBusinessStateOnContinue(IContext ctx)
			throws Exception {
		ctx.get("IsFirmPage");
		if (ctx.get("StateCode") != null
				&& !"".equals(ctx.get("StateCode").toString())) {

			Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey(
					"State.findByKey", ctx);
			if (obj != null && obj instanceof Map) {
				Map map = (Map) obj;
				ctx.put("IsBusinessActiveLW", map.get("IsBusinessActiveLW"));
				if (map.get("IsBusinessActiveLW") != null
						&& !"Y".equals(map.get("IsBusinessActiveLW").toString()))
					populateError(ctx, "",
							"We are not currently approved in this state.");

				if (map.get("IsBusinessActiveLW") == null)
					populateError(ctx, "",
							"We are not currently approved in this state.");
			}
		}
		
		if(ctx.get("Zip") != null && !"".equals(ctx.get("Zip").toString())){
			String insuredZip = ctx.get("Zip").toString();
			insuredZip = insuredZip.replace("-", "");
			if(insuredZip.length() != 5 && insuredZip.length() != 9){
				populateError(ctx, "Zip",
						"Zip Code length should be 5 or 9");
			}
		}
	}

	public void assignDefenseExpenses(IContext ctx) throws Exception
	{
		if (ctx.get("IsProfDefenceExpense") != null
				&& !"".equals(ctx.get("IsProfDefenceExpense").toString())) {
			ctx.put("IsProfDefenceExpense", "Y");
		} else {
			ctx.put("IsProfDefenceExpense", "N");
		}

		if (ctx.get("IsDollarDefense") != null
				&& !"".equals(ctx.get("IsDollarDefense").toString())) {
			ctx.put("IsDollarDefense", "Y");
		} else {
			ctx.put("IsDollarDefense", "N");
		}
	}

	public static boolean validateFirm(Context ctx) throws Exception {
		if (ctx.get("WorkPhone") != null && !ctx.get("WorkPhone").equals("")) {
			if (!validatePhoneNumber(ctx.get("WorkPhone").toString())) {
				populateError(ctx, "WorkPhone",
						"Phone should be atleast of 10 digits.");
			}
		}
		if (ctx.get("BackupAttorneyPhoneNumber") != null
				&& !ctx.get("BackupAttorneyPhoneNumber").equals("")) {
			if (!validatePhoneNumber(ctx.get("BackupAttorneyPhoneNumber")
					.toString())) {
				populateError(ctx, "BackupAttorneyPhoneNumber",
						"Phone should be atleast of 10 digits.");
			}
		}
		List list = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementdropdowndatacountyDropDown", ctx);
		if (list != null) {
			ctx.put("getCountyList", list);
		}
		List attList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.firmviewgetAttorneysList", ctx);
		if (attList == null || (attList != null && attList.size() == 0))
			populateError(ctx, "attorneyError",
					"Attorneys list is empty, atleast one record is required.");

		if (attList != null && attList.size() == 1) {
			if (ctx.get("IsIndependentContractor") == null) {
				populateError(ctx, "IsIndependentContractor",
						"Please indicate Yes on No.");
			}
			if (ctx.get("IsFirmHaveBackupAttorney") == null) {
				populateError(ctx, "IsFirmHaveBackupAttorney",
						"Please indicate Yes on No.");
			}

		} else {
			ctx.put("IsIndependentContractor", null);
			ctx.put("IsFirmHaveBackupAttorney", null);
			ctx.put("PerOfGrossBillingsUnderContract", null);
			ctx.put("BackupAttorneyName", null);
			ctx.put("BackupAttorneyAddress", null);
			ctx.put("BackupAttorneyPhoneNumber", null);
			ctx.put("BackupAttorneyAddress2", null);
		}

		boolean isAgent = false;
		Object objAgentRule = RuleUtils.executeRule(ctx, "LawyersRule.isAgent");
		if (objAgentRule != null && objAgentRule instanceof Boolean) {
			isAgent = (Boolean) objAgentRule;
		}

		if (isAgent) {
			boolean isRated = false;
			if (attList != null && attList.size() > 0) {
				for (int i = 0; i < attList.size(); i++) {
					Map map = (Map) attList.get(i);
					if (map.get("IsNonRatedAttorney") != null
							&& "Y".equals(map.get("IsNonRatedAttorney")
									.toString())) {

					} else {
						isRated = true;
						break;
					}
				}

				if (!isRated)
					populateError(ctx, "attorneyError",
							"Atleast one attorney should be a rated.");
			}

		}

		/*
		 * boolean flag = false; Object obj = RuleUtils .executeRule(ctx,
		 * "LawyersRule.showReadOnlyEmail"); if (obj != null && obj instanceof
		 * Boolean) { flag = (Boolean) obj;
		 * 
		 * if (!flag) { checkFirmWithEmailExist(ctx); } }
		 */

		boolean flag = false;
		Object obj = RuleUtils.executeRule(ctx,
				"LawyersRule.validateEmailOnFirmSave");
		if (obj != null && obj instanceof Boolean) {
			flag = (Boolean) obj;

			if (flag) {
				checkFirmWithEmailExist(ctx);
			}

		}

		validateBusinessStateOnContinue(ctx);
		validateEstimateForCurrentYear(ctx);
		validateFirmPrimaryLocationList(ctx);
		cleanFirm(ctx);
		trimFirmApplicantName(ctx);
		validateCountyField(ctx);
		validateCityField(ctx);
		validateFiscalYearDate(ctx);
		validateFiscalYearData(ctx);
		validateCountyForTX(ctx);
		if (ctx.get("StateCode") != null
				&& "MO".equals(ctx.get("StateCode").toString()))
			cleanDataForMissouri(ctx);

		LawyersValidationUtils.validateEmail(ctx, "AccountEmail",
				ctx.get("AccountEmail") != null ? ctx.get("AccountEmail")
						.toString().trim() : "");

		return true;
	}
	public static void validateCountyForTX(IContext ctx) throws Exception{
		
		if(ctx.get("StateCode") != null && ctx.get("StateCode").toString().equals("TX")){			
			String countyDesc = ctx.get("CountyDesc") != null ? ctx.get("CountyDesc").toString() : "";
			if("".equals(countyDesc)){
				LawyersUtils.populateError(ctx, "CountyDesc", "County is required.");
			}else{
				
				List countyListTX = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriescheckCountyListTX", ctx);
				if(countyListTX != null && countyListTX.size() > 0){
					
				}else{
					LawyersUtils.populateError(ctx, "CountyDesc", "Your county is not listed please contact us at " + LawyersConstants.PHONENUMBER + " for assistance.");					
				}
				
			}
			
		}
		
	}

	public static void validateFiscalYearDate(IContext ctx) throws Exception {

		for (int i = 0; i < 1; i++) {
			String date = ctx.get("YearEndDate_" + i) != null ? ctx.get(
					"YearEndDate_" + i).toString() : "";
			LawyersValidationUtils.validateDateFormat(date, "estimate_year",
					ctx, "Fiscal year is not valid");
		}

	}

	public static void trimFirmApplicantName(IContext ctx) {

		if (ctx.get("AccountName") != null
				&& !"".equals(ctx.get("AccountName").toString()))
			ctx.put("AccountName", ctx.get("AccountName").toString().trim());

		if (ctx.get("ContactPerson") != null
				&& !"".equals(ctx.get("ContactPerson").toString()))
			ctx.put("ContactPerson", ctx.get("ContactPerson").toString().trim());

		if (ctx.get("AccountEmail") != null
				&& !"".equals(ctx.get("AccountEmail").toString()))
			ctx.put("AccountEmail", ctx.get("AccountEmail").toString().trim());

		if (ctx.get("City") != null && !"".equals(ctx.get("City").toString()))
			ctx.put("City", ctx.get("City").toString().trim());

	}

	/*
	 * public static void checkFirmWithEmailExist(IContext ctx) throws Exception
	 * {
	 * 
	 * boolean flag = false; boolean isFirmExist = false;
	 * 
	 * ctx.get("isFirmPage");
	 * 
	 * Object obj = RuleUtils .executeRule(ctx,
	 * "LawyersRule.showReadOnlyEmail"); if (obj != null && obj instanceof
	 * Boolean) { flag = (Boolean) obj; if (!flag) {
	 * 
	 * List listFirm = SqlResources.getSqlMapProcessor(ctx).select(
	 * "SqlStmts.indexagentlogingetAccountDetails", ctx);
	 * 
	 * if (listFirm != null && listFirm.size() >= 1) isFirmExist = true;
	 * 
	 * if (isFirmExist) populateError(ctx, "AccountEmail", "This user already
	 * exists, please contact 1-877-569-4111 for any assistance"); } } }
	 */
	public static void validateAOPPercentage(IContext ctx) throws Exception {
		int total = 0;
		Map map = new HashMap();
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select(
				"AOPLW.findAll", ctx);
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
				try {
					total = total + Integer.parseInt(percentageValue);
				} catch (Exception e) {
					populateError(ctx, "TotalAOP_Percentage",
							"Invalid AOP data");
				}
			}
		}
		if (total < 100 || total > 100) {
			percentageError(ctx, total, "100");
		}
	}

	protected static void percentageError(IContext ctx, int total,
			String percentage) throws Exception {
		populateError(ctx, "TotalAOP_Percentage",
				"The total AOP percentage is required to equal 100%, current total is "
						+ total + "%");
	}

	public static void validateAOPComment(IContext ctx) throws Exception {
		Map map = new HashMap();
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select(
				"AOPLW.findAll", ctx);
		if (limtTypes != null) {
			for (int i = 0; i < limtTypes.size(); i++) {
				map = (HashMap) limtTypes.get(i);
				if (map.get("AOPKey").toString().equals("25")) {
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
							String msg = "Please describe";
							if (map.get("AOPKey").toString().equals("25"))
								msg = "Please describe";

							populateError(ctx,
									"AOPCommentDesc_"
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

	@SuppressWarnings("unchecked")
	public static void populateError(IContext ctx, String field, String msg)
			 {
		try
		{
		List errorList = new ArrayList();
		if (ctx.get(Constants.INET_ERRORS_LIST) != null)
			errorList = (List) ctx.get(Constants.INET_ERRORS_LIST);
		ValidationException e = new ValidationException(msg);
		e.setField(field);
		errorList.add(e);
		ctx.put(Constants.INET_ERRORS_LIST, errorList);
		ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
		}
		catch(Exception e)
		{
			logger.error("Exception occured while populating error message");
		}
	}

	protected static void cleanFirm(IContext ctx) throws Exception {
		if (ctx.get("IsIndependentContractor") != null
				&& ctx.get("IsIndependentContractor").equals("N")) {
			ctx.put("PerOfGrossBillingsUnderContract", "");
		}
		if (ctx.get("IsFirmHaveBackupAttorney") != null
				&& ctx.get("IsFirmHaveBackupAttorney").equals("N")) {
			ctx.put("BackupAttorneyName", "");
			ctx.put("BackupAttorneyAddress", "");
			ctx.put("BackupAttorneyAddress2", "");
			ctx.put("BackupAttorneyPhoneNumber", "");
		}
		if (ctx.get("IsApplicantProvidesLegalServices") != null
				&& ctx.get("IsApplicantProvidesLegalServices").equals("N")) {
			ctx.put("ApplicantProvidesLegalServicesDesc", "");
		}
		if (ctx.get("IsApplRestWithManagement") != null
				&& ctx.get("IsApplRestWithManagement").equals("Y")) {
			ctx.put("ApplRestWithManagementDesc", "");
		}
		if (ctx.get("IsFirmPracticeInOtherState") != null
				&& ctx.get("IsFirmPracticeInOtherState").equals("N")) {
			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementcheckAjaxListdeleteFirmPracticeLocationLWList",
							ctx);
			ctx.put("IsApplRestWithManagement", "");
			ctx.put("ApplRestWithManagementDesc", "");
		}

		if (ctx.get("IsApplFirmWithDifferentLegalName") != null
				&& ctx.get("IsApplFirmWithDifferentLegalName").equals("N")) {
			ctx.put("ApplLegalNameAddressDesc", "");
		}

		if (ctx.get("IsApplicantProvidesLegalServices") != null
				&& ctx.get("IsApplicantProvidesLegalServices").equals("N")) {
			ctx.put("ApplicantProvidesLegalServicesDesc", "");
		}

	}

	public static boolean validatePractice(Context ctx) throws Exception {
		// validateFirmAnnualRevenueList(ctx);

		// validatePersonnelServedList(ctx);
		validateApplLawSuits(ctx);
		cleanPractice(ctx);
		validatePercentageFieldPractise(ctx);
		/*
		 * if (ctx.get("IsFirmHaveClientMoreThan25PercentOfBilling") != null &&
		 * "Y".equals(ctx.get( "IsFirmHaveClientMoreThan25PercentOfBilling")
		 * .toString())) {
		 * 
		 * String date1 = ctx.get("DateRenderedFirstLargestRevenueClient") !=
		 * null ? ctx .get("DateRenderedFirstLargestRevenueClient").toString() :
		 * ""; String date2 = ctx.get("DateRenderedSecondLargestRevenueClient")
		 * != null ? ctx
		 * .get("DateRenderedSecondLargestRevenueClient").toString() : "";
		 * 
		 * if (!"".equals(date1)) validateLeapYearDate(ctx,
		 * "DateRenderedFirstLargestRevenueClient", date1);
		 * 
		 * if (!"".equals(date2)) validateLeapYearDate(ctx,
		 * "DateRenderedSecondLargestRevenueClient", date2); }
		 */
		return true;
	}

	public static boolean validateCoverage(Context ctx) throws Exception {
		
		Object val = ctx.get("IsFirmHaveLawyersLiabilityInsurance");
		
		if (ctx.get("ProInsPremium") != null) {
			Object ProInsPremium = removeAmountFormat(ctx
					.get("ProInsPremium"));
			ctx.put("ProInsPremium", ProInsPremium);
		}
		String strDate = null;
		if (ctx.get("PersonnelBeSubOfAnyInvestDate") != null
				&& !ctx.get("PersonnelBeSubOfAnyInvestDate").equals("")) {
			strDate = ctx.get("PersonnelBeSubOfAnyInvestDate").toString();
			validateLeapYearDate(ctx, "PersonnelBeSubOfAnyInvestDate", strDate);
		}
		if (ctx.get("AttorneyDeclineForProfLiabilityDates") != null
				&& !ctx.get("AttorneyDeclineForProfLiabilityDates").equals("")) {
			strDate = ctx.get("AttorneyDeclineForProfLiabilityDates")
					.toString();
			validateLeapYearDate(ctx, "AttorneyDeclineForProfLiabilityDates",
					strDate);
		}
		if (ctx.get("PolicyExpirationDatePross") != null
				&& !ctx.get("PolicyExpirationDatePross").equals("")) {
			strDate = ctx.get("PolicyExpirationDatePross").toString();

			validateLeapYearDate(ctx, "PolicyExpirationDatePross", strDate);
		}
		if (ctx.get("PriorActDatePross") != null
				&& !ctx.get("PriorActDatePross").equals("")) {
			strDate = ctx.get("PriorActDatePross").toString();
			validateLeapYearDate(ctx, "PriorActDatePross", strDate);
		}
		if (ctx.get("PolicyEffectiveDate") != null
				&& !ctx.get("PolicyEffectiveDate").equals("")) {
			strDate = ctx.get("PolicyEffectiveDate").toString();
			validateLeapYearDate(ctx, "PolicyEffectiveDate", strDate);
		}
		new LawyersValidationUtils().validatePolicyEffectiveDateCoverage(ctx);

		if (ctx.get("IsFirmHaveLawyersLiabilityInsurance") != null
				&& !"".equals(ctx.get("IsFirmHaveLawyersLiabilityInsurance")
						.toString())) {
			if ("Y".equals(ctx.get("IsFirmHaveLawyersLiabilityInsurance")
					.toString())) {

				if (ctx.get("IsPriorActDateFull") != null) {
					if (!"".equals(ctx.get("IsPriorActDateFull").toString())
							&& "N".equals(ctx.get("IsPriorActDateFull")
									.toString()))
						new LawyersValidationUtils()
								.validatePriorActDatePross(ctx);
				}
			}
		}
		validatePredessorFirmsList(ctx);
		validateClaimsList(ctx);
		cleanCoverage(ctx);

		// validateDuplicateQuote(ctx);

		validateLimitsAndClaimExpense(ctx);

		// added to set IsManualPremium In PolicyCoverage as N, earlier it was
		// null
		// This is required when validating GetAnotherQuote for quote already
		// exist.
		// if(ctx.get("IsManualPremium") == null)
		// ctx.put("IsManualPremium", "N");

		logger.debug("Po Tr 5---"
				+ ctx.get("TransactionSequence").toString());

		if (ctx.get("StateCode") != null
				&& "MO".equals(ctx.get("StateCode").toString()))
			cleanDataForMissouri(ctx);

		return true;
	}

	public static void validateDuplicateQuote(IContext ctx) throws Exception {

		boolean isNonRatingState = new LawyersValidationUtils()
				.isNonRatingState(ctx);

		if (isNonRatingState)
			ctx.put("IsManualPremium", "Y");
		else
			ctx.put("IsManualPremium", "N");

		Object obj = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementpdfquotelettercheckIfQuoteExist", ctx);

		List quoteList = null;
		if (obj != null && obj instanceof List)
			quoteList = (List) obj;

		if (quoteList != null && quoteList.size() > 0)
			populateError(ctx, "QuoteAlreadyExist",
					"This quote already exist, please select different combination.");

		ctx.put("IsManualPremium", null);

	}

	public static void cleanDataForMissouri(IContext ctx) throws Exception {

		ctx.put("IsAttorneyDeclineForProfLiability", "");
		ctx.put("AttorneyDeclineForProfLiabilityDates", "");
		ctx.put("AttorneyDeclineForProfLiabilityReasons", "");

	}

	public static void validateLimitsAndClaimExpense(IContext ctx)
			throws Exception {

		Object objMap = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"State.findByKey", ctx);

		if (objMap != null && objMap instanceof Map) {

			Map objState = (Map) objMap;

			if (objState.get("StateCode").toString()
					.equals(LawyersConstants.ARKANSAS)
					|| objState.get("StateCode").toString()
							.equals(LawyersConstants.OKLAHOMA)) {

				Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey(
						"SqlStmts.quickquoteinsuredupdatepopulateLimit", ctx);

				if (obj != null && obj instanceof Map) {

					Map policyMap = (Map) obj;
					int aggregateLimit = policyMap.get("AggregateLimit") != null ? LawyersValidationUtils
							.convertStringToInteger(policyMap.get(
									"AggregateLimit").toString()) : 0;
					int occuranceLimit = policyMap.get("OccuranceLimit") != null ? LawyersValidationUtils
							.convertStringToInteger(policyMap.get(
									"OccuranceLimit").toString()) : 0;

					if (aggregateLimit < 1000000 || occuranceLimit < 1000000) {
						String claimExpensesType = ctx
								.get("IsClaimExpensesType") != null ? ctx.get(
								"IsClaimExpensesType").toString() : "";

						if (claimExpensesType.equals("N")
								|| claimExpensesType.equals(""))
							populateError(
									ctx,
									"LimitSequence",
									"As per your state regulations,only policies issued with a limit of $1,000,000 or higher can include defense expenses as part of the limit.");

					}

				}
			}
		}

	}

	public static boolean validateClaims(Context ctx) throws Exception {
		String strDate = null;
		if (ctx.get("ClaimNotifiedDate") != null
				&& !ctx.get("ClaimNotifiedDate").equals("")) {
			strDate = ctx.get("ClaimNotifiedDate").toString();
			validateLeapYearDate(ctx, "ClaimNotifiedDate", strDate);
		}
		if (ctx.get("DateOfAllegedError") != null
				&& !ctx.get("DateOfAllegedError").equals("")) {
			strDate = ctx.get("DateOfAllegedError").toString();
			validateLeapYearDate(ctx, "DateOfAllegedError", strDate);
		}
		if (ctx.get("DateReportedToInsComp") != null
				&& !ctx.get("DateReportedToInsComp").equals("")) {
			strDate = ctx.get("DateReportedToInsComp").toString();
			validateLeapYearDate(ctx, "DateReportedToInsComp", strDate);
		}
		if (ctx.get("ClaimClosingDate") != null
				&& !ctx.get("ClaimClosingDate").equals("")) {
			strDate = ctx.get("ClaimClosingDate").toString();
			validateLeapYearDate(ctx, "ClaimClosingDate", strDate);
		}
		validateClaimsList(ctx);
		new LawyersUtils().removeAmountFormatForCoverage(ctx);
		return true;
	}

	private void removeAmountFormatForCoverage(IContext ctx) {
		if (ctx.get("InsurerLoss") != null
				&& !ctx.get("InsurerLoss").equals("")) {
			Object InsurerLoss = removeAmountFormat(ctx.get("InsurerLoss"));
			ctx.put("InsurerLoss", InsurerLoss);
		} else {
			ctx.put("InsurerLoss", null);
		}
		if (ctx.get("TotalAmountPaid") != null
				&& !ctx.get("TotalAmountPaid").equals("")) {
			Object TotalAmountPaid = removeAmountFormat(ctx
					.get("TotalAmountPaid"));
			ctx.put("TotalAmountPaid", TotalAmountPaid);
		} else {
			ctx.put("TotalAmountPaid", null);
		}
		if (ctx.get("ClaimantLastDemand") != null
				&& !ctx.get("ClaimantLastDemand").equals("")) {
			Object ClaimantLastDemand = removeAmountFormat(ctx
					.get("ClaimantLastDemand"));
			ctx.put("ClaimantLastDemand", ClaimantLastDemand);
		} else {
			ctx.put("ClaimantLastDemand", null);
		}

	}

	public static boolean validateAop(Context ctx) throws Exception {
		/*
		 * if(ctx.get("IsFirmHaveClientInEntertainmentInd") != null &&
		 * "Y".equals
		 * (ctx.get("IsFirmHaveClientInEntertainmentInd").toString())){ List
		 * list = SqlResources.getSqlMapProcessor(ctx).select(
		 * "SqlStmts.UserStatementcheckAjaxListgetAttorneyInEntertainForFirmLWList"
		 * , ctx); if(list == null || (list != null && list.size() == 0))
		 * populateError(ctx,"attorneyError","Attorneys list is empty, atleast
		 * one record is required"); }
		 * if(ctx.get("IsFirmHaveClientInEntertainmentInd") != null &&
		 * "Y".equals
		 * (ctx.get("IsFirmHaveClientInEntertainmentInd").toString())){ List
		 * list = SqlResources.getSqlMapProcessor(ctx).select(
		 * "SqlStmts.UserStatementcheckAjaxListgetEnterSportsClientOfFirmLWList"
		 * , ctx); if(list == null || (list != null && list.size() == 0))
		 * populateError(ctx,"clientError","Clients list is empty, atleast one
		 * record is required"); }
		 */

		validateAOPPercentage(ctx);
		validateAOPComment(ctx);
		cleanAop(ctx);

		List errorList = new ArrayList();
		errorList = (List) ctx.get(Constants.INET_ERRORS_LIST);

		if (errorList == null) {
			cleanAOPSupplementData(ctx);
		} else if (errorList != null
				&& (errorList.size() <= 0 || errorList.isEmpty())) {
			cleanAOPSupplementData(ctx);
		}

		/*
		 * Removed as per new Requirement Entertainment Supplement Removed
		 * 
		 * if (ctx.get("IsFirmHaveClientInEntertainmentInd") != null) { if
		 * (ctx.get("IsFirmHaveClientInEntertainmentInd").equals("Y")) {
		 * 
		 * List objList = SqlResources .getSqlMapProcessor(ctx) .select(
		 * "AttorneyInEntertainForFirmLW.findAllByPartialKey", ctx); if (objList
		 * != null) { if (objList.size() <= 0) { populateError(ctx,
		 * "attorneyError", "Atleast one record is required for attorney
		 * representing public figures"); } } else { populateError(ctx,
		 * "attorneyError", "Atleast one record is required for attorney
		 * representing public figures"); }
		 * 
		 * List objSportClientList = SqlResources .getSqlMapProcessor(ctx)
		 * .select( "EnterSportsClientOfFirmLW.findAllByPartialKey", ctx); if
		 * (objSportClientList != null) { if (objSportClientList.size() <= 0) {
		 * populateError(ctx, "clientError", "Atleast one record is required for
		 * client representing public figures"); } } else { populateError(ctx,
		 * "clientError", "Atleast one record is required for client
		 * representing public figures"); } } }
		 */

		logger.debug("Po Tr 3---"
				+ ctx.get("TransactionSequence").toString());

		return true;
	}

	public static void cleanAOPSupplementData(IContext ctx) throws Exception {
		/*
		 * // Removing CorporateSupp page data if (ctx.get("AOP_Percentage_5")
		 * != null && ctx.get("AOP_Percentage_5").toString().trim().equals(""))
		 * { ctx.put("AOP_Percentage_5", "0"); } if (ctx.get("AOP_Percentage_5")
		 * == null || (ctx.get("AOP_Percentage_5") != null &&
		 * Integer.parseInt(ctx .get("AOP_Percentage_5").toString()) <= 0)) {
		 * SqlResources.getSqlMapProcessor(ctx).delete(
		 * "SqlStmts.UserStatementremoveAOPdeleteCorpCommBusinessLW", ctx);
		 * ctx.put("IsCorporatePage", "N"); } // Removing EnvironmentalSupp page
		 * data if (ctx.get("AOP_Percentage_10") != null &&
		 * ctx.get("AOP_Percentage_10").toString().trim().equals("")) {
		 * ctx.put("AOP_Percentage_10", "0"); } if (ctx.get("AOP_Percentage_10")
		 * == null || (ctx.get("AOP_Percentage_10") != null && Integer
		 * .parseInt(ctx.get("AOP_Percentage_10").toString()) <= 0)) { //
		 * Removing Environmental Attorney Ajax list
		 * SqlResources.getSqlMapProcessor(ctx).delete(
		 * "SqlStmts.UserStatementremoveAOPdeleteAttorneyEnvWorkLW", ctx); //
		 * Removing Environmental Client Ajax list
		 * SqlResources.getSqlMapProcessor(ctx).delete(
		 * "SqlStmts.UserStatementremoveAOPdeleteFirmEnvClientsLW", ctx); //
		 * Removing Environmental Contractors Ajax list
		 * SqlResources.getSqlMapProcessor(ctx).delete(
		 * "SqlStmts.UserStatementremoveAOPdeleteContractorEnvLWList", ctx); //
		 * Removing Environmental page
		 * SqlResources.getSqlMapProcessor(ctx).delete(
		 * "SqlStmts.UserStatementremoveAOPdeleteEnvironmentalSuppLW", ctx);
		 * ctx.put("IsEnvironmentalPage", "N"); } // Removing FinancialInstSupp
		 * page data if (ctx.get("AOP_Percentage_12") != null &&
		 * ctx.get("AOP_Percentage_12").toString().trim().equals("")) {
		 * ctx.put("AOP_Percentage_12", "0"); } if (ctx.get("AOP_Percentage_12")
		 * == null || (ctx.get("AOP_Percentage_12") != null && Integer
		 * .parseInt(ctx.get("AOP_Percentage_12").toString()) <= 0)) { //
		 * Removing FinancialInst Firm Ajax list SqlResources
		 * .getSqlMapProcessor(ctx) .delete(
		 * "SqlStmts.UserStatementremoveAOPdeleteFinancialInstitutionLW", ctx);
		 * // Removing FinancialInstSupp page
		 * SqlResources.getSqlMapProcessor(ctx).delete(
		 * "SqlStmts.UserStatementremoveAOPdeleteFinancialInstSuppLW", ctx);
		 * ctx.put("IsFinancialInsPage", "N"); }
		 */

		// Removing Plaintiffsupp page data
		if (ctx.get("AOP_Percentage_18") != null
				&& ctx.get("AOP_Percentage_18").toString().trim().equals("")) {
			ctx.put("AOP_Percentage_18", "0");
		}
		if (ctx.get("AOP_Percentage_18") == null
				|| (ctx.get("AOP_Percentage_18") != null && Integer
						.parseInt(ctx.get("AOP_Percentage_18").toString()) <= 0)) {
			// Removing AreaofLitigation MOM list
			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementremoveAOPdeleteAreaOfLitigationForFirmLW",
							ctx);
			// Removing Attorney Ajax list
			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementremoveAOPdeleteAttorneyInPlaintiffForFirmLW",
							ctx);
			// Removing Plaintiffsupp page
			SqlResources.getSqlMapProcessor(ctx).delete(
					"SqlStmts.UserStatementremoveAOPdeletePlantiffSuppLW", ctx);
			ctx.put("IsPlaintiffPage", "N");
		}

		// Removing RealEstateCommercial data
		if (ctx.get("AOP_Percentage_20") != null
				&& ctx.get("AOP_Percentage_20").toString().trim().equals("")) {
			ctx.put("AOP_Percentage_20", "0");
		}

		if (ctx.get("AOP_Percentage_20") == null
				|| (ctx.get("AOP_Percentage_20") != null && Integer
						.parseInt(ctx.get("AOP_Percentage_20").toString()) <= 0)) {

			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementAOPSupplementsQueriescleanRealEstateCommercialData",
							ctx);
			ctx.put("IsRealEstateCommercialPage", "N");

		}

		// Removing RealEstateResidential data
		if (ctx.get("AOP_Percentage_27") != null
				&& ctx.get("AOP_Percentage_27").toString().trim().equals("")) {
			ctx.put("AOP_Percentage_27", "0");
		}

		if (ctx.get("AOP_Percentage_27") == null
				|| (ctx.get("AOP_Percentage_27") != null && Integer
						.parseInt(ctx.get("AOP_Percentage_27").toString()) <= 0)) {

			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementAOPSupplementsQueriescleanRealEstateResidentialData",
							ctx);
			ctx.put("IsRealEstateResidentialPage", "N");

		}

		/*
		 * // Removing RealEstateSupp page if (ctx.get("AOP_Percentage_20") !=
		 * null && ctx.get("AOP_Percentage_20").toString().trim().equals("")) {
		 * ctx.put("AOP_Percentage_20", "0"); } if (ctx.get("AOP_Percentage_20")
		 * == null || (ctx.get("AOP_Percentage_20") != null && Integer
		 * .parseInt(ctx.get("AOP_Percentage_20").toString()) <= 0)) { //
		 * Removing AreaofPracticeRE MOM list SqlResources
		 * .getSqlMapProcessor(ctx) .delete(
		 * "SqlStmts.UserStatementremoveAOPdeleteAreaOfPracticeRealEstateLW",
		 * ctx); // Removing RealEstatesupp page
		 * SqlResources.getSqlMapProcessor(ctx).delete(
		 * "SqlStmts.UserStatementremoveAOPdeleteRealEstateSuppLW", ctx);
		 * ctx.put("IsRealEstatePage", "N"); } // Removing TaxSupp page if
		 * (ctx.get("AOP_Percentage_22") != null &&
		 * ctx.get("AOP_Percentage_22").toString().trim().equals("")) {
		 * ctx.put("AOP_Percentage_22", "0"); } if (ctx.get("AOP_Percentage_22")
		 * == null || (ctx.get("AOP_Percentage_22") != null && Integer
		 * .parseInt(ctx.get("AOP_Percentage_22").toString()) <= 0)) { //
		 * Removing AreaofTaxPractice MOM list
		 * SqlResources.getSqlMapProcessor(ctx).delete(
		 * "SqlStmts.UserStatementremoveAOPdeleteAreaOfTaxPracticeLW", ctx); //
		 * Removing Attorney Ajax list SqlResources .getSqlMapProcessor(ctx)
		 * .delete(
		 * "SqlStmts.UserStatementremoveAOPdeleteAttorneyHaveTaxWorkLW", ctx);
		 * // Removing TaxSupp page SqlResources.getSqlMapProcessor(ctx).delete(
		 * "SqlStmts.UserStatementremoveAOPdeleteTaxSuppLW", ctx);
		 * ctx.put("IsTaxPage", "N"); } // Removing TitleSupp page if
		 * (ctx.get("AOP_Percentage_23") != null &&
		 * ctx.get("AOP_Percentage_23").toString().trim().equals("")) {
		 * ctx.put("AOP_Percentage_23", "0"); } if (ctx.get("AOP_Percentage_23")
		 * == null || (ctx.get("AOP_Percentage_23") != null && Integer
		 * .parseInt(ctx.get("AOP_Percentage_23").toString()) <= 0)) {
		 * SqlResources.getSqlMapProcessor(ctx).delete(
		 * "SqlStmts.UserStatementremoveAOPdeleteTitleSuppLW", ctx);
		 * ctx.put("IsTitlePage", "N"); }
		 */
		// Removing WillsEstateSupp page
		if (ctx.get("AOP_Percentage_24") != null
				&& ctx.get("AOP_Percentage_24").toString().trim().equals("")) {
			ctx.put("AOP_Percentage_24", "0");
		}
		if (ctx.get("AOP_Percentage_24") == null
				|| (ctx.get("AOP_Percentage_24") != null && Integer
						.parseInt(ctx.get("AOP_Percentage_24").toString()) <= 0)) {
			// Removing WillsEstateServicesLW MOM list
			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementremoveAOPdeleteWillsEstateServicesLW",
							ctx);
			// Removing WillsEstateSupp page data
			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementremoveAOPdeleteWillsTrustsEstateSuppLW",
							ctx);
			ctx.put("IsWillsEstatePage", "N");
		}

		// Removing RealEstateResidentialData
		if (ctx.get("AOP_Percentage_27") == null
				|| (ctx.get("AOP_Percentage_27") != null && Integer
						.parseInt(ctx.get("AOP_Percentage_27").toString()) <= 0)) {

			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementAOPSupplementsQueriescleanRealEstateResidentialData",
							ctx);

			ctx.put("IsRealEstateResidentialPage", "N");
		}

		if (ctx.get("AOP_Percentage_20") == null
				|| (ctx.get("AOP_Percentage_20") != null && Integer
						.parseInt(ctx.get("AOP_Percentage_20").toString()) <= 0)) {

			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementAOPSupplementsQueriescleanRealEstateCommercialData",
							ctx);

			ctx.put("IsRealEstateCommercialPage", "N");
		}

	}

	protected static void cleanAop(IContext ctx) throws Exception {
		// Removing Entertainment Attorney Ajax list
		if (ctx.get("IsFirmHaveClientInEntertainmentInd") != null
				&& ctx.get("IsFirmHaveClientInEntertainmentInd").equals("N")) {
			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementcheckAjaxListdeleteAttorneyInEntertainForFirmLWList",
							ctx);
		}
		// Removing Entertainment Client Ajax list
		if (ctx.get("IsFirmHaveClientInEntertainmentInd") != null
				&& ctx.get("IsFirmHaveClientInEntertainmentInd").equals("N")) {
			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementcheckAjaxListdeleteEnterSportsClientOfFirmLWList",
							ctx);
		}
		// Removing EntertainmentSupp page data
		if (ctx.get("IsFirmHaveClientInEntertainmentInd") != null
				&& ctx.get("IsFirmHaveClientInEntertainmentInd").equals("N")) {
			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementremoveAOPdeleteEntertainmentSportSuppLW",
							ctx);

		}
		if ((ctx.get("IsBusinessRelationWithApplEnt") != null && ctx.get(
				"IsBusinessRelationWithApplEnt").equals("N"))
				&& (ctx.get("IsApplSoughtHaveAuthForEntertain") != null && ctx
						.get("IsApplSoughtHaveAuthForEntertain").equals("N"))
				&& (ctx.get("IsApplProvideInvestForEntertain") != null && ctx
						.get("IsApplProvideInvestForEntertain").equals("N"))
				&& (ctx.get("IsApplServedAsTrustee") != null && ctx.get(
						"IsApplServedAsTrustee").equals("N"))
				&& (ctx.get("IsApplNagotiatePersonnelApp") != null && ctx.get(
						"IsApplNagotiatePersonnelApp").equals("N"))) {
			ctx.put("ApplDetailsForCoverageSought", null);
		}
		if (ctx.get("IsApplCovSoughtServeAsManager") != null
				&& ctx.get("IsApplCovSoughtServeAsManager").equals("N")) {
			ctx.put("ApplCovSoughtServeAsManagerDetails", null);
		}
		if (ctx.get("IsApplCoverageAcceptCompensation") != null
				&& ctx.get("IsApplCoverageAcceptCompensation").equals("N")) {
			ctx.put("ApplCoverageAcceptDetails", null);
		}
		if (ctx.get("IsFirmProvidedLegalService") != null
				&& ctx.get("IsFirmProvidedLegalService").equals("N")) {
			ctx.put("IsFirmProvidedLegalServiceDesc", null);
		}

	}

	protected static void cleanCoverage(IContext ctx) throws Exception {
		if (ctx.get("IsPersonnelBeSubOfAnyInvest") != null
				&& ctx.get("IsPersonnelBeSubOfAnyInvest").equals("N")) {
			ctx.put("PersonnelBeSubOfAnyInvestDate", "");
			ctx.put("PersonnelBeSubOfAnyInvestDetails", "");
		}
		if ((ctx.get("IsLawyerProfLiabClaimAgntAppl") != null && ctx.get(
				"IsLawyerProfLiabClaimAgntAppl").equals("N"))
				&& (ctx.get("IsAnyActOmmBecomeClaimAgntFirm") != null && ctx
						.get("IsAnyActOmmBecomeClaimAgntFirm").equals("N"))) {
			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementcheckAjaxListdeleteProfLiabClaimAgnstFirmLWList",
							ctx);
		}
		if (ctx.get("IsAttorneyDeclineForProfLiability") != null
				&& ctx.get("IsAttorneyDeclineForProfLiability").equals("N")) {
			ctx.put("AttorneyDeclineForProfLiabilityDates", "");
			ctx.put("AttorneyDeclineForProfLiabilityReasons", "");
		}
		if (ctx.get("IsFirmHaveLawyersLiabilityInsurance") != null
				&& ctx.get("IsFirmHaveLawyersLiabilityInsurance").equals("N")) {
			ctx.put("InsuranceCompanyNamePross", "");
			ctx.put("LimitSequencePross", "");
			ctx.put("IsClaimExpLiability", "");
			ctx.put("PriorActDatePross", "");
			ctx.put("PolicyExpirationDatePross", "");
			ctx.put("DeductibleSequencePross", "");
			ctx.put("IsPerClaim", "");
			ctx.put("IsProfDefenceExpense", "");
			ctx.put("ProInsPremium", "");
			ctx.put("IsPolicyIncludeLateralHireCov", "");
			ctx.put("IsPolicyExcludesCoverage", "");
			ctx.put("PolicyExcludeCoverageForAffiliatesDesc", "");
			ctx.put("IsPriorActDateFull", "");
			ctx.put("FirmYear", "");
		}
		if (ctx.get("IsPolicyExcludesCoverage") != null
				&& ctx.get("IsPolicyExcludesCoverage").equals("N")) {
			ctx.put("PolicyExcludeCoverageForAffiliatesDesc", "");
		}
		if (ctx.get("IsFirmMergedWithOtherFirm") != null
				&& ctx.get("IsFirmMergedWithOtherFirm").equals("N")) {
			ctx.put("IsApplIntToFinanAssests", "");
			ctx.put("IsFirmCoverageForPreceedorFirms", "");
			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementcheckAjaxListdeleteProdecessorFirmLWList",
							ctx);
		}
		if (ctx.get("IsFirmCoverageForPreceedorFirms") != null
				&& ctx.get("IsFirmCoverageForPreceedorFirms").equals("N")) {
			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementcheckAjaxListdeleteProdecessorFirmLWList",
							ctx);
		}
	}

	protected static void cleanPractice(IContext ctx) throws Exception {
		// RemovingFirm Annual Revenue ajax list
		if (ctx.get("IsFirmHaveClientMoreThan25PercentOfBilling") != null
				&& ctx.get("IsFirmHaveClientMoreThan25PercentOfBilling")
						.equals("N")) {
			ctx.put("PercentFromFirstLargestRevenueClient", "");
			ctx.put("ClientNameFirstLargestRevenueClient", "");
			ctx.put("ServicesRenderedFirstLargestRevenueClient", "");
			ctx.put("DateRenderedFirstLargestRevenueClient", "");

			ctx.put("ServicesRenderedSecondLargestRevenueClient", "");
			ctx.put("ClientNameSecondLargestRevenueClient", "");
			ctx.put("PercentFromSecondLargestRevenueClient", "");
			ctx.put("DateRenderedSecondLargestRevenueClient", "");
		}

		// Removing Personel Served ajax list
		// if ((ctx.get("IsFirmHaveEquityIntGrtThan10") != null &&
		// "N".equals(ctx
		// .get("IsFirmHaveEquityIntGrtThan10").toString()))
		// && (ctx.get("IsFirmPersServedAsOfficerInJointVenture") != null && "N"
		// .equals(ctx.get(
		// "IsFirmPersServedAsOfficerInJointVenture")
		// .toString()))) {
		// SqlResources
		// .getSqlMapProcessor(ctx)
		// .delete(
		// "SqlStmts.UserStatementcheckAjaxListdeleteFirmPersonnelServedLWList",
		// ctx);
		// }

		// Removing FirmLawsuits ajax list
		if (ctx.get("IsApplInitiatedLawsuitForFirm") != null
				&& "N".equals(ctx.get("IsApplInitiatedLawsuitForFirm")
						.toString())) {
			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementcheckAjaxListdeleteFirmInitLawsuitsLWList",
							ctx);
		}
	}

	public static boolean validateCorporate(Context ctx) throws Exception {
		if (ctx.get("CCB_CheckedValue_12") != null
				&& ctx.get("CCB_CheckedValue_12").toString().equals("Y")) {
			if (ctx.get("CCB_CommentDesc_12") == null
					|| (ctx.get("CCB_CommentDesc_12") != null && ctx.get(
							"CCB_CommentDesc_12").equals(""))) {
				populateError(ctx, "CCB_CommentDesc_12",
						"Fill in please describe.");
			}
		} else {
			ctx.put("CCB_CommentDesc_12", null);
		}
		boolean showError = true;
		List ccbTypes = SqlResources.getSqlMapProcessor(ctx).select(
				"CCBLW.findAll", ctx);
		if (ccbTypes != null) {
			for (int i = 0; i < ccbTypes.size(); i++) {
				Map map = (HashMap) ccbTypes.get(i);
				if (ctx.get("CCB_CheckedValue_" + map.get("CCBKey")) != null
						&& ctx.get("CCB_CheckedValue_" + map.get("CCBKey"))
								.equals("Y")) {
					showError = false;
				}
			}
		}
		if (showError) {
			populateError(ctx, "CCBError",
					"Please select any Commercial business practice.");
		}
		return true;
	}

	public static boolean validateEnvironmental(Context ctx) throws Exception {
		validateEnvContractorsList(ctx);
		cleanEnvironmental(ctx);
		return true;
	}

	protected static void cleanEnvironmental(IContext ctx) throws Exception {
		if (ctx.get("IsApplUseIndepContractor") != null
				&& ctx.get("IsApplUseIndepContractor").equals("N")) {
			// ctx.put("ApplicantUsed", "");
			SqlResources.getSqlMapProcessor(ctx).delete(
					"SqlStmts.UserStatementremoveAOPdeleteContractorEnvLWList",
					ctx);
		}
		if (ctx.get("IsApplRecomEnvAudits") != null
				&& ctx.get("IsApplRecomEnvAudits").equals("N")) {
			ctx.put("IsApplRefOfEnvConsultant", "");
			ctx.put("IsCleintRespForEngagementRisks", "");
		}
	}

	public static void validateEnvContractorsList(IContext ctx)
			throws Exception {
		if (ctx.get("IsApplUseIndepContractor") != null
				&& "Y".equals(ctx.get("IsApplUseIndepContractor").toString())) {
			List list = SqlResources
					.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementcheckAjaxListgetContractorEnvLWList",
							ctx);
			if (list == null)
				populateError(ctx, "IsApplUseIndepContractor",
						"Independent Contractors list is empty, atleast one record is required.");
			else if (list != null && list.size() == 0)
				populateError(ctx, "IsApplUseIndepContractor",
						"Independent Contractors list is empty, atleast one record is required.");
		}
		List attorneyEnvWorkLWList = SqlResources.getSqlMapProcessor(ctx)
				.select("AttorneyEnvWorkLW.findAllByPartialKey", ctx);
		if (attorneyEnvWorkLWList == null) {
			populateError(ctx, "environmentworkerror",
					"Attorney list is empty, atleast one record is required.");
		} else if (attorneyEnvWorkLWList != null
				&& attorneyEnvWorkLWList.size() <= 0) {
			populateError(ctx, "environmentworkerror",
					"Attorney list is empty, atleast one record is required.");
		}
		List firmEnvClientsLWList = SqlResources.getSqlMapProcessor(ctx)
				.select("FirmEnvClientsLW.findAllByPartialKey", ctx);
		if (firmEnvClientsLWList == null) {
			populateError(ctx, "environmentclienterror",
					"Attorney list is empty, atleast one record is required.");
		} else if (firmEnvClientsLWList != null
				&& firmEnvClientsLWList.size() <= 0) {
			populateError(ctx, "environmentclienterror",
					"Attorney list is empty, atleast one record is required.");
		}

	}

	public static boolean validateFinancialInst(Context ctx) throws Exception {
		List list = SqlResources
				.getSqlMapProcessor(ctx)
				.select("SqlStmts.financialInstitutionSuppviewgetFinancialInstitutionLWList",
						ctx);
		if (list == null)
			populateError(ctx, "FinancialListError",
					"Financial Institution List is empty, atleast one record is required.");
		else if (list != null && list.size() == 0)
			populateError(ctx, "FinancialListError",
					"Financial Institution List is empty, atleast one record is required.");
		cleanFinancialInst(ctx);
		return true;
	}

	protected static void cleanFinancialInst(IContext ctx) throws Exception {
		if (ctx.get("IsApplServedAsCEOCouncel") != null
				&& ctx.get("IsApplServedAsCEOCouncel").equals("N")) {
			ctx.put("IsFinanInstProvideAggrement", "");
		}
		if (ctx.get("IsApplHadEquityInterest") != null
				&& ctx.get("IsApplHadEquityInterest").equals("N")) {
			ctx.put("InterestOrCommAmounts", "");
		}
	}

	public static boolean validateRealEstateResidential(IContext ctx)
			throws Exception {
		if (ctx.get("AOPRE_Percentage_20") != null
				&& !ctx.get("AOPRE_Percentage_20").toString().equals("")
				&& !ctx.get("AOPRE_Percentage_20").toString().equals("0")) {
			if (ctx.get("AOPRE_CommentDesc_20") == null
					|| (ctx.get("AOPRE_CommentDesc_20") != null && ctx.get(
							"AOPRE_CommentDesc_20").equals(""))) {
				populateError(ctx, "AOPRE_CommentDesc_20",
						"Fill in please describe");
			}
		} else {
			ctx.put("AOPRE_CommentDesc_20", null);
		}
		validateAOPPercentageRealEstateResidential(ctx);
		/*
		 * if (ctx.get("LargestPurchaseForResi") != null) { Object
		 * LargestPurchaseForResi = new LawyersUtils()
		 * .removeAmountFormat(ctx.get("LargestPurchaseForResi"));
		 * ctx.put("LargestPurchaseForResi", LargestPurchaseForResi); } if
		 * (ctx.get("LasrgestPurchaseForComm") != null) { Object
		 * LasrgestPurchaseForComm = new LawyersUtils()
		 * .removeAmountFormat(ctx.get("LasrgestPurchaseForComm"));
		 * ctx.put("LasrgestPurchaseForComm", LasrgestPurchaseForComm); }
		 */
		return true;
	}

	public static void validateAOPPercentageRealEstateResidential(IContext ctx)
			throws Exception {
		int total = 0;
		Map map = new HashMap();
		List limtTypes = SqlResources
				.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementAOPSupplementsQueriesgetAOPREResidentialKeys",
						ctx);
		if (limtTypes != null) {
			for (int i = 0; i < limtTypes.size(); i++) {
				map = (HashMap) limtTypes.get(i);
				String percentageValue = ctx.get("AOPRE_Percentage_"
						+ map.get("AOPREKey")) == null ? null : ctx.get(
						"AOPRE_Percentage_" + map.get("AOPREKey")).toString();
				if (percentageValue == null
						|| "".equals(percentageValue.trim()))
					percentageValue = "0";
				try {
					total = total + Integer.parseInt(percentageValue);
				} catch (Exception e) {
					populateError(ctx, "TotalAOPRE_Percentage",
							"Invalid AOP data.");
				}
			}
		}
		if (total < 100 || total > 100) {
			percentageErrorForRE(ctx, total, "100");
		}
	}

	public static boolean validateRealEstateCommercial(IContext ctx)
			throws Exception {
		if (ctx.get("AOPRE_Percentage_17") != null
				&& !ctx.get("AOPRE_Percentage_17").toString().equals("")
				&& !ctx.get("AOPRE_Percentage_17").toString().equals("0")) {
			if (ctx.get("AOPRE_CommentDesc_17") == null
					|| (ctx.get("AOPRE_CommentDesc_17") != null && ctx.get(
							"AOPRE_CommentDesc_17").equals(""))) {
				populateError(ctx, "AOPRE_CommentDesc_17",
						"Fill in please describe");
			}
		} else {
			ctx.put("AOPRE_CommentDesc_17", null);
		}
		validateAOPPercentageRealEstateCommercial(ctx);

		return true;
	}

	public static void validateAOPPercentageRealEstateCommercial(IContext ctx)
			throws Exception {
		int total = 0;
		Map map = new HashMap();
		List limtTypes = SqlResources
				.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementAOPSupplementsQueriesgetAOPRECommercialKeys",
						ctx);
		if (limtTypes != null) {
			for (int i = 0; i < limtTypes.size(); i++) {
				map = (HashMap) limtTypes.get(i);
				String percentageValue = ctx.get("AOPRE_Percentage_"
						+ map.get("AOPREKey")) == null ? null : ctx.get(
						"AOPRE_Percentage_" + map.get("AOPREKey")).toString();
				if (percentageValue == null
						|| "".equals(percentageValue.trim()))
					percentageValue = "0";
				try {
					total = total + Integer.parseInt(percentageValue);
				} catch (Exception e) {
					populateError(ctx, "TotalAOPRE_Percentage",
							"Invalid AOP data.");
				}
			}
		}
		if (total < 100 || total > 100) {
			percentageErrorForRE(ctx, total, "100");
		}
	}

	protected static void percentageErrorForRE(IContext ctx, int total,
			String percentage) throws Exception {
		populateError(ctx, "TotalAOPRE_Percentage",
				"The total AOP percentage is required to equal 100%, current total is "
						+ total + "%");
	}

	public static boolean validateTax(Context ctx) throws Exception {
		if (ctx.get("AOTP_Percentage_9") != null
				&& !ctx.get("AOTP_Percentage_9").toString().equals("")
				&& !ctx.get("AOTP_Percentage_9").toString().equals("0")) {
			if (ctx.get("AOTP_CommentDesc_9") == null
					|| (ctx.get("AOTP_CommentDesc_9") != null && ctx.get(
							"AOTP_CommentDesc_9").equals(""))) {
				populateError(ctx, "AOTP_CommentDesc_9",
						"Fill in please describe");
			}
		} else {
			ctx.put("AOTP_CommentDesc_9", null);
		}

		List workList = SqlResources.getSqlMapProcessor(ctx).select(
				"AttorneyHaveTaxWorkLW.findAllByPartialKey", ctx);
		if (workList != null) {
			if (workList.size() <= 0) {
				populateError(ctx, "attorneyerror2",
						"Atleast one attorney is required.");
			}
		}
		validateAOPPercentageTax(ctx);
		return true;
	}

	public static void validateAOPPercentageTax(IContext ctx) throws Exception {
		int total = 0;
		Map map = new HashMap();
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select(
				"AOTPLW.findAll", ctx);
		if (limtTypes != null) {
			for (int i = 0; i < limtTypes.size(); i++) {
				map = (HashMap) limtTypes.get(i);
				String percentageValue = ctx.get("AOTP_Percentage_"
						+ map.get("AOTPKey").toString()) == null ? null
						: ctx.get(
								"AOTP_Percentage_"
										+ map.get("AOTPKey").toString())
								.toString();
				if (percentageValue == null
						|| "".equals(percentageValue.trim()))
					percentageValue = "0";
				try {
					total = total + Integer.parseInt(percentageValue);
				} catch (Exception e) {
					populateError(ctx, "TotalAOTP_Percentage",
							"Invalid AOP data");
				}
			}
		}
		if (total < 100 || total > 100) {
			percentageErrorForTax(ctx, total, "100");
		}
	}

	protected static void percentageErrorForTax(IContext ctx, int total,
			String percentage) throws Exception {
		populateError(ctx, "TotalAOTP_Percentage",
				"The total AOP percentage is required to equal 100%, current total is "
						+ total + "%");
	}

	public static boolean validateTitle(Context ctx) throws Exception {
		if (ctx.get("PercentOfTitleFromOther") != null
				&& !ctx.get("PercentOfTitleFromOther").toString().equals("")
				&& !ctx.get("PercentOfTitleFromOther").toString().equals("0")) {
			if (ctx.get("PercentOfTitleFromOther_CommentDesc") == null
					|| (ctx.get("PercentOfTitleFromOther_CommentDesc") != null && ctx
							.get("PercentOfTitleFromOther_CommentDesc").equals(
									""))) {
				populateError(ctx, "PercentOfTitleFromOther_CommentDesc",
						"Fill in please describe");
			}
		} else {
			ctx.put("PercentOfTitleFromOther_CommentDesc", null);
		}

		if (ctx.get("PercentOfTitleFromResi") == null
				&& ctx.get("PercentOfTitleFromComm") == null
				&& ctx.get("PercentOfTitleFromAgri") == null
				&& ctx.get("PercentOfTitleFromOther") == null)
			populateError(ctx, "PercentOfTitleAllValidate",
					"Please provide one");

		if ((ctx.get("PercentOfTitleFromResi") != null
				&& ctx.get("PercentOfTitleFromResi").toString().equals("") || ctx
				.get("PercentOfTitleFromResi").toString().equals("0"))
				&& (ctx.get("PercentOfTitleFromOther") != null
						&& ctx.get("PercentOfTitleFromOther").toString()
								.equals("") || ctx
						.get("PercentOfTitleFromOther").toString().equals("0"))
				&& (ctx.get("PercentOfTitleFromComm") != null
						&& ctx.get("PercentOfTitleFromComm").toString()
								.equals("") || ctx
						.get("PercentOfTitleFromComm").toString().equals("0"))
				&& (ctx.get("PercentOfTitleFromAgri") != null
						&& ctx.get("PercentOfTitleFromAgri").toString()
								.equals("") || ctx
						.get("PercentOfTitleFromAgri").toString().equals("0")))
			populateError(ctx, "PercentOfTitleAllValidate",
					"Please provide approximate percentage breakdown.");
		cleanTitle(ctx);
		return true;
	}

	protected static void cleanTitle(IContext ctx) throws Exception {
		if (ctx.get("IsApplMaintEquityInTitle") != null
				&& ctx.get("IsApplMaintEquityInTitle").equals("N")) {
			ctx.put("PercentageOfEquityInt", "");
		}
		if (ctx.get("IsTitleHaveProfLiabCoverage") != null
				&& ctx.get("IsTitleHaveProfLiabCoverage").equals("Y")) {
			ctx.put("IsApplCovProfLiabPolicy", "");
		}
		if (ctx.get("IsTitleInsDeclineToAppl") != null
				&& ctx.get("IsTitleInsDeclineToAppl").equals("N")) {
			ctx.put("NameAndReasonOfTitle", "");
		}
	}

	public static boolean validatePlantiff(Context ctx) throws Exception {
		if (ctx.get("AOL_PercentageOfRevenue_13") != null
				&& !ctx.get("AOL_PercentageOfRevenue_13").toString().equals("")
				&& !ctx.get("AOL_PercentageOfRevenue_13").toString()
						.equals("0")) {
			if (ctx.get("AOL_CommentDesc_13") == null
					|| (ctx.get("AOL_CommentDesc_13") != null && ctx.get(
							"AOL_CommentDesc_13").equals(""))) {
				populateError(ctx, "AOL_CommentDesc_13",
						"Fill in please describe");
			}
		} else {
			ctx.put("AOL_CommentDesc_13", null);
		}
		List plantiffAttorneyList = SqlResources
				.getSqlMapProcessor(ctx)
				.select("AttorneyInPlaintiffForFirmLW.findAllByPartialKey", ctx);
		if (plantiffAttorneyList == null) {
			populateError(ctx, "plantiffworkerror",
					"Atleast one attorney is required.");
		} else if (plantiffAttorneyList != null) {
			if (plantiffAttorneyList.size() <= 0) {
				populateError(ctx, "plantiffworkerror",
						"Atleast one attorney is required.");
			}
		}
		validateAOLPercentagePlantiff(ctx);
		cleanPlantiff(ctx);

		return true;
	}

	public static void validatePercentageFieldPractise(IContext ctx)
			throws Exception {

		if (ctx.get("IsFirmHaveClientMoreThan25PercentOfBilling") != null
				&& "Y".equals(ctx.get(
						"IsFirmHaveClientMoreThan25PercentOfBilling")
						.toString())) {

			if (ctx.get("PercentFromFirstLargestRevenueClient") != null
					&& !"".equals(ctx.get(
							"PercentFromFirstLargestRevenueClient").toString())) {

				int percent = Integer.parseInt(ctx.get(
						"PercentFromFirstLargestRevenueClient").toString());

				if (percent > 100)
					populateError(ctx, "PercentFromFirstLargestRevenueClient",
							"Percentage should not be greater than 100%");

			}

			if (ctx.get("PercentFromSecondLargestRevenueClient") != null
					&& !"".equals(ctx.get(
							"PercentFromSecondLargestRevenueClient").toString())) {

				int percent = Integer.parseInt(ctx.get(
						"PercentFromSecondLargestRevenueClient").toString());

				if (percent > 100)
					populateError(ctx, "PercentFromSecondLargestRevenueClient",
							"Percentage should not be greater than 100%");

			}

		}

	}

	public static boolean validatePlantiffOnSkip(IContext ctx) throws Exception {

		if (ctx.get("AOL_PercentageOfRevenue_13") != null
				&& !ctx.get("AOL_PercentageOfRevenue_13").toString().equals("")
				&& !ctx.get("AOL_PercentageOfRevenue_13").toString()
						.equals("0")) {
			if (ctx.get("AOL_CommentDesc_13") == null
					|| (ctx.get("AOL_CommentDesc_13") != null && ctx.get(
							"AOL_CommentDesc_13").equals(""))) {
				populateError(ctx, "AOL_CommentDesc_13",
						"Fill in please describe");
			}
		} else {
			ctx.put("AOL_CommentDesc_13", null);
		}
		validateAOLPercentagePlantiffSkip(ctx);
		cleanPlantiff(ctx);

		return true;
	}

	public static void validateAOLPercentagePlantiffSkip(IContext ctx)
			throws Exception {
		int total = 0;
		Map map = new HashMap();
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select(
				"AOLLW.findAll", ctx);
		if (limtTypes != null) {
			for (int i = 0; i < limtTypes.size(); i++) {
				map = (HashMap) limtTypes.get(i);
				String percentageValue = ctx.get("AOL_PercentageOfRevenue_"
						+ map.get("AOLKey").toString()) == null ? null : ctx
						.get("AOL_PercentageOfRevenue_"
								+ map.get("AOLKey").toString()).toString();
				if (percentageValue == null
						|| "".equals(percentageValue.trim())) {
					percentageValue = "0";
				}
				try {
					total = total + Integer.parseInt(percentageValue);
				} catch (Exception e) {
					populateError(ctx, "TotalAOL_PercentageOfRevenue",
							"Invalid AOP data");
				}
				if (ctx.get("AOL_AverageCaseSize_"
						+ map.get("AOLKey").toString()) != null
						&& !ctx.get(
								"AOL_AverageCaseSize_"
										+ map.get("AOLKey").toString()).equals(
								"")) {
					Object averageCaseSize = new LawyersUtils()
							.removeAmountFormat(ctx.get("AOL_AverageCaseSize_"
									+ map.get("AOLKey").toString()));
					ctx.put("AOL_AverageCaseSize_"
							+ map.get("AOLKey").toString(), averageCaseSize);
				}
				if (ctx.get("AOL_LargestCaseSize_"
						+ map.get("AOLKey").toString()) != null
						&& !ctx.get(
								"AOL_LargestCaseSize_"
										+ map.get("AOLKey").toString()).equals(
								"")) {
					Object largestCaseSize = new LawyersUtils()
							.removeAmountFormat(ctx.get("AOL_LargestCaseSize_"
									+ map.get("AOLKey").toString()));
					ctx.put("AOL_LargestCaseSize_"
							+ map.get("AOLKey").toString(), largestCaseSize);
				}
			}
		}
		// if (total < 100 || total > 100) {
		// percentageErrorAOL(ctx, total, "100");
		// }
	}

	public static void validateAOLPercentagePlantiff(IContext ctx)
			throws Exception 
	{
		int total = 0;
		Map map = new HashMap();
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select(
				"AOLLW.findAll", ctx);
		if (limtTypes != null) {
			for (int i = 0; i < limtTypes.size(); i++) {
				map = (HashMap) limtTypes.get(i);
				String percentageValue = ctx.get("AOL_PercentageOfRevenue_"
						+ map.get("AOLKey").toString()) == null ? null : ctx
						.get("AOL_PercentageOfRevenue_"
								+ map.get("AOLKey").toString()).toString();
				if (percentageValue == null
						|| "".equals(percentageValue.trim())) {
					percentageValue = "0";
				}
				try {
					total = total + Integer.parseInt(percentageValue);
				} catch (Exception e) {
					populateError(ctx, "TotalAOL_PercentageOfRevenue",
							"Invalid AOP data");
				}
				if (ctx.get("AOL_AverageCaseSize_"
						+ map.get("AOLKey").toString()) != null
						&& !ctx.get(
								"AOL_AverageCaseSize_"
										+ map.get("AOLKey").toString()).equals(
								"")) {
					Object averageCaseSize = new LawyersUtils()
							.removeAmountFormat(ctx.get("AOL_AverageCaseSize_"
									+ map.get("AOLKey").toString()));
					ctx.put("AOL_AverageCaseSize_"
							+ map.get("AOLKey").toString(), averageCaseSize);
				}
				if (ctx.get("AOL_LargestCaseSize_"
						+ map.get("AOLKey").toString()) != null
						&& !ctx.get(
								"AOL_LargestCaseSize_"
										+ map.get("AOLKey").toString()).equals(
								"")) {
					Object largestCaseSize = new LawyersUtils()
							.removeAmountFormat(ctx.get("AOL_LargestCaseSize_"
									+ map.get("AOLKey").toString()));
					ctx.put("AOL_LargestCaseSize_"
							+ map.get("AOLKey").toString(), largestCaseSize);
				}
			}
		}
		if (total < 100 || total > 100) {
			percentageErrorAOL(ctx, total, "100");
		}
	}

	protected static void percentageErrorAOL(IContext ctx, int total,
			String percentage) throws Exception {
		populateError(ctx, "TotalAOL_PercentageOfRevenue",
				"The total AOP percentage is required to equal 100%, current total is "
						+ total + "%");
	}

	protected static void cleanPlantiff(IContext ctx) throws Exception {
		if (ctx.get("IsAppAcceptRefersFromOtherFirms") != null
				&& ctx.get("IsAppAcceptRefersFromOtherFirms").equals("N")) {
			ctx.put("IsRefAggrementReferToAppl", "");

		}

		if (ctx.get("IsAppReferToOtherLawFirms") != null
				&& ctx.get("IsAppReferToOtherLawFirms").equals("N")) {

			ctx.put("IsRefAggrementReferToApplRefersOut", "");
			ctx.put("IsAppConfirmCarryProfLiabIns", "");
		}

		if (ctx.get("IsApplAcceptCasesToStatuteOfLim") != null
				&& ctx.get("IsApplAcceptCasesToStatuteOfLim").equals("N")) {

			ctx.put("IsApplAcceptCasesToStatuteOfLimDesc", "");

		}

	}

	public static boolean validateWillsEstate(Context ctx) throws Exception {
		if (ctx.get("WES_CheckedValue_12") != null
				&& ctx.get("WES_CheckedValue_12").toString().equals("Y")) {
			if (ctx.get("WES_CommentDesc_12") == null
					|| (ctx.get("WES_CommentDesc_12") != null && ctx.get(
							"WES_CommentDesc_12").equals(""))) {
				populateError(ctx, "WES_CommentDesc_12",
						"Fill in please describe");
			}
		} else {
			ctx.put("WES_CommentDesc_12", null);
		}
		cleanWillsEstate(ctx);
		return true;
	}

	protected static void cleanWillsEstate(IContext ctx) throws Exception {
		if (ctx.get("IsAttorneyServeAsExecutorTrustee") != null
				&& ctx.get("IsAttorneyServeAsExecutorTrustee").equals("N")) {
			ctx.put("ListOfAttWithNameServicesClient", "");
		}
		if (ctx.get("IsApplTaxInConjucWithTrustWork") != null
				&& ctx.get("IsApplTaxInConjucWithTrustWork").equals("N")) {
			ctx.put("IsApplObtainCertificate", "");
		}

		if (ctx.get("IsApplHaveControlOverFunds") != null
				&& ctx.get("IsApplHaveControlOverFunds").equals("N")) {
			ctx.put("IsCounterSignatureRequired", "");
			ctx.put("IsApplAuthorizeToWithdrawFromBank", "");
			ctx.put("IsApplCarryEmployeeDishonestyCoverage", "");
		}

	}

	public static void validateFirmPrimaryLocationList(IContext ctx)
			throws Exception {
		if (ctx.get("IsFirmPracticeInOtherState") != null
				&& "Y".equals(ctx.get("IsFirmPracticeInOtherState").toString())) {
			List list = SqlResources
					.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementcheckAjaxListgetFirmPracticeLocationLWList",
							ctx);
			if (list == null)
				populateError(
						ctx,
						"PrimaryLocationListError",
						"Applicant's additional location list is empty, at least one record is required.");
			else if (list != null && list.size() == 0)
				populateError(
						ctx,
						"PrimaryLocationListError",
						"Applicant's additional location list is empty, at least one record is required.");
		}
	}

	public static void validateFirmAnnualRevenueList(IContext ctx)
			throws Exception {
		if (ctx.get("IsFirmHaveClientMoreThan25PercentOfBilling") != null
				&& "Y".equals(ctx.get(
						"IsFirmHaveClientMoreThan25PercentOfBilling")
						.toString())) {
			List list = SqlResources
					.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementcheckAjaxListgetFirmAnnualRevenueLWList",
							ctx);
			if (list == null)
				populateError(ctx,
						"IsFirmHaveClientMoreThan25PercentOfBilling",
						"Information regarding largest client is empty, please provide.");
			else if (list != null && list.size() == 0)
				populateError(ctx,
						"IsFirmHaveClientMoreThan25PercentOfBilling",
						"Information regarding largest client is empty, please provide.");
		}
	}

	public static void validatePredessorFirmsList(IContext ctx)
			throws Exception {
		if (ctx.get("IsFirmCoverageForPreceedorFirms") != null
				&& "Y".equals(ctx.get("IsFirmCoverageForPreceedorFirms")
						.toString())) {
			if (ctx.get("IsFirmMergedWithOtherFirm") != null
					&& "Y".equals(ctx.get("IsFirmMergedWithOtherFirm")
							.toString())) {
				List list = SqlResources
						.getSqlMapProcessor(ctx)
						.select("SqlStmts.UserStatementcheckAjaxListgetProdecessorFirmLWList",
								ctx);
				if (list == null)
					populateError(ctx, "IsFirmCoverageForPreceedorFirms",
							"Firm Predecessor information is empty, please provide.");
				else if (list != null && list.size() == 0)
					populateError(ctx, "IsFirmCoverageForPreceedorFirms",
							"Firm Predecessor information is empty, please provide.");
			}
		}/*
		 * else if(ctx.get("IsFirmMergedWithOtherFirm") != null &&
		 * "N".equals(ctx.get("IsFirmMergedWithOtherFirm").toString())){
		 * SqlResources.getSqlMapProcessor(ctx).delete(
		 * "SqlStmts.UserStatementcheckAjaxListdeleteProdecessorFirmLWList",
		 * ctx); }
		 */
	}

	public static void validateClaimsList(IContext ctx) throws Exception {
		if ((ctx.get("IsLawyerProfLiabClaimAgntAppl") != null && ctx.get(
				"IsLawyerProfLiabClaimAgntAppl").equals("Y"))
				|| (ctx.get("IsAnyActOmmBecomeClaimAgntFirm") != null && ctx
						.get("IsAnyActOmmBecomeClaimAgntFirm").equals("Y"))) {
			List list = SqlResources
					.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementcheckAjaxListgetProfLiabClaimAgnstFirmLWList",
							ctx);
			if (list == null)
				populateError(ctx, "IsAnyActOmmBecomeClaimAgntFirm",
						"Claims or Incident information is empty please provide.");
			else if (list != null && list.size() == 0)
				populateError(ctx, "IsAnyActOmmBecomeClaimAgntFirm",
						"Claims or Incident information is empty please provide.");
		}
	}

	public static void validatePersonnelServedList(IContext ctx)
			throws Exception {
		if ((ctx.get("IsFirmHaveEquityIntGrtThan10") != null && "Y".equals(ctx
				.get("IsFirmHaveEquityIntGrtThan10").toString()))
				|| (ctx.get("IsFirmPersServedAsOfficerInJointVenture") != null && "Y"
						.equals(ctx.get(
								"IsFirmPersServedAsOfficerInJointVenture")
								.toString()))) {
			List list = SqlResources
					.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementcheckAjaxListgetFirmPersonnelServedLWList",
							ctx);
			if (list == null)
				populateError(
						ctx,
						"IsFirmPersServedAsOfficerInJointVenture",
						"Information regarding director, officer, partner, manager activity is empty, at least one record is required.");
			else if (list != null && list.size() == 0)
				populateError(
						ctx,
						"IsFirmPersServedAsOfficerInJointVenture",
						"Information regarding director, officer, partner, manager activity is empty, at least one record is required.");
		}/*
		 * else if((ctx.get("IsFirmHaveEquityIntGrtThan10") != null &&
		 * "N".equals(ctx.get("IsFirmHaveEquityIntGrtThan10").toString())) &&
		 * (ctx.get("IsFirmPersServedAsOfficerInJointVenture") != null &&
		 * "N".equals
		 * (ctx.get("IsFirmPersServedAsOfficerInJointVenture").toString()))){
		 * SqlResources.getSqlMapProcessor(ctx).delete(
		 * "SqlStmts.UserStatementcheckAjaxListdeleteFirmPersonnelServedLWList",
		 * ctx); }
		 */
	}

	public static void validateApplLawSuits(IContext ctx) throws Exception {
		if (ctx.get("AmountOfFeesSued") != null
				&& !ctx.get("AmountOfFeesSued").toString().equals("")) {
			Object AmountOfFeesSued = removeAmountFormat(ctx
					.get("AmountOfFeesSued"));
			ctx.put("AmountOfFeesSued", AmountOfFeesSued);
		} else {
			ctx.put("AmountOfFeesSued", null);
		}
		if (ctx.get("IsApplInitiatedLawsuitForFirm") != null
				&& "Y".equals(ctx.get("IsApplInitiatedLawsuitForFirm")
						.toString())) {
			List list = SqlResources
					.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementcheckAjaxListgetFirmInitLawsuitsLWList",
							ctx);
			if (list == null)
				populateError(ctx, "IsApplInitiatedLawsuitForFirm",
						"Fee Suit information is empty, atleast one record is required.");
			else if (list != null && list.size() == 0)
				populateError(ctx, "IsApplInitiatedLawsuitForFirm",
						"Fee Suit information is empty, atleast one record is required.");
		}/*
		 * else if(ctx.get("IsApplInitiatedLawsuitForFirm") != null &&
		 * "N".equals(ctx.get("IsApplInitiatedLawsuitForFirm").toString())){
		 * SqlResources.getSqlMapProcessor(ctx).delete(
		 * "SqlStmts.UserStatementcheckAjaxListdeleteFirmInitLawsuitsLWList",
		 * ctx); }
		 */
	}

	public static void validateProfLiabAgnstFirm(IContext ctx) throws Exception {
		if ((ctx.get("IsLawyerProfLiabClaimAgntAppl") != null && "N".equals(ctx
				.get("IsLawyerProfLiabClaimAgntAppl").toString()))
				&& (ctx.get("IsAnyActOmmBecomeClaimAgntFirm") != null && "N"
						.equals(ctx.get("IsAnyActOmmBecomeClaimAgntFirm")
								.toString()))) {
			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementcheckAjaxListdeleteProfLiabClaimAgnstFirmLWList",
							ctx);
			ctx.put("NameOfClaimant", "");
			ctx.put("IsClient", "");
			ctx.put("ClaimNotifiedDate", "");
			ctx.put("DateOfAllegedError", "");
			ctx.put("NameOfPersonnelINClaim", "");
			ctx.put("DescOfClaim", "");
			ctx.put("IsClaimReportedToInsComp", "");
			ctx.put("NameOfInsComp", "");
			ctx.put("DateReportedToInsComp", "");
			ctx.put("InsurerLoss", "");
			ctx.put("ClaimantLastDemand", "");
			ctx.put("CurrentStatus", "");
			ctx.put("ClaimClosingDate", "");
			ctx.put("TotalAmountPaid", "");
			ctx.put("StepsTakenToPreventClaims", "");
		}
	}

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
					&& (ctx.get("YearEndDate_0") != null && "".equals(ctx
							.get("YearEndDate_0").toString().trim()))) {
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
			} else if (ctx.get("Amount_0") != null
					&& ".".equals(ctx.get("Amount_0").toString().trim())) {
				populateError(ctx, "estimate_year",
						"Amount should be in correct format");
				flag = true;
			}

			if (ctx.get("Amount_1") != null
					&& ".".equals(ctx.get("Amount_1").toString().trim())) {
				populateError(ctx, "estimate_year",
						"Amount should be in correct format");
				flag = true;
			}
		}

		return flag;
	}

	public static Object returnValue(Object object) {
		return object;
	}

	public static String getEmailID(IContext ctx) throws Exception {
		String emailID = "";
		String productionEnv = "N";
		try {
			productionEnv = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production");
		} catch (Exception e) {
			logger.error("error in geeting production environment");
		}
		
		if ("N".equals(productionEnv)){
			emailID = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.email");
		} else { 
			if (ctx.get("AccountEmail") != null)
			emailID = ctx.get("AccountEmail").toString();
			try{
				Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetFirmAddressDetails", ctx);
				if(obj != null && obj instanceof Map)
				{
					ctx.putAll((Map)obj);
				}
			} catch(Exception e) {
				logger.error("Error occured while fetching secondary email address of firm :"+e);
			}
			
			if(ctx.get("secondaryEmail") != null)
				emailID=emailID+","+ctx.get("secondaryEmail").toString();
		}
		return emailID;
	}

	public void checkBusiness(IContext ctx) throws Exception {

		if (ctx.get("StateCode") != null
				&& !"".equals(ctx.get("StateCode").toString())) {
			Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey(
					"State.findByKey", ctx);
			if (obj != null && obj instanceof Map) {
				Map map = (Map) obj;
				ctx.put("IsBusinessActiveLW", map.get("IsBusinessActiveLW"));
			}
		}
	}

	public static void validateLeapYearDate(IContext ctx, String field,
			String strDate) throws Exception {
		try {
			if (strDate != null && !"".equals(strDate)) {
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
					populateError(ctx, field, "Invalid date format.");
				} else if (curMonth <= 0 || curDay <= 0 || curYear <= 0) {
					populateError(ctx, field, "Invalid date format.");
				} else if (isLeapYear(curYear)) {
					if (curMonth == 2 && curDay > 29) {
						populateError(ctx, field, "Invalid date.");
					} else if (curMonth != 2 && curDay > 31) {
						populateError(ctx, field, "Invalid date.");
					} else if (curMonth > 13) {
						populateError(ctx, field, "Invalid date.");
					} else if ((curMonth == 4 || curMonth == 6 || curMonth == 9 || curMonth == 11)
							&& curDay > 30) {
						populateError(ctx, field, "Invalid date.");
					} else if (curYear < 1800) {
						populateError(ctx, field, "Invalid date.");
					}
				} else if (curMonth == 2 && curDay > 28) {
					populateError(ctx, field, "Invalid date.");
				} else if (curMonth != 2 && curDay > 31) {
					populateError(ctx, field, "Invalid date.");
				} else if (curMonth > 13) {
					populateError(ctx, field, "Invalid date.");
				} else if ((curMonth == 4 || curMonth == 6 || curMonth == 9 || curMonth == 11)
						&& curDay > 30) {
					populateError(ctx, field, "Invalid date.");
				} else if (curYear < 1800) {
					populateError(ctx, field, "Invalid date.");
				}
			}
		} catch (Exception e) {
			populateError(ctx, field, "Invalid date format.");
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

	public static boolean validatePhoneNumber(String phoneNumber)
			throws Exception {
		if (phoneNumber == null)
			return false;
		phoneNumber = phoneNumber.replace("(", "").replace(")", "")
				.replace("-", "");
		if (phoneNumber.length() < 10)
			return false;
		return true;
	}

	public static boolean validateFirmAttorneyList(Context ctx)
			throws Exception {
		String strDate = null;
		if(ctx.get("validate") != null && "Y".equals(ctx.get("validate").toString())){
			if(ctx.get("AttorneyPriorActDate") == null){
				populateError(ctx, "AttorneyPriorActDate", "Date joined the firm is a required field");
			}else if(ctx.get("AttorneyPriorActDate") != null && "".equals(ctx.get("AttorneyPriorActDate").toString())){
				populateError(ctx, "AttorneyPriorActDate", "Date joined the firm is a required field");
			}
			
			
			if (ctx.get("AttorneyPriorActDate") != null
					&& !ctx.get("AttorneyPriorActDate").equals("")) {
				strDate = ctx.get("AttorneyPriorActDate").toString();
				validateLeapYearDate(ctx, "AttorneyPriorActDate", strDate);
			}
		}
		return true;
	}

	public static boolean validateEntertainClientList(Context ctx)
			throws Exception {
		String strDate = null;

		if (ctx.get("FromDate") != null && !ctx.get("FromDate").equals("")) {
			strDate = ctx.get("FromDate").toString();
			validateLeapYearDate(ctx, "FromDate", strDate);
		}
		if (ctx.get("ToDate") != null && !ctx.get("ToDate").equals("")) {
			strDate = ctx.get("ToDate").toString();
			validateLeapYearDate(ctx, "ToDate", strDate);
		}

		if ((ctx.get("FromDate") != null && !ctx.get("FromDate").equals(""))
				&& (ctx.get("ToDate") != null && !ctx.get("ToDate").equals(""))) {
			Date fromDate = com.manage.managemetadata.functions.commonfunctions.DateUtils
					.convertStringToDate(ctx.get("FromDate").toString(),
							"MM/dd/yyyy");
			Date toDate = com.manage.managemetadata.functions.commonfunctions.DateUtils
					.convertStringToDate(ctx.get("ToDate").toString(),
							"MM/dd/yyyy");

			long daysDiff = com.manage.managemetadata.functions.commonfunctions.DateUtils
					.calculateDiffInDays(toDate, fromDate);
			if (daysDiff < 0) {
				populateError(ctx, "ToDate",
						"To Date of Service should be after the From Date of Service");
			}

		}

		return true;
	}

	public static boolean validateFinancialInstList(Context ctx)
			throws Exception {
		String strDate = null;
		if (ctx.get("DateInitiated") != null
				&& !ctx.get("DateInitiated").equals("")) {
			strDate = ctx.get("DateInitiated").toString();
			validateLeapYearDate(ctx, "DateInitiated", strDate);
		}
		if (ctx.get("DateEnded") != null && !ctx.get("DateEnded").equals("")) {
			strDate = ctx.get("DateEnded").toString();
			validateLeapYearDate(ctx, "DateEnded", strDate);
		}
		if (ctx.get("DateOfInsolvency") != null
				&& !ctx.get("DateOfInsolvency").equals("")) {
			strDate = ctx.get("DateOfInsolvency").toString();
			validateLeapYearDate(ctx, "DateOfInsolvency", strDate);
		}
		return true;
	}

	public static boolean validateFeesSuedList(Context ctx) throws Exception {
		if (ctx.get("AmountOfFeesSued") != null
				&& !ctx.get("AmountOfFeesSued").toString().equals("")) {
			Object AmountOfFeesSued = removeAmountFormat(ctx
					.get("AmountOfFeesSued"));
			ctx.put("AmountOfFeesSued", AmountOfFeesSued);
		} else {
			ctx.put("AmountOfFeesSued", null);
		}
		String strDate = null;
		if (ctx.get("DueDateFees") != null
				&& !ctx.get("DueDateFees").equals("")) {
			strDate = ctx.get("DueDateFees").toString();
			validateLeapYearDate(ctx, "DueDateFees", strDate);
		}
		if (ctx.get("SuitFilesDateFees") != null
				&& !ctx.get("SuitFilesDateFees").equals("")) {
			strDate = ctx.get("SuitFilesDateFees").toString();
			validateLeapYearDate(ctx, "SuitFilesDateFees", strDate);
		}
		return true;
	}

	public static boolean validateProdecessorERPList(Context ctx)
			throws Exception {
		String strDate = null;
		if (ctx.get("ERPExpDate") != null && !ctx.get("ERPExpDate").equals("")) {
			strDate = ctx.get("ERPExpDate").toString();
			validateLeapYearDate(ctx, "ERPExpDate", strDate);
		}
		if (ctx.get("DateOfAcquisation") != null
				&& !ctx.get("DateOfAcquisation").equals("")) {
			strDate = ctx.get("DateOfAcquisation").toString();
			validateLeapYearDate(ctx, "DateOfAcquisation", strDate);
		}
		return true;
	}

	public static boolean validateFirmAnnualRevenue(Context ctx)
			throws Exception {
		if (ctx.get("PercentageOfAnnlRevenue") != null
				&& !ctx.get("PercentageOfAnnlRevenue").equals("")) {
			if (Integer.parseInt(ctx.get("PercentageOfAnnlRevenue").toString()) < 25) {
				populateError(ctx, "PercentageOfAnnlRevenue",
						"Percentage of revenue should be equal or greater than 25%.");
			}
		}
		return true;
	}

	public static boolean validateQuoteDate(Context ctx) throws Exception {

		if (ctx.get("StatusKey") != null
				&& "".equals(ctx.get("StatusKey").toString())) {
			populateError(ctx, "StatusKey", "Status is required");

		} else if (ctx.get("StatusKey") == null) {
			populateError(ctx, "StatusKey", "Status is required");
		} else {

			String strDate = null;
			if (ctx.get("QuoteEffectiveDate") != null
					&& !ctx.get("QuoteEffectiveDate").equals("")) {
				strDate = ctx.get("QuoteEffectiveDate").toString();
				validateLeapYearDate(ctx, "QuoteEffectiveDate", strDate);
			}
			Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey(
					"SqlStmts.status_freeformviewgetOldPolicyStatus", ctx);
			Map map = new HashMap();
			if (obj != null && obj instanceof Map) {
				map = (HashMap) obj;
				String oldKey = map.get("OldStatusKey") == null ? "" : map.get(
						"OldStatusKey").toString();
				String newKey = ctx.get("StatusKey") == null ? "" : ctx.get(
						"StatusKey").toString();
				if ((oldKey.equals("3") && newKey.equals("7"))
						|| (oldKey.equals("3") && newKey.equals("6"))) {
					if (ctx.get("AcceptanceDate") == null
							|| (ctx.get("AcceptanceDate") != null && ctx.get(
									"AcceptanceDate").equals(""))) {
						populateError(ctx, "AcceptanceDate",
								"Acceptance date is a required field.");
					}
				}
			}
			if (ctx.get("AcceptanceDate") != null
					&& !ctx.get("AcceptanceDate").equals("")) {
				strDate = ctx.get("AcceptanceDate").toString();
				validateLeapYearDate(ctx, "AcceptanceDate", strDate);
			}
			if (ctx.get("Premium") != null && !ctx.get("Premium").equals("")) {
				Object Premium = removeAmountFormat(ctx
						.get("Premium"));
				ctx.put("Premium", Premium);
			} else {
				ctx.put("Premium", null);
			}

			if (map.get("OldStatusKey") != null
					&& ("3".equals(map.get("OldStatusKey").toString()) || "7"
							.equals(map.get("OldStatusKey").toString()))) {
				if ("3".equals(map.get("OldStatusKey").toString())) {
					RuleUtils
							.executeRule(ctx, "LawyersRule.fillAcceptanceDate");

					// Object obj = RuleUtils.executeRule(ctx,
					// "LawyersRule.isQuoteExpired");

					Object obj1 = RuleUtils.executeRule(ctx,
							"LawyersRule.isQuotationExpiredAgent");

					boolean flag = false;
					if (obj instanceof Boolean)
						flag = (Boolean) obj;

					if (flag) {
						// LawyersUtils.populateError(ctx, "AcceptanceDate",
						// "Quote
						// expired and moved to under review bucket");
						SqlResources
								.getSqlMapProcessor(ctx)
								.update("SqlStmts.UserStatementsaveAccountstmtsupdatePolicyTransactionStatusKey",
										ctx);
					}

					if ("7".equals(ctx.get("StatusKey").toString())
							|| "6".equals(ctx.get("StatusKey").toString())) {

						List quoteList = null;

						boolean isNonRatingState = new LawyersValidationUtils()
								.isNonRatingState(ctx);
						if (isNonRatingState)
							quoteList = SqlResources
									.getSqlMapProcessor(ctx)
									.select("SqlStmts.quoteOptionviewgetQuoteListAllNonRatingState",
											ctx);

						else
							quoteList = SqlResources
									.getSqlMapProcessor(ctx)
									.select("SqlStmts.quoteOptionviewgetQuoteListAll",
											ctx);

						boolean finalisedFlag = false;
						int noOfFinalisedQuotes = 0;

						if (quoteList != null) {
							for (int i = 0; i < quoteList.size(); i++) {
								Map quote = (Map) quoteList.get(i);
								if (quote.get("IsThisOptionFinalised") != null) {
									if ("Y".equals(quote.get(
											"IsThisOptionFinalised").toString())) {
										finalisedFlag = true;
										ctx.put("FinalisedQuote_TransactionSequence",
												quote.get("TransactionSequence"));
										noOfFinalisedQuotes++;
									}
								}
							}
						}

						if (!finalisedFlag)
							LawyersUtils
									.populateError(ctx, "AcceptanceDate",
											"To continue, please finalise a quote option");
						else if (noOfFinalisedQuotes > 1)
							LawyersUtils.populateError(ctx, "AcceptanceDate",
									"Finalise one quote only");
					}

				}
			}
			if ("6".equals(ctx.get("StatusKey").toString())
					|| "7".equals(ctx.get("StatusKey").toString())) {
				new LawyersValidationUtils().checkIfApplicationIsComplete(ctx);

			}
			if ("6".equals(ctx.get("StatusKey").toString())) {

				Object object = SqlResources.getSqlMapProcessor(ctx).findByKey(
						"PaymentDetail.findByKey", ctx);

				if (object == null)
					populateError(ctx, "AcceptanceDate",
							"To continue please select payment option.");

				if (object != null && object instanceof Map) {
					Map objectMap = (Map) object;

					String paymentMode = objectMap.get("PaymentMode") != null ? objectMap
							.get("PaymentMode").toString() : "";

					if ("".equals(paymentMode))
						populateError(ctx, "AcceptanceDate",
								"To continue please select payment option.");

				}
				
				Object objRule = RuleUtils.executeRule(ctx, "LawyersRule.IsDaysMoreThan90");
				if(objRule != null){
					Boolean rule = (Boolean)objRule;
					if(rule){
						populateError(ctx, "AcceptanceDate",
								"Cannot Issue. Policy Effective Date is 90 days out.");
					}
				}
			}
			
			if(ctx.get("StatusKey")!=null && ctx.get("StatusKey").toString().equals("9"))
			{
				LawyersUtilities.validateAllPagesSaved(ctx);
				RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");
				if((Boolean) RuleUtils.executeRule(ctx, "LawyersRule.isInetErrorListEmpty"))
				{	
					 SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementdroolQueriesupdateStatusToUWreview",ctx);
				}
			}

		}

		return true;
	}

	public void setFirmDetail(IContext ctx) throws Exception {
		Object obj = ctx.get("Firm_Info");
		if (obj != null && ((List) obj).size() != 0 && ((List) obj).size() == 1) {
			Map map = (Map) ((List) obj).get(0);
			ctx.putAll(map);
		} else if (obj != null && ((List) obj).size() == 0) {
			populateError(ctx, "AccountEmail",
					"Credentials do not match, please contact 1-877-569-4111 for any assistance.");
		} else if (obj != null && ((List) obj).size() > 1) {
			populateError(ctx, "AccountEmail",
					"Please contact 1-877-569-4111 for any assistance.");
		}
	}

	private String getProjectUrl(IContext ctx) throws Exception {
		String resourcePath = null;
		resourcePath = SystemProperties.getInstance().getString(
				"appl." + ctx.getProject() + ".url");
		return resourcePath;
	}

	public void populateEnvironment(IContext ctx) throws Exception {
		String environmentVar = null;
		environmentVar = SystemProperties.getInstance().getString(
				"appl." + ctx.getProject() + ".environment");
		if (environmentVar == null || environmentVar.equals("DEV"))
			environmentVar = "QA";
		ctx.put("environmentVar", environmentVar);

		String isPaypalProduction = null;
		isPaypalProduction = SystemProperties.getInstance().getString(
				"paypal.production");
		if (isPaypalProduction == null)
			isPaypalProduction = "N";
		ctx.put("isPaypalProduction", isPaypalProduction);
	}

	/**
	 * This method gets the list of all licensed state and matches them with
	 * each of the user selected state . If user does not selects the licensed
	 * state then variable is net to N in context
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void getAllLincensedState(IContext ctx) throws Exception {

		ctx.remove("isLincesedState");
		List licensedStateList = new ArrayList();
		String locationState = null;

		if (ctx.get("StateDesc") != null)
			locationState = ctx.get("StateDesc").toString();
		else
			ctx.put("isLincesedState", "");

		Object objFirm = ctx.get("firm_list_4");
		List objFirmList = null;
		if (objFirm != null) {
			if (objFirm instanceof List) {
				objFirmList = (List) objFirm;
			}
			if (objFirmList != null && !objFirmList.isEmpty()) {
				for (int i = 0; i < objFirmList.size(); i++) {
					Map objAttorney = (Map) objFirmList.get(i);
					String state = (String) objAttorney.get("LicensedStates");
					String[] stateList = state.split(",");
					for (int j = 0; j < stateList.length; j++) {

						licensedStateList.add(stateList[j].trim());
					}
				}
				if (licensedStateList.contains(locationState)) {
					ctx.put("isLincesedState", "Y");
				} else {
					ctx.put("isLincesedState", "N");
				}
			} else {
				ctx.put("isLincesedState", "");
			}
		} else {
			ctx.put("isLincesedState", "");
		}
		/*
		 * Map stateMap = (Map) ctx.get("attorneys_freeform_1"); String state =
		 * (String) stateMap.get("LicensedStates"); String[] stateList =
		 * state.split(",");
		 * 
		 * 
		 * Object obj = SqlResources.getSqlMapProcessor(ctx).select(
		 * "State.findAll", ctx);
		 * 
		 * if (obj instanceof List) { List list = (List) obj; for (int i = 0; i
		 * < list.size(); i++) { Map statemap = (Map) list.get(i); String
		 * isLincesedState = (String) statemap .get("IsBusinessActiveLW");
		 * 
		 * if (isLincesedState != null && isLincesedState.equals("Y")) { String
		 * stateName = (String) statemap.get("StateDesc"); for (int j = 0; j <
		 * stateList.length; j++) { String selectedState = stateList[j];
		 * selectedState = selectedState.trim(); if
		 * (stateName.equals(selectedState)) { ctx.put("isLincesedState", "Y");
		 * break; } } } } if (ctx.get("isLincesedState") == null)
		 * ctx.put("isLincesedState", "N"); }
		 */

	}

	/**
	 * This method gets the licensed state for primary location
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void getAllLincensedStateForPrimary(IContext ctx) throws Exception {

		ctx.remove("isLincesedStateForPrimary");

		List licensedStateList = new ArrayList();
		String locationState = null;
		if (ctx.get("StateDesc") != null)
			locationState = ctx.get("StateDesc").toString();
		else
			ctx.put("isLincesedStateForPrimary", "N");

		boolean stateMatched = false;

		Object objPriLoc = ctx.get("firm_list_6");

		List objPrimaryLocList = null;
		if (objPriLoc != null) {
			if (objPriLoc instanceof List) {
				objPrimaryLocList = (List) objPriLoc;
			}

			if (objPrimaryLocList != null && !objPrimaryLocList.isEmpty()) {

				for (int i = 0; i < objPrimaryLocList.size(); i++) {

					Map objPrimaryLocation = (Map) objPrimaryLocList.get(i);
					String state = (String) objPrimaryLocation
							.get("FPLStateCode");
					if (state.equals(locationState)) {
						stateMatched = true;
						break;
					}
					// String[] stateList = state.split(",");
					// for (int j = 0; j < stateList.length; j++) {
					// licensedStateList.add(stateList[j].trim());
					// }

				}
				// if (licensedStateList.contains(locationState)) {
				// ctx.put("isLincesedStateForPrimary", "Y");
				// } else {
				// ctx.put("isLincesedStateForPrimary", "N");
				// }
			} else {
				// ctx.put("isLincesedStateForPrimary", "");
				stateMatched = true;
			}
		} else {
			// ctx.put("isLincesedStateForPrimary", "");
		}

		if (!stateMatched)
			ctx.put("isLincesedStateForPrimary", "N");
		else {
			ctx.put("isLincesedStateForPrimary", "Y");
		}

	}

	/**
	 * This method takes gets the amount from the HTML client and checks the
	 * fluctuation in amount
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void checkFluctuationInIncome(IContext ctx) throws Exception {
		
		if(ctx.get("inet_errors_list")==null)
		{
		
			Object objFirst = ctx.get("Amount_0");
			Object objSecond = ctx.get("Amount_1");
			Double amountFirst = 0.0;
			Double amountSecond = 0.0;
			if (objFirst != null && !"".equals(ctx.get("Amount_0").toString())) {
				String strAmountFirst = objFirst.toString();
				amountFirst = Double.parseDouble(strAmountFirst);
				/*
				 * String strAmountFirst = (String) objFirst; amountFirst =
				 * Double.parseDouble(strAmountFirst);
				 */
			}
			if (objSecond != null && !"".equals(ctx.get("Amount_1").toString())) {
				String strAmountSecond = objSecond.toString();
				// String strAmountSecond = (String) objSecond;
				amountSecond = Double.parseDouble(strAmountSecond);
			}
	
			Double difference;
			Double percentIncrease;
			if (amountFirst > amountSecond) {
	
				difference = amountFirst - amountSecond;
				percentIncrease = (difference / amountSecond) * 100;
			} else {
				difference = amountSecond - amountFirst;
				percentIncrease = ((difference / amountFirst) * 100);
			}
			if (percentIncrease >= 50) {
				ctx.put("showMsgGrossFluctuation", "Y");
			}
		}
	}

	/**
	 * This method is used to set the value of annualWorked hours of a single
	 * attorney. This is called only when the list of attorney size is one ie
	 * for a single attorney
	 * 
	 * @param ctx
	 */
	public void getAnnualHoursWorked(IContext ctx) {

		ctx.remove("AnnualWorkedHours");
		Object attorneyDetailListMap = ctx.get("firm_list_4");

		if (attorneyDetailListMap != null
				&& attorneyDetailListMap instanceof List) {

			List attorneyList = (List) attorneyDetailListMap;
			Map obj = (Map) attorneyList.get(0);
			String annualHoursWorked = String.valueOf(obj
					.get("AnnualWorkedHours"));
			ctx.put("AnnualWorkedHours", annualHoursWorked);

		}
	}

	/**
	 * This method checks the ratio of stafff to attorney if greater than
	 * three(3) then it will refer. (User for rules) Refer firm question no 3
	 * 
	 * @param ctx
	 */
	public void getRatioOfStaffToAttorney(IContext ctx) {

		ctx.remove("isRatioGreater");
		Object attorneyDetailListMap = ctx.get("firm_list_4");
		Object supportStaff = ctx.get("TotalNumOfNonAttorneyStaff");
		if (supportStaff != null && !"".equals(supportStaff.toString())) {
			Double intSupportStaff = Double
					.parseDouble(supportStaff.toString());
			List attorneyList = null;
			if (attorneyDetailListMap != null
					&& attorneyDetailListMap instanceof List) {

				attorneyList = (List) attorneyDetailListMap;
			}

			if (attorneyList == null || attorneyList.isEmpty()) {
				ctx.put("isRatioGreater", "N");
				return;
			}

			int size = attorneyList.size();

			Double ratio = (intSupportStaff / size);
			if (ratio > 3) {
				ctx.put("isRatioGreater", "Y");
			} else {
				ctx.put("isRatioGreater", "N");
			}
		}

	}

	public static boolean validateUserDetail(Context ctx) throws Exception {
		if (ctx.get("AccountEmail") == null)
			populateError(ctx, "AccountEmail", "User ID is requiredd.");
		else if (ctx.get("AccountEmail") != null
				&& "".equals(ctx.get("AccountEmail").toString()))
			populateError(ctx, "AccountEmail", "User ID is requiredd.");

		if (ctx.get("PassWord") == null)
			populateError(ctx, "PassWord", "Password is requiredd.");
		else if (ctx.get("PassWord") != null
				&& "".equals(ctx.get("PassWord").toString()))
			populateError(ctx, "PassWord", "Password is requiredd.");

		if (ctx.get("WorkPhone") == null)
			populateError(ctx, "WorkPhone", "Phone is required.");
		else if (ctx.get("WorkPhone") != null
				&& "".equals(ctx.get("WorkPhone").toString()))
			populateError(ctx, "WorkPhone", "Phone is required.");

		if (ctx.get("firstname") == null)
			populateError(ctx, "firstname", "First name is required.");
		else if (ctx.get("firstname") != null
				&& "".equals(ctx.get("firstname").toString()))
			populateError(ctx, "firstname", "First name is required.");

		if (ctx.get("lastname") == null)
			populateError(ctx, "lastname", "Last name is required.");
		else if (ctx.get("lastname") != null
				&& "".equals(ctx.get("lastname").toString()))
			populateError(ctx, "lastname", "Last name is required.");

		if (ctx.get("ChallengeQuesID") != null
				&& "".equals(ctx.get("ChallengeQuesID").toString()))
			populateError(ctx, "ChallengeQuesID",
					"Security Question is required.");

		if (ctx.get("ChallengeAns1") == null)
			populateError(ctx, "ChallengeAns1", "Security Answer is required.");
		else if (ctx.get("ChallengeAns1") != null
				&& "".equals(ctx.get("ChallengeAns1").toString()))
			populateError(ctx, "ChallengeAns1", "Security Answer is required.");

		if (ctx.get("PassWord") != null
				&& !"".equals(ctx.get("PassWord").toString())) {
			Object obj = RuleUtils.executeRule(ctx,
					"LawyersRule.validatePassword");
			if (obj != null && obj instanceof Boolean) {
				boolean flag = (Boolean) obj;
				if (!flag)
					populateError(ctx, "PassWord",
							"Password is not in correct format.");
			}
		}

		// This will check if LoginId already exist

		boolean emailExist = false;
		Object objEmailList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementManualBoQueriesgetAccountDetails", ctx);
		List emailList = null;
		if (objEmailList != null)
			emailList = (List) objEmailList;

		if (emailList != null && emailList.size() > 0)
			emailExist = true;

		if (emailExist)
			populateError(ctx, "AccountEmail",
					"Login Id already exist, please contact 1-877-569-4111 for assistance");

		return true;
	}

	public void processFinancialSuppList(IContext ctx) {
		if (ctx.get("financialInstitutionSupp_list_2") != null) {
			List objList = (List) ctx.get("financialInstitutionSupp_list_2");

			for (int i = 0; i < objList.size(); i++) {

				Map objMap = (Map) objList.get(i);
				if (objMap.get("DateOfInsolvency") != null) {

					ctx.put("isDateCompleted", "Y");

				}
			}
		}
	}

	public static boolean validateQuickQuote(Context ctx) throws Exception {

		if (ctx.get("IsQuickQuote") != null
				&& "Y".equals(ctx.get("IsQuickQuote").toString())
				&& ctx.get("IsFullQuote") != null
				&& "N".equals(ctx.get("IsFullQuote").toString())) {

			validateHistory(ctx);
			validateGeneralInfoQuickQuote(ctx);
			validateAOPPercentage(ctx);
			validateNumberOfYearsWithFirm(ctx);
			validateQuickQuotePhoneNumber(ctx);
			validateQuickQuoteDateField(ctx);
			validateCountyField(ctx);
			validateCityField(ctx);
			validateCountyForTX(ctx);

			boolean isAgent = false;
			Object objAgentRule = RuleUtils.executeRule(ctx,
					"LawyersRule.isAgent");
			if (objAgentRule != null && objAgentRule instanceof Boolean) {
				isAgent = (Boolean) objAgentRule;
			}

			// if (isAgent)
			// new
			// LawyersValidationUtils().validatePolicyEffectiveDateCoverage(ctx);

			if (ctx.get("IsClaimExpensesType") == null)
				ctx.put("IsClaimExpensesType", "N");
			if (ctx.get("IsClaimOptionType") == null)
				ctx.put("IsClaimOptionType", "N");

		}
		LawyersValidationUtils.validateEmail(ctx, "AccountEmail",
				ctx.get("AccountEmail") != null ? ctx.get("AccountEmail")
						.toString().trim() : "");

		return true;
	}

	private static void validateCountyField(IContext ctx) throws Exception {

		Object objCounty = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementdropdowndatacountyDropDown", ctx);

		if (objCounty != null && objCounty instanceof List) {

			List countyList = (List) objCounty;

			if (countyList != null && countyList.size() > 0) {

				String countyDesc = ctx.get("CountyDesc") != null ? ctx.get(
						"CountyDesc").toString() : "";
				if (countyDesc.equals(""))
					populateError(ctx, "CountyDesc",
							"County is a required field");

			}

		}

	}

	private static void validateCityField(IContext ctx) throws Exception {

		Object objCity = SqlResources.getSqlMapProcessor(ctx).select(
				"CityLW.getAllCityForCountyList", ctx);
		if (objCity != null && objCity instanceof List) {

			List cityList = (List) objCity;

			if (cityList != null && cityList.size() > 0) {

				String city = ctx.get("City") != null ? ctx.get("City")
						.toString() : "";
				if (city.equals(""))
					populateError(ctx, "City", "City is a required field");

			}

		}

	}

	public static boolean validatePolicyEffectiveDate(IContext ctx)
			throws Exception {

		String str = null;
		Object obj = ctx.get("PolicyEffectiveDate");

		if (obj != null
				&& !"".equals(ctx.get("PolicyEffectiveDate").toString())) {

			str = ctx.get("PolicyEffectiveDate").toString();

			Date objDate = new Date(str);

			Calendar cal = Calendar.getInstance();
			cal.set(objDate.getYear(), objDate.getMonth(), objDate.getDate());

			Date date = new Date();
			Calendar cal1 = Calendar.getInstance();
			cal1.set(date.getYear(), date.getMonth(), date.getDate());

			if (!cal.after(cal1)) {

				if (objDate.getYear() == date.getYear()
						&& objDate.getMonth() == date.getMonth()
						&& objDate.getDate() == date.getDate())
					return false;
				else
					return true;
				// populateError(ctx, "PolicyEffectiveDate",
				// "Policy Effective Date cannot be before the current date.");
			}

		}
		return false;

	}

	public static void validateNumberOfYearsWithFirm(IContext ctx)
			throws Exception {

		ctx.remove("ClaimAgeMap");
		int numberOfAttorney = 0;
		Map claimAgeMap = new HashMap();
		for (int i = 1; i <= MAXNUMBEROFATTORNEY; i++) {

			if (ctx.get("NumberOfYearsWithFirm_" + i) != null
					&& !"".equals(ctx.get("NumberOfYearsWithFirm_" + i)
							.toString())) {
				checkNegativeData(ctx.get("NumberOfYearsWithFirm_" + i)
						.toString(), "NumberOfYearsWithFirm_" + i, ctx);
				checkCompleteDataAttorney(ctx, i);
				getClaimAge(ctx, i);

				numberOfAttorney++;
			}

		}

		if (numberOfAttorney < 0 || numberOfAttorney == 0) {
			populateError(ctx, "NumberOfYearsWithFirm_1",
					"Provide data for attorney");
		} else {
			ctx.put("NumberOfLawyers", numberOfAttorney);
		}

		// ctx.put("ClaimAgeMap", claimAgeMap);
		logger.debug("Number Of Lawyers ------> " + numberOfAttorney);

	}

	public static void getClaimAge(IContext ctx, int i) throws Exception {

		int firmYear = 0;
		Integer numberOfYear = 0;
		if (ctx.get("FirmYear") != null)
			firmYear = Integer.parseInt(ctx.get("FirmYear").toString());

		if (ctx.get("NumberOfYearsWithFirm_" + i) != null)
			numberOfYear = Integer.parseInt(ctx.get(
					"NumberOfYearsWithFirm_" + i).toString());

		if (firmYear < numberOfYear) {
			ctx.put("ClaimAge_" + i, firmYear);
		} else {
			ctx.put("ClaimAge_" + i, numberOfYear);
		}

	}

	public static void checkCompleteDataAttorney(IContext ctx, int i)
			throws Exception {

		if (ctx.get("PartTime_" + i) == null)
			populateError(ctx, "PartTime_" + i, "Provide data for attorney "
					+ i);

		if (ctx.get("PartTime_" + i) != null
				&& "".equals(ctx.get("PartTime_" + i).toString()))
			populateError(ctx, "PartTime_" + i, "Provide data for attorney "
					+ i);

	}

	public static void validateGeneralInfoQuickQuote(IContext ctx)
			throws Exception {

		if (ctx.get("PolicyEffectiveDate") != null
				&& ctx.get("PolicyEffectiveDate").toString().equals(""))
			populateError(ctx, "PolicyEffectiveDate",
					"PolicyEffectiveDate is a required field");
	}

	/**
	 * This method sets the field IsFirmReffered to 'Y' in FirmLW entity in case
	 * the Policy is been refferred
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public static void isFirmRefferedQuickQuote(IContext ctx) throws Exception {

		String IsFirmReferred = "N";

		/*
		 * int numberOfAttorney = ctx.get("NumberOfLawyers") != null ?
		 * Integer.parseInt(ctx.get("NumberOfLawyers").toString()) : 0 ; // Solo
		 * And Part time if(numberOfAttorney == 1 ){
		 * 
		 * String partTime = ctx.get("PartTime_1") != null ?
		 * ctx.get("PartTime_1").toString() : null;
		 * 
		 * if(partTime != null && "Y".equals(ctx.get("PartTime_1").toString())){
		 * 
		 * IsFirmReferred = "Y"; } } // Number of lawyers greater than 5
		 * if(numberOfAttorney > 5){ IsFirmReferred = "N"; }
		 * 
		 * 
		 * //Aop Rule if (ctx.get("AOP_Percentage_23") != null) if
		 * (!"".equals(ctx.get("AOP_Percentage_23").toString()) &&
		 * !"0".equals(ctx.get("AOP_Percentage_23").toString())) IsFirmReferred
		 * = "Y";
		 * 
		 * if (ctx.get("AOP_Percentage_25") != null) if
		 * (!"".equals(ctx.get("AOP_Percentage_25").toString()) &&
		 * !"0".equals(ctx.get("AOP_Percentage_25").toString())) IsFirmReferred
		 * = "Y"; // If Claim In Last Five years if
		 * (ctx.get("IsClaimInLastFiveYears") != null &&
		 * !"".equals(ctx.get("IsClaimInLastFiveYears").toString())) if
		 * ("Y".equals(ctx.get("IsClaimInLastFiveYears").toString()))
		 * IsFirmReferred = "Y"; // If States if (ctx.get("StateCode") != null)
		 * {
		 * 
		 * for (int i = 0; i < referredStates.length; i++) { if
		 * (referredStates[i].equals(ctx.get("StateCode").toString()))
		 * IsFirmReferred = "Y"; } }
		 * 
		 * Object objPolicyEffectiveDateRefer = RuleUtils.executeRule(ctx,
		 * "LawyersRule.isPolicyEffectiveDateRefer"); boolean
		 * flagPolicyEffectiveDateRefer = false; if (objPolicyEffectiveDateRefer
		 * instanceof Boolean) flagPolicyEffectiveDateRefer = (Boolean)
		 * objPolicyEffectiveDateRefer;
		 * 
		 * if (flagPolicyEffectiveDateRefer) IsFirmReferred = "Y";
		 * 
		 * 
		 * RuleUtils.executeRule(ctx, "LawyersRule.assignAggregateLimit");
		 * RuleUtils.executeRule(ctx, "LawyersRule.assignOccurenceLimit");
		 * 
		 * int limit = ctx.get("AggregateLimitVal") != null ?
		 * Integer.parseInt(ctx.get("AggregateLimitVal").toString()) : 0; int
		 * deductible = 0;
		 * 
		 * 
		 * if (numberOfAttorney == 1 || numberOfAttorney == 2) { if (limit >
		 * 1000000) { IsFirmReferred = "Y"; } }
		 * 
		 * if (limit > 500000) { if (deductible < 5000) IsFirmReferred = "Y"; }
		 * 
		 * 
		 * if (numberOfAttorney > 2 && numberOfAttorney < 6) { if (limit <
		 * 2000000) IsFirmReferred = "Y"; }
		 */

		ctx.put("IsFirmReferred", IsFirmReferred);

	}

	public static void validateAOPCommentQuickQuote(IContext ctx)
			throws Exception {

		Map map = new HashMap();
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select(
				"AOPLW.findAll", ctx);

		if (limtTypes != null) {
			for (int i = 0; i < limtTypes.size(); i++) {
				map = (HashMap) limtTypes.get(i);
				if (map.get("AOPKey").toString().equals("20")) {
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
							String msg = "Please describe the Other Services";
							populateError(ctx,
									"AOPCommentDesc_"
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

	public static void validateHistory(IContext ctx) throws Exception {

		if (ctx.get("FirmYear") != null
				&& "".equals(ctx.get("FirmYear").toString()))
			populateError(ctx, "FirmYear", "Firm Year is a required field.");

		if (ctx.get("IsClaimInLastFiveYears") == null)
			populateError(ctx, "IsClaimsInLast5Year",
					"Please indicate yes or no.");

		if (ctx.get("FirmYear") != null
				&& !"".equals(ctx.get("FirmYear").toString())) {
			int firmYear = Integer.parseInt(ctx.get("FirmYear").toString());
			if (firmYear < 0)
				populateError(ctx, "FirmYear",
						"Firm Year cannot be a negative value");

		}
	}

	public static void validateQuickQuoteDateField(Context ctx)
			throws Exception {
		String strDate = null;
		if (ctx.get("PolicyEffectiveDate") != null
				&& !"".equals(ctx.get("PolicyEffectiveDate").toString())) {
			strDate = ctx.get("PolicyEffectiveDate").toString();
			validateLeapYearDate(ctx, "PolicyEffectiveDate", strDate);

		}
	}

	public void getAverageRevenueQuickQuote(IContext ctx) throws Exception {

		int count = 0;
		long total = 0;
		long average = 0;

		Object obj0 = removeAmountFormat(ctx.get("Amount"));
		if (obj0 != null && !"".equals(obj0) && !"0".equals(obj0)) {
			count = count + 1;
			total = total + Long.parseLong(obj0.toString());
		}

		if (count != 0 && total != 0) {
			average = total / count;
		}

		ctx.put("AverageRevFromAjax", average);
	}

	public static void validateQuickQuotePhoneNumber(IContext ctx)
			throws Exception {

		if (ctx.get("WorkPhone") != null
				&& !"".equals(ctx.get("WorkPhone").toString())) {
			if (!validatePhoneNumber(ctx.get("WorkPhone").toString().trim()))
				populateError(ctx, "WorkPhone",
						"Phone should be at least of 10 digit.");
		}
	}

	/**
	 * This method inserts data in ClaimAgeLW table
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void insertClaimAgeLW(IContext ctx) throws Exception {

		// delete all data of ClaimAge of current policy Before Adding
		DBUtils.executeDBOperation(ctx, "ClaimAgeLW", "14");
		// int FirmYear = ctx.get("FirmYear") != null ?
		// Integer.parseInt(ctx.get("FirmYear").toString()) : 0;
		if (ctx.get("NumberOfLawyers") != null
				&& Integer.parseInt(ctx.get("NumberOfLawyers").toString()) > 0) {

			int numOfLawyers = Integer.parseInt(ctx.get("NumberOfLawyers")
					.toString());

			for (int i = 1; i <= MAXNUMBEROFATTORNEY; i++) {

				if ((ctx.get("NumberOfYearsWithFirm_" + i) != null && !""
						.equals(ctx.get("NumberOfYearsWithFirm_" + i)))
						&& ctx.get("PartTime_" + i) != null) {

					// int ClaimAge =
					// checkNumberAndFirmYear(ctx.get("NumberOfYearsWithFirm_" +
					// i) != null ?
					// Integer.parseInt(ctx.get("NumberOfYearsWithFirm_" +
					// i).toString()) : 0,FirmYear);

					Context newctx = new Context();
					newctx.setProject(ctx.getProject());
					newctx.put("Year", ctx.get("NumberOfYearsWithFirm_" + i));
					newctx.put("PartTime", ctx.get("PartTime_" + i));
					newctx.put("PolicyKey", ctx.get("PolicyKey"));
			    	newctx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
			    	newctx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));

					DBUtils.executeDBOperation(newctx, "ClaimAgeLW", "1");

				}
			}

		}
	}

	public static int checkNumberAndFirmYear(int NumberOfYearWithFirm,
			int FirmYear) throws Exception {

		if (NumberOfYearWithFirm < FirmYear)
			return NumberOfYearWithFirm;
		else
			return FirmYear;

	}

	/**
	 * This method is for populating the ClaimAge data for Quick Quote Page
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void populateClaimAgeData(IContext ctx) throws Exception {

		List objList = SqlResources.getSqlMapProcessor(ctx).select(
				"ClaimAgeLW.findAllByPartialKey", ctx);

		if (objList != null && objList.size() > 0) {
			for (int i = 0; i < objList.size(); i++) {
				Map objMap = (Map) objList.get(i);
				ctx.put("PartTime_" + (i + 1), objMap.get("PartTime"));
				ctx.put("NumberOfYearsWithFirm_" + (i + 1), objMap.get("Year"));

			}
		}
	}

	/**
	 * This method is used when updating the PolicyCoverage data when AccountRep
	 * get the premium quote on quick quote page
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void updatePolicyCoverage(IContext ctx) throws Exception {

		Object obj = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetPolicyListAll", ctx);

		List objList = null;

		if (obj != null && obj instanceof List) {

			objList = (List) obj;
		}

		if (objList != null && objList.size() > 0) {

			for (int i = 0; i < objList.size(); i++) {
				Context newCtx = new Context();
				newCtx.setProject(ctx.getProject());
				Map objMap = (Map) objList.get(i);

				newCtx.putAll(objMap);

				newCtx.put("AggregateLimit", ctx.get("AggregateLimit"));
				newCtx.put("OccuranceLimit", ctx.get("OccuranceLimit"));
				newCtx.put("AggregateDeductible",
						ctx.get("AggregateDeductible"));
				newCtx.put("OccuranceDeductible",
						ctx.get("OccuranceDeductible"));
		    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
		    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));

				SqlResources.getSqlMapProcessor(newCtx).update(
						"PolicyCoverage.update", newCtx);

				break;

			}

		}
	}

	/**
	 * This method is called to validate when get another quote is been clicked
	 * on the quickquoteoption page in the accountrep view.
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public static boolean validateGetAnotherQuote(Context ctx) throws Exception {

		if (ctx.get("GetAnotherQuoteFullQuote") != null
				&& "Y".equals(ctx.get("GetAnotherQuoteFullQuote").toString())) {

			Object obj = SqlResources.getSqlMapProcessor(ctx).select(
					"SqlStmts.UserStatementpdfquotelettercheckIfQuoteExist",
					ctx);

			List quoteList = null;
			if (obj != null && obj instanceof List)
				quoteList = (List) obj;

			if (quoteList != null && quoteList.size() > 0)
				populateError(ctx, "LimitSequence",
						"This quote already exist, please select different combination");

			if (ctx.get("IsNonRatingState") != null
					&& "Y".equals(ctx.get("IsNonRatingState").toString())) {

				if (ctx.get("IsManualPremium") != null
						&& "N".equals(ctx.get("IsManualPremium").toString())) {

					populateError(ctx, "LimitSequence",
							"Only Manual Premium is allowed for this state");
				}
				if (ctx.get("IsManualPremium") == null) {
					populateError(ctx, "LimitSequence",
							"Only Manual Premium is allowed for this state");
				}

			}

			if (ctx.get("IsManualPremium") != null
					&& "Y".equals(ctx.get("IsManualPremium").toString())) {

				if (ctx.get("ManualPremium") == null)
					populateError(ctx, "ManualPremium",
							"Manual Premium is a required field.");

				if (ctx.get("ManualPremium") != null
						&& !"".equals(ctx.get("ManualPremium").toString())) {
					Object obj1 = removeAmountFormat(ctx.get("ManualPremium"));
					if (obj1 != null && "0".equals(obj1.toString()))
						populateError(ctx, "ManualPremium",
								"Manual Premium should not be equal to 0.");
				}

				if (ctx.get("ManualPremium") != null
						&& "".equals(ctx.get("ManualPremium").toString()))
					populateError(ctx, "ManualPremium",
							"Manual Premium is a required field.");

			}

			/*
			 * // The below rules are checked in case the State is Non Rating
			 * State
			 * 
			 * Object obj = DBUtils.executeDBOperation(ctx, "Address", "3");
			 * if(obj != null && obj instanceof Map){
			 * 
			 * Map objMap = (Map)obj ;
			 * 
			 * String StateCode = objMap.get("StateCode") != null ?
			 * objMap.get("StateCode").toString() : "" ;
			 * if(StateCode.equals(LawyersConstants.ARKANSAS) ||
			 * StateCode.equals(LawyersConstants.SOUTH_DAKHOTA)){ new
			 * LawyersValidationUtils().checkLimitsOfLiability(ctx); }
			 * if(StateCode.equals(LawyersConstants.VERMONT)){ } }
			 */

		}
		if (ctx.get("GetAnotherQuoteQQ") != null
				&& "Y".equals(ctx.get("GetAnotherQuoteQQ").toString())) {

			Object object = SqlResources.getSqlMapProcessor(ctx).select(
					"SqlStmts.UserStatementpdfquotelettercheckIfQuoteExistQQ",
					ctx);

			List quoteListQQ = null;
			if (object != null && object instanceof List)
				quoteListQQ = (List) object;

			if (quoteListQQ != null && quoteListQQ.size() > 0)
				populateError(ctx, "LimitSequence",
						"This quote already exist, please select different combination");

		}
		/*
		 * if (ctx.get("IsManualPremium") != null &&
		 * "Y".equals(ctx.get("IsManualPremium").toString())) {
		 * 
		 * return true; }
		 */

		if (ctx.get("IsQuickQuote") != null
				&& "Y".equals(ctx.get("IsQuickQuote").toString())) {
			if (ctx.get("IsClaimExpensesType") == null)
				ctx.put("IsClaimExpensesType", "N");
			if (ctx.get("IsClaimOptionType") == null)
				ctx.put("IsClaimOptionType", "N");
		}

		/*
		 * Boolean flag = false; Object obj = RuleUtils.executeRule(ctx,
		 * "LawyersRule.checkDefenseExpenseType");
		 * 
		 * if (obj != null && obj instanceof Boolean) flag = (Boolean) obj;
		 * 
		 * if (flag) ctx.put("IsClaimExpensesType", "Y");
		 */
		return true;
	}

	/**
	 * This Quote is been called as a MO validation. It validates if there is
	 * already existing Quote and does not allow to add multiple Quotes of same
	 * data or (type).
	 * 
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static boolean validateQuickOrFullQuote(Context ctx)
			throws Exception {
		
		if (ctx.get("IsClaimExpensesType") == null)
			ctx.put("IsClaimExpensesType", "N");
		else if (ctx.get("IsClaimExpensesType") != null
				&& "".equals(ctx.get("IsClaimExpensesType").toString()))
			ctx.put("IsClaimExpensesType", "N");

		if (ctx.get("IsClaimOptionType") == null)
			ctx.put("IsClaimOptionType", "N");
		else if (ctx.get("IsClaimOptionType") != null
				&& "".equals(ctx.get("IsClaimOptionType").toString()))
			ctx.put("IsClaimOptionType", "N");

		if (ctx.get("IsDollarDefense") == null)
			ctx.put("IsDollarDefense", "N");
		else if (ctx.get("IsDollarDefense") != null
				&& "".equals(ctx.get("IsDollarDefense").toString()))
			ctx.put("IsDollarDefense", "N");

		if (ctx.get("IsManualPremium") == null)
			ctx.put("IsManualPremium", "N");
		else if (ctx.get("IsManualPremium") != null
				&& "".equals(ctx.get("IsManualPremium").toString()))
			ctx.put("IsManualPremium", "N");

		if (ctx.get("IsManualPremium") != null
				&& "Y".equals(ctx.get("IsManualPremium").toString()))
			ctx.put("ManualPremium",
					removeAmountFormat(ctx.get("ManualPremium")));

		Object objAddress = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"Address.findByKey", ctx);

		if (objAddress != null) {

			Map mapAddress = (Map) objAddress;

			ctx.put("StateCode", mapAddress.get("StateCode"));

		}
		
		Object objR = RuleUtils.executeRule(ctx, "LawyersRule.isFullQuote");
		if(objR != null && objR instanceof Boolean)
		{
			Boolean fullquote = (Boolean) objR;
			if(fullquote)
				return true;
		}
		
		Object objRule = RuleUtils.executeRule(ctx,"LawyersRule.IsClaimExpenseOutsideOnly");
		if (objRule != null && objRule instanceof Boolean) {
			Boolean stateRule = (Boolean) objRule;
			if (stateRule) {
				ctx.put("IsClaimExpensesType", "Y");

			}
		}
		
		validateLimitsAndClaimExpense(ctx);

		return true;

	}

	/**
	 * This method is for condition that check whether the firm is to be
	 * referred or not
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public static void isFirmReffered(IContext ctx) throws Exception {

		String IsFirmReferred = "N";
		String IsQuoteViewedByInsured = "N";

		Object obj = DBUtils.executeDBOperation(ctx, "ClaimAgeLW", "8");
		List claimAgeList = null;
		Map objMap = null;
		if (obj != null && obj instanceof List) {
			claimAgeList = (List) obj;
			int numberOfAttorney = claimAgeList.size();
			if (numberOfAttorney == 1) {

				objMap = (Map) claimAgeList.get(0);
				if (objMap.get("PartTime") != null) {
					String partTime = objMap.get("PartTime").toString();
					if (!"".equals(partTime) && "Y".equals(partTime)) {
						IsFirmReferred = "Y";
					}
				}

			}

			for (int i = 0; i < claimAgeList.size(); i++) {

				Map claimAgeMap = (Map) claimAgeList.get(i);

				if (claimAgeMap.get("PartTime") != null
						&& "Y".equals(claimAgeMap.get("PartTime")))
					IsFirmReferred = "Y";

			}

			Object obj1 = SqlResources.getSqlMapProcessor(ctx).select(
					"SqlStmts.UserStatementsaveAccountstmtsgetPolicyListAll",
					ctx);
			List policyList = null;
			Map objPolicyMap = null;
			int limit = 0;
			int occlimit = 0;
			if (numberOfAttorney == 1 || numberOfAttorney == 2) {

				if (obj1 != null && obj1 instanceof List) {
					policyList = (List) obj1;

					objPolicyMap = (Map) policyList.get(0);
					if (objPolicyMap != null) {

						// if (objPolicyMap.get("AggregateLimit") != null) {
						//
						// limit = Integer.parseInt(objPolicyMap.get(
						// "AggregateLimit").toString());
						// if (limit > 1000000) {
						// IsFirmReferred = "Y";
						// }
						// }
						if (objPolicyMap.get("OccuranceLimit") != null) {

							occlimit = Integer.parseInt(objPolicyMap.get(
									"OccuranceLimit").toString());
							if (occlimit > 1000000) {
								IsFirmReferred = "Y";
							}
						}
					}
				}
				if (occlimit > 500000) {
					if (objPolicyMap.get("AggregateDeductible") != null) {
						int deductible = Integer.parseInt(objPolicyMap.get(
								"AggregateDeductible").toString());
						if (deductible < 5000)
							IsFirmReferred = "Y";
					}

				}

			}
			/*
			 * if (obj1 != null && obj1 instanceof List) { policyList = (List)
			 * obj1;
			 * 
			 * objPolicyMap = (Map) policyList.get(0); int aggrLimit =
			 * objPolicyMap.get("AggregateLimit") != null ? Integer
			 * .parseInt(objPolicyMap.get("AggregateLimit").toString()) : 0; int
			 * occLimit = objPolicyMap.get("OccuranceLimit") != null ? Integer
			 * .parseInt(objPolicyMap.get("OccuranceLimit").toString()) : 0; if
			 * (aggrLimit > 500000 || occLimit > 500000) { if
			 * (objPolicyMap.get("AggregateDeductible") != null) { int
			 * deductible = Integer.parseInt(objPolicyMap.get(
			 * "AggregateDeductible").toString()); if (deductible < 5000)
			 * IsFirmReferred = "Y"; } } }
			 */
			if (obj1 != null && obj1 instanceof List) {
				policyList = (List) obj1;

				objPolicyMap = (Map) policyList.get(0);
				int aggrLimit = objPolicyMap.get("AggregateLimit") != null ? Integer
						.parseInt(objPolicyMap.get("AggregateLimit").toString())
						: 0;
				int occLimit = objPolicyMap.get("OccuranceLimit") != null ? Integer
						.parseInt(objPolicyMap.get("OccuranceLimit").toString())
						: 0;

				if (numberOfAttorney > 2 && numberOfAttorney < 6) {
					if (occLimit > 2000000)
						IsFirmReferred = "Y";

					if (occLimit > 1000000) {

						int deductible = objPolicyMap
								.get("AggregateDeductible") != null ? Integer
								.parseInt(objPolicyMap.get(
										"AggregateDeductible").toString()) : 0;

						if (deductible < 10000)
							IsFirmReferred = "Y";

					}
				}
			}

			if (numberOfAttorney > 5) {
				IsFirmReferred = "Y";
			}
		}

		List aoplist = SqlResources
				.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementsaveAccountstmtspopulateAOPFieldsQuickQuote",
						ctx);
		if (aoplist != null) {
			Map map = new HashMap();
			for (int i = 0; i < aoplist.size(); i++) {
				map = (HashMap) aoplist.get(i);
				ctx.put("AOP_Percentage_" + map.get("AOPKey"),
						map.get("PercentageValue"));
			}
			if (ctx.get("AOP_Percentage_23") != null)
				if (!"".equals(ctx.get("AOP_Percentage_23").toString())
						&& !"0".equals(ctx.get("AOP_Percentage_23").toString()))
					IsFirmReferred = "Y";

			if (ctx.get("AOP_Percentage_25") != null)
				if (!"".equals(ctx.get("AOP_Percentage_25").toString())
						&& !"0".equals(ctx.get("AOP_Percentage_25").toString()))
					IsFirmReferred = "Y";
		}

		Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"SqlStmts.firmviewgetAllPageFlags", ctx);

		if (objFirm != null && objFirm instanceof Map) {

			Map objFirmMap = (Map) objFirm;

			if (objFirmMap.get("IsClaimInLastFiveYears") != null
					&& !"".equals(objFirmMap.get("IsClaimInLastFiveYears")
							.toString()))
				if ("Y".equals(objFirmMap.get("IsClaimInLastFiveYears")
						.toString()))
					IsFirmReferred = "Y";

		}

		Object objState = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"State.findByKey", ctx);

		Map stateMap = new HashMap();
		if (objState != null && objState instanceof Map)
			stateMap = (Map) objState;

		String isReferredState = stateMap.get("IsStateReferred") != null ? stateMap
				.get("IsStateReferred").toString() : "";

		if (isReferredState.equals("Y"))
			IsFirmReferred = "Y";

		Object objPolicyEffectiveDateRefer = RuleUtils.executeRule(ctx,
				"LawyersRule.isPolicyEffectiveDateRefer");
		boolean flagPolicyEffectiveDateRefer = false;
		if (objPolicyEffectiveDateRefer instanceof Boolean)
			flagPolicyEffectiveDateRefer = (Boolean) objPolicyEffectiveDateRefer;

		if (!flagPolicyEffectiveDateRefer)
			IsFirmReferred = "Y";

		// boolean isBeforeCurrentDate = validatePolicyEffectiveDate(ctx);

		// if(isBeforeCurrentDate)
		// IsFirmReferred = "Y";

		if (IsFirmReferred.equals("Y")) {
			IsQuoteViewedByInsured = "N";
		} else {
			IsQuoteViewedByInsured = "Y";
		}

		ctx.put("IsFirmReferred", IsFirmReferred);
		ctx.put("IsQuoteViewedByInsured", IsQuoteViewedByInsured);

		SqlResources.getSqlMapProcessor(ctx).update(
				"SqlStmts.UserStatementsaveAccountstmtsupdateFirmLW", ctx);

	}

	public void testing(IContext ctx) {

		logger.debug("AccountID is - - - >" + ctx.get("AccountID"));
		logger.debug("-----");

	}
	
	public static String getDateInStringFormat(String format) {
		String strDate = "";
		try{
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
		    strDate = sdf.format(cal.getTime());
		} catch (Exception e){
			logger.error("Unable to format date", e);
		}
	    return strDate;    

	}

	public static void main(String[] a) throws Exception {

//		LawyersUtils u = new LawyersUtils();
//		Context ctx = new Context();
//		ctx.put("AccountName", "Test");
//		ctx.put("PolicyKey", "5600");
//		ctx.setProject("LawyersIns");
		//u.generateThanksLetterBodyForSubProducer();
		//u.sendCompleteAppLinkMail(ctx);
		String val = new SimpleDateFormat("yyyy-MM-dd hh:mm").format("2019-05-17 13:06:56.61");
		logger.debug("Debug..." + val);

		// String priordate = "2009-01-26 00:00:00.000";
		// String effdate = "2013-01-26 00:00:00.000";
		// Date prior = LawyersDateUtils.convertStringToDate(priordate,
		// "yyyy-mm-dd");
		// Date eff = LawyersDateUtils.convertStringToDate(effdate,
		// "yyyy-mm-dd");
		// int count = 0;
		// int priorYear = prior.getYear();
		// int effYear = eff.getYear();
		// int priorMonth = prior.getMonth();
		// int effMonth = eff.getMonth();
		// int priorDate = prior.getDate();
		// int effDate = prior.getDate();
		//
		// int stepYear = effYear - priorYear;
		//
		// int stepmonth = effMonth - priorMonth;
		// if (stepmonth > 0)
		// stepYear = stepYear + 1;
		// else if (stepmonth == 0) {
		//
		// int stepDay = effDate - priorDate;
		// if (stepDay > 0) {
		// stepYear = stepYear + 1;
		// }
		//
		// }
		//
		// logger.debug("Year is  " + stepYear);

		// while(priorYear <= effYear){
		//
		// if(!isLeapYear(priorYear)){
		// count++ ;
		// }
		// priorYear++ ;
		// }
		// long days = LawyersDateUtils.calculateDiffInDays(eff, prior);
		// Double diff = Double.parseDouble((String.valueOf(days)));
		// diff = diff + count - 1;
		// double year = diff / 366;
		// logger.debug("Fraction " + year);
		// int firmStepRateYears = (int) Math.ceil(year);
		//
		//
		// logger.debug("Rate is - " + firmStepRateYears);

	}

	/**
	 * This method will delete the quick quote data before the Quote is
	 * connverted to full quote This is done in two steps 1. The first Object
	 * data is set to null 2. All the other Data are removed except the first
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void cleanPolicyCoverage(IContext ctx) throws Exception {

		// Clear ProfessionalLiabilityInSDetail Data
		cleanProfessionalLiabilityInsData(ctx);

		// Clear Rating data
		cleanRatingTraceData(ctx);

		// Clean Quote data
		cleanQuoteData(ctx);

		// Clear Policy Data
		cleanPolicyData(ctx);

		// delete policy transaction data
		cleanPolicyTransactionData(ctx);

		// delete coverage modifier data
		SqlResources.getSqlMapProcessor(ctx).delete(
				"SqlStmts.UserStatementsaveAccountstmtsdeleteCoverage", ctx);

		// delete Claim Age data
		DBUtils.executeDBOperation(ctx, "ClaimAgeLW", "2");

	}

	/*
	 * This method will clean the professionalLiabilityInsData
	 */
	private void cleanProfessionalLiabilityInsData(IContext ctx)
			throws Exception {

		List proList = SqlResources.getSqlMapProcessor(ctx).select(
				"ProfessionalLiabilityInsDetailLW.findAllByPartialKey", ctx);

		if (proList != null && proList.size() > 0) {

			for (int i = 0; i < proList.size(); i++) {
				Map objMap1 = (Map) proList.get(i);
				Context newCtx1 = new Context();
				newCtx1.setProject(ctx.getProject());
				newCtx1.put("VersionSequence", objMap1.get("VersionSequence"));
				newCtx1.put("PolicyKey", objMap1.get("PolicyKey"));
				newCtx1.put("FirmYear", "");
		    	newCtx1.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
		    	newCtx1.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));

				DBUtils.executeDBOperation(newCtx1,
						"ProfessionalLiabilityInsDetailLW", "4");

			}
		}

	}

	/*
	 * This method cleans the Rating Trace data before converting Quick Quote to
	 * full Quote
	 */
	private void cleanRatingTraceData(IContext ctx) throws Exception {

		List ratingList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsRatingTrace", ctx);

		if (ratingList != null && ratingList.size() > 0) {

			Map ratingMap = (Map) ratingList.get(0);

			Context newCtx = new Context();
			newCtx.setProject(ctx.getProject());
			newCtx.put("PolicyKey", ratingMap.get("PolicyKey"));
			newCtx.put("RatingTraceSequence",
					ratingMap.get("RatingTraceSequence"));
			newCtx.put("TransactionSequence",
					ratingMap.get("TransactionSequence"));
			newCtx.put("XmlInputDatatoRating", "");
			newCtx.put("XmlOutputDatafromRating", "");
			newCtx.put("RatingDate", "");
	    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
	    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));

			DBUtils.executeDBOperation(newCtx, "RatingTrace", "4");

			for (int i = 1; i < ratingList.size(); i++) {

				Map ratingMap1 = (Map) ratingList.get(i);

				Context newCtx1 = new Context();
				newCtx1.setProject(ctx.getProject());
				newCtx1.put("PolicyKey", ratingMap1.get("PolicyKey"));
				newCtx1.put("RatingTraceSequence",
						ratingMap1.get("RatingTraceSequence"));
				newCtx1.put("TransactionSequence",
						ratingMap1.get("TransactionSequence"));

				DBUtils.executeDBOperation(newCtx1, "RatingTrace", "2");

			}
		}

	}

	/*
	 * This method will delete all quote except the first Quote before quick
	 * quote is converted to full quote
	 */
	private void cleanQuoteData(IContext ctx) throws Exception {

		List quoteList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetQuoteList", ctx);

		if (quoteList != null && quoteList.size() > 0) {

			Map ratingMap = (Map) quoteList.get(0);

			Context newCtx = new Context();
			newCtx.setProject(ctx.getProject());
			newCtx.put("PolicyKey", ratingMap.get("PolicyKey"));
			newCtx.put("TransactionSequence",
					ratingMap.get("TransactionSequence"));
			newCtx.put("IsQuoteSelected", "");
			newCtx.put("IsFirstQuote", "");
			newCtx.put("TotalPremiumFirstRange", "");
			newCtx.put("IsMinimumPremium", "");
			newCtx.put("QuoteEffectiveDate", "");
			newCtx.put("QuoteExpiryDate", "");
	    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
	    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));

			DBUtils.executeDBOperation(newCtx, "Quote", "4");

			for (int i = 1; i < quoteList.size(); i++) {

				Map ratingMap1 = (Map) quoteList.get(i);

				Context newCtx1 = new Context();
				newCtx1.setProject(ctx.getProject());
				newCtx1.put("PolicyKey", ratingMap1.get("PolicyKey"));
				newCtx1.put("TransactionSequence",
						ratingMap1.get("TransactionSequence"));

				DBUtils.executeDBOperation(newCtx1, "Quote", "2");

			}

		}

	}

	/*
	 * This method will clean the Policy Data. It will clean the field before
	 * the Quote is converted to full quote but it will not delete the policy.
	 */
	private void cleanPolicyData(IContext ctx) throws Exception {

		List policyList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetPolicyListAll", ctx);

		if (policyList != null && policyList.size() > 0) {

			Map objMap = (Map) policyList.get(0);
			Context newCtx = new Context();
			newCtx.setProject(ctx.getProject());
			newCtx.put("TransactionSequence", objMap.get("TransactionSequence"));
			newCtx.put("CoverageSequence", objMap.get("CoverageSequence"));
			newCtx.put("PolicyKey", objMap.get("PolicyKey"));
			newCtx.put("Premium", "");
			newCtx.put("IsClaimOptionType", "");
			newCtx.put("IsClaimExpensesType", "");
			newCtx.put("AggregateLimit", "");
			newCtx.put("OccuranceLimit", "");
			newCtx.put("OccuranceDeductible", "");
			newCtx.put("TotalCovModifierPercentage", "");
			newCtx.put("TotalCoverageTaxAmount", "");
			newCtx.put("InitialCovPremium", "");
			newCtx.put("IsDollarDefense", "");
			newCtx.put("AggregateDeductible", "");
	    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
	    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));

			DBUtils.executeDBOperation(newCtx, "PolicyCoverage", "4");

			for (int i = 1; i < policyList.size(); i++) {
				Map objMap1 = (Map) policyList.get(i);
				Context newCtx1 = new Context();
				newCtx1.setProject(ctx.getProject());
				newCtx1.put("TransactionSequence",
						objMap1.get("TransactionSequence"));
				newCtx1.put("CoverageSequence", objMap1.get("CoverageSequence"));
				newCtx1.put("PolicyKey", objMap1.get("PolicyKey"));

				SqlResources.getSqlMapProcessor(newCtx1).delete(
						"SqlStmts.UserStatementsaveAccountstmtsdeleteCoverage",
						newCtx1);
				SqlResources.getSqlMapProcessor(newCtx1).delete(
						"PolicyCoverage.delete", newCtx1);

			}
		}
	}

	/*
	 * This method cleans the Policy Transaction Data. It will dete all the PT
	 * data but only the initial record remains, after the Quote is converted to
	 * full quote
	 */
	private void cleanPolicyTransactionData(IContext ctx) throws Exception {

		List transList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsPolicyTransaction", ctx);

		if (transList != null && transList.size() > 0) {

			Map transMap = (Map) transList.get(0);

			Context newCtx = new Context();
			newCtx.setProject(ctx.getProject());
			newCtx.put("PolicyKey", transMap.get("PolicyKey"));
			newCtx.put("TransactionSequence",
					transMap.get("TransactionSequence"));
			newCtx.put("TransactionTypeKey", transMap.get("TransactionTypeKey"));
			newCtx.put("AccountKey", transMap.get("AccountKey"));
			newCtx.put("StatusKey", transMap.get("StatusKey"));
			newCtx.put("TotalPremium", "");
			newCtx.put("TotalPolicyModifierPercentage", "");
			newCtx.put("TotalPolicyTaxAmount", "");
			newCtx.put("InvoicedPremium", "");
			newCtx.put("PolicyExpirationDate", "");
	    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
	    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));

			DBUtils.executeDBOperation(newCtx, "PolicyTransaction", "4");

			for (int i = 1; i < transList.size(); i++) {

				Map transMap1 = (Map) transList.get(i);

				Context newCtx1 = new Context();
				newCtx1.setProject(ctx.getProject());
				newCtx1.put("PolicyKey", transMap1.get("PolicyKey"));
				newCtx1.put("TransactionSequence",
						transMap1.get("TransactionSequence"));

				DBUtils.executeDBOperation(newCtx1, "PolicyTransaction", "2");

			}

		}

	}

	/**
	 * This method will get the claim age for quote option in full quote.
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void saveClaimAgeData(IContext ctx) throws Exception {
		/*
		 * List attorneyList = null; Object attorneyDetailList =
		 * ctx.get("firm_list_4");
		 * 
		 * if(attorneyDetailList != null && attorneyDetailList instanceof List)
		 * attorneyList = (List)attorneyDetailList; else return ;
		 * 
		 * 
		 * 
		 * if( attorneyList != null && attorneyList.size() > 0){
		 * 
		 * for(int i = 0 ; i<attorneyList.size() ; i++){
		 * 
		 * Map attorneyMap = (Map)attorneyList.get(i);
		 * 
		 * Object numberOfYears = attorneyMap.get("NumberOfYearsWithFirm");
		 * if(numberOfYears != null){
		 * 
		 * int numOfYears = Integer.parseInt(numberOfYears.toString()); Context
		 * newCtx = new Context(); newCtx.setProject(ctx.getProject());
		 * newCtx.put("Year", numOfYears); newCtx.put("PartTime","N");
		 * newCtx.put("PolicyKey",ctx.get("PolicyKey"));
		 * 
		 * 
		 * DBUtils.executeDBOperation(newCtx, "ClaimAgeLW", "1"); } } } }
		 */

		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"Policy.findByKey", ctx);

		Map policyMap = null;
		if (obj != null && obj instanceof Map)
			policyMap = (Map) obj;
		else
			return;

		Object policyEffectiveDate = policyMap.get("PolicyEffectiveDate");
		String strDate = null;

		if (policyEffectiveDate != null) {

			strDate = policyEffectiveDate.toString();
		}

		Date policyDate = LawyersDateUtils.convertStringToDate(strDate,
				"yyyy-MM-dd");

		List attorneyList = null;
		Object attorneyDetailList = ctx.get("firm_list_4");

		if (attorneyDetailList != null && attorneyDetailList instanceof List)
			attorneyList = (List) attorneyDetailList;
		else
			return;

		if (attorneyList != null && attorneyList.size() > 0) {

			for (int i = 0; i < attorneyList.size(); i++) {

				Map attorneyMap = (Map) attorneyList.get(i);

				Object attorneyPriorActDate = attorneyMap
						.get("AttorneyPriorActDate");

				Date attorneyDate = LawyersDateUtils.convertStringToDate(
						attorneyPriorActDate.toString(), "yyyy-MM-dd");
				long daysdiff = LawyersDateUtils.calculateDiffInDays(
						attorneyDate, policyDate);

				long claimAge = 0;

				if (LawyersValidationUtils.isLeapYear(policyDate))
					claimAge = daysdiff / 366;
				else
					claimAge = daysdiff / 365;

				Context newCtx = new Context();
				newCtx.setProject(ctx.getProject());
				newCtx.put("Year", claimAge);
				newCtx.put("PartTime", "N");
				newCtx.put("PolicyKey", ctx.get("PolicyKey"));
		    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
		    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));

				DBUtils.executeDBOperation(newCtx, "ClaimAgeLW", "1");

			}

		}

	}

	/**
	 * This method will caculate the claim age for the full quote
	 * 
	 * @param ctx
	 * @throws Exception
	 */

	public void calculateClaimAgeFullQuote(IContext ctx) throws Exception {
		DBUtils.executeDBOperation(ctx, "ClaimAgeLW", "2");
		
		Object objPolicy = SqlResources.getSqlMapProcessor(ctx).findByKey("Policy.findByKey", ctx);
		Map poliMap = objPolicy != null && objPolicy instanceof Map ? (Map) objPolicy: null;
		
		// To check if IsFirmCarryingProfLiabilityIns equal Y/N
		String IsFirmCarryingProfLiabilityIns = "N";
		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetIsFirmCarryingProfLiabilityIns",ctx);
		if(obj != null && obj instanceof Map){
			Map map = (Map) obj;
			IsFirmCarryingProfLiabilityIns = map.get("IsFirmHaveLawyersLiabilityInsurance") == null ? "N" : map.get("IsFirmHaveLawyersLiabilityInsurance").toString();
		}
		
		int firmStepRateYears = 0;		
		/*if("Y".equals(IsFirmCarryingProfLiabilityIns))
			firmStepRateYears = calculateFirmStepRateYears(ctx);*/
		firmStepRateYears = calculateFirmStepRateYears(ctx);

		Object objAttorney = ctx.get("firm_list_4");
		if (objAttorney != null && objAttorney instanceof List) {
			List attorneyList = (List) objAttorney;
			for (int i = 0; i < attorneyList.size(); i++) {
				Map attornMap = (Map) attorneyList.get(i);
				int attorneyStepRateYears = calculateAttorneyStepRateYears(attornMap, poliMap);	
				/*if("N".equals(IsFirmCarryingProfLiabilityIns) && firmStepRateYears == 0)
					firmStepRateYears = attorneyStepRateYears;*/
					ctx.put("AttorneyKey",attornMap.get("AttorneyKey"));
				int claimAge = getClaimAge(attorneyStepRateYears, firmStepRateYears);	
				
				saveClaimAgeDataFullQuote(ctx, claimAge);

			}
		}
	}

	public boolean checkIfPriorActDateFull(Map proLiabInsuranceMap)
			throws Exception {

		boolean isPriorActDateFull = false;

		if (proLiabInsuranceMap.get("IsPriorActDateFull") != null
				&& "Y".equals(proLiabInsuranceMap.get("IsPriorActDateFull")
						.toString()))
			isPriorActDateFull = true;

		return isPriorActDateFull;

	}

	public static void populateLastUpdateTimeStamp(IContext ctx)
			throws Exception {
//		RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");
		//if (ctx.get("LastUpdateTimeStamp") != null)
			ctx.put("UploadedTime", new Timestamp(new Date().getTime()).toString());
	}

	/**
	 * This will set into context the value of TransactionSequence
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void setPolicyTransaction(IContext ctx) throws Exception {

		Object objPolicy = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetPolicyListAll", ctx);

		if (objPolicy != null && objPolicy instanceof List) {
			List objList = (List) objPolicy;

			Map objMap = (Map) objList.get(0);

			ctx.put("TransactionSequence", objMap.get("TransactionSequence"));
			ctx.put("CoverageSequence", objMap.get("CoverageSequence"));
			ctx.put("PolicyKey", objMap.get("PolicyKey"));

		}

	}

	/**
	 * This will set claim Age of LawyersAttorney to Zero if professional
	 * Liability insurance is null or 'N'
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void setClaimAgeToZero(IContext ctx) throws Exception {

		// fetch Attorney data
		Object attorneyDetailList = ctx.get("firm_list_4");

		List attorneyList = null;
		if (attorneyDetailList != null && attorneyDetailList instanceof List)
			attorneyList = (List) attorneyDetailList;

		if (attorneyList != null && attorneyList.size() > 0) {

			for (int i = 0; i < attorneyList.size(); i++) {

				ctx.put("ClaimAge_" + (i + 1), 0);
			}

		}

	}

	public int calculateFirmStepRateYears(IContext ctx) throws Exception {
		int firmStepRateYears = 0;
		
		Object objProLiability = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.coverageviewgetProfessionalLiabilityInsDetailLW",ctx);		
		if (objProLiability != null && objProLiability instanceof Map) {
			Map proLiabInsuranceMap = (Map) objProLiability;

			String isPriorActDateFull = proLiabInsuranceMap
					.get("IsPriorActDateFull") != null ? proLiabInsuranceMap
					.get("IsPriorActDateFull").toString() : null;

			if (isPriorActDateFull != null && "Y".equals(isPriorActDateFull)) {
				firmStepRateYears = Integer.parseInt(proLiabInsuranceMap.get(
						"FirmYear").toString());
				return firmStepRateYears;

			} else {

				Object objPolicy = SqlResources.getSqlMapProcessor(ctx)
						.findByKey("Policy.findByKey", ctx);

				Map poliMap = objPolicy != null && objPolicy instanceof Map ? (Map) objPolicy
						: null;

				firmStepRateYears = daysDifferentInEffectiveAndPriorDate(
						proLiabInsuranceMap, poliMap);

			}
		}
		return firmStepRateYears;
	}

	public int daysDifferentInEffectiveAndPriorDate(Map liabilityMap,
			Map policyMap) throws Exception {

		int firmStepRateYears = 0;
		if (liabilityMap != null && policyMap != null) {

			Date priorActDate = liabilityMap.get("PriorActDatePross") != null ? LawyersDateUtils
					.convertStringToDate(liabilityMap.get("PriorActDatePross")
							.toString(), "yyyy-MM-dd") : null;
			Date policyEffectiveDate = policyMap.get("PolicyEffectiveDate") != null ? LawyersDateUtils
					.convertStringToDate(policyMap.get("PolicyEffectiveDate")
							.toString(), "yyyy-MM-dd") : null;

			if (priorActDate != null && policyEffectiveDate != null) {
				firmStepRateYears = getStepRateYearBetweenTwoDate(priorActDate,
						policyEffectiveDate);
			}

			// if (priorActDate != null && policyEffectiveDate != null) {
			//
			// long daysDiff = LawyersDateUtils.calculateDiffInDays(
			// policyEffectiveDate, priorActDate);
			//
			// double diff = Double.parseDouble(String.valueOf(daysDiff));
			//
			// //
			// int numberOfLeapYear =
			// getNumberOfNonLeapYears(priorActDate,policyEffectiveDate);
			// diff = diff + numberOfLeapYear ;
			//
			// double year = diff / 366;
			// firmStepRateYears = (int) Math.ceil(year);

			// if(LawyersValidationUtils.isLeapYear(policyEffectiveDate)){
			// double year = diff / 366;
			// firmStepRateYears = (int) Math.ceil(year);
			// }else{
			// double year = diff / 365;
			// firmStepRateYears = (int) Math.ceil(year);
			// }

			// }
		}
		return firmStepRateYears;
	}

	public int getStepRateYearBetweenTwoDate(Date startDate, Date endDate) {
		int startYear = startDate.getYear();
		int endYear = endDate.getYear();
		int startMonth = startDate.getMonth();
		int endMonth = endDate.getMonth();
		int startDay = startDate.getDate();
		int endDay = endDate.getDate();

		int stepYear = endYear - startYear;

		int stepmonth = endMonth - startMonth;
		if (stepmonth > 0)
			stepYear = stepYear + 1;
		else if (stepmonth == 0) {
			int stepDay = endDay - startDay;
			if (stepDay > 0) {
				stepYear = stepYear + 1;
			}

		}
		return stepYear;
	}

	public int getNumberOfNonLeapYears(Date date1, Date date2) throws Exception {

		int count = 0;
		int year1 = date1.getYear();
		int year2 = date2.getYear();
		while (year1 <= year2) {

			if (!isLeapYear(year1)) {
				count++;
			}
			year1++;
		}

		return count;
	}

	public int calculateAttorneyStepRateYears(Map attorneyMap, Map policyMap)
			throws Exception {

		int attorneyStepRateYears = 0;
		if (policyMap != null && attorneyMap != null) {

			Date policyEffectiveDate = policyMap.get("PolicyEffectiveDate") != null ? LawyersDateUtils.convertStringToDate(policyMap.get("PolicyEffectiveDate").toString(), "yyyy-MM-dd") : null;
			Date attorneyJoinedDate = attorneyMap.get("AttorneyPriorActDate") != null ? LawyersDateUtils.convertStringToDate(attorneyMap.get("AttorneyPriorActDate").toString(),
							"yyyy-MM-dd") : null;

			if (policyEffectiveDate != null && attorneyJoinedDate != null) {
				attorneyStepRateYears = getStepRateYearBetweenTwoDate(
						attorneyJoinedDate, policyEffectiveDate);
			}

			// long daysDiff = LawyersDateUtils.calculateDiffInDays(
			// policyEffectiveDate, attorneyJoinedDate);
			//
			// double diff = Double.parseDouble(String.valueOf(daysDiff));
			//
			// int numberOfLeapYears =
			// getNumberOfNonLeapYears(attorneyJoinedDate, policyEffectiveDate);
			// diff = diff + numberOfLeapYears ;
			//
			// double year = diff / 366;
			// attorneyStepRateYears = (int) Math.ceil(year);

			// if(LawyersValidationUtils.isLeapYear(policyEffectiveDate)){
			// double year = diff / 366;
			// attorneyStepRateYears = (int) Math.ceil(year);
			// }else{
			// double year = diff / 365;
			// attorneyStepRateYears = (int) Math.ceil(year);
			// }

			if (attorneyStepRateYears > 5) {

				attorneyStepRateYears = 5;
			}

		}
		return attorneyStepRateYears;
	}

	public int getClaimAge(int attorneyStepRateYears, int firmStepRateYears) {

		if (attorneyStepRateYears < 0)
			attorneyStepRateYears = 0;

		if (firmStepRateYears < 0)
			attorneyStepRateYears = 0;

		if (attorneyStepRateYears < firmStepRateYears)
			return attorneyStepRateYears;
		else
			return firmStepRateYears;

	}

	public void saveClaimAgeDataFullQuote(IContext ctx, int claimAge)
			throws Exception {

		Context newCtx = new Context();
		newCtx.setProject(ctx.getProject());
		newCtx.put("PolicyKey", ctx.get("PolicyKey"));
		newCtx.put("Year", claimAge);
		newCtx.put("PartTime", "");
    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
    	newCtx.put("AttorneyKey",ctx.get("AttorneyKey"));

		Object obj = DBUtils.executeDBOperation(newCtx, "ClaimAgeLW", "1");

	}

	public static void isFirmReferredAgent(IContext ctx) throws Exception {

		String IsFirmReferred = "N";

		Object objState = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"State.findByKey", ctx);

		Map stateMap = new HashMap();
		if (objState != null && objState instanceof Map)
			stateMap = (Map) objState;

		String isReferredState = stateMap.get("IsStateReferred") != null ? stateMap
				.get("IsStateReferred").toString() : "";

		if (isReferredState.equals("Y"))
			IsFirmReferred = "Y";

		ctx.put("IsFirmReferred", IsFirmReferred);

		SqlResources.getSqlMapProcessor(ctx).update(
				"SqlStmts.UserStatementsaveAccountstmtsupdateFirmLW", ctx);

	}

	public static void checkFirmWithEmailExist(IContext ctx) throws Exception {

		boolean isFirmExist = false;
		boolean flag = false;

		if (ctx.get("AccountEmail") != null) {

			List listFirm = SqlResources
					.getSqlMapProcessor(ctx)
					.select("SqlStmts.UserStatementsaveAccountstmtsgetAccountEmailDetails",
							ctx);

			if (listFirm != null && listFirm.size() > 0)
				isFirmExist = true;

			List listFirmEmail = null;
			if (isFirmExist)
				listFirmEmail = SqlResources.getSqlMapProcessor(ctx).select(
						"Account.getExistingPolicy", ctx);

			if (listFirmEmail != null && listFirmEmail.size() > 0) {

				ctx.put("SubSearch_firm_list_01", listFirmEmail);
				ctx.put("showSubList", "Y");
				populateError(ctx, "AccountEmail",
						"This user already exists, please contact 1-877-569-4111 for any assistance");
			}

		}
	}

	public static void checkNegativeData(String str, String field, IContext ctx)
			throws Exception {

		if (str != null) {
			int data = Integer.parseInt(str);
			if (data < 0)
				populateError(ctx, field, "Negative data is not allowed");
		}

	}

	public static void populatePolicyForm(IContext ctx) throws Exception {	
		//To set new filing flag
		boolean isRatingNew = false;        
		Object objRatingRule = RuleUtils.executeRule(ctx,"LawyersRule.checkNewFiling");
        if (objRatingRule != null && objRatingRule instanceof Boolean) {
        	isRatingNew = (Boolean) objRatingRule;
        }
        
		if(isRatingNew){
			if("Y".equals(ctx.get("IsNewFiling2020"))){
				ctx.put("IsNewFiling2020", "Y");
				ctx.put("IsNewFiling", "Y");
			} else if("Y".equals(ctx.get("IsNewFiling"))){
				ctx.put("IsNewFiling", "Y");
			}
		}
		else{
			ctx.put("IsNewFiling2020", "N");
			ctx.put("IsNewFiling", "N");
		}
		
		String IsNewFiling_form = "N";
		if("Y".equals(ctx.get("IsNewFiling")))
			IsNewFiling_form = "Y";
		
		Context newCtx = new Context();
		newCtx.putAll(ctx);		
		
		newCtx.put("PolicyKey_form", ctx.get("PolicyKey") !=null ? ctx.get("PolicyKey").toString(): ctx.get("PolicyKey"));
		newCtx.put("StateCode_form", ctx.get("StateCode") != null ? ctx.get("StateCode").toString() : ctx.get("StateCode"));
		newCtx.put("PolicyEffectiveDate_form", ctx.get("PolicyEffectiveDate") != null ? ctx.get("PolicyEffectiveDate").toString() : ctx.get("PolicyEffectiveDate"));
		newCtx.put("IsFinalisedOption_form", "N");
		newCtx.put("IsNewFiling_form", IsNewFiling_form);
		
		logger.debug("Going to execute the stored procedure for Form Attachment");
		SqlResources.getSqlMapProcessor(newCtx).select("AdditionalFormDataLW.LawyersFormAttachment_p", newCtx);
		logger.debug("SP has been execute for the Form Attachment");
		
//		List listForm = SqlResources.getSqlMapProcessor(ctx).select(
//				"SqlStmts.UserStatementManualBoQueriesDateSpecificFormQuery",
//				ctx);
//		List listStateForm = SqlResources
//				.getSqlMapProcessor(ctx)
//				.select("SqlStmts.UserStatementManualBoQueriesDateSpecificFormQueryForState",
//						ctx);
//
//		boolean dollarDefenseFlag = false;
//		String IsClaimExpensesType = "N";
//		String IsClaimOptionType = "N";
//		Context newctx = null;
//
//		List quotesList = SqlResources.getSqlMapProcessor(ctx).select(
//				"SqlStmts.UserStatementpdfquotelettergetQuoteOptedList", ctx);
//
//		boolean IsClaimExpensesTypeFlag = false;
//		boolean IsClaimOptionTypeFlag = false;
//		boolean IsManualPremumFlag = false;
//
//		if (quotesList != null) {
//			for (int i = 0; i < quotesList.size(); i++) {
//				Map map = (Map) quotesList.get(i);
//				if (map.get("IsDollarDefense") != null
//						&& "Y".equals(map.get("IsDollarDefense").toString()))
//					dollarDefenseFlag = true;
//
//				// dollarDefenseFlag = false;
//				if (map.get("IsClaimExpensesType") != null
//						&& "Y".equals(map.get("IsClaimExpensesType").toString()))
//					IsClaimExpensesTypeFlag = true;
//				if (map.get("IsClaimOptionType") != null
//						&& "Y".equals(map.get("IsClaimOptionType").toString()))
//					IsClaimOptionTypeFlag = true;
//
//				if (map.get("IsManualPremium") != null
//						&& "Y".equals(map.get("IsManualPremium").toString()))
//					IsManualPremumFlag = true;
//			}
//		}
//
//		if (IsClaimExpensesTypeFlag)
//			IsClaimExpensesType = "Y";
//		if (IsClaimOptionTypeFlag)
//			IsClaimOptionType = "Y";
//
//		if (listForm == null && listStateForm == null)
//			return;
//
//		if ("VT".equals(ctx.get("StateCode").toString())) {
//			listForm.remove(1);
//		}
//
//		if ("MA".equals(ctx.get("StateCode").toString())) {
//			if ("Y".equals(IsClaimExpensesType)) {
//				listForm.remove(1);
//			} else if ("N".equals(IsClaimExpensesType)) {
//				listForm.remove(1);
//
//				if (listStateForm != null) {
//					listStateForm.remove(0);
//				}
//
//			}
//
//			if (dollarDefenseFlag) {
//
//				if ("Y".equals(IsClaimExpensesType))
//					listForm.remove(1);
//				else if ("N".equals(IsClaimExpensesType))
//					listForm.remove(2);
//
//			} else {
//
//				if ("Y".equals(IsClaimExpensesType))
//					listForm.remove(1);
//				else if ("N".equals(IsClaimExpensesType))
//					listForm.remove(2);
//
//				if ("Y".equals(IsClaimExpensesType))
//					listStateForm.remove(1);
//				else
//					listStateForm.remove(0);
//
//			}
//		} else {
//			if ("N".equals(IsClaimExpensesType)) {
//				listForm.remove(1);
//
//			}
//			if (!dollarDefenseFlag
//					|| "NM".equals(ctx.get("StateCode").toString())
//					|| "NJ".equals(ctx.get("StateCode").toString())) {
//				if (listForm.size() == 12)
//					listForm.remove(2);
//				else if (listForm.size() == 11)
//					listForm.remove(1);
//
//			}
//		}
//
//		if ("MA".equals(ctx.get("StateCode").toString())) {
//
//			if ("Y".equals(IsClaimOptionType) && listForm.size() == 12)
//				listForm.remove(3);
//			else if ("Y".equals(IsClaimOptionType) && listForm.size() == 11)
//				listForm.remove(2);
//			else if ("Y".equals(IsClaimOptionType) && listForm.size() == 10)
//				listForm.remove(1);
//
//			else if ("N".equals(IsClaimOptionType)) {
//
//				if (listForm.size() == 12)
//					listForm.remove(3);
//				else if (listForm.size() == 11)
//					listForm.remove(2);
//				else if (listForm.size() == 10)
//					listForm.remove(1);
//
//				if (listStateForm != null) {
//					if ("MA".equals(ctx.get("StateCode").toString())
//							&& listStateForm.size() == 4)
//						listStateForm.remove(2);
//					else if ("MA".equals(ctx.get("StateCode").toString())
//							&& listStateForm.size() == 3)
//						listStateForm.remove(1);
//					else if ("MA".equals(ctx.get("StateCode").toString())
//							&& listStateForm.size() == 2)
//						listStateForm.remove(0);
//
//				}
//			}
//
//		} else {
//			if ("N".equals(IsClaimOptionType)) {
//
//				if (listForm.size() == 12)
//					listForm.remove(3);
//				else if (listForm.size() == 11)
//					listForm.remove(2);
//				else if (listForm.size() == 10)
//					listForm.remove(1);
//
//			}
//		}
//		if ("MA".equals(ctx.get("StateCode").toString())) {
//
//			if (listForm.size() == 12)
//				listForm.remove(4);
//			else if (listForm.size() == 11)
//				listForm.remove(3);
//			else if (listForm.size() == 10)
//				listForm.remove(2);
//			else if (listForm.size() == 9)
//				listForm.remove(1);
//
//		}
//		if ("MA".equals(ctx.get("StateCode").toString())) {
//
//			if (!IsManualPremumFlag) {
//				if (listStateForm.size() == 4)
//					listStateForm.remove(3);
//				else if (listStateForm.size() == 3)
//					listStateForm.remove(2);
//				else if (listStateForm.size() == 2)
//					listStateForm.remove(1);
//				else if (listStateForm.size() == 1)
//					listStateForm.remove(0);
//
//			}
//
//		}
//
//		if ("PA".equals(ctx.get("StateCode").toString())) {
//
//			boolean IsClaimWithingLimit = new LawyersValidationUtils()
//					.checkForAnyQuoteWithInsideLimit(ctx);
//			if (!IsClaimWithingLimit) {
//				listStateForm.remove(1);
//				listStateForm.remove(0);
//
//			}
//		}
//		if ("WY".equals(ctx.get("StateCode").toString())) {
//
//			boolean IsClaimWithingLimit = new LawyersValidationUtils()
//					.checkForAnyQuoteWithInsideLimit(ctx);
//			if (!IsClaimWithingLimit) {
//				listStateForm.remove(1);
//
//			}
//		}
//		if ("OH".equals(ctx.get("StateCode").toString())) {
//			if (!IsManualPremumFlag)
//				listStateForm.remove(0);
//		}
//		if ("NJ".equals(ctx.get("StateCode").toString())) {
//			// if(!IsManualPremumFlag)
//			listStateForm.remove(0);
//		}
//		if ("KY".equals(ctx.get("StateCode").toString())) {
//
//			boolean IsTaxablePremium = new LawyersValidationUtils()
//					.checkForKYMuncipalityTax(ctx);
//			if (!IsTaxablePremium)
//				listStateForm.remove(0);
//		}
//		if ("CT".equals(ctx.get("StateCode").toString())) {
//
//			if ("2".equals(ctx.get("PolicyStatusKey").toString())) {
//				listStateForm.remove(0);
//			} else {
//				listStateForm.remove(1);
//			}
//
//		}
//		if ("FL".equals(ctx.get("StateCode").toString())) {
//
//			if ("2".equals(ctx.get("PolicyStatusKey").toString())) {
//				listStateForm.remove(0);
//			} else {
//				listStateForm.remove(1);
//			}
//
//		}
//
//		// for ERP YEAR
//		if (listForm.size() == 12)
//			listForm.remove(4);
//		else if (listForm.size() == 11)
//			listForm.remove(3);
//		else if (listForm.size() == 10)
//			listForm.remove(2);
//		else if (listForm.size() == 9)
//			listForm.remove(1);
//
//		Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey(
//				"FirmLW.findByKey", ctx);
//		if (objFirm != null && objFirm instanceof Map) {
//
//			Map objFirmMap = (Map) objFirm;
//			String isOfficeSharingSuit = objFirmMap
//					.get("IsAppOfficeSharedWithAttorney") != null ? objFirmMap
//					.get("IsAppOfficeSharedWithAttorney").toString() : "";
//
//			if ("N".equals(isOfficeSharingSuit)
//					|| "".equals(isOfficeSharingSuit)) {
//
//				if (listForm.size() == 12)
//					listForm.remove(5);
//				else if (listForm.size() == 11)
//					listForm.remove(4);
//				else if (listForm.size() == 10)
//					listForm.remove(3);
//				else if (listForm.size() == 9)
//					listForm.remove(2);
//				else if (listForm.size() == 8)
//					listForm.remove(1);
//
//			}
//
//		}
//		if ("MA".equals(ctx.get("StateCode").toString())) {
//			if (listForm.size() == 12)
//				listForm.remove(9);
//			else if (listForm.size() == 11)
//				listForm.remove(8);
//			else if (listForm.size() == 10)
//				listForm.remove(7);
//			else if (listForm.size() == 9)
//				listForm.remove(6);
//			else if (listForm.size() == 8)
//				listForm.remove(5);
//			else if (listForm.size() == 7)
//				listForm.remove(4);
//		}
//
//		// listForm.remove(1);
//
//		SqlResources.getSqlMapProcessor(ctx).delete(
//				"SqlStmts.UserStatementpdfquoteletterdeletePolicyForm", ctx);
//
//		for (int i = 0; i < listForm.size(); i++) {
//			Map map = (Map) listForm.get(i);
//			newctx = new Context();
//			newctx.putAll(map);
//			newctx.setProject(ctx.getProject());
//			newctx.put("PolicyKey", ctx.get("PolicyKey"));
//			newctx.put("StateCode", ctx.get("StateCode"));
//			newctx.put("LastUpdateUserID", ctx.get("LastUpdateUserID"));
//			newctx.put("FormType", "C");
//			populateLastUpdateTimeStamp(newctx);
//
//			DBUtils.executeDBOperation(newctx, "PolicyFormLW", "1");
//		}
//
//		if (listStateForm == null)
//			return;
//
//		for (int i = 0; i < listStateForm.size(); i++) {
//			Map map = (Map) listStateForm.get(i);
//			newctx = new Context();
//			newctx.putAll(map);
//			newctx.setProject(ctx.getProject());
//			newctx.put("PolicyKey", ctx.get("PolicyKey"));
//			newctx.put("StateCode", ctx.get("StateCode"));
//			newctx.put("LastUpdateUserID", ctx.get("LastUpdateUserID"));
//
//			// logger.debug("StateCodeType-----"+map.get("StateFormType"));
//
//			newctx.put("FormType", "S");
//
//			populateLastUpdateTimeStamp(newctx);
//
//			DBUtils.executeDBOperation(newctx, "PolicyFormLW", "1");
//		}
//		newctx.remove("FormType");

	}

	public static void validateFiscalYearData(IContext ctx) throws Exception {

		String date = ctx.get("YearEndDate_0") != null ? ctx.get(
				"YearEndDate_0").toString() : "";

		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);

		if (date != null && !"".equals(date)) {

			String[] str = date.split("/");

			int month = Integer.parseInt(str[0]);
			int year = Integer.parseInt(str[1]);

			if (year > currentYear) {
				if (year - currentYear != 1)
					LawyersUtils
							.populateError(
									ctx,
									"estimate_year",
									"Fiscal Year should be current year or one year less than the current year or one year greater than the current year");
			} else if (currentYear > year) {
				if (currentYear - year != 1)
					LawyersUtils
							.populateError(
									ctx,
									"estimate_year",
									"Fiscal Year should be current year or one year less than the current year or one year greater than the current year");
			}
		}

	}

	/*
	 * public void populateLicenseNumber(IContext ctx) throws Exception {
	 * 
	 * if (ctx.get("labels_freeform_99") != null) { Map map = (Map)
	 * ctx.get("labels_freeform_99"); if (map.get("LicenseNumber") == null &&
	 * !"Y".equals(map.get("IsLicenseNumberUpdated"))) {
	 * map.put("LicenseNumber", ctx.get("Org_LicenseNumber")); SqlResources
	 * .getSqlMapProcessor(ctx) .update(
	 * "SqlStmts.UserStatementManualBoQueriesupdateIsLicenseNumberUpdated",
	 * ctx); } // map.put("labels_freeform_99", map); } }
	 */
	// public void populateLicenseNumber(IContext ctx) throws Exception {
	//
	//
	// if (ctx.get("LicenseNumber") == null
	// && !"Y".equals(ctx.get("IsLicenseNumberUpdated"))) {
	// ctx.put("LicenseNumber", ctx.get("Org_LicenseNumber"));
	// SqlResources
	// .getSqlMapProcessor(ctx)
	// .update(
	// "SqlStmts.UserStatementManualBoQueriesupdateIsLicenseNumberUpdated",
	// ctx);
	// }
	//
	//
	// }
	/*
	 * The code is used in Attachment of PolicyForm
	 */

	public static synchronized String generatePolicyNumber(IContext ctx) throws Exception {

		String prefix = "580";
		String initialPolicyNo = null;
		//Thread.sleep(800);
		getPolicyData((Context)ctx);
		Object objPNo = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"SqlStmts.UserStatementformgetMaxPolicyNo", ctx);
		if (objPNo != null && objPNo instanceof Map) {
			Map map = (Map) objPNo;
			if (map.get("PolicyNumber") != null) {
				String PolicyNumber = map.get("PolicyNumber").toString();
				logger.debug("LAST MAX POLICY NUMBER:-"+PolicyNumber+" LAST UPDATED AT"+System.currentTimeMillis());
				int index = PolicyNumber.indexOf("-");
				// int lastIndex = PolicyNumber.lastIndexOf("-");

				String number = PolicyNumber.substring(index + 1, index + 7);

				int policyNo = Integer.parseInt(number);
				policyNo = policyNo + 1;
				initialPolicyNo = new Integer(policyNo).toString();
			}
		}

		if (initialPolicyNo == null)
			initialPolicyNo = SystemProperties.getInstance().getString(
					"appl." + ctx.getProject() + ".initialPolicyNo");

		double sum = 0;
		String number = prefix + initialPolicyNo;

		for (int i = 0; i < number.length(); i++) {
			sum = sum + Integer.parseInt(number.substring(i, i + 1));
		}

		double result = sum / 9;

		String strResult = new Double(result).toString();
		int rdp = 0;
		int val = 0;
		boolean flag = false;
		int decIndex = 0;
		decIndex = strResult.lastIndexOf(".");

		if (decIndex > 0) {
			String strRDP = strResult.substring(decIndex + 1, decIndex + 2);
			rdp = Integer.parseInt(strRDP);
			val = 9 - rdp;
		}
		logger.debug("NEWLY GENERATED POLICY NUMBER:-"+prefix + "-" + initialPolicyNo + "-"+ new Integer(val).toString()+" LAST UPDATED AT"+System.currentTimeMillis());
		return prefix + "-" + initialPolicyNo + "-"+ new Integer(val).toString();
	}

	public static List populateFinalisedPolicyForm(IContext ctx)throws Exception {
		//To set new filing flag
		boolean isRatingNew = false;        
		Object objRatingRule = RuleUtils.executeRule(ctx,"LawyersRule.checkNewFiling");
        if (objRatingRule != null && objRatingRule instanceof Boolean) {
        	isRatingNew = (Boolean) objRatingRule;
        }	
        
        if(isRatingNew){
			if("Y".equals(ctx.get("IsNewFiling2020"))){
				ctx.put("IsNewFiling2020", "Y");
				ctx.put("IsNewFiling", "Y");
			} else if("Y".equals(ctx.get("IsNewFiling"))){
				ctx.put("IsNewFiling2020", "N");
				ctx.put("IsNewFiling", "Y");
			}
		}
		else{
			ctx.put("IsNewFiling2020", "N");
			ctx.put("IsNewFiling", "N");
		}
		
		String IsNewFiling_form = "N";
		if("Y".equals(ctx.get("IsNewFiling")))
			IsNewFiling_form = "Y";
		
		Context newCtx = new Context();
		newCtx.putAll(ctx);
		
		newCtx.put("PolicyKey_form", ctx.get("PolicyKey") !=null ? ctx.get("PolicyKey").toString(): ctx.get("PolicyKey"));
		newCtx.put("StateCode_form", ctx.get("StateCode") != null ? ctx.get("StateCode").toString() : ctx.get("StateCode"));
		newCtx.put("PolicyEffectiveDate_form", ctx.get("PolicyEffectiveDate") != null ? ctx.get("PolicyEffectiveDate").toString() : ctx.get("PolicyEffectiveDate"));
		newCtx.put("IsFinalisedOption_form", "Y");
		newCtx.put("IsNewFiling_form", IsNewFiling_form);
		
		logger.debug("Going to execute the stored procedure for Form Attachment");
		List listForm = SqlResources.getSqlMapProcessor(newCtx).select("AdditionalFormDataLW.LawyersFormAttachment_p", newCtx);
		logger.debug("SP has been execute for the Form Attachment");
		return listForm;
		/*
		 * List listForm = SqlResources.getSqlMapProcessor(ctx).select(
		 * "SqlStmts.UserStatementManualBoQueriesFormLW", ctx); List
		 * listStateForm = SqlResources.getSqlMapProcessor(ctx).select(
		 * "SqlStmts.UserStatementManualBoQueriesStateFormLW", ctx);
		 

		List listForm = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementManualBoQueriesDateSpecificFormQuery",
				ctx);
		List listStateForm = SqlResources
				.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementManualBoQueriesDateSpecificFormQueryForState",
						ctx);

		boolean dollarDefenseFlag = false;
		boolean IsManualPremumFlag = false;
		String IsClaimExpensesType = "N";
		String IsClaimOptionType = "N";
		String IsManualPremium = "N";
		Context newctx = null;

		Object objFinalisedQuote = SqlResources.getSqlMapProcessor(ctx)
				.findByKey("SqlStmts.UserStatementformgetFinalisedQuote", ctx);
		Map mapFinalisedQuote = null;

		if (objFinalisedQuote != null && objFinalisedQuote instanceof Map)
			mapFinalisedQuote = (Map) objFinalisedQuote;

		if (mapFinalisedQuote != null && !mapFinalisedQuote.isEmpty()) {
			if (mapFinalisedQuote.get("IsDollarDefense") != null
					&& "Y".equals(mapFinalisedQuote.get("IsDollarDefense")
							.toString()))
				dollarDefenseFlag = true;
			if (mapFinalisedQuote.get("IsClaimExpensesType") != null)
				IsClaimExpensesType = mapFinalisedQuote.get(
						"IsClaimExpensesType").toString();
			if (mapFinalisedQuote.get("IsClaimOptionType") != null)
				IsClaimOptionType = mapFinalisedQuote.get("IsClaimOptionType")
						.toString();
			if (mapFinalisedQuote.get("IsManualPremium") != null)
				IsManualPremium = mapFinalisedQuote.get("IsManualPremium")
						.toString();
		}

		if ("Y".equals(IsManualPremium))
			IsManualPremumFlag = true;

		if (listForm == null && listStateForm == null)
			return;

		if ("VT".equals(ctx.get("StateCode").toString())) {
			listForm.remove(1);
		}

		if ("MA".equals(ctx.get("StateCode").toString())) {
			if ("Y".equals(IsClaimExpensesType)) {
				listForm.remove(1);
			} else if ("N".equals(IsClaimExpensesType)) {
				listForm.remove(1);

				if (listStateForm != null) {
					listStateForm.remove(0);
				}

			}

			if (dollarDefenseFlag) {

				if ("Y".equals(IsClaimExpensesType))
					listForm.remove(1);
				else if ("N".equals(IsClaimExpensesType))
					listForm.remove(2);

			} else {

				if ("Y".equals(IsClaimExpensesType))
					listForm.remove(1);
				else if ("N".equals(IsClaimExpensesType))
					listForm.remove(2);

				if ("Y".equals(IsClaimExpensesType))
					listStateForm.remove(1);
				else
					listStateForm.remove(0);

			}
		} else {
			if ("N".equals(IsClaimExpensesType)) {
				listForm.remove(1);

			}
			if (!dollarDefenseFlag
					|| "NM".equals(ctx.get("StateCode").toString())
					|| "NJ".equals(ctx.get("StateCode").toString())) {
				if (listForm.size() == 12)
					listForm.remove(2);
				else if (listForm.size() == 11)
					listForm.remove(1);

			}
		}

		if ("MA".equals(ctx.get("StateCode").toString())) {

			if ("Y".equals(IsClaimOptionType) && listForm.size() == 12)
				listForm.remove(3);
			else if ("Y".equals(IsClaimOptionType) && listForm.size() == 11)
				listForm.remove(2);
			else if ("Y".equals(IsClaimOptionType) && listForm.size() == 10)
				listForm.remove(1);

			else if ("N".equals(IsClaimOptionType)) {

				if (listForm.size() == 12)
					listForm.remove(3);
				else if (listForm.size() == 11)
					listForm.remove(2);
				else if (listForm.size() == 10)
					listForm.remove(1);

				if (listStateForm != null) {
					if ("MA".equals(ctx.get("StateCode").toString())
							&& listStateForm.size() == 4)
						listStateForm.remove(2);
					else if ("MA".equals(ctx.get("StateCode").toString())
							&& listStateForm.size() == 3)
						listStateForm.remove(1);
					else if ("MA".equals(ctx.get("StateCode").toString())
							&& listStateForm.size() == 2)
						listStateForm.remove(0);

				}
			}

		} else {
			if ("N".equals(IsClaimOptionType)) {

				if (listForm.size() == 12)
					listForm.remove(3);
				else if (listForm.size() == 11)
					listForm.remove(2);
				else if (listForm.size() == 10)
					listForm.remove(1);

			}
		}
		if ("MA".equals(ctx.get("StateCode").toString())) {

			if (listForm.size() == 12)
				listForm.remove(4);
			else if (listForm.size() == 11)
				listForm.remove(3);
			else if (listForm.size() == 10)
				listForm.remove(2);
			else if (listForm.size() == 9)
				listForm.remove(1);

		}
		if ("MA".equals(ctx.get("StateCode").toString())) {

			if (!IsManualPremumFlag) {
				if (listStateForm.size() == 4)
					listStateForm.remove(3);
				else if (listStateForm.size() == 3)
					listStateForm.remove(2);
				else if (listStateForm.size() == 2)
					listStateForm.remove(1);
				else if (listStateForm.size() == 1)
					listStateForm.remove(0);

			}

		}

		if ("PA".equals(ctx.get("StateCode").toString())) {

			boolean IsClaimWithingLimit = new LawyersValidationUtils()
					.checkForFinalizedQuoteWithInsideLimit(ctx);
			if (!IsClaimWithingLimit) {
				listStateForm.remove(1);
				listStateForm.remove(0);

			}
		}
		if ("WY".equals(ctx.get("StateCode").toString())) {

			boolean IsClaimWithingLimit = new LawyersValidationUtils()
					.checkForFinalizedQuoteWithInsideLimit(ctx);
			if (!IsClaimWithingLimit) {
				listStateForm.remove(1);

			}
		}
		if ("OH".equals(ctx.get("StateCode").toString())) {
			if (!IsManualPremumFlag)
				listStateForm.remove(0);
		}
		if ("NJ".equals(ctx.get("StateCode").toString())) {
			// if(!IsManualPremumFlag)
			listStateForm.remove(0);
		}
		if ("KY".equals(ctx.get("StateCode").toString())) {

			boolean IsTaxablePremium = new LawyersValidationUtils()
					.checkForKYMuncipalityTax(ctx);
			if (!IsTaxablePremium)
				listStateForm.remove(0);
		}

		if ("CT".equals(ctx.get("StateCode").toString())) {

			listStateForm.remove(0);
			listStateForm.remove(0);
//			if ("2".equals(ctx.get("PolicyStatusKey").toString())) {
//				listStateForm.remove(0);
//			} else {
//				listStateForm.remove(1);
//			}

		}
		if ("FL".equals(ctx.get("StateCode").toString())) {

			listStateForm.remove(0);
			listStateForm.remove(0);
//			if ("2".equals(ctx.get("PolicyStatusKey").toString())) {
//				listStateForm.remove(0);
//			} else {
//				listStateForm.remove(1);
//			}

		}

		// for ERP YEAR
		if (listForm.size() == 12)
			listForm.remove(4);
		else if (listForm.size() == 11)
			listForm.remove(3);
		else if (listForm.size() == 10)
			listForm.remove(2);
		else if (listForm.size() == 9)
			listForm.remove(1);

		Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"FirmLW.findByKey", ctx);
		if (objFirm != null && objFirm instanceof Map) {

			Map objFirmMap = (Map) objFirm;
			String isOfficeSharingSuit = objFirmMap
					.get("IsAppOfficeSharedWithAttorney") != null ? objFirmMap
					.get("IsAppOfficeSharedWithAttorney").toString() : "";

			if ("N".equals(isOfficeSharingSuit)
					|| "".equals(isOfficeSharingSuit)) {

				if (listForm.size() == 12)
					listForm.remove(5);
				else if (listForm.size() == 11)
					listForm.remove(4);
				else if (listForm.size() == 10)
					listForm.remove(3);
				else if (listForm.size() == 9)
					listForm.remove(2);
				else if (listForm.size() == 8)
					listForm.remove(1);

			}

		}

		/*
		 * boolean appRemove = false; if
		 * ("CT".equals(ctx.get("StateCode").toString()) ||
		 * "IA".equals(ctx.get("StateCode").toString()) ||
		 * "NH".equals(ctx.get("StateCode").toString())) { listForm.remove(1);
		 * appRemove = true; }
		 * 
		 * if ("MA".equals(ctx.get("StateCode").toString())) { if
		 * ("Y".equals(IsClaimExpensesType)) { if (appRemove)
		 * listForm.remove(1); else listForm.remove(2); } else if
		 * ("N".equals(IsClaimExpensesType)) {
		 * 
		 * if (appRemove) listForm.remove(1); else listForm.remove(2);
		 * 
		 * if (listStateForm != null) { listStateForm.remove(0); } }
		 * 
		 * if (dollarDefenseFlag) {
		 * 
		 * if (appRemove && "Y".equals(IsClaimExpensesType)) listForm.remove(1);
		 * else if (!appRemove && "Y".equals(IsClaimExpensesType))
		 * listForm.remove(2); else if (appRemove &&
		 * "N".equals(IsClaimExpensesType)) listForm.remove(2); } else {
		 * 
		 * if (appRemove && "Y".equals(IsClaimExpensesType)) listForm.remove(1);
		 * else if (!appRemove && "Y".equals(IsClaimExpensesType))
		 * listForm.remove(2); else if (appRemove &&
		 * "N".equals(IsClaimExpensesType)) listForm.remove(2);
		 * 
		 * if ("Y".equals(IsClaimExpensesType)) listStateForm.remove(1); else
		 * listStateForm.remove(0); } } else { if
		 * ("N".equals(IsClaimExpensesType)) { if (listForm.size() == 8)
		 * listForm.remove(2); else if (listForm.size() == 7)
		 * listForm.remove(1); }
		 * 
		 * if (!dollarDefenseFlag) { if (listForm.size() == 8)
		 * listForm.remove(3); else if (listForm.size() == 7)
		 * listForm.remove(2); else if (listForm.size() == 6)
		 * listForm.remove(1); } }
		 * 
		 * if ("MA".equals(ctx.get("StateCode").toString())) {
		 * 
		 * if ("Y".equals(IsClaimOptionType) && listForm.size() == 8)
		 * listForm.remove(4); else if ("Y".equals(IsClaimOptionType) &&
		 * listForm.size() == 7) listForm.remove(3); else if
		 * ("Y".equals(IsClaimOptionType) && listForm.size() == 6)
		 * listForm.remove(2); else if ("Y".equals(IsClaimOptionType) &&
		 * listForm.size() == 5) listForm.remove(1);
		 * 
		 * else if ("N".equals(IsClaimOptionType)) {
		 * 
		 * if (listForm.size() == 8) listForm.remove(4); else if
		 * (listForm.size() == 7) listForm.remove(3); else if (listForm.size()
		 * == 6) listForm.remove(2); else if (listForm.size() == 5)
		 * listForm.remove(1);
		 * 
		 * if (listStateForm != null) { if
		 * ("MA".equals(ctx.get("StateCode").toString()) && listStateForm.size()
		 * == 4) listStateForm.remove(2); else if
		 * ("MA".equals(ctx.get("StateCode").toString()) && listStateForm.size()
		 * == 3) listStateForm.remove(1); else if
		 * ("MA".equals(ctx.get("StateCode").toString()) && listStateForm.size()
		 * == 2) listStateForm.remove(0); } } } else { if
		 * ("N".equals(IsClaimOptionType)) {
		 * 
		 * if (listForm.size() == 8) listForm.remove(4); else if
		 * (listForm.size() == 7) listForm.remove(3); else if (listForm.size()
		 * == 6) listForm.remove(2); else if (listForm.size() == 5)
		 * listForm.remove(1); } } if
		 * ("MA".equals(ctx.get("StateCode").toString())) {
		 * 
		 * if (listForm.size() == 8) listForm.remove(5); else if
		 * (listForm.size() == 7) listForm.remove(4); else if (listForm.size()
		 * == 6) listForm.remove(3); else if (listForm.size() == 5)
		 * listForm.remove(2); else if (listForm.size() == 4)
		 * listForm.remove(1); } if
		 * ("MA".equals(ctx.get("StateCode").toString())) {
		 * 
		 * if (!IsManualPremumFlag) { if (listStateForm.size() == 4)
		 * listStateForm.remove(3); else if (listStateForm.size() == 3)
		 * listStateForm.remove(2); else if (listStateForm.size() == 2)
		 * listStateForm.remove(1); else if (listStateForm.size() == 1)
		 * listStateForm.remove(0); } }
		 * 
		 * if ("PA".equals(ctx.get("StateCode").toString())) {
		 * 
		 * boolean IsClaimWithingLimit = new LawyersValidationUtils()
		 * .checkForFinalizedQuoteWithInsideLimit(ctx); if
		 * (!IsClaimWithingLimit) { listStateForm.remove(1);
		 * listStateForm.remove(0); } } if
		 * ("WY".equals(ctx.get("StateCode").toString())) {
		 * 
		 * boolean IsClaimWithingLimit = new LawyersValidationUtils()
		 * .checkForFinalizedQuoteWithInsideLimit(ctx); if
		 * (!IsClaimWithingLimit) { listStateForm.remove(0); } } if
		 * ("OH".equals(ctx.get("StateCode").toString())) { if
		 * (!IsManualPremumFlag) listStateForm.remove(0); } if
		 * ("NJ".equals(ctx.get("StateCode").toString())) { //
		 * if(!IsManualPremumFlag) listStateForm.remove(0); } if
		 * ("KY".equals(ctx.get("StateCode").toString())) {
		 * 
		 * boolean IsTaxablePremium = new
		 * LawyersValidationUtils().checkForKYMuncipalityTax(ctx); if
		 * (!IsTaxablePremium) listStateForm.remove(0); } // for ERP YEAR if
		 * (listForm.size() == 8) listForm.remove(5); else if (listForm.size()
		 * == 7) listForm.remove(4); else if (listForm.size() == 6)
		 * listForm.remove(3); else if (listForm.size() == 5)
		 * listForm.remove(2); else if (listForm.size() == 4)
		 * listForm.remove(1); else if (listForm.size() == 3)
		 * listForm.remove(0);
		 * 
		 * Object objFirm =
		 * SqlResources.getSqlMapProcessor(ctx).findByKey("FirmLW.findByKey",
		 * ctx); if (objFirm != null && objFirm instanceof Map) {
		 * 
		 * Map objFirmMap = (Map) objFirm; String isOfficeSharingSuit =
		 * objFirmMap.get("IsAppOfficeSharedWithAttorney") != null ?
		 * objFirmMap.get("IsAppOfficeSharedWithAttorney").toString() : "";
		 * 
		 * if ("N".equals(isOfficeSharingSuit) ||
		 * "".equals(isOfficeSharingSuit)) {
		 * 
		 * if (listForm.size() == 8) listForm.remove(6); else if
		 * (listForm.size() == 7) listForm.remove(5); else if (listForm.size()
		 * == 6) listForm.remove(4); else if (listForm.size() == 5)
		 * listForm.remove(3); else if (listForm.size() == 4)
		 * listForm.remove(2); else if (listForm.size() == 3)
		 * listForm.remove(1); } }
		 */
		// listForm.remove(1);
//		SqlResources.getSqlMapProcessor(ctx).delete(
//				"SqlStmts.UserStatementpdfquoteletterdeletePolicyForm", ctx);
//
//		for (int i = 0; i < listForm.size(); i++) {
//			Map map = (Map) listForm.get(i);
//			newctx = new Context();
//			newctx.putAll(map);
//			newctx.setProject(ctx.getProject());
//			newctx.put("PolicyKey", ctx.get("PolicyKey"));
//			newctx.put("StateCode", ctx.get("StateCode"));
//			newctx.put("LastUpdateUserID", ctx.get("LastUpdateUserID"));
//			newctx.put("FormType", "C");
//			populateLastUpdateTimeStamp(newctx);
//
//			DBUtils.executeDBOperation(newctx, "PolicyFormLW", "1");
//		}
//
//		if (listStateForm == null)
//			return;
//
//		for (int i = 0; i < listStateForm.size(); i++) {
//			Map map = (Map) listStateForm.get(i);
//			newctx = new Context();
//			newctx.putAll(map);
//			newctx.setProject(ctx.getProject());
//			newctx.put("PolicyKey", ctx.get("PolicyKey"));
//			newctx.put("StateCode", ctx.get("StateCode"));
//			newctx.put("LastUpdateUserID", ctx.get("LastUpdateUserID"));
//			newctx.put("FormType", "S");
//
//			populateLastUpdateTimeStamp(newctx);
//			DBUtils.executeDBOperation(newctx, "PolicyFormLW", "1");
//		}
//
//		newctx.remove("FormType"); 
	}

	public static String getAdminEmailID(IContext ctx) throws Exception {

		String emailID = "";

		emailID = SystemProperties.getInstance().getString(
				"appl." + ctx.getProject() + ".admin.email");

		return emailID;

	}

	public static String getInsuredEmail(IContext ctx) throws Exception {

		String emailId = "";
		String isIssuedMailSendToInsured = "";

		try {
			isIssuedMailSendToInsured = SystemProperties.getInstance()
					.getString(
							"appl." + ctx.getProject()
									+ ".Insured.SendIssuedEmail");
		} catch (Exception e) {
			logger.error("Unable to read issued-policy mail setting", e);
		}
		if ("Y".equals(isIssuedMailSendToInsured)) {

			boolean isMailSendToInsured = isMailSendToInsured(ctx);
			if (isMailSendToInsured)
				emailId = getEmailID(ctx);

		}

		return emailId;
	}

	public static boolean isMailSendToInsured(IContext ctx) throws Exception {

		Object objState = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"State.findByKey", ctx);

		Map objStateMap = null;
		if (objState != null)
			objStateMap = (Map) objState;

		if (objStateMap != null) {

			String issueMailLW = objStateMap.get("IssueMailLwy") != null ? objStateMap
					.get("IssueMailLwy").toString() : "";

			if ("Y".equals(issueMailLW))
				return true;

		}

		/*
		 * Object objIssueMailAcc =
		 * SqlResources.getSqlMapProcessor(ctx).findByKey("State.findByKey",
		 * ctx); Object objAddress =
		 * SqlResources.getSqlMapProcessor(ctx).findByKey
		 * ("SqlStmts.firmviewgetAddressdetails", ctx);
		 * 
		 * List listIssueMailAcc = null; Map mapAddress = null;
		 * 
		 * if (objIssueMailAcc != null) listIssueMailAcc = (List)
		 * objIssueMailAcc;
		 * 
		 * if (objAddress != null) mapAddress = (Map) objAddress;
		 * 
		 * if (listIssueMailAcc == null || objAddress == null) return false;
		 * 
		 * String policyIssueEmailAdmin = "N"; String StateCode = ""; String
		 * issueStateCode = "";
		 * 
		 * 
		 * 
		 * 
		 * for (int i = 0; i < listIssueMailAcc.size(); i++) { Map mapIssueMail
		 * = (Map) listIssueMailAcc.get(i); issueStateCode =
		 * mapIssueMail.get("StateCode").toString(); StateCode =
		 * mapAddress.get("StateCode").toString(); if
		 * (issueStateCode.equals(StateCode)) { return true; } }
		 */
		return false;
	}

	// _Jaspreet PolicyForm_End

	/**
	 * This will return the LoginId of the logged in user
	 */
	public static String getLoginID(IContext ctx) throws Exception {

		String loginId = "";
		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"Account.findByKey", ctx);
		if (obj != null && obj instanceof Map) {
			Map accountMap = (Map) obj;
			if (accountMap != null) {
				loginId = accountMap.get("LoginID") != null ? accountMap.get(
						"LoginID").toString() : "";
			}

		}

		return loginId;
	}

	public void insertDataForRenewPolicy(IContext ctx)  {
		
		boolean oldFlow = false,newFlow=false;
		Object obj=null;
		try
		{
	/*	Object oldObj = RuleUtils.executeRule(ctx, "LawyersRule.isOldAppFlowRenewal");			
		Object newObj = RuleUtils.executeRule(ctx, "LawyersRule.isNewAppFlowRenewal");	
		if(oldObj != null && oldObj instanceof Boolean )
			oldFlow =(Boolean) oldObj;
		
		if(newObj != null && newObj instanceof Boolean )
			newFlow =(Boolean) newObj;
		
		if(oldFlow==true)
			 obj = ctx.get("RenewPolicy_Data");*/
		 obj = ctx.get("RenewPolicy_Data");
		
		if (obj != null) {
			List list = (List) obj;

			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);

				ctx.put("PolicyKey", map.get("PolicyKey"));
				ctx.put("VersionSequence", map.get("VersionSequence"));
				ctx.put("TransactionSequence", map.get("TransactionSequence"));
				ctx.put("CoverageSequence", map.get("CoverageSequence"));
				ctx.put("AccountKey", map.get("AccountKey"));
				ctx.put("AddressKey", map.get("AddressKey"));
			}
		}
		
		Object objPolicy = SqlResources.getSqlMapProcessor(ctx).findByKey("Policy.findByKey", ctx);
		if(objPolicy != null)
			ctx.putAll((Map)objPolicy);
		
		Object objAddress = SqlResources.getSqlMapProcessor(ctx).findByKey("Address.findByKey", ctx);
		if(objAddress != null)
			ctx.putAll((Map)objAddress);
		
		Object objPolCov = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyCoverageRenewal", ctx);
		List polCovList = null;
		if(objPolicy != null){
			polCovList = (List) objPolCov;
			Map mapPolCov = (Map) polCovList.get(0);
			ctx.putAll(mapPolCov);
		}			
		
		Object oldObj = RuleUtils.executeRule(ctx, "LawyersRule.AssignClaimExpensesAndDollarDefense");   
        if(ctx.get("IsClaimExpensesType") != null){
              SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateCoverageDataClaimExpenseTypeRenewal",ctx);
        }
        if(ctx.get("IsDollarDefense") != null){
              SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateCoverageDataDollarDefenseRenewal",ctx);
        }
        
		/*Object oldObj = RuleUtils.executeRule(ctx, "LawyersRule.AssignClaimExpensesAndDollarDefense");	
		if(ctx.get("IsClaimExpensesType") != null){
			SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateCoverageDataClaimExpenseTypeRenewal",ctx);
		}
		if(ctx.get("IsDollarDefense") != null){
			SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateCoverageDataDollarDefenseRenewal",ctx);
		}*/
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
        
	}

	public void sendAppChangeMail(IContext ctx) throws Exception {
		String bccAddress="";
		String emailID = getFirstReviewerAgentEmailID(ctx);
		if ("".equals(emailID))
			return;
		
		bccAddress=SystemProperties.getInstance().getString("mail.admin.cc.address");
		logger.debug("Mail is going for sendAppChangeMail--- ");
		
		MailSender mail = new MailSender();
		mail.setContentType("text/html");
		mail.setToAdd(emailID);
		mail.setBccAdd(bccAddress);
		
		mail.setSubject(ctx.get("AccountName").toString() + "-" + ctx.get("QuoteNumber").toString());
		mail.setBody(generateAppChangeBody(ctx));
		
		mail.sendMail((Context) ctx);
		logger.debug("Mail has sent-for sendAppChangeMail--- ");

	}

	private String generateAppChangeBody(IContext ctx) throws Exception {
		return "<table><tr><td>This firm has requested assistance with their application. Please contact them ASAP.</td></tr></table>";

	}

	public static String getFirstReviewerAgentEmailID(IContext ctx) throws Exception {
		String emailID = "";
		try {
    		List getFirstReviewerEmail = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoFirstReviewer", ctx);
    		if(getFirstReviewerEmail != null && getFirstReviewerEmail.size() > 0 && !getFirstReviewerEmail.isEmpty()){
    			Map getFirstReviewerList = (Map)getFirstReviewerEmail.get(0);
    			emailID = (getFirstReviewerList.get("AgentEmail") == null ? "" : getFirstReviewerList.get("AgentEmail").toString().trim());
    		}
    		
	    	if(!emailID.isEmpty()) {
	    		String productionEnv = "N";
				try {
					productionEnv = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production");
				} catch (Exception e) {
					logger.error("error in geeting production environment");
				}
				if ("N".equals(productionEnv))
					emailID = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.cc.email");
    		}
		} catch (Exception e) {
			throw e;
		}

		return emailID;
	}

	public void checkForPolicyKey(IContext ctx) throws Exception {

		// String orgPolicyKey="";

		if (ctx.get("orgPolicyKey") == null) {
			if (ctx.get("PolicyKey") != null
					&& !"null".equals(ctx.get("PolicyKey").toString())) {
				ctx.put("orgPolicyKey", ctx.get("PolicyKey").toString());
			}
		} else {
			ctx.put("PolicyKey", ctx.get("orgPolicyKey").toString());
		}
	}

	public void sendMailToSubProducer(IContext ctx) throws Exception {
		String mailingOnOFF = null;
		try {
			mailingOnOFF = SystemProperties.getInstance().getString(
					"Insured.sendmail");
		} catch (Exception e) {
			logger.error("Unable to read insured mail setting", e);
		}
		if (mailingOnOFF != null && "Y".equals(mailingOnOFF)) {
			// String emailID = LawyersUtils.getEmailID(ctx);
			String emailID = LawyersUtils.getAdminEmailID(ctx);
			// String emailID = "jaspreets@outlinesys.com";
			if ("".equals(emailID))
				return;

			String ccaddress = getCCAddressFromProperties(ctx);

			String producerEmail = getProducerEmailId(ctx);

			if ("".equals(producerEmail))
				producerEmail = ccaddress;

			String ccaddString = ccaddress + "," + producerEmail;

			MailSender mail = new MailSender();
			mail.setToAdd(emailID);
			mail.setCcAdd(ccaddString);
			mail.setSubject("You have successfully completed your application");
			mail.setContentType("text/html");
			mail.setBody(generateThanksLetterBodyForSubProducer(ctx));
			mail.sendProducerMail();
			logger.debug("Mail has sent---- ");

		}
	}

	private String getProducerEmailId(IContext ctx) throws Exception {

		String producerEmailId = "";
		try {
			String isprodenv = SystemProperties.getInstance().getString(
					"appl." + ctx.getProject() + ".environment");
			if ("PD".equals(isprodenv)) {
				producerEmailId = ctx.get("SPConatctEmailId") != null ? ctx
						.get("SPConatctEmailId").toString() : "";
			} else {
				producerEmailId = SystemProperties.getInstance().getString(
						"mail.subproducer.address");
			}
		} catch (Exception e) {
			logger.error("Unable to resolve producer email", e);
		}

		return producerEmailId;
	}

	private String getCCAddressFromProperties(IContext ctx) throws Exception {
		String emailID = "";
		try {
			emailID = SystemProperties.getInstance().getString(
					"mail.admin.cc.address");
		} catch (Exception e) {
			throw e;
		}

		return emailID;
	}

	private String generateThanksLetterBodyForSubProducer(IContext ctx)
			throws Exception {

		StringBuilder msg = new StringBuilder(1024);
		msg.append("<table><tr><td>");
		
		msg.append("Dear ").append(ctx.get("AccountName")).append(",<br/><br/>");
		msg.append("Thank you for beginning the Protexure Lawyers application. We appreciate the opportunity to provide you with a quote. Choosing the right plan is very important and we believe that the options we offer will provide you and your firm with peace of mind.<br/><br/>");
		msg.append("Once your have completed your application, our underwriters will begin the review process and will contact you within one business day.<br/><br/>");
		// msg = msg
		// +
		// "The security of your information is important to us. If you have not already registered please <a href="
		// + LawyersUtils.getProjectUrl(ctx)
		// + "/login.jsp?PolicyKey="
		// + ctx.get("PolicyKey").toString()
		// +
		// ">click here</a>. To review your application securely you will need to register."
		// + "<br/><br/>";
		// msg = msg + "If you have already registered, <a href="
		// + getProjectUrl(ctx) + "/lawyersrep.jsp?PolicyKey="
		// + ctx.get("PolicyKey").toString() + ">click here</a>."
		// + "<br/><br/>";
		msg.append("However, if you have any questions or concerns, please call us toll free at ");
		msg.append(ctx.get("SPPhoneNumber") != null ? ctx.get("SPPhoneNumber").toString() : "1-877-569-4111");
		msg.append(", and one of our licensed, experienced Professional Liability Specialists will assist you.");
		msg.append("<br/><br/>");

		msg.append("Thank you again for considering ");
		msg.append(ctx.get("SPContactName") != null ? ctx.get("SPContactName").toString() : "");
		msg.append(" & Protexure Lawyers for your Professional Liability needs.<br/><br/>");
		msg.append("Best regards,<br/><br/>");
		msg.append("<b>");
		msg.append(ctx.get("SPContactName") != null ? ctx.get("SPContactName").toString() : "Protexure Lawyers").append("</b><br/>");
		msg.append("<b>");
		msg.append(ctx.get("SPPhoneNumber") != null ? ctx.get("SPPhoneNumber").toString() : "1-877-569-4111").append("</b>");
		msg.append("</td></tr></table>");
		return msg.toString();
	}

	public static void checkAccountEmailExist(IContext ctx) throws Exception {

		boolean isAccountEmailExist = false;

		List listFirm = SqlResources
				.getSqlMapProcessor(ctx)
				.select("SqlStmts.UserStatementManualBoQueriescheckIfLoginIdExist",
						ctx);

		if (listFirm != null && listFirm.size() >= 1)
			isAccountEmailExist = true;

		if (isAccountEmailExist)
			populateError(ctx, "AccountEmail",
					"This user already exists, please contact 1-888-803-9898 for any assistance");

	}

	public void saveRegistrationInfo(IContext ctx) throws Exception {

		List accountList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetFirstdataInAccount",
				ctx);
		logger.debug("Number of Records per AccountID " + accountList != null ? accountList
				.size() : null);
		if (accountList != null && accountList.size() > 0) {

			Map accountMap = (Map) accountList.get(0);
			String accountKey = accountMap.get("AccountKey") != null ? accountMap
					.get("AccountKey").toString() : "";

			ctx.put("AccountKey_Register", accountKey);

		}

	}

	public static boolean validateRenewFirm(Context ctx) throws Exception {
		validateCountyForTX(ctx);
		if (ctx.get("GrossFeesPaidRenew") == null) {
			populateError(ctx, "GrossFeesPaidRenew",
					"Gross Fees Billed is a required field.");
		}
		if (ctx.get("GrossFeesPaidRenew") != null
				&& "".equals(ctx.get("GrossFeesPaidRenew"))) {
			populateError(ctx, "GrossFeesPaidRenew",
					"Gross Fees Billed is a required field.");
		}

		if (ctx.get("GrossFeesPaidRenew") != null
				&& !"".equals(ctx.get("GrossFeesPaidRenew"))) {
			ctx.put("GrossFeesPaidRenew",
					removeAmountFormat(ctx.get("GrossFeesPaidRenew")));
		}

		if (ctx.get("WorkPhone") != null && !ctx.get("WorkPhone").equals("")) {
			if (!validatePhoneNumber(ctx.get("WorkPhone").toString())) {
				populateError(ctx, "WorkPhone",
						"Phone should be atleast of 10 digits.");
			}
		}

		List list = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementdropdowndatacountyDropDown", ctx);
		if (list != null) {
			ctx.put("getCountyList", list);
		}
		List attList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.RenewFirmviewgetAttorneysList", ctx);
		if (attList == null || (attList != null && attList.size() == 0))
			populateError(ctx, "attorneyError",
					"Attorneys list is empty, atleast one record is required.");

		validateFirmPrimaryLocationList(ctx);
		validateAOPPercentage(ctx);
		validateClaimsList(ctx);
		validateAOPComment(ctx);
		cleanAop(ctx);

		List errorList = new ArrayList();
		errorList = (List) ctx.get(Constants.INET_ERRORS_LIST);

		if (errorList == null) {
			cleanAOPSupplementData(ctx);
		} else if (errorList != null
				&& (errorList.size() <= 0 || errorList.isEmpty())) {
			cleanAOPSupplementData(ctx);
		}

		validateApplLawSuits(ctx);
		cleanPractice(ctx);
		validatePercentageFieldPractise(ctx);

		if (ctx.get("GrossFeesPaidRenew") != null
				&& "".equals(ctx.get("GrossFeesPaidRenew"))) {
			ctx.put("GrossFeesPaidRenew", 0);
		}
		if (ctx.get("GrossFeesPaidRenew") != null
				&& !"".equals(ctx.get("GrossFeesPaidRenew"))) {
			ctx.put("GrossFeesPaidRenew",
					removeAmountFormat(ctx.get("GrossFeesPaidRenew")));
		}
		
		/**
		 * 3 March 2015
		 * Commenting this code as we do not require to delete the claims information,
		 * even if the accountrep/insured answers No to the below fields. 
		 */
		
		/*if (ctx.get("IsPersonnelBeSubOfAnyInvest") != null
				&& ctx.get("IsPersonnelBeSubOfAnyInvest").equals("N")) {
			ctx.put("PersonnelBeSubOfAnyInvestDate", "");
			ctx.put("PersonnelBeSubOfAnyInvestDetails", "");
		}
		if ((ctx.get("IsLawyerProfLiabClaimAgntAppl") != null && ctx.get(
				"IsLawyerProfLiabClaimAgntAppl").equals("N"))
				&& (ctx.get("IsAnyActOmmBecomeClaimAgntFirm") != null && ctx
						.get("IsAnyActOmmBecomeClaimAgntFirm").equals("N"))) {
			SqlResources
					.getSqlMapProcessor(ctx)
					.delete("SqlStmts.UserStatementcheckAjaxListdeleteProfLiabClaimAgnstFirmLWList",
							ctx);
		} */
		
		/*
		 * boolean isAgent = false; Object objAgentRule =
		 * RuleUtils.executeRule(ctx, "LawyersRule.isAgent"); if (objAgentRule
		 * != null && objAgentRule instanceof Boolean) { isAgent = (Boolean)
		 * objAgentRule; }
		 * 
		 * if(isAgent){ boolean isRated = false ; if(attList != null &&
		 * attList.size() > 0) { for(int i = 0 ; i < attList.size() ; i++){ Map
		 * map = (Map)attList.get(i); if(map.get("IsNonRatedAttorney") != null
		 * && "Y".equals(map.get("IsNonRatedAttorney").toString())){
		 * 
		 * }else{ isRated = true ; break ; } }
		 * 
		 * if(!isRated) populateError(ctx, "attorneyError",
		 * "Atleast one attorney should be a rated."); }
		 * 
		 * 
		 * }
		 * 
		 * boolean flag = false; Object obj = RuleUtils.executeRule(ctx,
		 * "LawyersRule.validateEmailOnFirmSave"); if (obj != null && obj
		 * instanceof Boolean) { flag = (Boolean) obj;
		 * 
		 * if (flag) { checkFirmWithEmailExist(ctx); }
		 * 
		 * }
		 * 
		 * validateBusinessStateOnContinue(ctx);
		 * validateEstimateForCurrentYear(ctx);
		 * 
		 * cleanFirm(ctx); trimFirmApplicantName(ctx); validateCountyField(ctx);
		 * validateCityField(ctx); validateFiscalYearDate(ctx);
		 * validateFiscalYearData(ctx); if (ctx.get("StateCode") != null &&
		 * "MO".equals(ctx.get("StateCode").toString()))
		 * cleanDataForMissouri(ctx);
		 * 
		 * LawyersValidationUtils.validateEmail(ctx, "AccountEmail", ctx
		 * .get("AccountEmail") != null ? ctx.get("AccountEmail")
		 * .toString().trim() : "");
		 */
		return true;
	}
	
	public void sendSignAndPayMail(IContext ctx) throws Exception {
		String emailID = getEmailID(ctx);
		String bccAddress="";
		
		if ("".equals(emailID))
			return;
		
		MailSender mail = new MailSender();
		mail.setToAdd(emailID);
		mail.setCcAdd(getFirstReviewerAgentEmailID(ctx));
		if("".equals(bccAddress))
			bccAddress=SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.bcc.email");
		mail.setBccAdd(bccAddress);
		mail.setSubject("Protexure Insurance - Sign and Pay ");
		mail.setContentType("text/html");
		mail.setBody(generateSignAndPayMailBody(ctx));
		mail.sendMail((Context) ctx);
		logger.debug("Sign and Pay email sent.");

	}
	public void sendSignAndPayMailforSubProducer(IContext ctx) throws Exception {
		
		
		
		
		String mailingOnOFF = null;	String bccAddress="";
		try {
			mailingOnOFF = SystemProperties.getInstance().getString(
					"Insured.sendmail");
		} catch (Exception e) {
			logger.error("Unable to read insured mail setting", e);
		}
		if (mailingOnOFF != null && "Y".equals(mailingOnOFF)) {
			String emailID = getSubProducerEmailID(ctx);
			// String emailID = "jaspreets@outlinesys.com";
			if ("".equals(emailID))
				return;
			
			//MailSender mail = new MailSender();
//			mail.setToAdd(emailID);
//			mail.setFromAdd(emailID);
//			mail.setCcAdd(emailID);
			/*mail.setSubject("Thank you for your interest in Lawyers Application By Subproducer.");
			mail.setContentType("text/html");
			mail.setBody(generateThanksLetterBodySubProducerFlow(ctx));
			mail.sendMailtoSub(ctx);
			*/
			String ccAddress= "";
			String productionEnv = "Y";
			try {
				productionEnv = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production");
			} 
			catch (Exception e) 
			{
				logger.error("error in geeting production environment");
			}
			/*
			 * if ("N".equals(productionEnv))
			 * ccAddress=SystemProperties.getInstance().getString("mail.admin.cc.address");
			 * else ccAddress =
			 * ctx.get("Producer_email")!=null?ctx.get("Producer_email").toString():
			 * SystemProperties.getInstance().getString("mail.adminsub.cc.address");
			 */
		
			if("".equals(bccAddress))
				bccAddress=SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.bcc.email");
			
			
			ctx.put("toAddress",emailID);
			ctx.put("bccAddress",bccAddress);
			ctx.put("subject",ctx.get("ProducerName")+" - Sign and Pay ");
			ctx.put("body", generateSignAndPayMailBodyforSubProducer(ctx));
			//ctx.put(key, value);
			SubproducerMailer.sendEmailAsSubProducer((Context)ctx);
//			

//		}
		
//		String emailID = getEmailID(ctx);
//		if ("".equals(emailID))
//			return;
//
//		logger.debug(" - - - Sending Sign and Pay Email to - - - " + emailID);
//		MailSender mail = new MailSender();
//		mail.setToAdd(emailID);
//		mail.setSubject("Thank You from Protexure");
//		mail.setContentType("text/html");
//		mail.setBody(generateSignAndPayMailBodyforSubProducer(ctx));
//		mail.sendMail((Context) ctx);
		logger.debug("Sign and Pay email sent.");
		}
	}
	public String generateSignAndPayMailBody(IContext ctx) throws Exception {
		
		LawyersValidationUtils utils = new LawyersValidationUtils();
		String encrytedPolicyKey = utils.encrypt(ctx.get("PolicyKey").toString());
		encrytedPolicyKey = URLEncoder.encode(encrytedPolicyKey,""+ StandardCharsets.UTF_8);
		/*encrytedPolicyKey = encrytedPolicyKey.replace("=", "%3D");
		encrytedPolicyKey = encrytedPolicyKey.replace("+", "%2B");*/
		
		StringBuilder msg = new StringBuilder(2048);
		msg.append("<table>");
		msg.append("<tr>");
		msg.append("<td>");
		
		msg.append(ctx.get("AccountName")).append("<br/>");
		msg.append(ctx.get("Address1")); 
		String address2 = ctx.get("Address2") != null ? " ," + ctx.get("Address2") : "";
		msg.append(address2).append("<br/>");
		msg.append(ctx.get("City")).append(" ,").append(ctx.get("StateDesc")).append(" ").append(ctx.get("Zip")).append("<br/><br/>");
		
		msg.append("Dear ").append(ctx.get("ContactPerson")).append(",<br/><br/>");
		
		msg.append("Thank you for completing your Protexure Lawyers application.<br/><br/>");
		msg.append("Your quotation expires ").append(ctx.get("QuoteExpiryDate")).append(".<br/><br/>");
		
		msg.append("To finalize your coverage electronically please follow the link below:<br/><br/>");
		

		msg.append("<a href=").append(getProjectUrl(ctx)).append("/signandpay.jsp?PolicyKey=")
				.append(encrytedPolicyKey).append(">Finalize Your Coverage</a><br/><br/>");
		msg.append("This link is specifically for your account, you will be able to securely review and sign your application. Once coverage has been accepted, you will have the opportunity to continue with your payment option.<br/><br/>");
		if(!"3".equals(ctx.get("CompanyKey").toString()))
		{
				msg.append("Another option to accept coverage is to follow the instructions in the attached quote letter.<br/><br/>");
				}
		msg.append("To help you better evaluate your options we have included some additional information on our <a href='http://info.protexurelawyers.com/protexure-lawyers-professional-liability-insurance#Features'> policy features</a>,<a href='http://info.protexurelawyers.com/protexure-lawyers-professional-liability-insurance#Claims'> claims handling </a>, and a <a href='http://info.protexurelawyers.com/protexure-lawyers-professional-liability-insurance#Specimen'>specimen policy</a>.<br/><br/>");
		
	
		msg.append("If you have any additional questions, please contact us at our toll-free number 877-569-4111 and one of our licensed professional liability specialists will be happy to assist you.<br/><br/>");

		
		msg.append("Regards,<br/><br/>");
		msg.append("The Protexure Lawyers Team<br/>");
		msg.append("Phone 877-569-4111<br/>");
		msg.append("Fax (440) 333-3214<br/><br/><br/>");

		msg.append("P.S. Protexure also offers a Property and General Liability program tailored to small firms. Privacy/Cyber Protection, Employee Dishonesty, Workers Comp and a Business Owners Policy for a home based business are available as well. Please email or call to inquire about any of our offerings.");
		
		msg.append("</td>");
		msg.append("</tr>");
		msg.append("</table>");

		return msg.toString();

	}
	
public String generateSignAndPayMailBodyforSubProducer(IContext ctx) throws Exception {
		
		LawyersValidationUtils utils = new LawyersValidationUtils();
		String encrytedPolicyKey = utils.encrypt(ctx.get("PolicyKey").toString());
		encrytedPolicyKey = URLEncoder.encode(encrytedPolicyKey,""+ StandardCharsets.UTF_8);
		/*encrytedPolicyKey = encrytedPolicyKey.replace("=", "%3D");
		encrytedPolicyKey = encrytedPolicyKey.replace("+", "%2B");*/
		
		String SignatureText=ctx.get("SignatureText")!=null?ctx.get("SignatureText").toString():"";
		
		String fax=null;
		
		if(ctx.get("Fax") != null && !"".equals(ctx.get("Fax")) && !"null".equals(ctx.get("Fax"))){
			fax="F:&nbsp;"+ctx.get("Fax").toString();
		}
		else{
			fax="";
		}
		
		String emailsig="";
		 if(ctx.get("EmailSignature")!=null && !"".equals(ctx.get("EmailSignature"))){
			 emailsig="<img src=\"cid:image1\">";
		 }
		 
		 String SignatureText2="";
		 if(ctx.get("SignatureText2")!=null && !"".equals(ctx.get("SignatureText2"))){
			 SignatureText2=ctx.get("SignatureText2").toString();
		 }
		 String SignatureText3="";
		 if(ctx.get("SignatureText3")!=null && !"".equals(ctx.get("SignatureText3"))){
			 SignatureText3="<td style='border-left: 3px solid green; padding-left: 5px'><img src=\"cid:image2\"></td>";
			}
		 
		 String Signature="";
		 if(ctx.get("Signature")!=null && !"".equals(ctx.get("Signature"))){
			 Signature="<tr><td><img src=\"cid:image\"></td></tr>";
		 }
		 String subProducerCode = SystemProperties.getInstance().getString("appl.LawyersIns.subproducer.signaturedisplay");
	     	String[] subProducerCodeList = subProducerCode.split("~");
	     	for(int subProducerCodeCount = 0; subProducerCodeCount < subProducerCodeList.length; subProducerCodeCount++){
	     		if(subProducerCodeList[subProducerCodeCount].equalsIgnoreCase(ctx.get("ProducerCode").toString())){
	     			Signature = "";
	     		}
	     	}
		 
		String resourcePath = SystemProperties.getInstance().getString(
				"appl." + ctx.getProject() + ".url");
		String ACHPath = SystemProperties.getInstance().getString(
				"appl." + ctx.getProject() + ".ach.url");
		
		StringBuilder msg = new StringBuilder(2048);
		msg.append("<table>");
		msg.append("<tr>");
		msg.append("<td>");
		
		msg.append(ctx.get("AccountName")).append("<br/>");
		msg.append(ctx.get("Address1")); 
		String address2 = ctx.get("Address2") != null ? " ," + ctx.get("Address2") : "";
		msg.append(address2).append("<br/>");
		msg.append(ctx.get("City")).append(" ,").append(ctx.get("StateDesc")).append(" ").append(ctx.get("Zip")).append("<br/><br/>");
		
		msg.append("Dear ").append(ctx.get("ContactPerson")).append(",<br/><br/>");
		
		msg.append("To finalize your professional liability coverage<br/><br/>");
		

		msg.append("<a href=").append(resourcePath).append("/signandpay.jsp?PolicyKey=")
				.append(encrytedPolicyKey).append(">Finalize</a><br/><br/>");

		       boolean flag = false;
				Object obj = RuleUtils.executeRule(ctx,
						"LawyersRule.isLSquared");
				if (obj != null && obj instanceof Boolean) {
					flag = (Boolean) obj;
	
			if (flag) {
			msg.append(" <a href=").append(ACHPath).append(">ACH Payment Link</a><br/>");
			}
				
		msg.append("<br/>");
		}
		msg.append("This link will bring you to your completed application where you can review, sign, and submit payment.<br/><br/>");
		
		
		msg.append("The online process gives you the instructions on how to pay via check or finance. If you prefer to pay by check, please send a check payable to ")
				.append(ctx.get("ProducerName")).append(" to ").append(ctx.get("CompleteAddress")).append(".<br/><br/>");
		
		msg.append("If you have any additional questions, please contact us ").append(ctx.get("SPPhoneNumber")).append(" and one of our licensed professional liability specialists will be happy to assist you.<br/><br/>");
			 
	   
		msg.append("Best regards,<br/>");
		msg.append("<table cellpadding='0' cellspacing='0'>").append(Signature).append("<tr><td>").append(SignatureText).append("P:&nbsp;").append(ctx.get("SPPhoneNumber")).append("&nbsp;</br>").append(fax).append("</td>").append(SignatureText3).append("</tr></table>");
		msg.append(emailsig).append("</br>");
        msg.append("</br>");
        msg.append("<p style='color:#999999'>").append(SignatureText2).append("</p>");
		msg.append("</td>");
		msg.append("</tr>");
		msg.append("</table>");

		return msg.toString();

	}
	
	public static void testval(IContext ctx) throws Exception{
		
		
				
		boolean isValidQN = false;        
		Object objQNRule = RuleUtils.executeRule(ctx,"LawyersRule.QuoteNumberNotEmptyAdmin");
        if (objQNRule != null && objQNRule instanceof Boolean) {
        	isValidQN = (Boolean) objQNRule;
        }	        
		if(!isValidQN) 
			LawyersUtils.populateError(ctx, "InvaliQuoteNum","Invalid Quote Number");
		
		boolean isValidWorkPhone = false;        
		Object objWorkPhoneRule = RuleUtils.executeRule(ctx,"LawyersRule.checkWorkPhoneAdminLength");
        if (objWorkPhoneRule != null && objWorkPhoneRule instanceof Boolean) {
        	isValidWorkPhone = (Boolean) objWorkPhoneRule;
        }	        
		if(!isValidWorkPhone) 
			LawyersUtils.populateError(ctx, "InvaliWorkPhone_admin","Please enter a valid phone number.");
	}
	public  void test(IContext ctx) throws Exception
	{
		
		if(ctx.get(Constants.INET_ERRORS_LIST)!=null  && ctx.get(Constants.INET_ERRORS_LIST) instanceof List)
		{
			List list = (List)ctx.get(Constants.INET_ERRORS_LIST);
			logger.debug(list.size());
			
		}
			
		
	}
	
	public static void generateDummyError(Context ctx) throws Exception{
		DataUtils.populateError(ctx, "fielderror", "");
	}
	
	public void setImagesPathToTemplate(IContext ctx) throws Exception {
		String homePath=SystemProperties.getInstance().getString("appl.home.dir");
		String checkboxYPath=homePath+"image//check-btn6.png";
		String checkboxNPath=homePath+"image//check-btn4.png";
		String cfLogoPath=homePath+"image//crum_logo.png";
		String protexureaccountantsLogoPath=homePath+"image//logo_protexureaccountants1.png";
		String headerBottomPath=homePath+"image//header_bottom.png";
		String spacerPath=homePath+"image//spacer.png";
		String spacerPath1=homePath+"img//space.gif";
		String freeTradeZoneStampPath=homePath+"image//free_trade_zone_stamp.png";
		String crumLogoPath=homePath+"image//crum_logo.png";
		String boxCrossPath=homePath+"image//boxcross.png";
		String noCrossPath=homePath+"image//nocross.jpg";
		String sign1Path=homePath+"image//sign1.png";
		String sign2Path=homePath+"image//sign2.png";
		String logoProtexureaccountants1Path=homePath+"image//logo_protexureaccountants1.png";
		String kylePath=homePath+"image//kyle.png";
		
		ctx.put("checkboxYPath",checkboxYPath);
		ctx.put("checkboxNPath",checkboxNPath);
		ctx.put("cfLogoPath",cfLogoPath);
		ctx.put("protexureaccountantsLogoPath",protexureaccountantsLogoPath);
		ctx.put("headerBottomPath",headerBottomPath);
		ctx.put("spacerPath",spacerPath);
		ctx.put("freeTradeZoneStampPath",freeTradeZoneStampPath);
		ctx.put("crumLogoPath",crumLogoPath);
		ctx.put("boxCrossPath",boxCrossPath);
		ctx.put("noCrossPath",noCrossPath);
		ctx.put("sign1Path",sign1Path);
		ctx.put("sign2Path",sign2Path);
		ctx.put("logoProtexureaccountants1Path",logoProtexureaccountants1Path);
		ctx.put("kylePath",kylePath);
		ctx.put("spacerPath1",spacerPath1);
	}
	public static void validateSelection(Context ctx)
	{
		String policyKey=ctx.get("strPolicyKey").toString();
		//logger.debug("*******************"+policyKey+"***************");
		try {
			if(policyKey==null |policyKey.equals(""))
				LawyersUtils.populateError(ctx, "noSelecion",
						"No offrisk policy has selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		}
		
	}

	public static void validateAllFlagsCleared(Context ctx) {
		int css =0;
		if(ctx.get("clearstatuscount")!=null){
			css=(Integer) ctx.get("clearstatuscount");
		}				

		try {
			if (css>0){
				LawyersUtils.populateError(ctx, "flagsNotCleard",
						"All Referrals flags are not cleared for the policy.");			
			}
			//comments
			/*if(ctx.get("isPricingApproved")==null || ctx.get("isPricingApproved").toString().equals("N") )*/
			String isPricingApproved=ctx.get("isPricingApproved")!=null && !ctx.get("isPricingApproved").equals(HtmlConstants.EMPTY) ?ctx.get("isPricingApproved").toString():"N";
			String pricingStatus= ctx.get("PricingStatus")!=null && !ctx.get("PricingStatus").equals(HtmlConstants.EMPTY) ?ctx.get("PricingStatus").toString():"N";
			//if(isPricingApproved.equals("N")|| isPricingApproved.equals("AI"))
			if(isPricingApproved.equals("N") && pricingStatus.equals("N"))
				LawyersUtils.populateError(ctx, "flagsNotCleard","All Referrals flags are not cleared for the policy.");
			

		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}

	}
	public static void checkAllReferralsCleared(Context ctx) {
		int count =0;
		if(ctx.get("unClearedFLagCount")!=null)
		{
			count=(Integer) ctx.get("unClearedFLagCount");
		}
				

		try {
			if (count>0)
			{
				LawyersUtils.populateError(ctx, "referalsFlagsNotCleard",
						"All Referrals Flags are not cleared for the policy.");
			}
			
		} catch (Exception e) {

			logger.error("Unexpected error", e);
		}

	}
	public static boolean validateWillsEstate(IContext ctx) throws Exception {
		Context ctx1=(Context)ctx;
		return validateWillsEstate(ctx1);
	}
	public static String clobStringConversion(Clob clb) throws IOException, SQLException
    {
      if (clb == null)
    	  return  "";
      StringBuffer str = new StringBuffer();
      String strng;
	  try (BufferedReader bufferRead = new BufferedReader(clb.getCharacterStream())) {
		  while ((strng=bufferRead.readLine())!=null)
		   str.append(strng);
	  }
      return str.toString();    } 
	
	public static void fetchDataForEmail(Context ctx)
	{
		
		try{
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
	    	String email_notification_event_name = ctx.get("event_name").toString();
	    	Context newCtx = new Context();
	    	newCtx.setProject(ctx.getProject());
	    	
	    	newCtx.put("UserNo", ctx.get("UserNo"));
	    	newCtx.put("PolicyKey", ctx.get("PolicyKey"));
	    	newCtx.put("event_name", email_notification_event_name);
	    	newCtx.put("VersionSequence", ctx.get("VersionSequence"));
	    	
	    	//going to get event description from database
	    	Object obj = SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementdroolQueriesgetEmailNotificationData", newCtx);
	    	if(obj != null && obj instanceof List){
	    		List list = (List)obj;
	    		for(int i=0; i<list.size(); i++){
	    			Map map = (Map)list.get(i);
	    			
	    			if(i > 0){
		    			ctx.put(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_FROMEMAIL_ADDRESS, ctx.get(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_FROMEMAIL_ADDRESS + "_"+i));
		    			ctx.put(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_TOEMAIL_ADDRESS, ctx.get(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_TOEMAIL_ADDRESS + "_"+i));
		    			ctx.put(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_CCEMAIL_ADDRESS, ctx.get(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_CCEMAIL_ADDRESS + "_"+i));
	    			}
	    			
	    			//sendEmailNotification(ctx, newCtx, map);
	    			sendEmailNotification(ctx, map);
	    		}
	    	}
    	}catch (Exception e) {
    		logger.error("Unable to send email notification due to error : " + e.getMessage());
		}
		
	}
	private static void sendEmailNotification(Context ctx, Map map) throws Exception {
		List attachments = new ArrayList();
		String htmlDir = SystemProperties.getInstance().getString("html.basedir");
		String to_email_address = null;
		String cc_email_address = null;
		String from_email_address = null;
		String subject = null;
		String bodyContent = null;
		
		List objAcountInfo=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAccountInfoForMail",ctx);
		if(objAcountInfo != null)
			ctx.putAll((Map) objAcountInfo.get(0));

		if(map.get("event_name")!=null)
			ctx.put("email_notification_event_name", map.get("event_name"));
		try
		{
		String from_role_desc = map.get("from_role_desc") != null ? map.get("from_role_desc").toString() : null;
		String to_role_desc = map.get("to_role_desc") != null ? map.get("to_role_desc").toString() : null;
		String bcc_role_desc = map.get("cc_role_desc") != null ? map.get("cc_role_desc").toString() : null;
		String email_template = map.get("email_template") != null ? map.get("email_template").toString() : null;
		
		String attachment_path = map.get("attachment_path") != null ? map.get("attachment_path").toString() : null;
		String attachment_content = map.get("attachment_content") != null ? map.get("attachment_content").toString() : null;
		ctx.put("Firm_Name", ctx.get("AccountName"));
		ctx.put("Quote_no", ctx.get("QuoteNumber"));
		ctx.put("ContactName", ctx.get("ContactPerson"));
		if(attachment_content!=null)
		{
		String[] attachmentpath=RuleEngineUtility.getTokens(attachment_content,",");
		
		for(int i=0;i<attachmentpath.length;i++)
			attachments.add(htmlDir+attachmentpath[i]);
		}
		String environmentVar = null;
		environmentVar = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment");
		if(!"CyberCoverage_Email_Protexure".equals(map.get("event_name").toString()) && !"UnderwriterAcknowledgement".equals(map.get("event_name").toString())
			&& !"Finance_mail".equals(map.get("event_name").toString())	&& !"UnderwriterAcknowledgement_DeclineStatus".equals(map.get("event_name").toString()))
		{
			if("CyberCoverage_Email_Lawyers".equals(map.get("event_name").toString()))
			{
				logger.debug("preparing to send Cyber email to insured");
				boolean producerCodeExist = new LawyersValidationUtils().checkIfSubProducerExist(ctx);

				if (producerCodeExist) {
					try {
						//to_role_desc = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.email");
						
						if("Y".equals(ctx.get("IsDriect")))
							to_role_desc = getInsuredAccountEmail(ctx);
						
					} catch (Exception e) {
						logger.error("Can't send cyber email because of "+e);
					}
				} 
				else
					to_role_desc=getInsuredAccountEmail(ctx);
				
			}
			else
			to_role_desc=getInsuredAccountEmail(ctx);
		}
		//environmentVar="PD";
		if(environmentVar.equals("DEV") || environmentVar.equals("QA")|| environmentVar.equals("UAT"))
		{
			from_role_desc=SystemProperties.getInstance().getString("mail.admin.from.address");
			to_role_desc = SystemProperties.getInstance().getString("mail.admin.to.address");
			bcc_role_desc = SystemProperties.getInstance().getString("mail.admin.cc.address");
			
		}
		
		if (to_role_desc==null || "".equals(to_role_desc))
			return;
		
			subject = map.get("subject") != null ? map.get("subject").toString() : HtmlConstants.EMPTY;
			if(!StringUtils.isBlank(subject)){
				subject = new MessageStringFormat(ctx).format(subject);
			}
			
			bodyContent = map.get("body") != null ? map.get("body").toString() : HtmlConstants.EMPTY;
			if(!StringUtils.isBlank(bodyContent)){
				bodyContent = new MessageStringFormat(ctx).format(bodyContent);
			}
			if(ctx.get("isSendMailTrue") != null && !HtmlConstants.EMPTY.equals(ctx.get("isSendMailTrue").toString())&& "Y".equals(ctx.get("isSendMailTrue"))){
				
			
					MailSender mailSender = new MailSender();
					mailSender.setContentType("text/html");
					
					mailSender.setFromAdd(from_role_desc);
					mailSender.setToAdd(to_role_desc);
					mailSender.setBccAdd(bcc_role_desc);
					mailSender.setSubject(subject);
					mailSender.setBody(bodyContent);
					if(attachments!=null && attachments.size()>0)
					mailSender.setAttachments(attachments);
					mailSender.sendMail(ctx);
					ctx.remove("email_notification_event_name");
				}
		}catch(Exception e)
		{
			logger.error("unable to send email of "+ctx.get("email_notification_event_name")+ "for Policykey "+ctx.get("PolicyKey"));
		}
		}
	
    public static void checkRightClickPageDivId(Context ctx, String divajaxpostionid, String fieldid){
        try{
              if(ctx.get("operationType") == null)
                    return;
              
              if(ctx.get("operationType").toString().equals("I")){
                    String collapserightclickpageid = ctx.get("collapserightclickpageid") != null ? ctx.get("collapserightclickpageid").toString() : null;
                    
                    if(collapserightclickpageid == null)
                          collapserightclickpageid = divajaxpostionid+":"+ctx.get(fieldid);
                    else{
                          collapserightclickpageid = ctx.get("collapserightclickpageid").toString();
                          
                          collapserightclickpageid = collapserightclickpageid + "," + divajaxpostionid+":"+ctx.get(fieldid);
                    }
                    
                    ctx.put("collapserightclickpageid", collapserightclickpageid);
              }else if(ctx.get("operationType").toString().equals("D")){
                    String collapserightclickpageid = ctx.get("collapserightclickpageid") != null ? ctx.get("collapserightclickpageid").toString() : null;
                    
                    if(collapserightclickpageid != null){
                          StringTokenizer tokens = new StringTokenizer(collapserightclickpageid, ",");
                    
                          String newTokens = null;
                          while(tokens.hasMoreTokens()){
                                String token = tokens.nextToken();
                                
                                if(token.equals(divajaxpostionid+":"+ctx.get(fieldid))){
                                      continue;
                                }
                                
                                if(newTokens == null)
                                      newTokens = token;
                                else
                                      newTokens = newTokens + "," + token;
                          }
                          
                          ctx.put("collapserightclickpageid", newTokens);
                    }
              }
        }catch(Exception e){
              logger.error("Unexpected error", e);
        }
  }

	
    public static void  compareReferrals(Context ctx) {
    	Object obj=null;
    try
    {	
    	List firmDetails=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetFirmDetails",ctx);
		ctx.putAll((Map) firmDetails.get(0));
    	Context newCtx=new Context();
		newCtx.setProject(ctx.getProject());
		newCtx.put("PolicyKey",ctx.get("PolicyKey"));
		newCtx.put("VersionSequence",ctx.get("VersionSequence"));
		newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
		newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
		List getReferralCountForAI=SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementManualBoQueriesgetReferralCountForAI",newCtx);
		newCtx.putAll((Map) getReferralCountForAI.get(0));
		List triggredReferrals=SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementManualBoQueriesgetCurrentTriggeredReferralsCount",newCtx);
		newCtx.putAll((Map) triggredReferrals.get(0));
		List historyReferrals=SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementManualBoQueriesgetReferralHistoryCount",newCtx);
		newCtx.putAll((Map) historyReferrals.get(0));
		List NewReferralCount=SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementManualBoQueriesgetCurrentApprovedReferralsCount",newCtx);
		newCtx.putAll((Map) NewReferralCount.get(0));
		List allReferralsCleared=SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementManualBoQueriesgetAllClearedReferrals",newCtx);
		newCtx.putAll((Map) allReferralsCleared.get(0));
		List getReferralCountForAIPricing=SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementManualBoQueriesgetReferralCountForAIPricing",newCtx);
		newCtx.putAll((Map) getReferralCountForAIPricing.get(0));
		obj = SqlResources.getSqlMapProcessor(newCtx).findByKey("SqlStmts.UserStatementManualBoQueriesgetReferralClearSubjectiveCount", newCtx);
		if(obj != null && obj instanceof Map)
			ctx.putAll((Map)obj);
		
		int noClearedReferralCount=Integer.valueOf(newCtx.get("noClearedReferralCount").toString());
		ctx.put("noClearedReferralCount",noClearedReferralCount);
		int totalReferralsCount=Integer.valueOf(newCtx.get("totalReferralTrigered").toString());
		int AICount=Integer.valueOf(newCtx.get("AICount").toString());
		int historyReferralsCount=Integer.valueOf(newCtx.get("ReferralCountHistory").toString());
		int newReferralCount=Integer.valueOf(newCtx.get("NewReferralCount").toString());
		int AIPricingCount=Integer.valueOf(newCtx.get("PricingAICount").toString());
		ctx.put("AICount",AICount);
		  String isPricingApproved=	ctx.get("isPricingApproved")!=null && !ctx.get("isPricingApproved").equals(HtmlConstants.EMPTY)?ctx.get("isPricingApproved").toString():"N";
		if(noClearedReferralCount==0 && isPricingApproved.equals("Y"))
		{
			newCtx.put("FollowUnderwriterFlow","N");
			ctx.put("FollowUnderwriterFlow","N");
		}
		newCtx.put("user_id",ctx.get("user_id"));
		newCtx.put("isPricingApproved",ctx.get("isPricingApproved"));
		newCtx.put("FollowUnderwriterFlow",ctx.get("FollowUnderwriterFlow"));
		newCtx.put("isRatingsChnaged",ctx.get("isRatingsChnaged"));
		newCtx.put("isComingFromUWFlow",ctx.get("isComingFromUWFlow"));
		
		//if((historyReferralsCount!=0) && (totalReferralsCount!=historyReferralsCount))
		if(newReferralCount>0 || ((historyReferralsCount!=0) && (totalReferralsCount!=historyReferralsCount))  )
		{
			//isPricingApproved='N',FollowUnderwriterFlow='Y',isRatingsChnaged='Y'
			newCtx.put("isPricingApproved","N");
			newCtx.put("FollowUnderwriterFlow","Y");
			newCtx.put("isRatingsChnaged","N");
			newCtx.put("isComingFromUWFlow","YES");
		}
		
		if(ctx.get("isRatingsChnaged")!=null && ctx.get("isRatingsChnaged").toString().equals("Y") )
		{
			newCtx.put("isPricingApproved","N");
			newCtx.put("FollowUnderwriterFlow","Y");
			newCtx.put("isRatingsChnaged","Y");
			newCtx.put("isComingFromUWFlow","NO");
		}
		if(AICount>0 || AIPricingCount>0)
		{
			newCtx.put("FollowUnderwriterFlow","Y");
		}
		//if( && ctx.get("isPricingApproved").toString().equals("Y") )
		if(newReferralCount==0 && (totalReferralsCount==historyReferralsCount) && isPricingApproved.equals("Y") && AICount==0 )
		{
			
			newCtx.put("isPricingApproved","Y");
			newCtx.put("FollowUnderwriterFlow","N");
			newCtx.put("isRatingsChnaged","N");
			newCtx.put("isComingFromUWFlow","NO");
		}

		ctx.put("isPricingApproved",newCtx.get("isPricingApproved"));
		ctx.put("FollowUnderwriterFlow",newCtx.get("FollowUnderwriterFlow"));
		ctx.put("isRatingsChnaged",newCtx.get("isRatingsChnaged"));
		ctx.put("isComingFromUWFlow",newCtx.get("isComingFromUWFlow"));
		//RuleUtils.executeRule(newCtx, "LawyersRule.AssignLastUpdateTimeStamp");
		//Raghu
		//SqlResources.getSqlMapProcessor(newCtx).update("SqlStmts.UserStatementManualBoQueriesresetRatingsAndUWflow", newCtx);
    }
    catch(Exception e)
    {
    	logger.error("Unexpected error", e);
    	
    }
    }
    public static void fetchFirstReviewer(Context ctx) {
    	try {
			SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateModiferRenewFirm", ctx);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		}
    	
    }
    
    public static void getAttorneyListUpdated(Context ctx){
    	try{
	    	HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
	        HttpSession sess = req.getSession();
	        
	        List finalList = null;
	        ctx.put("deletedAttorneys",null);
	        if(ctx.get("AttorneysDetailsList_list_01") != null &&   ctx.get("AttorneysDetailsList_list_01") instanceof List)
	        {
		        finalList = (List)ctx.get("AttorneysDetailsList_list_01");
		        if(finalList.size()==0)
		        {
		        	Map map = new HashMap();
		        	for (int i = 0; i < 1; i++)
		        	{
		        		map.put("AttorneyName", null);
						map.put("DesignationId", null);
						map.put("LicensedStates",null);
						map.put("AnnualWorkedHours",null);
						map.put("AttorneyPriorActDate", null);
						map.put("PriorActDateAttorneysNew",null);
						map.put("NumberOfYearsInPractice",null);
						map.put("combinedStates",null);
						map.put("IsNonRatedAttorney","N");
						map.put("CreatedBy", null);
						map.put("CreatedDate",null);
			        	finalList.add(map);
		        	
		        	}
		       }
		        
		       int newAttorneyList=ctx.get("addAttorneys")!=null?Integer.parseInt(ctx.get("addAttorneys").toString()):finalList.size();
		       if(finalList.size()==0 || newAttorneyList>finalList.size()){		
		    	   
			       	Map map = new HashMap();
			       	for (int i = finalList.size(); i < finalList.size()+(newAttorneyList-finalList.size()); i++){
			       		map.put("AttorneyName", null);
						map.put("DesignationId", null);
						map.put("LicensedStates",null);
						map.put("AnnualWorkedHours",null);
						map.put("AttorneyPriorActDate", null);
						map.put("PriorActDateAttorneysNew",null);
						map.put("NumberOfYearsInPractice",null);
						map.put("combinedStates",null);
						map.put("IsNonRatedAttorney","N");
						map.put("CreatedBy", null);
						map.put("CreatedDate",null);
			        	finalList.add(map);
			       	
			       	}
		       }
		       if(newAttorneyList<finalList.size())
		      		populateError(ctx, "attorneyCountError","Attorney count mismatch");
		       
		       ctx.put("AttorneysDetailsList_list_01",finalList);
		       ctx.put("addAttorneys", finalList.size());
	        }
	        
	        //To set new filing flag
			boolean isRatingNew = false;        
			Object objRatingRule = RuleUtils.executeRule(ctx,"LawyersRule.checkNewFiling");
	        if (objRatingRule != null && objRatingRule instanceof Boolean) {
	        	isRatingNew = (Boolean) objRatingRule;
	        }	        
			if(isRatingNew)
	        	ctx.put("IsNewFiling", "Y");
			else
				ctx.put("IsNewFiling", "N");
			
		    }catch(Exception e){
				logger.error("Unable to determine rating flow", e);
		    }    	
    }
    public static void removeAttorneys(Context ctx)
	{
		List finalList = null;
		int	elementRemoveIndex=0;
		int attorneyToRemoved= ctx.get("AttorneyKey") != null? Integer.parseInt(ctx.get("AttorneyKey").toString()): 1000;
		if(attorneyToRemoved!=1000)
		{
		List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
		try {
			if (ctx.get("AttorneysDetailsList_list_01") != null	&& ctx.get("AttorneysDetailsList_list_01") instanceof List) {
				// finalList = (List)sess.getAttribute("AttorneysDetailsList_list_01");
				finalList = (List) ctx.get("AttorneysDetailsList_list_01");
				Map map1 = null, map2 = null;
				for (int i = 0; i < finalList.size(); i++) {
					map1 = new HashMap();
					map2 = (HashMap) finalList.get(i);
					map1.put("AttorneyKey", map2.get("AttorneyKey"));
					if(attorneyToRemoved==Integer.parseInt(map2.get("AttorneyKey").toString()))
						elementRemoveIndex=i;
					map1.put("AttorneyName", ctx.get("AttorneyName_" + i));
					map1.put("DesignationId", ctx.get("DesignationId_" + i));
					map1.put("LicensedStates", map2.get("LicensedStates_" + i));
					map1.put("AnnualWorkedHours", ctx.get("AnnualWorkedHours_" + i));
					map1.put("AttorneyPriorActDate", ctx.get("AttorneyPriorActDate_" + i));
					map1.put("PriorActDateAttorneysNew", ctx.get("PriorActDateAttorneysNew_" + i));
					map1.put("NumberOfYearsInPractice", ctx.get("NumberOfYearsInPractice_" + i));
					map1.put("combinedStates", ctx.get("combinedStates_" + i));
					map1.put("IsNonRatedAttorney", ctx.get("IsNonRatedAttorney_" + i));
					map1.put("isAttorneyEndorsed", map2.get("isAttorneyEndorsed"));
					map1.put("isSaved", map2.get("isSaved"));
					map1.put("rownum", map2.get("rownum"));
					newList.add(map1);

				}
				newList.remove(elementRemoveIndex);
				ctx.put("isDataChange", "Y");
//				int saveAttorneys = ctx.get("savedCount") != null && !ctx.get("savedCount").equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("savedCount").toString()): 0;
//				int newAttorneyList = ctx.get("addAttorneys") != null? Integer.parseInt(ctx.get("addAttorneys").toString()): finalList.size();

//				if (newAttorneyList > finalList.size()) {
//					int index = finalList.size();
//					Map map = null;
//					for (int i = 0; i < newAttorneyList - finalList.size(); i++) {
//						map = new HashMap();
//						index = index + 1;
//						map.put("AttorneyKey", null);
//						map.put("AttorneyName", null);
//						map.put("DesignationId", null);
//						map.put("LicensedStates", null);
//						map.put("AnnualWorkedHours", null);
//						map.put("AttorneyPriorActDate", null);
//						map.put("PriorActDateAttorneysNew", null);
//						map.put("NumberOfYearsInPractice", null);
//						map.put("combinedStates", null);
//						map.put("IsNonRatedAttorney", "N");
//						map.put("isSaved", null);
//						map.put("rownum", index);
//						newList.add(map);
//
//					}
					ctx.put("addAttorneys", newList.size());
//				}
//        else
//        {
//	        if(newAttorneyList<saveAttorneys)
//	        {
//	        	populateError(ctx, "attorneyDataIncomplete","Selected attorney count cannot be less than of saved attorney count.");
//	        	ctx.put("addAttorneys", saveAttorneys);
//	        }
//	        else
//	        {
//	        	int result=finalList.size()-newAttorneyList;
//	        	int temp=finalList.size()-1;
//	        	for(int j=0;j<result;j++)
//	        		newList.remove(temp-j);
//	        	ctx.put("addAttorneys", newList.size());
//	        }
//        }
				ctx.put("AttorneysDetailsList_list_01", newList);

			}
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
		}

	}
   
    public static void addNewAttornies(Context ctx)
    {
    	 List finalList = null;
    	 List<Map<String,Object>> newList=new ArrayList<Map<String,Object>>();
         try
         {
    	 if(ctx.get("AttorneysDetailsList_list_01") != null &&   ctx.get("AttorneysDetailsList_list_01") instanceof List)
         {
            // finalList = (List)sess.getAttribute("AttorneysDetailsList_list_01");
         	 finalList = (List)ctx.get("AttorneysDetailsList_list_01");
         	Map map1=null,map2=null;
         	for (int i = 0; i <finalList.size(); i++)
         	 { map1 = new HashMap();
         	 map2=(HashMap)finalList.get(i);
         	 	map1.put("AttorneyKey", map2.get("AttorneyKey"));
         		map1.put("AttorneyName", ctx.get("AttorneyName_"+i));
 				map1.put("DesignationId",ctx.get("DesignationId_"+i));
 				map1.put("LicensedStates",map2.get("LicensedStates_"+i));
 				map1.put("AnnualWorkedHours",ctx.get("AnnualWorkedHours_"+i));
 				map1.put("AttorneyPriorActDate", ctx.get("AttorneyPriorActDate_"+i));
 				map1.put("PriorActDateAttorneysNew",ctx.get("PriorActDateAttorneysNew_"+i));
 				map1.put("NumberOfYearsInPractice",ctx.get("NumberOfYearsInPractice_"+i));
 				map1.put("combinedStates",ctx.get("combinedStates_"+i));
 				map1.put("IsNonRatedAttorney",ctx.get("IsNonRatedAttorney_"+i));
 				map1.put("isAttorneyEndorsed", map2.get("isAttorneyEndorsed"));
 				map1.put("isSaved",map2.get("isSaved"));
 				map1.put("rownum",map2.get("rownum"));
 				newList.add(map1);
         	
         	}
         
        int saveAttorneys=ctx.get("savedCount")!=null && !ctx.get("savedCount").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("savedCount").toString()):0;
        int newAttorneyList=ctx.get("addAttorneys")!=null?Integer.parseInt(ctx.get("addAttorneys").toString()):finalList.size();
        
        if(newAttorneyList>finalList.size())
        {
        	int index=finalList.size();
        	Map map = null;
        	for (int i = 0; i < newAttorneyList-finalList.size(); i++)
        	{
        		map=new HashMap();
        		index=index+1;
        		map.put("AttorneyKey",null);
        		map.put("AttorneyName", null);
 				map.put("DesignationId", null);
 				map.put("LicensedStates",null);
 				map.put("AnnualWorkedHours",null);
 				map.put("AttorneyPriorActDate", null);
 				map.put("PriorActDateAttorneysNew",null);
 				map.put("NumberOfYearsInPractice",null);
 				map.put("combinedStates",null);
 				map.put("IsNonRatedAttorney","N");
 				map.put("isSaved",null);
 				map.put("rownum",index);
 				newList.add(map);
        	
        	}
        	ctx.put("addAttorneys", newList.size());
        }
        else
        {
	        if(newAttorneyList<saveAttorneys)
	        {
	        	populateError(ctx, "attorneyDataIncomplete","Selected attorney count cannot be less than of saved attorney count.");
	        	ctx.put("addAttorneys", saveAttorneys);
	        }
	        else
	        {
	        	int result=finalList.size()-newAttorneyList;
	        	int temp=finalList.size()-1;
	        	for(int j=0;j<result;j++)
	        		newList.remove(temp-j);
	        	ctx.put("addAttorneys", newList.size());
	        }
        }
        ctx.put("AttorneysDetailsList_list_01",newList);
        
         }
         }catch(Exception e)
         {
        	 logger.error("Unexpected error", e);
         }
    
 	

    }
    public static void saveAttornies(Context ctx)
    {
    	try
    	{
    		getPolicyData(ctx);
    		int policyStatuskey=ctx.get("PolicyStatusKey")!=null && !ctx.get("PolicyStatusKey").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("PolicyStatusKey").toString()):99;
    		String isAttorneyAddedDeleted=ctx.get("IsAttorneyAddedDeleted")!=null && !ctx.get("IsAttorneyAddedDeleted").equals(HtmlConstants.EMPTY)?ctx.get("IsAttorneyAddedDeleted").toString():"";
    		List finalList =null;
	        List inputList=new ArrayList();
	        List statesList=new ArrayList();
	        String dateData=null;
	        boolean flag=false;
	        boolean isErrorExist=false;
	        boolean isFTEFlag = false;
	        int AnnualWorkedHours = 0;
	        int initialAttorneyCount=ctx.get("InitialAttorneyCount")!=null?Integer.parseInt(ctx.get("InitialAttorneyCount").toString()):0;
	       if(policyStatuskey==1 || (policyStatuskey==2 && isAttorneyAddedDeleted.equalsIgnoreCase("Y")))
	       {
	        if(ctx.get("AttorneysDetailsList_list_01") != null && ctx.get("AttorneysDetailsList_list_01") instanceof List)
	        {
	        	finalList = (List)ctx.get("AttorneysDetailsList_list_01");
		        int ratedCount=0;
		        int j=0;
		        String isNonRated="N";
		        int anualworkingHours=0;
		        String licensedStates=	ctx.get("newLicensedStates")!=null && !ctx.get("newLicensedStates").equals(HtmlConstants.EMPTY)?ctx.get("newLicensedStates").toString():"";
		        StringTokenizer stok = new StringTokenizer(licensedStates,"#");
				String tokens[] = new String[stok.countTokens()];
			    for(int i=0; i<tokens.length; i++){
			    	statesList.add(stok.nextToken());
			    }
			    
		        Map map = new HashMap();
				for (int i = 0; i < finalList.size(); i++) {	
					map = (HashMap) finalList.get(i);
						ctx.remove("LicensedStates_"+i);
						map.put("AttorneyName", ctx.get("AttorneyName_"+i));
						map.put("DesignationId", ctx.get("DesignationId_"+i));
						map.put("LicensedStates", statesList.get(i));
						map.put("AnnualWorkedHours", ctx.get("AnnualWorkedHours_"+i));
						map.put("AttorneyPriorActDate", ctx.get("AttorneyPriorActDate_"+i));
						map.put("NumberOfYearsInPractice", ctx.get("NumberOfYearsInPractice_"+i));
						map.put("IsNonRatedAttorney", ctx.get("IsNonRatedAttorney_"+i)); 
						map.put("AttorneyKey", ctx.get("AttorneyKey_"+i));
						map.put("PolicyKey", ctx.get("PolicyKey"));
						map.put("VersionSequence", ctx.get("VersionSequence"));
						map.put("VersionKey", ctx.get("VersionKey"));
						map.put("LastUpdateUserID", ctx.get("LastUpdateUserID"));
						map.put("LastUpdateTimeStamp", ctx.get("LastUpdateTimeStamp"));
						int attorneyKey=ctx.get("AttorneyKey_"+i)!=null && !ctx.get("AttorneyKey_"+i).equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("AttorneyKey_"+i).toString()):0;
						if(attorneyKey!=0)
						{
							map.put("CreatedBy", ctx.get("CreatedBy_"+i));
							map.put("CreatedDate",ctx.get("CreatedDate_"+i));
						}
						else
						{
							map.put("CreatedBy", ctx.get("LastUpdateUserID"));
							map.put("CreatedDate",ctx.get("LastUpdateTimeStamp"));
						}
						inputList.add(map);
					
					Object attorneyPriorActDateObj = ctx.get("AttorneyPriorActDate_"+i);
					if(attorneyPriorActDateObj != null && !attorneyPriorActDateObj.equals(HtmlConstants.EMPTY))
					{ 	dateData=attorneyPriorActDateObj.toString();
						if(validateDateFieldData(dateData)){
							flag=true;
							LawyersUtils.populateError(ctx, "attorneyDataIncomplete","Please enter valid date.");
							ctx.put("saveAttorneys","N");
						}
					}
				}
				for (int i = 0; i < finalList.size(); i++)
					ctx.put("LicensedStates_"+i, statesList.get(i));				
						
			
				if(inputList.size()>0 && flag==false){
					ctx.put("inputList",inputList);
					convertListDataToXML(ctx,"inputList","outputList");
					ctx.put("addAttorney", finalList.size());
					ctx.put("saveAttorneys","Y");
				}
				else
					ctx.put("saveAttorneys","N");				
	        }
    	}
    	}
        catch(Exception e){
		logger.error("Unable to convert list to XML", e);
        	ctx.put("skipSave","Y");
        }
    }
    public static void saveAttorniesRenewal(Context ctx)
    {  	
    	String isAttorneysModified=ctx.get("IsAttorneyAddedDeleted")!=null && !ctx.get("IsAttorneyAddedDeleted").equals( HtmlConstants.EMPTY)?ctx.get("IsAttorneyAddedDeleted").toString():"N";
    	if("Y".equalsIgnoreCase(isAttorneysModified))
    		saveAttornies(ctx);
    	else {
    		ctx.put("inet_skip_validation","Y");
    		ctx.put("skipSave","Y");
    	}    	
    }

public static Object convertListDataToXML(Context ctx,String inputListName,String outputXmlName) throws Exception
{
    
    if(inputListName == null || HtmlConstants.EMPTY.equals(inputListName) || outputXmlName == null || HtmlConstants.EMPTY.equals(outputXmlName))
        return null;
    
    	org.jdom.Element root = null;
        
        List dataList = ctx.get(inputListName) != null ? (List)ctx.get(inputListName) : null;
        if(dataList != null && dataList.size() > 0){
            
            root = new org.jdom.Element("Root");
            for(int i =0; i<dataList.size(); i++)
            {
                Map map = (Map)dataList.get(i);
                org.jdom.Element child = new org.jdom.Element("Root1");
                if(map != null)
                {
                    Set set = map.keySet();
                    Iterator<String> itr = set.iterator();
                    
                    while(itr.hasNext())
                    {
                        String key = itr.next();
                        org.jdom.Element rowElement = new org.jdom.Element(key);
                        
                        if(ctx.get(key+"_"+i) != null)
                        {
                            if(!HtmlConstants.EMPTY.equals(ctx.get(key+"_"+i).toString())){
                            	String value = "";
                            	if(key.contains("Debtor") || key.contains("Creditor") || key.contains("Trustee") || key.contains("AverageCaseValue")){
                            		value = removeAmountFormat(ctx.get(key+"_"+i)).toString();
                            	} else {
                            		value = ctx.get(key+"_"+i).toString();
                            	}
                                rowElement.addContent(value);
                                child.addContent(rowElement);
                            }else{
                            	
                            	 if(map.get(key) != null)
                                 {
                                     rowElement.addContent(map.get(key).toString());
                                     child.addContent(rowElement);
                                 }
                            	 else
                            	 {
	                                rowElement.addContent("");
	                                child.addContent(rowElement);
                            	 }
                            }
                            
                        }
                        else
                        {
                            //for checkbox you have to mention the key else it will update the map value there
                            if(key.equals("is_included"))
                            {
                                rowElement.addContent("N");
                                child.addContent(rowElement);
                                continue;
                            }
                            
                            if(map.get(key) != null)
                            {
                                rowElement.addContent(map.get(key).toString());
                                child.addContent(rowElement);
                            }
                            else if(map.get(key) == null)
                            {
                                rowElement.addContent("");
                                child.addContent(rowElement);
                            }
                        }
                        
                        
                    }
                    root.addContent(child);
                }
                
            }
            
        }
        XMLOutputter output = new XMLOutputter();
        String xml = output.outputString(root);
        ctx.put(outputXmlName, xml);
        return null;
        
}
	public static void viewPredecessorContent(Context ctx)
	{
		 HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
	        HttpSession sess = req.getSession();
	        
	        List finalList = null;
	        if(ctx.get("PredecessorSupplement_list_01") != null &&   ctx.get("PredecessorSupplement_list_01") instanceof List)
	        {
	           // finalList = (List)sess.getAttribute("AttorneysDetailsList_list_01");
	        	 finalList = (List)ctx.get("PredecessorSupplement_list_01");
	        	int initailSize=3;
		        if(finalList.size()==0)
		        {
		        	Map map = new HashMap();
		        	for (int i =0; i<initailSize; i++)
		        	{
		        		map.put("FirmName", null);
						map.put("DateOfAcquisation", null);
						map.put("TypeOfEntity",null);
						map.put("NumberOfAttorneyAtDiss",null);
						map.put("NumberOfAttorneyAtApplFirm", null);
						map.put("InsurerAtDissolution",null);
						map.put("IsERPPurchased",null);
						map.put("ERPExpDate",null);
			        	finalList.add(map);
		        	
		        	}
		        	
		        }
		       
		        else
		        {
		        	Map map = new HashMap();
		        	for (int i =initailSize-finalList.size(); i>0; i--)
		        	{
		        		map.put("FirmName", null);
						map.put("DateOfAcquisation", null);
						map.put("TypeOfEntity",null);
						map.put("NumberOfAttorneyAtDiss",null);
						map.put("NumberOfAttorneyAtApplFirm", null);
						map.put("InsurerAtDissolution",null);
						map.put("IsERPPurchased",null);
						map.put("ERPExpDate",null);
			        	finalList.add(map);
		        	
		        	}
		        }
		        ctx.put("PredecessorSupplement_list_01",finalList);
		        sess.setAttribute("PredecessorSupplement_list_01",finalList);
	        }
	}
	 public static void savePredecessorContentSupliment(Context ctx)
	    {
	    	try
	    	{
		    	HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
		        HttpSession sess = req.getSession();
		        
		        List finalList =null;
		        List inputList=new ArrayList();
		       if(sess.getAttribute("PredecessorSupplement_list_01") != null &&   sess.getAttribute("PredecessorSupplement_list_01") instanceof List)
		        {
			            finalList = (List)sess.getAttribute("PredecessorSupplement_list_01");
			       
					for (int i = 0; i < finalList.size(); i++) 
					{
						Map map = new HashMap();
						if(i==0)
						{
							if( HtmlConstants.EMPTY.equals(ctx.get("FirmName_"+i)) || ctx.get("FirmName_"+i) == null ||
								HtmlConstants.EMPTY.equals(ctx.get("DateOfAcquisation_"+i)) || ctx.get("DateOfAcquisation_"+i)==null	||
								HtmlConstants.EMPTY.equals(ctx.get("TypeOfEntity_"+i)) || ctx.get("TypeOfEntity_"+i)==null	||
								HtmlConstants.EMPTY.equals(ctx.get("NumberOfAttorneyAtDiss_"+i)) || ctx.get("NumberOfAttorneyAtDiss_"+i)==null	||
								HtmlConstants.EMPTY.equals(ctx.get("NumberOfAttorneyAtApplFirm_"+i)) || ctx.get("NumberOfAttorneyAtApplFirm_"+i)==null	||
								HtmlConstants.EMPTY.equals(ctx.get("InsurerAtDissolution_"+i)) || ctx.get("InsurerAtDissolution_"+i)==null	||
								HtmlConstants.EMPTY.equals(ctx.get("IsERPPurchased_"+i)) || ctx.get("IsERPPurchased_"+i)==null)
								{
									populateError(ctx, "needFirstRow","At least one record need to be filled.");
									return;
								}
							if(ctx.get("IsERPPurchased_"+i).equals("Y") &&(HtmlConstants.EMPTY.equals(ctx.get("ERPExpDate_"+i)) || ctx.get("ERPExpDate_"+i)==null))
							{
					
								populateError(ctx, "needFirstRow","At least one record need to be filled.");
								return;
							}
						
						}
						
						
							map.put("FirmName", ctx.get("FirmName_"+i));
							map.put("DateOfAcquisation", ctx.get("DateOfAcquisation_"+i));
							map.put("TypeOfEntity", ctx.get("TypeOfEntity_"+i));
							map.put("NumberOfAttorneyAtDiss", ctx.get("NumberOfAttorneyAtDiss_"+i));
							map.put("NumberOfAttorneyAtApplFirm", ctx.get("NumberOfAttorneyAtApplFirm_"+i));
							map.put("InsurerAtDissolution", ctx.get("InsurerAtDissolution_"+i));
							map.put("IsERPPurchased", ctx.get("IsERPPurchased_"+i));
							map.put("ERPExpDate", ctx.get("ERPExpDate_"+i));
							map.put("ProdecessorFirmKey", ctx.get("ProdecessorFirmKey_"+i));
							map.put("PolicyKey", ctx.get("PolicyKey"));
							map.put("VersionSequence", ctx.get("VersionSequence"));
							map.put("LastUpdateUserID", ctx.get("LastUpdateUserID"));
							map.put("LastUpdateTimeStamp", ctx.get("LastUpdateTimeStamp"));
							inputList.add(map);
							
						
						/*else
							populateError(ctx, "completeRow","You need to fill complete row.");*/
						
						
					}
					ctx.put("inputList", inputList);
					convertListDataToXML(ctx,"inputList","outputList");
					
				
		        }
	    	}
	        catch(Exception e)
	        {logger.error("Unable to convert list to XML", e);}
	    }
	 
	 public static void validateBankruptcySupplement(Context ctx)
	 {
		 try
		 {
			 HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
		     HttpSession sess = req.getSession();
		     List finalList =null;
			 
			if(!ctx.get("personalBankrupties").equals(HtmlConstants.EMPTY) && ctx.get("personalBankrupties")!=null ||
						!ctx.get("commercialBankruptcies").equals(HtmlConstants.EMPTY) && ctx.get("commercialBankruptcies")!=null	)
		        {
					int personalBankrupties=!ctx.get("personalBankrupties").equals(HtmlConstants.EMPTY) && ctx.get("personalBankrupties")!=null?Integer.parseInt(ctx.get("personalBankrupties").toString()):0;
					int commercialBankruptcies=!ctx.get("commercialBankruptcies").equals(HtmlConstants.EMPTY) && ctx.get("commercialBankruptcies")!=null?Integer.parseInt(ctx.get("commercialBankruptcies").toString()):0;
					int totalPercent=personalBankrupties+commercialBankruptcies;
		        	if(totalPercent>100 || totalPercent<100)
		        		populateError(ctx, "bankcruptiesPercentage","Total Bankruptcies percentage should be equal to 100.");
		        }
			int count=0;
			int totalPercent=0;
			 
	        Object objRule = RuleUtils.executeRule(ctx, "LawyersRule.bankruptcyImplementationDate");
			if(objRule != null){
				Boolean rule = (Boolean)objRule;
			if(rule){
				
		       if(ctx.get("CasesDetails_list_02") != null &&   ctx.get("CasesDetails_list_02") instanceof List)
		        {
			        finalList = (List)ctx.get("CasesDetails_list_02");
			        for (int i = 0; i < finalList.size(); i++) 
					{
			        	if( !ctx.get("Percentage_"+i).equals(HtmlConstants.EMPTY) && ctx.get("Percentage_"+i)!=null ||
							!ctx.get("AverageCaseValue_"+i).equals(HtmlConstants.EMPTY) && ctx.get("AverageCaseValue_"+i)!=null)
			        	{
			        		logger.debug("");
			        	int Percentage=!ctx.get("Percentage_"+i).equals(HtmlConstants.EMPTY) && ctx.get("Percentage_"+i)!=null?Integer.parseInt(ctx.get("Percentage_"+i).toString()):0;
						
			        	 totalPercent=Percentage+totalPercent;
			        	 
			        	}
			        	 else
			        	 {
			        		count++;
			        	 }
			        	
			        	
					}
			        if(totalPercent>100 || totalPercent<100){
		        		populateError(ctx, "bankcruptiesPercentageNEW","Total Bankruptcies percentage should be equal to 100.");
		              }
		        }
		       if(count>3)
		       {
		    	   populateError(ctx, "incompleteChart2","Please complete the chart in question 2.");  
		       }
			} 
			
			else{
				  if(ctx.get("CasesDetails_list_01") != null &&   ctx.get("CasesDetails_list_01") instanceof List)
		        {
			        finalList = (List)ctx.get("CasesDetails_list_01");
			        for (int i = 0; i < finalList.size(); i++) 
					{
			        	if( !ctx.get("Debtor_"+i).equals(HtmlConstants.EMPTY) && ctx.get("Debtor_"+i)!=null ||
							!ctx.get("Creditor_"+i).equals(HtmlConstants.EMPTY) && ctx.get("Creditor_"+i)!=null	||
							!ctx.get("Trustee_"+i).equals(HtmlConstants.EMPTY) && ctx.get("Trustee_"+i)!=null)
			        		logger.debug("");
			        		
			        	else
			        		count++;
					}
		        }
		       if(count>2)
		       {
		    	   populateError(ctx, "incompleteChart","Please complete the chart in question 2.");  
		       }
		       
		       
		 }
		 }
		 }
		 catch(Exception exception)
		 {
			 logger.error("Unexpected error", exception);
		 }
	 }
	 public static void saveBankruptcyCaseDetails(Context ctx)
	 {
	    	try
	    	{
		    	HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
		        HttpSession sess = req.getSession();
		        
		        List finalList =null;
		        List inputList=new ArrayList();
		        //List inputList2=new ArrayList();
		        int count=0;
		        
		        Object objRule = RuleUtils.executeRule(ctx, "LawyersRule.bankruptcyImplementationDate");
				if(objRule != null){
					Boolean rule = (Boolean)objRule;
				if(rule){
					
					 if(ctx.get("CasesDetails_list_02") != null &&   ctx.get("CasesDetails_list_02") instanceof List)
				        {
					        finalList = (List)ctx.get("CasesDetails_list_02");
					        Map map = null;
							for (int i = 0; i < finalList.size(); i++) 
							{
								map=new HashMap();
								{
									map.put("bankruptcyKey", i+1);
									map.put("Percentage", ctx.get("Percentage_"+i));
									map.put("AverageCaseValue", ctx.get("AverageCaseValue_"+i));
									map.put("PolicyKey", ctx.get("PolicyKey"));
									map.put("VersionSequence", ctx.get("VersionSequence"));
									inputList.add(map);
								}
							}
			           }
		           }
				
				
				else {
				
		       if(ctx.get("CasesDetails_list_01") != null &&   ctx.get("CasesDetails_list_01") instanceof List)
		        {
			        finalList = (List)ctx.get("CasesDetails_list_01");
			        Map map = null;
					for (int i = 0; i < finalList.size(); i++)  
					{
						map=new HashMap();
						{
							map.put("bankruptcyKey", i+1);
							map.put("Debtor", ctx.get("Debtor_"+i));
							map.put("Creditor", ctx.get("Creditor_"+i));
							map.put("Trustee", ctx.get("Trustee_"+i));
							map.put("PolicyKey", ctx.get("PolicyKey"));
							map.put("VersionSequence", ctx.get("VersionSequence"));
							inputList.add(map);
						}
					 }
				  }
		     	}
		       ctx.put("inputList", inputList);
				convertListDataToXML(ctx,"inputList","outputList");
	    	}
	    	}
	        catch(Exception e)
	        {
			logger.error("Unable to convert list to XML", e);
	        	
	        }
	   }
	 
	 public static void saveBankruptcyCaseDetails_New(Context ctx)
	   {
	    	try
	    	{
		    	HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
		        HttpSession sess = req.getSession();
		        
		        List finalList =null;
		        List inputList=new ArrayList();
		        int count=0;
		        
		       if(ctx.get("CasesDetails_list_01") != null &&   ctx.get("CasesDetails_list_01") instanceof List)
		        {
			        finalList = (List)ctx.get("CasesDetails_list_01");
			        Map map = null;
					for (int i = 0; i < finalList.size(); i++) 
					{
						map=new HashMap();
						{
							map.put("bankruptcyKey", i+1);
							map.put("Percentage", ctx.get("Percentage_"+i));
							map.put("Averagecase", ctx.get("Averagecase_"+i));
							map.put("PolicyKey", ctx.get("PolicyKey"));
							map.put("VersionSequence", ctx.get("VersionSequence"));
							inputList.add(map);
							
						}
					}
					ctx.put("inputList", inputList);
					convertListDataToXML(ctx,"inputList","outputList");
			       
			       
			     }
	    	}
	        catch(Exception e)
	        {
			logger.error("Unable to convert list to XML", e);
	        	
	        }
	   }
	 
	 
	 
	 public static void savePageOnTabs(Context ctx)
	 {
		// Context localCtx = new Context();
		//	localCtx.setProject(ctx.getProject());
			ctx.remove("isNotCompletlyFilled");
			Object val = ctx.get("IsFirmHaveLawyersLiabilityInsurance");
			ComponentImpl compImpl;
			try {
				compImpl = ComponentResources.getInstance(ctx).getComponent(ctx.get("from_page").toString());
				//localCtx.putAll(ctx);
				//localCtx.put("inet_method", "save");
				/*Object objCompleted = SqlResources.getSqlMapProcessor(ctx).findByKey(
						"SqlStmts.UserStatementManualBoQueriesisAppCompletedForTabs",
						ctx);
				ctx.put("isAppCompletedSave", objCompleted);
				ctx.get("isAppCompletedSave");*/
				List firmDetails= (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesisAppCompletedForTabs",ctx);
				ctx.putAll((Map) firmDetails.get(0));
				
				boolean isContinue = false;
				Object objIsContinue = RuleUtils.executeRule(ctx, "LawyersRule.isContinueAction");
				if (objIsContinue != null && objIsContinue instanceof Boolean) {
					isContinue = (Boolean) objIsContinue;
				}
				/*String isAppCompleted=ctx.get("isAppCompleted")!=null && !ctx.get("isAppCompleted").equals(HtmlConstants.EMPTY)?ctx.get("isAppCompleted").toString():"N";*/
				if(isContinue==true)
				{
					ctx.put("inet_method","continue");
				
					ctx.put("inet_skip_validation", "Y");
				}
				else
				{	
				ctx.put("inet_method", "save");
				
				}
				compImpl.validate(ctx);
				                        
				compImpl.performAction(ctx);
				
				
				  if(ctx.get("object_name")!=null && ctx.get("object_name").toString().equals("quoteOption"))
				  {
					  int statusKey=ctx.get("StatusKey")!=null && !ctx.get("StatusKey").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("StatusKey").toString()):0;
					  if(statusKey==1 || statusKey==2)
						  validateApplicationForcompletion(ctx);
					//validateAllPagesSavedNewApp(ctx);
				  }
				if(ctx.get("inet_errors_list") == null)
					ctx.put("WORKFLOW_FORWARD", ctx.get("object_name"));
				else
					ctx.put("WORKFLOW_FORWARD", ctx.get("from_page"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("Unexpected error", e);
			}
			
		 
	 }
	 public static void validateAllPagesCompleted(Context ctx)
	 {
		 try
		 {
			 
		 }
		 catch(Exception e)
		 {
			 logger.error("Unable to validate page completion", e);
		 }
	 }
	 
	 public static void validateCollectionPercentageManually(Context ctx)
	 {
		 try
		 {
			 if(ctx.get("consumerCollections")!=null && !ctx.get("consumerCollections").equals(HtmlConstants.EMPTY)
					||  ctx.get("commercialollections")!=null&& !ctx.get("commercialollections").equals(HtmlConstants.EMPTY))
			 {
				 int consumerCollections=ctx.get("consumerCollections")!=null && !ctx.get("consumerCollections").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("consumerCollections").toString()):0;
				 int commercialollections=ctx.get("commercialollections")!=null&& !ctx.get("commercialollections").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("commercialollections").toString()):0;
				 int totalPercentage=consumerCollections+commercialollections;
				 
				 if(totalPercentage>100 || totalPercentage<100)
					 populateError(ctx, "totalPercentageError","Amounts of Consumer Collections and Commercial Collections must equal to 100%");
			 }
			 else
				 populateError(ctx, "collectionPracticeError","Please provide percentage of collections practice.");
		 }
		 catch(Exception e)
		 {
			 logger.error("Unexpected error", e);
		 }
	}
	 public static void supplementTabs(Context ctx)  {			
		 try{
			 //By Raghu CCB Supplement
			 RuleUtils.executeRule(ctx, "LawyersRule.checkCCBSupp");				
			 
			 try{
				 if(ctx.get("ElectronicInsuranceImplDate") instanceof String) {
					 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				     Date parsedDate = dateFormat.parse(ctx.get("ElectronicInsuranceImplDate").toString());
					 ctx.put("ElectronicInsuranceImplDate", new Timestamp(parsedDate.getTime()));
				 }
			 new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,AccountKey,litigationNewImplDate,IsCCBFlag,RenewalSupplementNewImplDate,isCannibSuppFlow,BankruptcySuppImplDate");
			 List validatePageData =(List)SqlResources.getSqlMapProcessor(ctx).select("FirmLW.ValidateIndivisualPageData_p", ctx);
			 if(validatePageData!=null && validatePageData.size()>0)
					ctx.putAll((Map) validatePageData.get(0));
			
				/*
				 * validatePageData =(List)SqlResources.getSqlMapProcessor(ctx).select(
				 * "FirmLW.ValidateIndivisualPageData_p", ctx); if(validatePageData!=null &&
				 * validatePageData.size()>0) ctx.putAll((Map) validatePageData.get(0));
				 */
				
			 }catch(Exception etab){
				 logger.error("Unexpected error", etab);
			 }
			 HashMap cmap=null;
			 RuleUtils.executeRule(ctx, "LawyersRule.setLitigationNewImplDate");
			 
			/* if(ctx.get("litigationNewImplDate") != null && !HtmlConstants.EMPTY.equals(ctx.get("litigationNewImplDate"))){ 
	             SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	             try{
	                 sdf.setLenient(false);
	                 ctx.put("litigationDateNEW", sdf.parse(ctx.get("litigationNewImplDate").toString()));
	             }catch(Exception e){
	             }
	         }*/
			//ctx.put("litigationDateNEW", new SimpleDateFormat("MM/dd/yyyy").format(new Date(ctx.get("litigationNewImplDate").toString())));
			 ctx.put("litigationDateNEW",ctx.get("litigationNewImplDate").toString().trim());
			 
			 if(ctx.get("ElectronicInsuranceImplDate") instanceof String) {
				 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			     Date parsedDate = dateFormat.parse(ctx.get("ElectronicInsuranceImplDate").toString());
				 ctx.put("ElectronicInsuranceImplDate", new Timestamp(parsedDate.getTime()));
			 }
			 
			 new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,litigationDateNEW,IsCCBFlag,RenewalSupplementNewImplDate");
			 List dataList=SqlResources.getSqlMapProcessor(ctx).select("FirmLW.TabsWorkFlow_p",ctx);
			 
			 for (int i = 0; i < dataList.size(); i++) {	
				 ctx.put("tabs_tabsResult_list_01_"+i, dataList.get(i));
			 }
			
			 //ctx.put("tabs_tabsResult_list_01", dataList);
			 //logger.debug("policy----" + ctx.get("tabs_tabsResult_list_01_7"));
			 Object obj = ctx.get("tabs_tabsResult_list_01_7");
			 if (obj != null) {
				List list = (List) obj;
				for (int i = 0; i <list.size(); i++) {
					Map map = (Map) list.get(i);
					ctx.put("flag_for_supplment", map.get("flag_for_supplment"));
					logger.debug("flag for policy----" + ctx.get("flag_for_supplment"));
				}
			 }
			
			 Object objSupplement = ctx.get("tabs_tabsResult_list_01_1");
			 List listSupplement = null;
			 Map mapSupplement = null;
			 String initialSupplementPage = "";
			 if(objSupplement != null){
				 listSupplement = (List) objSupplement;
				 if(listSupplement != null && listSupplement.size() > 0){
					 mapSupplement = (Map) listSupplement.get(0);
					 initialSupplementPage = mapSupplement.get("object_name").toString();
					 ctx.put("initialSupplementPage", initialSupplementPage);
				 }
			 }
			 
		 }catch(Exception e) {
			 logger.error("Unexpected error", e);
		 }
	}
	 
	 public static boolean validateFirmNewApp(Context ctx) throws Exception {
			List list = SqlResources.getSqlMapProcessor(ctx).select(
					"SqlStmts.UserStatementdropdowndatacountyDropDown", ctx);
			if (list != null) {
				ctx.put("getCountyList", list);
			}
			
			boolean isAgent = false;
			Object objAgentRule = RuleUtils.executeRule(ctx, "LawyersRule.isAgent");
			if (objAgentRule != null && objAgentRule instanceof Boolean) {
				isAgent = (Boolean) objAgentRule;
			}
			/*boolean flag = false;
			Object obj = RuleUtils.executeRule(ctx,
					"LawyersRule.validateEmailOnFirmSave");
			if (obj != null && obj instanceof Boolean) {
				flag = (Boolean) obj;

				if (flag) {
					checkFirmWithEmailExist(ctx);
				}

			}*/

			validateBusinessStateOnContinue(ctx);
			//validateEstimateForCurrentYear(ctx);
			//validateFirmPrimaryLocationList(ctx);
			//cleanFirm(ctx);
			trimFirmApplicantName(ctx);
			validateCountyField(ctx);
			validateCityField(ctx);
			//validateFiscalYearDate(ctx);
			//validateFiscalYearData(ctx);
			validateCountyForTX(ctx);
			if (ctx.get("StateCode") != null
					&& "MO".equals(ctx.get("StateCode").toString()))
				cleanDataForMissouri(ctx);

			/*LawyersValidationUtils.validateEmail(ctx, "AccountEmail",
					ctx.get("AccountEmail") != null ? ctx.get("AccountEmail")
							.toString().trim() : "");*/

			return true;
		}
	 public static void getAopSearchData(Context ctx)
	 {
		 String str=ctx.get("AopSearch")!=null && !ctx.get("AopSearch").equals(HtmlConstants.EMPTY)?ctx.get("AopSearch").toString():"";
		 if(str.length()>2)
			 ctx.put("isCallSearch", "Y");
	 }
	
	 public static void fetchDataForRLIEmail(Context ctx)
		{
			
			try{
			/*	validateApplicationForcompletion(ctx);
				String isNotCompletlyFilled=!ctx.get("isNotCompletlyFilled").equals(HtmlConstants.EMPTY) && ctx.get("isNotCompletlyFilled")!=null?ctx.get("isNotCompletlyFilled").toString():"";
				if("Y".equals(isNotCompletlyFilled))
					return;*/
				List Firmlist = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetFirmDetails",ctx);
				if(Firmlist!=null)
					ctx.putAll((Map) Firmlist.get(0));
				 Firmlist = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetProfessionalLiabilityDetailList",ctx);
				if(Firmlist!=null)
					ctx.putAll((Map) Firmlist.get(0));
				String htmlDir = SystemProperties.getInstance().getString("html.basedir");
	
		    	Context newCtx = new Context();
		    	newCtx.setProject(ctx.getProject());
		    	
		    	
		    	newCtx.put("PolicyKey", ctx.get("PolicyKey"));
		    	
		    //	String dstId = "";

				// Do not send quote letter mail to insured if producer code is	exist
		    	/*boolean producerCodeExist = new LawyersValidationUtils().checkIfSubProducerExist(ctx);

				if (producerCodeExist) {
					try {
						dstId = SystemProperties.getInstance().getString(
								"appl." + ctx.getProject() + ".admin.email");
						
						if("Y".equals(ctx.get("IsDriect"))){
							dstId = LawyersUtils.getEmailID(ctx);
						}
					} catch (Exception e) {
						logger.error("Unable to resolve notification recipient", e);
					}
				} else {
					dstId = LawyersUtils.getEmailID(ctx);
				}

				if ("".equals(dstId))
					return;*/
				
		    	//going to get event description from database
				newCtx.put("StateCode", ctx.get("StateCode"));
				 	Object obj = SqlResources.getSqlMapProcessor(newCtx).select("FirmLW.GetCybercoverageEmailDataLW_p", newCtx);
		    	if(obj != null && obj instanceof List){
		    		List list = (List)obj;
		    		for(int i=0; i<list.size(); i++){
		    			Map map = (Map)list.get(i);
		    			
		    			if(i > 0){
			    			ctx.put(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_FROMEMAIL_ADDRESS, ctx.get(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_FROMEMAIL_ADDRESS + "_"+i));
			    			ctx.put(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_TOEMAIL_ADDRESS, ctx.get(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_TOEMAIL_ADDRESS + "_"+i));
			    			ctx.put(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_CCEMAIL_ADDRESS, ctx.get(HtmlConstants.EMAIL_NOTIFICATION_AGENCY_CCEMAIL_ADDRESS + "_"+i));
		    			}
		    			
		    			
		    			sendEmailNotification(ctx, map);
		    		}
		    	}
	    	}catch (Exception e) {
	    		logger.error("Unable to send email notification due to error : " + e.getMessage());
			}
			
	}
	
	 public static void validateAopPercentageManually(Context ctx)
	 {
		 
		HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
        HttpSession sess = req.getSession();
        int total=0,percentage=0;
        try
	     {
        	int policyStatusKey=ctx.get("PolicyStatusKey")!=null && !ctx.get("PolicyStatusKey").equals(HtmlConstants.EMPTY) ?Integer.parseInt(ctx.get("PolicyStatusKey").toString()):0;
        	if(policyStatusKey==2)
        	{
        		boolean isAOPRenewalNew = false;        
    	  		Object objAOPRenewalRule = RuleUtils.executeRule(ctx,"LawyersRule.showAOPRenewalNewFlow");
    	          if (objAOPRenewalRule != null && objAOPRenewalRule instanceof Boolean) {
    	          	isAOPRenewalNew = (Boolean) objAOPRenewalRule;
    	          }	        
    	  		if(isAOPRenewalNew){
		        	if(ctx.get("IsAOPChange") == null)
		    			populateError(ctx, "IsAOPChange", "Please select Yes or No");
	        	}
        	}
        	if (ctx.get(Constants.INET_ERRORS_LIST) == null){
        		
//	    	 List limtTypes = SqlResources.getSqlMapProcessor(ctx).select("AOPLW.findAll", ctx);
	         List finalList = new ArrayList();
	         if(sess.getAttribute("aoplist_list_2") != null &&   sess.getAttribute("aoplist_list_2") instanceof List)
	            finalList = (List)sess.getAttribute("aoplist_list_2");
	         
	        Map map = new HashMap();
	        for (int i = 0; i < finalList.size(); i++) 
	 		{	
	        	Context ctx1=new Context();
				ctx1.setProject(ctx.getProject());
				map = (HashMap) finalList.get(i);
				ctx1.put("PolicyKey",ctx.get("PolicyKey"));
				ctx1.put("AOPkey",map.get("Aopkey"));
				ctx1.put("percentage",ctx.get("AOP_Percentage_"+i));
				//SqlResources.getSqlMapProcessor(ctx1).update("SqlStmts.UserStatementManualBoQueriesupdateSmartSearchValue", ctx1);
				percentage=ctx.get("AOP_Percentage_"+i)!=null && !ctx.get("AOP_Percentage_"+i).equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("AOP_Percentage_"+i).toString()):0;;
				total=total+percentage;
	 		}
	        if(total>100 ||total<100)
			{
				populateError(ctx, "totalPercentageError","The total AOP percentage is required to equal 100%, current total is "+ total + "%");
			}
        	}
	 
	     }
        	catch(Exception e)
	        {
	        	logger.error("Unexpected error", e);
	        }
	        }

	 public static void updateAOPList(Context ctx)
	 {
		 HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
	     HttpSession sess = req.getSession();
	     List<Map<String,Object>> newList=new ArrayList<Map<String,Object>>();
		 List finalList = null;
		 Map map1=null,map2=null;
         try
         {
        	 int rownum=0;
        	 int index=ctx.get("rownum")!=null && !ctx.get("rownum").equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("rownum").toString()):-2;
	    	 if(ctx.get("aoplist_list_2") != null &&   ctx.get("aoplist_list_2") instanceof List)
	         {
	            // finalList = (List)sess.getAttribute("AttorneysDetailsList_list_01");
	         	 finalList = (List)ctx.get("aoplist_list_2");
	         	 
	         	 for(int i=0;i<finalList.size();i++)
	         	 {
	         		 	//,,
	         	 	map1 = new HashMap();
	         	 	map2=(HashMap)finalList.get(i);
	         	 	map1.put("SmartSearchID", map2.get("SmartSearchID"));
	         		map1.put("DisplaySequence", map2.get("DisplaySequence"));
	 				map1.put("Aopkey",map2.get("Aopkey"));
	 				map1.put("AOPDesc",ctx.get("AOPDesc_"+i));
	 				map1.put("percentage", ctx.get("AOP_Percentage_"+i));
	 				map1.put("rownum",map2.get("rownum"));
	 				newList.add(map1);
	         	 }
			 
		 }
	    	 if(index!=-2)
	    		 newList.remove(index-1);
	    	 for(int i=0;i<newList.size();i++)
         	 {
	    		rownum=rownum+1;
         	 	map2=(HashMap)newList.get(i);
         	 	map2.put("rownum",rownum);
 				
         	 }
	    	 
	    	 
	    	 ctx.put("aoplist_list_2",newList);
	    	 sess.setAttribute("aoplist_list_2", newList);
	    	 
         }catch(Exception e)
         {
        	 logger.error("Unexpected error", e);
         }
	 }
	 
	 public static void validateAllPagesSavedNewApp(Context ctx)
	 {
		 try {	
			 if(ctx.get("ElectronicInsuranceImplDate") instanceof String) {
				 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			     Date parsedDate = dateFormat.parse(ctx.get("ElectronicInsuranceImplDate").toString());
				 ctx.put("ElectronicInsuranceImplDate", new Timestamp(parsedDate.getTime()));
			 }
			 	RuleUtils.executeRule(ctx, "LawyersRule.setLitigationNewImplDate");	
			 	RuleUtils.executeRule(ctx, "LawyersRule.setRenewalSupplementNewImplDate");	
				ctx.put("isCannibSuppFlow","Y");
			 	new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,AccountKey,litigationNewImplDate,RenewalSupplementNewImplDate,isCannibSuppFlow");
				List validatePadeData =(List)SqlResources.getSqlMapProcessor(ctx).select("FirmLW.ValidateIsAppCompleted_p", ctx);
				
				if(validatePadeData!=null && validatePadeData.size()>0)
				ctx.putAll((Map) validatePadeData.get(0));
				String isAppFilledCompleted=ctx.get("CompletlyFilled")!=null && !ctx.get("CompletlyFilled").equals(HtmlConstants.EMPTY)?ctx.get("CompletlyFilled").toString():"N";
				
				ctx.put("isAppFilledCompleted",isAppFilledCompleted);
				if("N".equals(isAppFilledCompleted))
					LawyersUtils.populateError(ctx, "ApplicationNotCompleted","Please complete the application.");
				
		 	}catch (Exception e) {
					logger.error("Unexpected error", e);
			}
		 
	 }
	 public static void validateAllPagesSavedSubmit(Context ctx)
	 {
		 try {
			 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			 if(ctx.get("ElectronicInsuranceImplDate") instanceof String) {
			     Date parsedDate = dateFormat.parse(ctx.get("ElectronicInsuranceImplDate").toString());
				 ctx.put("ElectronicInsuranceImplDate", new Timestamp(parsedDate.getTime()));
			 }
			 if(ctx.get("BankruptcySuppImplDate") instanceof String) {
			     Date parsedDate = dateFormat.parse(ctx.get("BankruptcySuppImplDate").toString());
				 ctx.put("BankruptcySuppImplDate", new Timestamp(parsedDate.getTime()));
			 }
			
			 new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,AccountKey,litigationNewImplDate,RenewalSupplementNewImplDate,isCannibSuppFlow");
				List validatePadeData =(List)SqlResources.getSqlMapProcessor(ctx).select("FirmLW.ValidateIsAppCompleted_p", ctx);
				
				if(validatePadeData!=null && validatePadeData.size()>0)
				{
					ctx.putAll((Map) validatePadeData.get(0));
				}
				String isAppFilledCompleted=ctx.get("CompletlyFilled")!=null && !ctx.get("CompletlyFilled").equals(HtmlConstants.EMPTY)?ctx.get("CompletlyFilled").toString():"N";
				
				
				if("N".equals(isAppFilledCompleted))
					ctx.put("ShowCompletedError", "N");
				
				else
					ctx.put("ShowCompletedError", "Y");
				
		 	} 
		 
		 
		 		catch (Exception e) {
					logger.error("Unexpected error", e);
				}
		 
	 }
	 public static void addNewFeeSuit(Context ctx)
	    {
	    	 List finalList = null;
	    	 HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
		     HttpSession sess = req.getSession();
	    	 List<Map<String,Object>> newList=new ArrayList<Map<String,Object>>();
	         try
	         {
	        	 
	    	 if(ctx.get("FeeSuitData_list_01") != null &&   ctx.get("FeeSuitData_list_01") instanceof List)
	         {
	           
	         	 finalList = (List)ctx.get("FeeSuitData_list_01");
	         	Map map1=null,map2=null;
	         	for (int i = 0; i <finalList.size(); i++)
	         	 {
	         		map1 = new HashMap();
	         		map2=(HashMap)finalList.get(i);
	         	 	map1.put("FirminitLawsuitsKey", map2.get("FirminitLawsuitsKey"));
	         		map1.put("IsAllegOfLegalMalPrac", ctx.get("IsAllegOfLegalMalPrac_"+i));
	 				map1.put("SuitFilesDateFees",ctx.get("SuitFilesDateFees_"+i));
	 				map1.put("FeeSuitDispositionKey",ctx.get("FeeSuitDispositionKey_"+i));
	 				map1.put("AmountOfFeesSued", ctx.get("AmountOfFeesSued_"+i));
	 				map1.put("isSaved",map2.get("isSaved"));
	 				map1.put("ROWID",map2.get("ROWID"));
	 				newList.add(map1);
	         	
	         	}
	         
	        int savedFeeSuits=ctx.get("savedCount")!=null && !ctx.get("savedCount").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("savedCount").toString()):0;
	        int newFeeSuitsList=ctx.get("addFeeSuits")!=null?Integer.parseInt(ctx.get("addFeeSuits").toString()):finalList.size();
	        
	        if(newFeeSuitsList>finalList.size())
	        {
	        	int index=finalList.size();
	        	Map map = null;
	        	for (int i = 0; i < newFeeSuitsList-finalList.size(); i++)
	        	{
	        		map=new HashMap();
	        		index=index+1;
	        		map.put("FirminitLawsuitsKey", null);
	        		map.put("IsAllegOfLegalMalPrac", null);
	 				map.put("SuitFilesDateFees", null);
	 				map.put("FeeSuitDispositionKey",null);
	 				map.put("AmountOfFeesSued",null);
	 				map.put("isSaved",null);
	 				map.put("ROWID",index);
	 				newList.add(map);
	        	
	        	}
	        	
	        }
	        else
	        {
		        if(newFeeSuitsList<savedFeeSuits)
		        {
		        	populateError(ctx, "feesuitIncompleteData","Selected fee suit count cannot be less than of saved fee suit count.");
		        	ctx.put("addFeeSuits", savedFeeSuits);
		        }
		        else
		        {
		        	int result=finalList.size()-newFeeSuitsList;
		        	int temp=finalList.size()-1;
		        	for(int j=0;j<result;j++)
		        		newList.remove(temp-j);
		        	ctx.put("addFeeSuits", newList.size());
		        }
	        }
	        ctx.put("addFeeSuits", newList.size());
	        ctx.put("FeeSuitData_list_01",newList);
	        sess.setAttribute("FeeSuitData_list_01s", newList);
	        
	         }
	         }catch(Exception e)
	         {
	        	 logger.error("Unexpected error", e);
	         }
	    
	 	

	    }
	 
	 public static void updateFeeSuitList(Context ctx)
	 {
		 HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
	     HttpSession sess = req.getSession();
	     List<Map<String,Object>> newList=new ArrayList<Map<String,Object>>();
		 List finalList = null;
		 Map map1=null,map2=null;
         try
         {
        	 int ROWID=0;
        	 int index=ctx.get("ROWID")!=null && !ctx.get("ROWID").equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("ROWID").toString()):-2;
	    	 if(ctx.get("FeeSuitData_list_01") != null &&   ctx.get("FeeSuitData_list_01") instanceof List)
	         {
	           
	         	 finalList = (List)ctx.get("FeeSuitData_list_01");
	         	 
	         	 for(int i=0;i<finalList.size();i++)
	         	 {
	         		 
	         	 	map1 = new HashMap();
	         	 	map2=(HashMap)finalList.get(i);
	         	 	map1.put("FirminitLawsuitsKey", map2.get("FirminitLawsuitsKey"));
	         		map1.put("IsAllegOfLegalMalPrac", ctx.get("IsAllegOfLegalMalPrac_"+i));
	 				map1.put("SuitFilesDateFees",ctx.get("SuitFilesDateFees_"+i));
	 				map1.put("FeeSuitDispositionKey",ctx.get("FeeSuitDispositionKey_"+i));
	 				map1.put("AmountOfFeesSued", ctx.get("AmountOfFeesSued_"+i));
	 				map1.put("isSaved",map2.get("isSaved"));
	 				map1.put("ROWID",map2.get("ROWID"));
	 				newList.add(map1);
	         	 }
			 
		 }
	    	 if(index!=-2)
	    		 newList.remove(index-1);
	    	 for(int i=0;i<newList.size();i++)
         	 {
	    		 ROWID=ROWID+1;
         	 	map2=(HashMap)newList.get(i);
         	 	map2.put("ROWID",ROWID);
 				
         	 }
	    	 
	    	 
	    	 ctx.put("FeeSuitData_list_01",newList);
	    	 sess.setAttribute("FeeSuitData_list_01s", newList);
	    	 
         }catch(Exception e)
         {
        	 logger.error("Unexpected error", e);
         }
}
	 
	/* public static void validateSubProducer(Context ctx)
	 {
		 List subProducerList;
		try {
			subProducerList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetProducerCodeData",ctx);
			List subProducerForPolicy =(List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetProducerCodeForPolicy", ctx);
			ctx.put("isSubProduced","N");
			
			for (int i = 0; i <subProducerList.size(); i++) 
			{
				String producerCode="";
				Map dataMap = (Map) subProducerList.get(i);
				producerCode=dataMap.get("ProducerCode").toString();
				for (int j = 0; j <subProducerForPolicy.size(); j++) 
				{	
					Map dataMapList = (Map) subProducerForPolicy.get(j);
					if(producerCode.equals(dataMapList.get("ProducerCode").toString()))
						ctx.put("isSubProduced","Y");
					
				}
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }*/
/*	  public static void getAttorneysHoursRating(Context ctx)
	 {
		double sum=0;
		try {
			List allAttornieslist = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetAllAttorneys",ctx);
			if(allAttornieslist!=null && allAttornieslist instanceof List )
			{		
				int count=allAttornieslist.size();
				if(count>1)
					ctx.put("MultiOrSolo", "M");
					
				else
					ctx.put("MultiOrSolo", "S");
				
				for (int i = 0; i < allAttornieslist.size(); i++) 
				{
					Map dataMap = (Map) allAttornieslist.get(i);
					if(dataMap.get("AnnualWorkedHours")!=null)
					{
						ctx.put("AnnualWorkedHours", dataMap.get("AnnualWorkedHours"));
						List hoursFactor= (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetFactorForRating",ctx);
						Context newctx=new Context();
						newctx.putAll((Map) hoursFactor.get(0));
						sum=sum+Double.valueOf(newctx.get("Factors").toString());
						logger.debug(sum);
					}
				}
				ctx.put("RatingFactor",sum);
				SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateFactorForRating", ctx);
				Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesupdateFactorForRating", ctx);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }*/
	 
	 public static int getPageNumber (String filePath, String strToCheck) throws IOException {
		 File file =  null;
		 
		 try {
		file =  new File(filePath);		
		PDDocument pddoc = PDDocument.load(file);		
		for(int pageNumber = 1; pageNumber <= pddoc.getNumberOfPages(); pageNumber++){
		    PDFTextStripper s = new PDFTextStripper();
		    s.setStartPage(pageNumber);
		    s.setEndPage(pageNumber);
		    String contents = s.getText(pddoc);  
		    if(contents.contains(strToCheck)){
		    	return pageNumber;
		    }
		}
		
		 }catch(Exception e){
			 logger.error("Unexpected error", e);
		 } finally{
			 /*code by sukhi 26/09/2018*/
				file=null;
		 }
		
		return -1;
	}
	 
	 public static Map<String, Double> getCoordinateXY (String filePath, String strToCheck, int pageNo) throws IOException {
		 return new PrintTextLocations().printSubwords(filePath,  strToCheck, pageNo);		
	}
	 
	 public static Object roundCeiling (Object arg1, Object places){
	      if(arg1 == null)
	          return 0;
	      
	      String formatter = "1";
	      int plVal = 0;
	      double factor = 0.0;
	      if (places != null)
	    	  plVal = Integer.parseInt(places.toString());
	      
	      if(plVal == 0)
	    	  return round(arg1);
	      else{
	    	  for(int i=0; i<plVal; i++){
		    	  formatter = formatter + "0";
		      }
	    	  formatter = formatter + ".0";
	    	  factor = Double.parseDouble(formatter);
	    	  return java.lang.Math.round(Double.parseDouble(arg1.toString())*factor)/factor;
	      }
	 }
	 
	 public static Object round(Object arg1){
	      if(arg1 == null)
	          return 0;
	      
	      return java.lang.Math.round(Double.parseDouble(arg1.toString()));
	}
	
	public static Object round(Object arg1, int places){
	      if(arg1 == null)
	          return 0;
	      
	      BigDecimal bd = new BigDecimal(Double.parseDouble(arg1.toString()));
	        bd = bd.setScale(places, RoundingMode.HALF_UP);
	        return bd.doubleValue();  
	  }
	 
	 public static void calculateClaimAgeFactorFullQuote(Context ctx)
	 {
		 try
	    	{
		    	HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
		        HttpSession sess = req.getSession();
		        
		        List finalList =null;
		        List inputList=new ArrayList();
		        int count=0;
		        
		       if(ctx.get("firm_list_4") != null &&   ctx.get("firm_list_4") instanceof List)
		        {
			        finalList = (List)ctx.get("firm_list_4");
			        Map map = null,map1=null;
			        
					for (int i = 0; i < finalList.size(); i++) 
					{
						map=new HashMap();
						 map1=(Map)(finalList.get(i));
						{
							
							map.put("Year", map1.get("NumberOfYearsInPractice"));
							map.put("PartTime", map1.get("IsNonRatedAttorney"));
							map.put("PolicyKey", ctx.get("PolicyKey"));
							inputList.add(map);
							
						}
					}
					ctx.put("inputList", inputList);
					convertListDataToXML(ctx,"inputList","outputList");
			       
			       
			     }
	    	}
	        catch(Exception e)
	        {
			logger.error("Unable to convert list to XML", e);
	        	
	        }
	 }
	 
		public static void fillIndicationClaimAgeLW(IContext ctx) throws Exception {
			//Delete all data from ClaimAge and AttorneyDetails of current policy Before Adding
			SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteAttorneyDetailsLW", ctx);
			SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteClaimAgeLW", ctx);
			SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteClaimAgeEstimation", ctx);
			
			if (ctx.get("NumberOfLawyers") != null && Integer.parseInt(ctx.get("NumberOfLawyers").toString()) > 0) {

				int numOfLawyers = Integer.parseInt(ctx.get("NumberOfLawyers").toString());
				for (int i = 1; i <= numOfLawyers; i++) {

					if ((ctx.get("ClaimAge_" + i) != null && !"".equals(ctx.get("ClaimAge_" + i)))) {

						Context newctx = new Context();
						newctx.setProject(ctx.getProject());
						newctx.put("Year", ctx.get("ClaimAge_" + i));
						newctx.put("PolicyKey", ctx.get("PolicyKey"));
						newctx.put("VersionKey", ctx.get("VersionKey"));
						newctx.put("VersionSequence", ctx.get("VersionSequence"));
						newctx.put("NumberOfYearsWithFirm", ctx.get("ClaimAge_" + i));
				    	newctx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
				    	newctx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
						DBUtils.executeDBOperation(newctx, "ClaimAgeEstimation", "1");
						DBUtils.executeDBOperation(newctx, "ClaimAgeLW", "1");
						DBUtils.executeDBOperation(newctx, "AttorneyDetailsLW", "1");
					}
				}
			}
		}
		
		public static void populateIndicationClaimAgeData(IContext ctx) throws Exception {
			//To clean claim age in case of difference
			for (int i = 1; i <= LawyersConstants.CLAIM_AGE_COUNT; i++) {
				ctx.remove("ClaimAge_" + (i + 1));
			}
			List objList = SqlResources.getSqlMapProcessor(ctx).select("ClaimAgeLW.findAllByPartialKey", ctx);
			if (objList != null && objList.size() > 0) {
				for (int i = 0; i < objList.size(); i++) {
					Map objMap = (Map) objList.get(i);
					ctx.put("ClaimAge_" + (i + 1), objMap.get("Year"));
				}
			}
		}
		
		public static void populateIndication(IContext ctx) throws Exception {
			ctx.get("IsClaimExpensesType");
			ctx.get("IsDollarDefense");
			
			ctx.put("showUnderwriterLink","N");
			ctx.put("SchduleRatingModifier1", ctx.get("TotalPolicyModifierPercentage"));
			ctx.put("TotalPremium", ctx.get("TotalPremium"));
			ctx.put("InvoicedPremium", ctx.get("InvoicedPremium"));
			ctx.put("XmlOutputDatafromRating", ctx.get("XmlOutputDatafromRating"));
			
			//Refresh County
			/*if(ctx.get("countyChanged") != null && "Y".equals((String)ctx.get("countyChanged"))){
				ctx.remove("CountyDesc");
				ctx.remove("City");
			}*/
					
		}
		
		public static void validateAopPercentageIndication(Context ctx) throws Exception {
			 List aopList = SqlResources.getSqlMapProcessor(ctx).select("AOPLW.findAll", ctx);
			 Context ctx1;
			 int total=0,percentage=0;
			 if (aopList != null) {
				
				try {
					Map map = new HashMap();
					for (int i = 0; i < aopList.size(); i++) {
						map = (HashMap) aopList.get(i);
						String percentageValue = ctx.get("AOP_Percentage_" + map.get("AOPKey").toString()) == null ? null
								: ctx.get("AOP_Percentage_" + map.get("AOPKey").toString()).toString().trim();
						
						if (percentageValue == null || "".equals(percentageValue) || "0".equals(percentageValue))
							percentageValue = "0";
						
						//logger.debug("AOP_Percentage_" + map.get("AOPKey").toString() + "   ....   percentageValue" + percentageValue);
						percentage = Integer.parseInt(percentageValue);
						total = total + percentage;
					}
					if(total>100 ||total<100) {
						populateError(ctx, "totalPercentageError","The total AOP percentage is required to equal 100%, current total is "+ total + "%");
					}
				} catch (Exception e) {
					logger.error("Unexpected error", e);
				}			
			 }
		}
		
		public static void validateClaimAgeIndication(Context ctx){		 
			 boolean flag = true;
			 int lawyers = 0;
			 String field = null;
			 String msg = null;
			 
			 if(ctx.get("NumberOfLawyers") == null || (ctx.get("NumberOfLawyers") != null && "".equals(ctx.get("NumberOfLawyers").toString()))){
				 flag = false;
				 field = "NumberOfLawyers";
				 msg = "Number Of Lawyers is a required field";
			 }
			 
			 if(flag)
				 if(ctx.get("NumberOfLawyers") != null){
					 try{
						 lawyers = Integer.parseInt(ctx.get("NumberOfLawyers").toString());				 
					 } catch (NumberFormatException nfe){
						 flag = false;
						 field = "NumberOfLawyers";
						 msg = "Enter numeric value for Full Time Attys";
					 }
				 }	
			 
			 if(flag)
				 if(lawyers > LawyersConstants.CLAIM_AGE_COUNT){
					flag = false;
					field = "NumberOfLawyers";
					msg = "There cannot be more than " + LawyersConstants.CLAIM_AGE_COUNT + " Full Time Attorneys";
				}
					
			 
			 if(flag){
				 int act = 0;
				 for(int i=1; i<=LawyersConstants.CLAIM_AGE_COUNT; i++){
					if(flag == true){
						String age = null;
						if(ctx.get("ClaimAge_"+i) != null && !"".equals(ctx.get("ClaimAge_"+i).toString()))
							act = act + 1;				 
					} 
				 }
				 if(lawyers != act)
					 flag = false;
				 
				 if(!flag){
					 field = "totalLawyers";
					 msg = "Number of Attorneys should match the number of age of lawyers";
				 }
			 }
			 
			 if(flag){
				 for(int i=1; i<=lawyers; i++){
					if(flag == true){
						String age = null;
						if(ctx.get("ClaimAge_"+i) != null)
							age = ctx.get("ClaimAge_"+i).toString();
						
						 if(age == null || (age != null && "".equals(age)))
							 flag = false;
						
						 if(age != null && !"".equals(age)){
							 try{
							 Integer.parseInt(age);
							 } catch (NumberFormatException nfe){
								 flag = false;
							 }
						 }						 
					} 
				 }				 
				 if(!flag){
					 field = "totalLawyers";
					 msg = "Enter correct values for each Full Time Attys";
				 }
			 }
			 if(flag){
				 for(int i=1; i<=lawyers; i++){
					if(flag == true){
						if(ctx.get("ClaimAge_"+i) != null && !"".equals(ctx.get("ClaimAge_"+i).toString()) && (0 > Integer.parseInt(ctx.get("ClaimAge_"+i).toString()) || 99 < Integer.parseInt(ctx.get("ClaimAge_"+i).toString()))){
							flag = false;
							break;
						}
					} 
				 }
				 if(!flag){
					 field = "totalLawyers";
					 msg = "Number of Attorneys should be between 0 and 99";
				 }
			 }
			//Put Defense Outside for State NJ
			  if(flag){
				 try {
					checkNewFiling(ctx);
				} catch (Exception e) {
					logger.error("Unexpected error", e);
					ctx.put("IsNewFiling", "N");
				}
				 
				 if(!"Y".equals(ctx.get("IsNewFiling"))){
					 if(ctx.get("StateCode") != null && "NJ".equals((String)ctx.get("StateCode"))){
							if(ctx.get("IsClaimExpensesType") == null || (ctx.get("IsClaimExpensesType") != null && "N".equals((String)ctx.get("IsClaimExpensesType")))){
								flag = false;
								field = "IsClaimExpensesType";
								msg = "Defense outside is only allowed for state New Jersey";
							}
						}
				 } 				 
			 }			
				
				
			if(!flag){
				 try {
					populateError(ctx, field, msg);
				} catch (Exception e) {
					logger.error("Unexpected error", e);
				}
			 }
		}
		
		public void processIndication(IContext ctx) throws Exception { 
			/*if( ctx.get("IsPolicyCreatedNew") == null || (ctx.get("IsPolicyCreatedNew") != null && "N".equals(ctx.get("IsPolicyCreatedNew").toString()))){
				ctx.put("IsPolicyDetailChanged", "N");
				ctx.put("IsPolicyCoverageChanged", "Y");
				ctx.put("IsAccountDetailChanged", "Y");
			}
			else
				ctx.put("IsPolicyDetailChanged", "Y");*/
			
			IContext newCtx = new Context();
			newCtx.setProject(ctx.getProject());
			newCtx.putAll(ctx);
			
			 String IsPolicyDetailChanged = "N";
			 String IsPolicyCoverageChanged = "N";
			 String IsAccountDetailChanged = "N";
			 String IsIndicationBatchUpdated = "N";
			 boolean accFlag = true;
			 boolean polFlag = true;
			 boolean covFlag = true;
			 boolean modifierFlag = true;
			 
			 
			//To check changes in existing policy
			if(newCtx.get("IsPolicyCreatedNew") == null || (newCtx.get("IsPolicyCreatedNew") != null && "N".equals(newCtx.get("IsPolicyCreatedNew").toString()))){
				
				Object objPolicy = SqlResources.getSqlMapProcessor(newCtx).findByKey("Policy.findByKey", newCtx);
				 Map  mapPolicy = objPolicy instanceof Map ? (Map) objPolicy : new HashMap();
				 
				 Object objFirmLW = SqlResources.getSqlMapProcessor(newCtx).findByKey("FirmLW.findByKey", newCtx);
				 Map  mapFirmLW = objFirmLW instanceof Map ? (Map) objFirmLW : new HashMap();
				 
				 Object objAccount = SqlResources.getSqlMapProcessor(newCtx).findByKey("SqlStmts.indicationinjackviewgetAccountData", newCtx);
				 Map  mapAccount = objAccount instanceof Map ? (Map) objAccount : new HashMap();
				 
				 Object objAddress = SqlResources.getSqlMapProcessor(newCtx).findByKey("SqlStmts.indicationinjackviewgetAddressdetails", newCtx);
				 Map  mapAddress = objAddress instanceof Map ? (Map) objAddress : new HashMap();
				 
				 Object objPriorActDateProssDetails = SqlResources.getSqlMapProcessor(newCtx).findByKey("SqlStmts.indicationinjackviewgetPriorActDateProssDetails", newCtx);
				 Map  mapPriorActDateProssDetails = objPriorActDateProssDetails instanceof Map ? (Map) objPriorActDateProssDetails : new HashMap();
				 
				 //To check Account changes
				 if(!mapAccount.isEmpty()){	
					 if(accFlag)
						 accFlag = compareValues(newCtx.get("AccountName"), mapAccount.get("AccountName") != null ? mapAccount.get("AccountName") : "");
					 if(accFlag)
						 accFlag = compareValues(newCtx.get("AccountEmail"), mapAccount.get("AccountEmail") != null ? mapAccount.get("AccountEmail") : "");
				 }
				 if(!mapAddress.isEmpty()){	
					 if(accFlag)
						 accFlag = compareValues(newCtx.get("WorkPhone"), mapAddress.get("WorkPhone") != null ? mapAddress.get("WorkPhone") : "");
					 if(accFlag)
						 accFlag = compareValues(newCtx.get("StateCode"), mapAddress.get("StateCode") != null ? mapAddress.get("StateCode") : "");
				 }
				 
				 List objPolicyCoverage = SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyCoverageIndication", newCtx);
				 //To check Policy Coverage or Modifier changes
				 if (objPolicyCoverage != null && objPolicyCoverage.size() > 0) {
					 for (int i = 0; i < objPolicyCoverage.size(); i++) {
						 Map mapPolicyCoverage = (Map) objPolicyCoverage.get(i);
						 covFlag = true;
						 modifierFlag = true;
						 if(covFlag)
							 covFlag = compareValues(newCtx.get("LimitSequence"), mapPolicyCoverage.get("LimitSequence"));
						 if(covFlag)
							 covFlag = compareValues(newCtx.get("DeductibleSequence"), mapPolicyCoverage.get("DeductibleSequence"));
						 if(covFlag)
							 covFlag = compareValues(newCtx.get("IsClaimExpensesType"), mapPolicyCoverage.get("IsClaimExpensesType"));
						 if(covFlag)
							 covFlag = compareValues(newCtx.get("IsClaimOptionType"), mapPolicyCoverage.get("IsClaimOptionType"));
						 if(covFlag)
							 covFlag = compareValues(newCtx.get("IsDollarDefense"), mapPolicyCoverage.get("IsDollarDefense"));						 
						 if(modifierFlag)
							 modifierFlag = compareValues(newCtx.get("SchduleRatingModifier1"), mapPolicyCoverage.get("TotalPolicyModifierPercentage"));
						 
						 if(covFlag && modifierFlag)
							 break;
					 }
					 for (int i = 0; i < objPolicyCoverage.size(); i++) {
						 Map mapPolicyCoverage = (Map) objPolicyCoverage.get(i);

						 if(accFlag)
							 accFlag = compareValues(newCtx.get("ProducerCode"), mapPolicyCoverage.get("ProducerCode") != null ? mapPolicyCoverage.get("ProducerCode") : "");
						 if(accFlag)
							 break;
					 }
				 }
				 if(!accFlag)
					 IsAccountDetailChanged = "Y";
				 if(!covFlag)
					 IsPolicyCoverageChanged = "Y";
				 if(!modifierFlag)
					 IsIndicationBatchUpdated ="Y";	
				 
				 //To check Policy Level changes
				 if(!mapPolicy.isEmpty()){	
					 if(polFlag){
						 long diff = LawyersDateUtils.calculateDiffInDays(LawyersDateUtils.convertStringToDate(newCtx.get("PolicyEffectiveDate").toString(), "MM/dd/yyyy"),LawyersDateUtils.convertStringToDate(mapPolicy.get("PolicyEffectiveDate").toString(), "MM/dd/yyyy"));
						if(diff != 0)
							polFlag = false;
					 }
				 }
				 if(!mapAddress.isEmpty()){	
					 if(polFlag)
						 polFlag = compareValues(newCtx.get("CountyDesc"), mapAddress.get("CountyDesc") != null ? mapAddress.get("CountyDesc") : "");
					 if(polFlag)
						 polFlag = compareValues(newCtx.get("City"), mapAddress.get("City") != null ? mapAddress.get("City") : "");
					 if(polFlag)
						 polFlag = compareValues(newCtx.get("Street"), mapAddress.get("Street") != null ? mapAddress.get("Street") : "");
					 if(polFlag)
						 polFlag = compareValues(newCtx.get("Zip"), mapAddress.get("Zip") != null ? mapAddress.get("Zip") : "");
				 }
				 if(!mapFirmLW.isEmpty()){	
					 if(polFlag)
						 polFlag = compareValues(newCtx.get("NumberOfLawyers"), mapFirmLW.get("NumberOfLawyers") != null ? mapFirmLW.get("NumberOfLawyers").toString().equals("0") ? "" : mapFirmLW.get("NumberOfLawyers") : "");
					 if(polFlag)
						 polFlag = compareValues(newCtx.get("NumberOfLawyers500"), mapFirmLW.get("NumberOfLawyers500") != null ? mapFirmLW.get("NumberOfLawyers500").toString().equals("0") ? "" : mapFirmLW.get("NumberOfLawyers500") : "");
					 if(polFlag)
						 polFlag = compareValues(newCtx.get("NumberOfLawyers1000"), mapFirmLW.get("NumberOfLawyers1000") != null ? mapFirmLW.get("NumberOfLawyers1000").toString().equals("0") ? "" : mapFirmLW.get("NumberOfLawyers1000") : "");
				 }

				 if(!mapPriorActDateProssDetails.isEmpty()){
					 if(polFlag)
						 polFlag = compareValues(newCtx.get("InsuranceCompanyNamePross"), mapPriorActDateProssDetails.get("InsuranceCompanyNamePross") != null ? mapPriorActDateProssDetails.get("InsuranceCompanyNamePross") : "");
					 if(polFlag)
						 polFlag = compareValues(newCtx.get("ProInsPremium"), mapPriorActDateProssDetails.get("ProInsPremium") != null ? mapPriorActDateProssDetails.get("ProInsPremium") : "");
					 if(polFlag)
						 polFlag = compareValues(newCtx.get("LimitSequencePross"), mapPriorActDateProssDetails.get("LimitSequencePross") != null ? mapPriorActDateProssDetails.get("LimitSequencePross") : "");
					 if(polFlag)
						 polFlag = compareValues(newCtx.get("DeductibleSequencePross"), mapPriorActDateProssDetails.get("DeductibleSequencePross") != null ? mapPriorActDateProssDetails.get("DeductibleSequencePross") : "");
				 }
				 
				 if(polFlag){	 
					 List objList = SqlResources.getSqlMapProcessor(newCtx).select("ClaimAgeLW.findAllByPartialKey", newCtx);
					 if (objList != null && objList.size() > 0) {
						for (int i = 0; i < objList.size(); i++) {
							Map objMap = (Map) objList.get(i);
							if(!polFlag)
								break;
							polFlag = compareValues(newCtx.get("ClaimAge_" + (i + 1)), objMap.get("Year") != null ? objMap.get("Year") : "");
						}
					 }
				 }
				
				 if(polFlag){
					 List aoplist = SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementremoveAOPselectAreaOfPracticeLW", newCtx);		
					if (aoplist != null) {
						Map map = new HashMap();
						for (int i = 0; i < aoplist.size(); i++) {
							map = (HashMap) aoplist.get(i);
							if(!polFlag)
								break;
							polFlag = compareValues("".equals(newCtx.get("AOP_Percentage_" + map.get("AOPKey"))) || newCtx.get("AOP_Percentage_" + map.get("AOPKey")) == null ? 
									"0":newCtx.get("AOP_Percentage_" + map.get("AOPKey")), map.get("PercentageValue"));
						}
					}
				 }
				 
				 if(!polFlag){
					 IsPolicyDetailChanged = "Y";
					 IsIndicationBatchUpdated = "Y";
				 }
				 
			 }			 
			//IsAccountDetailChanged = "Y";
			ctx.put("IsPolicyDetailChanged", IsPolicyDetailChanged);
			ctx.put("IsPolicyCoverageChanged", IsPolicyCoverageChanged);
			ctx.put("IsAccountDetailChanged",IsAccountDetailChanged);
			ctx.put("IsIndicationBatchUpdated",IsIndicationBatchUpdated);			
			ctx.put("OldPolicyKey", newCtx.get("PolicyKey"));
			
			if(newCtx.get("SchduleRatingModifier1") == null || (newCtx.get("SchduleRatingModifier1") != null && "".equals(newCtx.get("SchduleRatingModifier1").toString())))
				ctx.put("SchduleRatingModifier1","0");
		}
		
		public void deleteIndication(IContext ctx) throws Exception {			
			//To check existing indication for changes in Account section and delete all and their siblings
			if("Y".equals(ctx.get("IsAccountDetailChanged").toString())
				|| (ctx.get("IsPolicyCreatedNew") != null && "Y".equals(ctx.get("IsPolicyCreatedNew").toString()))){
				List list = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetIndicationAccount", ctx);			
				if(list != null && list.size() > 0){
					for(int i=0; i<list.size(); i++){
						Context newCtx=new Context();
						newCtx.setProject(ctx.getProject());
						newCtx.putAll(ctx);
						newCtx.putAll((Map)list.get(i));
						deleteAccountPolicy(newCtx);
						ctx.put("IsExistingPolicyDeleted", "Y");
					}					 
				} 
			}			
		}
		
		public void splitZipCode(IContext ctx) throws Exception {
			if(ctx.get("Zip") != null && !"".equals(ctx.get("Zip"))){
				String zip = (String) ctx.get("Zip");
				if(zip.indexOf("-") > -1){
					ctx.put("Zip",zip.split("-")[0]);
					if((zip.split("-")).length > 1)
					ctx.put("Zip4",zip.split("-")[1]);
				}
			}
		}
		 
		public boolean compareValues(Object obj1, Object obj2){
			boolean flag = true;
			if(obj1 == null && obj2 == null)
				return true;
			if(obj1 == null && obj2 != null)
				return false;
			if(obj1 != null && obj2 == null)
				return false;
			
			String val1 = obj1.toString();		
			String val2 = obj2.toString();
			if(!val1.equals(val2))
				return false;
			
			return flag;
		}
		
		
		public void updatePolicyData(IContext ctx) throws Exception { 
			 RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");			 
			 
			 SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdatePolicy", ctx);
			 
			 Object objAccount = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetIndicationAccountKeys", ctx);
			 Map  mapAccount = null;
			 if(objAccount != null){
				 mapAccount = (Map) objAccount;
				 String addKey = null;
				 String accKey = null;
				 if(mapAccount.get("AccountKey") != null)
					 accKey = mapAccount.get("AccountKey").toString();
				 if(mapAccount.get("AddressKey") != null)
					 addKey = mapAccount.get("AddressKey").toString();
				
				ctx.put("AddressKey",addKey);
				SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdatePolicyAddress", ctx);				
			 }
			 
			 SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateFirmLWLawyerNo", ctx);
			 
			 SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateProfessionalLiabilityInsDetailLW", ctx);
		}
		
		public void updatePolicyAccountsData(IContext ctx) throws Exception { 
			 IContext ctxNew = new Context();
			 ctxNew.setProject(ctx.getProject());
			 ctxNew.put("AccountName", ctx.get("AccountName"));
			 ctxNew.put("AccountEmail", ctx.get("AccountEmail"));
			 ctxNew.put("WorkPhone", ctx.get("WorkPhone"));
			 ctxNew.put("PolicyKey", ctx.get("PolicyKey"));
			 ctxNew.put("MainPolicyKey", ctx.get("PolicyKey"));
			 updatePolicyAccountData(ctxNew); 
		}

		public void updatePolicyAccountData(IContext ctx) throws Exception { 
			
			 Object objPolicyTransition = SqlResources.getSqlMapProcessor(ctx).findByKey("PolicyTransition.findAllByPartialKey", ctx);
			 Map  mapPolicyTransition = null;
			 if(objPolicyTransition != null){
				 mapPolicyTransition = (Map) objPolicyTransition;
				 
				 Object objAccount = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetIndicationAccountKeys", ctx);
				 Map  mapAccount = null;
				 if(objAccount != null){
					 mapAccount = (Map) objAccount;
					 String addKey = null;
					 String accKey = null;
					 if(mapAccount.get("AccountKey") != null)
						 accKey = mapAccount.get("AccountKey").toString();
					 if(mapAccount.get("AddressKey") != null)
						 addKey = mapAccount.get("AddressKey").toString();
					 
					ctx.put("AccountKey",accKey);
					ctx.put("AddressKey",addKey);
					SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateAddress", ctx);
					SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateAccount", ctx);
					
					String nextPolicyKey = null;
					String previousPolicyKey = null;
					
					if(mapPolicyTransition.get("NextPolicyKey") != null)
						nextPolicyKey = mapPolicyTransition.get("NextPolicyKey").toString();
					if(mapPolicyTransition.get("PreviousPolicyKey") != null)
						previousPolicyKey = mapPolicyTransition.get("PreviousPolicyKey").toString();
					if(nextPolicyKey != null && !nextPolicyKey.equals(ctx.get("MainPolicyKey").toString())){
						ctx.put("PolicyKey", nextPolicyKey);
						updatePolicyAccountData(ctx);
					}
					if(previousPolicyKey != null && !previousPolicyKey.equals(ctx.get("MainPolicyKey").toString())){
						ctx.put("PolicyKey", previousPolicyKey);
						updatePolicyAccountData(ctx);
					}
				 }
			 }		 
		}
		
		
		public void deleteAccountPolicy(IContext ctx) throws Exception { 
			/*
			 Object objPolicyTransition = SqlResources.getSqlMapProcessor(ctx).findByKey("Policy.findByKey", ctx);
			 Map  mapPolicyTransition = null;
			 if(objPolicyTransition != null){
				 mapPolicyTransition = (Map) objPolicyTransition;
				 
				 Object objAccount = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetIndicationAccountKeys", ctx);
				 Map  mapAccount = null;
				 if(objAccount != null && !mapPolicyTransition.isEmpty()){
					 mapAccount = (Map) objAccount;
					 String addKey = null;
					 String accKey = null;
					 if(mapAccount.get("AccountKey") != null)
						 accKey = mapAccount.get("AccountKey").toString();
					 if(mapAccount.get("AddressKey") != null)
						 addKey = mapAccount.get("AddressKey").toString();
					 
					ctx.put("AccountKey",accKey);
					ctx.put("AddressKey",addKey);
					
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteRatingTrace", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteQuote", ctx);
					
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteAttorneyDetailsLW", ctx);
					
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteCoverageModifiers", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeletePolicyCoverage", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeletePolicyTransaction", ctx);
					
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteCommentDetail", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeletePricingComment", ctx);
					
					SqlResources.getSqlMapProcessor(ctx).delete("AccountAddress.delete", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("Account.delete", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("Address.delete", ctx);
					
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteClaimAgeLW", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteAreaOfPracticeLW", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteAreaOfPracticeQuickQuoteLW", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteSmartSearchValue", ctx);

					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteRuleCommentLW", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteRulePolicyTransactionLW", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteDocumentArchiveLW", ctx);
					
					SqlResources.getSqlMapProcessor(ctx).delete("FirmLW.deleteAllByPartialKey", ctx);
					
					deletePoliciesData(ctx);
					
					SqlResources.getSqlMapProcessor(ctx).delete("PolicyTransition.deleteAllByPartialKey", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("Policy.delete", ctx);
					
				 }
			 }		*/ 
			try{
				SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteIndicationLogically", ctx);
				
			}
			catch(Exception e)
			{
				logger.error("Error occured while deleting indication");
				
			}
		}
		
		public void deletePoliciesData(IContext ctx) throws Exception { 
			
			 Object objPolicyTransition = SqlResources.getSqlMapProcessor(ctx).findByKey("PolicyTransition.findAllByPartialKey", ctx);
			 IContext ctxNew = new Context();
			 ctxNew.setProject(ctx.getProject());
			 ctxNew.put("MainPolicyKey", ctx.get("PolicyKey"));
			 
			 Map  mapPolicyTransition = null;
			 if(objPolicyTransition != null){
				 mapPolicyTransition = (Map) objPolicyTransition;
				 
				 String nextPolicyKey = null;
				 String previousPolicyKey = null;
				
				 if(mapPolicyTransition.get("NextPolicyKey") != null)
					nextPolicyKey = mapPolicyTransition.get("NextPolicyKey").toString();
				 if(mapPolicyTransition.get("PreviousPolicyKey") != null)
					 previousPolicyKey = mapPolicyTransition.get("PreviousPolicyKey").toString();
				 
				 if(nextPolicyKey != null && !nextPolicyKey.equals(ctxNew.get("MainPolicyKey").toString())){
					ctxNew.put("PolicyKey", nextPolicyKey);
					deletePolicyData(ctxNew);
				}
				if(previousPolicyKey != null && !previousPolicyKey.equals(ctxNew.get("MainPolicyKey").toString())){
					ctxNew.put("PolicyKey", previousPolicyKey);
					deletePolicyData(ctxNew);
				}
				
				SqlResources.getSqlMapProcessor(ctx).delete("PolicyTransition.deleteAllByPartialKey", ctx);
			 }
		}
		
		public void deletePolicyData(IContext ctx) throws Exception { 
			
			 Object objPolicyTransition = SqlResources.getSqlMapProcessor(ctx).findByKey("PolicyTransition.findAllByPartialKey", ctx);
			 Map  mapPolicyTransition = null;
			 if(objPolicyTransition != null){
				 mapPolicyTransition = (Map) objPolicyTransition;
				 
				 Object objAccount = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetIndicationAccountKeys", ctx);
				 Map  mapAccount = null;
				 if(objAccount != null && !mapPolicyTransition.isEmpty()){
					 mapAccount = (Map) objAccount;
					 String addKey = null;
					 String accKey = null;
					 if(mapAccount.get("AccountKey") != null)
						 accKey = mapAccount.get("AccountKey").toString();
					 if(mapAccount.get("AddressKey") != null)
						 addKey = mapAccount.get("AddressKey").toString();
					 
					ctx.put("AccountKey",accKey);
					ctx.put("AddressKey",addKey);
					
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteRatingTrace", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteQuote", ctx);
					
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteAttorneyDetailsLW", ctx);
					
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteCoverageModifiers", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeletePolicyCoverage", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeletePolicyTransaction", ctx);
					
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteCommentDetail", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeletePricingComment", ctx);
					
					SqlResources.getSqlMapProcessor(ctx).delete("AccountAddress.delete", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("Account.delete", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("Address.delete", ctx);
					
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteClaimAgeLW", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteAreaOfPracticeLW", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteAreaOfPracticeQuickQuoteLW", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteSmartSearchValue", ctx);
					
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteRuleCommentLW", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteRulePolicyTransactionLW", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteDocumentArchiveLW", ctx);
					
					SqlResources.getSqlMapProcessor(ctx).delete("FirmLW.deleteAllByPartialKey", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("PolicyTransition.deleteAllByPartialKey", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete("Policy.delete", ctx);
					
					String nextPolicyKey = null;
					String previousPolicyKey = null;
					
					if(mapPolicyTransition.get("NextPolicyKey") != null)
						nextPolicyKey = mapPolicyTransition.get("NextPolicyKey").toString();
					if(mapPolicyTransition.get("PreviousPolicyKey") != null)
						previousPolicyKey = mapPolicyTransition.get("PreviousPolicyKey").toString();
					
					if(nextPolicyKey != null && !nextPolicyKey.equals(ctx.get("MainPolicyKey").toString())){
						ctx.put("PolicyKey", nextPolicyKey);
						deletePolicyData(ctx);
					}
					if(previousPolicyKey != null && !previousPolicyKey.equals(ctx.get("MainPolicyKey").toString())){
						ctx.put("PolicyKey", previousPolicyKey);
						deletePolicyData(ctx);
					}
				 }
			 }		 
		}
		
		public void processSelectedIndication (IContext ctx) throws Exception { 
			if(ctx.get("SchduleRatingModifier1") == null || (ctx.get("SchduleRatingModifier1") != null && "".equals(ctx.get("SchduleRatingModifier1").toString())))
				ctx.put("SchduleRatingModifier1","0");
			
			List listIndication = null;		
			if(ctx.get("indication_list_01") != null)
				listIndication = (List) ctx.get("indication_list_01");
			
			if(listIndication == null)
				return;
			
			int index = 0;
			if(ctx.get("SelectedIndicationIndex") != null && !"".equals(ctx.get("SelectedIndicationIndex").toString().trim()))
				index = Integer.parseInt(ctx.get("SelectedIndicationIndex").toString());
			else {
				for (int i = 0; i < listIndication.size(); i++) {
					Map objMap = (Map) listIndication.get(i);					
					if(objMap.get("IsQuoteSelected") != null && "Y".equals(objMap.get("IsQuoteSelected").toString())){
						index = i;
						break;
					}
				}
			}
			
			Map indicationMap = null;
			try{
				indicationMap = (Map) listIndication.get(index);
				if(indicationMap != null){
					ctx.put("TransactionSequence",indicationMap.get("TransactionSequence"));
					ctx.put("CoverageSequence",indicationMap.get("CoverageSequence"));				
					ctx.put("RatingTraceSequence",indicationMap.get("RatingTraceSequence"));
				}
			} catch (ArrayIndexOutOfBoundsException ae){
				indicationMap = (Map) listIndication.get(0);
				if(indicationMap != null){
					ctx.put("TransactionSequence",indicationMap.get("TransactionSequence"));
					ctx.put("CoverageSequence",indicationMap.get("CoverageSequence"));				
					ctx.put("RatingTraceSequence",indicationMap.get("RatingTraceSequence"));
				}
			}
			
		}
		
		
		public static Object getLawyersPropertyFromPropertiesFiles(String propertyName)
		{
			Object obj=null;
			try
			{
				obj=SystemProperties.getInstance().getString(propertyName);	
				
			}
			catch(Exception e)
			{
				logger.error("error in fetching data from property file");
			}
			return obj;
		}
		public static boolean validateDateFieldData(String dateString)
		{
			boolean flag=false;
			try{
				logger.debug("going to  Validate  Date field data");
				//String str1=dateString.replace("/", "#");
				StringTokenizer st=new StringTokenizer(dateString,"/");
				
				int month=Integer.parseInt(st.nextToken().trim());
				int day=Integer.parseInt(st.nextToken().trim());
				int year=Integer.parseInt(st.nextToken().trim());
				
				int length=dateString.length();
				if(length!=10)
					flag=true;
				if(day<1 || day>31)
					flag=true;
				if(month<1 || month>12)
					flag=true;				
				// As per Pat on 01/18/2018
				if(year<1900)
					flag=true;
				
			}catch(Exception e){
				logger.error("Exception occured at Validation date data");
				flag=true;
			}
			return	flag;
		}
		
		public static boolean validateCounty(Context ctx){		 
			 boolean flag = true;
			 String state = null;
			 String county = "";
			 if(ctx.get("StateCode") != null)
				 state = ctx.get("StateCode").toString();
			 if(ctx.get("CountyDesc") != null)
				 county = ctx.get("CountyDesc").toString();

			 for(int i=0; i<countyRequiredStates.length; i++){
				 if(countyRequiredStates[i].equals(state) && ("".equals(county) || "Select".equals(county)))
					 flag = false;				 
			 }			 
			 
			/*if(!flag){
				 try {
					populateError(ctx, "CountyDesc", "County is a required field.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			 }*/
			return flag;
		}
		
		public void processSaveIndicationQuotes(IContext ctx) throws Exception { 			
			 Object obj = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetIndicationQuotesList", ctx);
			 if(obj == null)
				 return;
			 
			 ctx.put("MainVersionSequence", ctx.get("VersionSequence"));
			 ctx.put("MainTransactionSequence", ctx.get("TransactionSequence"));
			 ctx.put("MainCoverageSequence", ctx.get("CoverageSequence"));
			 ctx.put("MainRatingTraceSequence", ctx.get("RatingTraceSequence"));
			 ctx.put("MainSchduleRatingModifier1", ctx.get("SchduleRatingModifier1"));
			 
			 List quoteList = null;	
			 Map  mapQuote = null;
			 boolean flag = true;
			 DocumentManagment docMngt = null;
			 if(obj != null) {
				 quoteList = (List) obj;
				 for(int i=0; i< quoteList.size(); i++){
					 mapQuote = (Map) quoteList.get(i);					 
					 if(flag){
						 ctx.putAll(mapQuote);						 
						 flag = processIndicationQuote(ctx);
						 if(flag){
							 SqlResources.getSqlMapProcessor(ctx).update("PolicyTransaction.update", ctx);
							 SqlResources.getSqlMapProcessor(ctx).update("PolicyCoverage.update", ctx);
							 SqlResources.getSqlMapProcessor(ctx).update("RatingTrace.update", ctx);
							 SqlResources.getSqlMapProcessor(ctx).update("Quote.update",ctx);
						 }
					 }
						 
					 if(!flag)
						 break;
				 }
				 
			 }
			 
			 //To populate initial quote
			 ctx.put("VersionSequence", ctx.get("MainVersionSequence"));
			 ctx.put("TransactionSequence", ctx.get("MainTransactionSequence"));
			 ctx.put("CoverageSequence", ctx.get("MainCoverageSequence"));
			 ctx.put("RatingTraceSequence", ctx.get("MainRatingTraceSequence"));
			 ctx.remove("MainVersionSequence");
			 ctx.remove("MainTransactionSequence");
			 ctx.remove("MainCoverageSequence");
			 ctx.remove("MainRatingTraceSequence");
			 ctx.remove("MainSchduleRatingModifier1");
		}
		
		public void processIndicationQuotes(IContext ctx) throws Exception { 			
			 Object obj = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetIndicationQuotesList", ctx);
			 if(obj == null)
				 return;
			 
			 ctx.put("MainVersionSequence", ctx.get("VersionSequence"));
			 ctx.put("MainTransactionSequence", ctx.get("TransactionSequence"));
			 ctx.put("MainCoverageSequence", ctx.get("CoverageSequence"));
			 ctx.put("MainRatingTraceSequence", ctx.get("RatingTraceSequence"));
			 ctx.put("MainSchduleRatingModifier1", ctx.get("SchduleRatingModifier1"));
			 
			 List quoteList = null;	
			 Map  mapQuote = null;
			 boolean flag = true;
			 DocumentManagment docMngt = null;
			 if(obj != null) {
				 quoteList = (List) obj;
				 for(int i=0; i< quoteList.size(); i++){
					 mapQuote = (Map) quoteList.get(i);					 
					 if(flag){
						 ctx.putAll(mapQuote);						 
						 flag = processIndicationQuote(ctx);
						 if(flag){
							 SqlResources.getSqlMapProcessor(ctx).update("PolicyTransaction.update", ctx);
							 SqlResources.getSqlMapProcessor(ctx).update("PolicyCoverage.update", ctx);
							 SqlResources.getSqlMapProcessor(ctx).update("RatingTrace.update", ctx);
							 SqlResources.getSqlMapProcessor(ctx).update("Quote.update",ctx);
						 }
					 }
						 
					 if(!flag)
						 break;
				 }
				 
				 if(flag){
					 SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteDocumentArchiveLWBatch", ctx);
					 docMngt = new DocumentManagment();
					 for(int i=0; i< quoteList.size(); i++){
						 mapQuote = (Map) quoteList.get(i);
						 ctx.putAll(mapQuote);
						 docMngt.processIndicationDocument(ctx);
					 }
				 }
			 }
			 
			 //To populate initial quote
			 ctx.put("VersionSequence", ctx.get("MainVersionSequence"));
			 ctx.put("TransactionSequence", ctx.get("MainTransactionSequence"));
			 ctx.put("CoverageSequence", ctx.get("MainCoverageSequence"));
			 ctx.put("RatingTraceSequence", ctx.get("MainRatingTraceSequence"));
			 ctx.remove("MainVersionSequence");
			 ctx.remove("MainTransactionSequence");
			 ctx.remove("MainCoverageSequence");
			 ctx.remove("MainRatingTraceSequence");
			 ctx.remove("MainSchduleRatingModifier1");
		}
		
		public static boolean processIndicationQuote(IContext ctx) throws Exception {
			WebservicecallImpl webSer = null;
			/*IContext newCtx = new Context();			
			newCtx.setProject(ctx.getProject());
			newCtx.putAll(quoteMap);
			newCtx.put("PolicyKey", ctx.get("PolicyKey"));
			newCtx.put("VersionSequence", ctx.get("VersionSequence"));
			newCtx.put("AddressKey", ctx.get("AddressKey"));
			newCtx.put("StateCode", ctx.get("StateCode"));
			newCtx.put("AddressTypeKey", ctx.get("AddressTypeKey"));
			newCtx.put("CompanyKey", ctx.get("CompanyKey"));
			newCtx.put("LOBKey", ctx.get("LOBKey"));
			newCtx.put("LookupStateCode", ctx.get("LookupStateCode"));
			newCtx.put("ERPYear", ctx.get("ERPYear"));
			
			newCtx.put("PolicyEffectiveDate", ctx.get("PolicyEffectiveDate"));
			newCtx.put("NumberOfLawyers", ctx.get("NumberOfLawyers"));
			newCtx.put("NumberOfLawyers500", ctx.get("NumberOfLawyers500"));
			newCtx.put("NumberOfLawyers1000", ctx.get("NumberOfLawyers1000"));
			newCtx.put("SchduleRatingModifier1", ctx.get("SchduleRatingModifier1"));*/
			
			Rating.populateInputRequestIndication(ctx);

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
				logger.error("Unable to read rating service URL", e);
			}

			if (url == null)
				return false;

			webSer.setServiceurl(url);
			ctx.put("responseMap", webSer.execute(ctx));
			Element outElementError = new LawyersValidationUtils().checkIfErrorOutput(ctx.get("responseMap"));
			if (outElementError != null) {
				List responseErrorList = new LawyersValidationUtils().getErrorListFromResponse(outElementError);
				StringBuffer buffer = new LawyersValidationUtils().addListToInetErrorList(responseErrorList);
				LawyersUtils.populateError(ctx, "SchduleRatingModifier1", buffer != null ? buffer.toString() : null);
				return false;
			} else {
				Rating.populateOuputResponseIndication(ctx);
				return true;				
			}
		}
		
		public void sendCompleteAppLinkMail(IContext ctx) throws Exception {
			
			Object  obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetEndorsementAccountDetails", ctx);
			
			if(obj != null && obj instanceof Map){
		        Map map1 = (Map)obj;
		        ctx.putAll(map1);
		    }
			
			String dstId = "";
			// Do not send quote letter mail to insured if producer code is
			// there
			boolean producerCodeExist = new LawyersValidationUtils().checkIfSubProducerExist(ctx);
			if (producerCodeExist) {
				try {
					
					if("Y".equals(ctx.get("IsDriect")))
						dstId = LawyersUtils.getEmailID(ctx);
					else
						dstId = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.email");
					
				} 
				catch (Exception e) {
					logger.error("Unable to resolve notification recipient", e);
				}
			} else {
				dstId = LawyersUtils.getEmailID(ctx);
			}
			
			String bccAddress="";
			if("".equals(bccAddress))
				bccAddress=SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.bcc.email");
			if ("".equals(dstId))
				return;
			logger.debug("Mail is going---- "); 
			MailSender mail = new MailSender();
			mail.setToAdd(dstId);
			mail.setCcAdd(getFirstReviewerAgentEmailID(ctx));
			mail.setBccAdd(bccAddress);
		//	mail.setCcAdd(cc_role_desc);
			mail.setSubject("Protexure Lawyers Application for " + ctx.get("AccountName").toString() +"");
			mail.setContentType("text/html");
			mail.setBody(generateCompleteAppLinkMailBody(ctx));
			mail.sendMail((Context) ctx);
			logger.debug("Mail has sent---- ");

		}

		/*private String generateCompleteAppLinkMailBody(IContext ctx) throws Exception {
			String msg = "";

			msg = msg + "<table>";
			msg = msg + "<tr>";
			msg = msg + "<td>";

			msg = msg + "Dear (" + ctx.get("AccountName").toString() + "),"
					+ "<br/><br/>";
			msg = msg + "Click <a href="
					+ getProjectUrl(ctx) + "/completeapplink.jsp?PolicyKey="
					+ ctx.get("PolicyKey").toString() + ">here</a>"
					+ " to complete your account with us."
					+ "<br/><br/> ";
			msg = msg
					+ "If you have any questions please contact us at 1-877-569-4111. "
					+ "<br/><br/>";
			msg = msg + "Sincerely," + "<br/><br/>";
			msg = msg + "<b>The Protexure Lawyers Team</b>" + "<br/>";

			msg = msg + "</td>";
			msg = msg + "</tr>";
			msg = msg + "</table>";

			return msg;
		}
*/
	      private String generateCompleteAppLinkMailBody(IContext ctx) throws Exception {
	    	  LawyersValidationUtils utils = new LawyersValidationUtils();
	    		String encrytedPolicyKey = utils.encrypt(ctx.get("PolicyKey").toString().trim());
	    		encrytedPolicyKey = URLEncoder.encode(encrytedPolicyKey,""+ StandardCharsets.UTF_8);
	    		logger.debug("Encrypt Policykey for CompletedApp link is :"+encrytedPolicyKey);
              String completeAppLink = getProjectUrl(ctx) + "/completeapplink.jsp?PolicyKey=" + encrytedPolicyKey;
              StringBuilder msg = new StringBuilder(2048);
              
              msg.append("<table>");
              msg.append("<tr>");
              msg.append("<td>");
              
             
              msg.append("Thank you for beginning the application process with Protexure!<br/><br/>"); 
              
              msg.append("If you are unable to complete the application for any reason, don't worry!<br/>");
              msg.append("The information you've provided isn't lost.<br/><br/>");
              

              /*msg = msg + "Dear (" + ctx.get("AccountName").toString() + "),"
                          + "<br/><br/>";*/
              msg.append("All you need to do is  <a href=")
                          .append(completeAppLink).append(">continue here</a>, and you will be able to:")
                          /*+ " to complete your account with us."*/
                          
                          .append("<br/><br/> ");
              
              msg.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &#8226;   &nbsp; <a href=")
                          .append(completeAppLink).append(">View the information entered so far</a><br/> ");
              
              msg.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &#8226;   &nbsp; <a href=")
                                      .append(completeAppLink).append(">Edit and continue to complete the application</a><br/> ");
             
    		  
              msg.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &#8226;   &nbsp; <a href=")
                          .append(completeAppLink).append(">Get your Quote!</a><br/><br/> ");
              
              msg.append("Applications can be a pain, Protexure offers an easy to complete annual online application process.<br/><br/>");
              msg.append("If you have any questions, or need assistance with completing the application, please feel free to call us")
                           .append(" at 877-569-4111 and one of our licensed insurance professionals will gladly assist you.<br/><br/>");
              msg.append("Sincerely,<br/><br/>");
              msg.append("The Protexure Team<br/>");

              msg.append("</td>");
              msg.append("</tr>");
              msg.append("</table>");

              return msg.toString();
        }
	      
      public static void checkNewFiling(IContext ctx) throws Exception {
	    	//To set new filing flag
    	  	String dt = ctx.get("PolicyEffectiveDate") == null ? "" : ctx.get("PolicyEffectiveDate").toString();
	  		boolean isRatingNew = false;        
	  		Object objRatingRule = RuleUtils.executeRule(ctx,"LawyersRule.checkNewFiling");
	          if (objRatingRule != null && objRatingRule instanceof Boolean) {
	          	isRatingNew = (Boolean) objRatingRule;
	          }	        
	  		
	          if(isRatingNew){
	  			if("Y".equals(ctx.get("IsNewFiling2020"))){
	  				ctx.put("IsNewFiling2020", "Y");
	  				ctx.put("IsNewFiling", "Y");
	  			} else if("Y".equals(ctx.get("IsNewFiling"))){
	  				ctx.put("IsNewFiling2020", "N");
	  				ctx.put("IsNewFiling", "Y");
	  			}
	  		}
	  		else{
	  			ctx.put("IsNewFiling2020", "N");
	  			ctx.put("IsNewFiling", "N");
	  		}
	  			  		
	  		// Signing Form State MT if final quote is defense inside
	  		/*if("MT".equals(ctx.get("StateCode").toString())){	  			
		  		String IsClaimExpensesType = "N";
				Object objFinalisedQuote = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementformgetFinalisedQuote", ctx);
				Map mapFinalisedQuote = null;
	
				if (objFinalisedQuote != null && objFinalisedQuote instanceof Map)
					mapFinalisedQuote = (Map) objFinalisedQuote;
	
				if (mapFinalisedQuote != null && !mapFinalisedQuote.isEmpty()) {
					if (mapFinalisedQuote.get("IsClaimExpensesType") != null)
						IsClaimExpensesType = mapFinalisedQuote.get("IsClaimExpensesType").toString();
				}
				
				if("N".equals(IsClaimExpensesType)){
					ctx.put("IsSignFormMT", "Y");
				}
	  		}*/
      }	
	      
    /*  public static void getValueofSubproducerFromSession(IContext ctx) throws Exception   
		 	 {
		 		 if(ctx.get(Constants.INET_PAGE).toString().equals("workq") && ctx.get("ProducerCode") != null
		 					&& !HtmlConstants.EMPTY.equals(ctx.get("ProducerCode").toString()))
		 		 {
		 		    if(ctx.get("ProducerCode")=="P0000001")
		 		    {
		 			 
		 		     ctx.put("ProducerCode","P0000001");
		 		    }
		 		   }
		 		 
		 		HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
		        HttpSession sess = req.getSession();
		        ctx.put("ProducerCode",sess.getAttribute("ProducerCode"));
		        ctx.put("ProducerCodeSub",sess.getAttribute("ProducerCode"));
		 	 }    
		     */
		     /* public static void getValueofSubproducerForFilter(IContext ctx) throws Exception   
			 	 {
			 		 if(ctx.get(Constants.INET_PAGE).toString().equals("filter") && ctx.get("ProducerCode_search") != null
			 					&& !HtmlConstants.EMPTY.equals(ctx.get("ProducerCode_search").toString()))
			 		 {
			 		    if(ctx.get("ProducerCode_search")=="P0000001")
			 		    {
			 			 
			 		     ctx.put("P0000001",ctx.get("ProducerCode_search"));
			 		    }
			 		    else
			 		    {
			 		    	
			 		    	ctx.put("P0000002",ctx.get("ProducerCode_search"));
			 		    }
			 		 }
			 		ctx.put("user_id","1107");
					 ctx.put("UserNo","1107"); 
			 	 }
*/

	      
		    /*public static void setInsuranceDataForSpecialStates(Context ctx)
		    {
		    	logger.debug("");
		    	String stateCode=ctx.get("StateCode").toString();
		    	int limitSequence=Integer.parseInt(ctx.get("LimitSequence").toString());
		    	if(stateCode.equals("AR") ||  stateCode.equals("OK") || stateCode.equals("NJ"))
		    			{		
		    		if(limitSequence < 33){
		    			ctx.put("IsClaimExpensesType","Y");		    						
		    		}	
		    		else
		    			ctx.put("IsClaimExpensesType",ctx.get("claimEXType"));
		    	}
		    	 if(stateCode.equals("NM")|| stateCode.equals("VT"))
		    	{
		    		 ctx.put("IsClaimExpensesType","Y");			
		    	}
		    	
		    	if(stateCode.equals("NJ")|| stateCode.equals("NM")){
		    		 ctx.put("IsDollarDefense","Y");		    		
		    	}
		    }*/
		  

	public static void insertZOHORecords(Context ctx) {
      	try
  		{
			getZohoToken(ctx);
      		String zohoAccountID = insertZOHORecordsOfAccounts(ctx);
      		if(zohoAccountID != null && !"".equals(zohoAccountID) && !zohoAccountID.isEmpty()){
      			insertZOHORecordsOfPotentials(ctx, zohoAccountID);
      		}
  		} catch(Exception e) {
  			logger.error("Unexpected error", e);
  		}
  	}
    
	public static void updateZOHORecords(Context ctx){
		try 
  		{
			getZohoToken(ctx);
      		String zohoAccountID = insertZOHORecordsOfAccounts(ctx);
      		if(zohoAccountID != null && !"".equals(zohoAccountID) && !zohoAccountID.isEmpty()){
      			insertZOHORecordsOfPotentials(ctx, zohoAccountID);
      		}
  		} catch(Exception e) {
  			logger.error("Unexpected error", e);
  		}
	}
	
	public static void updateZohoToken(IContext ctx)
	{
		try
		{
			int status = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateZohoAccessToken", ctx);
			logger.debug(status);
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
			logger.error("Exception occured while updateZohoToken");
		}
	}
	

	
	public static void getZohoToken(IContext ctx)
	{
		try
		{
			if(ctx.get("isZohoAccessToken") == null || "".equals(ctx.get("isZohoAccessToken"))){
				String getZohoAccessToken = "";
				List getZohoAccessTokenList = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoAccessToken", ctx);
	    		if(getZohoAccessTokenList != null && getZohoAccessTokenList.size() > 0 && !getZohoAccessTokenList.isEmpty()){
	    			Map getZohoAccessTokenMap = (Map)getZohoAccessTokenList.get(0);
	    			getZohoAccessToken = (getZohoAccessTokenMap.get("ZohoAccessToken") == null ? "" : getZohoAccessTokenMap.get("ZohoAccessToken").toString());
	    		}
	    		ctx.put("isZohoAccessToken",getZohoAccessToken);
			}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
			logger.error("Exception occured while updateZohoToken");
		}
	}
	
    public static JSONObject getZOHORecordOfPotentials(Context ctx, String[] matchColumns, String[] matchColumnValues, String zohoAccountID)
	{
    	GetMethod get = null;
      	try{
      		System.setProperty("javax.net.ssl.trustStore", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStore"));
			System.setProperty("javax.net.ssl.keyStorePassword", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStorePassword"));
			System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
      		
  	    	String serachTargetURL = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.modulesurl") + "/Potentials/search?";
//  	    	https://crm.zoho.com/crm/private/xml/Potentials/searchRecords?
//  	    	https://www.zohoapis.com/crm/v2/Potentials/search?
//  	    	https://www.zohoapis.com/crm/v2/Leads/search?
  	    	String authtoken = "Zoho-oauthtoken " + ctx.get("isZohoAccessToken");//If you don't have a authtoken please refer this wiki https://zohocrmapi.wiki.zoho.com/using-authtoken.html 
  			String selectColumns ="Account_Name,Deal_Name,Owner,Closing_Date,Stage,Premium,Policy,Expiring_Premium,Carrier";
  			String criteria = "(";
  			for(int criteriaCount = 0; criteriaCount < matchColumns.length; criteriaCount++){

  				if("Account_Name".equals(matchColumns[criteriaCount]) && ctx.get(matchColumnValues[criteriaCount]) != null && !"".equals(ctx.get(matchColumnValues[criteriaCount]))){
  					criteria += "(" + matchColumns[criteriaCount] + ".id:equals:" + zohoAccountID + ")";
  				}
//  				if("Account_Name".equals(matchColumns[criteriaCount]) && ctx.get(matchColumnValues[criteriaCount]) != null && !"".equals(ctx.get(matchColumnValues[criteriaCount]))){
//  					criteria += "((" + matchColumns[criteriaCount] + ":equals:" + URLEncoder.encode(ctx.get(matchColumnValues[criteriaCount]).toString(), "UTF-8") + ")";
//  					criteria += "AND(" + matchColumns[criteriaCount] + ".id:equals:" + zohoAccountID + "))";
//  				}
  				if("Deal_Name".equals(matchColumns[criteriaCount]) && ctx.get(matchColumnValues[criteriaCount]) != null && !"".equals(ctx.get(matchColumnValues[criteriaCount]))){
  					criteria += "AND(" + matchColumns[criteriaCount] + ":equals:" + ctx.get(matchColumnValues[criteriaCount]).toString() + ")";
  				}
  			}
  			criteria += ")";
  			
  			//Authorization: Zoho-oauthtoken d92d401c803988c5cb849d0b4215f52"
  			//----------------------------Fetch ZOHO Data ----------------------
  			serachTargetURL += "fields=" + selectColumns + "&criteria=" + criteria;
  			get = new GetMethod(serachTargetURL);
  			get.setRequestHeader("Authorization", authtoken);
  			
  			HttpClient httpclient = new HttpClient();
  			int result = httpclient.executeMethod(get);
  			
			logger.debug("Zoho HTTP status: " + result);

  			if(get.getResponseBodyAsString() != null && !"".equals(get.getResponseBodyAsString())){
  				return new JSONObject(get.getResponseBodyAsString());
  			}
      	} catch(Exception e){
      		logger.error("Unexpected error", e);
  		}
      	return null;
	}
	
	public static void insertZOHORecordsOfPotentials(Context ctx, String zohoAccountID) {
    	try 
		{
    		Map<String,String> ownerMap = new HashMap();
////    		ownerMap.put("Abbey Kenney", "203455000026483009");
////    		ownerMap.put("Alex Pendzinski", "203455000065574005");
//    		ownerMap.put("Amber Weiss", "203455000125787001");
//    		ownerMap.put("Andrea Lakatos", "203455000210815001");
////    		ownerMap.put("Brett Walker", "203455000006109001");
////    		ownerMap.put("Brian Vierk", "203455000021508463");
////    		ownerMap.put("Clayton Todd", "203455000063136023");
////    		ownerMap.put("Cloud Bakers", "203455000063357085");
//    		ownerMap.put("Curtis Barndollar", "203455000025471105");
//    		ownerMap.put("Dane Keelan", "203455000147603001");
////    		ownerMap.put("Danielle Peterson", "203455000022242025");
////    		ownerMap.put("Dean Kostopoulos", "203455000100117001");
//    		ownerMap.put("Emma Sepke", "203455000020490353");
//    		ownerMap.put("Fran Moreno", "203455000000166011");
////    		ownerMap.put("Gabe", "203455000006109007");
////    		ownerMap.put("GWeiss", "203455000006109007");
//    		ownerMap.put("HubSpot  Sync", "203455000115995118");
////    		ownerMap.put("J Meyer Meyer", "203455000006899216");
////    		ownerMap.put("J Meyer", "203455000006899216");
////    		ownerMap.put("Jennifer Seay", "203455000041792003");
//    		ownerMap.put("Jessica Wolf", "203455000028699009");
////    		ownerMap.put("Jody Montalto", "203455000026628013");
////    		ownerMap.put("Kandace Dickeson", "203455000070024001");
//    		ownerMap.put("Katie Long", "203455000167715001");
////    		ownerMap.put("Karly Wells", "203455000046971004");
//    		ownerMap.put("Lorelei Lorenzen", "203455000000112003");
////    		ownerMap.put("Lori Michi", "203455000023364001");
//    		ownerMap.put("Natalie Long", "203455000133617001");
////    		ownerMap.put("Nicohle Johnson", "203455000175798001");
//    		ownerMap.put("Outline Systems", "203455000031179005");
//    		ownerMap.put("Pat Cosulich", "203455000000033001");
//    		ownerMap.put("Quiana Bennett", "203455000173489001");
//    		ownerMap.put("Ryan Schwartz", "203455000000166005");
//    		ownerMap.put("Sales Archive", "203455000125034001");
//    		ownerMap.put("Sara Lang", "203455000008114038");
////    		ownerMap.put("Terry Odekirk", "203455000000268538");
////    		ownerMap.put("Tonya Wentz", "203455000013177421");
    		
    		List getZohoUserDetails = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoUserDetails", ctx);
			if(getZohoUserDetails != null && getZohoUserDetails.size() > 0 && !getZohoUserDetails.isEmpty()){
				for (int zohoUserDetailsCount = 0; zohoUserDetailsCount < getZohoUserDetails.size(); zohoUserDetailsCount++) {
					Map getZohoUserDetailsList = (Map)getZohoUserDetails.get(zohoUserDetailsCount);
					ownerMap.put(getZohoUserDetailsList.get("AgentName").toString(), getZohoUserDetailsList.get("ZohoUserID").toString());
				}
			}
    		
    		String previousPolicyKey = "";
    		String previousVersionSequence = "";
			List getZohoPreviousPolicyKey = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey", ctx);
			if(getZohoPreviousPolicyKey != null && getZohoPreviousPolicyKey.size() > 0 && !getZohoPreviousPolicyKey.isEmpty()){
    			Map getZohoPreviousPolicyKeyList = (Map)getZohoPreviousPolicyKey.get(0);
    			previousPolicyKey = (getZohoPreviousPolicyKeyList.get("PreviousPolicykey") == null ? "" : getZohoPreviousPolicyKeyList.get("PreviousPolicykey").toString());
    			previousVersionSequence = (getZohoPreviousPolicyKeyList.get("previousVersionSequence") == null ? "" : getZohoPreviousPolicyKeyList.get("previousVersionSequence").toString());
    		}
			
    		String zohoPotentialsID = "";
    		List getZohoPotentialsID = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoAccountID", ctx);
    		if(getZohoPotentialsID != null && getZohoPotentialsID.size() > 0 && !getZohoPotentialsID.isEmpty()){
    			Map getZohoPotentialsIDList = (Map)getZohoPotentialsID.get(0);
    			zohoPotentialsID = (getZohoPotentialsIDList.get("ZohoPotentialsID") == null ? "" : getZohoPotentialsIDList.get("ZohoPotentialsID").toString());
    		}
    		
    		String statusDesc = "";
    		String stateCode = "";
    		String accountName = "";
    		List getPolicyStatus = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoStatus", ctx);
    		if(getPolicyStatus != null && getPolicyStatus.size() > 0 && !getPolicyStatus.isEmpty()){
    			Map getPolicyStatusList = (Map)getPolicyStatus.get(0);
    			stateCode = (getPolicyStatusList.get("StateCode") == null ? "" : getPolicyStatusList.get("StateCode").toString());
    			statusDesc = (getPolicyStatusList.get("StatusDesc") == null ? "" : getPolicyStatusList.get("StatusDesc").toString());
    			accountName = (getPolicyStatusList.get("AccountName") == null ? "" : getPolicyStatusList.get("AccountName").toString());
    		}
    		
    		if("New".equals(statusDesc)){ // && "Y".equals(ctx.get("IsQuickQuote"))
    			statusDesc = "Indicated";
    		} else  if("UnderReview".equals(statusDesc) || "UW Review".equals(statusDesc) || "UW Completed".equals(statusDesc)){
    			statusDesc = "Under Review";
    		} else  if("Indication".equals(statusDesc)){
    			statusDesc = "Indicated";
    		} else  if("Declined".equals(statusDesc)){
    			statusDesc = "Declined/Non-Renewed";
    		}
    		
    		String totalPremium = "";
    		ctx.put("PolicyKey",Integer.parseInt(ctx.get("PolicyKey").toString()));
    		List getPolicyTotalPremium = (List)SqlResources.getSqlMapProcessor(ctx).select("Quote.GetZohoPremiumLW_p", ctx);
    		if(getPolicyTotalPremium != null && getPolicyTotalPremium.size() > 0 && !getPolicyTotalPremium.isEmpty()){
    			Map getPolicyTotalPremiumList = (Map)getPolicyTotalPremium.get(0);
    			totalPremium = (getPolicyTotalPremiumList.get("InvoicedPremium") == null ? "" : getPolicyTotalPremiumList.get("InvoicedPremium").toString());
    		}
    		ctx.put("PolicyKey",String.valueOf(ctx.get("PolicyKey")));
    		
    		String firstReviewer = "";
    		List getZohoFirstReviewer = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoFirstReviewer", ctx);
    		if(getZohoFirstReviewer != null && getZohoFirstReviewer.size() > 0 && !getZohoFirstReviewer.isEmpty()){
    			Map getFirstReviewerList = (Map)getZohoFirstReviewer.get(0);
    			firstReviewer = (getFirstReviewerList.get("AgentName") == null ? "" : getFirstReviewerList.get("AgentName").toString().trim());
    		}
    		
    		String expiringPremium = "";
    		String carrier = "";
    		String priorActDatePross = "";
    		List getZohoPriorActDatePross = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoPriorActDateProssDetails", ctx);
    		if(getZohoPriorActDatePross != null && getZohoPriorActDatePross.size() > 0 && !getZohoPriorActDatePross.isEmpty()){
    			Map getPriorActDateProssList = (Map)getZohoPriorActDatePross.get(0);
    			priorActDatePross = (getPriorActDateProssList.get("PriorActDatePross") == null ? "" : getPriorActDateProssList.get("PriorActDatePross").toString());
    			expiringPremium = (getPriorActDateProssList.get("ProInsPremium") == null ? "" : getPriorActDateProssList.get("ProInsPremium").toString());
    			carrier = (getPriorActDateProssList.get("InsuranceCompanyNamePross") == null ? "" : getPriorActDateProssList.get("InsuranceCompanyNamePross").toString());
    		}
    		
    		String policyEffectiveDate = "";
    		String quoteType = "";
    		String policyStatusKey = "";
    		List getPolicyEffectiveDate = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoPolicyEffectiveDate", ctx);
    		if(getPolicyEffectiveDate != null && getPolicyEffectiveDate.size() > 0 && !getPolicyEffectiveDate.isEmpty()){
    			Map getPolicyEffectiveDateList = (Map)getPolicyEffectiveDate.get(0);
    			policyEffectiveDate = (getPolicyEffectiveDateList.get("PolicyEffectiveDate") == null ? "" : getPolicyEffectiveDateList.get("PolicyEffectiveDate").toString());
    			quoteType = (getPolicyEffectiveDateList.get("QuoteType") == null ? "" : getPolicyEffectiveDateList.get("QuoteType").toString());
    			policyStatusKey = (getPolicyEffectiveDateList.get("PolicyStatusKey") == null ? "" : getPolicyEffectiveDateList.get("PolicyStatusKey").toString());
    		}
    		
    		String subProducerName = "";
    		String producerCode = "";
    		List getSubProducerNameList = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoDealSubProducerName", ctx);
    		if(getSubProducerNameList != null && getSubProducerNameList.size() > 0 && !getSubProducerNameList.isEmpty()){
    			Map getSubProducerNameMap = (Map)getSubProducerNameList.get(0);
    			subProducerName = (getSubProducerNameMap.get("ProducerCompanyName") == null ? "" : getSubProducerNameMap.get("ProducerCompanyName").toString());
    			producerCode = (getSubProducerNameMap.get("ProducerCode") == null ? "" : getSubProducerNameMap.get("ProducerCode").toString());
    		}
    		
			String[] matchColumns = {"Account_Name","Deal_Name"};
			String[] matchColumnValues = {"Account_Name","Deal_Name"};
			
			String keys = "Account_Name,Deal_Name,Owner,Closing_Date,Stage,Premium,Policy,Expiring_Premium,Carrier,id";
			String values = "Account_Name,Deal_Name,Owner,Closing_Date,Stage,Premium,Policy,Expiring_Premium,Carrier,id";
			
			if("2".equals(policyStatusKey)){
				keys = "Account_Name,Deal_Name,Owner,Closing_Date,Stage,Premium,Policy,Expiring_Premium,id";
				values = "Account_Name,Deal_Name,Owner,Closing_Date,Stage,Premium,Policy,Expiring_Premium,id";
				
				Context ctx1 = new Context();
				ctx1.putAll(ctx);
				
	    		ctx1.put("PolicyKey",Integer.parseInt(previousPolicyKey));
	    		List getPolicyExpiringPremium = (List)SqlResources.getSqlMapProcessor(ctx1).select("Quote.GetZohoPremiumLW_p", ctx1);
	    		if(getPolicyExpiringPremium != null && getPolicyExpiringPremium.size() > 0 && !getPolicyExpiringPremium.isEmpty()){
	    			Map getPolicyExpiringPremiumList = (Map)getPolicyExpiringPremium.get(0);
	    			expiringPremium = (getPolicyExpiringPremiumList.get("InvoicedPremium") == null ? "" : getPolicyExpiringPremiumList.get("InvoicedPremium").toString());
	    		}
			}
			
			if("".equals(subProducerName)){ // For Direct
				if(firstReviewer == null || "".equals(firstReviewer) || ownerMap.get(firstReviewer) == null) {
		    		if("2".equals(policyStatusKey)){
	
						String forDirectFRRS = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.fordirectrenew.firstreviewer.RS");
//						String forDirectFRNN = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.fordirectrenew.firstreviewer.NN");
						String forDirectFRJF = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.fordirectrenew.firstreviewer.JF");
						String forDirectFRAL = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.fordirectrenew.firstreviewer.AL");
	
						if("CO#IL#NM".contains(stateCode)){
		    				firstReviewer = forDirectFRRS;//Ryan Schwartz
		    			} else if("CT#DE#DC#FL#GA#IN#MD#MA#ME#MI#NE#NH#NJ#NY#NC#OH#PA#RI#VT#WV".contains(stateCode)){
		    				firstReviewer = forDirectFRJF;//Jack Fink
		    			} else if("AL#AZ#AR#CA#HI#ID#IA#KS#KY#LA#MN#MS#MO#MT#NV#ND#OK#OR#SC#SD#TN#TX#UT#VT#WA#WI#WY".contains(stateCode)){
		    				firstReviewer = forDirectFRAL;//Andrea Lakatos
		    			}
		    		} else {
//						String forDirectFRRS = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.fordirectrenew.firstreviewer.RS");
						String forDirectFRQB = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.fordirectnew.firstreviewer.QB");
//		    			String forDirectFRDK = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.fordirectnew.firstreviewer.DK");
//						String forDirectFRSH = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.fordirectnew.firstreviewer.SH");
//						String forDirectFRCC = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.fordirectnew.firstreviewer.CC");
						String forDirectFRXR = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.fordirectnew.firstreviewer.XR");
	
//						if("CT#GA#FL#IL".contains(stateCode)){
//		    				firstReviewer = forDirectFRRS;//Ryan Schwartz
	    				if("CT#GA#FL#IL#PA#NC#KS#UT#MD#ME#NY#NH#VT#AL#AR#HI#IA#MS".contains(stateCode)){
		    				firstReviewer = forDirectFRXR;//Xavier
						} else if("CA#CO#IN#TX#WA#ID#AZ#MA#NJ#MN#WI#NV#OH#MO#MI#VA#TN#AK#KY#MT#ND#NM#SC#SD#WY#NE#OK#DE#DC#LA#WV#RI".contains(stateCode)){
							firstReviewer = forDirectFRQB;//Quiana
//		    			} else if("CT#GA#FL#KS#VA#TN#UT#NC#SC#OH#IL#MO#MI#AL#AR#HI#IA#MS#NE#OK#DE#DC#LA#WV#RI".contains(stateCode)){
//		    				firstReviewer = forDirectFRDK;//Dane
//		    			} else if("AZ#MA#MI#NV#NC#PA#SC#DE#DC#MD#ME#NH#RI#VT".contains(stateCode)){
//		    				firstReviewer = forDirectFRSH;//Sharon
//		    			} else if("OH#IL#MN#MO#NJ#WI#LA#NY#WV#MI#NV#PA#NH#RI#VT".contains(stateCode)){
//		    				firstReviewer = forDirectFRCC;//Chaz
		    			}
		    		}
				}
			} else {
				String forSubProducerDH = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.forsubproducer.firstreviewer.DH");
				String forSubProducerJW = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.forsubproducer.firstreviewer.JW");
				
				if("P0000005".equals(producerCode) || "P0000012".equals(producerCode)) {
					if("2".equals(policyStatusKey)){
						firstReviewer = forSubProducerDH;//Daniel O'Halloran
					} else  {
						firstReviewer = forSubProducerJW;//Jessica Wolf
					}
				}
			}
			
			// CREATE XML COLUMN LIST
			String[] keyList = keys.split(",");
			String[] keyValue = values.split(",");
			
			Date date = null;
    		if("".equals(policyEffectiveDate)){
    			date = new SimpleDateFormat("MM/dd/yyyy").parse(ctx.get("PolicyEffectiveDate").toString());
    		} else {
    			date = new SimpleDateFormat("yyyy-MM-dd").parse(policyEffectiveDate);
    		}
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			ctx.put("Deal_Name", cal.get(Calendar.YEAR)+"");
			if(ownerMap.get(firstReviewer) != null){
				ctx.put("Owner", firstReviewer);
			} else {
				ctx.put("Owner", "");
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			logger.debug(dateFormat.format(date)); //2016/11/16 12:08:43
			ctx.put("Closing_Date", dateFormat.format(date));
			ctx.put("Stage", statusDesc);
			ctx.put("Premium", (totalPremium == null || totalPremium.isEmpty())? "" : Integer.parseInt(totalPremium));
			ctx.put("Policy", "LPL");
			ctx.put("Expiring_Premium", (expiringPremium == null || expiringPremium.isEmpty())? "" :  (expiringPremium.length() > 5) ? Integer.parseInt(expiringPremium.substring(0,5)) : expiringPremium);
			ctx.put("Carrier", carrier);
			ctx.put("Account_Name", accountName);

			if(!("New".equals(statusDesc) && "FQ".equals(quoteType))){
				updateZOHORecordsOfPotentials(accountName, zohoPotentialsID, zohoAccountID, ctx, keyList, keyValue, matchColumns, matchColumnValues, ownerMap);
			}
		} catch(Exception e) {
			logger.error("Unexpected error", e);
		}	
	}

	public static void updateZOHORecordsOfPotentials(String accountName, String zohoPotentialsID, String zohoAccountID, Context ctx, String[] keyList, String[] keyValue, String[] matchColumns, String[] matchColumnValues, Map<String,String> ownerMap) throws Exception {
		String xmlDataString = "";
		boolean flag = true;
		
//		if(zohoPotentialsID != null && !"".equals(zohoPotentialsID) && !zohoPotentialsID.isEmpty()){
//        	ctx.put("id", zohoPotentialsID);
//			ctx.put("Account_Name", "");
//        	xmlDataString = createZohoUpdateJsonObjectOfPotentials(ctx, keyList, keyValue, ownerMap);
//        	if(!xmlDataString.isEmpty()){
//    	        logger.debug("update   " + xmlDataString);
//    	        boolean result = updateRecordsIntoZOHODataOfPotentials(ctx, xmlDataString, zohoPotentialsID, "NO");
//    	        if(!result){
//    	        	zohoPotentialsID = "";
//    				ctx.put("Account_Name", accountName);
//    	        	updateZOHORecordsOfPotentials(accountName, zohoPotentialsID, zohoAccountID, ctx, keyList, keyValue, matchColumns, matchColumnValues, ownerMap);
//		    	}
//    	    }
//		}else {
			JSONObject captureZohoData = getZOHORecordOfPotentials(ctx,matchColumns,matchColumnValues, zohoAccountID);
			if(captureZohoData != null && !captureZohoData.isNull("data")){
				JSONArray captureZohoDataList = (JSONArray)captureZohoData.get("data");
				for(int captureZohoDataCount = 0; captureZohoDataCount < captureZohoDataList.length(); captureZohoDataCount++){
					captureZohoData = new JSONObject(captureZohoDataList.get(captureZohoDataCount).toString());
					
					if(!captureZohoData.isNull("Policy") && captureZohoData.get("Policy").toString().equalsIgnoreCase(ctx.get("Policy").toString())){
			        	
						zohoPotentialsID = (captureZohoData.isNull("id")) ? null : (String) captureZohoData.get("id");
						ctx.put("id", zohoPotentialsID);
						ctx.put("Account_Name", "");
			        	xmlDataString = createZohoUpdateJsonObjectOfPotentials(ctx, keyList, keyValue, ownerMap);
			        	if(!xmlDataString.isEmpty()){
			    	        updateRecordsIntoZOHODataOfPotentials(ctx, xmlDataString, zohoPotentialsID, "NO");
			    	    }
			        	flag = false;
			        	break;
		        	}
				}
			}
			
		    if(flag && xmlDataString.isEmpty()){

				ctx.put("id", zohoPotentialsID);
				ctx.put("AccountZohoID", zohoAccountID);
		    	xmlDataString = createZohoUpdateJsonObjectOfPotentials(ctx, keyList, keyValue, ownerMap);
	        	if(!xmlDataString.isEmpty()){
			        insertRecordsIntoZOHODataOfPotentials(ctx,xmlDataString); 
	        	}
	        }
//		}
    }
	
	public static String  createZohoUpdateJsonObjectOfPotentials(Context ctx, String[] keyList, String[] keyValue, Map<String,String> ownerMap){
		try {
			JSONArray captureZohoDataList = new JSONArray();
			JSONObject captureZohoObject = new JSONObject();
			JSONObject captureZohoData = new JSONObject();
			
			for(int statusCount = 0; statusCount < keyList.length; statusCount++){
				if(ctx.get(keyValue[statusCount]) != null){
					if("Account_Name".equals(keyList[statusCount])){
						if(!"".equals(ctx.get(keyValue[statusCount]))){
							JSONObject accountZohoData = new JSONObject();
							accountZohoData.put("name", ctx.get(keyValue[statusCount]));
							accountZohoData.put("id", ctx.get("AccountZohoID"));
							
							captureZohoData.put(keyList[statusCount], accountZohoData);
						}
					} else if("Owner".equals(keyList[statusCount])){
						if(!"".equals(ctx.get(keyValue[statusCount]))){
							JSONObject dealOwnerZohoData = new JSONObject();
							dealOwnerZohoData.put("name", ctx.get(keyValue[statusCount]));
							dealOwnerZohoData.put("id", ownerMap.get(ctx.get(keyValue[statusCount])));
							
							captureZohoData.put(keyList[statusCount], dealOwnerZohoData);
						}
					} else {
						captureZohoData.put(keyList[statusCount], ctx.get(keyValue[statusCount]));
					}
					
				}
	        }
			captureZohoDataList.put(captureZohoData);
			captureZohoObject.put("data", captureZohoDataList);
			
			captureZohoDataList = new JSONArray();
			captureZohoData = new JSONObject();
			captureZohoData.put("workflow", "");
			captureZohoData.put("approval", "");
			captureZohoData.put("blueprint", "");
			
			captureZohoDataList.put(captureZohoData);
			captureZohoObject.put("triggger", captureZohoDataList);
			
			return captureZohoObject.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		}
		return "";
	}
	
	public static void insertRecordsIntoZOHODataOfPotentials(Context ctx, String XmlString)
	{	
		try	
		{
      		System.setProperty("javax.net.ssl.trustStore", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStore"));
			System.setProperty("javax.net.ssl.keyStorePassword", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStorePassword"));
			System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
			
			//----------------------------Fetch Auth Token ----------------------
//			String targetURL = "https://crm.zoho.com/crm/private/xml/Potentials/insertRecords"; 
			String targetURL = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.modulesurl") + "/Potentials";
			String authtoken = "Zoho-oauthtoken " + ctx.get("isZohoAccessToken");//If you don't have a authtoken please refer this wiki https://zohocrmapi.wiki.zoho.com/using-authtoken.html

			PostMethod post = null;
			// Execute http request
			try
			{
				post = new PostMethod(targetURL);
				post.setRequestHeader("Authorization", authtoken);
				post.setRequestHeader("Content-Type", "application/json");
				post.setRequestBody(XmlString);

				HttpClient httpclient = new HttpClient();
				
				int result = httpclient.executeMethod(post);
				logger.debug("Zoho HTTP status: " + result);
				
				JSONObject captureZohoData = new JSONObject(post.getResponseBodyAsString());
				if(captureZohoData != null && !captureZohoData.isNull("data")){
					JSONArray captureZohoDataList = (JSONArray)captureZohoData.get("data");
					for(int captureZohoDataCount = 0; captureZohoDataCount < captureZohoDataList.length(); captureZohoDataCount++){
						captureZohoData = new JSONObject(captureZohoDataList.get(captureZohoDataCount).toString());
						
						if(!captureZohoData.isNull("details")){
							JSONObject captureZohoDataChild = (JSONObject) captureZohoData.get("details");
							if(!captureZohoDataChild.isNull("id")){
								String zohoPotentialsID = (captureZohoDataChild.isNull("id")) ? null : (String) captureZohoDataChild.get("id");
								ctx.put("ZohoPotentialsID", zohoPotentialsID);
								int status = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateZohoPotentialsID", ctx);
								logger.debug(status);
							}
						}
					}
				}
				
			} catch(Exception e) {
				logger.error("Unexpected error", e);
			} finally {
				post.releaseConnection();
			}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
	}
			
	public static boolean updateRecordsIntoZOHODataOfPotentials(Context ctx, String XmlString, String zohoPotentialsID, String isZohoPotentialsIDUpdate) {	
		try	
		{
      		System.setProperty("javax.net.ssl.trustStore", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStore"));
			System.setProperty("javax.net.ssl.keyStorePassword", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStorePassword"));
			System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
			
			//----------------------------Fetch Auth Token ----------------------
//			String targetURL = "https://crm.zoho.com/crm/private/xml/Potentials/updateRecords";
			String targetURL = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.modulesurl") + "/Potentials/" + zohoPotentialsID;
			String authtoken = "Zoho-oauthtoken " + ctx.get("isZohoAccessToken");//If you don't have a authtoken please refer this wiki https://zohocrmapi.wiki.zoho.com/using-authtoken.html
			
			PutMethod post = null;
			// Execute http request
			try
			{
				post = new PutMethod(targetURL);
				post.setRequestHeader("Authorization", authtoken);
				post.setRequestHeader("Content-Type", "application/json");
				post.setRequestBody(XmlString);

				HttpClient httpclient = new HttpClient();
				
				int result = httpclient.executeMethod(post);
				logger.debug("Zoho HTTP status: " + result);
				if(post.getResponseBodyAsString() != null && !"".equals(post.getResponseBodyAsString())){
	  				JSONObject captureZohoData = new JSONObject(post.getResponseBodyAsString());
					if(captureZohoData != null && !captureZohoData.isNull("data")){
						JSONArray captureZohoDataList = (JSONArray)captureZohoData.get("data");
						for(int captureZohoDataCount = 0; captureZohoDataCount < captureZohoDataList.length(); captureZohoDataCount++){
							captureZohoData = new JSONObject(captureZohoDataList.get(captureZohoDataCount).toString());
							if(!captureZohoData.isNull("message")){
								String message = (String) captureZohoData.get("message");
								if("the id given seems to be invalid".equals(message)){
									return false;
								}
							}
						}
	  				}
	  			}
				if("YES".equals(isZohoPotentialsIDUpdate)){
					ctx.put("ZohoPotentialsID", zohoPotentialsID);
					int status = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateZohoPotentialsID", ctx);
				}
				
			} catch(Exception e) {
				logger.error("Unexpected error", e);
			} finally {
				post.releaseConnection();
			}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
		return true;
	}
	
    public static JSONObject getZOHORecordOfAccounts(Context ctx, String[] matchColumns, String[] matchColumnValues)
	{
    	JSONObject copyCaptureZohoData = null;
      	try{
      		System.setProperty("javax.net.ssl.trustStore", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStore"));
			System.setProperty("javax.net.ssl.keyStorePassword", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStorePassword"));
			System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
			
      		for(int criteriaCount = 0; criteriaCount < matchColumns.length; criteriaCount++){
	  	    	String serachTargetURL = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.modulesurl") + "/Accounts/search?";
//	  	    	https://crm.zoho.com/crm/private/xml/Accounts/searchRecords?
//	  	    	https://www.zohoapis.com/crm/v2/Accounts/search?
//	  	    	https://www.zohoapis.com/crm/v2/Leads/search?
	  	    	String authtoken = "Zoho-oauthtoken " + ctx.get("isZohoAccessToken");//If you don't have a authtoken please refer this wiki https://zohocrmapi.wiki.zoho.com/using-authtoken.html 
	  			String selectColumns ="Account_Name,Billing_Code,Billing_Street,Billing_State,Billing_City,County,Phone,Account_Email,Sub_Producer,NameClearStatus,NameClearType,NameClearDate,RequestedAppName,Deductible,Firm_Limit,Ex_Date";
	  			String criteria = "(";
//  				if(!"(".equals(criteria) && criteriaCount > 0 && ctx.get(matchColumnValues[criteriaCount]) != null && !"".equals(ctx.get(matchColumnValues[criteriaCount]))){
//  					criteria += conditionOpertor;
//  				} 
  				if("Account_Name".equals(matchColumns[criteriaCount]) && ctx.get(matchColumnValues[criteriaCount]) != null && !"".equals(ctx.get(matchColumnValues[criteriaCount]))){
  					criteria += "(" + matchColumns[criteriaCount] + ":equals:" + URLEncoder.encode(ctx.get(matchColumnValues[criteriaCount]).toString(), "UTF-8") + ")";
  				} 
//  				if("Billing_State".equals(matchColumns[criteriaCount]) && ctx.get(matchColumnValues[criteriaCount]) != null && !"".equals(ctx.get(matchColumnValues[criteriaCount]))){
//  					criteria += "AND(" + matchColumns[criteriaCount] + ":equals:" + URLEncoder.encode(ctx.get(matchColumnValues[criteriaCount]).toString(), "UTF-8") + "))";
//  				}
  				if("Phone".equals(matchColumns[criteriaCount]) && ctx.get(matchColumnValues[criteriaCount]) != null && !"".equals(ctx.get(matchColumnValues[criteriaCount]))){
  					String workPhone = ctx.get(matchColumnValues[criteriaCount]).toString().replaceAll("\\D+","");
  					workPhone = workPhone.substring(0,3) + "-" + workPhone.substring(3,6) + "-" + workPhone.substring(6,10);
  					criteria += "(" + matchColumns[criteriaCount] + ":equals:" + URLEncoder.encode(workPhone, "UTF-8") + ")";
  				}
  				if("Account_Email".equals(matchColumns[criteriaCount]) && ctx.get(matchColumnValues[criteriaCount]) != null && !"".equals(ctx.get(matchColumnValues[criteriaCount]))){
  					criteria += "(" + matchColumns[criteriaCount] + ":equals:" + URLEncoder.encode(ctx.get(matchColumnValues[criteriaCount]).toString(), "UTF-8") + ")";
  				}
	  			criteria += ")";
	  			
	  			//Authorization: Zoho-oauthtoken d92d401c803988c5cb849d0b4215f52"
	  			//----------------------------Fetch ZOHO Data ----------------------
	  			serachTargetURL += "fields=" + selectColumns + "&criteria=" + criteria;
	  			GetMethod get = new GetMethod(serachTargetURL);
	  			get.setRequestHeader("Authorization", authtoken);
	  			
	  			HttpClient httpclient = new HttpClient();
	  			int result = httpclient.executeMethod(get);
  			
			logger.debug("Zoho HTTP status: " + result);
	
	  			if(get.getResponseBodyAsString() != null && !"".equals(get.getResponseBodyAsString())){
	  				JSONObject captureZohoData = new JSONObject(get.getResponseBodyAsString());
	  				if(!(captureZohoData != null && !captureZohoData.isNull("status") && "error".equals(captureZohoData.get("status")))){
		  				if(copyCaptureZohoData == null){
		  					copyCaptureZohoData = new JSONObject(captureZohoData, JSONObject.getNames(captureZohoData));
		  				} else {
		  					if(captureZohoData != null && !captureZohoData.isNull("data")){
		  						JSONArray captureZohoDataList = (JSONArray)captureZohoData.get("data");
		  						for(int captureZohoDataCount = 0; captureZohoDataCount < captureZohoDataList.length(); captureZohoDataCount++){
		  							((JSONArray)copyCaptureZohoData.get("data")).put(new JSONObject(captureZohoDataList.get(captureZohoDataCount).toString()));
		  						}
		  					}
		  				}
	  				}
	  			}
      		}
      	} catch(Exception e){
      		logger.error("Unexpected error", e);
  		}
      	return copyCaptureZohoData;
	}
	public static String insertZOHORecordsOfAccounts(Context ctx) {
    	try 
		{
    		String maxPolicyKey = "";
    		List getMaxPolicyKey = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoMaxPolicyKey", ctx);
    		if(getMaxPolicyKey != null && getMaxPolicyKey.size() > 0 && !getMaxPolicyKey.isEmpty()){
    			Map getMaxPolicyKeyList = (Map)getMaxPolicyKey.get(0);
    			maxPolicyKey = (getMaxPolicyKeyList.get("MaxPolicyKey") == null ? "" : getMaxPolicyKeyList.get("MaxPolicyKey").toString());
    		}
    		
    		if(maxPolicyKey == null || "".equals(maxPolicyKey) || maxPolicyKey.equals(String.valueOf(ctx.get("PolicyKey")))){
    		
	    		String totalPremium = "";
	    		String transactionSequence = "";
	    		ctx.put("PolicyKey",Integer.parseInt(ctx.get("PolicyKey").toString()));
	    		List getPolicyTotalPremium = (List)SqlResources.getSqlMapProcessor(ctx).select("Quote.GetZohoPremiumLW_p", ctx);
	    		if(getPolicyTotalPremium != null && getPolicyTotalPremium.size() > 0 && !getPolicyTotalPremium.isEmpty()){
	    			Map getPolicyTotalPremiumList = (Map)getPolicyTotalPremium.get(0);
	    			totalPremium = (getPolicyTotalPremiumList.get("InvoicedPremium") == null ? "" : getPolicyTotalPremiumList.get("InvoicedPremium").toString());
	    			transactionSequence = (getPolicyTotalPremiumList.get("TransactionSequence") == null ? "" : getPolicyTotalPremiumList.get("TransactionSequence").toString());
	    		}
	    		ctx.put("PolicyKey",String.valueOf(ctx.get("PolicyKey")));
	    		
	    		String isZohoInsert = "";
	    		String zohoAccountID = "";
	    		String numberOfLawyers = "";
	    		String isFirmHaveLawyersLiabilityInsurance = "";
	    		List getZohoAccountID = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoAccountID", ctx);
	    		if(getZohoAccountID != null && getZohoAccountID.size() > 0 && !getZohoAccountID.isEmpty()){
	    			Map getZohoAccountIDList = (Map)getZohoAccountID.get(0);
	    			zohoAccountID = (getZohoAccountIDList.get("ZohoAccountID") == null ? "" : getZohoAccountIDList.get("ZohoAccountID").toString());
	    			isZohoInsert = (getZohoAccountIDList.get("ISZohoInsert") == null ? "" : getZohoAccountIDList.get("ISZohoInsert").toString());
	    			numberOfLawyers = (getZohoAccountIDList.get("NumberOfLawyers") == null ? "" : getZohoAccountIDList.get("NumberOfLawyers").toString());
	    			isFirmHaveLawyersLiabilityInsurance = (getZohoAccountIDList.get("IsFirmHaveLawyersLiabilityInsurance") == null ? "" : getZohoAccountIDList.get("IsFirmHaveLawyersLiabilityInsurance").toString());
	    		}
	    		
	    		String accountName = "";
	    		String stateCode = "";
	    		String workPhone = "";
	    		String accountEmail = "";
	    		String address1 = "";
	    		String city = "";
	    		String zip = "";
	    		String countyDesc = "";
	    		String street = "";
	    		String stateDesc = "";
	    		String statusDesc = "";
	    		List getPolicyStatus = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoStatus", ctx);
	    		if(getPolicyStatus != null && getPolicyStatus.size() > 0 && !getPolicyStatus.isEmpty()){
	    			Map getPolicyStatusList = (Map)getPolicyStatus.get(0);
	    			accountName = (getPolicyStatusList.get("AccountName") == null ? "" : getPolicyStatusList.get("AccountName").toString());
	    			accountEmail = (getPolicyStatusList.get("AccountEmail") == null ? "" : getPolicyStatusList.get("AccountEmail").toString());
	    			address1 = (getPolicyStatusList.get("Address1") == null ? "" : getPolicyStatusList.get("Address1").toString());
	    			city = (getPolicyStatusList.get("City") == null ? "" : getPolicyStatusList.get("City").toString());
	    			stateCode = (getPolicyStatusList.get("StateCode") == null ? "" : getPolicyStatusList.get("StateCode").toString());
	    			zip = (getPolicyStatusList.get("Zip") == null ? "" : getPolicyStatusList.get("Zip").toString());
	    			zip += (getPolicyStatusList.get("Zip4") == null ? "" : "-"+getPolicyStatusList.get("Zip4").toString());
	    			workPhone = (getPolicyStatusList.get("WorkPhone") == null ? "" : getPolicyStatusList.get("WorkPhone").toString());
	    			countyDesc = (getPolicyStatusList.get("CountyDesc") == null ? "" : getPolicyStatusList.get("CountyDesc").toString());
	    			street = (getPolicyStatusList.get("Street") == null ? "" : getPolicyStatusList.get("Street").toString());
	    			stateDesc = (getPolicyStatusList.get("StateDesc") == null ? "" : getPolicyStatusList.get("StateDesc").toString());
	    			statusDesc = (getPolicyStatusList.get("StatusDesc") == null ? "" : getPolicyStatusList.get("StatusDesc").toString());
	    		}
	    		
	    		accountName = ("".equals(accountName)) ? (String) ctx.get("AccountName") : accountName;
	    		stateCode = ("".equals(stateCode)) ? (String) ctx.get("StateCode") : stateCode;
	    		address1 = ("".equals(address1)) ? (String) ctx.get("Address1") : address1;
	    		countyDesc = ("".equals(countyDesc)) ? (String) ctx.get("CountyDesc") : countyDesc;
	    		city = ("".equals(city)) ? (String) ctx.get("City") : city;
	    		zip = ("".equals(zip)) ? (String) ctx.get("Zip") : zip;
	    		workPhone = ("".equals(workPhone)) ? (String) ctx.get("WorkPhone") : workPhone;
	    		accountEmail = ("".equals(accountEmail)) ? (String) ctx.get("AccountEmail") : accountEmail;
	    		
	    		String subProducerName = "";
	    		List getSubProducerNameList = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoSubProducerName", ctx);
	    		if(getSubProducerNameList != null && getSubProducerNameList.size() > 0 && !getSubProducerNameList.isEmpty()){
	    			Map getSubProducerNameMap = (Map)getSubProducerNameList.get(0);
	    			subProducerName = (getSubProducerNameMap.get("ProducerCompanyName") == null ? "" : getSubProducerNameMap.get("ProducerCompanyName").toString());
	    		}
	    		
	    		String attorneyDetails = "";
	    		List getZohoAttorneyDetails = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoAttorneyDetails", ctx);
	    		if(getZohoAttorneyDetails != null && getZohoAttorneyDetails.size() > 0 && !getZohoAttorneyDetails.isEmpty()){
	    			Map getAttorneyDetailsList = (Map)getZohoAttorneyDetails.get(0);
	    			attorneyDetails = (getAttorneyDetailsList.get("addAttorneys") == null ? "" : getAttorneyDetailsList.get("addAttorneys").toString());
	    		}
	    		
	    		String priorActDatePross = "";
	    		String isPriorActDateFull = "";
	    		List getZohoPriorActDatePross = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoPriorActDateProssDetails", ctx);
	    		if(getZohoPriorActDatePross != null && getZohoPriorActDatePross.size() > 0 && !getZohoPriorActDatePross.isEmpty()){
	    			Map getPriorActDateProssList = (Map)getZohoPriorActDatePross.get(0);
	    			priorActDatePross = (getPriorActDateProssList.get("PriorActDatePross") == null ? "" : getPriorActDateProssList.get("PriorActDatePross").toString());
	    			isPriorActDateFull = (getPriorActDateProssList.get("IsPriorActDateFull") == null ? "" : getPriorActDateProssList.get("IsPriorActDateFull").toString());
	    		}
	    		
	    		if("Y".equals(isPriorActDateFull)){
	    			priorActDatePross = "Full";
	    		}
	    		
	    		String aggregateLimit = "";
	    		String occuranceLimit = "";
	    		String aggregateDeductible="";
	    		String occuranceDeductible="";
	    		ctx.put("TransactionSequenceZoho", transactionSequence);
	    		List getDeductibleLimitList = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoDeductible", ctx);
	    		if(getDeductibleLimitList != null && getDeductibleLimitList.size() > 0 && !getDeductibleLimitList.isEmpty()){
	    			Map getDeductibleLimitMap = (Map)getDeductibleLimitList.get(0);
	    			aggregateLimit = (getDeductibleLimitMap.get("AggregateLimit") == null ? "" : getDeductibleLimitMap.get("AggregateLimit").toString());
	    			occuranceLimit = (getDeductibleLimitMap.get("OccuranceLimit") == null ? "" : getDeductibleLimitMap.get("OccuranceLimit").toString());
	    			aggregateDeductible = (getDeductibleLimitMap.get("AggregateDeductible") == null ? "" : getDeductibleLimitMap.get("AggregateDeductible").toString());
	    			occuranceDeductible = (getDeductibleLimitMap.get("OccuranceDeductible") == null ? "" : getDeductibleLimitMap.get("OccuranceDeductible").toString());
	    		}
	    		
	    		if(aggregateLimit != null && !aggregateLimit.isEmpty()){
	    			int aggrLimit = Integer.parseInt(aggregateLimit);
	    			if(aggrLimit >= 100000 && aggrLimit < 1000000){
	    				aggregateLimit = (String) LawyersUtilities.amountFormatLawyers(aggregateLimit);
	    			} else if(aggrLimit >= 1000000){
	    				aggregateLimit = "$" + aggregateLimit.substring(0,1) + "M";
	    			}
	    			aggregateLimit += "/";
	    		}
	    		
	    		if(occuranceLimit != null && !occuranceLimit.isEmpty()){
	    			int occurLimit = Integer.parseInt(occuranceLimit);
	    			if(occurLimit >= 100000 && occurLimit < 1000000){
	    				occuranceLimit = (String) LawyersUtilities.amountFormatLawyers(occuranceLimit);
	    			} else if(occurLimit >= 1000000){
	    				occuranceLimit = "$" + occuranceLimit.substring(0,1) + "M";
	    			}
	    		}
	    		
	    		String policyEffectiveDate = "";
	    		String quoteType = "";
	    		String policyStatusKey = "";
	    		String quoteNumber = "";
	    		String companyName = "";
	    		List getPolicyEffectiveDate = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoPolicyEffectiveDate", ctx);
	    		if(getPolicyEffectiveDate != null && getPolicyEffectiveDate.size() > 0 && !getPolicyEffectiveDate.isEmpty()){
	    			Map getPolicyEffectiveDateList = (Map)getPolicyEffectiveDate.get(0);
	    			policyEffectiveDate = (getPolicyEffectiveDateList.get("PolicyEffectiveDate") == null ? "" : getPolicyEffectiveDateList.get("PolicyEffectiveDate").toString());
	    			quoteType = (getPolicyEffectiveDateList.get("QuoteType") == null ? "" : getPolicyEffectiveDateList.get("QuoteType").toString());
	    			policyStatusKey = (getPolicyEffectiveDateList.get("PolicyStatusKey") == null ? "" : getPolicyEffectiveDateList.get("PolicyStatusKey").toString());
	    			quoteNumber = (getPolicyEffectiveDateList.get("QuoteNumber") == null ? "" : getPolicyEffectiveDateList.get("QuoteNumber").toString());
	    			companyName = (getPolicyEffectiveDateList.get("CompanyName") == null ? "" : getPolicyEffectiveDateList.get("CompanyName").toString());
	    		}
	    		
	    		if("New".equals(statusDesc) && "Y".equals(ctx.get("IsQuickQuote"))){ 
	    			attorneyDetails = numberOfLawyers;
	    		}
//	    		if("UnderReview".equals(statusDesc) || "UW Review".equals(statusDesc) || "UW Completed".equals(statusDesc)){
//	    			statusDesc = "Under Review";
//	    		}
	    		if("Indication".equals(statusDesc)){
	    			attorneyDetails = numberOfLawyers;
	    		}
//	    		if("Declined".equals(statusDesc)){
//	    			statusDesc = "Declined/Non-Renewed";
//	    		}
	    		
				String[] matchColumns = {"Account_Name","Phone","Account_Email"};
				String[] matchColumnValues = {"Account_Name","Phone","Account_Email"};
				
				// CREATE XML COLUMN LIST
				String[] keyList = {"Account_Name","Billing_State","Billing_Street","County","Billing_City","Billing_Code","Phone","Account_Email","NameClearDate","NameClearStatus","NameClearType","RequestedAppName","Sub_Producer","Our_Premium","Quote_Number","Firm_Limit","Deductible","Ex_Date","Professionals","Prior_Acts_Date","Industry","id"};
				String[] keyValue = {"Account_Name","Billing_State","Billing_Street","County","Billing_City","Billing_Code","Phone","Account_Email","NameClearDate","NameClearStatus","NameClearType","RequestedAppName","Sub_Producer","Our_Premium","Quote_Number","Firm_Limit","Deductible","Ex_Date","Professionals","Prior_Acts_Date","Industry","id"};
	
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				logger.debug(dateFormat.format(date)); //2016/11/16 12:08:43
				Date exDate = null;
				if("".equals(policyEffectiveDate)){
					exDate = new SimpleDateFormat("MM/dd/yyyy").parse(ctx.get("PolicyEffectiveDate").toString());
	    		} else {
	    			exDate = new SimpleDateFormat("yyyy-MM-dd").parse(policyEffectiveDate);
	    		}
				ctx.put("Account_Name", accountName);
				ctx.put("Billing_State", stateCode);
				ctx.put("Billing_Street", address1);
				ctx.put("County", countyDesc);
				ctx.put("Billing_City", city);
				ctx.put("Billing_Code", zip);
				ctx.put("Phone", workPhone);
				ctx.put("Account_Email", accountEmail);
				ctx.put("Ex_Date", dateFormat.format(exDate));
				ctx.put("Our_Premium",totalPremium);
				ctx.put("Quote_Number",quoteNumber);
				ctx.put("Firm_Limit",aggregateLimit + occuranceLimit);
				ctx.put("Deductible",aggregateDeductible);
				ctx.put("Professionals",attorneyDetails);
				if(!("New".equals(statusDesc) && "Y".equals(ctx.get("IsQuickQuote"))) && !"Indication".equals(statusDesc)) {
					if(!"".equals(priorActDatePross) && !priorActDatePross.isEmpty()){
						ctx.put("Prior_Acts_Date",priorActDatePross);
					} else if("1".equals(policyStatusKey) && "N".equals(isFirmHaveLawyersLiabilityInsurance)){
						ctx.put("Prior_Acts_Date",new SimpleDateFormat("MM/dd/yyyy").format(exDate));
					}
				}
				ctx.put("Industry","Lawyers");
				ctx.put("RequestedAppName","LawyersIns");
				ctx.put("Sub_Producer",subProducerName);
				
				String flagStatus = ctx.get("ZohoStatusDescUpdated") == null ? "" : ctx.get("ZohoStatusDescUpdated").toString().replaceAll("\\(P\\)", "").trim();
				if(("Y".equals(ctx.get("IsAccountDetailChanged") == null ? "" : ctx.get("IsAccountDetailChanged").toString()))
				|| ("1".equals(policyStatusKey) && (ctx.get("isNameClearanceRequired") == null || "".equalsIgnoreCase(ctx.get("isNameClearanceRequired").toString().trim()) || ctx.get("isNameClearanceRequired").toString().isEmpty())
					&& (("FQ".equals(quoteType) && "New".equals(flagStatus) && "FirmNewApp".equalsIgnoreCase(ctx.get("inet_page").toString()) && "save".equalsIgnoreCase(ctx.get("inet_method").toString()))
						|| ("IND".equals(quoteType) && "IndicationZohoStatus".equals(flagStatus) && "indicationinjack".equalsIgnoreCase(ctx.get("inet_page").toString()) && "addIndication".equalsIgnoreCase(ctx.get("inet_method").toString()))
						|| ("QQ".equals(quoteType) && "QuickQuoteZohoStatus".equals(flagStatus) && "quickquoteinsured".equalsIgnoreCase(ctx.get("inet_page").toString()) && "save".equalsIgnoreCase(ctx.get("inet_method").toString()))))) {
					
					if(LawyersValidationUtils.validateExistingZohoRecordInProdDB(ctx) || "Y".equals(ctx.get("IsAccountDetailChanged") == null ? "" : ctx.get("IsAccountDetailChanged").toString())){
						zohoAccountID = generateZohoNameClearanceFlag(ctx);
					} else {
						zohoAccountID = updateZohoDirectAccount(dateFormat, date, accountName, stateCode, accountEmail, workPhone, isZohoInsert, zohoAccountID, ctx, keyList, keyValue, matchColumns, matchColumnValues);
					}
				} else {
					zohoAccountID = updateZohoDirectAccount(dateFormat, date, accountName, stateCode, accountEmail, workPhone, isZohoInsert, zohoAccountID, ctx, keyList, keyValue, matchColumns, matchColumnValues);
				}
				int status = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateZohoSubProducerNameClearance", ctx);
				logger.debug(status);
				
				return zohoAccountID;
    		}
		} catch(Exception e) {
			logger.error("Unexpected error", e);
		}
		return "";
	}
	
	public static String updateZohoDirectAccount(SimpleDateFormat dateFormat, Date date, String accountName, String stateCode, String accountEmail, String workPhone, String isZohoInsert, String zohoAccountID, Context ctx, String[] keyList, String[] keyValue, String[] matchColumns, String[] matchColumnValues) throws Exception{
		final String nameClearStatus = "UnderReview";
		String xmlDataString = "";
		boolean flag = true;

		ctx.put("NameClearDate", dateFormat.format(date));
		ctx.put("NameClearStatus",nameClearStatus);
		ctx.put("NameClearType", "Automatic");
		
		if(zohoAccountID != null && !"".equals(zohoAccountID) && !zohoAccountID.isEmpty()){
			ctx.put("id",zohoAccountID);
			xmlDataString = createZohoUpdateXML(ctx, keyList, keyValue);
			if(!xmlDataString.isEmpty()){
		    	boolean result = updateRecordsIntoZOHOData(ctx, xmlDataString,zohoAccountID, "NO", isZohoInsert);
		    	if(!result){
		    		zohoAccountID = "";
		    		zohoAccountID = updateZohoDirectAccount(dateFormat, date, accountName, stateCode, accountEmail, workPhone, isZohoInsert, zohoAccountID, ctx, keyList, keyValue, matchColumns, matchColumnValues);
		    	}
		    }
		}else {
			
			JSONObject captureZohoData = getZOHORecordOfAccounts(ctx,matchColumns,matchColumnValues);
			if(captureZohoData != null && !captureZohoData.isNull("data")){
				JSONArray captureZohoDataList = (JSONArray)captureZohoData.get("data");
				for(int captureZohoDataCount = 0; captureZohoDataCount < captureZohoDataList.length(); captureZohoDataCount++){
					captureZohoData = new JSONObject(captureZohoDataList.get(captureZohoDataCount).toString());
					
					if(((!captureZohoData.isNull("Account_Name") && captureZohoData.get("Account_Name").toString().replaceAll("[^a-zA-Z0-9\\s]", "").equalsIgnoreCase(accountName.replaceAll("[^a-zA-Z0-9\\s]", ""))) && (!captureZohoData.isNull("Billing_State") && captureZohoData.get("Billing_State").toString().equalsIgnoreCase(stateCode)))
						|| ((!captureZohoData.isNull("Account_Name") && captureZohoData.get("Account_Name").toString().replaceAll("[^a-zA-Z0-9\\s]", "").equalsIgnoreCase(accountName.replaceAll("[^a-zA-Z0-9\\s]", ""))) && (!captureZohoData.isNull("Phone") && captureZohoData.get("Phone").toString().replaceAll("\\D+","").equalsIgnoreCase(workPhone.replaceAll("\\D+",""))))
						|| (!captureZohoData.isNull("Account_Email") && captureZohoData.get("Account_Email").toString().equalsIgnoreCase(accountEmail))){
    		        	
			        	zohoAccountID = (captureZohoData.isNull("id")) ? null : (String) captureZohoData.get("id");
						ctx.put("id",zohoAccountID);
						xmlDataString = createZohoUpdateXML(ctx, keyList, keyValue);
		    			if(!xmlDataString.isEmpty()){
		    		    	updateRecordsIntoZOHOData(ctx, xmlDataString,zohoAccountID, "YES", isZohoInsert);
		    		    }
    		        	flag = false;
			        	break;
		        	}
				}
			}
		    if(flag && xmlDataString.isEmpty()){
		    	ctx.put("id",zohoAccountID);
				xmlDataString = createZohoUpdateXML(ctx, keyList, keyValue);
				if(!xmlDataString.isEmpty()){
			        zohoAccountID = insertRecordsIntoZOHOData(ctx,xmlDataString);
			        ctx.put("isNameClearanceRequired", "N"); 
				}
	        }
		}
		return zohoAccountID;
	}
	
	public static String generateZohoNameClearanceFlag(Context ctx) throws Exception{
		String ruleId="NameClearance_Lawyers",desc="Name Clearance Required",tooltip="Name Clearance Required";
		RuleEngineUtilityNewApp.insertRulesToDatabase(ctx, null, ruleId, desc, tooltip);
		ctx.put("isNameClearanceRequired", "Y");
		ctx.put("NameClearanceIndicationFlag", "Y");
		return "";
	}
	
	public static String createZohoUpdateXML(Context ctx, String[] keyList, String[] keyValue){
		try {
			JSONArray captureZohoDataList = new JSONArray();
			JSONObject captureZohoObject = new JSONObject();
			JSONObject captureZohoData = new JSONObject();
			
			for(int statusCount = 0; statusCount < keyList.length; statusCount++){
				if(ctx.get(keyValue[statusCount]) != null){
					if("Phone".equals(keyList[statusCount])){
						captureZohoData.put(keyList[statusCount], ctx.get(keyValue[statusCount]).toString().replaceAll("\\(","").replaceAll("\\)","-"));
					} else {
						captureZohoData.put(keyList[statusCount], ctx.get(keyValue[statusCount]));
					}
				}
	        }
			captureZohoDataList.put(captureZohoData);
			captureZohoObject.put("data", captureZohoDataList);
			
			captureZohoDataList = new JSONArray();
			captureZohoData = new JSONObject();
			captureZohoData.put("workflow", "");
			captureZohoData.put("approval", "");
			captureZohoData.put("blueprint", "");
			
			captureZohoDataList.put(captureZohoData);
			captureZohoObject.put("triggger", captureZohoDataList);
			
			return captureZohoObject.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		}
		return "";
	}

	public static String insertRecordsIntoZOHOData(Context ctx, String XmlString)
	{	
		try	
		{
      		System.setProperty("javax.net.ssl.trustStore", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStore"));
			System.setProperty("javax.net.ssl.keyStorePassword", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStorePassword"));
			System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
			
			//----------------------------Fetch Auth Token ----------------------
//			String targetURL = "https://crm.zoho.com/crm/private/xml/Accounts/insertRecords"; 
			String targetURL = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.modulesurl") + "/Accounts";
			String authtoken = "Zoho-oauthtoken " + ctx.get("isZohoAccessToken");//If you don't have a authtoken please refer this wiki https://zohocrmapi.wiki.zoho.com/using-authtoken.html

			String zohoAccountID = "";
			PostMethod post = null;
			// Execute http request
			try
			{
				post = new PostMethod(targetURL);
				post.setRequestHeader("Authorization", authtoken);
				post.setRequestHeader("Content-Type", "application/json");
				post.setRequestBody(XmlString);

				HttpClient httpclient = new HttpClient();
				
				int result = httpclient.executeMethod(post);
				logger.debug("Zoho HTTP status: " + result);
				
				JSONObject captureZohoData = new JSONObject(post.getResponseBodyAsString());
				if(captureZohoData != null && !captureZohoData.isNull("data")){
					JSONArray captureZohoDataList = (JSONArray)captureZohoData.get("data");
					for(int captureZohoDataCount = 0; captureZohoDataCount < captureZohoDataList.length(); captureZohoDataCount++){
						captureZohoData = new JSONObject(captureZohoDataList.get(captureZohoDataCount).toString());
						
						if(!captureZohoData.isNull("details")){
							JSONObject captureZohoDataChild = (JSONObject) captureZohoData.get("details");
							if(!captureZohoDataChild.isNull("id")){
								zohoAccountID = (captureZohoDataChild.isNull("id")) ? null : (String) captureZohoDataChild.get("id");
								ctx.put("ZohoAccountID", zohoAccountID);
								ctx.put("ISZohoInsert", "Insert");
								int status = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateZohoAccountID", ctx);
								status = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateISZohoInsert", ctx);
								logger.debug(status);
							}
						}
					}
				}
				return zohoAccountID;
			} catch(Exception e) {
				logger.error("Unexpected error", e);
			} finally {
				post.releaseConnection();
			}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}	
		return "";
	}
			
	public static boolean updateRecordsIntoZOHOData(Context ctx, String XmlString, String zohoAccountID, String isZohoAccountIDUpdate, String isZohoInsert) {	
		try	
		{
      		System.setProperty("javax.net.ssl.trustStore", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStore"));
			System.setProperty("javax.net.ssl.keyStorePassword", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStorePassword"));
			System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
			
			//----------------------------Fetch Auth Token ----------------------
//			String targetURL = "https://crm.zoho.com/crm/private/xml/Accounts/updateRecords";
			String targetURL = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.modulesurl") + "/Accounts/" + zohoAccountID;
			String authtoken = "Zoho-oauthtoken " + ctx.get("isZohoAccessToken");//If you don't have a authtoken please refer this wiki https://zohocrmapi.wiki.zoho.com/using-authtoken.html
			
			PutMethod post = null;
			// Execute http request
			try
			{
				post = new PutMethod(targetURL);
				post.setRequestHeader("Authorization", authtoken);
				post.setRequestHeader("Content-Type", "application/json");
				post.setRequestBody(XmlString);

				HttpClient httpclient = new HttpClient();
				
				int result = httpclient.executeMethod(post);
				logger.debug("Zoho HTTP status: " + result);
				
				
				if(post.getResponseBodyAsString() != null && !"".equals(post.getResponseBodyAsString())){
	  				JSONObject captureZohoData = new JSONObject(post.getResponseBodyAsString());
					if(captureZohoData != null && !captureZohoData.isNull("data")){
						JSONArray captureZohoDataList = (JSONArray)captureZohoData.get("data");
						for(int captureZohoDataCount = 0; captureZohoDataCount < captureZohoDataList.length(); captureZohoDataCount++){
							captureZohoData = new JSONObject(captureZohoDataList.get(captureZohoDataCount).toString());
							if(!captureZohoData.isNull("message")){
								String message = (String) captureZohoData.get("message");
								if("the id given seems to be invalid".equals(message)){
									return false;
								}
							}
						}
	  				}
	  			}
				if("YES".equals(isZohoAccountIDUpdate)){
					ctx.put("ZohoAccountID", zohoAccountID);
					int status = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateZohoAccountID", ctx);
				}
				if(!("Insert".equals(isZohoInsert) || "Update".equals(isZohoInsert))){
					ctx.put("ISZohoInsert", "Update");
					int status = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateISZohoInsert", ctx);
				}
			} catch(Exception e) {
				logger.error("Unexpected error", e);
			} finally {
				post.releaseConnection();
			}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
		return true;
	}
    
    public static void getZOHOIndicationRecordsOfAccounts(Context ctx) {
    	try 
		{
    		if(ctx.get("WorkPhone") != null && !"".equals(ctx.get("WorkPhone")) 
				|| (ctx.get("AccountEmail") != null && !"".equals(ctx.get("AccountEmail")))){
    			
				String[] matchColumns = {"Account_Email","Phone"};
				String[] matchColumnValues = {"AccountEmail","WorkPhone"};
				
				int count = 0;
				
				JSONObject captureZohoData = getZOHOIndicationRecordAccounts(ctx,matchColumns,matchColumnValues);
				if(captureZohoData != null && !captureZohoData.isNull("data")){
					JSONArray captureZohoDataList = (JSONArray)captureZohoData.get("data");
					for(int captureZohoDataCount = 0; captureZohoDataCount < captureZohoDataList.length(); captureZohoDataCount++){
						captureZohoData = new JSONObject(captureZohoDataList.get(captureZohoDataCount).toString());
						
				        if(!captureZohoData.isNull("Account_Email") && captureZohoData.get("Account_Email").toString().equalsIgnoreCase(ctx.get("AccountEmail").toString())){
			        		count++;
				        }
			        	if(!captureZohoData.isNull("Phone") && captureZohoData.get("Phone").toString().replaceAll("\\D+","").equalsIgnoreCase(ctx.get("WorkPhone").toString().replaceAll("\\D+",""))){
			        		count++;
				        }
				        if(count != 0){
		
				        	boolean flag = false; 
				        	String zohoAccountID = (captureZohoData.isNull("id")) ? null : (String) captureZohoData.get("id");
				        	String county = (captureZohoData.isNull("County")) ? null : (String) captureZohoData.get("County");
				        	if(county != null && !"".equals(county)){
				        		ctx.put("CountyDesc",county);
				        	} else {
				        		flag = true;
				        	}
				        	String city = (captureZohoData.isNull("Billing_City")) ? null : (String) captureZohoData.get("Billing_City");
				        	if(city != null && !"".equals(city)){
				        		ctx.put("City",city);
				        	} else {
				        		flag = true;
				        	}
				        	String accountEmail = (captureZohoData.isNull("Account_Email")) ? null : (String) captureZohoData.get("Account_Email");
				        	if(accountEmail != null && !"".equals(accountEmail)){
				        		ctx.put("AccountEmail",accountEmail);
				        	} else {
				        		flag = true;
				        	}
				        	String accountName = (captureZohoData.isNull("Account_Name")) ? null : (String) captureZohoData.get("Account_Name");
				        	if(accountName != null && !"".equals(accountName)){
				        		ctx.put("AccountName",accountName);
				        	} else {
				        		flag = true;
				        	}
				        	String state = (captureZohoData.isNull("Billing_State")) ? null : (String) captureZohoData.get("Billing_State");
				        	if(state != null && !"".equals(state)){
				        		ctx.put("StateCode",state);
				        	} else {
				        		flag = true;
				        	}
				        	String phone = (captureZohoData.isNull("Phone")) ? null : (String) captureZohoData.get("Phone");
				        	if(phone != null && !"".equals(phone)){
				        		ctx.put("WorkPhone",phone);
				        	} else {
				        		flag = true;
				        	}
				        	boolean isNotSubProducer = false;
							Object objSubProducerRule = RuleUtils.executeRule(ctx, "LawyersRule.isNotSubproducerLoggedIn");
							if (objSubProducerRule != null && objSubProducerRule instanceof Boolean) {
								isNotSubProducer = (Boolean) objSubProducerRule;
							}

							if (isNotSubProducer) {
					        	String subProducer = (captureZohoData.isNull("Sub_Producer")) ? null : (String) captureZohoData.get("Sub_Producer");
					        	if(subProducer != null && !"".equals(subProducer)){
					        		
					        		try {
						        		ctx.put("ProducerCompanyName",subProducer);
						        		String subProducerCode = "";
						        		List getSubProducerCodeList = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoSubProducerCode", ctx);
						        		if(getSubProducerCodeList != null && getSubProducerCodeList.size() > 0 && !getSubProducerCodeList.isEmpty()){
						        			Map getSubProducerCodeMap = (Map)getSubProducerCodeList.get(0);
						        			subProducerCode = (getSubProducerCodeMap.get("ProducerCode") == null ? "" : getSubProducerCodeMap.get("ProducerCode").toString());
						        		}
						        		ctx.put("ProducerCode",subProducerCode);
					        		} catch (Exception e) {
				        				logger.error("Unexpected error", e);
				        			}
	
//					        	} else {
//					        		flag = true;
					        	}
							}
				        	String effectiveDate = (captureZohoData.isNull("Ex_Date")) ? null : (String) captureZohoData.get("Ex_Date");
				        	if(effectiveDate != null && !"".equals(effectiveDate)){
				        		SimpleDateFormat inSDF = new SimpleDateFormat("yyyy-MM-dd");
				        		SimpleDateFormat outSDF = new SimpleDateFormat("MM/dd/yyyy");
				        		String outDate = "";
			        	        try {
			        	            Date date = inSDF.parse(effectiveDate);
			        	            outDate = outSDF.format(date);
			        } catch (Exception ex){
								logger.error("Unable to parse Zoho effective date", ex);
			        }
				        	    
				        		ctx.put("PolicyEffectiveDate",outDate);
//				        	} else {
//				        		flag = true;
				        	}
				        	String billingStreet = (captureZohoData.isNull("Billing_Street")) ? null : (String) captureZohoData.get("Billing_Street");
				        	if(billingStreet != null && !"".equals(billingStreet)){
				        		ctx.put("Street",billingStreet);
				        	} else {
				        		flag = true;
				        	}
				        	String zipCode = (captureZohoData.isNull("Billing_Code")) ? null : (String) captureZohoData.get("Billing_Code");
				        	if(zipCode != null && !"".equals(zipCode)){
				        		ctx.put("Zip",zipCode);
				        	} else {
				        		flag = true;
				        	}
				        	
				        	if(flag){
				        		getZOHOIndicationRecordsOfContacts(ctx,flag);
				        	}
				        	break;
			        	}
				        count = 0;
					}
				} else {
//					LawyersUtils.populateError(ctx, "ZohoError","No Data Found in Zoho CRM.");
					getZOHOIndicationRecordsOfContacts(ctx,false);
	    		}
    		} else {
    			LawyersUtils.populateError(ctx, "ZohoError","Please Enter Insured Email.");
    		}
		} catch(Exception e) {
			logger.error("Unexpected error", e);
		}	
	}
	
    public static JSONObject getZOHOIndicationRecordAccounts(Context ctx, String[] matchColumns, String[] matchColumnValues)
    {
    	GetMethod get = null;
    	try{
      		System.setProperty("javax.net.ssl.trustStore", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStore"));
			System.setProperty("javax.net.ssl.keyStorePassword", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStorePassword"));
			System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
    		
	    	String serachTargetURL = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.modulesurl") + "/Accounts/search?";
//	    	https://crm.zoho.com/crm/private/xml/Accounts/searchRecords?
//	    	https://www.zohoapis.com/crm/v2/Accounts/search?
//	    	https://www.zohoapis.com/crm/v2/Leads/search?
	    	String authtoken = "Zoho-oauthtoken " + ctx.get("isZohoAccessToken");//If you don't have a authtoken please refer this wiki https://zohocrmapi.wiki.zoho.com/using-authtoken.html 
			String selectColumns ="Account_Name,Billing_Code,Billing_Street,Billing_State,Billing_City,County,Phone,Account_Email,Sub_Producer,NameClearStatus,NameClearType,NameClearDate,RequestedAppName,Deductible,Firm_Limit,Ex_Date";
			String criteria = "(";
			for(int criteriaCount = 0; criteriaCount < matchColumns.length; criteriaCount++){
				if(!"(".equals(criteria) && criteriaCount > 0 && ctx.get(matchColumnValues[criteriaCount]) != null && !"".equals(ctx.get(matchColumnValues[criteriaCount]))){
					criteria += "OR";
				}
				if("Phone".equals(matchColumns[criteriaCount]) && ctx.get(matchColumnValues[criteriaCount]) != null && !"".equals(ctx.get(matchColumnValues[criteriaCount]))){
					String workPhone = ctx.get(matchColumnValues[criteriaCount]).toString().replaceAll("\\D+","");
					workPhone = workPhone.substring(0,3) + "-" + workPhone.substring(3,6) + "-" + workPhone.substring(6,10);
					criteria += "(" + matchColumns[criteriaCount] + ":equals:" + URLEncoder.encode(workPhone, "UTF-8") + ")";
				} else if("Account_Name".equals(matchColumns[criteriaCount]) && ctx.get(matchColumnValues[criteriaCount]) != null && !"".equals(ctx.get(matchColumnValues[criteriaCount]))){
					criteria += "(" + matchColumns[criteriaCount] + ":equals:" + URLEncoder.encode(ctx.get(matchColumnValues[criteriaCount]).toString().replaceAll(" ", "_"), "UTF-8") + ")";
				} else if(ctx.get(matchColumnValues[criteriaCount]) != null && !"".equals(ctx.get(matchColumnValues[criteriaCount]))){
					criteria += "(" + matchColumns[criteriaCount] + ":equals:" + URLEncoder.encode(ctx.get(matchColumnValues[criteriaCount]).toString(), "UTF-8") + ")";
				}
			}
			criteria += ")";
			
			//Authorization: Zoho-oauthtoken d92d401c803988c5cb849d0b4215f52"
			//----------------------------Fetch ZOHO Data ----------------------
			serachTargetURL += "fields=" + selectColumns + "&criteria=" + criteria;
			get = new GetMethod(serachTargetURL);
			get.setRequestHeader("Authorization", authtoken);
			
			HttpClient httpclient = new HttpClient();
			int result = httpclient.executeMethod(get);
			
			logger.debug("Zoho HTTP status: " + result);

			if(get.getResponseBodyAsString() != null && !"".equals(get.getResponseBodyAsString())){
				return new JSONObject(get.getResponseBodyAsString());
			}
    	} catch(Exception e){
    		logger.error("Unexpected error", e);
		}
    	return null;
    }
    
    public static void getZOHOIndicationRecordsOfContacts(Context ctx, boolean flag) {
    	try 
		{
    		if(ctx.get("WorkPhone") != null && !"".equals(ctx.get("WorkPhone")) 
				|| (ctx.get("AccountEmail") != null && !"".equals(ctx.get("AccountEmail")))){
    			
				String[] matchColumns = {"Email","Phone"};
				String[] matchColumnValues = {"AccountEmail","WorkPhone"};
				
				int count = 0;
				
				JSONObject captureZohoData = getZOHOIndicationRecordContacts(ctx,matchColumns,matchColumnValues);
				if(captureZohoData != null && !captureZohoData.isNull("data")){
					JSONArray captureZohoDataList = (JSONArray)captureZohoData.get("data");
					for(int captureZohoDataCount = 0; captureZohoDataCount < captureZohoDataList.length(); captureZohoDataCount++){
						captureZohoData = new JSONObject(captureZohoDataList.get(captureZohoDataCount).toString());
						
				        if(!captureZohoData.isNull("Email") && captureZohoData.get("Email").toString().equalsIgnoreCase(ctx.get("AccountEmail").toString())){
			        		count++;
				        }
			        	if(!captureZohoData.isNull("Phone") && captureZohoData.get("Phone").toString().replaceAll("\\D+","").equalsIgnoreCase(ctx.get("WorkPhone").toString().replaceAll("\\D+",""))){
			        		count++;
				        }
				        if(count != 0){

				        	String zohoAccountID = (captureZohoData.isNull("id")) ? null : (String) captureZohoData.get("id");
				        	String accountName = (captureZohoData.isNull("Account_Name")) ? null : (String) ((JSONObject)captureZohoData.get("Account_Name")).get("name");
				        	if(accountName != null && !"".equals(accountName) && "".equals(ctx.get("AccountName"))){
				        		ctx.put("AccountName",accountName);
				        	}
				        	String phone = (captureZohoData.isNull("Phone")) ? null : (String) captureZohoData.get("Phone");
				        	if(phone != null && !"".equals(phone) && "".equals(ctx.get("WorkPhone"))){
				        		ctx.put("WorkPhone",phone);
				        	}
				        	String accountEmail = (captureZohoData.isNull("Email")) ? null : (String) captureZohoData.get("Email");
				        	if(accountEmail != null && !"".equals(accountEmail) && "".equals(ctx.get("AccountEmail"))){
				        		ctx.put("AccountEmail",accountEmail);
				        	}
				        	String county = (captureZohoData.isNull("County")) ? null : (String) captureZohoData.get("County");
				        	if(county != null && !"".equals(county) && "".equals(ctx.get("CountyDesc"))){
				        		ctx.put("CountyDesc",county);
				        	}
				        	String billingStreet = (captureZohoData.isNull("Mailing_Street")) ? null : (String) captureZohoData.get("Mailing_Street");
				        	if(billingStreet != null && !"".equals(billingStreet) && "".equals(ctx.get("Street"))){
				        		ctx.put("Street",billingStreet);
				        	}
				        	String city = (captureZohoData.isNull("Mailing_City")) ? null : (String) captureZohoData.get("Mailing_City");
				        	if(city != null && !"".equals(city) && "".equals(ctx.get("City"))){
				        		ctx.put("City",city);
				        	}
				        	String state = (captureZohoData.isNull("Mailing_State")) ? null : (String) captureZohoData.get("Mailing_State");
				        	if(state != null && !"".equals(state) && "".equals(ctx.get("StateCode"))){
				        		ctx.put("StateCode",state);
				        	}
				        	String zipCode = (captureZohoData.isNull("Mailing_Zip")) ? null : (String) captureZohoData.get("Mailing_Zip");
				        	if(zipCode != null && !"".equals(zipCode) && "".equals(ctx.get("Zip"))){
				        		ctx.put("Zip",zipCode);
				        	}
				        	
				        	if(!flag)
				        		getZOHOIndicationRecordsOfAccountsSecond(ctx);

				        	break;
			        	}
				        count = 0;
					}
				} else {
					if(!flag)
	    			LawyersUtils.populateError(ctx, "ZohoError","No Data Found in Zoho CRM.");
	    		}
    		} else {
    			LawyersUtils.populateError(ctx, "ZohoError","Please Enter Insured Email.");
    		}
		} catch(Exception e) {
			logger.error("Unexpected error", e);
		}	
	}
    
    public static JSONObject getZOHOIndicationRecordContacts(Context ctx, String[] matchColumns, String[] matchColumnValues)
    {
    	GetMethod get = null;
    	try{
      		System.setProperty("javax.net.ssl.trustStore", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStore"));
			System.setProperty("javax.net.ssl.keyStorePassword", SystemProperties.getInstance().getString("appl.LawyersIns.webservice.securitycacerts.keyStorePassword"));
			System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
    		
	    	String serachTargetURL = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.modulesurl") + "/Contacts/search?";
//	    	https://crm.zoho.com/crm/private/xml/Accounts/searchRecords?
//	    	https://www.zohoapis.com/crm/v2/Accounts/search?
//	    	https://www.zohoapis.com/crm/v2/Leads/search?
	    	String authtoken = "Zoho-oauthtoken " + ctx.get("isZohoAccessToken");//If you don't have a authtoken please refer this wiki https://zohocrmapi.wiki.zoho.com/using-authtoken.html 
			String selectColumns ="Account_Name,Phone,Email,County,Mailing_Street,Mailing_City,Mailing_State,Mailing_Zip";
			String criteria = "(";
			for(int criteriaCount = 0; criteriaCount < matchColumns.length; criteriaCount++){
				if(!"(".equals(criteria) && criteriaCount > 0 && ctx.get(matchColumnValues[criteriaCount]) != null && !"".equals(ctx.get(matchColumnValues[criteriaCount]))){
					criteria += "OR";
				}
				if("Phone".equals(matchColumns[criteriaCount]) && ctx.get(matchColumnValues[criteriaCount]) != null && !"".equals(ctx.get(matchColumnValues[criteriaCount]))){
					String workPhone = ctx.get(matchColumnValues[criteriaCount]).toString().replaceAll("\\D+","");
					workPhone = workPhone.substring(0,3) + "-" + workPhone.substring(3,6) + "-" + workPhone.substring(6,10);
					criteria += "(" + matchColumns[criteriaCount] + ":equals:" + URLEncoder.encode(workPhone, "UTF-8") + ")";
				} else if("Account_Name".equals(matchColumns[criteriaCount]) && ctx.get(matchColumnValues[criteriaCount]) != null && !"".equals(ctx.get(matchColumnValues[criteriaCount]))){
					criteria += "(" + matchColumns[criteriaCount] + ":equals:" + URLEncoder.encode(ctx.get(matchColumnValues[criteriaCount]).toString().replaceAll(" ", "_"), "UTF-8") + ")";
				} else if(ctx.get(matchColumnValues[criteriaCount]) != null && !"".equals(ctx.get(matchColumnValues[criteriaCount]))){
					criteria += "(" + matchColumns[criteriaCount] + ":equals:" + URLEncoder.encode(ctx.get(matchColumnValues[criteriaCount]).toString(), "UTF-8") + ")";
				}
			}
			criteria += ")";
			
			//Authorization: Zoho-oauthtoken d92d401c803988c5cb849d0b4215f52"
			//----------------------------Fetch ZOHO Data ----------------------
			serachTargetURL += "fields=" + selectColumns + "&criteria=" + criteria;
			get = new GetMethod(serachTargetURL);
			get.setRequestHeader("Authorization", authtoken);
			
			HttpClient httpclient = new HttpClient();
			int result = httpclient.executeMethod(get);
			
			logger.debug("Zoho HTTP status: " + result);

			if(get.getResponseBodyAsString() != null && !"".equals(get.getResponseBodyAsString())){
				return new JSONObject(get.getResponseBodyAsString());
			}
    	} catch(Exception e){
    		logger.error("Unexpected error", e);
		}
    	return null;
    }
    
    public static void getZOHOIndicationRecordsOfAccountsSecond(Context ctx) {
    	try 
		{
    		if(ctx.get("WorkPhone") != null && !"".equals(ctx.get("WorkPhone")) 
				|| (ctx.get("AccountEmail") != null && !"".equals(ctx.get("AccountEmail")))){
    			
				String[] matchColumns = {"Account_Email","Phone"};
				String[] matchColumnValues = {"AccountEmail","WorkPhone"};
				
				int count = 0;
				
				JSONObject captureZohoData = getZOHOIndicationRecordAccounts(ctx,matchColumns,matchColumnValues);
				if(captureZohoData != null && !captureZohoData.isNull("data")){
					JSONArray captureZohoDataList = (JSONArray)captureZohoData.get("data");
					for(int captureZohoDataCount = 0; captureZohoDataCount < captureZohoDataList.length(); captureZohoDataCount++){
						captureZohoData = new JSONObject(captureZohoDataList.get(captureZohoDataCount).toString());
						
				        if(!captureZohoData.isNull("Account_Email") && captureZohoData.get("Account_Email").toString().equalsIgnoreCase(ctx.get("AccountEmail").toString())){
			        		count++;
				        }
			        	if(!captureZohoData.isNull("Phone") && captureZohoData.get("Phone").toString().replaceAll("\\D+","").equalsIgnoreCase(ctx.get("WorkPhone").toString().replaceAll("\\D+",""))){
			        		count++;
				        }
				        if(count != 0){
		
				        	String zohoAccountID = (captureZohoData.isNull("id")) ? null : (String) captureZohoData.get("id");
				        	String county = (captureZohoData.isNull("County")) ? null : (String) captureZohoData.get("County");
				        	if(county != null && !"".equals(county)){
				        		ctx.put("CountyDesc",county);
				        	}
				        	String city = (captureZohoData.isNull("Billing_City")) ? null : (String) captureZohoData.get("Billing_City");
				        	if(city != null && !"".equals(city)){
				        		ctx.put("City",city);
				        	}
				        	String accountEmail = (captureZohoData.isNull("Account_Email")) ? null : (String) captureZohoData.get("Account_Email");
				        	if(accountEmail != null && !"".equals(accountEmail)){
				        		ctx.put("AccountEmail",accountEmail);
				        	}
				        	String accountName = (captureZohoData.isNull("Account_Name")) ? null : (String) captureZohoData.get("Account_Name");
				        	if(accountName != null && !"".equals(accountName)){
				        		ctx.put("AccountName",accountName);
				        	}
				        	String state = (captureZohoData.isNull("Billing_State")) ? null : (String) captureZohoData.get("Billing_State");
				        	if(state != null && !"".equals(state)){
				        		ctx.put("StateCode",state);
				        	}
				        	String phone = (captureZohoData.isNull("Phone")) ? null : (String) captureZohoData.get("Phone");
				        	if(phone != null && !"".equals(phone)){
				        		ctx.put("WorkPhone",phone);
				        	}
				        	boolean isNotSubProducer = false;
							Object objSubProducerRule = RuleUtils.executeRule(ctx, "LawyersRule.isNotSubproducerLoggedIn");
							if (objSubProducerRule != null && objSubProducerRule instanceof Boolean) {
								isNotSubProducer = (Boolean) objSubProducerRule;
							}

							if (isNotSubProducer) {
					        	String subProducer = (captureZohoData.isNull("Sub_Producer")) ? null : (String) captureZohoData.get("Sub_Producer");
					        	if(subProducer != null && !"".equals(subProducer)){
					        		
					        		try {
						        		ctx.put("ProducerCompanyName",subProducer);
						        		String subProducerCode = "";
						        		List getSubProducerCodeList = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetZohoSubProducerCode", ctx);
						        		if(getSubProducerCodeList != null && getSubProducerCodeList.size() > 0 && !getSubProducerCodeList.isEmpty()){
						        			Map getSubProducerCodeMap = (Map)getSubProducerCodeList.get(0);
						        			subProducerCode = (getSubProducerCodeMap.get("ProducerCode") == null ? "" : getSubProducerCodeMap.get("ProducerCode").toString());
						        		}
						        		ctx.put("ProducerCode",subProducerCode);
					        		} catch (Exception e) {
				        				logger.error("Unexpected error", e);
				        			}
					        	}
							}
				        	String effectiveDate = (captureZohoData.isNull("Ex_Date")) ? null : (String) captureZohoData.get("Ex_Date");
				        	if(effectiveDate != null && !"".equals(effectiveDate)){
				        		SimpleDateFormat inSDF = new SimpleDateFormat("yyyy-MM-dd");
				        		SimpleDateFormat outSDF = new SimpleDateFormat("MM/dd/yyyy");
				        		String outDate = "";
			        	        try {
			        	            Date date = inSDF.parse(effectiveDate);
			        	            outDate = outSDF.format(date);
			        } catch (Exception ex){
								logger.error("Unable to parse Zoho effective date", ex);
			        }
				        	    
				        		ctx.put("PolicyEffectiveDate",outDate);
				        	}
				        	String billingStreet = (captureZohoData.isNull("Billing_Street")) ? null : (String) captureZohoData.get("Billing_Street");
				        	if(billingStreet != null && !"".equals(billingStreet)){
				        		ctx.put("Street",billingStreet);
				        	}
				        	String zipCode = (captureZohoData.isNull("Billing_Code")) ? null : (String) captureZohoData.get("Billing_Code");
				        	if(zipCode != null && !"".equals(zipCode)){
				        		ctx.put("Zip",zipCode);
				        	}
				        	break;
			        	}
				        count = 0;
					}
	    		}
    		}
		} catch(Exception e) {
			logger.error("Unexpected error", e);
		}	
	}
    
	public static void setProducerCodeForInsured(Context ctx)
	{
		ctx.put("ProducerCode",ctx.get("SubProducerID"));
		
	}	
	
	public static void validateApplicationForcompletion(Context ctx)
	 {
		 try {	
				//By Raghu CCB Supplement
			 if(ctx.get("ElectronicInsuranceImplDate") instanceof String) {
				 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			     Date parsedDate = dateFormat.parse(ctx.get("ElectronicInsuranceImplDate").toString());
				 ctx.put("ElectronicInsuranceImplDate", new Timestamp(parsedDate.getTime()));
			 }
				
				 	new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,AccountKey,litigationNewImplDate,IsCCBFlag,RenewalSupplementNewImplDate,isCannibSuppFlow,BankruptcySuppImplDate");
					List validatePageData =(List)SqlResources.getSqlMapProcessor(ctx).select("FirmLW.ValidateIndivisualPageCompletion_p", ctx);
					
					if(validatePageData!=null && validatePageData.size()>0)
					ctx.putAll((Map) validatePageData.get(0));
					String isNotCompletlyFilled=!ctx.get("isNotCompletlyFilled").equals(HtmlConstants.EMPTY) && ctx.get("isNotCompletlyFilled")!=null?ctx.get("isNotCompletlyFilled").toString():"";
					String firmNewAppPage=!ctx.get("firmNewAppPage").equals(HtmlConstants.EMPTY) && ctx.get("firmNewAppPage")!=null?ctx.get("firmNewAppPage").toString():"";
					String insuranceHistoryPage=!ctx.get("insuranceHistoryPage").equals(HtmlConstants.EMPTY) && ctx.get("insuranceHistoryPage")!=null?ctx.get("insuranceHistoryPage").toString():"";
					String attorneysPage=!ctx.get("attorneysPage").equals(HtmlConstants.EMPTY) && ctx.get("attorneysPage")!=null?ctx.get("attorneysPage").toString():"";
					String lawPracticePage=!ctx.get("lawPracticePage").equals(HtmlConstants.EMPTY) && ctx.get("lawPracticePage")!=null?ctx.get("lawPracticePage").toString():"";
					
					RuleUtils.executeRule(ctx, "LawyersRule.checkCCBSupp");
					boolean flowRule = false;        
					Object objRatingRule = RuleUtils.executeRule(ctx,"LawyersRule.isInternalUser");
			        if (objRatingRule != null && objRatingRule instanceof Boolean) {
			        	flowRule = (Boolean) objRatingRule;
			        }	   
			        if(flowRule)
			        {
			        	if("Y".equals(firmNewAppPage) || "Y".equals(insuranceHistoryPage) || "Y".equals(attorneysPage) || "Y".equals(lawPracticePage))
			        		LawyersUtils.populateError(ctx, "ApplicationNotCompleted","Please complete the application.");
			        }
			        else
			        	if("Y".equals(isNotCompletlyFilled))
			        		LawyersUtils.populateError(ctx, "ApplicationNotCompleted","Please complete the application.");
		        
				
		 	}catch (Exception e) {
					logger.error("Unexpected error", e);	
			}
		 
	 }
public static void processPolicyForNewFiling(Context ctx)
{
	try
	{
		char updateCoverage='N';
		/*boolean isMissouriAndNewFiling = false;        
		RuleUtils.executeRule(ctx,"LawyersRule.AssignClaimExpensesAndDollarDefense");
		if(ctx.get("IsClaimExpensesType")!=null ||ctx.get("IsDollarDefense")!=null)
			updateCoverage='Y';*/
		ctx.put("updateCoverage", updateCoverage);
    	}
	catch(Exception e)
	{
		logger.error("Unexpected error", e);
	}
	}

public static void reatinAddedAops(Context ctx)
{
	try
	{
		List oldSaveAopList=(List)ctx.get("aoplist_list_2");
		
	//	System.out.println("hello");

		
        List inputList=new ArrayList();
        Context newCtx=new Context();
		newCtx.setProject(ctx.getProject());
		newCtx.putAll(ctx);
        try
        {
            
            List finalList = new ArrayList();
            if(ctx.get("aoplist_list_2") != null &&   ctx.get("aoplist_list_2") instanceof List)
            {
                finalList = (List)ctx.get("aoplist_list_2");
            }
            Map reqMap = new HashMap();
            Map map = null;
            for (int i = 0; i < finalList.size(); i++) 
    		{
    				reqMap = (HashMap) finalList.get(i);
    				map=new HashMap();
    				map.put("AOPKey",reqMap.get("Aopkey"));
    				map.put("Percentage",("".equals(ctx.get("AOP_Percentage_"+i)) || "0".equals(ctx.get("AOP_Percentage_"+i))) ? null : ctx.get("AOP_Percentage_"+i));
    				map.put("PolicyKey",ctx.get("PolicyKey"));
    				map.put("PercentageOld",("".equals(ctx.get("AOP_PercentageOld_"+i)) || "0".equals(ctx.get("AOP_PercentageOld_"+i))) ? null : ctx.get("AOP_PercentageOld_"+i));
    				map.put("IsEdit",reqMap.get("IsEdit"));
    				map.put("CreatedBy", ctx.get("LastUpdateUserID"));
					map.put("CreatedDate",ctx.get("LastUpdateTimeStamp"));
    				inputList.add(map);
    		}
            newCtx.put("inputList", inputList);
			LawyersUtils.convertListDataToXML(newCtx,"inputList","outputList");
			ctx.put("outputList",newCtx.get("outputList"));
	}
	catch(Exception e)
	{
		logger.error("Unexpected error", e);
	}

	}
	catch(Exception e)
	{
		logger.error("Unable to retain AOP data", e);
	}
}


public void sendMailForSubproducerFlow(IContext ctx) throws Exception {
	String mailingOnOFF = null;
	try {
		mailingOnOFF = SystemProperties.getInstance().getString(
				"Insured.sendmail");
	} catch (Exception e) {
		logger.error("Unable to read insured mail setting", e);
	}
	if (mailingOnOFF != null && "Y".equals(mailingOnOFF)) {
		String emailID = getSubProducerEmailID(ctx);
		// String emailID = "jaspreets@outlinesys.com";
		if ("".equals(emailID))
			return;
		
		//MailSender mail = new MailSender();
//		mail.setToAdd(emailID);
//		mail.setFromAdd(emailID);
//		mail.setCcAdd(emailID);
		/*mail.setSubject("Thank you for your interest in Lawyers Application By Subproducer.");
		mail.setContentType("text/html");
		mail.setBody(generateThanksLetterBodySubProducerFlow(ctx));
		mail.sendMailtoSub(ctx);
		*/
		String ccAddress= "";
		
		ccAddress=getProducerEmail(ctx);
		
		ctx.put("toAddress",emailID);
		ctx.put("ccAddress",ccAddress);
		String thankuheader="Thank you for your interest in Lawyers professional liability from "+ctx.get("ProducerName")+".";
		ctx.put("subject",thankuheader);
		ctx.put("body", generateThanksLetterBodySubProducerFlow(ctx));
		//ctx.put(key, value);
		SubproducerMailer.sendEmailAsSubProducer((Context)ctx);
		logger.debug("Mail has sent---- ");

	}
}

public  String generateThanksLetterBodySubProducerFlow(IContext ctx) throws Exception {
	//String filePath=new DocumentGenerationBO().imageDownload(ctx.get("Signature").toString());
	String encryptedProducerCode=new LawyersValidationUtils().encryptForUrl(ctx.get("ProducerCode").toString());
	LawyersValidationUtils utils = new LawyersValidationUtils();
	String encrytedPolicyKey = utils.encrypt(ctx.get("PolicyKey")
			.toString());
	encrytedPolicyKey = URLEncoder.encode(encrytedPolicyKey,""+ StandardCharsets.UTF_8);
	/*encrytedPolicyKey = encrytedPolicyKey.replace("=", "%3D");
	encrytedPolicyKey = encrytedPolicyKey.replace("+", "%2B");*/
	
	String SignatureText=ctx.get("SignatureText")!=null?ctx.get("SignatureText").toString():"";
	
	String fax=null;
	
	if(ctx.get("Fax") != null && !"".equals(ctx.get("Fax")) && !"null".equals(ctx.get("Fax"))){
		fax="F:&nbsp;"+ctx.get("Fax").toString();
	}
	else{
		fax="";
	}
	
	String emailsig="";
	 if(ctx.get("EmailSignature")!=null && !"".equals(ctx.get("EmailSignature"))){
		 emailsig="<img src=\"cid:image1\">";
	 }
	 
	 String SignatureText2="";
	 if(ctx.get("SignatureText2")!=null && !"".equals(ctx.get("SignatureText2"))){
		 SignatureText2=ctx.get("SignatureText2").toString();
	 }
	 String SignatureText3="";
	 if(ctx.get("SignatureText3")!=null && !"".equals(ctx.get("SignatureText3"))){
		 SignatureText3="<img src=\"cid:image2\">";
		 SignatureText3="<td style='border-left: 3px solid green; padding-left: 5px'>"+SignatureText3+"</td>";
	 }
	 
	 String Signature="";
	 if(ctx.get("Signature")!=null && !"".equals(ctx.get("Signature"))){
		 Signature="<tr><td><img src=\"cid:image\"></td></tr>";
	 }
	 
	 String subProducerCode = SystemProperties.getInstance().getString("appl.LawyersIns.subproducer.signaturedisplay");
  	String[] subProducerCodeList = subProducerCode.split("~");
  	for(int subProducerCodeCount = 0; subProducerCodeCount < subProducerCodeList.length; subProducerCodeCount++){
  		if(subProducerCodeList[subProducerCodeCount].equalsIgnoreCase(ctx.get("ProducerCode").toString())){
  			Signature = "";
  		}
  	}
	
	StringBuilder msg = new StringBuilder(2048);
	msg.append("<table>");
	msg.append("<tr>");
	msg.append("<td>");

	msg.append("Dear ").append(ctx.get("AccountName").toString()).append(",<br/><br/>");
	msg.append("Thank you for beginning the ").append(ctx.get("ProducerName")).append(" lawyers application. We appreciate the opportunity to provide you with a quote. Choosing the right plan is very important and we believe that the options we offer will provide you and your firm with peace of mind.<br/><br/>");
	msg.append("Once you have completed your application, our underwriters will begin the review process and will contact you within one business day.<br/><br/>");
	msg.append("If you need to complete your application <a href='")
			.append(getProjectUrl(ctx)).append("/completeapplink.jsp?PolicyKey=")
	            .append(encrytedPolicyKey).append("&amp;SubProducer=").append(encryptedProducerCode).append("'>click here</a>.<br/><br/>");
	msg.append("If you have any questions or concerns, please call us toll free at ").append(ctx.get("SPPhoneNumber")).append(" , and one of our Account Executives will assist you.<br/><br/>");

	msg.append("Thank you again for considering ").append(ctx.get("ProducerName")).append(" for your Professional Liability needs.<br/><br/>");
	msg.append("Best regards,<br/>");
	msg.append("<table cellpadding='0' cellspacing='0'>").append(Signature).append("<tr><td>").append(SignatureText).append("P:&nbsp;").append(ctx.get("SPPhoneNumber")).append("&nbsp;</br>").append(fax).append("</td>").append(SignatureText3).append("</tr></table>");
	msg.append(emailsig).append("</br>");
    msg.append("</br>");
    msg.append("<p style='color:#999999'>").append(SignatureText2).append("</p>");
	msg.append("</td>");
	msg.append("</tr>");
	msg.append("</table>");
	return msg.toString();
}
	public static void loadLogoImagePath(IContext ctx){
		
		try{
	    	String fileURL = ctx.get("LogoImagePath") == null ? "" : ctx.get("LogoImagePath").toString().trim();
	    	
	    	if(!"".equals(fileURL) && !"null".equalsIgnoreCase(fileURL)){
	    		ImageDownld(fileURL);
	    	}
	    	
	    	ctx.put("LogoImagePath", "image/SUBPRODUCERLOGO.png");
    	} catch(Exception ee) {
    		logger.error("Unexpected error", ee);
        } 
	}
	public static void ImageDownld(String fileURL){
		File filedlt = null;
		FileOutputStream outputStream = null;
		ByteArrayOutputStream bout = null;
		
		try{
			String filePath=SystemProperties.getInstance().getString("appl.LawyersIns.subproducerlogopath");
			filedlt=new File(filePath + "SUBPRODUCERLOGO.png");
			if(filedlt.exists()){
			filedlt.delete();
			}
			
			String fileName = filePath + fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
			outputStream = new FileOutputStream(new File(fileName));
			
			String userName = (new DocumentManagment()).getUserName();
			String password = (new DocumentManagment()).getUserPassword();
			String domain = (new DocumentManagment()).getDomainName();
			String baseDir = (new DocumentManagment()).getSharePointBaseDirectory();
	
			byte[] barr = new DocManagementUtil().downloadDocFromSharePoint(fileURL,
					userName, password, domain);
	
			if(barr != null){
			bout = new ByteArrayOutputStream();
			bout.write(barr);
			bout.writeTo(outputStream);
			
			outputStream.flush();
			bout.close();
			outputStream.close();
		        
	        logger.debug("File downloaded");
	        String inputImage = fileName;
	        String oututImage = filePath + "SUBPRODUCERLOGO.png";
	        String formatName = "PNG";
	        try {
	            boolean result = DocumentGenerationBO.convertFormat(inputImage,oututImage, formatName);
	            if (result) {
	                logger.debug("Image converted successfully.");
	            } else {
	                logger.debug("Could not convert image.");
	            }
	        } catch (IOException ex) {
	            logger.error("Unable to convert image", ex);
	            logger.error("Unexpected error", ex);
	        }
	        filedlt=new File(fileName);
			filedlt.delete();
			}
			
		}catch(Exception e){
			logger.error("Unable to download quote letter image", e);
			logger.error("Error in ImageDownload For QuoteLetter"	+ e.getMessage());
			logger.error("Unexpected error", e);
		} finally {
			try{
				/*code by sukhi 26/09/2018*/
				if(outputStream != null){
					outputStream.close();
					outputStream = null;
				}
				if(bout != null){
					bout.close();
					bout = null;
				}
				filedlt=null;
			}catch(Exception e){
				logger.error("Unexpected error", e);
			}
		}
	}
	
	public static void SubProducerNumDefault(IContext ctx){
		
		try{
			if(ctx.get("ProducerCode") == null || "".equals(ctx.get("ProducerCode"))){
			Object obj_code = null;
			Object obj = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.SubproducerFormviewgetSubproducerformId", ctx);
			Integer producer_id = ((Map<String,Integer>)((List) obj).get(0)).get("ProducerId");
			do{
				producer_id += 1;
				String producerCode="P"+String.format("%07d", producer_id); 
				logger.debug(producerCode);
				ctx.put("ProducerCode", producerCode);
				obj_code = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.SubproducerFormviewgetSubproducerformcode", ctx);
			} while(obj_code != null);
			}
			
		}catch(Exception ex){
			logger.error("Unexpected error", ex);
		}
	}
	public void generatePublicLink(IContext ctx) {
		try{
			String bccAddress= "",toAddress="";
			//<img src='"+filePath+"' />
			ctx.put("PublicinsuredEmail",ctx.get("AccountEmailSearch"));
			logger.debug("Going to generate email content for public link email.");
			String SignatureText=ctx.get("SignatureText")!=null?ctx.get("SignatureText").toString():"";
			String fax=null;
			
			if(ctx.get("Fax") != null && !"".equals(ctx.get("Fax")) && !"null".equals(ctx.get("Fax"))){
				fax="F:&nbsp;"+ctx.get("Fax").toString();
			}
			else{
				fax="";
			}
			
			String emailsig="";
			 if(ctx.get("EmailSignature")!=null && !"".equals(ctx.get("EmailSignature"))){
				 emailsig="<img src=\"cid:image1\">";
			 }
			 
			 String SignatureText2="";
			 if(ctx.get("SignatureText2")!=null && !"".equals(ctx.get("SignatureText2"))){
				 SignatureText2=ctx.get("SignatureText2").toString();
			 }
			 
			 String SignatureText3="";
			 if(ctx.get("SignatureText3")!=null && !"".equals(ctx.get("SignatureText3"))){
				 SignatureText3="<td style='border-left: 3px solid green; padding-left: 5px'><img src=\"cid:image2\"></td>";
				}
			 
			 String Signature="";
			 if(ctx.get("Signature")!=null && !"".equals(ctx.get("Signature"))){
				 Signature="<tr><td><img src=\"cid:image\"></td></tr>";
			 }
			 
			 String subProducerCode = SystemProperties.getInstance().getString("appl.LawyersIns.subproducer.signaturedisplay");
		     	String[] subProducerCodeList = subProducerCode.split("~");
		     	for(int subProducerCodeCount = 0; subProducerCodeCount < subProducerCodeList.length; subProducerCodeCount++){
		     		if(subProducerCodeList[subProducerCodeCount].equalsIgnoreCase(ctx.get("ProducerCode").toString())){
		     			Signature = "";
		     		}
		     	}
			
		String encryptedProducerCode=new LawyersValidationUtils().encryptForUrl(ctx.get("ProducerCode").toString());
		String publicApplicationUrl = SystemProperties.getInstance().getString(
				"appl.LawyersIns.nativeEnvironment") + "LawyersIns/insured.jsp?SubProducer=" + encryptedProducerCode;
		//String filePath=new DocumentGenerationBO().imageDownload(ctx.get("Signature").toString());
			String msg = new StringBuilder(1536)
					.append("<table> <tr> <td colspan='2'> Hi,<BR><BR><BR> Below is a link to the online application. This program is designed for small to mid-sized law firms.<br> They offer competitive coverage through ISMIE Mutual, with a streamlined application and easy renewal process. <BR>  <BR> <a  href='")
					.append(publicApplicationUrl)
					.append("'>Apply Online</a>  <BR><BR>Please let us know if you have any additional questions. Call us at ")
					.append(ctx.get("SPPhoneNumber"))
					.append(".<BR> </td></tr> <tr><td></td></tr><tr> <td>Best Regards,</td> </tr><tr><td><table cellpadding='0' cellspacing='0'>")
					.append(Signature)
					.append("<tr><td>")
					.append(SignatureText)
					.append("P:&nbsp;")
					.append(ctx.get("SPPhoneNumber"))
					.append("</br>")
					.append(fax)
					.append("</td>")
					.append(SignatureText3)
					.append("</tr></table></td></tr><tr><td>")
					.append(emailsig)
					.append("</td></tr><tr><td></td></tr><tr><td style='color:#999999'>")
					.append(SignatureText2)
					.append("</td></tr></table>")
					.toString();
		
		String environmentVar = null;
		environmentVar = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment");
		if(environmentVar.equals("DEV")||environmentVar.equals("QA")|| environmentVar.equals("UAT"))
		{
			toAddress=SystemProperties.getInstance().getString("appl.LawyersIns.admin.email");
			bccAddress=SystemProperties.getInstance().getString("appl.LawyersIns.admin.bcc.email");
		}
		else
		{
			toAddress =ctx.get("PublicinsuredEmail").toString();
			bccAddress=getProducerEmail(ctx);
		}
			
		if ("".equals(toAddress)){
			LawyersUtils.populateError(ctx, "PublicinsuredEmail","Please enter the Email.");
			return;
		}
		ctx.put("subject", "On-line application from "+ctx.get("ProducerName")+".");
		ctx.put("body", msg);
		ctx.put("toAddress",toAddress);
		ctx.put("bccAddress",bccAddress);
		logger.debug("Mail is going---- ");
		SubproducerMailer.sendEmailAsSubProducer((Context)ctx);
		/*MailSender mail = new MailSender();
		mail.setToAdd(dstId);
		mail.setCcAdd(cc_role_desc);
		mail.setSubject("On-line application from "+ctx.get("ProducerName")+".");
		mail.setContentType("text/html");
		mail.setBody(msg);
		mail.sendMail((Context) ctx);*/
		logger.debug("Mail has sent---- ");
		ctx.put("SendMailToSubproducerInsured", "Mail has been sent successfully.");
		}catch(Exception e){
			try{
				LawyersUtils.populateError(ctx, "PublicinsuredEmail", "Mail has not been sent successfully.");
			}catch(Exception ee){
				logger.error("Unexpected error", ee);
			}
			logger.error("Unexpected error", e);
		}
		
	}
	public  void decryptSubproducerNumber(IContext ctx)
	{
		try
		{
			String pNumber=ctx.get("SubProducer")!=null && !ctx.get("SubProducer").equals(HtmlConstants.EMPTY) ?ctx.get("SubProducer").toString().trim():"";
		if(!"".equals(pNumber) && !"SPACE".equalsIgnoreCase(pNumber))
		{
			HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
	        HttpSession sess = req.getSession();
	      
	        String decryptPnumber=new LawyersValidationUtils().decrypt(pNumber);
			ctx.put("SubProducer",decryptPnumber );
				}
		}
		catch(Exception e)
		{
			logger.error("Unable to decrypt policy number", e);
		}
	}
	public  void decryptPolicyNumber(IContext ctx)
	{
		try
		{
			String policyKeyString=ctx.get("PolicyKey") != null && !HtmlConstants.EMPTY.equals(ctx.get("PolicyKey")) && !"SPACE".equals(ctx.get("PolicyKey")) ? ctx.get("PolicyKey").toString():"";
			if(policyKeyString.contains(" "))
				policyKeyString=policyKeyString.replace(' ','+');
			
			if(!"".equals(policyKeyString))
			{
				HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
		        HttpSession sess = req.getSession();
		        try
				{
					
					int policyKey=Integer.parseInt(ctx.get("PolicyKey").toString());
					ctx.put("PolicyKey",policyKey);
				}
				catch(Exception e)
				{
					ctx.put("PolicyKey", new LawyersValidationUtils().decrypt(policyKeyString));
				}
				
			}
			String pNumber=ctx.get("SubProducer")!=null && !ctx.get("SubProducer").equals(HtmlConstants.EMPTY) ?ctx.get("SubProducer").toString():"";
			pNumber = pNumber.trim();
			if(!"".equals(pNumber) && !"SPACE".equalsIgnoreCase(pNumber))
			{
				HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
		        HttpSession sess = req.getSession();
		      
		        String decryptPnumber=new LawyersValidationUtils().decrypt(pNumber);
				ctx.put("SubProducer",decryptPnumber );
					}
			else
			{
				// Direct application links do not include a sub-producer.  The JSP
				// uses SPACE as its placeholder; do not try to decrypt that marker.
				ctx.put("SubProducer", "");
			}
		}
		catch(IllegalArgumentException e)
		{
			ctx.put("PolicyKey", 0);
			ctx.put("SubProducer", "");
			populateError(ctx, "ApplicationLink", "Invalid or expired application link.");
			logger.warn("Rejected invalid application link.");
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
	}
	public static String[] getTokens(String str, String delim)
	{
	    StringTokenizer stok = new StringTokenizer(str, delim);
	    String tokens[] = new String[stok.countTokens()];
	    for(int i=0; i<tokens.length; i++){
	        tokens[i] = stok.nextToken();
	        tokens[i] = tokens[i].trim();
	    }
	    return tokens;
	}
	
	/*public static String readXML(String xml, String rootNode, String nodeKay){
		try{
			InputSource source = new InputSource(new StringReader(xml));
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		    DocumentBuilder db = dbf.newDocumentBuilder();
		    Document document = db.parse(source);
		
		    NodeList flowList = document.getElementsByTagName(rootNode);
		    
		    for (int nodes = 0; nodes < flowList.getLength(); nodes++) {
		    	
		    	NodeList childList = flowList.item(nodes).getChildNodes();
            	
            	for (int innerNodes = 0; innerNodes < childList.getLength(); innerNodes++) {
            		
            		NodeList innerChildList = childList.item(innerNodes).getChildNodes();
            		
            		for (int innerInnerNodes = 0; innerInnerNodes < innerChildList.getLength(); innerInnerNodes++) {

        		    	if(nodeKay.equals(innerChildList.item(innerInnerNodes).getNodeName())){
        		    		
        		    		String nodeValue = innerChildList.item(innerInnerNodes).getTextContent();
        		    		return nodeValue;
        		    	}
                	}
            	}
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}*/
	
	public static void lawyersCantSeeQuote(IContext ctx) {
		logger.debug("going to debug lawyersCantSeeQuote function : ");
		
		try {
			boolean isSeeQuoteReferralTriggered = false;
		/*	int policyStatusKey = ctx.get("PolicyStatusKey") != null && !ctx.get("PolicyStatusKey").equals(HtmlConstants.EMPTY) ? Integer.parseInt(ctx.get("PolicyStatusKey").toString()) : 0;
			if (policyStatusKey == 2) {
				logger.debug("lawyersCantSeeQuote policyStatusKey : " + ctx.get("PolicyStatusKey"));
				List getPolicyKey = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey", ctx);
				Map policyData = (Map)getPolicyKey.get(0);
				Context oldPolicyContext = new Context();
				oldPolicyContext.setProject(ctx.getProject());
				oldPolicyContext.put("PolicyKey", policyData.get("PreviousPolicykey"));
				oldPolicyContext.put("VersionSequence", policyData.get("previousVersionSequence"));
				oldPolicyContext.put("IsThisOptionFinalisedQuote", "Y");
				oldPolicyContext.put("bankruptcyCasesKey", 1);
				ctx.put("IsThisOptionFinalisedQuote", "Y");
				ctx.put("bankruptcyCasesKey", 1);
				
				List policyAddressList = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyAddressData", ctx);
				String rbStateCodeList = (String)((Map)policyAddressList.get(0)).get("StateCode");
				List nbPolicyTransactionList = (List)SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementManualBoQueriesgetNBPolicyTransactionList", oldPolicyContext);
				List rbPolicyTransactionList = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetRBPolicyTransactionList", ctx);
				
				Map nbPolicyTransactionData = null;
				Map rbPolicyTransactionData = null;
				if(nbPolicyTransactionList != null && nbPolicyTransactionList.size() > 0){
					nbPolicyTransactionData = (Map)nbPolicyTransactionList.get(0);
				}
				if(rbPolicyTransactionList != null && rbPolicyTransactionList.size() > 0){
					rbPolicyTransactionData = (Map)rbPolicyTransactionList.get(0);
				}
				
				if(nbPolicyTransactionData != null){
					String producerCode = (nbPolicyTransactionData.get("ProducerCode") == null ? null : (String)nbPolicyTransactionData.get("ProducerCode")); 
					if((producerCode == null || "".equals(producerCode)) && !("VA".equals(rbStateCodeList) || "MA".equals(rbStateCodeList) 
							|| "MO".equals(rbStateCodeList) || "NE".equals(rbStateCodeList) || "CA".equals(rbStateCodeList) || "MI".equals(rbStateCodeList)
							|| "TX".equals(rbStateCodeList) || "CO".equals(rbStateCodeList) || "HI".equals(rbStateCodeList) || "FL".equals(rbStateCodeList)
							|| "GA".equals(rbStateCodeList) || "IL".equals(rbStateCodeList) || "LA".equals(rbStateCodeList) || "NM".equals(rbStateCodeList)
							|| "WV".equals(rbStateCodeList))){
						
						List nbPolicyAddressData = (List)SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementManualBoQueriesgetPolicyAddressData", oldPolicyContext);
						List rbPolicyAddressData = policyAddressList;
						List nbPolicyFirmLWData = (List)SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementManualBoQueriesgetPolicyFirmLWData", oldPolicyContext);
						List rbPolicyFirmLWData = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyFirmLWData", ctx);
						List nbPolicyAttorniesData = (List)SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementdroolQueriesgetAllAttorneys", oldPolicyContext);
						List rbPolicyAttorniesData = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetAllAttorneys", ctx);
						List nbPolicyAopData = (List)SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementManualBoQueriesgetPolicyAOPList", oldPolicyContext);
						List rbPolicyAopData = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyAOPList", ctx);
						List nbPolicyBankruptcySuppData = (List)SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementManualBoQueriesgetPolicyBankruptcySupList", oldPolicyContext);
						List rbPolicyBankruptcySuppData = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyBankruptcySupList", ctx);
						List nbPolicyBankruptcyCasesSuppData = (List)SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementManualBoQueriesgetPolicyBankruptcyCasesSupList", oldPolicyContext);
						List rbPolicyBankruptcyCasesSuppData = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyBankruptcyCasesSupList", ctx);
						List nbPolicyCollectionSuppData = (List)SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementManualBoQueriesgetPolicyCollectionsSupList", oldPolicyContext);
						List rbPolicyCollectionSuppData = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyCollectionsSupList", ctx);
						List nbPolicyCopyRightTrademarkSuppData = (List)SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementManualBoQueriesgetPolicyCopyRightTrademarkSupList", oldPolicyContext);
						List rbPolicyCopyRightTrademarkSuppData = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyCopyRightTrademarkSupList", ctx);
						List nbPolicyFamilyLawSuppData = (List)SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementManualBoQueriesgetPolicyFamilySupList", oldPolicyContext);
						List rbPolicyFamilyLawSuppData = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyFamilySupList", ctx);
						List nbPolicyGovernSupData = (List)SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementManualBoQueriesgetPolicyGovernSupList", oldPolicyContext);
						List rbPolicyGovernSupData = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyGovernSupList", ctx);
						List nbPolicyPlantiffSuppData = (List)SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementManualBoQueriesgetPolicyPlantiffSupList", oldPolicyContext);
						List rbPolicyPlantiffSuppData = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyPlantiffSupList", ctx);
						List nbRealEstateSuppData = (List)SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementManualBoQueriesgetPolicyRealEastateSupList", oldPolicyContext);
						List rbRealEstateSuppData = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyRealEastateSupList", ctx);
						List nbAOPRealEstateSuppData = (List)SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementManualBoQueriesgetPolicyAOPRealEastateSupList", oldPolicyContext);
						List rbAOPRealEstateSuppData = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyAOPRealEastateSupList", ctx);
						List nbPolicyTaxSuppData = (List)SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementManualBoQueriesgetPolicyTaxSupList", oldPolicyContext);
						List rbPolicyTaxSuppData = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyTaxSupList", ctx);
						List nbPolicyCoverageData = (List)SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementManualBoQueriesgetPolicyCoverageData", oldPolicyContext);
						List getPolicyData = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyData", ctx);
						Object obj = SqlResources.getSqlMapProcessor(oldPolicyContext).findByKey("SqlStmts.UserStatementManualBoQueriesgetFinalizedQuoteData", oldPolicyContext);
						if(obj != null && obj instanceof Map)
						{
							oldPolicyContext.putAll((Map)obj);
						}
						oldPolicyContext.remove("IsThisOptionFinalisedQuote");
						oldPolicyContext.remove("bankruptcyCasesKey");
						ctx.remove("IsThisOptionFinalisedQuote");
						ctx.remove("bankruptcyCasesKey");
						int allAttorniesCount = rbPolicyAttorniesData.size();
						Map nbPolicyCoverageMap = (Map)nbPolicyCoverageData.get(0);
						if("Y".equals(nbPolicyCoverageMap.get("IsManualPremium")) ){
							isSeeQuoteReferralTriggered = true;
						}
						int totalModifiers=nbPolicyCoverageMap.get("TotalCovModifierPercentage")!=null ?Integer.parseInt(nbPolicyCoverageMap.get("TotalCovModifierPercentage").toString()):0;
						
						if(totalModifiers<0)
						{
							if(totalModifiers>=-25 && totalModifiers<=-11)
								isSeeQuoteReferralTriggered = true;
						}
						else
						{
							if(totalModifiers>=11 && totalModifiers<=25)
								isSeeQuoteReferralTriggered = true;
						}
						double nbTotalPremium = (nbPolicyTransactionData.get("TotalPremium") == null ? 0 : Double.parseDouble(nbPolicyTransactionData.get("TotalPremium").toString()));
						double rbTotalPremium = 0;
						if(rbPolicyTransactionData != null){
							rbTotalPremium = (rbPolicyTransactionData.get("TotalPremium") == null ? 0 : Double.parseDouble(rbPolicyTransactionData.get("TotalPremium").toString()));
						}
						
						if(ctx.get("TotalPremium") != null && rbTotalPremium == 0){
//							Map ratingMapValues = (Map) ctx.get("ratingMapValues");
//							String ratingOutputXML = ratingMapValues.get("OutputXML").toString();
							
							rbTotalPremium = Double.parseDouble(ctx.get("TotalPremium").toString());
						}
						
						double premuimDifference=Math.abs(rbTotalPremium - nbTotalPremium);
						double tenPercent=nbTotalPremium * .1;
						if((rbTotalPremium > 5000) || rbTotalPremium < (nbTotalPremium - tenPercent) || rbTotalPremium > (nbTotalPremium + tenPercent)){
							isSeeQuoteReferralTriggered = true;
						}
						
						Map nbPolicyCoverageList = (Map)nbPolicyCoverageData.get(0);
						
						int aggregateLimit  = (Integer)nbPolicyCoverageList.get("AggregateLimit");
						int occuranceLimit = (Integer)nbPolicyCoverageList.get("OccuranceLimit");
						//1,000,000/1,000,000 limit check
						if(aggregateLimit > 1000000 || occuranceLimit > 1000000){
							isSeeQuoteReferralTriggered = true;
						}
						
						Map nbPolicyFirmLWObj = (Map)nbPolicyFirmLWData.get(0);
						if(!"Y".equals(nbPolicyFirmLWObj.get("IsFirmHaveLawyersLiabilityInsurance")) && getPolicyData.size() <= 2){
							isSeeQuoteReferralTriggered = true;
						}
						
						//FirmNewApp
						Map nbPolicyAddressList = (Map)nbPolicyAddressData.get(0);
						Map rbPolicyAddressList = (Map)rbPolicyAddressData.get(0);
						
						String address1 = rbPolicyAddressList.get("Address1") != null ? rbPolicyAddressList.get("Address1").toString().trim().toLowerCase() : "";
						String[] poPatterns = getTokens(ctx.get("poBoxNamePatterns").toString().trim().toLowerCase(), ",");
						for (int i = 0; i < poPatterns.length; i++) {
							if (address1.contains(poPatterns[i])) {
								isSeeQuoteReferralTriggered = true;
								break;
							}
						}
		
						String nbStateCode = (String) nbPolicyAddressList.get("StateCode");
						if (isSeeQuoteReferralTriggered == false && !nbStateCode.equals(rbPolicyAddressList.get("StateCode"))) {
							isSeeQuoteReferralTriggered = true;
						}
	
						//Attorneys
						if(allAttorniesCount <= 2){
							if (allAttorniesCount == 1) {
								for (int attornieCount = 0; attornieCount < nbPolicyAttorniesData.size(); attornieCount++) {
									Map nbPolicyAttorniesList = (Map)nbPolicyAttorniesData.get(attornieCount);
									Map rbPolicyAttorniesList = (Map)rbPolicyAttorniesData.get(0);
			
									if (rbPolicyAttorniesList.get("AttorneyName").toString().equals(nbPolicyAttorniesList.get("AttorneyName").toString())
										 & rbPolicyAttorniesList.get("LicensedStates").toString().equals(nbPolicyAttorniesList.get("LicensedStates").toString())
										 & rbPolicyAttorniesList.get("AttorneyPriorActDate").toString().equals(nbPolicyAttorniesList.get("AttorneyPriorActDate").toString())) {
										if (Integer.parseInt(rbPolicyAttorniesList.get("AnnualWorkedHours").toString()) < 500) {
											if (isSeeQuoteReferralTriggered == false && !rbPolicyAttorniesList.get("AnnualWorkedHours").toString().equals(nbPolicyAttorniesList.get("AnnualWorkedHours").toString())) {
												isSeeQuoteReferralTriggered = true;
												break;
											}
										}
									}
								}
							}
							for (int attornieCount = 0; attornieCount < allAttorniesCount; attornieCount++) {
								Map rbPolicyAttorniesList = (Map)rbPolicyAttorniesData.get(attornieCount);
								if (isSeeQuoteReferralTriggered == false && !rbPolicyAttorniesList.get("LicensedStates").toString().contains(rbStateCodeList)) {
									isSeeQuoteReferralTriggered = true;
								}
							}
						} else {
							isSeeQuoteReferralTriggered = true;
						}
	
						if(rbPolicyAopData.size() > 10){
							isSeeQuoteReferralTriggered = true;
						}
						//lawPractice AOP
						int collectionAOPPercentageValue = 0;
						int bankruptcyAOPPercentageValue = 0;
						int realEstateAOPPercentageValueResi = 0;
						int realEstateAOPPercentageValueComm = 0;
						for (int aopCount = 0; aopCount < nbPolicyAopData.size(); aopCount++) {
							Map nbPolicyAopList = (Map)nbPolicyAopData.get(aopCount);
							Map rbPolicyAopList = (Map)rbPolicyAopData.get(aopCount);
							
							if ("3".equals(rbPolicyAopList.get("AOPKey").toString())) {
								bankruptcyAOPPercentageValue = Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString());
							}
							if ("6".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && Math.abs(Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyAopList.get("PercentageValue").toString())) > 5) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if ("10".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && Math.abs(Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyAopList.get("PercentageValue").toString())) > 0) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if ("12".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && Math.abs(Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString())) > 0) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if ("13".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && Math.abs(Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyAopList.get("PercentageValue").toString())) > 10) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if ("15".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && Math.abs(Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyAopList.get("PercentageValue").toString())) > 0) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if ("21".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && Math.abs(Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyAopList.get("PercentageValue").toString())) > 0) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if ("20".equals(rbPolicyAopList.get("AOPKey").toString())) {
								realEstateAOPPercentageValueComm = Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString());
							}
							if ("27".equals(rbPolicyAopList.get("AOPKey").toString())) {
								realEstateAOPPercentageValueResi = Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString());
							}
							if ("31".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && Math.abs(Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyAopList.get("PercentageValue").toString())) > 20) {
									isSeeQuoteReferralTriggered = true;
								}
								collectionAOPPercentageValue = Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString());
							}
							if ("38".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && Math.abs(Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyAopList.get("PercentageValue").toString())) > 0) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if ("42".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && Math.abs(Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyAopList.get("PercentageValue").toString())) > 0) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if ("46".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && Math.abs(Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString())) > 0) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if ("48".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && Math.abs(Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString())) > 0) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if ("59".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && (Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyAopList.get("PercentageValue").toString())) > 0 || Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyAopList.get("PercentageValue").toString()) < 0) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if ("61".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && Math.abs(Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyAopList.get("PercentageValue").toString())) > 0) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if ("62".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && Math.abs(Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyAopList.get("PercentageValue").toString())) > 10) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if ("63".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && Math.abs(Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyAopList.get("PercentageValue").toString())) > 0) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if ("67".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (nbPolicyAopList.get("AOPCommentDesc") != null && rbPolicyAopList.get("AOPCommentDesc") != null) {
									if (isSeeQuoteReferralTriggered == false && !rbPolicyAopList.get("AOPCommentDesc").toString().equals(nbPolicyAopList.get("AOPCommentDesc").toString())) {
										isSeeQuoteReferralTriggered = true;
									}
								}
								else if(oldAopData.get("AOPCommentDesc") == null && newAopData.get("AOPCommentDesc") != null){
								isSeeQuoteReferralTriggered=true;
								} else if(oldAopData.get("AOPCommentDesc") != null && newAopData.get("AOPCommentDesc") == null){
								isSeeQuoteReferralTriggered=true;
								}
							}
							if ("68".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && Math.abs(Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyAopList.get("PercentageValue").toString())) > 0) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if ("69".equals(rbPolicyAopList.get("AOPKey").toString())) {
								if (isSeeQuoteReferralTriggered == false && Math.abs(Integer.parseInt(rbPolicyAopList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyAopList.get("PercentageValue").toString())) > 0) {
									isSeeQuoteReferralTriggered = true;
								}
							}
						}
	
						//Firm Info
						//pastClaims
						Map nbPolicyFirmLWList = (Map)nbPolicyFirmLWData.get(0);
						Map rbPolicyFirmLWList = (Map)rbPolicyFirmLWData.get(0);
						if (isSeeQuoteReferralTriggered == false && "Y".equals(rbPolicyFirmLWList.get("IsPersonnelBeSubOfAnyInvest")) || "Y".equals(rbPolicyFirmLWList.get("IsLawyerProfLiabClaimAgntAppl")) || "Y".equals(rbPolicyFirmLWList.get("IsAnyActOmmBecomeClaimAgntFirm"))) {
							isSeeQuoteReferralTriggered = true;
						}
					
						//AboutFirmRenewal
						if(isSeeQuoteReferralTriggered == false && "6".equals(rbPolicyFirmLWList.get("AnnualRevenueSequence"))){
							isSeeQuoteReferralTriggered = true;
						} else {
							if(isSeeQuoteReferralTriggered == false && (Integer.parseInt(rbPolicyFirmLWList.get("AnnualRevenueSequence").toString()) - Integer.parseInt(nbPolicyFirmLWList.get("AnnualRevenueSequence").toString())) > 2){
								isSeeQuoteReferralTriggered = true;
							}
						}
						
						if (isSeeQuoteReferralTriggered == false && "Y".equals(rbPolicyFirmLWList.get("IsFirmMergedWithOtherFirm"))) {
							isSeeQuoteReferralTriggered = true;
						}
						
						if (isSeeQuoteReferralTriggered == false && "Y".equals(rbPolicyFirmLWList.get("isServedAsCEOChairmanPresident"))) {
							isSeeQuoteReferralTriggered = true;
						}
		
						String isFirmHaveClientMoreThan25PercentOfBilling = (String)nbPolicyFirmLWList.get("IsFirmHaveClientMoreThan25PercentOfBilling");
						if (isSeeQuoteReferralTriggered == false && "Y".equals(rbPolicyFirmLWList.get("IsFirmHaveClientMoreThan25PercentOfBilling")) && "N".equals(isFirmHaveClientMoreThan25PercentOfBilling)) {
							isSeeQuoteReferralTriggered = true;
						}
	
						//Supplements
						//bankruptcySupplement
						if(rbPolicyBankruptcySuppData != null && rbPolicyBankruptcySuppData.size() > 0){
							Map rbPolicyBankruptcySuppList = (Map)rbPolicyBankruptcySuppData.get(0);
							Map rbPolicyBankruptcyCasesSuppList = (Map)rbPolicyBankruptcyCasesSuppData.get(0);
							if (nbPolicyBankruptcySuppData != null && nbPolicyBankruptcySuppData.size() > 0) {
								Map nbPolicyBankruptcySuppList = (Map)nbPolicyBankruptcySuppData.get(0);
								Map nbPolicyBankruptcyCasesSuppList = (Map)nbPolicyBankruptcyCasesSuppData.get(0);
								
								if (isSeeQuoteReferralTriggered == false && "N".equals(nbPolicyBankruptcySuppList.get("representedDebtors")) && "Y".equals(rbPolicyBankruptcySuppList.get("representedDebtors"))) {
									isSeeQuoteReferralTriggered = true;
								}
								if (isSeeQuoteReferralTriggered == false && "N".equals(nbPolicyBankruptcySuppList.get("preBankruptcyServices")) && "Y".equals(rbPolicyBankruptcySuppList.get("preBankruptcyServices"))) {
									isSeeQuoteReferralTriggered = true;
								}
								if (isSeeQuoteReferralTriggered == false && "Y".equals(nbPolicyBankruptcySuppList.get("disclosureStatementExplaining")) && "N".equals(rbPolicyBankruptcySuppList.get("disclosureStatementExplaining"))) {
									isSeeQuoteReferralTriggered = true;
								} else if (isSeeQuoteReferralTriggered == false && "N".equals(nbPolicyBankruptcySuppList.get("disclosureStatementExplaining")) && "Y".equals(rbPolicyBankruptcySuppList.get("disclosureStatementExplaining"))) {
									isSeeQuoteReferralTriggered = true;
								}
								if (isSeeQuoteReferralTriggered == false && "Y".equals(nbPolicyBankruptcySuppList.get("conspicuousStatement")) && "N".equals(rbPolicyBankruptcySuppList.get("conspicuousStatement"))) {
									isSeeQuoteReferralTriggered = true;
								} else if (isSeeQuoteReferralTriggered == false && "N".equals(nbPolicyBankruptcySuppList.get("conspicuousStatement")) && "Y".equals(rbPolicyBankruptcySuppList.get("conspicuousStatement"))) {
									isSeeQuoteReferralTriggered = true;
								}
							} else {
								if (isSeeQuoteReferralTriggered == false && (Integer)rbPolicyBankruptcyCasesSuppList.get("Debtor") > (2 * bankruptcyAOPPercentageValue)) {
									isSeeQuoteReferralTriggered = true;
								}
								if (isSeeQuoteReferralTriggered == false && (Integer)rbPolicyBankruptcyCasesSuppList.get("Creditor") > (2 * bankruptcyAOPPercentageValue)) {
									isSeeQuoteReferralTriggered = true;
								}
								if (isSeeQuoteReferralTriggered == false && (Integer)rbPolicyBankruptcyCasesSuppList.get("Trustee") > (2 * bankruptcyAOPPercentageValue)) {
									isSeeQuoteReferralTriggered = true;
								}
								if (isSeeQuoteReferralTriggered == false && "N".equals(rbPolicyBankruptcySuppList.get("disclosureStatementExplaining"))) {
									isSeeQuoteReferralTriggered = true;
								}
								if (isSeeQuoteReferralTriggered == false && "N".equals(rbPolicyBankruptcySuppList.get("conspicuousStatement"))) {
									isSeeQuoteReferralTriggered = true;
								}
							}
						}
						
						//collectionSupplement
						if(rbPolicyCollectionSuppData != null && rbPolicyCollectionSuppData.size() > 0){
							Map rbPolicyCollectionSuppList = (Map)rbPolicyCollectionSuppData.get(0);
							if (nbPolicyCollectionSuppData != null && nbPolicyCollectionSuppData.size() > 0) {
								Map nbPolicyCollectionSuppList = (Map)nbPolicyCollectionSuppData.get(0);
								
								if(!nbPolicyCollectionSuppList.get("averageCases").toString().equals(rbPolicyCollectionSuppList.get("averageCases"))){
									if (isSeeQuoteReferralTriggered == false && (Integer)rbPolicyCollectionSuppList.get("averageCases") > (1.5 * collectionAOPPercentageValue)) {
										isSeeQuoteReferralTriggered = true;
									}
								}
								if(!nbPolicyCollectionSuppList.get("consumerCollections").toString().equals(rbPolicyCollectionSuppList.get("consumerCollections"))){
									if (isSeeQuoteReferralTriggered == false && (Integer)rbPolicyCollectionSuppList.get("consumerCollections") > (.2 * collectionAOPPercentageValue)) {
										isSeeQuoteReferralTriggered = true;
									}
								}
								if (isSeeQuoteReferralTriggered == false && (Integer)nbPolicyCollectionSuppList.get("individualCollections") != (Integer)rbPolicyCollectionSuppList.get("individualCollections")) {
									isSeeQuoteReferralTriggered = true;
								}
								if (isSeeQuoteReferralTriggered == false && "N".equals(nbPolicyCollectionSuppList.get("personnelToCollectDebts")) && "Y".equals(rbPolicyCollectionSuppList.get("personnelToCollectDebts"))) {
									isSeeQuoteReferralTriggered = true;
								}
								if (isSeeQuoteReferralTriggered == false && "N".equals(nbPolicyCollectionSuppList.get("servicesToPurchasers")) && "Y".equals(rbPolicyCollectionSuppList.get("servicesToPurchasers"))) {
									isSeeQuoteReferralTriggered = true;
								}
								if (isSeeQuoteReferralTriggered == false && "N".equals(nbPolicyCollectionSuppList.get("claimsOrSuitsFDCPA")) && "Y".equals(rbPolicyCollectionSuppList.get("claimsOrSuitsFDCPA"))) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if (isSeeQuoteReferralTriggered == false && (Integer)rbPolicyCollectionSuppList.get("individualCollections") > 25000) {
								isSeeQuoteReferralTriggered = true;
							}
							if (isSeeQuoteReferralTriggered == false && (Integer)rbPolicyCollectionSuppList.get("consumerCollections") > 20) {
								isSeeQuoteReferralTriggered = true;
							}
						}
					
						//copyRightTrademarkSupplement
						if (nbPolicyCopyRightTrademarkSuppData != null && nbPolicyCopyRightTrademarkSuppData.size() > 0 && rbPolicyCopyRightTrademarkSuppData != null && rbPolicyCopyRightTrademarkSuppData.size() > 0) {
							Map nbPolicyCopyRightTrademarkSuppList = (Map)nbPolicyCopyRightTrademarkSuppData.get(0);
							Map rbPolicyCopyRightTrademarkSuppList = (Map)rbPolicyCopyRightTrademarkSuppData.get(0);
							
							if (isSeeQuoteReferralTriggered == false && "N".equals(nbPolicyCopyRightTrademarkSuppList.get("otherServices")) && "Y".equals(rbPolicyCopyRightTrademarkSuppList.get("otherServices"))) {
								isSeeQuoteReferralTriggered = true;
							}
						}
	
						//familylawsupplement
						if (nbPolicyFamilyLawSuppData != null && nbPolicyFamilyLawSuppData.size() > 0 && rbPolicyFamilyLawSuppData != null && rbPolicyFamilyLawSuppData.size() > 0) {
							for (int familyCount = 0; familyCount < nbPolicyFamilyLawSuppData.size(); familyCount++) {
								Map nbPolicyFamilyLawSuppList = (Map)nbPolicyFamilyLawSuppData.get(familyCount);
								Map rbPolicyFamilyLawSuppList = (Map)rbPolicyFamilyLawSuppData.get(familyCount);
		
								if ("2".equals(nbPolicyFamilyLawSuppList.get("FLAOPKey").toString()) && "2".equals(rbPolicyFamilyLawSuppList.get("FLAOPKey").toString())) {
									if (isSeeQuoteReferralTriggered == false && Integer.parseInt(rbPolicyFamilyLawSuppList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyFamilyLawSuppList.get("PercentageValue").toString()) > 0) {
										isSeeQuoteReferralTriggered = true;
									}
								}
								if ("5".equals(nbPolicyFamilyLawSuppList.get("FLAOPKey").toString()) && "5".equals(rbPolicyFamilyLawSuppList.get("FLAOPKey").toString())) {
									if (isSeeQuoteReferralTriggered == false && Integer.parseInt(rbPolicyFamilyLawSuppList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyFamilyLawSuppList.get("PercentageValue").toString()) > 10) {
										isSeeQuoteReferralTriggered = true;
									}
								}
								if ("6".equals(nbPolicyFamilyLawSuppList.get("FLAOPKey").toString()) && "6".equals(rbPolicyFamilyLawSuppList.get("FLAOPKey").toString())) {
									if (isSeeQuoteReferralTriggered == false && Integer.parseInt(rbPolicyFamilyLawSuppList.get("PercentageValue").toString()) - Integer.parseInt(nbPolicyFamilyLawSuppList.get("PercentageValue").toString()) > 0) {
										isSeeQuoteReferralTriggered = true;
									}
								}
							}
						}
	
						//GovernmentSupplement
						if (nbPolicyGovernSupData != null && nbPolicyGovernSupData.size() > 0 && rbPolicyGovernSupData != null && rbPolicyGovernSupData.size() > 0) {
							for (int governCount = 0; governCount < nbPolicyGovernSupData.size(); governCount++) {
								Map nbPolicyGovernSupList = (Map)nbPolicyGovernSupData.get(governCount);
								Map rbPolicyGovernSupList = (Map)rbPolicyGovernSupData.get(governCount);
								if (isSeeQuoteReferralTriggered == false && "N".equals(nbPolicyGovernSupList.get("providingBondWork")) && "Y".equals(rbPolicyGovernSupList.get("providingBondWork"))) {
									isSeeQuoteReferralTriggered = true;
								}
								if (isSeeQuoteReferralTriggered == false && "N".equals(nbPolicyGovernSupList.get("eminentDomainServices")) && "Y".equals(rbPolicyGovernSupList.get("eminentDomainServices"))) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							} else {
								if (isSeeQuoteReferralTriggered == false && "Y".equals(rbPolicyGovernSupList.get("providingBondWork"))) {
									isSeeQuoteReferralTriggered = true;
								}
								if (isSeeQuoteReferralTriggered == false && "Y".equals(rbPolicyGovernSupList.get("eminentDomainServices"))) {
									isSeeQuoteReferralTriggered = true;
								}
						}
	
						//litigationSupplement
						if (rbPolicyPlantiffSuppData != null && rbPolicyPlantiffSuppData.size() > 0) {
							Map rbPolicyPlantiffSuppList = (Map)rbPolicyPlantiffSuppData.get(0);
							if (nbPolicyPlantiffSuppData != null && nbPolicyPlantiffSuppData.size() > 0) {
								for (int plantiffCount = 0; plantiffCount < nbPolicyPlantiffSuppData.size(); plantiffCount++) {
									Map nbPolicyPlantiffSuppList = (Map)nbPolicyPlantiffSuppData.get(plantiffCount);
									rbPolicyPlantiffSuppList = (Map)rbPolicyPlantiffSuppData.get(plantiffCount);
									
									if (isSeeQuoteReferralTriggered == false && (Integer)nbPolicyPlantiffSuppList.get("NumberOfInjuryCasesIn12Month") < (Integer)rbPolicyPlantiffSuppList.get("NumberOfInjuryCasesIn12Month")){
										int totalNumberOfPlaintiff = ((Integer)rbPolicyPlantiffSuppList.get("NumberOfInjuryCasesIn12Month"))/allAttorniesCount;
										if(totalNumberOfPlaintiff > 100){
											isSeeQuoteReferralTriggered = true;
										}
									}
									if (isSeeQuoteReferralTriggered == false && "N".equals(nbPolicyPlantiffSuppList.get("IsSettlementAggrementsUsed")) && "Y".equals(rbPolicyPlantiffSuppList.get("IsSettlementAggrementsUsed"))) {
										isSeeQuoteReferralTriggered = true;
									}
									
								}
							}
							if (isSeeQuoteReferralTriggered == false && "Y".equals(rbPolicyPlantiffSuppList.get("IsSettlementAggrementsUsed"))) {
								isSeeQuoteReferralTriggered = true;
							}
							if (isSeeQuoteReferralTriggered == false && (Integer)rbPolicyPlantiffSuppList.get("PerOfCasesSettledBeforeTrail") < 80) {
								isSeeQuoteReferralTriggered = true;
							}
							if (isSeeQuoteReferralTriggered == false && "Y".equals(rbPolicyPlantiffSuppList.get("massTortOrClassAction"))) {
								isSeeQuoteReferralTriggered = true;
							}
						}
						
						//RealEstateSupplement
						if (rbRealEstateSuppData != null && rbRealEstateSuppData.size() > 0) {
							Map rbRealEstateSuppList = (Map)rbRealEstateSuppData.get(0);
							if (nbRealEstateSuppData != null && nbRealEstateSuppData.size() > 0) {
								Map nbRealEstateSuppList = (Map)nbRealEstateSuppData.get(0);
								
								//pending
								int titleOpinionsPercentageValueResi = 0;
								int purchaseAndSalePercentageValueResi = 0;
								int titleOpinionsPercentageValueComm = 0;
								int purchaseAndSalePercentageValueComm = 0;
								for(int rbAOPRealEstateCount = 0; rbAOPRealEstateCount < rbAOPRealEstateSuppData.size(); rbAOPRealEstateCount++){
									Map rbAOPRealEstateSuppList = (Map)rbAOPRealEstateSuppData.get(rbAOPRealEstateCount);
									Map nbAOPRealEstateSuppList = (Map)nbAOPRealEstateSuppData.get(rbAOPRealEstateCount);
									int rbPercentageValueResi = rbAOPRealEstateSuppList.get("PercentageValue") == null ? 0 : (Integer)rbAOPRealEstateSuppList.get("PercentageValue");
									int nbPercentageValueResi = nbAOPRealEstateSuppList.get("PercentageValue") == null ? 0 : (Integer)nbAOPRealEstateSuppList.get("PercentageValue");
									int rbPercentageValueComm = rbAOPRealEstateSuppList.get("CommercialPercentageValue") == null ? 0 : (Integer)rbAOPRealEstateSuppList.get("CommercialPercentageValue");
									int nbPercentageValueComm = nbAOPRealEstateSuppList.get("CommercialPercentageValue") == null ? 0 : (Integer)nbAOPRealEstateSuppList.get("CommercialPercentageValue");
									if ("1".equals(rbAOPRealEstateSuppList.get("AOPREKey"))) {
										purchaseAndSalePercentageValueResi = rbPercentageValueResi;
									}
									if ("2".equals(rbAOPRealEstateSuppList.get("AOPREKey"))) {
										purchaseAndSalePercentageValueComm = rbPercentageValueComm;
									}
									if ("22".equals(rbAOPRealEstateSuppList.get("AOPREKey"))) {
										titleOpinionsPercentageValueResi = rbPercentageValueResi;
										titleOpinionsPercentageValueComm = rbPercentageValueComm;
									}
									if(isSeeQuoteReferralTriggered == false && purchaseAndSalePercentageValueResi < titleOpinionsPercentageValueResi){
										isSeeQuoteReferralTriggered = true;
									}
									if (isSeeQuoteReferralTriggered == false && ("22".equals(rbAOPRealEstateSuppList.get("AOPREKey"))) && ((rbPercentageValueResi - nbPercentageValueResi) > (nbPercentageValueResi * .15))) {
										isSeeQuoteReferralTriggered = true;
									}
									if (isSeeQuoteReferralTriggered == false && ("19".equals(rbAOPRealEstateSuppList.get("AOPREKey"))) && ((rbPercentageValueResi - nbPercentageValueResi) > (nbPercentageValueResi * .1))) {
										isSeeQuoteReferralTriggered = true;
									}
									if (isSeeQuoteReferralTriggered == false && ("13".equals(rbAOPRealEstateSuppList.get("AOPREKey"))) && (rbPercentageValueResi > nbPercentageValueResi)) {
										isSeeQuoteReferralTriggered = true;
									}
									if (isSeeQuoteReferralTriggered == false && ("16".equals(rbAOPRealEstateSuppList.get("AOPREKey"))) && (rbPercentageValueResi > nbPercentageValueResi)) {
										isSeeQuoteReferralTriggered = true;
									}
									if (isSeeQuoteReferralTriggered == false && ("20".equals(rbAOPRealEstateSuppList.get("AOPREKey"))) && (rbPercentageValueResi > nbPercentageValueResi)) {
										isSeeQuoteReferralTriggered = true;
									}
									if (isSeeQuoteReferralTriggered == false && ("20".equals(rbAOPRealEstateSuppList.get("AOPREKey"))) && (rbAOPRealEstateSuppList.get("AOPRECommentDesc").toString().equals(nbAOPRealEstateSuppList.get("AOPRECommentDesc")))) {
										isSeeQuoteReferralTriggered = true;
									}
									
									if(isSeeQuoteReferralTriggered == false && purchaseAndSalePercentageValueComm < titleOpinionsPercentageValueComm){
										isSeeQuoteReferralTriggered = true;
									}
									if (isSeeQuoteReferralTriggered == false && ("22".equals(rbAOPRealEstateSuppList.get("AOPREKey"))) && ((rbPercentageValueComm - nbPercentageValueComm) > (nbPercentageValueComm * .15))) {
										isSeeQuoteReferralTriggered = true;
									}
									if (isSeeQuoteReferralTriggered == false && ("19".equals(rbAOPRealEstateSuppList.get("AOPREKey"))) && ((rbPercentageValueComm - nbPercentageValueComm) > (nbPercentageValueComm * .1))) {
										isSeeQuoteReferralTriggered = true;
									}
									if (isSeeQuoteReferralTriggered == false && ("13".equals(rbAOPRealEstateSuppList.get("AOPREKey"))) && (rbPercentageValueComm > nbPercentageValueComm)) {
										isSeeQuoteReferralTriggered = true;
									}
									if (isSeeQuoteReferralTriggered == false && ("16".equals(rbAOPRealEstateSuppList.get("AOPREKey"))) && (rbPercentageValueComm > nbPercentageValueComm)) {
										isSeeQuoteReferralTriggered = true;
									}
									if (isSeeQuoteReferralTriggered == false && ("20".equals(rbAOPRealEstateSuppList.get("AOPREKey"))) && (rbPercentageValueComm > nbPercentageValueComm)) {
										isSeeQuoteReferralTriggered = true;
									}
									if (isSeeQuoteReferralTriggered == false && ("20".equals(rbAOPRealEstateSuppList.get("AOPREKey"))) && (rbAOPRealEstateSuppList.get("AOPRECommCommentDesc").toString().equals(nbAOPRealEstateSuppList.get("AOPRECommCommentDesc")))) {
										isSeeQuoteReferralTriggered = true;
									}
								}
								if (isSeeQuoteReferralTriggered == false && ("3".equals(nbRealEstateSuppList.get("useDisclosureForm")) || "Y".equals(nbRealEstateSuppList.get("useDisclosureForm"))) && "N".equals(rbRealEstateSuppList.get("useDisclosureForm"))) {
									isSeeQuoteReferralTriggered = true;
								}
								if (isSeeQuoteReferralTriggered == false && (Integer)rbRealEstateSuppList.get("transactionsHandled12MonthsResi") > (2 * realEstateAOPPercentageValueResi)) {
									isSeeQuoteReferralTriggered = true;
								}
								if (isSeeQuoteReferralTriggered == false && (Integer)rbRealEstateSuppList.get("transactionsHandled12MonthsComm") > (2 * realEstateAOPPercentageValueComm)) {
									isSeeQuoteReferralTriggered = true;
								}
							}
							if (isSeeQuoteReferralTriggered == false && "Y".equals(rbRealEstateSuppList.get("attendClosings"))) {
								isSeeQuoteReferralTriggered = true;
							}
							if (isSeeQuoteReferralTriggered == false && "Y".equals(rbRealEstateSuppList.get("includeSecuringFinancing"))) {
								isSeeQuoteReferralTriggered = true;
							}
						}
					
						//taxSupplement
						if (nbPolicyTaxSuppData != null && nbPolicyTaxSuppData.size() > 0 && rbPolicyTaxSuppData != null && rbPolicyTaxSuppData.size() > 0) {
							for (int taxCount = 0; taxCount < nbPolicyTaxSuppData.size(); taxCount++) {
								Map nbPolicyTaxSuppList = (Map)nbPolicyTaxSuppData.get(taxCount);
								Map rbPolicyTaxSuppList = (Map)rbPolicyTaxSuppData.get(taxCount);
								
								if ("4".equals(rbPolicyTaxSuppList.get("TaxAOPKey").toString()) && "4".equals(nbPolicyTaxSuppList.get("TaxAOPKey").toString())) {
									if (isSeeQuoteReferralTriggered == false && (Integer.parseInt(rbPolicyTaxSuppList.get("revenuePercentage").toString()) - Integer.parseInt(nbPolicyTaxSuppList.get("revenuePercentage").toString())) > 0) {
										isSeeQuoteReferralTriggered = true;
									}
								}
								if ("5".equals(rbPolicyTaxSuppList.get("TaxAOPKey").toString()) && "5".equals(nbPolicyTaxSuppList.get("TaxAOPKey").toString())) {
									if (isSeeQuoteReferralTriggered == false && (Integer.parseInt(rbPolicyTaxSuppList.get("revenuePercentage").toString()) - Integer.parseInt(nbPolicyTaxSuppList.get("revenuePercentage").toString())) > 0) {
										isSeeQuoteReferralTriggered = true;
									}
								}
								if ("6".equals(rbPolicyTaxSuppList.get("TaxAOPKey").toString()) && "6".equals(nbPolicyTaxSuppList.get("TaxAOPKey").toString())) {
									if (isSeeQuoteReferralTriggered == false && (Integer.parseInt(rbPolicyTaxSuppList.get("revenuePercentage").toString()) - Integer.parseInt(nbPolicyTaxSuppList.get("revenuePercentage").toString())) > 0) {
										isSeeQuoteReferralTriggered = true;
									}
								}
								if ("7".equals(rbPolicyTaxSuppList.get("TaxAOPKey").toString()) && "7".equals(nbPolicyTaxSuppList.get("TaxAOPKey").toString())) {
									if (isSeeQuoteReferralTriggered == false && (Integer.parseInt(rbPolicyTaxSuppList.get("revenuePercentage").toString()) - Integer.parseInt(nbPolicyTaxSuppList.get("revenuePercentage").toString())) > 0) {
										isSeeQuoteReferralTriggered = true;
									}
								}
								if ("8".equals(rbPolicyTaxSuppList.get("TaxAOPKey").toString()) && "8".equals(nbPolicyTaxSuppList.get("TaxAOPKey").toString())) {
									if (isSeeQuoteReferralTriggered == false && (Integer.parseInt(rbPolicyTaxSuppList.get("revenuePercentage").toString()) - Integer.parseInt(nbPolicyTaxSuppList.get("revenuePercentage").toString())) > 0) {
										isSeeQuoteReferralTriggered = true;
									}
								}
								if ("9".equals(rbPolicyTaxSuppList.get("TaxAOPKey").toString()) && "9".equals(nbPolicyTaxSuppList.get("TaxAOPKey").toString())) {
									if (isSeeQuoteReferralTriggered == false && (Integer.parseInt(rbPolicyTaxSuppList.get("revenuePercentage").toString()) - Integer.parseInt(nbPolicyTaxSuppList.get("revenuePercentage").toString())) > 0) {
										isSeeQuoteReferralTriggered = true;
									}
								}
								if ("9".equals(rbPolicyTaxSuppList.get("TaxAOPKey").toString()) && "9".equals(nbPolicyTaxSuppList.get("TaxAOPKey").toString())) {
									if (isSeeQuoteReferralTriggered == false && !rbPolicyTaxSuppList.get("TaxCommentDesc").toString().equals(nbPolicyTaxSuppList.get("TaxCommentDesc").toString())) {
										isSeeQuoteReferralTriggered = true;
									}
								}
							}
						}
						//WillsTrustProbationSupplment
	
						Map obj_code = (Map)SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetManualPremiumIssuedPolicyCount", ctx);
						if(obj_code != null){
							int isNotShowPremiumQuoteCount=obj_code.get("isNotShowPremiumQuoteCount")!=null?Integer.parseInt(obj_code.get("isNotShowPremiumQuoteCount").toString()):0;
						if(isNotShowPremiumQuoteCount>0)
							isSeeQuoteReferralTriggered = true;
						}
						
						obj_code = (Map)SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetReferralCountTriggered", ctx);
						if(obj_code != null){
							int referralCounts=obj_code.get("referralCounts")!=null?Integer.parseInt(obj_code.get("referralCounts").toString()):0;
							if(referralCounts>0)
								isSeeQuoteReferralTriggered = true;
						}
						//isSeeQuoteReferralTriggered=false;
						if (isSeeQuoteReferralTriggered){
							ctx.put("isSeeQuoteReferralTriggered", "N");
							ctx.put("IsCantSeeQuote", "N");
						} else {
							ctx.put("isSeeQuoteReferralTriggered", "Y");
							ctx.put("IsCantSeeQuote", "Y");
						}
						RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");
						int status = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateCantSeeQuoteReferralTriggered", ctx);
					}
				}
			}
		*/
		
		ctx.put("isSeeQuoteReferralTriggered", "N");
		ctx.put("IsCantSeeQuote", "N");
		
			RuleUtils.executeRule(ctx, "LawyersRule.AssignLastUpdateTimeStamp");
			int status = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateCantSeeQuoteReferralTriggered", ctx);
		
		} catch (Exception e) {
			logger.error("error in lawyersCantSeeQuote function " + e.getMessage());
			logger.error("Unexpected error", e);
		}
	}
	
	public static void lawyersShowSeeQuote(IContext ctx){
		logger.debug("going to debug lawyersShowSeeQuote function : ");
		try{
			ctx.put("StatusKey", "3");
			ctx.put("QuoteComment", "AutoQuote");
			RuleUtils.executeRule(ctx, "LawyersRule.fillQuoteDates");
			int status = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateSeeQuoteStatus", ctx);
			status = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateSeeQuoteUWFlags", ctx);
			status = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateFirmAutoQuoteDates", ctx);
			//Raghu
			//status = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateSeeQuoteUWFlags2", ctx);
			ctx.put("isPricingApproved", "Y");
			logger.debug(status);
		} catch (Exception e){
			logger.error("error in lawyersShowSeeQuote function " + e.getMessage());
			logger.error("Unexpected error", e);
		}
	}
	public static void validatePolicyExtensionDate(Context ctx)
	{
		try
		{
			if(ctx.get("policyExtendExpirationDate")!=null && !ctx.get("policyExtendExpirationDate").equals(HtmlConstants.EMPTY) )
			{
				new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyExpirationDate,policyExtendExpirationDate");
				List errorMsg =(List)SqlResources.getSqlMapProcessor(ctx).select("PolicyTransaction.PolicyExtentionDateValidationLW_p",ctx);
				if(errorMsg!=null && errorMsg.size()>0)
				ctx.putAll((Map) errorMsg.get(0));
				String errorMessage=!ctx.get("ErrorMessage").equals(HtmlConstants.EMPTY) && ctx.get("ErrorMessage")!=null?ctx.get("ErrorMessage").toString():"";
				if(!"NO ERROR".equals(errorMessage))
				{
					populateError(ctx, "policyExtendExpirationDateError",errorMessage);	
					return;
				}
			
			}
			else
				populateError(ctx, "policyExtendExpirationDateError","Please enter policy expiration date");
		}
		catch(Exception e)
		{
			logger.error("Unable to validate policy extension date", e);
		}
	}
	
	public static void validateStateNY(Context ctx)
	{
		logger.debug("going to validate State is NewYork ");
		try
		{
		if(ctx.get("StateCode")!=null && ctx.get("StateCode").equals("NY"))
		{
			populateError(ctx, "NewYorkStatePremiumError","Premium generation is not allowed for NewYork");
			
		}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
			logger.error("Error in indication while validation state code for NewYork State");
		}
		
	}
	
	public static void validateModifierForL2SP(Context ctx)
	{
		logger.debug("going to validate indication Modifier For L2 SP ");
		try
		{
			if("2".equals(ctx.get("role_id").toString()) || "3".equals(ctx.get("role_id").toString()) || "6".equals(ctx.get("role_id").toString())) {
				LawyersValidationUtils.validateModifiersStateWise(ctx, Integer.parseInt(ctx.get("SchduleRatingModifier1").toString()), "Schedlue modifier must exist between");
			}
			
			boolean isIndicationModifier = false;
			Object objIndicationModifier = RuleUtils.executeRule(ctx, "LawyersRule.validateRatingModifierIndicationForSubProducer");
			if (objIndicationModifier != null && objIndicationModifier instanceof Boolean) {
				isIndicationModifier = (Boolean) objIndicationModifier;
			}
			
			if (isIndicationModifier) {
				populateError(ctx, "SchduleRatingModifier1", "Schedlue modifier must exist between -10 and +10");
				return;
			}
			
			objIndicationModifier = RuleUtils.executeRule(ctx, "LawyersRule.validateRatingModifierIndicationForL2SubProducerState");
			if (objIndicationModifier != null && objIndicationModifier instanceof Boolean) {
				isIndicationModifier = (Boolean) objIndicationModifier;
			}

			if (isIndicationModifier) {
				populateError(ctx, "SchduleRatingModifier1", "Schedlue modifier must exist between -20 and +20");
				return;
			}
			
			objIndicationModifier = RuleUtils.executeRule(ctx, "LawyersRule.validateRatingModifierIndicationForL2SubProducer");
			if (objIndicationModifier != null && objIndicationModifier instanceof Boolean) {
				isIndicationModifier = (Boolean) objIndicationModifier;
			}
			
			if (isIndicationModifier) {
				populateError(ctx, "SchduleRatingModifier1", "Schedlue modifier must exist between -10 and +10");
			}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
			logger.error("Error in indication while validation Modifier For L2 SP");
		}
		
	}

	public static void validateSubproducerFormCompletion(Context ctx){
		

		try{
			boolean isAgent = false;
			Object objAgentRule = RuleUtils.executeRule(ctx, "LawyersRule.isSubProducerEditNotReadOnly");
			if (objAgentRule != null && objAgentRule instanceof Boolean) {
				isAgent = (Boolean) objAgentRule;
			}

			if (isAgent) {
				Object obj_code = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.SubproducerFormviewgetSubproducerformcode", ctx);
				if(obj_code != null){
					populateError(ctx, "ProducerCode", "Sub Producer Number can't be duplicate");
				}
			
			}
		}catch (Exception e) {
			logger.error("Unable to validate subproducer form completion", e);
			logger.error("Unexpected error", e);
		}
		
		if(ctx.get("fileItems") == null || !(ctx.get("fileItems") instanceof List))
			return;

		String signature_path="";
		String spLogo_Path="";
		
		try {
			List fileItems = (List)ctx.get("fileItems");
			Iterator i = fileItems.iterator();
			
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// filename on the client
					if("sig_path".equals(fi.getFieldName())){
						signature_path=fi.getName();
					}
					if("file_path".equals(fi.getFieldName())){
						spLogo_Path=fi.getName();
					}
				}
			}
			
			if("Y".equals(ctx.get("SubProducerAccess"))){
				if ((signature_path == null || "".equals(signature_path)) && (ctx.get("s_path") == null || "".equals(ctx.get("s_path")))){
					populateError(ctx, "sig_path", "Signature has not been selected");
				}
				if((spLogo_Path == null || "".equals(spLogo_Path)) && (ctx.get("path") == null || "".equals(ctx.get("path")))){
					populateError(ctx, "file_path", "Logo has not been selected");
				}
			}
		} catch (Exception e) {
			logger.error("Unable to process subproducer form completion", e);
			logger.error("Unexpected error", e);
		}
	}
	public static void financeMailToSubproducer(Context ctx)
	{
		try{
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
	    	String email_notification_event_name = ctx.get("event_name").toString();
	    	Context newCtx = new Context();
	    	newCtx.setProject(ctx.getProject());
	    	
	    	newCtx.put("UserNo", ctx.get("UserNo"));
	    	newCtx.put("PolicyKey", ctx.get("PolicyKey"));
	    	newCtx.put("event_name", email_notification_event_name);
	    	
	    	//going to get event description from database
	    	Object obj = SqlResources.getSqlMapProcessor(newCtx).select("SqlStmts.UserStatementdroolQueriesgetEmailNotificationData", newCtx);
	    	List list = (List)obj;
	    	Map map = (Map)list.get(0);
	    	 
			map.put("to_role_desc",ctx.get("Producer_email"));
			
	    	sendEmailNotification(ctx, map);
    	}catch (Exception e) {
    		logger.error("Unable to send email notification due to error : " + e.getMessage());
		}
		
	}
	
	public void sendCompleteAppLinkMailforSubProducer (IContext ctx){
		try{
		Object  obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetEndorsementAccountDetails", ctx);
		Object  objsp = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.indexThanksviewgetsubproducerdata", ctx);
		
		if(obj != null && obj instanceof Map){
	        Map map1 = (Map)obj;
	        ctx.putAll(map1);
	    }
		if(objsp != null && objsp instanceof Map){
	        Map mapsp = (Map)objsp;
	        ctx.putAll(mapsp);
	    }
		String mailingOnOFF = null;
		try {
			mailingOnOFF = SystemProperties.getInstance().getString(
					"Insured.sendmail");
		} catch (Exception e) {
			logger.error("Unable to read insured mail setting", e);
		}
		if (mailingOnOFF != null && "Y".equals(mailingOnOFF)) {
			String emailID = getSubProducerEmailID(ctx);
			// String emailID = "jaspreets@outlinesys.com";
			if ("".equals(emailID))
				return;
			
			//MailSender mail = new MailSender();
//			mail.setToAdd(emailID);
//			mail.setFromAdd(emailID);
//			mail.setCcAdd(emailID);
			/*mail.setSubject("Thank you for your interest in Lawyers Application By Subproducer.");
			mail.setContentType("text/html");
			mail.setBody(generateThanksLetterBodySubProducerFlow(ctx));
			mail.sendMailtoSub(ctx);
			*/
			String bccAddress="";
			String ccAddress= "";
			bccAddress=getProducerEmail(ctx);
			
			ctx.put("toAddress",emailID);
			ctx.put("bccAddress",ccAddress);
			ctx.put("subject",""+ctx.get("ProducerName")+" Application for " + ctx.get("AccountName").toString() +"");
			ctx.put("body", generateCompleteAppLinkMailBodyforSubProducer(ctx));
			//ctx.put(key, value);
			SubproducerMailer.sendEmailAsSubProducer((Context)ctx);
//			

//		}
		
//		String emailID = getEmailID(ctx);
//		if ("".equals(emailID))
//			return;
//
//		logger.debug(" - - - Sending Sign and Pay Email to - - - " + emailID);
//		MailSender mail = new MailSender();
//		mail.setToAdd(emailID);
//		mail.setSubject("Thank You from Protexure");
//		mail.setContentType("text/html");
//		mail.setBody(generateSignAndPayMailBodyforSubProducer(ctx));
//		mail.sendMail((Context) ctx);
		logger.debug("Complete app email sent to subproducer.");
	}
		}
		catch(Exception ex){
			logger.error("Unexpected error", ex);
		}
	}
	
	private String generateCompleteAppLinkMailBodyforSubProducer(IContext ctx) throws Exception {
		LawyersValidationUtils utils = new LawyersValidationUtils();
		String encrytedPolicyKey = utils.encrypt(ctx.get("PolicyKey")
				.toString());
		encrytedPolicyKey = URLEncoder.encode(encrytedPolicyKey,""+ StandardCharsets.UTF_8);
		/*encrytedPolicyKey = encrytedPolicyKey.replace("=", "%3D");
		encrytedPolicyKey = encrytedPolicyKey.replace("+", "%2B");*/
		
		String encryptedProducerCode=new LawyersValidationUtils().encryptForUrl(ctx.get("ProducerCode").toString());
		
		String SignatureText=ctx.get("SignatureText")!=null?ctx.get("SignatureText").toString():"";
		String fax=null;
		
		if(ctx.get("Fax") != null && !"".equals(ctx.get("Fax")) && !"null".equals(ctx.get("Fax"))){
			fax="F:&nbsp;"+ctx.get("Fax").toString();
		}
		else{
			fax="";
		}
		
		String emailsig="";
		 if(ctx.get("EmailSignature")!=null && !"".equals(ctx.get("EmailSignature"))){
			 emailsig="<img src=\"cid:image1\">";
		 }
		 
		 String Signature="";
		 if(ctx.get("Signature")!=null && !"".equals(ctx.get("Signature"))){
			 Signature="<tr><td><img src=\"cid:image\"></td></tr>";
		 }
		 
		 String subProducerCode = SystemProperties.getInstance().getString("appl.LawyersIns.subproducer.signaturedisplay");
	     	String[] subProducerCodeList = subProducerCode.split("~");
	     	for(int subProducerCodeCount = 0; subProducerCodeCount < subProducerCodeList.length; subProducerCodeCount++){
	     		if(subProducerCodeList[subProducerCodeCount].equalsIgnoreCase(ctx.get("ProducerCode").toString())){
	     			Signature = "";
	     		}
	     	}
		 
		 String SignatureText2="";
		 if(ctx.get("SignatureText2")!=null && !"".equals(ctx.get("SignatureText2"))){
			 SignatureText2=ctx.get("SignatureText2").toString();
		 }
		 
		 String SignatureText3="";
		 if(ctx.get("SignatureText3")!=null && !"".equals(ctx.get("SignatureText3"))){
			 SignatureText3="<td style='border-left: 3px solid green; padding-left: 5px'><img src=\"cid:image2\"></td>";
			}
		
		String completeAppLink = getProjectUrl(ctx) + "/completeapplink.jsp?PolicyKey="
	    			+ encrytedPolicyKey + "&amp;SubProducer=" + encryptedProducerCode;
		StringBuilder msg = new StringBuilder(2048);
        
        msg.append("<table>");
        msg.append("<tr>");
        msg.append("<td>");
        
       
        msg.append("Thank you for beginning the application process with ").append(ctx.get("ProducerName")).append("!<br/><br/>"); 
        
        msg.append("If you are unable to complete the application for any reason, don't worry!<br/>");
        msg.append("The information you've provided isn't lost.<br/><br/>");
        

        /*msg = msg + "Dear (" + ctx.get("AccountName").toString() + "),"
                    + "<br/><br/>";*/
		msg.append("All you need to do is  <a href='")
	        			.append(completeAppLink).append("'>continue here</a>, and you will be able to:")
                    /*+ " to complete your account with us."*/
                    
                    .append("<br/><br/> ");
        
		msg.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &#8226;   &nbsp; <a href='")
	        		.append(completeAppLink).append("'>View the information entered so far</a><br/> ");
        
		msg.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &#8226;   &nbsp; <a href='")
	        		.append(completeAppLink).append("'>Edit and continue to complete the application</a><br/> ");
       
		  
		msg.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &#8226;   &nbsp; <a href='")
	        		.append(completeAppLink).append("'>Get your Quote!</a><br/><br/> ");
        
        msg.append("Applications can be a pain, ").append(ctx.get("ProducerName")).append(" offers an easy way to complete the annual online application process.<br/><br/>");
        msg.append("If you have any questions, or need assistance with completing the application, please feel free to call us")
                     .append(" at ").append(ctx.get("SPPhoneNumber")).append(" and one of our licensed insurance professionals will gladly assist you.<br/><br/>");
        msg.append("Sincerely,<br/><br/>");
        msg.append("<table cellpadding='0' cellspacing='0'>").append(Signature).append("<tr><td>").append(SignatureText).append("P:&nbsp;").append(ctx.get("SPPhoneNumber")).append("&nbsp;</br>").append(fax).append("</td>").append(SignatureText3).append("</tr></table>");
        msg.append(emailsig).append("</br>");
        msg.append("</br>");
        msg.append("<p style='color:#999999'>").append(SignatureText2).append("</p>");
        msg.append("</td>");
        msg.append("</tr>");
        msg.append("</table>");

        return msg.toString();
  }

	public static void downloadInsurenceCertificate(Context ctx){
		String foFile="";
		HttpServletRequest request = (HttpServletRequest) ctx.get("DocumentRequest");
		HttpServletResponse response = (HttpServletResponse) ctx.get("DocumentResponse");
		try{
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
			String filePath = htmlDir + "data\\InsurenceCertificate_" + ctx.get("PolicyKey").toString() + ".pdf";
			getPolicyData(ctx);
			if("3".equals(ctx.get("CompanyKey").toString()))	
				foFile = SystemProperties.getInstance().getString("xsl.filepath.COI") + "ISMIE2022//foxslnew/endorsement/LiabilityInsCertificate.xsl";
			else
				foFile = SystemProperties.getInstance().getString("xsl.filepath.COI") + "foxsl2017//foxslnew/endorsement/LiabilityInsCertificate.xsl";
			
			String environment=SystemProperties.getInstance().getString("appl.LawyersIns.environment.production");
			ctx.put("environmentproduction",environment);
			new XML2PDF().convertPOToPDF(foFile, new StringBuffer(new DownloadForm().generateDataXml(ctx)), filePath);
			
			exportFileDownLoad(filePath, response);
			if(filePath != null && !filePath.isEmpty()){
	        	deleteDirectory(new File(filePath));
        	}
		}catch(Exception e){
 			logger.error("Unexpected error", e);
		}
	}

	public static void exportFileDownLoad(String filePath, HttpServletResponse response){
    	
    	try{
	    	
	        if(filePath != null && !filePath.isEmpty() && !"null".equals(filePath)){
	
	        	//System.out.println(filePath);
	        	File downloadFile = new File(filePath);
	        	
	        	if(downloadFile.exists()){
	        	    	
		        	    // gets MIME type of the file
	        		String mimeType = "application/pdf";
		        	    //System.out.println("MIME type: " + mimeType);

	        		    // modifies response
	        		response.setContentType(mimeType);
	        		response.setContentLength((int) downloadFile.length());
	        		
		        	    // forces download
	        		String headerKey = "Content-Disposition";
	        	    String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());//attachment
	        	    response.setHeader(headerKey, headerValue);
	        	    response.setHeader("Expires", "0");
	        	    response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		        	    //System.out.println(headerKey+"   fgfgfgf   "+ headerValue);
		        	    
	        		try (FileInputStream inStream = new FileInputStream(downloadFile);
	        				OutputStream outStream = response.getOutputStream()) {
	        			byte[] buffer = new byte[(int) downloadFile.length()];
	        			int bytesRead;
	        			while ((bytesRead = inStream.read(buffer)) != -1) {
	        				outStream.write(buffer, 0, bytesRead);
	        			}
	        		}
		        	    
	        		//System.out.println("sssssss");
	        	}
	        	
	        }
    	} catch(Exception ee) {
    		logger.error("Unexpected error", ee);
		}
    }
	
	public static boolean deleteDirectory(File dir) {
		if (dir.isDirectory()) {
			File[] children = dir.listFiles(); 
			for (int i = 0; i < children.length; i++) { 
				boolean success = deleteDirectory(children[i]);
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}
	
	 public static void setAttorniesList(Context ctx)
	    {
	    	try
	    	{
	    		getPolicyData(ctx);
	    		List finalList =null;
		        List inputList=new ArrayList();
		        List statesList=new ArrayList();
		        String dateData=null;
		        boolean flag=false;
		        boolean isErrorExist=false;
		        boolean isFTEFlag = false;
		        int AnnualWorkedHours = 0;
		        if(ctx.get("AttorneysDetailsList_list_01") != null )
		        {
		        	finalList = (List)ctx.get("AttorneysDetailsList_list_01");
			        int ratedCount=0;
			        int j=0;
			        String isNonRated="N";
			        int anualworkingHours=0;
			        String licensedStates=	ctx.get("newLicensedStates")!=null && !ctx.get("newLicensedStates").equals(HtmlConstants.EMPTY)?ctx.get("newLicensedStates").toString():"";
			        StringTokenizer stok = new StringTokenizer(licensedStates,"#");
					String tokens[] = new String[stok.countTokens()];
				    for(int i=0; i<tokens.length; i++){
				    	statesList.add(stok.nextToken());
				    }
				    
			        Map map = new HashMap();
			        Map map1 = null;
					for (int i = 0; i < finalList.size(); i++) {	
						map = (HashMap) finalList.get(i);
						
						
							ctx.remove("LicensedStates_"+i);
							if(map.get("AttorneyKey")==null)
							{
								map1=new HashMap();
								map1.put("AttorneyName", ctx.get("AttorneyName_"+i));
								map1.put("DesignationId", ctx.get("DesignationId_"+i));
							
								map1.put("LicensedStates", statesList.get(i));
								map1.put("combinedStates",statesList.get(i));
								map1.put("AnnualWorkedHours", ctx.get("AnnualWorkedHours_"+i));
								map1.put("AttorneyPriorActDate", ctx.get("AttorneyPriorActDate_"+i));
							
								map1.put("NumberOfYearsInPractice", ctx.get("NumberOfYearsInPractice_"+i));
								map1.put("PolicyKey", ctx.get("PolicyKey"));
								map1.put("VersionSequence", ctx.get("VersionSequence"));
								map1.put("LastUpdateUserID", ctx.get("LastUpdateUserID"));
								map1.put("LastUpdateTimeStamp", ctx.get("LastUpdateTimeStamp"));
								map1.put("IsNonRatedAttorney", ctx.get("IsNonRatedAttorney_"+i)); 
								map1.put("isAttorneyEndorsed", "N");
								inputList.add(map1);
							}
							else
							{
							map.put("AttorneyName", ctx.get("AttorneyName_"+i));
							map.put("DesignationId", ctx.get("DesignationId_"+i));
						
							map.put("LicensedStates", statesList.get(i));
							map.put("combinedStates",statesList.get(i));
							map.put("AnnualWorkedHours", ctx.get("AnnualWorkedHours_"+i));
							map.put("AttorneyPriorActDate", ctx.get("AttorneyPriorActDate_"+i));
						
							map.put("NumberOfYearsInPractice", ctx.get("NumberOfYearsInPractice_"+i));
							map.put("PolicyKey", ctx.get("PolicyKey"));
							map.put("VersionSequence", ctx.get("VersionSequence"));
							map.put("LastUpdateUserID", ctx.get("LastUpdateUserID"));
							map.put("LastUpdateTimeStamp", ctx.get("LastUpdateTimeStamp"));
							map.put("IsNonRatedAttorney", ctx.get("IsNonRatedAttorney_"+i)); 
							
							inputList.add(map);
							}
						Object attorneyPriorActDateObj = ctx.get("AttorneyPriorActDate_"+i);
						if(attorneyPriorActDateObj != null && !attorneyPriorActDateObj.equals(HtmlConstants.EMPTY))
						{ 	dateData=attorneyPriorActDateObj.toString();
							if(validateDateFieldData(dateData)){
								flag=true;
								LawyersUtils.populateError(ctx, "attorneyDataIncomplete","Please enter valid date.");
								ctx.put("saveAttorneys","N");
							}
						}
						
					}
					
					for (int i = 0; i < finalList.size(); i++) {
						ctx.put("LicensedStates_"+i, statesList.get(i));				
					}				
					ctx.put("AttorneysDetailsList_list_01", inputList);
					ctx.put("firm_list_4", inputList);
					
					ctx.put("skipAttorneyView", "Y");
				}
	    	}
	        catch(Exception e){
			logger.error("Unable to convert list to XML", e);
	        	ctx.put("skipSave","Y");
	        }
	    }
	   
public static void manageDeleteAttorneyForEndorsement(Context ctx)
{
	logger.debug("manageDeleteAttorneyForEndorsement method called for endorsement");
	try
	{
		List finalList =null;
        List inputList=new ArrayList();
        List statesList=new ArrayList();
       StringBuilder deletedAttorneys=new StringBuilder();
        String dateData=null;
        boolean flag=false;
        boolean isErrorExist=false;
        boolean isFTEFlag = false;
        Map map1 = new HashMap();
        String oldDeletedData=ctx.get("deletedAttorneys")!=null && !ctx.get("deletedAttorneys").equals(HtmlConstants.EMPTY)?ctx.get("deletedAttorneys").toString():"";
        if(!oldDeletedData.equals(""))
        deletedAttorneys=deletedAttorneys.append(oldDeletedData+",");
		 if(ctx.get("AttorneysDetailsList_list_01") != null )
	        {
	        	finalList = (List)ctx.get("AttorneysDetailsList_list_01");
	        	if(finalList.size()<2)
	        	{
	        		LawyersUtils.populateError(ctx, "attorneyDataIncomplete","Minimum one attorney required");
	        	return;
	        	}
	        		int index=ctx.get("rownum")!=null?Integer.parseInt(ctx.get("rownum").toString()):50;
	        		map1=(HashMap) finalList.get(index-1);
	        		if(deletedAttorneys.toString().contains(map1.get("AttorneyKey").toString())==false)
	        			deletedAttorneys=deletedAttorneys.append(map1.get("AttorneyKey").toString());
		        finalList.remove(index-1);
		        
		        String licensedStates=	ctx.get("newLicensedStates")!=null && !ctx.get("newLicensedStates").equals(HtmlConstants.EMPTY)?ctx.get("newLicensedStates").toString():"";
		        StringTokenizer stok = new StringTokenizer(licensedStates,"#");
				String tokens[] = new String[stok.countTokens()];
			    for(int i=0; i<tokens.length; i++){
			    	statesList.add(stok.nextToken());
			    }
			    if (index - 1 < statesList.size())
			    	statesList.remove(index-1);
		        Map map = new HashMap();
				for (int i = 0; i < finalList.size(); i++) {	
					map = (HashMap) finalList.get(i);
					ctx.remove("LicensedStates_"+i);
					map.put("rownum", i+1);
					if (i < statesList.size()) {
						map.put("LicensedStates", statesList.get(i));
						map.put("combinedStates",statesList.get(i));
					}
					inputList.add(map);
				}
				for (int i = 0; i < finalList.size(); i++) {
					if (i < statesList.size())
						ctx.put("LicensedStates_"+i, statesList.get(i));				
				}				
				ctx.put("AttorneysDetailsList_list_01", inputList);
				ctx.put("skipAttorneyView", "Y");
			}
		 ctx.put("deletedAttorneys", deletedAttorneys);
	}
	catch(Exception e)
	{
		logger.error("Exception occured in manageDeleteAttorneyForEndorsement method :"+e);
	}
}
public static void saveEndorsedAttornies(Context ctx)
{
	try
	{
		
		List newVersionSequence= (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetFirmMaxVersionSequence",ctx);
		ctx.putAll((Map) newVersionSequence.get(0));
		List finalList =null;
        List inputList=new ArrayList();
        List statesList=new ArrayList();
        String dateData=null;
        boolean flag=false;
        boolean isErrorExist=false;
        boolean isFTEFlag = false;
        int AnnualWorkedHours = 0;
        if(ctx.get("AttorneysDetailsList_list_01") != null && ctx.get("AttorneysDetailsList_list_01") instanceof List)
        {
        	finalList = (List)ctx.get("AttorneysDetailsList_list_01");
	        int ratedCount=0;
	        int j=0;
	        String isNonRated="N";
	        int anualworkingHours=0;
	        
	        boolean modifyLincensedStates=false;
	        String licensedStates=	ctx.get("newLicensedStates")!=null && !ctx.get("newLicensedStates").equals(HtmlConstants.EMPTY)?ctx.get("newLicensedStates").toString():"";
	       if(!"".equals(licensedStates))
	       {
		        StringTokenizer stok = new StringTokenizer(licensedStates,"#");
				String tokens[] = new String[stok.countTokens()];
			    for(int i=0; i<tokens.length; i++){
			    	statesList.add(stok.nextToken());
		    }
	       }
	        Map map = new HashMap();
			for (int i = 0; i < finalList.size(); i++) {	
				map = (HashMap) finalList.get(i);
				
				if(modifyLincensedStates)
				{
					ctx.remove("LicensedStates_"+i);
					if (i < statesList.size())
						map.put("LicensedStates", statesList.get(i));
				}
					map.put("AttorneyName", ctx.get("AttorneyName_"+i));
					map.put("DesignationId", ctx.get("DesignationId_"+i));
					
					map.put("AnnualWorkedHours", ctx.get("AnnualWorkedHours_"+i));
					map.put("AttorneyPriorActDate", ctx.get("AttorneyPriorActDate_"+i));
					map.put("NumberOfYearsInPractice", ctx.get("NumberOfYearsInPractice_"+i));
					map.put("PriorActDateAttorneysNew", ctx.get("PriorActDateAttorneysNew"+i));
					map.put("PolicyKey", ctx.get("PolicyKey"));
					map.put("VersionSequence", ctx.get("VersionSequence"));
					map.put("VersionKey", ctx.get("VersionKey"));
					map.put("LastUpdateUserID", ctx.get("LastUpdateUserID"));
					map.put("LastUpdateTimeStamp", ctx.get("LastUpdateTimeStamp"));
					map.put("IsNonRatedAttorney", ctx.get("IsNonRatedAttorney_"+i)); 
					if(map.get("isAttorneyEndorsed")==null)
					map.put("isAttorneyEndorsed", "N"); 
					
					inputList.add(map);
					
				
				Object attorneyPriorActDateObj = ctx.get("AttorneyPriorActDate_"+i);
				if(attorneyPriorActDateObj != null && !attorneyPriorActDateObj.equals(HtmlConstants.EMPTY))
				{ 	dateData=attorneyPriorActDateObj.toString();
					if(validateDateFieldData(dateData)){
						flag=true;
						LawyersUtils.populateError(ctx, "attorneyDataIncomplete","Please enter valid date.");
						ctx.put("saveAttorneys","N");
					}
				}
			}
			if(modifyLincensedStates)
			{
			for (int i = 0; i < finalList.size(); i++) {
				if (i < statesList.size())
					ctx.put("LicensedStates_"+i, statesList.get(i));				
				}				
			}
			if(inputList.size()>0 && flag==false){
				ctx.put("inputList",inputList);
				convertListDataToXML(ctx,"inputList","outputList");
				ctx.put("addAttorney", finalList.size());
				ctx.put("saveAttorneys","Y");
			}
			else
				ctx.put("saveAttorneys","N");				
        }
	}
    catch(Exception e){
		logger.error("Unable to convert list to XML", e);
    	ctx.put("skipSave","Y");
    }
}

public static void setAttorniesForDeleteEndorsement(Context ctx)
{
	try
	{
		ctx.put("firm_list_4", ctx.get("AttorneysDetailsList_list_01"));
		ctx.put("skipAttorneyView", "Y");
		
	}
    catch(Exception e){
		logger.error("Unable to convert list to XML", e);
    	ctx.put("skipSave","Y");
    }
}

public static void saveAttorniesForDeleteEndorsement(Context ctx)
{
		try
		{
			
			List newVersionSequence= (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetFirmMaxVersionSequence",ctx);
			ctx.putAll((Map) newVersionSequence.get(0));
			getPolicyData(ctx);
			List finalList =null;
	        List inputList=new ArrayList();
	      int attorneyDataListSize=0,newAttorneyListSize=0;
	        List attorneyOldDataList  =(List) SqlResources.getSqlMapProcessor(ctx).select("AttorneyDetailsLW.GetAttorneyData_p",ctx);
			if(attorneyOldDataList!=null && attorneyOldDataList instanceof List)
				attorneyDataListSize=attorneyOldDataList.size();
			
			List newAttorneyDataList=(List)ctx.get("AttorneysDetailsList_list_01");
			if(newAttorneyDataList == null)
			{
				ctx.put("isUpdateAttorneyList","N");
				return;
			}
			newAttorneyListSize=newAttorneyDataList.size();
	        if(attorneyDataListSize!=newAttorneyListSize)
	        {
		        if(ctx.get("AttorneysDetailsList_list_01") != null && ctx.get("AttorneysDetailsList_list_01") instanceof List)
		        {
		        	finalList = (List)ctx.get("AttorneysDetailsList_list_01");
			        Map map = new HashMap();
					for (int i = 0; i < finalList.size(); i++) {	
						map = (HashMap) finalList.get(i);
						ctx.remove("LicensedStates_"+i);
						map.put("PolicyKey", ctx.get("PolicyKey"));
						map.put("VersionSequence", ctx.get("VersionSequence"));
						map.put("VersionKey", ctx.get("VersionKey"));
						map.put("LastUpdateUserID", ctx.get("LastUpdateUserID"));
						map.put("LastUpdateTimeStamp", ctx.get("LastUpdateTimeStamp"));
						inputList.add(map);
					}
					if(inputList.size()>0){
						ctx.put("inputList",inputList);
						convertListDataToXML(ctx,"inputList","outputList");
						ctx.put("addAttorney", finalList.size());
						ctx.put("isUpdateAttorneyList","Y");
					}
					else
						ctx.put("isUpdateAttorneyList","N");				
		        }
		        }
		}
	    catch(Exception e){
		logger.error("Unable to convert list to XML", e);
	    	ctx.put("skipSave","Y");
	    }
	}	
public static void sendSubProducerCodeandAccess(IContext ctx) {
	logger.debug("method in sendSubProducerCodeandAccess");
	try	
	{
		String producerCode="";
		String subProducerAccess="";
		String subProducerAccess1="";
		
		Context newctx=new Context();
		newctx.setProject(ctx.getProject());
		SecureUtils secureUtils=new SecureUtils();
		
		producerCode=(String) ctx.get("ProducerCode");
		subProducerAccess=(String) ctx.get("SubProducerAccess");
		subProducerAccess1=(String) ctx.get("SubProducerAccess1");
		if(!subProducerAccess1.equalsIgnoreCase(subProducerAccess)){
			if(subProducerAccess.equals("Y")){
				subProducerAccess="A";
			}
			else if(subProducerAccess.equals("N")){
				subProducerAccess="I";
			}
			newctx.put("ProducerCode", producerCode);
			newctx.put("SubProducerAccess", subProducerAccess);
		
			secureUtils.updateSubproducerStatus(newctx);
			logger.debug("WEB SErvice CALL");
			logger.debug("WEB SErvice CALL");
		}
		else{
			logger.debug("No WEB SErvice CALL");
			logger.debug("No WEB SErvice CALL");
		}
	} catch(Exception e) {
		logger.error("Error in sendSubProducerCodeandAccess" + e.getMessage());
		logger.error("Unexpected error", e);
	}	
}
	public static void updateCoverageDataForRenewalPolicy(Context ctx) {	
		try	{
			List object = (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetCoverageDataForRenewalPolicy",ctx);
			ctx.putAll((Map) object.get(0));
			
			String IsClaimExpensesTypeOld = ctx.get("IsClaimExpensesTypeOld") != null ? ctx.get("IsClaimExpensesTypeOld").toString() : "N";
			String IsDollarDefenseOld = ctx.get("IsDollarDefenseOld") != null ? ctx.get("IsDollarDefenseOld").toString() : "N";
			ctx.put("IsClaimExpensesType", IsClaimExpensesTypeOld);
			ctx.put("IsDollarDefense", IsDollarDefenseOld);
			ctx.put("StateCode", ctx.get("StateCode"));
			ctx.put("LimitSequence", ctx.get("LimitSequence"));
			ctx.put("TransactionSequence", ctx.get("TransactionSequence"));
			
			logger.debug("Going to set dollar defense and claim expense type for renewal policy");
			RuleUtils.executeRule(ctx,"LawyersRule.AssignClaimExpensesAndDollarDefense");
			String IsClaimExpensesType = ctx.get("IsClaimExpensesType") != null ? ctx.get("IsClaimExpensesType").toString() : "N";
			String IsDollarDefense = ctx.get("IsDollarDefense") != null ? ctx.get("IsDollarDefense").toString() : "N";
			
			ctx.put("IsClaimExpensesType", IsClaimExpensesType);
			ctx.put("IsDollarDefense", IsDollarDefense);
			
			int ret = SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriessetCoverageDataForPolicy",ctx);	
			ctx.remove("IsClaimExpensesType");
			ctx.remove("IsDollarDefense");
			ctx.put("IsRuleExecuted","Y");
			logger.debug("Dollar defense and claim expense type for renewal policy with policy key " + ctx.get("PolicyKey") + " has been updated");
						
		} catch(Exception e) {
			logger.error("Error occured while setting coverage data for renewal policy " + e);
		}
		
	}
	
    public static void setRecordsListPre(IContext ctx){//recordsperpage  pagenumber
		
		try{
			logger.debug("In setRecordsListPre method logging start ");
			double searchListCount = 0;
			String searchListAction = (ctx.get("searchListAction") == null || "".equals(ctx.get("searchListAction"))) ? "pending" : ctx.get("searchListAction").toString();
			//ctx.put("UserNo", Integer.parseInt(ctx.get("UserNo").toString()));
			ctx.put("BucketListAction", searchListAction);
			ctx.put("LastUpdateUserID", String.valueOf(ctx.get("UserNo")));
			
			
			Object objRule = RuleUtils.executeRule(ctx, "LawyersRule.ComingfromLostworkqBucket");
			if(objRule != null){
				Boolean rule = (Boolean)objRule;
				if(rule){
					List searchCountForLawyersData = SqlResources.getSqlMapProcessor(ctx).select("ProfileFilter.SearchCountForListLawyersLostWorkque_Proc", ctx);
					Map map = new HashMap();
					if (searchCountForLawyersData != null) {
						for (int i = 0; i < searchCountForLawyersData.size(); i++) {
							map = (HashMap) searchCountForLawyersData.get(i);
							searchListCount = Double.parseDouble(map.get("totalCount").toString());
						}
					}
				} else {
					List searchCountForLawyersData = SqlResources.getSqlMapProcessor(ctx).select("ProfileFilter.SearchCountForListLawyers_Proc", ctx);
					Map map = new HashMap();
					if (searchCountForLawyersData != null) {
						for (int i = 0; i < searchCountForLawyersData.size(); i++) {
							map = (HashMap) searchCountForLawyersData.get(i);
							searchListCount = Double.parseDouble(map.get("totalCount").toString());
						}
					}
				}
			}
			double recordsperpage = (ctx.get("recordsperpage") == null || "".equals(ctx.get("recordsperpage")))? 20.0 : Double.parseDouble(ctx.get("recordsperpage").toString());
			double pagenumber = (ctx.get("pagenumber") == null || "".equals(ctx.get("pagenumber")))? 1.0 : Double.parseDouble(ctx.get("pagenumber").toString());
			String paginationaction = (ctx.get("paginationaction") == null) ? "" : ctx.get("paginationaction").toString();
			String inetmethod = (ctx.get("inet_method") == null) ? "" : ctx.get("inet_method").toString();
			String pageSort = (ctx.get("PageSort") == null) ? "" : ctx.get("PageSort").toString();
			String pageSortType = (ctx.get("page_sort_type") == null) ? "" : ctx.get("page_sort_type").toString();
			
			if("previouspage".equals(paginationaction)){
				pagenumber = pagenumber - 1;
			} else if("nextpage".equals(paginationaction)){
				pagenumber = pagenumber + 1;
			} else if("firstpage".equals(paginationaction) || "setrecordcount".equals(paginationaction)){
				pagenumber = 1;
			} else if("lastpage".equals(paginationaction)){
				pagenumber = Math.ceil(searchListCount / recordsperpage);
			}
			int searchListCountI = (int) searchListCount;
			int recordsperpageI = (int) recordsperpage;
			int pagenumberI = (int) pagenumber;
//			if("sorting".equals(inetmethod)){
//				recordsperpageI = 0;
//				pagenumberI = 1;
//			}
			
			ctx.put("PageSize",recordsperpageI);
			ctx.put("PageNumber",pagenumberI);
			ctx.put("searchListCount",searchListCountI);
			ctx.put("PageSort",pageSort);
			ctx.put("page_sort_type",pageSortType);
//			ctx.put("recordsperpage",recordsperpageI);
//			ctx.put("pagenumber",pagenumberI);
		}catch(Exception e){
			logger.error("Unexpected error", e);
		}
	}
	public static void setRecordsListPost(IContext ctx){//recordsperpage  pagenumber
		List attorneyListold = null;
		List attorneyListNew = new ArrayList();
//		int size = 0;
		if (ctx.get("Search_firm_list_01") != null) {
			attorneyListold = (List) ctx.get("Search_firm_list_01");
//			size = attorneyListold.size();
		}
		double searchListCount = (ctx.get("searchListCount") != null || !"".equals(ctx.get("searchListCount")))?Double.parseDouble(ctx.get("searchListCount").toString()):20;
		double recordsperpage = (ctx.get("recordsperpage") == null || "".equals(ctx.get("recordsperpage")))? 20.0 : Double.parseDouble(ctx.get("recordsperpage").toString());
		double pagenumber = (ctx.get("pagenumber") == null || "".equals(ctx.get("pagenumber")))? 1.0 : Double.parseDouble(ctx.get("pagenumber").toString());
		String paginationaction = (ctx.get("paginationaction") == null) ? "" : ctx.get("paginationaction").toString();
		String inetmethod = (ctx.get("inet_method") == null) ? "" : ctx.get("inet_method").toString();
		
		if("previouspage".equals(paginationaction)){
			pagenumber = pagenumber - 1;
		} else if("nextpage".equals(paginationaction)){
			pagenumber = pagenumber + 1;
		} else if("firstpage".equals(paginationaction) || "setrecordcount".equals(paginationaction)){
			pagenumber = 1;
		} else if("lastpage".equals(paginationaction)){
			pagenumber = Math.ceil(searchListCount / recordsperpage);
		}
		int searchListCountI = (int) searchListCount;
		int recordsperpageI = (int) recordsperpage;
		int pagenumberI = (int) pagenumber;
//		if("sorting".equals(inetmethod)){
//			recordsperpageI = 0;
//			pagenumberI = 1;
//		}
		
		int listCountOld = 0;
		Map m = new HashMap();
		for(int listCount = 0; listCount < searchListCountI; listCount++){
			if((listCount >= ((recordsperpageI * pagenumberI)-recordsperpageI) && listCount < (recordsperpageI * pagenumberI)) || (recordsperpageI == 0)){
				attorneyListNew.add(attorneyListold.get(listCountOld++));
			} else {
				attorneyListNew.add(m);
			}
		}
		
		ctx.put("Search_firm_list_01",attorneyListNew);
	}
	
	public static void lawyerLoggingStart(IContext ctx){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");  
	    Date date = new Date();  
	    logger.debug(formatter.format(date));
		logger.debug("In " + ctx.get("loggingMethod") + " component logging start " + formatter.format(date));
	}
	public static void lawyerLoggingEnd(IContext ctx){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");  
	    Date date = new Date();  
	    logger.debug(formatter.format(date));
		logger.debug("In " + ctx.get("loggingMethod") + " component logging end " + formatter.format(date));
	}
	
	public static void validateAOPPercentageQucikQuote(Context ctx) throws Exception {
		int total = 0;
		Map map = new HashMap();
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select(
				"AOPLW.findAll", ctx);
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
				try {
					total = total + Integer.parseInt(percentageValue);
				} catch (Exception e) {
					populateError(ctx, "TotalAOP_Percentage",
							"Invalid AOP data");
				}
			}
		}
		if (total < 100 || total > 100) {
			percentageError(ctx, total, "100");
		}
	}
	
	public static void printQuickQuoteContent(IContext ctx){
		
		HttpServletRequest request = (HttpServletRequest) ctx.get("DocumentRequest");
		HttpServletResponse response = (HttpServletResponse) ctx.get("DocumentResponse");
		try{
			String htmlDir = SystemProperties.getInstance().getString("html.basedir");
			String filePath = htmlDir + "data\\QuickQuoteOptions_" + ctx.get("QuoteNumber").toString() + ".pdf";
			
			String foFile = SystemProperties.getInstance().getString("xsl.filepath") + "quickquote.xsl";
      
			String environment=SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production");
			ctx.put("environmentproduction",environment);
			new XML2PDF().convertPOToPDF(foFile, new StringBuffer(new DownloadForm().generateDataXml((Context)ctx)), filePath);
			
			exportFileDownLoad(filePath, response);
			if(filePath != null && !filePath.isEmpty()){
	        	deleteDirectory(new File(filePath));
        	}
		}catch(Exception e){
 			logger.error("Unexpected error", e);
		}
	}	
	
	public static String getProducerEmail(IContext ctx){
		String ccAddress= "";
		String productionEnv = "Y";
		try {
			productionEnv = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production");
		
		if ("N".equals(productionEnv))
			ccAddress=SystemProperties.getInstance().getString("mail.adminsub.cc.address");
		else 
			 ccAddress = ctx.get("Producer_email")!=null?ctx.get("Producer_email").toString():SystemProperties.getInstance().getString("mail.adminsub.cc.address");
		
		} 
		catch (Exception e) 
		{
			logger.error("error in getting ProducerEmail method "+e.getMessage());
		}
		return ccAddress;
	}
	
	public static String getSubProducerEmailID(IContext ctx) throws Exception {
		String emailID = "";
		String productionEnv = "Y";
		try {
			productionEnv = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production");
		} 
		catch (Exception e) 
		{
			logger.error("error in geeting production environment");
		}
		if ("N".equals(productionEnv)){
			emailID = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".adminsub.email");
		}
		else{ 
			if (ctx.get("AccountEmail") != null)
			emailID = ctx.get("AccountEmail").toString();
			try
			{
				Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetFirmAddressDetails", ctx);
				if(obj != null && obj instanceof Map)
				{
					ctx.putAll((Map)obj);
				}
			}
			catch(Exception e)
			{
				logger.error("Error occured while fetching secondary email address of firm :"+e);
			}
			if(ctx.get("secondaryEmail")!=null)
				emailID=emailID+","+ctx.get("secondaryEmail").toString();
		}
		return emailID;
	}
	
	public static String getProducerdisplayEmail(IContext ctx){
		String ccAddress= "";
		String productionEnv = "Y";
		try {
			productionEnv = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production");
		
		if ("N".equals(productionEnv))
			ccAddress=SystemProperties.getInstance().getString("mail.adminsubdisplay.address");
		else 
			 ccAddress = ctx.get("Producer_email")!=null?ctx.get("Producer_email").toString():SystemProperties.getInstance().getString("mail.adminsub.cc.address");
		
		} 
		catch (Exception e) 
		{
			logger.error("error in getting ProducerEmail method "+e.getMessage());
		}
		return ccAddress;
	}
	
	public static void deleteSecondQuickQuote(IContext ctx) throws Exception
    {
		List list = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdeletegetPolicyQuote", ctx);
		
		if(list != null && list.size() > 1)
		{
			for (int i = 1; i < list.size(); i++) {

				Map map = (Map) list.get(i);

				Context newCtx = new Context();
				newCtx.setProject(ctx.getProject());
				newCtx.put("PolicyKey", map.get("PolicyKey"));
				newCtx.put("RatingTraceSequence", map.get("RatingTraceSequence"));
				newCtx.put("TransactionSequence", map.get("TransactionSequence"));
				newCtx.put("CoverageSequence", map.get("CoverageSequence"));

				DBUtils.executeDBOperation(newCtx, "RatingTrace", "2");
				DBUtils.executeDBOperation(newCtx, "Quote", "2");
				SqlResources.getSqlMapProcessor(newCtx).delete("SqlStmts.UserStatementdeleteDeleteSurchargeTax", newCtx);
				DBUtils.executeDBOperation(newCtx, "PolicyCoverage", "2");
				DBUtils.executeDBOperation(newCtx, "PolicyTransaction", "2");

			}			
			
		}
//		if(list != null && list.size() > 0)
//			generateQuickQuoteListLetter(ctx);
    }
	
	public static void sendIndicationToInsured(IContext ctx){
		String mailingOnOFF = null;
		try {

			mailingOnOFF = SystemProperties.getInstance().getString(
					"Insured.sendmail");
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}

		try{
			if (mailingOnOFF != null && "Y".equals(mailingOnOFF)) {
				// if(ctx.get("IsQuickQuoteByEmail") != null &&
				// "Y".equals(ctx.get("IsQuickQuoteByEmail").toString()))
				// {
	
				String emailID = getEmailID(ctx);
				if ("".equals(emailID))
					return;
				
				String bccAddress="";
				if("".equals(bccAddress))
					bccAddress=SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.bcc.email");
				logger.debug("Mail is going---- ");
				MailSender mail = new MailSender();
				mail.setToAdd(emailID);
				mail.setCcAdd(getFirstReviewerAgentEmailID(ctx));
				mail.setBccAdd(bccAddress);
				mail.setSubject("Thank you for your interest in Protexure Lawyers Insurance");
				mail.setContentType("text/html");
				mail.setBody(generateIndicationBody(ctx,mail));
				mail.sendMail((Context) ctx);
				logger.debug("Mail has sent---- ");
				// }
			}
		}catch(Exception e){
			logger.error("Unexpected error", e);
		}
	}
	
	private static String generateIndicationBody(IContext ctx, MailSender mail) throws Exception {
		LawyersUtils accountUtils = new LawyersUtils();
		NumberFormat numberFormat =
			    NumberFormat.getCurrencyInstance(Locale.US);
		StringBuilder msg = new StringBuilder(2048);
		msg.append("<table>");
		msg.append("<tr>");
		msg.append("<td>");

		msg.append(ctx.get("AccountName")).append(",<br/><br/>");

		msg.append("Thank you for giving us the opportunity to provide you with an estimate for professional liability coverage!<br/><br/>");

		msg.append("Based on the information you provided we have generated the following premium estimate(s): <br/><br/>");

		List aoplist = null;		
		if(ctx.get("indication_list_01") != null){
			aoplist = (List) ctx.get("indication_list_01");
		}else{
			aoplist = SqlResources.getSqlMapProcessor(ctx).select("PolicyTransition.GetPolicyIndications_p", ctx);
		}
		if (aoplist != null) {
			Map map = new HashMap();
			for (int i = 0; i < aoplist.size(); i++) {
				
				
				map = (HashMap) aoplist.get(i);
				
				/*if("Y".equals(map.get("IsQuoteSelected"))){
					msg = msg + "<img src=\"cid:memememe\"/>" + "&nbsp;&nbsp;";
					mail.setImgPath(SystemProperties.getInstance().getString("appl.home.dir") + "image//check-btn4.png");
				} else {
					msg = msg + "<img src=\"cid:memememe1\"/>" + "&nbsp;&nbsp;";
					mail.setImgPath1(SystemProperties.getInstance().getString("appl.home.dir") + "image//check-btn4.png");
				}*/
				int count=i+1;
				msg.append("<b><u>Option ").append(count).append("</u></b><br/>");
				
				
				/*msg = msg + "Premium: <b>" + map.get("InvoicedPremium") +"</b>&nbsp;&nbsp;";*/
				msg.append("Limit: <b>").append(map.get("LimitAmount")).append("</b>&nbsp;&nbsp;<br/>");
				if(map.get("IsClaimExpensesType").equals("Yes"))
				{
				msg.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Claims Expense Outside the Limit <br/>");
				}
				if(map.get("IsClaimExpensesType").equals("No"))
				{
				msg.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Claims Expenses Inside the Limit <br/>");
				}
				
				msg.append("Deductible: <b>").append(map.get("DeductibleAmount")).append("</b>&nbsp;&nbsp;<br/>");
				
				if(map.get("IsClaimOptionType").equals("Yes"))
				{
				msg.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Aggregate");
				}
				if(map.get("IsClaimOptionType").equals("No"))
				{
				msg.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Per Claim");
				}
				if(map.get("IsDollarDefenseText").equals("Yes"))
				{
				msg.append(", First Dollar Defense");
				
				}
				msg.append("<br/>");
				
				
				String invoicedPremium = removeAmountFormat(map.get("InvoicedPremium")).toString();
				Double d = (Double.parseDouble(invoicedPremium) / 10);
				int premium  = Integer.parseInt(d.toString().split("\\.")[1]);
				if(premium < 5){
					msg.append("Premium: <b>").append(numberFormat.format((Double.parseDouble(invoicedPremium) - premium)).split("\\.")[0]).append("</b>&nbsp;");
				} else {
					msg.append("Premium: <b>").append(numberFormat.format((Double.parseDouble(invoicedPremium) + (10 - premium))).split("\\.")[0]).append("</b>&nbsp;");
				}
				
			if(i==1){
					msg.append("<br/><br/>");
				} else {
				msg.append("<br/><br/>");
				}
			
			
			}
		}
		msg.append("<br/>");

		msg.append("If you have any questions regarding our program, please give us a call at 877-569-4111.  You can access additional information about <a href='http://info.protexurelawyers.com/protexure-lawyers-professional-liability-insurance'> Protexure Lawyers here.</a><br/><br/>");
		/*msg = msg + "To receive a full quotation, follow the link below and complete the application.  To make the process easier, the information you provided has been pre-entered into our online system." + "<br/><br/>";*/
		msg.append("To receive a full quotation, follow the link below and complete the application. To make the process easier, the information you provided has been pre-entered into our online system.<br/><br/>");
		
		msg.append("<a href=").append(accountUtils.getProjectUrl(ctx)).append("/indicationInsured.jsp?PolicyKey=").append(ctx.get("PolicyKey").toString())
				.append("&VersionSequence=").append(ctx.get("VersionSequence").toString())
				.append("&TransactionSequence=").append(ctx.get("TransactionSequence").toString())
				.append("&CoverageSequence=").append(ctx.get("CoverageSequence").toString())
				.append("&RatingTraceSequence=").append(ctx.get("RatingTraceSequence").toString())
				.append("&AccountID=").append(ctx.get("AccountID").toString())
				.append("&AccountKey=").append(ctx.get("AccountKey").toString())
				.append("&AddressKey=").append(ctx.get("AddressKey").toString())
				.append(">Continue to Application</a><br/><br/>");
		
		/*msg = msg + "To help you better evaluate your options we have included some additional information on our <a href='http://info.protexurelawyers.com/protexure-lawyers-professional-liability-insurance#Features'> policy features</a>,<a href='http://info.protexurelawyers.com/protexure-lawyers-professional-liability-insurance#Claims'> claims handling </a>, and a <a href='http://info.protexurelawyers.com/protexure-lawyers-professional-liability-insurance#Specimen'>specimen policy</a>." + "<br/><br/>";
		msg = msg + "If you have any questions please contact one of our experienced professional liability specialists at 877-569-4111." + "<br/><br/>";
*/
		msg.append("Sincerely,<br/><br/>");
		msg.append("<b>Protexure Lawyers Insurance</b><br/>");
		msg.append("<b>877-569-4111</b><br/>");
		msg.append("<b>Fax: (440) 333-3214</b><br/>");
		msg.append("<b>www.protexurelawyers.com</b>");
		
		msg.append("</td>");
		msg.append("</tr>");
		msg.append("</table>");

		return msg.toString();
	}
	
	public void saveClaimAgeDataFullQuoteEndorsement(IContext ctx, int claimAge,int attorneyKey)
			throws Exception {

		Context newCtx = new Context();
		newCtx.setProject(ctx.getProject());
		newCtx.put("PolicyKey", ctx.get("PolicyKey"));
		newCtx.put("Year", claimAge);
		newCtx.put("PartTime", "");
    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
    	newCtx.put("AttorneyKey",attorneyKey);
    	newCtx.put("VersionKey",ctx.get("VersionKey"));
    	newCtx.put("CreatedBy",ctx.get("LastUpdateUserID"));
    	newCtx.put("CreatedDate",ctx.get("LastUpdateTimeStamp"));
		Object obj = DBUtils.executeDBOperation(newCtx, "ClaimAgeLWEndorsement", "1");
    	/*new SetParametersForStoredProcedures().setParametersInContext(newCtx, "PolicyKey,Year,LastUpdateTimeStamp,LastUpdateUserID,AttorneyKey");
		SqlResources.getSqlMapProcessor(newCtx).update("ClaimAgeLWEndorsement.InsertClaimAgeEndorsementDetails_p",newCtx);*/

	}
public static void mergeAccounts(Context ctx)
{
	try
	{
		 List finalList = null;
		 HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
	        HttpSession sess = req.getSession();
	        if(ctx.get("AccountsSearch_list_01") != null &&   ctx.get("AccountsSearch_list_01") instanceof List)
	        {
		        finalList = (List)ctx.get("AccountsSearch_list_01");
		        if(finalList.size()>0)
		        {
		        	Map map =null;
		        	for (int i = 0; i < finalList.size(); i++)
		        	{
		        		map=(Map) finalList.get(i);
		        		int accountNumber=map.get("AccountNumber")!=null?Integer.parseInt(map.get("AccountNumber").toString()):0;
		        		int accountID=ctx.get("AccountID_"+i)!=null?Integer.parseInt(ctx.get("AccountID_"+i).toString()):0;
		        		if(accountNumber!=0 && accountID!=0 && accountNumber!=accountID){
		        			logger.debug("going to update "+accountNumber+" with "+accountID);
		        			try {
		        				ctx.put("PolicyKey",map.get("PolicyKey"));
		        				ctx.put("newAccountID", accountID);
		        				ctx.put("oldAccountID", accountNumber);
		    					new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,newAccountID,oldAccountID,UserNo");
		    					SqlResources.getSqlMapProcessor(ctx).update("Policy.UpdateAccountIdLW_p",ctx);
		    				} catch (Exception e) {
					logger.error("Unable to update account IDs", e);
		    					logger.error("Unexpected error", e);
		    				}
		        		}
		        	}
		       }
	        }
	}
	catch(Exception e)
	{
		logger.error("Unable to merge accounts", e);
		logger.error("Unexpected error", e);
	}
}

public static void validateAccountManagement(Context ctx)
{
	String policyKey=ctx.get("strPolicyKey").toString();
	String accountActivity=ctx.get("accountActivity").toString();
	boolean flag=false,flag1=false;
	try {
		String methodType= ctx.get("inet_method")!=null && !ctx.get("inet_method").equals(HtmlConstants.EMPTY) ?ctx.get("inet_method").toString():"back";
		if("search".equalsIgnoreCase(methodType))
		{
		
				
					if(ctx.get("AccountNameSearch")!=null && !"".equals(ctx.get("AccountNameSearch").toString()))
						flag=true;
					if(ctx.get("AccountEmailSearch")!=null && !"".equals(ctx.get("AccountEmailSearch").toString()))
						flag=true;
					if(ctx.get("AccountNumberSearch")!=null && !"".equals(ctx.get("AccountNumberSearch").toString()))
						flag=true;
					if(ctx.get("WorkPhoneSearch")!=null && !"".equals(ctx.get("WorkPhoneSearch").toString()))
						flag=true;
					if(ctx.get("PolicyEffectiveMonthYear")!=null && !"".equals(ctx.get("PolicyEffectiveMonthYear").toString()))
						flag1=true;
					if(ctx.get("MergeStateCode")!=null & !"".equals(ctx.get("MergeStateCode").toString()))
						flag1=true;
					
					if(flag!=true && flag1!=true)
					{
						LawyersUtils.populateError(ctx, "noMandetoryFiletr","You need to select one of the filter.");
						return;
					}
					if(flag!=true && flag1==true)
					{
						LawyersUtils.populateError(ctx, "noMandetoryFiletr","State or Effective date can't be used independently, additonal to these one more filter required.");
						return;
					}
				
						
				
		}
		if("save".equalsIgnoreCase(methodType))
		{
			if(policyKey==null |policyKey.equals(""))
			{
				LawyersUtils.populateError(ctx, "noSelecion","No account has selected for Link/Delink activity.");
				return;
			}
			if(policyKey.length()<4 && accountActivity.equalsIgnoreCase("linkAccounts") )
			{
				LawyersUtils.populateError(ctx, "singalAccountActivity","You need to select other account to perform linking.");
				return;
			}
				if(accountActivity==null |accountActivity.equals(""))
				{
				LawyersUtils.populateError(ctx, "noAccountActivity","Please choose type of activity you need to perform with selected accounts.");
				return;
				}
				
		}
		
	} catch (Exception e) {
		logger.error("Unable to validate account management data", e);
		logger.error("Unexpected error", e);
	}
	
}

public static void populateAccountsList(Context ctx)
{
	boolean flag=false;
try
{
	if((ctx.get("MergeStateCode")!=null && !"".equals(ctx.get("MergeStateCode").toString())) ||
			(ctx.get("PolicyEffectiveMonthYear")!=null && !"".equals(ctx.get("PolicyEffectiveMonthYear").toString())))
			{
				if(ctx.get("AccountNameSearch")!=null && !"".equals(ctx.get("AccountNameSearch").toString()))
					flag=true;
				if(ctx.get("AccountEmailSearch")!=null && !"".equals(ctx.get("AccountEmailSearch").toString()))
					flag=true;
				if(ctx.get("AccountNumberSearch")!=null && !"".equals(ctx.get("AccountNumberSearch").toString()))
					flag=true;
				if(ctx.get("WorkPhoneSearch")!=null && !"".equals(ctx.get("WorkPhoneSearch").toString()))
					flag=true;
				
				if(flag!=true)
				{
					LawyersUtils.populateError(ctx, "noMandetoryFiletr","State or Effective date can't be used independently, one more additional filter is required");
					return;
				}
			
					
			}
	flag=false;
	if(ctx.get("AccountNameSearch")!=null && !"".equals(ctx.get("AccountNameSearch").toString()))
		flag=true;
	if(ctx.get("AccountEmailSearch")!=null && !"".equals(ctx.get("AccountEmailSearch").toString()))
		flag=true;
	if(ctx.get("AccountNumberSearch")!=null && !"".equals(ctx.get("AccountNumberSearch").toString()))
		flag=true;
	if(ctx.get("WorkPhoneSearch")!=null && !"".equals(ctx.get("WorkPhoneSearch").toString()))
		flag=true;
	if(ctx.get("MergeStateCode")!=null && !"".equals(ctx.get("MergeStateCode").toString()))
		flag=true;
	if(ctx.get("PolicyEffectiveMonthYear")!=null && !"".equals(ctx.get("PolicyEffectiveMonthYear").toString()))
		flag=true;
	if(flag==true)
	{
		 new SetParametersForStoredProcedures().setParametersInContext(ctx, "AccountNameSearch,AccountNumberSearch,AccountEmailSearch,PolicyEffectiveMonthYear,WorkPhoneSearch,MergeStateCode");
			List policyAccountsList =(List)SqlResources.getSqlMapProcessor(ctx).select("Policy.AccountManagementSearchLW_p", ctx);
			if(policyAccountsList.size()>0)
			{
				ctx.put("AccountsSearch_list_01",policyAccountsList);
				ctx.put("SearchlistSize",policyAccountsList.size());
			}
	}
}
catch(Exception e)
{
logger.error("Unable to populate account list data", e);
}
}


public static void processPaymentACH(IContext ctx){
	HttpServletResponse response = (HttpServletResponse) ctx.get("DocumentResponse");
	String achURL = "";
	try {
		achURL = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".ach.url");
		response.sendRedirect(achURL);
	} catch (Exception e) {
		logger.error("Unexpected error", e);		
	}    		
}

public static void isPaymentDetailExist(IContext ctx){
	List paymentDetailList = null;
	try {
		paymentDetailList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPaymentDetail", ctx);
		if(paymentDetailList != null && paymentDetailList.size() > 0){
			Map map = (Map) paymentDetailList.get(0);
			ctx.put("PaymentDetailId",map.get("PaymentDetailId"));
			ctx.put("TransactionSequence",map.get("TransactionSequence"));
			ctx.put("isPaymentDetailExist","Y");			
		} else {
			ctx.remove("isPaymentDetailExist");
		}
		ctx.put("TransactionDate",ctx.get("LastUpdateTimeStamp"));		
		
	} catch (Exception e) {
		logger.error("Unexpected error", e);		
	} 
}

public static void processFinanceData(IContext ctx){	
	try {
		if(ctx.get("IPFSFinanceQurMon") != null && "IPFSFinanceQuarterly".equals(ctx.get("IPFSFinanceQurMon"))){
			ctx.put("FinanceInterval", "Quarterly");//Q
		} else {
			ctx.put("FinanceInterval", "Monthly");//M
		}
		
		if(ctx.get("IPFSFinanceCCACHMC") != null && "IPFSFinanceCCACH".equals(ctx.get("IPFSFinanceCCACHMC"))){
			ctx.put("FinanceDownPayment", "EFT");
		} else if(ctx.get("IPFSFinanceCCACHMC") != null && "IPFSFinanceManualCheck".equals(ctx.get("IPFSFinanceCCACHMC"))){
			ctx.put("FinanceDownPayment", "ManualCheck");
		}
		ctx.put("Amount", ctx.get("PremiumAmount"));
		ctx.put("TransactionStatus", "Pending");
		
    	logger.debug("Payment Detail has been updated by FinanceInterval and FinanceDownPayment...");	
		
	} catch (Exception e) {
		logger.error("Unexpected error", e);		
	} 
}

	public static int compareDates(String Date1,String Date2)
	{	  try
		  {
	        SimpleDateFormat     sdfo= new SimpleDateFormat("yyyy-MM-dd"); 
	        Date d1 = sdfo.parse(Date1); 
	        Date d2 = sdfo.parse(Date2); 
	        return d1.compareTo(d2);
		  }
		  catch(Exception e)
		  {
			  logger.error("Unable to compare dates", e);
			  logger.error("Unexpected error", e);
			  return 5;
		  }
	}


	
public static void insertDataForCopiedPolicy(Context ctx) throws Exception {
		
		boolean oldFlow = false,newFlow=false;
		Object obj=null;
	
		 obj = ctx.get("CopyPolicyData_list");
		
		if (obj != null) {
			List list = (List) obj;

			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);

				ctx.put("PolicyKey", map.get("PolicyKey"));
				ctx.put("VersionSequence", map.get("VersionSequence"));
				ctx.put("TransactionSequence", map.get("TransactionSequence"));
				ctx.put("CoverageSequence", map.get("CoverageSequence"));
				ctx.put("AccountKey", map.get("AccountKey"));
				ctx.put("AddressKey", map.get("AddressKey"));
			}
		}
		
		Object objPolicy = SqlResources.getSqlMapProcessor(ctx).findByKey("Policy.findByKey", ctx);
		if(objPolicy != null)
			ctx.putAll((Map)objPolicy);
		
		Map getFirmDetailsRequotePolicy = (Map)SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetFirmDetailsRequotePolicy", ctx);
		if(getFirmDetailsRequotePolicy != null)
			ctx.putAll(getFirmDetailsRequotePolicy);
		
	/*	Object objPolCov = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyCoverageRenewal", ctx);
		List polCovList = null;
		if(objPolicy != null){
			polCovList = (List) objPolCov;
			Map mapPolCov = (Map) polCovList.get(0);
			ctx.putAll(mapPolCov);
		}	*/		
		
		/*Object oldObj = RuleUtils.executeRule(ctx, "LawyersRule.AssignClaimExpensesAndDollarDefense");   
        if(ctx.get("IsClaimExpensesType") != null){
              SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateCoverageDataClaimExpenseTypeRenewal",ctx);
        }
        if(ctx.get("IsDollarDefense") != null){
              SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateCoverageDataDollarDefenseRenewal",ctx);
        }*/
		
        
	}

public static void generateReportForRequote(Context ctx)  {
	try {

		
		HttpServletResponse response = (HttpServletResponse) ctx.get("DocumentResponse");
		
		
		String excelname="RequoteList.xls";
		String filePath =SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".ratingexceldownloadpath");
		filePath=filePath+excelname;
		String reportHeaders=ctx.get("ReportHeaders").toString();
		ArrayList<String> headerlist= new ArrayList<String>(Arrays.asList(reportHeaders.split(",")));
		String[] sheetHeaders=new String[headerlist.size()];
		for(int i=0;i<sheetHeaders.length;i++)
			sheetHeaders[i]=headerlist.get(i);
		
		/*String requestXml="<request><requestdata><inet_project_id>LawyersIns</inet_project_id><filterCriteria><data><PolicyEffectiveDateFrom>06/01/2018</PolicyEffectiveDateFrom><SubProducerNumber></SubProducerNumber><SubProduced>N</SubProduced><Limit>100000/300000</Limit><StateCode>AL</StateCode><PolicyType>NB</PolicyType><PolicyEffectiveDateTo>12/18/2018</PolicyEffectiveDateTo></data></filterCriteria></requestdata></request>";
		ctx.put("inputXml", requestXml);*/
		logger.debug("Going to execute the stored procedure BatchRatingForLawyers_Proc");
		new SetParametersForStoredProcedures().setParametersInContext(ctx, "EffectiveDateFrom,EffectiveDateTo,stateCodes,aopCodes,requote_numberOfAttorneys,reQuoteURL,requoteTransactionType,requote_dircectSubproduced");
		List list = (List)SqlResources.getSqlMapProcessor(ctx).select("FirmLW.RequoteReportDataLW_p", ctx);
		logger.debug("BatchRatingForLawyers_Proc has been executed");
		logger.debug(list.size());
		
		generateReportSheet(ctx, "Data", list,excelname,sheetHeaders);
		
		LawyersBatch.FormatExcel(filePath);
		
	}catch(Exception e) {
		logger.error("Unexpected error", e);
		
	}
}

public static void generateReportSheet(Context ctx, String sheetName, List list,String excelname,String[] sheetHeaders) throws Exception {
	
	String path =SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".ratingexceldownloadpath");
	
	path=path+excelname;
	String path1 = (String) ctx.get(path);
	Workbook workbook = null;
	int rows; // No of rows
	String value = "";	
	Map <String, String>valuesMap = null;
	List headerList = new ArrayList();
	Row row;
	//XSSFRow row;
    Cell cell = null;
    Sheet sheet;
    String key = "";
    String val = "";
    
   try (FileOutputStream fileOut = new FileOutputStream(new File(path))) {
          workbook = new HSSFWorkbook();
          sheet = workbook.createSheet(sheetName);
		 // headers.clear();
		  if(list == null)
			  return;
		  
		  CellStyle style = workbook.createCellStyle();
		  Font font = workbook.createFont();
		  font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		  style.setFont(font);
		    
		  if(list.size() >= 1){
			  Row headerRow = sheet.createRow(0);	
			  for(int j=0; j<sheetHeaders.length; j++){
				  cell = headerRow.createCell(j);
		          cell.setCellValue(sheetHeaders[j]);
		          cell.setCellStyle(style);
		          sheet.autoSizeColumn(j);    
			  }
		  }
			  
		  	int rowNum = 1;
	        for(int j=0; j<list.size(); j++) {
	            row = sheet.createRow(rowNum++);		            
	            Map valueMap = (Map) list.get(j);
	         // System.out.println("PolicyKey :"+valueMap.get("PolicyKey"));
	           RatingBatch.callingRatingEngine(valueMap);
	            for(int k=0; k<sheetHeaders.length; k++){
	            	
	            	key = sheetHeaders[k];
	            	if(valueMap.get(key) != null)
	            		val = valueMap.get(key).toString();
	            	else
	            		val = "";
	            	row.createCell(k).setCellValue(val);
	            }
	            sheet.autoSizeColumn(j);
	            
	        }
	        
	        workbook.write(fileOut);
	       
	} catch (Exception e) {
		logger.error("Unable to write file", e);
	}
	
}


public static void decrytPolicyKeyFromDB(Context context)
{
	try
	{
		
		Map obj = (Map) SqlResources.getSqlMapProcessor(context).findByKey("SqlStmts.UserStatementManualBoQueriesgetDecryptedPolicyKeyAndEffectiveDateFromDB", context);
		if(obj != null && obj instanceof Map)
		{
			context.put("PolicyKey",obj.get("decryptedPolicyKey"));
			context.put("requotePolicyEffectiveDate",obj.get("decryptedEffectiveDate"));
		}
		if("RequotePolicy".equals(context.get("inet_page").toString()))
			context.put("moduleName", "Requote");
	}
	catch(Exception e)
	{ logger.error("Unable to prepare requote policy", e); }

}
public static void displayEndorsementOptions(Context ctx)
{
	logger.debug("loading Endorsement options");
try
{
	new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey");
	 List endorsementOptionsList =(List)SqlResources.getSqlMapProcessor(ctx).select("FirmLW.EndorsementOptionsDisplayLW_p", ctx);
	 if(endorsementOptionsList!=null && endorsementOptionsList.size()>0)
			ctx.putAll((Map) endorsementOptionsList.get(0));
	
}
catch(Exception e)
{
	logger.error("Unexpected error", e);
logger.error("Error occured while displaying Endorsement Options");	
}
}
public  void calculateClaimAgeRequote(Context ctx) throws Exception {
	DBUtils.executeDBOperation(ctx, "ClaimAgeLW", "2");
	int policyStatusKey = ctx.get("PolicyStatusKey") != null ? Integer.parseInt(ctx.get("PolicyStatusKey").toString()):0;
	//Object objPolicy = SqlResources.getSqlMapProcessor(ctx).findByKey("Policy.findByKey", ctx);
	Map poliMap = new HashMap();
	poliMap.put("PolicyKey", ctx.get("PolicyKey"));
	poliMap.put("PolicyEffectiveDate", ctx.get("PolicyEffectiveDate"));
	
	// To check if IsFirmCarryingProfLiabilityIns equal Y/N
	String IsFirmCarryingProfLiabilityIns = "N";
	Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetIsFirmCarryingProfLiabilityIns",ctx);
	if(obj != null && obj instanceof Map){
		Map map = (Map) obj;
		IsFirmCarryingProfLiabilityIns = map.get("IsFirmHaveLawyersLiabilityInsurance") == null ? "N" : map.get("IsFirmHaveLawyersLiabilityInsurance").toString();
	}
	
	int firmStepRateYears = 0;		
	/*if("Y".equals(IsFirmCarryingProfLiabilityIns))
		firmStepRateYears = calculateFirmStepRateYears(ctx);*/
	
	Object objProLiability = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.coverageviewgetProfessionalLiabilityInsDetailLW",ctx);		
	if (objProLiability != null && objProLiability instanceof Map) {
		Map proLiabInsuranceMap = (Map) objProLiability;

		String isPriorActDateFull = proLiabInsuranceMap.get("IsPriorActDateFull") != null ? proLiabInsuranceMap.get("IsPriorActDateFull").toString() : null;
		if(policyStatusKey==1)
		{
		if(IsFirmCarryingProfLiabilityIns.equals("N"))
			proLiabInsuranceMap.put("PriorActDatePross", ctx.get("PolicyEffectiveDate"));
		}
		if (isPriorActDateFull != null && "Y".equals(isPriorActDateFull)) {
			firmStepRateYears = Integer.parseInt(proLiabInsuranceMap.get("FirmYear").toString());
			

		} else {

			//Object objPolicy1 = SqlResources.getSqlMapProcessor(ctx).findByKey("Policy.findByKey", ctx);

			//Map poliMap1 = objPolicy1 != null && objPolicy1 instanceof Map ? (Map) objPolicy1: null;

			firmStepRateYears = daysDifferentInEffectiveAndPriorDate(proLiabInsuranceMap, poliMap);

		}
	}
	
	
	

	Object objAttorney = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.firmviewgetAttorneysList1", ctx);
	if (objAttorney != null && objAttorney instanceof List) {
		List attorneyList = (List) objAttorney;
		for (int i = 0; i < attorneyList.size(); i++) {
			Map attornMap = (Map) attorneyList.get(i);
			int attorneyStepRateYears = calculateAttorneyStepRateYears(attornMap, poliMap);	
			/*if("N".equals(IsFirmCarryingProfLiabilityIns) && firmStepRateYears == 0)
				firmStepRateYears = attorneyStepRateYears;*/
				
			int claimAge = getClaimAge(attorneyStepRateYears, firmStepRateYears);	
			
			saveClaimAgeDataFullQuote(ctx, claimAge);

		}
	}
}
	public static void uploadClaimSummaryReport(Context ctx) throws Exception
	{
		logger.debug("going to upload claims report");

		String uploaddirectory = SystemProperties.getInstance().getString("fileupload.uploaddirectory");
		
		String filePath = LawyersUtilities.uploadFile(ctx, uploaddirectory);
		
		try
		{
			readExcelFile(ctx, new File(uploaddirectory + filePath));
		}
		catch(Exception e)
		{
			logger.error("Unable to upload report", e);
		} finally{
			if(!filePath.isEmpty())
				LawyersUtilities.fileDelete(uploaddirectory, filePath);
		}
	}
	
	public static void readExcelFile(Context ctx, File file){
		try {
		    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
		    HSSFWorkbook wb = new HSSFWorkbook(fs);
		    HSSFSheet sheet = wb.getSheetAt(0);
		    HSSFRow row;
		    HSSFCell cell;
	        List inputList=new ArrayList();

		    int rows; // No of rows
		    rows = sheet.getPhysicalNumberOfRows();

		    int cols = 0; // No of columns
		    int tmp = 0;
		    boolean flag = false;

		    // This trick ensures that we get the data properly even if it doesn't start from first few rows
		    for(int i = 0; i < 10 || i < rows; i++) {
		        row = sheet.getRow(i);
		        if(row != null) {
		            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
		            if(tmp > cols) cols = tmp;
		        }
		    }
		    List<String> columnsList = new ArrayList();
		    for(int r = 0; r < 1; r++) {
		        row = sheet.getRow(r);
		        boolean result=checkIfRowIsEmpty(row);
		        if(!result) {
		            for(int c = 0; c < cols; c++) {
		                cell = row.getCell((short)c);
		                if(cell != null) {
		                	if("AccDescPart3Text".equals((cell + "").replaceAll("[^a-zA-Z0-9]", ""))){
		                		ctx.put("ClaimReportDescriptionColumn", "AccDescPart3Text");
		                		flag = true;
		                	}
		                	columnsList.add((cell + "").replaceAll("[^a-zA-Z0-9]", ""));
		                }
		            }
		        }
		    }
		    String[] columnsDescription = new String[columnsList.size()];
		    columnsDescription = columnsList.toArray(columnsDescription);
		   
		    for(int r = 1; r < rows; r++) {
		        row = sheet.getRow(r);
		        boolean result=checkIfRowIsEmpty(row);
		        if(!result) {
				    Map map = new HashMap();
				    String companyCode = "";
		            for(int c = 0; c < cols; c++) {
		                cell = row.getCell((short)c);
		                if(cell != null) {
		                	if("CompanyCode".equals(columnsDescription[c])){
		                		companyCode = cell + "";
		                		map.put(columnsDescription[c], removeSpecialSymbolsFromExcel(cell));
		                	} else if("PolicyNumber".equals(columnsDescription[c])){
		                		if("CNF".equals(companyCode)) {
		                			map.put(columnsDescription[c], (cell.toString().length() > 9) ? cell.toString().replace(".", "").substring(0, 9) : cell.toString());
		                		} else if("ISMIE".equals(companyCode)) {
			                		map.put(columnsDescription[c], removeSpecialSymbolsFromExcel(cell));
		                		}
		                	} else if(("ClaimNumber".equals(columnsDescription[c]) || "AccDescPart3Text".equals(columnsDescription[c])) && flag){
		                		map.put(columnsDescription[c], removeSpecialSymbolsFromExcel(cell));
		                	} else if(flag == false){
		                		map.put(columnsDescription[c], removeSpecialSymbolsFromExcel(cell));
		                	}
		                }
		            }
					inputList.add(map);
		        }
		    }
		    
		    if(inputList.size()>0){
				ctx.put("inputList",inputList);
				convertListDataToXML(ctx,"inputList","outputList");
			}
		} catch(Exception ioe) {
		    logger.error("Unexpected error", ioe);
		}
	}
	
	public static boolean checkIfRowIsEmpty(Row row) {
		 if (row == null) {
		        return true;
		    }
		    if (row.getLastCellNum() <= 0) {
		        return true;
		    }
		    for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
		        Cell cell = row.getCell(cellNum);
		        if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK && StringUtils.isNotBlank(cell.toString())) {
		            return false;
		        }
		    }
		    return true;
	}
	public static Object removeSpecialSymbolsFromExcel(Object obj){
		obj  = obj.toString().replace("'", "''");
		return obj;
	}
	public static boolean validateProtexureHoldSubproducer(Context ctx)
	{
		Map dataMap=null;
		boolean errorFlag=false;
		try
		{
			String producerCode=null;
			List transactionDetails = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetProducerCodeForPolicy",ctx);
			if(transactionDetails != null && !transactionDetails.isEmpty() && transactionDetails.get(0) instanceof Map)
			{
				dataMap=(Map) transactionDetails.get(0);
				 producerCode=dataMap.get("ProducerCode")!=null?dataMap.get("ProducerCode").toString():"";
			}
			String onholdProducerCode=SystemProperties.getInstance().getString("appl.LawyersIns.onhold.ProducerCode");
			if(onholdProducerCode != null && onholdProducerCode.equals(producerCode))
			{
			populateError(ctx, "policyInsuranceValidation","The Sub-Producer Number is not valid for policy issuance.");
			errorFlag=true;
			}
		}
		catch(Exception e)
		{
			logger.error("Exception occured in validateProtexureHoldSubproducer method", e);
		}
		return errorFlag;
	}
	public static void getPolicyFormsList(Context ctx)
	{
		try
		{
			StringBuilder newForms,priorForms=null;
			newForms=new StringBuilder(); 
			Map map=null;
			List newPolicyFormsList=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementautomationQuerriesgetNewPolicyForms",ctx);
			if(newPolicyFormsList !=null && newPolicyFormsList instanceof List)
			{
				for(int i=0;i<newPolicyFormsList.size();i++)
				{
					map=(Map) newPolicyFormsList.get(i);
					newForms.append(map.get("FormID"));
					if(i<newPolicyFormsList.size())
					newForms.append(",");
				}
			}
			if(newForms !=null)
				ctx.put("newPolicyForms", newForms.toString().trim());
			
			
			
			Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementautomationQuerriesgetPriorFormsEffectiveDate", ctx);
			if(obj != null && obj instanceof Map)
				ctx.putAll((Map)obj);
			
			priorForms =new StringBuilder(); 
			Context newCtx=new Context();
			newCtx.setProject(ctx.getProject());
			newCtx.put("PolicyKey_form", ctx.get("PolicyKey") !=null ? ctx.get("PolicyKey").toString(): ctx.get("PolicyKey"));
			newCtx.put("StateCode_form", ctx.get("StateCode") != null ? ctx.get("StateCode").toString() : ctx.get("StateCode"));
			newCtx.put("PolicyEffectiveDate_form", ctx.get("priorEffectivedate") != null ? ctx.get("priorEffectivedate").toString() : ctx.get("priorEffectivedate"));
			newCtx.put("IsNewFiling_form", ctx.get("IsNewFiling") != null ? ctx.get("IsNewFiling").toString() : "N");
			
			logger.debug("Going to execute the stored procedure for Form Attachment");
			List priorListForm = SqlResources.getSqlMapProcessor(newCtx).select("PolicyFormLW.LawyersFormAttachmentAuto_p", newCtx);
			if(priorListForm !=null && priorListForm instanceof List)
			{
				for(int i=0;i<priorListForm.size();i++)
				{
					map=(Map) priorListForm.get(i);
						priorForms.append(map.get("FormID"));
					if(i<priorListForm.size())
						priorForms.append(",");
				}
			}
			if(priorForms !=null)
				ctx.put("priorPolicyForms", priorForms.toString().trim());
			
			logger.debug("SP has been execute for the Form Attachment");
			
		}
		catch(Exception e)
		{
		logger.error("Exception occured while putting list of New Forms and Prior Policy Form");	
		}
	}

	public static void copyFile(String source, String destination)
			   throws IOException
			{
		File sourcePath=new File(source);
		File destinationPath=new File(destination);
			   // Open file to be copied
			   try (InputStream in = new FileInputStream(sourcePath);
					   OutputStream out = new FileOutputStream(destinationPath)) {
				   byte[] buf = new byte[1024];
				   int len;
				   while ((len = in.read(buf)) >= 0)
				   {
				      out.write(buf, 0, len);
				   }
			   }
			}
	
	public static String getCurrentDate()
	{
		Date date= new Date(System.currentTimeMillis());
		return DateUtils.convertDateToString(date,"MM/dd/yyyy");
	}

	public static void validateQuoteAddition(Context ctx)
	{
		logger.debug("validating quotes for duplicacy.");
		try
		{
			if(ctx.get("inet_method").equals("saveQuote"))
			{
				 new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,LimitSequence,DeductibleSequence,CoverageKey,IsClaimExpensesType,IsDollarDefense,IsClaimOptionType,IsManualPremium");
				 List validateQuoteData =(List)SqlResources.getSqlMapProcessor(ctx).select("FirmLW.ValidateQuoteAddition_p", ctx);
				 if(validateQuoteData!=null && validateQuoteData.size()>0)
						ctx.putAll((Map) validateQuoteData.get(0));
				 //saveQuote
				 String duplicateQuote=ctx.get("duplicateQuote")!=null?ctx.get("duplicateQuote").toString():"N";
				if("Y".equals(duplicateQuote))
				{
					populateError(ctx, "duplicateQuoteError","Requested quote already exist");
				}
			}
			
		}
		catch(Exception e)
		{
			logger.error("Error ocured while validating quotes for duplicacy.");
			
		}
		
	}
	
	public static void uploadEmailNotificationsContant(Context ctx) throws Exception
	{
		logger.debug("going to upload claims report");
	
		String uploaddirectory = SystemProperties.getInstance().getString("fileupload.uploaddirectory");
		
		String filePath = LawyersUtilities.uploadFile(ctx, uploaddirectory);
		
		try
		{
			convertWordToHTML(ctx, new File(uploaddirectory + filePath));
		}
		catch(Exception e)
		{
			logger.error("Unable to upload report", e);
		} finally{
			if(!filePath.isEmpty())
				LawyersUtilities.fileDelete(uploaddirectory, filePath);
		}
	}
	
	public static void convertWordToHTML(Context ctx, File file){
		
		try {
			DocumentConverter doc = new DocumentConverter();
			Result<String> r;
			try (InputStream ins = new FileInputStream(file)) {
				r = doc.convertToHtml(ins);
			}
			
			String html = r.getValue();
			html = html.replace("Gotovideo", "{VideoURL}");
			html = html.replace("RenewHere", "{AppURL}");
			ctx.put("TemplateBody", html);
			if("edit".equals(ctx.get("inet_eventid")))
				ctx.put("UploadUpdateTemplateDocument", "YES");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e);
		}
	}
	
	public static void validateAopDateUpdated(Context ctx)
	{
		try
		{
			Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetAOPPercentageSum", ctx);
			if(obj != null && obj instanceof Map)
			{
				ctx.putAll((Map)obj);
			}
			int percentageSum=ctx.get("percentageSum")!=null && !ctx.get("percentageSum").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("percentageSum").toString()):0;
			if(percentageSum==0)
				ctx.put("isDataChange","Y");
			/*else
				ctx.put("isAopDataSaved","Y");*/
		}
		catch(Exception e )
		{
			logger.error("Exception occured while calculation Aop percentage sum");
		}
	}
	public static void validateCoverageModifiersForURFlow(Context ctx)
	{
		try
		{
			Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetCocerageModifiers", ctx);
			if(obj != null && obj instanceof Map)
			{
				ctx.putAll((Map)obj);
			}
			int previousModifier=ctx.get("PreviousModifier")!=null && !ctx.get("PreviousModifier").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("PreviousModifier").toString()):0;
			int SchduleRatingModifier1=ctx.get("SchduleRatingModifier1")!=null && !ctx.get("SchduleRatingModifier1").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("SchduleRatingModifier1").toString()):0;
			int SchduleRatingModifier2=ctx.get("SchduleRatingModifier2")!=null && !ctx.get("SchduleRatingModifier2").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("SchduleRatingModifier2").toString()):0;
			int SchduleRatingModifier3=ctx.get("SchduleRatingModifier3")!=null && !ctx.get("SchduleRatingModifier3").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("SchduleRatingModifier3").toString()):0;
			int SchduleRatingModifier4=ctx.get("SchduleRatingModifier4")!=null && !ctx.get("SchduleRatingModifier4").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("SchduleRatingModifier4").toString()):0;
			int SchduleRatingModifier5=ctx.get("SchduleRatingModifier5")!=null && !ctx.get("SchduleRatingModifier5").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("SchduleRatingModifier5").toString()):0;
			int SchduleRatingModifier6=ctx.get("SchduleRatingModifier6")!=null && !ctx.get("SchduleRatingModifier6").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("SchduleRatingModifier6").toString()):0;
			int totalModifiers=SchduleRatingModifier1+SchduleRatingModifier2+SchduleRatingModifier3+SchduleRatingModifier3+SchduleRatingModifier4+SchduleRatingModifier5+SchduleRatingModifier6;
			if(totalModifiers>previousModifier)
				ctx.put("resetRatingFlag","N");
			else
				ctx.put("resetRatingFlag","Y");
		}
		catch(Exception e)
		{
			logger.error("Exception occured in validateCoverageModifiersForURFlow method");
		}
	}
	public List manageAttoneyEndorsementChanges(Context ctx)
	{
		
		ArrayList additionalAttorneys=new ArrayList();
		int attorneyDataListSize=0,newAttorneyListSize=0;;			
		try
		{
			List attorneyOldDataList  =(List) SqlResources.getSqlMapProcessor(ctx).select("AttorneyDetailsLW.GetAttorneyData_p",ctx);
			if(attorneyOldDataList!=null && attorneyOldDataList instanceof List)
				attorneyDataListSize=attorneyOldDataList.size();
			
			List newAttorneyDataList=(List)ctx.get("AttorneysDetailsList_list_01");
			if(newAttorneyDataList == null)
			{
				ctx.put("isUpdateAttorneyList","N");
				return additionalAttorneys;
			}
			newAttorneyListSize=newAttorneyDataList.size();
			
			
			List statesList=new ArrayList();
			 boolean modifyLincensedStates=false;
		        String licensedStates=	ctx.get("newLicensedStates")!=null && !ctx.get("newLicensedStates").equals(HtmlConstants.EMPTY)?ctx.get("newLicensedStates").toString():"";
		       if(!"".equals(licensedStates))
		       {
			        StringTokenizer stok = new StringTokenizer(licensedStates,"#");
					String tokens[] = new String[stok.countTokens()];
				    for(int i=0; i<tokens.length; i++){
				    	statesList.add(stok.nextToken());
				    }
		       }
			if(attorneyDataListSize==newAttorneyListSize)
			{
				if(attorneyOldDataList!=null)
				{
				Map map1=null;
				for(int i=0;i<attorneyOldDataList.size();i++)
				{
					map1=(HashMap)attorneyOldDataList.get(i);
					if(!map1.get("AttorneyName").toString().equals(ctx.get("AttorneyName_"+i).toString())
					||	!map1.get("DesignationId").toString().equals(ctx.get("DesignationId_"+i).toString())
					||	!map1.get("AnnualWorkedHours").toString().equals(ctx.get("AnnualWorkedHours_"+i).toString())
					||	!map1.get("AttorneyPriorActDate").toString().equals(ctx.get("AttorneyPriorActDate_"+i).toString())
					||	!map1.get("NumberOfYearsInPractice").toString().equals(ctx.get("NumberOfYearsInPractice_"+i).toString())
					||	!map1.get("LicensedStates").toString().equals(statesList.get(i).toString()) )
						
					{
	         		map1.put("AttorneyName", ctx.get("AttorneyName_"+i));
	 				map1.put("DesignationId",ctx.get("DesignationId_"+i));
	 				if(statesList!=null && statesList.size()>0)
	 				map1.put("LicensedStates",statesList.get(i));
	 				map1.put("AnnualWorkedHours",ctx.get("AnnualWorkedHours_"+i));
	 				map1.put("AttorneyPriorActDate", ctx.get("AttorneyPriorActDate_"+i));
	 				map1.put("TransactionEffectiveDate",ctx.get("endorsementEffectiveDate"));
					map1.put("EffectiveDateOfPolicy",ctx.get("PolicyEffectiveDate"));
	 				map1.put("NumberOfYearsInPractice",ctx.get("NumberOfYearsInPractice_"+i));
	 				map1.put("TransactionEffectiveDate",ctx.get("endorsementEffectiveDate"));
					map1.put("EffectiveDateOfPolicy",ctx.get("PolicyEffectiveDate"));
						
						additionalAttorneys.add(map1);
					}
					
				}
				}
				
			}
			if(newAttorneyListSize>attorneyDataListSize)
			{
				int sizeDiff=newAttorneyListSize-attorneyDataListSize;
				for(int i=0;i<sizeDiff;i++)
				{
					Map map=new HashMap();
					map.put("AttorneyKey",null);
	        		map.put("AttorneyName", null);
	 				map.put("DesignationId", null);
	 				map.put("LicensedStates",null);
	 				map.put("AnnualWorkedHours",null);
	 				map.put("AttorneyPriorActDate", null);
	 				map.put("PriorActDateAttorneysNew",null);
	 				map.put("NumberOfYearsInPractice",null);
	 				map.put("combinedStates",null);
	 				attorneyOldDataList.add(map);
	 			
				}
				

				Map map1=null;
				for(int i=0;i<attorneyOldDataList.size();i++)
				{
					map1=(HashMap)attorneyOldDataList.get(i);
					if(map1.get("AttorneyKey")==null)
					{
						map1.put("TransactionEffectiveDate",ctx.get("endorsementEffectiveDate"));
						map1.put("EffectiveDateOfPolicy",ctx.get("PolicyEffectiveDate"));
						map1.put("AttorneyName", ctx.get("AttorneyName_"+i));
		 				map1.put("DesignationId",ctx.get("DesignationId_"+i));
		 				map1.put("LicensedStates",statesList.get(i));
		 				map1.put("AnnualWorkedHours",ctx.get("AnnualWorkedHours_"+i));
		 				map1.put("AttorneyPriorActDate", ctx.get("AttorneyPriorActDate_"+i));
		 				map1.put("TransactionEffectiveDate",ctx.get("endorsementEffectiveDate"));
						map1.put("EffectiveDateOfPolicy",ctx.get("PolicyEffectiveDate"));
		 				map1.put("NumberOfYearsInPractice",ctx.get("NumberOfYearsInPractice_"+i));
						additionalAttorneys.add(map1);
					}
					else
					{
					if(
					!map1.get("AttorneyName").toString().equals(ctx.get("AttorneyName_"+i).toString())
					||	!map1.get("DesignationId").toString().equals(ctx.get("DesignationId_"+i).toString())
					||	!map1.get("AnnualWorkedHours").toString().equals(ctx.get("AnnualWorkedHours_"+i).toString())
					||	!map1.get("AttorneyPriorActDate").toString().equals(ctx.get("AttorneyPriorActDate_"+i).toString())
					||	!map1.get("NumberOfYearsInPractice").toString().equals(ctx.get("NumberOfYearsInPractice_"+i).toString())
					||	!map1.get("LicensedStates").toString().equals(statesList.get(i).toString())
					//||	!map1.get("AttorneyKey").toString().equals(ctx.get("AttorneyName_"+i).toString())
							)
						
					{
	         		map1.put("AttorneyName", ctx.get("AttorneyName_"+i));
	 				map1.put("DesignationId",ctx.get("DesignationId_"+i));
	 				map1.put("LicensedStates",statesList.get(i));
	 				map1.put("AnnualWorkedHours",ctx.get("AnnualWorkedHours_"+i));
	 				map1.put("AttorneyPriorActDate", ctx.get("AttorneyPriorActDate_"+i));
	 				map1.put("TransactionEffectiveDate",ctx.get("endorsementEffectiveDate"));
					map1.put("EffectiveDateOfPolicy",ctx.get("PolicyEffectiveDate"));
	 				map1.put("NumberOfYearsInPractice",ctx.get("NumberOfYearsInPractice_"+i));
	 			//	map1.put("combinedStates",map.get("LicensedStates_"+i));
						
						additionalAttorneys.add(map1);
					}
					}
				
					
				}
				
				
			}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
		finally
		{
			return additionalAttorneys;
		}
	}
	public static void setQuoteExpirationDate(Context ctx)
	{
		try
		{
			 new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey");
			 List dataList =(List)SqlResources.getSqlMapProcessor(ctx).select("Quote.FillQuoteExpiryDateLW_p", ctx);
			 if(dataList!=null && dataList instanceof List)
					ctx.putAll((Map) dataList.get(0));
		}
		catch(Exception e)
		{
			logger.error("Exception occured while setting quote expiry date");
		}
	}
	
	public static void updateLoginDetalis(IContext ctx)
	{
		try
		{
			SqlResources.getSqlMapProcessor(ctx).insert("UserSessionLogin.insert", ctx);
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
			logger.error("Exception occured while updateLoginDetalis");
		}
	}
	
	
	
	
	
	public void sendTemplatesMail(IContext ctx) throws Exception {
		
	
		String body = (String) ctx.get("TemplateBody");
		String TemplateName = (String) ctx.get("TemplateName");
		
		
		 body=body.replace("{EmailSignature}", "<br/><br/><b>{SignatureText}</b><br/><b>T:  {SPPhoneNumber}</b><br/><b>F:  {Fax}</b><br/><b>{SPWebsite}</b><br/><br/></td></tr></table>");
		 body=body.replace("{ContactPerson}","Test123");
		 body=body.replace("{PolicyExpirationDate}","08/08/2020");
		 body=body.replace("{ProducerName}", "Protexure Lawyers");
		 body=body.replace("{SignatureText}", "Protexure Lawyers");
		 body=body.replace("{SPPhoneNumber}", "877-569-4111");
		 body=body.replace("{Fax}", "(440) 333-3214");
		 body=body.replace("{SPWebsite}", "www.protexurelawyers.com");
		 body=body.replace("{VideoURL}",SystemProperties.getInstance().getString("appl.LawyersIns.videourl"));
		 body=body.replace("{AppURL}",SystemProperties.getInstance().getString("appl.LawyersIns.AppURL"));
		 body=body.replace("{StateDesc}", "Colorado");
		 String dstId = "";
		// Do not send quote letter mail to insured if producer code is
		// there
		
		String to_RsEmail = SystemProperties.getInstance().getString("mail.RSEmail.to.address");
		String cc_RsEmail = SystemProperties.getInstance().getString("mail.RSEmail.cc.address");
		
		logger.debug("Mail is going---- "); 
		MailSender mail = new MailSender();
		mail.setToAdd(to_RsEmail);
		mail.setCcAdd(cc_RsEmail);
		mail.setSubject("Review for (" +TemplateName+") letter");
		mail.setContentType("text/html");
		//mail.setBody(generateTemplatesMailBody(ctx));
		mail.setBody(body);
		mail.sendMail((Context) ctx);
		logger.debug("Mail has sent---- ");

	}

	
	
	
	
public static void sendErrorEmail(Context ctx,String eventName)
{
	Map map = null;
	try
	{
			ctx.put("event_name", eventName);
		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementdroolQueriesgetEmailNotificationData", ctx);
		if (obj != null && obj instanceof Map) 
			map = (Map) obj;
		else
			map = new HashMap();
		
		String subject=map.get("subject")!=null?map.get("subject").toString():"EMPTY";
		String body=map.get("body")!=null?map.get("body").toString():"EMPTY";
		String to_role_desc=SystemProperties.getInstance().getString("appl.errorEmail.toAddress");
		String cc_role_desc=SystemProperties.getInstance().getString("appl.errorEmail.ccAddress");
		ctx.put("isSendMailTrue","Y");
		map.put("to_role_desc", to_role_desc);
		map.put("cc_role_desc", cc_role_desc);
		//SubproducerMailer.sendEmailAsSubProducer(ctx);
		sendEmailNotification(ctx,map);
	
	}
	catch(Exception e)
	{
		logger.error("Unexpected error", e);
	}
}



public  void calculateClaimAgeRerate(Context ctx) throws Exception {
	DBUtils.executeDBOperation(ctx, "ClaimAgeLW", "2");
	
	//Object objPolicy = SqlResources.getSqlMapProcessor(ctx).findByKey("Policy.findByKey", ctx);
	Map poliMap = new HashMap();
	poliMap.put("PolicyKey", ctx.get("PolicyKey"));
	poliMap.put("PolicyEffectiveDate", ctx.get("PolicyEffectiveDate"));
	
	int firmStepRateYears = 0;		
	
	Object objProLiability = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.coverageviewgetProfessionalLiabilityInsDetailLW",ctx);		
	if (objProLiability != null && objProLiability instanceof Map) {
		Map proLiabInsuranceMap = (Map) objProLiability;

		String isPriorActDateFull = proLiabInsuranceMap.get("IsPriorActDateFull") != null ? proLiabInsuranceMap.get("IsPriorActDateFull").toString() : null;
		if (isPriorActDateFull != null && "Y".equals(isPriorActDateFull)) 
			firmStepRateYears = Integer.parseInt(proLiabInsuranceMap.get("FirmYear").toString());
		else 
			firmStepRateYears = daysDifferentInEffectiveAndPriorDate(proLiabInsuranceMap, poliMap);

	}
	
	Object objAttorney = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.firmviewgetAttorneysList1", ctx);
	if (objAttorney != null && objAttorney instanceof List) {
		List attorneyList = (List) objAttorney;
		for (int i = 0; i < attorneyList.size(); i++) {
			Map attornMap = (Map) attorneyList.get(i);
			int attorneyStepRateYears = calculateAttorneyStepRateYears(attornMap, poliMap);	
			/*if("N".equals(IsFirmCarryingProfLiabilityIns) && firmStepRateYears == 0)
				firmStepRateYears = attorneyStepRateYears;*/
				
			int claimAge = getClaimAge(attorneyStepRateYears, firmStepRateYears);	
			
			saveClaimAgeDataFullQuote(ctx, claimAge);

		}
	}
}
public static void getClaimSummaryDetails(Context ctx)
{
	try
	{
		new SetParametersForStoredProcedures().setParametersInContext(ctx, "ProfLiabClaimAgnstFirmKey");
		List claimSuppData =(List)SqlResources.getSqlMapProcessor(ctx).select("ProfLiabClaimAgnstFirmLW.FetchClaimSummaryDetailsLW_p", ctx);
		if(claimSuppData!=null && claimSuppData.size()>0)
		ctx.putAll((Map) claimSuppData.get(0));
	}
	catch(Exception e)
	{
		logger.error("Unexpected error", e);
	}
}

public static void getBrokerPolicyDetails(Context ctx)
{
	try
	{
		boolean flag = false;
		Object brokerageDataUpdate = RuleUtils.executeRule(ctx, "LawyersRule.isBrokerageDataUpdate");
		if (brokerageDataUpdate != null && brokerageDataUpdate instanceof Boolean) {
			flag = (Boolean) brokerageDataUpdate;
		}
		
		 if(flag)
		 {
			 Context newCtx=new Context();
				newCtx.setProject(ctx.getProject());
				newCtx.putAll(ctx);
				String accountName=ctx.get("AccountName")!=null && !ctx.get("AccountName").equals(HtmlConstants.EMPTY) ?ctx.get("AccountName").toString():"N";
				accountName=accountName.replace("'","''" );
				newCtx.put("AccountName", accountName);
				
				if(ctx.get("Address")!=null)
				{
				String address=ctx.get("Address").toString();
				address=address.replace("'","''" );
				newCtx.put("Address", address);
				}
				if(ctx.get("Address2")!=null)
				{
				String address2=ctx.get("Address2").toString();
				address2=address2.replace("'","''" );
				newCtx.put("Address2", address2);
				}
		 	new SetParametersForStoredProcedures().setParametersInContext(newCtx, "CarrierKey,BrokerPolicyEffectiveDate,BrokerPolicyExpirationDate,AccountName,Address,State,City,Zip,Phone,Email,LOBKey,BrokerKey,Commission,ExpiringPolicyNumber,StatusKey,Premium,PremiumTax,AccountID,TransactionTypeKey,Fees,Address2");
			List brokeragePolicyDataList =(List)SqlResources.getSqlMapProcessor(newCtx).select("BrokeragePolicy.GetBrokeragePolicyData_p", newCtx);
			
			if(brokeragePolicyDataList!=null && brokeragePolicyDataList.size()>0)
			ctx.putAll((Map) brokeragePolicyDataList.get(0));
		 }
		
		
		if(ctx.get("PolicyKey")!=null && ctx.get("PolicyKey").equals(HtmlConstants.EMPTY))
			ctx.put("PolicyKey", null);
		if(ctx.get("BrokeragePolicyKey")!=null && ctx.get("BrokeragePolicyKey").equals(HtmlConstants.EMPTY))
			ctx.put("BrokeragePolicyKey", null);
		if(ctx.get("TransactionTypeKey")!=null && ctx.get("TransactionTypeKey").equals(HtmlConstants.EMPTY))
			ctx.put("TransactionTypeKey", null);
		new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,BrokeragePolicyKey,isNewBrokerPolicy,TransactionTypeKey,RenewAsBrokerage,MarketableProductKey,LastUpdateUserID");
		List claimSuppData =(List)SqlResources.getSqlMapProcessor(ctx).select("BrokeragePolicy.GetBrokerPolicyDetailsLW_p", ctx);
		if(claimSuppData!=null && claimSuppData.size()>0)
		ctx.putAll((Map) claimSuppData.get(0));
		
	}
	catch(Exception e)
	{
		logger.error("Unexpected error", e);
		logger.error("Error occured while fetching details of broker poliy");
	}
}

	public void getTemplatesCode(IContext ctx) throws Exception {
		
		String email_template_id = "";
		List getMaxEMAIL_Template_ID_List = (List)SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetMaxEMAIL_Template_ID", ctx);
		
		if(getMaxEMAIL_Template_ID_List != null && getMaxEMAIL_Template_ID_List.size() > 0 && !getMaxEMAIL_Template_ID_List.isEmpty()){
			Map getMaxEMAIL_Template_ID = (Map)getMaxEMAIL_Template_ID_List.get(0);
			email_template_id = (getMaxEMAIL_Template_ID.get("EmailTemplateID") == null ? "" : getMaxEMAIL_Template_ID.get("EmailTemplateID").toString());
		}
		ctx.put("TemplateCode","TEMP_"+(Integer.parseInt(email_template_id)+1));
	
	}

	public static void saveWillsTrustMattersAmountsData(Context ctx)
	{
			
            List inputList=new ArrayList();
            try
            {
            	HashMap map=null;
	            List finalList = new ArrayList();
	            if(ctx.get("WillsTrustAmount_list_01") != null &&   ctx.get("WillsTrustAmount_list_01") instanceof List)
	                finalList=(List)ctx.get("WillsTrustAmount_list_01");
	            
	            int j=0;
	            Map reqMap = new HashMap();
	            Context newCtx=new Context();
	            newCtx.setProject(ctx.getProject());
	            newCtx.putAll(ctx);
	            
	    		for (int i = 0; i < finalList.size(); i++) 
	    		{
	    			map=new HashMap();
					map.put("AmountRangeKey", ctx.get("AmountRangeKey_"+i));
					map.put("NumberOfMattersValued", ctx.get("NumberOfMattersValued_"+i));
					map.put("PolicyKey",ctx.get("PolicyKey"));
					map.put("VersionSequence",ctx.get("VersionSequence"));
					map.put("VersionKey",ctx.get("VersionKey"));
					if(ctx.get("CreatedBy_"+i)==null || ctx.get("CreatedBy_"+i).equals(HtmlConstants.EMPTY))
						map.put("CreatedBy", ctx.get("LastUpdateUserID"));
					else
						map.put("CreatedBy", ctx.get("CreatedBy_"+i));
					
					if(ctx.get("CreatedDate_"+i)==null || ctx.get("CreatedDate_"+i).equals(HtmlConstants.EMPTY))
						map.put("CreatedDate",ctx.get("LastUpdateTimeStamp"));
					else
						map.put("CreatedDate",ctx.get("CreatedDate_"+i));
					inputList.add(map);
	    				
	    		}
	    		newCtx.put("inputList",inputList);
				LawyersUtils.convertListDataToXML(newCtx,"inputList","outputList");
				ctx.put("outputList", newCtx.get("outputList"));		
	    		
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
	}
	public static void getWillTrustMattersValued(Context ctx)
	{
			try 
			{
				
				int numberOfMattersValued=0;
				List aoplist = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetWillTrustAmountDetails", ctx);
				Map covMap = new HashMap();
				if (aoplist != null && aoplist.size()>0) 
				{
					Map map = new HashMap();
					for (int i = 0; i < aoplist.size(); i++) 
					{
						map = (HashMap) aoplist.get(i);
						numberOfMattersValued=map.get("NumberOfMattersValued")!=null && !map.get("NumberOfMattersValued").equals(HtmlConstants.EMPTY)?Integer.parseInt(map.get("NumberOfMattersValued").toString()):0;
						ctx.put("NumberOfMattersValued_" + map.get("AmountRangeKey"),numberOfMattersValued );
						
					}
				}
				
			}
			catch (Exception e)
			{
				logger.error("Unexpected error", e);
			}
	}
	
	public static void validateBrokeragePolicy(Context ctx)
	{
		boolean flag = false;
		try
		{
			String  comingFrom=ctx.get("comingFrom")!=null && !ctx.get("comingFrom").equals(HtmlConstants.EMPTY)?ctx.get("comingFrom").toString():"Other";
			Object obj = RuleUtils.executeRule(ctx,"LawyersRule.isInetErrorListEmpty");
			if (obj instanceof Boolean)
				flag = (Boolean) obj;
		
			if ("addEditBrokeragePolicy".equals(comingFrom) && flag==true) 
				ctx.put("isShowDocUpload","Y");
		
		}
		catch(Exception e)
		{ logger.error("Unable to evaluate brokerage policy display", e); }
	}
	
	public static void setBrokerageRecordsListPre(IContext ctx){//recordsperpage  pagenumber
		
		try{
			logger.debug("In setBrokerageRecordsListPre method logging start ");
			double searchListCount = 0;
			ctx.put("LastUpdateUserID", String.valueOf(ctx.get("UserNo")));
			

			double recordsperpage = (ctx.get("recordsperpage") == null || "".equals(ctx.get("recordsperpage")))? 20.0 : Double.parseDouble(ctx.get("recordsperpage").toString());
			double pagenumber = (ctx.get("pagenumber") == null || "".equals(ctx.get("pagenumber")))? 1.0 : Double.parseDouble(ctx.get("pagenumber").toString());

			ctx.put("PageSize",(int) recordsperpage);
			ctx.put("PageNumber",(int) pagenumber);
			List brokeragePolicyList = SqlResources.getSqlMapProcessor(ctx).select("BrokeragePolicy.FetchBrokeragePoliciesCountLW_p", ctx);
			if(brokeragePolicyList != null && brokeragePolicyList.size() > 0){
				Map map = (Map) brokeragePolicyList.get(0);
				searchListCount = Double.parseDouble(map.get("BrokeragePolicyCount").toString());
			}
			
			String paginationaction = (ctx.get("paginationaction") == null) ? "" : ctx.get("paginationaction").toString();
			String inetmethod = (ctx.get("inet_method") == null) ? "" : ctx.get("inet_method").toString();
			String pageSort = (ctx.get("PageSort") == null) ? "" : ctx.get("PageSort").toString();
			String pageSortType = (ctx.get("page_sort_type") == null) ? "" : ctx.get("page_sort_type").toString();
			
			if("previouspage".equals(paginationaction)){
				pagenumber = pagenumber - 1;
			} else if("nextpage".equals(paginationaction)){
				pagenumber = pagenumber + 1;
			} else if("firstpage".equals(paginationaction) || "setrecordcount".equals(paginationaction)){
				pagenumber = 1;
			} else if("lastpage".equals(paginationaction)){
				pagenumber = Math.ceil(searchListCount / recordsperpage);
			}
			int searchListCountI = (int) searchListCount;
			int recordsperpageI = (int) recordsperpage;
			int pagenumberI = (int) pagenumber;
//			if("sorting".equals(inetmethod)){
//				recordsperpageI = 0;
//				pagenumberI = 1;
//			}
			
			ctx.put("PageSize",recordsperpageI);
			ctx.put("PageNumber",pagenumberI);
			ctx.put("searchListCount",searchListCountI);
			ctx.put("PageSort",pageSort);
			ctx.put("page_sort_type",pageSortType);
//			ctx.put("recordsperpage",recordsperpageI);
//			ctx.put("pagenumber",pagenumberI);
		}catch(Exception e){
			logger.error("Unexpected error", e);
		}
	}
	public static void setBrokerageRecordsListPost(IContext ctx){//recordsperpage  pagenumber
		List brokeragePolicyListold = null;
		List brokeragePolicyListNew = new ArrayList();
//		int size = 0;
		if (ctx.get("BrokeragePolicyList_list_01") != null) {
			brokeragePolicyListold = (List) ctx.get("BrokeragePolicyList_list_01");
//			size = attorneyListold.size();
		}
		double searchListCount = (ctx.get("searchListCount") != null || !"".equals(ctx.get("searchListCount")))?Double.parseDouble(ctx.get("searchListCount").toString()):20;
		double recordsperpage = (ctx.get("recordsperpage") == null || "".equals(ctx.get("recordsperpage")))? 20.0 : Double.parseDouble(ctx.get("recordsperpage").toString());
		double pagenumber = (ctx.get("pagenumber") == null || "".equals(ctx.get("pagenumber")))? 1.0 : Double.parseDouble(ctx.get("pagenumber").toString());
		String paginationaction = (ctx.get("paginationaction") == null) ? "" : ctx.get("paginationaction").toString();
		String inetmethod = (ctx.get("inet_method") == null) ? "" : ctx.get("inet_method").toString();
		
		if("previouspage".equals(paginationaction)){
			pagenumber = pagenumber - 1;
		} else if("nextpage".equals(paginationaction)){
			pagenumber = pagenumber + 1;
		} else if("firstpage".equals(paginationaction) || "setrecordcount".equals(paginationaction)){
			pagenumber = 1;
		} else if("lastpage".equals(paginationaction)){
			pagenumber = Math.ceil(searchListCount / recordsperpage);
		}
		int searchListCountI = (int) searchListCount;
		int recordsperpageI = (int) recordsperpage;
		int pagenumberI = (int) pagenumber;
//		if("sorting".equals(inetmethod)){
//			recordsperpageI = 0;
//			pagenumberI = 1;
//		}
		
		int listCountOld = 0;
		Map m = new HashMap();
		for(int listCount = 0; listCount < searchListCountI; listCount++){
			if((listCount >= ((recordsperpageI * pagenumberI)-recordsperpageI) && listCount < (recordsperpageI * pagenumberI)) || (recordsperpageI == 0)){
				brokeragePolicyListNew.add(brokeragePolicyListold.get(listCountOld++));
			} else {
				brokeragePolicyListNew.add(m);
			}
		}
		
		ctx.put("BrokeragePolicyList_list_01",brokeragePolicyListNew);
	}
	
	public static void saveUpdateBrokeragePolicyDetails(Context ctx)
	{
		try
		{
			logger.debug("going to save brokerage policy data");
			Context newCtx=new Context();
			newCtx.setProject(ctx.getProject());
			newCtx.putAll(ctx);
			String accountName=ctx.get("AccountName")!=null && !ctx.get("AccountName").equals(HtmlConstants.EMPTY) ?ctx.get("AccountName").toString():"N";
			accountName=accountName.replace("'","''" );
			newCtx.put("AccountName", accountName);
			
			if(ctx.get("Address")!=null)
			{
			String address=ctx.get("Address").toString();
			address=address.replace("'","''" );
			newCtx.put("Address", address);
			}
			if(ctx.get("Address2")!=null)
			{
			String address2=ctx.get("Address2").toString();
			address2=address2.replace("'","''" );
			newCtx.put("Address2", address2);
			}
			new SetParametersForStoredProcedures().setParametersInContext(newCtx, "BrokeragePolicyKey,CarrierKey,BrokerPolicyEffectiveDate,AccountName,Address,State,City,Zip,Phone,Email,LOBKey,haveProfessionalLiabilityInsurance,PLEffectiveDate,PLExpirationDate,PLPolicyNumber,ContractComments,ExpiringCarrier,BrokerKey,Commission,ExpiringPolicyNumber,StatusKey,Premium,PremiumTax,LastUpdateUserID,LastUpdateTimeStamp,BrokeredPolicyNumber,BrokerPolicyExpirationDate,AccountID,ExpiringPremium,ExpiringPolicyEffectiveDate,ExpiringPolicyExpirationDate,TransactionTypeKey,Fees,TotalCalculatedPremium,BrokeragePaymentType,Address,RenewAsBrokerage,PolicyKey,isConvertedPolicy,ProducerCode,LimitSequence,DeductibleSequence,IsClaimExpensesType,IsClaimOptionType,IsDollarDefense");
			SqlResources.getSqlMapProcessor(newCtx).update("BrokeragePolicy.BrokeragePolicyDetailsLW_p", newCtx);
						
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
			logger.error("Error occured while saving brokerage policy data");
		}
	}
	/*public static void getAppData(Context ctx)
	{
		try
		{
		
				OutputStream out = null;
				File file = null;
				String htmlDir = SystemProperties.getInstance().getString(
						"html.basedir");
				String baseUrl = null;
				if (ctx.get("baseUrl") != null)
					baseUrl = ctx.get("baseUrl").toString();

				ServletContextURIResolver uriResolver = null;
				if (ctx.get("uriResolver") != null)
					uriResolver = (ServletContextURIResolver)ctx.get(HtmlConstants.DOCUMENTURIRESOLVER);
				
				
				List dataList =  SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetSpoildData",ctx);
				if (dataList != null) {
					for (int i = 0; i < dataList.size(); i++)
					{
						Map map = (HashMap) dataList.get(i);
						ctx.putAll(map);
						
							String outFile = htmlDir + "data//Lawyers_"+ ctx.get("QuoteNumber").toString() + ".pdf";
							out = new java.io.FileOutputStream(outFile);
							out = new java.io.BufferedOutputStream(out);
				
							if (ctx.get("PolicyStatusKey") != null
									&& "2".equals(ctx.get("PolicyStatusKey").toString())) {
								new DownloadPDF().makeRenewPdf((Context) ctx, out, baseUrl, uriResolver);
							} else {
								new DownloadPDF().makePdf((Context) ctx, out, baseUrl, uriResolver);
							}
				
					}
				}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/
	
	public static void ValidateBrokeragePolicyNumberPrefix(Context ctx)
	{

		try {

			new SetParametersForStoredProcedures().setParametersInContext(ctx,
					"CarrierKey,LOBKey,BrokeredPolicyNumber");
			List validatePageData = (List) SqlResources.getSqlMapProcessor(ctx).select("BrokeragePolicy.ValidateBrokeragePolicyNumberPrefix_p", ctx);
			if (validatePageData != null && validatePageData.size() > 0)
				ctx.putAll((Map) validatePageData.get(0));

			if ("False".equals(ctx.get("isValidPolicyNumber").toString()))
			{
				populateError(ctx, "PolicyNumberPrefixError",ctx.get("BrokeragePolicyPrefixErrorMessage").toString());
			
			return;
			}
		} catch (Exception etab) {
			logger.error("error ocured at ValidateBrokeragePolicyNumberPrefix method "+ etab.getMessage());
			logger.error("Unexpected error", etab);
		}
	}
public static void getPolicyData(Context ctx)
{
	logger.debug("goinng to set companycode as per user role");
	try{
				
		List getPolicyData=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetPolicyUpdatedData",ctx);
		ctx.putAll((Map) getPolicyData.get(0));
		
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
		
}
/*
 * public static void validateAndSetLoginSecurityForUser(Context ctx) { try {
 * List getCompanyCode =(List)SqlResources.getSqlMapProcessor(ctx).select(
 * "UserDetails.UserLoginSecurity_p", ctx); ctx.putAll((Map)
 * getCompanyCode.get(0)); } catch(Exception e) {
 * logger.error("error ocured at setting company code as per user role method "+
 * e.getMessage()); e.printStackTrace(); } }
 */


private static String getMapStringValue(Map map, String key) {
	Object value = map.get(key);
	if (value == null || value.equals(HtmlConstants.EMPTY))
		return "";
	return value.toString().trim();
}

private static int getMapIntValue(Map map, String key, int defaultValue) {
	String value = getMapStringValue(map, key);
	if ("".equals(value))
		return defaultValue;
	return Integer.parseInt(value);
}

private static int parseISMIEPolicySequence(String policyNumber) throws Exception {
	int index = policyNumber.indexOf("-");
	int second = policyNumber.indexOf("-", index + 1);
	int third = policyNumber.indexOf("-", second + 1);
	if (index < 0 || second < 0 || third < 0)
		throw new Exception("Invalid ISMIE policy number format: " + policyNumber);
	return Integer.parseInt(policyNumber.substring(second + 1, third));
}

private static String padISMIEPolicySequence(int policyNo) {
	String initialPolicyNo = new Integer(policyNo).toString();
	while (initialPolicyNo.length() < ISMIE_POLICY_SEQUENCE_LENGTH)
		initialPolicyNo = "0" + initialPolicyNo;
	return initialPolicyNo;
}

private static String formatISMIESuffix(int suffixNo) {
	String suffix = new Integer(suffixNo).toString();
	if (suffix.length() == 1)
		suffix = "0" + suffix;
	return suffix;
}

private static int getLastGeneratedISMIESuffix(String basePolicyNumber) {
	Object lastSuffix = lastGeneratedISMIESuffixByBasePolicyNumber.get(basePolicyNumber);
	if (lastSuffix == null)
		return 0;
	return Integer.parseInt(lastSuffix.toString());
}

public static synchronized String generatePolicyNumberISMIE(IContext ctx) throws Exception {

	/*
	 * String prefix = "ALA-04",suffix=""; Map map=null;; String initialPolicyNo =
	 * null; int comapnyAccountCount=0,policyStatusKey=0;
	 * 
	 * Context newCtx=new Context(); newCtx.setProject(ctx.getProject());
	 * newCtx.put("PolicyKey", ctx.get("PolicyKey"));
	 * newCtx.put("MarketableProductKey", ctx.get("MarketableProductKey")); List
	 * dataList =(List)SqlResources.getSqlMapProcessor(newCtx).select(
	 * "Policy.GetDataForPolicyNumber_p", newCtx); if(dataList!=null &&
	 * dataList.size()>0) map=(Map) dataList.get(0);
	 * 
	 * if (map.get("PolicyNumber") != null) { String PolicyNumber =
	 * map.get("PolicyNumber").toString();
	 * logger.debug("LAST MAX POLICY NUMBER:-"+PolicyNumber+" LAST UPDATED AT"
	 * +System.currentTimeMillis()); int index = PolicyNumber.indexOf("-"); int
	 * second = PolicyNumber.indexOf("-", index + 1); int third =
	 * PolicyNumber.indexOf("-", second + 1); int policyNo=
	 * Integer.parseInt(PolicyNumber.substring(second+1 ,third))+1; initialPolicyNo
	 * = new Integer(policyNo).toString(); int limit=6; int
	 * initialPolicyNoLength=initialPolicyNo.length(); for(int
	 * i=0;i<limit-initialPolicyNoLength;i++) initialPolicyNo="0"+initialPolicyNo; }
	 * if (initialPolicyNo == null) initialPolicyNo =
	 * SystemProperties.getInstance().getString( "appl." + ctx.getProject() +
	 * ".ISMIE.initialPolicyNo"); policyStatusKey=map.get("policyStatusKey")!=null
	 * && !map.get("policyStatusKey").equals(HtmlConstants.EMPTY)
	 * ?Integer.parseInt(map.get("policyStatusKey").toString()):0;
	 * comapnyAccountCount=map.get("comapnyAccountCount")!=null &&
	 * !map.get("comapnyAccountCount").equals(HtmlConstants.EMPTY)
	 * ?Integer.parseInt(map.get("comapnyAccountCount").toString()):0;
	 * 
	 * if(policyStatusKey==1) suffix="01"; else if(policyStatusKey==2 &&
	 * comapnyAccountCount==1) suffix="01"; else { suffix=new
	 * Integer(comapnyAccountCount).toString(); if(suffix.length()==1)
	 * suffix="0"+suffix;
	 * 
	 * }
	 * 
	 * logger.debug("NEWLY GENERATED POLICY NUMBER:-"+prefix + "-" + initialPolicyNo
	 * + "-"+ suffix+" LAST UPDATED AT"+System.currentTimeMillis()); return prefix +
	 * "-" + initialPolicyNo + "-"+ suffix;
	 */
	
	
	
	String prefix = ISMIE_POLICY_PREFIX, suffix = "", newPolicyNumber = "";
	Map map = null;
	int comapnyAccountCount = 0, policyStatusKey = 0, oldSuffix = 0;
	
	Context newCtx=new Context();
	newCtx.setProject(ctx.getProject());
	newCtx.put("PolicyKey", ctx.get("PolicyKey"));
	newCtx.put("MarketableProductKey", ctx.get("MarketableProductKey"));
	List dataList =(List)SqlResources.getSqlMapProcessor(newCtx).select("Policy.GetDataForPolicyNumber_p", newCtx);
	if(dataList!=null && dataList.size()>0 && dataList.get(0) instanceof Map)
		map=(Map) dataList.get(0);
	if (map == null)
		throw new Exception("Unable to fetch policy number data for PolicyKey " + ctx.get("PolicyKey"));
	
	policyStatusKey = getMapIntValue(map, "policyStatusKey", 0);
	comapnyAccountCount = getMapIntValue(map, "comapnyAccountCount", 0);
	
	if(policyStatusKey==1 || (policyStatusKey==2 && comapnyAccountCount==0))
	{
		String PolicyNumber = getMapStringValue(map, "PolicyNumber");
		int policyNo = 0;
		if (!"".equals(PolicyNumber)) {
			logger.debug("LAST MAX POLICY NUMBER:-"+PolicyNumber+" LAST UPDATED AT"+System.currentTimeMillis());
			policyNo = parseISMIEPolicySequence(PolicyNumber) + 1;
		} else {
			String initialPolicyNo = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".ISMIE.initialPolicyNo");
			if (initialPolicyNo == null || "".equals(initialPolicyNo.trim()))
				throw new Exception("Missing ISMIE initial policy number configuration");
			policyNo = Integer.parseInt(initialPolicyNo.trim());
		}
		if (lastGeneratedISMIEPolicySequence >= policyNo)
			policyNo = lastGeneratedISMIEPolicySequence + 1;

		boolean isPolicyNumberAvailable = false;
		for (int i = 0; i < ISMIE_MAX_SUFFIX_ATTEMPTS; i++) {
			newPolicyNumber = prefix + "-" + padISMIEPolicySequence(policyNo) + "-01";
			newCtx.put("newPolicyNumber", newPolicyNumber);
			if (!validateIfPolicynumberExists(newCtx)) {
				isPolicyNumberAvailable = true;
				break;
			}
			policyNo++;
		}
		if (!isPolicyNumberAvailable)
			throw new Exception("Unable to generate available ISMIE policy number");
		lastGeneratedISMIEPolicySequence = policyNo;
	}
	else{
		String basePolicyNumber = getMapStringValue(map, "LastIssuedPoliyNumberForAccountWithoutSuffix");
		if ("".equals(basePolicyNumber))
			throw new Exception("Unable to fetch last issued ISMIE policy number for PolicyKey " + ctx.get("PolicyKey"));

		oldSuffix = getMapIntValue(map, "OldSuffix", 0) + 1;
		int lastGeneratedSuffix = getLastGeneratedISMIESuffix(basePolicyNumber);
		if (lastGeneratedSuffix >= oldSuffix)
			oldSuffix = lastGeneratedSuffix + 1;

		boolean isPolicyNumberAvailable = false;
		for (int i = 0; i < ISMIE_MAX_SUFFIX_ATTEMPTS; i++) {
			suffix = formatISMIESuffix(oldSuffix);
			newPolicyNumber = basePolicyNumber + "-" + suffix;
			newCtx.put("newPolicyNumber", newPolicyNumber);
			if (!validateIfPolicynumberExists(newCtx)) {
				lastGeneratedISMIESuffixByBasePolicyNumber.put(basePolicyNumber, new Integer(oldSuffix));
				isPolicyNumberAvailable = true;
				break;
			}
			oldSuffix++;
		}

		if (!isPolicyNumberAvailable)
			throw new Exception("Unable to generate available ISMIE policy number suffix for " + basePolicyNumber);
	}
		
		
	logger.debug("NEWLY GENERATED POLICY NUMBER:-"+newPolicyNumber+" LAST UPDATED AT"+System.currentTimeMillis());
	return newPolicyNumber;
}

public static boolean validateIfPolicynumberExists(Context ctx)
{ 
	boolean result=true;
	try {
		
		new SetParametersForStoredProcedures().setParametersInContext(ctx, "newPolicyNumber");
		List validatePolicyData =(List)SqlResources.getSqlMapProcessor(ctx).select("Policy.ValidatePolicynumber_P", ctx);
		
		if(validatePolicyData!=null && validatePolicyData.size()>0)
		ctx.putAll((Map) validatePolicyData.get(0));
		result=ctx.get("result")!=null && !ctx.get("result").equals(HtmlConstants.EMPTY)?Boolean.parseBoolean(ctx.get("result").toString()):false;
		
	} catch (Exception e) {
		logger.error("Unexpected error", e);
	}
	
	return result;
}
public static void setCompanyForPolicy(Context ctx) throws Exception 
{
	try {
		new SetParametersForStoredProcedures().setParametersInContext(ctx, "StateCode,MarketableProductKey,PolicyEffectiveDate");
		String lookupKey = companyLookupKey(ctx);
		if (restoreCompanyLookup(ctx, lookupKey))
			return;

		ctx.remove("CompanyKey");
		ctx.remove("CompanyAsPerState");
		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("Policy.GetCompanyList_p", ctx);
		if (obj != null && obj instanceof Map) {
			Map objMap = (Map) obj;
			ctx.putAll(objMap);
			cacheCompanyLookup(ctx, lookupKey, objMap);
		}
	} catch(Exception e) {
		logger.error("Unexpected error", e);
	}
}

static String companyLookupKey(Context ctx) {
	return String.valueOf(ctx.get("StateCode")) + "|"
			+ String.valueOf(ctx.get("MarketableProductKey")) + "|"
			+ String.valueOf(ctx.get("PolicyEffectiveDate"));
}

static boolean restoreCompanyLookup(Context ctx, String lookupKey) {
	Object cachedResult = ctx.get(COMPANY_LOOKUP_CACHE_RESULT);
	if (!lookupKey.equals(ctx.get(COMPANY_LOOKUP_CACHE_KEY)) || !(cachedResult instanceof Map))
		return false;

	ctx.putAll((Map) cachedResult);
	return true;
}

static void cacheCompanyLookup(Context ctx, String lookupKey, Map result) {
	ctx.put(COMPANY_LOOKUP_CACHE_KEY, lookupKey);
	ctx.put(COMPANY_LOOKUP_CACHE_RESULT, new HashMap(result));
}

public static void executeUnderwritingRules(Context ctx)
{
	
		logger.debug("going to execute undewriter rules");
			
		 try {
			 new SetParametersForStoredProcedures().setParametersInContext(ctx, UNDERWRITING_RULE_PARAMS);
			 SqlMapProcessor sqlProcessor = SqlResources.getSqlMapProcessor(ctx);
			 for (int i = 0; i < UNDERWRITING_RULE_STATEMENTS.length; i++) {
				 executeUnderwritingRule(sqlProcessor, ctx, UNDERWRITING_RULE_STATEMENTS[i]);
			 }

			 boolean isElectronicInsuranceTab = false;
			 Object objElectronicInsuranceTabRule = RuleUtils.executeRule(ctx, "LawyersRule.isElectronicInsuranceImplDate");
			 if (objElectronicInsuranceTabRule != null && objElectronicInsuranceTabRule instanceof Boolean) {
				 isElectronicInsuranceTab = (Boolean) objElectronicInsuranceTabRule;
			 }

			 if (isElectronicInsuranceTab) {
				 executeUnderwritingRule(sqlProcessor, ctx, "rulepolicytransaction.UWRules_ElectronicsSelectionRejection_LW");
			 }
		} catch (Exception e19) {
			logger.error("Error while preparing underwriting rules: " + e19);
			logger.error("Unexpected error", e19);
		}
		 logger.debug("Execution of undewriter rules has been completed");
}

public static boolean validatePolicyIssuanceForCompany(Context ctx) throws Exception 
{
	boolean result=false;
	int statusKey=0;
	String IsQuickQuote="";
	try {
			IsQuickQuote=ctx.get("IsQuickQuote")!=null? ctx.get("IsQuickQuote").toString():"NA";
			if(!"Y".equals(IsQuickQuote))
			 statusKey=ctx.get("StatusKey")!=null? Integer.parseInt(ctx.get("StatusKey").toString()):0;
			if("Y".equals(IsQuickQuote))
				statusKey=11;
			if(statusKey==9 || statusKey==10 || statusKey==3 || statusKey==7 || statusKey==11 )
			{
				setCompanyForPolicy(ctx);
				int companyKey=ctx.get("CompanyKey")!=null? Integer.parseInt(ctx.get("CompanyKey").toString()):0;
			
				if(companyKey==3)
					result=true;
			}
	} catch(Exception e) {
		logger.error("Unexpected error", e);
	}
	return result;
}
public static String getInsuredAccountEmail(Context ctx)
{
	logger.debug("going to fetch insured AccountEmail");
	String emailID="";
	try
	{
		 
		if (ctx.get("AccountEmail") != null)
		emailID = ctx.get("AccountEmail").toString();
		try{
			Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetFirmAddressDetails", ctx);
			if(obj != null && obj instanceof Map)
			{
				ctx.putAll((Map)obj);
			}
		} catch(Exception e) {
			logger.error("Error occured while fetching secondary email address of firm :"+e);
		}
		
		if(ctx.get("secondaryEmail") != null)
			emailID=emailID+","+ctx.get("secondaryEmail").toString();
	
	}
	catch(Exception e)
	{logger.error("Unable to fetch insured account email", e);}
	return emailID;
}

public static boolean isApplicationNotCompletlyFilled(Context ctx)
{	
	boolean isAppNotCompleted=false;
	 try {	
			//By Raghu CCB Supplement
		 if(ctx.get("ElectronicInsuranceImplDate") instanceof String) {
			 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		     Date parsedDate = dateFormat.parse(ctx.get("ElectronicInsuranceImplDate").toString());
			 ctx.put("ElectronicInsuranceImplDate", new Timestamp(parsedDate.getTime()));
		 }
			
			 	new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,AccountKey,litigationNewImplDate,IsCCBFlag,RenewalSupplementNewImplDate,isCannibSuppFlow,BankruptcySuppImplDate");
				List validatePageData =(List)SqlResources.getSqlMapProcessor(ctx).select("FirmLW.ValidateIndivisualPageCompletion_p", ctx);
				String isNotCompletlyFilled=null;
				if(validatePageData!=null && validatePageData.size()>0)
				{
					 Map map = (Map) validatePageData.get(0);					
					  isNotCompletlyFilled=!map.get("isNotCompletlyFilled").equals(HtmlConstants.EMPTY) && map.get("isNotCompletlyFilled")!=null?map.get("isNotCompletlyFilled").toString():"";
				}
				
				if("Y".equals(isNotCompletlyFilled))
					isAppNotCompleted=true;
	 }
	 catch(Exception e)
	 {
		 logger.error("Unable to validate application completion", e);
	 }
	 
	 return isAppNotCompleted;
 }

public static void validateAutoquoteFlow(Context ctx) {
	String IsReferRuleTriggered = "";
	try {
		new SetParametersForStoredProcedures().setParametersInContext(ctx, "InvoicedPremium,FullTimeEquivalent");
		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("rulepolicytransaction.AutoQuote_LW", ctx);
		Map keysMap = null;
		if (obj != null && obj instanceof Map) {
			keysMap = (Map) obj;
			ctx.putAll(keysMap);
		}

		IsReferRuleTriggered = ctx.get("IsReferRuleTriggered") != null
				&& !ctx.get("IsReferRuleTriggered").equals(HtmlConstants.EMPTY)
						? ctx.get("IsReferRuleTriggered").toString()
						: "N";
		if ("Y".equals(IsReferRuleTriggered)) {
			ctx.put("IsFirmReferred", "Y");
		} else {
			ctx.put("IsFirmReferred", "N");
		}
		// ctx.put("IsFirmReferred", "Y");
		SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesupdateFirmReferred",
				ctx);

	} catch (Exception e) {
		logger.error("error occured while executing isReferRuleTriggeredNewApp method" + e);
		logger.error("Unexpected error", e);
	}

}
private String generateAutoQuoteMailBody(IContext ctx) throws Exception {
	
	StringBuilder msg = new StringBuilder(512);
	msg.append("<table>");
	msg.append("<tr>");
	msg.append("<td>");
	msg.append("Hi Team,<br/><br/>");
	msg.append(ctx.get("QuoteNumber").toString()).append(", ").append(ctx.get("AccountName").toString()).append(" has been been handled by the auto quote functionality.<br/><br/>");

	msg.append("<b>Limits			:	</b>").append(ctx.get("LimitAmount").toString()).append("<br/>");
	msg.append("<b>Deductibles	:	</b>").append(ctx.get("DeductibleAmount").toString()).append("<br/>");
	msg.append("<b>Inside/Outside	:	</b>").append(ctx.get("ClaimExpensesTypeText").toString()).append("<br/>");
	msg.append("<b>Claim/Aggregate:	</b>").append(ctx.get("IsClaimOptionType").toString()).append("<br/>");
	msg.append("<b>Premium Amount	:	</b>").append(ctx.get("InvoicedPremium").toString()).append("<br/>");
	msg.append("<br/><br/> ");

	msg.append("Best Regards <br/>");
	msg.append("The Protexure Team.<br/>");

	msg.append("</td>");
	msg.append("</tr>");
	msg.append("</table>");

	return msg.toString();
}
public void sendAutoQuoteMail(IContext ctx) {
	try
	{
		Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetAccountDetailsForCompleteAppLink", ctx);

		if (obj != null && obj instanceof Map) {
			Map map = (Map) obj;
			ctx.putAll(map);
		}
		
		obj = SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementManualBoQueriesgetPremiumDetailsForAutoQuote", ctx);

		if (obj != null && obj instanceof Map) {
			Map map = (Map) obj;
			ctx.putAll(map);
		}

		String dstId =SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".autoquote.to.email");
		// Do not send quote letter mail to insured if producer code is
		// there
		
//			dstId = getEmailID(ctx);
		// String cc_role_desc =
		// SystemProperties.getInstance().getString("mail.admin.cc.addr");
		if ("".equals(dstId))
			return;
//		String bccAddress = "";
//		bccAddress = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".autoquote.cc.email");

		logger.debug("Mail is going---- ");
		MailSender mail = new MailSender();
		mail.setToAdd(dstId);
//		mail.setBccAdd(bccAddress);
		mail.setSubject( ctx.get("QuoteNumber").toString()+", "+ ctx.get("AccountName").toString()+" has been been handled by the auto quote functionality");
		mail.setContentType("text/html");
		mail.setBody(generateAutoQuoteMailBody(ctx));
		mail.sendMail((Context) ctx);
		logger.debug("Mail has sent---- ");
		SqlResources.getSqlMapProcessor(ctx).update("SqlStmts.UserStatementManualBoQueriesUpdateAutoQuoteEmailFlag",ctx);
		
		try{
			LawyersUtils.updateZOHORecords((Context) ctx);
		} catch(Exception e) {
			logger.error("Unable to update ZOHO record", e);
		}
	
	}
	catch(Exception e)
	{
		logger.error("Unable to send auto-quote email", e);
	}

}

public static boolean validateQuoteRatingStates2024(Context ctx)
{
	boolean result=false,result1=false,isSubproducerAccess=false;
	Object objRule=null;
	try
	{
		 objRule = RuleUtils.executeRule(ctx, "LawyersRule.isSubproducerAccess");
			if (objRule != null && objRule instanceof Boolean) 
				isSubproducerAccess = (Boolean) objRule;	
			
			objRule = RuleUtils.executeRule(ctx, "LawyersRule.isOtherStatesThanNewGroupRating1");
			if (objRule != null && objRule instanceof Boolean) 
				result1 = (Boolean) objRule;	
		
		if(isSubproducerAccess==false && result1==true)
			return true;
		else
		{
			objRule = RuleUtils.executeRule(ctx, "LawyersRule.QuoteRating1States");
			if (objRule != null && objRule instanceof Boolean)
			result = (Boolean) objRule;
			return result;
		}
	}
	catch(Exception e)
	{
		logger.error("Exception occured while executing rule for Quote leter"+e.getMessage());
	}
	return result;
	
}


public static void viewProtexureFormAttorneysData(Context ctx) {
	HttpServletRequest req = (HttpServletRequest) ctx.get("DocumentRequest");
	HttpSession sess = req.getSession();
	List finalList = null;
	int attorneyCount = ctx.get("addAttorneys") != null && !ctx.get("addAttorneys").equals(HtmlConstants.EMPTY)
			? Integer.parseInt(ctx.get("addAttorneys").toString())
			: 0;
	if (ctx.get("FeeSuitData_list_01") != null && ctx.get("FeeSuitData_list_01") instanceof List) {
		finalList = (List) ctx.get("FeeSuitData_list_01");
	}
	if (attorneyCount > 0 && sess.getAttribute("FeeSuitData_list_01") != null
			&& sess.getAttribute("FeeSuitData_list_01") instanceof List
			&& ((List) sess.getAttribute("FeeSuitData_list_01")).size() >= attorneyCount) {
		finalList = (List) sess.getAttribute("FeeSuitData_list_01");
		ctx.put("FeeSuitData_list_01", finalList);
	}
	if (ctx.get("FeeSuitData_list_01") == null)
		ctx.put("FeeSuitData_list_01", sess.getAttribute("FeeSuitData_list_01"));

	if (ctx.get("FeeSuitData_list_01") != null && ctx.get("FeeSuitData_list_01") instanceof List) {
		finalList = (List) ctx.get("FeeSuitData_list_01");
		int feesuitsCount = attorneyCount > 0 ? attorneyCount : finalList.size();
		if (feesuitsCount > finalList.size()) {

			int existingListSize = finalList.size();
			for (int i = existingListSize; i < feesuitsCount; i++) {
				Map map = new HashMap();
				map.put("AttorneyName", null);
				map.put("AnnualHours", null);
				map.put("AttorneyPriorActDate", null);
				map.put("EstimateFormAttorneyKey", null);
				finalList.add(map);

			}
		}
		else if (feesuitsCount < finalList.size()) {
			for (int i = finalList.size() - 1; i >= feesuitsCount; i--) {
				finalList.remove(i);
			}
		}
		ctx.put("addAttorneys", feesuitsCount);
		ctx.put("FeeSuitData_list_01", finalList);
		sess.setAttribute("FeeSuitData_list_01", finalList);

	}

}
 
public static void saveProtexureFormAttorneysData(Context ctx)
{
	logger.debug("Going to save attorneys for Estimate form");
	try
	{
		if (!(ctx.get("FeeSuitData_list_01") instanceof List))
			return;

		int size = hasProtexureEstimateValue(ctx.get("addAttorneys")) ? Integer.parseInt(ctx.get("addAttorneys").toString()) : 0;
		List inputList = new ArrayList(size);
		for (int i = 0; i < size; i++) {
			Object attorneyName = ctx.get("AttorneyName_" + i);
			Object annualHours = ctx.get("AnnualHours_" + i);
			Object attorneyPriorActDate = ctx.get("AttorneyPriorActDate_" + i);
			if (hasProtexureEstimateValue(attorneyName) && hasProtexureEstimateValue(annualHours)
					&& hasProtexureEstimateValue(attorneyPriorActDate)) {
				Map map = new HashMap();
				map.put("AttorneyName", attorneyName);
				map.put("AnnualHours", annualHours);
				map.put("AttorneyPriorActDate", attorneyPriorActDate);
				inputList.add(map);
			}
		}

		if (!inputList.isEmpty()) {
			Context newCtx = new Context();
			newCtx.setProject(ctx.getProject());
			newCtx.putAll(ctx);
			newCtx.put("inputList", inputList);
			LawyersUtils.convertListDataToXML(newCtx, "inputList", "outputList");
			ctx.put("outputList1", newCtx.get("outputList"));
		}
	}
    catch(Exception e)
    {logger.error("exception occured while saving Estimate form attorneys" + e.getMessage());
    }
}

public static void addNewProtexureFormAttorneys(Context ctx)
{
	 List finalList = null;
	 HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
     HttpSession sess = req.getSession();
	 List<Map<String,Object>> newList=new ArrayList<Map<String,Object>>();
     try
     {
    	 
	 if(ctx.get("FeeSuitData_list_01") != null &&   ctx.get("FeeSuitData_list_01") instanceof List)
     {
       
     	 finalList = (List)ctx.get("FeeSuitData_list_01");
     	Map map1=null,map2=null;
     	for (int i = 0; i <finalList.size(); i++)
     	 {
     		map1 = new HashMap();
     		map2=(HashMap)finalList.get(i);
     	 		map1.put("EstimateFormAttorneyKey", map2.get("EstimateFormAttorneyKey"));
     	 		map1.put("AttorneyName", ctx.get("AttorneyName_"+i));
				map1.put("AnnualHours",ctx.get("AnnualHourss_"+i));
				map1.put("AttorneyPriorActDate",ctx.get("AttorneyPriorActDate_"+i));
				map1.put("isSaved",map2.get("isSaved"));
				map1.put("ROWID",map2.get("ROWID"));
				newList.add(map1);
     	
     	}
     
    int savedFeeSuits=ctx.get("savedCount")!=null && !ctx.get("savedCount").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("savedCount").toString()):0;
    int newFeeSuitsList=ctx.get("addAttorneys")!=null?Integer.parseInt(ctx.get("addAttorneys").toString()):finalList.size();
    
    if(newFeeSuitsList>finalList.size())
    {
    	int index=finalList.size();
    	Map map = null;
    	for (int i = 0; i < newFeeSuitsList-finalList.size(); i++)
    	{
    		map=new HashMap();
    		index=index+1;
    		map.put("EstimateFormAttorneyKey", null);
    		map.put("AttorneyName", null);
			map.put("AnnualHours", null);
			map.put("AttorneyPriorActDate_", null);
			map.put("isSaved",null);
			map.put("ROWID",index);
			newList.add(map);
    	
    	}
    	
    }
    else
    {
        if(newFeeSuitsList<savedFeeSuits)
        {
        	populateError(ctx, "feesuitIncompleteData","Selected Attorneys count cannot be less than of saved Attorneys.");
        	ctx.put("addAttorneys", savedFeeSuits);
        }
        else
        {
        	int result=finalList.size()-newFeeSuitsList;
        	int temp=finalList.size()-1;
        	for(int j=0;j<result;j++)
        		newList.remove(temp-j);
        	ctx.put("addAttorneys", newList.size());
        }
    }
    ctx.put("addAttorneys", newList.size());
    ctx.put("FeeSuitData_list_01",newList);
    sess.setAttribute("FeeSuitData_list_01s", newList);
    
     }
     }catch(Exception e)
     {
    	 logger.error("Unexpected error", e);
     }

	

}
public static void saveProtexureFormData(Context ctx)  {
	long startTime = System.currentTimeMillis();
	logProtexureEstimateSubmit(ctx, "START", "Protexure estimate form submit started.", null);
	try {
		saveProtexureFormAttorneysData(ctx);
		saveProtexureFormAOP(ctx);

		new SetParametersForStoredProcedures().setParametersInContext(ctx, PROTEXURE_ESTIMATE_GENERAL_PARAMS);
		SqlMapProcessor sqlProcessor = SqlResources.getSqlMapProcessor(ctx);
		sqlProcessor.update("EstimateFormGeneralInfo.InsertUpdateProtexureFormGeneralData_p", ctx);

		Object obj = sqlProcessor.findByKey("SqlStmts.UserStatementManualBoQueriesgetEstimateFormGeneralInfoDetails", ctx);
		if (obj instanceof Map) {
			ctx.putAll((Map) obj);
		} else {
			logger.error("Unable to fetch Protexure estimate form details after save");
		}
		sendProtexureEstimateForm(ctx);
		logProtexureEstimateSubmit(ctx, "SUCCESS", "Protexure estimate form submit completed successfully.", null);
			
//			new SetParametersForStoredProcedures().setParametersInContext(ctx,"");
//			SqlResources.getSqlMapProcessor(ctx).update("ProtexureEstimateForm.InsertProtexureFormAttorniesData_p", ctx);
//			
//			new SetParametersForStoredProcedures().setParametersInContext(ctx,"outputList");
//			SqlResources.getSqlMapProcessor(ctx).update("ProtexureEstimateForm.InsertProtexureFormAopDataLW_p", ctx);
//			
		
//		}

	} catch (Exception e) {
		logProtexureEstimateSubmit(ctx, "ERROR", "Protexure estimate form submit failed.", e);
		logger.error("error occured while executing isReferRuleTriggeredNewApp method" + e);
		logger.error("Unexpected error", e);
	} finally {
		logProtexureEstimateSubmit(ctx, "END", "Protexure estimate form submit finished in " + (System.currentTimeMillis() - startTime) + " ms.", null);
	}

}
public static void saveProtexureFormAOP(Context ctx)
{
	logger.debug("Saving Estimate form AOPs.");
	try
	{	
		List aopList = SqlResources.getSqlMapProcessor(ctx).select("AOPLW.findAll", ctx);
		if (aopList == null || aopList.isEmpty())
			return;

		List inputList = new ArrayList(aopList.size());
		Object otherAopDesc = ctx.get("otherAopDesc");
		for (int i = 0; i < aopList.size(); i++) {
			Map map = (Map) aopList.get(i);
			Object aopKey = map.get("AOPKey");
			String percentageValue = "0";
			Object percentageValueObj = ctx.get("AOP_Percentage_" + aopKey);
			if (percentageValueObj != null) {
				percentageValue = percentageValueObj.toString().trim();
				if ("".equals(percentageValue))
					percentageValue = "0";
			}

			Map reqMap = new HashMap();
			reqMap.put("AOPKey", aopKey);
			reqMap.put("percentageValue", percentageValue);
			if ("25".equals(String.valueOf(aopKey)) && otherAopDesc != null)
				reqMap.put("AOPCommentDesc", otherAopDesc);

			inputList.add(reqMap);
		}

		Context newCtx = new Context();
		newCtx.setProject(ctx.getProject());
		newCtx.putAll(ctx);
		newCtx.put("inputList", inputList);
		LawyersUtils.convertListDataToXML(newCtx, "inputList", "outputList");
		ctx.put("outputList", newCtx.get("outputList"));

	}
	catch(Exception e)
	{
		logger.error("Unable to retain AOP data", e);
	}
}

public static void sendProtexureEstimateForm(Context ctx){
	try{
		String ccAddress = SystemProperties.getInstance().getString("appl.LawyersIns.admin.cc.email");
		String emailID = getProtexureEstimateEmailID(ctx);
		if (!hasProtexureEstimateValue(emailID))
			return;
		logger.debug("Protexure Estimate Mail is going---- ");
		MailSender mail = new MailSender();
		mail.setToAdd(emailID.trim());
		mail.setCcAdd(ccAddress);
		mail.setSubject("Protexure Estimate Form");
		mail.setContentType("text/html");
		mail.setBody(generateProtexureEstimateBody(ctx));
		if (mail.sendMail((Context) ctx))
			logger.debug("Mail has sent---- ");
		else
			logger.error("Protexure Estimate Mail was not sent. Please verify SMTP credentials and mail configuration.");
	}catch(Exception e){
		logger.error("Exception occurred while sending Protexure Estimate Mail: " + e.getMessage());
	}
}

private static void sendProtexureEstimateValidationErrorForm(Context ctx) {
	if ("Y".equals(getEstimateLogValue(ctx, "ProtexureEstimateValidationEmailSent"))) {
		return;
	}
	try {
		ctx.put("ProtexureEstimateValidationEmailSent", "Y");
		sendProtexureEstimateForm(ctx);
	} catch (Exception e) {
		logger.error("Exception occurred while sending Protexure Estimate validation error mail: " + e.getMessage());
	}
}


	public static String getProtexureEstimateEmailID(Context ctx) throws Exception {
		String stateCode = ctx.get("StateCode") == null ? "" : ctx.get("StateCode").toString();
		String emailID = "";
		String firstReviewer = "";
		String productionEnv = "N";
		try {
			productionEnv = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".environment.production");
		} catch (Exception e) {
			logger.error("error in geeting production environment");
		}
		if ("N".equals(productionEnv)){
			emailID = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".admin.email");
		} else {
			String forDirectFRRS = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.fordirectrenew.firstreviewer.RS");
			String forDirectFRQB = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.fordirectnew.firstreviewer.QB");
//			String forDirectFRDK = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.fordirectnew.firstreviewer.DK");
//			String forDirectFRSH = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.fordirectnew.firstreviewer.SH");
//			String forDirectFRCC = SystemProperties.getInstance().getString("appl.LawyersIns.zoho.fordirectnew.firstreviewer.CC");

			if(isProtexureEstimateReviewerState(PROTEXURE_ESTIMATE_REVIEWER_RS_STATES, stateCode)){
				firstReviewer = forDirectFRRS;//Ryan Schwartz
			} else if(isProtexureEstimateReviewerState(PROTEXURE_ESTIMATE_REVIEWER_QB_STATES, stateCode)){
				firstReviewer = forDirectFRQB;//Quiana
//			} else if("CT#GA#FL#KS#VA#TN#UT#NC#SC#OH#IL#MO#MI#AL#AR#HI#IA#MS#NE#OK#DE#DC#LA#WV#RI".contains(stateCode)){
//				firstReviewer = forDirectFRDK;//Dane
//			} else if("AZ#MA#MI#NV#NC#PA#SC#DE#DC#MD#ME#NH#RI#VT".contains(stateCode)){
//				firstReviewer = forDirectFRSH;//Sharon
//			} else if("OH#IL#MN#MO#NJ#WI#LA#NY#WV#MI#NV#PA#NH#RI#VT".contains(stateCode)){
//				firstReviewer = forDirectFRCC;//Chaz
			}

			if (hasProtexureEstimateValue(firstReviewer)) {
				ctx.put("agentname", firstReviewer);
				List getFirstReviewerEmail = (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetProtexureEstimateEmail", ctx);
	    		if(getFirstReviewerEmail != null && !getFirstReviewerEmail.isEmpty()){
	    			Map getFirstReviewerList = (Map)getFirstReviewerEmail.get(0);
	    			emailID = (getFirstReviewerList.get("AgentEmail") == null ? "" : getFirstReviewerList.get("AgentEmail").toString().trim());
	    		}
			}
		}
		return emailID;
	}
	private static void appendProtexureEstimateRow(StringBuilder msg, String label, Object value) {
		msg.append("<tr><td colspan=\"2\" style=\"width:30%\">")
				.append(label)
				.append(" </td><td><b>:</b>&nbsp;&nbsp;")
				.append(value)
				.append("</td></tr>");
	}

	private static void appendProtexureEstimateOptionalRow(StringBuilder msg, String label, Object value) {
		if (value != null)
			appendProtexureEstimateRow(msg, label, value);
	}

	private static void appendProtexureEstimateSectionHeader(StringBuilder msg, String sectionName) {
		msg.append("<tr><td colspan=\"3\"><b>")
				.append(sectionName)
				.append("</b><br/><br/></td></tr>");
	}

	private static void appendProtexureEstimateContactRows(StringBuilder msg, Context ctx) {
		appendProtexureEstimateSectionHeader(msg, "Contact Information");
		appendProtexureEstimateRow(msg, "Law firm Name", ctx.get("AccountName"));
		appendProtexureEstimateRow(msg, "Contact Person", ctx.get("ContactPerson"));
		appendProtexureEstimateRow(msg, "Address ", ctx.get("Address1"));
		appendProtexureEstimateRow(msg, "State ", ctx.get("StateCode"));
		appendProtexureEstimateRow(msg, "County ", ctx.get("CountyDesc"));
		appendProtexureEstimateRow(msg, "City ", ctx.get("City"));
		appendProtexureEstimateRow(msg, "Zip Code ", ctx.get("Zip"));
		appendProtexureEstimateRow(msg, "Phone Number ", ctx.get("WorkPhone"));
		appendProtexureEstimateRow(msg, "Email ", ctx.get("AccountEmail"));
	}

	private static void appendProtexureEstimateCoverageRows(StringBuilder msg, Context ctx) {
		appendProtexureEstimateSectionHeader(msg, "Coverage");
		appendProtexureEstimateRow(msg, "Limit", ctx.get("AggregateLimit") + "/" + ctx.get("OccuranceLimit"));
		appendProtexureEstimateRow(msg, "Deductible", ctx.get("OccuranceDeductible"));
		appendProtexureEstimateRow(msg, "Expenses Outside Limit", ctx.get("IsClaimExpensesType"));
		appendProtexureEstimateOptionalRow(msg, "IsDollarDefense", ctx.get("IsDollarDefense"));
		appendProtexureEstimateRow(msg, "Current Carrier", ctx.get("InsuranceCompanyNamePross"));
		appendProtexureEstimateRow(msg, "Current policy expiration date", ctx.get("PolicyExpirationDatePross"));
		appendProtexureEstimateRow(msg, "Current Premium", ctx.get("ProInsPremium"));
		appendProtexureEstimateRow(msg, "Has Prior Acts Date ", ctx.get("IsPriorActDateFull"));
		appendProtexureEstimateOptionalRow(msg, "What is your prior acts date? ", ctx.get("PriorActDatePross"));
		appendProtexureEstimateOptionalRow(msg, "If Full, pleaser provide number of years", ctx.get("FirmYear"));
		appendProtexureEstimateRow(msg,
				"Within the past five (5) years has any firm personnel reported claims or been the subject of a disciplinary matter?",
				ctx.get("hasAnyFirmPersonnelReported"));
	}

	private static boolean hasProtexureEstimateValidationErrors(Context ctx) {
		List errorList = getProtexureEstimateErrorList(ctx);
		return errorList != null && !errorList.isEmpty();
	}

	private static void appendProtexureEstimateValidationErrorRows(StringBuilder msg, Context ctx) {
		List errorList = getProtexureEstimateErrorList(ctx);
		if (errorList == null || errorList.isEmpty()) {
			return;
		}

		appendProtexureEstimateSectionHeader(msg, "Validation Errors");
		for (int i = 0; i < errorList.size(); i++) {
			Object error = errorList.get(i);
			if (error instanceof ValidationException) {
				ValidationException validationException = (ValidationException) error;
				appendProtexureEstimateRow(msg, validationException.getField(), validationException.getMessage());
			} else {
				appendProtexureEstimateRow(msg, "Error " + (i + 1), error);
			}
		}
	}

	private static void appendSubmittedProtexureEstimateAttorneyRows(StringBuilder msg, Context ctx) {
		int attorneyCount = getEstimateLogInteger(ctx, "addAttorneys");
		for (int i = 0; i < attorneyCount; i++) {
			msg.append("<tr><td>")
					.append(ctx.get("AttorneyName_" + i))
					.append("</td><td>")
					.append(ctx.get("AnnualHours_" + i))
					.append("</td><td>")
					.append(ctx.get("AttorneyPriorActDate_" + i))
					.append("</td></tr>");
		}
	}

	private static void appendSubmittedProtexureEstimateAopRows(StringBuilder msg, Context ctx) {
		if (ctx == null) {
			return;
		}

		List aopKeys = new ArrayList();
		Iterator iterator = ctx.keySet().iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			if (key != null && key.toString().startsWith("AOP_Percentage_")
					&& hasProtexureEstimateValue(ctx.get(key))) {
				aopKeys.add(key.toString());
			}
		}

		Collections.sort(aopKeys);
		for (int i = 0; i < aopKeys.size(); i++) {
			String key = aopKeys.get(i).toString();
			Object percentage = ctx.get(key);
			if (!isProtexureEstimatePositivePercentage(percentage)) {
				continue;
			}
			String aopKey = key.substring("AOP_Percentage_".length());
			Object aopDesc = ctx.get("AOPDesc_" + aopKey);
			appendProtexureEstimateRow(msg, hasProtexureEstimateValue(aopDesc) ? aopDesc.toString() : key, percentage);
		}
	}

	private static boolean isProtexureEstimatePositivePercentage(Object value) {
		if (!hasProtexureEstimateValue(value)) {
			return false;
		}
		try {
			return Integer.parseInt(value.toString().trim()) > 0;
		} catch (Exception e) {
			return true;
		}
	}

	private static String generateProtexureEstimateBody(Context ctx) throws Exception {
		StringBuilder msg = new StringBuilder(4096);
		msg.append("<table cellpadding=\"5\" cellspacing=\"5\">");
		msg.append("<tr><td colspan=\"3\">Hi<br/><br/></td></tr>");
		if (hasProtexureEstimateValidationErrors(ctx)) {
			msg.append("<tr><td colspan=\"3\">The following Firm tried to submit their application but received validation errors.<br/><br/></td></tr>");
		} else {
			msg.append("<tr><td colspan=\"3\">The following Firm has completed their application<br/><br/></td></tr>");
		}

		appendProtexureEstimateContactRows(msg, ctx);
		if (hasProtexureEstimateValidationErrors(ctx)) {
			appendProtexureEstimateValidationErrorRows(msg, ctx);
		}
		appendProtexureEstimateCoverageRows(msg, ctx);

		appendProtexureEstimateSectionHeader(msg, "Attorney Details");
		msg.append("<tr><td>Attorney Name</td><td>Approximate Annual Hours</td><td>Date Joined the Firm</td></tr>");

		List attorneyDetails = null;
		try {
			attorneyDetails = (List) SqlResources.getSqlMapProcessor(ctx).select(
					"SqlStmts.UserStatementManualBoQueriesgetProtexureEstimateEmailAttorneyDetail", ctx);
		} catch (Exception e) {
			logger.error("Unable to fetch saved Protexure estimate attorney email details: " + e.getMessage());
		}
		if (attorneyDetails != null && !attorneyDetails.isEmpty()) {
			for (int i = 0; i < attorneyDetails.size(); i++) {
				Map attorneyDetail = (Map) attorneyDetails.get(i);
				msg.append("<tr><td>")
						.append(attorneyDetail.get("AttorneyName"))
						.append("</td><td>")
						.append(attorneyDetail.get("AnnualHours"))
						.append("</td><td>")
						.append(attorneyDetail.get("AttorneyPriorActDate"))
						.append("</td></tr>");
			}
		} else {
			appendSubmittedProtexureEstimateAttorneyRows(msg, ctx);
		}

		appendProtexureEstimateSectionHeader(msg, "Area of Practice");

		List aopDetails = null;
		try {
			aopDetails = (List) SqlResources.getSqlMapProcessor(ctx).select(
					"SqlStmts.UserStatementManualBoQueriesgetProtexureEstimateEmailAOPDetail", ctx);
		} catch (Exception e) {
			logger.error("Unable to fetch saved Protexure estimate AOP email details: " + e.getMessage());
		}
		if (aopDetails != null && !aopDetails.isEmpty()) {
			for (int i = 0; i < aopDetails.size(); i++) {
				Map aopDetail = (Map) aopDetails.get(i);
				appendProtexureEstimateRow(msg, String.valueOf(aopDetail.get("AOPDescNew")), aopDetail.get("PercentageValue"));
			}
		} else {
			appendSubmittedProtexureEstimateAopRows(msg, ctx);
		}

		msg.append("</table>");
		return msg.toString();
	}


 
	public static void validateAttorneysForEstimateForm(Context ctx)
	{
		try
		{
	    	HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
	        HttpSession sess = req.getSession();
	       // boolean isDateValid=true;
	        List finalList = null;
	        if (sess.getAttribute("FeeSuitData_list_01") != null && sess.getAttribute("FeeSuitData_list_01") instanceof List) {
				finalList = (List) sess.getAttribute("FeeSuitData_list_01");
	        }

			int attorneyCount = ctx.get("addAttorneys") != null && !ctx.get("addAttorneys").equals(HtmlConstants.EMPTY)
					? Integer.parseInt(ctx.get("addAttorneys").toString())
					: finalList != null ? finalList.size() : 0;
			List postedAttorneyList = new ArrayList();
			for (int i = 0; i < attorneyCount; i++)
			{
				Object attorneyName = ctx.get("AttorneyName_"+i);
				Object annualHours = ctx.get("AnnualHours_"+i);
				Object attorneyPriorActDate = ctx.get("AttorneyPriorActDate_"+i);
				Map postedAttorney = new HashMap();
				postedAttorney.put("AttorneyName", attorneyName);
				postedAttorney.put("AnnualHours", annualHours);
				postedAttorney.put("AttorneyPriorActDate", attorneyPriorActDate);
				postedAttorney.put("EstimateFormAttorneyKey", ctx.get("EstimateFormAttorneyKey_"+i));
				postedAttorneyList.add(postedAttorney);
				if (attorneyName == null || attorneyName.toString().trim().equals(HtmlConstants.EMPTY)
						|| annualHours == null || annualHours.toString().trim().equals(HtmlConstants.EMPTY)
						|| attorneyPriorActDate == null || attorneyPriorActDate.toString().trim().equals(HtmlConstants.EMPTY)) {
					LawyersUtils.populateError(ctx, "needFirstRow","All added attorneys need to be filled.");
				}
	        }
			ctx.put("FeeSuitData_list_01", postedAttorneyList);
			sess.setAttribute("FeeSuitData_list_01", postedAttorneyList);
	       
	       boolean isErrorListNotEmpty = false;
			Object isErrorListNotEmptyRule = RuleUtils.executeRule(ctx,"LawyersRule.isInetErrorListNotEmpty");
			if (isErrorListNotEmptyRule != null && isErrorListNotEmptyRule instanceof Boolean) {
				isErrorListNotEmpty = (Boolean) isErrorListNotEmptyRule;
			}
			if(isErrorListNotEmpty)
			{
				LawyersUtils.populateError(ctx, "EstomateForm","There are issues with the data being submitted. Please correct any errors noted below and resubmit.");
				logProtexureEstimateSubmit(ctx, "VALIDATION_ERROR", "Protexure estimate form submit stopped due to validation errors.", null);
				sendProtexureEstimateValidationErrorForm(ctx);
			}
		}
		catch(Exception e)
		{logger.error("Unexpected error", e);}
	}
	

public static void saveCarrearCoverageAttorneys(Context ctx)
{
	try
	{
		List finalList =null;
        List inputList=new ArrayList();
        if(ctx.get("AttorneysDetailsList_list_01") != null && ctx.get("AttorneysDetailsList_list_01") instanceof List)
        {
        	finalList = (List)ctx.get("AttorneysDetailsList_list_01");
	        Map map = new HashMap();
			for (int i = 0; i < finalList.size(); i++) 
			{	
					map = (HashMap) finalList.get(i);
					map.put("AttorneyName", ctx.get("AttorneyName_"+i));
					map.put("AttorneyKey", ctx.get("AttorneyKey_"+i));
					map.put("PolicyKey", ctx.get("PolicyKey"));
					map.put("VersionKey", ctx.get("VersionKey"));
					map.put("LastUpdateUserID", ctx.get("LastUpdateUserID"));
					map.put("LastUpdateTimeStamp", ctx.get("LastUpdateTimeStamp"));
					map.put("CreatedBy", ctx.get("LastUpdateUserID"));
					map.put("CreatedDate",ctx.get("LastUpdateTimeStamp"));
					String attorneyPriorActDate=ctx.get("AttorneyPriorActDate_"+i)!=null && !ctx.get("AttorneyPriorActDate_"+i).equals(HtmlConstants.EMPTY)?ctx.get("AttorneyPriorActDate_"+i).toString():null;
				
						map.put("AttorneyPriorActDate", ctx.get("AttorneyPriorActDate_"+i));
						inputList.add(map);
					
			}
			if(inputList.size()>0){
				ctx.put("inputList",inputList);
				convertListDataToXML(ctx,"inputList","outputList");
			}
	    }
	}
    catch(Exception e){
		logger.error("Unable to convert list to XML", e);
    	ctx.put("skipSave","Y");
    }
}


}
