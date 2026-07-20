window.history.forward();
	
//-------------manage submit form start--------------------------------------
function managetestharness(dynaKeys, dynaValues, method, action){
	//alert('call')
	//alert(document.forms[0].dynaKeys + '  ' + dynaKeys)
	document.forms[0].dynaKeys.value = dynaKeys;
	//alert(document.forms[0].dynaValues)
	document.forms[0].dynaValues.value = dynaValues;
	//alert(document.forms[0].inet_method)
	document.forms[0].inet_method.value = method;
	//alert(document.forms[0].dynaValues)
	document.forms[0].inet_page.value= action;
	
	document.forms[0].target = "_self";
	
	if(method=='INET_SHOW_TESTHARNESS_PAGE' || method=='INET_SHOW_QUERY_TESTHARNESS_PAGE' || method=='INET_SHOW_LOGS_TESTHARNESS_PAGE' ||
			method=='INET_SHOW_INTEGRATION_TESTHARNESS_PAGE' || method=='INET_SHOW_SESSION_TESTHARNESS_PAGE' ||
			method=='INET_SHOW_ERRORS_TESTHARNESS_PAGE' || method == 'INET_SHOW_STACK_TESTHARNESS_PAGE'){
		document.forms[0].target = "_blank";
		document.forms[0].submit();
	}else{
		document.forms[0].target = "_self";
		document.forms[0].submit();
	}
}