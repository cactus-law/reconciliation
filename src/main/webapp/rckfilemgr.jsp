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
<title>清算文件管理</title>
<script language="javascript">
	function execute(){
		var cleardate=document.getElementById("cleardate").value;
		if(cleardate.length!=8){
			alert("请选择清算日期");
			return;
		}
		document.forms[0].action = "selectRckfilemger_nontaxAction?cleardate="+cleardate;
		document.forms[0].target="_self";
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
	<form method="post">
		<div id="site_content">
			<div id="content">
				<p>
					清算时间： <input type="text" value="${cleardate}" name="cleardate"
						id="cleardate" onclick="WdatePicker({dateFmt:'yyyyMMdd'})"
						readonly="readonly" style="width: 182px" />
					<!-- 	<input name="search" type="image" style="border: 0; margin: 0 0 -9px 5px;" src="style/search.png" alt="查看" title="查看" onclick="execute()" /> -->
					<input class="submit" type="button" onclick="execute()" value="查询 " />
				</p>
				<table style="width: 100%; border-spacing: 0;">
					<tr>
						<th style="width: 70px">文件类型</th>
						<th>文件状态</th>
						<th>文件路径</th>
						<th>处理时间</th>
						<th>文件处理数量</th>
						<th>重复下载</th>
						<th>异常</th>
						<th>描述</th>
					</tr>
					<c:forEach items="${rckfliemgerlist}" var="list">
						<tr>
							<td>${list.id.filetype}</td>
							<td><c:if test="${list.filestate eq 'N'}">未运行</c:if> <c:if
									test="${list.filestate eq 'R'}">正在执行</c:if> <c:if
									test="${list.filestate eq 'C'}">执行成功</c:if> <c:if
									test="${list.filestate eq 'E'}">执行失败</c:if></td>
							<td><input type="button" title="${list.filepath}"
								value="文件存放地址" onclick="showMsg('${list.filepath}')" /></td>
							<td>${list.dealdatetime}</td>
							<td>${list.filecount}</td>
							<td>${list.isredownload}</td>
							<td>${list.exceptiondesc}</td>
							<td>${list.remark}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</form>
</body>
</html>