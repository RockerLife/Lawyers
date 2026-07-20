package com.mail;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
/**
 * This object loads the properties into memory and returns the propetry value when
 * required
 * Creation date: 12/05/2006
 * @author: Amit Jain
 */
public class PropertiesLoader
{
	static Properties systemProp;	
	
	/*
	* returns System Properties after loading 
	*/
	
	public static String INET_SYSTEM_PROPERTIES = "Lawyers.properties";
	
	public static String getSystemProperty(String key) throws Exception
	{			
		String ini_entry = systemProp.getProperty(key);
		return ini_entry;
	}
	
	/*
	* opens the properties file and loads into system properties.
	*/
	public static Properties loadProperties() throws Exception
	{
		FileInputStream lo_FileInputStream=null;

		try {
			File lo_File = new File("");
			String ls_path = lo_File.getCanonicalPath();
			systemProp = new Properties();
			//System.out.println(ls_path);
			lo_FileInputStream =
				new FileInputStream(ls_path.trim() + "//" + INET_SYSTEM_PROPERTIES);
			systemProp.load(lo_FileInputStream);
			
		} finally
		{
			if (lo_FileInputStream != null)
				lo_FileInputStream.close();
		}
		return systemProp;
	}
}