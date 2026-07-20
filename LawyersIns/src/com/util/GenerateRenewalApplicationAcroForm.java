package com.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
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
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.graphics.color.PDGamma;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckbox;
import org.apache.pdfbox.pdmodel.interactive.form.PDRadioCollection;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextbox;

public class GenerateRenewalApplicationAcroForm {
	private static final InetLogger logger = InetLogger.getInetLogger(GenerateRenewalApplicationAcroForm.class);
    private static final String WIDGET_SUBTYPE = "Widget";
    private static final String BUTTON_FIELD_TYPE = "Btn";
    private static final int ANNOTATION_PRINT_FLAG = 4;
    private static final int RADIO_BUTTON_FLAG = 1 << 15;
    private static final int TEXT_FIELD_MULTILINE_FLAG = 1 << 12;
    private static final int ATTORNEY_ROSTER_ROW_COUNT = 25;
    private static final String DEFAULT_OUTPUT_PDF =
            "D:/LPL-Renewal-Application-AcroForm.pdf";
    private static final String LOGO_IMAGE_NAME = "ISMIE_logo.png";
    private static final String LOGO_IMAGE_PATH = "web/image/" + LOGO_IMAGE_NAME;
    private static final String FALLBACK_LOGO_IMAGE_PATH = "D:/ISMIELogo.jpg";
    private static final String FORM_FONT_RESOURCE_NAME = "Helv";
    private static final String ARIAL_REGULAR = "C:/Windows/Fonts/arial.ttf";
    private static final String ARIAL_BOLD = "C:/Windows/Fonts/arialbd.ttf";
    private static final String ARIAL_ITALIC = "C:/Windows/Fonts/ariali.ttf";
    private static final String ARIAL_BOLD_ITALIC = "C:/Windows/Fonts/arialbi.ttf";
    private static final String FORM_NUMBER = "ISMIE ALA-04-S002 (09/01/2021)";
    private static final float PAGE_MARGIN = 20f;
    private static final float BODY_FONT_SIZE = 10f;
    private static final float SMALL_FONT_SIZE = 9f;
    private static final float FORM_FIELD_FONT_SIZE = 10f;
    private static final float FRAUD_NOTICE_FONT_SIZE = 9.11f;
    private static final float FRAUD_NOTICE_LINE_HEIGHT = 11.5f;
    private static final float GLOBAL_FONT_SCALE = 1.0f;
    private static final float MIN_TEXT_FONT_SIZE = 6f;
    private static final float MIN_TEXT_FIELD_HEIGHT = 13f;
    private static final float RADIO_BUTTON_SIZE = 10f;
    private static final float RADIO_LABEL_GAP = 4f;
    private static final float RADIO_OPTION_GAP = 16f;
    private static final float ROW_HEIGHT = 18f;

    private static PDFont REGULAR_FONT = PDType1Font.HELVETICA;
    private static PDFont BOLD_FONT = PDType1Font.HELVETICA_BOLD;
    private static PDFont ITALIC_FONT = PDType1Font.HELVETICA_OBLIQUE;
    private static PDFont BOLD_ITALIC_FONT = PDType1Font.HELVETICA_BOLD_OBLIQUE;

    private static final Map<String, Integer> FIELD_NAME_COUNTS = new HashMap<String, Integer>();

    public static void main(String[] args) {
        File outputFile = new File(args != null && args.length > 0 ? args[args.length - 1] : DEFAULT_OUTPUT_PDF);

        try {
        	logger.debug("Creating Renewal AcroForm PDF at " + outputFile.getAbsolutePath());

            File outputDir = outputFile.getParentFile();
            if (outputDir != null && !outputDir.exists()) {
                outputDir.mkdirs();
            }

            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(outputFile);
                generatePdf(outputStream);
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        
            logger.debug("Renewal AcroForm PDF created successfully at " + outputFile.getAbsolutePath());
        } catch (Exception e) {
            logger.error("Unexpected error", e);
        }
    }

    public static void generateFillablePdf(Context ctx,String filePath) throws IOException {
    	  File outputFile = new File(filePath);

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outputFile);
            generatePdf(ctx, outputStream);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public static void generatePdf(OutputStream outputStream) throws IOException {
        generatePdf(null, outputStream);
    }

    public static void generatePdf(Context ctx, OutputStream outputStream) throws IOException {
        PDDocument document = null;
        try {
            FIELD_NAME_COUNTS.clear();
            document = new PDDocument();
            buildDocument(document, ctx);
            saveDocument(document, outputStream);
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }

    private static void buildDocument(PDDocument document, Context ctx) {
        loadArialFonts(document);
        PDAcroForm acroForm = createAcroForm(document);
        drawCoreRenewalApplicationSections(acroForm, document, ctx);
        drawRenewalSupplementSections(acroForm, document, ctx);
        addPageFooters(document);
    }

    private static void loadArialFonts(PDDocument document) {
        REGULAR_FONT = loadTrueTypeFont(document, ARIAL_REGULAR, PDType1Font.HELVETICA);
        BOLD_FONT = loadTrueTypeFont(document, ARIAL_BOLD, PDType1Font.HELVETICA_BOLD);
        ITALIC_FONT = loadTrueTypeFont(document, ARIAL_ITALIC, PDType1Font.HELVETICA_OBLIQUE);
        BOLD_ITALIC_FONT = loadTrueTypeFont(document, ARIAL_BOLD_ITALIC, PDType1Font.HELVETICA_BOLD_OBLIQUE);
    }

    private static PDFont loadTrueTypeFont(PDDocument document, String fontPath, PDFont fallbackFont) {
        try {
            File fontFile = new File(fontPath);
            if (fontFile.exists()) {
                return PDTrueTypeFont.loadTTF(document, fontFile);
            }
        } catch (IOException e) {
            logger.debug("Warning: Could not load font " + fontPath + ". Using PDFBox Helvetica fallback.");
        }
        return fallbackFont;
    }

    private static PDAcroForm createAcroForm(PDDocument document) {
        PDResources resources = new PDResources();
        resources.addFont(REGULAR_FONT, FORM_FONT_RESOURCE_NAME);

        PDAcroForm acroForm = new PDAcroForm(document);
        document.getDocumentCatalog().setAcroForm(acroForm);
        acroForm.setDefaultResources(resources);
        acroForm.setFields(new ArrayList());
        acroForm.getDictionary().setItem(COSName.DA, new COSString(getTextFieldDefaultAppearance()));
        acroForm.getDictionary().setBoolean(COSName.getPDFName("NeedAppearances"), false);
        return acroForm;
    }

    private static String getTextFieldDefaultAppearance() {
        return "/" + FORM_FONT_RESOURCE_NAME + " " + formatPdfNumber(FORM_FIELD_FONT_SIZE) + " Tf 0 g";
    }

    private static String blankIfNull(Object value) {
        return value == null ? "" : value.toString();
    }

    private static String getBlankIfNull(Map map, String key) {
        return map == null ? "" : blankIfNull(map.get(key));
    }

    private static String getBlankIfDefaultDate(Map map, String key) {
        String value = getBlankIfNull(map, key).trim();
        String normalized = value.toLowerCase(Locale.US);
        if (normalized.startsWith("01/01/1900") || normalized.startsWith("1/1/1900")
                || normalized.startsWith("1900-01-01") || normalized.startsWith("01-01-1900")) {
            return "";
        }
        return value;
    }

    private static String getBlankIfNull(Context ctx, String key) {
        return ctx == null ? "" : blankIfNull(ctx.get(key));
    }

    private static String getFirstNonBlank(Context ctx, String[] keys) {
        if (ctx == null || keys == null) {
            return "";
        }
        for (int i = 0; i < keys.length; i++) {
            String value = getBlankIfNull(ctx, keys[i]).trim();
            if (value.length() > 0) {
                return value;
            }
        }
        return "";
    }

    private static String getAmountWithoutCurrency(Map map, String key) {
        String amount = getBlankIfNull(map, key).trim().replace("$", "").replace("\u20B9", "").trim();
        while (amount.length() > 0 && !Character.isDigit(amount.charAt(0)) && amount.charAt(0) != '-') {
            amount = amount.substring(1).trim();
        }
        return amount;
    }

    private static String getWebsiteAddress(Map policyMap, Map firmMap) {
        String[] websiteKeys = { "Website", "WebsiteAddress", "WebSite", "FirmWebsite", "AccountWebsite" };
        for (int i = 0; i < websiteKeys.length; i++) {
            String value = getBlankIfNull(policyMap, websiteKeys[i]).trim();
            if (value.length() > 0 && !looksLikePhysicalAddress(value)) {
                return value;
            }
        }
        for (int i = 0; i < websiteKeys.length; i++) {
            String value = getBlankIfNull(firmMap, websiteKeys[i]).trim();
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
        return normalized.matches("^[0-9]+\\s+.*")
                && (normalized.indexOf(" street") >= 0 || normalized.indexOf(" st") >= 0
                        || normalized.indexOf(" avenue") >= 0 || normalized.indexOf(" ave") >= 0
                        || normalized.indexOf(" road") >= 0 || normalized.indexOf(" rd") >= 0
                        || normalized.indexOf(" lane") >= 0 || normalized.indexOf(" ln") >= 0
                        || normalized.indexOf(" drive") >= 0 || normalized.indexOf(" dr") >= 0
                        || normalized.indexOf(" boulevard") >= 0 || normalized.indexOf(" blvd") >= 0
                        || normalized.indexOf(" suite") >= 0);
    }

    private static Map getMapIfPresent(Context ctx, String key) {
        if (ctx == null) {
            return Collections.EMPTY_MAP;
        }
        Object value = ctx.get(key);
        return value instanceof Map ? (Map) value : Collections.EMPTY_MAP;
    }

    private static List getListIfPresent(Context ctx, String key) {
        if (ctx == null) {
            return Collections.EMPTY_LIST;
        }
        Object value = ctx.get(key);
        return value instanceof List ? (List) value : Collections.EMPTY_LIST;
    }

    private static Map getMapFromList(List list, int index) {
        if (list == null || index < 0 || index >= list.size()) {
            return Collections.EMPTY_MAP;
        }
        Object value = list.get(index);
        return value instanceof Map ? (Map) value : Collections.EMPTY_MAP;
    }

    private static Map getFirstMapFromContext(Context ctx, String key) {
        Object value = ctx == null ? null : ctx.get(key);
        if (value instanceof Map) {
            return (Map) value;
        }
        return value instanceof List ? getMapFromList((List) value, 0) : Collections.EMPTY_MAP;
    }

    private static String getYesNoValue(Map map, String key) {
        return normalizeYesNo(getBlankIfNull(map, key));
    }

    private static String normalizeYesNo(String value) {
        if ("Y".equalsIgnoreCase(value) || "YES".equalsIgnoreCase(value)) {
            return "Yes";
        }
        if ("N".equalsIgnoreCase(value) || "NO".equalsIgnoreCase(value)) {
            return "No";
        }
        return value == null ? "" : value;
    }

    private static String normalizeYesNoNA(String value) {
        if ("N/A".equalsIgnoreCase(value)) {
            return "NA";
        }
        return normalizeYesNo(value);
    }

    private static String getClientTypeValue(Map map) {
        String value = getBlankIfNull(map, "IsClient").trim();
        if ("Y".equalsIgnoreCase(value) || "CLIENT".equalsIgnoreCase(value) || "Client".equals(value)) {
            return "Client";
        }
        if ("N".equalsIgnoreCase(value) || "NON_CLIENT".equalsIgnoreCase(value) || "NonClient".equals(value)) {
            return "NonClient";
        }
        return value;
    }

    private static String getRenewalRevenueValue(Map firmMap) {
        String value = getBlankIfNull(firmMap, "AnnualRevenueSequence").trim();
        if ("1".equals(value)) {
            return "0To100000";
        }
        if ("2".equals(value)) {
            return "100000To250000";
        }
        if ("3".equals(value)) {
            return "250000To500000";
        }
        if ("4".equals(value)) {
            return "500000To750000";
        }
        if ("5".equals(value)) {
            return "750000To1000000";
        }
        if ("6".equals(value) || "7".equals(value) || "8".equals(value)) {
            return "1000000Plus";
        }
        return value;
    }

    private static Map getAreaPracticeRightMap(List aopList1, List aopList2, String[][] rows, int rowIndex) {
        if (rows[rowIndex][1] == null || rows[rowIndex][1].trim().length() == 0) {
            return Collections.EMPTY_MAP;
        }
        if (rowIndex == 0) {
            return getMapFromList(aopList1, 34);
        }
        int sourceIndex = -1;
        for (int i = 1; i <= rowIndex; i++) {
            if (rows[i][1] != null && rows[i][1].trim().length() > 0) {
                sourceIndex++;
            }
        }
        return sourceIndex >= 0 ? getMapFromList(aopList2, sourceIndex) : Collections.EMPTY_MAP;
    }

    private static Map getAreaPracticeTotalMap(List aopList2) {
        Map totalMap = getMapFromList(aopList2, 30);
        if ("25".equals(getBlankIfNull(totalMap, "AOPKey"))) {
            totalMap = getMapFromList(aopList2, 31);
        }
        return totalMap;
    }

    private static String getFamilyLawOtherDescription(Context ctx) {
        String value = getFirstNonBlank(ctx, new String[] {
                "FLAOP_OtherDescription", "FLAOPCommentDesc", "FLAO_CommentDesc_7", "FLAOP_CommentDesc_7" });
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
        return "";
    }

    private static void drawCoreRenewalApplicationSections(PDAcroForm acroForm, PDDocument document, Context ctx) {
        drawFirmProfileSection(acroForm, document, ctx);
        drawAttorneyRosterSection(acroForm, document, ctx);
        drawAreaOfPracticeSection(acroForm, document, ctx);
        drawClaimsAndFirmUpdatesSection(acroForm, document, ctx);
        drawFraudNoticeSection(document);
        drawSignatureSection(acroForm, document);
    }

    private static void drawFirmProfileSection(PDAcroForm acroForm, PDDocument document, Context ctx) {
        try {
            Map policyMap = getMapIfPresent(ctx, "policy_freeform_01");
            Map firmMap = getMapIfPresent(ctx, "firm_freeform_01");
            PDPage page = createPage(document);
            float x = PAGE_MARGIN;
            float y = 684;

            addLogoToPage(document, page, x - 8, y, 220, 64);
            y = 648.5f;
            addCenteredText(document, page, "LAWYERS PROFESSIONAL LIABILITY RENEWAL APPLICATION", y, BOLD_FONT, 13f);
            y = 624.7f;
            addFirstPageNoticeText(document, page, x - 2, y);
            y = 567.1f;
            addFirstPageBoldTypefaceText(document, page, x - 2, y);
            y = 532.6f;
            addFirstPageApplicationText(document, page, x - 2, y);
            y = 464.5f;
            addText(document, page,
                    "Wherever the word \u201CYou, Your or Firm\u201D is used, it will be deemed to include all attorneys within the firm and any predecessor firms.",
                    x + 4, y, BOLD_FONT, 8.18f);
            drawLine(document, page, x - 4, y - 4, x + 580, y - 4, 0.5f);
            y = 434.3f;

            drawSectionHeader(document, page, "FIRM PROFILE", x - 6, y, 586);
            y -= 24;

            addText(document, page, "1.", x + 4, y + 3, false);
            addText(document, page, "Firm Name:", x + 32, y + 3, false);
            addUnderlinedField(document, acroForm, page, "firmName", getBlankIfNull(policyMap, "AccountName"),
                    x + 84, y, 496, 14);
            addText(document, page, "Legal name of the Firm to be insured", x + 252, y - 10, ITALIC_FONT, SMALL_FONT_SIZE);
            y -= 34;

            addText(document, page, "Address:", x + 32, y + 3, false);
            addUnderlinedField(document, acroForm, page, "address", getBlankIfNull(policyMap, "Street"),
                    x + 84, y, 130, 14);
            addText(document, page, "Physical location of Principal office", x + 218, y + 3, ITALIC_FONT, SMALL_FONT_SIZE);
            y -= 34;

            addText(document, page, "City:", x + 32, y + 3, false);
            addUnderlinedField(document, acroForm, page, "city", getBlankIfNull(policyMap, "City"),
                    x + 84, y, 130, 14);
            addText(document, page, "County:", x + 224, y + 3, false);
            addUnderlinedField(document, acroForm, page, "county", getBlankIfNull(policyMap, "CountyDesc"),
                    x + 262, y, 120, 14);
            addText(document, page, "State:", x + 390, y + 3, false);
            addUnderlinedField(document, acroForm, page, "state", getBlankIfNull(policyMap, "StateDesc"),
                    x + 420, y, 48, 14);
            addText(document, page, "Zip:", x + 474, y + 3, false);
            addUnderlinedField(document, acroForm, page, "zip", getBlankIfNull(policyMap, "Zip")+"-"+getBlankIfNull(policyMap, "Zip4"),
                    x + 500, y, 80, 14);
            y -= 30;

            addText(document, page, "Primary Contact Name:", x + 32, y + 3, false);
            addUnderlinedField(document, acroForm, page, "primaryContactName",
                    getBlankIfNull(policyMap, "ContactPerson"), x + 140, y, 155, 14);
            addText(document, page, "Secondary Contact Name:", x + 296, y + 3, false);
            addUnderlinedField(document, acroForm, page, "secondaryContactName",
                    getBlankIfNull(firmMap, "secondayContactPerson"), x + 425, y, 155, 14);
            y -= 30;

            addText(document, page, "Phone Number:", x + 32, y + 3, false);
            addUnderlinedField(document, acroForm, page, "primaryPhoneNumber",
                    getBlankIfNull(policyMap, "WorkPhone"), x + 132, y, 78, 14);
            addText(document, page, "Secondary Phone Number:", x + 296, y + 3, false);
            addUnderlinedField(document, acroForm, page, "secondaryPhoneNumber",
                    getBlankIfNull(policyMap, "otherPhone"), x + 425, y, 155, 14);
            y -= 30;

            addText(document, page, "Email:", x + 32, y + 3, false);
            addUnderlinedField(document, acroForm, page, "primaryEmail", getBlankIfNull(policyMap, "AccountEmail"),
                    x + 66, y, 175, 14);
            addText(document, page, "Secondary Email:", x + 272, y + 3, false);
            addUnderlinedField(document, acroForm, page, "secondaryEmail",
                    getBlankIfNull(policyMap, "secondaryEmail"), x + 370, y, 210, 14);
            y -= 30;

            addText(document, page, "Website Address:", x + 32, y + 3, false);
            addUnderlinedField(document, acroForm, page, "websiteAddress", getWebsiteAddress(policyMap, firmMap),
                    x + 132, y, 448, 14);
        } catch (Exception e) {
            logger.error("Unexpected error", e);
        }
    }

    private static void addFirstPageNoticeText(PDDocument document, PDPage page, float x, float y) {
        float textSize = BODY_FONT_SIZE;
        float lineHeight = 11.5f;
        addText(document, page,
                "NOTICE: COVERAGE FOR WHICH THIS APPLICATION IS MADE IS WRITTEN ON A CLAIMS MADE AND REPORTED BASIS",
                x, y, REGULAR_FONT, textSize);

        y -= lineHeight;
        float nextX = addTextRun(document, page,
                "MEANING, EXCEPT AS OTHERWISE PROVIDED, COVERAGE APPLIES ONLY TO ", x, y, REGULAR_FONT, textSize);
        nextX = addTextRun(document, page, "CLAIMS", nextX, y, BOLD_FONT, textSize);
        addTextRun(document, page, " FIRST MADE AGAINST THE", nextX, y, REGULAR_FONT, textSize);

        y -= lineHeight;
        nextX = addTextRun(document, page, "INSURED", x, y, BOLD_FONT, textSize);
        nextX = addTextRun(document, page, " AND REPORTED TO THE ", nextX, y, REGULAR_FONT, textSize);
        nextX = addTextRun(document, page, "INSURER", nextX, y, BOLD_FONT, textSize);
        nextX = addTextRun(document, page, " DURING THE ", nextX, y, REGULAR_FONT, textSize);
        nextX = addTextRun(document, page, "POLICY PERIOD", nextX, y, BOLD_FONT, textSize);
        addTextRun(document, page, " OR DURING ANY APPLICABLE EXTENDED", nextX, y, REGULAR_FONT, textSize);

        y -= lineHeight;
        addTextRun(document, page, "REPORTING PERIOD.", x, y, REGULAR_FONT, textSize);
    }

    private static void addFirstPageBoldTypefaceText(PDDocument document, PDPage page, float x, float y) {
        float textSize = BODY_FONT_SIZE;
        float lineHeight = 11.5f;
        addText(document, page,
                "CAREFULLY READ THE ENTIRE POLICY FOR WHICH THIS APPLICATION IS MADE. WORDS AND PHRASES WHICH ARE",
                x, y, REGULAR_FONT, textSize);
        y -= lineHeight;
        float nextX = addTextRun(document, page, "PRINTED IN ", x, y, REGULAR_FONT, textSize);
        nextX = addTextRun(document, page, "BOLD TYPEFACE", nextX, y, BOLD_FONT, textSize);
        addTextRun(document, page,
                " HAVE SPECIFIC MEANING AND ARE DEFINED IN SECTION IV. OF THE POLICY.",
                nextX, y, REGULAR_FONT, textSize);
    }

    private static void addFirstPageApplicationText(PDDocument document, PDPage page, float x, float y) {
        float textSize = BODY_FONT_SIZE;
        float lineHeight = 11.5f;
        addText(document, page,
                "THE APPLICATION, ITS ATTACHMENTS AND ALL PREVIOUS APPLICATIONS AND THEIR ATTACHMENTS SHALL SERVE",
                x, y, REGULAR_FONT, textSize);
        y -= lineHeight;
        addText(document, page,
                "AS THE BASIS OF THE POLICY AND SHALL BECOME PART OF SUCH POLICY SHOULD A POLICY BE ISSUED, AS IF",
                x, y, REGULAR_FONT, textSize);
        y -= lineHeight;
        float nextX = addTextRun(document, page, "PHYSICALLY ATTACHED. THE ", x, y, REGULAR_FONT, textSize);
        nextX = addTextRun(document, page, "INSURER", nextX, y, BOLD_FONT, textSize);
        nextX = addTextRun(document, page, " RELIES UPON THE APPLICATION IN ISSUING THE ", nextX, y, REGULAR_FONT, textSize);
        addTextRun(document, page, "POLICY. COMPLETION OF", nextX, y, REGULAR_FONT, textSize);
        y -= lineHeight;
        addText(document, page,
                "THIS APPLICATION DOES NOT IN ANY WAY IMPLY SUCH COVERAGE UNDER THE POLICY. COVERAGE IS AFFORDED",
                x, y, REGULAR_FONT, textSize);
        y -= lineHeight;
        addText(document, page,
                "ONLY IF AND TO THE EXTENT INDICATED BY THE TERMS AND CONDITIONS OF THE POLICY IF ISSUED.",
                x, y, REGULAR_FONT, textSize);
    }

    private static void drawAttorneyRosterSection(PDAcroForm acroForm, PDDocument document, Context ctx) {
        try {
            List attorneyList = getListIfPresent(ctx, "attorneys_firm_list_01");
            Map firmMap = getMapIfPresent(ctx, "firm_freeform_01");
            PDPage page = createPage(document);
            float x = PAGE_MARGIN;
            float y = 742;

            addText(document, page, "ATTORNEYS", PAGE_MARGIN, 770, BOLD_FONT, 1f);
            addText(document, page, "2.", PAGE_MARGIN + 4, y, false);
            addWrappedText(document, page,
                    "Has the status of any of your attorneys changed in the past year or have any attorneys left or joined the firm?",
                    x + 4, y, 420, REGULAR_FONT, BODY_FONT_SIZE, 11f);
            addYesNoButtons(document, acroForm, page, "IsAttorneyAddedDeleted",
                    getYesNoValue(firmMap, "IsAttorneyAddedDeleted"), 512, y);
            addTextWithBoldYes(document, page, "If \u201CYes\u201D, please update the list below:", x + 4, y - 28,
                    ITALIC_FONT, BODY_FONT_SIZE);
            y = 620;

            String[] headers = { "Attorney Name", "Attorney Designation", "States Licensed", "Annual Hours",
                    "Date Joined", "# Years in Practice" };
            float[] widths = { 120, 110, 90, 80, 70, 90 };
            drawAttorneyRosterHeader(document, page, headers, widths, x, y);
            y -= ROW_HEIGHT;
            int populatedRows = Math.min(attorneyList.size(), ATTORNEY_ROSTER_ROW_COUNT);
            for (int row = 0; row < ATTORNEY_ROSTER_ROW_COUNT; row++) {
                Map attorneyMap = row < populatedRows ? getMapFromList(attorneyList, row) : Collections.EMPTY_MAP;
                drawAttorneyRosterRow(document, acroForm, page, x, y, widths, row, attorneyMap);
                y -= ROW_HEIGHT;
            }

            y -= 10;
            addText(document, page, "*Attorney Designations:", x, y, BOLD_FONT, BODY_FONT_SIZE);
            y -= 11;
            y = addWrappedText(document, page,
                    "A=Associate, E=Employee, IC=Independent Contractor, OC=Of Counsel, P=Partner, "
                            + "RP=Retired Partner, O=Owner, S=Solo Practitioner.",
                    x, y, 560, REGULAR_FONT, BODY_FONT_SIZE, 11f);
        } catch (Exception e) {
            logger.error("Unexpected error", e);
        }
    }

    private static void drawAreaOfPracticeSection(PDAcroForm acroForm, PDDocument document, Context ctx) {
        try {
            List aopList1 = getListIfPresent(ctx, "aopData_list_01");
            List aopList2 = getListIfPresent(ctx, "aopData_list_02");
            Map firmMap = getMapIfPresent(ctx, "firm_freeform_01");
            PDPage page = createPage(document);
            float x = PAGE_MARGIN;
            float y = 742;
            drawSectionHeader(document, page, "AREA OF PRACTICE", x, y);
            y -= 28;

            y = addQuestionWithYesNo(document, acroForm, page, "areasOfPracticeChanged",
                    "3. a. Has the distribution of your Areas of Practice changed?", x, y, 420,
                    getYesNoValue(firmMap, "IsAOPChange"));
            addTextWithBoldYes(document, page, "If Yes, please update the list below:", x + 18, y + 4,
                    ITALIC_FONT, SMALL_FONT_SIZE);
            y -= 16;

            float[] areaWidths = { 214, 85, 193, 80 };
            float labelPadding = 8f;
            String[][] rows = createAreaOfPracticeRows();
            float rowHeight = 13.5f;
            float rowLineHeight = SMALL_FONT_SIZE + 1f;
            float[] rowHeights = new float[rows.length];
            float rowsHeight = 0f;
            for (int i = 0; i < rows.length; i++) {
                int leftLines = wrapText(REGULAR_FONT, rows[i][0], SMALL_FONT_SIZE,
                        areaWidths[0] - (labelPadding * 2)).size();
                int rightLines = wrapText(REGULAR_FONT, rows[i][1], SMALL_FONT_SIZE,
                        areaWidths[2] - (labelPadding * 2)).size();
                rowHeights[i] = Math.max(rowHeight, Math.max(leftLines, rightLines) * rowLineHeight + 2f);
                rowsHeight += rowHeights[i];
            }
            float tableWidth = areaWidths[0] + areaWidths[1] + areaWidths[2] + areaWidths[3];
            float tableTopY = y + 12;
            float tableHeight = 16 + rowsHeight + rowHeight;
            drawBox(document, page, x, tableTopY - tableHeight, tableWidth, tableHeight, false);
            addCenteredTextInCell(document, page, "AREA OF PRACTICE", x, y, areaWidths[0], BOLD_FONT, SMALL_FONT_SIZE);
            addCenteredTextInCell(document, page, "% OF REVENUE", x + areaWidths[0], y, areaWidths[1], BOLD_FONT,
                    SMALL_FONT_SIZE);
            addCenteredTextInCell(document, page, "AREA OF PRACTICE", x + areaWidths[0] + areaWidths[1], y,
                    areaWidths[2], BOLD_FONT, SMALL_FONT_SIZE);
            addCenteredTextInCell(document, page, "% OF REVENUE",
                    x + areaWidths[0] + areaWidths[1] + areaWidths[2], y, areaWidths[3], BOLD_FONT,
                    SMALL_FONT_SIZE);
            y -= 14;

            String[] areaTotalFields = new String[rows.length * 2];
            String[] areaTotalValues = new String[rows.length * 2];
            String litigationDefenseOtherDescription = "";
            String litigationPlaintiffOtherDescription = "";
            String otherAopDescription = "";
            for (int i = 0; i < rows.length; i++) {
                Map leftMap = getMapFromList(aopList1, i);
                float currentRowHeight = rowHeights[i];
                float rowTextY = y - SMALL_FONT_SIZE;
                float fieldY = y - currentRowHeight + Math.max(1f, (currentRowHeight - 10f) / 2f);
                String leftFieldName = "areaPracticeLeft" + (i + 1);
                String leftValue = formatWholeNumber(getBlankIfNull(leftMap, "percentage"));
                addTextInBox(document, page, rows[i][0], x + labelPadding, rowTextY,
                        areaWidths[0] - (labelPadding * 2), false, SMALL_FONT_SIZE, currentRowHeight);
                addWholeNumberField(acroForm, page, leftFieldName, leftValue, x + areaWidths[0] + 34, fieldY - 1, 38, 12);
                addText(document, page, "%", x + areaWidths[0] + 76, fieldY + 2.5f, REGULAR_FONT, SMALL_FONT_SIZE);
                addTextInBox(document, page, rows[i][1], x + areaWidths[0] + areaWidths[1] + labelPadding,
                        rowTextY, areaWidths[2] - (labelPadding * 2), false, SMALL_FONT_SIZE, currentRowHeight);
                Map rightMap = getAreaPracticeRightMap(aopList1, aopList2, rows, i);
                if (i == 4) {
                    litigationDefenseOtherDescription = getBlankIfNull(rightMap, "AOPCommentDesc");
                } else if (i == 6) {
                    litigationPlaintiffOtherDescription = getBlankIfNull(rightMap, "AOPCommentDesc");
                } else if (i == 32) {
                    otherAopDescription = getBlankIfNull(rightMap, "AOPCommentDesc");
                }
                String rightFieldName = "areaPracticeRight" + (i + 1);
                String rightValue = formatWholeNumber(getBlankIfNull(rightMap, "percentage"));
                addWholeNumberField(acroForm, page, rightFieldName, rightValue,
                        x + areaWidths[0] + areaWidths[1] + areaWidths[2] + 28, fieldY - 1, 38, 12);
                addText(document, page, "%", x + areaWidths[0] + areaWidths[1] + areaWidths[2] + 70, fieldY + 2.5f,
                        REGULAR_FONT, SMALL_FONT_SIZE);
                areaTotalFields[i * 2] = leftFieldName;
                areaTotalFields[i * 2 + 1] = rightFieldName;
                areaTotalValues[i * 2] = leftValue;
                areaTotalValues[i * 2 + 1] = rightValue;
                y -= currentRowHeight;
            }

            addText(document, page, "TOTAL (must equal 100%)", x + areaWidths[0] + areaWidths[1] + 4, y + 2.8f,
                    BOLD_FONT, SMALL_FONT_SIZE);
            addCalculatedTotalField(acroForm, page, "areaPracticeTotalPercent", sumFieldDefaults(areaTotalValues),
                    x + areaWidths[0] + areaWidths[1] + areaWidths[2] + 28, y + 1, 38, 12, areaTotalFields);
            addText(document, page, "%", x + areaWidths[0] + areaWidths[1] + areaWidths[2] + 70, y + 3.5f,
                    REGULAR_FONT, SMALL_FONT_SIZE);

            y -= 12;
            y = addAreaPracticeDescriptionFields(document, acroForm, page, x, y,
                    new String[] { "Litigation defense other Description :",
                            "Litigation plaintiff other Description :", "Other Aop Description :" },
                    new String[] { "areaPracticeLitigationDefenseOtherDescription",
                            "areaPracticeLitigationPlaintiffOtherDescription", "areaPracticeOtherAopDescription" },
                    new String[] { litigationDefenseOtherDescription, litigationPlaintiffOtherDescription,
                            otherAopDescription });
            y -= 10;
            float inlineX = addTextRun(document, page,
                    "If you practice in any area(s) above with a numerical notation(s), complete the associated ",
                    x, y, ITALIC_FONT, SMALL_FONT_SIZE);
            inlineX = addTextRun(document, page, "Supplement", inlineX, y, BOLD_ITALIC_FONT, SMALL_FONT_SIZE);
            addTextRun(document, page, " as follows:", inlineX, y, ITALIC_FONT, SMALL_FONT_SIZE);
            y -= 12;
            y -= 10;
            drawBox(document, page, x, y - 21, 550, 30, true);
            addText(document, page, "(1) Bankruptcy          (4) Family Law          (7) Plaintiff Litigation          (10) Trusts, Wills, Estate, Probate",
                    x + 5, y, REGULAR_FONT, SMALL_FONT_SIZE);
            addText(document, page, "(2) Collections / Repossession          (5) Financial Institution          (8) Real Estate          (11) Corporate/Commercial/Business",
                    x + 5, y - 9, REGULAR_FONT, SMALL_FONT_SIZE);
            addText(document, page, "(3) Copyright / Trademark          (6) Government          (9) Tax",
                    x + 5, y - 18, REGULAR_FONT, SMALL_FONT_SIZE);
        } catch (Exception e) {
            logger.error("Unexpected error", e);
        }
    }

    private static void drawClaimsAndFirmUpdatesSection(PDAcroForm acroForm, PDDocument document, Context ctx) {
        try {
            Map firmMap = getMapIfPresent(ctx, "firm_freeform_01");
            PDPage page = createPage(document);
            float x = PAGE_MARGIN;
            float y = 742;

            addText(document, page, "3.", x + 4, y, false);
            addText(document, page, "b.", x + 28, y, false);
            addText(document, page,
                    "If any aspect of your practice or professional services relates to cannabis or the cannabis industry in any way please",
                    x + 48, y, REGULAR_FONT, BODY_FONT_SIZE);
            addText(document, page, "complete the", x + 48, y - 11, REGULAR_FONT, BODY_FONT_SIZE);
            addText(document, page, "Cannabis Supplement.", x + 125, y - 11, BOLD_ITALIC_FONT, BODY_FONT_SIZE);
            y -= 22;
            y -= 8;
            drawSectionHeader(document, page, "CLAIMS", x, y);
            y -= 28;
            addText(document, page, "4.", x + 4, y, false);
            y = addWrappedText(document, page,
                    "After inquiry of all attorneys and staff of the firm, within the past year have any past or present personnel:",
                    x + 28, y, 520, REGULAR_FONT, BODY_FONT_SIZE, 11f);
            y -= 4;
            y = addQuestionWithYesNo(document, acroForm, page, "IsPersonnelBeSubOfAnyInvest",
                    "a. been the subject of any disciplinary complaint or regulatory investigation or inquiry; suspended or disbarred from practice; or charged, indicted or been convicted of any criminal charge?",
                    x + 20, y, 415, getYesNoValue(firmMap, "IsPersonnelBeSubOfAnyInvest"));
            float inlineX = x + 20;
            inlineX = addTextRun(document, page, "If \u201CYes\u201D", inlineX, y + 2, BOLD_ITALIC_FONT, SMALL_FONT_SIZE);
            addTextRun(document, page, ", please provide details and dates:", inlineX, y + 2, ITALIC_FONT, SMALL_FONT_SIZE);
            addUnderlinedField(document, acroForm, page, "PersonnelBeSubOfAnyInvestDate",
                    getBlankIfDefaultDate(firmMap, "PersonnelBeSubOfAnyInvestDate"), x + 215, y - 3, 345, 14);
            addUnderlinedField(document, acroForm, page, "PersonnelBeSubOfAnyInvestDetails",
                    getBlankIfNull(firmMap, "PersonnelBeSubOfAnyInvestDetails"), x + 20, y - 22, 540, 14);
            y -= 42;

            y = addQuestionWithYesNo(document, acroForm, page, "IsLawyerProfLiabClaimAgntAppl",
                    "b. know of any professional liability claims made against the firm, its affiliates or its personnel?",
                    x + 20, y, 415, getYesNoValue(firmMap, "IsLawyerProfLiabClaimAgntAppl"));
            y = addQuestionWithYesNo(document, acroForm, page, "IsAnyActOmmBecomeClaimAgntFirm",
                    "c. become aware of any act, error or omission or fee dispute which might become the basis of a claim against the firm or its personnel?",
                    x + 20, y, 415, getYesNoValue(firmMap, "IsAnyActOmmBecomeClaimAgntFirm"));
            y = addWrappedText(document, page,
                    "NOTE: THE POLICY FOR WHICH THIS APPLICATION IS BEING MADE SHALL NOT APPLY TO ANY INCIDENTS OR CLAIMS DETAILED OR WHICH SHOULD HAVE BEEN DETAILED IN QUESTION 4 a, b or c ABOVE.",
                    x, y - 3, 560, BOLD_FONT, SMALL_FONT_SIZE, 9f);
            y -= 8;
            inlineX = x;
            inlineX = addTextRun(document, page, "If \u201CYes\u201D", inlineX, y, BOLD_ITALIC_FONT, SMALL_FONT_SIZE);
            inlineX = addTextRun(document, page, " to 4 b or c above, complete the ", inlineX, y, ITALIC_FONT, SMALL_FONT_SIZE);
            inlineX = addTextRun(document, page, "CLAIM SUPPLEMENT", inlineX, y, BOLD_ITALIC_FONT, SMALL_FONT_SIZE);
            addTextRun(document, page, " for each claim or potential claim.", inlineX, y, ITALIC_FONT, SMALL_FONT_SIZE);

            y -= 24;
            drawSectionHeader(document, page, "UPDATES TO FIRM INFORMATION", x, y);
            y -= 28;
            addText(document, page, "5. What was your revenue last year?", x, y + 4, false);
            addRadioButtonsGrid(document, acroForm, page, "AnnualRevenueSequence", createRevenueButtons(),
                    getRenewalRevenueValue(firmMap),
                    x + 20, y - 22, 3, 185, 24);
            y -= 66;

            y = addQuestionWithYesNo(document, acroForm, page, "IsFirmMergedWithOtherFirm",
                    "6. In the past year have you acquired or merged with another firm?", x, y, 430,
                    getYesNoValue(firmMap, "IsFirmMergedWithOtherFirm"));
            y = addQuestionWithYesNo(document, acroForm, page, "IsApplIntToFinanAssests",
                    "a. If \u201CYes\u201D, were you the majority successor in interest to the financial assets and liabilities of the acquired or merged firm?",
                    x + 20, y, 410, getYesNoValue(firmMap, "IsApplIntToFinanAssests"));
            y = addQuestionWithYesNo(document, acroForm, page, "IsFirmCoverageForPreceedorFirms",
                    "b. If \u201CYes\u201D, do you desire coverage for this entity as a predecessor firm?", x + 20, y, 410,
                    getYesNoValue(firmMap, "IsFirmCoverageForPreceedorFirms"));
            inlineX = x;
            inlineX = addTextRun(document, page, "If \u201CYes\u201D", inlineX, y + 2, BOLD_ITALIC_FONT, SMALL_FONT_SIZE);
            inlineX = addTextRun(document, page, " to a or b above, complete the ", inlineX, y + 2, ITALIC_FONT, SMALL_FONT_SIZE);
            addTextRun(document, page, "Predecessor Firm Supplement.", inlineX, y + 2, BOLD_ITALIC_FONT, SMALL_FONT_SIZE);
            y -= 18;

            y = addQuestionWithYesNo(document, acroForm, page, "isServedAsCEOChairmanPresident",
                    "7. In the past year have you served as general counsel, CEO, chairman, president, officer, director or member of an internal committee for a publicly traded company or any financial institution?",
                    x, y, 430, getYesNoValue(firmMap, "isServedAsCEOChairmanPresident"));
            y = addQuestionWithYesNo(document, acroForm, page, "IsFirmHaveClientMoreThan25PercentOfBilling",
                    "8. At any time during the past year has any client of the firm represented more than 50% of the firm's annual revenue?",
                    x, y, 430, getYesNoValue(firmMap, "IsFirmHaveClientMoreThan25PercentOfBilling"));
            float revenueClientTableX = x + 20;
            float revenueClientTableTopY = y + 14;
            float revenueClientHeaderHeight = 18;
            float revenueClientRowHeight = 18;
            float[] revenueClientWidths = { 150, 205, 80, 80 };
            float cellX = revenueClientTableX;
            float headerY = revenueClientTableTopY - revenueClientHeaderHeight;
            String[] revenueClientHeaders = { "Client Name:", "Services Provided:", "Percentage", "Year:" };
            for (int i = 0; i < revenueClientWidths.length; i++) {
                drawBox(document, page, cellX, headerY, revenueClientWidths[i], revenueClientHeaderHeight, false);
                addText(document, page, revenueClientHeaders[i], cellX + 5, headerY + 5, BOLD_FONT, SMALL_FONT_SIZE);
                cellX += revenueClientWidths[i];
            }

            float rowOneY = headerY - revenueClientRowHeight;
            float rowTwoY = rowOneY - revenueClientRowHeight;
            for (int row = 0; row < 2; row++) {
                cellX = revenueClientTableX;
                float rowY = row == 0 ? rowOneY : rowTwoY;
                for (int i = 0; i < revenueClientWidths.length; i++) {
                    drawBox(document, page, cellX, rowY, revenueClientWidths[i], revenueClientRowHeight, false);
                    cellX += revenueClientWidths[i];
                }
            }

            addField(acroForm, page, "ClientNameFirstLargestRevenueClient",
                    getBlankIfNull(firmMap, "ClientNameFirstLargestRevenueClient"), revenueClientTableX + 5,
                    rowOneY + 3, revenueClientWidths[0] - 10, 12, false);
            addField(acroForm, page, "ServicesRenderedFirstLargestRevenueClient",
                    getBlankIfNull(firmMap, "ServicesRenderedFirstLargestRevenueClient"),
                    revenueClientTableX + revenueClientWidths[0] + 5, rowOneY + 3, revenueClientWidths[1] - 10, 12,
                    false);
            addField(acroForm, page, "PercentFromFirstLargestRevenueClient",
                    getBlankIfNull(firmMap, "PercentFromFirstLargestRevenueClient"),
                    revenueClientTableX + revenueClientWidths[0] + revenueClientWidths[1] + 5, rowOneY + 3,
                    revenueClientWidths[2] - 10, 12, false);
            addField(acroForm, page, "DateRenderedFirstLargestRevenueClient",
                    getBlankIfNull(firmMap, "DateRenderedFirstLargestRevenueClient"),
                    revenueClientTableX + revenueClientWidths[0] + revenueClientWidths[1] + revenueClientWidths[2] + 5,
                    rowOneY + 3, revenueClientWidths[3] - 10, 12, false);

            addField(acroForm, page, "ClientNameSecondLargestRevenueClient",
                    getBlankIfNull(firmMap, "ClientNameSecondLargestRevenueClient"), revenueClientTableX + 5,
                    rowTwoY + 3, revenueClientWidths[0] - 10, 12, false);
            addField(acroForm, page, "ServicesRenderedSecondLargestRevenueClient",
                    getBlankIfNull(firmMap, "ServicesRenderedSecondLargestRevenueClient"),
                    revenueClientTableX + revenueClientWidths[0] + 5, rowTwoY + 3, revenueClientWidths[1] - 10, 12,
                    false);
            addField(acroForm, page, "PercentFromSecondLargestRevenueClient",
                    getBlankIfNull(firmMap, "PercentFromSecondLargestRevenueClient"),
                    revenueClientTableX + revenueClientWidths[0] + revenueClientWidths[1] + 5, rowTwoY + 3,
                    revenueClientWidths[2] - 10, 12, false);
            addField(acroForm, page, "DateRenderedSecondLargestRevenueClient",
                    getBlankIfNull(firmMap, "DateRenderedSecondLargestRevenueClient"),
                    revenueClientTableX + revenueClientWidths[0] + revenueClientWidths[1] + revenueClientWidths[2] + 5,
                    rowTwoY + 3, revenueClientWidths[3] - 10, 12, false);

            page = createPage(document);
            y = 742;

            y = addQuestionWithYesNo(document, acroForm, page, "custodyOrControlFunds",
                    "9. Do you have care, custody or control over any funds or accounts of any of your clients, including but not limited to escrow or trust accounts?",
                    x, y, 430, getYesNoValue(firmMap, "custodyOrControlFunds"));
            addText(document, page, "If \u201CYes\u201D:", x + 20, y, BOLD_FONT, BODY_FONT_SIZE);
            y = addWrappedText(document, page,
                    "a. In the past year, approximately how many disbursement transactions have you executed from your client funds account?",
                    x + 70, y, 400, REGULAR_FONT, BODY_FONT_SIZE, 10f);
            addBoxedField(document, acroForm, page, "disbursementTransaction",
                    getBlankIfNull(firmMap, "disbursementTransaction"), x + 500, y + 10, 70, 14);
            y -= 4;
            y = addWrappedText(document, page,
                    "b. How many people at your firm have the authority to disburse funds out of your client accounts, or to change payment instructions, payment accounts or addresses?",
                    x + 70, y, 400, REGULAR_FONT, BODY_FONT_SIZE, 10f);
            addBoxedField(document, acroForm, page, "peopleToDisburseFunds",
                    getBlankIfNull(firmMap, "peopleToDisburseFunds"), x + 500, y + 10, 70, 14);
            y -= 4;
            y = addQuestionWithYesNo(document, acroForm, page, "instructionsDocumentedReceivingParty",
                    "c. Do you authenticate all changes to payment instructions, account numbers and addresses via outbound phone call to the documented receiving party?",
                    x + 20, y, 410, getYesNoValue(firmMap, "instructionsDocumentedReceivingParty"));
            y = addQuestionWithYesNo(document, acroForm, page, "doucumentedProtocolsAndAuthority",
                    "d. Do you have documented protocols and authority level sign-off for all changes to payment instructions, account numbers and addresses of clients, vendors and third parties?",
                    x + 20, y, 410, getYesNoValue(firmMap, "doucumentedProtocolsAndAuthority"));

            y -= 12;
            addText(document, page, "10.", 18, y, REGULAR_FONT, BODY_FONT_SIZE);
            addText(document, page,
                    "Do you have a fraud security awareness program that all emp",
                    42.8f, y, REGULAR_FONT, BODY_FONT_SIZE);
            addText(document, page, "loyees are briefed on?", 315.8f, y, REGULAR_FONT, BODY_FONT_SIZE);
            addReferenceYesNoButtons(document, acroForm, page, "securityAwareness",
                    getYesNoValue(firmMap, "securityAwareness"),
                    527, y, 546.6f, y - 1.4f, 581.2f, y - 2.1f);

            y -= 23;
            addText(document, page, "11.", 18, y, REGULAR_FONT, BODY_FONT_SIZE);
            addText(document, page,
                    "In the past year have you initiated lawsuits or arbitration procedures to enforce the collection of unpaid fees",
                    42.8f, y, REGULAR_FONT, BODY_FONT_SIZE);
            addText(document, page, "of any client?", 42.8f, y - 12, REGULAR_FONT, BODY_FONT_SIZE);
            addReferenceYesNoButtons(document, acroForm, page, "IsApplInitiatedLawsuitForFirm",
                    getYesNoValue(firmMap, "IsApplInitiatedLawsuitForFirm"),
                    527, y - 12, 548, y - 13.4f, 580.7f, y - 13.4f);

            y -= 35;
            addText(document, page, "If \"", 42.8f, y, REGULAR_FONT, BODY_FONT_SIZE);
            addText(document, page, "Yes", 54.4f, y, BOLD_ITALIC_FONT, BODY_FONT_SIZE);
            addText(document, page, "\", please complete the ", 72.3f, y, REGULAR_FONT, BODY_FONT_SIZE);
            float nextX = addTextRun(document, page, "Fee Suit Supplement", 173.4f, y, BOLD_ITALIC_FONT,
                    BODY_FONT_SIZE);
            addText(document, page, ".", nextX, y, REGULAR_FONT, BODY_FONT_SIZE);
        } catch (Exception e) {
            logger.error("Unexpected error", e);
        }
    }

    private static void drawFraudNoticeSection(PDDocument document) {
        PDPage page = createPage(document);
        float x = 18;
        float y = 753;

        addJustifiedLine(document, page,
                "ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR OTHER PERSON",
                x, y, 575, REGULAR_FONT, FRAUD_NOTICE_FONT_SIZE);
        addJustifiedLine(document, page,
                "FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF CLAIM CONTAINING ANY MATERIALLY FALSE",
                x, y - FRAUD_NOTICE_LINE_HEIGHT, 575, REGULAR_FONT, FRAUD_NOTICE_FONT_SIZE);
        addJustifiedLine(document, page,
                "INFORMATION OR CONCEALS FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT",
                x, y - FRAUD_NOTICE_LINE_HEIGHT * 2, 575, REGULAR_FONT, FRAUD_NOTICE_FONT_SIZE);
        addJustifiedLine(document, page,
                "MATERIAL THERETO COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME AND SUBJECTS SUCH PERSON",
                x, y - FRAUD_NOTICE_LINE_HEIGHT * 3, 575, REGULAR_FONT, FRAUD_NOTICE_FONT_SIZE);
        addJustifiedLine(document, page,
                "TO CRIMINAL AND CIVIL PENALTIES. (Not applicable in AL, AR, CO, DC, FL, KS, KY, LA, MD, ME, NJ,",
                x, y - FRAUD_NOTICE_LINE_HEIGHT * 4, 575, REGULAR_FONT, FRAUD_NOTICE_FONT_SIZE);
        addText(document, page,
                "NM, NY, OH, OK, OR, RI, TN, VA, VT, WA or WV - see Additional Fraud Notices for these States below).",
                x, y - FRAUD_NOTICE_LINE_HEIGHT * 5, REGULAR_FONT, FRAUD_NOTICE_FONT_SIZE);

        addText(document, page, "ADDITIONAL FRAUD NOTICES", 232.7f, 661, BOLD_FONT, FRAUD_NOTICE_FONT_SIZE);
        y = 638;
        y = addFraudNotice(document, page,
                "NOTICE TO ALABAMA, ARKANSAS, LOUISIANA, NEW MEXICO, RHODE ISLAND AND WEST VIRGINIA APPLICANTS:",
                "Any person who knowingly presents a false or fraudulent claim for payment of a loss or benefit or knowingly presents false information in an application for insurance is guilty of a crime and may be subject to fines and confinement in prison.",
                x, y);
        y = addFraudNotice(document, page, "NOTICE TO COLORADO APPLICANTS:",
                "It is unlawful to knowingly provide false, incomplete, or misleading facts or information to an insurance company for the purpose of defrauding or attempting to defraud the company. Penalties may include imprisonment, fines, denial of insurance, and civil damages. Any insurance company or agent of an insurance company who knowingly provides false, incomplete, or misleading facts or information to a policyholder or claimant for the purpose of defrauding or attempting to defraud the policyholder or claimant with regard to a settlement or award payable from insurance proceeds shall be reported to the Colorado Division of Insurance within the Department of Regulatory Agencies.",
                x, y);
        y = addFraudNotice(document, page, "NOTICE TO DISTRICT OF COLUMBIA APPLICANTS:",
                "WARNING: It is a crime to provide false or misleading information to an insurer for the purpose of defrauding the insurer or any other person. Penalties include imprisonment and/or fines. In addition, an insurer may deny insurance benefits if false information materially related to a claim was provided by the applicant.",
                x, y);
        y = addFraudNotice(document, page, "NOTICE TO FLORIDA APPLICANTS:",
                "Any person who knowingly and with intent to injure, defraud, or deceive any insurer files a statement of claim or an application containing any false, incomplete, or misleading information is guilty of a felony of the third degree.",
                x, y);
        y = addFraudNotice(document, page, "NOTICE TO KANSAS APPLICANTS:",
                "Any person who, knowingly and with intent to defraud, presents, causes to be presented or prepares with knowledge or belief that it will be presented to or by an insurer, purported insurer, broker or any agent thereof, any written, electronic, electronic impulse, facsimile, magnetic, oral, or telephonic communication or statement as part of, or in support of, an application for the issuance of, or the rating of an insurance policy for personal or commercial insurance, or a claim for payment or other benefit pursuant to an insurance policy for commercial or personal insurance which such person knows to contain materially false information concerning any fact material thereto; or conceals, for the purpose of misleading, information concerning any fact material thereto commits a fraudulent insurance act.",
                x, y);
        y = addFraudNotice(document, page, "NOTICE TO KENTUCKY APPLICANTS:",
                "Any person who knowingly and with intent to defraud any insurance company or other person files an application for insurance containing any materially false information or conceals, for the purpose of misleading, information concerning any fact material thereto commits a fraudulent insurance act, which is a crime.",
                x, y);
        y = addFraudNotice(document, page, "NOTICE TO MAINE APPLICANTS:",
                "It is a crime to knowingly provide false, incomplete or misleading information to an insurance company for the purpose of defrauding the company. Penalties may include imprisonment, fines or denial of insurance benefits.",
                x, y);
        y = addFraudNotice(document, page, "NOTICE TO MARYLAND APPLICANTS:",
                "Any person who knowingly or willfully presents a false or fraudulent claim for payment of a loss or benefit or who knowingly or willfully presents false information in an application for insurance is guilty of a crime and may be subject to fines and confinement in prison.",
                x, y);
        y = addFraudNotice(document, page, "NOTICE TO NEW JERSEY APPLICANTS:",
                "Any person who includes any false or misleading information on an application for an insurance policy is subject to criminal and civil penalties.",
                x, y);
        y = addFraudNotice(document, page, "NOTICE TO NEW YORK APPLICANTS:",
                "Any person who knowingly and with intent to defraud any insurance company or other person files an application for insurance or statement of claim containing any materially false information, or conceals for the purpose of misleading, information concerning any fact material thereto, commits a fraudulent insurance act, which is a crime, and shall also be subject to a civil penalty not to exceed five thousand dollars and the stated value of the claim for each such violation.",
                x, y);

        page = createPage(document);
        y = 753;
        y = addFraudNotice(document, page, "NOTICE TO OHIO APPLICANTS:",
                "Any person who, with intent to defraud or knowing that he is facilitating a fraud against an insurer, submits an application or files a claim containing a false or deceptive statement is guilty of insurance fraud.",
                x, y);
        y = addFraudNotice(document, page, "NOTICE TO OKLAHOMA APPLICANTS:",
                "WARNING: Any person who knowingly, and with intent to injure, defraud or deceive any insurer, makes any claim for the proceeds of an insurance policy containing any false, incomplete or misleading information is guilty of a felony.",
                x, y);
        y = addFraudNotice(document, page, "NOTICE TO OREGON APPLICANTS:",
                "Any person who knowingly and with intent to defraud or solicit another to defraud the insurer by submitting an application containing a false statement as to any material fact may be violating state law.",
                x, y);
        y = addFraudNotice(document, page, "NOTICE TO TENNESSEE, VIRGINIA AND WASHINGTON APPLICANTS:",
                "It is a crime to knowingly provide false, incomplete, or misleading information to an insurance company for the purpose of defrauding the company. Penalties include imprisonment, fines, and denial of insurance benefits.",
                x, y);
        addFraudNotice(document, page, "NOTICE TO VERMONT APPLICANTS:",
                "Any person who knowingly presents a false statement in an application for insurance may be guilty of a criminal offense and subject to penalties under state law.",
                x, y);
    }

    private static void drawSignatureSection(PDAcroForm acroForm, PDDocument document) {
        PDPage page = createPage(document);
        float x = PAGE_MARGIN;
        float y = 742;

        y = addSignatureIntroText(document, page, x, y);
        y -= 10;
        y = addWrappedText(document, page,
                "ALL WRITTEN STATEMENTS, SUPPLEMENTAL APPLICATION AND MATERIALS FURNISHED TO THE INSURER IN CONJUNCTION WITH THIS APPLICATION ARE INCORPORATED BY REFERENCE INTO THIS APPLICATION AND MADE A PART THEREOF, AND DEEMED ATTACHED HERETO.",
                x, y, 560, BOLD_FONT, BODY_FONT_SIZE, 11f);
        y -= 18;

        addText(document, page, "SIGNATURE*", x, y + 3, REGULAR_FONT, BODY_FONT_SIZE);
        addUnderlinedField(document, acroForm, page, "signature", x + 90, y, 190, 16);
        addText(document, page, "PRINTED NAME*", x + 310, y + 3, REGULAR_FONT, BODY_FONT_SIZE);
        addUnderlinedField(document, acroForm, page, "printedName", x + 405, y, 155, 16);
        y -= 28;
        float nextX = addTextRun(document, page,
                "*MUST BE SIGNED BY A DULY AUTHORIZED OFFICER OF THE FIRM ON BEHALF OF ALL ",
                x, y + 3, REGULAR_FONT, SMALL_FONT_SIZE);
        nextX = addTextRun(document, page, "INSUREDS", nextX, y + 3, BOLD_FONT, SMALL_FONT_SIZE);
        addTextRun(document, page, ".", nextX, y + 3, REGULAR_FONT, SMALL_FONT_SIZE);
        y -= 22;
        addText(document, page, "TITLE OF SIGNATORY:", x, y + 3, REGULAR_FONT, BODY_FONT_SIZE);
        addUnderlinedField(document, acroForm, page, "titleOfSignatory", x + 165, y, 145, 16);
        addText(document, page, "DATE SIGNED:", x + 340, y + 3, REGULAR_FONT, BODY_FONT_SIZE);
        addUnderlinedField(document, acroForm, page, "dateSigned", x + 430, y, 130, 16);
        addText(document, page, "MM / DD / YYYY", x + 470, y - 13, ITALIC_FONT, SMALL_FONT_SIZE);
        y -= 77;
        addText(document, page, "FLORIDA FIRMS ONLY:", x, y + 18, BOLD_FONT, SMALL_FONT_SIZE);
        addUnderlinedField(document, acroForm, page, "floridaProducerName", x + 155, y + 12, 255, 12);
        addText(document, page, "Producer's Name", x + 255, y + 3, REGULAR_FONT, SMALL_FONT_SIZE);
        addUnderlinedField(document, acroForm, page, "floridaProducerLicenseNumber", x + 425, y + 12, 135, 12);
        addText(document, page, "Producer's Florida License Number", x + 430, y + 3,
                REGULAR_FONT, SMALL_FONT_SIZE);
        y -= 47;
        addText(document, page, "IOWA FIRMS ONLY:", x, y + 18, BOLD_FONT, SMALL_FONT_SIZE);
        addText(document, page, "Producer's Name:", x + 140, y + 18, REGULAR_FONT, BODY_FONT_SIZE);
        addUnderlinedField(document, acroForm, page, "iowaProducerName", x + 235, y + 15, 325, 12);
    }

    private static float addSignatureIntroText(PDDocument document, PDPage page, float x, float y) {
        float nextX = addTextRun(document, page,
                "Completion and/or signing of this application does not bind the firm to purchase, nor the ",
                x, y, REGULAR_FONT, BODY_FONT_SIZE);
        nextX = addTextRun(document, page, "Insurer", nextX, y, BOLD_FONT, BODY_FONT_SIZE);
        addTextRun(document, page, " to provide, any insurance policy;", nextX, y, REGULAR_FONT, BODY_FONT_SIZE);
        addText(document, page,
                "however, no policy can be issued unless the application is properly completed, signed and dated.",
                x, y - 11, REGULAR_FONT, BODY_FONT_SIZE);

        y -= 32;
        nextX = addTextRun(document, page,
                "The signatory declares that (s)he is authorized by the firm to sign this application on behalf of all prospective ",
                x, y, REGULAR_FONT, BODY_FONT_SIZE);
        nextX = addTextRun(document, page, "Insureds", nextX, y, BOLD_FONT, BODY_FONT_SIZE);
        addTextRun(document, page, " and that to", nextX, y, REGULAR_FONT, BODY_FONT_SIZE);
        addText(document, page,
                "the best of his/her knowledge the statements herein are true. The signatory agrees that if the information supplied in this",
                x, y - 11, REGULAR_FONT, BODY_FONT_SIZE);
        addText(document, page,
                "application and the materials submitted therewith should change between the date this application is signed and the effective date",
                x, y - 22, REGULAR_FONT, BODY_FONT_SIZE);
        nextX = addTextRun(document, page,
                "of the proposed insurance, the signatory shall immediately notify the ",
                x, y - 33, REGULAR_FONT, BODY_FONT_SIZE);
        nextX = addTextRun(document, page, "Insurer", nextX, y - 33, BOLD_FONT, BODY_FONT_SIZE);
        nextX = addTextRun(document, page, " of such and shall provide the ", nextX, y - 33, REGULAR_FONT, BODY_FONT_SIZE);
        nextX = addTextRun(document, page, "Insurer", nextX, y - 33, BOLD_FONT, BODY_FONT_SIZE);
        addTextRun(document, page, " with", nextX, y - 33, REGULAR_FONT, BODY_FONT_SIZE);
        nextX = addTextRun(document, page,
                "information that would complete, update or correct the application or materials submitted therewith. The ",
                x, y - 44, REGULAR_FONT, BODY_FONT_SIZE);
        nextX = addTextRun(document, page, "Insurer", nextX, y - 44, BOLD_FONT, BODY_FONT_SIZE);
        addTextRun(document, page, " may withdraw or", nextX, y - 44, REGULAR_FONT, BODY_FONT_SIZE);
        addText(document, page, "modify any of the terms or conditions of coverage accordingly.",
                x, y - 55, REGULAR_FONT, BODY_FONT_SIZE);
        return y - 66;
    }

    private static void drawRenewalSupplementSections(PDAcroForm acroForm, PDDocument document, Context ctx) {
        drawCompactSupplementPage9(acroForm, document, ctx);
        drawCompactSupplementPage10(acroForm, document, ctx);
        drawCompactSupplementPage11(acroForm, document, ctx);
        drawCompactSupplementPage12(acroForm, document, ctx);
        drawCompactSupplementPage13(acroForm, document, ctx);
        drawCompactSupplementPage14(acroForm, document, ctx);
        drawClaimAndFeeSuitSupplementPage(acroForm, document, ctx);
    }

    private static void drawCompactSupplementPage9(PDAcroForm acroForm, PDDocument document, Context ctx) {
        Map bankruptcyMap = getMapIfPresent(ctx, "Bankruptcy_list_01");
        Map collectionsMap = getMapIfPresent(ctx, "Collections_list_01");
        Map collectionsAmountMap = getMapIfPresent(ctx, "collectionAmount_list_01");
        PDPage page = createSupplementPage(document, "PREDECESSOR FIRM SUPPLEMENT (as required in Question 6)");
        float x = PAGE_MARGIN;
        float y = 700;

        y = drawPredecessorFirmTable(document, acroForm, page, x, y, ctx);

        y -= 18;
        drawSectionHeader(document, page, "BANKRUPTCY SUPPLEMENT (as required in Question 3, Area of Practice Table)", x, y);
        y -= 24;
        y = addWrappedText(document, page,
                "NOTE: THIS SUPPLEMENT IS ONLY REQUIRED TO BE COMPLETED IF THE PERCENTAGE FOR BANKRUPTCY IN THE AREA OF PRACTICE TABLE IS GREATER THAN 35%.",
                x, y, 560, BOLD_FONT, SMALL_FONT_SIZE, 9f);
        y -= 12;
        addText(document, page, "1. Please indicate the percentage of bankruptcy cases which are:", x, y + 3, false);
        y -= 18;
        addText(document, page, "Personal Bankruptcies", x + 20, y + 3, false);
        addBoxedField(document, acroForm, page, "bankruptcyPersonalPercent",
                getBlankIfNull(bankruptcyMap, "personalBankrupties"), x + 142, y, 42, 14);
        addText(document, page, "%", x + 188, y + 3, false);
        addText(document, page, "Commercial Bankruptcies", x + 210, y + 3, false);
        addBoxedField(document, acroForm, page, "bankruptcyCommercialPercent",
                getBlankIfNull(bankruptcyMap, "commercialBankruptcies"), x + 333, y, 42, 14);
        addText(document, page, "%", x + 379, y + 3, false);
        addText(document, page, "Total (must equal 100%)", x + 428, y + 3, BOLD_FONT, SMALL_FONT_SIZE);
        addCalculatedTotalField(acroForm, page, "bankruptcyTotalPercent",
                sumFieldDefaults(new String[] { getBlankIfNull(bankruptcyMap, "personalBankrupties"),
                        getBlankIfNull(bankruptcyMap, "commercialBankruptcies") }),
                x + 532, y, 28, 14,
                new String[] { "bankruptcyPersonalPercent", "bankruptcyCommercialPercent" });
        addText(document, page, "%", x + 564, y + 3, false);
        y -= 16;
        y -= 18;
        addText(document, page, "2. How much of your bankruptcy practice involves the following:", x, y + 3, false);
        y -= 12;
        y = drawBankruptcyPracticeTable(document, acroForm, page, x, y, ctx, new String[] {
                "Bankruptcy Representation - Consumer",
                "Bankruptcy Representation - Commercial",
                "Bankruptcy Trustee - Consumer",
                "Bankruptcy Trustee - Commercial"
        });
        y = addQuestionWithYesNo(document, acroForm, page, "bankruptcyThirdPartyFinancing",
                "3. Do you use any third-party or outside company to finance bankruptcy litigation?", x, y, 430,
                getYesNoValue(bankruptcyMap, "companyFinance"));
        y = addQuestionWithYesNo(document, acroForm, page, "bankruptcyLargeDebtors",
                "4. In the past year have you ever represented debtors in a bankruptcy proceeding where total debt exceeded $10 million?",
                x, y, 430, getYesNoValue(bankruptcyMap, "representedDebtors"));
        y = addQuestionWithYesNo(document, acroForm, page, "bankruptcyDisclosureProcedure",
                "5. Do you have any affiliations, or referral arrangements with third party entities or other attorneys that offer any services in the area of debt settlement, debt resolution, debt consolidation, debt relief, credit counseling or attorneys fee financing?",
                x, y, 430, getYesNoValue(bankruptcyMap, "preBankruptcyServices"));
        y = addQuestionWithYesNo(document, acroForm, page, "bankruptcyDueDiligence",
                "6. Do you have a due diligence process for certifying the truthfulness and accuracy of the debtors bankruptcy schedule?",
                x, y, 430, getYesNoValue(bankruptcyMap, "dueDiligenceProcess"));
        y = addQuestionWithYesNo(document, acroForm, page, "bankruptcyAbilityToPayReview",
                "7. Do you use a review procedure for certification of the debtor's ability to pay?", x, y, 430,
                getYesNoValue(bankruptcyMap, "reviewProcedureForCertification"));
        y = addQuestionWithYesNo(document, acroForm, page, "bankruptcyUniformDisclosure",
                "8. Do you use a uniform disclosure statement explaining the duties of the debtor in bankruptcy which is disseminated to all clients?",
                x, y, 430, getYesNoValue(bankruptcyMap, "disclosureStatementExplaining"));
        y = addQuestionWithYesNo(document, acroForm, page, "bankruptcyAdvertisingDisclosure",
                "9. Do you include a conspicuous statement in all advertising stating that the firm is acting as a debt relief agency and containing all required disclosures?",
                x, y, 430, getYesNoValue(bankruptcyMap, "conspicuousStatement"));

        y -= 10;
        drawSectionHeader(document, page, "COLLECTIONS SUPPLEMENT (as required in Question 3, Area of Practice Table)", x, y);
        y -= 24;
        addText(document, page, "1. What is the approximate number of cases the firm has handled in the past year?", x, y + 3, false);
        addField(acroForm, page, "collectionsCaseCount", getBlankIfNull(collectionsMap, "averageCases"),
                x + 505, y, 60, 14);
        y -= 24;
        addText(document, page, "2. What percentage of your collections practice are:", x, y + 3, false);
        y -= 18;
        addText(document, page, "Consumer Collections?", x + 20, y + 3, false);
        addBoxedField(document, acroForm, page, "collectionsConsumerPercent",
                getBlankIfNull(collectionsMap, "consumerCollections"), x + 130, y, 42, 14);
        addText(document, page, "%", x + 176, y + 3, false);
        addText(document, page, "Commercial Collections?", x + 205, y + 3, false);
        addBoxedField(document, acroForm, page, "collectionsCommercialPercent",
                getBlankIfNull(collectionsMap, "commercialollections"), x + 328, y, 42, 14);
        addText(document, page, "%", x + 374, y + 3, false);
        addText(document, page, "Total (must equal 100%)", x + 428, y + 3, BOLD_FONT, SMALL_FONT_SIZE);
        addCalculatedTotalField(acroForm, page, "collectionsTotalPercent",
                sumFieldDefaults(new String[] { getBlankIfNull(collectionsMap, "consumerCollections"),
                        getBlankIfNull(collectionsMap, "commercialollections") }),
                x + 532, y, 28, 14,
                new String[] { "collectionsConsumerPercent", "collectionsCommercialPercent" });
        addText(document, page, "%", x + 564, y + 3, false);
        y -= 16;
        y -= 24;
        addText(document, page, "3. What is the average debt amount for individual collections accounts?", x, y + 3, false);
        y -= 18;
        addText(document, page, "$", x + 20, y + 3, false);
        addBoxedField(document, acroForm, page, "collectionsAverageDebtAmount",
                getBlankIfNull(collectionsAmountMap, "individualCollectionsAmount"), x + 35, y, 110, 14);
    }

    private static float drawPredecessorFirmTable(PDDocument document, PDAcroForm acroForm, PDPage page,
            float x, float y) {
        return drawPredecessorFirmTable(document, acroForm, page, x, y, null);
    }

    private static float drawPredecessorFirmTable(PDDocument document, PDAcroForm acroForm, PDPage page,
            float x, float y, Context ctx) {
        Map predecessorMap = getMapFromList(getListIfPresent(ctx, "prodecessor_list_1"), 0);
        String[] headers = { "Firm Name", "Date of Acquisition or Merger", "Type of Legal Entity",
                "# of Attys at Firm at Dissolution", "# of Attys for Whom Coverage is Sought",
                "Insurer at Dissolution", "Was ERP Purchased", "ERP Expiration Date" };
        String[] values = { getBlankIfNull(predecessorMap, "FirmName"),
                getBlankIfNull(predecessorMap, "DateOfAcquisation"), getBlankIfNull(predecessorMap, "TypeOfEntity"),
                getBlankIfNull(predecessorMap, "NumberOfAttorneyAtDiss"),
                getBlankIfNull(predecessorMap, "NumberOfAttorneyAtApplFirm"),
                getBlankIfNull(predecessorMap, "InsurerAtDissolution"),
                getBlankIfNull(predecessorMap, "IsERPPurchased"), getBlankIfNull(predecessorMap, "ERPExpDate") };
        float[] widths = { 95, 70, 55, 72, 105, 78, 50, 47 };
        float rowHeight = 70;
        float dataRowHeight = 22;
        float tableWidth = 0f;
        for (int i = 0; i < widths.length; i++) {
            tableWidth += widths[i];
        }
        float currentX = x;
        float cellY = y - rowHeight;
        for (int i = 0; i < headers.length; i++) {
            drawBox(document, page, currentX, cellY, widths[i], rowHeight, false);
            List<String> lines = wrapText(BOLD_FONT, headers[i], SMALL_FONT_SIZE, widths[i] - 6);
            float textY = y - 14;
            for (int j = 0; j < lines.size() && j < 4; j++) {
                addCenteredTextInCell(document, page, lines.get(j), currentX, textY, widths[i],
                        BOLD_FONT, SMALL_FONT_SIZE);
                textY -= 10;
            }
            addField(acroForm, page, "predecessorFirmColumn" + (i + 1), normalizeYesNo(values[i]), currentX + 2, cellY + 2,
                    widths[i] - 4, 18, false);
            currentX += widths[i];
        }
        drawLine(document, page, x, cellY + dataRowHeight, x + tableWidth, cellY + dataRowHeight, 0.5f);
        return cellY - 14;
    }

    private static float drawBankruptcyPracticeTable(PDDocument document, PDAcroForm acroForm, PDPage page,
            float x, float y, String[] rows) {
        return drawBankruptcyPracticeTable(document, acroForm, page, x, y, null, rows);
    }

    private static float drawBankruptcyPracticeTable(PDDocument document, PDAcroForm acroForm, PDPage page,
            float x, float y, Context ctx, String[] rows) {
        Map[] bankruptcyCaseRows = { getMapIfPresent(ctx, "bankruptcyCasesDetails_new_list_01"),
                getMapIfPresent(ctx, "bankruptcyCasesDetails_new_list_02"),
                getMapIfPresent(ctx, "bankruptcyCasesDetails_new_list_03"),
                getMapIfPresent(ctx, "bankruptcyCasesDetails_new_list_04") };
        float tableX = x + 20;
        float[] widths = { 300, 120, 140 };
        float headerHeight = 16;
        float rowHeight = 16;
        float cellY = y - headerHeight;

        drawBox(document, page, tableX, cellY, widths[0], headerHeight, false);
        drawBox(document, page, tableX + widths[0], cellY, widths[1], headerHeight, false);
        drawBox(document, page, tableX + widths[0] + widths[1], cellY, widths[2], headerHeight, false);
        addCenteredTextInCell(document, page, "Practice Type", tableX, cellY + 4, widths[0],
                BOLD_FONT, SMALL_FONT_SIZE);
        addCenteredTextInCell(document, page, "Percentage", tableX + widths[0], cellY + 4, widths[1],
                BOLD_FONT, SMALL_FONT_SIZE);
        addCenteredTextInCell(document, page, "Average Case Value", tableX + widths[0] + widths[1],
                cellY + 4, widths[2], BOLD_FONT, SMALL_FONT_SIZE);

        for (int i = 0; i < rows.length; i++) {
            cellY -= rowHeight;
            drawBox(document, page, tableX, cellY, widths[0], rowHeight, false);
            drawBox(document, page, tableX + widths[0], cellY, widths[1], rowHeight, false);
            drawBox(document, page, tableX + widths[0] + widths[1], cellY, widths[2], rowHeight, false);
            addTextInBox(document, page, rows[i], tableX + 4, cellY + rowHeight - 11,
                    widths[0] - 8, false, SMALL_FONT_SIZE, rowHeight);
            addField(acroForm, page, "bankruptcyPracticePercent" + (i + 1),
                    getBlankIfNull(bankruptcyCaseRows[i], "Percentage"), tableX + widths[0] + 4,
                    cellY + 2, widths[1] - 24, 12, false);
            addText(document, page, "%", tableX + widths[0] + widths[1] - 15, cellY + 4,
                    REGULAR_FONT, SMALL_FONT_SIZE);
            addText(document, page, "$", tableX + widths[0] + widths[1] + 5, cellY + 4,
                    REGULAR_FONT, SMALL_FONT_SIZE);
            addField(acroForm, page, "bankruptcyAverageCaseValue" + (i + 1),
                    getAmountWithoutCurrency(bankruptcyCaseRows[i], "AverageCaseValue"),
                    tableX + widths[0] + widths[1] + 18, cellY + 2, widths[2] - 22, 12, false);
        }
        return cellY - 14;
    }

    private static void drawCompactSupplementPage10(PDAcroForm acroForm, PDDocument document, Context ctx) {
        Map collectionsMap = getMapIfPresent(ctx, "Collections_list_01");
        Map copyrightMap = getMapIfPresent(ctx, "CopyRightTrademark_list_01");
        Map policyCCBMap = getMapIfPresent(ctx, "policy_CCB_01");
        PDPage page = createPage(document);
        float x = PAGE_MARGIN;
        float y = 742;

        y = addQuestionWithYesNo(document, acroForm, page, "collectionsNonLawyerPersonnel",
                "4. Do you use non-lawyer personnel to collect debts?", x, y, 430,
                getYesNoValue(collectionsMap, "personnelToCollectDebts"));
        y = addQuestionWithYesNo(document, acroForm, page, "collectionsDebtPurchasers",
                "5. Do you own or provide any services to purchasers of debt or debt consolidators?", x, y, 430,
                getYesNoValue(collectionsMap, "servicesToPurchasers"));
        y = addQuestionWithYesNo(document, acroForm, page, "collectionsFDCPAClaims",
                "6. In the past year have you been a party to any claims or suits under the FDCPA?", x, y, 430,
                getYesNoValue(collectionsMap, "claimsOrSuitsFDCPA"));

        y -= 12;
        drawSectionHeader(document, page, "COPYRIGHT / TRADEMARK SUPPLEMENT (as required in Question 3, Area of Practice Table)", x, y);
        y -= 28;

        y = addQuestionWithYesNo(document, acroForm, page, "copyrightTrademarkServicesOtherThanSearches",
                "1. Do you provide services other than searches and filings?", x, y, 430,
                getYesNoValue(copyrightMap, "otherServices"));
        addIfYesWrappedText(document, page, ", please describe:", x, y + 3, 240, BODY_FONT_SIZE, 11f);
//        addBoxedField(document, acroForm, page, "copyrightTrademarkDescription1",getBlankIfNull(copyrightMap, "otherServicesDesc"), x + 120, y, 440, 14);
        addBoxedField(document, acroForm, page, "copyrightTrademarkDescription2", getBlankIfNull(copyrightMap, "otherServicesDesc"), x, y - 20, 560, 14);
        y -= 46;

        drawSectionHeader(document, page, "CORPORATE / COMMERCIAL / BUSINESS SUPPLEMENT (as required in Question 6, Area of Practice Table)", x, y);
        y -= 24;
        y = addQuestionWithYesNo(document, acroForm, page, "corporateOtherServices",
                "1. Do you provide corporate services other than the following: general counsel/corporate governance, contract drafting and review, legal entity formations, employment contracts, partnership agreements, ERISA and employment benefit consulting?",
                x, y, 430, getYesNoValue(policyCCBMap, "OtherLegalCorporateServices"));
        y = addIfYesWrappedText(document, page,
                ", please provide a brief description of those corporate services not named above and, for each service, the percent of total income derived from such services in the past year.",
                x, y, 560, SMALL_FONT_SIZE, 9f);
        y -= 4;
        y = drawCorporateServiceTable(document, acroForm, page, x + 20, y, policyCCBMap);
        y -= 6;
        y = addQuestionWithYesNo(document, acroForm, page, "corporatePubliclyTradedClients",
                "2. Does the firm render services to publicly traded clients? (If \u201CYes\u201D, please explain below)",
                x, y, 430, getYesNoValue(policyCCBMap, "PubliclyRenderedServices"));
        y = addWrappedText(document, page,
                "3. With regard to corporate clients, does the firm or any member of the firm:",
                x, y, 560, REGULAR_FONT, BODY_FONT_SIZE, 10f);
        y -= 2;
        String[] questions = {
                "a. Have a business relationship other than the rendering of legal services?",
                "b. Have authority to disburse funds for any corporate clients?",
                "c. Accept compensation on a commission basis or based on dollar value of sale?",
                "d. Accept securities in payment for legal services in lieu of legal fees?",
                "e. Perform due diligence on behalf of a prospective buyer of a business?",
                "4. Has the firm or any member of the firm provided any kind of legal services or advice to a client who subsequently went insolvent, bankrupt, or into liquidation or receivership during the past two years?"
        };
        String[] questionFields = { "LegalBusinessRelationshipServices", "AuthorityToDisburseFunds",
                "AcceptCompensation", "LegalServicesSecuritiesPayment", "PerformDueDiligence",
                "LegalServicesBankruptLiquidation" };
        y = addYesNoQuestionList(document, acroForm, page, "corporateQuestion", questions, x, y, 430,
                policyCCBMap, questionFields);
        addIfYesWrappedText(document, page,
                " to any in a. through e. above, please provide a detailed explanation.",
                x, y + 3, 560, BODY_FONT_SIZE, 11f);
        y -= 20;
        addBoxedField(document, acroForm, page, "corporateExplanation1",
                getBlankIfNull(policyCCBMap, "DetailedDescCorporateServices"), x, y, 560, 16);
        addBoxedField(document, acroForm, page, "corporateExplanation2", "", x, y - 25, 560, 16);
    }

    private static float drawCorporateServiceTable(PDDocument document, PDAcroForm acroForm, PDPage page,
            float x, float y) {
        return drawCorporateServiceTable(document, acroForm, page, x, y, Collections.EMPTY_MAP);
    }

    private static float drawCorporateServiceTable(PDDocument document, PDAcroForm acroForm, PDPage page,
            float x, float y, Map policyCCBMap) {
        float descriptionWidth = 450;
        float percentWidth = 105;
        float headerHeight = 18;
        float rowHeight = 20;
        float cellY = y - headerHeight;

        drawBox(document, page, x, cellY, descriptionWidth, headerHeight, false);
        drawBox(document, page, x + descriptionWidth, cellY, percentWidth, headerHeight, false);
        addText(document, page, "Description", x + 5, cellY + 5, BOLD_FONT, BODY_FONT_SIZE);
        addCenteredTextInCell(document, page, "% of Total Income", x + descriptionWidth, cellY + 5,
                percentWidth, BOLD_FONT, SMALL_FONT_SIZE);
        for (int row = 1; row <= 3; row++) {
            cellY -= rowHeight;
            drawBox(document, page, x, cellY, descriptionWidth, rowHeight, false);
            drawBox(document, page, x + descriptionWidth, cellY, percentWidth, rowHeight, false);
            addField(acroForm, page, "corporateServiceDescription" + row,
                    getBlankIfNull(policyCCBMap, "CorporateServiceDesc" + row), x + 4, cellY + 2,
                    descriptionWidth - 8, rowHeight - 4, false);
            addField(acroForm, page, "corporateServiceIncomePercent" + row,
                    getBlankIfNull(policyCCBMap, "CorporateServicePercentage" + row),
                    x + descriptionWidth + 4, cellY + 2, percentWidth - 28, rowHeight - 4, false);
            addText(document, page, "%", x + descriptionWidth + percentWidth - 17, cellY + 5,
                    REGULAR_FONT, SMALL_FONT_SIZE);
        }
        return cellY - 14;
    }

    private static float drawFamilyLawPercentGrid(PDAcroForm acroForm, PDDocument document, PDPage page,
            float x, float y, Context ctx) {
        String[] leftLabels = { "a. Adoption", "b. Assisted Reproductive Technology", "c. Child Support",
                "d. Custody" };
        String[] leftKeys = { "FLAOP_PercentageValue_1", "FLAOP_PercentageValue_2",
                "FLAOP_PercentageValue_3", "FLAOP_PercentageValue_8" };
        String[] rightLabels = { "e. Divorce - assets under $5M", "f. Divorce - assets over $5M",
                "g. Surrogacy", "h. Other (please describe):" };
        String[] rightKeys = { "FLAOP_PercentageValue_4", "FLAOP_PercentageValue_5",
                "FLAOP_PercentageValue_6", "FLAOP_PercentageValue_7" };

        float tableX = x + 8;
        float tableWidth = 552;
        float halfWidth = tableWidth / 2f;
        float headerHeight = 18;
        float rowHeight = 18;
        float descriptionHeight = 38;
        float cellY = y - headerHeight;
        float leftFieldX = tableX + halfWidth - 82;
        float rightX = tableX + halfWidth;
        float rightFieldX = tableX + tableWidth - 106;
        float fieldWidth = 56;

        drawBox(document, page, tableX, cellY, halfWidth, headerHeight, false);
        drawBox(document, page, rightX, cellY, halfWidth, headerHeight, false);
        addCenteredTextInCell(document, page, "% of Revenue", leftFieldX - 8, cellY + 5, 90,
                BOLD_FONT, BODY_FONT_SIZE);
        addCenteredTextInCell(document, page, "% of Revenue", rightFieldX - 8, cellY + 5, 90,
                BOLD_FONT, BODY_FONT_SIZE);

        for (int i = 0; i < leftLabels.length; i++) {
            cellY -= rowHeight;
            drawBox(document, page, tableX, cellY, halfWidth, rowHeight, false);
            drawBox(document, page, rightX, cellY, halfWidth, rowHeight, false);

            addTextInBox(document, page, leftLabels[i], tableX + 6, cellY + rowHeight - 11,
                    leftFieldX - tableX - 12, false, BODY_FONT_SIZE, rowHeight);
            addField(acroForm, page, "familyLawPercent" + (i + 1), getBlankIfNull(ctx, leftKeys[i]),
                    leftFieldX, cellY + 1, fieldWidth, rowHeight - 2, false);
            addText(document, page, "%", leftFieldX + fieldWidth + 8, cellY + 4, REGULAR_FONT, BODY_FONT_SIZE);

            addTextInBox(document, page, rightLabels[i], rightX + 8, cellY + rowHeight - 11,
                    rightFieldX - rightX - 14, false, BODY_FONT_SIZE, rowHeight);
            addField(acroForm, page, "familyLawPercent" + (i + 5), getBlankIfNull(ctx, rightKeys[i]),
                    rightFieldX, cellY + 1, fieldWidth, rowHeight - 2, false);
            addText(document, page, "%", rightFieldX + fieldWidth + 8, cellY + 4, REGULAR_FONT, BODY_FONT_SIZE);
        }

        cellY -= descriptionHeight;
        drawBox(document, page, tableX, cellY, tableWidth, descriptionHeight, false);
        addText(document, page, "Description :", tableX + 8, cellY + descriptionHeight - 20,
                REGULAR_FONT, BODY_FONT_SIZE);
        addField(acroForm, page, "FLAOP_OtherDescription",
                getFamilyLawOtherDescription(ctx),
                tableX + 100, cellY + 7, tableWidth - 108, 20, false);

        cellY -= 22;
        String[] totalFields = { "familyLawPercent1", "familyLawPercent2", "familyLawPercent3", "familyLawPercent4",
                "familyLawPercent5", "familyLawPercent6", "familyLawPercent7", "familyLawPercent8" };
        String[] totalKeys = { "FLAOP_PercentageValue_1", "FLAOP_PercentageValue_2", "FLAOP_PercentageValue_3",
                "FLAOP_PercentageValue_8", "FLAOP_PercentageValue_4", "FLAOP_PercentageValue_5",
                "FLAOP_PercentageValue_6", "FLAOP_PercentageValue_7" };
        addText(document, page, "TOTAL (must equal 100%)", rightX + 8, cellY + 5, BOLD_FONT, BODY_FONT_SIZE);
        addCalculatedTotalField(acroForm, page, "familyLawTotalPercent", sumValues(ctx, totalKeys),
                rightFieldX, cellY + 1, fieldWidth, rowHeight - 2, totalFields);
        addText(document, page, "%", rightFieldX + fieldWidth + 8, cellY + 4, REGULAR_FONT, BODY_FONT_SIZE);
        return cellY - 18;
    }

    private static void drawCompactSupplementPage11(PDAcroForm acroForm, PDDocument document, Context ctx) {
        Map financialMap = getMapIfPresent(ctx, "FinancialInstitution_list_01");
        Map governmentMap = getMapIfPresent(ctx, "Government_list_01");
        Map[] governmentServiceMaps = { getMapIfPresent(ctx, "governmentServices_list_01"),
                getMapIfPresent(ctx, "governmentServices_list_02"), getMapIfPresent(ctx, "governmentServices_list_03") };
        PDPage page = createSupplementPage(document, "FAMILY LAW SUPPLEMENT (as required in Question 3, Area of Practice Table)");
        float x = PAGE_MARGIN;
        float y = 700;

        y = addWrappedText(document, page,
                "NOTE: THIS SUPPLEMENT IS ONLY REQUIRED TO BE COMPLETED IF THE PERCENTAGE FOR FAMILY LAW IN THE AREA OF PRACTICE TABLE IS GREATER THAN 35%.",
                x, y, 560, BOLD_FONT, SMALL_FONT_SIZE, 9f);
        y -= 8;
        y = addWrappedText(document, page,
                "1. Please complete the chart below for the past fiscal year for the Family Law services that you provide:",
                x, y, 560, REGULAR_FONT, BODY_FONT_SIZE, 10f);
        y -= 4;
        y = drawFamilyLawPercentGrid(acroForm, document, page, x, y, ctx);

        y -= 8;
        drawSectionHeader(document, page, "FINANCIAL INSTITUTION SUPPLEMENT (as required in Question 3, Area of Practice Table)", x, y);
        y -= 24;
        y = addWrappedText(document, page,
                "1. With regard to any financial institution client(s) within the past year, has any current or former attorney of the firm:",
                x, y, 560, REGULAR_FONT, BODY_FONT_SIZE, 10f);
        y -= 2;
        String[] financialQuestions = {
                "a. had any equity interest in or a loan commitment in or from said financial institution other than a mortgage on a personal residence?",
                "b. performed services related to regulatory compliance, opinion letters, preferred loan documentation, foreclosure or loan workout?",
                "2. Is any institution which you have represented in the past year currently or been previously under regulatory review by any state or government agency or had action taken against them?",
                "3. Has any financial institution for which you have done work in the past year become bankrupt or insolvent?"
        };
        y = addYesNoQuestionList(document, acroForm, page, "financialInstitutionQuestion", financialQuestions, x, y,
                430, financialMap, new String[] { "equityInterest", "servicesRelatedRegulatory",
                        "underRegulatoryReview", "haveBecomeBankruptInsolvent" });

        y -= 8;
        drawSectionHeader(document, page, "GOVERNMENT SUPPLEMENT (as required in Question 3, Area of Practice Table)", x, y);
        y -= 24;
        addText(document, page, "1. Please complete the information below for each city, town, county or municipality to whom you provide services:", x, y + 3, false);
        y -= 18;
        String[] headers = { "Name", "Services Provided" };
        float[] widths = { 210, 350 };
        drawTableHeader(document, page, headers, widths, x, y);
        y -= ROW_HEIGHT;
        for (int row = 1; row <= 3; row++) {
            drawFieldsAcrossRow(document, acroForm, page, "governmentEntityRow" + row, x, y, widths,
                    governmentServiceMaps[row - 1], new String[] { "ServiceName", "ServicesProvided" });
            y -= ROW_HEIGHT;
        }
        y = addQuestionWithYesNo(document, acroForm, page, "governmentBondWork",
                "2. Are you providing bond work as part of your services?", x, y - 8, 430,
                getYesNoValue(governmentMap, "providingBondWork"));
        addQuestionWithYesNo(document, acroForm, page, "governmentEminentDomain",
                "3. Are you providing services related to eminent domain?", x, y, 430,
                getYesNoValue(governmentMap, "eminentDomainServices"));
    }

    private static void drawCompactSupplementPage12(PDAcroForm acroForm, PDDocument document, Context ctx) {
        Map plaintiffCaseMap = getMapIfPresent(ctx, "aol_plaintiffMap");
        Map plaintiffMap = getMapIfPresent(ctx, "plaintiff_freeform_01");
        PDPage page = createSupplementPage(document, "PLAINTIFF LITIGATION SUPPLEMENT (as required in Question 3, Area of Practice Table)");
        float x = PAGE_MARGIN;
        float y = 700;

        y = addWrappedText(document, page,
                "1. Please complete the following plaintiff litigation chart providing the approximate largest case size for the past fiscal year for each area of the firm's plaintiff litigation practice:",
                x, y, 560, REGULAR_FONT, BODY_FONT_SIZE, 10f);
        y -= 2;
        String[] areas = { "Civil", "Construction", "Corporate / Commercial / Business", "Mass Tort Litigation / Class Action / Toxic Tort",
                "Medical Malpractice", "Personal Injury / Property Damage", "Products Liability",
                "Professional Liability (non-medical)", "Other" };
        y = drawPlaintiffLitigationTable(document, acroForm, page, x + 30, y, areas, plaintiffCaseMap);
        y -= 6;
        float plaintiffNumericFieldX = x + 430;
        float plaintiffNumericFieldWidth = 90;
        y = addLabeledField(document, acroForm, page, "2. Number of support staff devoted to plaintiff work:",
                "plaintiffSupportStaffCount", getBlankIfNull(plaintiffMap, "NumberOfSuppotedStaffToPlantiff"),
                x, y, plaintiffNumericFieldX, plaintiffNumericFieldWidth);
        y = addLabeledField(document, acroForm, page, "3. Total number of plaintiff cases during the past 12 months:",
                "plaintiffCaseCount", getBlankIfNull(plaintiffMap, "NumberOfInjuryCasesIn12Month"),
                x, y, plaintiffNumericFieldX, plaintiffNumericFieldWidth);
        y = addWrappedText(document, page,
                "4. Of the number of plaintiff cases handled by the firm, in the past year, what percentage of them were settled prior to trial:",
                x, y + 3, 560, REGULAR_FONT, BODY_FONT_SIZE, 10f);
        y -= 8;
        addText(document, page, "Percentage settled prior to trial:", x + 20, y + 3, false);
        addWholeNumberField(acroForm, page, "plaintiffPercentSettled",
                getBlankIfNull(plaintiffMap, "PerOfCasesSettledBeforeTrail"), plaintiffNumericFieldX,
                y, plaintiffNumericFieldWidth, 14);
        addText(document, page, "%", plaintiffNumericFieldX + plaintiffNumericFieldWidth + 8, y + 3, false);
        y -= 22;
        String[] questions = {
                "5. Do you accept referrals from other firms?",
                "If \u201CYes\u201D, is a written referral agreement used?",
                "6. Do you refer cases to other law firms?",
                "a. If \u201CYes\u201D, are written referral agreements used?",
                "b. If \u201CYes\u201D, do you confirm that firms to which referrals are made carry professional liability insurance?",
                "7. Are written authorizations for settlement always obtained from clients when settlements are reached?",
                "8. In the past year have you been lead counsel or co-lead counsel on any mass tort or class action matter?"
        };
        addYesNoQuestionList(document, acroForm, page, "plaintiffLitigationQuestion", questions, x, y, 430,
                plaintiffMap, new String[] { "IsAppAcceptRefersFromOtherFirms", "IsRefAggrementReferToAppl",
                        "IsAppReferToOtherLawFirms", "IsRefAggrementReferToApplRefersOut",
                        "IsAppConfirmCarryProfLiabIns", "IsSettlementAggrementsUsed", "massTortOrClassAction" });
    }

    private static float drawPlaintiffLitigationTable(PDDocument document, PDAcroForm acroForm, PDPage page,
            float x, float y, String[] areas) {
        return drawPlaintiffLitigationTable(document, acroForm, page, x, y, areas, Collections.EMPTY_MAP);
    }

    private static float drawPlaintiffLitigationTable(PDDocument document, PDAcroForm acroForm, PDPage page,
            float x, float y, String[] areas, Map plaintiffCaseMap) {
        String[] areasLCS = { "LargestCaseSize_14", "LargestCaseSize_15", "LargestCaseSize_4",
                "LargestCaseSize_11", "LargestCaseSize_5", "LargestCaseSize_6", "LargestCaseSize_8",
                "LargestCaseSize_9", "LargestCaseSize_13" };
        float areaWidth = 380;
        float valueWidth = 165;
        float headerHeight = 16;
        float rowHeight = 14;
        float cellY = y - headerHeight;

        drawBox(document, page, x, cellY, areaWidth, headerHeight, false);
        drawBox(document, page, x + areaWidth, cellY, valueWidth, headerHeight, false);
        addCenteredTextInCell(document, page, "AREA OF LITIGATION", x, cellY + 5, areaWidth,
                BOLD_FONT, BODY_FONT_SIZE);
        addCenteredTextInCell(document, page, "Largest Case Size", x + areaWidth, cellY + 5,
                valueWidth, BOLD_FONT, BODY_FONT_SIZE);
        for (int i = 0; i < areas.length; i++) {
            cellY -= rowHeight;
            drawBox(document, page, x, cellY, areaWidth, rowHeight, false);
            drawBox(document, page, x + areaWidth, cellY, valueWidth, rowHeight, false);
            addText(document, page, Character.toString((char) ('a' + i)) + ".", x + 5, cellY + 3,
                    REGULAR_FONT, SMALL_FONT_SIZE);
            addText(document, page, areas[i], x + 28, cellY + 3, REGULAR_FONT, SMALL_FONT_SIZE);
            addText(document, page, "$", x + areaWidth + 8, cellY + 3, REGULAR_FONT, SMALL_FONT_SIZE);
            addField(acroForm, page, "plaintiffLargestCaseSize" + (i + 1),
                    getBlankIfNull(plaintiffCaseMap, areasLCS[i]),
                    x + areaWidth + 20, cellY + 1, valueWidth - 24, rowHeight - 2, false);
        }
        return cellY - 14;
    }

    private static void drawCompactSupplementPage13(PDAcroForm acroForm, PDDocument document, Context ctx) {
        Map realEstateMap = getMapIfPresent(ctx, "RealEastate_list_01");
        Map realEstateAmountMap = getMapIfPresent(ctx, "amountForRealEstate_list_01");
        PDPage page = createSupplementPage(document, "REAL ESTATE SUPPLEMENT (as required in Question 3, Area of Practice Table)");
        float x = PAGE_MARGIN;
        float y = 700;

        y = addWrappedText(document, page,
                "1. Please complete the following chart, providing a breakdown of the firm's Residential real estate practice based on revenue for the past fiscal year:",
                x, y, 560, REGULAR_FONT, SMALL_FONT_SIZE, 9f);
        y -= 2;
        String[] realEstateRows = new String[] { "a. Purchase and Sale", "b. Title Opinions",
                "c. Foreclosures / Loan Workouts", "d. Property Valuation / Real Estate Tax Abatement",
                "e. Eminent Domain", "f. Landlord Tenant", "g. Condominiums, Cooperatives or Homeowner Associations",
                "h. Leases", "i. Land Use / Development",
                "j. Speculative Real Estate, Limited Partnerships, Real Estate Syndications",
                "k. Real Estate Trusts", "l. Other (please describe):" };
        y = drawCompactPercentRows(acroForm, document, page, "residentialRealEstate", x, y,
                realEstateRows,
                new String[] { "AOPRE_PercentageValue_1", "AOPRE_PercentageValueRes_22",
                        "AOPRE_PercentageValue_19", "AOPRE_PercentageValue_7", "AOPRE_PercentageValue_12",
                        "AOPRE_PercentageValue_6", "AOPRE_PercentageValue_4", "AOPRE_PercentageValueRes_23",
                        "AOPRE_PercentageValue_11", "AOPRE_PercentageValue_13", "AOPRE_PercentageValueRes_16",
                        "AOPRE_PercentageValue_20" },
                ctx, null, "TOTAL (if applicable, must equal 100%)", "AOPRE_CommentDesc_20",
                getBlankIfNull(ctx, "AOPRE_CommentDesc_20"));
        y -= 8;
        y = addWrappedText(document, page,
                "Please complete the following chart, providing a breakdown of the firm's Commercial real estate practice based on revenue for the past fiscal year:",
                x, y, 560, REGULAR_FONT, SMALL_FONT_SIZE, 9f);
        y -= 2;
        y = drawCompactPercentRows(acroForm, document, page, "commercialRealEstate", x, y,
                realEstateRows,
                new String[] { "AOPRE_PercentageValueCom_1", "AOPRE_PercentageValueCom_22",
                        "AOPRE_PercentageValueCom_19", "AOPRE_PercentageValueCom_7",
                        "AOPRE_PercentageValueCom_12", "AOPRE_PercentageValueCom_6",
                        "AOPRE_PercentageValueCom_4", "AOPRE_PercentageValueCom_23",
                        "AOPRE_PercentageValueCom_11", "AOPRE_PercentageValueCom_13",
                        "AOPRE_PercentageValueRes_16", "AOPRE_PercentageValueCom_20" },
                ctx, null, "TOTAL (if applicable, must equal 100%)", "AOPRE_CommentDescCom_20",
                getBlankIfNull(ctx, "AOPRE_CommentDescCom_20"));

        page = createSupplementPage(document, "REAL ESTATE SUPPLEMENT (continued)");
        y = 700;
        float residentialFieldX = x + 390;
        float commercialFieldX = x + 500;
        float realEstateFieldWidth = 80;
        addText(document, page, "Residential", residentialFieldX, y + 3, true);
        addText(document, page, "Commercial", commercialFieldX, y + 3, true);
        y -= 18;
        addWrappedText(document, page,
                "2. What is the approximate number of transactions handled in the past 12 months?",
                x, y + 3, 360, REGULAR_FONT, BODY_FONT_SIZE, 10f);
        addBoxedField(document, acroForm, page, "realEstateResidentialTransactions",
                getBlankIfNull(realEstateMap, "transactionsHandled12MonthsResi"), residentialFieldX, y,
                realEstateFieldWidth, 14);
        addBoxedField(document, acroForm, page, "realEstateCommercialTransactions",
                getBlankIfNull(realEstateMap, "transactionsHandled12MonthsComm"), commercialFieldX, y,
                realEstateFieldWidth, 14);
        y -= 20;
        addWrappedText(document, page, "3. What was the largest real estate transaction in the past 12 months?",
                x, y + 3, 360, REGULAR_FONT, BODY_FONT_SIZE, 10f);
        addText(document, page, "$", residentialFieldX - 12, y + 3, false);
        addBoxedField(document, acroForm, page, "realEstateResidentialLargest",
                getBlankIfNull(realEstateAmountMap, "largestTransaction12MonthsResi"), residentialFieldX, y,
                realEstateFieldWidth, 14);
        addText(document, page, "$", commercialFieldX - 12, y + 3, false);
        addBoxedField(document, acroForm, page, "realEstateCommercialLargest",
                getBlankIfNull(realEstateAmountMap, "largestTransaction12MonthsComm"), commercialFieldX, y,
                realEstateFieldWidth, 14);
        y -= 22;
        y = addQuestionWithYesNo(document, acroForm, page, "realEstateNonAttorneyClosings",
                "4. Do non-attorney staff members ever attend closings on behalf of the firm in lieu of attorneys?",
                x, y, 430, getYesNoValue(realEstateMap, "attendClosings"));
        y = addQuestionWithYesNo(document, acroForm, page, "realEstateSecuringFinancing",
                "5. Does the firm's practice include securing financing for its clients?", x, y, 430,
                getYesNoValue(realEstateMap, "includeSecuringFinancing"));
        y = addWrappedText(document, page,
                "6. If you act in a dual capacity in the same real estate transaction, do you always use a disclosure form signed by both parties?",
                x, y + 3, 430, REGULAR_FONT, BODY_FONT_SIZE, 10f);
        addYesNoNAButtons(document, acroForm, page, "realEstateDualCapacityDisclosure",
                normalizeYesNoNA(getBlankIfNull(realEstateMap, "useDisclosureForm")), x + 450, y + 12);

        page = createSupplementPage(document, "TAX SUPPLEMENT (as required in Question 3, Area of Practice Table)");
        y = 700;
        y = addWrappedText(document, page,
                "NOTE: THIS SUPPLEMENT IS ONLY REQUIRED TO BE COMPLETED IF THE PERCENTAGE FOR TAX IN THE AREA OF PRACTICE TABLE IS GREATER THAN 25%.",
                x, y, 560, BOLD_FONT, SMALL_FONT_SIZE, 9f);
        y -= 6;
        y = addWrappedText(document, page,
                "1. Please complete the following chart, providing a breakdown of the firm's tax practice based on revenue for the past fiscal year:",
                x, y, 560, REGULAR_FONT, SMALL_FONT_SIZE, 9f);
        y -= 2;
        drawCompactPercentRows(acroForm, document, page, "taxPractice", x, y,
                new String[] { "a. Personal", "b. Corporate", "c. Estate Tax Returns",
                        "d. Investment Counselor Services", "e. Subchapter S Elections",
                        "f. Liquidation of Corporations", "g. Opinions on Tax Shelters",
                        "h. Opinions Involving Private Placement Memoranda", "i. Other (please describe):" },
                new String[] { "Tax_PercentageValue_1", "Tax_PercentageValue_2", "Tax_PercentageValue_3",
                        "Tax_PercentageValue_4", "Tax_PercentageValue_5", "Tax_PercentageValue_6",
                        "Tax_PercentageValue_7", "Tax_PercentageValue_8", "Tax_PercentageValue_9" },
                ctx, null, "TOTAL (must equal 100%)", "Tax_CommentDesc_9",
                getBlankIfNull(ctx, "Tax_CommentDesc_9"));
    }

    private static void drawCompactSupplementPage14(PDAcroForm acroForm, PDDocument document, Context ctx) {
        Map willsEstateMap = getMapIfPresent(ctx, "willsEstate_freeform_01");
        Map trustMap1 = getMapIfPresent(ctx, "Trust_list_01");
        Map trustMap2 = getMapIfPresent(ctx, "Trust_list_02");
        Map trustMap3 = getMapIfPresent(ctx, "Trust_list_03");
        Map cannabisMap = getMapIfPresent(ctx, "policy_Cannabis_01");
        PDPage page = createSupplementPage(document, "TRUSTS, WILLS, ESTATE, PROBATE SUPPLEMENT (as required in Question 3, Area of Practice Table)");
        float x = PAGE_MARGIN;
        float y = 700;

        y = addWrappedText(document, page,
                "1. Please complete the following chart, providing a breakdown of the firm's trusts, wills, estate and probate practice based on revenue for the past fiscal year:",
                x, y, 560, REGULAR_FONT, SMALL_FONT_SIZE, 9f);
        y -= 2;
        y = drawCompactPercentRows(acroForm, document, page, "trustsWillsEstate", x, y,
                new String[] { "a. Preparation of Wills", "b. Estate Planning", "c. Probate",
                        "d. Trust Administration", "e. Guardianship", "f. Medical Planning", "g. Trust Creation",
                        "h. Corporation Formation", "i. Taxation", "j. Tax Opinions", "k. Asset Protection",
                        "l. Litigation", "m. Other (please describe):" },
                new String[] { "WESKey_PercentageValue_1", "WESKey_PercentageValue_2", "WESKey_PercentageValue_3",
                        "WESKey_PercentageValue_4", "WESKey_PercentageValue_9", "WESKey_PercentageValue_10",
                        "WESKey_PercentageValue_13", "WESKey_PercentageValue_5", "WESKey_PercentageValue_7",
                        "WESKey_PercentageValue_6", "WESKey_PercentageValue_8", "WESKey_PercentageValue_11",
                        "WESKey_PercentageValue_12" },
                ctx, null, "TOTAL (must equal 100%)", "WESKey_CommentDesc_12",
                getBlankIfNull(ctx, "WESKey_CommentDesc_12"));
        y -= 12;
        y = addQuestionWithYesNo(document, acroForm, page, "trustsServeAsRepresentativeOrTrustee",
                "2. Does any attorney currently serve as Personal Representative/Administrator or Trustee?", x, y,
                430, getYesNoValue(willsEstateMap, "IsAttorneyServeAsExecutorTrustee"));
        y = addQuestionWithYesNoNA(document, acroForm, page, "trusteeAuthorityPurchaseSale",
                "a. If you are acting as a Trustee, do your activities as a trustee include authority to make decisions resulting in the purchase or sale of securities, real estate or other investments?",
                x + 20, y, 390, normalizeYesNoNA(getBlankIfNull(willsEstateMap, "ActingAsTrustee")));
        y = addQuestionWithYesNoNA(document, acroForm, page, "trusteeInvestmentProfessionalAdvice",
                "b. If you make investment decisions, are all investment decisions made in accord with advice from a certified investment professional?",
                x + 20, y, 390, normalizeYesNoNA(getBlankIfNull(willsEstateMap, "CertifiedInvestmentDecisions")));
        y = addWrappedText(document, page,
                "3. During the course of your professional practice, how many will, trust or estate matters are valued at the following amounts?",
                x, y, 560, REGULAR_FONT, BODY_FONT_SIZE, 10f);
        y -= 16;
        y = addLabeledField(document, acroForm, page, "$1,000,000 - $4,999,999",
                "trustsMatters1mTo4999999", getBlankIfNull(trustMap1, "NumberOfMattersValued"),
                x + 20, y, x + 230, 70);
        y = addLabeledField(document, acroForm, page, "$5,000,000 - $9,999,999",
                "trustsMatters5mTo9999999", getBlankIfNull(trustMap2, "NumberOfMattersValued"),
                x + 20, y, x + 230, 70);
        y = addLabeledField(document, acroForm, page, "$10,000,000 and over",
                "trustsMatters10mOver", getBlankIfNull(trustMap3, "NumberOfMattersValued"),
                x + 20, y, x + 230, 70);
        y = addWrappedText(document, page,
                "4. For how many will, estate, trust or probate matters did you provide professional services in the past year?",
                x, y + 3, 470, REGULAR_FONT, BODY_FONT_SIZE, 11f);
        addBoxedField(document, acroForm, page, "trustsMattersPastYear",
                getBlankIfNull(willsEstateMap, "willEstatesHandledPastYear"), x + 500, y + 11, 80, 14);
        y -= 10;
        y = addQuestionWithYesNo(document, acroForm, page, "trustsBeneficiaryInterest",
                "5. Do you have a beneficiary interest in any of the wills, trusts, probate and/or estates for which you provide services?",
                x, y, 430, getYesNoValue(willsEstateMap, "beneficiaryInterestWillsTrust"));

        page = createSupplementPage(document, "CANNABIS SUPPLEMENT (as required in Question 3b)");
        y = 700;
        y = addQuestionWithYesNo(document, acroForm, page, "cannabisHandlingCases",
                "1. Is your firm currently handling, previously handled or intending to handle any cases/files/transactions directly related to the distribution, warehousing, growing, manufacturing or selling of cannabis?",
                x, y, 430, getYesNoValue(cannabisMap, "distributionWareinghouseCases"));
        addText(document, page, "2. Please confirm any involvement in the following:", x, y + 3, false);
        y -= 18;
        String[] questions = {
                "a. Licensing",
                "b. Services related to private offerings, investments, business valuations, or purchase or sale of cannabis enterprises",
                "c. Federal, state or local compliance",
                "d. Intellectual property including trademark matters",
                "e. Negotiating real estate purchase contracts for cannabis enterprises",
                "f. Representing clients in litigation matters involving complex issues related to the cannabis industry",
                "g. Other"
        };
        addYesNoQuestionList(document, acroForm, page, "cannabisQuestion", questions, x + 20, y, 410,
                cannabisMap, new String[] { "cannibsLicensing", "canniabisInvestmentServices",
                        "StateOrLocalCompliance", "IntellectualProperty", "NegotiatePurchaseContracts",
                        "RepresentClientLitigation", "CannibsOther" });
    }

    private static void drawClaimAndFeeSuitSupplementPage(PDAcroForm acroForm, PDDocument document, Context ctx) {
        Map claimMap = getFirstMapFromContext(ctx, "claims_list_1");
        List lawsuits = getListIfPresent(ctx, "firmlawsuit_practice_list_01");
        PDPage page = createPage(document);
        float x = PAGE_MARGIN;
        float y = 742;

        drawSectionHeader(document, page, "CLAIM SUPPLEMENT (as required in Question 4)", x, y);
        y -= 26;
        addText(document, page, "1.", x + 4, y + 3, false);
        addText(document, page, "Full name of claimant or potential claimant:", x + 28, y + 3, false);
        addUnderlinedField(document, acroForm, page, "claimSupplementClaimantName",
                getBlankIfNull(claimMap, "NameOfClaimant"), x + 235, y, 205, 14);
        addRadioButtons(document, acroForm, page, "claimSupplementClaimantType",
                createButtonList(new Button("Client", "Client"), new Button("Non-Client", "NonClient")),
                getClientTypeValue(claimMap), x + 455, y + 2);
        y -= 24;

        y = addQuestionWithYesNoNA(document, acroForm, page, "claimSupplementClaimInSuit",
                "2.   Is the claim in suit?", x + 4, y, 420,
                normalizeYesNoNA(getBlankIfNull(claimMap, "IsClaimInSuit")));
        addText(document, page, "3.", x + 4, y + 3, false);
        addText(document, page, "Date you were notified of claim or became aware of error/incident:", x + 28, y + 3, false);
        addUnderlinedField(document, acroForm, page, "claimSupplementNoticeDate",
                getBlankIfNull(claimMap, "ClaimNotifiedDate"), x + 375, y, 95, 14);
        y -= 17;
        addText(document, page, "Date of alleged error:", x + 28, y + 3, false);
        addUnderlinedField(document, acroForm, page, "claimSupplementErrorDate",
                getBlankIfNull(claimMap, "DateOfAllegedError"), x + 145, y, 95, 14);
        y -= 13;
        addText(document, page, "MM / DD / YY", x + 390, y + 20, ITALIC_FONT, SMALL_FONT_SIZE);
        addText(document, page, "MM / DD / YY", x + 160, y + 3, ITALIC_FONT, SMALL_FONT_SIZE);
        y -= 20;

        addText(document, page, "4.", x + 4, y + 3, false);
        addText(document, page, "Names of firm personnel involved in the claim or potential claim:", x + 28, y + 3, false);
        addUnderlinedField(document, acroForm, page, "claimSupplementPersonnel",
                getBlankIfNull(claimMap, "NameOfPersonnelINClaim"), x + 375, y, 200, 14);
        y -= 24;
        addText(document, page, "5.", x + 4, y + 3, false);
        addText(document, page, "Description of claim or potential claim:", x + 28, y + 3, false);
        addUnderlinedField(document, acroForm, page, "claimSupplementDescription1",
                getBlankIfNull(claimMap, "DescOfClaim"), x + 220, y, 360, 14);
        y -= 20;
        addUnderlinedField(document, acroForm, page, "claimSupplementDescription2", "", x + 28, y, 552, 14);
        y -= 28;

        addText(document, page,
                "6. Has this claim or potential claim been reported to the firm's professional liability insurer?",
                x + 4, y + 3, false);
        addYesNoButtons(document, acroForm, page, "claimSupplementReportedToInsurer",
                getYesNoValue(claimMap, "IsClaimReportedToInsComp"), x + 505, y + 1);
        y -= 24;
        addText(document, page, "7.", x + 4, y + 3, false);
        addText(document, page, "Name of insurance company:", x + 28, y + 3, false);
        addUnderlinedField(document, acroForm, page, "claimSupplementInsuranceCompany",
                getBlankIfNull(claimMap, "NameOfInsComp"), x + 165, y, 150, 14);
        addText(document, page, "Date reported to insurance company:", x + 330, y + 3, false);
        addUnderlinedField(document, acroForm, page, "claimSupplementDateReported",
                getBlankIfNull(claimMap, "DateReportedToInsComp"), x + 515, y, 65, 14);
        y -= 13;
        addText(document, page, "MM / DD / YY", x + 515, y + 3, ITALIC_FONT, SMALL_FONT_SIZE);
        y -= 22;

        addText(document, page, "8.", x + 4, y + 3, false);
        addText(document, page, "If claim is now open, provide: Insurer's loss reserve", x + 28, y + 3, false);
        addUnderlinedField(document, acroForm, page, "claimSupplementLossReserve",
                getBlankIfNull(claimMap, "InsurerLoss"), x + 300, y, 90, 14);
        addText(document, page, "Claimant's last demand", x + 405, y + 3, false);
        addUnderlinedField(document, acroForm, page, "claimSupplementLastDemand",
                getBlankIfNull(claimMap, "ClaimantLastDemand"), x + 515, y, 60, 14);
        y -= 22;
        addText(document, page, "Current Status:", x + 28, y + 3, false);
        addUnderlinedField(document, acroForm, page, "claimSupplementCurrentStatus",
                getBlankIfNull(claimMap, "CurrentStatus"), x + 120, y, 460, 14);
        y -= 24;

        addText(document, page, "9.", x + 4, y + 3, false);
        addText(document, page, "If closed, provide: date closed", x + 28, y + 3, false);
        addUnderlinedField(document, acroForm, page, "claimSupplementDateClosed",
                getBlankIfNull(claimMap, "ClaimClosingDate"), x + 230, y, 92, 14);
        addText(document, page, "MM / DD / YY", x + 242, y - 8, ITALIC_FONT, SMALL_FONT_SIZE);
        y -= 26;
        addText(document, page, "Total amount paid (including damages and defense expenses): $", x + 28, y + 5, false);
        addUnderlinedField(document, acroForm, page, "claimSupplementTotalPaid",
                getAmountWithoutCurrency(claimMap, "TotalAmountPaid"), x + 370, y, 120, 14);
        y -= 22;

        addText(document, page, "10.", x + 4, y + 3, false);
        addText(document, page, "What steps have you taken to prevent similar allegations from being made in future claims/incidents?", x + 28, y + 3, false);
        y -= 24;
        addUnderlinedField(document, acroForm, page, "claimSupplementPrevention1",
                getBlankIfNull(claimMap, "StepsTakenToPreventClaims"), x + 28, y, 552, 14);
        y -= 20;
        addUnderlinedField(document, acroForm, page, "claimSupplementPrevention2", x + 28, y, 552, 14);
        y -= 42;

        drawSectionHeader(document, page, "FEE SUIT SUPPLEMENT (as required in Question 11)", x, y);
        y -= 26;
        y = addWrappedText(document, page,
                "1. Please complete the following for each suit the firm has filed within the past year against a client for collection of fees due:",
                x + 4, y, 560, REGULAR_FONT, BODY_FONT_SIZE, 10f);
        y -= 8;
        String[] headers = { "Amount of Fees Sued For", "Date Fee Suit Filed",
                "Was There a Counter Claim or Allegation of Legal Malpractice?", "Disposition of Fee Suit *" };
        float[] widths = { 150, 130, 150, 140 };
        float headerHeight = 36;
        float rowHeight = 15;
        float headerY = y - headerHeight;
        drawWrappedTableHeader(document, page, headers, widths, x + 8, headerY, headerHeight);
        y = headerY - rowHeight;
        for (int row = 1; row <= 3; row++) {
            Map lawsuitMap = getMapFromList(lawsuits, row - 1);
            float rowX = x + 8;
            for (int i = 0; i < widths.length; i++) {
                drawBox(document, page, rowX, y, widths[i], rowHeight, false);
                rowX += widths[i];
            }
            rowX = x + 8;
            addText(document, page, "$", rowX + 4, y + 3, false);
            addField(acroForm, page, "feeSuitAmount" + row, getBlankIfNull(lawsuitMap, "AmountOfFeesSued"),
                    rowX + 16, y + 1, widths[0] - 18, 12, false);
            rowX += widths[0];
            addField(acroForm, page, "feeSuitDateFiled" + row, getBlankIfNull(lawsuitMap, "SuitFilesDateFees"),
                    rowX + 4, y + 1, widths[1] - 8, 12, false);
            rowX += widths[1];
            addYesNoButtons(document, acroForm, page, "feeSuitCounterClaim" + row,
                    getYesNoValue(lawsuitMap, "IsAllegOfLegalMalPrac"), rowX + 34, y + 2);
            rowX += widths[2];
            addField(acroForm, page, "feeSuitDisposition" + row, getBlankIfNull(lawsuitMap, "FeeSuitDesc"),
                    rowX + 4, y + 1, widths[3] - 8, 12, false);
            y -= rowHeight;
        }
        y -= 15;
        addText(document, page,
                "*P = Fees paid in full, NS = Negotiated Settlement, JP = Judgment Plaintiff, JD = Judgment Defense, O = Open",
                x + 8, y, REGULAR_FONT, SMALL_FONT_SIZE);
    }

    private static float drawCompactPercentRows(PDAcroForm acroForm, PDDocument document, PDPage page, String prefix,
            float x, float y, String[] rows) {
        return drawCompactPercentRows(acroForm, document, page, prefix, x, y, rows, "TOTAL (must equal 100%)");
    }

    private static float drawCompactPercentRows(PDAcroForm acroForm, PDDocument document, PDPage page, String prefix,
            float x, float y, String[] rows, String totalLabel) {
        return drawCompactPercentRows(acroForm, document, page, prefix, x, y, rows, null, null, null, totalLabel);
    }

    private static float drawCompactPercentRows(PDAcroForm acroForm, PDDocument document, PDPage page, String prefix,
            float x, float y, String[] rows, String[] valueKeys, Context ctx, String totalKey) {
        return drawCompactPercentRows(acroForm, document, page, prefix, x, y, rows, valueKeys, ctx, totalKey,
                "TOTAL (must equal 100%)");
    }

    private static float drawCompactPercentRows(PDAcroForm acroForm, PDDocument document, PDPage page, String prefix,
            float x, float y, String[] rows, String[] valueKeys, Context ctx, String totalKey, String totalLabel) {
        return drawCompactPercentRows(acroForm, document, page, prefix, x, y, rows, valueKeys, ctx, totalKey,
                totalLabel, null, null);
    }

    private static float drawCompactPercentRows(PDAcroForm acroForm, PDDocument document, PDPage page, String prefix,
            float x, float y, String[] rows, String[] valueKeys, Context ctx, String totalKey, String totalLabel,
            String descriptionFieldName, String descriptionValue) {
        float tableX = x + 30;
        float labelWidth = 390;
        float percentWidth = 145;
        float headerHeight = 16;
        float rowHeight = 16;
        float cellY = y - headerHeight;

        drawBox(document, page, tableX, cellY, labelWidth, headerHeight, false);
        drawBox(document, page, tableX + labelWidth, cellY, percentWidth, headerHeight, false);
        addCenteredTextInCell(document, page, "% of Revenue", tableX + labelWidth, cellY + 4, percentWidth,
                BOLD_FONT, SMALL_FONT_SIZE);
        String[] percentFieldNames = new String[rows.length];
        String[] percentValues = new String[rows.length];
        for (int i = 0; i < rows.length; i++) {
            int lineCount = Math.max(1, wrapText(REGULAR_FONT, rows[i], SMALL_FONT_SIZE, labelWidth - 10).size());
            float currentRowHeight = Math.max(rowHeight, lineCount * 8.4f + 5f);
            cellY -= currentRowHeight;
            drawBox(document, page, tableX, cellY, labelWidth, currentRowHeight, false);
            drawBox(document, page, tableX + labelWidth, cellY, percentWidth, currentRowHeight, false);
            addTextInBox(document, page, rows[i], tableX + 6, cellY + currentRowHeight - 10f,
                    labelWidth - 10, false, SMALL_FONT_SIZE, currentRowHeight);
            String defaultValue = valueKeys != null && i < valueKeys.length ? getBlankIfNull(ctx, valueKeys[i]) : "";
            String percentFieldName = prefix + "Percent" + (i + 1);
            addField(acroForm, page, percentFieldName, defaultValue, tableX + labelWidth + percentWidth - 72,
                    cellY + 1f, 58, Math.max(14, currentRowHeight - 2), false);
            addText(document, page, "%", tableX + labelWidth + percentWidth - 10, cellY + 4f,
                    REGULAR_FONT, SMALL_FONT_SIZE);
            percentFieldNames[i] = percentFieldName;
            percentValues[i] = defaultValue;
        }

        if (descriptionFieldName != null && descriptionFieldName.length() > 0) {
            float descriptionHeight = 38;
            cellY -= descriptionHeight;
            addText(document, page, "Description :", tableX + 8, cellY + descriptionHeight - 20,
                    REGULAR_FONT, BODY_FONT_SIZE);
            addField(acroForm, page, descriptionFieldName, blankIfNull(descriptionValue),
                    tableX + 100, cellY + 7, labelWidth + percentWidth - 108, 20, false);
            drawBox(document, page, tableX + 100, cellY + 7, labelWidth + percentWidth - 108, 20, false);
        }

        cellY -= rowHeight;
        addCenteredTextInCell(document, page, totalLabel, tableX, cellY + 2, labelWidth,
                BOLD_FONT, SMALL_FONT_SIZE);
        addCalculatedTotalField(acroForm, page, prefix + "TotalPercent", sumFieldDefaults(percentValues),
                tableX + labelWidth + percentWidth - 72, cellY + 1f, 58, Math.max(14, rowHeight - 2),
                percentFieldNames);
        addText(document, page, "%", tableX + labelWidth + percentWidth - 10, cellY + 4f,
                REGULAR_FONT, SMALL_FONT_SIZE);
        return cellY - 16;
    }

    private static PDPage createSupplementPage(PDDocument document, String title) {
        PDPage page = createPage(document);
        drawSectionHeader(document, page, title, PAGE_MARGIN, 742);
        return page;
    }

    private static PDPage createPage(PDDocument document) {
        PDPage page = new PDPage(PDPage.PAGE_SIZE_LETTER);
        document.addPage(page);
        return page;
    }

    private static void addPageFooters(PDDocument document) {
        try {
            List<?> pages = document.getDocumentCatalog().getAllPages();
            int printedPageTotal = Math.max(1, pages.size() - 1);
            for (int i = 0; i < pages.size(); i++) {
                if (i == 1) {
                    continue;
                }
                PDPage page = (PDPage) pages.get(i);
                int printedPageNumber = i == 0 ? 1 : i;
                addText(document, page, FORM_NUMBER, PAGE_MARGIN, 18, REGULAR_FONT, SMALL_FONT_SIZE);
                addText(document, page, "Page " + printedPageNumber + " of " + printedPageTotal, 520, 18,
                        REGULAR_FONT, SMALL_FONT_SIZE);
            }
        } catch (Exception e) {
            logger.error("Unexpected error", e);
        }
    }

    private static void addLogoToPage(PDDocument document, PDPage page, float x, float y, float width, float height) {
        BufferedImage bufferedImage = resolveLogoImage();
        if (bufferedImage == null) {
            return;
        }

        PDPageContentStream contentStream = null;
        try {
            PDXObjectImage image = new PDJpeg(document, bufferedImage);
            contentStream = new PDPageContentStream(document, page, true, true, true);
            contentStream.drawXObject(image, x, y, width, height);
        } catch (Exception e) {
            logger.error("Unexpected error", e);
        } finally {
            closeQuietly(contentStream);
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
            File classLocation = new File(GenerateRenewalApplicationAcroForm.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI());
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
            inputStream = GenerateRenewalApplicationAcroForm.class.getResourceAsStream(resourcePath);
            if (inputStream == null && resourcePath.startsWith("/")) {
                inputStream = GenerateRenewalApplicationAcroForm.class.getClassLoader()
                        .getResourceAsStream(resourcePath.substring(1));
            }
            if (inputStream == null) {
                return null;
            }
            return ImageIO.read(inputStream);
        } catch (Exception e) {
            return null;
        } finally {
            closeQuietly(inputStream);
        }
    }

    private static void drawSectionHeader(PDDocument document, PDPage page, String title, float x, float y) {
        drawSectionHeader(document, page, title, x, y, 572);
    }

    private static void drawSectionHeader(PDDocument document, PDPage page, String title, float x, float y,
            float width) {
        PDPageContentStream contentStream = null;
        try {
            contentStream = new PDPageContentStream(document, page, true, true, true);
            contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
            contentStream.addRect(x, y, width, 16);
            contentStream.fill(1);
            contentStream.setStrokingColor(Color.BLACK);
            contentStream.setLineWidth(0.5f);
            contentStream.addRect(x, y, width, 16);
            contentStream.stroke();
        } catch (IOException e) {
            logger.error("Unexpected error", e);
        } finally {
            closeQuietly(contentStream);
        }
        addSectionHeaderText(document, page, title, x + 4, y + 4);
    }

    private static void addSectionHeaderText(PDDocument document, PDPage page, String title, float x, float y) {
        int parentheticalIndex = title.indexOf(" (as required");
        if (parentheticalIndex < 0) {
            addText(document, page, title, x, y, BOLD_FONT, BODY_FONT_SIZE);
            return;
        }

        String heading = title.substring(0, parentheticalIndex);
        String parenthetical = title.substring(parentheticalIndex);
        float nextX = addTextRun(document, page, heading, x, y, BOLD_FONT, BODY_FONT_SIZE);
        addTextRun(document, page, parenthetical, nextX, y, BOLD_ITALIC_FONT, BODY_FONT_SIZE);
    }

    private static void drawTableHeader(PDDocument document, PDPage page, String[] headers, float[] widths, float x, float y) {
        float currentX = x;
        for (int i = 0; i < headers.length; i++) {
            drawBox(document, page, currentX, y, widths[i], ROW_HEIGHT, false);
            addCenteredTextInCell(document, page, headers[i], currentX, y + 6, widths[i],
                    BOLD_FONT, BODY_FONT_SIZE);
            currentX += widths[i];
        }
    }

    private static void drawWrappedTableHeader(PDDocument document, PDPage page, String[] headers, float[] widths,
            float x, float y, float height) {
        float currentX = x;
        for (int i = 0; i < headers.length; i++) {
            drawBox(document, page, currentX, y, widths[i], height, false);
            List<String> lines = wrapText(BOLD_FONT, headers[i], SMALL_FONT_SIZE, widths[i] - 6);
            float textY = y + height - 9;
            for (int j = 0; j < lines.size() && j < 3; j++) {
                addCenteredTextInCell(document, page, lines.get(j), currentX, textY, widths[i],
                        BOLD_FONT, SMALL_FONT_SIZE);
                textY -= 8;
            }
            currentX += widths[i];
        }
    }

    private static void drawAttorneyRosterHeader(PDDocument document, PDPage page, String[] headers, float[] widths,
            float x, float y) {
        String[][] headerLines = {
                { "Attorney Name" },
                { "Attorney", "Designation" },
                { "States Licensed" },
                { "Annual Hours" },
                { "Date Joined" },
                { "# of Years", "in Practice" } };
        float currentX = x;
        for (int i = 0; i < headers.length; i++) {
            drawBox(document, page, currentX, y, widths[i], 48, false);
            float textY = y + 29;
            if (headerLines[i].length == 1) {
                textY = y + 23;
            }
            for (int j = 0; j < headerLines[i].length; j++) {
                addCenteredTextInCell(document, page, headerLines[i][j], currentX, textY, widths[i],
                        BOLD_FONT, BODY_FONT_SIZE);
                textY -= 10;
            }
            currentX += widths[i];
        }
    }

    private static void addCenteredTextInCell(PDDocument document, PDPage page, String text, float x, float y,
            float width) {
        addCenteredTextInCell(document, page, text, x, y, width, REGULAR_FONT, BODY_FONT_SIZE);
    }

    private static void addCenteredTextInCell(PDDocument document, PDPage page, String text, float x, float y,
            float width, PDFont font, float fontSize) {
        float textX = x + Math.max(2, (width - getTextWidth(font, text, fontSize)) / 2f);
        addText(document, page, text, textX, y, font, fontSize);
    }

    private static void drawBox(PDDocument document, PDPage page, float x, float y, float width, float height, boolean shaded) {
        PDPageContentStream contentStream = null;
        try {
            contentStream = new PDPageContentStream(document, page, true, true, true);
            if (shaded) {
                contentStream.setNonStrokingColor(new Color(230, 230, 230));
                contentStream.addRect(x, y, width, height);
                contentStream.fill(1);
            }
            contentStream.setStrokingColor(Color.BLACK);
            contentStream.setLineWidth(0.4f);
            contentStream.addRect(x, y, width, height);
            contentStream.stroke();
        } catch (IOException e) {
            logger.error("Unexpected error", e);
        } finally {
            closeQuietly(contentStream);
        }
    }

    private static void drawLine(PDDocument document, PDPage page, float x1, float y1, float x2, float y2,
            float lineWidth) {
        PDPageContentStream contentStream = null;
        try {
            contentStream = new PDPageContentStream(document, page, true, true, true);
            contentStream.setStrokingColor(Color.BLACK);
            contentStream.setLineWidth(lineWidth);
            contentStream.drawLine(x1, y1, x2, y2);
        } catch (IOException e) {
            logger.error("Unexpected error", e);
        } finally {
            closeQuietly(contentStream);
        }
    }

    private static void drawAttorneyRosterRow(PDDocument document, PDAcroForm acroForm, PDPage page, float x, float y,
            float[] widths, int row) {
        drawAttorneyRosterRow(document, acroForm, page, x, y, widths, row, Collections.EMPTY_MAP);
    }

    private static void drawAttorneyRosterRow(PDDocument document, PDAcroForm acroForm, PDPage page, float x, float y,
            float[] widths, int row, Map attorneyMap) {
        String[] names = { "attorneyName", "attorneyDesignation", "attorneyStatesLicensed", "attorneyAnnualHours",
                "attorneyDateJoined", "attorneyYearsInPractice" };
        String[] values = { getBlankIfNull(attorneyMap, "AttorneyName"), getBlankIfNull(attorneyMap, "AttorneyDesg"),
                getBlankIfNull(attorneyMap, "LicensedStates"), getBlankIfNull(attorneyMap, "AnnualWorkedHours"),
                getBlankIfNull(attorneyMap, "AttorneyPriorActDate"),
                getBlankIfNull(attorneyMap, "NumberOfYearsInPractice") };
        float currentX = x;
        float fieldXPadding = 5f;
        float fieldYPadding = 4f;
        for (int i = 0; i < widths.length; i++) {
            drawBox(document, page, currentX, y, widths[i], ROW_HEIGHT, false);
            addField(acroForm, page, names[i] + row, values[i], currentX + fieldXPadding, y + fieldYPadding,
                    widths[i] - (fieldXPadding * 2), ROW_HEIGHT - (fieldYPadding * 2), false);
            currentX += widths[i];
        }
    }

    private static void drawFieldsAcrossRow(PDDocument document, PDAcroForm acroForm, PDPage page, String prefix,
            float x, float y, float[] widths) {
        drawFieldsAcrossRow(document, acroForm, page, prefix, x, y, widths, Collections.EMPTY_MAP, new String[0]);
    }

    private static void drawFieldsAcrossRow(PDDocument document, PDAcroForm acroForm, PDPage page, String prefix,
            float x, float y, float[] widths, Map values, String[] keys) {
        float currentX = x;
        for (int i = 0; i < widths.length; i++) {
            drawBox(document, page, currentX, y, widths[i], ROW_HEIGHT, false);
            String defaultValue = i < keys.length ? getBlankIfNull(values, keys[i]) : "";
            addField(acroForm, page, prefix + "Column" + (i + 1), defaultValue, currentX + 2, y + 2,
                    widths[i] - 4, ROW_HEIGHT - 4, false);
            currentX += widths[i];
        }
    }

    private static float addQuestionWithYesNo(PDDocument document, PDAcroForm acroForm, PDPage page,
            String groupName, String text, float x, float y, float textWidth) {
        return addQuestionWithYesNo(document, acroForm, page, groupName, text, x, y, textWidth, "");
    }

    private static float addQuestionWithYesNo(PDDocument document, PDAcroForm acroForm, PDPage page,
            String groupName, String text, float x, float y, float textWidth, String defaultValue) {
        float questionTop = y;
        y = addQuestionText(document, page, text, x, y, textWidth, REGULAR_FONT, BODY_FONT_SIZE, 11f);
        addYesNoButtons(document, acroForm, page, groupName, defaultValue, x + textWidth + 15, questionTop - 2);
        return y - 10;
    }

    private static float addQuestionWithYesNoNA(PDDocument document, PDAcroForm acroForm, PDPage page,
            String groupName, String text, float x, float y, float textWidth) {
        return addQuestionWithYesNoNA(document, acroForm, page, groupName, text, x, y, textWidth, "");
    }

    private static float addQuestionWithYesNoNA(PDDocument document, PDAcroForm acroForm, PDPage page,
            String groupName, String text, float x, float y, float textWidth, String defaultValue) {
        float questionTop = y;
        y = addQuestionText(document, page, text, x, y, textWidth, REGULAR_FONT, BODY_FONT_SIZE, 11f);
        addYesNoNAButtons(document, acroForm, page, groupName, defaultValue, x + textWidth + 15, questionTop - 2);
        return y - 10;
    }

    private static float addYesNoQuestionList(PDDocument document, PDAcroForm acroForm, PDPage page,
            String fieldPrefix, String[] questions, float x, float y, float textWidth) {
        for (int i = 0; i < questions.length; i++) {
            y = addQuestionWithYesNo(document, acroForm, page, fieldPrefix + (i + 1), questions[i], x, y, textWidth);
        }
        return y;
    }

    private static float addYesNoQuestionList(PDDocument document, PDAcroForm acroForm, PDPage page,
            String fieldPrefix, String[] questions, float x, float y, float textWidth, Map values, String[] keys) {
        for (int i = 0; i < questions.length; i++) {
            String defaultValue = i < keys.length ? getYesNoValue(values, keys[i]) : "";
            y = addQuestionWithYesNo(document, acroForm, page, fieldPrefix + (i + 1), questions[i], x, y,
                    textWidth, defaultValue);
        }
        return y;
    }

    private static float addFraudNotice(PDDocument document, PDPage page, String noticeLabel, String noticeText,
            float x, float y) {
        float maxWidth = 575;
        float labelWidth = getTextWidth(BOLD_FONT, noticeLabel, FRAUD_NOTICE_FONT_SIZE);
        addText(document, page, noticeLabel, x, y, BOLD_FONT, FRAUD_NOTICE_FONT_SIZE);

        if (labelWidth < maxWidth - 120) {
            float textX = x + labelWidth + getTextWidth(REGULAR_FONT, " ", FRAUD_NOTICE_FONT_SIZE);
            float remainingWidth = maxWidth - labelWidth - getTextWidth(REGULAR_FONT, " ", FRAUD_NOTICE_FONT_SIZE);
            String[] words = sanitize(noticeText).split("\\s+");
            StringBuilder firstLine = new StringBuilder();
            int wordIndex = 0;
            while (wordIndex < words.length) {
                String candidate = firstLine.length() == 0 ? words[wordIndex]
                        : firstLine.toString() + " " + words[wordIndex];
                if (getTextWidth(REGULAR_FONT, candidate, FRAUD_NOTICE_FONT_SIZE) <= remainingWidth
                        || firstLine.length() == 0) {
                    firstLine.setLength(0);
                    firstLine.append(candidate);
                    wordIndex++;
                } else {
                    break;
                }
            }

            if (firstLine.length() > 0) {
                addText(document, page, firstLine.toString(), textX, y, REGULAR_FONT, FRAUD_NOTICE_FONT_SIZE);
                y -= FRAUD_NOTICE_LINE_HEIGHT;

                StringBuilder remainingText = new StringBuilder();
                while (wordIndex < words.length) {
                    if (remainingText.length() > 0) {
                        remainingText.append(' ');
                    }
                    remainingText.append(words[wordIndex]);
                    wordIndex++;
                }

                List<String> continuationLines = wrapText(REGULAR_FONT, remainingText.toString(),
                        FRAUD_NOTICE_FONT_SIZE, maxWidth);
                for (int i = 0; i < continuationLines.size(); i++) {
                    addText(document, page, continuationLines.get(i), x, y, REGULAR_FONT, FRAUD_NOTICE_FONT_SIZE);
                    y -= FRAUD_NOTICE_LINE_HEIGHT;
                }
            } else {
                y -= FRAUD_NOTICE_LINE_HEIGHT;
            }
        } else {
            y -= FRAUD_NOTICE_LINE_HEIGHT;
            y = addWrappedText(document, page, noticeText, x, y, maxWidth, REGULAR_FONT,
                    FRAUD_NOTICE_FONT_SIZE, FRAUD_NOTICE_LINE_HEIGHT);
        }

        return y - FRAUD_NOTICE_LINE_HEIGHT;
    }

    private static void addYesNoButtons(PDDocument document, PDAcroForm acroForm, PDPage page,
            String groupName, String defaultValue, float x, float y) {
        addRadioButtons(document, acroForm, page, groupName, createYesNoButtons(), defaultValue, x, y);
    }

    private static void addYesNoNAButtons(PDDocument document, PDAcroForm acroForm, PDPage page,
            String groupName, String defaultValue, float x, float y) {
        addRadioButtons(document, acroForm, page, groupName, createYesNoNAButtons(), defaultValue, x, y);
    }

    @SuppressWarnings("unchecked")
    private static void addReferenceYesNoButtons(PDDocument document, PDAcroForm acroForm, PDPage page,
            String groupName, String defaultValue, float labelX, float labelY,
            float yesBoxX, float yesBoxY, float noBoxX, float noBoxY) {
        try {
            addText(document, page, "Yes", labelX, labelY, REGULAR_FONT, BODY_FONT_SIZE);
            addText(document, page, "No", noBoxX - 18, labelY, REGULAR_FONT, BODY_FONT_SIZE);

            List<Button> buttonList = createYesNoButtons();
            List<String> buttonValues = new ArrayList<String>();
            for (Button button : buttonList) {
                buttonValues.add(button.value);
            }

            PDRadioCollection radioButton = new PDRadioCollection(acroForm, createFieldDictionary(BUTTON_FIELD_TYPE));
            radioButton.setPartialName(resolveFieldName(groupName));
            radioButton.getDictionary().setInt(COSName.FF, RADIO_BUTTON_FLAG);
            radioButton.setOptions(buttonValues);

            PDAppearanceCharacteristicsDictionary appearanceCharacteristics =
                    new PDAppearanceCharacteristicsDictionary(new COSDictionary());
            appearanceCharacteristics.setBorderColour(createColor(0, 0, 0));
            appearanceCharacteristics.setBackground(createColor(1, 1, 1));

            ArrayList<COSObjectable> radioOptions = new ArrayList<COSObjectable>();
            float[] xPositions = { yesBoxX, noBoxX };
            float[] yPositions = { yesBoxY, noBoxY };
            for (int i = 0; i < buttonList.size(); i++) {
                Button button = buttonList.get(i);
                PDCheckbox radioOption = new PDCheckbox(acroForm, createFieldDictionary(BUTTON_FIELD_TYPE));
                initializeWidgetAnnotation(radioOption.getDictionary());
                radioOption.setParent(radioButton);
                normalizeRadioWidgetDictionary(radioOption);

                PDAnnotationWidget widget = radioOption.getWidget();
                widget.setRectangle(createRectangle(xPositions[i], yPositions[i], 10.5f, 10.5f));
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
                appearanceMap.put(button.value, createRadioButtonAppearanceStream(document, widget, true));
                appearance.setNormalAppearance(appearanceMap);
                widget.setAppearance(appearance);
                widget.setAppearanceStream("Off");

                radioOptions.add(radioOption);
                page.getAnnotations().add(widget);
            }

            radioButton.setKids(radioOptions);
            acroForm.getFields().add(radioButton);
            applyRadioSelection(radioButton, radioOptions, buttonList, defaultValue);
        } catch (IOException e) {
            logger.error("Unexpected error", e);
        }
    }

    @SuppressWarnings("unchecked")
    private static void addRadioButtonsGrid(PDDocument document, PDAcroForm acroForm, PDPage page, String groupName,
            List<Button> buttonList, String defaultValue, float x, float y, int columns, float columnWidth,
            float rowHeight) {
        try {
            List<String> buttonValues = new ArrayList<String>();
            for (Button button : buttonList) {
                buttonValues.add(button.value);
            }

            PDRadioCollection radioButton = new PDRadioCollection(acroForm, createFieldDictionary(BUTTON_FIELD_TYPE));
            radioButton.setPartialName(resolveFieldName(groupName));
            radioButton.getDictionary().setInt(COSName.FF, RADIO_BUTTON_FLAG);
            radioButton.setOptions(buttonValues);

            PDAppearanceCharacteristicsDictionary appearanceCharacteristics =
                    new PDAppearanceCharacteristicsDictionary(new COSDictionary());
            appearanceCharacteristics.setBorderColour(createColor(0, 0, 0));
            appearanceCharacteristics.setBackground(createColor(1, 1, 1));

            ArrayList<COSObjectable> radioOptions = new ArrayList<COSObjectable>();
            for (int i = 0; i < buttonList.size(); i++) {
                Button button = buttonList.get(i);
                float currentX = x + (i % columns) * columnWidth;
                float currentY = y - (i / columns) * rowHeight;

                PDCheckbox radioOption = new PDCheckbox(acroForm, createFieldDictionary(BUTTON_FIELD_TYPE));
                initializeWidgetAnnotation(radioOption.getDictionary());
                radioOption.setParent(radioButton);
                normalizeRadioWidgetDictionary(radioOption);

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
                appearanceMap.put(button.value, createRadioButtonAppearanceStream(document, widget, true));
                appearance.setNormalAppearance(appearanceMap);
                widget.setAppearance(appearance);
                widget.setAppearanceStream("Off");

                radioOptions.add(radioOption);
                page.getAnnotations().add(widget);
                addText(document, page, button.label, currentX + RADIO_BUTTON_SIZE + RADIO_LABEL_GAP,
                        currentY + 2, false);
            }

            radioButton.setKids(radioOptions);
            acroForm.getFields().add(radioButton);
            applyRadioSelection(radioButton, radioOptions, buttonList, defaultValue);
        } catch (IOException e) {
            logger.error("Unexpected error", e);
        }
    }

    @SuppressWarnings("unchecked")
    private static void addRadioButtons(PDDocument document, PDAcroForm acroForm, PDPage page, String groupName,
            List<Button> buttonList, String defaultValue, float x, float y) {
        try {
            List<String> buttonValues = new ArrayList<String>();
            for (Button button : buttonList) {
                buttonValues.add(button.value);
            }

            PDRadioCollection radioButton = new PDRadioCollection(acroForm, createFieldDictionary(BUTTON_FIELD_TYPE));
            radioButton.setPartialName(resolveFieldName(groupName));
            radioButton.getDictionary().setInt(COSName.FF, RADIO_BUTTON_FLAG);
            radioButton.setOptions(buttonValues);

            PDAppearanceCharacteristicsDictionary appearanceCharacteristics =
                    new PDAppearanceCharacteristicsDictionary(new COSDictionary());
            appearanceCharacteristics.setBorderColour(createColor(0, 0, 0));
            appearanceCharacteristics.setBackground(createColor(1, 1, 1));

            ArrayList<COSObjectable> radioOptions = new ArrayList<COSObjectable>();
            float currentX = x;
            for (Button button : buttonList) {
                PDCheckbox radioOption = new PDCheckbox(acroForm, createFieldDictionary(BUTTON_FIELD_TYPE));
                initializeWidgetAnnotation(radioOption.getDictionary());
                radioOption.setParent(radioButton);
                normalizeRadioWidgetDictionary(radioOption);

                PDAnnotationWidget widget = radioOption.getWidget();
                widget.setRectangle(createRectangle(currentX, y, RADIO_BUTTON_SIZE, RADIO_BUTTON_SIZE));
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
                appearanceMap.put(button.value, createRadioButtonAppearanceStream(document, widget, true));
                appearance.setNormalAppearance(appearanceMap);
                widget.setAppearance(appearance);
                widget.setAppearanceStream("Off");

                radioOptions.add(radioOption);
                page.getAnnotations().add(widget);

                addText(document, page, button.label, currentX + RADIO_BUTTON_SIZE + RADIO_LABEL_GAP, y + 2, false);
                currentX += RADIO_BUTTON_SIZE + RADIO_LABEL_GAP
                        + getTextWidth(REGULAR_FONT, button.label, BODY_FONT_SIZE) + RADIO_OPTION_GAP;
            }

            radioButton.setKids(radioOptions);
            acroForm.getFields().add(radioButton);
            applyRadioSelection(radioButton, radioOptions, buttonList, defaultValue);
        } catch (IOException e) {
            logger.error("Unexpected error", e);
        }
    }

    private static void applyRadioSelection(PDRadioCollection radioButton, ArrayList<COSObjectable> radioOptions,
            List<Button> buttonList, String defaultValue) throws IOException {
        String selectedValue = resolveRadioSelectionValue(buttonList, defaultValue);
        if (selectedValue.length() > 0) {
            radioButton.setValue(selectedValue);
            radioButton.getDictionary().setItem(COSName.V, COSName.getPDFName(selectedValue));
            radioButton.getDictionary().setItem(COSName.DV, COSName.getPDFName(selectedValue));
        } else {
            radioButton.getDictionary().setItem(COSName.V, COSName.getPDFName("Off"));
            radioButton.getDictionary().setItem(COSName.DV, COSName.getPDFName("Off"));
        }
        for (int i = 0; i < radioOptions.size() && i < buttonList.size(); i++) {
            PDCheckbox radioOption = (PDCheckbox) radioOptions.get(i);
            String optionValue = buttonList.get(i).value;
            String appearanceState = optionValue.equals(selectedValue) ? optionValue : "Off";
            radioOption.getWidget().setAppearanceStream(appearanceState);
            radioOption.getDictionary().setItem(COSName.AS, COSName.getPDFName(appearanceState));
            radioOption.getDictionary().removeItem(COSName.V);
        }
    }

    private static void normalizeRadioWidgetDictionary(PDCheckbox radioOption) {
        radioOption.getDictionary().removeItem(COSName.FT);
        radioOption.getDictionary().removeItem(COSName.V);
    }

    private static String resolveRadioSelectionValue(List<Button> buttonList, String defaultValue) {
        String value = defaultValue == null ? "" : defaultValue.trim();
        if (value.length() == 0) {
            return "";
        }
        for (Button button : buttonList) {
            if (button.value.equals(value) || button.label.equalsIgnoreCase(value)) {
                return button.value;
            }
        }
        if ("Y".equalsIgnoreCase(value) || "YES".equalsIgnoreCase(value) || "TRUE".equalsIgnoreCase(value)) {
            return findRadioOptionValue(buttonList, "Yes", "Yes");
        }
        if ("N".equalsIgnoreCase(value) || "NO".equalsIgnoreCase(value) || "FALSE".equalsIgnoreCase(value)) {
            return findRadioOptionValue(buttonList, "No", "No");
        }
        if ("NA".equalsIgnoreCase(value) || "N/A".equalsIgnoreCase(value)) {
            return findRadioOptionValue(buttonList, "NA", "NA");
        }
        return value;
    }

    private static String findRadioOptionValue(List<Button> buttonList, String preferredValue, String label) {
        for (Button button : buttonList) {
            if (button.value.equals(preferredValue) || button.label.equalsIgnoreCase(label)) {
                return button.value;
            }
        }
        return preferredValue;
    }

    private static float addLabeledField(PDDocument document, PDAcroForm acroForm, PDPage page, String label,
            String fieldName, float labelX, float y, float fieldX, float fieldWidth) {
        return addLabeledField(document, acroForm, page, label, fieldName, "", labelX, y, fieldX, fieldWidth);
    }

    private static float addLabeledField(PDDocument document, PDAcroForm acroForm, PDPage page, String label,
            String fieldName, String defaultValue, float labelX, float y, float fieldX, float fieldWidth) {
        addText(document, page, label, labelX, y + 3, false);
        addBoxedField(document, acroForm, page, fieldName, defaultValue, fieldX, y, fieldWidth, 14);
        return y - 22;
    }

    private static void addBoxedField(PDDocument document, PDAcroForm acroForm, PDPage page, String name,
            String defaultValue, float x, float y, float fieldWidth, float fieldHeight) {
        drawBox(document, page, x, y, fieldWidth, fieldHeight, false);
        addField(acroForm, page, name, defaultValue, x, y, fieldWidth, fieldHeight, false);
    }

    private static void addUnderlinedField(PDDocument document, PDAcroForm acroForm, PDPage page, String name,
            float x, float y, float fieldWidth, float fieldHeight) {
        addUnderlinedField(document, acroForm, page, name, "", x, y, fieldWidth, fieldHeight);
    }

    private static void addUnderlinedField(PDDocument document, PDAcroForm acroForm, PDPage page, String name,
            String defaultValue, float x, float y, float fieldWidth, float fieldHeight) {
        drawLine(document, page, x, y + 1, x + fieldWidth, y + 1, 0.5f);
        addField(acroForm, page, name, defaultValue, x, y, fieldWidth, fieldHeight, false);
    }

    private static float addAreaPracticeDescriptionFields(PDDocument document, PDAcroForm acroForm, PDPage page,
            float x, float y, String[] labels, String[] fieldNames, String[] defaultValues) {
        float gap = 8f;
        float columnWidth = (572f - (gap * 2)) / 3f;
        float labelHeight = 12f;
        float fieldHeight = 22f;
        float fieldY = y - labelHeight - fieldHeight;
        for (int i = 0; i < labels.length; i++) {
            float columnX = x + (i * (columnWidth + gap));
            addTextInBox(document, page, labels[i], columnX, y - 3, columnWidth, true,
                    8f, labelHeight);
            drawBox(document, page, columnX, fieldY, columnWidth, fieldHeight, false);
            addMultilineField(acroForm, page, fieldNames[i], defaultValues[i],
                    columnX, fieldY, columnWidth, fieldHeight);
        }
        return fieldY - 3;
    }

    @SuppressWarnings("unchecked")
    private static void addMultilineField(PDAcroForm acroForm, PDPage page, String name, String defaultValue,
            float x, float y, float fieldWidth, float fieldHeight) {
        try {
            PDTextbox textBox = new PDTextbox(acroForm);
            initializeWidgetAnnotation(textBox.getDictionary());
            textBox.setPartialName(resolveFieldName(name));
            textBox.setAlternateFieldName(name);
            textBox.getDictionary().setInt(COSName.FF, TEXT_FIELD_MULTILINE_FLAG);
            applyTextFieldDefaults(textBox);
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
            borderStyleDictionary.setWidth(0f);
            borderStyleDictionary.setStyle(PDBorderStyleDictionary.STYLE_SOLID);
            widget.setBorderStyle(borderStyleDictionary);

            page.getAnnotations().add(widget);
            textBox.setValue(defaultValue == null ? "" : defaultValue);
        } catch (IOException e) {
            logger.error("Unexpected error", e);
        }
    }

    private static void addField(PDAcroForm acroForm, PDPage page, String name, String defaultValue,
            float x, float y, float fieldWidth, float fieldHeight) {
        addField(acroForm, page, name, defaultValue, x, y, fieldWidth, fieldHeight, true);
    }

    private static void addWholeNumberField(PDAcroForm acroForm, PDPage page, String name, String defaultValue,
            float x, float y, float fieldWidth, float fieldHeight) {
        addField(acroForm, page, name, formatWholeNumber(defaultValue), x, y, fieldWidth, fieldHeight, true, true);
    }

    @SuppressWarnings("unchecked")
    private static void addField(PDAcroForm acroForm, PDPage page, String name, String defaultValue,
            float x, float y, float fieldWidth, float fieldHeight, boolean drawBorder) {
        addField(acroForm, page, name, defaultValue, x, y, fieldWidth, fieldHeight, drawBorder, false);
    }

    @SuppressWarnings("unchecked")
    private static void addField(PDAcroForm acroForm, PDPage page, String name, String defaultValue,
            float x, float y, float fieldWidth, float fieldHeight, boolean drawBorder, boolean wholeNumberOnly) {
        try {
            PDTextbox textBox = new PDTextbox(acroForm);
            initializeWidgetAnnotation(textBox.getDictionary());
            textBox.setPartialName(resolveFieldName(name));
            textBox.setAlternateFieldName(name);
            applyTextFieldDefaults(textBox);
            if (wholeNumberOnly) {
                attachWholeNumberScript(textBox);
            }
            acroForm.getFields().add(textBox);

            PDAnnotationWidget widget = textBox.getWidget();
            float widgetHeight = fieldHeight < 12f ? fieldHeight : Math.max(fieldHeight, MIN_TEXT_FIELD_HEIGHT);
            widget.setRectangle(createRectangle(x, y, fieldWidth, widgetHeight));
            widget.setPage(page);
            widget.setPrinted(true);

            PDAppearanceCharacteristicsDictionary fieldAppearance =
                    new PDAppearanceCharacteristicsDictionary(new COSDictionary());
            fieldAppearance.setBorderColour(createColor(0, 0, 0));
            widget.setAppearanceCharacteristics(fieldAppearance);
            PDBorderStyleDictionary borderStyleDictionary = new PDBorderStyleDictionary();
            borderStyleDictionary.setWidth(drawBorder ? 0.5f : 0f);
            borderStyleDictionary.setStyle(PDBorderStyleDictionary.STYLE_SOLID);
            widget.setBorderStyle(borderStyleDictionary);

            page.getAnnotations().add(widget);
            textBox.setValue(wholeNumberOnly ? formatWholeNumber(defaultValue) : (defaultValue == null ? "" : defaultValue));
        } catch (IOException e) {
            logger.error("Unexpected error", e);
        }
    }

    private static void attachWholeNumberScript(PDTextbox textBox) {
        COSDictionary action = new COSDictionary();
        action.setItem(COSName.S, COSName.getPDFName("JavaScript"));
        action.setItem(COSName.getPDFName("JS"), new COSString(
                "if (!event.willCommit) { event.rc = /^[0-9]*$/.test(event.change); } "
                        + "else { event.rc = (event.value === '' || /^[0-9]+$/.test(event.value)); }"));
        COSDictionary additionalActions = new COSDictionary();
        additionalActions.setItem(COSName.getPDFName("K"), action);
        textBox.getDictionary().setItem(COSName.getPDFName("AA"), additionalActions);
    }

    @SuppressWarnings("unchecked")
    private static void addCalculatedTotalField(PDAcroForm acroForm, PDPage page, String name, String defaultValue,
            float x, float y, float fieldWidth, float fieldHeight, String[] sourceFieldNames) {
        try {
            PDTextbox textBox = new PDTextbox(acroForm);
            initializeWidgetAnnotation(textBox.getDictionary());
            textBox.setPartialName(resolveFieldName(name));
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
            logger.error("Unexpected error", e);
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
                    .append(escapeJavaScript(getCurrentFieldName(sourceFieldNames[i]))).append("\");\n");
        }
        script.append("event.value = total === 0 ? \"\" : String(Math.round(total * 100) / 100);");
        return script.toString();
    }

    private static String getCurrentFieldName(String baseName) {
        String normalized = toFieldName(baseName);
        Integer count = FIELD_NAME_COUNTS.get(normalized);
        if (count == null || count.intValue() <= 1) {
            return normalized;
        }
        return normalized + "_" + count.intValue();
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

    private static void applyTextFieldDefaults(PDTextbox textBox) {
        // Match the source AcroForm: text fields inherit the form-level default appearance.
    }

    private static void addText(PDDocument document, PDPage page, String text, float x, float y, boolean bold) {
        addText(document, page, text, x, y, bold ? BOLD_FONT : REGULAR_FONT, BODY_FONT_SIZE);
    }

    private static void addText(PDDocument document, PDPage page, String text, float x, float y,
            PDFont font, float fontSize) {
        PDPageContentStream contentStream = null;
        try {
            contentStream = new PDPageContentStream(document, page, true, true, true);
            contentStream.beginText();
            contentStream.setFont(font, scaleFontSize(fontSize));
            contentStream.moveTextPositionByAmount(x, y);
            contentStream.drawString(sanitize(text));
            contentStream.endText();
        } catch (IOException e) {
            logger.error("Unexpected error", e);
        } finally {
            closeQuietly(contentStream);
        }
    }

    private static float addTextRun(PDDocument document, PDPage page, String text, float x, float y,
            PDFont font, float fontSize) {
        addText(document, page, text, x, y, font, fontSize);
        return x + getTextWidth(font, text, fontSize);
    }

    private static float addTextWithBoldYes(PDDocument document, PDPage page, String text, float x, float y,
            PDFont font, float fontSize) {
        String cleanText = sanitize(text);
        float nextX = x;
        int start = 0;
        int yesIndex = cleanText.indexOf("Yes");
        PDFont yesFont = font == ITALIC_FONT ? BOLD_ITALIC_FONT : BOLD_FONT;
        while (yesIndex >= 0) {
            if (yesIndex > start) {
                nextX = addTextRun(document, page, cleanText.substring(start, yesIndex), nextX, y, font, fontSize);
            }
            nextX = addTextRun(document, page, "Yes", nextX, y, yesFont, fontSize);
            start = yesIndex + 3;
            yesIndex = cleanText.indexOf("Yes", start);
        }
        if (start < cleanText.length()) {
            nextX = addTextRun(document, page, cleanText.substring(start), nextX, y, font, fontSize);
        }
        return nextX;
    }

    private static float getTextWidthWithBoldYes(String text, PDFont font, float fontSize) {
        String cleanText = sanitize(text);
        float width = 0f;
        int start = 0;
        int yesIndex = cleanText.indexOf("Yes");
        PDFont yesFont = font == ITALIC_FONT ? BOLD_ITALIC_FONT : BOLD_FONT;
        while (yesIndex >= 0) {
            if (yesIndex > start) {
                width += getTextWidth(font, cleanText.substring(start, yesIndex), fontSize);
            }
            width += getTextWidth(yesFont, "Yes", fontSize);
            start = yesIndex + 3;
            yesIndex = cleanText.indexOf("Yes", start);
        }
        if (start < cleanText.length()) {
            width += getTextWidth(font, cleanText.substring(start), fontSize);
        }
        return width;
    }

    private static void addCenteredText(PDDocument document, PDPage page, String text, float y, PDFont font, float fontSize) {
        float x = (page.getMediaBox().getWidth() - getTextWidth(font, text, fontSize)) / 2f;
        addText(document, page, text, x, y, font, fontSize);
    }

    private static void addTextInBox(PDDocument document, PDPage page, String text, float x, float y,
            float maxWidth, boolean bold) {
        addTextInBox(document, page, text, x, y, maxWidth, bold, BODY_FONT_SIZE, 20f);
    }

    private static void addTextInBox(PDDocument document, PDPage page, String text, float x, float y,
            float maxWidth, boolean bold, float preferredFontSize, float maxHeight) {
        PDFont font = bold ? BOLD_FONT : REGULAR_FONT;
        float fontSize = fitFontSize(font, preferredFontSize, text, maxWidth);
        float lineHeight = Math.max(7f, fontSize + 1f);
        List<String> lines = wrapText(font, sanitize(text), fontSize, maxWidth);
        int maxLines = Math.max(1, (int) Math.floor(maxHeight / lineHeight));
        for (int i = 0; i < lines.size() && i < maxLines; i++) {
            addText(document, page, lines.get(i), x, y - (i * lineHeight), font, fontSize);
        }
    }

    private static float addWrappedText(PDDocument document, PDPage page, String text, float x, float y,
            float maxWidth, PDFont font, float fontSize, float lineHeight) {
        String cleanText = sanitize(text);
        float hangingIndent = getQuestionHangingIndent(cleanText, font, fontSize);
        if (hangingIndent <= 0) {
            List<String> lines = wrapText(font, cleanText, fontSize, maxWidth);
            for (int i = 0; i < lines.size(); i++) {
                addText(document, page, lines.get(i), x, y, font, fontSize);
                y -= lineHeight;
            }
            return y;
        }

        String[] words = cleanText.split("\\s+");
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

    private static float addWrappedTextWithBoldYes(PDDocument document, PDPage page, String text, float x, float y,
            float maxWidth, PDFont font, float fontSize, float lineHeight) {
        List<String> lines = wrapText(font, sanitize(text), fontSize, maxWidth);
        for (int i = 0; i < lines.size(); i++) {
            addTextWithBoldYes(document, page, lines.get(i), x, y, font, fontSize);
            y -= lineHeight;
        }
        return y;
    }

    private static float addQuestionText(PDDocument document, PDPage page, String text, float x, float y,
            float maxWidth, PDFont font, float fontSize, float lineHeight) {
        String cleanText = sanitize(text);
        float hangingIndent = getQuestionHangingIndent(cleanText, font, fontSize);
        if (hangingIndent <= 0) {
            return addWrappedTextWithBoldYes(document, page, cleanText, x, y, maxWidth, font, fontSize, lineHeight);
        }

        String[] words = cleanText.split("\\s+");
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

            addTextWithBoldYes(document, page, line.toString(), firstLine ? x : x + hangingIndent, y, font,
                    fontSize);
            y -= lineHeight;
            firstLine = false;
            line.setLength(0);
            line.append(word);
        }
        if (line.length() > 0) {
            addTextWithBoldYes(document, page, line.toString(), firstLine ? x : x + hangingIndent, y, font,
                    fontSize);
            y -= lineHeight;
        }
        return y;
    }

    private static float getQuestionHangingIndent(String text, PDFont font, float fontSize) {
        String cleanText = sanitize(text).trim();
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

    private static float addIfYesWrappedText(PDDocument document, PDPage page, String textAfterIfYes,
            float x, float y, float maxWidth, float fontSize, float lineHeight) {
        String prefix = "If \u201CYes\u201D";
        float prefixWidth = getTextWidthWithBoldYes(prefix, ITALIC_FONT, fontSize);
        String textAfterPrefix = sanitize(textAfterIfYes);
        boolean needsGap = textAfterPrefix.length() > 0 && ",.;:)".indexOf(textAfterPrefix.charAt(0)) < 0;
        float gapWidth = needsGap ? getTextWidth(REGULAR_FONT, " ", fontSize) : 0f;
        List<String> lines = wrapText(REGULAR_FONT, textAfterPrefix, fontSize,
                maxWidth - prefixWidth - gapWidth);
        float nextX = addTextWithBoldYes(document, page, prefix, x, y, ITALIC_FONT, fontSize);
        nextX += gapWidth;
        if (lines.size() > 0) {
            addText(document, page, lines.get(0), nextX, y, REGULAR_FONT, fontSize);
            y -= lineHeight;
            for (int i = 1; i < lines.size(); i++) {
                addText(document, page, lines.get(i), x, y, REGULAR_FONT, fontSize);
                y -= lineHeight;
            }
            return y;
        }
        return y - lineHeight;
    }

    private static float addJustifiedWrappedText(PDDocument document, PDPage page, String text, float x, float y,
            float maxWidth, PDFont font, float fontSize, float lineHeight) {
        List<String> lines = wrapText(font, sanitize(text), fontSize, maxWidth);
        for (int i = 0; i < lines.size(); i++) {
            if (i < lines.size() - 1) {
                addJustifiedLine(document, page, lines.get(i), x, y, maxWidth, font, fontSize);
            } else {
                addText(document, page, lines.get(i), x, y, font, fontSize);
            }
            y -= lineHeight;
        }
        return y;
    }

    private static void addJustifiedLine(PDDocument document, PDPage page, String text, float x, float y,
            float maxWidth, PDFont font, float fontSize) {
        String cleanText = sanitize(text).trim();
        if (cleanText.length() == 0) {
            return;
        }

        String[] words = cleanText.split("\\s+");
        if (words.length < 2) {
            addText(document, page, cleanText, x, y, font, fontSize);
            return;
        }

        float wordsWidth = 0f;
        for (int i = 0; i < words.length; i++) {
            wordsWidth += getTextWidth(font, words[i], fontSize);
        }

        if (wordsWidth >= maxWidth) {
            addText(document, page, cleanText, x, y, font, fontSize);
            return;
        }

        float spaceWidth = (maxWidth - wordsWidth) / (words.length - 1);
        float currentX = x;
        for (int i = 0; i < words.length; i++) {
            addText(document, page, words[i], currentX, y, font, fontSize);
            currentX += getTextWidth(font, words[i], fontSize) + spaceWidth;
        }
    }

    private static List<String> wrapText(PDFont font, String text, float fontSize, float maxWidth) {
        List<String> lines = new ArrayList<String>();
        String[] words = text.split("\\s+");
        StringBuilder currentLine = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String candidate = currentLine.length() == 0 ? words[i] : currentLine.toString() + " " + words[i];
            if (getTextWidth(font, candidate, fontSize) <= maxWidth || currentLine.length() == 0) {
                currentLine.setLength(0);
                currentLine.append(candidate);
            } else {
                lines.add(currentLine.toString());
                currentLine.setLength(0);
                currentLine.append(words[i]);
            }
        }

        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }
        return lines;
    }

    private static float fitFontSize(PDFont font, float preferredFontSize, String text, float maxWidth) {
        float textWidth = getTextWidth(font, text, preferredFontSize);
        if (maxWidth <= 0 || textWidth <= maxWidth) {
            return preferredFontSize;
        }
        return Math.max(MIN_TEXT_FONT_SIZE, preferredFontSize * maxWidth / textWidth);
    }

    private static float getTextWidth(PDFont font, String text, float fontSize) {
        if (text == null || text.length() == 0) {
            return 0;
        }
        try {
            return font.getStringWidth(sanitize(text)) / 1000f * scaleFontSize(fontSize);
        } catch (IOException e) {
            return text.length() * scaleFontSize(fontSize) * 0.5f;
        }
    }

    private static float scaleFontSize(float fontSize) {
        return fontSize * GLOBAL_FONT_SCALE;
    }

    private static PDRectangle createRectangle(float x, float y, float width, float height) {
        PDRectangle rectangle = new PDRectangle();
        rectangle.setLowerLeftX(x);
        rectangle.setLowerLeftY(y);
        rectangle.setUpperRightX(x + width);
        rectangle.setUpperRightY(y + height);
        return rectangle;
    }

    private static COSDictionary createFieldDictionary(String fieldType) {
        COSDictionary dictionary = new COSDictionary();
        dictionary.setItem(COSName.FT, COSName.getPDFName(fieldType));
        return dictionary;
    }

    private static void initializeWidgetAnnotation(COSDictionary dictionary) {
        dictionary.setItem(COSName.TYPE, COSName.ANNOT);
        dictionary.setItem(COSName.SUBTYPE, COSName.getPDFName(WIDGET_SUBTYPE));
        dictionary.setInt(COSName.getPDFName("F"), ANNOTATION_PRINT_FLAG);
    }

    private static PDAppearanceStream createRadioButtonAppearanceStream(PDDocument document,
            PDAnnotationWidget widget, boolean on) throws IOException {
        PDRectangle rect = widget.getRectangle();
        PDAppearanceStream appearanceStream = new PDAppearanceStream(document.getDocument().createCOSStream());
        appearanceStream.setBoundingBox(new PDRectangle(rect.getWidth(), rect.getHeight()));
        appearanceStream.setResources(new PDResources());
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
        writeAppearanceStream(appearanceStream, appearance.toString());
        return appearanceStream;
    }

    private static PDAppearanceStream createSquareButtonAppearanceStream(PDDocument document,
            PDAnnotationWidget widget, boolean on) throws IOException {
        PDRectangle rect = widget.getRectangle();
        PDAppearanceStream appearanceStream = new PDAppearanceStream(document.getDocument().createCOSStream());
        appearanceStream.setBoundingBox(new PDRectangle(rect.getWidth(), rect.getHeight()));
        appearanceStream.setResources(new PDResources());

        StringBuilder appearance = new StringBuilder();
        appearance.append("q\n");
        appearance.append("1 1 1 rg\n");
        appearance.append("0 0 ").append(formatPdfNumber(rect.getWidth())).append(" ")
                .append(formatPdfNumber(rect.getHeight())).append(" re\n");
        appearance.append("f\n");
        appearance.append("0 0 0 RG\n");
        appearance.append("0.5 w\n");
        appearance.append("0.25 0.25 ").append(formatPdfNumber(rect.getWidth() - 0.5f)).append(" ")
                .append(formatPdfNumber(rect.getHeight() - 0.5f)).append(" re\n");
        appearance.append("S\n");
        if (on) {
            appearance.append("1.8 5.3 m\n");
            appearance.append("4.2 2.4 l\n");
            appearance.append(formatPdfNumber(rect.getWidth() - 1.8f)).append(" ")
                    .append(formatPdfNumber(rect.getHeight() - 2.2f)).append(" l\n");
            appearance.append("1.2 w\n");
            appearance.append("S\n");
        }
        appearance.append("Q\n");
        writeAppearanceStream(appearanceStream, appearance.toString());
        return appearanceStream;
    }

    private static String circlePath(float x, float y, float r) {
        float c = r * 0.55228475f;
        return formatPdfNumber(x + r) + " " + formatPdfNumber(y) + " m\n"
                + formatPdfNumber(x + r) + " " + formatPdfNumber(y + c) + " "
                + formatPdfNumber(x + c) + " " + formatPdfNumber(y + r) + " "
                + formatPdfNumber(x) + " " + formatPdfNumber(y + r) + " c\n"
                + formatPdfNumber(x - c) + " " + formatPdfNumber(y + r) + " "
                + formatPdfNumber(x - r) + " " + formatPdfNumber(y + c) + " "
                + formatPdfNumber(x - r) + " " + formatPdfNumber(y) + " c\n"
                + formatPdfNumber(x - r) + " " + formatPdfNumber(y - c) + " "
                + formatPdfNumber(x - c) + " " + formatPdfNumber(y - r) + " "
                + formatPdfNumber(x) + " " + formatPdfNumber(y - r) + " c\n"
                + formatPdfNumber(x + c) + " " + formatPdfNumber(y - r) + " "
                + formatPdfNumber(x + r) + " " + formatPdfNumber(y - c) + " "
                + formatPdfNumber(x + r) + " " + formatPdfNumber(y) + " c\n";
    }

    private static float getLineWidth(PDAnnotationWidget widget) {
        PDBorderStyleDictionary borderStyleDictionary = widget.getBorderStyle();
        if (borderStyleDictionary != null) {
            return borderStyleDictionary.getWidth();
        }
        return 1f;
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
        return formatPdfNumber(color.getR()) + " " + formatPdfNumber(color.getG()) + " "
                + formatPdfNumber(color.getB()) + (stroking ? " RG\n" : " rg\n");
    }

    private static String formatPdfNumber(float value) {
        String formatted = String.format(Locale.US, "%.4f", new Object[] { Float.valueOf(value) });
        while (formatted.indexOf('.') >= 0 && formatted.endsWith("0")) {
            formatted = formatted.substring(0, formatted.length() - 1);
        }
        if (formatted.endsWith(".")) {
            formatted = formatted.substring(0, formatted.length() - 1);
        }
        return formatted.length() == 0 ? "0" : formatted;
    }

    private static void writeAppearanceStream(PDAppearanceStream appearanceStream, String content) throws IOException {
        OutputStream outputStream = appearanceStream.getStream().createUnfilteredStream();
        try {
            outputStream.write(content.getBytes("ISO-8859-1"));
        } finally {
            outputStream.close();
        }
    }

    private static String[][] createAreaOfPracticeRows() {
        return new String[][] {
                { "Administrative or Regulatory", "Investment Counseling / Money Management" },
                { "Admiralty and Maritime", "Juvenile or Guardianship" },
                { "Alternate Dispute Resolution", "Labor \u2013 Management" },
                { "Antitrust", "Labor \u2013 Union" },
                { "Appellate Practice", "Litigation \u2013 Other \u2013 Defense (please describe):" },
                { "Aviation", "" },
                { "Bankruptcy / Insolvency and Reorganization (1)", "Litigation \u2013 Other \u2013 Plaintiff (7) (please describe):" },
                { "Civil Litigation \u2013 Defense", "" },
                { "Civil Litigation \u2013 Plaintiff (7)", "Mass Tort Litigation / Class Actions \u2013 Defense" },
                { "Civil Rights or Discrimination", "Mass Tort Litigation / Class Actions \u2013 Plaintiff (7)" },
                { "Collections / Repossession (2)", "Medical Malpractice \u2013 Defense" },
                { "Communications / Media", "Medical Malpractice \u2013 Plaintiff (7)" },
                { "Construction", "Mergers and Acquisitions" },
                { "Construction Litigation \u2013 Defense", "Natural Resources" },
                { "Construction Litigation \u2013 Plaintiff (7)", "Non-Medical Malpractice \u2013 Defense" },
                { "Copyright / Trademark (3)", "Non-Medical Malpractice \u2013 Plaintiff (7)" },
                { "Corporate / Commercial / Business (11)", "Non-Profit / Charities" },
                { "Corporate / Commercial / Business Litigation \u2013 Defense", "Patent" },
                { "Corporate / Commercial / Business Litigation \u2013 Plaintiff (7)", "Personal Injury Litigation \u2013 Defense" },
                { "Creditor Debtor Rights", "Personal Injury Litigation \u2013 Plaintiff (7)" },
                { "Criminal Defense", "Product Liability Litigation \u2013 Defense" },
                { "Education", "Product Liability Litigation \u2013 Plaintiff (7)" },
                { "Elder Law", "Real Estate \u2013 Commercial (8)" },
                { "Employee Benefits (ERISA)", "Real Estate \u2013 Residential (8)" },
                { "Employment", "Securities and/or Security Litigation" },
                { "Entertainment / Sports", "Social Security / Disability" },
                { "Environmental", "Tax (9)" },
                { "Family Law (4)", "Traffic or DUI/DWI Defense" },
                { "Financial Institution / Banking / Finance (5)", "Trusts / Estates / Wills / Probate (10)" },
                { "Government / Municipal / Zoning (6)", "Utilities" },
                { "Healthcare", "Workers Compensation \u2013 Defense" },
                { "Immigration", "Workers Compensation \u2013 Plaintiff" },
                { "Insurance Defense", "Other (please describe):" },
                { "International", "" } };
    }

    private static List<Button> createYesNoButtons() {
        return createButtonList(new Button("Yes", "Yes"), new Button("No", "No"));
    }

    private static List<Button> createYesNoNAButtons() {
        return createButtonList(new Button("NA", "NA"), new Button("Yes", "Yes"), new Button("No", "No"));
    }

    private static List<Button> createRevenueButtons() {
        return createButtonList(
                new Button("$0-$100,000", "0To100000"),
                new Button("$100,000-$250,000", "100000To250000"),
                new Button("$250,000-$500,000", "250000To500000"),
                new Button("$500,000-$750,000", "500000To750000"),
                new Button("$750,000-$1,000,000", "750000To1000000"),
                new Button("$1,000,000+", "1000000Plus"));
    }

    private static List<Button> createButtonList(Button... buttonArray) {
        List<Button> buttons = new ArrayList<Button>();
        for (int i = 0; i < buttonArray.length; i++) {
            buttons.add(buttonArray[i]);
        }
        return buttons;
    }

    private static String resolveFieldName(String baseName) {
        String normalized = toFieldName(baseName);
        Integer count = FIELD_NAME_COUNTS.get(normalized);
        if (count == null) {
            FIELD_NAME_COUNTS.put(normalized, Integer.valueOf(1));
            return normalized;
        }

        int next = count.intValue() + 1;
        FIELD_NAME_COUNTS.put(normalized, Integer.valueOf(next));
        return normalized + "_" + next;
    }

    private static String toFieldName(String value) {
        String cleaned = sanitize(value)
                .replace("#", " number ")
                .replace("%", " percent ")
                .replace("$", " dollars ")
                .replace("&", " and ");
        String[] tokens = cleaned.split("[^A-Za-z0-9]+");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.length() == 0) {
                continue;
            }
            String lower = token.toLowerCase(Locale.ENGLISH);
            if (builder.length() == 0) {
                builder.append(lower);
            } else {
                builder.append(Character.toUpperCase(lower.charAt(0)));
                if (lower.length() > 1) {
                    builder.append(lower.substring(1));
                }
            }
        }

        if (builder.length() == 0) {
            builder.append("field");
        }
        if (Character.isDigit(builder.charAt(0))) {
            builder.insert(0, "field");
        }
        return builder.length() > 90 ? builder.substring(0, 90) : builder.toString();
    }

    private static String sanitize(String text) {
        if (text == null) {
            return "";
        }
        return text.replace('\u2018', '\'')
                .replace('\u2019', '\'')
                .replace('\u201c', '"')
                .replace('\u201d', '"')
                .replace('\u2013', '-')
                .replace('\u2014', '-')
                .replace('\u00a0', ' ');
    }

    private static void saveDocument(PDDocument document, OutputStream outputStream) throws IOException {
        try {
            document.save(outputStream);
        } catch (COSVisitorException e) {
            throw new IOException("Could not save renewal AcroForm PDF.", e);
        }
    }

    private static void closeQuietly(PDPageContentStream contentStream) {
        if (contentStream != null) {
            try {
                contentStream.close();
            } catch (IOException e) {
                logger.error("Unexpected error", e);
            }
        }
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                logger.error("Unexpected error", e);
            }
        }
    }

    private static class Button {
        private final String label;
        private final String value;

        Button(String label, String value) {
            this.label = label;
            this.value = value;
        }
    }
}
