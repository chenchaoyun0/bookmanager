<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head lang="en">
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>数通天下图书馆-底部文件</title>
<link rel="stylesheet" href="<c:url value='css/base.css'/>"
	type="text/css">
<link rel="stylesheet" href="<c:url value='css/global.css'/>"
	type="text/css">
<link rel="stylesheet" href="<c:url value='css/header.css'/>"
	type="text/css">
<link rel="stylesheet" href="<c:url value='css/index.css'/>"
	type="text/css">
<link rel="stylesheet" href="<c:url value='css/bottomnav.css'/>"
	type="text/css">
	<link rel="stylesheet" href="<c:url value='css/footer.css'/>"
    type="text/css">
<link rel="stylesheet" type="text/css" href="<c:url value='css/my.css'/>">
<script type="text/javascript" src="<c:url value='js/jquery-1.11.1.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='js/header.js'/>"></script>
<script type="text/javascript" src="<c:url value='js/index.js'/>"></script>
<style type="text/css">
.footcss{
    color: blue;
}
.footcss a{
    color: blue;
}
</style>

</head>

  <body>
  <div align="center">
   <%-- <img src="<c:url value='img/logo.png'/>" alt="数通图书"/> --%>
  </div>
  	<!-- 底部版权 start -->
    <div class="footer w1210 bc mt10" align="center">
        <p class="copyright">@2014-2016 www.ccy123.cn</p>
        <p class="auth">
            <a href=""><img height="90px" width="100px" src="<c:url value='img/logo.png'/>" alt="" />
            <a href=""><img height="90px" width="100px" src="<c:url value='img/logo.png'/>" alt="" />
            <a href=""><img height="90px" width="100px" src="<c:url value='img/logo.png'/>" alt="" />
            <a href=""><img height="90px" width="100px" src="<c:url value='img/logo.png'/>" alt="" />
        </p>
    </div>
  </body>
</html>
