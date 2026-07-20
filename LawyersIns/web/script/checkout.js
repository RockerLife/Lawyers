function andDoneOnLoad() {
	try{
		
		var paymentIntentToken = $.trim($("#isvalidid").val());
		//alert("Token   " + paymentIntentToken);
		console.log("Token  " + paymentIntentToken);
		
		let pluginOption = {
			theme: "classic",  // classic, modern, minimal, vibrant
			container: "mywidget",  // id of the div to render the widget
		}
	
		// Initialize the plugin with token and options
	
		//let andDone = new AndDone(paymentIntentToken);
	
		let andDone = new AndDone(paymentIntentToken, pluginOption);
	
	
		// Using onload function to perform some action after the widget is loaded  and ready
		andDone.onload = (plugin) => {
	
			console.log("Widget loaded successfully");
			getAndDoneResult();
		}
	} catch(e){
		console.log('error', error);
	}
	
}

function getAndDoneResult(){
	var url = window.location.href;
	
	url = url.replace("paymentmethod.do","AndDoneResponse");
	//alert(url)
	var result = "";
	var responseTime = setInterval(function () {
		jQuery.ajax({
			url: url,
			type: 'POST',
			success: function(data) {
				//alert(data);
				result = data;
				if("DOAGAIN" == data){
					
				} else if("SUCCESS" == data) {
    				clearInterval(responseTime);
					clearInterval(sessionTimeOut);
    				andDonePaymentSuccess();
				} else if("ERROR" == data) {
    				clearInterval(responseTime);
					clearInterval(sessionTimeOut);
    				andDonePaymentFailure();
				}
			}
		});
    },5000);
    
    
	var sessionTimeOut = setInterval(function () {
		clearInterval(responseTime);
		clearInterval(sessionTimeOut);
		if("DOAGAIN" == result){
			showPopUpOverloaded('andDoneSessionTimeOut','500','400');
		}
    },185000);
    
}

function andDonePaymentSuccess(){
	//alert("d")
	showPopUpOverloaded('andDoneSuccessFull','500','400');
	disableBody();
	window.location.href = $('#andDonePaymentSuccess').attr('href');
}

function andDonePaymentFailure(){
	//alert("d")
	showPopUpOverloaded('andDonePaymentFailureOut','500','400');
}

function onExistButtonClick(href){
	disableBody();
	var url = window.location.href;
	
	url = url.replace("paymentmethod.do","AndDoneResponse");
	jQuery.ajax({
		url: url,
		type: 'POST',
		success: function(data) {
			//alert(data);
			result = data;
			if("SUCCESS" == data) {
				window.location.href = $('#andDonePaymentSuccess').attr('href');
			} else if("ERROR" == data) {
				window.location.href = $('#' + href).attr('href');
			} else if("DOAGAIN" == data){
				window.location.href = $('#' + href).attr('href');
			}
		}
	});
}