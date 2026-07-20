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
}

function showPopUp1a(el) {
	var w = (screen.width-600)/2;
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

 
}

function showPopUp1(el) {
    var w = (screen.width-934)/2;
    var t = (screen.height-450)/2;
    var cvr = document.getElementById("cover");
    var dlg = document.getElementById(el);
    var ss = document.getElementById("StateCode");
    cvr.style.display = "block";
    dlg.style.display = "block";
    dlg.style.left =w;
    dlg.style.top =t;
    document.getElementById("cover").style.height =document.body.scrollHeight;
 
}
function showPopUp2(el) {
    var w = (screen.width-734)/2;
    var t = (screen.height-650)/2;
    var cvr = document.getElementById("cover");
    var dlg = document.getElementById(el);
   
    cvr.style.display = "block";
    dlg.style.display = "block";
	dlg.style.left =w;
    dlg.style.top =t;
    document.getElementById("cover").style.height = document.body.scrollHeight;
 
}

function closePopUp(el) {
	
var cvr = document.getElementById("cover")
var dlg = document.getElementById(el)
cvr.style.display = "none"
dlg.style.display = "none"
document.body.style.overflowY = "scroll"
}
// JavaScript Document
function PopupCenter(pageURL, title,w,h) {
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);
	var targetWin = window.open (pageURL, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
} 

function showDivArea(areas_show, areas_hide){
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
var openImg = new Image();
openImg.src = "images/minus.gif";
var closedImg = new Image();
closedImg.src = "images/plus.gif";

function showBranch(branch){
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
function setOpacity(e,opacity){
var o=e.style;
o.opacity=(opacity/100); //Opera
o.MozOpacity=(opacity/100); //Mozilla+Firefox
o.KhtmlOpacity=(opacity/100); //Konqueror
o.filter="alpha(opacity="+opacity+")"; //IE
}
function myfocus(){
	//window.document.body.disabled=false;
	//document.body.style.filter = true ? "alpha(opacity=100); -moz-opacity:100; opacity=100" : "" ; 
	setOpacity(document.body, 100);//Sets the opacity to 50%

}
function commissionWindow() {
var myArguments = new Object();
myArguments.param1 = "VIN";

setOpacity(document.body, 50);//Sets the opacity to 50%

window.showModalDialog("commission.html", myArguments, "dialogwidth: 850px; dialogheight: 400px; resizable: No"); 
}
