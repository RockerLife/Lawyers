package com.pdf;

import java.util.List;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;

public class ComputerService {
	
	public void getDataForComputerService(Context ctx) throws Exception
	{
		Object listITServicesSupp =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.computer-servicesviewgetITServicesSupp", ctx);
		ctx.put(PDFConstants.COMPUTERSERVICE_LIST_03, listITServicesSupp);
	}
	
	public void makeLayoutForComputerService(Context ctx) throws Exception
	{
		
	}
	
	public PdfPTable populateComputerServiceData(Document document, Context ctx, PdfWriter writer) throws Exception
	{
		PDFUtils.addSpace(document);		
		PdfPTable maintable  = new PdfPTable(3);
		maintable.setWidthPercentage(100);
		float[] width = {2.5f, 51.5f, 21f };
		maintable.setWidths(width);
		maintable.setSpacingBefore(10f);
		maintable.setSpacingAfter(0f);

		PdfPCell cellHeader=PDFUtils.addHeader("COMPUTER RELATED SERVICES SUPPLEMENT (As required in question 14. N)");
		cellHeader.setColspan(3);
		maintable.addCell(cellHeader);
		
		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
		
		PdfPCell q1SNo = PDFUtils.addPDFCellWithDefalutSize("1.");
		q1SNo.setBorder(0);
		maintable.addCell(q1SNo);
		
		PdfPCell q1label =  PDFUtils.addPDFCellWithDefalutSize("Please complete below  the type of Information Technology Services:");
		q1label.setBorder(0);
		q1label.setColspan(2);
		maintable.addCell(q1label);
		

		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
		
//		document.add(PDFUtils.addPDFCellWithDefalutSize("Please complete below  the type of Information Technology Services:"));
		PdfPTable pdfPTable=populateComputerServiceList(document, ctx);
		
		PdfPCell pdfPCellComputer=new PdfPCell(pdfPTable);
		pdfPCellComputer.setColspan(3);
		pdfPCellComputer.setBorder(0);
		maintable.addCell(pdfPCellComputer);
		
		float availableSpace = (writer.getVerticalPosition(false)- document.bottomMargin()-15);
		maintable.setTotalWidth(500f);
		float height=0;
		for(int i=0;i<maintable.getRows().size();i++){
			height +=maintable.getRowHeight(i);
		}
		if(availableSpace<height){
			document.newPage();
		}
		document.add(maintable);
		return maintable;
	}
	
	public PdfPTable populateComputerServiceList(Document document, Context ctx) throws Exception
	{
		Object obj = ctx.get(PDFConstants.COMPUTERSERVICE_LIST_03);
		List list = null;
		if(obj != null)
			list = (List) obj;
		
		Object objFirm = ctx.get(PDFConstants.FIRM_FREEFORM_01);
		Map firmMap = null;
		if (objFirm != null)
			firmMap = (Map) objFirm;		
		
		boolean otherFlag = false;		
		PdfPTable headtable = null;
		if(list != null)
		{
			headtable = new PdfPTable(4);
			headtable.setWidthPercentage(100);
			float[] widths={2f,1f,1.5f,1.5f};
			headtable.setWidths(widths);
			headtable.addCell(PDFUtils.addPDFCelHeader(" "));
			headtable.addCell(PDFUtils.addPDFCelHeader("Revenues From Last Fiscal Year"));
			headtable.addCell(PDFUtils.addPDFCelHeader("Estimated Revenue from Current Fiscal Year"));
			headtable.addCell(PDFUtils.addPDFCelHeader("Are Written Agreements or Engagement Letters used Annually?"));
			//document.add(headtable);
			
			for(int i=0; i< list.size(); i++)
			{
				//PdfPTable rowtable = new PdfPTable(8);
				//rowtable.setWidthPercentage(100);
				
				Map map = (Map)list.get(i);
				String ITServiceCategoryDesc = PDFUtils.getValueFromMap(map, "ITServiceCategoryDesc", false);
				String RevenuesFromLastYear = (PDFUtils.getValueFromMap(map, "RevenuesFromLastYear", false));
				String EstimatedRevenue = PDFUtils.getValueFromMap(map, "EstimatedRevenue", false);
				String IsAnnually = PDFUtils.getValueFromMap(map, "IsAnnually", true);
				
				if("0".equals(RevenuesFromLastYear))
					RevenuesFromLastYear = "";
				if("0".equals(EstimatedRevenue))
					EstimatedRevenue = "";
				
				if(i==4 && !"".equals(RevenuesFromLastYear))
				{
					otherFlag = true;
				}				
				
				PdfPCell cell =  PDFUtils.addPDFCellWithDefalutSize(ITServiceCategoryDesc);
				headtable.addCell(cell);
				if(!RevenuesFromLastYear.equals("")){
					cell =  PDFUtils.addPDFCellWithDefalutSize(PDFGenerator.formatter.format(new Double(RevenuesFromLastYear)));	
				}else{
					cell =  PDFUtils.addPDFCellWithDefalutSize(RevenuesFromLastYear);
				}
				headtable.addCell(cell);
				if(!EstimatedRevenue.equals("")){
					cell =  PDFUtils.addPDFCellWithDefalutSize(PDFGenerator.formatter.format(new Double(EstimatedRevenue)));	
				}else{
					cell =  PDFUtils.addPDFCellWithDefalutSize(EstimatedRevenue);
				}
				headtable.addCell(cell);
				cell =  PDFUtils.getYesNoImageObjectForTable(IsAnnually);
				cell.setPadding(3f);
				headtable.addCell(cell);						
				
				
				//document.add(rowtable);
				
			}
		}
		
		if(otherFlag)
		{
			String OtherITServiceComment = PDFUtils.getValueFromMap(firmMap, "OtherITServiceComment", false);
			Phrase phrase1= new Phrase("Please describe: ",new Font(Font.TIMES_ROMAN, 9, Font.NORMAL));
			Phrase phrase2= new Phrase(OtherITServiceComment,new Font(Font.TIMES_ROMAN, 9, Font.NORMAL));
			Paragraph paragraph=new Paragraph();
			paragraph.add(phrase1);
			paragraph.add(phrase2);
			PdfPCell cell=new PdfPCell(paragraph);
			cell.setColspan(4);
			cell.setBorder(0);
			headtable.addCell(cell);			
		}
		
		
		return headtable;
	}
}
