package com.excel.oldpim;


import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.excel.downloadexcel.RowColumnBean;
import com.util.Context;

public class ExcelDataBaseMapping {
	
	public Map cellInfo = null;	  
	
	@SuppressWarnings("unchecked")
	public Map getCellInfo(Context ctx,Sheet mainSheet) throws Exception 
	{		
		if(ctx.get("EXCEL_FIELD_MAPPING") == null)
			cellInfo = new HashMap();
		else
			cellInfo = (Map) ctx.get("EXCEL_FIELD_MAPPING");
		
		  String setValue = null ;        
		  int rownum = 0;
		
		  for(int i=rownum; i<=mainSheet.getLastRowNum(); i++)
		  {
            Row row = mainSheet.getRow(rownum++);
            if(row==null)
            	continue;
            
            int cellnum = 0;
            for(int j = cellnum; j<61; j++)
            {            	
                Cell cell = row.getCell(cellnum++);
                
                if((cell== null || cell.getCellType()==Cell.CELL_TYPE_BLANK) ){
                	continue;
                }
                if(Cell.CELL_TYPE_STRING==cell.getCellType()){
                	
               // String  setValue = PricingExcelUtil.getCellValue(mainSheet, rownum, cellnum);
        		  //String setValue = cell.getStringCellValue();
        		  if(null!=cell)
          			switch (cell.getCellType ())
  	        		{   
  	        			case Cell.CELL_TYPE_NUMERIC :
  	        			{
  	        				
  	        				// cell type numeric.
  	        				  double setvalue = cell.getNumericCellValue();
  	        				  setValue = Double.toString(setvalue);
  	        				//CellStyle clone =  wb.createCellStyle();
  	        				//clone.cloneStyleFrom(cell1.getCellStyle());
  	        				
  	        				//cell2.setCellStyle(clone);
  	        				//System.out.println("inside numeric"+cell2.getNumericCellValue());
  	        				break;
  	        			}
  	        			
  	        			case Cell.CELL_TYPE_STRING :
  	        			{
  	        				
  	        				// cell type string.
  	        				setValue = cell.getStringCellValue();
  	        				//CellStyle clone = wb.createCellStyle();
  	        				//clone.cloneStyleFrom(cell1.getCellStyle());
  	        				
  	        				//cell2.setCellStyle(clone);
  	        				//cell2.setCellStyle(cell1.getCellStyle());
  	        		         // System.out.println("inside string"+cell2.getStringCellValue());
  	        				break;
  	        			}
  	        			
  	        			
  	        		} 
        		if(null!=setValue && setValue.startsWith (":"))
        		{                
	                String Value = setValue.replace(":", "");
//	                if(Constants.ClientNameOsi.equals(Value))
//	                	System.out.println("get");
//	                if(Constants.ClientNamePpo.equals(Value))
//	                	System.out.println("get");
	                
	                if(!cellInfo.containsKey(Value))
	                {           
		                cellInfo.put(Value, new RowColumnBean(i,j, cell));		               
	                }                
        		}
        		
              }
            }
          
            
       }
      
		  return cellInfo;   
       
	}
	
	
	@SuppressWarnings("unchecked")
	public Map getListCellInfo(Context ctx,Sheet mainSheet) throws Exception 
	{
		
		  Map cellInfo =null;
		  cellInfo =new HashMap();
        String setValue = null ;
        
		int rownum = 0;
		
       for(int i=rownum; i<270; i++){
            Row row = mainSheet.getRow(rownum++);
            if(row==null)
            	continue;
            int cellnum = 0;
            for(int j = cellnum; j<45; j++){
            	
                Cell cell = row.getCell(cellnum++);
                
                if((cell== null || cell.getCellType()==Cell.CELL_TYPE_BLANK) ){
                	continue;
                }
                if(Cell.CELL_TYPE_STRING==cell.getCellType()){
                	
               // String  setValue = PricingExcelUtil.getCellValue(mainSheet, rownum, cellnum);
        		  //String setValue = cell.getStringCellValue();
        		  if(null!=cell)
          			switch (cell.getCellType ())
  	        		{   
  	        			case Cell.CELL_TYPE_NUMERIC :
  	        			{
  	        				
  	        				// cell type numeric.
  	        				  double setvalue = cell.getNumericCellValue();
  	        				  setValue = Double.toString(setvalue);
  	        				//CellStyle clone =  wb.createCellStyle();
  	        				//clone.cloneStyleFrom(cell1.getCellStyle());
  	        				
  	        				//cell2.setCellStyle(clone);
  	        				//System.out.println("inside numeric"+cell2.getNumericCellValue());
  	        				break;
  	        			}
  	        			
  	        			case Cell.CELL_TYPE_STRING :
  	        			{
  	        				
  	        				// cell type string.
  	        				setValue = cell.getStringCellValue();
  	        				//CellStyle clone = wb.createCellStyle();
  	        				//clone.cloneStyleFrom(cell1.getCellStyle());
  	        				
  	        				//cell2.setCellStyle(clone);
  	        				//cell2.setCellStyle(cell1.getCellStyle());
  	        		         // System.out.println("inside string"+cell2.getStringCellValue());
  	        				break;
  	        			}
  	        			
  	        			
  	        		} 
        		if(null!=setValue && setValue.endsWith (":")&&(setValue.equalsIgnoreCase("SAFETY")||setValue.equalsIgnoreCase("MISC")||setValue.equalsIgnoreCase("For Contracting Risks")||setValue.equalsIgnoreCase("For Ag-Related Risks"))){
                
                String Value = setValue.replace(":", "");
                if(!cellInfo.containsKey(Value))  {           
                cellInfo.put(Value, new RowColumnBean(i,j, cell));
                //ctx.putAll(cellInfo);
                }
                
        		}
              }}
          
            
       }
       
	return cellInfo;   
       
        }
}
