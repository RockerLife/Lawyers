package com.userbo;

import com.util.InetLogger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.util.StringUtils;

/**
 * This class handles the date related information in Lawyers
 *
 */
public class LawyersDateUtils {
	private static final InetLogger logger = InetLogger.getInetLogger(LawyersDateUtils.class);
	
	private static String SQL_DATE_PATTERN = "yyyy-MM-dd";
	
	/**
	 * This method takes input date in any format and returns day from the input date
	 * @param objDate
	 * @return
	 */
	public static String getDayFromDate(Object objDate){
		
		if(objDate == null){
			return "";
		}
		
		Date date = (Date)objDate;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(strDate));
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String strDay = String.valueOf(day);		
		return strDay;
	}
	
	/**
	 * This method takes input date in any format and returns Month from the input date
	 * @param objDate
	 * @return
	 */
	public static String getMonthFromDate(Object objDate){
		
		if(objDate == null){
			return "";
		}
		
		Date date = (Date)objDate;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(strDate));
		int month = cal.get(Calendar.MONTH);
		
		String strMonth = String.valueOf(month);
		
		return strMonth;
	}
	
	public static long calculateDiffInDays(Date date1, Date date2)
	{
		if(date1 == null || date2 == null)
            return 0;
		
		Date date3=formatDate(date1);
		logger.debug(date3);
		Date date4=formatDate(date2);
		logger.debug(date4);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date3);
		Calendar cal1  = Calendar.getInstance();
		cal1.setTime(date4);
		
		long time1 = cal.getTimeInMillis();
		long time2 = cal1.getTimeInMillis();
		long timeInMilli = time1 - time2;
		Long numOfDays = timeInMilli/(24*60*60*1000);
		logger.debug("Number  of Days is - - - - - >" + numOfDays);
		return numOfDays;
		/*
		if(date1 == null || date2 == null)
            return 0;
		
		long lDifferenceInMS = 0;
		long lDays = 0;
		
		lDifferenceInMS = date1.getTime() - date2.getTime();
		lDays = lDifferenceInMS / (24*60*60*1000);
	
		return lDays;
		*/
	}
	public static Date formatDate(Date date){
		
		
		
		SimpleDateFormat strFormat = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = strFormat.format(date);
		Date newDate = new Date(strDate);
		
		return newDate;
	}
	
	public static void main(String a[]) {

		LawyersDateUtils obj = new LawyersDateUtils();
		Date objDate = new Date();

		obj.getDayFromDate(objDate);

	}
	
	public static Date convertStringToDate(String value, String pattern)
    {
          try
          {
                if (StringUtils.isBlank(value))
                      return null;
                
               // return new SimpleDateFormat(pattern).parse(value);
                return new SimpleDateFormat(pattern).parse(new SimpleDateFormat(pattern).format(new SimpleDateFormat("MM/dd/yyyy").parse(value)));
          }
          catch(ParseException pe)
          {
                try{
                      return new SimpleDateFormat(SQL_DATE_PATTERN).parse(value);
                }
                catch(Exception e)
                {
                    
                	//logger.trace(e);
                      return null;
                }                 
          }
          catch(Exception e)
          {
        	 
                //logger.trace(e);
                return null;
          }
    }
	public static String getCurrentDate(){		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);
		return strDate;
	}
}
