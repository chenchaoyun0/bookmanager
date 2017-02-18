<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form action="${pageContext.request.contextPath}/user/userList.action">
  	用户名:<input type="text" value="${requestScope.userName}" name="userName"/>
  	<input type="submit" value="模糊查询"/>
  </form>
  
    <div align="center">
		<span class="fontColor">所有结果展示</span>
	</div>
	
	<table class="fontColor" width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr bgcolor="#EEEEEE">
			<td>id</td>
			<td>username</td>
			<td>userpwd</td>
			<td>useremail</td>
			<td>usertel</td>
			<td>userbirthday</td>
		</tr>
		<c:if test="${pages.total le 0 }">
			<tr bgcolor="#FFF">
				<td colspan="9">目前没有数据！</td>
			</tr>
		</c:if>
		<c:if test="${pages.total gt 0 }">
		<c:forEach items="${pages.dataList}" var="user" varStatus="vs">
		<tr bgcolor="#FFFFFF">
			<td>${user.id}</td>
			<td>${user.username}</td>
			<td>${user.userpwd}</td>
			<td>${user.useremail}</td>
			<td>${user.usertel}</td>
			<td>${user.userbirthday}</td>
		</tr>
	</c:forEach>
	</c:if>
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
	</table>
	<tr bgcolor="#FFF" align="center">
			<jsp:include page="../page.jsp"></jsp:include>
	</tr>
	<a href="${pageContext.request.contextPath}/product/productPluginList.action">商品列表-插件分页</a>
	<a href="${pageContext.request.contextPath}/product/productList.action">商品列表-存储过程分页</a>
 
  </body>
</html>
