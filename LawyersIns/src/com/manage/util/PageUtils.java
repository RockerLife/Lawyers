package com.manage.util;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.w3c.tidy.Tidy;

import com.manage.framework.ProcessAction;
import com.manage.managebusinessrules.rules.RuleImpl;
import com.manage.managebusinessrules.rules.RulesResources;
import com.manage.managecomponent.components.ComponentImpl;
import com.manage.managecomponent.components.ComponentResources;
import com.manage.managecomponent.processflow.PagecomponentImpl;
import com.manage.managecomponent.processflow.ProcessFlowResources;
import com.manage.managecomponent.tabsconfiguration.TabsConfigurationResources;
import com.manage.managemetadata.security.SecurityResources;
import com.manage.managemetadata.util.exception.ValidationException;
import com.manage.managereporting.reports.ReportResources;
import com.manage.process.HTMLGenerator;
import com.util.CacheManager;
import com.util.Constants;
import com.util.Context;
import com.util.HTML2XML;
import com.util.HTMLUtils;
import com.util.HtmlConstants;
import com.util.IContext;
import com.util.IOUtils;
import com.util.InetLogger;
import com.util.ObjectCloner;
import com.util.SystemProperties;

public class PageUtils {
	private static InetLogger logger = InetLogger.getInetLogger(PageUtils.class);
	public static String PAGE_LIST_STACK = "page_list_stack";
    public static String BREAD_CRUMB_PAGE = "bread_crumb_page";
    public static String BREAD_CRUMB_DISPLAY_PAGE = "bread_crumb_display_page";
    public static String BREAD_CRUMB_PAGE_REQUEST = "bread_crumb_page_request";
    public static String BREAD_CRUMB_PAGE_DESC = "bread_crumb_page_desc";
    
	public void populateContext(HttpServletRequest request, Context ctx) throws Exception {
		//FormUtils.populatePageName(request, ctx);
		FormUtils.populateSessionData(request, ctx);

		if(ServletFileUpload.isMultipartContent(request)){
			FormUtils.populateMultipartFormData(request, ctx);
		}
		
		populateDynaKeysSafely(request, ctx);
		
		String event = (String) ctx.get(HtmlConstants.INET_METHOD);
		if(!"cancel".equals(event)){
			populateRequestDataSafely(request, ctx);
			// The legacy method reparses dynaKeys/dynaValues internally and can
			// index past the shorter value list. Reapply the guarded parser last.
			populateDynaKeysSafely(request, ctx);
			FormUtils.populateServerInfo(request, ctx);
			//FormUtils.populateMultipartFormData(request, ctx);
		}
		
		if(null!=ctx.get("inet_link_blockid")){
			PageInfo stack1 = (PageInfo)request.getSession().getAttribute("stack");
			Context  oldctx = stack1.getContext();
			String oldblockid = (String) oldctx.get("datalist");
			ctx.put("old"+ctx.get("inet_link_blockid"), oldctx.get(oldblockid));
	    }
		
		String projectid = (String) ctx.get(Constants.INET_PROJECT_ID);
		ctx.setProject(projectid);
		ctx.put(Constants.HTTPREQUEST, request);
		
		//Added code to populate context with Tabs_Conf_Map cache data -- by vikas 4/6/12
		if(CacheManager.get("Tabs_Conf_Map") != null && CacheManager.get("Tabs_Conf_Map") instanceof Map){
			Map map = (Map)CacheManager.get("Tabs_Conf_Map");
			Set keysSet = map.keySet();
			Iterator itr = keysSet.iterator();
			while(itr.hasNext()){
				String key = itr.next().toString();
				ctx.put(key, map.get(key));
			}
		}
		//Ended code
		
		//going to filter fields based on showRightClickPageId flag -- 17/8/2016
		if(ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEID) != null && ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEID) != null){
			String showRightClickPageId = ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEID) != null ? ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEID).toString() : HtmlConstants.EMPTY;
			String showRightClickPageFields = ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEFIELDS) != null ? ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEFIELDS).toString() : HtmlConstants.EMPTY;
			
			if(StringUtils.isBlank(showRightClickPageFields))
				return;
			
			StringTokenizer tokens = new StringTokenizer(showRightClickPageFields, ",");
			while(tokens.hasMoreTokens()){
				String token = tokens.nextToken();
				
				ctx.put(token, ctx.get(token+"_"+ctx.get(showRightClickPageId)));
				ctx.remove(token+"_"+ctx.get(showRightClickPageId));
			}
		}
		//Ended code 
	}

	private void populateDynaKeysSafely(HttpServletRequest request, Context ctx) throws Exception {
		String keys = getDynaParameter(request, Constants.DYNAKEYS);
		String values = getDynaParameter(request, Constants.DYNAVALUES);
		String[] keyParts = splitDynaParameter(keys);
		String[] valueParts = splitDynaParameter(values);

		if (keys != null && values != null && keyParts.length != valueParts.length) {
			populateDynaKeysFallback(ctx, keyParts, valueParts, null);
			return;
		}

		try {
			FormUtils.populateDynaKeys(request, ctx);
		} catch (ArrayIndexOutOfBoundsException e) {
			if (keys == null || values == null)
				throw e;
			populateDynaKeysFallback(ctx, keyParts, valueParts, e);
		}
	}

	private void populateRequestDataSafely(HttpServletRequest request, Context ctx) throws Exception {
		Enumeration parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String name = (String) parameterNames.nextElement();
			String[] values = request.getParameterValues(name);
			if (values != null && values.length > 0)
				ctx.put(name, resolveRequestParameterValue(values[0]));
		}

		Enumeration attributeNames = request.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String name = (String) attributeNames.nextElement();
			Object value = request.getAttribute(name);
			if (value != null)
				ctx.put(name, resolveRequestParameterValue(value.toString()));
		}
	}

	private String resolveRequestParameterValue(String value) {
		if (value == null || value.length() == 0)
			return value;
		return value.replace("$AND$", "&").replace("$PER$", "%").replace("$APH$", "'")
				.replace("#AMP#", "&").replace("$AMP$", "&").replace("$HYPHEN$", "|");
	}

	private String getDynaParameter(HttpServletRequest request, String name) {
		String value = request.getParameter(name);
		if (StringUtils.isBlank(value) && request.getAttribute(name) != null)
			value = request.getAttribute(name).toString();
		return value;
	}

	private String[] splitDynaParameter(String value) {
		if (StringUtils.isBlank(value))
			return new String[0];
		int start = value.startsWith("|") ? 1 : 0;
		int end = value.endsWith("|") ? value.length() - 1 : value.length();
		if (end <= start)
			return new String[0];
		return value.substring(start, end).split("\\|", -1);
	}

	private void populateDynaKeysFallback(Context ctx, String[] keys, String[] values, Throwable cause) {
		String message = "Mismatched dynaKeys/dynaValues; keys=" + keys.length + ", values=" + values.length;
		if (cause == null)
			logger.error(message);
		else
			logger.error(message + "; parsed safely after legacy FormUtils failure", cause);

		for (int i = 0; i < keys.length; i++) {
			String key = keys[i] == null ? HtmlConstants.EMPTY : keys[i].trim();
			if (key.length() == 0)
				continue;
			String value = i < values.length ? values[i] : HtmlConstants.EMPTY;
			ctx.put(key, resolveDynaValue(value == null ? HtmlConstants.EMPTY : value.trim()));
		}
	}

	private String resolveDynaValue(String value) {
		if ("$".equals(value))
			return HtmlConstants.EMPTY;
		return value.replace("_HASH_", "#").replace("$AND$", "&").replace("$PER$", "%")
				.replace("$APH$", "'").replace("#AMP#", "&").replace("$AMP$", "&")
				.replace("$HYPHEN$", "|");
	}

	public String validateAjaxComponent(Context ctx, HttpServletRequest request) throws Exception{
		ProcessAction processAction = new ProcessAction();
		processAction.validate(ctx, (String) ctx.get(Constants.INET_PAGE));

		List errorlist = null;

		if(ctx.get(Constants.INET_ERRORS_LIST)!=null)
			errorlist = (List)ctx.get(Constants.INET_ERRORS_LIST);

		if(errorlist == null){
			if(ctx.get(Constants.IS_RULEVALIDATE) != null && "Y".equals((String)ctx.get(Constants.IS_RULEVALIDATE))){
				ValidationException e = new ValidationException("Rule is valid");
				errorlist = new ArrayList();
				errorlist.add(e);
                ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
			}else{
				return null;
			}
		}

		/*table = new Element(HtmlConstants.TABLE);
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
 
		return HTMLUtils.beautifyHTML(table);*/

		StringBuffer errorHTML = new StringBuffer();
		for(int i=0; i<errorlist.size(); i++){
			Object validationObj = errorlist.get(i);
			ValidationException  exception = (ValidationException) validationObj;
			
			//going to filter fields based on showRightClickPageId flag -- 25/8/2016 vikas
			if(ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEID) != null && ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEID) != null){
				String showRightClickPageId = ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEID) != null ? ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEID).toString() : HtmlConstants.EMPTY;
				String showRightClickPageFields = ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEFIELDS) != null ? ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEFIELDS).toString() : HtmlConstants.EMPTY;
				
				boolean isFieldFound = false;
				if(ctx.get(showRightClickPageId) != null){
					StringTokenizer tokens = new StringTokenizer(showRightClickPageFields, ",");
					while(tokens.hasMoreTokens()){
						String token = tokens.nextToken();
						
						token = token.replace(HtmlConstants.AJAX_FIELD, HtmlConstants.EMPTY);
						
						if(exception.getField().equals(token)){
							errorHTML.append(exception.getField()+"_"+ctx.get(showRightClickPageId).toString() +":" + exception.getMessage() +"|");
							
							isFieldFound = true;
							break;
						}
					}
				}
				
				if(!isFieldFound)
					errorHTML.append(exception.getField() + ":" + exception.getMessage() +"|"); 
			}else //Ended code
				errorHTML.append(exception.getField() + ":" + exception.getMessage() +"|"); 
		}

		return errorHTML.toString();

	}

	public String populateNextComponentpage(Context ctx, HttpServletRequest request) throws Exception {
		String next_page="";
//		new MultirowsaveListHandler().processRequest(ctx, request, stack1);
		ProcessAction processAction = new ProcessAction();
		
		if("sorting".equals(ctx.get(HtmlConstants.INET_METHOD))|| "pagination".equals(ctx.get(HtmlConstants.INET_METHOD))){
			next_page = processAction.processRoute(ctx,(String) ctx.get(Constants.INET_PAGE));
	        
			ctx.put(HtmlConstants.NEXT_PAGE_COMPONENT, ctx.get(Constants.INET_PAGE));
		}else if(null!=ctx.get("popuppage")||"popuppage".equals(ctx.get("popuppage"))){
			 next_page = processAction.processRoute(ctx,(String) ctx.get(Constants.INET_PAGE));
			 
			 ctx.put(HtmlConstants.NEXT_PAGE_COMPONENT, ctx.get(Constants.INET_PAGE));
		}else	
			 next_page = processAction.execute(ctx);

		return next_page;
	}

	public String populateNextComponentpage(Context ctx, HttpServletRequest request,PageInfo stack1) throws Exception
	{
		String next_page="";
//		new MultirowsaveListHandler().processRequest(ctx, request, stack1);
		ProcessAction processAction = new ProcessAction();
		if("sorting".equals(ctx.get(HtmlConstants.INET_METHOD))|| "pagination".equals(ctx.get(HtmlConstants.INET_METHOD)))
		 {
			 populateStackValues(   ctx,  stack1,  request);
			 next_page = processAction.processRoute(ctx,(String) ctx.get(Constants.INET_PAGE));
		 }
		 else if(null!=ctx.get("popuppage")||"popuppage".equals(ctx.get("popuppage")) )
		 {
			 populateStackValues(   ctx,  stack1,  request);
			 next_page = processAction.processRoute(ctx,(String) ctx.get(Constants.INET_PAGE));
		}
		else
			 next_page = processAction.execute(ctx);


		return next_page;
	}

	public Map convertHTMLToXMl(PagecomponentImpl pageCmpImpl, Context ctx) throws Exception{
		//PagecomponentImpl pageCmpImpl =  ProcessFlowResources.getInstance(ctx).getPagecomponent(next_page);
		HTML2XML htm2xml = new HTML2XML();
		htm2xml.setHtmlPath(SystemProperties.getInstance().getString("html.basedir"));
		htm2xml.convert(SystemProperties.getInstance().getString("html.basedir")+pageCmpImpl.getPath(),pageCmpImpl.getName(), ctx);
		String html = IOUtils.readFile(SystemProperties.getInstance().getString("html.basedir")+"temp.xml");

		Map map = new HashMap();
		map.put("DOCType", getDocType(html));
		map.put("XMLDocument", getDocument(html, pageCmpImpl.getName()));

       return map;
	}

	public void populateSession(HttpServletRequest request, Context ctx){
		IContext sessionVars = ctx.subset(Constants.INET_SESSION_NAMESPACE);

		if(sessionVars==null)
			return;

		if(sessionVars.get(Constants.INET_PROJECT_ID)==null && ctx.get(Constants.INET_PROJECT_ID)!=null)
			sessionVars.put(Constants.INET_PROJECT_ID, "Y");

		Iterator keyIt = sessionVars.keySet().iterator();
		HttpSession session = request.getSession();

		while(keyIt.hasNext()){
			String key = (String) keyIt.next();
			Object sessionUpdate = sessionVars.get(key);

			if(sessionUpdate==null)
				continue;

			if(Constants.NO.equals(sessionUpdate))
				session.removeAttribute(key);
			else{
				if(ctx.containsKey(key)){
					Object val = ctx.get(key);
					session.setAttribute(key, val);
					
					/*if(val!=null && val instanceof Integer)
					val = val.toString();
					if(val!=null)
					session.setAttribute(key, val);*/
				}
			}
		}
		
		//update the DocumentSessionMap
		Map documentSessionMap = new HashMap();
		Enumeration sessionEnum = session.getAttributeNames();
		while(sessionEnum.hasMoreElements()){
			Object obj = sessionEnum.nextElement();
			if(obj instanceof String){
				String key = obj.toString();
				
				documentSessionMap.put(key, session.getAttribute(key));
			}
		}
		
		ctx.put(HtmlConstants.DocumentSessionMap, documentSessionMap);
	}


	public Document parseHtmlDocument(Context ctx, String next_page,String ajaxpage,HttpServletRequest request) throws Exception
	{
		String isLoadHTMLS = SystemProperties.getInstance().getString("appl."+ctx.getProject()+".LoadHTMLS");
        Map nextPageDoc = getPageDocumentMap(ctx, next_page, isLoadHTMLS != null && "Y".equalsIgnoreCase(isLoadHTMLS));

		Document document1 =nextPageDoc.get("XMLDocument")==null?null:(Document)nextPageDoc.get("XMLDocument");
		HTMLGenerator htmlGenerator = new HTMLGenerator(ctx.get(Constants.INET_PROJECT_ID).toString(), next_page);
		Document document = (Document)ObjectCloner.deepCopy(document1);
		if(document == null)
			throw new IllegalStateException("Unable to clone cached HTML page - " + next_page);
		document = htmlGenerator.parseDocument(ctx, document, next_page,request);

		return document;
	}

	public String parseHtmlDocument(Context ctx, String next_page, HttpServletRequest request) throws Exception
	{
		String isLoadHTMLS = SystemProperties.getInstance().getString("appl."+ctx.getProject()+".LoadHTMLS");
        Map nextPageDoc = getPageDocumentMap(ctx, next_page, isLoadHTMLS != null && "Y".equalsIgnoreCase(isLoadHTMLS));

		String nextPagDocType = nextPageDoc.get("DOCType")==null?"":nextPageDoc.get("DOCType").toString();
		Document document1 =nextPageDoc.get("XMLDocument")==null?null:(Document)nextPageDoc.get("XMLDocument");
		HTMLGenerator htmlGenerator = new HTMLGenerator(ctx.get(Constants.INET_PROJECT_ID).toString(), next_page);
		
		Document document = (Document)ObjectCloner.deepCopy(document1);
		if(document == null)
			throw new IllegalStateException("Unable to clone cached HTML page - " + next_page);

		//going to revise accordian data list based on showAccordianPageList variable in context
		checkForShowAccordianPageList(ctx);
		
		document = htmlGenerator.parseDocument(ctx, document, next_page,request);
		
		//going to check for default open accordian page based on showAccordianPage variable in context
		checkForShowAccordianPage(ctx, request, document);
		
		//going to add pageFieldsList to form
		try{
			Element formElement = document.getRootElement().getChild(HtmlConstants.BODY).getChild(HtmlConstants.FORM);
			htmlGenerator.addPageFieldsListToForm(ctx, formElement, next_page);
		}catch(Exception e){
			logger.error("Unable to add pageFieldsList to page : " + next_page + " due to error : " + e.getMessage());
		}
		
		String htmlDoc = HTMLUtils.beautifyHTML(document);
		htmlDoc = htmlDoc.replaceAll(HtmlConstants.SPACE, "&nbsp;");
		String htmlcontent = appendDOCTYPE(htmlDoc,nextPagDocType);
		
		//going to get list of labels/security/tabsconfig ids exists on stored html page
		htmlcontent = checkForLabelSecurityTabsConfigIdOnPage(ctx, document, htmlcontent, next_page);
		
		document = null;
		return htmlcontent;
	}

	public Map getHtmlDocMap(Context ctx, String next_page) throws Exception{
	    PagecomponentImpl pcImpl = ProcessFlowResources.getInstance(ctx).getPagecomponent(next_page);
        Map map = new HashMap();
        if (pcImpl == null) {
            logger.error("Page component not found - " + next_page);
            return map;
        }
        try {
            map = new PageUtils().convertHTMLToXMl(pcImpl, ctx);
        } catch (Exception e) {
            logger.error("Error in reading Html - " + pcImpl.getName());
            logger.error("Unexpected error", e);
            return new HashMap();
        }
        return map;
	}

	private Map getPageDocumentMap(Context ctx, String next_page, boolean loadFromCache) throws Exception {
		Map pageDoc = null;
		if (loadFromCache) {
			Object cached = CacheManager.get(next_page);
			if (cached instanceof Map)
				pageDoc = (Map) cached;
		}

		if (pageDoc == null || !(pageDoc.get("XMLDocument") instanceof Document)) {
			if (loadFromCache)
				logger.error("Page not Found in the Cache for Page - " + next_page + "; loading it from the page source");
			pageDoc = getHtmlDocMap(ctx, next_page);
			if (loadFromCache && pageDoc != null && pageDoc.get("XMLDocument") instanceof Document)
				CacheManager.put(next_page, pageDoc);
		}

		if (pageDoc == null || !(pageDoc.get("XMLDocument") instanceof Document))
			throw new IllegalStateException("Unable to load HTML page - " + next_page);
		return pageDoc;
	}

	public String getDocType(String htmlContent){
		if(htmlContent.contains("<!DOCTYPE"))
			{
				int docCloseIndex = htmlContent.indexOf(">");
				return htmlContent.substring(0, docCloseIndex+1);
			}
		return "";
	}

	public String appendDOCTYPE(String htmlDoc,String oldDocType) {

//		if(oringalhtml.contains("<!DOCTYPE"))
//		{
//			int docIndex = oringalhtml.indexOf("<!DOCTYPE");
//			int docCloseIndex = oringalhtml.indexOf(">");
//			oringalhtml = oringalhtml.substring(0, docCloseIndex+1);
			htmlDoc =oldDocType+htmlDoc;
//		}
		return htmlDoc;
	}

	public void writeResponse(HttpServletResponse response,String htmlDoc, String contentType)
	{
		//response.setContentType("text/html");
		if(contentType == null || contentType.equals(""))
			response.setContentType("text/html");
		else
			response.setContentType(contentType);

		PrintWriter out = null;
		if(htmlDoc == null)
			return;

		try
		{

			if(!response.isCommitted()){
				out = response.getWriter();

				out.println(htmlDoc);
				out.flush();
				out.close();
			}

		}
		catch (IOException e)
		{
			logger.error("Unable to parse the HTML document", e);
		}
		finally{
			if(out != null)
				out.close();
		}
	}

	public Element getExistingElement(Element root, String id) {
        String elementId = null;
        Element extElement = null;
        List childern = root.getChildren();

        if (childern != null)
        {
            for (int i = 0; i < childern.size(); i++)
            {
                Element child = (Element) childern.get(i);
                Attribute elementsAttr = child.getAttribute("id");

                if (elementsAttr != null)
                {
                    elementId = elementsAttr.getValue();
                    if (elementId.contains (id))
                        return child;
                }

                extElement = getExistingElement(child, id);

                if (extElement != null)
                    return extElement;
            }
        }
        
        return null;
    }

	public String getElementFromDoc(Document document, Context ctx){
		 String htmlDoc = "";
		 Element root = null;
		 
		 if(document != null)
			 root = document.getRootElement();
		 
         String replayid = null;

         if(HtmlConstants.SORTING.equals(ctx.get(HtmlConstants.INET_METHOD)) ||"pagination".equals(ctx.get(HtmlConstants.INET_METHOD)))
			 replayid = ctx.get(HtmlConstants.DATA_DISPLAY_ID).toString();
         else if(null != ctx.get("popuppage") || "popuppage".equals(ctx.get("popuppage"))){
        	 replayid = ctx.get(HtmlConstants.DATA_DISPLAY_ID).toString();
         }else if(ctx.get(HtmlConstants.DATA_DISPLAY_ID) != null && !"".equals(ctx.get(HtmlConstants.DATA_DISPLAY_ID))){
        	 if(ctx.get(HtmlConstants.DATA_DISPLAY_ID).toString().contains(HtmlConstants.DIV_AJAX_POSTION))
			       	replayid = ctx.get(Constants.INET_PAGE).toString();
		 		else
		 			replayid = ctx.get(HtmlConstants.DATA_DISPLAY_ID).toString();
		 }else
			 replayid = ctx.get(Constants.INET_PAGE).toString();

         //Added code for Ajax suggestion
         //containerid on html must be start with (ajaxxml + fieldname which will fetch from database + ui fieldname in which value will be show)
         if(replayid != null && replayid.startsWith("ajaxxml")){
         	String xml = HtmlConstants.EMPTY;
         	
         	if(ctx.get(replayid) != null && ctx.get(replayid) instanceof List){
         		try{
	         		List list = (List)ctx.get(replayid);
	         		Element xmlroot = new Element("response");
	         		String field = replayid.substring(7, replayid.indexOf("_"));
	         		//String field = replayid.substring(7, replayid.lastIndexOf("_"));
	         		//field = field.substring(0, field.length()-3);
	     			for(int i=0; i<list.size(); i++){
	     				Element row = new Element(replayid);
	     				Map rowMap = (Map)list.get(i);
	     				row.setText(rowMap.get(field).toString());
	     				xmlroot.addContent(row);
	     			}
	     			
	     			xml = new XMLOutputter(Format.getPrettyFormat()).outputString(xmlroot);
         		}catch (Exception e) {
				logger.error("Unable to get page data", e);
				}
     		}
         	
         	return xml;
         }/*else{
         	logger.debug(replayid + " not started with xml in "+ctx.get(Constants.INET_PAGE).toString()+".html");
         }*/
         //Ended code for Ajax suggestion

		// from root element getting out the root table
		Element element = getExistingElement(root, replayid);
		//System.out.println(new XMLOutputter(Format.getPrettyFormat()).outputString(element));
        if(element == null)
        	logger.debug( replayid+" div id is not present in "+ctx.get(Constants.INET_PAGE).toString()+".html");

        if(element != null){
	        List eleList = element.getChildren();
			if(eleList != null){
				for(int i=0; i<eleList.size(); i++){
					 htmlDoc = htmlDoc + HTMLUtils.beautifyHTML((Element)eleList.get(i));
				}
				
				htmlDoc = htmlDoc.replaceAll(HtmlConstants.SPACE, "&nbsp;");
			}
        }
        
		return htmlDoc;
	}

	public void populateAjaxpage(HttpServletRequest request, Context ctx)
	{
		String value =request.getParameter(HtmlConstants.AJAX_SUBMITFORM);

		if(value.contains("."))
			value = value.substring(0,value.indexOf("."));

		ctx.put((Constants.INET_PAGE), value);
	}

	public void populateStackValues( Context ctx,PageInfo stack1,HttpServletRequest request) throws Exception
	{
		Object inetpage  =    ctx.get(Constants.INET_PAGE);
		if(null!=stack1)
		{
			//ctx.putAll( stack1.getContext());
			populateDynaKeysSafely(request, ctx);
			FormUtils.populatePagination(request, ctx);
			ctx.put(Constants.INET_PAGE, inetpage);
		}
	}

   public Document getDocument(String html, String pageName){

		Document document = null;
		StringReader reader = null;
		SAXBuilder builder = new SAXBuilder();
		String sourceHtml = html;
		try {
			html = cleanContent(html);
			reader = new StringReader(html);
			document = builder.build(reader);
			//reader.close();
		}
		catch(Exception e){
			logger.error("Strict XML parsing failed for " + pageName + "; using tolerant HTML parsing");
			try {
				Tidy tidy = new Tidy();
				tidy.setQuiet(true);
				tidy.setShowWarnings(false);
				tidy.setXmlOut(false);
				org.w3c.dom.Document dom = tidy.parseDOM(
						new ByteArrayInputStream(sourceHtml.getBytes("UTF-8")),
						new ByteArrayOutputStream());
				if (dom != null && dom.getDoctype() != null)
					dom.removeChild(dom.getDoctype());
				document = dom == null ? null : new DOMBuilder().build(dom);
			} catch(Exception fallbackException) {
				logger.error("Unable to parse HTML " + pageName, fallbackException);
			}
		}
		finally{
			if(reader != null)
				reader.close();
		}
		return document;
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

  //Created to handle document generation like pdf/excel
  public void populateDocumentComponentPage(Context ctx) throws Exception{
	  ProcessAction processAction = new ProcessAction();
	  processAction.executeDocument(ctx);
  }

  	public void setPageInStack(Context breadCrumbCtx, HttpServletRequest request,String pageName,String pageContent, Context currentCtx){
  		try{
  			if(SystemProperties.getInstance().getProperty("appl."+currentCtx.getProject()+".isbreadcrumb.required") != null && 
  					SystemProperties.getInstance().getProperty("appl."+currentCtx.getProject()+".isbreadcrumb.required").toString().equals("N")){
  				return;
  			}
  		}catch (Exception e) {
			// TODO: handle exception
		}
  		
  		if(StringUtils.isBlank(pageName))
  			return;
  		
  		if(pageName.equals("sessionList"))
  		  return;
  		
  		//going to check whether page needs to be added in breadcrumb or not
  		if(currentCtx.get(pageName+"_breadcrumb") != null && currentCtx.get(pageName+"_breadcrumb").toString().equals("N"))
  			return;
  		
  		List<Map<Object,Object>> stack = null;
  
  		if(breadCrumbCtx.get(PAGE_LIST_STACK) != null){
  			stack = (ArrayList<Map<Object, Object>>)breadCrumbCtx.get(PAGE_LIST_STACK);
  		} else {
  			stack = new ArrayList<Map<Object, Object>>();
  		}
      
  		String methodName = null;
      
  		if(request.getParameter("inet_method") != null)
  			methodName = request.getParameter("inet_method").toString();
        
  		//checking for removal of breadcrumb pages from Context
  		if(breadCrumbCtx.get(HtmlConstants.REMOVEBREADCRUMBPAGES) != null && 
  				!breadCrumbCtx.get(HtmlConstants.REMOVEBREADCRUMBPAGES).toString().equals(HtmlConstants.EMPTY)){
  			StringTokenizer tokens = new StringTokenizer(breadCrumbCtx.get(HtmlConstants.REMOVEBREADCRUMBPAGES).toString(), ",");
  			while(tokens.hasMoreTokens()){
  				String token = tokens.nextToken().trim();
  				
  				int indexOfExistPage = checkExistanceOfPageInStack(stack, token, methodName);
  	  			if(indexOfExistPage > 0)
  	  				stack = removeExistancePageFromStack(stack, indexOfExistPage-1);
  			}
  		}else {
  			int indexOfExistPage = checkExistanceOfPageInStack(stack, pageName, methodName);
  			if(indexOfExistPage > 0)
  				stack = removeExistancePageFromStack(stack, indexOfExistPage-1);
  		}
  		
  		Map<Object,Object> page = new HashMap<Object, Object>();
  		String pageDesc = null;
      
  		if(pageName.equalsIgnoreCase("managereporttemplate") || pageName.equals("managereportdatatemplate")){
  			try{
  				pageDesc = (ReportResources.getInstance(currentCtx).getReportDef(currentCtx.get("reportID").toString())).getDescription();
  			}catch (Exception ex) {
  				logger.error(ex.getMessage());
			}
  		}else{
		    try {
		        pageDesc = ComponentResources.getInstance(currentCtx).getComponent(pageName).getStackdisplayname();
		    } catch (Exception ex) {
		    	logger.error(ex.getMessage());
		    }
  		}
      
        //if stackdisplayname have eval - rule
        if(pageDesc != null && pageDesc.contains("eval:")){ 
        	String eval = pageDesc.substring(pageDesc.indexOf(":")+1, pageDesc.length());	
        	RuleImpl ruleImpl = null;
        	Object obj = null;
        	try {
        		ruleImpl = RulesResources.getInstance(currentCtx).findRule(eval);
        		if(ruleImpl != null)
        			obj = ruleImpl.execute(currentCtx, null);
        	}catch (Exception e) {
        		logger.error("Unexpected error", e);
        	}
			
        	if(obj != null)
        		pageDesc = obj.toString();
        	else
        		pageDesc = null;
        }
      
        //if statckdisplayname have label conf id
        if(!StringUtils.isBlank(pageDesc)){
        	StringBuffer newPageDesc = new StringBuffer(HtmlConstants.EMPTY);
        	StringTokenizer tokens = new StringTokenizer(pageDesc, " ");
        	while(tokens.hasMoreTokens()){
        		String token = tokens.nextToken();
        		Map<String,String> map = CacheManager.get("Labels_Conf") == null ? null : (HashMap)CacheManager.get("Labels_Conf");
        		if(map != null && map.containsKey(token))
        			token = map.get(token);
			  
        		if(newPageDesc.length() == 0)
        			newPageDesc.append(token);
        		else
        			newPageDesc.append(" " + token);	  
        	}
    	  
        	pageDesc = newPageDesc.toString();
        }
      
        //if statckdisplayname have value stored in context
        if(pageDesc != null && pageDesc.endsWith("_context")){
        	String labelConfId = pageDesc.substring(0, pageDesc.lastIndexOf("_context"));
        	if(!StringUtils.isBlank(labelConfId)){
        		pageDesc = currentCtx.get(labelConfId) != null ? currentCtx.get(labelConfId).toString() : labelConfId;
        	}
        }   
      
        if(pageDesc != null && !pageDesc.equals(""))
        	pageDesc = pageDesc.replaceAll("&amp;", "&");
      
        page.put(BREAD_CRUMB_PAGE, pageName);
        page.put(BREAD_CRUMB_PAGE_DESC, pageDesc);
        page.put("IS_CURRENT_PAGE", "Y");
        
        //going to bypass breadcrumb for reporting framework pages
        if(pageName != null && (pageName.equals("managereporttemplate") || pageName.equals("managereportdatatemplate"))){
        	page.put("IS_REPORTING_PAGE", "Y");
        	page.put(BREAD_CRUMB_DISPLAY_PAGE, (pageDesc != null && !"".equals(pageDesc.trim())) ? pageDesc : pageName);
        }else
        	page.put(BREAD_CRUMB_DISPLAY_PAGE, (pageDesc != null && !"".equals(pageDesc.trim())) ? pageDesc : pageName);
        
        page.put("bread_crumb_page_content", pageContent);
        
        /*Context newContext = new Context();
      	try {
        	populateContext(request, newContext);
      	} catch (Exception e) {
          	// TODO Auto-generated catch block
    		e.printStackTrace();
	  	}*/
      
        Context storedBreadCrumbCtx = createBreadCrumbSnapshot(breadCrumbCtx);

        //Added code to put session attributes in DocumentSessionMap - 12/10/2015 vikas
        if(request.getSession().getAttributeNames() != null){
			Map documentSessionMap = new HashMap();
			
			Enumeration<String> enm = request.getSession().getAttributeNames();
			while(enm.hasMoreElements()){
				String attr = enm.nextElement();
				if(!attr.equals(PAGE_LIST_STACK) && !attr.equals(HtmlConstants.REQUEST_AUTH_TOKEN))
					documentSessionMap.put(attr, request.getSession().getAttribute(attr));
			}
			
			storedBreadCrumbCtx.put(HtmlConstants.DocumentSessionMap, documentSessionMap);
		}
        //Ended code
        
        page.put(BREAD_CRUMB_PAGE_REQUEST, storedBreadCrumbCtx);
        stack.add(page);
        breadCrumbCtx.put(PAGE_LIST_STACK, stack);
        request.getSession().removeAttribute(PAGE_LIST_STACK);
        request.getSession().setAttribute(PAGE_LIST_STACK, stack);
  	}

	static Context createBreadCrumbSnapshot(Context breadCrumbCtx){
		Context storedBreadCrumbCtx = (Context)breadCrumbCtx.clone();
		storedBreadCrumbCtx.remove(Constants.HTTPREQUEST);
		storedBreadCrumbCtx.remove(PAGE_LIST_STACK);
		return storedBreadCrumbCtx;
	}
  
  public void setPageInStackWithContent(Context ctx,HttpServletRequest request,String pageName,String pageContent){
	  try{
		if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".isbreadcrumb.required") != null && 
				SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".isbreadcrumb.required").toString().equals("N")){
			return;
		}
	  }catch (Exception e) {
		// TODO: handle exception
	  }
		
	  if(pageName.equals("sessionList"))
		  return;
	  
	  //going to check whether page needs to be added in breadcrumb or not
	  if(ctx.get(pageName+"_breadcrumb") != null && ctx.get(pageName+"_breadcrumb").toString().equals("N"))
		  return;
		
      List<Map<Object,Object>> stack = null;
      if(ctx.get(PAGE_LIST_STACK) != null){
          stack = (ArrayList<Map<Object, Object>>)ctx.get(PAGE_LIST_STACK);
      } else {
          stack = new ArrayList<Map<Object, Object>>();
      }
      
      
      
      if(stack.isEmpty())
    	  return;

      

      Map<Object,Object> page = new HashMap<Object, Object>();
      page = stack.remove(stack.size()-1);
     
      page.put("bread_crumb_page_content", pageContent);
      
      
      stack.add(page);
      ctx.put(PAGE_LIST_STACK, stack);
      request.getSession().setAttribute(PAGE_LIST_STACK, stack);
  }
  
  public int checkExistanceOfPageInStack(List<Map<Object,Object>> stack,String pageName,String methodName){
      int i = 1;
      boolean flag = false;
      if(pageName!= null)
          for(Map<Object,Object> map : stack){
          	map.put("IS_CURRENT_PAGE", "N");
              if(map.get(BREAD_CRUMB_PAGE).toString().equalsIgnoreCase(pageName)){
              	map.put("IS_CURRENT_PAGE", "Y");
              	flag = true;
                  break;
              }
              i++;
          }
      if(flag){
      	if(methodName != null && "admin".equalsIgnoreCase(methodName))
      		return 2;
      	return i;
      }
      	
      if(methodName != null && "admin".equalsIgnoreCase(methodName))
      	return 2;
      return 0;

  }


  public List<Map<Object,Object>> removeExistancePageFromStack(List<Map<Object,Object>> stack,int index){
      List<Map<Object,Object>> newStack = new ArrayList<Map<Object, Object>>();
      for(int i=0;i<index;i++){
          
          newStack.add(stack.get(i));
      }
      return newStack;
  }

    public Context getOldRequest(HttpServletRequest req){
		List<Map<Object,Object>> stack = null;
	    Context newCtx = new Context();
	    try {
	    	FormUtils.populateMultipartDynaKeysForBreadCrumb(req, newCtx);
			populateDynaKeysSafely(req, newCtx);
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
		
		String pageName = newCtx.get(BREAD_CRUMB_PAGE) != null?newCtx.get(BREAD_CRUMB_PAGE).toString():null;
	    if(req.getSession().getAttribute(PAGE_LIST_STACK) != null)
	    	stack = (ArrayList<Map<Object, Object>>)req.getSession().getAttribute(PAGE_LIST_STACK);
	    else
	        stack = new ArrayList<Map<Object, Object>>();
	   
	    int indexOfExistPage = checkExistanceOfPageInStack(stack,pageName,null);
	    if(indexOfExistPage == 0)
	    	return null;
	
	    stack = removeExistancePageFromStack(stack,indexOfExistPage);
	     
	    Map<Object,Object> page = new HashMap<Object, Object>();
	
	    page = stack.get(indexOfExistPage-1);
	      
	    if(page == null)
	    	return null;
	      
	    req.getSession().removeAttribute(PAGE_LIST_STACK);
	    req.getSession().setAttribute(PAGE_LIST_STACK, stack);
	    
	    newCtx = (Context)page.get(BREAD_CRUMB_PAGE_REQUEST);
	    newCtx.put(Constants.HTTPREQUEST, req);
	    newCtx.put(HtmlConstants.ISMETAOBJECTBOEXECUTED, null);
	    
	    //populating breadCrumb session data from old context stored session map - 12/10/2015 by vikas
	    if(newCtx.get(HtmlConstants.DocumentSessionMap) != null && newCtx.get(HtmlConstants.DocumentSessionMap) instanceof Map){
	    	Map documentSessionMap = (Map)newCtx.get(HtmlConstants.DocumentSessionMap);
	    	
    		Set keySet = documentSessionMap.keySet();
    		Iterator<String> itr = keySet.iterator();
    		while(itr.hasNext()){
    			String key = itr.next();
    			
    			if(!key.equals(HtmlConstants.PAGE_LIST_STACK) && !key.equals(HtmlConstants.REQUEST_AUTH_TOKEN))
    				req.getSession().setAttribute(key, documentSessionMap.get(key));
    		}
	    }
	    //Ended code
	    
	    newCtx.put(BREAD_CRUMB_PAGE, pageName);
	    populateSessionForBreadCrumb(newCtx, req.getSession(), pageName);
	    return newCtx;
	}
  
   public String getOldPageRequest(HttpServletRequest req){

       List<Map<Object,Object>> stack = null;
 
       if(req.getSession().getAttribute(PAGE_LIST_STACK) != null)
            stack = (ArrayList<Map<Object, Object>>)req.getSession().getAttribute(PAGE_LIST_STACK);
       else
            stack = new ArrayList<Map<Object, Object>>();
      
       if(stack.isEmpty())
    	   return null;
       Map<Object,Object> page = new HashMap<Object, Object>();
       
       page = stack.get(stack.size()-1);

       
      if(page == null)
          return null;       
       
       
       return page.get("bread_crumb_page_content") != null ? page.get("bread_crumb_page_content").toString() : null;

   }
   
   /*public void populateRequest(HttpServletRequest request, Context ctx){
	   IContext requestVars = ctx.subset(Constants.INET_REQUEST_NAMESPACE);
	   if(requestVars == null)
		   return;

	   if(requestVars.get(Constants.INET_PROJECT_ID) == null && ctx.get(Constants.INET_PROJECT_ID) != null)
		   requestVars.put(Constants.INET_PROJECT_ID, "Y");

	   Iterator keyIt = requestVars.keySet().iterator();
	   
	   while(keyIt.hasNext()){
		   String key = (String)keyIt.next();
		   Object requestUpdate = requestVars.get(key);
		   if(requestUpdate == null)
			   continue;

		   if(Constants.NO.equals(requestUpdate))
			   request.removeAttribute(key);
		   else{
			   Object val = requestVars.get(key);
			   if(val != null && val instanceof Integer)
				   val = val.toString();
			   if(val != null)
				   request.setAttribute(key, val);
			}
		}
	}*/
   
   public String createAjaxErrorsHTML(Context ctx, HttpServletRequest request) throws Exception {
		/*ProcessAction processAction = new ProcessAction();
		processAction.validate(ctx, (String) ctx.get(Constants.INET_PAGE));*/

		List errorlist = null;
		Element table = null;

		if(ctx.get(Constants.INET_ERRORS_LIST)!=null)
			errorlist = (List)ctx.get(Constants.INET_ERRORS_LIST);

		if(errorlist == null){
			if(ctx.get(Constants.IS_RULEVALIDATE) != null && "Y".equals((String)ctx.get(Constants.IS_RULEVALIDATE))){
				ValidationException e = new ValidationException("Rule is valid");
				errorlist = new ArrayList();
				errorlist.add(e);
               ctx.put(Constants.INET_FORM_DIRTY, Constants.YES);
			}else{
				return null;
			}
		}

		StringBuffer errorHTML = new StringBuffer();
		for(int i=0; i<errorlist.size(); i++){
			Object validationObj = errorlist.get(i);
			ValidationException  exception = (ValidationException) validationObj;
			errorHTML.append("ajaxerrors" + exception.getField() + ":" + exception.getMessage() +"|");
		}

		return errorHTML.toString();
	}
   
    //newly created for updating current session from old context 
    private void populateSessionForBreadCrumb(Context oldContext, HttpSession session, String pageName){
    	try{
	    	Enumeration<String> enm = session.getAttributeNames();
	    	while(enm.hasMoreElements()){
	    		String attributeName = enm.nextElement();
	    		if(!attributeName.equals(PAGE_LIST_STACK) && !attributeName.equals("REQUEST_AUTH_TOKEN") && oldContext.containsKey(attributeName))
	    			session.setAttribute(attributeName, oldContext.get(attributeName));
	    	}
	    	
	    	ComponentImpl compImpl = ComponentResources.getInstance(oldContext).getComponent(pageName);
	    	if(compImpl == null)
	    		return;
	    	
	    	if(!StringUtils.isBlank(compImpl.getBreadcrumbsessionparams())){
	    		String breadCrumbsessionParams = compImpl.getBreadcrumbsessionparams();
	    		StringTokenizer tokens = new StringTokenizer(breadCrumbsessionParams, ",");
	    		while(tokens.hasMoreTokens()){
	    			String token = tokens.nextToken();
	    			if(oldContext.containsKey(token))
	    				session.setAttribute(token, oldContext.get(token));
	    		}
	    	}
    	}catch (Exception e) {
			logger.error("Unable to populate session for Breadcrumb bcoz of error : " + e.getMessage());
		}
    }
    
    //Method created to create page list stack
	public String createPageListStack(Context ctx) throws Exception{
		String pageStackListContent = null;
		
		if(ctx.get(HtmlConstants.PAGE_LIST_STACK) != null && ctx.get(HtmlConstants.PAGE_LIST_STACK) instanceof List){
			List list = (List)ctx.get(HtmlConstants.PAGE_LIST_STACK);
			
			Element spanTableElement = new Element(HtmlConstants.SPAN);
			spanTableElement.setAttribute(HtmlConstants.ID, HtmlConstants.PAGE_LIST_STACK);
			
			Element trSpanElement = new Element(HtmlConstants.SPAN);
			trSpanElement.setAttribute(HtmlConstants.ID, "page_list_header_stack");
			spanTableElement.addContent(trSpanElement.detach());
			
			for(int i=0; i<list.size(); i++){
				Map map = (Map)list.get(i);
				
				trSpanElement = new Element(HtmlConstants.SPAN);
				trSpanElement.setAttribute(HtmlConstants.ID, "page_list_data_stack");
				
				Element tdSpanElement = new Element(HtmlConstants.SPAN);
				tdSpanElement.setAttribute(HtmlConstants.ID, "block_field_bread_crumb_display_page");
				
				if((i == list.size()-1) && (ctx.get("isShowBreadCrumbLinkForSinglePage") == null || 
						!ctx.get("isShowBreadCrumbLinkForSinglePage").toString().equals("Y"))){ //last record only
					
					tdSpanElement.setAttribute(HtmlConstants.CSS_CLASS, "breadcrumbLast");
					
					Element spanElement = new Element(HtmlConstants.SPAN);
					spanElement.addContent(map.get("bread_crumb_display_page").toString());
					
					tdSpanElement.addContent(spanElement.detach());
				}else{
					tdSpanElement.setAttribute(HtmlConstants.CSS_CLASS, "breadcrumbLink");
					
					Element aElement = new Element(HtmlConstants.LINK);
					String href = "javascript:managesubmitform(document.forms[0],'breadCrumbLink','|inet_skip_validation|bread_crumb_page|','|Y|"+map.get("bread_crumb_page")+"|','"+map.get("bread_crumb_display_page").toString()+"');";
					
					aElement.setAttribute(HtmlConstants.HREF, href);
					aElement.addContent(map.get("bread_crumb_display_page").toString());
					tdSpanElement.addContent(aElement.detach());
					
					if(i != list.size()-1)
						tdSpanElement.addContent(" > ");
				}
				
				trSpanElement.addContent(tdSpanElement.detach());
				
				spanTableElement.addContent(trSpanElement.detach());
			}
			
			pageStackListContent = new XMLOutputter(Format.getPrettyFormat()).outputString(spanTableElement);
		}
		
		return pageStackListContent;
	}
	
	//Method created to check for showAccordainPage
    private void checkForShowAccordianPage(Context ctx, HttpServletRequest request, Document document) throws Exception{
		try{
			if(ctx.get("showAccordianPage") != null && !ctx.get("showAccordianPage").toString().equals(HtmlConstants.EMPTY) &&
					ctx.get("showAccordianPageId") != null && !ctx.get("showAccordianPageId").toString().equals(HtmlConstants.EMPTY)){
				
				String showAccordianPage = ctx.get("showAccordianPage").toString();
				String showAccordianPageId = ctx.get("showAccordianPageId").toString();
				
				//going to execute view of accordian page to be opened
				new ProcessAction().processRoute(ctx, showAccordianPage);
				
				if(showAccordianPageId.contains("div_ajaxpostion")){
					//String showAccordianPageInputId = null;
					String showAccordianPageIdFirst = showAccordianPageId.substring(0, showAccordianPageId.lastIndexOf(":"));
					String showAccordianPageIdSecond = showAccordianPageId.substring(showAccordianPageId.lastIndexOf(":")+1, showAccordianPageId.length());
					//showAccordianPageInputId = showAccordianPageIdSecond;
					
					showAccordianPageIdSecond = ctx.get(showAccordianPageIdSecond) != null ? ctx.get(showAccordianPageIdSecond).toString() : showAccordianPageIdSecond;
					
					showAccordianPageId = showAccordianPageIdFirst + ":" + showAccordianPageIdSecond;
					
					/*//going to revise accordian data list
					if(ctx.get("showAccordianPageList") != null && !ctx.get("showAccordianPageList").toString().equals(HtmlConstants.EMPTY)){
						String showAccordianPageList = ctx.get("showAccordianPageList").toString();
						
						if(ctx.get(showAccordianPageList) != null && ctx.get(showAccordianPageList) instanceof List){
							List dataList = (List)ctx.get(showAccordianPageList);
							List finalList = new ArrayList();
							Map dataMap = null;
							
							if(dataList != null && dataList.size() > 0){
								for(int i=0; i<dataList.size(); i++){
									Map map = (Map)dataList.get(i);
									
									if(map.get(showAccordianPageInputId) != null && !map.get(showAccordianPageInputId).toString().equals(HtmlConstants.EMPTY) && showAccordianPageIdSecond != null &&
											!showAccordianPageIdSecond.equals(HtmlConstants.EMPTY) &&
											Integer.parseInt(map.get(showAccordianPageInputId).toString()) == Integer.parseInt(showAccordianPageIdSecond)){
										
										dataMap = map;
										
										dataList.remove(map);
										i--;
									}
								}
								
								finalList.add(dataMap);
								finalList.addAll(dataList);
								
								ctx.put(showAccordianPageList, finalList);
							}
						}
					}*/
				}
				
				Element rootElement = document.getRootElement();
				
				addAccordianPageContent(ctx, request, rootElement, showAccordianPage, showAccordianPageId);
			}
		}catch(Exception e){
			logger.error("Unable to add accordian page due to error : " + e.getMessage());
		}
    }
    
    //Method created to add accordian page content in container id
  	private void addAccordianPageContent(Context ctx, HttpServletRequest request, Object parentObj, String showAccordianPage, String showAccordianPageId) throws Exception{
  		if(parentObj != null && parentObj instanceof Element){
  			Element parentElement = (Element)parentObj;
  			
  			if(parentElement != null && parentElement.getChildren() != null && parentElement.getChildren().size() > 0){
  				for(int i=0; i<parentElement.getChildren().size(); i++){
  					Object obj = parentElement.getChildren().get(i);
  					
  					if(obj != null && obj instanceof Element){
  						Element element = (Element)obj;
  						
  						//System.out.println(element.getAttributeValue(HtmlConstants.ID));
  						
  						if(element.getAttributeValue(HtmlConstants.ID) != null &&
  								element.getAttributeValue(HtmlConstants.ID).equals(showAccordianPageId)){
  							
  							element.removeContent();
  							
  							String accordianPageContent = parseHtmlDocument(ctx, showAccordianPage, request);
  							
  							Element rootElement = getDocument(accordianPageContent, showAccordianPage).getRootElement();
  							
  							getAccordianPageContent(element, rootElement, showAccordianPage);
  							
  							return;
  						}
  						
  						if(element.getAttributeValue(HtmlConstants.ID) != null &&
  								element.getAttributeValue(HtmlConstants.ID).equals("divHideShow")){
  							
  							element.setAttribute(HtmlConstants.VALUE, showAccordianPageId);
  							
  							return;
  						}
  					}
  					
  					addAccordianPageContent(ctx, request, obj, showAccordianPage, showAccordianPageId);
  				}
  			}
  		}
  	}
  	
  	private void getAccordianPageContent(Element parentElement, Object parentObj, String showAccordianPage) throws Exception{
		if(parentObj != null && parentObj instanceof Element){
			Element parentObjElement = (Element)parentObj;
			
			if(parentObjElement.getChildren() != null && parentObjElement.getChildren().size() > 0){
				for(int i=0; i<parentObjElement.getChildren().size(); i++){
					Object obj = parentObjElement.getChildren().get(i);
					
					if(obj instanceof Element){
						Element element = (Element)obj;
						
						if(element.getAttributeValue(HtmlConstants.ID) != null &&
								element.getAttributeValue(HtmlConstants.ID).equals(showAccordianPage)){
							
							parentElement.addContent(element.detach());
							return;
						}
					}
					
					getAccordianPageContent(parentElement, obj, showAccordianPage);
				}
			}
		}
	}
  	
  	public void checkForShowAccordianPageList(Context ctx) throws Exception{
  		try{
  			if(ctx.get("showAccordianPage") != null && !ctx.get("showAccordianPage").toString().equals(HtmlConstants.EMPTY) &&
					ctx.get("showAccordianPageId") != null && !ctx.get("showAccordianPageId").toString().equals(HtmlConstants.EMPTY)){
				
				String showAccordianPageId = ctx.get("showAccordianPageId").toString();
				
				if(showAccordianPageId.contains("div_ajaxpostion")){
					String showAccordianPageInputId = null;
					String showAccordianPageIdFirst = showAccordianPageId.substring(0, showAccordianPageId.lastIndexOf(":"));
					String showAccordianPageIdSecond = showAccordianPageId.substring(showAccordianPageId.lastIndexOf(":")+1, showAccordianPageId.length());
					showAccordianPageInputId = showAccordianPageIdSecond;
					
					showAccordianPageIdSecond = ctx.get(showAccordianPageIdSecond) != null ? ctx.get(showAccordianPageIdSecond).toString() : showAccordianPageIdSecond;
					
					showAccordianPageId = showAccordianPageIdFirst + ":" + showAccordianPageIdSecond;
					
					//going to revise accordian data list
					if(ctx.get("showAccordianPageList") != null && !ctx.get("showAccordianPageList").toString().equals(HtmlConstants.EMPTY)){
						String showAccordianPageList = ctx.get("showAccordianPageList").toString();
						
						if(ctx.get(showAccordianPageList) != null && ctx.get(showAccordianPageList) instanceof List){
							List dataList = (List)ctx.get(showAccordianPageList);
							List finalList = new ArrayList();
							Map dataMap = null;
							
							if(dataList != null && dataList.size() > 0){
								for(int i=0; i<dataList.size(); i++){
									Map map = (Map)dataList.get(i);
									
									if(map.get(showAccordianPageInputId) != null && !map.get(showAccordianPageInputId).toString().equals(HtmlConstants.EMPTY) && showAccordianPageIdSecond != null &&
											!showAccordianPageIdSecond.equals(HtmlConstants.EMPTY) &&
											Integer.parseInt(map.get(showAccordianPageInputId).toString()) == Integer.parseInt(showAccordianPageIdSecond)){
										
										dataMap = map;
										
										dataList.remove(map);
										i--;
									}
								}
								
								finalList.add(dataMap);
								finalList.addAll(dataList);
								
								ctx.put(showAccordianPageList, finalList);
							}
						}
					}
				}
  			}
  		}catch(Exception e){
  			logger.error("Unable to check for accordian page list due to error : " + e.getMessage());
  		}
  	}
  	
  	//Method created to populate context with tabsconfiguration values
  	public void populateContextWithTabsConfiguration(Context ctx) throws Exception{
  		//Added code to populate context with Tabs_Conf_Map cache data -- by vikas 4/6/12
		if(CacheManager.get("Tabs_Conf_Map") != null && CacheManager.get("Tabs_Conf_Map") instanceof Map){
			Map map = (Map)CacheManager.get("Tabs_Conf_Map");
			Set keysSet = map.keySet();
			Iterator itr = keysSet.iterator();
			while(itr.hasNext()){
				String key = itr.next().toString();
				ctx.put(key, map.get(key));
			}
		}
		//Ended code
  	}
  	
  	//Method created to get old page context
	public Context getOldRequestForPage(HttpServletRequest req, String pageName){
		List<Map<Object,Object>> stack = null;
	    Context newCtx = new Context();
	    try {
	    	FormUtils.populateMultipartDynaKeysForBreadCrumb(req, newCtx);
			populateDynaKeysSafely(req, newCtx);
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
		
	    if(req.getSession().getAttribute(HtmlConstants.PAGE_LIST_STACK) != null)
	    	stack = (ArrayList<Map<Object, Object>>)req.getSession().getAttribute(HtmlConstants.PAGE_LIST_STACK);
	    else
	        stack = new ArrayList<Map<Object, Object>>();
	   
	    int indexOfExistPage = checkExistanceOfPageInStack(stack,pageName,null);
	    if(indexOfExistPage == 0)
	    	return null;
	     
	    Map<Object,Object> page = new HashMap<Object, Object>();
	
	    page = stack.get(indexOfExistPage-1);
	      
	    if(page == null)
	    	return null;
	    
	    newCtx = (Context)page.get("bread_crumb_page_request");
	    
	    return newCtx;
	}
	
	//Method created to get list of labels/security/tabsconfiguration ids on page
	private String checkForLabelSecurityTabsConfigIdOnPage(Context ctx, Document document, String htmlContent, String next_page) throws Exception{
		try{
			if(SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES) != null &&
				!SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES).toString().equals(HtmlConstants.EMPTY) &&
				("|"+SystemProperties.getInstance().getProperty("appl.configuration."+HtmlConstants.CONFIGURATION_ROLES).toString()+"|").contains("|"+ctx.get(HtmlConstants.ROLES)+"|")){
				
				Element rootElement = document.getRootElement();
				
				List<Map<String, String>> labelsList = new ArrayList<Map<String, String>>();
				List<Map<String, String>> tabsconfigurationList = new ArrayList<Map<String, String>>();
				List<Map<String, String>> securityList = new ArrayList<Map<String, String>>();
				
				Map<String, String> labelCacheMap = CacheManager.get("Labels_Conf") != null ? (Map)CacheManager.get("Labels_Conf") : null;
				Map<String, String> tabsconfigurationCacheMap = CacheManager.get("Tabs_Conf") != null ? (Map)CacheManager.get("Tabs_Conf") : null;
				
				checkForLabelSecurityTabsConfigIdOnPageInner(ctx, rootElement, next_page, labelCacheMap, tabsconfigurationCacheMap, labelsList, tabsconfigurationList, securityList);
				
				//logger.debug("labelsList : "+labelsList);
				
				if(labelsList != null && labelsList.size() > 0){
					Element tableElement = new Element(HtmlConstants.TABLE);
					tableElement.setAttribute(HtmlConstants.CSS_CLASS, "gridTable");
					tableElement.setAttribute(HtmlConstants.CELLSPACING, "0");
					tableElement.setAttribute(HtmlConstants.CELLPADDING, "0");
					tableElement.setAttribute(HtmlConstants.WIDTH, "100%");
					tableElement.setAttribute(HtmlConstants.ID, "labelsList_list_1");
					
					Element trElement = new Element(HtmlConstants.TR);
					trElement.setAttribute(HtmlConstants.ID, "labelsList_list_header_1");
					
					Element tdElement = new Element(HtmlConstants.TD);
					tdElement.setAttribute(HtmlConstants.ID, HtmlConstants.BLOCK_FIELD+"labeldesc");
					tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
					tdElement.setText("Description");
					trElement.addContent(tdElement.detach());
					
					tdElement = new Element(HtmlConstants.TD);
					tdElement.setAttribute(HtmlConstants.ID, HtmlConstants.BLOCK_FIELD+"labelkey");
					tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
					tdElement.setText("Id");
					trElement.addContent(tdElement.detach());
					
					tableElement.addContent(trElement.detach());
					
					for(int i=0; i<labelsList.size(); i++){
						Map rowMap = (Map)labelsList.get(i);
						
						trElement = new Element(HtmlConstants.TR);
						if(i%2==0)
							trElement.setAttribute(HtmlConstants.CSS_CLASS, "listRow2CSS");
						else
							trElement.setAttribute(HtmlConstants.CSS_CLASS, "listRow1CSS");
						
						trElement.setAttribute(HtmlConstants.ID, "labelsList_list_data_1");
						
						tdElement = new Element(HtmlConstants.TD);
						tdElement.setAttribute("id", "block_field_labeldesc");
						tdElement.setText(rowMap.get("labeldesc").toString());
						trElement.addContent(tdElement.detach());
						
						tdElement = new Element(HtmlConstants.TD);
						tdElement.setAttribute("id", "block_field_labelkey");
						tdElement.setText(rowMap.get("labelkey").toString());
						trElement.addContent(tdElement.detach());
						
						tableElement.addContent(trElement.detach());
					}
					
					String labelListContent = new XMLOutputter(Format.getPrettyFormat()).outputString(tableElement);
					
					htmlContent = htmlContent.replace("#labelsList_list_1#", labelListContent);
				}else{
					htmlContent = htmlContent.replace("#labelsList_list_1#", HtmlConstants.EMPTY);
				}
				
				if(tabsconfigurationList != null && tabsconfigurationList.size() > 0){
					Element tableElement = new Element(HtmlConstants.TABLE);
					tableElement.setAttribute(HtmlConstants.CELLSPACING, "0");
					tableElement.setAttribute(HtmlConstants.CELLPADDING, "0");
					tableElement.setAttribute(HtmlConstants.WIDTH, "100%");
					tableElement.setAttribute(HtmlConstants.CSS_CLASS, "gridTable");
					tableElement.setAttribute(HtmlConstants.ID, "tabsconfigurationList_list_1");
					
					Element trElement = new Element(HtmlConstants.TR);
					trElement.setAttribute(HtmlConstants.ID, "tabsconfigurationList_list_header_1");
					
					Element tdElement = new Element(HtmlConstants.TD);
					
					tdElement = new Element(HtmlConstants.TD);
					tdElement.setAttribute(HtmlConstants.ID, HtmlConstants.BLOCK_FIELD+"id");
					tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
					tdElement.setText("Id");
					trElement.addContent(tdElement.detach());
					
					tdElement = new Element(HtmlConstants.TD);
					tdElement.setAttribute(HtmlConstants.ID, HtmlConstants.BLOCK_FIELD+"description");
					tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
					tdElement.setText("Description");
					trElement.addContent(tdElement.detach());
					
					tableElement.addContent(trElement.detach());
					
					for(int i=0; i<tabsconfigurationList.size(); i++){
						Map rowMap = (Map)tabsconfigurationList.get(i);
						
						trElement = new Element(HtmlConstants.TR);
						if(i%2==0)
							trElement.setAttribute(HtmlConstants.CSS_CLASS, "listRow2CSS");
						else
							trElement.setAttribute(HtmlConstants.CSS_CLASS, "listRow1CSS");
						
						trElement.setAttribute(HtmlConstants.ID, "tabsconfigurationList_list_data_1");
						
						tdElement = new Element(HtmlConstants.TD);
						tdElement.setAttribute("id", "block_field_id");
						tdElement.setText(rowMap.get("id").toString());
						trElement.addContent(tdElement.detach());
						
						tdElement = new Element(HtmlConstants.TD);
						tdElement.setAttribute("id", "block_field_description");
						tdElement.setText(rowMap.get("description").toString());
						trElement.addContent(tdElement.detach());
						
						tableElement.addContent(trElement.detach());
					}
					
					String labelListContent = new XMLOutputter(Format.getPrettyFormat()).outputString(tableElement);
					
					htmlContent = htmlContent.replace("#tabsconfigurationList_list_1#", labelListContent);
				}else{
					htmlContent = htmlContent.replace("#tabsconfigurationList_list_1#", HtmlConstants.EMPTY);
				}
				
				if(securityList != null && securityList.size() > 0){
					Element tableElement = new Element(HtmlConstants.TABLE);
					tableElement.setAttribute(HtmlConstants.CELLSPACING, "0");
					tableElement.setAttribute(HtmlConstants.CELLPADDING, "0");
					tableElement.setAttribute(HtmlConstants.WIDTH, "100%");
					tableElement.setAttribute(HtmlConstants.CSS_CLASS, "gridTable");
					tableElement.setAttribute(HtmlConstants.ID, "securityList_list_list_1");
					
					Element trElement = new Element(HtmlConstants.TR);
					trElement.setAttribute(HtmlConstants.ID, "securityList_list_header_1");
					
					Element tdElement = new Element(HtmlConstants.TD);
					
					tdElement = new Element(HtmlConstants.TD);
					tdElement.setAttribute(HtmlConstants.ID, HtmlConstants.BLOCK_FIELD+"id");
					tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
					tdElement.setText("Id");
					trElement.addContent(tdElement.detach());
					
					tdElement = new Element(HtmlConstants.TD);
					tdElement.setAttribute(HtmlConstants.ID, HtmlConstants.BLOCK_FIELD+"description");
					tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
					tdElement.setText("Description");
					trElement.addContent(tdElement.detach());
					
					tableElement.addContent(trElement.detach());
					
					for(int i=0; i<securityList.size(); i++){
						Map rowMap = (Map)securityList.get(i);
						
						trElement = new Element(HtmlConstants.TR);
						if(i%2==0)
							trElement.setAttribute(HtmlConstants.CSS_CLASS, "listRow2CSS");
						else
							trElement.setAttribute(HtmlConstants.CSS_CLASS, "listRow1CSS");
						
						trElement.setAttribute(HtmlConstants.ID, "securityList_list_data_1");
						
						tdElement = new Element(HtmlConstants.TD);
						tdElement.setAttribute("id", "block_field_uniquename");
						tdElement.setText(rowMap.get("uniquename").toString());
						trElement.addContent(tdElement.detach());
						
						tdElement = new Element(HtmlConstants.TD);
						tdElement.setAttribute("id", "block_field_description");
						tdElement.setText(rowMap.get("description") != null ? rowMap.get("description").toString() : HtmlConstants.EMPTY);
						trElement.addContent(tdElement.detach());
						
						tableElement.addContent(trElement.detach());
					}
					
					String labelListContent = new XMLOutputter(Format.getPrettyFormat()).outputString(tableElement);
					
					htmlContent = htmlContent.replace("#securityList_list_1#", labelListContent);
				}else{
					htmlContent = htmlContent.replace("#securityList_list_1#", HtmlConstants.EMPTY);
				}
			}
		}catch(Exception e){
			logger.error("Unable to get list of labels/security/tabsconfiguration ids due to error : " + e.getMessage());
		}
		
		return htmlContent;
    }
    
    //Method created to get list of labels/security/tabsconfiguration ids on page
  	private void checkForLabelSecurityTabsConfigIdOnPageInner(Context ctx, Element parentObj, String next_page, Map labelCacheMap, Map tabsconfigurationCacheMap,
  			List labelsList, List tabsconfigurationList, List securityList) throws Exception{
  		
  		if(parentObj != null && parentObj instanceof Element){
  			Element parentElement = (Element)parentObj;
  			
  			if(parentElement != null && parentElement.getChildren() != null && parentElement.getChildren().size() > 0){
  				for(int i=0; i<parentElement.getChildren().size(); i++){
  					Object obj = parentElement.getChildren().get(i);
  					
  					if(obj != null && obj instanceof Element){
  						Element element = (Element)obj;
  						
  						if(element.getAttributeValue(HtmlConstants.ID) != null){
  							String id = element.getAttributeValue(HtmlConstants.ID);
  							
  							//going to check for labelconf id
  							if(labelCacheMap != null && labelCacheMap.containsKey(id)){
  								Map map = new HashMap();
  								
  								map.put("labelkey", id);
  								map.put("labeldesc", labelCacheMap.get(id));
  								
  								if(!labelsList.contains(map))
  									labelsList.add(map);
  							}
  							
  							//going to check for tabsconfiguration id
  							if(tabsconfigurationCacheMap != null && tabsconfigurationCacheMap.containsKey(id)){
  								Map map = new HashMap();
  								
  								map.put("id", id);
  								map.put("description", TabsConfigurationResources.getInstance(ctx).getTabsconfigurationresource(id).getDescription());
  								
  								if(!tabsconfigurationList.contains(map))
  									tabsconfigurationList.add(map);
  							}
  							
  							//going to check for security id
  							if(SecurityResources.getInstance(ctx).getProtectedResource(next_page+"."+id) != null){
  								Map map = new HashMap();
  								
  								map.put("uniquename", id);
  								map.put("description", SecurityResources.getInstance(ctx).getProtectedResource(next_page+"."+id).getDescription());
  								
  								if(!securityList.contains(map))
  									securityList.add(map);
  							}
  							if(SecurityResources.getInstance(ctx).getProtectedResource("common."+id) != null){
  								Map map = new HashMap();
  								
  								map.put("uniquename", id);
  								map.put("description", SecurityResources.getInstance(ctx).getProtectedResource("common."+id).getDescription());
  								
  								if(!securityList.contains(map))
  									securityList.add(map);
  							}
  						}
  						
  						checkForLabelSecurityTabsConfigIdOnPageInner(ctx, element, next_page, labelCacheMap, tabsconfigurationCacheMap, labelsList, tabsconfigurationList, securityList);
  					}
  				}
  			}
  		}
  	}
}

