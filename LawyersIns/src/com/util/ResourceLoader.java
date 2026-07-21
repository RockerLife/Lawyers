package com.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;

import org.apache.commons.configuration.Configuration;

import com.xmlobjectbuilder.XMLParser;
/**
 * @author Nilesh
 *
 * TODO don't forget to add the javadoc!
 */
public class ResourceLoader 
{
	private static InetLogger logger = InetLogger.getInetLogger(ResourceLoader.class);
	
	private static final String RESOURCE_TYPE = "xml";
	private static final String TEMPLATE = "template";
	private static final String PACKAGENAME = "packagename";
	private static final String VALIDATE = "validate";
	private static final String SCHEMA = "schema";
	
	private static final String RESOURCEOBJECT = "resourceobject";
	private static final String RESOURCES_KEY = "resources";
	
	public static IResourceKeys parse(String fileName, String resourceIdentifier, boolean commonDefMerge, String projectName) throws Exception
	{
		Configuration conf = getResourceConfiguration(resourceIdentifier);
		if(conf==null)
			return null;
		
		XMLParser parser = configureXMLParser(conf);
		parser.setTemplateType(resourceIdentifier);
		parser.setDefinationFile(fileName);
		
		return (IResourceKeys) parser.parse(commonDefMerge, projectName);
	}
	
	public static IResourceKeys load(StringBuffer xml, String resourceIdentifier, String projectName) throws Exception
	{
	    try
		{
			Configuration conf = getResourceConfiguration(resourceIdentifier);
			if(conf==null)
				return null;
			
			IResourceKeys root = null;
			try
			{
			    XMLParser parser = configureXMLParser(conf);
				parser.setTemplateType(resourceIdentifier);
				parser.setDefinationXML(xml.toString());
				
				root = (IResourceKeys) parser.parse(true, projectName);
			}catch(Exception e)
			{
				logger.error("Unexpected error", e);
				logger.error(e.getMessage());
				return null;
			}
			
			String resourceObject = conf.getString(RESOURCEOBJECT);
			
			Object resourceObjectInstance = constructResourceObject(resourceObject, root);
			putResourceInCache(root, resourceIdentifier, resourceObjectInstance);
			
			return root;
		}catch(Exception e)
		{
			logger.trace(e);
			return null;
		}
	}
	
	public static IResourceKeys load(String fileName, String resourceIdentifier, String projectName) throws Exception
	{
		Context ctx = new Context();
		ctx.setProject(projectName);
		
		try	{
			Configuration conf = getResourceConfiguration(resourceIdentifier);
			if(conf==null)
				return null;
			
			IResourceKeys root = null;
			try	{
				fileName = fileName.replace("//", File.separator);
				fileName = fileName.replace("\\", File.separator);
				
				root = parse(fileName, resourceIdentifier, true, projectName);
			}catch(FileNotFoundException fe) {
				if(SystemProperties.getInstance().getProperty("appl.logs.required") != null && 
						SystemProperties.getInstance().getProperty("appl.logs.required").toString().equalsIgnoreCase("Y")){
					logger.error("Unexpected error", fe);
				}
				
				if(ctx.get(HtmlConstants.LOADINGERROR) != null && !ctx.get(HtmlConstants.LOADINGERROR).toString().equals(HtmlConstants.EMPTY))
					logger.debug("Unable to load " + resourceIdentifier + " xml for " + projectName + " due to error : " + ctx.get(HtmlConstants.LOADINGERROR).toString());
				else{
					String msg = fe.getMessage();
					if(msg == null || msg.equalsIgnoreCase("null"))
						logger.debug("Unable to load " + resourceIdentifier + " xml for " + projectName);
					else
						logger.debug("Unable to load " + resourceIdentifier + " xml for " + projectName + " due to error : " + msg);
				}
				
				ctx.remove(HtmlConstants.LOADINGERROR);
				return null;
			}catch(Exception e) {
				if(SystemProperties.getInstance().getProperty("appl.logs.required") != null && 
						SystemProperties.getInstance().getProperty("appl.logs.required").toString().equalsIgnoreCase("Y")){
					logger.error("Unexpected error", e);
				}
				
				if(ctx.get(HtmlConstants.LOADINGERROR) != null && !ctx.get(HtmlConstants.LOADINGERROR).toString().equals(HtmlConstants.EMPTY))
					logger.debug("Unable to load " + resourceIdentifier + " xml for " + projectName + " due to error : " + ctx.get(HtmlConstants.LOADINGERROR).toString());
				else{
					String msg = e.getMessage();
					if(msg == null || msg.equalsIgnoreCase("null"))
						logger.debug("Unable to load " + resourceIdentifier + " xml for " + projectName);
					else
						logger.debug("Unable to load " + resourceIdentifier + " xml for " + projectName + " due to error : " + msg);
				}
				
				ctx.remove(HtmlConstants.LOADINGERROR);
				return null;
			}
			
			String resourceObject = conf.getString(RESOURCEOBJECT);
			
			Object resourceObjectInstance = constructResourceObject(resourceObject, root);
			putResourceInCache(root, resourceIdentifier, resourceObjectInstance);
			/*
			//Test Code
			if(resourceIdentifier.equals("metadata")){
				//new XMLOutputter().outputString((Document)root);
				FileOutputStream fout = new FileOutputStream("D:\\vikas_workspace\\ProducerOne\\web\\XML\\ProducerOne\\output.xml");
				fout.write(root.toString().getBytes());
			}
			//Ended Test Code
			*/
			return root;
		}catch(Exception e)	{
			logger.error("Problem loading resource " + fileName, e);
			return null;
		}
	}
	
	private static Object constructResourceObject(String resourceObject, Object root) throws Exception
	{
		if(resourceObject==null)
			return null;
		
		Class cls = Class.forName(resourceObject);
		Class params[] = {Object.class};
		Object values[] = {root};
		Constructor ctor = cls.getDeclaredConstructor(params);
		Object resourceObjectInstance = ctor.newInstance(values);
		return resourceObjectInstance;
	}
	
	private static void putResourceInCache(IResourceKeys keys, String resourceIdentifier, Object resourceObjectInstance)
	{
		try
		{
			if(keys == null)
				return;
			
			String projectId = keys.getProject();
			if(projectId==null || resourceIdentifier==null)
				return;
			
			MultiMap map = getResourceMap();
			
			synchronized(map)
			{
				map.put(projectId, resourceIdentifier, resourceObjectInstance);
			}
			logger.debug(resourceIdentifier + " is loaded for " + projectId);			
		}catch(Throwable e)
		{
			logger.trace(e);
		}
	}

	private static MultiMap getResourceMap() throws Exception
	{
		MultiMap multiMap = (MultiMap) CacheManager.get(RESOURCES_KEY);
		if(multiMap==null)
		{
			multiMap = new MultiHashMap();
			CacheManager.put(RESOURCES_KEY, multiMap);
		}
		return multiMap;
	}
	
	private static Configuration getResourceConfiguration(String resourceIdentifier) throws Exception
	{
		Configuration conf = SystemProperties.getInstance().subset(RESOURCE_TYPE);
		return conf.subset(resourceIdentifier);
	}
	
	public static XMLParser configureXMLParser(Configuration conf) throws Exception
	{
		XMLParser parser = new XMLParser();
		
		if(conf.containsKey(TEMPLATE))
			parser.setTemplateFile(conf.getString(TEMPLATE));
		
		if(conf.containsKey(PACKAGENAME))
			parser.setPackageName(conf.getString(PACKAGENAME));
		
		if(conf.containsKey(VALIDATE))
			parser.setXmlValidate(conf.getBoolean(VALIDATE));
		
		if(conf.containsKey(SCHEMA))
			parser.setSchemaLocation(conf.getString(SCHEMA));
		
		return parser;
	}
	
	public static IResourceKeys load(String fileName, String resourceIdentifier, String projectName, boolean isCommonMerge) throws Exception
	{
		Context ctx = new Context();
		ctx.setProject(projectName);
		
		try	{
			Configuration conf = getResourceConfiguration(resourceIdentifier);
			if(conf==null)
				return null;
			
			IResourceKeys root = null;
			try	{
				fileName = fileName.replace("//", File.separator);
				fileName = fileName.replace("\\", File.separator);
				
				root = parse(fileName, resourceIdentifier, isCommonMerge, projectName);
			}catch(FileNotFoundException fe) {
				if(SystemProperties.getInstance().getProperty("appl.logs.required") != null && 
						SystemProperties.getInstance().getProperty("appl.logs.required").toString().equalsIgnoreCase("Y")){
					logger.error("Unexpected error", fe);
				}
				
				if(ctx.get(HtmlConstants.LOADINGERROR) != null && !ctx.get(HtmlConstants.LOADINGERROR).toString().equals(HtmlConstants.EMPTY))
					logger.debug("Unable to load " + resourceIdentifier + " for " + projectName + " due to error : " + ctx.get(HtmlConstants.LOADINGERROR).toString());
				else{
					String msg = fe.getMessage();
					if(msg == null || msg.equalsIgnoreCase("null"))
						logger.debug("Unable to load " + resourceIdentifier + " for " + projectName);
					else
						logger.debug("Unable to load " + resourceIdentifier + " for " + projectName + " due to error : " + msg);
				}
				
				ctx.remove(HtmlConstants.LOADINGERROR);
				return null;
			}catch(Exception e) {
				if(SystemProperties.getInstance().getProperty("appl.logs.required") != null && 
						SystemProperties.getInstance().getProperty("appl.logs.required").toString().equalsIgnoreCase("Y")){
					logger.error("Unexpected error", e);
				}
				
				if(ctx.get(HtmlConstants.LOADINGERROR) != null && !ctx.get(HtmlConstants.LOADINGERROR).toString().equals(HtmlConstants.EMPTY))
					logger.debug("Unable to load " + resourceIdentifier + " for " + projectName + " due to error : " + ctx.get(HtmlConstants.LOADINGERROR).toString());
				else{
					String msg = e.getMessage();
					if(msg == null || msg.equalsIgnoreCase("null"))
						logger.debug("Unable to load " + resourceIdentifier + " for " + projectName);
					else
						logger.debug("Unable to load " + resourceIdentifier + " for " + projectName + " due to error : " + msg);
				}
				
				ctx.remove(HtmlConstants.LOADINGERROR);
				return null;
			}
			
			String resourceObject = conf.getString(RESOURCEOBJECT);
			
			Object resourceObjectInstance = constructResourceObject(resourceObject, root);
			putResourceInCache(root, resourceIdentifier, resourceObjectInstance);
			/*
			//Test Code
			if(resourceIdentifier.equals("metadata")){
				//new XMLOutputter().outputString((Document)root);
				FileOutputStream fout = new FileOutputStream("D:\\vikas_workspace\\ProducerOne\\web\\XML\\ProducerOne\\output.xml");
				fout.write(root.toString().getBytes());
			}
			//Ended Test Code
			*/
			return root;
		}catch(Exception e)	{
			logger.error("Problem loading resource " + fileName, e);
			return null;
		}
	}
}
