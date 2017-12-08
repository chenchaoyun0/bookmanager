<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数通书城-电子书列表</title>
<link href="${pageContext.request.contextPath}/css/stylebg.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<!-- Bootstrap -->
<link rel="stylesheet"
    href="<c:url value='css/bootstrap.min.css'/>">

<!-- Font Awesome -->
<link rel="stylesheet"
    href="<c:url value='css/font-awesome.min.css'/>">

<!-- Custom CSS -->
<link rel="stylesheet" href="<c:url value='css/owl.carousel.css'/>">
<link rel="stylesheet" href="<c:url value='css/style.css'/>">
<link rel="stylesheet" href="<c:url value='css/responsive.css'/>">
<link href="<c:url value='css/ebook.css'/>" rel="stylesheet"
    type="text/css" />
<script type="text/javascript" src="<c:url value='js/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='js/jquery-1.11.1.min.js'/>"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js" defer="defer"></script>
<script language="javascript">
	$(function() {
		//导航切换
		$(".imglist li").click(function() {
			$(".imglist li.selected").removeClass("selected")
			$(this).addClass("selected");
		})
	})
	
	function deleteEBook(ebookId){
		if(window.confirm("确认删除么？")){
            location.href="<%=request.getContextPath()%>/ebook/deleteEBook/"+ebookId;
            return;
        }else{
            return;
        } 
    }
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});

	});
	$(function(){   
	    //导航切换
	    $(".imglist li").click(function(){
	        $(".imglist li.selected").removeClass("selected");
	        $(this).addClass("selected");
	    });
	});
	</script>
	<script type="text/javascript">
	$(document).ready(function(){
	  $(".click").click(function(){
	  $(".tip").fadeIn(200);
	  });
	  
	  $(".tiptop a").click(function(){
	  $(".tip").fadeOut(200);
	});

	  $(".sure").click(function(){
	  $(".tip").fadeOut(100);
	});

	  $(".cancel").click(function(){
	  $(".tip").fadeOut(100);
	});

	});
	/**********************************/
	$(function(){
	    $("#conditionQueryShow").hide();
	    $("#conditionQueryShow").bind("click", function(event) { $("#conditionQuery").show("slow")
	        ,$("#conditionQueryShow").hide(),$("#conditionQueryHide").show()
	    });
	    $("#conditionQueryHide").bind("click", function(event) { $("#conditionQuery").hide("slow")
	    ,$("#conditionQueryHide").hide(),$("#conditionQueryShow").show();
	    });
	});
	</script>
	<style type="text/css">
	    tbody tr td{text-align: center;}
	    thead th td{text-align: center;}
	    .innera{
	       color: black;
	    }
	</style>
</head>


<body>
    <!-- 顶部导航 start -->
    <jsp:include page="../public/header.jsp"></jsp:include>
    <div class="mainmenu-area">
        <div class="container">
            <div class="row">
                <div class="navbar-header">
                </div>
<%--                 <div class="navbar-collapse collapse" style="color: white;">
                    <ul class="nav navbar-nav">
                        <li><a style="color: white;" href="<c:url value='book/selectBookPages'/>">主页</a>
                        </li>
                        <li class="active"><a href="<c:url value='book/selectBookPages'/>">图书列表</a>
                        </li>
                        <li><a style="color: white;" href="<c:url value='book/selectBookPages'/>">个人中心</a>
                        </li>
                        <li><a style="color: white;" href="<c:url value='book/selectBookPages'/>">我的借书单</a>
                        </li>
                        <li><a style="color: white;" href="<c:url value='user/userLoginInput.action'/>">登录</a>
                        </li>
                        <li><a style="color: white;" href="<c:url value='user/userRegistInput.action'/>">新用户注册</a>
                        </li>
                        <c:if test="${sessionScope.userLogin.userRole eq 1}">
                        <li><a style="color: white;" href="<c:url value='book/selectBookPages'/>">管理员入口</a>
                        </li>
                        </c:if>
                    </ul>
                </div> --%>
            </div>
        </div>
    </div>
    <!-- End mainmenu area -->
    <div class="choose" style="border: 0px solid #00AAEE;margin-left: 80px;margin-top: 50px;">
    全部分类:&nbsp;&nbsp;<a href="<c:url value='book/selectBookPages.action'/>" style="color: blue;">全部书籍</a>&nbsp;
    <a href="<c:url value='ebook/selectEBookPages.action'/>" style="color: blue;">全部电子书</a>&nbsp;&nbsp;&nbsp;
    <c:if test="${sessionScope.userLogin ne null}">
        <a href="<c:url value='book/selectBookPages?userId=${sessionScope.userLogin.userId}'/>" style="color: blue;">我的图书</a>&nbsp;&nbsp;&nbsp;
        <a href="<c:url value='ebook/selectEBookPages?userId=${sessionScope.userLogin.userId}'/>" style="color: blue;">我的电子书</a>&nbsp;&nbsp;&nbsp;
    </c:if>
    <button type="button" id="conditionQueryShow">条件查找</button>
                    &nbsp;&nbsp;&nbsp;<button type="button" id="conditionQueryHide">收起</button>
    <br/>
    <br/>
    <form action="<c:url value='ebook/selectEBookPages'/>" method="GET">
        <table class="innertab">
            <tr id="conditionQuery">
                <td>
                            图书编号:
                <input style="height: 20px;border: 1px solid #00AAEE;font-size: 15px;" type="text" name="ebookNo" size="10px" value="${ebook.ebookNo}"/>&nbsp;&nbsp;&nbsp;
                            上架时间:
                    <input style="height: 20px;border: 1px solid #00AAEE;font-size: 15px;" type="text" id="ebookUploadTime" name="ebookUploadTime" value="${ebook.ebookUploadTime}" readonly="true" onfocus="WdatePicker()" size="15" tabindex="1"/>&nbsp;&nbsp;&nbsp;
                            书名:
                <input style="height: 20px;border: 1px solid #00AAEE;font-size: 15px;" type="text" name="ebookName" size="10px" value="${ebook.ebookName}"/>&nbsp;&nbsp;&nbsp;
                            作者:
                <input style="height: 20px;border: 1px solid #00AAEE;font-size: 15px;" type="text" name="ebookAuthor" size="10px" value="${ebook.ebookAuthor}"/>&nbsp;&nbsp;&nbsp;
                            国外&nbsp;<input type="radio" value="0" name="ebookCountry"/>&nbsp;&nbsp;
                            国内&nbsp;<input type="radio" value="1" name="ebookCountry"/>&nbsp;&nbsp;
                            出版社:
                <input style="height: 20px;border: 1px solid #00AAEE;font-size: 15px;" type="text" name="ebookHouse" size="10px" value="${ebook.ebookHouse}"/>&nbsp;&nbsp;&nbsp;
                            书主人:
                <input style="height: 20px;border: 1px solid #00AAEE;font-size: 15px;" type="text" name="loginName" size="10px" value="${ebook.user.loginName}"/>&nbsp;&nbsp;&nbsp;
                </td>
                <td>&nbsp;&nbsp;&nbsp;<input type="submit"  value="确定" />
                </td>
            </tr>
        </table>
        </form>
    </div>
<c:if test="${pages.total le 0 }">
            <div align="center" style="margin-top: 30px;">目前没有数据!
            <a style="color: blue;" href="<c:url value='/ebook/uploadEBookInput.action'/>" >点此上传电子书</a>
            </div>
</c:if>
<c:if test="${pages.total gt 0 }">
	<div class="rightinfo" style="width: 90%;margin: 0 auto;">
		<table class="imgtable">
			<thead>
				<tr style="color: maroon;">
                        <th style="text-align: center;">图片</th>
                        <th style="text-align: center;">编号</th>
                        <th style="text-align: center;">书名</th>
                        <th style="text-align: center;min-width: 50px;">国家</th>
                        <th style="text-align: center;">作者</th>
                        <th style="text-align: center;">出版社</th>
                        <th style="text-align: center;">描述</th>
                        <th style="text-align: center;min-width: 50px;">格式</th>
                        <th style="text-align: center;">大小</th>
                        <th style="text-align: center;">上传者</th>
                        <th style="text-align: center;">上传时间</th>
                        <th style="text-align: center;min-width: 100px;">下载次数</th>
                        <th style="text-align: center;min-width: 120px;">操作</th>
                </tr>
			</thead>
<c:forEach items="${pages.dataList}" var="eBook">
			<tbody>
				<tr>
					<td class="imgtd"><img style="height: 30px;width:30px;" src="${eBook.ebookImg}" /></td>
					<td style="color: green">${eBook.ebookNo}</td>
					<td >
					<a style="color: red" href="view/pdfview/${eBook.ebookId}" class="innera">${eBook.ebookName}</a>
					</td>
					<td>
					   <c:if test="${eBook.ebookCountry eq 0}">国外</c:if>
                    <c:if test="${eBook.ebookCountry eq 1}">国内</c:if>
					</td>
					<td style="color: blue">${eBook.ebookAuthor}</td>
					<td style="color: purple;">${eBook.ebookHouse}</td>
                            <td style="color: green">${fn:substring(eBook.ebookDesc, 0, 7)}...</td>
                            <td style="color: red">${eBook.ebookType}</td>
                            <td style="color: fuchsia;"><fmt:formatNumber type="number"
                                    value="${eBook.ebookSize/(1024*1024)}" maxFractionDigits="2" />M</td>
                            <td style="color: red">${eBook.user.loginName}</td>
                            <td style="color:navy;">${fn:substring(eBook.ebookUploadTime, 0, 16)}</td>
                            <td style="color: green;">${eBook.ebookDownloadCount}</td>
                            <td><a href="view/pdfview/${eBook.ebookId}" style="color: blue;" target="_self">预览</a>
                            <a href="ebook/downloadFile/${eBook.ebookId}" style="color: blue;">下载</a>
                             <c:if test="${sessionScope.userLogin.userId eq eBook.userId or sessionScope.userLogin.userRole eq 1}">
                            &nbsp;<a style="cursor: pointer;color: red" onclick="deleteEBook('${eBook.ebookId}')">删除</a>
                            </c:if>
                            </td>
                                <!-- href="<c:url value='ebook/deleteEBook/${eBook.ebookId}'/>" -->                            
				</tr>
			</tbody>
            </c:forEach>
		</table>
		<div class="pagin">
	<jsp:include page="../public/page.jsp"></jsp:include>
	</div>
</div>
</c:if>
	<div align="center" style="margin-top: 20px;border-top: 3px #00AAEE solid;">
       <jsp:include page="../public/footer.jsp"></jsp:include>
    </div>
	<script type="text/javascript">
		$('.imgtable tbody tr:odd').addClass('odd');
	</script>
	
</body>
</html>