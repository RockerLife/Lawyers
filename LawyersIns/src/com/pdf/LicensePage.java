package com.pdf;

import com.util.InetLogger;

import java.util.Map;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.util.Context;

public class LicensePage {
	private static final InetLogger logger = InetLogger.getInetLogger(LicensePage.class);

	public void addLicenseData(Document document, Context ctx) {

		try {
			
			Phrase phrase1 = new Phrase(
					"NOTICE TO ALASKA APPLICANTS: “A PERSON WHO KNOWINGLY AND WITH INTENT TO INJURE, DEFRAUD, "
							+ "OR DECEIVE AN INSURANCE COMPANY FILES A ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase2 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase3 = new Phrase(
					"CONTAINING FALSE, INCOMPLETE, OR MISLEADING"
							+ " INFORMATION MAY BE PROSECUTED UNDER STATE LAW.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase4 = new Phrase(
					"TO ARKANSAS APPLICANTS:  “ANY PERSON WHO KNOWINGLY PRESENTS A FALSE OR FRAUDULENT ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase5 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase6 = new Phrase("FOR PAYMENT OF A ", new Font(
					Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase7 = new Phrase(new Chunk("LOSS ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase8 = new Phrase(
					"OR BENEFIT OR KNOWINGLY PRESENTS FALSE INFORMATION IN AN APPLICATION FOR INSURANCE IS GUILTY OF A CRIME AND MAY BE SUBJECT TO FINES AND CONFINEMENT IN PRISON.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase9 = new Phrase(
					"NOTICE TO ARIZONA APPLICANTS:  “ANY PERSON WHO KNOWINGLY PRESENTS A FALSE OR FRAUDULENT ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase10 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase11 = new Phrase("FOR PAYMENT OF A ", new Font(
					Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase12 = new Phrase(new Chunk("LOSS ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase13 = new Phrase(
					"IS SUBJECT TO CRIMINAL AND CIVIL PENALTIES.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase14 = new Phrase(
					"NOTICE TO CALIFORNIA APPLICANTS:  “ANY PERSON WHO KNOWINGLY PRESENTS A FALSE OR FRAUDULENT ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase15 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase16 = new Phrase("FOR PAYMENT OF A ", new Font(
					Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase17 = new Phrase(new Chunk("LOSS ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase18 = new Phrase(
					"IS GUILTY OF A CRIME AND MAY BE SUBJECT TO FINES AND CONFINEMENT IN STATE PRISON.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase19 = new Phrase(
					"NOTICE TO COLORADO APPLICANTS:  \"IT IS UNLAWFUL TO KNOWINGLY PROVIDE FALSE, INCOMPLETE OR MISLEADING FACTS OR INFORMATION TO AN INSURANCE COMPANY FOR THE PURPOSE OF DEFRAUDING OR ATTEMPTING TO DEFRAUD THE COMPANY.  PENALTIES MAY INCLUDE IMPRISONMENT, FINES, DENIAL OF INSURANCE AND CIVIL DAMAGES.  ANY INSURANCE COMPANY OR AGENT OF AN INSURANCE COMPANY WHO KNOWINGLY PROVIDES FALSE, INCOMPLETE OR MISLEADING FACTS OR INFORMATION TO A POLICYHOLDER OR CLAIMANT FOR THE PURPOSE OF DEFRAUDING OR ATTEMPTING TO DEFRAUD THE POLICYHOLDER OR CLAIMANT WITH REGARD TO A SETTLEMENT OR AWARD PAYABLE FROM INSURANCE PROCEEDS SHALL BE REPORTED TO THE COLORADO DIVISION OF INSURANCE WITHIN THE DEPARTMENT OF REGULATORY AGENCIES.\"\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase20 = new Phrase(
					"NOTICE TO DISTRICT OF COLUMBIA APPLICANTS:  “WARNING: IT IS A CRIME TO PROVIDE FALSE OR MISLEADING INFORMATION TO AN ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase21 = new Phrase(new Chunk("INSURER ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase22 = new Phrase("FOR THE PURPOSE OF DEFRAUDING THE ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase23 = new Phrase(new Chunk("INSURER ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase24 = new Phrase(
					"OR ANY OTHER PERSON.  PENALTIES INCLUDE IMPRISONMENT AND/OR FINES.  IN ADDITION, AN INSURER MAY DENY INSURANCE BENEFITS IF FALSE INFORMATION MATERIALLY RELATED TO A ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase25 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase26 = new Phrase("WAS PROVIDED BY THE APPLICANT.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase27 = new Phrase(
					"NOTICE TO FLORIDA APPLICANTS:  “ANY PERSON WHO KNOWINGLY AND WITH INTENT TO INJURE, DEFRAUD OR DECEIVE ANY ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase28 = new Phrase(new Chunk("INSURER, ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase29 = new Phrase("FILES A STATEMENT OF ", new Font(
					Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase30 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase31 = new Phrase(
					"OR AN APPLICATION CONTAINING ANY FALSE, INCOMPLETE, OR MISLEADING INFORMATION IS GUILTY OF A FELONY OF THE THIRD DEGREE.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase32 = new Phrase(
					"NOTICE TO HAWAII APPLICANTS:  “FOR OUR PROTECTION, HAWAII LAW REQUIRES YOU TO BE INFORMED THAT PRESENTING A FRAUDULENT ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase33 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase34 = new Phrase("FOR PAYMENT OF A", new Font(
					Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase35 = new Phrase(new Chunk("LOSS ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase36 = new Phrase(
					"OR BENEFIT IS A CRIME PUNISHABLE BY FINES OR IMPRISONMENT OR BOTH.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase37 = new Phrase(
					"NOTICE TO IDAHO APPLICANTS:  “ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD OR DECEIVE ANY INSURANCE COMPANY, FILES A STATEMENT CONTAINING ANY FALSE, INCOMPLETE OR MISLEADING INFORMATION IS GUILTY OF A FELONY.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase38 = new Phrase(
					"NOTICE TO INDIANA APPLICANTS:  “ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD AN ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase39 = new Phrase(new Chunk("INSURER, ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase40 = new Phrase("FILES A STATEMENT OF ", new Font(
					Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase41 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase42 = new Phrase(
					"CONTAINING ANY FALSE, INCOMPLETE OR MISLEADING INFORMATION COMMITS A FELONY.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase43 = new Phrase(
					"NOTICE TO KENTUCKY APPLICANTS:  \"ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR OTHER PERSON FILES AN APPLICATION FOR INSURANCE CONTAINING ANY MATERIALLY FALSE INFORMATION OR CONCEALS, FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT MATERIAL THERETO COMMITS A FRAUDULENT ACT, WHICH IS A CRIME.\"\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase44 = new Phrase(
					"NOTICE TO LOUISIANA APPLICANTS: “ANY PERSON WHO KNOWINGLY PRESENTS A FALSE OR FRAUDULENT ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase45 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase46 = new Phrase("FOR PAYMENT OF A", new Font(
					Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase47 = new Phrase(new Chunk("LOSS ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase48 = new Phrase(
					"OR BENEFIT OR KNOWINGLY PRESENTS FALSE INFORMATION IN AN APPLICATION FOR INSURANCE IS GUILTY OF A CRIME AND MAY BE SUBJECT TO FINES AND CONFINEMENT IN PRISON.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase49 = new Phrase(
					"NOTICE TO MAINE APPLICANTS: “IT IS A CRIME TO KNOWINGLY PROVIDE FALSE, INCOMPLETE, OR MISLEADING INFORMATION TO AN INSURANCE COMPANY FOR THE PURPOSE OF DEFRAUDING THE COMPANY. PENALTIES MAY INCLUDE IMPRISONMENT, FINES, OR DENIAL OF INSURANCE BENEFITS.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase50 = new Phrase(
					"NOTICE TO MARYLAND APPLICANTS:  “ANY PERSON WHO KNOWINGLY AND WILLFULLY PRESENTS A FALSE OR FRAUDULENT ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase51 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase52 = new Phrase("FOR PAYMENT OF A", new Font(
					Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase53 = new Phrase(new Chunk("LOSS ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase54 = new Phrase(
					"OR BENEFIT OR WHO KNOWINGLY AND WILLFULLY PRESENTS FALSE INFORMATION IN AN APPLICATION FOR INSURANCE IS GUILTY OF A CRIME AND MAY BE SUBJECT TO FINES AND CONFINEMENT TO PRISON.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase55 = new Phrase(
					"NOTICE TO MASSACHUSETTS APPLICANTS:  “ANY PERSON HOW KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR ANTOHER PERSON FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase56 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase57 = new Phrase(
					"CONTAINING ANY MATERIALLY FALSE INFORMATION, OR CONCEALS FOR THE PURPOSE OF MISLEADING INFORMATION CONCERNING ANY FACT MATERIAL THERETO, COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME AND MAY SUBJECT THE PERSON TO CRIMINAL AND CIVIL PENALTIES.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase58 = new Phrase(
					"NOTICE TO MINNESOTA APPLICANTS:  “A PERSON WHO SUBMITS AN APPLICATION OR FILES A ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase59 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase60 = new Phrase(
					"WITH INTENT TO DEFRAUD OR HELPS COMMIT A FRAUD AGAINST AN ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase61 = new Phrase(new Chunk("INSURER ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase62 = new Phrase("IS GUILTY OF A CRIME.\"\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase63 = new Phrase("NOTICE TO MISSOURI APPLICANTS: “",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase64 = new Phrase(new Chunk("DEFENSE COSTS ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase65 = new Phrase(
					"PAID UNDER THE POLICY PROVISIONS WILL REDUCE THE AVAILABLE LIMIT OF LIABILITY AND MAY EXHAUST THEM COMPLETELY.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase66 = new Phrase(
					"NOTICE TO NEBRASKA APPLICANTS:  “ANY PERSON HOW KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR ANOTHER PERSON FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase67 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase68 = new Phrase(
					"CONTAINING ANY MATERIALLY FALSE INFORMATION, OR CONCEALS FOR THE PURPOSE OF MISLEADING INFORMATION CONCERNING ANY FACT MATERIAL THERETO, COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME AND MAY SUBJECT THE PERSON TO CRIMINAL AND CIVIL PENALTIES.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase69 = new Phrase(
					"NOTICE TO NEW JERSEY APPLICANTS:  “ANY PERSON WHO INCLUDES ANY INFORMATION ON AN APPLICATION FOR AN INSURANCE POLICY IS SUBJECT TO CRIMINAL AND CIVIL PENALTIES.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase70 = new Phrase(
					"NOTICE TO NEW MEXICO APPLICANTS:  “ANY PERSON WHO KNOWINGLY PRESENTS A FALSE OR FRAUDULENT ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase71 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase72 = new Phrase("FOR PAYMENT OF A", new Font(
					Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase73 = new Phrase(new Chunk("LOSS ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase74 = new Phrase(
					"OR BENEFIT OR KNOWINGLY PRESENTS FALSE INFORMATION IN AN APPLICATION FOR INSURANCE IS GUILTY OF A CRIME AND MY BE SUBJECT TO CIVIL FINES AND CRIMINAL PENALTIES.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase75 = new Phrase(
					"NOTICE TO NEW YORK APPLICANTS: \"ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR OTHER PERSON FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase76 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase77 = new Phrase(
					"CONTAINING ANY MATERIALLY FALSE INFORMATION, OR CONCEALS FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT MATERIAL THERETO, COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME, AND SHALL ALSO BE SUBJECT TO A CIVIL PENALTY NOT TO EXCEED FIVE THOUSAND DOLLARS AND THE STATED VALUE OF THE ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase78 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase79 = new Phrase("FOR EACH SUCH VIOLATION.\"",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase80 = new Phrase(
					"NOTICE TO OHIO APPLICANTS:  \"ANY PERSON WHO WITH INTENT TO DEFRAUD OR KNOWING HE IS FACILITATING A FRAUD AGAINST AN ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase81 = new Phrase(new Chunk("INSURER, ", new Font(
					Font.TIMES_ROMAN, 10, Font.ITALIC)));
			Phrase phrase82 = new Phrase("SUBMITS AN APPLICATION OR FILES A ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase83 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase84 = new Phrase(
					"CONTAINING A FALSE OR DECEPTIVE STATEMENT IS GUILTY OF INSURANCE FRAUD.\"\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase85 = new Phrase(
					"NOTICE TO OKLAHOMA APPLICANTS:  “WARNING: ANY PERSON WHO KNOWINGLY, AND WITH INTENT TO INJURE, DEFRAUD OR DECEIVE ANY ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase86 = new Phrase(new Chunk("INSURER, ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase87 = new Phrase("MAKES ANY ", new Font(
					Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase88 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase89 = new Phrase(
					"FOR THE PROCEEDS OF AN INSURANCE POLICY CONTAINING ANY FALSE, INCOMPLETE OR MISLEADING INFORMATION IS GUILTY OF A FELONY.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase90 = new Phrase(
					"NOTICE TO OREGON APPLICANTS:  “ANY PERSON HOW KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR ANTOHER PERSON FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase91 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase92 = new Phrase(
					"CONTAINING ANY MATERIALLY FALSE INFORMATION, OR CONCEALS FOR THE PURPOSE OF MISLEADING INFORMATION CONCERNING ANY FACT MATERIAL THERETO, COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME AND MAY SUBJECT THE PERSON TO CRIMINAL AND CIVIL PENALTIES.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase93 = new Phrase(
					"NOTICE TO PENNSYLVANIA APPLICANTS:  \"ANY PERSON WHO KNOWINGLY AND WITH INTENT TO DEFRAUD ANY INSURANCE COMPANY OR OTHER PERSON FILES AN APPLICATION FOR INSURANCE OR STATEMENT OF ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase94 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase95 = new Phrase(
					"CONTAINING ANY MATERIALLY FALSE INFORMATION OR CONCEALS FOR THE PURPOSE OF MISLEADING, INFORMATION CONCERNING ANY FACT MATERIAL THERETO COMMITS A FRAUDULENT INSURANCE ACT, WHICH IS A CRIME AND SUBJECTS SUCH PERSON TO CRIMINAL AND CIVIL PENALTIES.\"\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase96 = new Phrase(
					"NOTICE TO TENNESSEE APPLICANTS:  “IT IS A CRIME TO KNOWINGLY PROVIDE FALSE, INCOMPLETE OR MISLEADING INFORMATION TO AN INSURANCE COMPANY FOR THE PURPOSE OF DEFRAUDING THE COMPANY.  PENALTIES INCLUDE IMPRISONMENT, FINES AND DENIAL OF INSURANCE BENEFITS.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase97 = new Phrase(
					"NOTICE TO VERMONT APPLICANTS: ANY PERSON WHO KNOWINGLY PRESENTS A FALSE STAEMENT IN AN APPLICATION FOR INSURANCE MAY BE GUILTY OF A CRIMINALOFFENSE AND SUBJECT TO PENALTIES UNDER STATE LAW.\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase98 = new Phrase(
					"NOTICE TO VIRGINIA APPLICANTS:  “IT IS A CRIME TO KNOWINGLY PROVIDE FALSE, INCOMPLETE OR MISLEADING INFORMATION TO AN INSURANCE COMPANY FOR THE PURPOSE OF DEFRAUDING THE COMPANY.  PENALTIES INCLUDE IMPRISONMENT, FINES AND DENIAL OF INSURANCE BENEFITS.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase99 = new Phrase(
					"NOTICE TO WASHINGTON APPLICANTS:  “IT IS A CRIME TO KNOWINGLY PROVIDE FALSE, INCOMPLETE OR MISLEADING INFORMATION TO AN INSURANCE COMPANY FOR THE PURPOSE OF DEFRAUDING THE COMPANY.  PENALTIES INCLUDE IMPRISONMENT, FINES AND DENIAL OF INSURANCE BENEFITS.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			Phrase phrase100 = new Phrase(
					"NOTICE TO WEST VIRGINIA APPLICANTS:  “ANY PERSON WHO KNOWINGLY PRESENTS A FALSE OR FRAUDULENT ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase101 = new Phrase(new Chunk("CLAIM ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase102 = new Phrase("FOR PAYMENT OF A ", new Font(
					Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase103 = new Phrase(new Chunk("LOSS ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase104 = new Phrase(
					"OR BENEFIT OR KNOWINGLY PRESENTS FALSE INFORMATION IN AN APPLICATION FOR INSURANCE IS GUILTY OF A CRIME AND MAY BE SUBJECT TO FINES AND CONFINEMENT TO PRISON.”\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			
			// Common
			Phrase phrase105 = new Phrase(
					"Completion and/or signing of this application does not bind the Applicant to purchase, nor the ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase106 = new Phrase(new Chunk("Insurer ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase107 = new Phrase(
					"to provide, any insurance policy; however, no policy can be issued unless the application is properly completed, signed and dated.\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase108 = new Phrase(
					"The signatory declares that (s)he is authorized by the Applicant to sign this application on behalf of all prospective ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase109 = new Phrase(new Chunk("Insured ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase110 = new Phrase(
					"and that to the best of his/her knowledge the statements herein are true.  The signatory agrees that if the information supplied in this application and the materials submitted therewith should change between the date this application is signed and the effective date of the proposed insurance, the signatory shall immediately notify the Insurer of such and shall provide the ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase111 = new Phrase(new Chunk("Insurer ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase112 = new Phrase(
					"with information that would complete, update or correct the application or materials submitted therewith.  The ",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase113 = new Phrase(new Chunk("Insurer ", new Font(
					Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
			Phrase phrase114 = new Phrase(
					"may withdraw or modify any of the terms or conditions of coverage accordingly.\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
			Phrase phrase115 = new Phrase(
					"ALL WRITTTEN STATEMENTS, SUPPLEMENTAL APPLICATION AND MATERIALS FURNISHED TO THE INSURER IN CONJUNCTION WITH THIS APPLICATION ARE INCORPORATED BY REFERENCE INTO THIS APPLICATION AND MADE A PART THEREOF, AND DEEMED ATTACHED HERETO.\n\n",
					new Font(Font.TIMES_ROMAN, 10, Font.BOLD));
			Phrase phrase116 = new Phrase(
					"ANY PERSON WHO, WITH INTENT TO DEFRAUD OR KNOWING THAT (S)HE IS FACILITATING A FRAUD AGAINST AN INSURER, SUBMITS AN APPLICATION OR FILES A CLAIM CONTAINING A FALSE OR DECEPTIVE STATEMENT IS GUILTY OF INSURANCE FRAUD.\n\n\n\n",
					new Font(Font.TIMES_ROMAN, 9, Font.BOLD));
			Phrase phrase117 = new Phrase(
					"SIGNATURE*_________________________________________  PRINTED NAME* ___________________________________________________\n",
					new Font(Font.TIMES_ROMAN, 9, Font.NORMAL));
			Phrase phrase118 = new Phrase(
					"*MUST BE SIGNED BY A DULY AUTHORIZED OFFICER OF THE APPLICANT ON BEHALF OF ALL INSUREDS.\n\n\n\n",
					new Font(Font.TIMES_ROMAN, 9, Font.NORMAL));
			Phrase phrase119 = new Phrase(
					"TITLE OF SIGNATORY: _______________________________________________________________ DATE SIGNED: ______/_____/__________\n",
					new Font(Font.TIMES_ROMAN, 9, Font.NORMAL));
			
			Phrase phrase120 = new Phrase(new Chunk("MM  /   DD   / YYYY\n",
					new Font(Font.TIMES_ROMAN, 9, Font.NORMAL)));
			
			Phrase phrase121 = new Phrase(
					"PROGRAM AGENT: Protexure.;",
					new Font(Font.TIMES_ROMAN, 9, Font.NORMAL));
			
			Object objUser = ctx.get(PDFConstants.USER_FREEFORM_01);
			Map userMap = null;
			if (objUser != null)
				userMap = (Map) objUser;
			
			String AgentName = PDFUtils.getValueFromMap(userMap, "AgentName", false);
			
			Object objFirm = ctx.get(PDFConstants.FIRM_FREEFORM_01);
			Map firmMap = null;
			if (objFirm != null)
				firmMap = (Map) objFirm;
			
			String LicenseNumber = PDFUtils.getValueFromMap(firmMap, "LicenseNumber", false);
			
			
			PdfPTable maintable  = new PdfPTable(2);
			maintable.setWidthPercentage(100);
			
			Phrase phrase122= new Phrase("AGENT NAME: ",new Font(Font.TIMES_ROMAN, 9, Font.NORMAL));
			Phrase phrase123= new Phrase(AgentName,new Font(Font.TIMES_ROMAN, 9, Font.NORMAL));
			Phrase phrase124= new Phrase("FLORIDA LICENSE # ",new Font(Font.TIMES_ROMAN, 9, Font.NORMAL));
			Phrase phrase125= new Phrase(LicenseNumber,new Font(Font.TIMES_ROMAN, 9, Font.NORMAL));
			PdfPCell cell_florida=new PdfPCell(phrase121);
			cell_florida.setColspan(2);
			cell_florida.setBorder(0);
			maintable.addCell(cell_florida);
			
			Paragraph paragraph_sub=new Paragraph();
			paragraph_sub.add(phrase122);
			paragraph_sub.add(phrase123);
			PdfPCell clientNameNmclabel =new PdfPCell(paragraph_sub);
			clientNameNmclabel.setBorder(0);
			maintable.addCell(clientNameNmclabel);
			
			Paragraph paragraph_sub2=new Paragraph();
			paragraph_sub2.add(phrase124);
			paragraph_sub2.add(phrase125);
			clientNameNmclabel =new PdfPCell(paragraph_sub2);
			clientNameNmclabel.setBorder(0);
			maintable.addCell(clientNameNmclabel);
			
			Phrase phrasespace=new Phrase(new Chunk("         ",new Font(Font.TIMES_ROMAN, 9, Font.NORMAL)));
			Paragraph paragraph = new Paragraph();
			paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
			
			Object objAddress = ctx.get(PDFConstants.ADDRESS_FREEFORM_01);
			Map addressMap = null;
			if (objAddress != null)
				addressMap = (Map) objAddress;
			
			String StateCode = PDFUtils.getValueFromMap(addressMap, "StateCode", false);
			
			//Alaska
			if("AK".equals(StateCode))
			{
				paragraph.add(phrase1);
				paragraph.add(phrase2);
				paragraph.add(phrase3);
			}			
			
			//Arkansas
			else if("AR".equals(StateCode))
			{
				paragraph.add(phrase4);
				paragraph.add(phrase5);
				paragraph.add(phrase6);
				paragraph.add(phrase7);
				paragraph.add(phrase8);
			}
			
			//Arizona
			else if("AZ".equals(StateCode))
			{
				paragraph.add(phrase9);
				paragraph.add(phrase10);
				paragraph.add(phrase11);
				paragraph.add(phrase12);
				paragraph.add(phrase13);
			}
			
			//Califirnia
			else if("CA".equals(StateCode))
			{
				paragraph.add(phrase14);
				paragraph.add(phrase15);
				paragraph.add(phrase16);
				paragraph.add(phrase17);
				paragraph.add(phrase18);
			}
			
			// Colorado
			else if("CO".equals(StateCode))			
				paragraph.add(phrase19);
			
			//Coloumbia
			else if("CL".equals(StateCode))
			{
				paragraph.add(phrase20);
				paragraph.add(phrase21);
				paragraph.add(phrase22);
				paragraph.add(phrase23);
				paragraph.add(phrase24);
				paragraph.add(phrase25);
				paragraph.add(phrase26);
			}
			
			//Florida
			else if("FL".equals(StateCode))
			{
				paragraph.add(phrase27);
				paragraph.add(phrase28);
				paragraph.add(phrase29);
				paragraph.add(phrase30);
				paragraph.add(phrase31);
			}
			
			//Hawaii
			else if("HI".equals(StateCode))
			{
				paragraph.add(phrase32);
				paragraph.add(phrase33);
				paragraph.add(phrase34);
				paragraph.add(phrase35);
				paragraph.add(phrase36);
			}
			
			//Idaho
			else if("ID".equals(StateCode))
				paragraph.add(phrase37);
			
			//Indiana
			else if("IN".equals(StateCode))
			{
				paragraph.add(phrase38);
				paragraph.add(phrase39);
				paragraph.add(phrase40);
				paragraph.add(phrase41);
				paragraph.add(phrase42);
			}
			
			//Louisiana
			else if("LA".equals(StateCode))
			{
				paragraph.add(phrase43);
				paragraph.add(phrase44);
				paragraph.add(phrase45);
				paragraph.add(phrase46);
				paragraph.add(phrase47);
				paragraph.add(phrase48);
			}
			
			//Maine
			else if("ME".equals(StateCode))
				paragraph.add(phrase49);
			
			//Maryland
			else if("MD".equals(StateCode))
			{
				paragraph.add(phrase50);
				paragraph.add(phrase51);
				paragraph.add(phrase52);
				paragraph.add(phrase53);
				paragraph.add(phrase54);
			}
			
			//Massachusets
			else if("MA".equals(StateCode))
			{
				paragraph.add(phrase55);
				paragraph.add(phrase56);
				paragraph.add(phrase57);
			}
			
			//Minnesota
			else if("MN".equals(StateCode))
			{
				paragraph.add(phrase58);
				paragraph.add(phrase59);
				paragraph.add(phrase60);
				paragraph.add(phrase61);
				paragraph.add(phrase62);
			}
			
			//Missouri
			else if("MO".equals(StateCode))
			{
				paragraph.add(phrase63);
				paragraph.add(phrase64);
				paragraph.add(phrase65);
			}
			
			//Nebraska
			else if("NE".equals(StateCode))
			{
				paragraph.add(phrase66);
				paragraph.add(phrase67);
				paragraph.add(phrase68);
			}
			
			// New Jersy
			else if("NJ".equals(StateCode))
				paragraph.add(phrase69);
			
			//Maxico
			else if("NM".equals(StateCode))
			{
				paragraph.add(phrase70);			
				paragraph.add(phrase71);
				paragraph.add(phrase72);
				paragraph.add(phrase73);
				paragraph.add(phrase74);
			}
			
			// New York
			else if("NY".equals(StateCode))
			{
				paragraph.add(phrase75);
				paragraph.add(phrase76);
				paragraph.add(phrase77);
				paragraph.add(phrase78);
				paragraph.add(phrase79);
			}
			
			//Ohio	
			else if("OH".equals(StateCode))
			{
				paragraph.add(phrase80);
				paragraph.add(phrase81);
				paragraph.add(phrase82);
				paragraph.add(phrase83);
				paragraph.add(phrase84);
			}
			
			//Oklahoma
			else if("OK".equals(StateCode))
			{
				paragraph.add(phrase85);
				paragraph.add(phrase86);
				paragraph.add(phrase87);
				paragraph.add(phrase88);
				paragraph.add(phrase89);
			}
			
			//Oregon
			else if("OR".equals(StateCode))
			{
				paragraph.add(phrase90);
				paragraph.add(phrase91);
				paragraph.add(phrase92);
			}
			
			//Pennsylvania
			else if("PA".equals(StateCode))
			{
				paragraph.add(phrase93);
				paragraph.add(phrase94);
				paragraph.add(phrase95);
			}
			
			//Tennesse
			else if("TN".equals(StateCode))
				paragraph.add(phrase96);
			
			//Vermont
			else if("VT".equals(StateCode))
				paragraph.add(phrase97);
			
			//Virginia
			else if("VA".equals(StateCode))
				paragraph.add(phrase98);
			
			//Wasinghton
			else if("WA".equals(StateCode))
				paragraph.add(phrase99);
			
			//West Verginia
			else if("WV".equals(StateCode))
			{
				paragraph.add(phrase100);
				paragraph.add(phrase101);
				paragraph.add(phrase102);
				paragraph.add(phrase103);
				paragraph.add(phrase104);
			}
			
			Paragraph paragraph2=new Paragraph();
			
			paragraph2.add(phrase105);
			paragraph2.add(phrase106);
			paragraph2.add(phrase107);
			paragraph2.add(phrase108);
			paragraph2.add(phrase109);
			paragraph2.add(phrase110);
			paragraph2.add(phrase111);
			paragraph2.add(phrase112);
			paragraph2.add(phrase113);
			paragraph2.add(phrase114);
			paragraph2.add(phrase115);
			paragraph2.add(phrase116);
			paragraph2.add(phrase117);
			paragraph2.add(phrase118);
			paragraph2.add(phrase119);
			paragraph2.add(phrasespace);
						
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(phrasespace);
			paragraph2.add(new Phrase(new Chunk("  ",new Font(Font.TIMES_ROMAN, 9, Font.NORMAL))));
			paragraph2.add(phrase120);
			paragraph.setLeading(12f);
			paragraph2.setLeading(12f);			
			
			document.add(paragraph);
			document.add(paragraph2);
			if("FL".equals(StateCode))
				document.add(maintable);
			
		} catch (Exception e) {
			logger.error("Unexpected error", e);
		}
	}
}
