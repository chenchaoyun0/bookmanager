function initdata(pageNo,pageSize){	
	
	$.ajax({
	    url:"/bookmanager/order/adminData",    //请求的url地址
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
	var html = "    <tr>"+ "<th>借书人</th>"+ "<th>借出时间</th>"
			+ "<th>借书册数</th>"+ "<th>书主人</th>"+ "<th>书名</th>" + "<th>状态</th>" + "<th>操作</th>"+"</tr>";
	for (var i = 0; i < data.length; i++) {
		html += "<tr>";	//注意，用map返回的话，字段名称与数据库的一样，用下划线方式
		if(data[i].user==null)
			html+="<td>-</td>"
		else
			html += "<td>" + data[i].user.loginName + "</td>";
		html += "<td>" + data[i].lendTime + "</td>";
		html += "<td>" + data[i].lendCount + "</td>";
		html += "<td>" + data[i].book.user.loginName + "</td>";
		html += "<td>" + data[i].book.bookName + "</td>";
		if(data[i].itemStatus == 0){
			html += "<td>"+"正在申请借书"+ "</td>";
			html += "<td>"+"<a href=javascript:updateOrderItem('"+data[i].bookId+"','"+data[i].itemId+"',1);>同意申请</a>";
			html += "<a style='display:block' href=javascript:updateOrderItem('"+data[i].bookId+"','"+data[i].itemId+"',-1);>拒绝申请</a>"+ "</td>";}
		else if(data[i].itemStatus == -1){  
			html += "<td>"+"借书申请已拒绝"+ "</td>";
			html += "<td>"+"-"+ "</td>";}
		else if(data[i].itemStatus == 1){  
			html +="<td>"+"书籍正在借出"+ "</td>";
			html += "<td>"+"-"+ "</td>";}
		else if(data[i].itemStatus == 2){  
			html += "<td>"+"正在申请还书"+ "</td>";
			html += "<td>"+"<a href=javascript:updateOrderItem('"+data[i].bookId+"','"+data[i].itemId+"',3);>同意申请</a>";
			html += "<a style='display:block' href=javascript:updateOrderItem('"+data[i].bookId+"','"+data[i].itemId+"',-3);>拒绝申请</a>"+ "</td>";}
		else if(data[i].itemStatus == 3){
			html +="<td>"+"已还书"+ "</td>";
			html += "<td>"+"-"+ "</td>";}
		else if(data[i].itemStatus == -3){
			html +="<td>"+"还书申请已拒绝"+ "</td>";
			html += "<td>"+"-"+ "</td>";}
		else {
			html +="<td>"+"-"+ "</td>";
			html += "<td>"+"-"+ "</td>";}
		html += "</tr>";
		
	}
	
	$("#data").html(html);
}

function updateOrderItem(bookId,itemId,status){
	$.post("/bookmanager/order/updateOrderItem",{bookId:bookId,itemId:itemId,status:status},function(data){
		if(data==-1){
			alert("该书已被借完。请拒绝该请求。");
/*            var txt=  "该书已被借完。请拒绝该请求。";
            window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);*/
		}else{
            alert("操作成功！")
            initdata(1,5);
		}
	});
}