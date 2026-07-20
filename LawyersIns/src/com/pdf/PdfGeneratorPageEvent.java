package com.pdf;

import com.util.InetLogger;

import com.lowagie.text.Document;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class PdfGeneratorPageEvent extends PdfPageEventHelper {
	private static final InetLogger logger = InetLogger.getInetLogger(PdfGeneratorPageEvent.class);
	protected PdfTemplate total;
	protected BaseFont helv;
	protected Image img;

	@Override
	public void onOpenDocument(PdfWriter writer, Document document) {
		total = writer.getDirectContent().createTemplate(100, 100);
		total.setBoundingBox(new Rectangle(-20, -20, 100, 100));
		try {
			helv = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.WINANSI,
					BaseFont.NOT_EMBEDDED);
			img = Image.getInstance(PDFGenerator.logo);

		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}

	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent();
		cb.saveState();
		String textAPL="APL-101-2009";
		String text = "Page " + writer.getPageNumber() + " of";
		float textBasefooter = document.bottom() - 10;
		cb.beginText();
		cb.setFontAndSize(helv, 8);
		
		cb.setTextMatrix(document.left(),textBasefooter);
		cb.showText(textAPL);
		cb.endText();
		cb.beginText();
		cb.setFontAndSize(helv, 8);
		
		cb.setTextMatrix((document.right() - document.left()) / 2,
				textBasefooter);
		cb.showText(text);
		cb.endText();
		cb.addTemplate(total, (document.right() - document.left() + 70) / 2,
				textBasefooter);

		cb.restoreState();
	}

	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent();
		cb.saveState();
		try{
		cb.addImage(img,
				img.width()/2 + 50, 0, 0, img.height()/2 + 6, 395, 750);
		}catch(Exception e){
			logger.error("Unexpected error", e);
		}
		cb.restoreState();
	}

	@Override
	public void onCloseDocument(PdfWriter writer, Document document) {
		total.beginText();
		total.setFontAndSize(helv, 8);
		total.setTextMatrix(0, 0);
		total.showText(String.valueOf(writer.getPageNumber() - 1));
		total.endText();
	}
}
