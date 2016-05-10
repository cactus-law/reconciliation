<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>

<head>
<title>清算执行</title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="style/style.css"
	title="style" />
<script src="<c:url value='/common/date/WdatePicker.js'/>"
	type="text/javascript"></script>
<script type="text/javascript">
	function execute(tip){
		var rckDate=document.getElementById("cleardate").value;
		if(rckDate.length!=8){
			alert("请选择清算日期！");
			return;
		}
		if(tip=="run"){
			document.forms[0].action = "execute_nontaxAction";
			document.forms[0].target="_self";
			document.forms[0].submit();
			}else{
				document.getElementById("tip").value="select";
				document.forms[0].action = "execute_nontaxAction";
				document.forms[0].target="_self";
				document.forms[0].submit();
				}

	}
	</script>
</head>

<body>
	<div id="site_content">
		<div id="content">
			<form action="" class="form_settings">
				<p>
					<span>选择清算日期</span> <input type="text" value="" id="cleardate"
						name="cleardate" onclick="WdatePicker({dateFmt:'yyyyMMdd'})"
						readonly="readonly" />
				</p>
				<p></p>
				<p>
					<span>选择清算类型</span> <select id="rcktype" name="rcktype">
						<option value="PT">清算</option>
						<option value="FS">非税</option>
						<option value="LC">理财</option>
						<option value="BZ">并账</option>
					</select>
				</p>
				<p style="padding-top: 15px">
					<span>&nbsp;</span> <input class="submit" type="button"
						onclick="execute('run')" value="执行清算 " />
				</p>
				<p style="padding-top: 15px">
					<span>&nbsp;</span> <input class="submit" type="button"
						onclick="execute('history')" value="查看历史 " />
				</p>
				<input type="text" name="tip" id="tip" style="display: none" />
			</form>
		</div>
	</div>
</body>
</html>
