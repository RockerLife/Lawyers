package com.manage.managecomponent.components;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.manage.managebusinessrules.rules.RuleImpl;
import com.manage.managebusinessrules.rules.RulesResources;
import com.manage.managecomponent.applicationworkflow.ApplicationWorkflowResources;
import com.manage.managecomponent.applicationworkflow.EventImpl;
import com.manage.managecomponent.fields.FieldImpl;
import com.manage.managecomponent.fields.FieldsResources;
import com.manage.managecomponent.processflow.BlockImpl;
import com.manage.managecomponent.processflow.BlockfieldImpl;
import com.manage.managecomponent.processflow.BlockfieldsetImpl;
import com.manage.managecomponent.processflow.LinkForwardImpl;
import com.manage.managecomponent.processflow.LinkImpl;
import com.manage.managecomponent.processflow.PagecomponentImpl;
import com.manage.managecomponent.processflow.ProcessFlowResources;
import com.manage.managemetadata.functions.commonfunctions.DataUtils;
import com.manage.managemetadata.metadata.MetaDataResources;
import com.manage.managemetadata.metadata.MetaobjectImpl;
import com.manage.managemetadata.metadata.PropertyImpl;
import com.manage.managemetadata.util.exception.ValidationException;
import com.ormapping.ibatis.SqlResources;
import com.outline.drools.RuleService;
import com.outline.drools.RuleServiceImpl;
import com.util.CacheManager;
import com.util.Constants;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.InetLogger;
import com.util.StringUtils;
import com.util.SystemProperties;

public class ComponentImpl extends Component {
	private static InetLogger logger = InetLogger.getInetLogger(ComponentImpl.class);
	private List<PagecomponentImpl> parentComponentList = new ArrayList<PagecomponentImpl>();
	
    public String processRequest(Context ctx) throws Exception{
        //computeMetaObjectProperties(ctx);
        validate(ctx);
        
        //going to check for fields to be encrypted based on fieldsimpl.xml
        if(!Constants.YES.equals(ctx.get(Constants.INET_FORM_DIRTY)))
        	checkForEncryptFields(ctx);
        
        //going to check for defaultvalue for htmlfields available on screen
        if(!Constants.YES.equals(ctx.get(Constants.INET_FORM_DIRTY)))
        	checkForDefaultValues(ctx);
        
        ctx.put("metaobjectList", getMetaObjectList(ctx,getMetaobject()));
        performAction(ctx);
        
        String next = null;
        
        //if workflow forward found from workflow xml status node then use this instead of processflow's forward
        if(ctx.get(HtmlConstants.WORKFLOW_FORWARD) != null && !ctx.get(HtmlConstants.WORKFLOW_FORWARD).toString().equals(HtmlConstants.EMPTY))
        	next = ctx.get(HtmlConstants.WORKFLOW_FORWARD).toString();
        
        if(StringUtils.isBlank(next))
        	next = getNextPage(ctx);
        
        //inserting information into uservisits table
        if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".isuservisits.required") != null && 
				SystemProperties.getInstance().getString("appl."+ctx.getProject()+".isuservisits.required").equals("Y")){
        	
        	insertUserVisitInformation(ctx, next);
        }
    	
        if(ctx.get("extendedFields") != null && ctx.get("extendedFields").toString().equals("Y"))
        	insertCustomFields(ctx);
        
    	if(ctx.get("EXECUTE_CUSTOM_RULES") != null && ctx.get("EXECUTE_CUSTOM_RULES").toString().equals("Y")){
    		try{
    			logger.debug("going to execute custom rules from rule engine");
    			RuleService ruleService = new RuleServiceImpl();
    			Context lctx=new Context();
    			if(ctx.size()>0)
    			{
    				Iterator<String> loop = ctx.keySet().iterator();
    				while(loop.hasNext()) {
    					String key = loop.next();
    					if(ctx.get(key)!=null && ctx.get(key) instanceof List) {
    						continue;
    					}
    					lctx.put(key, ctx.get(key));
    				}
    				
    			}
    			
    			ruleService.execute(lctx);
    			/*if(lctx.size()>0)
    			{
    				Iterator<String> loop = lctx.keySet().iterator();
    				Map<String,String> pMap = null;
    				while(loop.hasNext()) {
    					String key = loop.next();
    					if(key.startsWith("Drool-")) {
    						pMap = (Map<String,String>)lctx.get(key);
    						ctx.put("Validation", pMap.get("VALIDATION"));
    						ctx.put("toolTip", pMap.get("TOOLTIP"));
    						ctx.put("answer", pMap.get("ANSWER"));
    					}
    					
    				}
    				
    			}*/
    			ctx.putAll(lctx);
    		}catch (Exception e) {
				logger.error("Unable to execute rules from rule engine due to error : " + e.getMessage());
			}
    	}
    	
        return next;
    }
    
    public void processView(Context ctx) throws Exception{
    	if(ctx.get(HtmlConstants.ISSKIPPAGEVIEW) != null && ctx.get(HtmlConstants.ISSKIPPAGEVIEW).toString().equals("Y"))
    		return;
    	
        ctx.put("metaobjectList", getMetaObjectList(ctx,getMetaobject()));
        performViewAction(ctx);
        
        if(ctx.get("extendedFields") != null && ctx.get("extendedFields").toString().equals("Y"))
        	getCustomFieldsData(ctx);
        
        /*//going to insert user logs
        if(ctx.get("DEBUGMSG") != null && !ctx.get("DEBUGMSG").toString().equals(HtmlConstants.EMPTY))
        	insertUserLogs(ctx);*/
    }
    
    public void performAction(Context ctx) throws Exception{
        if(Constants.YES.equals(ctx.get(Constants.INET_FORM_DIRTY)))
            return;
        
        String actionId = ctx.get(HtmlConstants.INET_METHOD) == null?null:ctx.get(HtmlConstants.INET_METHOD).toString();
        if(actionId == null)
            return;
        
        ActionImpl actionImpl = getAction(actionId);
        if(actionImpl == null)
            return;
        
        try{
            if(Constants.YES.equals(ctx.get(Constants.INET_FORM_DIRTY)))
                return;
            
            List paramList = actionImpl.getSortedActionParamList();
            
            populateActionParams(ctx,paramList,true,false);
            if(isMultiRowList(ctx))
            	processMultiRowList(ctx, actionImpl);
            else
            	actionImpl.processRequest(ctx);
            
            //going to execute workflow event
            if(ctx.get(Constants.INET_ERRORS_LIST) == null){
            	//if new workflow found in ProducerOne
        		if(ctx.get("isApplicationWorkflowNew") != null && ctx.get("isApplicationWorkflowNew").toString().equals("Y")){
        			executeWorkflowNew(ctx, actionImpl, actionId);
        		}else{
        			executeWorkflow(ctx, actionImpl);
        		}
            }
            
            /*if(ctx.get(Constants.INET_ERRORS_LIST) == null){
	            if(ctx.get(HtmlConstants.WORKFLOW_EVENT) != null &&
	            		!ctx.get(HtmlConstants.WORKFLOW_EVENT).toString().equals(HtmlConstants.EMPTY)){
	            	String workflow_event = ctx.get(HtmlConstants.WORKFLOW_EVENT).toString();
	            	try{
	            		EventImpl eventImpl = ApplicationWorkflowResources.getInstance(ctx).getEvent(workflow_event);
	            		logger.debug("Going to execute workflow event : " + workflow_event);
	            		
	            		//if new workflow found in ProducerOne
	            		if(ctx.get("isApplicationWorkflowNew") != null && ctx.get("isApplicationWorkflowNew").toString().equals("Y"))
	            			actionImpl.executeWorkflowEventNew(ctx, eventImpl);
	            		else
	            			actionImpl.executeWorkflowEvent(ctx, eventImpl);
	            		
	            		logger.debug("Workflow event " + workflow_event + " executed successfully");
	            	}catch (Exception e) {
						logger.error("Unbale to get event from applicationworkflow.xml due to error : - " + e.getMessage());
					}
	            }else if(ctx.get(HtmlConstants.EMAIL_NOTIFICATION_EVENT_NAME) != null && 
	                	!ctx.get(HtmlConstants.EMAIL_NOTIFICATION_EVENT_NAME).toString().equals(HtmlConstants.EMPTY)){ //going to execute email notifications
	            	logger.debug("going to execute Email Notification : " + ctx.get(HtmlConstants.EMAIL_NOTIFICATION_EVENT_NAME).toString());
	            	actionImpl.executeEmailNotification(ctx);
	            	logger.debug("Email Notification done");
	            }
            }*/
            
            //going to execute email notifications
            if(ctx.get(HtmlConstants.EMAIL_NOTIFICATION_EVENT_NAME) != null && 
                	!ctx.get(HtmlConstants.EMAIL_NOTIFICATION_EVENT_NAME).toString().equals(HtmlConstants.EMPTY)){ 
            	logger.debug("going to execute Email Notification : " + ctx.get(HtmlConstants.EMAIL_NOTIFICATION_EVENT_NAME).toString());
            	actionImpl.executeEmailNotification(ctx);
            	logger.debug("Email Notification done");
            }
            
            populateActionParams(ctx,paramList,false,true);
        }catch(ValidationException e){
            ArrayList errorsList = (ArrayList) ctx.get(Constants.INET_ERRORS_LIST);
            if(errorsList == null){
                errorsList = new ArrayList();
                ctx.put(Constants.INET_ERRORS_LIST, errorsList);
            }
            
            errorsList.add(e);
            ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
        }
    }
    
    private boolean isMultiRowList(Context ctx) {
    	
    	if(ctx.get("inet_mom_save_list") == null)
    		return false;
    	
    	if("Y".equals(ctx.get("inet_mom_save_list").toString()))
    		return true;

    	return false;
	}

	private void performViewAction(Context ctx) throws Exception{
        ActionImpl actionImpl = getAction("view");
        
        if(actionImpl == null)
            return;
        
        //Calling BeforeMetaObject.BO business object
        if(ctx.get(HtmlConstants.ISMETAOBJECTBOEXECUTED) == null || !ctx.get(HtmlConstants.ISMETAOBJECTBOEXECUTED).toString().equals("Y")){
	        try{
	        	logger.debug("Going to execute bo ..... com.userbo.BeforeMetaObjectBO");
	        	boolean foundExecute = false;
	    		try{
	    			Class claz = Class.forName("com.userbo.BeforeMetaObjectBO");
	                Method[] methods = claz.getMethods();
	                if(methods != null && methods.length > 0){
	                	for(int i=0; i<methods.length; i++){
	                		Method method = methods[i];
	                        if(method.getName().equals("execute")){
	                        	method.invoke(claz.newInstance(), ctx);
	                            foundExecute = true;
	                            ctx.put(HtmlConstants.ISMETAOBJECTBOEXECUTED, "Y");
	                            break;
	                        }
	                    }
	                }
	                if(!foundExecute){
	                	logger.info("Bo can't be execute bcoz it has no execute method.");
	                	ctx.put(HtmlConstants.ISMETAOBJECTBOEXECUTED, "Y");
	                }
	            }catch(InvocationTargetException e){
	                Throwable t = e.getCause();
	                throw (Exception) e.getCause();
	            }
	            
	    		logger.info("Bo execution done for ..... com.userbo.BeforeMetaObjectBO");
	        }catch (Exception e) {
				logger.error("Unable to execute BeforeMetaObject BO", e);
				ctx.put(HtmlConstants.ISMETAOBJECTBOEXECUTED, "Y");
			}
        }
        
        populateDropdownData(ctx);

        List paramList = actionImpl.getSortedActionParamList();
        
        populateActionParams(ctx,paramList,true,false);
        actionImpl.processRequest(ctx);
        populateActionParams(ctx,paramList,false,true);
        
        //going to check for fields to be decrypted based on fieldsimpl.xml
        checkForDecryptFields(ctx);
    }
    
    public void populateDropdownData(Context ctx) throws Exception{
        List moList = getMetaObjectList(ctx,getMetaobject());
        
        if(moList == null || moList.isEmpty())
            return;
      
        PagecomponentImpl pageCmpImpl = ProcessFlowResources.getInstance(ctx).getPagecomponent(name);
        List htmlFields = getHtmlFields(ctx, pageCmpImpl);
        for(int i = 0;i<moList.size();i++){
            MetaobjectImpl moImpl =(MetaobjectImpl) moList.get(i);
            
            moImpl.resolveDropDownValue(ctx,htmlFields);
        }
        
        //going to check for workflow dropdown if exists
        /*try{
	        if(ctx.get(HtmlConstants.INET_METHOD) != null && ApplicationWorkflowResources.getInstance(ctx).getEvent(ctx.get(HtmlConstants.INET_METHOD).toString()) != null){
	        	EventImpl eventImpl = (EventImpl)ApplicationWorkflowResources.getInstance(ctx).getEvent(ctx.get(HtmlConstants.INET_METHOD).toString());
	        	if(eventImpl.getSortedDropDownList() != null && eventImpl.getSortedDropDownList().size() > 0){
	        		for(int i=0; i<eventImpl.getSortedDropDownList().size(); i++){
	        			DropdownImpl dropdownImpl = (DropdownImpl)eventImpl.getSortedDropDownList().get(i);
	        			
	        			dropdownImpl.initializeBOInstance();
	        			
	        			if(StringUtils.isNotBlank(dropdownImpl.getEval())){
	        				RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(dropdownImpl.getEval());
	        	        	if(ruleImpl.evaluate((Context)ctx, null)){
	        	        		Object obj = dropdownImpl.getRange(ctx);
	 	        	           
	        	            	if(obj != null)
	        	            		ctx.put(dropdownImpl.getName(), obj);
	        	        	}
	        			}else{
	        				Object obj = dropdownImpl.getRange(ctx);
	 	        	           
        	            	if(obj != null)
        	            		ctx.put(dropdownImpl.getName(), obj);
	        			}
	        		}
	        	}
	        }
        }catch (Exception e) {
			// TODO: handle exception
		}*/
    }
    
    public void validate(Context ctx) throws Exception{
    	/*if(ctx.get(Constants.INET_SKIP_VALIDATION) != null && ctx.get(Constants.INET_SKIP_VALIDATION).equals("Y")){
    		return;*/
    		
		List moList = new ArrayList();
        String actionId = ctx.get(HtmlConstants.INET_METHOD) == null?null:ctx.get(HtmlConstants.INET_METHOD).toString();
          
        if(actionId == null)
        	moList = getMetaObjectList(ctx,getMetaobject());
        
        PagecomponentImpl pageCmpImpl = ProcessFlowResources.getInstance(ctx).getPagecomponent(name);
        if(ctx.get("rightclickmenufrompage") != null && !ctx.get("rightclickmenufrompage").toString().equals(HtmlConstants.EMPTY))
        	pageCmpImpl = ProcessFlowResources.getInstance(ctx).getPagecomponent(ctx.get("rightclickmenufrompage").toString());
        
        List htmlFieldList = new ArrayList();
          
        List metaobjectsList = new ArrayList();
        ActionImpl actionImpl = getAction(actionId);
        if(actionImpl != null){
        	//added code for validating node
        	List validatefieldList = actionImpl.getValidatefieldList();
        	if(validatefieldList != null && validatefieldList.size() > 0){
        		for(int i=0; i<validatefieldList.size(); i++){
        			ValidatefieldImpl validatefield = (ValidatefieldImpl)validatefieldList.get(i);
        			String rule = validatefield.getEval();
        			if(rule != null && !rule.equals("")){
        				RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(rule);
		          	    if(ruleImpl != null){
		          	    	boolean flag = ruleImpl.evaluate(ctx, null);
		          	    	if(flag){
		          	    		//Getting list of html fields for which validation to be execute
		          	    		String fields = validatefield.getFields();
		          	    		htmlFieldList = Arrays.asList(fields.split(";")); 
		          	    	}else{
		          	    		htmlFieldList = getHtmlFields(ctx, pageCmpImpl); 
		          	    	}
		          	    }
        			}else{
        				String fields = validatefield.getFields();
          	    		htmlFieldList = Arrays.asList(fields.split(";"));
        			}
        			//Getting list of metaobjects for which mo validation to be skip
        			String metaobjects = validatefield.getMetaobjects();
        			if(metaobjects != null && !metaobjects.equals("")){
        				metaobjectsList = Arrays.asList(metaobjects.split(";"));
        			}
        		}
        	}else{
        		htmlFieldList = getHtmlFields(ctx, pageCmpImpl); 
  	    	}
        	  
        	if(actionImpl.getMetaobject() != null && !"".equals(actionImpl.getMetaobject()))
        		moList = getMetaObjectList(ctx,actionImpl.getMetaobject());
        	else
        		moList = getMetaObjectList(ctx,getMetaobject());
        }
       
        //going to get list of active html fields from pageName_pageFieldsList field in context
        if(ctx.get(name+"_pageFieldsList") != null && !ctx.get(name+"_pageFieldsList").toString().equals(HtmlConstants.EMPTY)){
        	htmlFieldList = new ArrayList();
        	
        	StringTokenizer tokens = new StringTokenizer(ctx.get(name+"_pageFieldsList").toString(), ",");
        	while(tokens.hasMoreTokens()){
        		htmlFieldList.add(tokens.nextToken());
        	}
        }
        
        //going to validate parent component metaobjects also
        if(ctx.get(HtmlConstants.INET_SKIP_PARENT_VALIDATION) != null && 
        		ctx.get(HtmlConstants.INET_SKIP_PARENT_VALIDATION).toString().equalsIgnoreCase("N")){
	        
        	getParentComponent(ctx, pageCmpImpl);
	        
	        if(parentComponentList != null && parentComponentList.size() > 0){
	        	for(int i=0; i<parentComponentList.size(); i++){
	        		PagecomponentImpl rowPagecompImpl = (PagecomponentImpl)parentComponentList.get(i);
	        		
	        		if(moList == null)
	        			moList = new ArrayList();
	        		
	        		if(ComponentResources.getInstance(ctx).getComponent(rowPagecompImpl.getName()) != null){
	        			moList.addAll(ComponentResources.getInstance(ctx).getComponent(rowPagecompImpl.getName()).getMetaObjectList(ctx));
	        		}
	        		
	        		htmlFieldList.addAll(getHtmlFields(ctx, rowPagecompImpl));
	        	}
	        }
        }
        //Ended code for parent component validation
        
        if(moList == null || moList.isEmpty())
        	return;
          
        //List htmlFieldList = getHtmlFields(pageCmpImpl);
        
        try{
    		if(ProcessFlowResources.getInstance(ctx).getPagecomponent(ctx.get(Constants.INET_PAGE).toString()).getParent() != null){
    			String parent = ProcessFlowResources.getInstance(ctx).getPagecomponent(ctx.get(Constants.INET_PAGE).toString()).getParent();
    			
    			parent = parent.substring(0, parent.indexOf(".html"));
    			
    			ctx.put(HtmlConstants.INET_PARENT_PAGE, parent);
    		}
    	}catch (Exception e) {
    		//Do nothing
		}
    	
        for(int i=0; i<moList.size(); i++){
        	MetaobjectImpl moImpl = (MetaobjectImpl) moList.get(i);
            moImpl.validate(ctx,htmlFieldList, metaobjectsList);
        }
        
        //going to validate fieldsimpl.xml
        if(ctx.get(Constants.INET_SKIP_VALIDATION) == null || !ctx.get(Constants.INET_SKIP_VALIDATION).toString().equals("Y")){
	        if(ctx.get("extendedFields") != null && ctx.get("extendedFields").toString().equals("Y")){
	        	validateFieldsXML(ctx, htmlFieldList);
	        }
        }
        	
        //going to validate custom fields attached rule
        /*if(ctx.get("extendedFields") != null && ctx.get("extendedFields").toString().equals("Y"))
        	validateCustomFields(ctx);*/
    }
    
    public List getMetaObjectList(Context ctx,String attachMetaObject) throws Exception{
        
        List moList = new ArrayList();
//        String attachMetaObject = getMetaobject();
        
        if(attachMetaObject == null || "".equals(attachMetaObject))
            return moList;
        
        StringTokenizer tokenizer = new StringTokenizer(attachMetaObject,";");
        
        
        MetaDataResources metaDataResources = MetaDataResources.getInstance(ctx);
        
        while(tokenizer.hasMoreTokens()){
            String mo = tokenizer.nextToken();
            MetaobjectImpl moImpl = metaDataResources.getMetaobject(mo);
            moList.add(moImpl);
        }
        
        return moList;
    }
    
    public List getMetaObjectList(Context ctx) throws Exception{        
        ComponentImpl cmpImpl = ComponentResources.getInstance(ctx).getComponent(name);
        String attachMetaObject = cmpImpl.getMetaobject();
        
        return getMetaObjectList(ctx, attachMetaObject);
    }
    
    private String getNextPage(Context ctx) throws Exception {
    	String next = null;
    	PagecomponentImpl pageCmpImpl = null;
    	if(null != ctx.get(HtmlConstants.INET_PREVIOUSPAGE)){
    		 pageCmpImpl = ProcessFlowResources.getInstance(ctx).getPagecomponent(ctx.get(HtmlConstants.INET_PREVIOUSPAGE).toString());
    	}else 
    	  pageCmpImpl = ProcessFlowResources.getInstance(ctx).getPagecomponent(name);
        
        
//    	 if("tabs".equals(name))
//    	 {
//    	        name=ctx.get("page").toString();
//    	        return name;
//    	 }
    	 if(Constants.YES.equals(ctx.get(Constants.INET_FORM_DIRTY)))
             return name;
    	
        LinkImpl linkImpl = getLink(ctx,pageCmpImpl);
        
        if(HtmlConstants.AJAX_SUBMITFORM.equals(ctx.get(HtmlConstants.AJAX_SUBMITFORM))){
        	next = name;
        }else{
        	LinkForwardImpl forward = getForward(ctx,linkImpl);
        	next = forward.getComponent();
        }
        
        //going to check for next page component from context
        if(next != null && next.endsWith("_context") && ctx.get(next) != null && !ctx.get(next).toString().equals(HtmlConstants.EMPTY)){
        	next = ctx.get(next).toString();
        }
        
        return next;
    }
    
    private LinkForwardImpl getForward(Context ctx,LinkImpl linkImpl) throws Exception{
    	List forwradlist = linkImpl.getLinkForwardList();
    	
    	for (Object object : forwradlist) {
    		LinkForwardImpl linkForwardImpl = (LinkForwardImpl)object;
    		if(linkForwardImpl.getEval() != null && !"".equals(linkForwardImpl.getEval())){
    			if(linkForwardImpl.evaluate(ctx))
    				return linkForwardImpl;
    		}
		}
    	
    	return linkImpl.getFirstLinkForward();
    }
    
    public List getHtmlFields(Context ctx, PagecomponentImpl pageCmpImpl) throws Exception {
        List fields = new ArrayList();
        
        if(pageCmpImpl == null)
            return fields;
        
        List blocks = pageCmpImpl.getBlockList();
        
        if(blocks == null || blocks.size()==0)
            return fields;
        
        for(int i=0;i<blocks.size();i++){
            BlockImpl blockImpl = (BlockImpl)blocks.get(i);
            List blockFielsSetList = blockImpl.getBlockfieldsetList();
            if(blockFielsSetList != null){
                for(int j=0; j<blockFielsSetList.size(); j++){
                    BlockfieldsetImpl  fieldSetImpl = (BlockfieldsetImpl)blockFielsSetList.get(j);
                    List blockFieldList = fieldSetImpl.getBlockfieldList();
                    if(blockFieldList != null){
                        for(int k=0; k<blockFieldList.size(); k++){
                            BlockfieldImpl fieldImpl = (BlockfieldImpl)blockFieldList.get(k);
                            if(fieldImpl.getFieldtype() != null && Constants.LABEL.equals(fieldImpl.getFieldtype()))
                            	continue;
                            
                            fields.add(fieldImpl.getFieldname());
                            if(ctx.get("HTMLFIELDSTYPE") == null){
                            	Map map = new HashMap();
                            	map.put(fieldImpl.getFieldname(), fieldImpl.getFieldtype());
                            	ctx.put("HTMLFIELDSTYPE", map);
                            }else{
                            	Map map = (Map)ctx.get("HTMLFIELDSTYPE");
                            	map.put(fieldImpl.getFieldname(), fieldImpl.getFieldtype());
                            	ctx.put("HTMLFIELDSTYPE", map);
                            }
                        }
                    }
                }                
            }
        }
        
        return fields;
    }
    
    private LinkImpl getLink(Context ctx,PagecomponentImpl pageCmpImpl) throws Exception{
        List blockList =  pageCmpImpl.getBlockList();
        String actionId = ctx.get(HtmlConstants.INET_METHOD) == null?null:ctx.get(HtmlConstants.INET_METHOD).toString();
        LinkImpl linkImpl = null;
        if(actionId == null)
            return null;
        
        if(blockList != null)
	        for(int i=0;i<blockList.size();i++){
	            BlockImpl blockImpl = (BlockImpl)blockList.get(i);
	            linkImpl = blockImpl.getBlocklink(actionId);
	            if(linkImpl != null)
	                return linkImpl;  
	            
	            linkImpl = blockImpl.getRowlink(actionId);
	            if(linkImpl != null)
	                return linkImpl;
	        }
        
        linkImpl = pageCmpImpl.getFormlink(actionId);
                        
        return linkImpl;
    }
    
    public void populateActionParams(Context ctx, List paramList, boolean isPre, boolean isPost) throws Exception{
        if(paramList == null)
            return;
        
        for(int i=0; i<paramList.size(); i++){
            ParamImpl impl = (ParamImpl)paramList.get(i);

            if(impl.getPost() && isPost){
            	 if(impl.evaluate(ctx)){ //Checking for Rule eval
                	 impl.populate(ctx);
                }
            }else if(impl.getPre() && isPre){
            	 if(impl.evaluate(ctx)){ //Checking for Rule eval
                	 impl.populate(ctx);
                }
            }
        }
    }
    
    public void processMultiRowList(Context ctx, ActionImpl actionImpl){
		List datalist = new ArrayList();
		try {
			PagecomponentImpl pageCmpImpl = ProcessFlowResources.getInstance(ctx).getPagecomponent(ctx.get(Constants.INET_PAGE).toString());
			if(pageCmpImpl == null){	
				logger.error(ctx.get(Constants.INET_PAGE).toString()+" page does not exit in prcoesflow xml");
			}
			
			BlockImpl blockImpl = pageCmpImpl.getBlock(ctx.get("inet_link_blockid").toString());
//				PageInfo info = (PageInfo)ctx.get("stack");
//				Context oldctx = info.getContext();
		
			Object oldlist = ctx.get("old"+ctx.get("inet_link_blockid"));
			List olddatalist = (ArrayList)oldlist;
			List  blocklist = blockImpl.getBlockfieldsetList();
			BlockfieldsetImpl blockfieldsetImpl =(BlockfieldsetImpl) blocklist.get(0);
			List blockfieldlist = blockfieldsetImpl.getBlockfieldList();
			 
			for(int j=0; j<olddatalist.size(); j++){
				Map  datamap = new HashMap ();
				Map  oldmap = (Map) olddatalist.get(j);
				String chkOldValue = null;
				String chkNewValue = null;
				List ckList = new ArrayList ();
				for(int i=0; i<blockfieldlist.size(); i++){
					BlockfieldImpl blockfieldImpl = (BlockfieldImpl) blockfieldlist.get(i);					 
					String fieldname = blockfieldImpl.getFieldname();
					
					String type = blockfieldImpl.getFieldtype();
					if(type != null && "checkbox".equals(type)){
						Map checkbox = new HashMap();
						if(oldmap != null){
							chkOldValue = oldmap.get(fieldname) != null ? oldmap.get(fieldname).toString() : null;
						}
						
						chkNewValue = ctx.get(fieldname+"_"+j) != null ? ctx.get(fieldname+"_"+j).toString() : null;								
						
						checkbox.put("chkOldValue", chkOldValue);
						checkbox.put("chkNewValue", chkNewValue);
						ckList.add(checkbox);
						datamap.put(fieldname, ctx.get(fieldname+"_"+j));
					}
					else
						datamap.put(fieldname,  ctx.get(fieldname+"_"+j));
				}
					
				datamap.put("inet_mom_operation",processCheckboxList(ctx , ckList));
					
				ctx.putAll(datamap);					
				actionImpl.processRequest(ctx);
			}
		} catch (Exception e){
			logger.error("Unable to process multisave list", e);
		}
	}
    
    public String processCheckboxList(Context ctx , List ckList){
    	String inet_mom_operation = "";
    	
    	if(ckList == null)
    		return null;
    	
    	for(int i=0; i<ckList.size();i++){
    		Map checkbox = (Map) ckList.get(i);
    		Object chkOldValue = checkbox.get("chkOldValue");
    		Object chkNewValue = checkbox.get("chkNewValue");
    		
    		if((chkOldValue == null || "".equals(chkOldValue) || "0".equals(chkOldValue) || "N".equals(chkOldValue)) && chkNewValue == null 
    				&& ("NA".equals(inet_mom_operation) || "".equals(inet_mom_operation))){
    			inet_mom_operation = "NA";
			}else if((chkOldValue == null || "".equals(chkOldValue) || "0".equals(chkOldValue) || "N".equals(chkOldValue))
    				&& chkNewValue != null){
    			if("NU".equals(inet_mom_operation) || "D".equals(inet_mom_operation))
    				inet_mom_operation = "U";    			
    			else
    				inet_mom_operation = "I";
    		}else if((chkOldValue != null && !"".equals(chkOldValue) && ( "1".equals(chkOldValue) || "Y".equals(chkOldValue)))
    				&& chkNewValue != null && ("NU".equals(inet_mom_operation) || "".equals(inet_mom_operation)))
    			inet_mom_operation = "NU";
    		else if((chkOldValue != null && !"".equals(chkOldValue) && ( "1".equals(chkOldValue) || "Y".equals(chkOldValue)))
    				&& chkNewValue == null){
    			if("NU".equals(inet_mom_operation) || "I".equals(inet_mom_operation))
    				inet_mom_operation = "U";    			
    			else if("D".equals(inet_mom_operation) || "".equals(inet_mom_operation) || "NA".equals(inet_mom_operation)){
    				inet_mom_operation = "D";
    			}
    		}
    	}
    	return inet_mom_operation;
    }
    
    private void computeMetaObjectProperties(Context ctx) throws Exception {
    	List moList = new ArrayList();
        String actionId = ctx.get(HtmlConstants.INET_METHOD) == null?null:ctx.get(HtmlConstants.INET_METHOD).toString();
        
        if(actionId == null)
            moList = getMetaObjectList(ctx,getMetaobject());
        
        ActionImpl actionImpl = getAction(actionId);
        if(actionImpl != null){
      	  if(actionImpl.getMetaobject() != null && !"".equals(actionImpl.getMetaobject()))
      		  moList = getMetaObjectList(ctx,actionImpl.getMetaobject());
      	  else
      		  moList = getMetaObjectList(ctx,getMetaobject());
        }
        
        if(moList == null || moList.isEmpty())
            return;
        
        PagecomponentImpl pageCmpImpl = ProcessFlowResources.getInstance(ctx).getPagecomponent(name);
        List htmlFieldList = getHtmlFields(ctx, pageCmpImpl);
        
        for(int i = 0; i<moList.size(); i++){
            MetaobjectImpl moImpl =(MetaobjectImpl) moList.get(i);
            moImpl.computeMetaObjectProperty(ctx,htmlFieldList);
        }
    }
    
    private void validateCustomFields(Context ctx){
    	try{
    		if(ctx.get("isValidateExtendedFields") == null || !ctx.get("isValidateExtendedFields").toString().equals("Y"))
    			return;
    		
    		ctx.put("screen_name", name);
			ctx.put("client_id", SystemProperties.getInstance().getString("appl.configuration.clientid"));	
			ctx.put("releaseno_id", SystemProperties.getInstance().getString("appl.configuration.releaseno"));	
			ctx.put("releaseversionno_id", SystemProperties.getInstance().getString("appl.configuration.releaseversionno"));	
			ctx.put("projectname", ctx.getProject());	
			ctx.put("object_id", -1);	
			
			new SetParametersForStoredProcedures().setParametersInContext(ctx, "client_id,releaseno_id,releaseversionno_id,object_id");
			List fieldsList = SqlResources.getSqlMapProcessor(ctx).select("managefields.getExtendedFieldsByScreenName_p", ctx);
			if(fieldsList != null && fieldsList.size() > 0){
				for(int i=0; i<fieldsList.size(); i++){
					Map map = (Map)fieldsList.get(i);
					
					String name = map.get("name") != null ? map.get("name").toString() : null;
					String description = map.get("description") != null ? map.get("description").toString() : null;
					String isrequired = map.get("isrequired") != null ? map.get("isrequired").toString() : null;
					String type = map.get("type") != null ? map.get("type").toString() : null;
					String displaytype = map.get("displaytype") != null ? map.get("displaytype").toString() : null;
					String labelkey = map.get("labelkey") != null ? map.get("labelkey").toString() : null;
					
					if(isrequired != null && isrequired.equals("Y")){
						if(ctx.get(name) == null || ctx.get(name).toString().equals(HtmlConstants.EMPTY)){
							String msgkey = labelkey != null ? labelkey : description;
							
							msgkey = DataUtils.getLabelFromLabelConf(msgkey);
							
							if(displaytype != null && displaytype.equals("select"))
								DataUtils.populateError(ctx, name, "Please select " + msgkey);
							else
								DataUtils.populateError(ctx, name, "Please enter " + msgkey);
						}
					}
				}
			}
    		
    		/*ctx.put("component_name", name);
            Object obj = SqlResources.getSqlMapProcessor(ctx).findByKey("managefields.getManageFieldsComponentByName", ctx);
            if(obj != null && obj instanceof Map){
            	Map compMap = (Map)obj;
                  
                ctx.put("component_id", compMap.get("component_id"));
                if(compMap.get("object_id") != null){
                	String object_id = compMap.get("object_id").toString();
                    object_id = object_id.substring(object_id.indexOf(".")+1,object_id.length());
                    if(ctx.get(object_id) != null && !ctx.get(object_id).toString().equals(HtmlConstants.EMPTY))
                    	ctx.put("object_id", ctx.get(object_id));
                    else
                        ctx.put("object_id", null);
                }
                  
                new SetParametersForStoredProcedures().setParametersInContext(ctx, "component_id,object_id");
                List fieldsList = SqlResources.getSqlMapProcessor(ctx).select("managefields.getCustomFieldsList_p_java", ctx);
                if(fieldsList != null && fieldsList.size() > 0){
                	for(int i=0; i<fieldsList.size(); i++){
                    	Map row = (Map)fieldsList.get(i);
                    
                    	if((row.get("ruleid") != null && !row.get("ruleid").toString().equals(HtmlConstants.EMPTY)) &&
                        	(row.get("ruleexpression") != null && !row.get("ruleexpression").toString().equals(HtmlConstants.EMPTY))){
                        	try{
                        		RuleImpl ruleImpl = new RuleImpl();
                            	ruleImpl.setExpression(row.get("ruleexpression").toString());
                            	ruleImpl.setId(row.get("ruleid").toString());
                            	  
                            	ruleImpl.execute(ctx, null);
                        	}catch (Exception e1) {
    							logger.error("Unable to execute attached rule " + row.get("ruleid").toString() + " due to error : " + e1.getMessage());
    						}
                        }
                	}
                }
            }*/    
    	}catch (Exception e) {
    		logger.error("Unable to validate custom fields attached rule due to error : " + e.getMessage());
		}
    }
    
    //Method created to validate fieldsimpl.xml
    private void validateFieldsXML(Context ctx, List htmlFieldsList) throws Exception{
    	try{
	    	List fieldList = FieldsResources.getInstance(ctx).getFieldListByScreen(ctx.get(Constants.INET_PAGE).toString());
	    	if(fieldList != null && fieldList.size() > 0){
	    		for(int i=0; i<fieldList.size(); i++){
	    			FieldImpl fieldImpl = (FieldImpl)fieldList.get(i);
	    			
	    			String datatype = fieldImpl.getDatatype();
	    			String isextendedfield = fieldImpl.getIsextendedfield();
	    			
	    			if(isextendedfield == null || !isextendedfield.equals("Y"))
	    				continue;
	    			
	    			if(fieldImpl.getIsrequired() != null){
	    				if(fieldImpl.getIsrequired().equals("Y")){
		    				if(ctx.get(fieldImpl.getName()) == null || ctx.get(fieldImpl.getName()).toString().equals(HtmlConstants.EMPTY)){
		    					String labeldesc = fieldImpl.getLabeldesc() != null ? fieldImpl.getLabeldesc() : fieldImpl.getName();
		    					String displaytype = fieldImpl.getDisplaytype() != null ? fieldImpl.getDisplaytype() : fieldImpl.getName();
		    					
		    					labeldesc = DataUtils.getLabelFromLabelConf(labeldesc);
		    					
		    					if(displaytype != null && displaytype.equals("select"))
									DataUtils.populateError(ctx, fieldImpl.getName(), "Please select " + labeldesc);
								else
									DataUtils.populateError(ctx, fieldImpl.getName(), "Please enter " + labeldesc);
		    				}
	    				}else if(fieldImpl.getIsrequired().equals("E") && fieldImpl.getRequiredeval() != null){
	    					boolean isErrorToBeChecked = false;
	    		        	try{
	    		        		 //checking tabsconfiguration
	    		        		Map map = CacheManager.get("Tabs_Conf") == null ? null : (HashMap)CacheManager.get("Tabs_Conf");
	    		        	    if(map != null && map.containsKey(fieldImpl.getRequiredeval()) && "Y".equalsIgnoreCase(map.get(fieldImpl.getRequiredeval()).toString()))
	    		        	    	isErrorToBeChecked = true;
	    		        	     
	    		        	    //checking security
	    		        	     
	    		        	     
	    		        	    //checking rules
	    		        	    RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(fieldImpl.getRequiredeval());
	    		        	    if(ruleImpl.evaluate(ctx, null))
	    		        	    	isErrorToBeChecked = true;    
	    		        	}catch (Exception e) {
	    		        		// TODO: handle exception
	    		        	}
	    		        	 
	    		        	if(isErrorToBeChecked){
	    		        		if(ctx.get(fieldImpl.getName()) == null || ctx.get(fieldImpl.getName()).toString().equals(HtmlConstants.EMPTY)){
	    		        			String labeldesc = fieldImpl.getLabeldesc() != null ? fieldImpl.getLabeldesc() : fieldImpl.getName();
			    					String displaytype = fieldImpl.getDisplaytype() != null ? fieldImpl.getDisplaytype() : fieldImpl.getName();
			    					
			    					labeldesc = DataUtils.getLabelFromLabelConf(labeldesc);
			    					
			    					if(displaytype != null && displaytype.equals("select"))
										DataUtils.populateError(ctx, fieldImpl.getName(), "Please select " + labeldesc);
									else
										DataUtils.populateError(ctx, fieldImpl.getName(), "Please enter " + labeldesc);
	    		        		}
	    		        	}
	    				}
	    			}
	    			
	    			try{
		    			if(datatype != null && datatype.equals("I"))
		    				new PropertyImpl().validateIntegerField(ctx, ctx.get(fieldImpl.getName()), fieldImpl.getName());
		    			if(datatype != null && datatype.equals("N"))
		    				new PropertyImpl().validateNumericField(ctx, ctx.get(fieldImpl.getName()), fieldImpl.getName());
		    			if(datatype != null && datatype.equals("D"))
		    				new PropertyImpl().validateDateField(ctx, ctx.get(fieldImpl.getName()), fieldImpl.getName(), null);
	    			}catch(ValidationException e1){
	    				ArrayList errorsList = (ArrayList)ctx.get(Constants.INET_ERRORS_LIST);
	    				if(errorsList == null){
	    					errorsList = new ArrayList();
	    					ctx.put(Constants.INET_ERRORS_LIST, errorsList);
	    				}
	    				
	    				//if error already exists with same name then skip new one
	    				boolean isErrorExists = false;
	    				if(errorsList != null && errorsList.size() > 0){
	    					for(int j=0; j<errorsList.size(); j++){
	    						ValidationException ve = (ValidationException)errorsList.get(j);
	    						
	    						if(ve.getField().equals(fieldImpl.getName())){
	    							isErrorExists = true;
	    							break;
	    						}
	    					}
	    				}
	    				
	    				if(!isErrorExists){
	    					e1.setField(fieldImpl.getName());
	    					errorsList.add(e1);
	    				}
	    				
	    				ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
	    			}
	    		}
	    	}
    	}catch(Exception e){
    		logger.error("Unable to validate field for page "+ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE)+" due to error : " + e.getMessage());
    	}
    	
    	/*if(htmlFieldsList == null || htmlFieldsList.size() == 0)
    		return;
    	
    	List errorsList = (ctx.get(Constants.INET_ERRORS_LIST) != null && !ctx.get(Constants.INET_ERRORS_LIST).toString().equals(HtmlConstants.EMPTY)) ? (List)ctx.get(Constants.INET_ERRORS_LIST) : null;
    	for(int i=0; i<htmlFieldsList.size(); i++){
    		String fieldName = (String)htmlFieldsList.get(i);
    		
    		try{
    			String inet_page = ctx.get(Constants.INET_PAGE) != null ? ctx.get(Constants.INET_PAGE).toString() : HtmlConstants.EMPTY;
    			try{
    	    		if(ProcessFlowResources.getInstance(ctx).getPagecomponent(inet_page).getParent() != null){
    	    			String parent = ProcessFlowResources.getInstance(ctx).getPagecomponent(inet_page).getParent();
    	    			
    	    			parent = parent.substring(0, parent.indexOf(".html"));
    	    			
    	    			inet_page = parent;
    	    		}
    	    	}catch (Exception e) {
    	    		//Do nothing
    			}
    	    	
    			String uniqueName = inet_page + "." + fieldName + "_security";
    			ProtectedresourceImpl protectedResImpl = SecurityResources.getInstance(ctx).getProtectedResource(uniqueName);
    			if(protectedResImpl != null){
    				int accessType = SecurityResources.getInstance(ctx).getAccessType(protectedResImpl.getUniquename(), ctx);
    				if(accessType == 0)
    					continue;
    			}
    			
	    		if(FieldsResources.getInstance(ctx).getField(inet_page + "." + fieldName) != null){
	    			FieldImpl fldImpl = (FieldImpl)FieldsResources.getInstance(ctx).getField(inet_page + "." + fieldName);
	    			
	    			if(fldImpl.getType() == null || !fldImpl.getType().equalsIgnoreCase("Field"))
	    				continue;
	    			
	    			//if field is visible then needs to be validated
	    			if(fldImpl.getIsvisible() != null && fldImpl.getIsvisible().toString().equals("Y")){
		    			if(fldImpl.getIsrequired() != null && fldImpl.getIsrequired().equals("Y")){
		    				if(ctx.get(fieldName) == null || ctx.get(fieldName).toString().equals(HtmlConstants.EMPTY)){
		    					
		    					if(errorsList == null)
		    						errorsList = new ArrayList();
		    					
		    					String displayname = fldImpl.getDisplayname() != null ? fldImpl.getDisplayname() : fieldName;
		    					
		    					ValidationException exception = new ValidationException(ctx, ErrorKeys.REQUIRED_FIELD_MISSING, displayname);
		    					exception.setField(fieldName);
		    					
		    					errorsList.add(exception);   
		    					
		    					continue;
		    				}
		    			}
		    			
		    			//going to check for attached rules for validation
		    			if(fldImpl.getRuleList() != null && fldImpl.getRuleList().size() > 0){
		    				for(int j=0; j<fldImpl.getRuleList().size(); j++){
		    					com.manage.managecomponent.fields.RuleImpl rImpl = (com.manage.managecomponent.fields.RuleImpl)fldImpl.getRuleList().get(j);
		    					
		    					//bypass rule execution if rule is not of Validation type
		    					if(rImpl.getRuletype() == null || !rImpl.getRuletype().equalsIgnoreCase("V"))
		    						continue;
		    					
		    					String rulesetname = rImpl.getRulesetname();
		    					String id = rImpl.getId();
		    					
		    					RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(rulesetname + "." + id);
		    					
		    					if(!ruleImpl.evaluate(ctx, null)){
		    						String message = ruleImpl.getMessage();
		    						message = DataUtils.getMessage(ctx, message);
		    						
		    						String displayname = fldImpl.getDisplayname() != null ? fldImpl.getDisplayname() : fieldName;
		    						
		    						if(StringUtils.isBlank(message))
		    							message = displayname;
		    							
		    						ValidationException exception = new ValidationException(ctx, message, displayname);
			    					exception.setField(fieldName);
			    					
			    					if(errorsList == null)
			    						errorsList = new ArrayList();
			    					
			    					errorsList.add(exception); 
		    						
		    						continue;
		    					}
		    				}
		    			}
	    			}
	    		}
    		}catch (Exception e) {
    			//logger.error("Unable to validate field " + fieldName + " due to error : " + e.getMessage());
    		}
    		
    		ctx.put(Constants.INET_ERRORS_LIST, errorsList);
    		if(errorsList != null && errorsList.size() > 0)
    			ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
    	}*/
    }
    
    //Method created to check for fields to be encrypted based on fieldsimpl.xml
    private void checkForEncryptFields(Context ctx) throws Exception{
    	/*try{
    		List fieldsList = getHtmlFields(ctx, ProcessFlowResources.getInstance(ctx).getPagecomponent(name));
    		if(fieldsList == null || fieldsList.size() == 0)
    			return;
    		
    		for(int i=0; i<fieldsList.size(); i++){
    			String field = (String)fieldsList.get(i);
    			
    			if(ctx.get(field) == null || ctx.get(field).toString().equals(HtmlConstants.EMPTY))
    				continue;
    			
    			try{
    				String currentPageComp = ctx.get(Constants.INET_PAGE) != null ? ctx.get(Constants.INET_PAGE).toString() : HtmlConstants.EMPTY;
    				if(ProcessFlowResources.getInstance(ctx).getPagecomponent(currentPageComp).getParent() != null){
            			String parent = ProcessFlowResources.getInstance(ctx).getPagecomponent(currentPageComp).getParent();
            			
            			parent = parent.substring(0, parent.indexOf(".html"));
            			
            			currentPageComp = parent;
            		}
    				
    				field = currentPageComp + "." + field; 
    				
	    			if(FieldsResources.getInstance(ctx).getField(field) != null){
	    				FieldImpl fieldImpl = (FieldImpl)FieldsResources.getInstance(ctx).getField(field);
	    				
	    				if(Constants.YES.equals(fieldImpl.getIsencrypt())){
	    					ctx.put((String)fieldsList.get(i), new TripleDESEncryptionDecryption().encrypt(ctx.get((String)fieldsList.get(i)).toString()));
	    				}
	    			}
	    		}catch (Exception e1) {
					// TODO: handle exception
				}
    		}
    	}catch (Exception e) {
			logger.error("Error while checking for fields to be encrypted : " + e.getMessage());
		}*/
    }
    
    //Method created to check for fields to be decrypted based on fieldsimpl.xml
    private void checkForDecryptFields(Context ctx) throws Exception{
    	/*try{
    		if(ctx.get(HtmlConstants.NEXT_PAGE_COMPONENT) == null || ctx.get(HtmlConstants.NEXT_PAGE_COMPONENT).toString().equals(HtmlConstants.EMPTY))
    			return;
    		
    		List fieldsList = getHtmlFields(ctx, ProcessFlowResources.getInstance(ctx).getPagecomponent(ctx.get(HtmlConstants.NEXT_PAGE_COMPONENT).toString()));
    		if(fieldsList == null || fieldsList.size() == 0)
    			return;
    		
    		for(int i=0; i<fieldsList.size(); i++){
    			String field = (String)fieldsList.get(i);
    			
    			if(ctx.get(field) == null || ctx.get(field).toString().equals(HtmlConstants.EMPTY))
    				continue;
    			
    			try{
    				String currentPageComp = ctx.get(HtmlConstants.NEXT_PAGE_COMPONENT) != null ? ctx.get(HtmlConstants.NEXT_PAGE_COMPONENT).toString() : HtmlConstants.EMPTY;
    				if(ProcessFlowResources.getInstance(ctx).getPagecomponent(currentPageComp).getParent() != null){
            			String parent = ProcessFlowResources.getInstance(ctx).getPagecomponent(currentPageComp).getParent();
            			
            			parent = parent.substring(0, parent.indexOf(".html"));
            			
            			currentPageComp = parent;
            		}
    				
    				field = currentPageComp + "." + field;
    				
	    			if(FieldsResources.getInstance(ctx).getField(field) != null){
	    				FieldImpl fieldImpl = (FieldImpl)FieldsResources.getInstance(ctx).getField(field);
	    				
	    				if(Constants.YES.equals(fieldImpl.getIsencrypt())){
	    					ctx.put((String)fieldsList.get(i) , new TripleDESEncryptionDecryption().decrypt(ctx.get((String)fieldsList.get(i)).toString()));
	    				}
	    			}
    			}catch (Exception e1) {
					// TODO: handle exception
				}
    		}
    	}catch (Exception e) {
			logger.error("Error while checking for fields to be encrypted : " + e.getMessage());
		}*/
    }
    
    //Method created to check for default values for htmlfields available on screen
    private void checkForDefaultValues(Context ctx) throws Exception{
    	try{
    		List fieldsList = getHtmlFields(ctx, ProcessFlowResources.getInstance(ctx).getPagecomponent(name));
    		if(fieldsList == null || fieldsList.size() == 0)
    			return;
    		
    		for(int i=0; i<fieldsList.size(); i++){
    			String field = (String)fieldsList.get(i);
    			
    			try{
    				String currentPageComp = ctx.get(Constants.INET_PAGE) != null ? ctx.get(Constants.INET_PAGE).toString() : HtmlConstants.EMPTY;
    				if(ProcessFlowResources.getInstance(ctx).getPagecomponent(currentPageComp).getParent() != null){
            			String parent = ProcessFlowResources.getInstance(ctx).getPagecomponent(currentPageComp).getParent();
            			
            			parent = parent.substring(0, parent.indexOf(".html"));
            			
            			currentPageComp = parent;
            		}
    				
    				field = currentPageComp + "." + field;
    				
	    			if(FieldsResources.getInstance(ctx).getField(field) != null){
	    				FieldImpl fieldImpl = (FieldImpl)FieldsResources.getInstance(ctx).getField(field);
	    				
	    				//if field have no value in request but having defaultvalue
		    			if((ctx.get((String)fieldsList.get(i)) == null || ctx.get((String)fieldsList.get(i)).toString().equals(HtmlConstants.EMPTY)) 
		    					&& StringUtils.isNotBlank(fieldImpl.getDefaultvalue()))
		    				ctx.put((String)fieldsList.get(i), fieldImpl.getDefaultvalue());
	    			}
    			}catch (Exception e1) {
					// TODO: handle exception
				}
    		}
    	}catch (Exception e) {
			logger.error("Error while checking for fields to be encrypted : " + e.getMessage());
		}
    }
    
    //Method created to execute new workflow
    private void executeWorkflowNew(Context ctx, ActionImpl actionImpl, String actionId){
    	try{
    		String workflow_event = ctx.get(HtmlConstants.WORKFLOW_EVENT).toString();
    		
            if(ApplicationWorkflowResources.getInstance(ctx) != null && ApplicationWorkflowResources.getInstance(ctx).getEvent(workflow_event) != null){
            	EventImpl eventImpl = ApplicationWorkflowResources.getInstance(ctx).getEvent(workflow_event);
        		logger.debug("Going to execute workflow event : " + workflow_event);
        		
        		try{
	        		actionImpl.executeWorkflowEventNew(ctx, eventImpl);
	        		
	        		logger.debug("Workflow event " + workflow_event + " executed successfully");
        		}catch (Exception e) {
        			logger.error("Unable to execute event due to error : - " + e.getMessage());
				}
            }
        }catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    //Method created to execute old workflow
    private void executeWorkflow(Context ctx, ActionImpl actionImpl){
    	if(ctx.get(HtmlConstants.WORKFLOW_EVENT) != null &&
        		!ctx.get(HtmlConstants.WORKFLOW_EVENT).toString().equals(HtmlConstants.EMPTY)){
    		
        	String workflow_event = ctx.get(HtmlConstants.WORKFLOW_EVENT).toString();
        	try{
        		EventImpl eventImpl = ApplicationWorkflowResources.getInstance(ctx).getEvent(workflow_event);
        		logger.debug("Going to execute workflow event : " + workflow_event);
        		
        		actionImpl.executeWorkflowEvent(ctx, eventImpl);
        		
        		logger.debug("Workflow event " + workflow_event + " executed successfully");
        	}catch (Exception e) {
				logger.error("Unbale to get event from applicationworkflow.xml due to error : - " + e.getMessage());
			}
        }
    }
    
    //Method created to get parent component defined in processflow.xml file
    private void getParentComponent(Context ctx, PagecomponentImpl currentPageComponentImpl) throws Exception{
    	try{
	    	String parentCompName = StringUtils.isNotBlank(currentPageComponentImpl.getParent()) ? currentPageComponentImpl.getParent() : null;
	    	
	    	if(StringUtils.isBlank(parentCompName)){
	    		return;
	    	}
	    	
	    	StringTokenizer tokens = new StringTokenizer(parentCompName, ",");
	    	while(tokens.hasMoreTokens()){
	    		String token = tokens.nextToken();
    			token = token.replace(".html", HtmlConstants.EMPTY);
	    		
    			if(!isSkipParentComponent(ctx, token))
    				continue;
    			
	    		if(StringUtils.isNotBlank(token) && ProcessFlowResources.getInstance(ctx).getPagecomponent(token) != null){
		    		parentComponentList.add(ProcessFlowResources.getInstance(ctx).getPagecomponent(token));
		    		
		    		getParentComponent(ctx, token);
		    	}
	    	}
    	}catch(Exception e){
    		//Do nothing
    	}
	}
	
    //Method created to get parent component defined in processflow.xml file
	private void getParentComponent(Context ctx, String parentCompName) throws Exception{
		try{
			if(ProcessFlowResources.getInstance(ctx).getPagecomponent(parentCompName) != null){
				parentCompName = ProcessFlowResources.getInstance(ctx).getPagecomponent(parentCompName).getParent();
				
				if(StringUtils.isBlank(parentCompName)){
		    		return;
		    	}
		    	
		    	StringTokenizer tokens = new StringTokenizer(parentCompName, ",");
		    	while(tokens.hasMoreTokens()){
		    		String token = tokens.nextToken();
		    		token = token.replace(".html", HtmlConstants.EMPTY);
		    		
		    		if(!isSkipParentComponent(ctx, token))
	    				continue;
		    		
		    		if(StringUtils.isNotBlank(token) && ProcessFlowResources.getInstance(ctx).getPagecomponent(token) != null){
			    		parentComponentList.add(ProcessFlowResources.getInstance(ctx).getPagecomponent(token));
			    		
			    		getParentComponent(ctx, token);
			    	}
		    	}
			}
		}catch(Exception e){
			//Do nothing
		}
	}
	
	//Method created to check whether to skip parent component
	private boolean isSkipParentComponent(Context ctx, String parentPageName){
		if(ctx.get(HtmlConstants.INET_SKIP_PARENT_VALIDATION_PAGES) != null && 
				!ctx.get(HtmlConstants.INET_SKIP_PARENT_VALIDATION_PAGES).toString().equals(HtmlConstants.EMPTY)){
			
			String skipValidationPages = ctx.get(HtmlConstants.INET_SKIP_PARENT_VALIDATION_PAGES).toString();
			
			StringTokenizer tokens = new StringTokenizer(skipValidationPages, ",");
			while(tokens.hasMoreTokens()){
				String token = tokens.nextToken();
				parentPageName = parentPageName.replace(".html", HtmlConstants.EMPTY);
				
				if(token.equals(parentPageName))
					return false;
			}
		}
		
		return true;
	}
	
	//Method created to insert user visit information
	public void insertUserVisitInformation(Context ctx, String next){
		try{
			logger.debug("Going to execute bo ..... com.userbo.UserVisitsBO");
        	boolean foundExecute = false;
    		try{
    			ctx.put("uservisitsnextpage", next);
    			
    			Class claz = Class.forName("com.userbo.UserVisitsBO");
                Method[] methods = claz.getMethods();
                if(methods != null && methods.length > 0){
                	for(int i=0; i<methods.length; i++){
                		Method method = methods[i];
                        if(method.getName().equals("execute")){
                        	method.invoke(claz.newInstance(), ctx);
                            foundExecute = true;
                            break;
                        }
                    }
                }
                if(!foundExecute){
                	logger.info("Bo can't be execute bcoz it has no execute method.");
                }
            }catch(InvocationTargetException e){
                Throwable t = e.getCause();
                throw (Exception) e.getCause();
            }
            
    		logger.info("Bo execution done for ..... com.userbo.UserVisitsBO");
			
			
			/*Context newCtx = new Context();
			
			newCtx.setProject(ctx.getProject());
			newCtx.put("user_id", ctx.get("user_id"));
			newCtx.put("pagename", next);
			newCtx.put("last_updated_by", ctx.get("user_id"));
			newCtx.put("last_updated_ts", DateUtils.getTodaysDate());
			
			if(ComponentResources.getInstance(newCtx).getComponent(next) != null)
				newCtx.put("pagedescription", DataUtils.getLabelFromLabelConf(ComponentResources.getInstance(newCtx).getComponent(next).getStackdisplayname()));
			else
				newCtx.put("pagedescription", next);
			
			SqlResources.getSqlMapProcessor(newCtx).insert("uservisits.insert", newCtx);*/
		}catch(Exception e){
			logger.error("Unable to insert user visists information due to error : " + e.getMessage());
		}
	}
	
	//Method created to insert extended fields
	private void insertCustomFields(Context ctx) throws Exception{
		try{
			if(ctx.get(Constants.INET_FORM_DIRTY) != null && ctx.get(Constants.INET_FORM_DIRTY).toString().equals("Y"))
				return;
			
			List fieldsList = FieldsResources.getInstance(ctx).getFieldListByScreen(ctx.get(Constants.INET_PAGE).toString());
			
			Element rootElement = new Element("root");
			
			if(fieldsList != null && fieldsList.size() > 0){
				String objectcolumn = null;
				
				for(int i=0; i<fieldsList.size(); i++){
					FieldImpl fieldImpl = (FieldImpl)fieldsList.get(i);
					
					objectcolumn = fieldImpl.getObjectcolumn();
					String defaultvalue = fieldImpl.getDefaultvalue();
					String value = ctx.get(fieldImpl.getName()) != null ? ctx.get(fieldImpl.getName()).toString() : HtmlConstants.EMPTY;
					
					if(fieldImpl.getDisplaytype() != null && (fieldImpl.getDisplaytype().equalsIgnoreCase(HtmlConstants.CHECKBOX) || 
							fieldImpl.getDisplaytype().equalsIgnoreCase(HtmlConstants.RADIO)) &&
						value.equals("on")){
						value = defaultvalue != null ? defaultvalue : "Y";
					}
					
					Element ele = new Element(fieldImpl.getName());
					ele.setText(value);
					
					rootElement.addContent(ele.detach());
				}
				
				String extendedfieldsxml = new XMLOutputter(Format.getPrettyFormat()).outputString(rootElement);
				
				ctx.put("objectcolumn_id", ctx.get(objectcolumn) != null ? ctx.get(objectcolumn).toString() : -1);
				ctx.put("screen_name", ctx.get(Constants.INET_PAGE).toString());
				ctx.put("extendedfieldsxml", extendedfieldsxml);
				ctx.put("client_id", SystemProperties.getInstance().getString("appl.configuration.clientid"));	
				ctx.put("releaseno_id", SystemProperties.getInstance().getString("appl.configuration.releaseno"));	
				ctx.put("releaseversionno_id", SystemProperties.getInstance().getString("appl.configuration.releaseversionno"));
				
				new SetParametersForStoredProcedures().setParametersInContext(ctx, "objectcolumn_id,client_id,releaseno_id,releaseversionno_id");
				SqlResources.getSqlMapProcessor(ctx).insert("manageScreens.insertUpdateDeleteConfigurationExtendedFieldsData_p", ctx);
			}
		}catch(Exception e){
			logger.error("Unable to insert custom fields due to error : " + e.getMessage());
		}
	}
	
	//Method created to get extended fields data
	private void getCustomFieldsData(Context ctx) throws Exception{
		try{
			if(ctx.get(Constants.INET_FORM_DIRTY) != null && ctx.get(Constants.INET_FORM_DIRTY).toString().equals("Y"))
				return;
			
			List fieldsList = FieldsResources.getInstance(ctx).getFieldListByScreen(ctx.get(HtmlConstants.NEXT_PAGE_FOR_VIEW).toString());
			
			if(fieldsList != null && fieldsList.size() > 0){
				String objectcolumn = null;
				
				for(int i=0; i<fieldsList.size(); i++){
					FieldImpl fieldImpl = (FieldImpl)fieldsList.get(i);
					
					objectcolumn = fieldImpl.getObjectcolumn();
					break;
				}
				
				ctx.put("screen_name", ctx.get(HtmlConstants.NEXT_PAGE_FOR_VIEW).toString());
				ctx.put("object_id", ctx.get(objectcolumn) != null ? ctx.get(objectcolumn).toString() : -1);
				
				new SetParametersForStoredProcedures().setParametersInContext(ctx, "html_id,object_id");
				Map map = (Map)SqlResources.getSqlMapProcessor(ctx).findByKey("manageScreens.getConfigurationExtendedFieldsData_p", ctx);
				new WorkflowImpl().populateClobValue(map);
				
				if(map.get("dataxml") != null && !map.get("dataxml").toString().equals(HtmlConstants.EMPTY)){
					SAXBuilder builder = new SAXBuilder();
					org.jdom.Document doc = builder.build(new StringReader(map.get("dataxml").toString()));
					Element root = doc.getRootElement();
					if(root.getChildren() != null && root.getChildren().size() > 0){
						for(int i=0; i<root.getChildren().size(); i++){
							Element ele = (Element)root.getChildren().get(i);
							
							ctx.put(ele.getName(), ele.getText());
						}
					}
					
				}
			}
		}catch(Exception e){
			logger.error("Unable to get custom fields data due to error : " + e.getMessage());
		}
	}
	
	//Method created to insert userlog into database
	public void insertUserLogs(Context ctx){
        try{
        	if(ctx.get("DEBUGMSG") == null || ctx.get("DEBUGMSG").toString().equals(HtmlConstants.EMPTY))
        		return;
        	
        	ctx.put("DEBUGMSG", ctx.get("DEBUGMSG").toString().replace("'", "''"));
        	
        	Context localCtx = new Context();
			localCtx.setProject(ctx.getProject());
			localCtx.put("logs", ctx.get("DEBUGMSG"));
			localCtx.put("last_updated_by", ctx.get("user_id"));
			localCtx.put("user_id", ctx.get("user_id"));
			localCtx.put("jsessionid", ctx.get("jsessionid"));
			localCtx.put("Status", "A");
			localCtx.put("operationType", "I");
  		  	SqlResources.getSqlMapProcessor(localCtx).insert("dummy_metadata.insertUserLogs_p", localCtx);
  		  	ctx.remove("DEBUGMSG");
        }catch(Exception e){
        	logger.error("Unable to insert userlog into database due to error : " + e.getMessage());
        }
	}
}
