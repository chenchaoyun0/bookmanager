function initdata(pageNo,pageSize){	
	$.ajax({
	    url:"user/adminData",    //请求的url地址
	    //dataType:"json",   //返回格式为json
	    async:false,//请求是否异步，默认为异步，这也是ajax重要特性
	    data:{	"pageNo":pageNo,
	    		"pageSize":pageSize},    //参数值
	    type:"POST",   //请求方式
	    beforeSend:function(){
	    	//alert("beforeSend");
	        //请求前的处理
	    },
	    success:function(req){
	    	console.log(req);
	    	setdata(req);
	        //请求成功时处理
	    },
	    complete:function(){
	    	//alert("complete");
	        //请求完成的处理
	    },
	    error:function(){
	    	console.log(error);
	        //请求出错处理
	    }
	});

}

function setdata(req){
	console.log(req);
	data=req.dataList;
	$("#PageCount").val(req.total);
	console.log(data);
	var html = "    <tr>"+"<th>头像</th>" + "<th>登录名称</th>" + "<th>真实名称</th>"
			+ "<th>性别</th>"+ "<th>邮件</th>" + "<th>电话</th>" + "<th>出生日期</th>" 
			+ "<th>注册时间</th>" + "<th>操作</th>"+"</tr>";
	for (var i = 0; i < data.length; i++) {
		html += "<tr>";	//注意，用map返回的话，字段名称与数据库的一样，用下划线方式
		var str=data[i].userHead;
		html += "<td>" +"<img style='height: 40px;width:40px;' src="+str+"/>" + "</td>";
		html += "<td>" + data[i].loginName + "</td>";
		html += "<td>" + data[i].realName + "</td>";
		
		if(data[i].userSex==1)
			html += "<td>" + "男" + "</td>";
		else
			html += "<td>" + "女" + "</td>";
		
		html += "<td>" + data[i].userEmail + "</td>";
		html += "<td>" + data[i].userTel + "</td>";
		html += "<td>" + data[i].userBirthday+"</td>";
		html += "<td>" + data[i].userRegisttime+"</td>";
		if(data[i].userRole == 0){
		html +="<td>" + "<a href=javascript:updatePermission('"+data[i].userId+"');>升级为管理员</a>" + "</td>";
		}else{
		html +="<td>" + "-" + "</td>";
		}
		
		html += "</tr>";
		
	}
	
	$("#data").html(html);
}

function updatePermission(userId){
	$.post("user/updatePermission/"+userId,function(data){
            alert("已升级为管理员")
            initdata(1,5);
	});
}