package com.manage.parser;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Element;

import com.manage.managemetadata.metadata.Field;
import com.manage.managemetadata.metadata.MetaDataResources;
import com.util.Context;
import com.util.DateUtils;
import com.util.InetLogger;
import com.util.StringUtils;

public class ParseUtil
{
  private static InetLogger logger = InetLogger.getInetLogger(ParseUtil.class);

  protected static String getValueForInputByName(Element sibling, Map currentData, Context ctx) throws Exception {
    String value = "";
    if (sibling.getAttributeValue("name") != null) {
      String name = sibling.getAttributeValue("name").toString();
      if ((currentData != null) && 
        (currentData.containsKey(name)) && (currentData.get(name) != null)) {
        Object object = currentData.get(name);
        value = getValueFromObject(object, name, ctx);
      }
    }

    return value;
  }

  public static void addAttribute(Element element, String attributeName, String value)
  {
    if ((element != null) && (attributeName != null))
      element.setAttribute(attributeName, value);
  }

  public static Element getElementByAttribute(Element element, String attributeType, String value)
  {
    if (element == null) {
      return null;
    }
    List children = element.getChildren();
    if (children == null) {
      return null;
    }
    int i = 0; if (i < children.size())
    {
      Element child = (Element)children.get(i);
      Attribute attribute = child.getAttribute(attributeType);
      if (attribute != null)
      {
        String type = attribute.getValue();
        if (value.equals(type))
          return child;
      }
      return getElementByAttribute(child, attributeType, value);
    }
    return null;
  }

  public static Element createCopyForElement(Element element)
  {
    if (element == null) {
      return null;
    }
    String name = element.getName();
    Element copyElement = new Element(name);

    List attributes = element.getAttributes();
    if (attributes != null) {
      for (int i = 0; i < attributes.size(); i++)
      {
        Attribute attribute = (Attribute)attributes.get(i);
        String attName = attribute.getName();
        String attValue = attribute.getValue();
        addAttribute(copyElement, attName, attValue);
      }

    }

    String text = element.getText();
    if (text != null) {
      copyElement.setText(text);
    }
    List children = element.getChildren();
    List copyChildren = new ArrayList();
    if (children != null)
    {
      for (int j = 0; j < children.size(); j++)
      {
        Element child = (Element)children.get(j);
        child.detach();
        Element copyChild = createCopyForElement(child);
        copyChildren.add(copyChild);
        j--;
      }
      copyElement.addContent(copyChildren);
    }

    return copyElement;
  }

  public static String[] processHref(String href)
    throws Exception
  {
    if (!href.contains("(")) {
      return null;
    }
    if (!href.contains(")"))
      return null;
    try
    {
      int startIndex = href.indexOf("(");
      int endIndex = href.indexOf(")");

      String str = href.substring(startIndex + 1, endIndex);
      String[] hrefs = str.split(",");
      return trimHrefValue(hrefs, "'");
    }
    catch (Exception e) {
      logger.error(href + " was not in correct format");
    }return null;
  }

  public static String[] trimHrefValue(String[] hrefs, String trimString)
    throws Exception
  {
    String[] strs = new String[hrefs.length];
    for (int i = 0; i < hrefs.length; i++)
    {
      String element = hrefs[i];
      int start = element.indexOf(trimString);
      int end = element.lastIndexOf(trimString);
      element = element.substring(start + 1, end);
      element = element.trim();
      strs[i] = element;
    }

    return strs;
  }

  public static Element createInputElement(String name, String type, String value, String id)
  {
    Element input = new Element("input");
    addAttribute(input, "type", type);
    addAttribute(input, "name", name);
    addAttribute(input, "value", value);
    addAttribute(input, "id", id);
    return input;
  }

  public static boolean isElementContainingAttributeWithValue(Element element, String type, String value)
  {
    Attribute idAttribute = element.getAttribute(type);
    if (idAttribute != null)
    {
      String id = idAttribute.getValue();
      if (id.contains(value))
        return true;
    }
    return false;
  }

  public static String getValueFromObject(Object object, String name, Context ctx) {
    String value = "";

    if (object == null) {
      return value;
    }
    if ((object instanceof Date)) {
      if (!StringUtils.isBlank(name))
        try {
          Field fld = MetaDataResources.getInstance(ctx).getField(name);
          if (!StringUtils.isBlank(fld.getPattern())) {
            value = DateUtils.convertDateToString((Date)object, fld.getPattern());
          }
          return DateUtils.convertDateToString((Date)object, "MM/dd/yyyy");
        }
        catch (Exception e)
        {
          value = DateUtils.convertDateToString((Date)object, "MM/dd/yyyy");
        }
      else
        value = DateUtils.convertDateToString((Date)object, "MM/dd/yyyy");
    } else if ((object instanceof Timestamp)) {
      if (!StringUtils.isBlank(name))
        try {
          Field fld = MetaDataResources.getInstance(ctx).getField(name);
          if (!StringUtils.isBlank(fld.getPattern())) {
            value = DateUtils.convertDateToString((Timestamp)object, fld.getPattern());
          }
          return DateUtils.convertDateToString((Timestamp)object, "MM/dd/yyyy");
        }
        catch (Exception e)
        {
          value = DateUtils.convertDateToString((Timestamp)object, "MM/dd/yyyy");
        }
      else
        value = DateUtils.convertDateToString((Timestamp)object, "MM/dd/yyyy");
    }
    else value = object.toString();

    return value;
  }

  public static String appendValueWithCoumn(String control_name, String appendValue)
  {
    if (appendValue == null) {
      return control_name;
    }
    return control_name + "_" + appendValue;
  }
}