package com.manage.managemetadata.metadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.manage.managemetadata.util.exception.ValidationException;
import com.manage.managemetadata.versioning.VersionDataLoader;
import com.manage.managemetadata.versioning.VersionResources;
import com.util.Constants;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.InetLogger;
import com.util.StringUtils;


/**
 * @author Nilesh
 *
 * TODO don't forget to add the javadoc!
 */
public class MetaobjectImpl extends Metaobject
{
	private static InetLogger logger = InetLogger.getInetLogger(MetaobjectImpl.class);
	
	private String autoIncrementCol;
	private String logicalSeqCol;
	
	private boolean root;
	
	private List parentMetaObjects = new ArrayList();
	
	private List allKeyFields;
	private List parentKeyFields;
	
	private List uniqueProperties = new ArrayList();
	private List persistentUniqueProperties = new ArrayList();
	
	public void postParseVisit()
	{
		super.postParseVisit();
		//resolvePropertyVersions();
	}
	
	public void resolvePropertyVersions()
	{
		try
		{
			VersionResources.getInstance(getRootNodeResourceKeys());
		}catch(Exception e)
		{
			return;
		}
		
		List propVersions = getPropertyversionList();
		if(propVersions==null)
			return;
		
		for(int i=0; i<propVersions.size(); i++)
		{
			PropertyversionImpl propVersion = (PropertyversionImpl) propVersions.get(i);
			new VersionDataLoader().resolveVersionedData(propVersion);
		}
	}
	
	public void setRoot(String root)
	{
		this.root = super.convertBoolean(root);
	}
	
	public boolean isRoot()
	{
		return this.root;
	}
	
	public boolean isKey(String propName)
	{
		if(propName==null)
			return false;
			
		if(allKeyFields==null)
			setKeys();	
		
		if(allKeyFields==null)
			return false;
			
		for(int i=0; i<allKeyFields.size(); i++)
			if(propName.equals(((Property)allKeyFields.get(i)).getFieldName()))
				return true;
				
		return false;			
	}
	
	public void addParentMetaObject(Metaobject parent)
	{
		parentMetaObjects.add(parent);
	}
	
	public List getParentMetaObjects()
	{
		return parentMetaObjects;
	}
	
	// this should be done at runtime. generator prog. should not add context fields inside link
	// while creating link at runtime, check for exact version and populate context fields accordingly.
	public List getContextFields()
	{
		if(getFirstPropertyversion()==null)
			return null;
		return getFirstPropertyversion().getContextFields();
	}
	
	public void setPropertyversionMap(PropertyversionMap propertyversionMap)
	{
		super.setPropertyversionMap(propertyversionMap);
		
		populateUniquePropertiesList();
		
		try
		{
			for(int i=0; i<uniqueProperties.size(); i++)
			{
				PropertyImpl property = (PropertyImpl) uniqueProperties.get(i);
				if(autoIncrementCol==null && property.isIdentity())
					autoIncrementCol = property.getFieldName();
					
				if(logicalSeqCol==null && property.isLogicalSequence())
					logicalSeqCol = property.getFieldName();	
			}
		}catch(Exception e)
		{
			logger.trace(e);
		}
	}
	
	private void populateUniquePropertiesList()
	{
		List propsVersionList = getPropertyversionList();
		
		if(propsVersionList==null)
			return;
		
		List donePropsList = new ArrayList();
		
		for(int i=0; i<propsVersionList.size(); i++)
		{
			Propertyversion propVersion = (Propertyversion) propsVersionList.get(i);
			List propsList = propVersion.getPropertyList();
			if(propsList==null)
				continue;
			
			for(int j=0; j<propsList.size(); j++)
			{
				Property prop = (Property) propsList.get(j);
				if(donePropsList.contains(prop.getFieldName()))
					continue;
				
				donePropsList.add(prop.getFieldName());
				uniqueProperties.add(prop);
				if(prop.getPersistent())
					persistentUniqueProperties.add(prop);
			}
		}
	}
	
	public PropertyImpl getUniqueProperty(String propName)
	{
		for(int i=0; i<uniqueProperties.size(); i++)
		{
			PropertyImpl prop = (PropertyImpl) uniqueProperties.get(i);
			if(prop.getFieldName().equals(propName))
				return prop;
		}
		return null;
	}
	
/*	public List getPropertyList(IContext ctx) throws Exception
	{
		return getFirstPropertyversion().getPropertyList();
	}
	
	public PropertyImpl getProperty(IContext ctx, String field)
	{
		return getFirstPropertyversion().getProperty(field);
	}*/
	
	void setKeys()
	{
		allKeyFields = new ArrayList();
		for(int i=0; i<uniqueProperties.size(); i++)
			if (((PropertyImpl)uniqueProperties.get(i)).isPrimaryKey()) 
				allKeyFields.add(uniqueProperties.get(i));
	}
	
	public List getKeys()
	{
		if(allKeyFields == null)
			setKeys();
		
		return allKeyFields;
	}
	
	public String getAutoIncrementedCol()
	{
		return autoIncrementCol;
	}
	
	public String getLogicalSeqCol()
	{
		return logicalSeqCol;
	}

	public List getUniqueProperties()
	{
		return uniqueProperties;
	}
	
	public List getPersistentUniqueProperties()
	{
		return persistentUniqueProperties;
	}
	
    
	public void validate(Context ctx, Map fieldMap) throws Exception	{
        
        List propertyList = getFirstPropertyversion().getPropertyList();
        
        if(propertyList == null || propertyList.isEmpty())
            return;
        
        for(int i=0;i<propertyList.size();i++){
            PropertyImpl impl = (PropertyImpl)propertyList.get(i);
            
            try {
//                if(fieldMap.get(impl.getField().getName())!=null)
                	impl.validate(ctx, fieldMap);
                	
            } catch(ValidationException e) {
                ArrayList errorsList = (ArrayList) ctx.get(Constants.INET_ERRORS_LIST);
                if(errorsList==null) {
                    errorsList = new ArrayList();
                    ctx.put(Constants.INET_ERRORS_LIST, errorsList);
                }
                e.setField(impl.getField().getName());
                errorsList.add(e);
                ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
            }
        }
        
    }
    
	public void validateMetaObject(Context ctx, List metaobjectsList) throws Exception {
		
		List moValidationRuleList = getMovalidationruleList();
    	if(moValidationRuleList != null)
    	{
    		for(int i=0; i<moValidationRuleList.size(); i++)
    		{
    			MovalidationruleImpl movalidationruleImpl = (MovalidationruleImpl) moValidationRuleList.get(i);
    			String msg = movalidationruleImpl.getMessage();
    			if(msg == null)
    				msg = name;
    			
    			if(!movalidationruleImpl.evaluate(ctx))
    			{
    				ArrayList errorsList = (ArrayList) ctx.get(Constants.INET_ERRORS_LIST);
                    if(errorsList==null) {
                        errorsList = new ArrayList();
                        ctx.put(Constants.INET_ERRORS_LIST, errorsList);
                    }
                    
                    ValidationException e = new ValidationException(msg);
                    e.setField(name);
                    errorsList.add(e);
                    ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
    			}
    		}
    	}
	}
	
    public void validate(Context ctx, List htmlFieldList, List metaobjectsList) throws Exception {
    	if(ctx.get(Constants.INET_SKIP_VALIDATION) == null || !ctx.get(Constants.INET_SKIP_VALIDATION).equals("Y")){
	    	//Changed code to skip mo validation according to validatefields node
	    	if(metaobjectsList != null && metaobjectsList.size() > 0){
	    		if(!metaobjectsList.contains(getName())){
	    			validateMetaObject(ctx, metaobjectsList);
	    		}
	    	}else{
	    		validateMetaObject(ctx, metaobjectsList);
	    	}
	    	//ended code changes
    	}
    	
    	List propertyList = getFirstPropertyversion().getPropertyList();        
        if(propertyList == null || propertyList.isEmpty())
            return;
        
        for(int i=0;i<propertyList.size();i++){
            PropertyImpl impl = (PropertyImpl)propertyList.get(i);
            try {
            	String field = impl.getFieldName();
            	
            	//Added code to bypass validations if it disabled on page -- 20/01/16
            	if(!isFieldDisabled(ctx, field))
            		continue;
            	//Ended code
            	
            	//Added For trim head/tail spaces
            	Field fld = impl.getField();
            	if(fld != null && !fld.getType().equals("D") && ctx.get(field) != null){
            		ctx.put(field, StringUtils.trim(ctx.get(field) != null ? ctx.get(field).toString() : null));
            	}
        		//To init cap the string
                if(impl.getInitcap())
                	ctx.put(field, StringUtils.getInitcapString(ctx.get(field).toString()));
                
            	if(ctx.get(Constants.INET_SKIP_VALIDATION) == null || !ctx.get(Constants.INET_SKIP_VALIDATION).equals("Y")){
            		if(ctx.get(HtmlConstants.HTMLFIELDSTYPE) != null && ((Map)ctx.get(HtmlConstants.HTMLFIELDSTYPE)).get(impl.getField().getName()) != null)
            			impl.validate(ctx, ((Map)ctx.get(HtmlConstants.HTMLFIELDSTYPE)).get(impl.getField().getName()).toString());
            		else if(htmlFieldList.contains(impl.getField().getName()))
        				impl.validate(ctx);
            	}              
            }
            catch(ValidationException e) {
            	
                ArrayList errorsList = (ArrayList) ctx.get(Constants.INET_ERRORS_LIST);
                if(errorsList==null) {
                    errorsList = new ArrayList();
                    ctx.put(Constants.INET_ERRORS_LIST, errorsList);
                }
                
                //if error already exists with same name then skip new one
                boolean isErrorExists = false;
                if(errorsList != null && errorsList.size() > 0){
                	for(int j=0; j<errorsList.size(); j++){
                		ValidationException ve = (ValidationException)errorsList.get(j);
                		
                		if(ve.getField().equals(impl.getField().getName())){
                			isErrorExists = true;
                			break;
                		}
                	}
                }
                
                if(!isErrorExists){
                	e.setField(impl.getField().getName());
                	errorsList.add(e);
                }
                
                ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
            }
        }
        
    }
    
    public void resolveDropDownValue(Context ctx,List htmlFieldList) throws Exception{
    	if(getFirstPropertyversion() == null || getFirstPropertyversion().getPropertyList() == null)
        	return;
    	
        List propertyList = getFirstPropertyversion().getPropertyList();
        
        if(propertyList == null || propertyList.isEmpty())
            return;
        
        for(int i=0;i<propertyList.size();i++){
            PropertyImpl impl = (PropertyImpl)propertyList.get(i);
            
            try
            {
                if(htmlFieldList.contains(impl.getField().getName()))
                    impl.resolveDropDownValue(ctx);
                
               
                
            }catch(ValidationException e)
            {
               
            }
        }
        
    }
    
//	public MetaviewImpl resolveForwardForParent(String parentMOName) throws Exception
//	{
//		List moViewsList = getMetaviewList();
//		MetaviewImpl mainViewWithoutParent = null;
//		if(moViewsList==null)
//		    //throw new Exception("Metaviews are not defined for " + getName());
//		    return null;
//		for(int i=0; i<moViewsList.size(); i++)
//		{
//			MetaviewImpl moView = (MetaviewImpl) moViewsList.get(i);
//			if(!moView.getMainview())
//				continue;
//			
//			Metaobject parentMO = moView.getParentobject();
//			if(parentMO==null || parentMOName==null)
//			{
//				if(mainViewWithoutParent==null)
//					mainViewWithoutParent = moView;
//						
//				continue;
//			}
//			
//			if(parentMOName.equals(parentMO.getName()))
//				return moView;
//		}
//		
//		return mainViewWithoutParent;
//	}
//	public String  getDevelopercomments( )
//	{
//		return ResloveDeveloperComments.getDeveloperComment( developercomments);
//	}
//	
//	public String  getDevelopercommentsForDoc( )
//	{
//		return ResloveDeveloperComments.getDeveloperCommentsForDoc( developercomments);
//	}
    
    public void computeMetaObjectProperty(Context ctx, List htmlFieldList) throws Exception {
        List propertyList = getFirstPropertyversion().getPropertyList();        
        if(propertyList == null || propertyList.isEmpty())
            return;
        
        for(int i=0; i<propertyList.size(); i++){
            PropertyImpl impl = (PropertyImpl)propertyList.get(i);
        	if(htmlFieldList.contains(impl.getField().getName())){
        		String field = impl.getFieldName();
        		
        		//Added For trim head/tail spaces
                ctx.put(impl.getFieldName(), StringUtils.trim(ctx.get(field) != null ? ctx.get(field).toString() : null));
                
        	    //To init cap the string
                if(impl.getInitcap())
                	ctx.put(field, StringUtils.getInitcapString(ctx.get(field).toString()));
            }
        }
    }

    public void resetUniqueAndPersistentProperties(){
    	this.uniqueProperties = new ArrayList();
    	this.persistentUniqueProperties = new ArrayList();
    }
    
    //Method created to check whether field is disabled on page or not
    private boolean isFieldDisabled(Context ctx, String field){
    	if(ctx.get(HtmlConstants.PAGE_DISABLED_FIELDS) == null || ctx.get(HtmlConstants.PAGE_DISABLED_FIELDS).toString().equals(HtmlConstants.EMPTY))
    		return true;
    	
    	StringTokenizer tokens = new StringTokenizer(ctx.get(HtmlConstants.PAGE_DISABLED_FIELDS).toString(), ",");
    	while(tokens.hasMoreTokens()){
    		String token = tokens.nextToken();
    		
    		if(token.equals(field))
    			return false;
    	}
    	
    	return true;
    }
}
