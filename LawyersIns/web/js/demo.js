// JavaScript Document

function showDivArea(areas_show, areas_hide){
	alert("hello");
	for (var i = 0; i < areas_show.length; i++)
	{
		ge = document.getElementById(areas_show[i]);
		ge.style.display = "block";
	}
	for (var i = 0; i < areas_hide.length; i++)
	{
		gee = document.getElementById(areas_hide[i]);
		gee.style.display = "none";
	}
}

//
function showPopUp(el, wdth, hght) {
   
	var w = (screen.width-wdth)/2 +'px';
    var t = (screen.height-hght)/2 +'px';
    var cvr = document.getElementById("cover");
    var dlg = document.getElementById(el);
    var ss = document.getElementById("StateCode");
    cvr.style.display = "block";
    dlg.style.display = "block";
    dlg.style.left =w;
    dlg.style.top =t;
    document.getElementById("cover").style.height =document.body.scrollHeight;
	document.getElementById("cover").focus();
}
function closePopUp(el) {	
var cvr = document.getElementById("cover")
var dlg = document.getElementById(el)
cvr.style.display = "none"
dlg.style.display = "none"
}


 function show(obj) 
 {     
 	no = obj.options[obj.selectedIndex].value;      
	count = obj.options.length;      
	for(i=1;i<count;i++)        
	document.getElementById('myDiv'+i).style.display = 'none';      
	if(no>0)        
	document.getElementById('myDiv'+no).style.display = 'block';    
	} 
	
	// JavaScript Document
var openImg = new Image();
openImg.src = "images/minus.gif";
var closedImg = new Image();
closedImg.src = "images/plus.gif";

function showBranch(branch)

{
	var objBranch = document.getElementById(branch).style;
	if(objBranch.display=="block")
		objBranch.display="none";
	else
		objBranch.display="block";
}

function swapFolder(img1){
	objImg = document.getElementById(img1);
	if(objImg.src.indexOf('images/plus.gif')>-1)
		objImg.src = openImg.src;
	else
		objImg.src = closedImg.src;
}

function radioOnClick(event){
	var val = document.getElementById(event.id).value;
	
	if(val == 1){
		document.getElementById("div1").style.display='block'
		document.getElementById("div2").style.display='none'
		document.getElementById("div3").style.display='none'
		document.getElementById("div4").style.display='none'
	}else if(val == 2){
		document.getElementById("div1").style.display='none'
		document.getElementById("div2").style.display='block'
		document.getElementById("div3").style.display='none'
		document.getElementById("div4").style.display='none'
	}else if(val == 3){
		document.getElementById("div1").style.display='none'
		document.getElementById("div2").style.display='none'
		document.getElementById("div3").style.display='block'
		document.getElementById("div4").style.display='none'
	}else if(val == 4){
		document.getElementById("div1").style.display='none'
		document.getElementById("div2").style.display='none'
		document.getElementById("div3").style.display='none'
		document.getElementById("div4").style.display='block'
	}
	
}