package com.pdf;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;

public class PracticeMangement {
	
	public void getDataForPracticeMangement(Context ctx) throws Exception
	{
		Object objFirm =SqlResources.getSqlMapProcessor(ctx).findByKey("Firm.findByKey", ctx);	
		ctx.put(PDFConstants.FIRM_FREEFORM_01, objFirm);
		
		Object objClientServiceDescFirm =SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsgeClientServiceDescFirm", ctx);
		ctx.put(PDFConstants.CLIENTSERVICEDESC_FIRM_FREEFORM_01, objClientServiceDescFirm);
					
	}
	
	public void makeLayoutForPracticeMangement(Context ctx) throws Exception
	{
		
	}
	
	public PdfPTable populatePracticeMangementData(Document document, Context ctx, PdfWriter writer) throws Exception
	{
		PdfPTable maintable  = new PdfPTable(3);
		maintable.setWidthPercentage(100);
		float[] width = { 2.5f, 51.5f, 21f };
		maintable.setWidths(width);
		maintable.setSpacingBefore(10f);
		maintable.setSpacingAfter(0f);

		PdfPCell cellHeader=PDFUtils.addHeader("PRACTICE MANAGEMENT SUPPLEMENT (As required in question 3 )");	
		cellHeader.setColspan(3);
		
		maintable.addCell(cellHeader);
		
		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
		
		Object objFirm = ctx.get(PDFConstants.FIRM_FREEFORM_01);
		Map firmMap = null;
		if(objFirm != null)
			firmMap = (Map)objFirm;	
		
		Object objClientServiceDesc = ctx.get(PDFConstants.CLIENTSERVICEDESC_FIRM_FREEFORM_01);		
		Map clientServiceDescMap = null;
		if (objClientServiceDesc != null)
			clientServiceDescMap = (HashMap) objClientServiceDesc;	
		
//		if(firmMap != null && clientServiceDescMap != null)
//		{
			String IsClientTerminated = PDFUtils.getValueFromMap(firmMap, "IsClientTerminated", true);
			String IsAnyDeclination = PDFUtils.getValueFromMap(firmMap, "IsAnyDeclination", true);
			String IsPolicyProhibiting = PDFUtils.getValueFromMap(firmMap, "IsPolicyProhibiting", true);
			String IsWrittenAnyQualityControlDocument = PDFUtils.getValueFromMap(firmMap, "IsWrittenAnyQualityControlDocument", true);
			
			String IsNonMonetaryCompensationRecieved = PDFUtils.getValueFromMap(firmMap, "IsNonMonetaryCompensationRecieved", true);
			String CurrentValueNmc = PDFUtils.getValueFromMap(firmMap, "CurrentValueNmc", false);
			String ClientNameNmc = PDFUtils.getValueFromMap(firmMap, "ClientNameNmc", false);
			
			String ClientIndustryDesc = PDFUtils.getValueFromMap(clientServiceDescMap, "ClientIndustryDesc", false);
			String ServiceIndustryDesc = PDFUtils.getValueFromMap(clientServiceDescMap, "ServiceIndustryDesc", false);
			
			String IsReducedNumberOfOwners = PDFUtils.getValueFromMap(firmMap, "IsReducedNumberOfOwners", true);
			String MergedOrNumberComment = PDFUtils.getValueFromMap(firmMap, "MergedOrNumberComment", false);
			
			PdfPCell q1SNo = PDFUtils.addPDFCellWithDefalutSize("1.");
			q1SNo.setBorder(0);
			maintable.addCell(q1SNo);
			
			PdfPCell q1label =  PDFUtils.addPDFCellWithDefalutSize("Does your Firm have dis-engagement procedures for terminating client relationships?");
			q1label.setBorder(0);
			maintable.addCell(q1label);
			
			PdfPCell q1labelValue = PDFUtils.getYesNoImageObject(IsClientTerminated);
			q1labelValue.setBorder(0);
			q1labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q1labelValue);
			
			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q2SNo = PDFUtils.addPDFCellWithDefalutSize("2.");
			q2SNo.setBorder(0);
			maintable.addCell(q2SNo);
			
			PdfPCell q2label =  PDFUtils.addPDFCellWithDefalutSize("Are declination / non-engagement letters used on all matters declined by the Firm?");
			q2label.setBorder(0);
			maintable.addCell(q2label);
			
			PdfPCell q2labelValue = PDFUtils.getYesNoImageObject(IsAnyDeclination);
			q2labelValue.setBorder(0);
			q2labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q2labelValue);
			
			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q3SNo = PDFUtils.addPDFCellWithDefalutSize("3.");
			q3SNo.setBorder(0);
			maintable.addCell(q3SNo);
			
			PdfPCell q3label =  PDFUtils.addPDFCellWithDefalutSize("Does your Firm have a written policy prohibiting business ventures with clients of the Firm?");
			q3label.setBorder(0);
			maintable.addCell(q3label);
			
			PdfPCell q3labelValue = PDFUtils.getYesNoImageObject(IsPolicyProhibiting);
			q3labelValue.setBorder(0);
			q3labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q3labelValue);
			
			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q4SNo = PDFUtils.addPDFCellWithDefalutSize("4.");
			q4SNo.setBorder(0);
			maintable.addCell(q4SNo);
			
			PdfPCell q4label =  PDFUtils.addPDFCellWithDefalutSize("Does your Firm have a written internal quality control document?");
			q4label.setBorder(0);
			maintable.addCell(q4label);
			
			PdfPCell q4labelValue = PDFUtils.getYesNoImageObject(IsWrittenAnyQualityControlDocument);
			q4labelValue.setBorder(0);
			q4labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q4labelValue);
			
			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q5SNo = PDFUtils.addPDFCellWithDefalutSize("5.");
			q5SNo.setBorder(0);
			maintable.addCell(q5SNo);
			
			PdfPCell q5label =  PDFUtils.addPDFCellWithDefalutSize("Within the past 5 years has your Firm or its personnel received non-monetary compensation for professional services performed for a client or its related entities?");
			q5label.setBorder(0);	
//			q5label.setColspan (2);
			maintable.addCell(q5label);
			
//			PdfPCell q5part1label =  PDFUtils.addPDFCellWithDefalutSize("professional services performed for a client or its related entities?");
//			q5part1label.setBorder(0);
			PdfPCell q5labelValue = PDFUtils.getYesNoImageObject(IsNonMonetaryCompensationRecieved);
			q5labelValue.setBorder(0);
			q5labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			
//			maintable.addCell(q5part1label);
			maintable.addCell(q5labelValue);
			
			PdfPCell blank = PDFUtils.addPDFCellWithDefalutSize(" ");
			blank.setBorder(0);
			
			if("Yes".equals(IsNonMonetaryCompensationRecieved))
			{
				maintable.addCell(blank);
				
				PdfPCell currentValueNmclabel =  PDFUtils.addPDFCellWithDefalutSize("If yes, current value of such non-monetary compensation  from  client (include related entities):");
				currentValueNmclabel.setBorder(0);
				maintable.addCell(currentValueNmclabel);
				
				PdfPCell currentValueNmcValue;
				if(!CurrentValueNmc.equals("")){
					currentValueNmcValue = PDFUtils.getCustomFontValue(PDFGenerator.formatter.format(new Double(CurrentValueNmc)), 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);	
				}else{
					currentValueNmcValue = PDFUtils.getCustomFontValue(CurrentValueNmc, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
				}
				currentValueNmcValue.setBorder(0);
				currentValueNmcValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
				maintable.addCell(currentValueNmcValue);
				
				maintable.addCell(blank);
				
				Phrase phrase1=new Phrase("Client Industry: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
				Phrase phrase2=new Phrase(ClientIndustryDesc,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
				Phrase phrase3=new Phrase("  Client Name: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
				Phrase phrase4=new Phrase(ClientNameNmc,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
				Phrase phrase5=new Phrase("  Services rendered: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
				Phrase phrase6=new Phrase(ServiceIndustryDesc,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
				Paragraph paragraph=new Paragraph();
				paragraph.add(phrase1);
				paragraph.add(phrase2);
				paragraph.add(phrase3);
				paragraph.add(phrase4);
				paragraph.add(phrase5);
				paragraph.add(phrase6);
				PdfPCell clientNameNmclabel = new PdfPCell(paragraph);
				clientNameNmclabel.setBorder(0);
				clientNameNmclabel.setColspan(2);
				maintable.addCell(clientNameNmclabel);
				
//				PdfPCell clientNameNmcValue = PDFUtils.addPDFCellWithDefalutSize(ClientNameNmc);
//				clientNameNmcValue.setBorder(0);
//				clientNameNmcValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
//				maintable.addCell(clientNameNmcValue);
			}
			
			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q6SNo = PDFUtils.addPDFCellWithDefalutSize("6.");
			q6SNo.setBorder(0);
			maintable.addCell(q6SNo);
			
			PdfPCell q6label =  PDFUtils.addPDFCellWithDefalutSize("Within the past 5 years, has your Firm reduced the number of its owners, partners, or officers by 50% or more?");
			q6label.setBorder(0);	
			maintable.addCell(q6label);
			
			PdfPCell q6labelValue = PDFUtils.getYesNoImageObject(IsReducedNumberOfOwners);
			q6labelValue.setBorder(0);
			q6labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q6labelValue);
			
			if("Yes".equals(IsReducedNumberOfOwners))
			{
				maintable.addCell(blank);
				Phrase phrase1=new Phrase("If yes, provide explanation:  ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
				Phrase phrase2=new Phrase(MergedOrNumberComment,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
				Paragraph paragraph=new Paragraph();
				paragraph.add(phrase1);
				paragraph.add(phrase2);
				PdfPCell q6_yes_label = new PdfPCell(paragraph);
				q6_yes_label.setBorder(0);
				q6_yes_label.setColspan(2);
				maintable.addCell(q6_yes_label);
				
//				PdfPCell q6_yes_labelValue =  PDFUtils.addPDFCellWithDefalutSize(MergedOrNumberComment);
//				q6_yes_labelValue.setBorder(0);
//				maintable.addCell(q6_yes_label);
			}			
			
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
