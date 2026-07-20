package com.pdf;

import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;

public class Investment {
	
	public void getDataForInvestment(Context ctx) throws Exception
	{
		Object objFinancialPlanningAndIAServiceSupp =SqlResources.getSqlMapProcessor(ctx).findByKey("FinancialPlanningAndIAServiceSupp.findByKey", ctx);
		ctx.put(PDFConstants.INVEST_FINAN_PLANING_FREEFORM_01, objFinancialPlanningAndIAServiceSupp);
			
	}
	
	public void makeLayoutForInvestment(Context ctx) throws Exception
	{
		
	}
	
	public PdfPTable populateInvestmentData(Document document, Context ctx, PdfWriter writer) throws Exception
	{
		PdfPTable maintable  = new PdfPTable(3);
		maintable.setWidthPercentage(100);
		float[] width = { 2.5f, 51.5f, 21f };
		maintable.setWidths(width);
		maintable.setSpacingBefore(10f);
		maintable.setSpacingAfter(0f);

		PdfPCell cellHeader=PDFUtils.addHeader("INVESTMENT/FINANCIAL PLANNING SUPPLEMENT(As required in question 14. T )");	
		cellHeader.setColspan(3);
		
		maintable.addCell(cellHeader);
		
		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
		
		Object objFirm = ctx.get(PDFConstants.INVEST_FINAN_PLANING_FREEFORM_01);
		Map firmMap = null;
		if(objFirm != null)
			firmMap = (Map)objFirm;		
		
//		if(firmMap != null )
//		{	
			String IsFinancialPlansPreparing = PDFUtils.getValueFromMap(firmMap, "IsFinancialPlansPreparing", true);
			String IsDiscretionaryAssetMangt = PDFUtils.getValueFromMap(firmMap, "IsDiscretionaryAssetMangt", true);
			String IsNonDiscretionaryAssetMangt = PDFUtils.getValueFromMap(firmMap, "IsNonDiscretionaryAssetMangt", true);
			String IsSecuritiesSales = PDFUtils.getValueFromMap(firmMap, "IsSecuritiesSales", true);
			String IsContractualRelationShip = PDFUtils.getValueFromMap(firmMap, "IsContractualRelationShip", true);
			String IsOmissionsPolicy = PDFUtils.getValueFromMap(firmMap, "IsOmissionsPolicy", true);
			
			String IsAnyNonPublicInvestments = PDFUtils.getValueFromMap(firmMap, "IsAnyNonPublicInvestments", true);
			String IsAnyFirmAffiliateLicensed = PDFUtils.getValueFromMap(firmMap, "IsAnyFirmAffiliateLicensed", true);
			String IsAnyEmployeeBenefitPlan = PDFUtils.getValueFromMap(firmMap, "IsAnyEmployeeBenefitPlan", true);
			
			PdfPCell q1SNo = PDFUtils.addPDFCellWithDefalutSize("1.");
			q1SNo.setBorder(0);
			maintable.addCell(q1SNo);
			
			PdfPCell q1label =  PDFUtils.addPDFCellWithDefalutSize("Do the Investment and Financial Planning Services provided by your Firm include:");
			q1label.setBorder(0);
			q1label.setColspan(2);
			maintable.addCell(q1label);
			
			float[] width1 = { 2f, 52f, 21f };
			
			PdfPTable q1SubTable = new PdfPTable(3);
			q1SubTable.setWidthPercentage(100);
			q1SubTable.setWidths(width1);
			
			PdfPCell qa1SNo = PDFUtils.addPDFCellWithDefalutSize("a.");
			qa1SNo.setBorder(0);
			q1SubTable.addCell(qa1SNo);
			
			PdfPCell q1alabel =  PDFUtils.addPDFCellWithDefalutSize("Preparation of Financial Plans");
			q1alabel.setBorder(0);
			q1SubTable.addCell(q1alabel);
			
			PdfPCell q1alabelValue = PDFUtils.getYesNoImageObject(IsFinancialPlansPreparing);
			q1alabelValue.setBorder(0);
			q1alabelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			q1SubTable.addCell(q1alabelValue);
			
			PdfPCell qb1SNo = PDFUtils.addPDFCellWithDefalutSize("b.");
			qb1SNo.setBorder(0);
			q1SubTable.addCell(qb1SNo);
			
			PdfPCell q1blabel =  PDFUtils.addPDFCellWithDefalutSize("Discretionary Asset Management Services");
			q1blabel.setBorder(0);
			q1SubTable.addCell(q1blabel);
			
			PdfPCell q1blabelValue = PDFUtils.getYesNoImageObject(IsDiscretionaryAssetMangt);
			q1blabelValue.setBorder(0);
			q1blabelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			q1SubTable.addCell(q1blabelValue);
			
			PdfPCell qc1SNo = PDFUtils.addPDFCellWithDefalutSize("c.");
			qc1SNo.setBorder(0);
			q1SubTable.addCell(qc1SNo);
			
			PdfPCell q1clabel =  PDFUtils.addPDFCellWithDefalutSize("Non-Discretionary Asset Management Services");
			q1clabel.setBorder(0);
			q1SubTable.addCell(q1clabel);
			
			PdfPCell q1clabelValue = PDFUtils.getYesNoImageObject(IsNonDiscretionaryAssetMangt);
			q1clabelValue.setBorder(0);
			q1clabelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			q1SubTable.addCell(q1clabelValue);
			
			PdfPCell qd1SNo = PDFUtils.addPDFCellWithDefalutSize("d.");
			qd1SNo.setBorder(0);
			q1SubTable.addCell(qd1SNo);
			
			PdfPCell q1dlabel =  PDFUtils.addPDFCellWithDefalutSize("Securities Sales");
			q1dlabel.setBorder(0);
			q1SubTable.addCell(q1dlabel);
			
			PdfPCell q1dlabelValue = PDFUtils.getYesNoImageObject(IsSecuritiesSales);
			q1dlabelValue.setBorder(0);
			q1dlabelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			q1SubTable.addCell(q1dlabelValue);			
			
			PdfPCell pdfPCell1 = new PdfPCell(q1SubTable);
			pdfPCell1.setBorder(0);
			pdfPCell1.setPaddingLeft(20);
			pdfPCell1.setColspan(3);
			maintable.addCell(pdfPCell1);
			
			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q2SNo = PDFUtils.addPDFCellWithDefalutSize("2.");
			q2SNo.setBorder(0);
			maintable.addCell(q2SNo);
			
			PdfPCell q2label =  PDFUtils.addPDFCellWithDefalutSize("Does your Firm have a contractual relationship with a securities broker or dealer?");
			q2label.setBorder(0);	
			maintable.addCell(q2label);
			
			PdfPCell q2labelValue = PDFUtils.getYesNoImageObject(IsContractualRelationShip);
			q2labelValue.setBorder(0);
			q2labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);			
			maintable.addCell(q2labelValue);
			
			PdfPCell blank = PDFUtils.addPDFCellWithDefalutSize(" ");
			blank.setBorder(0);
			
			if("Yes".equals(IsContractualRelationShip))
			{
				maintable.addCell(blank);
				
				PdfPCell q2_yes_label =  PDFUtils.addPDFCellWithDefalutSize("If 'Yes', are you or individual registered representatives of your Firm insured under a broker or dealer’s errors and omissions policy?");
				q2_yes_label.setBorder(0);
			//	q2_yes_label.setColspan(2);
				maintable.addCell(q2_yes_label);
				
//				PdfPCell q2_yes_part1label =  PDFUtils.addPDFCellWithDefalutSize("errors and omissions policy? ");
//				q2_yes_part1label.setBorder(0);
//				maintable.addCell(q2_yes_part1label);
				
				PdfPCell q2_yes_Value = PDFUtils.getYesNoImageObject(IsOmissionsPolicy);
				q2_yes_Value.setBorder(0);
				q2_yes_Value.setHorizontalAlignment(Element.ALIGN_RIGHT);
				maintable.addCell(q2_yes_Value);
			}
			
			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q3SNo = PDFUtils.addPDFCellWithDefalutSize("3.");
			q3SNo.setBorder(0);
			maintable.addCell(q3SNo);
			
			PdfPCell q3label =  PDFUtils.addPDFCellWithDefalutSize("Within the past 5 years, have any personnel recommended any non-public investments to clients in which the Firm or their personnel have an ownership interest?");
			q3label.setBorder(0);
//			q3label.setColspan(2);
			maintable.addCell(q3label);
			
//			PdfPCell q3part1label =  PDFUtils.addPDFCellWithDefalutSize("in which the Firm or their personnel have an ownership interest?");
//			q3part1label.setBorder(0);
//			maintable.addCell(q3part1label);
			
			PdfPCell q3labelValue = PDFUtils.getYesNoImageObject(IsAnyNonPublicInvestments);
			q3labelValue.setBorder(0);
			q3labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q3labelValue);
			
			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q4SNo = PDFUtils.addPDFCellWithDefalutSize("4.");
			q4SNo.setBorder(0);
			maintable.addCell(q4SNo);
			
			PdfPCell q4label =  PDFUtils.addPDFCellWithDefalutSize("Is any person in your Firm licensed as life/health/accident/disability insurance agent or broker?");
			q4label.setBorder(0);
			maintable.addCell(q4label);
			
			PdfPCell q4labelValue = PDFUtils.getYesNoImageObject(IsAnyFirmAffiliateLicensed);
			q4labelValue.setBorder(0);
			q4labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q4labelValue);			
			
			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q5SNo = PDFUtils.addPDFCellWithDefalutSize("5.");
			q5SNo.setBorder(0);
			maintable.addCell(q5SNo);
			
			PdfPCell q5label =  PDFUtils.addPDFCellWithDefalutSize("Does your Firm or its personnel have discretionary authority to invest for any employee benefit plan?");
			q5label.setBorder(0);	
			maintable.addCell(q5label);
			
			PdfPCell q5labelValue = PDFUtils.getYesNoImageObject(IsAnyEmployeeBenefitPlan);
			q5labelValue.setBorder(0);
			q5labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q5labelValue);
			
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
