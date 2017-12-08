<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <base href="<%=basePath%>">
    <title>数通天下图书馆-用户注册</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/regist.css">
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js" defer="defer"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/regist.js"></script>
</head>
  
<body onload="_init_area()">
<div class="top">
    
    <jsp:include page="../public/header.jsp"></jsp:include>
</div>
<div id="wp" class="wp cl">
<div id="append_parent"></div>
<div id="ajaxwaitid"></div>
<div class="bm_h bbs" style="background-color: #ECF0F1">
    <span class="y"> <a
        href="<c:url value='user/userLogin.action'/>"
        class="xi2" style="margin-right: 20px;">已有帐号？现在登录</a> </span>
    <h3 class="xs2">新用户注册</h3>
</div>
    <p align="center" style="color: red; font-weight:bold;font-size: 20px;">${requestScopt.msg}</p>
<form onsubmit="return checkInput()" method="post" name="register" id="registerform" action="<c:url value='user/userRegistSubmit.action'/>">
<input type="hidden" name="method" value="userRegist"/>
<div class="bm_c">
<div class="mtw">
<div>
    <div class="rfm">
            <table>
                <tbody>
                    <tr>
                        <th id="rfmth"><span class="rq">*</span><label>Email:</label>
                        </th>
                        <td><input onfocus="emaonf()" type="text" id="userEmail" name="userEmail"
                            size="25" tabindex="1" class="px" value="${requestScope.user.userEmail}" /><br />
                        <em id="emailmore">&nbsp;</em></td>
                            <td class="tipcol">
                            <i id="tip_email" class="p_tip" style="display: inline;font-size: 10px;"></i>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
         <div class="rfm">
            <table>
                <tbody>
                    <tr>
                        <th id="rfmth"><span class="rq">*</span><label>手机号:</label>
                        </th>
                        <td><input type="text" id="userTel" name="userTel"
                            size="25" tabindex="1" class="px" value="${requestScope.user.userTel}" />
                            <em id="emailmore">&nbsp;</em></td>
                            <td class="tipcol">
                            <i id="telHome" class="p_tip" style="display: inline;font-size: 10px;"></i>
                        </td></td>
                    </tr>
                </tbody>
            </table>
        </div>
    <div class="rfm">
        <table>
            <tbody>
            <tr>
                <th id="rfmth"><span class="rq">*</span><label>用户名:</label>
                </th>
                <td><input onfocus="usnonf()" type="text" id="loginName"
                    name="loginName" class="px er" tabindex="1" size="25"
                    maxlength="15" value="${requestScope.user.loginName}"/>
                </td>
                <td class="tipcol">
                    <i id="tip_username" class="p_tip" style="display: inline;font-size: 10px;"></i> 
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="rfm">
            <table>
                <tbody>
                    <tr>
                        <th id="rfmth"><span class="rq">*</span><label>真实姓名:</label>
                        </th>
                        <td><input type="text" id="realName" name="realName"
                            size="25" tabindex="1" class="px" value="${requestScope.user.userTel}" />
                            <em id="emailmore">&nbsp;</em></td>
                            <td class="tipcol">
                            <i id="realName" class="p_tip" style="display: inline;font-size: 10px;"></i>
                        </td></td>
                    </tr>
                </tbody>
            </table>
        </div>
    <div class="rfm">
            <table>
                <tbody>
                    <tr>
                        <th id="rfmth"><span class="rq">*</span><label>出生日期:</label>
                        </th>
                        <td><input name="userBirthday" id="userBirthday" onfocus="WdatePicker()" size="25" tabindex="1" value="${requestScope.user.userBirthday}"
                        class="px" readonly="true"/></td>
                    </tr>
                </tbody>
            </table>
        </div>
         <div class="rfm">
            <table>
                <tbody>
                    <tr>
                        <th id="rfmth"><span class="rq">*</span><label>性别:</label>
                        </th>
                        <td>男<input name="userSex" id="userSex" type="radio" checked="checked"  checked="checked" value="1"/></td>
                        <td> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;女<input name="userSex" id="userSex" type="radio" value="0"/></td>
                    </tr>
                </tbody>
            </table>
        </div>
    <div class="rfm">
        <table>
            <tbody>
                <tr>
                    <th id="rfmth"><span class="rq">*</span><label>登录密码:</label>
                    </th>
                    <td><input onfocus="pasonf()" onblur="pasonb()" type="password" id="userPwd"
                        name="userPwd" size="25" tabindex="1" class="px er" />
                    </td>
                    <td class="tipcol"><i id="tip_password" class="p_tip"
                        style="display: inline;font-size: 10px;"></i>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="rfm">
        <table>
            <tbody>
                <tr>
                    <th id="rfmth"><span class="rq">*</span><label>确认登录密码:</label>
                    </th>
                    <td><input onfocus="cmponf()" onblur="cmponb()" type="password" id="compassword"
                        name="compassword" size="25" tabindex="1" value=""
                        class="px" /></td>
                    <td class="tipcol"><i id="tip_compassword" class="p_tip"
                        style="display: inline;font-size: 10px;"></i> 
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="rfm">
        <table>
            <tbody>
                <tr>
                    <th id="rfmth"><span class="rq">*</span><label>验证码:</label>
                    </th>
                    <td><input style="font-size: 20px;" type="text" name="verifyCode" size="8"/></td>
                    <td class="tipcol">&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<a href="javascript:_change()">
                                <img id="imgv" src="${pageContext.request.contextPath}/util/verifyCode.action"/>
                                </a>
                    </td>
                    <td class="tipcol"><i id="error_code" class="p_tip"
                        style="display: inline;font-size: 10px;color: red">${error_code}</i> 
                    </td>
                    
                </tr>
            </tbody>
        </table>
    </div>
<!-- ************************************************************** -->     
</div>
</div>
</div>
<div>
<div class="rfm mbw bw0">
<table>
    <tbody>
        <tr>
            <th>&nbsp;</th>
            <td><span> <em>&nbsp;</em>
                <button class="pn pnc" id="registerformsubmit" type="submit" name="regsubmit" value="true" tabindex="1">
                    <strong>提交</strong>
                </button> 
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button class="pn pnc" onclick="javascript:history.back()" value="true" tabindex="1">
                    <strong>返回</strong>
                </button> 
                 </span></td>
            <td>
            </td>
        </tr>
    </tbody>
</table>
</div>
<%--     <div class="rfm bw0  mbw">
        <table>
            <tbody>
                <tr>
                    <th>快捷登录:</th>
                    <td><a href="http://wpa.qq.com/msgrd?v=3&uin=873692191" target="_top" rel="nofollow"><img src="<c:url value='img/regist/qq_login.gif'/>" class="vm" /> </a></td>
                </tr>
            </tbody>
        </table>
    </div> --%>
    </div>
</form>
</div>
<div id="layer_regmessage" class="f_c blr nfl" style="display: none">
<div class="c">
<div class="alert_right">
<div id="messageleft1"></div>
<p class="alert_btnleft" id="messageright1"></p>
</div>
</div>
</div>
<div align="center" style="margin-top: 20px;border-top: 3px #00AAEE solid;">
       <jsp:include page="../public/footer.jsp"></jsp:include>
    </div>
</body>
</html>