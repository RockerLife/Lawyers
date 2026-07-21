package com;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.jdom.input.SAXBuilder;

import com.manage.managecomponent.processflow.processflowgenerator.HTMLParser;
import com.manage.managedatabase.dblayergeneration.ibatisgeneration.SQLMapGenerator;
import com.manage.managedatabase.gendbfrommeta.RunDBSync;
import com.manage.managedatabase.genmetafromdb.RunGenMetaFromDB;
import com.manage.util.ConvertIbatisXmls;
import com.util.InetLogger;
import com.util.ResourceLoader;
import com.util.SystemProperties;
import com.util.XmlEncryptor;
import com.xmlobjectbuilder.XMLParser;

public class BuildProcess {

	private static InetLogger logger = InetLogger.getInetLogger(BuildProcess.class);
		
	private String projectName="";
	
	public BuildProcess(String projectName){
		
		this.projectName = projectName;
	}	
	public BuildProcess(String projectName,String propertiesPath){
		
		this.projectName = projectName;
		
		try {
			SystemProperties.setPropertyFileName(propertiesPath);
		} catch (IOException ioe) {
			logger.error("Properties file is not found at location " + propertiesPath);
		}
		catch (Exception e) {
			logger.error("Properties file is not loaded from location " + propertiesPath);
		}
	}
	
	public void generateProcessFlow()
	{
		HTMLParser parser = new HTMLParser();
        try {
        	String htmlbasedir = SystemProperties.getInstance().getString("html.basedir");
        	String htmlstartpage = SystemProperties.getInstance().getString("appl." + projectName + ".htmlstartpage");
        	
        	String baseDir = SystemProperties.getInstance().getString("xml.basedir");			
			String xmldir = baseDir + projectName + "//processflow.xml";
            
			ResourceLoader.load(baseDir + projectName + "//components//components.xml", "components", projectName);
			ResourceLoader.load(baseDir + projectName + "//rules.xml", "rules", projectName);
        	parser.setHtmlPath(htmlbasedir, projectName, xmldir);
			parser.parsingHTMLPage(htmlstartpage);
			parser.populateProcessFlow();
			
			/*
			FileInputStream fin = new FileInputStream(xmldir);
			byte rb[] = new byte[fin.available()];
			fin.read(rb);
			fin.close();
			
			String xml = new String(rb);
			
			String replaceWith = null;
			
			replaceWith = "\t<pagecomponent seq=\"300\" path=\"ManageIndex.jsp\" name=\"ManageIndex\">\n\t\t<formlink action=\"show\" seq=\"0\" name=\"startapplication\">\n\t\t\t<formlinkforward eval=\"ProducerOneRule.isNotAgentRole\" component=\"homeAction\" action=\"view\" seq=\"0\" name=\"show\"/>\n\t\t\t<formlinkforward eval=\"ProducerOneRule.isAgentRole\" component=\"agentGeneralDetails\" action=\"view\" seq=\"1\" name=\"show\"/>\n\t\t</formlink>\n\t</pagecomponent>\n</processflow>";
			xml = xml.replaceAll("</processflow>", replaceWith);
			
			//System.out.println(xml);
			replaceWith = "\t<pagecomponent seq=\"301\" path=\"userRoles.jsp\" name=\"userRoles\">\n\t\t<formlink action=\"show\" seq=\"0\" name=\"startapplication\">\n\t\t\t<formlinkforward component=\"login\" action=\"view\" seq=\"0\" name=\"show\"/>\n\t\t</formlink>\n\t</pagecomponent>\n</processflow>";
			xml = xml.replaceAll("</processflow>", replaceWith);
			//System.out.println(xml);
			
			FileOutputStream fout = new FileOutputStream(xmldir);
			fout.write(xml.getBytes());
			fout.close();*/
			
        }catch (Exception e) {
        	logger.error("Process flow is not generated..."+ e.toString());
		}
	}


	public void generateDBFromMetadata(){
		try {
			
			RunDBSync runDBSync = new RunDBSync(projectName);
			List errors = runDBSync.generateDBFromMetadata();
			
		}catch(Exception e) {
			logger.error("DB synchrnization is not completed..."+ e.toString());
		}
	}
	
	public void generateMetaFromDB( ){
		try {		
			
			RunGenMetaFromDB genMeta = new RunGenMetaFromDB(projectName);
			String metaXml = genMeta.generateMetadataFromDB();
			
		}catch(Exception e)	{
			logger.error("Metadata is not generated..."+ e.toString());
		}
	}
	
	
	public void generateIbatis(int techType, boolean flag){
		try	{
			
			String baseDir = SystemProperties.getInstance().getString("xml.basedir");			
			String metadatapath = baseDir + projectName + "//metadata//metadata.xml";	
			String componentpath = baseDir + projectName + "//components//components.xml";
			String reportspath = baseDir + projectName + "//reports.xml";
			//String graphspath = baseDir + projectName + "//graphs.xml";
			
			SQLMapGenerator sqlMapGenerator = new SQLMapGenerator(projectName, metadatapath , componentpath, baseDir, flag, reportspath, null ,null);			
			sqlMapGenerator.generateSQLMaps(techType, reportspath, null ,null);
			
		}catch(Exception e)	{
			logger.error("Ibatis are not generated..."+ e.toString());
		}
	}
	
	public void generateIbatisForDotNet(){
		ConvertIbatisXmls convertor;
		String[] projects = {"LawyersIns"};
		for(int i=0;i<projects.length;i++){
			convertor = new ConvertIbatisXmls(projects[i]);
			convertor.processConverssion();
		}
	}
	
	private static void encryptXMLs(String projectName) {
		try{
			String property = "appl." + projectName + ".encrypt.xml";
			if(SystemProperties.getInstance().getProperty(property) != null &&
					SystemProperties.getInstance().getProperty(property).equals("Y")){
				
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"ProducerOne\\rules.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"ProducerOne\\security.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"ProducerOne\\processflow.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"ProducerOne\\labelconf.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"ProducerOne\\messages.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"ProducerOne\\metadata.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"ProducerOne\\functions.xml");
				
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"ProducerOne\\components\\components.xml");
				//encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"ProducerOne\\components\\producernumbers.xml");
				
				
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"common\\components.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"common\\functions.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"common\\metadata.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"common\\messages.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"common\\processflow.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"common\\rules.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"common\\security.xml");
				
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"commonResources\\functions.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"commonResources\\rules.xml");
				
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"templates\\businessrules.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"templates\\components.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"templates\\functions.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"templates\\messages.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"templates\\metadata.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"templates\\processflow.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"templates\\rules.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"templates\\security.xml");
				//encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"templates\\SqlMapConfig.xml");
				encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"templates\\versioning.xml");
				
			}
		}catch (Exception e) {
			logger.error("Unexpected error", e);
		}
	}
	
	private static void encryptXML(String xmlFile) throws Exception {
		logger.info("Going to encrypt file at : - " + xmlFile);
		String data = new XmlEncryptor().encrypt(xmlFile);
		//System.out.println(data);
		
		try (FileOutputStream fout = new FileOutputStream(xmlFile)) {
			fout.write(data.getBytes());
		}
		
	}
	
	private static void decryptXML(String xmlFile) throws Exception {
		String path = xmlFile.substring(xmlFile.lastIndexOf("XML\\")+4,xmlFile.length());
		String data = new XmlEncryptor().decrypt(xmlFile);
		
		SAXBuilder builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser", false);
		builder.build(new StringReader(data));
		
		
		try (FileOutputStream fout = new FileOutputStream(xmlFile)) {
			fout.write(data.getBytes());
		}
	}
	
	private void generateMetaObjectImpl(String customMetadataXmls, String projectName, String customFilePath, String implFilePath) throws Exception{
		new XMLParser().generateMetaObjectImpl(customMetadataXmls, projectName, customFilePath, implFilePath);
	}
	
	public static void main(String[] args)throws Exception {
		
		String projectname = "LawyersIns";
		String propertiesPath = "LawyersInsMain.properties";
		if(args.length > 0) {
			propertiesPath = args[0];
		}
		BuildProcess buildProcess = new BuildProcess(projectname, propertiesPath); 
		//////////////////////////////////////buildProcess.generateMetaFromDB();
		buildProcess.generateProcessFlow();
		
		int techType = 1;  // 1 for Java and 2 for .Net
		buildProcess.generateIbatis(techType, true);
		
		
		/*ExceptionReport report = new ExceptionReport(projectname, propertiesPath);
		report.generateExcelForHtml("coverage.html", "C:\\Users\\viveks\\Desktop\\output.xls");*/
		
		
		//report.setHtmlDirectory("carrier,admin,reports");
		//report.generateExceptionReport("C:\\Users\\vikask\\Desktop\\output.xls");
		//call();
		
		//encryptXMLs(projectname);
//		encryptXML(SystemProperties.getInstance().getString("xml.basedir")+"ProducerOne\\processflowImpl.xml");
//		decryptXML("D:\\vikas_workspace\\ProducerOne\\web\\XML\\ProducerOne\\components\\components.xml");
		//decryptXML("D:\\vikas_workspace\\ProducerOne\\web\\XML\\ProducerOne\\security.xml");
		//decryptXML("D:\\vikas_workspace\\ProducerOne\\web\\XML\\ProducerOne\\components.xml");
//		decryptXML("D:\\vikas_workspace\\ProducerOne\\web\\XML\\ProducerOne\\processflowImpl.xml");
		
		logger.debug("Generated.");
		
	}
			
	
	
}
