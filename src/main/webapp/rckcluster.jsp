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
<title>集群管理</title>
<script language="javascript">
	function execute(){
		var clustertype=document.getElementById("clustertype").value;
		document.forms[0].action = "clusterList_nontaxAction?clustertype="+clustertype;
		document.forms[0].target="_self";
		document.forms[0].submit();
	}
	function changeClusterExecute(clustertype,isexecute) {
		document.forms[0].action = "<c:url value='/changeClusterExecute_nontaxAction?ct="+clustertype+"&isexecute="+isexecute+"  '/>";
		document.forms[0].target = "_self";
		document.forms[0].submit();
	}
</script>
</head>
<body>
	<form method="post">
		<div id="site_content">
			<div id="content">
				<p>
					类型： <select name="clustertype" id="clustertype"
						style="width: 100px">
						<option value=""></option>
						<option value="SS" ${clustertype eq 'SS'? 'selected':''}>收付通</option>
					</select>
					<!-- <input name="search" type="image" style="border: 0; margin: 0 0 -9px 5px;" src="style/search.png" alt="查看" title="查看" onclick="execute()" /> -->
					<input class="submit" type="button" onclick="execute()" value="查询 " />
				</p>
				<table style="width: 100%; border-spacing: 0;">
					<tr>
						<th>类型</th>
						<th>主机IP地址</th>
						<th>执行状态</th>
						<th>最后执行日期</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>是否执行</th>
						<th>是否指定运行</th>
						<th>指定运行IP地址</th>
						<th>描述</th>
					</tr>
					<c:forEach items="${list}" var="list">
						<tr>
							<td>${list.clustertype}</td>
							<td>${list.hostaddr}</td>
							<td><c:if test="${list.exestate eq 'N'}">未运行</c:if> <c:if
									test="${list.exestate eq 'R'}">正在运行</c:if> <c:if
									test="${list.exestate eq 'C'}">运行完成</c:if> <c:if
									test="${list.exestate eq 'E'}">运行异常</c:if></td>
							<td>${list.exedate}</td>
							<td>${list.exestarttime}</td>
							<td>${list.exeendtime}</td>
							<td><c:if test="${list.isexecute eq 'N' }">
									<input type="button"
										onclick="changeClusterExecute('${list.clustertype}','${list.isexecute}')"
										value="不执行" title="点击切换" />
								</c:if> <c:if test="${list.isexecute eq 'Y' }">
									<input type="button"
										onclick="changeClusterExecute('${list.clustertype}','${list.isexecute}')"
										value="执行" title="点击切换" />
								</c:if></td>
							<td><c:if test="${list.isassign eq 'Y'}">是</c:if> <c:if
									test="${list.isassign eq 'N'}">否</c:if></td>
							<td>${list.assignhostaddr}</td>
							<td>${list.remark}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</form>
</body>
</html>