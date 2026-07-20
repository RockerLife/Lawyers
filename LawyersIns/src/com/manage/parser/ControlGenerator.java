package com.manage.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Parent;
import org.jdom.Text;

import com.manage.managebusinessrules.rules.RuleImpl;
import com.manage.managebusinessrules.rules.RulesResources;
import com.manage.managecomponent.applicationworkflow.ActionparamImpl;
import com.manage.managecomponent.applicationworkflow.ApplicationWorkflowResources;
import com.manage.managecomponent.applicationworkflow.EventImpl;
import com.manage.managecomponent.components.ActionImpl;
import com.manage.managecomponent.components.ComponentImpl;
import com.manage.managecomponent.components.ComponentResources;
import com.manage.managecomponent.components.ParamImpl;
import com.manage.managecomponent.fields.FieldImpl;
import com.manage.managecomponent.fields.FieldsResources;
import com.manage.managecomponent.processflow.BlockImpl;
import com.manage.managecomponent.processflow.BlockfieldImpl;
import com.manage.managecomponent.processflow.BlockfieldsetImpl;
import com.manage.managecomponent.processflow.BlocklinkImpl;
import com.manage.managecomponent.processflow.ProcessFlowResources;
import com.manage.managemetadata.functions.commonfunctions.DataUtils;
import com.manage.managemetadata.metadata.Field;
import com.manage.managemetadata.metadata.MetaDataResources;
import com.manage.managemetadata.metadata.MetaobjectImpl;
import com.manage.managemetadata.metadata.PropertyImpl;
import com.manage.managemetadata.security.BusinessroleImpl;
import com.manage.managemetadata.security.ProtectedresourceImpl;
import com.manage.managemetadata.security.RoleaccesslevelImpl;
import com.manage.managemetadata.security.SecurityResources;
import com.manage.managemetadata.security.UserdefinedImpl;
import com.manage.managemetadata.security.UserdefinedaccesslevelImpl;
import com.util.CacheManager;
import com.util.Constants;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.InetLogger;
import com.util.SystemProperties;

public class ControlGenerator {

	private static InetLogger logger = InetLogger.getInetLogger(ControlGenerator.class);


	protected BlockImpl blockImpl;
	protected MetaDataResources metadataResources;

	public ControlGenerator()
	{

	}

	public ControlGenerator(MetaDataResources metadataResources)
	{
		this.metadataResources = metadataResources;
	}

	public Element createText(Element text, String name, String id, String value, Context ctx, Map record)throws Exception
	{
		if(text==null)
			text = new Element(HtmlConstants.INPUT);

		ParseUtil.addAttribute(text, HtmlConstants.TYPE, Constants.TEXT);
		
		if(name!=null)
			ParseUtil.addAttribute(text, HtmlConstants.NAME, name);
		if(id!=null)
			ParseUtil.addAttribute(text, HtmlConstants.ID, id);
		if(value!=null)
			ParseUtil.addAttribute(text, HtmlConstants.VALUE, value);

		//Added code to create tooltiptext for component
		if(text.getAttributeValue(HtmlConstants.TITLE) != null){
			String title = text.getAttributeValue(HtmlConstants.TITLE);
			
			/*if(text.getAttributeValue(HtmlConstants.TITLE).startsWith("context_")){
				Context ctxClone = (Context)ctx.clone();
				if(record != null)
					ctxClone.putAll(record);
				
				title = title.substring(title.indexOf("context_")+8, title.length());
				
				title = ctxClone.get(title) != null ? ctxClone.get(title).toString() : title;
			}*/
			
			title = ctx.get(title) != null ? ctx.get(title).toString() : title;
			
			title = new DataUtils().getLabelFromLabelConf(title);
			
			ParseUtil.addAttribute(text, HtmlConstants.TITLE, title);
		}
		//Ended code
		
		//Added code to check for encryption/decryption from security xml
		if(text.getAttributeValue(HtmlConstants.ID) != null){
			id = text.getAttributeValue(HtmlConstants.ID);
			if(record != null && id.contains("_") && id.lastIndexOf("_") < id.length()){
				id = id.substring(id.lastIndexOf("_")+1, id.length());
				if(com.util.StringUtils.isNumeric(id))
					id = text.getAttributeValue(HtmlConstants.ID).substring(0, text.getAttributeValue(HtmlConstants.ID).lastIndexOf("_"));
			}
				
			value = SecurityResources.getInstance(ctx).checkForEncryptionDecryption(ctx, id, HtmlConstants.TEXT, value);
			if(ctx.get(id+"_encryptreadonly") != null && ctx.get(id+"_encryptreadonly").toString().equals("Y")){
				ParseUtil.addAttribute(text, HtmlConstants.DISABLED, HtmlConstants.DISABLED);
				ctx.remove(id+"_encryptreadonly");
			}
			if(ctx.get(id+"_encrypt") != null && ctx.get(id+"_encrypt").toString().equals("Y")){
				if(ctx.get(id+"_encrypttext") != null && ctx.get(id+"_encrypttext").toString().equals("Y"))
					text.setAttribute(HtmlConstants.TYPE, Constants.TEXT);
				else	
					text.setAttribute(HtmlConstants.TYPE, Constants.PASSWORD);
				
				ctx.remove(id+"_encrypt");
				ctx.remove(id+"_encrypttext");
			}
			
			text.setAttribute(HtmlConstants.VALUE, value != null ? value : HtmlConstants.EMPTY);
		}
		//Ended code
		
		//checking for masking in securotyimpl.xml
		/*if(isMaskingExists(ctx, text.getAttributeValue(HtmlConstants.ID)))
			ParseUtil.addAttribute(text, HtmlConstants.TYPE, Constants.PASSWORD);*/
		
		//going to add in pageFieldsList for validations phase
		if(text.getAttributeValue(HtmlConstants.DISABLED) == null || !text.getAttributeValue(HtmlConstants.DISABLED).equalsIgnoreCase(HtmlConstants.DISABLED)){
			if(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) != null){
				String NEXT_PAGE_FOR_PARSE = ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString();
				
				if(ctx.get(NEXT_PAGE_FOR_PARSE+"_pageFieldsList") == null){
					ctx.put(NEXT_PAGE_FOR_PARSE+"_pageFieldsList", text.getAttributeValue(HtmlConstants.NAME)+"_FIELDTYPE=text");
				}else{
					ctx.put(NEXT_PAGE_FOR_PARSE+"_pageFieldsList", ctx.get(NEXT_PAGE_FOR_PARSE+"_pageFieldsList") + "," + text.getAttributeValue(HtmlConstants.NAME)+"_FIELDTYPE=text");
				}
			}
		}
		//Ended code
		
		//Added code to show tooltip -- 29/8/2016 vikask
		if(SystemProperties.getInstance().getProperty("appl.configuration.applicable") != null &&
				SystemProperties.getInstance().getProperty("appl.configuration.applicable").toString().equals("Y") &&
				SystemProperties.getInstance().getProperty("appl.configuration.showtooltip") != null && 
				SystemProperties.getInstance().getProperty("appl.configuration.showtooltip").toString().equals("Y")){
			if(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) != null && (ctx.get(text.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded") == null || 
					!ctx.get(text.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded").equals("Y"))){
				if(CacheManager.get(HtmlConstants.PAGEFIELDSMAP) != null){
					Map map = (Map)CacheManager.get(HtmlConstants.PAGEFIELDSMAP);
					if(map.containsKey(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) + "." + text.getAttributeValue(HtmlConstants.NAME))){
						Element imgElement = new Element(HtmlConstants.IMG);
						imgElement.setAttribute(HtmlConstants.SRC, "images/icons/tooltipField.png");
						imgElement.setAttribute(HtmlConstants.CSS_CLASS, "tooltipField");
						imgElement.setAttribute(HtmlConstants.TITLE, map.get(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) + "." + text.getAttributeValue(HtmlConstants.NAME)).toString());
						text.getParent().addContent(imgElement.detach());
						
						//set flag to avoid duplicity of tooltip for select field because it is already created from processMetaobject() above
						ctx.put(text.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded", "Y");
					}
				}
			}
		}
		//Ended code
		
		//Added code to generate css class based on isrequired in metaobject property
		if(text.getParent() == null)
			return text;
		
		//Added code to add tooltip icon next to field
		try{
			if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".showtooltip") != null && 
					SystemProperties.getInstance().getString("appl."+ctx.getProject()+".showtooltip").equals("Y")){
				//String tooltip = MetaDataResources.getInstance(ctx).getField(text.getAttributeValue(HtmlConstants.NAME)).getTiptext();
				String tooltip = FieldsResources.getInstance(ctx).getField(text.getAttributeValue(HtmlConstants.NAME)).getTooltip();
				if(StringUtils.isNotBlank(tooltip)){
					Element aEle = new Element(HtmlConstants.LINK);
					aEle.setAttribute(HtmlConstants.TITLE, tooltip);
					aEle.setAttribute(HtmlConstants.CSS_CLASS, "icoTooltip icons");
					aEle.setAttribute(HtmlConstants.HREF, "#");
					
					((Element)text.getParent()).addContent(aEle.detach());
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		String cssClass = ((Element)text.getParent()).getAttributeValue(HtmlConstants.CSS_CLASS);
		if(StringUtils.isBlank(cssClass))
			cssClass = "nonMandatory";
		else
			cssClass = cssClass + " " + "nonMandatory";
		
		//text.setAttribute(HtmlConstants.CSS_CLASS, cssClass);
		((Element)text.getParent()).setAttribute(HtmlConstants.CSS_CLASS, cssClass);
		
		try{
			if(ctx.get(Constants.INET_PAGE).toString().equals(HtmlConstants.MANAGE_TESTHARNESS))
				return text;
			
			List fieldsList = FieldsResources.getInstance(ctx).getFieldListByScreen(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString());
			if(fieldsList != null && fieldsList.size() > 0){
				for(int j=0; j<fieldsList.size(); j++){
					FieldImpl fldImpl = (FieldImpl)fieldsList.get(j);
					
					String fieldname = fldImpl.getName() != null ? fldImpl.getName() : null;
					if(fieldname.equals(text.getAttributeValue(HtmlConstants.NAME))){
						String isrequired = fldImpl.getIsrequired() != null ? fldImpl.getIsrequired() : null;
						String requiredeval = fldImpl.getRequiredeval() != null ? fldImpl.getRequiredeval() : null;
						
						if(isrequired != null && isrequired.equals("E")){
							boolean isErrorToBeChecked = false;
							
							//checking tabsconfiguration
			           		Map map = CacheManager.get("Tabs_Conf") == null ? null : (HashMap)CacheManager.get("Tabs_Conf");
			           	    if(map != null && map.containsKey(requiredeval) && "Y".equalsIgnoreCase(map.get(requiredeval).toString())){
			           	    	isErrorToBeChecked = true;
			           	    }
			           	     
			           	    //checking security
			           	     
			           	     
			           	    //checking rules
			           	    RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(requiredeval);
			           	    if(ruleImpl != null && ruleImpl.evaluate(ctx, null)){
			           	    	isErrorToBeChecked = true;
			           	    }
			           	     
			           	    if(isErrorToBeChecked){
			           	    	if(StringUtils.isBlank(cssClass))
			           				cssClass = "mandatory";
			           	    	else
			           	    		cssClass = cssClass.replace("nonMandatory", "").replace("mandatory", "") + " " + "mandatory";
			           	    	
			           	    	((Element)text.getParent()).setAttribute(HtmlConstants.CSS_CLASS, cssClass);
			           	     }
						}
						
						return text;
					}
				}
			}
			
			ComponentImpl componentImpl = ComponentResources.getInstance(ctx).getComponent(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString());
			List moList = componentImpl.getMetaObjectList(ctx);
			
			if(componentImpl.getAction(ctx.get(HtmlConstants.INET_METHOD).toString()) != null && componentImpl.getAction(ctx.get(HtmlConstants.INET_METHOD).toString()).getMetaobject() != null){
				moList = componentImpl.getMetaObjectList(ctx, componentImpl.getAction(ctx.get(HtmlConstants.INET_METHOD).toString()).getMetaobject());
			}
			
			if(moList != null && moList.size() > 0){
				for(int i=0; i<moList.size(); i++){
					MetaobjectImpl impl = (MetaobjectImpl)moList.get(i);
					
					List propList = impl.getFirstPropertyversion().getPropertyList();
					if(propList != null && propList.size() > 0){
						for(int j=0; j<propList.size(); j++){
							PropertyImpl pImpl = (PropertyImpl)propList.get(j);
							
							if(pImpl.getFieldName().equals(text.getAttributeValue(HtmlConstants.NAME))){
								if(pImpl.getIsrequired() != null && pImpl.getIsrequired().equals("E")){
									boolean isErrorToBeChecked = false;
									
									//checking tabsconfiguration
					           		 Map map = CacheManager.get("Tabs_Conf") == null ? null : (HashMap)CacheManager.get("Tabs_Conf");
					           	     if(map != null && map.containsKey(pImpl.getRequiredeval()) && "Y".equalsIgnoreCase(map.get(pImpl.getRequiredeval()).toString())){
					           	    	isErrorToBeChecked = true;
					           	     }
					           	     
					           	     //checking security
					           	     
					           	     
					           	     //checking rules
					           	     RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(pImpl.getRequiredeval());
					           	     if(ruleImpl != null && ruleImpl.evaluate(ctx, null)){
					           	    	isErrorToBeChecked = true;
					           	     }
					           	     
					           	     if(isErrorToBeChecked){
					           	    	if(StringUtils.isBlank(cssClass))
					           				cssClass = "mandatory";
					           	    	else
					           	    		cssClass = cssClass.replace("nonMandatory", "").replace("mandatory", "") + " " + "mandatory";
					           	    	
					           	    	//text.setAttribute(HtmlConstants.CSS_CLASS, cssClass);
					           	    	((Element)text.getParent()).setAttribute(HtmlConstants.CSS_CLASS, cssClass);
					           	     }
								}/*else if(pImpl.getIsrequired() != null && pImpl.getIsrequired().equals("Y")){
									if(StringUtils.isBlank(cssClass))
				           				cssClass = "mandatory";
				           	    	else
				           	    		cssClass = cssClass.replace("nonMandatory", "") + " " + "mandatory";
				           	    	
									text.setAttribute(HtmlConstants.CSS_CLASS, cssClass);
								}*/
								
								return text;
							}
						}
					}
				}
			}
		}catch(Exception e){
			logger.error(ctx, "Unable to generate css class for text box due to error : " + e.getMessage());
		}
		
		return text;
	}
	
	public Element createPasswordField(Element text, String name, String id, String value, Context ctx, Map record)throws Exception{
		if(text==null)
			text = new Element(HtmlConstants.INPUT);

		ParseUtil.addAttribute(text, HtmlConstants.TYPE, Constants.PASSWORD);
		if(name!=null)
			ParseUtil.addAttribute(text, HtmlConstants.NAME, name);
		if(id!=null)
			ParseUtil.addAttribute(text, HtmlConstants.ID, id);
		if(value!=null)
			ParseUtil.addAttribute(text, HtmlConstants.VALUE, value);

		//Added code to create tooltiptext for component
		if(text.getAttributeValue(HtmlConstants.TITLE) != null){
			String title = text.getAttributeValue(HtmlConstants.TITLE);
			
			/*if(text.getAttributeValue(HtmlConstants.TITLE).startsWith("context_")){
				Context ctxClone = (Context)ctx.clone();
				if(record != null)
					ctxClone.putAll(record);
				
				title = title.substring(title.indexOf("context_")+8, title.length());
				
				title = ctxClone.get(title) != null ? ctxClone.get(title).toString() : title;
			}*/
			
			title = ctx.get(title) != null ? ctx.get(title).toString() : title;
			
			title = new DataUtils().getLabelFromLabelConf(title);
			
			ParseUtil.addAttribute(text, HtmlConstants.TITLE, title);
		}
		//Ended code
		
		//going to add in pageFieldsList for validations phase
		if(text.getAttributeValue(HtmlConstants.DISABLED) == null || !text.getAttributeValue(HtmlConstants.DISABLED).equalsIgnoreCase(HtmlConstants.DISABLED)){
			if(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) != null){
				String NEXT_PAGE_FOR_PARSE = ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString();
				
				if(ctx.get(NEXT_PAGE_FOR_PARSE+"_pageFieldsList") == null){
					ctx.put(NEXT_PAGE_FOR_PARSE+"_pageFieldsList", text.getAttributeValue(HtmlConstants.NAME)+"_FIELDTYPE=text");
				}else{
					ctx.put(NEXT_PAGE_FOR_PARSE+"_pageFieldsList", ctx.get(NEXT_PAGE_FOR_PARSE+"_pageFieldsList") + "," + text.getAttributeValue(HtmlConstants.NAME)+"_FIELDTYPE=text");
				}
			}
		}
		//Ended code
		
		//Added code to show tooltip -- 29/8/2016 vikask
		if(SystemProperties.getInstance().getProperty("appl.configuration.applicable") != null &&
				SystemProperties.getInstance().getProperty("appl.configuration.applicable").toString().equals("Y") &&
				SystemProperties.getInstance().getProperty("appl.configuration.showtooltip") != null && 
				SystemProperties.getInstance().getProperty("appl.configuration.showtooltip").toString().equals("Y")){
			if(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) != null && (ctx.get(text.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded") == null || 
					!ctx.get(text.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded").equals("Y"))){
				if(CacheManager.get(HtmlConstants.PAGEFIELDSMAP) != null){
					Map map = (Map)CacheManager.get(HtmlConstants.PAGEFIELDSMAP);
					if(map.containsKey(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) + "." + text.getAttributeValue(HtmlConstants.NAME))){
						Element imgElement = new Element(HtmlConstants.IMG);
						imgElement.setAttribute(HtmlConstants.SRC, "images/icons/tooltipField.png");
						imgElement.setAttribute(HtmlConstants.CSS_CLASS, "tooltipField");
						imgElement.setAttribute(HtmlConstants.TITLE, map.get(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) + "." + text.getAttributeValue(HtmlConstants.NAME)).toString());
						text.getParent().addContent(imgElement.detach());
						
						//set flag to avoid duplicity of tooltip for select field because it is already created from processMetaobject() above
						ctx.put(text.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded", "Y");
					}
				}
			}
		}
		//Ended code
		
		return text;
	}
	
	public Element createHiddenText(Element hidden, String name, String id, String value, Context ctx) throws Exception{
		if(hidden==null)
			hidden = new Element(HtmlConstants.INPUT);

		ParseUtil.addAttribute(hidden, HtmlConstants.TYPE, HtmlConstants.HIDDEN);
		if(name!=null)
			ParseUtil.addAttribute(hidden, HtmlConstants.NAME, name);
		if(id!=null)
			ParseUtil.addAttribute(hidden, HtmlConstants.ID, id);
		if(value!=null)
			ParseUtil.addAttribute(hidden, HtmlConstants.VALUE, value);

		//going to add in pageFieldsList for validations phase
		if(hidden.getAttributeValue(HtmlConstants.DISABLED) == null || !hidden.getAttributeValue(HtmlConstants.DISABLED).equalsIgnoreCase(HtmlConstants.DISABLED)){
			if(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) != null){
				String NEXT_PAGE_FOR_PARSE = ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString();
				
				if(ctx.get(NEXT_PAGE_FOR_PARSE+"_pageFieldsList") == null){
					ctx.put(NEXT_PAGE_FOR_PARSE+"_pageFieldsList", hidden.getAttributeValue(HtmlConstants.NAME));
				}else{
					ctx.put(NEXT_PAGE_FOR_PARSE+"_pageFieldsList", ctx.get(NEXT_PAGE_FOR_PARSE+"_pageFieldsList") + "," + hidden.getAttributeValue(HtmlConstants.NAME));
				}
			}
		}
		//Ended code
		
		return hidden;
	}

	public Element createLabel(Element label, String name, String id, String value, String width, String height, 
			Context ctx, Map record) throws Exception {
		
		if(label == null)
			label = new Element(Constants.LABEL);

		if(name != null)
			ParseUtil.addAttribute(label, HtmlConstants.NAME, name);
		
		if(id != null)
			ParseUtil.addAttribute(label, HtmlConstants.ID, id);
		
		//Added code to add css based on security
       	String cssClass = label.getAttributeValue(HtmlConstants.CSS_CLASS);
       	if(StringUtils.isNotBlank(cssClass)){
           	String finalCssClass = null;
           	
           	StringTokenizer tokens = new StringTokenizer(cssClass, " ");
           	while(tokens.hasMoreTokens()){
           		String token = tokens.nextToken();
           		
           		if(StringUtils.isNotBlank(token)){
           			String rowCssClass = token;
           			
           			if(!rowCssClass.contains("."))
           				rowCssClass = (ctx.get(HtmlConstants.NEXT_PAGE_COMPONENT) != null ? ctx.get(HtmlConstants.NEXT_PAGE_COMPONENT).toString() : HtmlConstants.EMPTY) + "." + token; 
               		
           			SecurityResources.getInstance(ctx).getAccessType(rowCssClass, ctx);
           			
               		rowCssClass = ctx.get(HtmlConstants.LABELSTYLE) != null ? ctx.get(HtmlConstants.LABELSTYLE).toString() : token;
               		
               		ctx.remove(HtmlConstants.LABELSTYLE);
               		finalCssClass = finalCssClass == null ? rowCssClass : finalCssClass + " " + rowCssClass; 
               	}
           	}
           	
           	label.setAttribute(HtmlConstants.CSS_CLASS, finalCssClass);
       	}
       	//Ended code
		
		//Added code to check for encryption/decryption from security xml
		if(label.getAttributeValue(HtmlConstants.ID) != null){
			value = SecurityResources.getInstance(ctx).checkForEncryptionDecryption(ctx, label.getAttributeValue(HtmlConstants.ID), Constants.LABEL, value);
			if(ctx.get(label.getAttributeValue(HtmlConstants.ID)+"_encryptreadonly") != null && ctx.get(label.getAttributeValue(HtmlConstants.ID)+"_encryptreadonly").toString().equals("Y")){
				ctx.remove(label.getAttributeValue(HtmlConstants.ID)+"_encryptreadonly");
			}
			if(ctx.get(label.getAttributeValue(HtmlConstants.ID)+"_encrypt") != null && ctx.get(label.getAttributeValue(HtmlConstants.ID)+"_encrypt").toString().equals("Y")){
				ctx.remove(label.getAttributeValue(HtmlConstants.ID)+"_encrypt");
			}
		}
		//Ended code
		
		//checking for masking in securityimpl.xml
		/*if(isMaskingExists(ctx, label.getAttributeValue(HtmlConstants.ID))){
			if(value != null && value.length() > 0){
				String newValue = null;
				
				for(int i=0; i<value.length(); i++){
					if(newValue == null)
						newValue = "X";
					else
						newValue = newValue + "X";
				}
				
				value = newValue;
			}
		}*/
		
		//Added code to add <div> instead of <label> to apply wrap type formatting on div ---- by vikas 2 Sep. 2013
		if(label != null && label.getAttributeValue(HtmlConstants.ID) != null && 
				label.getAttributeValue(HtmlConstants.ID).startsWith(HtmlConstants.BLOCK_FIELD)){
			String labelId = label.getAttributeValue(HtmlConstants.ID);
			labelId = labelId.substring(labelId.lastIndexOf(HtmlConstants.BLOCK_FIELD)+12, labelId.length());
			
			Element labelCloneElement = ParseUtil.createCopyForElement(label);
			
			Element divElement = new Element(HtmlConstants.DIV);
			
			List attrList = labelCloneElement.getAttributes();
			if(attrList != null && attrList.size() > 0){
				for(int i=0; i<attrList.size(); i++){
					Attribute attr = (Attribute)attrList.get(i);
					
					Attribute attrClone = (Attribute)attr.clone();
					divElement.setAttribute(attrClone.detach());
				}
			}
			
			divElement.setAttribute(HtmlConstants.ID, labelId);
			divElement.addContent(value);
			
			Parent parent = label.getParent();
			parent.removeContent(label.detach());
			parent.addContent(divElement);
			
			return divElement;
		}

		if(value != null && !"".equals(value)){
			label.removeContent();
			label.addContent(value);
		}

		//Added code to create tooltiptext for component
		if(label.getAttributeValue(HtmlConstants.TITLE) != null){
			String title = label.getAttributeValue(HtmlConstants.TITLE);
			
			/*if(label.getAttributeValue(HtmlConstants.TITLE).startsWith("context_")){
				Context ctxClone = (Context)ctx.clone();
				if(record != null)
					ctxClone.putAll(record);
				
				title = title.substring(title.indexOf("context_")+8, title.length());
				
				title = ctxClone.get(title) != null ? ctxClone.get(title).toString() : title;
			}*/
			
			title = ctx.get(title) != null ? ctx.get(title).toString() : title;
			
			title = new DataUtils().getLabelFromLabelConf(title);
			
			ParseUtil.addAttribute(label, HtmlConstants.TITLE, title);
		}
		//Ended code
		
		//going to check for label childrens
		if(label.getContent() != null && label.getContent().size() > 0){
			String finalLabelValue = null;
			for(int i=0; i<label.getContent().size(); i++){
				Object obj = label.getContent().get(i);
				
				if(obj instanceof Text){
					Text textObj = (Text)obj;
					String labelName = textObj.getText();
					String labelValue = ctx.get(labelName) != null ? ctx.get(labelName).toString() : labelName;
					
					finalLabelValue = (finalLabelValue != null) ? (finalLabelValue + " " + labelValue) : labelValue;
				}else if(obj instanceof Element){
					Element eleObj = (Element)obj;
					
					String labelName = eleObj.getText();
					String labelValue = ctx.get(labelName) != null ? ctx.get(labelName).toString() : labelName;
					
					finalLabelValue = (finalLabelValue != null) ? (finalLabelValue + " " + labelValue) : labelValue;
				}
			}
			
			label.removeContent();
			label.setText(finalLabelValue);
			
			//checking if id found as complete label conf key
			//going to check for label
			Map<String,String> map = CacheManager.get(HtmlConstants.LABELS_CONF) == null ? null : (HashMap)CacheManager.get(HtmlConstants.LABELS_CONF);
			
			if(map != null && map.containsKey(finalLabelValue)){
				finalLabelValue = DataUtils.getLabelFromLabelConf(finalLabelValue);
				label.setText(finalLabelValue);
			}else if(map != null && finalLabelValue != null && !map.containsKey(finalLabelValue)){ //checking if id have partial components in label conf key
				if(!finalLabelValue.equals(DataUtils.getLabelFromLabelConf(finalLabelValue)))
					label.setText(DataUtils.getLabelFromLabelConf(finalLabelValue));
			}
		}
		//Ended code
		
		return label;
	}

	public Element createTextArea(Element textarea, String name, String id, String value, String width, String height, boolean isTextAreaExist, 
			int index, Context ctx, Map record) throws Exception
	{
		if(textarea==null)
			textarea = new Element(Constants.TEXTAREA);

		if(name!=null)
			ParseUtil.addAttribute(textarea, HtmlConstants.NAME, name);
		if(id!=null)
			ParseUtil.addAttribute(textarea, HtmlConstants.ID, id);

		if(width!=null)
			ParseUtil.addAttribute(textarea, HtmlConstants.WIDTH, width);
		if(height!=null)
			ParseUtil.addAttribute(textarea, HtmlConstants.HEIGHT, height);
		
		//Added to handle Textarea remaining characters 
		if(isTextAreaExist){
			int count = 1024;
			String divId = HtmlConstants.DIVTEXTAREACHARCOUNTMSG+"_"+index;
			if(textarea.getAttribute("onkeyup") != null){
				String onKeyUpValue = textarea.getAttribute("onkeyup").getValue(); 
				String strCount = onKeyUpValue.substring(onKeyUpValue.lastIndexOf(",")+1, onKeyUpValue.lastIndexOf(")"));
				if(StringUtils.isNumeric(strCount))
					count = Integer.parseInt(strCount);
				ParseUtil.addAttribute(textarea, "onkeyup", "updateTextAreaCharCountMsg(this,'"+divId+"',"+ count +")");
			}
			
			if(textarea.getAttribute("onfocus") != null){
				String onKeyUpValue = textarea.getAttribute("onfocus").getValue(); 
				String strCount = onKeyUpValue.substring(onKeyUpValue.lastIndexOf(",")+1, onKeyUpValue.lastIndexOf(")"));
				if(StringUtils.isNumeric(strCount))
					count = Integer.parseInt(strCount);
				ParseUtil.addAttribute(textarea, "onfocus", "showTextAreaCharCountMsg(this,'"+divId+"',"+ count +")");
			}
			
			if(textarea.getAttribute("onblur") != null){
				String onKeyUpValue = textarea.getAttribute("onblur").getValue(); 
				String strCount = onKeyUpValue.substring(onKeyUpValue.lastIndexOf(",")+1, onKeyUpValue.lastIndexOf(")"));
				if(StringUtils.isNumeric(strCount))
					count = Integer.parseInt(strCount);
				ParseUtil.addAttribute(textarea, "onblur", "hideTextAreaCharCountMsg(this,'"+divId+"',"+ count +")");
			}
			
		}
		//Ended
		
		if(value!=null && !"".equals(value))
			textarea.addContent(value);

		//Added code to create tooltiptext for component
		if(textarea.getAttributeValue(HtmlConstants.TITLE) != null){
			String title = textarea.getAttributeValue(HtmlConstants.TITLE);
			
			/*if(textarea.getAttributeValue(HtmlConstants.TITLE).startsWith("context_")){
				Context ctxClone = (Context)ctx.clone();
				if(record != null)
					ctxClone.putAll(record);
				
				title = title.substring(title.indexOf("context_")+8, title.length());
				
				title = ctxClone.get(title) != null ? ctxClone.get(title).toString() : title;
			}*/
			
			title = ctx.get(title) != null ? ctx.get(title).toString() : title;
			
			title = new DataUtils().getLabelFromLabelConf(title);
			
			ParseUtil.addAttribute(textarea, HtmlConstants.TITLE, title);
		}
		//Ended code
		
		//going to add in pageFieldsList for validations phase
		if(textarea.getAttributeValue(HtmlConstants.DISABLED) == null || !textarea.getAttributeValue(HtmlConstants.DISABLED).equalsIgnoreCase(HtmlConstants.DISABLED)){
			if(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) != null){
				String NEXT_PAGE_FOR_PARSE = ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString();
				
				if(ctx.get(NEXT_PAGE_FOR_PARSE+"_pageFieldsList") == null){
					ctx.put(NEXT_PAGE_FOR_PARSE+"_pageFieldsList", textarea.getAttributeValue(HtmlConstants.NAME)+"_FIELDTYPE=textarea");
				}else{
					ctx.put(NEXT_PAGE_FOR_PARSE+"_pageFieldsList", ctx.get(NEXT_PAGE_FOR_PARSE+"_pageFieldsList") + "," + textarea.getAttributeValue(HtmlConstants.NAME)+"_FIELDTYPE=textarea");
				}
			}
		}
		//Ended code
		
		//Added code to show tooltip -- 29/8/2016 vikask
		if(SystemProperties.getInstance().getProperty("appl.configuration.applicable") != null &&
				SystemProperties.getInstance().getProperty("appl.configuration.applicable").toString().equals("Y") &&
				SystemProperties.getInstance().getProperty("appl.configuration.showtooltip") != null && 
				SystemProperties.getInstance().getProperty("appl.configuration.showtooltip").toString().equals("Y")){
			if(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) != null && (ctx.get(textarea.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded") == null || 
					!ctx.get(textarea.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded").equals("Y"))){
				if(CacheManager.get(HtmlConstants.PAGEFIELDSMAP) != null){
					Map map = (Map)CacheManager.get(HtmlConstants.PAGEFIELDSMAP);
					if(map.containsKey(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) + "." + textarea.getAttributeValue(HtmlConstants.NAME))){
						Element imgElement = new Element(HtmlConstants.IMG);
						imgElement.setAttribute(HtmlConstants.SRC, "images/icons/tooltipField.png");
						imgElement.setAttribute(HtmlConstants.CSS_CLASS, "tooltipField");
						imgElement.setAttribute(HtmlConstants.TITLE, map.get(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) + "." + textarea.getAttributeValue(HtmlConstants.NAME)).toString());
						textarea.getParent().addContent(imgElement.detach());
						
						//set flag to avoid duplicity of tooltip for select field because it is already created from processMetaobject() above
						ctx.put(textarea.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded", "Y");
					}
				}
			}
		}
		//Ended code
		
		//Added code to generate css class based on isrequired in metaobject property
		if(textarea.getParent() == null)
			return textarea;
		
		//Added code to add tooltip icon next to field
		try{
			if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".showtooltip") != null && 
					SystemProperties.getInstance().getString("appl."+ctx.getProject()+".showtooltip").equals("Y")){
				//String tooltip = MetaDataResources.getInstance(ctx).getField(textarea.getAttributeValue(HtmlConstants.NAME)).getTiptext();
				String tooltip = FieldsResources.getInstance(ctx).getField(textarea.getAttributeValue(HtmlConstants.NAME)).getTooltip();
				if(StringUtils.isNotBlank(tooltip)){
					Element aEle = new Element(HtmlConstants.LINK);
					aEle.setAttribute(HtmlConstants.TITLE, tooltip);
					aEle.setAttribute(HtmlConstants.CSS_CLASS, "icoTooltip icons");
					aEle.setAttribute(HtmlConstants.HREF, "#");
					
					((Element)textarea.getParent()).addContent(aEle.detach());
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
				
		String cssClass = ((Element)textarea.getParent()).getAttributeValue(HtmlConstants.CSS_CLASS);
		if(StringUtils.isBlank(cssClass))
			cssClass = "nonMandatory";
		else
			cssClass = cssClass + " " + "nonMandatory";
		
		//textarea.setAttribute(HtmlConstants.CSS_CLASS, cssClass);
		((Element)textarea.getParent()).setAttribute(HtmlConstants.CSS_CLASS, cssClass);
		
		try{
			if(ctx.get(Constants.INET_PAGE).toString().equals(HtmlConstants.MANAGE_TESTHARNESS))
				return textarea;
			
			List fieldsList = FieldsResources.getInstance(ctx).getFieldListByScreen(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString());
			if(fieldsList != null && fieldsList.size() > 0){
				for(int j=0; j<fieldsList.size(); j++){
					FieldImpl fldImpl = (FieldImpl)fieldsList.get(j);
					
					String fieldname = fldImpl.getName() != null ? fldImpl.getName() : null;
					if(fieldname.equals(textarea.getAttributeValue(HtmlConstants.NAME))){
						String isrequired = fldImpl.getIsrequired() != null ? fldImpl.getIsrequired() : null;
						String requiredeval = fldImpl.getRequiredeval() != null ? fldImpl.getRequiredeval() : null;
						
						if(isrequired != null && isrequired.equals("E")){
							boolean isErrorToBeChecked = false;
							
							//checking tabsconfiguration
			           		Map map = CacheManager.get("Tabs_Conf") == null ? null : (HashMap)CacheManager.get("Tabs_Conf");
			           	    if(map != null && map.containsKey(requiredeval) && "Y".equalsIgnoreCase(map.get(requiredeval).toString())){
			           	    	isErrorToBeChecked = true;
			           	    }
			           	     
			           	    //checking security
			           	     
			           	     
			           	    //checking rules
			           	    RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(requiredeval);
			           	    if(ruleImpl != null && ruleImpl.evaluate(ctx, null)){
			           	    	isErrorToBeChecked = true;
			           	    }
			           	     
			           	    if(isErrorToBeChecked){
			           	    	if(StringUtils.isBlank(cssClass))
			           				cssClass = "mandatory";
			           	    	else
			           	    		cssClass = cssClass.replace("nonMandatory", "").replace("mandatory", "") + " " + "mandatory";
			           	    	
			           	    	((Element)textarea.getParent()).setAttribute(HtmlConstants.CSS_CLASS, cssClass);
			           	     }
						}
						
						return textarea;
					}
				}
			}
			
			ComponentImpl componentImpl = ComponentResources.getInstance(ctx).getComponent(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString());
			List moList = componentImpl.getMetaObjectList(ctx);
			
			if(componentImpl.getAction(ctx.get(HtmlConstants.INET_METHOD).toString()) != null && componentImpl.getAction(ctx.get(HtmlConstants.INET_METHOD).toString()).getMetaobject() != null){
				moList = componentImpl.getMetaObjectList(ctx, componentImpl.getAction(ctx.get(HtmlConstants.INET_METHOD).toString()).getMetaobject());
			}
			
			if(moList != null && moList.size() > 0){
				for(int i=0; i<moList.size(); i++){
					MetaobjectImpl impl = (MetaobjectImpl)moList.get(i);
					
					List propList = impl.getFirstPropertyversion().getPropertyList();
					if(propList != null && propList.size() > 0){
						for(int j=0; j<propList.size(); j++){
							PropertyImpl pImpl = (PropertyImpl)propList.get(j);
							
							if(pImpl.getFieldName().equals(textarea.getAttributeValue(HtmlConstants.NAME))){
								if(pImpl.getIsrequired() != null && pImpl.getIsrequired().equals("E")){
									boolean isErrorToBeChecked = false;
									
									//checking tabsconfiguration
					           		 Map map = CacheManager.get("Tabs_Conf")==null?null:(HashMap)CacheManager.get("Tabs_Conf");
					           	     if(map != null && map.containsKey(pImpl.getRequiredeval()) && "Y".equalsIgnoreCase(map.get(pImpl.getRequiredeval()).toString())){
					           	    	isErrorToBeChecked = true;
					           	     }
					           	     
					           	     //checking security
					           	     
					           	     
					           	     //checking rules
					           	     RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(pImpl.getRequiredeval());
					           	     if(ruleImpl != null && ruleImpl.evaluate(ctx, null)){
					           	    	isErrorToBeChecked = true;
					           	     }
					           	     
					           	     if(isErrorToBeChecked){
					           	    	if(StringUtils.isBlank(cssClass))
					           				cssClass = "mandatory";
					           	    	else
					           	    		cssClass = cssClass.replace("nonMandatory", "").replace("mandatory", "") + " " + "mandatory";
					           	    	
					           	    	//textarea.setAttribute(HtmlConstants.CSS_CLASS, cssClass);
					           	    	((Element)textarea.getParent()).setAttribute(HtmlConstants.CSS_CLASS, cssClass);
					           	     }
								}/*else if(pImpl.getIsrequired() != null && pImpl.getIsrequired().equals("Y")){
									if(StringUtils.isBlank(cssClass))
				           				cssClass = "mandatory";
				           	    	else
				           	    		cssClass = cssClass.replace("nonMandatory", "") + " " + "mandatory";
				           	    	
									textarea.setAttribute(HtmlConstants.CSS_CLASS, cssClass);
								}*/
								
								return textarea;
							}
						}
					}
				}
			}
		}catch(Exception e){
			logger.error(ctx, "Unable to generate css class for textarea box due to error : " + e.getMessage());
		}
		
		return textarea;
	}

	public Element createCheckbox(Element checkbox,  String type, String name, String id, String value, boolean checked, Context ctx,
			Map record)throws Exception
	{
		if(checkbox==null)
			checkbox = new Element(HtmlConstants.INPUT);

		ParseUtil.addAttribute(checkbox, HtmlConstants.TYPE, Constants.CHECKBOX);

		if(name!=null)
			ParseUtil.addAttribute(checkbox, HtmlConstants.NAME, name);
		if(id!=null)
			ParseUtil.addAttribute(checkbox, HtmlConstants.ID, id);
		if(value!=null && !"".equals(value))
			ParseUtil.addAttribute(checkbox, HtmlConstants.VALUE, value);
		if(checked)
			ParseUtil.addAttribute(checkbox, Constants.CHECKED, Constants.CHECKED);

		//Added code to create tooltiptext for component
		if(checkbox.getAttributeValue(HtmlConstants.TITLE) != null){
			String title = checkbox.getAttributeValue(HtmlConstants.TITLE);
			
			/*if(checkbox.getAttributeValue(HtmlConstants.TITLE).startsWith("context_")){
				Context ctxClone = (Context)ctx.clone();
				if(record != null)
					ctxClone.putAll(record);
				
				title = title.substring(title.indexOf("context_")+8, title.length());
				
				title = ctxClone.get(title) != null ? ctxClone.get(title).toString() : title;
			}*/
			
			title = ctx.get(title) != null ? ctx.get(title).toString() : title;
			
			title = new DataUtils().getLabelFromLabelConf(title);
			
			ParseUtil.addAttribute(checkbox, HtmlConstants.TITLE, title);
		}
		//Ended code
		
		//going to add in pageFieldsList for validations phase
		if(checkbox.getAttributeValue(HtmlConstants.DISABLED) == null || !checkbox.getAttributeValue(HtmlConstants.DISABLED).equalsIgnoreCase(HtmlConstants.DISABLED)){
			if(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) != null){
				String NEXT_PAGE_FOR_PARSE = ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString();
				
				if(ctx.get(NEXT_PAGE_FOR_PARSE+"_pageFieldsList") == null){
					ctx.put(NEXT_PAGE_FOR_PARSE+"_pageFieldsList", checkbox.getAttributeValue(HtmlConstants.NAME)+"_FIELDTYPE=select");
				}else{
					ctx.put(NEXT_PAGE_FOR_PARSE+"_pageFieldsList", ctx.get(NEXT_PAGE_FOR_PARSE+"_pageFieldsList") + "," + checkbox.getAttributeValue(HtmlConstants.NAME)+"_FIELDTYPE=select");
				}
			}
		}
		//Ended code
		
		//Added code to show tooltip -- 29/8/2016 vikask
		if(SystemProperties.getInstance().getProperty("appl.configuration.applicable") != null &&
				SystemProperties.getInstance().getProperty("appl.configuration.applicable").toString().equals("Y") &&
				SystemProperties.getInstance().getProperty("appl.configuration.showtooltip") != null && 
				SystemProperties.getInstance().getProperty("appl.configuration.showtooltip").toString().equals("Y")){
			if(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) != null && (ctx.get(checkbox.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded") == null || 
					!ctx.get(checkbox.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded").equals("Y"))){
				if(CacheManager.get(HtmlConstants.PAGEFIELDSMAP) != null){
					Map map = (Map)CacheManager.get(HtmlConstants.PAGEFIELDSMAP);
					if(map.containsKey(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) + "." + checkbox.getAttributeValue(HtmlConstants.NAME))){
						Element imgElement = new Element(HtmlConstants.IMG);
						imgElement.setAttribute(HtmlConstants.SRC, "images/icons/tooltipField.png");
						imgElement.setAttribute(HtmlConstants.CSS_CLASS, "tooltipField");
						imgElement.setAttribute(HtmlConstants.TITLE, map.get(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) + "." + checkbox.getAttributeValue(HtmlConstants.NAME)).toString());
						checkbox.getParent().addContent(imgElement.detach());
						
						//set flag to avoid duplicity of tooltip for select field because it is already created from processMetaobject() above
						ctx.put(checkbox.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded", "Y");
					}
				}
			}
		}
		//Ended code
		
		//Added code to generate css class based on isrequired in metaobject property
		if(checkbox.getParent() == null)
			return checkbox;
		
		//Added code to add tooltip icon next to field
		try{
			if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".showtooltip") != null && 
					SystemProperties.getInstance().getString("appl."+ctx.getProject()+".showtooltip").equals("Y")){
				//String tooltip = MetaDataResources.getInstance(ctx).getField(checkbox.getAttributeValue(HtmlConstants.NAME)).getTiptext();
				String tooltip = FieldsResources.getInstance(ctx).getField(checkbox.getAttributeValue(HtmlConstants.NAME)).getTooltip();
				if(StringUtils.isNotBlank(tooltip)){
					Element aEle = new Element(HtmlConstants.LINK);
					aEle.setAttribute(HtmlConstants.TITLE, tooltip);
					aEle.setAttribute(HtmlConstants.CSS_CLASS, "icoTooltip icons");
					aEle.setAttribute(HtmlConstants.HREF, "#");
					
					((Element)checkbox.getParent()).addContent(aEle.detach());
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
				
		String cssClass = ((Element)checkbox.getParent()).getAttributeValue(HtmlConstants.CSS_CLASS);
		if(StringUtils.isBlank(cssClass))
			cssClass = "nonMandatory";
		else
			cssClass = cssClass + " " + "nonMandatory";
		
		//checkbox.setAttribute(HtmlConstants.CSS_CLASS, cssClass);
		((Element)checkbox.getParent()).setAttribute(HtmlConstants.CSS_CLASS, cssClass);
		
		try{
			if(ctx.get(Constants.INET_PAGE).toString().equals(HtmlConstants.MANAGE_TESTHARNESS))
				return checkbox;
			
			List fieldsList = FieldsResources.getInstance(ctx).getFieldListByScreen(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString());
			if(fieldsList != null && fieldsList.size() > 0){
				for(int j=0; j<fieldsList.size(); j++){
					FieldImpl fldImpl = (FieldImpl)fieldsList.get(j);
					
					String fieldname = fldImpl.getName() != null ? fldImpl.getName() : null;
					if(fieldname.equals(checkbox.getAttributeValue(HtmlConstants.NAME))){
						String isrequired = fldImpl.getIsrequired() != null ? fldImpl.getIsrequired() : null;
						String requiredeval = fldImpl.getRequiredeval() != null ? fldImpl.getRequiredeval() : null;
						
						if(isrequired != null && isrequired.equals("E")){
							boolean isErrorToBeChecked = false;
							
							//checking tabsconfiguration
			           		Map map = CacheManager.get("Tabs_Conf") == null ? null : (HashMap)CacheManager.get("Tabs_Conf");
			           	    if(map != null && map.containsKey(requiredeval) && "Y".equalsIgnoreCase(map.get(requiredeval).toString())){
			           	    	isErrorToBeChecked = true;
			           	    }
			           	     
			           	    //checking security
			           	     
			           	     
			           	    //checking rules
			           	    RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(requiredeval);
			           	    if(ruleImpl != null && ruleImpl.evaluate(ctx, null)){
			           	    	isErrorToBeChecked = true;
			           	    }
			           	     
			           	    if(isErrorToBeChecked){
			           	    	if(StringUtils.isBlank(cssClass))
			           				cssClass = "mandatory";
			           	    	else
			           	    		cssClass = cssClass.replace("nonMandatory", "").replace("mandatory", "") + " " + "mandatory";
			           	    	
			           	    	((Element)checkbox.getParent()).setAttribute(HtmlConstants.CSS_CLASS, cssClass);
			           	     }
						}
						
						return checkbox;
					}
				}
			}
			
			ComponentImpl componentImpl = ComponentResources.getInstance(ctx).getComponent(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString());
			List moList = componentImpl.getMetaObjectList(ctx);
			
			if(componentImpl.getAction(ctx.get(HtmlConstants.INET_METHOD).toString()) != null && componentImpl.getAction(ctx.get(HtmlConstants.INET_METHOD).toString()).getMetaobject() != null){
				moList = componentImpl.getMetaObjectList(ctx, componentImpl.getAction(ctx.get(HtmlConstants.INET_METHOD).toString()).getMetaobject());
			}
			
			if(moList != null && moList.size() > 0){
				for(int i=0; i<moList.size(); i++){
					MetaobjectImpl impl = (MetaobjectImpl)moList.get(i);
					
					List propList = impl.getFirstPropertyversion().getPropertyList();
					if(propList != null && propList.size() > 0){
						for(int j=0; j<propList.size(); j++){
							PropertyImpl pImpl = (PropertyImpl)propList.get(j);
							
							if(pImpl.getFieldName().equals(checkbox.getAttributeValue(HtmlConstants.NAME))){
								if(pImpl.getIsrequired() != null && pImpl.getIsrequired().equals("E")){
									boolean isErrorToBeChecked = false;
									
									//checking tabsconfiguration
					           		 Map map = CacheManager.get("Tabs_Conf") == null ? null : (HashMap)CacheManager.get("Tabs_Conf");
					           	     if(map != null && map.containsKey(pImpl.getRequiredeval()) && "Y".equalsIgnoreCase(map.get(pImpl.getRequiredeval()).toString())){
					           	    	isErrorToBeChecked = true;
					           	     }
					           	     
					           	     //checking security
					           	     
					           	     
					           	     //checking rules
					           	     RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(pImpl.getRequiredeval());
					           	     if(ruleImpl != null && ruleImpl.evaluate(ctx, null)){
					           	    	isErrorToBeChecked = true;
					           	     }
					           	     
					           	     if(isErrorToBeChecked){
					           	    	if(StringUtils.isBlank(cssClass))
					           				cssClass = "mandatory";
					           	    	else
					           	    		cssClass = cssClass.replace("nonMandatory", "").replace("mandatory", "") + " " + "mandatory";
					           	    	
					           	    	//checkbox.setAttribute(HtmlConstants.CSS_CLASS, cssClass);
					           	    	((Element)checkbox.getParent()).setAttribute(HtmlConstants.CSS_CLASS, cssClass);
					           	     }
								}/*else if(pImpl.getIsrequired() != null && pImpl.getIsrequired().equals("Y")){
									if(StringUtils.isBlank(cssClass))
				           				cssClass = "mandatory";
				           	    	else
				           	    		cssClass = cssClass.replace("nonMandatory", "") + " " + "mandatory";
				           	    	
									checkbox.setAttribute(HtmlConstants.CSS_CLASS, cssClass);
								}*/
								
								return checkbox;
							}
						}
					}
				}
			}
		}catch(Exception e){
			logger.error(ctx, "Unable to generate css class for select box due to error : " + e.getMessage());
		}
		
		return checkbox;
	}

	public Element createRadio(Element radio,  String type, String name, String id, String value, boolean checked, Context ctx, Map record)throws Exception
	{
		if(radio==null)
			radio = new Element(HtmlConstants.INPUT);

		ParseUtil.addAttribute(radio, HtmlConstants.TYPE, Constants.RADIO);

		if(name!=null)
			ParseUtil.addAttribute(radio, HtmlConstants.NAME, name);
		if(id!=null)
			ParseUtil.addAttribute(radio, HtmlConstants.ID, id);
		if(value!=null)
			ParseUtil.addAttribute(radio, HtmlConstants.VALUE, value);
		if(checked)
			ParseUtil.addAttribute(radio, Constants.CHECKED, Constants.CHECKED);

		//Added code to create tooltiptext for component
		if(radio.getAttributeValue(HtmlConstants.TITLE) != null){
			String title = radio.getAttributeValue(HtmlConstants.TITLE);
			
			/*if(radio.getAttributeValue(HtmlConstants.TITLE).startsWith("context_")){
				Context ctxClone = (Context)ctx.clone();
				if(record != null)
					ctxClone.putAll(record);
				
				title = title.substring(title.indexOf("context_")+8, title.length());
				
				title = ctxClone.get(title) != null ? ctxClone.get(title).toString() : title;
			}*/
			
			title = ctx.get(title) != null ? ctx.get(title).toString() : title;
			
			title = new DataUtils().getLabelFromLabelConf(title);
			
			ParseUtil.addAttribute(radio, HtmlConstants.TITLE, title);
		}
		//Ended code
		
		//going to add in pageFieldsList for validations phase
		if(radio.getAttributeValue(HtmlConstants.DISABLED) == null || !radio.getAttributeValue(HtmlConstants.DISABLED).equalsIgnoreCase(HtmlConstants.DISABLED)){
			if(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) != null){
				String NEXT_PAGE_FOR_PARSE = ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString();
				
				if(ctx.get(NEXT_PAGE_FOR_PARSE+"_pageFieldsList") == null){
					ctx.put(NEXT_PAGE_FOR_PARSE+"_pageFieldsList", radio.getAttributeValue(HtmlConstants.NAME)+"_FIELDTYPE=select");
				}else{
					ctx.put(NEXT_PAGE_FOR_PARSE+"_pageFieldsList", ctx.get(NEXT_PAGE_FOR_PARSE+"_pageFieldsList") + "," + radio.getAttributeValue(HtmlConstants.NAME)+"_FIELDTYPE=select");
				}
			}
		}
		//Ended code
		
		//Added code to show tooltip -- 29/8/2016 vikask
		if(SystemProperties.getInstance().getProperty("appl.configuration.applicable") != null &&
				SystemProperties.getInstance().getProperty("appl.configuration.applicable").toString().equals("Y") &&
				SystemProperties.getInstance().getProperty("appl.configuration.showtooltip") != null && 
				SystemProperties.getInstance().getProperty("appl.configuration.showtooltip").toString().equals("Y")){
			if(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) != null && (ctx.get(radio.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded") == null || 
					!ctx.get(radio.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded").equals("Y"))){
				if(CacheManager.get(HtmlConstants.PAGEFIELDSMAP) != null){
					Map map = (Map)CacheManager.get(HtmlConstants.PAGEFIELDSMAP);
					if(map.containsKey(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) + "." + radio.getAttributeValue(HtmlConstants.NAME))){
						Element imgElement = new Element(HtmlConstants.IMG);
						imgElement.setAttribute(HtmlConstants.SRC, "images/icons/tooltipField.png");
						imgElement.setAttribute(HtmlConstants.CSS_CLASS, "tooltipField");
						imgElement.setAttribute(HtmlConstants.TITLE, map.get(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) + "." + radio.getAttributeValue(HtmlConstants.NAME)).toString());
						radio.getParent().addContent(imgElement.detach());
						
						//set flag to avoid duplicity of tooltip for select field because it is already created from processMetaobject() above
						ctx.put(radio.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded", "Y");
					}
				}
			}
		}
		//Ended code
		
		//Added code to generate css class based on isrequired in metaobject property
		if(radio.getParent() == null)
			return radio;
		
		//Added code to add tooltip icon next to field
		try{
			if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".showtooltip") != null && 
					SystemProperties.getInstance().getString("appl."+ctx.getProject()+".showtooltip").equals("Y")){
				//String tooltip = MetaDataResources.getInstance(ctx).getField(radio.getAttributeValue(HtmlConstants.NAME)).getTiptext();
				String tooltip = FieldsResources.getInstance(ctx).getField(radio.getAttributeValue(HtmlConstants.NAME)).getTooltip();
				if(StringUtils.isNotBlank(tooltip)){
					Element aEle = new Element(HtmlConstants.LINK);
					aEle.setAttribute(HtmlConstants.TITLE, tooltip);
					aEle.setAttribute(HtmlConstants.CSS_CLASS, "icoTooltip icons");
					aEle.setAttribute(HtmlConstants.HREF, "#");
					
					((Element)radio.getParent()).addContent(aEle.detach());
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
				
		String cssClass = ((Element)radio.getParent()).getAttributeValue(HtmlConstants.CSS_CLASS);
		if(StringUtils.isBlank(cssClass))
			cssClass = "nonMandatory";
		else
			cssClass = cssClass + " " + "nonMandatory";
		
		//radio.setAttribute(HtmlConstants.CSS_CLASS, cssClass);
		((Element)radio.getParent()).setAttribute(HtmlConstants.CSS_CLASS, cssClass);
		
		try{
			if(ctx.get(Constants.INET_PAGE).toString().equals(HtmlConstants.MANAGE_TESTHARNESS))
				return radio;
			
			List fieldsList = FieldsResources.getInstance(ctx).getFieldListByScreen(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString());
			if(fieldsList != null && fieldsList.size() > 0){
				for(int j=0; j<fieldsList.size(); j++){
					FieldImpl fldImpl = (FieldImpl)fieldsList.get(j);
					
					String fieldname = fldImpl.getName() != null ? fldImpl.getName() : null;
					if(fieldname.equals(radio.getAttributeValue(HtmlConstants.NAME))){
						String isrequired = fldImpl.getIsrequired() != null ? fldImpl.getIsrequired() : null;
						String requiredeval = fldImpl.getRequiredeval() != null ? fldImpl.getRequiredeval() : null;
						
						if(isrequired != null && isrequired.equals("E")){
							boolean isErrorToBeChecked = false;
							
							//checking tabsconfiguration
			           		Map map = CacheManager.get("Tabs_Conf") == null ? null : (HashMap)CacheManager.get("Tabs_Conf");
			           	    if(map != null && map.containsKey(requiredeval) && "Y".equalsIgnoreCase(map.get(requiredeval).toString())){
			           	    	isErrorToBeChecked = true;
			           	    }
			           	     
			           	    //checking security
			           	     
			           	     
			           	    //checking rules
			           	    RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(requiredeval);
			           	    if(ruleImpl != null && ruleImpl.evaluate(ctx, null)){
			           	    	isErrorToBeChecked = true;
			           	    }
			           	     
			           	    if(isErrorToBeChecked){
			           	    	if(StringUtils.isBlank(cssClass))
			           				cssClass = "mandatory";
			           	    	else
			           	    		cssClass = cssClass.replace("nonMandatory", "").replace("mandatory", "") + " " + "mandatory";
			           	    	
			           	    	((Element)radio.getParent()).setAttribute(HtmlConstants.CSS_CLASS, cssClass);
			           	     }
						}
						
						return radio;
					}
				}
			}
			
			ComponentImpl componentImpl = ComponentResources.getInstance(ctx).getComponent(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString());
			List moList = componentImpl.getMetaObjectList(ctx);
			
			if(componentImpl.getAction(ctx.get(HtmlConstants.INET_METHOD).toString()) != null && componentImpl.getAction(ctx.get(HtmlConstants.INET_METHOD).toString()).getMetaobject() != null){
				moList = componentImpl.getMetaObjectList(ctx, componentImpl.getAction(ctx.get(HtmlConstants.INET_METHOD).toString()).getMetaobject());
			}
			
			if(moList != null && moList.size() > 0){
				for(int i=0; i<moList.size(); i++){
					MetaobjectImpl impl = (MetaobjectImpl)moList.get(i);
					
					List propList = impl.getFirstPropertyversion().getPropertyList();
					if(propList != null && propList.size() > 0){
						for(int j=0; j<propList.size(); j++){
							PropertyImpl pImpl = (PropertyImpl)propList.get(j);
							
							if(pImpl.getFieldName().equals(radio.getAttributeValue(HtmlConstants.NAME))){
								if(pImpl.getIsrequired() != null && pImpl.getIsrequired().equals("E")){
									boolean isErrorToBeChecked = false;
									
									//checking tabsconfiguration
					           		 Map map = CacheManager.get("Tabs_Conf") == null ? null : (HashMap)CacheManager.get("Tabs_Conf");
					           	     if(map != null && map.containsKey(pImpl.getRequiredeval()) && "Y".equalsIgnoreCase(map.get(pImpl.getRequiredeval()).toString())){
					           	    	isErrorToBeChecked = true;
					           	     }
					           	     
					           	     //checking security
					           	     
					           	     
					           	     //checking rules
					           	     RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(pImpl.getRequiredeval());
					           	     if(ruleImpl != null && ruleImpl.evaluate(ctx, null)){
					           	    	isErrorToBeChecked = true;
					           	     }
					           	     
					           	     if(isErrorToBeChecked){
					           	    	if(StringUtils.isBlank(cssClass))
					           				cssClass = "mandatory";
					           	    	else
					           	    		cssClass = cssClass.replace("nonMandatory", "").replace("mandatory", "") + " " + "mandatory";
					           	    	
					           	    	//radio.setAttribute(HtmlConstants.CSS_CLASS, cssClass);
					           	    	((Element)radio.getParent()).setAttribute(HtmlConstants.CSS_CLASS, cssClass);
					           	     }
								}/*else if(pImpl.getIsrequired() != null && pImpl.getIsrequired().equals("Y")){
									if(StringUtils.isBlank(cssClass))
				           				cssClass = "mandatory";
				           	    	else
				           	    		cssClass = cssClass.replace("nonMandatory", "") + " " + "mandatory";
				           	    	
									radio.setAttribute(HtmlConstants.CSS_CLASS, cssClass);
								}*/
								
								return radio;
							}
						}
					}
				}
			}
		}catch(Exception e){
			logger.error(ctx, "Unable to generate css class for radio box due to error : " + e.getMessage());
		}
		
		return radio;
	}


	public Element createSelect(Element select, String name, String id, String value, boolean isRowLinkAjaxExist, int rowIndex, Context ctx, Map record) throws Exception {
		if(select==null)
			select = new Element(Constants.SELECT);

		if(name!=null)
			ParseUtil.addAttribute(select, HtmlConstants.NAME, name);
		if(id!=null)
			ParseUtil.addAttribute(select, HtmlConstants.ID, id);

		List optionList = select.getChildren();
		if(optionList!=null)
			for(int i=0; i<optionList.size(); i++)
			{
				Element option  = (Element)optionList.get(i);
				if(option.getAttributeValue(HtmlConstants.VALUE)!=null)
				{
					if(value.equalsIgnoreCase(option.getAttributeValue(HtmlConstants.VALUE)))
						ParseUtil.addAttribute(option, HtmlConstants.SELECTED, HtmlConstants.SELECTED);
					else
						option.removeAttribute(HtmlConstants.SELECTED);
				}
				
				//going to check displaytext with labelconf.xml
				if(option.getText() != null){
					String displayText = option.getText();
					displayText = new DataUtils().getLabelFromLabelConf(displayText);
					option.setText(displayText);
				}
			}

		//Added code to create tooltiptext for component
		if(select.getAttributeValue(HtmlConstants.TITLE) != null){
			String title = select.getAttributeValue(HtmlConstants.TITLE);
			
			/*if(select.getAttributeValue(HtmlConstants.TITLE).startsWith("context_")){
				Context ctxClone = (Context)ctx.clone();
				if(record != null)
					ctxClone.putAll(record);
				
				title = title.substring(title.indexOf("context_")+8, title.length());
				
				title = ctxClone.get(title) != null ? ctxClone.get(title).toString() : title;
			}*/
			
			title = ctx.get(title) != null ? ctx.get(title).toString() : title;
			
			title = new DataUtils().getLabelFromLabelConf(title);
			
			ParseUtil.addAttribute(select, HtmlConstants.TITLE, title);
		}
		//Ended code
		
		//going to add in pageFieldsList for validations phase
		if(select.getAttributeValue(HtmlConstants.DISABLED) == null || !select.getAttributeValue(HtmlConstants.DISABLED).equalsIgnoreCase(HtmlConstants.DISABLED)){
			if(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) != null){
				String NEXT_PAGE_FOR_PARSE = ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString();
				
				if(ctx.get(NEXT_PAGE_FOR_PARSE+"_pageFieldsList") == null){
					ctx.put(NEXT_PAGE_FOR_PARSE+"_pageFieldsList", select.getAttributeValue(HtmlConstants.NAME)+"_FIELDTYPE=select");
				}else{
					ctx.put(NEXT_PAGE_FOR_PARSE+"_pageFieldsList", ctx.get(NEXT_PAGE_FOR_PARSE+"_pageFieldsList") + "," + select.getAttributeValue(HtmlConstants.NAME)+"_FIELDTYPE=select");
				}
			}
		}
		//Ended code
		
		//Added code to show tooltip -- 29/8/2016 vikask
		if(SystemProperties.getInstance().getProperty("appl.configuration.applicable") != null &&
				SystemProperties.getInstance().getProperty("appl.configuration.applicable").toString().equals("Y") &&
				SystemProperties.getInstance().getProperty("appl.configuration.showtooltip") != null && 
				SystemProperties.getInstance().getProperty("appl.configuration.showtooltip").toString().equals("Y")){
			if(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) != null && (ctx.get(select.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded") == null || 
					!ctx.get(select.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded").equals("Y"))){
				if(CacheManager.get(HtmlConstants.PAGEFIELDSMAP) != null){
					Map map = (Map)CacheManager.get(HtmlConstants.PAGEFIELDSMAP);
					if(map.containsKey(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) + "." + select.getAttributeValue(HtmlConstants.NAME))){
						Element imgElement = new Element(HtmlConstants.IMG);
						imgElement.setAttribute(HtmlConstants.SRC, "images/icons/tooltipField.png");
						imgElement.setAttribute(HtmlConstants.CSS_CLASS, "tooltipField");
						imgElement.setAttribute(HtmlConstants.TITLE, map.get(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) + "." + select.getAttributeValue(HtmlConstants.NAME)).toString());
						select.getParent().addContent(imgElement.detach());
						
						//set flag to avoid duplicity of tooltip for select field because it is already created from processMetaobject() above
						ctx.put(select.getAttributeValue(HtmlConstants.NAME)+"tooltipaddded", "Y");
					}
				}
			}
		}
		//Ended code
		
		//Added code to generate css class based on isrequired in metaobject property
		if(select.getParent() == null)
			return select;
		
		//Added code to add tooltip icon next to field
		try{
			if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".showtooltip") != null && 
					SystemProperties.getInstance().getString("appl."+ctx.getProject()+".showtooltip").equals("Y")){
				//String tooltip = MetaDataResources.getInstance(ctx).getField(select.getAttributeValue(HtmlConstants.NAME)).getTiptext();
				String tooltip = FieldsResources.getInstance(ctx).getField(select.getAttributeValue(HtmlConstants.NAME)).getTooltip();
				if(StringUtils.isNotBlank(tooltip)){
					Element aEle = new Element(HtmlConstants.LINK);
					aEle.setAttribute(HtmlConstants.TITLE, tooltip);
					aEle.setAttribute(HtmlConstants.CSS_CLASS, "icoTooltip icons");
					aEle.setAttribute(HtmlConstants.HREF, "#");
					
					((Element)select.getParent()).addContent(aEle.detach());
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
				
		String cssClass = ((Element)select.getParent()).getAttributeValue(HtmlConstants.CSS_CLASS);
		if(StringUtils.isBlank(cssClass))
			cssClass = "nonMandatory";
		else
			cssClass = cssClass + " " + "nonMandatory";
		
		//select.setAttribute(HtmlConstants.CSS_CLASS, cssClass);
		((Element)select.getParent()).setAttribute(HtmlConstants.CSS_CLASS, cssClass);
		
		try{
			if(ctx.get(Constants.INET_PAGE).toString().equals(HtmlConstants.MANAGE_TESTHARNESS))
				return select;
			
			List fieldsList = FieldsResources.getInstance(ctx).getFieldListByScreen(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString());
			if(fieldsList != null && fieldsList.size() > 0){
				for(int j=0; j<fieldsList.size(); j++){
					FieldImpl fldImpl = (FieldImpl)fieldsList.get(j);
					
					String fieldname = fldImpl.getName() != null ? fldImpl.getName() : null;
					if(fieldname.equals(select.getAttributeValue(HtmlConstants.NAME))){
						String isrequired = fldImpl.getIsrequired() != null ? fldImpl.getIsrequired() : null;
						String requiredeval = fldImpl.getRequiredeval() != null ? fldImpl.getRequiredeval() : null;
						
						if(isrequired != null && isrequired.equals("E")){
							boolean isErrorToBeChecked = false;
							
							//checking tabsconfiguration
			           		Map map = CacheManager.get("Tabs_Conf") == null ? null : (HashMap)CacheManager.get("Tabs_Conf");
			           	    if(map != null && map.containsKey(requiredeval) && "Y".equalsIgnoreCase(map.get(requiredeval).toString())){
			           	    	isErrorToBeChecked = true;
			           	    }
			           	     
			           	    //checking security
			           	     
			           	     
			           	    //checking rules
			           	    RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(requiredeval);
			           	    if(ruleImpl != null && ruleImpl.evaluate(ctx, null)){
			           	    	isErrorToBeChecked = true;
			           	    }
			           	     
			           	    if(isErrorToBeChecked){
			           	    	if(StringUtils.isBlank(cssClass))
			           				cssClass = "mandatory";
			           	    	else
			           	    		cssClass = cssClass.replace("nonMandatory", "").replace("mandatory", "") + " " + "mandatory";
			           	    	
			           	    	((Element)select.getParent()).setAttribute(HtmlConstants.CSS_CLASS, cssClass);
			           	     }
						}
						
						return select;
					}
				}
			}
			
			ComponentImpl componentImpl = ComponentResources.getInstance(ctx).getComponent(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString());
			List moList = componentImpl.getMetaObjectList(ctx);
			
			if(componentImpl.getAction(ctx.get(HtmlConstants.INET_METHOD).toString()) != null && componentImpl.getAction(ctx.get(HtmlConstants.INET_METHOD).toString()).getMetaobject() != null){
				moList = componentImpl.getMetaObjectList(ctx, componentImpl.getAction(ctx.get(HtmlConstants.INET_METHOD).toString()).getMetaobject());
			}
			
			if(moList != null && moList.size() > 0){
				for(int i=0; i<moList.size(); i++){
					MetaobjectImpl impl = (MetaobjectImpl)moList.get(i);
					
					List propList = impl.getFirstPropertyversion().getPropertyList();
					if(propList != null && propList.size() > 0){
						for(int j=0; j<propList.size(); j++){
							PropertyImpl pImpl = (PropertyImpl)propList.get(j);
							
							if(pImpl.getFieldName().equals(select.getAttributeValue(HtmlConstants.NAME))){
								if(pImpl.getIsrequired() != null && pImpl.getIsrequired().equals("E")){
									boolean isErrorToBeChecked = false;
									
									//checking tabsconfiguration
					           		 Map map = CacheManager.get("Tabs_Conf") == null ? null : (HashMap)CacheManager.get("Tabs_Conf");
					           	     if(map != null && map.containsKey(pImpl.getRequiredeval()) && "Y".equalsIgnoreCase(map.get(pImpl.getRequiredeval()).toString())){
					           	    	isErrorToBeChecked = true;
					           	     }
					           	     
					           	     //checking security
					           	     
					           	     
					           	     //checking rules
					           	     RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(pImpl.getRequiredeval());
					           	     if(ruleImpl != null && ruleImpl.evaluate(ctx, null)){
					           	    	isErrorToBeChecked = true;
					           	     }
					           	     
					           	     if(isErrorToBeChecked){
					           	    	if(StringUtils.isBlank(cssClass))
					           				cssClass = "mandatory";
					           	    	else
					           	    		cssClass = cssClass.replace("nonMandatory", "").replace("mandatory", "") + " " + "mandatory";
					           	    	
					           	    	//select.setAttribute(HtmlConstants.CSS_CLASS, cssClass);
					           	    	((Element)select.getParent()).setAttribute(HtmlConstants.CSS_CLASS, cssClass);
					           	     }
								}/*else if(pImpl.getIsrequired() != null && pImpl.getIsrequired().equals("Y")){
									if(StringUtils.isBlank(cssClass))
				           				cssClass = "mandatory";
				           	    	else
				           	    		cssClass = cssClass.replace("nonMandatory", "") + " " + "mandatory";
				           	    	
									select.setAttribute(HtmlConstants.CSS_CLASS, cssClass);
								}*/
								
								return select;
							}
						}
					}
				}
			}
		}catch(Exception e){
			logger.error(ctx, "Unable to generate css class for select box due to error : " + e.getMessage());
		}
		
		return select;
	}

	public Element createOption(Element option, String value , String content, String optionValue)throws Exception
	{
		if(option==null)
			option = new Element(HtmlConstants.OPTION);

		if(value!=null)
			ParseUtil.addAttribute(option, HtmlConstants.VALUE, value);

		if(value!=null && optionValue!=null)
		{
			if(value.equalsIgnoreCase(optionValue))
				ParseUtil.addAttribute(option, HtmlConstants.SELECTED, HtmlConstants.SELECTED);
		}
		
		//going to check content with labelconf.xml
		content = new DataUtils().getLabelFromLabelConf(content);
		option.addContent(content);

		return option;
	}

	public String getOnClickContent(String[] hrefValues) throws Exception
	{
		return getOnClickContent(hrefValues, false);
	}

	public String getOnClickContent(String[] hrefValues, boolean isMenu) throws Exception
	{
		StringBuffer buffer = new StringBuffer();

		if(isMenu)
			buffer.append(getReturnValuesMethod()+"(");
		else
			buffer.append(getReturnValuesMethod()+"(");

		for(int i=0; i<hrefValues.length; i++)
		{
			String paramValue = hrefValues[i];
			if(paramValue!=null)
				buffer.append("'"+paramValue+"',");
			else
				buffer.append("'',");
		}

		String content = buffer.substring(0, buffer.length()-1);
		content = content+")";

		return content;
	}

	public String getAttributeContent(String component_page, String action , Context ctx, Map record, String isAjax,
			String href,HttpServletRequest request, String multiRowLinkValue) throws Exception {
		if("Y".equals(isAjax))
			return getAttributeContent(component_page, action, ctx, record, false, true,href,request, multiRowLinkValue);
		else
			return getAttributeContent(component_page, action, ctx, record, false, false,null,request, null);
	}

	public String getAttributeContent(String component_page, String action , Context ctx, Map record,HttpServletRequest request) throws Exception
	{
		return getAttributeContent(component_page, action, ctx, record, false,request);
	}

	public String getAttributeContent(String component_page, String action , Context ctx, Map record, boolean isMenu,HttpServletRequest request) throws Exception
	{
		return getAttributeContent(component_page, action, ctx, record, false, false,null,request, null);
	}

	public String getAttributeContent(String component_page, String action , Context ctx, Map record, boolean isMenu, 
			boolean isAjax, String href,HttpServletRequest request, String multiRowLinkValue) throws Exception {
		
		StringBuffer buffer = new StringBuffer();

		ComponentImpl componentImpl = ComponentResources.getInstance(ctx).getComponent(component_page);
		if(componentImpl == null)
			return "";

		ActionImpl actionImpl = componentImpl.getAction(action);
		if(actionImpl == null)
			return "";

		if(isAjax){
			if(href.contains(HtmlConstants.JAVASCRIPT_AJAXPAGE_BLOCKLINK))
				buffer.append(getJavaScriptMethodStartBlockLink()+",");
			else if(href.contains(HtmlConstants.RIGHTCLICK_MENU ))
				buffer.append(getJavaScriptMethodRightClinkMenu()+",");
			else if(href.contains(HtmlConstants.JAVASCRIPT_SHOW_DIALOGBOX ))
				buffer.append(getJavaScriptMethodShowDialogBox()+",");
			else if(href.contains(HtmlConstants.JAVASCRIPT_SUBMITFORM_AJAX)){
				buffer.append(getJavaScriptMethodStartAjaPage()+",");
			}
			else if(href.contains(HtmlConstants.JAVASCRIPT_SUBMITFORM_POPUP ))	{
				buffer.append(getJavaScriptMethodSubmitFormPopUp()+",");
			}
			else if(href != null && href.contains("javascript:submitformforupload")){
				buffer.append("javascript:managesubmitformforupload(document.forms[0],");
			}else if(href != null && href.contains(HtmlConstants.JAVASCRIPT_SUBMITFORM_AJAX)){
				buffer.append(HtmlConstants.ACTUALSUBMIT_FORMAJAX+",");
			}
			else
				buffer.append(getJavaScriptMethodStartAjax()+",");
		}
		else
		{
			if(isMenu)
				buffer.append(getJavaScriptMethodStartMenu()+",");
			else
				buffer.append(getJavaScriptMethodStart()+",");
		}

		buffer.append("'"+action+"',");

		List paramList = actionImpl.getSortedActionParamList();
		if(isMenu)
		{
			buffer.append(generateDynaKeyValues(paramList, ctx, record,request, multiRowLinkValue));
			buffer.append("'"+component_page+"',");
			buffer.append(generateDynaKeyMenu(paramList, ctx, record));
			buffer.append(getJavaScriptMethodEnd(""));
		}
		else
		{
			buffer.append(generateDynaKeyValues(paramList, ctx, record,request, multiRowLinkValue));
			buffer.append(getJavaScriptMethodEnd(component_page));
		}

		return buffer.toString();
	}

	public String getJavaScriptMethodStart()
	{
		return HtmlConstants.ACTUALSUBMIT_FORM;
	}

	public String getJavaScriptMethodStartMenu()
	{
		return HtmlConstants.ACTUALSUBMIT_FORM_MENU;
	}

	public String getJavaScriptMethodStartAjax()
	{
		return HtmlConstants.ACTUALSUBMIT_FORMAJAX;
	}

	public String getJavaScriptMethodStartBlockLink(){
		return HtmlConstants.ACTUALSUBMIT_FORMAJAXBLOCKLINK ;
	}

	public String getJavaScriptMethodRightClinkMenu()
	{
		return HtmlConstants.ACTUALSUBMIT_FORMRIGHTCLICKMENU;
	}

	public String getJavaScriptMethodStartAjaPage(){
		return HtmlConstants.ACTUALSUBIT_FORMAJAXPAGE;
	}

	public String getJavaScriptMethodShowDialogBox(){
		return HtmlConstants.ACTUALSUBMIT_FORM_SHOW_DIALOGBOX;
	}

	public String getJavaScriptMethodSubmitFormPopUp(){
		return HtmlConstants.ACTUALSUBIT_FORMPOPUP ;
	}

	public String getReturnValuesMethod()
	{
		return HtmlConstants.RETURN_VALUES;
	}

	public String getJavaScriptMethodEnd(String component)
	{
		return "'"+component+"')";
	}

	public String generateDynaKeyValues(List paramList, Context ctx, Map record,HttpServletRequest request, String multiRowLinkValue)
	{
		StringBuffer buffer = new StringBuffer();
		if(paramList == null)
			return "'','',";

		if(paramList != null){
			StringBuffer bufferDynaKeys = new StringBuffer();
			StringBuffer bufferDynaValues = new StringBuffer();

			for(int i=0; i<paramList.size(); i++){
				ParamImpl paramImpl= (ParamImpl)paramList.get(i);
				String paramName = paramImpl.getName();
				String paramValue = paramImpl.getVal();

				boolean isViewParamToBeAdded = true; 
				
				//Added code to create multiple link for single column - 26/7/2012
				String multiRowLinkParamName = paramName;
				paramName = paramName.endsWith("_multirow_link") ? paramName.substring(0, paramName.indexOf("_multirow_link")) : paramName;
				//Ended code
				
				if(paramImpl.getViewparam() && (paramImpl.getScope() == null || (paramImpl.getScope() != null && 
						!paramImpl.getScope().equals(HtmlConstants.MENU)))){
					
					//checking for evalute attribute to get param value from rule
					if(paramImpl.getEvalute()){
						Object result = executeRule(ctx, paramValue, paramName);
						if(result != null)
							paramValue = result.toString();
					}
					
					//checking for eval attribute to decide whether viewparam will add to link or not
					if(record == null && !StringUtils.isBlank(paramImpl.getEval())){
						Object result = executeRule(ctx, paramImpl.getEval(), paramName);
						if(result != null && result.toString().equals("true"))
							isViewParamToBeAdded = true;
						else
							isViewParamToBeAdded = false;
					}
					
					if(!isViewParamToBeAdded)
						continue;
					
					if(record == null){ //added in case on non-list link
	                    if(paramImpl.getPopulatename() != null && !HtmlConstants.EMPTY.equals(paramImpl.getPopulatename().trim()))
	                        bufferDynaKeys.append(paramImpl.getPopulatename()+"|");
	                    else
	                        bufferDynaKeys.append(paramName+"|");
					}

					//For List Type View Only
					if(record != null){
						//added code to get values from Context as well as from List row Map in case of List view viewparams
						Context newCtx = new Context();
						newCtx.putAll(ctx);
						newCtx.putAll(record);
						//Ended code
						
						//checking for eval attribute to decide whether viewparam will add to link or not
						if(!StringUtils.isBlank(paramImpl.getEval())){
							Object result = executeRule(newCtx, paramImpl.getEval(), paramName);
							if(result != null && result.toString().equals("true"))
								isViewParamToBeAdded = true;
							else
								isViewParamToBeAdded = false;
						}
						
						if(!isViewParamToBeAdded)
							continue;
						
					    if(paramImpl.getPopulatename() != null && !HtmlConstants.EMPTY.equals(paramImpl.getPopulatename().trim()))
	                        bufferDynaKeys.append(paramImpl.getPopulatename()+"|");
	                    else
	                        bufferDynaKeys.append(paramName+"|");
						
						if(paramImpl.getSession()){
//							HttpServletRequest request = (HttpServletRequest)ctx.get(Constants.HTTPREQUEST);
							if(request != null){
								HttpSession session = request.getSession();
								if(session != null){
									if(session.getAttribute(paramName) != null && !HtmlConstants.EMPTY.equals(session.getAttribute(paramName))){
										Object obj = session.getAttribute(paramName);
//										paramValue = (String)session.getAttribute(paramName);
										paramValue = ParseUtil.getValueFromObject(obj, null, null); //passing null last two params bcoz formatted value already stored in session
									}
								}
							}else{
								if(ctx.get(paramName) != null){
									Object obj = ctx.get(paramName);
//									paramValue = (String)ctx.get(paramName);
									paramValue = ParseUtil.getValueFromObject(obj, null, null);//passing null last two params bcoz formatted value already stored in session
								}
							}
						}
						if(paramImpl.getForm() && paramValue == null && newCtx.get(paramName) != null){
							Object object = newCtx.get(paramName);
							paramValue = ParseUtil.getValueFromObject(object, null, null);//passing null last two params bcoz formatted value already stored in session
						}
					}else{
						if(paramValue == null){
							if(paramImpl.getSession()){
//								HttpServletRequest request = (HttpServletRequest)ctx.get(Constants.HTTPREQUEST);
								if(request != null){
									HttpSession session = request.getSession();
									if(session != null){
										if(session.getAttribute(paramName) != null && !HtmlConstants.EMPTY.equals(session.getAttribute(paramName))){
											Object obj = session.getAttribute(paramName);
//											paramValue = (String)session.getAttribute(paramName);
											paramValue = ParseUtil.getValueFromObject(obj, null, null);//passing null last two params bcoz formatted value already stored in session
										}
									}
								}
							}else if(ctx.get(paramName) != null){
								Object object = ctx.get(paramName);
								paramValue = ParseUtil.getValueFromObject(object, null, null);//passing null last two params bcoz formatted value already stored in session
							}
						}
					}

					//Added code to create multiple link for single column - 26/7/2012
					paramValue = multiRowLinkParamName.endsWith("_multirow_link") && multiRowLinkValue != null ? multiRowLinkValue : paramValue;
					//Ended code
					
					if(paramValue!=null && !HtmlConstants.EMPTY.equals(paramValue)){
						if(paramValue.contains("'"))
							paramValue = paramValue.replace("'", "$APH$");
						if(paramValue.contains("&"))
							//paramValue = paramValue.replace("&", "#AMP#");
							paramValue = paramValue.replace("&", "$AMP$");
						if(paramValue.contains("%"))
							paramValue = paramValue.replace("%", "$PER$");
						if(paramValue.contains("|"))
							paramValue = paramValue.replace("|", "$HYPHEN$");
						
						bufferDynaValues.append(paramValue+"|");
					}else
						bufferDynaValues.append(Constants.SYMBOL + "|");
				}
			}

			if(!HtmlConstants.EMPTY.equals(bufferDynaKeys.toString().trim())){
				buffer.append("'|"+bufferDynaKeys+"',");
				buffer.append("'|"+bufferDynaValues+"',");
			}else{
				buffer.append("'',");
				buffer.append("'',");
			}
		}

		return buffer.toString();
	}

	public String generateDynaKeyMenu(List paramList, Context ctx, Map record)
	{
		StringBuffer buffer = new StringBuffer();
		if(paramList == null)
			return "'',";

		if(paramList!=null)
		{
			StringBuffer bufferExtraDynaKeys = new StringBuffer();

			for(int i=0; i<paramList.size(); i++)
			{
				ParamImpl paramImpl= (ParamImpl)paramList.get(i);
				String paramName = paramImpl.getName();
				if(paramImpl.getViewparam() && (paramImpl.getScope()!=null && paramImpl.getScope().equals(HtmlConstants.MENU)))
					bufferExtraDynaKeys.append(paramName+"|");
			}

			buffer.append("'"+bufferExtraDynaKeys+"',");
		}

		return buffer.toString();
	}



	public BlockfieldImpl getBlockFieldForHTMLControl(BlockImpl blockImpl, Element element ) throws Exception
	{
		if(blockImpl ==null)
			return null;

		String name = null;
		if(element.getAttributeValue(HtmlConstants.NAME) ==null)
		{
			if(element.getAttributeValue(HtmlConstants.ID) ==null)
				return null;
			else
				name = element.getAttributeValue(HtmlConstants.ID);
		}
		else
			name = element.getAttributeValue(HtmlConstants.NAME);

		if(name ==null)
			return null;

		BlockfieldsetImpl  blockfieldsetImpl = blockImpl.getFirstBlockfieldset();
		if(blockfieldsetImpl ==null)
			return null;

		List blockFieldList = blockfieldsetImpl.getBlockfieldList();
		if(blockFieldList ==null)
			return null;

		for(int i=0; i<blockFieldList.size(); i++)
		{
			BlockfieldImpl blockfieldImpl = (BlockfieldImpl)blockFieldList.get(i);
			if(name.equalsIgnoreCase(blockfieldImpl.getFieldname()))
				return blockfieldImpl;
		}

		return null;
	}

	public BlocklinkImpl getBlockLinkForHTMLControl(BlockImpl blockImpl, String methodStr)
	{
		if(blockImpl ==null)
			return null;

//		if(element.getAttributeValue(HtmlConstants.HREF)==null)
//			return null;
//		String href = element.getAttributeValue(HtmlConstants.HREF).toString();

		if(methodStr == null)
			return null;

		String[] hrefs = null;
		try	{
			hrefs = ParseUtil.processHref(methodStr);
		}
		catch(Exception e) {
			logger.error(methodStr + " is not in correct format");
		}

		String action = null;
		if(hrefs!=null)
		{
			AttributeObject attributeObject = new AttributeObject();
			attributeObject.populateObject(hrefs);
			action = attributeObject.getAction();
		}

		if(action ==null)
			return null;

		return blockImpl.getBlocklink(action);
	}

	public BlocklinkImpl getBlockLinkForHTMLControl(BlockImpl blockImpl, Element element)
	{
		if(blockImpl ==null)
			return null;

		if(element.getAttributeValue(HtmlConstants.HREF)==null)
			return null;

		String href = element.getAttributeValue(HtmlConstants.HREF).toString();
		String[] hrefs = null;
		try	{
			hrefs = ParseUtil.processHref(href);
		}
		catch(Exception e) {
			logger.error(href + " is not in correct format");
		}

		String action = null;
		if(hrefs!=null)
		{
			AttributeObject attributeObject = new AttributeObject();
			attributeObject.populateObject(hrefs);
			action = attributeObject.getAction();
		}

		if(action ==null)
			return null;

		return blockImpl.getBlocklink(action);
	}

	public boolean processElementForHide(Element sibling, Context ctx, String pageName) throws Exception{
		boolean flag = false;

		if(sibling.getAttributeValue(HtmlConstants.ID) != null){
			String id = (String) sibling.getAttributeValue(HtmlConstants.ID);
            // Check in the client specific project configuration for hide/show that block/Tab
            if(isBlockhideByConf(sibling, ctx, id)){
            	sibling.detach();
                return true;
            }
            
            //going to check for applicationworkflow xml show/hide
            if(isBlockhideByApplicationWorkflow(ctx, id, sibling)){
            	sibling.detach();
                return true;
            }
            
			if(id.startsWith(HtmlConstants.EVAL)){
				String eval = id.substring(id.indexOf(":")+1, id.length());
				if(!isElementToMakeHide(eval, ctx)){
					sibling.detach();
					flag = true;
				}
			}
			
			getLabelData(id,sibling, ctx, pageName);
			
			isElementExistsInSecurity(ctx, sibling, id, pageName);
		}

		return flag;
	}

    public boolean isBlockhideByConf(Element sibling, Context ctx, String id){
    	try{
	    	if(id.equals("isShowConfigurationXmlsReload") || id.equals("isShowConfigurationXmlsHelp")){
	    		if(SystemProperties.getInstance().getProperty("appl.configuration.applicable") != null &&
    					SystemProperties.getInstance().getProperty("appl.configuration.applicable").toString().equals("Y")){
					
					if(SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES) != null &&
							!SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES).toString().equals(HtmlConstants.EMPTY) &&
							("|"+SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES).toString()+"|").contains("|"+ctx.get(HtmlConstants.ROLES)+"|")){
						//do nothing
					}else{
						sibling.removeContent();
						return true;
					}
				}else{
					sibling.removeContent();
					return true;
				}
			}
    	}catch(Exception e1){
    		
    	}
    	
        Map map = null;
        map = CacheManager.get("Tabs_Conf")==null?null:(HashMap)CacheManager.get("Tabs_Conf");
        if(map == null)
            return false;
        
        //going to update html id bcoz labelconf contains pagename.id
    	String newid = (ctx.get(HtmlConstants.NEXT_PAGE_COMPONENT) != null ? ctx.get(HtmlConstants.NEXT_PAGE_COMPONENT).toString() : "");
    	try{
    		if(ProcessFlowResources.getInstance(ctx).getPagecomponent(newid).getParent() != null){
    			String parent = ProcessFlowResources.getInstance(ctx).getPagecomponent(newid).getParent();
    			
    			parent = parent.substring(0, parent.indexOf(".html"));
    			
    			newid = parent;
    		}
    	}catch (Exception e) {
    		//Do nothing
		}
    	
    	if(!id.contains("."))
    		newid = newid + "." + id;
    	
    	/*if(map.containsKey(newid) && map.get(newid) != null && map.get(newid).toString().startsWith(HtmlConstants.EVAL)){
    		String ruleid = map.get(newid).toString();
    		try{
	    		RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(ruleid);
				ctx.put(id, ruleImpl.execute(ctx, null).toString());
    		}catch (Exception e) {
				logger.error("Unable to execute rule "+ ruleid + "due to error : " + e.getMessage());
			}
    		
    		return false;
    	}
    	
    	if(map.containsKey(id) && map.get(id) != null && map.get(id).toString().startsWith(HtmlConstants.EVAL)){
    		String ruleid = map.get(id).toString();
    		try{
	    		RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(ruleid);
				ctx.put(id, ruleImpl.execute(ctx, null).toString());
    		}catch (Exception e) {
				logger.error("Unable to execute rule "+ ruleid + "due to error : " + e.getMessage());
			}
    		
    		return false;
    	}*/
    	
    	if(map.containsKey(newid)){
    		try{
    			if(SystemProperties.getInstance().getProperty("appl.configuration.applicable") != null &&
    					SystemProperties.getInstance().getProperty("appl.configuration.applicable").toString().equals("Y") &&
    					SystemProperties.getInstance().getProperty("appl.configuration.showtooltip") != null &&
    					SystemProperties.getInstance().getProperty("appl.configuration.showtooltip").toString().equals("Y")){
		    		
    				if(SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES) != null && 
							!SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES).toString().equals(HtmlConstants.EMPTY) &&
							("|"+SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES).toString()+"|").contains("|"+ctx.get(HtmlConstants.ROLES).toString()+"|")){
		    			
			    		String title = sibling.getAttributeValue(HtmlConstants.TITLE);
			    		
			    		if(StringUtils.isBlank(title))
			    			title = "Tabsconfiguration ID : " + newid;
			    		else	
			    			title = title + "; " + "Tabsconfiguration ID : " + newid;
			    			
			    		sibling.setAttribute(HtmlConstants.TITLE, title);	
		    		}
    			}
    		}catch (Exception e) {
				// TODO: handle exception
			}
    	}
    	
    	if(map.containsKey(id)){
    		try{
    			if(SystemProperties.getInstance().getProperty("appl.configuration.applicable") != null &&
    					SystemProperties.getInstance().getProperty("appl.configuration.applicable").toString().equals("Y") &&
    					SystemProperties.getInstance().getProperty("appl.configuration.showtooltip") != null &&
    					SystemProperties.getInstance().getProperty("appl.configuration.showtooltip").toString().equals("Y")){
    				
	    			if(SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES) != null && 
							!SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES).toString().equals(HtmlConstants.EMPTY) &&
							("|"+SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES).toString()+"|").contains("|"+ctx.get(HtmlConstants.ROLES).toString()+"|")){
		    			
			    		String title = sibling.getAttributeValue(HtmlConstants.TITLE);
			    		
			    		if(StringUtils.isBlank(title))
			    			title = "Tabsconfiguration ID : " + id;
			    		else	
			    			title = title + "; " + "Tabsconfiguration ID : " + id;
			    			
			    		sibling.setAttribute(HtmlConstants.TITLE, title);
		    		}
    			}
    		}catch (Exception e) {
				// TODO: handle exception
			}
    	}
    	
    	if(map.containsKey(newid))
            if(map.get(newid) !=null && "N".equalsIgnoreCase(map.get(newid).toString()))
                return true;
    	
        if(map.containsKey(id))
            if(map.get(id) !=null && "N".equalsIgnoreCase(map.get(id).toString()))
                return true;

        return false;
    }

    public void getLabelData(String id, Element sibling, Context ctx, String pageName) throws Exception {
    	if(id != null && id.contains("_fieldlabel")){ //Check to display label from Field's displayname from Metadata.xml
    		try{
    			id = id.substring(0, id.lastIndexOf("_"));
	        	Field field = MetaDataResources.getInstance(ctx).getField(id);
	        	if(field != null){
	        		sibling.setText(field.getDisplayname() != null ? field.getDisplayname() : "");
	        		return;
	        	}
    		}catch (Exception e) {
				// TODO: handle exception
			}
        }
        
    	Map<String,String> map = null;
    	map = CacheManager.get("Labels_Conf") == null ? null : (HashMap)CacheManager.get("Labels_Conf");
    	if(map == null)
    		return;
    	
    	//going to update html id bcoz labelconf contains pagename.id
    	String newid = (ctx.get(HtmlConstants.NEXT_PAGE_COMPONENT) != null ? ctx.get(HtmlConstants.NEXT_PAGE_COMPONENT).toString() : "");
    	try{
    		if(ProcessFlowResources.getInstance(ctx).getPagecomponent(newid).getParent() != null){
    			String parent = ProcessFlowResources.getInstance(ctx).getPagecomponent(newid).getParent();
    			
    			parent = parent.substring(0, parent.indexOf(".html"));
    			
    			newid = parent;
    		}
    	}catch (Exception e) {
    		//Do nothing
		}
    	
    	newid = newid + "." + id;
    	
    	if(map != null && map.containsKey(id)){
    		//sibling.setText(map.get(id));
    		
    		//going to get list of label configured fields available on page
    		if(ctx.get("labelconfiguredfieldsonpage_list_1") != null && ctx.get("labelconfiguredfieldsonpage_list_1") instanceof List){
    			List list = (List)ctx.get("labelconfiguredfieldsonpage_list_1");
    			Map labelMap = new HashMap();
    			//labelMap.put(id, map.get(id));
    			labelMap.put("column_key", id);
    			labelMap.put("column_value", map.get(id));
    			
    			if(!list.contains(labelMap))
    				list.add(labelMap);
    		}else{
    			List list = new ArrayList();
    			Map labelMap = new HashMap();
    			//labelMap.put(id, map.get(id));
    			labelMap.put("column_key", id);
    			labelMap.put("column_value", map.get(id));
    			
    			if(!list.contains(labelMap))
    				list.add(labelMap);
    			
    			ctx.put("labelconfiguredfieldsonpage_list_1", list);
    		}
    	}
    	
    	//if(map != null && !map.containsKey(id)){
    	if(!id.equals(DataUtils.getLabelFromLabelConf(id))){
			sibling.setText(DataUtils.getLabelFromLabelConf(id));
			
			/*sibling.setAttribute(HtmlConstants.CSS_CLASS, "configTooltip");
			
			Element spanElement = new Element(HtmlConstants.SPAN);
			spanElement.setAttribute(HtmlConstants.CSS_CLASS, "tooltip");
			
			Element innerSpanElement = new Element(HtmlConstants.SPAN);
			innerSpanElement.setAttribute(HtmlConstants.CSS_CLASS, "value");
			
			Element innerSpanElement1 = new Element(HtmlConstants.SPAN);
			innerSpanElement1.setAttribute(HtmlConstants.CSS_CLASS, "label");
			innerSpanElement1.setText("Labelconf ID : ");
			innerSpanElement.addContent(innerSpanElement1.detach());
			
			innerSpanElement1 = new Element(HtmlConstants.SPAN);
			innerSpanElement1.setAttribute(HtmlConstants.CSS_CLASS, "llblValue");
			innerSpanElement1.setText(id);
			
			innerSpanElement.addContent(innerSpanElement1.detach());
			
			spanElement.addContent(innerSpanElement.detach());
			sibling.addContent(spanElement);*/
			
			//show for configurationRoles only
			try{
				if(SystemProperties.getInstance().getProperty("appl.configuration.applicable") != null &&
    					SystemProperties.getInstance().getProperty("appl.configuration.applicable").toString().equals("Y") &&
    					SystemProperties.getInstance().getProperty("appl.configuration.showtooltip") != null &&
    					SystemProperties.getInstance().getProperty("appl.configuration.showtooltip").toString().equals("Y")){
					
					if(SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES) != null && 
							!SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES).toString().equals(HtmlConstants.EMPTY) &&
							("|"+SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES).toString()+"|").contains("|"+ctx.get(HtmlConstants.ROLES).toString()+"|")){
						
						String title = sibling.getAttributeValue(HtmlConstants.TITLE);
						if(StringUtils.isBlank(title))
							title = "Labelconf ID : " + id;
						else
							title = title + "; Labelconf ID : " + id;
						
						sibling.setAttribute(HtmlConstants.TITLE, title);
					}
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
    	//}
    	
    	/*if(map != null && map.containsKey(newid))
    		sibling.setText(map.get(newid));*/
    	
    	//if(map != null && !map.containsKey(newid)){
    	if(!newid.equals(DataUtils.getLabelFromLabelConf(newid))){
			sibling.setText(DataUtils.getLabelFromLabelConf(newid));
			
			/*sibling.setAttribute(HtmlConstants.CSS_CLASS, "configTooltip");
			
			Element spanElement = new Element(HtmlConstants.SPAN);
			spanElement.setAttribute(HtmlConstants.CSS_CLASS, "tooltip");
			
			Element innerSpanElement = new Element(HtmlConstants.SPAN);
			innerSpanElement.setAttribute(HtmlConstants.CSS_CLASS, "value");
			
			Element innerSpanElement1 = new Element(HtmlConstants.SPAN);
			innerSpanElement1.setAttribute(HtmlConstants.CSS_CLASS, "label");
			innerSpanElement1.setText("Labelconf ID : ");
			innerSpanElement.addContent(innerSpanElement1.detach());
			
			innerSpanElement1 = new Element(HtmlConstants.SPAN);
			innerSpanElement1.setAttribute(HtmlConstants.CSS_CLASS, "llblValue");
			innerSpanElement1.setText(newid);
			
			innerSpanElement.addContent(innerSpanElement1.detach());
			
			spanElement.addContent(innerSpanElement.detach());
			sibling.addContent(spanElement);*/
			
			//show for configurationRoles only
			try{
				if(SystemProperties.getInstance().getProperty("appl.configuration.applicable") != null &&
	    				SystemProperties.getInstance().getString("appl.configuration.applicable").equals("Y")){
					
					if(SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES) != null && 
							!SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES).toString().equals(HtmlConstants.EMPTY) &&
							("|"+SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES).toString()+"|").contains("|"+ctx.get(HtmlConstants.ROLES).toString()+"|")){
						
						String title = sibling.getAttributeValue(HtmlConstants.TITLE);
						if(StringUtils.isBlank(title))
							title = "Labelconf ID : " + newid;
						else
							title = title + "; Labelconf ID : " + newid;
						
						sibling.setAttribute(HtmlConstants.TITLE, title);
					}
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}	
    	//}
    	
    }

	public boolean isElementToMakeHide(String eval, Context ctx)
	{
		boolean flag = false;
		try
		{
//            IEvaluate evalutor = null;
//            evalutor = ExpressionResources.getInstance(ctx).findEvaluator(eval);
//            flag = evalutor.evaluate(ctx, new String[]{});

			RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(eval);
	      	flag = ruleImpl.evaluate(ctx, null);

        }
		catch (Exception e)
        {
			logger.error(eval + "could not be evaluated");
            //e.printStackTrace();
        }

       return flag;
	}


//	protected void parseTextAreaControls(Element container, Element sibling, String value, boolean listFlag, BlockfieldImpl blockfieldImpl, String appendValue)throws Exception
//	{
//////		if(listFlag)
//////			container.addContent(createTextArea(sibling, null, null, value, null, null));
//////		else
////		String control_name = null;
////		if(sibling.getAttributeValue(HtmlConstants.NAME)!=null)
////		{
////			control_name = (String)sibling.getAttributeValue(HtmlConstants.NAME);
////			control_name = ParseUtil.appendValueWithCoumn(control_name, appendValue);
////		}
////		if(appendValue!=null)
////		{
////			createHiddenText(sibling, control_name, control_name, value);
////		}
////		else
////			createTextArea(sibling, null, null, value, null, null);
//
//	}

//	protected void parseLabelControls(Element container, Element sibling, String value, boolean listFlag, BlockfieldImpl blockfieldImpl, String appendValue)throws Exception
//	{
//////		if(listFlag)
//////			container.addContent(createLabel(sibling, null, null, value, null, null));
//////		else
////		String control_name = null;
////		if(sibling.getAttributeValue(HtmlConstants.NAME)!=null)
////		{
////			control_name = (String)sibling.getAttributeValue(HtmlConstants.NAME);
////			control_name = ParseUtil.appendValueWithCoumn(control_name, appendValue);
////		}
////		if(appendValue!=null)
////		{
////			createLabel(sibling, control_name, control_name, value, null, null);
////		}
////		else
////			createLabel(sibling, null, null, value, null, null);
//
//	}

//	protected void parseInputControls(Element container, Element sibling,String selectedVal, String value, boolean listFlag, BlockfieldImpl blockfieldImpl, String appendValue)throws Exception
//	{
////		if(sibling.getAttribute(HtmlConstants.TYPE)!=null)
////		{
////			String control_type = (String)sibling.getAttributeValue(HtmlConstants.TYPE);
////
////			String control_name = null;
////			if(sibling.getAttributeValue(HtmlConstants.NAME)!=null)
////			{
////				control_name = (String)sibling.getAttributeValue(HtmlConstants.NAME);
////				control_name = ParseUtil.appendValueWithCoumn(control_name, appendValue);
////			}
////
////
////			if(Constants.TEXT.equalsIgnoreCase(control_type))
////			{
//////				if(listFlag)
//////					container.addContent(createText(sibling, null, null, value));
//////				else
////				if(appendValue!=null)
////				{
////					createText(sibling, control_name, control_name, value);
////				}
////				else
////					createText(sibling, null, null, value);
////			}
////			if(HtmlConstants.HIDDEN.equalsIgnoreCase(control_type))
////			{
//////				if(listFlag)
//////					container.addContent(createHiddenText(sibling, null, null, value));
//////				else
////				if(appendValue!=null)
////				{
////					createHiddenText(sibling, control_name, control_name, value);
////				}
////				else
////					createHiddenText(sibling, null, null, value);
////			}
////			else if(Constants.RADIO.equalsIgnoreCase(control_type))
////			{
////				if(listFlag)
////				{
////					if(appendValue!=null)
////					{
////						if(value.equals(selectedVal))
////							createRadio(sibling,  control_type, control_name, control_name, value, true);
////						else
////							createRadio(sibling,  control_type, control_name, control_name, value, false);
////					}
////					else
////					{
////						if(value.equals(selectedVal))
////							createRadio(sibling,  control_type, null, null, value, true);
////						else
////							createRadio(sibling,  control_type, null, null, value, false);
////					}
////				}
////				else
////				{
////					if(sibling.getAttributeValue(HtmlConstants.VALUE)!=null)
////					{
////						String inputvalue = sibling.getAttributeValue(HtmlConstants.VALUE)!=null?sibling.getAttributeValue(HtmlConstants.VALUE).toString():"";
////						if(value.equals(inputvalue))
////						{
////							if(appendValue!=null)
////							{
////								createRadio(sibling,  control_type, control_name, control_name, inputvalue, true);
////							}
////							else
////								createRadio(sibling,  control_type, null, null, inputvalue, true);
////						}
////					}
////				}
////			}
////			else if(Constants.CHECKBOX.equalsIgnoreCase(control_type))
////			{
////				if(listFlag)
////				{
////					if(appendValue!=null)
////					{
////						if(value.equals(selectedVal))
////							createCheckbox(sibling,  control_type, control_name, control_name, value, true);
////						else
////							createCheckbox(sibling,  control_type, control_name, control_name, value, false);
////					}
////					else
////					{
////						if(value.equals(selectedVal))
////							createCheckbox(sibling,  control_type, null, null, value, true);
////						else
////							createCheckbox(sibling,  control_type, null, null, value, false);
////					}
////				}
////				else
////				{
////					if(sibling.getAttributeValue(HtmlConstants.VALUE)!=null)
////					{
////						String inputvalue = sibling.getAttributeValue(HtmlConstants.VALUE)!=null?sibling.getAttributeValue(HtmlConstants.VALUE).toString():"";
////						if(value.equals(inputvalue))
////						{
////							if(appendValue!=null)
////							{
////								createCheckbox(sibling,  control_type, control_name, control_name, inputvalue, true);
////							}
////							else
////								createCheckbox(sibling,  control_type, null, null, inputvalue, true);
////						}
////					}
////				}
////			}
////
////
////		}
//	}


//	protected void generateHeaderColumn(Context ctx, ComponentImpl componentImpl, Element displayElement, BlockImpl blockImpl)throws Exception
//	{
////		if(displayElement!=null)
////		{
//// 			List displayList = displayElement.getChildren();
////			if(displayList!=null && displayList.size()>0)
////			{
////				Element viewType = (Element)displayList.get(0);
////				String type = viewType.getName();
////				if(HtmlConstants.LINK.equalsIgnoreCase(type))
////				{
////					if(viewType.getAttributeValue(HtmlConstants.HREF)!=null)
////					{	// Link
////						new LinkGenerator().parseHeaderLink(ctx, displayElement, viewType, getBlockLinkForHTMLControl(blockImpl, viewType));
////					}
////				}
////				// Other Control If Needed Here
////			}
////		}
//
//	}
//
//
//	protected void generateListColumn(Context ctx, ComponentImpl componentImpl, Element displayElement, Map record, String field, BlockImpl blockImpl, String appendValue)throws Exception
//	{
////		String fieldValue = "";
////		String selectedVal = "";
////
////		if(field.contains(HtmlConstants.SELECTED_ROW))
////		{
////			field = field.replace(HtmlConstants.SELECTED_ROW, "");
////			if(ctx.get(Constants.INET_EVENTID) != null && Constants.EDIT.equals(ctx.get(Constants.INET_EVENTID)))
////			{
////				Object object = ctx.get(field);
////				selectedVal = ParseUtil.getValueFromObject(object);
////			}
////		}
////		if(record.get(field)!=null)
////		{
////			Object object = record.get(field);
////			fieldValue = ParseUtil.getValueFromObject(object);
////
////		}
////
////
////		if(displayElement!=null)
////		{
//// 			List displayList = displayElement.getChildren();
////			if(displayList!=null && displayList.size()==0)
////			{
////				// Label
////				displayElement.setText(fieldValue);
////			}
////			else if(displayList!=null && displayList.size()>0)
////			{
////				Element viewType = (Element)displayList.get(0);
////				String type = viewType.getName();
////				if(HtmlConstants.INPUT.equals(type))
////				{
////					parseInputControls(displayElement, viewType,selectedVal, fieldValue, true, getBlockFieldForHTMLControl(blockImpl, displayElement), appendValue);
////				}
////				else if(Constants.SELECT.equalsIgnoreCase(type))
////				{
////					// Select
////					parseSelectControl(componentImpl, ctx, displayElement, viewType, fieldValue, true, getBlockFieldForHTMLControl(blockImpl, displayElement), appendValue);
////				}
////				else if(Constants.TEXTAREA.equalsIgnoreCase(type))
////				{
////					// TextArea
////					parseTextAreaControls(displayElement, viewType, fieldValue, true, getBlockFieldForHTMLControl(blockImpl, displayElement), appendValue);
////				}
////				else if(Constants.LABEL.equalsIgnoreCase(type))
////				{
////					// Label
////					parseLabelControls(displayElement, viewType, fieldValue, true, getBlockFieldForHTMLControl(blockImpl, displayElement), appendValue);
////				}
////				else if(HtmlConstants.LINK.equalsIgnoreCase(type))
////				{
////					if(viewType.getAttributeValue(HtmlConstants.HREF)!=null)
////					{
////						// Link
////						new LinkGenerator().parseRowLink(ctx, displayElement, viewType, record, getBlockLinkForHTMLControl(blockImpl, viewType));
////					}
////
////				}
////
////			}
////		}
//
//	}

//	protected void parseSelectControl(ComponentImpl componentImpl, Context ctx, Element container, Element sibling, String value, boolean listFlag, BlockfieldImpl blockfieldImpl, String appendValue)throws Exception
//	{
////		if(listFlag) // has to take care in case of list
////		{
//////			container.removeContent(sibling.detach());
//////			container.addContent(createSelect(sibling, null, null, value));
////			populateDropDown(componentImpl, ctx, container, sibling, value, listFlag, blockfieldImpl, appendValue);
////		}
////		else
////			//createSelect(sibling, null, null, value);
////			populateDropDown(componentImpl, ctx, container, sibling, value, listFlag, blockfieldImpl, appendValue);
////
//	}
//
//	protected void populateDropDown(ComponentImpl componentImpl, Context ctx, Element container, Element sibling, String value, boolean listFlag, BlockfieldImpl blockfieldImpl, String appendValue)throws Exception
//	{
////		String moNames = componentImpl.getMetaobject();
////		String[] moNamesList = {moNames};
////		if(moNames.contains(";"))
////			moNamesList = moNames.split(";");
////
////		boolean flag = false;
////		String name = null;
////		String control_name = null;
////		if(sibling.getAttributeValue(HtmlConstants.NAME)!=null)
////		{
////			name = sibling.getAttributeValue(HtmlConstants.NAME).toString();
////			if(appendValue!=null)
////			{
////				control_name = ParseUtil.appendValueWithCoumn(name, appendValue);
////			}
////		}
////
////		if(moNamesList.length > 0)
////		{
////			for(int i=0; i<moNamesList.length; i++)
////			{
////				String moName = (String)moNamesList[i];
////				MetaobjectImpl moImpl = metadataResources.getMetaobject(moName);
////				if(processMetaobject(moImpl, name, ctx, container, sibling, value, appendValue))
////				{
////					flag = true;
////					break;
////				}
////			}
////		}
////
////		if(!flag)
////		{
////			if(appendValue!=null)
////				createSelect(sibling, control_name, control_name, value);
////			else
////				createSelect(sibling, null, null, value);
////		}
//
//	}

//	public boolean processMetaobject(MetaobjectImpl moImpl, String propName, Context ctx, Element container, Element sibling, String value, String appendValue)throws Exception
//	{
////		PropertyImpl prop  = moImpl.getFirstPropertyversion().getProperty(propName);
////		boolean flag = false;
////		if(prop==null)
////			flag = false;
////
////		if(prop!=null)
////		{
////			DropdownImpl  dropdownImpl = prop.getFirstDropdown();
////			if(dropdownImpl!=null)
////			{
////				flag = true;
////				processDropDown(ctx, dropdownImpl, container, sibling, value, appendValue);
////			}
////			else
////			{
////				if(appendValue!=null)
////				{
////					String control_name = null;
////					if(sibling.getAttributeValue(HtmlConstants.NAME)!=null)
////					{
////						control_name = sibling.getAttributeValue(HtmlConstants.NAME).toString();
////						if(appendValue!=null)
////							control_name = ParseUtil.appendValueWithCoumn(control_name, appendValue);
////					}
////					createSelect(sibling, control_name, control_name, value);
////				}
////				else
////					createSelect(sibling, null, null, value);
////			}
////		}
////
////		return flag;
//		return true;
//	}
//
//	public void processDropDown(Context ctx, DropdownImpl dropdownImpl, Element container, Element sibling, String value, String appendValue)throws Exception
//	{
////		DisplayfieldImpl disFld = dropdownImpl.getFirstDisplayfield();
////		ValuefieldImpl valFld = dropdownImpl.getFirstValuefield();
////		String dropDownListId = dropdownImpl.getName();
////
////		List optionList = null;
////		String displayText = null;
////		String displayValue = null;
////
////		if(ctx.get(dropDownListId)!=null)
////		{
////			Object obj = ctx.get(dropDownListId);
////			if(obj instanceof List)
////				optionList = (List)obj;
////		}
////
////		if(disFld!=null && valFld!=null)
////		{
////			displayText = disFld.getField();
////			displayValue = valFld.getField();
////		}
////
////		if(displayText!=null && displayValue!=null)
////		{
////			generateDropDownControl(sibling, optionList, displayValue, displayText, value, appendValue);
////		}
//	}
//
//	public void generateDropDownControl(Element sibling, List optionList, String displayValue, String displayText, String value, String appendValue)throws Exception
//	{
////		if(optionList == null)
////			return;
////
////		for(int i=0; i<optionList.size(); i++)
////		{
////			String optionValue = null;
////			String optionText = null;
////			Map optionMap = (Map)optionList.get(i);
////
////			Object object = optionMap.get(displayValue);
////			optionValue = ParseUtil.getValueFromObject(object);
////
////			object = optionMap.get(displayText);
////			optionText = ParseUtil.getValueFromObject(object);
////
////			sibling.addContent(createOption(null, optionValue , optionText, value));
////
////
////		}
////
////		if(appendValue!=null)
////		{
////			String control_name = null;
////			if(sibling.getAttributeValue(HtmlConstants.NAME)!=null)
////			{
////				control_name = sibling.getAttributeValue(HtmlConstants.NAME).toString();
////				if(appendValue!=null)
////					control_name = ParseUtil.appendValueWithCoumn(control_name, appendValue);
////			}
////			ParseUtil.addAttribute(sibling, HtmlConstants.NAME, control_name);
////			ParseUtil.addAttribute(sibling, HtmlConstants.ID, control_name);
////		}
//	}

//	public boolean rectifyLink(Element sibling , Context ctx, Map record, BlocklinkImpl blocklinkImpl)throws Exception
//	{
//		boolean detachFlag = false;
////		if(sibling.getAttributeValue(HtmlConstants.HREF)==null)
////			return detachFlag;
////
////		String href = sibling.getAttributeValue(HtmlConstants.HREF).toString();
////		String[] hrefs = ParseUtil.processHref(href);
////		if(hrefs!=null)
////		{
////			String forward = hrefs[0];
////			if(forward.contains("."))
////				forward = forward.substring(0,forward.indexOf("."));
////
////			String action = hrefs[1];
////			if(action.contains("."))
////			{
////				int dot_index = action.indexOf(".");
////				forward = action.substring(0,action.indexOf("."));
////				action = action.substring(dot_index+1, action.length());
////			}
////
//////			if(action.equals("continueupdateVehicles"))
//////				System.out.println("GOT");
////
////			// Block Id Temporary
////			String evalType = hrefs[2];
////			String evalId = hrefs[3];
////			boolean evalFlag = true;
////
////			if(blocklinkImpl!=null)
////			{
////				if(blocklinkImpl.getEval()!=null)
////					evalFlag = blocklinkImpl.evaluate(ctx);
////			}
////
////			String attribute = getAttributeContent(forward, action, ctx, record);
////			if(!"".equals(attribute))
////				sibling.setAttribute(HtmlConstants.HREF, attribute);
////
////			if(!evalFlag)
////			{
////				sibling.detach();
////				detachFlag = true;
////			}
////
////		}
//
//		return detachFlag;
//	}
//

//	public boolean rectifyLink(Element sibling , Context ctx, PagecomponentImpl pagecomponentImpl)throws Exception
//	{
//		boolean detachFlag = false;
////		if(sibling.getAttributeValue(HtmlConstants.HREF)==null)
////			return detachFlag;
////
////		String href = sibling.getAttributeValue(HtmlConstants.HREF).toString();
////		String[] hrefs = ParseUtil.processHref(href);
////		if(hrefs!=null)
////		{
////			String forward = hrefs[0];
////			if(forward.contains("."))
////				forward = forward.substring(0,forward.indexOf("."));
////
////			String action = hrefs[1];
////			if(action.contains("."))
////			{
////				int dot_index = action.indexOf(".");
////				forward = action.substring(0,action.indexOf("."));
////				action = action.substring(dot_index+1, action.length());
////			}
////
//////			if(action.equals("continueupdateVehicles"))
//////				System.out.println("GOT");
////
////			LinkImpl formlinkImpl = getLinkImpl(pagecomponentImpl,action);
////
////			// Block Id Temporary
////			String evalType = hrefs[2];
////			String evalId = hrefs[3];
////			boolean evalFlag = true;
////
////			if(formlinkImpl!=null)
////			{
////				if(formlinkImpl.getEval()!=null)
////					evalFlag = formlinkImpl.evaluate(ctx);
////			}
////
////			String attribute = getAttributeContent(forward, action, ctx, null);
////			if(!"".equals(attribute))
////				sibling.setAttribute(HtmlConstants.HREF, attribute);
////
////			if(!evalFlag)
////			{
////				sibling.detach();
////				detachFlag = true;
////			}
////		}
//
//		return detachFlag;
//	}

//	public void rectifyMenuLink(Element container, Element sibling , Context ctx, Map record, BlocklinkImpl blocklinkImpl)throws Exception
//	{
////		if(sibling.getAttributeValue(HtmlConstants.HREF)==null)
////			return ;
////
////		String href = sibling.getAttributeValue(HtmlConstants.HREF).toString();
////		String[] hrefs = ParseUtil.processHref(href);
////		if(hrefs!=null)
////		{
////			String forward = hrefs[0];
////			if(forward.contains("."))
////				forward = forward.substring(0,forward.indexOf("."));
////
////			String action = hrefs[1];
////			if(action.contains("."))
////			{
////				int dot_index = action.indexOf(".");
////				forward = action.substring(0,action.indexOf("."));
////				action = action.substring(dot_index+1, action.length());
////			}
////
////			// Block Id Temporary
////			String evalType = hrefs[2];
////			String evalId = hrefs[3];
////
////			String attribute = getAttributeContent(forward, action, ctx, record, true);
////			if(!"".equals(attribute))
////				sibling.setAttribute(HtmlConstants.HREF, attribute);
////
////		}
//	}
//
//	public void rectifyLink(Element container, Element sibling , Context ctx, Map record, BlocklinkImpl blocklinkImpl)throws Exception
//	{
////		if(sibling.getAttributeValue(HtmlConstants.HREF)!=null)
////		{
////			if(ParseUtil.isElementContainingAttributeWithValue(sibling, HtmlConstants.HREF, HtmlConstants.SUBMIT_FORM))
////			{
////				rectifyLinkByHRefAttribute(container, sibling , ctx, record, blocklinkImpl);
////			}
////		}
////
////		if(sibling.getAttributeValue(HtmlConstants.ID)!=null)
////		{
////			if(ParseUtil.isElementContainingAttributeWithValue(sibling, HtmlConstants.ID, HtmlConstants.RIGHT_CLICK_MENU))
////			{
////				rectifyLinkByIDAttribute(container, sibling , ctx, record, blocklinkImpl);
////			}
////		}
////
////		if(sibling.getAttributeValue(HtmlConstants.ONCLICK)!=null)
////		{
////			if(ParseUtil.isElementContainingAttributeWithValue(sibling, HtmlConstants.ONCLICK, HtmlConstants.RETURN_VALUES))
////			{
////				rectifyLinkByOnClickAttribute(container, sibling , ctx, record, blocklinkImpl);
////			}
////		}
////
////		rectifyLinkByContent(container, sibling , ctx, record, blocklinkImpl);
//
//	}
//
//	public void rectifyLinkByContent(Element container, Element sibling , Context ctx, Map record, BlocklinkImpl blocklinkImpl)throws Exception
//	{
////		String fieldName = "";
////		String fieldValue = null;
////		if(container.getAttributeValue(HtmlConstants.ID)!=null)
////		{
////			fieldName = container.getAttributeValue(HtmlConstants.ID);
////			fieldName = getFieldNameForList(fieldName);
////			if(record.get(fieldName)!=null)
////			{
////				Object object = record.get(fieldName);
////				fieldValue = ParseUtil.getValueFromObject(object);
////			}
////
////		}
////
////		if(!fieldName.endsWith(HtmlConstants.ROW_LINK))
////		{
////			if(fieldValue!=null)
////			{
////				sibling.removeContent();
////				sibling.addContent(fieldValue);
////			}
////			else
////				sibling.removeContent();
////		}
//	}
//
//
//	public void rectifyLinkByOnClickAttribute(Element container, Element sibling , Context ctx, Map record, BlocklinkImpl blocklinkImpl)throws Exception
//	{
////		String href = sibling.getAttributeValue(HtmlConstants.ONCLICK).toString();
////		String[] hrefs = ParseUtil.processHref(href);
////		String[] hrefValues = null;
////		if(hrefs!=null)
////		{
////			hrefValues = new String[hrefs.length];
////			for(int i=0; i<hrefs.length; i++)
////			{
////				String paramName = hrefs[i];
////				String paramValue = null;
////				if(record !=null)
////				{
////					if(record.get(paramName)!=null)
////					{
////						Object object = record.get(paramName);
////						paramValue = ParseUtil.getValueFromObject(object);
////					}
////				}
////				hrefValues[i] = paramValue;
////			}
////		}
////
////		if(hrefValues!=null)
////		{
////			String attribute = getOnClickContent(hrefValues);
////			if(!"".equals(attribute))
////				sibling.setAttribute(HtmlConstants.ONCLICK, attribute);
////		}
//	}
//
//
//	public void rectifyLinkByHRefAttribute(Element container, Element sibling , Context ctx, Map record, BlocklinkImpl blocklinkImpl)throws Exception
//	{
////		String href = sibling.getAttributeValue(HtmlConstants.HREF).toString();
////		String[] hrefs = ParseUtil.processHref(href);
////		if(hrefs!=null)
////		{
////			String forward = hrefs[0];
////			if(forward.contains("."))
////				forward = forward.substring(0,forward.indexOf("."));
////
////			String action = hrefs[1];
////			if(action.contains("."))
////			{
////				int dot_index = action.indexOf(".");
////				forward = action.substring(0,action.indexOf("."));
////				action = action.substring(dot_index+1, action.length());
////			}
////
////			// Block Id Temporary
////			String evalType = hrefs[2];
////			String evalId = hrefs[3];
////			boolean evalFlag = true;
////
////			if(blocklinkImpl!=null)
////			{
////				if(blocklinkImpl.getEval()!=null)
////					evalFlag = blocklinkImpl.evaluate(ctx);
////			}
////
////			String attribute = getAttributeContent(forward, action, ctx, record);
////			if(!"".equals(attribute))
////				sibling.setAttribute(HtmlConstants.HREF, attribute);
////
////		}
//	}
//
//	public void rectifyLinkByIDAttribute(Element container, Element sibling , Context ctx, Map record, BlocklinkImpl blocklinkImpl)throws Exception
//	{
////		String id = sibling.getAttributeValue(HtmlConstants.ID).toString();
////		String[] ids = id.split(";");
////		String forward = ids[1];
////		String action = ids[2];
////		String attribute = createAttributeContentMenu(forward, action , ctx, record);
////
////		if("".equals(attribute))
////			attribute = ids[0]+";";
////		else
////			attribute = ids[0] + attribute;
////
////		sibling.setAttribute(HtmlConstants.ID, attribute);
//
//	}
//
//	public String createAttributeContentMenu(String component_page, String action , Context ctx, Map record) throws Exception
//	{
//		StringBuffer buffer = new StringBuffer();
//
////		ComponentImpl componentImpl = ComponentResources.getInstance(ctx).getComponent(component_page);
////		if(componentImpl == null)
////			return "";
////
////		ActionImpl actionImpl = componentImpl.getAction(action);
////		if(actionImpl == null)
////			return "";
////
////		List paramList = actionImpl.getActionparamList();
////		buffer.append(generateDynaValuesMenu(paramList, ctx, record));
//
//		return buffer.toString();
//	}

	public String generateDynaValuesMenu(List paramList, Context ctx, Map record,HttpServletRequest request)
	{
		StringBuffer buffer = new StringBuffer();
		if(paramList == null)
			return "";

		if(paramList!=null)
		{
			StringBuffer bufferExtraDynaValuess = new StringBuffer();

			for(int i=0; i<paramList.size(); i++)
			{
				ParamImpl paramImpl= (ParamImpl)paramList.get(i);
				String paramName = paramImpl.getName();
				String paramValue = paramImpl.getVal();

				if(paramImpl.getPre() && (paramImpl.getScope()!=null && paramImpl.getScope().equals("menu")))
				{
					if(record !=null)
					{
						if(record.get(paramName)!=null)
							paramValue = (String)record.get(paramName);
					}

					if(paramValue ==null)
					{
						if(paramImpl.getSession())
						{
//							HttpServletRequest request = (HttpServletRequest)ctx.get(Constants.HTTPREQUEST);
							HttpSession session =  request.getSession();
							if(session.getAttribute(paramName)!=null)
							{
								paramValue = (String)session.getAttribute(paramName);
							}

						}
					}

					if(paramValue ==null)
					{
						if(ctx.get(paramName)!=null)
							paramValue = (String)ctx.get(paramName);
					}

					if(paramValue!=null)
						bufferExtraDynaValuess.append(paramValue+";");
				}
			}

			buffer.append(";"+bufferExtraDynaValuess);
		}

		return buffer.toString();
	}

	public String getFieldNameForList(String field)
	{
		try {

			if(field.contains(HtmlConstants.BLOCK_FIELD))
				field = field.substring(field.indexOf(HtmlConstants.BLOCK_FIELD)+12, field.length());

		}catch(Exception e) {
			logger.debug("Block field " +  field + " was not in correct fromat");
		}

		return field;
	}

	//11/6/2013
	//Method created to execute rule added for viewparam attribute in <actionparam> node
	private Object executeRule(Context ctx, String ruleName, String paramName){
		try{
			RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(ruleName);
	      	if(ruleImpl != null){
        		Object flag = ruleImpl.execute(ctx, null);
        		logger.info(ctx, flag);
        		return flag.toString();
        	}
		}catch (Exception e) {
			logger.error(ctx, "Invalid rule found while creating viewparam - " + paramName);
		}
		
		return null;
	}
	
	//Method created to hide/show based on applicationworkflow xml
	private boolean isBlockhideByApplicationWorkflow(Context ctx, String id, Element sibling) throws Exception{
		try{
			if(id.equals("submitForApprovalBeforeImport"))
				logger.debug("found");
			
			EventImpl eventImpl = ApplicationWorkflowResources.getInstance(ctx).getEventById(id);
			if(eventImpl != null){
				if(ctx.get("inet_workflow_event") != null && !ctx.get("inet_workflow_event").toString().equals(HtmlConstants.EMPTY) && eventImpl.getSortedActionParamList() != null && eventImpl.getSortedActionParamList().size() > 0){
					for(int i=0; i<eventImpl.getSortedActionParamList().size(); i++){
						ActionparamImpl actionparamImpl = (ActionparamImpl)eventImpl.getSortedActionParamList().get(i);
						
						if(actionparamImpl.getName().equals("context_workflow_event")){
							if(StringUtils.isNotBlank(actionparamImpl.getVal()) && !ctx.get("inet_workflow_event").toString().equals(actionparamImpl.getVal())){
								return true;
							}
							
							break;
						}
					}
				}
				
				if(eventImpl.getAccesslevel() != null){
					if(eventImpl.getAccesslevel().equals("0"))
						return true;
					
					if(eventImpl.getAccesslevel().equals("1"))
						return false;
					
					StringTokenizer tokens = new StringTokenizer(eventImpl.getAccesslevel(), ",");
					while(tokens.hasMoreTokens()){
						String token = tokens.nextToken();
						
						//checking for rules
						if(token.startsWith(HtmlConstants.EVAL)){
							try{
								String eval = token.substring(token.indexOf(":")+1, token.length());
								if(!isElementToMakeHide(eval, ctx)){
									return true;
								}
							}catch (Exception e) {
								logger.error(ctx, "Unable to execute rule "+token+" due to error : " + e.getMessage());
							}
						}
						
						//checking for security.xml
						int accessType = SecurityResources.getInstance(ctx).getAccessType(token, ctx);
						if(SecurityParser.isHideResource(accessType)){
							return true;
						}
					}
				}
			}
			
			/*if(eventImpl.getDescription() != null){
				if(sibling.getContent() != null && sibling.getContent().get(0) != null && sibling.getContent().get(0) instanceof Element){
					Element element = (Element)sibling.getContent().get(0);
					if(element.getName().equals(HtmlConstants.SPAN))
						element.setText(new DataUtils().getLabelFromLabelConf(eventImpl.getDescription()));
				}
			}*/
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	
	//Method created to check for masking in securityimpl.xml
	private boolean isMaskingExists(Context ctx, String id) throws Exception{
		if(StringUtils.isBlank(id))
			return false;
		
		if(id.startsWith(HtmlConstants.AJAX_FIELD))
			id = id.substring(id.indexOf(HtmlConstants.AJAX_FIELD)+11, id.length());
		
		String currentPageComp = ctx.get(HtmlConstants.NEXT_PAGE_COMPONENT) != null ? ctx.get(HtmlConstants.NEXT_PAGE_COMPONENT).toString() : HtmlConstants.EMPTY;
    	try{
    		if(ProcessFlowResources.getInstance(ctx).getPagecomponent(currentPageComp).getParent() != null){
    			String parent = ProcessFlowResources.getInstance(ctx).getPagecomponent(currentPageComp).getParent();
    			
    			parent = parent.substring(0, parent.indexOf(".html"));
    			
    			currentPageComp = parent;
    		}
    		
			String securityId = currentPageComp + "." + id + "_security";
	    	ProtectedresourceImpl pImpl = SecurityResources.getInstance(ctx).getProtectedResource(securityId);
	    	if(pImpl != null && pImpl.getBusinessroleList() != null && pImpl.getBusinessroleList().size() > 0){
	    		
	    		for(int i=0; i<pImpl.getBusinessroleList().size(); i++){
	    			BusinessroleImpl businessRoleImpl = (BusinessroleImpl)pImpl.getBusinessroleList().get(i);
    	    		
    	    		if(businessRoleImpl != null && businessRoleImpl.getRoleid().equals(ctx.get("roles")) && 
    	    				businessRoleImpl.getRoleaccesslevelList() != null && businessRoleImpl.getRoleaccesslevelList().size() > 0){
    	    			
    	    			RoleaccesslevelImpl roleAccessLevelImpl = (RoleaccesslevelImpl)businessRoleImpl.getRoleaccesslevelList().get(0);
	    				if(roleAccessLevelImpl != null && businessRoleImpl.getIsencrypt() != null && businessRoleImpl.getIsencrypt().equals("Y")){
	    					return true;
	    				}
    	    		}
	    		}
	    	}
	    	
	    	if(pImpl != null && pImpl.getUserdefinedList() != null && pImpl.getUserdefinedList().size() > 0){
	    		
	    		for(int i=0; i<pImpl.getUserdefinedList().size(); i++){
	    			UserdefinedImpl userdefinedImpl = (UserdefinedImpl)pImpl.getUserdefinedList().get(i);
    	    		
	    			if(userdefinedImpl != null && StringUtils.isNotBlank(userdefinedImpl.getRuleid())){
	    				RuleImpl ruleImpl = (RuleImpl)RulesResources.getInstance(ctx).findRule(userdefinedImpl.getRuleid());
	    				if(ruleImpl.evaluate(ctx, null)){
	    					UserdefinedaccesslevelImpl userdefinedaccesslevelImpl = (UserdefinedaccesslevelImpl)userdefinedImpl.getUserdefinedaccesslevelList().get(0);
	    					
	    					if(userdefinedaccesslevelImpl != null && userdefinedImpl.getIsencrypt() != null && userdefinedImpl.getIsencrypt().equals("Y")){
    	    					return true;
    	    				}
	    				}
	    			}
	    		}
	    	}
    	}catch (Exception e) {
    		//Do nothing
		}
		
		return false;
	}
	
	public void isElementExistsInSecurity(Context ctx, Element sibling, String id, String pageName){
		try{
			if(SystemProperties.getInstance().getProperty("appl.configuration.applicable") != null &&
					SystemProperties.getInstance().getProperty("appl.configuration.applicable").toString().equals("Y") &&
					SystemProperties.getInstance().getProperty("appl.configuration.showtooltip") != null &&
					SystemProperties.getInstance().getProperty("appl.configuration.showtooltip").toString().equals("Y")){
				
				if(SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES) != null && 
						!SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES).toString().equals(HtmlConstants.EMPTY) &&
						("|"+SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES).toString()+"|").contains("|"+ctx.get(HtmlConstants.ROLES).toString()+"|")){
					
					if(SecurityResources.getInstance(ctx).getProtectedResource(pageName+"."+id)!= null){
						String title = sibling.getAttributeValue(HtmlConstants.TITLE);
		    			if(StringUtils.isBlank(title))
		    				title = "Security ID : " + id;
		    			else
		    				title = title + "; Security ID : " + id;
		    			
		    			sibling.setAttribute(HtmlConstants.TITLE, title);
					}else if(SecurityResources.getInstance(ctx).getProtectedResource("common"+"."+id)!= null){
						String title = sibling.getAttributeValue(HtmlConstants.TITLE);
		    			if(StringUtils.isBlank(title))
		    				title = "Security ID : " + id;
		    			else
		    				title = title + "; Security ID : " + id;
		    			
		    			sibling.setAttribute(HtmlConstants.TITLE, title);
					}
				}
			}
		}catch(Exception e){
			
		}
	}
	
	public Element createFile(Element file, String name, String id, String value, Context ctx, Map record) throws Exception
	{
		if(file==null)
			file = new Element(HtmlConstants.INPUT);

		ParseUtil.addAttribute(file, HtmlConstants.TYPE, "file");
		
		if(name!=null)
			ParseUtil.addAttribute(file, HtmlConstants.NAME, name);
		if(id!=null)
			ParseUtil.addAttribute(file, HtmlConstants.ID, id);
		if(value!=null)
			ParseUtil.addAttribute(file, HtmlConstants.VALUE, value);

		//Added code to create tooltiptext for component
		if(file.getAttributeValue(HtmlConstants.TITLE) != null){
			String title = file.getAttributeValue(HtmlConstants.TITLE);
			
			title = ctx.get(title) != null ? ctx.get(title).toString() : title;
			
			title = new DataUtils().getLabelFromLabelConf(title);
			
			ParseUtil.addAttribute(file, HtmlConstants.TITLE, title);
		}
		//Ended code
		
		//Added code to check for encryption/decryption from security xml
		if(file.getAttributeValue(HtmlConstants.ID) != null){
			id = file.getAttributeValue(HtmlConstants.ID);
			if(record != null && id.contains("_") && id.lastIndexOf("_") < id.length()){
				id = id.substring(id.lastIndexOf("_")+1, id.length());
				if(com.util.StringUtils.isNumeric(id))
					id = file.getAttributeValue(HtmlConstants.ID).substring(0, file.getAttributeValue(HtmlConstants.ID).lastIndexOf("_"));
			}
				
			value = SecurityResources.getInstance(ctx).checkForEncryptionDecryption(ctx, id, HtmlConstants.TEXT, value);
			if(ctx.get(id+"_encryptreadonly") != null && ctx.get(id+"_encryptreadonly").toString().equals("Y")){
				ParseUtil.addAttribute(file, HtmlConstants.DISABLED, HtmlConstants.DISABLED);
				ctx.remove(id+"_encryptreadonly");
			}
			if(ctx.get(id+"_encrypt") != null && ctx.get(id+"_encrypt").toString().equals("Y")){
				if(ctx.get(id+"_encrypttext") != null && ctx.get(id+"_encrypttext").toString().equals("Y"))
					file.setAttribute(HtmlConstants.TYPE, Constants.TEXT);
				else	
					file.setAttribute(HtmlConstants.TYPE, Constants.PASSWORD);
				
				ctx.remove(id+"_encrypt");
				ctx.remove(id+"_encrypttext");
			}
			
			file.setAttribute(HtmlConstants.VALUE, value != null ? value : HtmlConstants.EMPTY);
		}
		//Ended code
		
		//going to add in pageFieldsList for validations phase
		if(file.getAttributeValue(HtmlConstants.DISABLED) == null || !file.getAttributeValue(HtmlConstants.DISABLED).equalsIgnoreCase(HtmlConstants.DISABLED)){
			if(ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE) != null){
				String NEXT_PAGE_FOR_PARSE = ctx.get(HtmlConstants.NEXT_PAGE_FOR_PARSE).toString();
				
				if(ctx.get(NEXT_PAGE_FOR_PARSE+"_pageFieldsList") == null){
					ctx.put(NEXT_PAGE_FOR_PARSE+"_pageFieldsList", file.getAttributeValue(HtmlConstants.NAME)+"_FIELDTYPE=select");
				}else{
					ctx.put(NEXT_PAGE_FOR_PARSE+"_pageFieldsList", ctx.get(NEXT_PAGE_FOR_PARSE+"_pageFieldsList") + "," + file.getAttributeValue(HtmlConstants.NAME)+"_FIELDTYPE=select");
				}
			}
		}
		//Ended code
		
		return file;
	}
}
