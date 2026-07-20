package com.ormapping.ibatis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration.Configuration;

import com.util.CacheManager;
import com.util.IResourceKeys;
import com.util.InetLogger;
import com.util.MultiHashMap;
import com.util.MultiMap;
import com.util.ResourceKeys;
import com.util.SystemProperties;


/**
 * @author Nilesh
 *
 * TODO don't forget to add the javadoc!
 */
public class SqlResources 
{
    private static InetLogger logger = InetLogger.getInetLogger(SqlResources.class);
    private static final String RESOURCE_IDENTIFIER = "ibatis";
    private static final String RESOURCES_KEY = "resources";

    public static void load(String appName, String ibatisConfig)
    throws Exception 
    {
        Properties props = SystemProperties.getInstance().getAllProperties();
        
        if (props == null) 
        {
            return;
        }
        props.setProperty("appl.home.dir", SystemProperties.getInstance().getString("appl.home.dir"));
        props.setProperty("xml.basedir", SystemProperties.getInstance().getString("xml.basedir"));
        SqlMapProcessor sqlPrcessor = new SqlMapProcessor(ibatisConfig, props);
        getResourceMap().put(appName, RESOURCE_IDENTIFIER, sqlPrcessor);

        logger.debug(RESOURCE_IDENTIFIER + " is loaded for " + appName);
    }
    
    public static void releaseResources() throws Exception
    {
        Configuration props = SystemProperties.getInstance().subset("appl");
        if(props==null)
            return;
        
        Set appNames = new HashSet();
        Iterator keyIt = props.getKeys();
        while(keyIt.hasNext())
        {
            String appName = (String) keyIt.next();
            int separator = appName.indexOf(".");
            if(separator > 0)
                appNames.add(appName.substring(0, separator));
        }

        MultiMap multiMap = getResourceMap();
        keyIt = appNames.iterator();
        while(keyIt.hasNext())
        {
            String appName = (String) keyIt.next();
            SqlMapProcessor sqlPrcessor = (SqlMapProcessor) multiMap.get(appName, RESOURCE_IDENTIFIER);
            if(sqlPrcessor==null)
                continue;
            
            sqlPrcessor.closeSesison();
        }
    }
    
    public static SqlMapProcessor getSqlMapProcessor(IResourceKeys keys)
    throws Exception 
    {
        String project = keys.getProject();

        return (SqlMapProcessor) getResourceMap().get(project, RESOURCE_IDENTIFIER);
    }

    public static MultiMap getResourceMap() throws Exception 
    {
        MultiMap multiMap = (MultiMap) CacheManager.get(RESOURCES_KEY);

        if (multiMap == null) 
        {
            multiMap = new MultiHashMap();
            CacheManager.put(RESOURCES_KEY, multiMap);
        }

        return multiMap;
    }

    public static void main(String[] args) 
    {
        try 
        {
            String ibatisFile = SystemProperties.getInstance().getString("appl.PTS.ibatisconfig");
            load("PTS", ibatisFile);

            IResourceKeys keys = new ResourceKeys();
            keys.setProject("PTS");

            Map data = new HashMap();
            Object o = getSqlMapProcessor(keys).select("TestStoredProc.teststoredproc",
                    data);
//            System.out.println(o);
        } catch (Exception e) {
            logger.trace(e);
        }
    }
}
