<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//Dth HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dth">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站访问IP日志 by 陈超允</title>
</head>
<body style="background-color: black;">
<!-- 顶部导航 start -->
    <jsp:include page="public/top.jsp"></jsp:include>
<div align="center" style="margin-top: 20px" >
                <a href="<c:url value='/book/selectBookPages'/>"><img src="<c:url value='img/myheader.jpeg'/>" style="width: 180px;height: 100px;" alt="数通图书"/>
                </a>
            <i style="color: yellow;font-style:normal;">点他头像查看主页  by 陈超允</i>
            <%-- <object height="100" width="100" data="${pageContext.request.contextPath}/music/jj-qt.mp3"></object> --%>
            <%-- <EMBED src="${pageContext.request.contextPath}/music/jj-qt.mp3" width="300" height="45" type="audio/mpeg" loop="true" autostart="true" volume="100"></EMBED> --%>
            </div>
        <div align="center" style="margin-top:10px">
<p style="color: yellow;font-weight: bolder;font-size: 20px">今日总访问人数：${todayCount.todayVisitorCount}人，今日总访问量：${todayCount.todayCount}次</p>
<br/>
<p style="color: yellow;font-weight: bolder;font-size: 20px">总访问人数：${pages.total}人，总访问量：${totalcount}次</p>
<br/>
<a style="color: lime;font-weight: bold;" href="http://39.107.126.75" target="_bank">点此查看他在线简历</a>
<br/>
<br/>
</div>

<div align="center" style="margin-top: 20px" >
<div style="color:fuchsia;">
当前主机:
<%
String dataName = request.getParameter("dataName");
if (dataName != null && dataName.length() > 0) {
    String dataValue = request.getParameter("dataValue");
    session.setAttribute(dataName, dataValue);
}
%>
    <%
    out.println(request.getLocalAddr() + " : " + request.getLocalPort()
            + "<br>");
    %>
    <%
        out.println("<br> SESSIONID :" + session.getId() + "<br>");
        %>
</div>
<br/>
<c:if test="${pages.total le 0 }">
            <div align="center" style="margin-top: 30px;">目前没有数据!</div>
</c:if>
<c:if test="${pages.total gt 0 }">
        <table align="center" width="100%">
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
                    <th style="color: yellow"><a href="<c:url value='indexHomeForIp?userIp=${log.userIp}'/>" style="color:lime; text-decoration:underline;">${log.userIp}</a></th>
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
	<div align="center" style="color: white;">
	<br/>
	Gerrit:<a style="color: lime;" href="http://39.107.126.75/gerrit" target="_bank">http://39.107.126.75/gerrit</a>，
	Jenkins:<a style="color: lime;" href="http://58.87.121.73:8081" target="_bank">http://www.longge1987.cn/jenkins/</a>
	，ActiveMQ:<a style="color: lime;" href="http://58.87.121.73:8161/admin/queues.jsp" target="_bank">http://www.longge1987.cn:8161/admin/queues.jsp</a>
	<br/>
	<br/>
	私服库:<a style="color: lime;" href="http://58.87.121.73:8080/nexus" target="_bank">http://www.longge1987.cn:8080/nexus/</a>
	，手机商城:<a style="color: lime;" href="http://39.105.33.58:8081/AShopping" target="_bank">http://www.ccy123.cn/AShopping/</a>
	<br/>
	<br/>
	Zookeeper节点管理:<a style="color: lime;" href="http://39.105.33.58:8081/zkWeb" target="_bank">http://www.ccy123.cn/zkWeb/</a>
	，Dubbo:<a style="color: lime;" href="http://39.105.33.58:8081/dubbo-admin" target="_bank">http://www.ccy123.cn/dubbo-admin/</a>
	<br/>
	<br/>
	项目gitHub:<a style="color: lime;" href="https://github.com/chenchaoyun0" target="_bank">https://github.com/chenchaoyun0</a>
	，博客:<a style="color: lime;" href="http://blog.csdn.net/sinat_22767969" target="_bank">http://blog.csdn.net/sinat_22767969</a>
	<br/>
	<br/>
	备案/许可证编号:<a style="color: lime;" href="http://www.miitbeian.gov.cn" target="_bank">桂ICP备16001571</a>
</div>
</body>
</html>
