package com.manage.process;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;

import com.manage.util.PageUtils;
import com.processor.XSLTProcessor;
import com.util.Constants;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.SystemProperties;

public class PIMAjaxProcessorImpl implements PIMAjaxProcessorInterface{

	public void validateAjaxComponent(Context ctx, PageUtils utils, HttpServletRequest request, 
				HttpServletResponse response)throws Exception {
		
		String errorHtml = utils.validateAjaxComponent(ctx, request);
		if(errorHtml != null){
			utils.writeResponse(response, errorHtml, "");
		}
	}
	
	public void populateNextComponentpage(Context ctx, PageUtils utils, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		
		if(null != request.getParameter(HtmlConstants.INET_AJAX_PAGE)){
			utils.populateAjaxpage(request,ctx);
		}
		
		String next_page = utils.populateNextComponentpage(ctx, request);
		utils.populateSession(request, ctx);
		
		if(ctx.get(Constants.INET_FORM_DIRTY) != null && ctx.get(Constants.INET_FORM_DIRTY).equals("Y")){
			//going to create ajaxerrors html
			String errorHtml = utils.createAjaxErrorsHTML(ctx, request);
			if(errorHtml != null){
				utils.writeResponse(response, errorHtml, "");
			}
			return;
		}
		
		String htmlDoc = null;
//		
		if(SystemProperties.getInstance().getProperty("appl." + ctx.getProject() + ".xslt.processor") != null && 
				SystemProperties.getInstance().getProperty("appl." + ctx.getProject() + ".xslt.processor").equals("Y")){
			XSLTProcessor xsltProcessor = new XSLTProcessor();
			String dataXmlPath = SystemProperties.getInstance().getProperty("appl.home.dir").toString() + "contextdata.xml";
			ctx.put("next_page", next_page);
			xsltProcessor.populateDataXml(ctx, dataXmlPath, false);
			htmlDoc = xsltProcessor.process(request, response, next_page, true, ctx, null, false, dataXmlPath);
		}else{
			Document document = null;
			//added code to skip parsing of entire page in case of suggestion input box 
			if(ctx.get(HtmlConstants.DATA_DISPLAY_ID) != null &&
					ctx.get(HtmlConstants.DATA_DISPLAY_ID).toString().startsWith("ajaxxml")){
				//do nothing
			}else
				document = utils.parseHtmlDocument(ctx,next_page,HtmlConstants.INET_AJAX_PAGE,request);
			
			htmlDoc = utils.getElementFromDoc(document, ctx);
			htmlDoc = appendJSessionIdToCapcha(request, htmlDoc); //To append JSESSIONID to JCapcha
			document = null;
		}
		
		//Writing to the response object.
		if(ctx.get(HtmlConstants.DATA_DISPLAY_ID) != null &&
				ctx.get(HtmlConstants.DATA_DISPLAY_ID).toString().startsWith("ajaxxml")){
			utils.writeResponse(response, htmlDoc, "text/xml");
		}else
			utils.writeResponse(response, htmlDoc, "text/html");
	}
	
	private String appendJSessionIdToCapcha(HttpServletRequest req, String htmlDoc) throws Exception {
		if(htmlDoc != null && htmlDoc.contains("jcaptcha.jpg")){
			htmlDoc = htmlDoc.replaceAll("jcaptcha.jpg", "jcaptcha.jpg;jsessionid="+req.getSession().getId()+"?rnum="+new Random().nextLong());
		}
		
		return htmlDoc;
	}
}
