$(document).ready(function() {
	
		
	$('#xaddAttorneys').change(function() {
        $('#rowMake').html('');
        for (var i = 0; i<parseInt($(this).val()); i++) {
            var row = '<tr>\
                <td>\
                    <input type="text" class="grid-form-control-90" />\
                </td>\
                <td>\
                    <select>\
					  <option value="A">Select</option>\
					  <option value="A">A</option>\
					  <option value="E">E</option>\
					  <option value="IC">IC</option>\
					  <option value="OC">OC</option>\
					  <option value="P">P</option>\
					  <option value="RP">RP</option>\
					  <option value="O">O</option>\
					  <option value="S">S</option>\
					</select>        \
                </td>\
				<td><select >\
				  <option value="Select">Select</option>\
				  <option value="AL">AL</option>\
				  <option value="AK">AK</option>\
				  <option value="AZ">AZ</option>\
				  <option value="AR">AR</option>\
				  <option value="CA">CA</option>\
				  <option value="CO">CO</option>\
				  <option value="CT">CT</option>\
				  <option value="DE">DE</option>\
				  <option value="FL">FL</option>\
				  <option value="GA">GA</option>\
				  <option value="HI">HI</option>\
				  <option value="ID">ID</option>\
				  <option value="IL">IL</option>\
				  <option value="IN">IN</option>\
				  <option value="IA">IA</option>\
				  <option value="KS">KS</option>\
				  <option value="KY">KY</option>\
				  <option value="LA">LA</option>\
				  <option value="ME">ME</option>\
				  <option value="MD">MD</option>\
				  <option value="MA">MA</option>\
				  <option value="MI">MI</option>\
				  <option value="MN">MN</option>\
				  <option value="MS">MS</option>\
				  <option value="MO">MO</option>\
				  <option value="MT">MT</option>\
				  <option value="NE">NE</option>\
				  <option value="NV">NV</option>\
				  <option value="NH">NH</option>\
				  <option value="NJ">NJ</option>\
				  <option value="NM">NM</option>\
				  <option value="NY">NY</option>\
				  <option value="NC">NC</option>\
				  <option value="ND">ND</option>\
				  <option value="OH">OH</option>\
				  <option value="OK">OK</option>\
				  <option value="OR">OR</option>\
				  <option value="PA">PA</option>\
				  <option value="RI">RI</option>\
				  <option value="SC">SC</option>\
				  <option value="SD">SD</option>\
				  <option value="TN">TN</option>\
				  <option value="TX">TX</option>\
				  <option value="UT">UT</option>\
				  <option value="VT">VT</option>\
				  <option value="VA">VA</option>\
				  <option value="WA">WA</option>\
				  <option value="WV">WV</option>\
				  <option value="WI">WI</option>\
				  <option value="WY">WY</option>\
				</select></td>\
				<td><input type="text" /></td>\
				<td nowrap><input type="text" style="width: 80px" /><a class="datePicker"><img src="img/icon_calender.gif" /></a></td>\
				<td nowrap><input type="text" style="width: 80px" /><a class="datePicker"><img src="img/icon_calender.gif" /></a></td>\
				<td><input type="text" /></td>\
            </tr>';
            row = $(row);
            $('#rowMake').append(row);
        }
    });
	
	
	
	
		$("#import").click(function(e) {
			$("#Confirmation").show();	    
		});
		
		$("#search").click(function(e) {
			$("#searchResult").show();	    
		});
		$("#addSearch").click(function(e) {
			$("#row2, #row3, #row4, #row5").show();	    
		});
		
	$('.question tbody tr').click(function(){
         $(this).addClass("selected").siblings().removeClass('selected');	    
    });
	
	$('.question tbody tr.no_bg').click(function(){
         $(this).removeClass('selected');	    
    });
	
	
		
		$('#addAttorneys').on('change', function() {
      if ( this.value == '0')
      //.....................^.......
      {
        $("#row1").show();
      }
	  else
	  {
		$("#row2").hide();
		$("#row3").hide();
		$("#row4").hide();
		$("#row5").hide();
		}
	  if ( this.value == '1')
      //.....................^.......
      {
        $("#row1").show();
      }
	  else
	  {
		$("#row2").hide();
		$("#row3").hide();
		$("#row4").hide();
		$("#row5").hide();
		}
     
	 if ( this.value == '2')
      //.....................^.......
      {
        $("#row1").show();
		$("#row2").show();
      }
	  else
	  {
		$("#row3").hide();
		$("#row4").hide();
		$("#row5").hide();
		}
	if ( this.value == '3')
      //.....................^.......
      {
        $("#row1").show();
		$("#row2").show();
		$("#row3").show();
      }
	  else
	  {
		$("#row4").hide();
		$("#row5").hide();
		}
	if ( this.value == '4')
      //.....................^.......
      {
        $("#row1").show();
		$("#row2").show();
		$("#row3").show();
		$("#row4").show();
		
      }
	  else
	  {
		$("#row5").hide();
		}
	if ( this.value == '5')
      //.....................^.......
      {
        $("#row1").show();
		$("#row2").show();
		$("#row3").show();
		$("#row4").show();
		$("#row5").show();
      }
    });
});

function showDivArea(areas_show, areas_hide) {
	for (var i = 0; i < areas_show.length; i++) {
		if (document.getElementById(areas_show[i]) != null) {
			ge = document.getElementById(areas_show[i]);
			ge.style.display = "block";
		}
	}
	for (var i = 0; i < areas_hide.length; i++) {
		if (document.getElementById(areas_hide[i]) != null) {
			gee = document.getElementById(areas_hide[i]);
			gee.style.display = "none";
		}
	}
}

function showPopUp(el) {
	var w = (screen.width-434)/2;
    var t = (screen.height-450)/2;
    var cvr = document.getElementById("cover");
    var dlg = document.getElementById(el);
    var ss = document.getElementById("StateCode");
    cvr.style.display = "block";
    dlg.style.display = "block";
    dlg.style.left =w;
    dlg.style.top =t;
    document.getElementById("cover").style.height =document.body.scrollHeight;
	document.getElementById("cover").focus();
	document.body.style.overflowY = "hidden"
}
function closePopUp(el) {
	var cvr = document.getElementById("cover")
	var dlg = document.getElementById(el)
	cvr.style.display = "none"
	dlg.style.display = "none"
	document.body.style.overflowY = "scroll"
}


// function start for agency admin dashboard request type select box......

function showDivAreaOptions(id){
	var value=id.value;
	if(value==1){
					document.getElementById("div1Y").style.display = "block";
					document.getElementById("div2Y").style.display = "none";
					document.getElementById("div3Y").style.display = "none";
					document.getElementById("div4Y").style.display = "none";
					document.getElementById("div5Y").style.display = "none";
					document.getElementById("div6Y").style.display = "none";
					document.getElementById("div7Y").style.display = "none";
					document.getElementById("div8Y").style.display = "none";
					document.getElementById("div9Y").style.display = "none";
					document.getElementById("div10Y").style.display = "none";
					document.getElementById("div11Y").style.display = "none";
					document.getElementById("div12Y").style.display = "none";
					document.getElementById("div13Y").style.display = "none";
					document.getElementById("div14Y").style.display = "none";
					
					
	}
	if(value==2){
					document.getElementById("div1Y").style.display = "none";
					document.getElementById("div2Y").style.display = "block";
					document.getElementById("div3Y").style.display = "none";
					document.getElementById("div4Y").style.display = "none";
					document.getElementById("div5Y").style.display = "none";
					document.getElementById("div6Y").style.display = "none";
					document.getElementById("div7Y").style.display = "none";
					document.getElementById("div8Y").style.display = "none";
					document.getElementById("div9Y").style.display = "none";
					document.getElementById("div10Y").style.display = "none";
					document.getElementById("div11Y").style.display = "none";
					document.getElementById("div12Y").style.display = "none";
					document.getElementById("div13Y").style.display = "none";
					document.getElementById("div14Y").style.display = "none";
								 
	}
	if(value==3){
					document.getElementById("div1Y").style.display = "none";
					document.getElementById("div2Y").style.display = "none";
					document.getElementById("div3Y").style.display = "block";
					document.getElementById("div4Y").style.display = "none";
					document.getElementById("div5Y").style.display = "none";
					document.getElementById("div6Y").style.display = "none";
					document.getElementById("div7Y").style.display = "none";
					document.getElementById("div8Y").style.display = "none";
					document.getElementById("div9Y").style.display = "none";
					document.getElementById("div10Y").style.display = "none";
					document.getElementById("div11Y").style.display = "none";
					document.getElementById("div12Y").style.display = "none";
					document.getElementById("div13Y").style.display = "none";
					document.getElementById("div14Y").style.display = "none";
							   
	}
	if(value==4){
					document.getElementById("div1Y").style.display = "none";
					document.getElementById("div2Y").style.display = "none";
					document.getElementById("div3Y").style.display = "none";
					document.getElementById("div4Y").style.display = "block";
					document.getElementById("div5Y").style.display = "none";
					document.getElementById("div6Y").style.display = "none";
					document.getElementById("div7Y").style.display = "none";
					document.getElementById("div8Y").style.display = "none";
					document.getElementById("div9Y").style.display = "none";
					document.getElementById("div10Y").style.display = "none";
					document.getElementById("div11Y").style.display = "none";
					document.getElementById("div12Y").style.display = "none";
					document.getElementById("div13Y").style.display = "none";
					document.getElementById("div14Y").style.display = "none";
					
				
	}
	if(value==5){
					document.getElementById("div1Y").style.display = "none";
					document.getElementById("div2Y").style.display = "none";
					document.getElementById("div3Y").style.display = "none";
					document.getElementById("div4Y").style.display = "none";
					document.getElementById("div5Y").style.display = "block";
					document.getElementById("div6Y").style.display = "none";
					document.getElementById("div7Y").style.display = "none";
					document.getElementById("div8Y").style.display = "none";
					document.getElementById("div9Y").style.display = "none";
					document.getElementById("div10Y").style.display = "none";
					document.getElementById("div11Y").style.display = "none";
					document.getElementById("div12Y").style.display = "none";
					document.getElementById("div13Y").style.display = "none";
					document.getElementById("div14Y").style.display = "none";
	   
	}
	if(value==6){
					document.getElementById("div1Y").style.display = "none";
					document.getElementById("div2Y").style.display = "none";
					document.getElementById("div3Y").style.display = "none";
					document.getElementById("div4Y").style.display = "none";
					document.getElementById("div5Y").style.display = "none";
					document.getElementById("div6Y").style.display = "block";
					document.getElementById("div7Y").style.display = "none";
					document.getElementById("div8Y").style.display = "none";
					document.getElementById("div9Y").style.display = "none";
					document.getElementById("div10Y").style.display = "none";
					document.getElementById("div11Y").style.display = "none";
					document.getElementById("div12Y").style.display = "none";
					document.getElementById("div13Y").style.display = "none";
					document.getElementById("div14Y").style.display = "none";
		
	}
	if(value==7){
					document.getElementById("div1Y").style.display = "none";
					document.getElementById("div2Y").style.display = "none";
					document.getElementById("div3Y").style.display = "none";
					document.getElementById("div4Y").style.display = "none";
					document.getElementById("div5Y").style.display = "none";
					document.getElementById("div6Y").style.display = "none";
					document.getElementById("div7Y").style.display = "block";
					document.getElementById("div8Y").style.display = "none";
					document.getElementById("div9Y").style.display = "none";
					document.getElementById("div10Y").style.display = "none";
					document.getElementById("div11Y").style.display = "none";
					document.getElementById("div12Y").style.display = "none";
					document.getElementById("div13Y").style.display = "none";
					document.getElementById("div14Y").style.display = "none";
			
	}
	if(value==8){
					document.getElementById("div1Y").style.display = "none";
					document.getElementById("div2Y").style.display = "none";
					document.getElementById("div3Y").style.display = "none";
					document.getElementById("div4Y").style.display = "none";
					document.getElementById("div5Y").style.display = "none";
					document.getElementById("div6Y").style.display = "none";
					document.getElementById("div7Y").style.display = "none";
					document.getElementById("div8Y").style.display = "block";
					document.getElementById("div9Y").style.display = "none";
					document.getElementById("div10Y").style.display = "none";
					document.getElementById("div11Y").style.display = "none";
					document.getElementById("div12Y").style.display = "none";
					document.getElementById("div13Y").style.display = "none";
					document.getElementById("div14Y").style.display = "none";
		
	}
	if(value==9){
					document.getElementById("div1Y").style.display = "none";
					document.getElementById("div2Y").style.display = "none";
					document.getElementById("div3Y").style.display = "none";
					document.getElementById("div4Y").style.display = "none";
					document.getElementById("div5Y").style.display = "none";
					document.getElementById("div6Y").style.display = "none";
					document.getElementById("div7Y").style.display = "none";
					document.getElementById("div8Y").style.display = "none";
					document.getElementById("div9Y").style.display = "block";
					document.getElementById("div10Y").style.display = "none";
					document.getElementById("div11Y").style.display = "none";
					document.getElementById("div12Y").style.display = "none";
					document.getElementById("div13Y").style.display = "none";
					document.getElementById("div14Y").style.display = "none";
					
	}
	if(value==10){
					document.getElementById("div1Y").style.display = "none";
					document.getElementById("div2Y").style.display = "none";
					document.getElementById("div3Y").style.display = "none";
					document.getElementById("div4Y").style.display = "none";
					document.getElementById("div5Y").style.display = "none";
					document.getElementById("div6Y").style.display = "none";
					document.getElementById("div7Y").style.display = "none";
					document.getElementById("div8Y").style.display = "none";
					document.getElementById("div9Y").style.display = "none";
					document.getElementById("div10Y").style.display = "block";
					document.getElementById("div11Y").style.display = "none";
					document.getElementById("div12Y").style.display = "none";
					document.getElementById("div13Y").style.display = "none";
					document.getElementById("div14Y").style.display = "none";
			
	}
	if(value==11){
					document.getElementById("div1Y").style.display = "none";
					document.getElementById("div2Y").style.display = "none";
					document.getElementById("div3Y").style.display = "none";
					document.getElementById("div4Y").style.display = "none";
					document.getElementById("div5Y").style.display = "none";
					document.getElementById("div6Y").style.display = "none";
					document.getElementById("div7Y").style.display = "none";
					document.getElementById("div8Y").style.display = "none";
					document.getElementById("div9Y").style.display = "none";
					document.getElementById("div10Y").style.display = "none";
					document.getElementById("div11Y").style.display = "block";
					document.getElementById("div12Y").style.display = "none";
					document.getElementById("div13Y").style.display = "none";
					document.getElementById("div14Y").style.display = "none";
			
	}
	if(value==12){
					document.getElementById("div1Y").style.display = "none";
					document.getElementById("div2Y").style.display = "none";
					document.getElementById("div3Y").style.display = "none";
					document.getElementById("div4Y").style.display = "none";
					document.getElementById("div5Y").style.display = "none";
					document.getElementById("div6Y").style.display = "none";
					document.getElementById("div7Y").style.display = "none";
					document.getElementById("div8Y").style.display = "none";
					document.getElementById("div9Y").style.display = "none";
					document.getElementById("div10Y").style.display = "none";
					document.getElementById("div11Y").style.display = "none";
					document.getElementById("div12Y").style.display = "block";
					document.getElementById("div13Y").style.display = "none";
					document.getElementById("div14Y").style.display = "none";
			
	}
	if(value==13){
					document.getElementById("div1Y").style.display = "none";
					document.getElementById("div2Y").style.display = "none";
					document.getElementById("div3Y").style.display = "none";
					document.getElementById("div4Y").style.display = "none";
					document.getElementById("div5Y").style.display = "none";
					document.getElementById("div6Y").style.display = "none";
					document.getElementById("div7Y").style.display = "none";
					document.getElementById("div8Y").style.display = "none";
					document.getElementById("div9Y").style.display = "none";
					document.getElementById("div10Y").style.display = "none";
					document.getElementById("div11Y").style.display = "none";
					document.getElementById("div12Y").style.display = "none";
					document.getElementById("div13Y").style.display = "block";
					document.getElementById("div14Y").style.display = "none";
			
	}
	if(value==14){
					document.getElementById("div1Y").style.display = "none";
					document.getElementById("div2Y").style.display = "none";
					document.getElementById("div3Y").style.display = "none";
					document.getElementById("div4Y").style.display = "none";
					document.getElementById("div5Y").style.display = "none";
					document.getElementById("div6Y").style.display = "none";
					document.getElementById("div7Y").style.display = "none";
					document.getElementById("div8Y").style.display = "none";
					document.getElementById("div9Y").style.display = "none";
					document.getElementById("div10Y").style.display = "none";
					document.getElementById("div11Y").style.display = "none";
					document.getElementById("div12Y").style.display = "none";
					document.getElementById("div13Y").style.display = "none";
					document.getElementById("div14Y").style.display = "block";
			
	}
}

function showBranchForAppointment(branch) {
 		//alert(branch);
 		if (document.getElementById(branch) != null) {
 			var objBranch = document.getElementById(branch).style;
 			//alert(objBranch);
 			if (objBranch.display == "block") {
 					objBranch.display = "none";
 			} else {
 				objBranch.display = "block";
 			}
 		}
 			//closeOtherFolder(branch,1);
 	}
function swapFolder(img1) {
	var openImg = new Image();
	openImg.src = "images/minus.gif";
	var closedImg = new Image();
	closedImg.src = "images/plus.gif";
	if (document.getElementById(img1) != null) {
		objImg = document.getElementById(img1);
		if (objImg.src.indexOf("images/plus.gif") > -1) {
			objImg.src = openImg.src;
		} else {
			objImg.src = closedImg.src;
		}
	}
}