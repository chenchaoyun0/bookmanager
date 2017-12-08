<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>数通天下-上传图书</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
    href="<c:url value='css/style_8_common.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='css/cellmes.css'/>">
<script language="javascript" type="text/javascript" src="<c:url value='js/My97DatePicker/WdatePicker.js'/>" defer="defer"></script>
<script src="<c:url value='js/common.js'/>" type="text/javascript"></script>
<style>
			form {
				margin: 0;
			}
			textarea {
				display: block;
			}
		</style>
		<link rel="stylesheet" href="css/kindeditor/themes/default/default.css" />
		<script charset="utf-8" src="css/kindeditor/kindeditor-min.js"></script>
		<script charset="utf-8" src="css/kindeditor/lang/zh_CN.js"></script>
		<script>
			KindEditor.ready(function(K) {
				K.create('textarea[name="pro_desc"]', {
					autoHeightMode : true,
					afterCreate : function() {
						this.loadPlugin('autoheight');
					}
				});
			});
			function _change(){
			    var imgEle=document.getElementById("imgv");
			    imgEle.src="util/verifyCode.action?a="+new Date().getTime();
			}
		</script>
</head>

<body>
<div style="margin-bottom: 130px;">
<jsp:include page="../public/header.jsp"></jsp:include>
</div>
	<div class="bm_h bbs"
		style="background-color: #ECF0F1;text-align: center;">
		<h3 class="xs2">上传外借书籍</h3><br/>
	</div>
		<h3 align="center" style="color: red;font-size: 20px">${errorMsg}</h3>
		<h3 align="center" style="color: green;font-size: 20px">${successMsg}</h3>
	<div class="rfm" style="margin-left: 40px;width: 1000px;">


<form action="<c:url value='book/updateBookSubmit.action'/>" method="post"
			enctype="multipart/form-data">
	<div style="position: absolute;border: 0px red solid;width: 600px;height: 500px;margin-left: 660px">
		<c:if test="${!empty book.bookImg or book.bookImg.length ne null}">
		  <div align="center">
            <span class="xs2">最近上传的书籍</span>
        </div>
		</c:if>
		<br/>
		<div class="cpimg" style="">
			<c:forEach items="${imgList}" var="img" varStatus="vs">
			     <c:if test="${vs.index eq 0}">
			         <div>
                     <img id="upimg" alt=""
                      src="${img}" style="width: 350px;height: 350px;"/>
                    </div>
			     </c:if>
				<div class="cptmp">
					<img name="cptmp" style="height:70px;width:70px" alt=""
						src="${img}">
				</div>
			</c:forEach>
		</div>
	</div>
	<script type="text/javascript">
			var upimg = document.getElementById("upimg");
			window.onload = function() {
				var cptmp_img = document.getElementsByName("cptmp")
				for ( var i = 0; i < cptmp_img.length; i++) {
					var cp_img = cptmp_img[i];
					cp_img.onmouseover = ch;
				}
			};
			function ch() {
				upimg.src = this.src;
			}
		</script>
			<table>
				<tr>
			<!-- **********错误信息******* -->		
					<td>
						<h3 align="left" style="font-size: 14px;color: red;">${requestScope.msg}</h3>
					</td>
				</tr>
				<tr>
					<th id="rfmth"><span class="rq">*</span>选择分类:</th>
					<td>
					<input type="radio" name="bookCountry" value="0" checked="checked"/>国外书籍
					<input type="radio" name="bookCountry" value="1" />国内书籍
					</td>
				</tr>
				<c:if test="${sessionScope.userLogin.userRole eq 1}">
				<tr>
                    <th id="rfmth"><span class="rq">*</span>书籍所属:</th>
                    <td>
                    <input type="radio" name="bookType" value="1" checked="checked"/>公司书籍
                    <input type="radio" name="bookType" value="0" />个人书籍
                    </td>
                </tr>
				</c:if>
				<tr>
					<th id="rfmth"><span class="rq">*</span><label>书籍名称:</label>
					</th>
					<td>
					<input type="text" id="bookName" name="bookName" value="${book.bookName}"/>
				</td>
				</tr>
				<tr>
					<th id="rfmth"><span class="rq">*</span><label>书籍作者:</label>
					</th>
					<td>
					<input type="text" id="bookName" name="bookAuthor" value="${book.bookAuthor}"/>
				</td>
				</tr>
				<tr>
					<th id="rfmth"><span class="rq">*</span><label>书籍出版社:</label>
					</th>
					<td>
					<input type="text" id="bookHouse" name="bookHouse" value="${book.bookHouse}"/>
				</td>
				</tr>
				<tr>
					<th id="rfmth"><span class="rq">*</span><label>数量:</label>
					</th>
					<td><input size="5px" type="number" id="count" name="bookCount"  value="${book.bookCount}"/>
					</td>
				</tr>
				<tr>
					<td><span class="rq">*</span>购买日期:</td>
					<td><input name="bookByTime" id="buyday" onfocus="WdatePicker()" size="20px" value="${book.bookByTime}"
						/></td>
				</tr>
				<tr>
					<th id="rfmth"><span class="rq">*</span><label>书籍价格:</label>
					</th>
					<td><input type="number" id="price" name="bookPrice"  value="${book.bookPrice }"/><br />
				</tr>
				<tr>
					<th id="rfmth"><span class="rq">*</span><label>书籍简介:</label>
					</th>
    				<td>
    					 <textarea  name="bookDesc" id="p_des"  style="width:500px;height:200px;" >${book.bookDesc}</textarea>
    				</td>
				</tr>
				<tr>
                    <th id="rfmth"><span class="rq">*</span><label>验证码:</label>
                    </th>
                    <td><input size="10px" type="text" id="count" name="verifyCode"  value="" style="margin-top: -20px;"/>
                    <a href="javascript:_change()">
                                <img id="imgv" src="${pageContext.request.contextPath}/util/verifyCode.action"/>
                                </a>
                                <i id="error_code" class="p_tip"
                        style="display: inline;font-size: 10px;color: red">${error_code}</i> 
                                </td>
                </tr>
			</table>
			<div class="rfm mbw bw0" style="margin-left: 80px;">
				<table>
					<tbody>
						<tr>
							<td>
								<button class="pn pnc" id="registerformsubmit" type="submit"
									name="regsubmit" value="true" tabindex="1">
									<strong>提交</strong>
								</button>
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>
								<button class="pn pnc" id="registerformsubmit" type="submit"
									name="regsubmit" value="true" tabindex="1">
									<strong>返回</strong>
								</button>
							</td>
							<td>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<input type="hidden" name="bookId" value="${book.bookId}">
		</form>
	</div>
<div align="center" style="margin-top: 20px;border-top: 3px #00AAEE solid;">
       <jsp:include page="../public/footer.jsp"></jsp:include>
    </div>
	<!-- ************************************************************** -->
</body>
</html>
