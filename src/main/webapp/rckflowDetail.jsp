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
<title>执行流程明细</title>
<script language="javaScript">
	function changeExecute(cleadate,rcktype,rckorder) {
		document.forms[0].action = "<c:url value='/changeExecute_nontaxAction?cleadate="+cleadate+"&rcktype="+rcktype+"&rckorder="+rckorder+"  '/>";
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
	setTimeout("clockList()",3000);

	function clockList() {
		//window.location.href="getAJAXdetail_nontaxAction?rcktype=${detailList[0].id.rcktype}&&cleardate=${detailList[0].id.cleardate}";
		
	}
</script>
</head>
<body>
	<form action="" method="post">
		<div id="site_content">
			<div id="content">
				<p>清算时间：${detailList[0].id.cleardate}
					清算类型：${detailList[0].id.rcktype}</p>
				<input type="hidden" value="${rcktype}" name="rcktype"> <input
					type="hidden" value="${cleardate}" name="cleardate">
				<table style="width: 100%; border-spacing: 0;">
					<tr>
						<th>步骤</th>
						<th>模块名称</th>
						<th>模块状态</th>
						<th>处理总数</th>
						<th>处理时间</th>
						<th>异常信息</th>
						<th>是否执行</th>
					</tr>
					<c:forEach items="${detailList}" var="list">
						<tr>
							<td>${list.id.rckorder}</td>
							<td>${list.flowname}</td>
							<td><c:if test="${list.flowstate eq 'N'}">未运行</c:if> <c:if
									test="${list.flowstate eq 'R'}">正在运行</c:if> <c:if
									test="${list.flowstate eq 'C'}">运行完成</c:if> <c:if
									test="${list.flowstate eq 'E'}">运行异常</c:if></td>
							<td>${list.dealcount}</td>
							<td>${list.dealdatetime}</td>
							<td><c:if test="${not empty list.exceptiondesc}">
									<input type="button" value="异常信息" size="8"
										title="${list.exceptiondesc}"
										onclick="showMsg('${list.exceptiondesc}')" />
								</c:if></td>
							<td><c:if test="${list.isexecute eq 'N' }">
									<input type="button"
										onclick="changeExecute('${cleardate}','${rcktype}','${list.id.rckorder}')"
										value="不执行" title="点击切换" />
								</c:if> <c:if test="${list.isexecute eq 'Y' }">
									<input type="button"
										onclick="changeExecute('${cleardate}','${rcktype}','${list.id.rckorder}')"
										value="执行" title="点击切换" />
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
				<%-- <center><input type="button" value="返回" onclick="history.back()">	</center> --%>
			</div>
		</div>
	</form>
</body>
</html>
