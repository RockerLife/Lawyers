var existingFormSerializeObj="";
var pageSortType = "";

function encodeRequestParam(value)
{
	return encodeURIComponent(value == null ? "" : value);
}

$( window ).load(function() {
  // Run code
	
	if($("#headerStatusDesc").text() != "") {
		$("*#headerStatusDescUpdated").attr("value", $("#headerStatusDesc").text());
		$("*#ZohoStatusDescUpdated").attr("value", $("#headerStatusDesc").text());
	}
	if($("#StatusDesc").text() != "") {
		$("*#headerStatusDescUpdated").attr("value", $("#StatusDesc").text());
		$("*#ZohoStatusDescUpdated").attr("value", $("#StatusDesc").text());
	}
	
	setTimeout(function(){ 
		if($("#headerStatusDesc").text() != "") {
			$("*#headerStatusDescUpdated").attr("value", $("#headerStatusDesc").text());
			$("*#ZohoStatusDescUpdated").attr("value", $("#headerStatusDesc").text());
		}
		if($("#StatusDesc").text() != "") {
			$("*#headerStatusDescUpdated").attr("value", $("#StatusDesc").text());
			$("*#ZohoStatusDescUpdated").attr("value", $("#StatusDesc").text());
		}
		existingFormSerializeObj = $.trim($("form").find("select,textarea, input").not('[type="hidden"]').not('[readonly="readonly"]').not('[disabled="disabled"]').serialize());
//		alert(existingFormSerializeObj)
	}, 100);
});


window.history.forward();
// **** KEYPRESS DETECT CODE TO DETECT ENTER KEY PRESS ****
var IE5=document.all? 1:0 
if(!IE5) // Must capture the event if it is NS6.0 and NS4.0+ 
{ 
	document.captureEvents(Event.KEYPRESS) 
} 
document.onkeypress = testKey; // when a key is pressed perform testkey function 
/*function testKey(e) 
{ 
	 Grab the ascii code of the key that was pressed  
	whKey = !IE5? e.which:event.keyCode; 
	
	if (whKey == 13){
	// *** PLACE CODE TO BE EXECUTED ON ENTER PRESS HERE
			//this code searches the document for a link with id attribute set to "submitForm" and if it finds it, evaluates the function in its href
			//link href must be of the format: href="javascript:function();"
			//note this will not simulate a click event on the link so it won't work with links that rely on the onClick() event
			for (var i=0; i<document.links.length; i++){
				if (document.links[i].id == "submitForm"){
					//window.location = document.links[i].href	//use this if you want to call a regular link instead of a js function
					eval(unescape(document.links[i].href))	//unescape is used to prevent spaces between function arguments from being escaped and causing a syntax error
				}
			}
	// *** END ENTER PRESS HANDLER CODE
	//to simply submit a form on enter press, use this code: document.formName.submit()
	}

}*/

function testKey(e) 
{ 
	/* Grab the ascii code of the key that was pressed */ 
	whKey = !IE5? e.which:event.keyCode; 
	var flag = false;
	
	if (whKey == 13){
		
		var linkID = "submitForm";
		var activeElementID = document.activeElement.id;
		
		if(activeElementID == "ajax_field_AopSearch"){
			
			linkID = activeElementID + "Link";
			
		}
		
	// *** PLACE CODE TO BE EXECUTED ON ENTER PRESS HERE
			//this code searches the document for a link with id attribute set to "submitForm" and if it finds it, evaluates the function in its href
			//link href must be of the format: href="javascript:function();"
			//note this will not simulate a click event on the link so it won't work with links that rely on the onClick() event
			for (var i=0; i<document.links.length; i++){
				
				if (document.links[i].id == linkID){					
					//window.location = document.links[i].href	//use this if you want to call a regular link instead of a js function
					if(activeElementID != linkID){
						if(document.links[i].onclick){
							document.links[i].onclick();
							}
						eval(unescape(document.links[i].href))	//unescape is used to prevent spaces between function arguments from being escaped and causing a syntax error
						
						flag = true;
					}
				}
			}
			if(flag == false){
				linkID = "submitForm";
				for (var i=0; i<document.links.length; i++){
				
					if (document.links[i].id == linkID){					
						//window.location = document.links[i].href	//use this if you want to call a regular link instead of a js function
						
						if(activeElementID != linkID){
							if(document.links[i].onclick){
							document.links[i].onclick();
							}
							eval(unescape(document.links[i].href))	//unescape is used to prevent spaces between function arguments from being escaped and causing a syntax error
							
						}
					}
				}
			}
			
	// *** END ENTER PRESS HANDLER CODE
	//to simply submit a form on enter press, use this code: document.formName.submit()
	}
}



//-------------manage submit form start--------------------------------------
function bbmitformforupload(af_1, method, as_dynaKeys, as_dynaValues, action)
{
  	  //alert(as_dynaKeys); alert(as_dynaValues);
      //af_1.dynaKeys.value = as_dynaKeys;
      //af_1.dynaValues.value = as_dynaValues;
      //af_1.inet_method.value = method;
      //af_1.inet_page.value= action;
      //af_1.submit();
      
      var fileName =af_1.file_path.value;
	 
	  if(fileName==""){
		alert("Please select a Excel to upload.");
		}
	  else{
		af_1.method = "post";
		af_1.action= "upload?dynaKeys="+encodeRequestParam(as_dynaKeys)+"&dynaValues="+encodeRequestParam(as_dynaValues)+"&inet_method="+encodeRequestParam(method)+"&inet_page="+encodeRequestParam(action);
		af_1.submit();		
		}
      
}


function disableBody(){
	//alert('1');
	//window.scrollTo(0,0); 
	//document.body.style.cursor='wait';
	//document.body.disabled = true;


	//document.body.pause();
	    var cvr = document.getElementById("progressBarDiv");
	    //alert(cvr);
	    if(cvr!=null)
	    {
	    cvr.style.display = "block";
	    
	    document.documentElement.style.overflow = document.body.style.overflow = "auto";
	    var height = document.body.clientHeight;
	    //showPopUp('cover1','50','50');
	    if (document.body.style.overflow = "hidden") {
	        cvr.style.width = "100%";
	        cvr.style.height = "100%";
	        //alert();
	        document.getElementById("progressBarDiv").style.height = height + "px";
	    }
	    }
	    
	}

	 
	function enableBody(containerid){

	//document.body.disabled = false;
		//document.body.style.cursor='default';

	//document.body.resume();
		
		var cvr = document.getElementById("progressBarDiv");
//		alert(cvr);
		if(cvr != null){
			//closePopUp('cover1');
			cvr.style.display = "none";
			
			document.documentElement.style.overflow = document.body.style.overflow = "";
			//var containerid = document.getElementById('divHideShow').value;
			//alert(containerid);
			
			if(containerid != null && containerid != '')
			{
				getElementForFocus(containerid);
			}
		}
	}  
	function getElementForFocus(containerid){
		if(document.getElementById(containerid) == null)
			return;
		
		if(document.getElementById(containerid).getElementsByTagName('') != null){
			var fldArray = document.getElementById(containerid).getElementsByTagName('*');
			//alert(fldArray.length);
			
			var focusfield;
			for(var i=0; i<fldArray.length; i++){
				var fld = fldArray[i];
				if(fld.getAttribute("id") != null && document.getElementById(fld.getAttribute("id")) != null){
					if(!document.getElementById(fld.getAttribute("id")).disabled &&
						(fld.type == 'text' || 
						fld.type == 'select-one' ||
						fld.type == 'select-one' ||
						fld.type == 'textarea' ||
						fld.type == 'radio' ||
						fld.type == 'checkbox' ||
						(fld.type == 'hidden' && fld.name=='hiddenfocus'))){
						
						//alert(fld.name + ' -- ' + fld.type);
						focusfield = fld.type;	
						break;
						//if(i == 4)
						//	break;
					}
				}
			}
			
			if(focusfield == 'text' || focusfield == 'checkbox' || focusfield == 'radio' || focusfield == 'hidden')
				focusfield = 'input';
			else if(focusfield == 'select-one' || focusfield == 'select')
				focusfield = 'select';	
				
			//alert(focusfield);
			fldArray = document.getElementById(containerid).getElementsByTagName(focusfield);
			for(var i=0; i<fldArray.length; i++){
				var fld = fldArray[i];
				if(fld.getAttribute("id") != null){
					fld = document.getElementById(fld.getAttribute("id"));
					if(!document.getElementById(fld.getAttribute("id")).disabled){
						fld.focus();
						return;	
					}
				}
			}
			fldArray = document.getElementById(containerid).getElementsByTagName(focusfield);
			for(var i=0; i<fldArray.length; i++){
				var fld = fldArray[i];
				if(fld.getAttribute("id") != null){
					fld = document.getElementById(fld.getAttribute("id"));
					if(!document.getElementById(fld.getAttribute("id")).disabled){
						fld.focus();
						return;	
					}
				}
			}
			fldArray = document.getElementById(containerid).getElementsByTagName(focusfield);
			for(var i=0; i<fldArray.length; i++){
				var fld = fldArray[i];
				if(fld.getAttribute("id") != null){
					fld = document.getElementById(fld.getAttribute("id"));
					if(!document.getElementById(fld.getAttribute("id")).disabled){
						fld.focus();
						return;	
					}
				}
			}
		}
	}
function manageshowdialogbox(frm,inet_method, dynakeys,dynavalues,page,containerid,nextpage)
{
	
                   //alert(page)  
              // var url ='test.do;jsessionid='+frm.jsessionid.value+'?inet_method='+inet_method+'&inet_page='+nextpage+'&dynaKeys='+dynakeys+'inet_previouspage|'+'&dynaValues='+dynavalues+page+'|'; 
               
               if(page == 'firm'){
               //alert("from firm");
               		var url ='test.do?inet_method='+encodeRequestParam(inet_method)+'&inet_page='+encodeRequestParam(nextpage)+'&dynaKeys='+encodeRequestParam(dynakeys+'inet_previouspage|')+'&dynaValues='+encodeRequestParam(dynavalues+page+'|');
               		
            		var currentFormSerializeObj = $.trim($("form").find("select,textarea, input").not('[type="hidden"]').not('[readonly="readonly"]').not('[disabled="disabled"]').serialize());
              	    var isDataChange = "";
              	    if(currentFormSerializeObj != existingFormSerializeObj){
              	   	 isDataChange = "Y";
              	    } else {
              	   	 isDataChange = "N";
              	    }
              	  url = url + "&isDataChange=" + isDataChange;
               	    
               		 window.open(url);
                 
                  
                  managesubmitformwithajax(frm,inet_method,dynakeys, dynavalues,page,containerid);
                  
                 enableBody(); 
               
               }else{
                //alert("from Attorney freeform");
               		var url ='test.do;jsessionid='+encodeRequestParam(frm.jsessionid.value)+'?inet_method='+encodeRequestParam(inet_method)+'&inet_page='+encodeRequestParam(nextpage)+'&dynaKeys='+encodeRequestParam(dynakeys+'inet_previouspage|')+'&dynaValues='+encodeRequestParam(dynavalues+page+'|');
               		
               		var currentFormSerializeObj = $.trim($("form").find("select,textarea, input").not('[type="hidden"]').not('[readonly="readonly"]').not('[disabled="disabled"]').serialize());
              	    var isDataChange = "";
              	    if(currentFormSerializeObj != existingFormSerializeObj){
              	   	 isDataChange = "Y";
              	    } else {
              	   	 isDataChange = "N";
              	    }
              	  url = url + "&isDataChange=" + isDataChange;
              	  
                disableBody();
                var newWin =  window.open(url,'',"width=850,height=320,left=75,top=200,toolbar=no,status=off,resizable=no,scrollbars=yes,menubar=no,status=no,directories=no");
            	 newWin.focus();
                enableBody();
               }
              
             
              
                  
}


function managesubmitformwithajax(frm,inet_method,dynakeys, dynavalues,page,containerid)
{
  // alert(dynakeys);
   //alert(dynavalues);
    var page_request= false;
    var queryst ='dynaKeys='+encodeRequestParam(dynakeys+'popuppage|datadisplayid|')  +'&dynaValues='+encodeRequestParam(dynavalues+'popuppage|'+containerid+'|')+'&ajaxpage='+encodeRequestParam(page)+'&ajaxpage=ajaxpage';
	var url =  queryst;
	var currentFormSerializeObj = $.trim($("form").find("select,textarea, input").not('[type="hidden"]').not('[readonly="readonly"]').not('[disabled="disabled"]').serialize());
	    var isDataChange = "";
	    if(currentFormSerializeObj != existingFormSerializeObj){
	   	 isDataChange = "Y";
	    } else {
	   	 isDataChange = "N";
	    }
	  url = url + "&isDataChange=" + isDataChange;
	disableBody(); 
    retrieveURL1( url, containerid, page_request,page,inet_method);
}

function managesubmitpopupform(af_1,method,as_dynaKeys, as_dynaValues,action)
{
     // alert('kk');
      af_1.dynaKeys.value = as_dynaKeys;
      af_1.dynaValues.value = as_dynaValues;
      af_1.inet_method.value = method;
      af_1.inet_page.value= action;
     af_1.method="post";
      af_1.submit();
      window.close();
}



//-------------------------------------------------------------  Runtime Script-----------------------------------------------------------------
  	var loadedobjects=""
	var rootdomain="http://"+window.location.hostname

   //----start of ajaxpage block link------------
		function manageajaxpageblocklink(frm,  inetmethod , dynakeys, dynavalues,page,containerid)
	{
	  //alert(containerid);
  		
  	    
		var page_request ;
		 // alert( frm+'--'+ inetmethod +'----'+ dynakeys+'--------'+dynavalues+'------'+containerid)
		dynavalues = dynavalues.replace('&','#AMP#');
		//alert(123)
		var submitUrl =  parseElement(document.forms[0]);
		var queryst = submitUrl;
		//alert(queryst)
		queryst = queryst + 'dynaKeys='+encodeRequestParam(dynakeys+'inet_method|datadisplayid|')  +'&dynaValues='+encodeRequestParam(dynavalues+inetmethod+'|'+containerid+'|') +'&ajaxpage='+encodeRequestParam(page);
		
		//alert(document.getElementById("append_IsFirmCarryingProfLiabilityIns").value);
		 //alert('containerid' + containerid);
		
		var currentFormSerializeObj = $.trim($("form").find("select,textarea, input").not('[type="hidden"]').not('[readonly="readonly"]').not('[disabled="disabled"]').serialize());
  	    var isDataChange = "";
  	    if(currentFormSerializeObj != existingFormSerializeObj){
  	   	 isDataChange = "Y";
  	    } else {
  	   	 isDataChange = "N";
  	    }
  	  queryst = queryst + "&isDataChange=" + isDataChange;
		 //divhideshow (containerid);
		 
		 if(document.getElementById("Amount_0"))
		{
			var node = document.getElementById("Amount_0");
			queryst = queryst+"&"+ node.name + "=" + $.trim(node.value);
		//	alert(queryst);
		}
		if(document.getElementById("Amount_1"))
		{
			var node = document.getElementById("Amount_1");
			queryst = queryst+"&"+ node.name + "=" + $.trim(node.value);
		//	alert(queryst);
		}
		if(document.getElementById("Amount_2"))
		{
			var node = document.getElementById("Amount_2");
			queryst = queryst+"&"+ node.name + "=" + $.trim(node.value);
		//	alert(queryst);
		}
		//alert(document.getElementById("CountyDesc"));
		/*
		if(document.getElementById("CountyDesc"))
        {
       
              var node = document.getElementById("CountyDesc");
              queryst = queryst+"&"+ node.name + "=" + node.value ;
              alert(queryst);
        }*/
        // from Accountant
        if(document.getElementById("CountyDesc"))
        {
        		//alert("CountyDesc");
              var node = document.getElementById("CountyDesc");
              var county = node.value;
              county = county.replace("&","#AMP#");
              queryst = queryst+"&"+ node.name + "=" + $.trim(county);
              //alert(queryst);
        }
        if(document.getElementById("ajax_field_CountySelect"))
        {
        		//alert("CountyDesc");
              var node = document.getElementById("ajax_field_CountySelect");
              var county = node.value;
              county = county.replace("&","#AMP#");
              queryst = queryst+"&"+ node.name + "=" + $.trim(county);
              //alert(queryst);
        }

		if(document.getElementById("AccountName"))
        {
              var node = document.getElementById("AccountName");
              queryst = queryst+"&"+ node.name + "=" + $.trim(node.value);
             // alert(queryst);
        }
		if(document.getElementById("WorkPhone"))
        {
              var node = document.getElementById("WorkPhone");
              queryst = queryst+"&"+ node.name + "=" + $.trim(node.value);
             // alert(queryst);
        }
		if(document.getElementById("AccountEmail"))
        {
              var node = document.getElementById("AccountEmail");
              queryst = queryst+"&"+ node.name + "=" + $.trim(node.value);
             // alert(queryst);
        }
		if(document.getElementById("ProducerCode"))
        {
              var node = document.getElementById("ProducerCode");
              queryst = queryst+"&"+ node.name + "=" + $.trim(node.value);
             // alert(queryst);
        }
		if(document.getElementById("StateCode"))
        {
              var node = document.getElementById("StateCode");
              queryst = queryst+"&"+ node.name + "=" + $.trim(node.value);
             // alert(queryst);
        }
		if(document.getElementById("PolicyEffectiveDate"))
        {
              var node = document.getElementById("PolicyEffectiveDate");
              queryst = queryst+"&"+ node.name + "=" + $.trim(node.value);
             // alert(queryst);
        }
       if(document.getElementById("CompanyKey"))
        {
              var node = document.getElementById("CompanyKey");
              queryst = queryst+"&"+ node.name + "=" + $.trim(node.value);
             // alert(queryst);
        }
		if(document.getElementById("searchByCountyDesc")){
			var node = document.getElementById("searchByCountyDesc");
            var county = node.value;
            county = county.replace("&","#AMP#");
            queryst = queryst+"&"+ node.name + "=" + $.trim(county);
			
		}
		if(document.getElementById("ProducerCode")){
			var node = document.getElementById("ProducerCode");
            var ProducerCode = node.value;
            ProducerCode = ProducerCode.replace("&","#AMP#");
            queryst = queryst+"&"+ node.name + "=" + $.trim(ProducerCode);
            //alert(queryst);
		}
		if(document.getElementById("AccountNamesearch")){
			var node = document.getElementById("AccountNamesearch");
            var accountName = node.value;
            accountName = accountName.replace("&","#AMP#");
            queryst = queryst+"&"+ node.name + "=" + $.trim(accountName);
			
		}
		if(document.getElementById("WQQuoteNumberSearch")){
			var node = document.getElementById("WQQuoteNumberSearch");
            var accountName = node.value;
            accountName = accountName.replace("&","#AMP#");
            queryst = queryst+"&"+ node.name + "=" + $.trim(accountName);
			
		}
		if(document.getElementById("WQPolicyNumberSearch")){
			var node = document.getElementById("WQPolicyNumberSearch");
            var accountName = node.value;
            accountName = accountName.replace("&","#AMP#");
            queryst = queryst+"&"+ node.name + "=" + $.trim(accountName);
			
		}
		if(document.getElementById("WQEmailSearch")){
			var node = document.getElementById("WQEmailSearch");
            var accountName = node.value;
            accountName = accountName.replace("&","#AMP#");
            queryst = queryst+"&"+ node.name + "=" + $.trim(accountName);
			
		}
		if(document.getElementById("AccountEmailSearch")){
			var node = document.getElementById("AccountEmailSearch");
            var accountName = node.value;
            accountName = accountName.replace("&","#AMP#");
            queryst = queryst+"&"+ node.name + "=" + $.trim(accountName);
			
		}
		if(document.getElementById("WQEmailSearch") || document.getElementById("WQPolicyNumberSearch") || document.getElementById("WQQuoteNumberSearch") || document.getElementById("AccountNamesearch") || document.getElementById("AccountNameSearch") || document.getElementById("AccountEmailSearch")){
			var fieldid = containerid;
			fieldid = fieldid.substring(containerid.indexOf("_")+1);
			if(fieldid == 'AccountNameSearch'){
				fieldid += "MA";
			}
			queryst = queryst+"&"+ "SEARCHTYPE=" + fieldid;
		}
		if(document.getElementById("BRAccountNameSearch")){
			var node = document.getElementById("BRAccountNameSearch");
            var accountName = node.value;
            accountName = accountName.replace("&","#AMP#");
            queryst = queryst+"&"+ node.name + "=" + $.trim(accountName);
			
		}
		if(document.getElementById("BRPolicyNumberSearch")){
			var node = document.getElementById("BRPolicyNumberSearch");
            var accountName = node.value;
            accountName = accountName.replace("&","#AMP#");
            queryst = queryst+"&"+ node.name + "=" + $.trim(accountName);
			
		}
		if(containerid ==  'insuredEmailZoho' )
        {
			queryst += $("#insuredEmailZoho :input").serialize();
        }
		
		
		// alert(queryst);
		if(inetmethod !="refreshLimits") 
			disableBody();
		 
		var url = queryst;
		 // alert(url);
	  //  alert(containerid);
	     // alert(url);
	     //alert(document.forms[0].jsessionid.value);
    	//Added Session Handling Code 
    	retrieveURL1( url, containerid, page_request,page,document.forms[0].jsessionid.value,inetmethod);
	}
  //----end of ajaxpage block link---------------
  
  function saveLimit()
  {
      
  }
  
  function get_radio_value()
	{
		for (var i=0; i < document.myForm.IsClaimExpensesType.length; i++)
   		{
   			if (document.myForm.IsClaimExpensesType[i].checked)
      			{
      				var rad_val = document.myForm.IsClaimExpensesType[i].value;
      				return rad_val;
      			}
   		}
    }
   function get_radio_value1()
	{
	    //alert(document.myForm.IsControlClientFunds.length);
		for (var i=0; i < document.myForm.IsControlClientFunds.length; i++)
   		{
   			if (document.myForm.IsControlClientFunds[i].checked)
      			{
      				var rad_val = document.myForm.IsControlClientFunds[i].value;
      				//alert(rad_val);
      				return rad_val;
      			}
   		}
    }
     function get_radio_value2()
	{
	    
		for (var i=0; i < document.myForm.IsFirmRenderedAuditOrAttestService.length; i++)
   		{
   			if (document.myForm.IsFirmRenderedAuditOrAttestService[i].checked)
      			{
      				var rad_val = document.myForm.IsFirmRenderedAuditOrAttestService[i].value;
      				return rad_val;
      			}
   		}
    }
    
 	function get_radio_value3()
	{
		for (var i=0; i < document.myForm.IsNonMonetaryCompensationRecieved.length; i++)
   		{
   			if (document.myForm.IsNonMonetaryCompensationRecieved[i].checked)
      			{
      				var rad_val = document.myForm.IsNonMonetaryCompensationRecieved[i].value;
      				return rad_val;
      			}
   		}
    }
  //-- start of manage right click menu-----------
	function managerightclickmenu(frm,  inet_method, dynakeys,dynavalues,page,containerid)
	{ 
  		var page_request = false;
  		var starry = new Array();
  		var queryst ="";
  		var fieldno ="";

   		//populating the div postion id throw ctxmenu param  appening the primary key value to the container--------------------
  		var ctxmenuparamvalue = document.getElementsByName('ctxmenuparam').value;
  		 //alert(ctxmenuparamvalue);
		if(ctxmenuparamvalue){
			starry = ctxmenuparamvalue.split('|');
			 
	   		for(var i=0;i<starry.length;i++)
	  		{
	     		if(starry[i].length!=0)
	      		{
	         		if(i%2==1)
	         			dynakeys =dynakeys + starry[i]+'|';
	         		else{
	         		    dynavalues=dynavalues+ starry[i]+'|';
	        			}
	        	
		        	if(i==3)
		        	{
		        		if(dynavalues.match('menuAdd'))
		        		{
		         	 		containerid;
		         	 	}else{
		         	 	     fieldno = starry[2];
		         	 		containerid = containerid+"_"+fieldno;
		         	 	}
		         	}
	             }
	         }
		}
   // hidding of the div based on the container id  
     //divhideshow (containerid);
     
    // based on the dynakey value the page is submitted 
    if(dynakeys.match('isajaxsubmit'))
    { 
    	/*disableBody();  
  		queryst ='dynaKeys='+dynakeys+'ajaxpage|&dynaValues='+dynavalues +'ajaxpage|&ajaxpage='+page;
  		var url =  queryst; //'ComponentProcessServlet.do?'+queryst;
  		//alert('33');
  		retrieveURL1( url, containerid, page_request,page,frm.jsessionid.value,inet_method);*/
  		
  		var submitUrl = parseElement(document.forms[0]);
    	disableBody(); 
    	queryst = submitUrl; 
  		queryst = queryst+'dynaKeys='+encodeRequestParam(dynakeys+'ajaxpage|')+'&dynaValues='+encodeRequestParam(dynavalues +'ajaxpage|')+'&ajaxpage='+encodeRequestParam(page) + '&inet_method='+encodeRequestParam(inet_method) + '&inet_ajax_form=Y' + '&inet_page='+encodeRequestParam(page);
  		var url = queryst; 
  		
  		var currentFormSerializeObj = $.trim($("form").find("select,textarea, input").not('[type="hidden"]').not('[readonly="readonly"]').not('[disabled="disabled"]').serialize());
  	    var isDataChange = "";
  	    if(currentFormSerializeObj != existingFormSerializeObj){
  	   	 isDataChange = "Y";
  	    } else {
  	   	 isDataChange = "N";
  	    }
  	  url = url + "&isDataChange=" + isDataChange;
  		//alert('33');
  		//alert(url);
  		
  		//going to validate rightclickmenu
  		//alert('going to validate rightclickmenu'); 
  		var isErrorFound = false;
  		validateRightClickMenu(url, containerid, page_request, page, frm.jsessionid.value, inet_method, isErrorFound, frm, dynakeys,dynavalues);
    } else
    	 { 
    	divhideshow (containerid);
    	 	managesubmitform(document.forms[0], inet_method, dynakeys, dynavalues, page);
  		  }
   }
//------------------------------- end of right click menu-------------------------------------------

//-------------manage submit form start--------------------------------------
function managesubmitform(af_1, method, as_dynaKeys, as_dynaValues, action)
{
	if(!(method.indexOf("download") > -1) && method != 'showSessionList' && method != 'viewAll'){
		disableBody();
	}
  // alert(as_dynaKeys); alert(as_dynaValues);
      af_1.dynaKeys.value = as_dynaKeys;
      af_1.dynaValues.value = as_dynaValues;
      af_1.inet_method.value = method;
      af_1.inet_page.value= action;
      af_1.method = "post";
      try
      {
     var currentFormSerializeObj = $.trim($("form").find("select,textarea, input").not('[type="hidden"]').not('[readonly="readonly"]').not('[disabled="disabled"]').serialize());
     var isDataChange = "";
     if(currentFormSerializeObj != existingFormSerializeObj){
    	 isDataChange = "Y";
     } else {
    	 isDataChange = "N";
     }
     var status = $("#headerStatusDesc").text();
     if(status.indexOf('New')!= -1 && $("#User").val() == "Agent" && isDataChange == "N"){
    	 isDataChange = "Y";
     }
     if(af_1.enctype == 'multipart/form-data')
      {
            //af_1.action= "test.do?dynaKeys="+as_dynaKeys+"&dynaValues="+as_dynaValues+"&inet_method="+method+"&inet_page="+action;
            af_1.action= "test.do;jsessionid="+encodeRequestParam(af_1.jsessionid.value)+"?dynaKeys="+encodeRequestParam(as_dynaKeys)+"&dynaValues="+encodeRequestParam(as_dynaValues)+"&inet_method="+encodeRequestParam(method)+"&inet_page="+encodeRequestParam(action);
           // alert(af_1.action);
      }
     
     $(af_1).append('<input type="hidden" name="isDataChange" id="isDataChange" value="' + isDataChange + '" />');
     }
     catch(e)
     {}
     //alert(method);
     //alert(action);
     if(method=='showSessionList' || (method=='viewAll' && action=='UWReview') || (method == 'downloadViewQuoteLetter' && action == 'quoteOption')
      || (method == 'downloadpdf' && action == 'list') || (method == 'downloadpdf' && action == 'header'))
     	af_1.target="_blank";
      else
     	 af_1.target="_self";
      
     af_1.submit();
     
}

 
//--------------end of the manage submit form------------------------------


//---------start of the div hide show----------------------------------
	//div Hide and Show on based on the container id ------
function divhideshow (containerid){
    //alert('container id---'+containerid);
    if(containerid != 'attachmentDiv'){
	      var tempcontainerid = containerid.substring(0, containerid.lastIndexOf(':'));
	      tempcontainerid = tempcontainerid.substring(tempcontainerid.lastIndexOf('_')+1, tempcontainerid.length);
	      //alert(tempcontainerid);
	      if(tempcontainerid.indexOf('multiple')!=-1){
	            //alert(tempcontainerid);
	            tempcontainerid = tempcontainerid.substring(tempcontainerid.indexOf('multiple')+8, tempcontainerid.length);
	            //alert(tempcontainerid);     
	            
	            if(document.getElementById('divHideShow'+tempcontainerid) != null){
	            var divid =  document.getElementById('divHideShow'+tempcontainerid).value;
	            
	            //alert('MUltiple div id---'+divid);
	          
	                  if(divid != ''){           
	                  if(document.getElementById(divid))
	                        document.getElementById(divid).innerHTML = '';
	                  }
	              
	              document.getElementById('divHideShow'+tempcontainerid).value = containerid;
	              
	             // alert('divHideShow1 ---  ' + document.getElementById('divHideShow'+tempcontainerid).value);
	         }  
	                  
	      }else{      
	            if(document.getElementById('divHideShow') != null){
	            var divid =  document.getElementById('divHideShow').value;
	            
	           // alert('div id---'+divid);
	          
	                  if(divid != ''){           
	                  if(document.getElementById(divid))
	                        document.getElementById(divid).innerHTML = '';
	                  }
	              
	              document.getElementById('divHideShow').value = containerid;
	         }
	   }
	  }
}

//---------- end of the div hideshow-----------------------------------

//---------start ajaxsorting ----------------------------------------------
	function ajaxsorting(page, containerid,dynakeys,dynavalues)
	{
    	ajaxpagination(page, containerid,dynakeys,dynavalues);
	}
//----------end of ajaxsorting--------------------------------------------


//--------------------start of ajaxpagination ---------------------------
	function ajaxpagination(page, containerid,dynakeys,dynavalues)
	{
		disableBody();
    	var page_request = false;
		var temp = new Array();
    	temp = dynakeys.split('|');
    	var submitUrl =  parseElement(document.forms[0]);
    	//alert(submitUrl);
    	queryst = submitUrl;
    	
		var containerIndex =  containerid.substring(containerid.lastIndexOf('_'),containerid.length);
        if(containerid=='ajax_sorting'){
		        var recordPerPage = document.getElementById(temp[6]);
		    	
		    	var rePerpagevalue = recordPerPage.options[recordPerPage.selectedIndex].value;
				var pagenumber = document.getElementById(temp[7]);
				var pagenumbervalue = pagenumber.options[pagenumber.selectedIndex].value;
				dynavalues = dynavalues+rePerpagevalue+'|'+pagenumbervalue+'|';
				queryst = queryst+ 'dynaKeys='+encodeRequestParam(dynakeys)+'&dynaValues='+encodeRequestParam(dynavalues)+'&ajaxpage='+encodeRequestParam(page);

				pageSortType = (pageSortType == "" || pageSortType == "DESC") ? "ASC" : (pageSortType == "ASC") ? "DESC" : pageSortType;
				queryst = queryst.replace("inet_sort_type|", "page_sort_type|inet_skip_request|");
				queryst = queryst.replace("inet_sort_field|", "PageSort|");
				queryst = queryst.replace("desc|", pageSortType+"|Y|");
				queryst = queryst.replace("|sorting|", "|pagination|");
				queryst = queryst.replace("Premium|", "PremiumSort|");
				queryst = queryst.replace("TotalCalculatedPremiumSort|", "PremiumSort|");
//				queryst = queryst.replace("=list", "=list.html");
        }else
        {
		        var recordPerPage = document.getElementById(temp[6]+containerIndex);
		    	
		    	var rePerpagevalue = recordPerPage.options[recordPerPage.selectedIndex].value;
				var pagenumber = document.getElementById(temp[7]+containerIndex);
				var pagenumbervalue = pagenumber.options[pagenumber.selectedIndex].value;
				dynavalues = dynavalues+rePerpagevalue+'|'+pagenumbervalue+'|';
				queryst = queryst+ 'dynaKeys='+encodeRequestParam(dynakeys)+'&dynaValues='+encodeRequestParam(dynavalues)+'&ajaxpage='+encodeRequestParam(page);
		}
			
		//alert(queryst +'  ' + containerid);
		
//				alert(queryst);
    	var url = queryst;
    	//alert(url);
		retrieveURL1( url, containerid, page_request,page,document.forms[0].jsessionid.value,null);
		
		 if(containerid=='ajax_sorting'){
			changeStatusColor(); 
		 }
	}
//----------------end of ajaxpagination -----------------------------------



//----------start of the retrieveURL----------------------------------------------------------
	function retrieveURL1( url, containerid, page_request,page,jsessionid,inet_method)
	{
	  // alert(window.XMLHttpRequest);
	   //alert(window.ActiveXObject);
		try{ 
			   if (window.XMLHttpRequest){ // if Mozilla, Safari etc
					//alert('33');
					page_request = new XMLHttpRequest()
				}else if (window.ActiveXObject)
				{                             // if IE
					try {
		//			alert('2');
							page_request = new ActiveXObject("Msxml2.XMLHTTP")
						} 
					catch (e){
								try{
			//					alert('3');
										page_request = new ActiveXObject("Microsoft.XMLHTTP")
						  		    }
										catch (e){ alert(e);}
							  }
				}else{
				//alert('5');
					return false
				}
			page_request.onreadystatechange=function()
			{
				loadpage(page_request, containerid,page,inet_method)
			}
	
			//alert(url);
			
			//Added Session Handling Code
			var doVar = "*.do";
			//alert(jsessionid);
			if(jsessionid != null){
				doVar = doVar + ";jsessionid="+jsessionid;
			}
//			alert($.trim(inet_method));
//			alert(page);
//			alert(containerid);
			if(("" == $.trim(inet_method) || "update" == $.trim(inet_method) || "saveRenewReview" == $.trim(inet_method) || "close" == $.trim(inet_method) || "save" == $.trim(inet_method)) && ("renewalReview_freeform" == page || "list" == page || "list.html" == page || "changeFirstReviewer" == page || "status_freeform" == page || "changelostworkq" == page) && "ajax_sorting" == containerid){
				page_request.open('POST', doVar, false);
			} else {
				page_request.open('POST', doVar, true);
			}
			//Ended Code
			page_request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");	
			
			page_request.send(url);
			//alert('6');
		}catch (e){ alert(e)}
	}
//--------------------end of the retrieveURL--------------------------------------------------
	function callJavaScriptMethodsForAjax(page,inet_method){
		//alert(page)
		if(page=='Attorneys'){
		
			showPopUpOverloaded('LicensedStates','isPopupAttorneyStates');
			
		}
	}

//--------------------start of load page------------------------------------------------------
	function loadpage(page_request, containerid,page,inet_method)
	{
	    //	alert('loadpage start..');
		//	alert(page_request.readyState);
		//alert(page_request.status);
		//alert(window.location.href.indexOf("http"));
		//alert(containerid);
		if (page_request.readyState == 4 && ((page_request && page_request.status==200) || window.location.href.indexOf("http")==-1))
		{
			//alert('inside if   ' + containerid);
			//alert('response....'+page_request.responseText);
			if(page_request.responseText != null && page_request.responseText.length >= 10){
				if(page_request.responseText.substr(0,10) != null){
					var result = page_request.responseText.substr(0,10);
					if(result.indexOf('ajaxerrors')>=0){
						clearPageMsgOrPageErrorOnAjaxCall();
						result = page_request.responseText; //removing ajaxerrors from response
						//alert(result)
						
						populaterrorinajaxform(result);
						enableBody();
						
						return;
					}		
				}
			}
			//enableBody();
			if(containerid.length >= 3){
				var str = containerid.substring(0,7);
				//alert('inside if   ' + containerid + " ----- " + document.getElementById(containerid));
				//alert('response....'+page_request.responseText);
				
				if(str == "ajaxxml"){
					try{
					document.getElementById(containerid).innerHTML = '';
					//alert(page_request.responseXML);
					var xml = page_request.responseXML;
					//alert(xml);
					var nodes = xml.getElementsByTagName(containerid);
					//alert(nodes.length); 
					var node, nodevalue;
					var renderedSuggestions = {};
					var renderedSuggestionCount = 0;
					for(var i=0; i <nodes.length; i++) {
						node = nodes[i];
						if(node == null || node.firstChild == null){
							continue;
						}
						nodevalue = node.firstChild.nodeValue;
						//alert(nodevalue);
						var suggestionKey = nodevalue.replace(/^\s+|\s+$/g, "").toLowerCase();
						if(renderedSuggestions[suggestionKey]){
							continue;
						}
						renderedSuggestions[suggestionKey] = true;
						nodevalue = nodevalue.replace(/ /g, '&nbsp;');
						var fieldid = containerid;
						fieldid = fieldid.substring(containerid.indexOf("_")+1);//"StatusKey_search";
						//alert(document.getElementById(fieldid)!=null);
						// this condition is added to solve the issue PONEJ-2863 (part 2) 
						if(document.getElementById(fieldid) == null){
							fieldid = "ajax_field_" +fieldid
						}	
						var search = "this.innerHTML,\'" + containerid + "\',\'" + fieldid + "\'";
						var suggest = '<div onmouseover="javascript:suggestOver(this);"';
						suggest += 'onmouseout="javascript:suggestOut(this);"';
						suggest += 'onclick="javascript:setSearch('+search+');"';
						suggest += 'class="suggest_link">' + nodevalue + '</div>';
						//alert(suggest);
						document.getElementById(containerid).innerHTML += suggest;
						renderedSuggestionCount++;
						if(document.getElementById(fieldid)){
							document.getElementById(fieldid).focus();
						}
						else if(document.getElementById('ajax_field_'+fieldid)){
							document.getElementById('ajax_field_'+fieldid).focus();
						}
						
					}
					if(renderedSuggestionCount > 0){
					if(document.getElementById(containerid))
						document.getElementById(containerid).style.display = "block";
					} else {
						if(document.getElementById(containerid))
							document.getElementById(containerid).style.display = "none";
					}
					}catch (e) {
			            enableBody();
						// TODO: handle exception
					}
	                    //alert(document.getElementById(containerid).innerHTML)                   
					enableBody();
					return;
				}
				
				
				//alert('if.response..2..');
			}
			
			//alert(containerid);
			//alert(document.getElementById(containerid));
			try{
			document.getElementById(containerid).style.display='block';
			//alert(document.getElementById(containerid).innerHTML);
			
			document.getElementById(containerid).innerHTML=page_request.responseText;
			//alert(page_request.responseText)
			if(containerid == 'countyList' )
  			{
                    //document.getElementById('StateCode').focus();
                       //alert('StateCode'+document.getElementById('StateCode').value);                   
            }
            if(containerid ==  'cityList' )
            {
                //document.getElementById('CountyDesc').focus();
                       
            }          		
            if(containerid ==  'insuredEmailZoho' )
            {
                //document.getElementById('CountyDesc').focus();
            	loadIndicationPage();
            	toggleDivDisplay('ajaxxmlCountyDesc_CountyDesc');
            	makeDefenseOutsideRuleOnPageLoadIndication();
            	loadQuickQuoteInsured();
            	showIndicationOther('AOP_Percentage_25');
            	$('.datepicker').datepicker();
            	$("#PolicyEffectiveDate").change();
            }
            if(containerid ==  'emailNotificationsDetail' )
            {
            	$('.jqte-test').jqte();
            	$('.datepicker').datepicker();
            }
			
			//alert('if.response..2..');
			//alert(page);
            callJavaScriptMethodsForAjax(page,inet_method);
			if(page=='attorneys_freeform' || page=='firm' || page=='RenewFirm'){
				loadFirmAttorney();
				//alert(inet_method)
				if(inet_method=='attorneys_states'){
					//alert("kk");
					showPopUpList('isPopupAttorneyStates','eval:FirmRule.isPopupAttorneyStates');
					resetattorneystate();
				}
			}else if(page=='primaryLocation_freeform'){
				loadFirmPrimaryLoc();
			}else if(page=='enterSportAttorneys_freeform'){
				loadEntertainAttorney();
			}else if(page=='enterSportClients_freeform'){
				loadEntertainClient();
			}else if(page=='firm' && containerid=='div5y'){
				loadFirmAttorney();
			}else if(page=='firm' && containerid=='div6y'){
				loadFirmPrimaryLoc();
			}else if(page=='aop' && containerid=='divAay'){
				loadEntertainAttorney();
			}else if(page=='aop' && containerid=='divAby'){
				loadEntertainClient();
			}else if(page=='firmAnnualRevenue_freeform'){
				loadPracticeAnnRev();
			}else if(page=='personnelServed_freeform'){
				loadPracticePersonnel();
			}else if(page=='feesSued_freeform'){
				loadPracticeFeesSuit();
			}else if(page=='practice' && containerid=='div13y'){
				loadPracticeAnnRev();
			}else if(page=='practice' && containerid=='divPract3y'){
				loadPracticePersonnel();
			}else if(page=='practice' && containerid=='divPrac11y'){
				loadPracticeFeesSuit();
			}else if(page=='coverageClaims_freeform'){
				loadCoverageClaim();
				loadEditCoverageClaim();
			}else if(page=='coverage' && containerid=='divCov1By'){
				loadCoverageClaim();
			}else if(page=='predecessorERP_freeform'){
				loadCoverageERP();
			}else if(page=='coverage' && containerid=='divCov7By'){
				loadCoverageERP();
			}else if(page=='corporateSupp' && containerid=='divAOP'){
				loadEnvironmental();
				loadFinancialIns();
				loadPlaintiff();
				loadRealEstate();
				loadTax();
				loadTitle();
				loadWillsEstate();
			}else if(page=='environmentalSupp' && containerid=='divAOP'){
				loadEnvironmental();
				loadFinancialIns();
				loadPlaintiff();
				loadRealEstate();
				loadTax();
				loadTitle();
				loadWillsEstate();
			}else if(page=='environmentalAttorney_freeform'){
				loadEnvAttorney();
			}else if(page=='environmentalSupp' && containerid=='divAy'){
				loadEnvAttorney();
			}else if(page=='environmentalContractors_freeform'){
				loadEnvContractor();
			}else if(page=='environmentalSupp' && containerid=='divEnvCay'){
				loadEnvContractor();
			}else if(page=='financialInstitutionSupp' && containerid=='divAOP'){
				loadEnvironmental();
				loadFinancialIns();
				loadPlaintiff();
				loadRealEstate();
				loadTax();
				loadTitle();
				loadWillsEstate();
			}else if(page=='financialInstitution_freeform'){
				loadFinanInst();
			}else if(page=='financialInstitutionSupp' && containerid=='div2Ay'){
				loadFinanInst();
			}else if(page=='plaintiffSupp' && containerid=='divAOP'){
				loadEnvironmental();
				loadFinancialIns();
				loadPlaintiff();
				loadRealEstate();
				loadTax();
				loadTitle();
				loadWillsEstate();
			}else if(page=='plaintiffAttorneys_freeform'){
				loadPlaintiffAttorney();
			}else if(page=='plaintiffSupp' && containerid=='div2Ay'){
				loadPlaintiffAttorney();
			}else if(page=='taxSupp' && containerid=='divAOP'){
				loadEnvironmental();
				loadFinancialIns();
				loadPlaintiff();
				loadRealEstate();
				loadTax();
				loadTitle();
				loadWillsEstate();
			}else if(page=='taxAttorney_freeform'){
				loadTaxAttorney();
			}else if(page=='taxSupp' && containerid=='div2ay'){
				loadTaxAttorney();
			}else if(page=='titleSupp' && containerid=='divAOP'){
				loadEnvironmental();
				loadFinancialIns();
				loadPlaintiff();
				loadRealEstate();
				loadTax();
				loadTitle();
				loadWillsEstate();
			}else if(page=='realEstateSupp' && containerid=='divAOP'){
				loadEnvironmental();
				loadFinancialIns();
				loadPlaintiff();
				loadRealEstate();
				loadTax();
				loadTitle();
				loadWillsEstate();
			}else if(page=='willsEstateSupp' && containerid=='divAOP'){
				loadEnvironmental();
				loadFinancialIns();
				loadPlaintiff();
				loadRealEstate();
				loadTax();
				loadTitle();
				loadWillsEstate();
			}else if(page=='PredecessorFirmSupplement' || page=='feesuitSupplement'  ||page=='AttorneyEstimateForm'  || page=='claimSupplement' || page=='LicensedStates' || page=='changelostworkq'){
				$('.datepicker').datepicker();
				setEwrp();
				loadFeeSupplement();
				loadAttorneyLicensedStates();
				loadClaimSupplement();
				loadAttorneyEstimateForm();
			}	
			}catch (e) {
	            enableBody();
				// TODO: handle exception
			}
            enableBody();
		}
			
		

	}
//----------------------end of load page--------------------------------------------------------	
//-------------------------------------------------------------------------------------------------------------------


/***************** ajax page validation *********************************************/
  
  var req;
  var url ="*.do";
  var set_af_1;
  var set_method;
  var set_dynaKeys;
  var set_dynaValues;
  var set_inet_page;
  var inet_ajax_form ="Y";
  var set_div_id = "ajax_errors_list";
  var set_form_containerid;
 
  function resolveControlValue(fieldVal){
  
  	if(fieldVal){
  		
  		if(fieldVal.indexOf("&") != -1){  		
  			fieldVal = fieldVal.replace(/&/gi,"$AND$"); 
  			}
  		if(fieldVal.indexOf("%") != -1){
  			fieldVal = fieldVal.replace(/%/gi,"$PER$");
  			}
  	}
  	return fieldVal;
  }	
  
    
  function parseElement(obj){
      var getstr="";       
      var objarr = new Array();
      objarr = obj.elements;

      for (i=0; i<objarr.length; i++){
      var node = objarr[i];                       
      if (node.tagName == "INPUT"){   
      var id = node.id;
      if(id.match('ajax_field')){
                      if (node.type == "text"){
                      getstr = getstr+ node.name + "=" + resolveControlValue(node.value) + "&";                      
      }else if (node.type == "checkbox"){
                      if (node.checked) {
                                      getstr = getstr+ node.name + "=" + resolveControlValue(node.value) + "&";
                                      //alert(getstr);
                      } else {
                                      getstr = getstr+ node.name + "=&";
                                      //alert(getstr+"3333");
                      }
      }else if (node.type == "radio"){                 
                      if (node.checked){
                                      getstr = getstr+ node.name + "=" + resolveControlValue(node.value) + "&";
                      }
      }else if (node.type == "hidden"){
                      getstr = getstr+ node.name + "=" + resolveControlValue(node.value) + "&";                 
      }else if (node.type == "password"){
                      getstr = getstr+ node.name + "=" + resolveControlValue(node.value) + "&";                 
      }  
}    
}else if (node.tagName == "SELECT"){
      var id = node.id;
      //alert(id + ' == ' + node.getAttribute("multiple"));
      if(id.match('ajax_field')){
                      //alert(node.options[node.selectedIndex]);
                      //alert(node.selectedIndex);
                      if(node.selectedIndex >=0){
                                      if(node.getAttribute("multiple") == null || !node.getAttribute("multiple")=='multiple')
                                      getstr = getstr+ node.name + "=" + resolveControlValue(node.options[node.selectedIndex].value) + "&";
                      else
                                      getstr = getstr+ node.name + "=" + resolveControlValueForMultipleSelect(node) + "&";
      }
}
}else if (node.tagName == "TEXTAREA"){
      var id = node.id;
      if(id.match('ajax_field')){
      getstr = getstr+ node.name + "=" + resolveControlValue(node.value) + "&";
}
} 
} 

return getstr;
}

  
  function parseAjax(element_id)
  {
  	return parseElement(element_id);  	 	
  }  
  
  	function parseDefaultAjax()
 	{
 		var str = "dynaKeys="+encodeRequestParam(set_dynaKeys)+"&dynaValues="+encodeRequestParam(set_dynaValues)+"&inet_method="+encodeRequestParam(set_method)+"&inet_page="+encodeRequestParam(set_inet_page)+"&inet_ajax_form="+encodeRequestParam(inet_ajax_form);
 		return str;
 	}
 

   function buildSubmitUrl(fr_id,form_containerid)
  {
  	var submitUrl =  parseElement(fr_id);  
  	submitUrl  =submitUrl +   "dynaKeys="+encodeRequestParam(set_dynaKeys)+"&dynaValues="+encodeRequestParam(set_dynaValues)+"&datadisplayid="+encodeRequestParam(form_containerid) + "&inet_method="+encodeRequestParam(set_method)+"&inet_page="+encodeRequestParam(set_inet_page)+"&inet_ajax_submit_form="+encodeRequestParam(inet_ajax_form);
  	return submitUrl; 	
  }  
  
 	function setFormContent(af_1, method, as_dynaKeys, as_dynaValues, action, form_containerid)
  	{
  		af_1.dynaKeys.value = as_dynaKeys;
      	af_1.dynaValues.value = as_dynaValues;
      	af_1.inet_method.value = method;
      	af_1.inet_page.value= action;  
      	     	
  		set_af_1 = af_1;
  		set_method = method;
  		set_dynaKeys = as_dynaKeys;
  		set_dynaValues = as_dynaValues;   		
  		set_inet_page = action;  
  		set_form_containerid = 	form_containerid;	
  		inet_ajax_form ="Y";
  		url ="*.do";
  	}
 	
	function managesubmitformajax(af_1, method, as_dynaKeys, as_dynaValues, action, form_containerid)
	{  
		//alert("java");
		setFormContent(af_1, method, as_dynaKeys, as_dynaValues, action, form_containerid);
  		processFormAjax(af_1);
	}  
 	
	function processFormAjax(form)
  	{
  		
	    if (window.XMLHttpRequest)
	    { 
	      // Non-IE browsers	
	      req = new XMLHttpRequest();	      
	    } 
	    else if (window.ActiveXObject) 
	    { 
		  // IE
		  req = new ActiveXObject("Microsoft.XMLHTTP");	
    	}
    	
    	if (req)
	    {	 	
			var param = parseAjax(form);
	        param += parseDefaultAjax();	
	        var currentFormSerializeObj = $.trim($("form").find("select,textarea, input").not('[type="hidden"]').not('[readonly="readonly"]').not('[disabled="disabled"]').serialize());
      	    var isDataChange = "";
      	    if(currentFormSerializeObj != existingFormSerializeObj){
      	   	 isDataChange = "Y";
      	    } else {
      	   	 isDataChange = "N";
      	    }
      	  param = param + "&isDataChange=" + isDataChange;
	      // alert(param + "--- " + form.jsessionid);
	       //Added Session Handling Code 
	       
	        if(url == '*.do'){
	       		url = url + ";jsessionid="+form.jsessionid.value;		
	        }
	        
	        //Ended Code
	       //alert(url);
	           
	        req.onreadystatechange = processRequest; 
	        req.open("POST", url, true);
			req.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");		
	        req.send(param);
 	    }	
  	}
  	
  	function processRequest()
  	{
//  		debugger;
	  	if (req.readyState == 4)
	    { 
	    	// Complete
	  		
	      if (req.status == 200) 
	      { 
	      	// OK response
	      	var result = req.responseText;	 
	      	
	      	//alert('result : ' + result)     	
	      	if(result != "")
	        {
	        	//document.getElementById(set_div_id).innerHTML = result;
	        	enableBody();
	        	populaterrorinajaxform(result);
	        		        	
	        }
			else
			{			
				//set_af_1.submit();
				manageajaxsubmitform(set_af_1,set_method,set_dynaKeys,set_dynaValues,set_inet_page,set_form_containerid);
			}
	      }
	      else
	      {
	      	alert("Problem: " + req.statusText);
	      }
	    }
  	}
  	
  	function populaterrorinajaxform(result)
	{	
	//alert(result);
		var errorArr = new Array(); 
		errorArr = result.split('|');
		
		var objarr = document.getElementsByTagName("div");
  	 	//alert(errorArr.length);
	  	 for (i=0; i<objarr.length; i++)
	  	 {      	
	      	 var div_element = objarr[i];
	      	 var div_id = div_element.id;        	
		     if(div_id.match('ajax_error_'))
		     {     	
				div_element.innerHTML = '';
				//document.getElementById('tr_'+div_id).style.display='none';			
			 }    
		 }
		
		var is_inet_error = 'N';
		
		for (i=0; i<=errorArr.length-1; i++){
			var error=errorArr[i];
			var colonIndex = error.indexOf(':');
			var fld = null;
			if(error.indexOf('ajaxerrors') == -1){
				fld = error.substring(0,colonIndex);
			}else{	
				fld = error.substring(10,colonIndex);
			}
				
			var msg = error.substring(colonIndex+1);
			//alert(msg);alert(fld);
			//alert(fld +"...."+msg);
			//document.getElementById('tr_ajax_error_'+fld).style.display='block';
			//alert(fld);
			if(document.getElementById('ajax_error_'+fld) != null)
			{	
				is_inet_error = 'Y';
				if(document.getElementById('error_'+fld) != null)
					document.getElementById('error_'+fld).innerHTML = '';
				document.getElementById('ajax_error_'+fld).innerHTML = msg;
				document.getElementById('ajax_error_'+fld).style.display='block';
			}
			if(fld == "ZohoError"){
				showPopUpOverloaded('zohodata','500','400');
			}
		}
		
		if(is_inet_error='Y')
		{			
			if(document.getElementById('ajax_error_indication_div') != null)
			{
				document.getElementById('ajax_error_indication_div').innerHTML = 'Please correct the errors highlighted in red below';
				document.getElementById('ajax_error_indication_div').style.display='block';
			}
		}
		
		enableBody();
			
	}
  	
  	function manageajaxsubmitform(frm,  inetmethod , dynakeys, dynavalues,page,containerid)
	{
	 // alert('33');
		//var page_request ;
		 // alert( frm+'--'+ inetmethod +'----'+ dynakeys+'--------'+dynavalues+'------'+containerid)
		var queryst = buildSubmitUrl(frm,containerid);
		disableBody();
		
		//alert(document.getElementById("append_IsFirmCarryingProfLiabilityIns").value);
		 
		
		// alert(queryst);
		  // divhideshow (containerid);
		 
//		 if(document.getElementById("LimitKey_append"))
//		{
//			var node = document.getElementById("LimitKey_append");
//			queryst = queryst+"&"+ node.name + "=" + node.options[node.selectedIndex].value ;
//		//	alert(queryst);
//		}
		
		var currentFormSerializeObj = $.trim($("form").find("select,textarea, input").not('[type="hidden"]').not('[readonly="readonly"]').not('[disabled="disabled"]').serialize());
  	    var isDataChange = "";
  	    if(currentFormSerializeObj != existingFormSerializeObj){
  	   	 isDataChange = "Y";
  	    } else {
  	   	 isDataChange = "N";
  	    }
  	  queryst = queryst + "&isDataChange=" + isDataChange;
		
		 
		var url = queryst;
		//alert(url + " --- " + page);
	  //  alert(containerid);
	     // alert(url);
    	retrieveURL1( url, containerid, req,page,frm.jsessionid.value,inetmethod);
    	
    	if(containerid=='ajax_sorting'){
			changeStatusColor(); 
		 }
	}
  	

/*************************end of ajax validation************************************/

//---------------submit form for Menu-----------------------------------------------
	function submitFormMenu(af_1, method, as_dynaKeys, as_dynaValues, action, ex_dynaKey, ex_dynaVal)
	{
      af_1.dynaKeys.value = as_dynaKeys + ex_dynaKey;
      af_1.dynaValues.value = as_dynaValues + ex_dynaVal;
      af_1.inet_method.value = method;
      af_1.inet_page.value= action;
      af_1.method = "post";
      
      af_1.submit();
	}
//---------------------------end of submit form for menu-----------------------------

//--------------------------------------------------------------------------------------------------------------------
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
//----------------------------------------------------------------------------------------------------------------------------------

 
//------------------------------------delete confirmation-------------------------------------------------
	function delete_this()
	{
 		var answer = confirm("Do you really want to delete this ??");
	}
//----------------------------------------------------------------------------------------------------
	
//-------------------------------------------------------------------------------
	


//---------------------------------------------rule------------------------------------------

var globalCursorPos; // global variabe to keep track of where the cursor was
//sets the global variable to keep track of the cursor position
function setCursorPos() {
 globalCursorPos = getCursorPos(document.forms[0].expression);
 //alert("ss");
}

//This function returns the index of the cursor location in
//the value of the input text element
//It is important to make sure that the sWeirdString variable contains
//a set of characters that will not be encountered normally in your
//text
function getCursorPos(textElement) {
 //save off the current value to restore it later,
 var sOldText = textElement.value;

//create a range object and save off it's text
 var objRange = document.selection.createRange();
 var sOldRange = objRange.text;

//set this string to a small string that will not normally be encountered
 var sWeirdString = '#%~';

//insert the weirdstring where the cursor is at
 objRange.text = sOldRange + sWeirdString; objRange.moveStart('character', (0 - sOldRange.length - sWeirdString.length));

//save off the new string with the weirdstring in it
 var sNewText = textElement.value;

//set the actual text value back to how it was
 objRange.text = sOldRange;
setTextAreasOnFocus();
//look through the new string we saved off and find the location of
//the weirdstring that was inserted and return that value
 for (i=0; i <= sNewText.length; i++) {
   var sTemp = sNewText.substring(i, i + sWeirdString.length);
   if (sTemp == sWeirdString) {
     var cursorPos = (i - sOldRange.length);
     return cursorPos;
   }
 }
}

//this function inserts the input string into the textarea
//where the cursor was at
function insertString(stringToInsert) {
 
 var firstPart = document.forms[0].expression.value.substring(0, globalCursorPos);
 var secondPart = document.forms[0].expression.value.substring(globalCursorPos, document.forms[0].expression.value.length);
  document.forms[0].expression.focus();
 document.forms[0].expression.value = firstPart + stringToInsert + secondPart;
}
function setTextAreasOnFocus() {
/***
 * This function will force the cursor to be positioned
 * at the end of all textareas when they receive focus.
 */
    var textAreas = document.getElementsByTagName('expression');

    for(var i = 0; i < textAreas.length; i++) {
        textAreas[i].onfocus = function() {
            setCaretPosition(this.id, this.value.length);
        }
    }

    textAreas = null;
}




function showRuleset(ruleset){	
	
	if(ruleset.value == '0')
	{
		showDivArea(['divOtherRuleset'], ['divOtherRulesethide']);
		
	} else {
		showDivArea(['divOtherRulesethide'], ['divOtherRuleset']);
		document.forms[0].otherRuleSet.value='';
	}
}

function clearContents(){
	document.forms[0].DocTitle.value='';
	document.forms[0].Comment.value='';
	document.forms[0].file_path.value='';
}

function getCheckedStates()
		{
		 	
		 	var formObject = document.forms[0];
		 	var states_val= "";
		 	
			  for (var i=0; i<formObject.length; i++)
		      {
		      		
		            var n = formObject.elements[i].name;
		            if (n.indexOf("StateDesc_") > -1)		            
		            {
		            	if (formObject.elements[i].checked){
		            		if(states_val == ""){
		            			states_val = formObject.elements[i].value ;
		            		}else{		            			            		
		            			states_val = states_val + ", " + formObject.elements[i].value ;
		            			}
		            	}
		           	}
		      }
		      return states_val;
		}

function post_value(){	
	
			//alert(opener.document.forms[0].LicensedStates.value);					
			//opener.document.forms[0].LicensedStates.value = getCheckedStates();
			//self.close();
	//alert(getCheckedStates())
	//alert(document.getElementById('ajax_field_LicensedStates'))
	document.getElementById('ajax_field_LicensedStates').value=getCheckedStates();
	//alert(document.getElementById('ajax_field_LicensedStates').value)
}

function suggestOver(div_value) {
			div_value.className = 'suggest_link_over';
}
			//Mouse out function
function suggestOut(div_value) {
			div_value.className = 'suggest_link';
}

			//Click function
function setSearch(value,containerid,fieldid) {

			if(value != null){
			value = value.replace(/&amp;/g,'&');
			value = value.replace(/&nbsp;/g,' ');
			//alert(value);
			document.getElementById(fieldid).value = value;
			document.getElementById(containerid).innerHTML = '';
			document.getElementById(containerid).style.display='none';
			document.getElementById(fieldid).focus();
			}
}

function validateRightClickMenu(url, containerid, page_request, page, jsessionid, inet_method, isErrorFound, frm, dynakeys,dynavalues){
	try{ 
		if (window.XMLHttpRequest){ // if Mozilla, Safari etc
			//alert('33');
			page_request = new XMLHttpRequest();
		}else if (window.ActiveXObject){// if IE
			try {
				page_request = new ActiveXObject("Msxml2.XMLHTTP");
			}catch (e){
				try{
					page_request = new ActiveXObject("Microsoft.XMLHTTP");
				}catch (e){ alert(e);}
			}
		}else{
			return false;
		}
		
		//alert(containerid);
		page_request.onreadystatechange=function(){
			if(page_request.readyState == 4 && ((page_request && page_request.status==200) || window.location.href.indexOf("http")==-1)){
		    	if(page_request.responseText != null && page_request.responseText.length >= 10){
					result = page_request.responseText; 
					enableBody();
					populaterrorinajaxform(result);
					isErrorFound = true;
					return isErrorFound;
				}
				
				if(!isErrorFound){
					//alert('No error found')
		    		// hidding the div based on the container id
		    		
		    		
		    		submitUrl = parseElement(document.forms[0]);
		    		
		    		if(!dynakeys.match('isskiprightclickmenucancel')) 
		    			divhideshow(containerid);
		    			
		    		disableBody(); 
		    		queryst = submitUrl; 
		  			queryst = queryst+'dynaKeys='+encodeRequestParam(dynakeys+'ajaxpage|')+'&dynaValues='+encodeRequestParam(dynavalues +'ajaxpage|')+'&ajaxpage='+encodeRequestParam(page) + '&inet_method='+encodeRequestParam(inet_method) + '&inet_page='+encodeRequestParam(page) + '&inet_skip_validation=Y';
		  			var url = queryst;
		  			 
		    		retrieveURL1(url, containerid, page_request, page, frm.jsessionid.value, inet_method);
				}
			}
		}
		
		//alert(url);
		//Added Session Handling Code
		var doVar = "test.do";
		//alert(jsessionid);
		if(jsessionid != null){
			doVar = doVar + ";jsessionid="+jsessionid;
		}
		
		page_request.open('POST', doVar, true);
		//Ended Code
		page_request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");	
			
		page_request.send(url);
		
		//alert('After -- ' + isErrorFound);
		return isErrorFound;
	}catch (e){ 
		alert(e);
	}
}
function getCheckedQuotations()
{
 	var formObject = document.forms[0];
 	var IsQuoteSelected= "";
 	
	  for (var i=0; i<formObject.length; i++)
      {
      		
            var n = formObject.elements[i].name;
            if (n.indexOf("IsQuoteSelected_") > -1)		            
            {
            	if (formObject.elements[i].checked){
            		if(IsQuoteSelected == ""){
            			IsQuoteSelected = formObject.elements[i].value ;
            		}else{		            			            		
            			IsQuoteSelected = IsQuoteSelected + ", " + formObject.elements[i].value ;
            			}
            	}
           	}
      }
	  //alert(IsQuoteSelected);
      return IsQuoteSelected;
}

function clearPageMsgOrPageErrorOnAjaxCall(){
	//alert("page : "  + page);
	if(document.getElementById("pageMsg") != null){
		document.getElementById('pageMsg').innerHTML = '';
	}
	if(document.getElementById("pageMsg1") != null){
		document.getElementById('pageMsg1').innerHTML = '';
	}
	if(document.getElementById("ajax_pageMsg3") != null){
		document.getElementById('ajax_pageMsg3').innerHTML = '';
	}
	if(document.getElementById("pageError") != null){
		document.getElementById('pageError').innerHTML = '';
	}
	if(document.getElementById("pageError1") != null){
		document.getElementById('pageError1').innerHTML = '';
	}
	if(document.getElementById("pageError23") != null){
		document.getElementById('pageError23').innerHTML = '';
	}
	
	var fldArray = document.getElementsByTagName('div');
	for (var i = 0; i < fldArray.length; i++) {
		var fld = fldArray[i];
		if(fld == null)
			continue;
		
		if(fld != null && fld.id != null){	
			var id = fld.id;
			//alert(id)
			if(id.indexOf("ajax_error_")>-1){
				//alert(id)
				document.getElementById(id).innerHTML='';
			}
		}
	}		
}
