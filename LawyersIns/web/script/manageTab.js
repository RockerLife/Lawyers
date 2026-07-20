$(function() {
	try{
//	aboutYouSubTab
	var aboutYouSubContentTabList = ["FirmNewApp_freeform_contextdata_100","insuranceHistory_freeform_contextdata_100"];
	var aboutYouSubTabList = ["firmNewAppPage","insuranceHistoryPage"];
	var aboutYouSubTabValues = ["Will hold Firm Name","Insurance History/Coverage"];
	for(var aboutYouContentTabCount = 0; aboutYouContentTabCount < aboutYouSubContentTabList.length; aboutYouContentTabCount++){
		if($("#contentSection").find("div").attr("id") === aboutYouSubContentTabList[aboutYouContentTabCount]){
			$("#aboutYouSubTab").find("a:eq(" + aboutYouContentTabCount + ")").addClass("active2");
		} else {
			$("#aboutYouSubTab").find("a:eq(" + aboutYouContentTabCount + ")").removeClass("active2");
		}
	}
	
	for(var aboutYouTabCount = 0; aboutYouTabCount < aboutYouSubTabList.length; aboutYouTabCount++){
		if($.trim($("#" + aboutYouSubTabList[aboutYouTabCount]).val()) === 'N'){
			var tabValue = $.trim($("#aboutYouSubTab").find("a:eq(" + aboutYouTabCount + ")").text());
			if(tabValue == aboutYouSubTabValues[aboutYouTabCount]){
				$("#aboutYouSubTab").find("a:eq(" + aboutYouTabCount + ")").html("<i class=\"fa-check fa green2\">&nbsp;</i>" + tabValue);
			}
		}
	}
	
	
//	supplementsSubTab
	var supplementsSubTabCount = 0;
	var supplementsSubTabList = ["bankruptcySuppPage","collectionSuppPage","copyrightSuppPage","ccbSuppPage","familyLawSuppPage","financialSuppPage","governmentSuppPage","litigationSuppPage","realEstateSuppPage","willStateSuppTrustPage","taxSuppPage"];
	var supplementsSubTabValues = ["Bankruptcy","Collection","Copyright Trademark","Corporate Commercial Business","Family Law","Financial Institution","Government","Plaintiff Litigation","Real Estate","Wills Trust Probate","Tax"];
	var supplementsSubContentTabList = ["bankruptcy_freeform_contextdata_1100","CollectionSupplement_freeform_contextdata_1100","copyRightSupplement_freeform_contextdata_1100","ccbSupplement_freeform_contextdata_1100",
	                                    "familyLaw_freeform_contextdata_100","financialInstitutionSupplement_freeform_contextdata_100","GovenrmentSupplement_freeform_contextdata_1100",
	                                    "litigationSupplement_freeform_contextdata_1100","RealEstateSupplement_freeform_contextdata_1100","WillsTrustProbationSupplement_freeform_contextdata_1100",
	                                    "taxSupplement_freeform_contextdata_1100"];
	$("#supplementsSubTab").find("a").removeClass("active2");
	loop1:
	for(var supplementContentTabCount = 0; supplementContentTabCount< supplementsSubContentTabList.length; supplementContentTabCount++){
		if($("#contentSection").find("div").attr("id") === supplementsSubContentTabList[supplementContentTabCount]){
			var tabSize = $("#supplementsSubTab").find("a").length;
			for(var subTabCount = 0; subTabCount < tabSize; subTabCount++){
				var tabValue = $.trim($("#supplementsSubTab").find("a:eq(" + subTabCount + ")").text());
				if(tabValue == supplementsSubTabValues[supplementContentTabCount]){
					$("#supplementsSubTab").find("a:eq(" + subTabCount + ")").addClass("active2");
					 break loop1;
				}
			}
		}
	}
	
	for(var supplementTabCount = 0; supplementTabCount < supplementsSubTabList.length; supplementTabCount++){
		if($.trim($("#" + supplementsSubTabList[supplementTabCount]).val()) === 'N'){
			var tabSize = $("#supplementsSubTab").find("a").length;
			for(var subTabCount = 0; subTabCount < tabSize; subTabCount++){
				var tabValue = $.trim($("#supplementsSubTab").find("a:eq(" + subTabCount + ")").text());
				if(tabValue == supplementsSubTabValues[supplementTabCount]){
					$("#supplementsSubTab").find("a:eq(" + subTabCount + ")").html("<i class=\"fa-check fa green2\">&nbsp;</i>" + tabValue);
				}
			}
		}
	}
	
	
//	firmInformationSubTab
	var firmInformationSubTabList = ["aboutFirmPage","practiceManagementPage","morePracticeManagementPage","underWritingInfoPage","isElectronicInsurancePage","pastClaimsPage"];
	var firmInformationSubTabValues = ["About Your Firm","Practice Management","More Practice Management","Underwriting Information","Electronic Selection / Rejection","Past Claims"];
	var firmInformationSubContentTabList = ["aboutFirm_freeform_contextdata_1100","practiceManagement_freeform_contextdata_1100",
	                                    "morePracticeManagement_freeform_contextdata_1100","underwritingInfo_freeform_contextdata_100",
	                                    "electronic_freeform_contextdata_1100","pastClaims_freeform_contextdata_1100"];
	
	$("#firmInformationSubTab").find("a").removeClass("active2");
	loop1:
	for(var firmInformationContentTabCount = 0; firmInformationContentTabCount< firmInformationSubContentTabList.length; firmInformationContentTabCount++){
		if($("#PolicyStatusKey").val() == 2 && firmInformationSubTabValues[firmInformationContentTabCount] == "About Your Firm"){
			firmInformationSubContentTabList[firmInformationContentTabCount] = "aboutFirmRenewal_freeform_contextdata_1100";
		}
		if($("#contentSection").find("div").attr("id") === firmInformationSubContentTabList[firmInformationContentTabCount]){
			var tabSize = $("#firmInformationSubTab").find("a").length;
			for(var subTabCount = 0; subTabCount < tabSize; subTabCount++){
				var tabValue = $.trim($("#firmInformationSubTab").find("a:eq(" + subTabCount + ")").text());
				if(firmInformationSubTabValues[firmInformationContentTabCount] === "Past Claims"){
					if(firmInformationSubTabValues[firmInformationContentTabCount].indexOf(tabValue) > -1){
						$("#firmInformationSubTab").find("a:eq(" + subTabCount + ")").addClass("active2");
						 break loop1;
					}
				} else if(firmInformationSubTabValues[firmInformationContentTabCount] === tabValue){
					$("#firmInformationSubTab").find("a:eq(" + subTabCount + ")").addClass("active2");
					 break loop1;
				}
			}
		}
	}
	
	for(var firmInformationTabCount = 0; firmInformationTabCount < firmInformationSubTabList.length; firmInformationTabCount++){
		if($("#PolicyStatusKey").val() == 2 && firmInformationSubTabValues[firmInformationTabCount] == "About Your Firm"){
			firmInformationSubTabList[firmInformationTabCount] = "aboutFirmRenewalPage";
		}
		if($.trim($("#" + firmInformationSubTabList[firmInformationTabCount]).val()) === 'N'){
			var tabSize = $("#firmInformationSubTab").find("a").length;
			for(var subTabCount = 0; subTabCount < tabSize; subTabCount++){
				var tabValue = $.trim($("#firmInformationSubTab").find("a:eq(" + subTabCount + ")").text());
				if(firmInformationSubTabValues[firmInformationTabCount] === "Past Claims"){
					if(firmInformationSubTabValues[firmInformationTabCount].indexOf(tabValue) > -1){
						$("#firmInformationSubTab").find("a:eq(" + subTabCount + ")").html("<i class=\"fa-check fa green2\">&nbsp;</i>" + tabValue);
					}
				} else if(firmInformationSubTabValues[firmInformationTabCount] === tabValue){
					$("#firmInformationSubTab").find("a:eq(" + subTabCount + ")").html("<i class=\"fa-check fa green2\">&nbsp;</i>" + tabValue);
				}
			}
		}
	}
	}catch (e) {
		
	}
});