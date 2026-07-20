package com.userbo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.manage.managemetadata.functions.commonfunctions.DataUtils;
import com.processor.RequestProcessor;
import com.util.Context;
import com.util.HtmlConstants;
import com.util.IContext;
import com.util.InetLogger;
import com.util.SystemProperties;

public class LawyersBatch {

	static List <String> headers = new ArrayList <String> ();
	private static InetLogger logger = InetLogger
			.getInetLogger(LawyersBatch.class);
	
	public static void lawyerBatchXML(Context ctx)  {
		try {
			
			HttpServletResponse response = (HttpServletResponse) ctx.get("DocumentResponse");
			
			//String filePath = "D:\\workspace_product\\LawyersIns\\Excel\\download\\Rating.xls";
			String excelname="Rating.xls";
			String filePath =SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".ratingexceldownloadpath");
			filePath=filePath+excelname;
//			List inputList=new ArrayList();
			populateExcel(ctx,"filterCriteria","updateCriteria");
			
			
			/*String limit=ctx.get("LimitSequence").toString();
			limit=limit.replace(",", "");*/
			
			//String PolicyEffectiveDate = ctx.get("LBEf;fectiveDate_update")!=""?ctx.get("LBEffectiveDate_update").toString():"";
			Object modifierValue = ctx.get("Modifier");
			String Modifier = modifierValue == null ? "" : modifierValue.toString();
			
			Map map = new HashMap();
			map.put("PolicyEffectiveDateFrom", ctx.get("LBEffectiveDate_searchFrom"));
			map.put("PolicyEffectiveDateTo", ctx.get("LBEffectiveDate_searchTo"));
			
			map.put("StateCodefilter", ctx.get("StateCodefilter"));
			map.put("SubProduced", ctx.get("LBSubProduced"));
			map.put("SubProducerNumber", ctx.get("ProducerCode_search"));
			
			map.put("PolicyEffectiveDate", ctx.get("LBEffectiveDate_update"));
			map.put("Modifier", ctx.get("Modifier"));
//			inputList.add(map);
			ctx.putAll(map);
			 
			//ctx.put("filterCriteria",populateInputCriteria(ctx, "filterCriteria"));
			//ctx.put("filterCriteria",populateInputCriteria(ctx, "filterCriteria"));
			//ctx.put("updateCriteria", populateInputCriteria(ctx, "updateCriteria"));
			
			Context ctxNew = new Context();
			ctxNew.setProject(ctx.getProject());
			ctxNew.put("filterCriteria",populateInputCriteria(ctx, "filterCriteria"));
			
			/*if(PolicyEffectiveDate != "" && Modifier != "")
				ctxNew.put("updateCriteria",populateInputCriteria(ctx, "updateCriteria"));*/
			
			String requestxml = new RequestProcessor().generateRequestXml(ctxNew);
			ctxNew.put("inputXml", requestxml);
			List list = fetchBatchRatingData(ctxNew);
			logger.debug(list.size());
			
			generateDataSheet(ctx, "Data", list);
			
			FormatExcel(filePath);
			
			
//			exportFileDownLoad(ctx,filePath,response);
			
			
       	 /*DataUtils.populateError(ctx, "successmessage", "File Downloaded Successfully");*/
			
			/*String request=new RequestProcessor().generateRequestXml(ctx);
			System.out.println("request>>>>>>>"+request);*/
	
		}catch(Exception e) {
			logger.error("Unexpected error", e);
			
		}
	}
	
	public static void populateExcel(IContext ctx,String sheetname,String sheetname1){
		
		String excelname="Rating.xls";
		String filePath="";
		try {
			filePath = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".ratingexceldownloadpath");
			filePath=filePath+excelname;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			logger.error("Unexpected error", e1);
		}
		
		
		/*String limit=ctx.get("LimitSequence").toString();
		limit=limit.replace(",", "");*/
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheetupd = workbook.createSheet(sheetname1);
		
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);
	   
		
		Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
		data.put("1", new Object[]{"Modifier"});
		data.put("2", new Object[]{ctx.get("Modifier")});

	Set<String> keyset = data.keySet();
    int rownum = 0;
    for (String key : keyset) {
    	if(rownum==0){
        Row row = sheetupd.createRow(rownum++);
        
        Object[] objArr = data.get(key);
        int cellnum = 0;
        for (Object obj : objArr) {
            Cell cell = row.createCell(cellnum++);
            if (obj instanceof Date) {
                cell.setCellValue((Date) obj);
                cell.setCellStyle(style);
            } else if (obj instanceof Boolean) {
                cell.setCellValue((Boolean) obj);
                cell.setCellStyle(style);
            } else if (obj instanceof String) {
                cell.setCellValue((String) obj);
                cell.setCellStyle(style);
            } else if (obj instanceof Double) {
                cell.setCellValue((Double) obj);
                cell.setCellStyle(style);
            }
        }
        
    	}
    	else{
    		 Row row = sheetupd.createRow(rownum++);
    		Object[] objArr = data.get(key);
	        int cellnum = 0;
	        for (Object obj : objArr) {
	            Cell cell = row.createCell(cellnum++);
	            if (obj instanceof Date) {
	                cell.setCellValue((Date) obj);
	            } else if (obj instanceof Boolean) {
	                cell.setCellValue((Boolean) obj);
	            } else if (obj instanceof String) {
	                cell.setCellValue((String) obj);
	            } else if (obj instanceof Double) {
	                cell.setCellValue((Double) obj);
	            }
	        }
	     
    	}
    }
	    HSSFSheet sheet = workbook.createSheet(sheetname);
	    
		  
	    Map<String, Object[]> data1 = new LinkedHashMap<String, Object[]>();
			data1.put("1", new Object[]{"PolicyEffectiveDateFrom", "PolicyEffectiveDateTo", "PolicyType","StateCode","SubProduced","SubProducerNumber","Limit","ModifierUpdate"});
			data1.put("2", new Object[]{ctx.get("LBEffectiveDate_searchFrom"),ctx.get("LBEffectiveDate_searchTo"),ctx.get("LBpolicy_type"),ctx.get("StateCodefilter"),ctx.get("LBSubProduced"),ctx.get("ProducerCode_search"),null,ctx.get("Modifier")});

		Set<String> keyset1 = data1.keySet();
	    int rownum1 = 0;
	    for (String key : keyset1) {
	    	if(rownum1==0){
	        Row row1 = sheet.createRow(rownum1++);
	        
	        Object[] objArr1 = data1.get(key);
	        int cellnumm = 0;
	        for (Object obj : objArr1) {
	            Cell cell1 = row1.createCell(cellnumm++);
	            if (obj instanceof Date) {
	                cell1.setCellValue((Date) obj);
	                cell1.setCellStyle(style);
	            } else if (obj instanceof Boolean) {
	                cell1.setCellValue((Boolean) obj);
	                cell1.setCellStyle(style);
	            } else if (obj instanceof String) {
	                cell1.setCellValue((String) obj);
	                cell1.setCellStyle(style);
	            } else if (obj instanceof Double) {
	                cell1.setCellValue((Double) obj);
	                cell1.setCellStyle(style);
	            }
	        }
	        
	    	}
	    	else{
	    		 Row row1 = sheet.createRow(rownum1++);
	    		Object[] objArr1 = data1.get(key);
		        int cellnumm = 0;
		        for (Object obj : objArr1) {
		            Cell cell1 = row1.createCell(cellnumm++);
		            if (obj instanceof Date) {
		                cell1.setCellValue((Date) obj);
		            } else if (obj instanceof Boolean) {
		                cell1.setCellValue((Boolean) obj);
		            } else if (obj instanceof String) {
		                cell1.setCellValue((String) obj);
		            } else if (obj instanceof Double) {
		                cell1.setCellValue((Double) obj);
		            }
		        }
		     
	    	}
	    }
	    sheet.autoSizeColumn(7);
	    try (FileOutputStream out = new FileOutputStream(new File(filePath))) {
	        workbook.write(out);
	        logger.debug("Excel written successfully..");
	    } catch (FileNotFoundException e) {
	        logger.error("Unexpected error", e);
	    } catch (IOException e) {
	        logger.error("Unexpected error", e);
	    }
	
	}
	
	public static Map <String, String> populateInputCriteria(IContext ctx, String sheetName) throws Exception {
		//String path = "D:\\workspace_product\\LawyersIns\\Excel\\download\\Rating.xls";	
		String excelname="Rating.xls";
		String path =SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".ratingexceldownloadpath");
		path=path+excelname;
		String path1 = (String) ctx.get(path);
		Workbook workbook = null;
		int rows; // No of rows
		String value = "";	
		Map <String, String>valuesMap = null;
		Row row;
		//XSSFRow row;
	    Cell cell = null;
	    Sheet sheet;
	    
	   try (FileInputStream input = new FileInputStream(path)) {
	          workbook = new HSSFWorkbook(input);
	          sheet = workbook.getSheet(sheetName);
			  rows = sheet.getPhysicalNumberOfRows();
			  row = sheet.getRow(0);
			  headers.clear();
	          if(row != null) {
	              for(int c=0; c < row.getLastCellNum(); c++){
	            	  headers.add(row.getCell(c).getStringCellValue());
	              }
	          }
	          
	          for(int r = 1; r < rows; r++) {      	    	
	  	        row = sheet.getRow(r);
	  	      if(row == null){
	              row = sheet.createRow(r);
	          }

	  	        valuesMap = new HashMap <String, String>();      	        
	  	        if(row != null) {
	  	        	for(int c=0; c < row.getLastCellNum(); c++){
	  	        		cell = row.getCell(c);
	  	        		if(cell == null){
	  	                  cell = row.createCell(c);
	  	              }

	  	        		if(cell != null){
	  	        			if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
	  	        				if(HSSFDateUtil.isCellDateFormatted(cell)) {
	  	        					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	  	        					String  date =  sdf.format(cell.getDateCellValue());
	  	        					valuesMap.put(headers.get(c), date); 
	  	        				} else {
			      	        		cell.setCellType(Cell.CELL_TYPE_STRING);
			      	        		valuesMap.put(headers.get(c), cell.getStringCellValue()); 
	      	        			}
	  	        				
	  	        			} else {
		      	        		cell.setCellType(Cell.CELL_TYPE_STRING);
		      	        		valuesMap.put(headers.get(c), cell.getStringCellValue()); 
	  	        			}
	  	        			
	  	        		}
	  	        	}     	        	
	  	        }      	        
	  	        break;
	  	    } 
		} catch (FileNotFoundException e) {
			logger.debug("Problem in reading file " + path + " " + e.getMessage());
		}
		catch (IOException e) {
			logger.debug("Problem in reading file " + path + " " + e.getMessage());
		}
	   return valuesMap;
	}
	
	public static void generateDataSheet(IContext ctx, String sheetName, List list) throws Exception {
		//String path = "D:\\workspace_product\\LawyersIns\\Excel\\download\\Rating.xls";	
		String excelname="Rating.xls";
		String path =SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".ratingexceldownloadpath");
		path=path+excelname;
		String path1 = (String) ctx.get(path);
		Workbook workbook = null;
		int rows; // No of rows
		String value = "";	
		Map <String, String>valuesMap = null;
		List headerList = new ArrayList();
		Row row;
		//XSSFRow row;
	    Cell cell = null;
	    Sheet sheet;
	    String key = "";
	    String val = "";
	    
	   try {
		   	  try (FileInputStream input = new FileInputStream(path)) {
	          workbook = new HSSFWorkbook(input);
	          sheet = workbook.createSheet(sheetName);
			  headers.clear();
			  if(list == null)
				  return;
			  
			  CellStyle style = workbook.createCellStyle();
			  Font font = workbook.createFont();
			  font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			  style.setFont(font);
			  
			    
			  if(list.size() >= 1){
				  Row headerRow = sheet.createRow(0);	
				  for(int j=0; j<RateBatchConstants.headers.length; j++){
					  cell = headerRow.createCell(j);
			          cell.setCellValue(RateBatchConstants.headers[j]);
			          cell.setCellStyle(style);
			          sheet.autoSizeColumn(j);    
				  }
			  }
				  
			  	int rowNum = 1;
		        for(int j=0; j<list.size(); j++) {
		            row = sheet.createRow(rowNum++);		            
		            Map valueMap = (Map) list.get(j);
		            for(int k=0; k<RateBatchConstants.headers.length; k++){
		            	key = RateBatchConstants.headers[k];
		            	if(valueMap.get(key) != null)
		            		val = valueMap.get(key).toString();
		            	else
		            		val = "";
		            	row.createCell(k).setCellValue(val);
		            }
		            sheet.autoSizeColumn(j);
		        }
		        
			  }
		        File file = new File(path);
		        if(file.exists())
		        	file.delete();
		        
		        try (FileOutputStream fileOut = new FileOutputStream(path)) {
		        	workbook.write(fileOut);
		        }
		       
		} catch (Exception e) {
			logger.debug("Problem in writing in file " + path + " " + e.getMessage());
		}
		
	   
	}

	public static List fetchBatchRatingData(IContext ctx)throws Exception {
		List list=null;
		logger.debug("Going to execute the stored procedure BatchRatingForLawyers_Proc");
	//	new SetParametersForStoredProcedures().setParametersInContext(ctx, "inputXml");
		 //list = (List)SqlResources.getSqlMapProcessor(ctx).select("Policy.BatchRatingForLawyers_Proc", ctx);
		String webServiceURI = SystemProperties.getInstance().getString("appl.LawyersIns.ReRate.url");
	//	new SetParametersForStoredProcedures().setParametersInContext(ctx, "inputXml");
		// list = (List)SqlResources.getSqlMapProcessor(ctx).select("Policy.BatchRatingForLawyers_Proc", ctx);
	//	String webServiceURI = SystemProperties.getInstance().getString("appl.LawyersIns.ReRate.url");
				//"http://192.168.1.101:8080/ReRatingService/rest/rerating";
		JSONObject requestJson = new JSONObject();
		// Merchant ID
		requestJson.put("inputXml",ctx.get("inputXml"));
		//requestJson.put("inputXml", "<request><requestdata><inet_project_id>LawyersIns</inet_project_id><filterCriteria><data><PolicyEffectiveDateFrom>06/01/2018</PolicyEffectiveDateFrom><SubProducerNumber></SubProducerNumber><SubProduced>N</SubProduced><Limit>100000/300000</Limit><StateCode>AL</StateCode><PolicyType>NB</PolicyType><PolicyEffectiveDateTo>06/18/2018</PolicyEffectiveDateTo></data></filterCriteria></requestdata></request>");
		String data = LawyersUtilities.authorizeTransaction(requestJson, webServiceURI);
		
		 JSONObject jsonObject =null;
		try {
			
		      jsonObject = new JSONObject(data);
		     logger.debug(jsonObject);
		}catch (JSONException err){
		     logger.error("Unexpected error", err);
		}
		list=LawyersUtilities.jsonArrayStringToList(jsonObject.get("responseMainObject").toString());
		logger.debug("BatchRatingForLawyers_Proc has been executed");
		return list;
	}

	
	
	public static void lawyerBatchExel(IContext ctx){
		try{
			logger.debug(ctx);
			
			
		}catch(Exception e){
			logger.error("Unexpected error", e);
		}
		
	}

	public static void exportFileDownLoad(Context ctx,String filePath, HttpServletResponse response){
		
		File downloadFile = null;
		ServletOutputStream outStream = null;
		FileInputStream inStream = null;
		
		try{
	    	
	        if(filePath != null && !filePath.isEmpty() && !"null".equals(filePath)){

	        	//System.out.println(filePath);
	        	downloadFile = new File(filePath);
	        	
	        	if(downloadFile.exists()){
	        		logger.debug(downloadFile.getAbsolutePath().substring(downloadFile.getAbsolutePath().lastIndexOf("\\")+1));
	        		/*inStream = new FileInputStream(downloadFile);
		        	    
		        	    // gets MIME type of the file
	        		String mimeType = "application/octet-stream";
		        	    //System.out.println("MIME type: " + mimeType);

	        		    // modifies response
	        		response.setContentType(mimeType);
	        		response.setContentLength((int) downloadFile.length());
	        		
		        	    // forces download
	        		String headerKey = "Content-Disposition";
	        		String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());//attachment
	        		response.setHeader(headerKey, headerValue);
		        	    //System.out.println(headerKey+"   fgfgfgf   "+ headerValue);
		        	    
	        		outStream = response.getOutputStream();
		            
	        		response.flushBuffer();
	        		
	        		byte[] buffer = new byte[(int) downloadFile.length()];
	        		int bytesRead = -1;
		                 
	        		while ((bytesRead = inStream.read(buffer)) != -1) {
	        			outStream.write(buffer, 0, bytesRead);
	        		}
		                 
	        		if(inStream != null)
			    		inStream.close();
	        		if(outStream != null)
			    		outStream.close();
	        		*/       		
	        		
	        		
	        		
	        		String filename = downloadFile.toString();
	               /* response.setContentType("application/octet-stream");
	                response.setHeader("Content-Disposition",
	                        "attachment;filename="+filename);*/
	        		HttpServletRequest req=(HttpServletRequest)ctx.get("DocumentRequest");	        		
	        		ServletContext servletContext=(ServletContext)ctx.get("DocumentServletContext");
	        		
	        		String contentType = servletContext.getMimeType(
	        				(String) ctx.get("DocFileName"));
	        		response.setContentType(contentType);
	        		response.setHeader("Content-disposition", "attachment; filename="
	        				+ filename);

	                File file = new File(filename);
	                outStream = response.getOutputStream();

	                byte[] outputByte = new byte[(int)file.length()];
	                //copy binary contect to output stream
	                /*while(fileIn.read(outputByte, 0, (int)file.length()) != -1)
	                {
	                	outStream.write(outputByte, 0, (int)file.length());
	                }*/
	                
	                try (InputStream fileIn = new FileInputStream(file)) {
	                	fileIn.read(outputByte);
	                }
	                
	                ByteArrayOutputStream bout = new ByteArrayOutputStream();
	        		bout.write(outputByte);
	        		bout.writeTo(outStream);

//	        		outStream.flush();
	        		bout.close();
	        		outStream.close();
	                
	        		DataUtils.populateError(ctx, "successmessage", "File Downloaded Successfully");
	        		
	        		//response.flushBuffer();
	        		ctx.put("showCallRating","Y");
	        		//System.out.println("sssssss");
	        		return;
	        	}
	        	
	        }
	        ctx.put("showCallRating","N");
	        return;
		} catch(Exception ee) {
			logger.error("Unexpected error", ee);
			return;
	    } finally {
	    	/*code by sukhi 26/09/2018*/
	    	try {
		    	if(inStream != null){
		    		inStream.close();
		    		inStream = null;
		    	}
		    	if(outStream != null){
		    		outStream.close();
		    		outStream = null;
		    	}
		    	downloadFile = null;
	    	}catch(Exception e){
	    		logger.error("Unexpected error", e);
	    	}
	    }
	}

	public void RatingProcessExcel(IContext ctx){
		try{
		processExcel("Data",ctx);
		DataUtils.populateError((Context) ctx, "successmessage", "Call Rating Successfully");
		}catch(Exception e){
			logger.error("Unexpected error", e);
		}
	}

	public void processExcel(String sheetName,IContext ctx) throws Exception {
		headers.clear();
		//String path = "C:\\Users\\raghurajs\\Desktop\\RatingOut.xls";
		//String path = "D:\\workspace_product\\LawyersIns\\Excel\\upload\\Rating.xls";
		String excelname="Rating.xls";
		String path =SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".ratingexceldownloadpath");
		path=path+excelname;
		Workbook workbook = null;
		int rows; // No of rows
		String value = "";	
		Map <String, String>valuesMap = null;
		Row row;
		//XSSFRow row;
	    Cell cell = null;
	    Sheet sheet;
	    Map <String, Integer> headersIndexUpdated = new HashMap <String, Integer> ();
	   try {
		   try (FileInputStream input = new FileInputStream(path)) {
		   	workbook = new HSSFWorkbook(input);
		   	sheet = workbook.getSheet(sheetName); 
			rows = sheet.getPhysicalNumberOfRows();
			row = sheet.getRow(0);
	          if(row != null) {
	              for(int c=0; c < row.getLastCellNum(); c++){
	            	  headers.add(row.getCell(c).getStringCellValue());
	              }
	          }
	          
	          for(int c=0; c < RateBatchConstants.headersUpdated.length; c++){
	        	  String header = RateBatchConstants.headersUpdated[c];
	        	  if(headers.contains(header)){
	        		  for(int k=(headers.size())-1; k >=0 ; k--){
	        			if(header.equals(headers.get(k))){
	        				headersIndexUpdated.put(header, new Integer(k));
	        				break;
	        			}
	        		  }
	        	  }
	        	  
	          }
	          
	          logger.debug(headersIndexUpdated);
	          
	          for(int r = 1; r < rows; r++) {      	    	
	  	        row = sheet.getRow(r);
	  	        if(row == null){
	  	        	row = sheet.createRow(r);
	  	        }

	  	        valuesMap = new HashMap <String, String>();      	        
	  	        if(row != null) {
	  	        	for(int c=0; c < row.getLastCellNum(); c++){
	  	        		cell = row.getCell(c);
	  	        		if(cell == null){
	  	                  cell = row.createCell(c);
	  	        		}

	  	        		if(cell != null){
	  	        			if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
	  	        				if(HSSFDateUtil.isCellDateFormatted(cell)) {
	  	        					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	  	        					String  date =  sdf.format(cell.getDateCellValue());
	  	        					valuesMap.put(headers.get(c), date); 
	  	        				} else {
			      	        		cell.setCellType(Cell.CELL_TYPE_STRING);
			      	        		valuesMap.put(headers.get(c), cell.getStringCellValue()); 
	      	        			}
	  	        				
	  	        			} else {
		      	        		cell.setCellType(Cell.CELL_TYPE_STRING);
		      	        		valuesMap.put(headers.get(c), cell.getStringCellValue()); 
	  	        			}
	  	        			
	  	        		}
	  	        	}
	  	        	
	  	        	proceeExcelRow(valuesMap);        	        	
	  	        	
	  	        	Iterator <String> itr= headersIndexUpdated.keySet().iterator();
	  	        	String key = "";
	  	        	while(itr.hasNext()){
	  	        		key = itr.next();  
	  	        		int index = headersIndexUpdated.get(key);
	  	        		cell = row.getCell(index);
	  	        		if(cell == null){
	  	                  cell = row.createCell(index);
	  	        		}

	  	        		if(cell != null){
	  	        			cell.setCellType(Cell.CELL_TYPE_STRING);
	  	        			if((Object)valuesMap.get(key) instanceof Long)
	  	        				cell.setCellValue(String.valueOf(valuesMap.get(key)));
	  	        			else
	  	        				cell.setCellValue(valuesMap.get(key));	      	        		
	  	        		}
	  	        	}
	  	        	
	  	        } 
	  	        
	          } 
	          
	          for(int i = 0; i < RateBatchConstants.headers.length; i++) {
		            sheet.autoSizeColumn(i);
	          }
	          
		   }
	          File file = new File(path);
	          if(file.exists())
	        	  file.delete();
		        
	          try (FileOutputStream outFile = new FileOutputStream(path)) {
	          	workbook.write(outFile);
	          }
	         
	          
		} catch (Exception e) {
			logger.error("Unexpected error", e);
	    }
		
	}
	public void proceeExcelRow(Map <String, String>valuesMap) throws Exception {
		RatingBatch.callingRatingEngineForRerate(valuesMap);
		//System.out.println(valuesMap.get("Premium").toString());
		logger.debug("Row has processed");
	}

	public static void ValidateDownloadExcel(Context ctx){
		try{
			String successdate="true";
			String successlimit="true";
		if((ctx.get("LBEffectiveDate_searchFrom") != null && !HtmlConstants.EMPTY.equals(ctx.get("LBEffectiveDate_searchFrom").toString()))
	    		&& (ctx.get("LBEffectiveDate_searchTo") != null && !HtmlConstants.EMPTY.equals(ctx.get("LBEffectiveDate_searchTo").toString()))){
			ctx.put("isDataFound", "Y");
			
			String issued_date=ctx.get("LBEffectiveDate_searchFrom").toString();
			String iisued_dateTo=ctx.get("LBEffectiveDate_searchTo").toString();
			
			 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	         Date date1 = sdf.parse(issued_date);
	         Date date2 = sdf.parse(iisued_dateTo);
				
	         if(date2.before(date1)){
	        	 LawyersUtils.populateError(ctx, "LBEffectiveDate_searchTo", "Effective Date To should be greater than Effective Date From");
	        	 successdate="false";
	         }
							
			
		}
		else{
			if (ctx.get("LBEffectiveDate_searchFrom") == null || HtmlConstants.EMPTY.equals(ctx.get("LBEffectiveDate_searchFrom").toString())) {
				LawyersUtils.populateError(ctx, "LBEffectiveDate_searchFrom", "Effective Date From is required field");
				successdate="false";
			}
			if (ctx.get("LBEffectiveDate_searchTo") == null || HtmlConstants.EMPTY.equals(ctx.get("LBEffectiveDate_searchTo").toString())) {
	    	LawyersUtils.populateError(ctx, "LBEffectiveDate_searchTo","Effective Date To is required field");
	    	successdate="false";
	    } 
		}
		
		/*if(ctx.get("LimitSequence") == "" || ctx.get("LimitSequence") == null){
			LawyersUtils.populateError(ctx, "LimitSequence","Limit is required field");
			successlimit="false";
		}*/
		
		if(successdate.equals("true") && successlimit.equals("true")){
			DataUtils.populateError(ctx,"validatesuccessmessage","Fields Validated Successfully");
			ctx.put("showCallRating","Y");
		}
		
		}catch(Exception ex){
			logger.error("Unexpected error", ex);
		}
		return;
	}

	public static void uploadExcel(IContext ctx) throws Exception {
        logger.debug("processDocument has started");
        if(ctx.get("fileItems") == null || !(ctx.get("fileItems") instanceof List))
              return;
        
        String fileName = null;
        String DocFileName = null;
        FileItem uploadFile = null;

        
        try {
              List fileItems = (List)ctx.get("fileItems");
              Iterator i = fileItems.iterator();

              while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                          // filename on the client
                          fileName = fi.getName();
                          uploadFile = fi;
                          if (fileName == null) {
                                continue;
                          }
                    }
              }
              
        } catch (Exception e) {
              logger.debug("Error in file process");
              logger.error("Unexpected error", e);
              throw e;
        }

        if (fileName == null || "".equals(fileName))
              LawyersUtils.populateError(ctx, "DocFileName", "Document has not been selected");
        

        
        int fileIndex = fileName.indexOf(".");
        String extension = fileName.substring(fileIndex);
        logger.debug(extension);
        File file = null;
        File upfile = null;
        
        try{
              String org_fileName = fileName;
              //fileName = fileName + extension;
              logger.debug(fileName);

//              DocFileName = fileName.substring(fileName.lastIndexOf("\\") + 1,fileName.length());
              DocFileName="Rating.xls";
              
              file = new File(fileName);
              String docName = file.getName();
              String docNameTemp = file.getName();
              
              LawyersUtils.populateLastUpdateTimeStamp(ctx);

              String uploaddirectory = SystemProperties.getInstance().getString("appl." + ctx.getProject() + ".ratingexceluploadpath");
              upfile = new File(uploaddirectory, DocFileName);
              if(upfile.exists()){
            	  upfile.delete();
              }
              try {
                    uploadFile.write(new File(uploaddirectory, DocFileName));
              } catch (Exception e) {
                    logger.debug("Exception " + e );
                    LawyersUtils.populateError(ctx, "DocUploadError",
                                "Document could not be write to file" );
                    

                    return;
              }

              String result = null;
              
              DataUtils.populateError((Context) ctx, "successmessage", "File Uploaded Successfully");
              
              
              }catch(Exception e){
                    logger.debug("Exception in DocFileName" + e);
                    logger.error("Unexpected error", e);
              } finally {
            	  /*code by sukhi 26/09/2018*/
                  upfile=null;
                  file=null;
              }
  }
	
	public static void FormatExcel(String path){
		String excelFilePath = path;
		FileInputStream inputStream = null;
		FileOutputStream out = null;
        try {
            inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            int sheets=workbook.getNumberOfSheets();
            for(int i=0;i<sheets;i++){
            Sheet sheet = workbook.getSheetAt(i);
            int numberOfCells =0;
            int rowCount = sheet.getLastRowNum();
            Iterator rowIterator = sheet.rowIterator();
            /**
             * Escape the header row *
             */
            if (rowIterator.hasNext())
            {
                Row headerRow = (Row) rowIterator.next();
                Iterator<Cell> cellIterator = headerRow.cellIterator();

                while (cellIterator.hasNext()) {
               Cell cell = cellIterator.next();
               int cellnum=cell.getColumnIndex();
               logger.debug(cell.getStringCellValue());
               sheet.autoSizeColumn(cellnum);
                }
                
                //get the number of cells in the header row
                	
                }
            out = new FileOutputStream(path);
            workbook.write(out);
            
            
            /*code by sukhi 26/09/2018*/
            out.close();
            inputStream.close();
            }
	}catch(Exception e){
		logger.debug(e.toString());
		logger.error("Unexpected error", e);
	} finally {
		/*code by sukhi 26/09/2018*/
		try {
			if(out != null){
				out.close();
				out = null;
			}
			if(inputStream != null){
				inputStream.close();
				inputStream = null;
			}
		}catch(Exception e){
			logger.error("Unexpected error", e);
		}
	}

	}
	
}
