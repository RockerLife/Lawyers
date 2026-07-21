package com.excel.downloadexcel;

import com.util.InetLogger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;

import com.excel.oldpim.ExcelDataBaseMapping;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;
import com.util.SystemProperties;

public class DownloadExcel implements Constants {
	private static final InetLogger logger = InetLogger.getInetLogger(DownloadExcel.class);
	
	Map dbValues = null;
	private int cols = 0; // No of columns	
	
	private static String projectName;
	private final String[] sheets ={"Firm", "AOP", "Practice Management", 
									"Coverage", "Corporate" ,"Environmental","Financial","Plantiff","Real Estate",
									"Tax","Title","Wills & Estate"};
	
	private final String[] formulas ={"Firm", "AOP", "Practice Management", 
			"Coverage","Corporate" ,"Environmental","Financial","Plantiff","Real Estate",
			"Tax","Title","Wills & Estate","Rating"};
	
	public static void main(String[] ar){
		try {
			
			Context ctx = new Context();
			projectName = "LawyersIns";
			String resourcePath = SystemProperties.getInstance().getString("xml.basedir") + projectName + "//ibatis//maps//SqlMapConfig.xml";
			SqlResources.load(projectName, resourcePath);
			
			ctx.setProject(projectName);
			ctx.put("PolicyKey", "6");
			ctx.put("TransactionSequence", "6");
			ctx.put("VersionSequence", "6");
			ctx.put("CoverageSequence", "6");
			ctx.put("AccountKey", "6");
			ctx.put("AddressKey", "6");	
			
			HSSFWorkbook wb;		
			String file = SystemProperties.getInstance().getString("xml.basedir") + "excel//RatingTemplate.xls";
			FileInputStream input = new FileInputStream(file);
			wb = new HSSFWorkbook(input);
			
	      //  new DownloadExcel().populateDataIntoExcel(ctx, wb);		
	        FileOutputStream out = new FileOutputStream("D://Rating.xls");
			wb.write(out);
			out.flush();
			out.close();	
			input.close();	
			
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
	}
	
	public void downloadExcel(Context ctx, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		FileInputStream input = null; 
		ServletOutputStream outputStream = null;
		
		try {			
			HSSFWorkbook wb;			
			String file = SystemProperties.getInstance().getString("xml.basedir") + "excel//RatingTemplate.xls";
			
			input = new FileInputStream(file);
			 
			wb = new HSSFWorkbook(input);
			
            populateDataIntoExcel(ctx, wb);
            
            outputStream = res.getOutputStream();
			wb.write(outputStream);
			outputStream.flush();			
			
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
		finally{
			if(input != null)
				try {
					input.close();
				} catch (IOException e) {
					
					logger.error("Unexpected error", e);
				}
			if(outputStream != null)
				try {
					outputStream.close();
				} catch (IOException e) {
					
					logger.error("Unexpected error", e);
				}			
		}
	}
	
	public void populateDataIntoExcel(Context ctx, HSSFWorkbook wb) throws Exception 
	{
		Map getSheetDatabasefield = null;
		
		for(int i=0; i<sheets.length; i++)
		{
			String sheet = sheets[i];
			
			getSheetDatabasefield = new ExcelDataBaseMapping().getCellInfo(ctx, wb.getSheet(sheet));        
			ctx.put("EXCEL_FIELD_MAPPING", getSheetDatabasefield);
		}		
		new Firm().getDataForFirm(ctx);
		new Firm().populateFirmData(ctx, wb);
		
		new AOP().getDataForAop(ctx);
		new AOP().populateAopData(ctx, wb);
		
		new PracticeMangement().getDataForPracticeMangement(ctx);
		new PracticeMangement().populatePracticeMangementData(ctx, wb);
		
		
		 if(ctx.get("manualrating") == null || (ctx.get("manualrating")!= null && "N".equals(ctx.get("manualrating"))))
		{
			Object objPolicyCoverage =SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.BasicInfoviewCoveragePolicy", ctx);
			ctx.put(Constants.POLICYCOVERAGE_FREEFORM_01, objPolicyCoverage);
		}
		else
		{
			Map mapPolicyCoverage = new HashMap();
			mapPolicyCoverage.put(Constants.IsClaimExpensesType, ctx.get(Constants.IsClaimExpensesType));
			mapPolicyCoverage.put(Constants.IsClaimOptionType, ctx.get(Constants.IsClaimOptionType));
			
			Object objLimit =SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtspopulateLimit", ctx);
			Object objDeductible =SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtspopulateDeductible", ctx);
			
			if(objLimit != null && objLimit instanceof Map)
			{
				mapPolicyCoverage.put(Constants.AggregateLimit, ((Map)objLimit).get(Constants.AggregateLimit));
				mapPolicyCoverage.put(Constants.OccuranceLimit, ((Map)objLimit).get(Constants.OccuranceLimit));
			}
			if(objDeductible != null && objDeductible instanceof Map)
			{
				mapPolicyCoverage.put(Constants.DeductibleAmount, ((Map)objDeductible).get(Constants.DeductibleAmount));
			}
			
			mapPolicyCoverage.put(Constants.Premium, ctx.get("ManualPremium"));
			ctx.put(Constants.POLICYCOVERAGE_FREEFORM_01, mapPolicyCoverage);
		}
	
		
			new Coverage().getDataForCoverage(ctx);
			new Coverage().populateCoverageData(ctx, wb);
		
		
		
		
		
		new Corporate().getDataForCorporate(ctx);
		new Corporate().populateCorporateData(ctx, wb);
		
		new Environmental().getDataForEnvironmental(ctx);
		new Environmental().populateEnvironmentalData(ctx, wb);
		
		new FinancialInstitution().getDataForFinancial(ctx);
		new FinancialInstitution().populateFinancialData(ctx, wb);
		
		new Plantiff().getDataForPlantiff(ctx);
		new Plantiff().populatePlantiffData(ctx, wb);
		
		new RealEstate().getDataForRealEstate(ctx);
		new RealEstate().populateRealEstateData(ctx, wb);
		
		new Tax().getDataForTax(ctx);
		new Tax().populateTaxData(ctx, wb);
		
		new Title().getDataForTitle(ctx);
		new Title().populateTitleData(ctx, wb);
		
		new WillsEstate().getDataForWillsEstate(ctx);
		new WillsEstate().populateWillsEstateData(ctx, wb);
		
		FormulaExecutor(wb);
	}
	
	public void FormulaExecutor(HSSFWorkbook wb)
	{
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		for(int i=0; i<formulas.length; i++)
		{
			String sheetName = formulas[i];
			if(sheetName.equals("Rating")){
				logger.debug("inside the sheet");
			}
			Sheet sheet = wb.getSheet(sheetName);         
	        for(Row r : sheet) {
	            for(Cell c : r) {
	                if(c.getCellType() == Cell.CELL_TYPE_FORMULA) {
	                	//System.out.println(c.getCellFormula());
	                	try{
	                		evaluator.evaluateFormulaCell(c);
	                	}catch(Exception e){
	                		logger.error("Unexpected error", e);
	                	}
	                }
	            }
	        }			
		}
		
        		
	}
	
	
	public DownloadExcel() {
		
	}
	
	public DownloadExcel(String projectName) {
		setProjectName(projectName);
	}	

	public void processExcel() throws Exception {
		try {
		
		String file = SystemProperties.getInstance().getString("xml.basedir") + "excel//RatingTemplate.xls";
		
		try (FileInputStream fis = new FileInputStream(file)) {
	    POIFSFileSystem fs = new POIFSFileSystem(fis);
	    HSSFWorkbook wb = new HSSFWorkbook(fs);

	    HSSFSheet sheet = null;
	    
	 //   System.out.println(BASIC_INFO_SHEET);
	  //  sheet =  wb.getSheet(BASIC_INFO_SHEET);
	//	processSheet(sheet);
		
//		System.out.println(GENERAL_SHEET);
//	    sheet =  wb.getSheet(GENERAL_SHEET);
//		processSheet(sheet);
//		
//		System.out.println(SUPP_INVEST_FINANCIAL_PLANNING);
//	    sheet =  wb.getSheet(SUPP_INVEST_FINANCIAL_PLANNING);
//		processSheet(sheet);
//		
//		System.out.println(SUPP_ATTESTATION);
//	    sheet =  wb.getSheet(SUPP_ATTESTATION);
//		processSheet(sheet);
//		
//		System.out.println(SUPP_COMPUTER_RELATED_SERVICES);
//	    sheet =  wb.getSheet(SUPP_COMPUTER_RELATED_SERVICES);
//		processSheet(sheet);
//		
//		System.out.println(PRACTICE_MANAGEMENT_SHEET);
//		sheet =  wb.getSheet(PRACTICE_MANAGEMENT_SHEET);
//		processSheet(sheet);
//		
//		System.out.println(BUSINESS_MANAGEMENT_SHEET);
//		sheet =  wb.getSheet(BUSINESS_MANAGEMENT_SHEET);
//		processSheet(sheet);
		
		try (FileOutputStream out = new FileOutputStream("C://Documents and Settings//raghurajs//Desktop//Rating.xls")) {
			wb.write(out);
			out.flush();
		}
		}
		
		} catch (FileNotFoundException e) {
			logger.error("Unexpected error", e);
		}
		catch (IOException e) {
			logger.error("Unexpected error", e);
		}
		
	}

	private void processSheet(HSSFSheet sheet) throws Exception{
		HSSFRow row;
	    HSSFCell cell;
	    int rows; // No of rows
	    rows = sheet.getPhysicalNumberOfRows();
	    int tmp = 0;
	    
	    populateDBValue();
	    
	    // This trick ensures that we get the data properly even if it doesn't start from first few rows
	    for(int i = 0; i < 10 || i < rows; i++) {
	        row = sheet.getRow(i);
	        if(row != null) {
	            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
	            if(tmp > cols) 
	            	cols = tmp;
	        }
	    }
	    
	    for(int r = 0; r < rows; r++) {
	    	logger.debug("");
	        row = sheet.getRow(r);
	        if(row != null) {
	        	for(int c=0; c < row.getLastCellNum(); c++){
	        		logger.debug("\t" + row.getCell(c));
	        		if(null!=row.getCell(c) 
	        				&& HSSFCell.CELL_TYPE_STRING == row.getCell(c).getCellType()
	        				&& row.getCell(c).getStringCellValue().length() > 1
	        				&& row.getCell(c).getStringCellValue().startsWith(CHECK_FIELD_SIGN) 
	        				&& row.getCell(c).getStringCellValue().endsWith(CHECK_FIELD_SIGN)){
//		        		System.out.println(row.getRowNum() + " " + row.getCell(c).getCellNum() + "\t" + row.getCell(c));
	        			populateExcelCell(row.getCell(c));
	        		}
	        	}
	        }
	    }
	}
	
	public void populateDBValue() throws Exception {
		
		if(dbValues == null){
			AccessDB accessDB = new AccessDB(getProjectName());
			dbValues = accessDB.populateDBValues();
		}
		
	}	
	
	public void populateExcelCell(HSSFCell cell) throws Exception {
		
		String dbFieldToBeCalculated = cell.getStringCellValue().substring(1, cell.getStringCellValue().length()-1);
		cell.setCellValue("Calculated Value for " + dbValues.get(dbFieldToBeCalculated) + " from database.");
	}	

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public void downloadDocument(Context ctx, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		try (ServletOutputStream outputStream = res.getOutputStream()) {			
			String docName = (String)ctx.get("DocFileName");
			logger.debug("Document Name is --- " + docName);
			String path = "C:\\Tomcat\\webapps\\AccountantUploadFiles";
			
			try (FileInputStream fin = new FileInputStream(path + "\\" + docName)) {
			res.setContentLength(fin.available());
			
			byte[] barr = new byte[fin.available()];
			fin.read(barr);
			
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			bout.write(barr);
			bout.writeTo(outputStream);
			}

            outputStream.flush();
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
	}

	public void downloadAutomationExcel(Context ctx, HttpServletRequest req, HttpServletResponse response) throws Exception {
		
		String file;
		
		response.setContentType("text/plain");
		 
        
       if(ctx.get("ProjectType_admin").equals("Lwy")){
    	   response.setHeader("Content-disposition","attachment; filename=Testdatasheet_Lawyers.xlsx"); // Used to name the download file and its format
           file = SystemProperties.getInstance().getString("file.downloadfile") + "Testdatasheet_Lawyers.xlsx";
   		  
       }else{
    	   response.setHeader("Content-disposition","attachment; filename=Testdatasheet_Accountants.xlsx"); // Used to name the download file and its format
           file = SystemProperties.getInstance().getString("file.downloadfile") + "Testdatasheet_Accountants.xlsx";
   		  
       }
        
        File my_file = new File(file); // We are downloading .txt file, in the format of doc with name check - check.doc

        
        OutputStream out = response.getOutputStream();
		try (FileInputStream in = new FileInputStream(my_file)) {
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0){
			   out.write(buffer, 0, length);
			}
		}
		out.flush();
	}
	
	public void downloadPolicyPdf(Context ctx, HttpServletRequest req, HttpServletResponse response) throws Exception {
		
		String file;
		
		response.setContentType("text/plain");
		 
		String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//Policy Form_" + ctx.get("QuoteNumber").toString() + ".pdf";		
		
      
    	   response.setHeader("Content-disposition","attachment; filename=Policy Form_" + ctx.get("QuoteNumber").toString() + ".pdf"); // Used to name the download file and its format
           file = outFile;
   		  
      
        
        File my_file = new File(file); // We are downloading .txt file, in the format of doc with name check - check.doc

        
        OutputStream out = response.getOutputStream();
		try (FileInputStream in = new FileInputStream(my_file)) {
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0){
			   out.write(buffer, 0, length);
			}
		}
		out.flush();
	}
	
public void downloadEndorsementPdf(Context ctx, HttpServletRequest req, HttpServletResponse response,String outFile,String fileName) throws Exception {
		
		String file;
		
		response.setContentType("text/plain");
		 
		//String outFile = SystemProperties.getInstance().getString("html.basedir") + "data//Policy Form_" + ctx.get("QuoteNumber").toString() + ".pdf";		
		
      
    	   response.setHeader("Content-disposition","attachment; filename=" + fileName+ ".pdf"); // Used to name the download file and its format
           file = outFile;
   		  
      
        
        File my_file = new File(file); // We are downloading .txt file, in the format of doc with name check - check.doc

        
        OutputStream out = response.getOutputStream();
		try (FileInputStream in = new FileInputStream(my_file)) {
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0){
			   out.write(buffer, 0, length);
			}
		}
		out.flush();
	}

}
