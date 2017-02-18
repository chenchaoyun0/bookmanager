<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
$(function(){
    $("#user_send").click(function(){
        var user_say=$("#user_say").val();
        $.ajax({
            type:'post',
            url:'user/chatWithRobot',
            dataType:'json', //返回json结果
            data:'user_say='+user_say,
            success:function(data){//返回json结果
                 $("#tuling").text(data["text"]);
                 if(data["list"]!=null){
                	 var txapi="<table>"; 
                	 for(var i=0; i<8; i++) 
                	 {
                		 txapi+="<tr><td><img height=80px src='"+data["list"][i]["icon"]+"'></img></td>";
                	     txapi+="<td><a style='font-size: 17px;font-family: '宋体'' target='_bank' href='"+data["list"][i]["detailurl"]+"'>"+data["list"][i]["article"]+"</a></td></tr>"; 
                	 }   
                	     txapi+="</table>"; 
                	 $('#news').append(txapi); 
                 }
            }
        });
    });
});
</script>
</head>
<body>
 <div align="center" style="border: 0px solid lime;">
    <div> 
        <br/>
            <font color="red" style="font-weight: bolder;">机器人小Cy:&nbsp;</font><span id="tuling" style="color: blue;font-weight: bolder;font-size: 22px;">你好，可以在下面跟我交流喔!</span>
        <br/>
        <br/>
        <input id="user_say" type="text" size="60"/>
        <button id="user_send" style="color: white;background-color: green;font-weight: bolder;">发送</button>
        </div>
    </div>
    <br/>
    <div id="news" align="center">
    </div>
</body>
</html>