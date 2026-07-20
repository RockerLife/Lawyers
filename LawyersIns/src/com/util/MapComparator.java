package com.util;

import java.util.Comparator;
import java.util.Date;
import java.util.Map;

/**
 * @author NILESH
 *
 * TODO don't forget to add the javadoc!
 */
public class MapComparator implements Comparator
{
	private String key;
	private int dataType;
	private String sortOrder;
	
	private static InetLogger logger = InetLogger.getInetLogger(MapComparator.class);
	
	public MapComparator(String key, int dataType, String sortOrder)
	{
		this.key = key;
		this.dataType = dataType;
		this.sortOrder = sortOrder; 
	}
	
	public MapComparator(String key, int dataType)
	{
		this(key, dataType, Constants.ASC_SORT_ORDER);
	}
	
	public int compare(Object arg1, Object arg2)
	{
		Map map1 = (Map) arg1;
		Map map2 = (Map) arg2;
		
		
		Object val1 = map1.get(key);
		Object val2 = map2.get(key);
		
		if(val1==null)
			if(val2!=null)
				return -1;
			
		if(val1!=null)
			if(val2==null)
				return 1;
			
		if(val1==null && val2==null)
			return 0;
			
		switch(dataType)
    	{
    		case Constants.CHAR_DATA_TYPE :
    			String str1 = val1.toString();
    			String str2 = val2.toString();
    			if(Constants.ASC_SORT_ORDER.equals(sortOrder))
    				return String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
    				//str1.casecompareTo(str2);
    			else
    				//return str2.compareTo(str1);
    				return String.CASE_INSENSITIVE_ORDER.compare(str2, str1);
    			
    		case Constants.NUMERIC_DATA_TYPE :
    			Long l1 = getLongVal(val1);
    			Long l2 = getLongVal(val2);
    			if(Constants.ASC_SORT_ORDER.equals(sortOrder))
    				return (l1.compareTo(l2));
    			else
    				return (l2.compareTo(l1));
    			
    		case Constants.DATE_DATA_TYPE :
    			
    			if (!(val1 instanceof Date)) {
    				if (val2 instanceof Date)
    					return Constants.ASC_SORT_ORDER.equals(sortOrder) ? -1 : 1;
					
				}
    			
    			if (val1 instanceof Date) {
    				if (!(val2 instanceof Date))
    					return Constants.ASC_SORT_ORDER.equals(sortOrder) ? 1 : -1;					
				}
    			
    			if (val1 instanceof Date && val2 instanceof Date)
    			{
    				Date dt1 = (Date) val1;
    				Date dt2 = (Date) val2;
    				
    				if(Constants.ASC_SORT_ORDER.equals(sortOrder))
    					return (dt1.compareTo(dt2));
    				else
    					return (dt2.compareTo(dt1));
    			}
    	}
		logger.error("Invalid dataType: " + dataType);
		return 0;
	}
	
    private Long getLongVal(Object val)
    {
    	try
    	{
    		return new Long(val.toString());
    	}catch(Exception e)
    	{
    		return new Long(Long.MIN_VALUE);
    	}
    }
}
