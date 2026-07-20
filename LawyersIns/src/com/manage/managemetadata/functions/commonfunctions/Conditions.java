package com.manage.managemetadata.functions.commonfunctions;

import com.util.InetLogger;

import java.util.List;

import com.util.StringUtils;

public class Conditions {
	private static final InetLogger logger = InetLogger.getInetLogger(Conditions.class);
	
	//private static InetLogger logger = InetLogger.getInetLogger(Conditions.class);
	
	public static int isAlphaNumeric (String value ) 
	{
		 if(!StringUtils.isNumeric(value))
			  return 0;
		  else
			  return -1;		 
	}
	
	public  static int contains(String bean , String value) 
	{
		
		if(bean== null)
			return -1;
		if(value ==null)
			return -1;
		
		if(bean.contains(value ))
			return 0;
		else
			return -1;
	}
	 
	 
	public  static int contains(List bean , String condition) 
	{
	   if(bean== null)
		   return -1;
	   
	   if(condition == null)
		   return -1;
	   
	     if(bean.contains(condition))
	    	 return 0;
		
		return -1;
	}
	
	 public static int isListEmpty(List list)
	    {
	    	if(list == null)
	    		return 0;
	    	
	    	if(list.isEmpty())
	    		return 0;
	    	
	    	return -1;
	    }
	 
	 public static int isStringEmpty(String bean)
	 {
    	if(bean == null)
    		return 0;
    	
    	if(bean.equals("") )
    		return 0;
    	
    	return -1;
	 }
	 
	 public static String blankString()
	 {    	
    	return "";
	 }

	public static int isAtleastOneChar(String value)
	{
		  String s = value;
		  String [] ch =  s.split("") ;
		  if(!StringUtils.isNumeric(s) )
		  { 
		  int alfa = 0;
	          for(int i=0; i< ch.length; i++){
	        	    String st =   ch[i];
	        	    boolean bool =  StringUtils.isAlpha(st);
	        	    if(bool)
	        	    	alfa++;
	        	     
	        	    	
	          }
	          if(alfa>2)
	        	  return -1;
	          else 
	        	  return 0;
	           
		  }else
			  return -1;
		  
	}
	
	public  static boolean isNot (Object param1 ) 
	{
		if(param1==null )
			return false;
		
		return ( !Boolean.parseBoolean(param1.toString()) );	 
	}
	
	public  static boolean isEqual (Object param1, Object param2 ) 
	{
		if(param1==null || param2==null)
			return false;
		else if (param1==null && param2==null)
			return true;
		
		param1 = correctParam (param1, 1);
		param2 = correctParam (param2, 1);
		
		param1 = new String(param1.toString());
		param2 = new String(param2.toString());
		
		return (param1.equals(param2));	 
	}	
	
	
	
	public  static boolean isNotEqual (Object param1, Object param2 ) 
	{
		return !isEqual (param1, param2 );
	}
	
	public  static boolean isGreaterThan (Object param1, Object param2 ) 
	{		
		if(param1==null || param2==null)
			return false;
		
		param1 = correctParam (param1, 1);
		param2 = correctParam (param2, 1);
		
		if(Double.parseDouble(param1.toString()) > Double.parseDouble(param2.toString()))
			  return true;
		  else
			  return false;		
	}
	
	public  static boolean isLessThan (Object param1, Object param2 ) 
	{
		if(param1==null || param2==null)
			return false;
		
		param1 = correctParam (param1, 1);
		param2 = correctParam (param2, 1);
		
		if(Double.parseDouble(param1.toString()) < Double.parseDouble(param2.toString()))
			  return true;
		  else
			  return false;	
	}
	
	public  static boolean isGreaterThanEqual (Object param1, Object param2 ) 
	{
		if(param1==null || param2==null)
			return false;
		
		param1 = correctParam (param1, 1);
		param2 = correctParam (param2, 1);
		
		if(Double.parseDouble(param1.toString()) >= Double.parseDouble(param2.toString()))
			  return true;
		  else
			  return false;	
	}
	
	public  static boolean isLessThanEqual (Object param1, Object param2 ) 
	{
		if(param1==null || param2==null)
			return false;
		
		param1 = correctParam (param1, 1);
		param2 = correctParam (param2, 1);
		
		if(Double.parseDouble(param1.toString()) <= Double.parseDouble(param2.toString()))
			  return true;
		  else
			  return false;
	}
	
	public  static boolean andCondition (Object param1, Object param2 ) 
	{
		if(param1==null || param2==null)
			return false;
		
		 if(Boolean.parseBoolean(param1.toString()) && Boolean.parseBoolean(param2.toString()))
			  return true;
		  else
			  return false;
	}
	
	public  static boolean orCondition (Object param1, Object param2 ) 
	{
		if(param1==null || param2==null)
			return false;
		
		 if(Boolean.parseBoolean(param1.toString()) || Boolean.parseBoolean(param2.toString()))
			  return true;
		  else
			  return false;
	}
	
	public  static boolean isNull (Object param1 ) 
	{
		 if(param1 == null)
			  return true;
		  else
			  return false;
	}
	
	public  static boolean isNotNull (Object param1 ) 
	{
		 return !isNull(param1);
	}
	
	
	public  static Object correctParam (Object param, int decimal) 
	{
	  	Object value = null;
		String val = param.toString();
		
		if(val.indexOf(".") >= 0){
			
			String beforeDecimal = val.substring(0,val.indexOf("."));
			String afterDecimal = val.substring(val.indexOf(".")+1,val.length());
			
			if(decimal > 0){
				String preciceValue = afterDecimal;
				if(afterDecimal.length() >= decimal)
					preciceValue = afterDecimal.substring(0,decimal);	
				else{
					for(int i=0; i<decimal-afterDecimal.length();i++)
						preciceValue = preciceValue +"0";
				}
				
				String finalString = beforeDecimal.concat(".").concat(preciceValue);			
				value = finalString;
			}
			else
				value = val;
			
		}else{
			String beforeDecimal = val;
			String preciceValue = "";
			
			if(decimal > 0){
				for(int i=0; i<decimal;i++)
					preciceValue = preciceValue +"0";		
				
				String finalString = beforeDecimal.concat(".").concat(preciceValue);
				value = finalString;
			}
			else
				value = beforeDecimal;			
		}
		
	  return value;
	}
	
 
	@SuppressWarnings("static-access")
	public static void main(String arg[]){	  
	 
	  
	  logger.debug(isEqual("2015.0", "2015"));
	  
  }

}

