<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" href="style/style.css"
	title="style" />
<title>清算流程查看</title>
<script language="javascript">
	function execute(){
		var rcktype=document.getElementById("rcktype").value;
		document.forms[0].action = "selectFlows_nontaxAction?rcktype="+rcktype;
		document.forms[0].target="_self";
		document.forms[0].submit();
	}
</script>
</head>
<body>
	<form method="post">
		<div id="site_content">
			<div id="content">
				<p>
					清算类型： <select name="rcktype" id="rcktype" style="width: 100px">
						<option value="FS" ${rcktype eq 'FS'? 'selected':''}>非税</option>

						<option value="LC" ${rcktype eq 'LC'? 'selected':""}>理财</option>
					</select>
					<!-- <input name="search" type="image" style="border: 0; margin: 0 0 -9px 5px;" src="style/search.png" alt="查看" title="查看" onclick="execute()" /> -->
					<input class="submit" type="button" onclick="execute()" value="查询 " />
				</p>
				<table style="width: 100%; border-spacing: 0;">
					<tr>
						<th style="width: 70px">清算类型</th>
						<th>排序</th>
						<th>执行BEAN</th>
						<th>初始化状态</th>
						<th>是否可修改状态</th>
						<th>描述</th>
					</tr>
					<c:forEach items="${flowlist}" var="list">
						<tr>
							<td>${list.id.rcktype}</td>
							<td>${list.id.rckorder}</td>
							<td>${list.beanid}</td>
							<td>${list.initstate}</td>
							<td>${list.alowchange}</td>
							<td>${list.remark}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</form>
</body>
</html>