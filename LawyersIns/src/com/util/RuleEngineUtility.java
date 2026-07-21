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
import com.ormapping.ibatis.SqlResources;
import com.outline.rules.pojo.AOP;
import com.outline.rules.pojo.Coverage;
import com.outline.rules.pojo.Firm;
import com.outline.rules.pojo.PlaintiffSupp;
import com.outline.rules.pojo.PracticeManagement;
import com.outline.rules.pojo.ResidentialSupplement;
import com.outline.rules.pojo.WillsTrustsEstates;

public class RuleEngineUtility {
	private static InetLogger logger = InetLogger.getInetLogger(RuleEngineUtility.class);
	public static void evaluateFirmWorkFlow(Context ctx)
	{
		
		
	}
	public static void evaluateAopWorkFlow(Context ctx)throws Exception
	{	
		////////////Common referrals//////////////////
		String ruleid="";
		List<Context> ruleSetContextList = new ArrayList<Context>();
		if(ctx.get("IsFirmHaveClientInEntertainmentInd")!=null)
		{
			ruleid="applicantWithPublicFigures_Lawyers";	
			ctx.put("isFirmHaveClientInEntertainmentInd",ctx.get("IsFirmHaveClientInEntertainmentInd"));
			AOP aop=(AOP)initlizePOJO(ctx, new AOP());
			ctx.put("RuleObject",aop);
			insertRulesToDatabase(callRuleEngine(ctx,ruleid),aop,ruleid,aop.getDescription(),aop.getTooltip());
			/*ruleSetContextList=initilizeRuleEngineContext(ctx,ruleid,"isFirmHaveClientInEntertainmentInd");
			insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
			clearRuleSetContextList(ruleid,ruleSetContextList);*/
		}
		if(ctx.get("IsFirmProvidedLegalService")!=null)
		{
			ctx.put("isFirmProvidedLegalService",ctx.get("IsFirmProvidedLegalService"));
			ruleid="hasFirmProvidedLegalServices_Lawyers";	
			AOP aop=(AOP)initlizePOJO(ctx, new AOP());
			ctx.put("RuleObject",aop);
			insertRulesToDatabase(callRuleEngine(ctx,ruleid),aop,ruleid,aop.getDescription(),aop.getTooltip());
			/*ruleSetContextList=initilizeRuleEngineContext(ctx,ruleid,"isFirmProvidedLegalService");
			insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
			clearRuleSetContextList(ruleid,ruleSetContextList);*/
		}
		List areasOfPracticeDataList=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetAopData",ctx);
		
		if(areasOfPracticeDataList!=null)
		{
			for (int i = 0; i < areasOfPracticeDataList.size(); i++) 
			{
				Map dataMap = (Map) areasOfPracticeDataList.get(i);
				
				if(dataMap.get("AOPKey").toString().equals("6"))
				{
					ruleid="copyrightTrademarkPatentAOP_Lawyers";
					
					ctx.put("ctpPercentage",dataMap.get("PercentageValue"));
					AOP areaOfPractice=(AOP)initlizePOJO(ctx, new AOP());
					ctx.put("RuleObject",areaOfPractice);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),areaOfPractice,ruleid,areaOfPractice.getDescription(),areaOfPractice.getTooltip());
					
				}
				if(dataMap.get("AOPKey").toString().equals("12"))
				{
					ruleid="financialInstitutionAOP_Lawyers";	
					
					ctx.put("financialPercentage",dataMap.get("PercentageValue"));
					AOP areaOfPractice=(AOP)initlizePOJO(ctx, new AOP());
					ctx.put("RuleObject",areaOfPractice);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),areaOfPractice,ruleid,areaOfPractice.getDescription(),areaOfPractice.getTooltip());
				}
				
				if(dataMap.get("AOPKey").toString().equals("10"))
				{
					ruleid="environmentalAOP_Lawyers";	
					ctx.put("environmentalAOPPercentage",dataMap.get("PercentageValue"));
					AOP areaOfPractice=(AOP)initlizePOJO(ctx, new AOP());
					ctx.put("RuleObject",areaOfPractice);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),areaOfPractice,ruleid,areaOfPractice.getDescription(),areaOfPractice.getTooltip());
					
				}
				if(dataMap.get("AOPKey").toString().equals("21"))
				{
					ruleid="securitiesBonds_Lawyers";	
					ctx.put("securitiesBondsAOPPercentage",dataMap.get("PercentageValue"));
					AOP areaOfPractice=(AOP)initlizePOJO(ctx, new AOP());
					ctx.put("RuleObject",areaOfPractice);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),areaOfPractice,ruleid,areaOfPractice.getDescription(),areaOfPractice.getTooltip());
					
				}
				if(dataMap.get("AOPKey").toString().equals("25"))
				{
					ruleid="otherAop_Lawyers";	
					ctx.put("otherAOPPercentage",dataMap.get("PercentageValue"));
					AOP areaOfPractice=(AOP)initlizePOJO(ctx, new AOP());
					ctx.put("RuleObject",areaOfPractice);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),areaOfPractice,ruleid,areaOfPractice.getDescription(),areaOfPractice.getTooltip());
				}
				if(dataMap.get("AOPKey").toString().equals("23"))
				{
					ruleid="titleOpinionsAop_Lawyers";	
					ctx.put("titleOpinionsAopPercentage",dataMap.get("PercentageValue"));
					AOP areaOfPractice=(AOP)initlizePOJO(ctx, new AOP());
					ctx.put("RuleObject",areaOfPractice);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),areaOfPractice,ruleid,areaOfPractice.getDescription(),areaOfPractice.getTooltip());
				
				}
				if(dataMap.get("AOPKey").toString().equals("15"))
				{
					ruleid="investmentCounselingMoneyMgtAOP_Lawyers";	
					ctx.put("investmentCounselingMoneyMgtPercentage",dataMap.get("PercentageValue"));
					AOP areaOfPractice=(AOP)initlizePOJO(ctx, new AOP());
					ctx.put("RuleObject",areaOfPractice);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),areaOfPractice,ruleid,areaOfPractice.getDescription(),areaOfPractice.getTooltip());
				}
			}
			
			//System.out.println(areaOfPractice.getTitleOpinionsAopPercentage()+" "+areaOfPractice.getOtherAOPPercentage());
			
		}
		
		///////////////Referrals for NB////////////////////////////////////
		
		if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("1"))
		{
			if(areasOfPracticeDataList!=null)
			{
				for (int i = 0; i < areasOfPracticeDataList.size(); i++) 
				{
					Map dataMap = (Map) areasOfPracticeDataList.get(i);
					if(dataMap.get("AOPKey").toString().equals("3"))
					{
						ruleid="bankruptcyAOP_Lawyers";	
						ctx.put("bankruptcyPercentage",dataMap.get("PercentageValue"));
						AOP areaOfPractice=(AOP)initlizePOJO(ctx, new AOP());
						ctx.put("RuleObject",areaOfPractice);
						insertRulesToDatabase(callRuleEngine(ctx,ruleid),areaOfPractice,ruleid,areaOfPractice.getDescription(),areaOfPractice.getTooltip());
					
						/*ruleSetContextList=initilizeRuleEngineContext(ctx,ruleid,"bankruptcyPercentage");
						insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
						clearRuleSetContextList(ruleid,ruleSetContextList);*/
					}
					if(dataMap.get("AOPKey").toString().equals("13"))
					{
						ruleid="governmentNBAOP_Lawyers";	
						ctx.put("governmentAOPPercentage",dataMap.get("PercentageValue"));
						AOP areaOfPractice=(AOP)initlizePOJO(ctx, new AOP());
						ctx.put("RuleObject",areaOfPractice);
						insertRulesToDatabase(callRuleEngine(ctx,ruleid),areaOfPractice,ruleid,areaOfPractice.getDescription(),areaOfPractice.getTooltip());
					
						/*ruleSetContextList=initilizeRuleEngineContext(ctx,ruleid,"GovernmentAOPPercentage");
						insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
						clearRuleSetContextList(ruleid,ruleSetContextList);*/
					}
				}
			}
		}
		
		/////////////Referrals for Renewal Flow////////////////////////////////////
		
		if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("2"))
		{
			List previousPolicyKey= (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey",ctx);
			ctx.putAll((Map) previousPolicyKey.get(0));
			Context oldPolicyContext=new Context();
			oldPolicyContext.setProject(ctx.getProject());
			oldPolicyContext.put("PolicyKey",ctx.get("PreviousPolicykey"));
			oldPolicyContext.put("VersionSequence",ctx.get("previousVersionSequence"));
			
			List areasOfPracticeDataListOld=SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementdroolQueriesgetAopData",oldPolicyContext);
			if(areasOfPracticeDataList!=null)
			{	String aopKey="";
		
					for (int i = 0; i <areasOfPracticeDataList.size(); i++) 
					{
						
						Map dataMap = (Map) areasOfPracticeDataList.get(i);
						aopKey=dataMap.get("AOPKey").toString();
						for (int j = 0; j <areasOfPracticeDataListOld.size(); j++) 
						{	
							Map dataMapOld = (Map) areasOfPracticeDataListOld.get(j);
							if(aopKey.equals("3") && aopKey.equals(dataMapOld.get("AOPKey").toString()))
							{
								
								
								ruleid="bankruptcyRenewAOP_Lawyers";	
								ctx.put("currentPercentage",dataMap.get("PercentageValue"));
								ctx.put("priorYearPercentage",dataMapOld.get("PercentageValue"));
								AOP aop=(AOP)initlizePOJO(ctx, new AOP());
								ctx.put("RuleObject",aop);
								insertRulesToDatabase(callRuleEngine(ctx,ruleid),aop,ruleid,aop.getDescription(),aop.getTooltip());
								/*ruleSetContextList=initilizeRuleEngineContext(ctx,ruleid,"currentPercentage,priorYearPercentage");
								insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
								clearRuleSetContextList(ruleid,ruleSetContextList);*/
							}
							if(aopKey.equals("13") && aopKey.equals(dataMapOld.get("AOPKey").toString()))
							{
								ruleid="governmentRenewAOP_Lawyers";	
								ctx.put("currentgovernmentPercentage",dataMap.get("PercentageValue"));
								ctx.put("priorYeargovernmentPercentage",dataMapOld.get("PercentageValue"));
								AOP aop=(AOP)initlizePOJO(ctx, new AOP());
								ctx.put("RuleObject",aop);
								insertRulesToDatabase(callRuleEngine(ctx,ruleid),aop,ruleid,aop.getDescription(),aop.getTooltip());
								/*ruleSetContextList=initilizeRuleEngineContext(ctx,ruleid,"currentgovernmentPercentage,priorYeargovernmentPercentage");
								insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
								clearRuleSetContextList(ruleid,ruleSetContextList);*/
							}
						}
					}
			
			}
		}
		
		if(areasOfPracticeDataList!=null)
		{
			for (int i = 0; i < areasOfPracticeDataList.size(); i++) 
			{
				Map dataMap = (Map) areasOfPracticeDataList.get(i);
				if(dataMap.get("AOPKey").toString().equals("18"))
				{
					if(dataMap.get("PercentageValue")==null || dataMap.get("PercentageValue").toString().equals("")|| Integer.valueOf(dataMap.get("PercentageValue").toString().trim())==0)
					{
						evaluatePlaintiffSupp(ctx);
					}
				}
				if(dataMap.get("AOPKey").toString().equals("20"))
				{
					if(dataMap.get("PercentageValue")==null || dataMap.get("PercentageValue").toString().equals("")|| Integer.valueOf(dataMap.get("PercentageValue").toString().trim())==0)
					{
						evaluateResidentialSupplement(ctx);
					}
				}
				if(dataMap.get("AOPKey").toString().equals("24"))
				{
					if(dataMap.get("PercentageValue")==null || dataMap.get("PercentageValue").toString().equals("")|| Integer.valueOf(dataMap.get("PercentageValue").toString().trim())==0)
					{
						evaluateWillsTrustsEstates(ctx);
					}
				}
				if(dataMap.get("AOPKey").toString().equals("27"))
				{
					if(dataMap.get("PercentageValue")==null || dataMap.get("PercentageValue").toString().equals("")|| Integer.valueOf(dataMap.get("PercentageValue").toString().trim())==0)
					{
						evaluateResidentialSupplement(ctx);
					}
				}
			}
		}
	}
	public static void evaluatePracticeManagementWorkFlow(Context ctx) throws Exception
	{	String ruleid="";
		List<Context> ruleSetContextList = new ArrayList<Context>();
		List firmDetails = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetFirmDetails",ctx);
		ctx.putAll((Map) firmDetails.get(0));
			if(ctx.get("IsFirmHaveClientMoreThan25PercentOfBilling")!=null && ctx.get("IsFirmHaveClientMoreThan25PercentOfBilling").toString().equalsIgnoreCase("Y"))
			{
			ruleid="IsFirmHaveClientMoreThanCertainPercentageOfBilling_Lawyers";
			ctx.put("percentFromFirstLargestRevenueClient",ctx.get("PercentFromFirstLargestRevenueClient"));
			ctx.put("percentFromSecondLargestRevenueClient",ctx.get("PercentFromSecondLargestRevenueClient"));
			PracticeManagement practiceManagement=(PracticeManagement)initlizePOJO(ctx, new PracticeManagement());
			ctx.put("RuleObject",practiceManagement);
			insertRulesToDatabase(callRuleEngine(ctx,ruleid),practiceManagement,ruleid,practiceManagement.getDescription(),practiceManagement.getTooltip());
			}
			else
			{
				ruleid="IsFirmHaveClientMoreThanCertainPercentageOfBilling_Lawyers";	
				archiveFlag(ctx,ruleid);
			}
			
			List firmInitLawsuits=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetFirmInitLawsuits",ctx);
			
			if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("1"))
			{
				
				if(ctx.get("IsFirmProvideInvestmentAdvice")!=null && ctx.get("IsFirmProvideInvestmentAdvice").toString().equalsIgnoreCase("Y"))
				{
					ctx.put("isFirmProvideInvestmentAdvice", ctx.get("IsFirmProvideInvestmentAdvice"));
				ruleid="IsFirmProvideInvestmentAdvice_Lawyers";
				PracticeManagement practiceManagement=(PracticeManagement)initlizePOJO(ctx, new PracticeManagement());
				ctx.put("RuleObject",practiceManagement);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),practiceManagement,ruleid,practiceManagement.getDescription(),practiceManagement.getTooltip());
				
				/*ruleSetContextList=initilizeRuleEngineContext(ctx,ruleid,"isFirmProvideInvestmentAdvice");
				insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
				clearRuleSetContextList(ruleid,ruleSetContextList);*/
				}
				else
				{
					ruleid="IsFirmProvideInvestmentAdvice_Lawyers";	
					archiveFlag(ctx,ruleid);
				}
				if(ctx.get("IsFirmHaveIndepDockets")!=null && ctx.get("IsFirmHaveIndepDockets").toString().equalsIgnoreCase("N"))
				{	
					ctx.put("isFirmHaveIndepDockets", ctx.get("IsFirmHaveIndepDockets"));
				ruleid="IsFirmHaveCenteralDocketOrDairySystem_Lawyers";
				PracticeManagement practiceManagement=(PracticeManagement)initlizePOJO(ctx, new PracticeManagement());
				ctx.put("RuleObject",practiceManagement);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),practiceManagement,ruleid,practiceManagement.getDescription(),practiceManagement.getTooltip());
				
				}
				else
				{
					ruleid="IsFirmHaveCenteralDocketOrDairySystem_Lawyers";	
					archiveFlag(ctx,ruleid);
					
				}
				
				if(ctx.get("PercentofApplAcctRcbl")!=null)
				{
					ctx.put("percentofApplAcctRcbl", ctx.get("PercentofApplAcctRcbl"));
				ruleid="firmAccountsReceivable_Lawyers";
				PracticeManagement practiceManagement=(PracticeManagement)initlizePOJO(ctx, new PracticeManagement());
				ctx.put("RuleObject",practiceManagement);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),practiceManagement,ruleid,practiceManagement.getDescription(),practiceManagement.getTooltip());
				
				
				}
				else
				{
					ruleid="firmAccountsReceivable_Lawyers";	
					archiveFlag(ctx,ruleid);
					
				}
			}
			
	}
	
	
	public static void evaluateCoverageWorkFlow(Context ctx) 
	{
	
		
		try
		{
		String ruleid="";
		ctx.put("effectiveDateOfPolicy",new Date(ctx.get("PolicyEffectiveDate").toString()).getTime());
		List<Context> ruleSetContextList = new ArrayList<Context>();
		List firmDetails = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetFirmDetails",ctx);
		ctx.putAll((Map) firmDetails.get(0));
		ctx.put("currentDate", new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
		ctx.put("stateCode", ctx.get("StateCode"));
		
		if(ctx.get("IsAttorneyDeclineForProfLiability")!=null &&  ctx.get("IsAttorneyDeclineForProfLiability").toString().equals("Y"))
		{
			ruleid="checkAttorneyDeclineForProfLiability_Lawyers";
			ctx.put("isAttorneyDeclineForProfLiability", ctx.get("IsAttorneyDeclineForProfLiability"));
			Coverage coverage=(Coverage)initlizePOJO(ctx, new Coverage());
			ctx.put("RuleObject",coverage);
			insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage,ruleid,coverage.getDescription(),coverage.getTooltip());
		}
		else
		{
			ruleid="checkAttorneyDeclineForProfLiability_Lawyers";	
			archiveFlag(ctx,ruleid);
		}
		
		List attorneyJoiningDate = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetAllAttorneys",ctx);
		if(ctx.get("IsFirmHaveLawyersLiabilityInsurance")!=null && ctx.get("IsFirmHaveLawyersLiabilityInsurance").toString().equals("Y") )
		{
			if(ctx.get("PriorActDatePross")!=null && !ctx.get("PriorActDatePross").toString().equals("") )
			{
				ruleid="checkPriorActDateWithAttorneysHiringDate_Lawyers";
				
				for (int i = 0; i < attorneyJoiningDate.size(); i++) 
				{
					Map dataMap = (Map) attorneyJoiningDate.get(i);
					Context newCtx=new Context();
					newCtx.setProject(ctx.getProject());
					ctx.put("attorneyPriorActDate", new Date(dataMap.get("AttorneyPriorActDate").toString()).getTime());
					ctx.put("priorActDate", new Date(ctx.get("PriorActDatePross").toString()).getTime());
					Coverage coverage1=(Coverage)initlizePOJO(ctx, new Coverage());
					newCtx.put("RuleObject",coverage1);
					newCtx=callRuleEngine(newCtx,ruleid);
					if(coverage1.getDescription()!=null && coverage1.getTooltip()!=null && !coverage1.getDescription().equals("") && !coverage1.getTooltip().equals(""))
					{
						newCtx.put("question",coverage1.getDescription());
						newCtx.put("hint",coverage1.getTooltip());
					}
					ruleSetContextList.add(newCtx);
				}
				insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
				clearRuleSetContextList(ruleid,ruleSetContextList);
				
				ruleid="checkPriorActDateWithEstablishDate_Lawyers";
				ctx.put("priorActDateYear", new SimpleDateFormat("YYYY").format(new Date(ctx.get("PriorActDatePross").toString())));
				ctx.put("yearOfFirmEstablished", ctx.get("YearOfFirmEstablished"));
				Coverage coverage=(Coverage)initlizePOJO(ctx, new Coverage());
				ctx.put("RuleObject",coverage);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage,ruleid,coverage.getDescription(),coverage.getTooltip());
			}
			else
			{
				ruleid="checkPriorActDateWithAttorneysHiringDate_Lawyers";	
				archiveFlag(ctx,ruleid);
				ruleid="checkPriorActDateWithEstablishDate_Lawyers";	
				archiveFlag(ctx,ruleid);
			}
			
			
			if(ctx.get("IsPolicyIncludeLateralHireCov")!=null && !ctx.get("IsPolicyIncludeLateralHireCov").toString().equals(""))
			{
				ruleid="checkPolicyIncludeLateralHireCoverage_Lawyers";
				ctx.put("isPolicyIncludeLateralHireCov", ctx.get("IsPolicyIncludeLateralHireCov"));
				Coverage coverage=(Coverage)initlizePOJO(ctx, new Coverage());
				ctx.put("RuleObject",coverage);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage,ruleid,coverage.getDescription(),coverage.getTooltip());
			}
			else
			{
				ruleid="checkPolicyIncludeLateralHireCoverage_Lawyers";	
				archiveFlag(ctx,ruleid);
			}
			if(ctx.get("IsPolicyExcludesCoverage")!=null && !ctx.get("IsPolicyExcludesCoverage").toString().equals(""))
			{
				ruleid="IsPolicyExcludesCoverage_Lawyers";
				ctx.put("isPolicyExcludesCoverage", ctx.get("IsPolicyExcludesCoverage"));
				Coverage coverage=(Coverage)initlizePOJO(ctx, new Coverage());
				ctx.put("RuleObject",coverage);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage,ruleid,coverage.getDescription(),coverage.getTooltip());
				
			}
			else
			{
				ruleid="IsPolicyExcludesCoverage_Lawyers";	
				archiveFlag(ctx,ruleid);
			}
			if(ctx.get("PolicyExpirationDatePross")!=null)
			{
				ctx.put("policyExpiryDate", new Date(ctx.get("PolicyExpirationDatePross").toString()).getTime());
				
				if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("1"))
				{
					ruleid="validateExpirationDateNewBusiness_Lawyers";
					Coverage coverage=(Coverage)initlizePOJO(ctx, new Coverage());
					ctx.put("RuleObject",coverage);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage,ruleid,coverage.getDescription(),coverage.getTooltip());
					
				}
				if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("2"))
				{
					ruleid="validateExpirationDateRenwal_Lawyers";
					Coverage coverage=(Coverage)initlizePOJO(ctx, new Coverage());
					ctx.put("RuleObject",coverage);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage,ruleid,coverage.getDescription(),coverage.getTooltip());
				}
				
				int limitSequenceDifference=(Integer.valueOf(ctx.get("LimitSequence").toString())-Integer.valueOf(ctx.get("LimitSequencePross").toString()))+1;
				ctx.put("limitSequenceDifference", limitSequenceDifference);
				ruleid="validateLimitsNewBusiness_Lawyers";
				Coverage coverage=(Coverage)initlizePOJO(ctx, new Coverage());
				ctx.put("RuleObject",coverage);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage,ruleid,coverage.getDescription(),coverage.getTooltip());
				}
			}
		else
		{
			ruleid="checkPriorActDateWithAttorneysHiringDate_Lawyers";	
			archiveFlag(ctx,ruleid);
			ruleid="checkPriorActDateWithEstablishDate_Lawyers";	
			archiveFlag(ctx,ruleid);
			ruleid="checkPolicyIncludeLateralHireCoverage_Lawyers";	
			archiveFlag(ctx,ruleid);
			ruleid="checkIsFirmMergedWithOtherFirm_Lawyers";	
			archiveFlag(ctx,ruleid);
			ruleid="validateExpirationDateNewBusiness_Lawyers";	
			archiveFlag(ctx,ruleid);
			ruleid="validateExpirationDateRenwal_Lawyers";	
			archiveFlag(ctx,ruleid);
			ruleid="validateLimitsNewBusiness_Lawyers";	
			archiveFlag(ctx,ruleid);
			ruleid="validateEffectiveDateWithCurrentInsurance_Lawyers";	
			archiveFlag(ctx,ruleid);
			ruleid="checkPolicyIncludeLateralHireCoverage_Lawyers";	
			archiveFlag(ctx,ruleid);
			
			
		}
		if(ctx.get("IsFirmMergedWithOtherFirm")!=null)
		{
			ruleid="checkIsFirmMergedWithOtherFirm_Lawyers";
			ctx.put("isFirmMergedWithOtherFirm", ctx.get("IsFirmMergedWithOtherFirm"));
			Coverage coverage=(Coverage)initlizePOJO(ctx, new Coverage());
			ctx.put("RuleObject",coverage);
			insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage,ruleid,coverage.getDescription(),coverage.getTooltip());
		}
		else
		{
			ruleid="checkIsFirmMergedWithOtherFirm_Lawyers";	
			archiveFlag(ctx,ruleid);
			
		}
		String effectiveDateOfPolicyString=new SimpleDateFormat("MM/dd/yyyy").format(new Date(ctx.get("PolicyEffectiveDate").toString()));
		
		
		if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("1"))
		{
			
			ruleid="validateEffectiveDateWithCurrentDateNewBusiness_Lawyers";
			ctx.put("isCurrentDateAterEfectiveDate",DateUtils.isDateBefore(ctx.get("PolicyEffectiveDate"), ctx.get("currentDate")));
			Coverage coverage=(Coverage)initlizePOJO(ctx, new Coverage());
			ctx.put("RuleObject",coverage);
			insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage,ruleid,coverage.getDescription(),coverage.getTooltip());
			
			long daysDifferenceForEfectiveDate=(DateUtils.calculateDiffInDays(new Date(effectiveDateOfPolicyString),new Date(ctx.get("currentDate").toString()))+1);
			ctx.put("daysDifferenceForEfectiveDate",daysDifferenceForEfectiveDate);
			logger.debug("daysDifferenceForEfectiveDate: "+daysDifferenceForEfectiveDate);
			ruleid="validateEffectiveDateWithDaysNewBusiness_Lawyers";
			Coverage coverage1=(Coverage)initlizePOJO(ctx, new Coverage());
			ctx.put("RuleObject",coverage1);
			
			insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage1,ruleid,coverage1.getDescription(),coverage1.getTooltip());
			
			List firmInitLawsuits=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetFirmInitLawsuits",ctx);

			int sum=0;
				if(ctx.get("IsApplInitiatedLawsuitForFirm")!=null && ctx.get("IsApplInitiatedLawsuitForFirm").toString().equalsIgnoreCase("Y"))
				{
				
					int count=0;
					if(firmInitLawsuits!=null)
					{
						
						for (int i = 0; i < firmInitLawsuits.size(); i++) 
						{
							Map dataMap = (Map) firmInitLawsuits.get(i);
							String str=dataMap.get("SuitFilesDateFees").toString();
							int  feeSuedDate=Integer.valueOf(str.substring(str.length()-4,str.length()));
							int  feeSuedMonth=Integer.valueOf(str.substring(str.length()-10,str.length()-8));
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
							SimpleDateFormat sdf1=new SimpleDateFormat("MM");
							int policyEffectiveYear=Integer.valueOf(sdf.format(new Date(new SimpleDateFormat("MM/dd/yyyy").format(new Date(ctx.get("PolicyEffectiveDate").toString())))).toString());
							int policyEffectiveMonth=Integer.valueOf(sdf1.format(new Date(new SimpleDateFormat("MM/dd/yyyy").format(new Date(ctx.get("PolicyEffectiveDate").toString())))).toString());
							//new SimpleDateFormat("MM/dd/yyyy").format(new Date(ctx.get("PolicyEffectiveDate").toString()))
							sum=sum+Integer.valueOf(dataMap.get("AmountOfFeesSued").toString());
							if(feeSuedDate==policyEffectiveYear)
							{
								long daysDifference=DateUtils.calculateDiffInDays(new Date(dataMap.get("SuitFilesDateFees").toString()),new Date(ctx.get("PolicyEffectiveDate").toString().toString()));
								if(daysDifference<0)
								count++;
								
							}
							if(feeSuedDate==(policyEffectiveYear-1)||feeSuedDate==(policyEffectiveYear-2))
							{
								count++;
								
							}
							if(feeSuedDate==(policyEffectiveYear-3))
							{
								if(feeSuedMonth>=policyEffectiveMonth)
								count++;
								
							}
						}
						
					}
					ctx.put("amountOfFeesSuedTotal",sum);
					ctx.put("coutOfFeeSuit", count);
					ruleid="checkCountOfFeeSuitInPastYears_Lawyers";
					Coverage coverage3=(Coverage)initlizePOJO(ctx, new Coverage());
					ctx.put("RuleObject",coverage3);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage3,ruleid,coverage3.getDescription(),coverage3.getTooltip());
					
					
					ruleid="checkAmountOfFeesSued_Lawyers";
					Coverage coverage2=(Coverage)initlizePOJO(ctx, new Coverage());
					ctx.put("RuleObject",coverage2);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage2,ruleid,coverage2.getDescription(),coverage2.getTooltip());
					
				
				}
				else
				{
					
					ruleid="checkCountOfFeeSuitInPastYears_Lawyers";	
					archiveFlag(ctx,ruleid);
					ruleid="checkAmountOfFeesSued_Lawyers";	
					archiveFlag(ctx,ruleid);
					
				}
				if(ctx.get("IsPersonnelBeSubOfAnyInvest")!=null && ctx.get("IsLawyerProfLiabClaimAgntAppl")!=null && ctx.get("IsAnyActOmmBecomeClaimAgntFirm")!=null  )
				{
					ruleid="claimsRegulatory_Lawyers";
					ctx.put("isPersonnelBeSubOfAnyInvest", ctx.get("IsPersonnelBeSubOfAnyInvest").toString().trim());
					ctx.put("isLawyerProfLiabClaimAgntAppl", ctx.get("IsLawyerProfLiabClaimAgntAppl").toString().trim());
					ctx.put("isAnyActOmmBecomeClaimAgntFirm", ctx.get("IsAnyActOmmBecomeClaimAgntFirm").toString().trim());
					 coverage=(Coverage)initlizePOJO(ctx, new Coverage());
					ctx.put("RuleObject",coverage);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage,ruleid,coverage.getDescription(),coverage.getTooltip());
				}
				else
				{
					ruleid="claimsRegulatory_Lawyers";	
					archiveFlag(ctx,ruleid);
				}
			
		}
		
			if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("2"))
			{
				if(ctx.get("IsPersonnelBeSubOfAnyInvest")!=null && ctx.get("IsLawyerProfLiabClaimAgntAppl")!=null && ctx.get("IsAnyActOmmBecomeClaimAgntFirm")!=null  )
				{
					ruleid="claimsRegulatoryRenewal_Lawyers";
					ctx.put("isPersonnelBeSubOfAnyInvest", ctx.get("IsPersonnelBeSubOfAnyInvest").toString().trim());
					ctx.put("isLawyerProfLiabClaimAgntAppl", ctx.get("IsLawyerProfLiabClaimAgntAppl").toString().trim());
					ctx.put("isAnyActOmmBecomeClaimAgntFirm", ctx.get("IsAnyActOmmBecomeClaimAgntFirm").toString().trim());
					Coverage coverage=(Coverage)initlizePOJO(ctx, new Coverage());
					ctx.put("RuleObject",coverage);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage,ruleid,coverage.getDescription(),coverage.getTooltip());
				}
				else
				{
					ruleid="claimsRegulatory_Lawyers";	
					archiveFlag(ctx,ruleid);
				}
				
				List firmInitLawsuits=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetFirmInitLawsuits",ctx);
				if(ctx.get("IsApplInitiatedLawsuitForFirm")!=null && ctx.get("IsApplInitiatedLawsuitForFirm").toString().equalsIgnoreCase("Y"))
				{
						int sum=0;
					
					if(firmInitLawsuits!=null)
					{
						
						for (int i = 0; i < firmInitLawsuits.size(); i++) 
						{
							Map dataMap = (Map) firmInitLawsuits.get(i);
							sum=sum+Integer.valueOf(dataMap.get("AmountOfFeesSued").toString());
							
						}
						
					}
					ctx.put("feeSuedTotal",sum);
					ruleid="checkAmountOfFeesSuedRenewal_Lawyers";
					Coverage coverage=(Coverage)initlizePOJO(ctx, new Coverage());
					ctx.put("RuleObject",coverage);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),coverage,ruleid,coverage.getDescription(),coverage.getTooltip());
				}
				else
				{
					ruleid="checkAmountOfFeesSuedRenewal_Lawyers";	
					archiveFlag(ctx,ruleid);
					
				}
			}
			
		}
	catch(Exception e)
	{
		logger.error("Exception ouccured at Coverage Page", e);
	}
	
		
	}
	

	public static void evaluatePlaintiffSupp(Context ctx) throws Exception
	{
		String ruleid="";
		List<Context> ruleSetContextList = new ArrayList<Context>();
		List plantiffSuppList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetPlantiffSuppList",ctx);
		if(plantiffSuppList !=null && plantiffSuppList.size()>0)
		{
		ctx.putAll((Map) plantiffSuppList.get(0));
		}
		List areaOfLitigationList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetAreaOfLitigation",ctx);
		if (areaOfLitigationList == null)
			areaOfLitigationList = new ArrayList();
		float commercial=0,wc=0;
		int medicalMalpractice=0,productsLiability=0,toxicTort=0,other=0;
		if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("1"))
		{
			if(areaOfLitigationList!=null && areaOfLitigationList.size()>0)
			{
				for (int i = 0; i < areaOfLitigationList.size(); i++) 
				{
					Map dataMap = (Map) areaOfLitigationList.get(i);
					if(dataMap.get("AOLKey").toString().equals("4"))
					{
						commercial=Float.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
					}
					if(dataMap.get("AOLKey").toString().equals("5"))
					{
						ctx.put("medicalMalpracticeAOLPercentage",dataMap.get("PercentageOfRevenue"));
						medicalMalpractice=Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
					}
					if(dataMap.get("AOLKey").toString().equals("8"))
					{
						ctx.put("productsLiabilityAOLPercentage",dataMap.get("PercentageOfRevenue"));
						productsLiability=Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
					}
					if(dataMap.get("AOLKey").toString().equals("11"))
					{
						ctx.put("toxicTortAOLPercentage",dataMap.get("PercentageOfRevenue"));
						toxicTort=Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
					}
					if(dataMap.get("AOLKey").toString().equals("12"))
					{
						wc=Float.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
					}
					if(dataMap.get("AOLKey").toString().equals("13"))
					{
						ctx.put("OtherAOLPercentage",dataMap.get("PercentageOfRevenue"));
						other=Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
					}
				}
				
				if(medicalMalpractice>0  || productsLiability>0 || toxicTort>0 ||other>0)
				{
					ruleid="validateAreaOfLitigation_Lawyers";
					ctx.put("otherAol",other );
					ctx.put("medicalMalpracticeAol", medicalMalpractice);
					ctx.put("productsLiabilityAol", productsLiability);
					ctx.put("toxicTortAol", toxicTort);
					PlaintiffSupp plaintiffSupp=(PlaintiffSupp)initlizePOJO(ctx, new PlaintiffSupp());
					ctx.put("RuleObject",plaintiffSupp);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),plaintiffSupp,ruleid,plaintiffSupp.getDescription(),plaintiffSupp.getTooltip());
				}
				else
				{
					ruleid="validateAreaOfLitigation_Lawyers";	
					archiveFlag(ctx,ruleid);
				}
				
				ruleid="validatePlaintiffLargestCase_Lawyers";
				for (int i = 0; i < areaOfLitigationList.size(); i++) 
				{
					Map dataMap = (Map) areaOfLitigationList.get(i);
					Context newCtx=new Context();
					newCtx.setProject(ctx.getProject());
					ctx.put("plaintiffLargestCaseSize",dataMap.get("LargestCaseSize"));
					PlaintiffSupp plaintiffSupp1=(PlaintiffSupp)initlizePOJO(ctx, new PlaintiffSupp());
					newCtx.put("RuleObject",plaintiffSupp1);
					newCtx=callRuleEngine(newCtx,ruleid);
					if(plaintiffSupp1.getDescription()!=null && plaintiffSupp1.getTooltip()!=null && !plaintiffSupp1.getDescription().equals("") && !plaintiffSupp1.getTooltip().equals(""))
					{
						newCtx.put("question",plaintiffSupp1.getDescription());
						newCtx.put("hint",plaintiffSupp1.getTooltip());
					}
					ruleSetContextList.add(newCtx);
				}
				insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
				clearRuleSetContextList(ruleid,ruleSetContextList);
			
			}
			else
			{
				ruleid="validateAreaOfLitigation_Lawyers";	
				archiveFlag(ctx,ruleid);
				ruleid="validatePlaintiffLargestCase_Lawyers";	
				archiveFlag(ctx,ruleid);
			}
			

			if(areaOfLitigationList!=null && areaOfLitigationList.size()>0)
			{	boolean flag=false;
				for (int i = 0; i < areaOfLitigationList.size(); i++) 
				{
					Map dataMap = (Map) areaOfLitigationList.get(i);
					if(dataMap.get("PercentageOfRevenue")!=null && Integer.valueOf((dataMap.get("PercentageOfRevenue").toString().trim()))>0)
					{
						if(dataMap.get("LargestCaseSize")==null || dataMap.get("LargestCaseSize").toString().equals(""))
							flag=true;
					}
				}
				
					ctx.put("isLargestCaseMissing", flag);
					String plaintiffPercentage=ctx.get("AOP_Percentage_18").toString();
					ctx.put("plaintiffPercentage", plaintiffPercentage);
				
					ruleid="missingLargestCase_Lawyers";	
					PlaintiffSupp plaintiffSupp=(PlaintiffSupp)initlizePOJO(ctx, new PlaintiffSupp());
					ctx.put("RuleObject",plaintiffSupp);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),plaintiffSupp,ruleid,plaintiffSupp.getDescription(),plaintiffSupp.getTooltip());
				}
			else
			{
				ruleid="missingLargestCase_Lawyers";	
				archiveFlag(ctx,ruleid);
				
			}
			if(ctx.get("NumberOfInjuryHandleByAttorney")!=null)
			{
			ruleid="validateAverageCasesPerAttorney_Lawyers";
			ctx.put("numberOfInjuryHandleByAttorney", ctx.get("NumberOfInjuryHandleByAttorney"));
			PlaintiffSupp plaintiffSupp=(PlaintiffSupp)initlizePOJO(ctx, new PlaintiffSupp());
			ctx.put("RuleObject",plaintiffSupp);
			insertRulesToDatabase(callRuleEngine(ctx,ruleid),plaintiffSupp,ruleid,plaintiffSupp.getDescription(),plaintiffSupp.getTooltip());
			
			/*ruleSetContextList=initilizeRuleEngineContext(ctx,ruleid,"numberOfInjuryHandleByAttorney");
			insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
			clearRuleSetContextList(ruleid,ruleSetContextList);*/
			}
			else
			{
				ruleid="validateAverageCasesPerAttorney_Lawyers";	
				archiveFlag(ctx,ruleid);
				
			}
			
			if(ctx.get("IsSettlementAggrementsUsed")!=null && ctx.get("IsSettlementAggrementsUsed").toString().equals("N"))
			{
				ruleid="validateAuthorizationsForSettlement_Lawyers";
				ctx.put("isSettlementAggrementsUsed", ctx.get("IsSettlementAggrementsUsed"));
				PlaintiffSupp plaintiffSupp=(PlaintiffSupp)initlizePOJO(ctx, new PlaintiffSupp());
				ctx.put("RuleObject",plaintiffSupp);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),plaintiffSupp,ruleid,plaintiffSupp.getDescription(),plaintiffSupp.getTooltip());
			}
			else
			{
				ruleid="validateAuthorizationsForSettlement_Lawyers";	
				archiveFlag(ctx,ruleid);
			}
		}
		
		
	
		if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("2"))
		{
			List previousPolicyKey= (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey",ctx);
			ctx.putAll((Map) previousPolicyKey.get(0));
			Context oldPolicyContext=new Context();
			oldPolicyContext.setProject(ctx.getProject());
			oldPolicyContext.put("PolicyKey",ctx.get("PreviousPolicykey"));
			oldPolicyContext.put("VersionSequence",ctx.get("previousVersionSequence"));
			List plantiffSuppListOld = SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementdroolQueriesgetPlantiffSuppList",oldPolicyContext);
			if(plantiffSuppListOld !=null && plantiffSuppListOld.size()>0)
			{
				oldPolicyContext.putAll((Map) plantiffSuppListOld.get(0));
			}
			List areaOfLitigationListOld = SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementdroolQueriesgetAreaOfLitigation",oldPolicyContext);
			commercial=0;wc=0;
			 medicalMalpractice=0;productsLiability=0;toxicTort=0;other=0;
			 int medicalMalpracticeOld=0,productsLiabilityOld=0,toxicTortOld=0,otherOld=0;
			
			if(areaOfLitigationList!=null && areaOfLitigationList.size()>0)
			{
				for (int i = 0; i < areaOfLitigationList.size(); i++) 
				{
					Map dataMap = (Map) areaOfLitigationList.get(i);
					Map dataMapOld=null;
					if(areaOfLitigationListOld!=null && areaOfLitigationListOld.size()>0)
					{
					 dataMapOld = (Map) areaOfLitigationListOld.get(i);
					}
					if(dataMap.get("AOLKey").toString().equals("4"))
					{
						commercial=Float.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
					}
					if(dataMap.get("AOLKey").toString().equals("5"))
					{
						ctx.put("medicalMalpracticeAol",dataMap.get("PercentageOfRevenue"));
						if(areaOfLitigationListOld!=null && areaOfLitigationListOld.size()>0)
						ctx.put("medicalMalpracticeAolOld",dataMapOld!=null?dataMapOld.get("PercentageOfRevenue").toString():0);
						else 
							ctx.put("medicalMalpracticeAolOld",0);
						medicalMalpractice=Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
						medicalMalpracticeOld=dataMapOld!=null?Integer.valueOf(dataMapOld.get("PercentageOfRevenue").toString()):0;
						
					}
					if(dataMap.get("AOLKey").toString().equals("8"))
					{
						ctx.put("productsLiabilityAol",dataMap.get("PercentageOfRevenue"));
						if(areaOfLitigationListOld!=null && areaOfLitigationListOld.size()>0)
						ctx.put("productsLiabilityAolOld",dataMapOld!=null?dataMapOld.get("PercentageOfRevenue").toString():0);
						else
							ctx.put("productsLiabilityAolOld",0);
						productsLiability=Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
						
					}
					if(dataMap.get("AOLKey").toString().equals("11"))
					{
						ctx.put("toxicTortAol",dataMap.get("PercentageOfRevenue"));
						if(areaOfLitigationListOld!=null && areaOfLitigationListOld.size()>0)
						ctx.put("toxicTortAolOld",dataMapOld!=null?dataMapOld.get("PercentageOfRevenue").toString():0);
						else
							ctx.put("toxicTortAolOld",0);
						toxicTort=Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
											}
					if(dataMap.get("AOLKey").toString().equals("12"))
					{
						wc=Float.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
					}
					if(dataMap.get("AOLKey").toString().equals("13"))
					{
						ctx.put("otherAol",dataMap.get("PercentageOfRevenue"));
						if(areaOfLitigationListOld!=null && areaOfLitigationListOld.size()>0)
							ctx.put("otherAolOld",dataMapOld!=null?dataMapOld.get("PercentageOfRevenue").toString():0);
						else
							ctx.put("otherAolOld",0);
						other=Integer.valueOf(dataMap.get("PercentageOfRevenue").toString().trim());
						
				}
				}
				if(medicalMalpractice>0  || productsLiability>0 || toxicTort>0 ||other>0)
				{
					ruleid="validateAreaOfLitigationRenewal_Lawyers";
					PlaintiffSupp plaintiffSupp=(PlaintiffSupp)initlizePOJO(ctx, new PlaintiffSupp());
					ctx.put("RuleObject",plaintiffSupp);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),plaintiffSupp,ruleid,plaintiffSupp.getDescription(),plaintiffSupp.getTooltip());
				}
				else
				{
					ruleid="validateAreaOfLitigation_Lawyers";	
					archiveFlag(ctx,ruleid);
				}
		
			}
			
			if(areaOfLitigationList!=null && areaOfLitigationList.size()>0)
			{	boolean flag=false;
				for (int i = 0; i < areaOfLitigationList.size(); i++) 
				{
					Map dataMap = (Map) areaOfLitigationList.get(i);
					Map dataMapOld=null;
					if(areaOfLitigationListOld!=null && areaOfLitigationListOld.size()>0)
					{
					 dataMapOld = (Map) areaOfLitigationListOld.get(i);
					}
					if(dataMap.get("PercentageOfRevenue")!=null && Integer.valueOf((dataMap.get("PercentageOfRevenue").toString().trim()))>0)
					{
						if(dataMap.get("LargestCaseSize")==null || dataMap.get("LargestCaseSize").toString().equals(""))
							flag=true;
					}
				}
				List areasOfPracticeDataLisOldt=SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementdroolQueriesgetAopData",oldPolicyContext);
				if (areasOfPracticeDataLisOldt == null)
					areasOfPracticeDataLisOldt = new ArrayList();
				if(areasOfPracticeDataLisOldt!=null && areasOfPracticeDataLisOldt.size()>0)
				oldPolicyContext.putAll((Map)areasOfPracticeDataLisOldt.get(0));
				
				ctx.put("isLargestCaseMissing", flag);
					
					for (int i = 0; i < areasOfPracticeDataLisOldt.size(); i++) 
					{
						Map dataMap = (Map) areasOfPracticeDataLisOldt.get(i);
						if(dataMap.get("AOPKey").toString().equals("18"))
						{
						
							ctx.put("plaintiffPercentageOld",dataMap!=null?dataMap.get("PercentageValue").toString():0);
						
						}
					}
					int plaintiffPercentage=ctx.get("AOP_Percentage_18")!=null?Integer.valueOf(ctx.get("AOP_Percentage_18").toString()):0;
					//int plaintiffPercentageOld=oldPolicyContext.get("AOP_Percentage_18")!=null?Integer.valueOf(oldPolicyContext.get("AOP_Percentage_18").toString()):0;
					int plaintiffPercentageOld=ctx.get("plaintiffPercentageOld")!=null?Integer.valueOf(ctx.get("plaintiffPercentageOld").toString()):0;
					ctx.put("plaintiffPercentage", plaintiffPercentage);
					ctx.put("plaintiffPercentageOld", plaintiffPercentageOld);
				
					ruleid="missingLargestCaseRenewal_Lawyers";	
					PlaintiffSupp plaintiffSupp=(PlaintiffSupp)initlizePOJO(ctx, new PlaintiffSupp());
					ctx.put("RuleObject",plaintiffSupp);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),plaintiffSupp,ruleid,plaintiffSupp.getDescription(),plaintiffSupp.getTooltip());
					}
			else
			{
				ruleid="missingLargestCaseRenewal_Lawyers";	
				archiveFlag(ctx,ruleid);
				
			}
			
			if(ctx.get("NumberOfInjuryHandleByAttorney")!=null)
			{
				ruleid="validateAverageCasesPerAttorneyRenewal_Lawyers";
				ctx.put("numberOfInjuryHandleByAttorneyOld",oldPolicyContext.get("NumberOfInjuryHandleByAttorney")!=null?oldPolicyContext.get("NumberOfInjuryHandleByAttorney"):"0");
				ctx.put("numberOfInjuryHandleByAttorney", ctx.get("NumberOfInjuryHandleByAttorney"));
				PlaintiffSupp plaintiffSupp=(PlaintiffSupp)initlizePOJO(ctx, new PlaintiffSupp());
				ctx.put("RuleObject",plaintiffSupp);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),plaintiffSupp,ruleid,plaintiffSupp.getDescription(),plaintiffSupp.getTooltip());
			}
			else
			{
				ruleid="validateAverageCasesPerAttorneyRenewal_Lawyers";	
				archiveFlag(ctx,ruleid);
			}
			
			
				ruleid="validateAuthorizationsForSettlementRenewal_Lawyers";
				ctx.put("isSettlementAggrementsUsedOld", oldPolicyContext.get("IsSettlementAggrementsUsed")!=null?oldPolicyContext.get("IsSettlementAggrementsUsed"):"X");
				ctx.put("isSettlementAggrementsUsed", ctx.get("IsSettlementAggrementsUsed"));
				PlaintiffSupp plaintiffSupp=(PlaintiffSupp)initlizePOJO(ctx, new PlaintiffSupp());
				ctx.put("RuleObject",plaintiffSupp);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),plaintiffSupp,ruleid,plaintiffSupp.getDescription(),plaintiffSupp.getTooltip());
				
				
				ruleid="validatePlaintiffLargestCase_Lawyers";
				for (int i = 0; i < areaOfLitigationList.size(); i++) 
				{
					Map dataMap = (Map) areaOfLitigationList.get(i);
					Context newCtx=new Context();
					newCtx.setProject(ctx.getProject());
					ctx.put("plaintiffLargestCaseSize",dataMap.get("LargestCaseSize"));
					PlaintiffSupp plaintiffSupp1=(PlaintiffSupp)initlizePOJO(ctx, new PlaintiffSupp());
					newCtx.put("RuleObject",plaintiffSupp1);
					newCtx=callRuleEngine(newCtx,ruleid);
					if(plaintiffSupp1.getDescription()!=null && plaintiffSupp1.getTooltip()!=null && !plaintiffSupp1.getDescription().equals("") && !plaintiffSupp1.getTooltip().equals(""))
					{
						newCtx.put("question",plaintiffSupp1.getDescription());
						newCtx.put("hint",plaintiffSupp1.getTooltip());
					}
					ruleSetContextList.add(newCtx);
				}
				insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
				clearRuleSetContextList(ruleid,ruleSetContextList);
			}
		
		
		if(ctx.get("AOP_Percentage_18")!=null && Integer.valueOf(ctx.get("AOP_Percentage_18").toString())>0)
		{	
			float mainPlaintiff=0;
			if(ctx.get("AOP_Percentage_18")!=null)
				 mainPlaintiff=Float.valueOf(ctx.get("AOP_Percentage_18").toString())/100;
			float result=mainPlaintiff*((commercial/100)+(wc/100));
			int difference=Math.round((mainPlaintiff-result)*100);
			ruleid="plaintiffLitigationAOP_Lawyers";	
			ctx.put("difference",difference);
			ctx.put("stateCode", ctx.get("StateCode"));
			PlaintiffSupp plaintiffSupp=(PlaintiffSupp)initlizePOJO(ctx, new PlaintiffSupp());
			ctx.put("RuleObject",plaintiffSupp);
			insertRulesToDatabase(callRuleEngine(ctx,ruleid),plaintiffSupp,ruleid,plaintiffSupp.getDescription(),plaintiffSupp.getTooltip());
			
		}
		else
		{
			ruleid="plaintiffLitigationAOP_Lawyers";	
			archiveFlag(ctx,ruleid);
		}
		
		
		/*if(areaOfLitigationList!=null && areaOfLitigationList.size()>0)
		{	boolean flag=false;
			for (int i = 0; i < areaOfLitigationList.size(); i++) 
			{
				Map dataMap = (Map) areaOfLitigationList.get(i);
				if(dataMap.get("PercentageOfRevenue")!=null && Integer.valueOf((dataMap.get("PercentageOfRevenue").toString().trim()))>0)
				{
					if(dataMap.get("LargestCaseSize")==null || dataMap.get("LargestCaseSize").toString().equals(""))
						flag=true;
				}
			}
			
				ctx.put("isLargestCaseMissing", flag);
				String plaintiffPercentage=ctx.get("AOP_Percentage_18").toString();
				ctx.put("plaintiffPercentage", plaintiffPercentage);
			
				ruleid="missingLargestCaseRenewal_Lawyers";	
				ruleSetContextList=initilizeRuleEngineContext(ctx,ruleid,"isLargestCaseMissing,plaintiffPercentage");
				insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
				clearRuleSetContextList(ruleid,ruleSetContextList);
			}
		else
		{
			ruleid="missingLargestCaseRenewal_Lawyers";	
			archiveFlag(ctx,ruleid);
			
		}*/
		}
		
		

	
	public static void evaluateWillsTrustsEstates(Context ctx) throws Exception
	{
		List<Context> ruleSetContextList = new ArrayList<Context>();
		List willsTrustsEstateDataList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetWillsTrustsEstateData",ctx);
		String ruleid="";
		if(willsTrustsEstateDataList !=null && willsTrustsEstateDataList.size()>0)
		{	
			
			
			ruleid="checkApplHavediscretionaryControlOverFunds_Lawyers";
			ctx.put("isApplHaveControlOverFunds", ctx.get("IsApplHaveControlOverFunds"));
			WillsTrustsEstates willsTrustsEstates=(WillsTrustsEstates)initlizePOJO(ctx, new WillsTrustsEstates());
			ctx.put("RuleObject",willsTrustsEstates);
			insertRulesToDatabase(callRuleEngine(ctx,ruleid),willsTrustsEstates,ruleid,willsTrustsEstates.getDescription(),willsTrustsEstates.getTooltip());
		
			if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("1"))
			{
				ruleid="checkClientEstatesOrTrusts_Lawyers";
				if(ctx.get("NumberOfEstateOver5Million")==null || ctx.get("NumberOfEstateOver5Million").toString().equals(""))
				{
					ctx.put("estateOver5Million",0);
				}
				else
				{
					ctx.put("estateOver5Million",ctx.get("NumberOfEstateOver5Million"));
				}
				if(ctx.get("NumberOfEstateOver10Million")==null || ctx.get("NumberOfEstateOver10Million").toString().equals(""))
				{
					ctx.put("estateOver10Million",0);
				}
				else
				{
					ctx.put("estateOver10Million",ctx.get("NumberOfEstateOver10Million"));
				}
				WillsTrustsEstates willsTrustsEstates1=(WillsTrustsEstates)initlizePOJO(ctx, new WillsTrustsEstates());
				ctx.put("RuleObject",willsTrustsEstates1);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),willsTrustsEstates1,ruleid,willsTrustsEstates1.getDescription(),willsTrustsEstates1.getTooltip());
			}
			
			if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("2"))
				{
				List previousPolicyKey= (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey",ctx);
				ctx.putAll((Map) previousPolicyKey.get(0));
				Context oldPolicyContext=new Context();
				oldPolicyContext.setProject(ctx.getProject());
				oldPolicyContext.put("PolicyKey",ctx.get("PreviousPolicykey"));
				oldPolicyContext.put("VersionSequence",ctx.get("previousVersionSequence"));
				List willsTrustsEstateDataListOld = SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementdroolQueriesgetWillsTrustsEstateData",oldPolicyContext);
				if(willsTrustsEstateDataListOld!=null && willsTrustsEstateDataListOld.size()>0)
				oldPolicyContext.putAll((Map) willsTrustsEstateDataListOld.get(0));
				
				ruleid="checkClientEstatesOrTrustsRenewal_Lawyers";
				ctx.put("numberOfEstateOver5Million",ctx.get("NumberOfEstateOver5Million")!=null?ctx.get("NumberOfEstateOver5Million"):0);
				ctx.put("numberOfEstateOver5MillionOld",oldPolicyContext.get("NumberOfEstateOver5Million")!=null?oldPolicyContext.get("NumberOfEstateOver5Million"):0);
				ctx.put("numberOfEstateOver10Million",ctx.get("NumberOfEstateOver10Million")!=null?ctx.get("NumberOfEstateOver10Million"):0);
				ctx.put("numberOfEstateOver10MillionOld",oldPolicyContext.get("NumberOfEstateOver10Million")!=null?oldPolicyContext.get("NumberOfEstateOver10Million"):0);
				WillsTrustsEstates willsTrustsEstates2=(WillsTrustsEstates)initlizePOJO(ctx, new WillsTrustsEstates());
				ctx.put("RuleObject",willsTrustsEstates2);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),willsTrustsEstates2,ruleid,willsTrustsEstates2.getDescription(),willsTrustsEstates2.getTooltip());
			}
			List willsEstateServicesList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetWillsEstateServicesList",ctx);
			if(willsEstateServicesList!=null)
			{  
				for (int i = 0; i < willsEstateServicesList.size(); i++) 
				{
					Map dataMap = (Map) willsEstateServicesList.get(i);
					
					if(dataMap.get("WESKey").toString().equals("8"))
					{
						ruleid="checkAssetProtectionChecked_Lawyers";	
						ctx.put("assetProtectionValue",dataMap.get("CheckedValue"));
						WillsTrustsEstates willsTrustsEstates3=(WillsTrustsEstates)initlizePOJO(ctx, new WillsTrustsEstates());
						ctx.put("RuleObject",willsTrustsEstates3);
						insertRulesToDatabase(callRuleEngine(ctx,ruleid),willsTrustsEstates3,ruleid,willsTrustsEstates3.getDescription(),willsTrustsEstates3.getTooltip());
					}
					if(dataMap.get("WESKey").toString().equals("7"))
					{
						ruleid="checkTaxationChecked_Lawyers";	
						ctx.put("taxationValue",dataMap.get("CheckedValue"));
						WillsTrustsEstates willsTrustsEstates3=(WillsTrustsEstates)initlizePOJO(ctx, new WillsTrustsEstates());
						ctx.put("RuleObject",willsTrustsEstates3);
						insertRulesToDatabase(callRuleEngine(ctx,ruleid),willsTrustsEstates3,ruleid,willsTrustsEstates3.getDescription(),willsTrustsEstates3.getTooltip());
					}
				}
			}
		}
		else
		{
			ruleid="checkAttorneyAsExecutorTrusteeAndAppControlOverFunds_Lawyers";	
			archiveFlag(ctx,ruleid);
			ruleid="checkApplHavediscretionaryControlOverFunds_Lawyers";	
			archiveFlag(ctx,ruleid);
			ruleid="checkClientEstatesOrTrusts_Lawyers";	
			archiveFlag(ctx,ruleid);
			ruleid="checkAssetProtectionChecked_Lawyers";	
			archiveFlag(ctx,ruleid);
			ruleid="checkTaxationChecked_Lawyers";	
			archiveFlag(ctx,ruleid);
			
			
		}
		
		
	}
	public static void evaluateResidentialSupplement(Context ctx) throws Exception
	{
		String ruleid="";
		Context oldPolicyContext=null;
		List<Context> ruleSetContextList = new ArrayList<Context>();
		List getRealEstateResiList=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetRealEstateResiList",ctx);
		List getRealEstateResiListOld=null,getRealEstateCommListOld=null;
		if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("2"))
		{
		List previousPolicyKey= (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey",ctx);
		ctx.putAll((Map) previousPolicyKey.get(0));
		oldPolicyContext=new Context();
		oldPolicyContext.setProject(ctx.getProject());
		oldPolicyContext.put("PolicyKey",ctx.get("PreviousPolicykey"));
		oldPolicyContext.put("VersionSequence",ctx.get("previousVersionSequence"));
		getRealEstateResiListOld=SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementdroolQueriesgetRealEstateResiList",oldPolicyContext);
		
		}
		if(getRealEstateResiList!=null && getRealEstateResiList.size()>0)
		{
			for (int i = 0; i < getRealEstateResiList.size(); i++) 
			{
				Map dataMap = (Map) getRealEstateResiList.get(i);
				Map dataMapOld=null; 
				if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("2"))
				{
					if(getRealEstateResiListOld!=null && getRealEstateResiListOld.size()>0)
					dataMapOld = (Map) getRealEstateResiListOld.get(i);
				}
				
				if(dataMap.get("AOPREKey").toString().equals("19"))
				{
					ctx.put("foreclosuresResidentialPercentage",dataMap.get("PercentageValue")!=null?dataMap.get("PercentageValue"):0);
					
				}
				if(dataMap.get("AOPREKey").toString().equals("20"))
				{
					if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("1"))
					{
						ruleid="checkOtherResidentialAop_Lawyers";	
						ctx.put("otherAopResidentialPercentage",dataMap.get("PercentageValue")!=null?dataMap.get("PercentageValue"):0);
						ResidentialSupplement residentialSupplement=(ResidentialSupplement)initlizePOJO(ctx, new ResidentialSupplement());
						ctx.put("RuleObject",residentialSupplement);
						insertRulesToDatabase(callRuleEngine(ctx,ruleid),residentialSupplement,ruleid,residentialSupplement.getDescription(),residentialSupplement.getTooltip());
						
					}
					if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("2"))
					{
						
						ruleid="checkOtherResidentialAopRenewal_Lawyers";	
						ctx.put("otherAopResidentialPercentage",dataMap.get("PercentageValue")!=null?dataMap.get("PercentageValue"):0);
						if(getRealEstateResiListOld!=null && getRealEstateResiListOld.size()>0  )
						ctx.put("otherAopResidentialPercentageOld",!dataMapOld.isEmpty() && dataMapOld.get("PercentageValue")!=null?dataMapOld.get("PercentageValue"):0);
						else
							ctx.put("otherAopResidentialPercentageOld",0);	
						ResidentialSupplement residentialSupplement=(ResidentialSupplement)initlizePOJO(ctx, new ResidentialSupplement());
						ctx.put("RuleObject",residentialSupplement);
						insertRulesToDatabase(callRuleEngine(ctx,ruleid),residentialSupplement,ruleid,residentialSupplement.getDescription(),residentialSupplement.getTooltip());
						
					}
				}
				if(dataMap.get("AOPREKey").toString().equals("21"))
				{
					ctx.put("LoanWorkoutsAopResidentialPercentage",dataMap.get("PercentageValue")!=null?dataMap.get("PercentageValue"):0);
					
				}
				
			}
			if((ctx.get("foreclosuresResidentialPercentage")!=null && Integer.valueOf(ctx.get("foreclosuresResidentialPercentage").toString())>0)
				||(ctx.get("LoanWorkoutsAopResidentialPercentage")!=null && Integer.valueOf(ctx.get("LoanWorkoutsAopResidentialPercentage").toString())>0))
			{
				ruleid="checkForeclosuresAndLoanWorkoutsAOPResidential_Lawyers";	
				ctx.put("loanWorkoutsAopResidentialPercentage", ctx.get("LoanWorkoutsAopResidentialPercentage"));
				ResidentialSupplement residentialSupplement=(ResidentialSupplement)initlizePOJO(ctx, new ResidentialSupplement());
				ctx.put("RuleObject",residentialSupplement);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),residentialSupplement,ruleid,residentialSupplement.getDescription(),residentialSupplement.getTooltip());
			}
			else
			{
				ruleid="checkForeclosuresAndLoanWorkoutsAOPResidential_Lawyers";	
				archiveFlag(ctx,ruleid);
			}
		
		}
		else
		{
			ruleid="checkForeclosuresAndLoanWorkoutsAOPResidential_Lawyers";	
			archiveFlag(ctx,ruleid);
			ruleid="checkOtherResidentialAop_Lawyers";	
			archiveFlag(ctx,ruleid);
		}
		
		List getRealEstateCommList=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetRealEstateCommList",ctx);
		if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("2"))
		{ 
			getRealEstateCommListOld=SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementdroolQueriesgetRealEstateCommList",oldPolicyContext);
		}
		if(getRealEstateCommList!=null && getRealEstateCommList.size()>0)
		{
			for (int i = 0; i < getRealEstateCommList.size(); i++) 
			{
				Map dataMap = (Map) getRealEstateCommList.get(i);
				Map dataMapOld=null; 
				if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("2"))
				{
					if(getRealEstateCommListOld!=null && getRealEstateCommListOld.size()>0)
					 dataMapOld = (Map) getRealEstateCommListOld.get(i);
				}
				if(dataMap.get("AOPREKey").toString().equals("8"))
				{
					ctx.put("foreclosuresCommercialPercentage",dataMap.get("PercentageValue")!=null?dataMap.get("PercentageValue"):0);
				}
				if(dataMap.get("AOPREKey").toString().equals("10"))
				{
					
					ctx.put("loanWorkoutsCommercialPercentage",dataMap.get("PercentageValue")!=null?dataMap.get("PercentageValue"):0);
					
					
				}
				if(dataMap.get("AOPREKey").toString().equals("13"))
				{
					ctx.put("SpeculativeAopCommercialPercentage",dataMap.get("PercentageValue")!=null?dataMap.get("PercentageValue"):0);
					
				}
				if(dataMap.get("AOPREKey").toString().equals("15"))
				{
					ctx.put("investmentTrustsPercentage",dataMap.get("PercentageValue")!=null?dataMap.get("PercentageValue"):0);
					
				}
				if(dataMap.get("AOPREKey").toString().equals("16"))
				{
					ctx.put("estateSyndicationsAopCommercialPercentage",dataMap.get("PercentageValue")!=null?dataMap.get("PercentageValue"):0);
					
				}
				
				if(dataMap.get("AOPREKey").toString().equals("17"))
				{
					if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("1"))
					{
						ruleid="checkOtherCommercialAop_Lawyers";	
						ctx.put("otherAopCommercialPercentage",dataMap.get("PercentageValue")!=null?dataMap.get("PercentageValue"):0);
						ResidentialSupplement residentialSupplement=(ResidentialSupplement)initlizePOJO(ctx, new ResidentialSupplement());
						ctx.put("RuleObject",residentialSupplement);
						insertRulesToDatabase(callRuleEngine(ctx,ruleid),residentialSupplement,ruleid,residentialSupplement.getDescription(),residentialSupplement.getTooltip());
						
					}
				
					if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("2"))
					{
						ruleid="checkOtherCommercialAopRenewal_Lawyers";	
						ctx.put("otherAopCommercialPercentage",dataMap.get("PercentageValue")!=null?dataMap.get("PercentageValue"):0);
						if(getRealEstateCommListOld !=null && getRealEstateCommListOld.size()>0)
						ctx.put("otherAopCommercialPercentageOld",dataMapOld.get("PercentageValue")!=null?dataMapOld.get("PercentageValue"):0);
						else
							ctx.put("otherAopCommercialPercentageOld",0);
						ResidentialSupplement residentialSupplement=(ResidentialSupplement)initlizePOJO(ctx, new ResidentialSupplement());
						ctx.put("RuleObject",residentialSupplement);
						insertRulesToDatabase(callRuleEngine(ctx,ruleid),residentialSupplement,ruleid,residentialSupplement.getDescription(),residentialSupplement.getTooltip());
					}
			}
			
			}
			if((ctx.get("SpeculativeAopCommercialPercentage")!=null && Integer.valueOf(ctx.get("SpeculativeAopCommercialPercentage").toString())>0)
				||(ctx.get("investmentTrustsPercentage")!=null && Integer.valueOf(ctx.get("investmentTrustsPercentage").toString())>0)
				||(ctx.get("estateSyndicationsAopCommercialPercentage")!=null && Integer.valueOf(ctx.get("estateSyndicationsAopCommercialPercentage").toString())>0))
			{
				ruleid="checkCommercialCombineAop_Lawyers";	
				ctx.put("speculativeAopCommercialPercentage", ctx.get("SpeculativeAopCommercialPercentage"));
				ResidentialSupplement residentialSupplement=(ResidentialSupplement)initlizePOJO(ctx, new ResidentialSupplement());
				ctx.put("RuleObject",residentialSupplement);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),residentialSupplement,ruleid,residentialSupplement.getDescription(),residentialSupplement.getTooltip());
				
			}
			else
			{
				ruleid="checkCommercialCombineAop_Lawyers";	
				archiveFlag(ctx,ruleid);
			}
			if((ctx.get("foreclosuresCommercialPercentage")!=null && Integer.valueOf(ctx.get("foreclosuresCommercialPercentage").toString())>0)
				|| (ctx.get("loanWorkoutsCommercialPercentage")!=null && Integer.valueOf(ctx.get("loanWorkoutsCommercialPercentage").toString())>0)	
					)
			{
				ruleid="checkForeclosuresAndLoanWorkoutsAOPCommercial_Lawyers";	
				ResidentialSupplement residentialSupplement=(ResidentialSupplement)initlizePOJO(ctx, new ResidentialSupplement());
				ctx.put("RuleObject",residentialSupplement);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),residentialSupplement,ruleid,residentialSupplement.getDescription(),residentialSupplement.getTooltip());
			}
			else
			{
				ruleid="checkForeclosuresAndLoanWorkoutsAOPCommercial_Lawyers";	
				archiveFlag(ctx,ruleid);
			}
		}
		else
		{
		ruleid="checkForeclosuresAndLoanWorkoutsAOPCommercial_Lawyers";	
		archiveFlag(ctx,ruleid);
		ruleid="checkCommercialCombineAop_Lawyers";	
		archiveFlag(ctx,ruleid);
		ruleid="checkOtherCommercialAop_Lawyers";	
		archiveFlag(ctx,ruleid);
		}
		
	}
	public static void evaluateRenewFirm(Context ctx) throws Exception
	{
		evaluateAttornies(ctx);
		
		evaluateAopWorkFlow(ctx);
		evaluatePracticeManagementWorkFlow(ctx);
		evaluateCoverageWorkFlow(ctx);
		
	}
	public static void evaluateAttornies(Context ctx) throws Exception
	{
			
		String ruleid="";
		List allAttornieslist = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetAllAttorneys",ctx);
		if (allAttornieslist == null)
			allAttornieslist = new ArrayList();
		List<Context> ruleSetContextList = new ArrayList<Context>();
		if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("1") )
		{
			if(allAttornieslist!=null && allAttornieslist instanceof List && allAttornieslist.size()>1)
			{		ruleid="multipleAttorneyAtleastHaveThousandHours_Lawyers";	
					int count=0;
					for (int i = 0; i < allAttornieslist.size(); i++) 
					{
						Map dataMap = (Map) allAttornieslist.get(i);
						if(dataMap.get("AnnualWorkedHours")!=null && Integer.valueOf(dataMap.get("AnnualWorkedHours").toString())>=1000 )
							count++;
					}	
					ctx.put("attorneysCountHave1000Hours", count);
					Firm firm=(Firm)initlizePOJO(ctx, new Firm());
					ctx.put("RuleObject",firm);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm,ruleid,firm.getDescription(),firm.getTooltip());
			}
			else
			{	ruleid="multipleAttorneyAtleastHaveThousandHours_Lawyers";	
				archiveFlag(ctx,ruleid);
				
			}
			if(allAttornieslist!=null && allAttornieslist.size()==1 )
			{
				
				
				List attornieslistsolo= (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetSoloAttorneys",ctx);
				
				Context attorniesctx=new Context();
				attorniesctx.putAll((Map) attornieslistsolo.get(0));
				
				
					ruleid="soloAtorneyHoursLessThanFiveHundred_Lawyers";	
					
					ctx.put("annualWorkedHours",attorniesctx.get("AnnualWorkedHours"));
					Firm firm=(Firm)initlizePOJO(ctx, new Firm());
					ctx.put("RuleObject",firm);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm,ruleid,firm.getDescription(),firm.getTooltip());
					
					ruleid="soloAttorneyAndNoBackup_Lawyers";	
					ctx.put("isFirmHaveBackupAttorney", ctx.get("IsFirmHaveBackupAttorney"));
					Firm firm1=(Firm)initlizePOJO(ctx, new Firm());
					ctx.put("RuleObject",firm1);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm1,ruleid,firm1.getDescription(),firm1.getTooltip());
					
			}
			else
			{	ruleid="soloAtorneyHoursLessThanFiveHundred_Lawyers";	
				archiveFlag(ctx,ruleid);
				ruleid="soloAttorneyAndNoBackup_Lawyers";	
				archiveFlag(ctx,ruleid);
			}
			//Requested by client. JIRA :PA-764
			/*if(allAttornieslist!=null && allAttornieslist instanceof List && allAttornieslist.size()>1)
			{		ruleid="anyNonRatedAttorney_Lawyers";	
					int nonRateAttorneyCount=0;
					for (int i = 0; i < allAttornieslist.size(); i++) 
					{
						Map dataMap = (Map) allAttornieslist.get(i);
						if(dataMap.get("IsNonRatedAttorney")!=null && dataMap.get("IsNonRatedAttorney").toString().equals("Y")) 
							nonRateAttorneyCount++;
							
					}
					if(nonRateAttorneyCount>0)
					ctx.put("isNonRatedAttorney", "Y");
					Firm firm=(Firm)initlizePOJO(ctx, new Firm());
					ctx.put("RuleObject",firm);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm,ruleid,firm.getDescription(),firm.getTooltip());
					
			}
			else
			{	ruleid="anyNonRatedAttorney_Lawyers";	
				archiveFlag(ctx,ruleid);
				
			}*/
			if(allAttornieslist!=null && allAttornieslist instanceof List && allAttornieslist.size()>1)
			{		
				int count=0;
				ruleid="moreThanFiveRateableAttorneys_Lawyers";	
				for (int i = 0; i < allAttornieslist.size(); i++) 
				{
					Map dataMap = (Map) allAttornieslist.get(i);
					if(dataMap.get("IsNonRatedAttorney")==null || !dataMap.get("IsNonRatedAttorney").toString().equals("Y") )
						count++;
				}	
				ctx.put("totalRatedAttornyes", count);
				Firm firm=(Firm)initlizePOJO(ctx, new Firm());
				ctx.put("RuleObject",firm);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm,ruleid,firm.getDescription(),firm.getTooltip());
			}
			else
			{	ruleid="moreThanFiveRateableAttorneys_Lawyers";	
				archiveFlag(ctx,ruleid);
				
			}
			
			
			if(allAttornieslist!=null && allAttornieslist instanceof List )
			{		
				int count=allAttornieslist.size();
				ruleid="attorneySupportStaffRatio_Lawyers";	
				ctx.put("totalAttornyes",count);
				ctx.put("totalNumOfNonAttorneyStaff",ctx.get("TotalNumOfNonAttorneyStaff"));
				Firm firm=(Firm)initlizePOJO(ctx, new Firm());
				ctx.put("RuleObject",firm);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm,ruleid,firm.getDescription(),firm.getTooltip());
			}
			
			if(allAttornieslist!=null && allAttornieslist instanceof List && allAttornieslist.size()>0)
			{		
					ruleid="attorneysNotLicensedIndomicile_Lawyers";
					for (int i = 0; i < allAttornieslist.size(); i++) 
					{
						Map dataMap = (Map) allAttornieslist.get(i);
						Context newCtx=new Context();
						newCtx.setProject(ctx.getProject());
						if(dataMap.get("LicensedStates")!=null && dataMap.get("LicensedStates").toString().length()>0 )
						{
							if(dataMap.get("LicensedStates").toString().contains(ctx.get("StateDesc").toString()))
								ctx.put("isLiscenseIndomicile","Y");
							else
								ctx.put("isLiscenseIndomicile","N");	
						}
						Firm firm=(Firm)initlizePOJO(ctx, new Firm());
						newCtx.put("RuleObject",firm);
						newCtx=callRuleEngine(newCtx,ruleid);
						if(firm.getDescription()!=null && firm.getTooltip()!=null && !firm.getDescription().equals("") && !firm.getTooltip().equals(""))
						{
							newCtx.put("question",firm.getDescription());
							newCtx.put("hint",firm.getTooltip());
						}
						ruleSetContextList.add(newCtx);
					}
					insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
					clearRuleSetContextList(ruleid,ruleSetContextList);
			}
			else
			{	ruleid="multipleAttorneyAtleastHaveThousandHours_Lawyers";	
				archiveFlag(ctx,ruleid);
				
			}
			
			
			
			ruleid="checkEstablishDateWithAttorneysHiringDate_Lawyers";
			
			for (int i = 0; i < allAttornieslist.size(); i++) 
			{
				Map dataMap = (Map) allAttornieslist.get(i);
				Context newCtx=new Context();
				newCtx.setProject(ctx.getProject());
				ctx.put("hiringYearOfAttorney",new SimpleDateFormat("YYYY").format(new Date(dataMap.get("AttorneyPriorActDate").toString()))); 
				ctx.put("yearOfFirmEstablished",ctx.get("YearOfFirmEstablished"));
				Firm firm2=(Firm)initlizePOJO(ctx, new Firm());
				newCtx.put("RuleObject",firm2);
				logger.debug(firm2.getHiringYearOfAttorney()+" "+firm2.getYearOfFirmEstablished());
				newCtx=callRuleEngine(newCtx,ruleid);
				if(firm2.getDescription()!=null && firm2.getTooltip()!=null && !firm2.getDescription().equals("") && !firm2.getTooltip().equals(""))
				{
					newCtx.put("question",firm2.getDescription());
					newCtx.put("hint",firm2.getTooltip());
				}
				ruleSetContextList.add(newCtx);
			}
			insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
			clearRuleSetContextList(ruleid,ruleSetContextList);
			
			List firmGrossRevenue= (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetFirmRecentGrosRevenue",ctx);
			if(firmGrossRevenue != null)
			{
				ruleid="averageRevenuePerAttorney_Lawyers";	
				long grossRevenue=0;
				for (int i = 0; i < firmGrossRevenue.size(); i++) 
				{
					Map dataMap = (Map) firmGrossRevenue.get(i);
					if(dataMap.get("Amount")!=null)
					grossRevenue=Long.valueOf(dataMap.get("Amount").toString());
				}
				int totalAttornyes=allAttornieslist.size();
				long averageRevenuePerAttorney=grossRevenue/totalAttornyes;
				ctx.put("averageRevenuePerAttorney", averageRevenuePerAttorney);
				Firm firm1=(Firm)initlizePOJO(ctx, new Firm());
				ctx.put("RuleObject",firm1);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm1,ruleid,firm1.getDescription(),firm1.getTooltip());
				
			}
			
			
			if(ctx.get("IsFirmPracticeInOtherState")!=null && ctx.get("IsFirmPracticeInOtherState").toString().equals("Y"))
			{
			List firmPracticeLocation=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.firmviewgetPrimaryLocationList1",ctx);
				if(firmPracticeLocation!=null && firmPracticeLocation instanceof List) 
				{
					ruleid="isOfficeInOtherThanStateOfDomicile_Lawyers";
					for (int i = 0; i < firmPracticeLocation.size(); i++) 
					{
						Map dataMap = (Map) firmPracticeLocation.get(i);
						Context newCtx=new Context();
						newCtx.setProject(ctx.getProject());
						ctx.put("stateDesc",ctx.get("StateDesc"));
						ctx.put("priorStateDesc",dataMap.get("FPLStateCode"));
						Firm firm=(Firm)initlizePOJO(ctx, new Firm());
						newCtx.put("RuleObject",firm);
						newCtx=callRuleEngine(newCtx,ruleid);
						if(firm.getDescription()!=null && firm.getTooltip()!=null && !firm.getDescription().equals("") && !firm.getTooltip().equals(""))
						{
							newCtx.put("question",firm.getDescription());
							newCtx.put("hint",firm.getTooltip());
						}
						ruleSetContextList.add(newCtx);
					}
					insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
					clearRuleSetContextList(ruleid,ruleSetContextList);
				}
				
			}
			else
			{
				ruleid="isOfficeInOtherThanStateOfDomicile_Lawyers";	
				archiveFlag(ctx,ruleid);
			}
			
			if(ctx.get("IsApplicantProvidesLegalServices")!=null && ctx.get("IsApplicantProvidesLegalServices").toString().equals("Y"))
			{		ruleid="isApplicantProvidesLegalServices_Lawyers";	
					ctx.put("isApplicantProvidesLegalServices", ctx.get("IsApplicantProvidesLegalServices"));
					Firm firm1=(Firm)initlizePOJO(ctx, new Firm());
					ctx.put("RuleObject",firm1);
					insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm1,ruleid,firm1.getDescription(),firm1.getTooltip());
					
			}
			else
			{	ruleid="isApplicantProvidesLegalServices_Lawyers";	
				archiveFlag(ctx,ruleid);
				
			}
			
		}	
		
		if(ctx.get("PolicyStatusKey")!=null && ctx.get("PolicyStatusKey").toString().equals("2") )
		{
			
			List previousPolicyKey= (List) SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey",ctx);
			ctx.putAll((Map) previousPolicyKey.get(0));
			Context oldPolicyContext=new Context();
			oldPolicyContext.setProject(ctx.getProject());
			oldPolicyContext.put("PolicyKey",ctx.get("PreviousPolicykey"));
			oldPolicyContext.put("VersionSequence",ctx.get("previousVersionSequence"));
			List oldPolicyAllAttornieslist = SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementdroolQueriesgetAllAttorneys",oldPolicyContext);
			int odlRatedAttorneyCount=0,currentRatedAttorneyCount=0;
			List getAddressdetailsForAll=SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.UserStatementManualBoQueriesgetAddressdetailsForAll",oldPolicyContext);
			oldPolicyContext.putAll((Map) getAddressdetailsForAll.get(0));
			
			ruleid="stateChange_Lawyers";	
			ctx.put("priorStateDesc",oldPolicyContext.get("StateDesc"));
			ctx.put("stateDesc",ctx.get("StateDesc"));
			Firm firm=(Firm)initlizePOJO(ctx, new Firm());
			ctx.put("RuleObject",firm);
			insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm,ruleid,firm.getDescription(),firm.getTooltip());
			 
			ctx.put("currentDate", new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
			ctx.put("effectiveDateOfPolicy", new SimpleDateFormat("MM/dd/yyyy").format(new Date(ctx.get("PolicyEffectiveDate").toString())));
			
			long daysDifferenceForEfectiveDate=0;
			daysDifferenceForEfectiveDate=(DateUtils.calculateDiffInDays(new Date(ctx.get("effectiveDateOfPolicy").toString()),new Date(ctx.get("currentDate").toString()))+1);

			ruleid="validateEffectiveDateWithDaysReNewBusiness_Lawyers";
			ctx.put("daysDifferenceForEfectiveDate",daysDifferenceForEfectiveDate);
			Firm firm1=(Firm)initlizePOJO(ctx, new Firm());
			ctx.put("RuleObject",firm1);
			insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm1,ruleid,firm1.getDescription(),firm1.getTooltip());
			
			if(allAttornieslist.size()==1)
			{
				for (int i = 0; i <allAttornieslist.size(); i++) 
				{
					Map dataMapOld = (Map) oldPolicyAllAttornieslist.get(i);
					Map dataMap = (Map) allAttornieslist.get(i);
					if(dataMap.get("AttorneyName").toString().equals(dataMapOld.get("AttorneyName").toString())
						& dataMap.get("LicensedStates").toString().equals(dataMapOld.get("LicensedStates").toString())
						& dataMap.get("AttorneyPriorActDate").toString().equals(dataMapOld.get("AttorneyPriorActDate").toString()))
					{
						if(!dataMap.get("AnnualWorkedHours").toString().equals(dataMapOld.get("AnnualWorkedHours").toString()))
						{
						ruleid="soloAttorneyAndHoursChange_Lawyers";	
						ctx.put("annualWorkedHours",dataMap.get("AnnualWorkedHours"));
						Firm firm2=(Firm)initlizePOJO(ctx, new Firm());
						ctx.put("RuleObject",firm2);
						insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm2,ruleid,firm2.getDescription(),firm2.getTooltip());
						}
					}
					
				}
			}
			else
			{
				ruleid="soloAttorneyAndHoursChange_Lawyers";	
				archiveFlag(ctx,ruleid);
			}
		
			int isOld=0,isNewAttorney=0,attorneyDesgModified=0,licensedStatesModified=0,annualWorkedHoursModified=0,isNonRatedAttorneyModified=0,attorneyCount=0;
			int nonratedAttorneyCount=0;
			for (int i = 0; i <allAttornieslist.size(); i++) 
			{
				isOld=0;
				isNonRatedAttorneyModified=0;
				
				Map dataMap = (Map) allAttornieslist.get(i);
				String attorneyName=dataMap.get("AttorneyName")!=null?dataMap.get("AttorneyName").toString():"";
				String attorneyPriorActDate=dataMap.get("AttorneyPriorActDate")!=null?dataMap.get("AttorneyPriorActDate").toString():"";	
				String isNonRatedAttorney=dataMap.get("IsNonRatedAttorney")!=null?dataMap.get("IsNonRatedAttorney").toString():"N";
				for (int j = 0; j <oldPolicyAllAttornieslist.size(); j++) 
				{
					Map dataMapOld = (Map) oldPolicyAllAttornieslist.get(j);
					String attorneyNameOld=dataMapOld.get("AttorneyName")!=null?dataMapOld.get("AttorneyName").toString():"";
					String attorneyPriorActDateOld=dataMapOld.get("AttorneyPriorActDate")!=null?dataMapOld.get("AttorneyPriorActDate").toString():"";	
					String isNonRatedAttorneyOld=dataMapOld.get("IsNonRatedAttorney")!=null?dataMapOld.get("IsNonRatedAttorney").toString():"N";	
					if(attorneyName.equals(attorneyNameOld) && attorneyPriorActDate.equals(attorneyPriorActDateOld))
					{
								isOld++;
								if(!dataMap.get("DesignationId").toString().equals(dataMapOld.get("DesignationId").toString()))
									attorneyDesgModified++;
								if(!dataMap.get("LicensedStates").toString().equals(dataMapOld.get("LicensedStates").toString()))
								{
									licensedStatesModified++;
									if(dataMap.get("LicensedStates")!=null && dataMap.get("LicensedStates").toString().length()>0 )
									{
										if(dataMap.get("LicensedStates").toString().contains(ctx.get("StateDesc").toString()))
											ctx.put("isLiscenseIndomicile","Y");
										else
											ctx.put("isLiscenseIndomicile","N");	
										
										ruleid="addedAttorneyIsLiscenseIndomicile_Lawyers";	
										Firm firm2=(Firm)initlizePOJO(ctx, new Firm());
										ctx.put("RuleObject",firm2);
										insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm2,ruleid,firm2.getDescription(),firm2.getTooltip());
									}
								}
								if(!dataMap.get("AnnualWorkedHours").toString().equals(dataMapOld.get("AnnualWorkedHours").toString()))
								{
									annualWorkedHoursModified++;
										//attorneyCount++;
								}
								if(!isNonRatedAttorney.equals(isNonRatedAttorneyOld))
								{
									isNonRatedAttorneyModified++;
									
								}
								if(dataMap.get("AnnualWorkedHours")!=null && Integer.valueOf(dataMap.get("AnnualWorkedHours").toString())>1000 )
									
								{
									
										attorneyCount++;
								}
					}	
				}
				
				if(isOld==0)
				{
					isNewAttorney++;
					
						if(dataMap.get("LicensedStates").toString().contains(ctx.get("StateDesc").toString()))
							ctx.put("isLiscenseIndomicile","Y");
						else
							ctx.put("isLiscenseIndomicile","N");	
						
						ruleid="addedAttorneyIsLiscenseIndomicile_Lawyers";	
						Firm firm2=(Firm)initlizePOJO(ctx, new Firm());
						ctx.put("RuleObject",firm2);
						insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm2,ruleid,firm2.getDescription(),firm2.getTooltip());
				}
				if(isNewAttorney>0 || isNonRatedAttorneyModified>0 )
				{
					if(dataMap.get("AnnualWorkedHours")!=null && Integer.valueOf(dataMap.get("AnnualWorkedHours").toString())>=1000 && "N".equals(dataMap.get("IsNonRatedAttorney")!=null?dataMap.get("IsNonRatedAttorney").toString():"N")  )
					{
						attorneyCount++;
					}
					
				}
				if(isNonRatedAttorneyModified>0 || oldPolicyAllAttornieslist.size()!=allAttornieslist.size() ||isNewAttorney>0){
					if("Y".equals(dataMap.get("IsNonRatedAttorney")!=null?dataMap.get("IsNonRatedAttorney").toString():"N"))
					nonratedAttorneyCount++;
				}
				
			}
			if(isNewAttorney>0 || isNonRatedAttorneyModified>0 || oldPolicyAllAttornieslist.size()!=allAttornieslist.size())
			{
				for (int i = 0; i <allAttornieslist.size(); i++) 
				{
					
					Map dataMap = (Map) allAttornieslist.get(i);
					
						if(dataMap.get("IsNonRatedAttorney")==null || !dataMap.get("IsNonRatedAttorney").toString().equals("Y"))
						currentRatedAttorneyCount++;
					
					
				}
				ruleid="ratedAttorneysRenew_Lawyers";	
				ctx.put("currentRatedAttorneyCount", currentRatedAttorneyCount);
				Firm firm2=(Firm)initlizePOJO(ctx, new Firm());
				ctx.put("RuleObject",firm2);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm2,ruleid,firm2.getDescription(),firm2.getTooltip());
			}
			else
			{
				ruleid="ratedAttorneysRenew_Lawyers";	
				archiveFlag(ctx,ruleid);
			}
			//Requested by client. JIRA :PA-764
			/*if(nonratedAttorneyCount>0)
			{
				ctx.put("nonratedAttorneyCount",nonratedAttorneyCount);
				ruleid="nonRatedAttorneyAdded_Lawyers";	
				
				Firm firm2=(Firm)initlizePOJO(ctx, new Firm());
				ctx.put("RuleObject",firm2);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm2,ruleid,firm2.getDescription(),firm2.getTooltip());
			}
			else
			{
				ruleid="nonRatedAttorneyAdded_Lawyers";	
				archiveFlag(ctx,ruleid);
			}*/
			if(allAttornieslist.size()>1 && ctx.get("IsAttorneyAddedDeleted")!=null && ctx.get("IsAttorneyAddedDeleted").toString().equals("Y"))
			{
				if(isNewAttorney>0 || annualWorkedHoursModified>0)
					{	
						ruleid="attorneyHoursChange_Lawyers";	
						ctx.put("attorneyCount",attorneyCount);
						Firm firm2=(Firm)initlizePOJO(ctx, new Firm());
						ctx.put("RuleObject",firm2);
						insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm2,ruleid,firm2.getDescription(),firm2.getTooltip());
					}
				else
				{
					ruleid="attorneyHoursChange_Lawyers";	
					archiveFlag(ctx,ruleid);
				}
				
			}
			else
			{
				ruleid="attorneyHoursChange_Lawyers";	
				archiveFlag(ctx,ruleid);
			}
			
			
				ruleid="averageRevenuePerAttorney_Lawyers";	
				double grossRevenue=	grossRevenue=Long.valueOf(ctx.get("GrossFeesPaidRenew").toString());
				double totalAttornyes=allAttornieslist.size();
				double averageRevenuePerAttorney=grossRevenue/totalAttornyes;
				ctx.put("averageRevenuePerAttorney", averageRevenuePerAttorney);
				Firm firm2=(Firm)initlizePOJO(ctx, new Firm());
				ctx.put("RuleObject",firm2);
				insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm2,ruleid,firm2.getDescription(),firm2.getTooltip());
				
			
				if(ctx.get("IsFirmPracticeInOtherState")!=null && ctx.get("IsFirmPracticeInOtherState").toString().equals("Y"))
					{
					
					List firmPracticeLocationOld = SqlResources.getSqlMapProcessor(oldPolicyContext).select("SqlStmts.firmviewgetPrimaryLocationList1",oldPolicyContext);
					List firmPracticeLocation=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.firmviewgetPrimaryLocationList1",ctx);
					boolean isMOdified=false;
						if(firmPracticeLocation!=null && firmPracticeLocationOld !=null ) 
						{
							ruleid="isOfficeInOtherThanStateOfDomicile_Lawyers";
							for (int i = 0; i <firmPracticeLocation.size(); i++) 
							{
								isOld=0;
								isMOdified=false;
								Map dataMap = (Map) firmPracticeLocation.get(i);
								String fplAddress=dataMap.get("FPLAddress")!=null?dataMap.get("FPLAddress").toString():"";
								String fplCity=dataMap.get("FPLCity")!=null?dataMap.get("FPLCity").toString():"";
								String numberOfAttorneys=dataMap.get("NumberOfAttorneys")!=null?dataMap.get("NumberOfAttorneys").toString():"";
								
								for (int j = 0; j <firmPracticeLocationOld.size(); j++) 
								{
									Map dataMapOld = (Map) firmPracticeLocationOld.get(j);
									String fplAddressOld=dataMapOld.get("FPLAddress")!=null?dataMapOld.get("FPLAddress").toString():"";
									String fplCityOld=dataMapOld.get("FPLCity")!=null?dataMapOld.get("FPLCity").toString():"";
									String numberOfAttorneysOld=dataMapOld.get("NumberOfAttorneys")!=null?dataMapOld.get("NumberOfAttorneys").toString():"";
									if(fplAddress.equals(fplAddressOld) && fplCity.equals(fplCityOld) && numberOfAttorneys.equals(numberOfAttorneysOld))
									{
										isOld++;
										if(!dataMap.get("FPLStateCode").toString().equals(dataMapOld.get("FPLStateCode").toString()))
											{
											isMOdified=true;
											Context newCtx=new Context();
											newCtx.setProject(ctx.getProject());
											ctx.put("stateDesc",ctx.get("StateDesc"));
											ctx.put("fPLStateCode",dataMap.get("FPLStateCode"));
											Firm firm3=(Firm)initlizePOJO(ctx, new Firm());
											newCtx.put("RuleObject",firm3);
											newCtx=callRuleEngine(newCtx,ruleid);
											if(firm3.getDescription()!=null && firm3.getTooltip()!=null && !firm3.getDescription().equals("") && !firm3.getTooltip().equals(""))
											{
												newCtx.put("question",firm3.getDescription());
												newCtx.put("hint",firm3.getTooltip());
											}
											ruleSetContextList.add(newCtx);
											}
									}
								}
								
								if(isOld==0)
								{	isMOdified=true;
									Context newCtx=new Context();
									newCtx.setProject(ctx.getProject());
									ctx.put("stateDesc",ctx.get("StateDesc"));
									ctx.put("fPLStateCode",dataMap.get("FPLStateCode"));
									Firm firm3=(Firm)initlizePOJO(ctx, new Firm());
									newCtx.put("RuleObject",firm3);
									newCtx=callRuleEngine(newCtx,ruleid);
									if(firm3.getDescription()!=null && firm3.getTooltip()!=null && !firm3.getDescription().equals("") && !firm3.getTooltip().equals(""))
									{
										newCtx.put("question",firm3.getDescription());
										newCtx.put("hint",firm3.getTooltip());
									}
									ruleSetContextList.add(newCtx);
								}
								
							}
							
							if(isMOdified==false)
							{
								ruleid="isOfficeInOtherThanStateOfDomicile_Lawyers";	
								archiveFlag(ctx,ruleid);
							}
							else
							{
								insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
								clearRuleSetContextList(ruleid,ruleSetContextList);	
							}
							
							}
						
					}
				else
				{
					ruleid="isOfficeInOtherThanStateOfDomicile_Lawyers";	
					archiveFlag(ctx,ruleid);
				}
		
		}
		
		
		//Common Referrals for Firm Page
		
		ruleid="validateFirmAddress_Lawyers";	
		boolean containsPOBOX=false;
		String address1 = ctx.get("Address1") != null ? ctx.get("Address1").toString().trim().toLowerCase() : "";
		//String address2 = ctx.get("Address2") != null ? ctx.get("Address2").toString().trim().toLowerCase()  : "";
		String[] poPatterns=getTokens(ctx.get("poBoxNamePatterns").toString().trim().toLowerCase(),",");
		for(int i=0;i<poPatterns.length;i++)
		{
			if(address1.contains(poPatterns[i]) /*||address2.contains(poPatterns[i])*/)
			{
				containsPOBOX=true;
			}
			
		}
		ctx.put("containsPOBOXInAddress", containsPOBOX);
		Firm firm=(Firm)initlizePOJO(ctx, new Firm());
		ctx.put("RuleObject",firm);
		insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm,ruleid,firm.getDescription(),firm.getTooltip());
		if(ctx.get("IsFirmPracticeInOtherState")!=null && ctx.get("IsFirmPracticeInOtherState").toString().equals("Y"))
		{
			ruleid="isFirmOperateUnderOtherLegalName_Lawyers";	
			ctx.put("isApplFirmWithDifferentLegalName", ctx.get("IsApplFirmWithDifferentLegalName"));
			Firm firm2=(Firm)initlizePOJO(ctx, new Firm());
			ctx.put("RuleObject",firm2);
			insertRulesToDatabase(callRuleEngine(ctx,ruleid),firm2,ruleid,firm2.getDescription(),firm2.getTooltip());
			/*ruleSetContextList=initilizeRuleEngineContext(ctx,ruleid,"isApplFirmWithDifferentLegalName");
			
			insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
			clearRuleSetContextList(ruleid,ruleSetContextList);*/
		}
		else
		{
			ruleid="isFirmOperateUnderOtherLegalName_Lawyers";	
			archiveFlag(ctx,ruleid);
		}
		
	}
	
	public static void evaluateQuoteOptions(Context ctx)throws Exception
	{	
		String ruleid="";
		List<Context> ruleSetContextList = new ArrayList<Context>();
		
		int statusKey=ctx.get("StatusKey")!=null?Integer.parseInt(ctx.get("StatusKey").toString()):0;
		List defaultQuoteDetails = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetPolicyCoverageDetailsWithFirstQuote",ctx);
		Map dataMap=null;
		int defaultLimitSequence=0,limitSequence=0,reductionLimitCount=0;
		if(defaultQuoteDetails!=null && defaultQuoteDetails instanceof List )
		{	
			dataMap=(Map) defaultQuoteDetails.get(0);
			defaultLimitSequence= dataMap.get("LimitSequence")!=null? Integer.parseInt(dataMap.get("LimitSequence").toString()):0;
		
		}
		List policyCoverageDetailsList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdroolQueriesgetPolicyCoverageDetailsList",ctx);
		for(int i=0;i<policyCoverageDetailsList.size();i++)
		{
			dataMap=(Map) policyCoverageDetailsList.get(i);
				
			limitSequence=dataMap.get("LimitSequence")!=null?Integer.parseInt(dataMap.get("LimitSequence").toString()):0;
			 if( limitSequence<defaultLimitSequence)
				 reductionLimitCount++;
		}
		
		/*
		 * if(reductionLimitCount>0) { ruleid="ValidateReductionOfLimit_Lawyers";
		 * logger.debug("going to debug : "+ ruleid);
		 * RuleEngineUtilityNewApp.insertRulesToDatabaseTemp(ctx,
		 * ruleid,"Requesting limit below expiring limit"
		 * ,"Requires reduction in limit advisory signature"); }
		 * 
		 * else { ruleid="ValidateReductionOfLimit_Lawyers"; archiveFlag(ctx,ruleid); }
		 */
		/*
		 * int modPercentage=dataMap.get("TotalCovModifierPercentage")!=null?Integer.
		 * parseInt(dataMap.get("TotalCovModifierPercentage").toString()):100;
		 * if(modPercentage!=100) { String isArchived=""; String ruleStatus=""; Object
		 * objMap = SqlResources.getSqlMapProcessor(ctx).findByKey(
		 * "SqlStmts.UserStatementdroolQueriesgetRecentDetailsForRulePolicyTransaction",
		 * ctx); Map map=null; if (objMap != null && objMap instanceof Map) {
		 * map=(Map)objMap;
		 * isArchived=map.get("isArchived")!=null?map.get("isArchived").toString():"X";
		 * ruleStatus=map.get("RuleStatus")!=null?map.get("RuleStatus").toString():"X";
		 * } else { isArchived="YES"; ruleStatus="OLD"; }
		 * 
		 * if(modPercentage<-5) { if(isArchived.equals("YES") &&
		 * ruleStatus.equals("OLD")) { ruleid="ValidateModifiersRange_Lawyers";
		 * logger.debug("going to debug : "+ ruleid);
		 * RuleEngineUtilityNewApp.insertRulesToDatabaseTemp(ctx,
		 * ruleid,"Modifier less than -5."," "); } } else {
		 * ruleid="ValidateModifiersRange_Lawyers"; archiveFlag(ctx,ruleid); } }
		 */
			
		if(statusKey==2)
		{
			/*
			 * List getTotalPremiumList=SqlResources.getSqlMapProcessor(ctx).select(
			 * "SqlStmts.UserStatementManualBoQueriesgetTotalPremiumList",ctx); List
			 * previousPolicyKey= (List) SqlResources.getSqlMapProcessor(ctx).select(
			 * "SqlStmts.UserStatementdroolQueriesgetPreviousPolicyKey",ctx);
			 * ctx.putAll((Map) previousPolicyKey.get(0)); Context oldPolicyContext=new
			 * Context(); oldPolicyContext.setProject(ctx.getProject());
			 * oldPolicyContext.put("PolicyKey",ctx.get("PreviousPolicykey"));
			 * oldPolicyContext.put("VersionSequence",ctx.get("previousVersionSequence"));
			 * List priorPolicyCoverageDetailsList =
			 * SqlResources.getSqlMapProcessor(oldPolicyContext).select(
			 * "SqlStmts.UserStatementdroolQueriesgetPriorPolicyCoverageDetailsList",
			 * oldPolicyContext); if(priorPolicyCoverageDetailsList!=null &&
			 * priorPolicyCoverageDetailsList instanceof List )
			 * oldPolicyContext.putAll((Map) priorPolicyCoverageDetailsList.get(0)); int
			 * previouslimitSequence=oldPolicyContext.get("LimitSequence")!=null?Integer.
			 * parseInt(oldPolicyContext.get("LimitSequence").toString()):0; // List
			 * priorPolicyCoverageDetailsList =
			 * SqlResources.getSqlMapProcessor(oldPolicyContext).select(
			 * "SqlStmts.UserStatementdroolQueriesgetPriorPolicyCoverageDetailsList",
			 * oldPolicyContext);
			 * 
			 * limitSequence=0; int count2M=0; if(policyCoverageDetailsList!=null &&
			 * policyCoverageDetailsList instanceof List ) { dataMap = null; for(int
			 * i=0;i<policyCoverageDetailsList.size();i++) { dataMap=(Map)
			 * policyCoverageDetailsList.get(i);
			 * 
			 * limitSequence=dataMap.get("LimitSequence")!=null?Integer.parseInt(dataMap.get
			 * ("LimitSequence").toString()):0; if( previouslimitSequence<36 &&
			 * limitSequence>=36) count2M++; }
			 * 
			 * 
			 * if(count2M>0) { ruleid="ValidateLimitFor2M_Lawyers";
			 * logger.debug("going to debug : "+ ruleid);
			 * RuleEngineUtilityNewApp.insertRulesToDatabaseTemp(ctx,
			 * ruleid,"Requesting 2M in limit.","Requesting 2M in limit."); }
			 * 
			 * else { ruleid="ValidateLimitFor2M_Lawyers"; archiveFlag(ctx,ruleid); }
			 * 
			 * }
			 * 
			 * dataMap=null; if(getTotalPremiumList!=null && getTotalPremiumList.size()>0) {
			 * ruleid="renewalPremiumComparision_Lawyers"; logger.debug("going to debug : "+
			 * ruleid); for (int i = 0; i < getTotalPremiumList.size(); i++) { dataMap =
			 * (Map) getTotalPremiumList.get(i); Context newCtx=new Context();
			 * newCtx.setProject(ctx.getProject());
			 * ctx.put("totalPremium",dataMap.get("TotalPremium")); QuoteOptions
			 * quoteOptions=(QuoteOptions)initlizePOJO(ctx, new QuoteOptions());
			 * newCtx.put("RuleObject",quoteOptions); newCtx=callRuleEngine(newCtx,ruleid);
			 * if(quoteOptions.getDescription()!=null && quoteOptions.getTooltip()!=null &&
			 * !quoteOptions.getDescription().equals("") &&
			 * !quoteOptions.getTooltip().equals("")) {
			 * newCtx.put("question",quoteOptions.getDescription());
			 * newCtx.put("hint",quoteOptions.getTooltip()); }
			 * ruleSetContextList.add(newCtx); ruleSetContextList.add(newCtx); }
			 * insertRulesToDatabase(ctx,ruleSetContextList,ruleid);
			 * clearRuleSetContextList(ruleid,ruleSetContextList); } else {
			 * ruleid="renewalPremiumComparision_Lawyers"; archiveFlag(ctx,ruleid);
			 * 
			 * }
			 */}
		
	}
	public static List<Context>  initilizeRuleEngineContext(Context ctx,List dataList,String ruleId,String dataForRuleEngine)
	{	
		try 
		{
			String[] tokens=getTokens(dataForRuleEngine,",");
			List<Context> ruleSetContextList = new ArrayList<Context>();
			if(dataList!=null && dataList instanceof List)
			{
				for (int i = 0; i < dataList.size(); i++) {
					Map dataMap = (Map) dataList.get(i);
					Context newCtx=new Context();
					newCtx.setProject(ctx.getProject());
					//newCtx.put(ruleId,dataMap.get(ruleId));
					/*for(int j=0;j<tokens.length;j++)
					{
						newCtx.put(tokens[j],dataMap.get(tokens[j]));
						
					}*/
					newCtx=callRuleEngine(ctx,ruleId);
					ruleSetContextList.add(newCtx);
					}
					
			}
			return ruleSetContextList;
		}
		catch (Exception e)
		{
			logger.error("Error Occured while initilizing attorney data  : " + e.getMessage());
			return null;
		}
	}
	public static List<Context>  initilizeRuleEngineContext(Context ctx,String ruleId,String dataForRuleEngine)
	{	
		try 
		{
			String[] tokens=getTokens(dataForRuleEngine,",");
			List<Context> ruleSetContextList = new ArrayList<Context>();
			Context newCtx=new Context();
			newCtx.setProject(ctx.getProject());
			/*for(int j=0;j<tokens.length;j++)
			{
				newCtx.put(tokens[j],ctx.get(tokens[j]));
			}*/
			newCtx=callRuleEngine(ctx,ruleId);
			ruleSetContextList.add(newCtx);
			return ruleSetContextList;
		
		}
		catch (Exception e) {
			logger.error("Error Occured while initilizing attorney data  : " + e.getMessage());
			return null;
		}	
	}
	public static List<Context>  initilizeRuleEngineContext(Context ctx,String ruleId)
	{	
		try 
		{	
			List<Context> ruleSetContextList = new ArrayList<Context>();
			Context newCtx=new Context();
			newCtx.setProject(ctx.getProject());
			newCtx=callRuleEngine(ctx,ruleId);
			ruleSetContextList.add(newCtx);
			return ruleSetContextList;
		
		}
		catch (Exception e) {
			logger.error("Error Occured while initilizing attorney data  : " + e.getMessage());
			return null;
		}	
	}
	public static Context callRuleEngine(Context ctx,String ruleId) throws Exception
	{
		RulesResources.getInstance(ctx).findRule("TestRule."+ruleId).execute(ctx, null);
		//RulesResources.getInstance(ctx).findRule("TestRule."+ruleId).executeByObject(ctx, null);
		return ctx;
	}
	public static void insertRulesToDatabase(Context ctx,List ruleSetContextList,String ruleId )
	{	String question="",hint="";
		if( ruleSetContextList !=null && ruleSetContextList.size()>0)
		{
			for( int i =0; i< ruleSetContextList.size(); i++)
			{
				Map listMap = (Map)ruleSetContextList.get(i);
				if(listMap != null && !listMap.isEmpty()){
					
					if(listMap.get("question") != null && listMap.get("hint") != null)
					{
						if(listMap.get("question")!=null && listMap.get("hint")!=null)
							question=listMap.get("question").toString();
							hint=listMap.get("hint").toString();
							//break;
					}
				}
			}
	
			logger.debug("Question= "+question+" Question Hint="+hint);
			if(!question.equals("") && !hint.equals("") )
			{	
				ctx.put("ruleId",ruleId);
				ctx.put("question",question);
				ctx.put("hint",hint);
				ctx.put("ActionTakenBy", ctx.get("user_id"));
				try {
					new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,ruleId,question,hint,ActionTakenBy");
					SqlResources.getSqlMapProcessor(ctx).update("rulepolicytransaction.SETFlagsLawyers_p",ctx);
				} catch (Exception e) {
					logger.error("Unexpected error", e);
				}
			}
			else
			{	ctx.put("ruleId",ruleId);
				try {
					new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,ruleId,question,hint,ActionTakenBy");
					SqlResources.getSqlMapProcessor(ctx).update("rulepolicytransaction.RESETFlagsLawyers_p",ctx);
				} catch (Exception e) {
					logger.error("Unexpected error", e);
				}
				
			}
		}
	}
	
	public static void insertRulesToDatabase(Context ctx,Object obj,String ruleId,String description,String toolTip )
	{	
			
			if(description!=null && toolTip!=null &&!description.equals("") && !toolTip.equals("") )
			{	
				ctx.put("ruleId",ruleId);
				ctx.put("question",description);
				ctx.put("hint",toolTip);
				ctx.put("ActionTakenBy", ctx.get("user_id"));
				try {
					new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,ruleId,question,hint,ActionTakenBy");
					SqlResources.getSqlMapProcessor(ctx).update("rulepolicytransaction.SETFlagsLawyers_p",ctx);
				} catch (Exception e) {
					logger.error("Unexpected error", e);
				}
			}
			else
			{	ctx.put("ruleId",ruleId);
				ctx.put("question","");
				ctx.put("hint","");
				try {
					new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,ruleId,question,hint,ActionTakenBy");
					SqlResources.getSqlMapProcessor(ctx).update("rulepolicytransaction.RESETFlagsLawyers_p",ctx);
				} catch (Exception e) {
					logger.error("Unexpected error", e);
				}
				
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
	
	public static void archiveFlag(Context ctx,String ruleId)
	{
		
		ctx.put("ruleId",ruleId);
		try {
			new SetParametersForStoredProcedures().setParametersInContext(ctx, "PolicyKey,ruleId,question,hint,ActionTakenBy");
			SqlResources.getSqlMapProcessor(ctx).update("rulepolicytransaction.RESETFlagsLawyers_p",ctx);
			} catch (Exception e) {
				logger.error("Unexpected error", e);
			}
		}
		
	public static void clearRuleSetContextList(String ruleId,List ruleSetContextList)
	{
		try
		{
			if(ruleSetContextList !=null)
				ruleSetContextList.clear();
			
			
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
	}
	public static String formatDate(Object obj,String format)
	{
		SimpleDateFormat formatter= new SimpleDateFormat(format);
		try
		{
		String dateString=obj.toString();
		Date date = formatter.parse(dateString);
		return formatter.format(date);
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
			return null;
		}
		
	}
	public static Object initlizePOJO(Context ctx,Object obj)
	{
		String methodName="";
		
		try {

			Class c=obj.getClass(); 
			obj = c.newInstance();
			Method method=null;
			Field[] fields=c.getDeclaredFields(); 
			for(Field field : fields)
			{
				
				if(field.getType().toString().equalsIgnoreCase("class java.lang.Integer"))
				{
					methodName=field.getName();
					int fieldValue=(ctx.get(field.getName())!=null?Integer.valueOf(ctx.get(field.getName()).toString().trim()):0);
					BeanUtils.setProperty(obj, field.getName(), fieldValue);
					
				}
				if(field.getType().toString().equalsIgnoreCase("class java.lang.String"))
				{
					methodName=field.getName();
					String fieldValue=(ctx.get(field.getName())!=null?ctx.get(field.getName()).toString().trim():"");
					BeanUtils.setProperty(obj, field.getName(), fieldValue);
					
				}
				if(field.getType().toString().equalsIgnoreCase("class java.lang.Long"))
				{
					methodName=field.getName();
					long fieldValue=(ctx.get(field.getName())!=null?Long.valueOf(ctx.get(field.getName()).toString().trim()):0);
					BeanUtils.setProperty(obj, field.getName(), fieldValue);
					
				}
				if(field.getType().toString().equalsIgnoreCase("class java.lang.Float"))
				{
					methodName=field.getName();
					float fieldValue=(ctx.get(field.getName())!=null?Float.valueOf(ctx.get(field.getName()).toString().trim()):0.0F);
					BeanUtils.setProperty(obj, field.getName(), fieldValue);
					
				}
				if(field.getType().toString().equalsIgnoreCase("class java.lang.Character"))
				{
					methodName=field.getName();
					char fieldValue=(ctx.get(field.getName())!=null?Character.valueOf(ctx.get(field.getName()).toString().charAt(0)):' ');
					BeanUtils.setProperty(obj, field.getName(), fieldValue);
					
				}
				if(field.getType().toString().equalsIgnoreCase("class java.lang.Double"))
				{
					methodName=field.getName();
					double fieldValue=(ctx.get(field.getName())!=null?Double.valueOf(ctx.get(field.getName()).toString().trim()):0.0);
					BeanUtils.setProperty(obj, field.getName(),fieldValue);
					
				}
				/*if(!methodName.equals(""))
				{
				method=c.getMethod("get"+""+Character.toUpperCase(methodName.charAt(0)) + methodName.substring(1));
				}*/
			}
			
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
		return obj;
	}
}
