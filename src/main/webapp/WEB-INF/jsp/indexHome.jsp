<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>数通书城-图书列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!-- Google Fonts -->
<!-- Bootstrap -->
<link rel="stylesheet"
	href="<c:url value='css/bootstrap.min.css'/>">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/xcConfirm.css"/>

<!-- Font Awesome -->
<link rel="stylesheet"
	href="<c:url value='css/font-awesome.min.css'/>">

<!-- Custom CSS -->
<link rel="stylesheet" href="<c:url value='css/owl.carousel.css'/>">
<link rel="stylesheet" href="<c:url value='css/style.css'/>">
<link rel="stylesheet" href="<c:url value='css/responsive.css'/>">
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js" defer="defer"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">

/**********************************/
function sou(){
	alert($("souinp").value)
	document.forms["sou"].submit();
}
function inpch(){
	var souinp=document.getElementById("souinp");
	souinp.value="";
	souinp.style.color="black";
	souinp.style.fontSize="17px";
}
$(function(){
    $("#conditionQueryShow").hide();
    $("#conditionQueryShow").bind("click", function(event) { $("#conditionQuery").show("slow")
    	,$("#conditionQueryShow").hide(),$("#conditionQueryHide").show()
    });
    $("#conditionQueryHide").bind("click", function(event) { $("#conditionQuery").hide("slow")
    ,$("#conditionQueryHide").hide(),$("#conditionQueryShow").show();
    });
});


function ilend(bookId){
    var userId=$("#userId").val();
	if(userId==null||userId==""){
		var txt=  "请登录";
        var option = {
                title: "提示",
                btn: parseInt("0011",2),    //定义有无确定框和取消框，在xsComfirm.js中定义
                onOk: function(){
                	window.location.href="user/userLoginInput.action";
                }
        }
            window.wxc.xcConfirm(txt, "confirm", option);  
		
	}else{
	    var txt=  "请问您要借这本书么？";
	    var option = {
	            title: "提示",
	            btn: parseInt("0011",2),    //定义有无确定框和取消框，在xsComfirm.js中定义
	            onOk: function(){
	                $.post("order/lendBook",{bookId:bookId,userId:userId},function(data){
	                	if(data>=1)
	                		alert("成功申请借书！");
	                	else if(data==0)
	                		alert("书已被借完")
	                	else if(data==-1)
	                		alert("用户未登录");
	               		else if(data==-2)
	               			alert("书籍信息为空");
	                    else if(data==-9)
	                        alert("今天已借过该书，请等待管理员审核");
	               		else 
	               			alert("申请借书失败");
	                });
	                }
	            }
	        window.wxc.xcConfirm(txt, "confirm", option);  
	}
}
</script>

</head>
<body>
	<!-- 顶部导航 start -->
    <jsp:include page="public/header.jsp"></jsp:include>
    <input id="userId" type="hidden" value="${sessionScope.userLogin.userId}"> 
	<div class="mainmenu-area" style="margin-top: 150px;">
		<div class="container">
			<div class="row">
				<div class="navbar-header">
				</div>
<%-- 				<div class="navbar-collapse collapse" style="color: white;">
					<ul class="nav navbar-nav">
						<li><a style="color: white;" href="<c:url value='book/selectBookPages'/>">主页</a>
                        </li>
                        <li class="active"><a href="<c:url value='book/selectBookPages'/>">图书列表</a>
                        </li>
                        <li><a style="color: white;" href="<c:url value='book/selectBookPages'/>">个人中心</a>
                        </li>
                        <li><a style="color: white;" href="<c:url value='user/userLoginInput.action'/>">登录</a>
                        </li>
                        <li><a style="color: white;" href="<c:url value='user/userRegistInput.action'/>">新用户注册</a>
                        </li>
                        <c:if test="${sessionScope.userLogin.userRole eq 1}">
                        <li><a style="color: white;" href="<c:url value='user/admin'/>">管理员入口</a>
                        </li>
                        </c:if>
					</ul>
				</div> --%>
			</div>
		</div>
	</div>
	<!-- End mainmenu area -->
<c:if test="${pages.total le 0 }">
            <div align="center" style="margin-top: 30px;">目前没有数据!
            <a style="color: blue;" href="<c:url value='/book/uploadBookInput.action'/>" >点此上传图书</a>
            </div>
</c:if>
<c:if test="${pages.total gt 0 }">
	<div class="choose" style="border: 0px solid #00AAEE;margin-left: 100px;margin-top: 30px;">
	全部分类:&nbsp;&nbsp;<a href="<c:url value='book/selectBookPages.action'/>" style="color: blue;">全部书籍</a>&nbsp;
    <a href="<c:url value='ebook/selectEBookPages.action'/>" style="color: blue;">全部电子书</a>&nbsp;&nbsp;&nbsp;
    <c:if test="${sessionScope.userLogin ne null}">
        <a href="<c:url value='book/selectBookPages?userId=${sessionScope.userLogin.userId}'/>" style="color: blue;">我的图书</a>&nbsp;&nbsp;&nbsp;
        <a href="<c:url value='ebook/selectEBookPages?userId=${sessionScope.userLogin.userId}'/>" style="color: blue;">我的电子书</a>&nbsp;&nbsp;&nbsp;
    </c:if>
<a href="<c:url value='exportBookListExcel/${pages.pageNo}/${pages.pageSize}'/>" style="color: blue;">导出本页至EXCEL</a>&nbsp;
	<button type="button" id="conditionQueryShow">条件查找</button>
                    &nbsp;&nbsp;&nbsp;<button type="button" id="conditionQueryHide">收起</button>
	<br/>
	<form action="book/selectBookPages.action" method="GET">
		<table class="innertab">
            <tr><td><br/></td></tr>
            <tr id="conditionQuery">
                <td>
                            图书编号:
                <input style="border: 1px solid #00AAEE;font-size: 15px;" type="text" name="bookNo" size="10px" value="${book.bookNo}"/>&nbsp;&nbsp;&nbsp;
                            上架时间:
                    <input style="border: 1px solid #00AAEE;font-size: 15px;" type="text" id="bookUploadTime" name="bookUploadTime" value="${book.bookUploadTime}" readonly="true" onfocus="WdatePicker()" size="15" tabindex="1"/>&nbsp;&nbsp;&nbsp;
                            书名:
                <input style="border: 1px solid #00AAEE;font-size: 15px;" type="text" name="bookName" size="10px" value="${book.bookName}"/>&nbsp;&nbsp;&nbsp;
                            作者:
                <input style="border: 1px solid #00AAEE;font-size: 15px;" type="text" name="bookAuthor" size="10px" value="${book.bookAuthor}"/>&nbsp;&nbsp;&nbsp;
                            国外&nbsp;<input style="border: 1px solid #00AAEE;font-size: 15px;" type="radio" value="0" name="bookCountry"/>&nbsp;&nbsp;
                            国内&nbsp;<input style="border: 1px solid #00AAEE;font-size: 15px;" type="radio" value="1" name="bookCountry"/>&nbsp;&nbsp;
                            出版社:
                <input style="border: 1px solid #00AAEE;font-size: 15px;" type="text" name="bookHouse" size="10px" value="${book.bookHouse}"/>&nbsp;&nbsp;&nbsp;
                            书主人:
                <input style="border: 1px solid #00AAEE;font-size: 15px;" type="text" name="loginName" size="10px" value="${book.user.loginName}"/>&nbsp;&nbsp;&nbsp;
                </td>
                <td>&nbsp;&nbsp;&nbsp;<input type="submit" value="确定" />
                </td>
            </tr>
            <tr><td><br/></td></tr>
		</table>
		</form>
	</div>
	
	<div style="text-align: center;margin-top: 10px;">
	</div>
	
		<div class="zigzag-bottom"></div>
		<div class="container">
			<div class="row">
<c:forEach items="${pages.dataList}" var="book" varStatus="stat">
			<!-- ******************************************** -->
				<div class="col-md-3 col-sm-6" style="border: 0px solid #00AAEE;height: 400px;width:300px;">
					<div class="single-shop-product" style="margin-bottom: 30px;border:#00AAEE 0px solid;">
						<div class="product-upper" align="center">
							<a  href="<c:url value='book/selectBookDetail/${book.bookId}'/>">
						<%-- <img style="height: 230px;width:180px;" src="${fn:substringBefore(book.bookImg, ',')}"> --%>
						<img style="height: 230px;width:180px;" src="${book.bookImg}">
						</a>
						</div>
						<br/>
						<h2 align="left">
                        <span style="color: blue">${stat.index}书名:</span><a style="color: red;"  href="<c:url value='book/selectBookDetail/${book.bookId}'/>">
                         ${stat.count}-${book.bookName}
							</a>
							<span style="color: blue">作者:</span><a style="color:purple;"  href="<c:url value='book/selectBookDetail/${book.bookId}'/>">${book.bookAuthor}
							</a>
							<span style="color: blue">简介:</span><a style="color: red;" href="<c:url value='book/selectBookDetail/${book.bookId}'/>">${fn:substring(book.bookDesc, 0, 7)}...</a>
                                                 <span style="color: blue">书主人:</span><a style="color: green;"  href="book/selectBookPages?userId=${book.userId}">${book.user.loginName}
                            </a>
                        </h2>
                        <div class="product-carousel-price" align="left">
                            <span style="color: blue">购入价格:</span><ins><font style="color: blue;font-size: 15px;">&nbsp;￥${book.bookPrice}</font></ins>
                            <!-- <del>$9999.00</del> -->
                            <c:if test="${sessionScope.userLogin.userId eq book.userId}">
                            &nbsp;&nbsp;&nbsp;<a href="<c:url value='book/updateBookInput/${book.bookId}'/>" style="color: blue">编辑</a>
                            </c:if>
                        </div>
                        <br/>
						<div class="product-option-shop" align="center">
							<c:if test="${book.bookStatus eq -1}">
								<a class="btn"
								href="javascript:void(0)" style="background-color: gray;color: white;">该图书已被下架</a>
							</c:if>
								<c:if test="${book.bookStatus eq 1}">
							<a id="lend" class="btn" href="javascript:ilend('${book.bookId}');" />借书</a>
							</c:if>
						</div>
					</div>
				</div>
			<!-- ******************************************** -->
</c:forEach>
			</div>
		</div>
		<jsp:include page="public/page.jsp"></jsp:include>
	</div>
</c:if>
	<div align="center" style="margin-top: 20px;border-top: 3px #00AAEE solid;">
	   <jsp:include page="public/footer.jsp"></jsp:include>
	</div>
	
</body>

</html>