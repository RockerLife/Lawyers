package com.util;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class HTMLUtils {
	protected static String EMPTY_NODE = "#EmptyNode#";

	public static String beautifyHTML(Document document) {
		Element root = document.getRootElement();
		List rootchildList = root.getChildren();
		populateElements(rootchildList);
		String str = (new XMLOutputter()).outputString(root);
		str = str.replaceAll(EMPTY_NODE, "");
		return str;
	}

	public static String beautifyHTML(Element root) {
		if (root == null) {
			return null;
		} else {
			List rootchildList = root.getChildren();
			populateElements(rootchildList);
			String str = (new XMLOutputter()).outputString(root);
			str = str.replaceAll(EMPTY_NODE, "");
			return str;
		}
	}

	private static void populateElements(List rootchildList) {
		for (int i = 0; i < rootchildList.size(); ++i) {
			Element childElement = (Element) rootchildList.get(i);
			List childlist = childElement.getChildren();
			if (childlist.isEmpty()) {
				String text = childElement.getText();
				if (text.equals((Object) null) || text.equals("")) {
					childElement.addContent(EMPTY_NODE);
				}
			}

			if (!childlist.isEmpty()) {
				populateElements(childlist);
			}
		}

	}
}