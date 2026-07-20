
package com.userbo;

import java.util.ArrayList;
import java.util.List;

import com.manage.managemetadata.functions.commonfunctions.RuleUtils;
import com.manage.managemetadata.metadata.IDropDown;
import com.ormapping.ibatis.SqlResources;
import com.util.IContext;

/**
 * @author Amit Jain
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeductibleDropdown implements IDropDown
{

	public List getDropDownData(IContext ctx,String field)throws Exception
	{
		boolean isInsured = false;        
		Object userTypeRule = RuleUtils.executeRule(ctx,"LawyersRule.insuredDeductibleNB");
        if (userTypeRule != null && userTypeRule instanceof Boolean) {
        	isInsured = (Boolean) userTypeRule;
        }	   
		List newDeductible = new ArrayList();
		List revenueList=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.firmviewmomgrossrevenue", ctx);
		if(isInsured)
			newDeductible=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdropdowndatadeductibleInsured", ctx);
		else
			newDeductible=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdropdowndatadeductible", ctx);

		/*
		long average = 0;
		if(ctx.get(Constants.INET_FORM_DIRTY) != null && "Y".equals(ctx.get(Constants.INET_FORM_DIRTY)))
		{
			getAverageRevenue(ctx);
			average = ctx.get("AverageRevFromAjax")!=null ? Long.parseLong(ctx.get("AverageRevFromAjax").toString()) : 0;
		}else if(ctx.get("FromAjax") != null && "Y".equals(ctx.get("FromAjax")))
			average = ctx.get("AverageRevFromAjax")!=null ? Long.parseLong(ctx.get("AverageRevFromAjax").toString()) : 0;
		else{
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
			newDeductible=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdropdowndatadeductible", ctx);
			
			if(newDeductible == null || newDeductible.size() == 0)
				return new ArrayList();
			
		}else if(average >= 500000 && average <=999999){
			   
			ctx.put("AggregateDeductible", "2500");
			newDeductible=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdropdowndatadeductible1", ctx);
			
			if(newDeductible == null || newDeductible.size() == 0)
				return new ArrayList();
			
		}else if(average >= 1000000 && average <=2000000){
			ctx.put("AggregateDeductible", "5000");
			newDeductible=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdropdowndatadeductible1", ctx);
			
			if(newDeductible == null || newDeductible.size() == 0)
				return new ArrayList();
		}else{
			newDeductible=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdropdowndatadeductible", ctx);
			
			if(newDeductible == null || newDeductible.size() == 0)
				return new ArrayList();
		}
		
		*/
		
		return newDeductible;
		
	}
	
   private void getAverageRevenue(IContext ctx) throws Exception{
		
		int count = 0;
		long total = 0;
		long average = 0;
		Object obj0 = com.manage.managemetadata.functions.commonfunctions.Math.removeAmountFormat(ctx.get("Amount_0"));
		Object obj1 = com.manage.managemetadata.functions.commonfunctions.Math.removeAmountFormat(ctx.get("Amount_1"));
		// obj2 = com.manage.managemetadata.functions.commonfunctions.Math.removeAmountFormat(ctx.get("Amount_2"));
		if (obj0 != null && !"".equals(obj0) && !"0".equals(obj0)){
			count = count + 1;
			
			total = total + Long.parseLong(obj0.toString());
		}
		if (obj1 != null && !"".equals(obj1) && !"0".equals(obj1)){
			count = count + 1;
			total = total + Long.parseLong(obj1.toString());
		}
//		if (obj2 != null && !"".equals(obj2) && !"0".equals(obj2)){
//			count = count + 1;
//			total = total + Long.parseLong(obj2.toString());
//		}
		
		if(count != 0 && total != 0){
			average = total/count;
		}
		
		ctx.put("AverageRevFromAjax", average);
	}
	
}
