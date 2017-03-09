<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
/* Cookie[] cs=request.getCookies();
if(cs!=null){
	for(Cookie c:cs){
		if("loginName".equals(c.getName())){
			request.setAttribute("uform", c.getValue());
		}
		else if("user_pwd".equals(c.getName())){
			request.setAttribute("pform", c.getValue());
		}
	}
} */
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>数通图书-用户登录 </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='css/login.css'/>">
	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script type="text/javascript">
window.onload=function(){
	document.getElementById("loginName").focus();
};
function checkinput(){
	document.forms["loginForm"].submit();
}
</script>
<style type="text/css">
</style>
	</head>
	<body>
		<jsp:include page="../public/header.jsp"></jsp:include>
		<div style="margin-top: 240px;"></div>
<form action="${pageContext.request.contextPath}/user/userLoginSubmit.action" id="loginForm" method="post">
			<div class="table" align="center">
					<h3 align="center" style="font-size: 17px;margin-left: 20px;color: red;margin-bottom: 20px;">${requestScope.errorMsg}</h3>
<!--<script type="text/javascript">
	$(document).ready(function(){
		$("#loginName").focus(function(){
			$uname=$(this).val();
			if($uname==this.defaultValue){
				$(this).val("");
			}else{
				$(this).val($uname);
			}
		});
		$("#loginName").blur(function(){
			$uname=$(this).val();
			if($uname==""){
				$(this).val(this.defaultValue);
			}else{
				$(this).val($uname);
			}
		});
		
	});
</script>-->
						<table class="table_in" style="color: blue">
							<tr class="table_tr">
								<td>
									用&nbsp;户&nbsp;名/邮箱：
								</td>
								<td>
									<input style="font-size: 20px;"  type="text" name="loginName" id="loginName" value="chenchaoyun"/>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td>
									密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：
								</td>
								<td><!-- ${fn:substring(requestScope.pform, 0, 6)} -->
									<input onfocus="pswonf()" onblur="pswonb()" type="password" value="111111" name="userPwd" id="password" style="font-size: 20px;" />
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td colspan="2" align="center"><input type="checkbox" name="jizhu" id="jizhu" value="1"/>
								<a href="javascript:void(0)">记住我一周</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="javascript:void(0)" style="color: blue">忘记密码</a></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td colspan="2" align="center">
									<span><a style="color: black;"
										><button style="background-color: #FFF;font-size: 20px;color: blue;border: 1px solid #E3E3E3;font-family: '隶书';" onclick="checkinput()">登陆</button></a> </span>
								&nbsp;&nbsp;&nbsp;&nbsp;
									<span><a style="color: black;"
										>
										<button type="reset" style="background-color: #fff;font-size: 20px;color: blue;border: 1px solid #E3E3E3;font-family: '隶书';">重置</button></a> </span>
								</td>
							</tr>
						</table>
			</div>
		</form>
	</body>
       
</html>
