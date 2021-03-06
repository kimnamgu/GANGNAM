function gfn_isNull(str) {
	if (str == null) return true;
	if (str == "NaN") return true;
	if (new String(str).valueOf() == "undefined") return true;    
    var chkStr = new String(str);
    if( chkStr.valueOf() == "undefined" ) return true;
    if (chkStr == null) return true;    
    if (chkStr.toString().length == 0 ) return true;   
    return false; 
}

function ComSubmit(opt_formId) {
	this.formId = gfn_isNull(opt_formId) == true ? "commonForm" : opt_formId;
	this.url = "";
	this.target = "";
	
	if(this.formId == "commonForm"){
		$("#commonForm")[0].reset();
		$("#commonForm").empty();
	}
	
	this.setUrl = function setUrl(url){
		this.url = url;
	};
	
	this.target = function target(target){
		this.target = target;
	};
	
	this.addParam = function addParam(key, value){
		$("#"+this.formId).append($("<input type='hidden' name='"+key+"' id='"+key+"' value='"+value+"' >"));
	};
	
	this.submit = function submit(){
		var frm = $("#"+this.formId)[0];
		frm.action = this.url;
		frm.method = "post";
		if(this.target == "content")
			frm.target = "content";
		else if(this.target == "_top")
			frm.target = "_top";
		else
			frm.target = "_self";
		frm.submit();
	};
}

var gfv_ajaxCallback = "";
function ComAjax(opt_formId){
	this.url = "";		
	this.formId = gfn_isNull(opt_formId) == true ? "commonForm" : opt_formId;
	this.param = "";
	
	if(this.formId == "commonForm"){
		$("#commonForm")[0].reset();
		$("#commonForm").empty();
	}
	
	this.setUrl = function setUrl(url){
		this.url = url;
	};
	
	this.setCallback = function setCallback(callBack){
		fv_ajaxCallback = callBack;
	};

	this.addParam = function addParam(key,value){ 
		this.param = this.param + "&" + key + "=" + value; 
	};
	
	this.ajax = function ajax(){
		if(this.formId != "commonForm"){
			this.param += "&" + $("#" + this.formId).serialize();
		}
		$.ajax({
			url : this.url,    
			type : "POST",   
			data : this.param,
			async : false, 
			success : function(data, status) {
				if(typeof(fv_ajaxCallback) == "function"){
					fv_ajaxCallback(data);
				}
				else {					
					eval(fv_ajaxCallback + "(data);");
				}
			}
		});
	};
}

/*
divId : 페이징 태그가 그려질 div
pageIndx : 현재 페이지 위치가 저장될 input 태그 id
recordCount : 페이지당 레코드 수
totalCount : 전체 조회 건수 
eventName : 페이징 하단의 숫자 등의 버튼이 클릭되었을 때 호출될 함수 이름
*/
var gfv_pageIndex = null;
var gfv_eventName = null;
function gfn_renderPaging(params){
	
	var divId = params.divId; //페이징이 그려질 div id	
	gfv_pageIndex = params.pageIndex; //현재 위치가 저장될 input 태그
	var totalCount = params.totalCount; //전체 조회 건수
	var currentIndex = $("#"+params.pageIndex).val(); //현재 위치
	
	if($("#"+params.pageIndex).length == 0 || gfn_isNull(currentIndex) == true){
		currentIndex = 1;
	}
	
	var recordCount = params.recordCount; //페이지당 레코드 수
	if(gfn_isNull(recordCount) == true){
		recordCount = 20;
	}
	
	var totalIndexCount = Math.ceil(totalCount / recordCount); // 전체 인덱스 수
	gfv_eventName = params.eventName;
	//alert(gfv_eventName);
	
	$("#"+divId).empty();
	var preStr = "";
	var postStr = "";
	var str = "";
	
	//alert("tot=[" + totalCount + "]  curidx=[" + currentIndex + "] totidx=[" + totalIndexCount + "]");
	var first = (parseInt((currentIndex-1) / 10) * 10) + 1;		
	var last = 0;
	var prev = (parseInt((currentIndex-1)/10)*10) - 9 > 0 ? (parseInt((currentIndex-1)/10)*10) - 9 : 1; 
	var next = (parseInt((currentIndex-1)/10)+1) * 10 + 1 < totalIndexCount ? (parseInt((currentIndex-1)/10)+1) * 10 + 1 : totalIndexCount;
	
	if( currentIndex%10 == 0)
		last = 10;
	else
		last = (parseInt(totalIndexCount/10) == parseInt(currentIndex/10)) ? totalIndexCount%10 : 10;
	
	
	//alert("first=[" + first + "]  last=[" + last + "] prev=[" + prev + "] next=[" + next + "]");
	
	if(totalIndexCount > 10){ //전체 인덱스가 10이 넘을 경우, 맨앞, 앞 태그 작성
		preStr += "<a href='#this' class='pad_5' onclick='_movePage(1)'>[<<]</a> " +
				"<a href='#this' class='pad_5' onclick='_movePage("+prev+")'>[<]</a> ";
	}
	else if(totalIndexCount <=10 && totalIndexCount > 1){ //전체 인덱스가 10보다 작을경우, 맨앞 태그 작성
		preStr += "<a href='#this' class='pad_5' onclick='_movePage(1)'>[<<]</a> ";
	}
	
	if(totalIndexCount > 10){ //전체 인덱스가 10이 넘을 경우, 맨뒤, 뒤 태그 작성
		postStr += "<a href='#this' class='pad_5' onclick='_movePage("+next+")'>[>]</a>" +
					"<a href='#this' class='pad_5' onclick='_movePage("+totalIndexCount+")'>[>>]</a> ";
	}
	else if(totalIndexCount <=10 && totalIndexCount > 1){ //전체 인덱스가 10보다 작을경우, 맨뒤 태그 작성
		postStr += "<a href='#this' class='pad_5' onclick='_movePage("+totalIndexCount+")'>[>>]</a> ";
	}
	
	for(var i=first; i<(first+last); i++){
		if(i != currentIndex){
			str += " <a href='#this' class='pad_5' onclick='_movePage("+i+")'>"+i+"</a> ";
		}
		else{
			str += " <strong><a href='#this' class='pad_5' onclick='_movePage("+i+")'>"+i+"</a></strong> ";
		}
	}
	//alert(preStr + str + postStr);
	$("#"+divId).append(preStr + str + postStr);
}

function _movePage(value){
	$("#"+gfv_pageIndex).val(value);
	if(typeof(gfv_eventName) == "function"){
		gfv_eventName(value);
	}
	else {
		eval(gfv_eventName + "(value);");
	}
}


/* 동일페이지에 여러 테이블이 있을 경우 각각에 대한 페이징 처리를 위하여 분리 - 서보형
divId : 페이징 태그가 그려질 div
pageIndx : 현재 페이지 위치가 저장될 input 태그 id
recordCount : 페이지당 레코드 수
totalCount : 전체 조회 건수 
eventName : 페이징 하단의 숫자 등의 버튼이 클릭되었을 때 호출될 함수 이름

movepage 인자추가
params.eventName : 이부분은 조회할 function의 이름
params.pageIndex : 페이지 인덱스 아이디
*/
function gfn_renderPaging_multi(params){
	
	var divId = params.divId; //페이징이 그려질 div id	
	var totalCount = params.totalCount; //전체 조회 건수
	var currentIndex = $("#"+params.pageIndex).val(); //현재 위치
	
	if($("#"+params.pageIndex).length == 0 || gfn_isNull(currentIndex) == true){
		currentIndex = 1;
	}
	
	var recordCount = params.recordCount; //페이지당 레코드 수
	if(gfn_isNull(recordCount) == true){
		recordCount = 20;
	}
	
	var totalIndexCount = Math.ceil(totalCount / recordCount); // 전체 인덱스 수
	gfv_eventName = params.eventName;
	//alert(gfv_eventName);
	
	$("#"+divId).empty();
	var preStr = "";
	var postStr = "";
	var str = "";
	
	//alert("tot=[" + totalCount + "]  curidx=[" + currentIndex + "] totidx=[" + totalIndexCount + "]");
	var first = (parseInt((currentIndex-1) / 10) * 10) + 1;		
	var last = 0;
	var prev = (parseInt((currentIndex-1)/10)*10) - 9 > 0 ? (parseInt((currentIndex-1)/10)*10) - 9 : 1; 
	var next = (parseInt((currentIndex-1)/10)+1) * 10 + 1 < totalIndexCount ? (parseInt((currentIndex-1)/10)+1) * 10 + 1 : totalIndexCount;
	
	if( currentIndex%10 == 0)
		last = 10;
	else
		last = (parseInt(totalIndexCount/10) == parseInt(currentIndex/10)) ? totalIndexCount%10 : 10;
	
	
	//alert("first=[" + first + "]  last=[" + last + "] prev=[" + prev + "] next=[" + next + "]");
	
	if(totalIndexCount > 10){ //전체 인덱스가 10이 넘을 경우, 맨앞, 앞 태그 작성
		preStr += "<a href='#this' class='pad_5' onclick='_movePage_multi(1,"+params.eventName+",\""+params.pageIndex+"\")'>[<<]</a> " +
				"<a href='#this' class='pad_5' onclick='_movePage_multi("+prev+","+params.eventName+",\""+params.pageIndex+"\")'>[<]</a> ";
	}
	else if(totalIndexCount <=10 && totalIndexCount > 1){ //전체 인덱스가 10보다 작을경우, 맨앞 태그 작성
		preStr += "<a href='#this' class='pad_5' onclick='_movePage_multi(1,"+params.eventName+",\""+params.pageIndex+"\")'>[<<]</a> ";
	}
	
	if(totalIndexCount > 10){ //전체 인덱스가 10이 넘을 경우, 맨뒤, 뒤 태그 작성
		postStr += "<a href='#this' class='pad_5' onclick='_movePage_multi("+next+","+params.eventName+",\""+params.pageIndex+"\")'>[>]</a>" +
					"<a href='#this' class='pad_5' onclick='_movePage_multi("+totalIndexCount+","+params.eventName+",\""+params.pageIndex+"\")'>[>>]</a> ";
	}
	else if(totalIndexCount <=10 && totalIndexCount > 1){ //전체 인덱스가 10보다 작을경우, 맨뒤 태그 작성
		postStr += "<a href='#this' class='pad_5' onclick='_movePage_multi("+totalIndexCount+","+params.eventName+",\""+params.pageIndex+"\")'>[>>]</a> ";
	}
	
	for(var i=first; i<(first+last); i++){
		if(i != currentIndex){
			str += " <a href='#this' class='pad_5' onclick='_movePage_multi("+i+","+params.eventName+",\""+params.pageIndex+"\")'>"+i+"</a> ";
		}
		else{
			str += " <strong><a href='#this' class='pad_5' onclick='_movePage_multi("+i+","+params.eventName+",\""+params.pageIndex+"\")'>"+i+"</a></strong> ";
		}
	}
	//alert(preStr + str + postStr);
	$("#"+divId).append(preStr + str + postStr);
}

function _movePage_multi(value,eventName,pageIndexID){
	$("#"+pageIndexID).val(value);
	if(typeof(eventName) == "function"){
		eventName(value);
	}
	else {
		eval(eventName + "(value);");
	}
}



function NvlStr(str){
	if( jQuery.type(str) == 'undefined' ) {
		
		return "";
	}
	else
		return str;
}


function formatDate(date, str)
{
	
	function pad(num) {
		num = num + '';
		return num.length < 2 ? '0' + num : num;
	}
	
	if(date.length == 8)
		return date.substring(0, 4) + str + pad(date.substring(4, 6))  + str + pad(date.substring(6, 8)); 
	else
		return date;
}


//콤마찍기
function comma(str) 
{    
	str = String(str);    
	return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}


//콤마풀기
function uncomma(str) 
{    
	str = String(str);    
	return str.replace(/[^\d]+/g, '');
}


//글 줄이기 ...
function CutString(str, size) 
{   
	var len = 0;
	
	len = str.length;
	
	if( len > size)
		return str.substr(0, size) + "...";
	else
		return str;
}

function dv_yrmonth(val, str){
	var tmp;
	
	tmp = val.substring(0, 4) + str + val.substring(4,6);
	
	return tmp;			
}


function out_eval(val){
	var tmp;
	
	if(val == 'S')
		tmp = "매우만족";
	else if(val == 'A')
		tmp = "만족";
	else if(val == 'B')
		tmp = "보통";
	else if(val == 'C')
		tmp = "불만족";
	else
		tmp = "";
	
	return tmp;			
}




function format_saupno(val){
	var tmp;
	
	if(val.length == 10)
	{
		val.substring(0,3) + "-" + val.substring(3,5) + "-" + val.substring(5,10);
	
	}
	else{
		
		tmp = val;
	}
	
	return tmp;			
}


function out_gubun1(val){
	var tmp;
	
	if(val == '1')
		tmp = "공사";
	else if(val == '2')
		tmp = "용역";
	else if(val == '3')
		tmp = "물품";	
	else
		tmp = "";
	
	return tmp;			
}


function gb_Query(val){
	var tmp;
	
	if(val == '100')
		tmp = "AA";
	else if(val == '010')
		tmp = "BB";
	else if(val == '001')
		tmp = "CC";
	else if(val == '110')
		tmp = "DD";
	else if(val == '101')
		tmp = "EE";
	else if(val == '011')
		tmp = "FF";
	else
		tmp = "";
	
	return tmp;			
}



function get_check_val(){
	var tmp = "";
	
	if($("input:checkbox[id='UH1']").is(":checked") == true )
	{
		tmp = "1";
	}
	else{
		tmp = "0";
	}
	
	if($("input:checkbox[id='UH2']").is(":checked") == true )
	{
		tmp = tmp + "1";
	}
	else{
		tmp = tmp + "0";
	}
	
	if($("input:checkbox[id='UH3']").is(":checked") == true )
	{
		tmp = tmp + "1";
	}
	else{
		tmp = tmp + "0";
	}
	
	return tmp;
}



function get_check_val2(){
	
	if($("input:checkbox[id='UH21']").is(":checked") == true )
	{
		tmp = "1";
	}
	else{
		tmp = "0";
	}
	
	if($("input:checkbox[id='UH22']").is(":checked") == true )
	{
		tmp = tmp + "1";
	}
	else{
		tmp = tmp + "0";
	}
	
	if($("input:checkbox[id='UH23']").is(":checked") == true )
	{
		tmp = tmp + "1";
	}
	else{
		tmp = tmp + "0";
	}
	
	if($("input:checkbox[id='UH24']").is(":checked") == true )
	{
		tmp = tmp + "1";
	}
	else{
		tmp = tmp + "0";
	}
	
	if($("input:checkbox[id='UH25']").is(":checked") == true )
	{
		tmp = tmp + "1";
	}
	else{
		tmp = tmp + "0";
	}
	
	if($("input:checkbox[id='UH26']").is(":checked") == true )
	{
		tmp = tmp + "1";
	}
	else{
		tmp = tmp + "0";
	}
	
	return tmp;
}


function out_UH(val){
	var tmp;
	
	if(val == '100')
		tmp = "공사";
	else if(val == '010')
		tmp = "용역";
	else if(val == '001')
		tmp = "물품";
	else if(val == '110')
		tmp = "공사,용역";
	else if(val == '101')
		tmp = "공사,물품";
	else if(val == '011')
		tmp = "용역,물품";
	else if(val == '111')
		tmp = "공사,용역,물품";
	else
		tmp = "";
	
	return tmp;			
}


function out_UH2(val){
	var tmp = "";
	
	if(val.substring(0,1) == '1')
	{
		tmp = "디자인";
	}
	
	if(val.substring(1,2) == '1')
	{	
		if(tmp == "")	
			tmp = "인쇄물";
		else
			tmp = tmp + ",인쇄물";	
	}
	
	if(val.substring(2,3) == '1')		
	{	
		if(tmp == "")	
			tmp = "동영상";
		else
			tmp = tmp + ",동영상";	
	}
	
	if(val.substring(3,4) == '1')		
	{	
		if(tmp == "")	
			tmp = "행사기획";
		else
			tmp = tmp + ",행사기획";	
	}
	
	if(val.substring(4,5) == '1')		
	{	
		if(tmp == "")	
			tmp = "종합홍보";
		else
			tmp = tmp + ",종합홍보";	
	}
	
	if(val.substring(5,6) == '1')		
	{	
		if(tmp == "")	
			tmp = "기타";
		else
			tmp = tmp + ",기타";	
	}
	
	return tmp;			
}


function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/gi, '');
}


function addCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
 
//모든 콤마 제거
function removeCommas(x) {
    if(!x || x.length == 0) return "";
    else return x.split(",").join("");
}


function onlyNumber(event){
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
		return;
	else{
		alert('숫자만 입력가능합니다.!!');
		return false;
	}	
}
function removeChar(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
		return;
	else
		event.target.value = event.target.value.replace(/[^0-9]/g, "");
}

function jumin_format(str) {
	if(!str || str.length == 0) return "";
    else return str.substr(0,6) + '-' + str.substr(-7); 
}

function hp_format(str) {
	if(!str || str.length == 0) return "";
    else return str.substr(0,3) + '-' + str.substr(3,4) + '-' + str.substr(-4); 
}

function birth_format(str) {
	if(!str || str.length == 0) return "";
    else return str.substr(0,4) + '-' + str.substr(4,2) + '-' + str.substr(-2); 
}
