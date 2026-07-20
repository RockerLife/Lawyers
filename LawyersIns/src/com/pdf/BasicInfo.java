package com.pdf;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.lowagie.text.Chunk;
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

public class BasicInfo {

	public void getDataForBasicInfo(Context ctx) throws Exception {
		Object objAccount = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"Account.findByKey", ctx);
		ctx.put(PDFConstants.ACCOUNT_FREEFORM_01, objAccount);

		Object objAddress = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"SqlStmts.BasicInfoviewgetAddressdetails", ctx);
		ctx.put(PDFConstants.ADDRESS_FREEFORM_01, objAddress);

		Object objPolicy = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"Policy.findByKey", ctx);
		ctx.put(PDFConstants.POLICY_FREEFORM_01, objPolicy);

		Object objFirm = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"Firm.findByKey", ctx);
		ctx.put(PDFConstants.FIRM_FREEFORM_01, objFirm);

		Object objPolicyCoverage = SqlResources.getSqlMapProcessor(ctx)
				.findByKey("SqlStmts.BasicInfoviewCoveragePolicy", ctx);
		ctx.put(PDFConstants.POLICYCOVERAGE_FREEFORM_01, objPolicyCoverage);

		Object objProfessionalLiabilityInsDetail = SqlResources
				.getSqlMapProcessor(ctx).findByKey(
						"ProfessionalLiabilityInsDetail.findByKey", ctx);
		ctx.put(PDFConstants.PROFESSIONALLIABILITYINS_FREEFORM_01,
				objProfessionalLiabilityInsDetail);
		
		Object objLimitDeductibleProfessionalLiabilityInsDetail = SqlResources
		.getSqlMapProcessor(ctx).findByKey(
				"SqlStmts.UserStatementsaveAccountstmtsgeLimitDeductibleProfessionalLiabilityInsDetail", ctx);
		ctx.put(PDFConstants.LIMITDEDUCTIBLEPROFESSIONALLIABILITYINS_FREEFORM_01,
				objLimitDeductibleProfessionalLiabilityInsDetail);
		
		
		Object listClaimIncidentSupp = SqlResources.getSqlMapProcessor(ctx)
				.select("SqlStmts.BasicInfoviewgetClaimIncidentSupp", ctx);
		ctx.put(PDFConstants.BASICINFO_LIST_04, listClaimIncidentSupp);

		Object listPersonnelAffiliation = SqlResources.getSqlMapProcessor(ctx)
				.select("SqlStmts.BasicInfoviewmomfirmaffiliates", ctx);
		ctx.put(PDFConstants.BASICINFO_LIST_01, listPersonnelAffiliation);

		Object listGrossRevenueForFirm = SqlResources.getSqlMapProcessor(ctx)
				.select("SqlStmts.BasicInfoviewmomgrossrevenue", ctx);
		ctx.put(PDFConstants.BASICINFO_LIST_03, listGrossRevenueForFirm);

		Object listPublicPrivateOfferingSupp = SqlResources.getSqlMapProcessor(
				ctx).select(
				"SqlStmts.BasicInfoviewListPublicPrivateOfferingSupp", ctx);
		ctx.put(PDFConstants.BASICINFO_LIST_06, listPublicPrivateOfferingSupp);

		Object listAOP = SqlResources.getSqlMapProcessor(ctx).select(
				"SqlStmts.UserStatementsaveAccountstmtspopulateAOPFields", ctx);
		ctx.put(PDFConstants.BASICINFO_LIST_AOP, listAOP);
		
		Object objUser = SqlResources.getSqlMapProcessor(ctx).findByKey(
				"UserDetails.findByKey", ctx);
		ctx.put(PDFConstants.USER_FREEFORM_01, objUser);

		new General().getDataForGeneral(ctx);

	}

	public void makeLayoutForBasicInfo(Context ctx) throws Exception {

	}

	public PdfPTable populateBasicInfoData(Document document, Context ctx, PdfWriter writer)
			throws Exception {
		PDFUtils.addSpace(document);
		PdfPTable maintable = new PdfPTable(1);
		maintable.setWidthPercentage(100);
		PDFUtils.addHeader(document, "FIRM INFORMATION");

		populateFirmInfo(document, ctx);
		populatePeronnalAffiliation(document, ctx);
		populateGrossRevenue(document, ctx);
		populateFirmInfoMain(document, ctx, writer);

		return maintable;
	}

	public PdfPTable populateFirmInfo(Document document, Context ctx)
			throws Exception {
		Object objFirm = ctx.get(PDFConstants.FIRM_FREEFORM_01);
		Map firmMap = null;
		if (objFirm != null)
			firmMap = (Map) objFirm;

		Object objAccount = ctx.get(PDFConstants.ACCOUNT_FREEFORM_01);
		Map accountMap = null;
		if (objAccount != null)
			accountMap = (Map) objAccount;

		Object objAddress = ctx.get(PDFConstants.ADDRESS_FREEFORM_01);
		Map addressMap = null;
		if (objAddress != null)
			addressMap = (Map) objAddress;

		Object objPolicy = ctx.get(PDFConstants.POLICY_FREEFORM_01);
		Map policyMap = null;
		if (objPolicy != null)
			policyMap = (Map) objPolicy;
		
//		Object objPolicyCoverage = ctx.get(PDFConstants.POLICYCOVERAGE_FREEFORM_01);
//		Map policyCoverageMap = null;
//		if(objPolicyCoverage != null)
//			policyCoverageMap = (Map)objPolicyCoverage;

//		if (firmMap != null && accountMap != null && addressMap != null
//				&& policyMap != null && policyCoverageMap != null) {
			
		String AccountName = PDFUtils.getValueFromMap(accountMap, "AccountName", false);
		String AccountEmail = PDFUtils.getValueFromMap(accountMap, "AccountEmail", false);
		
		String ContactPerson = PDFUtils.getValueFromMap(firmMap, "ContactPerson", false);
		String ContactPhone = PDFUtils.getValueFromMap(firmMap, "ContactPhone", false);
		String ContactPhoneExt=PDFUtils.getValueFromMap(firmMap, "ContactPhoneExt", false);
		
		String Address1 = PDFUtils.getValueFromMap(addressMap, "Address1", false);
		String Address2 = PDFUtils.getValueFromMap(addressMap, "Address2", false);
		String City = PDFUtils.getValueFromMap(addressMap, "City", false);
		String StateDesc = PDFUtils.getValueFromMap(addressMap, "StateDesc", false);
		String Zip = PDFUtils.getValueFromMap(addressMap, "Zip", false);
		String Zip4 = PDFUtils.getValueFromMap(addressMap, "Zip4", false);
		String WorkPhone = PDFUtils.getValueFromMap(addressMap, "WorkPhone", false);
		String WorkExt = PDFUtils.getValueFromMap(addressMap, "WorkExt", false);
		
		String PolicyEffectiveDate = "";
		if(policyMap != null && policyMap.get("PolicyEffectiveDate") != null)
			PolicyEffectiveDate = PDFUtils.getFormattedDateFromObject(policyMap.get("PolicyEffectiveDate"));
		
		String PolicyNumber = PDFUtils.getValueFromMap(policyMap, "PolicyNumber", false);
		
			PdfPTable tableBasicInfo=new PdfPTable(4);
			tableBasicInfo.setWidthPercentage(100);
			tableBasicInfo.setSpacingBefore(10f);
			tableBasicInfo.setSpacingAfter(0f);
			
			PdfPCell firmNameLabel = PDFUtils
					.addPDFCellWithDefalutSize("Firm Name: " );
			firmNameLabel.setBorder(0);
			tableBasicInfo.addCell(firmNameLabel);
			
			PdfPCell firmNameLabelValue = PDFUtils
					.getCustomFontValue(AccountName, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
			firmNameLabelValue.setBorder(0);
			firmNameLabelValue.setColspan(3);
			tableBasicInfo.addCell(firmNameLabelValue);
			
			
			PdfPCell addressLabel = PDFUtils
					.addPDFCellWithDefalutSize("Address: ");
			addressLabel.setBorder(0);
			tableBasicInfo.addCell(addressLabel);

			PdfPCell addressLabelValue = PDFUtils
					.getCustomFontValue(Address1 + " "
							+ Address2, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
			addressLabelValue.setBorder(0);
			tableBasicInfo.addCell(addressLabelValue);

			PdfPCell cityLabel = PDFUtils
					.addPDFCellWithDefalutSize("City: ");
			cityLabel.setBorder(0);
			tableBasicInfo.addCell(cityLabel);

			PdfPCell cityLabelValue = PDFUtils
					.getCustomFontValue(City, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
			cityLabelValue.setBorder(0);
			tableBasicInfo.addCell(cityLabelValue);

			PdfPCell stateLabel = PDFUtils.addPDFCellWithDefalutSize("State: ");
			stateLabel.setBorder(0);
			tableBasicInfo.addCell(stateLabel);

			PdfPCell stateLabelValue = PDFUtils.getCustomFontValue(StateDesc, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
			stateLabelValue.setBorder(0);
			tableBasicInfo.addCell(stateLabelValue);

			PdfPCell zipLabel = PDFUtils.addPDFCellWithDefalutSize("Zip: ");
			zipLabel.setBorder(0);
			tableBasicInfo.addCell(zipLabel);
			
			Paragraph ph=new Paragraph ();
			Phrase phrase1=new Phrase(Zip ,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
			ph.add(phrase1);
			Phrase phrase2;
			Phrase phrase3;
			if(!Zip4.equalsIgnoreCase("")){
				phrase2=new Phrase(" - ",new Font(Font.TIMES_ROMAN,10,Font.BOLD));
				phrase3=new Phrase(Zip4 ,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
				ph.add(phrase2);
				ph.add(phrase3);
			}
			PdfPCell zipLabelValue = new PdfPCell(ph);
			zipLabelValue.setBorder(0);
			tableBasicInfo.addCell(zipLabelValue);

			PdfPCell workPhoneLabel = PDFUtils.addPDFCellWithDefalutSize("Firm Phone Number: ");
			workPhoneLabel.setBorder(0);
			tableBasicInfo.addCell(workPhoneLabel);
			ph=new Paragraph ();
			phrase1=new Phrase(WorkPhone ,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
			ph.add(phrase1);
			if(!WorkExt.equalsIgnoreCase("")){
				phrase2=new Phrase(" Ext: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
				phrase3=new Phrase(WorkExt ,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
				ph.add(phrase2);
				ph.add(phrase3);
			}
			PdfPCell workPhoneLabelValue = new PdfPCell(ph);
			workPhoneLabelValue.setBorder(0);
			tableBasicInfo.addCell(workPhoneLabelValue);

			PdfPCell accEmailLabel = PDFUtils.addPDFCellWithDefalutSize("E-mail Address: ");
			accEmailLabel.setBorder(0);
			tableBasicInfo.addCell(accEmailLabel);
			
			PdfPCell accEmailLabelValue = PDFUtils.getCustomFontValue(AccountEmail, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
			accEmailLabelValue.setBorder(0);
			tableBasicInfo.addCell(accEmailLabelValue);

			PdfPCell contactLabel = PDFUtils
					.addPDFCellWithDefalutSize("Contacts: ");
			contactLabel.setBorder(0);
			tableBasicInfo.addCell(contactLabel);
			
			/*PdfPTable contacttable=new PdfPTable(2);
			contacttable.setWidthPercentage(100);
			*/
			float[] width12={2f,3f,2f,3f};
			String cellContactCheck=null;
			if("Yes".equalsIgnoreCase("")){
				cellContactCheck="Mr.";
			}else if("No".equalsIgnoreCase("")){
				cellContactCheck="Ms.";
			}else{
				cellContactCheck="";
			}
			/*contacttable.addCell(cellContactCheck);
			*/
			PdfPCell contactValue = PDFUtils
					.getCustomFontValue(cellContactCheck+ " " + ContactPerson, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
			contactValue.setBorder(0);
			tableBasicInfo.addCell(contactValue);
			
//			PdfPCell cellContact=new PdfPCell(contacttable);
//			cellContact.setBorder(0);
//			tableBasicInfo.addCell(cellContact);
			
			phrase1=new Phrase( "Contact Phone " ,new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
			phrase2=new Phrase("(if different than above)",new Font(Font.TIMES_ROMAN,8,Font.ITALIC));
			ph=new Paragraph ();
			ph.add(phrase1);
			ph.add(phrase2);
			PdfPCell contactPhoneLabel = new PdfPCell(ph);
			contactPhoneLabel.setBorder(0);
			tableBasicInfo.addCell(contactPhoneLabel);
			
			phrase1=new Phrase(ContactPhone ,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
			ph=new Paragraph ();
			ph.add(phrase1);
			if(!ContactPhoneExt.equalsIgnoreCase("")){
				phrase2=new Phrase(" Ext: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
				phrase3=new Phrase(ContactPhoneExt ,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
				ph.add(phrase2);
				ph.add(phrase3);						
			}
			PdfPCell contactPhoneLabelValue = new PdfPCell(ph);
			contactPhoneLabelValue.setBorder(0);
			tableBasicInfo.addCell(contactPhoneLabelValue);
			
			document.add(tableBasicInfo);

			PdfPTable effDateTable = new PdfPTable(3);
			effDateTable.setWidthPercentage(100);
			float[] width = { 2.5f, 20.5f, 70f };
			effDateTable.setWidths(width);
			
			PdfPCell q1SNo = PDFUtils.addPDFCellWithDefalutSize("1.");
			q1SNo.setBorder(0);
			effDateTable.addCell(q1SNo);
			
			PdfPCell effDatelabel = PDFUtils
					.addPDFCellWithDefalutSize("Desired Effective Date: ");
			effDatelabel.setBorder(0);
			PdfPCell effDateValue = PDFUtils
					.getCustomFontValue(PolicyEffectiveDate, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
			effDateValue.setBorder(0);

			PdfPCell effDateEmpty = PDFUtils.addPDFCellWithDefalutSize(" ");
			effDateEmpty.setBorder(0);
			PdfPCell effDateFormat = PDFUtils
					.addPDFCellWithCustomSize("(MM/DD/YYYY)", 8, false);
			effDateTable.addCell(effDatelabel);
			effDateTable.addCell(effDateValue);
			effDateTable.addCell(effDateEmpty);
			effDateTable.addCell(effDateEmpty);
			effDateTable.addCell(effDateFormat);
			
			document.add(effDateTable);			
//		}

		return null;
	}

	public PdfPTable populatePeronnalAffiliation(Document document, Context ctx)
			throws Exception {
		// PDFUtils.addSpace(document);
		PdfPTable headTable = new PdfPTable(3);
		headTable.setWidthPercentage(100);
		headTable.setSpacingBefore(10f);
		headTable.setSpacingAfter(0f);
		float[] width = {2.5f, 51.5f, 21f };
		headTable.setWidths(width);
		
		PdfPCell q2SNo=PDFUtils.addPDFCellValueWithDefalutSize("2.");
		q2SNo.setBorder(0);
		headTable.addCell(q2SNo);
		
		PdfPCell label = PDFUtils
				.addPDFCellWithDefalutSize("Please indicate the number of personnel in the Firm:");
		label.setBorder(0);
		label.setColspan(2);
		headTable.addCell(label);

		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		float[] widths = { 2.5f, 0.5f, 2.5f, 0.5f };
		table.setWidths(widths);

		Object obj = ctx.get(PDFConstants.BASICINFO_LIST_01);
		List list = null;
		if (obj != null)
			list = (List) obj;
		int total = 0;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (i == 0 || i == 3) {
					Map row = (Map) list.get(i);
					String str = row.get("PersonnelTypeNumbering") != null ? row
							.get("PersonnelTypeNumbering").toString()
							: "";
					String str1 = row.get("PersonnelTypeDesc") != null ? row
							.get("PersonnelTypeDesc").toString() : "";
					if (!"".equals(str))
						str = "(" + str + ") ";
					PdfPCell cell = PDFUtils.addPDFCellWithDefalutSize(str
							+ str1);
					cell.setBorder(0);
					table.addCell(cell);

					// str =
					// row.get("PersonnelTypeDesc")!=null?row.get("PersonnelTypeDesc").toString():"";
					// cell = PDFUtils.addPDFCellWithDefalutSize(str);
					// cell.setBorder(0);
					// table.addCell(cell);

					str = row.get("NumberOfPersonnel") != null ? row.get(
							"NumberOfPersonnel").toString() : "";
					cell = PDFUtils.getCustomFontValue(str, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
					cell.setBorder(0);
					table.addCell(cell);

					if (row.get("NumberOfPersonnel") != null) {

						if (!"".equals(row.get("NumberOfPersonnel").toString()))
							total = total
									+ Integer.parseInt(row.get(
											"NumberOfPersonnel").toString());
					}

				}
			}

			for (int i = 0; i < list.size(); i++) {
				if (i == 1 || i == 4) {
					Map row = (Map) list.get(i);
					String str = row.get("PersonnelTypeNumbering") != null ? row
							.get("PersonnelTypeNumbering").toString()
							: "";
					String str1 = row.get("PersonnelTypeDesc") != null ? row
							.get("PersonnelTypeDesc").toString() : "";
					if (!"".equals(str))
						str = "(" + str + ") ";
					PdfPCell cell = PDFUtils.addPDFCellWithDefalutSize(str
							+ str1);
					cell.setBorder(0);
					table.addCell(cell);

					// str =
					// row.get("PersonnelTypeDesc")!=null?row.get("PersonnelTypeDesc").toString():"";
					// cell = PDFUtils.addPDFCellWithDefalutSize(str);
					// cell.setBorder(0);
					// table.addCell(cell);

					str = row.get("NumberOfPersonnel") != null ? row.get(
							"NumberOfPersonnel").toString() : "";
					cell = PDFUtils.getCustomFontValue(str, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
					cell.setBorder(0);
					table.addCell(cell);

					if (i != 1) {
						if (row.get("NumberOfPersonnel") != null) {
							if (!"".equals(row.get("NumberOfPersonnel")
									.toString()))
								total = total
										+ Integer
												.parseInt(row.get(
														"NumberOfPersonnel")
														.toString());
						}
					}

				}
			}

			for (int i = 0; i < list.size(); i++) {
				if (i == 2 || i == 5) {
					Map row = (Map) list.get(i);
					String str = row.get("PersonnelTypeNumbering") != null ? row
							.get("PersonnelTypeNumbering").toString()
							: "";
					String str1 = row.get("PersonnelTypeDesc") != null ? row
							.get("PersonnelTypeDesc").toString() : "";
					if (!"".equals(str))
						str = "(" + str + ") ";
					PdfPCell cell = PDFUtils.addPDFCellWithDefalutSize(str
							+ str1);
					cell.setBorder(0);
					table.addCell(cell);

					// str =
					// row.get("PersonnelTypeDesc")!=null?row.get("PersonnelTypeDesc").toString():"";
					// cell = PDFUtils.addPDFCellWithDefalutSize(str);
					// cell.setBorder(0);
					// table.addCell(cell);

					str = row.get("NumberOfPersonnel") != null ? row.get(
							"NumberOfPersonnel").toString() : "";
					cell = PDFUtils.getCustomFontValue(str, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
					cell.setBorder(0);
					table.addCell(cell);
					if (row.get("NumberOfPersonnel") != null) {

						if (!"".equals(row.get("NumberOfPersonnel").toString()))
							total = total
									+ Integer.parseInt(row.get(
											"NumberOfPersonnel").toString());
					}
				}
			}
		}

		PdfPCell blankCell =PDFUtils.addPDFCellWithCustomSize(" ", 5, false);
		blankCell.setColspan(4);
		table.addCell(blankCell);
		PdfPCell cell = PDFUtils.getBoldCell("Total Firm Personnel ");
		cell.setBorder(0);
		table.addCell(cell);
		Font font=new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f));
		font.setStyle(Font.UNDERLINE);
		font.setStyle(Font.BOLD);
		Phrase phrase1=new Phrase(new Integer(total).toString(),font);
		Phrase phrase2=new Phrase(" (A + B + C + D + E)",new Font(Font.TIMES_ROMAN,8,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
		Paragraph paragraph=new Paragraph();
		paragraph.add(phrase1);
		paragraph.add(phrase2);
		cell = new PdfPCell(paragraph);
		cell.setBorder(0);
		cell.setColspan(3);
		table.addCell(cell);
		// cell = PDFUtils.getBoldCell("(A)+(B)+(C)+(D)+(E)");
		// cell.setBorder(0);
		// table.addCell(cell);

		// cell = PDFUtils.addPDFCell("");
		// cell.setBorder(0);
		// table.addCell(cell);
		// table.addCell(cell);
		// table.addCell(cell);

		PdfPCell blank = PDFUtils.addPDFCellWithDefalutSize(" ");
		blank.setBorder(0);
		
		PdfPCell cell2 = new PdfPCell(table);
		cell2.setBorder(0);
		cell2.setColspan(2);
		headTable.addCell(blank);
		headTable.addCell(cell2);
		// document.add(table);
		document.add(headTable);
		return null;
	}

	public PdfPTable populateGrossRevenue(Document document, Context ctx)
			throws Exception {
		//PDFUtils.addSpace(document);
		PdfPTable headTable = new PdfPTable(3);
		headTable.setWidthPercentage(100);
		headTable.setSpacingBefore(10f);
		headTable.setSpacingAfter(0f);
		float[] width = {2.5f, 51.5f, 21f };
		headTable.setWidths(width);
		
		PdfPCell q3SNo=PDFUtils.addPDFCellValueWithDefalutSize("3.");
		q3SNo.setBorder(0);
		headTable.addCell(q3SNo);
		
		PdfPCell label = PDFUtils
				.addPDFCellWithDefalutSize("Gross annual revenue for the Firm for the last 3 years:");
		label.setBorder(0);
		label.setColspan(2);
		headTable.addCell(label);

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);

		Object obj = ctx.get(PDFConstants.BASICINFO_LIST_03);
		List list = null;
		if (obj != null)
			list = (List) obj;
		
		long total = 0;
		int count = 0;
		
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Map row = (Map) list.get(i);
				String str = row.get("GrossRevenueDesc") != null ? row.get(
						"GrossRevenueDesc").toString() : "";
				PdfPCell cell = PDFUtils.addPDFCelHeader(str);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

			}
			for (int i = 0; i < list.size(); i++) {
				Map row = (Map) list.get(i);
				String str = row.get("YearEndDate") != null ? row.get(
						"YearEndDate").toString() : "";
				PdfPCell cell = PDFUtils.getCustomFontValue("FYE:" + str, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				table.addCell(cell);

			}
			// for(int i=0; i<list.size(); i++)
			// {
			// Map row = (Map) list.get(i);
			// PdfPCell cell = PDFUtils.addPDFCellWithDefalutSize(" MM/YYYY" );
			// table.addCell(cell);
			// }
			for (int i = 0; i < list.size(); i++) {
				
				Map row = (Map) list.get(i);
				Object objAmount = row.get("Amount");
				if(objAmount != null && !"".equals(objAmount.toString()) && !"0".equals(objAmount.toString()))
	   			{
	   				total = total + Long.parseLong(objAmount.toString());
	   				count = count + 1;
	   			}
				
				PdfPCell cell=null;
				if(row.get("Amount") != null){
					cell = PDFUtils.getCustomFontValue(PDFGenerator.formatter.format(new Double(row.get("Amount")
							.toString())), 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);					
				}else{
					cell = PDFUtils.getCustomFontValue("", 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
				}
				table.addCell(cell);				
			}
			
			if(count > 0)
    		{
    			long AverageRevenue = total/count;
    			ctx.put("AverageRevenue", AverageRevenue);
    		}
    		else
    			ctx.put("AverageRevenue", 0);
		}

		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(0);
		cell.setColspan(3);
		headTable.addCell(cell);
		
		PdfPCell cell2=PDFUtils.addPDFCellWithCustomSizeBoldItalic("* If the revenue estimate for the current year is above $500,000; complete the PRACTICE MANAGEMENT SUPPLEMENT",8,false);
		cell2.setColspan(3);
		headTable.addCell(cell2);
		
		document.add(headTable);
		// document.add(table);
		return null;
	}

	public PdfPTable populateFirmInfoMain(Document document, Context ctx, PdfWriter writer)
			throws Exception {
		Object objFirm = ctx.get(PDFConstants.FIRM_FREEFORM_01);
		Map firmMap = null;
		if (objFirm != null)
			firmMap = (Map) objFirm;

//		if (firmMap != null) {
		
			String IsFirmRenderingServices = PDFUtils.getValueFromMap(firmMap,
					"IsFirmRenderingServices", true);
			ctx.put("IsFirmRenderingServices", IsFirmRenderingServices);
			
			String IsFirmsRevenueMorethan25Percent = PDFUtils.getValueFromMap(
					firmMap, "IsFirmsRevenueMorethan25Percent", true);
			String PercentOfRevenueFromLargestClient = PDFUtils
					.getValueFromMap(firmMap,
							"PercentOfRevenueFromLargestClient", false);
			String ServiceIndustryofLargestClient = PDFUtils.getValueFromMap(
					firmMap, "ServiceIndustryofLargestClient", false);
			String PercentOfRevenueFromSecondLargestClient = PDFUtils
					.getValueFromMap(firmMap,
							"PercentOfRevenueFromSecondLargestClient", false);
			String ServiceIndustryofSecondLargestClient = PDFUtils
					.getValueFromMap(firmMap,
							"ServiceIndustryofSecondLargestClient", false);

			String IsSignedEngagement = PDFUtils.getValueFromMap(firmMap,
					"IsSignedEngagement", true);
			String IsFirmAttendedRiskMagtSeminarIn3Years = PDFUtils
					.getValueFromMap(firmMap,
							"IsFirmAttendedRiskMagtSeminarIn3Years", true);
			String IsAnyFirmMeargedOrAcquired = PDFUtils.getValueFromMap(
					firmMap, "IsAnyFirmMeargedOrAcquired", true);
			String IsControlClientFunds = PDFUtils.getValueFromMap(firmMap,
					"IsControlClientFunds", true);

			String IsPublicTradedAudited = PDFUtils.getValueFromMap(firmMap,
					"IsPublicTradedAudited", true);
			String IsPublicPrivateOfferingsDone = PDFUtils.getValueFromMap(
					firmMap, "IsPublicPrivateOfferingsDone", true);
			String IsInvestmentVenture = PDFUtils.getValueFromMap(firmMap,
					"IsInvestmentVenture", true);
			String IsSyndicationManagers = PDFUtils.getValueFromMap(firmMap,
					"IsSyndicationManagers", true);
			String IsFirmRenderedAuditOrAttestService = PDFUtils
					.getValueFromMap(firmMap,
							"IsFirmRenderedAuditOrAttestService", true);
			String IsManagementServiceProvided = PDFUtils.getValueFromMap(
					firmMap, "IsManagementServiceProvided", true);
			String IsCommissionRecieved = PDFUtils.getValueFromMap(firmMap,
					"IsCommissionRecieved", true);

			String IsDebtOrEquityFinancing = PDFUtils.getValueFromMap(firmMap,
					"IsDebtOrEquityFinancing", true);
			String IsNonPublicInvestmentDone = PDFUtils.getValueFromMap(
					firmMap, "IsNonPublicInvestmentDone", true);
			String IsServedAsTrustee = PDFUtils.getValueFromMap(firmMap,
					"IsServedAsTrustee", true);
			String IsFirmRenderedServiceForAnyClient = PDFUtils
					.getValueFromMap(firmMap,
							"IsFirmRenderedServiceForAnyClient", true);

			String IsAnyRegulatoryInquiry = PDFUtils.getValueFromMap(firmMap,
					"IsAnyRegulatoryInquiry", true);
			String RegulatoryInquiryExaplain = PDFUtils.getValueFromMap(
					firmMap, "RegulatoryInquiryExaplain", false);

			String IsAwarenessOfAnyProfLiability = PDFUtils.getValueFromMap(
					firmMap, "IsAwarenessOfAnyProfLiability", true);
			ctx.put("IsAwarenessOfAnyProfLiability", IsAwarenessOfAnyProfLiability);
			String IsAwarenessOfAnyProfLiabilitySuitAgainst = PDFUtils
					.getValueFromMap(firmMap,
							"IsAwarenessOfAnyProfLiabilitySuitAgainst", true);
			ctx.put("IsAwarenessOfAnyProfLiabilitySuitAgainst", IsAwarenessOfAnyProfLiabilitySuitAgainst);
			
			String IsFirmDeclinedOtherthanNonPayment = PDFUtils
					.getValueFromMap(firmMap,
							"IsFirmDeclinedOtherthanNonPayment", true);
			String IsFirmCarryingProfLiabilityIns = PDFUtils.getValueFromMap(
					firmMap, "IsFirmCarryingProfLiabilityIns", true);
			String IsPolicyExcludesCoverage = PDFUtils.getValueFromMap(firmMap,
					"IsPolicyExcludesCoverage", true);
			String RetroFirmName = PDFUtils.getValueFromMap(firmMap,
					"RetroFirmName", false);

			String IsSuitInstituted = PDFUtils.getValueFromMap(firmMap,
					"IsSuitInstituted", true);
			String AgainstHowManyClients = PDFUtils.getValueFromMap(firmMap,
					"AgainstHowManyClients", false);

			PdfPTable q4to5table = new PdfPTable(3);
			q4to5table.setWidthPercentage(100);
			q4to5table.setSpacingBefore(10f);
			q4to5table.setSpacingAfter(0f);
			float[] width = { 2.5f, 51.5f, 21f };
			q4to5table.setWidths(width);

			PdfPCell q4SNo = PDFUtils.addPDFCellWithDefalutSize("4.");
			q4SNo.setBorder(0);
			q4to5table.addCell(q4SNo);
			PdfPCell q4label = PDFUtils
					.addPDFCellWithDefalutSize("Does your Firm or any owners, partners or officers render services or conduct any business activities under a separate entity name? ");
			q4label.setBorder(0);
			// q4label.setColspan (2);
			q4to5table.addCell(q4label);

			// PdfPCell q4part1label = PDFUtils.addPDFCellWithDefalutSize("under
			// a separate entity name? ");
			// q4part1label.setBorder(0);
			PdfPCell q4labelValue = PDFUtils
					.getYesNoImageObject(IsFirmRenderingServices);
			q4labelValue.setBorder(0);
			q4labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			q4to5table.addCell(q4labelValue);

			PdfPCell q4aSNo = PDFUtils.addPDFCellWithDefalutSize(" ");
			q4aSNo.setBorder(0);
			q4to5table.addCell(q4aSNo);
			PdfPCell q4alabel = PDFUtils
					.addPDFCellWithDefalutSizeBoldItalic("If yes, complete the SEPARATE ENTITY SUPPLEMENT for all such entities whether coverage is desired or not.");
			q4alabel.setBorder(0);
			q4alabel.setColspan (2);
			q4to5table.addCell(q4alabel);
			
			q4to5table.addCell(q4aSNo);
			PdfPCell q4blabel = PDFUtils
					.addPDFCellWithDefalutSizeItalic("Coverage may be available for such entities by endorsement to your policy subject to underwriting approval.");
			q4blabel.setBorder(0);
			q4blabel.setColspan (2);
			q4to5table.addCell(q4blabel);
			
			// q4to5table.addCell(q4part1label);
			
			q4to5table.addCell(PDFUtils.getBlankCustomSizeCell(10, 3,false));
			
			PdfPCell q5SNo = PDFUtils.addPDFCellWithDefalutSize("5.");
			q5SNo.setBorder(0);
			q4to5table.addCell(q5SNo);
			PdfPCell q5label = PDFUtils
					.addPDFCellWithDefalutSize("During each of the last 3 years has any client of the Firm represented more than 25% of the Firm’s revenue? ");
			q5label.setBorder(0);
			PdfPCell q5labelValue = PDFUtils
					.getYesNoImageObject(IsFirmsRevenueMorethan25Percent);
			q5labelValue.setBorder(0);
			q5labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			q4to5table.addCell(q5label);
			q4to5table.addCell(q5labelValue);

			if ("Yes".equals(IsFirmsRevenueMorethan25Percent)) {
				PdfPTable q5additionaltable = new PdfPTable(3);
				q5additionaltable.setWidthPercentage(100);
				float[] width1 = { 2f, 70f, 5f };
				q5additionaltable.setWidths(width1);

				PdfPCell q5aSNo = PDFUtils.addPDFCellWithDefalutSize("a.");
				q5aSNo.setBorder(0);
				Phrase phrase1=new Phrase("If Yes, please indicate percentage of revenue from largest revenue client",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
				Phrase phrase2=new Phrase(" (include client’s affiliated entities):",new Font(Font.TIMES_ROMAN,8,Font.ITALIC));
				Paragraph paragraph=new Paragraph();
				paragraph.add(phrase1);
				paragraph.add(phrase2);
				PdfPCell q5alabel = new PdfPCell(paragraph);
				q5alabel.setBorder(0);
				PdfPCell q5alabelValue = PDFUtils
						.getCustomFontValue(PercentOfRevenueFromLargestClient
								+ "%", 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.UNDERLINE);
				q5alabelValue.setBorder(0);
				q5alabelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

				q5additionaltable.addCell(q5aSNo);
				q5additionaltable.addCell(q5alabel);
				q5additionaltable.addCell(q5alabelValue);

				PdfPCell q5apartSNo = PDFUtils.addPDFCellWithDefalutSize("");
				q5apartSNo.setBorder(0);
				Phrase phrase7=new Phrase("Services rendered: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
				Phrase phrase8=new Phrase(ServiceIndustryofLargestClient,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
				Paragraph paragraph3=new Paragraph();
				paragraph3.add(phrase7);
				paragraph3.add(phrase8);
				PdfPCell q5apart2label = new PdfPCell(paragraph3);
				q5apart2label.setBorder(0);
				q5apart2label.setColspan(2);
				// PdfPCell q5apart2labelValue =
				// PDFUtils.addPDFCellWithDefalutSize(ServiceIndustryofLargestClient
				// );
				// q5apart2labelValue.setBorder(0);
				// q5apart2labelValue.setHorizontalAlignment(Element.ALIGN_LEFT);

				q5additionaltable.addCell(q5apartSNo);
				q5additionaltable.addCell(q5apart2label);
				// q5additionaltable.addCell(q5apart2labelValue);

				PdfPCell q5bSNo = PDFUtils.addPDFCellWithDefalutSize("b.");
				q5bSNo.setBorder(0);
				
				Phrase phrase3=new Phrase("Please indicate the percentage of revenue from second largest revenue client",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
				Phrase phrase4=new Phrase(" (include client’s affiliated entities):",new Font(Font.TIMES_ROMAN,8,Font.ITALIC));
				Paragraph paragraph1=new Paragraph();
				paragraph1.add(phrase3);
				paragraph1.add(phrase4);
				PdfPCell q5blabel = new PdfPCell(paragraph1);
				q5blabel.setBorder(0);
				PdfPCell q5blabelValue = PDFUtils
						.getCustomFontValue(PercentOfRevenueFromSecondLargestClient
								+ "%", 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.UNDERLINE);
				q5blabelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

				q5additionaltable.addCell(q5bSNo);
				q5additionaltable.addCell(q5blabel);
				q5additionaltable.addCell(q5blabelValue);

				PdfPCell q5bpartSNo = PDFUtils.addPDFCellWithDefalutSize("");
				q5bpartSNo.setBorder(0);
				
				Phrase phrase5=new Phrase("Services rendered: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
				Phrase phrase6=new Phrase(ServiceIndustryofSecondLargestClient,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
				Paragraph paragraph2=new Paragraph();
				paragraph2.add(phrase5);
				paragraph2.add(phrase6);
				PdfPCell q5bpart2label = new PdfPCell(paragraph2);
				q5bpart2label.setBorder(0);
				q5bpart2label.setColspan(2);
				// PdfPCell q5bpart2labelValue =
				// PDFUtils.addPDFCellWithDefalutSize(ServiceIndustryofSecondLargestClient
				// );
				// q5bpart2labelValue.setBorder(0);
				// q5bpart2labelValue.setHorizontalAlignment(Element.ALIGN_LEFT);

				q5additionaltable.addCell(q5bpartSNo);
				q5additionaltable.addCell(q5bpart2label);
				// q5additionaltable.addCell(q5bpart2labelValue);

				PdfPCell additionalCell = new PdfPCell(q5additionaltable);
				additionalCell.setColspan(3);
				additionalCell.setPaddingLeft(10f);
				additionalCell.setPaddingRight(10f);
				additionalCell.setBorder(0);
				q4to5table.addCell(additionalCell);
				// document.add(q5additionaltable);
			}
			document.add(q4to5table);

			PdfPTable q9to10table = new PdfPTable(3);
			q9to10table.setWidthPercentage(100);
			q9to10table.setSpacingBefore(10f);
			q9to10table.setSpacingAfter(0f);
			q9to10table.setWidths(width);

			PdfPCell q6SNo = PDFUtils.addPDFCellWithDefalutSize("6.");
			q6SNo.setBorder(0);

			PdfPCell q6label = PDFUtils
					.addPDFCellWithDefalutSize("Does the Firm obtain a signed engagement letter or written agreement updated annually outlining the services the Firm will perform for each of the Firm’s clients?");
			q6label.setBorder(0);
			// q6label.setColspan (2);
			q9to10table.addCell(q6SNo);
			q9to10table.addCell(q6label);

			// PdfPCell q6part1label = PDFUtils.addPDFCellWithDefalutSize("the
			// Firm will perform for each of the Firm’s clients? ");
			// q6part1label.setBorder(0);

			PdfPCell q6labelValue = PDFUtils
					.getYesNoImageObject(IsSignedEngagement);
			q6labelValue.setBorder(0);
			q6labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			// q9to10table.addCell(q6part1label);
			q9to10table.addCell(q6labelValue);

			q9to10table.addCell(PDFUtils.getBlankCustomSizeCell(10, 3,false));
			
			PdfPCell q7SNo = PDFUtils.addPDFCellWithDefalutSize("7.");
			q7SNo.setBorder(0);
			PdfPCell q7label = PDFUtils
					.addPDFCellWithDefalutSize("Has any member of the Firm attended a Risk Management seminar in the last 3 years?");
			q7label.setBorder(0);
			PdfPCell q7labelValue = PDFUtils
					.getYesNoImageObject(IsFirmAttendedRiskMagtSeminarIn3Years);
			q7labelValue.setBorder(0);
			q7labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			q9to10table.addCell(q7SNo);
			q9to10table.addCell(q7label);
			q9to10table.addCell(q7labelValue);

			q9to10table.addCell(PDFUtils.getBlankCustomSizeCell(10, 3,false));
			
			PdfPCell q8SNo = PDFUtils.addPDFCellWithDefalutSize("8.");
			q8SNo.setBorder(0);
			q9to10table.addCell(q8SNo);
			PdfPCell q8label = PDFUtils
					.addPDFCellWithDefalutSize("Has your Firm merged with or acquired the business of any sole practitioner, accounting Firm or other business entity?");
			q8label.setBorder(0);
			// q8label.setColspan (2);
			q9to10table.addCell(q8label);

			// PdfPCell q8part1label = PDFUtils.addPDFCellWithDefalutSize("the
			// Firm will perform for each of the Firm’s clients? ");
			// q8part1label.setBorder(0);
			PdfPCell q8labelValue = PDFUtils
					.getYesNoImageObject(IsAnyFirmMeargedOrAcquired);
			q8labelValue.setBorder(0);
			q8labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			// q9to10table.addCell(q8part1label);
			q9to10table.addCell(q8labelValue);
			
			q9to10table.addCell(PDFUtils.getBlankCustomSizeCell(10, 3,false));
			
			PdfPCell q9SNo = PDFUtils.addPDFCellWithDefalutSize("9.");
			q9SNo.setBorder(0);
			PdfPCell q9label = PDFUtils
					.addPDFCellWithDefalutSize("Does the Firm control or disburse client funds?");
			q9label.setBorder(0);
			PdfPCell q9labelValue = PDFUtils
					.getYesNoImageObject(IsControlClientFunds);
			q9labelValue.setBorder(0);
			q9labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			q9to10table.addCell(q9SNo);
			q9to10table.addCell(q9label);
			q9to10table.addCell(q9labelValue);
			
			PdfPCell q9alabel = PDFUtils
					.addPDFCellWithCustomSizeBoldItalic("If Yes, please complete the BUSINESS MANAGEMENT SUPPLEMENT",10,false);
			q9alabel.setColspan(2);

			q9to10table.addCell(PDFUtils.addPDFCellValueWithDefalutSize(" "));
			q9to10table.addCell(q9alabel);
	
	
			q9to10table.addCell(PDFUtils.getBlankCustomSizeCell(10, 3,false));
			
			PdfPCell q10SNo = PDFUtils.addPDFCellWithDefalutSize("10.");
			q10SNo.setBorder(0);
			q9to10table.addCell(q10SNo);
			PdfPCell q10label = PDFUtils
					.addPDFCellWithDefalutSize("Has the Firm or its predecessors within the past five years:");
			q10label.setBorder(0);
			q10label.setColspan(2);
			q9to10table.addCell(q10label);

			PdfPTable pdfPTable = new PdfPTable(3);
			float[] width1 = { 2f, 52f, 21f };
			pdfPTable.setWidths(width1);

			PdfPCell qa10SNo = PDFUtils.addPDFCellWithDefalutSize("a.");
			qa10SNo.setBorder(0);
			Phrase phrase1=new Phrase("Performed audits for SEC – regulated entities",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
			Phrase phrase2=new Phrase(" (other than for broker-dealers)",new Font(Font.TIMES_ROMAN,8,Font.ITALIC));
			Phrase phrase3=new Phrase("?",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
			Paragraph paragraph=new Paragraph();
			paragraph.add(phrase1);
			paragraph.add(phrase2);
			paragraph.add(phrase3);
			PdfPCell a_10_label = new PdfPCell(paragraph);
			a_10_label.setBorder(0);
			PdfPCell a_10_labelValue = PDFUtils
					.getYesNoImageObject(IsPublicTradedAudited);
			a_10_labelValue.setBorder(0);
			a_10_labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			pdfPTable.addCell(qa10SNo);
			pdfPTable.addCell(a_10_label);
			pdfPTable.addCell(a_10_labelValue);

			PdfPCell qb10SNo = PDFUtils.addPDFCellWithDefalutSize("b.");
			qb10SNo.setBorder(0);
			pdfPTable.addCell(qb10SNo);
			PdfPCell b_10_label = PDFUtils
					.addPDFCellWithDefalutSize("Received commissions, referral fees, reciprocity or other inducements arising from the sale, promotion or recommendation of securities, insurance products, real estate or other investments?");
			b_10_label.setBorder(0);
			// b_10_label.setColspan (2);
			pdfPTable.addCell(b_10_label);

			// PdfPCell b_10_part1label =
			// PDFUtils.addPDFCellWithDefalutSize("promotion or recommendation
			// of securities, insurance products, real estate or other
			// investments? ");
			// b_10_part1label.setBorder(0);
			PdfPCell b_10_labelValue = PDFUtils
					.getYesNoImageObject(IsPublicPrivateOfferingsDone);
			b_10_labelValue.setBorder(0);
			b_10_labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			// q9to10table.addCell(b_10_part1label);
			pdfPTable.addCell(b_10_labelValue);

			PdfPCell qc10SNo = PDFUtils.addPDFCellWithDefalutSize("c.");
			qc10SNo.setBorder(0);
			pdfPTable.addCell(qc10SNo);
			PdfPCell c_10_label = PDFUtils
					.addPDFCellWithDefalutSize("Organized, promoted, or referred others to invest in investment ventures?");
			c_10_label.setBorder(0);
			PdfPCell c_10_labelValue = PDFUtils
					.getYesNoImageObject(IsInvestmentVenture);
			c_10_labelValue.setBorder(0);
			c_10_labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			pdfPTable.addCell(c_10_label);
			pdfPTable.addCell(c_10_labelValue);

			PdfPCell qd10SNo = PDFUtils.addPDFCellWithDefalutSize("d.");
			qd10SNo.setBorder(0);
			pdfPTable.addCell(qd10SNo);
			PdfPCell d_10_label = PDFUtils
					.addPDFCellWithDefalutSize("Provided services to investment funds, hedge funds, mutual funds or syndication managers?");
			d_10_label.setBorder(0);
			PdfPCell d_10_labelValue = PDFUtils
					.getYesNoImageObject(IsSyndicationManagers);
			d_10_labelValue.setBorder(0);
			d_10_labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			pdfPTable.addCell(d_10_label);
			pdfPTable.addCell(d_10_labelValue);

			PdfPCell qe10SNo = PDFUtils.addPDFCellWithDefalutSize("e.");
			qe10SNo.setBorder(0);
			pdfPTable.addCell(qe10SNo);
			PdfPCell e_10_label = PDFUtils
					.addPDFCellWithDefalutSize("Rendered services, other than tax services, for any client in which Firm personnel, or a relative of Firm personnel owned or received an equity interest in excess of 10%?");
			e_10_label.setBorder(0);
			// e_10_label.setColspan (2);
			pdfPTable.addCell(e_10_label);

			// PdfPCell e_10_part1label =
			// PDFUtils.addPDFCellWithDefalutSize("personnel owned or received
			// an equity interest in excess of 10%? ");
			// e_10_part1label.setBorder(0);
			PdfPCell e_10_labelValue = PDFUtils
					.getYesNoImageObject(IsFirmRenderedAuditOrAttestService);
			e_10_labelValue.setBorder(0);
			e_10_labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			// q9to10table.addCell(e_10_part1label);
			pdfPTable.addCell(e_10_labelValue);

			PdfPCell qf10SNo = PDFUtils.addPDFCellWithDefalutSize("f.");
			qf10SNo.setBorder(0);
			pdfPTable.addCell(qf10SNo);
			PdfPCell f_10_label = PDFUtils
					.addPDFCellWithDefalutSize("Provided management services for investment ventures ?");
			f_10_label.setBorder(0);
			PdfPCell f_10_labelValue = PDFUtils
					.getYesNoImageObject(IsManagementServiceProvided);
			f_10_labelValue.setBorder(0);
			f_10_labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			pdfPTable.addCell(f_10_label);
			pdfPTable.addCell(f_10_labelValue);

			PdfPCell qg10SNo = PDFUtils.addPDFCellWithDefalutSize("g.");
			qg10SNo.setBorder(0);
			pdfPTable.addCell(qg10SNo);
			PdfPCell g_10_label = PDFUtils
					.addPDFCellWithDefalutSize("Performed services or consented to the use of your work product in connection with public or private offerings of securities, real estate or other investments?");
			g_10_label.setBorder(0);
			// g_10_label.setColspan (2);
			pdfPTable.addCell(g_10_label);

			// PdfPCell g_10_part1label = PDFUtils.addPDFCellWithDefalutSize("of
			// securities, real estate or other investments?");
			// g_10_part1label.setBorder(0);
			PdfPCell g_10_labelValue = PDFUtils
					.getYesNoImageObject(IsCommissionRecieved);
			g_10_labelValue.setBorder(0);
			g_10_labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			// q9to10table.addCell(g_10_part1label);
			pdfPTable.addCell(g_10_labelValue);

			PdfPCell pdfPCell1 = new PdfPCell(pdfPTable);
			pdfPCell1.setBorder(0);
			pdfPCell1.setPaddingLeft(10);
			pdfPCell1.setColspan(3);
			q9to10table.addCell(pdfPCell1);
			
			q9to10table.addCell(PDFUtils.getBlankCustomSizeCell(10, 3,false));
			
			if ("Yes".equals(IsCommissionRecieved)) {
				PdfPTable table = populatePublicPrivateOffering(document, ctx);
				if (table != null) {
					// PdfPTable q10_g_headertable = new PdfPTable(2);
					// q10_g_headertable.setWidthPercentage(100);

					PdfPCell g_10_yes_label = new PdfPCell(
							new Paragraph(
									"If yes to 10g, please provide information below for each offering of securities, real estate or other investments within the past 5 years including non-regulated offerings:",
									new Font(Font.TIMES_ROMAN, 10, Font.ITALIC)));
					g_10_yes_label.setBorder(0);
					g_10_yes_label.setColspan(3);
					g_10_yes_label.setPaddingLeft(10);
					// q10_g_headertable.addCell(g_10_yes_label);
					q9to10table.addCell(g_10_yes_label);
					// PdfPCell g_10_yes_part1label =
					// PDFUtils.addPDFCellWithDefalutSize("years including
					// non-regulated offerings: ");
					// g_10_yes_part1label.setBorder(0);
					// q10_g_headertable.addCell(g_10_yes_part1label);

					PdfPCell cell = new PdfPCell(table);
					cell.setBorder(0);
					cell.setColspan(3);
					q9to10table.addCell(cell);

					// PdfPTable q10_g_footertable = new PdfPTable(2);
					// q10_g_footertable.setWidthPercentage(100);

					PdfPCell g_10_yes_footer_label = PDFUtils
							.addPDFCellWithDefalutSize("*If public, indicate primary or secondary. If private, indicate partnership, trust or stock sale. ");
					g_10_yes_footer_label.setBorder(0);
					g_10_yes_footer_label.setColspan(3);
					// q10_g_footertable.addCell(g_10_yes_footer_label);
					q9to10table.addCell(g_10_yes_footer_label);
					// document.add(q10_g_headertable);
					// document.add(table);
					// document.add(q10_g_footertable);
				}
			}

			document.add(q9to10table);

			PdfPTable q11to12table = new PdfPTable(3);
			q11to12table.setWidthPercentage(100);
			q11to12table.setWidths(width);
			q11to12table.setSpacingBefore(10f);
			q11to12table.setSpacingAfter(0f);

			PdfPCell q11SNo = PDFUtils.addPDFCellWithDefalutSize("11.");
			q11SNo.setBorder(0);
			q11to12table.addCell(q11SNo);
			PdfPCell q11label = PDFUtils
					.addPDFCellWithDefalutSize("Has the Firm or its predecessor Firms within the past five years, arranged debt or equity financing, acted as a business broker, underwritten the offering of public or private securities, or prepared fairness opinions?");
			q11label.setBorder(0);
			// q11label.setColspan (2);
			q11to12table.addCell(q11label);

			// PdfPCell q11part1label =
			// PDFUtils.addPDFCellWithDefalutSize("broker, underwritten the
			// offering of public or private securities, or prepared fairness
			// opinions?");
			// q11part1label.setBorder(0);
			PdfPCell q11labelValue = PDFUtils
					.getYesNoImageObject(IsDebtOrEquityFinancing);
			q11labelValue.setBorder(0);
			q11labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			// q11to13table.addCell(q11part1label);
			q11to12table.addCell(q11labelValue);

			q11to12table.addCell(PDFUtils.getBlankCustomSizeCell(10, 3,false));
			
			PdfPCell q12SNo = PDFUtils.addPDFCellWithDefalutSize("12.");
			q12SNo.setBorder(0);
			q11to12table.addCell(q12SNo);

			PdfPCell q12label = PDFUtils
					.addPDFCellWithDefalutSize("Has the Firm, its personnel or its predecessors within the past five years, invested in any non-public investment venture in which a client has also invested?");
			q12label.setBorder(0);
			// q12label.setColspan (2);
			q11to12table.addCell(q12label);

			// PdfPCell q12part1label =
			// PDFUtils.addPDFCellWithDefalutSize("venture in which a client has
			// also invested?");
			// q12part1label.setBorder(0);
			PdfPCell q12labelValue = PDFUtils
					.getYesNoImageObject(IsNonPublicInvestmentDone);
			q12labelValue.setBorder(0);
			q12labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			// q11to13table.addCell(q12part1label);
			q11to12table.addCell(q12labelValue);
			
			document.add(q11to12table);
			
			PdfPTable q13table = new PdfPTable(3);
			q13table.setWidthPercentage(100);
			q13table.setWidths(width);
			q13table.setSpacingBefore(10f);
			q13table.setSpacingAfter(0f);
			
			PdfPCell q13SNo = PDFUtils.addPDFCellWithDefalutSize("13.");
			q13SNo.setBorder(0);
			q13table.addCell(q13SNo);

			PdfPCell q13label = PDFUtils
					.addPDFCellWithDefalutSize("Has the Firm, its personnel or its predecessor Firms within the past five years, served as a trustee, co-trustee, executor,  administrator or personal representative for any client?");
			q13label.setBorder(0);
			// q13label.setColspan (2);
			q13table.addCell(q13label);

			// PdfPCell q13part1label =
			// PDFUtils.addPDFCellWithDefalutSize("administrator or personal
			// representative for any client?");
			// q13part1label.setBorder(0);
			PdfPCell q13labelValue = PDFUtils
					.getYesNoImageObject(IsServedAsTrustee);
			q13labelValue.setBorder(0);
			q13labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			// q11to13table.addCell(q13part1label);
			q13table.addCell(q13labelValue);

			if ("Yes".equals(IsServedAsTrustee)) {
				PdfPTable table1 = populateTrustee(document, ctx);
				if (table1 != null) {
					// PdfPTable q13_headertable = new PdfPTable(2);
					// q13_headertable.setWidthPercentage(100);
					//
					//q11to13table.addCell(PDFUtils.getBlankCustomSizeCell(10, 3,false));
					
					PdfPCell q13_header_label = PDFUtils
							.addPDFCellWithDefalutSizeItalic("If answer to question  13 is yes, please complete the following information for each trust or estate below:");
					q13_header_label.setBorder(0);
					q13_header_label.setColspan(3);
					q13_header_label.setPaddingLeft(10f);
					// q13_headertable.addCell(q13_header_label);
					q13table.addCell(q13_header_label);

					PdfPCell cell = new PdfPCell(table1);
					cell.setBorder(0);
					cell.setColspan(3);
					q13table.addCell(cell);

					PdfPCell q13_footer_label = new PdfPCell(
							new Paragraph(
									"**Beneficiary interest means any personal interest you or your relatives might have as heir or beneficiary of the trust or estate funds, other than customary fees as trustee, administrator, executor or personal representative to which you are entitled.",
									new Font(Font.TIMES_ROMAN, 9, Font.NORMAL)));
					q13_footer_label.setBorder(0);
					q13_footer_label.setColspan(3);
					q13_footer_label.setPaddingLeft(10f);
					q13table.addCell(q13_footer_label);
					
				}
			}
			float availableSpace = (writer.getVerticalPosition(false)- document.bottomMargin()-15);
			q13table.setTotalWidth(500f);
			float height=0;
			for(int i=0;i<q13table.getRows().size();i++){
				height +=q13table.getRowHeight(i);
			}
			if(availableSpace<height){
				document.newPage();
			}
			document.add(q13table);

			PdfPTable q14table = new PdfPTable(3);
			q14table.setWidthPercentage(100);
			q14table.setWidths(width);
			q14table.setSpacingBefore(10f);
			q14table.setSpacingAfter(0f);
			
			PdfPCell q14SNo = PDFUtils.addPDFCellWithDefalutSize("14.");
			q14SNo.setBorder(0);
			q14table.addCell(q14SNo);

			PdfPCell q14label = PDFUtils
					.addPDFCellWithDefalutSize("Has your Firm or its personnel served as an officer, director, partner, manager or other member of a client’s governing body?");
			q14label.setBorder(0);
//			q14label.setColspan(2);
			q14table.addCell(q14label);

//			PdfPCell q14part1label = PDFUtils
//					.addPDFCellWithDefalutSize("or other member of a client’s governing body?");
//			q14part1label.setBorder(0);
			PdfPCell q14labelValue = PDFUtils
					.getYesNoImageObject(IsFirmRenderedServiceForAnyClient);
			q14labelValue.setBorder(0);
			q14labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

//			q14table.addCell(q14part1label);
			q14table.addCell(q14labelValue);

			if ("Yes".equals(IsFirmRenderedServiceForAnyClient)) {
				PdfPTable table2 = populateFirmRendered(document, ctx);
				if (table2 != null) {
//					PdfPTable q14_headertable = new PdfPTable(2);
//					q14_headertable.setWidthPercentage(100);

					PdfPCell q14_header_label = PDFUtils
							.addPDFCellWithDefalutSizeItalic("If yes to Question 14, please complete the information below:");
					q14_header_label.setBorder(0);
					q14_header_label.setColspan(3);
//					q14_headertable.addCell(q14_header_label);

//					document.add(q14_headertable);
//					document.add(table2);
					q14table.addCell(q14_header_label);
					PdfPCell cell = new PdfPCell(table2);
					cell.setBorder(0);
					cell.setColspan(3);
					q14table.addCell(cell);

				}
			}
			availableSpace = (writer.getVerticalPosition(false)- document.bottomMargin()-15);
			q14table.setTotalWidth(500f);
			height=0;
			for(int i=0;i<q14table.getRows().size();i++){
				height +=q14table.getRowHeight(i);
			}
			if(availableSpace<height){
				document.newPage();
			}
			document.add(q14table);
			
			PdfPTable q15table = new PdfPTable(3);
			q15table.setWidthPercentage(100);
			q15table.setWidths(width);
			q15table.setSpacingBefore(10f);
			q15table.setSpacingAfter(0f);
			
			PdfPCell cellHeader =PDFUtils.addHeader("NATURE OF PRACTICE");
			cellHeader.setColspan(3);
			q15table.addCell(cellHeader);
			
			q15table.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q15SNo = PDFUtils.addPDFCellWithDefalutSize("15.");
			q15SNo.setBorder(0);
			q15table.addCell(q15SNo);

			PdfPCell q15label = PDFUtils
					.addPDFCellWithDefalutSize("Provide the percentage of gross annual revenue derived from the areas of practice listed below:");
			q15label.setBorder(0);
			q15label.setColspan (2);
			q15table.addCell(q15label);
			
			q15table.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			//document.add(q15table);
			
			//PDFUtils.addSpace(document);
			
			PdfPTable tableAop=populateAOP(document, ctx);
			PdfPCell cellAop=new PdfPCell(tableAop);
			cellAop.setColspan(3);
			cellAop.setBorder(0);
			q15table.addCell(cellAop);
			
			availableSpace = (writer.getVerticalPosition(false)- document.bottomMargin()-15);
			q15table.setTotalWidth(500f);
			height=0;
			for(int i=0;i<q15table.getRows().size();i++){
				height +=q15table.getRowHeight(i);
			}
			if(availableSpace<height){
				document.newPage();
			}
			
			document.add(q15table);
			
			PdfPTable q16table = new PdfPTable(3);
			q16table.setWidthPercentage(100);
			q16table.setWidths(width);
			q16table.setSpacingBefore(10f);
			q16table.setSpacingAfter(0f);

			PdfPCell q16SNo = PDFUtils.addPDFCellWithDefalutSize("16.");
			q16SNo.setBorder(0);
			q16table.addCell(q16SNo);

			PdfPCell q16label = PDFUtils
					.addPDFCellWithDefalutSize("Within the past 5 years, has your Firm instituted suit to collect fees, including in small claims court?");
			q16label.setBorder(0);
			q16table.addCell(q16label);

			PdfPCell q16labelValue = PDFUtils
					.getYesNoImageObject(IsSuitInstituted);
			q16labelValue.setBorder(0);
			q16labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			q16table.addCell(q16labelValue);

			if ("Yes".equals(IsFirmDeclinedOtherthanNonPayment)) {
				PdfPCell blank=PDFUtils.addPDFCellValueWithDefalutSize(" ");
				PdfPCell q16_yes_label = PDFUtils
						.addPDFCellWithDefalutSize("If yes, against how many clients?  ");
				q16_yes_label.setBorder(0);
				q16_yes_label.setPaddingLeft(10f);
				PdfPCell q16_yes_labelValue;
				if(!AgainstHowManyClients.equals("")){
					q16_yes_labelValue = PDFUtils
					.getCustomFontValue(PDFGenerator.numberFormat.format(new Double(AgainstHowManyClients)), 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.UNDERLINE);					
				}else{
					q16_yes_labelValue = PDFUtils
					.getCustomFontValue(AgainstHowManyClients, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.UNDERLINE);
				}
				q16_yes_labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

				q16table.addCell(blank);
				q16table.addCell(q16_yes_label);
				q16table.addCell(q16_yes_labelValue);
			}

			document.add(q16table);

			PdfPTable q17table = new PdfPTable(3);
			q17table.setWidthPercentage(100);
			q17table.setWidths(width);
			q17table.setSpacingBefore(10f);
			q17table.setSpacingAfter(0f);

			PdfPCell q17SNo = PDFUtils.addPDFCellWithDefalutSize("17.");
			q17SNo.setBorder(0);
			q17table.addCell(q17SNo);
			
			PdfPCell q17label = PDFUtils
					.addPDFCellWithDefalutSize("After inquiry of all owners, partners, officers and other professional staff of the Firm, within the past 5 years have any past or present personnel:");
			q17label.setBorder(0);
			q17label.setColspan(2);
			q17table.addCell(q17label);

			PdfPTable q17SubTable = new PdfPTable(3);
			q17SubTable.setWidthPercentage(100);
			q17SubTable.setWidths(width1);
			
			PdfPCell q17aSNo = PDFUtils.addPDFCellWithDefalutSize("a.");
			q17aSNo.setBorder(0);
			q17SubTable.addCell(q17aSNo);
			
			PdfPCell q17alabel = PDFUtils
					.addPDFCellWithDefalutSize("been the subject of any regulatory investigation or inquiry; suspended from practice; or charged, indicted, plead no contest (“nolo contendere”), plead guilty or convicted of any felony charge?");
			q17alabel.setBorder(0);
			q17SubTable.addCell(q17alabel);

			PdfPCell q17alabelValue = PDFUtils
					.getYesNoImageObject(IsAnyRegulatoryInquiry);
			q17alabelValue.setBorder(0);
			q17alabelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			q17SubTable.addCell(q17alabelValue);

			if ("Yes".equals(IsAnyRegulatoryInquiry)) {
				PdfPCell blank=PDFUtils.addPDFCellValueWithDefalutSize(" ");
				Phrase phrase10=new Phrase("If yes, please provide details and dates:  ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
				Phrase phrase11=new Phrase(RegulatoryInquiryExaplain,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
				Paragraph paragraph10=new Paragraph();
				paragraph10.add(phrase10);
				paragraph10.add(phrase11);
				PdfPCell q17a_yes_label = new PdfPCell(paragraph10);
				q17a_yes_label.setBorder(0);
				q17a_yes_label.setColspan(2);
				q17SubTable.addCell(blank);
				q17SubTable.addCell(q17a_yes_label);
			}

			PdfPCell q17bSNo = PDFUtils.addPDFCellWithDefalutSize("b.");
			q17bSNo.setBorder(0);
			q17SubTable.addCell(q17bSNo);
			
			PdfPCell q17blabel = PDFUtils
					.addPDFCellWithDefalutSize("become aware of any accountants professional liability claims made against the Firm, Firm affiliates, their personnel, or the Firm’s predecessors in business?");
			q17blabel.setBorder(0);
			q17SubTable.addCell(q17blabel);

			PdfPCell q17blabelValue = PDFUtils
					.getYesNoImageObject(IsAwarenessOfAnyProfLiability);
			q17blabelValue.setBorder(0);
			q17blabelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			q17SubTable.addCell(q17blabelValue);

			PdfPCell q17cSNo = PDFUtils.addPDFCellWithDefalutSize("c.");
			q17cSNo.setBorder(0);
			q17SubTable.addCell(q17cSNo);
			
			PdfPCell q17clabel = PDFUtils
					.addPDFCellWithDefalutSize("become aware of any act, omission, or  fee dispute which could be the basis of a claim against the Firm,  its personnel, or the Firm predecessors in business?");
			q17clabel.setBorder(0);
			q17SubTable.addCell(q17clabel);

			PdfPCell q17clabelValue = PDFUtils
					.getYesNoImageObject(IsAwarenessOfAnyProfLiabilitySuitAgainst);
			q17clabelValue.setBorder(0);
			q17clabelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

			q17SubTable.addCell(q17clabelValue);

			PdfPCell q17NoteSNo = PDFUtils.addPDFCellWithDefalutSize(" ");
			q17NoteSNo.setBorder(0);
			q17SubTable.addCell(q17NoteSNo);
			
			PdfPCell q17Notelabel = PDFUtils
					.addPDFCellWithDefalutSizeBoldItalic("If yes to 17 b or c above, please complete the CLAIM SUPPLEMENT for each claim or potential claim");
			q17Notelabel.setBorder(0);
			q17Notelabel.setColspan(2);
			q17SubTable.addCell(q17Notelabel);

			PdfPCell pdfPCell2 = new PdfPCell(q17SubTable);
			pdfPCell2.setBorder(0);
			pdfPCell2.setPaddingLeft(10);
			pdfPCell2.setColspan(3);
			
			q17table.addCell(pdfPCell2);
			
			document.add(q17table);

			PDFUtils.addHeader(document, "NOTE: THE POLICY FOR WHICH THIS APPLICATION IS BEING MADE SHALL NOT APPLY TO ANY INCIDENTS OR CLAIMS DETAILED OR WHICH SHOULD HAVE BEEN DETAILED IN THE QUESTION 16 a, b or c ABOVE.");
			
			PdfPTable q18table = new PdfPTable(3);
			q18table.setWidthPercentage(100);
			q18table.setWidths(width);
			q18table.setSpacingBefore(10f);
			q18table.setSpacingAfter(0f);

			PdfPCell q18SNo = PDFUtils.addPDFCellWithDefalutSize("18.");
			q18SNo.setBorder(0);
			q18table.addCell(q18SNo);
			
			PdfPCell q18label = PDFUtils
					.addPDFCellWithDefalutSize("Within the past five years has the Firm or their personnel been declined, canceled, or non-renewed for professional liability insurance for any reason?  (Not applicable to Missouri)");
			q18label.setBorder(0);
//			q18label.setColspan(2);
			q18table.addCell(q18label);

//			PdfPCell q18part1label = PDFUtils
//					.addPDFCellWithDefalutSize("non-renewed for professional liability insurance for any reason?  (Not applicable to Missouri)");
//			q18part1label.setBorder(0);
			PdfPCell q18labelValue = PDFUtils
					.getYesNoImageObject(IsFirmDeclinedOtherthanNonPayment);
			q18labelValue.setBorder(0);
			q18labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

//			q18table.addCell(q18part1label);
			q18table.addCell(q18labelValue);
			
			q18table.addCell(PDFUtils.getBlankCustomSizeCell(10, 3,false));
			
			if ("Yes".equals(IsFirmDeclinedOtherthanNonPayment)) {
				PdfPCell blank=PDFUtils.addPDFCellValueWithDefalutSize(" ");
				Phrase phrase20=new Phrase("If yes, please provide reasons and dates:  " ,new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
				Phrase phrase21=new Phrase(RegulatoryInquiryExaplain,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
				Paragraph paragraph20=new Paragraph();
				paragraph20.add(phrase20);
				paragraph20.add(phrase21);
				PdfPCell q18_yes_label = new PdfPCell(paragraph20);
				q18_yes_label.setBorder(0);
				q18_yes_label.setColspan(2);
//				PdfPCell q18_yes_labelValue = PDFUtils
//						.addPDFCellWithDefalutSize(RegulatoryInquiryExaplain);
//				q18_yes_labelValue.setBorder(0);

				q18table.addCell(blank);
				q18table.addCell(q18_yes_label);
//				q18table.addCell(q18_yes_labelValue);
			}

			document.add(q18table);

			//PDFUtils.addSpace(document);
			
			PdfPTable q19table = new PdfPTable(3);
			q19table.setWidthPercentage(100);
			q19table.setWidths(width);
			q19table.setSpacingBefore(10f);
			q19table.setSpacingAfter(0f);
			
			PdfPCell priorHeader =PDFUtils.addHeader("PRIOR INSURANCE INFORMATION");
			priorHeader.setColspan(3);
			q19table.addCell(priorHeader);
			
			q19table.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
			
			PdfPCell q19SNo = PDFUtils.addPDFCellWithDefalutSize("19.");
			q19SNo.setBorder(0);
			q19table.addCell(q19SNo );
			
			PdfPCell q19label = PDFUtils
					.addPDFCellWithDefalutSize("Does your Firm currently carry accountants professional liability insurance?");
			q19label.setBorder(0);
			PdfPCell q19labelValue = PDFUtils
					.getYesNoImageObject(IsFirmCarryingProfLiabilityIns);
			q19labelValue.setBorder(0);
			q19labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
			q19table.addCell(q19label);
			q19table.addCell(q19labelValue);

			
			PdfPCell blank = PDFUtils.addPDFCellWithDefalutSize(" ");
			blank.setBorder(0);
			
			if ("Yes".equals(IsFirmCarryingProfLiabilityIns)) {
				// PdfPTable q19_yes_headertable = new PdfPTable(2);
				// q19_yes_headertable.setWidthPercentage(100);
	
				q19table.addCell(blank);
				PdfPCell q19_yes_label = PDFUtils
						.addPDFCellWithDefalutSizeItalic("If yes, provide the information requested below:");
				q19_yes_label.setBorder(0);
				q19_yes_label.setColspan(2);
				q19table.addCell(q19_yes_label);
	
				// document.add(q19_yes_headertable);
	
				// PdfPTable proInstable = new PdfPTable(4);
	
				Object objProIns = ctx
						.get(PDFConstants.PROFESSIONALLIABILITYINS_FREEFORM_01);
				Map proInsMap = null;
				if (objProIns != null)
					proInsMap = (Map) objProIns;
	
				Object objLimitDeductibleProIns = ctx
						.get(PDFConstants.LIMITDEDUCTIBLEPROFESSIONALLIABILITYINS_FREEFORM_01);
				Map limitDeductibleProInsMap = null;
				if (objLimitDeductibleProIns != null)
					limitDeductibleProInsMap = (Map) objLimitDeductibleProIns;
	
				// if (proInsMap != null) {
	
				String InsuranceCompanyNamePross = PDFUtils.getValueFromMap(
						proInsMap, "InsuranceCompanyNamePross", false);
				String PolicyExpirationDatePross = "";
				if (proInsMap != null)
					PolicyExpirationDatePross = PDFUtils
							.getFormattedDateFromObject(proInsMap
									.get("PolicyExpirationDatePross"));
	
				String LimitAmount = PDFUtils.getValueFromMap(
						limitDeductibleProInsMap, "LimitAmount", false);
				String DeductibleAmount = PDFUtils.getValueFromMap(
						limitDeductibleProInsMap, "DeductibleAmount", false);
	
				String ProInsPremium = PDFUtils.getValueFromMap(proInsMap,
						"ProInsPremium", false);
				String IsPriorActDateFull = PDFUtils.getValueFromMap(proInsMap,
						"IsPriorActDateFull", false);
				String PriorActDatePross = "";
				if (proInsMap != null)
					PriorActDatePross = PDFUtils
							.getFormattedDateFromObject(proInsMap
									.get("PriorActDatePross"));
	
				String FirmYear = PDFUtils.getValueFromMap(proInsMap, "FirmYear",
						false);
	
				q19table.addCell(blank);
	
				PdfPTable pdfPTableInsur = new PdfPTable(4);
				pdfPTableInsur.setWidthPercentage(100);
				float[] widthTableInsur = { 1.5f, 1.0f, 0.75f, 0.75f };
				pdfPTableInsur.setWidths(widthTableInsur);
	
				Phrase phrasecellInsurance1 = new Phrase(
						"Insurance Company(not broker): ", new Font(
								Font.TIMES_ROMAN, 10, Font.NORMAL));
				Phrase phrasecellInsurance2 = new Phrase(InsuranceCompanyNamePross,
						new Font(Font.TIMES_ROMAN, 10, Font.BOLD, new Color(0.2f,
								0.2f, 0.2f)));
				Paragraph paragraphcelInsurancel = new Paragraph();
				paragraphcelInsurancel.add(phrasecellInsurance1);
				paragraphcelInsurancel.add(phrasecellInsurance2);
				PdfPCell InsuranceCompanyKeyLabel = new PdfPCell(
						paragraphcelInsurancel);
				InsuranceCompanyKeyLabel.setBorder(0);
				InsuranceCompanyKeyLabel.setColspan(2);
	
				pdfPTableInsur.addCell(InsuranceCompanyKeyLabel);
	
				PdfPCell PolicyExpirationDateProssLabel = PDFUtils
						.addPDFCellWithDefalutSize("Policy Expiration Date: ");
				PolicyExpirationDateProssLabel.setBorder(0);
				PdfPCell PolicyExpirationDateProssValue = PDFUtils
						.getCustomFontValue(PolicyExpirationDatePross, 10, false,
								new Color(0.2f, 0.2f, 0.2f), Font.TIMES_ROMAN,
								Font.BOLD);
				PolicyExpirationDateProssValue.setBorder(0);
	
				pdfPTableInsur.addCell(PolicyExpirationDateProssLabel);
				pdfPTableInsur.addCell(PolicyExpirationDateProssValue);
	
				pdfPTableInsur.addCell(blank);
				pdfPTableInsur.addCell(blank);
				pdfPTableInsur.addCell(blank);
				PdfPCell cell = PDFUtils.getCustomFontValue("MM/DD/YYYY", 8, false,
						new Color(0.2f, 0.2f, 0.2f), Font.TIMES_ROMAN, Font.ITALIC);
				cell.setBorder(0);
				pdfPTableInsur.addCell(cell);
	
				PdfPCell cellInsur = new PdfPCell(pdfPTableInsur);
				cellInsur.setBorder(0);
				cellInsur.setColspan(2);
				q19table.addCell(cellInsur);
	
				q19table.addCell(blank);
	
				PdfPTable proInstable1 = new PdfPTable(3);
				proInstable1.setWidthPercentage(100);
				float[] width12={1.50f,1.0f,1.50f};
				proInstable1.setWidths(width12);
				
				Phrase phrasecell1 = new Phrase("Limits of Liability: ", new Font(
						Font.TIMES_ROMAN, 10, Font.NORMAL));
				Phrase phrasecell2 = new Phrase("$" + LimitAmount, new Font(
						Font.TIMES_ROMAN, 10, Font.BOLD,
						new Color(0.2f, 0.2f, 0.2f)));
				Paragraph paragraphcell = new Paragraph();
				paragraphcell.add(phrasecell1);
				paragraphcell.add(phrasecell2);
				PdfPCell LimitAmountLabel = new PdfPCell(paragraphcell);
				LimitAmountLabel.setBorder(0);
	
				proInstable1.addCell(LimitAmountLabel);
	
				Phrase phraseDeductibleAmountLabel = new Phrase("Deductible: ",
						new Font(Font.TIMES_ROMAN, 10, Font.NORMAL));
				Phrase phraseDeductibleAmountLabel1 = new Phrase("$"
						+ DeductibleAmount, new Font(Font.TIMES_ROMAN, 10,
						Font.BOLD, new Color(0.2f, 0.2f, 0.2f)));
				
				Paragraph paragraphDeductibleAmountLabel = new Paragraph();
				paragraphDeductibleAmountLabel.add(phraseDeductibleAmountLabel);
				paragraphDeductibleAmountLabel.add(phraseDeductibleAmountLabel1);
				PdfPCell DeductibleAmountLabel = new PdfPCell(
						paragraphDeductibleAmountLabel);
				DeductibleAmountLabel.setBorder(0);
	
				proInstable1.addCell(DeductibleAmountLabel);
	
				Phrase phraseProInsPremiumLabel = new Phrase("Premium: ", new Font(
						Font.TIMES_ROMAN, 10, Font.NORMAL));
				Phrase phraseProInsPremiumLabel1;
				if (!ProInsPremium.equals("")) {
					phraseProInsPremiumLabel1 = new Phrase(PDFGenerator.formatter
							.format(new Double(ProInsPremium)), new Font(
							Font.TIMES_ROMAN, 10, Font.BOLD, new Color(0.2f, 0.2f,
									0.2f)));
				} else {
					phraseProInsPremiumLabel1 = new Phrase(ProInsPremium, new Font(
							Font.TIMES_ROMAN, 10, Font.BOLD, new Color(0.2f, 0.2f,
									0.2f)));
				}
				Paragraph paragraphProInsPremiumLabel = new Paragraph();
				paragraphProInsPremiumLabel.add(phraseProInsPremiumLabel);
				paragraphProInsPremiumLabel.add(phraseProInsPremiumLabel1);
				PdfPCell ProInsPremiumLabel = new PdfPCell(
						paragraphProInsPremiumLabel);
				ProInsPremiumLabel.setBorder(0);
	
				proInstable1.addCell(ProInsPremiumLabel);
	
				PdfPCell secondrowcell = new PdfPCell(proInstable1);
				secondrowcell.setBorder(0);
				secondrowcell.setColspan(2);
	
				q19table.addCell(secondrowcell);
	
				q19table.addCell(blank);
	
				String strExp = PDFUtils.getValueFromMap(proInsMap,
						PDFConstants.IsClaimExpLiability, true);
				float[] widths = { 1f, 25f, 1f, 14f };
				PdfPCell cellCheck = PDFUtils
						.getckUckCellObject(
								strExp,
								"Defense expenses are paid in addition to limits of liability",
								"Defense expenses reduce limits of liability                    or",
								100, widths, true);
				cellCheck.setColspan(2);
				q19table.addCell(cellCheck);
	
				q19table.addCell(blank);
	
				if (IsPriorActDateFull.equals("N")) {
					PdfPCell priorActDateProssLabel = PDFUtils
							.addPDFCellWithDefalutSize("Indicate the prior acts date (also known as retroactive date) for your policy: ");
					priorActDateProssLabel.setBorder(0);
					q19table.addCell(priorActDateProssLabel);
	
					PdfPTable tablePriorAct = new PdfPTable(1);
					tablePriorAct.setWidthPercentage(100);
					PdfPCell priorActDateProssValue1 = PDFUtils.getCustomFontValue(
							PriorActDatePross, 10, false, new Color(0.2f, 0.2f,
									0.2f), Font.TIMES_ROMAN, Font.BOLD);
					priorActDateProssValue1.setBorder(0);
	
					tablePriorAct.addCell(priorActDateProssValue1);
					PdfPCell cell2 = PDFUtils.getCustomFontValue("(MM/DD/YYYY)", 8,
							false, new Color(0.2f, 0.2f, 0.2f), Font.TIMES_ROMAN,
							Font.ITALIC);
					cell2.setBorder(0);
					tablePriorAct.addCell(cell2);
					PdfPCell cell3 = new PdfPCell(tablePriorAct);
					cell3.setBorder(0);
					q19table.addCell(cell3);
				} else if (IsPriorActDateFull.equals("Y")) {
					PdfPCell priorActDateProssLabel1 = PDFUtils
							.addPDFCellWithDefalutSize("Prior Acts : Full ");
					priorActDateProssLabel1.setBorder(0);
					priorActDateProssLabel1.setColspan(2);
					q19table.addCell(priorActDateProssLabel1);
	
					q19table.addCell(blank);
	
					PdfPCell priorActDateProssLabel = PDFUtils
							.addPDFCellWithDefalutSize("How many years has your Firm had continuous insurance coverage?");
					priorActDateProssLabel.setBorder(0);
					q19table.addCell(priorActDateProssLabel);
	
					PdfPCell firmYear = PDFUtils.getCustomFontValue(FirmYear, 10,
							false, new Color(0.2f, 0.2f, 0.2f), Font.TIMES_ROMAN,
							Font.BOLD);
					firmYear.setBorder(0);
					q19table.addCell(firmYear);
				}
	
				q19table.addCell(blank);
	
			}
			availableSpace = (writer.getVerticalPosition(false)- document.bottomMargin()-15);
			q19table.setTotalWidth(500f);
			height=0;
			for(int i=0;i<q19table.getRows().size();i++){
				height +=q19table.getRowHeight(i);
			}
			if(availableSpace<height){
				document.newPage();
			}
			document.add(q19table);
			
			PdfPTable q20table = new PdfPTable(3);
			q20table.setWidthPercentage(100);
			q20table.setWidths(width);
			q20table.setSpacingBefore(10f);
			q20table.setSpacingAfter(0f);

			PdfPCell q20SNo = PDFUtils.addPDFCellWithDefalutSize("20.");
			q20SNo.setBorder(0);
			q20table.addCell(q20SNo );
			

			PdfPCell q20label = PDFUtils
					.addPDFCellWithDefalutSize("Does the policy above, exclude coverage for any predecessor Firms, Firm affiliates, clients, specific engagements or other circumstances?");
			q20label.setBorder(0);
//			q20label.setColspan(2);
			q20table.addCell(q20label);

//			PdfPCell q20part1label = PDFUtils
//					.addPDFCellWithDefalutSize("specific engagements or other circumstances?");
//			q20part1label.setBorder(0);
			PdfPCell q20labelValue = PDFUtils
					.getYesNoImageObject(IsPolicyExcludesCoverage);
			q20labelValue.setBorder(0);
			q20labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);

//			q20table.addCell(q20part1label);
			q20table.addCell(q20labelValue);

			if ("Yes".equals(IsPolicyExcludesCoverage)) {
				PdfPTable pdfPTablePolicy=new PdfPTable(2);
				pdfPTablePolicy.setWidthPercentage(100);
				float[] widthTablePolicy={0.6f,2.4f};
				pdfPTablePolicy.setWidths(widthTablePolicy);
				
				PdfPCell q20_yes_label = PDFUtils
						.addPDFCellWithDefalutSizeItalic("If yes, please describe:  ");
				q20_yes_label.setBorder(0);
				PdfPCell q20_yes_labelValue = PDFUtils
						.getCustomFontValue(RetroFirmName, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
				q20_yes_labelValue.setBorder(0);
				
				pdfPTablePolicy.addCell(q20_yes_label);
				pdfPTablePolicy.addCell(q20_yes_labelValue);
				
				PdfPCell cell=new PdfPCell(pdfPTablePolicy);
				cell.setBorder(0);
				cell.setColspan(2);
				q20table.addCell(blank);
				q20table.addCell(cell);
//				q20table.addCell(q20_yes_labelValue);
			}

			document.add(q20table);						
			
			Object objPolicyCoverage= ctx.get(PDFConstants.POLICYCOVERAGE_FREEFORM_01);
			Map policyCoverageMap = null;
			if (objPolicyCoverage != null)
				policyCoverageMap = (Map) objPolicyCoverage;
		
//			if(policyCoverageMap != null) 
//			{
				String LimitAmount = PDFUtils.getValueFromMap(policyCoverageMap, "LimitAmount", false);
				String DeductibleAmount = PDFUtils.getValueFromMap(policyCoverageMap, "DeductibleAmount", false);
				
				String IsClaimExpensesType = PDFUtils.getValueFromMap(policyCoverageMap, PDFConstants.IsClaimExpensesType, false);
				if("Claimpaid".equals(IsClaimExpensesType))
					IsClaimExpensesType = "Yes";
				
				String IsClaimOptionType = PDFUtils.getValueFromMap(policyCoverageMap, PDFConstants.IsClaimOptionType, false);
				if("AnnualAg".equals(IsClaimOptionType))
					IsClaimOptionType = "Yes";
								
				PdfPTable q21to23table = new PdfPTable(3);
				q21to23table.setWidthPercentage(100);
				float[] widthq21to23table = { 2.5f, 51.5f, 21f };
				q21to23table.setWidths(widthq21to23table);
				q21to23table.setSpacingBefore(10f);
				q21to23table.setSpacingAfter(0f);
				
				PdfPCell coverageHeader=PDFUtils.addHeader("COVERAGE SELECTION");
				coverageHeader.setColspan(3);
				q21to23table.addCell(coverageHeader);
				
				q21to23table.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
				
				PdfPCell cellq23a=PDFUtils.addPDFCellValueWithDefalutSize("Indicate your desired coverage selection:");
				cellq23a.setBorder(0);
				cellq23a.setColspan(3);
				q21to23table.addCell(cellq23a);
				
				q21to23table.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
				
				PdfPCell q21Sno=PDFUtils.addPDFCellValueWithDefalutSize("21.");
				q21Sno.setBorder(0);
				q21to23table.addCell(q21Sno);
				
				PdfPTable q21Table=new PdfPTable(3);
				q21Table.setWidthPercentage(100);
				float[] widthq21={20,40,40};
				q21Table.setWidths(widthq21);
				
				PdfPCell LimitAmountLabel = PDFUtils
						.addPDFCellWithDefalutSize("Limits of Liability: ");
				LimitAmountLabel.setBorder(0);
				q21Table.addCell(LimitAmountLabel);
				
				
				
				if (LimitAmount != null) {
					String[] splitS = LimitAmount.split("/");
					Phrase peraLabel=new Phrase("Per Claim: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
					Phrase perValueLabel=new Phrase("$"+ splitS[0],new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
					Paragraph paragraphcell=new Paragraph();
					paragraphcell.add(peraLabel);
					paragraphcell.add(perValueLabel);
					PdfPCell PerLabel = new PdfPCell(paragraphcell);
					PerLabel.setBorder(0);

					q21Table.addCell(PerLabel);

					peraLabel=new Phrase("Annual Aggregate: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
					perValueLabel=new Phrase("$"+ splitS[1],new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
					paragraphcell=new Paragraph();
					paragraphcell.add(peraLabel);
					paragraphcell.add(perValueLabel);
					PdfPCell AnnualLabel = new PdfPCell(paragraphcell);
					AnnualLabel.setBorder(0);

					q21Table.addCell(AnnualLabel);
				} else {
					PdfPCell PerLabel = PDFUtils
							.addPDFCellWithDefalutSize("Per Claim $");
					PerLabel.setBorder(0);

					q21Table.addCell(PerLabel);

					PdfPCell AnnualLabel = PDFUtils
							.addPDFCellWithDefalutSize("Annual Aggregate: $");
					AnnualLabel.setBorder(0);

					q21Table.addCell(AnnualLabel);
				}
				
				

				PdfPCell cellq21=new PdfPCell(q21Table);
				cellq21.setBorder(0);
				cellq21.setColspan(2);
				q21to23table.addCell(cellq21);
				
				
				q21to23table.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
				
				PdfPCell q22Sno=PDFUtils.addPDFCellValueWithDefalutSize("22.");
				q22Sno.setBorder(0);
				q21to23table.addCell(q22Sno);
				
				PdfPTable pdfPTable3=new PdfPTable(2);
				pdfPTable3.setWidthPercentage(100);
				float[] widthTable={20f,80f};
				pdfPTable3.setWidths(widthTable);
				
				Phrase peraLabel=new Phrase("Deductible: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
				Phrase perValueLabel=new Phrase("$" + DeductibleAmount,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
				Paragraph paragraphcell=new Paragraph();
				paragraphcell.add(peraLabel);
				paragraphcell.add(perValueLabel);
				PdfPCell q22Value = new PdfPCell(paragraphcell);
				q22Value.setBorder(0);
				pdfPTable3.addCell(q22Value);
				float[] checkWidth={1f,20f,1f,20f};
				
				PdfPCell checkCell=PDFUtils.getckUckCellObject(IsClaimOptionType, "Annual Aggregate ", "Per Claim        or", 100, checkWidth, false);
				checkCell.setBorder(0);
				pdfPTable3.addCell(checkCell);
				
				PdfPCell decductCell=new PdfPCell(pdfPTable3);
				decductCell.setBorder(0);
				decductCell.setColspan(2);
				q21to23table.addCell(decductCell);
				
				q21to23table.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
				
				PdfPCell q23Sno=PDFUtils.addPDFCellValueWithDefalutSize("23.");
				q23Sno.setBorder(0);
				q21to23table.addCell(q23Sno);
				
				PdfPTable pdfPTable2=new PdfPTable(2);
				pdfPTable2.setWidthPercentage(100);
				float[] widthTable1={20f,80f};
				pdfPTable2.setWidths(widthTable1);
				
				PdfPCell cell=PDFUtils.addPDFCellValueWithDefalutSize("Defense Expenses:");
				cell.setBorder(0);
				pdfPTable2.addCell(cell);
				
				float[] widths={1f,20f,1f,20f};
				PdfPCell cellCheck=PDFUtils.getckUckCellObject(IsClaimExpensesType, "Defense expenses are paid in addition to limits of liability", "Defense expenses reduce limits of liability  or", 100, widths, false);
				pdfPTable2.addCell(cellCheck);
				
				PdfPCell cell2=new PdfPCell(pdfPTable2);
				cell2.setBorder(0);
				cell2.setColspan(2);
				q21to23table.addCell(cell2);
				
				availableSpace = (writer.getVerticalPosition(false)- document.bottomMargin()-15);
				q21to23table.setTotalWidth(500f);
				height=0;
				for(int i=0;i<q21to23table.getRows().size();i++){
					height +=q21to23table.getRowHeight(i);
				}
				if(availableSpace<height){
					document.newPage();
				}
			document.add(q21to23table);				

		document.newPage();
		return null;
	}

	public PdfPTable populateAOP(Document document, Context ctx)
			throws Exception {
		Object obj = ctx.get(PDFConstants.BASICINFO_LIST_AOP);
		List list = null;
		if (obj != null)
			list = (List) obj;

		PdfPTable maintable = new PdfPTable(2);
		if (list != null) {
			maintable.setWidthPercentage(100);
			
			PdfPTable subtable1=new PdfPTable(2);
			subtable1.setWidthPercentage(100);
			float[] widths={3f,1f};
			subtable1.setWidths(widths);
			PdfPCell subHeader11=PDFUtils.addPDFCelHeader("SERVICE AREA");
			subHeader11.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell subHeader12=PDFUtils.addPDFCelHeader("% OF REVENUE");
			subHeader12.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			subtable1.addCell(subHeader11);
			subtable1.addCell(subHeader12);
			
			PdfPTable subtable2=new PdfPTable(2);
			subtable2.setWidthPercentage(100);
			subtable2.setWidths(widths);
			PdfPCell subHeader21=PDFUtils.addPDFCelHeader("SERVICE AREA");
			subHeader21.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell subHeader22=PDFUtils.addPDFCelHeader("% OF REVENUE");
			subHeader22.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			subtable2.addCell(subHeader21);
			subtable2.addCell(subHeader22);
			
			int total = 0;
			PdfPCell pdfCellOtherAOPDesc=null;
			PdfPCell pdfPCellOtherAOPPerc=null;
			Phrase pdfPCellOtherAOPComment=null;
			int ascii=65;
			byte[] bytes = new byte[1];
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				String AOPDesc = PDFUtils
						.getValueFromMap(map, "AOPDesc", false);
				String PercentageValue = PDFUtils.getValueFromMap(map,
						"PercentageValue", false);
				String AOPCommentDesc = PDFUtils.getValueFromMap(map,
						"AOPCommentDesc", false);
				
				if("5".equals(map.get("AOPKey").toString()) || "14".equals(map.get("AOPKey").toString())
						|| "18".equals(map.get("AOPKey").toString()) || "19".equals(map.get("AOPKey").toString())
						|| "6".equals(map.get("AOPKey").toString()))
					ctx.put("AOP_Percentage_"+map.get("AOPKey"), PercentageValue);
				

				if (map.get("PercentageValue") != null) {

					if (!"".equals(map.get("PercentageValue").toString()))
						total = total
								+ Integer.parseInt(map.get("PercentageValue")
										.toString());
				}

				if (i < 3) {
					if(i==0){
						PdfPCell subheader = PDFUtils.addPDFCelSubHeader("Tax");
						subheader.setColspan(2);
						subtable1.addCell(subheader);
					}
					bytes[0] = (byte) ascii++;
					subtable1.addCell(PDFUtils
							.getCustomFontValue("    " + new String(bytes) + ". " + AOPDesc, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL));
					subtable1.addCell(PDFUtils
							.getCustomFontValue(PercentageValue + "%", 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD));
				} else if (i > 2 && i < 4) {
					PdfPCell subheader = PDFUtils.addPDFCelSubHeader("Accounting / Bookkeeping");
					subheader.setColspan(2);
					subheader.setMinimumHeight(24f);
					subtable1.addCell(subheader);
					
					bytes[0] = (byte) ascii++;
					subtable1.addCell(PDFUtils
							.getCustomFontValue("    " + new String(bytes) + ". " + AOPDesc, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL));
					subtable1.addCell(PDFUtils
							.getCustomFontValue(PercentageValue + "%", 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD));
				} else if (i > 3 && i < 10) {
					if(i==4){
						PdfPCell subheader = PDFUtils.addPDFCelSubHeader("Attestation");
						subheader.setColspan(2);
						subtable1.addCell(subheader);
					}else if(i==6){
						Chunk chunk=new Chunk("(1)",new Font(Font.TIMES_ROMAN,7f,Font.BOLDITALIC,new Color(0.2f,0.2f,0.2f,0.2f)));
						chunk.setTextRise(4.0f);
						Phrase phrase2=new Phrase("Complete the Audit Supplement if an entry in E or F above ",new Font(Font.TIMES_ROMAN,8,Font.BOLDITALIC,new Color(0.2f,0.2f,0.2f)));
						Paragraph paragraph=new Paragraph();
						paragraph.add(chunk);
						paragraph.add(phrase2);
						PdfPCell subheader = new PdfPCell(paragraph);
						subheader.setColspan(2);
						subtable1.addCell(subheader);
					}
					Chunk chunk;
					if(i==4 || i==5){
						chunk=new Chunk("(1)",new Font(Font.TIMES_ROMAN,7f,Font.BOLD,new Color(0.2f,0.2f,0.2f,0.2f)));							
					}else if(i==8){
						chunk=new Chunk("(2)",new Font(Font.TIMES_ROMAN,7f,Font.BOLD,new Color(0.2f,0.2f,0.2f,0.2f)));
					}else if(i==9){
						chunk=new Chunk("(3)",new Font(Font.TIMES_ROMAN,7f,Font.BOLD,new Color(0.2f,0.2f,0.2f,0.2f)));
					}else{
						chunk=new Chunk("",new Font(Font.TIMES_ROMAN,7f,Font.BOLD,new Color(0.2f,0.2f,0.2f,0.2f)));
					}
					chunk.setTextRise(4.0f);
					bytes[0] = (byte) ascii++;
					Phrase phrase2=new Phrase("    " + new String(bytes) + ". " + AOPDesc,new Font(Font.TIMES_ROMAN,10,Font.NORMAL,new Color(0.2f,0.2f,0.2f)));
					Paragraph paragraph=new Paragraph();
					paragraph.add(phrase2);
					paragraph.add(chunk);
					PdfPCell subheader = new PdfPCell(paragraph);
					subtable1.addCell(subheader);					
					subtable1.addCell(PDFUtils
							.getCustomFontValue(PercentageValue + "%", 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD));
					if (i == 8 || i == 9) {
						if(i==8){
							chunk=new Chunk("(2)",new Font(Font.TIMES_ROMAN,7f,Font.BOLD,new Color(0.2f,0.2f,0.2f,0.2f)));	
						}else{
							chunk=new Chunk("(3)",new Font(Font.TIMES_ROMAN,7f,Font.BOLD,new Color(0.2f,0.2f,0.2f,0.2f)));
						}
						chunk.setTextRise(4.0f);
						phrase2=new Phrase("Please describe: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL,new Color(0.2f,0.2f,0.2f)));
						Phrase phrase3=new Phrase(AOPCommentDesc,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
						paragraph=new Paragraph();
						paragraph.add(chunk);
						paragraph.add(phrase2);
						paragraph.add(phrase3);
						subheader = new PdfPCell(paragraph);
						subheader.setColspan(2);
						subtable1.addCell(subheader);						
//						subtable1.addCell(PDFUtils
//								.getCustomFontValue(AOPCommentDesc, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL));
						PdfPCell blank=PDFUtils.addPDFCellValueWithDefalutSize(" ");
						blank.setColspan(2);
						subtable1.addCell(blank);
						if(i==9){
							subtable1.addCell(blank);
							subtable1.addCell(blank);
						}
					}
				} else if (i > 9 && i < 17) {
					if(i==10){
						PdfPCell subheader = PDFUtils.addPDFCelSubHeader("Consulting");
						subheader.setColspan(2);
						subtable2.addCell(subheader);
					}
					Chunk chunk;
					if(i==11){
						chunk=new Chunk("(4)",new Font(Font.TIMES_ROMAN,7f,Font.BOLD,new Color(0.2f,0.2f,0.2f,0.2f)));							
					}else if(i==13){
						chunk=new Chunk("(5)",new Font(Font.TIMES_ROMAN,7f,Font.BOLD,new Color(0.2f,0.2f,0.2f,0.2f)));
					}else{
						chunk=new Chunk("",new Font(Font.TIMES_ROMAN,7f,Font.BOLD,new Color(0.2f,0.2f,0.2f,0.2f)));
					}
					chunk.setTextRise(4.0f);
					bytes[0] = (byte) ascii++;
					Phrase phrase2=new Phrase("    " + new String(bytes) + ". " +  AOPDesc,new Font(Font.TIMES_ROMAN,10,Font.NORMAL,new Color(0.2f,0.2f,0.2f)));
					Paragraph paragraph=new Paragraph();
					paragraph.add(phrase2);
					paragraph.add(chunk);
					PdfPCell subheader = new PdfPCell(paragraph);
					subtable2.addCell(subheader);
					subtable2.addCell(PDFUtils
							.getCustomFontValue(PercentageValue + "%", 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD));
					if (i == 11) {
						chunk=new Chunk("(4)",new Font(Font.TIMES_ROMAN,7f,Font.BOLD,new Color(0.2f,0.2f,0.2f,0.2f)));
						chunk.setTextRise(4.0f);
						phrase2=new Phrase("Please describe: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL,new Color(0.2f,0.2f,0.2f)));
						Phrase phrase3=new Phrase(AOPCommentDesc,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
						paragraph=new Paragraph();
						paragraph.add(chunk);
						paragraph.add(phrase2);
						paragraph.add(phrase3);
						subheader = new PdfPCell(paragraph);
						subheader.setColspan(2);
						subtable2.addCell(subheader);
						
//						subtable2.addCell(PDFUtils
//								.getCustomFontValue(AOPCommentDesc, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL));
					}if(i==13){
						chunk=new Chunk("(5)",new Font(Font.TIMES_ROMAN,7f,Font.BOLDITALIC,new Color(0.2f,0.2f,0.2f,0.2f)));
						chunk.setTextRise(4.0f);
						phrase2=new Phrase("Complete Computer Related Services Supplement  if entry in N above",new Font(Font.TIMES_ROMAN,8,Font.BOLDITALIC,new Color(0.2f,0.2f,0.2f)));
						paragraph=new Paragraph();
						paragraph.add(chunk);
						paragraph.add(phrase2);
						subheader = new PdfPCell(paragraph);
						subheader.setFixedHeight(14f);
						subheader.setColspan(2);
						subtable2.addCell(subheader);
					}
				} else if (i > 16 && i < 19 || i == 20) {
					if(i==17){
						PdfPCell subheader = PDFUtils.addPDFCelSubHeader("Special Services");
						subheader.setColspan(2);
						subtable2.addCell(subheader);
					}
					Chunk chunk;
					if(i==17){
						chunk=new Chunk("(6)",new Font(Font.TIMES_ROMAN,7f,Font.BOLD,new Color(0.2f,0.2f,0.2f,0.2f)));							
					}else if(i==18){
						chunk=new Chunk("(7)",new Font(Font.TIMES_ROMAN,7f,Font.BOLD,new Color(0.2f,0.2f,0.2f,0.2f)));
					}else if(i==20){
						chunk=new Chunk("(8)",new Font(Font.TIMES_ROMAN,7f,Font.BOLD,new Color(0.2f,0.2f,0.2f,0.2f)));
					}else {
						chunk=new Chunk("",new Font(Font.TIMES_ROMAN,7f,Font.BOLD,new Color(0.2f,0.2f,0.2f,0.2f)));
					}
					chunk.setTextRise(4.0f);
					bytes[0] = (byte) ascii++;
					Phrase phrase2=new Phrase("    " + new String(bytes) + ". " +  AOPDesc,new Font(Font.TIMES_ROMAN,10,Font.NORMAL,new Color(0.2f,0.2f,0.2f)));
					Paragraph paragraph=new Paragraph();
					paragraph.add(phrase2);
					paragraph.add(chunk);
					PdfPCell subheader = new PdfPCell(paragraph);
					subtable2.addCell(subheader);
					subtable2.addCell(PDFUtils
							.getCustomFontValue(PercentageValue + "%", 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD));
					if(i==17){
						chunk=new Chunk("(6)",new Font(Font.TIMES_ROMAN,7f,Font.BOLDITALIC,new Color(0.2f,0.2f,0.2f,0.2f)));
						chunk.setTextRise(4.0f);
						phrase2=new Phrase("Complete the Business Management Supplement if an entry in R above",new Font(Font.TIMES_ROMAN,8,Font.BOLDITALIC,new Color(0.2f,0.2f,0.2f)));
						paragraph=new Paragraph();
						paragraph.add(chunk);
						paragraph.add(phrase2);
						subheader = new PdfPCell(paragraph);
						subheader.setColspan(2);
						subtable2.addCell(subheader);
					}else if (i == 20) {
						chunk=new Chunk("(7)",new Font(Font.TIMES_ROMAN,7f,Font.BOLD,new Color(0.2f,0.2f,0.2f,0.2f)));
						chunk.setTextRise(4.0f);
						phrase2=new Phrase("Please describe: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL,new Color(0.2f,0.2f,0.2f)));
						Phrase phrase3=new Phrase(AOPCommentDesc,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
						paragraph=new Paragraph();
						paragraph.add(chunk);
						paragraph.add(phrase2);
						paragraph.add(phrase3);
						PdfPCell subheader1 = new PdfPCell(paragraph);
						subheader1.setColspan(2);
						subtable2.addCell(subheader1);
//						subtable2.addCell(PDFUtils
//								.getCustomFontValue(AOPCommentDesc, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL));
						subheader = PDFUtils.addPDFCelSubHeader("Other");
						subheader.setColspan(2);
						subtable2.addCell(subheader);
						subtable2.addCell(pdfCellOtherAOPDesc);
						subtable2.addCell(pdfPCellOtherAOPPerc);
						chunk=new Chunk("(9)",new Font(Font.TIMES_ROMAN,7f,Font.BOLD,new Color(0.2f,0.2f,0.2f,0.2f)));
						chunk.setTextRise(4.0f);
						phrase2=new Phrase("Please describe: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL,new Color(0.2f,0.2f,0.2f)));
						paragraph=new Paragraph();
						paragraph.add(chunk);
						paragraph.add(phrase2);
						paragraph.add(pdfPCellOtherAOPComment);
						subheader1 = new PdfPCell(paragraph);
						subheader1.setColspan(2);
						subtable2.addCell(subheader1);
						//subtable2.addCell(pdfPCellOtherAOPComment);
					}else if(i==18){
						chunk=new Chunk("(8)",new Font(Font.TIMES_ROMAN,7f,Font.BOLDITALIC,new Color(0.2f,0.2f,0.2f,0.2f)));
						chunk.setTextRise(4.0f);
						phrase2=new Phrase("Complete the Investment Financial Planning Supplement if entry in T above",new Font(Font.TIMES_ROMAN,8,Font.BOLDITALIC,new Color(0.2f,0.2f,0.2f)));
						paragraph=new Paragraph();
						paragraph.add(chunk);
						paragraph.add(phrase2);
						subheader = new PdfPCell(paragraph);
						subheader.setColspan(2);
						subtable2.addCell(subheader);
					}
				} else if (i > 18 && i < 20) {
					Chunk chunk = new Chunk("(9)", new Font(Font.TIMES_ROMAN, 7f,
							Font.BOLD, new Color(0.2f, 0.2f, 0.2f, 0.2f)));
					chunk.setTextRise(4.0f);
					bytes[0] = (byte) ++ascii;
					Phrase phrase2 = new Phrase("    " + new String(bytes) + ". " +  AOPDesc, new Font(
							Font.TIMES_ROMAN, 10, Font.NORMAL, new Color(0.2f,
									0.2f, 0.2f)));
					ascii--;
					Paragraph paragraph = new Paragraph();
					paragraph.add(phrase2);
					paragraph.add(chunk);
					pdfCellOtherAOPDesc = new PdfPCell(paragraph);
					pdfPCellOtherAOPPerc=PDFUtils
							.getCustomFontValue(PercentageValue + "%", 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
					if (i == 19) {
						
						pdfPCellOtherAOPComment=new Phrase(AOPCommentDesc,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
					}
				}
			}
			
			PdfPCell maintablecolumn1=new PdfPCell(subtable1);
//			maintablecolumn1.setBorder(0);
			PdfPCell maintablecolumn2=new PdfPCell(subtable2);
//			maintablecolumn2.setBorder(0);
			
			maintable.addCell(maintablecolumn1);
			maintable.addCell(maintablecolumn2);
			PdfPCell cell=PDFUtils.addPDFCellValueWithDefalutSize(" ");
			cell.setBorder(0);
			cell.setColspan(2);
			maintable.addCell(cell);
			PdfPCell totalcell = PDFUtils
					.getCustomFontValue("Total of all items: "
							+ new Integer(total).toString() + "%", 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
			totalcell.setColspan(2);
			totalcell.setHorizontalAlignment((Element.ALIGN_RIGHT));
			totalcell.setBorder(0);
			maintable.addCell(totalcell);

			//document.add(maintable);
		}

		return maintable;
	}

	public PdfPTable populatePublicPrivateOffering(Document document,
			Context ctx) throws Exception {
		Object obj = ctx.get(PDFConstants.BASICINFO_LIST_06);
		List list = null;
		if (obj != null)
			list = (List) obj;

		PdfPTable headtable = null;
		if (list != null) {
			headtable = new PdfPTable(6);
			headtable.setWidthPercentage(100);
			headtable.addCell(PDFUtils
					.addPDFCelHeader("Client Name & Industry"));
			headtable.addCell(PDFUtils
					.addPDFCelHeader("Type of Services Rendered by Your Firm"));
			headtable.addCell(PDFUtils
					.addPDFCelHeader("Year Services Rendered"));
			headtable.addCell(PDFUtils.addPDFCelHeader("Size of Offering"));
			headtable.addCell(PDFUtils.addPDFCelHeader("Fees Charged"));
			headtable.addCell(PDFUtils.addPDFCelHeader("Type of Offering*"));
			// document.add(headtable);

			for (int i = 0; i < list.size(); i++) {
				// PdfPTable rowtable = new PdfPTable(6);
				// rowtable.setWidthPercentage(100);
				Map map = (Map) list.get(i);
				String ClientIndustryDesc = PDFUtils.getValueFromMap(map,
						"ClientIndustryDesc", false);
				String ServiceIndustryDesc = PDFUtils.getValueFromMap(map,
						"ServiceIndustryDesc", false);
				String YearServiceRenderedPpo = PDFUtils.getValueFromMap(map,
						"YearServiceRenderedPpo", false);
				String SizeOfOfferingPpo = PDFUtils.getValueFromMap(map,
						"SizeOfOfferingPpo", false);
				String FeesChargedPpo = PDFUtils.getValueFromMap(map,
						"FeesChargedPpo", false);
				String TypeOfOfferingDesc = PDFUtils.getValueFromMap(map,
						"TypeOfOfferingDesc", false);

				PdfPCell cell = PDFUtils
						.getCustomFontValue(ClientIndustryDesc, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				headtable.addCell(cell);
				cell = PDFUtils
						.getCustomFontValue(ServiceIndustryDesc, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				headtable.addCell(cell);
				cell = PDFUtils
						.getCustomFontValue(YearServiceRenderedPpo, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				headtable.addCell(cell);
//				if(!SizeOfOfferingPpo.equals("")){
//					cell = PDFUtils.getCustomFontValue(SizeOfOfferingPpo, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);	
//				}else{
					cell = PDFUtils.getCustomFontValue(SizeOfOfferingPpo, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
//				}
				headtable.addCell(cell);
				if(!FeesChargedPpo.equals("")){
					cell = PDFUtils.getCustomFontValue(PDFGenerator.formatter.format(new Double(FeesChargedPpo)), 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);	
				}else{
					cell = PDFUtils.getCustomFontValue(FeesChargedPpo, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				}
				headtable.addCell(cell);
				cell = PDFUtils.getCustomFontValue(TypeOfOfferingDesc, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				headtable.addCell(cell);

				// document.add(rowtable);

			}
		}

		return headtable;
	}

	public PdfPTable populateTrustee(Document document, Context ctx)
			throws Exception {
		Object obj = ctx.get(PDFConstants.GENERAL_LIST_01);
		List list = null;
		if (obj != null)
			list = (List) obj;

		PdfPTable headtable = null;
		if (list != null) {
			headtable = new PdfPTable(7);
			headtable.setWidthPercentage(100);
			headtable.addCell(PDFUtils
					.addPDFCelHeader("Name of Trust or Estate"));
			headtable.addCell(PDFUtils
					.addPDFCelHeader("Start Date of Engagement"));
			headtable.addCell(PDFUtils.addPDFCelHeader("Type"));
			headtable.addCell(PDFUtils.addPDFCelHeader("Value of Assets"));
			headtable.addCell(PDFUtils
					.addPDFCelHeader("Annual Income of Assets"));
			headtable.addCell(PDFUtils
					.addPDFCelHeader("Number of Beneficiaries"));
			headtable.addCell(PDFUtils.addPDFCelHeader("**Beneficiary Interest"));
			// document.add(headtable);

			for (int i = 0; i < list.size(); i++) {
				// PdfPTable rowtable = new PdfPTable(7);
				// rowtable.setWidthPercentage(100);
				Map map = (Map) list.get(i);
				String NameOfTrust = PDFUtils.getValueFromMap(map,
						"NameOfTrust", false);
				String StartEngagemenTrusttDate = PDFUtils
						.getFormattedDateFromObject(map.get("StartEngagemenTrusttDate"));
				String TypeofTrustDesc = PDFUtils.getValueFromMap(map,
						"TypeofTrustDesc", false);
				String ValueOfAssetsTrust = PDFUtils.getValueFromMap(map,
						"ValueOfAssetsTrust", false);
				String AnnualIncomeofAssetsTrust = PDFUtils.getValueFromMap(
						map, "AnnualIncomeofAssetsTrust", false);
				String NumberOfBeneficiariesTrust = PDFUtils.getValueFromMap(
						map, "NumberOfBeneficiariesTrust", false);
				String IsBeneficiaryInterestTrust = PDFUtils.getValueFromMap(
						map, "IsBeneficiaryInterestTrust", true);

				PdfPCell cell = PDFUtils.getCustomFontValue(NameOfTrust, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				headtable.addCell(cell);
				cell = PDFUtils
						.getCustomFontValue(StartEngagemenTrusttDate, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				headtable.addCell(cell);
				cell = PDFUtils.getCustomFontValue(TypeofTrustDesc, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				headtable.addCell(cell);
				if(!ValueOfAssetsTrust.equals("")){
					cell = PDFUtils.getCustomFontValue(PDFGenerator.formatter.format(new Double(ValueOfAssetsTrust)), 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);	
				}else{
					cell = PDFUtils.getCustomFontValue(ValueOfAssetsTrust, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				}
				headtable.addCell(cell);
				if(!AnnualIncomeofAssetsTrust.equals("")){
					cell = PDFUtils.getCustomFontValue(PDFGenerator.formatter.format(new Double(AnnualIncomeofAssetsTrust)), 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				}else{
					cell = PDFUtils
					.getCustomFontValue(AnnualIncomeofAssetsTrust, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				}
				headtable.addCell(cell);
				if(!NumberOfBeneficiariesTrust.equals("")){
					cell = PDFUtils
					.getCustomFontValue(PDFGenerator.numberFormat.format(new Double(NumberOfBeneficiariesTrust)), 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);					
				}else{
					cell = PDFUtils
					.getCustomFontValue(NumberOfBeneficiariesTrust, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				}
				
				headtable.addCell(cell);
				cell = PDFUtils
						.getYesNoImageObjectForTable(IsBeneficiaryInterestTrust);
				cell.setPadding(3f);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headtable.addCell(cell);

				// document.add(rowtable);

			}
		}
		return headtable;
	}

	public PdfPTable populateFirmRendered(Document document, Context ctx)
			throws Exception {
		Object obj = ctx.get(PDFConstants.GENERAL_LIST_04);
		List list = null;
		if (obj != null)
			list = (List) obj;

		PdfPTable headtable = null;
		if (list != null) {
			headtable = new PdfPTable(8);
			headtable.setWidthPercentage(100);
			float[] width={10f,10f,10f,15f,15f,10f,15f,15f};
			headtable.setWidths(width);
			headtable.addCell(PDFUtils.addPDFCelHeader("Firm Member"));
			headtable.addCell(PDFUtils.addPDFCelHeader("Entity Name"));
			headtable.addCell(PDFUtils.addPDFCelHeader("Industry"));
			headtable.addCell(PDFUtils.addPDFCelHeader("Position held"));
			headtable
					.addCell(PDFUtils
							.addPDFCelHeader("Is Director / Officer Insurance in force?"));
			headtable.addCell(PDFUtils
					.addPDFCelHeader("Percent Equity Interest"));
			headtable.addCell(PDFUtils.addPDFCelHeader("Firm Services"));
			headtable
					.addCell(PDFUtils
							.addPDFCelHeader("Does the individual listed perform these services?"));
			// document.add(headtable);

			for (int i = 0; i < list.size(); i++) {
				// PdfPTable rowtable = new PdfPTable(8);
				// rowtable.setWidthPercentage(100);
				Map map = (Map) list.get(i);
				String ClientNameOsi = PDFUtils.getValueFromMap(map,
						"ClientNameOsi", false);
				String EntityNameOsi = (PDFUtils.getValueFromMap(map,
						"EntityNameOsi", false));
				String ClientIndustryOsiKey = PDFUtils.getValueFromMap(map,
						"ClientIndustryDesc", false);
				String PositionHeldOsi = PDFUtils.getValueFromMap(map,
						"PositionHeldOsi", false);
				String DOInsuranceOsi = PDFUtils.getValueFromMap(map,
						"DOInsuranceOsi", true);
				String PercentEquityInterestOsi = PDFUtils.getValueFromMap(
						map, "PercentEquityInterestofOsi", false);
				String ServiceIndustryOsiKey = PDFUtils.getValueFromMap(map,
						"ServiceIndustryDesc", false);
				String IndividualListedServicesOsi = PDFUtils.getValueFromMap(
						map, "IndividualListedServicesOsi", true);

				PdfPCell cell = PDFUtils
						.getCustomFontValue(ClientNameOsi, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				headtable.addCell(cell);
				cell = PDFUtils.getCustomFontValue(EntityNameOsi, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				headtable.addCell(cell);
				cell = PDFUtils.getCustomFontValue(ClientIndustryOsiKey, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				headtable.addCell(cell);
				cell = PDFUtils.getCustomFontValue(PositionHeldOsi, 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				headtable.addCell(cell);
				cell = PDFUtils.getYesNoImageObjectForTable(DOInsuranceOsi);
				cell.setPadding(3f);
				headtable.addCell(cell);
				cell = PDFUtils
						.getCustomFontValue((PercentEquityInterestOsi.equalsIgnoreCase("") ? "" :  PercentEquityInterestOsi+"%"), 10, true, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.NORMAL);
				headtable.addCell(cell);
				cell = PDFUtils
						.addPDFCellWithDefalutSize(ServiceIndustryOsiKey);
				headtable.addCell(cell);
				cell = PDFUtils
						.getYesNoImageObjectForTable(IndividualListedServicesOsi);
				cell.setPadding(3f);
				headtable.addCell(cell);

				// document.add(rowtable);

			}
		}
		return headtable;
	}

	public PdfPTable populateClaimSupplementList(Document document, Context ctx, PdfWriter writer)
			throws Exception {
		Object obj = ctx.get(PDFConstants.BASICINFO_LIST_04);
		List list = null;
		if (obj != null)
			list = (List) obj;

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				document.add(populateClaimSupplement(document, ctx, map,writer));
			}
		}

		return null;
	}

	public PdfPTable populateClaimSupplement(Document document, Context ctx,
			Map map, PdfWriter writer) throws Exception {
		
		PdfPTable maintable = new PdfPTable(3);
		maintable.setWidthPercentage(100);
		float[] width = {2.5f, 51.5f, 21f };
		maintable.setWidths(width);
		maintable.setSpacingBefore(10f);
		maintable.setSpacingAfter(0f);
		
		PdfPCell cellHeader=PDFUtils.addHeader("CLAIM  SUPPLEMENT (As required in  question 16 b or 16c)");
		cellHeader.setColspan(3);
		maintable.addCell(cellHeader);
		
		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
		
		String NameOfClaimantCi = PDFUtils.getValueFromMap(map,
				"NameOfClaimantCi", false);
		String IsClientCi = PDFUtils.getValueFromMap(map, "IsClientCi", true);
		String NameOfFirmPersonnelCi = PDFUtils.getValueFromMap(map,
				"NameOfFirmPersonnelCi", false);
		String DescOfClaimCi = PDFUtils.getValueFromMap(map, "DescOfClaimCi",
				false);

		String IsThisClaimReportedCi = PDFUtils.getValueFromMap(map,
				"IsThisClaimReportedCi", true);
		String NameOfInsuranceCompanyCi = PDFUtils.getValueFromMap(map,
				"NameOfInsuranceCompanyCi", false);
		String InsurerLossReserveCi = PDFUtils.getValueFromMap(map,
				"InsurerLossReserveCi", false);

		String ClaimantLastDemandCi = PDFUtils.getValueFromMap(map,
				"ClaimantLastDemandCi", false);
		String TotalClaimExpensesCi = PDFUtils.getValueFromMap(map,
				"TotalClaimExpensesCi", false);
		String StepsTakenToPreventClaimCi = PDFUtils.getValueFromMap(map,
				"StepsTakenToPreventClaimCi", false);

		String ClaimNotifiedCiDate = map.get("ClaimNotifiedCiDate") != null ? PDFUtils
				.getFormattedDateFromObject(map.get("ClaimNotifiedCiDate"))
				: "";
		String AllegedErrorCiDate = map.get("AllegedErrorCiDate") != null ? PDFUtils
				.getFormattedDateFromObject(map.get("AllegedErrorCiDate"))
				: "";
		String ReportedCiDate = map.get("ReportedCiDate") != null ? PDFUtils
				.getFormattedDateFromObject(map.get("ReportedCiDate")) : "";
		String ClosingCiDate = map.get("ClosingCiDate") != null ? PDFUtils
				.getFormattedDateFromObject(map.get("ClosingCiDate")) : "";
				
		String CurrentStatus = PDFUtils.getValueFromMap(map,
				"CurrentStatus", false);

		
		PdfPCell q1SNo = PDFUtils.addPDFCellWithDefalutSize("1.");
		q1SNo.setBorder(0);
		maintable.addCell(q1SNo);
				
		Phrase phrase1=new Phrase("Full name of claimant or potential claimant: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
		Phrase phrase2=new Phrase(NameOfClaimantCi,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
		Paragraph paragraph=new Paragraph();
		paragraph.add(phrase1);
		paragraph.add(phrase2);
		PdfPCell q1label = new PdfPCell(paragraph);
		q1label.setBorder(0);
		maintable.addCell(q1label);

		float[] tempwidth={1f,4f,1f,4f};
		PdfPCell q1labelValue = PDFUtils.getckUckCellObject(IsClientCi,"Client","Non-Client",20,tempwidth,true);
		q1labelValue.setBorder(0);
		q1labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
		maintable.addCell(q1labelValue);

		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));

		PdfPCell q2SNo = PDFUtils.addPDFCellWithDefalutSize("2.");
		q2SNo.setBorder(0);
		maintable.addCell(q2SNo);
		
		PdfPTable pdfTableQ2=new PdfPTable(4);
		pdfTableQ2.setWidthPercentage(100);
		float[] widths={1.4f,1f,1f,1f};
		pdfTableQ2.setWidths(widths);
		
		PdfPCell q2label = PDFUtils
				.addPDFCellWithDefalutSize("Date Firm was notified of claim: ");
		q2label.setBorder(0);
		pdfTableQ2.addCell(q2label);

		PdfPCell q2labelValue = PDFUtils
				.getCustomFontValue(ClaimNotifiedCiDate, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
		q2labelValue.setBorder(0);
		pdfTableQ2.addCell(q2labelValue);
		
		PdfPCell q2label2 = PDFUtils
				.addPDFCellWithDefalutSize("Date of alleged error ");
		q2label2.setBorder(0);
		pdfTableQ2.addCell(q2label2);

		PdfPCell q2labelValue2 = PDFUtils
				.getCustomFontValue(AllegedErrorCiDate, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
		q2labelValue2.setBorder(0);
		pdfTableQ2.addCell(q2labelValue2);

		PdfPCell blank = PDFUtils.addPDFCellWithDefalutSize(" ");
		blank.setBorder(0);
		
		pdfTableQ2.addCell(blank);
		PdfPCell cellDateFormat=PDFUtils.getCustomFontValue("(MM/DD/YYYY)", 8, false, new Color(0.0f,0.0f,0.0f), Font.TIMES_ROMAN, Font.ITALIC);
		cellDateFormat.setBorder(0);
		pdfTableQ2.addCell(cellDateFormat);
		pdfTableQ2.addCell(blank);
		pdfTableQ2.addCell(cellDateFormat);
		
		PdfPCell cell=new PdfPCell(pdfTableQ2);
		cell.setBorder(0);
		cell.setColspan(2);
		maintable.addCell(cell);

		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));

		PdfPCell q3SNo = PDFUtils.addPDFCellWithDefalutSize("3.");
		q3SNo.setBorder(0);
		maintable.addCell(q3SNo);

		phrase1=new Phrase("Names of Firm personnel involved in the claim or potential claim: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
		phrase2=new Phrase(NameOfFirmPersonnelCi,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
		paragraph=new Paragraph();
		paragraph.add(phrase1);
		paragraph.add(phrase2);
		PdfPCell q3label = new PdfPCell(paragraph);
		q3label.setBorder(0);
		q3label.setColspan(2);
		maintable.addCell(q3label);

//		PdfPCell q3labelValue = PDFUtils
//				.addPDFCellWithDefalutSize(NameOfFirmPersonnelCi);
//		q3labelValue.setBorder(0);
//		q3labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		maintable.addCell(q3labelValue);

		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));

		PdfPCell q4SNo = PDFUtils.addPDFCellWithDefalutSize("4.");
		q4SNo.setBorder(0);
		maintable.addCell(q4SNo);
		
		Phrase phrase3=new Phrase("Description of claim or potential claim: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
		Phrase phrase4=new Phrase(DescOfClaimCi,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
		Paragraph paragraph1=new Paragraph();
		paragraph1.add(phrase3);
		paragraph1.add(phrase4);
		PdfPCell q4alabel = new PdfPCell(paragraph1);
		q4alabel.setBorder(0);
		q4alabel.setColspan(2);
		maintable.addCell(q4alabel);

//		PdfPCell q4blabel = PDFUtils.addPDFCellWithDefalutSize(DescOfClaimCi);
//		q4blabel.setBorder(0);
//		maintable.addCell(q4blabel);

		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));

		PdfPCell q5SNo = PDFUtils.addPDFCellWithDefalutSize("5.");
		q5SNo.setBorder(0);
		maintable.addCell(q5SNo);
		
		PdfPCell q5label = PDFUtils
				.addPDFCellWithDefalutSize("Has this claim or potential claim been reported to your insurance company?");
		q5label.setBorder(0);
		maintable.addCell(q5label);

		PdfPCell q5labelValue = PDFUtils
				.getYesNoImageObject(IsThisClaimReportedCi);
		q5labelValue.setBorder(0);
		q5labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
		maintable.addCell(q5labelValue);

		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));

		PdfPCell q6SNo = PDFUtils.addPDFCellWithDefalutSize("6.");
		q6SNo.setBorder(0);
		maintable.addCell(q6SNo);
		
		PdfPTable pdfTableQ6=new PdfPTable(4);
		pdfTableQ6.setWidthPercentage(100);
		pdfTableQ6.setWidths(widths);
		
		Phrase phrase5=new Phrase("Name of insurance company: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
		Phrase phrase6=new Phrase(NameOfInsuranceCompanyCi,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
		Paragraph paragraph2=new Paragraph();
		paragraph2.add(phrase5);
		paragraph2.add(phrase6);
		PdfPCell q6label = new PdfPCell(paragraph2);
		q6label.setBorder(0);
		q6label.setColspan(2);
		pdfTableQ6.addCell(q6label);

		PdfPTable pdfSubTableQ6=new PdfPTable(2);
		pdfSubTableQ6.setWidthPercentage(100);
		float[] widthSubTableQ6={2f,1f};
		pdfSubTableQ6.setWidths(widthSubTableQ6);
		
		PdfPCell q6label2 = PDFUtils
				.addPDFCellWithDefalutSize("Date reported to insurance company: ");
		q6label2.setBorder(0);
		pdfSubTableQ6.addCell(q6label2);

		PdfPCell q6labelValue2 = PDFUtils
				.getCustomFontValue(ReportedCiDate, 10, false, new Color(0.2f,0.2f,0.2f), Font.TIMES_ROMAN, Font.BOLD);
		q6labelValue2.setBorder(0);
		pdfSubTableQ6.addCell(q6labelValue2);

		pdfSubTableQ6.addCell(blank);
		pdfSubTableQ6.addCell(cellDateFormat);
		
		PdfPCell cellQ6SubTable=new PdfPCell(pdfSubTableQ6);
		cellQ6SubTable.setBorder(0);
		cellQ6SubTable.setColspan(2);
		pdfTableQ6.addCell(cellQ6SubTable);
		
		PdfPCell cell1=new PdfPCell(pdfTableQ6);
		cell1.setBorder(0);
		cell1.setColspan(2);
		maintable.addCell(cell1);

		
//		PdfPCell q6label = PDFUtils
//				.addPDFCellWithDefalutSize("Name of insurance company:  "
//						+ NameOfInsuranceCompanyCi);
//		q6label.setBorder(0);
//		maintable.addCell(q6label);
//
//		PdfPCell q6labelValue = PDFUtils
//				.addPDFCellWithDefalutSize("Date reported to insurance company: "
//						+ ReportedCiDate);
//		q6labelValue.setBorder(0);
//		q6labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		maintable.addCell(q6labelValue);

		//maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));

		PdfPCell q7SNo = PDFUtils.addPDFCellWithDefalutSize("7.");
		q7SNo.setBorder(0);
		maintable.addCell(q7SNo);

		Phrase phrase7=new Phrase("If open, provide: Insurer’s loss reserve ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
		Phrase phrase8;
		if(!InsurerLossReserveCi.equals("")){
			phrase8=new Phrase(PDFGenerator.formatter.format(new Double(InsurerLossReserveCi)),new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));	
		}else{
			phrase8=new Phrase(InsurerLossReserveCi,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
		}
		Phrase phrase9=new Phrase("Claimant’s last demand ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
		Phrase phrase10;
		if(!ClaimantLastDemandCi.equals("")){
			phrase10=new Phrase(PDFGenerator.formatter.format(new Double(ClaimantLastDemandCi)) + ";",new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));	
		}else{
			phrase10=new Phrase(ClaimantLastDemandCi + ";",new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
		}
		Paragraph paragraph4=new Paragraph();
		//paragraph4.add(phrase7);
//		paragraph4.add(phrase8);
		paragraph4.add(phrase9);
		paragraph4.add(phrase10);
		PdfPCell q7label = new PdfPCell(paragraph4);
		q7label.setBorder(0);
		q7label.setColspan(2);
		maintable.addCell(q7label);

//		PdfPCell q7labelValue = PDFUtils
//				.addPDFCellWithDefalutSize("Claimant’s last demand $ "
//						+ ClaimantLastDemandCi);
//		q7labelValue.setBorder(0);
//		q7labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		maintable.addCell(q7labelValue);
		
		maintable.addCell(blank);
		
		phrase1=new Phrase("Current Status: ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
		phrase2=new Phrase(CurrentStatus,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
		paragraph=new Paragraph();
		paragraph.add(phrase1);
		paragraph.add(phrase2);
		PdfPCell q7label2 = new PdfPCell(paragraph);
		q7label2.setBorder(0);
		q7label2.setColspan(2);
		maintable.addCell(q7label2);

		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));
		
		PdfPCell q8SNo = PDFUtils.addPDFCellWithDefalutSize("8.");
		q8SNo.setBorder(0);
		maintable.addCell(q8SNo);
		
		Phrase phrase11=new Phrase("If closed, provide: date closed  ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
		Phrase phrase12=new Phrase(ClosingCiDate,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
		Phrase phrase13=new Phrase(";  total amount paid (including damages and defense expenses): ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
		Phrase phrase14;
		if(!TotalClaimExpensesCi.equals("")){
			phrase14=new Phrase(PDFGenerator.formatter.format(new Double(TotalClaimExpensesCi)),new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));	
		}else{
			phrase14=new Phrase(TotalClaimExpensesCi,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
		}
		Paragraph paragraph5=new Paragraph();
		paragraph5.add(phrase11);
		paragraph5.add(phrase12);
		paragraph5.add(phrase13);
		paragraph5.add(phrase14);
		PdfPCell q8label = new PdfPCell(paragraph5);
		q8label.setBorder(0);
		q8label.setColspan(2);
		maintable.addCell(q8label);

//		PdfPCell q8labelValue = PDFUtils
//				.addPDFCellWithDefalutSize("total amount paid (including damages and defense expenses): $"
//						+ TotalClaimExpensesCi);
//		q8labelValue.setBorder(0);
//		q8labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		maintable.addCell(q8labelValue);

		maintable.addCell(PDFUtils.getBlankCustomSizeCell(10, 3, false));

		PdfPCell q9SNo = PDFUtils.addPDFCellWithDefalutSize("9.");
		q9SNo.setBorder(0);
		maintable.addCell(q9SNo);
		
		Phrase phrase15=new Phrase("What steps have been taken within your Firm to prevent similar claims? ",new Font(Font.TIMES_ROMAN,10,Font.NORMAL));
		Phrase phrase16=new Phrase(StepsTakenToPreventClaimCi,new Font(Font.TIMES_ROMAN,10,Font.BOLD,new Color(0.2f,0.2f,0.2f)));
		Paragraph paragraph6=new Paragraph();
		paragraph6.add(phrase15);
		paragraph6.add(phrase16);
		PdfPCell q9label = new PdfPCell(paragraph6);
		q9label.setBorder(0);
		q9label.setColspan(2);
		maintable.addCell(q9label);

//		PdfPCell q9labelValue = PDFUtils
//				.addPDFCellWithDefalutSize(StepsTakenToPreventClaimCi);
//		q9labelValue.setBorder(0);
//		q9labelValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		maintable.addCell(q9labelValue);

		// document.add(maintable);
		float availableSpace = (writer.getVerticalPosition(false)- document.bottomMargin()-15);
		maintable.setTotalWidth(500f);
		float height=0;
		for(int i=0;i<maintable.getRows().size();i++){
			height +=maintable.getRowHeight(i);
		}
		if(availableSpace<height){
			document.newPage();
		}
		return maintable;
	}

}
