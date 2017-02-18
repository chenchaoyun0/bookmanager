<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>提示信息</title>
<meta http-equiv="Refresh" content="3;url=http://${requestScope.serverHost}${pageContext.request.contextPath}/${requestScope.tz}">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	var i = 4;
	function shownum() {
		i = i - 1;
		document.getElementById("time").innerHTML = i;
		setTimeout('shownum()', 1000);
	}
</script>
</head>

<body onload="shownum()">
	<h3 align="center" style="color: red">
		<i id="time" style="color: red;"></i>&nbsp;${msg}
	</h3>
</body>
</html>
