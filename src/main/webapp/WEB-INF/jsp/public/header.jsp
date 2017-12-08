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
<title>数通天下图书馆-头部文件</title>
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
<link rel="stylesheet" type="text/css" href="<c:url value='css/my.css'/>">
<script type="text/javascript" src="<c:url value='js/jquery-1.11.1.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='js/header.js'/>"></script>
<script type="text/javascript" src="<c:url value='js/index.js'/>"></script>
<script type="text/javascript">
/*搜素提示*/	
var nameArr = null;
$(function() {
 	var sortId = "";
 	if (nameArr == null) {
		$.post("/ajaxProName.html",{},function(data) {
			nameArr = new Array(data.length);
			for (var i = 0; i < data.length; i++) {
				nameArr[i] = data[i]+"";
			}
			auto(nameArr);
		});
	}
	$("#p_f_v").focus(function() {
		if (nameArr == null) {
			$.post("/ajaxProName.html",{},function(data) {
				nameArr = new Array(data.length);
				for (var i = 0; i < data.length; i++) {
					nameArr[i] = data[i]+"";
				}
				auto(nameArr);
			});
		}
		auto(nameArr);
	});

	//搜索条件伸缩
	$('.all .allnav').click(function(){
		var ul=$('.allcon');
		if(ul.css("display")=="none"){
			ul.slideDown();
		}else{
			ul.slideUp();
		}
	})
	$('.allcon a').click(function(){
		$("input[name='sortId']").val($(this).attr("da"));
		var a=$(this).html();
		$("#all_type").html(a);
		$(".allcon").hide();
		var arr=$(this).attr('class').split('-');
		suff_class=arr[arr.length-1];
		$("#search-fenlei").attr('class','search-fenlei-'+suff_class);
	})

});
function auto(nameArr) {
	$("#p_f_v").autocomplete(nameArr, {
        minChars: 1,        //至少输入的字符数，default：1；
        matchContains : true,
        mustMatch : false,
		selectFirst:false, 
        width: 485,            //下拉框的宽度，default：input元素的宽度
        max: nameArr.length,            //下拉项目的个数，default：10
        scrollHeight: 300,    // 下拉框的高度， Default: 180 
        scroll: true,        //当结果集大于默认高度时，是否使用滚动条，Default: true
        multiple: false    //是否允许输入多个值. Default: false
	}).result(function(event, nameArr, formatted) {
		$("#p_search_button").click();
	});
}
</script>
</head>
  
  <body>
  	<jsp:include page="top.jsp"></jsp:include>
	<!-- 顶部导航 end -->
	<div style="clear:both;"></div>
	<!-- 头部 start -->
	<div class="header w1210 bc mt15">
		<!-- 头部上半部分 start 包括 logo、搜索、用户中心和购物车结算 -->
		<div class="logo w1210">
			<h1 class="fl">
				<a href="<c:url value='/book/selectBookPages'/>"><img src="<c:url value='img/myheader.jpeg'/>" style="width: 180px;height: 100px;" alt="数通图书"/>
				</a>
			</h1>
			<!-- 头部搜索 start -->
        <div class="sousu" style="margin-left: 360px;margin-top: -100px;">
			<div class="fl">
				<div class="all">
					<div id="all_type" class="allnav">
						<span id="search-fenlei">书名</span>
					</div>
				</div>
				<form action="${pageContext.request.contextPath}/book/selectBookPages.action" id="p_form" method="get" target="_bank">
					<input class="sousus ac_input" type="text" id="p_f_v"
						placeholder="请输入您想要图书" name="bookName" value="${book.bookName}"
						onfocus="if($.trim(this.value)==&#39;请输入您想要图书&#39;)this.value=&#39;&#39;"
						onblur="if($.trim(this.value)==&#39;&#39;)this.value=&#39;请输入您想要图书&#39;"
						autocomplete="off"> <input type="image"
						src="<c:url value='img/sou2.jpg'/>">
				</form>
			</div>
		</div>
			<!-- 头部搜索 end -->
			<%-- <div>
          <img alt="" src="<c:url value='img/illu.jpg'/>">
        </div> --%>
		</div>
		
		</div>
  </body>
</html>
