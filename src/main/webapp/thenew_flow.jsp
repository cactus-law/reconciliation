<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>方付通对账平台管理系统</title>

<link rel="stylesheet" type="text/css"
	href="js/jquery/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />

<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" language="javascript"
	src="js/datatables-1.9.4/js/jquery.dataTables.js"></script>

<style type="text/css" title="currentStyle">
@import "js/datatables-1.9.4/css/demo_page.css";

@import "js/datatables-1.9.4/css/demo_table.css";

@import "js/datatables-1.9.4/css/jquery.dataTables.css";
</style>

<style type="text/css">
* {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

body {
	font-family: Arial;
	background: #fff url(images/bg.png) no-repeat top left;
}
/* navigation */
.navigation {
	margin: 0px auto;
	font-family: "Trebuchet MS", sans-serif;
	font-size: 24px;
	font-style: normal;
	font-weight: bold;
	letter-spacing: 1.4px;
}

.navigation .item {
	position: absolute;
}

.navigation .home {
	top: 40px;
	left: 40px;
}

.navigation a.icon {
	width: 52px;
	height: 52px;
	position: absolute;
	top: 0px;
	left: 0px;
	cursor: pointer;
}

.navigation .home a.icon {
	background: url(images/home.png) no-repeat 0px 0px;
}

.navigation .item a.active {
	background-position: 0px -52px;
}

.navigation .item img.circle {
	position: absolute;
	top: 0px;
	left: 0px;
	width: 52px;
	height: 52px;
	opacity: 0.1;
}

.navigation .item h2 {
	position: absolute;
	width: 147px;
	height: 52px;
	color: #222;
	font-size: 18px;
	top: -50px;
	left: -50px;
	text-indent: 10px;
	line-height: 52px;
	text-shadow: 1px 1px 1px #fff;
	text-transform: uppercase;
}

.navigation .item h2.active {
	color: #fff;
	text-shadow: 1px 0px 1px #555;
}
</style>

<style>
.span {
	text-align: right;
	width: 100px;
	padding: 5px 50px 0 40px;
}

.submit {
	font: 100% arial;
	border: 1px solid;
	float: right;
	width: 99px;
	height: 30px;
	padding: 2px 0 3px 0;
	cursor: pointer;
	background: #263C56;
	color: #FFF;
}

.h1 {
	font-size: 1.3em;
	font-weight: bold;
	color: #4E6CA3;
}
</style>
</head>

<body class="easyui-layout">

	<div region="west" style="width: 130px; padding: 10px; border: 0;">
		<div class="navigation" id="nav">
			<div class="item home">
				<img src="images/bg_home.png" alt="" width="125" height="125"
					class="circle" /> <a
					href="javascript:window.location.href='index.html';"
					class="icon"></a>
				<!--  
					<h2 style="padding-top:15px;padding-left:45px;">主页</h2>
					-->
			</div>
		</div>
	</div>

	<div region="center" style="border: 0;">
		<form action="">

			<!-- 分割线 
			<div style="width:90%;border-bottom: 1px solid #B0BED9;MARGIN: auto;padding-top:10px; " >
				<h1 class="h1">
					     当前业务-流程管理
				</h1>
			</div>-->
			<!-- 查询条件 -->
			<div
				style="width: 90%; MARGIN: auto; padding: 30px 0 10px 0; height: 10px;">
				<div
					style="width: 80%; height: 30px; float: left; font-weight: bold; font-size: 14px; text-align: left;">
					<span class="span">清算类型</span> <select class="easyui-combobox"
						id="rcktype" name="rcktype" editable="false">
					<option value="RQ" ${rcktype eq 'RQ'? 'selected':""}>日切</option>
					<option value="FS" ${rcktype eq 'FS'? 'selected':''}>非税</option>
					<option value="FG" ${rcktype eq 'FG'? 'selected':''}>非税改造</option>
					<option value="LC" ${rcktype eq 'LC'? 'selected':""}>理财</option>
					</select>
				</div>
				<div
					style="width: 20%; float: right; display: inline; font-weight: bold; font-size: 14px;">
					<input type="button" value="查询" onclick="execute()" class="submit" />
				</div>
			</div>
			<!-- 分割线 -->
			<!--  
			<div style="width:90%;border-bottom: 1px solid #B0BED9;MARGIN: auto;padding-top:15px; " >
			</div>-->
			<!-- 数据表格 -->
			<input type="text" name="module" id="module" value=""
				style="display: none" />
			<table cellpadding="0" width="90%" style="padding-top: 8px;"
				cellspacing="0" border="0" class="display dataTable">
				<thead>
					<tr align="center">
						<th style="width: 70px">清算类型</th>
						<th>排序</th>
						<th>执行BEAN</th>
						<th>初始化状态</th>
						<th>是否可修改状态</th>
						<th>异常时是否执行</th>
						<th>是否允许单独执行</th>
						<th>描述</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${flowlist}" var="list">
						<tr class="gradeA" align="center">
							<td><c:if test="${list.id.rcktype eq 'FS'}">非税</c:if> <c:if
									test="${list.id.rcktype eq 'LC'}">理财</c:if></td>
							<td>${list.id.rckorder}</td>
							<td>${list.beanid}</td>
							<td>${list.initstate}</td>
							<td>${list.alowchange}</td>
							<td>${list.expcont}</td>
							<td>${list.singleext}</td>
							<td>${list.remark}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	<script type="text/javascript">
$(function(){

	$('#nav > div').hover(function(){
		var $this = $(this);
		$this.find('img').stop().animate({'width':'125px','height':'125px','top':'-37px','left':'-37px','opacity':'1.0'},500,'easeOutBack',function(){
			$(this).parent().find('ul').fadeIn(700);
		});
		$this.find('a:first,h2').addClass('active');
	},function(){
		var $this = $(this);
		$this.find('ul').fadeOut(500);
		$this.find('img').stop().animate({'width':'52px','height':'52px','top':'0px','left':'0px','opacity':'0.1'},5000,'easeOutBack');
		$this.find('a:first,h2').removeClass('active');
	});

});

$(document).ready(function() {
	$('.dataTable').dataTable({
		bFilter:false,
		bPaginate:false,
		bJQueryUI:true,
		bSort:false
	});
});

function execute(){
	document.forms[0].action = "selectFlows_extendsNontaxAction";
	document.forms[0].target="_self";
	document.forms[0].submit();
}
</script>
</body>
</html>