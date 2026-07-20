$(document).ready(function() {
	try {
		$("*.addCommas").keyup(function() {
			var nStr = $(this).val();
			nStr += '';
			var x = nStr.split('.');
			var x1 = x[0];
			var x2 = x.length > 1 ? '.' + x[1] : '';
			var rgx = /(\d{3})(\d{3})/;
			while (rgx.test(x1)) {
				x1 = x1.replace(rgx, '$1' + ',' + '$2');
			}
			$("#" + this.id).val(x1 + x2);
		});
		$("*.addCommas").trigger("keyup");
	} catch (e) {
		console.log("Error in addCommas function : " + e);
	}
	try{
		$("*.accordion").accordion({
			header : "h3",
			collapsible : true,
			active : false
		});
	}catch (e) {
		console.log("Error in accordion function : " + e);
	}
});
function IsNumeric(obj, event, allowDecimal, allowNegative, allowCharacter) {
	var key;
	var isCtrl = false;
	var keychar;
	var reg;
	var typeCharacterOlnyNumSymbolsMsg = " ";

	if (window.event) {
		key = event.keyCode;
		isCtrl = window.event.ctrlKey;
	} else if (event.which) {
		key = event.which;
		isCtrl = event.ctrlKey;
	}

	if (isNaN(key))
		return true;

	keychar = String.fromCharCode(key);

	// check for backspace or delete, or if Ctrl was pressed
	if (key === 8 || isCtrl) {
		return true;
	}
	if (allowCharacter !== "") {
		for (var allowCharacterCount = 0; allowCharacterCount < allowCharacter.length; allowCharacterCount++) {
			// alert(allowCharacter[allowCharacterCount]);
			if (keychar === allowCharacter[allowCharacterCount]) {
				return true;
			}
		}
	}

	reg = /^\s*[0-9]+\s*$/;
	return reg.test(keychar);
}

function IsFloat(myfield, event, decimalLength)
{

	//alert("is Float "+decimalLength);
    var str = myfield.value;
//    alert(str);
    var decPos = str.split('.');
    var fldDecimalIndex=str.indexOf(".");

    var key;
    var keychar;

    if (window.event)
        key = window.event.keyCode;
    else if (event)
        key = event.which;
    else
        return true;

    keychar = String.fromCharCode(key);
//    alert(key);

    // control keys
    if ((key == null) || (key === 0) || (key === 8) || (key === 9) || (key === 13) || (key === 27) || (key === 45)) {
        return true;
    } else if (key === 32) {
        return false;
    } else if (fldDecimalIndex > -1){
        var selectedValue = $.trim(myfield.value.substring(myfield.selectionStart, myfield.selectionEnd));
        if(selectedValue === ""){
			var subfld=str.substr(fldDecimalIndex+1, str.length);
	        var subfldLength=subfld.length;
	        if(subfldLength == decimalLength){
	            //alert(subfldLength);
	        	return false;
	            //alert(subfldLength);
	        }
        }
    } else if (keychar === ".") { // numbers
        return true;
    }

    reg = /^\s*[0-9]+\s*$/;
    return reg.test(keychar);
}

function IsFloatUp(myfield, event, decimalLength)
{
    var str = myfield.value;
    var key = event.keyCode;

    var decPos = str.split('.');

    if (str.indexOf(".") > -1) {
        if (decPos.length > 1) {
            decPos = decPos[1];
            if (decPos.length > decimalLength) {
                myfield.value = "";
            }
        }
    }
	for (var allowNegative = 1; allowNegative < myfield.value.length; allowNegative++) {
		if ("-" === myfield.value[allowNegative]) {
			myfield.value = "";
		}
	}
}

function IsFloatUpWithoutSymbol(myfield, event, decimalLength)
{
    var str = myfield.value;
    var key = event.keyCode;

    var decPos = str.split('.');

    if (str.indexOf(".") > -1) {
        if (decPos.length > 1) {
            decPos = decPos[1];
            if (decPos.length > decimalLength) {
                myfield.value = "";
            }
        }
    }
}

function allowNegativeIsNumeric(obj, event, allowDecimal, allowNegative, allowCharacter)
{
	var key;
    var isCtrl = false;
    var keychar;
    var reg;

    if (window.event) {
        key = event.keyCode;
        isCtrl = window.event.ctrlKey;
    }
    else if (event.which) {
        key = event.which;
        isCtrl = event.ctrlKey;
    }

    if (isNaN(key))
        return true;

    keychar = String.fromCharCode(key);

    // check for backspace or delete, or if Ctrl was pressed
    if (key === 8 || isCtrl)
    {
        return true;
    }
    if (allowCharacter !== "") {
        for (var allowCharacterCount = 0; allowCharacterCount < allowCharacter.length; allowCharacterCount++) {
//            alert(allowCharacter[allowCharacterCount]);
            if (keychar === allowCharacter[allowCharacterCount]) {
                return true;
            }
        }
    }    

    reg = /^\s*[0-9]+\s*$/;
    return reg.test(keychar);
}

function allowNegativeIsNumericUp(obj, event, allowDecimal, allowNegative, allowCharacter)
{
//    if(obj.value.length > 1){
	    for (var allowNegative = 1; allowNegative < obj.value.length; allowNegative++) {
	      if ("-" === obj.value[allowNegative]) {
	    	  obj.value = "";
	      }
	    }
//    }
}

function IsNumericANDCharacter(obj, event, allowDecimal, allowNegative, allowCharacter)
{
    var key;
    var isCtrl = false;
    var keychar;
    var reg;
    var typeCharacterOlnyAlfaNumSymbolsMsg = "";

    if (window.event) {
        key = event.keyCode;
        isCtrl = window.event.ctrlKey;
    }
    else if (event.which) {
        key = event.which;
        isCtrl = event.ctrlKey;
    }

    if (isNaN(key))
        return true;

    keychar = String.fromCharCode(key);

    // check for backspace or delete, or if Ctrl was pressed
    if (key === 8 || key === 9 || isCtrl)
    {
        return true;
    }
    if (allowCharacter !== "") {
        for (var allowCharacterCount = 0; allowCharacterCount < allowCharacter.length; allowCharacterCount++) {
//            alert(allowCharacter[allowCharacterCount]);
            if (keychar === allowCharacter[allowCharacterCount]) {
                return true;
            }
        }
    }

    reg = /^\s*[a-zA-Z0-9\s]+\s*$/;
    return reg.test(keychar);
}

function IsCharacter(obj, event, allowDecimal, allowNegative, allowCharacter)
{
    var key;
    var isCtrl = false;
    var keychar;
    var reg;
    var typeCharacterOlnyAlfaSymbolsMsg = "";

    if (window.event) {
        key = event.keyCode;
        isCtrl = window.event.ctrlKey;
    }
    else if (event.which) {
        key = event.which;
        isCtrl = event.ctrlKey;
    }

    if (isNaN(key))
        return true;

    keychar = String.fromCharCode(key);

    // check for backspace or delete, or if Ctrl was pressed
    if (key === 8 || key === 9 || isCtrl)
    {
        return true;
    }
    if (allowCharacter !== "") {
        for (var allowCharacterCount = 0; allowCharacterCount < allowCharacter.length; allowCharacterCount++) {
//            alert(allowCharacter[allowCharacterCount]);
            if (keychar === allowCharacter[allowCharacterCount]) {
                return true;
            }
        }
    }

    reg = /^\s*[a-zA-Z\s]+\s*$/;
    return reg.test(keychar);
}

function IsAllowedCharacter(obj, event, allowDecimal, allowNegative, allowCharacter)
{
    var key;
    var isCtrl = false;
    var keychar;
    var reg;
    var typeCharacterOlnyAllowedMsg = "";

    if (window.event) {
        key = event.keyCode;
        isCtrl = window.event.ctrlKey;
    }
    else if (event.which) {
        key = event.which;
        isCtrl = event.ctrlKey;
    }

    if (isNaN(key))
        return true;

    keychar = String.fromCharCode(key);

    // check for backspace or delete, or if Ctrl was pressed
    if (key === 8 || key === 9 || isCtrl)
    {
        return true;
    }
    if (allowCharacter !== "") {
        for (var allowCharacterCount = 0; allowCharacterCount < allowCharacter.length; allowCharacterCount++) {
//            alert(allowCharacter[allowCharacterCount]);
            if (keychar === allowCharacter[allowCharacterCount]) {
                return true;
            }
        }
    }

//    reg = /^\s*[a-zA-Z0-9\s]+\s*$/;
//                var isFirstN = allowNegative ? keychar == '-' && obj.value.indexOf('-') == -1 : false;
//                var isFirstD = allowDecimal ? keychar == '.' && obj.value.indexOf('.') == -1 : false;

//    return reg.test(keychar);

    return false;
}

function IsCharacterSpecSymbols(obj, event, allowDecimal, allowNegative)
{
    var key;
    var isCtrl = false;
    var keychar;
    var reg;

    if (window.event) {
        key = event.keyCode;
        isCtrl = window.event.ctrlKey;
    }
    else if (event.which) {
        key = event.which;
        isCtrl = event.ctrlKey;
    }

    if (isNaN(key))
        return true;

    keychar = String.fromCharCode(key);

    // check for backspace or delete, or if Ctrl was pressed
    if (key === 8 || key === 45 || key === 95 || isCtrl)
    {
        return true;
    }

    reg = /^\s*[a-zA-Z0-9\s]+\s*$/;
//                var isFirstN = allowNegative ? keychar == '-' && obj.value.indexOf('-') == -1 : false;
//                var isFirstD = allowDecimal ? keychar == '.' && obj.value.indexOf('.') == -1 : false;

    return reg.test(keychar);
}
function IsCharacterSpecSymbolsComa(obj, event, allowDecimal, allowNegative)
{
    var key;
    var isCtrl = false;
    var keychar;
    var reg;

    if (window.event) {
        key = event.keyCode;
        isCtrl = window.event.ctrlKey;
    }
    else if (event.which) {
        key = event.which;
        isCtrl = event.ctrlKey;
    }

    if (isNaN(key))
        return true;

    keychar = String.fromCharCode(key);

    // check for backspace or delete, or if Ctrl was pressed
    if (key === 8 || key === 44 || key === 45 || key === 95 || isCtrl)
    {
        return true;
    }

    reg = /^\s*[a-zA-Z0-9\s]+\s*$/;
//                var isFirstN = allowNegative ? keychar == '-' && obj.value.indexOf('-') == -1 : false;
//                var isFirstD = allowDecimal ? keychar == '.' && obj.value.indexOf('.') == -1 : false;

    return reg.test(keychar);
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
/// common

function readonlyDropdown(obj)
{
	try{
		obj.addClass("state_readonly");
		obj.prop("readonly",true);
		obj.attr("readonly","readonly");
		obj.attr("onclick","return false;");
		obj.attr("aria-readonly", "true");
		obj.attr("tabindex",-1);
		obj.on('mousedown', function(e) {return false;});
	}
	catch(Error){}
}

function removeReadonlyDropdown(obj)
{
	try{
		obj.removeClass("state_readonly");
		obj.prop("readonly",false);
		obj.removeAttr("readonly");
		obj.removeAttr("onclick");
		obj.removeAttr("aria-readonly");
		obj.attr("tabindex",0);
		obj.unbind('mousedown').bind('mousedown',function(){return true;});
	} catch(Error){}
}

function disableIconPC(obj)
{
	try{
		obj.addClass("state_disabled");
		obj.attr('tabindex',-1);
		obj.css( 'pointer-events', 'none' );
		obj.css( 'cursor', 'default' );
		obj.attr("disabled","disabled");
 		obj.attr("aria-disabled", "true");
// 		obj.bind('onmousehover','fun(event)');
 		obj.addClass("ui-button-disabled ui-state-disabled");
// 		obj.mouseover(function(){obj.removeClass("ui-state-hover");});
 	}catch(Error){}
}

function enableIconPC(obj,action)
{
	obj.removeClass("state_disabled");
	obj.attr("tabindex",-1);
	obj.css( 'pointer-events', 'auto' );
	obj.css( 'cursor', 'pointer' );
	obj.removeAttr("disabled");
	obj.removeAttr("aria-disabled");
	obj.removeClass("ui-button-disabled ui-state-disabled");
//	obj.mouseover(function(){obj.addClass("ui-state-hover");});
//	obj.mouseout(function(){obj.removeClass("ui-state-hover");});
	obj.attr('onclick',action);
}

function disableIcon(obj)
{
	try{
		obj.addClass("state_disabled");
		obj.prop("disabled",true);
		obj.attr("disabled","disabled");
		obj.attr("onclick","return false;");
		obj.unbind("click");
		obj.attr("aria-disabled", "true");
		obj.attr("tabindex",-1);
	}
	catch(Error){}
}

function enableIcon(obj,action)
{
	try{
		obj.removeClass("state_disabled");
		obj.prop("disabled",false);
		obj.removeAttr("disabled");
		obj.attr('onclick',action);
		obj.bind("click");
		obj.attr("aria-disabled", "false");
		obj.attr("tabindex",0);
	}
	catch(Error){}
}

function readonlyIcon(obj)
{
	try{
		obj.addClass("state_readonly");
		obj.prop("readonly",true);
		obj.attr("readonly","readonly");
		obj.attr("onclick","return false;");
		obj.attr("aria-readonly", "true");
		obj.attr("tabindex",-1);
	}
	catch(Error){}
}

function removeReadonlyIcon(obj,action)
{
	try{
		obj.removeClass("state_readonly");
		obj.prop("readonly",false);
		obj.removeAttr("readonly");
		obj.attr("onclick",action);
		obj.attr("aria-readonly", "false");
		obj.attr("tabindex",-1);
	}
	catch(Error){}
}

function disableInputField(obj)
{
	try{
		obj.addClass("state_disabled");
		obj.prop("disabled",true);
		obj.attr("disabled","disabled");
		obj.attr("onclick","return false;");
		obj.attr("aria-disabled", "true");
		obj.attr("tabindex",-1);
	}
	catch(Error){}
}

function enableInputField(obj,action)
{
	try{
		obj.removeClass("state_disabled");
		obj.prop("disabled",false);
		obj.removeAttr("disabled");
		obj.attr('onclick',action);
		obj.attr("aria-disabled", "false");
		obj.removeAttr("tabindex",0);
	}
	catch(Error){}
}

function readonlyInputField(obj)
{
	try{
		obj.addClass("state_readonly");
		obj.prop("readonly",true);
		obj.attr("readonly","readonly");
		obj.attr("onclick","return false;");
		obj.attr("aria-readonly", "true");
		obj.attr("tabindex",-1);
	}
	catch(Error){}
}

function removeReadonlyInputField(obj,action)
{
	try{
		obj.removeClass("state_readonly");
		obj.prop("readonly",false);
		obj.removeAttr("readonly");
		obj.attr("onclick",action);
		obj.attr("aria-readonly", "false");
		obj.attr("tabindex",0);
	} catch(Error){}
}

function calculateDateTimeSeconds(fromDateTime, toDateTime) {
	var fromDateTimeArray = fromDateTime.split(" ");
	var toDateTimeArray = toDateTime.split(" ");
	var fromDate = fromDateTimeArray[0];
	var toDate = toDateTimeArray[0];
	
	var fromTime = fromDateTimeArray[1];
	var toTime = toDateTimeArray[1];
	
	var calculatedDays = calculateDateDays(fromDate, toDate);
	
	var calculatedSeconds = calculateTimeSeconds(fromTime, toTime);
	
	return parseInt(calculatedDays*24*60*60) + parseInt(calculatedSeconds);
}

function calculateDateTimeMinutes(fromDateTime, toDateTime) {
	var fromDateTimeArray = fromDateTime.split(" ");
	var toDateTimeArray = toDateTime.split(" ");
	var fromDate = fromDateTimeArray[0];
	var toDate = toDateTimeArray[0];
	
	var fromTime = fromDateTimeArray[1];
	var toTime = toDateTimeArray[1];
	
	var calculatedDays = calculateDateDays(fromDate, toDate);
	
	var calculatedMinutes = calculateTimeMinutes(fromTime, toTime);
	
	return parseInt(calculatedDays*24*60) + parseInt(calculatedMinutes);
}

function calculateDateTimeHours(fromDateTime, toDateTime) {
	
	var fromDateTimeArray = fromDateTime.split(" ");
	var toDateTimeArray = toDateTime.split(" ");
	var fromDate = fromDateTimeArray[0];
	var toDate = toDateTimeArray[0];
	
	var fromTime = fromDateTimeArray[1];
	var toTime = toDateTimeArray[1];
	
	var calculatedDays = calculateDateDays(fromDate, toDate);
	
	var calculatedHours = calculateTimeHours(fromTime, toTime);
	
	return parseInt(calculatedDays*24) + parseInt(calculatedHours);
}

function calculateTimeSeconds(fromTime, toTime) {
	
	var time1 = fromTime.split(':');
	var time2 = toTime.split(':');
    var hours1 = parseInt(time1[0], 10); 
    if(fromTime.indexOf("PM") > -1){
    	hours1 += 12;
	}
    var hours2 = parseInt(time2[0], 10);
    if(toTime.indexOf("PM") > -1){
    	hours2 += 12;
	}
    var mins1 = parseInt(time1[1], 10);
    var mins2 = parseInt(time2[1], 10);
    var hours = hours2 - hours1;
    var mins = 0;
    
    var seconds = 0;
    if($.trim(time1[2]) !== "" && $.trim(time2[2]) !== "") {
	    var seconds1 = parseInt(time1[2], 10);
	    var seconds2 = parseInt(time2[2], 10);
	    if(seconds2 >= seconds1) {
	        seconds = seconds2 - seconds1;
	    } else {
	    	seconds = (seconds2 + 60) - seconds1;
	    }
    }
    // get hours
    if(hours < 0) hours = 24 + hours;

    // get minutes
    if(mins2 >= mins1) {
        mins = mins2 - mins1;
    } else {
        mins = (mins2 + 60) - mins1;
        hours--;
    }
    
    // convert to fraction of 60
    mins = mins / 60; 

    hours += mins;
    hours = hours.toFixed(2);

    return (parseInt((hours*60)+mins) * 60) + seconds;
}

function calculateTimeMinutes(fromTime, toTime) {
	
	var time1 = fromTime.split(':');
	var time2 = toTime.split(':');
    var hours1 = parseInt(time1[0], 10); 
    if(fromTime.indexOf("PM") > -1){
    	hours1 += 12;
	}
    var hours2 = parseInt(time2[0], 10);
    if(toTime.indexOf("PM") > -1){
    	hours2 += 12;
	}
    var mins1 = parseInt(time1[1], 10);
    var mins2 = parseInt(time2[1], 10);
    var hours = hours2 - hours1;
    var mins = 0;

    // get hours
    if(hours < 0) hours = 24 + hours;

    // get minutes
    if(mins2 >= mins1) {
        mins = mins2 - mins1;
    }
    else {
        mins = (mins2 + 60) - mins1;
        hours--;
    }

    // convert to fraction of 60
    mins = mins / 60; 

    hours += mins;
    hours = hours.toFixed(2);

    return parseInt((hours*60)+mins);
}

function calculateTimeHours(fromTime, toTime) {
	
	var time1 = fromTime.split(':');
	var time2 = toTime.split(':');
    var hours1 = parseInt(time1[0], 10); 
    if(fromTime.indexOf("PM") > -1){
    	hours1 += 12;
	}
    var hours2 = parseInt(time2[0], 10);
    if(toTime.indexOf("PM") > -1){
    	hours2 += 12;
	}
    var mins1 = parseInt(time1[1], 10);
    var mins2 = parseInt(time2[1], 10);
    var hours = hours2 - hours1;
    var mins = 0;

    // get hours
    if(hours < 0) hours = 24 + hours;

    // get minutes
    if(mins2 >= mins1) {
        mins = mins2 - mins1;
    }
    else {
        mins = (mins2 + 60) - mins1;
        hours--;
    }

    // convert to fraction of 60
    mins = mins / 60; 

    hours += mins;
    hours = hours.toFixed(2);
    return parseInt(hours);
}

function calculateDateDays(fromDate, toDate) {
	
	var now = new Date(fromDate);
	var past = new Date(toDate);
    //store the getTime diff - or +
	var subSymbol = "";
	if(now < past){
		subSymbol = "-";
	}
    return subSymbol + Math.round(Math.abs((now.getTime() - past.getTime())/(24 * 60 * 60 * 1000)));
    //Convert values to -/+ days and return value
}

function calculateDateMonths(fromDate, toDate) {
	
	var now = new Date(fromDate);
	var past = new Date(toDate);
	var months;
	
    months = (past.getFullYear() - now.getFullYear()) * 12;
    months -= now.getMonth() + 1;
    months += past.getMonth() +1;
    
    return months;
}

function calculateDateYears(fromDate, toDate)
{
	var now = new Date(fromDate);
	var todayYear = now.getFullYear();
	var todayMonth = now.getMonth();
	var todayDay = now.getDate();

	var past = new Date(toDate);
	var birthYear = past.getFullYear();
	var birthMonth = past.getMonth();
	var birthDay = past.getDate();

	var age = todayYear - birthYear; 

	if(age !== 0){
		var subSymbol = "";
		age += "";
		if(age.indexOf('-') > -1){
			subSymbol = "-";
			age = parseInt(age.replace('-',""));
			var swapNumMonth = todayMonth;
			todayMonth = birthMonth;
			birthMonth = swapNumMonth;
			
			var swapNumDay = todayDay;
			todayDay = birthDay;
			birthDay = swapNumDay;
		}else {
			age = parseInt(age);
		}
		if (todayMonth + 1 < birthMonth + 1) {
			age--;
		}
	
		if ((birthMonth + 1 == todayMonth + 1) && todayDay < birthDay) {
			age--;
		}
		age = parseInt(subSymbol + "" + age);
	}
	return age;
}

function dateFormat(strDate){
	var nowDate = new Date(strDate);
	var toYear = nowDate.getFullYear();
	var toMonth = nowDate.getMonth();
	var toDay = nowDate.getDate();
	
	toMonth = toMonth + 1;
	
	return toMonth + "/" + toDay + "/" + toYear;
}

function processBarIn(){
	if(detectBrowser() !== "Microsoft Internet Explorer" || detectBrowser() !== "Chrome"){
		$("#processing").show();
	} else {
		$("#processing").fadeIn("slow");
	}
}

function processBarOut(){
	setTimeout(function() {
		if(detectBrowser() !== "Microsoft Internet Explorer" || detectBrowser() !== "Chrome"){
			$("#processing").hide();
		} else {
			$("#processing").fadeOut("slow");
		}
	}, 100);
}