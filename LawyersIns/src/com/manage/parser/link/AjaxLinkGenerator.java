package com.manage.parser.link;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.jdom.Element;

import com.manage.framework.ProcessAction;
import com.manage.parser.ControlGenerator;
import com.manage.parser.ParseUtil;
import com.manage.util.PageUtils;
import com.util.Constants;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.InetLogger;
import com.util.StringUtils;

public class AjaxLinkGenerator extends ControlGenerator {
	
	private static InetLogger logger = InetLogger.getInetLogger(AjaxLinkGenerator.class);
	private static String  ONMOUSE_OVER ="onmouseover";
	
	public void populateRightclickmemuData(Element sibling, Map record,Context ctx, String href )  throws Exception{
		//populateRightclickmemuData(  sibling,   record,  ctx,   href,  null );
	}
	private void populateRightclickmemuData(Element sibling, Map record,Context ctx, String href,Element rootElement )  throws Exception 
	{		
		StringBuffer buffer = new StringBuffer();
		String mouseover = sibling.getAttributeValue(ONMOUSE_OVER);
		String field =  mouseover.substring (mouseover.indexOf("('")+2, mouseover.indexOf("')"));
		
		if(field==null)
		{	
			logger.debug("fields does not contain on mouserover "+ mouseover);
		}
		if(field!=null)
		{
			String[] fields = field.split(";");	
			 
			buffer.append(Constants.DELIMITER);
			for(int i=0;i<fields.length;i++)
			{
				buffer.append(fields[i]);
				buffer.append(Constants.DELIMITER);	
				buffer.append(record.get(fields[i])); 
				buffer.append(Constants.DELIMITER);
			}
		}
		 	 
		sibling.setAttribute(ONMOUSE_OVER,"displaymessage('"+ buffer.toString()+"')");
	}
	
	private void getAjaxDivPositionID(Context ctx, Element sibling,  Map data,List childrenlist) throws Exception{		 
		if(childrenlist != null){
			for(int i=0; i<childrenlist.size(); i++){	
				 Element childelement = (Element)childrenlist.get(i);
				 if(null != childelement.getAttributeValue(HtmlConstants.ID)){	
					String divpostionid = childelement.getAttributeValue(HtmlConstants.ID);
					
					if(divpostionid.contains(HtmlConstants.DIV_AJAX_POSTION) || divpostionid.contains("attachmentDivContentStart") ||
							divpostionid.contains("attachmentDivContentEnd") || divpostionid.contains("attachmentDiv")){	
						
						Object valueobj = getDIVFieldData(divpostionid,data);
						String fieldValue = ParseUtil.getValueFromObject(valueobj, null, null);
						  
						if(fieldValue != null){
							String searchChar = "_";
							
							if(divpostionid.contains(":"))
								searchChar = ":";
							
							int indexlast = divpostionid.lastIndexOf(searchChar);
							divpostionid = divpostionid.substring(0,indexlast);
							
							try{
								//added code to show accordian page by default based on expandall flag in context
								if(ctx.get(HtmlConstants.ISSHOWRIGHTCLICKPAGEALL) != null && ctx.get(HtmlConstants.ISSHOWRIGHTCLICKPAGEALL).toString().equalsIgnoreCase("expand")){
									String rightClickPage = ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGE) != null ? ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGE).toString() : HtmlConstants.EMPTY;
									String rightClickPageFields = ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEFIELDS) != null ? ","+ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEFIELDS).toString()+"," : null;
									String rightClickPageId = childelement.getAttributeValue(HtmlConstants.ID);
									String showRightClickPageId = ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEID) != null ? ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEID).toString() : HtmlConstants.EMPTY;
									String isShowRightClickPage = HtmlConstants.EMPTY;//ctx.get(HtmlConstants.isShowRightClickPage) != null ? ctx.get(HtmlConstants.isShowRightClickPage).toString() : HtmlConstants.EMPTY;
									String collapserightclickpageid = ctx.get("collapserightclickpageid") != null ? ctx.get("collapserightclickpageid").toString() : HtmlConstants.EMPTY;
									
									if((","+collapserightclickpageid+",").contains(","+divpostionid+searchChar+fieldValue+","))
										isShowRightClickPage = "N";
									
									//if(ctx.get(showRightClickPageId) != null && isShowRightClickPage.equals("N") && fieldValue.equals(ctx.get(showRightClickPageId).toString()))
									if(isShowRightClickPage.equals("N")){
										childelement.setAttribute(HtmlConstants.ID, divpostionid+searchChar+fieldValue);
										continue;
									}
									
									if(rightClickPageId.contains(":")){
										rightClickPageId = rightClickPageId.substring(rightClickPageId.lastIndexOf(":")+1, rightClickPageId.length());
									}
									
									Context newContext = new Context();
									newContext.setProject(ctx.getProject());
									newContext.putAll(ctx);
									newContext.putAll(data);
									
									newContext.put(rightClickPageId, fieldValue);
									
									//going to execute view of right click page
									new ProcessAction().processRoute(newContext, rightClickPage);
					 				
					 				String content = new PageUtils().parseHtmlDocument(newContext, rightClickPage, (HttpServletRequest)ctx.get(HtmlConstants.DOCUMENTREQUEST));
					 				Element rootElement = new PageUtils().getDocument(content, rightClickPage).getRootElement(); 
					 				
					 				if(rightClickPageFields != null)
					 					parseRightClickPage(ctx, rootElement, rightClickPageFields, fieldValue);
					 				
					 				childelement.addContent(rootElement.getChild(HtmlConstants.BODY).getChild(HtmlConstants.FORM).getChild(HtmlConstants.DIV).detach());
								}
							}catch(Exception e){
								//e.printStackTrace();
								logger.error("Unable to generate expandall/collapseall rightclickmenu due to error : " + e.getMessage());
							}
							
							childelement.setAttribute(HtmlConstants.ID, divpostionid+searchChar+fieldValue);
						}
						 
						//break;
					 }
				 }	
				
				 getAjaxDivPositionID(ctx, childelement, data, childelement.getChildren());
			}
		}
	}
	
	private Object getDIVFieldData(String divpostionid, Map recorddata) throws Exception {
		
		String fieldname = "";
		  
		if(divpostionid.contains(":"))
			fieldname =  divpostionid.substring(divpostionid.lastIndexOf(":")+1, divpostionid.length());
		else
			fieldname =  divpostionid.substring(divpostionid.lastIndexOf("_")+1, divpostionid.length());
		if(fieldname==null || "".equals( fieldname)){
			logger.debug(divpostionid +"does not have");
		}
		  Object  valueobj =  recorddata.get(fieldname);
		  return valueobj;
	}
	
	public void getDivPostionList(Context ctx, List list, Map data, Element sibling) throws Exception {
		
		//Element newRecord = null;		
		if(data == null)
			return;		
 
		//newRecord = ParseUtil.createCopyForElement((Element)sibling.clone());
		getAjaxDivPositionID(ctx, sibling, data,sibling.getChildren());
		//list.add(newRecord.detach());
	}
	
	//Method created to parse accordian page
	private void parseRightClickPage(Context ctx, Element element, String rightClickPageFields, String rowid) throws Exception{
		if(element.getChildren() != null && element.getChildren().size() > 0){
			for(int i=0; i<element.getChildren().size(); i++){
				Object obj = element.getChildren().get(i);
				
				if(obj != null && obj instanceof Element){
					Element ele = (Element)obj;
					
					if(ele.getAttributeValue(HtmlConstants.ID) != null ||
							ele.getAttributeValue(HtmlConstants.NAME) != null){
						
						if(ele.getAttributeValue(HtmlConstants.NAME) != null){
							String name = ele.getAttributeValue(HtmlConstants.NAME);
						
							if(StringUtils.isNotBlank(name) && rightClickPageFields.contains(","+name+",")){
								ele.setAttribute(HtmlConstants.NAME, name+"_"+rowid);
							}else if(StringUtils.isNotBlank(name) && name.contains("_") && StringUtils.isNumeric(name.substring(name.lastIndexOf("_")+1, name.length()))){
								if(rightClickPageFields.contains(","+name.substring(0, name.lastIndexOf("_")))){
									ele.setAttribute(HtmlConstants.NAME, name+"_"+rowid);
									
									String newrightClickPageFields = ctx.get(HtmlConstants.SHOWRIGHTCLICKPAGEFIELDS).toString();
									newrightClickPageFields = newrightClickPageFields + "," + name;
									
									ctx.put(HtmlConstants.SHOWRIGHTCLICKPAGEFIELDS, newrightClickPageFields);
								}
							}
						}
						
						if(ele.getAttributeValue(HtmlConstants.ID) != null){
							String id = ele.getAttributeValue(HtmlConstants.ID);
							
							ele.setAttribute(HtmlConstants.ID, id+"_"+rowid);
						}
						
						if(ele.getAttributeValue(HtmlConstants.ONCLICK) != null){
							String onclick = ele.getAttributeValue(HtmlConstants.ONCLICK);
							
							StringTokenizer tokens = new StringTokenizer(rightClickPageFields, ",");
							while(tokens.hasMoreTokens()){
								String token = tokens.nextToken();
								
								if(rightClickPageFields.contains(","+token+","))
									onclick = onclick.replace(token, token+"_"+rowid);
							}
							
							ele.setAttribute(HtmlConstants.ONCLICK, onclick);
						}
					}
					
					//going to override showRightClickPageFields dynaValue in links
					if(ele.getAttributeValue(HtmlConstants.HREF) != null && !ele.getAttributeValue(HtmlConstants.HREF).equals(HtmlConstants.EMPTY)){
						String href = ele.getAttributeValue(HtmlConstants.HREF);
						
						if(href.startsWith("javascript:managesubmitformajax(")){
							String first = href.substring(0, href.indexOf("javascript:managesubmitformajax(")+32);
							String last = href.substring(href.lastIndexOf(")"), href.length());
							
							href = href.substring(href.indexOf("javascript:managesubmitformajax(")+32, href.lastIndexOf(")"));
							
							String form = href.substring(0, href.indexOf("document.forms[0],")+18);
							
							href = href.substring(href.indexOf("document.forms[0],")+18, href.length());
							
							String hrefs[] = href.split("','"); 
							logger.debug(href);
							
							String action = hrefs[0];
							String dynaKeys = hrefs[1];
							
							String dynaValues = hrefs[2];
							List dynaValuesTokensList = new ArrayList();
							
							StringTokenizer dynaValuesTokens = new StringTokenizer(dynaValues, "|");
							while(dynaValuesTokens.hasMoreTokens()){
								String token = dynaValuesTokens.nextToken();
								
								dynaValuesTokensList.add(token);
							}
							
							String[] dynaValuesArray = new String[dynaValuesTokensList.size()];
							
							for(int j=0; j<dynaValuesTokensList.size(); j++){
								dynaValuesArray[j] = (String)dynaValuesTokensList.get(j);
							}
							
							int j=0;
							dynaValues = null;
							StringTokenizer tokens = new StringTokenizer(dynaKeys, "|");
							while(tokens.hasMoreTokens()){
								String token = tokens.nextToken();
								
								String value = dynaValuesArray[j];
								
								if(token.equals("showRightClickPageFields")){
									value = ctx.get(token).toString();
								}
								
								dynaValues = dynaValues == null ? value : dynaValues + "|" + value;
									
								j++;
							}
							
							String pageName = hrefs[3];
							String divId = hrefs[4];
							
							href = first + form + action + "','" + dynaKeys + "','" + dynaValues + "','" + pageName + "','" + divId + last;
							
							ele.setAttribute(HtmlConstants.HREF, href);
						}
					}
					
					parseRightClickPage(ctx, ele, rightClickPageFields, rowid);
				}
			}
		}
	}
	
}
