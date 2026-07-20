package com.pdf;

import java.awt.Color;
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

public class General {
	
	public void getDataForGeneral(Context ctx) throws Exception
	{
		Object objFirm =SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.generalviewgetPriorPolicyInfo", ctx);	
		ctx.put(PDFConstants.LABELS_FREEFORM_01, objFirm);
		
		Object objSeperateEntitySupp =SqlResources.getSqlMapProcessor(ctx).findByKey("SeperateEntitySupp.findByKey", ctx);	
		ctx.put(PDFConstants.GENERAL_FREEFORM_02, objSeperateEntitySupp);
		
		Object objFundsControlledSupp =SqlResources.getSqlMapProcessor(ctx).findByKey("FundsControlledSupp.findByKey", ctx);
		ctx.put(PDFConstants.GENERAL_FREEFORM_03, objFundsControlledSupp);
		
		
		Object listTrusteeOrEstateSupp =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.generalviewListTrusteeOrEstateSupp", ctx);
		ctx.put(PDFConstants.GENERAL_LIST_01, listTrusteeOrEstateSupp);
		
		Object listOutsideInterestSupp =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.generalviewgetOutsideInterestSupp", ctx);
		ctx.put(PDFConstants.GENERAL_LIST_04, listOutsideInterestSupp);
			
	}
	
	public void makeLayoutForGeneral(Context ctx) throws Exception
	{
		
	}
	
	public PdfPTable populateGeneralData(Document document, Context ctx) throws Exception
	{
		PdfPTable table = new PdfPTable(2);
		return table;
	}
	
	public PdfPTable populateSeperateEntity(Document document, Context ctx, PdfWriter writer) throws Exception
	{
		PdfPTable maintable  = new PdfPTable(3);
		maintable.setWidthPercentage(100);
		float[] width = { 2.5f, 51.5f, 21f };
		maintable.setWidths(width);
		maintable.setSpacingBefore(10f);
		maintable.setSpacingAfter(0f);

		
		PdfPCell cellHeader=PDFUtils.addHeader("SEPARATE ENTITY SUPPLEMENT (as required in question 4)");
		cellHeader.setColspan(3);
		maintable.addCell(cellHeader);
		
		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
		
		Object objSepEntity = ctx.get(PDFConstants.GENERAL_FREEFORM_02);
		Map sepEntityMap = null;
		if(objSepEntity != null)
			sepEntityMap = (Map)objSepEntity;		
		
//		if(sepEntityMap != null )
//		{	
			String EntityNameSe = PDFUtils.getValueFromMap(sepEntityMap, "EntityNameSe", false);
			String EstablisedSeDate = PDFUtils.getValueFromMap(sepEntityMap, "EstablisedSeDate", false);
			String PercentOfOwnershipSe = PDFUtils.getValueFromMap(sepEntityMap, "PercentOfOwnershipSe", false);
			String TotalProfessionalStaffSe = PDFUtils.getValueFromMap(sepEntityMap, "TotalProfessionalStaffSe", false);
			String TotalSupportStaffSe = PDFUtils.getValueFromMap(sepEntityMap, "TotalSupportStaffSe", false);
			String DetailedDescSe = PDFUtils.getValueFromMap(sepEntityMap, "DetailedDescSe", false);
			
			String LastFiscalYearGrossRevenueSe = PDFUtils.getValueFromMap(sepEntityMap, "LastFiscalYearGrossRevenueSe", false);
			String EstimateCurrentYearGrossRevenueSe = PDFUtils.getValueFromMap(sepEntityMap, "EstimateCurrentYearGrossRevenueSe", false);
			String LastFiscalYearSeEndDate = PDFUtils.getValueFromMap(sepEntityMap, "LastFiscalYearSeEndDate", false);
			String EstimateCurrentYearSeEndDate = PDFUtils.getValueFromMap(sepEntityMap, "EstimateCurrentYearSeEndDate", false);
			
			PdfPCell firstline = PDFUtils.addPDFCellWithDefalutSize("Provide the following for each entity:");
			firstline.setBorder(0);
			firstline.setColspan(3);
			maintable.addCell(firstline);
			
			PdfPCell q1SNo = PDFUtils.addPDFCellWithDefalutSize("1.");
			q1SNo.setBorder(0);
			maintable.addCell(q1SNo);
			
			Phrase phrase1=new Phrase("Name of entity and form of entity (LLC, LLP, Inc. etc.):  ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
			Phrase phrase2=new Phrase(EntityNameSe,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
			Paragraph paragraph=new Paragraph();
			paragraph.add(phrase1);
			paragraph.add(phrase2);
			PdfPCell q1label = new PdfPCell(paragraph);
			q1label.setBorder(0);
			q1label.setColspan(2);
			maintable.addCell(q1label);
			
//			PdfPCell q1labelValue =  PDFUtils.addPDFCellWithDefalutSize(EntityNameSe);
//			q1labelValue.setBorder(0);
//			q1labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
//			maintable.addCell(q1labelValue);

			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q2SNo = PDFUtils.addPDFCellWithDefalutSize("2.");
			q2SNo.setBorder(0);
			maintable.addCell(q2SNo);
			
			PdfPTable pdfPTableQ2=new PdfPTable(2);
			pdfPTableQ2.setWidthPercentage(50);
			float[] width1={1f,2f};
			pdfPTableQ2.setWidths(width1);
			
			PdfPCell q2label =  PDFUtils.addPDFCellWithDefalutSize("Date established: ");
			q2label.setBorder(0);
			pdfPTableQ2.addCell(q2label);
			
			PdfPCell q2labelValue =  PDFUtils.getCustomFontValue(EstablisedSeDate, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
			q2labelValue.setBorder(0);
			pdfPTableQ2.addCell(q2labelValue);
			
			PdfPCell blank = PDFUtils.addPDFCellWithDefalutSize(" ");
			blank.setBorder(0);
			
			PdfPCell cellDateFormat=PDFUtils.getCustomFontValue("(YYYY)", 8, false, new Color(0.0f,0.0f,0.0f), Font.TIMES_ROMAN, Font.ITALIC);
			cellDateFormat.setBorder(0);
			
			pdfPTableQ2.addCell(blank);
			pdfPTableQ2.addCell(cellDateFormat);
			
			PdfPCell cell=new PdfPCell(pdfPTableQ2);
			cell.setBorder(0);
//			cell.setColspan(2);
			maintable.addCell(cell);
			maintable.addCell(blank);

			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q3SNo = PDFUtils.addPDFCellWithDefalutSize("3.");
			q3SNo.setBorder(0);
			maintable.addCell(q3SNo);
			
			Phrase phrase3=new Phrase("Percent of ownership held by your Firm and all Firm personnel:  ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
			Phrase phrase4=new Phrase(PercentOfOwnershipSe +"%",new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
			Paragraph paragraph1=new Paragraph();
			paragraph1.add(phrase3);
			paragraph1.add(phrase4);
			PdfPCell q3label = new PdfPCell(paragraph1);
			q3label.setBorder(0);
			q3label.setColspan(2);
			maintable.addCell(q3label);
			
//			PdfPCell q3labelValue =  PDFUtils.addPDFCellWithDefalutSize(PercentOfOwnershipSe +"%");
//			q3labelValue.setBorder(0);
//			q3labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
//			maintable.addCell(q3labelValue);

			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q4SNo = PDFUtils.addPDFCellWithDefalutSize("4.");
			q4SNo.setBorder(0);
			maintable.addCell(q4SNo);
			
			Phrase phrase5=new Phrase("Total professional staff: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
			Phrase phrase6;
			if(!TotalProfessionalStaffSe.equals("")){
				phrase6=new Phrase(PDFGenerator.numberFormat.format(new Double(TotalProfessionalStaffSe)),new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));		
			}else{
				phrase6=new Phrase(TotalProfessionalStaffSe,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
			}
			Phrase phrase7=new Phrase("  Total support staff:  ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
			Phrase phrase8;
			if(!TotalSupportStaffSe.equals("")){
				phrase8=new Phrase(PDFGenerator.numberFormat.format(new Double(TotalSupportStaffSe)),new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));	
			}else{
				phrase8=new Phrase(TotalSupportStaffSe,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
			}
			Paragraph paragraph2=new Paragraph();
			paragraph2.add(phrase5);
			paragraph2.add(phrase6);
			paragraph2.add(phrase7);
			paragraph2.add(phrase8);
			PdfPCell q4alabel = new PdfPCell(paragraph2);
			q4alabel.setBorder(0);
			q4alabel.setColspan(2);
			maintable.addCell(q4alabel);
			
//			PdfPCell q4blabel =  PDFUtils.addPDFCellWithDefalutSize("  Total support staff:  " + TotalSupportStaffSe);
//			q4blabel.setBorder(0);
//			maintable.addCell(q4blabel);

			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q5SNo = PDFUtils.addPDFCellWithDefalutSize("5.");
			q5SNo.setBorder(0);
			maintable.addCell(q5SNo);
			
			Phrase phrase9=new Phrase("Provide a detailed description of the entity’s services: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
			Phrase phrase10=new Phrase(DetailedDescSe,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
			Paragraph paragraph3=new Paragraph();
			paragraph3.add(phrase9);
			paragraph3.add(phrase10);
			PdfPCell q5label = new PdfPCell(paragraph3);
			q5label.setBorder(0);
			q5label.setColspan(2);
			maintable.addCell(q5label);
			
//			PdfPCell q5labelValue =  PDFUtils.addPDFCellWithDefalutSize(DetailedDescSe);
//			q5labelValue.setBorder(0);
//			q5labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
//			maintable.addCell(q5labelValue);			
			
			PdfPTable revenuetable  = new PdfPTable(2);
			revenuetable.setWidthPercentage(25);
			revenuetable.addCell(PDFUtils.addPDFCelHeader("Last Fiscal Year"));
			revenuetable.addCell(PDFUtils.addPDFCelHeader("Estimate For Current Year"));
			
			PdfPCell q6lastyear =  PDFUtils.getCustomFontValue("FYE: " + LastFiscalYearSeEndDate + " (MM/YYYY)", 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
//			q6lastyear.setBorder(0);
			revenuetable.addCell(q6lastyear);
			
			PdfPCell q6currentyear =  PDFUtils.getCustomFontValue("FYE: " + EstimateCurrentYearSeEndDate + " (MM/YYYY)", 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
//			q6currentyear.setBorder(0);
			revenuetable.addCell(q6currentyear);
			
			PdfPCell q6lastyearrevenue;
			if(!LastFiscalYearGrossRevenueSe.equals("")){
				q6lastyearrevenue =  PDFUtils.getCustomFontValue(PDFGenerator.formatter.format(new Double(LastFiscalYearGrossRevenueSe)), 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);	
			}else{
				q6lastyearrevenue =  PDFUtils.getCustomFontValue(LastFiscalYearGrossRevenueSe, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
			}
//			q6lastyearrevenue.setBorder(0);
			revenuetable.addCell(q6lastyearrevenue);
			PdfPCell q6currentyearrevenue;
			if(!EstimateCurrentYearGrossRevenueSe.equals("")){
				q6currentyearrevenue =  PDFUtils.getCustomFontValue(PDFGenerator.formatter.format(new Double(EstimateCurrentYearGrossRevenueSe)), 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);	
			}else{
				q6currentyearrevenue =  PDFUtils.getCustomFontValue(EstimateCurrentYearGrossRevenueSe, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
			}
//			q6currentyearrevenue.setBorder(0);
			revenuetable.addCell(q6currentyearrevenue);

			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q6SNo = PDFUtils.addPDFCellWithDefalutSize("6.");
			q6SNo.setBorder(0);
			maintable.addCell(q6SNo);
			
			PdfPCell q6label =  PDFUtils.addPDFCellWithDefalutSize("Gross Annual Revenue:");
			q6label.setBorder(0);
			q6label.setColspan(2);
			maintable.addCell(q6label);
			
			maintable.addCell(blank);
			
			PdfPCell q6labelValue =  new PdfPCell(revenuetable);
			q6labelValue.setBorder(0);
//			q6labelValue.setColspan(2);
			maintable.addCell(q6labelValue);
			maintable.addCell(blank);
//		}
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

}
