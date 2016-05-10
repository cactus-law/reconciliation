<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="style/style.css"
	title="style" />
<script src="<c:url value='/common/date/WdatePicker.js'/>"
	type="text/javascript"></script>
<title>清算流程明细</title>
<script type="text/javascript">
function stepExecute(module) {
	document.forms[0].action = "<c:url value='/reconcileAction!setpExecute?module="+module+"'/>";
	document.forms[0].target = "_self";
	document.forms[0].submit();
}
function changeRedownload(module) {
	document.forms[0].action = "<c:url value='/reconcileAction!changeRedownload?module="+module+"'/>";
	document.forms[0].target = "_self";
	document.forms[0].submit();
}
function changeExecute(module) {
	document.forms[0].action = "<c:url value='/reconcileAction!changeExecute?module="+module+"'/>";
	document.forms[0].target = "_self";
	document.forms[0].submit();
}

function showMsg(msg){
	if(msg!=null){
		alert(msg);
	}else{
		alert("没有信息......");
	}
}
</script>
</head>
<body>
	<form action="" method="post">
		<div id="site_content" style="margin-left: 342px">
			<div id="content">
				<p>Posp清算运行详情</p>
				<input type="hidden" value="${clearDate}" name="clearDate">
				<table style="width: 100%; border-spacing: 0;">
					<tr>
						<th>清算日期</th>
						<th>步骤</th>
						<th>模块名称</th>
						<th>模块状态</th>
						<th>文件名</th>
						<th>处理总数</th>
						<th>处理时间</th>
						<th>异常信息</th>
						<th>是否执行</th>
						<th>重复执行</th>
					</tr>
					<s:iterator value="#request.moduleList" var="list">
						<tr>
							<td>${list.id.cleardate}</td>
							<td>${list.id.modulestep}</td>
							<td>${list.modulename}</td>
							<td><s:if test="#list.modulestate eq 0">未运行</s:if> <s:if
									test="#list.modulestate eq 1">正在运行</s:if> <s:if
									test="#list.modulestate eq 2">运行完成</s:if> <s:if
									test="#list.modulestate eq 3">运行异常</s:if></td>
							<td><s:if test="#list.filename != null">
									<input type="button" title="${list.filename}" value="文件存放地址"
										onclick="showMsg('${list.filename}')" />
								</s:if></td>
							<td>${list.dealcount}</td>
							<td>${list.dealdatetime}</td>
							<td><s:if test="#list.exceptiondesc != null">
									<input type="button" value="异常信息" size="8"
										title="${list.exceptiondesc}"
										onclick="showMsg('${list.exceptiondesc}')" />
								</s:if></td>
							<td><s:if test="#list.execute eq 0 ">
									<input type="button" value="不执行" title="点击切换"
										onclick="changeExecute(${list.id.modulestep})" />
								</s:if> <s:if test="#list.execute eq 1 ">
									<input type="button" value="执行" title="点击切换"
										onclick="changeExecute(${list.id.modulestep})" />
								</s:if></td>
							<td><s:if test="#list.redownload eq 0 ">
									<input type="button" value="不重复下载文件" title="点击切换"
										onclick="changeRedownload(${list.id.modulestep})" />
								</s:if> <s:if test="#list.redownload eq 1 ">
									<input type="button" value="替换下载文件" title="点击切换"
										onclick="changeRedownload(${list.id.modulestep})" />
								</s:if></td>
						</tr>
					</s:iterator>
				</table>
				<%--  <center><input type="button" value="返回" onclick="history.back()">	</center> --%>
			</div>
		</div>
	</form>
</body>
</html>
