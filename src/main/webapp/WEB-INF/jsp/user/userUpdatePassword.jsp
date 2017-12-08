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
</head>
  
<body onload="init()">
<div class="top">
    
    <jsp:include page="../public/header.jsp"></jsp:include>
</div>
<div id="wp" class="wp cl">
<div id="append_parent"></div>
<div id="ajaxwaitid"></div>
<div class="bm_h bbs" style="background-color: #ECF0F1">

    <h3 class="xs2">密码修改</h3>
</div>
    <p align="center" style="color: red; font-weight:bold;font-size: 20px;">${requestScopt.msg}</p>
<input type="hidden" name="method" value="userRegist"/>
<input type="hidden" id="userId" value="${sessionScope.userLogin.userId}">
<div class="bm_c">
<div class="mtw">
<div>
    <div class="rfm">
            <table>
                <tbody>
                    <tr>
                        <th id="rfmth"><span class="rq">*</span><label>Email:</label>
                        </th>
                        <td><input readonly="readonly" type="text" id="userEmail" name="userEmail"
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
                        <td><input readonly="readonly" type="text" id="userTel" name="userTel"
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
                <td><input readonly="readonly" type="text" id="loginName"
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
                        <td><input readonly="readonly" type="text" id="realName" name="realName"
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
                        <td><input readonly="readonly" name="userBirthday" id="userBirthday" size="25" tabindex="1" value="${requestScope.user.userBirthday}"
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
                        <td>男<input disabled name="userSex" id="userSex" type="radio" checked="checked" value="1"/></td>
                        <td> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;女<input disabled name="userSex" id="userSex" type="radio" value="0"/></td>
                    </tr>
                </tbody>
            </table>
        </div>
    <div class="rfm">
        <table>
            <tbody>
                <tr>
                    <th id="rfmth"><span style="color:red">*</span><label>原密码:</label>
                    </th>
                    <td><input type="password" id="oripassword"
                        name="userPwd" size="25" tabindex="1" class="px er" />
                    </td>
                </tr>
            </tbody>
        </table>
    </div>    
    <div class="rfm">
        <table>
            <tbody>
                <tr>
                    <th id="rfmth"><span style="color:red">*</span><label>修改密码:</label>
                    </th>
                    <td><input onfocus="pasonf()" onblur="pasonb()" type="password" id="nowpassword"
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
                    <th id="rfmth"><span style="color:red">*</span><label>确认修改密码:</label>
                    </th>
                    <td><input  onfocus="cmponf()" onblur="cmponb()"  type="password" id="compassword"
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
                    <th id="rfmth"><span style="color:red">*</span><label>验证码:</label>
                    </th>
                    <td><input id="verifyCode" style="font-size: 20px;" type="text" name="verifyCode" size="8"/></td>
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
                <button onclick="checkspace()" class="pn pnc" id="registerformsubmit" value="true" tabindex="1">
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

<script>

function cmponf(){
    tip_compassword.innerHTML="请再次确认密码";
}
function cmponb(){
    var compassword=document.getElementById("compassword").value;
    var compassword_re=new RegExp("[0-9a-zA-Z]{6}","g");
    var tip_compassword=document.getElementById("tip_compassword");
    if(compassword_re.exec(compassword)==null){
        tip_compassword.style.color="red";
        /* document.getElementById("compassword").focus(); */
        return true;
    }
    tip_compassword.style.color="#7ABD54";
    tip_compassword.innerHTML="可用";
    return comparePassword();
}

function comparePassword(){
    var userPwd=document.getElementById("nowpassword").value;
    var compassword=document.getElementById("compassword").value;
    if(userPwd!=compassword){
        var tip_compassword=document.getElementById("tip_compassword");
        tip_compassword.style.color="red";
        tip_compassword.innerHTML="两次密码输入不一致";
        return true;
    }
    return false;
}

function pasonb(){
    var userPwd=document.getElementById("nowpassword").value;
    var password_re=new RegExp("[0-9a-zA-Z]{6}","g");
    var tip_password=document.getElementById("tip_password");
    if(password_re.exec(userPwd)==null){
        tip_password.style.color="red";
        return true;    
    }
    tip_password.style.color="#7ABD54";
    tip_password.innerHTML="可用";
    return false;
}
function pasonf(){
    tip_password.innerHTML="请填写修改密码, 最小长度为 6 个字符";
}

function _change(){
    var imgEle=document.getElementById("imgv");
    imgEle.src="util/verifyCode.action?a="+new Date().getTime();
}

function init(){
	$.ajax({
        url:"user/getThisUser",    //请求的url地址
        //dataType:"json",   //返回格式为json
        async:false,//请求是否异步，默认为异步，这也是ajax重要特性
        data:{  "userId":$("#userId").val()},    //参数值
        type:"POST",   //请求方式
        beforeSend:function(){
            //alert("beforeSend");
            //请求前的处理
        },
        success:function(req){
        	console.log(req);
        	setform(req);
            //请求成功时处理
        },
        complete:function(){
            //alert("complete");
            //请求完成的处理
        },
        error:function(){
        	alert("读取用户信息失败！");
            console.log(error);
            //请求出错处理
        }
    });
}

function setform(req){
	$("#userEmail").val(req.userEmail);
	$("#userTel").val(req.userTel);
	$("#loginName").val(req.loginName);
	$("#realName").val(req.realName);
	$("#userBirthday").val(req.userBirthday);
	if(req.userSex==0){
        $("input[name='userSex'][value='0']").removeAttr("checked"); 
        $("input[name='userSex'][value='1']").removeAttr("checked"); 
        $("input[name='userSex'][value='0']").attr("checked",true);
    }
}

function checkspace(){
	var loginName=$("#loginName").val();
	var userId=$("#userId").val();
	var ori=$("#oripassword").val().trim();
	var now=$("#nowpassword").val().trim();
	var com=$("#compassword").val().trim();
	var verifyCode=$("#verifyCode").val().trim();
	if((ori==null||ori=="") && (now==null||now=="") && 
			(com==null||com=="") && (verifyCode==null||verifyCode=="")){
		alert("重置密码不能为空");
		return;
	}else if(now!=com){
		alert("两次输入的密码不正确");
		return;
	}else{
	    
		  $.ajax({
		        url:"user/updatePassword",    //请求的url地址
		        //dataType:"json",   //返回格式为json
		        async:false,//请求是否异步，默认为异步，这也是ajax重要特性
		        data:{  "userId":userId,
		                "loginName":loginName,
		                "oripassword":ori,
		                "nowpassword":now,
		                "verifyCode":verifyCode},    //参数值
		        type:"POST",   //请求方式
		        beforeSend:function(){
		            //alert("beforeSend");
		            //请求前的处理
		        },
		        success:function(req){
		            console.log(req);
		            if(req==-1) {
		            	alert("验证码错误！")
		            }else if(req==-2){
		            	alert("原密码错误！")
		            }else if(req>=1){
		            	alert("修改密码成功！")
		            	window.location.href='/';
		            }
		            
		            //请求成功时处理
		        },
		        complete:function(){
		            //alert("complete");
		            //请求完成的处理
		        },
		        error:function(error){
		            console.log(error);
		            alert("error");
		            //请求出错处理
		        }
		    });
		
	}
} 
</script>

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