
package com.userbo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.manage.managecomponent.components.SetParametersForStoredProcedures;
import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.manage.managemetadata.metadata.IDropDown;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;
import com.util.IContext;

/**
 * @author Amit Jain
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LimitDropdown implements IDropDown
{

	public List getDropDownData(IContext ctx,String field)throws Exception {
		List newLimits = new ArrayList();		
		if(!"protexureEstimate".equals(ctx.get("inet_page").toString()))
		
		{
		List revenueList=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.firmviewmomgrossrevenue", ctx);
		LawyersUtils.setCompanyForPolicy((Context)ctx);
		Object obj = RuleUtils.executeRule(ctx, "LawyersRule.showLimitForInsured");		
		boolean isInsuredAndQuickQuoteNotSaved = false;
		if(obj != null && obj instanceof Boolean){
			isInsuredAndQuickQuoteNotSaved = (Boolean)obj;
		}
		
		if(isInsuredAndQuickQuoteNotSaved){
			
			Context ctx1 = new Context();
			ctx1.setProject(ctx.getProject());
			ctx1.putAll(ctx);
			
			if(ctx1.get("CompanyKey") == null || !"1".equals(ctx1.get("CompanyKey").toString())) {
				ctx1.put("AggregateLimit", "2000000");
				ctx1.put("OccuranceLimit", "4000000");
			} else {
				
				ctx1.put("AggregateLimit", "1000000");
				ctx1.put("OccuranceLimit", "3000000");
			}
			newLimits=SqlResources.getSqlMapProcessor(ctx1).select("SqlStmts.UserStatementdropdowndataQuickQuoteDropDown", ctx1);
			if(newLimits == null || newLimits.size()==0)
				return new ArrayList();		
			
		}else{
			
			boolean flag = checkSouthDakhotaRule(ctx);
			
			boolean isRatingNew = false;        
			Object objRatingRule = RuleUtils.executeRule(ctx,"LawyersRule.checkNewFiling");
	        if (objRatingRule != null && objRatingRule instanceof Boolean)
	        	isRatingNew = (Boolean) objRatingRule;
			
			Context ctx1 = new Context();
			ctx1.setProject(ctx.getProject());
			ctx1.putAll(ctx);
			
			if(ctx1.get("CompanyKey") == null || !"1".equals(ctx1.get("CompanyKey").toString())) {
				ctx1.put("AggregateLimit", "2000000");
				ctx1.put("OccuranceLimit", "4000000");
			} else {
				
				ctx1.put("AggregateLimit", "1000000");
				ctx1.put("OccuranceLimit", "3000000");
			}
			
			ctx1.put("LimitSequenceLower", "33");
			ctx1.put("LimitSequenceUpper", "35");
			
			if(flag && isRatingNew) // SD NewFiling
				newLimits=SqlResources.getSqlMapProcessor(ctx1).select("SqlStmts.UserStatementdropdowndataSouthDakhotaDropDownNewFiling", ctx1);
			/*else if(flag && !isRatingNew) // SD Old logic
				newLimits=SqlResources.getSqlMapProcessor(ctx1).select("SqlStmts.UserStatementdropdowndataSouthDakhotaDropDown", ctx1);*/
			else
				newLimits = SqlResources.getSqlMapProcessor(ctx1).select("SqlStmts.UserStatementdropdowndataQuickQuoteDropDown", ctx1);
			
			boolean isInsuredRule = false;        
			Object objInsuredRule = RuleUtils.executeRule(ctx,"LawyersRule.isInsured");
	        if (objInsuredRule != null && objInsuredRule instanceof Boolean)
	        	isInsuredRule = (Boolean) objInsuredRule;
			
	        if(isInsuredRule){
				Context ctx2 = new Context();
				ctx2.setProject(ctx.getProject());
				ctx2.putAll(ctx);
				
				if(ctx2.get("CompanyKey") == null || !"1".equals(ctx2.get("CompanyKey").toString())) {
					ctx2.put("AggregateLimit", "2000000");
					ctx2.put("OccuranceLimit", "4000000");
				} else {
					
					ctx2.put("AggregateLimit", "1000000");
					ctx2.put("OccuranceLimit", "3000000");
				}
				
				if(ctx2.get("PolicyKey")!=null && "".equals(ctx2.get("PolicyKey").toString()))
					ctx2.put("PolicyKey",null);
				 new SetParametersForStoredProcedures().setParametersInContext(ctx2, "PolicyKey,AggregateLimit,OccuranceLimit");
				newLimits = (List)SqlResources.getSqlMapProcessor(ctx2).select("Limits.GetLimtsForInsuredFullQuoteLW_p", ctx2);
	        }
			if(newLimits == null || newLimits.size()==0)
				return new ArrayList();	
			
			
//			if(flag){
//				Context ctx1 = new Context();
//				ctx1.setProject(ctx.getProject());
//				ctx1.putAll(ctx);
//				
//				ctx1.put("AggregateLimit", "1000000");
//				ctx1.put("OccuranceLimit", "1000000");
//				
//				newLimits=SqlResources.getSqlMapProcessor(ctx1).select("SqlStmts.UserStatementdropdowndataSouthDakhotaDropDown", ctx1);
//				if(newLimits == null || newLimits.size()==0)
//					return new ArrayList();	
//				
//			}else{
//				Context ctx1 = new Context();
//				ctx1.setProject(ctx.getProject());
//				ctx1.putAll(ctx);
//				
//				ctx1.put("AggregateLimit", "2000000");
//				ctx1.put("OccuranceLimit", "4000000");
//				newLimits = SqlResources.getSqlMapProcessor(ctx1).select("SqlStmts.UserStatementdropdowndataQuickQuoteDropDown", ctx1);
//				//newLimits=SqlResources.getSqlMapProcessor(ctx1).select("SqlStmts.UserStatementdropdowndatalimits", ctx1);
//				if(newLimits == null || newLimits.size()==0)
//					return new ArrayList();	
//			}
				
			
			
			
		}
		/*
		long average = 0;
		if(ctx.get(Constants.INET_FORM_DIRTY) != null && "Y".equals(ctx.get(Constants.INET_FORM_DIRTY)))
		{
			getAverageRevenue(ctx);
			average = ctx.get("AverageRevFromAjax")!=null ? Long.parseLong(ctx.get("AverageRevFromAjax").toString()) : 0;
		}else if(ctx.get("FromAjax") != null && "Y".equals(ctx.get("FromAjax")))
			average = ctx.get("AverageRevFromAjax")!=null ? Long.parseLong(ctx.get("AverageRevFromAjax").toString()) : 0;
		else
		{
			Map map = new HashMap();
			long total = 0;
			int count = 0;
			for(int i=0;i<revenueList.size();i++){	
				map = (HashMap)revenueList.get(i);
				Object objAmount = map.get("Amount");				
				if(objAmount != null && !"".equals(objAmount.toString()) && !"0".equals(objAmount.toString()))
   			 	{
	   				total = total + Long.parseLong(objAmount.toString());
	   				count = count + 1;	   				
   			 	}				
			}			
			if(count > 0)
    		{
				average = total/count;
    		
    		}
    		else
    			average = 0;
		}
		
		if(average == 0 || average <=499999)
		{
			ctx.put("AggregateLimit", "1000000");
			newLimits=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdropdowndatalimits1", ctx);
			if(newLimits == null || newLimits.size()==0)
				return new ArrayList();			
			
		}else if(average >= 500000 && average <=2000000){
			ctx.put("AggregateLimit", "2000000");
			newLimits=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdropdowndatalimits1", ctx);
			if(newLimits == null || newLimits.size()==0)
				return new ArrayList();
			
			
		}else{
			newLimits=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdropdowndatalimits", ctx);
			if(newLimits == null || newLimits.size()==0)
				return new ArrayList();
			
		}
		
		//List limitList=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdropdowndatalimits", ctx);
		*/
		
		}
		else
		{
			newLimits=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdropdowndatalimits", ctx);
		}
		return newLimits;
		
	}
	
     private void getAverageRevenue(IContext ctx) throws Exception{
		
		int count = 0;
		long total = 0;
		long average = 0;
		Object obj0 = com.manage.managemetadata.functions.commonfunctions.Math.removeAmountFormat(ctx.get("Amount_0"));
		Object obj1 = com.manage.managemetadata.functions.commonfunctions.Math.removeAmountFormat(ctx.get("Amount_1"));
		Object obj2 = com.manage.managemetadata.functions.commonfunctions.Math.removeAmountFormat(ctx.get("Amount_2"));
		if (obj0 != null && !"".equals(obj0) && !"0".equals(obj0)){
			count = count + 1;
			
			total = total + Long.parseLong(obj0.toString());
		}
		if (obj1 != null && !"".equals(obj1) && !"0".equals(obj1)){
			count = count + 1;
			total = total + Long.parseLong(obj1.toString());
		}
		if (obj2 != null && !"".equals(obj2) && !"0".equals(obj2)){
			count = count + 1;
			total = total + Long.parseLong(obj2.toString());
		}
		
		if(count != 0 && total != 0){
			average = total/count;
		}
		
		ctx.put("AverageRevFromAjax", average);
	}
     
     
     private boolean checkSouthDakhotaRule(IContext ctx) throws Exception{    	 
    	 ctx.remove("HideDefenseExpense");
    	 boolean flag = false ;
    	 
    	 Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("State.findByKey", ctx);
    	 
    	 Map stateMap = new HashMap();
 		if (obj != null && obj instanceof Map)
 			stateMap = (Map) obj;
 		
 		
 		String StateCode = stateMap.get("StateCode") != null ? stateMap.get("StateCode").toString() : "";
				
		if(StateCode.equals(LawyersConstants.SOUTH_DAKHOTA)){
			flag = true ;
		}
		
		if(flag)
			ctx.put("HideDefenseExpense", "Y");
    	 
    	 return flag ;
    	 
     }
	
}
