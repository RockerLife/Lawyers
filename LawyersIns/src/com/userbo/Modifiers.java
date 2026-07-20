package com.userbo;

import com.util.InetLogger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.manage.managemetadata.functions.commonfunctions.DBUtils;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;
import com.util.IContext;
import com.util.StringUtils;
import com.util.SystemProperties;

public class Modifiers {
	private static final InetLogger logger = InetLogger.getInetLogger(Modifiers.class);

	public static boolean validateModifiers(Context ctx) throws Exception {
		
		boolean isMissouriAndNewFiling = false;        
		Object objRatingRule = RuleUtils.executeRule(ctx,"LawyersRule.isMissouriAndNewFiling");
        if (objRatingRule != null && objRatingRule instanceof Boolean) {
        	isMissouriAndNewFiling = (Boolean) objRatingRule;
        }	
		if (ctx.get("saveModifier") == null)
			return true;

		if ((ctx.get("ModifierComment") == null || "".equals(ctx .get("ModifierComment"))) 
				&& ((ctx.get("SchduleRatingModifier1") != null && !"".equals(ctx.get("SchduleRatingModifier1").toString().trim()) && !"0".equals(ctx.get("SchduleRatingModifier1").toString().trim()))
				 || (ctx.get("SchduleRatingModifier2") != null && !"".equals(ctx.get("SchduleRatingModifier2").toString().trim()) && !"0".equals(ctx.get("SchduleRatingModifier2").toString().trim())) 
				 || (ctx.get("SchduleRatingModifier3") != null && !"".equals(ctx.get("SchduleRatingModifier3").toString().trim()) && !"0".equals(ctx.get("SchduleRatingModifier3").toString().trim())))) {
			LawyersUtils.populateError(ctx, "ModifierComment", "Underwriting Justification is required");
			return true;
		}

		if ((ctx.get("SchduleRatingModifier1") == null && ctx.get("SchduleRatingModifier2") == null && ctx.get("SchduleRatingModifier3") == null)
				|| ((ctx.get("SchduleRatingModifier1") != null && ("".equals(ctx.get("SchduleRatingModifier1").toString().trim()) || "0".equals(ctx.get("SchduleRatingModifier1").toString().trim())))
				 && (ctx.get("SchduleRatingModifier2") != null && ("".equals(ctx.get("SchduleRatingModifier2").toString().trim()) || "0".equals(ctx.get("SchduleRatingModifier2").toString().trim()))) 
				 && (ctx.get("SchduleRatingModifier3") != null && ("".equals(ctx.get("SchduleRatingModifier3").toString().trim()) || "0".equals(ctx.get("SchduleRatingModifier3").toString().trim())))))
			ctx.put("ModifierComment", null);
		
		int percent = 0;
		int totalModifierPercentage = 0;
		
		if (ctx.get("SchduleRatingModifier1") != null && !"".equals(ctx.get("SchduleRatingModifier1"))) {
			if (!StringUtils.isNumeric(ctx.get("SchduleRatingModifier1").toString())) {
				LawyersUtils.populateError(ctx, "SchduleRatingModifier1", "Claim modifier is not valid");
				return true;
			}
			percent = Integer.parseInt(ctx.get("SchduleRatingModifier1").toString());
			
			totalModifierPercentage = percent;
		}

		if (ctx.get("SchduleRatingModifier2") != null && !"".equals(ctx.get("SchduleRatingModifier2"))) {
			if (!StringUtils.isNumeric(ctx.get("SchduleRatingModifier2").toString()))
				if(isMissouriAndNewFiling) {
					LawyersUtils.populateError(ctx, "SchduleRatingModifier2","Types of Clients are not valid");
					return true;
				}
				else {
					LawyersUtils.populateError(ctx, "SchduleRatingModifier2","Firm Practice modifier is not valid");
					return true;
				}

			percent = Integer.parseInt(ctx.get("SchduleRatingModifier2").toString());

			totalModifierPercentage = totalModifierPercentage + percent;
		}

		if (ctx.get("SchduleRatingModifier3") != null && !"".equals(ctx.get("SchduleRatingModifier3"))) {
			if (!StringUtils.isNumeric(ctx.get("SchduleRatingModifier3").toString()))
				
				if(isMissouriAndNewFiling) {
					LawyersUtils.populateError(ctx, "SchduleRatingModifier3","Internal Management is not valid");
					return true;
				}
				else {
					LawyersUtils.populateError(ctx, "SchduleRatingModifier3","Firm Ed. and Training modifier is not valid");
					return true;
				}

			percent = Integer.parseInt(ctx.get("SchduleRatingModifier3").toString());

			totalModifierPercentage = totalModifierPercentage + percent;
		}
		
		if(isMissouriAndNewFiling)
		{
			if (ctx.get("SchduleRatingModifier4") != null && !"".equals(ctx.get("SchduleRatingModifier4"))) 
			{
				if (!StringUtils.isNumeric(ctx.get("SchduleRatingModifier4").toString())) {
					LawyersUtils.populateError(ctx, "SchduleRatingModifier4","Classification Peculiarities modifier is not valid");
					return true;
				}
				percent = Integer.parseInt(ctx.get("SchduleRatingModifier4").toString());
				
				totalModifierPercentage = totalModifierPercentage + percent;
			}
			if (ctx.get("SchduleRatingModifier5") != null && !"".equals(ctx.get("SchduleRatingModifier5"))) {
				if (!StringUtils.isNumeric(ctx.get("SchduleRatingModifier5").toString())) {
					LawyersUtils.populateError(ctx, "SchduleRatingModifier5","Years in Existence modifier is not valid");
					return true;
				}
				percent = Integer.parseInt(ctx.get("SchduleRatingModifier5").toString());
				
				totalModifierPercentage = totalModifierPercentage + percent;
			}
			if (ctx.get("SchduleRatingModifier6") != null && !"".equals(ctx.get("SchduleRatingModifier6"))) {
				if (!StringUtils.isNumeric(ctx.get("SchduleRatingModifier6").toString())) {
					LawyersUtils.populateError(ctx, "SchduleRatingModifier6","Ethics and Moral Standing modifier is not valid");
					return true;
				}

				percent = Integer.parseInt(ctx.get("SchduleRatingModifier6").toString());
				
				totalModifierPercentage = totalModifierPercentage + percent;
			}
		}
		
		return LawyersValidationUtils.validateModifiersStateWise(ctx, totalModifierPercentage, "Total Modifier Percentage should be between");
	}

	public void saveCoverageModifiers(IContext ctx) throws Exception {

		List quoteList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetAllQuotes", ctx);
		if (quoteList != null) {
			try {
				Map map = new HashMap();
				for (int i = 0; i < quoteList.size(); i++) {
					map = (HashMap) quoteList.get(i);
					Context newCtx = new Context();
					newCtx.putAll(ctx);

					newCtx.put("TransactionSequence", map.get("TransactionSequence"));
					newCtx.put("CoverageSequence", map.get("CoverageSequence"));
					newCtx.put("PolicyKey", map.get("PolicyKey"));
					addCoverageModifiers(newCtx);
					newCtx.clear();
				}
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}

		}

	}

	public void addCoverageModifiers(IContext ctx) throws Exception {

		if (ctx.get("updateModifier") == null)
			populateCoverageModifiers(ctx);

		List modifiersList = SqlResources.getSqlMapProcessor(ctx).select(
				"Modifiers.findAll", ctx);

		Object objDefaultModifier = RuleUtils.executeRule(ctx,
				"LawyersRule.checkDefaultModifierAndCommentQuickQuote");

		boolean flag = false;
		String defaultModifier = null;

		if (objDefaultModifier != null && objDefaultModifier instanceof Boolean)
			flag = (Boolean) objDefaultModifier;

		if (flag) {
			defaultModifier = SystemProperties.getInstance().getString(
					"appl." + ctx.getProject() + ".ModifierQQ");

		}

		if (modifiersList != null) {
			try {
				Map map = new HashMap();
				for (int i = 0; i < modifiersList.size(); i++) {
					map = (HashMap) modifiersList.get(i);
					String percentageValue = null;

					if (flag && i == 0) {
						percentageValue = defaultModifier;
					} else {

						percentageValue = ctx.get("SchduleRatingModifier"
								+ map.get("ModifierKey").toString()) == null ? null
								: ctx.get(
										"SchduleRatingModifier"
												+ map.get("ModifierKey")
														.toString()).toString()
										.trim();
					}

					if (percentageValue == null || "".equals(percentageValue)
							|| "0".equals(percentageValue))
						percentageValue = "0";

					Context newCtx = new Context();
					newCtx.put("TransactionSequence", ctx
							.get("TransactionSequence"));
					newCtx.put("CoverageSequence", ctx.get("CoverageSequence"));
					newCtx.put("PolicyKey", ctx.get("PolicyKey"));
					newCtx.put("ModifierKey", map.get("ModifierKey"));
					newCtx.put("Percentage", percentageValue);
			    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
			    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));

					newCtx.setProject(ctx.getProject());
					Object obj2 = DBUtils.executeDBOperation(newCtx,
							"CoverageModifiers", "3");

					if (obj2 == null) {
						DBUtils.executeDBOperation(newCtx, "CoverageModifiers",
								"1");

					} else {
						DBUtils.executeDBOperation(newCtx, "CoverageModifiers",
								"2");
						DBUtils.executeDBOperation(newCtx, "CoverageModifiers",
								"1");
					}
				}
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}

		}

	}

	/**
	 * This function is used to populate coverage modifiers on quote option and
	 * quick quote option page
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void populateCoverageModifiers(IContext ctx) throws Exception {

		@SuppressWarnings("rawtypes")
		List modifiersList = SqlResources
				.getSqlMapProcessor(ctx)
				.select(
						"SqlStmts.UserStatementsaveAccountstmtspopulateCoverageModifiers",
						ctx);
		ctx.remove("Modifiers_freeform_01");

		Map covMap = new HashMap();
		if (modifiersList != null && modifiersList.size() > 0) 
		{
			Map map = new HashMap();
			for (int i = 0; i < modifiersList.size(); i++) 
			{
				map = (HashMap) modifiersList.get(i);
				covMap.put("SchduleRatingModifier" + map.get("ModifierKey"),
						map.get("Percentage"));
			}
		}
		
		if(covMap.get("SchduleRatingModifier1") != null && covMap.get("SchduleRatingModifier1").toString().equals("0")){
			covMap.put("SchduleRatingModifier1", "");	
			ctx.put("SchduleRatingModifier1", covMap.get("SchduleRatingModifier1"));
		}
		else{
			ctx.put("SchduleRatingModifier1", covMap.get("SchduleRatingModifier1"));
		}
		
		if(covMap.get("SchduleRatingModifier2") != null && covMap.get("SchduleRatingModifier2").toString().equals("0")){
			covMap.put("SchduleRatingModifier2", "");	
			ctx.put("SchduleRatingModifier2", covMap.get("SchduleRatingModifier2"));
		}
		else{
			ctx.put("SchduleRatingModifier2", covMap.get("SchduleRatingModifier2"));
		}
		
		if(covMap.get("SchduleRatingModifier3") != null && covMap.get("SchduleRatingModifier3").toString().equals("0")){
			covMap.put("SchduleRatingModifier3", "");	
			ctx.put("SchduleRatingModifier3", covMap.get("SchduleRatingModifier3"));
		}
		else{
			ctx.put("SchduleRatingModifier3", covMap.get("SchduleRatingModifier3"));
		}
		boolean isMissouriAndNewFiling = false;        
		Object objRatingRule = RuleUtils.executeRule(ctx,"LawyersRule.isMissouriAndNewFiling");
        if (objRatingRule != null && objRatingRule instanceof Boolean) {
        	isMissouriAndNewFiling = (Boolean) objRatingRule;
        }	
        if(isMissouriAndNewFiling)
        {
			if(covMap.get("SchduleRatingModifier4") != null && covMap.get("SchduleRatingModifier4").toString().equals("0")){
				covMap.put("SchduleRatingModifier4", "");	
				ctx.put("SchduleRatingModifier4", covMap.get("SchduleRatingModifier4"));
			}
			else{
				ctx.put("SchduleRatingModifier4", covMap.get("SchduleRatingModifier4"));
			}
			if(covMap.get("SchduleRatingModifier5") != null && covMap.get("SchduleRatingModifier5").toString().equals("0")){
				covMap.put("SchduleRatingModifier5", "");	
				ctx.put("SchduleRatingModifier5", covMap.get("SchduleRatingModifier5"));
			}
			else{
				ctx.put("SchduleRatingModifier5", covMap.get("SchduleRatingModifier5"));
			}
			if(covMap.get("SchduleRatingModifier6") != null && covMap.get("SchduleRatingModifier6").toString().equals("0")){
				covMap.put("SchduleRatingModifier6", "");	
				ctx.put("SchduleRatingModifier6", covMap.get("SchduleRatingModifier6"));
			}
			else{
				ctx.put("SchduleRatingModifier6", covMap.get("SchduleRatingModifier6"));
			}
        }
		//ctx.put("SchduleRatingModifier1", covMap.get("SchduleRatingModifier1"));
		//ctx.put("SchduleRatingModifier2", covMap.get("SchduleRatingModifier2"));
		//ctx.put("SchduleRatingModifier3", covMap.get("SchduleRatingModifier3"));

		Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"FirmLW.findByKey", ctx);

		Map firmMap = null;
		if (objFirm != null) {
			firmMap = (Map) objFirm;

			covMap.put("ModifierComment",
					firmMap.get("ModifierComment") != null ? firmMap.get(
							"ModifierComment").toString() : "");
		}
		ctx.put("Modifiers_freeform_01", covMap);
	}

	public void saveQuoteSentToPDF(IContext ctx) throws Exception {
		List quoteList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetAllQuotes", ctx);
		if (quoteList != null) {
			for (int i = 0; i < quoteList.size(); i++) {
				Map map = (Map) quoteList.get(i);
				Context newctx = new Context();
				newctx.putAll(map);
		    	newctx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
		    	newctx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
				newctx.setProject(ctx.getProject());
				newctx.put("PolicyKey", map.get("PolicyKey"));
				newctx.put("TransactionSequence", map
						.get("TransactionSequence"));

				if (ctx.get("IsQuoteSelected" + "_" + i) != null
						&& ("on".equals(ctx.get("IsQuoteSelected" + "_" + i)
								.toString()) || "Y".equals(ctx.get(
								"IsQuoteSelected" + "_" + i).toString())))
					newctx.put("IsQuoteSelected", "Y");
				else
					newctx.put("IsQuoteSelected", ctx.get("IsQuoteSelected"
							+ "_" + i));

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

	/**
	 * This method saves the coverage modifiers for Quote option page in full
	 * quote
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void saveCoverageModifiersQuoteOption(IContext ctx) throws Exception {

		List quoteList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetAllQuotes", ctx);
		if (quoteList != null) {
			try {
				Map map = new HashMap();
				for (int i = 0; i < quoteList.size(); i++) {
					map = (HashMap) quoteList.get(i);
					Context newCtx = new Context();
					newCtx.putAll(ctx);

					newCtx.put("TransactionSequence", map
							.get("TransactionSequence"));
					newCtx.put("CoverageSequence", map.get("CoverageSequence"));
					newCtx.put("PolicyKey", map.get("PolicyKey"));
					addCoverageModifiersQuoteOption(newCtx);
					newCtx.clear();
				}
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}

		}
	}

	public void addCoverageModifiersQuoteOption(IContext ctx) throws Exception {

		List modifiersList = SqlResources.getSqlMapProcessor(ctx).select(
				"Modifiers.findAll", ctx);

		Object objDefaultModifier = RuleUtils.executeRule(ctx,
				"LawyersRule.isNotAgentAndQuotePageSaved");

		boolean flag = false;
		String defaultModifier = null;

		if (objDefaultModifier != null && objDefaultModifier instanceof Boolean)
			flag = (Boolean) objDefaultModifier;

		if (flag) {
			defaultModifier = SystemProperties.getInstance().getString(
					"appl." + ctx.getProject() + ".ModifierFQ");
		}

		if (modifiersList != null) {
			try {
				Map map = new HashMap();
				for (int i = 0; i < modifiersList.size(); i++) {
					map = (HashMap) modifiersList.get(i);
					String percentageValue = null;

					if (defaultModifier != null && i == 0) {
						percentageValue = defaultModifier;
					} else if (ctx.get("SaveModifierFullQuote") != null
							&& "Y".equals(ctx.get("SaveModifierFullQuote")
									.toString())) {
						percentageValue = ctx.get("SchduleRatingModifier"
								+ map.get("ModifierKey").toString()) == null ? null
								: ctx.get(
										"SchduleRatingModifier"
												+ map.get("ModifierKey")
														.toString()).toString()
										.trim();
					}
					// assign Defaulf Modiifer Percentage
					if (flag)
						percentageValue = assignDefaulsModifierPercentage(ctx, map,
								percentageValue);

					if (percentageValue == null || "".equals(percentageValue)
							|| "0".equals(percentageValue))
						percentageValue = "0";

					Context newCtx = new Context();
					newCtx.put("TransactionSequence", ctx
							.get("TransactionSequence"));
					newCtx.put("CoverageSequence", ctx.get("CoverageSequence"));
					newCtx.put("PolicyKey", ctx.get("PolicyKey"));
					newCtx.put("ModifierKey", map.get("ModifierKey"));
					newCtx.put("Percentage", percentageValue);

					newCtx.setProject(ctx.getProject());
			    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
			    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
					Object obj2 = DBUtils.executeDBOperation(newCtx, "CoverageModifiers", "3");

					if (obj2 == null) {
						DBUtils.executeDBOperation(newCtx, "CoverageModifiers",
								"1");

					} else {
						/*DBUtils.executeDBOperation(newCtx, "CoverageModifiers",
								"2");
						DBUtils.executeDBOperation(newCtx, "CoverageModifiers",
								"1");*/
						DBUtils.executeDBOperation(newCtx, "CoverageModifiers", "4");						
					}
				}
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}
		}
		if (flag)
			saveDefaulfModifierComment(ctx);
	}

	public void saveDefaulfModifierComment(IContext ctx) throws Exception {
		Context newCtx = new Context();
		newCtx.put("PolicyKey", ctx.get("PolicyKey"));
		newCtx.put("VersionSequence", ctx.get("VersionSequence"));
		newCtx.setProject(ctx.getProject());

		String ModifierComment = "";
		

		Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"FirmLW.findByKey", ctx);
		Map objFirmMap = null;
		if (objFirm != null && objFirm instanceof Map)
			objFirmMap = (Map) objFirm;

		if (objFirmMap != null) {
		
		if((objFirmMap.get("IsLawyerProfLiabClaimAgntAppl") != null	&& !"Y".equals(objFirmMap.get("IsLawyerProfLiabClaimAgntAppl").toString()))
				&& (objFirmMap.get("IsAnyActOmmBecomeClaimAgntFirm") != null && !"Y".equals(objFirmMap.get("IsAnyActOmmBecomeClaimAgntFirm").toString()))){
			if(ModifierComment!=null)
				ModifierComment = ModifierComment + "Claims free credit";
			else
				ModifierComment = ModifierComment;
		}

		if (objFirmMap.get("IsFirmRequireEngagementLetter") != null
				&& "Y".equals(objFirmMap.get("IsFirmRequireEngagementLetter").toString())) {
			
				ModifierComment = ModifierComment + ",Firm Practice credit";
		}

		newCtx.put("ModifierComment", ModifierComment);
    	newCtx.put("LastUpdateTimeStamp",ctx.get("LastUpdateTimeStamp"));
    	newCtx.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
		SqlResources.getSqlMapProcessor(newCtx).update(
				"SqlStmts.UserStatementManualBoQueriesupdateModifierComment",
				newCtx);
		}
	}

	public String assignDefaulsModifierPercentage(IContext ctx, Map map,
			String percentageValue) throws Exception {

		Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"FirmLW.findByKey", ctx);
		Map objFirmMap = null;
		if (objFirm != null && objFirm instanceof Map)
			objFirmMap = (Map) objFirm;

		if (objFirmMap != null) {

			if (objFirmMap.get("IsFirmRequireEngagementLetter") != null
					&& "Y".equals(objFirmMap.get(
							"IsFirmRequireEngagementLetter").toString())) {

				if ("2".equals(map.get("ModifierKey").toString()))
					/*percentageValue = "-5";*/
					percentageValue = "";
			}
			if((objFirmMap.get("IsLawyerProfLiabClaimAgntAppl") != null	&& !"Y".equals(objFirmMap.get("IsLawyerProfLiabClaimAgntAppl").toString()))
					&& (objFirmMap.get("IsAnyActOmmBecomeClaimAgntFirm") != null && !"Y".equals(objFirmMap.get("IsAnyActOmmBecomeClaimAgntFirm").toString()))){
				if("1".equals(map.get("ModifierKey").toString())){
					/*percentageValue = "-10" ;*/
					percentageValue = "" ;
				}
			}

		}
		return percentageValue;
	}

	public void addDefaultModifier(IContext ctx) throws Exception {

		List modifierList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtsgetCoverageModifier",
				ctx);

		if (modifierList != null && modifierList.size() > 0) {
			populateCoverageModifiers(ctx);

		} else {
			String defaultModifier = null;
			defaultModifier = SystemProperties.getInstance().getString(
					"appl." + ctx.getProject() + ".Modifier1");
			ctx.put("ModifierKey", "1");
			ctx.put("Percentage", defaultModifier);

			ctx.put("SchduleRatingModifier1", defaultModifier);
			saveCoverageModifiers(ctx);
		}
	}
	
}
