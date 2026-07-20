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

public class BusinessManagment {
	
	public void getDataForBusinessManagment(Context ctx) throws Exception
	{
		Object objFundsControlledSupp =SqlResources.getSqlMapProcessor(ctx).findByKey("FundsControlledSupp.findByKey", ctx);
		ctx.put(PDFConstants.GENERAL_FREEFORM_03, objFundsControlledSupp);	
		
		Object objClientServiceDescFundControl =SqlResources.getSqlMapProcessor(ctx).findByKey("SqlStmts.UserStatementsaveAccountstmtsgeClientServiceDescFundControl", ctx);
		ctx.put(PDFConstants.CLIENTSERVICEDESC_FUNDCONTROL_FREEFORM_01, objClientServiceDescFundControl);
				
	}
	
	public void makeLayoutForBusinessManagment(Context ctx) throws Exception
	{
		
	}
	
	public PdfPTable populateBusinessManagmentData(Document document, Context ctx, PdfWriter writer) throws Exception
	{
		PdfPTable maintable  = new PdfPTable(3);
		maintable.setWidthPercentage(100);
		float[] width = {2.5f, 51.5f, 21f };
		maintable.setWidths(width);
		maintable.setSpacingBefore(10f);
		maintable.setSpacingAfter(0f);

		PdfPCell cellHeader=PDFUtils.addHeader("BUSINESS MANAGEMENT SUPPLEMENT (As required in question 8 and14 R)");
		cellHeader.setColspan(3);
		maintable.addCell(cellHeader);
		
		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
		
		Object objBusinessMngt = ctx.get(PDFConstants.GENERAL_FREEFORM_03);
		Map businessMngtMap = null;
		if(objBusinessMngt != null)
			businessMngtMap = (Map)objBusinessMngt;	
		
		Object objClientServiceDesc = ctx.get(PDFConstants.CLIENTSERVICEDESC_FUNDCONTROL_FREEFORM_01);		
		Map clientServiceDescMap = null;
		if (objClientServiceDesc != null)
			clientServiceDescMap = (HashMap) objClientServiceDesc;
		
		
//		if(businessMngtMap != null )
//		{	
			String TotlaNumberOfClientsFc = PDFUtils.getValueFromMap(businessMngtMap, "TotlaNumberOfClientsFc", false);
			String TotalAmountOfClientFundsFc = PDFUtils.getValueFromMap(businessMngtMap, "TotalAmountOfClientFundsFc", false);
			
			String IsControlFundsForBusinessAndEntimentFc = PDFUtils.getValueFromMap(businessMngtMap, "IsControlFundsForBusinessAndEntimentFc", true);
			String NumberOfClientsFc = PDFUtils.getValueFromMap(businessMngtMap, "NumberOfClientsFc", false);
			String IsCountersignatureRequiredFc = PDFUtils.getValueFromMap(businessMngtMap, "IsCountersignatureRequiredFc", true);
			
			String IsBankAccountReconciledFc = PDFUtils.getValueFromMap(businessMngtMap, "IsBankAccountReconciledFc", true);
			
			String IsFirmPersonnelBusinessManagerFc = PDFUtils.getValueFromMap(businessMngtMap, "IsFirmPersonnelBusinessManagerFc", true);
			String ProvideNumberOfClientFc = PDFUtils.getValueFromMap(businessMngtMap, "ProvideNumberOfClientFc", false);
			String ClientIndustryFc = PDFUtils.getValueFromMap(clientServiceDescMap, "ClientIndustryDescFc", false);
			
			String NumberOfFirmEmployeesFc = PDFUtils.getValueFromMap(businessMngtMap, "NumberOfFirmEmployeesFc", false);
			String IsEmployeeDishonestyCoverageFc = PDFUtils.getValueFromMap(businessMngtMap, "IsEmployeeDishonestyCoverageFc", true);
			
			PdfPCell q1SNo = PDFUtils.addPDFCellWithDefalutSize("1.");
			q1SNo.setBorder(0);
			maintable.addCell(q1SNo);
			
			PdfPCell q1label =  PDFUtils.addPDFCellWithDefalutSize("What is the total number of clients for which your Firm controls or disburses funds?  ");
			q1label.setBorder(0);
			maintable.addCell(q1label);
			
			PdfPCell q1labelValue;
			
			if(!TotlaNumberOfClientsFc.equals("")){
				q1labelValue =  PDFUtils.getCustomFontValue(PDFGenerator.numberFormat.format(new Double(TotlaNumberOfClientsFc)), 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);				
			}else{
				q1labelValue =  PDFUtils.getCustomFontValue(TotlaNumberOfClientsFc, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);	
			}
			q1labelValue.setBorder(0);
			q1labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q1labelValue);
			
			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));			
			
			PdfPCell q2SNo = PDFUtils.addPDFCellWithDefalutSize("2.");
			q2SNo.setBorder(0);
			maintable.addCell(q2SNo);
			
			PdfPCell q2label =  PDFUtils.addPDFCellWithDefalutSize("List total amount of client funds your Firm disburses annually:");
			q2label.setBorder(0);
			maintable.addCell(q2label);
			PdfPCell q2labelValue;
			if(!TotalAmountOfClientFundsFc.equals("")){
				q2labelValue =  PDFUtils.getCustomFontValue(PDFGenerator.formatter.format(new Double(TotalAmountOfClientFundsFc)), 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);	
			}else{
				q2labelValue =  PDFUtils.getCustomFontValue(TotalAmountOfClientFundsFc, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
			}
			q2labelValue.setBorder(0);
			q2labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q2labelValue);

			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));			
			
			PdfPCell q3SNo = PDFUtils.addPDFCellWithDefalutSize("3.");
			q3SNo.setBorder(0);
			maintable.addCell(q3SNo);
			
			PdfPCell q3label =  PDFUtils.addPDFCellWithDefalutSize("Do you provide business/personal management or family office services or control funds for clients in the entertainment or sports industry?");
			q3label.setBorder(0);
//			q3label.setColspan(2);
			maintable.addCell(q3label);
			
//			PdfPCell q3part1label =  PDFUtils.addPDFCellWithDefalutSize("clients in the entertainment or sports industry? ");
//			q3part1label.setBorder(0);
//			maintable.addCell(q3part1label);
			
			PdfPCell q3labelValue =  PDFUtils.getYesNoImageObject(IsControlFundsForBusinessAndEntimentFc);
			q3labelValue.setBorder(0);
			q3labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q3labelValue);
			
			PdfPCell blank = PDFUtils.addPDFCellWithDefalutSize(" ");
			blank.setBorder(0);
			
			if("Yes".equals(IsControlFundsForBusinessAndEntimentFc))
			{
				maintable.addCell(blank);
				
				PdfPCell q3_yes_label =  PDFUtils.addPDFCellWithDefalutSize("If “Yes,” provide the number of such clients:");
				q3_yes_label.setBorder(0);
				maintable.addCell(q3_yes_label);
				
				PdfPCell q3_yes_labelValue;
				
				if(!NumberOfClientsFc.equals("")){
					q3_yes_labelValue =  PDFUtils.getCustomFontValue(PDFGenerator.numberFormat.format(new Double(NumberOfClientsFc)), 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);					
				}else{
					q3_yes_labelValue =  PDFUtils.getCustomFontValue(NumberOfClientsFc, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
				}
				q3_yes_labelValue.setBorder(0);
				q3_yes_labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
				maintable.addCell(q3_yes_labelValue);
			}

			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));			
						
			PdfPCell q4SNo = PDFUtils.addPDFCellWithDefalutSize("4.");
			q4SNo.setBorder(0);
			maintable.addCell(q4SNo);
			
			PdfPCell q4label =  PDFUtils.addPDFCellWithDefalutSize("Is a client countersignature required on all client checks issued by personnel of the Firm?");
			q4label.setBorder(0);
			maintable.addCell(q4label);
			
			PdfPCell q4labelValue =  PDFUtils.getYesNoImageObject(IsCountersignatureRequiredFc);
			q4labelValue.setBorder(0);
			q4labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q4labelValue);

			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));			
			
			PdfPCell q5SNo = PDFUtils.addPDFCellWithDefalutSize("5.");
			q5SNo.setBorder(0);
			maintable.addCell(q5SNo);
			
			PdfPCell q5label =  PDFUtils.addPDFCellWithDefalutSize("Are all client bank accounts reconciled by someone other than Firm personnel authorized to deposit or withdraw therefrom?");
			q5label.setBorder(0);
//			q5label.setColspan(2);
			maintable.addCell(q5label);
			
//			PdfPCell q5part1label =  PDFUtils.addPDFCellWithDefalutSize("to deposit or withdraw therefrom? ");
//			q5part1label.setBorder(0);
//			maintable.addCell(q5part1label);
//			
			PdfPCell q5labelValue =  PDFUtils.getYesNoImageObject(IsBankAccountReconciledFc);
			q5labelValue.setBorder(0);
			q5labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q5labelValue);

			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));			
			
			PdfPCell q6SNo = PDFUtils.addPDFCellWithDefalutSize("6.");
			q6SNo.setBorder(0);
			maintable.addCell(q6SNo);
			
			PdfPCell q6label =  PDFUtils.addPDFCellWithDefalutSize("Do any Firm personnel act as a business manager or CFO for business clients?");
			q6label.setBorder(0);
			maintable.addCell(q6label);
			
			PdfPCell q6labelValue =  PDFUtils.getYesNoImageObject(IsFirmPersonnelBusinessManagerFc);
			q6labelValue.setBorder(0);
			q6labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q6labelValue);
			
			if("Yes".equals(IsFirmPersonnelBusinessManagerFc))
			{
				maintable.addCell(blank);
				
				PdfPTable subtable  = new PdfPTable(2);
				subtable.setWidthPercentage(100);
				float[] widths={1.5f,1f};
				subtable.setWidths(widths);
				
				Phrase phrase1=new Phrase("If yes, provide the number of such clients: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
				Phrase phrase2=new Phrase(ProvideNumberOfClientFc,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
				Paragraph paragraph=new Paragraph();
				paragraph.add(phrase1);
				paragraph.add(phrase2);
				PdfPCell q6_yes_part1label = new PdfPCell(paragraph);
				q6_yes_part1label.setBorder(0);
				subtable.addCell(q6_yes_part1label);
				
//				PdfPCell q6_yes_part1labelValue =  PDFUtils.addPDFCellWithDefalutSize(ProvideNumberOfClientFc);
//				q6_yes_part1labelValue.setBorder(0);
//				subtable.addCell(q6_yes_part1labelValue);
				
				Phrase phrase3=new Phrase("Client Industry: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
				Phrase phrase4=new Phrase(ClientIndustryFc,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
				Paragraph paragraph1=new Paragraph();
				paragraph1.add(phrase3);
				paragraph1.add(phrase4);
				PdfPCell q6_yes_part2label = new PdfPCell(paragraph1);
				q6_yes_part2label.setBorder(0);
				subtable.addCell(q6_yes_part2label);
				
//				PdfPCell q6_yes_part2labelValue =  PDFUtils.addPDFCellWithDefalutSize(ClientIndustryFc);
//				q6_yes_part2labelValue.setBorder(0);
//				subtable.addCell(q6_yes_part2labelValue);
				
				PdfPCell cell =  new PdfPCell(subtable);
				cell.setBorder(0);
				cell.setColspan(2);
				maintable.addCell(cell);
			}

			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));			
			
			PdfPCell q7SNo = PDFUtils.addPDFCellWithDefalutSize("7.");
			q7SNo.setBorder(0);
			maintable.addCell(q7SNo);
			
			PdfPCell q7label =  PDFUtils.addPDFCellWithDefalutSize("Provide the number of Firm employees who control or disburse funds for the Firm or clients");
			q7label.setBorder(0);
			maintable.addCell(q7label);
			
			PdfPCell q7labelValue;
			
			if(!NumberOfFirmEmployeesFc.equals("")){
				q7labelValue =  PDFUtils.getCustomFontValue(PDFGenerator.numberFormat.format(new Double(NumberOfFirmEmployeesFc)), 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);				
			}else{
				q7labelValue =  PDFUtils.getCustomFontValue(NumberOfFirmEmployeesFc, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
			}
			q7labelValue.setBorder(0);
			q7labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q7labelValue);

			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));			
			
			PdfPCell q8SNo = PDFUtils.addPDFCellWithDefalutSize("8.");
			q8SNo.setBorder(0);
			maintable.addCell(q8SNo);
			
			PdfPCell q8label =  PDFUtils.addPDFCellWithDefalutSize("Does the Firm carry Employee Dishonesty Coverage?");
			q8label.setBorder(0);
			maintable.addCell(q8label);
			
			PdfPCell q8labelValue =  PDFUtils.getYesNoImageObject(IsEmployeeDishonestyCoverageFc);
			q8labelValue.setBorder(0);
			q8labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q8labelValue);
			
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
