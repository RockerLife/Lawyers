//-------------------------------------------------- include the page in html----------------------------------------------------
var loadedobjects=""
var rootdomain="http://"+window.location.hostname

function ajaxpage(url, containerid,a,b){
var page_request = false
if (window.XMLHttpRequest) {// if Mozilla, Safari etc
	page_request = new XMLHttpRequest()
}
	else if (window.ActiveXObject){ // if IE
		try {
			page_request = new ActiveXObject("Msxml2.XMLHTTP")
			} 
		catch (e){
			try{
				page_request = new ActiveXObject("Microsoft.XMLHTTP")
				}
		catch (e){}
		}
	}
	else
	return false
	page_request.onreadystatechange=function(){
		loadpage(page_request, containerid)
	}
	page_request.open("GET", url, true)
	page_request.send(null)
}

function loadpage(page_request, containerid){
if (page_request.readyState == 4 && (page_request.status==200 || window.location.href.indexOf("http")==-1))
document.getElementById(containerid).innerHTML=page_request.responseText
}

function loadobjs(){
if (!document.getElementById)
return
for (i=0; i<arguments.length; i++){
var file=arguments[i]
var fileref=""
if (loadedobjects.indexOf(file)==-1){ //Check to see if this object has not already been added to page before proceeding
if (file.indexOf(".js")!=-1){ //If object is a js file
fileref=document.createElement('script')
fileref.setAttribute("type","text/javascript");
fileref.setAttribute("src", file);
}
else if (file.indexOf(".css")!=-1){ //If object is a css file
fileref=document.createElement("link")
fileref.setAttribute("rel", "stylesheet");
fileref.setAttribute("type", "text/css");
fileref.setAttribute("href", file);
}
}
if (fileref!=""){
document.getElementsByTagName("head").item(0).appendChild(fileref)
loadedobjects+=file+" " //Remember this object as being already added to page
}
}
}
//--------------------------------------------------show and hide div-------------------------------------------------------------

function showDivArea(areas_show, areas_hide){
//alert(areas_show);
	for (var i = 0; i < areas_show.length; i++)
	{	
		ge = document.getElementById(areas_show[i]);		
		if(ge){
	//	alert(ge);
			//ge.style.display = "block";
			$("#" + areas_show[i]).show();
			}
	}
	for (var i = 0; i < areas_hide.length; i++)
	{
		var gee = document.getElementById(areas_hide[i]);		
		if(gee)
			//gee.style.display = "none";
			$("#" + areas_hide[i]).hide();
		
	}
}

function openDivArea1(){
	if(getRadioValue(document.getElementById('IsFirmHaveEquityIntGrtThan10'))=='Y' ||
			getRadioValue(document.getElementById('IsFirmPersServedAsOfficerInJointVenture'))=='Y'){
		showDivArea(['divAy'], ['divAn']);
	}
	if(getRadioValue(document.getElementById('IsFirmHaveEquityIntGrtThan10'))=='' &&
			getRadioValue(document.getElementById('IsFirmPersServedAsOfficerInJointVenture'))==''){
		showDivArea(['divAn'], ['divAy']);
	}
}

function openDivArea2(){
	//alert(getRadioValue(document.getElementById('IsFirmHaveEquityIntGrtThan10')));
	//alert(getRadioValue(document.getElementById('IsFirmPersServedAsOfficerInJointVenture')));
	if(getRadioValue(document.getElementById('IsLawyerProfLiabClaimAgntAppl'))=='Y' ||
			getRadioValue(document.getElementById('IsAnyActOmmBecomeClaimAgntFirm'))=='Y'){
		showDivArea(['divy'], ['divn']);
	}
	if(getRadioValue(document.getElementById('IsLawyerProfLiabClaimAgntAppl'))=='' &&
			getRadioValue(document.getElementById('IsAnyActOmmBecomeClaimAgntFirm'))==''){
		showDivArea(['divn'], ['divy']);
	}
}

function openDivArea3(){
	//alert(getRadioValue(document.getElementById('IsBusinessRelationWithApplEnt')));
	//alert(getRadioValue(document.getElementById('IsApplSoughtHaveAuthForEntertain')));
	//alert(getRadioValue(document.getElementById('IsApplProvideInvestForEntertain')));
	//alert(getRadioValue(document.getElementById('IsApplServedAsTrustee')));
	//alert(getRadioValue(document.getElementById('IsApplNagotiatePersonnelApp')));
	if(getRadioValue(document.getElementById('IsBusinessRelationWithApplEnt'))=='Y' ||
			getRadioValue(document.getElementById('IsApplSoughtHaveAuthForEntertain'))=='Y' ||
			getRadioValue(document.getElementById('IsApplProvideInvestForEntertain'))=='Y' ||
			getRadioValue(document.getElementById('IsApplServedAsTrustee'))=='Y' ||
			getRadioValue(document.getElementById('IsApplNagotiatePersonnelApp'))=='Y'){
			//alert('1');
		showDivArea(['divBy'], ['divBn']);
	}
	if( (getRadioValue(document.getElementById('IsBusinessRelationWithApplEnt'))=='N' || getRadioValue(document.getElementById('IsBusinessRelationWithApplEnt'))=='') &&
			(getRadioValue(document.getElementById('IsApplSoughtHaveAuthForEntertain'))=='N' || getRadioValue(document.getElementById('IsApplSoughtHaveAuthForEntertain'))=='')&&
			(getRadioValue(document.getElementById('IsApplProvideInvestForEntertain'))=='N' || getRadioValue(document.getElementById('IsApplProvideInvestForEntertain'))=='')&&
			(getRadioValue(document.getElementById('IsApplServedAsTrustee'))=='N' || getRadioValue(document.getElementById('IsApplServedAsTrustee'))=='')&&
			(getRadioValue(document.getElementById('IsApplNagotiatePersonnelApp'))=='N' || getRadioValue(document.getElementById('IsApplNagotiatePersonnelApp'))=='')){
			//alert('2');
		showDivArea(['divBn'], ['divBy']);
	}
}
//----------------------------------------------------------------------------------------------------------------------------------
$( document ).ready(function() {
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
		// TODO: handle exception
	}
});

function IsNumeric(obj, event, allowDecimal, allowNegative, allowCharacter)
{
    var key;
    var isCtrl = false;
    var keychar;
    var reg;
    var typeCharacterOlnyNumSymbolsMsg = " ";

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

    var allowCharacterValid = "";
    if (allowCharacter !== "") {
    	allowCharacterValid = "and (";
        for (var allowCharacterCount = 0; allowCharacterCount < allowCharacter.length; allowCharacterCount++) {
        	
        	if(allowCharacterCount < allowCharacter.length-1) {
            	allowCharacterValid += allowCharacter[allowCharacterCount] + ", ";
        	} else {
        		allowCharacterValid += allowCharacter[allowCharacterCount];
        	}
        }
        allowCharacterValid += ") ";
    }
    
    reg = /^\s*[0-9]+\s*$/;

//    if(allowCharacterValid !== "") {
//    	typeCharacterOlnyNumSymbolsMsg = typeCharacterOlnyNumSymbols.replace('{0}', allowCharacterValid);
//	} else {
//		typeCharacterOlnyNumSymbolsMsg = typeCharacterOlnyNumSymbols.replace('{0}', "");
//	}
//    if(!reg.test(keychar)){
//    	errorMessage(typeCharacterOlnyNumSymbolsMsg);
//    }
    return reg.test(keychar);
}


//for public insured
$(document).ready(function() {
	// Validating Form Fields.....
	$(".validateEmail").change(function() {
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (re.test($(this).val()) === false && $.trim($(this).val()) !== "") {
        
            $(this).val("");
            $(this).focus();
        }
	}); 
});