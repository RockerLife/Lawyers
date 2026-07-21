package com.manage.process;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.manage.framework.ProcessAction;
import com.manage.mail.MailSender;
import com.manage.managecomponent.components.DocumentImpl;
import com.manage.managemetadata.functions.commonfunctions.DataUtils;
import com.manage.managemetadata.util.exception.ValidationException;
import com.manage.managereporting.ReportingConstants;
import com.manage.util.PageUtils;
import com.processor.XSLTProcessor;
import com.util.Constants;
import com.util.Context;
import com.util.HTML2XML;
import com.util.HTMLUtils;
import com.util.HtmlConstants;
import com.util.IOUtils;
import com.util.SystemProperties;

public class PIMRequestProcessorImpl implements PIMRequestProcessorInterface{
	private Logger logger = Logger.getLogger(PIMRequestProcessorImpl.class);
	
	public void populateNextComponentpage(Context ctx, PageUtils utils, HttpServletRequest request,
			HttpServletResponse response)throws Exception {

		Context breadCrumbContext = new Context();
		
		//Component Processing
		String next_page = utils.populateNextComponentpage(ctx, request);
		
		if(ctx.get(HtmlConstants.INET_METHOD) != null && 
			ctx.get(HtmlConstants.INET_METHOD).toString().startsWith("download")){
			
			if(ctx.get(Constants.INET_ERRORS_LIST) != null){
				HttpServletResponse resp = (HttpServletResponse)ctx.get("DocumentResponse");
				byte[] rb = new byte[0];
				resp.setContentLength(rb.length);
				
				String fileName = null;
				
				if(ctx.get("document_name") != null && !ctx.get("document_name").toString().equals(HtmlConstants.EMPTY)){
					fileName = ctx.get("document_name").toString();
				}
				
				if(fileName == null && ctx.get("file_name") != null && !ctx.get("file_name").toString().equals(HtmlConstants.EMPTY)){
					fileName = ctx.get("file_name").toString();
				}
				
				if(fileName == null)
					fileName = ctx.getProject();
				
				resp.setContentType(((ServletContext)ctx.get("DocumentServletContext")).getMimeType(fileName));
				resp.setHeader("content-disposition", "attachment;filename="+fileName);
				ServletOutputStream sout = resp.getOutputStream();
				sout.write(rb);
				sout.close();
			}
			
			return;
		}
		
		if("sorting".equals(ctx.get(HtmlConstants.INET_METHOD))|| "pagination".equals(ctx.get(HtmlConstants.INET_METHOD)))
			breadCrumbContext = (Context)ctx.clone();
		else
			breadCrumbContext = (Context)ctx.get(HtmlConstants.BREADCRUMB_CONTEXT);
		
		ctx.put("next_page_for_view", next_page);
		
		//updating session data
		utils.populateSession(request, ctx);
		
		if((request.getParameter("inet_method") != null && !"breadCrumbLink".equals(request.getParameter("inet_method").toString()))
        		&& (ctx.get("inet_skip_breadcrumb")==null || !"Y".equalsIgnoreCase(ctx.get("inet_skip_breadcrumb").toString()))
        		&& !(ctx.get(HtmlConstants.INET_METHOD).toString().startsWith("download") || ctx.get(HtmlConstants.INET_METHOD).toString().startsWith("export")
        				|| ctx.get(HtmlConstants.INET_METHOD).toString().startsWith("showSessionList"))
        		&& !Constants.YES.equals(ctx.get(Constants.INET_FORM_DIRTY)))
            utils.setPageInStack(breadCrumbContext, request, next_page, HtmlConstants.EMPTY, ctx); 
		
		String htmlDoc = null;
		
		//updating PAGE_LIST_STACK in current context
		ctx.put(PageUtils.PAGE_LIST_STACK, breadCrumbContext.get(PageUtils.PAGE_LIST_STACK));
		
		if(SystemProperties.getInstance().getProperty("appl." + ctx.getProject() + ".xslt.processor") != null && 
				SystemProperties.getInstance().getProperty("appl." + ctx.getProject() + ".xslt.processor").equals("Y")){
			XSLTProcessor xsltProcessor = new XSLTProcessor();
			String dataXmlPath = SystemProperties.getInstance().getProperty("appl.home.dir").toString() + "contextdata.xml";
			ctx.put("next_page", next_page);
			
			File xmlDataFile = new File(dataXmlPath);
			xmlDataFile.delete();
			
			xsltProcessor.populateDataXml(ctx, dataXmlPath, false);
			htmlDoc = xsltProcessor.process(request, response, next_page, false, ctx, null, false, dataXmlPath);
		}else 
			htmlDoc = utils.parseHtmlDocument(ctx, next_page,request);
		
		htmlDoc = appendJSessionIdToCapcha(request, htmlDoc); //To append JSESSIONID to JCapcha
		htmlDoc = addDIVForProgressBar(htmlDoc);
		
		if((request.getParameter("inet_method") != null && !"breadCrumbLink".equals(request.getParameter("inet_method").toString()))
        		&& (ctx.get("inet_skip_breadcrumb")==null || !"Y".equalsIgnoreCase(ctx.get("inet_skip_breadcrumb").toString()))
        		&& !(ctx.get(HtmlConstants.INET_METHOD).toString().startsWith("download") || ctx.get(HtmlConstants.INET_METHOD).toString().startsWith("export")
        				|| ctx.get(HtmlConstants.INET_METHOD).toString().startsWith("showSessionList"))
        		&& !Constants.YES.equals(ctx.get(Constants.INET_FORM_DIRTY)))
			utils.setPageInStackWithContent(ctx,request,next_page,htmlDoc);   
		 
		//Added code to write old response for uploadDocument action - 19/7/2012
		if(ctx.get(HtmlConstants.INET_METHOD) != null && (ctx.get(HtmlConstants.INET_METHOD).equals("uploadAttachment")
				|| ctx.get(HtmlConstants.INET_METHOD).toString().startsWith("download"))){
			
			String pageSource = ctx.get("htmlSource") != null ? ctx.get("htmlSource").toString() : HtmlConstants.EMPTY;
			
			//going to reset <form action=""> removing querystring
			/*if(pageSource.indexOf("<FORM") != -1){ // in IE
				
			}*/
			if(pageSource.indexOf("<form ") != -1){ //in Mozilla 
				String start = pageSource.substring(0, pageSource.indexOf("<form"));
				String end = pageSource.substring(pageSource.indexOf("<form"), pageSource.length());
				end = end.substring(end.indexOf(">")+1, end.length());
				
				pageSource = start + "<form action=\"test.do;jsessionid="+ request.getSession().getId() +"\" method=\"post\" enctype=\"multipart/form-data\">" + end;
				
			}
			
			boolean isFileUploadDownloadErrorFound = false;
			String fileUploadDownloadError = HtmlConstants.EMPTY;
			//handling error in case of download document
			if(ctx.get(Constants.INET_ERRORS_LIST) != null){
				List errorsList = (List)ctx.get(Constants.INET_ERRORS_LIST);
				if(errorsList != null && errorsList.size() > 0 && errorsList.get(0) instanceof ValidationException){
					ValidationException exp = (ValidationException)errorsList.get(0);
					
					fileUploadDownloadError = exp.getMessage();
					isFileUploadDownloadErrorFound = true;
					
					logger.error(exp.getMessage());
				}
			}
			
			String attachmentDivContentStart = "attachmentDivContentStart";
			if(ctx.get("rownumber") != null && !ctx.get("rownumber").toString().equals(HtmlConstants.EMPTY))
				attachmentDivContentStart = attachmentDivContentStart + ":" + ctx.get("rownumber");
			
			if(pageSource != null && pageSource.contains(attachmentDivContentStart)){
				if(pageSource.indexOf("<INPUT name=REQUEST_AUTH_TOKEN") != -1){ // in IE
					String start = pageSource.substring(0, pageSource.indexOf("<INPUT name=REQUEST_AUTH_TOKEN"));
					String end = pageSource.substring(pageSource.lastIndexOf("</FORM>"), pageSource.length());
					
					/*if(isFileUploadDownloadErrorFound){
						pageSource = start + "<INPUT name=REQUEST_AUTH_TOKEN value=" + request.getSession().getAttribute("REQUEST_AUTH_TOKEN").toString() + " type=hidden>";
						pageSource = pageSource + "<INPUT name=fileUploadDownloadError value=" + fileUploadDownloadError + " id=fileUploadDownloadError type=hidden>" + end;
					}else*/
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
							
							pageSource = start + "<INPUT name=REQUEST_AUTH_TOKEN value=" + request.getSession().getAttribute("REQUEST_AUTH_TOKEN").toString() + " type=hidden>";
							pageSource = pageSource + "<INPUT name=ssoTokenKey id=ajax_field_ssoTokenKey value=" + ssoTokenKey + "type=hidden>";
							pageSource = pageSource + "<INPUT name="+ssoTokenKey+" id=ajax_field_"+ssoTokenKey+" value=" + request.getParameter(ssoTokenKey) + "type=hidden>" +end;
						}else{
							pageSource = start + "<INPUT name=REQUEST_AUTH_TOKEN value=" + request.getSession().getAttribute("REQUEST_AUTH_TOKEN").toString() + " type=hidden>" + end;
						}
					}catch (Exception e) {
						pageSource = start + "<INPUT name=REQUEST_AUTH_TOKEN value=" + request.getSession().getAttribute("REQUEST_AUTH_TOKEN").toString() + " type=hidden>" + end;
					}
				}
				if(pageSource.indexOf("<input name=\"REQUEST_AUTH_TOKEN\"") != -1){ //in Mozilla 
					String start = pageSource.substring(0, pageSource.indexOf("<input name=\"REQUEST_AUTH_TOKEN\""));
					String end = pageSource.substring(pageSource.lastIndexOf("</form>"), pageSource.length());
					
					try{
						String isSSOEnabled = SystemProperties.getInstance().getString("appl."+ctx.getProject()+".issso.enabled");
						if(isSSOEnabled != null && isSSOEnabled.equals("Y")){
							String ssoTokenKey = "SAMLart";
							try{
								ssoTokenKey = SystemProperties.getInstance().getString("sso.token.key");
							}catch (Exception e) {
								logger.error("Unable to get sso.token.key property due to error : " + e.getMessage());
							}
							
							pageSource = start + "<input name=\"REQUEST_AUTH_TOKEN\" value=\"" + request.getSession().getAttribute("REQUEST_AUTH_TOKEN").toString() + "\" type=\"hidden\"/>";
							pageSource = pageSource + "<INPUT name=\"ssoTokenKey\" id=\"ajax_field_ssoTokenKey\" value=\"" + ssoTokenKey + "\" type=\"hidden\"/>";
							pageSource = pageSource + "<INPUT name=\""+ssoTokenKey+"\" id=\"ajax_field_"+ssoTokenKey+"\" value=\"" + request.getParameter(ssoTokenKey) + "\" type=\"hidden\"/>" +end;
						}else{
							pageSource = start + "<input name=\"REQUEST_AUTH_TOKEN\" value=\"" + request.getSession().getAttribute("REQUEST_AUTH_TOKEN").toString() + "\" type=\"hidden\"/>" + end;
						}
					}catch (Exception e) {
						pageSource = start + "<input name=\"REQUEST_AUTH_TOKEN\" value=\"" + request.getSession().getAttribute("REQUEST_AUTH_TOKEN").toString() + "\" type=\"hidden\"/>" + end;
					}
				}
				
				if(pageSource.indexOf("<input type=\"hidden\" name=\"REQUEST_AUTH_TOKEN\"") != -1){ //in Chrome 
					String start = pageSource.substring(0, pageSource.indexOf("<input type=\"hidden\" name=\"REQUEST_AUTH_TOKEN\""));
					String end = pageSource.substring(pageSource.lastIndexOf("</form>"), pageSource.length());
					
					try{
						String isSSOEnabled = SystemProperties.getInstance().getString("appl."+ctx.getProject()+".issso.enabled");
						if(isSSOEnabled != null && isSSOEnabled.equals("Y")){
							String ssoTokenKey = "SAMLart";
							try{
								ssoTokenKey = SystemProperties.getInstance().getString("sso.token.key");
							}catch (Exception e) {
								logger.error("Unable to get sso.token.key property due to error : " + e.getMessage());
							}
							
							pageSource = start + "<input type=\"hidden\" name=\"REQUEST_AUTH_TOKEN\" value=\"" + request.getSession().getAttribute("REQUEST_AUTH_TOKEN").toString() + "\"/>";
							pageSource = pageSource + "<INPUT type=\"hidden\" name=\"ssoTokenKey\" id=\"ajax_field_ssoTokenKey\" value=\"" + ssoTokenKey + "\"/>";
							pageSource = pageSource + "<INPUT type=\"hidden\" name=\""+ssoTokenKey+"\" id=\"ajax_field_"+ssoTokenKey+"\" value=\"" + request.getParameter(ssoTokenKey) + "\"/>" +end;
						}else{
							pageSource = start + "<input type=\"hidden\" name=\"REQUEST_AUTH_TOKEN\" value=\"" + request.getSession().getAttribute("REQUEST_AUTH_TOKEN").toString() + "\"/>" + end;
						}
					}catch (Exception e) {
						pageSource = start + "<input type=\"hidden\" name=\"REQUEST_AUTH_TOKEN\" value=\"" + request.getSession().getAttribute("REQUEST_AUTH_TOKEN").toString() + "\"/>" + end;
					}
				}
				
				//checking for inet_method/inet_page/dynaKeys/dynaValues
				if(pageSource.indexOf("<INPUT name=inet_method") != -1){ // in IE
					String start = pageSource.substring(0, pageSource.indexOf("<INPUT name=inet_method"));
					String end = pageSource.substring(pageSource.lastIndexOf("<INPUT name=inet_method"), pageSource.length());
					end = end.substring(end.indexOf(">")+1, end.length());
					
					pageSource = start + "<INPUT name=inet_method type=hidden>" + end;
					
					start = pageSource.substring(0, pageSource.indexOf("<INPUT name=inet_page"));
					end = pageSource.substring(pageSource.lastIndexOf("<INPUT name=inet_page"), pageSource.length());
					end = end.substring(end.indexOf(">")+1, end.length());
					
					pageSource = start + "<INPUT name=inet_page type=hidden>" + end;
					
					start = pageSource.substring(0, pageSource.indexOf("<INPUT name=dynaKeys"));
					end = pageSource.substring(pageSource.lastIndexOf("<INPUT name=dynaKeys"), pageSource.length());
					end = end.substring(end.indexOf(">")+1, end.length());
					
					pageSource = start + "<INPUT name=dynaKeys type=hidden>" + end;
					
					start = pageSource.substring(0, pageSource.indexOf("<INPUT name=dynaValues"));
					end = pageSource.substring(pageSource.lastIndexOf("<INPUT name=dynaValues"), pageSource.length());
					end = end.substring(end.indexOf(">")+1, end.length());
					
					pageSource = start + "<INPUT name=dynaValues type=hidden>" + end;
				}
				
				if(pageSource.indexOf("<input name=\"inet_method\"") != -1){ //in Mozilla 
					String start = pageSource.substring(0, pageSource.indexOf("<input name=\"inet_method\""));
					String end = pageSource.substring(pageSource.lastIndexOf("<input name=\"inet_method\""), pageSource.length());
					end = end.substring(end.indexOf(">")+1, end.length());
					
					pageSource = start + "<input name=\"inet_method\" type=\"hidden\"/>" + end;
					
					start = pageSource.substring(0, pageSource.indexOf("<input name=\"inet_page\""));
					end = pageSource.substring(pageSource.lastIndexOf("<input name=\"inet_page\""), pageSource.length());
					end = end.substring(end.indexOf(">")+1, end.length());
					
					pageSource = start + "<input name=\"inet_page\" type=\"hidden\"/>" + end;
					
					start = pageSource.substring(0, pageSource.indexOf("<input name=\"dynaKeys\""));
					end = pageSource.substring(pageSource.lastIndexOf("<input name=\"dynaKeys\""), pageSource.length());
					end = end.substring(end.indexOf(">")+1, end.length());
					
					pageSource = start + "<input name=\"dynaKeys\" type=\"hidden\"/>" + end;
					
					start = pageSource.substring(0, pageSource.indexOf("<input name=\"dynaValues\""));
					end = pageSource.substring(pageSource.lastIndexOf("<input name=\"dynaValues\""), pageSource.length());
					end = end.substring(end.indexOf(">")+1, end.length());
					
					pageSource = start + "<input name=\"dynaValues\" type=\"hidden\"/>" + end;
				}
				
				if(pageSource.indexOf("<input type=\"hidden\" name=\"inet_method\"") != -1){ //in Chrome 
					String start = pageSource.substring(0, pageSource.indexOf("<input type=\"hidden\" name=\"inet_method\""));
					String end = pageSource.substring(pageSource.lastIndexOf("<input type=\"hidden\" name=\"inet_method\""), pageSource.length());
					end = end.substring(end.indexOf(">")+1, end.length());
					
					pageSource = start + "<input type=\"hidden\" name=\"inet_method\"/>" + end;
					
					start = pageSource.substring(0, pageSource.indexOf("<input type=\"hidden\" name=\"inet_page\""));
					end = pageSource.substring(pageSource.lastIndexOf("<input type=\"hidden\" name=\"inet_page\""), pageSource.length());
					end = end.substring(end.indexOf(">")+1, end.length());
					
					pageSource = start + "<input type=\"hidden\" name=\"inet_page\"/>" + end;
					
					start = pageSource.substring(0, pageSource.indexOf("<input type=\"hidden\" name=\"dynaKeys\""));
					end = pageSource.substring(pageSource.lastIndexOf("<input type=\"hidden\" name=\"dynaKeys\""), pageSource.length());
					end = end.substring(end.indexOf(">")+1, end.length());
					
					pageSource = start + "<input type=\"hidden\" name=\"dynaKeys\"/>" + end;
					
					start = pageSource.substring(0, pageSource.indexOf("<input type=\"hidden\" name=\"dynaValues\""));
					end = pageSource.substring(pageSource.lastIndexOf("<input type=\"hidden\" name=\"dynaValues\""), pageSource.length());
					end = end.substring(end.indexOf(">")+1, end.length());
					
					pageSource = start + "<input type=\"hidden\" name=\"dynaValues\"/>" + end;
				}
				
				if(SystemProperties.getInstance().getProperty("appl." + ctx.getProject() + ".xslt.processor") != null && 
						SystemProperties.getInstance().getProperty("appl." + ctx.getProject() + ".xslt.processor").equals("Y")){
					pageSource = getPageSourceForXslt(pageSource, htmlDoc);
				}else{
					pageSource = getPageSource(pageSource, htmlDoc, ctx);
				}
			}
			
			if(isFileUploadDownloadErrorFound){
				if(pageSource.indexOf("<INPUT name=REQUEST_AUTH_TOKEN") != -1){ // in IE
					String start = pageSource.substring(0, pageSource.lastIndexOf("</FORM>"));
					String end = pageSource.substring(pageSource.lastIndexOf("</FORM>"), pageSource.length());
					
					pageSource = start + "<INPUT name=fileUploadDownloadError value=" + fileUploadDownloadError + " id=fileUploadDownloadError type=hidden>" + end;
				}
				if(pageSource.indexOf("<input name=\"REQUEST_AUTH_TOKEN\"") != -1 || pageSource.indexOf("<input type=\"hidden\" name=\"REQUEST_AUTH_TOKEN\"") != -1){ //in Mozilla 
					String start = pageSource.substring(0, pageSource.lastIndexOf("</form>"));
					String end = pageSource.substring(pageSource.lastIndexOf("</form>"), pageSource.length());
					
					pageSource = start + "<input name=\"fileUploadDownloadError\" id=\"fileUploadDownloadError\" value=\"" + fileUploadDownloadError + "\" type=\"hidden\"/>" + end;
				}
			}
			
			htmlDoc = pageSource;
		}
		//Ended code
		
		//Puting page level label conf fields in session
		/*if(ctx.get("labelconfiguredfieldsonpage_list_1") != null){
			if(request.getSession().getAttribute("labelconfiguredfieldsonpagelist") != null){
				Map pageMap = (Map)request.getSession().getAttribute("labelconfiguredfieldsonpagelist");
				
				pageMap.put(next_page, ctx.get("labelconfiguredfieldsonpage_list_1"));
				request.getSession().setAttribute("labelconfiguredfieldsonpagelist", pageMap);
			}else{
				Map pageMap = new HashMap();
				pageMap.put(next_page, ctx.get("labelconfiguredfieldsonpage_list_1"));
				request.getSession().setAttribute("labelconfiguredfieldsonpagelist", pageMap);
			}
		}*/
		
		//replacing dynamic content found on html page
		htmlDoc = htmlDoc.replace("#dynamicContent#", ctx.get("#dynamicContent#") != null ? ctx.get("#dynamicContent#").toString() : HtmlConstants.EMPTY);
		
		//replacing page list stack content on html page
		String pageStackContent = new PageUtils().createPageListStack(ctx);
		htmlDoc = htmlDoc.replace("#page_list_stack#", pageStackContent != null ? pageStackContent : HtmlConstants.EMPTY);
		
		response.setHeader("X-XSS-Protection", "0");
		utils.writeResponse(response, htmlDoc, "text/html");
	}
	
	private String appendJSessionIdToCapcha(HttpServletRequest req, String htmlDoc) throws Exception {
		if(htmlDoc != null && htmlDoc.contains("jcaptcha.jpg")){
			htmlDoc = htmlDoc.replaceAll("jcaptcha.jpg", "jcaptcha.jpg;jsessionid="+req.getSession().getId()+"?rnum="+new Random().nextLong());
		}
		
		return htmlDoc;
	}

	//Newly created to get attachment page content - 20/7/2012
	private String getAttachmentDivContent(String htmlDoc, Context ctx) throws Exception {
		String attachmentDiv = "attachmentDiv";
		
		String attachmentContent = "<div id=\""+attachmentDiv+"\">";
		
		if(htmlDoc.indexOf("<div id=\"attachmentDivInnerContentStart\"></div>") == -1 || 
				htmlDoc.indexOf("<div id=\"attachmentDivInnerContentEnd\"></div>") == -1)
			return attachmentContent;
			
		int startIndex = htmlDoc.indexOf("<div id=\"attachmentDivInnerContentStart\"></div>") + 47;
		int endIndex = htmlDoc.indexOf("<div id=\"attachmentDivInnerContentEnd\"></div>");
		
		attachmentContent = attachmentContent + htmlDoc.substring(startIndex, endIndex) + "</div>";
		
		//in case of attachments in grid
		if(ctx.get("rownumber") != null && !ctx.get("rownumber").toString().equals(HtmlConstants.EMPTY)){
			//attachmentDiv = attachmentDiv + ":" + ctx.get("rownumber");
			String content = "<div id=\"attachmentDiv:" + ctx.get("rownumber") + "\"><div id=\"div_ajaxpostion_uploadDocument:" + ctx.get("rownumber") + "\" class=\"bgAccordionN\">";
			content = content + attachmentContent + "</div></div>";
			
			attachmentContent = content;
		}
		
		return attachmentContent;
	}
	
	private String getPageSource(String pageSource, String htmlDoc, Context ctx) throws Exception {
		String attachmentDivContentStart = "attachmentDivContentStart";
		String attachmentDivContentEnd = "attachmentDivContentEnd";
		int rownumbersize = 0;
		
		if(ctx.get("rownumber") != null && !ctx.get("rownumber").toString().equals(HtmlConstants.EMPTY)){
			attachmentDivContentStart = attachmentDivContentStart + ":" + ctx.get("rownumber");
			attachmentDivContentEnd = attachmentDivContentEnd + ":" + ctx.get("rownumber");
			rownumbersize = 1 + ctx.get("rownumber").toString().length();
		}
		
		if(pageSource.contains("<DIV id="+attachmentDivContentStart+"></DIV>")){
			int startIndex = pageSource.indexOf("<DIV id="+attachmentDivContentStart+"></DIV>") + (40 + rownumbersize);
			int endIndex = pageSource.indexOf("<DIV id="+attachmentDivContentEnd+"></DIV>");
			
			String startPageSource = pageSource.substring(0, startIndex);
			String endPageSource = pageSource.substring(endIndex, pageSource.length());
			
			pageSource = startPageSource + getAttachmentDivContent(htmlDoc, ctx) + endPageSource;
			
		}else if(pageSource.contains("<div id=\""+attachmentDivContentStart+"\"></div>")){
			int startIndex = pageSource.indexOf("<div id=\""+attachmentDivContentStart+"\"></div>") + (42 + rownumbersize);
			int endIndex = pageSource.indexOf("<div id=\""+attachmentDivContentEnd+"\"></div>");
			
			String startPageSource = pageSource.substring(0, startIndex);
			String endPageSource = pageSource.substring(endIndex, pageSource.length());
			
			pageSource = startPageSource + getAttachmentDivContent(htmlDoc, ctx) + endPageSource;
		}
		
		return pageSource;
	}
	
	private String getPageSourceForXslt(String pageSource, String htmlDoc) throws Exception {
		if(pageSource.contains("<DIV id=attachmentDivContentStart>")){
			int startIndex1 = pageSource.indexOf("<DIV id=attachmentDivContentStart>");
			String pageSource1 = pageSource.substring(startIndex1, pageSource.length());
			int startIndex2 = pageSource1.indexOf("</DIV>") + 6;
			
			int startIndex = startIndex1 + startIndex2;
			
			/*int endIndex1 = pageSource.indexOf("<DIV id=attachmentDivContentEnd><SPAN style=\"COLOR: transparent !important\">.</SPAN>");
			String pageSource2 = pageSource.substring(endIndex1, pageSource.length());
			int endIndex2 = pageSource2.indexOf("</DIV>");*/
			
			int endIndex = pageSource.indexOf("<DIV id=attachmentDivContentEnd>");
			
			//int endIndex = pageSource.indexOf("<DIV id=attachmentDivContentEnd><SPAN style=\"COLOR: transparent !important\">.</SPAN>") + 6;
			
			String startPageSource = pageSource.substring(0, startIndex);
			String endPageSource = pageSource.substring(endIndex, pageSource.length());
			
			pageSource = startPageSource + getAttachmentDivContent(htmlDoc, null) + endPageSource;
		}else if(pageSource.contains("<div id=\"attachmentDivContentStart\">")){
			int startIndex1 = pageSource.indexOf("<div id=\"attachmentDivContentStart\">");
			String pageSource1 = pageSource.substring(startIndex1, pageSource.length());
			int startIndex2 = pageSource1.indexOf("</div>") + 6;
			
			int startIndex = startIndex1 + startIndex2;
			
			//int startIndex = pageSource.indexOf("<div id=\"attachmentDivContentStart\"><span style=\"color: transparent !important\">.</span></div>") + 94;
			
			int endIndex = pageSource.indexOf("<div id=\"attachmentDivContentEnd\">");
			
			String startPageSource = pageSource.substring(0, startIndex);
			String endPageSource = pageSource.substring(endIndex, pageSource.length());
			
			pageSource = startPageSource + getAttachmentDivContent(htmlDoc, null) + endPageSource;
		}
		
		return pageSource;
	}
	
	//Newly created to display bread crumb page
	public String populateBreadCrumbPage(Context ctx, PageUtils utils, HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		String breadCrumbPageContent = HtmlConstants.EMPTY;
		String bread_crumb_page = ctx.get("bread_crumb_page") != null ? ctx.get("bread_crumb_page").toString() : null;
		if(bread_crumb_page == null)
			return breadCrumbPageContent;
		
		if(bread_crumb_page.equals(ReportingConstants.REPORT_TEMPLATE_HTML_NAME)){
			breadCrumbPageContent = utils.getOldPageRequest(request);
			return breadCrumbPageContent;
		}
		
		new ProcessAction().processRoute(ctx, bread_crumb_page);
		
		//updating session data
		utils.populateSession(request, ctx);
		
		breadCrumbPageContent = utils.parseHtmlDocument(ctx, bread_crumb_page, request);
		breadCrumbPageContent = addDIVForProgressBar(breadCrumbPageContent);
		
		//replacing page list stack content on html page
		String pageStackContent = new PageUtils().createPageListStack(ctx);
		breadCrumbPageContent = breadCrumbPageContent.replace("#page_list_stack#", pageStackContent != null ? pageStackContent : HtmlConstants.EMPTY);
		
		response.setHeader("X-XSS-Protection", "0");
		return breadCrumbPageContent;
	}
	
	//Method created to add progressbar div on top of every page
	private String addDIVForProgressBar(String htmlDoc) throws Exception {
		String progressBarDIV = "<div id=\"progressBarDiv\"></div>";
		
		if(htmlDoc != null && htmlDoc.indexOf(progressBarDIV) == -1){
			htmlDoc = htmlDoc.replace("<form", progressBarDIV+ " <form");
		}
		return htmlDoc;
	}
	
	//Method created to process MANAGE_TESTHARNESS events
	public void processManageTestHarness(Context ctx, PageUtils utils, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		if(ctx.get(HtmlConstants.INET_METHOD) == null)
			return;
		
		String action = ctx.get(HtmlConstants.INET_METHOD) != null ? ctx.get(HtmlConstants.INET_METHOD).toString() : null;
		if (action == null)
			return;
		
		String htmlcontent = null;
		
		if(action.equalsIgnoreCase(HtmlConstants.INET_SHOW_TESTHARNESS_PAGE)){
			htmlcontent = parseManageTestHarness(ctx, "testHarness.html", "testHarness", request);
		}else if(action.equalsIgnoreCase(HtmlConstants.INET_SHOW_QUERY_TESTHARNESS_PAGE)){
			htmlcontent = parseManageTestHarness(ctx, "testQuery.html", "testQuery", request);
		}else if(action.equalsIgnoreCase(HtmlConstants.INET_SHOW_SUBMIT_QUERY_TESTHARNESS_PAGE)){
			generateTestQueryResponse(ctx, "columnListName", "dataListName", "Y");
			
			htmlcontent = parseManageTestHarness(ctx, "testQuery.html", "testQuery", request);
		}else if(action.equalsIgnoreCase(HtmlConstants.INET_SHOW_LOGS_TESTHARNESS_PAGE)){
			htmlcontent = parseManageTestHarness(ctx, "logsInformation.html", "logsInformation", request);
		}else if(action.equalsIgnoreCase(HtmlConstants.INET_SHOW_SUBMIT_LOGS_TESTHARNESS_PAGE)){
			generateLogsInformationResponse(ctx);
			
			htmlcontent = parseManageTestHarness(ctx, "logsInformation.html", "logsInformation", request);
		}else if(action.equalsIgnoreCase(HtmlConstants.INET_SHOW_DOWNLOAD_LOGS_TESTHARNESS_PAGE)){
			DocumentImpl documentImpl = new DocumentImpl();
			documentImpl.setDownloadfilepath("filepath");
			documentImpl.setFilenamefield("filename");
			documentImpl.execute(ctx);
			return;
		}else if(action.equalsIgnoreCase(HtmlConstants.INET_SHOW_INTEGRATION_TESTHARNESS_PAGE)){
			generateIntegrationResponse(ctx, request);
			
			htmlcontent = parseManageTestHarness(ctx, "testIntegration.html", "testIntegration", request);
		}else if(action.equalsIgnoreCase(HtmlConstants.INET_SHOW_SESSION_TESTHARNESS_PAGE)){
			new DataUtils().getSessionList(ctx, "sessionList_list_1");
			
			htmlcontent = parseManageTestHarness(ctx, "sessionList.html", "sessionList", request);
		}else if(action.equalsIgnoreCase("INET_SUBMIT_MAIL_TESTHARNESS_PAGE")){
			generateIntegrationResponse(ctx, request);
			
			try{
				MailSender mailSender = new MailSender();
				mailSender.setContenttype("text/plain");
				mailSender.setSubject(ctx.get("subject") != null ? ctx.get("subject").toString() : null);
				mailSender.setFrom(SystemProperties.getInstance().getString("mail.admin.address"));
				
				String toAddress = ctx.get("toAddress") != null ? ctx.get("toAddress").toString() : null;
				
				List<String> toAddList = new ArrayList<String>();
		    	toAddList.add(toAddress);
		    	mailSender.setToAddList(toAddList);
				
				mailSender.setBody(ctx.get("body") != null ? ctx.get("body").toString() : null);
				mailSender.sendMail(ctx);
				
				ctx.put("subject", null);
				ctx.put("toAddress", null);
				ctx.put("body", null);
				
				ctx.put("mailMsg", "Test Mail has been sent successfully");
				logger.debug("Test Mail has been sent successfully");
			}catch(Exception e1){
				logger.error("Unable to send mail due to error : " + e1.getMessage());
				DataUtils.populateError(ctx, "mailError", "Unable to send mail due to error : " + e1.getMessage());
			}
			
			ctx.put("isShowTestMailDiv", "Y");
			htmlcontent = parseManageTestHarness(ctx, "testIntegration.html", "testIntegration", request);
		}else if(action.equalsIgnoreCase(HtmlConstants.INET_SHOW_ERRORS_TESTHARNESS_PAGE)){
			
			htmlcontent = parseManageTestHarness(ctx, "showErrors.html", "showErrors", request);
		}else if(action.equalsIgnoreCase(HtmlConstants.INET_SHOW_SUBMIT_ERRORS_TESTHARNESS_PAGE)){
			generateErrorsResponse(ctx, request);
			
			htmlcontent = parseManageTestHarness(ctx, "showErrors.html", "showErrors", request);
		}/*else if(action.equalsIgnoreCase(HtmlConstants.INET_SHOW_STACK_TESTHARNESS_PAGE)){
			ctx.put("stackList_list_1", new DataUtils().getStackList(ctx, ctx.get("stack_page").toString()));
			
			htmlcontent = parseManageTestHarness(ctx, "stackList.html", "stackList", request);
		}else if(action.equalsIgnoreCase(HtmlConstants.INET_SHOW_AUTHXML_TESTHARNESS_PAGE)){
			
			htmlcontent = parseManageTestHarness(ctx, "authxml.html", "authxml", request);
		}else if(action.equalsIgnoreCase(HtmlConstants.INET_SHOW_SUBMIT_AUTHXML_TESTHARNESS_PAGE)){
			generateAuthXML(ctx);
			
			htmlcontent = parseManageTestHarness(ctx, "authxml.html", "authxml", request);
		}*/
		
		if (htmlcontent == null) {
			logger.error("No test harness page was generated for action: " + action);
			return;
		}

		htmlcontent = htmlcontent.replace("#dynamicContent#", ctx.get("#dynamicContent#") != null ? ctx.get("#dynamicContent#").toString() : HtmlConstants.EMPTY);
		
		htmlcontent = htmlcontent.replace("#dynamicContent_pageListStackString#", ctx.get("#dynamicContent_pageListStackString#") != null ? ctx.get("#dynamicContent_pageListStackString#").toString() : HtmlConstants.EMPTY);
		
		new PageUtils().writeResponse(response, htmlcontent, "text/html");
	}
	
	public String parseManageTestHarness(Context ctx, String templatePath, String template, HttpServletRequest request) throws Exception {
		PageUtils pageUtils = new PageUtils();
		HTML2XML htm2xml = new HTML2XML();
		htm2xml.setHtmlPath(SystemProperties.getInstance().getString("html.basedir"));
		htm2xml.convert(SystemProperties.getInstance().getString("html.basedir")+templatePath, template, ctx);
		String html = IOUtils.readFile(SystemProperties.getInstance().getString("html.basedir") + "temp.xml");

		String nextPagDocType = pageUtils.getDocType(html);

		Document document = pageUtils.getDocument(html, template);		
		HTMLGenerator htmlGenerator = new HTMLGenerator(ctx.get(Constants.INET_PROJECT_ID).toString(), template);
		
		document = htmlGenerator.parseDocument(ctx, document, template, request);
		
		String htmlDoc = HTMLUtils.beautifyHTML(document);
		htmlDoc = htmlDoc.replaceAll(HtmlConstants.SPACE, "&nbsp;");
		String htmlcontent = pageUtils.appendDOCTYPE(htmlDoc, nextPagDocType);
		document = null;
		
		return htmlcontent;
	}
	
	//Method created to generate logs information response
	public void generateLogsInformationResponse(Context ctx) throws Exception{
		try{
			String logslevel = ctx.get("logslevel") != null ? ctx.get("logslevel").toString() : "DEBUG";
			int logscount = (ctx.get("logscount") != null && !ctx.get("logscount").toString().equals(HtmlConstants.EMPTY)) ? Integer.parseInt(ctx.get("logscount").toString()) : 1000;
			String logsPath = SystemProperties.getInstance().getString("log4j.appender.file.file");
			String downloadFilesListName = "logsfiles_list_1";
			
			List list = new DataUtils().getLogsInformation(ctx, logsPath, logslevel, downloadFilesListName);
			
			StringBuffer logs = new StringBuffer();
			if(list.size() < logscount)
				logscount = list.size();
			
			for(int i=logscount; i>=0; i--){
				/*if(i >= logscount)
					break;*/
				
				logs.append(((Map)list.get(i)).get("value")+ System.getProperty("line.separator"));
			}
			
			ctx.put("logsinformation", logs);
			
			//going to generate log files list
			if(ctx.get(downloadFilesListName) != null && ctx.get(downloadFilesListName) instanceof List){
				List downloadFilesList = (List)ctx.get(downloadFilesListName);
				
				Collections.reverse(downloadFilesList);
				
				Element tableElement = new Element(HtmlConstants.TABLE);
				tableElement.setAttribute(HtmlConstants.WIDTH, "100%");
				tableElement.setAttribute(HtmlConstants.CELLPADDING, "0");
				tableElement.setAttribute(HtmlConstants.CELLSPACING, "0");
				
				for(int i=0; i<downloadFilesList.size(); i++){
					if(i>4)
						break;
					
					Map downloadMap = (Map)downloadFilesList.get(i);
					
					Element trElement = new Element(HtmlConstants.TR);
					
					Element tdElement = new Element(HtmlConstants.TD);
					tdElement.setAttribute(HtmlConstants.ID, "block_field_fieldname");
					
					Element aElement = new Element(HtmlConstants.LINK);
					String href = "javascript:managetestharness('filename|filepath','"+downloadMap.get("filename").toString()+"|"+downloadMap.get("filepath").toString()+"','INET_SHOW_DOWNLOAD_LOGS_TESTHARNESS_PAGE','MANAGE_TESTHARNESS');";
					aElement.setAttribute(HtmlConstants.HREF, href);
					//aElement.addContent(downloadMap.get("filename").toString());
					aElement.addContent("Download Log File"+(i+1));
					
					tdElement.addContent(aElement.detach());
					trElement.addContent(tdElement.detach());
					tableElement.addContent(trElement.detach());
				}
				
				ctx.put("#dynamicContent#", new XMLOutputter(Format.getPrettyFormat()).outputString(tableElement));
			}
		}catch(Exception e){
			logger.error("Unable to generate logs information due to error : " + e.getMessage());
		}
	}
	
	//Method created to generate testQuery page response		
	public void generateTestQueryResponse(Context ctx, String columnListName, String dataListName, String isDataSource) throws Exception{
		new DataUtils().testQuery(ctx, columnListName, dataListName, isDataSource);

		if(ctx.get(columnListName) != null && ctx.get(columnListName) instanceof List){
			String dynamicContent = "";
			
			org.jdom.Element tableElement = new org.jdom.Element(HtmlConstants.TABLE);
			tableElement.setAttribute(HtmlConstants.CELLPADDING, "0");
			tableElement.setAttribute(HtmlConstants.CELLSPACING, "0");
			
			//creating header row
			org.jdom.Element headerTrElement = new org.jdom.Element(HtmlConstants.TR);
			
			List<String> columnsList = (List<String>)ctx.get(columnListName);
			
			for(int i=0; i<columnsList.size(); i++){
				String header = columnsList.get(i);
				
				org.jdom.Element headerTdElement = new org.jdom.Element(HtmlConstants.TD);
				headerTdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
				headerTdElement.addContent(header);
				
				headerTrElement.addContent(headerTdElement.detach());
			}
			
			tableElement.addContent(headerTrElement.detach());
			
			//creating data row
			if(ctx.get(dataListName) != null && ctx.get(dataListName) instanceof List){
				List<Map> dataList = (List<Map>)ctx.get(dataListName);
				
				for(int i=0; i<dataList.size(); i++){
					Map dataMap = (Map)dataList.get(i);
					
					org.jdom.Element dataTrElement = new org.jdom.Element(HtmlConstants.TR);
					if(i%2==0)
						dataTrElement.setAttribute(HtmlConstants.CSS_CLASS, "list2Row");
					else
						dataTrElement.setAttribute(HtmlConstants.CSS_CLASS, "list1Row");
					
					for(int j=0; j<columnsList.size(); j++){
						String header = columnsList.get(j);
						
						org.jdom.Element dataTdElement = new org.jdom.Element(HtmlConstants.TD);
						
						if(dataMap.get(header) != null)
							dataTdElement.addContent(dataMap.get(header).toString());
						else
							dataTdElement.addContent(HtmlConstants.EMPTY);
						
						dataTrElement.addContent(dataTdElement.detach());
					}
					
					tableElement.addContent(dataTrElement.detach());
				}
			}
			
			dynamicContent = new XMLOutputter().outputString(tableElement);
			
			ctx.put("#dynamicContent#", dynamicContent);
		}
	}
	
	//Method created to generate integration response
	public void generateIntegrationResponse(Context ctx, HttpServletRequest request) throws Exception{
		List integrationList = new ArrayList();
		
		Map propertyMap = new HashMap();
		propertyMap.put("property", "Miscelleneous");
		propertyMap.put("value", "BOLD");
		propertyMap.put("status", null);
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "context.url");
		if(SystemProperties.getInstance().getProperty("context.url") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("context.url"));
			propertyMap.put("status", getUrlResponse(SystemProperties.getInstance().getString("context.url")));
		}else{
			propertyMap.put("value", "context.url");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "appl.error.forward.url");
		if(SystemProperties.getInstance().getProperty("appl.error.forward.url") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("appl.error.forward.url"));
			propertyMap.put("status", getUrlResponse(SystemProperties.getInstance().getString("appl.error.forward.url")));
		}else{
			propertyMap.put("value", "appl.error.forward.url");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "appl."+ctx.getProject()+".db.datasource");
		if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".db.datasource") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("appl."+ctx.getProject()+".db.datasource"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "appl."+ctx.getProject()+".db.datasource");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "Logs");
		propertyMap.put("value", "BOLD");
		propertyMap.put("status", null);
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "log4j.rootLogger");
		if(SystemProperties.getInstance().getProperty("log4j.rootLogger") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("log4j.rootLogger"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "log4j.rootLogger");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "log4j.appender.file.file");
		if(SystemProperties.getInstance().getProperty("log4j.appender.file.file") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("log4j.appender.file.file"));
			propertyMap.put("status", getFolderResponse(SystemProperties.getInstance().getString("log4j.appender.file.file")));
		}else{
			propertyMap.put("value", "log4j.appender.file.file");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "bgcheck.enabled");
		if(SystemProperties.getInstance().getProperty("bgcheck.enabled") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("bgcheck.enabled"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "bgcheck.enabled");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "appl."+ctx.getProject()+".bgone.URL");
		if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".bgone.URL") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("appl."+ctx.getProject()+".bgone.URL"));
			propertyMap.put("status", getUrlResponse(SystemProperties.getInstance().getString("appl."+ctx.getProject()+".bgone.URL")));
		}else{
			propertyMap.put("value", "appl."+ctx.getProject()+".bgone.URL");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "ESign");
		propertyMap.put("value", "BOLD");
		propertyMap.put("status", null);
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "esign.enabled");
		if(SystemProperties.getInstance().getProperty("esign.enabled") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("esign.enabled"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "esign.enabled");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "esign.ws.url");
		if(SystemProperties.getInstance().getProperty("esign.ws.url") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("esign.ws.url"));
			propertyMap.put("status", getUrlResponse(SystemProperties.getInstance().getString("esign.ws.url")));
		}else{
			propertyMap.put("value", "esign.ws.url");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "esign.userName");
		if(SystemProperties.getInstance().getProperty("esign.userName") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("esign.userName"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "esign.userName");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "esign.password");
		if(SystemProperties.getInstance().getProperty("esign.password") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("esign.password"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "esign.password");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "NIPR");
		propertyMap.put("value", "BOLD");
		propertyMap.put("status", null);
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "appl."+ctx.getProject()+".niprone.URL");
		if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".niprone.URL") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("appl."+ctx.getProject()+".niprone.URL"));
			propertyMap.put("status", getUrlResponse(SystemProperties.getInstance().getString("appl."+ctx.getProject()+".niprone.URL")));
		}else{
			propertyMap.put("value", "appl."+ctx.getProject()+".niprone.URL");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "appl."+ctx.getProject()+".niprone.username");
		if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".niprone.username") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("appl."+ctx.getProject()+".niprone.username"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "appl."+ctx.getProject()+".niprone.username");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "appl."+ctx.getProject()+".niprone.password");
		if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".niprone.password") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("appl."+ctx.getProject()+".niprone.password"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "appl."+ctx.getProject()+".niprone.password");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		if(SystemProperties.getInstance().subset("appl."+ctx.getProject()+".group") != null){
			propertyMap = new HashMap();
			propertyMap.put("property", "Roles");
			propertyMap.put("value", "BOLD");
			propertyMap.put("status", null);
			integrationList.add(propertyMap);
			
			Iterator<String> itr = SystemProperties.getInstance().subset("appl."+ctx.getProject()+".group").getKeys();
			while(itr.hasNext()){
				String key = itr.next();
				
				propertyMap = new HashMap();
				propertyMap.put("property", "appl."+ctx.getProject()+".group."+key);
				if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".group."+key) != null){
					propertyMap.put("value", SystemProperties.getInstance().getString("appl."+ctx.getProject()+".group."+key));
					propertyMap.put("status", "Exists");
				}else{
					propertyMap.put("value", "appl."+ctx.getProject()+".group."+key);
					propertyMap.put("status", "Not Exists");
				}
				integrationList.add(propertyMap);
			}
		}
		
		/*propertyMap = new HashMap();
		propertyMap.put("property", "Roles");
		propertyMap.put("value", "BOLD");
		propertyMap.put("status", null);
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "appl."+ctx.getProject()+".group.carrieradmin");
		if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".group.carrieradmin") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("appl."+ctx.getProject()+".group.carrieradmin"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "appl."+ctx.getProject()+".group.carrieradmin");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "appl."+ctx.getProject()+".group.marketingassistant");
		if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".group.marketingassistant") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("appl."+ctx.getProject()+".group.marketingassistant"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "appl."+ctx.getProject()+".group.marketingassistant");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "appl."+ctx.getProject()+".group.readonly");
		if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".group.readonly") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("appl."+ctx.getProject()+".group.readonly"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "appl."+ctx.getProject()+".group.readonly");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "appl."+ctx.getProject()+".group.treasury");
		if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".group.treasury") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("appl."+ctx.getProject()+".group.treasury"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "appl."+ctx.getProject()+".group.treasury");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "appl."+ctx.getProject()+".group.vpmarketing");
		if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".group.vpmarketing") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("appl."+ctx.getProject()+".group.vpmarketing"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "appl."+ctx.getProject()+".group.vpmarketing");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "appl."+ctx.getProject()+".group.compensationanalyst");
		if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".group.compensationanalyst") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("appl."+ctx.getProject()+".group.compensationanalyst"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "appl."+ctx.getProject()+".group.compensationanalyst");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "appl."+ctx.getProject()+".group.marketingrep");
		if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".group.marketingrep") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("appl."+ctx.getProject()+".group.marketingrep"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "appl."+ctx.getProject()+".group.marketingrep");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "appl."+ctx.getProject()+".group.salesdirector");
		if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".group.salesdirector") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("appl."+ctx.getProject()+".group.salesdirector"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "appl."+ctx.getProject()+".group.salesdirector");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "appl."+ctx.getProject()+".group.accountantadmin");
		if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".group.accountantadmin") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("appl."+ctx.getProject()+".group.accountantadmin"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "appl."+ctx.getProject()+".group.accountantadmin");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "appl."+ctx.getProject()+".group.it");
		if(SystemProperties.getInstance().getProperty("appl."+ctx.getProject()+".group.it") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("appl."+ctx.getProject()+".group.it"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "appl."+ctx.getProject()+".group.it");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);*/
		
		propertyMap = new HashMap();
		propertyMap.put("property", "DMS");
		propertyMap.put("value", "BOLD");
		propertyMap.put("status", null);
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "dms.enabled");
		if(SystemProperties.getInstance().getProperty("dms.enabled") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("dms.enabled"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "dms.enabled");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "dms.url");
		if(SystemProperties.getInstance().getProperty("dms.url") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("dms.url"));
			propertyMap.put("status", getFolderResponse(SystemProperties.getInstance().getString("dms.url")));
		}else{
			propertyMap.put("value", "dms.url");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "Mail");
		propertyMap.put("value", "BOLD");
		propertyMap.put("status", "MAILLINK");
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "mail.host.server");
		if(SystemProperties.getInstance().getProperty("mail.host.server") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("mail.host.server"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "mail.host.server");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "mail.port");
		if(SystemProperties.getInstance().getProperty("mail.port") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("mail.port"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "mail.port");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "mail.debug");
		if(SystemProperties.getInstance().getProperty("mail.debug") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("mail.debug"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "mail.debug");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "mail.admin.address");
		if(SystemProperties.getInstance().getProperty("mail.admin.address") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("mail.admin.address"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "mail.admin.address");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "mail.admin.password");
		if(SystemProperties.getInstance().getProperty("mail.admin.password") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("mail.admin.password"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "mail.admin.password");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		propertyMap = new HashMap();
		propertyMap.put("property", "mail.ssl.required");
		if(SystemProperties.getInstance().getProperty("mail.ssl.required") != null){
			propertyMap.put("value", SystemProperties.getInstance().getString("mail.ssl.required"));
			propertyMap.put("status", "Exists");
		}else{
			propertyMap.put("value", "mail.ssl.required");
			propertyMap.put("status", "Not Exists");
		}
		integrationList.add(propertyMap);
		
		//ctx.put("testIntegration_list_1", integrationList);
		
		String dynamicContent = null;
		
		if(integrationList != null && integrationList.size() > 0){
			Element tableElement = new Element(HtmlConstants.TABLE);
			tableElement.setAttribute(HtmlConstants.ID, "testIntegration_list_1");
			tableElement.setAttribute(HtmlConstants.WIDTH, "100%");
			tableElement.setAttribute(HtmlConstants.CELLSPACING, "0");
			tableElement.setAttribute(HtmlConstants.CELLPADDING, "0");
			
			Element trElement = new Element(HtmlConstants.TR);
			trElement.setAttribute(HtmlConstants.ID, "testIntegration_list_header_1");
			
			Element tdElement = new Element(HtmlConstants.TD);
			tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
			tdElement.setAttribute(HtmlConstants.ID, "block_field_property");
			tdElement.addContent("Property");
			trElement.addContent(tdElement.detach());
			
			tdElement = new Element(HtmlConstants.TD);
			tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
			tdElement.setAttribute(HtmlConstants.ID, "block_field_value");
			tdElement.addContent("Value");
			trElement.addContent(tdElement.detach());
			
			tdElement = new Element(HtmlConstants.TD);
			tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
			tdElement.setAttribute(HtmlConstants.ID, "block_field_status");
			tdElement.addContent("Status");
			trElement.addContent(tdElement.detach());
			
			tdElement = new Element(HtmlConstants.TD);
			tdElement.setAttribute(HtmlConstants.CSS_CLASS, "HeaderStyle");
			tdElement.setAttribute(HtmlConstants.ID, "block_field_test_row_link");
			trElement.addContent(tdElement.detach());
			
			tableElement.addContent(trElement.detach());
			
			for(int i=0; i<integrationList.size(); i++){
				Map map = (Map)integrationList.get(i);
				
				trElement = new Element(HtmlConstants.TR);
				
				if(i%2==0)
					trElement.setAttribute(HtmlConstants.CSS_CLASS, "listRow2CSS");
				else
					trElement.setAttribute(HtmlConstants.CSS_CLASS, "listRow1CSS");
				
				String value = map.get("value") != null ? map.get("value").toString() : HtmlConstants.EMPTY;
				String status = map.get("status") != null ? map.get("status").toString() : HtmlConstants.EMPTY;
				
				tdElement = new Element(HtmlConstants.TD);
				tdElement.setAttribute(HtmlConstants.ID, "block_field_property");
				tdElement.addContent(map.get("property") != null ? map.get("property").toString() : HtmlConstants.EMPTY);
				
				if(value.equals("BOLD"))
					tdElement.setAttribute(HtmlConstants.STYLE, "font-weight:bold;padding-top:15px;");
				if(status.equals("Not Exists") || status.equals("Down"))
					tdElement.setAttribute(HtmlConstants.STYLE, "color:red");
				
				trElement.addContent(tdElement.detach());
				
				tdElement = new Element(HtmlConstants.TD);
				tdElement.setAttribute(HtmlConstants.ID, "block_field_value");
				
				if(value.equals("BOLD"))
					tdElement.setAttribute(HtmlConstants.STYLE, "font-weight:bold;");
				else
					tdElement.addContent(value);
				
				if(status.equals("Not Exists") || status.equals("Down"))
					tdElement.setAttribute(HtmlConstants.STYLE, "color:red");
				
				trElement.addContent(tdElement.detach());
				
				tdElement = new Element(HtmlConstants.TD);
				tdElement.setAttribute(HtmlConstants.ID, "block_field_status");
				
				if(status.equals("MAILLINK")){
					Element aElement = new Element(HtmlConstants.LINK);
					aElement.setAttribute(HtmlConstants.HREF, "#");
					aElement.setAttribute(HtmlConstants.ONCLICK, "javascript:showTestMailDiv();");
					aElement.addContent("Test Mail");
					tdElement.addContent(aElement.detach());
				}else
					tdElement.addContent(status);
				
				if(value.equals("BOLD"))
					tdElement.setAttribute(HtmlConstants.STYLE, "font-weight:bold;");
				if(status.equals("Not Exists") || status.equals("Down"))
					tdElement.setAttribute(HtmlConstants.STYLE, "color:red");
				
				trElement.addContent(tdElement.detach());
				
				tableElement.addContent(trElement.detach());
			}
			
			dynamicContent = new XMLOutputter(Format.getPrettyFormat()).outputString(tableElement);
			
			ctx.put("#dynamicContent#", dynamicContent);
		}
	}
	
	private String getUrlResponse(String urlStr) throws Exception{
		try{
			URL url = new URL(urlStr);
			
			HttpURLConnection hc = (HttpURLConnection)url.openConnection();
			hc.connect();
			
			return hc.getResponseCode() == 200 ? "On" : "Down";
		}catch(Exception e){
			logger.error("Error while connecting to url : " + e.getMessage());
		}
		
		return "Down";
	}
	
	private String getFolderResponse(String path) throws Exception{
		try{
			path = path.replace("//", File.separator).replace("\\", File.separator);
			return new File(path).exists() ? "Exists" : "Not Exists";
		}catch(Exception e){
			logger.error("Error while connecting to url : " + e.getMessage());
		}
		
		return "Not Exists";
	}
	
	private void generateErrorsResponse(Context ctx, HttpServletRequest req) throws Exception{
		try{
			if(ctx.get("LogId") == null || ctx.get("LogId").toString().equals(HtmlConstants.EMPTY))
				new DataUtils().getErrorInformationById(ctx, "columnListName", "errors_list_1", "Y", null);
			else
				new DataUtils().getErrorInformationById(ctx, "columnListName", "errors_list_1", "Y", ctx.get("LogId").toString());
		}catch(Exception e){
			logger.error("Error while generating errors response : " + e.getMessage());
		}
	}
	
	/*private void generateAuthXML(Context ctx) throws Exception{
		try{
			if(ctx.get("user_id") == null || ctx.get("user_id").toString().equals(HtmlConstants.EMPTY)){
				DataUtils.populateError(ctx, "user_id", "Please enter User Id");
				return;
			}
				
			String xml = null;
			if(ctx.get("user_id") == null || ctx.get("user_id").toString().equals(HtmlConstants.EMPTY))
				xml = new DataUtils().getAuthXml(ctx, ctx.get("user_id").toString());
			
			ctx.put("xml", xml);
		}catch(Exception e){
			logger.error("Error while generating errors response : " + e.getMessage());
		}
	}*/
}
