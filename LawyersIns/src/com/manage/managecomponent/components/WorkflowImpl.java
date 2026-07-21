package com.manage.managecomponent.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.manage.managebusinessrules.rules.RuleImpl;
import com.manage.managebusinessrules.rules.RulesResources;
import com.manage.managecomponent.workflow.ExecutionSeqComparator;
import com.manage.managecomponent.workflow.Instruction;
import com.manage.managemetadata.metadata.ComputeruleImpl;
import com.manage.managemetadata.metadata.FieldImpl;
import com.manage.managemetadata.metadata.MetaDataResources;
import com.manage.managemetadata.metadata.MetaobjectImpl;
import com.manage.managemetadata.metadata.PropertyImpl;
import com.manage.managemetadata.metadata.PropertyversionImpl;
import com.manage.managemetadata.security.SecurityResources;
import com.ormapping.ibatis.SqlMapProcessor;
import com.ormapping.ibatis.SqlResources;
import com.util.CacheManager;
import com.util.Constants;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.IContext;
import com.util.InetLogger;
import com.util.MapComparator;
import com.util.StringUtils;

public class WorkflowImpl extends Workflow
{
	private static InetLogger logger = InetLogger.getInetLogger(WorkflowImpl.class);
    
    private List instructionsList = new ArrayList();
    public void postParseVisit()
    {
        super.postParseVisit();
        
        instructionsList = new ArrayList();
        if (getBoList() != null)
            instructionsList.addAll(getBoList());

        if (getDatabaseoperationList() != null)
            instructionsList.addAll(getDatabaseoperationList());
        
        if (getStoredprocedureoperationList() != null)
            instructionsList.addAll(getStoredprocedureoperationList());
        
        if(getWebservicecallList() != null)
        	instructionsList.addAll(getWebservicecallList());
        
        if(getDocumentList() != null)
       	 	instructionsList.addAll(getDocumentList());
        
        if(getEmailList() != null)
        	instructionsList.addAll(getEmailList());
        
        if(getBusinessobjectList() != null)
        	instructionsList.addAll(getBusinessobjectList());
        
        if(getFunctionList() != null)
        	instructionsList.addAll(getFunctionList());
        
        Collections.sort(instructionsList, ExecutionSeqComparator.getInstance());
    }
    
    public void executeWorkflow(Context ctx) throws Exception{     

       executeInstructions(ctx, instructionsList);
    }
    
    public List getInstructionList()
    {
    	return instructionsList;
    }
    
//    private void executeInstructions(IContext data, List instructionsList)
//    throws Exception
//    {
//        if (instructionsList == null)
//            return;
//
//       // populateMODefaults(data);
//        
//        
//        
//        try
//        {
//            for (int i = 0; i < instructionsList.size(); i++)
//            {
//                Instruction instr = (Instruction) instructionsList.get(i);
//    
//                if(instr.evaluate(data))
//                {
//	                Object result = executeInstruction(data, instr);
//	                populateResult(data, instr, result);
//                }
//            }
//            
//        }catch(Exception e)
//        {
//           e.printStackTrace();
//        }
//        
//    }
    
    private boolean executeInstructions(IContext data, List instructionsList) throws Exception
    {
        if (instructionsList == null)
            return true;
        
        List resultList = new ArrayList();
        SqlMapProcessor sqlProcessor = SqlResources.getSqlMapProcessor(data);
                
        try
        {
            sqlProcessor.startTransaction();
            for (int i = 0; i < instructionsList.size(); i++)
            {
                Instruction instr = (Instruction) instructionsList.get(i);
    
                if(instr.evaluate(data))
                {
	                Object result = executeInstruction(data, instr);
	                populateResult(data, instr, result);
                }
            }
            
            //Added code to insert custom fields data in db - 18/1/2013
            if(data.get(Constants.INET_ERRORS_LIST) == null)
            	insertCustomFieldsData(data);
            //Ended code
            
            sqlProcessor.commitTransaction();
           
        }
        catch(Exception e)
        {
        	cleanUpResultFromContext(data, instructionsList);
//        	logger.error(e.getMessage());
        	
        	//if(e instanceof ValidationException)
        	if(data.get("isThrowExceptionToPage") != null && data.get("isThrowExceptionToPage").toString().equals("Y")){
        		//do nothing
        	}else{
        	   throw e;
            }
        	
        	//return false;
        }
        finally
        {
        	sqlProcessor.endTransaction();
        	sqlProcessor.closeSesison();            
        }
        
        return true;
    }
    
//    public Object executeInstruction(IContext context, String componentName, String instrName) throws Exception
//    {
//        String oldProject = context.getProject();
//
//        try
//        {
//            if(context == null)
//                return null;
//
//            Instruction instr = getInstructionInstance(context, componentName, instrName);
//            if(instr == null)
//            	return null;
//            
//            return executeInstruction(context, instr);
//        }
//        finally
//        {
//            context.setProject(oldProject);
//        }
//    }
//    
//    public Instruction getInstructionInstance(IContext context, String componentName, String instrName)
//    {
//    	Instruction instr = null;
//    	
//    	ComponentImpl componentImpl = null;
//		try {
//			componentImpl = ComponentResources.getInstance(context).getComponent(componentName);
//		} catch (Exception e) {
//			logger.debug("Problem in loading component...." +componentName);
//		}
//    	if(componentImpl == null)
//    		return instr;
//    	
//    	List actionList = componentImpl.getActionList();
//    	if(actionList == null)
//    		return instr;
//    	
//    	for(int i=0; i<actionList.size(); i++)
//    	{
//    		ActionImpl actionImpl = (ActionImpl) actionList.get(i);
//    		if(actionImpl == null)
//    			continue;
//    		
//    		List workflowList = actionImpl.getWorkflowList();
//    		if(workflowList == null)
//    			continue;
//    		
//    		for(int j=0; j<workflowList.size(); j++)
//        	{
//    			WorkflowImpl workflowImpl = (WorkflowImpl) workflowList.get(j);
//        		List instructionList = workflowImpl.getInstructionList();
//        		instr = getInstructionInstance(instructionList, instrName);
//        		if(instr != null)
//        			break;
//        	}    		
//    	}
//    	
//        return instr;
//    }
//    
//    public Instruction getInstructionInstance(List instructionList, String instrName)
//    {
//    	if(instructionList == null)
//    		return null;
//    	
//    	for(int j=0; j<instructionList.size(); j++)
//    	{
//    		Instruction instr = (Instruction) instructionList.get(j);
//    		if(instrName.equals(instr.getName()))
//    			return instr;
//    	}
//    	
//    	return null;
//    }
    
    public Object executeInstruction(IContext data, Instruction instr) throws Exception
    {
        Object o = null;
        
        try
        {
            o = instr.execute(data);
        }
        catch (SQLException sqe)
        {
        	throw sqe;
        } 
        
        return o;
    }
    
    private void processComputeRule(IContext data, Instruction instr, Map result) throws Exception
    {
    	Context ctx = (Context)data.getclone();
    	String moNames = instr.getMetaobject();
    	String moName = null;
    	String[] moNameArray = null;
    	
    	if(moNames == null || "".equals(moNames))
    		return;
    	
    	if(moNames.contains(";"))
    		moNameArray = moNames.split(";");
    	    	
    	if(moNameArray != null)
    	{
    		for(int i=0; i<moNameArray.length; i++)
    		{
    			moName = moNameArray[i];
    			if(moName == null)
    	    		continue;
    	    	
    	    	MetaobjectImpl moImpl = MetaDataResources.getInstance(ctx).getMetaobject(moName);
    	    	if(moImpl == null)
    	    		continue;
    	    	
    	    	processMetaObjetComputeRule(ctx, moImpl, result);
    		}
    	}
    	else
    	{
    		moName = moNames;
    		if(moName == null)
	    		return;
	    	
	    	MetaobjectImpl moImpl = MetaDataResources.getInstance(ctx).getMetaobject(moName);
	    	if(moImpl == null)
	    		return;
	    	
	    	processMetaObjetComputeRule(ctx, moImpl, result);
    	}
    	
    	
    }
    
    private void processMetaObjetComputeRule(IContext ctx, MetaobjectImpl moImpl, Map result) throws Exception
    {    	
    	PropertyversionImpl propVersion = moImpl.getPropertyversion("0");
    	if(propVersion == null)
    		return;
    	
    	List propList = propVersion.getPropertyList();
    	if(propList == null)
    		return;
    	
    	ctx.putAll(result);
    	for(int i=0; i<propList.size(); i++)
    	{
    		PropertyImpl propImpl = (PropertyImpl)propList.get(i);
    		String name = propImpl.getFieldName();
    		
    		List computeRuleList = propImpl.getComputeruleList();
    		if(computeRuleList == null)
    			continue;
    		
    		for(int j=0; j<computeRuleList.size(); j++)
    		{
    			ComputeruleImpl computeruleImpl = (ComputeruleImpl) computeRuleList.get(j);
    			computeruleImpl.execute((Context)ctx);
    			result.put(name, ctx.get(name));
    		}
    		
//    		String ruleId = propImpl.getComputerule();
//    		if(ruleId == null || "".equals(ruleId))
//    			continue;
//    		
//    		RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(ruleId);
//          	try {
//                ruleImpl.evaluate((Context)ctx, null);
//            } catch (Exception e) {                
//                e.printStackTrace();
//            }  
//          result.put(name, ctx.get(name));
          	
//          transferDataFromContextToMap(ctx, result);          	
    	}
    }
    
    private void transferDataFromContextToMap(IContext ctx, Map result) throws Exception
    {
    	Iterator it = result.keySet().iterator();
    	while(it.hasNext())
    	{
    		String key = (String)it.next();
    		Object obj = ctx.get(key);
    		if(obj != null)
    			result.put(key, obj);
    	}
    }
    private void cleanUpResultFromContext(IContext data, List instructionsList) throws Exception
    {
    	if(instructionsList == null)
    		return;
    	
    	for (int i = 0; i < instructionsList.size(); i++)
        {
            Instruction instr = (Instruction) instructionsList.get(i);
            cleanUpResult(data, instr);
        }
    }
    
    private void cleanUpResult(IContext data, Instruction instr) throws Exception
    {
    	String viewid = instr.getViewid();  
    	String populateid = instr.getPopulate();
    	
//        if(viewid != null)
//        {
//        	if(data.containsKey(viewid))
//            	data.remove(viewid);
//        }
//        else 
    	
    	if(populateid != null)
        {
        	if(data.containsKey(populateid))
            	data.remove(populateid);
        }
    }
    
    private void populateResult(IContext data, Instruction instr, Object result) throws Exception{
//        if (result == null)
//            return;
        
        String viewid = instr.getViewid();        
        
        if (result == null){
        	data.put(viewid, result);
        	return; 
        }
           
        if(result instanceof Map){
        	if(result != null){
        		processComputeRule(data, instr, (Map)result);
        		//checkForDateFields(data, (Map)result); //checking for date fields here, to add AM/PM
        		populateClobValue((Map)result);
            }
            if(viewid != null){
            	Map map = new HashMap();
            	if(data.containsKey(viewid))
            		map = (HashMap)data.get(viewid);
            	
            	map.putAll((Map)result);
                
            	data.put(viewid, map);
            }
            else{
                data.putAll((Map)result);
            }
        }
        
        if(result instanceof List){
        	List listData = (List)result;
        	List newList = new ArrayList();
        	if(listData != null && listData.size() > 0 && listData.get(0) instanceof List){//For multiple resultset's
        		for(int i=0; i<listData.size(); i++){
        			newList = new ArrayList();
        			List innerListData = (List)listData.get(i);
        			if(innerListData != null){
        				//going to get data from xml for audit history
    	                if(data.get("outputtype") != null && data.get("outputtype").toString().equalsIgnoreCase("xml")){
    	                	//populateXMLResult(data, result, viewid+"_"+i);
    	                	for(int j=0; j<innerListData.size(); j++){
	    	        			Map map = (Map)innerListData.get(j);
	    	        			if(map != null){
	    	        				//checkForDateFields(data, map); //checking for date fields here, to add AM/PM
	    	        				processComputeRule(data, instr, map);
	                                populateClobValue(map);
	                            }
	    	        			newList.add(map);
	    	        		}
    	                }else{
	        				for(int j=0; j<innerListData.size(); j++){
	    	        			Map map = (Map)innerListData.get(j);
	    	        			if(map != null){
	    	        				//checkForDateFields(data, map); //checking for date fields here, to add AM/PM
	    	        				processComputeRule(data, instr, map);
	                                populateClobValue(map);
	                            }
	    	        			newList.add(map);
	    	        		}
    	                }
    	        	}
    	        	
    	        	result = null;
    	        	result = newList;
    	        	
    	        	if(viewid != null){
    	                List list = new ArrayList();
    	                Map map = new HashMap();
    	                if(data.containsKey(viewid)){
    	                    if(data.get(viewid) instanceof List){
//    	                        list = (List)data.get(viewid);
//    	                        list.addAll((List)result);                        
    	                        data.put(viewid, result);
    	                    }
    	                    if(data.get(viewid) instanceof Map && ((List)result).size() >= 1){
    	                        map = (HashMap)data.get(viewid);
    	                        map.putAll((Map)((List)result).get(0));                        
    	                        data.put(viewid, map);
    	                    }
    	                } else if(viewid.contains("_freeform_") && ((List)result).size() >= 1){
    	                    map.putAll((Map)((List)result).get(0));                        
    	                    data.put(viewid, map);
    	                }else{
    	                	data.put(viewid + "_" + i, result);
    	                }
    	            }
        		}
        	}else{
	        	if(listData != null){
	        		//going to get data from xml for audit history
	                if(data.get("outputtype") != null && data.get("outputtype").toString().equalsIgnoreCase("xml")){
	                	populateXMLResult(data, result, viewid);
	                }else{
	                	for(int i=0; i<listData.size(); i++){
		        			Map map =(Map)listData.get(i);
		        			if(map != null){
		        				//checkForDateFields(data, map); //checking for date fields here, to add AM/PM	
		        				processComputeRule(data, instr, map);
	                            populateClobValue(map);
	                        }
		        			
		        			newList.add(map);
		        		}
	                }
	        	}
	        	
	        	result = null;
	        	result = newList;
	        	
	        	if(viewid != null){
	                List list = new ArrayList();
	                Map map = new HashMap();
	                if(data.containsKey(viewid)){
	                    if(data.get(viewid) instanceof List){
//	                        list = (List)data.get(viewid);
//	                        list.addAll((List)result);                        
	                        data.put(viewid, result);
	                    }
	                    if(data.get(viewid) instanceof Map && ((List)result).size() >= 1){
	                        map = (HashMap)data.get(viewid);
	                        map.putAll((Map)((List)result).get(0));                        
	                        data.put(viewid, map);
	                    }
	                } else if(viewid.contains("_freeform_") && ((List)result).size() >= 1){
	                    
	                    map.putAll((Map)((List)result).get(0));                        
	                    data.put(viewid, map);
	                    
	                }else{
	                	data.put(viewid, result);
	                }
	            }
	            //sorting 
	//            result = applySorting((List)result,data);
	//            data.put(viewid, result);
	        }
        }
       
        
        
        if(instr.getPopulate() != null){
        	data.put(instr.getPopulate(), result);
        }
            
    }
    private List applySorting(  List blockDataList,IContext ctx)  throws Exception
    {
    	int dataType=0;
        if (blockDataList == null)
        {
            return null;
        }
        if(null!=ctx.get(Constants.INET_SORT_FIELD))
        {
        	FieldImpl fieldImpl = MetaDataResources.getInstance(ctx).getField(ctx.get(Constants.INET_SORT_FIELD).toString());
        	if(fieldImpl==null)
        		dataType = Constants.CHAR_DATA_TYPE ;
        	else
        		dataType = populateDataType(fieldImpl);
        	MapComparator mapComparator = new MapComparator(ctx.get(Constants.INET_SORT_FIELD).toString(),dataType, ctx.get(Constants.INET_SORT_TYPE).toString());
        	Collections.sort(blockDataList, mapComparator);
        }
        return blockDataList;
     }

	private int populateDataType(FieldImpl fieldImpl) {
		String datatype =fieldImpl.getType();
		if(datatype.equals("V"))
			return Constants.CHAR_DATA_TYPE ;
		 else if(datatype.equals("I"))
			 return Constants.NUMERIC_DATA_TYPE;
		 else if(datatype.equals("D"))
			 return Constants.DATE_DATA_TYPE;
		 else
			 return  Constants.CHAR_DATA_TYPE ;
		 
	}
	
	public boolean evaluate(IContext data) throws Exception {
		if(getEval() == null || "".equals(getEval()))
            return true;
		
		boolean validate = true;
		try {
			//checking for tabsconfigurationresource id
	        Map map = CacheManager.get("Tabs_Conf") == null ? null : (HashMap)CacheManager.get("Tabs_Conf");
	        if(map != null && map.containsKey(getEval()) && "N".equalsIgnoreCase(map.get(getEval()).toString())){
	        	return false;
	        }
	        
	        map = CacheManager.get("Tabs_Conf_Map") == null ? null : (HashMap)CacheManager.get("Tabs_Conf_Map");
	        if(map != null && map.containsKey(getEval()) && "N".equalsIgnoreCase(map.get(getEval()).toString())){
	        	return false;
	        }
			
	        //checking for security id
			int accessType = SecurityResources.getInstance(data).getAccessType(getEval(), (Context)data);
			if(accessType == SecurityResources.HIDE)
				return false;
	        
			RuleImpl ruleImpl = RulesResources.getInstance(data).findRule(getEval());
			if(ruleImpl != null)
				validate = ruleImpl.evaluate((Context)data, null);
		}catch (Exception e) {            
			logger.error((Context)data, "Error in loading resource for eval " + getEval());
			throw e;
//        	e.printStackTrace();
		}
		
		return validate;
	}

        public void populateClobValue(Map map){
            if(map != null){
                Iterator it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pairs = (Map.Entry)it.next();
                    BufferedReader reader = null;
                    StringBuffer strbuf = null;
                    if(pairs.getValue() instanceof net.sourceforge.jtds.jdbc.ClobImpl){
                       net.sourceforge.jtds.jdbc.ClobImpl value = (net.sourceforge.jtds.jdbc.ClobImpl)pairs.getValue();
                        try {
                            reader = new BufferedReader(value.getCharacterStream());
                            

                            boolean endFlag = true;
                            String line = null;
                            strbuf = new StringBuffer(300);
                            while (endFlag)
                            {
                                    line = reader.readLine();
                                    if (line != null)
                                            strbuf.append(line).append("\n");
                                    if (line == null)
                                            endFlag = false;
                            }

                        } catch (SQLException ex) {
	                            logger.error("Unable to convert CLOB field", ex);
                        } catch (IOException iex) {
	                            logger.error("Unable to convert CLOB field", iex);
                        } finally {
                            if (reader != null) {
                                try {
                                    reader.close();
                                } catch (IOException ex) {
	                                    logger.error("Unable to close CLOB reader", ex);
                                }
                            }
                        }
                    }
                    if(strbuf != null)
                        map.put(pairs.getKey(), strbuf.toString());

                }
            }
        }
        
    //Newly created to add AM/PM format in data type fields   
    public void checkForDateFields(IContext ctx, Map dataMap) throws Exception {
    	if(dataMap != null){
    		Set keysSet = dataMap.keySet();
    		Iterator itr = keysSet.iterator();
    		while(itr.hasNext()){
    			String fld = itr.next().toString();
    			try{
    				if(MetaDataResources.getInstance(ctx).getField(fld) != null){
    					FieldImpl fldImpl = (FieldImpl)MetaDataResources.getInstance(ctx).getField(fld);
	    				if(fldImpl.getType() != null && (fldImpl.getType().equalsIgnoreCase("TS"))){
	    					if(dataMap.get(fld) != null && !dataMap.get(fld).equals(HtmlConstants.EMPTY)){
			    				try{
			    					String parsePattern = HtmlConstants.EMPTY;
			    					String toPattern = "MM/dd/yyyy";
			    					
			    					//setting toPattern for all display dates based on dateFormat property in tabsconfiguration.xml file located at Project level
			    					if(ctx.get(HtmlConstants.DATE_FORMAT_TS) != null && !ctx.get(HtmlConstants.DATE_FORMAT_TS).equals(HtmlConstants.EMPTY)){
			    						toPattern = ctx.get(HtmlConstants.DATE_FORMAT_TS).toString();
			    					}
			    					
			    					//Reading datePattern from Properties file
			    					/*if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".datePattern") != null &&
			    							(!SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".datePattern").equals(""))){
			    						toPattern = SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".datePattern").toString();
			    					}*/
			    					
			    					if(dataMap.get(fld).toString().length() <= 10){
			    						if(dataMap.get(fld).toString().contains("/"))
			    							parsePattern = "MM/dd/yyyy";
			    						else
			    							parsePattern = "yyyy-MM-dd";
			    					}else{
			    						parsePattern = "yyyy-MM-dd hh:mm:ss";
			    					}
			    					
			    					SimpleDateFormat sdf = new SimpleDateFormat(parsePattern);
			    					Date dt = sdf.parse(dataMap.get(fld).toString()); 
			    					
			    					if(fldImpl.getPattern() != null && !fldImpl.getPattern().equals(HtmlConstants.EMPTY))
			    						toPattern = fldImpl.getPattern();
			    					
			    					sdf = new SimpleDateFormat(toPattern);
			    					dataMap.put(fld, sdf.format(dt));
			    				}catch (Exception e) {
			    					logger.error((Context)ctx, "Date not parsed properly - " + e.getMessage());
								}
			    			}
			    		}else if(fldImpl.getType() != null && (fldImpl.getType().equalsIgnoreCase("D"))){
	    					if(dataMap.get(fld) != null && !dataMap.get(fld).equals(HtmlConstants.EMPTY)){
			    				try{
			    					String parsePattern = HtmlConstants.EMPTY;
			    					String toPattern = "MM/dd/yyyy";
			    					
			    					//setting toPattern for all display dates based on dateFormatTS property in tabsconfiguration.xml file located at Project level
			    					if(ctx.get(HtmlConstants.DATE_FORMAT) != null && !ctx.get(HtmlConstants.DATE_FORMAT).equals(HtmlConstants.EMPTY)){
			    						toPattern = ctx.get(HtmlConstants.DATE_FORMAT).toString();
			    					}
			    					
			    					if(dataMap.get(fld).toString().length() <= 10){
			    						if(dataMap.get(fld).toString().contains("/"))
			    							parsePattern = "MM/dd/yyyy";
			    						else
			    							parsePattern = "yyyy-MM-dd";
			    					}else{
			    						parsePattern = "yyyy-MM-dd hh:mm:ss";
			    					}
			    					
			    					SimpleDateFormat sdf = new SimpleDateFormat(parsePattern);
			    					Date dt = sdf.parse(dataMap.get(fld).toString()); 
			    					
			    					if(fldImpl.getPattern() != null && !fldImpl.getPattern().equals(HtmlConstants.EMPTY))
			    						toPattern = fldImpl.getPattern();
			    					
			    					sdf = new SimpleDateFormat(toPattern);
			    					dataMap.put(fld, sdf.format(dt));
			    				}catch (Exception e) {
			    					logger.error((Context)ctx, "Date not parsed properly - " + e.getMessage());
								}
			    			}
			    		}
	    			}
    			 }catch (Exception e) {
					logger.error((Context)ctx, e.getMessage());
				}
    		 }
    	 }
     
     }

    //Newly created to save/update custom fields data in db
    private void insertCustomFieldsData(IContext data){
    	try{
	    	if(data.get("customFieldsData") != null && !data.get("customFieldsData").equals(HtmlConstants.EMPTY)){
	        	String actionId = data.get(HtmlConstants.INET_METHOD) == null ? HtmlConstants.EMPTY : data.get(HtmlConstants.INET_METHOD).toString();
	        	if(data.get("customFieldsMethodsMap") != null && data.get("customFieldsMethodsMap") instanceof Map){
	        		if(((Map)data.get("customFieldsMethodsMap")).containsKey(actionId)){
    	            	String customFieldsData = data.get("customFieldsData").toString();
    	            	String component_id = customFieldsData.substring(customFieldsData.indexOf(":")+1, customFieldsData.lastIndexOf(":"));
    	            	
    	            	String metaobject_name = customFieldsData.substring(customFieldsData.lastIndexOf(":")+1, customFieldsData.length());
    	            	metaobject_name = metaobject_name.substring(0, metaobject_name.indexOf("."));
    	        		String object_id = customFieldsData.substring(customFieldsData.lastIndexOf(".")+1, customFieldsData.length());
    	        		
    	        		Object obj = data.get(object_id);
    	        		if(obj == null){
    	        			data.put("key_name", object_id);
    	        			data.put("metaobject_name", metaobject_name);
    	        			obj = SqlResources.getSqlMapProcessor(data).findByKey("managefields.getMaxMetaobjectIdForCustomFields_p_java", data);
    	        			data.put(object_id, ((Map)obj).get(object_id));
    	        		}
    	        		if(data.get(object_id) != null && !data.get(object_id).equals(HtmlConstants.EMPTY)){
    		        		data.put("project_name", data.getProject());
    		        		data.put("component_id", component_id);
    		        		data.put("object_id", -1);
    		        		StringBuffer xml = new StringBuffer("<CustomFieldsData>");
    		        		
    		        		List customFieldsList = SqlResources.getSqlMapProcessor(data).select("managefields.getCustomFieldsList_p_java", data);
    		        		if(customFieldsList != null && customFieldsList.size() > 0){
    		        			for(int l=0; l<customFieldsList.size(); l++){
    		        				Map row = (Map)customFieldsList.get(l);
    		        				
    		        				xml.append("<"+row.get("field")+">");
    		        				xml.append(data.get(row.get("field")) != null ? data.get(row.get("field")) : HtmlConstants.EMPTY);
    		        				xml.append("</"+row.get("field")+">");
    		        			}
    		        		}
    		        		xml.append("</CustomFieldsData>");
    		        		data.put("search_xml", xml.toString());
    		        		data.put("object_id", data.get(object_id));
    		        		SqlResources.getSqlMapProcessor(data).insert("managefields.insertCustomFieldsList_p_java", data);
    		        		
    		        		data.put("customFieldsData", null);
    	        		}
	        		}
	        	}
	        }
    	}catch (Exception e) {
			logger.error((Context)data, e.getMessage());
		}
    }
    
    private void populateXMLResult(IContext ctx, Object result, String viewid) throws Exception{
    	List dataList = new ArrayList();
    	
    	try{
	    	if(result != null && result instanceof List){
	    		List resultDataList = (List)result;
	
	    		if(resultDataList != null){
	    			if(resultDataList.get(0) instanceof List){ //found multiple resultsets one for data and second for pagination and third for dynamic headers
	    				//going to process data xml
	    				List resultDataList0 = (List)resultDataList.get(0);
	    				
	    				for(int i=0; i<resultDataList0.size(); i++){
		    				Map resultMap = (Map)resultDataList0.get(i);
		            		
		    				populateClobValue(resultMap);
		    				
		            		if(resultMap.get("xml") != null){
		            			String xml = resultMap.get("xml").toString();
		            			
		            			xml = xml.replaceAll("&lt;", "<");
		            			xml = xml.replaceAll("&gt;", ">");
		            			
		            			SAXBuilder builder = new SAXBuilder();
		            			Document doc = builder.build(new StringReader(xml));
		            			
		            			Element root = doc.getRootElement();
		            			
		            			processXMLResultForDynamicColumns(ctx, root, dataList, ctx.get("dynamicHeaders"), viewid);
		            		}
	    				}
	    			}
	    		}
	    	}
    	}catch (Exception e) {
			logger.error((Context)ctx, "Unable to parse data XML due to error : " + e.getMessage());
		}
    	
    	//return dataList;
    }
    
    private void processXMLResultForDynamicColumns(IContext ctx, Element root, List dataList, Object dynamicHeadersObj, 
    		String viewid) throws Exception{
    	
    	String dynamicHeaders = dynamicHeadersObj != null ? dynamicHeadersObj.toString() : HtmlConstants.EMPTY;
    	String dynamicHeadersArray[] = dynamicHeaders.split("\\|");
    	
    	List dataListArray = new ArrayList();
    	
    	if(root.getContent() != null && root.getContent().size() > 0){ //got root here
    		List childList = root.getContent();
			
			for(int i=0; i<childList.size(); i++){
				dataList = new ArrayList(); //reset datalist for various dynaheaders
				
				Object obj = childList.get(i); //got DYNACOLUMNS node here
				if(obj != null && obj instanceof Element){
					Element row = (Element)obj;
					
					if(row.getContent() != null){
						List innerChildList = row.getContent();
						for(int j=0; j<innerChildList.size(); j++){
							Object innerObj = innerChildList.get(j); //got DATA_NODE node here
							if(innerObj != null && innerObj instanceof Element){
								Element innerRow = (Element)innerObj;
								LinkedHashMap innerDataMap = new LinkedHashMap();
								
								if(innerRow.getName().equalsIgnoreCase("DATA_NODE")){
									List childrenList = (List)innerRow.getContent();
									if(childrenList != null && childrenList.size() > 0){
										for(int k=0; k<childrenList.size(); k++){
											Object obj1 = childrenList.get(k);
											
											if(obj1 != null && obj1 instanceof Element){
												Element row1 = (Element)obj1;
												
												innerDataMap.put(row1.getName(), row1.getText());
											}
										}
									}
								}
								
								dataList.add(innerDataMap);
							}
						}
					}
				}
				
				dataListArray.add(dataList); //setting list at a particular index
			}
		}
    	
    	//List filteredDataList = new ArrayList();
    	
    	//going to filter datalist based on coming dynamic headers
    	if(dataListArray != null && dataListArray.size() > 0){
    		if(dynamicHeadersArray != null && dynamicHeadersArray.length > 0){
    			for(int k=0; k<dynamicHeadersArray.length; k++){
    				dynamicHeaders = dynamicHeadersArray[k]; //getting dynamicHeaders required for indexed datalist to be displayed
    				
    				dataList = (List)dataListArray.get(k); //getting dataList required for indexed datalist to be displayed
    				
    				List filteredDataList = new ArrayList();
    				
    				for(int j=0; j<dataList.size(); j++){
    					Map dataMap = (Map)dataList.get(j);
    					
    					LinkedHashMap linkedMap = new LinkedHashMap();
    					
    					if(dynamicHeaders != null && !dynamicHeaders.equals(HtmlConstants.EMPTY)){
    						StringTokenizer tokens = new StringTokenizer(dynamicHeaders, ",");
    						
    						while(tokens.hasMoreTokens()){
    							String token = tokens.nextToken();
    							
    							if(!StringUtils.isBlank(token) && dataMap.get(token.trim()) != null){
    								String key = token.trim();
    								String value = dataMap.get(key) != null ? dataMap.get(key).toString() : HtmlConstants.EMPTY;
    								
    								if(key.contains("_hyphen"))
    									key = key.replace("_hyphen", "-");
    								if(key.contains("_amp"))
    									key = key.replace("_amp", "&");
    								if(key.contains("_lp"))
    									key = key.replace("_lp", "(");
    								if(key.contains("_rp"))
    									key = key.replace("_rp", ")");
    								if(key.contains("_lsqb"))
    									key = key.replace("_lsqb", "[");
    								if(key.contains("_rsqb"))
    									key = key.replace("_rsqb", "]");
    								if(key.contains("_space"))
    									key = key.replace("_", " ");
    								
    								linkedMap.put(key.trim(), value);
    							}
    						}
    						
    						if(linkedMap != null){
    	        				checkForDateFields(ctx, linkedMap); //checking for date fields here, to add AM/PM
    	        				populateClobValue(linkedMap);
    	                    }
    					}
    					
    					filteredDataList.add(linkedMap);
    				}
    				
    				ctx.put(viewid+"_"+k, filteredDataList);
    			}
    		}
		}
    	
    	/*if(filteredDataList != null && filteredDataList.size() > 0){
    		return filteredDataList;
    	}*/
    	
    	//return dataList;
    }
}
