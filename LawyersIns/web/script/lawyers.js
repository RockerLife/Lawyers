//loadsetERPOption();
function otherAop(Y) {
	try
	{
	var txtBox = document.getElementById("AOP_Percentage_10");
	if (txtBox.value != 0 && txtBox.value != null && txtBox.value != '') {
		showDivArea([ 'otherAopy' ], [ 'otherAopn' ]);
		// if(isSetFocus == 'Y')
		// document.getElementById("AOPCommentDesc_10").focus();
	} else {
		// if(document.getElementById("AOPCommentDesc_10"))
		// document.getElementById("AOPCommentDesc_10").innerHTML = "";

		showDivArea([ 'otherAopn' ], [ 'otherAopy' ]);

		if (document.getElementById("error_AOPCommentDesc_10"))
			document.getElementById("error_AOPCommentDesc_10").style.display = "none";
	}
	}
	catch(e)
	{}

}

document.writeln("<script type='text/javascript' src='js/masks.js'></script>");

function loadAppointmentFeeInformation(id) {
	var amountMask = new Mask("$#,###", "number");	
	var feeMask = new Mask("$#,###.##", "number")
	
	if(document.getElementById(id) != null)	
		var class1volume = document.getElementById(id);
	if (class1volume != null) {
		
		class1volume.value = amountMask.format(class1volume.value);
		amountMask.attach(class1volume);
	}
}


function showDivAreaStatusFreeform(new_Selected, prev_Selected, quote_Type) {
	//alert("new_Selected:"+new_Selected+" prev_Selected "+prev_Selected+" quote_Type "+quote_Type+"   "+document.getElementById('tbl_Quote').value);
	new_Selected = $.trim(new_Selected);
	if (new_Selected == '3') {
		if (document.getElementById('tbl_Quote') != null) {
			document.getElementById('tbl_Quote').style.display = 'block';
		}
		// alert('hi');
		document.getElementById('tbl_QuotedToIssuedAccepted').style.display = 'none';
//		document.getElementById('tbl_AcceptedToIssued').style.display = 'none';
		document.getElementById('tbl_Reason').style.display = 'none';
		// alert('bye');
	} else if (new_Selected == '4' || new_Selected == '5' || new_Selected == '8') {
		if (document.getElementById('tbl_Reason') != null) {
			if(new_Selected == '4'){
				$("select[id=ajax_field_Comment] option[value=relationship]").hide();
				$("select[id=ajax_field_Comment] option[value=noresponse]").hide();
				$("select[id=ajax_field_Comment] option[value=coverage]").hide();
				$("select[id=ajax_field_Comment] option[value=price]").hide();
				$("select[id=ajax_field_Comment] option[value=alreadyrenewed]").hide();
				
				$("select[id=ajax_field_Comment] option[value=aops]").show();
				$("select[id=ajax_field_Comment] option[value=claims_disciplinary]").show();
				$("select[id=ajax_field_Comment] option[value=non_renewed]").show();
				$("select[id=ajax_field_Comment] option[value=practice_management]").show();
				$("select[id=ajax_field_Comment] option[value=too_large]").show();
				$("select[id=ajax_field_Comment] option[value=underwriting_guidelines]").show();
				$("select[id=ajax_field_Comment] option[value=incomplete_application]").show();
			}
			if(new_Selected == '5'){
				
				$("select[id=ajax_field_Comment] option[value=aops]").hide();
				$("select[id=ajax_field_Comment] option[value=claims_disciplinary]").hide();
				$("select[id=ajax_field_Comment] option[value=non_renewed]").hide();
				$("select[id=ajax_field_Comment] option[value=practice_management]").hide();
				$("select[id=ajax_field_Comment] option[value=too_large]").hide();
				$("select[id=ajax_field_Comment] option[value=underwriting_guidelines]").hide();
				$("select[id=ajax_field_Comment] option[value=incomplete_application]").hide();
				
				$("select[id=ajax_field_Comment] option[value=relationship]").show();
				$("select[id=ajax_field_Comment] option[value=noresponse]").show();
				$("select[id=ajax_field_Comment] option[value=coverage]").show();
				$("select[id=ajax_field_Comment] option[value=price]").show();
				$("select[id=ajax_field_Comment] option[value=alreadyrenewed]").show();
			}
			document.getElementById('tbl_Reason').style.display = 'block';
		}
		document.getElementById('tbl_QuotedToIssuedAccepted').style.display = 'none';
		document.getElementById('tbl_Quote').style.display = 'none';
//		document.getElementById('tbl_AcceptedToIssued').style.display = 'none';
	} else if (new_Selected == '6') {
		if (prev_Selected == '3' && quote_Type == 'FQ') {
			if (document.getElementById('tbl_QuotedToIssuedAccepted') != null) {
				document.getElementById('tbl_QuotedToIssuedAccepted').style.display = 'block';
			}
//			if (document.getElementById('tbl_AcceptedToIssued') != null) {
//				document.getElementById('tbl_AcceptedToIssued').style.display = 'block';
//			}
			document.getElementById('tbl_Reason').style.display = 'none';
			document.getElementById('tbl_Quote').style.display = 'none';
		} else if (prev_Selected == '7' && quote_Type == 'FQ') {
//			if (document.getElementById('tbl_AcceptedToIssued') != null) {
//				document.getElementById('tbl_AcceptedToIssued').style.display = 'block';
//			}
			document.getElementById('tbl_QuotedToIssuedAccepted').style.display = 'none';
			document.getElementById('tbl_Reason').style.display = 'none';
			document.getElementById('tbl_Quote').style.display = 'none';
		}
	} else if (new_Selected == '7') {
		if (quote_Type == 'FQ') {
			if (document.getElementById('tbl_QuotedToIssuedAccepted') != null) {
				document.getElementById('tbl_QuotedToIssuedAccepted').style.display = 'block';
			}
		}
//		document.getElementById('tbl_AcceptedToIssued').style.display = 'none';
		document.getElementById('tbl_Reason').style.display = 'none';
		document.getElementById('tbl_Quote').style.display = 'none';
	} else if (new_Selected == '2') {
		document.getElementById('tbl_QuotedToIssuedAccepted').style.display = 'none';
		document.getElementById('tbl_Reason').style.display = 'none';
		document.getElementById('tbl_Quote').style.display = 'none';
//		document.getElementById('tbl_AcceptedToIssued').style.display = 'none';
	} else {
//		document.getElementById('tbl_AcceptedToIssued').style.display = 'none';
		document.getElementById('tbl_QuotedToIssuedAccepted').style.display = 'none';
		document.getElementById('tbl_Reason').style.display = 'none';
		document.getElementById('tbl_Quote').style.display = 'none';
	}

	// if(document.getElementById('updateStatusWithConfirmation').style.display=='block'){
	// document.getElementById('updateStatus').style.display='block';
	// document.getElementById('updateStatusWithConfirmation').style.display='none';
	// }
}

function populateYearEndDate(thisField) {
	var fieldName = thisField.name;
	var curVal = thisField.value;
	var curYear;
	var curMonth;
	// alert(fieldName);
	if (curVal != null && curVal != "") {
		if (chkFiscalYearFormat(curVal) == false) {
			// alert(fieldName);
			// document.forms[0].fieldName.select();
			thisField.select();
			alert("This date is invalid. Please enter again.");
			// document.getElementById("YearEndDate_0").focus();
			// setFocus('YearEndDate_0');
			thisField.focus();

			// document.forms[0].fieldName.focus();

			return false;
		} else if (fieldName == 'YearEndDate_0') {
			curYear = curVal.substring(curVal.indexOf("/") + 1, curVal.length);
			curMonth = curVal.substring(0, curVal.indexOf("/"));
			if (checkCurrentYear(curYear) == false) {
				// setTimeout(document.forms[0].fieldName.select(), 1);
				// thisField.select();
				alert("The Year should be current or next year or one year back. Please enter again.");
				// setFocus('Amount_0');
				thisField.focus();
				// thisField.select();

				return false;
			}
			var objName = curMonth + "/" + "01/" + curYear;
			var totalFields = getTotalNoOfField('YearEndDate_');

			for ( var i = 1; i < totalFields; i++) {

				var nextYear = curMonth + "/" + (parseInt(curYear) - i);
				document.getElementById("YearEndDate_" + i).value = nextYear;

			}
		}

	}
}

function checkCurrentYear(curYear) {
	var d = new Date();
	var curr_year = d.getFullYear();
	if (parseInt(curYear) < curr_year - 1 || parseInt(curYear) > curr_year + 1) {
		return false;
	}
}

function getTotalNoOfField(fieldPrefix) {
	var total = 0;
	for ( var i = 0; i < document.forms[0].length; i++) {
		var n = document.forms[0].elements[i].name;

		if (n != null && n.indexOf(fieldPrefix) > -1) {
			total = total + 1;
		}
	}
	return total;
}

function chkFiscalYearFormat(curVal) {
	var err = 0;
	var curYear;
	var curMonth;
	var firstIndex = curVal.indexOf("/");
	if (curVal.indexOf(".") >= 0) {
		err++;
		return false;
	}
	if (firstIndex == -1) {
		err++;
		return false;
	}
	var secondIndex = (curVal.substring(firstIndex + 1)).indexOf("/");
	if (secondIndex >= 0) {
		err++;
		return false;
	}
	curYear = curVal.substring(firstIndex + 1, curVal.length);
	curMonth = curVal.substring(0, firstIndex);

	if (curYear.length < 4) {
		err++;
		return false;
	}
	if (isNaN(curMonth)) {
		err++;
		return false;
	}
	if (isNaN(curYear)) {
		err++;
		return false;
	}
	if (curMonth.indexOf("0") == 0) {
		curMonth = curMonth.substring(1, curMonth.length);
	}
	var intYear = parseInt(curYear);
	var intMonth = parseInt(curMonth);

	if (isNaN(intMonth)) {
		err++;
		return false;
	}
	if (isNaN(intYear)) {
		err++;
		return false;
	}
	if (intMonth < 1 || intMonth > 12) {
		err++;
		return false;
	}
}

function limitAmountText(limitField, limitNum) {
	var myString = limitField.value;
	if (myString == '')
		return;

	var newString = "";
	for (i = 0; i < myString.length; i++) {
		var ch = myString.substring(i, i + 1);
		if (ch != ',' && ch != '$') {
			newString += ch;
		}
	}

	if (newString.length > limitNum) {
		newString = limitField.value.substring(0, limitNum);
	}

	var tempStr = "";
	for (j = 0; j < newString.length; j++) {
		var ch = newString.substring(j, j + 1);
		if (ch != '-') {
			tempStr += ch;
		} else if (j == 0 && ch == '-') {
			tempStr += ch;
		}
	}
	limitField.value = tempStr;
}

function populateTotalFieldValue(fieldPrefix, totalValContainerid) {
	
	var total = 0;
	var err = 0;
	var myVal;
	//alert("hello1"+fieldPrefix);
	//alert("hello2"+totalValContainerid);
	for ( var i = 0; i < document.forms[0].length; i++) {
		var n = document.forms[0].elements[i].name;
	
		if (n != null && n.indexOf(fieldPrefix) > -1) {
			//alert("hello");
			myVal = document.forms[0].elements[i].value;
			
			if (myVal != null && myVal != '') {
				
				if (!isNumberCorrect(myVal)) {
					//alert("That number is invalid.  Please enter again...");
					// document.forms[0].elements[i].select();
					// document.forms[0].elements[i].focus();
					return false;
				} else {
					//alert("helloelse");
					var ival = parseInt(myVal);
					if (n != "NumberOfPersonnel_1") {
						total = parseInt(total) + ival;
					} else {
						// total = parseInt( total);
					}
				}
			}
		}
		
	}
	
	document.getElementById(totalValContainerid).innerHTML = total;
	showOtherDesc();
	if (fieldPrefix == 'AOP_Percentage_') {
		if (total == 100) {
			document.getElementById(totalValContainerid).style.color = "blue";
			SetFocus('divshow');
		} else {
			document.getElementById(totalValContainerid).style.color = "red";
			//alert(total+' else');
		}
		// alert(total);
	}
	if (fieldPrefix == 'PercentageValue_') {
		
		if (total == 100) {
			document.getElementById(totalValContainerid).style.color = "blue";
			SetFocus('divshow');
		} else {
			document.getElementById(totalValContainerid).style.color = "red";
		}
		// alert(total);
	}
	if (fieldPrefix == 'CommercialPercentageValue_') {
		if (total == 100) {
			document.getElementById(totalValContainerid).style.color = "blue";
			SetFocus('divshow');
		} else {
			document.getElementById(totalValContainerid).style.color = "red";
		}
		// alert(total);
	}
	//alert(total);

	// document.getElementById("IsFirmHaveClientInEntertainmentInd").focus();
	
}


function populateTotalFieldValueForAOL(fieldPrefix, totalValContainerid) {
	var total = 0;
	var err = 0;
	var myVal;

	// alert(fieldPrefix);
	for ( var i = 0; i < document.forms[0].length; i++) {
		var n = document.forms[0].elements[i].name;
		if (n != null && n.indexOf(fieldPrefix) > -1) {
			myVal = document.forms[0].elements[i].value;
			if (myVal != null && myVal != '') {
				if (!isNumberCorrect(myVal)) {
					alert("That number is invalid.  Please enter again...");
					return false;
				} else {
					var ival = parseInt(myVal);
					if (n != "NumberOfPersonnel_1") {
						total = parseInt(total) + ival;
					}
				}
			}
		}
	}

	document.getElementById(totalValContainerid).innerHTML = total;

	if (fieldPrefix == 'AOL_PercentageOfRevenue_') {
		if (total == 100) {
			document.getElementById(totalValContainerid).className = "txt12BoldBlue";
		} else {
			document.getElementById(totalValContainerid).className = "txt12BoldRed";
		}
	}
}

function populateTotalFieldValueForAOLAverageSize(fieldPrefix,
		totalValContainerid) {
	var total = 0;
	var err = 0;
	var myVal;

	// alert(fieldPrefix);
	for ( var i = 0; i < document.forms[0].length; i++) {
		var n = document.forms[0].elements[i].name;
		if (n != null && n.indexOf(fieldPrefix) > -1) {
			myVal = document.forms[0].elements[i].value;
			if (myVal != null && myVal != '') {
				var myVal = filterNum(myVal);
				var ival = parseInt(myVal);
				total = parseInt(total) + ival;
			}
		}
	}
	total = numberFormat(total);
	document.getElementById(totalValContainerid).innerHTML = total;

	if (fieldPrefix == 'AOL_AverageCaseSize_') {
		if (total == 100) {
			// document.getElementById(totalValContainerid).className =
			// "txt12BoldBlue";
		} else {
			// document.getElementById(totalValContainerid).className =
			// "txt12BoldRed";
		}
	}
}

function numberFormat(nStr) {

	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1))
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	return x1 + x2;
}

function filterNum(str) {
	re = /^\$|,/g;
	// remove "$" and ","
	return str.replace(re, "");
}

function populateTotalFieldValueForAOLLargestSize(fieldPrefix,
		totalValContainerid) {
	var total = 0;
	var err = 0;
	var myVal;

	// alert(fieldPrefix);
	for ( var i = 0; i < document.forms[0].length; i++) {
		var n = document.forms[0].elements[i].name;
		if (n != null && n.indexOf(fieldPrefix) > -1) {
			myVal = document.forms[0].elements[i].value;
			if (myVal != null && myVal != '') {
				var myVal = filterNum(myVal);
				var ival = parseInt(myVal);
				total = parseInt(total) + ival;
			}
		}
	}
	total = numberFormat(total);
	document.getElementById(totalValContainerid).innerHTML = total;

	if (fieldPrefix == 'AOL_LargestCaseSize_') {
		if (total == 100) {
			// document.getElementById(totalValContainerid).className =
			// "txt12BoldBlue";
		} else {
			// document.getElementById(totalValContainerid).className =
			// "txt12BoldRed";
		}
	}
}

function populateTotalFieldValueForAOPRE(fieldPrefix, totalValContainerid) {
	var total = 0;
	var err = 0;
	var myVal;

	// alert(fieldPrefix);
	for ( var i = 0; i < document.forms[0].length; i++) {
		var n = document.forms[0].elements[i].name;
		if (n != null && n.indexOf(fieldPrefix) > -1) {
			myVal = document.forms[0].elements[i].value;
			if (myVal != null && myVal != '') {
				if (!isNumberCorrect(myVal)) {
					alert("That number is invalid.  Please enter again...");
					return false;
				} else {
					var ival = parseInt(myVal);
					if (n != "NumberOfPersonnel_1") {
						total = parseInt(total) + ival;
					}
				}
			}
		}
	}

	document.getElementById(totalValContainerid).innerHTML = total;

	if (fieldPrefix == 'AOPRE_Percentage_') {
		if (total == 100) {
			document.getElementById("TotalAOPRE_Percentage").className = "txt12BoldBlue";
		} else {
			document.getElementById("TotalAOPRE_Percentage").className = "txt12BoldRed";
		}
	}

}

function populateTotalFieldValueForAOTP(fieldPrefix, totalValContainerid) {
	var total = 0;
	var err = 0;
	var myVal;

	// alert(fieldPrefix);
	for ( var i = 0; i < document.forms[0].length; i++) {
		var n = document.forms[0].elements[i].name;
		if (n != null && n.indexOf(fieldPrefix) > -1) {
			myVal = document.forms[0].elements[i].value;
			if (myVal != null && myVal != '') {
				if (!isNumberCorrect(myVal)) {
					alert("That number is invalid.  Please enter again...");
					return false;
				} else {
					var ival = parseInt(myVal);
					if (n != "NumberOfPersonnel_1") {
						total = parseInt(total) + ival;
					}
				}
			}
		}
	}

	document.getElementById(totalValContainerid).innerHTML = total;

	if (fieldPrefix == 'AOTP_Percentage_') {
		if (total == 100) {
			document.getElementById(totalValContainerid).className = "txt12BoldBlue";
		} else {
			document.getElementById(totalValContainerid).className = "txt12BoldRed";
		}
	}
}

function isNumberCorrect(curVal) {
	if (curVal.indexOf(".") >= 0) {
		return false;
	}
	if (isNaN(curVal)) {
		return false;
	}
	return true;
}

function loadFirm() {
	
	//document.getElementById("YearEndDate_0").tabIndex = "101";
		//alert("Hi");
	if (getRadioValue(document.forms[0].IsApplicantProvidesLegalServices) == 'Y')
		showDivArea([ 'div8y' ], [ 'div8n' ]);
	if (getRadioValue(document.forms[0].IsIndependentContractor) == 'Y')
		showDivArea([ 'div4By' ], [ 'div4Bn' ]);
	if (getRadioValue(document.forms[0].IsFirmHaveBackupAttorney) == 'Y')
		showDivArea([ 'div4Cy' ], [ 'div4Cn' ]);
	openDivArea6();

	amountMask = new Mask("$#,###", "number");

	var amt0 = document.getElementById("Amount_0");
	var amt1 = document.getElementById("Amount_1");

	if (amt0 != null) {
		amountMask.attach(amt0);
		// if(amt0.value == '0')
		// amt0.value = '';
		// alert(amt0.value)
		amt0.value = amountMask.format(amt0.value);
	}
	// alert(amt0);
	if (amt1 != null) {
		amountMask.attach(amt1);
		// if(amt1.value == '0')
		// amt1.value = '';
		// alert(amt1.value)
		amt1.value = amountMask.format(amt1.value);
	}

	mmyyyyMask = new Mask("mm/yyyy", "date");

	var YearEndDate_0 = document.getElementById("YearEndDate_0");
	var YearEndDate_1 = document.getElementById("YearEndDate_1");

	if (YearEndDate_0 != null) {
		mmyyyyMask.attach(YearEndDate_0);
	}
	if (YearEndDate_1 != null) {
		mmyyyyMask.attach(YearEndDate_1);
	}

	mmddyyyyMask = new Mask("mm/dd/yyyy", "date");

	var YearEndDate_3 = document.getElementById("PolicyEffectiveDate");

	if (YearEndDate_3 != null) {
		mmddyyyyMask.attach(YearEndDate_3);
	}

	phoneMask = new Mask("(###)###-####");

	var wrkphone = document.getElementById("WorkPhone");
	var cntphone = document.getElementById("ContactPhone");
	var backupAttorneyPhoneNumber = document
			.getElementById("BackupAttorneyPhoneNumber");

	if (wrkphone != null) {
		wrkphone.value = phoneMask.format(wrkphone.value);
		phoneMask.attach(wrkphone);
	}
	if (cntphone != null) {
		cntphone.value = phoneMask.format(cntphone.value);
		phoneMask.attach(cntphone);
	}
	if (backupAttorneyPhoneNumber != null) {
		backupAttorneyPhoneNumber.value = phoneMask
				.format(backupAttorneyPhoneNumber.value);
		phoneMask.attach(backupAttorneyPhoneNumber);
	}
	numberMask = new Mask("###", "number");

	// var zip4 = document.getElementById("Zip4");
	var WorkExt = document.getElementById("WorkExt");
	var ContactPhoneExt = document.getElementById("ContactPhoneExt");
	var YearOfFirmEstablished = document
			.getElementById("YearOfFirmEstablished");
	var TotalNumOfNonAttorneyStaff = document
			.getElementById("TotalNumOfNonAttorneyStaff");
	var PerOfGrossBillingsUnderContract = document
			.getElementById("PerOfGrossBillingsUnderContract");

	/*
	 * if(zip4 != null){ numberMask.attach(zip4); }
	 * 
	 * if(WorkExt != null){ numberMask.attach(WorkExt); } if(ContactPhoneExt !=
	 * null){ numberMask.attach(ContactPhoneExt); }
	 */

	if (YearOfFirmEstablished != null) {
		numberMask.attach(YearOfFirmEstablished);
	}
	if (TotalNumOfNonAttorneyStaff != null) {
		numberMask.attach(TotalNumOfNonAttorneyStaff);
	}
	if (PerOfGrossBillingsUnderContract != null) {
		numberMask.attach(PerOfGrossBillingsUnderContract);
	}
}

function loadPractice() {
	openDivArea23();
	if (getRadioValue(document.forms[0].IsFirmHaveClientMoreThan25PercentOfBilling) == 'Y')
		showDivArea([ 'div1y' ], [ 'div1n' ]);
	if (getRadioValue(document.forms[0].IsApplInitiatedLawsuitForFirm) == 'Y')
		showDivArea([ 'div11y' ], [ 'div11n' ]);

	numberMask = new Mask("###", "number");

	var percentFirstLargest = document
			.getElementById("PercentFromFirstLargestRevenueClient");
	var percentSecondLargest = document
			.getElementById("PercentFromSecondLargestRevenueClient");

	if (percentFirstLargest != null)
		numberMask.attach(percentFirstLargest);
	if (percentSecondLargest != null)
		numberMask.attach(percentSecondLargest);

	var dateRenderedFirstClient = document
			.getElementById("DateRenderedFirstLargestRevenueClient");
	var dateRenderedSecondClient = document
			.getElementById("DateRenderedSecondLargestRevenueClient");

	if (dateRenderedFirstClient != null)
		numberMask.attach(dateRenderedFirstClient);
	if (dateRenderedSecondClient != null)
		numberMask.attach(dateRenderedSecondClient);

	/*
	 * if (getRadioValue(document.forms[0].IsPersonnelBeSubOfAnyInvest)=='Y')
	 * showDivArea(['div31ay'], ['div31an']); if
	 * (getRadioValue(document.forms[0].IsLawyerProfLiabClaimAgntAppl)=='Y')
	 * showDivArea(['divy'], ['divn']); if
	 * (getRadioValue(document.forms[0].IsAnyActOmmBecomeClaimAgntFirm)=='Y')
	 * showDivArea(['divy'], ['divn']); if
	 * (getRadioValue(document.forms[0].IsAttorneyDeclineForProfLiability)=='Y')
	 * showDivArea(['div32y'], ['div32n']); if
	 * (getRadioValue(document.forms[0].IsFirmPersServedAsOfficerInJointVenture)=='Y' ||
	 * getRadioValue(document.forms[0].IsFirmHaveEquityIntGrtThan10)=='Y')
	 * showDivArea(['divAy'], ['divAn']);
	 */

	mmddyyyyMask = new Mask("mm/dd/yyyy", "date");

	var ClaimNotifiedDate = document.getElementById("ClaimNotifiedDate");
	var DateOfAllegedError = document.getElementById("DateOfAllegedError");
	var DateReportedToInsComp = document
			.getElementById("DateReportedToInsComp");
	var ClaimClosingDate = document.getElementById("ClaimClosingDate");
	var PersonnelBeSubOfAnyInvestDate = document
			.getElementById("PersonnelBeSubOfAnyInvestDate");
	var AttorneyDeclineForProfLiabilityDates = document
			.getElementById("AttorneyDeclineForProfLiabilityDates");

	if (ClaimNotifiedDate != null) {
		// mmddyyyyMask.attach(ClaimNotifiedDate);
	}
	if (DateOfAllegedError != null) {
		// mmddyyyyMask.attach(DateOfAllegedError);
	}
	if (DateReportedToInsComp != null) {
		// mmddyyyyMask.attach(DateReportedToInsComp);
	}
	if (ClaimClosingDate != null) {
		// mmddyyyyMask.attach(ClaimClosingDate);
	}
	if (PersonnelBeSubOfAnyInvestDate != null) {
		// mmddyyyyMask.attach(PersonnelBeSubOfAnyInvestDate);
	}
	if (AttorneyDeclineForProfLiabilityDates != null) {
		// mmddyyyyMask.attach(AttorneyDeclineForProfLiabilityDates);
	}
	amountMask = new Mask("$#,###", "number");
	var amountFeesSued = document.getElementById("ajax_field_AmountOfFeesSued");

	if (amountFeesSued != null) {
		amountFeesSued.value = amountMask.format(amountFeesSued.value);
		amountMask.attach(amountFeesSued);
	}
	attachPercentageFormat(document.forms[0].PercentofApplAcctRcbl);
}

function loadCoverage() {
	showDivClaim();
	if (getRadioValue(document.forms[0].IsPersonnelBeSubOfAnyInvest) == 'Y')
		showDivArea([ 'div1Ay' ], [ 'div1An' ]);
	if (getRadioValue(document.forms[0].IsAttorneyDeclineForProfLiability) == 'Y')
		showDivArea([ 'div2y' ], [ 'div2n' ]);
	if (getRadioValue(document.forms[0].IsFirmHaveLawyersLiabilityInsurance) == 'Y')
		showDivArea([ 'div3y' ], [ 'div3n' ]);
	if (getRadioValue(document.forms[0].IsPolicyExcludesCoverage) == 'Y')
		showDivArea([ 'div5y' ], [ 'div5n' ]);
	if (getRadioValue(document.forms[0].IsFirmMergedWithOtherFirm) == 'Y')
		showDivArea([ 'div7y' ], [ 'div7n' ]);
	if (getRadioValue(document.forms[0].IsFirmCoverageForPreceedorFirms) == 'Y')
		showDivArea([ 'div7By' ], [ 'div7Bn' ]);
	if (getRadioValue(document.forms[0].IsPriorActDateFull) == 'Y')
		showDivArea([ 'DivIsPriorActDateFullY' ], [ 'DivIsPriorActDateFullN' ]);
	if (getRadioValue(document.forms[0].IsPriorActDateFull) == 'N')
		showDivArea([ 'DivIsPriorActDateFullN' ], [ 'DivIsPriorActDateFullY' ]);

	mmddyyyyMask = new Mask("mm/dd/yyyy", "date");

	var PolicyEffectiveDate = document.getElementById("PolicyEffectiveDate");
	var PersonnelBeSubOfAnyInvestDate = document
			.getElementById("PersonnelBeSubOfAnyInvestDate");
	var ClaimNotifiedDate = document.getElementById("ClaimNotifiedDate");
	var DateOfAllegedError = document.getElementById("DateOfAllegedError");
	var DateReportedToInsComp = document
			.getElementById("DateReportedToInsComp");
	var ClaimClosingDate = document.getElementById("ClaimClosingDate");
	var AttorneyDeclineForProfLiabilityDates = document
			.getElementById("AttorneyDeclineForProfLiabilityDates");
	var PriorActDatePross = document.getElementById("PriorActDatePross");
	var PolicyExpirationDatePross = document
			.getElementById("PolicyExpirationDatePross");
	var ERPExpDate = document.getElementById("ajax_field_ERPExpDate");

	if (PolicyEffectiveDate != null) {
		// mmddyyyyMask.attach(PolicyEffectiveDate);
	}
	if (PersonnelBeSubOfAnyInvestDate != null) {
		// mmddyyyyMask.attach(PersonnelBeSubOfAnyInvestDate);
	}
	if (ClaimNotifiedDate != null) {
		// mmddyyyyMask.attach(ClaimNotifiedDate);
	}
	if (DateOfAllegedError != null) {
		// mmddyyyyMask.attach(DateOfAllegedError);
	}
	if (DateReportedToInsComp != null) {
		// mmddyyyyMask.attach(DateReportedToInsComp);
	}
	if (ClaimClosingDate != null) {
		// mmddyyyyMask.attach(ClaimClosingDate);
	}
	if (AttorneyDeclineForProfLiabilityDates != null) {
		// mmddyyyyMask.attach(AttorneyDeclineForProfLiabilityDates);
	}
	if (PriorActDatePross != null) {
		// mmddyyyyMask.attach(PriorActDatePross);
	}
	if (PolicyExpirationDatePross != null) {
		// mmddyyyyMask.attach(PolicyExpirationDatePross);
	}
	if (ERPExpDate != null) {
		// mmddyyyyMask.attach(ERPExpDate);
	}

	amountMask = new Mask("$#,###", "number");
	var InsurerLoss = document.getElementById("InsurerLoss");
	var TotalAmountPaid = document.getElementById("TotalAmountPaid");
	var ClaimantLastDemand = document.getElementById("ClaimantLastDemand");
	var ProInsPremium = document.getElementById("ProInsPremium");

	if (InsurerLoss != null) {
		InsurerLoss.value = amountMask.format(InsurerLoss.value);
		amountMask.attach(InsurerLoss);
	}
	if (TotalAmountPaid != null) {
		TotalAmountPaid.value = amountMask.format(TotalAmountPaid.value);
		amountMask.attach(TotalAmountPaid);
	}
	if (ClaimantLastDemand != null) {
		ClaimantLastDemand.value = amountMask.format(ClaimantLastDemand.value);
		amountMask.attach(ClaimantLastDemand);
	}
	if (ProInsPremium != null) {
		ProInsPremium.value = amountMask.format(ProInsPremium.value);
		amountMask.attach(ProInsPremium);
	}
}

function loadAop() {
	pleaseDescribeAOP();

	if (getRadioValue(document.forms[0].IsFirmHaveClientInEntertainmentInd) == 'Y')
		showDivArea([ 'divAOP2y' ], [ 'divAOP2n' ]);
	if (getRadioValue(document.forms[0].IsBusinessRelationWithApplEnt) == 'Y'
			|| getRadioValue(document.forms[0].IsApplSoughtHaveAuthForEntertain) == 'Y'
			|| getRadioValue(document.forms[0].IsApplProvideInvestForEntertain) == 'Y'
			|| getRadioValue(document.forms[0].IsApplServedAsTrustee) == 'Y'
			|| getRadioValue(document.forms[0].IsApplNagotiatePersonnelApp) == 'Y') {
		showDivArea([ 'divBy' ], [ 'divBn' ]);
	}
	if (getRadioValue(document.forms[0].IsBusinessRelationWithApplEnt) == 'N'
			&& getRadioValue(document.forms[0].IsApplSoughtHaveAuthForEntertain) == 'N'
			&& getRadioValue(document.forms[0].IsApplProvideInvestForEntertain) == 'N'
			&& getRadioValue(document.forms[0].IsApplServedAsTrustee) == 'N'
			&& getRadioValue(document.forms[0].IsApplNagotiatePersonnelApp) == 'N') {
		showDivArea([ 'divBn' ], [ 'divBy' ]);
	}
	if (getRadioValue(document.forms[0].IsApplCovSoughtServeAsManager) == 'Y')
		showDivArea([ 'divCy' ], [ 'divCn' ]);
	if (getRadioValue(document.forms[0].IsApplCoverageAcceptCompensation) == 'Y')
		showDivArea([ 'divDy' ], [ 'divDn' ]);

	if (getRadioValue(document.forms[0].IsFirmProvidedLegalService) == 'Y')
		showDivArea([ 'div22y' ], [ 'div22n' ]);

	var aop1 = document.getElementById("AOP_Percentage_1");
	var aop2 = document.getElementById("AOP_Percentage_2");
	var aop3 = document.getElementById("AOP_Percentage_3");
	var aop4 = document.getElementById("AOP_Percentage_4");
	var aop5 = document.getElementById("AOP_Percentage_5");
	var aop6 = document.getElementById("AOP_Percentage_6");
	var aop7 = document.getElementById("AOP_Percentage_7");
	var aop8 = document.getElementById("AOP_Percentage_8");
	var aop9 = document.getElementById("AOP_Percentage_9");
	var aop10 = document.getElementById("AOP_Percentage_10");
	var aop11 = document.getElementById("AOP_Percentage_11");
	var aop12 = document.getElementById("AOP_Percentage_12");
	var aop13 = document.getElementById("AOP_Percentage_13");
	var aop14 = document.getElementById("AOP_Percentage_14");
	var aop15 = document.getElementById("AOP_Percentage_15");
	var aop16 = document.getElementById("AOP_Percentage_16");
	var aop17 = document.getElementById("AOP_Percentage_17");
	var aop18 = document.getElementById("AOP_Percentage_18");
	var aop19 = document.getElementById("AOP_Percentage_19");
	var aop20 = document.getElementById("AOP_Percentage_20");
	var aop21 = document.getElementById("AOP_Percentage_21");
	var aop22 = document.getElementById("AOP_Percentage_22");
	var aop23 = document.getElementById("AOP_Percentage_23");
	var aop24 = document.getElementById("AOP_Percentage_24");
	var aop25 = document.getElementById("AOP_Percentage_25");
	var aop26 = document.getElementById("AOP_Percentage_26");
	var aop27 = document.getElementById("AOP_Percentage_27");

	numberMask = new Mask("###", "number");
	if (aop1 != null) {
		numberMask.attach(aop1);
		if (aop1.value == '0')
			aop1.value = '';
	}
	if (aop2 != null) {
		numberMask.attach(aop2);
		if (aop2.value == '0')
			aop2.value = '';
	}
	if (aop3 != null) {
		numberMask.attach(aop3);
		if (aop3.value == '0')
			aop3.value = '';
	}
	if (aop4 != null) {
		numberMask.attach(aop4);
		if (aop4.value == '0')
			aop4.value = '';
	}
	if (aop5 != null) {
		numberMask.attach(aop5);
		if (aop5.value == '0')
			aop5.value = '';
	}
	if (aop6 != null) {
		numberMask.attach(aop6);
		if (aop6.value == '0')
			aop6.value = '';
	}
	if (aop7 != null) {
		numberMask.attach(aop7);
		if (aop7.value == '0')
			aop7.value = '';
	}
	if (aop8 != null) {
		numberMask.attach(aop8);
		if (aop8.value == '0')
			aop8.value = '';
	}
	if (aop9 != null) {
		numberMask.attach(aop9);
		if (aop9.value == '0')
			aop9.value = '';
	}
	if (aop10 != null) {
		numberMask.attach(aop10);
		if (aop10.value == '0')
			aop10.value = '';
	}
	if (aop11 != null) {
		numberMask.attach(aop11);
		if (aop11.value == '0')
			aop11.value = '';
	}
	if (aop12 != null) {
		numberMask.attach(aop12);
		if (aop12.value == '0')
			aop12.value = '';
	}
	if (aop13 != null) {
		numberMask.attach(aop13);
		if (aop13.value == '0')
			aop13.value = '';
	}
	if (aop14 != null) {
		numberMask.attach(aop14);
		if (aop14.value == '0')
			aop14.value = '';
	}
	if (aop15 != null) {
		numberMask.attach(aop15);
		if (aop15.value == '0')
			aop15.value = '';
	}
	if (aop16 != null) {
		numberMask.attach(aop16);
		if (aop16.value == '0')
			aop16.value = '';
	}
	if (aop17 != null) {
		numberMask.attach(aop17);
		if (aop17.value == '0')
			aop17.value = '';
	}
	if (aop18 != null) {
		numberMask.attach(aop18);
		if (aop18.value == '0')
			aop18.value = '';
	}
	if (aop19 != null) {
		numberMask.attach(aop19);
		if (aop19.value == '0')
			aop19.value = '';
	}
	if (aop20 != null) {
		numberMask.attach(aop20);
		if (aop20.value == '0')
			aop20.value = '';
	}
	if (aop21 != null) {
		numberMask.attach(aop21);
		if (aop21.value == '0')
			aop21.value = '';
	}
	if (aop22 != null) {
		numberMask.attach(aop22);
		if (aop22.value == '0')
			aop22.value = '';
	}
	if (aop23 != null) {
		numberMask.attach(aop23);
		if (aop23.value == '0')
			aop23.value = '';
	}
	if (aop24 != null) {
		numberMask.attach(aop24);
		if (aop24.value == '0')
			aop24.value = '';
	}
	if (aop25 != null) {
		numberMask.attach(aop25);
		if (aop25.value == '0')
			aop25.value = '';
	}
	if (aop26 != null) {
		numberMask.attach(aop26);
		if (aop26.value == '0')
			aop26.value = '';
	}
	if (aop27 != null) {
		numberMask.attach(aop27);
		if (aop27.value == '0')
			aop27.value = '';
	}

}

function loadEntertainmentSports() {
	if (getRadioValue(document.forms[0].IsBusinessRelationWithApplEnt) == 'Y'
			|| getRadioValue(document.forms[0].IsApplSoughtHaveAuthForEntertain) == 'Y'
			|| getRadioValue(document.forms[0].IsApplProvideInvestForEntertain) == 'Y'
			|| getRadioValue(document.forms[0].IsApplServedAsTrustee) == 'Y'
			|| getRadioValue(document.forms[0].IsApplNagotiatePersonnelApp) == 'Y') {
		showDivArea([ 'divBy' ], [ 'divBn' ]);
	}
	if (getRadioValue(document.forms[0].IsApplCovSoughtServeAsManager) == 'Y')
		showDivArea([ 'divCy' ], [ 'divCn' ]);
	if (getRadioValue(document.forms[0].IsApplCoverageAcceptCompensation) == 'Y')
		showDivArea([ 'divDy' ], [ 'divDn' ]);
}

function loadTax() {
	if (document.forms[0].AOTP_Percentage_1 == null) {
		setTimeout(loadTax, 5);
	} else {
		loadTax1();
		loadTaxAttorney();
		setScroll();
	}
}
function loadTax1() {
	var aop1 = document.forms[0].AOTP_Percentage_1;
	var aop2 = document.forms[0].AOTP_Percentage_2;
	var aop3 = document.forms[0].AOTP_Percentage_3;
	var aop4 = document.forms[0].AOTP_Percentage_4;
	var aop5 = document.forms[0].AOTP_Percentage_5;
	var aop6 = document.forms[0].AOTP_Percentage_6;
	var aop7 = document.forms[0].AOTP_Percentage_7;
	var aop8 = document.forms[0].AOTP_Percentage_8;
	var aop9 = document.forms[0].AOTP_Percentage_9;

	numberMask = new Mask("###", "number");
	if (aop1 != null) {
		numberMask.attach(aop1);
		if (aop1.value == '0')
			aop1.value = '';
	}
	if (aop2 != null) {
		numberMask.attach(aop2);
		if (aop2.value == '0')
			aop2.value = '';
	}
	if (aop3 != null) {
		numberMask.attach(aop3);
		if (aop3.value == '0')
			aop3.value = '';
	}
	if (aop4 != null) {
		numberMask.attach(aop4);
		if (aop4.value == '0')
			aop4.value = '';
	}
	if (aop5 != null) {
		numberMask.attach(aop5);
		if (aop5.value == '0')
			aop5.value = '';
	}
	if (aop6 != null) {
		numberMask.attach(aop6);
		if (aop6.value == '0')
			aop6.value = '';
	}
	if (aop7 != null) {
		numberMask.attach(aop7);
		if (aop7.value == '0')
			aop7.value = '';
	}
	if (aop8 != null) {
		numberMask.attach(aop8);
		if (aop8.value == '0')
			aop8.value = '';
	}
	if (aop9 != null) {
		numberMask.attach(aop9);
		if (aop9.value == '0')
			aop9.value = '';
	}
}

function loadEnvironmental() {

	if (document.forms[0].IsApplRenderLiabForCleanupExp == null) {
		setTimeout(loadEnvironmental, 5);
	} else {
		loadEnvAttorney();
		loadEnvContractor();
		setScroll();
	}
}

function loadSecurities() {
	if (getRadioValue(document.forms[0].IsNoConflictWithSecurityMatters) == 'N'
			|| getRadioValue(document.forms[0].IsAppSeekSecAdvRelToTransaction) == 'N')
		showDivArea([ 'divAy' ], [ 'divAn' ]);
	if (getRadioValue(document.forms[0].IsAppReqAttorneyToIntervClientDir) == 'N'
			|| getRadioValue(document.forms[0].IsAppReqRecordsOfFactualsource) == 'N'
			|| getRadioValue(document.forms[0].IsAppProhibitPolicyFromSecProcess) == 'N')
		showDivArea([ 'divBy' ], [ 'divBn' ]);
	if (getRadioValue(document.forms[0].IsAppProhibitSecAttorWithInvest) == 'N'
			|| getRadioValue(document.forms[0].IsAppProhibitFirmFromTrading) == 'N'
			|| getRadioValue(document.forms[0].IsAppReqSecToDissolveAllInvest) == 'N'
			|| getRadioValue(document.forms[0].IsAppHavePolicyGovernTrading) == 'N'
			|| getRadioValue(document.forms[0].IsAppPreventAttorneysStaff) == 'N'
			|| getRadioValue(document.forms[0].IsAppPrecludingInDisclosureDocs) == 'N'
			|| getRadioValue(document.forms[0].IsAppProhibitAttorneyOfDirector) == 'N'
			|| getRadioValue(document.forms[0].IsAppProhSecPaysForAppServices) == 'N')
		showDivArea([ 'divCy' ], [ 'divCn' ]);
	if (getRadioValue(document.forms[0].IsAppSubjectOfInvestBySEC) == 'Y')
		showDivArea([ 'divDy' ], [ 'divDn' ]);
	if (getRadioValue(document.forms[0].IsAppProvLegalServiceFor1933Act) == 'Y')
		showDivArea([ 'div5y' ], [ 'div5n' ]);
	if (getRadioValue(document.forms[0].IsSecurityIssuedByBank) == 'Y'
			|| getRadioValue(document.forms[0].IsSecurityIssuedByLoanInstitute) == 'Y')
		showDivArea([ 'divFy' ], [ 'divFn' ]);
	if (getRadioValue(document.forms[0].IsAppProvLegalServiceForInsuranceBonds) == 'Y')
		showDivArea([ 'divGy' ], [ 'divGn' ]);
}

function loadWillsEstate() {

	if (document.forms[0].NumberOfEstateOver1Million == null) {
		setTimeout(loadWillsEstate, 5);
	} else {
		loadWillsEstate1();
	}
}
function loadWillsEstate1() {
	// setScroll();
	numberMask = new Mask("###", "number");
	var NumberOfEstateOver1Million = document.forms[0].NumberOfEstateOver1Million;
	var NumberOfEstateOver5Million = document.forms[0].NumberOfEstateOver5Million;
	var NumberOfEstateOver10Million = document.forms[0].NumberOfEstateOver10Million;

	if (NumberOfEstateOver1Million != null) {
		numberMask.attach(NumberOfEstateOver1Million);
	}
	if (NumberOfEstateOver5Million != null) {
		numberMask.attach(NumberOfEstateOver5Million);
	}
	if (NumberOfEstateOver10Million != null) {
		numberMask.attach(NumberOfEstateOver10Million);
	}
	if (getRadioValue(document.forms[0].IsApplHaveControlOverFunds) == 'Y')
		showDivArea([ 'divControlFundY' ], [ 'divControlFundN' ]);

	if (getRadioValue(document.forms[0].IsApplHaveControlOverFunds) == 'Y')
		showDivArea([ 'divControlFundY' ], [ 'divControlFundN' ]);

	if (document.forms[0].WES_CheckedValue_12.checked == true) {
		document.getElementById("divWillsOther").style.display = 'block';
		document.getElementById("divWillsOtherN").style.display = 'none';
	} else {
		document.getElementById("divWillsOtherN").style.display = 'block';
		document.getElementById("divWillsOther").style.display = 'none';
	}

}

function loadPlaintiff() {

	pleaseDescribeAOL();

	if (document.forms[0].NumberOfSuppotedStaffToPlantiff == null) {
		setTimeout(loadPlaintiff, 5);
	} else {
		loadPlaintiff1();
		loadPlaintiffAttorney();

	}
}
function loadPlaintiff1() {
	// setScroll();
	numberMask = new Mask("###", "number");
	var NumberOfSuppotedStaffToPlantiff = document.forms[0].NumberOfSuppotedStaffToPlantiff;
	var NumberOfInjuryCasesIn12Month = document.forms[0].NumberOfInjuryCasesIn12Month;
	var NumberOfInjuryHandleByAttorney = document.forms[0].NumberOfInjuryHandleByAttorney;
	var PerOfCasesBeforeTrail = document.forms[0].PerOfCasesBeforeTrail;
	var PerOfCasesTriedToConclusion = document.forms[0].PerOfCasesTriedToConclusion;
	var PerOfCasesReferToApplicant = document.forms[0].PerOfCasesReferToApplicant;
	var PerOfCasesSettledBeforeTrail = document.forms[0].PerOfCasesSettledBeforeTrail;

	if (NumberOfSuppotedStaffToPlantiff != null) {
		numberMask.attach(NumberOfSuppotedStaffToPlantiff);
	}
	if (NumberOfInjuryCasesIn12Month != null) {
		numberMask.attach(NumberOfInjuryCasesIn12Month);
	}
	if (NumberOfInjuryHandleByAttorney != null) {
		numberMask.attach(NumberOfInjuryHandleByAttorney);
	}
	if (PerOfCasesBeforeTrail != null) {
		numberMask.attach(PerOfCasesBeforeTrail);
	}
	if (PerOfCasesTriedToConclusion != null) {
		numberMask.attach(PerOfCasesTriedToConclusion);
	}
	if (PerOfCasesReferToApplicant != null) {
		numberMask.attach(PerOfCasesReferToApplicant);
	}
	if (PerOfCasesSettledBeforeTrail != null) {
		numberMask.attach(PerOfCasesSettledBeforeTrail);
	}

	var aop1 = document.forms[0].AOL_PercentageOfRevenue_1;
	var aop2 = document.forms[0].AOL_PercentageOfRevenue_2;
	var aop3 = document.forms[0].AOL_PercentageOfRevenue_3;
	var aop4 = document.forms[0].AOL_PercentageOfRevenue_4;
	var aop5 = document.forms[0].AOL_PercentageOfRevenue_5;
	var aop6 = document.forms[0].AOL_PercentageOfRevenue_6;
	var aop7 = document.forms[0].AOL_PercentageOfRevenue_7;
	var aop8 = document.forms[0].AOL_PercentageOfRevenue_8;
	var aop9 = document.forms[0].AOL_PercentageOfRevenue_9;
	var aop10 = document.forms[0].AOL_PercentageOfRevenue_10;
	var aop11 = document.forms[0].AOL_PercentageOfRevenue_11;
	var aop12 = document.forms[0].AOL_PercentageOfRevenue_12;
	var aop13 = document.forms[0].AOL_PercentageOfRevenue_13;

	numberMask = new Mask("###", "number");
	if (aop1 != null) {
		numberMask.attach(aop1);
		if (aop1.value == '0')
			aop1.value = '';
	}
	if (aop2 != null) {
		numberMask.attach(aop2);
		if (aop2.value == '0')
			aop2.value = '';
	}
	if (aop3 != null) {
		numberMask.attach(aop3);
		if (aop3.value == '0')
			aop3.value = '';
	}
	if (aop4 != null) {
		numberMask.attach(aop4);
		if (aop4.value == '0')
			aop4.value = '';
	}
	if (aop5 != null) {
		numberMask.attach(aop5);
		if (aop5.value == '0')
			aop5.value = '';
	}
	if (aop6 != null) {
		numberMask.attach(aop6);
		if (aop6.value == '0')
			aop6.value = '';
	}
	if (aop7 != null) {
		numberMask.attach(aop7);
		if (aop7.value == '0')
			aop7.value = '';
	}
	if (aop8 != null) {
		numberMask.attach(aop8);
		if (aop8.value == '0')
			aop8.value = '';
	}
	if (aop9 != null) {
		numberMask.attach(aop9);
		if (aop9.value == '0')
			aop9.value = '';
	}
	if (aop10 != null) {
		numberMask.attach(aop10);
		if (aop10.value == '0')
			aop10.value = '';
	}
	if (aop11 != null) {
		numberMask.attach(aop11);
		if (aop11.value == '0')
			aop11.value = '';
	}
	if (aop12 != null) {
		numberMask.attach(aop12);
		if (aop12.value == '0')
			aop12.value = '';
	}
	if (aop13 != null) {
		numberMask.attach(aop13);
		if (aop13.value == '0')
			aop13.value = '';
	}

	var aola1 = document.forms[0].AOL_AverageCaseSize_1;
	var aola2 = document.forms[0].AOL_AverageCaseSize_2;
	var aola3 = document.forms[0].AOL_AverageCaseSize_3;
	var aola4 = document.forms[0].AOL_AverageCaseSize_4;
	var aola5 = document.forms[0].AOL_AverageCaseSize_5;
	var aola6 = document.forms[0].AOL_AverageCaseSize_6;
	var aola7 = document.forms[0].AOL_AverageCaseSize_7;
	var aola8 = document.forms[0].AOL_AverageCaseSize_8;
	var aola9 = document.forms[0].AOL_AverageCaseSize_9;
	var aola10 = document.forms[0].AOL_AverageCaseSize_10;
	var aola11 = document.forms[0].AOL_AverageCaseSize_11;
	var aola12 = document.forms[0].AOL_AverageCaseSize_12;
	var aola13 = document.forms[0].AOL_AverageCaseSize_13;
	var TotalAOL_AverageCaseSize = document.forms[0].TotalAOL_AverageCaseSize;

	amountMask = new Mask("$#,###", "number");
	if (aola1 != null) {
		aola1.value = amountMask.format(aola1.value);
		amountMask.attach(aola1);
	}
	amountMask = new Mask("$#,###", "number");
	if (aola2 != null) {
		aola2.value = amountMask.format(aola2.value);
		amountMask.attach(aola2);
	}
	amountMask = new Mask("$#,###", "number");
	if (aola3 != null) {
		aola3.value = amountMask.format(aola3.value);
		amountMask.attach(aola3);
	}
	amountMask = new Mask("$#,###", "number");
	if (aola4 != null) {
		aola4.value = amountMask.format(aola4.value);
		amountMask.attach(aola4);
	}
	amountMask = new Mask("$#,###", "number");
	if (aola5 != null) {
		aola5.value = amountMask.format(aola5.value);
		amountMask.attach(aola5);
	}
	amountMask = new Mask("$#,###", "number");
	if (aola6 != null) {
		aola6.value = amountMask.format(aola6.value);
		amountMask.attach(aola6);
	}
	amountMask = new Mask("$#,###", "number");
	if (aola7 != null) {
		aola7.value = amountMask.format(aola7.value);
		amountMask.attach(aola7);
	}
	amountMask = new Mask("$#,###", "number");
	if (aola8 != null) {
		aola8.value = amountMask.format(aola8.value);
		amountMask.attach(aola8);
	}
	amountMask = new Mask("$#,###", "number");
	if (aola9 != null) {
		aola9.value = amountMask.format(aola9.value);
		amountMask.attach(aola9);
	}
	amountMask = new Mask("$#,###", "number");
	if (aola10 != null) {
		aola10.value = amountMask.format(aola10.value);
		amountMask.attach(aola10);
	}
	amountMask = new Mask("$#,###", "number");
	if (aola11 != null) {
		aola11.value = amountMask.format(aola11.value);
		amountMask.attach(aola11);
	}
	amountMask = new Mask("$#,###", "number");
	if (aola12 != null) {
		aola12.value = amountMask.format(aola12.value);
		amountMask.attach(aola12);
	}
	amountMask = new Mask("$#,###", "number");
	if (aola13 != null) {
		aola13.value = amountMask.format(aola13.value);
		amountMask.attach(aola13);
	}
	amountMask = new Mask("$#,###", "number");
	if (TotalAOL_AverageCaseSize != null) {
		TotalAOL_AverageCaseSize.value = amountMask
				.format(TotalAOL_AverageCaseSize.value);
		amountMask.attach(TotalAOL_AverageCaseSize);
	}

	var aosa1 = document.forms[0].AOL_LargestCaseSize_1;
	var aosa2 = document.forms[0].AOL_LargestCaseSize_2;
	var aosa3 = document.forms[0].AOL_LargestCaseSize_3;
	var aosa4 = document.forms[0].AOL_LargestCaseSize_4;
	var aosa5 = document.forms[0].AOL_LargestCaseSize_5;
	var aosa6 = document.forms[0].AOL_LargestCaseSize_6;
	var aosa7 = document.forms[0].AOL_LargestCaseSize_7;
	var aosa8 = document.forms[0].AOL_LargestCaseSize_8;
	var aosa9 = document.forms[0].AOL_LargestCaseSize_9;
	var aosa10 = document.forms[0].AOL_LargestCaseSize_10;
	var aosa11 = document.forms[0].AOL_LargestCaseSize_11;
	var aosa12 = document.forms[0].AOL_LargestCaseSize_12;
	var aosa13 = document.forms[0].AOL_LargestCaseSize_13;
	var TotalAOL_LargestCaseSize = document.forms[0].TotalAOL_LargestCaseSize;

	amountMask = new Mask("$#,###", "number");
	if (aosa1 != null) {
		aosa1.value = amountMask.format(aosa1.value);
		amountMask.attach(aosa1);
	}
	amountMask = new Mask("$#,###", "number");
	if (aosa2 != null) {
		aosa2.value = amountMask.format(aosa2.value);
		amountMask.attach(aosa2);
	}
	amountMask = new Mask("$#,###", "number");
	if (aosa3 != null) {
		aosa3.value = amountMask.format(aosa3.value);
		amountMask.attach(aosa3);
	}
	amountMask = new Mask("$#,###", "number");
	if (aosa4 != null) {
		aosa4.value = amountMask.format(aosa4.value);
		amountMask.attach(aosa4);
	}
	amountMask = new Mask("$#,###", "number");
	if (aosa5 != null) {
		aosa5.value = amountMask.format(aosa5.value);
		amountMask.attach(aosa5);
	}
	amountMask = new Mask("$#,###", "number");
	if (aosa6 != null) {
		aosa6.value = amountMask.format(aosa6.value);
		amountMask.attach(aosa6);
	}
	amountMask = new Mask("$#,###", "number");
	if (aosa7 != null) {
		aosa7.value = amountMask.format(aosa7.value);
		amountMask.attach(aosa7);
	}
	amountMask = new Mask("$#,###", "number");
	if (aosa8 != null) {
		aosa8.value = amountMask.format(aosa8.value);
		amountMask.attach(aosa8);
	}
	amountMask = new Mask("$#,###", "number");
	if (aosa9 != null) {
		aosa9.value = amountMask.format(aosa9.value);
		amountMask.attach(aosa9);
	}
	amountMask = new Mask("$#,###", "number");
	if (aosa10 != null) {
		aosa10.value = amountMask.format(aosa10.value);
		amountMask.attach(aosa10);
	}
	amountMask = new Mask("$#,###", "number");
	if (aosa11 != null) {
		aosa11.value = amountMask.format(aosa11.value);
		amountMask.attach(aosa11);
	}
	amountMask = new Mask("$#,###", "number");
	if (aosa12 != null) {
		aosa12.value = amountMask.format(aosa12.value);
		amountMask.attach(aosa12);
	}
	amountMask = new Mask("$#,###", "number");
	if (aosa13 != null) {
		aosa13.value = amountMask.format(aosa13.value);
		amountMask.attach(aosa13);
	}
	amountMask = new Mask("$#,###", "number");
	if (TotalAOL_LargestCaseSize != null) {
		TotalAOL_LargestCaseSize.value = amountMask
				.format(TotalAOL_LargestCaseSize.value);
		amountMask.attach(TotalAOL_LargestCaseSize);
	}

}

function loadRealEstate() {
	if (document.forms[0].ApproxNumberOfPurchaseForResi == null) {
		setTimeout(loadRealEstate, 5);
	} else {
		loadRealEstate1();
		setScroll();
	}
}
function loadRealEstate1() {

	numberMask = new Mask("###", "number");
	var ApproxNumberOfPurchaseForResi = document.forms[0].ApproxNumberOfPurchaseForResi;
	var ApproxNumberOfPurchaseForComm = document.forms[0].ApproxNumberOfPurchaseForComm;

	if (ApproxNumberOfPurchaseForResi != null) {
		numberMask.attach(ApproxNumberOfPurchaseForResi);
	}
	if (ApproxNumberOfPurchaseForComm != null) {
		numberMask.attach(ApproxNumberOfPurchaseForComm);
	}

	var aop1 = document.forms[0].AOPRE_Percentage_1;
	var aop2 = document.forms[0].AOPRE_Percentage_2;
	var aop3 = document.forms[0].AOPRE_Percentage_3;
	var aop4 = document.forms[0].AOPRE_Percentage_4;
	var aop5 = document.forms[0].AOPRE_Percentage_5;
	var aop6 = document.forms[0].AOPRE_Percentage_6;
	var aop7 = document.forms[0].AOPRE_Percentage_7;
	var aop8 = document.forms[0].AOPRE_Percentage_8;
	var aop9 = document.forms[0].AOPRE_Percentage_9;
	var aop10 = document.forms[0].AOPRE_Percentage_10;
	var aop11 = document.forms[0].AOPRE_Percentage_11;
	var aop12 = document.forms[0].AOPRE_Percentage_12;
	var aop13 = document.forms[0].AOPRE_Percentage_13;
	var aop14 = document.forms[0].AOPRE_Percentage_14;
	var aop15 = document.forms[0].AOPRE_Percentage_15;
	var aop16 = document.forms[0].AOPRE_Percentage_16;
	var aop17 = document.forms[0].AOPRE_Percentage_17;

	numberMask = new Mask("###", "number");
	if (aop1 != null) {
		numberMask.attach(aop1);
		if (aop1.value == '0')
			aop1.value = '';
	}
	if (aop2 != null) {
		numberMask.attach(aop2);
		if (aop2.value == '0')
			aop2.value = '';
	}
	if (aop3 != null) {
		numberMask.attach(aop3);
		if (aop3.value == '0')
			aop3.value = '';
	}
	if (aop4 != null) {
		numberMask.attach(aop4);
		if (aop4.value == '0')
			aop4.value = '';
	}
	if (aop5 != null) {
		numberMask.attach(aop5);
		if (aop5.value == '0')
			aop5.value = '';
	}
	if (aop6 != null) {
		numberMask.attach(aop6);
		if (aop6.value == '0')
			aop6.value = '';
	}
	if (aop7 != null) {
		numberMask.attach(aop7);
		if (aop7.value == '0')
			aop7.value = '';
	}
	if (aop8 != null) {
		numberMask.attach(aop8);
		if (aop8.value == '0')
			aop8.value = '';
	}
	if (aop9 != null) {
		numberMask.attach(aop9);
		if (aop9.value == '0')
			aop9.value = '';
	}
	if (aop10 != null) {
		numberMask.attach(aop10);
		if (aop10.value == '0')
			aop10.value = '';
	}
	if (aop11 != null) {
		numberMask.attach(aop11);
		if (aop11.value == '0')
			aop11.value = '';
	}
	if (aop12 != null) {
		numberMask.attach(aop12);
		if (aop12.value == '0')
			aop12.value = '';
	}
	if (aop13 != null) {
		numberMask.attach(aop13);
		if (aop13.value == '0')
			aop13.value = '';
	}
	if (aop14 != null) {
		numberMask.attach(aop14);
		if (aop14.value == '0')
			aop14.value = '';
	}
	if (aop15 != null) {
		numberMask.attach(aop15);
		if (aop15.value == '0')
			aop15.value = '';
	}
	if (aop16 != null) {
		numberMask.attach(aop16);
		if (aop16.value == '0')
			aop16.value = '';
	}
	if (aop17 != null) {
		numberMask.attach(aop17);
		if (aop17.value == '0')
			aop17.value = '';
	}

	var LargestPurchaseForResi = document.forms[0].LargestPurchaseForResi;
	var LasrgestPurchaseForComm = document.forms[0].LasrgestPurchaseForComm;

	amountMask = new Mask("$#,###", "number");
	if (LargestPurchaseForResi != null) {
		LargestPurchaseForResi.value = amountMask
				.format(LargestPurchaseForResi.value);
		amountMask.attach(LargestPurchaseForResi);
	}
	amountMask = new Mask("$#,###", "number");
	if (LasrgestPurchaseForComm != null) {
		LasrgestPurchaseForComm.value = amountMask
				.format(LasrgestPurchaseForComm.value);
		amountMask.attach(LasrgestPurchaseForComm);
	}
}

function loadFinancialIns() {

	if (document.forms[0].IsApplServedAsCEOCouncel == null) {
		setTimeout(loadFinancialIns, 5);
	} else {

		loadFinanInst();
		setScroll();
	}
}

function loadTitle() {

	if (document.forms[0].PercentageOfEquityInt == null) {
		setTimeout(loadTitle, 5);
	} else {
		loadTitle1();
		setScroll();
	}
}
function loadTitle1() {
	numberMask = new Mask("###", "number");
	var PercentageOfEquityInt = document.forms[0].PercentageOfEquityInt;
	var NumberOfTitleAttInFirm = document.forms[0].NumberOfTitleAttInFirm;
	var NumberOfTitleNonAttInFirm = document.forms[0].NumberOfTitleNonAttInFirm;
	var NumberOfTitleAttNotInFirm = document.forms[0].NumberOfTitleAttNotInFirm;
	var NumberOfTitleNonAttSubCont = document.forms[0].NumberOfTitleNonAttSubCont;
	var NumberOfRealEstTitleIns = document.forms[0].NumberOfRealEstTitleIns;
	var PercentOfTitleFromResi = document.forms[0].PercentOfTitleFromResi;
	var PercentOfTitleFromAgri = document.forms[0].PercentOfTitleFromAgri;
	var PercentOfTitleFromComm = document.forms[0].PercentOfTitleFromComm;
	var PercentOfTitleFromOther = document.forms[0].PercentOfTitleFromOther;
	var NumberOfTitleIssued = document.forms[0].NumberOfTitleIssued;

	if (PercentageOfEquityInt != null) {
		numberMask.attach(PercentageOfEquityInt);
	}
	if (NumberOfTitleAttInFirm != null) {
		numberMask.attach(NumberOfTitleAttInFirm);
	}
	if (NumberOfTitleNonAttInFirm != null) {
		numberMask.attach(NumberOfTitleNonAttInFirm);
	}
	if (NumberOfTitleAttNotInFirm != null) {
		numberMask.attach(NumberOfTitleAttNotInFirm);
	}
	if (NumberOfTitleNonAttSubCont != null) {
		numberMask.attach(NumberOfTitleNonAttSubCont);
	}
	if (NumberOfRealEstTitleIns != null) {
		numberMask.attach(NumberOfRealEstTitleIns);
	}
	if (PercentOfTitleFromResi != null) {
		numberMask.attach(PercentOfTitleFromResi);
	}
	if (PercentOfTitleFromAgri != null) {
		numberMask.attach(PercentOfTitleFromAgri);
	}
	if (PercentOfTitleFromComm != null) {
		numberMask.attach(PercentOfTitleFromComm);
	}
	if (PercentOfTitleFromOther != null) {
		numberMask.attach(PercentOfTitleFromOther);
	}
	if (NumberOfTitleIssued != null) {
		numberMask.attach(NumberOfTitleIssued);
	}
}

function getRadioValue(radioObj) {
	if (radioObj) {

		var radioLength = radioObj.length;

		if (radioLength == undefined)
			if (radioObj.checked)
				return radioObj.value;
			else
				return "";

		for ( var i = 0; i < radioLength; i++) {
			if (radioObj[i].checked) {
				return radioObj[i].value;
			}

		}
	}
	return "";
}

function getGenericRadioValue(controlName)
{
	
	var control =  document.getElementsByName(controlName);
	if(control){
	if(control.length){
		
		for (var i=0; i < control.length; i++)
   		{
   			if (control[i].checked)
      			{
      				var rad_val = control[i].value;
      				return rad_val;
      			}
      		if(i == ((control.length)-1)){
      			return null;
      		}
   		}
   	}else{
   		
   		return control.value;
   	}
	}
}


function validateEmail(m) {
	var strValue = m.value;
	if (strValue == '')
		return;

	// var objRegExp =
	// /(^[a-z]([a-z_\.]*)@([a-z_\.]*)([.][a-z]{3})$)|(^[a-z]([a-z_\.]*)@([a-z_\.]*)(\.[a-z]{3})(\.[a-z]{2})*$)/i;
	// var objRegExp = /b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4};
	var objRegExp = /(^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,3})+$)/;
	if (!strValue.match(objRegExp)) {
		alert("Email address is not valid");
		m.focus();
	}

}

function showPopUp(el) {

	var w = (screen.width - 434) / 2;
	var t = (screen.height - 450) / 2;
	var cvr = document.getElementById("cover");
	var dlg = document.getElementById(el);
	var ss = document.getElementById("StateCode");
	cvr.style.display = "block";
	dlg.style.display = "block";
	dlg.style.left = w;
	dlg.style.top = t;
	document.getElementById("cover").style.height = document.body.scrollHeight;
}
function showPopUpOverloaded(el, wdth, hght) {

	window.scrollTo(0,0);
	var wBody = parseInt(document.body.scrollWidth, 10);
	var wPopup = parseInt(document.getElementById(el).style.width, 10);
//	document.documentElement.style.overflow = document.body.style.overflow = "auto";
	var w = ((wBody - wPopup) / 2);
	var h = 100;
	var cvr = document.getElementById("cover");
	var dlg = document.getElementById(el);
	//var height = document.body.clientHeight;
	var body = document.body,
	html = document.documentElement;
	
	var height = Math.max( body.scrollHeight, body.offsetHeight, 
	                              html.clientHeight, html.scrollHeight, html.offsetHeight );
	if (dlg != null && w!=null) {
		dlg.style.display = "block";
		dlg.style.left = w + "px";
		dlg.style.top = h + "px";
		//window.scrollTo(0,0);
		//alert(w+" "+h);
	}
	if (cvr != null) {
		cvr.style.height = height + "px";
		cvr.style.display = "block";
		//alert(height);
		cvr.style.position = "fixed";
	}
}

/*function closePopUp(el) {
	var ss = document.getElementById("StateCode");
	ss.style.display = "block";
	var ss1 = document.getElementById("DeductibleSequence");
	ss1.style.display = "block";

	var cvr = document.getElementById("cover");
	var dlg = document.getElementById(el);
	cvr.style.display = "none";
	dlg.style.display = "none";
}*/

function attachMMDDYYYYDateFormat(obj) {
	if (obj == null)
		return;
	dateMask = new Mask("mm/dd/yyyy", "date");
	dateMask.attach(obj);
}

function resetSerachFormWorkQ() {
	//alert('Hi');
	if (document.getElementById("AccountNamesearch") != null)
		document.getElementById("AccountNamesearch").value = '';
	
	if (document.getElementById("WQEmailSearch") != null)
		document.getElementById("WQEmailSearch").value = '';
	
	if (document.getElementById("WQPolicyEffectiveDate_search") != null)
		document.getElementById("WQPolicyEffectiveDate_search").value = '';
	
	if (document.getElementById("WQPolicyEffectiveDate_searchTo") != null)
		document.getElementById("WQPolicyEffectiveDate_searchTo").value = '';
	
	if (document.getElementById("WQStateCode_search") != null)
		document.getElementById("WQStateCode_search").value = '';
	
	if (document.getElementById("WQStatusKey_search") != null)
		document.getElementById("WQStatusKey_search").value = '';
		
	if (document.getElementById("WQQuoteNumberSearch") != null)
		document.getElementById("WQQuoteNumberSearch").value = '';
	
	if (document.getElementById("WQPolicyNumberSearch") != null)
		document.getElementById("WQPolicyNumberSearch").value = '';
	
	if (document.getElementById("WQNumberOfDays_search") != null)
		document.getElementById("WQNumberOfDays_search").value = '';
	
	if (document.getElementById("WQIsRenewed_search").checked==true )
		document.getElementById("WQIsRenewed_search").checked = false;
	if (document.getElementById("WQIsBrokered_search").checked==true )
		document.getElementById("WQIsBrokered_search").checked = false;
	
	
}

function checkPercentage(obj) {
	var val = obj.value;
	// alert(obj);
	var reg = "^[0-9]{1,3}([.][0-9]{1,2})?$";
	if (!val.match(reg)) {
		alert('Percentage is not valid.');
		obj.focus();
	}
}
// ---------------------------show and hide AOP
// tab-------------------------------
function hideTabs() {
	document.getElementById("corporateCommercial_tab").style.display = "none";
	document.getElementById("environmental_tab").style.display = "none";
	document.getElementById("financialInstitution_tab").style.display = "none";
	document.getElementById("plaintiffLitigation_tab").style.display = "none";
	document.getElementById("realEstate_tab").style.display = "none";
	document.getElementById("tax_tab").style.display = "none";
	document.getElementById("title_tab").style.display = "none";
	document.getElementById("WillTrustEstates_tab").style.display = "none";
}
function showTabs() {

	if (((document.getElementById("AOP_E").value != "") && (document
			.getElementById("AOP_E").value != null))
			|| ((document.getElementById("AOP_J").value != "") && (document
					.getElementById("AOP_J").value != null))
			|| ((document.getElementById("AOP_L").value != "") && (document
					.getElementById("AOP_L").value != null))
			|| ((document.getElementById("AOP_R").value != "") && (document
					.getElementById("AOP_R").value != null))
			|| ((document.getElementById("AOP_T").value != "") && (document
					.getElementById("AOP_T").value != null))
			|| ((document.getElementById("AOP_V").value != "") && (document
					.getElementById("AOP_V").value != null))
			|| ((document.getElementById("AOP_W").value != "") && (document
					.getElementById("AOP_W").value != null))
			|| ((document.getElementById("AOP_X").value != "") && (document
					.getElementById("AOP_X").value != null))) {

		showDivArea([ 'divAOPy' ], [ 'divAOPn' ]);
		hideTabs();
		if ((document.getElementById("AOP_E").value != "")
				&& (document.getElementById("AOP_E").value != null))
			document.getElementById("corporateCommercial_tab").style.display = "inline-block";
		if ((document.getElementById("AOP_J").value != "")
				&& (document.getElementById("AOP_J").value != null))
			document.getElementById("environmental_tab").style.display = "inline-block";
		if ((document.getElementById("AOP_L").value != "")
				&& (document.getElementById("AOP_L").value != null))
			document.getElementById("financialInstitution_tab").style.display = "inline-block";
		if ((document.getElementById("AOP_R").value != "")
				&& (document.getElementById("AOP_R").value != null))
			document.getElementById("plaintiffLitigation_tab").style.display = "inline-block";
		if ((document.getElementById("AOP_T").value != "")
				&& (document.getElementById("AOP_T").value != null))
			document.getElementById("realEstate_tab").style.display = "inline-block";
		if ((document.getElementById("AOP_V").value != "")
				&& (document.getElementById("AOP_V").value != null))
			document.getElementById("tax_tab").style.display = "inline-block";
		if ((document.getElementById("AOP_W").value != "")
				&& (document.getElementById("AOP_W").value != null))
			document.getElementById("title_tab").style.display = "inline-block";
		if ((document.getElementById("AOP_X").value != "")
				&& (document.getElementById("AOP_X").value != null))
			document.getElementById("WillTrustEstates_tab").style.display = "inline-block";
		setClass();
	}
}
function setClass() {
	if ((document.getElementById("AOP_E").value != "")
			&& (document.getElementById("AOP_E").value != null)) {
		document.getElementById("corporateCommercial_tab").className = "tabs_active";
		ajaxpage('corporateCommercialSupplement.html', 'divAOP');

	} else if ((document.getElementById("AOP_J").value != "")
			&& (document.getElementById("AOP_J").value != null)) {
		document.getElementById("environmental_tab").className = "tabs_active";
		ajaxpage('environmentalSupplement.html', 'divAOP');

	} else if ((document.getElementById("AOP_L").value != "")
			&& (document.getElementById("AOP_L").value != null)) {
		document.getElementById("financialInstitution_tab").className = "tabs_active";
		ajaxpage('financialInstitutionSupplement.html', 'divAOP');

	} else if ((document.getElementById("AOP_R").value != "")
			&& (document.getElementById("AOP_R").value != null)) {
		document.getElementById("plaintiffLitigation_tab").className = "tabs_active";
		ajaxpage('plaintiffLitigationSupplement.html', 'divAOP');

	} else if ((document.getElementById("AOP_T").value != "")
			&& (document.getElementById("AOP_T").value != null)) {
		document.getElementById("realEstate_tab").className = "tabs_active";
		ajaxpage('realEstateSupplement.html', 'divAOP');

	} else if ((document.getElementById("AOP_V").value != "")
			&& (document.getElementById("AOP_V").value != null)) {
		document.getElementById("tax_tab").className = "tabs_active";
		ajaxpage('taxSupplement.html', 'divAOP');

	} else if ((document.getElementById("AOP_W").value != "")
			&& (document.getElementById("AOP_W").value != null)) {
		document.getElementById("title_tab").className = "tabs_active";
		ajaxpage('titleSupplement.html', 'divAOP');

	} else if ((document.getElementById("AOP_X").value != "")
			&& (document.getElementById("AOP_X").value != null)) {
		document.getElementById("WillTrustEstates_tab").className = "tabs_active";
		ajaxpage('WillTrustEstatesSupplement.html', 'divAOP');

	}
}

function changeClass(MyElement) {
	document.getElementById("corporateCommercial_tab").className = "tabs_inactive";
	document.getElementById("environmental_tab").className = "tabs_inactive";
	document.getElementById("financialInstitution_tab").className = "tabs_inactive";
	document.getElementById("plaintiffLitigation_tab").className = "tabs_inactive";
	document.getElementById("realEstate_tab").className = "tabs_inactive";
	document.getElementById("tax_tab").className = "tabs_inactive";
	document.getElementById("title_tab").className = "tabs_inactive";
	document.getElementById("WillTrustEstates_tab").className = "tabs_inactive";
	document.getElementById(MyElement).className = "tabs_active";
}

function openDivArea6() {
	//alert("Y " + getRadioValue(document.forms[0].IsFirmPracticeInOtherState));
	if (getRadioValue(document.forms[0].IsFirmPracticeInOtherState) == 'Y') {
		showDivArea([ 'div6Ay' ], [ 'div6An' ]);

		if (getRadioValue(document.forms[0].IsApplRestWithManagement) == 'N') {
			showDivArea([ 'div6Cy' ], [ 'div6Cn' ]);
		}
		if (getRadioValue(document.forms[0].IsApplFirmWithDifferentLegalName) == 'Y') {
			showDivArea([ 'div6By' ], [ 'div6Bn' ]);
		}
	} else {
		//alert("N " + getRadioValue(document.forms[0].IsFirmPracticeInOtherState));
		showDivArea([ 'div6An' ], [ 'div6Ay' ]);
		showDivArea([ 'div6Bn' ], [ 'div6By' ]);
	}
}

function openDivArea23() {
	if ((getRadioValue(document.forms[0].IsFirmHaveEquityIntGrtThan10) == 'Y')
			|| (getRadioValue(document.forms[0].IsFirmPersServedAsOfficerInJointVenture) == 'Y'))
		showDivArea([ 'div3y' ], [ 'div3n' ]);
	else
		showDivArea([ 'div3n' ], [ 'div3y' ]);
}

function showDivClaim() {
	if ((getRadioValue(document.forms[0].IsLawyerProfLiabClaimAgntAppl) == 'Y')
			|| (getRadioValue(document.forms[0].IsAnyActOmmBecomeClaimAgntFirm) == 'Y'))
		showDivArea([ 'div1By' ], [ 'div1Bn' ]);
	else
		showDivArea([ 'div1Bn' ], [ 'div1By' ]);
}

function attachPercentageFormat(obj) {
	if (obj == null)
		return;
	percentageMask = new Mask("###", "number");
	percentageMask.attach(obj);
}

function loadFirmAttorney() {
	if (document.forms[0].NumberOfYearsInPractice == null) {
		setTimeout(loadFirmAttorney, 5000);
	} else {
		loadFirmAttorney1();
	}
}
function loadFirmAttorney1() {
	attachPercentageFormat(document.forms[0].NumberOfYearsInPractice);
	attachPercentageFormat(document.forms[0].NumberOfYearsMalpracticeCov);
	attachPercentageFormat(document.forms[0].AnnualWorkedHours);
	attachPercentageFormat(document.forms[0].NumberOfYearsWithFirm);
	// attachMMDDYYYYDateFormat(document.forms[0].AttorneyPriorActDate);
}

function loadFirmPrimaryLoc() {
	if (document.forms[0].NumberOfAttorneys == null) {
		setTimeout(loadFirmPrimaryLoc, 5);
	} else {
		loadFirmPrimaryLoc1();
	}
}
function loadFirmPrimaryLoc1() {
	attachPercentageFormat(document.forms[0].NumberOfAttorneys);
	attachPercentageFormat(document.forms[0].NumberOfBillabeHours);
	// attachPercentageFormat(document.forms[0].FPLZip);
	// attachPercentageFormat(document.forms[0].FPLZip4);
	attachPercentageFormat(document.forms[0].NumberOfOtherEmployees);
	// alert('hi');
}

function loadEntertainAttorney() {
	if (document.forms[0].NumberOfYrsInSpec == null) {
		setTimeout(loadEntertainAttorney, 5);
	} else {
		loadEntertainAttorney1();
	}
}
function loadEntertainAttorney1() {
	attachPercentageFormat(document.forms[0].NumberOfYrsInSpec);
	attachPercentageFormat(document.forms[0].PercentageOfTimeDev);
}

function loadEntertainClient() {
	if (document.forms[0].FromDate == null) {
		setTimeout(loadEntertainClient, 5);
	} else {
		loadEntertainClient1();
	}
}
function loadEntertainClient1() {
	// attachMMDDYYYYDateFormat(document.forms[0].FromDate);
	// attachMMDDYYYYDateFormat(document.forms[0].ToDate);
}

function loadEnvAttorney() {
	// alert('hi');
	if (document.forms[0].NumberOfYrOfEnvExp == null) {
		setTimeout(loadEnvAttorney, 5);
	} else {
		loadEnvAttorney1();
	}
}
function loadEnvAttorney1() {
	// alert(document.forms[0].NumberOfYrOfEnvExp + " " +
	// document.forms[0].ajax_field_NumberOfYrOfEnvExp);
	attachPercentageFormat(document.forms[0].NumberOfYrOfEnvExp);
	attachPercentageFormat(document.forms[0].PercentOfTimeDevoted);
}

function loadEnvContractor() {
	if (document.forms[0].PercentOfEnvGBillings == null) {
		setTimeout(loadEnvContractor, 5);
	} else {
		loadEnvContractor1();
	}
}
function loadEnvContractor1() {
	attachPercentageFormat(document.forms[0].PercentOfEnvGBillings);
}

function loadFinanInst() {
	if (document.forms[0].DateInitiated == null) {
		setTimeout(loadFinanInst, 5);
	} else {
		loadFinanInst1();
	}
}
function loadFinanInst1() {
	// attachMMDDYYYYDateFormat(document.forms[0].DateInitiated);
	// attachMMDDYYYYDateFormat(document.forms[0].DateEnded);
	// attachMMDDYYYYDateFormat(document.forms[0].DateOfInsolvency);
}

function loadPlaintiffAttorney() {
	if (document.forms[0].PlaintiffYrsExp == null) {
		setTimeout(loadPlaintiffAttorney, 5);
	} else {
		loadPlaintiffAttorney1();
	}
}
function loadPlaintiffAttorney1() {
	attachPercentageFormat(document.forms[0].PlaintiffYrsExp);
	attachPercentageFormat(document.forms[0].PlaintiffPecOfTimeDev);
}

function loadTaxAttorney() {
	// alert('hello');
	if (document.forms[0].NumberOfYrOfTaxExp == null) {
		setTimeout(loadTaxAttorney, 5);
	} else {
		loadTaxAttorney1();
	}
}
function loadTaxAttorney1() {
	attachPercentageFormat(document.forms[0].NumberOfYrOfTaxExp);
	attachPercentageFormat(document.forms[0].PercentOfTimeDevoted);
}

function loadPracticeAnnRev() {
	if (document.forms[0].PercentageOfAnnlRevenue == null) {
		setTimeout(loadPracticeAnnRev, 5);
	} else {
		loadPracticeAnnRev1();
	}
}
function loadPracticeAnnRev1() {
	attachPercentageFormat(document.forms[0].PercentageOfAnnlRevenue);
}

function loadPracticePersonnel() {
	if (document.forms[0].PercentEquityInterest == null) {
		setTimeout(loadPracticePersonnel, 5);
	} else {
		loadPracticePersonnel1();
	}
}
function loadPracticePersonnel1() {
	attachPercentageFormat(document.forms[0].PercentEquityInterest);
}

function loadPracticeFeesSuit() {
	if (document.forms[0].AmountOfFeesSued == null) {
		setTimeout(loadPracticeFeesSuit, 5);
	} else {
		loadPracticeFeesSuit1();
	}

}
function loadPracticeFeesSuit1() {
	attachAmountFormat(document.forms[0].AmountOfFeesSued);
	// attachMMDDYYYYDateFormat(document.forms[0].DueDateFees);
	// attachMMDDYYYYDateFormat(document.forms[0].SuitFilesDateFees);
}

function attachAmountFormat(obj) {
	if (obj == null)
		return;
	amountMask = new Mask("$#,###", "number");
	amountMask.attach(obj);
}

function loadEditPracticeFeesSuit() {
	if (document.forms[0].AmountOfFeesSued == null) {
		setTimeout(loadEditPracticeFeesSuit, 5);
	} else {
		loadEditPracticeFeesSuit1();
	}
}
function loadEditPracticeFeesSuit1() {
	attachAmountFormat(document.forms[0].AmountOfFeesSued);
	// attachMMDDYYYYDateFormat(document.forms[0].DueDateFees);
	// attachMMDDYYYYDateFormat(document.forms[0].SuitFilesDateFees);

	amountMask = new Mask("$#,###", "number");
	document.forms[0].AmountOfFeesSued.value = amountMask
			.format(document.forms[0].AmountOfFeesSued.value);
}

function loadCoverageERP() {
	if (document.forms[0].ERPExpDate == null) {
		setTimeout(loadCoverageERP, 5);
	} else {
		loadCoverageERP1();
	}
}
function loadCoverageERP1() {
	// attachMMDDYYYYDateFormat(document.forms[0].ERPExpDate);
	// attachMMDDYYYYDateFormat(document.forms[0].DateOfAcquisation);
	attachPercentageFormat(document.forms[0].NumberOfAttorneyAtDiss);
	attachPercentageFormat(document.forms[0].NumberOfAttorneyAtApplFirm);
}

function loadCoverageClaim() {
	if (document.forms[0].ClaimNotifiedDate == null) {
		setTimeout(loadCoverageClaim, 5);
	} else {
		loadCoverageClaim1();
	}
}
function loadCoverageClaim1() {
	// attachMMDDYYYYDateFormat(document.forms[0].ClaimNotifiedDate);
	// attachMMDDYYYYDateFormat(document.forms[0].DateOfAllegedError);
	// attachMMDDYYYYDateFormat(document.forms[0].DateReportedToInsComp);
	// attachMMDDYYYYDateFormat(document.forms[0].ClaimClosingDate);
	attachAmountFormat(document.forms[0].InsurerLoss);
	attachAmountFormat(document.forms[0].ClaimantLastDemand);
	attachAmountFormat(document.forms[0].TotalAmountPaid);
}

function loadEditCoverageClaim() {
	if (document.forms[0].ClaimNotifiedDate == null) {
		setTimeout(loadEditCoverageClaim, 5);
	} else {
		loadEditCoverageClaim1();
	}
}
function loadEditCoverageClaim1() {
	// attachMMDDYYYYDateFormat(document.forms[0].ClaimNotifiedDate);
	// attachMMDDYYYYDateFormat(document.forms[0].DateOfAllegedError);
	// attachMMDDYYYYDateFormat(document.forms[0].DateReportedToInsComp);
	// attachMMDDYYYYDateFormat(document.forms[0].ClaimClosingDate);

	attachAmountFormat(document.forms[0].InsurerLoss);
	attachAmountFormat(document.forms[0].ClaimantLastDemand);
	attachAmountFormat(document.forms[0].TotalAmountPaid);
	amountMask = new Mask("$#,###", "number");
	document.forms[0].InsurerLoss.value = amountMask
			.format(document.forms[0].InsurerLoss.value);
	document.forms[0].ClaimantLastDemand.value = amountMask
			.format(document.forms[0].ClaimantLastDemand.value);
	document.forms[0].TotalAmountPaid.value = amountMask
			.format(document.forms[0].TotalAmountPaid.value);
}

function pleaseDescribeAOP(isSetFocus) {
	var txtBox = document.getElementById("AOP_Percentage_25");
	// alert(txtBox);
	if (txtBox.value != 0 && txtBox.value != null && txtBox.value != '') {
		showDivArea([ 'divOthery' ], [ 'divOthern' ]);
	} else {
		showDivArea([ 'divOthern' ], [ 'divOthery' ]);
		if (document.getElementById("error_AOPCommentDesc_25"))
			document.getElementById("error_AOPCommentDesc_25").style.display = "none";
	}
}

// Added for Real Estate Commercial
function pleaseDescribeAOPRealEstateCommercial() {
	var txtBox = document.getElementById("ajax_field_AOPRE_Percentage_17");

	if (txtBox.value != 0 && txtBox.value != null && txtBox.value != '') {

		document.getElementById("divRealEstOthery").style.display = "block";

	} else {
		document.getElementById("divRealEstOthery").style.display = "none";

		if (document.getElementById("error_AOPCommentDesc_17"))
			document.getElementById("error_AOPCommentDesc_17").style.display = "none";
	}
}

// Added for Real Estate Residential
function pleaseDescribeAOPRealEstateResidential() {
	var txtBox = document.getElementById("ajax_field_AOPRE_Percentage_20");
	// alert(txtBox);
	if (txtBox.value != 0 && txtBox.value != null && txtBox.value != '') {
		document.getElementById("divRealEstOthery").style.display = "block";
	} else {
		document.getElementById("divRealEstOthery").style.display = "none";
		if (document.getElementById("error_AOPCommentDesc_20"))
			document.getElementById("error_AOPCommentDesc_20").style.display = "none";
	}
}

function limitText(limitField, limitCount, limitNum) {
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	} else {
		limitCount.value = limitNum - limitField.value.length;
	}
}

function limitTextarea(el, maxLines, maxChar) {
	if (!el.x) {
		el.x = uniqueInt();
		el.onblur = function() {
			clearInterval(window['int' + el.x]);
		}
	}
	window['int' + el.x] = setInterval(function() {
		var lines = el.value.replace(/\r/g, '').split('\n'), char_removed;

		if (maxChar) {
			i = lines.length;
			while (i-- > 0)
				if (lines[i].length > maxChar) {
					lines[i] = lines[i].slice(0, maxChar);
					char_removed = 1
				}
			if (char_removed)
				alert('You can not enter more than ' + maxChar + ' characters')
		}
		if (char_removed || lines_removed)
			el.value = lines.join('\n')
	}, 50);
}

function uniqueInt() {
	var num, maxNum = 100000;
	if (!uniqueInt.a || maxNum <= uniqueInt.a.length)
		uniqueInt.a = [];
	do
		num = Math.ceil(Math.random() * maxNum);
	while (uniqueInt.a.hasMember(num))
	uniqueInt.a[uniqueInt.a.length] = num;
	return num
}

function openCorpOtherArea(obj) {
	var val = obj.checked;
	if (val) {
		document.getElementById("divCorpOther").style.display = "block";
	} else {
		document.getElementById("divCorpOther").style.display = "none";
	}
}

function openWillsOtherArea(obj) {
	var val = obj.checked;
	if (val) {
		document.getElementById("divWillsOther").style.display = "block";
	} else {
		document.getElementById("divWillsOther").style.display = "none";
	}
}

function pleaseDescribeAOPRE(isSetFocus, obj) {
	var txtBox = obj;
	if (txtBox.value != 0 && txtBox.value != null && txtBox.value != '') {
		document.getElementById("divRealEstOthery").style.display = "block";
	} else {
		document.getElementById("divRealEstOthery").style.display = "none";
	}
}

function pleaseDescribeAOTP(isSetFocus, obj) {
	var txtBox = obj;
	if (txtBox.value != 0 && txtBox.value != null && txtBox.value != '') {
		document.getElementById("divTaxOthery").style.display = "block";
	} else {
		document.getElementById("divTaxOthery").style.display = "none";
	}
}

function pleaseDescribeTitle(isSetFocus, obj) {
	var txtBox = obj;
	if (obj == null) {
		return;
	}
	if (txtBox.value != 0 && txtBox.value != null && txtBox.value != '') {
		document.getElementById("divTitleOthery").style.display = "block";
	} else {
		document.getElementById("divTitleOthery").style.display = "none";
	}
}

function SetFocus(element) {
	// alert("Alert from "+element) ;
	try
	{
	document.getElementById(element).focus();
	}
	catch(e)
	{}
	return true;
}

function setPolicyEffDate(obj) {
	// alert(obj);
	if (obj == null) {
		return;
	}
	document.forms[0].PolicyEffectiveDate.value = obj;
}

function pleaseDescribeAOL(isSetFocus, obj) {
	if (obj == null) {
		return;
	}
	var txtBox = obj;
	if (txtBox.value != 0 && txtBox.value != null && txtBox.value != '') {
		document.getElementById("divPlantiffOthery").style.display = "block";
	} else {
		document.getElementById("divPlantiffOthery").style.display = "none";
	}
}
function pleaseDescribeAOL() {
	// alert("1");
	var txtBox = document
			.getElementById("ajax_field_AOL_PercentageOfRevenue_13");
	// alert(txtBox);
	if (txtBox.value != 0 && txtBox.value != null && txtBox.value != '') {
		document.getElementById("divPlantiffOthery").style.display = "block";
	} else {
		document.getElementById("divPlantiffOthery").style.display = "none";
		if (document.getElementById("error_AOL_CommentDesc_13"))
			document.getElementById("error_AOL_CommentDesc_13").style.display = "none";
	}
}

function fillLimitOfLiability(value) {
	var IsNewFiling = document.forms[0].IsNewFiling.value;
	if(IsNewFiling == 'Y'){
		if(value<=38)
			fillLimitOfLiabilityNewFiling(value);
		else
			fillLimitOfLiabilityNewFiling("");
	} else{
		if(value<=38)
			fillLimitOfLiabilityOldFiling(value);
		else	
			fillLimitOfLiabilityOldFiling(34);
	}
}
function fillLimitOfLiabilityNewFiling(value) {
	var prosslimit = value;	
	var limit = '';
	if (document.getElementById('ajax_field_LimitSequence') != null){
		limit = document.forms[0].LimitSequence.value;	
	}
	var stateCode = '';
	if (document.getElementById('StateCode') != null){
		stateCode = document.forms[0].StateCode.value;	
	}	
	
	if (document.getElementById('ajax_field_LimitSequence') != null) {
		//if(stateCode != 'SD' || (stateCode == 'SD' && prosslimit > 32)){
			document.getElementById('ajax_field_LimitSequence').value = value;	
			makeDefenseOutsideRule(value);
		//} 				
	}
}

function fillLimitOfLiabilityOldFiling(value) {
	var limit = '';
	if (document.getElementById('ajax_field_LimitSequence') != null){
		limit = document.forms[0].LimitSequence.value;	
	}
	
	if (document.getElementById('ajax_field_LimitSequence') != null) {
		document.getElementById('ajax_field_LimitSequence').value = value;	
		makeDefenseOutsideRule(value);						
	}
}

function fillDeductible(value) {	
	
	if (document.getElementById('ajax_field_DeductibleSequence') != null) {
		document.getElementById('ajax_field_DeductibleSequence').value = value;
	}
}

function fillDefenseExpense(value) {	
	var prosslimit = '';
	if (document.getElementById('ajax_field_LimitSequencePross') != null){
		prosslimit = document.forms[0].LimitSequencePross.value;	
	}
	var limit = '';
	if (document.getElementById('ajax_field_LimitSequence') != null){
		limit = document.forms[0].LimitSequence.value;	
	}
	var stateCode = '';
	if (document.getElementById('StateCode') != null){
		stateCode = document.forms[0].StateCode.value;	
	}	
		
	if (document.getElementById('IsClaimExpensesType') != null
			/*&& getRadioValue(document.forms[0].IsClaimExpensesType) == ''*/) {

		if (value == 'DR') {
			document.forms[0].IsClaimExpensesType[0].checked = true;
		} else if (value == 'DA') {
			document.forms[0].IsClaimExpensesType[1].checked = true;
		}
	}
	
	if (document.getElementById('ajax_field_IsClaimExpensesType') != null && prosslimit != '')
	{				
		if((stateCode == 'AR' ||  stateCode == 'OK' || stateCode == 'NJ' || stateCode == 'MT' || stateCode == 'ND')){
			if(prosslimit < 33){
				if(limit ==''){
					limit = prosslimit;
				}	
				makeDefenseOutsideRule(limit);
			} else{
				if (value == 'DR') {
					document.forms[0].IsClaimExpensesType[0].checked = true;
				} else if (value == 'DA') {
					document.forms[0].IsClaimExpensesType[1].checked = true;
				}
			}
			
		} else if(stateCode == 'SD'){
			//if(prosslimit > 32){
				if(limit ==''){
					limit = prosslimit;
				}	
				makeDefenseOutsideRule(limit);
			//} 
		} else if(stateCode == 'NM'){			
			if(limit ==''){
				limit = prosslimit;
			}	
			makeDefenseOutsideRule(limit);
			 
		}else {
			
			if (value == 'DR') {
				document.forms[0].IsClaimExpensesType[0].checked = true;
			} else if (value == 'DA') {
				document.forms[0].IsClaimExpensesType[1].checked = true;
			}						
			
		}
	}
}

function fillClaim(value) {
	if (document.getElementById('IsClaimOptionType') != null/*&& getRadioValue(document.forms[0].IsClaimOptionType) == ''*/) {

		if (value == 'PC') {
			document.forms[0].IsClaimOptionType[0].checked = true;
		} else if (value == 'AA') {
			document.forms[0].IsClaimOptionType[1].checked = true;
		}
	}
	if (document.getElementById('ajax_field_IsClaimOptionType') != null/*&& getRadioValue(document.forms[0].IsClaimOptionType) == ''*/) {

		if (value == 'PC') {
			document.forms[0].IsClaimOptionType[0].checked = true;
		} else if (value == 'AA') {
			document.forms[0].IsClaimOptionType[1].checked = true;
		}
	}
}

function fillFirstDollarDefence(value){
	var IsNewFiling = document.forms[0].IsNewFiling.value;
	if(IsNewFiling == 'Y'){
		fillFirstDollarDefenceNewFiling(value);
	} else{
		fillFirstDollarDefenceOldFiling(value);
	}
}

function fillFirstDollarDefenceOldFiling(value) {
	if (document.getElementById('IsDollarDefense') != null && !document.getElementById('IsDollarDefense').checked) 
	{
		if (document.getElementById('IsProfDefenceExpense').checked) 
		{
			document.getElementById('IsDollarDefense').checked = true;
		} else 
		{
			document.getElementById('IsDollarDefense').checked = false;
		}
	}
	if (document.getElementById('ajax_field_IsDollarDefense') != null && !document.getElementById('ajax_field_IsDollarDefense').checked) 
	{
		if (document.getElementById('ajax_field_IsProfDefenceExpense').checked) 
		{
			document.getElementById('ajax_field_IsDollarDefense').checked = true;
		} else 
		{
			document.getElementById('ajax_field_IsDollarDefense').checked = false;
		}
	}
}

function fillFirstDollarDefenceNewFiling(value) {
	var stateCode = document.forms[0].StateCode.value;
	
	if (document.getElementById('IsDollarDefense') != null && !document.getElementById('IsDollarDefense').checked) 
	{
		if (document.getElementById('IsProfDefenceExpense').checked) 
		{
			document.getElementById('IsDollarDefense').checked = true;
		} 
		else 
		{
			document.getElementById('IsDollarDefense').checked = false;
		}
	}
	if (document.getElementById('ajax_field_IsDollarDefense') != null && !document.getElementById('ajax_field_IsDollarDefense').checked) 
	{
		if(stateCode != 'NM'){
			if (document.getElementById('ajax_field_IsProfDefenceExpense').checked) {
				document.getElementById('ajax_field_IsDollarDefense').checked = true;
			} else {
				document.getElementById('ajax_field_IsDollarDefense').checked = false;
			}
		}
		if(stateCode == 'SD'){
			document.getElementById('ajax_field_IsDollarDefense').checked = false;
			document.getElementById('ajax_field_IsDollarDefense').disabled = true;			
		}
	}
}

function fillPolicyEffectiveDate(value) {
	if (document.getElementById('PolicyEffectiveDate') != null) {

		document.getElementById('PolicyEffectiveDate').value = value;
	}

}

function getYpos(el) {
	var _y = 0;
	while (el && !isNaN(el.offsetTop)) {
		// _y += el.offsetTop - el.scrollTop;
		_y += el.offsetTop;
		el = el.parentNode;
	}
	return (_y);
}
function setScroll() {
	// alert("aa");
	var yCor1 = getYpos(document
			.getElementById('IsFirmProvidedLegalServiceToSecurity'));
	var yCor = (yCor1 - 380) - ((yCor1 - 1938) / 2);
	if (getRadioValue(document.forms[0].IsFirmHaveClientInEntertainmentInd) == 'Y')
		window.scrollTo(0, yCor);
	else
		window.scrollTo(0, 840);

}

function applyDateFormat(d, e) {
	var val = d.value;
	var flag = false;
	var len = d.value.length;
	if (len != 10) {
		for ( var i = 0; i < len; i++) {
			var c = d.value.charAt(i);
			if (c == '/') {
				d.value = d.value.replace(c, "");
				// alert(i);
				flag = true;
			}
		}
	}
	if (flag) {
		return;
	}
	// alert(val);
	var str = "0123456789";
	var ch = val.charAt(len - 1);
	// alert(val + ' ' + len + ' ' + ch + str.indexOf(ch));
	if (str.indexOf(ch) >= 0) {
		if (len == 2 || len == 5) {
			// d.value = d.value + '/';
		}
		// return true;
	} else {
		d.value = d.value.replace(ch, "");
		return;
	}
	// alert(len);
	if (len == 8 && val.indexOf("/") < 0) {
		var mm = d.value.substr(0, 2);
		var dd = d.value.substr(2, 2);
		var yy = d.value.substr(4, 4);
		// alert(mm + ' ' + dd + ' ' + yy);
		d.value = mm + '/' + dd + '/' + yy;
		// alert(d.value);
	}
}

function applyAmountFormat(d) {
	var initval = d.value;
	var len = d.value.length;
	for ( var i = 0; i < len; i++) {
		var c = d.value.charAt(i);
		if (c == '$' || c == ',') {
			d.value = d.value.replace(c, "");
		}
	}
	var strValue = d.value;
	var str = "0123456789";
	var ch = strValue.charAt(d.value.length - 1);
	if (str.indexOf(ch) >= 0) {
		strValue = addCommas(strValue);
		d.value = '$' + strValue;
		return;
	} else {
		d.value = d.value.replace(ch, "");
		d.value = initval.replace(ch, "");
		return;
	}

	/*
	 * var objRegExp = /-?[0-9]$/; if(objRegExp.test(strValue)) {
	 * //alert(strValue); objRegExp.compile('^-'); strValue =
	 * addCommas(strValue); //alert(strValue); if(objRegExp.test(strValue)){
	 * strValue = '(' + strValue.replace(objRegExp,'') + ')'; } d.value = '$' +
	 * strValue; return; }else return;
	 */
}
function addCommas(strValue) {
	var objRegExp = new RegExp('(-?[0-9]+)([0-9]{3})');
	// check for match to search criteria
	while (objRegExp.test(strValue)) {
		// replace original string with first group match,
		// a comma, then second group match
		strValue = strValue.replace(objRegExp, '$1,$2');
	}
	return strValue;
}
function adjustFocus() {

	var a = document.getElementById("IsAnyActOmmBecomeClaimAgntFirm").focus();

}
function checkIsDateEmpty() {
	alert("This is ok");
}

function loadQuickQuoteInsured() {

	/*
	 * amountMask = new Mask("$#,###", "number"); var amt =
	 * document.getElementById("Amount");
	 * 
	 * if(amt != null) { amountMask.attach(amt); if(amt.value == '0') amt.value =
	 * '';
	 * 
	 * amt.value=amountMask.format(amt.value); }
	 * 
	 * mmddyyyyMask = new Mask("mm/dd/yyyy", "date"); var YearEndDate_3 =
	 * document.getElementById("PolicyEffectiveDate"); if(YearEndDate_3 != null) {
	 * //mmddyyyyMask.attach(YearEndDate_3); }
	 * 
	 * numberMask = new Mask("###", "number"); for (i = 1; i < 25; i++) { var
	 * aop = document.getElementById("AOP_Percentage_"+i); if(aop != null) {
	 * numberMask.attach(aop); if(aop.value =='0') aop.value = ''; } }
	 * 
	 * pleaseDescribe5('N');
	 */

	numberMask = new Mask("###", "number");
	for (i = 1; i < 26; i++) {
		var aop = document.getElementById("AOP_Percentage_" + i);
		if (aop != null) {
			numberMask.attach(aop);
			if (aop.value == '0')
				aop.value = '';
		}
	}

	for (i = 1; i < 7; i++) {
		var ClaimAge = document.getElementById("NumberOfYearsWithFirm_" + i);
		if (ClaimAge != null) {
			numberMask.attach(ClaimAge);
			// if(ClaimAge.value =='0')
			// ClaimAge.value = '';
		}

	}

	var FirmYear = document.getElementById("FirmYear");
	if (FirmYear != null) {
		numberMask.attach(FirmYear);
		// if(FirmYear.value =='0')
		// FirmYear.value = '';
	}

	mmddyyyyMask = new Mask("mm/dd/yyyy", "date");
	var PolicyEffectiveDate = document.getElementById("PolicyEffectiveDate");
	if (PolicyEffectiveDate != null) {
		// mmddyyyyMask.attach(PolicyEffectiveDate);
	}

	phoneMask = new Mask("(###)###-####");

	var wrkphone = document.getElementById("WorkPhone");

	var wrkphoneext = document.getElementById("WorkExt");
	if (wrkphone != null) {
		wrkphone.value = phoneMask.format(wrkphone.value);
		phoneMask.attach(wrkphone);
	}

	if (wrkphoneext != null) {
		numberMask.attach(wrkphoneext);
	}

}

function showDivAreaManualPremium(controlId, divId) {

	if (document.getElementById(divId) != null) {
		if (document.getElementById(controlId).checked) {

			document.getElementById(divId).style.display = 'block';
			document.getElementById('saveLink').style.display = 'block';
			document.getElementById('getLink').style.display = 'none';

			if (document.getElementById('LimitSequence') != null
					&& document.getElementById('DeductibleSequence') != null) {
				var LimitSequence = document.getElementById('LimitSequence');
				var limit = LimitSequence.options[LimitSequence.selectedIndex].value;

				var DeductibleSequence = document
						.getElementById('DeductibleSequence');
				var deductible = DeductibleSequence.options[DeductibleSequence.selectedIndex].value;

				if (limit == '' || deductible == '') {
					document.getElementById('downloadLinkText').style.display = 'block';
					document.getElementById('downloadLink').style.display = 'none';
				} else {
					document.getElementById('downloadLink').style.display = 'block';
					document.getElementById('downloadLinkText').style.display = 'none';
				}
			}
		} else {
			document.getElementById(divId).style.display = 'none';
			document.getElementById('saveLink').style.display = 'none';
			document.getElementById('getLink').style.display = 'block';
		}
	}
}
function showHideRatingLink() {
	// alert(document.getElementById('ManualPremium').style.display);
	if (document.getElementById('IsManualPremium') != null) {
		if (document.getElementById('IsManualPremium').checked) {
			if (document.getElementById('LimitSequence') != null
					&& document.getElementById('DeductibleSequence') != null) {
				// alert(document.getElementById('ManualPremium').style.display);
				if (document.getElementById('ManualPremium').style.display == 'block') {
					var LimitSequence = document
							.getElementById('LimitSequence');
					var limit = LimitSequence.options[LimitSequence.selectedIndex].value;

					var DeductibleSequence = document
							.getElementById('DeductibleSequence');
					var deductible = DeductibleSequence.options[DeductibleSequence.selectedIndex].value;

					if (limit == '' || deductible == '') {
						document.getElementById('downloadLinkText').style.display = 'block';
						document.getElementById('downloadLink').style.display = 'none';
					} else {
						document.getElementById('downloadLink').style.display = 'block';
						document.getElementById('downloadLinkText').style.display = 'none';
					}
				}
			}

		}
	}
}

function showPopUpList(outerDiv, innerDiv) {
	if (document.getElementById(innerDiv) != null) {
		var wBody = parseInt(document.body.scrollWidth, 10);
		var wPopup = parseInt(document.getElementById(innerDiv).style.width, 10);
		
		//alert(" popup ");
		window.scrollTo(0,0)
		 document.documentElement.style.overflow = document.body.style.overflow = "hidden";
		  // alert(wBody+" "+wPopup)
		var w = ((wBody - wPopup) / 2);
		  //alert(w+" "+wBody+" "+wPopup+ document.getElementById(innerDiv).style.width);
        //var h = ((hBody-hPopup)/2);
		var h = window.innerHeight + "px";
		//var h = 100;
		var cvr = document.getElementById("cover");
		var dlg = document.getElementById(outerDiv);
		//document.ontouchmove = function(e){ e.preventDefault(); }
		
    	//var height = document.body.clientHeight;
		var body = document.body,
    		html = document.documentElement;

		var height = Math.max( body.scrollHeight, body.offsetHeight, 
                       html.clientHeight, html.scrollHeight, html.offsetHeight );
		//var height = Math.max( body.scrollHeight, body.offsetHeight, html.clientHeight, html.scrollHeight, html.offsetHeight );
		if (dlg != null) {
			dlg.style.display = "block";
			dlg.style.left = w + "px";
			dlg.style.top = h + "px";
			//alert(w+" "+h);
		}
		/*if (cvr != null) {
			cvr.style.height = height + "px";
			cvr.style.display = "block";
			//alert(height);
			cvr.style.position = "fixed";
			
			//document.getElementById("cover").style.height = document.body.scrollHeight;
			//document.getElementsByTagName('body').style.height = height + "px";
			//document.getElementById("cover").style.height = height + "px"; 
			document.getElementById("cover").style.height = 100 + "%"; 
			//document.body.scrollHeight;
		}*/
		if (cvr != null) {
        	cvr.style.height = height + "px";
			cvr.style.display = "block";
			//alert(height);
			cvr.style.position = "fixed";
			//document.body.scrollHeight;
			//alert(height);
			}
	}

}
//##################showPopUpList1####################
function showPopUpList1(outerDiv, innerDiv) {
	//var hBody = parseInt(window.innerHeight, 10);
	//var hPopup = parseInt(document.getElementById(innerDiv).style.pixelHeight, 10);
	//alert(outerDiv +" " + innerDiv +" " + document.getElementById(innerDiv));
	
	if (document.getElementById(innerDiv) != null) {
		var wBody = parseInt(document.body.scrollWidth, 10);
		var wPopup = parseInt(document.getElementById(innerDiv).style.width, 10);
		//alert("--->"+document.documentElement.style.overflow);
		window.scrollTo(0,0)
		 document.documentElement.style.overflow = document.body.style.overflow = "auto";
		  // alert(wBody+" "+wPopup)
		var w = ((wBody - wPopup) / 2);
		  //alert(w+" "+wBody+" "+wPopup+ document.getElementById(innerDiv).style.width);
          // var h = ((hBody-hPopup)/2);
		var h = 100;
		var cvr = document.getElementById("cover");
		var dlg = document.getElementById(outerDiv);
		//document.ontouchmove = function(e){ e.preventDefault(); }
		
    	//var height = document.body.clientHeight;
		var body = document.body,
    		html = document.documentElement;

		var height = Math.max( body.scrollHeight, body.offsetHeight, 
                       html.clientHeight, html.scrollHeight, html.offsetHeight );
		//var height = Math.max( body.scrollHeight, body.offsetHeight, html.clientHeight, html.scrollHeight, html.offsetHeight );
		if (dlg != null) {
			dlg.style.display = "block";
			dlg.style.left = w + "px";
			dlg.style.top = h + "px";
			//alert(w+" "+h);
		}
		if (cvr != null) {
			cvr.style.height = height + "px";
			cvr.style.display = "block";
			//alert(height);
			cvr.style.position = "fixed";
			
			//document.getElementById("cover").style.height = document.body.scrollHeight;
			//document.getElementsByTagName('body').style.height = height + "px";
			//document.getElementById("cover").style.height = height + "px"; 
			//document.getElementById("cover").style.height = 100 + "%"; 
			//document.body.scrollHeight;
		}
	}

		
	
}
function closePopUp(el) {
	document.documentElement.style.overflow = document.body.style.overflow = 'auto';
	var cvr = document.getElementById("cover")
	var dlg = document.getElementById(el)
	cvr.style.display = "none"
	dlg.style.display = "none"
}

function loadPlaintiffSupp() {

	// alert("Called Pla");
	if (getRadioValue(document.forms[0].IsApplAcceptCasesToStatuteOfLim) == 'Y')
		showDivArea([ 'div6y' ], [ 'div6n' ]);

	if (getRadioValue(document.forms[0].IsAppReferToOtherLawFirms) == 'Y')
		showDivArea([ 'divky' ], [ 'divkn' ]);

	if (getRadioValue(document.forms[0].IsAppAcceptRefersFromOtherFirms) == 'Y')
		showDivArea([ 'divHy' ], [ 'divHn' ]);

}

function loadRealEstateResidentialSupplement() {

	var aop1 = document.getElementById("ajax_field_AOPRE_Percentage_1");
	var aop2 = document.getElementById("ajax_field_AOPRE_Percentage_4");
	var aop3 = document.getElementById("ajax_field_AOPRE_Percentage_19");
	var aop4 = document.getElementById("ajax_field_AOPRE_Percentage_6");
	var aop5 = document.getElementById("ajax_field_AOPRE_Percentage_20");
	var aop6 = document.getElementById("ajax_field_AOPRE_Percentage_21");

	numberMask = new Mask("###", "number");
	if (aop1 != null) {

		numberMask.attach(aop1);
		if (aop1.value == '0')
			aop1.value = '';
	}
	if (aop2 != null) {
		numberMask.attach(aop2);
		if (aop2.value == '0')
			aop2.value = '';
	}
	if (aop3 != null) {
		numberMask.attach(aop3);
		if (aop3.value == '0')
			aop3.value = '';
	}
	if (aop4 != null) {
		numberMask.attach(aop4);
		if (aop4.value == '0')
			aop4.value = '';
	}
	if (aop5 != null) {
		numberMask.attach(aop5);
		if (aop5.value == '0')
			aop5.value = '';
	}
	if (aop6 != null) {
		numberMask.attach(aop6);
		if (aop6.value == '0')
			aop6.value = '';
	}

}

function loadRealEstateCommercialSupplement() {

	var aop1 = document.getElementById("ajax_field_AOPRE_Percentage_2");
	var aop2 = document.getElementById("ajax_field_AOPRE_Percentage_7");
	var aop3 = document.getElementById("ajax_field_AOPRE_Percentage_8");
	var aop4 = document.getElementById("ajax_field_AOPRE_Percentage_9");
	var aop5 = document.getElementById("ajax_field_AOPRE_Percentage_10");

	var aop6 = document.getElementById("ajax_field_AOPRE_Percentage_11");
	var aop7 = document.getElementById("ajax_field_AOPRE_Percentage_12");
	var aop8 = document.getElementById("ajax_field_AOPRE_Percentage_13");
	var aop9 = document.getElementById("ajax_field_AOPRE_Percentage_14");
	var aop10 = document.getElementById("ajax_field_AOPRE_Percentage_15");

	var aop11 = document.getElementById("ajax_field_AOPRE_Percentage_16");
	var aop12 = document.getElementById("ajax_field_AOPRE_Percentage_17");

	numberMask = new Mask("###", "number");
	if (aop1 != null) {
		numberMask.attach(aop1);
		if (aop1.value == '0')
			aop1.value = '';
	}
	if (aop2 != null) {
		numberMask.attach(aop2);
		if (aop2.value == '0')
			aop2.value = '';
	}
	if (aop3 != null) {
		numberMask.attach(aop3);
		if (aop3.value == '0')
			aop3.value = '';
	}
	if (aop4 != null) {
		numberMask.attach(aop4);
		if (aop4.value == '0')
			aop4.value = '';
	}
	if (aop5 != null) {
		numberMask.attach(aop5);
		if (aop5.value == '0')
			aop5.value = '';
	}
	if (aop6 != null) {
		numberMask.attach(aop6);
		if (aop6.value == '0')
			aop6.value = '';
	}
	if (aop7 != null) {
		numberMask.attach(aop7);
		if (aop7.value == '0')
			aop7.value = '';
	}
	if (aop8 != null) {
		numberMask.attach(aop8);
		if (aop8.value == '0')
			aop8.value = '';
	}
	if (aop9 != null) {
		numberMask.attach(aop9);
		if (aop9.value == '0')
			aop9.value = '';
	}
	if (aop10 != null) {
		numberMask.attach(aop10);
		if (aop10.value == '0')
			aop10.value = '';
	}
	if (aop11 != null) {
		numberMask.attach(aop11);
		if (aop11.value == '0')
			aop11.value = '';
	}
	if (aop12 != null) {
		numberMask.attach(aop12);
		if (aop12.value == '0')
			aop12.value = '';
	}

}
function showPopUpConfirmation(el, wdth, hght) {
	// alert("test");
	/*
	 * var w = (screen.width-434)/2; var t = (screen.height-450)/2; var cvr =
	 * document.getElementById("cover"); var dlg = document.getElementById(el);
	 * //var ss = document.getElementById("StateCode"); cvr.style.display =
	 * "block"; dlg.style.display = "block"; dlg.style.left =w; dlg.style.top
	 * =t; document.getElementById("cover").style.height
	 * =document.body.scrollHeight;
	 * 
	 */

	var w = (screen.width - wdth) / 2 + 'px';
	var t = (screen.height - hght) / 2 + 'px';
	var cvr = document.getElementById("cover");
	var dlg = document.getElementById(el);
	var ss = document.getElementById("StateCode");
	cvr.style.display = "block";
	dlg.style.display = "block";
	dlg.style.left = w;
	dlg.style.top = t;
	document.getElementById("cover").style.height = "1000px";
}

function sendPaypal(isProd) {
	if (isProd == 'Y') {
		document.forms[0].action = "https://www.paypal.com/cgi-bin/webscr";
	} else {
		document.forms[0].action = "https://www.sandbox.paypal.com/us/cgi-bin/webscr";		
	}
	document.forms[0].submit();
}

function sendAuth(isProd) {
	
	if(isProd == 'Y')
	{
		document.forms[0].action = "https://secure.authorize.net/gateway/transact.dll";
	}	
	else
	{
		document.forms[0].action = "https://test.authorize.net/gateway/transact.dll";
	}	
	document.forms[0].submit();
}

function invalidCardExpiration(card_expiration){
	
	if(isNaN(card_expiration)){
		return true ;
	}else if(card_expiration.length != 4 ){
		return true ;
	}else if(card_expiration.length == 4){
		var month = card_expiration.substring(0,2);
		var year = card_expiration.substring(2,4);		
		if(parseInt(month) > 12 || parseInt(month) < 0){
			alert("here");
			return true ;
		}
		else{
			var d = new Date();
			var n = d.getFullYear(); 			
			if(parseInt(year) < parseInt(n.toString().substring(2,4))){
				alert("year here");
				return true ;
			}
		}
	}
}

function validateCardNumber(card_number) {

	// American Express
	var cardno = /^(?:3[47][0-9]{13})$/;
	if (card_number.match(cardno)) {
		return true;
	} else {
		// For Diners
		cardno = /^(?:3(?:0[0-5]|[68][0-9])[0-9]{11})$/;
		if (card_number.match(cardno)) {
			return true;
		} else {
			// Discovers
			cardno = /^(?:6(?:011|5[0-9][0-9])[0-9]{12})$/;
			if (card_number.match(cardno)) {
				return true;
			} else {
				// VISA
				cardno = /^(?:4[0-9]{12}(?:[0-9]{3})?)$/;
				if (card_number.match(cardno)) {
					return true;
				} else {
					// Master
					cardno = /^(?:5[1-5][0-9]{14})$/;
					if (card_number.match(cardno)) {
						return true;
					} else {
						// JCB
						cardno = /^(?:(?:2131|1800|35\d{3})\d{11})$/;
						if (card_number.match(cardno)) {
							return true;
						} else {
							return false;
						}
					}
				}
			}
		}
	}
}

function openAttorneyDiv() {
	if (getRadioValue(document.forms[0].IsAttorneyAddedDeleted) == 'Y') {
		showDivArea([ 'attorneydivY' ], [ 'attorneydivN' ]);
	} else {
		showDivArea([ 'attorneydivN' ], [ 'attorneydivY' ]);
	}
}

function loadRenewFirm() {

	// document.getElementById("YearEndDate_0").tabIndex="101"
	if (getRadioValue(document.forms[0].IsApplicantProvidesLegalServices) == 'Y')
		showDivArea([ 'div8y' ], [ 'div8n' ]);
	if (getRadioValue(document.forms[0].IsIndependentContractor) == 'Y')
		showDivArea([ 'div4By' ], [ 'div4Bn' ]);
	if (getRadioValue(document.forms[0].IsFirmHaveBackupAttorney) == 'Y')
		showDivArea([ 'div4Cy' ], [ 'div4Cn' ]);
	openAttorneyDiv();
	openDivArea6();
	amountMask = new Mask("$#,###", "number");

	var amt0 = document.getElementById("Amount_0");
	var amt1 = document.getElementById("Amount_1");

	if (amt0 != null) {
		amountMask.attach(amt0);
		amt0.value = amountMask.format(amt0.value);
	}
	// alert(amt0);
	if (amt1 != null) {
		amountMask.attach(amt1);
		amt1.value = amountMask.format(amt1.value);
	}

	mmyyyyMask = new Mask("mm/yyyy", "date");

	var YearEndDate_0 = document.getElementById("YearEndDate_0");
	var YearEndDate_1 = document.getElementById("YearEndDate_1");

	if (YearEndDate_0 != null) {
		mmyyyyMask.attach(YearEndDate_0);
	}
	if (YearEndDate_1 != null) {
		mmyyyyMask.attach(YearEndDate_1);
	}

	mmddyyyyMask = new Mask("mm/dd/yyyy", "date");

	var YearEndDate_3 = document.getElementById("PolicyEffectiveDate");

	if (YearEndDate_3 != null) {
		mmddyyyyMask.attach(YearEndDate_3);
	}

	phoneMask = new Mask("(###)###-####");

	var wrkphone = document.getElementById("WorkPhone");
	var cntphone = document.getElementById("ContactPhone");

	if (wrkphone != null) {
		wrkphone.value = phoneMask.format(wrkphone.value);
		phoneMask.attach(wrkphone);
	}
	if (cntphone != null) {
		cntphone.value = phoneMask.format(cntphone.value);
		phoneMask.attach(cntphone);
	}

	numberMask = new Mask("###", "number");

	// var zip4 = document.getElementById("Zip4");
	var WorkExt = document.getElementById("WorkExt");
	var ContactPhoneExt = document.getElementById("ContactPhoneExt");
	var YearOfFirmEstablished = document
			.getElementById("YearOfFirmEstablished");
	var TotalNumOfNonAttorneyStaff = document
			.getElementById("TotalNumOfNonAttorneyStaff");
	var PerOfGrossBillingsUnderContract = document
			.getElementById("PerOfGrossBillingsUnderContract");

	/*
	 * if(zip4 != null){ numberMask.attach(zip4); }
	 * 
	 * if(WorkExt != null){ numberMask.attach(WorkExt); } if(ContactPhoneExt !=
	 * null){ numberMask.attach(ContactPhoneExt); }
	 */

	if (YearOfFirmEstablished != null) {
		numberMask.attach(YearOfFirmEstablished);
	}
	if (TotalNumOfNonAttorneyStaff != null) {
		numberMask.attach(TotalNumOfNonAttorneyStaff);
	}
	if (PerOfGrossBillingsUnderContract != null) {
		numberMask.attach(PerOfGrossBillingsUnderContract);
	}

	numberMask = new Mask("###", "number");

	var percentFirstLargest = document
			.getElementById("PercentFromFirstLargestRevenueClient");
	var percentSecondLargest = document
			.getElementById("PercentFromSecondLargestRevenueClient");

	if (percentFirstLargest != null)
		numberMask.attach(percentFirstLargest);
	if (percentSecondLargest != null)
		numberMask.attach(percentSecondLargest);

	var dateRenderedFirstClient = document
			.getElementById("DateRenderedFirstLargestRevenueClient");
	var dateRenderedSecondClient = document
			.getElementById("DateRenderedSecondLargestRevenueClient");

	if (dateRenderedFirstClient != null)
		numberMask.attach(dateRenderedFirstClient);
	if (dateRenderedSecondClient != null)
		numberMask.attach(dateRenderedSecondClient);

	loadRenewAOPFields();
	pleaseDescribeAOP();

	loadRenewPracticeFields();
	if (getRadioValue(document.forms[0].IsPersonnelBeSubOfAnyInvest) == 'Y')
		showDivArea([ 'div1Ay' ], [ 'div1An' ]);
	showDivClaim();

	var amtgross = document.getElementById("GrossFeesPaidRenew");
	if (amtgross != null) {
		amountMask.attach(amtgross);
		amtgross.value = amountMask.format(amtgross.value);
	}
}

function loadRenewPracticeFields() {

	if (getRadioValue(document.forms[0].IsFirmHaveClientMoreThan25PercentOfBilling) == 'Y')
		showDivArea([ 'div1y' ], [ 'div1n' ]);
	if (getRadioValue(document.forms[0].IsApplInitiatedLawsuitForFirm) == 'Y')
		showDivArea([ 'div11y' ], [ 'div11n' ]);

	numberMask = new Mask("###", "number");

	var percentFirstLargest = document
			.getElementById("PercentFromFirstLargestRevenueClient");
	var percentSecondLargest = document
			.getElementById("PercentFromSecondLargestRevenueClient");

	if (percentFirstLargest != null)
		numberMask.attach(percentFirstLargest);
	if (percentSecondLargest != null)
		numberMask.attach(percentSecondLargest);

	var dateRenderedFirstClient = document
			.getElementById("DateRenderedFirstLargestRevenueClient");
	var dateRenderedSecondClient = document
			.getElementById("DateRenderedSecondLargestRevenueClient");

	if (dateRenderedFirstClient != null)
		numberMask.attach(dateRenderedFirstClient);
	if (dateRenderedSecondClient != null)
		numberMask.attach(dateRenderedSecondClient);

}

function loadRenewAOPFields() {
	if (getRadioValue(document.forms[0].IsFirmProvidedLegalService) == 'Y')
		showDivArea([ 'div22y' ], [ 'div22n' ]);

	var aop1 = document.getElementById("AOP_Percentage_1");
	var aop2 = document.getElementById("AOP_Percentage_2");
	var aop3 = document.getElementById("AOP_Percentage_3");
	var aop4 = document.getElementById("AOP_Percentage_4");
	var aop5 = document.getElementById("AOP_Percentage_5");
	var aop6 = document.getElementById("AOP_Percentage_6");
	var aop7 = document.getElementById("AOP_Percentage_7");
	var aop8 = document.getElementById("AOP_Percentage_8");
	var aop9 = document.getElementById("AOP_Percentage_9");
	var aop10 = document.getElementById("AOP_Percentage_10");
	var aop11 = document.getElementById("AOP_Percentage_11");
	var aop12 = document.getElementById("AOP_Percentage_12");
	var aop13 = document.getElementById("AOP_Percentage_13");
	var aop14 = document.getElementById("AOP_Percentage_14");
	var aop15 = document.getElementById("AOP_Percentage_15");
	var aop16 = document.getElementById("AOP_Percentage_16");
	var aop17 = document.getElementById("AOP_Percentage_17");
	var aop18 = document.getElementById("AOP_Percentage_18");
	var aop19 = document.getElementById("AOP_Percentage_19");
	var aop20 = document.getElementById("AOP_Percentage_20");
	var aop21 = document.getElementById("AOP_Percentage_21");
	var aop22 = document.getElementById("AOP_Percentage_22");
	var aop23 = document.getElementById("AOP_Percentage_23");
	var aop24 = document.getElementById("AOP_Percentage_24");
	var aop25 = document.getElementById("AOP_Percentage_25");
	var aop26 = document.getElementById("AOP_Percentage_26");
	var aop27 = document.getElementById("AOP_Percentage_27");

	numberMask = new Mask("###", "number");
	if (aop1 != null) {
		numberMask.attach(aop1);
		if (aop1.value == '0')
			aop1.value = '';
	}
	if (aop2 != null) {
		numberMask.attach(aop2);
		if (aop2.value == '0')
			aop2.value = '';
	}
	if (aop3 != null) {
		numberMask.attach(aop3);
		if (aop3.value == '0')
			aop3.value = '';
	}
	if (aop4 != null) {
		numberMask.attach(aop4);
		if (aop4.value == '0')
			aop4.value = '';
	}
	if (aop5 != null) {
		numberMask.attach(aop5);
		if (aop5.value == '0')
			aop5.value = '';
	}
	if (aop6 != null) {
		numberMask.attach(aop6);
		if (aop6.value == '0')
			aop6.value = '';
	}
	if (aop7 != null) {
		numberMask.attach(aop7);
		if (aop7.value == '0')
			aop7.value = '';
	}
	if (aop8 != null) {
		numberMask.attach(aop8);
		if (aop8.value == '0')
			aop8.value = '';
	}
	if (aop9 != null) {
		numberMask.attach(aop9);
		if (aop9.value == '0')
			aop9.value = '';
	}
	if (aop10 != null) {
		numberMask.attach(aop10);
		if (aop10.value == '0')
			aop10.value = '';
	}
	if (aop11 != null) {
		numberMask.attach(aop11);
		if (aop11.value == '0')
			aop11.value = '';
	}
	if (aop12 != null) {
		numberMask.attach(aop12);
		if (aop12.value == '0')
			aop12.value = '';
	}
	if (aop13 != null) {
		numberMask.attach(aop13);
		if (aop13.value == '0')
			aop13.value = '';
	}
	if (aop14 != null) {
		numberMask.attach(aop14);
		if (aop14.value == '0')
			aop14.value = '';
	}
	if (aop15 != null) {
		numberMask.attach(aop15);
		if (aop15.value == '0')
			aop15.value = '';
	}
	if (aop16 != null) {
		numberMask.attach(aop16);
		if (aop16.value == '0')
			aop16.value = '';
	}
	if (aop17 != null) {
		numberMask.attach(aop17);
		if (aop17.value == '0')
			aop17.value = '';
	}
	if (aop18 != null) {
		numberMask.attach(aop18);
		if (aop18.value == '0')
			aop18.value = '';
	}
	if (aop19 != null) {
		numberMask.attach(aop19);
		if (aop19.value == '0')
			aop19.value = '';
	}
	if (aop20 != null) {
		numberMask.attach(aop20);
		if (aop20.value == '0')
			aop20.value = '';
	}
	if (aop21 != null) {
		numberMask.attach(aop21);
		if (aop21.value == '0')
			aop21.value = '';
	}
	if (aop22 != null) {
		numberMask.attach(aop22);
		if (aop22.value == '0')
			aop22.value = '';
	}
	if (aop23 != null) {
		numberMask.attach(aop23);
		if (aop23.value == '0')
			aop23.value = '';
	}
	if (aop24 != null) {
		numberMask.attach(aop24);
		if (aop24.value == '0')
			aop24.value = '';
	}
	if (aop25 != null) {
		numberMask.attach(aop25);
		if (aop25.value == '0')
			aop25.value = '';
	}
	if (aop26 != null) {
		numberMask.attach(aop26);
		if (aop26.value == '0')
			aop26.value = '';
	}
	if (aop27 != null) {
		numberMask.attach(aop27);
		if (aop27.value == '0')
			aop27.value = '';
	}

}

function showHideCommentDiv() {
	if (document.forms[0].IsRenewalReview == null) {
		setTimeout(showHideCommentDiv, 10);
	} else {
		showReviewCommentsBlock();
	}
}

function showReviewCommentsBlock() {
	if (getRadioValue(document.forms[0].IsRenewalReview) == 'Y') {
		showDivArea([ 'divRenewalReviewY' ], [ 'divRenewalReviewN' ]);
	} else {
		showDivArea([ 'divRenewalReviewN' ], [ 'divRenewalReviewY' ]);
	}
}

function loadAdminPanel() {

	mmddyyyyMask = new Mask("mm/dd/yyyy", "date");

	var policyexpirationdate = document.getElementById("PolicyExpirationDate_admin");
	var prioractdatepross = document.getElementById("PriorActDatePross_admin");

	if (policyexpirationdate != null) {
		//mmddyyyyMask.attach(policyexpirationdate);
	}
	if (prioractdatepross != null) {
		//mmddyyyyMask.attach(prioractdatepross);
	}

	phoneMask = new Mask("(###)###-####");

	var wrkphone = document.getElementById("WorkPhone_admin");
	if (wrkphone != null) {
		wrkphone.value = phoneMask.format(wrkphone.value);
		phoneMask.attach(wrkphone);
	}

}

function showCreditDebitDiv(){
	
	if(document.getElementById("bluefinerror") != null){
		var bluefinerror = document.getElementById("bluefinerror").value ;
		if(bluefinerror == 'Y'){
			showDivArea(['DivPaypal'], ['DivFin','DivEFT']);
		}
	}
}

function confirmDelete(){
	
	var x = confirm("Are you sure you want to delete this quote ? ");
	return x ;
	
}


var toggle = true;
function toggleDivDisplay(ajaxdivids) {
            //alert(ajaxdivids);
      var divids = ajaxdivids.split(",");
      for (var i = 0; i < divids.length; i++) {
                  //alert(divids[i]);
            if (document.getElementById(divids[i]) != null) {
                  if (toggle) {
                        if (document.getElementById(divids[i]).style.display == "block") {
                              document.getElementById(divids[i]).style.display = "none";
                        }
                  }
                  toggle = true;
            }
      }
}
function nottoggleDivDisplay(element, id, divid) {
      if (document.getElementById(divid) != null) {
            if (element.id == id) {
                        //alert('hi');
                  if (document.getElementById(divid).style.display == "block") {
                        document.getElementById(divid).style.display = "none";
                        toggle = false;
                  }
            }
      }
}

function showSpecialIssuanceDiv(){	
	
	if(document.getElementById("IsSpecialIssuance") != null){
		
		if(getRadioValue(document.forms[0].IsSpecialIssuance) == 'Y'){	
			document.getElementById("SpecialIssuanceCommentsDiv").style.display = "block";		
		}else{	
			document.getElementById("SpecialIssuanceCommentsDiv").style.display = "none";	
		}		
	}
	
}

function loadAdditionalFormAttachment(){
	
	if(document.getElementById("IsPredecessorFormSelected") != null){		
		if(getRadioValue(document.forms[0].IsPredecessorFormSelected) == 'Y'){			
			showDivArea(['div1y'], ['div1n']);
			}else{				
			showDivArea(['div1n'], ['div1y']);
		}		
	}
	
	if(document.getElementById("IsSpecifiedAttorneySelected") != null){
		
		if(getRadioValue(document.forms[0].IsSpecifiedAttorneySelected) == 'Y'){	
			showDivArea(['div2y'], ['div2n']);
		}else{	
			showDivArea(['div2n'], ['div2y']);
		}		
	}
	
	if(document.getElementById("IsSpecificProfServicesSelected") != null){
		
		if(getRadioValue(document.forms[0].IsSpecificProfServicesSelected) == 'Y'){	
			showDivArea(['div3y'], ['div3n']);
		}else{	
			showDivArea(['div3n'], ['div3y']);
		}		
	}
	
	if(document.getElementById("IsPreviouFirmFormSelected") != null){
		
		if(getRadioValue(document.forms[0].IsPreviouFirmFormSelected) == 'Y'){	
			showDivArea(['div4y'], ['div4n']);
		}else{	
			showDivArea(['div4n'], ['div4y']);
		}		
	}
}

function loadPayment()
{
	   
   if(getRadioValue(document.forms[0].PayFull) == 'payfull')
   {
	   showDivArea(['DivPayFull'], ['DivFin']);
	   checkUnCheckPaymentDiv(['PayFull'], ['PaymentModeF']);
   }
   if(getRadioValue(document.forms[0].PaymentMode) == 'finance')
   {
      showDivArea(['DivFin'], ['DivPayFull','DivPaypal','DivEFT','DivACH']);
      checkUnCheckPaymentDiv(['PaymentModeF'], ['PayFull']);
   }
   if(getRadioValue(document.forms[0].PaymentMode) == 'paypal')
   {
	  showDivArea(['DivPayFull'], ['DivFin']);
      showDivArea(['DivPaypal'], ['DivFin','DivEFT','DivACH']);
      checkUnCheckPaymentDiv(['PayFull'], ['PaymentModeF']);
   }
   if(getRadioValue(document.forms[0].PaymentMode) == 'eft')
   {
	   showDivArea(['DivEFT'], ['DivPaypal','DivFin','DivACH']);
   }
   if(getRadioValue(document.forms[0].PaymentMode) == 'ach')
   {
	   showDivArea(['DivPayFull'], ['DivFin']);
	   showDivArea(['DivACH'], ['DivPaypal','DivFin','DivEFT']);
	   checkUnCheckPaymentDiv(['PayFull'], ['PaymentModeF']);
   }
}


function makeDefenseOutsideRuleOnPageLoad(){
	var limit = '';
	if (document.getElementById('ajax_field_LimitSequence') != null){
		limit = document.forms[0].LimitSequence.value;	
	}
	var IsNewFiling = document.forms[0].IsNewFiling.value;
	if(limit != ''){
		if(IsNewFiling == 'Y'){
			
			makeDefenseOutsideRuleNewFiling(limit);
		} else{
			makeDefenseOutsideRuleOldFiling(limit);
		}
	}
}

function makeDefenseOutsideRule(value){
	var IsNewFiling = document.forms[0].IsNewFiling.value;
	if(IsNewFiling == 'Y'){
		makeDefenseOutsideRuleNewFiling(value);
	} else{
		makeDefenseOutsideRuleOldFiling(value);
	}
}

function makeDefenseOutsideRuleNewFiling(value){	
	var stateCode = document.forms[0].StateCode.value;	
	
	if(stateCode == 'AR' || stateCode == 'NJ' || stateCode == 'MT' || stateCode == 'ND'){		
		if(value < 33){
			document.forms[0].IsClaimExpensesType[1].checked = true;
			document.forms[0].IsClaimExpensesType[0].checked = false;
			document.forms[0].IsClaimExpensesType[0].disabled = true;			
		}else{
			document.forms[0].IsClaimExpensesType[0].disabled = false;
			document.forms[0].IsClaimExpensesType[1].disabled = false;
		}		
	}else if(stateCode == 'NM'){
		var NMRatingVersion=document.getElementById('NMRatingVersion').value;
		//alert(NMRatingVersion);
		if(NMRatingVersion=='NM1')
			{
				if(value < 28){
					document.forms[0].IsClaimExpensesType[1].checked = true;
					document.forms[0].IsClaimExpensesType[1].disabled = false;
					document.forms[0].IsClaimExpensesType[0].checked = false;
					document.forms[0].IsClaimExpensesType[0].disabled = true;
					
					document.getElementById("ajax_field_IsDollarDefense").checked = true;
					document.getElementById("ajax_field_IsDollarDefense").disabled = true;	
				}else if(value >= 28){
					document.forms[0].IsClaimExpensesType[0].checked = true;
					//document.forms[0].IsClaimExpensesType[0].disabled = false;
					document.forms[0].IsClaimExpensesType[1].checked = false;
					//document.forms[0].IsClaimExpensesType[1].disabled = true;
					
					document.getElementById("ajax_field_IsDollarDefense").checked = false;
					//document.getElementById("ajax_field_IsDollarDefense").disabled = true;	
				}	
			}
		if(NMRatingVersion=='NM2')
			{
			if(value < 28){
				document.forms[0].IsClaimExpensesType[1].checked = true;
				document.forms[0].IsClaimExpensesType[1].disabled = false;
				document.forms[0].IsClaimExpensesType[0].checked = false;
				document.forms[0].IsClaimExpensesType[0].disabled = true;
				
				document.getElementById("ajax_field_IsDollarDefense").checked = true;
				document.getElementById("ajax_field_IsDollarDefense").disabled = true;	
			}else if(value >= 28){
				
			
				document.forms[0].IsClaimExpensesType[0].disabled = false;
				
				document.forms[0].IsClaimExpensesType[1].disabled = false;
				
				
				document.getElementById("ajax_field_IsDollarDefense").disabled = false;	
			}	
			document.getElementById('lb1').innerHTML="Defense expenses reduce the limit by 50%";
			}
	
	}
	
	else if(stateCode == 'SD' ){
		document.forms[0].IsClaimExpensesType[0].checked = true;
		document.forms[0].IsClaimExpensesType[1].checked = false;
		document.forms[0].IsClaimExpensesType[1].disabled = true;
	}else if(stateCode == 'VT'){
		document.forms[0].IsClaimExpensesType[1].checked = true;
		document.forms[0].IsClaimExpensesType[0].checked = false;
		document.forms[0].IsClaimExpensesType[0].disabled = true;			
	}else{
		document.forms[0].IsClaimExpensesType[0].disabled = false;
	}	
	
	if(stateCode == 'NJ'){
		document.getElementById("ajax_field_IsDollarDefense").checked = true;
		document.getElementById("ajax_field_IsDollarDefense").disabled = true;		
	}else if(stateCode == 'SD'){
		document.getElementById('ajax_field_IsDollarDefense').checked = false;
		document.getElementById('ajax_field_IsDollarDefense').disabled = true;		
	}else if(stateCode != 'NM' && stateCode != 'SD' ){
		document.getElementById("ajax_field_IsDollarDefense").disabled = false;	
	}
	
}

function makeDefenseOutsideRuleOldFiling(value){	
	var stateCode = document.forms[0].StateCode.value;	
	if(stateCode == 'AR' ||  stateCode == 'OK'){		
		if(value < 33){
			document.forms[0].IsClaimExpensesType[1].checked = true;
			document.forms[0].IsClaimExpensesType[0].checked = false;
			document.forms[0].IsClaimExpensesType[0].disabled = true;			
		}else{
			document.forms[0].IsClaimExpensesType[0].disabled = false;
			document.forms[0].IsClaimExpensesType[1].disabled = false;
		}		
	}else if(stateCode == 'NM' || stateCode == 'NJ' || stateCode == 'VT'){
		
			document.forms[0].IsClaimExpensesType[1].checked = true;
			document.forms[0].IsClaimExpensesType[0].checked = false;
			document.forms[0].IsClaimExpensesType[0].disabled = true;
			
	}else if(stateCode == 'SD' ){
		document.forms[0].IsClaimExpensesType[0].checked = true;
		document.forms[0].IsClaimExpensesType[1].checked = false;
		document.forms[0].IsClaimExpensesType[1].disabled = true;
	}else{
		document.forms[0].IsClaimExpensesType[0].disabled = false;
	}	
	
	if(stateCode == 'NJ' || stateCode == 'NM'){
		document.getElementById("ajax_field_IsDollarDefense").checked = true;
		document.getElementById("ajax_field_IsDollarDefense").disabled = true;		
	}else {
		document.getElementById("ajax_field_IsDollarDefense").disabled = false;	
	}function makeDefenseOutsideRuleOldFiling(value){	
		var stateCode = document.forms[0].StateCode.value;	
		if(stateCode == 'AR' ||  stateCode == 'OK'){		
			if(value < 33){
				document.forms[0].IsClaimExpensesType[1].checked = true;
				document.forms[0].IsClaimExpensesType[0].checked = false;
				document.forms[0].IsClaimExpensesType[0].disabled = true;			
			}else{
				document.forms[0].IsClaimExpensesType[0].disabled = false;
				document.forms[0].IsClaimExpensesType[1].disabled = false;
			}		
		}else if(stateCode == 'NM' || stateCode == 'NJ' || stateCode == 'VT'){
			
				document.forms[0].IsClaimExpensesType[1].checked = true;
				document.forms[0].IsClaimExpensesType[0].checked = false;
				document.forms[0].IsClaimExpensesType[0].disabled = true;
				
		}else if(stateCode == 'SD' ){
			document.forms[0].IsClaimExpensesType[0].checked = true;
			document.forms[0].IsClaimExpensesType[1].checked = false;
			document.forms[0].IsClaimExpensesType[1].disabled = true;
		}else{
			document.forms[0].IsClaimExpensesType[0].disabled = false;
		}	
		
		if(stateCode == 'NJ' || stateCode == 'NM'){
			document.getElementById("ajax_field_IsDollarDefense").checked = true;
			document.getElementById("ajax_field_IsDollarDefense").disabled = true;		
		}else {
			document.getElementById("ajax_field_IsDollarDefense").disabled = false;	
		}
		
	}function makeDefenseOutsideRuleOldFiling(value){	
		var stateCode = document.forms[0].StateCode.value;	
		if(stateCode == 'AR' ||  stateCode == 'OK'){		
			if(value < 33){
				document.forms[0].IsClaimExpensesType[1].checked = true;
				document.forms[0].IsClaimExpensesType[0].checked = false;
				document.forms[0].IsClaimExpensesType[0].disabled = true;			
			}else{
				document.forms[0].IsClaimExpensesType[0].disabled = false;
				document.forms[0].IsClaimExpensesType[1].disabled = false;
			}		
		}else if(stateCode == 'NM' || stateCode == 'NJ' || stateCode == 'VT'){
			
				document.forms[0].IsClaimExpensesType[1].checked = true;
				document.forms[0].IsClaimExpensesType[0].checked = false;
				document.forms[0].IsClaimExpensesType[0].disabled = true;
				
		}else if(stateCode == 'SD' ){
			document.forms[0].IsClaimExpensesType[0].checked = true;
			document.forms[0].IsClaimExpensesType[1].checked = false;
			document.forms[0].IsClaimExpensesType[1].disabled = true;
		}else{
			document.forms[0].IsClaimExpensesType[0].disabled = false;
		}	
		
		if(stateCode == 'NJ' || stateCode == 'NM'){
			document.getElementById("ajax_field_IsDollarDefense").checked = true;
			document.getElementById("ajax_field_IsDollarDefense").disabled = true;		
		}else {
			document.getElementById("ajax_field_IsDollarDefense").disabled = false;	
		}
		
	}
	
}

function makeDefenseOutsideRuleEndorsement(){
	
	var value = document.getElementById("ajax_field_LimitSequence").value;
	if(value != ""){
		var IsNewFiling = document.forms[0].IsNewFiling.value;
		//alert(IsNewFiling)
		if(IsNewFiling == 'Y'){
			makeDefenseOutsideRuleNewFilingEndorsement(value);
		} else{
			makeDefenseOutsideRuleOldFilingEndorsement(value);
		}
	}
}

function makeDefenseOutsideRuleNewFilingEndorsement(value){	
	var stateCode = document.forms[0].StateCode.value;

	//alert(stateCode)
	if(stateCode == 'AR' || stateCode == 'NJ' || stateCode == 'MT' || stateCode == 'ND'){		
		if(value < 33){
			document.forms[0].IsClaimExpenseType[1].checked = true;
			document.forms[0].IsClaimExpenseType[0].checked = false;
			//document.forms[0].IsClaimExpenseType[0].disabled = true;	
			readonlyDropdown($("#ajax_field_IsClaimExpenseType_N"));			
		}else{
			//document.forms[0].IsClaimExpenseType[0].disabled = false;
			//document.forms[0].IsClaimExpenseType[1].disabled = false;
			removeReadonlyDropdown($("#ajax_field_IsClaimExpenseType_N"));	
			removeReadonlyDropdown($("#ajax_field_IsClaimExpenseType_Y"));
					
		}		
	}else if(stateCode == 'NM'){
    	var NMRatingVersion=document.getElementById('NMRatingVersion').value;
		//alert(NMRatingVersion);
		if(NMRatingVersion=='NM1')
			{//alert('1');
				if(value < 28){
					document.forms[0].IsClaimExpenseType[1].checked = true;
					document.forms[0].IsClaimExpenseType[0].checked = false;
					document.getElementById("ajax_field_IsDollarDefense").checked = true;
					readonlyDropdown($("#ajax_field_IsClaimExpenseType_Y"));
					readonlyDropdown($("#ajax_field_IsClaimExpenseType_N"));	
					readonlyDropdown($("#ajax_field_IsDollarDefense"));	
				}else if(value >= 28){
					document.forms[0].IsClaimExpensesType[0].checked = true;
					document.forms[0].IsClaimExpensesType[1].checked = false;
					document.getElementById("ajax_field_IsDollarDefense").checked = false;
					//document.forms[0].IsClaimExpensesType[0].disabled = false;
					//document.forms[0].IsClaimExpensesType[1].disabled = true;
					//document.getElementById("ajax_field_IsDollarDefense").disabled = true;
					readonlyDropdown($("#ajax_field_IsClaimExpenseType_Y"));
					removeReadonlyDropdown($("#ajax_field_IsClaimExpenseType_N"));	
					readonlyDropdown($("#ajax_field_IsDollarDefense"));		
				}	
			}
		if(NMRatingVersion=='NM2')
			{
				//alert('2');
			if(value < 28){
				
				document.forms[0].IsClaimExpenseType[1].checked = true;
				document.forms[0].IsClaimExpenseType[0].checked = false;
				document.getElementById("ajax_field_IsDollarDefense").checked = true;
				readonlyDropdown($("#ajax_field_IsClaimExpenseType_Y"));
				readonlyDropdown($("#ajax_field_IsClaimExpenseType_N"));	
				readonlyDropdown($("#ajax_field_IsDollarDefense"));	
			}else if(value >= 28){
					
				removeReadonlyDropdown($("#ajax_field_IsClaimExpenseType_Y"));
				removeReadonlyDropdown($("#ajax_field_IsClaimExpenseType_N"));	
				removeReadonlyDropdown($("#ajax_field_IsDollarDefense"));	
			}	
			
			}
	}

	else if(stateCode == 'SD' ){
		document.forms[0].IsClaimExpenseType[0].checked = true;
		document.forms[0].IsClaimExpenseType[1].checked = false;
		//document.forms[0].IsClaimExpenseType[1].disabled = true;
		readonlyDropdown($("#ajax_field_IsClaimExpenseType_Y"));	
	}else if(stateCode == 'VT'){
		document.forms[0].IsClaimExpenseType[1].checked = true;
		document.forms[0].IsClaimExpenseType[0].checked = false;
		//document.forms[0].IsClaimExpenseType[0].disabled = true;	
		readonlyDropdown($("#ajax_field_IsClaimExpenseType_N"));			
	}else{
		//document.forms[0].IsClaimExpenseType[0].disabled = false;
		removeReadonlyDropdown($("#ajax_field_IsClaimExpenseType_N"));	
	}	
	
	if(stateCode == 'NJ'){
		document.getElementById("ajax_field_IsDollarDefense").checked = true;
		//document.getElementById("ajax_field_IsDollarDefense").disabled = true;
		readonlyDropdown($("#ajax_field_IsDollarDefense"));			
	}else if(stateCode == 'SD'){
		document.getElementById('ajax_field_IsDollarDefense').checked = false;
		//document.getElementById('ajax_field_IsDollarDefense').disabled = true;
		readonlyDropdown($("#ajax_field_IsDollarDefense"));			
	}else if(stateCode != 'NM' && stateCode != 'SD' ){
		//document.getElementById("ajax_field_IsDollarDefense").disabled = false;	
		removeReadonlyDropdown($("#ajax_field_IsDollarDefense"));	
	}
	
	
}

function makeDefenseOutsideRuleOldFilingEndorsement(value){	
	var stateCode = document.forms[0].StateCode.value;	
	if(stateCode == 'AR' ||  stateCode == 'OK'){		
		if(value < 33){
			document.forms[0].IsClaimExpenseType[1].checked = true;
			document.forms[0].IsClaimExpenseType[0].checked = false;
			document.forms[0].IsClaimExpenseType[0].disabled = true;			
		}else{
			document.forms[0].IsClaimExpenseType[0].disabled = false;
			document.forms[0].IsClaimExpenseType[1].disabled = false;
		}		
	}else if(stateCode == 'NM' || stateCode == 'NJ' || stateCode == 'VT'){
		
			document.forms[0].IsClaimExpenseType[1].checked = true;
			document.forms[0].IsClaimExpenseType[0].checked = false;
			document.forms[0].IsClaimExpenseType[0].disabled = true;
			
	}else if(stateCode == 'SD' ){
		document.forms[0].IsClaimExpenseType[0].checked = true;
		document.forms[0].IsClaimExpenseType[1].checked = false;
		document.forms[0].IsClaimExpenseType[1].disabled = true;
	}else{
		document.forms[0].IsClaimExpenseType[0].disabled = false;
	}	
	
	if(stateCode == 'NJ' || stateCode == 'NM'){
		document.getElementById("ajax_field_IsDollarDefense").checked = true;
		document.getElementById("ajax_field_IsDollarDefense").disabled = true;		
	}else {
		document.getElementById("ajax_field_IsDollarDefense").disabled = false;	
	}

}

function makeDefenseOutsideRuleQuoteOption(value){
	var IsNewFiling = document.forms[0].IsNewFiling.value;
	if(IsNewFiling == 'Y'){		
		makeDefenseOutsideRuleQuoteOptionNewFiling(value);
	} else{
		makeDefenseOutsideRuleQuoteOptionOldFiling(value);		
	}
}

function makeDefenseOutsideRuleQuoteOptionNewFiling(value){	
	var stateCode = document.forms[0].StateCode.value;
	if(stateCode == 'AR' || stateCode == 'NJ' || stateCode == 'MT' || stateCode == 'ND'){
		if(value < 33){		
			document.getElementById("IsClaimExpensesType").checked = true;
			document.getElementById("IsClaimExpensesType").disabled = true;	
			//document.getElementById("expenseType").value = 'Y';
			
		}else{		
			document.getElementById("IsClaimExpensesType").disabled = false;
		}
	} else if(stateCode == 'NM'){
		var NMRatingVersion=document.getElementById('NMRatingVersion').value;
		//alert(NMRatingVersion);
		if(NMRatingVersion=='NM1')
		{
			if(value < 28){
			document.getElementById("IsClaimExpensesType").checked = true;
			document.getElementById("IsClaimExpensesType").disabled = true;
			document.getElementById("IsDollarDefense").checked = true;
			document.getElementById("IsDollarDefense").disabled = true;	
			//document.getElementById("expenseType").value = 'Y';
		
		}else if(value >= 28){
			document.getElementById("IsClaimExpensesType").checked = false;
			document.getElementById("IsClaimExpensesType").disabled = true;
			document.getElementById("IsDollarDefense").checked = false;
			document.getElementById("IsDollarDefense").disabled = true;	
			//document.getElementById("expenseType").value = 'N';
		}
		}
		if(NMRatingVersion=='NM2')
		{
			if(value < 28){
				//alert(value);
				document.getElementById("IsClaimExpensesType").checked = true;
				document.getElementById("IsClaimExpensesType").disabled = true;
				document.getElementById("IsDollarDefense").checked = true;
				document.getElementById("IsDollarDefense").disabled = true;	
				//document.getElementById("expenseType").value = 'Y';
			
			}else if(value >= 28){
				document.getElementById("IsClaimExpensesType").checked = false;
				document.getElementById("IsClaimExpensesType").disabled = false;
				document.getElementById("IsDollarDefense").checked = false;
				document.getElementById("IsDollarDefense").disabled = false;	
		}
		
	
		}
	}else if(stateCode == 'SD' ){
		document.getElementById("IsClaimExpensesType").checked = false;
		document.getElementById("IsClaimExpensesType").disabled = true;
		//document.getElementById("expenseType").value = 'N';
	}else if(stateCode == 'VT'){
		document.getElementById("IsClaimExpensesType").checked = true;
		document.getElementById("IsClaimExpensesType").disabled = true;		
		//document.getElementById("expenseType").value = 'Y';
	}else{
		document.getElementById("IsClaimExpensesType").disabled = false;	
	}
	
	if(stateCode == 'NJ'){
		document.getElementById("IsDollarDefense").checked = true;
		document.getElementById("IsDollarDefense").disabled = true;		
	}else if(stateCode == 'SD'){
		document.getElementById("IsDollarDefense").checked = false;
		document.getElementById("IsDollarDefense").disabled = true;		
	}else if(stateCode != 'NM' && stateCode != 'SD'){
		document.getElementById("IsDollarDefense").disabled = false;	
	}
}
function makeDefenseOutsideRuleQuoteOptionOldFiling(value){	
	var stateCode = document.forms[0].StateCode.value;
	if(stateCode == 'AR' ||  stateCode == 'OK'){
		if(value < 33){		
			document.getElementById("IsClaimExpensesType").checked = true;
			document.getElementById("IsClaimExpensesType").disabled = true;		
		}else{		
			document.getElementById("IsClaimExpensesType").disabled = false;
		}
	}else if(stateCode == 'NM' || stateCode == 'NJ' || stateCode == 'VT'){
		document.getElementById("IsClaimExpensesType").checked = true;
		document.getElementById("IsClaimExpensesType").disabled = true;		
	}else if(stateCode == 'SD' ){
		document.getElementById("IsClaimExpensesType").checked = false;
		document.getElementById("IsClaimExpensesType").disabled = true;
	}else{
		document.getElementById("IsClaimExpensesType").disabled = false;	
	}
	
	if(stateCode == 'NJ' || stateCode == 'NM'){
		document.getElementById("IsDollarDefense").checked = true;
		document.getElementById("IsDollarDefense").disabled = true;		
	}else{
		document.getElementById("IsDollarDefense").disabled = false;	
	}
}

function makeDefenseOutsideRuleCoverage(){	
	var value = document.getElementById("LimitSequence").value;
	var stateCode = document.forms[0].StateCode.value;
	if(stateCode == 'ND'){
		if(value < 32){
			document.forms[0].IsClaimExpensesType[1].checked = true;
			document.forms[0].IsClaimExpensesType[0].checked = false;
			//document.forms[0].IsClaimExpensesType[0].disabled = true;
			
		}else{
			
			document.forms[0].IsClaimExpensesType[0] = false;
			document.forms[0].IsClaimExpensesType[0].disabled = false;
		}
	}
}

function resetLimitIndiaction(){	
	if (document.getElementById('LimitSequence') != null){
		limit = document.forms[0].LimitSequence.value = "";	
	}
}

function changeIndicationModifierLabel(){
	var stateCode = $("#StateCode option:selected" ).val();
	var countyDesc = $("#ajax_field_CountyDesc").val();
	var role_id = $("#role_id").val();
	var indicationAccess = $("#IndicationAccess").val();
	var UserNo = $("#UserNo").val();
	var producerCode = $("#ProducerCode").val();
	
	if(role_id == "2" || role_id == "3" || role_id == "6") {
		if("14" == UserNo || "1100" == UserNo || "1106" == UserNo || "1203" == UserNo) {
			if("AZ" == stateCode || "CO" == stateCode || "MA" == stateCode || "MI" == stateCode || "NV" == stateCode || "UT" == stateCode) {
				$("#subProducerModifier").text("Modifier (max +35 or -35) :");//ngtValidPercent = -35; pvtValidPercent = 35;
			} else if("GA" == stateCode) {
				$("#subProducerModifier").text("Modifier (max +40 or -50) :");//ngtValidPercent = -50; pvtValidPercent = 40;
			} else if("IL" == stateCode || "IN" == stateCode || "NC" == stateCode || "TN" == stateCode || "VA" == stateCode || "WI" == stateCode) {
				$("#subProducerModifier").text("Modifier (max +50 or -50) :");//ngtValidPercent = -50; pvtValidPercent = 50;
			} else if("KS" == stateCode || "MN" == stateCode || "PA" == stateCode || "TX" == stateCode) {
				$("#subProducerModifier").text("Modifier (max +40 or -40) :");//ngtValidPercent = -40; pvtValidPercent = 40;
			} else if("SC" == stateCode) {
				$("#subProducerModifier").text("Modifier (max +25 or -40) :");//ngtValidPercent = -40; pvtValidPercent = 25;
			} else {
				$("#subProducerModifier").text("Modifier (max +25 or -25) :");
			}
		}
	} else if(role_id == "7" && indicationAccess == "Y") {
		if(producerCode == "P0000012" && ((stateCode == "CA" && (countyDesc != "Los Angeles" && countyDesc != "Orange")) || stateCode == "OH" || stateCode == "WA" || stateCode == "MI")){
			$("#subProducerModifier").text("Modifier (max +20 or -20) :");
		} else {
			$("#subProducerModifier").text("Modifier (max +10 or -10) :");
		}
	} 
}
function makeDefenseOutsideRuleOnPageLoadIndication(){	
	var limit = '';
	if (document.getElementById('LimitSequence') != null){
		limit = document.forms[0].LimitSequence.value;	
	}
	var IsNewFiling = document.forms[0].IsNewFiling.value;
	if(limit != ''){
		if(IsNewFiling == 'Y'){
			makeDefenseOutsideRuleNewFilingIndication(limit);
		} else{
			makeDefenseOutsideRuleOldFilingIndication(limit);
		}
	}	
}

function makeDefenseOutsideRuleIndication(value){
	var stateCode = document.forms[0].StateCode.value;
	var policyEffectiveDate = document.forms[0].PolicyEffectiveDate.value;
	if(stateCode == '' || policyEffectiveDate ==''){
		
		return;
	}	
	
	var IsNewFiling = document.forms[0].IsNewFiling.value;
	//alert('hello');
	//alert(IsNewFiling);
	if(IsNewFiling == 'Y'){
		makeDefenseOutsideRuleNewFilingIndication(value);
	} else{
		makeDefenseOutsideRuleOldFilingIndication(value);
	}
}

function makeDefenseOutsideRuleNewFilingIndication(value){	
	var stateCode = document.forms[0].StateCode.value;	
	if(stateCode == 'AR' || stateCode == 'NJ' || stateCode == 'MT' || stateCode == 'ND'){		
		if(value < 33){
			document.forms[0].IsClaimExpensesType[1].checked = true;
			document.forms[0].IsClaimExpensesType[0].checked = false;
			document.forms[0].IsClaimExpensesType[0].disabled = true;			
		}else{
			document.forms[0].IsClaimExpensesType[0].disabled = false;
			document.forms[0].IsClaimExpensesType[1].disabled = false;
		}		
	}else if(stateCode == 'NM'){
		var NMRatingVersion=document.getElementById('NMRatingVersion').value;
		//alert(NMRatingVersion);
		if(NMRatingVersion=='NM1')
		{
			
			if(value < 28){
				document.forms[0].IsClaimExpensesType[1].checked = true;
				document.forms[0].IsClaimExpensesType[1].disabled = false;
				document.forms[0].IsClaimExpensesType[0].checked = false;
				document.forms[0].IsClaimExpensesType[0].disabled = true;
				
				document.getElementById("IsDollarDefense").checked = true;
				document.getElementById("IsDollarDefense").disabled = true;	
			}else if(value >= 28){
				document.forms[0].IsClaimExpensesType[0].checked = true;
				document.forms[0].IsClaimExpensesType[0].disabled = false;
				document.forms[0].IsClaimExpensesType[1].checked = false;
				document.forms[0].IsClaimExpensesType[1].disabled = true;
				
				document.getElementById("IsDollarDefense").checked = false;
				document.getElementById("IsDollarDefense").disabled = true;	
			}	
		}
		if(NMRatingVersion=='NM2')
		{
			if(value < 28){
				//alert('inside');
				document.forms[0].IsClaimExpensesType[1].checked = true;
				document.forms[0].IsClaimExpensesType[1].disabled = false;
				document.forms[0].IsClaimExpensesType[0].checked = false;
				document.forms[0].IsClaimExpensesType[0].disabled = true;
				
				document.getElementById("IsDollarDefense").checked = true;
				document.getElementById("IsDollarDefense").disabled = true;	
			}else if(value >= 28){
				
				document.forms[0].IsClaimExpensesType[0].disabled = false;
				document.forms[0].IsClaimExpensesType[1].disabled = false;
				document.getElementById("IsDollarDefense").disabled = false;	
			}
		}
		
	}else if(stateCode == 'SD' ){
		document.forms[0].IsClaimExpensesType[0].checked = true;
		document.forms[0].IsClaimExpensesType[1].checked = false;
		document.forms[0].IsClaimExpensesType[1].disabled = true;
	}else if(stateCode == 'VT'){
		document.forms[0].IsClaimExpensesType[1].checked = true;
		document.forms[0].IsClaimExpensesType[0].checked = false;
		document.forms[0].IsClaimExpensesType[0].disabled = true;			
	}else{
		document.forms[0].IsClaimExpensesType[0].disabled = false;
	}	
	
	if(stateCode == 'NJ'){
		document.getElementById("IsDollarDefense").checked = true;
		document.getElementById("IsDollarDefense").disabled = true;		
	}else if(stateCode == 'SD'){
		document.getElementById('IsDollarDefense').checked = false;
		document.getElementById('IsDollarDefense').disabled = true;		
	}else if(stateCode != 'NM' && stateCode != 'SD' ){
		document.getElementById("IsDollarDefense").disabled = false;	
	}
	
}
function makeDefenseOutsideRuleOldFilingIndication(value){	
	var stateCode = document.forms[0].StateCode.value;	
	if(stateCode == 'AR' ||  stateCode == 'OK'){		
		if(value < 33){
			document.forms[0].IsClaimExpensesType[1].checked = true;
			document.forms[0].IsClaimExpensesType[0].checked = false;
			document.forms[0].IsClaimExpensesType[0].disabled = true;			
		}else{
			document.forms[0].IsClaimExpensesType[0].disabled = false;
			document.forms[0].IsClaimExpensesType[1].disabled = false;
		}		
	}else if(stateCode == 'NM' || stateCode == 'NJ' || stateCode == 'VT'){
		
			document.forms[0].IsClaimExpensesType[1].checked = true;
			document.forms[0].IsClaimExpensesType[0].checked = false;
			document.forms[0].IsClaimExpensesType[0].disabled = true;
			
	}else if(stateCode == 'SD' ){
		document.forms[0].IsClaimExpensesType[0].checked = true;
		document.forms[0].IsClaimExpensesType[1].checked = false;
		document.forms[0].IsClaimExpensesType[1].disabled = true;
	}else{
		document.forms[0].IsClaimExpensesType[0].disabled = false;
	}	
	
	if(stateCode == 'NJ' || stateCode == 'NM'){
		document.getElementById("IsDollarDefense").checked = true;
		document.getElementById("IsDollarDefense").disabled = true;		
	}else {
		document.getElementById("IsDollarDefense").disabled = false;	
	}
	
}

function selectallcheckbox(){
	//alert(document.forms[0].length);
	if(document.forms[0].selectall.checked){
		
		for(var i = 0 ; i < document.forms[0].length ; i++){			
			if(document.getElementById("PolicyKey_"+i) == null)
				break;				
			if(document.getElementById("PolicyKey_"+i) != null ) {
				document.getElementById("PolicyKey_"+i).checked = true;
			}	
		
		}
		
	}
	
	if(!document.forms[0].selectall.checked){		
		for(var i = 0 ; i < document.forms[0].length ; i++){			
			if(document.getElementById("PolicyKey_"+i) == null)
				break;				
			if(document.getElementById("PolicyKey_"+i) != null ) {
				document.getElementById("PolicyKey_"+i).checked = false;
			}
		
		}
		
	}
	getSelectedPolicyKey();
}

function getSelectedPolicyKey(){
	//alert("hello");
	var offrsikListSize=document.getElementById('offrsikListSize').value;
	//alert(offrsikListSize);
	var strPolicyKey = "";
	for(var i = 0 ; i < offrsikListSize ; i++)
	{			
		if(document.getElementById("PolicyKey_"+i) == null)
			break;		
		if(document.getElementById("PolicyKey_"+i).checked) {			
			strPolicyKey = strPolicyKey.concat(document.getElementById("PolicyKey_"+i).value).concat(",");
		}		
	}	
	document.getElementById("strPolicyKey").value = strPolicyKey;
	
	
}

function getSelectedTransactionSequence(){
	var TransactionSequenceHidden = "";
	for(var i = 0 ; i < 100 ; i++){			
		if(document.getElementById("IsQuoteSelected_"+i) == null)
			break;		
		if(document.getElementById("IsQuoteSelected_"+i).checked) {			
			TransactionSequenceHidden = TransactionSequenceHidden.concat(document.getElementById("TransactionSequence_"+i).value).concat(",");
		}		
	}	
	
	document.getElementById("TransactionSequenceHidden").value = TransactionSequenceHidden;
}

function setSelectionOfQuotes(){

	for(var i = 0 ; i < 100 ; i++){			
		if(document.getElementById("IsQuoteSelected_"+i) == null)
			break;		
		if(document.getElementById("IsQuoteSelected_"+i).value == 'Y') {			
			document.getElementById("IsQuoteSelected_"+i).checked = true;
		}else{
			document.getElementById("IsQuoteSelected_"+i).checked = false ;
		}		
	}	
}

function showotherdiv(val, divid){
	if(val == 'other'){
		document.getElementById(divid).style.display="block";
		document.getElementById("SearchHidden").value = "other";
	}else{
		document.getElementById(divid).style.display="none";
		document.getElementById("SearchHidden").value = "";
	}
}	

function showDocTitle(){
	try
	{
		var val = document.getElementById("SearchHidden").value;
		if(val == 'other'){
		showotherdiv(val, 'otherdiv');
		document.getElementById("SearchHidden").value = "";
	}
	else{
		document.getElementById("otherdiv").style.display="none";
		document.getElementById("SearchHidden").value = "";
	}
	}
	catch(e)
	{}
	try
	{
		var isBrokerageDocument=null,isBrokerPolicy=null;
			if(document.getElementById('isBrokerageDocument')!=null)
				isBrokerageDocument=document.getElementById('isBrokerageDocument').value;
			if(document.getElementById('isBrokerPolicy')!=null)
				isBrokerPolicy=document.getElementById('isBrokerPolicy').value;
			if(isBrokerageDocument=='Y' )
					document.getElementById('isBrokerageDoc').checked=true;
			else if(isBrokerPolicy=='Y')
				document.getElementById('isBrokerageDoc').checked=true;
			else
				document.getElementById('isBrokerageDoc').checked=false;
			
	}
	catch(e)
	{}
}	

function resetattorneystate(){
	//alert("1");
	
	var formObject = document.forms[0];
	var licensedStates ="";
	if(document.forms[0].LicensedStates)
		licensedStates = document.forms[0].LicensedStates.value;
	
	var licensedStatesArray = new Array();
	licensedStatesArray = licensedStates.split(", ");
	//alert(licensedStates);
	
	for (var i = 0; i < formObject.length; i++) {
		var n = formObject.elements[i].name;
		if (n != null) {
			if (n.indexOf("StateDesc_") > -1) {
				document.getElementById(n).checked = false;
			}
		}
	}
	
	for (var i = 0; i < formObject.length; i++) {
		var n = formObject.elements[i].name;
		if (n != null) {
			if (n.indexOf("StateDesc_") > -1) {
				//alert(n)
				if (licensedStatesArray != null && licensedStatesArray.length > 0) {
					for (var j = 0; j < licensedStatesArray.length; j++) {
						if (formObject.elements[i].value!=null && formObject.elements[i].value == licensedStatesArray[j]) {
							if(document.getElementById(n)!=null)
		        				document.getElementById(n).checked = true;
							else
								document.getElementById(n).checked = false;
						}
					}
				}
			}
		}
	}
}
function showPopUpEnhanced(el, wdth, hght) {
    window.scrollTo(0,0);
    var wBody = parseInt(document.body.scrollWidth, 10);
    var wPopup = parseInt(document.getElementById(el).style.width, 10);
    document.documentElement.style.overflow = document.body.style.overflow = "auto";
    var w = ((wBody - wPopup) / 2);
    var h = 100;
    var cvr = document.getElementById("cover");
    var dlg = document.getElementById(el);
    //var height = document.body.clientHeight;
    var body = document.body,
    html = document.documentElement;
    
    var height = Math.max( body.scrollHeight, body.offsetHeight, 
                                  html.clientHeight, html.scrollHeight, html.offsetHeight );
    if (dlg != null && w!=null) {
                    dlg.style.display = "block";
                    dlg.style.left = w + "px";
                    dlg.style.top = h + "px";
                    //window.scrollTo(0,0);
                    //alert(w+" "+h);
    }
    if (cvr != null) {
                    cvr.style.height = height + "px";
                    cvr.style.display = "block";
                    //alert(height);
                    cvr.style.position = "fixed";
    }
}
function WESCheckBoxes()
{
	var count=0;
	for(var i = 1 ; i <= 12 ; i++)
	{	
		if(document.getElementById("ajax_field_WES_CheckedValue_"+i).checked)
		count++;				
	}
	document.getElementById("WESCheckCount").value = count;
}

function getRadioButtonValue(inputControlName, outputControlName)
 {
//alert(inputControlName)
	var control = document.getElementsByName(inputControlName);
	var clearStatus;
	if (control) {
		if (control.length) {

			for ( var i = 0; i < control.length; i++) {
				if (control[i].checked) {
					clearStatus = control[i].value;
					//return rad_val;
				}
				/*if(i == ((control.length)-1)){
					alert("none is selected");
					//return null;
				}*/
			}
		} else {
			clearStatus=null;
			//return control.value;
		}
	}
	
	document.getElementById(outputControlName).value = clearStatus;
}

function getPricingApproval(controlName) {

	var control = document.getElementsByName(controlName);
	var pricingClearStatus;
	if (control) {
		if (control.length) {

			for ( var i = 0; i < control.length; i++) {
				if (control[i].checked) {
					pricingClearStatus = control[i].value;
					}

			}
		} else {
			pricingClearStatus = null;

		}
	}
	//alert(pricingClearStatus);
	document.getElementById("ajax_field_pricingClearStatus").value = pricingClearStatus;
	

}
/*
function getSelectedNewsLetter() {
var formObject = document.forms[0];
//alert(formObject.length);
var newsLetterIds = "";
for (var i = 0; i < formObject.length; i++) {
		
        var n = formObject.elements[i].id;
		if (n != null) {
            if (n.indexOf("div_ajaxpostionpremium_freeform") != "-1") 
            	
        {
            	alert("Hello");
           if (formObject.elements[i].checked) 
           {
                var index = n.substring(n.lastIndexOf("_") + 1, n.length);
                var fld = "ajax_field_newsletter_id_" + index;
        //alert(document.getElementById(fld));
                if (newsLetterIds == "") {
                   newsLetterIds = document.getElementById(fld).value;
                } else {
                  newsLetterIds = newsLetterIds + "," + document.getElementById(fld).value;
                    }
                                            
                            }
            }
                }
        alert(n);
        }

var divs = document.getElementsByTagName("div");
for(var i = 0; i < divs.length; i++)
{
	var n=divs[i].id;
	if(n.indexOf("div_ajaxpostionpremium_freeform") != -1) 
	{
		toggleIt(divs[i])
	}
   //do something to each div like
  // alert(n);
}
}
function toggleIt(div) {
	//alert(div.id);
    var hiddenUl = document.getElementById(div.id);
    if (hiddenUl.getAttribute('class') == 'Shown') {
        hiddenUl.setAttribute('class', 'Hidden');
    } 
    else {
        hiddenUl.setAttribute('class', 'Shown');
        alert("hello");
    }
}*/

function loadFirmNewApp() {

    zipMask = new Mask("#####-####");
    var zip = document.getElementById("ajax_field_Zip");
    if (zip != null) {
          zip.value = zipMask.format(zip.value);
          zipMask.attach(zip);
    }
   
	phoneMask = new Mask("(###)###-####");
	var wrkphone = document.getElementById("ajax_field_WorkPhone");
	if (wrkphone != null) {
		wrkphone.value = phoneMask.format(wrkphone.value);
		phoneMask.attach(wrkphone);
	}
	
	var brokerPolicyPhone = document.getElementById("ajax_field_BPhone");
	if (brokerPolicyPhone != null) {
		brokerPolicyPhone.value = phoneMask.format(brokerPolicyPhone.value);
		phoneMask.attach(brokerPolicyPhone);
	}
	
	var wrkphoneAccountSearch = document.getElementById("ajax_field_WorkPhoneSearch");
	if (wrkphoneAccountSearch != null) {
		wrkphoneAccountSearch.value = phoneMask.format(wrkphoneAccountSearch.value);
		phoneMask.attach(wrkphoneAccountSearch);
	}
	
	var otherphone = document.getElementById("ajax_field_otherPhone");
	if (otherphone != null) {
		otherphone.value = phoneMask.format(otherphone.value);
		phoneMask.attach(otherphone);
	}
	
	var SPphone = document.getElementById("ajax_field_SPPhoneNumber");
	if (SPphone != null) {
		SPphone.value = phoneMask.format(SPphone.value);
		phoneMask.attach(SPphone);
	}
	
	var Fax = document.getElementById("ajax_field_Fax");
	if (Fax != null) {
		Fax.value = phoneMask.format(Fax.value);
		phoneMask.attach(Fax);
	}
	
	zipMask = new Mask("#####-####");
    var zip = document.getElementById("ajax_field_SPZip");
    if (zip != null) {
          zip.value = zipMask.format(zip.value);
          zipMask.attach(zip);
    }
}
function showHideInsuranceHistory() {
	var p=getGenericRadioValue("IsFirmHaveLawyersLiabilityInsurance");
	//alert(p);
	if(p=='Y')
	{
		$("#div2").show();
		$("#div1").show();
	}
	else if(p=='N')
	{	
		$("#div1").hide();
		$("#div2").show();
	}else{
		$("#div1").hide();
		$("#div2").hide();
	}
	var proInsPremium=document.getElementById("ajax_field_ProInsPremium");
	if (proInsPremium.value == '0')
		proInsPremium.value = '';
	loadAppointmentFeeInformation('ajax_field_ProInsPremium');
	
	
}
function showHideInsuranceHistory1() {
	var p=getGenericRadioValue("IsFirmHaveLawyersLiabilityInsurance");
		$("#div2").show();
		$("#div1").show();
		//alert(p);
		document.getElementById("ajax_field_IsFirmHaveLawyersLiabilityInsurance").value = p;
		
}
function showHideInsuranceHistory2() 

{
	var p=getGenericRadioValue("IsFirmHaveLawyersLiabilityInsurance");
	$("#div1").hide();
	$("#div2").show();
	//alert(p);
	document.getElementById("ajax_field_IsFirmHaveLawyersLiabilityInsurance").value = p;
}
function showPriorActsSection() { 
	try
	{
		var p=getGenericRadioValue("IsPriorActDateFull");
		$("#fullYear").hide();
		if(p=='N')
		{
			$("#priorActsSpecific").show();
			$("input[name=FirmYear]").val("");
		}
		else
		{
			$("#priorActsSpecific").hide();
		}
	  }
	   catch(e1)
	   {}
	}

function showFullActsSection() { 
	try{
	var p=getGenericRadioValue("IsPriorActDateFull");
	$("#priorActsSpecific").hide();
	if(p=='Y')
	{
		$("#fullYear").show();
		$("input[name=PriorActDatePross]").val("");
	}
	else
	{
		$("#fullYear").hide();
	}
	 }
	 catch(e2)
	 {}
	}
function showIsPriorActDateFullSection()
{
	var p=getGenericRadioValue("IsPriorActDateFull");
	if(p=='N')
	{
		$("#fullYear").hide();
		$("#priorActsSpecific").show();
	}
	else if(p=='Y')
	{
		$("#priorActsSpecific").hide();
		$("#fullYear").show();
	}else{
		$("#fullYear").hide();
		$("#priorActsSpecific").hide();
	}
}

/*
function setRadioButtonsForInsuranceHistory()
{
	var z=document.getElementById("perClaim").value;
	//var z=getGenericRadioValue("IsPerClaim")
	alert(z);
	setRadioValue('IsPerClaim',z);
	
	var q=document.getElementById("ajax_field_IsClaimOptionType").value;
	setRadioValue('IsClaimOptionType',q);
	var r=document.getElementById("ajax_field_IsClaimExpensesType").value;
	
	setRadioValue('IsClaimExpensesType',r);
}*/
function showHideisFirmCoverageForPreceedorFirmsDescription() {
	var p=getGenericRadioValue("IsFirmHaveLawyersLiabilityInsurance");
	if(p='Y')
		$("#trId").show();
	else if(p='N')
		$("#trId").hide();
	
	else
		$("#trId").hide();
	
		
		
}
function setRadioValue(controlName,controlValue)
{
                
        var control =  document.getElementsByName(controlName);
        if(control){
	        if(control.length){
	                        
	            for (var i=0; i < control.length; i++)
	            {              
	                            if (control[i].value == controlValue)
	                            {
	                                            control[i].checked = true;
	                            }
	            }
	        }
        }

}


function maskHtmlPageFields()
{
	numberMask = new Mask("###", "number");
	amountMask = new Mask("$#,###", "number");
	var yearOfFirmEstablished = document.getElementById("ajax_field_YearOfFirmEstablished");
	var lastYearAmount = document.getElementById("ajax_field_lastYearAmount");
	var preLastYearAmount = document.getElementById("ajax_field_preLastYearAmount");
	var totalNumOfNonAttorneyStaff = document.getElementById("ajax_field_TotalNumOfNonAttorneyStaff");
	
	if (yearOfFirmEstablished != null) {
		numberMask.attach(yearOfFirmEstablished);
	}
	if (lastYearAmount != null) {
		numberMask.attach(lastYearAmount);
	}
	if (preLastYearAmount != null) {
		numberMask.attach(preLastYearAmount);
	}
	if (totalNumOfNonAttorneyStaff != null) {
		numberMask.attach(totalNumOfNonAttorneyStaff);
	}
	if (percentFromFirstLargestRevenueClient != null) {
		numberMask.attach(percentFromFirstLargestRevenueClient);
	}
	if (percentFromSecondLargestRevenueClient != null) {
		numberMask.attach(percentFromSecondLargestRevenueClient);
	}
}
function loadrevenueClientsAjaxExpansions()
{	numberMask = new Mask("###", "number");
	var percentFromSecondLargestRevenueClient=document.getElementById("ajax_field_PercentFromSecondLargestRevenueClient");
	var percentFromFirstLargestRevenueClient=document.getElementById("ajax_field_PercentFromFirstLargestRevenueClient");
	var dateRenderedFirstLargestRevenueClient=document.getElementById("ajax_field_DateRenderedFirstLargestRevenueClient");
	var dateRenderedSecondLargestRevenueClient=document.getElementById("ajax_field_DateRenderedSecondLargestRevenueClient");
	if (percentFromFirstLargestRevenueClient != null) {
		numberMask.attach(percentFromFirstLargestRevenueClient);
	}
	if (percentFromSecondLargestRevenueClient != null) {
		numberMask.attach(percentFromSecondLargestRevenueClient);
	}
	if (dateRenderedFirstLargestRevenueClient != null) {
		numberMask.attach(dateRenderedFirstLargestRevenueClient);
	}
	if (dateRenderedSecondLargestRevenueClient != null) {
		numberMask.attach(dateRenderedSecondLargestRevenueClient);
	}
}
function showHidePreceedorFirmsDescription()
{
	var p=getGenericRadioValue("IsFirmCoverageForPreceedorFirms");
	if(p=='Y')
		document.getElementById("rowsm").style.display = "table-row";
	else if(p=='N')
		document.getElementById("rowsm").style.display = "none";
	else
		document.getElementById("rowsm").style.display = "none";
	
}
/*function showPreceedorFirmsDescription()
{
	
	var p=getGenericRadioValue("IsFirmCoverageForPreceedorFirms");
	
	
	document.getElementById("rowsm").style.display = "block";
	
}
function hidePreceedorFirmsDescription()
{	
	var p=getGenericRadioValue("IsFirmCoverageForPreceedorFirms");
	document.getElementById("rowsm").style.display = "none";
	
}*/

function getLoc() {
	
	var items=document.getElementsByName('AOPKey');
	var selectedItems="";
	for (i = 0; i < items.length; i++) { 
		if(items[i].type=='checkbox' && items[i].checked==true)
			selectedItems+=items[i].value+",";
	}
	
	document.getElementById("ajax_field_AOP_values").value = selectedItems;
	
}
function attorneysSelection()
{
//alert("test");	
}
function getLicesedStates()
{
	
	var formObject = document.forms[0];
	var k=0;
    var newsLetterIds = "";
    for (var i = 0; i < formObject.length; i++)
    {
    	var n = formObject.elements[i].name;
    	
    	if (n != null) 
    		{
    		
    		if(n.startsWith("LicensedStates_"))
    			{
    			//alert("working fine"+i);
    			
    			
    			
		    	if (n.indexOf("LicensedStates_") > -1) 
		            {
		        		var index = n.substring(n.lastIndexOf("_") + 1, n.length);
			            var fld = "ajax_field_LicensedStates_" + index;
			            //alert(document.getElementById(fld).title);
			           /* var select1=document.getElementById(fld);
			            var selected1 = [];
			                for (var i = 0; i < select1.length; i++) {
			                    if (select1.options[i].selected) 
			                    	selected1.push(select1.options[i].value);
			                }
			                alert(selected1);
			                if (newsLetterIds == "")
			                {
			                	newsLetterIds = document.getElementById(fld).value;
			                } 
			                else
			                {
			                	newsLetterIds = newsLetterIds + "," + document.getElementById(fld).value;
			                 }*/
		                     
		              }
    			}
          }
    }
}
    function showHideDiv(divName,flagValue)
    {
    
    	//alert(fld+"/"+divName+"/"+flagValue);
    	if(flagValue=='Y')
    		{
    		document.getElementById(divName).style.display = "table-row";
    		}
    	else if(flagValue=='N')
    		{
    		document.getElementById(divName).style.display = "none";
    		}
    	else
    		{
    		document.getElementById(divName).style.display = "none";
    		
    		}
    }
    function showHideClaimsSuppliments()
    {
    	if ((getRadioValue(document.forms[0].IsLawyerProfLiabClaimAgntAppl) == 'Y') || (getRadioValue(document.forms[0].IsAnyActOmmBecomeClaimAgntFirm) == 'Y'))
    		document.getElementById('claimDiv').style.display = "table-row";
    	else
    		document.getElementById('claimDiv').style.display = "none";
    	
    }
    

    function setEwrp()
    {
    		//alert("hello");
    	var formObject = document.forms[0];
    
    	for (var i = 0; i < formObject.length; i++)
        {
    	
        	var n = formObject.elements[i].name;
        	
        	if (n != null) 
        		{
        		if(n.startsWith("IsERPPurchased_"))
    			{	
        			
    				if (n.indexOf("IsERPPurchased_") > -1) 
    	            {
    					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
			            var fld = "ajax_field_IsERPPurchased_" + index;
			            
			            var p=getGenericRadioValue("IsERPPurchased_0");
			          disableERPExpirationDate(fld,p);
    	            }
    			}
        		
        		}
        }
    }
    
    
    function populateTotalFieldValueForLitigation(fieldPrefix, totalValContainerid) {
    	var total = 0;
    	var err = 0;
    	var myVal;

    	//alert(fieldPrefix+'  '+totalValContainerid);
    	for ( var i = 0; i < document.forms[0].length; i++) {
    		var n = document.forms[0].elements[i].name;
    		if (n != null && n.indexOf(fieldPrefix) > -1) {
    			myVal = document.forms[0].elements[i].value;
    			if (myVal != null && myVal != '') {
    				if (!isNumberCorrect(myVal)) {
    					alert("That number is invalid.  Please enter again...");
    					return false;
    				} else {
    					var ival = parseInt(myVal);
    					if (n != "NumberOfPersonnel_1") {
    						total = parseInt(total) + ival;
    					}
    				}
    			}
    		}
    	}
    	//alert(total);
    	document.getElementById(totalValContainerid).innerHTML = total;

    	if (fieldPrefix == 'AOL_PercentageOfRevenue_') {
    		if (total == 100) {
    			document.getElementById(totalValContainerid).style.color = "blue"; 
    		} else {
    			document.getElementById(totalValContainerid).style.color = "red";
    		}
    	}
    	if (fieldPrefix == 'AOL_PercentageOfDefense_') {
    		alert(totalValContainerid);
    		if (total == 100) {
    			document.getElementById(totalValContainerid).style.color = "blue"; 
    		} else {
    			document.getElementById(totalValContainerid).style.color = "red";
    		}
    	}
    	if (fieldPrefix == 'AOL_LargestCaseSize_') {
    		if (total == 100) {
    			document.getElementById(totalValContainerid).style.color = "blue"; 
    		} else {
    			
    			
    			document.getElementById(totalValContainerid).style.color = "red";
    		}
    	}
    	
    	if (fieldPrefix == 'PercentageValue_') {
    		if (total == 100) {
    			document.getElementById(totalValContainerid).style.color = "blue";
    		} else {
    			document.getElementById(totalValContainerid).style.color = "red";
    		}
    	}
    	if (fieldPrefix == 'revenuePercentage_') {
    		if (total == 100) {
    			document.getElementById(totalValContainerid).style.color = "blue";
    		} else {
    			document.getElementById(totalValContainerid).style.color = "red";
    		}
    	}
    }
    

    function showHideisOtherAopDesc(fieldPrefix) 
    {
    	try
    	{
    	numberMask = new Mask("###", "number");
    	var formObject = document.forms[0];
    	var trustAdmin=0,trustCreation=0,aop0=0,aop1=0,aop2=0;
		for ( var i = 0; i < formObject.length; i++) {
	
			var n = formObject.elements[i].name;
			if (n != null) {
	
				if (n.indexOf(fieldPrefix) > -1) {
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_" + fieldPrefix + index;
					var aopVal=document.getElementById(fld);
					
					numberMask.attach(aopVal);
					if (aopVal.value == '0')
						aopVal.value = '';
					var percentage=document.getElementById(fld).value;
					if (index ==11)
					{
						if (percentage != null && percentage != '' && percentage>0)
								document.getElementById('descDiv').style.display = "table-row";
						else
							document.getElementById('descDiv').style.display = "none";
					}
					if (index==3)
					{
						if(percentage != null && percentage != '')
							trustAdmin=percentage;
					}
					if (index==12)
					{ if(percentage != null && percentage != '')
						trustCreation=percentage;
					}
					if (index==0)
					{ if(percentage != null && percentage != '')
						aop0=percentage;
					}
					
					if (index==1)
					{ if(percentage != null && percentage != '')
						aop1=percentage;
					}
					
					if (index==2)
					{ if(percentage != null && percentage != '')
						aop2=percentage;
					}
					
					
				}
				
			
				if (n.indexOf("AssetsValue_") > -1) 
				{
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_AssetsValue_" + index;
					var assetVal=document.getElementById(fld);
					numberMask.attach(assetVal);
					if (assetVal.value == '0')
						assetVal.value = '';
					
					
					loadAppointmentFeeInformation(fld);
				}
			
			}
			
		}	
    	}
		catch(e1)
		{}
		
		
		try
		{
			var isCannibSuppFlow=document.getElementById("isCannibSuppFlow").value;
			
		  	if(isCannibSuppFlow==null || isCannibSuppFlow=='')
		  		isCannibSuppFlow='N';
		  	
			
			if(isCannibSuppFlow=="Y")
				{
				//alert('hi');
				var beneficiaryInterestWillsTrust=document.getElementById("ajax_field_beneficiaryInterestWillsTrust");
				numberMask.attach(beneficiaryInterestWillsTrust);
				if (beneficiaryInterestWillsTrust.value == '0')
					beneficiaryInterestWillsTrust.value = '';
				if(trustAdmin>0 || trustCreation>0)
				{
						document.getElementById('EstateMatterAmountsDiv').style.display = "table-row";
						document.getElementById('EstateMatterAmountsDiv1').style.display = "table-row";
						//document.getElementById('largestTrustsDiv2').style.display = "table-row";
					}
				else
					{
						document.getElementById('EstateMatterAmountsDiv').style.display = "none";
						document.getElementById('EstateMatterAmountsDiv1').style.display = "none";
						
					}
				
				if(aop0>0 || aop1>0 || aop2>0)
					{
						document.getElementById('div9').style.display = "table-row";
						document.getElementById('div10').style.display = "table-row";
					}
				else
					{
					document.getElementById('div9').style.display = "none";
					document.getElementById('div10').style.display = "none";
					}
				}
			else
				{
				
				var pastYear=document.getElementById("ajax_field_willEstatesHandledPastYear");
				numberMask.attach(pastYear);
				if (pastYear.value == '0')
					pastYear.value = '';
					if(trustAdmin>0 || trustCreation>0)
						{
							document.getElementById('largestTrustsDiv').style.display = "table-row";
							document.getElementById('largestTrustsDiv1').style.display = "table-row";
							document.getElementById('largestTrustsDiv2').style.display = "table-row";
						}
					else
						{
							document.getElementById('largestTrustsDiv').style.display = "none";
							document.getElementById('largestTrustsDiv1').style.display = "none";
							document.getElementById('largestTrustsDiv2').style.display = "none";
						}
					
					if(aop0>0 || aop1>0 || aop2>0)
						document.getElementById('div9').style.display = "table-row";
					else
						document.getElementById('div9').style.display = "none";
						
					}
			
			}
		catch(e10)
		{}
		
   }
    
    
    
  function showHideWillsEstate()
  {
	  try
	  {
	  	var isCannibSuppFlow=document.getElementById("isCannibSuppFlow").value;
	  	//alert(isCannibSuppFlow);
	  	if(isCannibSuppFlow==null || isCannibSuppFlow=='')
	  		isCannibSuppFlow='N';
	  var p=getGenericRadioValue("IsAttorneyServeAsExecutorTrustee");
	  if(isCannibSuppFlow=='Y')
		  {
			  if(p=='Y')
				{
					document.getElementById("RowID12").style.display = "table-row";
					document.getElementById("RowID13").style.display = "table-row";
				}
			else
				{
					document.getElementById("RowID12").style.display = "none";
					document.getElementById("RowID13").style.display = "none";
				}
		  
		  }
	  else
		  {
		  	if(p=='Y')
		  		document.getElementById("trId1").style.display = "table-row";
		  	else
		  		document.getElementById("trId1").style.display = "none";
		  }		
	  }
	  catch(e1)
	  {}
	  try
		{
			var formObject = document.forms[0];
	    	var trustAdmin=0,trustCreation=0,aop0=0,aop1=0,aop2=0;
			for ( var i = 0; i < formObject.length; i++) {
		
				var n = formObject.elements[i].name;
				if (n != null) {
					
					if (n.indexOf("NumberOfMattersValued_") > -1) 
					{
						var index = n.substring(n.lastIndexOf("_") + 1, n.length);
						var fld = "ajax_field_NumberOfMattersValued_" + index;
						var rangeVal=document.getElementById(fld);
						numberMask.attach(rangeVal);
						if (rangeVal.value == '0')
							rangeVal.value = '';
						
						
						
					}
				}
			}
		}
		catch(e12)
		{}
  }
  
  function validateAttorney()
  {  
	 var p=document.getElementById("ajax_field_PolicyStatusKey").value;		 
	 var attyNo=document.getElementById("ajax_field_addAttorneys").value;
	 var dataEmpty='N';
	 var fteValue='N';
	 var isValidDate=true;
	 
	 var newFiling='';
	 if(document.getElementById("IsNewFiling") != null){
		 newFiling = document.getElementById("IsNewFiling").value;
	 }
	 
	 if(newFiling == 'N')
		 fteValue='Y';
	 //alert(attyNo);
	 
	 if(attyNo > 1) {
		for (var index = 0; index < attyNo; index++) {	
			
			var attyName = "AttorneyName_"+ index;
			var desId = "ajax_field_DesignationId_"+ index;
			var licState = "ajax_field_LicensedStates_"+ index;
			var wrkHour = "ajax_field_AnnualWorkedHours_"+ index;
			var joinDate = "ajax_field_AttorneyPriorActDate_"+ index;
			var priorDate = "ajax_field_PriorActDateAttorneysNew_"+ index;			
			var yrsPractice = "ajax_field_NumberOfYearsInPractice_"+ index;
			//alert(joinDate + ' '+ priorDate);
			//alert(document.getElementById(wrkHour).value);
			
			if((document.getElementById(attyName) != null &&  document.getElementById(attyName).value =='')
				|| (document.getElementById(desId) != null &&  document.getElementById(desId).value =='')
				|| (document.getElementById(licState) != null &&  document.getElementById(licState).value =='')
				|| (document.getElementById(wrkHour) != null &&  document.getElementById(wrkHour).value =='')
				|| (document.getElementById(joinDate) != null &&  document.getElementById(joinDate).value =='')
				|| (document.getElementById(priorDate) != null &&  document.getElementById(priorDate).value =='')
				|| (document.getElementById(yrsPractice) != null &&  document.getElementById(yrsPractice).value =='')					
			){
				dataEmpty='Y';
				//alert('Data is empty');
			} 
			if(document.getElementById(wrkHour) != null ){
				var hr = document.getElementById(wrkHour).value;
				hr = parseFloat(hr);
				if(hr > 1000){
					fteValue='Y';
					//alert('fteValue=Y');
				}
			}
			if(document.getElementById(joinDate) != null ){
				//alert(document.getElementById(joinDate).value);
				isValidDate = validateDate(document.getElementById(joinDate).value);
			} 
			if(document.getElementById(priorDate) != null ){
				//alert(document.getElementById(priorDate).value);
				isValidDate = validateDate(document.getElementById(priorDate).value);
			}
		}
	 } else if(attyNo == 1){
		 fteValue = 'Y'
		 for (var index = 0; index < attyNo; index++) {					
			var attyName = "AttorneyName_"+ index;
			var desId = "ajax_field_DesignationId_"+ index;
			var licState = "ajax_field_LicensedStates_"+ index;
			var wrkHour = "ajax_field_AnnualWorkedHours_"+ index;
			var joinDate = "ajax_field_AttorneyPriorActDate_"+ index;
			var priorDate = "ajax_field_PriorActDateAttorneysNew_"+ index;			
			var yrsPractice = "ajax_field_NumberOfYearsInPractice_"+ index;
			//alert(joinDate + ' '+ priorDate);
			//alert(document.getElementById(wrkHour).value);
			
			if((document.getElementById(attyName) != null &&  document.getElementById(attyName).value =='')
				|| (document.getElementById(desId) != null &&  document.getElementById(desId).value =='')
				|| (document.getElementById(licState) != null &&  document.getElementById(licState).value =='')
				|| (document.getElementById(wrkHour) != null &&  document.getElementById(wrkHour).value =='')
				|| (document.getElementById(joinDate) != null &&  document.getElementById(joinDate).value =='')
				|| (document.getElementById(priorDate) != null &&  document.getElementById(priorDate).value =='')
				|| (document.getElementById(yrsPractice) != null &&  document.getElementById(yrsPractice).value =='')					
			){
				dataEmpty='Y';
				//alert('Data is empty');
			} 
			if(document.getElementById(wrkHour) != null ){
				var hr = document.getElementById(wrkHour).value;
				hr = parseFloat(hr);
				if(hr > 1000){
					fteValue='Y';
					//alert('fteValue=Y');
				}
			}
			if(document.getElementById(joinDate) != null ){
				//alert(document.getElementById(joinDate).value);
				isValidDate = validateDate(document.getElementById(joinDate).value);
			} 
			if(document.getElementById(priorDate) != null ){
				//alert(document.getElementById(priorDate).value);
				isValidDate = validateDate(document.getElementById(priorDate).value);
			}	
			
		}
	 }
		 
	 //alert('dataEmpty..' + dataEmpty +'fteValue..' + fteValue + ' isValidDate.. ' + isValidDate);	 
	 if(dataEmpty == 'Y'){
		 showPopUpOverloaded('emptyAttorney','500','400');
		 return false;
	 } else if(fteValue == 'N'){
		 showPopUpOverloaded('fte','500','400');
		 return false;
	 } else if(!isValidDate){
		 showPopUpOverloaded('invalidDate','500','400');
		 return false;
	 }
	 
	 return true;
  }
  
  function validateFeeSuitData()
  {  
	  var result = 0;
	  if($.trim($('input[name=IsApplInitiatedLawsuitForFirm]:checked').val()) === 'Y'){
		  var feeSuitTrLength = $('#ajax_field_addFeeSuits :selected').val();
		  for(var feeSuitCount = 0; feeSuitCount < feeSuitTrLength; feeSuitCount++){
			  var trObj = $("#FeeSuitData_list_01").find("tbody").find("tr[id=FeeSuitData_list_mom_data_01_" + feeSuitCount + "]");
			  var AmountOfFeesSued = trObj.find("input[name=AmountOfFeesSued_" + feeSuitCount + "]").val();
			  var SuitFilesDateFees = trObj.find("input[name=SuitFilesDateFees_" + feeSuitCount + "]").val();
			  var IsAllegOfLegalMalPrac = trObj.find("input[name=IsAllegOfLegalMalPrac_" + feeSuitCount + "]:checked").val();
			  var FeeSuitDispositionKey = trObj.find("#ajax_field_FeeSuitDispositionKey_" + feeSuitCount + " :selected").val();
			  
			  if($.trim(AmountOfFeesSued) == "" || $.trim(SuitFilesDateFees) == "" || $.trim(FeeSuitDispositionKey) == "" || $.trim(IsAllegOfLegalMalPrac) == ""){
				  result = 1;
				  break;
			  }
		  }
	  }
	  if(result == 0){
		  return true;
	  } else {
		  var cvr = document.getElementById("cover");
		  var body = document.body,
		  html = document.documentElement;
			
		  var height = Math.max( body.scrollHeight, body.offsetHeight, 
			                              html.clientHeight, html.scrollHeight, html.offsetHeight );
			
		  if (cvr != null) {
			  cvr.style.height = height + "px";
			  cvr.style.display = "block";
			  cvr.style.position = "fixed";
		  }
		  $('#msgBoxFeeSuitData').show();
		  return false;
	  }
  }
  
  function loadLawPractice()
  {  
	 var numberMask = new Mask("###", "number");
	 var formObject = document.forms[0];
	 
	 var p=document.getElementById("ajax_field_PolicyStatusKey").value;
	 var q=getGenericRadioValue("IsAttorneyAddedDeleted");
	 
	 var newFiling='';
	 if(document.getElementById("IsNewFiling") != null){
		 newFiling = document.getElementById("IsNewFiling").value;
	 }
	 //alert('newFiling.. ' + newFiling);
	 if(p==2){
		
		 if(q=='Y')
			 document.getElementById("addAttorneysDiv").style.display = "block";
		 else
			 document.getElementById("addAttorneysDiv").style.display = "none";
		 }
	 
		for ( var i = 0; i < formObject.length; i++) {
	
			var n = formObject.elements[i].name;
			if (n != null) 
			{
				if (n.indexOf("AnnualWorkedHours_") > -1) 
				{
					try{
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_AnnualWorkedHours_"+ index;
					var anualHours=document.getElementById(fld);
					numberMask.attach(anualHours);
					/*if (anualHours.value == '0')
						anualHours.value = '';*/
					var annualHoursAttorney=anualHours.value;
					//alert(annualHoursAttorney);
					if(annualHoursAttorney.length > 0 && isNumberCorrect(annualHoursAttorney))
					{	
						//alert('newFiling.. ' + newFiling +' annualHoursAttorney..' + annualHoursAttorney);
						if(newFiling == 'N'){
							if(annualHoursAttorney > 1000)
								document.getElementById("ajax_field_IsNonRatedAttorney1_FixedValue_"+index).disabled = true;
							else
								document.getElementById("ajax_field_IsNonRatedAttorney1_FixedValue_"+index).disabled = false;
						} else {
							document.getElementById("ajax_field_IsNonRatedAttorney1_FixedValue_"+index).disabled = true;
						}
					}
					}catch (e) {
						// TODO: handle exception
					}
				}
				if (n.indexOf("NumberOfYearsInPractice_") > -1) 
				{
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_NumberOfYearsInPractice_" + index;
					var yearsInPrac=document.getElementById(fld);
					numberMask.attach(yearsInPrac);
					/*if (yearsInPrac.value == '0')
						yearsInPrac.value = '';*/
				}
				if (n.indexOf("LicensedStates_") > -1) 
				{
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_LicensedStates_" + index;
					values=document.getElementById("ajax_field_combinedStates_"+index).value;
					//alert(values);
					setSelectedIndex(document.getElementById(fld),values);
				}
			}
		}
  }
  function loadChangeAttorney()
  {
	 // alert('hello');
	  var formObject = document.forms[0];
	  
	  for ( var i = 0; i < formObject.length; i++) 
  	{
		var n = formObject.elements[i].name;
		// alert(n);
		if (n != null) 
		{
			 //alert('hello1');
		  if (n.indexOf("LicensedStates_") > -1) 
			{
			 
				var index = n.substring(n.lastIndexOf("_") + 1, n.length);
				var fld = "ajax_field_LicensedStates_" + index;
				values=document.getElementById("ajax_field_combinedStates_"+index).value;
			
				//alert(values);
				setSelectedIndex(document.getElementById(fld),values);
			}
		}
  }
  }
  function setSelectedIndex(s, v) {
	  //alert(v);
	var ss = v.split(",");
	for ( var j in ss) {
		for ( var i = 0; i < s.options.length; i++) {

			if (s.options[i].text == ss[j]) {

				s.options[i].selected = true;

			}

		}

	}

}

  
  function loadFirm()
  {
	  var numberMask = new Mask("###", "number");
	  var establishedDate=document.getElementById("ajax_field_YearOfFirmEstablished");
	  var lastYearAmount=document.getElementById("ajax_field_lastYearAmount");
	  var nonAttorneyStaff=document.getElementById("ajax_field_TotalNumOfNonAttorneyStaff");
		numberMask.attach(establishedDate);
		if (establishedDate.value == '0')
			establishedDate.value = '';
		numberMask.attach(lastYearAmount);
		if (lastYearAmount.value == '0')
			lastYearAmount.value = '';
		numberMask.attach(nonAttorneyStaff);
		if (nonAttorneyStaff.value == '0')
			nonAttorneyStaff.value = '';
  }
  function loadAttorneyInsuranceInfo()
  {
	  loadAppointmentFeeInformation('ajax_field_premiumFirstAttoryneyInsurance');
	  loadAppointmentFeeInformation('ajax_field_premiumSecondAttorneyInsurance');
	  loadAppointmentFeeInformation('ajax_field_premiumThirdAttorneyInsurance');
	   numberMask = new Mask("###", "number");
	  var policyPeriod1=document.getElementById("ajax_field_policyPeriodFirstAttoryneyInsurance");
	  var policyPeriod2=document.getElementById("ajax_field_policyPeriodSecondAttorneyInsurance");
	  var policyPeriod3=document.getElementById("ajax_field_policyPeriodThirdAttorneyInsurance");
	  var numberOfAttorneysFirstAttoryneyInsurance=document.getElementById("ajax_field_numberOfAttorneysFirstAttoryneyInsurance");
	  var numberOfAttorneysSecondAttorneyInsurance=document.getElementById("ajax_field_numberOfAttorneysSecondAttorneyInsurance");
	  var numberOfAttorneysThirdAttorneyInsurance=document.getElementById("ajax_field_numberOfAttorneysForThirdAttorneyInsurance");
	  var premium1=document.getElementById("ajax_field_premiumFirstAttoryneyInsurance");
	  var premium2=document.getElementById("ajax_field_premiumSecondAttorneyInsurance");
	  var premium3=document.getElementById("ajax_field_premiumThirdAttorneyInsurance");
	  
	  numberMask.attach(premium1);
		if (premium1.value == '0')
			premium1.value = '';
		numberMask.attach(premium2);
		if (premium2.value == '0')
			premium3.value = '';
		numberMask.attach(premium3);
		if (premium3.value == '0')
			premium3.value = '';
		
		numberMask.attach(policyPeriod1);
		if (policyPeriod1.value == '0')
			policyPeriod1.value = '';
		numberMask.attach(policyPeriod2);
		if (policyPeriod2.value == '0')
			policyPeriod2.value = '';
		numberMask.attach(policyPeriod3);
		if (policyPeriod3.value == '0')
			policyPeriod3.value = '';
		
		numberMask.attach(numberOfAttorneysFirstAttoryneyInsurance);
		if (numberOfAttorneysFirstAttoryneyInsurance.value == '0')
			numberOfAttorneysFirstAttoryneyInsurance.value = '';
		
		numberMask.attach(numberOfAttorneysSecondAttorneyInsurance);
		if (numberOfAttorneysSecondAttorneyInsurance.value == '0')
			numberOfAttorneysSecondAttorneyInsurance.value = '';
		
		numberMask.attach(numberOfAttorneysThirdAttorneyInsurance);
		if (numberOfAttorneysThirdAttorneyInsurance.value == '0')
			numberOfAttorneysThirdAttorneyInsurance.value = '';
		
		
  }
  function showHideClaimsSupp()
  {
	  var p=getGenericRadioValue("IsAnyActOmmBecomeClaimAgntFirm");
	  var q=getGenericRadioValue("IsLawyerProfLiabClaimAgntAppl");
	  var r=getGenericRadioValue("IsPersonnelBeSubOfAnyInvest");
	  var s=getGenericRadioValue("IsAttorneyDeclineForProfLiability");
	  
	 	if(p=='Y' || q=='Y')
			$("*.claimsSupp").show();
		else
			$("*.claimsSupp").hide();
	  
	 		
	 	if(r=='Y')
	 		document.getElementById("criminalChargeDesc").style.display = "table-row";
		else
			document.getElementById("criminalChargeDesc").style.display = "none";
	 	
	 	if(s=='Y')
	 		document.getElementById("declinedDesc").style.display = "table-row";
		else
			document.getElementById("declinedDesc").style.display = "none";
	 			
  }
  function loadPredecessorSupplement()
  {

	  
		 var numberMask = new Mask("###", "number");
		 var formObject = document.forms[0];
			for ( var i = 0; i < formObject.length; i++) {
		
				var n = formObject.elements[i].name;
				if (n != null) {
		
					if (n.indexOf("NumberOfAttorneyAtDiss_") > -1) {
						var index = n.substring(n.lastIndexOf("_") + 1, n.length);
						var fld = "ajax_field_NumberOfAttorneyAtDiss_"+ index;
						var NumberOfAttorneyAtDiss=document.getElementById(fld);
						numberMask.attach(NumberOfAttorneyAtDiss);
						if (NumberOfAttorneyAtDiss.value == '0')
							NumberOfAttorneyAtDiss.value = '';
						}
					
				
					if (n.indexOf("NumberOfAttorneyAtApplFirm_") > -1) 
					{
						var index = n.substring(n.lastIndexOf("_") + 1, n.length);
						var fld = "ajax_field_NumberOfAttorneyAtApplFirm_" + index;
						var NumberOfAttorneyAtApplFirm=document.getElementById(fld);
						numberMask.attach(NumberOfAttorneyAtApplFirm);
						if (NumberOfAttorneyAtApplFirm.value == '0')
							NumberOfAttorneyAtApplFirm.value = '';
					}
				}
				
			}
		
  }
  function loadBannkruptcy()
  {
	  numberMask = new Mask("###", "number");
	  var personalBankrupties=document.getElementById("ajax_field_personalBankrupties");
	  var commercialBankruptcies=document.getElementById("ajax_field_commercialBankruptcies");
	  numberMask.attach(personalBankrupties);
		if (personalBankrupties.value == '0')
			personalBankrupties.value = '';
		 numberMask.attach(commercialBankruptcies);
			if (commercialBankruptcies.value == '0')
				commercialBankruptcies.value = '';
			
		
				  
				 var numberMask = new Mask("###", "number");
				 var formObject = document.forms[0];
					for ( var i = 0; i < formObject.length; i++) {
				
						var n = formObject.elements[i].name;
						if (n != null) {
				
							if (n.indexOf("Debtor_") > -1) {
								var index = n.substring(n.lastIndexOf("_") + 1, n.length);
								var fld = "ajax_field_Debtor_"+ index;
								var Debtor=document.getElementById(fld);
								
								numberMask.attach(Debtor);
								if (Debtor.value == '0')
									Debtor.value = '';
								}
							
						
							if (n.indexOf("Creditor_") > -1) 
							{
								var index = n.substring(n.lastIndexOf("_") + 1, n.length);
								var fld = "ajax_field_Creditor_" + index;
								var Creditor=document.getElementById(fld);
								numberMask.attach(Creditor);
								if (Creditor.value == '0')
									Creditor.value = '';
							}
							

							if (n.indexOf("Trustee_") > -1) 
							{
								var index = n.substring(n.lastIndexOf("_") + 1, n.length);
								var fld = "ajax_field_Trustee_" + index;
								var Trustee=document.getElementById(fld);
								numberMask.attach(Trustee);
								if (Trustee.value == '0')
									Trustee.value = '';
							}
							
						}
					
					
				  
			  }
  }
  function calculateBanckruptcyPercentage()
  {	
	  
	  var personalBankrupties=document.getElementById("ajax_field_personalBankrupties").value;
	  var commercialBankruptcies=document.getElementById("ajax_field_commercialBankruptcies").value;
	  if(personalBankrupties=='' || personalBankrupties==null)
		  personalBankrupties=0;
	  if(commercialBankruptcies=='' || commercialBankruptcies==null)
		  commercialBankruptcies=0;
	 // alert(personalBankrupties+'          '+commercialBankruptcies);
			total=parseInt(personalBankrupties)+parseInt(commercialBankruptcies);
			//alert(total);
			document.getElementById("ajax_field_totalPercentage").innerHTML = total;
			if (total == 100) {
				document.getElementById("ajax_field_totalPercentage").style.color = "blue";
			} else {
				document.getElementById("ajax_field_totalPercentage").style.color = "red";
			}
	  
  }
  function loadTaxSupplement()
  {
	  
	 var numberMask = new Mask("###", "number");
	 var formObject = document.forms[0];
		for ( var i = 0; i < formObject.length; i++) {
	
			var n = formObject.elements[i].name;
			if (n != null) {
	
				if (n.indexOf("revenuePercentage_") > -1) {
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_revenuePercentage_"+ index;
					var revenuePercentage=document.getElementById(fld);
					
					numberMask.attach(revenuePercentage);
					if (revenuePercentage.value == '0')
						revenuePercentage.value = '';
					}
			}
			
		}
		
	  
  }
  function showHideisTaxOtherAopDesc(fieldPrefix) 
  {
  	numberMask = new Mask("###", "number");
  	var formObject = document.forms[0];
		for ( var i = 0; i < formObject.length; i++) {
			
			var n = formObject.elements[i].name;
			if (n != null) {
	
				if (n.indexOf(fieldPrefix) > -1) {
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_" + fieldPrefix + index;
					var aopVal=document.getElementById(fld);
					
					numberMask.attach(aopVal);
					if (aopVal.value == '0')
						aopVal.value = '';
					
					if (index ==8)
					{
						if (document.getElementById(fld).value)
								document.getElementById('descDiv').style.display = "table-row";
						else
							document.getElementById('descDiv').style.display = "none";
					}
	
				}
			}
			
		}		
}
 function loadCollectionSupplement()
 {
		numberMask = new Mask("###", "number");
		 var averageCases=document.getElementById("ajax_field_averageCases");
		 var consumerCollections=document.getElementById("ajax_field_consumerCollections");
		 var commercialollections=document.getElementById("ajax_field_commercialollections");
		 var individualCollections=document.getElementById("ajax_field_individualCollections");
		  numberMask.attach(averageCases);
			if (averageCases.value == '0')
				averageCases.value = '';
			numberMask.attach(consumerCollections);
			if (consumerCollections.value == '0')
				consumerCollections.value = '';
			numberMask.attach(commercialollections);
			if (commercialollections.value == '0')
				commercialollections.value = '';
			numberMask.attach(individualCollections);
			if (individualCollections.value == '0')
				individualCollections.value = '';
		
			var consumerCollections=document.getElementById("ajax_field_consumerCollections").value;
			 var commercialollections=document.getElementById("ajax_field_commercialollections").value;
			  if(consumerCollections=='' || consumerCollections==null)
				  consumerCollections=0;
			  if(commercialollections=='' || commercialollections==null)
				  commercialollections=0;
			 // alert(personalBankrupties+'          '+commercialBankruptcies);
					total=parseInt(consumerCollections)+parseInt(commercialollections);
					
					document.getElementById("ajax_field_totalPercentage").innerHTML = total;
					if (total == 100) {
						document.getElementById("ajax_field_totalPercentage").style.color = "blue";
					} else {
						document.getElementById("ajax_field_totalPercentage").style.color = "red";
					}
			  
					loadAppointmentFeeInformation('ajax_field_individualCollections');
 }
 function loadCopyRightTrademark()
 {
	 var p=getGenericRadioValue("otherServices");
	 	if(p=='Y')
			document.getElementById("trId").style.display = "table-row";
		else
			document.getElementById("trId").style.display = "none";
	 
 }
 function loadFeeSupplement()
 {
	  
	 var numberMask = new Mask("###", "number");
	 var formObject = document.forms[0];
		for ( var i = 0; i < formObject.length; i++) {
	
			var n = formObject.elements[i].name;
			if (n != null) {
	
				if (n.indexOf("AmountOfFeesSued_") > -1) {
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_AmountOfFeesSued_"+ index;
					var amount=document.getElementById(fld);
					
					numberMask.attach(amount);
					if (amount.value == '0')
						amount.value = '';
					
					loadAppointmentFeeInformation(fld);
				}
				
			}
			
		}
		
	  
  }
 function loadHouseKeeping()
 {
	 phoneMask = new Mask("(###)###-####");

		var wrkphone = document.getElementById("ajax_field_WorkPhone");
		var cntphone = document.getElementById("ajax_field_otherPhone");
		if (wrkphone != null) {
			wrkphone.value = phoneMask.format(wrkphone.value);
			phoneMask.attach(wrkphone);
		}
		if (cntphone != null) {
			cntphone.value = phoneMask.format(cntphone.value);
			phoneMask.attach(cntphone);
		}
 }
 function validateWebAddress(m,fieldname) {
     var strValue = m;
     if(strValue=='')
            return;
            
     var objRegExp  = /^(((ht|f)tp(s?))\:\/\/)?(www.|[a-zA-Z0-9_-]+\.)*[a-zA-Z0-9_-]+(\.[a-z]{2,6}){1,2}$/;    
     checkFocus=false;
     if(!objRegExp.test(strValue))
     {
            checkEl=fieldname;
            alert("Website address is not valid");
            
            setTimeout(function(){if(checkEl)checkEl.focus();checkEl.select();},100);
     }
     checkFocus=true;
}
 function validateEmail(m,fieldname) {
     var strValue = m;
     if(strValue=='')
            return;
     
     //var objRegExp  = /(^[a-z]([a-z_\.]*)@([a-z_\.]*)([.][a-z]{3})$)|(^[a-z]([a-z_\.]*)@([a-z_\.]*)(\.[a-z]{3})(\.[a-z]{2})*$)/i;
     //var objRegExp = /b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4};       
     var objRegExp  = /(^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,3})+$)/;
     checkFocus=false;
     if(!strValue.match(objRegExp))
     {
            checkEl=fieldname;
            alert("Email address is not valid");
            
            setTimeout(function(){if(checkEl)checkEl.focus();checkEl.select();},100);
            
     }      
     checkFocus=true;
}
 
 function setReadWriteAccessAttorneysList(div_id)
 {
	 var p=getGenericRadioValue("IsAttorneyAddedDeleted");
	 var q=document.getElementById("ajax_field_PolicyStatusKey").value;
	 var inputs=document.getElementById(div_id).getElementsByTagName('input');
	//var selects=document.getElementById(div_id).getElementsByTagName('select');
	//var buttons=document.getElementById(div_id).getElementsByTagName('button');
	 
	 var formObject = document.forms[0];
	 var newFiling='';
	 if(document.getElementById("IsNewFiling") != null){
		 newFiling = document.getElementById("IsNewFiling").value;
	 }
	 
    if(q==2)
    {	
   	 	for(var i=0; i<inputs.length; ++i)
        {
    	 if(p=='Y'){
			 inputs[i].disabled=false;  
			 //document.getElementById("ajax_field_LicensedStates_0").className = "btn-default";
		 }
    	 else
    		inputs[i].disabled=true;
    	 }
    /* for(var i=0; i<selects.length; ++i)
     {
	 	 if(p=='Y')
	 	{
	 		 selects[i].disabled=false;   
	 		//document.getElementById("ajax_field_LicensedStates_0").className = "btn-default";
	 	}
	 		 else
	 		selects[i].disabled=true;
 	 }*/
     var index = 9;
     if (p=="Y") {
         $('#AttorneysDetailsList_list_01 th:nth-child(' + index + ')').show();
         $('#AttorneysDetailsList_list_01 td:nth-child(' + index + ')').show();
     } else {
         $('#AttorneysDetailsList_list_01 th:nth-child(' + index + ')').hide();
         $('#AttorneysDetailsList_list_01 td:nth-child(' + index + ')').hide();
     }
    /* for(var i=0; i<buttons.length; ++i)
     {
	 	 if(p=='Y')
	 		buttons[i].disabled=false;   
	 	else
	 		buttons[i].disabled=true;
 	 }*/         
        
    }
  
 }
 
 function showhide(id) {
	 
	 if(id != null && id != '')
		{
		 if (id=='aboutyou')
			 {
	
				 var div = document.getElementById("aboutyou");  
			     if (div.style.display !== "none")
			     {  
			         div.style.display = "none";  
			     }  
			     else 
			     {  
			         div.style.display = "block";  
			     }  
			 }
		 if (id=='supplements')
		 {
			 
			 var div = document.getElementById("supplements");  
		     if (div.style.display !== "none")
		     {  
		         div.style.display = "none";  
		     }  
		     else 
		     {  
		         div.style.display = "block";  
		     }  
		 }
		 if (id=='firminfo')
		 {
			 
			 var div = document.getElementById("firminfo");  
		     if (div.style.display !== "none")
		     {  
		         div.style.display = "none";  
		     }  
		     else 
		     {  
		         div.style.display = "block";  
		     }  
		 }
		}
	 
	       	
}
 function loadAboutFirm()
 {
	 
	 try
	 {
		 var isCannibSuppPage=document.getElementById("ajax_field_isCannibSuppPage");  
		 //alert(isCannibSuppPage.checked);
		 if(isCannibSuppPage.checked==true)
			 {
			 document.getElementById("CannibSupp1").style.display = "table-row";
			 document.getElementById("CannibSupp2").style.display = "table-row";
			 document.getElementById("CannibSupp3").style.display = "table-row";
			 document.getElementById("CannibSupp4").style.display = "table-row";
			 document.getElementById("CannibSupp5").style.display = "table-row";
			 document.getElementById("CannibSupp6").style.display = "table-row";
			 document.getElementById("CannibSupp7").style.display = "table-row";
			 document.getElementById("CannibSupp8").style.display = "table-row";
			 document.getElementById("CannibSupp9").style.display = "table-row";
			 document.getElementById("CannibSupp10").style.display = "table-row";
			 }
		 else
			 {
			 document.getElementById("CannibSupp1").style.display = "none";
			 document.getElementById("CannibSupp2").style.display = "none";
			 document.getElementById("CannibSupp3").style.display = "none";
			 document.getElementById("CannibSupp4").style.display = "none";
			 document.getElementById("CannibSupp5").style.display = "none";
			 document.getElementById("CannibSupp6").style.display = "none";
			 document.getElementById("CannibSupp7").style.display = "none";
			 document.getElementById("CannibSupp8").style.display = "none";
			 document.getElementById("CannibSupp9").style.display = "none";
			 document.getElementById("CannibSupp10").style.display = "none";
			 }
		 
		 
		 showHideCannibsOtherDesc();
	 }
	 catch(e1)
	 {}	
	 var numberMask = new Mask("###", "number");
	 var YearOfFirmEstablished = document.getElementById("ajax_field_YearOfFirmEstablished");
	 numberMask.attach(YearOfFirmEstablished);
	 var TotalNumOfNonAttorneyStaff = document.getElementById("ajax_field_TotalNumOfNonAttorneyStaff");
	 numberMask.attach(TotalNumOfNonAttorneyStaff);
	 
	 var r=getGenericRadioValue("IsApplicantProvidesLegalServices");
	 var s=getGenericRadioValue("IsFirmMergedWithOtherFirm");
	 var p=getGenericRadioValue("IsFirmCoverageForPreceedorFirms");
	 var q=getGenericRadioValue("IsApplIntToFinanAssests");
	 
	/* var q=getGenericRadioValue("IsFirmMergedWithOtherFirm");*/
	 	if(r=='Y')
			document.getElementById("applicantProvidesLegalServicesDescDiv").style.display = "table-row";
		else
			document.getElementById("applicantProvidesLegalServicesDescDiv").style.display = "none";
	 	
	 
	 if(s=='Y')
		 {
			document.getElementById("divQ6").style.display = "table-row-group";
			if(p=='Y' || q=='Y')
				document.getElementById("aboutDiv").style.display = "block";
			else
				document.getElementById("aboutDiv").style.display = "none";
		 }	
	else
		{
		document.getElementById("divQ6").style.display = "none";
		document.getElementById("aboutDiv").style.display = "none";
		}
	 
	 	
	 		
 }
 function loadCyber()
 {
	 
	 var numberMask = new Mask("###", "number");
	 var disbursementTransaction = document.getElementById("ajax_field_disbursementTransaction");
	 numberMask.attach(disbursementTransaction);
	 var peopleToDisburseFunds = document.getElementById("ajax_field_peopleToDisburseFunds");
	 numberMask.attach(peopleToDisburseFunds);
	 
	// loadAppointmentFeeInformation("ajax_field_disbursementTransaction");
 }
 /*function loadClaimSupplement()
 {
		
		loadAppointmentFeeInformation('ajax_field_InsurerLoss');
		loadAppointmentFeeInformation('ajax_field_ClaimantLastDemand');
		loadAppointmentFeeInformation('ajax_field_TotalAmountPaid');
	 var numberMask = new Mask("###", "number");
	 var InsurerLoss = document.getElementById("ajax_field_InsurerLoss");
	 numberMask.attach(InsurerLoss);
	 var ClaimantLastDemand = document.getElementById("ajax_field_ClaimantLastDemand");
	 numberMask.attach(ClaimantLastDemand);
	 var TotalAmountPaid = document.getElementById("ajax_field_TotalAmountPaid");
	 numberMask.attach(TotalAmountPaid);
	 
	 var insurerLoss=document.getElementById("ajax_field_InsurerLoss");
		if (insurerLoss.value == '0')
			insurerLoss.value = '';
		var claimantLastDemand=document.getElementById("ajax_field_ClaimantLastDemand");
		if (claimantLastDemand.value == '0')
			claimantLastDemand.value = '';
		var totalAmountPaid=document.getElementById("ajax_field_TotalAmountPaid");
		if (totalAmountPaid.value == '0')
			totalAmountPaid.value = '';

 }
 */
 function validateAnualHours(field)
 {
	// alert('hello');
	 try{
	var annualHours=document.getElementById(field.id).value;
	//alert(annualHours);
	var n=field.id;
	var index = n.substring(n.lastIndexOf("_") + 1, n.length);
	
	if(annualHours.length>0 && isNumberCorrect(annualHours))
	{	
		if(annualHours<50)
		{
			showPopUpOverloaded('hours','500','400');
		}
		
		if(annualHours > 1000)
			document.getElementById("ajax_field_IsNonRatedAttorney1_FixedValue_"+index).disabled = true;
		else
			document.getElementById("ajax_field_IsNonRatedAttorney1_FixedValue_"+index).disabled = false;
	}
	 }catch (e) {
		// TODO: handle exception
	}
}
 
 function loadAttorneyPriorActDate()
 {
	 try{
	 var formObject = document.forms[0];
		for ( var i = 0; i < formObject.length; i++) {
	
			var n = formObject.elements[i].name;
			if (n != null) {
	
			if (n.indexOf("AttorneyPriorActDate_") > -1) 
				{
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_AttorneyPriorActDate_" + index;
					var fld1 = "ajax_field_PriorActDateAttorneysNew_" + index;
					var attorneyPriorActDate=document.getElementById(fld).value;
					document.getElementById(fld1).value=attorneyPriorActDate;
				}
			}
			
		}
	 }catch (e) {
		// TODO: handle exception
	}
 }
 

 function loadLicensedStates() {
	try
	{
	var x = '';
	var count = 0;
	var formObject = document.forms[0];
	for ( var i = 0; i < formObject.length; i++) {

		var n = formObject.elements[i].name;
		if (n != null) {

			if (n.indexOf("LicensedStates_") > -1) {

				var index = n.substring(n.lastIndexOf("_") + 1, n.length);
				var fld = "ajax_field_LicensedStates_" + index;
				var field = document.getElementById(fld);
				count++;
				if (count == 1) {
					x = x + showSelected(field);
				} else
					x = x + '#' + showSelected(field);
			}
		}
	}
	
	document.getElementById('ajax_field_newLicensedStates').value = x;
	}
	catch(e)
	{}
}
 
 function showSelected(field)
 {
var x='';
 var count=0;
 for (var i = 0; i < field.options.length; i++) {
   if (field.options[i].selected) {
	   {
		   count++;
		   if(count==1)
	   x=x+field.options[i].value
	   else
		   x=x+','+field.options[i].value
	   }
   }
 }
return x;
 }
 function loadPracticeManagement()
 {
	 var p=getGenericRadioValue("IsApplInitiatedLawsuitForFirm");
	 
	 		if(p=='Y')
				document.getElementById("suppDiv").style.display = "block";
			else
				document.getElementById("suppDiv").style.display = "none";
	 
 }
 function loadUnderwritingInfo()
 {
	 var p=getGenericRadioValue("custodyOrControlFunds");
	 var q=getGenericRadioValue("isPolicyExcludeCoverage");
	 
		if(p=='Y')
			document.getElementById("suppDiv").style.display = "table-row-group";
		else
			document.getElementById("suppDiv").style.display = "none"; 
		if(q=='Y')
			document.getElementById("rowsm").style.display = "table-row";
		else
			document.getElementById("rowsm").style.display = "none"; 
	 var premium1=document.getElementById("ajax_field_premiumFirstAttoryneyInsurance");
	  var premium2=document.getElementById("ajax_field_premiumSecondAttorneyInsurance");
	  var premium3=document.getElementById("ajax_field_premiumThirdAttorneyInsurance");
	  
	  if(premium1!=null)
		{
		  if (premium1.value == '0')
			premium1= '';
		}

	  if(premium2!=null)
		{
		  if (premium2.value == '0')
			  premium2= '';
		}

	  if(premium3!=null)
		{
		  if (premium3.value == '0')
			  premium3= '';
		}
	 loadAppointmentFeeInformation('ajax_field_premiumFirstAttoryneyInsurance');
	  loadAppointmentFeeInformation('ajax_field_premiumSecondAttorneyInsurance');
	  loadAppointmentFeeInformation('ajax_field_premiumThirdAttorneyInsurance');
	  
	 
		
	  
	
		
 }
 function loadMorePracticeManagement()
 {
	 var p=getGenericRadioValue("IsFirmHaveClientMoreThan25PercentOfBilling");
	 var q=getGenericRadioValue("IsFirmHaveClientInEntertainmentInd");
	 var r=getGenericRadioValue("IsFirmProvideInvestmentAdvice");
	 var s=getGenericRadioValue("IsFirmProvidedLegalService");
	 
		if(p=='Y')
			document.getElementById("trID1").style.display = "table-row";
		else
			document.getElementById("trID1").style.display = "none"; 
		
		if(q=='Y')
			document.getElementById("trID2").style.display = "table-row-group";
		else
			document.getElementById("trID2").style.display = "none"; 
		
		if(r=='Y')
			document.getElementById("firmInvestmentAdviceDescDiv").style.display = "table-row-group";
		else
			document.getElementById("firmInvestmentAdviceDescDiv").style.display = "none"; 
		
		if(s=='Y')
			document.getElementById("isFirmProvidedLegalServiceDescDiv").style.display = "table-row-group";
		else
			document.getElementById("isFirmProvidedLegalServiceDescDiv").style.display = "none"; 
	 
 }
 function  familyLawSupplement()
 {
	  
	 var numberMask = new Mask("###", "number");
	 var formObject = document.forms[0];
	 var total=0;
		for ( var i = 0; i < formObject.length; i++) {
	
			var n = formObject.elements[i].name;
			if (n != null) {
	
				if (n.indexOf("FLAOP_Percentage_") > -1) {
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_FLAOP_Percentage_"+ index;
					var amount=document.getElementById(fld);
					var percentage=document.getElementById(fld).value;
					numberMask.attach(amount);
					if (amount.value == '0')
						amount.value = '';
					
					if (percentage != null && percentage != '') {
						if (!isNumberCorrect(percentage)) {
							alert("That number is invalid.  Please enter again...");
							// document.forms[0].elements[i].select();
							// document.forms[0].elements[i].focus();
							return false;
						} else {
							var percentage = parseInt(percentage);
							if (n != "NumberOfPersonnel_1") {
								total=total+parseInt(percentage);
							}
							
						}
					
						
							
					}
					
					}
			}
			
			
			
		}
		document.getElementById("ajax_field_totalFLAOP").innerHTML = total;
		if (total == 100) {
			document.getElementById("ajax_field_totalFLAOP").style.color = "blue";
		} else {
			document.getElementById("ajax_field_totalFLAOP").style.color = "red";
		}
		
		 var p=document.getElementById("ajax_field_FLAOP_Percentage_7").value;
		if(p != null && p != '')
				document.getElementById("otherDescDiv").style.display = "table-row";
			else
				document.getElementById("otherDescDiv").style.display = "none"; 
 }
 
 function maskPlaintiff()
 {
 	 var p=getGenericRadioValue("IsAppAcceptRefersFromOtherFirms");
 	 var q=getGenericRadioValue("IsAppReferToOtherLawFirms");
 	 
 		if(p=='Y')
 			document.getElementById("trDiv1").style.display = "table-row";
 		else
 			document.getElementById("trDiv1").style.display = "none"; 
 		
 		if(q=='Y')
 			document.getElementById("trDiv2").style.display = "table-row-group";
 		else
 			document.getElementById("trDiv2").style.display = "none"; 
 		
 	 var numberMask = new Mask("###", "number");
 	 var numberOfSuppotedStaffToPlantiff = document.getElementById("ajax_field_NumberOfSuppotedStaffToPlantiff");
 	 numberMask.attach(numberOfSuppotedStaffToPlantiff);
 	 var numberOfInjuryCasesIn12Month = document.getElementById("ajax_field_NumberOfInjuryCasesIn12Month");
 	 numberMask.attach(numberOfInjuryCasesIn12Month);
 	 var perOfCasesSettledBeforeTrail = document.getElementById("ajax_field_PerOfCasesSettledBeforeTrail");
 	 numberMask.attach(perOfCasesSettledBeforeTrail);
 	 
 	
 	 
 }
 function loadLitigation(fieldPrefix)
 {
	 //alert(fieldPrefix);
	 var numberMask = new Mask("###", "number");
	 var formObject = document.forms[0];
	 var total=0;
		for ( var i = 0; i < formObject.length; i++) {
	
			var n = formObject.elements[i].name;
			if (n != null) {
	
				if (n.indexOf(fieldPrefix) > -1) {
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_"+fieldPrefix+ index;
					var amount=document.getElementById(fld);
					if(fieldPrefix=='AOL_LargestCaseSize_')
							new Mask("$#,###", "number").attach(amount);
					else
						numberMask.attach(amount);
					
			}
		}

		}
		
		 
		
 }
 function calculateTotalPercentage()
 {
	var totalPercentageOfRevenue=0; 
	var totalPercentageOfDefense=0; 
	var totalLargestCaseSize=0; 
	 var formObject = document.forms[0];
	 	for ( var i = 0; i < formObject.length; i++) 
	 	{
	
			var n = formObject.elements[i].name;
			if (n != null) 
			{
				
	
				if (n.indexOf("AOL_PercentageOfRevenue_") > -1) 
				{
					
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_AOL_PercentageOfRevenue_"+ index;
					myVal = document.getElementById(fld).value;
					var amount= document.getElementById(fld);
					if (amount.value == '0')
						amount.value = '';
	    			if (myVal != null && myVal != '') 
	    			{
	    				if (!isNumberCorrect(myVal)) {
	    					alert("That number is invalid.  Please enter again...");
	    					return false;
	    				} else {
	    					var ival = parseInt(myVal);
	    					totalPercentageOfRevenue = totalPercentageOfRevenue + ival;
	    					
	    				}
	    			}
					
				}
				
				if (n.indexOf("AOL_PercentageOfDefense_") > -1) 
				{
					
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_AOL_PercentageOfDefense_"+ index;
					myVal1 = document.getElementById(fld).value;
					var amount= document.getElementById(fld);
					if (amount.value == '0')
						amount.value = '';
	    			if (myVal1 != null && myVal1 != '') 
	    			{
	    				if (!isNumberCorrect(myVal1)) {
	    					alert("That number is invalid.  Please enter again...");
	    					return false;
	    				} else {
	    					var ival1 = parseInt(myVal1);
	    					totalPercentageOfDefense = totalPercentageOfDefense + ival1;
	    					
	    				}
	    			}
					
				}
				
				if (n.indexOf("AOL_LargestCaseSize_") > -1) 
				{
					
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_AOL_LargestCaseSize_"+ index;
					myVal2 = document.getElementById(fld).value;
					var amount= document.getElementById(fld);
					if (amount.value == '0')
						amount.value = '';
					loadAppointmentFeeInformation(fld);
	    			
					
	    			
				}
			}

		}
		
	 	//alert(document.getElementById("ajax_field_isBeforeLitigationImplementationDate").value);
	 	if(document.getElementById("ajax_field_isBeforeLitigationImplementationDate").value=='Y')
	 		{
		if(totalPercentageOfRevenue==0)
 		{
			document.getElementById("trDiv3").style.display = "none"; 
			document.getElementById("trDiv2").style.display = "none";
			document.getElementById("trDiv4").style.display = "none";
			document.getElementById("trDiv5").style.display = "none";
 		}
 		
	else
		{
		document.getElementById("trDiv3").style.display = "table-row-group";
 		document.getElementById("trDiv2").style.display = "table-row-group";
 		document.getElementById("trDiv4").style.display = "table-row-group";
 		document.getElementById("trDiv5").style.display = "table-row";
 		maskPlaintiff();
		}
		var totalPercentagePlaintiff=totalPercentageOfRevenue+totalPercentageOfDefense;
		//maskPlaintiff();
	 	document.getElementById("ajax_field_totalAOLPercentageOfRevenue").innerHTML = totalPercentagePlaintiff;
	 	if (totalPercentagePlaintiff == 100) 
    			document.getElementById("ajax_field_totalAOLPercentageOfRevenue").style.color = "blue"; 
    		 else 
    			document.getElementById("ajax_field_totalAOLPercentageOfRevenue").style.color = "red";
	 		}
	 	/*document.getElementById("ajax_field_totalAOLPercentageOfDefense").innerHTML = totalPercentageOfDefense;
	 	if (totalPercentageOfRevenue == 100) 
    			document.getElementById("ajax_field_totalAOLPercentageOfDefense").style.color = "blue"; 
    		 else 
    			document.getElementById("ajax_field_totalAOLPercentageOfDefense").style.color = "red";
	 	
	 	document.getElementById("ajax_field_totalAOLLargestCaseSize").innerHTML = totalLargestCaseSize;
	 */
	 	
  
 }
	 	


function loadRealEstateSupplement()
{	var prevIndex=333;
	var totalPercentageValue=0,totalCommercialPercentageValue=0;
	 var numberMask = new Mask("###", "number");
	 var formObject = document.forms[0];
		for ( var i = 0; i < formObject.length; i++) {
	
			var n = formObject.elements[i].name;
			if (n != null) {
			if (n.indexOf("PercentageValue_") > -1 && n.indexOf("CommercialPercentageValue_") == -1) 
				{
				
				//alert(prevIndex+'  '+index);
					
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					
					var fld = "ajax_field_PercentageValue_" + index;
					var amount=document.getElementById(fld);
					numberMask.attach(amount);
					if (amount.value == '0')
						amount.value = '';
					if(prevIndex!=index)
					{
						
					myVal1 = document.getElementById(fld).value;
					
					if (myVal1 != null && myVal1 != '') 
	    			{
						//alert(fld+'  '+myVal1);
						
	    				if (!isNumberCorrect(myVal1)) {
	    					alert("That number is invalid.  Please enter again...");
	    					return false;
	    				} else {
	    					var ival1 = parseInt(myVal1);
	    					totalPercentageValue = totalPercentageValue + ival1;
	    					
	    				}
	    				//alert("totalPercentageValue....."+totalPercentageValue);
	    				
	    			}
					if(index==11)
    					{
						if (myVal1 != null && myVal1 != '') 
		    			
							document.getElementById("ResiOtherDescDiv").style.display = "block";
		    			}
						else
							document.getElementById("ResiOtherDescDiv").style.display = "none";
							
						}
					
					prevIndex=index;
				}
				
			
			
			
			if (n.indexOf("CommercialPercentageValue_") > -1) 
			{
				var index = n.substring(n.lastIndexOf("_") + 1, n.length);
				var fld = "ajax_field_CommercialPercentageValue_" + index;
				var amount1=document.getElementById(fld);
				numberMask.attach(amount1);
				if (amount1.value == '0')
					amount1.value = '';
				myVal2 = document.getElementById(fld).value;
				if (myVal2 != null && myVal2 != '') 
    			{
    				if (!isNumberCorrect(myVal2)) {
    					alert("That number is invalid.  Please enter again...");
    					return false;
    				} else {
    					var ival2 = parseInt(myVal2);
    					totalCommercialPercentageValue = totalCommercialPercentageValue + ival2;
    					
    				}
    			}
				if(index==11)
				{
					if (myVal2 != null && myVal2 != '') 
	    				document.getElementById("CommOtherDescDiv").style.display = "block";
	    			else
						document.getElementById("CommOtherDescDiv").style.display = "none";
				}
			
				
			}
			
			}
			
		}
		//alert(totalPercentageValue);
//		debugger;
		if(document.getElementById("ajax_field_TotalAOP_Percentage")!=null)
		{
			document.getElementById("ajax_field_TotalAOP_Percentage").innerHTML = totalPercentageValue;
		}
		if(document.getElementById("ajax_field_TotalAOP_Percentage_Commercial")!=null)
		{
			document.getElementById("ajax_field_TotalAOP_Percentage_Commercial").innerHTML = totalCommercialPercentageValue;
		}
		
		if(document.getElementById("ajax_field_TotalAOP_Percentage")!=null)
		{
			if (totalPercentageValue == 100) 
				document.getElementById("ajax_field_TotalAOP_Percentage").style.color = "blue"; 
			 else 
				document.getElementById("ajax_field_TotalAOP_Percentage").style.color = "red";
		}
		if(document.getElementById("ajax_field_TotalAOP_Percentage_Commercial")!=null)
		{
			if (totalCommercialPercentageValue == 100) 
				document.getElementById("ajax_field_TotalAOP_Percentage_Commercial").style.color = "blue"; 
			 else 
				document.getElementById("ajax_field_TotalAOP_Percentage_Commercial").style.color = "red";
		}

		/*loadAppointmentFeeInformation('ajax_field_transactionsHandled12MonthsResi');
		loadAppointmentFeeInformation('ajax_field_transactionsHandled12MonthsComm');
		*/
		loadAppointmentFeeInformation('ajax_field_largestTransaction12MonthsResi');
		loadAppointmentFeeInformation('ajax_field_largestTransaction12MonthsComm');

}

function loadRenewAboutFirm()
{	
	showHideCannibSupp();
	
	 var s=getGenericRadioValue("IsFirmMergedWithOtherFirm");
	 var p=getGenericRadioValue("IsFirmCoverageForPreceedorFirms");
	 var q=getGenericRadioValue("IsApplIntToFinanAssests");
	 
	 if(s=='Y')
		 document.getElementById("divQ6").style.display = "table-row-group";
			
		else
			document.getElementById("divQ6").style.display = "none";
				
		
	 if(s=='Y')
	 	{
		 	if(p=='Y' || q=='Y')
		 		document.getElementById("aboutDiv").style.display = "block";
		 	else
		 		document.getElementById("aboutDiv").style.display = "none";
		}
	 else
		 document.getElementById("aboutDiv").style.display = "none";
	 
	 
	 var p=getGenericRadioValue("custodyOrControlFunds");
	 
		if(p=='Y')
			document.getElementById("suppDiv").style.display = "table-row-group";
		else
			document.getElementById("suppDiv").style.display = "none"; 
		
		var f=getGenericRadioValue("IsFirmHaveClientMoreThan25PercentOfBilling");
		 
			if(f=='Y')
				document.getElementById("trID1").style.display = "table-row";
			else
				document.getElementById("trID1").style.display = "none"; 
			
			
			var p=getGenericRadioValue("IsApplInitiatedLawsuitForFirm");
			 
	 		if(p=='Y')
				document.getElementById("suppDiv2").style.display = "block";
			else
				document.getElementById("suppDiv2").style.display = "none";
}
function showOtherDesc()

{
//alert(otherPerc)
//	debugger;
	try{
	var formObject = document.forms[0];
	var listSize=document.getElementById("ajax_field_totalAopListSize").value-1;
	var listSize1=document.getElementById("ajax_field_PriorAopListSize").value-1;
	var litPerPlain=0,litPerDiff=0,otherPerc=0,litPerPlainPrior=0,litPerDiffPrior=0,otherPercPrior=0;
	for ( var i = 0; i < formObject.length; i++) 
 	{
		var n = formObject.elements[i].name;
		if (n != null) 
		{	if (n.indexOf("AOP_Percentage_") > -1) 
			{	
				var index = n.substring(n.lastIndexOf("_") + 1, n.length);
				var fld = "AOP_Percentage_"+ index;
				var values=document.getElementById(fld).value;
				
						if(values != null)
						{
							var desc=document.getElementById("AOPDesc_"+index).value;
							//alert("desc show other  "+desc);
							if(desc=="Other")
							{
								//alert("otherPerc")
								var myVal= parseInt(values);
								otherPerc=myVal;
							
							}
							if(desc=="Litigation - Other - Plaintiff")
							{
								var myVal= parseInt(values);
								litPerPlain=myVal;
							}
							if(desc=="Litigation - Other - Defense")
							{
								var myVal= parseInt(values);
								if(myVal>0)
									litPerDiff=myVal;
							}
							
					}
				}
		if (n.indexOf("PriorAopPercentage_") > -1) 
		{	
			var index = n.substring(n.lastIndexOf("_") + 1, n.length);
			var fld = "PriorAopPercentage_"+ index;
			var values=document.getElementById(fld).value;
					if(values != null)
					{
						var desc=document.getElementById("DescAOP_"+index).value;
						
						if(desc=="Other")
						{
							var myVal= parseInt(values);
							otherPercPrior=myVal;
						}
						if(desc=="Litigation - Other - Plaintiff")
						{
							var myVal= parseInt(values);
							
							litPerPlainPrior=myVal;
							
						}
						if(desc=="Litigation - Other - Defense")
						{
							var myVal= parseInt(values);
							litPerDiffPrior=myVal;
						}
					}
			}
			
			}
		}
	}catch (e) {
		// TODO: handle exception
	}
	try{
	if(litPerPlain>0)
		document.getElementById("litigationPlaintiffDescId").style.display = "table-row";
	else
			document.getElementById("litigationPlaintiffDescId").style.display = "none"; 
	
	if(litPerDiff>0)
	{
	document.getElementById("litigationDefensefDescId").style.display = "table-row";
	litPer=myVal;
	}
	else
		document.getElementById("litigationDefensefDescId").style.display = "none"; 

	
	if(otherPerc>0)
		document.getElementById("otherDescId").style.display = "table-row";
	else
			document.getElementById("otherDescId").style.display = "none"; 
	
	if(otherPercPrior>0)
		document.getElementById("otherPriorDescId").style.display = "table-row";
	else
			document.getElementById("otherPriorDescId").style.display = "none"; 
	if(litPerPlainPrior>0)
		document.getElementById("litigationPlaintiffPriorDescId").style.display = "table-row";
	else
			document.getElementById("litigationPlaintiffPriorDescId").style.display = "none";
	if(litPerDiffPrior>0)
		document.getElementById("litigationDefensefPriorDescId").style.display = "table-row";
	else
			document.getElementById("litigationDefensefPriorDescId").style.display = "none"; 

	}catch (e) {
		// \: handle exception
	}
	
}

function loadQuoteOption()
{
	
	var modifier1= document.getElementById("SchduleRatingModifier1");
	var modifier2= document.getElementById("SchduleRatingModifier2");
	var modifier3= document.getElementById("SchduleRatingModifier3");
	if (modifier1.value == '0')
		modifier1.value = '';
	if (modifier2.value == '0')
		modifier2.value = '';
	if (modifier3.value == '0')
		modifier3.value = '';
	if(modifier1.value == '' && modifier2.value == '' && modifier3.value == '')
		document.getElementById("ModifierComment").innerHTML="";
}
function showPlaintiffOtherDesc()
{
	
	
	var plaintiffPercentage=0,defensePercentage=0,casePercentage=0;
	if(document.getElementById("ajax_field_isBeforeLitigationImplementationDate").value=='Y')
		{
		if(document.getElementById("ajax_field_AOL_PercentageOfRevenue_13")!=null)
			plaintiffPercentage=document.getElementById("ajax_field_AOL_PercentageOfRevenue_13").value;
		
		if(document.getElementById("ajax_field_AOL_PercentageOfDefense_13")!=null)
			defensePercentage=document.getElementById("ajax_field_AOL_PercentageOfDefense_13").value;
		
		if (plaintiffPercentage != null && plaintiffPercentage != '' && plaintiffPercentage>0) 
				document.getElementById("plaintiffOtherDescDiv").style.display = "block";
			else
				document.getElementById("plaintiffOtherDescDiv").style.display = "none";
		
		if (defensePercentage != null && defensePercentage != '' && defensePercentage>0) 
				document.getElementById("defenseOtherDescDiv").style.display = "block";
			else
				document.getElementById("defenseOtherDescDiv").style.display = "none";
		}
	/*if(document.getElementById("ajax_field_isBeforeLitigationImplementationDate").value=='N')
	{
		if(document.getElementById("ajax_field_AOL_LargestCaseSize_13")!=null )
			casePercentage = document.getElementById("ajax_field_AOL_LargestCaseSize_13").value;
		
		//casePercentage=casePercentage.replace("$","").raplace(",","");
		
		if ( casePercentage != null && casePercentage != '' && casePercentage!='0') 
			{
			
			document.getElementById("caseOtherDescDiv").style.display = "block";
			}
		else
			{
			
			document.getElementById("caseOtherDescDiv").style.display = "none";
			}
	}*/
}
function setAttorneysDateValues()
{
	try{
	var formObject = document.forms[0];
	for ( var i = 0; i < formObject.length; i++) {

		var n = formObject.elements[i].name;
		if (n != null) {
			if (n.indexOf("AttorneyPriorActDate_") > -1) 
			{
				var index = n.substring(n.lastIndexOf("_") + 1, n.length);
				var fld = "ajax_field_AttorneyPriorActDate_" + index;
				values=document.getElementById(fld).value;
				
				priorActDate=document.getElementById("ajax_field_PriorActDateAttorneysNew_"+index).value;
				if(priorActDate==null)
				document.getElementById("ajax_field_PriorActDateAttorneysNew_"+index).value=values;
			}
			/*if (n.indexOf("IsNonRatedAttorney_") > -1) 
			{
				var index = n.substring(n.lastIndexOf("_") + 1, n.length);
				var fld = "ajax_field_IsNonRatedAttorney_" + index;
				isNonRated=document.getElementById(fld).value;
				
				if(isNonRated==null)
				document.getElementById(fld).checked = true
			}*/
		
		}
	}
	
	}catch(e){
		
	}
	
	
	
	/*
	 * 
	 * */
}

function setAttorneyLicensedStates() {
	var x = '';
	var count = 0;
	var formObject = document.forms[0];
	for ( var i = 0; i < formObject.length; i++) {

		var n = formObject.elements[i].name;
		if (n != null) {

			if (n.indexOf("StateDesc_") > -1) {

				var index = n.substring(n.lastIndexOf("_") + 1, n.length);
				var fld = "StateDesc_" + index;
				var field = document.getElementById(fld);
				
				
				if(field.checked==true)
				{	count++;
					
				if (count == 1) {
					x = x + field.value;
				} else
					x = x + ',' +field.value;
				}
			}
		}
	}
	
	document.getElementById('ajax_field_newLicensedStates').value = x;
	
}
function loadAttorneyLicensedStates()
{
	var formObject = document.forms[0];
	//alert("hello");
	var n = formObject.elements[i].name;
	if (n != null) {
		if (n.indexOf("StateDesc_") > -1) 
		{	//alert('hello');
			var index = n.substring(n.lastIndexOf("_") + 1, n.length);
			var fld = "StateDesc_" + index;
			values=document.getElementById("ajax_field_LicensedStates_"+index).value;
			//alert(values);
			setSelectedIndex(document.getElementById(fld),values);
		}
		
		
	
	}
	
	
}

function loadClaimSupplement()
{
	 var numberMask = new Mask("###", "number");
	 var InsurerLoss = document.getElementById("ajax_field_InsurerLoss");
	 numberMask.attach(InsurerLoss);
	 var ClaimantLastDemand = document.getElementById("ajax_field_ClaimantLastDemand");
	 numberMask.attach(ClaimantLastDemand);
	 var TotalAmountPaid = document.getElementById("ajax_field_TotalAmountPaid");
	 numberMask.attach(TotalAmountPaid);
	 
	 var insurerLoss=document.getElementById("ajax_field_InsurerLoss");
		if (insurerLoss.value == '0')
			insurerLoss.value = '';
		var claimantLastDemand=document.getElementById("ajax_field_ClaimantLastDemand");
		if (claimantLastDemand.value == '0')
			claimantLastDemand.value = '';
		var totalAmountPaid=document.getElementById("ajax_field_TotalAmountPaid");
		if (totalAmountPaid.value == '0')
			totalAmountPaid.value = '';
		
		loadAppointmentFeeInformation('ajax_field_InsurerLoss');
		loadAppointmentFeeInformation('ajax_field_ClaimantLastDemand');
		loadAppointmentFeeInformation('ajax_field_TotalAmountPaid');
}

function checkListOn()

{

	 var x='';
	 var formObject = document.forms[0];
		for ( var i = 0; i < formObject.length; i++) {
	
			var n = formObject.elements[i].name;
			if (n != null) {
	
			if (n.indexOf("combinedStates_") > -1) 
				{
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_combinedStates_" + index;
					var fld1="ajax_field_LicensedStates_" + index;
					var value=document.getElementById(fld).value;
					
					var  StateList=document.getElementById(fld).value;
					if(StateList != null && StateList != '')
						{
							var StateListArray=StateList.split(",");
							$('#'+fld1).val(StateListArray);
						}
				}
			}
			
		}

}

function disableERPExpirationDate(field,value)
{
	
	
	var n=field.slice(-1);
	var id="ajax_field_ERPExpDate_"+n
	if(value=='N')
		{
		document.getElementById(id).disabled = true;
		document.getElementById(id).value = "";
		}
	if(value=='Y')
		{
		document.getElementById(id).disabled = false;
		}
	

	
}

function setEndorsementType()
{
	var cancellationReason=document.getElementById("ajax_field_cancellationReason").value;
	if(cancellationReason==1 || cancellationReason==2)
		{
		document.getElementById("ajax_field_cancellationType").value = 2;
//		  document.getElementById("ajax_field_cancellationType").disabled = true;
		  
		readonlyDropDown($("#ajax_field_cancellationType"));
			 
		
		} 
	else
		{
		//document.getElementById("ajax_field_cancellationType").value = 0;
//			document.getElementById("ajax_field_cancellationType").disabled = false;
		enableDropDown($("#ajax_field_cancellationType"));
		}
}

function readonlyDropDown(obj){
		try{
			obj.prop("readonly",true);
			obj.attr("readonly","readonly");
			obj.attr("onclick","return false;");
			obj.attr("tabindex",-1);
			obj.css( 'pointer-events', 'none' ); 
		} catch (e) {
			// TODO: handle exception
		}
}

function enableDropDown(obj){
	try{
		obj.prop("readonly",false);
		obj.removeAttr("readonly");
		obj.removeAttr("onclick");
		obj.removeAttr("tabindex");
		obj.css( 'pointer-events', '' ); 
	} catch (e) {
		// TODO: handle exception
	}
}

function showHideDeductibleEndorsementContent(id)
{
	
	var value=document.getElementById(id).value;
	var isEnforcedPolicy=document.getElementById('isEnforcedPolicy').value;
	var anyDecendentPolicy=document.getElementById('anyDecendentPolicy').value;
	
	//alert(value);
	if (value=='DC') 
	{
		if(isEnforcedPolicy=='N')
			{
			
			document.getElementById('premiumCalculationDC').style.display="none";
			document.getElementById('ajax_field_deductibleEndorsementPremiumNew').value="0.0";
			document.getElementById('ajax_field_deductibleEndorsementPremium').value="0.0";
			}
		else
			{
			if(anyDecendentPolicy='Y' && isShowPemiumCalculation=='N')
				{
				document.getElementById('premiumCalculationDC').style.display="none";
				document.getElementById('ajax_field_deductibleEndorsementPremiumNew').value="0.0";
				document.getElementById('ajax_field_deductibleEndorsementPremium').value="0.0";
				}
			else
				{
				document.getElementById('premiumCalculationDC').style.display="block";
				}
			
			}
		
		
		document.getElementById("changeDeductibles").style.display = "block";
		document.getElementById("changeLimits").style.display = "none";

	}
	else 
	{
		document.getElementById("changeDeductibles").style.display = "none";
		document.getElementById("changeLimits").style.display = "block";
//		document.getElementById("changeDeductibles").style.display = "none";
//		document.getElementById("deductibleChange").style.display = "none";
//		document.getElementById("limitChange").style.display = "block";
		
	}
	
/*	document.getElementById("ajax_field_currentLimit").disabled = true;
	document.getElementById("ajax_field_currentClaimExpensesType").disabled = true;
	document.getElementById("ajax_field_currentClaimExpensesTypeOutside").disabled = true;
	document.getElementById("ajax_field_currentDollarDefense").disabled = true;*/
	
	
}

function setFieldsReadonly() {
   /* document.getElementById("ajax_field_currentLimit").readOnly = true;
    document.getElementById("ajax_field_currentClaimExpensesType").disabled = true;
    document.getElementById("ajax_field_currentClaimExpensesTypeOutside").disabled = true;
    document.getElementById("ajax_field_currentDollarDefense").disabled = true;*/
}

function fillIndicationSequence(id, name)
{
	var formObject = document.forms[0];
 	var IsQuoteSelected= "";
 	//alert(id + '  ' + name);
	  for (var i=0; i<formObject.length; i++)
	  {	
	    var n = formObject.elements[i].name;
	    if (n.indexOf("IsThisOptionFinalised_") > -1)            
	    {
	    	if (formObject.elements[i].checked && n != name)
	    	{
	    		//alert(n);
	    		document.getElementById(n).checked = false;
	    	}
	   	}
	  }
	  
	  var index = name.substring(name.indexOf("_") + 1, name.length);
  	  document.getElementById(id).value = index;
}
function loadIndicationPage()
{
	//loadCompany();
	numberMask = new Mask("###", "number");
	var 	NumberOfLawyers=document.getElementById("NumberOfLawyers");
	var 	NumberOfLawyers500=document.getElementById("NumberOfLawyers500");
	var 	NumberOfLawyers1000=document.getElementById("NumberOfLawyers1000");
	var 	ClaimAge_1=document.getElementById("ClaimAge_1");
	var 	ClaimAge_2=document.getElementById("ClaimAge_2");
	var 	ClaimAge_3=document.getElementById("ClaimAge_3");
	var 	ClaimAge_4=document.getElementById("ClaimAge_4");
	var 	ClaimAge_5=document.getElementById("ClaimAge_5");
	var 	ClaimAge_6=document.getElementById("ClaimAge_6");
	var 	ClaimAge_7=document.getElementById("ClaimAge_7");
	var 	ClaimAge_8=document.getElementById("ClaimAge_8");
	var 	ClaimAge_9=document.getElementById("ClaimAge_9");
	var 	ClaimAge_10=document.getElementById("ClaimAge_10");
	var 	ClaimAge_11=document.getElementById("ClaimAge_11");
	var 	ClaimAge_12=document.getElementById("ClaimAge_12");
	
	numberMask.attach(ClaimAge_1);
	numberMask.attach(ClaimAge_2);
	numberMask.attach(ClaimAge_3);
	numberMask.attach(ClaimAge_4);
	numberMask.attach(ClaimAge_5);
	numberMask.attach(ClaimAge_6);
	numberMask.attach(ClaimAge_7);
	numberMask.attach(ClaimAge_8);
	numberMask.attach(ClaimAge_9);
	numberMask.attach(ClaimAge_10);
	numberMask.attach(ClaimAge_11);
	numberMask.attach(ClaimAge_12);
	if (NumberOfLawyers != null) {
		numberMask.attach(NumberOfLawyers);
		if (NumberOfLawyers.value == '0')
			NumberOfLawyers.value = '';
	}
	if (NumberOfLawyers500 != null) {
		numberMask.attach(NumberOfLawyers500);
		if (NumberOfLawyers500.value == '0')
			NumberOfLawyers500.value = '';
	}
	if (NumberOfLawyers1000 != null) {
		numberMask.attach(NumberOfLawyers1000);
		if (NumberOfLawyers1000.value == '0')
			NumberOfLawyers1000.value = '';
	}	
		
}


function setAccountType2(value){
//	alert(sessionStorage);
	sessionStorage.accounttype=value;
	//alert('setting : ' + sessionStorage.accounttype);	
}

function setCSSForAccountType2(){
	//alert('setCSSForAccountType2 : ' + sessionStorage.accounttype);	
	//alert(document.getElementById('accountstyle').getAttribute('href'));
	//alert('getting : ' + sessionStorage.accounttype)
	if(sessionStorage.accounttype=='aop_list'){
	//alert(sessionStorage.accounttype);
		if(document.getElementById('aop_search') != null){
			document.getElementById("aop_search").style.display = "none";
		}
		if(document.getElementById('aop_list') != null){
			document.getElementById("aop_list").style.display = "block";
		}
//		if(document.getElementById('calculateAndSave_btn') != null){
//            document.getElementById("calculateAndSave_btn").focus();
//		}
		$(document).ready(function() {	
			$("#aop_search_btn").removeClass("active");
			$("#aop_list_btn").addClass("active");
		});
	} else if(sessionStorage.accounttype=='aop_search'){
		//alert(sessionStorage.accounttype);
		if(document.getElementById('aop_search') != null){
			document.getElementById("aop_search").style.display = "block";
		}
		if(document.getElementById('aop_list') != null){
			document.getElementById("aop_list").style.display = "none";
		}
		$(document).ready(function() {	
			$("#aop_search_btn").addClass("active");
			$("#aop_list_btn").removeClass("active");
		});
		if(document.getElementById('AccountName') != null){
            document.getElementById("AccountName").focus();
		}
	}
	else{
			$(document).ready(function(){
				//$("#rowSelect").removeClass("selected_color");
			});

			
	}							
}
$(document).ready(function() {
	$("#aop_search_btn").click(function(e) {
		document.getElementById("aop_list").style.display = "none";
		document.getElementById("aop_search").style.display = "block";
	});
	$("#aop_list_btn").click(function(e) {
		document.getElementById("aop_list").style.display = "block";
		document.getElementById("aop_search").style.display = "none";
	});
});
function showIndicationOther(id)
{
var val=document.getElementById(id).value;
if(val>0)
	document.getElementById("otherDescId").style.display = "table-row";
else
document.getElementById("otherDescId").style.display = "none";
	
}
function loadCancellationPage() {

	try
	{
		try
		{
		var cancellationReturnPremium=document.getElementById('ajax_field_cancellationReturnPremium').value;
		var cancellationReturnTax=document.getElementById('ajax_field_cancellationReturnTax').value;
		var cancellationTotalReturnPremium=document.getElementById('ajax_field_cancellationTotalReturnPremium').value;
		if(cancellationReturnPremium!=null && cancellationReturnPremium!="")
		{
		//alert('hi');
			var cancellationType=document.getElementById('ajax_field_cancellationType').value;
			if(cancellationType!=null && cancellationType!="" && cancellationType!=1)	
				document.getElementById('endorsementPdfDiv').style.display = "block";
			else
				document.getElementById('endorsementPdfDiv').style.display = "none";
		 setAmoutFormat('ajax_field_cancellationReturnPremium');
	
		}
		else
		{
		//alert('hi');
		 document.getElementById('endorsementPdfDiv').style.display = "none";
		}
	if(cancellationReturnTax!=null && cancellationReturnTax!="")
	setAmoutFormat('ajax_field_cancellationReturnTax');
	if(cancellationTotalReturnPremium!=null && cancellationTotalReturnPremium!="")
	setAmoutFormat('ajax_field_cancellationTotalReturnPremium');
		}catch(e)
	{}
		try
		{
	var reinstatementReturnPremium=document.getElementById('ajax_field_reinstatementReturnPremium').value;
	var reinstatementReturnTax=document.getElementById('ajax_field_reinstatementReturnTax').value;
	var reinstatementTotalReturnPremium=document.getElementById('ajax_field_reinstatementTotalReturnPremium').value;
	if(reinstatementReturnPremium!=null && reinstatementReturnPremium!="")
	setAmoutFormat('ajax_field_reinstatementReturnPremium');
	if(reinstatementReturnTax!=null && reinstatementReturnTax!="")
	setAmoutFormat('ajax_field_reinstatementReturnTax');
	if(reinstatementTotalReturnPremium!=null && reinstatementTotalReturnPremium!="")
	setAmoutFormat('ajax_field_reinstatementTotalReturnPremium');
		}
		catch(e)
		{}
	var isActivePolicy=document.getElementById('isEnforcedPolicy').value;
	 if(isActivePolicy=='Y')
		 {
			 document.getElementById('premiumDiv1').style.display = "block";
			 document.getElementById('premiumList1').style.display = "block";
			 document.getElementById('premiumList1').style.display = "block";
		 }
	 if(isActivePolicy=='N')
	 {
		 document.getElementById('premiumDiv1').style.display = "none";
		 document.getElementById('premiumList1').style.display = "none";
		 document.getElementById('premiumList1').style.display = "none";
	 }

	}
	catch(err)
	{}
	
}
function loadLimitEndorsement() {
	//alert("hello");
	try
	{
	if(document.getElementById('ajax_field_litmitsEndorsementPremiumNew')!=null)
	setAmoutFormat('ajax_field_litmitsEndorsementPremiumNew');
	if(document.getElementById('ajax_field_litmitsEndorsementTaxNew')!=null)
	setAmoutFormat('ajax_field_litmitsEndorsementTaxNew');
	if(document.getElementById('ajax_field_litmitsEndorsementTotalPremiumNew')!=null)
	setAmoutFormat('ajax_field_litmitsEndorsementTotalPremiumNew');
	if(document.getElementById('ajax_field_deductibleEndorsementPremiumNew')!=null)
	setAmoutFormat('ajax_field_deductibleEndorsementPremiumNew');
	}
	catch(err)
	{}
	try
	{
		var isEnforcedPolicy=document.getElementById('isEnforcedPolicy').value;
		var limitDeuctibleEndorsementType=document.getElementById('ajax_field_limitDeuctibleEndorsementType').value;
		var anyDecendentPolicy=document.getElementById('anyDecendentPolicy').value;
		//alert(isEnforcedPolicy+' '+limitDeuctibleEndorsementType);
		if(isEnforcedPolicy=='N')
			{
			if(limitDeuctibleEndorsementType=='LC')
				{
				document.getElementById('premiumCalculationLt').style.display="none";
				document.getElementById('ajax_field_litmitsEndorsementPremium').value="0.0";
				document.getElementById('ajax_field_litmitsEndorsementTax').value="0.0";
				document.getElementById('ajax_field_litmitsEndorsementTotalPremium').value="0.0";
				document.getElementById('ajax_field_litmitsEndorsementTotalPremiumNew1').value="0.0";
				
			}
			if(limitDeuctibleEndorsementType=='DC')
			{
			document.getElementById('premiumCalculationDC').style.display="none";
			document.getElementById('ajax_field_deductibleEndorsementPremiumNew').value="0.0";
			document.getElementById('ajax_field_deductibleEndorsementPremium').value="0.0";
	
			}
		
			}
		else
			{
			if(limitDeuctibleEndorsementType=='LC')
			{
				if(anyDecendentPolicy=='Y' && isShowPemiumCalculation=='N')
					{
					//alert('hello');
				document.getElementById('premiumCalculationLt').style.display="none";
				document.getElementById('ajax_field_litmitsEndorsementPremium').value="0.0";
				document.getElementById('ajax_field_litmitsEndorsementTax').value="0.0";
				document.getElementById('ajax_field_litmitsEndorsementTotalPremium').value="0.0";
				document.getElementById('ajax_field_litmitsEndorsementTotalPremiumNew1').value="0.0";
					}
				else
					document.getElementById('premiumCalculationLt').style.display="block";
			}
			if(limitDeuctibleEndorsementType=='DC' && isShowPemiumCalculation=='N')
			{
				if(anyDecendentPolicy=='Y' && isShowPemiumCalculation=='N')
				{
				document.getElementById('premiumCalculationDC').style.display="none";
				document.getElementById('ajax_field_deductibleEndorsementPremiumNew').value="0.0";
				document.getElementById('ajax_field_deductibleEndorsementPremium').value="0.0";
				}
				else
					document.getElementById('premiumCalculationDC').style.display="block";
			}
			
			}
		
	}
	catch(err)
	{}
}
function setAmoutFormat(id)
{
	
	amountMask = new Mask("$#,###.####", "number");	
	if(document.getElementById(id) != null)	
		var class1volume = document.getElementById(id);
	if (class1volume != null) {
		class1volume.value = amountMask.format(class1volume.value);
		amountMask.attach(class1volume);
	
	}
	
	
	
	
	
}

function setProfileDropdownData()
{
	
	showSelectedFirstReviewer('StateCodefilter','StateCode_search');
	//showSelectedFirstReviewer('ProducerCodefilter','ajax_field_ProducerCode');
	//showSelectedFirstReviewer('FirstReviewerfilter','ajax_field_EligibleFirstReviewer');
	
	showSelectedFirstReviewer('ProducerCodefilter','ProducerCode_search');
	showSelectedFirstReviewer('EligibleFirstReviewerfilter','EligibleFirstReviewer_search');
	
	showSelectedFirstReviewer('BrokerID','BrokerKey');
	showSelectedFirstReviewer('CarrierID','CarrierKey');
	showSelectedFirstReviewer('CompanyKeyfilter','CompanyKey_search');
}
function showSelectedFirstReviewer(dstID,field)
{
	//alert('hello');
	//var fd = field.id;
	var fd = field;
	var operatingDivisionIds='';
    var operatingDivisionNames='';
    // debugger;
    var selected=[];
    $('#'+fd+' :selected').each(function(i, sel){
   	 	
           // var operatingDivisionId=$(sel).val();
           //foo[i] = $(selected).text(); 
         
         if(operatingDivisionIds ==''){
            operatingDivisionIds=$(sel).val();
            operatingDivisionNames= $(sel).text();
         }
         else{
            operatingDivisionIds=operatingDivisionIds+','+$(sel).val();
            operatingDivisionNames=operatingDivisionNames+','+$(sel).text();
         }
        
        });
    	
   
    if(document.getElementById(dstID)!=null)
        document.getElementById(dstID).value= operatingDivisionIds;
}
function loadProfile()
{
	//alert("StateCodefilter");
	try
	{
		if(document.getElementById("StateCodefilter")!=null)
		{
			values=document.getElementById("StateCodefilter").value;
			setSelectedIndexProfile(document.getElementById('StateCode_search'),values);
		}
	}
	catch(err)
	{}
	try
	{
		
		if(document.getElementById("ProducerCodefilter")!=null)
		{ 	
		values=document.getElementById("ProducerCodefilter").value;
			//alert(values);
			//setSelectedIndexProfile(document.getElementById('ajax_field_ProducerCode'),values);
			setSelectedIndexProfile(document.getElementById('ProducerCode_search'),values);
		}
	}
	catch(err)
	{}
	try
	{
		if(document.getElementById("EligibleFirstReviewerfilter")!=null)
		{
			values=document.getElementById("EligibleFirstReviewerfilter").value;
			//alert(values);
			//setSelectedIndexProfile(document.getElementById('ajax_field_EligibleFirstReviewer'),values);
			setSelectedIndexProfile(document.getElementById('EligibleFirstReviewer_search'),values);
		}
	}
	catch(err)
	{}
	
	try
	{
		if(document.getElementById("BrokerID")!=null)
		{
			values=document.getElementById("BrokerID").value;
			//alert(values);
			//setSelectedIndexProfile(document.getElementById('ajax_field_EligibleFirstReviewer'),values);
			setSelectedIndexProfile(document.getElementById('BrokerKey'),values);
		}
	}
	catch(err)
	{}
	
	try
	{
		if(document.getElementById("CarrierID")!=null)
		{
			values=document.getElementById("CarrierID").value;
			//alert(values);
			//setSelectedIndexProfile(document.getElementById('ajax_field_EligibleFirstReviewer'),values);
			setSelectedIndexProfile(document.getElementById('CarrierKey'),values);
		}
	}
	catch(err)
	{}
	try
	{
		if(document.getElementById("CompanyKeyfilter")!=null)
		{
			values=document.getElementById("CompanyKeyfilter").value;
			setSelectedIndexProfile(document.getElementById('CompanyKey_search'),values);
		}
	}
	catch(err)
	{}
}
function setSelectedIndexProfile(s, v) {
	//alert(s,v);
	var ss = v.split(",");
	for ( var j in ss) {
		for ( var i = 0; i < s.options.length; i++) {

			if (s.options[i].value == ss[j]) {				
				s.options[i].selected = true;
			}
			else if(s.options[i].value=="")
			{
				s.options[i].selected = false;
			}
		}
		if(ss.lenght==0)
		{
			s.options[0].selected = true;
		}

	}
}

function validateDate(date) {
	var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/ ;
	if(!(date_regex.test(date)))
	{
		return false;
	}
	return true;
}

function confirmDeleteSubproducerForm(){
	
	var x = confirm("Are you sure you want to delete this record ? ");
	return x ;
	
}

function SubProducerMask(){
	
	SubMask = new Mask("P#######");
	
	var SubNum = document.getElementById("ajax_field_ProducerCode");
	
	if (SubNum != null) {
		SubNum.value = SubMask.format(SubNum.value);
		SubMask.attach(SubNum);
		
	}
	
	
}

function SubProducerCommissionMask(){	
	CommissionMask = new Mask("##.##");
	var NC = document.getElementById("ajax_field_NewCommission");
	if (NC != null) {
		NC.value = CommissionMask.format(NC.value);
		CommissionMask.attach(NC);
	}
	
	var RNC = document.getElementById("ajax_field_RenewalCommission");
	if (RNC != null) {
		RNC.value = CommissionMask.format(RNC.value);
		CommissionMask.attach(RNC);
	}
	
}
function showHideNameChangeEndorsement(id)
{
	try
	{
	
	var isEnforcedPolicy=document.getElementById("isEnforcedPolicy").value;
	var anyDecendentPolicy=document.getElementById("anyDecendentPolicy").value;
	var isManualPremiumPolicy=document.getElementById("isManualPremiumPolicy").value;
	//alert(anyDecendentPolicy);
	var value=document.getElementById(id).value;
	if (value=='nameChangeEndorsement') 
	{
		
		document.getElementById("ratingOutputID").style.display = "none";
		document.getElementById("div1").style.display = "block";
		document.getElementById("buttonsDiv").style.display = "none";
		document.getElementById("div2").style.display = "none";
		document.getElementById("div3").style.display = "none";
		document.getElementById('ajax_field_priorActEndorsementPremiumCharge').checked=true;
		document.getElementById('ajax_field_premiumCalulated').value='N';
	}
	if(value=='priorActEndorsement')
	{
		document.getElementById("div2").style.display = "block";
		document.getElementById("div1").style.display = "none";
		document.getElementById("div3").style.display = "none";
		if(isEnforcedPolicy=='N')
			{
			document.getElementById("ajax_field_priorActEndorsementPremiumCharge").disabled=true;
			document.getElementById("ajax_field_priorActEndorsementPremiumCharge").checked=true;
			document.getElementById("buttonsDiv").style.display = "none";
			document.getElementById("ajax_field_returnPremium").value="0.0";
			document.getElementById("ajax_field_returnTax").value="0.0";
			document.getElementById("ajax_field_totalReturnPremium").value="0.0";
			document.getElementById('ajax_field_premiumCalulated').value='N';
			document.getElementById("ratingOutputID").style.display = "none";
			}
		else
			{
			if(anyDecendentPolicy=='Y' && isShowPemiumCalculation=='N' || isManualPremiumPolicy=='Y')
				{
				document.getElementById("ajax_field_priorActEndorsementPremiumCharge").disabled=true;
				document.getElementById("ajax_field_priorActEndorsementPremiumCharge").checked=true;
				document.getElementById("buttonsDiv").style.display = "none";
				document.getElementById("ajax_field_returnPremium").value="0.0";
				document.getElementById("ajax_field_returnTax").value="0.0";
				document.getElementById("ajax_field_totalReturnPremium").value="0.0";
				document.getElementById('ajax_field_premiumCalulated').value='N';
				}
			else
				{
				document.getElementById("ajax_field_priorActEndorsementPremiumCharge").disabled=false;
				document.getElementById("ajax_field_priorActEndorsementPremiumCharge").checked=false;
				document.getElementById("buttonsDiv").style.display = "block";
				document.getElementById('ajax_field_priorActEndorsementPremiumCharge').checked=false;
				document.getElementById('ajax_field_premiumCalulated').value='Y';
				document.getElementById("ratingOutputID").style.display = "block";
				}
			if(isManualPremiumPolicy=='Y')
				{document.getElementById("errorDivision").style.display = "block";}
			
			}
	}
	if(value=='policyExtensionEndorsement')
	{
		document.getElementById("div3").style.display = "block";
		//document.getElementById("buttonsDiv").style.display = "block";
		document.getElementById("div1").style.display = "none";
		document.getElementById("div2").style.display = "none";
		if(isEnforcedPolicy=='N')
		{
			document.getElementById("buttonsDiv").style.display = "none";
			document.getElementById("ajax_field_returnPremium").value="0.0";
			document.getElementById("ajax_field_returnTax").value="0.0";
			document.getElementById("ajax_field_totalReturnPremium").value="0.0";
			document.getElementById('ajax_field_priorActEndorsementPremiumCharge').checked=true;
			document.getElementById('ajax_field_premiumCalulated').value='N';
			document.getElementById("ratingOutputID").style.display = "none";
		}
		else
		{
			if(anyDecendentPolicy=='Y' && isShowPemiumCalculation=='N')
			{
				document.getElementById("buttonsDiv").style.display = "none";
				document.getElementById("ajax_field_returnPremium").value="0.0";
				document.getElementById("ajax_field_returnTax").value="0.0";
				document.getElementById("ajax_field_totalReturnPremium").value="0.0";
				document.getElementById('ajax_field_priorActEndorsementPremiumCharge').checked=true;
				document.getElementById('ajax_field_premiumCalulated').value='N';
				}
			else
			{
					document.getElementById("buttonsDiv").style.display = "block";
					document.getElementById('ajax_field_priorActEndorsementPremiumCharge').checked=false;
					document.getElementById('ajax_field_premiumCalulated').value='Y';
					document.getElementById("ratingOutputID").style.display = "block";
			}
		}
	}
	
	
	
	
	}
	catch(err)
	{}
	
}

function loadCertificateInsurance() {

	try
	{
	
    zipMask = new Mask("#####-####");
    var zip = document.getElementById("ajax_field_CertificateHolderZip");
    if (zip != null) {
          zip.value = zipMask.format(zip.value);
          zipMask.attach(zip);
    }
    var zip = document.getElementById("ajax_field_nameAddressEndorsementZipCode");
    if (zip != null) {
          zip.value = zipMask.format(zip.value);
          zipMask.attach(zip);
    }
	}
	catch(err)
	{alert(err.message);}
}
function showHidePremiumCalulator(id)
{
	//alert(document.getElementById(id).value);
	var y=document.getElementById('ajax_field_TypeOfEndorsement').value;
	var isManualPremiumPolicy=document.getElementById('isManualPremiumPolicy').value;
	if(y=='priorActEndorsement')
	{
			
		var x=document.getElementById(id).checked;
		//alert(x);
		
		if(x==true || isManualPremiumPolicy=='Y')
		{
			document.getElementById("ratingOutputID").style.display = "none";
			document.getElementById("buttonsDiv").style.display = "none";
			document.getElementById("ajax_field_premiumCalulated").value = 'N';			
		}
		if(x!=true)
		{
			document.getElementById("ratingOutputID").style.display = "block";
			document.getElementById("buttonsDiv").style.display = "block";
			document.getElementById("ajax_field_premiumCalulated").value = 'Y';			
		}
		
	}
	
	if(y=='policyExtensionEndorsement')
		document.getElementById("buttonsDiv").style.display = "block";
		
	
	/*alert(document.getElementById("ajax_field_premiumCalulated").value);*/
		
}
function showPopUp2(el, wdth, hght) {
	window.scrollTo(0,0);
	var wBody = parseInt(document.body.scrollWidth, 10);
	var wPopup = parseInt(document.getElementById(el).style.width, 10);
	document.documentElement.style.overflow = document.body.style.overflow = "auto";
	var w = ((wBody - wPopup) / 2);
	var h = 100;
	var cvr = document.getElementById("cover");
	var dlg = document.getElementById(el);
	//var height = document.body.clientHeight;
	var body = document.body,
	html = document.documentElement;
	
	var height = Math.max( body.scrollHeight, body.offsetHeight, 
	                              html.clientHeight, html.scrollHeight, html.offsetHeight );
	if (dlg != null && w!=null) {
		dlg.style.display = "block";
		dlg.style.left = w + "px";
		dlg.style.top = h + "px";
		//window.scrollTo(0,0);
		//alert(w+" "+h);
	}
	if (cvr != null) {
		cvr.style.height = height + "px";
		cvr.style.display = "block";
		//alert(height);
		cvr.style.position = "fixed";
	}
}
function closePopUp2(el) {
	document.documentElement.style.overflow = document.body.style.overflow = "auto";
	var cvr = document.getElementById("cover");
	var dlg = document.getElementById(el);
	//alert("--------"+cvr);
	if(cvr!=null){
		//alert(cvr.style);
		cvr.style.display = "none";
		dlg.style.display = "none";
	}
	//alert('agter');
	if(document.getElementById("tax_popup_error")!=null)
	{
		document.getElementById("tax_popup_error").innerHTML = "";
	}
	
}

function changeStatusColor(){
	var rowLength = $("#Search_firm_list_01").find("tr[class=bglightGray]").length;
	for(var statusCount=0; statusCount< rowLength; statusCount++){
		var rowStatusObj = $("#Search_firm_list_01").find("tr[class=bglightGray]:eq("+statusCount+")");
		var statusValue = rowStatusObj.find("td[id=policyStatusDesc]").find("span").find("span").text();
//		alert(statusValue)
		var find = 'LESSTHAN';
	    var re = new RegExp(find, 'g');
	    statusValue = statusValue.replace(re, "<");
		var find = 'GREATERTHAN';
	    var re = new RegExp(find, 'g');
	    statusValue = statusValue.replace(re, ">");
//	    alert(statusValue);
	    rowStatusObj.find("td[id=policyStatusDesc]").find("span").find("span").html(statusValue);
	}
}

function disableIndiOnlinePayButton(){
	var subProducerAccessValue = $.trim($("input[name='SubProducerAccess']:checked").val());
	var isDriectValue = $.trim($("input[name='IsDriect']:checked").val());
	
	if(subProducerAccessValue == 'N'){
		$("input[name='IndicationAccess'][value='Y']").prop("disabled",true);
		$("input[name='IndicationAccess'][value='Y']").attr("disabled","disabled");
		$("input[name='creditCardAccess'][value='Y']").prop("disabled",true);
		$("input[name='creditCardAccess'][value='Y']").attr("disabled","disabled");
		
		$("input[name='IndicationAccess'][value='N']").prop("checked",true);
		$("input[name='creditCardAccess'][value='N']").prop("checked",true);
		
		$("input[name='IsDriect'][value='Y']").prop("disabled",false);
		$("input[name='IsDriect'][value='Y']").removeAttr("disabled");
	} else {
		$("input[name='IndicationAccess'][value='Y']").prop("disabled",false);
		$("input[name='IndicationAccess'][value='Y']").removeAttr("disabled");
		$("input[name='creditCardAccess'][value='Y']").prop("disabled",false);
		$("input[name='creditCardAccess'][value='Y']").removeAttr("disabled");
		
		if(subProducerAccessValue == 'Y'){
			$("input[name='IsDriect'][value='Y']").prop("disabled",true);
			$("input[name='IsDriect'][value='Y']").attr("disabled","disabled");
			
			$("input[name='IsDriect'][value='N']").prop("checked",true);
			
			$("input[name='SendRenewalSolicitation'][value='Y']").prop("disabled",false);
			$("input[name='SendRenewalSolicitation'][value='Y']").removeAttr("disabled");
			
			$("input[name='SendQuoteLetter'][value='Y']").prop("disabled",false);
			$("input[name='SendQuoteLetter'][value='Y']").removeAttr("disabled");
		}
	}
}

function disableIsDriectButtonAct(){
	var subProducerAccessValue = $.trim($("input[name='SubProducerAccess']:checked").val());
	var isDriectValue = $.trim($("input[name='IsDriect']:checked").val());
	
	if(isDriectValue == 'Y'){
		$("input[name='IndicationAccess'][value='Y']").prop("disabled",true);
		$("input[name='IndicationAccess'][value='Y']").attr("disabled","disabled");
		$("input[name='IndicationAccess'][value='N']").prop("checked",true);

		$("input[name='creditCardAccess'][value='N']").prop("disabled",true);
		$("input[name='creditCardAccess'][value='N']").attr("disabled","disabled");
		$("input[name='creditCardAccess'][value='Y']").prop("checked",true);
		$("input[name='creditCardAccess'][value='Y']").prop("disabled",false);
		$("input[name='creditCardAccess'][value='Y']").removeAttr("disabled");

		$("input[name='SendRenewalSolicitation'][value='N']").prop("disabled",true);
		$("input[name='SendRenewalSolicitation'][value='N']").attr("disabled","disabled");
		$("input[name='SendRenewalSolicitation'][value='Y']").prop("checked",true);

		$("input[name='SendQuoteLetter'][value='Y']").prop("disabled",true);
		$("input[name='SendQuoteLetter'][value='Y']").attr("disabled","disabled");
		$("input[name='SendQuoteLetter'][value='N']").prop("checked",true);
		
		$("input[name='SubProducerAccess'][value='Y']").prop("disabled",true);
		$("input[name='SubProducerAccess'][value='Y']").attr("disabled","disabled");
		$("input[name='SubProducerAccess'][value='N']").prop("checked",true);
	} else {
		$("input[name='IndicationAccess'][value='Y']").prop("disabled",false);
		$("input[name='IndicationAccess'][value='Y']").removeAttr("disabled");
		
		$("input[name='creditCardAccess'][value='N']").prop("disabled",false);
		$("input[name='creditCardAccess'][value='N']").removeAttr("disabled");
		
		$("input[name='SendRenewalSolicitation'][value='N']").prop("disabled",false);
		$("input[name='SendRenewalSolicitation'][value='N']").removeAttr("disabled");
		
		$("input[name='SendQuoteLetter'][value='Y']").prop("disabled",false);
		$("input[name='SendQuoteLetter'][value='Y']").removeAttr("disabled");
		
		$("input[name='SubProducerAccess'][value='Y']").prop("disabled",false);
		$("input[name='SubProducerAccess'][value='Y']").removeAttr("disabled");
		
		if(subProducerAccessValue == 'N'){
			$("input[name='IndicationAccess'][value='Y']").prop("disabled",true);
			$("input[name='IndicationAccess'][value='Y']").attr("disabled","disabled");
			$("input[name='creditCardAccess'][value='Y']").prop("disabled",true);
			$("input[name='creditCardAccess'][value='Y']").attr("disabled","disabled");
			
			$("input[name='IndicationAccess'][value='N']").prop("checked",true);
			$("input[name='creditCardAccess'][value='N']").prop("checked",true);
		}
	}
}

function formPreview(){
	var url = window.location.href;
	
	url = url.substring(0 , url.indexOf("LawyersIns/") + 11);
	
	url += "Endorsement.do?User=Agent&role_id=3&AccountEmail=pat.cosulich@amerinstpro.com&divHideShow=&inet_context=";
	url += "&inet_method=reviewInsurenceCertificate&inet_page=certificateInsurance&dynaKeys=&dynaValues=";
	url += "&ctxmenuparam=&dateFormat=&dateFormatTS=&quickquoteoptionlist_pageFieldsList=";
	
	url += "&CertificateHolderName="+$("#ajax_field_CertificateHolderName").val();
	url += "&CertificateHolderAddress="+$("#ajax_field_CertificateHolderAddress").val();
	url += "&CertificateHolderState="+$("#ajax_field_CertificateHolderState").val();
	url += "&CertificateHolderCity="+$("#ajax_field_CertificateHolderCity").val();
	url += "&CertificateHolderZip="+$("#ajax_field_CertificateHolderZip").val();
	url += "&CertificateRequestedDate="+$("#ajax_field_CertificateRequestedDate").val();
//	url += "&AOP_Percentage_7="+$("#AOP_Percentage_7").val();
//	url += "&AOP_Percentage_8="+$("#AOP_Percentage_8").val();
//	url += "&AccountName="+$("#AccountName").val();
//	url += "&IsLoginMailSent="+$("#IsLoginMailSent").val();
	url += "&jsessionid="+$("#jsessionid").val();
	url += "&PolicyKey="+$("#PolicyKey").val();
	url += "&isShowSignature=N";
//	url += "&QQRMLink="+$("#QQRMLink").val();
//	url += "&QuoteNumber="+$("#QuoteNumber").val();
	
	
	var params = ['height='+screen.height,'width='+screen.width,'scrollbars=yes'].join(',');
	var win = window.open(url, 'reviewInsurenceCertificate', params);
	win.moveTo(0,0);
	win.focus();
//	win.print();
}

function displayEmailMessage(id)
{
	
	var val=document.getElementById(id).value;
	if(val==null || val=='')
		closePopUp2('popupsave3');
	
}
function loadAddChangeAttorney(id)
{
	
	var val=document.getElementById(id).checked;
	var isManualPremiumPolicy=document.getElementById('isManualPremiumPolicy').value;
	var endorsementType=document.getElementById('EndorsementType').value;
	//alert(val);
	if(val==false && isManualPremiumPolicy=='Y')
		
		document.getElementById('errorDivision').style.display = 'block';
	else
		document.getElementById('errorDivision').style.display = 'none';
	if(endorsementType=='14')
	{
	//alert(val);
	if(val==true || isManualPremiumPolicy=='Y')
	{
		
		document.getElementById('ratingOutputID').style.display = 'None';
		document.getElementById('ajax_field_endorsementActivity').checked=true;	
		document.getElementById('ajax_field_endorsementActivity').disabled=false;
		document.getElementById('isNonFinancialEndorsement').value='Y';
		document.getElementById('isFinancialEndorsement').value='N';
		document.getElementById('generateEndorsementDocuments').value='N';
		document.getElementById('calculteButtonDiv').style.display = 'None';
		document.getElementById('endorsementPdfDiv').style.display = 'None';
		document.getElementById('ajax_field_endorsementPremium').value='0.0';
		document.getElementById('ajax_field_endorsementTaxes').value='0.0';
		document.getElementById('ajax_field_endorsementTotalPremium').value='0.0';
	}
	else
		{
		document.getElementById('ratingOutputID').style.display = 'block';
		document.getElementById('ajax_field_endorsementActivity').checked=false;	
		document.getElementById('ajax_field_endorsementActivity').disabled=true;
		document.getElementById('isNonFinancialEndorsement').value='N';
		document.getElementById('isFinancialEndorsement').value='Y';
		document.getElementById('generateEndorsementDocuments').value='Y';
		document.getElementById('calculteButtonDiv').style.display = 'block';
		document.getElementById('endorsementPdfDiv').style.display = 'block';
		document.getElementById('ajax_field_endorsementPremium').value='';
		document.getElementById('ajax_field_endorsementTaxes').value='';
		document.getElementById('ajax_field_endorsementTotalPremium').value='';
		}
	}
	if(endorsementType=='15')
	{
		if(val==true ||isManualPremiumPolicy=='Y')
		{
			document.getElementById('ratingOutputID').style.display = 'none';
			document.getElementById('ajax_field_endorsementActivity').checked=true;	
			document.getElementById('ajax_field_endorsementActivity').disabled=false;
			document.getElementById('isNonFinancialEndorsement').value='Y';
			
			document.getElementById('isFinancialEndorsement').value='N';
			document.getElementById('generateEndorsementDocuments').value='N';
			document.getElementById('calculteButtonDiv').style.display = 'None';
			document.getElementById('ajax_field_endorsementTotalPremium').value='0.0';
			
		}
		else
		{
			document.getElementById('ratingOutputID').style.display = 'block';
			document.getElementById('ajax_field_endorsementActivity').checked=false;	
			document.getElementById('ajax_field_endorsementActivity').disabled=true;
			document.getElementById('isNonFinancialEndorsement').value='N';
			document.getElementById('isFinancialEndorsement').value='Y';
			document.getElementById('generateEndorsementDocuments').value='Y';
			document.getElementById('calculteButtonDiv').style.display = 'block';
			document.getElementById('ajax_field_endorsementTotalPremium').value='';
		}
	}
	
	
}



function loadAttorneyEndortsement(id)
{
	try
	{
	var val=document.getElementById('isFinancialEndorsement').value;
	var val1=document.getElementById('generateEndorsementDocuments').value;
	
	
	//alert(val+'   123  '+val1);
	
	if( val=='N')
	{
		document.getElementById('ajax_field_premiumChange').checked=true;	
		document.getElementById('isNonFinancialEndorsement').value='Y';
		if(val1==null || val1=='')
			{
				document.getElementById('ajax_field_endorsementActivity').checked=true;	
				document.getElementById('generateEndorsementDocuments').value='N';
			}
		
	}
	else if( val=='Y')
		{
		document.getElementById('ajax_field_premiumChange').checked=false;	

		document.getElementById('isNonFinancialEndorsement').value='N';
		document.getElementById('isFinancialEndorsement').value='Y';
		document.getElementById('ajax_field_endorsementActivity').disabled=true;	
		
		if(val1==null || val1=='')
		{
			document.getElementById('ajax_field_endorsementActivity').checked=false;	
			document.getElementById('generateEndorsementDocuments').value='Y';
		}
		
		}
	else
		{
			document.getElementById('ajax_field_premiumChange').checked=true;	
			document.getElementById('isNonFinancialEndorsement').value='Y';
			document.getElementById('isFinancialEndorsement').value='N';
			document.getElementById('ajax_field_endorsementActivity').disabled=false;	
		
			if(val1==null || val1=='')
			{
				document.getElementById('ajax_field_endorsementActivity').checked=true;	
				document.getElementById('generateEndorsementDocuments').value='N';
			}
		
			
		}
	if(val1=='N')
		
		document.getElementById('ajax_field_endorsementActivity').checked=true;
	if(val1=='Y')
		
		document.getElementById('ajax_field_endorsementActivity').checked=false;
	
	var endorsementType=document.getElementById('EndorsementType').value;
	var isFinancialEndorsement=document.getElementById('isFinancialEndorsement').value;
	if(endorsementType=='14')
		{
		if(isFinancialEndorsement=='N')
			{
				document.getElementById('ratingOutputID').style.display = 'None';
				document.getElementById('endorsementPdfDiv').style.display = 'None';
				document.getElementById('calculteButtonDiv').style.display = 'None';
				document.getElementById('ajax_field_endorsementPremium').value='0.0';
				document.getElementById('ajax_field_endorsementTaxes').value='0.0';
				document.getElementById('ajax_field_endorsementTotalPremium').value='0.0';
			}
		if(isFinancialEndorsement=='Y')
			{
				document.getElementById('calculteButtonDiv').style.display = 'block';
				document.getElementById('ratingOutputID').style.display = 'block';
				document.getElementById('endorsementPdfDiv').style.display = 'block';
			}
		}
	if(endorsementType=='15')
		{
		
		if(isFinancialEndorsement=='N')
		{
			document.getElementById('calculteButtonDiv').style.display = 'None';
			document.getElementById('ratingOutputID').style.display = 'None';
			document.getElementById('endorsementPdfDiv').style.display = 'None';
			document.getElementById('ajax_field_endorsementTotalPremium').value='0.0';
			
		}
		if(isFinancialEndorsement=='Y')
		{
			document.getElementById('calculteButtonDiv').style.display = 'block';
			document.getElementById('ratingOutputID').style.display = 'block';
			document.getElementById('endorsementPdfDiv').style.display = 'block';
		}
		
		}
	
	var  isEnforcedPolicy=document.getElementById('isEnforcedPolicy').value;
	//alert(cancellEndorsement);
		if(isEnforcedPolicy=='N')
			{
				
				document.getElementById("ajax_field_premiumChange").disabled = true;
			//	document.getElementById("ajax_field_endorsementActivity").disabled = true;
				document.getElementById('calculteButtonDiv').style.display = 'None';
				document.getElementById('ratingOutputID').style.display = 'None';
				document.getElementById('endorsementPdfDiv').style.display = 'None';
				document.getElementById('ajax_field_endorsementPremium').value='0.0';
				document.getElementById('ajax_field_endorsementTaxes').value='0.0';
				document.getElementById('ajax_field_endorsementTotalPremium').value='0.0';
			}
	}
	catch(err)
	{
		//alert(err.message);
	}
}


/*	function readonlyInputField(obj)
	{
		try{
			//obj.addClass("state_readonly");
			obj.parent().css('color','darkgrey')
			obj.prop("readonly",true);
			obj.attr("readonly","readonly");
			obj.attr("onclick","return false;");
			
		}
		catch(Error){}
	}

	function removeReadonlyInputField(obj,action)
	{
		try{
			//obj.removeClass("state_readonly");
			obj.prop("readonly",false);
			obj.removeAttr("readonly");
			obj.attr("onclick",action);
		} catch(Error){}
	} 

function setERPOption()
{
	
	var field1=document.getElementById('ajax_field_isAttorneyRetiring').checked;
	var field2=document.getElementById('ajax_field_isFirmERP').checked;
	if(field1==true)
		{
		
		
		$('#ajax_field_isFirmERP').prop("readonly",true);
		$('#ajax_field_isFirmERP').attr("onclick","return false;"); 
		
		
		document.getElementById('').disabled=true;
		document.getElementById('').disabled=true;
		document.getElementById('').disabled=true;
		document.getElementById('').disabled=true;
		document.getElementById('').disabled=true;
		readonlyInputField($('#ajax_field_isFirmERP'));
		document.getElementById('ajax_field_isFirmERP').style.color="grey";
		readonlyInputField($('#ajax_field_oneYear'));
		readonlyInputField($('#ajax_field_threeYear'));
		readonlyInputField($('#ajax_field_fiveYear'));
		readonlyInputField($('#ajax_field_sixYear'));
		readonlyInputField($('#ajax_field_unlimitedYears'));
		document.getElementById('ajax_field_unlimitedYears').checked=true;
		}
	else
		{
		document.getElementById('').disabled=false;
		document.getElementById('').disabled=false;
		document.getElementById('').disabled=false;
		document.getElementById('').disabled=false;
		document.getElementById('').disabled=false;
		document.getElementById('').disabled=false;
		removeReadonlyInputField($('#ajax_field_isFirmERP'),'setERPOption()');
		removeReadonlyInputField($('#ajax_field_oneYear'),'setERPOption()');
		removeReadonlyInputField($('#ajax_field_threeYear'),'setERPOption()');
		removeReadonlyInputField($('#ajax_field_fiveYear'),'setERPOption()');
		removeReadonlyInputField($('#ajax_field_sixYear'),'setERPOption()');
		removeReadonlyInputField($('#ajax_field_unlimitedYears'),'setERPOption()');
		
		
		document.getElementById('ajax_field_unlimitedYears').checked=false;
		
		}
	if(field2==true)
	{
		
	document.getElementById('ajax_field_isAttorneyRetiring').disabled=true;
	document.getElementById('ajax_field_nameOfAttorney').disabled=true;
	document.getElementById('ajax_field_practicePortion').disabled=true;

	}
	else
		{
		document.getElementById('ajax_field_isAttorneyRetiring').disabled=false;
		document.getElementById('ajax_field_nameOfAttorney').disabled=false;
		document.getElementById('ajax_field_practicePortion').disabled=false;
		}
}
*/
function setERPOption()
{
	
	var field1=document.getElementById('ajax_field_isAttorneyRetiring').checked;
	var field2=document.getElementById('ajax_field_isFirmERP').checked;
	//alert(field1+'     '+field2);
	if(field1==true)
		{
		
		document.getElementById('ajax_field_isFirmERP').disabled=true;
		document.getElementById('ajax_field_oneYear').disabled=true;
		document.getElementById('ajax_field_threeYear').disabled=true;
		document.getElementById('ajax_field_fiveYear').disabled=true;
		document.getElementById('ajax_field_sixYear').disabled=true;
		document.getElementById('ajax_field_unlimitedYears').disabled=true;
		/*document.getElementById('ajax_field_unlimitedYears').checked=true;*/
		
		document.getElementById('ajax_field_isAttorneyRetiring').disabled=false;
		document.getElementById('ajax_field_nameOfAttorney').disabled=false;
		document.getElementById('ajax_field_practicePortion').disabled=false;
		}
	
	
	else if(field2==true)
	{
		//alert('hello');
	document.getElementById('ajax_field_isAttorneyRetiring').disabled=true;
	document.getElementById('ajax_field_nameOfAttorney').disabled=true;
	document.getElementById('ajax_field_practicePortion').disabled=true;

	
	document.getElementById('ajax_field_isFirmERP').disabled=false;
	document.getElementById('ajax_field_oneYear').disabled=false;
	document.getElementById('ajax_field_threeYear').disabled=false;
	document.getElementById('ajax_field_fiveYear').disabled=false;
	document.getElementById('ajax_field_sixYear').disabled=false;
	document.getElementById('ajax_field_unlimitedYears').disabled=false;
	
	document.getElementById('ajax_field_isFirmERP').checked=true;
	document.getElementById('ajax_field_oneYear').checked=true;
	document.getElementById('ajax_field_threeYear').checked=true;
	document.getElementById('ajax_field_fiveYear').checked=true;
	document.getElementById('ajax_field_sixYear').checked=true;
	document.getElementById('ajax_field_unlimitedYears').checked=true;

	
	document.getElementById('unlimitedYearsERP').value=10;
	}
	else
	{
		document.getElementById('ajax_field_isAttorneyRetiring').disabled=false;
		document.getElementById('ajax_field_nameOfAttorney').disabled=false;
		document.getElementById('ajax_field_practicePortion').disabled=false;
		document.getElementById('ajax_field_isFirmERP').disabled=false;
		document.getElementById('ajax_field_oneYear').disabled=false;
		document.getElementById('ajax_field_threeYear').disabled=false;
		document.getElementById('ajax_field_fiveYear').disabled=false;
		document.getElementById('ajax_field_sixYear').disabled=false;
		document.getElementById('ajax_field_unlimitedYears').disabled=false;
		document.getElementById('ajax_field_unlimitedYears').checked=false;
		document.getElementById('ajax_field_isFirmERP').checked=false;
		document.getElementById('ajax_field_oneYear').checked=false;
		document.getElementById('ajax_field_threeYear').checked=false;
		document.getElementById('ajax_field_fiveYear').checked=false;
		document.getElementById('ajax_field_sixYear').checked=false;
		
	}
}
function ipfsPFAPreview(){
	var url = window.location.href;
	
	url = url.substring(0 , url.indexOf("LawyersIns/") + 11);
	
	url += "Endorsement.do?User=insured&role_id=1&AccountEmail=pat.cosulich@amerinstpro.com&divHideShow=&inet_context=";
	url += "&inet_method=ipfsPFAPreview&inet_page=paymentmethod&dynaKeys=&dynaValues=";
	url += "&ctxmenuparam=&dateFormat=&dateFormatTS=&quickquoteoptionlist_pageFieldsList=";
	
	url += "&jsessionid="+$("#jsessionid").val();
	url += "&IPFSPFAURL="+$("#IPFSPFAURL").val();
	url += "&PaymentMode="+document.getElementById('PaymentMode').value;
	
	var params = ['height='+screen.height,'width='+screen.width,'scrollbars=yes'].join(',');
	var win = window.open(url, 'ipfsPFAPreview', params);
	win.moveTo(0,0);
	win.focus();
//	win.print();
}
function setERPEndorsement()
{
	var field=document.getElementById('ajax_field_isAttorneyRetiring1').checked;
	
	if(field==true)
	{//alert(field);
		document.getElementById('ajax_field_isDeathDisabilityERP1').disabled=true;
		document.getElementById('ajax_field_isDeathDisabilityERP2').disabled=true;
		document.getElementById('ajax_field_deathDisabilityFirmOrAttorneyName').disabled=true;
		document.getElementById('ajax_field_deathDiablityCeasedDate').disabled=true;
		document.getElementById('ajax_field_isFirmERP1').disabled=true;
		document.getElementById('ajax_field_isFirmERP2').disabled=true;
		document.getElementById('ajax_field_yearsOfERP').disabled=true;
		document.getElementById('ajax_field_nameOfAttorney').disabled=false;
		document.getElementById('ajax_field_ceasedDate').disabled=false;
		document.getElementById('ajax_field_isInsuredWithUsFromPast1').disabled=false;
		document.getElementById('ajax_field_isInsuredWithUsFromPast2').disabled=false;
		document.getElementById('ajax_field_percentagePracticeOfAttorney').disabled=false;

		document.getElementById('ajax_field_isAttorneyRetiring1').disabled=true;
		document.getElementById('ajax_field_isAttorneyRetiring2').disabled=true;
		document.getElementById('ajax_field_ceasedDate').disabled=true;
		document.getElementById('ajax_field_isInsuredWithUsFromPast1').disabled=true;
		document.getElementById('ajax_field_isInsuredWithUsFromPast2').disabled=true;
		document.getElementById('ajax_field_practicePortitonOfAttorney').disabled=true;
		/*document.getElementById('ajax_field_isDeathDisabilityERP1').checked=false;
		document.getElementById('ajax_field_isFirmERP1').checked=false;
		*/
		
	}
	
	var field1=document.getElementById('ajax_field_isAttorneyRetiring2').checked;
	//alert(field1);
	if(field1==true)
	
	{
		document.getElementById('ajax_field_nameOfAttorney').disabled=false;
		document.getElementById('ajax_field_ceasedDate').disabled=false;
		document.getElementById('ajax_field_isInsuredWithUsFromPast1').disabled=false;
		document.getElementById('ajax_field_isInsuredWithUsFromPast2').disabled=false;
		document.getElementById('ajax_field_practicePortitonOfAttorney').disabled=false;
		document.getElementById('ajax_field_isDeathDisabilityERP1').disabled=false;
		document.getElementById('ajax_field_isDeathDisabilityERP2').disabled=false;
		document.getElementById('ajax_field_deathDisabilityFirmOrAttorneyName').disabled=false;
		document.getElementById('ajax_field_deathDiablityCeasedDate').disabled=false;
		document.getElementById('ajax_field_isFirmERP1').disabled=false;
		document.getElementById('ajax_field_isFirmERP2').disabled=false;
		document.getElementById('ajax_field_yearsOfERP').disabled=false;
	
		
		
	}
	
	/*var field3=document.getElementById('ajax_field_isDeathDisabilityERP2').checked;
	
	 if(field1==true && field3==true)
		 {
				document.getElementById('ajax_field_isFirmERP1').disabled=true;
				document.getElementById('ajax_field_isFirmERP2').disabled=true;
				document.getElementById('ajax_field_yearsOfERP').disabled=true;
				
			
		 }*/
	/* if(field1!=true && field3!=true && field!=true)
	 {
			document.getElementById('ajax_field_isFirmERP1').disabled=false;
			document.getElementById('ajax_field_isFirmERP2').disabled=false;
			document.getElementById('ajax_field_yearsOfERP').disabled=false;
			
		
	 }*/
	
}
function isAttoryneyWithUsFromPast()
{
	
	var isInsuredWithUsFromPast1=document.getElementById('ajax_field_isInsuredWithUsFromPast1').checked;
	var field=document.getElementById('ajax_field_isAttorneyRetiring1').checked;
	var field1=document.getElementById('ajax_field_isAttorneyRetiring2').checked;
	//alert(field+' '+isInsuredWithUsFromPast1);
	if(field1==true)
		document.getElementById('ajax_field_practicePortitonOfAttorney').disabled=true;
	else if(isInsuredWithUsFromPast1==true && field==true )
		{document.getElementById('ajax_field_practicePortitonOfAttorney').disabled=true;}
	else
		{document.getElementById('ajax_field_practicePortitonOfAttorney').disabled=false;}	
}
function loadDeathDiabilityERP()
{
	var field1=document.getElementById('ajax_field_isAttorneyRetiring2').checked;
	var field2=document.getElementById('ajax_field_isDeathDisabilityERP1').checked;
	var field3=document.getElementById('ajax_field_isDeathDisabilityERP2').checked;
	//alert(field1+' '+field2+' '+field3 )
	if(field2==true)
		{
		document.getElementById('ajax_field_isFirmERP1').disabled=true;
		document.getElementById('ajax_field_isFirmERP2').disabled=true;
		document.getElementById('ajax_field_yearsOfERP').disabled=true;
		document.getElementById('ajax_field_deathDisabilityFirmOrAttorneyName').disabled=false;
		document.getElementById('ajax_field_deathDiablityCeasedDate').disabled=false;
		document.getElementById('ajax_field_isAttorneyRetiring1').disabled=true;
		document.getElementById('ajax_field_isAttorneyRetiring2').disabled=true;
		document.getElementById('ajax_field_nameOfAttorney').disabled=true;
		document.getElementById('ajax_field_ceasedDate').disabled=true;
		document.getElementById('ajax_field_isInsuredWithUsFromPast1').disabled=true;
		document.getElementById('ajax_field_isInsuredWithUsFromPast2').disabled=true;
		document.getElementById('ajax_field_practicePortitonOfAttorney').disabled=true;
		
		}
	else if(field3==true)
		{
		
		document.getElementById('ajax_field_isFirmERP1').disabled=false;
		document.getElementById('ajax_field_isFirmERP2').disabled=false;
		document.getElementById('ajax_field_yearsOfERP').disabled=false;
		document.getElementById('ajax_field_deathDisabilityFirmOrAttorneyName').disabled=false;
		document.getElementById('ajax_field_deathDiablityCeasedDate').disabled=false;
		document.getElementById('ajax_field_isAttorneyRetiring1').disabled=false;
		document.getElementById('ajax_field_isAttorneyRetiring2').disabled=false;
		document.getElementById('ajax_field_nameOfAttorney').disabled=false;
		document.getElementById('ajax_field_ceasedDate').disabled=false;
		document.getElementById('ajax_field_isInsuredWithUsFromPast1').disabled=false;
		document.getElementById('ajax_field_isInsuredWithUsFromPast2').disabled=false;
		document.getElementById('ajax_field_practicePortitonOfAttorney').disabled=false;
		
		}
	else
		{
		/*//alert('tata');
		document.getElementById('ajax_field_isFirmERP1').disabled=false;
		document.getElementById('ajax_field_isFirmERP2').disabled=false;
		document.getElementById('ajax_field_yearsOfERP').disabled=false;
		document.getElementById('ajax_field_deathDisabilityFirmOrAttorneyName').disabled=false;
		document.getElementById('ajax_field_deathDiablityCeasedDate').disabled=false;*/
		}
	
	}

function loadfirmERP()
{
	var field1=document.getElementById('ajax_field_isFirmERP1').checked;
	var field2=document.getElementById('ajax_field_isFirmERP2').checked;
	
	//alert(field1+' '+field2+' '+field3 )
	if(field1==true)
		{
		
		document.getElementById('ajax_field_deathDisabilityFirmOrAttorneyName').disabled=true;
		document.getElementById('ajax_field_deathDiablityCeasedDate').disabled=true;
		document.getElementById('ajax_field_isAttorneyRetiring1').disabled=true;
		document.getElementById('ajax_field_isAttorneyRetiring2').disabled=true;
		document.getElementById('ajax_field_nameOfAttorney').disabled=true;
		document.getElementById('ajax_field_ceasedDate').disabled=true;
		document.getElementById('ajax_field_isInsuredWithUsFromPast1').disabled=true;
		document.getElementById('ajax_field_isInsuredWithUsFromPast2').disabled=true;
		document.getElementById('ajax_field_practicePortitonOfAttorney').disabled=true;
		document.getElementById('ajax_field_isDeathDisabilityERP1').disabled=true;
		document.getElementById('ajax_field_isDeathDisabilityERP2').disabled=true;
		
		}
	else if(field2==true)
		{
		
		document.getElementById('ajax_field_isFirmERP1').disabled=false;
		document.getElementById('ajax_field_isFirmERP2').disabled=false;
		document.getElementById('ajax_field_yearsOfERP').disabled=false;
		document.getElementById('ajax_field_deathDisabilityFirmOrAttorneyName').disabled=false;
		document.getElementById('ajax_field_deathDiablityCeasedDate').disabled=false;
		document.getElementById('ajax_field_isAttorneyRetiring1').disabled=false;
		document.getElementById('ajax_field_isAttorneyRetiring2').disabled=false;
		document.getElementById('ajax_field_nameOfAttorney').disabled=false;
		document.getElementById('ajax_field_ceasedDate').disabled=false;
		document.getElementById('ajax_field_isInsuredWithUsFromPast1').disabled=false;
		document.getElementById('ajax_field_isInsuredWithUsFromPast2').disabled=false;
		document.getElementById('ajax_field_practicePortitonOfAttorney').disabled=false;
		document.getElementById('ajax_field_isDeathDisabilityERP1').disabled=false;
		document.getElementById('ajax_field_isDeathDisabilityERP2').disabled=false;
		
		}
	else
		{
		
		}
	
	}




function showHideERPYears()
{
	var field1=document.getElementById('ajax_field_isFirmERP1').checked;
	
	if(field1==true)
		document.getElementById('yearsDiv').style.display = 'block';
		else
			document.getElementById('yearsDiv').style.display = 'none';
}

function addRightAOP(){
	var items=document.getElementsByName('AOPKey');
	var selectedItems="";
	try
	{
	for (var i = 0; i < items.length; i++) {

		var aopFlag = 0;
		var AOP_values = document.getElementById("AOP_values").value.split(",");
		for(aopCount = 0; aopCount < AOP_values.length; aopCount++){
			if(AOP_values[aopCount] == items[i].value){
				if(i>0 && selectedItems != ""){
					selectedItems += "," ;
				}
				selectedItems += items[i].value;
				aopFlag = 1;
			}
		}
		if(aopFlag == 0){
			if(items[i].type=='checkbox' && items[i].checked==true){
				if(i>0 && selectedItems != ""){
					selectedItems += "," ;
				}
				selectedItems += items[i].value;
			}
		}
	}
	document.getElementById("AOP_values").value = selectedItems;
	addAOP();
	}
	catch(e)
	{}
}

function addAOP(){
	if($.trim(document.getElementById("AOP_values").value) !== ""){
		var rightAOPHTML = "";
		var selectedAopValue = $.trim(document.getElementById("AOP_values").value).split(",");
		var items=document.getElementsByName('AOPKey');
		for(var selectedAopCount = 0; selectedAopCount < selectedAopValue.length; selectedAopCount++){
			for (var aopItemCount = 0; aopItemCount < items.length; aopItemCount++) {
				if(items[aopItemCount].type=='checkbox' && items[aopItemCount].value == selectedAopValue[selectedAopCount]){
					$("#aoplist_list_1").find("table tbody").find("input[id=AOPKey][value=" + selectedAopValue[selectedAopCount] + "]")[0].checked = false;
					var selectedAop = $("#aoplist_list_1").find("table tbody").find("tr:eq(" + aopItemCount +")").find("td:eq(1)").text();
					rightAOPHTML += "<tr id=\"" + selectedAopValue[selectedAopCount] + "\"><td class=\"aopName\" width=\"75%\">";
					rightAOPHTML += "<input id=\"AOPDesc_" + selectedAopValue[selectedAopCount] + "\" type=\"hidden\" value=\"" + selectedAop + "\" name=\"AOPDesc_" + selectedAopValue[selectedAopCount] + "\">";
					rightAOPHTML += "<label id=\"AOPDesc\" name=\"AOPDesc\">" + selectedAop + "</label>";
					rightAOPHTML += "</td><td class=\"aopPercentage\" width=\"20%\" valign=\"middle\">";
					rightAOPHTML += "<input id=\"AOP_Percentage_" + selectedAopValue[selectedAopCount] + "\" type=\"text\" value=\"\" style=\"width: 70px; height: auto;\" onblur=\"populateTotalFieldValue('AOP_Percentage_','TotalAOP_Percentage');\" onkeypress='return IsNumeric(this, event, true, true, \"\");' ondrop=\"return false;\" onpaste=\"return false;\" maxlength=\"3\" placeholder=\"%\" name=\"AOP_Percentage_" + selectedAopValue[selectedAopCount] + "\">";
					rightAOPHTML += "</td><td class=\"aopPageSave\" width=\"5%\">";
					rightAOPHTML += "<a class=\"Link_blue\" onclick=\"removeAOP('" + selectedAopValue[selectedAopCount] + "');\" ><i class=\"fa fa-times delete_icon\"> </i></a>";
					rightAOPHTML += "</td></tr>";
					break;
				}
			}
		}
		
		var aopPercentageValue = "";
		var aopPercentageTRValue = "";
		if($.trim($("#rightaoplist_list").html()) != ""){
			for(var k = 0; k < $("#rightaoplist_list").find("tr").length; k++){
				if($.trim($("#rightaoplist_list").html()) !== ""){
					if(k>0){
						aopPercentageValue += ",";
						aopPercentageTRValue += ",";
					}
					aopPercentageValue += $("#rightaoplist_list").find("tr:eq(" + k + ")").find("td:eq(1)").find("input").val();
					aopPercentageTRValue += $("#rightaoplist_list").find("tr:eq(" + k + ")").attr("id");
				}
			}
			aopPercentageValue = aopPercentageValue.split(",");
			aopPercentageTRValue = aopPercentageTRValue.split(",");
		}
		$("#rightaoplist_list").html(rightAOPHTML);
		for(var k = 0; k < aopPercentageTRValue.length; k++){
			$("#rightaoplist_list").find("tr[id=" + aopPercentageTRValue[k] + "]").find("td:eq(1)").find("input").val(aopPercentageValue[k]);
		}
		$("#error_SelectAOP").removeClass("tr_errors");
		$("#error_SelectAOP").hide();
	} else {
		$("#error_SelectAOP").html("Please select any area of practice");
		$("#error_SelectAOP").addClass("tr_errors");
	}
}

function removeAOP(value){
	var selectedItems = $.trim(document.getElementById("AOP_values").value);
	selectedItems = selectedItems.replace(value,"0");
	document.getElementById("AOP_values").value = selectedItems;
	addAOP();
	populateTotalFieldValue('AOP_Percentage_','TotalAOP_Percentage');
}

function onPageLoad(){
	if($.trim(document.getElementById("AOP_values").value) !== ""){
		var selectedItems = $.trim(document.getElementById("AOP_values").value).split(",");
		for(var i = 0; i < selectedItems.length; i++){
			if(selectedItems[i] != 0 && selectedItems[i] != 24 && selectedItems[i] != 62 && selectedItems[i] != 35 && selectedItems[i] != 8 && selectedItems[i] != 5 && selectedItems[i] != 27 && selectedItems[i] != 20 && selectedItems[i] != 3){
				$("#aoplist_list_1").find("table tbody").find("input[id=AOPKey][value=" + selectedItems[i] + "]")[0].checked = true;
			}
		}
		addAOP();
		var aopPercentageValue = $.trim(document.getElementById("aopPercentageValue").value);
		var aopPercentageTRValue = $.trim(document.getElementById("aopPercentageTRValue").value);
		aopPercentageValue = aopPercentageValue.split(",");
		aopPercentageTRValue = aopPercentageTRValue.split(",");
		for(var k = 0; k < aopPercentageTRValue.length; k++){
			$("#rightaoplist_list").find("tr[id=" + aopPercentageTRValue[k] + "]").find("td:eq(1)").find("input").val(aopPercentageValue[k]);
			$("#rightaopstatic_list").find("tr[id=" + aopPercentageTRValue[k] + "]").find("td:eq(1)").find("input").val(aopPercentageValue[k]);
		}
//		populateTotalFieldValue('AOP_Percentage_','TotalAOP_Percentage');
	}
	if($.trim(document.getElementById("filedDisabled").value) === "YES"){
		$("#addRightAOPQQ").removeAttr("onclick");
		$("#addRightAOPQQ").attr("disabled", "disabled");
		$("#aoplist_list_1").find("table tbody").find("input[id=AOPKey][type=checkbox]").attr("disabled", "disabled");
		var aopPercentageTRValue = $.trim(document.getElementById("aopPercentageTRValue").value);
		aopPercentageTRValue = aopPercentageTRValue.split(",");
		for(var k = 0; k < aopPercentageTRValue.length; k++){
			$("#rightaoplist_list").find("tr[id=" + aopPercentageTRValue[k] + "]").find("td:eq(1)").find("input").attr("disabled", "disabled");
			$("#rightaoplist_list").find("tr[id=" + aopPercentageTRValue[k] + "]").find("td:eq(2)").remove();
		}
	}
	
//	var aopTabIndex = 90;
//	for(var aopListCount = 0; aopListCount<24; aopListCount++){
//		aopTabIndex += 10;
//		$("#leftAOPList").find("tbody").find("tr:eq(" + aopListCount + ")").find("input[id=AOPKey][name=AOPKey]").attr("tabindex",aopTabIndex);
//	}
}

function getPercentageValue(){
	var aopPercentageValue = "";
	var aopPercentageTRValue = "";
	var aopRowCount = 0;
	var aopLists = ["#rightaopstatic_list", "#rightaoplist_list"];
	for(var listIndex = 0; listIndex < aopLists.length; listIndex++){
		if($.trim($(aopLists[listIndex]).html()) != ""){
			for(var k = 0; k < $(aopLists[listIndex]).find("tr").length; k++){
				var aopRow = $(aopLists[listIndex]).find("tr:eq(" + k + ")");
				if(aopRow.find("td:eq(1)").find("input").length > 0){
					if(aopRowCount > 0){
						aopPercentageValue += ",";
						aopPercentageTRValue += ",";
					}
					aopPercentageValue += aopRow.find("td:eq(1)").find("input").val();
					aopPercentageTRValue += aopRow.attr("id");
					aopRowCount++;
				}
			}
		}
	}
	document.getElementById("aopPercentageValue").value = aopPercentageValue;
	document.getElementById("aopPercentageTRValue").value = aopPercentageTRValue;
}

function submitProtexureEstimateForm(){
	try {
		normalizeProtexurePriorActFields();
	} catch(e) {}
	try {
		getPercentageValue();
	} catch(e) {}
}

function normalizeProtexurePriorActFields(){
	var priorActDate = $("input[name=PriorActDatePross]").val();
	var firmYear = $("input[name=FirmYear]").val();
	if($.trim(priorActDate) !== "" && $.trim(firmYear) === ""){
		$("input[name=IsPriorActDateFull][value=N]").prop("checked", true);
		$("#fullYear").hide();
		$("#priorActsSpecific").show();
	} else if($.trim(firmYear) !== "" && $.trim(priorActDate) === ""){
		$("input[name=IsPriorActDateFull][value=Y]").prop("checked", true);
		$("#priorActsSpecific").hide();
		$("#fullYear").show();
	}

	if(getGenericRadioValue("IsPriorActDateFull") === "N"){
		$("input[name=FirmYear]").val("");
	} else if(getGenericRadioValue("IsPriorActDateFull") === "Y"){
		$("input[name=PriorActDatePross]").val("");
	}
}

function aopKeyPressEvent(event){
	var key;
	if (window.event) {
		key = event.keyCode;
	} else if (event.which) {
		key = event.which;
	}
	
	if (key === 32 || key === 13) {
		$("#addRightAOPQQ").trigger("click");
	}
}

function showbenefits2(){
	
	document.getElementById('showdivbenefits').style.display = 'block';
}


/*function showbenefits() {
    var x = document.getElementById("showdivbenefits");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}*/
function showbenefits3() {
	  document.getElementById("showdivbenefits").innerHTML ="Protexure Lawyers Program Benefits we will want to change out from time to time.";
	  //document.getElementById("demo").innerHTML = "YOU CLICKED ME!";
	}
function showbenefits() {
    var popup = document.getElementById("myPopup");
    popup.classList.toggle("show");
}

function showHideClaimAgeFiled(){
	try
	{
		var numberOfLawyers = parseInt($("#NumberOfLawyers option:selected").val());
		var numberOfOption = parseInt($("#NumberOfLawyers option").length);
		$("*.hideClaimAge").hide();
		for(var i = 0; i < numberOfLawyers; i++){
			$("#ClaimAge_l" + (i+1)).show();
		}
		for(var i = numberOfLawyers; i < numberOfOption; i++){
			$("#ClaimAge_" + (i+1)).val("");
		}
	}
	catch(e)
	{}
	document.getElementById('aoplist_div').style.display = "none";	
}

function detectBrowser(){
	var objAgent = window.navigator.userAgent; 
	var objbrowserName = ""; 
	
	if ((objAgent.indexOf("Chrome"))!=-1) { // In Chrome 
		objbrowserName = "Chrome";
	} else if ((objAgent.indexOf("MSIE"))!=-1 || navigator.userAgent.match(/Trident.*rv\:11\./)) { // In Microsoft internet explorer 
		objbrowserName = "Microsoft Internet Explorer";
	} else if ((objAgent.indexOf("Firefox"))!=-1) {  // In Firefox 
		objbrowserName = "Firefox"; 
	} else if ((objAgent.indexOf("Safari"))!=-1) {  // In Safari 
		objbrowserName = "Safari"; 
	}
	return objbrowserName;
}

function processBarIn(){
	try{
	var cvr = document.getElementById("cover");
	  var body = document.body,
	  html = document.documentElement;
		
	  var height = Math.max( body.scrollHeight, body.offsetHeight, html.clientHeight, html.scrollHeight, html.offsetHeight );
		
	  if (cvr != null) {
		  cvr.style.height = height + "px";
		  cvr.style.display = "block";
		  cvr.style.position = "fixed";
	  }
	$("#processing").show();
	}catch (e) {
		// TODO: handle exception
	}
}
function processBarOut(){
	try{
	var cvr = document.getElementById("cover");
	  var body = document.body,
	  html = document.documentElement;
		
	  var height = Math.max( body.scrollHeight, body.offsetHeight, html.clientHeight, html.scrollHeight, html.offsetHeight );
		
	  if (cvr != null) {
		  cvr.style.height = height + "px";
		  cvr.style.display = "none";
		  cvr.style.position = "";
	  }
	$("#processing").hide();
	}catch (e) {
		// TODO: handle exception
	}
}
function setEndorsementActivity(id)
{
	var val=document.getElementById('ajax_field_endorsementActivity').checked;
	//alert(val);
	if(val==true)
		document.getElementById('generateEndorsementDocuments').value='N';
	if(val==false)
		document.getElementById('generateEndorsementDocuments').value='Y';
	
}
function toUpperCase(id) {
	  var str = document.getElementById(id).value;
	  var res = str.toUpperCase();
	  document.getElementById(id).value=res;
}
/*function showHideFinancialEndorsement(id)
{
	var val=document.getElementById(id).checked;
	if(val=='Y')
		document.getElementById('ajax_field_priorActEndorsementPremiumCharge').checked=true;
	if(val=='N')
		document.getElementById('ajax_field_priorActEndorsementPremiumCharge').checked=false;
}*/

function isAreasofPracticeValueChange(){
	try{
	if(new Date($("#AOPRenewalDate").val()) <= new Date($("#PolicyEffectiveDate").text())){
	if($.trim($("#PolicyStatusKeyAOP").val()) == '2' && ($.trim($("#StatusKeyAOP").val()) == '1' || $.trim($("#StatusKeyAOP").val()) == '2')){
	var p = getGenericRadioValue("IsAOPChange");
	
	var trLength = $.trim($("#aopCount").val());

	for(var aopCount = 0; aopCount < trLength; aopCount++){
		var trObj = $("#aoplist_list_2").find("tbody").find("tr[id=aoplist_list_mom_data_2_" + aopCount + "]");
		var isEditValue = trObj.find("input[name=IsEdit_" + aopCount + "]").val();
		if (p == 'Y'){
			trObj.find("input[name=AOP_Percentage_" + aopCount + "]").removeAttr("readonly");
		} else if ((p == 'N' || $.trim(p) == '') && isEditValue == 'N'){
			trObj.find("input[name=AOP_Percentage_" + aopCount + "]").attr("readonly", "readonly");
		}
	}
	if (p == 'Y'){
		$("*#addAopNewApp").show();
		$("#ajax_field_litDefenseOtherDesc").removeAttr("readonly");
		$("#ajax_field_litPlaintiffotherDesc").removeAttr("readonly");
		$("#ajax_field_otherAopDescLaw").removeAttr("readonly");
	} else {
		$("*#addAopNewApp").hide();
		$("#ajax_field_litDefenseOtherDesc").attr("readonly", "readonly");
		$("#ajax_field_litPlaintiffotherDesc").attr("readonly", "readonly");
		$("#ajax_field_otherAopDescLaw").attr("readonly", "readonly");
	}
	}
	if(!($.trim($("#StatusKeyAOP").val()) == '1' || $.trim($("#StatusKeyAOP").val()) == '2')){
		$("#ajax_field_litDefenseOtherDesc").attr("readonly", "readonly");
		$("#ajax_field_litPlaintiffotherDesc").attr("readonly", "readonly");
		$("#ajax_field_otherAopDescLaw").attr("readonly", "readonly");
	}
	}
	}catch (e) {
		// TODO: handle exception
	}
}
function clearSearchData()
{
	document.getElementById('AccountNameSearch').value=null;
	document.getElementById('AccountEmailSearch').value=null;
	document.getElementById('ajax_field_PolicyEffectiveMonthYear').value=null;
	document.getElementById('ajax_field_WorkPhoneSearch').value=null;
	document.getElementById('ajax_field_MergeStateCode').value="";
	document.getElementById('ajax_field_AccountNumberSearch').value=null;
	document.getElementById('errorsDiv').style.display="none";
	document.getElementById('ajax_field_linkAccounts').checked=false;
	document.getElementById('ajax_field_deLinkAccounts').checked=false;
	
	
	
}
function setMonthYearFormat(id)
{
		
	var val=document.getElementById(id).value;
	if(val!=null)
		{
		var len=val.length;
		/*if (len !=6) {
			for ( var i = 0; i < len; i++) {
				var c = val.charAt(i);
				alert(Number.isNaN(c));
				if (Number.isNaN(c)) {
					val= val.replace(c,"");
					alert(val);
					document.getElementById(id).value=val;
					flag = true;
				}
			}
		}
		if (flag) {
			return;
		}*/
		if (len == 6) {
			var mm = val.substr(0, 2);
			var yy = val.substr(2, 4);
			val = mm + '/'+ yy;
		}
		document.getElementById(id).value=val;
		}
}

function linkDelinkAccounts()
{
	var field1=document.getElementById('ajax_field_linkAccounts').checked;
	var field2=document.getElementById('ajax_field_deLinkAccounts').checked;
	
	if(field1==true)
		{
		document.getElementById('accountActivity').value='linkAccounts';
		document.getElementById('actionName').innerHTML ='link';
		}
	if(field2==true)
	{
		document.getElementById('accountActivity').value='deLinkAccounts';
		document.getElementById('actionName').innerHTML ='de-link';
	}
	
	
}

function dataTablesFn(){
	var dataTables_grid = $('.dataTables_grid').attr('id');
	$('#'+dataTables_grid).each(function(){
		var noRecordFound = $(this).find('.EmptyRowStyle').length;
		if(noRecordFound != 1){
			$(this).DataTable({
			    "scrollY": "300px",
			    "ordering": false,
			    "paging": false,
			    "searching": false,
			    "info": false,
			    "bScrollInfinite": true,
		        "bScrollCollapse": true,
			});
			if($('.dataTables_scrollHeadInner') != null){
				$('.dataTables_scrollHeadInner').after('<div class="afterDivDataTable"></div>')
			}
		}
		
	});
}
$(document).ready(function(){
	dataTablesFn();
})
function setlinkDelink()
{
	try
	{
	var val=document.getElementById('accountActivity').value;
	//alert(val);
	if(val==null || val=='')
	{
		//alert('1');
		document.getElementById('ajax_field_linkAccounts').checked=false;
		document.getElementById('ajax_field_deLinkAccounts').checked=false;
	}
	
	if(val=='linkAccounts')
		{
		//alert('2');
		document.getElementById('ajax_field_linkAccounts').checked=true;
		
		}
	 if(val=='deLinkAccounts')
		 {
		// alert('3');
		document.getElementById('ajax_field_deLinkAccounts').checked=true;
		 }
	}
	catch (e) {
		// TODO: handle exception
	}
	 
	}
function updateData()
{
	var activityType=document.getElementById('accountActivity').value.length;
	
	var activityAccountNames=document.getElementById('activityAccountNames').textContent.length;
	
	
	
	if(activityType==0)
		{
		//alert('Please select Link or De-link activity type');
		showPopUp2('popupsave3','500','400');
		return;
		}
	if(activityAccountNames==0)
		{
		//alert('Please select accounts to perform activity');
		showPopUp2('popupsave4','500','400');
		return;
		}
	if(activityType>0 && activityAccountNames>0)
		{
		showPopUp2('popupsave2','500','400');
		}
		
}
function getSelectedPolicyKeyAccountManagement(){
	var searchlistSize=Number(document.getElementById('SearchlistSize').value);
	var policyKeys = [];
	for(var i = 0 ; i < searchlistSize ; i++)
	{
		var account = document.getElementById("AccountID_"+i);
		if(account == null)
			break;		
		if(account.checked)
			policyKeys.push(account.value);
	}
	document.getElementById("strPolicyKey").value = policyKeys.join(",");
}

function selectAllAccountManagement(checked){
	var searchlistSize=Number(document.getElementById('SearchlistSize').value);
	for(var i = 0 ; i < searchlistSize ; i++)
	{
		var account = document.getElementById("AccountID_"+i);
		if(account == null)
			break;
		account.checked = checked;
	}
	getSelectedPolicyKeyAccountManagement();
}

function getSelectedOutputColumns()
{
	
	var strOutputcolumn = "",activityAccountNames="";
	for(var i = 0 ; i < 160 ; i++)
	{			
		if(document.getElementById("outputcolumn_"+i) == null)
			break;		
		if(document.getElementById("outputcolumn_"+i) != null && document.getElementById("outputcolumn_"+i).checked) {			
			strOutputcolumn = strOutputcolumn.concat(document.getElementById("outputcolumn_"+i).value).concat(",");
			
		}		
	}	

	var strLength=strOutputcolumn.length;
	if(strLength>0)
		{
		strOutputcolumn=strOutputcolumn.substring(0, strLength - 1);
		
		}
	alert(strOutputcolumn);
	document.getElementById("strOutputcolumn").value = strOutputcolumn;
	
	
}
function getStuffedString(dstID,field)
{
	//alert('hello');
	//var fd = field.id;
	var fd = field;
	var operatingDivisionIds='';
    var operatingDivisionNames='';
    debugger;
    var selected=[];
    $('#'+fd+' :selected').each(function(i, sel){
   	 	
           // var operatingDivisionId=$(sel).val();
           //foo[i] = $(selected).text(); 
         
         if(operatingDivisionIds ==''){
            operatingDivisionIds=$(sel).val();
            operatingDivisionNames= $(sel).text();
         }
         else{
            operatingDivisionIds=operatingDivisionIds+','+$(sel).val();
            operatingDivisionNames=operatingDivisionNames+','+$(sel).text();
         }
        
        });
    	
   
    if(document.getElementById(dstID)!=null)
        document.getElementById(dstID).value= operatingDivisionIds;
}
function setInputValuesForRequote()
{
	getStuffedString('attorneysCount','numberOfAttorneys');
	getStuffedString('aopCodes','aop_search');
	getStuffedString('stateCodes','StateCode_search');

}

function toggleDisplay(id) {
	  var x = document.getElementById(id);
	  
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}
function manageEndorsementTypes()
{
	var  cancellEndorsement=document.getElementById('isShowCancelltionEndorsement').value;
	
//alert(cancellEndorsement);
	if(cancellEndorsement=='N')
	  document.getElementById("ajax_field_endorsementType").disabled = true;
	var  isManualPremiumPolicy=document.getElementById('isManualPremiumPolicy').value;
	if(isManualPremiumPolicy=='Y')
		{
		document.getElementById('errorDivision').style.display = 'Block';
		
		  document.getElementById("ajax_field_endorsementType2").disabled = true;}

}


function calculateBanckruptcyPercentageNEW()
{	
	  
	  var personalBankrupties1=document.getElementById("ajax_field_Percentage_0").value;
	  var personalBankrupties2=document.getElementById("ajax_field_Percentage_1").value;
	  var personalBankrupties3=document.getElementById("ajax_field_Percentage_2").value;
	  var personalBankrupties4=document.getElementById("ajax_field_Percentage_3").value;
	  //var commercialBankruptcies=document.getElementById("ajax_field_commercialBankruptcies").value;
	  if(personalBankrupties1=='' || personalBankrupties1==null)
		  personalBankrupties1=0;
	  if(personalBankrupties2=='' || personalBankrupties2==null)
		  personalBankrupties2=0;
	  if(personalBankrupties3=='' || personalBankrupties3==null)
		  personalBankrupties3=0;
	  if(personalBankrupties4=='' || personalBankrupties4==null)
		  personalBankrupties4=0;
	 
	 // alert(personalBankrupties+'          '+commercialBankruptcies);
			total=parseInt(personalBankrupties1)+parseInt(personalBankrupties2)+parseInt(personalBankrupties3)+parseInt(personalBankrupties4);
			//alert(total);
			document.getElementById("ajax_field_totalPercentageNEW").innerHTML = total;
			if (total == 100) {
				document.getElementById("ajax_field_totalPercentageNEW").style.color = "blue";
			} else {
				document.getElementById("ajax_field_totalPercentageNEW").style.color = "red";
			}
	  
}

function getMonthDiff() {
	
	var d1=document.getElementById("EffectiveDateFrom").value;
	var d2=document.getElementById("EffectiveDateTo").value;
	
	//alert(d1+'   '+d2);
	
	var parts1 =d1.split('/');
	var dateFrom = new Date(parts1[2],parts1[0],parts1[1] ); 
	
	var parts2 =d2.split('/');
	var dateTo = new Date( parts2[2],parts2[0], parts2[1]); 
	
	
	 var monthdiff= dateTo.getMonth() - dateFrom.getMonth() +  (12 * (dateTo.getFullYear() - dateFrom.getFullYear()));
	
	 if(!isNaN(monthdiff))
		 { //alert(monthdiff);
			 if(monthdiff>6)
				 {
				 document.getElementById("downloadDiv").style.display = "none";
				 document.getElementById("effectiveDateDiv").style.display = "block";
				 
				 //alert("Selected effective date range can't be exceed 6 months");
				 
				 
				 }
			 else
				 {
				 document.getElementById("downloadDiv").style.display = "block";
				 document.getElementById("effectiveDateDiv").style.display = "none";
				 }
			}
	 
	 }

function checkUnCheckPaymentDiv(areas_show, areas_hide){
//alert(areas_show);
	for (var i = 0; i < areas_show.length; i++)
	{	
		ge = document.getElementById(areas_show[i]);		
		if(ge){
			//	alert(ge);
			ge.checked = true;
		}
	}
	for (var i = 0; i < areas_hide.length; i++)
	{
		var gee = document.getElementById(areas_hide[i]);		
		if(gee){
			gee.checked = false;
		}
		
	}
}
function showHideInsuranceHistoryQQ() {
	
	try
	{
	var p=getGenericRadioValue("IsFirmHaveLawyersLiabilityInsurance");
	//alert(p);
	if(p=='Y')
	$("#effectiveDateDiv").show();
	else
	$("#effectiveDateDiv").hide();
		
		//document.getElementById("ajax_field_IsFirmHaveLawyersLiabilityInsurance").value = p;
	
	
	loadAppointmentFeeInformation('ProInsPremium');
	}
	catch(e)
	{}
	
}
function loadEmail()
{
	
	try
	{
		if(document.getElementById("StateCode")!=null)
		{
			values=document.getElementById("StateCode").value;
			setSelectedIndexProfile(document.getElementById('StateCode'),values);
		}
	}
	catch(err)
	{}
	
	
}

function setInputValuesForTemplates()
{

	getStuffedString('stateCodes','StateCode_search');
	getStuffedString('DynamicContentNames','DynamicContent_Name');
	

}

function loadTemplate()
{
	//alert("stateCodes");
	try
	{
		if(document.getElementById("stateCodes")!=null)
		{
			values=document.getElementById("stateCodes").value;
			setSelectedIndexProfile(document.getElementById('StateCode_search'),values);
		}
	}
	catch(err)
	{}
	try
	{
		
		if(document.getElementById("DynamicContentNames")!=null)
		{ 	
			values=document.getElementById("DynamicContentNames").value;
			setSelectedIndexProfile(document.getElementById('DynamicContent_Name'),values);
		}
	}
	catch(err)
	{}
	
	
}

function showHideAutomationContent()
{
		document.getElementById('test_Module').style.display="none";
		document.getElementById('test_Module_Acc').style.display="block";
	
}

function showHideAutomationContent2()
{
	document.getElementById('test_Module').style.display="block";
	document.getElementById('test_Module_Acc').style.display="none";
		
	
}


function loadBulkAppData()
{
	//alert("StateCodefilter");
	try
	{
		if(document.getElementById("StateCodefilter")!=null)
		{
			values=document.getElementById("StateCodefilter").value;
			setSelectedIndexProfile(document.getElementById('StateCode_search'),values);
		}
	}
	catch(err)
	{}
}
function setBulkDropdownData()
{
	showSelectedFirstReviewer('StateCodefilter','StateCode_search');
}

function checksBrokeragePolicy(){
	try
	{
		document.getElementById("ajax_field_BHPolicy_path").value=document.getElementById("ajax_field_BPolicy_path").value;
		document.getElementById("ajax_field_BHQuote_path").value=document.getElementById("ajax_field_BQuote_path").value;
		document.getElementById("ajax_field_BHLossRun_path").value=document.getElementById("ajax_field_BLossRun_path").value;
		
		
	
	}catch(e)
	{}
}
function setRerateDropdownData()
{
	showSelectedFirstReviewer('StateCodefilter','WQStateCode_search');
}
function loadRerateData()
{
	//alert(document.getElementById("StateCodefilter").value);
	try
	{
		if(document.getElementById("StateCodefilter")!=null)
		{
			values=document.getElementById("StateCodefilter").value;
			setSelectedIndexProfile(document.getElementById('WQStateCode_search'),values);
		}
	}
	catch(err)
	{}
	
	
}
function showHidePLSetion()
{
	try
	{
	var field1=document.getElementById("ajax_field_haveProfessionalLiabilityInsuranceY").checked;
	var field2=document.getElementById("ajax_field_haveProfessionalLiabilityInsuranceN").checked;
	//alert(field1+'   '+field2);
	if(field1==false && field2==false)
		document.getElementById('PLSetion').style.display="none";
	if(field1==true)
		{
		document.getElementById('PLSetion').style.display="block";
		}
	if(field2==true){
		document.getElementById('PLSetion').style.display="none";
	}
	}
	catch(e)
	{}
}

function calculateTotalPremium()
{

	try
	{
		var premium=document.getElementById("ajax_field_Premium");
		var tax = document.getElementById("ajax_field_PremiumTax");
		var fees=document.getElementById("ajax_field_Fees");
		var totalPremium=0;
		if(premium!=null)
			{
				premium=premium.value.replace(/[&\/\\#,+()$~%'":*?<>{}]/g, '');
				premium=premium!=''?parseFloat(premium):0;
				
			}
		if(tax!=null)
			{
				tax=tax.value.replace(/[&\/\\#,+()$~%'":*?<>{}]/g, '');
				tax=tax!=''?parseFloat(tax):0;
				
			
			}
		if(fees!=null)
			{
			
				fees=fees.value.replace(/[&\/\\#,+()$~%'":*?<>{}]/g, '');
				fees=fees!=''?parseFloat(fees):0;
				
			}
		totalPremium=premium+tax+fees;
		//alert(totalPremium);
		document.getElementById("ajax_field_TotalCalculatedPremium").value=totalPremium;
		setDecimalMasking('ajax_field_TotalCalculatedPremium');
	}
	catch(e7)
	{
		
	}	

}
function updateBrokerStatus(fld)
{
	try
	{
	var policyNumber=document.getElementById(fld).value;
	var statusKey=document.getElementById("ajax_field_StatusKey").value;
	if(policyNumber!=null && statusKey!=null && statusKey!=8)
		document.getElementById("ajax_field_StatusKey").value=6;
	}
	catch(err)
	{}
		
}
function loadBrokeragePage()
{
	
	try
	{
		//readonlyDropdown($("#ajax_field_ProducerCode"));
		var brokerEffectiveDate = document.getElementById("ajax_field_BrokerPolicyEffectiveDate");
		if(brokerEffectiveDate.value==null || brokerEffectiveDate.value=='')
			brokerEffectiveDate.value= getPresentDate();
			
		var lobKey = document.getElementById("ajax_field_LOBKey").value;
		if(lobKey==10)
			document.getElementById('otherLOBDescID').style.display="block";
		else
			document.getElementById('otherLOBDescID').style.display="none";
			
		var docType = document.getElementById("ajax_field_DocumentType").value;
		if(docType=='Other')
			document.getElementById('otherDocDescID').style.display="block";
		else
			document.getElementById('otherDocDescID').style.display="none";
		
		updateBrokerStatus('ajax_field_BrokeredPolicyNumber');
		
	}
	catch(e5)
	{}
	
	try
	{
	
		
		var field1=document.getElementById('ajax_field_isConvertedPolicy');
		if(field1!=null)
			{
			//alert(field1.value);
				if(field1.value=='Y')
					readonlyDropdown($("#ajax_field_LOBKey"));
				else
					removeReadonlyDropdown($("#ajax_field_LOBKey"));
			}
	}
	catch(e4)
	{}
try
{
	
		
	//alert('HI');
	amountMask = new Mask("$#,###", "number");
	amountDecimalMask = new Mask("$#,###.##");
	
	 zipMask = new Mask("#####-####");
	var BPremium = document.getElementById("ajax_field_Premium");
	var BPremiumTax = document.getElementById("ajax_field_PremiumTax");
	var fees=document.getElementById("ajax_field_Fees");
	var TotalCalculatedPremium=document.getElementById("ajax_field_TotalCalculatedPremium");
	var ExpiringPremium=document.getElementById("ajax_field_ExpiringPremium");
	setDecimalMasking('ajax_field_Premium');
	setDecimalMasking('ajax_field_PremiumTax');
	setDecimalMasking('ajax_field_Fees');
	setDecimalMasking('ajax_field_TotalCalculatedPremium');
	
	if (ExpiringPremium != null) {
		ExpiringPremium.value = amountMask.format(ExpiringPremium.value);
		amountMask.attach(ExpiringPremium);
	}
    var zip = document.getElementById("ajax_field_Zip");
    if (zip != null) {
          zip.value = zipMask.format(zip.value);
          zipMask.attach(zip);
    }
   
	phoneMask = new Mask("(###)###-####");
	var wrkphone = document.getElementById("ajax_field_Phone");
	if (wrkphone != null) {
		wrkphone.value = phoneMask.format(wrkphone.value);
		phoneMask.attach(wrkphone);
	}	
	
}
catch(e)
{}
try
{
	var field1=document.getElementById("ajax_field_StatusKey").value;
	//alert(field1);
	if(field1==6)
		{
		document.getElementById('premiumI').style.display="block";
		document.getElementById('premiumNI').style.display="none";
		document.getElementById('comissionI').style.display="block";
		document.getElementById('comissionNI').style.display="none";
		
		}
	else if(field1==3)
		{
		document.getElementById('premiumI').style.display="block";
		document.getElementById('premiumNI').style.display="none";
		document.getElementById('comissionI').style.display="none";
		document.getElementById('comissionNI').style.display="block";
		}
	else
		{
		document.getElementById('premiumI').style.display="none";
		document.getElementById('premiumNI').style.display="block";
		document.getElementById('comissionI').style.display="none";
		document.getElementById('comissionNI').style.display="block";
		}
}
catch(e1)
{}
try
{
	var stateCode=document.getElementById("ajax_field_StatusKey").value;
	//alert(stateCode);
	if(stateCode=='TX' || stateCode=='NJ' || stateCode=='WV')
		{
		document.getElementById('PremiumTaxI').style.display="block";
		document.getElementById('PremiumTaxNI').style.display="none";
		
		
		}
	else
		{
		document.getElementById('PremiumTaxI').style.display="none";
		document.getElementById('PremiumTaxNI').style.display="block";
		
		}
	
}
catch(e2)
{}
try
{
	setPercentageFormat('ajax_field_Commission');
	
}
catch(e11)
{}
setBrokerPolicyDates('ajax_field_PLEffectiveDate','ajax_field_PLExpirationDate');
showHideInsuranceHistoryBrokerage();
showHideExpiringInfoBrokerage();
setBrokerPolicyDates('ajax_field_BrokerPolicyEffectiveDate','ajax_field_BrokerPolicyExpirationDate');
}
function showHideOther()
{
	try
	{
		var lobKey = document.getElementById("ajax_field_LOBKey").value;
		if(lobKey==10)
			document.getElementById('otherLOBDescID').style.display="block";
		else
			document.getElementById('otherLOBDescID').style.display="none";
	}catch(e)
	{}
	
	try
	{
		var docType = document.getElementById("ajax_field_DocumentType").value;
		if(docType=='Other')
			document.getElementById('otherDocDescID').style.display="block";
		else
			document.getElementById('otherDocDescID').style.display="none";
	}catch(e)
	{}
	
	showHideInsuranceHistoryBrokerage();	
} 
function setworkq()
{
	var field=document.getElementById('setBrokerSearch').checked;
	if(field==true)
	{
		document.getElementById('WQIsBrokered_search').value='Y';
		document.getElementById('setBrokerSearch').value='Y';
		
		}
	else
	{
		document.getElementById('WQIsBrokered_search').value='N';
		document.getElementById('setBrokerSearch').value='N';
	}
		
	//	alert(document.getElementById('WQIsBrokered_search').value);
}
function loadCComments()
{
	document.getElementById('ajax_field_CComents').value=document.getElementById('ajax_field_ContractComments').value;
	}
function setContractComments()
{
//alert('hi');	
document.getElementById('ajax_field_ContractComments').value=document.getElementById('ajax_field_CComents').value;
}
/*function setBrokerPolicyDates()
{
	try
	{
		var BrokerPolicyEffectiveDate= document.getElementById("ajax_field_BrokerPolicyEffectiveDate");
		var brokerPolicyExpirationDate= document.getElementById("ajax_field_BrokerPolicyExpirationDate");
		
		if(BrokerPolicyEffectiveDate !=null)
			{
			
			var today=null;
			if(BrokerPolicyEffectiveDate.value=='')
				today = new Date();
			else
				today=new Date(BrokerPolicyEffectiveDate.value);
				
			//alert(today);
			
			var dd = String(today.getDate()).padStart(2, '0');
			var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
			var yyyy = today.getFullYear();
			var expYear=parseInt(yyyy)+1;
			today = mm + '/' + dd + '/' + yyyy;
			var expiryDate=mm + '/' + dd + '/' + expYear;
			document.getElementById("ajax_field_BrokerPolicyEffectiveDate").value=today;
				if(brokerPolicyExpirationDate ==null || brokerPolicyExpirationDate.value=='')
				{
					alert('Hello');
					document.getElementById("ajax_field_BrokerPolicyExpirationDate").value=expiryDate;
				}
			}
		
	}
	catch(e10)
	{
		
	}	
}*/

function setPercentageFormat(id)
{
	try
	{
		percentageMask = new Mask("##.###");
		var data=document.getElementById(id);
		
		if (data != null) {
			var comission=data.value;
				comission=comission.substring(0, comission.length);
				comission = percentageMask.format(comission);
			percentageMask.attach(data);
			if(comission.length>0 && !comission.includes('%'))
				data.value=comission+'%'
				//data.value='12';
			alert(Commission.value);
		}	
	}
	catch(e)
	{}
}
function manageLOB(id)
{
	try
	{
		var field=document.getElementById(id);
		if(field!=null)
		{	
			if(field.value!=null && field.value!='')
				showPopUp('popupsave1','500','400');
		
		}
	}
	catch(e)
	{}
}


function showHideBrokerSetion()
{
	try
	{
		var field1=$('input[name="isBrokered"]:checked').val();
		//alert(field1);
		if(field1 == 'N') {
			$("*.Brokerage_security").hide();
			
		} else if(field1 == 'Y') {
			$("*.Brokerage_security").show();
			
		}
	
	}
	catch(e)
	{}
	
}
function setBrokerPolicyDates(field1,field2)
{
	try
	{
		
		var effectiveDate= document.getElementById(field1);
		var policyExpiryDate= document.getElementById(field2).value;
		if(effectiveDate !=null)
			{
				//alert(effectiveDate);
				var today=null;
				/*if(effectiveDate.value=='')
					today = new Date();
				*/	
				if(effectiveDate.value!='')
					{
						today=new Date(effectiveDate.value);
						
						//alert(today);
						//alert(policyExpiryDate);
						var dd = String(today.getDate()).padStart(2, '0');
						var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
						var yyyy = today.getFullYear();
						var expYear=parseInt(yyyy)+1;
						today = mm + '/' + dd + '/' + yyyy;
						var expiryDate=mm + '/' + dd + '/' + expYear;
						document.getElementById(field1).value=today;
						if(policyExpiryDate=='')
							document.getElementById(field2).value=expiryDate;
					}
			}
		
	}
	catch(e10)
	{
		
	}	
}

function showHideInsuranceHistoryBrokerage()
{
	try
	{
		//alert('hi');
		var field1=document.getElementById('isNewBrokerPolicy').value;
		//alert(field1);
		var field2=document.getElementById('ajax_field_RenewAsBrokerage').value;
		//alert(field2);
		var lob = document.getElementById("ajax_field_LOBKey").value;
		
		//alert(lob);
		//alert('isNewBrokerPolicy :'+field1+'  RenewAsBrokerage'+field2+' LOB :'+lob+' TransactionTypeKey :'+field);
		if(lob==2)
			document.getElementById('priorInsuranceDiv').style.display="none";
		else if(field1=='Y' && field2=='N')
			document.getElementById('priorInsuranceDiv').style.display="none";
		else if(document.getElementById('AccountID')!=null)
			{
			var accountID=document.getElementById('AccountID').value;
			//alert(accountID);
			if(accountID!=null && accountID!='')
				document.getElementById('priorInsuranceDiv').style.display="none";
			else
				document.getElementById('priorInsuranceDiv').style.display="block";
			}
		else
			document.getElementById('priorInsuranceDiv').style.display="block";
	}
	catch(e)
	{}
		
}

function showHideExpiringInfoBrokerage()
{
try
{
	var field=document.getElementById('ajax_field_TransactionTypeKey').value;
	if(field==1)
		document.getElementById('expiringInfoDiv').style.display="none";
	 else
		 document.getElementById('expiringInfoDiv').style.display="block";
		 
	}
catch(e)
{}
}

function setBrokerage()
{
	try
	{
		var isBrokerageDoc=document.getElementById('isBrokerageDoc');
		if(isBrokerageDoc!=null)
			{
			//alert(isBrokerageDoc.checked);
				if(isBrokerageDoc.checked==true)
					document.getElementById('isBrokerageDocument').value='Y';
				else
					document.getElementById('isBrokerageDocument').value='N';
			}
	}
	catch(e)
	{}
}

function setPercentageFormatInteger(id)
{
	try
	{
	
	if (document.getElementById(id) != null) {
		var percentageAmount=document.getElementById(id).value;
		if(percentageAmount.length>0 && !percentageAmount.includes('%'))
			document.getElementById(id).value=percentageAmount+'%';
		//alert(Commission.value);
	}
	}
	catch(e)
	{}
		
}
function showHideCannibSupp()
{
	 try
	 {
		 var isCannibSuppPage=document.getElementById("ajax_field_isCannibSuppPage");  
		 //alert(isCannibSuppPage.checked);
		 if(isCannibSuppPage.checked==true)
			 {
			 document.getElementById("CannibSupp1").style.display = "table-row";
			 document.getElementById("CannibSupp2").style.display = "table-row";
			 document.getElementById("CannibSupp3").style.display = "table-row";
			 document.getElementById("CannibSupp4").style.display = "table-row";
			 document.getElementById("CannibSupp5").style.display = "table-row";
			 document.getElementById("CannibSupp6").style.display = "table-row";
			 document.getElementById("CannibSupp7").style.display = "table-row";
			 document.getElementById("CannibSupp8").style.display = "table-row";
			 document.getElementById("CannibSupp9").style.display = "table-row";
			 document.getElementById("CannibSupp10").style.display = "table-row";
			 }
		 else
			 {
			 document.getElementById("CannibSupp1").style.display = "none";
			 document.getElementById("CannibSupp2").style.display = "none";
			 document.getElementById("CannibSupp3").style.display = "none";
			 document.getElementById("CannibSupp4").style.display = "none";
			 document.getElementById("CannibSupp5").style.display = "none";
			 document.getElementById("CannibSupp6").style.display = "none";
			 document.getElementById("CannibSupp7").style.display = "none";
			 document.getElementById("CannibSupp8").style.display = "none";
			 document.getElementById("CannibSupp9").style.display = "none";
			 document.getElementById("CannibSupp10").style.display = "none";
			 document.getElementById("CannibsOtherDescDiv").style.display = "none";
			 }
		
		 
		 showHideCannibsOtherDesc();
	 }
	 catch(e1)
	 {}	
}
function showHideCannibsOtherDesc()
{
	 try
	{
		
		 var CannibsOther=document.getElementById("ajax_field_CannibsOther");  
		 var isCannibSuppPage=document.getElementById("ajax_field_isCannibSuppPage");  
		
	
			 if(CannibsOther.checked==true && isCannibSuppPage.checked==true)
				 document.getElementById("CannibsOtherDescDiv").style.display = "table-row";
			 else
				 document.getElementById("CannibsOtherDescDiv").style.display = "none";
		
	}
	catch(e)
	{}
}

function loadCCBPage()
{
	try
	{
		 var OtherLegalCorporateServices=document.getElementById("ajax_field_OtherLegalCorporateServices");  
		 if(OtherLegalCorporateServices.checked==true)
			 {
			 	document.getElementById("corpServDescDiv1").style.display =  "table-row";
			 	document.getElementById("corpServDescDiv2").style.display = "table-row";
			 }
		 else
			{
			 	document.getElementById("corpServDescDiv1").style.display = "none";
			 	document.getElementById("corpServDescDiv2").style.display = "none";			 
			}
		 
		 var publiclyRenderedServices=document.getElementById("ajax_field_PubliclyRenderedServices");  
		 //alert(isCannibSuppPage.checked);
		 if(publiclyRenderedServices.checked==true)
			document.getElementById("applicantProvidesLegalServicesDescDiv").style.display = "table-row";
		else
			document.getElementById("applicantProvidesLegalServicesDescDiv").style.display = "none";
			 
	}catch(e10)
	{}
	try
	{
		 var legalBusinessRelationshipServices=document.getElementById("ajax_field_LegalBusinessRelationshipServices");  
		 var authorityToDisburseFunds=document.getElementById("ajax_field_AuthorityToDisburseFunds");  
		 var acceptCompensation=document.getElementById("ajax_field_AcceptCompensation");  
		 var legalServicesSecuritiesPayment=document.getElementById("ajax_field_LegalServicesSecuritiesPayment");  
		 var performDueDiligence=document.getElementById("ajax_field_PerformDueDiligence");  
		 
		 if(legalBusinessRelationshipServices.checked==true || authorityToDisburseFunds.checked==true || acceptCompensation.checked==true 
				 || legalServicesSecuritiesPayment.checked==true || performDueDiligence.checked==true)
			 {
			
				document.getElementById("DetailedDescCorporateServicesDiv").style.display = "table-row";
				document.getElementById("DetailedDescCorporateServicesDiv2").style.display = "table-row";
			 }
			else
				{
				
				document.getElementById("DetailedDescCorporateServicesDiv").style.display = "none";
				document.getElementById("DetailedDescCorporateServicesDiv2").style.display = "none";
				}
	}
	catch(e1)
	{}
}

function setDecimalMasking(fld)
{
	
	var fldValue=document.getElementById(fld).value;
	fldValue=fldValue.replace(/[^\d.-]/g, '');
	if(fldValue!='')
		{
			const formatter = new Intl.NumberFormat('en-US', {
		  style: 'currency',
		  currency: 'USD',
		  minimumFractionDigits: 2
		
		})
		fldValue=formatter.format(parseFloat(fldValue).toFixed(2));
		//alert(fldValue);
		document.getElementById(fld).value=fldValue;
	}
	else
		document.getElementById(fld).value=fldValue;
}
function getPresentDate()
{
	var today = new Date();
	var dd = today.getDate();

	var mm = today.getMonth()+1; 
	var yyyy = today.getFullYear();
	if(dd<10) 
	    dd='0'+dd;
	if(mm<10) 
	   mm='0'+mm;
		
	today = mm+'/'+dd+'/'+yyyy;
	
	return today;
	
}

function loadBrokerlkulookup()
{
	/*try
	{
	 var numberMask = new Mask("###", "number");
	 var nbCommissionPercentage = document.getElementById("nbCommissionPercentage");
	 numberMask.attach(nbCommissionPercentage);
	 var rbCommissionPercentage = document.getElementById("rbCommissionPercentage");
	 numberMask.attach(rbCommissionPercentage);
}
	catch(e1)
	{}*/
	try
	{
		setPercentageFormat('nbCommissionPercentage');
		setPercentageFormat('rbCommissionPercentage');
		
	}
	catch(e2)
	{}
}
function confirmDelete(name){
	//alert('hi');
	var x = confirm("Are you sure you want to delete this "+name+" ? ");
	return x ;
	
}
function loadCompany()
{
	try
	{
		//alert('hi');
	var fldValue=document.getElementById('CompanyAsPerState').value;
	//alert(fldValue);	
	if(fldValue!=null && fldValue!='')
	{
			document.getElementById('ajax_field_CompanyKey').value=fldValue;
	
		}
	}
	catch(e1)
	{}
}

function loadWorkq()
{
	try
	{
		
	var field=document.getElementById('WQIsBrokered_search').value;
	if(field=='Y')
		document.getElementById('setBrokerSearch').checked=true;
	else
		document.getElementById('setBrokerSearch').checked=false;
			
		//alert(field);
	}
	catch(error)
	{}
	
}
function setPaymentModeType()
{
	try {
		var field = document.getElementById('PaymentMode').value;
		//otherIPFSDiv
		//alert(field);
		if (field != '') {
			if (field == 'paypal' || field == 'ach') {
				document.getElementById("otherPaymentButtonDiv").style.display = "none";
				document.getElementById("EFTPaymentButtonDiv").style.display = "none";
				document.getElementById("otherIPFSDiv").style.display = "none";
				document.getElementById("CC_ACHPaymentDiv").style.display = "table-row";
			} else if (field == 'finance') {
				document.getElementById("otherPaymentButtonDiv").style.display = "none";
				document.getElementById("EFTPaymentButtonDiv").style.display = "none";
				document.getElementById("otherIPFSDiv").style.display = "table-row";
				document.getElementById("CC_ACHPaymentDiv").style.display = "none";
			} else if (field == 'eft') {
				document.getElementById("otherPaymentButtonDiv").style.display = "none";
				document.getElementById("EFTPaymentButtonDiv").style.display = "table-row";
				document.getElementById("otherIPFSDiv").style.display = "none";
				document.getElementById("CC_ACHPaymentDiv").style.display = "none";
			} else  {
				document.getElementById("otherPaymentButtonDiv").style.display = "table-row";
				document.getElementById("EFTPaymentButtonDiv").style.display = "none";
				document.getElementById("otherIPFSDiv").style.display = "none";
				document.getElementById("CC_ACHPaymentDiv").style.display = "none";
			}
		}
		else {
			document.getElementById("otherPaymentButtonDiv").style.display = "none";
			document.getElementById("EFTPaymentButtonDiv").style.display = "none";
			document.getElementById("otherIPFSDiv").style.display = "none";
			document.getElementById("CC_ACHPaymentDiv").style.display = "none";
		}
	}
	catch (err) { }
}

function loadBankruptcy(fieldPrefix)
{
	
	 //alert(fieldPrefix);
	
	 var formObject = document.forms[0];
	
		for ( var i = 0; i < formObject.length; i++) {
	
			var n = formObject.elements[i].name;
			if (n != null) {
	
				if (n.indexOf(fieldPrefix) > -1) {
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_"+fieldPrefix+ index;
					
					if(fieldPrefix=='AverageCaseValue_')
							setAmoutFormat(fld);
					
					
			}
		}

		}
	
}

function policyDocumentsDeliveryOption(name){
	
	if($("input[name='" + name + "']:checked").val() == "Y"){
		
		if(name == "isElectronicInsurance"){
			$("#isElectronicAndPaperDeliveryN").prop("checked", true);
			$("#isElectronicDeliveryRejectionN").prop("checked", true);
			
			//if(document.getElementById("isElectronicDeliveryWithdrawalConsentN") != null)
				//$("#isElectronicDeliveryWithdrawalConsentN").prop("checked", true);
				
		} else if(name == "isElectronicAndPaperDelivery"){
			$("#isElectronicInsuranceN").prop("checked", true);
			$("#isElectronicDeliveryRejectionN").prop("checked", true);
			
			//if(document.getElementById("isElectronicDeliveryWithdrawalConsentN") != null)
				//$("#isElectronicDeliveryWithdrawalConsentN").prop("checked", true);
				
		} else if(name == "isElectronicDeliveryRejection"){
			$("#isElectronicInsuranceN").prop("checked", true);
			$("#isElectronicAndPaperDeliveryN").prop("checked", true);
			
			//if(document.getElementById("isElectronicDeliveryWithdrawalConsentN") != null)
				//$("#isElectronicDeliveryWithdrawalConsentN").prop("checked", true);
				
		//} else if(name == "isElectronicDeliveryWithdrawalConsent"){
			//$("#isElectronicInsuranceN").prop("checked", true);
			//$("#isElectronicAndPaperDeliveryN").prop("checked", true);
			//$("#isElectronicDeliveryRejectionN").prop("checked", true);
		}
	
		
		//document.getElementById("input[name='" + name + "'][value='Y']").checked=true;
		
	}
}

function showHidePriorInsuranceDiv()
{
	try
	{
		
		var field1=document.getElementById('ClaimAge_1').value;
		
		if(field1==null || field1=="")
			field1="0";
		
		var field2=document.getElementById('ClaimAge_2').value;
		if(field2==null || field2=="")
			field2="0";
	
		var field3=document.getElementById('ClaimAge_3').value;
		if(field3==null || field3=="")
			field3="0";
		
		var field4=document.getElementById('ClaimAge_4').value;
		if(field4==null || field4=="")
			field4="0";
		
		var field5=document.getElementById('ClaimAge_5').value;
		if(field5==null || field5=="")
			field5="0";
		
		if(field1!="0" || field2!="0" || field3!="0" || field4!="0" || field5!="0")
			document.getElementById("priorInsuranceDiv").style.display = "block";
		else
			document.getElementById("priorInsuranceDiv").style.display = "none";
	}
	catch(e)
	{
		
	}
	
} 
function resetFields()
{
	/*alert('hi');
	document.getElementById("ajax_field_limitEndorsementEffectiveDate").value=null;
	document.getElementById("ajax_field_IsClaimExpenseType_N").checked=false;
	document.getElementById("ajax_field_IsClaimExpenseType_Y").checked=false;
	document.getElementById("ajax_field_IsDollarDefense").checked=false;
	document.getElementById("ajax_field_deductibleEndorsementEffectiveDate").value=null;
	document.getElementById("ajax_field_IsClaimOptionType1").value=null;
	document.getElementById("ajax_field_DeductibleSequence").value=null;
	document.getElementById("XmlOutputDatafromRating").value=null;
	document.getElementById("ajax_field_deductibleEndorsementPremiumNew").value=null;
	document.getElementById("ajax_field_litmitsEndorsementPremiumNew").value=null;
	document.getElementById("ajax_field_litmitsEndorsementTaxNew").value=null;
	document.getElementById("ajax_field_litmitsEndorsementTotalPremiumNew").value=null;
	document.getElementById("XmlOutputDatafromRating").value=null;
	document.getElementById("ajax_field_LimitSequence").value=null;*/
	
}

function onSelectUploadInsuedFile(obj){
	$("#path").val($(obj).val())
}

function accountManagement()
{
	try
	{
		
	var field=document.getElementById('WQIsBrokered_search').value;
	if(field=='Y')
		document.getElementById('setBrokerSearch').checked=true;
	else
		document.getElementById('setBrokerSearch').checked=false;
			
		//alert(field);
	}
	catch(error)
	{}
	
}
function resetSerachFormBrokerage() {
	
	if (document.getElementById("BRAccountNameSearch") != null)
		document.getElementById("BRAccountNameSearch").value = '';
		
	if (document.getElementById("BRPolicyEffectiveDate_search") != null)
		document.getElementById("BRPolicyEffectiveDate_search").value = '';
	
	if (document.getElementById("BRPolicyEffectiveDate_searchTo") != null)
		document.getElementById("BRPolicyEffectiveDate_searchTo").value = '';
	
	if (document.getElementById("BRStateCode_search") != null)
		document.getElementById("BRStateCode_search").value = '';
	
	if (document.getElementById("BRStatusKey_search") != null)
		document.getElementById("BRStatusKey_search").value = '';
			
	if (document.getElementById("BRPolicyNumberSearch") != null)
		document.getElementById("BRPolicyNumberSearch").value = '';
	
	
	
}
function loadAttorneyEstimateForm()
 {
	  
	 var numberMask = new Mask("###", "number");
	 var formObject = document.forms[0];
		for ( var i = 0; i < formObject.length; i++) {
	
			var n = formObject.elements[i].name;
			if (n != null) {
	
				if (n.indexOf("AmountOfFeesSued_") > -1) {
					var index = n.substring(n.lastIndexOf("_") + 1, n.length);
					var fld = "ajax_field_AmountOfFeesSued_"+ index;
					var amount=document.getElementById(fld);
					
					numberMask.attach(amount);
					if (amount.value == '0')
						amount.value = '';
					
					loadAppointmentFeeInformation(fld);
				}
				
			}
			
		}
		
	  
  }
 loadProtexureForm()
{

try{
  zipMask = new Mask("#####-####");
    var zip = document.getElementById("ajax_field_Zip");
    if (zip != null) {
          zip.value = zipMask.format(zip.value);
          zipMask.attach(zip);
    }
   
	phoneMask = new Mask("(###)###-####");
	var wrkphone = document.getElementById("ajax_field_WorkPhone");
	if (wrkphone != null) {
		wrkphone.value = phoneMask.format(wrkphone.value);
		phoneMask.attach(wrkphone);
	}	
	}
	catch(e)
	{}
	try
	{
var val=document.getElementById('AOP_Percentage_25').value;
if(val>0)
	document.getElementById("otherDescId").style.display = "table-row";
else
document.getElementById("otherDescId").style.display = "none";
}
catch(e){}

}


function maskFieldToNumber(fld)
{
	try
	{
	var numberMask = new Mask("###", "number");
	var feeMask = new Mask("$#,###.##", "number")
	 var fieldValue = document.getElementById(fld);
	 if(fieldValue!=null)
	 {
	 numberMask.attach(fieldValue);
	// feeMask.attach(fieldValue);
	 }
	 }
	 catch(eror)
	 {}
}

function fillLimitOfLiabilityIndication(value) {
	/*var prosslimit = value;	
		var limit = '';
		if (document.getElementById('ajax_field_LimitSequence') != null){
			limit = document.forms[0].LimitSequence.value;	
		}
		var stateCode = '';
		if (document.getElementById('StateCode') != null){
			stateCode = document.forms[0].StateCode.value;	
		}	
	*/
	if (document.getElementById('LimitSequence') != null) 
			document.getElementById('LimitSequence').value = value;	
	
}

function fillDeductibleIndication(value) {	
	
	if (document.getElementById('DeductibleSequence') != null) {
		document.getElementById('DeductibleSequence').value = value;
	}
}

function onPageLoadEstimateForm(){
	if($.trim(document.getElementById("AOP_values").value) !== ""){
		var selectedItems = $.trim(document.getElementById("AOP_values").value).split(",");
		for(var i = 0; i < selectedItems.length; i++){
			if(selectedItems[i] != 0 && selectedItems[i] != 24 && selectedItems[i] != 62 && selectedItems[i] != 35 && selectedItems[i] != 8 && selectedItems[i] != 5 && selectedItems[i] != 27 && selectedItems[i] != 20 && selectedItems[i] != 3){
				$("#aoplist_list_1").find("table tbody").find("input[id=AOPKey][value=" + selectedItems[i] + "]")[0].checked = true;
			}
		}
		addAOP();
	}
	var aopPercentageValue = $.trim(document.getElementById("aopPercentageValue").value);
	var aopPercentageTRValue = $.trim(document.getElementById("aopPercentageTRValue").value);
	if(aopPercentageTRValue !== ""){
		aopPercentageValue = aopPercentageValue.split(",");
		aopPercentageTRValue = aopPercentageTRValue.split(",");
		for(var k = 0; k < aopPercentageTRValue.length; k++){
			$("#rightaoplist_list").find("tr[id=" + aopPercentageTRValue[k] + "]").find("td:eq(1)").find("input").val(aopPercentageValue[k]);
			$("#rightaopstatic_list").find("tr[id=" + aopPercentageTRValue[k] + "]").find("td:eq(1)").find("input").val(aopPercentageValue[k]);
		}
//		populateTotalFieldValue('AOP_Percentage_','TotalAOP_Percentage');
	}

//	var aopTabIndex = 90;
//	for(var aopListCount = 0; aopListCount<24; aopListCount++){
//		aopTabIndex += 10;
//		$("#leftAOPList").find("tbody").find("tr:eq(" + aopListCount + ")").find("input[id=AOPKey][name=AOPKey]").attr("tabindex",aopTabIndex);
//	}
}

function showSpecifiedAttorneyDiv(id)
{
	var x=id;
	
	if(x=='Y')
		document.getElementById("specifiedAttorneyDiv").style.display = "block";
	else
		document.getElementById("specifiedAttorneyDiv").style.display = "none";
	
}
function loadAdditionalFormPage()
{
	var isSpecifiedAttorneyCareerEndorsement=getGenericRadioValue("IsSpecifiedAttorneyCareerEndorsement");
	 if(isSpecifiedAttorneyCareerEndorsement=='Y')
			document.getElementById("specifiedAttorneyDiv").style.display = "block";
		else
			document.getElementById("specifiedAttorneyDiv").style.display = "none";	
			
	var isTitleAgencyEndorsementValue=getGenericRadioValue("IsTitleAgencyEndorsement");
	var isProfessionalServicesExclusionEndorsementValue=getGenericRadioValue("IsProfessionalServicesExclusionEndorsement");
	 	if(isTitleAgencyEndorsementValue=='Y')
			document.getElementById("tileAgencyNameRow").style.display = "table-row";
		else
			document.getElementById("tileAgencyNameRow").style.display = "none";
	 
	 if(isProfessionalServicesExclusionEndorsementValue=='Y')
			document.getElementById("specificProfessionalServicesArea").style.display = "table-row";
		else
			document.getElementById("specificProfessionalServicesArea").style.display = "none";	
	

	
	
}
