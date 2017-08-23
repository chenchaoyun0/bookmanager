<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>商品详情页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<c:url value='css/main.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='css/shopcar2.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='css/cellmes.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='css/style_8_common.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='css/goods.css'/>">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/xcConfirm.css"/>
<script type="text/javascript"
	src="<c:url value='js/jquery-1.11.1.min.js'/>"></script>
<script src="${pageContext.request.contextPath}/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
        <style type="text/css">
            .sgBtn{width: 135px; height: 35px; line-height: 35px; margin-left: 10px; margin-top: 10px; text-align: center; background-color: #0095D9; color: #FFFFFF; float: left; border-radius: 5px;}
        </style>
</head>
<body style="background-color: white;">
	<!-- 顶部导航 start -->
	<jsp:include page="../public/header.jsp"></jsp:include>
	<input type="hidden" id="userId" value="${sessionScope.userLogin.userId}"> 
	<div class="bor" style="margin-top: 140px;">
		<div class="cpimg" align="center">
			<c:forEach items="${book.bookImg}" var="img" varStatus="vs">
				<c:if test="${vs.index eq 0}">
					<div>
						<img style="height: 350px; width: 300px;" id="upimg" alt=""
							src="${fn:substringBefore(book.bookImg, ',')}" />
					</div>
				</c:if>
				<div class="cptmp">
					<img name="cptmp" style="height: 70px; width: 70px" alt=""
						src="${img}" />
				</div>
			</c:forEach>
		</div>
		<script type="text/javascript">
			var upimg = document.getElementById("upimg");
			window.onload = function() {
				var cptmp_img = document.getElementsByName("cptmp")
				for (var i = 0; i < cptmp_img.length; i++) {
					var cp_img = cptmp_img[i];
					cp_img.onmouseover = ch;
				}
			};
			function ch() {
				upimg.src = this.src;
			}
			    function ilend(bookId){
			        var txt=  "请问您要借这本书么？";
			        var option = {
			                title: "提示",
			                btn: parseInt("0011",2),    //定义有无确定框和取消框，在xsComfirm.js中定义
			                onOk: function(){
			                    var userId=$("#userId").val();
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
		</script>
			<div class="desc">
				<ul>
					<li></li>
					<li>
						<div>编号:&nbsp;&nbsp;${book.bookNo}
						 <c:if test="${sessionScope.userLogin.userId eq book.userId}">
                            &nbsp;&nbsp;&nbsp;<a href="<c:url value='book/updateBookInput/${book.bookId}'/>" style="color: blue">点此编辑</a>
                            </c:if>
						</div>
					</li>
					<li>
						<div>名称:&nbsp;&nbsp;
						${book.bookName}
						</div>
					</li>
					<li>
						<div>作者:&nbsp;&nbsp;${book.bookAuthor}</div>
					</li>
					<li>
						<div>
						 国籍:&nbsp;&nbsp;
						<c:if test="${book.bookCountry eq 1}">国内图书</c:if>
                        <c:if test="${book.bookCountry eq 0}">国外图书</c:if></div>
					</li>
					<li>
						<div>出版社:&nbsp;&nbsp;${book.bookHouse}</div>
					</li>
					<li>
						<div>简要描述:&nbsp;&nbsp;${book.bookDesc}</div>
					</li>
					<li>
						<div>购入价格:&nbsp;&nbsp;${book.bookPrice}</div>
					</li>
					<li>
						<div>
							上传时间:&nbsp;&nbsp;<span style="font-weight: normal;">&nbsp;${book.bookUploadTime}</span>
						</div>
					</li>
					<li>
						<div>
							图书所属:&nbsp;&nbsp;<span style="font-weight: normal;color: red">
							<c:if test="${book.bookType eq 1}">
							 公司书籍
							</c:if>
							<c:if test="${book.bookType eq 0}">
							 个人书籍
							</c:if>
							</span>
						</div>
					</li>
					<li>
						<div>
							数量:&nbsp;&nbsp;<span style="font-weight: normal; color: red">&nbsp;${book.bookCount}</span>
						</div>
					</li>
					<li>
						<div>
							选择数量:&nbsp;&nbsp;<input type="text" name="count" value="1" size="5" readonly="readonly"/>
						</div>
					</li>
					<li>
					   <c:if test="${book.bookStatus eq -1}">
                                <a class="btn"
                                href="javascript:void(0)" style="background-color: gray;color: white;">该图书已被下架</a>
                            </c:if>
					   <c:if test="${book.bookStatus eq 1}">
						<button onclick="ilend('${book.bookId}')"
							style="width: 120px; height: 50px; font-size: 18px; background-color: #3AA8EE; color: white; margin-left: 50px; cursor: pointer; border: 0px solid #00AAEE;">
							借书</button>
							</c:if>
					</li>
				</ul>
			</div>
		<div class="seller">
			<ul>
				<li><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;书籍主人信息
				</li>
				<li>
    			 <img alt="头像" src="${book.user.userHead}">
				</li>
				<li>
					<div>
						姓名:&nbsp;&nbsp;<span
							style="font-size: 20px; color: #E43941; font-weight: bolder;">${book.user.realName}</span>
					</div>
				</li>
				<li>
					<div>
						性别:&nbsp;&nbsp;
						<c:if test="${book.user.userSex eq 1}">男</c:if>
						<c:if test="${book.user.userSex eq 0}">女</c:if>
					</div>
				</li>
				<li>
					<div>
						邮箱:&nbsp;&nbsp;${book.user.userEmail}
					</div>
				</li>
				<li>
					<div>
						手机号:&nbsp;&nbsp;${book.user.userTel}
					</div>
				</li>
				<li>
					<div>
						注册时间:&nbsp;&nbsp;${book.user.userRegisttime}
					</div>
				</li>
				<li>
					<div>
						角色:&nbsp;&nbsp;
						<c:if test="${book.user.userRole eq 1}">
						  管理员
						</c:if>
						<c:if test="${book.user.userRole eq 0}">
						  普通用户
						</c:if>
					</div>
				</li>
			</ul>
		</div>

	</div>
</body>
</html>
