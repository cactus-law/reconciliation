<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	errorPage="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" type="image/ico"
	href="http://www.datatables.net/favicon.ico" />

<title>DataTables example</title>
<style type="text/css" title="currentStyle">
@import "js/datatables-1.9.4/css/demo_page.css";

@import "js/datatables-1.9.4/css/demo_table.css";

@import "js/datatables-1.9.4/css/jquery.dataTables.css";
</style>
</head>
<body id="dt_example">
	<div id="container">
		<div class="full_width big" style="text-align: center;">
			操作出现错误，点击 <a href="javascript:history.back();"
				style="text-decoration: underline;">返回上一级</a> 回到上一个页面。
		</div>
		<h1>错误信息</h1>
		${exception.message}
		<h1>问题描述</h1>
		<pre class="brush: js;"
			style="width: 100%; height: 300px; overflow: auto;">
				<s:debug></s:debug>
			</pre>
	</div>
</body>
</html>