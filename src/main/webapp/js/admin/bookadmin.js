function initdata(pageNo,pageSize){	
	$.ajax({
	    url:"book/adminData",    //请求的url地址
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
	var html = "    <tr>" + "<th>图片</th>" + "<th>编号</th>"
			+ "<th>图书所有者</th>" + "<th>名称</th>" 
			+ "<th>作者</th>" +"<th>出版社</th>"+"<th>价格</th>"
			+ "<th>描述</th>"+"<th>数量</th>"+"<th>在架数量</th>"+"<th>状态</th>"+"<th>操作</th>"+"</tr>";
	for (var i = 0; i < data.length; i++) {
		html += "<tr>";	//注意，用map返回的话，字段名称与数据库的一样，用下划线方式
		var str=data[i].bookImg.split(",");
		html += "<td>" +"<img style='height: 40px;width:40px;' src="+str[0]+">" + "</td>";
		html += "<td>" + data[i].bookNo + "</td>";
		html += "<td>" + data[i].user.loginName + "</td>";
		html += "<td>" + data[i].bookName + "</td>";
		html += "<td>" + data[i].bookAuthor + "</td>";
		html += "<td>" + data[i].bookHouse + "</td>";
		html += "<td>" + data[i].bookPrice + "</td>";
		html += "<td>" + data[i].bookDesc + "</td>";
		html += "<td>" + data[i].bookCount + "</td>";
		html += "<td>" + data[i].bookRemain + "</td>";
		if(data[i].bookStatus==1){
		html += "<td>" + "在架中" + "</td>";	
		html +="<td>" + "<a href=javascript:unmountBook('"+data[i].bookId+"');>下架</a>" + "</td>";
		}else{
		html += "<td>" + "已下架" + "</td>";	
		html += "<td>" + "<a href=javascript:mountBook('"+data[i].bookId+"');>上架</a>" + "</td>";	
		}
		html += "</tr>";
		
	}
	
	$("#data").html(html);
}

function unmountBook(bookId){
	$.post("book/unmountBook/"+bookId,function(data){
    		if(data>=0){
	            alert("下架成功！")
	            initdata(1,5);
	            return;}
	    	else {
	    		alert("服务器错误！")
	    		return;
	    	}
            initdata(1,5);
	});
}

function mountBook(bookId){
	$.post("book/mountBook/"+bookId,function(data){
			if(data>=0){
	            alert("上架成功！")
	            initdata(1,5);
	            return;}
	    	else {
	    		alert("服务器错误！")
	    		return;
	    	}
            initdata(1,5);
	});
}