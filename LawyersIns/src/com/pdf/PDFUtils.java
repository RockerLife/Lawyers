package com.pdf;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.util.DateUtils;

public class PDFUtils {

	
	public static Date populateDate(Object policyEffdate) throws Exception{
		DateFormat formatter ; 
		java.util.Date date ; 
		formatter = new SimpleDateFormat("MM/dd/yyyy");
        return (java.util.Date)formatter.parse(getFormattedDateFromObject(policyEffdate));
	}
	
	public static String getFormattedDateFromObject(Object object)
	{
		String value = "";
		
		if(object == null)
			return value;
		
		if(object instanceof java.sql.Date)
			value = DateUtils.convertDateToString((java.sql.Date)object, "MM/dd/yyyy");
		else if(object instanceof java.sql.Timestamp)
			value = DateUtils.convertDateToString((java.sql.Timestamp)object, "MM/dd/yyyy");
		else {
//              if(object.toString().contains(" 00:00:00.0")){
//            	 value =  object.toString().substring(0, object.toString().lastIndexOf(" 00:00:00.0"));
//              }else
            	  value = object.toString();
		}
		return value;
		
	}
	
	
	public static void addHeader(Document document, String headerst) throws Exception
	{
		PdfPTable table1  = new PdfPTable(1);
		table1.setWidthPercentage(100);
  
		PdfPCell cell = new PdfPCell (new Paragraph (headerst,new Font(PDFGenerator.tahomabd,8,Font.NORMAL)));
		//cell.setColspan (8);
		//cell.setPadding ( 5.0f);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingBottom(2f);
		cell.setPaddingTop(0f);
		cell.setBackgroundColor(Color.lightGray);
		table1.addCell (cell);
		document.add(table1);
	}
	public static PdfPCell addHeader(String headerst) throws Exception
	{
		PdfPCell cell = new PdfPCell (new Paragraph (headerst,new Font(PDFGenerator.tahomabd,8,Font.NORMAL)));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingBottom(2f);
		cell.setPaddingTop(0f);
		cell.setBackgroundColor(Color.lightGray);
		return cell;
	}
	
	public static void addContent(Document document, String headerst) throws Exception
	{
		PdfPTable table1  = new PdfPTable(1);
		table1.setWidthPercentage(100);
  
		PdfPCell cell = new PdfPCell (new Paragraph (headerst,new Font(Font.TIMES_ROMAN,8,Font.BOLD)));
		cell.setColspan (8);
		cell.setPadding ( 5.0f);
		cell.setVerticalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(Color.lightGray);
		cell.setBorder(0);
		table1.addCell (cell);
		document.add(table1);
	}
	
	public static void addSpace(Document document) throws Exception
	{
		Paragraph p1  = new Paragraph("\n",new Font(Font.NORMAL,10));
		p1.setAlignment(Element.ALIGN_LEFT);
		document.add(p1);
	}
	
	public static PdfPCell addPDFCell(String name) throws Exception {
	
		return new PdfPCell (new Paragraph ( name ,new Font(Font.TIMES_ROMAN,5,Font.NORMAL)));
	 
	}
	
	public static String getValueFromMap(Map map, String name, boolean isBoolean) throws Exception {
		String result = "";
		
		if(map == null)
			return result;
		
		result = map.get(name)!=null?map.get(name).toString():"";
		if(isBoolean)
		{
			 if("Y".equals(result))
				 result = "Yes";
			 else if("N".equals(result))
				 result = "No";
		}
		
		return result;
	 
	}
	
	public static PdfPCell setPdfPCellProperties(PdfPCell cell) throws Exception
	{
		cell.setColspan (8);
		cell.setPadding ( 5.0f);
		cell.setVerticalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(Color.lightGray);
		cell.setBorder(0);
		return cell;
	}
	
	
	public static PdfPCell addPDFCelHeader(String name) throws Exception {
		
		PdfPCell cell =	 new PdfPCell (new Paragraph ( name ,new Font(Font.TIMES_ROMAN,10,Font.BOLD)));
//		cell.setColspan (8);
//		cell.setPadding ( 5.0f);
		cell.setVerticalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(Color.lightGray);
	return cell;
	 
	}
	
	public static PdfPCell addPDFCelSubHeader(String name) throws Exception {
		
		PdfPCell cell =	 new PdfPCell (new Paragraph ( name ,new Font(Font.TIMES_ROMAN,10,Font.BOLD)));
		cell.setVerticalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(Color.lightGray);
	return cell;
	 
	}
	public static PdfPCell addPDFCellwithBOLD(String name) throws Exception {
		
		PdfPCell cell =	 new PdfPCell (new Paragraph ( name ,new Font(Font.TIMES_ROMAN,5,Font.BOLD)));
//		cell.setColspan (8);
//		cell.setPadding ( 5.0f);
		cell.setVerticalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(Color.lightGray);
	return cell;
	 
	}
	
	public static PdfPCell getBlankCustomSizeCell(int size,int colspan,boolean border) throws Exception{
		Paragraph paragraph=new Paragraph ( " " ,new Font(Font.TIMES_ROMAN,size,Font.NORMAL));
		PdfPCell cell=new PdfPCell(paragraph);
		cell.setColspan(colspan);
		if(!border){
			cell.setBorder(0);
		}
		return cell;
	}
	public static PdfPCell addPDFCellWithDefalutSize(String name) throws Exception {
		
		return new PdfPCell (new Paragraph ( name ,new Font(Font.TIMES_ROMAN,10,Font.NORMAL)));	 
	}
	
	public static PdfPCell addPDFCellWithDefalutSizeItalic(String name) throws Exception {
		
		return new PdfPCell (new Paragraph ( name ,new Font(Font.TIMES_ROMAN,10,Font.ITALIC)));	 
	}
	
	public static PdfPCell addPDFCellWithDefalutSizeBoldItalic(String name) throws Exception {
		
		return new PdfPCell (new Paragraph ( name ,new Font(Font.TIMES_ROMAN,10,Font.BOLDITALIC)));	 
	}
	public static PdfPCell addPDFCellWithDefalutSizeBold(String name) throws Exception {
		
		return new PdfPCell (new Paragraph ( name ,new Font(Font.TIMES_ROMAN,10,Font.BOLD)));	 
	}
	
	public static PdfPCell addPDFCellWithCustomSize(String name,int size,boolean border) throws Exception {
		
		PdfPCell cell= new PdfPCell (new Paragraph ( name ,new Font(Font.TIMES_ROMAN,size,Font.NORMAL)));
		if(!border){
			cell.setBorder(0);
		}
		return cell;
	}
	
	public static PdfPCell getCustomFontValue(String name,int size,boolean border,Color color,int fontFamily,int style) throws Exception {
		
		PdfPCell cell= new PdfPCell (new Paragraph ( name ,new Font(fontFamily,size,style,color)));
		if(!border){
			cell.setBorder(0);
		}
		return cell;
	}
	public static PdfPCell addPDFCellWithCustomSizeItalic(String name,int size,boolean border) throws Exception {
		
		PdfPCell cell=new PdfPCell (new Paragraph ( name ,new Font(Font.TIMES_ROMAN,size,Font.ITALIC)));
		if(!border){
			cell.setBorder(0);
		}
		return cell;
	}
	
	public static PdfPCell addPDFCellWithCustomSizeBoldItalic(String name,int size,boolean border) throws Exception {
		
		PdfPCell cell=new PdfPCell (new Paragraph ( name ,new Font(Font.TIMES_ROMAN,size,Font.BOLDITALIC)));
		if(!border){
			cell.setBorder(0);
		}
		return cell;
	}
	public static PdfPCell addPDFCellWithCustomSizeBold(String name, int size,boolean border) throws Exception {
		
		PdfPCell cell= new PdfPCell (new Paragraph ( name ,new Font(Font.TIMES_ROMAN,size,Font.BOLD)));
		if(!border){
			cell.setBorder(0);
		}
		return cell;
	}
	
	public static PdfPCell addPDFCellValueWithDefalutSize(String name) throws Exception {
		
		PdfPCell pcell= new PdfPCell (new Paragraph ( name ,new Font(Font.TIMES_ROMAN,10,Font.NORMAL)));
		pcell.setBorder(0);
		return pcell;
	}
	
	public static PdfPCell getPdfPHeaderCell(String cotent) throws Exception
	{
		PdfPCell cell = new PdfPCell (new Paragraph (cotent,new Font(Font.TIMES_ROMAN,8,Font.BOLD)));
		cell.setColspan (8);
		cell.setPadding ( 5.0f);
		cell.setVerticalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(Color.lightGray);		
		return cell;
	}
	
	public static PdfPCell getBoldCell(String cotent) throws Exception
	{
		PdfPCell cell = new PdfPCell (new Paragraph (cotent,new Font(Font.TIMES_ROMAN,10,Font.BOLD)));
		return cell;
	}
	
	public static String  retifyAmountDecimal(String content) throws Exception
	{
		if(content.contains("."))
		{
			content = content.substring(0, content.indexOf("."));
		}
		return content;
	}
	
	private static PdfPCell getYesNoImageCell(String compareValue,PdfPTable table,PdfPCell pdfPCell) throws Exception{
		Image imageck=Image.getInstance(PDFGenerator.ck);
		Image imageuck=Image.getInstance(PDFGenerator.uck);
		PdfPCell cell1=null,cell2=null,cell3=null,cell4=null;
		cell1 = new PdfPCell(PDFUtils.getCustomFontValue("Yes", 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL));
		cell3 = new PdfPCell(PDFUtils.getCustomFontValue("No", 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL));
		if(compareValue.equalsIgnoreCase("Yes")){
			cell2 = new PdfPCell(imageck);
			cell2.setPaddingTop(4f);			
			cell4 = new PdfPCell(imageuck);
			cell4.setPaddingTop(4f);
			//pdfPCell = new PdfPCell(Image.getInstance(PDFGenerator.ckY));
		}else if(compareValue.equalsIgnoreCase("No")){
			cell2 = new PdfPCell(imageuck);
			cell2.setPaddingTop(4f);			
			cell4 = new PdfPCell(imageck);
			cell4.setPaddingTop(4f);
			//pdfPCell = new PdfPCell(Image.getInstance(PDFGenerator.ckN));
		}else{
			cell2 = new PdfPCell(imageuck);
			cell2.setPaddingTop(4f);			
			cell4 = new PdfPCell(imageuck);
			cell4.setPaddingTop(4f);
			//pdfPCell = new PdfPCell(Image.getInstance(PDFGenerator.YN));
		}
		cell1.setBorder(0);
		cell2.setBorder(0);
		cell3.setBorder(0);
		cell4.setBorder(0);
		cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell2);
		table.addCell(cell1);
		table.addCell(cell4);
		table.addCell(cell3);
		pdfPCell=new PdfPCell(table);
	//	pdfPCell.setBorder(0);
		return pdfPCell;
	}
	public static PdfPCell getYesNoImageObject(String compareValue) throws Exception{
		PdfPTable table=new PdfPTable(4);
		table.setWidthPercentage(5);
		float[] width={10f,2.5f,1.5f,2f};
		table.setWidths(width);
		return getYesNoImageCell(compareValue, table, new PdfPCell());
	}
	
	public static PdfPCell getYesNoImageObjectForTable(String compareValue) throws Exception{
		PdfPTable table=new PdfPTable(4);
		table.setWidthPercentage(5);
		float[] width={1.5f,2.5f,1.5f,2f};
		table.setWidths(width);
		return getYesNoImageCell(compareValue, table, new PdfPCell());
	}
	
	public static PdfPCell getckUckCellObject(String compareValue,String firstString, String secondString, int percent,float[] width,boolean bothUnCheckFlag) throws Exception{
		PdfPTable table=new PdfPTable(4);
		table.setWidthPercentage(percent);
		table.setWidths(width);
		Image imageck=Image.getInstance(PDFGenerator.ck);
		Image imageuck=Image.getInstance(PDFGenerator.uck);
		PdfPCell pdfPCell=null;
		PdfPCell cell1=null,cell2=null,cell3=null,cell4=null;
		if(compareValue.equalsIgnoreCase("Yes")){
			cell1 = new PdfPCell(PDFUtils.addPDFCellValueWithDefalutSize(firstString));
			cell2 = new PdfPCell(imageck);
			cell2.setPaddingTop(4f);			
			cell3 = new PdfPCell(PDFUtils.addPDFCellValueWithDefalutSize(secondString));
			cell4 = new PdfPCell(imageuck);
			cell4.setPaddingTop(4f);
		}else {
			if(bothUnCheckFlag){
				if(compareValue.equalsIgnoreCase("No")){
					cell1 = new PdfPCell(PDFUtils.addPDFCellValueWithDefalutSize(firstString));
					cell2 = new PdfPCell(imageuck);
					cell2.setPaddingTop(4f);			
					cell3 = new PdfPCell(PDFUtils.addPDFCellValueWithDefalutSize(secondString));
					cell4 = new PdfPCell(imageck);
					cell4.setPaddingTop(4f);			
					
				}else{
					cell1 = new PdfPCell(PDFUtils.addPDFCellValueWithDefalutSize(firstString));
					cell2 = new PdfPCell(imageuck);
					cell2.setPaddingTop(4f);			
					
					cell3 = new PdfPCell(PDFUtils.addPDFCellValueWithDefalutSize(secondString));
					cell4 = new PdfPCell(imageuck);
					cell4.setPaddingTop(4f);			
					
				}
			}else{
				cell1 = new PdfPCell(PDFUtils.addPDFCellValueWithDefalutSize(firstString));
				cell2 = new PdfPCell(imageuck);
				cell2.setPaddingTop(4f);			
				cell3 = new PdfPCell(PDFUtils.addPDFCellValueWithDefalutSize(secondString));
				cell4 = new PdfPCell(imageck);
				cell4.setPaddingTop(4f);
			}
		}	
		cell1.setBorder(0);
		cell2.setBorder(0);
		cell3.setBorder(0);
		cell4.setBorder(0);
		table.addCell(cell4);
		table.addCell(cell3);
		table.addCell(cell2);
		table.addCell(cell1);
		pdfPCell=new PdfPCell(table);
		pdfPCell.setBorder(0);
		return pdfPCell;
	}
}
