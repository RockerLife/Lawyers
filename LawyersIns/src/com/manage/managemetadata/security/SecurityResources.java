package com.manage.managemetadata.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.manage.managebusinessrules.rules.RuleImpl;
import com.manage.managebusinessrules.rules.RulesResources;
import com.util.Constants;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.IResourceKeys;
import com.util.StringUtils;
import com.util.SystemProperties;
import com.util.TripleDESEncryptionDecryption;
import com.xmlobjectbuilder.Resources;

/**
 * @author Nilesh
 *
 * @version Hemantn updated on May 5th 2010
 * 
 * TODO don't forget to add the javadoc!
 */
public class SecurityResources extends Resources{
	private static Logger logger = Logger.getLogger(SecurityResources.class);
	private static final String RESOURCE_IDENTIFIER = "security";
	public static final int NOT_AVAILABLE = -1;
	public static final int HIDE = 0;
	public static final int SHOW = 1;
	public static final int READ_ONLY = 2;
	public static  String USER_ID = "user_id";
	public static  String ROLE_ID = "roles";
	public static final String RULE_ID = "ruleId";

        static{
            try{
                USER_ID = SystemProperties.getInstance().getString("USER_ID_FIELD_NAME");
                ROLE_ID = SystemProperties.getInstance().getString("ROLE_ID_FIELD_NAME");
            }catch(Exception e){
               
            }
        }
	
	private SecurityImpl security;
	
	public static SecurityResources getInstance(IResourceKeys keys) throws Exception{
		Object o = Resources.getInstance(keys, RESOURCE_IDENTIFIER);
		return o==null? null : (SecurityResources)o;
	}
	
	public SecurityResources(Object o){
		if(o==null)
			return;
		if (o instanceof SecurityImpl) {
			this.security = (SecurityImpl) o;
		}
	}
	
	public int getAccessType(String resourceName, Context ctx){
		ProtectedresourceImpl protectedresourceImpl = getProtectedResource(resourceName);
		
		if(protectedresourceImpl == null)
			return NOT_AVAILABLE;
		
		if(protectedresourceImpl != null && StringUtils.isNotBlank(protectedresourceImpl.getAccesstype()))
			return Integer.parseInt(protectedresourceImpl.getAccesstype());
		
		int accessType = NOT_AVAILABLE;
		
		int checkRoleAccess = checkRole(protectedresourceImpl, ctx.get(ROLE_ID), ctx); 
		if(checkRoleAccess != NOT_AVAILABLE)
			accessType = checkRoleAccess;
		
		int checkUserAccess = checkUser(protectedresourceImpl, ctx.get(USER_ID), ctx); 
		
		//going to check for security_userprivilege 
		if(getProtectedResource(resourceName+"_userprivilege") != null){
			ProtectedresourceImpl protectedresourceImpl1 = getProtectedResource(resourceName+"_userprivilege");
			checkUserAccess = checkUser(protectedresourceImpl1, ctx.get(USER_ID), ctx);
		}
		
		if(checkUserAccess != NOT_AVAILABLE)
			accessType = checkUserAccess;
		
		int checkRuleAccess = checkCustomeRule(protectedresourceImpl, ctx); 
		if(checkRuleAccess != NOT_AVAILABLE)
			accessType = checkRuleAccess;
		
		/*if(checkUserAccess == HIDE || checkRoleAccess == HIDE || checkRuleAccess == HIDE)
			return HIDE;
		else if(checkUserAccess == SHOW || checkRoleAccess == SHOW || checkRuleAccess == SHOW)
			return SHOW;
        else if( checkUserAccess == READ_ONLY || checkRoleAccess == READ_ONLY || checkRuleAccess == READ_ONLY)
			return READ_ONLY;
		else 
			return NOT_AVAILABLE;*/
		
		return accessType;
	}
	
	private int checkCustomeRule(ProtectedresourceImpl protectedresourceImpl, Context ctx) {
		int accessType = NOT_AVAILABLE;
		
		if(protectedresourceImpl==null)
			return accessType;
		
		List<UserdefinedImpl> userdefinedImplList = protectedresourceImpl.getOrderedUserDefinedRulesList();
		if(userdefinedImplList != null && userdefinedImplList.size() > 0){
			if(StringUtils.isBlank(protectedresourceImpl.getOperator())){
				for(int i=0; i<userdefinedImplList.size(); i++){
					UserdefinedImpl userdefinedImpl = userdefinedImplList.get(i);
					if(userdefinedImpl != null && !StringUtils.isBoolean(userdefinedImpl.getRuleid())){
						String rule = userdefinedImpl.getRuleid();
						
						try{
							RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(rule);
							if(ruleImpl.evaluate(ctx, null)){
								List<Object> accessLevelList = userdefinedImpl.getUserdefinedaccesslevelList();
								if(null != accessLevelList && accessLevelList.size() > 0){
									UserdefinedaccesslevelImpl userdefinedaccesslevelImpl = (UserdefinedaccesslevelImpl)accessLevelList.get(0);
									accessType = userdefinedaccesslevelImpl.getAccesstype();
								}
							}
						}catch (Exception e) {
							logger.error("Unable to execute rule from security.xml due to error : " + e.getMessage());
						}
					}
				}
			}else if(StringUtils.isNotBlank(protectedresourceImpl.getOperator()) && protectedresourceImpl.getOperator().equalsIgnoreCase("and")){
				for(int i=0; i<userdefinedImplList.size(); i++){
					UserdefinedImpl userdefinedImpl = userdefinedImplList.get(i);
					if(userdefinedImpl != null && !StringUtils.isBoolean(userdefinedImpl.getRuleid())){
						String rule = userdefinedImpl.getRuleid();
						
						try{
							RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(rule);
							if(ruleImpl.evaluate(ctx, null)){
								List<Object> accessLevelList = userdefinedImpl.getUserdefinedaccesslevelList();
								if(null != accessLevelList && accessLevelList.size() > 0){
									UserdefinedaccesslevelImpl userdefinedaccesslevelImpl = (UserdefinedaccesslevelImpl)accessLevelList.get(0);
									accessType = userdefinedaccesslevelImpl.getAccesstype();
								}
							}/*else{
								accessType = HIDE;
								break;
							}*/
						}catch (Exception e) {
							logger.error("Unable to execute rule from security.xml due to error : " + e.getMessage());
						}
					}
				}
			}else if(StringUtils.isNotBlank(protectedresourceImpl.getOperator()) && protectedresourceImpl.getOperator().equalsIgnoreCase("or")){
				for(int i=0; i<userdefinedImplList.size(); i++){
					UserdefinedImpl userdefinedImpl = userdefinedImplList.get(i);
					if(userdefinedImpl != null && !StringUtils.isBoolean(userdefinedImpl.getRuleid())){
						String rule = userdefinedImpl.getRuleid();
						
						try{
							RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(rule);
							if(ruleImpl.evaluate(ctx, null)){
								List<Object> accessLevelList = userdefinedImpl.getUserdefinedaccesslevelList();
								if(null != accessLevelList && accessLevelList.size() > 0){
									UserdefinedaccesslevelImpl userdefinedaccesslevelImpl = (UserdefinedaccesslevelImpl)accessLevelList.get(0);
									accessType = userdefinedaccesslevelImpl.getAccesstype();
									break;
								}
							}
						}catch (Exception e) {
							logger.error("Unable to execute rule from security.xml due to error : " + e.getMessage());
						}
					}
				}
			}
		}
		
		return accessType;
	}

	private int checkRole(ProtectedresourceImpl protectedresourceImpl, Object roleId, Context ctx){
		int accessType = NOT_AVAILABLE;
		if(protectedresourceImpl == null)
			return accessType;

		if(roleId == null || HtmlConstants.EMPTY.equals(roleId))
			return accessType;
		
		BusinessroleImpl businessroleImpl = protectedresourceImpl.getBusinessrole((String)roleId);
		if(null == businessroleImpl)
			return accessType;
		
		List<Object> accessLevelList = businessroleImpl.getRoleaccesslevelList();
		if(null != accessLevelList && accessLevelList.size() > 0){
			RoleaccesslevelImpl roleaccesslevelImpl = (RoleaccesslevelImpl)accessLevelList.get(0);
			accessType = roleaccesslevelImpl.getAccesstype();
		}
		
		return accessType;
	}

	private int checkUser(ProtectedresourceImpl protectedresourceImpl, Object userId, Context ctx) {
		int accessType = NOT_AVAILABLE;
		
		if(protectedresourceImpl == null)
			return accessType;
		
		if(userId == null || HtmlConstants.EMPTY.equals(userId))
			return accessType;
		
		try{
			AppuserImpl appuser = protectedresourceImpl.getAppuser((String)userId);
			if(null == appuser)
				return accessType;
			
			List<Object> accessLevelList = appuser.getUseraccesslevelList();
			if(null != accessLevelList && accessLevelList.size() > 0){
				UseraccesslevelImpl useraccesslevelImpl = ((UseraccesslevelImpl)accessLevelList.get(0));
				accessType = useraccesslevelImpl.getAccesstype();
			}
		}catch(Exception e){
			//do nothing
		}
		
		return accessType;
	}

	List getAccessLevels(String uniquename, List roles) throws Exception
	{
		if(roles==null || roles.size()==0)
			return null;
		
		Protectedresource resource = security.getProtectedresource(uniquename);
		if(resource==null)
			return null;
		
		List allAccessLevels = new ArrayList();
		
		for(int i=0; i<roles.size(); i++)
		{
			String roleId = (String) roles.get(i);
			BusinessroleImpl busRoleImpl = resource.getBusinessrole(roleId);
			if(busRoleImpl==null)
				continue;
			
			mergeUniqueAccessLevels(allAccessLevels, busRoleImpl.getRoleaccesslevelList());
		}
		return allAccessLevels;
	}
	
	List getAccessLevels(String uniquename, String userid)
	{
		Protectedresource resource = security.getProtectedresource(uniquename);
		if(resource==null)
			return null;
		
		Appuser user = resource.getAppuser(userid);
		if(user==null)
			return null;
		
		return user.getUseraccesslevelList();
	}
	
	List getAccessLevels(String uniquename, List roles, String userid) throws Exception{
		List allAccessLevels = new ArrayList();
		
		List busRoleAccessLevels = getAccessLevels(uniquename, roles);
		List userAccessLevels = getAccessLevels(uniquename, userid);
		
		mergeUniqueAccessLevels(allAccessLevels, busRoleAccessLevels);
		mergeUniqueAccessLevels(allAccessLevels, userAccessLevels);
		
		return allAccessLevels;
	}
	
	// need to revisit and introduce binary add instead of string concatanation.
	public String getAllAccessTypes(String uniquename, List roles, String userid) throws Exception
	{
		StringBuffer accessTypesBuffer = new StringBuffer();
		List allAccessLevels = getAccessLevels(uniquename, roles, userid);
		if(allAccessLevels==null)
			return accessTypesBuffer.toString();
		
		for(int i=0; i<allAccessLevels.size(); i++)
		{
			AccesslevelImpl accessLevel = (AccesslevelImpl) allAccessLevels.get(i);
//			accessTypesBuffer.append(accessLevel.getAccesstype()).append(Constants.COMMA);
		}
		StringUtils.removeTrailingString(accessTypesBuffer, Constants.COMMA);
		
		return accessTypesBuffer.toString();
	}

	private void mergeUniqueAccessLevels(List allAccessLevels, List accessLevels)
	{
		if(accessLevels==null)
			return;
		
		for(int i=0; i<accessLevels.size(); i++)
		{
			AccesslevelImpl accessLevel = (AccesslevelImpl) accessLevels.get(i);
			if(present(allAccessLevels, accessLevel))
				continue;
			
			allAccessLevels.add(accessLevel);
		}
	}
	
	private boolean present(List allAccessLevels, AccesslevelImpl in_accessLevel)
	{
		for(int i=0; i<allAccessLevels.size(); i++)
		{
			AccesslevelImpl accessLevel = (AccesslevelImpl) allAccessLevels.get(i);
			if(accessLevel.equals(in_accessLevel))
				return true;
		}
		return false;
	}

	
	
	public List getProtectedresourceList()
	{
		return security.getProtectedresourceList();
	}
	
	public ProtectedresourceImpl getProtectedResource(String uniqueName){
		return security.getProtectedresource(uniqueName);
	}
	
	//Method created to perform encryption/decryption on show accesstype only
	public String checkForEncryptionDecryption(Context ctx, String resourceName, String fieldType, String fieldValue){
		ProtectedresourceImpl protectedresourceImpl = null;
		if(resourceName.contains("."))
			protectedresourceImpl = getProtectedResource(resourceName);
		else
			protectedresourceImpl = getProtectedResource(ctx.get(HtmlConstants.NEXT_PAGE_COMPONENT) + "." + resourceName);
		
		fieldValue = checkUserForEncryption(protectedresourceImpl, ctx.get(USER_ID), ctx, fieldType, fieldValue); 
		fieldValue = checkRoleForEncryption(protectedresourceImpl, ctx.get(ROLE_ID), ctx, fieldType, fieldValue); 
		fieldValue = checkCustomeRuleForEncryption(protectedresourceImpl, ctx, fieldType, fieldValue); 

		return fieldValue;
	}
	
	private String checkCustomeRuleForEncryption(ProtectedresourceImpl protectedresourceImpl, Context ctx, String fieldType, String fieldValue){
		int accessType = NOT_AVAILABLE;
		
		if(protectedresourceImpl == null)
			return fieldValue;
		
		List<UserdefinedImpl> userdefinedImplList = protectedresourceImpl.getUserdefinedList();
		if(userdefinedImplList != null && userdefinedImplList.size() > 0){
			for(int i=0; i<userdefinedImplList.size(); i++){
				UserdefinedImpl userdefinedImpl = userdefinedImplList.get(i);
				if(userdefinedImpl != null && !StringUtils.isBoolean(userdefinedImpl.getRuleid())){
					String rule = userdefinedImpl.getRuleid();
					
					try{
						RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(rule);
						if(ruleImpl.evaluate(ctx, null)){
							List<Object> accessLevelList = userdefinedImpl.getUserdefinedaccesslevelList();
							if(null != accessLevelList && accessLevelList.size() > 0){
								UserdefinedaccesslevelImpl userdefinedaccesslevelImpl = (UserdefinedaccesslevelImpl)accessLevelList.get(0);
								accessType = userdefinedaccesslevelImpl.getAccesstype();
								
								if(accessType == SecurityResources.SHOW)
									fieldValue = checkForEncryptionDecryptionInner(protectedresourceImpl, userdefinedImpl, ctx, fieldType, fieldValue);
							}
						}
					}catch (Exception e) {
						logger.error("Unable to execute rule from security.xml due to error : " + e.getMessage());
					}
				}
			}
		}
		
		return fieldValue;
	}

	private String checkRoleForEncryption(ProtectedresourceImpl protectedresourceImpl, Object roleId, Context ctx, String fieldType, String fieldValue){
		int accessType = NOT_AVAILABLE;
		if(protectedresourceImpl == null)
			return fieldValue;

		if(roleId == null || HtmlConstants.EMPTY.equals(roleId))
			return fieldValue;
		
		BusinessroleImpl businessroleImpl = protectedresourceImpl.getBusinessrole((String)roleId);
		if(null == businessroleImpl)
			return fieldValue;
		
		List<Object> accessLevelList = businessroleImpl.getRoleaccesslevelList();
		if(null != accessLevelList && accessLevelList.size() > 0){
			RoleaccesslevelImpl roleaccesslevelImpl = (RoleaccesslevelImpl)accessLevelList.get(0);
			accessType = roleaccesslevelImpl.getAccesstype();
			
			if(accessType == SecurityResources.SHOW)
				fieldValue = checkForEncryptionDecryptionInner(protectedresourceImpl, businessroleImpl, ctx, fieldType, fieldValue);
		}
		
		return fieldValue;
	}

	private String checkUserForEncryption(ProtectedresourceImpl protectedresourceImpl, Object userId, Context ctx, String fieldType, String fieldValue) {
		int accessType = NOT_AVAILABLE;
		
		if(protectedresourceImpl == null)
			return fieldValue;
		
		if(userId == null || HtmlConstants.EMPTY.equals(userId))
			return fieldValue;
		
		AppuserImpl appuser = protectedresourceImpl.getAppuser((String)userId);
		if(null == appuser)
			return fieldValue;
		
		List<Object> accessLevelList = appuser.getUseraccesslevelList();
		if(null != accessLevelList && accessLevelList.size() > 0){
			UseraccesslevelImpl useraccesslevelImpl = ((UseraccesslevelImpl)accessLevelList.get(0));
			accessType = useraccesslevelImpl.getAccesstype();
			
			if(accessType == SecurityResources.SHOW)
				fieldValue = checkForEncryptionDecryptionInner(protectedresourceImpl, appuser, ctx, fieldType, fieldValue);
		}
		
		return fieldValue;
	}
	
	private String checkForEncryptionDecryptionInner(ProtectedresourceImpl protectedresourceImpl, Object obj, 
			Context ctx, String fieldType, String fieldValue){
		
		String fld = protectedresourceImpl.getUniquename();
		try{
			fld = fld.substring(fld.indexOf(".")+1, fld.length());
			if(fld.endsWith("_security"))
				fld = fld.substring(0, fld.indexOf("_security"));
			
			if(obj instanceof AppuserImpl){
				AppuserImpl appuserImpl = (AppuserImpl)obj;
				
				if(appuserImpl.getIsdecrypt() != null && appuserImpl.getIsdecrypt().equals("Y")){
					fieldValue = new TripleDESEncryptionDecryption().decrypt(fieldValue);
				}
				
				if(StringUtils.isNotBlank(appuserImpl.getIsencrypt())){
					if(appuserImpl.getIsencrypt().equals("Y")){
						String newValue = HtmlConstants.EMPTY;
						
						if(fieldType.equals(HtmlConstants.LABEL) || (appuserImpl.getIsreadonly() != null && appuserImpl.getIsreadonly().equals("Y"))){
							int unmaskeddigits = 0;
							if(appuserImpl.getUnmaskdigits() != null && !appuserImpl.getUnmaskdigits().equals(HtmlConstants.EMPTY))
								unmaskeddigits = Integer.parseInt(appuserImpl.getUnmaskdigits().toString()); 
							
							//if field value is less than or equals to unmaskeddigits length in size then just mask entire value
							if(fieldValue.length() <= unmaskeddigits){
								for(int j=0; j<fieldValue.length(); j++){
	    							newValue = newValue + "X";
	    						}
							}else{
								String maskedStr = fieldValue.substring(0, fieldValue.length()- unmaskeddigits);
								for(int j=0; j<maskedStr.length(); j++){
	    							newValue = newValue + "X";
	    						}
								
								newValue = newValue + fieldValue.substring(fieldValue.length()- unmaskeddigits, fieldValue.length());
							}
    						
    						fieldValue = newValue;
    						ctx.put(fld+"_encryptreadonly", "Y");
						}
						
						ctx.put(fld+"_encrypt", "Y");
					}
				}
			}else if(obj instanceof BusinessroleImpl){
				BusinessroleImpl businessroleImpl = (BusinessroleImpl)obj;
				
				if(businessroleImpl.getIsdecrypt() != null && businessroleImpl.getIsdecrypt().equals("Y")){
					fieldValue = new TripleDESEncryptionDecryption().decrypt(fieldValue);
				}
				
				if(StringUtils.isNotBlank(businessroleImpl.getIsencrypt())){
					if(businessroleImpl.getIsencrypt().equals("Y")){
						String newValue = HtmlConstants.EMPTY;
						
						if(businessroleImpl.getUnmaskdigits() != null && !businessroleImpl.getUnmaskdigits().equals(HtmlConstants.EMPTY)){
							int unmaskeddigits = Integer.parseInt(businessroleImpl.getUnmaskdigits().toString()); 
							
							//if field value is less than or equals to unmaskeddigits length in size then just mask entire value
							if(fieldValue.length() <= unmaskeddigits){
								for(int j=0; j<fieldValue.length(); j++){
	    							newValue = newValue + "X";
	    						}
							}else{
								String maskedStr = fieldValue.substring(0, fieldValue.length()- unmaskeddigits);
								for(int j=0; j<maskedStr.length(); j++){
	    							newValue = newValue + "X";
	    						}
								
								newValue = newValue + fieldValue.substring(fieldValue.length()- unmaskeddigits, fieldValue.length());
							}
							
							fieldValue = newValue;
							
							if(businessroleImpl.getIsreadonly() != null && businessroleImpl.getIsreadonly().equals("Y"))
								ctx.put(fld+"_encryptreadonly", "Y");
							
							if(!fieldType.equals(HtmlConstants.LABEL))
								ctx.put(fld+"_encrypttext", "Y");
						}else if(fieldType.equals(HtmlConstants.LABEL) || (businessroleImpl.getIsreadonly() != null && businessroleImpl.getIsreadonly().equals("Y"))){
							for(int j=0; j<fieldValue.length(); j++){
    							newValue = newValue + "X";
    						}
    						
    						fieldValue = newValue;
    						ctx.put(fld+"_encryptreadonly", "Y");
						}
						
						ctx.put(fld+"_encrypt", "Y");
					}
				}
			}else if(obj instanceof UserdefinedImpl){
				UserdefinedImpl userdefinedImpl = (UserdefinedImpl)obj;
				
				if(userdefinedImpl.getIsdecrypt() != null && userdefinedImpl.getIsdecrypt().equals("Y")){
					fieldValue = new TripleDESEncryptionDecryption().decrypt(fieldValue);
				}
				
				if(StringUtils.isNotBlank(userdefinedImpl.getIsencrypt())){
					if(userdefinedImpl.getIsencrypt().equals("Y")){
						String newValue = HtmlConstants.EMPTY;
						
						if(fieldType.equals(HtmlConstants.LABEL) || (userdefinedImpl.getIsreadonly() != null && userdefinedImpl.getIsreadonly().equals("Y"))){
							int unmaskeddigits = 0;
							if(userdefinedImpl.getUnmaskdigits() != null && !userdefinedImpl.getUnmaskdigits().equals(HtmlConstants.EMPTY))
								unmaskeddigits = Integer.parseInt(userdefinedImpl.getUnmaskdigits().toString()); 
							
							//if field value is less than or equals to unmaskeddigits length in size then just mask entire value
							if(fieldValue.length() <= unmaskeddigits){
								for(int j=0; j<fieldValue.length(); j++){
	    							newValue = newValue + "X";
	    						}
							}else{
								String maskedStr = fieldValue.substring(0, fieldValue.length()- unmaskeddigits);
								for(int j=0; j<maskedStr.length(); j++){
	    							newValue = newValue + "X";
	    						}
								
								newValue = newValue + fieldValue.substring(fieldValue.length()- unmaskeddigits, fieldValue.length());
							}
    						
    						fieldValue = newValue;
    						ctx.put(fld+"_encryptreadonly", "Y");
						}
						
						ctx.put(fld+"_encrypt", "Y");
					}
				}
			}
		}catch (Exception e) {
			//logger.error("Unable to encrypt/decrypt resource " + fld + " due to error : " + e.getMessage());
		}
		
		return fieldValue;
	}

}
