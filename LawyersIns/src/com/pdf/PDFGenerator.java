package com.pdf;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.util.Locale;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.util.Context;

public class PDFGenerator {

	public static final URL ckN = PDFGenerator.class.getClassLoader()
			.getResource("com/image/check-btn3.jpg");
	public static final URL ckY = PDFGenerator.class.getClassLoader()
			.getResource("com/image/check-btn.jpg");
	public static final URL YN = PDFGenerator.class.getClassLoader()
			.getResource("com/image/check-btn2.jpg");
	public static final URL ck = PDFGenerator.class.getClassLoader()
			.getResource("com/image/check-btn6.jpg");
	public static final URL uck = PDFGenerator.class.getClassLoader()
			.getResource("com/image/check-btn4.jpg");
	public static final URL logo = PDFGenerator.class.getClassLoader()
			.getResource("com/image/logo.jpg");

	public static BaseFont tahomabd;
	public static BaseFont tahoma;
	public static NumberFormat formatter=NumberFormat.getCurrencyInstance(Locale.US);
	public static NumberFormat numberFormat=NumberFormat.getNumberInstance(Locale.US);
	
	public Document execute(Document document, ByteArrayOutputStream baos,
			Context ctx, PdfWriter writer) throws Exception {
		
		tahomabd = BaseFont.createFont(URLDecoder.decode(PDFGenerator.class.getClassLoader()
				.getResource("com/image/tahomabd.ttf").getPath(),"UTF-8"), BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		tahoma = BaseFont.createFont(URLDecoder.decode(PDFGenerator.class.getClassLoader()
				.getResource("com/image/tahoma.ttf").getPath(),"UTF-8"), BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		formatter.setMaximumFractionDigits(0);
		
		document.open();
		populateHeader(document, ctx);

		new BasicInfo().getDataForBasicInfo(ctx);
		new BasicInfo().populateBasicInfoData(document, ctx, writer);

		new LicensePage().addLicenseData(document, ctx);
		
		if((ctx.get("AverageRevenue") != null && Double.parseDouble(ctx.get("AverageRevenue").toString()) > 500000)
				|| (ctx.get("AOP_Percentage_19") != null && !"".equals(ctx.get("AOP_Percentage_19")) && Integer.parseInt(ctx.get("AOP_Percentage_19").toString()) != 0 )
				|| ((ctx.get("AOP_Percentage_5") != null && !"".equals(ctx.get("AOP_Percentage_5")) && Integer.parseInt(ctx.get("AOP_Percentage_5").toString()) != 0) || (ctx.get("AOP_Percentage_6") != null && !"".equals(ctx.get("AOP_Percentage_6")) && Integer.parseInt(ctx.get("AOP_Percentage_6").toString()) != 0))
				|| (ctx.get("AOP_Percentage_14") != null && !"".equals(ctx.get("AOP_Percentage_14")) && Integer.parseInt(ctx.get("AOP_Percentage_14").toString()) != 0  )
				|| (ctx.get("AOP_Percentage_18") != null && !"".equals(ctx.get("AOP_Percentage_18")) && Integer.parseInt(ctx.get("AOP_Percentage_18").toString()) != 0  )
				|| ((ctx.get("IsAwarenessOfAnyProfLiability") != null || ctx.get("IsAwarenessOfAnyProfLiabilitySuitAgainst") != null) && (((ctx.get("IsAwarenessOfAnyProfLiability") != null && !"".equals(ctx.get("IsAwarenessOfAnyProfLiability").toString())) && "Yes".equals(ctx.get("IsAwarenessOfAnyProfLiability").toString())) || ((ctx.get("IsAwarenessOfAnyProfLiabilitySuitAgainst") != null && !"".equals(ctx.get("IsAwarenessOfAnyProfLiabilitySuitAgainst").toString())) && "Yes".equals(ctx.get("IsAwarenessOfAnyProfLiabilitySuitAgainst").toString()))))
				|| ((ctx.get("IsFirmRenderingServices") != null && !"".equals(ctx.get("IsFirmRenderingServices").toString())) && "Yes".equals(ctx.get("IsFirmRenderingServices").toString()))
				){
			document.newPage();
		}
		if(ctx.get("AverageRevenue") != null && Double.parseDouble(ctx.get("AverageRevenue").toString()) > 500000){
			
			new PracticeMangement().getDataForPracticeMangement(ctx);
			new PracticeMangement().populatePracticeMangementData(document, ctx, writer);
		}
		
		if(ctx.get("AOP_Percentage_19") != null && !"".equals(ctx.get("AOP_Percentage_19")) 
				&& Integer.parseInt(ctx.get("AOP_Percentage_19").toString()) != 0  ){
			new Investment().getDataForInvestment(ctx);
			new Investment().populateInvestmentData(document, ctx, writer);			
		}
		
		if((ctx.get("AOP_Percentage_5") != null && !"".equals(ctx.get("AOP_Percentage_5")) 
				&& Integer.parseInt(ctx.get("AOP_Percentage_5").toString()) != 0) ||
				(ctx.get("AOP_Percentage_6") != null && !"".equals(ctx.get("AOP_Percentage_6")) 
						&& Integer.parseInt(ctx.get("AOP_Percentage_6").toString()) != 0)){
			new Audit().getDataForAudit(ctx);
			new Audit().populateAuditData(document, ctx, writer);
		}
		
		if(ctx.get("AOP_Percentage_14") != null && !"".equals(ctx.get("AOP_Percentage_14")) 
				&& Integer.parseInt(ctx.get("AOP_Percentage_14").toString()) != 0  ){
			new ComputerService().getDataForComputerService(ctx);
			new ComputerService().populateComputerServiceData(document, ctx, writer);
		}
		
		if(ctx.get("AOP_Percentage_18") != null && !"".equals(ctx.get("AOP_Percentage_18")) 
				&& Integer.parseInt(ctx.get("AOP_Percentage_18").toString()) != 0  ){
			new BusinessManagment().getDataForBusinessManagment(ctx);
			new BusinessManagment().populateBusinessManagmentData(document, ctx, writer);
		}
		
		if(ctx.get("IsAwarenessOfAnyProfLiability") != null || ctx.get("IsAwarenessOfAnyProfLiabilitySuitAgainst") != null)
		{
			if((ctx.get("IsAwarenessOfAnyProfLiability") != null && !"".equals(ctx.get("IsAwarenessOfAnyProfLiability").toString()))
					&& "Yes".equals(ctx.get("IsAwarenessOfAnyProfLiability").toString())) {
				new BasicInfo().populateClaimSupplementList(document, ctx,writer);
			}
			else if((ctx.get("IsAwarenessOfAnyProfLiabilitySuitAgainst") != null && !"".equals(ctx.get("IsAwarenessOfAnyProfLiabilitySuitAgainst").toString()))
					&& "Yes".equals(ctx.get("IsAwarenessOfAnyProfLiabilitySuitAgainst").toString())) {
				new BasicInfo().populateClaimSupplementList(document, ctx, writer);
			}
		}
		
		if((ctx.get("IsFirmRenderingServices") != null && !"".equals(ctx.get("IsFirmRenderingServices").toString()))
				&& "Yes".equals(ctx.get("IsFirmRenderingServices").toString())) {
			new General().populateSeperateEntity(document, ctx, writer);
		}

		return document;
	}

	private void populateHeader(Document document, Context ctx)
			throws Exception {

		String pdf_header = null;

		Paragraph addess1 = new Paragraph(
				"UNITED STATES FIRE INSURANCE COMPANY", new Font(
						tahomabd, 10f, Font.NORMAL));
		addess1.setAlignment(Element.ALIGN_RIGHT);
		document.add(addess1);

		Paragraph addess2 = new Paragraph(
				"305 MADISON AVENUE, MORRISTOWN, NJ 07962", new Font(
						tahomabd, 10f, Font.NORMAL));
		addess2.setAlignment(Element.ALIGN_RIGHT);
		document.add(addess2);
		PDFUtils.addSpace(document);

		if (ctx.get("type") != null
				&& "application".equals(ctx.get("type").toString()))
			pdf_header = "ACCOUNTANT PROFESSIONAL LIABILITY APPLICATION";
		else
			pdf_header = "ACCOUNTANT PROFESSIONAL LIABILITY APPLICATION";

		Paragraph p1 = new Paragraph(pdf_header, new Font(tahomabd,
				14f, Font.NORMAL));
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);
		PDFUtils.addSpace(document);

		Chunk space = new Chunk(' ');
		Phrase phrase1 = new Phrase(
				"NOTICE:  COVERAGE FOR WHICH THIS APPLICATION IS MADE IS WRITTEN ON A CLAIMS MADE AND REPORTED BASIS MEANING, EXCEPT AS "
						+ "OTHERWISE PROVIDED, COVERAGE APPLIES ONLY TO",
				new Font(tahoma, 8, Font.NORMAL));
		Phrase phrase2 = new Phrase(new Chunk("CLAIMS", new Font(
				tahoma, 8, Font.BOLDITALIC)));
		Phrase phrase3 = new Phrase("FIRST MADE AGAINST THE", new Font(
				tahoma, 8, Font.NORMAL));
		Phrase phrase3a = new Phrase(new Chunk("INSURED", new Font(
				tahoma, 8, Font.BOLDITALIC)));
		Phrase phrase3b=new Phrase("AND REPORTED TO THE",new Font(tahoma,8,Font.NORMAL));
		Phrase phrase4 = new Phrase(new Chunk("INSURER", new Font(
				tahoma, 8, Font.BOLDITALIC)));
		Phrase phrase5 = new Phrase("DURING THE", new Font(tahoma, 8,
				Font.NORMAL));
		Phrase phrase6 = new Phrase(new Chunk("POLICY PERIOD.\n\n", new Font(
				tahoma, 8, Font.BOLDITALIC)));
		Phrase phrase7 = new Phrase(
				"CAREFULLY READ THE ENTIRE POLICY FOR WHICH THIS APPLICATION IS MADE.  WORDS AND PHRASES WHICH ARE PRINTED IN",
				new Font(tahoma, 8, Font.NORMAL));
		Phrase phrase8 = new Phrase(new Chunk("BOLD ITALIC TYPEFACE", new Font(
				tahoma, 8, Font.BOLDITALIC)));
		Phrase phrase9 = new Phrase(
				"HAVE SPECIFIC MEANING AND ARE DEFINED IN SECTION IV OF THE POLICY.\n\n",
				new Font(tahoma, 8, Font.NORMAL));
		Phrase phrase10 = new Phrase(
				"THE APPLICATION, ITS ATTACHMENTS AND ALL PREVIOUS APPLICATIONS AND THEIR ATTACHMENTS SHALL SERVE AS THE BASIS OF THE POLICY, AND SHALL BECOME PART OF SUCH POLICY SHOULD A POLICY BE ISSUED, AS IF PHYSICALLY ATTACHED.  THE",
				new Font(tahoma, 8, Font.NORMAL));
		Phrase phrase11 = new Phrase(new Chunk("INSURER", new Font(
				tahoma, 8, Font.BOLDITALIC)));
		Phrase phrase12 = new Phrase(
				"RELIES UPON THE APPLICATION IN ISSUING THE POLICY.  COMPLETION OF THIS APPLICATION DOES NOT IN ANY WAY IMPLY SUCH COVERAGE UNDER THE POLICY.  COVERAGE IS AFFORDED ONLY IF AND TO THE EXTENT INDICATED BY THE TERMS AND CONDITIONS OF THE POLICY IF ISSUED.",
				new Font(tahoma, 8, Font.NORMAL));

		Phrase phrase13 = new Phrase(
				new Chunk(
						"\n\nPlease complete the application with respect to the Firm. Wherever the word “Firm” is used, it will be deemed to include the "
								+ "Firm affiliates.", new Font(
										Font.TIMES_ROMAN, 10, Font.BOLD)));
		Paragraph p2 = new Paragraph();
		p2.add(phrase1);
		p2.add(space);
		p2.add(phrase2);
		p2.add(space);
		p2.add(phrase3);
		p2.add(space);
		p2.add(phrase3a);
		p2.add(space);
		p2.add(phrase3b);
		p2.add(space);
		p2.add(phrase4);
		p2.add(space);
		p2.add(phrase5);
		p2.add(space);
		p2.add(phrase6);
		p2.add(phrase7);
		p2.add(space);
		p2.add(phrase8);
		p2.add(space);
		p2.add(phrase9);
		p2.add(phrase10);
		p2.add(space);
		p2.add(phrase11);
		p2.add(space);
		p2.add(phrase12);
		p2.add(phrase13);
		p2.setLeading(10f);
		document.add(p2);
	}

	private void populateFooterDetails(Document document) throws Exception {

		Paragraph p11 = new Paragraph("\n", new Font(Font.BOLD));
		p11.setAlignment(Element.ALIGN_LEFT);
		document.add(p11);

		PdfPTable table7 = new PdfPTable(1);
		PdfPCell cell7 = null;
		table7.setWidthPercentage(100);

		cell7 = new PdfPCell(
				new Paragraph(
						"\n Lawyers Insurance has many convenient payment options available.Your Lawyers Agent can help you select a payment  program that best fits your needs.Just contact your Agent for the details. \n\n\nPlease remember that this quotation is based upon information provided by you and uses the rates in effect on the date  shown above.If the information is incorrect or if the rates change ,then the quotation is inapplicable .The purpose of  the quote is merely to provide you with an estimate of the cost of your Lawyers insurance. \n\n This is not an offer of coverage.Actual coverage is not in effect until an application is signed by you and accepted by us.\n\n\n Thank you for the opportunity to provide this competitive quotation.   ",
						new Font(Font.TIMES_ROMAN, 8, Font.NORMAL)));
		cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
		table7.addCell(cell7);

		document.add(table7);

		Paragraph cell = new Paragraph(
				"Prepared By:                                                                                    ",
				new Font(Font.TIMES_ROMAN, 8, Font.NORMAL));
		cell.setAlignment(Element.ALIGN_LEFT);
		document.add(cell);

		// String propObj = DateUtils.convertDateToString((Date) new Date(),
		// Constants.DATE_PATTERN);
		// Paragraph p13 = new Paragraph("Date:"+propObj,new
		// Font(Font.TIMES_ROMAN,8,Font.NORMAL));
		// p13.setAlignment(Element.ALIGN_RIGHT);
		// document.add(p13);

	}

	// private PdfPTable populateCommentForPolicyInfo(Map policyInfo ) throws
	// Exception
	// {
	// PdfPTable table1 = new PdfPTable(1);
	// table1.setWidthPercentage(100);
	// PdfPCell cell1 = PDFUtils.addPDFCellWithDefalutSize("" );
	// cell1.setBorder(0);
	// PdfPCell cell = PDFUtils.addPDFCellWithDefalutSize("Description of
	// Operations:" );
	// cell.setColspan (8);
	// cell.setPadding ( 5.0f);
	// cell.setHorizontalAlignment (Element.ALIGN_LEFT);
	// cell.setBackgroundColor(Color.lightGray);
	// table1.addCell (cell);
	//		    
	// String
	// description=policyInfo.get(Constants.DESCRIPTION_OPERATIONS)!=null?policyInfo.get(Constants.DESCRIPTION_OPERATIONS).toString():"";
	// PdfPCell desoperations = PDFUtils.addPDFCellWithDefalutSize( description
	// );
	// table1.addCell(desoperations) ;
	// 		    
	// return table1;
	// }
	private void calculateSize() {
		footerBottomMarginFactor = 10 + 8;
		heightDisplacementFactor = 50;
		lineLength = 0;
		fontSize = 8 + 3;
		imagePosition = 47;

	}

	private float footerBottomMarginFactor;

	private float heightDisplacementFactor;

	private float lineLength;
	private float fontSize;

	private float imagePosition;

	private int[] widths = { 10 };
	private float footerHeight;
	private Image[] imgs;
	private float lineSpacing;

}
