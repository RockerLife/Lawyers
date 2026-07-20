package com.userbo;

import java.sql.Timestamp;
import java.util.Date;

import com.ormapping.ibatis.SqlResources;
import com.util.Context;
import com.util.InetLogger;
import com.util.ResourceLoader;
import com.util.SystemProperties;

public class GenerateReportImpl {
	private static InetLogger logger = InetLogger.getInetLogger(GenerateReportImpl.class);	
	public static void main(String[] a) {
		loadLawyersInsProjectFiles(a);
	}
	public static void loadLawyersInsProjectFiles(String[] a){
		logger.debug("Requote Batch has Started....." + new Timestamp(new Date().getTime()));
		Context ctx = new Context();

		String project = a[0];
		String propertyPathMain = a[1];
		String xmlPath = a[2];
		
		logger.debug("project " + project + " " + new Timestamp(new Date().getTime()));
		logger.debug("propertyPathMain " + propertyPathMain + " " + new Timestamp(new Date().getTime()));
		logger.debug("xmlPath " + xmlPath + " " + new Timestamp(new Date().getTime()));
		
		ctx.setProject(project);
		ctx.put("LastUpdateTimeStamp", new Timestamp(new Date().getTime()));
		try {
			SystemProperties.setPropertyFileName(propertyPathMain);
			SqlResources.load(project, xmlPath + "/LawyersIns/ibatis/maps/SqlMapConfig.xml");
			ResourceLoader.load(xmlPath + "/LawyersIns/rules/rules.xml", "rules", project);
			ResourceLoader.load(xmlPath + "/LawyersIns/functions.xml", "functions", project);
			ResourceLoader.load(xmlPath + "/LawyersIns/metadata/metadata.xml", "metadata", project);
		} catch (Exception e1) {
			logger.error("Unexpected error", e1);
			logger.debug("Error in loading XML Files " + e1.getMessage() + " " + new Timestamp(new Date().getTime()));
		}
		logger.debug("XML Files Loaded...." + new Timestamp(new Date().getTime()));
		
		generateReport(ctx);
		logger.debug("ACH Batch Ended......" + new Timestamp(new Date().getTime()));
	}
	
	public static void generateReport(Context ctx)
	{
		
		
	}
}
