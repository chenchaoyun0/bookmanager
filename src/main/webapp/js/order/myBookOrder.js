function initdata(pageNo,pageSize){	
	var userId=$("#userId").val();
	console.log(userId);
	
	$.ajax({
	    url:"/bookmanager/order/orderBook",    //请求的url地址
	    //dataType:"json",   //返回格式为json
	    async:false,//请求是否异步，默认为异步，这也是ajax重要特性
	    data:{	"userId":userId,
	    		"pageNo":pageNo,
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
	    	alert("error");
	        //请求出错处理
	    }
	});

}

function setdata(req){
	console.log(req);
	data=req.dataList;
	$("#PageCount").val(req.total);
	console.log(data);
	var html = "    <tr>" + "<th>图片</th>" + "<th>图书所有者</th>"
			+ "<th>标题名称</th>" + "<th>作者</th>" + "<th>描述</th>" 
			+ "<th>借出时间</th>" +"<th>状态</th>"+"<th>还书日期</th>"+ "<th>操作</th>"+"</tr>";
	for (var i = 0; i < data.length; i++) {
		html += "<tr>";	//注意，用map返回的话，字段名称与数据库的一样，用下划线方式
		var str=data[i].book_Img.split(",");
		html += "<td>" +"<img style='height: 40px;width:40px;' src="+str[0]+">" + "</td>";
		html += "<td>" + data[i].real_Name + "</td>";
		html += "<td>" + data[i].book_Name + "</td>";
		html += "<td>" + data[i].book_Author + "</td>";
		html += "<td>" + data[i].book_Desc + "</td>";
		html += "<td>" + data[i].lend_Time + "</td>";
		html += "<td>";
		if(data[i].item_Status == 0)  html += "正在申请借书";
		if(data[i].item_Status == 1)  html += "书籍未归还";
		if(data[i].item_Status == 2)  html += "正在申请还书";
		if(data[i].item_Status == -3) html += "还书失败";
		html += "</td>";
		
		if(data[i].item_Status == 1){
		html += "<td>" + data[i].return_time + "</td>";
		html +="<td>" + "<a href=javascript:returnBook('"+data[i].item_Id+"');>还书</a>" + "</td>";
		}
		else if(data[i].item_Status == -3){
			html += "<td>" + data[i].return_time + "</td>";
			html += "<td>" + "<a href=javascript:returnBook('"+data[i].item_Id+"');>再次还书</a>" + "</td>";
		}else{
		html +="<td>" + "-" + "</td>";
		html +="<td>" + "-" + "</td>";
		}
		
		html += "</tr>";
		
	}
	
	$("#data").html(html);
}

function returnBook(itemId){
	$.post("/bookmanager/order/returnBook/"+itemId,function(data){
            alert("还书成功！等待管理员审核。")
            window.location.reload();
	});
}