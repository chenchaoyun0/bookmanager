<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta charset="utf-8" />
        <title>Demo</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/xcConfirm.css"/>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
        <style type="text/css">
            .sgBtn{width: 135px; height: 35px; line-height: 35px; margin-left: 10px; margin-top: 10px; text-align: center; background-color: #0095D9; color: #FFFFFF; float: left; border-radius: 5px;}
        </style>
        <script type="text/javascript">
            $(function(){
                
                $("#btn1").click(function(){
                    var txt=  "提示文字，提示文字，提示文字，提示文字，提示文字，提示文字";
                    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
                });
                
                $("#btn2").click(function(){
                    var txt=  "提示文字，提示文字，提示文字，提示文字，提示文字，提示文字";
                    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);

                });
                
                $("#btn3").click(function(){
                    var txt=  "提示文字，提示文字，提示文字，提示文字，提示文字，提示文字";
                    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
                });
                
                $("#btn4").click(function(){
                    var txt=  "提示文字，提示文字，提示文字，提示文字，提示文字，提示文字";
                    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
                });
                
                $("#btn5").click(function(){
                    var txt=  "提示文字，提示文字，提示文字，提示文字，提示文字，提示文字";
                    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
                });
                
                $("#btn6").click(function(){
                    var txt=  "请输入";
                    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.input,{
                        onOk:function(v){
                            console.log(v);
                        }
                    });
                });
                
                $("#btn7").click(function(){
                    var txt=  "自定义呀";
                    var option = {
                        title: "自定义",
                        btn: parseInt("0011",2),
                        onOk: function(){
                            console.log("确认啦");
                        }
                    }
                    window.wxc.xcConfirm(txt, "custom", option);
                });
                
                $("#btn8").click(function(){
                    var txt=  "默认";
                    window.wxc.xcConfirm(txt);
                });
            });
        </script>
    </head>
    <body>
        <div class="confirm" style="height: 768px;">
            <div class="sgBtn" id="btn1"><a style="cursor:pointer">弹窗1(信息)</a></div>
            <div class="sgBtn" id="btn2"><a style="cursor:pointer">弹窗2(提示)</a></div>
            <div class="sgBtn" id="btn3"><a style="cursor:pointer">弹窗3(警告)</a></div>
            <div class="sgBtn" id="btn4"><a style="cursor:pointer">弹窗4(错误)</a></div>
            <div class="sgBtn" id="btn5"><a style="cursor:pointer">弹窗5(成功)</a></div>
            <div class="sgBtn" id="btn6"><a style="cursor:pointer">弹窗6(输入框)</a></div>
            <div class="sgBtn" id="btn7"><a style="cursor:pointer">弹窗7(自定义)</a></div>
            <div class="sgBtn" id="btn8"><a style="cursor:pointer">弹窗8(默认)</a></div>
        </div>
        
        <script type="text/javascript">
        $(document).ready(function(){
        	  $(".ok").click(function(){
        		    console.log("dd");
        	    
        	  });
        	});
        </script>
    </body>
</html>
