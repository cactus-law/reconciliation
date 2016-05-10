<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/includeAll.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Posp清算运行状态</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
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
	function gotoIndex(){
		document.forms[0].action = "<c:url value='/index'/>";
		document.forms[0].target="_self";
		document.forms[0].submit();
	}
</script>
</head>
<body>
	<div align="center">Posp清算运行状态</div>
	<br>
	<br>
	<br>
	<form action="" method="post">
		<input type="hidden" value="${clearDate}" name="clearDate">
		<div align="center">
			<table border="0" bgcolor="" align="center" cellpadding="5"
				cellspacing="1">
				<tr>
					<td align="center">清算日期</td>
					<td align="center">步骤</td>
					<td align="center">模块名称</td>
					<td align="center">模块状态</td>
					<td align="center">文件名</td>
					<td align="center">处理总数</td>
					<%--
					<td>成功数</td>
					<td>失败</td>
					--%>
					<td align="left">处理时间</td>
					<td align="center">异常信息</td>
					<td align="center">是否执行</td>
					<td align="center">重复执行，点击切换模式</td>
				</tr>
				<s:iterator value="#request.moduleList" var="list">

					<tr>
						<td align="center">${list.id.cleardate}</td>
						<td align="center">${list.id.modulestep}</td>
						<td align="left">${list.modulename}</td>
						<td align="left"><s:if test="#list.modulestate eq 0">未运行</s:if>
							<s:if test="#list.modulestate eq 1">正在运行</s:if> <s:if
								test="#list.modulestate eq 2">运行完成</s:if> <s:if
								test="#list.modulestate eq 3">运行异常</s:if></td>
						<td align="center"><s:if test="#list.filename != null">
								<input type="button" title="${list.filename}" value="文件存放地址"
									onclick="showMsg('${list.filename}')" />
							</s:if></td>
						<td align="center">${list.dealcount}</td>
						<td align="center">${list.dealdatetime}</td>
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
			<input type="button" value="返回" onclick="gotoIndex()"> <br>
		</div>
	</form>
</body>
</html>
