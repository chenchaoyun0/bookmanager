function initdata(type,pageNo,pageSize){	
	$.ajax({
	    url:"order/orderBook",    //请求的url地址
	    //dataType:"json",   //返回格式为json
	    //async:true,//请求是否异步，默认为异步，这也是ajax重要特性
	    data:{	"userId":"2F1CE4A814744C8186F8E3B6902CA180",
	    		"pageNo":1,
	    		"pageSize":10},    //参数值
	    type:"POST",   //请求方式
	    beforeSend:function(){
	    	//alert("beforeSend");
	        //请求前的处理
	    },
	    success:function(req){
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

function setdata(data){
	console.log(data);
	var html = "    <tr>" + "<th>图片</th>" + "<th>图书id</th>" + "<th>图书所有者</th>"
			+ "<th>标题名称</th>" + "<th>作者</th>" + "<th>描述</th>" + "<th>总数量</th>"
			+ "<th>剩余数量</th>" + "<th>上架时间</th>" + "</tr>";
	for (var i = 0; i < data.length; i++) {
		html += "<tr>";
//		html += "<td>" + data[i].itemId + "</td>";
//		html += "<td>" + data[i].bookId + "</td>";
//		html += "<td>" + data[i].userId + "</td>";
		html += "<td>" + data[i].lendCount + "</td>";
		html += "<td>" + data[i].lendTime + "</td>";
		html += "<td>" + data[i].returnTime + "</td>";
		html += "<td>" + data[i].itemTotal + "</td>";
		html += "<td>" + data[i].itemStatus + "</td>";
		html += "<td>" + data[i].itemFlag + "</td>";
		html += "</tr>";
	}
	$("#data").html(html);
}