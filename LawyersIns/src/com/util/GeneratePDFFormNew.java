package com.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDGamma;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.pdmodel.interactive.action.type.PDActionGoTo;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageXYZDestination;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckbox;
import org.apache.pdfbox.pdmodel.interactive.form.PDRadioCollection;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextbox;

import com.ormapping.ibatis.SqlResources;

public class GeneratePDFFormNew {
	private static InetLogger logger = InetLogger.getInetLogger(GeneratePDFFormNew.class);
	private static final String WIDGET_SUBTYPE = "Widget";
	private static final String BUTTON_FIELD_TYPE = "Btn";
	private static final int ANNOTATION_PRINT_FLAG = 4;
	private static final int RADIO_BUTTON_FLAG = 1 << 15;
	private static final int TEXT_FIELD_MULTILINE_FLAG = 1 << 12;
	private static final int ATTORNEY_ROSTER_ROW_COUNT = 25;
	private static final String DEFAULT_OUTPUT_PDF = "D:/LawyersApplicationpdf_NewBusiness.pdf";
	private static final String LOGO_IMAGE_NAME = "ISMIE_logo.png";
	private static final String LOGO_IMAGE_PATH = "web/image/" + LOGO_IMAGE_NAME;
	private static final String FALLBACK_LOGO_IMAGE_PATH = "D:/ISMIELogo.jpg";
	private static final float PAGE_MARGIN = 20f;
	private static final float BODY_FONT_SIZE = 10f;
	private static final float PLACEHOLDER_FONT_SIZE = 10f;
	private static final float RADIO_LABEL_FONT_SIZE = 10f;
	private static final float SUPPLEMENT_FONT_SIZE = 10f;
	private static final float FORM_FIELD_FONT_SIZE = 10f;
	private static final int MIN_TEXT_FIELD_HEIGHT = 13;
	private static final float PAGE_FIVE_FONT_SIZE = 10f;
	private static final float PAGE_FIVE_LINE_HEIGHT = 12f;
	private static final float FRAUD_NOTICE_FONT_SIZE = 10f;
	private static final float FRAUD_NOTICE_LINE_HEIGHT = 12f;
	private static final float RADIO_BUTTON_SIZE = 12f;
	private static final float CHECKBOX_SIZE = 15f;
	private static final float RADIO_LABEL_GAP = 5f;
	private static final float RADIO_OPTION_GAP = 18f;
	private static final float RADIO_ROW_GAP = 18f;
	private static final float RADIO_RIGHT_MARGIN = 8f;
	private static final String FORM_FONT_RESOURCE_NAME = "Helv";
	private static final String LIMITS_PLACEHOLDER = "per claim / aggregate";
	private static final String FIRST_DOLLAR_DEFENSE_TEXT = "Deductible does not apply to defense expenses (first dollar defense)";

	private static PDFont regularFont = PDType1Font.HELVETICA;
	private static PDFont boldFont = PDType1Font.HELVETICA_BOLD;
	private static PDFont italicFont = PDType1Font.HELVETICA_OBLIQUE;
	private static final PDFont fraudRegularFont = PDType1Font.HELVETICA;
	private static final PDFont fraudBoldFont = PDType1Font.HELVETICA_BOLD;

	private interface PdfDrawAction {
		void draw() throws Exception;
	}

	private static class StyledTextPart {
		private final String text;
		private final boolean bold;

		private StyledTextPart(String text, boolean bold) {
			this.text = text;
			this.bold = bold;
		}
	}

	public static void main(String[] args) {
		try (PDDocument document = new PDDocument()) {
			Context ctx = new Context();
			String projectName = "LawyersIns";
			String resourcePath = SystemProperties.getInstance().getString("xml.basedir") + projectName
					+ "//ibatis//maps//SqlMapConfig.xml";
			SqlResources.load(projectName, resourcePath);
			ctx.setProject(projectName);
			ctx.put("CompanyKey", "3");
			ctx.put("litigationNewImplDate", "12-06-2017");
			ctx.put("TransactionSequence", "271705");
			ctx.put("MarketableProductKey", "2");
			ctx.put("AddressKey", "183323");
			ctx.put("RenewalSupplementNewImplDate", "01-01-2021");
			ctx.put("VersionSequence", "157950");
			ctx.put("CoverageSequence", "270033");
			ctx.put("CoverageKey", "3");
			ctx.put("StatusDesc", "UnderReview");
			ctx.put("PolicyKey", "183061");
			ctx.put("VersionKey", "166743");
			ctx.put("StatusKey", "2");
			ctx.put("inet_project_id", "LawyersIns");
			ctx.put("AccountID", "193576");
			ctx.put("IsFullQuote", "Y");
			ctx.put("QuoteNumber", "QN-0183061");
			ctx.put("isCannibSuppFlow", "Y");
			ctx.put("AccountKey", "193576");
			ctx.put("VersionSequence", "157950");

			new DocumentGenerationBO().fetchDataForApplicationPDF(ctx);
			loadApplicationFonts();
			PDAcroForm acroForm = createAcroForm(document);

			drawApplicationPages(ctx, acroForm, document);

			File outputFile = new File(DEFAULT_OUTPUT_PDF);
			File outputDir = outputFile.getParentFile();
			if (outputDir != null && !outputDir.exists()) {
				outputDir.mkdirs();
			}

			savePdfDocument(document, outputFile.getAbsolutePath());
			logger.debug("PDF Form created successfully at " + outputFile.getAbsolutePath());

		} catch (Exception e) {
			logPdfException("Error creating populated PDF. Saving an empty PDF instead", e);
			try {
				saveEmptyPdf(DEFAULT_OUTPUT_PDF);
			} catch (Exception saveException) {
				logPdfException("Unable to save empty fallback PDF", saveException);
			}
		}
	}

	private static String blankIfNull(Object value) {
		return value == null || HtmlConstants.EMPTY.equals(value) ? HtmlConstants.EMPTY : value.toString();
	}

	private static String getBlankIfNull(Map map, String key) {
		return map == null ? HtmlConstants.EMPTY : blankIfNull(map.get(key));
	}

	private static String getBlankIfDefaultDate(Map map, String key) {
		String value = getBlankIfNull(map, key).trim();
		String normalized = value.toLowerCase(Locale.US);
		if (normalized.startsWith("01/01/1900") || normalized.startsWith("1/1/1900")
				|| normalized.startsWith("1900-01-01") || normalized.startsWith("01-01-1900")) {
			return HtmlConstants.EMPTY;
		}
		return value;
	}

	private static String getFirstNonBlankFromMap(Map map, String[] keys) {
		if (map == null) {
			return HtmlConstants.EMPTY;
		}
		for (int i = 0; i < keys.length; i++) {
			String value = getBlankIfNull(map, keys[i]);
			if (value.trim().length() > 0) {
				return value;
			}
		}
		return HtmlConstants.EMPTY;
	}

	private static String getAopPercentage(Map map) {
		return getFirstNonBlankFromMap(map, new String[] { "percentage", "PercentageValue", "Percentage" });
	}

	private static String normalizeAopLabel(String value) {
		if (value == null) {
			return HtmlConstants.EMPTY;
		}
		String normalized = value.toLowerCase(Locale.US).replace('\u2013', '-').replace('\u2014', '-')
				.replace("and/or", "and or").replace("please describe", "").replace("desc", "");
		normalized = normalized.replaceAll("\\([^)]*\\)", "");
		normalized = normalized.replaceAll("[^a-z0-9]+", "");
		return normalized;
	}

	private static Map getAopMapByLabel(List aoplist1, List aoplist2, String label) {
		String target = normalizeAopLabel(label);
		Map match = getAopMapByLabel(aoplist1, target);
		if (match != null) {
			return match;
		}
		match = getAopMapByLabel(aoplist2, target);
		return match == null ? Collections.EMPTY_MAP : match;
	}

	private static Map getAopMapByLabel(List aopList, String normalizedLabel) {
		if (aopList == null) {
			return null;
		}
		for (int i = 0; i < aopList.size(); i++) {
			Object item = aopList.get(i);
			if (!(item instanceof Map)) {
				continue;
			}
			Map map = (Map) item;
			String description = getFirstNonBlankFromMap(map, new String[] { "AOPDescNew", "AOPDesc", "aopDesc" });
			if (normalizedLabel.equals(normalizeAopLabel(description))) {
				return map;
			}
		}
		return null;
	}

	private static String getAopTotal(Context ctx, List aoplist1, List aoplist2) {
		String total = blankIfNull(ctx.get("aop_total"));
		if (total.trim().length() > 0) {
			return total;
		}
		return Integer.toString(sumAopPercentages(aoplist1) + sumAopPercentages(aoplist2));
	}

	private static String getFamilyLawOtherDescription(Context ctx) {
		String value = blankIfNull(ctx.get("FLAOP_OtherDescription"));
		if (value.trim().length() > 0) {
			return value;
		}
		value = blankIfNull(ctx.get("FLAOPCommentDesc"));
		if (value.trim().length() > 0) {
			return value;
		}
		value = blankIfNull(ctx.get("FLAO_CommentDesc_7"));
		if (value.trim().length() > 0) {
			return value;
		}
		value = blankIfNull(ctx.get("FLAOP_CommentDesc_7"));
		if (value.trim().length() > 0) {
			return value;
		}

		List familyLawRows = getListIfPresent(ctx, "FamilyLaw_list_01");
		for (int i = 0; i < familyLawRows.size(); i++) {
			Object item = familyLawRows.get(i);
			if (item instanceof Map) {
				Map row = (Map) item;
				if ("7".equals(getBlankIfNull(row, "FLAOPKey"))) {
					return getBlankIfNull(row, "FLAOPCommentDesc");
				}
			}
		}
		return HtmlConstants.EMPTY;
	}

	private static int sumAopPercentages(List aopList) {
		int total = 0;
		if (aopList == null) {
			return total;
		}
		for (int i = 0; i < aopList.size(); i++) {
			Object item = aopList.get(i);
			if (!(item instanceof Map)) {
				continue;
			}
			String value = getAopPercentage((Map) item).trim();
			if (value.length() == 0) {
				continue;
			}
			try {
				total += Integer.parseInt(value);
			} catch (NumberFormatException e) {
				logPdfException("Unable to parse AOP percentage value: " + value, e);
			}
		}
		return total;
	}

	private static String getAmountWithoutCurrency(Map map, String key) {
		String amount = getBlankIfNull(map, key).trim().replace("$", "").replace("\u20B9", "").trim();
		while (amount.length() > 0 && !Character.isDigit(amount.charAt(0)) && amount.charAt(0) != '-') {
			amount = amount.substring(1).trim();
		}
		return amount;
	}

	private static Map getMapIfPresent(Context ctx, String key) {
		Object value = ctx.get(key);
		return value instanceof Map ? (Map) value : Collections.EMPTY_MAP;
	}

	private static Map getMapFromList(List list, int index) {
		if (list == null || index < 0 || index >= list.size()) {
			return Collections.EMPTY_MAP;
		}
		Object value = list.get(index);
		return value instanceof Map ? (Map) value : Collections.EMPTY_MAP;
	}

	private static List getListIfPresent(Context ctx, String key) {
		Object value = ctx.get(key);
		return value instanceof List ? (List) value : Collections.EMPTY_LIST;
	}

	private static Map getFirstMapFromContext(Context ctx, String key) {
		Object value = ctx.get(key);
		if (value instanceof Map) {
			return (Map) value;
		}
		return value instanceof List ? getMapFromList((List) value, 0) : Collections.EMPTY_MAP;
	}

	private static String getClientTypeValue(Map map) {
		String value = getBlankIfNull(map, "IsClient").trim();
		if ("Y".equalsIgnoreCase(value) || "CLIENT".equalsIgnoreCase(value) || "Client".equals(value)) {
			return "CLIENT";
		}
		if ("N".equalsIgnoreCase(value) || "NON_CLIENT".equalsIgnoreCase(value) || "NonClient".equals(value)) {
			return "NON_CLIENT";
		}
		return value;
	}

	public static void getFillablePDFDoc(Context ctx, String outFile) throws Exception {
		PDDocument document = null;
		try {
			document = new PDDocument();
			loadApplicationFonts();
			PDAcroForm acroForm = createAcroForm(document);

			drawApplicationPages(ctx, acroForm, document);
			savePdfDocument(document, outFile);
			logger.debug("PDF Form created successfully at " + outFile);
		} catch (Exception e) {
			logPdfException("Error creating populated PDF. Saving an empty PDF instead", e);
			saveEmptyPdf(outFile);
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}

	private static void drawApplicationPages(final Context ctx, final PDAcroForm acroForm, final PDDocument document) {
		safeDraw("Firm Profile", new PdfDrawAction() {
			public void draw() throws Exception {
				drawFirmProfilePage(ctx, acroForm, document);
			}
		});
		safeDraw("Insurance History", new PdfDrawAction() {
			public void draw() throws Exception {
				drawInsuranceHistory(ctx, acroForm, document);
			}
		});
		safeDraw("Coverage And Attorneys", new PdfDrawAction() {
			public void draw() throws Exception {
				drawCoverageAndAttorneysPage(ctx, acroForm, document);
			}
		});
		safeDraw("Area Of Practice", new PdfDrawAction() {
			public void draw() throws Exception {
				drawAreaOfPracticePage(ctx, acroForm, document);
			}
		});
		safeDraw("About Your Firm And Practice Management", new PdfDrawAction() {
			public void draw() throws Exception {
				drawAboutYourFirmAndPracticeManagementPage(ctx, acroForm, document);
			}
		});
		safeDraw("Securities And Shared Office", new PdfDrawAction() {
			public void draw() throws Exception {
				drawSecuritiesAndSharedOfficePage(ctx, acroForm, document);
			}
		});
		safeDraw("Underwriting Information", new PdfDrawAction() {
			public void draw() throws Exception {
				drawUnderwritingInformationPage(ctx, acroForm, document);
			}
		});
		safeDraw("Fraud Warnings And Signature", new PdfDrawAction() {
			public void draw() throws Exception {
				drawFraudWarningsAndSignaturePage(ctx, acroForm, document);
			}
		});
		safeDraw("Supplement Pages", new PdfDrawAction() {
			public void draw() throws Exception {
				drawSupplementPages(ctx, acroForm, document);
			}
		});
		safeDraw("Page Footers", new PdfDrawAction() {
			public void draw() throws Exception {
				addPageFooters(document);
			}
		});
		safeDraw("Initial PDF Focus", new PdfDrawAction() {
			public void draw() throws Exception {
				focusFirstPageOnOpen(document);
			}
		});
	}

	private static void safeDraw(String sectionName, PdfDrawAction action) {
		try {
			action.draw();
		} catch (Exception e) {
			logPdfException("Skipping PDF section after error in " + sectionName, e);
		}
	}

	private static void logPdfException(String message, Exception e) {
		logger.error(message, e);
	}

	private static void savePdfDocument(PDDocument document, String outFile) throws Exception {
		ensureOutputDirectory(outFile);
		ensureAtLeastOnePage(document);
		document.save(outFile);
	}

	private static void saveEmptyPdf(String outFile) throws Exception {
		PDDocument emptyDocument = new PDDocument();
		try {
			ensureOutputDirectory(outFile);
			ensureAtLeastOnePage(emptyDocument);
			emptyDocument.save(outFile);
		} finally {
			emptyDocument.close();
		}
	}

	private static void ensureOutputDirectory(String outFile) {
		File outputFile = new File(outFile);
		File outputDir = outputFile.getParentFile();
		if (outputDir != null && !outputDir.exists()) {
			outputDir.mkdirs();
		}
	}

	private static void ensureAtLeastOnePage(PDDocument document) {
		if (document.getNumberOfPages() == 0) {
			document.addPage(new PDPage(PDPage.PAGE_SIZE_LETTER));
		}
	}

	private static PDAcroForm createAcroForm(PDDocument document) {
		PDResources resources = new PDResources();
		resources.addFont(regularFont, FORM_FONT_RESOURCE_NAME);

		PDAcroForm acroForm = new PDAcroForm(document);
		document.getDocumentCatalog().setAcroForm(acroForm);
		acroForm.setDefaultResources(resources);

		String defaultAppearanceString = getTextFieldDefaultAppearance();
		acroForm.getDictionary().setItem(COSName.DA, new COSString(defaultAppearanceString));
		acroForm.getDictionary().setBoolean(COSName.getPDFName("NeedAppearances"), false);
		return acroForm;
	}

	private static String getTextFieldDefaultAppearance() {
		return "/" + FORM_FONT_RESOURCE_NAME + " " + formatPdfNumber(FORM_FIELD_FONT_SIZE) + " Tf 0 g";
	}

	private static void addText(PDDocument document, PDPage page, String myText, float x, float y, boolean bold) {
		PDFont selectedFont = bold ? boldFont : regularFont;
		addText(document, page, myText, x, y, selectedFont, BODY_FONT_SIZE);
	}

	private static void loadApplicationFonts() {
		regularFont = PDType1Font.HELVETICA;
		boldFont = PDType1Font.HELVETICA_BOLD;
		italicFont = PDType1Font.HELVETICA_OBLIQUE;
	}

	private static void addText(PDDocument document, PDPage page, String myText, float x, float y, PDFont font,
			float fontSize) {
		try (PDPageContentStream contentStream = new PDPageContentStream(document, page, true, true, true)) {
			contentStream.beginText();
			contentStream.setFont(font, fontSize);
			contentStream.moveTextPositionByAmount(x, y);
			contentStream.drawString(myText);
			contentStream.endText();
		} catch (IOException e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void addTextInLine(PDDocument document, PDPage page, String myText, float x, float y, boolean bold) {
		addText(document, page, myText, x, y, bold);
	}

	private static float addInlineTextSegment(PDDocument document, PDPage page, String myText, float x, float y,
			boolean bold) {
		return addInlineTextSegment(document, page, myText, x, y, bold, BODY_FONT_SIZE);
	}

	private static float addInlineTextSegment(PDDocument document, PDPage page, String myText, float x, float y,
			boolean bold, float fontSize) {
		PDFont selectedFont = bold ? boldFont : regularFont;
		addText(document, page, myText, x, y, selectedFont, fontSize);
		return x + getTextWidth(selectedFont, myText, fontSize);
	}

	private static StyledTextPart textPart(String text, boolean bold) {
		return new StyledTextPart(text, bold);
	}

	private static float addStyledWrappedText(PDDocument document, PDPage page, float x, float y, float maxWidth,
			float fontSize, float lineHeight, StyledTextPart... parts) {
		float currentX = x;
		boolean lineHasText = false;
		for (StyledTextPart part : parts) {
			if (part == null || part.text == null || part.text.trim().length() == 0) {
				continue;
			}
			PDFont selectedFont = part.bold ? boldFont : regularFont;
			String[] words = part.text.trim().split("\\s+");
			for (String word : words) {
				String printableWord = lineHasText ? " " + word : word;
				float wordWidth = getTextWidth(selectedFont, printableWord, fontSize);
				if (lineHasText && currentX + wordWidth > x + maxWidth) {
					y -= lineHeight;
					currentX = x;
					lineHasText = false;
					printableWord = word;
					wordWidth = getTextWidth(selectedFont, printableWord, fontSize);
				}
				addText(document, page, printableWord, currentX, y, selectedFont, fontSize);
				currentX += wordWidth;
				lineHasText = true;
			}
		}
		return y - lineHeight;
	}

	private static void addCenteredText(PDDocument document, PDPage page, String text, float centerX, float y,
			PDFont font, float fontSize) {
		float textWidth = getTextWidth(font, text, fontSize);
		addText(document, page, text, centerX - (textWidth / 2), y, font, fontSize);
	}

	private static float addTextInBox(PDDocument document, PDPage page, String myText, float x, float y, float maxWidth,
			boolean bold) {
		PDFont selectedFont = bold ? boldFont : regularFont;
		return addWrappedText(document, page, myText, x, y, maxWidth, selectedFont, BODY_FONT_SIZE, BODY_FONT_SIZE);
	}

	private static void addPlaceHolderText(PDDocument document, PDPage page, String myText, float x, float y) {
		try (PDPageContentStream contentStream = new PDPageContentStream(document, page, true, true, true)) {
			contentStream.beginText();
			contentStream.setFont(italicFont, PLACEHOLDER_FONT_SIZE);
			contentStream.moveTextPositionByAmount(x, y);
			contentStream.drawString(myText);
			contentStream.endText();
		} catch (IOException e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static PDPage createPage(PDDocument document) {
		PDPage page = new PDPage(PDPage.PAGE_SIZE_LETTER);
		document.addPage(page);
		return page;
	}

	private static float addWrappedText(PDDocument document, PDPage page, String text, float x, float y, float maxWidth,
			boolean bold) {
		return addWrappedText(document, page, text, x, y, maxWidth, bold ? boldFont : regularFont, BODY_FONT_SIZE,
				BODY_FONT_SIZE);
	}

	private static float addWrappedText(PDDocument document, PDPage page, String text, float x, float y, float maxWidth,
			PDFont font, float fontSize, float lineHeight) {
		float hangingIndent = getQuestionHangingIndent(text, font, fontSize);
		if (hangingIndent <= 0) {
			List<String> lines = wrapText(font, text, fontSize, maxWidth);
			for (String line : lines) {
				addText(document, page, line, x, y, font, fontSize);
				y -= lineHeight;
			}
			return y;
		}

		String[] words = text.split("\\s+");
		StringBuilder line = new StringBuilder();
		boolean firstLine = true;
		for (String word : words) {
			String candidate = line.length() == 0 ? word : line.toString() + " " + word;
			float availableWidth = firstLine ? maxWidth : maxWidth - hangingIndent;
			if (getTextWidth(font, candidate, fontSize) <= availableWidth || line.length() == 0) {
				line.setLength(0);
				line.append(candidate);
				continue;
			}

			addText(document, page, line.toString(), firstLine ? x : x + hangingIndent, y, font, fontSize);
			y -= lineHeight;
			firstLine = false;
			line.setLength(0);
			line.append(word);
		}
		if (line.length() > 0) {
			addText(document, page, line.toString(), firstLine ? x : x + hangingIndent, y, font, fontSize);
			y -= lineHeight;
		}
		return y;
	}

	private static float getQuestionHangingIndent(String text, PDFont font, float fontSize) {
		if (text == null) {
			return 0;
		}
		String cleanText = text.trim();
		int tokenEnd = cleanText.indexOf(' ');
		if (tokenEnd <= 0 || tokenEnd > 4) {
			return 0;
		}
		String token = cleanText.substring(0, tokenEnd);
		if (!token.endsWith(".")) {
			return 0;
		}
		String numberPart = token.substring(0, token.length() - 1);
		boolean numeric = true;
		for (int i = 0; i < numberPart.length(); i++) {
			if (!Character.isDigit(numberPart.charAt(i))) {
				numeric = false;
				break;
			}
		}
		boolean lettered = numberPart.length() == 1 && Character.isLetter(numberPart.charAt(0));
		if (!numeric && !lettered) {
			return 0;
		}
		return getTextWidth(font, token + " ", fontSize);
	}

	private static List<String> wrapText(PDFont font, String text, float fontSize, float maxWidth) {
		List<String> lines = new ArrayList<String>();
		String[] words = text.split("\\s+");
		StringBuilder currentLine = new StringBuilder();

		for (String word : words) {
			String candidate = currentLine.length() == 0 ? word : currentLine.toString() + " " + word;
			if (getTextWidth(font, candidate, fontSize) <= maxWidth) {
				currentLine.setLength(0);
				currentLine.append(candidate);
			} else {
				if (currentLine.length() > 0) {
					lines.add(currentLine.toString());
				}
				currentLine.setLength(0);
				currentLine.append(word);
			}
		}

		if (currentLine.length() > 0) {
			lines.add(currentLine.toString());
		}
		return lines;
	}

	private static float addFraudNotice(PDDocument document, PDPage page, String notice, float x, float y,
			float maxWidth) {
		int labelEnd = notice.indexOf(":");
		if (labelEnd < 0) {
			return addWrappedText(document, page, notice, x, y, maxWidth, fraudRegularFont, FRAUD_NOTICE_FONT_SIZE,
					FRAUD_NOTICE_LINE_HEIGHT);
		}

		String label = notice.substring(0, labelEnd + 1);
		String body = notice.substring(labelEnd + 1).trim();
		float fontSize = FRAUD_NOTICE_FONT_SIZE;
		float lineHeight = FRAUD_NOTICE_LINE_HEIGHT;
		addText(document, page, label, x, y, fraudBoldFont, fontSize);

		float currentX = x + getTextWidth(fraudBoldFont, label + " ", fontSize);
		StringBuilder line = new StringBuilder();
		String[] words = body.split("\\s+");
		for (String word : words) {
			String candidate = line.length() == 0 ? word : line.toString() + " " + word;
			float availableWidth = x + maxWidth - currentX;
			if (getTextWidth(fraudRegularFont, candidate, fontSize) <= availableWidth) {
				line.setLength(0);
				line.append(candidate);
			} else {
				if (line.length() > 0) {
					addText(document, page, line.toString(), currentX, y, fraudRegularFont, fontSize);
				}
				y -= lineHeight;
				currentX = x;
				line.setLength(0);
				line.append(word);
			}
		}

		if (line.length() > 0) {
			addText(document, page, line.toString(), currentX, y, fraudRegularFont, fontSize);
		}
		return y - lineHeight;
	}

	private static float addYesNoNAQuestion(PDDocument document, PDAcroForm acroForm, PDPage page, String groupName,
			String defaultValue, String text, float x, float y) {
		y = addWrappedText(document, page, text, x, y, 390, false);
		addYesNoNAButtons(document, acroForm, page, groupName, defaultValue, x + 400, y + 13);
		return y - 8;
	}

	private static void addSmallText(PDDocument document, PDPage page, String text, float x, float y, boolean bold) {
		addText(document, page, text, x, y, bold ? boldFont : regularFont, SUPPLEMENT_FONT_SIZE);
	}

	private static float addSmallInlineTextSegment(PDDocument document, PDPage page, String text, float x, float y,
			boolean bold) {
		PDFont selectedFont = bold ? boldFont : regularFont;
		addText(document, page, text, x, y, selectedFont, SUPPLEMENT_FONT_SIZE);
		return x + getTextWidth(selectedFont, text, SUPPLEMENT_FONT_SIZE);
	}

	private static float addSmallIfYesText(PDDocument document, PDPage page, float x, float y, String suffix) {
		float currentX = addSmallInlineTextSegment(document, page, "If \"", x, y, false);
		currentX = addSmallInlineTextSegment(document, page, "Yes", currentX, y, true);
		currentX = addSmallInlineTextSegment(document, page, "\"", currentX, y, false);
		addSmallText(document, page, suffix, currentX, y, false);
		return currentX;
	}

	private static float addWrappedSmallText(PDDocument document, PDPage page, String text, float x, float y,
			float maxWidth, boolean bold) {
		return addWrappedText(document, page, text, x, y, maxWidth, bold ? boldFont : regularFont, SUPPLEMENT_FONT_SIZE,
				12f);
	}

	private static int getLeadingNumberEnd(String text) {
		if (text == null) {
			return -1;
		}
		int dotIndex = text.indexOf(". ");
		if (dotIndex <= 0) {
			return -1;
		}
		for (int i = 0; i < dotIndex; i++) {
			if (!Character.isDigit(text.charAt(i))) {
				return -1;
			}
		}
		return dotIndex + 1;
	}

	private static float addNumberedWrappedSmallText(PDDocument document, PDPage page, String text, float x, float y,
			float maxWidth, boolean bold) {
		int numberEnd = getLeadingNumberEnd(text);
		if (numberEnd < 0) {
			return addWrappedSmallText(document, page, text, x, y, maxWidth, bold);
		}
		String number = text.substring(0, numberEnd);
		String body = text.substring(numberEnd + 1);
		float textX = x + getTextWidth(regularFont, number + " ", SUPPLEMENT_FONT_SIZE) + 5;
		addSmallText(document, page, number, x, y, bold);
		return addWrappedSmallText(document, page, body, textX, y, maxWidth - (textX - x), bold);
	}

	private static float addWrappedTableLabel(PDDocument document, PDPage page, String text, float x, float y,
			float maxWidth) {
		return addWrappedText(document, page, text, x, y, maxWidth, regularFont, 8f, 8.5f);
	}

	private static void addRadioLabel(PDDocument document, PDPage page, String myText, float x, float y, float maxWidth,
			boolean bold) {
		PDFont selectedFont = bold ? boldFont : regularFont;
		addWrappedText(document, page, myText, x, y, maxWidth, selectedFont, RADIO_LABEL_FONT_SIZE, 10f);
	}

	private static float addWrappedRadioLabel(PDDocument document, PDPage page, String text, float x, float y,
			float maxWidth, boolean bold) {
		return addWrappedText(document, page, text, x, y, maxWidth, bold ? boldFont : regularFont,
				RADIO_LABEL_FONT_SIZE, 10f);
	}

	private static void addDefenseExpenseRadioLabel(PDDocument document, PDPage page, String text, float x, float y) {
		if (text.indexOf("reduce limits") >= 0) {
			addText(document, page, "Defense expenses", x, y, regularFont, RADIO_LABEL_FONT_SIZE);
			addText(document, page, "reduce limits of liability", x, y - 10, boldFont, RADIO_LABEL_FONT_SIZE);
			return;
		}
		if (text.indexOf("in addition") >= 0) {
			addText(document, page, "Defense expenses are paid", x, y, regularFont, RADIO_LABEL_FONT_SIZE);
			addText(document, page, "in addition to limits of liability", x, y - 10, boldFont, RADIO_LABEL_FONT_SIZE);
			return;
		}
		addWrappedRadioLabel(document, page, text, x, y, 130, false);
	}

	private static void addFirstDollarDefenseLabel(PDDocument document, PDPage page, float x, float y) {
		addText(document, page, "Deductible does not apply to", x, y, regularFont, RADIO_LABEL_FONT_SIZE);
		addText(document, page, "defense expenses", x, y - 10, boldFont, RADIO_LABEL_FONT_SIZE);
		addText(document, page, "(first dollar defense)", x, y - 20, boldFont, RADIO_LABEL_FONT_SIZE);
	}

	private static void addPageFiveText(PDDocument document, PDPage page, String text, float x, float y, boolean bold) {
		addText(document, page, text, x, y, bold ? boldFont : regularFont, PAGE_FIVE_FONT_SIZE);
	}

	private static float addPageFiveWrappedText(PDDocument document, PDPage page, String text, float x, float y,
			float maxWidth) {
		return addWrappedText(document, page, text, x, y, maxWidth, regularFont, PAGE_FIVE_FONT_SIZE,
				PAGE_FIVE_LINE_HEIGHT);
	}

	private static float addPageFiveNumberedWrappedText(PDDocument document, PDPage page, String number, String text,
			float x, float y, float maxWidth) {
		float textX = x + 25;
		addPageFiveText(document, page, number, x, y, false);
		return addPageFiveWrappedText(document, page, text, textX, y, maxWidth - (textX - x));
	}

	private static float addPageFiveLetteredWrappedText(PDDocument document, PDPage page, String letter, String text,
			float x, float y, float maxWidth) {
		float textX = x + 23;
		addPageFiveText(document, page, letter, x, y, false);
		return addPageFiveWrappedText(document, page, text, textX, y, maxWidth - (textX - x));
	}

	private static float addPageFiveInlineText(PDDocument document, PDPage page, String text, float x, float y,
			boolean bold) {
		PDFont selectedFont = bold ? boldFont : regularFont;
		addText(document, page, text, x, y, selectedFont, PAGE_FIVE_FONT_SIZE);
		return x + getTextWidth(selectedFont, text, PAGE_FIVE_FONT_SIZE);
	}

	private static float addPageFiveIfYesText(PDDocument document, PDPage page, float x, float y, String suffix) {
		float currentX = addPageFiveInlineText(document, page, "If \"", x, y, false);
		currentX = addPageFiveInlineText(document, page, "Yes", currentX, y, true);
		currentX = addPageFiveInlineText(document, page, "\"", currentX, y, false);
		addPageFiveText(document, page, suffix, currentX, y, false);
		return currentX;
	}

	private static void addPageFiveIfYesTextWithBoldPhrase(PDDocument document, PDPage page, float x, float y,
			String beforeBold, String boldPhrase, String afterBold) {
		float currentX = addPageFiveInlineText(document, page, "If \"", x, y, false);
		currentX = addPageFiveInlineText(document, page, "Yes", currentX, y, true);
		currentX = addPageFiveInlineText(document, page, "\"", currentX, y, false);
		currentX = addPageFiveInlineText(document, page, beforeBold, currentX, y, false);
		currentX = addPageFiveInlineText(document, page, boldPhrase, currentX, y, true);
		addPageFiveText(document, page, afterBold, currentX, y, false);
	}

	private static float addPageFiveYesNoQuestionAtRadioX(PDDocument document, PDAcroForm acroForm, PDPage page,
			String groupName, String defaultValue, String text, float x, float y, float radioX) {
		y = addPageFiveWrappedText(document, page, text, x, y, radioX - x - 15);
		addYesNoButtons(document, acroForm, page, groupName, defaultValue, radioX, y + 12);
		return y - 8;
	}

	private static float addPageFiveNumberedYesNoQuestionAtRadioX(PDDocument document, PDAcroForm acroForm, PDPage page,
			String groupName, String defaultValue, String number, String text, float x, float y, float radioX) {
		y = addPageFiveNumberedWrappedText(document, page, number, text, x, y, radioX - x - 15);
		addYesNoButtons(document, acroForm, page, groupName, defaultValue, radioX, y + 12);
		return y - 8;
	}

	private static float addPageFiveLetteredYesNoQuestionAtRadioX(PDDocument document, PDAcroForm acroForm, PDPage page,
			String groupName, String defaultValue, String letter, String text, float x, float y, float radioX) {
		y = addPageFiveLetteredWrappedText(document, page, letter, text, x, y, radioX - x - 15);
		addYesNoButtons(document, acroForm, page, groupName, defaultValue, radioX, y + 12);
		return y - 8;
	}

	private static void drawLine(PDDocument document, PDPage page, float x1, float y1, float x2, float y2) {
		drawLine(document, page, x1, y1, x2, y2, 0.5f);
	}

	private static void drawLine(PDDocument document, PDPage page, float x1, float y1, float x2, float y2,
			float lineWidth) {
		try (PDPageContentStream contentStream = new PDPageContentStream(document, page, true, true, true)) {
			contentStream.setStrokingColor(Color.BLACK);
			contentStream.setLineWidth(lineWidth);
			contentStream.drawLine(x1, y1, x2, y2);
		} catch (IOException e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void drawBox(PDDocument document, PDPage page, float x, float y, float width, float height) {
		try (PDPageContentStream contentStream = new PDPageContentStream(document, page, true, true, true)) {
			contentStream.setStrokingColor(Color.BLACK);
			contentStream.setLineWidth(0.5f);
			contentStream.addRect(x, y, width, height);
			contentStream.stroke();
		} catch (IOException e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void drawFilledBox(PDDocument document, PDPage page, float x, float y, float width, float height,
			Color fillColor) {
		try (PDPageContentStream contentStream = new PDPageContentStream(document, page, true, true, true)) {
			contentStream.setNonStrokingColor(fillColor);
			contentStream.addRect(x, y, width, height);
			contentStream.fill(1);
			contentStream.setStrokingColor(Color.BLACK);
			contentStream.setLineWidth(0.5f);
			contentStream.addRect(x, y, width, height);
			contentStream.stroke();
		} catch (IOException e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void drawColumnTable(PDDocument document, PDPage page, float x, float topY, int[] colWidths,
			int rowCount, float headerHeight, float rowHeight) {
		int totalWidth = 0;
		for (int width : colWidths) {
			totalWidth += width;
		}

		float bottomY = topY - headerHeight - (rowCount * rowHeight);
		drawBox(document, page, x, bottomY, totalWidth, headerHeight + (rowCount * rowHeight));
		drawLine(document, page, x, topY - headerHeight, x + totalWidth, topY - headerHeight);

		float currentX = x;
		for (int width : colWidths) {
			currentX += width;
			drawLine(document, page, currentX, bottomY, currentX, topY);
		}

		for (int row = 1; row < rowCount; row++) {
			float rowY = topY - headerHeight - (row * rowHeight);
			drawLine(document, page, x, rowY, x + totalWidth, rowY);
		}
	}

	private static float addCompactYesNoQuestion(PDDocument document, PDAcroForm acroForm, PDPage page,
			String groupName, String defaultValue, String text, float x, float y) {
		int numberEnd = getLeadingNumberEnd(text);
		if (numberEnd >= 0) {
			return addNumberedCompactYesNoQuestion(document, acroForm, page, groupName, defaultValue,
					text.substring(0, numberEnd), text.substring(numberEnd + 1), x, y);
		}
		y = addWrappedSmallText(document, page, text, x, y, 430, false);
		addRadioButtons(document, acroForm, page, groupName, createCompactYesNoButtons(), defaultValue, x + 465, y + 9);
		return y - 5;
	}

	private static float addCompactYesNoQuestions(PDDocument document, PDAcroForm acroForm, PDPage page,
			Map values, String[] groupNames, String[] questions, float x, float y) {
		for (int i = 0; i < groupNames.length; i++) {
			y = addCompactYesNoQuestion(document, acroForm, page, groupNames[i], getBlankIfNull(values, groupNames[i]),
					questions[i], x, y);
		}
		return y;
	}

	private static void addStackedFields(PDAcroForm acroForm, PDPage page, String fieldPrefix, int count,
			float x, float firstY, float yStep, int width, int height,String fieldValue) {
		for (int i = 1; i <= count; i++) {
			addField(acroForm, page, fieldPrefix + i, fieldValue, x, firstY - ((i - 1) * yStep), width, height);
		}
	}

	private static float addNumberedCompactYesNoQuestion(PDDocument document, PDAcroForm acroForm, PDPage page,
			String groupName, String defaultValue, String number, String text, float x, float y) {
		float textX = x + getTextWidth(regularFont, number + " ", SUPPLEMENT_FONT_SIZE);
		addSmallText(document, page, number, x, y, false);
		y = addWrappedSmallText(document, page, text, textX, y, 430 - (textX - x), false);
		addRadioButtons(document, acroForm, page, groupName, createCompactYesNoButtons(), defaultValue, x + 465, y + 9);
		return y - 5;
	}

	private static float addCompactYesNoQuestionInColumn(PDDocument document, PDAcroForm acroForm, PDPage page,
			String groupName, String defaultValue, String text, float x, float y, float radioX) {
		int numberEnd = getLeadingNumberEnd(text);
		if (numberEnd >= 0) {
			String number = text.substring(0, numberEnd);
			String body = text.substring(numberEnd + 1);
			float textX = x + getTextWidth(regularFont, number + " ", SUPPLEMENT_FONT_SIZE) + 5;
			addSmallText(document, page, number, x, y, false);
			y = addWrappedSmallText(document, page, body, textX, y, radioX - textX - 15, false);
			addRadioButtons(document, acroForm, page, groupName, createCompactYesNoButtons(), defaultValue, radioX,
					y + 9);
			return y - 5;
		}
		y = addWrappedSmallText(document, page, text, x, y, 430, false);
		addRadioButtons(document, acroForm, page, groupName, createCompactYesNoButtons(), defaultValue, radioX, y + 9);
		return y - 5;
	}

	private static void addPageFooters(PDDocument document) {
		try {
			List pages = document.getDocumentCatalog().getAllPages();
			for (int i = 0; i < pages.size(); i++) {
				PDPage page = (PDPage) pages.get(i);
				addText(document, page, "ISMIE ALA-04-S001 (09012021)", 20, 8, regularFont, 6.5f);
				addText(document, page, "Page " + (i + 1) + " of " + pages.size(), 520, 8, regularFont, 6.5f);
			}
		} catch (Exception e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void focusFirstPageOnOpen(PDDocument document) {
		try {
			List pages = document.getDocumentCatalog().getAllPages();
			if (pages.isEmpty()) {
				return;
			}

			PDPage firstPage = (PDPage) pages.get(0);
			PDPageXYZDestination destination = new PDPageXYZDestination();
			destination.setPage(firstPage);
			destination.setLeft(0);
			destination.setTop((int) firstPage.getMediaBox().getHeight());
			destination.setZoom(0);

			PDActionGoTo openFirstPage = new PDActionGoTo();
			openFirstPage.setDestination(destination);
			document.getDocumentCatalog().setOpenAction(openFirstPage);
			document.getDocumentCatalog().setPageMode("UseNone");
			document.getDocumentCatalog().setPageLayout("SinglePage");
		} catch (Exception e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static class LayoutCursor {
		private final PDDocument document;
		private PDPage page;
		private float y;

		LayoutCursor(PDDocument document) {
			this.document = document;
			newPage();
		}

		void newPage() {
			page = createPage(document);
			y = 740;
		}

		void ensure(float requiredHeight) {
			if (y - requiredHeight < 55) {
				newPage();
			}
		}
	}

	private static Button createButton(String name, String value) {
		Button button = new Button();
		button.setName(name);
		button.setValue(value);
		return button;
	}

	private static ArrayList<Button> createButtonList(Button... buttons) {
		ArrayList<Button> buttonList = new ArrayList<Button>();
		Collections.addAll(buttonList, buttons);
		return buttonList;
	}

	private static List<Button> createYesNoButtons() {
		return createButtonList(createButton("Yes", "Y"), createButton("No", "N"));
	}

	private static List<Button> createYesNoNAButtons() {
		return createButtonList(createButton("NA", "N/A"), createButton("Yes", "Y"), createButton("No", "N"));
	}

	private static List<Button> createCompactYesNoButtons() {
		return createButtonList(createButton("Yes", "Y"), createButton("No", "N"));
	}

	private static List<Button> createDefenseExpenseButtons() {
		return createButtonList(createButton("Defense expenses reduce limits of liability", "DR"),
				createButton("Defense expenses are paid in addition to limits of liability", "DA"));
	}

	private static List<Button> createDeductibleTypeButtons() {
		return createButtonList(createButton("Per Claim", "PC"), createButton("Annual Aggregate", "AA"));
	}

	private static List<Button> createRevenueButtons() {
		return createButtonList(createButton("$0-$100,000", "1"), createButton("$100,000-$250,000", "2"),
				createButton("$250,000-$500,000", "3"), createButton("$500,000-$750,000", "4"),
				createButton("$750,000-$1,000,000", "5"), createButton("$1,000,000-$2,000,000", "6"),
				createButton("$2,000,000-$3,000,000", "7"), createButton("$3,000,000+", "8"));
	}

	private static void addField(PDAcroForm acroForm, PDPage page, String name, String defaultValue, float x, float y,
			int fieldWidth, int fieldHeight) {
		addField(acroForm, page, name, defaultValue, x, y, fieldWidth, fieldHeight, false);
	}

	private static void addWholeNumberField(PDAcroForm acroForm, PDPage page, String name, String defaultValue,
			float x, float y, int fieldWidth, int fieldHeight) {
		addField(acroForm, page, name, formatWholeNumber(defaultValue), x, y, fieldWidth, fieldHeight, true);
	}

	private static void addField(PDAcroForm acroForm, PDPage page, String name, String defaultValue, float x, float y,
			int fieldWidth, int fieldHeight, boolean wholeNumberOnly) {
		try {
			PDTextbox textBox = new PDTextbox(acroForm);
			initializeWidgetAnnotation(textBox.getDictionary());
			textBox.setPartialName(name);
			applyTextFieldDefaults(textBox);
			if (wholeNumberOnly) {
				attachWholeNumberScript(textBox);
			}
			acroForm.getFields().add(textBox);

			PDAnnotationWidget widget = textBox.getWidget();
			PDRectangle rect = createRectangle(x, y, fieldWidth, Math.max(fieldHeight, MIN_TEXT_FIELD_HEIGHT));
			widget.setRectangle(rect);
			widget.setPage(page);

			PDAppearanceCharacteristicsDictionary fieldAppearance = new PDAppearanceCharacteristicsDictionary(
					new COSDictionary());
			fieldAppearance.setBorderColour(createColor(0, 0, 0));
			widget.setAppearanceCharacteristics(fieldAppearance);
			PDBorderStyleDictionary borderStyleDictionary = new PDBorderStyleDictionary();
			borderStyleDictionary.setWidth(0.5f);
			borderStyleDictionary.setStyle(PDBorderStyleDictionary.STYLE_SOLID);
			widget.setBorderStyle(borderStyleDictionary);
			widget.setPrinted(true);

			page.getAnnotations().add(widget);
			textBox.setValue(wholeNumberOnly ? formatWholeNumber(defaultValue) : defaultValue);
		} catch (IOException e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	@SuppressWarnings("unchecked")
	private static void addCalculatedTotalField(PDAcroForm acroForm, PDPage page, String name, String defaultValue,
			float x, float y, float fieldWidth, float fieldHeight, String[] sourceFieldNames) {
		try {
			PDTextbox textBox = new PDTextbox(acroForm);
			initializeWidgetAnnotation(textBox.getDictionary());
			textBox.setPartialName(name);
			textBox.setAlternateFieldName(name);
			textBox.getDictionary().setInt(COSName.FF, 1);
			applyTextFieldDefaults(textBox);
			attachCalculationScript(textBox, sourceFieldNames);
			acroForm.getFields().add(textBox);

			PDAnnotationWidget widget = textBox.getWidget();
			widget.setRectangle(createRectangle(x, y, fieldWidth, Math.max(fieldHeight, MIN_TEXT_FIELD_HEIGHT)));
			widget.setPage(page);
			widget.setPrinted(true);

			PDAppearanceCharacteristicsDictionary fieldAppearance =
					new PDAppearanceCharacteristicsDictionary(new COSDictionary());
			fieldAppearance.setBorderColour(createColor(0, 0, 0));
			widget.setAppearanceCharacteristics(fieldAppearance);
			PDBorderStyleDictionary borderStyleDictionary = new PDBorderStyleDictionary();
			borderStyleDictionary.setWidth(0.5f);
			borderStyleDictionary.setStyle(PDBorderStyleDictionary.STYLE_SOLID);
			widget.setBorderStyle(borderStyleDictionary);

			page.getAnnotations().add(widget);
			addFieldToCalculationOrder(acroForm, textBox);
			textBox.setValue(defaultValue == null ? "" : defaultValue);
		} catch (IOException e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void attachCalculationScript(PDTextbox textBox, String[] sourceFieldNames) {
		COSDictionary action = new COSDictionary();
		action.setItem(COSName.S, COSName.getPDFName("JavaScript"));
		action.setItem(COSName.getPDFName("JS"), new COSString(buildSumScript(sourceFieldNames)));
		COSDictionary additionalActions = new COSDictionary();
		additionalActions.setItem(COSName.getPDFName("C"), action);
		textBox.getDictionary().setItem(COSName.getPDFName("AA"), additionalActions);
	}

	private static void attachWholeNumberScript(PDTextbox textBox) {
		COSDictionary action = new COSDictionary();
		action.setItem(COSName.S, COSName.getPDFName("JavaScript"));
		action.setItem(COSName.getPDFName("JS"),
				new COSString("if (!event.willCommit) { event.rc = /^[0-9]*$/.test(event.change); } "
						+ "else { event.rc = (event.value === '' || /^[0-9]+$/.test(event.value)); }"));
		COSDictionary additionalActions = new COSDictionary();
		additionalActions.setItem(COSName.getPDFName("K"), action);
		textBox.getDictionary().setItem(COSName.getPDFName("AA"), additionalActions);
	}

	private static void addFieldToCalculationOrder(PDAcroForm acroForm, PDTextbox textBox) {
		COSArray calculationOrder = (COSArray) acroForm.getDictionary().getDictionaryObject(COSName.getPDFName("CO"));
		if (calculationOrder == null) {
			calculationOrder = new COSArray();
			acroForm.getDictionary().setItem(COSName.getPDFName("CO"), calculationOrder);
		}
		calculationOrder.add(textBox.getDictionary());
	}

	private static String buildSumScript(String[] sourceFieldNames) {
		StringBuilder script = new StringBuilder("var total = 0;\n")
				.append("function addPercent(fieldName) {\n")
				.append("  var field = this.getField(fieldName);\n")
				.append("  if (!field) { return; }\n")
				.append("  var rawValue = field.valueAsString;\n")
				.append("  if (rawValue == null || rawValue === \"\" || rawValue === \"Off\") { return; }\n")
				.append("  rawValue = String(rawValue).replace(/,/g, \"\").replace(/[^0-9.\\-]/g, \"\");\n")
				.append("  if (rawValue === \"\" || rawValue === \"-\" || rawValue === \".\") { return; }\n")
				.append("  var numericValue = Number(rawValue);\n")
				.append("  if (!isNaN(numericValue)) { total += numericValue; }\n")
				.append("}\n");
		for (int i = 0; i < sourceFieldNames.length; i++) {
			script.append("addPercent.call(this, \"")
					.append(escapeJavaScript(sourceFieldNames[i])).append("\");\n");
		}
		script.append("event.value = total === 0 ? \"\" : String(Math.round(total * 100) / 100);");
		return script.toString();
	}

	private static String escapeJavaScript(String value) {
		return value == null ? "" : value.replace("\\", "\\\\").replace("\"", "\\\"");
	}

	private static String sumValues(Context ctx, String[] keys) {
		double total = 0;
		for (int i = 0; i < keys.length; i++) {
			total += parseNumber(getBlankIfNull(ctx, keys[i]));
		}
		return formatTotal(total);
	}

	private static String sumFieldDefaults(String[] values) {
		double total = 0;
		for (int i = 0; i < values.length; i++) {
			total += parseNumber(values[i]);
		}
		return formatTotal(total);
	}

	private static double parseNumber(String value) {
		if (value == null) {
			return 0;
		}
		String cleaned = value.replaceAll("[^0-9.\\-]", "");
		if (cleaned.length() == 0) {
			return 0;
		}
		try {
			return Double.parseDouble(cleaned);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	private static String formatTotal(double total) {
		if (total == 0) {
			return "";
		}
		long rounded = Math.round(total);
		if (Math.abs(total - rounded) < 0.0001d) {
			return Long.toString(rounded);
		}
		return String.format(Locale.US, "%.2f", new Object[] { Double.valueOf(total) });
	}

	private static String formatWholeNumber(String value) {
		if (value == null || value.trim().length() == 0) {
			return "";
		}
		double number = parseNumber(value);
		if (Math.abs(number) < 0.0001d) {
			return "";
		}
		return Long.toString(Math.round(number));
	}

	private static void addMultilineField(PDAcroForm acroForm, PDPage page, String name, String defaultValue, float x,
			float y, int fieldWidth, int fieldHeight) {
		try {
			PDTextbox textBox = new PDTextbox(acroForm);
			initializeWidgetAnnotation(textBox.getDictionary());
			textBox.setPartialName(name);
			textBox.getDictionary().setInt(COSName.FF, TEXT_FIELD_MULTILINE_FLAG);
			applyTextFieldDefaults(textBox);
			acroForm.getFields().add(textBox);

			PDAnnotationWidget widget = textBox.getWidget();
			PDRectangle rect = createRectangle(x, y, fieldWidth, Math.max(fieldHeight, MIN_TEXT_FIELD_HEIGHT));
			widget.setRectangle(rect);
			widget.setPage(page);

			PDAppearanceCharacteristicsDictionary fieldAppearance = new PDAppearanceCharacteristicsDictionary(
					new COSDictionary());
			fieldAppearance.setBorderColour(createColor(0, 0, 0));
			widget.setAppearanceCharacteristics(fieldAppearance);
			PDBorderStyleDictionary borderStyleDictionary = new PDBorderStyleDictionary();
			borderStyleDictionary.setWidth(0.5f);
			borderStyleDictionary.setStyle(PDBorderStyleDictionary.STYLE_SOLID);
			widget.setBorderStyle(borderStyleDictionary);
			widget.setPrinted(true);

			page.getAnnotations().add(widget);
			textBox.setValue(defaultValue);
		} catch (IOException e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static float addAreaPracticeDescriptionFields(PDDocument document, PDAcroForm acroForm, PDPage page,
			float x, float y, String[] labels, String[] fieldNames, String[] defaultValues) {
		float gap = 8f;
		float columnWidth = (570f - (gap * 2)) / 3f;
		float labelHeight = 12f;
		int fieldHeight = 22;
		float fieldY = y - labelHeight - fieldHeight;
		for (int i = 0; i < labels.length; i++) {
			float columnX = x + (i * (columnWidth + gap));
			addWrappedText(document, page, labels[i], columnX, y - 3, columnWidth, boldFont, 8f, 8f);
			drawBox(document, page, columnX, fieldY, columnWidth, fieldHeight);
			addMultilineField(acroForm, page, fieldNames[i], defaultValues[i], columnX, fieldY,
					(int) columnWidth, fieldHeight);
		}
		return fieldY - 3;
	}

	private static void applyTextFieldDefaults(PDTextbox textBox) {
		textBox.getDictionary().setItem(COSName.DA, new COSString(getTextFieldDefaultAppearance()));
	}

	private static void addCheckBox(PDDocument document, PDAcroForm acroForm, PDPage page, String name, boolean checked,
			float x, float y) {
		try {
			PDRectangle rect = createRectangle(x, y, CHECKBOX_SIZE, CHECKBOX_SIZE);
			PDCheckbox checkbox = new PDCheckbox(acroForm, createFieldDictionary(BUTTON_FIELD_TYPE));
			initializeWidgetAnnotation(checkbox.getDictionary());
			checkbox.setPartialName(name);
			PDAnnotationWidget widget = checkbox.getWidget();
			widget.setPage(page);
			widget.setRectangle(rect);
			widget.setPrinted(true);

			PDAppearanceCharacteristicsDictionary appearanceCharacteristics = new PDAppearanceCharacteristicsDictionary(
					new COSDictionary());
			appearanceCharacteristics.setBorderColour(createColor(0, 0, 0));
			appearanceCharacteristics.setBackground(createColor(1, 1, 1));
			widget.setAppearanceCharacteristics(appearanceCharacteristics);

			PDBorderStyleDictionary borderStyleDictionary = new PDBorderStyleDictionary();
			borderStyleDictionary.setWidth(1);
			borderStyleDictionary.setStyle(PDBorderStyleDictionary.STYLE_SOLID);
			widget.setBorderStyle(borderStyleDictionary);

			PDAppearanceDictionary ap = new PDAppearanceDictionary();
			Map<String, PDAppearanceStream> normalAppearance = new HashMap<String, PDAppearanceStream>();
			normalAppearance.put("Off", createCheckBoxAppearanceStream(document, widget, false));
			normalAppearance.put("Y", createCheckBoxAppearanceStream(document, widget, true));
			ap.setNormalAppearance(normalAppearance);
			widget.setAppearance(ap);
			widget.setAppearanceStream("Off");

			page.getAnnotations().add(widget);
			acroForm.getFields().add(checkbox);

			if (checked) {
				checkbox.getDictionary().setItem(COSName.V, COSName.getPDFName("Y"));
				widget.setAppearanceStream("Y");
				checkbox.getDictionary().setItem(COSName.AS, COSName.getPDFName("Y"));
			} else {
				checkbox.getDictionary().setItem(COSName.V, COSName.getPDFName("Off"));
				widget.setAppearanceStream("Off");
				checkbox.getDictionary().setItem(COSName.AS, COSName.getPDFName("Off"));
			}
		} catch (IOException e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void addRadioButtons(PDDocument document, PDAcroForm acroForm, PDPage page, List<Button> buttonList,
			String defaultValue, float x, float y) {
		addRadioButtons(document, acroForm, page, "RadioGrp_" + System.currentTimeMillis(), buttonList, defaultValue, x,
				y);
	}

	private static void addRadioButtons(PDDocument document, PDAcroForm acroForm, PDPage page, String groupName,
			List<Button> buttonList, String defaultValue, float x, float y) {
		try {
			List<String> buttonValues = new ArrayList<String>();
			for (Button button : buttonList) {
				buttonValues.add(button.getValue());
			}

			PDRadioCollection radioButton = new PDRadioCollection(acroForm, createFieldDictionary(BUTTON_FIELD_TYPE));
			radioButton.setPartialName(groupName);
			radioButton.getDictionary().setInt(COSName.FF, RADIO_BUTTON_FLAG);
			radioButton.setOptions(buttonValues);

			PDAppearanceCharacteristicsDictionary appearanceCharacteristics = new PDAppearanceCharacteristicsDictionary(
					new COSDictionary());
			appearanceCharacteristics.setBorderColour(createColor(0, 0, 0));
			appearanceCharacteristics.setBackground(createColor(1, 1, 1));

			ArrayList<COSObjectable> radioOptions = new ArrayList<COSObjectable>();
			float startX = x;
			float currentX = x;
			float currentY = y;
			float pageRight = getPageRight(page);

			for (Button button : buttonList) {
				float labelWidth = getTextWidth(regularFont, button.getName(), RADIO_LABEL_FONT_SIZE);
				float optionWidth = RADIO_BUTTON_SIZE + RADIO_LABEL_GAP + labelWidth;
				if (currentX > startX && currentX + optionWidth > pageRight) {
					currentX = startX;
					currentY -= RADIO_ROW_GAP;
				}

				PDCheckbox radioOption = new PDCheckbox(acroForm, createFieldDictionary(BUTTON_FIELD_TYPE));
				initializeWidgetAnnotation(radioOption.getDictionary());
				radioOption.setParent(radioButton);

				PDAnnotationWidget widget = radioOption.getWidget();
				widget.setRectangle(createRectangle(currentX, currentY, RADIO_BUTTON_SIZE, RADIO_BUTTON_SIZE));
				widget.setAppearanceCharacteristics(appearanceCharacteristics);

				PDBorderStyleDictionary borderStyleDictionary = new PDBorderStyleDictionary();
				borderStyleDictionary.setWidth(1);
				borderStyleDictionary.setStyle(PDBorderStyleDictionary.STYLE_SOLID);
				widget.setBorderStyle(borderStyleDictionary);
				widget.setPage(page);
				widget.setPrinted(true);

				PDAppearanceDictionary appearance = new PDAppearanceDictionary();
				Map<String, PDAppearanceStream> appearanceMap = new HashMap<String, PDAppearanceStream>();
				appearanceMap.put("Off", createRadioButtonAppearanceStream(document, widget, false));
				appearanceMap.put(button.getValue(), createRadioButtonAppearanceStream(document, widget, true));
				appearance.setNormalAppearance(appearanceMap);
				widget.setAppearance(appearance);
				widget.setAppearanceStream("Off");
				radioOptions.add(radioOption);
				page.getAnnotations().add(widget);

				addText(document, page, button.getName(), currentX + RADIO_BUTTON_SIZE + RADIO_LABEL_GAP, currentY + 3,
						regularFont, RADIO_LABEL_FONT_SIZE);
				currentX += optionWidth + RADIO_OPTION_GAP;
			}

			radioButton.setKids(radioOptions);
			acroForm.getFields().add(radioButton);
			applyRadioSelection(radioButton, radioOptions, buttonList, defaultValue);

		} catch (IOException e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void addCoverageRadioButtons(PDDocument document, PDAcroForm acroForm, PDPage page, String groupName,
			List<Button> buttonList, String defaultValue, float[] buttonX, float y, float[] labelWidths,
			boolean wrapLabels) {
		try {
			List<String> buttonValues = new ArrayList<String>();
			for (Button button : buttonList) {
				buttonValues.add(button.getValue());
			}

			PDRadioCollection radioButton = new PDRadioCollection(acroForm, createFieldDictionary(BUTTON_FIELD_TYPE));
			radioButton.setPartialName(groupName);
			radioButton.getDictionary().setInt(COSName.FF, RADIO_BUTTON_FLAG);
			radioButton.setOptions(buttonValues);

			PDAppearanceCharacteristicsDictionary appearanceCharacteristics = new PDAppearanceCharacteristicsDictionary(
					new COSDictionary());
			appearanceCharacteristics.setBorderColour(createColor(0, 0, 0));
			appearanceCharacteristics.setBackground(createColor(1, 1, 1));

			ArrayList<COSObjectable> radioOptions = new ArrayList<COSObjectable>();
			for (int i = 0; i < buttonList.size(); i++) {
				Button button = buttonList.get(i);
				PDCheckbox radioOption = new PDCheckbox(acroForm, createFieldDictionary(BUTTON_FIELD_TYPE));
				initializeWidgetAnnotation(radioOption.getDictionary());
				radioOption.setParent(radioButton);

				PDAnnotationWidget widget = radioOption.getWidget();
				widget.setRectangle(createRectangle(buttonX[i], y, RADIO_BUTTON_SIZE, RADIO_BUTTON_SIZE));
				widget.setAppearanceCharacteristics(appearanceCharacteristics);

				PDBorderStyleDictionary borderStyleDictionary = new PDBorderStyleDictionary();
				borderStyleDictionary.setWidth(1);
				borderStyleDictionary.setStyle(PDBorderStyleDictionary.STYLE_SOLID);
				widget.setBorderStyle(borderStyleDictionary);
				widget.setPage(page);
				widget.setPrinted(true);

				PDAppearanceDictionary appearance = new PDAppearanceDictionary();
				Map<String, PDAppearanceStream> appearanceMap = new HashMap<String, PDAppearanceStream>();
				appearanceMap.put("Off", createRadioButtonAppearanceStream(document, widget, false));
				appearanceMap.put(button.getValue(), createRadioButtonAppearanceStream(document, widget, true));
				appearance.setNormalAppearance(appearanceMap);
				widget.setAppearance(appearance);
				widget.setAppearanceStream("Off");

				radioOptions.add(radioOption);
				page.getAnnotations().add(widget);
				if (wrapLabels) {
					addDefenseExpenseRadioLabel(document, page, button.getName(), buttonX[i] + RADIO_BUTTON_SIZE + 4,
							y + 9);
				} else {
					addRadioLabel(document, page, button.getName(), buttonX[i] + RADIO_BUTTON_SIZE + 4, y + 4,
							labelWidths[i], false);
				}
			}

			radioButton.setKids(radioOptions);
			acroForm.getFields().add(radioButton);
			applyRadioSelection(radioButton, radioOptions, buttonList, defaultValue);
		} catch (IOException e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void addPositionedRadioButtons(PDDocument document, PDAcroForm acroForm, PDPage page,
			String groupName, List<Button> buttonList, String defaultValue, float[] buttonX, float[] buttonY) {
		try {
			List<String> buttonValues = new ArrayList<String>();
			for (Button button : buttonList) {
				buttonValues.add(button.getValue());
			}

			PDRadioCollection radioButton = new PDRadioCollection(acroForm, createFieldDictionary(BUTTON_FIELD_TYPE));
			radioButton.setPartialName(groupName);
			radioButton.getDictionary().setInt(COSName.FF, RADIO_BUTTON_FLAG);
			radioButton.setOptions(buttonValues);

			PDAppearanceCharacteristicsDictionary appearanceCharacteristics = new PDAppearanceCharacteristicsDictionary(
					new COSDictionary());
			appearanceCharacteristics.setBorderColour(createColor(0, 0, 0));
			appearanceCharacteristics.setBackground(createColor(1, 1, 1));

			ArrayList<COSObjectable> radioOptions = new ArrayList<COSObjectable>();
			for (int i = 0; i < buttonList.size(); i++) {
				Button button = buttonList.get(i);
				PDCheckbox radioOption = new PDCheckbox(acroForm, createFieldDictionary(BUTTON_FIELD_TYPE));
				initializeWidgetAnnotation(radioOption.getDictionary());
				radioOption.setParent(radioButton);

				PDAnnotationWidget widget = radioOption.getWidget();
				widget.setRectangle(createRectangle(buttonX[i], buttonY[i], RADIO_BUTTON_SIZE, RADIO_BUTTON_SIZE));
				widget.setAppearanceCharacteristics(appearanceCharacteristics);

				PDBorderStyleDictionary borderStyleDictionary = new PDBorderStyleDictionary();
				borderStyleDictionary.setWidth(1);
				borderStyleDictionary.setStyle(PDBorderStyleDictionary.STYLE_SOLID);
				widget.setBorderStyle(borderStyleDictionary);
				widget.setPage(page);
				widget.setPrinted(true);

				PDAppearanceDictionary appearance = new PDAppearanceDictionary();
				Map<String, PDAppearanceStream> appearanceMap = new HashMap<String, PDAppearanceStream>();
				appearanceMap.put("Off", createRadioButtonAppearanceStream(document, widget, false));
				appearanceMap.put(button.getValue(), createRadioButtonAppearanceStream(document, widget, true));
				appearance.setNormalAppearance(appearanceMap);
				widget.setAppearance(appearance);
				widget.setAppearanceStream("Off");

				radioOptions.add(radioOption);
				page.getAnnotations().add(widget);
				addText(document, page, button.getName(), buttonX[i] + RADIO_BUTTON_SIZE + 8, buttonY[i] + 2,
						regularFont, BODY_FONT_SIZE);
			}

			radioButton.setKids(radioOptions);
			acroForm.getFields().add(radioButton);
			applyRadioSelection(radioButton, radioOptions, buttonList, defaultValue);
		} catch (IOException e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void applyRadioSelection(PDRadioCollection radioButton, ArrayList<COSObjectable> radioOptions,
			List<Button> buttonList, String defaultValue) throws IOException {
		String selectedValue = resolveRadioSelectionValue(buttonList, defaultValue);
		if (selectedValue.length() > 0) {
			radioButton.setValue(selectedValue);
			radioButton.getDictionary().setItem(COSName.V, COSName.getPDFName(selectedValue));
			radioButton.getDictionary().setItem(COSName.DV, COSName.getPDFName(selectedValue));
		}
		for (int i = 0; i < radioOptions.size() && i < buttonList.size(); i++) {
			PDCheckbox radioOption = (PDCheckbox) radioOptions.get(i);
			String optionValue = buttonList.get(i).getValue();
			String appearanceState = optionValue.equals(selectedValue) ? optionValue : "Off";
			radioOption.getWidget().setAppearanceStream(appearanceState);
			radioOption.getDictionary().setItem(COSName.AS, COSName.getPDFName(appearanceState));
		}
	}

	private static String resolveRadioSelectionValue(List<Button> buttonList, String defaultValue) {
		String value = defaultValue == null ? "" : defaultValue.trim();
		if (value.length() == 0) {
			return "";
		}
		for (Button button : buttonList) {
			if (button.getValue().equals(value) || button.getName().equalsIgnoreCase(value)) {
				return button.getValue();
			}
		}
		if ("Y".equalsIgnoreCase(value) || "YES".equalsIgnoreCase(value) || "TRUE".equalsIgnoreCase(value)) {
			return findRadioOptionValue(buttonList, "Y", "Yes");
		}
		if ("N".equalsIgnoreCase(value) || "NO".equalsIgnoreCase(value) || "FALSE".equalsIgnoreCase(value)) {
			return findRadioOptionValue(buttonList, "N", "No");
		}
		if ("NA".equalsIgnoreCase(value) || "N/A".equalsIgnoreCase(value)) {
			return findRadioOptionValue(buttonList, "N/A", "NA");
		}
		return value;
	}

	private static String findRadioOptionValue(List<Button> buttonList, String preferredValue, String label) {
		for (Button button : buttonList) {
			if (button.getValue().equals(preferredValue) || button.getName().equalsIgnoreCase(label)) {
				return button.getValue();
			}
		}
		return preferredValue;
	}

	private static PDAppearanceStream createCheckBoxAppearanceStream(final PDDocument document,
			PDAnnotationWidget widget, boolean on) throws IOException {
		PDRectangle rect = widget.getRectangle();
		PDAppearanceCharacteristicsDictionary appearanceCharacteristics = widget.getAppearanceCharacteristics();
		PDAppearanceStream yesAP = new PDAppearanceStream(document.getDocument().createCOSStream());
		yesAP.setBoundingBox(new PDRectangle(rect.getWidth(), rect.getHeight()));
		yesAP.setResources(new PDResources());
		PDGamma backgroundColor = getBackgroundColor(appearanceCharacteristics);
		PDGamma borderColor = getBorderColor(appearanceCharacteristics);
		float lineWidth = getLineWidth(widget);
		StringBuilder appearance = new StringBuilder();
		appearance.append("q\n");
		appearance.append(toPdfColor(backgroundColor, false));
		appearance.append(rectangleCommand(0, 0, rect.getWidth(), rect.getHeight()));
		appearance.append("f\n");
		appearance.append(toPdfColor(borderColor, true));
		appearance.append(formatPdfNumber(lineWidth)).append(" w\n");
		appearance.append(rectangleCommand(lineWidth / 2, lineWidth / 2, rect.getWidth() - lineWidth,
				rect.getHeight() - lineWidth));
		appearance.append("S\n");

		if (on) {
			float startX = lineWidth + rect.getWidth() * 0.15f;
			float startY = rect.getHeight() * 0.45f;
			float midX = rect.getWidth() * 0.42f;
			float midY = lineWidth + rect.getHeight() * 0.18f;
			float endX = rect.getWidth() - lineWidth - rect.getWidth() * 0.12f;
			float endY = rect.getHeight() - lineWidth - rect.getHeight() * 0.18f;

			appearance.append("1 J\n1 j\n");
			appearance.append(formatPdfNumber(Math.max(1f, lineWidth * 1.5f))).append(" w\n");
			appearance.append(formatPdfNumber(startX)).append(" ").append(formatPdfNumber(startY)).append(" m\n");
			appearance.append(formatPdfNumber(midX)).append(" ").append(formatPdfNumber(midY)).append(" l\n");
			appearance.append(formatPdfNumber(endX)).append(" ").append(formatPdfNumber(endY)).append(" l\n");
			appearance.append("S\n");
		}

		appearance.append("Q\n");
		writeAppearanceStream(yesAP, appearance.toString());
		return yesAP;
	}

	private static PDAppearanceStream createRadioButtonAppearanceStream(final PDDocument document,
			PDAnnotationWidget widget, boolean on) throws IOException {
		PDRectangle rect = widget.getRectangle();
		PDAppearanceStream onAP = new PDAppearanceStream(document.getDocument().createCOSStream());
		onAP.setBoundingBox(new PDRectangle(rect.getWidth(), rect.getHeight()));
		onAP.setResources(new PDResources());
		PDAppearanceCharacteristicsDictionary appearanceCharacteristics = widget.getAppearanceCharacteristics();
		PDGamma backgroundColor = getBackgroundColor(appearanceCharacteristics);
		PDGamma borderColor = getBorderColor(appearanceCharacteristics);
		float lineWidth = getLineWidth(widget);
		float radius = Math.min(rect.getWidth() / 2, rect.getHeight() / 2);

		StringBuilder appearance = new StringBuilder();
		appearance.append("q\n");
		appearance.append(toPdfColor(backgroundColor, false));
		appearance.append(circlePath(rect.getWidth() / 2, rect.getHeight() / 2, radius));
		appearance.append("f\n");
		appearance.append(toPdfColor(borderColor, true));
		appearance.append(formatPdfNumber(lineWidth)).append(" w\n");
		appearance.append(circlePath(rect.getWidth() / 2, rect.getHeight() / 2, radius - lineWidth / 2));
		appearance.append("S\n");

		if (on) {
			appearance.append("0 0 0 rg\n");
			appearance.append(circlePath(rect.getWidth() / 2, rect.getHeight() / 2, (radius - lineWidth) / 2));
			appearance.append("f\n");
		}

		appearance.append("Q\n");
		writeAppearanceStream(onAP, appearance.toString());
		return onAP;
	}

	private static float getLineWidth(PDAnnotationWidget widget) {
		PDBorderStyleDictionary bs = widget.getBorderStyle();
		if (bs != null) {
			return bs.getWidth();
		}
		return 1;
	}

	private static String circlePath(float x, float y, float r) {
		float magic = r * 0.551784f;
		StringBuilder builder = new StringBuilder();
		builder.append(formatPdfNumber(x)).append(" ").append(formatPdfNumber(y + r)).append(" m\n");
		builder.append(formatPdfNumber(x + magic)).append(" ").append(formatPdfNumber(y + r)).append(" ")
				.append(formatPdfNumber(x + r)).append(" ").append(formatPdfNumber(y + magic)).append(" ")
				.append(formatPdfNumber(x + r)).append(" ").append(formatPdfNumber(y)).append(" c\n");
		builder.append(formatPdfNumber(x + r)).append(" ").append(formatPdfNumber(y - magic)).append(" ")
				.append(formatPdfNumber(x + magic)).append(" ").append(formatPdfNumber(y - r)).append(" ")
				.append(formatPdfNumber(x)).append(" ").append(formatPdfNumber(y - r)).append(" c\n");
		builder.append(formatPdfNumber(x - magic)).append(" ").append(formatPdfNumber(y - r)).append(" ")
				.append(formatPdfNumber(x - r)).append(" ").append(formatPdfNumber(y - magic)).append(" ")
				.append(formatPdfNumber(x - r)).append(" ").append(formatPdfNumber(y)).append(" c\n");
		builder.append(formatPdfNumber(x - r)).append(" ").append(formatPdfNumber(y + magic)).append(" ")
				.append(formatPdfNumber(x - magic)).append(" ").append(formatPdfNumber(y + r)).append(" ")
				.append(formatPdfNumber(x)).append(" ").append(formatPdfNumber(y + r)).append(" c\n");
		builder.append("h\n");
		return builder.toString();
	}

	private static PDRectangle createRectangle(float x, float y, float width, float height) {
		PDRectangle rectangle = new PDRectangle();
		rectangle.setLowerLeftX(x);
		rectangle.setLowerLeftY(y);
		rectangle.setUpperRightX(x + width);
		rectangle.setUpperRightY(y + height);
		return rectangle;
	}

	private static float getPageRight(PDPage page) {
		return page.getMediaBox().getWidth() - PAGE_MARGIN;
	}

	private static float getRadioGroupWidth(List<Button> buttonList) {
		float width = 0;
		for (Button button : buttonList) {
			if (width > 0) {
				width += RADIO_OPTION_GAP;
			}
			width += RADIO_BUTTON_SIZE + RADIO_LABEL_GAP
					+ getTextWidth(regularFont, button.getName(), RADIO_LABEL_FONT_SIZE);
		}
		return width;
	}

	private static float getRightAlignedRadioX(PDPage page, List<Button> buttonList) {
		return Math.max(PAGE_MARGIN, getPageRight(page) - RADIO_RIGHT_MARGIN - getRadioGroupWidth(buttonList));
	}

	private static float getTextWidth(PDFont font, String text, float fontSize) {
		if (text == null || text.length() == 0) {
			return 0;
		}

		try {
			return font.getStringWidth(text) / 1000f * fontSize;
		} catch (IOException e) {
			return text.length() * fontSize * 0.5f;
		}
	}

	private static float drawCoverageOptionsBlock(PDDocument document, PDAcroForm acroForm, PDPage page, float x,
			float y, String limitsFieldName, String limitsDefaultValue, String defenseGroupName,
			String defenseDefaultValue, String deductibleFieldName, String deductibleDefaultValue,
			String deductibleTypeGroupName, String deductibleTypeDefaultValue, String firstDollarDefenseFieldName,
			boolean firstDollarDefenseChecked) {
		String defenseValue = getDefenseExpenseRadioValue(defenseDefaultValue);
		String deductibleTypeValue = getDeductibleTypeRadioValue(deductibleTypeDefaultValue);

		addText(document, page, "Limits of Liability: $", x, y + 2, false);
		addField(acroForm, page, limitsFieldName, limitsDefaultValue, x + 105, y, 135, 15);
		addPlaceHolderText(document, page, LIMITS_PLACEHOLDER, x + 135, y - 11);
		addCoverageRadioButtons(document, acroForm, page, defenseGroupName, createDefenseExpenseButtons(),
				defenseValue, new float[] { x + 250, x + 415 }, y, new float[] { 105, 130 }, true);
		addRadioLabel(document, page, "OR", x + 382, y + 5, 20, true);
		y -= 44;

		addText(document, page, "Deductible: $", x, y + 2, false);
		addField(acroForm, page, deductibleFieldName, deductibleDefaultValue, x + 70, y, 110, 15);
		addCoverageRadioButtons(document, acroForm, page, deductibleTypeGroupName, createDeductibleTypeButtons(),
				deductibleTypeValue, new float[] { x + 195, x + 325 }, y, new float[] { 55, 95 }, false);
		addRadioLabel(document, page, "OR", x + 282, y + 5, 20, true);
		addCheckBox(document, acroForm, page, firstDollarDefenseFieldName, firstDollarDefenseChecked, x + 440, y);
		addFirstDollarDefenseLabel(document, page, x + 460, y + 10);
		return y - 40;
	}

	private static String getYesNoRadioValue(String value) {
		if ("Y".equalsIgnoreCase(value)) {
			return "Y";
		}
		if ("N".equalsIgnoreCase(value)) {
			return "N";
		}
		return "";
	}

	private static String getDefenseExpenseRadioValue(String value) {
		if ("DR".equalsIgnoreCase(value) || "N".equalsIgnoreCase(value)) {
			return "DR";
		}
		if ("DA".equalsIgnoreCase(value) || "Y".equalsIgnoreCase(value)) {
			return "DA";
		}
		return "";
	}

	private static String getDeductibleTypeRadioValue(String value) {
		if ("PC".equalsIgnoreCase(value) || "Y".equalsIgnoreCase(value) || "PER CLAIM".equalsIgnoreCase(value)) {
			return "PC";
		}
		if ("AA".equalsIgnoreCase(value) || "N".equalsIgnoreCase(value) || "ANNUAL AGGREGATE".equalsIgnoreCase(value)) {
			return "AA";
		}
		return "";
	}

	private static String getClaimOptionTypeRadioValue(String value) {
		if ("PC".equalsIgnoreCase(value) || "N".equalsIgnoreCase(value) || "NO".equalsIgnoreCase(value)
				|| "PER CLAIM".equalsIgnoreCase(value)) {
			return "PC";
		}
		if ("AA".equalsIgnoreCase(value) || "Y".equalsIgnoreCase(value) || "YES".equalsIgnoreCase(value)
				|| "ANNUAL".equalsIgnoreCase(value) || "ANNUAL AGGREGATE".equalsIgnoreCase(value)) {
			return "AA";
		}
		return "";
	}

	private static boolean isFirstDollarDefenseSelected(Map coverageMap) {
		return isTruthy(getBlankIfNull(coverageMap, "IsProfDefenceExpense"))
				|| isTruthy(getBlankIfNull(coverageMap, "IsDollarDefenseText"))
				|| isTruthy(getBlankIfNull(coverageMap, "IsDollarDefense"));
	}

	private static boolean isTruthy(String value) {
		String normalized = value == null ? "" : value.trim();
		return "Y".equalsIgnoreCase(normalized) || "YES".equalsIgnoreCase(normalized)
				|| "TRUE".equalsIgnoreCase(normalized) || "1".equals(normalized);
	}

	private static String getWebsiteAddress(Map policyMap, Map firmMap) {
		String[] websiteKeys = { "Website", "WebsiteAddress", "WebSite", "FirmWebsite", "AccountWebsite" };
		for (int i = 0; i < websiteKeys.length; i++) {
			String value = getBlankIfNull(policyMap, websiteKeys[i]);
			if (value.length() > 0 && !looksLikePhysicalAddress(value)) {
				return value;
			}
		}
		for (int i = 0; i < websiteKeys.length; i++) {
			String value = getBlankIfNull(firmMap, websiteKeys[i]);
			if (value.length() > 0 && !looksLikePhysicalAddress(value)) {
				return value;
			}
		}
		return "";
	}

	private static boolean looksLikePhysicalAddress(String value) {
		String normalized = value == null ? "" : value.trim().toLowerCase(Locale.US);
		if (normalized.length() == 0) {
			return false;
		}
		if (!normalized.matches(".*\\d+.*")) {
			return false;
		}
		return normalized.matches(".*\\b(st|street|ave|avenue|rd|road|dr|drive|ln|lane|blvd|boulevard|ct|court|cir|circle|way|pkwy|parkway|pl|place)\\b.*");
	}

	private static COSDictionary createFieldDictionary(String fieldType) {
		COSDictionary dictionary = new COSDictionary();
		dictionary.setItem(COSName.FT, COSName.getPDFName(fieldType));
		return dictionary;
	}

	private static void initializeWidgetAnnotation(COSDictionary dictionary) {
		dictionary.setItem(COSName.TYPE, COSName.getPDFName("Annot"));
		dictionary.setItem(COSName.SUBTYPE, COSName.getPDFName(WIDGET_SUBTYPE));
		dictionary.setInt(COSName.getPDFName("F"), ANNOTATION_PRINT_FLAG);
	}

	private static PDGamma createColor(float r, float g, float b) {
		PDGamma color = new PDGamma();
		color.setR(r);
		color.setG(g);
		color.setB(b);
		return color;
	}

	private static PDGamma getBorderColor(PDAppearanceCharacteristicsDictionary appearanceCharacteristics) {
		if (appearanceCharacteristics != null && appearanceCharacteristics.getBorderColour() != null) {
			return appearanceCharacteristics.getBorderColour();
		}
		return createColor(0, 0, 0);
	}

	private static PDGamma getBackgroundColor(PDAppearanceCharacteristicsDictionary appearanceCharacteristics) {
		if (appearanceCharacteristics != null && appearanceCharacteristics.getBackground() != null) {
			return appearanceCharacteristics.getBackground();
		}
		return createColor(1, 1, 1);
	}

	private static String toPdfColor(PDGamma color, boolean stroking) {
		return formatPdfNumber(color.getR()) + " " + formatPdfNumber(color.getG()) + " " + formatPdfNumber(color.getB())
				+ (stroking ? " RG\n" : " rg\n");
	}

	private static String rectangleCommand(float x, float y, float width, float height) {
		return formatPdfNumber(x) + " " + formatPdfNumber(y) + " " + formatPdfNumber(width) + " "
				+ formatPdfNumber(height) + " re\n";
	}

	private static String formatPdfNumber(float value) {
		String text = String.format(Locale.US, "%.4f", value);
		while (text.contains(".") && (text.endsWith("0") || text.endsWith("."))) {
			text = text.substring(0, text.length() - 1);
		}
		return text.isEmpty() ? "0" : text;
	}

	private static void writeAppearanceStream(PDAppearanceStream appearanceStream, String content) throws IOException {
		OutputStream outputStream = appearanceStream.getStream().createUnfilteredStream();
		try {
			outputStream.write(content.getBytes("ISO-8859-1"));
		} finally {
			outputStream.close();
		}
	}

	private static void addImageToPage(PDDocument pdDoc, PDPage pdPage, float x, float y, float width, float height) {
		try {
			BufferedImage bufferedImage = resolveLogoImage();
			if (bufferedImage == null) {
				logger.debug("Warning: ISMIE logo image not found. Skipping image draw.");
				return;
			}
			PDXObjectImage pdImage = new PDJpeg(pdDoc, bufferedImage);
			try (PDPageContentStream contentStream = new PDPageContentStream(pdDoc, pdPage, true, true, true)) {
				contentStream.drawXObject(pdImage, x, y, width, height);
			}
		} catch (Exception e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static BufferedImage resolveLogoImage() {
		String[] fileCandidates = {
				getWebAppPath("image/" + LOGO_IMAGE_NAME),
				getWebAppPath("images/" + LOGO_IMAGE_NAME),
				LOGO_IMAGE_PATH,
				"web/images/" + LOGO_IMAGE_NAME,
				"image/" + LOGO_IMAGE_NAME,
				"images/" + LOGO_IMAGE_NAME,
				FALLBACK_LOGO_IMAGE_PATH
		};
		for (int i = 0; i < fileCandidates.length; i++) {
			BufferedImage image = readLogoImage(fileCandidates[i]);
			if (image != null) {
				return image;
			}
		}

		String[] resourceCandidates = {
				"image/" + LOGO_IMAGE_NAME,
				"images/" + LOGO_IMAGE_NAME,
				"/" + "image/" + LOGO_IMAGE_NAME,
				"/" + "images/" + LOGO_IMAGE_NAME,
				LOGO_IMAGE_PATH,
				"/" + LOGO_IMAGE_PATH
		};
		for (int i = 0; i < resourceCandidates.length; i++) {
			BufferedImage image = readLogoResource(resourceCandidates[i]);
			if (image != null) {
				return image;
			}
		}
		return null;
	}

	private static String getWebAppPath(String childPath) {
		try {
			File classLocation = new File(GeneratePDFFormNew.class.getProtectionDomain().getCodeSource()
					.getLocation().toURI());
			if (!classLocation.isDirectory()) {
				return "";
			}
			File webInfDirectory = classLocation.getName().equalsIgnoreCase("classes")
					? classLocation.getParentFile() : null;
			if (webInfDirectory == null || !webInfDirectory.getName().equalsIgnoreCase("WEB-INF")) {
				return "";
			}
			File webRoot = webInfDirectory.getParentFile();
			return webRoot == null ? "" : new File(webRoot, childPath).getPath();
		} catch (Throwable e) {
			return "";
		}
	}

	private static BufferedImage readLogoImage(String path) {
		if (path == null || path.trim().length() == 0) {
			return null;
		}
		try {
			File logoFile = new File(path);
			return logoFile.exists() ? ImageIO.read(logoFile) : null;
		} catch (Exception e) {
			return null;
		}
	}

	private static BufferedImage readLogoResource(String resourcePath) {
		InputStream inputStream = null;
		try {
			inputStream = GeneratePDFFormNew.class.getResourceAsStream(resourcePath);
			if (inputStream == null && resourcePath.startsWith("/")) {
				inputStream = GeneratePDFFormNew.class.getClassLoader().getResourceAsStream(resourcePath.substring(1));
			}
			if (inputStream == null) {
				return null;
			}
			return ImageIO.read(inputStream);
		} catch (Exception e) {
			return null;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logPdfException("Exception closing logo resource stream", e);
				}
			}
		}
	}

	private static void drawFirmProfilePage(Context ctx, PDAcroForm acroForm, PDDocument document) {
		try {
			Map map = getMapIfPresent(ctx, "policy_freeform_01");
			float x = 20;
			float y = 740;
			PDPage page = new PDPage(PDPage.PAGE_SIZE_LETTER);
			document.addPage(page);

			addImageToPage(document, page, x, y, 150, 40);
			y -= 30;
			addCenteredText(document, page, "LAWYERS PROFESSIONAL LIABILITY APPLICATION", 306, y, boldFont, 14f);
			y -= 25;
			float noticeFontSize = 10.5f;
			float noticeLineHeight = 12.5f;
			y = addStyledWrappedText(document, page, x, y, 560, noticeFontSize, noticeLineHeight,
					textPart("NOTICE: COVERAGE FOR WHICH THIS APPLICATION IS MADE IS WRITTEN ON A CLAIMS MADE AND REPORTED BASIS MEANING, EXCEPT AS OTHERWISE PROVIDED, COVERAGE APPLIES ONLY TO", false),
					textPart("CLAIMS", true), textPart("FIRST MADE AGAINST THE", false),
					textPart("INSURED", true), textPart("AND REPORTED TO THE", false),
					textPart("INSURER", true), textPart("DURING THE", false),
					textPart("POLICY PERIOD", true),
					textPart("OR DURING ANY APPLICABLE EXTENDED REPORTING PERIOD.", false));

			y -= 10;
			y = addStyledWrappedText(document, page, x, y, 560, noticeFontSize, noticeLineHeight,
					textPart("CAREFULLY READ THE ENTIRE POLICY FOR WHICH THIS APPLICATION IS MADE. WORDS AND PHRASES WHICH ARE PRINTED IN", false),
					textPart("BOLD TYPEFACE", true),
					textPart("HAVE SPECIFIC MEANING AND ARE DEFINED IN SECTION IV. OF THE POLICY.", false));

			y -= 10;
			y = addStyledWrappedText(document, page, x, y, 560, noticeFontSize, noticeLineHeight,
					textPart("THE APPLICATION, ITS ATTACHMENTS AND ALL PREVIOUS APPLICATIONS AND THEIR ATTACHMENTS SHALL SERVE AS THE BASIS OF THE POLICY, AND SHALL BECOME PART OF SUCH POLICY SHOULD A POLICY BE ISSUED, AS IF PHYSICALLY ATTACHED. THE", false),
					textPart("INSURER", true),
					textPart("RELIES UPON THE APPLICATION IN ISSUING THE POLICY. COMPLETION OF THIS APPLICATION DOES NOT IN ANY WAY IMPLY SUCH COVERAGE UNDER THE POLICY. COVERAGE IS AFFORDED ONLY IF AND TO THE EXTENT INDICATED BY THE TERMS AND CONDITIONS OF THE POLICY IF ISSUED.", false));
			y -= 15;
			y = addWrappedText(document, page,
					"Wherever the word \"You, Your or Firm\" is used, it will be deemed to include all attorneys within the firm and any predecessor firms.",
					x, y, 560, boldFont, BODY_FONT_SIZE, 12f);

			y -= 15;
			PDRectangle rect = new PDRectangle();
			Color color = Color.LIGHT_GRAY;
			drawRect(document, page, color, rect, true, "FIRM PROFILE", x, y, 570, 15, x + 5, y + 4);

			y -= 25;
			addText(document, page, "1.  Firm Name:", x, y + 2, false);
			addField(acroForm, page, "firmName", getBlankIfNull(map, "AccountName"), x + 85, y, 485, 15);
			y -= 12;
			addPlaceHolderText(document, page, "Legal name of the Firm to be insured", x + 250, y + 1);

			y -= 25;
			addText(document, page, "Address:", x + 25, y + 2, false);
			addField(acroForm, page, "address", getBlankIfNull(map, "Street"), x + 85, y, 280, 15);
			addText(document, page, "County:", x + 380, y + 2, false);
			addField(acroForm, page, "county", getBlankIfNull(map, "CountyDesc"), x + 430, y, 140, 15);
			y -= 12;
			addPlaceHolderText(document, page, "Physical location of Principal office", x + 150, y + 1);

			y -= 25;
			addText(document, page, "Address Line 2:", x, y + 2, false);
			addField(acroForm, page, "address2", getBlankIfNull(map, "Address2"), x + 85, y, 485, 15);

			y -= 25;
			addText(document, page, "City:", x + 40, y + 2, false);
			addField(acroForm, page, "city", getBlankIfNull(map, "City"), x + 85, y, 140, 15);
			addText(document, page, "State:", x + 240, y + 2, false);
			addField(acroForm, page, "state", getBlankIfNull(map, "StateDesc"), x + 280, y, 100, 15);
			addText(document, page, "Zip:", x + 400, y + 2, false);
			addField(acroForm, page, "zip", getBlankIfNull(map, "Zip")+"-"+getBlankIfNull(map, "Zip4"), x + 430, y, 140, 15);

			y -= 30;
			addText(document, page, "2.  When would you like your insurance to start?", x, y + 2, false);
			addField(acroForm, page, "policyEffectiveDate", getBlankIfNull(map, "PolicyEffectiveDate"), x + 250, y, 150,
					15);
			y -= 12;
			addPlaceHolderText(document, page, "mm/dd/yyyy", x + 280, y + 1);

		} catch (Exception e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void drawRect(PDDocument document, PDPage page, Color color, PDRectangle rect, boolean fill,
			String label, float startX, float startY, float width, float height, float labelX, float labelY) {
		try (PDPageContentStream contentStream = new PDPageContentStream(document, page, true, true, true)) {
			contentStream.addRect(startX, startY, width, height);
			if (fill) {
				contentStream.setNonStrokingColor(color);
				contentStream.fill(1);
				contentStream.addRect(startX, startY, width, height);
				contentStream.setStrokingColor(Color.BLACK);
				contentStream.setLineWidth(0.5f);
				contentStream.stroke();
			} else {
				contentStream.setStrokingColor(color);
				contentStream.stroke();
			}
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(labelX, labelY);
			contentStream.setFont(boldFont, 11);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.drawString(label);
			contentStream.endText();
		} catch (Exception e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void drawInsuranceHistory(Context ctx, PDAcroForm acroForm, PDDocument document) {
		try {
			// Reuse the first page created by drawFirmProfilePage
			Map firmMap = getMapIfPresent(ctx, "firm_freeform_01");
			Map coverageMap = getMapIfPresent(ctx, "coverage_freeform_1");
			PDPage page = (PDPage) document.getDocumentCatalog().getAllPages().get(0);
			float x = 20;
			float y = 260;

			PDRectangle rect = new PDRectangle();
			Color color = Color.LIGHT_GRAY;
			drawRect(document, page, color, rect, true, "INSURANCE HISTORY", x, y, 570, 15, x + 5, y + 4);
			y -= 30;

			addText(document, page, "3.  Do you currently carry professional liability insurance?", x, y, false);
			addYesNoButtons(document, acroForm, page, "insurance_history_current_coverage",
					getBlankIfNull(firmMap, "IsFirmHaveLawyersLiabilityInsurance"), x + 350, y - 4);
			y -= 30;

			float currentX = addInlineTextSegment(document, page, "If \"", x, y, false);
			currentX = addInlineTextSegment(document, page, "Yes", currentX, y, true);
			addInlineTextSegment(document, page, "\", provide the information requested below:", currentX, y, false);
			y -= 25;

			addText(document, page, "Insurance Company (not broker/agent):", x, y + 2, false);
			addField(acroForm, page, "insuranceCompany", getBlankIfNull(coverageMap, "InsuranceCompanyNamePross"),
					x + 210, y, 160, 15);

			addText(document, page, "Policy Expiration Date:", x + 380, y + 2, false);
			addField(acroForm, page, "policyExpirationDate", getBlankIfNull(coverageMap, "PolicyExpirationDatePross"),
					x + 500, y, 70, 15);
			y -= 12;
			addPlaceHolderText(document, page, "mm/dd/yyyy", x + 505, y + 1);
			y -= 25;

			boolean isDollarDefense = isFirstDollarDefenseSelected(coverageMap);
			y = drawCoverageOptionsBlock(document, acroForm, page, x, y, "limitsOfLiability",
					getBlankIfNull(coverageMap, "LimitAmount"), "insurance_history_defense_expenses",
					getBlankIfNull(coverageMap, "IsClaimExpLiability"), "deductible",
					getBlankIfNull(coverageMap, "DeductibleAmount"), "insurance_history_deductible_type",
					getBlankIfNull(coverageMap, "IsPerClaim"), "IsProfDefenceExpense", isDollarDefense);

			addText(document, page, "Indicate the prior acts date (also known as retroactive date) for your policy:", x,
					y + 2, false);
			addField(acroForm, page, "priorActsDate", getBlankIfNull(coverageMap, "PriorActDatePross"), x + 380, y, 80,
					15);
			y -= 12;
			addPlaceHolderText(document, page, "MM/DD/YYYY", x + 385, y + 1);

			addText(document, page, "Premium: $", x + 470, y + 13, false);
			addField(acroForm, page, "ProInsPremium", getBlankIfNull(coverageMap, "ProInsPremium"), x + 530, y + 10, 40, 15);
			y -= 25;

			currentX = addInlineTextSegment(document, page, "If your current policy has ", x, y + 2, false);
			currentX = addInlineTextSegment(document, page, "Full prior acts", currentX, y + 2, true);
			addInlineTextSegment(document, page,
					", please provide the number of years of continuous insurance:", currentX, y + 2, false);
			addField(acroForm, page, "continuousInsuranceYears", getBlankIfNull(coverageMap, "FirmYear"), x + 530, y,
					40, 15);

		} catch (Exception e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void drawCoverageAndAttorneysPage(Context ctx, PDAcroForm acroForm, PDDocument document) {
		try {
			Map coverageMap = getMapIfPresent(ctx, "coverage_freeform_2");
			List attorneyList = (List) ctx.get("attorneys_firm_list_01");
			if (attorneyList == null) {
				attorneyList = Collections.emptyList();
			}

			float x = 20;
			float y = 740;
			PDPage page = new PDPage(PDPage.PAGE_SIZE_LETTER);
			document.addPage(page);

			// 1. COVERAGE SECTION
			PDRectangle rect = new PDRectangle();
			Color color = Color.LIGHT_GRAY;
			drawRect(document, page, color, rect, true, "COVERAGE", x, y, 570, 15, x + 5, y + 4);

			y -= 25;
			addText(document, page, "4.  Indicate your desired coverage selection:", x, y, false);

			y -= 25;
			boolean isDollarDefense = isFirstDollarDefenseSelected(coverageMap);
			y = drawCoverageOptionsBlock(document, acroForm, page, x, y, "desiredLimitsOfLiability",
					getBlankIfNull(coverageMap, "LimitAmount"), "coverage_defense_expenses",
					getBlankIfNull(coverageMap, "IsClaimExpensesType"), "desiredDeductible",
					getBlankIfNull(coverageMap, "DeductibleAmount"), "coverage_deductible_type",
					getClaimOptionTypeRadioValue(getBlankIfNull(coverageMap, "IsClaimOptionType")),
					"desiredFirstDollarDefense", isDollarDefense);

			// 2. ATTORNEYS SECTION
			drawRect(document, page, color, rect, true, "ATTORNEYS", x, y, 570, 15, x + 5, y + 4);
			y -= 25;

			addText(document, page, "5.  List all of your attorneys:", x, y, false);
			y -= 25;

			// Draw Table Headers
			int[] colWidths = { 120, 110, 90, 80, 70, 90 };
			String[] headers = { "Attorney Name", "Attorney Designation", "States Licensed", "Annual Hours",
					"Date Joined", "# Years in Practice" };
			float attorneyHeaderHeight = 32;
			float attorneyRowHeight = 24;
			int attorneyFieldXPadding = 7;
			int attorneyFieldYPadding = 4;
			int attorneyFieldHeight = (int) attorneyRowHeight - (attorneyFieldYPadding * 2);
			int row = 0;
			PDPage attorneyPage = page;
			float attorneyTableTopY = y + 12;
			while (row < ATTORNEY_ROSTER_ROW_COUNT) {
				if (row > 0) {
					attorneyPage = createPage(document);
					drawRect(document, attorneyPage, color, rect, true, "ATTORNEYS (continued)", x, 740, 570, 15,
							x + 5, 744);
					attorneyTableTopY = 705;
				}
				int rowsThisPage = Math.min(ATTORNEY_ROSTER_ROW_COUNT - row,
						Math.max(1, (int) ((attorneyTableTopY - attorneyHeaderHeight - 65) / attorneyRowHeight)));
				drawColumnTable(document, attorneyPage, x, attorneyTableTopY, colWidths, rowsThisPage,
						attorneyHeaderHeight, attorneyRowHeight);

				float currentX = x;
				for (int i = 0; i < headers.length; i++) {
					addTextInBox(document, attorneyPage, headers[i], currentX + 5, attorneyTableTopY - 14,
							colWidths[i] - 10, true);
					currentX += colWidths[i];
				}

				for (int pageRow = 0; pageRow < rowsThisPage; pageRow++) {
					Map map = row < attorneyList.size() ? getMapFromList(attorneyList, row) : Collections.EMPTY_MAP;
					float rowTopY = attorneyTableTopY - attorneyHeaderHeight - (pageRow * attorneyRowHeight);
					float fieldY = rowTopY - attorneyRowHeight + attorneyFieldYPadding;
					currentX = x;
					addField(acroForm, attorneyPage, "AttorneyName_" + row, getBlankIfNull(map, "AttorneyName"),
							currentX + attorneyFieldXPadding, fieldY, colWidths[0] - (attorneyFieldXPadding * 2),
							attorneyFieldHeight);
					currentX += colWidths[0];

					addField(acroForm, attorneyPage, "AttorneyDesignation_" + row,
							getBlankIfNull(map, "AttorneyDesg"), currentX + attorneyFieldXPadding, fieldY,
							colWidths[1] - (attorneyFieldXPadding * 2), attorneyFieldHeight);
					currentX += colWidths[1];

					addField(acroForm, attorneyPage, "LicensedStates_" + row,
							getBlankIfNull(map, "LicensedStates"), currentX + attorneyFieldXPadding, fieldY,
							colWidths[2] - (attorneyFieldXPadding * 2), attorneyFieldHeight);
					currentX += colWidths[2];

					addField(acroForm, attorneyPage, "AnnualWorkedHours_" + row,
							getBlankIfNull(map, "AnnualWorkedHours"), currentX + attorneyFieldXPadding, fieldY,
							colWidths[3] - (attorneyFieldXPadding * 2), attorneyFieldHeight);
					currentX += colWidths[3];

					addField(acroForm, attorneyPage, "AttorneyPriorActDate_" + row,
							getBlankIfNull(map, "AttorneyPriorActDate"), currentX + attorneyFieldXPadding, fieldY,
							colWidths[4] - (attorneyFieldXPadding * 2), attorneyFieldHeight);
					currentX += colWidths[4];

					addField(acroForm, attorneyPage, "NumberOfYearsInPractice_" + row,
							getBlankIfNull(map, "NumberOfYearsInPractice"), currentX + attorneyFieldXPadding, fieldY,
							colWidths[5] - (attorneyFieldXPadding * 2), attorneyFieldHeight);
					row++;
				}
				y = attorneyTableTopY - attorneyHeaderHeight - (rowsThisPage * attorneyRowHeight);
				page = attorneyPage;
			}

			y -= 15;
			addText(document, attorneyPage, "*Attorney Designations:", x, y, boldFont, BODY_FONT_SIZE);
			y -= 10;
			addText(document, attorneyPage,
					"A=Associate, E=Employee, IC=Independent Contractor, OC=Of Counsel, P=Partner, RP=Retired Partner, O=Owner",
					x, y, regularFont, BODY_FONT_SIZE);
			y -= 10;
			addText(document, attorneyPage, "S=Solo Practitioner", x, y, regularFont, BODY_FONT_SIZE);

		} catch (Exception e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void drawAreaOfPracticePage(Context ctx, PDAcroForm acroForm, PDDocument document) {
		try {
			float x = 20;
			float y = 740;
			List aoplist1 = (List) ctx.get("aopData_list_01");
			List aoplist2 = (List) ctx.get("aopData_list_02");
			if (aoplist1 == null) {
				aoplist1 = Collections.emptyList();
			}
			if (aoplist2 == null) {
				aoplist2 = Collections.emptyList();
			}
			PDPage page = new PDPage(PDPage.PAGE_SIZE_LETTER);
			document.addPage(page);

			// AREA OF PRACTICE HEADER
			PDRectangle rect = new PDRectangle();
			Color color = Color.LIGHT_GRAY;
			drawRect(document, page, color, rect, true, "AREA OF PRACTICE", x, y, 570, 15, x + 5, y + 4);

			y -= 20;
			addText(document, page, "6.", x, y, regularFont, BODY_FONT_SIZE);
			addText(document, page, "a.", x + 25, y, regularFont, BODY_FONT_SIZE);
			addText(document, page, "Enter the percentage of revenue for each area below in the past fiscal year.",
					x + 45, y, regularFont, BODY_FONT_SIZE);
			y -= 12;
			addText(document, page,
					"Indicate percentages in whole numbers next to the type of law the firm practices:", x + 45, y,
					regularFont, BODY_FONT_SIZE);
			y -= 14;

			// Define left and right columns (abbreviated for brevity, add the rest as
			// needed)
			String[] leftCol = { "Administrative or Regulatory", "Admiralty and Maritime",
					"Alternate Dispute Resolution", "Antitrust", "Appellate Practice", "Aviation",
					"Bankruptcy / Insolvency and Reorganization (1)", "Civil Litigation - Defense",
					"Civil Litigation - Plaintiff (7)", "Civil Rights or Discrimination",
					"Collections / Repossession (2)", "Communications / Media", "Construction",
					"Construction Litigation - Defense", "Construction Litigation - Plaintiff (7)",
					"Copyright / Trademark (3)", "Corporate / Commercial / Business (11)",
					"Corporate / Commercial / Business Litigation - Defense",
					"Corporate / Commercial / Business Litigation - Plaintiff (7)", "Creditor Debtor Rights",
					"Criminal Defense", "Education", "Elder Law", "Employee Benefits (ERISA)", "Employment",
					"Entertainment / Sports", "Environmental", "Family Law (4)",
					"Financial Institution / Banking / Finance (5)", "Government / Municipal / Zoning (6)",
					"Healthcare", "Immigration", "Insurance Defense", "International" };

			String[] rightCol = { "Investment Counseling / Money Management", "Juvenile or Guardianship",
					"Labor - Management", "Labor - Union",
					"Litigation - Other - Defense (please describe):",
					"Litigation - Other - Plaintiff (7) (please describe):",
					"Mass Tort Litigation / Class Actions - Defense",
					"Mass Tort Litigation / Class Actions - Plaintiff (7)", "Medical Malpractice - Defense",
					"Medical Malpractice - Plaintiff (7)", "Mergers and Acquisitions", "Natural Resources",
					"Non-Medical Malpractice - Defense", "Non-Medical Malpractice - Plaintiff (7)",
					"Non-Profit / Charities", "Patent", "Personal Injury Litigation - Defense",
					"Personal Injury Litigation - Plaintiff (7)", "Product Liability Litigation - Defense",
					"Product Liability Litigation - Plaintiff (7)", "Real Estate - Commercial (8)",
					"Real Estate - Residential (8)", "Securities and/or Security Litigation",
					"Social Security / Disability", "Tax (9)", "Traffic or DUI/DWI Defense",
					"Trusts / Estates / Wills / Probate (10)", "Utilities", "Workers Compensation - Defense",
					"Workers Compensation - Plaintiff", "Other (please describe):" };

			float tableX = x ;
			float tableTopY = y + 11;
			float headerHeight = 15f;
			float aopRowHeight = 15f;
			float aopLineHeight = BODY_FONT_SIZE;
			float leftLabelWidth = 215f;
			float rightLabelWidth = 230f;
			addText(document, page, "AREA OF PRACTICE", tableX + 48, tableTopY - 11, boldFont, 9f);
			addText(document, page, "% OF REVENUE", tableX + 218, tableTopY - 11, boldFont, 9f);
			addText(document, page, "AREA OF PRACTICE", tableX + 310, tableTopY - 11, boldFont, 9f);
			addText(document, page, "% OF REVENUE", tableX + 482, tableTopY - 11, boldFont, 9f);

			float leftRowTextY = tableTopY - headerHeight - 10;
			float rightRowTextY = leftRowTextY;
			float leftFieldX = tableX + 246;
			float rightFieldX = tableX + 524;
			float leftPercentX = tableX + 290;
			float rightPercentX = tableX + 568;

			String litigationDefenseOtherDescription = "";
			String litigationPlaintiffOtherDescription = "";
			String otherAopDesc = "";
			String[] areaTotalFields = new String[leftCol.length + rightCol.length];
			String[] areaTotalValues = new String[leftCol.length + rightCol.length];
			for (int i = 0; i < leftCol.length; i++) {
				Map map = getAopMapByLabel(aoplist1, aoplist2, leftCol[i]);
				int lineCount = Math.max(1, wrapText(regularFont, leftCol[i], BODY_FONT_SIZE, leftLabelWidth).size());
				addTextInBox(document, page, leftCol[i], tableX + 5, leftRowTextY, leftLabelWidth, false);
				String leftFieldName = "percentage_left_" + i;
				String leftValue = formatWholeNumber(getAopPercentage(map));
				addWholeNumberField(acroForm, page, leftFieldName, leftValue, leftFieldX, leftRowTextY - 10, 40, 12);
				addText(document, page, "%", leftPercentX, leftRowTextY - 1, regularFont, 9f);
				areaTotalFields[i] = leftFieldName;
				areaTotalValues[i] = leftValue;
				leftRowTextY -= Math.max(aopRowHeight, (lineCount * aopLineHeight) + 3);
			}
			for (int i = 0; i < rightCol.length; i++) {
				Map map = getAopMapByLabel(aoplist1, aoplist2, rightCol[i]);
				if (i == 4) {
					litigationDefenseOtherDescription = getBlankIfNull(map, "AOPCommentDesc");
				} else if (i == 5) {
					litigationPlaintiffOtherDescription = getBlankIfNull(map, "AOPCommentDesc");
				}
				if ("25".equals(getBlankIfNull(map, "AOPKey"))) {
					otherAopDesc = getBlankIfNull(map, "AOPCommentDesc");
				}
				int lineCount = Math.max(1, wrapText(regularFont, rightCol[i], BODY_FONT_SIZE, rightLabelWidth).size());
				addTextInBox(document, page, rightCol[i], tableX + 300, rightRowTextY, rightLabelWidth, false);
				String rightFieldName = "percentage_right_" + i;
				String rightValue = formatWholeNumber(getAopPercentage(map));
				addWholeNumberField(acroForm, page, rightFieldName, rightValue, rightFieldX, rightRowTextY - 10, 40, 12);
				addText(document, page, "%", rightPercentX, rightRowTextY - 1, regularFont, 9f);
				areaTotalFields[leftCol.length + i] = rightFieldName;
				areaTotalValues[leftCol.length + i] = rightValue;
				rightRowTextY -= Math.max(aopRowHeight, (lineCount * aopLineHeight) + 3);
			}

			float totalRowY = rightRowTextY - 2;
			addText(document, page, "TOTAL (must equal 100%)", tableX + 300, totalRowY, boldFont, 9f);
			addCalculatedTotalField(acroForm, page, "areaPracticeTotalPercent", sumFieldDefaults(areaTotalValues),
					rightFieldX, totalRowY - 10, 40, 12, areaTotalFields);
			addText(document, page, "%", rightPercentX, totalRowY - 1, regularFont, 9f);

			y = Math.min(leftRowTextY, totalRowY - aopRowHeight) - 8;
			y = addAreaPracticeDescriptionFields(document, acroForm, page, x, y,
					new String[] { "Litigation defense other Description :",
							"Litigation plaintiff other Description :", "Other Aop Description :" },
					new String[] { "areaPracticeLitigationDefenseOtherDescription",
							"areaPracticeLitigationPlaintiffOtherDescription", "areaPracticeOtherAopDescription" },
					new String[] { litigationDefenseOtherDescription, litigationPlaintiffOtherDescription,
							otherAopDesc });
			y -= 10;
			float currentX = addInlineTextSegment(document, page,
					"If you practice in any area(s) above with a numerical notation(s), complete the associated ",
					x + 15, y + 2, false);
			currentX = addInlineTextSegment(document, page, "Supplement", currentX, y + 2, true);
			addInlineTextSegment(document, page, " as follows:", currentX, y + 2, false);

			y -= 42;
			float supplementListFontSize = 9f;
			float supplementBoxX = x + 15;
			float supplementBoxY = y;
			drawFilledBox(document, page, supplementBoxX, supplementBoxY, 555, 40, Color.LIGHT_GRAY);
			addText(document, page, "(1) Bankruptcy", supplementBoxX + 10, supplementBoxY + 28, regularFont,
					supplementListFontSize);
			addText(document, page, "(4) Family Law", supplementBoxX + 150, supplementBoxY + 28, regularFont,
					supplementListFontSize);
			addText(document, page, "(7) Plaintiff Litigation", supplementBoxX + 275, supplementBoxY + 28, regularFont,
					supplementListFontSize);
			addText(document, page, "(10) Trusts, Wills, Estate, Probate", supplementBoxX + 405, supplementBoxY + 28,
					regularFont, supplementListFontSize);
			addText(document, page, "(2) Collections / Repossession", supplementBoxX + 10, supplementBoxY + 16,
					regularFont, supplementListFontSize);
			addText(document, page, "(5) Financial Institution", supplementBoxX + 150, supplementBoxY + 16, regularFont,
					supplementListFontSize);
			addText(document, page, "(8) Real Estate", supplementBoxX + 275, supplementBoxY + 16, regularFont,
					supplementListFontSize);
			addText(document, page, "(11) Corporate/Commercial/Business", supplementBoxX + 405, supplementBoxY + 16,
					regularFont, supplementListFontSize);
			addText(document, page, "(3) Copyright / Trademark", supplementBoxX + 10, supplementBoxY + 4, regularFont,
					supplementListFontSize);
			addText(document, page, "(6) Government", supplementBoxX + 150, supplementBoxY + 4, regularFont,
					supplementListFontSize);
			addText(document, page, "(9) Tax", supplementBoxX + 275, supplementBoxY + 4, regularFont,
					supplementListFontSize);

		} catch (Exception e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void drawAboutYourFirmAndPracticeManagementPage(Context ctx, PDAcroForm acroForm,
			PDDocument document) {
		try {
			Map firmMap = getMapIfPresent(ctx, "firm_freeform_01");
			float x = 20;
			float y = 765;
			PDPage page = new PDPage(PDPage.PAGE_SIZE_LETTER);
			document.addPage(page);

			addText(document, page, "6. b.", x, y, false);
			y = addWrappedText(document, page,
					"If any aspect of your practice or professional services relates to cannabis or the cannabis industry in any way please",
					x + 20, y, 510, false);
			y += 10;
			float cannabisX = addInlineTextSegment(document, page, "complete the ", x + 55, y, false);
			cannabisX = addInlineTextSegment(document, page, "Cannabis", cannabisX, y, true);
			addInlineTextSegment(document, page, " Supplement.", cannabisX, y, false);

			y -= 26;
			// ABOUT YOUR FIRM HEADER
			PDRectangle rect = new PDRectangle();
			Color color = Color.LIGHT_GRAY;
			drawRect(document, page, color, rect, true, "ABOUT YOUR FIRM", x, y, 570, 15, x + 5, y + 4);

			y -= 24;
			addText(document, page, "7. What year was your firm established?", x, y + 2, false);
			addField(acroForm, page, "yearEstablished", getBlankIfNull(firmMap, "YearOfFirmEstablished"), x + 450, y,
					70, 15);

			y -= 25;
			addText(document, page, "8. What was your revenue last year?", x, y + 2, false);

			y -= 20;
			addPositionedRadioButtons(document, acroForm, page, "annualRevenueSequence", createRevenueButtons(),
					getBlankIfNull(firmMap, "AnnualRevenueSequence"),
					new float[] { x + 55, x + 230, x + 405, x + 55, x + 230, x + 405, x + 55, x + 230 },
					new float[] { y, y, y, y - 22, y - 22, y - 22, y - 44, y - 44 });

			y -= 73;
			addText(document, page,
					"9. What is the number of non attorney support staff (full and part time combined)?", x, y + 2,
					false);
			addField(acroForm, page, "TotalNumOfNonAttorneyStaff",
					getBlankIfNull(firmMap, "TotalNumOfNonAttorneyStaff"), x + 500, y, 40, 15);

			y -= 25;
			addText(document, page, "10. Are there any other entities under which you provide professional services?",
					x, y + 2, false);
			addYesNoButtons(document, acroForm, page, "IsApplicantProvidesLegalServices",
					getBlankIfNull(firmMap, "IsApplicantProvidesLegalServices"), x + 450, y);

			y -= 15;
			addPageFiveIfYesText(document, page, x + 15, y,
					", please list the name of the entity and the services provided:");
//            addText(document, page, "If \"Yes\", please list the name of the entity and the services provided:", x + 15, y + 2, false);
			addField(acroForm, page, "ApplicantProvidesLegalServicesDesc",
					getBlankIfNull(firmMap, "ApplicantProvidesLegalServicesDesc"), x + 15, y - 16, 555, 15);

			y -= 42;
			float question11Y = y;
			addText(document, page, "11. If you are a solo practitioner, do you have a backup attorney in case you are",
					x, y + 2, false);
			y -= 12;
			addText(document, page, "unable or unavailable to work for periods of time?", x + 25, y + 2, false);
			addYesNoButtons(document, acroForm, page, "IsFirmHaveBackupAttorney",
					getBlankIfNull(firmMap, "IsFirmHaveBackupAttorney"), x + 450, question11Y);

			y -= 12;
			addText(document, page, "12. Have you acquired or merged with another firm in the past 10 years?", x, y + 2,
					false);
			addYesNoButtons(document, acroForm, page, "IsFirmMergedWithOtherFirm",
					getBlankIfNull(firmMap, "IsFirmMergedWithOtherFirm"), x + 450, y);

			y -= 20;
			addText(document, page, "a.", x + 15, y, regularFont, PAGE_FIVE_FONT_SIZE);
			addPageFiveIfYesText(document, page, x + 35, y,
					", were you the majority successor in interest to the financial assets and liabilities of the");
//            addText(document, page, "a. If \"Yes\", were you the majority successor in interest to the financial assets and liabilities of the", x + 15, y + 2, false);
			y -= 13;
			addText(document, page, "acquired or merged firm?", x + 35, y + 2, false);
			addYesNoButtons(document, acroForm, page, "IsApplIntToFinanAssests",
					getBlankIfNull(firmMap, "IsApplIntToFinanAssests"), x + 450, y);

			y -= 20;
			addText(document, page, "b.", x + 15, y, regularFont, PAGE_FIVE_FONT_SIZE);
			addPageFiveIfYesText(document, page, x + 35, y,
					", do you desire coverage for this entity as a predecessor firm?");
//            addText(document, page, "b. If \"Yes\", do you desire coverage for this entity as a predecessor firm?", x + 15, y + 2, false);
			addYesNoButtons(document, acroForm, page, "IsFirmCoverageForPreceedorFirms",
					getBlankIfNull(firmMap, "IsFirmCoverageForPreceedorFirms"), x + 450, y);

			y -= 16;
			addPageFiveIfYesTextWithBoldPhrase(document, page, x + 15, y, " to a or b above, complete the ",
					"Predecessor Firm Supplement", ".");
//            addPlaceHolderText(document, page, "If \"Yes\" to a or b above, complete the Predecessor Firm Supplement.", x + 15, y);

			y -= 30;

			// PRACTICE MANAGEMENT HEADER
			drawRect(document, page, color, rect, true, "PRACTICE MANAGEMENT", x, y, 570, 15, x + 5, y + 4);

			y -= 25;
			addText(document, page, "13. Do you maintain a central docket control or diary system?", x, y + 2, false);
			addYesNoButtons(document, acroForm, page, "IsFirmHaveIndepDockets",
					getBlankIfNull(firmMap, "IsFirmHaveIndepDockets"), x + 450, y);

			y -= 25;
			addText(document, page,
					"14. Does the firm have procedures for identifying and resolving potential or actual conflicts of interest",
					x, y + 2, false);
			y -= 12;
			addText(document, page, "including cross-checking of former, existing or potential clients?", x + 25, y + 2,
					false);
			addYesNoButtons(document, acroForm, page, "IsFirmHaveProcForFormersClients",
					getBlankIfNull(firmMap, "IsFirmHaveProcForFormersClients"), x + 450, y);

			y -= 25;
			addText(document, page,
					"15. Do you use engagement letters including fee agreements on all new matters undertaken by the firm?",
					x, y + 2, false);
			addYesNoButtons(document, acroForm, page, "IsFirmRequireEngagementLetter",
					getBlankIfNull(firmMap, "IsFirmRequireEngagementLetter"), x + 450, y);

			y -= 25;
			addText(document, page,
					"16. Are declinations or non-engagement letters, which include time sensitive dates, issued when declining or",
					x, y + 2, false);
			y -= 12;
			addText(document, page, "ceasing representation?", x + 25, y + 2, false);
			addYesNoButtons(document, acroForm, page, "IsNonEngagementLetterIssueToFirm",
					getBlankIfNull(firmMap, "IsNonEngagementLetterIssueToFirm"), x + 450, y);

			y -= 25;
			addText(document, page,
					"17. Have you moved to withdraw or been disengaged at the request of a client during the past two years?",
					x, y + 2, false);
			addYesNoButtons(document, acroForm, page, "withdrawOrDisengaged",
					getBlankIfNull(firmMap, "withdrawOrDisengaged"), x + 450, y);

			y -= 25;
			addText(document, page,
					"18. In the past three years have you initiated lawsuits or arbitration procedures to enforce the collection of",
					x, y + 2, false);
			y -= 12;
			addText(document, page, "unpaid fees of any client?", x + 25, y + 2, false);
			addYesNoButtons(document, acroForm, page, "IsApplInitiatedLawsuitForFirm",
					getBlankIfNull(firmMap, "IsApplInitiatedLawsuitForFirm"), x + 450, y);
			y -= 15;
			addPageFiveIfYesTextWithBoldPhrase(document, page, x + 15, y, ", please complete the ",
					"Fee Suit Supplement", ".");
//            addText(document, page, "If \"Yes\", please complete the Fee Suit Supplement.", x + 15, y + 2, false);

			y -= 25;
			addText(document, page,
					"19. Do you provide investment advice, make discretionary investments or have discretionary control of client",
					x, y + 2, false);
			y -= 12;
			addText(document, page, "funds?", x + 25, y + 2, false);
			addYesNoButtons(document, acroForm, page, "IsFirmProvideInvestmentAdvice",
					getBlankIfNull(firmMap, "IsFirmProvideInvestmentAdvice"), x + 450, y);
			y -= 15;
			addPageFiveIfYesText(document, page, x + 15, y, ", please explain.");
			addField(acroForm, page, "FirmInvestmentAdviceDesc", getBlankIfNull(firmMap, "FirmInvestmentAdviceDesc"),
					x + 15, y - 16, 555, 15);

		} catch (Exception e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	// Helper method to quickly generate Yes/No radio groups to save space
	private static void addYesNoButtons(PDDocument document, PDAcroForm acroForm, PDPage page, String groupName,
			String defaultValue, float x, float y) {
		List<Button> buttons = createYesNoButtons();
		addRadioButtons(document, acroForm, page, groupName, buttons, defaultValue,
				getRightAlignedRadioX(page, buttons), y);
	}

	private static void drawSecuritiesAndSharedOfficePage(Context ctx, PDAcroForm acroForm, PDDocument document) {
		try {
			float x = 20;
			float y = 765;
			Map firmMap = getMapIfPresent(ctx, "firm_freeform_01");
			PDPage page = new PDPage(PDPage.PAGE_SIZE_LETTER);
			document.addPage(page);

			float questionTopY = y;
			addPageFiveText(document, page, "20.", x, y, false);
			y = addPageFiveWrappedText(document, page,
					"At any time during the past three years has any client of the firm represented more than 50% of the firm's annual revenue?",
					x + 25, y, 400);
			addYesNoButtons(document, acroForm, page, "IsFirmHaveClientMoreThan25PercentOfBilling",
					getBlankIfNull(firmMap, "IsFirmHaveClientMoreThan25PercentOfBilling"), x + 450, questionTopY);

			y -= 13;
			float revenueClientTableX = x + 15;
			float revenueClientTableTopY = y + 10;
			float revenueClientHeaderHeight = 20;
			float revenueClientRowHeight = 20;
			int[] revenueClientWidths = { 160, 160, 80, 80 };
			drawColumnTable(document, page, revenueClientTableX, revenueClientTableTopY, revenueClientWidths, 2,
					revenueClientHeaderHeight, revenueClientRowHeight);

			float headerY = revenueClientTableTopY - 14;
			addPageFiveText(document, page, "Client Name:", revenueClientTableX + 5, headerY, false);
			addPageFiveText(document, page, "Services Provided:", revenueClientTableX + revenueClientWidths[0] + 5,
					headerY, false);
			addPageFiveText(document, page, "Percentage",
					revenueClientTableX + revenueClientWidths[0] + revenueClientWidths[1] + 5, headerY, false);
			addPageFiveText(document, page, "Year:",
					revenueClientTableX + revenueClientWidths[0] + revenueClientWidths[1] + revenueClientWidths[2] + 5,
					headerY, false);

			float rowOneFieldY = revenueClientTableTopY - revenueClientHeaderHeight - 15;
			float rowTwoFieldY = rowOneFieldY - revenueClientRowHeight;
			int fieldHeight = 12;
			addField(acroForm, page, "ClientNameFirstLargestRevenueClient",
					getBlankIfNull(firmMap, "ClientNameFirstLargestRevenueClient"), revenueClientTableX + 5,
					rowOneFieldY, revenueClientWidths[0] - 10, fieldHeight);
			addField(acroForm, page, "ServicesRenderedFirstLargestRevenueClient",
					getBlankIfNull(firmMap, "ServicesRenderedFirstLargestRevenueClient"),
					revenueClientTableX + revenueClientWidths[0] + 5, rowOneFieldY, revenueClientWidths[1] - 10,
					fieldHeight);
			addField(acroForm, page, "PercentFromFirstLargestRevenueClient",
					getBlankIfNull(firmMap, "PercentFromFirstLargestRevenueClient"),
					revenueClientTableX + revenueClientWidths[0] + revenueClientWidths[1] + 5, rowOneFieldY,
					revenueClientWidths[2] - 10, fieldHeight);
			addField(acroForm, page, "DateRenderedFirstLargestRevenueClient",
					getBlankIfNull(firmMap, "DateRenderedFirstLargestRevenueClient"),
					revenueClientTableX + revenueClientWidths[0] + revenueClientWidths[1] + revenueClientWidths[2] + 5,
					rowOneFieldY, revenueClientWidths[3] - 10, fieldHeight);

			addField(acroForm, page, "ClientNameSecondLargestRevenueClient",
					getBlankIfNull(firmMap, "ClientNameSecondLargestRevenueClient"), revenueClientTableX + 5,
					rowTwoFieldY, revenueClientWidths[0] - 10, fieldHeight);
			addField(acroForm, page, "ServicesRenderedSecondLargestRevenueClient",
					getBlankIfNull(firmMap, "ServicesRenderedSecondLargestRevenueClient"),
					revenueClientTableX + revenueClientWidths[0] + 5, rowTwoFieldY, revenueClientWidths[1] - 10,
					fieldHeight);
			addField(acroForm, page, "PercentFromSecondLargestRevenueClient",
					getBlankIfNull(firmMap, "PercentFromSecondLargestRevenueClient"),
					revenueClientTableX + revenueClientWidths[0] + revenueClientWidths[1] + 5, rowTwoFieldY,
					revenueClientWidths[2] - 10, fieldHeight);
			addField(acroForm, page, "DateRenderedSecondLargestRevenueClient",
					getBlankIfNull(firmMap, "DateRenderedSecondLargestRevenueClient"),
					revenueClientTableX + revenueClientWidths[0] + revenueClientWidths[1] + revenueClientWidths[2] + 5,
					rowTwoFieldY, revenueClientWidths[3] - 10, fieldHeight);
			y = revenueClientTableTopY - revenueClientHeaderHeight - (2 * revenueClientRowHeight) - 22;
			float q21Y = y;
			y = addPageFiveNumberedWrappedText(document, page, "21.",
					"Do you have public figures as clients? (e.g. Entertainment, Politics or Sports)", x, y, 425);
			addYesNoButtons(document, acroForm, page, "IsFirmHaveClientInEntertainmentInd ",
					getBlankIfNull(firmMap, "IsFirmHaveClientInEntertainmentInd"), x + 450, q21Y);

			y -= 8;
			addPageFiveIfYesText(document, page, x + 15, y, ":");
			addPageFiveText(document, page, "a.", x + 75, y, false);
			addPageFiveText(document, page, "Are you acting as an agent for such clients?", x + 98, y, false);
			addYesNoButtons(document, acroForm, page, "actAgentForPublicFigures ",
					getBlankIfNull(firmMap, "actAgentForPublicFigures"), x + 450, y);

			y -= 20;
			y = addPageFiveLetteredWrappedText(document, page, "b.",
					"Are you providing business management or negotiation of performance or professional contracts for such clients?",
					x + 75, y, 360);
			addYesNoButtons(document, acroForm, page, "provideProfessionalContract",
					getBlankIfNull(firmMap, "provideProfessionalContract"), x + 450, y + 13);

			y -= 4;
			y = addPageFiveLetteredWrappedText(document, page, "c.",
					"Are you providing money management or investment advice services to such clients?", x + 75, y,
					360);
			addYesNoButtons(document, acroForm, page, "provideMoneyManagementServices",
					getBlankIfNull(firmMap, "provideMoneyManagementServices"), x + 450, y + 13);

			y -= 16;
			addPageFiveText(document, page, "22.", x, y, false);
			y = addPageFiveWrappedText(document, page,
					"At any time in the past five years, have you provided legal services related to publically traded",
					x + 25, y, 400);
			y -= 3;
			addPageFiveText(document, page,
					"securities transactions or securities that are not exempt from registration, investment vehicles or",
					x + 25, y, false);
			y -= 15;
			addPageFiveText(document, page, "ventures, hedge or other investment funds?", x + 25, y, false);
			addYesNoButtons(document, acroForm, page, "IsFirmProvidedLegalService",
					getBlankIfNull(firmMap, "IsFirmProvidedLegalService"), x + 450, y);

			y -= 25;
			addPageFiveIfYesText(document, page, x + 15, y,
					", please identify and explain the relevant legal services.");
			y -= 20;
			addField(acroForm, page, "IsFirmProvidedLegalServiceDesc",
					getBlankIfNull(firmMap, "IsFirmProvidedLegalServiceDesc"), x + 15, y, 550, 15);

			y -= 25;
			addPageFiveText(document, page, "23.", x, y, false);
			float questionX = addPageFiveInlineText(document, page,
					"In the past five years have you served as general counsel, CEO, chairman, president, ", x + 25, y,
					false);
			addPageFiveText(document, page, "officer,", questionX, y, true);
			y -= 15;
			addPageFiveText(document, page,
					"director or member of an internal committee for a publically traded company or any financial",
					x + 25, y, false);
			y -= 15;
			addPageFiveText(document, page, "institution?", x + 25, y, false);
			addYesNoButtons(document, acroForm, page, "isServedAsCEOChairmanPresident",
					getBlankIfNull(firmMap, "isServedAsCEOChairmanPresident"), x + 450, y);

			y -= 25;
			float q24Y = y;
			y = addPageFiveNumberedWrappedText(document, page, "24.",
					"Is your office or suite shared with attorneys who are not members of your firm?", x, y, 425);
			addYesNoButtons(document, acroForm, page, "IsAppOfficeSharedWithAttorney",
					getBlankIfNull(firmMap, "IsAppOfficeSharedWithAttorney"), x + 450, q24Y);

			y -= 25;
			PDRectangle rect = new PDRectangle();
			Color color = Color.LIGHT_GRAY;
			drawRect(document, page, color, rect, true, "UNDERWRITING INFORMATION", x, y, 570, 15, x + 5, y + 4);

			y -= 24;
			addPageFiveText(document, page, "25.", x, y, false);
			y = addPageFiveWrappedText(document, page,
					"Does your current policy exclude coverage for any attorney, predecessor firms, firm affiliates, clients,",
					x + 25, y, 400);
			y -= 1;
			addPageFiveText(document, page, "specific engagements or other circumstances?", x + 25, y, false);
			addYesNoButtons(document, acroForm, page, "isPolicyExcludeCoverage",
					getBlankIfNull(firmMap, "isPolicyExcludeCoverage"), x + 450, y);

			y -= 18;
			addPageFiveIfYesText(document, page, x + 15, y, ", please describe:");
			addField(acroForm, page, "isPolicyExcludeCoverageDescription",
					getBlankIfNull(firmMap, "isPolicyExcludeCoverageDescription"), x + 160, y - 2, 405, 15);

			y -= 24;
			float q26Y = y;
			y = addPageFiveNumberedWrappedText(document, page, "26.",
					"Does your current policy include lateral hire coverage for any of the firm's current attorneys?",
					x, y, 425);
			addYesNoButtons(document, acroForm, page, "IsPolicyIncludeLateralHireCov",
					getBlankIfNull(firmMap, "IsPolicyIncludeLateralHireCov"), x + 450, q26Y);

			y -= 6;
			addPageFiveText(document, page, "27.", x, y, false);
			y = addPageFiveWrappedText(document, page,
					"Do you have care, custody or control over any funds or accounts of any of your clients, including but not",
					x + 25, y, 400);
			y -= 1;
			addPageFiveText(document, page, "limited to escrow or trust accounts?", x + 25, y, false);
			addYesNoButtons(document, acroForm, page, "custodyOrControlFunds",
					getBlankIfNull(firmMap, "custodyOrControlFunds"), x + 450, y);

			y -= 16;
			addPageFiveIfYesText(document, page, x + 15, y, ":");
			addPageFiveText(document, page,
					"a. In the past year, approximately how many disbursement transactions have you executed", x + 75,
					y, false);
			y -= 13;
			addPageFiveText(document, page, "from your client funds account?", x + 90, y, false);
			addField(acroForm, page, "disbursementTransaction", getBlankIfNull(firmMap, "disbursementTransaction"),
					x + 500, y - 2, 65, 15);

			y -= 20;
			addPageFiveText(document, page,
					"b. How many people at your firm have the authority to disburse funds out of your client", x + 75,
					y, false);
			y -= 13;
			addPageFiveText(document, page,
					"accounts, or to change payment instructions, payment accounts or addresses?", x + 90, y, false);
			addField(acroForm, page, "peopleToDisburseFunds", getBlankIfNull(firmMap, "peopleToDisburseFunds"), x + 500,
					y - 2, 65, 15);

			y -= 20;
			y = addPageFiveLetteredYesNoQuestionAtRadioX(document, acroForm, page, "instructionsDocumentedReceivingParty",
					getBlankIfNull(firmMap, "instructionsDocumentedReceivingParty"),
					"c.",
					"Do you authenticate all changes to payment instructions, account numbers and addresses via outbound phone call to the documented receiving party?",
					x + 75, y, x + 480);

			y -= 2;
			y = addPageFiveLetteredYesNoQuestionAtRadioX(document, acroForm, page, "doucumentedProtocolsAndAuthority",
					getBlankIfNull(firmMap, "doucumentedProtocolsAndAuthority"),
					"d.",
					"Do you have documented protocols and authority level sign-off for all changes to payment instructions, account numbers and addresses of clients, vendors and third parties?",
					x + 75, y, x + 480);

			y -= 12;
			float q28Y = y;
			y = addPageFiveNumberedWrappedText(document, page, "28.",
					"Do you have a fraud security awareness program that all employees are briefed on?", x, y, 425);
			addYesNoButtons(document, acroForm, page, "securityAwareness", getBlankIfNull(firmMap, "securityAwareness"),
					x + 450, q28Y);

		} catch (Exception e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void drawUnderwritingInformationPage(Context ctx, PDAcroForm acroForm, PDDocument document) {
		try {
			float x = 20;
			float y = 740;
			Map firmMap = getMapIfPresent(ctx, "firm_freeform_01");
			Map attorneysInsuranceMap = getMapIfPresent(ctx, "AttorneysInsurance_list_01");
			
			PDPage page = new PDPage(PDPage.PAGE_SIZE_LETTER);
			document.addPage(page);

			y = addPageFiveNumberedWrappedText(document, page, "29.",
					"If your firm has three or more attorneys, please provide your professional liability insurance history for the past three years:",
					x, y, 560);

			y -= 25;
			// Draw Insurance History Table Headers
			String[] headers = { "Insurance Carrier", "Policy Period", "Limit / Deductible", "Number of Attorneys",
					"Premium" };
			int[] widths = { 120, 100, 150, 100, 100 };
			float tableTopY = y + 14;
			float headerHeight = 30f;
			float rowHeight = 22f;
			drawColumnTable(document, page, x, tableTopY, widths, 3, headerHeight, rowHeight);
			float currentX = x;
			for (int i = 0; i < headers.length; i++) {
				addTextInBox(document, page, headers[i], currentX + 5, tableTopY - 12, widths[i] - 10, true);
				currentX += widths[i];
			}

			float row1Y = tableTopY - headerHeight - 18;
			currentX = x;
			addField(acroForm, page, "insuranceCarrierFirstAttoryneyInsurance",
					getBlankIfNull(attorneysInsuranceMap, "insuranceCarrierFirstAttoryneyInsurance"), currentX + 3, row1Y,
					widths[0] - 6, 14);
			currentX += widths[0];
			addField(acroForm, page, "policyPeriodFirstAttoryneyInsurance",
					getBlankIfNull(attorneysInsuranceMap, "policyPeriodFirstAttoryneyInsurance"), currentX + 3, row1Y, widths[1] - 6,
					14);
			currentX += widths[1];
			addField(acroForm, page, "limitDeductibleFirstAttoryneyInsurance",
					getBlankIfNull(attorneysInsuranceMap, "limitDeductibleFirstAttoryneyInsurance"), currentX + 3, row1Y,
					widths[2] - 6, 14);
			currentX += widths[2];
			addField(acroForm, page, "numberOfAttorneysFirstAttoryneyInsurance",
					getBlankIfNull(attorneysInsuranceMap, "numberOfAttorneysFirstAttoryneyInsurance"), currentX + 3, row1Y,
					widths[3] - 6, 14);
			currentX += widths[3];
			addField(acroForm, page, "premiumFirstAttoryneyInsurance",
					getBlankIfNull(attorneysInsuranceMap, "premiumFirstAttoryneyInsurance"), currentX + 3, row1Y, widths[4] - 6, 14);

			float row2Y = tableTopY - headerHeight - rowHeight - 18;
			currentX = x;
			addField(acroForm, page, "insuranceCarrierSecondAttorneyInsurance",
					getBlankIfNull(attorneysInsuranceMap, "insuranceCarrierSecondAttorneyInsurance"), currentX + 3, row2Y,
					widths[0] - 6, 14);
			currentX += widths[0];
			addField(acroForm, page, "policyPeriodSecondAttorneyInsurance",
					getBlankIfNull(attorneysInsuranceMap, "policyPeriodSecondAttorneyInsurance"), currentX + 3, row2Y, widths[1] - 6,
					14);
			currentX += widths[1];
			addField(acroForm, page, "limitDeductibleSecondAttorneyInsurance",
					getBlankIfNull(attorneysInsuranceMap, "limitDeductibleSecondAttorneyInsurance"), currentX + 3, row2Y,
					widths[2] - 6, 14);
			currentX += widths[2];
			addField(acroForm, page, "numberOfAttorneysSecondAttorneyInsurance",
					getBlankIfNull(attorneysInsuranceMap, "numberOfAttorneysSecondAttorneyInsurance"), currentX + 3, row2Y,
					widths[3] - 6, 14);
			currentX += widths[3];
			addField(acroForm, page, "premiumSecondAttorneyInsurance",
					getBlankIfNull(attorneysInsuranceMap, "premiumSecondAttorneyInsurance"), currentX + 3, row2Y, widths[4] - 6, 14);

			float row3Y = tableTopY - headerHeight - (2 * rowHeight) - 18;
			currentX = x;
			addField(acroForm, page, "insuranceCarrierThirdAttorneyInsurance",
					getBlankIfNull(attorneysInsuranceMap, "insuranceCarrierThirdAttorneyInsurance"), currentX + 3, row3Y,
					widths[0] - 6, 14);
			currentX += widths[0];
			addField(acroForm, page, "policyPeriodThirdAttorneyInsurance",
					getBlankIfNull(attorneysInsuranceMap, "policyPeriodThirdAttorneyInsurance"), currentX + 3, row3Y, widths[1] - 6,
					14);
			currentX += widths[1];
			addField(acroForm, page, "limitDeductibleThirdAttorneyInsurance",
					getBlankIfNull(attorneysInsuranceMap, "limitDeductibleThirdAttorneyInsurance"), currentX + 3, row3Y,
					widths[2] - 6, 14);
			currentX += widths[2];
			addField(acroForm, page, "numberOfAttorneysForThirdAttorneyInsurance",
					getBlankIfNull(attorneysInsuranceMap, "numberOfAttorneysForThirdAttorneyInsurance"), currentX + 3, row3Y,
					widths[3] - 6, 14);
			currentX += widths[3];
			addField(acroForm, page, "premiumThirdAttorneyInsurance",
					getBlankIfNull(attorneysInsuranceMap, "premiumThirdAttorneyInsurance"), currentX + 3, row3Y, widths[4] - 6, 14);

			y = tableTopY - headerHeight - (3 * rowHeight) - 35;
			drawPastClaimsAndContactSection(ctx, document, acroForm, page, x, y);

		} catch (Exception e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void drawPastClaimsAndContactSection(Context ctx, PDDocument document, PDAcroForm acroForm,
			PDPage page, float x, float y) {
		try {
			Map firmMap = getMapIfPresent(ctx, "firm_freeform_01");
			Map policyMap = getMapIfPresent(ctx, "policy_freeform_01");

			PDRectangle rect = new PDRectangle();
			Color color = Color.LIGHT_GRAY;
			drawRect(document, page, color, rect, true, "PAST CLAIMS", x, y, 570, 15, x + 5, y + 4);

			y -= 28;
			y = addPageFiveNumberedWrappedText(document, page, "30.",
					"After inquiry of all attorneys and staff of the firm, within the past five years have any past or present personnel:",
					x, y, 560);

			y -= 22;
			addText(document, page,
					"a. been the subject of any disciplinary complaint or regulatory investigation or inquiry; suspended or",
					x + 15, y, false);
			y -= 13;
			addText(document, page,
					"disbarred from practice; or charged, indicted or been convicted of any criminal charge?", x + 25,
					y, false);
			addYesNoButtons(document, acroForm, page, "IsPersonnelBeSubOfAnyInvest",
					getBlankIfNull(firmMap, "IsPersonnelBeSubOfAnyInvest"), x + 450, y);

			y -= 18;
			addPageFiveIfYesText(document, page, x + 15, y, ", please provide details and dates:");
//            addText(document, page, "If \"Yes\", please provide details and dates:", x + 25, y, false);
			addField(acroForm, page, "PersonnelBeSubOfAnyInvestDate",
					getBlankIfDefaultDate(firmMap, "PersonnelBeSubOfAnyInvestDate"), x + 245, y - 2, 300, 15);
			y -= 18;
			addField(acroForm, page, "PersonnelBeSubOfAnyInvestDetails",
					getBlankIfNull(firmMap, "PersonnelBeSubOfAnyInvestDetails"), x + 15, y - 2, 535, 15);
			y -= 26;
			addText(document, page,
					"b. know of any professional liability claims made against the firm, its affiliates or its personnel?",
					x + 15, y, false);
			addYesNoButtons(document, acroForm, page, "IsLawyerProfLiabClaimAgntAppl",
					getBlankIfNull(firmMap, "IsLawyerProfLiabClaimAgntAppl"), x + 450, y);

			y -= 26;
			addText(document, page,
					"c. become aware of any act, error or omission or fee dispute which might become the basis of a claim",
					x + 15, y, false);
			y -= 13;
			addText(document, page, "against the firm or its personnel?", x + 25, y, false);
			addYesNoButtons(document, acroForm, page, "IsAnyActOmmBecomeClaimAgntFirm",
					getBlankIfNull(firmMap, "IsAnyActOmmBecomeClaimAgntFirm"), x + 450, y);

			y -= 26;
			addText(document, page,
					"NOTE: THE POLICY FOR WHICH THIS APPLICATION IS BEING MADE SHALL NOT APPLY TO ANY INCIDENTS OR", x,
					y, true);
			y -= 12;
			addText(document, page,
					"CLAIMS DETAILED OR WHICH SHOULD HAVE BEEN DETAILED IN QUESTION 30 a, b or c ABOVE.", x, y, true);

			y -= 16;
			addPageFiveIfYesText(document, page, x + 15, y,
					" to 30 b or c above, complete the CLAIM SUPPLEMENT for each claim or potential claim.");
//            y = addWrappedText(document, page,
//                    "If \"Yes\" to 30 b or c above, complete the CLAIM SUPPLEMENT for each claim or potential claim.",
//                    x, y, 550, false);

			y -= 20;
			addText(document, page, "31.", x, y, false);
			addText(document, page,
					"Within the past five years has the firm or its attorneys been declined, canceled, or non-renewed for",
					x + 25, y, false);
			y -= 13;
			addText(document, page,
					"professional liability insurance for any reason (Not applicable to Missouri firms)", x + 25, y,
					false);
			addYesNoButtons(document, acroForm, page, "IsAttorneyDeclineForProfLiability",
					getBlankIfNull(firmMap, "IsAttorneyDeclineForProfLiability"), x + 450, y);

			y -= 18;
			addPageFiveIfYesText(document, page, x + 15, y, ", please provide dates and reasons:");
//            addText(document, page, "If \"Yes\", please provide dates and reasons:", x + 15, y, false);
			addField(acroForm, page, "AttorneyDeclineForProfLiabilityDates",
					getBlankIfDefaultDate(firmMap, "AttorneyDeclineForProfLiabilityDates"), x + 245, y - 2, 300, 15);
			y -= 18;
			addField(acroForm, page, "AttorneyDeclineForProfLiabilityReasons",
					getBlankIfNull(firmMap, "AttorneyDeclineForProfLiabilityReasons"), x + 15, y - 2, 535, 15);
			y -= 28;
			drawRect(document, page, Color.LIGHT_GRAY, new PDRectangle(), true, "CONTACT INFORMATION", x, y, 570, 15,
					x + 5, y + 4);

			y -= 22;
			addText(document, page, "32. Primary Contact Name:", x, y, false);
			addField(acroForm, page, "ContactPerson", getBlankIfNull(policyMap, "ContactPerson"), x + 145, y - 2, 135,
					15);
			addText(document, page, "Secondary Contact Name:", x + 300, y, false);
			addField(acroForm, page, "secondayContactPerson", getBlankIfNull(firmMap, "secondayContactPerson"), x + 440,
					y - 2, 130, 15);

			y -= 18;
			addText(document, page, "Phone Number:", x, y, false);
			addField(acroForm, page, "WorkPhone", getBlankIfNull(policyMap, "WorkPhone"), x + 145, y - 2, 135, 15);
			addText(document, page, "Secondary Phone Number:", x + 300, y, false);
			addField(acroForm, page, "otherPhone", getBlankIfNull(policyMap, "otherPhone"), x + 450, y - 2, 120, 15);

			y -= 18;
			addText(document, page, "Email:", x, y, false);
			addField(acroForm, page, "AccountEmail", getBlankIfNull(policyMap, "AccountEmail"), x + 70, y - 2, 210, 15);
			addText(document, page, "Secondary Email:", x + 300, y, false);
			addField(acroForm, page, "secondaryEmail", getBlankIfNull(policyMap, "secondaryEmail"), x + 405, y - 2, 165,
					15);

			y -= 18;
			addText(document, page, "Website Address:", x, y, false);
			addField(acroForm, page, "Website", getWebsiteAddress(policyMap, firmMap), x + 145, y - 2, 425, 15);
		} catch (Exception e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void drawFraudWarningsAndSignaturePage(Context ctx, PDAcroForm acroForm, PDDocument document) {
		try {
			float x = 20;
			float y = 740;
			PDPage page = createPage(document);

			y = addWrappedText(document, page,
					"ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR OTHER PERSON FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF CLAIM CONTAINING ANY MATERIALLY FALSE INFORMATION OR CONCEALS FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT MATERIAL THERETO COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME AND SUBJECTS SUCH PERSON TO CRIMINAL AND CIVIL PENALTIES. (Not applicable in AL, AR, CO, DC, FL, KS, KY, LA, MD, ME, NJ, NM, NY, OH, OK, OR, RI, TN, VA, VT, WA or WV - see Additional Fraud Notices for these States below).",
					x, y, 560, fraudRegularFont, FRAUD_NOTICE_FONT_SIZE, FRAUD_NOTICE_LINE_HEIGHT);

			y -= 8;
			String fraudHeading = "ADDITIONAL FRAUD NOTICES";
			addText(document, page, fraudHeading,
					x + (560 - getTextWidth(fraudBoldFont, fraudHeading, FRAUD_NOTICE_FONT_SIZE)) / 2, y, fraudBoldFont,
					FRAUD_NOTICE_FONT_SIZE);
			y -= 14;

			String[] notices = {
					"NOTICE TO ALABAMA, ARKANSAS, LOUISIANA, NEW MEXICO, RHODE ISLAND AND WEST VIRGINIA APPLICANTS: Any person who knowingly presents a false or fraudulent claim for payment of a loss or benefit or knowingly presents false information in an application for insurance is guilty of a crime and may be subject to fines and confinement in prison.",
					"NOTICE TO COLORADO APPLICANTS: It is unlawful to knowingly provide false, incomplete, or misleading facts or information to an insurance company for the purpose of defrauding or attempting to defraud the company. Penalties may include imprisonment, fines, denial of insurance, and civil damages. Any insurance company or agent of an insurance company who knowingly provides false, incomplete, or misleading facts or information to a policyholder or claimant for the purpose of defrauding or attempting to defraud the policyholder or claimant with regard to a settlement or award payable from insurance proceeds shall be reported to the Colorado Division of Insurance within the Department of Regulatory Agencies.",
					"NOTICE TO DISTRICT OF COLUMBIA APPLICANTS: WARNING: It is a crime to provide false or misleading information to an insurer for the purpose of defrauding the insurer or any other person. Penalties include imprisonment and/or fines. In addition, an insurer may deny insurance benefits if false information materially related to a claim was provided by the applicant.",
					"NOTICE TO FLORIDA APPLICANTS: Any person who knowingly and with intent to injure, defraud, or deceive any insurer files a statement of claim or an application containing any false, incomplete, or misleading information is guilty of a felony of the third degree.",
					"NOTICE TO KANSAS APPLICANTS: Any person who, knowingly and with intent to defraud, presents, causes to be presented or prepares with knowledge or belief that it will be presented to or by an insurer, purported insurer, broker or any agent thereof, any written, electronic, electronic impulse, facsimile, magnetic, oral, or telephonic communication or statement as part of, or in support of, an application for the issuance of, or the rating of an insurance policy for personal or commercial insurance, or a claim for payment or other benefit pursuant to an insurance policy for commercial or personal insurance which such person knows to contain materially false information concerning any fact material thereto; or conceals, for the purpose of misleading, information concerning any fact material thereto commits a fraudulent insurance act.",
					"NOTICE TO KENTUCKY APPLICANTS: Any person who knowingly and with intent to defraud any insurance company or other person files an application for insurance containing any materially false information or conceals, for the purpose of misleading, information concerning any fact material thereto commits a fraudulent insurance act, which is a crime.",
					"NOTICE TO MAINE APPLICANTS: It is a crime to knowingly provide false, incomplete or misleading information to an insurance company for the purpose of defrauding the company. Penalties may include imprisonment, fines or denial of insurance benefits.",
					"NOTICE TO MARYLAND APPLICANTS: Any person who knowingly or willfully presents a false or fraudulent claim for payment of a loss or benefit or who knowingly or willfully presents false information in an application for insurance is guilty of a crime and may be subject to fines and confinement in prison.",
					"NOTICE TO NEW JERSEY APPLICANTS: Any person who includes any false or misleading information on an application for an insurance policy is subject to criminal and civil penalties.",
					"NOTICE TO NEW YORK APPLICANTS: Any person who knowingly and with intent to defraud any insurance company or other person files an application for insurance or statement of claim containing any materially false information, or conceals for the purpose of misleading, information concerning any fact material thereto, commits a fraudulent insurance act, which is a crime, and shall also be subject to a civil penalty not to exceed five thousand dollars and the stated value of the claim for each such violation.",
					"NOTICE TO OHIO APPLICANTS: Any person who, with intent to defraud or knowing that he is facilitating a fraud against an insurer, submits an application or files a claim containing a false or deceptive statement is guilty of insurance fraud.",
					"NOTICE TO OKLAHOMA APPLICANTS: WARNING: Any person who knowingly, and with intent to injure, defraud or deceive any insurer, makes any claim for the proceeds of an insurance policy containing any false, incomplete or misleading information is guilty of a felony.",
					"NOTICE TO OREGON APPLICANTS: Any person who knowingly and with intent to defraud or solicit another to defraud the insurer by submitting an application containing a false statement as to any material fact may be violating state law.",
					"NOTICE TO TENNESSEE, VIRGINIA AND WASHINGTON APPLICANTS: It is a crime to knowingly provide false, incomplete, or misleading information to an insurance company for the purpose of defrauding the company. Penalties include imprisonment, fines, and denial of insurance benefits.",
					"NOTICE TO VERMONT APPLICANTS: Any person who knowingly presents a false statement in an application for insurance may be guilty of a criminal offense and subject to penalties under state law." };

			for (String notice : notices) {
				if (y < 130) {
					page = createPage(document);
					y = 765;
				}
				y = addFraudNotice(document, page, notice, x, y, 560);
				y -= 4;
			}

			if (y < 250) {
				page = createPage(document);
				y = 765;
			}

			y = addWrappedText(document, page,
					"Completion and/or signing of this application does not bind the firm to purchase, nor the Insurer to provide, any insurance policy; however, no policy can be issued unless the application is properly completed, signed and dated.",
					x, y, 560, fraudRegularFont, FRAUD_NOTICE_FONT_SIZE, FRAUD_NOTICE_LINE_HEIGHT);
			y -= 6;
			y = addWrappedText(document, page,
					"The signatory declares that (s)he is authorized by the firm to sign this application on behalf of all prospective Insureds and that to the best of his/her knowledge the statements herein are true. The signatory agrees that if the information supplied in this application and the materials submitted therewith should change between the date this application is signed and the effective date of the proposed insurance, the signatory shall immediately notify the Insurer of such and shall provide the Insurer with information that would complete, update or correct the application or materials submitted therewith. The Insurer may withdraw or modify any of the terms or conditions of coverage accordingly.",
					x, y, 560, fraudRegularFont, FRAUD_NOTICE_FONT_SIZE, FRAUD_NOTICE_LINE_HEIGHT);
			y -= 6;
			y = addWrappedText(document, page,
					"ALL WRITTEN STATEMENTS, SUPPLEMENTAL APPLICATION AND MATERIALS FURNISHED TO THE INSURER IN CONJUNCTION WITH THIS APPLICATION ARE INCORPORATED BY REFERENCE INTO THIS APPLICATION AND MADE A PART THEREOF, AND DEEMED ATTACHED HERETO.",
					x, y, 560, fraudBoldFont, FRAUD_NOTICE_FONT_SIZE, FRAUD_NOTICE_LINE_HEIGHT);

			y -= 25;
			addText(document, page, "SIGNATURE*", x, y, fraudRegularFont, FRAUD_NOTICE_FONT_SIZE);
			addField(acroForm, page, "signatureField", "", x + 105, y - 2, 190, 15);
			addText(document, page, "PRINTED NAME*", x + 315, y, fraudRegularFont, FRAUD_NOTICE_FONT_SIZE);
			addField(acroForm, page, "printedNameField", "", x + 425, y - 2, 150, 15);

			y -= 28;
			addText(document, page, "TITLE OF SIGNATORY:", x, y, fraudRegularFont, FRAUD_NOTICE_FONT_SIZE);
			addField(acroForm, page, "titleField", "", x + 135, y - 2, 170, 15);
			addText(document, page, "DATE SIGNED:", x + 315, y, fraudRegularFont, FRAUD_NOTICE_FONT_SIZE);
			addField(acroForm, page, "dateSigned", "MM/DD/YYYY", x + 425, y - 2, 120, 15);

			y -= 22;
			addText(document, page,
					"*MUST BE SIGNED BY A DULY AUTHORIZED OFFICER OF THE FIRM ON BEHALF OF ALL INSUREDS", x, y,
					fraudRegularFont, FRAUD_NOTICE_FONT_SIZE);
			y -= 40;
			addText(document, page, "FLORIDA FIRMS ONLY:", x, y, fraudBoldFont, FRAUD_NOTICE_FONT_SIZE);

			addField(acroForm, page, "ProducerCode", "", x + 120, y - 2, 220, 15);
			addPlaceHolderText(document, page, "Producer's Name", x + 210, y - 10);

			addField(acroForm, page, "fl_producer_license", "", x + 360, y - 2, 220, 15);
			addPlaceHolderText(document, page, "Producer's Florida License Number", x + 400, y - 10);
			y -= 40;
			addText(document, page, "IOWA FIRMS ONLY: Producer's Name:", x, y, fraudBoldFont, FRAUD_NOTICE_FONT_SIZE);
			addField(acroForm, page, "ProducerCode", "", x + 215, y - 2, 240, 15);

		} catch (Exception e) {
			logPdfException("Exception in GeneratePDFFormNew", e);
		}
	}

	private static void drawSupplementPages(Context ctx, PDAcroForm acroForm, PDDocument document) {
		try {
			final LayoutCursor cursor = new LayoutCursor(document);
			safeDraw("Predecessor Supplement", new PdfDrawAction() {
				public void draw() throws Exception {
					drawPredecessorSupplementSection(acroForm, document, cursor, ctx);
				}
			});
			safeDraw("Bankruptcy Supplement", new PdfDrawAction() {
				public void draw() throws Exception {
					drawBankruptcySupplementSection(acroForm, document, cursor, ctx);
				}
			});
			safeDraw("Copyright Trademark Supplement", new PdfDrawAction() {
				public void draw() throws Exception {
					drawCopyrightTrademarkSupplementSection(acroForm, document, cursor, ctx);
				}
			});

			cursor.newPage();
			safeDraw("Collections Supplement", new PdfDrawAction() {
				public void draw() throws Exception {
					drawCollectionsSupplementSection(acroForm, document, cursor, ctx);
				}
			});
			safeDraw("Corporate Supplement", new PdfDrawAction() {
				public void draw() throws Exception {
					drawCorporateSupplementSection(acroForm, document, cursor, ctx);
				}
			});

			cursor.newPage();
			safeDraw("Family Law Supplement", new PdfDrawAction() {
				public void draw() throws Exception {
					drawFamilyLawSupplementSection(acroForm, document, cursor, ctx);
				}
			});
			safeDraw("Financial Institution Supplement", new PdfDrawAction() {
				public void draw() throws Exception {
					drawFinancialInstitutionSupplementSection(acroForm, document, cursor, ctx);
				}
			});
			safeDraw("Government Supplement", new PdfDrawAction() {
				public void draw() throws Exception {
					drawGovernmentSupplementSection(acroForm, document, cursor, ctx);
				}
			});

			cursor.newPage();
			safeDraw("Plaintiff Litigation Supplement", new PdfDrawAction() {
				public void draw() throws Exception {
					drawPlaintiffLitigationSupplementSection(acroForm, document, cursor, ctx);
				}
			});

			cursor.newPage();
			safeDraw("Real Estate Supplement", new PdfDrawAction() {
				public void draw() throws Exception {
					drawRealEstateSupplementSection(acroForm, document, cursor, ctx);
				}
			});

			cursor.newPage();
			safeDraw("Tax Supplement", new PdfDrawAction() {
				public void draw() throws Exception {
					drawTaxSupplementSection(acroForm, document, cursor, ctx);
				}
			});
			safeDraw("Trusts Supplement", new PdfDrawAction() {
				public void draw() throws Exception {
					drawTrustsSupplementSection(acroForm, document, cursor, ctx);
				}
			});

			cursor.newPage();
			safeDraw("Cannabis Supplement", new PdfDrawAction() {
				public void draw() throws Exception {
					drawCannabisSupplementSection(acroForm, document, cursor, ctx);
				}
			});
			safeDraw("Fee Suit Supplement", new PdfDrawAction() {
				public void draw() throws Exception {
					drawFeeSuitSupplementSection(acroForm, document, cursor, ctx);
				}
			});

			cursor.newPage();
			safeDraw("Claim Supplement", new PdfDrawAction() {
				public void draw() throws Exception {
					drawClaimSupplementSection(acroForm, document, cursor, ctx);
				}
			});
		} catch (Exception e) {
			logPdfException("Exception in drawSupplementPages", e);
		}
	}

	private static void drawSupplementHeader(PDDocument document, LayoutCursor cursor, String title) {
		try {
			cursor.ensure(32);
			drawRect(document, cursor.page, Color.LIGHT_GRAY, new PDRectangle(), true, title, 20, cursor.y, 570, 15, 25,
					cursor.y + 4);
			cursor.y -= 26;
		} catch (Exception e) {
			logPdfException("Exception in drawSupplementHeader", e);
		}
	}

	private static void drawPredecessorSupplementSection(PDAcroForm acroForm, PDDocument document, LayoutCursor cursor,
			Context ctx) {
		Object prodecessorObj = ctx.get("prodecessor_list_1");
		List prodecessorList = prodecessorObj instanceof List ? (List) prodecessorObj : Collections.EMPTY_LIST;
		int listSize = prodecessorList.size();
		drawSupplementHeader(document, cursor, "PREDECESSOR FIRM SUPPLEMENT (as required in Question 12)");
		String[] headers = { "Firm Name", "Date of Acquisition or Merger", "Type of Legal Entity",
				"# of Attys at Firm at Dissolution", "# of Attys for Whom Coverage is Sought", "Insurer at Dissolution",
				"Was ERP Purchased", "ERP Expiration Date" };
		int[] widths = { 90, 70, 65, 70, 70, 60, 70, 60 };
		float tableX = 20;
		float tableTopY = cursor.y + 8;
		float headerHeight = 55f;
		float rowHeight = 24f;
		int rowCount = Math.max(listSize, 2);
		drawColumnTable(document, cursor.page, tableX, tableTopY, widths, rowCount, headerHeight, rowHeight);
		float currentX = tableX;
		for (int i = 0; i < headers.length; i++) {
			addWrappedText(document, cursor.page, headers[i], currentX + 3, tableTopY - 13, widths[i] - 6, boldFont,
					8.5f, 9.5f);
			currentX += widths[i];
		}
		cursor.y = tableTopY - headerHeight - 18;
		for (int row = 0; row < listSize; row++) {
			currentX = tableX;
			HashMap map = (HashMap) prodecessorList.get(row);
			addField(acroForm, cursor.page, "FirmName_" + row, getBlankIfNull(map, "FirmName"), currentX + 3, cursor.y,
					widths[0] - 6, 13);
			currentX += widths[0];
			addField(acroForm, cursor.page, "DateOfAcquisation_" + row, getBlankIfNull(map, "DateOfAcquisation"),
					currentX + 3, cursor.y, widths[1] - 6, 13);
			currentX += widths[1];
			addField(acroForm, cursor.page, "TypeOfEntity_" + row, getBlankIfNull(map, "TypeOfEntity"), currentX + 3,
					cursor.y, widths[2] - 6, 13);
			currentX += widths[2];
			addField(acroForm, cursor.page, "NumberOfAttorneyAtDiss_" + row,
					getBlankIfNull(map, "NumberOfAttorneyAtDiss"), currentX + 3, cursor.y, widths[3] - 6, 13);
			currentX += widths[3];
			addField(acroForm, cursor.page, "NumberOfAttorneyAtApplFirm_" + row,
					getBlankIfNull(map, "NumberOfAttorneyAtApplFirm"), currentX + 3, cursor.y, widths[4] - 6, 13);
			currentX += widths[4];
			addField(acroForm, cursor.page, "InsurerAtDissolution_" + row, getBlankIfNull(map, "InsurerAtDissolution"),
					currentX + 3, cursor.y, widths[5] - 6, 13);
			currentX += widths[5];
			addCoverageRadioButtons(document, acroForm, cursor.page, "IsERPPurchased_" + row,
					createCompactYesNoButtons(), getBlankIfNull(map, "IsERPPurchased"),
					new float[] { currentX + 3, currentX + 38 }, cursor.y, new float[] { 18, 16 }, false);
			currentX += widths[6];
			addField(acroForm, cursor.page, "ERPExpDate_" + row, getBlankIfNull(map, "ERPExpDate"), currentX + 3,
					cursor.y, widths[7] - 6, 13);
			cursor.y -= rowHeight;
		}
		cursor.y = tableTopY - headerHeight - (rowCount * rowHeight) - 32;
	}

	private static void drawBankruptcySupplementSection(PDAcroForm acroForm, PDDocument document, LayoutCursor cursor,
			Context ctx) {
		Map bankruptcyList01 = getMapIfPresent(ctx, "Bankruptcy_list_01");
		Map[] bankruptcyCaseRows = { getMapIfPresent(ctx, "bankruptcyCasesDetails_new_list_01"),getMapIfPresent(ctx, "bankruptcyCasesDetails_new_list_02"),getMapIfPresent(ctx, "bankruptcyCasesDetails_new_list_03"),
				getMapIfPresent(ctx, "bankruptcyCasesDetails_new_list_04") };

		drawSupplementHeader(document, cursor,
				"BANKRUPTCY SUPPLEMENT (as required in Question 6, Area of Practice Table)");
		cursor.y = addWrappedSmallText(document, cursor.page,
				"NOTE: THIS SUPPLEMENT IS ONLY REQUIRED TO BE COMPLETED IF THE PERCENTAGE FOR BANKRUPTCY IN THE AREA OF PRACTICE TABLE IS GREATER THAN 35%.",
				20, cursor.y, 560, true);
		cursor.y -= 8;
		addSmallText(document, cursor.page, "1. Please indicate the percentage of bankruptcy cases which are:", 20,
				cursor.y, false);
		cursor.y -= 18;
		addSmallText(document, cursor.page, "Personal Bankruptcies", 35, cursor.y, false);
		addField(acroForm, cursor.page, "personalBankrupties", getBlankIfNull(bankruptcyList01, "personalBankrupties"),
				150, cursor.y - 2, 40, 13);
		addSmallText(document, cursor.page, "%", 196, cursor.y, false);
		addSmallText(document, cursor.page, "Commercial Bankruptcies", 210, cursor.y, false);
		addField(acroForm, cursor.page, "commercialBankruptcies",
				getBlankIfNull(bankruptcyList01, "commercialBankruptcies"), 355, cursor.y - 2, 40, 13);
		addSmallText(document, cursor.page, "%", 401, cursor.y, false);
		addSmallText(document, cursor.page, "Total (must equal 100%)", 430, cursor.y, true);
		addCalculatedTotalField(acroForm, cursor.page, "bankruptcyTotalPercent",
				sumFieldDefaults(new String[] { getBlankIfNull(bankruptcyList01, "personalBankrupties"),
						getBlankIfNull(bankruptcyList01, "commercialBankruptcies") }),
				552, cursor.y - 2, 30, 13, new String[] { "personalBankrupties", "commercialBankruptcies" });
		addSmallText(document, cursor.page, "%", 586, cursor.y, false);

		cursor.y -= 24;
		addSmallText(document, cursor.page, "2. How much of your bankruptcy practice involves the following:", 20,
				cursor.y, false);
		cursor.y -= 16;
		addSmallText(document, cursor.page, "Practice Type", 35, cursor.y, true);
		addSmallText(document, cursor.page, "Percentage", 320, cursor.y, true);
		addSmallText(document, cursor.page, "Average Case Value", 430, cursor.y, true);
		cursor.y -= 16;
		String[] rows = { "Bankruptcy Representation - Consumer:", "Bankruptcy Representation - Commercial:",
				"Bankruptcy Trustee - Consumer:", "Bankruptcy Trustee - Commercial:" };
		for (int i = 0; i < rows.length; i++) {
			Map bankruptcyCaseRow = bankruptcyCaseRows[i];
			addSmallText(document, cursor.page, rows[i], 35, cursor.y, false);
			addField(acroForm, cursor.page, "grp_bankruptcy_pct_" + i,
					getBlankIfNull(bankruptcyCaseRow, "Percentage"), 320, cursor.y - 2, 35, 13);
			addSmallText(document, cursor.page, "%", 360, cursor.y, false);
			addSmallText(document, cursor.page, "$", 430, cursor.y, false);
			addField(acroForm, cursor.page, "grp_bankruptcy_value_" + i,
					getAmountWithoutCurrency(bankruptcyCaseRow, "AverageCaseValue"), 445, cursor.y - 2, 80, 13);
			cursor.y -= 16;
		}

		cursor.y -= 6;
		String[] bankruptcyQuestionFields = { "companyFinance", "representedDebtors", "preBankruptcyServices",
				"dueDiligenceProcess", "reviewProcedureForCertification", "disclosureStatementExplaining",
				"conspicuousStatement" };
		String[] bankruptcyQuestions = {
				"3. Do you use any third-party or outside company to finance bankruptcy litigation?",
				"4. Have you ever represented debtors in a bankruptcy proceeding where total debt exceeded $10 million?",
				"5. Do you have any affiliations, or referral arrangements with third party entities or other attorneys that offer any services in the area of debt settlement, debt resolution, debt consolidation, debt relief, credit counseling or attorneys fee financing?",
				"6. Do you have a due diligence process for certifying the truthfulness and accuracy of the debtors bankruptcy schedule?",
				"7. Do you use a review procedure for certification of the debtor's ability to pay?",
				"8. Do you use a uniform disclosure statement explaining the duties of the debtor in bankruptcy which is disseminated to all clients?",
				"9. Do you include a conspicuous statement in all advertising stating that the firm is acting as a debt relief agency and containing all required disclosures?" };
		cursor.y = addCompactYesNoQuestions(document, acroForm, cursor.page, bankruptcyList01,
				bankruptcyQuestionFields, bankruptcyQuestions, 20, cursor.y);
		cursor.y -= 8;
	}

	private static void drawCopyrightTrademarkSupplementSection(PDAcroForm acroForm, PDDocument document,
			LayoutCursor cursor, Context ctx) {
		Map copyRightTrademarkMap = getMapIfPresent(ctx, "CopyRightTrademark_list_01");
		drawSupplementHeader(document, cursor,
				"COPYRIGHT / TRADEMARK SUPPLEMENT (as required in Question 6, Area of Practice Table)");
		cursor.y = addCompactYesNoQuestion(document, acroForm, cursor.page, "otherServices",
				getBlankIfNull(copyRightTrademarkMap, "otherServices"),
				"1. Do you provide services other than searches and filings?", 20, cursor.y);
		addSmallIfYesText(document, cursor.page, 35, cursor.y, ", please describe:");
		addField(acroForm, cursor.page, "otherServicesDesc", getBlankIfNull(copyRightTrademarkMap, "otherServicesDesc"),
				155, cursor.y - 2, 405, 13);
		cursor.y -= 26;
	}

	private static void drawCollectionsSupplementSection(PDAcroForm acroForm, PDDocument document, LayoutCursor cursor,
			Context ctx) {
		Map collectionsMap = getMapIfPresent(ctx, "Collections_list_01");
		Map collectionsMap1 = getMapIfPresent(ctx, "collectionAmount_list_01");
		drawSupplementHeader(document, cursor,
				"COLLECTIONS SUPPLEMENT (as required in Question 6, Area of Practice Table)");
		addSmallText(document, cursor.page,
				"1. Over the past three years what is the average number of cases the firm has handled per year?", 20,
				cursor.y, false);
		addField(acroForm, cursor.page, "averageCases", getBlankIfNull(collectionsMap, "averageCases"), 520,
				cursor.y - 2, 45, 13);
		cursor.y -= 22;
		addSmallText(document, cursor.page, "2. What percentage of your collections practice are:", 20, cursor.y,
				false);
		cursor.y -= 16;
		addSmallText(document, cursor.page, "Consumer Collections?", 35, cursor.y, false);
		addField(acroForm, cursor.page, "consumerCollections", getBlankIfNull(collectionsMap, "consumerCollections"),
				145, cursor.y - 2, 40, 13);
		addSmallText(document, cursor.page, "%", 191, cursor.y, false);
		addSmallText(document, cursor.page, "Commercial Collections?", 225, cursor.y, false);
		addField(acroForm, cursor.page, "commercialollections", getBlankIfNull(collectionsMap, "commercialollections"),
				370, cursor.y - 2, 40, 13);
		addSmallText(document, cursor.page, "%", 416, cursor.y, false);
		addSmallText(document, cursor.page, "Total (must equal 100%)", 430, cursor.y, true);
		addCalculatedTotalField(acroForm, cursor.page, "collectionsTotalPercent",
				sumFieldDefaults(new String[] { getBlankIfNull(collectionsMap, "consumerCollections"),
						getBlankIfNull(collectionsMap, "commercialollections") }),
				552, cursor.y - 2, 30, 13, new String[] { "consumerCollections", "commercialollections" });
		addSmallText(document, cursor.page, "%", 586, cursor.y, false);
		cursor.y -= 22;
		addSmallText(document, cursor.page, "3. What is the average debt amount for individual collections accounts?",
				20, cursor.y, false);
		addField(acroForm, cursor.page, "individualCollectionsAmount",
				getBlankIfNull(collectionsMap1, "individualCollectionsAmount"), 380, cursor.y - 2, 80, 13);
		cursor.y -= 20;
		String[] collectionsQuestionFields = { "personnelToCollectDebts", "servicesToPurchasers",
				"claimsOrSuitsFDCPA" };
		String[] collectionsQuestions = { "4. Do you use non-lawyer personnel to collect debts?",
				"5. Do you own or provide any services to purchasers of debt or debt consolidators?",
				"6. Within the past three years have you been a party to any claims or suits under the FDCPA?" };
		cursor.y = addCompactYesNoQuestions(document, acroForm, cursor.page, collectionsMap,
				collectionsQuestionFields, collectionsQuestions, 20, cursor.y);
		cursor.y -= 10;
	}

	private static void drawCorporateSupplementSection(PDAcroForm acroForm, PDDocument document, LayoutCursor cursor,
			Context ctx) {
		
		Map policyCCBMap = getMapIfPresent(ctx, "policy_CCB_01");
		drawSupplementHeader(document, cursor,
				"CORPORATE / COMMERCIAL / BUSINESS SUPPLEMENT (as required in Question 6, Area of Practice Table)");
		cursor.y = addCompactYesNoQuestion(document, acroForm, cursor.page, "OtherLegalCorporateServices", getBlankIfNull(policyCCBMap,"OtherLegalCorporateServices"),
				"1. Do you provide corporate services other than general counsel/corporate governance, contract drafting and review, legal entity formations, employment contracts, partnership agreements, ERISA and employment benefit consulting?",
				20, cursor.y);
		addSmallIfYesText(document, cursor.page, 35, cursor.y,
				", please provide a brief description of those corporate services not named above and, for each service, the percent of");
		cursor.y -= 12;
		addSmallText(document, cursor.page, "total income derived from such services in the past year.", 35, cursor.y,
				false);
		cursor.y -= 16;
		addSmallText(document, cursor.page, "Description", 45, cursor.y, true);
		addSmallText(document, cursor.page, "% of Total Income", 430, cursor.y, true);
		cursor.y -= 20;

		for (int i = 1; i <= 3; i++) {
			addField(acroForm, cursor.page, "CorporateServiceDesc" + i,
					getBlankIfNull(policyCCBMap, "CorporateServiceDesc" + i), 50, cursor.y - 2, 338, 13);
			addField(acroForm, cursor.page, "CorporateServicePercentage" + i,
					getBlankIfNull(policyCCBMap, "CorporateServicePercentage" + i), 430, cursor.y - 2, 45, 13);
			addSmallText(document, cursor.page, "%", 485, cursor.y, false);
			cursor.y -= 15;
		}

		cursor.y -= 5;
		cursor.y = addCompactYesNoQuestion(document, acroForm, cursor.page, "PubliclyRenderedServices", getBlankIfNull(policyCCBMap,"PubliclyRenderedServices"),
				"2. Does the firm render services to publicly traded clients? (If \"Yes\", please explain below)", 20,
				cursor.y);
		addField(acroForm, cursor.page, "PubliclyRenderedServicesDesc", getBlankIfNull(policyCCBMap,"PubliclyRenderedServicesDesc"), 35, cursor.y - 2, 520, 13);
		cursor.y -= 22;
		addSmallText(document, cursor.page,
				"3. With regard to corporate clients, does the firm or any member of the firm:", 20, cursor.y, false);
		cursor.y -= 16;
		String[] questions = { "a. Have a business relationship other than the rendering of legal services?",
				"b. Have authority to disburse funds for any corporate clients?",
				"c. Accept compensation on a commission basis or based on dollar value of sale?",
				"d. Accept securities in payment for legal services in lieu of legal fees?",
				"e. Perform due diligence on behalf of a prospective buyer of a business?" };
		String[] questionsFields= {"LegalBusinessRelationshipServices","AuthorityToDisburseFunds","AcceptCompensation","LegalServicesSecuritiesPayment","PerformDueDiligence"};
		cursor.y = addCompactYesNoQuestions(document, acroForm, cursor.page, policyCCBMap, questionsFields, questions,
				35, cursor.y);
		addSmallIfYesText(document, cursor.page, 35, cursor.y,
				" to any in a. through e. above, please provide a detailed explanation.");
		addField(acroForm, cursor.page, "DetailedDescCorporateServices", getBlankIfNull(policyCCBMap,"DetailedDescCorporateServices"), 35, cursor.y - 18, 520, 13);
		cursor.y -= 38;
		cursor.y = addCompactYesNoQuestion(document, acroForm, cursor.page, "LegalServicesBankruptLiquidation", getBlankIfNull(policyCCBMap,"LegalServicesBankruptLiquidation"),
				"4. Has the firm or any member of the firm provided any kind of legal services or advice to a client who subsequently went insolvent, bankrupt, or into liquidation or receivership during the past two years?",
				20, cursor.y);
	}

	private static void drawFamilyLawSupplementSection(PDAcroForm acroForm, PDDocument document, LayoutCursor cursor,
			Context ctx) {
		drawSupplementHeader(document, cursor,
				"FAMILY LAW SUPPLEMENT (as required in Question 6, Area of Practice Table)");
		cursor.y = addWrappedSmallText(document, cursor.page,
				"NOTE: THIS SUPPLEMENT IS ONLY REQUIRED TO BE COMPLETED IF THE PERCENTAGE FOR FAMILY LAW IN THE AREA OF PRACTICE TABLE IS GREATER THAN 35%.",
				20, cursor.y, 560, true);
		cursor.y -= 8;
		addSmallText(document, cursor.page,
				"1. Please complete the chart below for the past fiscal year for the Family Law services that you provide:",
				20, cursor.y, false);
		cursor.y -= 18;
		addSmallText(document, cursor.page, "% of Revenue", 200, cursor.y, true);
		addSmallText(document, cursor.page, "% of Revenue", 465, cursor.y, true);
		cursor.y -= 16;
		String[] left = { "a. Adoption", "b. Assisted Reproductive Technology", "c. Child Support", "d. Custody" };
		String[] leftFields = { "FLAOP_PercentageValue_1","FLAOP_PercentageValue_2","FLAOP_PercentageValue_3","FLAOP_PercentageValue_8" };
		String[] right = { "e. Divorce - assets under $5M", "f. Divorce - assets over $5M", "g. Surrogacy",
				"h. Other (please describe):" };
		String[] rightFields = {"FLAOP_PercentageValue_4","FLAOP_PercentageValue_5","FLAOP_PercentageValue_6","FLAOP_PercentageValue_7"};
		float startY = cursor.y;
		for (int i = 0; i < left.length; i++) {
			addSmallText(document, cursor.page, left[i], 35, cursor.y, false);
			addField(acroForm, cursor.page, leftFields[i], getBlankIfNull(ctx,leftFields[i]), 200, cursor.y - 3, 48, 14);
			addSmallText(document, cursor.page, "%", 254, cursor.y, false);
			cursor.y -= 16;
		}
		cursor.y = startY;
		for (int i = 0; i < right.length; i++) {
			addSmallText(document, cursor.page, right[i], 305, cursor.y, false);
			addField(acroForm, cursor.page, rightFields[i],getBlankIfNull(ctx,rightFields[i]), 465, cursor.y - 3, 48, 14);
			addSmallText(document, cursor.page, "%", 519, cursor.y, false);
			cursor.y -= 16;
		}
		cursor.y -= 2;
		addSmallText(document, cursor.page, "Description :", 35, cursor.y, false);
		addField(acroForm, cursor.page, "FLAOP_OtherDescription", getFamilyLawOtherDescription(ctx),
				115, cursor.y - 18, 445, 18);
		cursor.y -= 38;
		String[] familyLawTotalFields = { "FLAOP_PercentageValue_1", "FLAOP_PercentageValue_2",
				"FLAOP_PercentageValue_3", "FLAOP_PercentageValue_8", "FLAOP_PercentageValue_4",
				"FLAOP_PercentageValue_5", "FLAOP_PercentageValue_6", "FLAOP_PercentageValue_7" };
		addSmallText(document, cursor.page, "TOTAL (must equal 100%)", 305, cursor.y, true);
		addCalculatedTotalField(acroForm, cursor.page, "familyLawTotalPercent",
				sumValues(ctx, familyLawTotalFields), 465, cursor.y - 3, 48, 14, familyLawTotalFields);
		addSmallText(document, cursor.page, "%", 519, cursor.y, false);
		cursor.y -= 26;
	}

	private static void drawFinancialInstitutionSupplementSection(PDAcroForm acroForm, PDDocument document,
			LayoutCursor cursor, Context ctx) {
		
		Map financialInstitutionMap = getMapIfPresent(ctx, "FinancialInstitution_list_01");
		drawSupplementHeader(document, cursor,
				"FINANCIAL INSTITUTION SUPPLEMENT (as required in Question 6, Area of Practice Table)");
		addSmallText(document, cursor.page,
				"1. With regard to any financial institution client(s) within the past five years, has any current or former attorney of the firm:",
				20, cursor.y, false);
		cursor.y -= 18;
		String[] financialQuestionFields = { "equityInterest", "servicesRelatedRegulatory" };
		String[] financialQuestions = {
				"a. had any equity interest in or a loan commitment in or from said financial institution other than a mortgage on a personal residence?",
				"b. performed services related to regulatory compliance, opinion letters, preferred loan documentation, foreclosure or loan workout?" };
		cursor.y = addCompactYesNoQuestions(document, acroForm, cursor.page, financialInstitutionMap,
				financialQuestionFields, financialQuestions, 35, cursor.y);
		cursor.y = addNumberedCompactYesNoQuestion(document, acroForm, cursor.page, "underRegulatoryReview",
				getBlankIfNull(financialInstitutionMap, "underRegulatoryReview"), "2.",
				"Is any institution which you have represented within the past five years currently or been previously under regulatory review by any state or government agency or had action taken against them?",
				20, cursor.y);
		cursor.y = addNumberedCompactYesNoQuestion(document, acroForm, cursor.page, "haveBecomeBankruptInsolvent",
				getBlankIfNull(financialInstitutionMap, "haveBecomeBankruptInsolvent"), "3.",
				"Has any financial institution for which you have done work in the past five years become bankrupt or insolvent?",
				20, cursor.y);
		cursor.y -= 8;
	}

	private static void drawGovernmentSupplementSection(PDAcroForm acroForm, PDDocument document, LayoutCursor cursor,
			Context ctx) {
		Map governmentMap = getMapIfPresent(ctx, "Government_list_01");
		Map governmentServicesMap1 = getMapIfPresent(ctx, "governmentServices_list_01");
		Map governmentServicesMap2 = getMapIfPresent(ctx, "governmentServices_list_02");
		Map governmentServicesMap3 = getMapIfPresent(ctx, "governmentServices_list_03");
		drawSupplementHeader(document, cursor,
				"GOVERNMENT SUPPLEMENT (as required in Question 6, Area of Practice Table)");
		addSmallText(document, cursor.page,
				"1. Please complete the information below for each city, town, county or municipality to whom you provide services:",
				20, cursor.y, false);
		cursor.y -= 17;
		addSmallText(document, cursor.page, "Name", 35, cursor.y, true);
		addSmallText(document, cursor.page, "Services Provided", 230, cursor.y, true);
		cursor.y -= 16;
		Map[] governmentServiceMaps = { governmentServicesMap1, governmentServicesMap2, governmentServicesMap3 };
		for (int i = 0; i < governmentServiceMaps.length; i++) {
			addField(acroForm, cursor.page, "ServiceName_" + i,
					getBlankIfNull(governmentServiceMaps[i], "ServiceName"), 40, cursor.y - 2, 160, 13);
			addField(acroForm, cursor.page, "ServicesProvided_" + i,
					getBlankIfNull(governmentServiceMaps[i], "ServicesProvided"), 235, cursor.y - 2, 310, 13);
			cursor.y -= 16;
		}
		cursor.y -= 5;
		String[] governmentFields = { "providingBondWork", "eminentDomainServices" };
		String[] governmentQuestions = { "2. Are you providing bond work as part of your services?",
				"3. Are you providing services related to eminent domain?" };
		cursor.y = addCompactYesNoQuestions(document, acroForm, cursor.page, governmentMap, governmentFields,
				governmentQuestions, 20, cursor.y);
	}

	private static void drawPlaintiffLitigationSupplementSection(PDAcroForm acroForm, PDDocument document,
			LayoutCursor cursor, Context ctx) {
		Map aolPlaintiffMap = getMapIfPresent(ctx, "aol_plaintiffMap");
		Map aolPlaintiffMap1 = getMapIfPresent(ctx, "plaintiff_freeform_01");
		drawSupplementHeader(document, cursor,
				"PLAINTIFF LITIGATION SUPPLEMENT (as required in Question 6, Area of Practice Table)");
		cursor.y = addNumberedWrappedSmallText(document, cursor.page,
				"1. Please complete the following plaintiff litigation chart providing the approximate largest case size for the past fiscal year for each area of the firm's plaintiff litigation practice:",
				20, cursor.y, 560, false);
		cursor.y -= 8;
		addSmallText(document, cursor.page, "AREA OF LITIGATION", 35, cursor.y, true);
		addSmallText(document, cursor.page, "Largest Case Size", 380, cursor.y, true);
		cursor.y -= 16;
		String[] areas = { "a. Civil", "b. Construction", "c. Corporate / Commercial / Business",
				"d. Mass Tort Litigation / Class Action / Toxic Tort", "e. Medical Malpractice",
				"f. Personal Injury / Property Damage", "g. Products Liability",
				"h. Professional Liability (non-medical)", "i. Other" };
		String[] areasLCS = { "LargestCaseSize_14", "LargestCaseSize_15", "LargestCaseSize_4", "LargestCaseSize_11",
				"LargestCaseSize_5", "LargestCaseSize_6", "LargestCaseSize_8", "LargestCaseSize_9",
				"LargestCaseSize_13" };
		for (int i = 0; i < areas.length; i++) {
			addSmallText(document, cursor.page, areas[i], 35, cursor.y, false);
			addSmallText(document, cursor.page, "$", 380, cursor.y, false);
			addField(acroForm, cursor.page, areasLCS[i], getBlankIfNull(aolPlaintiffMap, areasLCS[i]), 395,
					cursor.y - 2, 110, 13);
			cursor.y -= 15;
		}
		cursor.y -= 6;

		String[] fieldQuestions = { "2. Number of support staff devoted to plaintiff work:",
				"3. Total number of plaintiff cases during the past 12 months:" };
		String[] fieldNames = { "NumberOfSuppotedStaffToPlantiff", "NumberOfInjuryCasesIn12Month" };
		float plaintiffNumericFieldX = 430f;
		int plaintiffNumericFieldWidth = 80;
		for (int i = 0; i < fieldQuestions.length; i++) {
			addSmallText(document, cursor.page, fieldQuestions[i], 20, cursor.y, false);
			addField(acroForm, cursor.page, fieldNames[i], getBlankIfNull(aolPlaintiffMap1, fieldNames[i]),
					plaintiffNumericFieldX, cursor.y - 2, plaintiffNumericFieldWidth, 13);
			cursor.y -= 18;
		}

		addSmallText(document, cursor.page,
				"4. Of the number of plaintiff cases handled by the firm, in the past five years, what percentage of them were",
				20, cursor.y, false);
		cursor.y -= 12;
		addSmallText(document, cursor.page, "settled prior to trial:", 35, cursor.y, false);
		addWholeNumberField(acroForm, cursor.page, "PerOfCasesSettledBeforeTrail",
				getBlankIfNull(aolPlaintiffMap1, "PerOfCasesSettledBeforeTrail"), plaintiffNumericFieldX, cursor.y - 2,
				plaintiffNumericFieldWidth, 13);
		addSmallText(document, cursor.page, "%", plaintiffNumericFieldX + plaintiffNumericFieldWidth + 8, cursor.y, false);
		cursor.y -= 18;

		float plaintiffLitigationRadioX = 485f;
		cursor.y = addCompactYesNoQuestionInColumn(document, acroForm, cursor.page, "IsAppAcceptRefersFromOtherFirms",
				getBlankIfNull(aolPlaintiffMap1, "IsAppAcceptRefersFromOtherFirms"),
				"5. Do you accept referrals from other firms?", 20, cursor.y, plaintiffLitigationRadioX);
		cursor.y = addPlaintiffFollowUpQuestion(document, acroForm, cursor, aolPlaintiffMap1, "",
				"IsRefAggrementReferToAppl", ", are written referral agreements used?", plaintiffLitigationRadioX);

		cursor.y = addCompactYesNoQuestionInColumn(document, acroForm, cursor.page, "IsAppReferToOtherLawFirms",
				getBlankIfNull(aolPlaintiffMap1, "IsAppReferToOtherLawFirms"),
				"6. Do you refer cases to other law firms?", 20, cursor.y, plaintiffLitigationRadioX);
		cursor.y = addPlaintiffFollowUpQuestion(document, acroForm, cursor, aolPlaintiffMap1, "a. ",
				"IsRefAggrementReferToApplRefersOut", ", are written referral agreements used?",
				plaintiffLitigationRadioX);
		cursor.y = addPlaintiffFollowUpQuestion(document, acroForm, cursor, aolPlaintiffMap1, "b. ",
				"IsAppConfirmCarryProfLiabIns",
				", do you confirm that firms to which referrals are made carry professional liability insurance?",
				plaintiffLitigationRadioX);

		String[] yesNoFields = { "IsSettlementAggrementsUsed", "massTortOrClassAction" };
		String[] yesNoQuestions = {
				"7. Are written authorizations for settlement always obtained from clients when settlements are reached?",
				"8. In the past five years have you been lead counsel or co-lead counsel on any mass tort or class action matter?" };
		for (int i = 0; i < yesNoFields.length; i++) {
			cursor.y = addCompactYesNoQuestionInColumn(document, acroForm, cursor.page, yesNoFields[i],
					getBlankIfNull(aolPlaintiffMap1, yesNoFields[i]), yesNoQuestions[i], 20, cursor.y,
					plaintiffLitigationRadioX);
		}
	}

	private static float addPlaintiffFollowUpQuestion(PDDocument document, PDAcroForm acroForm, LayoutCursor cursor,
			Map plaintiffMap, String prefix, String fieldName, String questionText, float radioX) {
		cursor.y -= 14;
		float questionX = 35;
		if (prefix != null && prefix.length() > 0) {
			questionX = addSmallInlineTextSegment(document, cursor.page, prefix, questionX, cursor.y, false);
		}
		addSmallIfYesText(document, cursor.page, questionX, cursor.y, questionText);
		addRadioButtons(document, acroForm, cursor.page, fieldName, createCompactYesNoButtons(),
				getBlankIfNull(plaintiffMap, fieldName), radioX, cursor.y + 9);
		return cursor.y - 24;
	}

	private static void drawRealEstateRevenueTable(PDAcroForm acroForm, PDDocument document, LayoutCursor cursor,
			Context ctx, String heading, String fieldPrefix, String[] areas, String[] fields) {
		boolean residential = fieldPrefix.indexOf("_res_") >= 0;
		addSmallText(document, cursor.page, heading, 20, cursor.y, true);
		addSmallText(document, cursor.page, "% of " + (residential ? "Residential" : "Commercial") + " Revenue",
				400, cursor.y, true);
		cursor.y -= 13;
		String[] totalFields = new String[areas.length];
		String[] totalValues = new String[areas.length];
		for (int i = 0; i < areas.length; i++) {
			addSmallText(document, cursor.page, areas[i], 25, cursor.y, false);
			String fieldName = fieldPrefix + i;
			String defaultValue = getBlankIfNull(ctx, fields[i]);
			addField(acroForm, cursor.page, fieldName, defaultValue, 405, cursor.y - 2, 58, 13);
			addSmallText(document, cursor.page, "%", 471, cursor.y, false);
			totalFields[i] = fieldName;
			totalValues[i] = defaultValue;
			cursor.y -= 13;
		}
		cursor.y -= 8;
		addSmallText(document, cursor.page, "Description:", 35, cursor.y, false);
		addField(acroForm, cursor.page, residential ? "AOPRE_CommentDesc_20" : "AOPRE_CommentDescCom_20",
				getBlankIfNull(ctx, residential ? "AOPRE_CommentDesc_20" : "AOPRE_CommentDescCom_20"), 115,
				cursor.y - 18, 445, 18);
		cursor.y -= 40;
		addSmallText(document, cursor.page, "TOTAL (if applicable, must equal 100%)", 25, cursor.y, true);
		addCalculatedTotalField(acroForm, cursor.page,
				residential ? "residentialRealEstateTotalPercent" : "commercialRealEstateTotalPercent",
				sumFieldDefaults(totalValues), 405, cursor.y - 2, 58, 13, totalFields);
		addSmallText(document, cursor.page, "%", 471, cursor.y, false);
		cursor.y -= 18;
	}

	private static void drawTwoColumnPercentageRows(PDAcroForm acroForm, PDDocument document, LayoutCursor cursor,
			Context ctx, String[] leftLabels, String[] leftFields, String leftFieldPrefix, String[] rightLabels,
			String[] rightFields, String rightFieldPrefix, float leftLabelX, float leftFieldX, float leftPercentX,
			float rightLabelX, float rightFieldX, float rightPercentX, float rowGap) {
		float startY = cursor.y;
		for (int i = 0; i < leftLabels.length; i++) {
			addSmallText(document, cursor.page, leftLabels[i], leftLabelX, cursor.y, false);
			addField(acroForm, cursor.page, leftFieldPrefix + i, getBlankIfNull(ctx, leftFields[i]), leftFieldX,
					cursor.y - 3, 48, 14);
			addSmallText(document, cursor.page, "%", leftPercentX, cursor.y, false);
			cursor.y -= rowGap;
		}
		float leftBottomY = cursor.y;
		cursor.y = startY;
		for (int i = 0; i < rightLabels.length; i++) {
			addSmallText(document, cursor.page, rightLabels[i], rightLabelX, cursor.y, false);
			addField(acroForm, cursor.page, rightFieldPrefix + i, getBlankIfNull(ctx, rightFields[i]), rightFieldX,
					cursor.y - 3, 48, 14);
			addSmallText(document, cursor.page, "%", rightPercentX, cursor.y, false);
			cursor.y -= rowGap;
		}
		cursor.y = Math.min(leftBottomY, cursor.y);
	}

	private static String[] shortenLabels(String[] labels, int maxLength, int prefixLength) {
		String[] shortened = new String[labels.length];
		for (int i = 0; i < labels.length; i++) {
			shortened[i] = labels[i].length() > maxLength ? labels[i].substring(0, prefixLength) + "..." : labels[i];
		}
		return shortened;
	}

	private static void drawRealEstateSupplementSection(PDAcroForm acroForm, PDDocument document, LayoutCursor cursor,Context ctx) {
		Map realEastateMap01 = getMapIfPresent(ctx, "RealEastate_list_01");
		Map amountForRealEstateMap01 = getMapIfPresent(ctx, "amountForRealEstate_list_01");
		drawSupplementHeader(document, cursor,
				"REAL ESTATE SUPPLEMENT (as required in Question 6, Area of Practice Table)");
		cursor.y = addNumberedWrappedSmallText(document, cursor.page,
				"1. Please complete the following chart, providing a breakdown of the firm's Residential real estate practice based on revenue for the past fiscal year:",
				20, cursor.y, 560, false);
		cursor.y -= 6;
		String[] areas = { "a. Purchase and Sale", "b. Title Opinions", "c. Foreclosures / Loan Workouts",
				"d. Property Valuation / Real Estate Tax Abatement", "e. Eminent Domain", "f. Landlord Tenant",
				"g. Condominiums, Cooperatives or Homeowner Associations", "h. Leases", "i. Land Use / Development",
				"j. Speculative Real Estate, Limited Partnerships, Real Estate Syndications", "k. Real Estate Trusts",
				"l. Other" };
		String[] resiAopRevenuePercentage= {"AOPRE_PercentageValue_1","AOPRE_PercentageValueRes_22","AOPRE_PercentageValue_19","AOPRE_PercentageValue_7","AOPRE_PercentageValue_12","AOPRE_PercentageValue_6","AOPRE_PercentageValue_4","AOPRE_PercentageValueRes_23","AOPRE_PercentageValue_11","AOPRE_PercentageValue_13","AOPRE_PercentageValueRes_16","AOPRE_PercentageValue_20"};
		String[] commAopRevenuePercentage= {"AOPRE_PercentageValueCom_1","AOPRE_PercentageValueCom_22","AOPRE_PercentageValueCom_19","AOPRE_PercentageValueCom_7","AOPRE_PercentageValueCom_12","AOPRE_PercentageValueCom_6","AOPRE_PercentageValueCom_4","AOPRE_PercentageValueCom_23","AOPRE_PercentageValueCom_11","AOPRE_PercentageValueCom_13","AOPRE_PercentageValueRes_16","AOPRE_PercentageValueCom_20"};
		drawRealEstateRevenueTable(acroForm, document, cursor, ctx, "RESIDENTIAL REAL ESTATE AREA OF SPECIALTY",
				"grp_re_res_", areas, resiAopRevenuePercentage);
		cursor.y = addWrappedSmallText(document, cursor.page,
				"Please complete the following chart, providing a breakdown of the firm's Commercial real estate practice based on revenue for the past fiscal year:",
				20, cursor.y, 560, false);
		cursor.y -= 5;
		drawRealEstateRevenueTable(acroForm, document, cursor, ctx, "COMMERCIAL REAL ESTATE AREA OF SPECIALTY",
				"grp_re_com_", areas, commAopRevenuePercentage);
		float realEstateResidentialFieldX = 400f;
		float realEstateCommercialFieldX = 500f;
		int realEstateFieldWidth = 70;
		addSmallText(document, cursor.page, "Residential", realEstateResidentialFieldX, cursor.y, true);
		addSmallText(document, cursor.page, "Commercial", realEstateCommercialFieldX, cursor.y, true);
		cursor.y -= 16;
		float questionTwoY = cursor.y;
		float questionTwoBottomY = addNumberedWrappedSmallText(document, cursor.page,
				"2. What is the approximate number of transactions handled in the past 12 months?", 20, questionTwoY,
				360, false);
		addField(acroForm, cursor.page, "transactionsHandled12MonthsResi", getBlankIfNull(realEastateMap01,"transactionsHandled12MonthsResi"), realEstateResidentialFieldX, questionTwoY - 2, realEstateFieldWidth, 13);
		addField(acroForm, cursor.page, "transactionsHandled12MonthsComm", getBlankIfNull(realEastateMap01,"transactionsHandled12MonthsComm"), realEstateCommercialFieldX, questionTwoY - 2, realEstateFieldWidth, 13);
		cursor.y = questionTwoBottomY - 6;
		float questionThreeY = cursor.y;
		cursor.y = addNumberedWrappedSmallText(document, cursor.page,
				"3. What was the largest real estate transaction in the past 12 months?", 20, questionThreeY, 360, false);
		addSmallText(document, cursor.page, "$", realEstateResidentialFieldX - 10, questionThreeY, false);
		addField(acroForm, cursor.page, "largestTransaction12MonthsResi", getBlankIfNull(amountForRealEstateMap01,"largestTransaction12MonthsResi"), realEstateResidentialFieldX, questionThreeY - 2, realEstateFieldWidth, 13);
		addSmallText(document, cursor.page, "$", realEstateCommercialFieldX - 10, questionThreeY, false);
		addField(acroForm, cursor.page, "largestTransaction12MonthsComm", getBlankIfNull(amountForRealEstateMap01,"largestTransaction12MonthsComm"), realEstateCommercialFieldX, questionThreeY - 2, realEstateFieldWidth, 13);
		cursor.y -= 20;
		String[] realEstateQuestionFields = { "attendClosings", "includeSecuringFinancing" };
		String[] realEstateQuestions = {
				"4. Do non-attorney staff members ever attend closings on behalf of the firm in lieu of attorneys?",
				"5. Does the firm's practice include securing financing for its clients?" };
		cursor.y = addCompactYesNoQuestions(document, acroForm, cursor.page, realEastateMap01,
				realEstateQuestionFields, realEstateQuestions, 20, cursor.y);
		cursor.y = addYesNoNAQuestion(document, acroForm, cursor.page, "useDisclosureForm", getBlankIfNull(realEastateMap01,"useDisclosureForm"),
				"6. If you act in a dual capacity in the same real estate transaction, do you always use a disclosure form signed by both parties?",
				20, cursor.y);
	}

	private static void drawTaxSupplementSection(PDAcroForm acroForm, PDDocument document, LayoutCursor cursor,
			Context ctx) {

		drawSupplementHeader(document, cursor, "TAX SUPPLEMENT (as required in Question 6, Area of Practice Table)");
		cursor.y = addWrappedSmallText(document, cursor.page,
				"NOTE: THIS SUPPLEMENT IS ONLY REQUIRED TO BE COMPLETED IF THE PERCENTAGE FOR TAX IN THE AREA OF PRACTICE TABLE IS GREATER THAN 25%.",
				20, cursor.y, 560, true);
		cursor.y -= 8;
		addSmallText(document, cursor.page,
				"1. Please complete the following chart, providing a breakdown of the firm's tax practice based on revenue for the past fiscal year:",
				20, cursor.y, false);
		cursor.y -= 18;
		String[] left = { "a. Personal", "b. Corporate", "c. Estate Tax Returns", "d. Investment Counselor Services",
				"e. Subchapter S Elections" };
		String[] leftFields= {"Tax_PercentageValue_1","Tax_PercentageValue_2","Tax_PercentageValue_3","Tax_PercentageValue_4","Tax_PercentageValue_5"}; 
		String[] right = { "f. Liquidation of Corporations", "g. Opinions on Tax Shelters",
				"h. Opinions Involving Private Placement Memoranda", "i. Other (please describe):" };
		String[] rightFields= {"Tax_PercentageValue_6","Tax_PercentageValue_7","Tax_PercentageValue_8","Tax_PercentageValue_9"}; 
		addSmallText(document, cursor.page, "AREA OF TAX PRACTICE", 35, cursor.y, true);
		addSmallText(document, cursor.page, "%", 314, cursor.y, true);
		addSmallText(document, cursor.page, "AREA OF TAX PRACTICE", 325, cursor.y, true);
		addSmallText(document, cursor.page, "%", 588, cursor.y, true);
		cursor.y -= 16;
		drawTwoColumnPercentageRows(acroForm, document, cursor, ctx, left, leftFields, "grp_tax_left_",
				shortenLabels(right, 38, 35), rightFields, "grp_tax_right_", 35, 260, 314, 325, 535, 588, 16);
		cursor.y -= 10;
		addSmallText(document, cursor.page, "Description :", 35, cursor.y, false);
		addField(acroForm, cursor.page, "Tax_CommentDesc_9", getBlankIfNull(ctx, "Tax_CommentDesc_9"), 115,
				cursor.y - 18, 445, 18);
		cursor.y -= 40;
		String[] taxTotalFields = new String[leftFields.length + rightFields.length];
		String[] taxTotalValues = new String[leftFields.length + rightFields.length];
		for (int i = 0; i < leftFields.length; i++) {
			taxTotalFields[i] = "grp_tax_left_" + i;
			taxTotalValues[i] = getBlankIfNull(ctx, leftFields[i]);
		}
		for (int i = 0; i < rightFields.length; i++) {
			taxTotalFields[leftFields.length + i] = "grp_tax_right_" + i;
			taxTotalValues[leftFields.length + i] = getBlankIfNull(ctx, rightFields[i]);
		}
		addSmallText(document, cursor.page, "TOTAL (must equal 100%)", 325, cursor.y, true);
		addCalculatedTotalField(acroForm, cursor.page, "taxPracticeTotalPercent", sumFieldDefaults(taxTotalValues),
				535, cursor.y - 3, 48, 14, taxTotalFields);
		addSmallText(document, cursor.page, "%", 588, cursor.y, false);
		cursor.y -= 28;
	}

	private static void drawTrustsSupplementSection(PDAcroForm acroForm, PDDocument document, LayoutCursor cursor,
			Context ctx) {
		Map willsEstateMap01 = getMapIfPresent(ctx, "willsEstate_freeform_01");
		Map trustMap01 = getMapIfPresent(ctx, "Trust_list_01");
		Map trustMap02 = getMapIfPresent(ctx, "Trust_list_02");
		Map trustMap03 = getMapIfPresent(ctx, "Trust_list_03");
		
		drawSupplementHeader(document, cursor,
				"TRUSTS, WILLS, ESTATE, PROBATE SUPPLEMENT (as required in Question 6, Area of Practice Table)");
		cursor.y = addNumberedWrappedSmallText(document, cursor.page,
				"1. Please complete the following chart, providing a breakdown of the firm's trusts, wills, estate and probate practice based on revenue for the past fiscal year:",
				20, cursor.y, 560, false);
		cursor.y -= 8;
		String[] left = { "a. Preparation of Wills", "b. Estate Planning", "c. Probate", "d. Trust Administration",
				"e. Guardianship", "f. Medical Planning", "g. Trust Creation" };
		String[] leftFields = {"WESKey_PercentageValue_1","WESKey_PercentageValue_2","WESKey_PercentageValue_3","WESKey_PercentageValue_4","WESKey_PercentageValue_9","WESKey_PercentageValue_10","WESKey_PercentageValue_13"};
		String[] right = { "h. Corporation Formation", "i. Taxation", "j. Tax Opinions", "k. Asset Protection",
				"l. Litigation", "m. Other (please describe):" };
		String[] rightFields = {"WESKey_PercentageValue_5","WESKey_PercentageValue_7","WESKey_PercentageValue_6","WESKey_PercentageValue_8","WESKey_PercentageValue_11","WESKey_PercentageValue_12"};
		drawTwoColumnPercentageRows(acroForm, document, cursor, ctx, left, leftFields, "grp_trust_left_", right,
				rightFields, "grp_trust_right_", 35, 210, 264, 315, 510, 564, 15);
		cursor.y -= 14;
		addSmallText(document, cursor.page, "Description :", 35, cursor.y, false);
		addField(acroForm, cursor.page, "WESKey_CommentDesc_12", getBlankIfNull(ctx, "WESKey_CommentDesc_12"),
				115, cursor.y - 18, 445, 18);
		cursor.y -= 40;
		String[] trustTotalFields = new String[leftFields.length + rightFields.length];
		String[] trustTotalValues = new String[leftFields.length + rightFields.length];
		for (int i = 0; i < leftFields.length; i++) {
			trustTotalFields[i] = "grp_trust_left_" + i;
			trustTotalValues[i] = getBlankIfNull(ctx, leftFields[i]);
		}
		for (int i = 0; i < rightFields.length; i++) {
			trustTotalFields[leftFields.length + i] = "grp_trust_right_" + i;
			trustTotalValues[leftFields.length + i] = getBlankIfNull(ctx, rightFields[i]);
		}
		addSmallText(document, cursor.page, "TOTAL (must equal 100%)", 315, cursor.y, true);
		addCalculatedTotalField(acroForm, cursor.page, "trustsWillsEstateTotalPercent",
				sumFieldDefaults(trustTotalValues), 510, cursor.y - 3, 48, 14, trustTotalFields);
		addSmallText(document, cursor.page, "%", 564, cursor.y, false);
		cursor.y -= 22;
		cursor.y = addCompactYesNoQuestion(document, acroForm, cursor.page, "IsAttorneyServeAsExecutorTrustee", getBlankIfNull(willsEstateMap01,"IsAttorneyServeAsExecutorTrustee"),
				"2. Does any attorney currently serve as Personal Representative/Administrator or Trustee?", 20,
				cursor.y);
		float followUpY = cursor.y;
		float currentX = addSmallIfYesText(document, cursor.page, 35, followUpY, ":");
		addSmallText(document, cursor.page, "a.", currentX + 8, followUpY, false);
		float followUpTextX = currentX + 25;
		float followUpBottomY = addWrappedSmallText(document, cursor.page,
				"If you are acting as a Trustee, do your activities as a trustee include authority to make decisions resulting in the purchase or sale of securities, real estate or other investments?",
				followUpTextX, followUpY, 305, false);
		addYesNoNAButtons(document, acroForm, cursor.page, "ActingAsTrustee",
				getBlankIfNull(willsEstateMap01, "ActingAsTrustee"), 400, followUpY - 2);
		cursor.y = followUpBottomY - 8;

		followUpY = cursor.y;
		addSmallText(document, cursor.page, "b.", 35, followUpY, false);
		followUpBottomY = addWrappedSmallText(document, cursor.page,
				"If you make investment decisions, are all investment decisions made in accord with advice from a certified investment professional?",
				58, followUpY, 350, false);
		addYesNoNAButtons(document, acroForm, cursor.page, "CertifiedInvestmentDecisions",
				getBlankIfNull(willsEstateMap01, "CertifiedInvestmentDecisions"), 400, followUpY - 2);
		cursor.y = followUpBottomY - 11;
		cursor.y -= 3;
		addSmallText(document, cursor.page,
				"3. During the course of your professional practice, how many will, trust or estate matters are valued at the following amounts?",
				20, cursor.y, false);
		cursor.y -= 16;
		addSmallText(document, cursor.page, "$1,000,000 - $4,999,999", 35, cursor.y, false);
		addField(acroForm, cursor.page, "grp_trust_value_1", getBlankIfNull(trustMap01,"NumberOfMattersValued"), 190, cursor.y - 2, 45, 13);
		addSmallText(document, cursor.page, "$5,000,000 - $9,999,999", 255, cursor.y, false);
		addField(acroForm, cursor.page, "grp_trust_value_2", getBlankIfNull(trustMap02,"NumberOfMattersValued"), 420, cursor.y - 2, 45, 13);
		addSmallText(document, cursor.page, "$10,000,000 and over", 35, cursor.y - 16, false);
		addField(acroForm, cursor.page, "grp_trust_value_3", getBlankIfNull(trustMap03,"NumberOfMattersValued"), 190, cursor.y - 18, 45, 13);
		cursor.y -= 36;
		addSmallText(document, cursor.page,
				"4. For how many will, estate, trust or probate matters did you provide professional services in the past year?",
				20, cursor.y, false);
		addField(acroForm, cursor.page, "willEstatesHandledPastYear", getBlankIfNull(willsEstateMap01,"willEstatesHandledPastYear"), 520, cursor.y - 2, 45, 13);
		cursor.y -= 18;
		cursor.y = addCompactYesNoQuestion(document, acroForm, cursor.page, "beneficiaryInterestWillsTrust", getBlankIfNull(willsEstateMap01,"beneficiaryInterestWillsTrust"),
				"5. Do you have a beneficiary interest in any of the wills, trusts, probate and/or estates for which you provide services?",
				20, cursor.y);
	}

	private static void drawCannabisSupplementSection(PDAcroForm acroForm, PDDocument document, LayoutCursor cursor,
			Context ctx) {
		Map cannabisMap = getMapIfPresent(ctx, "policy_Cannabis_01");
		drawSupplementHeader(document, cursor, "CANNABIS SUPPLEMENT (as required in Question 6b)");
		cursor.y = addCompactYesNoQuestion(document, acroForm, cursor.page, "distributionWareinghouseCases",
				getBlankIfNull(cannabisMap, "distributionWareinghouseCases"),
				"1. Is your firm currently handling, previously handled or intending to handle any cases/files/transactions directly related to the distribution, warehousing, growing, manufacturing or selling of cannabis?",
				20, cursor.y);
		cursor.y -= 4;
		cursor.y = addNumberedWrappedSmallText(document, cursor.page,
				"2. Please confirm any involvement in the following:", 20, cursor.y, 560, false);
		cursor.y -= 6;

		String[] letters = { "a.", "b.", "c.", "d.", "e.", "f.", "g." };
		String[] questions = { "Licensing",
				"Services related to private offerings, investments, business valuations, or purchase or sale of cannabis enterprises",
				"Federal, state or local compliance", "Intellectual property including trademark matters",
				"Negotiating real estate purchase contracts for cannabis enterprises",
				"Representing clients in litigation matters involving complex issues related to the cannabis industry",
				"Other (If \"Yes\" please explain below)" };
		String[] fieldNames = { "cannibsLicensing", "canniabisInvestmentServices", "StateOrLocalCompliance",
				"IntellectualProperty", "NegotiatePurchaseContracts", "RepresentClientLitigation", "CannibsOther" };

		for (int i = 0; i < questions.length; i++) {
			float rowTopY = cursor.y;
			addSmallText(document, cursor.page, letters[i], 35, rowTopY, false);
			float rowBottomY = addWrappedSmallText(document, cursor.page, questions[i], 58, rowTopY, 410, false);
			addRadioButtons(document, acroForm, cursor.page, fieldNames[i], createCompactYesNoButtons(),
					getBlankIfNull(cannabisMap, fieldNames[i]), 485, rowTopY + 9);
			cursor.y = rowBottomY - 5;
		}

		addSmallText(document, cursor.page, "Other Description:", 35, cursor.y, false);
		addMultilineField(acroForm, cursor.page, "CannibsOtherDesc",
				getBlankIfNull(cannabisMap, "CannibsOtherDesc"), 125, cursor.y - 18, 430, 28);
		cursor.y -= 45;
	}

	private static void drawFeeSuitSupplementSection(PDAcroForm acroForm, PDDocument document, LayoutCursor cursor,
			Context ctx) {
		List lawsuits = getListIfPresent(ctx, "firmlawsuit_practice_list_01");
		drawSupplementHeader(document, cursor, "FEE SUIT SUPPLEMENT (as required in Question 18)");
		cursor.y = addNumberedWrappedSmallText(document, cursor.page,
				"1. Please complete the following for each suit the firm has filed within the past three years against a client for collection of fees due:",
				20, cursor.y, 560, false);
		cursor.y -= 8;
		String[] headers = { "Amount of Fees Sued For", "Date Fee Suit Filed",
				"Was There a Counter Claim or Allegation of Legal Malpractice?", "Disposition of Fee Suit *" };
		int[] widths = { 130, 115, 190, 125 };
		float tableX = 20;
		float tableTopY = cursor.y + 8;
		float headerHeight = 42f;
		float rowHeight = 22f;
		drawColumnTable(document, cursor.page, tableX, tableTopY, widths, 3, headerHeight, rowHeight);
		float currentX = tableX;
		for (int i = 0; i < headers.length; i++) {
			addWrappedText(document, cursor.page, headers[i], currentX + 4, tableTopY - 13, widths[i] - 8, boldFont,
					8.8f, 10f);
			currentX += widths[i];
		}
		cursor.y = tableTopY - headerHeight - 17;
		for (int row = 0; row < 3; row++) {
			Map map = getMapFromList(lawsuits, row);
			currentX = tableX;
			addText(document, cursor.page, "$", currentX + 5, cursor.y + 1, regularFont, SUPPLEMENT_FONT_SIZE);
			addField(acroForm, cursor.page, "AmountOfFeesSued_" + row, getBlankIfNull(map, "AmountOfFeesSued"), currentX + 20, cursor.y - 2, widths[0] - 25,
					13);
			currentX += widths[0];
			addField(acroForm, cursor.page, "SuitFilesDateFees_" + row, getBlankIfNull(map, "SuitFilesDateFees"), currentX + 4, cursor.y - 2, widths[1] - 8, 13);
			currentX += widths[1];
			addCoverageRadioButtons(document, acroForm, cursor.page, "IsAllegOfLegalMalPrac_" + row,
					createCompactYesNoButtons(), getBlankIfNull(map, "IsAllegOfLegalMalPrac"), new float[] { currentX + 35, currentX + 95 }, cursor.y - 1,
					new float[] { 24, 20 }, false);
			currentX += widths[2];
			addField(acroForm, cursor.page, "FeeSuitDesc_" + row, getBlankIfNull(map, "FeeSuitDesc"), currentX + 4, cursor.y - 2, widths[3] - 8,
					13);
			cursor.y -= rowHeight;
		}
		cursor.y = tableTopY - headerHeight - (3 * rowHeight) - 10;
		addWrappedSmallText(document, cursor.page,
				"*P = Fees paid in full, NS = Negotiated Settlement, JP = Judgment Plaintiff, JD = Judgment Defense, O = Open",
				20, cursor.y, 560, false);
	}

	private static void drawClaimSupplementSection(PDAcroForm acroForm, PDDocument document, LayoutCursor cursor,
			Context ctx) {
		Map map = getFirstMapFromContext(ctx, "claims_list_1");
		drawSupplementHeader(document, cursor, "CLAIM SUPPLEMENT (as required in Question 30)");
		addSmallText(document, cursor.page, "1. Full name of claimant or potential claimant:", 20, cursor.y, false);
		addField(acroForm, cursor.page, "grp_claimant_name", getBlankIfNull(map, "NameOfClaimant"), 245, cursor.y - 2, 190, 13);
		addRadioButtons(document, acroForm, cursor.page, "grp_claimant_type",
				createButtonList(createButton("Client", "CLIENT"), createButton("Non-Client", "NON_CLIENT")), getClientTypeValue(map),
				450, cursor.y - 3);
		cursor.y -= 28;
		addSmallText(document, cursor.page, "2. Is the claim in suit?", 20, cursor.y, false);
		addYesNoNAButtons(document, acroForm, cursor.page, "grp_claim_q2", getBlankIfNull(map, "IsClaimInSuit"), 180, cursor.y - 3);
		cursor.y -= 28;
		addSmallText(document, cursor.page, "3. Date you were notified of claim or became aware of error/incident:", 20,
				cursor.y, false);
		addField(acroForm, cursor.page, "grp_claim_notice_date", getBlankIfNull(map, "ClaimNotifiedDate"), 350, cursor.y - 2, 75, 13);
		cursor.y -= 18;
		addSmallText(document, cursor.page, "Date of alleged error:", 45, cursor.y, false);
		addField(acroForm, cursor.page, "grp_claim_error_date", getBlankIfNull(map, "DateOfAllegedError"), 165, cursor.y - 2, 85, 13);
		cursor.y -= 24;
		addSmallText(document, cursor.page, "4. Names of firm personnel involved in the claim or potential claim:", 20,
				cursor.y, false);
		addField(acroForm, cursor.page, "grp_claim_personnel", getBlankIfNull(map, "NameOfPersonnelINClaim"), 360, cursor.y - 2, 200, 13);
		cursor.y -= 24;
		addSmallText(document, cursor.page, "5. Description of claim or potential claim:", 20, cursor.y, false);
		addMultilineField(acroForm, cursor.page, "grp_claim_desc", getBlankIfNull(map, "DescOfClaim"), 20,
				cursor.y - 34, 540, 30);
		cursor.y -= 58;
		cursor.y = addCompactYesNoQuestion(document, acroForm, cursor.page, "grp_claim_q6", getBlankIfNull(map, "IsClaimReportedToInsComp"),
				"6. Has this claim or potential claim been reported to the firm's professional liability insurer?", 20,
				cursor.y);
		addSmallText(document, cursor.page, "7. Name of insurance company:", 20, cursor.y, false);
		addField(acroForm, cursor.page, "NameOfInsComp", getBlankIfNull(map, "NameOfInsComp"), 170, cursor.y - 2, 170, 13);
		addSmallText(document, cursor.page, "Date reported to insurance company:", 350, cursor.y, false);
		addField(acroForm, cursor.page, "DateReportedToInsComp", getBlankIfNull(map, "DateReportedToInsComp"), 520, cursor.y - 2, 70, 13);
		cursor.y -= 24;
		addSmallText(document, cursor.page, "8. If claim is now open, provide:", 20, cursor.y, false);
		addSmallText(document, cursor.page, "Insurer's loss reserve $", 210, cursor.y, false);
		addField(acroForm, cursor.page, "InsurerLoss", getBlankIfNull(map, "InsurerLoss"), 325, cursor.y - 2, 70, 13);
		addSmallText(document, cursor.page, "Claimant's last demand $", 400, cursor.y, false);
		addField(acroForm, cursor.page, "ClaimantLastDemand", getBlankIfNull(map, "ClaimantLastDemand"), 530, cursor.y - 2, 60, 13);
		cursor.y -= 18;
		addSmallText(document, cursor.page, "Current Status:", 45, cursor.y, false);
		addField(acroForm, cursor.page, "CurrentStatus", getBlankIfNull(map, "CurrentStatus"), 135, cursor.y - 2, 420, 13);
		cursor.y -= 24;
		addSmallText(document, cursor.page, "9. If closed, provide: date closed", 20, cursor.y, false);
		addField(acroForm, cursor.page, "ClaimClosingDate", getBlankIfNull(map, "ClaimClosingDate"), 185, cursor.y - 2, 80, 13);
		cursor.y -= 18;
		addSmallText(document, cursor.page, "Total amount paid (including damages and defense expenses): $", 20,
				cursor.y, false);
		addField(acroForm, cursor.page, "grp_claim_total_paid", getAmountWithoutCurrency(map, "TotalAmountPaid"), 345,
				cursor.y - 2, 100, 13);
		cursor.y -= 28;
		cursor.y = addNumberedWrappedSmallText(document, cursor.page,
				"10. What steps have you taken to prevent similar allegations from being made in future claims/incidents?",
				20, cursor.y, 560, false);
		cursor.y -= 4;
		addMultilineField(acroForm, cursor.page, "grp_claim_prevention", getBlankIfNull(map, "StepsTakenToPreventClaims"),
				20, cursor.y - 30, 540, 30);
	}

	// Helper for Yes/No/NA groups
	private static void addYesNoNAButtons(PDDocument document, PDAcroForm acroForm, PDPage page, String groupName,
			String defaultValue, float x, float y) {
		List<Button> buttons = createYesNoNAButtons();
		addRadioButtons(document, acroForm, page, groupName, buttons, defaultValue,
				getRightAlignedRadioX(page, buttons), y);
	}

	private static class Button {
		private String name = "";
		private String value = "";

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
