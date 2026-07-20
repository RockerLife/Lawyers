/**
 * Calendar Script
 * Creates a calendar widget which can be used to select the date more easily than using just a text box
 * http://www.openjs.com/scripts/ui/calendar/
 *
 * Example:
 * <input type="text" name="date" id="date" />
 * <script type="text/javascript">
 * 		calendar.set("date");
 * </script>
 */
calendar = {
	month_names: ["January","February","March","April","May","June","July","Augest","September","October","November","December"],
	weekdays: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
	month_days: [31,28,31,30,31,30,31,31,30,31,30,31],
	//Get today's date - year, month, day and date
	today : new Date(),
	opt : {},
	data: [],

	//Functions
	/// Used to create HTML in a optimized way.
	wrt:function(txt) {
		this.data.push(txt);
	},
	
	/* Inspired by http://www.quirksmode.org/dom/getstyles.html */
	getStyle: function(ele, property){
		if (ele.currentStyle) {
			var alt_property_name = property.replace(/\-(\w)/g,function(m,c){return c.toUpperCase();});//background-color becomes backgroundColor
			var value = ele.currentStyle[property]||ele.currentStyle[alt_property_name];
		
		} else if (window.getComputedStyle) {
			property = property.replace(/([A-Z])/g,"-$1").toLowerCase();//backgroundColor becomes background-color

			var value = document.defaultView.getComputedStyle(ele,null).getPropertyValue(property);
		}
		
		//Some properties are special cases
		if(property == "opacity" && ele.filter) value = (parseFloat( ele.filter.match(/opacity\=([^)]*)/)[1] ) / 100);
		else if(property == "width" && isNaN(value)) value = ele.clientWidth || ele.offsetWidth;
		else if(property == "height" && isNaN(value)) value = ele.clientHeight || ele.offsetHeight;
		return value;
	},
	getPosition:function(ele) {
		var x = 0;
		var y = 0;
		while (ele) {
			x += ele.offsetLeft;
			y += ele.offsetTop;
			ele = ele.offsetParent;
		}
		if (navigator.userAgent.indexOf("Mac") != -1 && typeof document.body.leftMargin != "undefined") {
			x += document.body.leftMargin;
			offsetTop += document.body.topMargin;
		}
	
		var xy = new Array(x,y);
		return xy;
	},
	/// Called when the user clicks on a date in the calendar.
	selectDate:function(year,month,day) {
		var ths = _calendar_active_instance;
		document.getElementById(ths.opt["input"]).value = year + "-" + month + "-" + day; // Date format is :HARDCODE:
		ths.hideCalendar();
	},
	/// Creates a calendar with the date given in the argument as the selected date.
	makeCalendar:function(year, month, day) {
		year = parseInt(year);
		month= parseInt(month);
		day	 = parseInt(day);
		
		//Display the table
		var next_month = month+1;
		var next_month_year = year;
		if(next_month>=12) {
			next_month = 0;
			next_month_year++;
		}
		
		var previous_month = month-1;
		var previous_month_year = year;
		if(previous_month< 0) {
			previous_month = 11;
			previous_month_year--;
		}
		
		this.wrt("<table>");
		this.wrt("<tr><th><a href='javascript:calendar.makeCalendar("+(previous_month_year)+","+(previous_month)+");' title='"+this.month_names[previous_month]+" "+(previous_month_year)+"'>&lt;</a></th>");
		this.wrt("<th colspan='5' class='calendar-title'><select name='calendar-month' class='calendar-month' onChange='calendar.makeCalendar("+year+",this.value);'>");
		for(var i in this.month_names) {
			this.wrt("<option value='"+i+"'");
			if(i == month) this.wrt(" selected='selected'");
			this.wrt(">"+this.month_names[i]+"</option>");
		}
		this.wrt("</select>");
		this.wrt("<select name='calendar-year' class='calendar-year' onChange='calendar.makeCalendar(this.value, "+month+");'>");
		var current_year = this.today.getYear();
		if(current_year < 1900) current_year += 1900;
		
		for(var i=current_year-70; i<current_year+10; i++) {
			this.wrt("<option value='"+i+"'")
			if(i == year) this.wrt(" selected='selected'");
			this.wrt(">"+i+"</option>");
		}
		this.wrt("</select></th>");
		this.wrt("<th><a href='javascript:calendar.makeCalendar("+(next_month_year)+","+(next_month)+");' title='"+this.month_names[next_month]+" "+(next_month_year)+"'>&gt;</a></th></tr>");
		this.wrt("<tr class='header'>");
		for(var weekday=0; weekday<7; weekday++) this.wrt("<td>"+this.weekdays[weekday]+"</td>");
		this.wrt("</tr>");
		
		//Get the first day of this month
		var first_day = new Date(year,month,1);
		var start_day = first_day.getDay();
		
		var d = 1;
		var flag = 0;
		
		//Leap year support
		if(year % 4 == 0) this.month_days[1] = 29;
		else this.month_days[1] = 28;
		
		var days_in_this_month = this.month_days[month];

		//Create the calender
		for(var i=0;i<=5;i++) {
			if(w >= days_in_this_month) break;
			this.wrt("<tr>");
			for(var j=0;j<7;j++) {
				if(d > days_in_this_month) flag=0; //If the days has overshooted the number of days in this month, stop writing
				else if(j >= start_day && !flag) flag=1;//If the first day of this month has come, start the date writing

				if(flag) {
					var w = d, mon = month+1;
					if(w < 10)	w	= "0" + w;
					if(mon < 10)mon = "0" + mon;

					//Is it today?
					var class_name = '';
					var yea = this.today.getYear();
					if(yea < 1900) yea += 1900;

					if(yea == year && this.today.getMonth() == month && this.today.getDate() == d) class_name = " today";
					if(day == d) class_name += " selected";
					
					class_name += " " + this.weekdays[j].toLowerCase();

					this.wrt("<td class='days"+class_name+"'><a href='javascript:calendar.selectDate(\""+year+"\",\""+mon+"\",\""+w+"\")'>"+w+"</a></td>");
					d++;
				} else {
					this.wrt("<td class='days'>&nbsp;</td>");
				}
			}
			this.wrt("</tr>");
		}
		this.wrt("</table>");
		this.wrt("<input type='button' value='Cancel' class='calendar-cancel' onclick='calendar.hideCalendar();' />");

		document.getElementById(this.opt['calendar']).innerHTML = this.data.join("");
		this.data = [];
	},
	
	/// Display the calendar - if a date exists in the input box, that will be selected in the calendar.
	showCalendar: function() {
		var input = document.getElementById(this.opt['input']);
		
		//Position the div in the correct location...
		var div = document.getElementById(this.opt['calendar']);
		var xy = this.getPosition(input);
		var width = parseInt(this.getStyle(input,'width'));
		div.style.left=(xy[0]+width+10)+"px";
		div.style.top=xy[1]+"px";

		// Show the calendar with the date in the input as the selected date
		var existing_date = new Date();
		var date_in_input = input.value;
		if(date_in_input) {
			var selected_date = false;
			var date_parts = date_in_input.split("-");
			if(date_parts.length == 3) {
				date_parts[1]--; //Month starts with 0
				selected_date = new Date(date_parts[0], date_parts[1], date_parts[2]);
			}
			if(selected_date && !isNaN(selected_date.getYear())) { //Valid date.
				existing_date = selected_date;
			}
		}
		
		var the_year = existing_date.getYear();
		if(the_year < 1900) the_year += 1900;
		this.makeCalendar(the_year, existing_date.getMonth(), existing_date.getDate());
		document.getElementById(this.opt['calendar']).style.display = "block";
		_calendar_active_instance = this;
	},
	
	/// Hides the currently show calendar.
	hideCalendar: function(instance) {
	alert("aa");
		var active_calendar_id = "";
		if(instance) active_calendar_id = instance.opt['calendar'];
		else active_calendar_id = _calendar_active_instance.opt['calendar'];
		
		if(active_calendar_id) document.getElementById(active_calendar_id).style.display = "none";
		_calendar_active_instance = {};
	},
	
	/// Setup a text input box to be a calendar box.
	set: function(input_id) {
		var input = document.getElementById(input_id);
		if(!input) return; //If the input field is not there, exit.
		
		if(!this.opt['calendar']) this.init();
		
		var ths = this;
		input.onclick=function(){
			ths.opt['input'] = this.id;
			ths.showCalendar();
		};
	},
	
	/// Will be called once when the first input is set.
	init: function() {
		if(!this.opt['calendar'] || !document.getElementById(this.opt['calendar'])) {
			var div = document.createElement('div');
			if(!this.opt['calendar']) this.opt['calendar'] = 'calender_div_'+ Math.round(Math.random() * 100);

			div.setAttribute('id',this.opt['calendar']);
			div.className="calendar-box";

			document.getElementsByTagName("body")[0].insertBefore(div,document.getElementsByTagName("body")[0].firstChild);
		}
	}
}
// colendar_cof--------------------------------------------------------------

var month_names = ['Jan','Feb','March','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'];
var date_format = '%m/%d/%Y';
// var date_format = '%Y/%m/%d';


function map(code,list) {
  var result = [];
  for (var i = 0; i < list.length; i++) {
    result.push( code(list[i]));
  };
  return result;
};

function grep(code,list) {
  var result = [];
  for (var i = 0; i < list.length; i++) {
    if (code(list[i])) {
      result.push( list[i]);
    };
  };
  return result;
};



var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1;//January is 0!
var yyyy = today.getFullYear();



function strftime(format,date) {
  var result = format;
  result = result.replace( /%Y/, date.getFullYear());
  var mm = date.getMonth()+1;
  if(mm<10){mm='0'+mm}
  result = result.replace( /%m/, mm);
  var dd =  date.getDate()
  if(dd<10){dd='0'+dd}
  result = result.replace( /%d/,dd);
  return result;
};

function strptime(format,text) {
  var result = new Date();
  // quote meta chars
  var s = '^' + format.replace( /([][\*])/g, '\\$1') + '$';
  var re_format = new RegExp( s.replace(/%[Ymd]/g,'(\\d+)'));
  var match = text.match( re_format );

  if (match) {
    // Throw away the full string that appears for some weird reason
    match.shift();
    // we have a valid, matching date
    var date_parts = new Object();
    date_parts['Y'] = result.getFullYear();
    date_parts['m'] = result.getMonth();
    date_parts['d'] = result.getDate();

    order = format.match( /%[Ymd]/g );
    for (var i = 0; i < order.length; i++) {
      date_parts[order[i].substr(1,1)] = parseInt(match[i]);
    };

    if (date_parts['Y'] < 100) { date_parts += 2000; };
    result = new Date(date_parts.Y, date_parts.m-1, date_parts.d);
  };
  return result;
};

function calendar_cells() {
  var table = document.getElementById('calendar_widget');
  return grep( function(i){ return i.className == 'number'}, table.getElementsByTagName('td'));
};

function add_days(date,delta) {
  // get the calendar date 24h before the current date:
  var result = new Date();
  result.setTime(date.getTime() + delta*(24 * 60 * 60 *1000) );
  if (result.getDate() == date.getDate()) {
    // Ooops - date had more than 24 hours to it (DST!)
    result.setTime( result.getTime() + (delta > 0 ? 1 : -1 ) *60 * 60 * 1000);
  };

  // Clamp the time to the original time:
  result.setHours( date.getHours());

  return result;
};

function day_before(date) { return add_days(date,-1); };
function day_after(date) { return add_days(date, 1); };
function first_of_month(date) { return new Date( date.getFullYear(), date.getMonth(), 1 ); };
function prev_month(date) { return day_before( first_of_month( date )); };
function next_month(date) { return add_days( first_of_month( date ),32 ); };
function prev_year(date) { var result = date; result.setYear( date.getFullYear() -1 ); return result; };
function next_year(date) { var result = date; result.setYear( date.getFullYear() +1 ); return result; };

var target_widget;
var current_day = new Date(2008,9,15);
var display_day = new Date();

function same_day(d1,d2) { return d1.getDate() == d2.getDate() && d1.getMonth() == d2.getMonth() && d1.getFullYear() == d2.getFullYear() };

function display_month(month) {
  if (! document.getElementById)
    return;
  var first = first_of_month( month );
  var today = new Date;

  if (! document.getElementById) {
    alert("document.getElementById not supported");
    alert(document);
  };

  var table = document.getElementById('calendar_widget');
  var month_year_display = document.getElementById('month_year_display');
  month_year_display.innerHTML = month_names[month.getMonth()] + " " + month.getFullYear();

  // Find the date for the upper left corner:
  var curr = day_before(first);
  while (curr.getDay() != 1) curr = day_before(curr);
  var cells = calendar_cells();
  for (var offset = 0; offset < cells.length; offset++) {
    var span_class = '';
    if (curr.getMonth() != month.getMonth()) { span_class = 'other_month' };
    if (same_day(curr,today)) { span_class = 'today' };
    if (same_day(curr,current_day)) { span_class = 'current_selection' };
    cells[offset].innerHTML = '<a href="#" onclick="javascript:day_select_click('+curr.getFullYear()+','+(curr.getMonth())+','+curr.getDate()+')">'
                            + '<span class="'+span_class+'">' + curr.getDate() + '</span></a>';
    curr = day_after( curr );
  };
  display_day = month;
};

function close_widget() {
  var parent = window.parent.document;
  parent.getElementById('calendar_widget').style.display = 'none';
};

function set_edit(widget) { target_widget = widget; current_day = strptime( date_format, target_widget.value ); display_month(current_day); };

function update_selection(date) {
	s = date != '' ? strftime(date_format,date) : '';
  	target_widget.value = s;
  	target_widget.focus();
  	target_widget.select();
};

function month_left_click(widget) { display_month( prev_month( display_day )); };
function month_right_click(widget) { display_month( next_month( display_day ));};
function year_left_click(widget) { display_month( prev_year( display_day )); };
function year_right_click(widget) { display_month( next_year( display_day )); };
function clear_button_click(widget) { update_selection( '' ); close_widget(); };
function close_button_click(widget) { close_widget() };
function day_select_click(year,month,day) { var selected = new Date(year,month,day); update_selection(selected); close_widget(); };





