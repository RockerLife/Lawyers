package com.userbo;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.manage.managecomponent.components.SetParametersForStoredProcedures;
import com.manage.managemetadata.functions.commonfunctions.DBUtils;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.manage.managemetadata.util.exception.ValidationException;
import com.ormapping.ibatis.SqlResources;
import com.util.BeanUtils;
import com.util.Constants;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.IContext;
import com.util.InetLogger;

public class AOP {

	private static InetLogger logger = InetLogger.getInetLogger(AOP.class);
	public boolean validateAOPComment(IContext ctx, List limtTypes)
			throws Exception {

		boolean flag = false;
		Map map = new HashMap();
		for (int i = 0; i < limtTypes.size(); i++) {
			map = (HashMap) limtTypes.get(i);
			if (map.get("AOPKey").toString().equals("9")
					|| map.get("AOPKey").toString().equals("10")
					|| map.get("AOPKey").toString().equals("12")
					|| map.get("AOPKey").toString().equals("20")
					|| map.get("AOPKey").toString().equals("21")) {
				String percentageValue = ctx.get("AOP_Percentage_"
						+ map.get("AOPKey").toString()) == null ? null : ctx
						.get("AOP_Percentage_" + map.get("AOPKey").toString())
						.toString();
				if (!(percentageValue == null || "".equals(percentageValue) || "0"
						.equals(percentageValue))) {
					String comment = ctx.get("AOPCommentDesc_"
							+ map.get("AOPKey").toString()) == null ? null
							: ctx.get(
									"AOPCommentDesc_"
											+ map.get("AOPKey").toString())
									.toString();
					if (comment == null || "".equals(comment)) {
					/*	System.out.println("Khashi is the kinG");*/
						new LawyersUtils().populateError(ctx, "AOPCommentDesc_"
								+ map.get("AOPKey").toString(),
								"Fill in please describe");
						flag = true;
					}
				}
			}
		}

		return flag;
	}

	/**
	 * This method saves the AOP page values
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void savePopulateFields(IContext oldCtx) throws Exception {
		Context ctx=(Context)oldCtx;
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select("AOPLW.findAll", ctx);
		 List inputList=new ArrayList();
		Context ctx1 = new Context();
		ctx1.setProject(ctx.getProject());
		ctx1.putAll(ctx);
		if (limtTypes != null) {
			int totalAOPpercent = checkAOPPercentage(limtTypes, ctx);
			// if(totalAOPpercent==100)
			// {
			// if(!validateAOPComment(ctx, limtTypes))
			// {
			try {
				Map map = new HashMap();
				Map map1 = null;
				for (int i = 0; i < limtTypes.size(); i++) {
					map = (HashMap) limtTypes.get(i);
					map1=new HashMap();
					String percentageValue = ctx.get("AOP_Percentage_"+ map.get("AOPKey").toString()) == null ?null:ctx.get("AOP_Percentage_"+ map.get("AOPKey").toString()).toString().trim();
					ctx1.remove("AOP_Percentage_"+ map.get("AOPKey"));
					if (percentageValue == null || "".equals(percentageValue) || "0".equals(percentageValue))
						percentageValue = "0";
					
					
					map1.put("PercentageValue", percentageValue);
					if (map.get("AOPKey").toString().equals("25"))
					{
						if (ctx.get("AOPCommentDesc_" + map.get("AOPKey")) != null)
						{
							map1.put("AOPCommentDesc", ctx.get("AOPCommentDesc_" + map.get("AOPKey")).toString().trim());
						}
					}
					map1.put("AOPKey", map.get("AOPKey"));
					
					
					map1.put("PolicyKey",ctx.get("PolicyKey"));
					map1.put("VersionSequence",ctx.get("VersionSequence"));
					inputList.add(map1);
					
					
					
					/*Object obj2 = DBUtils.executeDBOperation(ctx1,"AreaOfPracticeLW", "3");

					if (obj2 == null)
					{
						DBUtils.executeDBOperation(ctx1, "AreaOfPracticeLW","1");

					} 
					else
					{
						DBUtils.executeDBOperation(ctx1, "AreaOfPracticeLW","2");
						DBUtils.executeDBOperation(ctx1, "AreaOfPracticeLW","1");
					}*/
				}
				ctx1.put("inputList", inputList);
				LawyersUtils.convertListDataToXML(ctx1,"inputList","outputList");
				ctx.put("outputList", ctx1.get("outputList"));
				
				try {
					new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,VersionSequence,outputList");
					SqlResources.getSqlMapProcessor(ctx).update("AreaOfPracticeLW.InsertUpdateAOPFields_p",ctx);
					} catch (Exception e) {
						logger.error("Unexpected error", e);
					}
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}
			// }
			// }
		}

	}

	/**
	 * This method checks the total AOP percentage and populates error if the
	 * percentage is not 100
	 * 
	 * @param limtTypes
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static int checkAOPPercentage(List limtTypes, IContext ctx)
			throws Exception {
		int total = 0;
		Map map = new HashMap();
		for (int i = 0; i < limtTypes.size(); i++) {
			map = (HashMap) limtTypes.get(i);
			String percentageValue = ctx.get("AOP_Percentage_"+ map.get("AOPKey").toString()) == null ? null : ctx.get("AOP_Percentage_" + map.get("AOPKey").toString())
					.toString().trim();
			if (percentageValue == null || "".equals(percentageValue))
				percentageValue = "0";
			try {
				total = total + Integer.parseInt(percentageValue);
			} catch (Exception e) {

				LawyersUtils.populateError(ctx, "TotalAOP_Percentage",
						"Invalid AOP data");

			}
		}

		if (total < 100 || total > 100) {
			percentageError(ctx, total, "100");
		}

		return total;
	}

	/**
	 * This method populates the AOP fields on the AOP page
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void populateAOPFields(IContext ctx) throws Exception {
		
		if(ctx.get(Constants.INET_ERRORS_LIST) != null){
			return ;
		}
		
		List aoplist = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields", ctx);
		//ctx.remove("aop_freeform_4");
		ctx.put("showAopSupp", null);

		Map covMap = new HashMap();
		if (aoplist != null) {
			Map map = new HashMap();
			for (int i = 0; i < aoplist.size(); i++) {
				map = (HashMap) aoplist.get(i);
				ctx.put("AOP_Percentage_" + map.get("AOPKey"), map
						.get("PercentageValue"));
				ctx.put("AOPCommentDesc_" + map.get("AOPKey"), map
						.get("AOPCommentDesc"));
				
			}
		}		

//		ctx.put("AOP_Percentage_18",covMap.get("AOP_Percentage_18") );
//		ctx.put("AOP_Percentage_20",covMap.get("AOP_Percentage_20") );
//		ctx.put("AOP_Percentage_24",covMap.get("AOP_Percentage_24") );
//		ctx.put("AOP_Percentage_27",covMap.get("AOP_Percentage_27") );
		
		//ctx.put("aop_freeform_4", covMap);
		
	}

	/*public void populateGrossRevenue(IContext ctx) throws Exception {

		long total = 0;
		int count = 0;
		List grRevList = null;
		if (ctx.get("firm_list_9") != null) {
			grRevList = (List) ctx.get("firm_list_9");
		}

		if (grRevList != null) {
			Map map = new HashMap();
			for (int i = 0; i < grRevList.size(); i++) {
				map = (HashMap) grRevList.get(i);
				Object objAmount = map.get("Amount");
				ctx.put("Amount_" + i, map.get("Amount") == null ? "" : map
						.get("Amount").toString());

				if (objAmount != null && !"".equals(objAmount.toString())
						&& !"0".equals(objAmount.toString())) {
					total = total + Long.parseLong(objAmount.toString());
					count = count + 1;
					ctx.put("Amount_" + i, objAmount.toString());
				} else
					ctx.put("Amount_" + i, objAmount != null ? objAmount
							.toString() : "");
			}

			if (count > 0) {
				long AverageRevenue = total / count;
				ctx.put("AverageRevenue", AverageRevenue);
			} else
				ctx.put("AverageRevenue", 0);
		}
	}*/
	public void populateGrossRevenue(IContext ctx) throws Exception {

		long total = 0;
		int count = 0;
		List grRevList = null;
		if (ctx.get("firm_list_9") != null) {
			grRevList = (List) ctx.get("firm_list_9");
		}
		if (grRevList != null) {
			Map map = new HashMap();
			for (int i = 0; i < grRevList.size(); i++) {
				map = (HashMap) grRevList.get(i);
				Object objAmount = map.get("Amount");
				if(ctx.get("inet_errors_list")==null)
				{
					objAmount = map.get("Amount");
					ctx.put("Amount_" + i, map.get("Amount") == null ? "" : map
							.get("Amount").toString());
				}
				else
				{
					objAmount = ctx.get("Amount_"+i);
				}
					
				if (objAmount != null && !"".equals(objAmount.toString())
						&& !"0".equals(objAmount.toString())) {
					total = total + Long.parseLong(objAmount.toString().replace("$","").replace(",",""));
					count = count + 1;
					ctx.put("Amount_" + i, objAmount.toString());
				} else
					ctx.put("Amount_" + i, "");
			}

			if (count > 0) {
				long AverageRevenue = total / count;
				ctx.put("AverageRevenue", AverageRevenue);
			} else
				ctx.put("AverageRevenue", 0);
		}

	}
	public void saveAOTPFields(IContext ctx) throws Exception {
		Context ctx1;
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select(
				"AOTPLW.findAll", ctx);
		if (limtTypes != null) {
			try {
				Map map = new HashMap();
				for (int i = 0; i < limtTypes.size(); i++) {
					map = (HashMap) limtTypes.get(i);
					String percentageValue = ctx.get("AOTP_Percentage_"
							+ map.get("AOTPKey").toString()) == null ? null
							: ctx.get(
									"AOTP_Percentage_"
											+ map.get("AOTPKey").toString())
									.toString().trim();
					if (percentageValue == null || "".equals(percentageValue)
							|| "0".equals(percentageValue))
						percentageValue = "0";

					ctx1 = new Context();
					ctx1.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
					ctx1.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
					ctx1.put("VersionSequence", ctx.get("VersionSequence"));
					ctx1.put("PolicyKey", ctx.get("PolicyKey"));
					ctx1.put("PercentageValue", percentageValue);
					if (map.get("AOTPKey").toString().equals("9")) {
						if (ctx.get("AOTP_CommentDesc_" + map.get("AOTPKey")) != null) {
							ctx1.put("AOTPCommentDesc", ctx.get(
									"AOTP_CommentDesc_" + map.get("AOTPKey"))
									.toString().trim());
						}
					}
					ctx1.put("AOTPKey", map.get("AOTPKey"));
					ctx1.setProject(ctx.getProject());
					Object obj2 = DBUtils.executeDBOperation(ctx1,
							"AreaOfTaxPracticeLW", "3");

					if (obj2 == null) {
						DBUtils.executeDBOperation(ctx1, "AreaOfTaxPracticeLW",
								"1");
					} else {
						DBUtils.executeDBOperation(ctx1, "AreaOfTaxPracticeLW",
								"2");
						DBUtils.executeDBOperation(ctx1, "AreaOfTaxPracticeLW",
								"1");
					}
				}
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}
		}
	}

	private int checkAOTPPercentage(List limtTypes, IContext ctx)
			throws Exception {
		int total = 0;
		Map map = new HashMap();
		for (int i = 0; i < limtTypes.size(); i++) {
			map = (HashMap) limtTypes.get(i);
			String percentageValue = ctx.get("AOTP_Percentage_" + i) == null ? null
					: ctx.get("AOTP_Percentage_" + i).toString().trim();
			if (percentageValue == null || "".equals(percentageValue))
				percentageValue = "0";
			total = total + Integer.parseInt(percentageValue);
		}
		if (total < 100 || total > 100) {
			percentageError(ctx, total, "100");
		}
		return total;
	}

	public void populateAOTPFields(IContext ctx) throws Exception {
		List aoplist = SqlResources
				.getSqlMapProcessor(ctx)
				.select(
						"SqlStmts.UserStatementsaveAccountstmtspopulateAOTPFields",
						ctx);

		ctx.remove("taxSupp_freeform_1");
		ctx.remove("TaxOther");

		int totalPercentage = 0;
		Map covMap = new HashMap();
		if (aoplist != null) {
			Map map = new HashMap();
			for (int i = 0; i < aoplist.size(); i++) {
				map = (HashMap) aoplist.get(i);
				covMap.put("AOTP_Percentage_" + map.get("AOTPKey"), map
						.get("PercentageValue"));
				if (map.get("PercentageValue") != null) {
					totalPercentage = totalPercentage
							+ Integer.parseInt(map.get("PercentageValue")
									.toString());
				}
				covMap.put("AOTP_CommentDesc_" + map.get("AOTPKey"), map
						.get("AOTPCommentDesc"));
				if (covMap.get("AOTP_CommentDesc_9") != null
						&& !covMap.get("AOTP_CommentDesc_9").equals("")) {
					ctx.put("TaxOther", "Y");
				}
			}
		}
		ctx.put("TotalAOTP_Percentage", totalPercentage);
		ctx.put("taxSupp_freeform_1", covMap);
	}

	public void populateWESFields(IContext ctx) throws Exception {
		List wesList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtspopulateWESFields", ctx);

		ctx.remove("willsEstateSupp_freeform_1");
		ctx.remove("WillsOther");

		Map covMap = new HashMap();
		if (wesList != null) {
			Map map = new HashMap();
			for (int i = 0; i < wesList.size(); i++) {
				map = (HashMap) wesList.get(i);

				covMap.put("WES_CheckedValue_" + map.get("WESKey"), map
						.get("CheckedValue"));
				covMap.put("WES_CommentDesc_" + map.get("WESKey"), map
						.get("WESCommentDesc"));
				if (covMap.get("WES_CheckedValue_12") != null
						&& covMap.get("WES_CheckedValue_12").equals("Y")) {
					ctx.put("WillsOther", "Y");
				}
			}
		}

		ctx.put("willsEstateSupp_freeform_1", covMap);
	}

	public void saveWillsEstateServices(IContext ctx) throws Exception {
		Context ctx1;
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select(
				"WESLW.findAll", ctx);
		Object obj = ctx.get("WES_CheckedValue_10");
		if (limtTypes != null) {
			try {
				Map map = new HashMap();
				for (int i = 0; i < limtTypes.size(); i++) {
					map = (HashMap) limtTypes.get(i);
					ctx1 = new Context();
					ctx1.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
					ctx1.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
					ctx1.put("VersionSequence", ctx.get("VersionSequence"));
					ctx1.put("PolicyKey", ctx.get("PolicyKey"));
					ctx1.put("WESKey", map.get("WESKey"));
					ctx1.put("WESCommentDesc", ctx.get("WES_CommentDesc_"
							+ map.get("WESKey")));
					String checkedValue = ctx.get("WES_CheckedValue_"
							+ map.get("WESKey").toString()) == null ? null
							: ctx.get(
									"WES_CheckedValue_"
											+ map.get("WESKey").toString())
									.toString().trim();
					if (checkedValue == null || checkedValue.equals("")) {
						checkedValue = "N";
					} else {
						checkedValue = "Y";
					}
					ctx1.put("CheckedValue", checkedValue);
					ctx1.setProject(ctx.getProject());
					Object obj2 = DBUtils.executeDBOperation(ctx1,
							"WillsEstateServicesLW", "3");

					if (obj2 == null) {
						DBUtils.executeDBOperation(ctx1,
								"WillsEstateServicesLW", "1");

					} else {
						DBUtils.executeDBOperation(ctx1,
								"WillsEstateServicesLW", "2");
						DBUtils.executeDBOperation(ctx1,
								"WillsEstateServicesLW", "1");
					}
				}
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}
		}
	}

	public void saveAOL(IContext ctx) throws Exception {
		Context ctx1;
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select(
				"AOLLW.findAll", ctx);
		if (limtTypes != null) {
			try {
				Map map = new HashMap();
				for (int i = 0; i < limtTypes.size(); i++) {
					map = (HashMap) limtTypes.get(i);
					ctx1 = new Context();
					ctx1.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
					ctx1.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
					ctx1.put("VersionSequence", ctx.get("VersionSequence"));
					ctx1.put("PolicyKey", ctx.get("PolicyKey"));
					ctx1.put("AOLKey", map.get("AOLKey"));
					ctx1.put("AOLCommentDesc", ctx.get("AOL_CommentDesc_"
							+ map.get("AOLKey")));
					String percentageOfRevenue = ctx
							.get("AOL_PercentageOfRevenue_" + map.get("AOLKey")) == null ? null
							: ctx.get(
									"AOL_PercentageOfRevenue_"
											+ map.get("AOLKey")).toString();
					if (percentageOfRevenue == null
							|| "".equals(percentageOfRevenue))
						percentageOfRevenue = "0";
					ctx1.put("PercentageOfRevenue", percentageOfRevenue);
					ctx1.put("AverageCaseSize", ctx.get("AOL_AverageCaseSize_"
							+ map.get("AOLKey")));
					ctx1.put("LargestCaseSize", ctx.get("AOL_LargestCaseSize_"
							+ map.get("AOLKey")));
					ctx1.setProject(ctx.getProject());
					Object obj2 = DBUtils.executeDBOperation(ctx1,
							"AreaOfLitigationForFirmLW", "3");

					if (obj2 == null) {
						DBUtils.executeDBOperation(ctx1,
								"AreaOfLitigationForFirmLW", "1");

					} else {
						DBUtils.executeDBOperation(ctx1,
								"AreaOfLitigationForFirmLW", "2");
						DBUtils.executeDBOperation(ctx1,
								"AreaOfLitigationForFirmLW", "1");
					}
				}
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}
		}
	}

	public void populateAOLFields(IContext ctx) throws Exception {
		List wesList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOLFields", ctx);

		ctx.remove("plaintiffSupp_freeform_1");
		ctx.remove("PlaintiffOther");

		int totalPercentage = 0;
		long totalLargestCase = 0;
		long totalAverageCase = 0;
		Map covMap = new HashMap();

		if (wesList != null) {
			Map map = new HashMap();
			for (int i = 0; i < wesList.size(); i++) {
				map = (HashMap) wesList.get(i);

				covMap.put("AOL_PercentageOfRevenue_" + map.get("AOLKey"), map
						.get("PercentageOfRevenue"));

				if (map.get("PercentageOfRevenue") != null) {
					totalPercentage = totalPercentage
							+ Integer.parseInt(map.get("PercentageOfRevenue")
									.toString());
				}
				covMap.put("AOL_AverageCaseSize_" + map.get("AOLKey"), map
						.get("AverageCaseSize"));
				if (map.get("AverageCaseSize") != null) {
					totalAverageCase = totalAverageCase
							+ Long.parseLong(map.get("AverageCaseSize")
									.toString());
				}
				covMap.put("AOL_LargestCaseSize_" + map.get("AOLKey"), map
						.get("LargestCaseSize"));
				if (map.get("LargestCaseSize") != null) {
					totalLargestCase = totalLargestCase
							+ Long.parseLong(map.get("LargestCaseSize")
									.toString());
				}

				covMap.put("AOL_CommentDesc_" + map.get("AOLKey"), map
						.get("AOLCommentDesc"));

				if (covMap.get("AOL_CommentDesc_13") != null
						&& !covMap.get("AOL_CommentDesc_13").equals("")) {
					ctx.put("PlaintiffOther", "Y");
				}
			}
		}

		ctx.put("TotalAOL_PercentageOfRevenue", totalPercentage);
		String averageCase = getAmountFormat(String.valueOf(totalAverageCase));
		ctx.put("TotalAOL_AverageCaseSize", averageCase);
		String largestCase = getAmountFormat(String.valueOf(totalLargestCase));
		ctx.put("TotalAOL_LargestCaseSize", largestCase);
		ctx.put("plaintiffSupp_freeform_1", covMap);

		Map mapTest = new HashMap();
		mapTest.put("testValue", null);
		ctx.put("test_freeform_1", mapTest);

	}

	public void saveAOPForRealEstate(IContext ctx) throws Exception {
		Context ctx1;
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select(
				"AOPRELW.findAll", ctx);
		if (limtTypes != null) {
			try {
				Map map = new HashMap();
				for (int i = 0; i < limtTypes.size(); i++) {
					map = (HashMap) limtTypes.get(i);
					ctx1 = new Context();
					ctx1.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
					ctx1.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
					ctx1.put("VersionSequence", ctx.get("VersionSequence"));
					ctx1.put("PolicyKey", ctx.get("PolicyKey"));
					ctx1.put("AOPREKey", map.get("AOPREKey"));
					if (map.get("AOPREKey").toString().equals("17")) {
						if (ctx.get("AOPRE_CommentDesc_" + map.get("AOPREKey")) != null) {
							ctx1.put("AOPRECommentDesc", ctx.get(
									"AOPRE_CommentDesc_" + map.get("AOPREKey"))
									.toString().trim());
						}
					}
					ctx1.put("PercentageValue", ctx.get("AOPRE_Percentage_"
							+ map.get("AOPREKey")));
					ctx1.setProject(ctx.getProject());
					Object obj2 = DBUtils.executeDBOperation(ctx1,
							"AreaOfPracticeRealEstateLW", "3");

					if (obj2 == null) {
						DBUtils.executeDBOperation(ctx1,
								"AreaOfPracticeRealEstateLW", "1");
					} else {
						DBUtils.executeDBOperation(ctx1,
								"AreaOfPracticeRealEstateLW", "2");
						DBUtils.executeDBOperation(ctx1,
								"AreaOfPracticeRealEstateLW", "1");
					}
				}
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}
		}
	}

	public void populateAOPREFields(IContext ctx) throws Exception {
		List wesList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtspopulateAOPREFields",
				ctx);

		ctx.remove("realEstateSupp_freeform_1");
		ctx.remove("REOther");

		int totalPercentage = 0;
		Map covMap = new HashMap();
		if (wesList != null) {
			Map map = new HashMap();
			for (int i = 0; i < wesList.size(); i++) {
				map = (HashMap) wesList.get(i);
				covMap.put("AOPRE_Percentage_" + map.get("AOPREKey"), map
						.get("PercentageValue"));
				if (map.get("PercentageValue") != null) {
					totalPercentage = totalPercentage
							+ Integer.parseInt(map.get("PercentageValue")
									.toString());
				}
				covMap.put("AOPRE_CommentDesc_" + map.get("AOPREKey"), map
						.get("AOPRECommentDesc"));
				if (covMap.get("AOPRE_CommentDesc_17") != null
						&& !covMap.get("AOPRE_CommentDesc_17").equals("")) {
					ctx.put("REOther", "Y");
				}
			}
		}
		ctx.put("TotalAOPRE_Percentage", totalPercentage);
		ctx.put("realEstateSupp_freeform_1", covMap);
	}

	public void insertAreaOfPracticeRealEstateLWList(IContext ctx)
			throws Exception {
		List wesList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetAOPREFields", ctx);
		if (wesList != null) {
			Map map = new HashMap();
			for (int i = 0; i < wesList.size(); i++) {
				map = (HashMap) wesList.get(i);
				ctx.put("AOPREKey", map.get("AOPREKey"));
				DBUtils.executeDBOperation(ctx, "AreaOfPracticeRealEstateLW",
						"1");
			}
		}
	}

	public void insertAreaOfTaxPracticeLWList(IContext ctx) throws Exception {
		List wesList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetAOTPFields", ctx);
		if (wesList != null) {
			Map map = new HashMap();
			for (int i = 0; i < wesList.size(); i++) {
				map = (HashMap) wesList.get(i);
				ctx.put("AOTPKey", map.get("AOTPKey"));
				DBUtils.executeDBOperation(ctx, "AreaOfTaxPracticeLW", "1");
			}
		}
	}

	public void saveCCBFields(IContext ctx) throws Exception {
		Context ctx1;
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select(
				"CCBLW.findAll", ctx);
		if (limtTypes != null) {
			try {
				Map map = new HashMap();
				for (int i = 0; i < limtTypes.size(); i++) {
					map = (HashMap) limtTypes.get(i);
					ctx1 = new Context();
					ctx1.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
					ctx1.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
					ctx1.put("VersionSequence", ctx.get("VersionSequence"));
					ctx1.put("PolicyKey", ctx.get("PolicyKey"));
					ctx1.put("CCBKey", map.get("CCBKey"));
					ctx1.put("CCBCommentDesc", ctx.get("CCB_CommentDesc_"
							+ map.get("CCBKey")));
					String checkedValue = ctx.get("CCB_CheckedValue_"
							+ map.get("CCBKey").toString()) == null ? null
							: ctx.get(
									"CCB_CheckedValue_"
											+ map.get("CCBKey").toString())
									.toString().trim();
					if (checkedValue == null
							|| (checkedValue != null && checkedValue.equals(""))) {
						checkedValue = "N";
					} else {
						checkedValue = "Y";
					}
					ctx1.put("CCBCheckedValue", checkedValue);
					ctx1.setProject(ctx.getProject());
					Object obj2 = DBUtils.executeDBOperation(ctx1,
							"CorpCommBusinessLW", "3");

					if (obj2 == null) {
						DBUtils.executeDBOperation(ctx1, "CorpCommBusinessLW",
								"1");

					} else {
						DBUtils.executeDBOperation(ctx1, "CorpCommBusinessLW",
								"2");
						DBUtils.executeDBOperation(ctx1, "CorpCommBusinessLW",
								"1");
					}
				}
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}
		}
	}

	public void populateCCBFields(IContext ctx) throws Exception {
		List wesList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtspopulateCCBFields", ctx);

		ctx.remove("corporateSupp_freeform_1");
		ctx.remove("CorpOther");

		Map covMap = new HashMap();
		if (wesList != null) {
			Map map = new HashMap();
			for (int i = 0; i < wesList.size(); i++) {
				map = (HashMap) wesList.get(i);
				covMap.put("CCB_CheckedValue_" + map.get("CCBKey"), map
						.get("CCBCheckedValue"));
				covMap.put("CCB_CommentDesc_" + map.get("CCBKey"), map
						.get("CCBCommentDesc"));
				if (covMap.get("CCB_CheckedValue_12") != null
						&& covMap.get("CCB_CheckedValue_12").equals("Y")) {
					ctx.put("CorpOther", "Y");
				}
			}
		}
		ctx.put("corporateSupp_freeform_1", covMap);
	}

	protected static void percentageError(IContext ctx, int total,
			String percentage) throws Exception {
		populateError(ctx, "TotalAOP_Percentage",
				"The total AOP percentage is required to equal 100%, current total is "
						+ total + "%");
	}

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

	private String getAmountFormat(String amt) {
		NumberFormat nf = NumberFormat.getInstance();
		if (amt != null && !amt.equals("")) {
			long l = Long.parseLong(amt);
			return nf.format(l);
		}
		return "";
	}

	public static Object resolveAOPValue(Object bean, String key) {
		if (bean == null || key == null)
			return null;

		if (bean instanceof Map)
			return ((Map) bean).get(key);

		return BeanUtils.getBeanPropValue(bean, key);
	}

	public void checkIsAopSaved(IContext ctx) throws Exception {

		boolean isCompleteFlag = true;
		ctx.remove("isAopComplete");

		/*
		 * if (ctx.get("AOP_Percentage_5") != null &&
		 * !"".equals(ctx.get("AOP_Percentage_5").toString()) &&
		 * !"0".equals(ctx.get("AOP_Percentage_5").toString())) {
		 * 
		 * if (ctx.get("IsCorporatePage") == null) ctx.put("isAopComplete",
		 * "N"); else if (ctx.get("IsCorporatePage") != null &&
		 * ctx.get("IsCorporatePage").toString().equals("N"))
		 * 
		 * isCompleteFlag = false;
		 *  } if (ctx.get("AOP_Percentage_10") != null &&
		 * !ctx.get("AOP_Percentage_10").toString().equals("") &&
		 * !ctx.get("AOP_Percentage_10").toString().equals("0")) {
		 * 
		 * if (ctx.get("IsEnvironmentalPage") == null) ctx.put("isAopComplete",
		 * "N"); else if (ctx.get("IsEnvironmentalPage") != null &&
		 * ctx.get("IsEnvironmentalPage").toString().equals("N")) isCompleteFlag =
		 * false; } if (ctx.get("AOP_Percentage_12") != null &&
		 * !ctx.get("AOP_Percentage_12").toString().equals("") &&
		 * !ctx.get("AOP_Percentage_12").toString().equals("0")) {
		 * 
		 * if (ctx.get("IsFinancialInsPage") == null) ctx.put("isAopComplete",
		 * "N"); else if (ctx.get("IsFinancialInsPage") != null &&
		 * ctx.get("IsFinancialInsPage").toString().equals("N")) isCompleteFlag =
		 * false;
		 *  } if (ctx.get("AOP_Percentage_18") != null &&
		 * !ctx.get("AOP_Percentage_18").toString().equals("") &&
		 * !ctx.get("AOP_Percentage_18").toString().equals("0")) {
		 * 
		 * if (ctx.get("IsPlaintiffPage") == null) ctx.put("isAopComplete",
		 * "N"); else if (ctx.get("IsPlaintiffPage") != null &&
		 * ctx.get("IsPlaintiffPage").toString().equals("N")) isCompleteFlag =
		 * false;
		 *  }
		 */
		if (ctx.get("AOP_Percentage_20") != null
				&& !ctx.get("AOP_Percentage_20").toString().equals("")
				&& !ctx.get("AOP_Percentage_20").toString().equals("0")) {

			if (ctx.get("IsRealEstatePage") == null)
				ctx.put("isAopComplete", "N");
			else if (ctx.get("IsRealEstatePage") != null
					&& ctx.get("IsRealEstatePage").toString().equals("N"))
				isCompleteFlag = false;

		}
		/*
		 * if (ctx.get("AOP_Percentage_22") != null &&
		 * !ctx.get("AOP_Percentage_22").toString().equals("") &&
		 * !ctx.get("AOP_Percentage_22").toString().equals("0")) {
		 * 
		 * if (ctx.get("IsTaxPage") == null) ctx.put("IsTaxPage", "N"); else if
		 * (ctx.get("IsTaxPage") != null &&
		 * ctx.get("IsTaxPage").toString().equals("N")) isCompleteFlag = false;
		 *  } if (ctx.get("AOP_Percentage_23") != null &&
		 * !ctx.get("AOP_Percentage_23").toString().equals("") &&
		 * !ctx.get("AOP_Percentage_23").toString().equals("0")) {
		 * 
		 * if (ctx.get("IsTitlePage") == null) ctx.put("IsTitlePage", "N"); else
		 * if (ctx.get("IsTitlePage") != null &&
		 * ctx.get("IsTitlePage").toString().equals("N")) isCompleteFlag =
		 * false;
		 *  } if (ctx.get("AOP_Percentage_24") != null &&
		 * !ctx.get("AOP_Percentage_24").toString().equals("") &&
		 * !ctx.get("AOP_Percentage_24").toString().equals("0")) {
		 * 
		 * if (ctx.get("IsWillsEstatePage") == null)
		 * ctx.put("IsWillsEstatePage", "N"); else if
		 * (ctx.get("IsWillsEstatePage") != null &&
		 * ctx.get("IsWillsEstatePage").toString().equals("N")) isCompleteFlag =
		 * false;
		 *  }
		 */
		if (!isCompleteFlag) {
			populateError(ctx, "isAopComplete",
					"Please complete Real Estate Supplement below");
			ctx.put("isAopComplete", "N");
		}

	}

	public void cleanAopSupplement(IContext ctx) throws Exception {

		/*
		if (ctx.get("currentSuppPage") != null) {

			if (ctx.get("IsEnvironmentalPage") != null) {
				if (!ctx.get("currentSuppPage").toString().equals(
						"environmentalSupp")
						&& !"Y".equals(ctx.get("IsEnvironmentalPage")
								.toString())) {

					SqlResources.getSqlMapProcessor(ctx).delete(
							"AttorneyEnvWorkLW.deleteAllByPartialKey", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete(
							"FirmEnvClientsLW.deleteAllByPartialKey", ctx);
					SqlResources.getSqlMapProcessor(ctx).delete(
							"ContractorEnvLW.deleteAllByPartialKey", ctx);

				}
			}
			if (ctx.get("IsFinancialInsPage") != null) {
				if (!ctx.get("currentSuppPage").toString().equals(
						"financialInstitutionSupp")
						&& !"Y"
								.equals(ctx.get("IsFinancialInsPage")
										.toString())) {

					SqlResources
							.getSqlMapProcessor(ctx)
							.delete(
									"FinancialInstitutionLW.deleteAllByPartialKey",
									ctx);

				}
			}
			if (ctx.get("IsPlaintiffPage") != null) {
				if (!ctx.get("currentSuppPage").toString().equals(
						"plaintiffSupp")
						&& !"Y".equals(ctx.get("IsPlaintiffPage").toString())) {

					SqlResources
							.getSqlMapProcessor(ctx)
							.delete(
									"AttorneyInPlaintiffForFirmLW.deleteAllByPartialKey",
									ctx);

				}
			}
			if (ctx.get("IsTaxPage") != null) {
				if (!ctx.get("currentSuppPage").toString().equals("taxSupp")
						&& !"Y".equals(ctx.get("IsTaxPage").toString())) {

					SqlResources.getSqlMapProcessor(ctx).delete(
							"AttorneyHaveTaxWorkLW.deleteAllByPartialKey", ctx);

				}
			}

		}
		*/
	}

	public void getCurrentSuppPage(IContext ctx) throws Exception {

		//ctx.remove("currentSuppPage");		
		
//		if (ctx.get("AOP_Percentage_20") != null
//				&& !"".equals(ctx.get("AOP_Percentage_20").toString()) && !"0".equals(ctx.get("AOP_Percentage_20").toString()))
//			ctx.put("currentSuppPage", "realEstateSupp");
//		else {

			if (ctx.get("currentSuppPage") == null
					|| (ctx.get("currentSuppPage") != null)
					&& "".equals(ctx.get("currentSuppPage").toString()))
				RuleUtils.executeRule(ctx, "LawyersRule.getfirstSuppPage1");

			ctx.get("currentSuppPage");
		

	}

	public void savePopulateFieldsQuickQuote(IContext ctx) throws Exception {
		Context ctx1;
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select(
				"AOPLW.findAll", ctx);

		if (limtTypes != null) {
			int totalAOPpercent = checkAOPPercentage(limtTypes, ctx);

			try {
				Map map = new HashMap();
				for (int i = 0; i < limtTypes.size(); i++) {
					map = (HashMap) limtTypes.get(i);
					String percentageValue = ctx.get("AOP_Percentage_" + map.get("AOPKey").toString()) == null ? null: ctx.get("AOP_Percentage_" + map.get("AOPKey").toString()).toString().trim();
					if (percentageValue == null || "".equals(percentageValue) || "0".equals(percentageValue))
						percentageValue = "0";

					ctx1 = new Context();
					ctx1.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
					ctx1.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
					ctx1.put("VersionSequence", ctx.get("VersionSequence"));
					ctx1.put("PolicyKey", ctx.get("PolicyKey"));
					ctx1.put("VersionKey", ctx.get("VersionKey"));
					ctx1.put("PercentageValue", percentageValue);
					if ("58".equals(map.get("AOPKey").toString())
							|| "67".equals(map.get("AOPKey").toString())
							|| "25".equals(map.get("AOPKey").toString()))
						ctx1.put("AOPCommentDesc", ctx.get("AOPCommentDesc_" + map.get("AOPKey").toString()));

					ctx1.put("AOPKey", map.get("AOPKey"));

					ctx1.setProject(ctx.getProject());
					Object obj2 = DBUtils.executeDBOperation(ctx1, "AreaOfPracticeQuickQuoteLW", "3");

					if (obj2 == null) {
						DBUtils.executeDBOperation(ctx1, "AreaOfPracticeQuickQuoteLW", "1");

					} else {
						DBUtils.executeDBOperation(ctx1, "AreaOfPracticeQuickQuoteLW", "2");
						DBUtils.executeDBOperation(ctx1, "AreaOfPracticeQuickQuoteLW", "1");
					}
				}
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}
			// }
			// }
		}

	}

	@SuppressWarnings("unchecked")
	public void populateAOPFieldsQuickQuote(IContext ctx) throws Exception {
		List aoplist = SqlResources
				.getSqlMapProcessor(ctx)
				.select(
						"SqlStmts.UserStatementsaveAccountstmtspopulateAOPFieldsQuickQuote",
						ctx);
		ctx.remove("AOP_freeform_20");

		Map covMap = new HashMap();
		if (aoplist != null) {
			Map map = new HashMap();
			for (int i = 0; i < aoplist.size(); i++) {
				map = (HashMap) aoplist.get(i);
				covMap.put("AOP_Percentage_" + map.get("AOPKey"), map
						.get("PercentageValue"));
				// ctx.put("AOP_Percentage_"+map.get("AOPKey"),
				// map.get("PercentageValue"));
				covMap.put("AOPCommentDesc_" + map.get("AOPKey"), map
						.get("AOPCommentDesc"));
			}
		}

		ctx.put("AOP_Percentage_1", covMap.get("AOP_Percentage_1"));
		ctx.put("AOP_Percentage_2", covMap.get("AOP_Percentage_2"));
		ctx.put("AOP_Percentage_13", covMap.get("AOP_Percentage_13"));
		ctx.put("AOP_Percentage_10", covMap.get("AOP_Percentage_10"));
		ctx.put("AOP_Percentage_18", covMap.get("AOP_Percentage_18"));

		ctx.put("AOP_freeform_20", covMap);
		// return covMap;

	}

	public void showQuickQuoteList(IContext ctx) throws Exception {

		boolean flag = true;

		/*
		 * Object objAmount = ctx.get("Amount"); if(objAmount != null) { long
		 * amt = Long.parseLong(objAmount.toString()); if(amt > 750000) flag =
		 * false; }
		 * 
		 * Object objOther = ctx.get("AOP_Percentage_20"); if(objOther != null) {
		 * int per = Integer.parseInt(objOther.toString()); if(per > 0) flag =
		 * false; }
		 * 
		 * Object objClaim = ctx.get("IsClaimsInLast5Year"); if(objClaim !=
		 * null) { if("Y".equals(objClaim.toString())) flag = false; }
		 * 
		 * Object objDate1 = ctx.get("PolicyEffectiveDate"); if(objDate1 !=
		 * null) {
		 * 
		 * Date policyEffDate =
		 * DateUtils.convertStringToDate(objDate1.toString(), "mm/dd/yyyy");
		 * long diff = DateUtils.calculateDiffInDays(policyEffDate,
		 * DateUtils.getTodaysDate()); if(diff < 5) flag = false; }
		 * 
		 * Object objStateCode = ctx.get("StateCode"); if(objStateCode != null) {
		 * if("FL".equals(objStateCode.toString()) ||
		 * "NY".equals(objStateCode.toString()) ||
		 * "AR".equals(objStateCode.toString()) ||
		 * "OK".equals(objStateCode.toString())||
		 * "VT".equals(objStateCode.toString())) flag = false; }
		 */

		if (flag)
			ctx.put("hideQuoteList", "N");
		else {
			if (ctx.get("User") != null) {
				String user = ctx.get("User").toString();
				if ("insured".equals(user))
					ctx.put("hideQuoteList", "Y");
				else
					ctx.put("hideQuoteList", "N");
			}
		}

	}

	public static void testBo(Context ctx) throws Exception {

		logger.debug("TestBo");

	}
	public static void addNewAopData(Context ctx)
	{
		
	        int total=0,percentage=0;
	        try
	        {
	        	 Context ctx3=new Context();
  				 ctx3.setProject(ctx.getProject());
  				 ctx3.putAll(ctx);
	        	 List limtTypes = SqlResources.getSqlMapProcessor(ctx3).select("AOPLW.findAll", ctx3);
	             List finalList = new ArrayList();
	             if(ctx.get("aoplist_list_2") != null &&  ctx.get("aoplist_list_2") instanceof List)
	             {
	                 finalList = (List)ctx.get("aoplist_list_2");
	             }
	             
	            
	             Map map = new HashMap();
	             for (int i = 0; i < finalList.size(); i++) 
	     		{	
	            	Context ctx1=new Context();
     				ctx1.setProject(ctx.getProject());
     				map = (HashMap) finalList.get(i);
     				ctx1.put("PolicyKey",ctx.get("PolicyKey"));
     				ctx1.put("AOPkey",map.get("Aopkey"));
     				ctx1.put("percentage",ctx.get("AOP_Percentage_"+i));
     				ctx1.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
     				ctx1.put("UserNo",ctx.get("UserNo"));
					ctx1.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
     				SqlResources.getSqlMapProcessor(ctx1).update("SqlStmts.UserStatementManualBoQueriesupdateSmartSearchValue", ctx1);
     				percentage=ctx.get("AOP_Percentage_"+i)!=null && !ctx.get("AOP_Percentage_"+i).equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("AOP_Percentage_"+i).toString()):0;;
     				total=total+percentage;
	     		}
	             ctx.put("totalPercentage", total);
	 			 Context ctx2=new Context();
  				 ctx2.setProject(ctx.getProject());
  				 ctx2.putAll(ctx);
  				 for (int i = 0; i < finalList.size(); i++) 
   	     		{
  					ctx2.remove("AOP_Percentage_"+i);
   					
   	     		}
  				Map map1 = new HashMap();
  				 for (int i = 0; i < finalList.size(); i++) 
  	     		{
  					map1 = (HashMap) finalList.get(i);
  					ctx2.put("AOP_Percentage_"+map1.get("Aopkey"),ctx.get("AOP_Percentage_"+i));
  	     		}
  				 
  				List inputList=new ArrayList();
  	    		
  				if(limtTypes!=null)
  	    		{	
  	    			Map map2=null;
  	    			for(int i=0;i<limtTypes.size();i++)
  	    			{
  	    				map2=new HashMap();
  	    				map1=(HashMap) limtTypes.get(i);
  	    				map2.put("AOPKey", map1.get("AOPKey"));
  	    				map2.put("PercentageValue",ctx2.get("AOP_Percentage_"+map1.get("AOPKey")));
  	    				
  	    				int key=map1.get("AOPKey")!=null && !map1.get("AOPKey").equals(HtmlConstants.EMPTY)?Integer.parseInt(map1.get("AOPKey").toString()):0;
  	    				if(key==25)
  	    				{
  	    					int aopPercentage=ctx2.get("AOP_Percentage_"+map1.get("AOPKey"))!=null && !ctx2.get("AOP_Percentage_"+map1.get("AOPKey")).equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx2.get("AOP_Percentage_"+map1.get("AOPKey")).toString()):0;;
  	    					if(aopPercentage>0)
  	    					{
  	    						
  	    						if(ctx2.get("otherAopDescLaw")!=null && !ctx2.get("otherAopDescLaw").equals(HtmlConstants.EMPTY))
  	    						map2.put("AOPCommentDesc", ctx2.get("otherAopDescLaw"));
  	    						else
  	    						{
  	    							populateError(ctx, "otherAopDescLaw","Please provide description.");
  	    							logger.debug("unable to find other Aop description in request");
  	    						}
  	    					}
  	    					
  	    				}
  	    				else if(key==58)
  	    				{
  	    					int aopPercentage=ctx2.get("AOP_Percentage_"+map1.get("AOPKey"))!=null && !ctx2.get("AOP_Percentage_"+map1.get("AOPKey")).equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx2.get("AOP_Percentage_"+map1.get("AOPKey")).toString()):0;;
  	    					if(aopPercentage>0)
  	    					{
  	    						if(ctx2.get("litDefenseOtherDesc")!=null && !ctx2.get("litDefenseOtherDesc").equals(HtmlConstants.EMPTY))
  	    						map2.put("AOPCommentDesc", ctx2.get("litDefenseOtherDesc"));
  	    						else
  	    						{
  	    							populateError(ctx, "litDefenseOtherDesc","Please provide description.");
  	    							logger.debug(" Unable to find  litigation Defense  Aop description in request");
  	    						}
  	    					
  	    				
  	    					}
  	    				}
  	    				else if(key==67)
  	    				{
  	    					int aopPercentage=ctx2.get("AOP_Percentage_"+map1.get("AOPKey"))!=null && !ctx2.get("AOP_Percentage_"+map1.get("AOPKey")).equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx2.get("AOP_Percentage_"+map1.get("AOPKey")).toString()):0;
  	    					if(aopPercentage>0)
  	    					{
  	    						
  	    						if(ctx2.get("litPlaintiffotherDesc")!=null && !ctx2.get("litPlaintiffotherDesc").equals(HtmlConstants.EMPTY))
  	    						map2.put("AOPCommentDesc", ctx2.get("litPlaintiffotherDesc"));
  	    						else
  	    						{
  	    							populateError(ctx, "litPlaintiffotherDesc","Please provide description.");
  	    							logger.debug(" Unable to find  litigation Plaintiff other description in request");
  	    						}
  	    					}
  	    					
  	    				}
  	    				
  	    				else
  	    				map2.put("AOPCommentDesc",ctx2.get("AOPCommentDesc_"+map1.get("AOPKey"))!=null&& !ctx2.get("AOPCommentDesc_"+map1.get("AOPKey")).equals(HtmlConstants.EMPTY)?ctx2.get("AOPCommentDesc_"+map1.get("AOPKey")).toString():null );
  	    				map2.put("PolicyKey", ctx2.get("PolicyKey"));
  	    				map2.put("VersionSequence", ctx2.get("VersionSequence"));
  	    				map2.put("VersionKey", ctx2.get("VersionKey"));
  	    				map2.put("CreatedBy", ctx.get("LastUpdateUserID"));
						map2.put("CreatedDate",ctx.get("LastUpdateTimeStamp"));
  	    				inputList.add(map2);
  	    			}
  	    			ctx2.put("inputList", inputList);
  	    			LawyersUtils.convertListDataToXML(ctx2,"inputList","outputList");
  	    			ctx.put("outputList", ctx2.get("outputList"));
  	    		}
	        }
	        catch(Exception e)
	        {
	        	logger.error("Unexpected error", e);	        	
	        }
		
	}
	public static void  populateLitigationSupplementFields(Context ctx) 
	{
		try
		{
		List wesList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOLFields", ctx);
		int totalPercentage = 0;
		long totalLargestCase = 0;
		long totalPercentageOfDefense = 0;
		long combinedTotal=0;
		Map covMap = new HashMap();
		
		if (wesList != null)
		{
			Map map = new HashMap();
			for (int i = 0; i < wesList.size(); i++) {
				map = (HashMap) wesList.get(i);

				ctx.put("AOL_PercentageOfRevenue_" + map.get("AOLKey"), map.get("PercentageOfRevenue"));

				if (map.get("PercentageOfRevenue") != null) {
					totalPercentage = totalPercentage+ Integer.parseInt(map.get("PercentageOfRevenue").toString());
				}
				ctx.put("AOL_PercentageOfDefense_" + map.get("AOLKey"), map.get("PercentageOfDefense"));
				if (map.get("PercentageOfDefense") != null) {
					totalPercentageOfDefense = totalPercentageOfDefense+ Long.parseLong(map.get("PercentageOfDefense").toString());
				}
				ctx.put("AOL_LargestCaseSize_" + map.get("AOLKey"), map.get("LargestCaseSize"));
				if (map.get("LargestCaseSize") != null) {
					totalLargestCase = totalLargestCase+ Long.parseLong(map.get("LargestCaseSize").toString());
				}

				ctx.put("AOL_CommentDesc_" + map.get("AOLKey"), map.get("AOLCommentDesc"));

				if (covMap.get("AOL_CommentDesc_13") != null && !covMap.get("AOL_CommentDesc_13").equals("")) {
					ctx.put("PlaintiffOther", "Y");
				
				
				}
				if("13".equals(map.get("AOLKey").toString()))
				{
					ctx.put("AOLOtherCommentDesc", map.get("AOLCommentDesc"));
					ctx.put("DefenseOtherCommentDesc", map.get("DefenseCommentDesc"));
				}
			}
			
			combinedTotal=totalPercentage+totalPercentageOfDefense;
			ctx.put("combinedTotal", combinedTotal);
			
		}
		
		/*ctx.put("TotalAOL_PercentageOfRevenue", totalPercentage);
		String percentageOfDefense = getAmountFormat(String.valueOf(totalPercentageOfDefense));
		ctx.put("TotalAOL_PercentageOfDefense", percentageOfDefense);
		String largestCase = getAmountFormat(String.valueOf(totalLargestCase));
		ctx.put("TotalAOL_LargestCaseSize", largestCase);
		*/
	}
		catch(Exception e)
	{
		logger.error("Unexpected error", e);
	}
	}
	/*public static void saveLitigationSupplementFields(Context ctx) 
	{
		int totalPercentage = 0;
		int totalPercentageOfDefense = 0;
		int combinedTotal=0;
		int caseSize=0;
		Context newCtx=new Context();
		newCtx.setProject(ctx.getProject());
		newCtx.putAll(ctx);
		try
		{
		List wesList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAOLLWFullDetails", ctx);
		Map covMap = null;
		List inputList=new ArrayList();
		if (wesList != null)
		{
			for (int i = 0; i < wesList.size(); i++) 
			{
			newCtx.remove("AOL_LargestCaseSize_"+i);
			}
			//validateLitigationPercentage(ctx);
			Map map = new HashMap();
			for (int i = 0; i < wesList.size(); i++) 
			{	covMap = new HashMap();
				map = (HashMap) wesList.get(i);
				covMap.put("AOLKey",map.get("AOLKey"));
				//covMap.put("AOL_PercentageOfRevenue_" + map.get("AOLKey"), map.get("PercentageOfRevenue"));
				covMap.put("PercentageOfRevenue",ctx.get("AOL_PercentageOfRevenue_" + map.get("AOLKey"))); 
				covMap.put("PercentageOfDefense",ctx.get("AOL_PercentageOfDefense_" + map.get("AOLKey")));
				if(ctx.get("AOL_LargestCaseSize_" + map.get("AOLKey"))!=null && !ctx.get("AOL_LargestCaseSize_" + map.get("AOLKey")).equals(HtmlConstants.EMPTY))
					
					caseSize=Integer.parseInt(removeAmountFormat(ctx.get("AOL_LargestCaseSize_" + map.get("AOLKey"))).toString());
				else 
					caseSize=0;
				covMap.put("LargestCaseSize",caseSize);
				if("13".equals(map.get("AOLKey").toString()))
				{
					if(covMap.get("PercentageOfRevenue") != null && !covMap.get("PercentageOfRevenue").equals(HtmlConstants.EMPTY))
					{
						if(Integer.parseInt(covMap.get("PercentageOfRevenue").toString())>0)
							covMap.put("AOLCommentDesc",ctx.get("AOLOtherCommentDesc"));
					}
					if(covMap.get("PercentageOfDefense") != null && !covMap.get("PercentageOfDefense").equals(HtmlConstants.EMPTY))
					{
						if(Integer.parseInt(covMap.get("PercentageOfDefense").toString())>0)
							covMap.put("DefenseCommentDesc",ctx.get("DefenseOtherCommentDesc"));
					}
					if(caseSize>0)
						covMap.put("AOLCommentDesc",ctx.get("AOLOtherCommentDesc"));
				}
				else
				{
				covMap.put("AOLCommentDesc",null);
				covMap.put("DefenseCommentDesc",null);
				}
				covMap.put("PolicyKey",ctx.get("PolicyKey"));
				covMap.put("VersionSequence",ctx.get("VersionSequence"));
				inputList.add(covMap);
				
				if(ctx.get("AOL_PercentageOfRevenue_" + map.get("AOLKey"))!=null )
				{
					if (covMap.get("PercentageOfRevenue") != null && !covMap.get("PercentageOfRevenue").equals(HtmlConstants.EMPTY)) {
						totalPercentage = totalPercentage+ Integer.parseInt(covMap.get("PercentageOfRevenue").toString());
					}
				}
				if(ctx.get("AOL_PercentageOfDefense_" + map.get("AOLKey"))!=null )
				{
					if (covMap.get("PercentageOfDefense") != null && !covMap.get("PercentageOfDefense").equals(HtmlConstants.EMPTY)) {
						totalPercentageOfDefense = totalPercentageOfDefense+ Integer.parseInt(covMap.get("PercentageOfDefense").toString());
					}
				}
				
			}
			combinedTotal=totalPercentageOfDefense+totalPercentage;
			
			newCtx.put("inputList", inputList);
			LawyersUtils.convertListDataToXML(newCtx,"inputList","outputList");
			ctx.put("outputList",newCtx.get("outputList"));
		}
	}
		catch(Exception e)
	{
		e.printStackTrace();
	}
	}*/
	public static void saveLitigationSupplementFields(Context ctx) 
	{
		int totalPercentage = 0;
		int totalPercentageOfDefense = 0;
		int combinedTotal=0;
		int caseSize=0;
		Context newCtx=new Context();
		newCtx.setProject(ctx.getProject());
		newCtx.putAll(ctx);
		try
		{
		List wesList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAOLLWFullDetails", ctx);
		Map covMap = null;
		List inputList=new ArrayList();
		if (wesList != null)
		{
			for (int i = 0; i < wesList.size(); i++) 
			{
			newCtx.remove("AOL_LargestCaseSize_"+i);
			}
			//validateLitigationPercentage(ctx);
			Map map = new HashMap();
			for (int i = 0; i < wesList.size(); i++) 
			{	covMap = new HashMap();
				map = (HashMap) wesList.get(i);
				covMap.put("AOLKey",map.get("AOLKey"));
				//covMap.put("AOL_PercentageOfRevenue_" + map.get("AOLKey"), map.get("PercentageOfRevenue"));
				covMap.put("PercentageOfRevenue",ctx.get("AOL_PercentageOfRevenue_" + map.get("AOLKey"))); 
				covMap.put("PercentageOfDefense",ctx.get("AOL_PercentageOfDefense_" + map.get("AOLKey")));
				if(ctx.get("AOL_LargestCaseSize_" + map.get("AOLKey"))!=null && !ctx.get("AOL_LargestCaseSize_" + map.get("AOLKey")).equals(HtmlConstants.EMPTY))
					
					caseSize=Integer.parseInt(removeAmountFormat(ctx.get("AOL_LargestCaseSize_" + map.get("AOLKey"))).toString());
				else 
					caseSize=0;
				covMap.put("LargestCaseSize",caseSize);
				if("13".equals(map.get("AOLKey").toString()))
				{
					if(covMap.get("PercentageOfRevenue") != null && !covMap.get("PercentageOfRevenue").equals(HtmlConstants.EMPTY))
					{
						if(Integer.parseInt(covMap.get("PercentageOfRevenue").toString())>0)
							covMap.put("AOLCommentDesc",ctx.get("AOLOtherCommentDesc"));
					}
					if(covMap.get("PercentageOfDefense") != null && !covMap.get("PercentageOfDefense").equals(HtmlConstants.EMPTY))
					{
						if(Integer.parseInt(covMap.get("PercentageOfDefense").toString())>0)
							covMap.put("DefenseCommentDesc",ctx.get("DefenseOtherCommentDesc"));
					}
				}
				else
				{
				covMap.put("AOLCommentDesc",null);
				covMap.put("DefenseCommentDesc",null);
				}
				covMap.put("PolicyKey",ctx.get("PolicyKey"));
				covMap.put("VersionSequence",ctx.get("VersionSequence"));
				covMap.put("VersionKey",ctx.get("VersionKey"));
				if(ctx.get("CreatedBy_"+i)==null || ctx.get("CreatedBy_"+i).equals(HtmlConstants.EMPTY))
					covMap.put("CreatedBy", ctx.get("LastUpdateUserID"));
				else
					covMap.put("CreatedBy", ctx.get("CreatedBy_"+i));
				
				if(ctx.get("CreatedDate_"+i)==null || ctx.get("CreatedDate_"+i).equals(HtmlConstants.EMPTY))
					covMap.put("CreatedDate",ctx.get("LastUpdateTimeStamp"));
				else
					covMap.put("CreatedDate",ctx.get("CreatedDate_"+i));
				inputList.add(covMap);
				
				if(ctx.get("AOL_PercentageOfRevenue_" + map.get("AOLKey"))!=null )
				{
					if (covMap.get("PercentageOfRevenue") != null && !covMap.get("PercentageOfRevenue").equals(HtmlConstants.EMPTY)) {
						totalPercentage = totalPercentage+ Integer.parseInt(covMap.get("PercentageOfRevenue").toString());
					}
				}
				if(ctx.get("AOL_PercentageOfDefense_" + map.get("AOLKey"))!=null )
				{
					if (covMap.get("PercentageOfDefense") != null && !covMap.get("PercentageOfDefense").equals(HtmlConstants.EMPTY)) {
						totalPercentageOfDefense = totalPercentageOfDefense+ Integer.parseInt(covMap.get("PercentageOfDefense").toString());
					}
				}
				
			}
			combinedTotal=totalPercentageOfDefense+totalPercentage;
			
			newCtx.put("inputList", inputList);
			LawyersUtils.convertListDataToXML(newCtx,"inputList","outputList");
			ctx.put("outputList",newCtx.get("outputList"));
		}
	}
		catch(Exception e)
	{
		logger.error("Unexpected error", e);
	}
	}
	/*public static void validateLitigationPercentage(Context ctx)
			throws Exception 
	{
		int total = 0,litigationPercentage=0,percentageOfDefense=0;
		int defenseTotal=0,percentageValue=0;
		long largestCasesSize=0;
		long combinedTotal=0;
		Map map = new HashMap();
		List limtTypes =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAOLLWFullDetailsNewApp", ctx);
		getAopsValues(ctx);
		if (limtTypes != null) {
			for (int i = 0; i < limtTypes.size(); i++) {
				map = (HashMap) limtTypes.get(i);
				int AOLKey= map.get("AOLKey")!= null && !map.get("AOLKey").equals(HtmlConstants.EMPTY)? Integer.parseInt(map.get("AOLKey").toString()):0;;
				 percentageValue = ctx.get("AOL_PercentageOfRevenue_"+ AOLKey)!= null && !ctx.get("AOL_PercentageOfRevenue_"+ map.get("AOLKey")).equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("AOL_PercentageOfRevenue_"+ AOLKey).toString()):0;
					// percentageOfDefense = ctx.get("AOL_PercentageOfDefense_"+ AOLKey) != null && !ctx.get("AOL_PercentageOfDefense_"+ map.get("AOLKey")).equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("AOL_PercentageOfDefense_"+ AOLKey):0;
				
					largestCasesSize=ctx.get("AOL_LargestCaseSize_"+AOLKey ) != null && !ctx.get("AOL_LargestCaseSize_"+ map.get("AOLKey")).equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("AOL_LargestCaseSize_"+ AOLKey).toString().replace("$","").replace(",","")):-1;
					
				
					if((Integer.parseInt(ctx.get("aopPercentage_55").toString())>50 && AOLKey==14)
							||(Integer.parseInt(ctx.get("aopPercentage_56").toString())>50 && AOLKey==15)
							||(Integer.parseInt(ctx.get("aopPercentage_57").toString())>50 && AOLKey==4)
							||(Integer.parseInt(ctx.get("aopPercentage_67").toString())>50 && AOLKey==13)
							||(Integer.parseInt(ctx.get("aopPercentage_59").toString())>50 && AOLKey==11)
							||(Integer.parseInt(ctx.get("aopPercentage_60").toString())>50 && AOLKey==5)
							||(Integer.parseInt(ctx.get("aopPercentage_61").toString())>50 && AOLKey==9)
							||(Integer.parseInt(ctx.get("aopPercentage_62").toString())>50 && AOLKey==6)
							||(Integer.parseInt(ctx.get("aopPercentage_63").toString())>50 && AOLKey==8))
							
				
					populateLargestCaseError(ctx,largestCasesSize,map.get("AOLDescNew").toString());
				
				
				if(AOLKey==13 && (Integer.parseInt(ctx.get("aopPercentage_67").toString())>50))
				{
					if(ctx.get("AOLOtherCommentDesc")== null || ctx.get("AOLOtherCommentDesc").equals(HtmlConstants.EMPTY))
						populateError(ctx, "AOLOtherCommentDescError","Description required.");

				}
				
			}
			
		}
	
	}*/
	
	public static void validateLitigationPercentage(Context ctx)
	{
		try
	{
		int total = 0,litigationPercentage=0,percentageOfDefense=0;
		int defenseTotal=0,percentageValue=0;
		long largestCasesSize=0;
		long combinedTotal=0;
		
		boolean isBeforeLitigation=(Boolean) RuleUtils.executeRule(ctx, "LawyersRule.beforeLitigationImplementationDate");
		Map map = new HashMap();
		List limtTypes =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAOLLWFullDetailsNewApp", ctx);
		if (limtTypes != null) {
			if(isBeforeLitigation)
			{
			for (int i = 0; i < limtTypes.size(); i++) {
				map = (HashMap) limtTypes.get(i);
				 percentageValue = ctx.get("AOL_PercentageOfRevenue_"+ map.get("AOLKey").toString())!= null && !ctx.get("AOL_PercentageOfRevenue_"+ map.get("AOLKey")).equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("AOL_PercentageOfRevenue_"+ map.get("AOLKey")).toString()):0;
				 percentageOfDefense = ctx.get("AOL_PercentageOfDefense_"+ map.get("AOLKey").toString()) != null && !ctx.get("AOL_PercentageOfDefense_"+ map.get("AOLKey")).equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("AOL_PercentageOfDefense_"+ map.get("AOLKey")).toString()):0;
				try {
					total = total + percentageValue;
					defenseTotal=defenseTotal+percentageOfDefense;
				} catch (Exception e) {
					populateError(ctx, "TotalAOL_PercentageOfRevenue","Invalid AOP data");
				}
				largestCasesSize=ctx.get("AOL_LargestCaseSize_"+ map.get("AOLKey").toString()) != null&& !ctx.get("AOL_LargestCaseSize_"+ map.get("AOLKey")).equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("AOL_LargestCaseSize_"+ map.get("AOLKey")).toString().replace("$","").replace(",", "")):0;
				litigationPercentage=ctx.get("aopPercentage_18")!=null && !ctx.get("aopPercentage_18").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("aopPercentage_18").toString()):0;
				if(litigationPercentage>50 && percentageValue>0 && largestCasesSize==-1)
				{
					//populateError(ctx, "largestCasesSizeError","Largest plaintiff case size required.");
					populateLargestCaseError(ctx,largestCasesSize,map.get("AOLDESCNew").toString());
				}
				if(map.get("AOLKey").toString().equals("13"))
				{
					if(percentageValue>0 && (ctx.get("AOLOtherCommentDesc")==null || ctx.get("AOLOtherCommentDesc").equals(HtmlConstants.EMPTY))) 
						populateError(ctx, "AOLOtherCommentDescError","Please provide description");
					if(percentageOfDefense>0 && (ctx.get("DefenseOtherCommentDesc")==null || ctx.get("DefenseOtherCommentDesc").equals(HtmlConstants.EMPTY))) 
						populateError(ctx, "DefenseOtherCommentDescError","Please provide description");
					
				}
				
			}
			int totalPlaintiffPercentage=total+defenseTotal;
			if(totalPlaintiffPercentage>0)
			{
				if(totalPlaintiffPercentage>100 || totalPlaintiffPercentage<100 )
				{
					populateError(ctx, "totalAOLPercentageOfRevenue","Plaintiff percentage and defense percentage should be 100%.");
				}
			}
			/*if(total>0)
			{
				if(total>100 || total<100 )
				{
					populateError(ctx, "totalAOLPercentageOfRevenue","Plaintiff percentage should be 100%.");
				}
			}
			if(defenseTotal>0)
			{
				if(defenseTotal>100 || defenseTotal<100 )
				{
					populateError(ctx, "totalAOLPercentageOfDefense","Defense percentage should be 100%.");
					
				}
			}*/
			ctx.put("aolPercentageValue",total);
			if(defenseTotal==0 && total==0)
			{
				populateError(ctx, "litigationValidationError","Please fill atleast any of Plaintiff or Defense.");
			}
		
		}
			else
			{
				for (int i = 0; i < limtTypes.size(); i++) {
					map = (HashMap) limtTypes.get(i);
					int AOLKey= map.get("AOLKey")!= null && !map.get("AOLKey").equals(HtmlConstants.EMPTY)? Integer.parseInt(map.get("AOLKey").toString()):0;;
					 percentageValue = ctx.get("AOL_PercentageOfRevenue_"+ AOLKey)!= null && !ctx.get("AOL_PercentageOfRevenue_"+ map.get("AOLKey")).equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("AOL_PercentageOfRevenue_"+ AOLKey).toString()):0;
						// percentageOfDefense = ctx.get("AOL_PercentageOfDefense_"+ AOLKey) != null && !ctx.get("AOL_PercentageOfDefense_"+ map.get("AOLKey")).equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("AOL_PercentageOfDefense_"+ AOLKey):0;
					
						largestCasesSize=ctx.get("AOL_LargestCaseSize_"+AOLKey ) != null && !ctx.get("AOL_LargestCaseSize_"+ map.get("AOLKey")).equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("AOL_LargestCaseSize_"+ AOLKey).toString().replace("$","").replace(",","")):0;
						
					
						if((getContextInt(ctx, "aopPercentage_55")>0 && AOLKey==14)
								||(getContextInt(ctx, "aopPercentage_56")>0 && AOLKey==15)
								||(getContextInt(ctx, "aopPercentage_57")>0 && AOLKey==4)
								||(getContextInt(ctx, "aopPercentage_67")>0 && AOLKey==13)
								||(getContextInt(ctx, "aopPercentage_59")>0 && AOLKey==11)
								||(getContextInt(ctx, "aopPercentage_60")>0 && AOLKey==5)
								||(getContextInt(ctx, "aopPercentage_61")>0 && AOLKey==9)
								||(getContextInt(ctx, "aopPercentage_62")>0 && AOLKey==6)
								||(getContextInt(ctx, "aopPercentage_63")>0 && AOLKey==8)) /// check value change from 50 to 0
								
					
						populateLargestCaseError(ctx,largestCasesSize,map.get("AOLDESCNew").toString());
					
					
					/*if(AOLKey==13 && (Integer.parseInt(ctx.get("aopPercentage_67").toString())>50))
					{
						if(ctx.get("AOLOtherCommentDesc")== null || ctx.get("AOLOtherCommentDesc").equals(HtmlConstants.EMPTY))
							populateError(ctx, "AOLOtherCommentDescError","Description required.");

					}*/
					
				}
				
			}
		
		}
	}catch(Exception e1)
		{logger.error("Unexpected error", e1);}
	
		
			
			
		
		/*if(combinedTotal>50)
		{
			if(largestCasesSize==0)
				populateError(ctx, "largestCasesSizeError","Largest Case reuired.");
		}*/
	}

	static int getContextInt(IContext ctx, String key) {
		Object value = ctx.get(key);
		return value == null || value.toString().trim().length() == 0
				? 0 : Integer.parseInt(value.toString().trim());
	}
	public static void validateRealEstateManually(Context ctx)
	{
		int percentageValue=0,commercialPercentageValue=0,total=0,cTotal=0;
		try
		{
			getAopsValues(ctx);
			List limtTypes = SqlResources.getSqlMapProcessor(ctx).select("AOPRELW.findAll", ctx);
			if (limtTypes != null) 
			{	HashMap map=null,map1=null;
				for (int i = 0; i < limtTypes.size(); i++) 
				{	
					percentageValue = ctx.get("PercentageValue_"+i) != null && !ctx.get("PercentageValue_"+i).equals(HtmlConstants.EMPTY)?  Integer.parseInt(ctx.get("PercentageValue_"+ i).toString()):0;
					commercialPercentageValue = ctx.get("CommercialPercentageValue_"+ i) != null && !ctx.get("CommercialPercentageValue_"+i).equals(HtmlConstants.EMPTY)? Integer.parseInt(ctx.get("CommercialPercentageValue_"+ i).toString()):0;
					total = total + percentageValue;
					cTotal=cTotal+commercialPercentageValue;
					if(i==11)
					{
						if(percentageValue>0)
						{
							if(ctx.get("AOPREOtherCommentDesc")== null || ctx.get("AOPREOtherCommentDesc").equals(HtmlConstants.EMPTY))
							{
								populateError(ctx, "aOPREOtherCommentDescError","Please provide description.");
							}
						}
								
						if(commercialPercentageValue>0)
						{
							if(ctx.get("AOPRECommOtherCommentDesc")== null || ctx.get("AOPRECommOtherCommentDesc").equals(HtmlConstants.EMPTY))
							{
								populateError(ctx, "aOPRECommOtherCommentDescError","Please provide description.");
							}
							
						}	
					}
						
				}
				
				int realComm=ctx.get("aopPercentage_20")!=null &&  !ctx.get("aopPercentage_20").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("aopPercentage_20").toString()):0;
				int realResi=ctx.get("aopPercentage_27")!=null &&  !ctx.get("aopPercentage_27").equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("aopPercentage_27").toString()):0;
				if(realResi!=0)
				{
					if(total>100 || total<100)
					populateError(ctx, "resiAopError","The total AOP percentage of  Residential Revenue should be equal to 100%");
					
					
				}
				if(realComm!=0)
				{
					if(cTotal>100 || cTotal<100)
					populateError(ctx, "realCommAopError","The total AOP percentage of Commercial Revenue should be equal to 100%");
					
				}
			}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
	}
	
	public static void viewRealEstateDetails(Context ctx)
	{
		int percentageValue=0,commercialPercentageValue=0;
		try
		{
			List limtTypes = (List)ctx.get("AOPRELWData_list_01");
			if (limtTypes != null) 
			{	HashMap map=null,map1=null;
				for (int i = 0; i < limtTypes.size(); i++) 
				{	
					map=(HashMap) limtTypes.get(i);
					percentageValue = map.get("PercentageValue") != null && !map.get("PercentageValue").equals(HtmlConstants.EMPTY)?  Integer.parseInt(map.get("PercentageValue").toString()):0;
					commercialPercentageValue = map.get("CommercialPercentageValue")!= null && !map.get("CommercialPercentageValue").equals(HtmlConstants.EMPTY)? Integer.parseInt(map.get("CommercialPercentageValue").toString()):0;
					int aopKey=  Integer.parseInt(map.get("AOPREKey").toString());
					if(aopKey==20)
					{
						if(percentageValue>0)
						ctx.put("AOPREOtherCommentDesc", map.get("AOPRECommentDesc"));
								
						if(commercialPercentageValue>0)
							ctx.put("AOPRECommOtherCommentDesc", map.get("AOPRECommCommentDesc") );
					}
						
				}
			
			}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
	}
	public static void saveRealEstateDetails(Context ctx)
	{
			int percentageValue=0,commercialPercentageValue=0,total=0,cTotal=0;
			HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
            HttpSession sess = req.getSession();
            List inputList=new ArrayList();
            try
            {
	            List finalList = new ArrayList();
	            if(sess.getAttribute("AOPRELWData_list_01") != null &&   sess.getAttribute("AOPRELWData_list_01") instanceof List)
	            {
	                finalList = (List)sess.getAttribute("AOPRELWData_list_01");
	            }
	            int j=0;
	            Map reqMap = new HashMap();
	            Context newCtx=new Context();
	            newCtx.setProject(ctx.getProject());
	            newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
	            newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
	    		for (int i = 0; i < finalList.size(); i++) 
	    		{
	    				reqMap = (HashMap) finalList.get(i);
	    				newCtx.put("AOPREKey",reqMap.get("AOPREKey"));
	    				newCtx.put("PercentageValue_"+reqMap.get("AOPREKey"),ctx.get("PercentageValue_"+i));
	    				newCtx.put("CommercialPercentageValue_"+reqMap.get("AOPREKey"),ctx.get("CommercialPercentageValue_"+i));
	    		}
	    		for (int i = 0; i < finalList.size(); i++) 
	    		{
	    				ctx.remove("PercentageValue_"+i);
	    				ctx.remove("CommercialPercentageValue_"+i);
	    		}
			
			List limtTypes = SqlResources.getSqlMapProcessor(ctx).select("AOPRELW.findAll", ctx);
			if (limtTypes != null) 
			{	HashMap map=null,map1=null;
				for (int i = 0; i < limtTypes.size(); i++) 
				{
					map = (HashMap) limtTypes.get(i);
					map1=new HashMap();
					
						map1.put("AOPREKey", map.get("AOPREKey"));
						map1.put("PercentageValue", newCtx.get("PercentageValue_"+ map.get("AOPREKey")));
						map1.put("CommercialPercentageValue", newCtx.get("CommercialPercentageValue_"+ map.get("AOPREKey")));
						map1.put("PolicyKey",ctx.get("PolicyKey"));
						map1.put("VersionSequence",ctx.get("VersionSequence"));
						map1.put("VersionKey",ctx.get("VersionKey"));
						if(ctx.get("CreatedBy_"+i)==null || ctx.get("CreatedBy_"+i).equals(HtmlConstants.EMPTY))
							map1.put("CreatedBy", ctx.get("LastUpdateUserID"));
						else
							map1.put("CreatedBy", ctx.get("CreatedBy_"+i));
						
						if(ctx.get("CreatedDate_"+i)==null || ctx.get("CreatedDate_"+i).equals(HtmlConstants.EMPTY))
							map1.put("CreatedDate",ctx.get("LastUpdateTimeStamp"));
						else
							map1.put("CreatedDate",ctx.get("CreatedDate_"+i));
						if(Integer.parseInt(map.get("AOPREKey").toString())==20)
						{
							percentageValue = newCtx.get("PercentageValue_"+ map.get("AOPREKey")) != null && !newCtx.get("PercentageValue_"+ map.get("AOPREKey")).equals(HtmlConstants.EMPTY)?  Integer.parseInt(newCtx.get("PercentageValue_"+ map.get("AOPREKey")).toString()):0;
							commercialPercentageValue = newCtx.get("CommercialPercentageValue_"+ map.get("AOPREKey")) != null && !newCtx.get("CommercialPercentageValue_"+ map.get("AOPREKey")).equals(HtmlConstants.EMPTY)? Integer.parseInt(newCtx.get("CommercialPercentageValue_"+ map.get("AOPREKey")).toString()):0;
							if(percentageValue>0)
								map1.put("AOPRECommentDesc", ctx.get("AOPREOtherCommentDesc"));
							else
								map1.put("AOPRECommentDesc", newCtx.get("AOPRECommentDesc_"+ map.get("AOPREKey")));
							
							if(commercialPercentageValue>0)
								map1.put("AOPRECommCommentDesc", ctx.get("AOPRECommOtherCommentDesc"));
							else
								map1.put("AOPRECommCommentDesc", newCtx.get("AOPRECommCommentDesc_"+ map.get("AOPREKey")));
						}
						else
						{
							map1.put("AOPRECommentDesc", newCtx.get("AOPRECommentDesc_"+ map.get("AOPREKey")));
							map1.put("AOPRECommCommentDesc", newCtx.get("AOPRECommCommentDesc_"+ map.get("AOPREKey")));
						}
						inputList.add(map1);
				}
				ctx.put("inputList",inputList);
				
				LawyersUtils.convertListDataToXML(ctx,"inputList","outputList");
			}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
	}
	public static void viewWillTrustEstatesData(Context ctx)
	{
		try
		{
			List wesList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetWillsEstateServicesLWDetails", ctx);
			if(wesList!=null)
			{
				Map map=null;
				for(int i=0;i<wesList.size();i++)
				{
					map = (HashMap) wesList.get(i);
					if(map.get("WESKey") != null && Integer.parseInt(map.get("WESKey").toString())==12)
					{
						ctx.put("otherWillsAopDesc", map.get("WESCommentDesc"));
					}
				}
				
			}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
	}
	
	public static void validateWillTrustEstatesData(Context ctx)
	{
		long total=0;
		int percentage=0;
		HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
        HttpSession sess = req.getSession();
        int wills12=0,wills3=0,wills0=0,wills1=0,wills2=0;
        List inputList=new ArrayList();
        try
        {
            
	            List finalList = new ArrayList();
	            if(sess.getAttribute("WTPData_list_01") != null &&   sess.getAttribute("WTPData_list_01") instanceof List)
	            {
	                finalList = (List)sess.getAttribute("WTPData_list_01");
	            }
	           for (int i = 0; i < finalList.size(); i++) 
	    		{
	        	 
	    				percentage=ctx.get("PercentageValue_"+i)!=null && ctx.get("PercentageValue_"+i).toString().trim().length()>0?Integer.parseInt(ctx.get("PercentageValue_"+i).toString()):0;
	    				 if(i==0)
	   					  	ctx.put("wills0", percentage);
	    				 if(i==1)
	    					 ctx.put("wills1", percentage);
	    				 if(i==2)
	    					 ctx.put("wills2", percentage);
	   					
	    				if(i==3)
	    					wills3=percentage;
	    				
	    				  if(i==12)
	    					  wills12=percentage;
	    				  
	    				  
	    				total=total+percentage;
	    		}
	    		
	    		if(total>100 || total<100)
	    			populateError(ctx, "Total_Percentage","The total of percentage is required to equal 100%, current total is "+ total + "%");
//	    		if(wills3>0 || wills12>0)
//	    		{
//	    			if(ctx.get("LargestTrust_list_01") != null &&   ctx.get("LargestTrust_list_01") instanceof List)
//			        {
//				            finalList = (List)ctx.get("LargestTrust_list_01");
//				       
//				        Map map = new HashMap();
//						for (int i = 0; i < finalList.size(); i++) 
//						{
//							if(i==0)
//							{
//								if( ctx.get("TrustName_"+i)==null || ctx.get("TrustName_"+i).equals(HtmlConstants.EMPTY) ||
//										ctx.get("TrustsType_"+i)==null || ctx.get("TrustsType_"+i).equals(HtmlConstants.EMPTY)	||
//										ctx.get("AssetsValue_"+i)==null || ctx.get("AssetsValue_"+i).equals(HtmlConstants.EMPTY)	||
//										ctx.get("BeneficiaryInterest_"+i)==null || ctx.get("BeneficiaryInterest_"+i).equals(HtmlConstants.EMPTY))
//									{
//										new LawyersUtils().populateError(ctx, "needFirstRow","You need to fill first row completely.");
//										return;
//									}
//							
//							}
//						}
//			        }
//	    		}
        }
        catch(Exception exception)
        { logger.error("Unexpected error", exception);}
	}
		
	
	public static void saveWillTrustEstatesData(Context ctx)
	{
			long total=0;
			int percentage=0;
			HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
            HttpSession sess = req.getSession();
            List inputList=new ArrayList();
            try
            {
	            
	            List finalList = new ArrayList();
	            if(sess.getAttribute("WTPData_list_01") != null &&   sess.getAttribute("WTPData_list_01") instanceof List)
	            {
	                finalList = (List)sess.getAttribute("WTPData_list_01");
	            }
	            int j=0;
	            Map reqMap = new HashMap();
	            Context newCtx=new Context();
	            newCtx.setProject(ctx.getProject());
	            newCtx.putAll(ctx);
	            
	    		for (int i = 0; i < finalList.size(); i++) 
	    		{
	    				reqMap = (HashMap) finalList.get(i);
	    				newCtx.put("WESKey",reqMap.get("WESKey"));
	    				newCtx.put("PercentageValue_"+reqMap.get("WESKey"),ctx.get("PercentageValue_"+i));
	    				newCtx.put("WESCommentDesc_"+reqMap.get("WESKey"),ctx.get("WESCommentDesc_"+i));
	    				percentage=ctx.get("PercentageValue_"+i)!=null && ctx.get("PercentageValue_"+i).toString().trim().length()>0?Integer.parseInt(ctx.get("PercentageValue_"+i).toString()):0;
	    			//	total=total+percentage;
	    		}
	    		
	    		/*if(total>100 || total<100)
	    			populateError(ctx, "Total_Percentage","The total of percentage is required to equal 100%, current total is "+ total + "%");*/
			List limtTypes = SqlResources.getSqlMapProcessor(ctx).select("WESLW.findAll", ctx);
			if (limtTypes != null) 
			{	HashMap map=null,map1=null;
				for (int i = 0; i < limtTypes.size(); i++) 
				{
					map = (HashMap) limtTypes.get(i);
					map1=new HashMap();
					
						map1.put("WESKey", map.get("WESKey"));
						map1.put("PercentageValue", newCtx.get("PercentageValue_"+ map.get("WESKey")));
						map1.put("PolicyKey",ctx.get("PolicyKey"));
						map1.put("VersionSequence",ctx.get("VersionSequence"));
						map1.put("VersionKey",ctx.get("VersionKey"));
						if(ctx.get("CreatedBy_"+i)==null || ctx.get("CreatedBy_"+i).equals(HtmlConstants.EMPTY))
							map1.put("CreatedBy", ctx.get("LastUpdateUserID"));
						else
							map1.put("CreatedBy", ctx.get("CreatedBy_"+i));
						
						if(ctx.get("CreatedDate_"+i)==null || ctx.get("CreatedDate_"+i).equals(HtmlConstants.EMPTY))
							map1.put("CreatedDate",ctx.get("LastUpdateTimeStamp"));
						else
							map1.put("CreatedDate",ctx.get("CreatedDate_"+i));
						if(Integer.parseInt(map.get("WESKey").toString())==12 )
						{
							if(newCtx.get("PercentageValue_"+ map.get("WESKey"))!=null && !newCtx.get("PercentageValue_"+ map.get("WESKey")).equals(HtmlConstants.EMPTY))
							{
								if(ctx.get("otherWillsAopDesc")!=null && ctx.get("otherWillsAopDesc").toString().length()!=0)
									map1.put("WESCommentDesc", ctx.get("otherWillsAopDesc"));
								else
									populateError(ctx, "otherDesc","Please provide description.");
							}
						}
						else
							map1.put("WESCommentDesc",null);
						
						inputList.add(map1);
				}
				for (int i = 1; i <=finalList.size(); i++) 
	    		{
	    			newCtx.remove("PercentageValue_"+i);
	    			newCtx.remove("WESCommentDesc_"+i);
	    				
	    		}
				newCtx.put("inputList",inputList);
				
				LawyersUtils.convertListDataToXML(newCtx,"inputList","outputList");
				ctx.put("outputList", newCtx.get("outputList"));
			}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
	}
	
	public static void saveWillsLargestTrustData(Context ctx)
	{
			int assetsValue=0;
			HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
            HttpSession sess = req.getSession();
            List inputList=new ArrayList();
            Context newCtx=new Context();
			newCtx.setProject(ctx.getProject());
			newCtx.putAll(ctx);
            try
            {
	            
	            List finalList = new ArrayList();
	            if(sess.getAttribute("LargestTrust_list_01") != null &&   sess.getAttribute("LargestTrust_list_01") instanceof List)
	            {
	                finalList = (List)sess.getAttribute("LargestTrust_list_01");
	                
	                for (int i = 0; i < finalList.size(); i++) 
					{
					newCtx.remove("AssetsValue_"+i);
					}
	            }
	           
	            int j=0;
	            Map reqMap = new HashMap();
	            Map map = null;;
	            for (int i = 0; i < finalList.size(); i++) 
	    		{
	    				reqMap = (HashMap) finalList.get(i);
	    				map=new HashMap();
	    				map.put("TrustName",ctx.get("TrustName_"+i));
	    				map.put("TrustsType",ctx.get("TrustsType_"+i));
	    				
	    				if(ctx.get("AssetsValue_"+i)!=null && !ctx.get("AssetsValue_"+i).equals(HtmlConstants.EMPTY))
	    					
	    					assetsValue=Integer.parseInt(removeAmountFormat(ctx.get("AssetsValue_"+i)).toString());
	    				else 
	    					assetsValue=0;
	    				
	    				map.put("AssetsValue",assetsValue);
	    				map.put("BeneficiaryInterest",ctx.get("BeneficiaryInterest_"+i));
	    				map.put("PolicyKey",ctx.get("PolicyKey"));
	    				map.put("VersionSequence",ctx.get("VersionSequence"));
	    				map.put("LargestTrustsKey",ctx.get("LargestTrustsKey_"+i));
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
	            newCtx.put("inputList", inputList);
				LawyersUtils.convertListDataToXML(newCtx,"inputList","outputList");
				ctx.put("outputList",newCtx.get("outputList"));
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
	}
	
	public static void saveTaxSupplementData(Context ctx)
	{
			long total=0;
			int percentage=0;
			HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
            HttpSession sess = req.getSession();
            List inputList=new ArrayList();
            try
            {
	            
	            List finalList = new ArrayList();
	            if(sess.getAttribute("TaxSupplmentData_list_01") != null &&   sess.getAttribute("TaxSupplmentData_list_01") instanceof List)
	            {
	                finalList = (List)sess.getAttribute("TaxSupplmentData_list_01");
	            }
	            int j=0;
	            Map reqMap = new HashMap();
	            Context newCtx=new Context();
	            newCtx.setProject(ctx.getProject());
	            newCtx.putAll(ctx);
	            
	    		for (int i = 0; i < finalList.size(); i++) 
	    		{
	    				reqMap = (HashMap) finalList.get(i);
	    				newCtx.put("TaxAOPKey",reqMap.get("TaxAOPKey"));
	    				newCtx.put("revenuePercentage_"+reqMap.get("TaxAOPKey"),ctx.get("revenuePercentage_"+i));
	    				newCtx.put("TaxCommentDesc_"+reqMap.get("TaxAOPKey"),ctx.get("TaxCommentDesc_"+i));
	    				percentage=ctx.get("revenuePercentage_"+i)!=null && !ctx.get("revenuePercentage_"+i).equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("revenuePercentage_"+i).toString()):0;
	    				total=total+percentage;
	    		}
	    		
	    		if(total>100 || total<100)
	    			populateError(ctx, "Total_Percentage","The total of percentage is required to equal 100%, current total is "+ total + "%");
			
	    		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select("TaxSuppLKULW.findAll", ctx);
			if (limtTypes != null) 
			{	HashMap map=null,map1=null;
				for (int i = 0; i < limtTypes.size(); i++) 
				{
					map = (HashMap) limtTypes.get(i);
					map1=new HashMap();
					
						map1.put("TaxAOPKey", map.get("TaxAOPKey"));
						map1.put("revenuePercentage", newCtx.get("revenuePercentage_"+ map.get("TaxAOPKey")));
						map1.put("PolicyKey",ctx.get("PolicyKey"));
						map1.put("VersionSequence",ctx.get("VersionSequence"));
						if(Integer.parseInt(map.get("TaxAOPKey").toString())==9 )
						{
							if(newCtx.get("revenuePercentage_"+ map.get("TaxAOPKey"))!=null && !newCtx.get("revenuePercentage_"+ map.get("TaxAOPKey")).equals(HtmlConstants.EMPTY))
							{
								if(ctx.get("otherTaxAopDesc")!=null && !ctx.get("otherTaxAopDesc").equals(HtmlConstants.EMPTY))
									map1.put("TaxCommentDesc", ctx.get("otherTaxAopDesc"));
								else
									populateError(ctx, "otherDesc","Please provide dscription.");
							}
						}
						else
							map1.put("TaxCommentDesc",null);
						map1.put("VersionKey",ctx.get("VersionKey"));
						if(ctx.get("CreatedBy_"+i)==null || ctx.get("CreatedBy_"+i).equals(HtmlConstants.EMPTY))
							map1.put("CreatedBy", ctx.get("LastUpdateUserID"));
						else
							map1.put("CreatedBy", ctx.get("CreatedBy_"+i));
						
						if(ctx.get("CreatedDate_"+i)==null || ctx.get("CreatedDate_"+i).equals(HtmlConstants.EMPTY))
							map1.put("CreatedDate",ctx.get("LastUpdateTimeStamp"));
						else
							map1.put("CreatedDate",ctx.get("CreatedDate_"+i));
						inputList.add(map1);
				}
				for (int i = 1; i <=finalList.size(); i++) 
	    		{
	    			newCtx.remove("revenuePercentage_"+i);
	    			newCtx.remove("TaxCommentDesc_"+i);
	    				
	    		}
				newCtx.put("inputList",inputList);
				
				LawyersUtils.convertListDataToXML(newCtx,"inputList","outputList");
				ctx.put("outputList", newCtx.get("outputList"));
			}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
	}
	public static void viewTaxSupplementData(Context ctx)
	{
		try
		{
			List wesList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetTaxSuppDetailsLWDetails", ctx);
			if(wesList!=null)
			{
				Map map=null;
				for(int i=0;i<wesList.size();i++)
				{
					map = (HashMap) wesList.get(i);
					if(map.get("TaxAOPKey") != null && Integer.parseInt(map.get("TaxAOPKey").toString())==9)
					{
						ctx.put("otherTaxAopDesc", map.get("TaxCommentDesc"));
					}
				}
				
			}
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
	}
	
	public static void viewFeeSuitData(Context ctx)
    {
		HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
        HttpSession sess = req.getSession();
        List finalList =null;
        if(ctx.get("FeeSuitData_list_01")==null)
            ctx.put("FeeSuitData_list_01",sess.getAttribute("FeeSuitData_list_01"));
        
        if(ctx.get("FeeSuitData_list_01") != null &&   ctx.get("FeeSuitData_list_01") instanceof List)
        {
        	finalList = (List)ctx.get("FeeSuitData_list_01");
        	ctx.put("addFeeSuits",finalList.size());
        	 int feesuitsCount=ctx.get("addFeeSuits")!=null?Integer.parseInt(ctx.get("addFeeSuits").toString()):finalList.size();
             if(feesuitsCount>finalList.size())
             {

             	Map map = new HashMap();
          for (int i = finalList.size(); i < finalList.size()+(feesuitsCount-finalList.size()); i++)
             	{
        	  		map.put("FirminitLawsuitsKey", null);
        	  		map.put("AmountOfFeesSued", null);
      				map.put("SuitFilesDateFees", null);
      				map.put("IsAllegOfLegalMalPrac",null);
      				map.put("FeeSuitDispositionKey", null);
      				finalList.add(map);
             	
             	}
          	ctx.put("FeeSuitData_list_01",finalList);
             }
        	sess.setAttribute("FeeSuitData_list_01", ctx.get("FeeSuitData_list_01"));
        	
        }
        
    }
	
	public static void saveFeeSuitData(Context ctx)
    {
    	try
    	{
	    	HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
	        HttpSession sess = req.getSession();
	        
	        List finalList =null;
	        List inputList=new ArrayList();
	        Context newCtx=new Context();
			newCtx.setProject(ctx.getProject());
			newCtx.putAll(ctx);
	       if(sess.getAttribute("FeeSuitData_list_01") != null &&   sess.getAttribute("FeeSuitData_list_01") instanceof List)
	        {
		            finalList = (List)sess.getAttribute("FeeSuitData_list_01");
		       
		            for (int i = 0; i < finalList.size(); i++) 
					{
					newCtx.remove("AmountOfFeesSued_"+i);
					}
					
		            
		            
		        Map map =null;
				for (int i = 0; i < finalList.size(); i++) 
				{
					map= new HashMap();
					if( !ctx.get("AmountOfFeesSued_"+i).equals(HtmlConstants.EMPTY) && ctx.get("AmountOfFeesSued_"+i)!=null &&
							!ctx.get("SuitFilesDateFees_"+i).equals(HtmlConstants.EMPTY) && ctx.get("SuitFilesDateFees_"+i)!=null	&&
							!ctx.get("IsAllegOfLegalMalPrac_"+i).equals(HtmlConstants.EMPTY) && ctx.get("IsAllegOfLegalMalPrac_"+i)!=null	&&
							!ctx.get("FeeSuitDispositionKey_"+i).equals(HtmlConstants.EMPTY) && ctx.get("FeeSuitDispositionKey_"+i)!=null)
					{
						int amount=Integer.parseInt(removeAmountFormat(ctx.get("AmountOfFeesSued_"+i)).toString());
						map.put("AmountOfFeesSued", amount);
						map.put("SuitFilesDateFees", ctx.get("SuitFilesDateFees_"+i));
						map.put("IsAllegOfLegalMalPrac", ctx.get("IsAllegOfLegalMalPrac_"+i));
						map.put("FeeSuitDispositionKey", ctx.get("FeeSuitDispositionKey_"+i));
						map.put("FirminitLawsuitsKey", ctx.get("FirminitLawsuitsKey_"+i));
						map.put("PolicyKey", ctx.get("PolicyKey"));
						map.put("VersionSequence", ctx.get("VersionSequence"));
						map.put("LastUpdateUserID", ctx.get("LastUpdateUserID"));
						inputList.add(map);
						
						
					}
					
						
					
					
					
				}
				
				newCtx.put("inputList", inputList);
				LawyersUtils.convertListDataToXML(newCtx,"inputList","outputList");
				ctx.put("outputList",newCtx.get("outputList"));
				
			
	        }
    	}
        catch(Exception e)
        {logger.error("Unexpected error", e);
        }
    }
	
	public static Object removeAmountFormat(Object arg1){
	      if(arg1 == null)
	          return 0;
	      
	      String amount = arg1.toString();      
	      return amount.replace("$", "").replace(",", "");
	}
	public static void getAopsValues(Context ctx)
	{
			try 
			{
				RuleUtils.executeRule(ctx, "LawyersRule.checkCCBSupp");
				int percentage=0;
				if(ctx.get(Constants.INET_ERRORS_LIST) != null){
					return ;
				}
				
				List aoplist = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields", ctx);
				Map covMap = new HashMap();
				if (aoplist != null) 
				{
					Map map = new HashMap();
					for (int i = 0; i < aoplist.size(); i++) 
					{
						map = (HashMap) aoplist.get(i);
						percentage=map.get("PercentageValue")!=null && !map.get("PercentageValue").equals(HtmlConstants.EMPTY)?Integer.parseInt(map.get("PercentageValue").toString()):0;
						ctx.put("aopPercentage_" + map.get("AOPKey"),percentage );
						
					}
				}
				/*int policyStatusKey=ctx.get("PolicyStatusKey")!=null?Integer.parseInt(ctx.get("PolicyStatusKey").toString()):0;
				if(policyStatusKey==2)
				{
					Context priorPolicyContext=new Context();
					priorPolicyContext.setProject(ctx.getProject());
					priorPolicyContext.put("PolicyKey", ctx.get("PolicyKey"));
					logger.debug("going to populate prior year policy data");
					List previousPolicyKeyList = SqlResources.getSqlMapProcessor(priorPolicyContext).select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey", priorPolicyContext);
					if(previousPolicyKeyList!=null && previousPolicyKeyList.size()>0)
					{
						priorPolicyContext.putAll((Map) previousPolicyKeyList.get(0));
						priorPolicyContext.put("PolicyKey",priorPolicyContext.get("PreviousPolicykey"));
						priorPolicyContext.put("VersionSequence",priorPolicyContext.get("previousVersionSequence"));
						
					
						List priorAoplist = SqlResources.getSqlMapProcessor(priorPolicyContext).select("SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields", priorPolicyContext);
						// covMap = new HashMap();
						if (priorAoplist != null) 
						{
							Map map = new HashMap();
							for (int i = 0; i < priorAoplist.size(); i++) 
							{
								map = (HashMap) priorAoplist.get(i);
								percentage=map.get("PercentageValue")!=null && !map.get("PercentageValue").equals(HtmlConstants.EMPTY)?Integer.parseInt(map.get("PercentageValue").toString()):0;
								ctx.put("priorAopPercentage_" + map.get("AOPKey"),percentage );
								
							}
						}
					}
				}*/
			
			}
			catch (Exception e)
			{
				logger.error("Unexpected error", e);
			}
	}
	public static void getRealEstateAopsValues(Context ctx)
	{
			try 
			{
				int percentage=0;
				List aoplist = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetRealEstateCommListNew",ctx);
				Map covMap = new HashMap();
				if (aoplist != null) 
				{
					Map map = new HashMap();
					for (int i = 0; i < aoplist.size(); i++) 
					{
						map = (HashMap) aoplist.get(i);
						percentage=map.get("PercentageValue")!=null && !map.get("PercentageValue").equals(HtmlConstants.EMPTY)?Integer.parseInt(map.get("PercentageValue").toString()):0;
						ctx.put("aopResiPercentage_" + map.get("AOPREKey"),percentage );
						percentage=map.get("CommercialPercentageValue")!=null && !map.get("CommercialPercentageValue").equals(HtmlConstants.EMPTY)?Integer.parseInt(map.get("CommercialPercentageValue").toString()):0;
						ctx.put("aopCommPercentage_" + map.get("AOPREKey"),percentage );
					}
				}
			}
			catch (Exception e)
			{
				logger.error("Unexpected error", e);
			}
	}
	
	public static void validateFeeSuitData(Context ctx)
    {
    	try
    	{
	    	HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
	        HttpSession sess = req.getSession();
	       // boolean isDateValid=true;
	        String dateData=null;
	        List finalList =null;
	        List inputList=new ArrayList();
	       if(sess.getAttribute("FeeSuitData_list_01") != null &&   sess.getAttribute("FeeSuitData_list_01") instanceof List)
	        {
		            finalList = (List)sess.getAttribute("FeeSuitData_list_01");
		       
		        Map map = new HashMap();
				for (int i = 0; i < finalList.size(); i++) 
				{
					
						if( ctx.get("AmountOfFeesSued_"+i).equals(HtmlConstants.EMPTY) || ctx.get("AmountOfFeesSued_"+i)==null ||
								ctx.get("SuitFilesDateFees_"+i).equals(HtmlConstants.EMPTY) || ctx.get("SuitFilesDateFees_"+i)==null ||
								ctx.get("IsAllegOfLegalMalPrac_"+i).equals(HtmlConstants.EMPTY) || ctx.get("IsAllegOfLegalMalPrac_"+i)==null ||
								ctx.get("FeeSuitDispositionKey_"+i).equals(HtmlConstants.EMPTY) || ctx.get("FeeSuitDispositionKey_"+i)==null
								)
									{
							LawyersUtils.populateError(ctx, "needFirstRow","All added fee suits data need to be filled.");
							}
					
					if(!ctx.get("SuitFilesDateFees_"+i).equals(HtmlConstants.EMPTY) && ctx.get("SuitFilesDateFees_"+i)!=null)
					{ dateData=ctx.get("SuitFilesDateFees_"+i).toString();
						if(com.userbo.LawyersUtils.validateDateFieldData(dateData))
							LawyersUtils.populateError(ctx, "needFirstRow","Please enter valid date.");
					}
				}
				
	        }
    	}
    	catch(Exception e)
    	{logger.error("Unexpected error", e);}
    }
	
	
	 public static void validatePredecessorManually(Context ctx)
	    {
		 String dateData=null;
	    	try
	    	{
		    	HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
		        HttpSession sess = req.getSession();
		        
		        List finalList =null;
		        String IsApplIntToFinanAssests=ctx.get("IsApplIntToFinanAssests")!=null && !ctx.get("IsApplIntToFinanAssests").equals(HtmlConstants.EMPTY)?ctx.get("IsApplIntToFinanAssests").toString():"N"; 
	        String IsFirmCoverageForPreceedorFirms=ctx.get("IsFirmCoverageForPreceedorFirms")!=null && !ctx.get("IsFirmCoverageForPreceedorFirms").equals(HtmlConstants.EMPTY)?ctx.get("IsFirmCoverageForPreceedorFirms").toString():"N";
		       if(sess.getAttribute("PredecessorSupplement_list_01") != null &&   sess.getAttribute("PredecessorSupplement_list_01") instanceof List)
		        {
			            finalList = (List)sess.getAttribute("PredecessorSupplement_list_01");
			       
			        Map map = new HashMap();
					for (int i = 0; i < finalList.size(); i++) 
					{
						if(i==0)
						{
							if(ctx.get("DateOfAcquisation_"+i)==null || ctx.get("FirmName_"+i).equals(HtmlConstants.EMPTY) || 
									ctx.get("DateOfAcquisation_"+i)==null || ctx.get("DateOfAcquisation_"+i).equals(HtmlConstants.EMPTY) || 
									ctx.get("TypeOfEntity_"+i)==null	|| ctx.get("TypeOfEntity_"+i).equals(HtmlConstants.EMPTY) ||
									ctx.get("DateOfAcquisation_"+i)==null	|| ctx.get("DateOfAcquisation_"+i).equals(HtmlConstants.EMPTY) || 
									ctx.get("NumberOfAttorneyAtApplFirm_"+i)==null	|| ctx.get("NumberOfAttorneyAtApplFirm_"+i).equals(HtmlConstants.EMPTY) ||
									ctx.get("InsurerAtDissolution_"+i)==null	|| ctx.get("InsurerAtDissolution_"+i).equals(HtmlConstants.EMPTY) ||
									ctx.get("IsERPPurchased_"+i)==null || ctx.get("IsERPPurchased_"+i).equals(HtmlConstants.EMPTY))
										{
								
									if("Y".equals(IsApplIntToFinanAssests) || "Y".equals(IsFirmCoverageForPreceedorFirms) )
										new LawyersUtils().populateError(ctx, "needFirstRowPredecessor","At least one record need to be filled.");
									return;
								}
						}
						
						if(ctx.get("DateOfAcquisation_"+i)!=null && !ctx.get("DateOfAcquisation_"+i).equals(HtmlConstants.EMPTY))
						{ dateData=ctx.get("DateOfAcquisation_"+i).toString();
							if(com.userbo.LawyersUtils.validateDateFieldData(dateData))
								LawyersUtils.populateError(ctx, "needFirstRowPredecessor","Please enter valid date.");
						}
						if(ctx.get("IsERPPurchased_"+i)!=null && !ctx.get("IsERPPurchased_"+i).equals(HtmlConstants.EMPTY))
						{
							if("Y".equals(ctx.get("IsERPPurchased_"+i).toString()))
							{
								dateData=!ctx.get("ERPExpDate_"+i).equals(HtmlConstants.EMPTY) && ctx.get("ERPExpDate_"+i)!=null?ctx.get("ERPExpDate_"+i).toString():"99999999999";
								if(com.userbo.LawyersUtils.validateDateFieldData(dateData))
									LawyersUtils.populateError(ctx, "needFirstRowPredecessor","Please enter valid date.");
							
							}
						}
					}
					
					
				
		        }
	    	}
	        catch(Exception e)
	        {
	        	logger.error("Unexpected error", e);
	        	
	        }
	    }
	  
	public static void populateLargestCaseError(Context ctx,long largestCasesSize,String desc)
	{
		try
		{
		if(largestCasesSize==0)
		
			populateError(ctx, "largestCasesSizeError","Largest plaintiff case size required for "+desc);
		
		}
		catch(Exception e)
		{ logger.error("Unexpected error", e);}
		
	}
	
	public static void fillIndicationAOP(IContext ctx) throws Exception {
		int deletedRows = SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteAreaOfPracticeLW", ctx);
		deletedRows = SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteSmartSearchValue", ctx);
		deletedRows = SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteAreaOfPracticeQuickQuoteLW", ctx);
		
		List aopList = SqlResources.getSqlMapProcessor(ctx).select("AOPLW.findAll", ctx);
		Context ctx1;
		if (aopList != null) {
			
			try {
				Map map = new HashMap();
				for (int i = 0; i < aopList.size(); i++) {
					map = (HashMap) aopList.get(i);
					String percentageValue = ctx.get("AOP_Percentage_" + map.get("AOPKey").toString()) == null ? null
							: ctx.get("AOP_Percentage_" + map.get("AOPKey").toString()).toString().trim();
					
					if (percentageValue == null || "".equals(percentageValue) || "0".equals(percentageValue))
						percentageValue = "0";
					
					ctx1 = new Context();
					ctx1.put("AOPKey", map.get("AOPKey"));
					ctx1.put("PolicyKey", ctx.get("PolicyKey"));
					ctx1.put("VersionSequence", ctx.get("VersionSequence"));
					ctx1.put("PercentageValue", percentageValue);
					ctx1.put("Percentage", percentageValue);
		            ctx1.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
		            ctx1.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
		            ctx1.put("VersionKey",ctx.get("VersionKey"));
		            ctx1.put("CreatedBy",ctx.get("CreatedBy"));
		            ctx1.put("CreatedDate",ctx.get("CreatedDate"));
		            ctx1.put("IsEdit","Y");
					
					ctx1.setProject(ctx.getProject());
					DBUtils.executeDBOperation(ctx1, "AreaOfPracticeQuickQuoteLW", "1");
					DBUtils.executeDBOperation(ctx1, "AreaOfPracticeLW", "1");
					if(!"0".equals(percentageValue))
						DBUtils.executeDBOperation(ctx1, "SmartSearchValue", "1");
				}
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}			
		}
	}
	
	public static void populateIndicationAOP(IContext ctx) throws Exception {
		List aoplist = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementremoveAOPselectAreaOfPracticeLW", ctx);
		
		if (aoplist != null) {
			Map map = new HashMap();
			for (int i = 0; i < aoplist.size(); i++) {
				map = (HashMap) aoplist.get(i);
				if(map.get("PercentageValue") == null || (map.get("PercentageValue") != null && ("".equals(map.get("PercentageValue").toString().trim()) || "0".equals(map.get("PercentageValue").toString().trim()))))
					ctx.put("AOP_Percentage_" + map.get("AOPKey"), "");	
				else
					ctx.put("AOP_Percentage_" + map.get("AOPKey"), map.get("PercentageValue"));	
			
				/*if( "25".equals(map.get("AOPKey").toString()))
					ctx.put("otherAopDesc",map.get("AOPCommentDesc"));*/
						
			}
		}
	}	

	public static void getTaxAopsValues(Context ctx,String prefix)
	{
			try 
			{
				int percentage=0;
				List aoplist = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetTaxSuppDetails",ctx);
				Map covMap = new HashMap();
				if (aoplist != null) 
				{
					Map map = new HashMap();
					for (int i = 0; i < aoplist.size(); i++) 
					{
						map = (HashMap) aoplist.get(i);
						percentage=map.get("revenuePercentage")!=null && !map.get("revenuePercentage").equals(HtmlConstants.EMPTY)?Integer.parseInt(map.get("revenuePercentage").toString()):0;
						ctx.put("tax"+prefix+"AopPercentage_" + map.get("TaxAOPKey"),percentage );
						
					}
				}
				
				
			}
			catch (Exception e)
			{
				logger.error("Unexpected error", e);
			}
	}
	public static void fillProtexureFormAOP(IContext ctx) throws Exception {
		int deletedRows = SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteAreaOfPracticeLW", ctx);
		deletedRows = SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteSmartSearchValue", ctx);
		deletedRows = SqlResources.getSqlMapProcessor(ctx).delete("SqlStmts.UserStatementManualBoQueriesdeleteAreaOfPracticeQuickQuoteLW", ctx);
		
		List aopList = SqlResources.getSqlMapProcessor(ctx).select("AOPLW.findAll", ctx);
		Context ctx1;
		if (aopList != null) {
			
			try {
				Map map = new HashMap();
				for (int i = 0; i < aopList.size(); i++) {
					map = (HashMap) aopList.get(i);
					String percentageValue = ctx.get("AOP_Percentage_" + map.get("AOPKey").toString()) == null ? null
							: ctx.get("AOP_Percentage_" + map.get("AOPKey").toString()).toString().trim();
					
					if (percentageValue == null || "".equals(percentageValue) || "0".equals(percentageValue))
						percentageValue = "0";
					
					ctx1 = new Context();
					ctx1.put("AOPKey", map.get("AOPKey"));
					ctx1.put("PolicyKey", ctx.get("PolicyKey"));
					ctx1.put("VersionSequence", ctx.get("VersionSequence"));
					ctx1.put("PercentageValue", percentageValue);
					ctx1.put("Percentage", percentageValue);
		            ctx1.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
		            ctx1.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
		            ctx1.put("VersionKey",ctx.get("VersionKey"));
		            ctx1.put("CreatedBy",ctx.get("CreatedBy"));
		            ctx1.put("CreatedDate",ctx.get("CreatedDate"));
		            ctx1.put("IsEdit","Y");
					
					ctx1.setProject(ctx.getProject());
					DBUtils.executeDBOperation(ctx1, "AreaOfPracticeQuickQuoteLW", "1");
					DBUtils.executeDBOperation(ctx1, "AreaOfPracticeLW", "1");
					if(!"0".equals(percentageValue))
						DBUtils.executeDBOperation(ctx1, "SmartSearchValue", "1");
				}
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}			
		}
	}
	
}
