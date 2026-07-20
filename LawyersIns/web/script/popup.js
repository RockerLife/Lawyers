// Copyright © 2000 by Apple Computer, Inc., All Rights Reserved.
//
// You may incorporate this Apple sample code into your own code
// without restriction. This Apple sample code has been provided "AS IS"
// and the responsibility for its operation is yours. You may redistribute
// this code, but you are not permitted to redistribute it as
// "Apple sample code" after having made changes.
// ********************************
// application-specific functions *
// ********************************

// store variables to control where the popup will appear relative to the cursor position
// positive numbers are below and to the right of the cursor, negative numbers are above and to the left
var xOffset = 20;
var yOffset = -15;

function showPopup (targetObjectId, eventObj) {
    if(eventObj) {
		// hide any currently-visible popups
		hideCurrentPopup();
		// stop event from bubbling up any farther
		eventObj.cancelBubble = true;
		// move popup div to current cursor position 
		// (add scrollTop to account for scrolling for IE)
		var newXCoordinate = (eventObj.pageX)?eventObj.pageX + xOffset:eventObj.x + xOffset + ((document.body.scrollLeft)?document.body.scrollLeft:10);
		var newYCoordinate = (eventObj.pageY)?eventObj.pageY + yOffset:eventObj.y + yOffset + ((document.body.scrollTop)?document.body.scrollTop:155);
		
		//alert( newXCoordinate );
		//alert( newYCoordinate );
		moveObject(targetObjectId, newXCoordinate, newYCoordinate);
		// and make it visible
		if( changeObjectVisibility(targetObjectId, 'visible') ) {
	    	// if we successfully showed the popup
	    	// store its Id on a globally-accessible object
	    	window.currentlyVisiblePopup = targetObjectId;
	    	return true;
		} else {
	   	 // we couldn't show the popup, boo hoo!
	   	 return false;
		}
    } else {
		// there was no event object, so we won't be able to position anything, so give up
		return false;
    }
} // showPopup

function hideCurrentPopup() {
    // note: we've stored the currently-visible popup on the global object window.currentlyVisiblePopup
    if(window.currentlyVisiblePopup) {
	changeObjectVisibility(window.currentlyVisiblePopup, 'hidden');
	window.currentlyVisiblePopup = false;
    }
} // hideCurrentPopup



// ***********************
// hacks and workarounds *
// ***********************

// initialize hacks whenever the page loads
window.onload = initializeHacks;

// setup an event handler to hide popups for generic clicks on the document
document.onclick = hideCurrentPopup;

function initializeHacks() {
    // this ugly little hack resizes a blank div to make sure you can click
    // anywhere in the window for Mac MSIE 5
    if ((navigator.appVersion.indexOf('MSIE 5') != -1) 
	&& (navigator.platform.indexOf('Mac') != -1)
	&& getStyleObject('blankDiv')) {
	window.onresize = explorerMacResizeFix;
    }
    resizeBlankDiv();
    // this next function creates a placeholder object for older browsers
    createFakeEventObj();
}

function createFakeEventObj() {
    // create a fake event object for older browsers to avoid errors in function call
    // when we need to pass the event object to functions
    if (!window.event) {
	window.event = false;
    }
} // createFakeEventObj

function resizeBlankDiv() {
    // resize blank placeholder div so IE 5 on mac will get all clicks in window
    if ((navigator.appVersion.indexOf('MSIE 5') != -1) 
	&& (navigator.platform.indexOf('Mac') != -1)
	&& getStyleObject('blankDiv')) {
	getStyleObject('blankDiv').width = document.body.clientWidth - 20;
	getStyleObject('blankDiv').height = document.body.clientHeight - 20;
    }
}

function explorerMacResizeFix () {
    location.reload(false);
}

function hideObj(obj){
	document.getElementById(obj).style.visibility ="hidden";
	//document.getElementById(obj).style.display ="none";
}
function showObj(obj){
	document.getElementById(obj).style.visibility ="visible";
}
//---------------------------------------------------new tooltip-----------------------------------------

var DH = 0;var an = 0;var al = 0;var ai = 0;if (document.getElementById) {ai = 1; DH = 1;}else {if (document.all) {al = 1; DH = 1;} else { browserVersion = parseInt(navigator.appVersion); if ((navigator.appName.indexOf('Netscape') != -1) && (browserVersion == 4)) {an = 1; DH = 1;}}} function fd(oi, wS) {if (ai) return wS ? document.getElementById(oi).style:document.getElementById(oi); if (al) return wS ? document.all[oi].style: document.all[oi]; if (an) return document.layers[oi];}
function pw() {return window.innerWidth != null? window.innerWidth: document.body.clientWidth != null? document.body.clientWidth:null;}
function mouseX(evt) {if (evt.pageX) return evt.pageX; else if (evt.clientX)return evt.clientX + (document.documentElement.scrollLeft ?  document.documentElement.scrollLeft : document.body.scrollLeft); else return null;}
function mouseY(evt) {if (evt.pageY) return evt.pageY; else if (evt.clientY)return evt.clientY + (document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop); else return null;}
function popUp(evt,oi) {if (DH) {var wp = pw(); ds = fd(oi,1); dm = fd(oi,0); st = ds.visibility; if (dm.offsetWidth) ew = dm.offsetWidth; else if (dm.clip.width) ew = dm.clip.width; if (st == "visible" || st == "show") { ds.visibility = "hidden"; } else {tv = mouseY(evt) + 20; lv = mouseX(evt) - (ew/4); if (lv < 2) lv = 2; else if (lv + ew > wp) lv -= ew/2; if (!an) {lv += 'px';tv += 'px';} ds.left = lv; ds.top = tv; ds.visibility = "visible";}}}
                  