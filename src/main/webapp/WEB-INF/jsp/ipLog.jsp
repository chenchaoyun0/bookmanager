<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//Dth HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dth">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站访问IP日志</title>
</head>
<body style="background-color: black;">
<!-- 顶部导航 start -->
    <jsp:include page="public/top.jsp"></jsp:include>
<div align="center" style="margin-top: 20px" >
                <a href="<c:url value='/book/selectBookPages'/>"><img src="<c:url value='img/myheader.jpeg'/>" style="width: 180px;height: 100px;" alt="数通图书"/>
                </a>
            <i style="color: yellow;font-style:normal;">点击图片访问主页</i></div>
        <div align="center" style="margin-top:10px">
<p style="color: yellow;font-weight: bolder;font-size: 20px">当前总访问人数：${pages.total}人，总访问量：${totalcount}次</p></div>
<div align="center" style="margin-top: 20px" >
<c:if test="${pages.total le 0 }">
            <div align="center" style="margin-top: 30px;">目前没有数据!</div>
</c:if>
<c:if test="${pages.total gt 0 }">
        <table align="center" border="1" width="100%">
                <tr style="color: red;font-weight: bolder;">
                        <th >用户名</th>
                        <th >用户IP</th>
                        <th >访问时间</th>
                        <th >昵称</th>
                        <th >地址</th>
                        <th >经纬度</th>
                        <th >访问内容</th>
                        <th >执行方法</th>
                        <th >执行时间</th>
                        <th >访问次数</th>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
<c:forEach items="${pages.dataList}" var="log">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <th style="color: yellow;">${log.userName}</th>
                    <th style="color: yellow">${log.userIp}</th>
                    <th style="color: yellow">${log.operTime}</th>
                    <th style="color: yellow">${log.userNickName}</th>
                    <th style="color: yellow">${log.userAddress}</th>
                    <th style="color: yellow">${log.userJwd}</th>
                    <th style="color: yellow">${log.module}</th>
                    <th style="color: yellow">${log.action}</th>
                    <th style="color: yellow">${log.actionTime}</th>
                    <th style="color: yellow">${log.count}</th>
                </tr>
            </c:forEach>
        </table>
</c:if>
</div>
<div style="margin-top: 30px">
    <jsp:include page="public/page.jsp"></jsp:include>
    </div>
</body>
</html>