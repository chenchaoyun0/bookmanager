<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数通天下- 找不到页面</title>
        <style type="text/css">
            html,body,div,ul,ol,li,dl,dt,dd,h1,h2,h3,h4,h5,h6,p{margin:0; padding:0} h1,h2,h3,h4,h5,h6,em{font-size:1em; font-weight:normal; font-style:normal} a{text-decoration:none;color:#515151} ul, li{list-style:none} body{font-family:"微软雅黑","Microsoft Yahei",Arial,Helvetica,sans-serif,"宋体";font-size:14px;color:#515151} .header_mz{position:relative;z-index:999;background-color:#FFF} .header{width:1080px;height:77px;margin:0 auto;overflow:hidden} .header .head_logo{float:left;display:inline;margin:29px 0 0 20px;width:115px;height:20px;background:url('http://www2.res.meizu.com/zh_cn/images/common/logo-new.png') no-repeat;text-indent:-9999px}
            .tips_cont{height:440px;padding-top:215px;width:1080px;margin:0px auto;overflow:hidden} .tips_cont h2{font-size:30px;color:#3a3a3a;padding-bottom:10px} .tips_cont h5{font-size:18px;color:#3a3a3a;padding-bottom:58px} .img_tips{float:left;margin:0px 88px 0px 155px} .message_tips{float:left;margin-top:75px} .btn_tips{display:block;width:265px;height:55px;background-color:#31A5E7;font-size:24px;color:#FFF;text-align:center;line-height:55px}
        </style>
    </head>

    <body>
            <div class="header" style="margin-top: 10px;">
                <a href="${pageContext.request.contextPath}/book/selectBookPages" >
                    <img src="${pageContext.request.contextPath}//img/regist/sttxlogo.png" alt="" />
                </a>
            </div>
        <div class="tips_cont" style="margin-top: -125px;">
            <div class="img_tips"><img src="${pageContext.request.contextPath}/img/error/panda404.jpg"></div>
            <div class="message_tips">
                <h2>哎呀！ 找不到页面了</h2>
                <h5>不要伤心，可能是网址错了呢，重新核对一下吧。</h5>
                <a class="btn_tips" href="${pageContext.request.contextPath}/book/selectBookPages">返回数通天下图书城</a>
            </div>
        </div>

</body>
</html>