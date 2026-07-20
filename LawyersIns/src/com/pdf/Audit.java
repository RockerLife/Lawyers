package com.pdf;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;

public class Audit {
	
	public void getDataForAudit(Context ctx) throws Exception
	{
		Object objAttestation =SqlResources.getSqlMapProcessor(ctx).findByKey("Attestation.findByKey", ctx);
		ctx.put(PDFConstants.ATTESTATION_FREEFORM_02, objAttestation);		
		
		Object listAttestationAuditWork =SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.attestationviewmomAuditwork", ctx);
		ctx.put(PDFConstants.ATTESTATION_LIST_03, listAttestationAuditWork);
	}
	
	public void makeLayoutForAudit(Context ctx) throws Exception
	{
		
	}
	
	public PdfPTable populateAuditData(Document document, Context ctx, PdfWriter writer) throws Exception
	{
		PdfPTable maintable  = new PdfPTable(3);
		maintable.setWidthPercentage(100);
		float[] width = { 2.5f, 51.5f, 21f };
		maintable.setWidths(width);
		maintable.setSpacingBefore(10f);
		maintable.setSpacingAfter(0f);

		PdfPCell cellHeader=PDFUtils.addHeader("AUDIT SUPPLEMENT (As required in question 14E - F.)");
		cellHeader.setColspan(3);
		maintable.addCell(cellHeader);
		
		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
		
		PdfPCell q1SNo = PDFUtils.addPDFCellWithDefalutSize("1.");
		q1SNo.setBorder(0);
		maintable.addCell(q1SNo);
		
		PdfPCell q1label =  PDFUtils.addPDFCellWithDefalutSize("Please indicate the following for all Audit work.");
		q1label.setBorder(0);
		q1label.setColspan(2);
		maintable.addCell(q1label);
		//document.add(maintable);
		
		//PDFUtils.addSpace(document);
		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
		
		PdfPTable pdfPTableQ1=populateAttestationList(document, ctx);
		pdfPTableQ1.setWidthPercentage(80);
		PdfPCell cellAttestation=new PdfPCell(pdfPTableQ1);
		cellAttestation.setBorder(0);
		cellAttestation.setPaddingLeft(60f);
		cellAttestation.setPaddingRight(60f);
		cellAttestation.setColspan(3);
		
		maintable.addCell(cellAttestation);
		
//		PdfPCell blank = PDFUtils.addPDFCellWithDefalutSize(" ");
//		blank.setBorder(0);
//		
//		maintable.addCell(blank);
		
//		PdfPCell cell=new PdfPCell(pdfPTableQ1);
//		cell.setBorder(0);
//		//cell.setColspan(2);
//		cell.setPaddingLeft(40f);
//		cell.setPaddingRight(40f);
		//document.add(pdfPTableQ1);
		
		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
		
		Object objAttestation = ctx.get(PDFConstants.ATTESTATION_FREEFORM_02);
		Map attestationMap = null;
		if(objAttestation != null)
			attestationMap = (Map)objAttestation;		
		
//		if(attestationMap != null )
//		{	
			String FirmRenderedAuditAtt = PDFUtils.getValueFromMap(attestationMap, "FirmRenderedAuditAtt", false);
			String IsFirmHaveAWrittenPolicyAtt = PDFUtils.getValueFromMap(attestationMap, "IsFirmHaveAWrittenPolicyAtt", true);
			String IsSecondPartnerReviewAtt = PDFUtils.getValueFromMap(attestationMap, "IsSecondPartnerReviewAtt", true);
			String IsAuditEngagementsAtt = PDFUtils.getValueFromMap(attestationMap, "IsAuditEngagementsAtt", true);
			String IsAualityReviewAtt = PDFUtils.getValueFromMap(attestationMap, "IsAualityReviewAtt", true);
			String IsLastPeerReviewAtt = PDFUtils.getValueFromMap(attestationMap, "IsLastPeerReviewAtt", true);
			
			PdfPCell q2SNo = PDFUtils.addPDFCellWithDefalutSize("2.");
			q2SNo.setBorder(0);
			maintable.addCell(q2SNo);
			
			PdfPCell q2label =  PDFUtils.addPDFCellWithDefalutSize("Within the past 5 years, how many clients for whom your Firm rendered audit or attestation services subsequently, declared or filed bankruptcy, defaulted on a debt obligation or became insolvent?");
			q2label.setBorder(0);
//			q2label.setColspan(2);
			maintable.addCell(q2label);
			
//			PdfPCell q2part1label =  PDFUtils.addPDFCellWithDefalutSize("subsequently, declared or filed bankruptcy, defaulted on a debt obligation or became insolvent?");
//			q2part1label.setBorder(0);
//			maintable.addCell(q2part1label);
//			
			PdfPCell q2labelValue;
			
			if(!FirmRenderedAuditAtt.equals("")){
				q2labelValue =  PDFUtils.getCustomFontValue(PDFGenerator.numberFormat.format(new Double(FirmRenderedAuditAtt)), 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);				
			}else{
				q2labelValue =  PDFUtils.getCustomFontValue(FirmRenderedAuditAtt, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
			}
			
			q2labelValue.setBorder(0);
			q2labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q2labelValue);
			
			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));

			PdfPCell q3SNo = PDFUtils.addPDFCellWithDefalutSize("3.");
			q3SNo.setBorder(0);
			maintable.addCell(q3SNo);
			
			PdfPCell q3label =  PDFUtils.addPDFCellWithDefalutSize("Does your Firm have a written policy on audit-related CPE training, including required courses and CPE hours per year specific to audit services?");
			q3label.setBorder(0);
//			q3label.setColspan(2);
			maintable.addCell(q3label);
			
//			PdfPCell q3part1label =  PDFUtils.addPDFCellWithDefalutSize("courses and CPE hours per year specific to audit services?");
//			q3part1label.setBorder(0);
//			maintable.addCell(q3part1label);
//			
			PdfPCell q3labelValue =  PDFUtils.getYesNoImageObject(IsFirmHaveAWrittenPolicyAtt);
			q3labelValue.setBorder(0);
			q3labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q3labelValue);
			
			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));

			PdfPCell q4SNo = PDFUtils.addPDFCellWithDefalutSize("4.");
			q4SNo.setBorder(0);
			maintable.addCell(q4SNo);
			
			PdfPCell q4label =  PDFUtils.addPDFCellWithDefalutSize("Does a second partner review all audit workpapers and the audit report prior to signing and releasing of such audit report?");
			q4label.setBorder(0);
//			q4label.setColspan(2);
			maintable.addCell(q4label);
			
//			PdfPCell q4part1label =  PDFUtils.addPDFCellWithDefalutSize("and releasing of such audit report?");
//			q4part1label.setBorder(0);
//			maintable.addCell(q4part1label);
			
			PdfPCell q4labelValue =  PDFUtils.getYesNoImageObject(IsSecondPartnerReviewAtt);
			q4labelValue.setBorder(0);
			q4labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q4labelValue);

			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));

			PdfPCell q5SNo = PDFUtils.addPDFCellWithDefalutSize("5.");
			q5SNo.setBorder(0);
			maintable.addCell(q5SNo);
			
			PdfPCell q5label =  PDFUtils.addPDFCellWithDefalutSize("Does a second partner or committee approve and sign off on the acceptance of new audit engagements? ");
			q5label.setBorder(0);
			maintable.addCell(q5label);
			
			PdfPCell q5labelValue =  PDFUtils.getYesNoImageObject(IsAuditEngagementsAtt);
			q5labelValue.setBorder(0);
			q5labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q5labelValue);

			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q6SNo = PDFUtils.addPDFCellWithDefalutSize("6.");
			q6SNo.setBorder(0);
			maintable.addCell(q6SNo);
			
			PdfPCell q6label =  PDFUtils.addPDFCellWithDefalutSize("Within the past 3 years, has your Firm undergone a peer or quality review offered by the AICPA or any state CPA Society?");
			q6label.setBorder(0);
//			q6label.setColspan(2);
			maintable.addCell(q6label);
			
//			PdfPCell q6part1label =  PDFUtils.addPDFCellWithDefalutSize("or any state CPA Society?");
//			q6part1label.setBorder(0);
//			maintable.addCell(q6part1label);
			
			PdfPCell q6labelValue =  PDFUtils.getYesNoImageObject(IsAualityReviewAtt);
			q6labelValue.setBorder(0);
			q6labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q6labelValue);

			maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q7SNo = PDFUtils.addPDFCellWithDefalutSize("7.");
			q7SNo.setBorder(0);
			maintable.addCell(q7SNo);
			
			PdfPCell q7label =  PDFUtils.addPDFCellWithDefalutSize("Was the Firm’s last peer review modified, qualified, or adverse?");
			q7label.setBorder(0);
//			q7label.setColspan(2);
			maintable.addCell(q7label);
			
			PdfPCell q7labelValue =  PDFUtils.getYesNoImageObject(IsLastPeerReviewAtt);
			q7labelValue.setBorder(0);
			q7labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			maintable.addCell(q7labelValue);
			
			
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
	
	
	public PdfPTable populateAttestationList(Document document, Context ctx) throws Exception
	{
		Object obj = ctx.get(PDFConstants.ATTESTATION_LIST_03);
		List list = null;
		if(obj != null)
			list = (List) obj;
		
		PdfPTable headtable = null;
		if(list != null)
		{
			headtable = new PdfPTable(4);
			headtable.setWidthPercentage(100);
			float[] width1={2f,1f,1.5f,1.5f};
			headtable.setWidths(width1);
			
			headtable.addCell(PDFUtils.addPDFCelHeader("Client Industry"));
			headtable.addCell(PDFUtils.addPDFCelHeader("# of Audit Clients"));
			headtable.addCell(PDFUtils.addPDFCelHeader("# of Clients with Total Assets over $5,000,000"));
			headtable.addCell(PDFUtils.addPDFCelHeader("# of Clients with Net Loss for the past Fiscal Year"));
			//document.add(headtable);
			
			for(int i=0; i< list.size(); i++)
			{
				//PdfPTable rowtable = new PdfPTable(8);
				//rowtable.setWidthPercentage(100);
				Map map = (Map)list.get(i);
				String ClientIndustrySrNo = PDFUtils.getValueFromMap(map, "ClientIndustrySrNo", false);
				String ClientIndustryDesc = (PDFUtils.getValueFromMap(map, "ClientIndustryDesc", false));
				String AuditClients = PDFUtils.getValueFromMap(map, "AuditClients", false);
				String ClientsAssetsOver = PDFUtils.getValueFromMap(map, "ClientsAssetsOver", false);
				String NetLossForLastFYE = PDFUtils.getValueFromMap(map, "NetLossForLastFYE", false);
				
				PdfPCell cell =  PDFUtils.getCustomFontValue(ClientIndustrySrNo + " " + ClientIndustryDesc, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				headtable.addCell(cell);
				if(!AuditClients.equals("")){
					cell =  PDFUtils.getCustomFontValue(PDFGenerator.numberFormat.format(new Double(AuditClients)), 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);					
				}else{
					cell =  PDFUtils.getCustomFontValue(AuditClients, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				}				
				headtable.addCell(cell);
				if(!ClientsAssetsOver.equals("")){
					cell =  PDFUtils.getCustomFontValue(PDFGenerator.numberFormat.format(new Double(ClientsAssetsOver)), 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);					
				}else{
					cell =  PDFUtils.getCustomFontValue(ClientsAssetsOver, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				}
				headtable.addCell(cell);
				if(!NetLossForLastFYE.equals("")){
					cell =  PDFUtils.getCustomFontValue(PDFGenerator.numberFormat.format(new Double(NetLossForLastFYE)), 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);					
				}else{
					cell =  PDFUtils.getCustomFontValue(NetLossForLastFYE, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				}
				headtable.addCell(cell);
				
				//document.add(rowtable);
				
			}
			PdfPCell cell=PDFUtils.getCustomFontValue("*__________________________________________________________________________________", 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
			cell.setColspan(4);
			headtable.addCell(cell);
		}
		return headtable;
	}

}
