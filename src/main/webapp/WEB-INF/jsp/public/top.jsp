<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<base href="<%=basePath%>">

<title>顶部栏</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>head</title>
<link rel="stylesheet" href="<c:url value='css/reset.css'/>" />
<link rel="stylesheet" href="<c:url value='css/style.css'/>" />
<script src="<c:url value='/js/main.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<style type="text/css">
    a{color: #fff;  font-size: 14px;}
    a:hover{color:orange;}
</style>
</head>
<body>
    <!--scroll-head-->
    <div class="scroll-head"></div>
    <!--top-->
    <div class="top-wrapper">
        <div class="top-info">
            <div class="top-left">
                    <c:if test="${not empty sessionScope.userLogin.loginName}">
                        <div data-toggle="arrowdown" id="arrow1" class="user-name">
                                <a style="color: white;" href="#" >${sessionScope.userLogin.loginName}&nbsp;|&nbsp;</a>
                                <a style="color: white;" href="<c:url value='user/userLoginOut.action'/>" >退出&nbsp;|&nbsp;</a>
                                <a style="color: white;" href="<c:url value='user/updateUpdatePassword.action'/>">修改密码</a>
                            <span class="down-icon"></span>
                        </div>
                    </c:if>
                    <c:if test="${empty sessionScope.userLogin.loginName}">
                        <div class="user-name">
                                <a style="color: white;" href="<c:url value='user/userLoginInput.action'/>" >登录&nbsp;|&nbsp;</a>
                                <a style="color: white;" href="<c:url value='user/userRegistInput.action'/>" >注册</a>
                            <span class="down-icon"></span>
                        </div>
                    </c:if>
                    <div class="user-name">
                       <a style="color: white;" href="<c:url value='book/selectBookPages'/>">主页  </a>
                    </div>
            </div>
            <!--top-right-->
            <div class="top-right">
                <c:if test="${sessionScope.userLogin ne null}">
                <div data-toggle="arrowdown" id="arrow3" class="user-name">
                    <span style="color: white;font-size:14px;">我的书架</span>
                    <span class="down-icon"></span>
                </div>
                <div data-toggle="arrowdown" id="arrow4" class="user-name">
                    <a href="<c:url value='order/skipToOrderBook.action'/>" style="color: white;">已借书籍</a>
                </div>
                </c:if>                
                <span class="vertical-line">|</span>
                <a class="a-float-left" href="${pageContext.request.contextPath}/resume" style="color: white;">个人在线简历</a>
                <span class="vertical-line">|</span>
                <a class="a-float-left" href="<c:url value='/indexHome'/>" style="color: white;">访客统计</a>
                <span class="vertical-line">|</span>
                <a class="a-float-left" href="<c:url value='book/selectBookPages.action'/>" style="color: white;">所有图书</a>
                <span class="vertical-line">|</span>
                <a class="a-float-left" href="<c:url value='ebook/selectEBookPages.action'/>" style="color: white;">所有电子书</a>
                <span class="vertical-line">|</span>

                    <a target="_bank" href="<c:url value='/druid/sql.html'/>" style="color: white;">Druid监控</a>
                <c:if test="${sessionScope.userLogin.userRole eq 1}">
                <div data-toggle="arrowdown" id="arrow7" class="user-name">
                    <i class="fa fa-list-ul fa-orange"></i>
                    <a target="_bank" style="color: white;font-size:14px;margin-left:5px;" href="<c:url value='user/admin'/>">管理员入口</a>
                    <span class="down-icon"></span>
                </div>
                </c:if>
                <!--hidden-box-->
                <div data-toggle="hidden-box" id="nav-box3" class="my-taobao-box">
                    <ul>
                        <li><a href="<c:url value='book/selectBookPages?userId=${sessionScope.userLogin.userId}'/>" style="color: black;">我的实体书</a></li>
                        <li><a href="<c:url value='ebook/selectEBookPages?userId=${sessionScope.userLogin.userId}'/>" style="color: black;">我的电子书</a></li>
                        <li><a style="color: black;" href="<c:url value='/ebook/uploadEBookInput.action'/>" >上传电子书</a></li>
                        <li><a style="color: black;" href="<c:url value='book/uploadBookInput.action'/>" >上架图书</a></li>
                    </ul>
                </div>
                <div data-toggle="hidden-box" id="nav-box5" class="get-box">
                    <ul>
                    </ul>
                </div>
                <!-- <div data-toggle="hidden-box" id="nav-box6" class="center-box">
                </div> -->
        </div>
</div></div>
<script src="<c:url value='js/main.js'/>"></script>
<script src="<c:url value='js/img-show.js'/>"></script>
</body>
</html>

