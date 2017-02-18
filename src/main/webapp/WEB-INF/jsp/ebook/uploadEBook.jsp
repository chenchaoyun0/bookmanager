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

<title>数通天下-上传电子书</title>

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
<script type="text/javascript">
            function _change(){
                var imgEle=document.getElementById("imgv");
                imgEle.src="util/verifyCode.action?a="+new Date().getTime();
            }
        </script>
<style>
form {
	margin: 0;
}
textarea {
	display: block;
}
</style>
		
</head>

<body>
<div style="margin-bottom: 130px;">
<jsp:include page="../public/header.jsp"></jsp:include>
        <link rel="stylesheet" type="text/css" href="/bookmanager/css/xcConfirm.css"/>
        <script src="/bookmanager/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
</div>
	<div class="bm_h bbs"
		style="background-color: #ECF0F1;text-align: center;">
		<h3 class="xs2">上传电子书</h3><br/>
	</div>
		<h3 align="center" style="color: red;font-size: 20px">${errorMsg}</h3>
		<h3 align="center" style="color: green;font-size: 20px">${successMsg}</h3>
	<div class="rfm">


<form action="ebook/uploadEBookSubmit.action" method="post" enctype="multipart/form-data" onsubmit="return checkspace()">
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
					<input type="radio" name="ebookCountry" value="0" checked="checked"/>国外书籍
					<input type="radio" name="ebookCountry" value="1" />国内书籍
					</td>
				</tr>
				<tr>
					<th id="rfmth"><span class="rq">*</span><label>书籍名称:</label>
					</th>
					<td>
					<input type="text" id="ebookName" name="ebookName" value="${ebook.ebookName}"/>
				</td>
				</tr>
				<tr>
					<th id="rfmth"><span class="rq">*</span><label>书籍作者:</label>
					</th>
					<td>
					<input type="text" id="ebookAuthor" name="ebookAuthor" value="${ebook.ebookAuthor}"/>
				</td>
				</tr>
				<tr>
					<th id="rfmth"><span class="rq">*</span><label>书籍出版社:</label>
					</th>
					<td>
					<input type="text" id="ebookHouse" name="ebookHouse" value="${ebook.ebookHouse}"/>
				</td>
				</tr>
				<tr>
					<th id="rfmth"><span class="rq">*</span><label>书籍简介:</label>
					</th>
    				<td>
    					 <textarea  name="ebookDesc" id="ebookDesc"  style="width:500px;height:200px;" >${ebook.ebookDesc}</textarea>
    				</td>
				</tr>
				<tr>
                    <th id="rfmth"><span class="rq">*</span>电子书文件</th>
                    <td><input id="ebookFile" type="file" name="ebookFile"/></td>
                </tr>
                <tr>
                    <th id="rfmth">上传图片</th>
                    <td><input type="file" name="ebookImgFile"/></td>
                </tr>
				<tr>
                    <th id="rfmth"><span class="rq">*</span><label>验证码:</label>
                    </th>
                    <td><input size="10px" type="text" id="verifyCode" name="verifyCode"  value="" style="margin-top: -20px;"/>
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
		</form>
		<script>
        function checkspace(){
            var ebookName=$("#ebookName").val().trim();
            var ebookAuthor=$("#ebookAuthor").val().trim();
            var ebookHouse=$("#ebookHouse").val().trim();
            var ebookDesc=$("#ebookDesc").val().trim();
            
            if((ebookName==null||ebookName=="") && (ebookAuthor==null||ebookAuthor=="") && 
                    (ebookHouse==null||ebookHouse=="") && (ebookDesc==null||ebookDesc=="")){
                
                    var txt;
                    if($("#verifyCode").val().trim()==null || $("#verifyCode").val().trim()=="")
                        txt="验证码不能为空，请填写验证码";
                    else if($("#ebookFile").val().trim()==null || $("#ebookFile").val().trim()=="")
                    	txt="请选择上传文件";
                    else
                        txt= "打*的为必填空，你存在未填空。";
                    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
                    return false;
                } else {
                    return true;
                }
            }
        </script>
		
	</div>
<div align="center" style="margin-top: 20px;border-top: 3px #00AAEE solid;">
       <jsp:include page="../public/footer.jsp"></jsp:include>
    </div>
	<!-- ************************************************************** -->
</body>
</html>
