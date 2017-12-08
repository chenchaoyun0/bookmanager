<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    		function tz(){
    			var pageNo=document.getElementById("pageNo").value;
    			location.href="${pages.url}?pageNo="+pageNo+"&${pages.strWhere}";
    		}
</script>
    <center style="color: black;font-size: 14px;">
    	&nbsp;&nbsp;&nbsp;
    	<span style="font-size: 15px;color:green;">第${pages.pageNo }页/共${pages.pages}页&nbsp;</span>
    	<a href="${pages.url}?pageNo=1&${pages.strWhere}" style="font-size: 15px;color: blue;">首页&nbsp;</a>
    	<c:if test="${pages.pageNo>1}">
	    	<a style="font-size: 15px;color: blue;" href="${pages.url}?pageNo=${pages.pageNo-1}&${pages.strWhere}">上一页&nbsp;</a>
    	</c:if>
    	
    		<!-- 设置显示页码个数 -->
    	<c:choose>
    		<c:when test="${pages.pages<=8}">
    			<c:set var="begin" value="1" />
    			<c:set var="end" value="${pages.pages}"/>
    		</c:when>
    		<c:otherwise>
    				<c:set var="begin" value="${pages.pageNo-3 }" />
					<c:set var="end" value="${pages.pageNo+4 }" />
					<c:if test="${begin<1}">
						<c:set var="begin" value="1" />
						<c:set var="end" value="8" />
					</c:if>
					<c:if test="${end >pages.pages}">
						<c:set var="begin" value="${pages.pages -7 }" />
						<c:set var="end" value="${pages.pages }" />
					</c:if>
    			</c:otherwise>
    	</c:choose>
    	
    	<c:forEach var="i" begin="${begin}" end="${end}">
    		<c:choose>
    			<c:when test="${i eq pages.pageNo}">
    				<font color="red" style="font-size: 16px;color: red;">${i}</font>&nbsp;
    			</c:when>
    			<c:otherwise>
    				<a href="${pages.url}?pageNo=${i}&${pages.strWhere}" style="font-size: 15px;color: blue;">[${i}]</a>&nbsp;
    			</c:otherwise>
    		</c:choose>
    		
    	</c:forEach>
    	
    	<c:if test="${pages.pageNo<pages.pages}">
	    	<a style="font-size: 15px;color: blue;" href="${pages.url}?pageNo=${pages.pageNo+1}&${pages.strWhere}">下一页&nbsp;</a>
    	</c:if>
    	<!-- 跳页 -->
    	<a href="${pages.url}?pageNo=${pages.pages}&${pages.strWhere}" style="font-size: 15px;color: blue;">尾页</a>&nbsp;
    		<%-- 跳转到：<input type="number" value="${pages.pageNo}" name="pageNo" id="pageNo" size="3" style="width:40px;border: 1px solid #00AAEE;font-size: 15px;"/>
    		&nbsp;<button name="bttz" onclick="tz()">跳转</button>--%>
    	&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 15px;color:green;">总共${pages.total }条数据</span>
    	&nbsp;&nbsp;&nbsp;
    	&nbsp; 
    </center>
