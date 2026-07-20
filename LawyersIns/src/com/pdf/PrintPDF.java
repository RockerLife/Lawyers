/*
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.pdf;

import com.util.InetLogger;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;
import com.util.SystemProperties;

 
 
 
public class PrintPDF  {
	private static final InetLogger logger = InetLogger.getInetLogger(PrintPDF.class);
	
	private static final long serialVersionUID = 2788260006560387781L;	
	
	public static void main(String[] args) throws Exception {
		Context ctx = new Context();
		String projectName = "AccountantIns";
		String resourcePath = SystemProperties.getInstance().getString(
				"xml.basedir")
				+ projectName + "//ibatis//maps//SqlMapConfig.xml";
		SqlResources.load(projectName, resourcePath);
		
		ctx.setProject(projectName);
		ctx.put("PolicyKey", "1132");
		ctx.put("TransactionSequence", "1132");
		ctx.put("VersionSequence", "1120");
		ctx.put("CoverageSequence", "1133");
		ctx.put("AccountKey", "1133");
		ctx.put("AddressKey", "1133");		
		ctx.put("UserNo","1");
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 	Document document = new Document(PageSize.LETTER,30f,30f,50f,30f);
	 	PdfWriter writer=PdfWriter.getInstance(document, baos);		
	 	writer.setPageEvent(new PdfGeneratorPageEvent());
	 	
		new PDFGenerator().execute(document, baos, ctx,writer);
		document.close();
		FileOutputStream out = new FileOutputStream("C://Documents and Settings//raghurajs//Desktop//AccountantIns.pdf");
	        
        baos.writeTo(out);
		out.flush(); 
		out.close();  
		
	}
	

    public void writePDF() {
    	logger.debug("into print pdf's method make pdf.");
		ByteArrayOutputStream baos = null;
	 	Document document = new Document(PageSize.A4);
		
		 
			//queryString = request.getQueryString();
			baos = new ByteArrayOutputStream();
			logger.debug("PDF output buffer created");
			try {
				PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
//				PdfWriter.getInstance(document,new FileOutputStream("Auto Quote Summary"));
				pdfWriter.toString();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				logger.error("Unexpected error", e);
			} 
    }
	

	public void makePdf(Context ctx, HttpServletRequest request, HttpServletResponse response) {
		
		ByteArrayOutputStream baos = null;
		ServletOutputStream out = null;
		Document document = new Document(PageSize.LETTER,30f,30f,50f,30f);
	 	FileOutputStream fos = null;
		try {
			//System.out.println("into print pdf's method make pdf.");
			PdfWriter writer;
			if(request != null)
			{
				//queryString = request.getQueryString();
				baos = new ByteArrayOutputStream();
				logger.debug("PDF output buffer created");
				writer=PdfWriter.getInstance(document, baos);
			}
			else
			{		
				fos = new FileOutputStream("AccountantSummary");
				writer=PdfWriter.getInstance(document,fos);
			}
		
			writer.setPageEvent(new PdfGeneratorPageEvent());
			//this is the calling where we going to add in to document.
			new PDFGenerator().execute(document, baos, ctx,writer);
			document.close();
			
			if(response != null)
			{
				// setting some response headers
				response.setHeader("Expires", "0");
				//response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Content-disposition","attachment; filename=\"" + "Accountant_" + ctx.get("QuoteNumber").toString() + ".pdf\"");				
				response.setHeader("Pragma", "public");
				
				// setting the content type				
				// response.setHeader("Content-Disposition","attachment;filename=Timesheet.pdf");
			    response.setContentType("application/pdf");
				
				// the contentlength is needed for MSIE!!!
				response.setContentLength(baos.size());
				
				// write ByteArrayOutputStream to the ServletOutputStream
				out = response.getOutputStream();
				baos.writeTo(out);
				out.flush();				 
			}
		
			
		}catch(DocumentException de) {
            logger.error(de.getMessage());
		}
		catch (Exception e2) {
			logger.debug("Error in " + getClass().getName() + "\n" + e2);
			logger.error("Unexpected error", e2);
		}
		finally{
			if(document != null)
				document.close();
			if(baos != null)
				try {
					baos.close();
				} catch (IOException e) {
					
					logger.error("Unexpected error", e);
				}
			if(out != null)
				try {
					out.close();
				} catch (IOException e) {
					
					logger.error("Unexpected error", e);
				}
			
			
		}
	}
	/**
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	public void destroy() {
		
	}
	

}
