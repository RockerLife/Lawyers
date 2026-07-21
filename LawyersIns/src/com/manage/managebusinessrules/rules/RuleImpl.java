package com.manage.managebusinessrules.rules;

import java.util.ArrayList;

import com.manage.managebusinessrules.rulesengine.Expression;
import com.manage.managebusinessrules.rulesengine.ExpressionFactory;
import com.manage.managemetadata.util.exception.ValidationException;
import com.outline.drools.RuleService;
import com.outline.drools.RuleServiceImpl;
import com.outline.drools.RuleServiceObjectImpl;
import com.util.Constants;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.InetLogger;
import com.util.SystemProperties;

public class RuleImpl extends Rule
{
	private static InetLogger logger = InetLogger.getInetLogger(RuleImpl.class);
	private static String LOGGERBUFFER = "loggerbuffer";
	
//	public RuleImpl(String expression)
//	{
//		setExpression(expression);		
//	}
	
	
//	public void setExpression(String expression)
//	{
//		this.expression = expression;
//	}
	
	public boolean validate(Context data, String args[]) throws Exception
	{
		try
		{
			boolean flag = false;
			String expr = getExpression();		       
		    String error = "";    
			if(  expr == null  || "".equals(expr) || "null".equals(expr)  )
			{
				error = "Rule " + getId() +" is empty or null";
				logger.debug(error);
				populateLoggerBuffer(data).append(error +" \n");
				
				populateErrorList(error, data);
				return flag;
			}	
			
			String rule = expr.substring(0,expr.indexOf("("));
	        String ruleContent = expr.substring(expr.indexOf("(")+1,expr.lastIndexOf(")"));
			
			Expression expression = ExpressionFactory.getExpression(rule);	
			if(expression == null)
			{
				error = expr + " is not generated from factory";
				logger.debug(error);
				populateLoggerBuffer(data).append(error +" \n");
				populateErrorList(error, data);
				return flag;
			}
			
			flag = expression.checkBracesCorrectness(expr);
			
			if(!flag)
			{
				error = expr + " is not properly closed";
				logger.debug(error);
				populateLoggerBuffer(data).append(error +" \n");
				populateErrorList(error, data);
				return flag;
			}
			
			String id ="";
			RulesetImpl rulesetImpl = (RulesetImpl)getParentNode();	
			if(rulesetImpl != null && rulesetImpl.getName() != null)
				id = rulesetImpl.getName()+".";
			
			id = id + getId();
			
			expression.setId(id);
			expression.setRuleContent(ruleContent);
			expression.setRuleExpression(expr);	
			
			logger.debug("Expression " + getExpression() + " is going to validate");
			populateLoggerBuffer(data).append("Expression " + getExpression() + " is going to validate \n");
			Object result = expression.validateRule(data);
			
			if(result != null && result instanceof Boolean)
				flag = ((Boolean)result).booleanValue();		
			
			logger.debug("Expression " + getExpression() + " has been validated");	
			populateLoggerBuffer(data).append("Expression " + getExpression() + " has been validated \n");
			return flag;
		}
		catch (Exception e)
		{
			logger.error("Rule validation failed", e);
			populateLoggerBuffer(data).append(getExpression() + " could not validated \n");
			return false;
		}
	}
	
	public void populateErrorList(String error, Context ctx)
	{
		ArrayList errorsList = (ArrayList) ctx.get(Constants.INET_ERRORS_LIST);
	    if(errorsList==null)
	    {
	        errorsList = new ArrayList();
	        ctx.put(Constants.INET_ERRORS_LIST, errorsList);
	    }
	    
	    ValidationException e = new ValidationException(error);
	    errorsList.add(e);
	    ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
	}
	
	public boolean evaluate(Context data, String args[]) throws Exception
	{
		boolean flag = true;
		Object result = execute(data, args);
		if(result != null && result instanceof Boolean)
			flag = ((Boolean)result).booleanValue();
		//removing loggerbuffer from context if it not required for application
        if(SystemProperties.getInstance().getProperty("appl."+data.getProject()+".isruleslogger.required") == null ||
                    !SystemProperties.getInstance().getProperty("appl."+data.getProject()+".isruleslogger.required").toString().equals("Y"))
              data.remove(LOGGERBUFFER);

		return flag;
		
//		try
//		{
//			boolean flag = false;
//			String expr = getExpression();		       
//		        
//			if(  expr == null  || "".equals(expr) || "null".equals(expr)  )
//	        	return flag;
//			
//			String rule = expr.substring(0,expr.indexOf("("));
//	        String ruleContent = expr.substring(expr.indexOf("(")+1,expr.lastIndexOf(")"));
//			
//			Expression expression = ExpressionFactory.getExpression(rule);
//			if(expression == null)
//			{
//				logger.debug("Instance of " + expr + " is not generated from expression factory");
//				return flag;
//			}
//			
//			String id ="";
//			RulesetImpl rulesetImpl = (RulesetImpl)getParentNode();	
//			if(rulesetImpl != null)
//			{
//				String rulesetName = rulesetImpl.getName();
//				if(rulesetName != null)
//				{
//					id = rulesetName+".";
//					expression.setRulesetName(rulesetName);
//				}
//			}
//			
//			id = id + getId();
//			
//			expression.setId(id);
//			expression.setRuleContent(ruleContent);
//			expression.setRuleExpression(expr);
//			
////			Object result = null;
////			if(this.validate(data, null))
////				result = expression.executeRule(data);
//			
//			logger.debug("Expression " + getExpression() + " is going to execute");
//			
//			Object result = expression.executeRule(data);
//			
//			if(result != null && result instanceof Boolean)
//				flag = ((Boolean)result).booleanValue();
//			
//			
//			logger.debug("Expression " + getExpression() + " has been executed");
//			return flag;
//		}
//		catch (Exception e)
//		{
//			logger.debug(getExpression() + " could not evaluated");
//			return false;
//		}
//		USR91116991LU764
	}
	
	public static StringBuffer populateLoggerBuffer(Context data)
	{
		Object obj = data.get(LOGGERBUFFER);
		StringBuffer loggerBuffer = null;
		if(obj != null && obj instanceof String){
			loggerBuffer = new StringBuffer(obj.toString());
		}else if(obj != null && obj instanceof StringBuffer){
			loggerBuffer = (StringBuffer) data.get(LOGGERBUFFER);
		} 
		if(loggerBuffer == null){
			loggerBuffer = new StringBuffer();
			data.put(LOGGERBUFFER, loggerBuffer);
		}		
		
		return loggerBuffer;
	}
	
	public Object execute(Context data, String args[]) throws Exception
	{
		try
		{	
			try{
				if(getId().startsWith("CUSTOM")){
					String ruleId = getId();
					ruleId = ruleId.replace("CUSTOM", HtmlConstants.EMPTY);
					
					logger.debug("Executing CUSTOM rule : " + ruleId);
					if(!data.containsKey("RuleObject")) { 
						RuleService ruleService = new RuleServiceImpl();
						data.put("rule_id", ruleId);
						
						if(!ruleService.validate(data)){
							logger.debug(getExpression() + " could not evaluated");
							return null;
						}
						
						return ruleService.execute(data);
					}else {
						RuleService ruleService = new RuleServiceObjectImpl();
						return ruleService.execute(data.get("RuleObject"),ruleId);
					}
				}
			}catch (Exception e) {
				logger.error("Unexpected error", e);
				// TODO: handle exception
			}
			
			Object result = null;
			String expr = getExpression();		       
		        
			if(  expr == null  || "".equals(expr) || "null".equals(expr)  )
	        	return result;
			
			String rule = expr.substring(0,expr.indexOf("("));
	        String ruleContent = expr.substring(expr.indexOf("(")+1,expr.lastIndexOf(")"));
			
			Expression expression = ExpressionFactory.getExpression(rule);
			if(expression == null)
			{
				logger.debug("Instance of " + expr + " is not generated from expression factory");
				populateLoggerBuffer(data).append("Instance of " + expr + " is not generated from expression factory \n");
				return result;
			}
			
			String id ="";
			if(getParentNode() != null && getParentNode() instanceof RulesetImpl)
			{
				RulesetImpl rulesetImpl = (RulesetImpl)getParentNode();	
				if(rulesetImpl != null)
				{
					String rulesetName = rulesetImpl.getName();
					if(rulesetName != null)
					{
						id = rulesetName+".";
						expression.setRulesetName(rulesetName);
					}
				}  
			}
			
			
			id = id + getId();
			
			expression.setId(id);
			expression.setRuleContent(ruleContent);
			expression.setRuleExpression(expr);
			
//			Object result = null;
//			if(this.validate(data, null))
//				result = expression.executeRule(data);
			
			logger.debug("Expression " + getExpression() + " is going to execute");
//			populateLoggerBuffer(data).append("Rule " + getId() + " is going to execute \n");
			
			result = expression.executeRule(data);			
			
			logger.debug("Expression " + getExpression() + " has been executed");
//			populateLoggerBuffer(data).append("Rule " + getId() + " has been executed \n");
			
			return result;
		}
		catch (Exception e)
		{
			logger.error("Rule evaluation failed", e);
			//populateLoggerBuffer(data).append(getExpression() + " could not evaluated \n");
			
			return null;
		}
	}

	public Object executeByObject(Context data, String args[]) throws Exception
	{
		try
		{	
			try{
				if(getId().startsWith("CUSTOM")){
					String ruleId = getId();
					ruleId = ruleId.replace("CUSTOM", HtmlConstants.EMPTY);
					
					logger.debug("Executing CUSTOM rule : " + ruleId);
					
					RuleService ruleService = new RuleServiceObjectImpl();
					//data.put("rule_id", ruleId);
					
					/*if(!ruleService.validate(data)){
						logger.debug(getExpression() + " could not evaluated");
						return null;
					}*/
					
					return ruleService.execute(data.get("RuleObject"),ruleId);
				}
			}catch (Exception e) {
				logger.error("Unexpected error", e);
				// TODO: handle exception
			}
			
			Object result = null;
			String expr = getExpression();		       
		        
			if(  expr == null  || "".equals(expr) || "null".equals(expr)  )
	        	return result;
			
			String rule = expr.substring(0,expr.indexOf("("));
	        String ruleContent = expr.substring(expr.indexOf("(")+1,expr.lastIndexOf(")"));
			
			Expression expression = ExpressionFactory.getExpression(rule);
			if(expression == null)
			{
				logger.debug("Instance of " + expr + " is not generated from expression factory");
				//populateLoggerBuffer(data).append("Instance of " + expr + " is not generated from expression factory \n");
				return result;
			}
			
			String id ="";
			if(getParentNode() != null && getParentNode() instanceof RulesetImpl)
			{
				RulesetImpl rulesetImpl = (RulesetImpl)getParentNode();	
				if(rulesetImpl != null)
				{
					String rulesetName = rulesetImpl.getName();
					if(rulesetName != null)
					{
						id = rulesetName+".";
						expression.setRulesetName(rulesetName);
					}
				}  
			}
			
			
			id = id + getId();
			
			expression.setId(id);
			expression.setRuleContent(ruleContent);
			expression.setRuleExpression(expr);
			
//			Object result = null;
//			if(this.validate(data, null))
//				result = expression.executeRule(data);
			
			logger.debug("Expression " + getExpression() + " is going to execute");
//			populateLoggerBuffer(data).append("Rule " + getId() + " is going to execute \n");
			
			result = expression.executeRule(data);			
			
			logger.debug("Expression " + getExpression() + " has been executed");
//			populateLoggerBuffer(data).append("Rule " + getId() + " has been executed \n");
			
			return result;
		}
		catch (Exception e)
		{
			logger.error("Rule evaluation failed", e);
			//populateLoggerBuffer(data).append(getExpression() + " could not evaluated \n");
			
			return null;
		}
	}

	public Object evaluateAndResolve(Context data, String args[]) throws Exception {
		return null;
	}
	
}
