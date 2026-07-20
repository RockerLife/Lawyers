package com.manage.process;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.manage.framework.ProcessAction;
import com.manage.managebusinessrules.rules.RuleImpl;
import com.manage.managebusinessrules.rules.RulesResources;
import com.manage.managecomponent.components.ComponentImpl;
import com.manage.managecomponent.components.ComponentResources;
import com.manage.managecomponent.components.SetParametersForStoredProcedures;
import com.manage.managecomponent.processflow.PagecomponentImpl;
import com.manage.managecomponent.processflow.ProcessFlowResources;
import com.manage.managemetadata.functions.commonfunctions.DataUtils;
import com.manage.managemetadata.metadata.DisplayfieldImpl;
import com.manage.managemetadata.metadata.DropdownImpl;
import com.manage.managemetadata.metadata.FieldImpl;
import com.manage.managemetadata.metadata.MetaDataResources;
import com.manage.managemetadata.metadata.MetaobjectImpl;
import com.manage.managemetadata.metadata.PropertyImpl;
import com.manage.managemetadata.metadata.PropertyversionImpl;
import com.manage.managemetadata.metadata.ValuefieldImpl;
import com.manage.managemetadata.security.SecurityResources;
import com.manage.managemetadata.util.exception.ValidationException;
import com.manage.managereporting.graphs.GraphResources;
import com.manage.managereporting.graphs.GraphdefImpl;
import com.manage.parser.ControlGenerator;
import com.manage.parser.ElementProcessor;
import com.manage.parser.FreeFormGenerator;
import com.manage.parser.HelpMessageProcessor;
import com.manage.parser.ListFormGenerator;
import com.manage.parser.MenuParser;
import com.manage.parser.ParseUtil;
import com.manage.parser.SecurityParser;
import com.manage.parser.calendar.DynamicCalendar;
import com.manage.parser.link.FormLinkGenerator;
import com.manage.util.PageUtils;
import com.ormapping.ibatis.SqlResources;
import com.util.CacheManager;
import com.util.Constants;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.IOUtils;
import com.util.IResourceKeys;
import com.util.InetLogger;
import com.util.ObjectCloner;
import com.util.ResourceLoader;
import com.util.StringUtils;
import com.util.SystemProperties;
import com.util.XMLUtils;

public class HTMLGenerator {
	
	private static InetLogger logger = InetLogger.getInetLogger(HTMLGenerator.class);
	
	protected Context ctx;
	protected IResourceKeys keys;	
	
	protected String projectName;
	protected String next_page;	
	protected String ERROR_INDICATION_MSG = "Please correct the errors highlighted in red below";
	
	
	protected ComponentImpl componentImpl;
	protected MetaDataResources metadataResources;
	protected PagecomponentImpl pagecomponentImpl;
	protected Set importSet;
	protected Set strConstantSet;
	protected int language;
	
	public static void main(String args[])	
	{
		try
		{
			HTMLGenerator cps = new HTMLGenerator("DSPro", "nameandaddress");
			cps.process();
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
		}
		
	}
	
	public HTMLGenerator(String projectName)
	{
		this.projectName = projectName;
	}	
	
	public HTMLGenerator(String projectName, String next_page)
	{
		this.projectName = projectName;
		this.next_page = next_page;
	}
	
	public HTMLGenerator(String projectName,Set importSet,Set strConstantSet,int language){
    	
		this.projectName = projectName;
		this.importSet = importSet;
		this.strConstantSet = strConstantSet;
		this.language = language;
		ctx = new Context();
		ctx.setProject(projectName);
		
    }
	
	
	public void loadResources(String projectName) throws Exception  
    {
		  
		ResourceLoader.load("D:\\OutlineSys\\ManageMetadata\\src\\XML\\DSPro\\components.xml", "components", projectName);
			  
//	   	ResourceLoader.load("D:\\OutlineSys\\ManageMetadata\\src\\XML\\DSPro\\metadata.xml", "metadata");
//	    ResourceLoader.load("D:\\OutlineSys\\ManageMetadata\\src\\XML\\DSPro\\messages.xml", "messages");
//      ResourceLoader.load("D:\\OutlineSys\\ManageMetadata\\src\\XML\\DSPro\\functions.xml", "functions");
//      ResourceLoader.load("D:\\OutlineSys\\ManageMetadata\\src\\XML\\DSPro\\expressions.xml", "expressions");
//      ResourceLoader.load("D:\\OutlineSys\\ManageMetadata\\src\\XML\\DSPro\\processflow.xml", "processflow");
//      SqlResources.load("DSPro", "D:\\OutlineSys\\ManageMetadata\\src\\XML\\DSPro\\ibatis\\maps\\SqlMapConfig.xml");
      
    }
	protected void process() throws Exception
	{
		//loadResources(projectName);
		Context ctx = new Context();
		ctx.setProject(projectName);
		//populateContext(ctx);
		try
		{			
			//String html = IOUtils.readFile("D:\\OutlineSys\\Manage\\web\\nameandaddress.html");
			String html = IOUtils.readFile("D:\\OutlineSys\\Manage\\web\\test.html");
			Document document = getDocument(html, "test");
			//parseDocument(ctx, document);
			parseTest(ctx, document);
			
		}
		catch(Exception e)
		{
//			System.out.println(e.toString());
		}
		
	}
		
	protected void parseTest(Context ctx, Document document)throws Exception
	{
		Element root = document.getRootElement();
		Element body=root.getChild(HtmlConstants.BODY);		
		Element form=body.getChild(HtmlConstants.FORM);		
		
		List list = form.getChildren();
		List newList = new ArrayList();
		Element element = null;
		Element newElement = null;
		if (list != null)
		{  
			for (int i = 0; i < list.size(); i++)
			{
				element = (Element)list.get(i);
				element.detach();	
				newElement = ParseUtil.createCopyForElement(element);
				newList.add(newElement);
			}
			form.addContent(newList);
		}
		
		try 
		{
			logger.debug("Generated HTML document");
		} 
		catch (Exception e)
		{
			logger.error("Unexpected error", e);
		}	
		
	}	
	
	protected void populateContext(Context ctx)
	{
//		ctx.put("FirstName", "Raghuraj");
//		ctx.put("MI", "Singh");
//		ctx.put("LastName", "Pal");		
//		ctx.put("Suffix", "Bhagel");
//		ctx.put("EmployerAddress1", "D224 School Block Nathu Colony Delhi");
//		ctx.put("Country", "USA");
//		ctx.put("Langauge", "Hindi");
//		ctx.put("Prefix", "Miss");
		ctx.put("cust_id", "35");
		populateFreeform(ctx);
		populateList(ctx);
		
	}
	
	protected void populateFreeform(Context ctx)
	{				
		Map freefom_block = new HashMap();
		freefom_block.put("FirstName", "Raghuraj");
		freefom_block.put("MI", "Singh");
		freefom_block.put("LastName", "Pal");		
		freefom_block.put("Suffix", "Bhagel");
		freefom_block.put("EmployerAddress1", "D224 School Block Nathu Colony Delhi");
		freefom_block.put("Country", "USA");
		freefom_block.put("Langauge", "Hindi");
		freefom_block.put("Prefix", "Miss");		
		ctx.put("nameandaddress_freeform_1", freefom_block);
		
	}
	
	protected void populateList(Context ctx)
	{
		List <Map>list_block = new ArrayList<Map>();
		
		Map record1 = new HashMap();
		record1.put("name", "raghuraj");
		record1.put("address", "delhi");
		list_block.add(record1);
		
		Map record2 = new HashMap();
		record2.put("name", "singh");
		record2.put("address", "jaipur");
		list_block.add(record2);
		ctx.put("policylist_list_1", list_block);
		
	}
	
	 public Document getDocument(String html, String pageName){	
		
		Document document = null;
		StringReader reader = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			html = cleanContent(html);
			reader = new StringReader(html);
			document = builder.build(reader);
			//reader.close();
		}
		catch(Exception e){
			logger.debug("Unable to parse the html "+pageName);
		}
		finally{
			if(reader != null)
				reader.close();
		}
		return document;
	}
	
	protected Element getFormElement(Element body)throws Exception
	{
		List childs = body.getChildren();
		if(childs!=null)
		{
			for(int i=0; i<childs.size(); i++)
			{
				Element child = (Element)childs.get(i);
				if(child.getName().equalsIgnoreCase(HtmlConstants.FORM))
				{	
					return child;
				}
				else					
					return getFormElement(child);
			}
		}
		return null;
	}
	
	//changing for logging by vikas
	public Document parseDocument(Context ctx, Document document, String pageName,HttpServletRequest request)throws Exception
	{
		Element root= null;
		try 
		{	
			
			if(document!=null)
			{	
				root = document.getRootElement();
				if(root!=null)
				{
					Element body=root.getChild(HtmlConstants.BODY);
					if(body==null)
					{
						logger.debug(pageName + " does not contain BODY Element");
						return document;
					}
					Element form=body.getChild(HtmlConstants.FORM);
				
					Element head = root.getChild(HtmlConstants.HEAD);	
					appendItemsToHeadSection(head);
					
					if(form!=null)
						appendHiddenFields(form, ctx);  
					
					if(form==null)
					{
						logger.debug(pageName + " does not contain FORM Element");
						form = body;
					}
				
					//Element form = getFormElement(body);
					String ajax_append_field_value = (ctx.get("ajax_append_field_value") != null && !"".equals(ctx.get("ajax_append_field_value")))?ctx.get("ajax_append_field_value").toString():null;
					 
				
					parseHTMLMenu(ctx, body, body.getChildren(),request, pageName);
				
					List children = body.getChildren();
					
					//setting page name to NEXT_PAGE_FOR_PARSE to be used in ControlGenerator class
					ctx.put("NEXT_PAGE_FOR_PARSE", pageName);
					parseHTML(ctx, body, children, root,false,ajax_append_field_value,request, pageName);
					//rectifyScriptTag(root);
					
					if(isExistErrorList(ctx,body.getChildren()))
					{
						Element elementErrorListDiv = getErrorDiv(ctx, body.getChildren(), HtmlConstants.ERRORS_LIST);
						if(elementErrorListDiv != null)
							createErrorList(ctx, elementErrorListDiv);
					}
					else
					{
						populateErrorDiv(ctx, body);
					}
					
					//rectifyErrorList(ctx,body);		
				}
			 
			//cleanHTMLOutput(root);
			//System.out.println(XMLUtils.beautifyXML(root.getDocument()));
			}
			
		} 
		catch (Exception e)
		{
			logger.error("Unexpected error", e);
			throw e;
		}
		
		return document;
	}
	
	 
	public void parseHTMLMenu(Context ctx, Element element, List children,HttpServletRequest request, String pageName)throws Exception
	{
		Element blockelement = null;			
		if (children != null)
		{  
			for (int i = 0; i < children.size(); i++)
			{	 
        	  blockelement = (Element)children.get(i);
              Attribute elementsAttr =  blockelement.getAttribute(HtmlConstants.ID);
              if(elementsAttr!=null)
              {
	 			  String blockid = elementsAttr.getValue();
	 			  if(blockid.contains(HtmlConstants.CUSTOM_MENU_CONTEXT))
	 			  {
	 				 new MenuParser().parseMenu(ctx, blockelement, blockelement.getChildren(),request, pageName);
	 				
	 			  }
              }     
              
			}
		}
	}
	
	public void parseHTML(Context ctx, Element element, List children, Element rootElement, boolean readOnlyResourse,
			String ajax_append_field_value, HttpServletRequest request, String pageName) throws Exception {
		
		Element blockelement = null;			
		if (children != null){  
			for (int i = 0; i < children.size(); i++){	 
				boolean detachFlag = false;
				blockelement = null;	
				blockelement = (Element)children.get(i);
				Attribute elementsAttr =  blockelement.getAttribute(HtmlConstants.ID);
				List subchildren = blockelement.getChildren();
				Map currentData = null;
				List currentList = null;
				String type = blockelement.getName();
              
				ElementProcessor ep = new ElementProcessor(metadataResources, pagecomponentImpl, componentImpl);
				if(ep.processElement(blockelement , ctx, null, null,request)){
					i--;
					continue;
				}
              
				//Added dummy processflow entry for test harness pages
				if(next_page.equals("testIntegration") || next_page.equals("testQuery") || 
						next_page.equals("logsInformation") || next_page.equals("testHarness")
						|| next_page.equals("sessionList")|| next_page.equals("showErrors")){
					
					PagecomponentImpl pageCompImpl = new PagecomponentImpl();
					pageCompImpl.setName(next_page);
					pageCompImpl.setPath(next_page+".html");
					
					pagecomponentImpl = pageCompImpl;
				}else
					pagecomponentImpl = ProcessFlowResources.getInstance(ctx).getPagecomponent(next_page);
				
				if(pagecomponentImpl ==null){
					logger.writeToLog("PagecomponentImpl for "+next_page+" does not exist in ProcessFlow");
					throw new Exception("PagecomponentImpl for "+next_page+" does not exist in ProcessFlow");
				}
			  
				//Added dummy components entry for test harness pages
				if(next_page.equals("testIntegration") || next_page.equals("testQuery") || 
						next_page.equals("logsInformation") || next_page.equals("testHarness")
						|| next_page.equals("sessionList")|| next_page.equals("showErrors")){
					
					ComponentImpl compImpl = new ComponentImpl();
					compImpl.setName(next_page);
					
					componentImpl = compImpl;
				}else
					componentImpl = ComponentResources.getInstance(ctx).getComponent(next_page);
				
				if(componentImpl ==null){
					logger.writeToLog("ComponentImpl for "+next_page+" does not exist in Component");
					throw new Exception("ComponentImpl for "+next_page+" does not exist in Component");
				}
			  
				metadataResources = MetaDataResources.getInstance(ctx);
			                 
				if(elementsAttr != null){
					String blockid = elementsAttr.getValue();
	 			   	if(ctx.containsKey(blockid)){
	 				    if (ctx.get(blockid) instanceof Map){
	 				    	currentData = (Map)ctx.get(blockid);						
	 				    }else if (ctx.get(blockid) instanceof List){
	 				    	currentList = (List)ctx.get(blockid);						
	 				    }
	 			   	}
	 			   	int accessType = new SecurityParser().getAccessTypeBySecurity(ctx, blockelement, next_page);

	 			   	if (SecurityParser.isHideResource(accessType)) {
                        blockelement.detach();
                        i--;
                        continue;
                    }
	 			  
	 			   	if(new ElementProcessor().parseElement(ctx, blockelement, pageName)){
	 			   		i--;
	 			   		continue;
	 			   	}

                    readOnlyResourse = SecurityParser.isReadOnlyResource(accessType, readOnlyResourse);
	 			    // added for handling Help Messages
                    if(new HelpMessageProcessor().parseElement(ctx, blockelement)){
                    	i--;
                    	continue;
                    }
                    //End code
                    
                    
                  //Added code to parse css
                    if(blockelement.getAttributeValue(HtmlConstants.CSS_CLASS) != null && blockelement.getAttributeValue(HtmlConstants.CSS_CLASS).startsWith(HtmlConstants.EVAL)){
                          try{
                                String eval = blockelement.getAttributeValue(HtmlConstants.CSS_CLASS);
                                eval = eval.substring(eval.indexOf(HtmlConstants.EVAL)+5, eval.length());
                                RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(eval);
                                
                                blockelement.setAttribute(HtmlConstants.CSS_CLASS, ruleImpl.execute(ctx, null).toString());
                          }catch (Exception e) {
                                logger.error(ctx, "Unable to generate css due to error : " + e.getMessage());
                          }
                    }

                    
                    if(blockid.contains(HtmlConstants.FREEFORM)){
                    	new FreeFormGenerator(metadataResources, pagecomponentImpl, componentImpl).parseFreeform(ctx, blockelement, subchildren, currentData, currentData,readOnlyResourse,ajax_append_field_value,request, pageName, readOnlyResourse);
                    	continue;
                    }else if(blockid.contains(HtmlConstants.LIST) && !blockid.contains("_listfreeform_")){
                    	ctx.put("parsing_list_name",blockid);
                    	new ListFormGenerator(metadataResources, pagecomponentImpl, componentImpl).parseList(ctx, blockelement, subchildren, currentList,readOnlyResourse,ajax_append_field_value, request, pageName);
                    	continue;
                    }else if(HtmlConstants.LINK.equalsIgnoreCase(type)){
                    	//Added code to add css based on security
        	           	String cssClass = blockelement.getAttributeValue(HtmlConstants.CSS_CLASS);
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
        	               	
        	               	blockelement.setAttribute(HtmlConstants.CSS_CLASS, finalCssClass);
        	           	}
        	           	//Ended code
                    	
                    	//going to parse managereport link href to add dynamic values at run time
                    	if(blockelement.getAttributeValue(HtmlConstants.HREF) != null && blockelement.getAttributeValue(HtmlConstants.HREF).contains("managereport(")){
							String href = blockelement.getAttributeValue(HtmlConstants.HREF);
							
							try{
								if(href.endsWith(");"))
									href = href.substring(href.indexOf("javascript:managereport(")+24, href.lastIndexOf(");"));
								else
									href = href.substring(href.indexOf("javascript:managereport(")+24, href.lastIndexOf(")"));
								
								String hrefs[] = href.split(",");
								String dynaKeys = hrefs[0];
								String dynaValues = hrefs[1];
								String method = hrefs[2];
								String page = hrefs[3];
								
								String newDynaValues = null;
								
								String dynaValuesArray[] = dynaValues.split("\\|");
								for(int m=0; m<dynaValuesArray.length; m++){
									String key = dynaValuesArray[m];
									
									if(ctx.containsKey(key)){
										key = ctx.get(key) != null ? ctx.get(key).toString() : key;
									}
									
									if(newDynaValues == null)
										newDynaValues = "|" + key;
									else
										newDynaValues = "|" + newDynaValues + key;
								}
								
								if(newDynaValues != null)
									newDynaValues = newDynaValues + "|";
								
								String newHref = "javascript:managereport('" + dynaKeys + "','" + newDynaValues + "','" + method + "','" + page + "');";
								
								blockelement.setAttribute(HtmlConstants.HREF, newHref);
							}catch (Exception e) {
								logger.error("Unable to parse managereport link href to add dynamic values at run time due to error : " + e.getMessage());
							}
						}
                    	
                    	new FormLinkGenerator(metadataResources, pagecomponentImpl, componentImpl).parseFormLink(ctx, blockelement,request);
                    	continue;
                    }else if(blockid.contains("DefaultOpenForm:") ||
                    		blockid.contains("DefaultOpenForm_MultiForm:")){
	 				
                    	String form = blockid.substring(blockid.indexOf(":")+1, blockid.length());
	 				 
		 				if(form.contains("eeval:")){ //added code to handle DefaultOpen page on run time.
		 					String eval = form.substring(form.indexOf(":")+1, form.length());	
		 					RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(eval);
		 					if(ruleImpl == null){
		 						continue;
		 					}
		 					Object obj = ruleImpl.execute(ctx, null);
		 			      	if(obj != null){
		 			      		form = obj.toString();
		 			      	}
		 				 }
		 				 if(form.indexOf("/") != -1)
		 					 form = form.substring(form.lastIndexOf("/")+1, form.length()); //getting page component name
	 				
		 				 ComponentImpl component = ComponentResources.getInstance(ctx).getComponent(form); 		        
		 				 component.processView(ctx);
	 				
		 				 Element innerFormElement = null;
		 				 Document formdocument = new PageUtils().parseHtmlDocument(ctx, form, HtmlConstants.EMPTY, request);	
		 				 if(blockid.contains("DefaultOpenForm_MultiForm:"))
		 					innerFormElement = (Element)formdocument.getRootElement().getChild(HtmlConstants.BODY).getChild("div").getChild(HtmlConstants.FORM).detach();		
		 				 else
		 					innerFormElement = (Element)formdocument.getRootElement().getChild(HtmlConstants.BODY).getChild(HtmlConstants.FORM).getChild("div").detach();
		 				 
		 				 blockelement.addContent(innerFormElement);
		 				 continue;
                    }else if(blockid.contains("#Include:") && !blockid.contains("#Include:carrier/popupGraph")){ //Added code for Include tabs html's
                    	String form = blockid.substring(blockid.indexOf(":")+1, blockid.length()-1);
	 				 
                    	//admin/header.html
                    	if(form.indexOf("/") != -1)
                    		form = form.substring(form.lastIndexOf("/")+1, form.length()-5); //getting page component name
                    	
                    	//going to call view of page which is going to be included
                    	try{
                    		
                    		form = form.substring(form.lastIndexOf("/")+1, form.length()-5);
                    		new ProcessAction().processRoute(ctx, form);
                    	}catch (Exception e) {
							// TODO: handle exception
						}
                    	
                    	String isLoadHTMLS = SystemProperties.getInstance().getString("appl."+ctx.getProject()+".LoadHTMLS");                
                    	Map nextPageDoc = null;
                    	if(isLoadHTMLS != null && "Y".equalsIgnoreCase(isLoadHTMLS))            
                    		nextPageDoc = CacheManager.get(form)== null ? new HashMap() : (HashMap)CacheManager.get(form);
                    	else
                    		nextPageDoc = new PageUtils().getHtmlDocMap(ctx, form);
	 				
		 				Document document1 = nextPageDoc.get("XMLDocument") == null ? null:(Document)nextPageDoc.get("XMLDocument");
		 				Document formdocument = (Document)ObjectCloner.deepCopy(document1);
		 				
		 				Element innerFormElement = (Element)formdocument.getRootElement().getChild(HtmlConstants.BODY).getChild(HtmlConstants.FORM).getChild("div").detach();		
		 				blockelement.addContent(innerFormElement);
                    }else if(blockid.contains("#Show_Calendar#")){
                    	// String form = blockid.substring(blockid.indexOf(":")+1, blockid.length()-1);
                    	//admin/header.html
	 				
		 				String calTable = new DynamicCalendar().generateCalendarTable(ctx);
		 				Element calElement = XMLUtils.parseXMLRootElement(calTable);
		 				blockelement.addContent(calElement.detach());
                    }else if(blockid.contains("#IncludeCustomFields")){ //Added code to create custom fields table
                    	generateCustomFieldsBlock(ctx, blockelement, blockid);
                    }else if(blockid.contains("_listfreeform_")){
                    	new ListFormGenerator(metadataResources, pagecomponentImpl, componentImpl).parseListFreeform(ctx, blockelement, subchildren, currentList,readOnlyResourse,ajax_append_field_value, request, pageName, readOnlyResourse);
                    	continue;
                    }else if(blockid.contains("#HISTORY_DATA#")){ //going to generate Audit History data page
                    	generateAuditHistoryDataList(blockelement, ctx);
                    	continue;
                    }else if(blockid.contains("#DYNAMICPAGECONTENT#")){ //going to generate dynamic data page
                    	generateDynamicPageContent(blockelement, ctx);
                    	continue;
                    }else if(blockid.contains("#DYNAMICPAGECONTENTCREATETABLE#")){ //going to generate dynamic data page create table
                    	generateDynamicPageContentCreateTable(blockelement, ctx);
                    	request.getSession().setAttribute("createTableDataListMap", ctx.get("createTableDataListMap"));
                    	continue;
                    }else if(blockid.contains("#DYNAMICPAGETEMPLATEBUILDERCONTENTFULLCREATETABLE#")){ //going to generate dynamic template builder page to show created decision table
                    	generateDynamicTemplateBuilderPageContentFullCreateTable(blockelement, ctx);
                    	continue;
                    }else if(blockid.contains("#HELPICON#")){ //going to generate dynamic Help icon page <script>showPageHelpData('pagename');</script>
                    	blockelement.removeContent();
                    	Element scriptElement = new Element(HtmlConstants.SCRIPT);
                    	scriptElement.addContent("showPageHelpData('"+ctx.get("NEXT_PAGE")+"');");
                    	blockelement.setContent(scriptElement.detach());
                    }else if(blockid.contains("#DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLE#")){ //going to generate dynamic template builder page to show filtered list create table
                    	generateDynamicTemplateBuilderPageContentCreateTable(blockelement, ctx);
                    	continue;
                    }else if(blockid.contains("#Include:carrier/popupGraph_")){//Added code for Include tabs html's
                    	String form = "popupGraph";
                    	
                    	//#Include:carrier/popupGraph_1_allAgencies.html#
                    	String index = blockid.substring(blockid.indexOf("_")+1, blockid.lastIndexOf("_"));
                    	String graphId = blockid.substring(blockid.lastIndexOf("_")+1, blockid.lastIndexOf(".html"));
                    	
                    	//going to call view of page which is going to be included
                    	try{
                    		GraphdefImpl graphDefImpl = GraphResources.getInstance(ctx).getGraphDef(graphId);
                        	if(graphDefImpl != null && StringUtils.isNotBlank(graphDefImpl.getDefaulttype())){
                        		if(graphDefImpl.getDefaulttype().contains("="))
                        			ctx.put("graphtype"+index, graphDefImpl.getDefaulttype().substring(graphDefImpl.getDefaulttype().indexOf("=")+1, graphDefImpl.getDefaulttype().length()));
                        		else	
                        			ctx.put("graphtype"+index, graphDefImpl.getDefaulttype());
                        	}
                        	
                    		new ProcessAction().processRoute(ctx, form);
                    	}catch (Exception e) {
							// TODO: handle exception
						}
                    	
                    	String isLoadHTMLS = SystemProperties.getInstance().getString("appl."+ctx.getProject()+".LoadHTMLS");                
                    	Map nextPageDoc = null;
                    	if(isLoadHTMLS != null && "Y".equalsIgnoreCase(isLoadHTMLS))            
                    		nextPageDoc = CacheManager.get(form)== null ? new HashMap() : (HashMap)CacheManager.get(form);
                    	else
                    		nextPageDoc = new PageUtils().getHtmlDocMap(ctx, form);
	 				
		 				Document document1 = nextPageDoc.get("XMLDocument") == null ? null:(Document)nextPageDoc.get("XMLDocument");
		 				Document formdocument = (Document)ObjectCloner.deepCopy(document1);
		 				
		 				Element innerFormElement = (Element)formdocument.getRootElement().getChild(HtmlConstants.BODY).getChild(HtmlConstants.FORM).getChild("div").detach();		
		 				parsePopupGraph(innerFormElement, ctx, request, form);
		 				
		 				String graphPageContent = new XMLOutputter(Format.getPrettyFormat()).outputString(innerFormElement);
		 				graphPageContent = graphPageContent.replace("graphtype", "graphtype"+index);
		 				graphPageContent = graphPageContent.replace("graph_div", "graph_div"+index);
		 				graphPageContent = graphPageContent.replace("loadGraphFromAJAXIndex()", "loadGraphFromAJAXIndex("+index+")");
		 				
		 				SAXBuilder builder = new SAXBuilder();
		 				Document innerFormElementDoc = builder.build(new StringReader(graphPageContent));
		 				
		 				Element graphlinkElement = new Element(HtmlConstants.INPUT);
		 				graphlinkElement.setAttribute(HtmlConstants.TYPE, HtmlConstants.HIDDEN);
		 				graphlinkElement.setAttribute(HtmlConstants.NAME, "graphlink"+index);
		 				graphlinkElement.setAttribute(HtmlConstants.ID, "graphlink"+index);
		 				
		 				String graphlink = "graph;jsessionid="+ctx.get("jsessionid")+"?rnum="+new Random().nextLong()+"&graphID="+graphId;
		 				graphlinkElement.setAttribute(HtmlConstants.VALUE, graphlink);
		 				
		 				innerFormElementDoc.getRootElement().addContent(graphlinkElement.detach());
		 				
		 				blockelement.addContent(innerFormElementDoc.getRootElement().detach());
                    }
	            }else{            	 
	            	if(HtmlConstants.LINK.equalsIgnoreCase(type)){
	            		//going to parse managereport link href to add dynamic values at run time
                    	if(blockelement.getAttributeValue(HtmlConstants.HREF) != null && blockelement.getAttributeValue(HtmlConstants.HREF).contains("managereport(")){
							String href = blockelement.getAttributeValue(HtmlConstants.HREF);
							
							try{
								if(href.endsWith(");"))
									href = href.substring(href.indexOf("javascript:managereport(")+24, href.lastIndexOf(");"));
								else
									href = href.substring(href.indexOf("javascript:managereport(")+24, href.lastIndexOf(")"));
								
								String hrefs[] = href.split(",");
								String dynaKeys = hrefs[0];
								dynaKeys = dynaKeys.substring(dynaKeys.indexOf("'")+1, dynaKeys.lastIndexOf("'"));
								String dynaValues = hrefs[1];
								dynaValues = dynaValues.substring(dynaValues.indexOf("'")+1, dynaValues.lastIndexOf("'"));
								String method = hrefs[2];
								method = method.substring(method.indexOf("'")+1, method.lastIndexOf("'"));
								String page = hrefs[3];
								page = page.substring(page.indexOf("'")+1, page.lastIndexOf("'"));
								
								String newDynaValues = null;
								
								StringTokenizer tokens = new StringTokenizer(dynaValues, "|");
								while(tokens.hasMoreTokens()){
									String token = tokens.nextToken();
									
									if(ctx.containsKey(token)){
										token = ctx.get(token) != null ? ctx.get(token).toString() : token;
									}
									
									if(newDynaValues == null)
										newDynaValues = token;
									else
										newDynaValues = newDynaValues + "|" + token;
								}
								
								if(newDynaValues != null){
									if(!newDynaValues.startsWith("|"))
										newDynaValues = "|" + newDynaValues;
									if(!newDynaValues.endsWith("|"))
										newDynaValues = newDynaValues + "|";
								}
								
								String newHref = "javascript:managereport('" + dynaKeys + "','" + newDynaValues + "','" + method + "','" + page + "');";
								
								blockelement.setAttribute(HtmlConstants.HREF, newHref);
							}catch (Exception e) {
								logger.error("Unable to parse managereport link href to add dynamic values at run time due to error : " + e.getMessage());
							}
						}
                    	
	            		detachFlag = new FormLinkGenerator(metadataResources, pagecomponentImpl, componentImpl).parseFormLink(ctx, blockelement,request);
	            		if(detachFlag)
	            			i--;
	             	}
	            }
              
				parseHTML(ctx, blockelement, subchildren,rootElement,readOnlyResourse,ajax_append_field_value,request, pageName);
			}
		}
	}		
	
	public String cleanContent(String htmlcontent) 
	{		
		htmlcontent= htmlcontent.replaceAll("&nbsp;",HtmlConstants.SPACE);		
		// To Remove DocType Definition
		if(htmlcontent.contains("<!DOCTYPE"))
		{
			int docIndex = htmlcontent.indexOf("<!DOCTYPE");
			int docCloseIndex = htmlcontent.indexOf(">");
			htmlcontent = htmlcontent.substring(docCloseIndex+1, htmlcontent.length());			
		}		
		
		if(htmlcontent.contains( "&iuml;&raquo;&iquest;"))
		  {
			  htmlcontent = htmlcontent.replaceAll("&iuml;&raquo;&iquest;", "");
		  }
		return htmlcontent;
   }
	
	public void cleanHTMLOutput(Element root )throws Exception
	{		
		Element body=root.getChild("body");		
		Element form=body.getChild("form");
		List children = form.getChildren();	
		cleanHTMLOutput(form,  children );
		
	}
	
	public void cleanHTMLOutput(Element element, List children )throws Exception
	{
		Element sibling = null;
		
		if (children != null)
		{  
			for (int i = 0; i < children.size(); i++)
			{
				sibling = (Element)children.get(i);
				if(sibling.getText()!=null && sibling.getText().equals(HtmlConstants.SPACE))
					sibling.setText("&nbsp;");
				
				List childernlist = sibling.getChildren();			
				cleanHTMLOutput(sibling, childernlist);
			}
		}
	}
	
	protected void rectifyScriptTag(Element root)throws Exception
	{		
		List scriptList =root.getChildren();		
		String function = "function test(){}";
		if(scriptList!=null)
			for(int i=0; i<scriptList.size(); i++)
			{
				Element script = (Element)scriptList.get(i);
				if("script".equalsIgnoreCase(script.getName()))
				{	
					if(script.getText()==null || "".equals(script.getText().trim()))					
						script.addContent(function);
					continue;
				}
				rectifyScriptTag(script);
			}	
		
	}
	
	public void appendHiddenFields(Element form, Context ctx)
	{
		ParseUtil.addAttribute(form, HtmlConstants.ACTION, next_page+".do;jsessionid="+ctx.get("jsessionid"));
		ParseUtil.addAttribute(form, HtmlConstants.METHOD, "post");
		
		form.addContent(ParseUtil.createInputElement(HtmlConstants.INET_METHOD, HtmlConstants.HIDDEN, "", HtmlConstants.INET_METHOD));
		form.addContent(ParseUtil.createInputElement(Constants.INET_PAGE, HtmlConstants.HIDDEN, "", Constants.INET_PAGE));
		form.addContent(ParseUtil.createInputElement(Constants.DYNAKEYS, HtmlConstants.HIDDEN, "", Constants.DYNAKEYS));
		form.addContent(ParseUtil.createInputElement(Constants.DYNAVALUES, HtmlConstants.HIDDEN, "", Constants.DYNAVALUES));
		form.addContent(ParseUtil.createInputElement(Constants.INET_CONTEXT, HtmlConstants.HIDDEN, "", Constants.INET_CONTEXT));
		form.addContent(ParseUtil.createInputElement(HtmlConstants.CTXMENUPARAM, HtmlConstants.HIDDEN, "", ""));
		form.addContent(ParseUtil.createInputElement(HtmlConstants.DATE_FORMAT, HtmlConstants.HIDDEN, (ctx.get(HtmlConstants.DATE_FORMAT) != null ? ctx.get(HtmlConstants.DATE_FORMAT).toString() : HtmlConstants.EMPTY), "ajax_field_"+HtmlConstants.DATE_FORMAT));
		form.addContent(ParseUtil.createInputElement(HtmlConstants.DATE_FORMAT_TS, HtmlConstants.HIDDEN, (ctx.get(HtmlConstants.DATE_FORMAT_TS) != null ? ctx.get(HtmlConstants.DATE_FORMAT_TS).toString() : HtmlConstants.EMPTY), "ajax_field_"+HtmlConstants.DATE_FORMAT_TS));
		form.addContent(ParseUtil.createInputElement(HtmlConstants.PAGE_DISABLED_FIELDS, HtmlConstants.HIDDEN, (ctx.get(HtmlConstants.PAGE_DISABLED_FIELDS) != null ? ctx.get(HtmlConstants.PAGE_DISABLED_FIELDS).toString() : HtmlConstants.EMPTY), "ajax_field_"+HtmlConstants.PAGE_DISABLED_FIELDS));

		if(ctx.get("REQUEST_AUTH_TOKEN") != null)
			form.addContent(ParseUtil.createInputElement(HtmlConstants.REQUEST_AUTH_TOKEN, HtmlConstants.HIDDEN, ctx.get("REQUEST_AUTH_TOKEN").toString(), "REQUEST_AUTH_TOKEN"));
		
		//going to add hidden fields for SSO token handling
		try{
			String isSSOEnabled = SystemProperties.getInstance().getString("appl."+ctx.getProject()+".issso.enabled");
			if(isSSOEnabled != null && isSSOEnabled.equals("Y")){
				String ssoTokenKey = "SAMLart";
				try{
					ssoTokenKey = SystemProperties.getInstance().getString("sso.token.key");
				}catch (Exception e) {
					logger.error("Unable to get sso.token.key property due to error : " + e.getMessage());
				}
				
				form.addContent(ParseUtil.createInputElement(HtmlConstants.SSOTOKENKEY, HtmlConstants.HIDDEN, ssoTokenKey, "ajax_field_ssoTokenKey"));
				form.addContent(ParseUtil.createInputElement(ssoTokenKey, HtmlConstants.HIDDEN, (ctx.get(ssoTokenKey) != null ? ctx.get(ssoTokenKey).toString() : HtmlConstants.EMPTY), "ajax_field_"+ssoTokenKey));
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		if(ctx.get("NEXT_PAGE_COMPONENT") != null)
			form.addContent(ParseUtil.createInputElement("from_page", HtmlConstants.HIDDEN, ctx.get("NEXT_PAGE_COMPONENT").toString(), "from_page"));
	}
	
	protected void rectifyErrorList(Context ctx, Element body)throws Exception{
		List children =body.getChildren();		
		if(children!=null)
			for(int i=0; i<children.size(); i++){
				Element element = (Element)children.get(i);
				if(element.getAttributeValue("id")!=null && element.getAttributeValue("id").equalsIgnoreCase(HtmlConstants.ERRORS_LIST)){
					createErrorList(ctx, element);
					break;
				}				
				
				rectifyErrorList(ctx, element);
			}	
	}
	
	protected Boolean isExistErrorList(Context ctx, List children)
	{		
		Element childElement = null;
		for(int i=0; i<children.size(); i++)
		{
			childElement = (Element)children.get(i);
			if(childElement.getAttributeValue("id")!=null && childElement.getAttributeValue("id").equalsIgnoreCase(HtmlConstants.ERRORS_LIST))
			{
				return true;				
			}		
			
			List subchildren =childElement.getChildren();
			if(subchildren == null || subchildren.size() == 0)
				continue;
			
			if(isExistErrorList(ctx, subchildren))
				return true;
		}
		
		return false;
	}
	
	protected Element getErrorDiv(Context ctx, List children, String divId)
	{			
		Element childElement = null;
		for(int i=0; i<children.size(); i++)
		{
			childElement = (Element)children.get(i);
			if(childElement.getAttributeValue("id")!=null && childElement.getAttributeValue("id").equalsIgnoreCase(divId))
			{
				return childElement;					
			}				
			
			List subchildren =childElement.getChildren();			
			if(subchildren==null || subchildren.size()==0)
				continue;					
			
			childElement = getErrorDiv(ctx, subchildren, divId);
			if(childElement != null)
				return childElement;			
		}
		
		return null;
	}
	
	
	
	
	public void populateErrorDiv(Context ctx, Element element) 
	{	
		if(ctx.get("actionsToSkipErrorDiv")!=null && !ctx.get("actionsToSkipErrorDiv").toString().equals(HtmlConstants.EMPTY))
		{
			StringTokenizer tokens=new StringTokenizer(ctx.get("actionsToSkipErrorDiv").toString(),",");
			while(tokens.hasMoreTokens())
			{
				String token=tokens.nextToken();
				if(token.equals(ctx.get(HtmlConstants.INET_METHOD)))
				return;
				
			}
			
		}
		List errorlist = null;
		
		if(ctx.get(Constants.INET_ERRORS_LIST)!=null)
			errorlist = (List)ctx.get(Constants.INET_ERRORS_LIST);
		
		if(errorlist==null)
			  return;
		
		Element trIndicateErrorElement = getErrorDiv(ctx, element.getChildren(), "tr_error_indication_div");
		if(trIndicateErrorElement != null)
			ParseUtil.addAttribute(trIndicateErrorElement, HtmlConstants.CSS_CLASS, "tr_errors");			
		
		Element divIndicateErrorElement = getErrorDiv(ctx, element.getChildren(), "error_indication_div");		
		if(divIndicateErrorElement != null)
		{
			divIndicateErrorElement.setText(ERROR_INDICATION_MSG);			
			ParseUtil.addAttribute(divIndicateErrorElement, HtmlConstants.CSS_CLASS, "tr_errors");			
		}
		
		for(int i=0;i<errorlist.size();i++)
		{
			Object  validationObj = errorlist.get(i);
			ValidationException  exception = (ValidationException) validationObj;
			String msg = exception.getMessage();
			String field = exception.getField();
			Element trErrorElement = getErrorDiv(ctx, element.getChildren(), "tr_error_" + field);
			ParseUtil.addAttribute(trErrorElement, HtmlConstants.CSS_CLASS, "tr_errors");
			
			Element divErrorElement = getErrorDiv(ctx, element.getChildren(), "error_" + field);
			if(divErrorElement != null)
			{
				divErrorElement.setText(exception.getMessage());
				ParseUtil.addAttribute(divErrorElement, HtmlConstants.CSS_CLASS, "tr_errors");
				
			}
			
		}	
	}	
	
	public void createErrorList(Context ctx, Element element) 
	{		
		List errorlist = null;
		
		if(ctx.get(Constants.INET_ERRORS_LIST)!=null)
			errorlist = (List)ctx.get(Constants.INET_ERRORS_LIST);
		
		if(errorlist==null)
			  return;
		
		Element table = new Element(HtmlConstants.TABLE);
		ParseUtil.addAttribute(table, HtmlConstants.CSS_CLASS, HtmlConstants.ERRORS_LIST);
		for(int i=0;i<errorlist.size();i++)
		{
			Object  validationObj = errorlist.get(i);
			ValidationException  exception = (ValidationException) validationObj;
			Element tr = new Element(HtmlConstants.TR);
			Element td = new Element(HtmlConstants.TD);
			ParseUtil.addAttribute(td, HtmlConstants.CSS_CLASS, HtmlConstants.ERRORS_LIST);
			td.setText(exception.getMessage());
			tr.addContent(td);
			table.addContent(tr);
		}
		   
		element.addContent(table);
	}	

	//Newly created to generate custom field block
	public void generateCustomFieldsBlock(Context ctx, Element parentElement, String blockid) throws Exception {
		logger.debug("Going to process extended fields");
		
		if(ctx.get("extendedFields") == null || !ctx.get("extendedFields").toString().equals("Y")){
			logger.debug("Extended fields process done successfully without generating any field");
			return;
		}
		
		ctx.put("component_name", ctx.get("NEXT_PAGE"));
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
            	Element fieldSetElement = new Element("fieldset");
                fieldSetElement.setAttribute("id","extendedfields");
                boolean isReadOnlyAccess = false;
                    
                int accessType = new SecurityParser().getAccessTypeBySecurity(ctx, fieldSetElement, next_page);
                if(SecurityParser.isHideResource(accessType)){
                	return;
                }
                    
                if(SecurityParser.isReadOnlyResource(accessType, false))
                	isReadOnlyAccess = true;
                    
                fieldSetElement.addContent(new Element("legend").setText("Extended Fields"));
                                            
                Element tableElement = new Element(HtmlConstants.TABLE);
                tableElement.setAttribute("cellspacing", "0");
                tableElement.setAttribute("cellpadding", "0");
                    
                Element trElement = new Element(HtmlConstants.TR);
                Element tdElement = new Element(HtmlConstants.TD);
                Element textElement = new Element(HtmlConstants.INPUT);
                textElement.setAttribute(HtmlConstants.TYPE, HtmlConstants.HIDDEN);
                textElement.setAttribute(HtmlConstants.NAME, "customFieldsData");
                textElement.setAttribute(HtmlConstants.ID, "ajax_field_customFieldsData");
                textElement.setAttribute(HtmlConstants.VALUE, blockid + ":" + compMap.get("component_id") + ":" + compMap.get("object_id"));
                    
                tdElement.addContent(textElement);
                trElement.addContent(tdElement);
                tableElement.addContent(trElement);
                
                parentElement.addContent(tableElement.detach());
                
                tableElement = new Element(HtmlConstants.TABLE);
                tableElement.setAttribute(HtmlConstants.CSS_CLASS, "tdPaddingLeft");
                
                String xml = null;
                
                int fldCount = 0;
                    
                for(int i=0; i<fieldsList.size(); i++){
                	Map row = (Map)fieldsList.get(i);
                          
                    if(fldCount%2==0)
                    	trElement = new Element(HtmlConstants.TR);
                          
                    String elementString  = row.get("field").toString();
                    Element customElement = new Element(elementString);
                    customElement.setAttribute("name", elementString);
                    accessType = new SecurityParser().getAccessTypeBySecurity(ctx, customElement, next_page);
                      
                    if(SecurityParser.isHideResource(accessType)){
                    	continue;
                    }
                      
                    boolean isReadOnlyAccessChildField = false;
                    if(SecurityParser.isReadOnlyResource(accessType, isReadOnlyAccess))
                    	isReadOnlyAccessChildField = true;
                      
                    tdElement = new Element(HtmlConstants.TD);
                    //tdElement.setAttribute(HtmlConstants.WIDTH, "107px");
                    tdElement.setAttribute(HtmlConstants.CSS_CLASS, "labelAlign");
                    tdElement.setAttribute("nowrap", "nowrap");
                    tdElement.setText(row.get("displayname").toString() + " : ");
                    trElement.addContent(tdElement.detach());
                      
                    tdElement = new Element(HtmlConstants.TD);
                    textElement = new Element(HtmlConstants.INPUT);
                    textElement.setAttribute(HtmlConstants.TYPE, HtmlConstants.TEXT);
                    textElement.setAttribute(HtmlConstants.NAME, row.get("field").toString());
                    textElement.setAttribute(HtmlConstants.ID, "ajax_field_" + row.get("field").toString());
                    textElement.setAttribute(HtmlConstants.CSS_CLASS, "inputDetail");
                      
                    if(isReadOnlyAccessChildField)
                    	textElement.setAttribute(HtmlConstants.READONLY, HtmlConstants.READONLY);
                      
                    //textElement.setAttribute(HtmlConstants.STYLE, "width: 240px");
                    if(fldCount == 0){
                    	xml = row.get(HtmlConstants.VALUE) != null ? row.get(HtmlConstants.VALUE).toString() : null;
                    }
                      
                    String value = getCustomFieldValue(xml, row.get("field").toString());
                    if(ctx.get(Constants.INET_ERRORS_LIST) != null)
                    	value = ctx.get(row.get("field").toString()) != null ? ctx.get(row.get("field").toString()).toString() : null;
                    
                    textElement.setAttribute(HtmlConstants.VALUE, value);
                    tdElement.addContent(textElement);
                    trElement.addContent(tdElement.detach());
                      
                    if(fldCount%2==0){
                    	tdElement = new Element(HtmlConstants.TD);
                        tdElement.setAttribute(HtmlConstants.CSS_CLASS, "spacerTd40 agentSpacerTd40");
                        trElement.addContent(tdElement.detach());
                            
                        tdElement = new Element(HtmlConstants.TD);
                        tdElement.setAttribute(HtmlConstants.CSS_CLASS, "mendatoryAsterisk");
                        trElement.addContent(tdElement.detach());
                    }
                      
                    tableElement.addContent(trElement.detach());
                      
                    /*//going to call attached rule to field
                    if((row.get("ruleid") != null && !row.get("ruleid").toString().equals(HtmlConstants.EMPTY)) &&
                    	(row.get("ruleexpression") != null && !row.get("ruleexpression").toString().equals(HtmlConstants.EMPTY))){
                    	try{
                    		RuleImpl ruleImpl = new RuleImpl();
                        	ruleImpl.setExpression(row.get("ruleexpression").toString());
                        	ruleImpl.setId(row.get("ruleid").toString());
                        	  
                        	ruleImpl.execute(ctx, null);
                    	}catch (Exception e) {
							logger.error("Unable to execute attached rule " + row.get("ruleid").toString() + " due to error : " + e.getMessage());
						}
                    }*/
                      
                    fldCount++;
                }
                    
                fieldSetElement.addContent(tableElement.detach());
                parentElement.addContent(fieldSetElement);
                //parentElement.addContent(tableElement);
            }
        }
        
        /*
        String metaobject_name = blockid.substring(blockid.indexOf(":")+1, blockid.lastIndexOf(":"));
        String object_id = blockid.substring(blockid.lastIndexOf(":")+1, blockid.length());
        
        ctx.put("project_name", ctx.getProject());
        ctx.put("metaobject_name", metaobject_name);
        ctx.put("object_id", ctx.get(object_id));
        
        List customFieldsList = SqlResources.getSqlMapProcessor(ctx).select("person.getCustomFieldsList_p_java", ctx);
        if(customFieldsList != null && customFieldsList.size() > 0){
              Element fieldSetElement = new Element("fieldset");
              fieldSetElement.addContent(new Element("legend").setText("Custom Fields"));
              
              Element tableElement = new Element(HtmlConstants.TABLE);
              Element trElement = new Element(HtmlConstants.TR);
              Element tdElement = new Element(HtmlConstants.TD);
              Element textElement = new Element(HtmlConstants.INPUT);
              textElement.setAttribute(HtmlConstants.TYPE, HtmlConstants.HIDDEN);
              textElement.setAttribute(HtmlConstants.NAME, "customFieldsData");
              textElement.setAttribute(HtmlConstants.ID, "ajax_field_customFieldsData");
              textElement.setAttribute(HtmlConstants.VALUE, blockid);
              
              tdElement.addContent(textElement);
              trElement.addContent(tdElement);
              tableElement.addContent(trElement);
              
              parentElement.addContent(tableElement.detach());
              
              tableElement = new Element(HtmlConstants.TABLE);
              
              String xml = null;
              for(int l=0; l<customFieldsList.size(); l++){
                    Map row = (Map)customFieldsList.get(l);
                    
                    trElement = new Element(HtmlConstants.TR);
                    
                    tdElement = new Element(HtmlConstants.TD);
                    tdElement.setText(row.get("displayname").toString() + " : ");
                    trElement.addContent(tdElement.detach());
                    
                    tdElement = new Element(HtmlConstants.TD);
                    textElement = new Element(HtmlConstants.INPUT);
                    textElement.setAttribute(HtmlConstants.TYPE, HtmlConstants.TEXT);
                    textElement.setAttribute(HtmlConstants.NAME, row.get("field").toString());
                    textElement.setAttribute(HtmlConstants.ID, "ajax_field_" + row.get("field").toString());
                    textElement.setAttribute(HtmlConstants.STYLE, "width: 240px");
                    
                    if(l == 0){
                          xml = row.get(HtmlConstants.VALUE) != null ? row.get(HtmlConstants.VALUE).toString() : null;
                    }
                    
                    textElement.setAttribute(HtmlConstants.VALUE, getCustomFieldValue(xml, row.get("field").toString()));
                    
                    tdElement.addContent(textElement);
                    
                    trElement.addContent(tdElement.detach());
                    
                    tableElement.addContent(trElement);
              }
              
              fieldSetElement.addContent(tableElement);
              parentElement.addContent(fieldSetElement);
        }
        */
        
        logger.debug("Extended fields process done successfully");
	}
	
	private String getCustomFieldValue(String xml, String field) throws Exception {
		String obj = HtmlConstants.EMPTY;
		if(xml == null)
			return obj;
		
		try{
			SAXBuilder sax = new SAXBuilder();
			Document doc = sax.build(new StringReader(xml));
			Element root = doc.getRootElement();
			if(root.getContent() != null && root.getContent().size() > 0){
				obj = root.getChild(field) != null ? root.getChild(field).getText() : HtmlConstants.EMPTY;
			}
		}catch (Exception e) {
			logger.debug(e.getMessage());
		}
		
		return obj;
	}
	
	private void generateAuditHistoryDataList(Element blockelement, Context ctx) throws Exception {
		String dynamicHeadersArray[] = null;
		
		if(ctx.get("dynamicHeaders") != null && !ctx.get("dynamicHeaders").toString().equals(HtmlConstants.EMPTY)){
			String dynamicHeaders = ctx.get("dynamicHeaders").toString();
			
			dynamicHeadersArray = dynamicHeaders.split("\\|");
			
			for(int i=0; i<dynamicHeadersArray.length; i++){
				Element tableElement = new Element(HtmlConstants.TABLE);
				tableElement.setAttribute("cellpadding", "0");
				tableElement.setAttribute("cellspacing", "0");
				tableElement.setAttribute(HtmlConstants.WIDTH, "100%");
				tableElement.setAttribute(HtmlConstants.CSS_CLASS, "gridTable");
				tableElement.setAttribute("padding-top", "10px");
				
				String dynamicHeadersToken = dynamicHeadersArray[i];
				
				Element trElement = new Element(HtmlConstants.TR);
				
				StringTokenizer dynamicHeadersTokens = new StringTokenizer(dynamicHeadersToken, ",");
				while(dynamicHeadersTokens.hasMoreTokens()){
					String token = dynamicHeadersTokens.nextToken().trim();
					
					try{
						FieldImpl fieldImpl = MetaDataResources.getInstance(ctx).getField(token);
						String displayName = fieldImpl.getDisplayname();
						
						StringTokenizer displayNameTokens = new StringTokenizer(displayName, " ");
						displayName = null;
						while(displayNameTokens.hasMoreTokens()){
							String displayNameToken = displayNameTokens.nextToken().trim();
							
							Map<String,String> map = CacheManager.get("Labels_Conf") == null ? null : (HashMap)CacheManager.get("Labels_Conf");
							if(map != null && map.containsKey(displayNameToken) && map.get(displayNameToken) != null){
								displayNameToken = map.get(displayNameToken).toString();
							}
							
							displayName = (displayName == null) ? (displayNameToken) : (displayName + " " + displayNameToken);
						}
						
						token = displayName;
					}catch (Exception e) {
						// TODO: handle exception
					}
					
					Element tdElement = new Element(HtmlConstants.TD);
					
					tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
					tdElement.setText(token);
					trElement.addContent(tdElement.detach());
				}
				
				tableElement.addContent(trElement.detach());
				
				if(ctx.get("auditHistory_list_1_0_"+i) != null && ctx.get("auditHistory_list_1_0_"+i) instanceof List){
					List dataList = (List)ctx.get("auditHistory_list_1_0_"+i);
					
					if(dataList != null && dataList.size() > 0){
						for(int j=0; j<dataList.size(); j++){
							Map dataMap = (Map)dataList.get(j);
							trElement = new Element(HtmlConstants.TR);
							
							StringTokenizer tokens = new StringTokenizer(dynamicHeadersToken, ",");
							while(tokens.hasMoreTokens()){
								String token = tokens.nextToken().trim();
								
								if(!StringUtils.isBlank(token)){
									String key = token;
									
									//apply security/label conf here
									
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
									
									Element tdElement = new Element(HtmlConstants.TD);
									tdElement.setText(dataMap.get(key) != null ? dataMap.get(key).toString() : HtmlConstants.EMPTY);
									
									if(j%2 == 0)
										tdElement.setAttribute(HtmlConstants.CSS_CLASS, "listRow2CSS");
									else
										tdElement.setAttribute(HtmlConstants.CSS_CLASS, "listRow1CSS");
									
									trElement.addContent(tdElement.detach());
									tableElement.addContent(trElement.detach());
								}
							}
						}
					}
				}else{
					trElement = new Element(HtmlConstants.TR);
					Element tdElement = new Element(HtmlConstants.TD);
					tdElement.setAttribute(HtmlConstants.ALIGN, "center");
					tdElement.setAttribute("colspan", "50");
					tdElement.setText("No Record Found");
					tdElement.setAttribute("class", "EmptyRowStyle");
					
					trElement.addContent(tdElement.detach());
					tableElement.addContent(trElement.detach());
				}
				
				blockelement.addContent(tableElement.detach());
			}
		}
	}
	
	//Method created to generate dynamic page based on passing xml
	/*private void generateDynamicPageContent(Element blockElement, Context ctx) throws Exception{
		String dynamicPageXML = ctx.get("DYNAMICPAGEXML") != null ? ctx.get("DYNAMICPAGEXML").toString() : null;
		if(dynamicPageXML == null)
			return;
		
		try{
			LinkedHashMap headersMap = new LinkedHashMap();
			LinkedHashMap createTableHeadersMap = new LinkedHashMap();
			
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(new StringReader(dynamicPageXML));
			
			Element root = document.getRootElement(); //getting <root> node
			
			if(root.getContent() != null && root.getContent().size() > 0){
				
				Element tableElement = new Element(HtmlConstants.TABLE);
				tableElement.setAttribute(HtmlConstants.WIDTH, "100%");
				
				for(int i=0; i<root.getContentSize(); i++){
					Object fieldsObj = root.getContent().get(i);
					
					if(fieldsObj != null && fieldsObj instanceof Element){
						Element fieldsElement = (Element)fieldsObj; //getting <fields> node that can be multiple
						
						if(fieldsElement.getContent() != null && fieldsElement.getContentSize() > 0){
							for(int j=0; j<fieldsElement.getContentSize(); j++){
								Object obj1 = fieldsElement.getContent().get(j);
								
								if(obj1 != null && obj1 instanceof Element){
									Element fieldElement = (Element)obj1; //getting <field name="" type="" source=""> node
									
									Element trElement = new Element(HtmlConstants.TR);
									Element tdElement = new Element(HtmlConstants.TD);
									
									String name = fieldElement.getAttributeValue(HtmlConstants.NAME);
									String type = fieldElement.getAttributeValue(HtmlConstants.TYPE);
									String source = fieldElement.getAttributeValue("source");
									String operator = fieldElement.getAttributeValue("operator");
									String valuefield = fieldElement.getAttributeValue("valuefield");
									String displayfield = fieldElement.getAttributeValue("displayfield");
									String id = "ajax_field_"+name;
									
									FieldImpl fldImpl = MetaDataResources.getInstance(ctx).getField(name);
									String fieldLabel = fldImpl.getDisplayname();
									
									if(type != null){
										if(type.equals(HtmlConstants.TEXT)){
											//adding to headersMap to show in list headers 
											headersMap.put(fieldLabel, id+"_text");
											
											Element tableEle = new Element(HtmlConstants.TABLE);
											
											Element trEle = new Element(HtmlConstants.TR);
											
											//creating label
											Element tdEle = new Element(HtmlConstants.TD);
											tdEle.addContent(fieldLabel + " : ");
											trEle.addContent(tdEle.detach());
											
											//creating textbox
											tdEle = new Element(HtmlConstants.TD);
											
											Element textBoxElement = new ControlGenerator().createText(null, name, "ajax_field_"+name, "", ctx, null);
											
											tdEle.addContent(textBoxElement.detach());
											
											trEle.addContent(tdEle.detach());
											
											tableEle.addContent(trEle);
											
											tdElement.addContent(tableEle);
										}else if(type.equals(HtmlConstants.CHECKBOX)){
											//adding to headersMap to show in list headers 
											headersMap.put(fieldLabel, id+"_checkbox");
											
											Element tableEle = new Element(HtmlConstants.TABLE);
											
											Element trEle = new Element(HtmlConstants.TR);
											
											//creating label
											Element tdEle = new Element(HtmlConstants.TD);
											tdEle.addContent(fieldLabel + " : ");
											trEle.addContent(tdEle.detach());
											
											//creating textbox
											tdEle = new Element(HtmlConstants.TD);
											
											Element checkBoxElement = new ControlGenerator().createCheckbox(null, type, name, "ajax_field_"+name, "", false, ctx, null);
											
											tdEle.addContent(checkBoxElement.detach());
											
											trEle.addContent(tdEle.detach());
											
											tableEle.addContent(trEle);
											
											tdElement.addContent(tableEle);
										}else if(type.equals(HtmlConstants.RADIO)){
											//adding to headersMap to show in list headers 
											headersMap.put(fieldLabel, id+"_radio");
											
											Element tableEle = new Element(HtmlConstants.TABLE);
											
											Element trEle = new Element(HtmlConstants.TR);
											
											//creating label
											Element tdEle = new Element(HtmlConstants.TD);
											tdEle.addContent(fieldLabel + " : ");
											trEle.addContent(tdEle.detach());
											
											//creating textbox
											tdEle = new Element(HtmlConstants.TD);
											
											Element radioElement = new ControlGenerator().createRadio(null, type, name, "ajax_field_"+name, "", false, ctx, null);
											
											tdEle.addContent(radioElement.detach());
											
											trEle.addContent(tdEle.detach());
											
											tableEle.addContent(trEle);
											
											tdElement.addContent(tableEle);
										}else if(type.equals(HtmlConstants.SELECT)){
											//adding to headersMap to show in list headers 
											headersMap.put(fieldLabel, id+"_select="+source+"="+displayfield+"="+valuefield);
											
											Element tableEle = new Element(HtmlConstants.TABLE);
																						
											Element trEle = new Element(HtmlConstants.TR);
											
											//creating label
											Element tdEle = new Element(HtmlConstants.TD);
											tdEle.addContent(fieldLabel + " : ");
											
											trEle.addContent(tdEle.detach());
											
											//creating select
											tdEle = new Element(HtmlConstants.TD);
											
											Element selectElement = new ControlGenerator().createSelect(null, name, id, null, false, -1, ctx, null);
											selectElement.setAttribute(HtmlConstants.STYLE, "width:235px");
											
											Element optionElement = new ControlGenerator().createOption(null, HtmlConstants.EMPTY, "Select", null);
											selectElement.addContent(optionElement);
											
											List dataList = null;
											try{
												ctx.put("table_name", source);
												ctx.put("column_name", displayfield+","+valuefield);
												Object dataMapObj = SqlResources.getSqlMapProcessor(ctx).findByKey("person.getDynamicPageDDList_p_java", ctx);
												if(dataMapObj != null && dataMapObj instanceof Map){
													Map dataMap = (Map)dataMapObj;
													
													//going to get dynamic page data list
													dataList = getDynamicPageDataList(ctx, dataMap, displayfield, valuefield);
												}
											}catch (Exception e) {
												logger.error("Unable to get data for dynamic page due to error : " + e.getMessage());
											}
											
											if(dataList == null)
												continue;
											
											//creating options
											if(dataList != null && dataList.size() > 0){
												for(int k=0; k<dataList.size(); k++){
													Map dataMap = (Map)dataList.get(k);
													String label = null;
													String value = null;
													
													Set<Map.Entry<String, String>> entrySet = dataMap.entrySet();
													Iterator<Map.Entry<String, String>> itr = entrySet.iterator();
													while(itr.hasNext()){
														Map.Entry<String, String> entry = itr.next();
														
														label = entry.getKey();
														value = entry.getValue();
													}
													
													optionElement = new ControlGenerator().createOption(null, value, label, null);
													
													selectElement.addContent(optionElement);
												}
											}
											
											tdEle.addContent(selectElement.detach());
											
											trEle.addContent(tdEle.detach());
											
											tableEle.addContent(trEle);
											
											tdElement.addContent(tableEle);
										}else if(type.equals(HtmlConstants.LIST_MOM)){
											//adding to headersMap to show in list headers 
											headersMap.put(fieldLabel, id);
											
											createTableHeadersMap.put(fieldLabel, id);
											
											Element fieldSetElement = new Element(HtmlConstants.FIELDSET);
											fieldSetElement.setAttribute(HtmlConstants.WIDTH, "100%");
											
											Element legendElement = new Element(HtmlConstants.LEGEND);
											legendElement.addContent(fieldLabel);
											
											fieldSetElement.addContent(legendElement.detach());
											
											Element tableEle = new Element(HtmlConstants.TABLE);
											tableEle.setAttribute(HtmlConstants.WIDTH, "100%");
											
											List dataList = null;
											try{
												ctx.put("table_name", source);
												ctx.put("column_name", displayfield+","+valuefield);
												Object dataMapObj = SqlResources.getSqlMapProcessor(ctx).findByKey("person.getDynamicPageDDList_p_java", ctx);
												if(dataMapObj != null && dataMapObj instanceof Map){
													Map dataMap = (Map)dataMapObj;
													
													//going to get dynamic page data list
													dataList = getDynamicPageDataList(ctx, dataMap, displayfield, valuefield);
												}
											}catch (Exception e) {
												logger.error("Unable to get data for dynamic page due to error : " + e.getMessage());
												continue;
											}
											
											if(dataList == null)
												continue;
											
											Element trEle = new Element(HtmlConstants.TR);
											
											for(int k=0; k<dataList.size(); k++){
												if(k%3==0){
													tableEle.addContent(trEle.detach());
													trEle = new Element(HtmlConstants.TR);
												}
												
												if(k%2==0)
													trEle.setAttribute(HtmlConstants.CSS_CLASS, "listRow2CSS");
												else
													trEle.setAttribute(HtmlConstants.CSS_CLASS, "listRow1CSS");
												
												Map dataMap = (Map)dataList.get(k);
												String label = null;
												String value = null;
												
												Set<Map.Entry<String, String>> entrySet = dataMap.entrySet();
												Iterator<Map.Entry<String, String>> itr = entrySet.iterator();
												while(itr.hasNext()){
													Map.Entry<String, String> entry = itr.next();
													
													label = entry.getKey();
													value = entry.getValue();
												}
												
												Element tdEle = new Element(HtmlConstants.TD);
												
												Element innerTableEle = new Element(HtmlConstants.TABLE);
												innerTableEle.setAttribute(HtmlConstants.CELLSPACING, "0");
												innerTableEle.setAttribute(HtmlConstants.CELLPADDING, "0");
												
												Element innerTrEle = new Element(HtmlConstants.TR);
												Element innerTdEle = new Element(HtmlConstants.TD);
												
												Element checkBoxElement = new ControlGenerator().createCheckbox(null, type, name+"_"+k, id+"_"+k, value, false, ctx, null);
												innerTdEle.addContent(checkBoxElement.detach());
												
												innerTrEle.addContent(innerTdEle.detach());
												
												innerTdEle = new Element(HtmlConstants.TD);
												innerTdEle.addContent(label);
												
												innerTrEle.addContent(innerTdEle.detach());
												
												innerTdEle = new Element(HtmlConstants.TD);
												Element hiddenElement = new ControlGenerator().createHiddenText(null, name+"_description_"+k, id+"_description_"+k, label);
												innerTdEle.addContent(hiddenElement);
												innerTrEle.addContent(innerTdEle.detach());
												
												innerTableEle.addContent(innerTrEle.detach());
												
												tdEle.addContent(innerTableEle.detach());
												trEle.addContent(tdEle.detach());
											}
											
											if(dataList.size() < 3)
												tableEle.addContent(trEle.detach());
											
											fieldSetElement.addContent(tableEle.detach());
											
											tdElement.addContent(fieldSetElement);
										}
									}
									
									trElement.addContent(tdElement.detach());
									tableElement.addContent(trElement.detach());
								}
							}
						}
					}
				}
				
				blockElement.addContent(tableElement.detach());
				ctx.put("DYNAMICPAGECONTENTCREATETABLE_headersMap", headersMap);
				ctx.put("DYNAMICPAGECONTENTCREATETABLE_createTableHeadersMap", createTableHeadersMap);
			}
		}catch (Exception e) {
			logger.error("Unable to generate dynamic page content due to error : " + e.getMessage());
		}
	}*/
	
	private void generateDynamicPageContent(Element blockElement, Context ctx) throws Exception{
		String dynamicPageXML = ctx.get("DYNAMICPAGEXML") != null ? ctx.get("DYNAMICPAGEXML").toString() : null;
		if(dynamicPageXML == null)
			return;
		
		try{
			//going to get filters data from request on click of Create Table button
			if(ctx.get("isDynamicPageContentCreateTable") != null && ctx.get("isDynamicPageContentCreateTable").toString().equals("Y")){
				getDynamicTemplateBuilderFiltersData(ctx, dynamicPageXML);
			}
			
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new StringReader(dynamicPageXML));
			Element root = doc.getRootElement();
			
			if(root != null && root.getContent() != null && root.getContent().size() > 0){
				//Element tableElement = new Element(HtmlConstants.TABLE);
				//tableElement.setAttribute(HtmlConstants.WIDTH, "100%");
				
				for(int i=0; i<root.getContent().size(); i++){
					Object obj = root.getContent().get(i);
					
					if(obj != null && obj instanceof Element){
						if(((Element)obj).getName().equals("filters")){//getting <filters> node to print filters section
							Element filtersElement = (Element)obj;
							
							if(filtersElement.getContent() != null && filtersElement.getContent().size() > 0){
								for(int j=0; j<filtersElement.getContent().size(); j++){
									Object obj1 = filtersElement.getContent().get(j);
									
									if(obj1 != null && obj1 instanceof Element){
										Element filterElement = (Element)obj1;
										
										String name = filterElement.getAttributeValue(HtmlConstants.NAME);
										String id = "ajax_field_" + name;
										//String metaobject = filterElement.getAttributeValue("object");
										String metaobject = "templateBuilder";
										String operator = filterElement.getAttributeValue("operator");
										String fielddescription = filterElement.getAttributeValue("fielddescription") != null ? filterElement.getAttributeValue("fielddescription") : HtmlConstants.EMPTY;
										if(fielddescription.contains(".")){
											fielddescription = fielddescription.substring(fielddescription.indexOf(".")+1, fielddescription.length());
										}
										
										try{
											MetaobjectImpl metaObjectImpl = MetaDataResources.getInstance(ctx).getMetaobject(metaobject);
											if(metaObjectImpl != null && metaObjectImpl.getPropertyversionList() != null){
												PropertyversionImpl propertyversionImpl = (PropertyversionImpl)metaObjectImpl.getPropertyversionList().get(0);
												PropertyImpl propertyImpl = propertyversionImpl.getProperty(name);
												if(propertyImpl != null){
													if(propertyImpl.getDropdownList() != null && propertyImpl.getDropdownList().size() > 0){//going to make multiple checkboxes here
														List dropDownList = propertyImpl.getDropdownList();
														for(int k=0; k<dropDownList.size(); k++){
															DropdownImpl dropDownImpl = (DropdownImpl)dropDownList.get(k);
															
															if(dropDownImpl != null){
																Element tableEle = new Element(HtmlConstants.TABLE);
																tableEle.setAttribute(HtmlConstants.WIDTH, "100%");
																tableEle.setAttribute(HtmlConstants.CELLSPACING, "0");
																tableEle.setAttribute(HtmlConstants.CELLPADDING, "0");
																tableEle.setAttribute(HtmlConstants.CSS_CLASS, "gridTable");
																
																String valuefield = ((ValuefieldImpl)dropDownImpl.getValuefieldList().get(0)).getField();
																String displayfield = ((DisplayfieldImpl)dropDownImpl.getDisplayfieldList().get(0)).getField();
																
																//if(dropDownImpl.getDropdowntype().equals("sql")){
																	//List dataList = SqlResources.getSqlMapProcessor(ctx).select(metaobject+"."+dropDownImpl.getName(), ctx);
																	List dataList = dropDownImpl.getRange(ctx);
																	if(dataList != null && dataList.size() > 0){
																		Element trEle = new Element(HtmlConstants.TR);
																		for(int l=0; l<dataList.size(); l++){
																			if(l%3==0){
																				trEle = new Element(HtmlConstants.TR);
																				
																				if(l%2==0)
																					trEle.setAttribute(HtmlConstants.CSS_CLASS, "listRow2CSS");
																				else
																					trEle.setAttribute(HtmlConstants.CSS_CLASS, "listRow1CSS");
																			}
																			
																			Map dataMap = (Map)dataList.get(l);
																			
																			String value = dataMap.get(valuefield) != null ? dataMap.get(valuefield).toString() : null;
																			
																			Element tdEle = new Element(HtmlConstants.TD);
																			tdEle.setAttribute(HtmlConstants.WIDTH, "33%");
																			
																			boolean isChecked = false;
																			
																			//going to check for edit event
																			if(ctx.get(name) != null){
																				try{
																					StringTokenizer tokens = new StringTokenizer(ctx.get(name).toString(), ",");
																					while(tokens.hasMoreTokens()){
																						String token = tokens.nextToken();
																						
																						String pair = value;
																						if(pair.indexOf("=") != -1)
																							pair = pair.substring(pair.indexOf("=")+1, pair.length());
																						
																						/*if(token.indexOf("=") != -1)
																							token = token.substring(token.indexOf("=")+1, token.length());*/
																						
																						if(token.equals(pair)){
																							isChecked = true;
																							break;
																						}
																					}
																				}catch (Exception e2) {
																					// TODO: handle exception
																				}
																			}
																			
																			if(ctx.get(name+"_"+l) != null && !ctx.get(name+"_"+l).toString().equals(HtmlConstants.EMPTY))
																				isChecked = true;
																			
																			Element checkBoxElement = new ControlGenerator().createCheckbox(null, HtmlConstants.CHECKBOX, name+"_"+l, id+"_"+l, value, isChecked, ctx, null);
																			tdEle.addContent(checkBoxElement.detach());
																			
																			tdEle.addContent(dataMap.get(displayfield) != null ? dataMap.get(displayfield).toString() : HtmlConstants.EMPTY);
																			trEle.addContent(tdEle.detach());
																			
																			//check for empty columns to be created to maintain design on reaching last record 
																			if(l == dataList.size()-1){
																				int rem = dataList.size() % 3;
																				if(rem > 0){
																					for(int m=rem; m<3; m++){
																						tdEle = new Element(HtmlConstants.TD);
																						tdEle.setAttribute(HtmlConstants.WIDTH, "33%");
																						
																						trEle.addContent(tdEle.detach());
																					}
																				}
																			}
																			
																			tableEle.addContent(trEle.detach());
																		}
																	}
																//}
																
																Element fieldSetElement = new Element(HtmlConstants.FIELDSET);
																Element legendElement = new Element(HtmlConstants.LEGEND);
																
																try{
																	String legend = fielddescription;//MetaDataResources.getInstance(ctx).getField(name).getDisplayname();
																	legendElement.addContent(legend);
																}catch (Exception e2) {
																	logger.error("Unable to get field displayname for property "+ name +" due to error : " + e2.getMessage());
																}
																
																fieldSetElement.addContent(legendElement.detach());
																fieldSetElement.addContent(tableEle.detach());
																
																//going to add error div here
																Element errorTableElement = new Element(HtmlConstants.TABLE);
																Element errorTrElement = new Element(HtmlConstants.TR);
																Element errorTdElement = new Element(HtmlConstants.TD);
																
																Element errorDivElement = new Element(HtmlConstants.DIV);
																errorDivElement.setAttribute(HtmlConstants.ID, "ajax_field_"+name);
																errorDivElement.setAttribute(HtmlConstants.CSS_CLASS, "error");
																errorTdElement.addContent(errorDivElement.detach());
																errorDivElement = new Element(HtmlConstants.DIV);
																errorDivElement.setAttribute(HtmlConstants.ID, name);
																errorDivElement.setAttribute(HtmlConstants.CSS_CLASS, "error");
																errorTdElement.addContent(errorDivElement.detach());
																
																errorTrElement.addContent(errorTdElement.detach());
																errorTableElement.addContent(errorTrElement.detach());
																blockElement.addContent(errorTableElement.detach());
																//Ended error code here
																
																blockElement.addContent(fieldSetElement.detach());
															}
														}
													}else{//going to make textbox here because no dropdown exists 
														String value = ctx.get(name) != null ? ctx.get(name).toString() : HtmlConstants.EMPTY;
														
														Element tableElement = new Element(HtmlConstants.TABLE);
														Element trElement = new Element(HtmlConstants.TR);
														
														Element tdElement = new Element(HtmlConstants.TD);
														String displayname = name;
														
														try{
															displayname = fielddescription;//MetaDataResources.getInstance(ctx).getField(name).getDisplayname();
														}catch (Exception e3) {
															// TODO: handle exception
														}
														
														tdElement.addContent(displayname + " : ");
														trElement.addContent(tdElement.detach());
														
														tdElement = new Element(HtmlConstants.TD);
														
														if(operator != null){
															tdElement.addContent(operator);
														}
														
														Element textElement = new Element(HtmlConstants.INPUT);
														textElement.setAttribute(HtmlConstants.TYPE, HtmlConstants.TEXT);
														textElement.setAttribute(HtmlConstants.ID, "ajax_field_"+name);
														textElement.setAttribute(HtmlConstants.NAME, name);
														textElement.setAttribute(HtmlConstants.VALUE, value);
														tdElement.addContent(textElement.detach());
														
														trElement.addContent(tdElement.detach());
														
														tableElement.addContent(trElement.detach());
														
														//going to add error div here
														Element errorTableElement = new Element(HtmlConstants.TABLE);
														Element errorTrElement = new Element(HtmlConstants.TR);
														Element errorTdElement = new Element(HtmlConstants.TD);
														
														Element errorDivElement = new Element(HtmlConstants.DIV);
														errorDivElement.setAttribute(HtmlConstants.ID, "ajax_field_"+name);
														errorDivElement.setAttribute(HtmlConstants.CSS_CLASS, "error");
														errorTdElement.addContent(errorDivElement.detach());
														errorDivElement = new Element(HtmlConstants.DIV);
														errorDivElement.setAttribute(HtmlConstants.ID, name);
														errorDivElement.setAttribute(HtmlConstants.CSS_CLASS, "error");
														errorTdElement.addContent(errorDivElement.detach());
														
														errorTrElement.addContent(errorTdElement.detach());
														errorTableElement.addContent(errorTrElement.detach());
														blockElement.addContent(errorTableElement.detach());
														//Ended error code here
														
														blockElement.addContent(tableElement.detach());
													}
												}else{
													//going to make textbox here because no dropdown exists 
													String value = ctx.get(name) != null ? ctx.get(name).toString() : HtmlConstants.EMPTY;
													
													Element tableElement = new Element(HtmlConstants.TABLE);
													Element trElement = new Element(HtmlConstants.TR);
													
													Element tdElement = new Element(HtmlConstants.TD);
													String displayname = name;
													
													try{
														displayname = fielddescription;//MetaDataResources.getInstance(ctx).getField(name).getDisplayname();
													}catch (Exception e3) {
														// TODO: handle exception
													}
													
													tdElement.addContent(displayname + " : ");
													trElement.addContent(tdElement.detach());
													
													tdElement = new Element(HtmlConstants.TD);
													
													if(operator != null){
														tdElement.addContent(operator);
													}
													
													Element textElement = new Element(HtmlConstants.INPUT);
													textElement.setAttribute(HtmlConstants.TYPE, HtmlConstants.TEXT);
													textElement.setAttribute(HtmlConstants.ID, "ajax_field_"+name);
													textElement.setAttribute(HtmlConstants.NAME, name);
													textElement.setAttribute(HtmlConstants.VALUE, value);
													tdElement.addContent(textElement.detach());
													
													trElement.addContent(tdElement.detach());
													
													tableElement.addContent(trElement.detach());
													
													//going to add error div here
													Element errorTableElement = new Element(HtmlConstants.TABLE);
													Element errorTrElement = new Element(HtmlConstants.TR);
													Element errorTdElement = new Element(HtmlConstants.TD);
													
													Element errorDivElement = new Element(HtmlConstants.DIV);
													errorDivElement.setAttribute(HtmlConstants.ID, "ajax_field_"+name);
													errorDivElement.setAttribute(HtmlConstants.CSS_CLASS, "error");
													errorTdElement.addContent(errorDivElement.detach());
													errorDivElement = new Element(HtmlConstants.DIV);
													errorDivElement.setAttribute(HtmlConstants.ID, name);
													errorDivElement.setAttribute(HtmlConstants.CSS_CLASS, "error");
													errorTdElement.addContent(errorDivElement.detach());
													
													errorTrElement.addContent(errorTdElement.detach());
													errorTableElement.addContent(errorTrElement.detach());
													blockElement.addContent(errorTableElement.detach());
													//Ended error code here
													
													blockElement.addContent(tableElement.detach());
												}
											}
										}catch (Exception e1) {
											logger.error("Unable to get metaobject information due to error : " + e1.getMessage());
											
											//going to make textbox here because no dropdown exists 
											String value = ctx.get(name) != null ? ctx.get(name).toString() : HtmlConstants.EMPTY;
											
											Element tableElement = new Element(HtmlConstants.TABLE);
											Element trElement = new Element(HtmlConstants.TR);
											
											Element tdElement = new Element(HtmlConstants.TD);
											String displayname = name;
											
											try{
												displayname = fielddescription;//MetaDataResources.getInstance(ctx).getField(name).getDisplayname();
											}catch (Exception e3) {
												// TODO: handle exception
											}
											
											tdElement.addContent(displayname + " : ");
											trElement.addContent(tdElement.detach());
											
											tdElement = new Element(HtmlConstants.TD);
											
											if(operator != null){
												tdElement.addContent(operator);
											}
											
											Element textElement = new Element(HtmlConstants.INPUT);
											textElement.setAttribute(HtmlConstants.TYPE, HtmlConstants.TEXT);
											textElement.setAttribute(HtmlConstants.ID, "ajax_field_"+name);
											textElement.setAttribute(HtmlConstants.NAME, name);
											textElement.setAttribute(HtmlConstants.VALUE, value);
											tdElement.addContent(textElement.detach());
											
											trElement.addContent(tdElement.detach());
											
											tableElement.addContent(trElement.detach());
											
											//going to add error div here
											Element errorTableElement = new Element(HtmlConstants.TABLE);
											Element errorTrElement = new Element(HtmlConstants.TR);
											Element errorTdElement = new Element(HtmlConstants.TD);
											
											Element errorDivElement = new Element(HtmlConstants.DIV);
											errorDivElement.setAttribute(HtmlConstants.ID, "ajax_field_"+name);
											errorDivElement.setAttribute(HtmlConstants.CSS_CLASS, "error");
											errorTdElement.addContent(errorDivElement.detach());
											errorDivElement = new Element(HtmlConstants.DIV);
											errorDivElement.setAttribute(HtmlConstants.ID, name);
											errorDivElement.setAttribute(HtmlConstants.CSS_CLASS, "error");
											errorTdElement.addContent(errorDivElement.detach());
											
											errorTrElement.addContent(errorTdElement.detach());
											errorTableElement.addContent(errorTrElement.detach());
											blockElement.addContent(errorTableElement.detach());
											//Ended error code here
											
											blockElement.addContent(tableElement.detach());
										}
									}
								}
							}
						}
					}
				}
				
				//blockElement.addContent(tableElement.detach());
			}
		}catch (Exception e) {
			logger.error("Unable to generate dynamic page content due to error : " + e.getMessage());
		}
	}
	
	private List getMultiDimensionalList(Context ctx, List<String> headersList) throws Exception{
		List finalDataList = new ArrayList();
		
		if(headersList != null && headersList.size() > 0){
			String header = headersList.get(0); //going to get first header only
			
			Map dataMap = new HashMap();
			
			getMultiDimensionalList1(ctx, header, dataMap, headersList, 0, headersList.size(), finalDataList);
		}
		
		/*for(int i=0; i<finalDataList.size(); i++){
			System.out.println(finalDataList.get(i));
		}*/
		
		ctx.put("dynamicPageContentCreateTable_list_1", finalDataList);
		return finalDataList;
	}
	
	private void getMultiDimensionalList1(Context ctx, String header, Map dataMap, List<String> headersList, int headerIndex, int totalCount, List finalDataList) throws Exception{
		if(headerIndex == totalCount){
			Map map = new HashMap();
			map.putAll(dataMap);
			finalDataList.add(map);
			dataMap = new HashMap();
			return;
		}
		
		if(ctx.get(header+"_description") != null && !ctx.get(header+"_description").toString().equals(HtmlConstants.EMPTY)){
			String headerValue = ctx.get(header+"_description").toString();
			
			String[] arr = headerValue.split("#HASH;#");
			for(int i=0; i<arr.length; i++){
				String token = arr[i];
				
				String label = token.substring(0, token.indexOf("="));
				String value = token.substring(token.indexOf("=")+1, token.length());
				
				dataMap.put(header+"_description", label);
				dataMap.put(header, value);
				
				String nextHeader = (headerIndex == totalCount-1 ? null : headersList.get(headerIndex+1)); 
				
				getMultiDimensionalList1(ctx, nextHeader, dataMap, headersList, headerIndex+1, totalCount, finalDataList);
			}
		}else{
			headerIndex++;
		}
	}
	
	private List getDynamicPageDataList(Context ctx, Map dataMap, String displayField, String valueField) throws Exception{
		List dataList = null;
		
		if(dataMap != null && dataMap.get("xml") != null){
			String xml = dataMap.get("xml").toString();
			
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(new StringReader(xml));
			
			Element rootElement = document.getRootElement();
			
			if(rootElement.getContentSize() > 0){
				for(int i=0; i<rootElement.getContentSize(); i++){
					if(rootElement.getContent().get(i) instanceof Element){
						Element element = (Element)rootElement.getContent().get(i);
						
						if(element.getName().equals("DATA_NODE")){
							if(element.getContentSize() > 0){
								String key = null;
								String value = null;
								Map map = new HashMap();
								
								for(int j=0; j<element.getContentSize(); j++){
									if(element.getContent().get(j) instanceof Element){
										Element nodeElement = (Element)element.getContent().get(j);
										
										if(nodeElement.getName().equals(displayField)){
											key = nodeElement.getText();
										}
										if(nodeElement.getName().equals(valueField)){
											value = nodeElement.getText();
										}
									}
								}
								
								if(key != null && value != null){
									dataList = dataList == null ? new ArrayList() : dataList;
									
									map.put(key, value);
									dataList.add(map);
								}
							}
						}
					}
				}
			}
		}
		
		return dataList;
	}
	
	//Method created to generate dynamic page create table based on passing xml
	/*private void generateDynamicPageContentCreateTable(Element blockElement, Context ctx) throws Exception{
		String dynamicPageXML = ctx.get("DYNAMICPAGEXML") != null ? ctx.get("DYNAMICPAGEXML").toString() : null;
		if(dynamicPageXML == null)
			return;
		
		if(ctx.get("DYNAMICPAGECONTENTCREATETABLE_headersMap") == null || ctx.get("DYNAMICPAGECONTENTCREATETABLE_createTableHeadersMap") == null)
			return;
		
		Map headersMap = (Map)ctx.get("DYNAMICPAGECONTENTCREATETABLE_headersMap");
		Map createTableHeadersMap = (Map)ctx.get("DYNAMICPAGECONTENTCREATETABLE_createTableHeadersMap");
		
		if(ctx.get("isDynamicPageContentCreateTable") == null || !ctx.get("isDynamicPageContentCreateTable").toString().equals("Y")){
			Element tableElement = new Element(HtmlConstants.TABLE);
			tableElement.setAttribute(HtmlConstants.ID, "dynamicPageContents_list_mom_1");
			tableElement.setAttribute(HtmlConstants.CSS_CLASS, "gridTable");
			tableElement.setAttribute(HtmlConstants.CELLSPACING, "0");
			tableElement.setAttribute(HtmlConstants.CELLPADDING, "0");
			tableElement.setAttribute(HtmlConstants.WIDTH, "100%");
			
			Element trElement = new Element(HtmlConstants.TR);
			
			if(headersMap != null){
				Set headersMapSet = headersMap.keySet();
				Iterator itr = headersMapSet.iterator();
				while(itr.hasNext()){
					String header = itr.next().toString();
					
					String id = headersMap.get(header) != null ? headersMap.get(header).toString() : header;
					
					Element tdElement = new Element(HtmlConstants.TD);
					tdElement.setAttribute(HtmlConstants.ID, "block_field_"+id);
					tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
					tdElement.addContent(header);
					
					trElement.addContent(tdElement);
				}
				
				Element tdElement = new Element(HtmlConstants.TD);
				tdElement.setAttribute(HtmlConstants.ID, "block_field_comm_rate_NB");
				tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
				tdElement.addContent("Rate NB");
				
				trElement.addContent(tdElement);
				
				tdElement = new Element(HtmlConstants.TD);
				tdElement.setAttribute(HtmlConstants.ID, "block_field_comm_rate_RB");
				tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
				tdElement.addContent("Rate RB");
				
				trElement.addContent(tdElement);
				
				tableElement.addContent(trElement);
			}
			
			//going to create empty record row
			trElement = new Element(HtmlConstants.TR);
			Element tdElement = new Element(HtmlConstants.TD);
			tdElement.addContent("No Record Found");
			tdElement.setAttribute("class", "EmptyRowStyle");
			tdElement.setAttribute("colspan", "20");
			
			trElement.addContent(tdElement);
			tableElement.addContent(trElement);
			
			blockElement.addContent(tableElement.detach());
			return;
		}else{
			if(headersMap != null){
				List<String> headersList = new ArrayList();
				Set headersMapSet = createTableHeadersMap.keySet();
				Iterator itr = headersMapSet.iterator();
				while(itr.hasNext()){
					String header = itr.next().toString();
					
					header = headersMap.get(header) != null ? headersMap.get(header).toString() : header;
					
					headersList.add(header);
				}
				
				List finalDataList = getMultiDimensionalList(ctx, headersList);
				
				Element tableElement = new Element(HtmlConstants.TABLE);
				tableElement.setAttribute(HtmlConstants.ID, "dynamicPageContents_list_mom_1");
				tableElement.setAttribute(HtmlConstants.CSS_CLASS, "gridTable");
				tableElement.setAttribute(HtmlConstants.CELLSPACING, "0");
				tableElement.setAttribute(HtmlConstants.CELLPADDING, "0");
				tableElement.setAttribute(HtmlConstants.WIDTH, "100%");
				
				Element trElement = new Element(HtmlConstants.TR);
				if(headersMap != null){
					headersMapSet = headersMap.keySet();
					itr = headersMapSet.iterator();
					while(itr.hasNext()){
						String header = itr.next().toString();
						
						String id = headersMap.get(header) != null ? headersMap.get(header).toString() : header;
						
						Element tdElement = new Element(HtmlConstants.TD);
						
						//going to check for text/select components if exists as header having _text/_select in last
						if(id.endsWith("_text")){
							id = id.substring(0, id.lastIndexOf("_text"));
						}else if(id.endsWith("_checkbox")){
							id = id.substring(0, id.lastIndexOf("_checkbox"));
						}else if(id.endsWith("_radio")){
							id = id.substring(0, id.lastIndexOf("_radio"));
						}else if(id.contains("_select=")){
							id = id.substring(0, id.lastIndexOf("_select="));
						}
						
						tdElement.setAttribute(HtmlConstants.ID, "block_field_"+id);
						tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
						tdElement.addContent(header);
						
						trElement.addContent(tdElement);
					}
					
					Element tdElement = new Element(HtmlConstants.TD);
					tdElement.setAttribute(HtmlConstants.ID, "block_field_comm_rate_NB");
					tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
					tdElement.addContent("Rate NB");
					
					trElement.addContent(tdElement);
					
					tdElement = new Element(HtmlConstants.TD);
					tdElement.setAttribute(HtmlConstants.ID, "block_field_comm_rate_RB");
					tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
					tdElement.addContent("Rate RB");
					
					trElement.addContent(tdElement);
					
					tableElement.addContent(trElement);
				}
				
				if(finalDataList != null && finalDataList.size() > 0){
					for(int i=0; i<finalDataList.size(); i++){
						Map rowMap = (Map)finalDataList.get(i);
						
						trElement = new Element(HtmlConstants.TR);
						
						if(i%2==0)
							trElement.setAttribute(HtmlConstants.CSS_CLASS, "listRow2CSS");
						else
							trElement.setAttribute(HtmlConstants.CSS_CLASS, "listRow1CSS");
						
						if(headersMap != null){
							itr = headersMapSet.iterator();
							while(itr.hasNext()){
								String header = itr.next().toString();
								
								String id = headersMap.get(header) != null ? headersMap.get(header).toString() : header;
								
								Element tdElement = new Element(HtmlConstants.TD);
								
								//going to check for text/select components if exists as header having _text/_select in last
								if(id.endsWith("_text")){
									id = id.substring(0, id.lastIndexOf("_text"));
									
									String value = (rowMap.get(id) != null && !rowMap.get(id).toString().equals(HtmlConstants.EMPTY)) ? rowMap.get(id).toString() : null;
									value = (rowMap.get(id+"_description") != null && !rowMap.get(id+"_description").toString().equals(HtmlConstants.EMPTY)) ? rowMap.get(id+"_description").toString() : value;
									
									Element textBoxElement = new ControlGenerator().createText(null, id+"_"+i, id+"_"+i, null, ctx, null);
									tdElement.addContent(textBoxElement.detach());
								}else if(id.endsWith("_checkbox")){
									id = id.substring(0, id.lastIndexOf("_checkbox"));
									
									String value = (rowMap.get(id) != null && !rowMap.get(id).toString().equals(HtmlConstants.EMPTY)) ? rowMap.get(id).toString() : null;
									value = (rowMap.get(id+"_description") != null && !rowMap.get(id+"_description").toString().equals(HtmlConstants.EMPTY)) ? rowMap.get(id+"_description").toString() : value;
									
									Element checkBoxElement = new ControlGenerator().createCheckbox(null, null, id+"_"+i, id+"_"+i, value, false, ctx, null);
									tdElement.addContent(checkBoxElement.detach());
								}else if(id.endsWith("_radio")){
									id = id.substring(0, id.lastIndexOf("_radio"));
									
									String value = (rowMap.get(id) != null && !rowMap.get(id).toString().equals(HtmlConstants.EMPTY)) ? rowMap.get(id).toString() : null;
									value = (rowMap.get(id+"_description") != null && !rowMap.get(id+"_description").toString().equals(HtmlConstants.EMPTY)) ? rowMap.get(id+"_description").toString() : value;
									
									Element radioElement = new ControlGenerator().createRadio(null, null, id+"_"+i, id+"_"+i, value, false, ctx, null);
									tdElement.addContent(radioElement.detach());
								}else if(id.contains("_select=")){
									String sourceDisplayValuefld = id.substring(id.indexOf("_select=")+8, id.length());
									
									id = id.substring(0, id.indexOf("_select="));
									
									String sourceDisplayValuefldArr[] = sourceDisplayValuefld.split("=");
									String source = null;
									String displayfield = null;
									String valuefield = null;
												
									if(sourceDisplayValuefldArr != null && sourceDisplayValuefldArr.length == 3){
										source = sourceDisplayValuefldArr[0];
										displayfield = sourceDisplayValuefldArr[1];
										valuefield = sourceDisplayValuefldArr[2];
									}
									
									String value = (rowMap.get(id) != null && !rowMap.get(id).toString().equals(HtmlConstants.EMPTY)) ? rowMap.get(id).toString() : null;
									value = (rowMap.get(id+"_description") != null && !rowMap.get(id+"_description").toString().equals(HtmlConstants.EMPTY)) ? rowMap.get(id+"_description").toString() : value;
									
									Element selectElement = new ControlGenerator().createSelect(null, id+"_"+i, id+"_"+i, null, false, -1, ctx, null);
									selectElement.setAttribute(HtmlConstants.STYLE, "width:235px");
									
									Element optionElement = new ControlGenerator().createOption(null, HtmlConstants.EMPTY, "Select", null);
									selectElement.addContent(optionElement);
									
									List dataList = null;
									try{
										ctx.put("table_name", source);
										ctx.put("column_name", displayfield+","+valuefield);
										Object dataMapObj = SqlResources.getSqlMapProcessor(ctx).findByKey("person.getDynamicPageDDList_p_java", ctx);
										if(dataMapObj != null && dataMapObj instanceof Map){
											Map dataMap = (Map)dataMapObj;
											
											//going to get dynamic page data list
											dataList = getDynamicPageDataList(ctx, dataMap, displayfield, valuefield);
										}
									}catch (Exception e) {
										logger.error("Unable to get data for dynamic page due to error : " + e.getMessage());
										continue;
									}
									
									//creating options
									if(dataList != null && dataList.size() > 0){
										for(int k=0; k<dataList.size(); k++){
											Map dataMap = (Map)dataList.get(k);
											String label = null;
											String mapValue = null;
											
											Set<Map.Entry<String, String>> entrySet = dataMap.entrySet();
											Iterator<Map.Entry<String, String>> itr1 = entrySet.iterator();
											while(itr1.hasNext()){
												Map.Entry<String, String> entry = itr1.next();
												
												label = entry.getKey();
												mapValue = entry.getValue();
											}
											
											optionElement = new ControlGenerator().createOption(null, mapValue, label, null);
											
											selectElement.addContent(optionElement);
										}
									}
									
									tdElement.addContent(selectElement.detach());
								}else{ 
									String value = (rowMap.get(id) != null && !rowMap.get(id).toString().equals(HtmlConstants.EMPTY)) ? rowMap.get(id).toString() : null;
									value = (rowMap.get(id+"_description") != null && !rowMap.get(id+"_description").toString().equals(HtmlConstants.EMPTY)) ? rowMap.get(id+"_description").toString() : value;
									
									tdElement.addContent(value);
								}
								
								trElement.addContent(tdElement.detach());
							}
						}
						
						Element tdElement = new Element(HtmlConstants.TD);
						Element textBoxElement = new ControlGenerator().createText(null, "block_field_comm_rate_NB", "block_field_comm_rate_NB_"+i, null, ctx, null);
						tdElement.addContent(textBoxElement.detach());
						
						trElement.addContent(tdElement.detach());
						
						tdElement = new Element(HtmlConstants.TD);
						textBoxElement = new ControlGenerator().createText(null, "block_field_comm_rate_RB", "block_field_comm_rate_RB_"+i, null, ctx, null);
						tdElement.addContent(textBoxElement.detach());
						
						trElement.addContent(tdElement.detach());
						
						tableElement.addContent(trElement.detach());
					}
					
					blockElement.addContent(tableElement.detach());
					return;
				}else{
					//going to create empty record row
					trElement = new Element(HtmlConstants.TR);
					Element tdElement = new Element(HtmlConstants.TD);
					tdElement.addContent("No Record Found");
					tdElement.setAttribute("class", "EmptyRowStyle");
					tdElement.setAttribute("colspan", "20");
					
					trElement.addContent(tdElement);
					tableElement.addContent(trElement);
					
					blockElement.addContent(tableElement.detach());
					return;
				}
			}
		//}
	}*/
	
	//Method created to generate dynamic page create table based on passing xml
	private void generateDynamicPageContentCreateTable(Element blockElement, Context ctx) throws Exception{
		/*if(ctx.get("isDynamicPageContentCreateTable") == null || !ctx.get("isDynamicPageContentCreateTable").toString().equals("Y"))
			return;*/
		
		String dynamicPageXML = ctx.get("DYNAMICPAGEXML") != null ? ctx.get("DYNAMICPAGEXML").toString() : null;
		if(dynamicPageXML == null)
			return;
	
		try{
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new StringReader(dynamicPageXML));
			Element root = doc.getRootElement();
			
			//going to generate data row
			List finalDatalist = null;
			if(ctx.get("isDynamicPageContentCreateTable") != null && ctx.get("isDynamicPageContentCreateTable").toString().equals("Y")){
				finalDatalist = generateDynamicPageContentDataRowCreateTable(ctx);
			}
			
			if(root.getContent() != null && root.getContent().size() > 0){
				for(int i=0; i<root.getContent().size(); i++){
					Object obj = root.getContent().get(i);
					if(obj != null && obj instanceof Element){
						if(((Element)obj).getName().equals("tables")){//getting <tables> node
							Element tablesElementNode = (Element)obj;
							
							if(tablesElementNode.getContent() != null && tablesElementNode.getContent().size() > 0){
								int idIndex = 1;
								
								//Map created to store decision tables data row list
								LinkedHashMap createTableDataListMap = new LinkedHashMap();
								
								for(int j=0; j<tablesElementNode.getContent().size(); j++){
									Object obj1 = tablesElementNode.getContent().get(j);
									
									if(obj1 != null && obj1 instanceof Element && ((Element)obj1).getName().equals("table")){//getting <table> node
										Element tableElementNode = (Element)tablesElementNode.getContent().get(j);
										
										String label = tableElementNode.getAttributeValue(HtmlConstants.NAME);
										
										String listId = "createTable_list_mom_" + idIndex;
										String listheaderId = "createTable_list_header_" + idIndex;
										String listdataId = "createTable_list_mom_data_" + idIndex;
										
										Element tableEle = new Element(HtmlConstants.TABLE);
										tableEle.setAttribute(HtmlConstants.WIDTH, "100%");
										tableEle.setAttribute(HtmlConstants.ID, listId);
										tableEle.setAttribute(HtmlConstants.CELLSPACING, "0");
										tableEle.setAttribute(HtmlConstants.CELLPADDING, "0");
										
										//going to generate headers row
										Element trEle = new Element(HtmlConstants.TR);
										trEle.setAttribute(HtmlConstants.ID, listheaderId);
										
										if(tableElementNode.getContent() != null && tableElementNode.getContent().size() > 0){//getting <fields> node
											for(int k=0; k<tableElementNode.getContent().size(); k++){
												Object obj2 = tableElementNode.getContent().get(k);
												
												if(obj2 != null & obj2 instanceof Element){
													if(((Element)obj2).getName().equals("fields")){
														Element fieldsElementNode = (Element)obj2;
														
														if(fieldsElementNode.getContent() != null && fieldsElementNode.getContent().size() > 0){//getting <field> node
															for(int l=0; l<fieldsElementNode.getContent().size(); l++){
																Object obj3 = fieldsElementNode.getContent().get(l);
																
																if(obj3 != null & obj3 instanceof Element){
																	if(((Element)obj3).getName().equals("field")){
																		Element fieldElementNode = (Element)obj3;
																		
																		String name = fieldElementNode.getAttributeValue(HtmlConstants.NAME);
																		String displayname = fieldElementNode.getAttributeValue("fielddescription") != null ? fieldElementNode.getAttributeValue("fielddescription") : fieldElementNode.getAttributeValue(HtmlConstants.NAME);
																		/*try{
																			displayname = MetaDataResources.getInstance(ctx).getField(name).getDisplayname();
																		}catch (Exception e1) {
																			logger.error("Unable to get displayname of property " + name + " due to error : " + e1.getMessage());
																		}*/
																		if(displayname != null && displayname.indexOf(".") != -1){
																			displayname = displayname.substring(displayname.indexOf(".")+1, displayname.length());
																		}
																		
																		Element tdEle = new Element(HtmlConstants.TD);
																		tdEle.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
																		tdEle.setAttribute(HtmlConstants.ID, "block_field_"+name);
																		tdEle.addContent(displayname);
																		
																		trEle.addContent(tdEle.detach());
																		
																		tableEle.addContent(trEle.detach());
																	}
																}
															}
														}
													}
												}
											}
										}
										
										/*//going to generate data row
										List finalDatalist = generateDynamicPageContentDataRowCreateTable(ctx);*/
										
										//going to generate data row in case of edit event bcoz in this case data will come from database xml
										//show this list only when we open page first time, if we click on createTable again then new list will come
										if(ctx.get("inet_eventid") != null && ctx.get("inet_eventid").toString().equals("edit")){
											if(ctx.get("isDynamicPageContentCreateTable") == null || !ctx.get("isDynamicPageContentCreateTable").toString().equals("Y")){
												if(ctx.get("editCreateTableDataList") != null && ctx.get("editCreateTableDataList") instanceof List){
													if(((List)ctx.get("editCreateTableDataList")).size() >= idIndex)
														finalDatalist = (List)((List)ctx.get("editCreateTableDataList")).get(idIndex-1);
												}
											}
										}
										
										if(finalDatalist != null && finalDatalist.size() > 0){
											if(tableElementNode.getContent() != null && tableElementNode.getContent().size() > 0){//getting <fields> node
												for(int k=0; k<tableElementNode.getContent().size(); k++){
													Object obj2 = tableElementNode.getContent().get(k);
													
													if(obj2 != null & obj2 instanceof Element){
														if(((Element)obj2).getName().equals("fields")){
															List createTableDataList = new ArrayList();
															
															Element fieldsElementNode = (Element)obj2;
															
															for(int a=0; a<finalDatalist.size(); a++){
																Map dataMap = (Map)finalDatalist.get(a);
																
																LinkedHashMap createTableDataMap = new LinkedHashMap();
																
																trEle = new Element(HtmlConstants.TR);
																trEle.setAttribute(HtmlConstants.ID, listdataId);
																if(a%2==0)
																	trEle.setAttribute(HtmlConstants.CSS_CLASS, "listRow2CSS");
																else
																	trEle.setAttribute(HtmlConstants.CSS_CLASS, "listRow1CSS");
																
																if(fieldsElementNode.getContent() != null && fieldsElementNode.getContent().size() > 0){//getting <field> node
																	int fldIndex = 0;
																	for(int l=0; l<fieldsElementNode.getContent().size(); l++){
																		Object obj3 = fieldsElementNode.getContent().get(l);
																		
																		if(obj3 != null & obj3 instanceof Element){
																			if(((Element)obj3).getName().equals("field")){
																				Element fieldElementNode = (Element)obj3;
																				
																				String name = fieldElementNode.getAttributeValue(HtmlConstants.NAME);
																				String displayname = fieldElementNode.getAttributeValue("fielddescription");
																				String metaobject = fieldElementNode.getAttributeValue("object");
																				if(metaobject != null && !metaobject.equalsIgnoreCase("operator"))
																					metaobject = "templateBuilder";
																				
																				String isfilter = fieldElementNode.getAttributeValue("isfilter");
																				
																				Element tdEle = new Element(HtmlConstants.TD);
																				
																				tdEle.setAttribute(HtmlConstants.ID, "block_field_"+name);
																				
																				String pair = dataMap.get(name) != null ? dataMap.get(name).toString() : HtmlConstants.EMPTY;
																				String value = pair;
																				
																				//if filter element found then make it label only
																				if(isfilter != null && isfilter.equals("Y")){
																					String description = pair;
																						
																					if(pair.indexOf("=") != -1)
																						description = pair.substring(0, pair.indexOf("="));
																						
																					//value = pair.substring(pair.indexOf("=")+1, pair.length());
																						
																					tdEle.addContent(description);
																				}else{//otherwise check for fieldtype to be generated
																					boolean isDropDownExists = false;
																					try{
																						MetaobjectImpl metaObjectImpl = MetaDataResources.getInstance(ctx).getMetaobject(metaobject);
																						if(metaObjectImpl != null && metaObjectImpl.getFirstPropertyversion() != null){
																							PropertyImpl propertyImpl = metaObjectImpl.getFirstPropertyversion().getProperty(name);
																							if(propertyImpl != null && propertyImpl.getDropdownList() != null){
																								List dropDownList = propertyImpl.getDropdownList();
																								for(int m=0; m<dropDownList.size(); m++){
																									DropdownImpl dropDownImpl = (DropdownImpl)dropDownList.get(m);
																									if(dropDownImpl != null){
																										Element selectElement = new Element(HtmlConstants.SELECT);
																										//selectElement.setAttribute(HtmlConstants.WIDTH, "100%");
																										selectElement.setAttribute(HtmlConstants.NAME, name+"_"+a);
																										selectElement.setAttribute(HtmlConstants.ID, "ajax_field_"+name+"_"+a);
																										
																										Element optionElement = new Element(HtmlConstants.OPTION);
																										optionElement.setAttribute(HtmlConstants.VALUE, HtmlConstants.EMPTY);
																										optionElement.addContent("Select");
																										selectElement.addContent(optionElement.detach());
																										
																										String valuefield = ((ValuefieldImpl)dropDownImpl.getValuefieldList().get(0)).getField();
																										String displayfield = ((DisplayfieldImpl)dropDownImpl.getDisplayfieldList().get(0)).getField();
																										
																										//if(dropDownImpl.getDropdowntype().equals("sql")){
																											//List dataList = SqlResources.getSqlMapProcessor(ctx).select(metaobject+"."+dropDownImpl.getName(), ctx);
																											List dataList = dropDownImpl.getRange(ctx);
																											if(dataList != null && dataList.size() > 0){
																												for(int n=0; n<dataList.size(); n++){
																													Map selectDataMap = (Map)dataList.get(n);
																													
																													String optionValue = selectDataMap.get(valuefield) != null ? selectDataMap.get(valuefield).toString() : null;
																													
																													if(ctx.get(name+"_"+a) != null && !ctx.get(name+"_"+a).toString().equals(HtmlConstants.EMPTY))
																														value = ctx.get(name+"_"+a).toString();
																													
																													optionElement = new Element(HtmlConstants.OPTION);
																													
																													optionElement.setAttribute(HtmlConstants.VALUE, optionValue);
																													
																													String displayValue = selectDataMap.get(displayfield) != null ? selectDataMap.get(displayfield).toString() : null;
																													optionElement.addContent(displayValue);
																													
																													if(value.equals(optionValue))
																														optionElement.setAttribute(HtmlConstants.SELECTED, HtmlConstants.SELECTED);
																													
																													selectElement.addContent(optionElement.detach());
																												}
																											}
																										//}
																										
																										isDropDownExists = true;
																										tdEle.addContent(selectElement.detach());
																									}
																								}
																							}
																						}
																					}catch (Exception e) {
																						isDropDownExists = false;
																					}
																					
																					if(!isDropDownExists){
																						Element textElement = new Element(HtmlConstants.INPUT);
																						textElement.setAttribute(HtmlConstants.TYPE, HtmlConstants.TEXT);
																						textElement.setAttribute(HtmlConstants.NAME, name+"_"+a);
																						textElement.setAttribute(HtmlConstants.ID, "ajax_field_"+name+"_"+a);
																						
																						if(ctx.get(name+"_"+a) != null && !ctx.get(name+"_"+a).toString().equals(HtmlConstants.EMPTY))
																							value = ctx.get(name+"_"+a).toString();
																						
																						textElement.setAttribute(HtmlConstants.VALUE, value);
																						
																						tdEle.addContent(textElement.detach());
																					}
																				}
																				
																				trEle.addContent(tdEle.detach());
																				
																				tableEle.addContent(trEle.detach());
																				
																				//Putting datamap into createTableListMap to use it while making XML
																				createTableDataMap.put(HtmlConstants.NAME+"_"+fldIndex, name);
																				createTableDataMap.put("isfilter"+"_"+fldIndex, isfilter);
																				createTableDataMap.put(HtmlConstants.VALUE+"_"+fldIndex, value);
																				
																				fldIndex++;
																			}
																		}
																	}
																	
																	createTableDataMap.put("table_name", label);
																	
																	createTableDataList.add(createTableDataMap);
																}
															}
															
															//Putting datalist into map to get it while making XML 
															createTableDataListMap.put("createTableDataList_"+j, createTableDataList);
														}
													}
												}
											}
										}
										
										Element fieldSetElement = new Element(HtmlConstants.FIELDSET);
										Element legendElement = new Element(HtmlConstants.LEGEND);
										legendElement.addContent(label);
										
										fieldSetElement.addContent(legendElement);
										fieldSetElement.addContent(tableEle.detach());
										
										blockElement.addContent(fieldSetElement.detach());
										
										idIndex++;
									}
								}
								
								ctx.put("createTableDataListMap", createTableDataListMap);
								
							}
						}
					}
				}
			}
		}catch (Exception e) {
			logger.error("Unexpected error", e);
			logger.error("Unable to generate dynamic page content create table due to error : " + e.getMessage());
		}
	}
	
	//Method created to generate create table data row
	private List generateDynamicPageContentDataRowCreateTable(Context ctx) throws Exception{
		if(ctx.get("isDynamicPageContentCreateTable") == null || !ctx.get("isDynamicPageContentCreateTable").toString().equals("Y"))
			return null;
		
		String dynamicPageXML = ctx.get("DYNAMICPAGEXML") != null ? ctx.get("DYNAMICPAGEXML").toString() : null;
		if(dynamicPageXML == null)
			return null;
	
		List finalDataList = new ArrayList();
		try{
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new StringReader(dynamicPageXML));
			Element root = doc.getRootElement();
			List filtersDataList = new ArrayList();
			
			if(root.getContent() != null && root.getContent().size() > 0){
				for(int i=0; i<root.getContent().size(); i++){
					Object obj = root.getContent().get(i);
					if(obj != null && obj instanceof Element){
						if(((Element)obj).getName().equals("filters")){//getting <filters> node
							Element filtersElementNode = (Element)obj;
							
							if(filtersElementNode.getContent() != null && filtersElementNode.getContent().size() > 0){
								for(int j=0; j<filtersElementNode.getContent().size(); j++){
									Object obj1 = filtersElementNode.getContent().get(j);
									
									if(obj1 != null && obj1 instanceof Element && ((Element)obj1).getName().equals("filter")){//getting <filter> node
										Element filterElementNode = (Element)filtersElementNode.getContent().get(j);
										
										String name = filterElementNode.getAttributeValue(HtmlConstants.NAME);
										String metaobject = "templateBuilder";//filterElementNode.getAttributeValue("object");
										
										List filterDataList = new ArrayList();
										
										boolean isDropDownExists = false;
										try{
											MetaobjectImpl metaObjectImpl = MetaDataResources.getInstance(ctx).getMetaobject(metaobject);
											if(metaObjectImpl != null && metaObjectImpl.getFirstPropertyversion() != null){
												if(metaObjectImpl.getFirstPropertyversion().getProperty(name) != null){
													PropertyImpl propertyImpl = metaObjectImpl.getFirstPropertyversion().getProperty(name);
													if(propertyImpl != null && propertyImpl.getDropdownList() != null && propertyImpl.getDropdownList().size() > 0){
														isDropDownExists = true;
													}
												}
											}
										}catch (Exception e1) {
											// TODO: handle exception
										}
										
										if(isDropDownExists){
											for(int a=0; a<1000; a++){
												if(ctx.get(name+"_"+a) != null && !ctx.get(name+"_"+a).toString().equals(HtmlConstants.EMPTY)){
													Map map = new HashMap();
													
													String pair = ctx.get(name+"_"+a).toString();
													
													map.put(name, pair);
													
													filterDataList.add(map);
												}
											}
										}else{
											Map map = new HashMap();
											
											String pair = ctx.get(name).toString();
											
											map.put(name, pair);
											
											filterDataList.add(map);
										}
										
										if(filterDataList == null || filterDataList.size() == 0){
											String label = new DataUtils().getLabelFromLabelConf(name);
											if(isDropDownExists)
												new DataUtils().populateError(ctx, name, "Please select " + label);
											else
												new DataUtils().populateError(ctx, name, "Please enter " + label);
											
											return null;
										}
											
										filtersDataList.add(filterDataList);
									}
								}
							}
							
							logger.debug(filtersDataList);
						}
					}
				}
			}
			
			if(filtersDataList != null && filtersDataList.size() > 0){
				int index = 0;
				for(int i=index; i<1; i++){
					List firstList = (List)filtersDataList.get(i);
					
					for(int j=0; j<firstList.size(); j++){
						Map row = (Map)firstList.get(j);
						
						LinkedHashMap finalMap = new LinkedHashMap();
						
						Set<Map.Entry<String, String>> entrySet = row.entrySet();
						Iterator<Map.Entry<String, String>> itr = entrySet.iterator();
						while(itr.hasNext()){
							Map.Entry<String, String> entry = itr.next();
							
							finalMap.put(entry.getKey(), entry.getValue());
						}
						
						//if next indexed list exists then go to make matrix of that list items also
						getInnerCreateTable(finalMap, filtersDataList, index+1, finalDataList);
						
						//index++;
						if(finalDataList.size() == 0)
							finalDataList.add(finalMap);
					}
				}
			}
		}catch (Exception e) {
			logger.error("Unable to generate dynamic page content create table due to error : " + e.getMessage());
		}
		
		return finalDataList;
	}
	
	//Method created to get next indexed data list
	private void getInnerCreateTable(LinkedHashMap finalMap, List filtersDataList, int index, List finalDataList) throws Exception{
		try{
			if(filtersDataList.get(index) != null && ((List)filtersDataList.get(index)).size() > 0){
				List list = (List)(List)filtersDataList.get(index);
				
				for(int i=0; i<list.size(); i++){
					Map dataMap = (Map)list.get(i);
					
					Set<Map.Entry<String, String>> entrySet = dataMap.entrySet();
					Iterator<Map.Entry<String, String>> itr = entrySet.iterator();
					while(itr.hasNext()){
						Map.Entry<String, String> entry = itr.next();
						
						finalMap.put(entry.getKey(), entry.getValue());
					}
					
					getInnerCreateTable(finalMap, filtersDataList, index+1, finalDataList);
				}
			}
		}catch (Exception e) {
			logger.error("Unable to get inner create table items due to error : " + e.getMessage());
			Map newMap = new HashMap();
			newMap.putAll(finalMap);
			
			finalDataList.add(newMap);
		}
	}
	
	//Method created to generate template builder page having created decision tables data
	private void generateDynamicTemplateBuilderPageContentFullCreateTable(Element blockElement, Context ctx) throws Exception{
		//going to display decision table list
		if(ctx.get("DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLEFULLLISTNAME") != null && !ctx.get("DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLEFULLLISTNAME").toString().equals(HtmlConstants.EMPTY)){
			String DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLEFULLLISTNAME = ctx.get("DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLEFULLLISTNAME").toString();
			
			if(ctx.get(DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLEFULLLISTNAME) != null && ctx.get(DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLEFULLLISTNAME) instanceof List){
				List list = (List)ctx.get(DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLEFULLLISTNAME);
				
				if(list != null && list.size() > 0){
					for(int i=0; i<list.size(); i++){
						Map rowMap = (Map)list.get(i);
						Set keysSet = rowMap.keySet();
						Iterator itr = keysSet.iterator();
						while(itr.hasNext()){
							String key = itr.next().toString();
							
							Object valueObj = rowMap.get(key);
							if(valueObj != null && valueObj instanceof List){
								List rowList = (List)valueObj;
								
								Element divElement = new Element(HtmlConstants.DIV);
								divElement.setAttribute(HtmlConstants.CSS_CLASS, "templateBuilderDiv");
								
								/*Element innerDivElement = new Element(HtmlConstants.DIV);
								innerDivElement.setAttribute(HtmlConstants.STYLE, "font-weight:bold;");
								innerDivElement.addContent(key);
								divElement.addContent(innerDivElement.detach());*/
								
								Element h3Element = new Element("h3");
								h3Element.addContent(key);
								divElement.addContent(h3Element.detach());
								//blockElement.addContent(innerDivElement.detach());
								
								Element ulElement = new Element("ul");
								ulElement.setAttribute(HtmlConstants.ID, DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLEFULLLISTNAME);
								ulElement.setAttribute(HtmlConstants.CSS_CLASS, "templateBuilderUL");
								
								/*Element tableElement = new Element(HtmlConstants.TABLE);
								tableElement.setAttribute(HtmlConstants.ID, DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLEFULLLISTNAME);
								tableElement.setAttribute(HtmlConstants.WIDTH, "100%");
								tableElement.setAttribute(HtmlConstants.CELLSPACING, "0");
								tableElement.setAttribute(HtmlConstants.CELLPADDING, "0");
								
								Element trElement = new Element(HtmlConstants.TR);*/
								
								for(int j=0; j<rowList.size(); j++){
									Map row = (Map)rowList.get(j);
									
									String selectfieldname = row.get("selectfieldname").toString();
									String selectfielddescription = row.get("selectfielddescription").toString();
									
									String headerDescription = selectfielddescription;
									if(selectfielddescription.indexOf(".") != -1)
										headerDescription = selectfielddescription.substring(selectfielddescription.indexOf(".")+1, selectfielddescription.length());
									
									Element liElement = new Element("li");
									liElement.setAttribute(HtmlConstants.CSS_CLASS, "templateBuilderLI");
									
									Element spanElement = new Element(HtmlConstants.SPAN);
									spanElement.addContent(headerDescription);
									liElement.addContent(spanElement.detach());
									
									//going to add attach rule icon for per column
									//if(ctx.get("ISSKIPDELETECOLUMNTEMPLATEBUILDER") == null || !ctx.get("ISSKIPDELETECOLUMNTEMPLATEBUILDER").toString().equals("Y")){
										spanElement = new Element(HtmlConstants.SPAN);
										spanElement.setAttribute(HtmlConstants.ID, "block_field_"+selectfieldname+"_attachrule_row_link");
										spanElement.setAttribute(HtmlConstants.CSS_CLASS, "icoAttachRuleMini");
										Element aElement = new Element(HtmlConstants.LINK);
										
										String dynaKeys = "|attachRuleToTemplateBuilderColumn|attachTemplateBuilderColumnRuleListName|isDecisionTableColumnFound|attachTemplateBuilderColumnRuleListIndex|inet_skip_validation|";
										String dynaValues = "|"+selectfieldname+"|"+DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLEFULLLISTNAME+"|Y|"+i+"|Y|";
										String href = "javascript:manageajaxpageblocklink(document.forms[0],'attachRuleToTemplateBuilderColumn','"+dynaKeys+"','"+dynaValues+"','addManageTemplate3','popupAttachManageRule');";
										
										aElement.setAttribute(HtmlConstants.HREF, href);
										
										spanElement.addContent(aElement.detach());
										liElement.addContent(spanElement.detach());
									//}
									//Ended
									
									//going to make delete icon for per column
									if(ctx.get("ISSKIPDELETECOLUMNTEMPLATEBUILDER") == null || !ctx.get("ISSKIPDELETECOLUMNTEMPLATEBUILDER").toString().equals("Y")){
										spanElement = new Element(HtmlConstants.SPAN);
										spanElement.setAttribute(HtmlConstants.ID, "block_field_"+selectfieldname+"_delete_row_link");
										spanElement.setAttribute(HtmlConstants.CSS_CLASS, "icoDeleteMini");
										aElement = new Element(HtmlConstants.LINK);
										
										dynaKeys = "|deleteTemplateBuilderColumn|deleteTemplateBuilderColumnListName|deleteTemplateBuilderColumnListIndex|NEXT_PAGE|";
										dynaValues = "|"+selectfieldname+"|"+DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLEFULLLISTNAME+"|"+i+"|"+ctx.get(HtmlConstants.NEXT_PAGE)+"|";
										href = "javascript:managesubmitform(document.forms[0],'deleteTemplateBuilderColumn','"+dynaKeys+"','"+dynaValues+"','addManageTemplate3');";
										
										aElement.setAttribute(HtmlConstants.HREF, href);
										
										spanElement.addContent(aElement.detach());
										liElement.addContent(spanElement.detach());
									}
									
									ulElement.addContent(liElement.detach());
									
									/*Element tdElement = new Element(HtmlConstants.TD);
									tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
									
									Element tableInnerEle = new Element(HtmlConstants.TABLE);
									tableInnerEle.setAttribute(HtmlConstants.CELLSPACING, "0");
									tableInnerEle.setAttribute(HtmlConstants.CELLPADDING, "0");
									
									Element trInnerElement = new Element(HtmlConstants.TR);
									
									Element tdInnerElement = new Element(HtmlConstants.TD);
									tdInnerElement.setAttribute(HtmlConstants.CSS_CLASS, "icoDeleteMiniHeaderStyle");
									tdInnerElement.addContent(headerDescription);
									trInnerElement.addContent(tdInnerElement.detach());
									
									//going to add delete icon with each column
									tdInnerElement = new Element(HtmlConstants.TD);
									tdInnerElement.setAttribute(HtmlConstants.CSS_CLASS, "icoDeleteMiniTD");
									Element spanElement = new Element(HtmlConstants.SPAN);
									spanElement.setAttribute(HtmlConstants.ID, "block_field_"+selectfieldname+"_delete_row_link");
									spanElement.setAttribute(HtmlConstants.CSS_CLASS, "icoDeleteMini");
									Element aElement = new Element(HtmlConstants.LINK);
									
									String dynaKeys = "|deleteTemplateBuilderColumn|deleteTemplateBuilderColumnListName|deleteTemplateBuilderColumnListIndex|NEXT_PAGE|";
									String dynaValues = "|"+selectfieldname+"|"+DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLEFULLLISTNAME+"|"+i+"|"+ctx.get(HtmlConstants.NEXT_PAGE)+"|";
									String href = "javascript:managesubmitform(document.forms[0],'deleteTemplateBuilderColumn','"+dynaKeys+"','"+dynaValues+"','addManageTemplate3');";
									
									aElement.setAttribute(HtmlConstants.HREF, href);
									
									spanElement.addContent(aElement.detach());
									tdInnerElement.addContent(spanElement.detach());
									
									trInnerElement.addContent(tdInnerElement);
									//Ended code for deletion
									
									tableInnerEle.addContent(trInnerElement.detach());
									
									tdElement.addContent(tableInnerEle.detach());
									
									trElement.addContent(tdElement.detach());*/
								}
								
								divElement.addContent(ulElement.detach());
								
								//going to add attach rule icon for entire list
								//if(ctx.get("ISSKIPDELETECOLUMNTEMPLATEBUILDER") == null || !ctx.get("ISSKIPDELETECOLUMNTEMPLATEBUILDER").toString().equals("Y")){
									Element aDivElement = new Element(HtmlConstants.DIV);
									aDivElement.setAttribute(HtmlConstants.ID, "block_field_attachrule_row_link");
									aDivElement.setAttribute(HtmlConstants.CSS_CLASS, "icoAttachRule");
									Element aElement = new Element(HtmlConstants.LINK);
									
									String dynaKeys = "|attachTemplateBuilderRuleListName|attachTemplateBuilderRuleListIndex|isAttachRuleToTableListFound|";
									String dynaValues = "|"+DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLEFULLLISTNAME+"|"+i+"|Y|";
									String href = "javascript:manageajaxpageblocklink(document.forms[0],'attachRuleToTemplateBuilderList','"+dynaKeys+"','"+dynaValues+"','addManageTemplate3','popupAttachManageRule');";
									
									aElement.setAttribute(HtmlConstants.HREF, href);
									//aElement.addContent("Attach");
									aDivElement.addContent(aElement.detach());
									
									divElement.addContent(aDivElement.detach());
								//}
								//Ended
								
								//going to add delete icon for entire list
								if(ctx.get("ISSKIPDELETECOLUMNTEMPLATEBUILDER") == null || !ctx.get("ISSKIPDELETECOLUMNTEMPLATEBUILDER").toString().equals("Y")){
									aDivElement = new Element(HtmlConstants.DIV);
									aDivElement.setAttribute(HtmlConstants.ID, "block_field_delete_row_link");
									aDivElement.setAttribute(HtmlConstants.CSS_CLASS, "icoDelete");
									aElement = new Element(HtmlConstants.LINK);
									
									dynaKeys = "|deleteTemplateBuilderColumnListName|deleteTemplateBuilderColumnListIndex|";
									dynaValues = "|"+DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLEFULLLISTNAME+"|"+i+"|";
									href = "javascript:managesubmitform(document.forms[0],'deleteTemplateBuilderList','"+dynaKeys+"','"+dynaValues+"','addManageTemplate3');";
									
									aElement.setAttribute(HtmlConstants.HREF, href);
									
									aDivElement.addContent(aElement.detach());
									
									divElement.addContent(aDivElement.detach());
								}
								
								/*Element tdElement = new Element(HtmlConstants.TD);
								tdElement.setAttribute(HtmlConstants.CSS_CLASS, "icoDeleteMiniTD");
								Element spanElement = new Element(HtmlConstants.SPAN);
								spanElement.setAttribute(HtmlConstants.ID, "block_field_delete_row_link");
								spanElement.setAttribute(HtmlConstants.CSS_CLASS, "icoDelete");
								Element aElement = new Element(HtmlConstants.LINK);
								
								String dynaKeys = "|deleteTemplateBuilderColumnListName|deleteTemplateBuilderColumnListIndex|";
								String dynaValues = "|"+DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLEFULLLISTNAME+"|"+i+"|";
								String href = "javascript:managesubmitform(document.forms[0],'deleteTemplateBuilderList','"+dynaKeys+"','"+dynaValues+"','addManageTemplate3');";
								
								aElement.setAttribute(HtmlConstants.HREF, href);
								
								spanElement.addContent(aElement.detach());
								tdElement.addContent(spanElement.detach());
								
								trElement.addContent(tdElement.detach());*/
								//Ended code for deletion
								
								//tableElement.addContent(trElement);
								
								//blockElement.addContent(tableElement.detach());
								
								blockElement.addContent(divElement.detach());
							}
						}
					}
				}
			}
		}
	}
	
	//Method created to generate template builder page to show filtered temp list
	private void generateDynamicTemplateBuilderPageContentCreateTable(Element blockElement, Context ctx) throws Exception{
		String DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLELISTNAME = ctx.get("DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLELISTNAME").toString();
		
		//going to display filtered list
		if(ctx.get(DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLELISTNAME) != null && ctx.get(DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLELISTNAME) instanceof List){
			List list = (List)ctx.get(DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLELISTNAME);
			
			if(list != null && list.size() > 0){
				/*Element tableElement = new Element(HtmlConstants.TABLE);
				tableElement.setAttribute(HtmlConstants.ID, DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLELISTNAME);
				tableElement.setAttribute(HtmlConstants.STYLE, "margin-top:10px;");
				tableElement.setAttribute(HtmlConstants.WIDTH, "100%");
				tableElement.setAttribute(HtmlConstants.CELLSPACING, "0");
				tableElement.setAttribute(HtmlConstants.CELLPADDING, "0");
				
				Element trElement = new Element(HtmlConstants.TR);*/
				
				Element divElement = new Element(HtmlConstants.DIV);
				//divElement.setAttribute(HtmlConstants.CSS_CLASS, "templateBuilderDiv templateBuilderDecisionTable");
				
				//going to add filter heading for filtered list
				if(ctx.get("ISSKIPFILTERHEADERTEMPLATEBUILDER") == null || !ctx.get("ISSKIPFILTERHEADERTEMPLATEBUILDER").toString().equals("Y")){
					divElement.setAttribute(HtmlConstants.CSS_CLASS, "templateBuilderDiv templateBuilderDecisionTable");
					
					Element h3Element = new Element("h3");
					h3Element.addContent("Create Decision Table");
					divElement.addContent(h3Element.detach());
				}else{
					divElement.setAttribute(HtmlConstants.CSS_CLASS, "templateBuilderDiv");
				}
				
				Element ulElement = new Element("ul");
				ulElement.setAttribute(HtmlConstants.ID, DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLELISTNAME);
				ulElement.setAttribute(HtmlConstants.CSS_CLASS, "templateBuilderUL");
				
				for(int i=0; i<list.size(); i++){
					Map row = (Map)list.get(i);
					
					String selectfieldname = row.get("selectfieldname").toString();
					String selectfielddescription = row.get("selectfielddescription").toString();
					
					String headerDescription = selectfielddescription;
					if(selectfielddescription.indexOf(".") != -1)
						headerDescription = selectfielddescription.substring(selectfielddescription.indexOf(".")+1, selectfielddescription.length());
					
					Element liElement = new Element("li");
					liElement.setAttribute(HtmlConstants.CSS_CLASS, "templateBuilderLI");
					
					Element spanElement = new Element(HtmlConstants.SPAN);
					spanElement.addContent(headerDescription);
					liElement.addContent(spanElement.detach());
					
					//going to add attach rule icon for per column
					//if(ctx.get("ISSKIPDELETECOLUMNTEMPLATEBUILDER") == null || !ctx.get("ISSKIPDELETECOLUMNTEMPLATEBUILDER").toString().equals("Y")){
						spanElement = new Element(HtmlConstants.SPAN);
						spanElement.setAttribute(HtmlConstants.ID, "block_field_"+selectfieldname+"_attachrule_row_link");
						spanElement.setAttribute(HtmlConstants.CSS_CLASS, "icoAttachRuleMini");
						Element aElement = new Element(HtmlConstants.LINK);
						
						String dynaKeys = "|attachRuleToTemplateBuilderColumn|attachTemplateBuilderColumnRuleListName|inet_skip_validation|";
						String dynaValues = "|"+selectfieldname+"|"+DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLELISTNAME+"|Y|";
						String href = "javascript:manageajaxpageblocklink(document.forms[0],'attachRuleToTemplateBuilderColumn','"+dynaKeys+"','"+dynaValues+"','addManageTemplate3','popupAttachManageRule');";
						
						aElement.setAttribute(HtmlConstants.HREF, href);
						
						spanElement.addContent(aElement.detach());
						liElement.addContent(spanElement.detach());
					//}
					//Ended
					
					//going to add delete icon for entire list
					if(ctx.get("ISSKIPDELETECOLUMNTEMPLATEBUILDER") == null || !ctx.get("ISSKIPDELETECOLUMNTEMPLATEBUILDER").toString().equals("Y")){
						spanElement = new Element(HtmlConstants.SPAN);
						spanElement.setAttribute(HtmlConstants.ID, "block_field_"+selectfieldname+"_delete_row_link");
						spanElement.setAttribute(HtmlConstants.CSS_CLASS, "icoDeleteMini");
						aElement = new Element(HtmlConstants.LINK);
						
						dynaKeys = "|deleteTemplateBuilderColumn|deleteTemplateBuilderColumnListName|deleteTemplateBuilderColumnListIndex|inet_skip_validation|";
						dynaValues = "|"+selectfieldname+"|"+DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLELISTNAME+"|"+i+"|Y|";
						href = "javascript:managesubmitform(document.forms[0],'deleteTemplateBuilderColumn','"+dynaKeys+"','"+dynaValues+"','addManageTemplate3');";
						
						aElement.setAttribute(HtmlConstants.HREF, href);
						
						spanElement.addContent(aElement.detach());
						liElement.addContent(spanElement.detach());
					}
					//Ended
					
					ulElement.addContent(liElement.detach());
					
					/*Element tdElement = new Element(HtmlConstants.TD);
					tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
					
					Element tableInnerEle = new Element(HtmlConstants.TABLE);
					tableInnerEle.setAttribute(HtmlConstants.CELLSPACING, "0");
					tableInnerEle.setAttribute(HtmlConstants.CELLPADDING, "0");
					
					Element trInnerElement = new Element(HtmlConstants.TR);
					
					Element tdInnerElement = new Element(HtmlConstants.TD);
					tdInnerElement.setAttribute(HtmlConstants.CSS_CLASS, "icoDeleteMiniHeaderStyle");
					tdInnerElement.addContent(headerDescription);
					trInnerElement.addContent(tdInnerElement.detach());
					
					//going to add delete icon with each column
					tdInnerElement = new Element(HtmlConstants.TD);
					tdInnerElement.setAttribute(HtmlConstants.CSS_CLASS, "icoDeleteMiniTD");
					spanElement = new Element(HtmlConstants.SPAN);
					spanElement.setAttribute(HtmlConstants.ID, "block_field_"+selectfieldname+"_delete_row_link");
					spanElement.setAttribute(HtmlConstants.CSS_CLASS, "icoDeleteMini");
					aElement = new Element(HtmlConstants.LINK);
					
					dynaKeys = "|deleteTemplateBuilderColumn|deleteTemplateBuilderColumnListName|deleteTemplateBuilderColumnListIndex|";
					dynaValues = "|"+selectfieldname+"|"+DYNAMICPAGETEMPLATEBUILDERCONTENTCREATETABLELISTNAME+"|"+i+"|";
					href = "javascript:managesubmitform(document.forms[0],'deleteTemplateBuilderColumn','"+dynaKeys+"','"+dynaValues+"','addManageTemplate3');";
					
					aElement.setAttribute(HtmlConstants.HREF, href);
					
					spanElement.addContent(aElement.detach());
					tdInnerElement.addContent(spanElement.detach());
					
					trInnerElement.addContent(tdInnerElement);
					
					tableInnerEle.addContent(trInnerElement.detach());
					
					tdElement.addContent(tableInnerEle.detach());
					
					trElement.addContent(tdElement.detach());*/
				}
				
				//tableElement.addContent(trElement.detach());
				
				//blockElement.addContent(tableElement.detach());
				
				divElement.addContent(ulElement.detach());
				blockElement.addContent(divElement.detach());
			}
		}
	}
	
	private void appendItemsToHeadSection(Element headElement) throws Exception{
		if(headElement == null)
			return;
		
		//Adding for help sections
		Element scriptElement = new Element(HtmlConstants.SCRIPT);
		scriptElement.setAttribute(HtmlConstants.TYPE, "text/javascript");
		//scriptElement.setAttribute(HtmlConstants.SRC, "js/tooltip.js");
		headElement.addContent(scriptElement.detach());
		
		
		Element linkElement = new Element(HtmlConstants.CSS_LINK);
		//linkElement.setAttribute(HtmlConstants.HREF, "./css/tooltip.css");
		linkElement.setAttribute("rel", "stylesheet");
		linkElement.setAttribute(HtmlConstants.TYPE, "text/css");
		headElement.addContent(linkElement.detach());
		// Implement this for reload the all javascript and CSS; Clear the browser cache. Amit Jain - 26/07/2016
		parseHeadNode(headElement, headElement.getChildren());
		
	}
	
	//Method created to get filters data for template builder
	private void getDynamicTemplateBuilderFiltersData(Context ctx, String dynamicPageXML) throws Exception{
		if(dynamicPageXML == null)
			return;
	
		try{
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new StringReader(dynamicPageXML));
			Element root = doc.getRootElement();
			
			if(root.getContent() != null && root.getContent().size() > 0){
				for(int i=0; i<root.getContent().size(); i++){
					Object obj = root.getContent().get(i);
					if(obj != null && obj instanceof Element){
						if(((Element)obj).getName().equals("filters")){//getting <filters> node
							Element filtersElementNode = (Element)obj;
							
							if(filtersElementNode.getContent() != null && filtersElementNode.getContent().size() > 0){
								for(int j=0; j<filtersElementNode.getContent().size(); j++){
									Object obj1 = filtersElementNode.getContent().get(j);
									
									if(obj1 != null && obj1 instanceof Element && ((Element)obj1).getName().equals("filter")){//getting <filter> node
										Element filterElementNode = (Element)filtersElementNode.getContent().get(j);
										
										String name = filterElementNode.getAttributeValue(HtmlConstants.NAME);
										String metaobject = "templateBuilder";//filterElementNode.getAttributeValue("object");
										
										String commaSepratedValues = null;
										
										boolean isDropDownExists = false;
										try{
											MetaobjectImpl metaObjectImpl = MetaDataResources.getInstance(ctx).getMetaobject(metaobject);
											if(metaObjectImpl != null && metaObjectImpl.getFirstPropertyversion() != null){
												if(metaObjectImpl.getFirstPropertyversion().getProperty(name) != null){
													PropertyImpl propertyImpl = metaObjectImpl.getFirstPropertyversion().getProperty(name);
													if(propertyImpl != null && propertyImpl.getDropdownList() != null && propertyImpl.getDropdownList().size() > 0){
														isDropDownExists = true;
													}
												}
											}
										}catch (Exception e1) {
											// TODO: handle exception
										}
										
										if(isDropDownExists){
											for(int a=0; a<1000; a++){
												if(ctx.get(name+"_"+a) != null && !ctx.get(name+"_"+a).toString().equals(HtmlConstants.EMPTY)){
													Map map = new HashMap();
													
													String pair = ctx.get(name+"_"+a).toString();
													String value = pair;
													
													if(pair.indexOf("=") != -1)
														value = pair.substring(pair.indexOf("=")+1, pair.length());
													
													if(commaSepratedValues == null)
														commaSepratedValues = value;
													else
														commaSepratedValues = commaSepratedValues + "," + value;
												}
											}
										}else{
											commaSepratedValues = ctx.get(name) != null ? ctx.get(name).toString() : HtmlConstants.EMPTY;
										}
										
										ctx.put(name, commaSepratedValues);
									}
								}
							}
						}
					}
				}
			}
		}catch (Exception e) {
			logger.error("Unable to get dynamic template builder filters data due to error : " + e.getMessage());
		}
	}
	
	//Method created to parse popup graph
	private void parsePopupGraph(Element element, Context ctx, HttpServletRequest request, String pageName) throws Exception{
		if(element.getChildren() != null && element.getChildren().size() > 0){
			for(int i=0; i<element.getChildren().size(); i++){
				if(element.getChildren().get(i) instanceof Element){
					Element row = (Element)element.getChildren().get(i);
					
					if(row.getAttributeValue(HtmlConstants.ID) != null && row.getAttributeValue(HtmlConstants.ID).contains("_contextdata_")){
						ComponentImpl cImpl = ComponentResources.getInstance(ctx).getComponent(pageName);
						PagecomponentImpl pImpl = ProcessFlowResources.getInstance(ctx).getPagecomponent(pageName);
						
						new FreeFormGenerator(metadataResources, pImpl, cImpl).parseFreeform(ctx, row, row.getChildren(), (Map)ctx, (Map)ctx, false, null, request, pageName, false);
						return;
					}
					
					parsePopupGraph(row, ctx, request, pageName);
				}
			}
		}
	}
	public void addPageFieldsListToForm(Context ctx, Element element, String pageName) throws Exception{
		Element hiddenElement = new Element(HtmlConstants.INPUT);
		hiddenElement.setAttribute(HtmlConstants.TYPE, HtmlConstants.HIDDEN);
		hiddenElement.setAttribute(HtmlConstants.NAME, pageName + "_pageFieldsList");
		hiddenElement.setAttribute(HtmlConstants.ID, HtmlConstants.AJAX_FIELD+pageName + "_pageFieldsList");
		
		if(ctx.get(pageName+"_pageFieldsList") != null)
			hiddenElement.setAttribute(HtmlConstants.VALUE, ctx.get(pageName+"_pageFieldsList").toString());
		
		element.addContent(hiddenElement.detach());
	}
	// Added this method for reload the all javascript and CSS everytime; Clear the browser cache. Amit Jain - 26/07/2016
		private void parseHeadNode(Element element, List children) throws Exception{
			Element sibling = null;
			
			String js_src = null;
					
			for(int i=0; i<children.size(); i++){
				js_src = null;
				sibling = null;
				
				sibling = (Element)children.get(i);
			
				if(sibling.getName() != null && (HtmlConstants.SCRIPT.equalsIgnoreCase(sibling.getName()) || 
						HtmlConstants.CSS_LINK.equalsIgnoreCase(sibling.getName()))){
					
					if(HtmlConstants.SCRIPT.equalsIgnoreCase(sibling.getName()) && sibling.getAttributeValue(HtmlConstants.SRC) != null){				
						js_src = sibling.getAttributeValue(HtmlConstants.SRC);				
						js_src = js_src.contains("?") ? js_src + "&v1=" + System.currentTimeMillis() : js_src + "?v=" + System.currentTimeMillis();
						
						ParseUtil.addAttribute(sibling, HtmlConstants.SRC, js_src);				
					}
					
					if(HtmlConstants.CSS_LINK.equalsIgnoreCase(sibling.getName()) && sibling.getAttributeValue(HtmlConstants.HREF) != null){				
						js_src = sibling.getAttributeValue(HtmlConstants.HREF);				
						js_src = js_src.contains("?") ? js_src + "&v1=" + System.currentTimeMillis() : js_src + "?v=" + System.currentTimeMillis();
						
						ParseUtil.addAttribute(sibling, HtmlConstants.HREF, js_src);				
					}
					
					continue;
				}
				
				parseHeadNode(sibling, sibling.getChildren());
			}		
		}
}
