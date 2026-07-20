package com.util;

import java.io.FileInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.manage.managemetadata.util.exception.ValidationException;

public class RequestProcessor {
	private static final InetLogger logger = InetLogger.getInetLogger(RequestProcessor.class);
	protected String COMPEMENT = "component";
	protected String ACTION = "action";
	protected String REQUEST = "request";
	protected String REQUESTDATA = "requestdata";
	protected String RESPONSE = "response";
	protected String DATA = "data";
	protected String ERROR = "error";
	protected String INET_PROJECT_ID = "inet_project_id";
	protected String INET_MOM_LIST = "inet_mom_list";
	protected String INET_MOM_LIST_NAME = "inet_mom_list_name";
	protected String INET_DATA_LIST = "inet_data_list";
	protected String INET_DATA_LIST_NAME = "inet_data_list_name";
	protected String[] data_names = null;
	protected String[] mom_names = new String[]{"0"};

	public void populateContext(Context ctx, String strXml) throws Exception {
		Document document = this.getDocument(strXml);
		Element root = document.getRootElement();
		Element projecttElement = root.getChild(this.INET_PROJECT_ID);
		if (projecttElement != null) {
			ctx.put(this.INET_PROJECT_ID, projecttElement.getText());
		}

		Element componentElement = root.getChild(this.COMPEMENT);
		if (componentElement != null) {
			ctx.put(this.COMPEMENT, componentElement.getText());
		}

		Element actionElement = root.getChild(this.ACTION);
		if (actionElement != null) {
			ctx.put(this.ACTION, actionElement.getText());
		}

		Element eventElement = root.getChild("inet_eventid");
		if (eventElement != null) {
			ctx.put("inet_eventid", eventElement.getText());
		}

		Element momElement = root.getChild(this.INET_MOM_LIST);
		if (momElement != null) {
			String dataElement = momElement.getText();
			if (dataElement.contains(":")) {
				this.mom_names = dataElement.split(":");
			} else {
				this.mom_names[0] = dataElement;
			}

			ctx.put(this.INET_MOM_LIST_NAME, dataElement);
		}

		Element arg13 = root.getChild(this.INET_DATA_LIST);
		if (arg13 != null) {
			String list = arg13.getText();
			if (list.contains(":")) {
				this.data_names = list.split(":");
			} else {
				ctx.put(this.INET_DATA_LIST_NAME, list);
			}
		}

		List arg14 = root.getChildren(this.REQUESTDATA);
		if (arg14 != null) {
			for (int i = 0; i < arg14.size(); ++i) {
				Element childElement = (Element) arg14.get(i);
				this.processElement(ctx, childElement);
			}
		}

	}

	public void processElement(Context ctx, Element element) throws Exception {
		List list = element.getChildren();
		String mom_list_name = null;
		if (ctx.get(this.INET_MOM_LIST_NAME) != null) {
			mom_list_name = (String) ctx.get(this.INET_MOM_LIST_NAME);
		}

		String data_list_name = null;
		if (ctx.get(this.INET_DATA_LIST_NAME) != null) {
			data_list_name = (String) ctx.get(this.INET_DATA_LIST_NAME);
		}

		if (list != null) {
			for (int i = 0; i < list.size(); ++i) {
				Element childElement = (Element) list.get(i);
				String key = childElement.getName();
				String value = childElement.getText();
				if (data_list_name != null && key.equals(data_list_name)) {
					this.processMomListElement(ctx, childElement,
							this.INET_DATA_LIST);
				} else {
					ctx.put(key, value);
				}

				int j;
				String list_name;
				if (this.data_names != null) {
					for (j = 0; j < this.data_names.length; ++j) {
						list_name = this.data_names[j];
						if (key.equals(list_name)) {
							this.processMomListElement(ctx, childElement,
									list_name);
						}
					}
				}

				if (this.mom_names != null) {
					for (j = 0; j < this.mom_names.length; ++j) {
						list_name = this.mom_names[j];
						if (key.equals(list_name)) {
							this.processMomListElement(ctx, childElement,
									list_name);
						}
					}
				}
			}
		}

	}

	public String generateRequestXml(Context ctx) throws Exception {
		Element resElement = new Element(this.REQUEST);
		Element reqdataElement = new Element(this.REQUESTDATA);
		Set keys = ctx.keySet();
		Iterator iterator = keys.iterator();

		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			Object obj = ctx.get(key);
			Element element;
			if (obj instanceof List) {
				if (key.equals("inet_errors_list")) {
					element = new Element(key);
					this.parseErrorList(element, (List) obj, reqdataElement);
					reqdataElement.addContent(element);
				} else {
					element = new Element(key);
					this.parseList(element, (List) obj);
					reqdataElement.addContent(element);
				}
			} else if (obj instanceof Map) {
				element = new Element(key);
				this.parseMap(element, (Map) obj);
				reqdataElement.addContent(element);
			} else {
				reqdataElement.addContent(this.makeElement(key,
						ObjectUtils.getValueFromObject(obj)));
			}
		}

		resElement.addContent(reqdataElement);
		return HTMLUtils.beautifyHTML(resElement);
	}

	public void parseErrorList(Element parentElement, List list,
			Element resDataElement) throws Exception {
		if (list != null) {
			for (int i = 0; i < list.size(); ++i) {
				Object obj = list.get(i);
				if (obj instanceof Map) {
					Element exp = this.makeElement(this.ERROR, (String) null);
					this.parseRow(exp, (Map) obj);
					parentElement.addContent(exp);
				} else if (obj instanceof ValidationException) {
					ValidationException arg7 = (ValidationException) obj;
					Element rowElement = new Element("error_" + arg7.getField());
					rowElement.setText(arg7.getMessage());
					resDataElement.addContent(rowElement);
					rowElement = new Element("ajax_error_" + arg7.getField());
					rowElement.setText(arg7.getMessage());
					resDataElement.addContent(rowElement);
				}
			}

		}
	}

	public void processMomListElement(Context ctx, Element momElement,
			String list_name) throws Exception {
		List dataList = momElement.getChildren();
		ArrayList list = new ArrayList();
		if (dataList != null) {
			for (int i = 0; i < dataList.size(); ++i) {
				Element dataElement = (Element) dataList.get(i);
				this.processDataElement(list, dataElement);
			}
		}

		ctx.put(list_name, list);
	}

	public void processDataElement(List list, Element dataElement)
			throws Exception {
		List recordList = dataElement.getChildren();
		HashMap map = new HashMap();
		if (recordList != null) {
			for (int i = 0; i < recordList.size(); ++i) {
				Element element = (Element) recordList.get(i);
				String key = element.getName();
				String value = element.getText();
				map.put(key, value);
			}

			list.add(map);
		}

	}

	public Document getDocument(String strXml) {
		Document document = null;
		SAXBuilder builder = new SAXBuilder();

		try {
			document = builder.build(new StringReader(strXml));
		} catch (Exception arg4) {
			;
		}

		return document;
	}

	public Element makeElement(String tagName, String text) {
		Element element = null;
		if (tagName != null) {
			if (StringUtils.isNumeric(tagName.substring(0, 1))) {
				tagName = "_" + tagName;
			}

			element = new Element(tagName.trim());
		}

		if (text != null) {
			element.setText(text);
		}

		return element;
	}

	public String generateResponseXml(Context ctx, boolean isAppendMessages)
			throws Exception {
		Element resElement = new Element(this.RESPONSE);
		Set keys = ctx.keySet();
		Iterator iterator = keys.iterator();

		while (true) {
			String key;
			Object obj;
			Element element;
			while (true) {
				if (!iterator.hasNext()) {
					if (isAppendMessages) {
						this.appendMessageXML(ctx, resElement);
					}

					return HTMLUtils.beautifyHTML(resElement);
				}

				key = (String) iterator.next();
				obj = ctx.get(key);
				if (!(obj instanceof List)) {
					break;
				}

				if (key.equals("inet_errors_list")) {
					element = new Element(key);
					this.parseErrorList(element, (List) obj, ctx);
					resElement.addContent(element);
					break;
				}

				if (!key.equals("page_list_stack")) {
					element = new Element(key);
					this.parseList(element, (List) obj);
					resElement.addContent(element);
					break;
				}
			}

			if (obj instanceof List) {
				if (key.equals("inet_errors_list")) {
					element = new Element("errors_list");
					this.parseErrorsList(element, (List) obj, ctx);
					resElement.addContent(element);
				}
			} else if (obj instanceof Map) {
				element = new Element(key);
				this.parseMap(element, (Map) obj);
				resElement.addContent(element);
			} else if (obj instanceof HttpServletRequest) {
				element = new Element(key);
				this.parseRequest(element, (HttpServletRequest) obj, ctx);
				resElement.addContent(element);
			} else {
				resElement.addContent(this.makeElement(key,
						ObjectUtils.getValueFromObject(obj)));
			}
		}
	}

	public void parseList(Element parentElement, List list) throws Exception {
		if (list != null) {
			for (int i = 0; i < list.size(); ++i) {
				Object obj = list.get(i);
				if (obj instanceof Map) {
					Element rowElement = this.makeElement(this.DATA,
							(String) null);
					this.parseRow(rowElement, (Map) obj);
					parentElement.addContent(rowElement);
				}
			}

		}
	}

	public void parseErrorList(Element parentElement, List list, Context ctx)
			throws Exception {
		if (list != null) {
			for (int i = 0; i < list.size(); ++i) {
				Object obj = list.get(i);
				Element rowElement;
				if (obj instanceof Map) {
					rowElement = this.makeElement(this.ERROR, (String) null);
					this.parseRow(rowElement, (Map) obj);
					parentElement.addContent(rowElement);
				}

				if (obj instanceof ValidationException) {
					rowElement = this.makeElement(this.ERROR, (String) null);
					this.parseRow(rowElement, (ValidationException) obj, ctx);
					parentElement.addContent(rowElement);
				}
			}

		}
	}

	public void parseRow(Element parentElement, Map map) throws Exception {
		if (map != null) {
			Set keys = map.keySet();
			Iterator iterator = keys.iterator();

			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				if (!key.equals("bread_crumb_page_request")) {
					Object obj = map.get(key);
					String value = null;
					if (obj != null) {
						Element mapElement;
						if (obj instanceof List) {
							mapElement = new Element(key);
							parentElement.addContent(mapElement);
							this.parseList(mapElement, (List) obj);
						} else if (obj instanceof Map) {
							mapElement = new Element(key);
							parentElement.addContent(mapElement);
							this.parseMap(mapElement, (Map) obj);
						} else {
							value = ObjectUtils.getValueFromObject(obj);
							parentElement.addContent(this.makeElement(key,
									value));
						}
					}
				}
			}

		}
	}

	public void parseRow(Element parentElement, ValidationException exp,
			Context ctx) throws Exception {
		if (exp != null) {
			parentElement.addContent(this.makeElement(exp.getField(),
					exp.getMessage()));
		}
	}

	public void parseMap(Element parentElement, Map map) throws Exception {
		if (map != null) {
			Element rowElement = this.makeElement(this.DATA, (String) null);
			Set keys = map.keySet();
			Iterator iterator = keys.iterator();

			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object obj = map.get(key);
				String value = null;
				if (obj != null) {
					Element listElement;
					if (obj instanceof Map) {
						listElement = new Element(key);
						rowElement.addContent(listElement);
						this.parseMap(listElement, (Map) obj);
					} else if (obj instanceof List) {
						listElement = new Element(key);
						rowElement.addContent(listElement);
						this.parseList(listElement, (List) obj);
					} else {
						value = ObjectUtils.getValueFromObject(obj);
						rowElement.addContent(this.makeElement(key, value));
					}
				}
			}

			parentElement.addContent(rowElement);
		}
	}

	protected void populateContext(Context ctx) {
		ctx.put("Langauge", "Hindi");
		ctx.put("Prefix", "Miss");
		ctx.put("cust_id", "35");
		this.populateList(ctx);
	}

	protected void populateList(Context ctx) {
		ArrayList list_block = new ArrayList();
		HashMap record1 = new HashMap();
		record1.put("name", "raghuraj");
		record1.put("address", "delhi");
		list_block.add(record1);
		HashMap record2 = new HashMap();
		record2.put("name", "singh");
		record2.put("address", "jaipur");
		list_block.add(record2);
		ctx.put("policylist_list_1", list_block);
	}

	public static void main(String[] args) {
		try {
			Context e = new Context();
			RequestProcessor rp = new RequestProcessor();
			String strXml = IOUtils
					.readFile("D:\\OutlineSys\\ManagePSM\\components.xml");
			rp.populateContext(e, strXml);
		} catch (Exception arg3) {
			logger.error("Unexpected error", arg3);
		}

	}

	public void parseRequest(Element parentElement,
			HttpServletRequest httpRequest, Context ctx) throws Exception {
		Element rowElement = this.makeElement(this.DATA, (String) null);
		this.parseRequestDataRow(rowElement, httpRequest, ctx);
		parentElement.addContent(rowElement);
	}

	public void parseRequestDataRow(Element parentElement,
			HttpServletRequest httpRequest, Context ctx) throws Exception {
		if (ctx.get("multiPartFormDataMap") != null
				&& ctx.get("multiPartFormDataMap") instanceof Map) {
			Map paramNames1 = (Map) ctx.get("multiPartFormDataMap");
			Set name1 = paramNames1.entrySet();
			Iterator val1 = name1.iterator();

			while (val1.hasNext()) {
				Entry entry = (Entry) val1.next();
				parentElement.addContent(this.makeElement(
						(String) entry.getKey(), (String) entry.getValue()));
			}
		} else {
			Enumeration paramNames = httpRequest.getParameterNames();
			String name = null;
			String val = null;

			while (paramNames.hasMoreElements()) {
				name = (String) paramNames.nextElement();
				val = httpRequest.getParameter(name);
				parentElement.addContent(this.makeElement(name, val));
			}
		}

	}

	public void appendMessageXML(Context ctx, Element resElement)
			throws Exception {
		Element parentElement = new Element("messages");
		Element dataElement = new Element("data");
		parentElement.addContent(dataElement);
		SAXBuilder builder = new SAXBuilder();
		Document document;
		try (FileInputStream fin = new FileInputStream(SystemProperties
				.getInstance().getString("xml.basedir")
				+ ctx.getProject()
				+ "//" + "messages" + ".xml")) {
			document = builder.build(fin);
		}
		Element rootElement = document.getRootElement();
		List childList = rootElement.getChildren();

		for (int i = 0; i < childList.size(); ++i) {
			Element row = (Element) childList.get(i);
			Element newElement = new Element(row.getAttributeValue("msgkey"));
			newElement.setText(row.getAttributeValue("message"));
			dataElement.addContent(newElement);
		}

		resElement.addContent(parentElement);
	}

	public void parseErrorsList(Element parentElement, List list, Context ctx)
			throws Exception {
		if (list != null) {
			for (int i = 0; i < list.size(); ++i) {
				Object obj = list.get(i);
				Element rowElement;
				if (obj instanceof Map) {
					rowElement = this.makeElement(this.ERROR, (String) null);
					this.parseErrorsRow(rowElement, (Map) obj);
					parentElement.addContent(rowElement);
				}

				if (obj instanceof ValidationException) {
					rowElement = this.makeElement(this.ERROR, (String) null);
					this.parseErrorsRow(rowElement, (ValidationException) obj,
							ctx);
					parentElement.addContent(rowElement);
				}
			}

		}
	}

	public void parseErrorsRow(Element parentElement, Map map) throws Exception {
		if (map != null) {
			Set keys = map.keySet();
			Iterator iterator = keys.iterator();

			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Object obj = map.get(key);
				String value = null;
				if (obj != null) {
					value = ObjectUtils.getValueFromObject(obj);
					parentElement
							.addContent(this.makeElement("message", value));
				}
			}

		}
	}

	public void parseErrorsRow(Element parentElement, ValidationException exp,
			Context ctx) throws Exception {
		if (exp != null) {
			parentElement.addContent(this.makeElement("message",
					exp.getMessage()));
		}
	}

	public String generateResponseXmlForXSLT(Context ctx,
			boolean isAppendMessages) throws Exception {
		Element resElement = new Element(this.RESPONSE);
		Set keys = ctx.keySet();
		Iterator iterator = keys.iterator();

		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			Object obj = ctx.get(key);
			Element element;
			if (obj instanceof List) {
				if (key.equals("inet_errors_list")) {
					element = new Element(key);
					this.parseErrorList(element, (List) obj, ctx);
					resElement.addContent(element);
				} else {
					element = new Element(key);
					this.parseList(element, (List) obj);
					resElement.addContent(element);
				}
			}

			if (obj instanceof List) {
				if (key.equals("inet_errors_list")) {
					element = new Element("errors_list");
					this.parseErrorsList(element, (List) obj, ctx);
					resElement.addContent(element);
				}
			} else if (obj instanceof Map) {
				element = new Element(key);
				this.parseMap(element, (Map) obj);
				resElement.addContent(element);
			} else if (obj instanceof HttpServletRequest) {
				element = new Element(key);
				this.parseRequest(element, (HttpServletRequest) obj, ctx);
				resElement.addContent(element);
			} else {
				resElement.addContent(this.makeElement(key,
						ObjectUtils.getValueFromObject(obj)));
			}
		}

		if (isAppendMessages) {
			this.appendMessageXML(ctx, resElement);
		}

		return HTMLUtils.beautifyHTML(resElement);
	}

	public String generateResponseXml(Context ctx) throws Exception {
		Element resElement = new Element(this.RESPONSE);
		Set keys = ctx.keySet();
		Iterator iterator = keys.iterator();

		while (true) {
			while (true) {
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					Object obj = ctx.get(key);
					Element element;
					if (obj instanceof List) {
						if (key.equals("inet_errors_list")) {
							element = new Element(key);
							this.parseErrorList(element, (List) obj, resElement);
							if ((List) obj != null && ((List) obj).size() > 0) {
								String errors = null;

								for (int j = 0; j < ((List) obj).size(); ++j) {
									ValidationException exp = (ValidationException) ((List) obj)
											.get(j);
									errors = errors == null ? exp.getField()
											+ " : " + exp.getMessage() : errors
											+ ", " + exp.getField() + " : "
											+ exp.getMessage();
								}

								element.addContent(errors);
							}

							resElement.addContent(element);
						} else {
							element = new Element(key);
							this.parseList(element, (List) obj);
							resElement.addContent(element);
						}
					} else if (obj instanceof Map) {
						element = new Element(key);
						this.parseMap(element, (Map) obj);
						resElement.addContent(element);
					} else {
						resElement.addContent(this.makeElement(key,
								ObjectUtils.getValueFromObject(obj)));
					}
				}

				return HTMLUtils.beautifyHTML(resElement);
			}
		}
	}
}
