var str = "";
var _format="";
document.writeln("<div id=\"_contents\" style=\"padding:6px; background-color:#E3E3E3; font-size: 12px; border: 1px solid #777777;  position:absolute; left:?px; top:?px; width:?px; height:?px; z-index:1; visibility:hidden\">");
str += "\u65f6<select name=\"_hour\">";
for (h = 0; h <= 23; h++) {
	if(h<10){
		h="0"+h+"";
	}
    str += "<option value=\"" + h + "\">" + h + "</option>";
}
str += "</select> \u5206<select name=\"_minute\">";
for (m = 0; m <= 59; m++) {
	if(m<10){
		m="0"+m+"";
	}
    str += "<option value=\"" + m + "\">" + m + "</option>";
}
str += "</select> \u79d2<select name=\"_second\">";
for (s = 0; s <= 59; s++) {
	if(s<10){
		s="0"+s+"";
	}
    str += "<option value=\"" + s + "\">" + s + "</option>";
}
str += "</select> <input name=\"queding\" type=\"button\" onclick=\"_select()\" value=\"\u786e\u5b9a\" style=\"font-size:12px\" /></div>";
document.writeln(str);
var _fieldname;
function _SetTime(tt,format) {
	if(format!=null&&format!=""){
		_format=format;
	}
    _fieldname = tt;
    var ttop = tt.offsetTop;    //TT控件的定位点高
    var thei = tt.clientHeight;    //TT控件本身的高
    var tleft = tt.offsetLeft;    //TT控件的定位点宽
    while (tt = tt.offsetParent) {
        ttop += tt.offsetTop;
        tleft += tt.offsetLeft;
    }
    document.all._contents.style.top = ttop + thei + 4;
    document.all._contents.style.left = tleft;
    document.all._contents.style.visibility = "visible";
}
function _select() {
  	 _fieldname.value = document.all._hour.value + _format + document.all._minute.value + _format + document.all._second.value;
    document.all._contents.style.visibility = "hidden";
}