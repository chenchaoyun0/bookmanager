<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线预览-${title}</title>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/pdfobject.js"></script>
 <script type="text/javascript">
     window.onload = function () {
         var success = new PDFObject({
             url: "/bookmanagerfiledir/ebook/file/chenchaoyun/pdf/2016-08-03/554957101549797/Java%20%E7%BC%96%E7%A8%8B%E6%80%9D%E6%83%B3%EF%BC%88%E7%AC%AC4%E7%89%88%EF%BC%89.pdf",
             pdfOpenParams: {
                 scrollbars: '0',
                 toolbar: '0',
                 statusbar: '0'
                 }
          }).embed("pdf");
     };
 </script> 
<style type="text/css">
#pdf {
    width: 900px;
    height: 500px;
    margin: 2em auto;
    border: 10px solid #6699FF;
}

#pdf p {
   padding: 1em;
}

#pdf object {
   display: block;
   border: solid 1px #666;
}
-->
</style>
<a href="http://${url}">Effective+Java中文版.pdf</a>
</head>
<body>
<div id="pdf"> <a href=""></a></div>
</body>
</html>