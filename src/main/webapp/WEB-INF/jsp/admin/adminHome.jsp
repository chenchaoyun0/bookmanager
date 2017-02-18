<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员</title>
    <script type="text/javascript" src="/bookmanager/js/jquery-1.11.1.min.js"></script>
</head>
<body>
     <jsp:include page="../public/top.jsp"></jsp:include> 
    <link rel="stylesheet" href="/bookmanager/css/bootstrap.min.css">
    <link rel="stylesheet" href="/bookmanager/css/table.css">
    <script type="text/javascript" src="/bookmanager/js/page/jqPaginator.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
    <style type="text/css">
    
    #topbar li{
      margin:30px;
      float:left;
    }
    
    </style>
    <div>
    <ul id="topbar">
    <li><a href="user/admin">人员管理</a></li>
    <li><a href="book/admin">书籍管理</a></li>
    <li><a href="order/admin">订单管理</a></li>
    </ul>
    <form action=""></form>
    </div>
    <div id="user" style="margin-top:20px">
    <table id="data">
    </table>
    <div style="float:right">
        <ul class="pagination" id="pagination">
        </ul>
        <input type="hidden" id="startPosition" value="">
        <!--总共有多少条目 -->
        <input type="hidden" id="PageCount" runat="server" value="" />
        <!-- 每页显示多少条目 -->
        <input type="hidden" id="PageSize" runat="server" value="5" />
        <!-- 计算出来，总共有多少页 -->
        <input type="hidden" id="countindex" runat="server"/>
        <!--设置最多显示的页码数 可以手动设置 默认为7-->
        <input type="hidden" id="visiblePages" runat="server" value="10" />
        </div>
    </div>

    <script type="text/javascript">
    $(function(){
        initdata(1,5);
    });
    
     function loadData(num) {
         $("#PageCount").val(num);
     }
    </script>
    <script type="text/javascript" src="/bookmanager/js/admin/useradmin.js"></script>
    <script type="text/javascript" src="/bookmanager/js/page/myPage.js"></script>
    
</body>
</html>