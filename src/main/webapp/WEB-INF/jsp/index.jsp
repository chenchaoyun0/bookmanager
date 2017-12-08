<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/page/jqPaginator.min.js"></script>
    <script type="text/javascript">
        function loadData(num) {
            $("#PageCount").val("30");
        }
    </script>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    </div>
    <div>
    <table id="data">

    
    </table>
    <div style="float:right">
        <ul class="pagination" id="pagination">
        </ul>
        <input type="hidden" id="startPosition" value="">
        <!--总共有多少条目 -->
        <input type="hidden" id="PageCount" runat="server" value="" />
        <!-- 每页显示多少条目 -->
        <input type="hidden" id="PageSize" runat="server" value="10" />
        <!-- 计算出来，总共有多少页 -->
        <input type="hidden" id="countindex" runat="server"/>
        <!--设置最多显示的页码数 可以手动设置 默认为7-->
        <input type="hidden" id="visiblePages" runat="server" value="10" />
        </div>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/page/myPage.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
    </form>
</body>
</html>
