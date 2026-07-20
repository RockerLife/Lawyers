package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXMLFile {
	private static final InetLogger logger = InetLogger.getInetLogger(ReadXMLFile.class);

	

	public static Map<Integer, HashMap<String,String>> getXMLData(InputStream xml){
		try {
			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			SAXParser parser = parserFactor.newSAXParser();
			SAXHandler handler = new SAXHandler();
			parser.parse(xml,handler);
			
			return handler.xml;
		} catch (SAXException se) {
			logger.error("Unexpected error", se);
		} catch (IOException e) {
			logger.error("Unexpected error", e);
		} catch (ParserConfigurationException pce) {
			logger.error("Unexpected error", pce);
		}
		return null;
	}
}

		

/**
 * The Handler for SAX Events.
 */
class SAXHandler extends DefaultHandler {

	Map<Integer, HashMap<String,String>> xml = new HashMap<Integer, HashMap<String,String>>();
	HashMap<String,String> innerMap = null;
	String flVal = "";
	String flAttrVal = "";
	Integer rowno = 0;

	@Override
	// Triggered when the start of tag is found.
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		switch (qName) {
		// Create a new Employee object when the start tag is found
		case "row":
			rowno = Integer.parseInt(attributes.getValue("no"));
			innerMap = new HashMap<String,String>();
			break;
		case "FL":
			flAttrVal = attributes.getValue("val");
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		switch (qName) {
		// Add the employee to list once end tag is found
		case "row":
			xml.put(rowno, innerMap);
			break;
		// For all other end tags the employee has to be updated.
		case "FL":
			innerMap.put(flAttrVal, flVal);
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		flVal = String.copyValueOf(ch, start, length).trim().replace("null", "");
	}

}
