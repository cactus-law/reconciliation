<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	errorPage="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML >
<html>
<head>
<title>异常页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="style/style.css"
	title="style" />
</head>
<body>
	<div id="site_content">
		<div id="content">
			<div align="center">
				<h2>异常页面</h2>
				错误提示：${exception.message}
				<p>
					<input class="button" type="button" value="返回"
						onclick="window.history.back();" />
					<s:debug></s:debug>
			</div>

		</div>
	</div>
</body>
</html>
