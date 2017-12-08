$(function(){
    var userEmail=$("#userEmail");
    userEmail.focus();

    $("#userEmail").blur(function(){
        var userEmail=$("#userEmail").val();
        $.post("user/existUserEmail",{userEmail:userEmail},function(msg){
                var email_re=new RegExp("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$","g");
                var tip_email=$("#tip_email");
                if(!email_re.test(userEmail)){
                        tip_email.css({"color":"red"});
                        tip_email.html("请填写正确的邮箱格式");
                }
//                else if(userEmail.indexOf("sttxtech.com")==-1){
//                    tip_email.css({"color":"red"});
//                    tip_email.html("请使用数通邮箱");
//                }
                else if(msg=="true"){
                    tip_email.css({"color":"red"});
                    tip_email.html("该邮箱已注册");
                }else{
                    tip_email.css({"color":"#7ABD54"});
                    tip_email.html("可用");
                }   
        });
    });
    
    $("#userTel").blur(function(){
        var userTel=$("#userTel").val();
        if(userTel.match(/^1[3|5|8][0-9]\d{8}$/)){
        	$.ajax({
        		type:'post',
        		url:'user/userTelHome.action',
        		dataType:'json', //返回json结果
        		data:'userTel='+userTel,
        		success:function(data){//返回json结果
//        			alert(data['retData']['carrier']);
        			$("#telHome").text(data['retData']['carrier']).css("color","#7ABD54");
        		}
        		
        	});
        }else{
//        	$("#telHome").text("手机号码格式不正确");
//        	$("#telHome").css({"color":"red"});
//            alert("手机号码格式不正确");
//            $("#userTel").focus();
        }
    });
    
    $("#loginName").blur(function(){
        var loginName=$("#loginName").val();
        $.post("user/existLoginName.action",{loginName:loginName},function(mes){
                var loginName_re=new RegExp("^[a-zA-z][a-zA-Z0-9_]{2,13}$","g");
                var tip_username=$("#tip_username");
                if(!loginName_re.test(loginName)){
                        tip_username.css({"color":"red"});
                        tip_username.html("请注意用户名格式");
                }else if(mes=="true"){
                    tip_username.css({"color":"red"});
                    tip_username.html("该用户已存在");
                }else{
                    tip_username.css({"color":"#7ABD54"});
                    tip_username.html("可用");
                }
        });
        
    });
    
});

/**********************************/
function sou(){
    alert($("souinp").value)
    document.forms["sou"].submit();
}
function inpch(){
    var souinp=document.getElementById("souinp");
    souinp.value="";
    souinp.style.color="black";
    souinp.style.fontSize="17px";
}
/* function showtip(){
    var souinp=document.getElementById("souinp");
    souinp.value="请输入您要的机型";
    souinp.style.color="#DDDDDD";
    souinp.style.fontSize="14px";
} */
/**********AJAX**********************/
/*********对输入的校检*****************/
function emaonf(){
    tip_email.innerHTML="请填写您的真实的邮箱地址，我们会发邮件通知您";
}
function usnonf(){
    tip_username.innerHTML="由字母和数字，并且数字不能开头，长度为5-10位"
}
function pasonb(){
    var userPwd=document.getElementById("userPwd").value;
    var password_re=new RegExp("[0-9a-zA-Z]{6}","g");
    var tip_password=document.getElementById("tip_password");
    if(password_re.exec(userPwd)==null){
        tip_password.style.color="red";
        return true;    
    }
    tip_password.style.color="#7ABD54";
    tip_password.innerHTML="可用";
    return false;
}
function pasonf(){
    tip_password.innerHTML="请填写登录密码, 最小长度为 6 个字符";
}
function cmponb(){
    var compassword=document.getElementById("compassword").value;
    var compassword_re=new RegExp("[0-9a-zA-Z]{6}","g");
    var tip_compassword=document.getElementById("tip_compassword");
    if(compassword_re.exec(compassword)==null){
        tip_compassword.style.color="red";
        /* document.getElementById("compassword").focus(); */
        return true;
    }
    tip_compassword.style.color="#7ABD54";
    tip_compassword.innerHTML="可用";
    return comparePassword();
}

function comparePassword(){
    var userPwd=document.getElementById("userPwd").value;
    var compassword=document.getElementById("compassword").value;
    if(userPwd!=compassword){
        var tip_compassword=document.getElementById("tip_compassword");
        tip_compassword.style.color="red";
        tip_compassword.innerHTML="登录密码输入不一致";
        return true;
    }
    return false;
}

function cmponf(){
    tip_compassword.innerHTML="请填确认登录密码";
}
/*支付密码*/
function payonb(){
    var paypassword=document.getElementById("paypassword").value;
    var paypassword_re=new RegExp("[0-9a-zA-Z]{6}","g");
    var tip_paypassword=document.getElementById("tip_paypassword");
    if(paypassword_re.exec(paypassword)==null){
        tip_paypassword.style.color="red";
        return true;
    }
    tip_paypassword.style.color="#7ABD54";
    tip_paypassword.innerHTML="可用";
    return false;
}
function payonf(){
    var tip_paypassword=document.getElementById("tip_paypassword");
    tip_paypassword.innerHTML="请填写支付密码, 最小长度为 6 个字符";
}
function cmpponb(){
    var comppassword=document.getElementById("comppassword").value;
    var comppassword_re=new RegExp("[0-9a-zA-Z]{6}","g");
    var tip_compassword=document.getElementById("tip_comppassword");
    if(comppassword_re.exec(comppassword)==null){
        tip_compassword.style.color="red";
        return true;
    }
    tip_comppassword.style.color="#7ABD54";
    tip_comppassword.innerHTML="可用";
    return comparePaypsd();
}

function comparePaypsd(){
    var paypassword=document.getElementById("paypassword").value;
    var comppassword=document.getElementById("comppassword").value;
    if(paypassword!=comppassword){
        var tip_compassword=document.getElementById("tip_comppassword");
        tip_compassword.style.color="red";
        tip_comppassword.innerHTML="支付密码输入不一致";
        return true;
    }
    return false;
}

function cmpponf(){
    tip_comppassword.innerHTML="请确认支付密码";
}
/***********************************************/
function checkInput(){
    var tip_email=document.getElementById("tip_email");
    var tip_username=document.getElementById("tip_username");
    if(tip_email.innerHTML!=="可用"){
        return false;
    }
    if(tip_username.innerHTML!=="可用"){
        return false;
    }
    if(pasonb()){
        return false;
    }
    if(cmponb()){
        return false;
    }
    if(payonb()){
        return false;
    }
    if(cmpponb()){
        return false;
    }
    return true;
} 
function _change(){
	var imgEle=document.getElementById("imgv");
	imgEle.src="util/verifyCode.action?a="+new Date().getTime();
}