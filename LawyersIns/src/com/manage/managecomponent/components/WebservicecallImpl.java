package com.manage.managecomponent.components;



import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;

import com.manage.managebusinessrules.rules.RuleImpl;
import com.manage.managebusinessrules.rules.RulesResources;
import com.manage.managemetadata.metadata.MetaDataResources;
import com.manage.managemetadata.metadata.MetaobjectImpl;
import com.manage.managemetadata.metadata.PropertyImpl;
import com.userbo.LawyersUtils;
import com.util.Context;
import com.util.IContext;
import com.util.IOUtils;
import com.util.InetLogger;
import com.util.SystemProperties;
import com.util.XMLUtils;

public class WebservicecallImpl extends Webservicecall {
	
	private final String VALIDATIONERROR = "validationerrors";
	private static InetLogger logger = InetLogger.getInetLogger(WebservicecallImpl.class);
	public boolean evaluate(IContext data) throws Exception {
		// TODO Auto-generated method stub
		//return true;
		if(getEval() == null || "".equals(getEval()))
            return true;
            
            boolean validate = false;
            try{
                  RuleImpl ruleImpl = RulesResources.getInstance(data).findRule(getEval());
                  if(ruleImpl != null)
                        validate = ruleImpl.evaluate((Context)data, null);
            }catch(Exception e){
                  logger.error((Context)data, "Unable to execute eval for service due to error " + e.getMessage());
                  throw e;
            }
            
            return validate;

	}

	/**
	 *  
	 *  <Response>
     <ValidationErrors>
          <Error>Defense Expenses are only been paid outside for the Limit less than 1M/1M</Error>
     </ValidationErrors>
	</Response>
	 */
	public Object execute(IContext data) throws Exception {

		MetaobjectImpl outputMo = MetaDataResources.getInstance(data)
				.getMetaobject(getInput());

		Document doc = populateInput(data, outputMo);
		String inputString = XMLUtils.beautifyXML(doc);

		logger.debug("Calling configured web service");
		String outputString = sendRequest(inputString);

		logger.debug("Configured web service call completed");
		Element root = XMLUtils.parseXMLRootElement(outputString);

		Element outputElement = root.getChild(getOutput());

		// Element ruleTrace = root.getChild("RuleEngineTrace");
		// String ruleTraceStr = ruleTrace.getText();
		// Map map = new HashMap();
		// map.put("outputXml", traverseDocument(outputElement));
		// map.put("TraceString", ruleTraceStr);
		
		if(outputElement == null)
			outputElement = root.getChild("ValidationErrors");
			
		Map map = populateOutput(outputElement);

		map.put("InputXML", inputString);
		map.put("OutputXML", outputString);
		return map;
	}

	private Document populateInput(IContext ctx, MetaobjectImpl outputMo)
			throws Exception {

		List list = outputMo.getFirstPropertyversion().getPropertyList();
		Document listDocument = new Document();
		Element rootElement = new Element("PIMWebService");
		rootElement.setAttribute("SERVICE_NAME", getName());

		listDocument.addContent(rootElement);
		Element listElement = new Element(outputMo.getName());
		rootElement.addContent(listElement);
		for (int i = 0; i < list.size(); i++) {
			PropertyImpl propertyImpl = (PropertyImpl) list.get(i);
			Element propertyElement = new Element(propertyImpl.getField()
					.getName());
			listElement.addContent(propertyElement);
			propertyElement.addContent(ctx.get(propertyImpl.getField()
					.getName()) == null ? "" : ctx.get(
					propertyImpl.getField().getName()).toString());
		}

		return listDocument;
	}

	public String sendRequest(String inputXml) {
		String outputXML = "";
		try {
			 String serviceURL = getServiceurl();
			//String serviceURL = "http://localhost:8080/RatingEngine/rating";

			if (!serviceURL.contains(".com/")) {
				serviceURL = SystemProperties.getInstance().getString(
						serviceURL);
			}

			// System.out.println("##############" + serviceURL);

			URL url = new URL(serviceURL);

			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(connection
					.getOutputStream());
			writer.write(inputXml);
			writer.flush();

			// BufferedReader rd = new BufferedReader(new
			// InputStreamReader(connection.getInputStream()));
			outputXML = IOUtils.readFile(connection.getInputStream());
			// String line;
			// while ((line = rd.readLine()) != null) {
			// System.out.println(line.toString());
			// }
			writer.close();
			// rd.close();
		} catch (Exception exception) {
			logger.error("Unexpected error", exception);
		}
		return outputXML;
	}

	public String traverseDocument(Element element) {
		StringBuffer buffer = new StringBuffer();
		List children = element.getChildren();
		if (children == null)
			return "";

		for (int i = 0; i < children.size(); i++) {
			Element child = (Element) children.get(i);
			String name = child.getName();
			String str = child.getTextTrim();
			buffer.append(name).append(" : ").append(str).append("\n");
		}

		return buffer.toString();
	}

	public Map populateOutput(Element element) {
		StringBuffer buffer = new StringBuffer();
		List children = element.getChildren();
		if (children == null)
			return new HashMap();
		Map out = new HashMap();
		for (int i = 0; i < children.size(); i++) {
			Element child = (Element) children.get(i);
			String name = child.getName();
			String str = child.getTextTrim();
			out.put(name, str);
		}

		return out;
	}

}
