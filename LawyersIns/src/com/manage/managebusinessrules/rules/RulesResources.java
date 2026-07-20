package com.manage.managebusinessrules.rules;


import java.util.List;

import com.outline.drools.RuleService;
import com.outline.drools.RuleServiceImpl;
import com.util.IResourceKeys;
import com.util.InetLogger;
import com.xmlobjectbuilder.Resources;

public class RulesResources extends Resources{

    private static InetLogger logger = InetLogger.getInetLogger(RulesResources.class);
    private static final String RESOURCE_IDENTIFIER = "rules";
    
    private EvalrulesImpl evalRulesImpl;
    
    public static RulesResources getInstance(IResourceKeys keys) throws Exception
    {
        Object o = Resources.getInstance(keys, RESOURCE_IDENTIFIER);
        return o==null? null : (RulesResources)o;
    }
    
    public RulesResources(Object o)
    {
        if(o==null)
            return;
        
        this.evalRulesImpl = (EvalrulesImpl) o;
    }
    
   
    public RuleImpl findRule(String evalStr)  
    {
    	try{
    		RuleService ruleService = new RuleServiceImpl();
    		if(evalStr.indexOf(".") != -1){
    			String ruleId = evalStr.substring(evalStr.lastIndexOf(".")+1, evalStr.length());
    			
    			if(ruleService.exists(ruleId)){
    				logger.debug("CUSTOM rule found : " + ruleId);
    				RuleImpl ruleImpl = new RuleImpl();
    				ruleImpl.setId("CUSTOM"+ruleId);
    				return ruleImpl;
    			}
    		}
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	
        return evalRulesImpl.findRule(evalStr);    
    }
    
    public RulesetImpl findRuleSet(String evalStr)  
    {
        return evalRulesImpl.findRuleSet(evalStr);    
    }
    
    public List getRuleSetList()
    {
        return evalRulesImpl.getRulesetList();
    }
    
}
