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