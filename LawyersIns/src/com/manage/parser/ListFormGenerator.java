package com.manage.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.jdom.Attribute;
import org.jdom.Element;

import com.manage.managebusinessrules.rules.RuleImpl;
import com.manage.managebusinessrules.rules.RulesResources;
import com.manage.managecomponent.components.ComponentImpl;
import com.manage.managecomponent.processflow.BlockImpl;
import com.manage.managecomponent.processflow.PagecomponentImpl;
import com.manage.managemetadata.metadata.FieldImpl;
import com.manage.managemetadata.metadata.MetaDataResources;
import com.manage.managemetadata.security.SecurityResources;
import com.manage.parser.link.AjaxLinkGenerator;
import com.manage.parser.link.RowLinkGenerator;
import com.manage.parser.link.SortingLinkGenerator;
import com.manage.parser.listform.ListFormInputControlParser;
import com.manage.parser.listform.ListFormLabelControlParser;
import com.manage.parser.listform.ListFormSelectControlParser;
import com.manage.parser.listform.ListFormTextAreaControlParser;
import com.manage.util.PageUtils;
import com.manage.util.PaginationUtils;
import com.util.Context;
import com.util.IContext;
import com.util.InetLogger;
import com.util.MapComparator;
import com.util.SystemProperties;

public class ListFormGenerator
  extends ControlGenerator
{
  private static InetLogger logger = InetLogger.getInetLogger(ListFormGenerator.class);
  protected List<String> fieldNames = new ArrayList();
  protected List<Element> resList = new ArrayList();
  protected PagecomponentImpl pagecomponentImpl;
  protected ComponentImpl componentImpl;
  protected static final String DATA_LIST = "datalist";
  protected static final String DEFAULT_RECORDS_SIZE = "10";
  protected int index;
  protected static final String BLOCK_FIELD_SORTING = "sorting:";
  protected static final String BLOCK_FIELD_SORTING_NEW = "sorting(";
  protected static final String NORMAL_SORTING = "N";
  protected static final String AJAX_SORTING = "A";
  protected String listFirstRowCSS = "listRow1CSS";
  protected String listSecondRowCSS = "listRow2CSS";
  protected String fixedHeaderForMultipleCols = null;
  private Element dynaColumnsElement = null;
  
  public ListFormGenerator() {}
  
  public ListFormGenerator(MetaDataResources metadataResources, PagecomponentImpl pagecomponentImpl, ComponentImpl componentImpl)
  {
    super(metadataResources);
    this.pagecomponentImpl = pagecomponentImpl;
    this.componentImpl = componentImpl;
  }
  
  public void parseList(Context ctx, Element element, List children, List currentList, boolean readOnlyResourse, String ajax_append_field_value, HttpServletRequest request, String pageName)
    throws Exception
  {
    String id = "";
    List data = null;
    if (element.getAttributeValue("id") != null) {
      id = element.getAttributeValue("id");
    }
    if ((ctx.containsKey(id)) && 
      ((ctx.get(id) instanceof List)))
    {
      data = (List)ctx.get(id);
      ctx.put("datalist", id);
    }
    if (this.blockImpl == null)
    {
      this.blockImpl = this.pagecomponentImpl.getBlock(id);
      if (this.blockImpl == null) {
        logger.debug(ctx, this.pagecomponentImpl.getName() + " do not have block " + id);
      }
    }
    if (data == null) {
      data = currentList;
    }
    if (children != null) {
      for (int i = 0; i < children.size(); i++)
      {
        boolean detachFlag = false;
        Element sibling = (Element)children.get(i);
        String type = sibling.getName();
        
        int accessType = new SecurityParser().getAccessTypeBySecurity(ctx, sibling, this.componentImpl.getName());
        if (SecurityParser.isHideResource(accessType))
        {
          sibling.detach();
          i--;
        }
        else if (processElementForHide(sibling, ctx, pageName))
        {
          i--;
        }
        else
        {
          if (SecurityParser.checkForOverideSecurity(ctx, sibling, this.componentImpl.getName())) {
            readOnlyResourse = false;
          }
          readOnlyResourse = SecurityParser.isReadOnlyResource(accessType, readOnlyResourse);
          
          ElementProcessor ep = new ElementProcessor(this.metadataResources, this.pagecomponentImpl, this.componentImpl);
          if (ep.processElement(sibling, ctx, null, null, request))
          {
            i--;
          }
          else
          {
            if (sibling.getAttribute("id") != null)
            {
              String child_Id = sibling.getAttributeValue("id");
              if (child_Id.contains("list_grid"))
              {
                List templist = sibling.getChildren();
                for (int m = 0; m < templist.size(); m++)
                {
                  Element rest = (Element)templist.get(m);
                  Element restCopy = null;
                  if (rest.getAttributeValue("id") == null)
                  {
                    m--;
                    restCopy = ParseUtil.createCopyForElement((Element)rest.clone());
                    this.resList.add(restCopy);
                    sibling.removeContent(rest.detach());
                  }
                }
              }
              else if (child_Id.contains("list_header_"))
              {
                this.fieldNames = new ArrayList();
                processHeader(sibling, this.fieldNames);
                if ((child_Id.contains("dynamiccolumns")) && 
                  (ctx.get(id) != null) && ((ctx.get(id) instanceof List)))
                {
                  getHeaderElementForDynamicColumns(sibling);
                  Element dynaHeaderElement = this.dynaColumnsElement;
                  this.dynaColumnsElement = null;
                  if (dynaHeaderElement != null)
                  {
                    List attrList = new ArrayList();
                    if ((dynaHeaderElement.getAttributes() != null) && (dynaHeaderElement.getAttributes().size() > 0)) {
                      attrList = dynaHeaderElement.getAttributes();
                    }
                    String tdId = dynaHeaderElement.getAttributeValue("id");
                    
                    int index = sibling.indexOf(dynaHeaderElement);
                    
                    sibling.removeContent(dynaHeaderElement);
                    sibling.removeContent(index);
                    
                    List list = (List)ctx.get(id);
                    if ((list != null) && (list.size() > 0))
                    {
                      Map dynamicHeadersMap = (Map)list.get(list.size() - 1);
                      Set keySet = dynamicHeadersMap.keySet();
                      if ((keySet != null) && (keySet.size() > 0))
                      {
                        Iterator<String> itr = keySet.iterator();
                        String dynamicHeaders = (String)itr.next();
                        StringTokenizer tokens = new StringTokenizer(dynamicHeadersMap.get(dynamicHeaders).toString(), "|");
                        int count = 0;
                        int innerIndex = index;
                        while (tokens.hasMoreTokens())
                        {
                          String header = tokens.nextToken();
                          String header1 = header.substring(0, header.indexOf("="));
                          String header2 = header.substring(header.indexOf("=") + 1, header.length());
                          
                          String noOfDynaColumnsForOneHeader = null;
                          if ((tdId != null) && (tdId.lastIndexOf("dynamiccolumns") + 14 != tdId.length())) {
                            noOfDynaColumnsForOneHeader = tdId.substring(tdId.lastIndexOf("dynamiccolumns") + 14, tdId.length());
                          } else {
                            noOfDynaColumnsForOneHeader = "1";
                          }
                          try
                          {
                            int numberOfColsPerHeader = Integer.parseInt(noOfDynaColumnsForOneHeader);
                            if (count != 0)
                            {
                              innerIndex++;innerIndex = innerIndex;
                            }
                            for (int j = 0; j < numberOfColsPerHeader; j++) {
                              if (numberOfColsPerHeader == 1) {
                                this.fieldNames.add(header1.replace("block_field_", ""));
                              } else {
                                this.fieldNames.add(header1.replace("block_field_", "") + "_" + j);
                              }
                            }
                            dynaHeaderElement = new Element("td");
                            for (int k = 0; k < attrList.size(); k++)
                            {
                              Attribute attr = (Attribute)attrList.get(k);
                              
                              Attribute newAttr = new Attribute(attr.getName(), attr.getValue());
                              dynaHeaderElement.setAttribute(newAttr);
                            }
                            dynaHeaderElement.setAttribute("id", header1);
                            dynaHeaderElement.setText(header2);
                            
                            sibling.addContent(innerIndex, dynaHeaderElement);
                          }
                          catch (Exception localException1) {}
                          count++;
                        }
                      }
                    }
                  }
                }
                processHeaderRow(ctx, sibling, data, this.blockImpl, this.fieldNames, pageName);
              }
              else if (child_Id.contains("list_dynamiccolumnsfixedheader"))
              {
                if ((ctx.get(id) != null) && ((ctx.get(id) instanceof List)))
                {
                  getFixedHeaderForMultipleColumnsInDynamicColumns(sibling);
                  
                  getHeaderElementForDynamicColumns(sibling);
                  Element dynaTdElement = this.dynaColumnsElement;
                  this.dynaColumnsElement = null;
                  List attrList = new ArrayList();
                  if ((dynaTdElement.getAttributes() != null) && (dynaTdElement.getAttributes().size() > 0)) {
                    attrList = dynaTdElement.getAttributes();
                  }
                  Element dynaTdElementContent = null;
                  if (dynaTdElement.getChildren() != null) {
                    dynaTdElementContent = (Element)dynaTdElement.getChildren().get(0);
                  }
                  String tdId = dynaTdElement.getAttributeValue("id");
                  
                  int index = sibling.indexOf(dynaTdElement);
                  
                  sibling.removeContent(dynaTdElement);
                  sibling.removeContent(index);
                  
                  String[] fixedHeaders = (String[])null;
                  if (this.fixedHeaderForMultipleCols != null)
                  {
                    StringTokenizer tokens = new StringTokenizer(this.fixedHeaderForMultipleCols, "|");
                    fixedHeaders = new String[tokens.countTokens()];
                    int m = 0;
                    while (tokens.hasMoreTokens())
                    {
                      fixedHeaders[m] = tokens.nextToken().toString();
                      m++;
                    }
                  }
                  List list = (List)ctx.get(id);
                  Map dynamicHeadersMap = (Map)list.get(list.size() - 1);
                  Set keySet = dynamicHeadersMap.keySet();
                  if ((keySet != null) && (keySet.size() > 0))
                  {
                    Iterator<String> itr = keySet.iterator();
                    String dynamicHeaders = (String)itr.next();
                    StringTokenizer tokens = new StringTokenizer(dynamicHeadersMap.get(dynamicHeaders).toString(), "|");
                    int count = 0;
                    int innerIndex = index;
                    while (tokens.hasMoreTokens())
                    {
                      String header = tokens.nextToken();
                      if (dynaTdElementContent != null)
                      {
                        String noOfDynaColumnsForOneHeader = null;
                        if ((tdId != null) && (tdId.lastIndexOf("dynamiccolumns") + 14 != tdId.length())) {
                          noOfDynaColumnsForOneHeader = tdId.substring(tdId.lastIndexOf("dynamiccolumns") + 14, tdId.length());
                        } else {
                          noOfDynaColumnsForOneHeader = "1";
                        }
                        try
                        {
                          int numberOfColsPerHeader = Integer.parseInt(noOfDynaColumnsForOneHeader);
                          if (count != 0)
                          {
                            innerIndex++;innerIndex = innerIndex;
                          }
                          dynaTdElement = new Element("td");
                          for (int l = 0; l < attrList.size(); l++)
                          {
                            Attribute attr = (Attribute)attrList.get(l);
                            
                            Attribute newAttr = new Attribute(attr.getName(), attr.getValue());
                            dynaTdElement.setAttribute(newAttr);
                          }
                          Element dynaColumnsTable = new Element("table");
                          Element dynaColumnsTr = new Element("tr");
                          for (int j = 0; j < numberOfColsPerHeader; j++)
                          {
                            Element dynaTdInnerElement = new Element("td");
                            for (int l = 0; l < attrList.size(); l++)
                            {
                              Attribute attr = (Attribute)attrList.get(l);
                              
                              Attribute newAttr = new Attribute(attr.getName(), attr.getValue());
                              dynaTdInnerElement.setAttribute(newAttr);
                            }
                            Element dynaInputElement = new Element(dynaTdElementContent.getName());
                            for (int k = 0; k < dynaTdElementContent.getAttributes().size(); k++)
                            {
                              Attribute attr = (Attribute)dynaTdElementContent.getAttributes().get(k);
                              
                              Attribute newAttr = new Attribute(attr.getName(), attr.getValue());
                              dynaInputElement.setAttribute(newAttr);
                            }
                            if ((fixedHeaders != null) && (j < fixedHeaders.length) && (fixedHeaders[j] != null)) {
                              dynaTdInnerElement.setText(fixedHeaders[j]);
                            }
                            dynaColumnsTr.addContent(dynaTdInnerElement);
                          }
                          dynaColumnsTable.addContent(dynaColumnsTr);
                          dynaTdElement.addContent(dynaColumnsTable);
                          sibling.addContent(innerIndex, dynaTdElement);
                        }
                        catch (Exception e)
                        {
                          logger.error(ctx, "Dynamic columns not generated due to error - " + e.getMessage());
                        }
                      }
                      count++;
                    }
                  }
                }
              }
              else if (child_Id.contains("list_data"))
              {
                if ((data != null) && (!data.isEmpty()))
                {
                  if (ctx.get("datalist") != null) {
                    data = (List)ctx.get(ctx.get("datalist").toString());
                  }
                  if (data != null)
                  {
                    Element newRecord = null;
                    List list = new ArrayList();
                    for (int k = 0; k < data.size(); k++)
                    {
                      Map record = (Map)data.get(k);
                      String rowClass = "";
                      newRecord = ParseUtil.createCopyForElement((Element)sibling.clone());
                      String classAttr = newRecord.getAttributeValue("class");
                      if (newRecord.getAttribute("class") != null) {
                        newRecord.removeAttribute("class");
                      }
                      if (k % 2 == 0) {
                        rowClass = this.listSecondRowCSS;
                      } else {
                        rowClass = this.listFirstRowCSS;
                      }
                      if ((classAttr != null) && (classAttr.startsWith("eval:")))
                      {
                        String eval = classAttr.substring(classAttr.indexOf(":") + 1, classAttr.length());
                        Context ctxCopy = (Context)ctx.clone();
                        ctxCopy.putAll(record);
                        RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(eval);
                        Object obj = ruleImpl.execute(ctxCopy, null);
                        if (obj != null) {
                          rowClass = obj.toString();
                        }
                      }
                      newRecord.setAttribute("class", rowClass);
                      processDataRow(ctx, newRecord, record, this.blockImpl, this.fieldNames, null, element, readOnlyResourse, request, false, this.index, false, false, pageName);
                      list.add(newRecord.detach());
                    }
                    sibling.detach();
                    i--;
                    element.addContent(list);
                    element.addContent(this.resList);
                    break;
                  }
                }
                else
                {
                  if ((SystemProperties.getInstance().getProperty("appl." + ctx.getProject() + ".skipnorecordfound") != null) && 
                    (SystemProperties.getInstance().getProperty("appl." + ctx.getProject() + ".skipnorecordfound").toString().equals("Y")))
                  {
                    Element emptyRowElement = new Element("tr");
                    emptyRowElement.setAttribute("class", this.listSecondRowCSS);
                    getEmptyListMsg(ctx, emptyRowElement, sibling, pageName, this.fieldNames);
                    element.removeContent(sibling.detach());
                    element.addContent(emptyRowElement); break;
                  }
                  element.removeContent(sibling.detach());
                  element.addContent(getEmptyListMsg());
                  
                  break;
                }
              }
              else
              {
                if (child_Id.contains("list_mom"))
                {
                  if ((data != null) && (!data.isEmpty()))
                  {
                    if (ctx.get("datalist") != null) {
                      data = (List)ctx.get(ctx.get("datalist").toString());
                    }
                    boolean isFixedHeaderForMultipleColsInDynaCols = false;
                    if (this.fixedHeaderForMultipleCols != null) {
                      isFixedHeaderForMultipleColsInDynaCols = true;
                    }
                    if (child_Id.contains("dynamiccolumns"))
                    {
                      getHeaderElementForDynamicColumns(sibling);
                      Element dynaTdElement = this.dynaColumnsElement;
                      this.dynaColumnsElement = null;
                      List attrList = new ArrayList();
                      if ((dynaTdElement.getAttributes() != null) && (dynaTdElement.getAttributes().size() > 0)) {
                        attrList = dynaTdElement.getAttributes();
                      }
                      Element dynaTdElementContent = null;
                      if (dynaTdElement.getChildren() != null) {
                        dynaTdElementContent = (Element)dynaTdElement.getChildren().get(0);
                      }
                      String tdId = dynaTdElement.getAttributeValue("id");
                      
                      boolean isSiblingTbody = false;
                      int index = sibling.indexOf(dynaTdElement);
                      if (index == -1)
                      {
						List siblingChildren = sibling.getChildren();
						Element childElementTemp = siblingChildren != null && !siblingChildren.isEmpty()
								? (Element) siblingChildren.get(0) : null;
						if (childElementTemp == null)
							return;
						index = childElementTemp.indexOf(dynaTdElement);
                        isSiblingTbody = true;
                        childElementTemp.removeContent(dynaTdElement);
                        childElementTemp.removeContent(index);
                      }
                      else
                      {
                        isSiblingTbody = false;
                        sibling.removeContent(dynaTdElement);
                        sibling.removeContent(index);
                      }
                      List list = (List)ctx.get(id);
                      Map dynamicHeadersMap = (Map)list.get(list.size() - 1);
                      
                      data.remove(list.size() - 1);
                      ctx.put("datalist", data);
                      
                      Set keySet = dynamicHeadersMap.keySet();
                      if ((keySet != null) && (keySet.size() > 0))
                      {
                        Iterator<String> itr = keySet.iterator();
                        String dynamicHeaders = (String)itr.next();
                        StringTokenizer tokens = new StringTokenizer(dynamicHeadersMap.get(dynamicHeaders).toString(), "|");
                        int count = 0;
                        int innerIndex = index;
                        while (tokens.hasMoreTokens())
                        {
                          String header = tokens.nextToken();
                          header = header.substring(0, header.indexOf("="));
                          if (dynaTdElementContent != null)
                          {
                            String noOfDynaColumnsForOneHeader = null;
                            if ((tdId != null) && (tdId.lastIndexOf("dynamiccolumns") + 14 != tdId.length())) {
                              noOfDynaColumnsForOneHeader = tdId.substring(tdId.lastIndexOf("dynamiccolumns") + 14, tdId.length());
                            } else {
                              noOfDynaColumnsForOneHeader = "1";
                            }
                            try
                            {
                              int numberOfColsPerHeader = Integer.parseInt(noOfDynaColumnsForOneHeader);
                              if (count != 0)
                              {
                                innerIndex++;innerIndex = innerIndex;
                              }
                              dynaTdElement = new Element("td");
                              for (int l = 0; l < attrList.size(); l++)
                              {
                                Attribute attr = (Attribute)attrList.get(l);
                                
                                Attribute newAttr = new Attribute(attr.getName(), attr.getValue());
                                dynaTdElement.setAttribute(newAttr);
                              }
                              Element dynaColumnsTable = new Element("table");
                              Element dynaColumnsTr = new Element("tr");
                              for (int j = 0; j < numberOfColsPerHeader; j++)
                              {
                                Element dynaTdInnerElement = new Element("td");
                                for (int l = 0; l < attrList.size(); l++)
                                {
                                  Attribute attr = (Attribute)attrList.get(l);
                                  
                                  Attribute newAttr = new Attribute(attr.getName(), attr.getValue());
                                  dynaTdInnerElement.setAttribute(newAttr);
                                }
                                Element dynaInputElement = new Element(dynaTdElementContent.getName());
                                for (int k = 0; k < dynaTdElementContent.getAttributes().size(); k++)
                                {
                                  Attribute attr = (Attribute)dynaTdElementContent.getAttributes().get(k);
                                  
                                  Attribute newAttr = new Attribute(attr.getName(), attr.getValue());
                                  dynaInputElement.setAttribute(newAttr);
                                }
                                if (child_Id.contains("ajaxdynamiccolumns"))
                                {
                                  if (numberOfColsPerHeader == 1) {
                                    dynaInputElement.setAttribute("id", "ajax_field_" + header.replace("block_field_", ""));
                                  } else {
                                    dynaInputElement.setAttribute("id", "ajax_field_" + header.replace("block_field_", "") + "_" + j);
                                  }
                                }
                                else if (child_Id.contains("dynamiccolumns")) {
                                  if (numberOfColsPerHeader == 1) {
                                    dynaInputElement.setAttribute("id", header.replace("block_field_", ""));
                                  } else {
                                    dynaInputElement.setAttribute("id", header.replace("block_field_", "") + "_" + j);
                                  }
                                }
                                if (numberOfColsPerHeader == 1) {
                                  dynaInputElement.setAttribute("name", header.replace("block_field_", ""));
                                } else {
                                  dynaInputElement.setAttribute("name", header.replace("block_field_", "") + "_" + j);
                                }
                                dynaTdInnerElement.addContent(dynaInputElement);
                                if (numberOfColsPerHeader == 1) {
                                  dynaTdInnerElement.setAttribute("id", header);
                                } else {
                                  dynaTdInnerElement.setAttribute("id", header + "_" + j);
                                }
                                dynaColumnsTr.addContent(dynaTdInnerElement);
                              }
                              dynaColumnsTable.addContent(dynaColumnsTr);
                              dynaTdElement.addContent(dynaColumnsTable);
                              if (isSiblingTbody)
                              {
                                Element requiredSibling = (Element)sibling.getChildren().get(0);
                                requiredSibling.addContent(innerIndex, dynaTdElement);
                              }
                              else
                              {
                                sibling.addContent(innerIndex, dynaTdElement);
                              }
                            }
                            catch (Exception e)
                            {
                              logger.error(ctx, "Dynamic columns not generated due to error - " + e.getMessage());
                            }
                          }
                          count++;
                        }
                      }
                    }
                    boolean isMultiColumn = true;
                    int count = getDataRowDivisionCount(sibling, 0);
                    if (count == 0)
                    {
                      isMultiColumn = false;
                      count = data.size() + 1;
                    }
                    int startCount = 0;
                    List dataList = null;
                    List finalList = new ArrayList();
                    Element newRecord = null;
                    List list = new ArrayList();
                    for (int k = 0; k < data.size(); k++)
                    {
                      Map record = (Map)data.get(k);
                      if ((k + count) % count == 0) {
                        dataList = new ArrayList();
                      }
                      dataList.add(record);
                      startCount++;
                      if ((startCount == count) || (k == data.size() - 1))
                      {
                        finalList.add(dataList);
                        startCount = 0;
                      }
                    }
                    boolean isRowLinkAjaxExist = false;
                    if (child_Id.contains("_rowlinkajax")) {
                      isRowLinkAjaxExist = true;
                    }
                    this.index = 0;
                    if (isMultiColumn)
                    {
                      for (int m = 0; m < finalList.size(); m++)
                      {
                        List divList = (List)finalList.get(m);
                        
                        newRecord = ParseUtil.createCopyForElement((Element)sibling.clone());
                        newRecord.setAttribute("id", newRecord.getAttributeValue("id")+"_"+m);
                        String rowClass = null;
                        if (newRecord.getAttribute("class") != null) {
                          rowClass = newRecord.getAttributeValue("class");
                        }
                        if (rowClass == null) {
                          if (m % 2 == 0) {
                            rowClass = this.listSecondRowCSS;
                          } else {
                            rowClass = this.listFirstRowCSS;
                          }
                        }
                        newRecord.setAttribute("class", rowClass);
                        
                        boolean isTextAreaExist = false;
                        Element cloneDivTextArea = null;
                        List newRecordList = newRecord.getChildren();
                        for (i = 0; i < newRecordList.size(); i++) {
                          if ((newRecordList.get(i) != null) && ((newRecordList.get(i) instanceof Element)))
                          {
                            Element rowElement = (Element)newRecordList.get(i);
                            if ((rowElement.getAttribute("id") != null) && (rowElement.getAttribute("id").getValue().contains("textareacharcountrow")))
                            {
                              cloneDivTextArea = ParseUtil.createCopyForElement((Element)rowElement.clone());
                              rowElement.detach();
                              isTextAreaExist = true;
                            }
                          }
                        }
                        processDataRow(ctx, newRecord, divList, this.blockImpl, this.fieldNames, element, readOnlyResourse, request, isTextAreaExist, isRowLinkAjaxExist, isFixedHeaderForMultipleColsInDynaCols, pageName);
                        if (isTextAreaExist)
                        {
                          newRecord.addContent(cloneDivTextArea);
                          addTextAreaCharactersCountDiv(newRecord, this.index - 1);
                        }
                        list.add(newRecord.detach());
                      }
                    }
                    else
                    {
                      List divList = (List)finalList.get(0);
                      for (int k = 0; k < divList.size(); k++)
                      {
                        Map record = (Map)data.get(k);
                        String rowClass = "";
                        newRecord = ParseUtil.createCopyForElement((Element)sibling.clone());
                        newRecord.setAttribute("id", newRecord.getAttributeValue("id")+"_"+k);
                        
                        String classAttr = newRecord.getAttributeValue("class");
                        if (newRecord.getAttribute("class") != null) {
                          newRecord.removeAttribute("class");
                        }
                        if (k % 2 == 0) {
                          rowClass = this.listSecondRowCSS;
                        } else {
                          rowClass = this.listFirstRowCSS;
                        }
                        if ((classAttr != null) && (classAttr.startsWith("eval:")))
                        {
                          String eval = classAttr.substring(classAttr.indexOf(":") + 1, classAttr.length());
                          Context ctxCopy = (Context)ctx.clone();
                          ctxCopy.putAll(record);
                          RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(eval);
                          Object obj = ruleImpl.execute(ctxCopy, null);
                          if (obj != null) {
                            rowClass = obj.toString();
                          }
                        }
                        String appendValue = ParseUtil.getValueFromObject(Integer.valueOf(this.index), null, null);
                        newRecord.setAttribute("class", rowClass);
                        
                        boolean isTextAreaExist = false;
                        Element cloneDivTextArea = null;
                        List newRecordList = newRecord.getChildren();
                        for (i = 0; i < newRecordList.size(); i++) {
                          if ((newRecordList.get(i) != null) && ((newRecordList.get(i) instanceof Element)))
                          {
                            Element rowElement = (Element)newRecordList.get(i);
                            if ((rowElement.getAttribute("id") != null) && (rowElement.getAttribute("id").getValue().contains("textareacharcountrow")))
                            {
                              cloneDivTextArea = ParseUtil.createCopyForElement((Element)rowElement.clone());
                              rowElement.detach();
                              isTextAreaExist = true;
                            }
                          }
                        }
                        processDataRow(ctx, newRecord, record, this.blockImpl, this.fieldNames, appendValue, element, readOnlyResourse, request, isTextAreaExist, this.index, isRowLinkAjaxExist, isFixedHeaderForMultipleColsInDynaCols, pageName);
                        if (isTextAreaExist)
                        {
                          newRecord.addContent(cloneDivTextArea);
                          addTextAreaCharactersCountDiv(newRecord, this.index - 1);
                        }
                        this.index += 1;
                        list.add(newRecord.detach());
                      }
                    }
                    sibling.detach();
                    element.addContent(list);
                    element.addContent(this.resList);
                    break;
                  }
                  if ((SystemProperties.getInstance().getProperty("appl." + ctx.getProject() + ".skipnorecordfound") != null) && 
                    (SystemProperties.getInstance().getProperty("appl." + ctx.getProject() + ".skipnorecordfound").toString().equals("Y")))
                  {
                    Element emptyRowElement = new Element("tr");
                    emptyRowElement.setAttribute("class", this.listSecondRowCSS);
                    getEmptyListMsg(ctx, emptyRowElement, sibling, pageName, this.fieldNames);
                    element.removeContent(sibling.detach());
                    element.addContent(emptyRowElement); break;
                  }
                  element.removeContent(sibling.detach());
                  element.addContent(getEmptyListMsg());
                  
                  break;
                }
                if (child_Id.contains("pagination"))
                {
                  Map map = new HashMap();
                  map.put("list", data);
                  if ((data != null) && (!data.isEmpty()))
                  {
                    Object result = applySorting(currentList, ctx, child_Id);
                    ctx.put(ctx.get("datalist").toString(), result);
                    if ((data instanceof List)) {
                      map.put("listKey", ctx.get("datalist").toString());
                    }
                    if (ctx.get("request_pagenumber") != null) {
                      ctx.put("pagenumber", ctx.get("request_pagenumber"));
                    } else {
                      ctx.put("request_pagenumber", ctx.get("pagenumber"));
                    }
                    map.put("list", result);
                    
                    String parsing_list_name = ctx.get("parsing_list_name") != null ? ctx.get("parsing_list_name").toString() : null;
                    if (parsing_list_name != null)
                    {
                      if ((ctx.get(parsing_list_name + "_" + "pagenumber") != null) && (ctx.get(ctx.get(parsing_list_name + "_" + "pagenumber")) != null)) {
                        ctx.put("pagenumber", ctx.get(ctx.get(parsing_list_name + "_" + "pagenumber")));
                      }
                      if ((ctx.get(parsing_list_name + "_" + "recordsperpage") != null) && (ctx.get(ctx.get(parsing_list_name + "_" + "recordsperpage")) != null)) {
                        ctx.put("recordsperpage", ctx.get(ctx.get(parsing_list_name + "_" + "recordsperpage")));
                      }
                    }
                    map.put("pagenumber", ctx.get("pagenumber"));
                    map.put("recordsperpage", ctx.get("recordsperpage"));
                    map.put("paginationaction", ctx.get("paginationaction"));
                    map.put("paginationblockid", child_Id);
                    ctx.put("default_recordsperpage" + child_Id, "10");
                    ctx.put("paginationinfo", map);
                    PaginationUtils paginationUtils = new PaginationUtils();
                    paginationUtils.populatePaginatedList(ctx, ctx.get("datalist").toString(), child_Id);
                    data = (List)ctx.get(ctx.get("datalist").toString());
                    currentList = data;
                    ctx.remove(ctx.get("datalist").toString());
                    ctx.put(ctx.get("datalist").toString(), currentList);
                    new Pagination(paginationUtils).populatePagination(sibling, data, ctx, sibling.getChildren(), this.componentImpl.getName(), child_Id);
                  }
                  else
                  {
                    element.removeContent(sibling.detach());
                    i--;
                    continue;
                  }
                }
              }
            }
            parseList(ctx, sibling, sibling.getChildren(), data, readOnlyResourse, ajax_append_field_value, request, pageName);
          }
        }
      }
    }
  }
  
  public List applySorting(List blockDataList, IContext ctx, String id)
    throws Exception
  {
    int dataType = 0;
    if (blockDataList == null) {
      return null;
    }
    if ((id != null) && (id.contains("_ajaxdynamiccolumns")))
    {
      LinkedList newList = new LinkedList();
      for (int i = 0; i < blockDataList.size(); i++)
      {
        Map map = (Map)blockDataList.get(i);
        if (!map.containsKey("dynamicHeadersMap")) {
          newList.add(map);
        }
      }
      if (ctx.get("inet_sort_field") != null)
      {
        FieldImpl fieldImpl = MetaDataResources.getInstance(ctx).getField(ctx.get("inet_sort_field").toString());
        if (fieldImpl == null) {
          dataType = 2;
        } else {
          dataType = populateDataType(fieldImpl);
        }
        MapComparator mapComparator = new MapComparator(ctx.get("inet_sort_field").toString(), dataType, ctx.get("inet_sort_type").toString());
        Collections.sort(newList, mapComparator);
        
        newList.add(blockDataList.get(blockDataList.size() - 1));
        blockDataList = newList;
      }
    }
    else if (ctx.get("inet_sort_field") != null)
    {
      FieldImpl fieldImpl = MetaDataResources.getInstance(ctx).getField(ctx.get("inet_sort_field").toString());
      if (fieldImpl == null) {
        dataType = 2;
      } else {
        dataType = populateDataType(fieldImpl);
      }
      MapComparator mapComparator = new MapComparator(ctx.get("inet_sort_field").toString(), dataType, ctx.get("inet_sort_type").toString());
      Collections.sort(blockDataList, mapComparator);
      
      String parsing_list_name = ctx.get("parsing_list_name").toString();
      ctx.put(parsing_list_name + "_sortfield", ctx.get("inet_sort_field").toString());
    }
    return blockDataList;
  }
  
  private int populateDataType(FieldImpl fieldImpl)
  {
    String datatype = fieldImpl.getType();
    if (datatype.equals("V")) {
      return 2;
    }
    if (datatype.equals("I")) {
      return 1;
    }
    if (datatype.equals("D")) {
      return 3;
    }
    return 2;
  }
  
  public void processDataRow(Context ctx, Element newRecord, List divList, BlockImpl blockImpl, List fieldNames, Element rootElement, boolean readOnlyResourse, HttpServletRequest request, boolean isTextAreaExist, boolean isRowLinkAjaxExist, boolean isFixedHeaderForMultipleColsInDynaCols, String pageName)
    throws Exception
  {
    List children = newRecord.getChildren();
    if (children != null) {
      for (int i = 0; i < children.size(); i++)
      {
        boolean detachFlag = false;
        Element sibling = (Element)children.get(i);
        if (sibling.getAttribute("id") != null)
        {
          String child_Id = sibling.getAttributeValue("id");
          if (child_Id.equals("mom_child_data")) {
            if (!divList.isEmpty())
            {
              List list = new ArrayList();
              Map record = (Map)divList.get(0);
              String appendValue = ParseUtil.getValueFromObject(Integer.valueOf(this.index), null, null);
              Element newElement = ParseUtil.createCopyForElement((Element)sibling.clone());
              
              String rowClass = newElement.getAttributeValue("class");
              if ((rowClass != null) && (rowClass.startsWith("eval:")))
              {
                String eval = rowClass.substring(rowClass.indexOf(":") + 1, rowClass.length());
                Context ctxCopy = (Context)ctx.clone();
                ctxCopy.putAll(record);
                RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(eval);
                Object obj = ruleImpl.execute(ctxCopy, null);
                if (obj != null) {
                  rowClass = obj.toString();
                }
                newElement.setAttribute("class", rowClass);
              }
              processDataRow(ctx, newElement, record, blockImpl, fieldNames, appendValue, newRecord, readOnlyResourse, request, isTextAreaExist, this.index, isRowLinkAjaxExist, isFixedHeaderForMultipleColsInDynaCols, pageName);
              list.add(newElement.detach());
              
              divList.remove(0);
              this.index += 1;
              sibling.detach();
              newRecord.addContent(list);
            }
            else
            {
              sibling.detach();
            }
          }
        }
        processDataRow(ctx, sibling, divList, blockImpl, fieldNames, newRecord, readOnlyResourse, request, isTextAreaExist, isRowLinkAjaxExist, isFixedHeaderForMultipleColsInDynaCols, pageName);
      }
    }
  }
  
  private int getDataRowDivisionCount(Element rootElement, int divCount)
  {
    List elements = rootElement.getChildren();
    if (elements == null) {
      return divCount;
    }
    for (int i = 0; i < elements.size(); i++)
    {
      Element element = (Element)elements.get(i);
      String attributeID = element.getAttributeValue("id");
      if ((attributeID != null) && ("mom_child_data".equals(attributeID))) {
        divCount++;
      }
      divCount = getDataRowDivisionCount(element, divCount);
    }
    return divCount;
  }
  
  private void populateDivFieldname(Element rootElement, Context ctx)
  {
    Element divpostionElement = new PageUtils().getExistingElement(rootElement, "div_ajaxpostion");
    if (divpostionElement != null)
    {
      String divpostionid = divpostionElement.getAttributeValue("id");
      if ((divpostionid != null) && (!"".equals(divpostionid)))
      {
        String fieldname = divpostionid.substring("div_ajaxpostion".length() + 1, divpostionid.length());
        if ((fieldname != null) && (!"".equals(fieldname))) {
          ctx.put("divfieldname", fieldname);
        }
      }
    }
  }
  
  public void processHeader(Element sibling, List fieldNames)
  {
    List headers = sibling.getChildren();
    for (int j = 0; j < headers.size(); j++)
    {
      Element column = (Element)headers.get(j);
      String field = column.getAttributeValue("id");
      if ((field != null) && 
        (isBlockField(field)) && (!fieldNames.contains(getFieldNameForList(field)))) {
        fieldNames.add(getFieldNameForList(field));
      }
      processHeader(column, fieldNames);
    }
  }
  
  public void processHeaderRow(Context ctx, Element newRecord, List record, BlockImpl blockImpl, List fieldNames, String pageName)
    throws Exception
  {
    List children = newRecord.getChildren();
    for (int i = 0; i < children.size(); i++)
    {
      Element child = (Element)children.get(i);
      if (new SecurityParser().parseElement(ctx, child, this.componentImpl.getName()))
      {
        i--;
      }
      else if (processElementForHide(child, ctx, pageName))
      {
        i--;
      }
      else if ((child.getAttributeValue("id") != null) && (child.getAttributeValue("id").contains("block_field_")))
      {
        String field = child.getAttributeValue("id");
        if ((isBlockFieldSortingRequired(field).booleanValue()) && (record != null) && (!record.isEmpty()))
        {
          Map sortingMap = new HashMap();
          if (field.contains("sorting("))
          {
            String[] sortVals = field.substring(field.indexOf("("), field.indexOf(")")).split(",");
            if (sortVals.length < 3)
            {
              logger.error(ctx, "Sorting param id not defined properly for field " + getFieldNameForList(field) + " in block " + blockImpl.getId());
              sortingMap.put("type", "N");
            }
            else
            {
              sortingMap.put("default", (sortVals[0] != null) && (("desc".equalsIgnoreCase(sortVals[0])) || ("asc".equalsIgnoreCase(sortVals[0]))) ? sortVals[0] : null);
              sortingMap.put("type", (sortVals[1] != null) && (("N".equalsIgnoreCase(sortVals[1])) || ("A".equalsIgnoreCase(sortVals[1]))) ? sortVals[1] : null);
              sortingMap.put("displayid", sortVals[2]);
            }
          }
          else
          {
            sortingMap.put("type", "N");
          }
          field = getFieldNameForList(field);
          
          new SortingLinkGenerator(this.pagecomponentImpl, this.componentImpl).generateHeaderColumn(ctx, this.componentImpl, child, blockImpl, field, record, sortingMap);
        }
      }
      else
      {
        processHeaderRow(ctx, child, record, blockImpl, fieldNames, pageName);
      }
    }
  }
  
  public void processDataRow(Context ctx, Element newRecord, Map record, BlockImpl blockImpl, List fieldNames, String appendValue, Element rootElement, boolean readOnlyResourse, HttpServletRequest request, boolean isTextAreaExist, int index, boolean isRowLinkAjaxExist, boolean isFixedHeaderForMultipleColsInDynaCols, String pageName)
    throws Exception
  {
    List children = newRecord.getChildren();
    for (int i = 0; i < children.size(); i++)
    {
      Element child = (Element)children.get(i);
      
      Context ctxCopy = (Context)ctx.clone();
      if (record == null) {
        record = new HashMap();
      }
      ctxCopy.putAll(record);
      
      int accessType = new SecurityParser().getAccessTypeBySecurity(ctxCopy, child, this.componentImpl.getName(), true);
      if (SecurityParser.isHideResource(accessType))
      {
        child.detach();
        i--;
      }
      else if (processElementForHide(child, ctxCopy, pageName))
      {
        ctxCopy = null;
        i--;
      }
      else
      {
        if (SecurityParser.checkForOverideSecurity(ctxCopy, child, this.componentImpl.getName())) {
          readOnlyResourse = false;
        }
        readOnlyResourse = SecurityParser.isReadOnlyResource(accessType, readOnlyResourse);
        if (new HelpMessageProcessor().parseElement(ctx, child))
        {
          i--;
        }
        else
        {
          ctxCopy = null;
          if (child.getAttributeValue("id") != null)
          {
            String field = child.getAttributeValue("id");
            if ((field != null) && (field.contains("rowlinkajaxdiv")))
            {
              field = field + "_" + index;
              child.setAttribute("id", field);
            }
            field = getFieldNameForList(field);
            if (isColumn(field, fieldNames)) {
              generateListColumn(ctx, this.componentImpl, child, record, field, blockImpl, appendValue, readOnlyResourse, request, isTextAreaExist, index, isRowLinkAjaxExist);
            }
          }
          if (child.getAttributeValue("id") != null)
          {
            String child_Id = child.getAttributeValue("id");
            if ((child_Id.contains("ajaxpostion_list")) && 
              (record != null))
            {
              List list = new ArrayList();
              new AjaxLinkGenerator().getDivPostionList(ctx, list, record, child);
              
              break;
            }
          }
          processDataRow(ctx, child, record, blockImpl, fieldNames, appendValue, rootElement, readOnlyResourse, request, 
            isTextAreaExist, index, isRowLinkAjaxExist, isFixedHeaderForMultipleColsInDynaCols, pageName);
        }
      }
    }
  }
  
  protected void generateListColumn(Context ctx, ComponentImpl componentImpl, Element displayElement, Map record, String field, BlockImpl blockImpl, String appendValue, boolean readOnlyResourse, HttpServletRequest request, boolean isTextAreaExist, int index, boolean isRowLinkAjaxExist)
    throws Exception
  {
    String fieldValue = "";
    String selectedVal = null;
    
    String ajax_append_field_value = (ctx.get("ajax_append_field_value") != null) && (!"".equals(ctx.get("ajax_append_field_value"))) ? ctx.get("ajax_append_field_value").toString() : null;
    if (field.contains("_selected_row"))
    {
      field = field.replace("_selected_row", "");
      if ((ctx.get("inet_eventid") != null) && ("edit".equals(ctx.get("inet_eventid"))))
      {
        Object object = ctx.get(field);
        selectedVal = ParseUtil.getValueFromObject(object, null, null);
      }
    }
    if (record.get(field) != null)
    {
      Object object = record.get(field);
      fieldValue = ParseUtil.getValueFromObject(object, null, null);
    }
    if (displayElement != null)
    {
      List displayList = displayElement.getChildren();
      if ((displayList != null) && (displayList.size() == 0))
      {
        if ((displayElement.getAttributeValue("type") != null) && (
          (displayElement.getAttributeValue("type").equals("checkbox")) || 
          (displayElement.getAttributeValue("type").equals("radio")) || 
          (displayElement.getAttributeValue("type").equals("textarea")) || 
          (displayElement.getAttributeValue("type").equals("select")) || 
          (displayElement.getAttributeValue("type").equals("text")))) {
          return;
        }
        fieldValue = SecurityResources.getInstance(ctx).checkForEncryptionDecryption(ctx, field, "label", fieldValue);
        if ((ctx.get(field + "_encryptreadonly") != null) && (ctx.get(field + "_encryptreadonly").toString().equals("Y"))) {
          ctx.remove(field + "_encryptreadonly");
        }
        if ((ctx.get(field + "_encrypt") != null) && (ctx.get(field + "_encrypt").toString().equals("Y"))) {
          ctx.remove(field + "_encrypt");
        }
        displayElement.setText(fieldValue);
      }
      else if ((displayList != null) && (displayList.size() > 0))
      {
        Element viewType = (Element)displayList.get(0);
        String type = viewType.getName();
        
        ElementProcessor ep = new ElementProcessor(this.metadataResources, this.pagecomponentImpl, componentImpl);
        ep.processElement(viewType, ctx, record, null, request);
        if ("input".equals(type))
        {
          if (("Y".equals(ctx.get("inet_form_dirty"))) && (appendValue != null) && 
            (viewType.getAttributeValue("name") != null))
          {
            String control_name = ParseUtil.appendValueWithCoumn(viewType.getAttributeValue("name").toString(), appendValue);
            if (ajax_append_field_value != null) {
              control_name = ajax_append_field_value + control_name;
            }
            if (ctx.get(control_name) != null) {
              fieldValue = ctx.get(control_name).toString();
            }
          }
          if ((readOnlyResourse) && (viewType.getAttributeValue("disabled") == null) && (!"disabled".equalsIgnoreCase(viewType.getAttributeValue("disabled"))) && 
            (viewType.getAttributeValue("readonly") == null) && (!"readonly".equalsIgnoreCase(viewType.getAttributeValue("readonly")))) {
            viewType.setAttribute("disabled", "disabled");
          }
          new ListFormInputControlParser(this.metadataResources).parseInputControls(displayElement, viewType, selectedVal, fieldValue, getBlockFieldForHTMLControl(blockImpl, displayElement), appendValue, record, ajax_append_field_value, ctx);
        }
        else if ("select".equalsIgnoreCase(type))
        {
          if (("Y".equals(ctx.get("inet_form_dirty"))) && (appendValue != null) && 
            (viewType.getAttributeValue("name") != null))
          {
            String control_name = ParseUtil.appendValueWithCoumn(viewType.getAttributeValue("name").toString(), appendValue);
            if (ajax_append_field_value != null) {
              control_name = ajax_append_field_value + control_name;
            }
            if (ctx.get(control_name) != null) {
              fieldValue = ctx.get(control_name).toString();
            }
          }
          if ((readOnlyResourse) && (viewType.getAttributeValue("disabled") == null) && (!"disabled".equalsIgnoreCase(viewType.getAttributeValue("disabled"))) && 
            (viewType.getAttributeValue("readonly") == null) && (!"readonly".equalsIgnoreCase(viewType.getAttributeValue("readonly")))) {
            viewType.setAttribute("disabled", "disabled");
          }
          new ListFormSelectControlParser(this.metadataResources).parseSelectControl(componentImpl, ctx, displayElement, viewType, fieldValue, getBlockFieldForHTMLControl(blockImpl, displayElement), 
            appendValue, record, ajax_append_field_value, isRowLinkAjaxExist, index);
        }
        else if ("textarea".equalsIgnoreCase(type))
        {
          if (("Y".equals(ctx.get("inet_form_dirty"))) && (appendValue != null) && 
            (viewType.getAttributeValue("name") != null))
          {
            String control_name = ParseUtil.appendValueWithCoumn(viewType.getAttributeValue("name").toString(), appendValue);
            if (ajax_append_field_value != null) {
              control_name = ajax_append_field_value + control_name;
            }
            if (ctx.get(control_name) != null) {
              fieldValue = ctx.get(control_name).toString();
            }
          }
          if ((readOnlyResourse) && (viewType.getAttributeValue("disabled") == null) && (!"disabled".equalsIgnoreCase(viewType.getAttributeValue("disabled"))) && 
            (viewType.getAttributeValue("readonly") == null) && (!"readonly".equalsIgnoreCase(viewType.getAttributeValue("readonly")))) {
            viewType.setAttribute("readonly", "readonly");
          }
          new ListFormTextAreaControlParser(this.metadataResources).parseTextAreaControls(displayElement, viewType, fieldValue, true, getBlockFieldForHTMLControl(blockImpl, displayElement), appendValue, ajax_append_field_value, isTextAreaExist, index, ctx, record);
        }
        else if ("label".equalsIgnoreCase(type))
        {
          new ListFormLabelControlParser().parseLabelControls(displayElement, viewType, fieldValue, getBlockFieldForHTMLControl(blockImpl, displayElement), appendValue, ctx, record);
        }
        else if ("a".equalsIgnoreCase(type))
        {
          if (viewType.getAttributeValue("href") != null) {
            if (field.endsWith("_multirow_link"))
            {
              field = field.endsWith("_multirow_link") ? field.substring(0, field.indexOf("_multirow_link")) : field;
              new RowLinkGenerator(this.metadataResources, this.pagecomponentImpl, componentImpl).parseMultiRowLink(displayElement, viewType, ctx, record, blockImpl, request, field);
            }
            else
            {
              new RowLinkGenerator(this.metadataResources, this.pagecomponentImpl, componentImpl).parseRowLink(ctx, displayElement, viewType, record, blockImpl, request);
            }
          }
        }
        else if (("script".equalsIgnoreCase(type)) && 
          (viewType.getText() != null))
        {
          String content = viewType.getText().trim();
          
          boolean isTriggerOutlook = false;
          if ((content.startsWith("javascript:triggerOutlook")) || (content.startsWith("triggerOutlook"))) {
            isTriggerOutlook = true;
          }
          int startIndex = content.indexOf("(");
          int endIndex = content.indexOf(")");
          String params = content.substring(startIndex + 1, endIndex);
          if (isTriggerOutlook)
          {
            displayElement.removeContent();
            
            String[] paramsArray = params.split(",");
            String email = null;
            String subject = null;
            String body = null;
            if (paramsArray[0] != null) {
              email = record.get(paramsArray[0]) != null ? record.get(paramsArray[0]).toString() : null;
            }
            if (paramsArray[1] != null) {
              subject = record.get(paramsArray[1]) != null ? record.get(paramsArray[1]).toString() : null;
            }
            if (paramsArray[2] != null) {
              body = record.get(paramsArray[2]) != null ? record.get(paramsArray[2]).toString() : null;
            }
            Element alink = new Element("a");
            
            String href = "mailto:" + email + "?body=" + body + "&subject=" + subject;
            
            alink.setAttribute("href", href);
            alink.setAttribute("class", "icoMailing");
            
            displayElement.addContent(alink);
          }
          else
          {
            ArrayList list = new ArrayList();
            StringTokenizer tokens = new StringTokenizer(params, ",");
            while (tokens.hasMoreTokens()) {
              list.add(tokens.nextToken());
            }
            for (int i = 0; i < list.size(); i++)
            {
              String param = (String)list.get(i);
              content = viewType.getText().trim();
              if (param.equalsIgnoreCase("level"))
              {
                Element parentElement = (Element)viewType.getParent();
                parentElement.removeContent();
                parentElement.setAttribute("class", "SpaceLevel" + String.valueOf(record.get("Level")));
              }
              if (param.equalsIgnoreCase("OrderByField"))
              {
                Element parentElement = (Element)viewType.getParent();
                parentElement.removeContent();
                parentElement.setAttribute("class", "SpaceLevel" + String.valueOf(record.get("OrderByField")));
              }
              if (param.equalsIgnoreCase("org_level"))
              {
                viewType.removeContent();
                String newContent = String.valueOf(record.get("org_level"));
                newContent = newContent.replace("$", "\\$");
                newContent = content.replaceAll(param, newContent);
                viewType.setText(newContent);
              }
              else
              {
                viewType.removeContent();
                String newContent = String.valueOf(record.get(param));
                newContent = newContent.replace("$", "\\$");
                newContent = content.replaceAll(param, "'" + newContent + "'");
                viewType.setText(newContent);
              }
            }
          }
        }
      }
    }
  }
  
  public boolean isColumn(String field, List fieldNames)
  {
    if (fieldNames == null) {
      return false;
    }
    if (fieldNames.contains(field)) {
      return true;
    }
    return false;
  }
  
  public boolean isBlockField(String field)
  {
    if (field.contains("block_field_")) {
      return true;
    }
    return false;
  }
  
  public String getFieldNameForList(String field)
  {
    if (field.contains("block_field_"))
    {
      if (isBlockFieldSortingRequired(field).booleanValue()) {
        field = field.substring(field.indexOf(":") + 13, field.length());
      } else if (field.contains("block_field_radio_group_")) {
        field = field.substring(field.indexOf("block_field_radio_group_") + 24, field.length());
      } else {
        field = field.substring(field.indexOf("block_field_") + 12, field.length());
      }
      if ((field == null) || ("".equals(field))) {
        logger.debug("block field " + field + " is not specified correctly");
      }
    }
    return field;
  }
  
  public Boolean isBlockFieldSortingRequired(String field)
  {
    if ((field.contains("sorting:")) || (field.contains("sorting("))) {
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }
  
  private Element getEmptyListMsg()
  {
    Element nrfTR = new Element("tr");
    Element nrfTD = new Element("td");
    Element nrfDIV = new Element("div");
    nrfDIV.addContent("No Record Found");
    nrfDIV.setAttribute("class", "EmptyRowStyle");
    nrfTD.setAttribute("colspan", "20");
    nrfTD.addContent(nrfDIV);
    nrfTR.addContent(nrfTD);
    
    return nrfTR;
  }
  
  public static void main(String[] args)
  {
    ListFormGenerator ls = new ListFormGenerator();
  }
  
  private void addTextAreaCharactersCountDiv(Element element, int index)
    throws Exception
  {
    List children = element.getChildren();
    for (int i = 0; i < children.size(); i++) {
      if ((children.get(i) instanceof Element))
      {
        Element child = (Element)children.get(i);
        String childId = child.getAttribute("id") != null ? child.getAttribute("id").getValue() : null;
        if ((childId != null) && (childId.contains("divTextAreaCharCount")))
        {
          child.setAttribute("id", "divTextAreaCharCountMsg_" + index);
          return;
        }
        addTextAreaCharactersCountDiv(child, index);
      }
    }
  }
  
  private Element getHeaderElementForDynamicColumns(Element parent)
    throws Exception
  {
    Element element = null;
    if ((parent.getContent() != null) && (parent.getContent().size() > 0)) {
      for (int i = 0; i < parent.getContent().size(); i++)
      {
        Object obj = parent.getContent().get(i);
        if ((obj instanceof Element))
        {
          Element row = (Element)obj;
          if ((row.getAttributeValue("id") != null) && (row.getAttributeValue("id").contains("dynamiccolumns")))
          {
            this.dynaColumnsElement = row;
            return row;
          }
          getHeaderElementForDynamicColumns(row);
        }
      }
    }
    return element;
  }
  
  private Element generateFixedHeaderForMultipleDynaColumns(Element parent, Element sibling, int numberOfColsPerHeader)
    throws Exception
  {
    Element fixedDynaHeaderTd = new Element("td");
    fixedDynaHeaderTd.addContent("New");
    
    return fixedDynaHeaderTd;
  }
  
  private void getFixedHeaderForMultipleColumnsInDynamicColumns(Element parent)
    throws Exception
  {
    if ((parent.getContent() != null) && (parent.getContent().size() > 0))
    {
      List list = parent.getContent();
      for (int i = 0; i < list.size(); i++)
      {
        Object obj = list.get(i);
        if ((obj instanceof Element))
        {
          Element row = (Element)obj;
          if ((row.getAttributeValue("id") != null) && (row.getAttributeValue("id").contains("dynamiccolumns")))
          {
            getFixedHeaderForMultipleColumnsInDynamicColumns1(row);
            break;
          }
        }
      }
    }
  }
  
  private void getFixedHeaderForMultipleColumnsInDynamicColumns1(Element parent)
    throws Exception
  {
    if ((parent.getContent() != null) && (parent.getContent().size() > 0))
    {
      List list = parent.getContent();
      for (int i = 0; i < list.size(); i++)
      {
        Object obj = list.get(i);
        if ((obj instanceof Element))
        {
          Element headerChildElement = (Element)obj;
          String fixedHeaderLabel = headerChildElement.getAttributeValue("id");
          if ((fixedHeaderLabel != null) && (fixedHeaderLabel.equalsIgnoreCase("fixedheader")))
          {
            this.fixedHeaderForMultipleCols = headerChildElement.getText();
            break;
          }
          if (this.fixedHeaderForMultipleCols == null) {
            getFixedHeaderForMultipleColumnsInDynamicColumns1(headerChildElement);
          }
        }
      }
    }
  }
  
  public void parseListFreeform(Context ctx, Element element, List children, List currentList, boolean readOnlyResourse, String ajax_append_field_value, HttpServletRequest request, String pageName, boolean actualreadOnlyResourse)
    throws Exception
  {
    String id = "";
    List data = null;
    if (element.getAttributeValue("id") != null) {
      id = element.getAttributeValue("id");
    }
    if ((ctx.containsKey(id)) && 
      ((ctx.get(id) instanceof List)))
    {
      data = (List)ctx.get(id);
      ctx.put("datalist", id);
    }
    if (this.blockImpl == null)
    {
      this.blockImpl = this.pagecomponentImpl.getBlock(id);
      if (this.blockImpl == null) {
        logger.debug(this.pagecomponentImpl.getName() + " do not have block " + id);
      }
    }
    if (data == null) {
      data = currentList;
    }
    if (children != null) {
      for (int i = 0; i < children.size(); i++)
      {
        boolean detachFlag = false;
        Element sibling = (Element)children.get(i);
        String type = sibling.getName();
        
        int accessType = new SecurityParser().getAccessTypeBySecurity(ctx, sibling, this.componentImpl.getName());
        if (SecurityParser.isHideResource(accessType))
        {
          sibling.detach();
          i--;
        }
        else if (processElementForHide(sibling, ctx, pageName))
        {
          i--;
        }
        else
        {
          if (SecurityParser.checkForOverideSecurity(ctx, sibling, this.componentImpl.getName())) {
            readOnlyResourse = false;
          }
          readOnlyResourse = SecurityParser.isReadOnlyResource(accessType, readOnlyResourse);
          
          ElementProcessor ep = new ElementProcessor(this.metadataResources, this.pagecomponentImpl, this.componentImpl);
          if (ep.processElement(sibling, ctx, null, null, request))
          {
            i--;
          }
          else
          {
            if (sibling.getAttribute("id") != null)
            {
              String child_Id = sibling.getAttributeValue("id");
              if (child_Id.contains("list_header_"))
              {
                this.fieldNames = new ArrayList();
                processHeader(sibling, this.fieldNames);
                
                processHeaderRow(ctx, sibling, data, this.blockImpl, this.fieldNames, pageName);
              }
              else if (child_Id.contains("list_data"))
              {
                if ((data != null) && (!data.isEmpty()))
                {
                  if (ctx.get("datalist") != null) {
                    data = (List)ctx.get(ctx.get("datalist").toString());
                  }
                  if (data != null)
                  {
                    Element newRecord = null;
                    List list = new ArrayList();
                    for (int k = 0; k < data.size(); k++)
                    {
                      Map record = (Map)data.get(k);
                      String rowClass = "";
                      newRecord = ParseUtil.createCopyForElement((Element)sibling.clone());
                      String classAttr = newRecord.getAttributeValue("class");
                      if (newRecord.getAttribute("class") != null) {
                        newRecord.removeAttribute("class");
                      }
                      if (k % 2 == 0) {
                        rowClass = this.listSecondRowCSS;
                      } else {
                        rowClass = this.listFirstRowCSS;
                      }
                      if ((classAttr != null) && (classAttr.startsWith("eval:")))
                      {
                        String eval = classAttr.substring(classAttr.indexOf(":") + 1, classAttr.length());
                        Context ctxCopy = (Context)ctx.clone();
                        ctxCopy.putAll(record);
                        RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(eval);
                        Object obj = ruleImpl.execute(ctxCopy, null);
                        if (obj != null) {
                          rowClass = obj.toString();
                        }
                      }
                      newRecord.setAttribute("class", rowClass);
                      processDataRow(ctx, newRecord, record, this.blockImpl, this.fieldNames, null, element, readOnlyResourse, request, false, this.index, false, false, pageName);
                      list.add(newRecord.detach());
                    }
                    sibling.detach();
                    i--;
                    element.addContent(list);
                    element.addContent(this.resList);
                  }
                }
                else
                {
                  element.removeContent(sibling.detach());
                  i--;
                }
              }
              else if (child_Id.contains("list_mom"))
              {
                if ((data != null) && (!data.isEmpty()))
                {
                  if (ctx.get("datalist") != null) {
                    data = (List)ctx.get(ctx.get("datalist").toString());
                  }
                  boolean isMultiColumn = true;
                  int count = getDataRowDivisionCount(sibling, 0);
                  if (count == 0)
                  {
                    isMultiColumn = false;
                    count = data.size() + 1;
                  }
                  int startCount = 0;
                  List dataList = null;
                  List finalList = new ArrayList();
                  Element newRecord = null;
                  List list = new ArrayList();
                  for (int k = 0; k < data.size(); k++)
                  {
                    Map record = (Map)data.get(k);
                    if ((k + count) % count == 0) {
                      dataList = new ArrayList();
                    }
                    dataList.add(record);
                    startCount++;
                    if ((startCount == count) || (k == data.size() - 1))
                    {
                      finalList.add(dataList);
                      startCount = 0;
                    }
                  }
                  boolean isRowLinkAjaxExist = false;
                  if (child_Id.contains("_rowlinkajax")) {
                    isRowLinkAjaxExist = true;
                  }
                  this.index = 0;
                  if (isMultiColumn)
                  {
                    for (int m = 0; m < finalList.size(); m++)
                    {
                      List divList = (List)finalList.get(m);
                      
                      newRecord = ParseUtil.createCopyForElement((Element)sibling.clone());
                      
                      String rowClass = null;
                      if (newRecord.getAttribute("class") != null) {
                        rowClass = newRecord.getAttributeValue("class");
                      }
                      if (rowClass == null) {
                        if (m % 2 == 0) {
                          rowClass = this.listSecondRowCSS;
                        } else {
                          rowClass = this.listFirstRowCSS;
                        }
                      }
                      newRecord.setAttribute("class", rowClass);
                      
                      boolean isTextAreaExist = false;
                      Element cloneDivTextArea = null;
                      List newRecordList = newRecord.getChildren();
                      for (i = 0; i < newRecordList.size(); i++) {
                        if ((newRecordList.get(i) != null) && ((newRecordList.get(i) instanceof Element)))
                        {
                          Element rowElement = (Element)newRecordList.get(i);
                          if ((rowElement.getAttribute("id") != null) && (rowElement.getAttribute("id").getValue().contains("textareacharcountrow")))
                          {
                            cloneDivTextArea = ParseUtil.createCopyForElement((Element)rowElement.clone());
                            rowElement.detach();
                            isTextAreaExist = true;
                          }
                        }
                      }
                      processDataRow(ctx, newRecord, divList, this.blockImpl, this.fieldNames, element, readOnlyResourse, request, isTextAreaExist, isRowLinkAjaxExist, false, pageName);
                      if (isTextAreaExist)
                      {
                        newRecord.addContent(cloneDivTextArea);
                        addTextAreaCharactersCountDiv(newRecord, this.index - 1);
                      }
                      list.add(newRecord.detach());
                    }
                  }
                  else
                  {
                    List divList = (List)finalList.get(0);
                    for (int k = 0; k < divList.size(); k++)
                    {
                      Map record = (Map)data.get(k);
                      String rowClass = "";
                      newRecord = ParseUtil.createCopyForElement((Element)sibling.clone());
                      String classAttr = newRecord.getAttributeValue("class");
                      if (newRecord.getAttribute("class") != null) {
                        newRecord.removeAttribute("class");
                      }
                      if (k % 2 == 0) {
                        rowClass = this.listSecondRowCSS;
                      } else {
                        rowClass = this.listFirstRowCSS;
                      }
                      if ((classAttr != null) && (classAttr.startsWith("eval:")))
                      {
                        String eval = classAttr.substring(classAttr.indexOf(":") + 1, classAttr.length());
                        Context ctxCopy = (Context)ctx.clone();
                        ctxCopy.putAll(record);
                        RuleImpl ruleImpl = RulesResources.getInstance(ctx).findRule(eval);
                        Object obj = ruleImpl.execute(ctxCopy, null);
                        if (obj != null) {
                          rowClass = obj.toString();
                        }
                      }
                      String appendValue = ParseUtil.getValueFromObject(Integer.valueOf(this.index), null, null);
                      newRecord.setAttribute("class", rowClass);
                      
                      boolean isTextAreaExist = false;
                      Element cloneDivTextArea = null;
                      List newRecordList = newRecord.getChildren();
                      for (int j = 0; j < newRecordList.size(); j++) {
                        if ((newRecordList.get(j) != null) && ((newRecordList.get(j) instanceof Element)))
                        {
                          Element rowElement = (Element)newRecordList.get(j);
                          if ((rowElement.getAttribute("id") != null) && (rowElement.getAttribute("id").getValue().contains("textareacharcountrow")))
                          {
                            cloneDivTextArea = ParseUtil.createCopyForElement((Element)rowElement.clone());
                            rowElement.detach();
                            isTextAreaExist = true;
                          }
                        }
                      }
                      processDataRow(ctx, newRecord, record, this.blockImpl, this.fieldNames, appendValue, element, readOnlyResourse, request, isTextAreaExist, this.index, isRowLinkAjaxExist, false, pageName);
                      if (isTextAreaExist)
                      {
                        newRecord.addContent(cloneDivTextArea);
                        addTextAreaCharactersCountDiv(newRecord, this.index - 1);
                      }
                      this.index += 1;
                      list.add(newRecord.detach());
                    }
                  }
                  sibling.detach();
                  i--;
                  element.addContent(list);
                  element.addContent(this.resList);
                }
                else
                {
                  element.removeContent(sibling.detach());
                  i--;
                }
              }
              else if (child_Id.contains("_freeform_"))
              {
                new FreeFormGenerator(this.metadataResources, this.pagecomponentImpl, this.componentImpl).parseFreeform(ctx, sibling, sibling.getChildren(), ctx, ctx, readOnlyResourse, ajax_append_field_value, request, pageName, actualreadOnlyResourse);
                sibling.detach();
                Element cloneSibling = ParseUtil.createCopyForElement(sibling);
                element.addContent(cloneSibling);
                break;
              }
            }
            parseListFreeform(ctx, sibling, sibling.getChildren(), data, readOnlyResourse, ajax_append_field_value, request, pageName, actualreadOnlyResourse);
          }
        }
      }
    }
  }
  
  private List checkForDynamicHeadersMap(List list)
    throws Exception
  {
    LinkedList newList = new LinkedList();
    if ((list != null) && (list.size() > 0)) {
      for (int i = 0; i < list.size(); i++)
      {
        Map map = (Map)list.get(i);
        if (!map.containsKey("dynamicHeadersMap")) {
          newList.add(map);
        }
      }
    }
    return newList;
  }
  
  private void getEmptyListMsg(Context ctx, Element emptyRowElement, Element sibling, String pageName, List fieldNames)
    throws Exception
  {
    List children = sibling.getChildren();
    for (int i = 0; i < children.size(); i++)
    {
      Element child = (Element)children.get(i);
      
      Context ctxCopy = (Context)ctx.clone();
      
      int accessType = new SecurityParser().getAccessTypeBySecurity(ctxCopy, child, this.componentImpl.getName(), true);
      if (SecurityParser.isHideResource(accessType))
      {
        child.detach();
        i--;
      }
      else if (processElementForHide(child, ctxCopy, pageName))
      {
        ctxCopy = null;
        i--;
      }
      else if (new HelpMessageProcessor().parseElement(ctx, child))
      {
        i--;
      }
      else
      {
        if (child.getAttributeValue("id") != null)
        {
          String field = child.getAttributeValue("id");
          field = getFieldNameForList(field);
          if (isColumn(field, fieldNames))
          {
            Element nrfTD = new Element("td");
            nrfTD.setAttribute("id", child.getAttributeValue("id"));
            if (child.getAttributeValue("style") != null) {
              nrfTD.setAttribute("style", child.getAttributeValue("style"));
            }
            if (child.getName().equals("td"))
            {
              if ((child.getChildren() != null) && (child.getChildren().size() > 0)) {
                for (int j = 0; j < child.getChildren().size(); j++)
                {
                  Element ele = (Element)child.getChildren().get(j);
                  if ((ele.getName().equals("input")) || (ele.getName().equals("select")))
                  {
                    nrfTD.addContent("");
                    ele.detach();
                  }
                  else
                  {
                    nrfTD.addContent(ele.detach());
                  }
                  j--;
                }
              }
              child.detach();
            }
            else
            {
              nrfTD.addContent(child.detach());
            }
            emptyRowElement.addContent(nrfTD.detach());
            i--;
            continue;
          }
        }
        getEmptyListMsg(ctx, emptyRowElement, child, pageName, fieldNames);
      }
    }
  }
}
