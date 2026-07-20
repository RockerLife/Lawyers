package com.userbo;

import com.util.InetLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.manage.managemetadata.functions.commonfunctions.DBUtils;
import com.manage.managemetadata.util.exception.ValidationException;
import com.ormapping.ibatis.SqlResources;
import com.util.Constants;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.IContext;

public class AopSupplementsUtils {
	private static final InetLogger logger = InetLogger.getInetLogger(AopSupplementsUtils.class);

	public static void getFirstSupplementPage(IContext ctx) throws Exception {

		ctx.remove("FirstSupplementPage");

		@SuppressWarnings("unchecked")
		List aoplist = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields", ctx);

		Map covMap = new HashMap();
		if (aoplist != null) {
			Map map = new HashMap();
			for (int i = 0; i < aoplist.size(); i++) {
				map = (HashMap) aoplist.get(i);
				covMap.put("AOP_Percentage_" + map.get("AOPKey"), map
						.get("PercentageValue"));
				covMap.put("AOPCommentDesc_" + map.get("AOPKey"), map
						.get("AOPCommentDesc"));
			}
		}

		if (covMap.get("AOP_Percentage_27") != null
				&& Integer.parseInt(covMap.get("AOP_Percentage_27").toString()) > 0) {
			ctx.put("FirstSupplementPage", "realEstateResi");

		} else if (covMap.get("AOP_Percentage_20") != null
				&& Integer.parseInt(covMap.get("AOP_Percentage_20").toString()) > 0) {
			ctx.put("FirstSupplementPage", "realEstateCommercial");

		} else if (covMap.get("AOP_Percentage_18") != null
				&& Integer.parseInt(covMap.get("AOP_Percentage_18").toString()) > 0) {
			ctx.put("FirstSupplementPage", "plaintiffSupp");

		} else if (covMap.get("AOP_Percentage_24") != null
				&& Integer.parseInt(covMap.get("AOP_Percentage_24").toString()) > 0) {
			ctx.put("FirstSupplementPage", "willsEstateSupp");
		}
		
		ctx.put("AOP_Percentage_18",covMap.get("AOP_Percentage_18") );
		ctx.put("AOP_Percentage_20",covMap.get("AOP_Percentage_20") );
		ctx.put("AOP_Percentage_24",covMap.get("AOP_Percentage_24") );
		ctx.put("AOP_Percentage_27",covMap.get("AOP_Percentage_27") );

	}
	
	public void saveAopResidentialFields(IContext ctx) throws Exception{
		
			Context ctx1 ;
			List limtTypes = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementAOPSupplementsQueriesgetAOPREResidentialKeys", ctx);
			if (limtTypes != null) {
				
				//int totalAOPpercent = AOP.checkAOPPercentage(limtTypes, ctx);
				
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
						if (map.get("AOPREKey").toString().equals("20")) {
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
		
	public void populateAOPResidentialFields(IContext ctx) throws Exception {
		List wesList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementAOPSupplementsQueriespopulateAOPREResidential",
				ctx);

		//ctx.remove("realEstateSuppResidential_freeform_1");
		ctx.remove("REROther");
		ctx.remove("TotalAOPRE_Percentage");

		int totalPercentage = 0;
		Map covMap = new HashMap();
		if (wesList != null) {
			Map map = new HashMap();
			for (int i = 0; i < wesList.size(); i++) {
				map = (HashMap) wesList.get(i);
				ctx.put("AOPRE_Percentage_" + map.get("AOPREKey"), map
						.get("PercentageValue"));
				if (map.get("PercentageValue") != null) {
					totalPercentage = totalPercentage
							+ Integer.parseInt(map.get("PercentageValue")
									.toString());
				}
				ctx.put("AOPRE_CommentDesc_" + map.get("AOPREKey"), map
						.get("AOPRECommentDesc"));
				if (ctx.get("AOPRE_CommentDesc_20") != null
						&& !ctx.get("AOPRE_CommentDesc_20").equals("")) {
					ctx.put("REROther", "Y");
				}
			}
		}
		
		ctx.put("TotalAOPRER_Percentage", totalPercentage);
		//ctx.put("realEstateSuppResidential_freeform_1", covMap);
	}
	
	public void saveAopCommercialFields(IContext ctx) throws Exception{
		
		Context ctx1 ;
		List limtTypes = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementAOPSupplementsQueriesgetAOPRECommercialKeys", ctx);
		if (limtTypes != null) {
			
			//int totalAOPpercent = AOP.checkAOPPercentage(limtTypes, ctx);
			
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
	
	public void populateAOPCommercialFields(IContext ctx) throws Exception {
		List wesList = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementAOPSupplementsQueriespopulateAOPRECommercial",
				ctx);

		//ctx.remove("realEstateSupp_freeform_1");
		ctx.remove("REOther");
		ctx.remove("TotalAOPRE_Percentage");

		int totalPercentage = 0;
		Map covMap = new HashMap();
		if (wesList != null) {
			Map map = new HashMap();
			for (int i = 0; i < wesList.size(); i++) {
				map = (HashMap) wesList.get(i);
				ctx.put("AOPRE_Percentage_" + map.get("AOPREKey"), map
						.get("PercentageValue"));
				if (map.get("PercentageValue") != null) {
					totalPercentage = totalPercentage
							+ Integer.parseInt(map.get("PercentageValue")
									.toString());
				}
				ctx.put("AOPRE_CommentDesc_" + map.get("AOPREKey"), map
						.get("AOPRECommentDesc"));
				if (ctx.get("AOPRE_CommentDesc_17") != null
						&& !ctx.get("AOPRE_CommentDesc_17").equals("")) {
					ctx.put("REOther", "Y");
				}
			}
		}
		ctx.put("TotalAOPRE_Percentage", totalPercentage);
		//ctx.put("realEstateSupp_freeform_1", covMap);
	}
	
	public static void showAOPSupplementsLink(IContext ctx) throws Exception{
		
		

		@SuppressWarnings("unchecked")
		List aoplist = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields", ctx);

		Map covMap = new HashMap();
		if (aoplist != null) {
			Map map = new HashMap();
			for (int i = 0; i < aoplist.size(); i++) {
				map = (HashMap) aoplist.get(i);
				covMap.put("AOP_Percentage_" + map.get("AOPKey"), map
						.get("PercentageValue"));
				covMap.put("AOPCommentDesc_" + map.get("AOPKey"), map
						.get("AOPCommentDesc"));
			}
		}		
		
		ctx.put("AOP_Percentage_18",covMap.get("AOP_Percentage_18") );
		ctx.put("AOP_Percentage_20",covMap.get("AOP_Percentage_20") );
		ctx.put("AOP_Percentage_24",covMap.get("AOP_Percentage_24") );
		ctx.put("AOP_Percentage_27",covMap.get("AOP_Percentage_27") );
		
	}
	 public static void getFamilySupplementsDetails(Context ctx)
	 {
		 try
		 {
		 List wesList = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementManualBoQueriesgetAFamilyLawDetailsLWList",ctx);

			//ctx.remove("realEstateSuppResidential_freeform_1");
			int totalPercentage = 0;
			Map covMap = new HashMap();
			if (wesList != null) {
				Map map = new HashMap();
				for (int i = 0; i < wesList.size(); i++) {
					map = (HashMap) wesList.get(i);
					ctx.put("FLAOP_Percentage_" + map.get("FLAOPKey"), map.get("PercentageValue"));
					if (map.get("PercentageValue") != null) {
						totalPercentage = totalPercentage+ Integer.parseInt(map.get("PercentageValue").toString());
					}
					
					if(map.get("FLAOPKey")!=null && Integer.parseInt(map.get("FLAOPKey").toString())==7)
					{
						String desc= map.get("FLAOPCommentDesc")!=null && ! map.get("FLAOPCommentDesc").equals(HtmlConstants.EMPTY)? map.get("FLAOPCommentDesc").toString():"";
						
						ctx.put("FLAOPCommentDesc", desc);
					}
					}
				}
			
			
			ctx.put("TotalAOPRER_Percentage", totalPercentage);
			//ctx.put("realEstateSuppResidential_freeform_1", covMap);

		 }
		 catch(Exception e)
		 {logger.error("Unexpected error", e);}

}
	 
	 public static  void saveFamilyLawFields(Context ctx){
			
			List finalList=new ArrayList();
			try {
				List limtTypes = SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementAOPSupplementsQueriesgetFamilyLawKeys", ctx);
				if (limtTypes != null) {
				
				int totalAOPpercent =0;
				
			
					Map map = new HashMap();
					Map map1 = null;
					for (int i = 0; i < limtTypes.size(); i++) {
						map = (HashMap) limtTypes.get(i);
						map1=new HashMap();
						map1.put("VersionSequence", ctx.get("VersionSequence"));
						map1.put("VersionKey", ctx.get("VersionKey"));
						map1.put("PolicyKey", ctx.get("PolicyKey"));
						map1.put("FLAOPKey", map.get("FLAOPKey"));
						map1.put("PercentageValue", ctx.get("FLAOP_Percentage_"+ map.get("FLAOPKey")));
						map1.put("LastUpdateUserID", ctx.get("LastUpdateUserID"));
						if(ctx.get("CreatedBy_"+i)==null || ctx.get("CreatedBy_"+i).equals(HtmlConstants.EMPTY))
							map1.put("CreatedBy", ctx.get("LastUpdateUserID"));
						else
							map1.put("CreatedBy", ctx.get("CreatedBy_"+i));
						
						if(ctx.get("CreatedDate_"+i)==null || ctx.get("CreatedDate_"+i).equals(HtmlConstants.EMPTY))
							map1.put("CreatedDate",ctx.get("LastUpdateTimeStamp"));
						else
							map1.put("CreatedDate",ctx.get("CreatedDate_"+i));
						
						if (map.get("FLAOPKey").toString().equals("7") && ctx.get("FLAOP_Percentage_"+ map.get("FLAOPKey"))!=null && !ctx.get("FLAOP_Percentage_"+ map.get("FLAOPKey")).equals(HtmlConstants.EMPTY)) {
							if (ctx.get("FLAOPCommentDesc") != null && !ctx.get("FLAOPCommentDesc").equals(HtmlConstants.EMPTY)) {
								map1.put("FLAOPCommentDesc", ctx.get("FLAOPCommentDesc"));
							}
							else
								populateError(ctx, "descRequired_error","Description required.");
						}
						else
							map1.put("FLAOPCommentDesc", null);
						int percentage=ctx.get("FLAOP_Percentage_"+ map.get("FLAOPKey"))!=null && !ctx.get("FLAOP_Percentage_"+ map.get("FLAOPKey")).equals(HtmlConstants.EMPTY)?Integer.parseInt(ctx.get("FLAOP_Percentage_"+ map.get("FLAOPKey")).toString()):0;
						totalAOPpercent=totalAOPpercent+percentage;
						
					
						finalList.add(map1);
						
						
					}
					if(totalAOPpercent>100 ||totalAOPpercent<100)
						populateError(ctx, "Total_Percentage","The total of percentage is required to equal 100%, current total is "+ totalAOPpercent + "%");
					ctx.put("finalList",finalList);
					LawyersUtils.convertListDataToXML(ctx,"finalList","outputList");
					
				} 
				}catch (Exception e) {
					logger.error("Unexpected error", e);
			}
		}
	 public static void getGovernmentServicesDetails(Context ctx)
	 {
		 HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
	        HttpSession sess = req.getSession();
	        
	        List finalList = null;
	        if(ctx.get("GovernmentServicesList_list_01") != null &&   ctx.get("GovernmentServicesList_list_01") instanceof List)
	        {
	            finalList = (List)ctx.get("GovernmentServicesList_list_01");
	        	
	        
			       if(finalList.size()==0)
			        {
			    	   Map map = new HashMap();
			        	for (int i = 0; i < 3; i++)
			        	{
			        		map.put("ServiceName", null);
							map.put("ServicesProvided", null);
							finalList.add(map);
			        	
			        	}
			        }
	        }
		 
	 }
	 public static void validateGovernmentSupplement(Context ctx)
	 {
		 try
		 {	
			 HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
		     HttpSession sess = req.getSession();
			 List finalList = null;
		       
		        if(ctx.get("GovernmentServicesList_list_01") != null &&   ctx.get("GovernmentServicesList_list_01") instanceof List)
		        {
		            finalList = (List)sess.getAttribute("GovernmentServicesList_list_01");
		            for (int i = 0; i < finalList.size(); i++)
		        	{
		            	if(i==0)
		        		{
		        			if(ctx.get("ServiceName_"+i)==null || "".equals(ctx.get("ServiceName_"+i).toString())||ctx.get("ServicesProvided_"+i)==null
		        					|| "".equals(ctx.get("ServicesProvided_"+i).toString()) )
		        				new LawyersUtils().populateError(ctx, "serviceIncomplete","You need to complete first row");
		        		}
		        	}
		        }
		 }
		 catch(Exception e)
		 {
			 logger.error("Unexpected error", e);
		 }
	 }
	 public static void saveUpdateGovernmentDetails(Context ctx)
	 {
		 try
		 {
		 	HttpServletRequest req = (HttpServletRequest)ctx.get("DocumentRequest");
	        HttpSession sess = req.getSession();
	        
	        List finalList = null;
	       
	        if(ctx.get("GovernmentServicesList_list_01") != null &&   ctx.get("GovernmentServicesList_list_01") instanceof List)
	        {
	            finalList = (List)sess.getAttribute("GovernmentServicesList_list_01");
	        	int size=finalList.size();
	        	List inputList=new ArrayList();
			       if(size>0)
			        {
			    	   Map map = new HashMap();
			        	for (int i = 0; i < size; i++)
			        	{
			        		/*if(i==0)
			        		{
			        			if(ctx.get("ServiceName_"+i)==null || "".equals(ctx.get("ServiceName_"+i).toString())||ctx.get("ServicesProvided_"+i)==null
			        					|| "".equals(ctx.get("ServicesProvided_"+i).toString()) )
			        				new LawyersUtils().populateError(ctx, "serviceIncomplete","You need to complete first row");
			        		}*/
			        		map.put("GovtServiceKey", ctx.get("GovtServiceKey_"+i));
			        		map.put("ServiceName", ctx.get("ServiceName_"+i));
							map.put("ServicesProvided",ctx.get("ServicesProvided_"+i));
							map.put("PolicyKey",ctx.get("PolicyKey"));
							map.put("VersionSequence",ctx.get("VersionSequence"));
							map.put("LastUpdateUserID",ctx.get("LastUpdateUserID"));
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
}
