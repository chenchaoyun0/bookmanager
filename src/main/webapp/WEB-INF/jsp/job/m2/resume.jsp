
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>陈超允-个人简历</title>

<meta name="viewport" content="width=device-width,initial-scale=1">

<link rel="stylesheet"
 href="${pageContext.request.contextPath}/m2/styles/style.css"
 media="screen" />
<link rel="stylesheet"
 href="${pageContext.request.contextPath}/m2/styles/media-queries.css" />
<link rel="stylesheet"
 href="${pageContext.request.contextPath}/m2/flex-slider/flexslider.css"
 type="text/css" media="screen" />
<link
 href="${pageContext.request.contextPath}/m2/styles/prettyPhoto.css"
 rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/m2/styles/tipsy.css"
 rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript"
 src="${pageContext.request.contextPath}/m2/scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
 src="${pageContext.request.contextPath}/m2/flex-slider/jquery.flexslider-min.js"></script>
<script
 src="${pageContext.request.contextPath}/m2/scripts/jquery.prettyPhoto.js"
 type="text/javascript"></script>
<script
 src="${pageContext.request.contextPath}/m2/scripts/jquery.tipsy.js"
 type="text/javascript"></script>
<script
 src="${pageContext.request.contextPath}/m2/scripts/jquery.knob.js"
 type="text/javascript"></script>
<script type="text/javascript"
 src="${pageContext.request.contextPath}/m2/scripts/jquery.isotope.min.js"></script>
<script type="text/javascript"
 src="${pageContext.request.contextPath}/m2/scripts/jquery.smooth-scroll.min.js"></script>
<script type="text/javascript"
 src="${pageContext.request.contextPath}/m2/scripts/waypoints.min.js"></script>
<script type="text/javascript"
 src="${pageContext.request.contextPath}/m2/scripts/setup.js"></script>


</head>
<body>
 <div id="wrap">
  <!-- wrapper -->
  <div id="sidebar">
   <!-- the  sidebar -->
   <!-- logo -->
   <a href="#" id="logo"> <img
    src="${pageContext.request.contextPath}/m2/images/logo.png"
    height="180px" alt="" /></a>
   <!-- navigation menu -->
   <ul id="navigation">
    <li><a href="#home" class="active">Home</a></li>
    <li><a href="#skills">技能与特长</a></li>
    <li><a href="#about">关于我</a></li>
    <li><a href="#portfolio">项目展示</a></li>
    <li><a href="#contact">联系我</a></li>
    <li><a href="${pageContext.request.contextPath}/book/selectBookPages"
     class="active" target="_bank">项目首页</a></li>
    <li><a href="https://github.com/chenchaoyun0/bookmanager"
     class="active" target="_bank">项目gitHub</a></li>
      <li>
        <EMBED src=jj-qt.mp3 width=300 height=45 type=audio/mpeg loop="true" autostart="true" volume="100"></EMBED>
    </li>
     <!--
    <li><a href="${pageContext.request.contextPath}/downloadResumeDocx"
     class="active" target="_bank">简历下载</a></li>  -->
   </ul>
  </div>
  <div id="container">
   <!-- page container -->
   <div class="page" id="home">
 <marquee onmouseout="this.start()" onmouseover="this.stop()" behavior="scroll" scrollamount="5" width="560" style="color:white; font-size: 23px;font-family: 楷体; font-weight: bolder; width: 560px;">
    本网站近期不再维护，你现在看到的只是一个静态页面~作者要去研究新知识了哈哈哈！服务器资源紧张哎=-=奈何作者又是穷屌丝一个！你可以在该页面找到项目GitHub！别的千万别乱点！404！敬请期待下一版！
 </marquee>  
    <!-- page home -->
    <div class="page_content">
     <div class="gf-slider">
      <!-- slider -->
      <ul class="slides">
       <li><img
        src="${pageContext.request.contextPath}/m2/images/photos/01.jpg" alt="" />
        <p class="flex-caption">工作之余学习，搭建的gerrit+jenkins 持续集成系统</p></li>
       <li><img
        src="${pageContext.request.contextPath}/m2/images/photos/02.jpg" alt="" />
        <p class="flex-caption">实习期练手，基于Spring、Springmvc、Mybatis开发的图书馆管理系统
         JavaWeb</p></li>
       <li><img
        src="${pageContext.request.contextPath}/m2/images/photos/03.jpg" alt="" />
        <p class="flex-caption">大学时期，手机电子商城JavaWeb项目</p></li>
       <li><img
        src="${pageContext.request.contextPath}/m2/images/photos/04.jpg" alt="" />
        <p class="flex-caption">实习期项目，Zookeeper 节点管理系统</p></li>
       <li><img
        src="${pageContext.request.contextPath}/m2/images/photos/05.jpg" alt="" />
        <p class="flex-caption">工作之余学习，搭建的gerrit+jenkins 持续集成系统</p></li>
      </ul>
     </div>
     <div class="space"></div>
     <div class="clear"></div>
     <!-- services -->
     <div class="page" id="skills">
    <!-- page skills -->
    <h3 class="page_title">技术标签</h3>
    <div class="page_content">

     <div class="one_fourth first">
      <div class="column_content">
       <h4 class="blue" style="color: white;">Dubbo</h4>
       <input class="knob" data-readonly="true" data-width="120"
        data-min="0" data-angleoffset="0" data-displayprevious="true"
        data-fgcolor="#cfdee7" data-bgcolor="#0d4667" value="80">
      </div>
     </div>
     <div class="one_fourth">
      <div class="column_content">
       <h4 class="blue" style="color: white;">Zookeeper</h4>
       <input class="knob" data-readonly="true" data-width="120"
        data-min="0" data-angleoffset="0" data-displayprevious="true"
        value="70" data-fgcolor="#cfdee7" data-bgcolor="#0d4667">
      </div>
     </div>
     <div class="one_fourth">
      <div class="column_content">
       <h4 class="blue" style="color: white;">Spring</h4>
       <input class="knob" data-readonly="true" data-width="120"
        data-min="0" data-angleoffset="0" data-displayprevious="true"
        value="85" data-fgcolor="#cfdee7" data-bgcolor="#0d4667">
      </div>
     </div>
     <div class="one_fourth last">
      <div class="column_content">
       <h4 class="blue" style="color: white;">Git</h4>
       <input class="knob" data-readonly="true" data-width="120"
        data-min="0" data-angleoffset="0" data-displayprevious="true"
        value="90" data-fgcolor="#cfdee7" data-bgcolor="#0d4667">
      </div>
     </div>
     <div class="clear"></div>
    </div>
   </div>
   <div class="copyrights">
    Collect from <a href="#">网站首页</a>
   </div>
   <div class="page" id="industries">
    <!-- page industries -->
    <h3 class="page_title">基础必备</h3>
    <div class="page_content">
     <p>
     
     </p>
     <div class="space"></div>
     <div class="clear"></div>
     <ul class="sublist">
      <li style="font-size:17px;font-weight: bold;"><a href="#">SpringMVC</a></li>
      <li style="font-size:17px;font-weight: bold;"><a href="#">SpringBoot</a></li>
      <li style="font-size:17px;font-weight: bold;font-weight: bold;"><a href="#">Restful</a></li>
      <li style="font-size:17px;font-weight: bold;"><a href="#">Gerrit</a></li>
      <li style="font-size:17px;font-weight: bold;"><a href="#">Redis</a></li>
      <li style="font-size:17px;font-weight: bold;"><a href="#">ActiveMQ</a></li>
      <li style="font-size:17px;font-weight: bold;"><a href="#">Nginx</a></li>
      <li style="font-size:17px;font-weight: bold;"><a href="#">Apache</a></li>
      <li style="font-size:17px;font-weight: bold;"><a href="#">MySQL</a></li>
      <li style="font-size:17px;font-weight: bold;"><a href="#">Oracle</a></li>
      <li style="font-size:17px;font-weight: bold;"><a href="#">Mybatis</a></li>
      <li style="font-size:17px;font-weight: bold;"><a href="#">Hibernate</a></li>
     </ul>
     <div class="clear"></div>
      <blockquote style="font-size: 17px">
      <p>
       <b>项目gitHub:&nbsp;&nbsp;<a style="color: lime;" href="https://github.com/chenchaoyun0" target="_bank">https://github.com/chenchaoyun0</a></b>
      </p>
      <p>
       <b>博客:&nbsp;&nbsp;<a style="color: lime;" href="http://blog.csdn.net/sinat_22767969" target="_bank">http://blog.csdn.net/sinat_22767969</a></b>
      </p>
      <p>
       <b>Gerrit:&nbsp;&nbsp;<a style="color: lime;" href="http://39.107.126.75/gerrit" target="_bank">http://39.107.126.75/gerrit</a></b>
      </p>
      <p>
       <b>Jenkins:&nbsp;&nbsp;<a style="color: lime;" href="http://58.87.121.73:8081" target="_bank">http://www.longge1987.cn/jenkins/</a></b>
      </p>
      <p>
       <b>ActiveMQ:&nbsp;&nbsp;<a style="color: lime;" href="http://58.87.121.73:8161/admin/queues.jsp" target="_bank">http://www.longge1987.cn:8161/admin/queues.jsp</a></b>
      </p>
      <p>
       <b>nexus私服库:&nbsp;&nbsp;<a style="color: lime;" href="http://58.87.121.73:8080/nexus" target="_bank">http://www.longge1987.cn:8080/nexus/</a></b>
      </p>
      <p>
       <b>图书管理系统:&nbsp;&nbsp;<a style="color: lime;" href="${pageContext.request.contextPath}/indexHome" target="_bank">http://39.107.126.75/bookmanager/indexHome</a></b>
      </p>
      <p>
       <b>Zookeeper节点管理:&nbsp;&nbsp;<a style="color: lime;" href="http://39.105.33.58:8081/zkWeb" target="_bank">http://www.ccy123.cn/zkWeb/</a></b>
      </p>
      <p>
       <b>Dubbo:&nbsp;&nbsp;<a style="color: lime;" href="http://39.105.33.58:8081/dubbo-admin" target="_bank">http://www.ccy123.cn/dubbo-admin/</a></b>
      </p>
     </blockquote>
    </div>
   </div>
   </div>
   <div class="page" id="about">
    <!-- page about -->
    <h3 class="page_title">我的自评</h3>
    <div class="page_content">
     <p style="text-indent: 2em">
      我是一名热衷于IT行业的JAVA工程师，热爱编程，对技术有很大的求知欲，喜欢将多个人的技术合并到一起生成新的东西。我一直以“严谨、专注、虚心”鞭策自己，我喜欢学习，并且有较强的学习能力。<br />
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我不是一个简单纯粹的人，我追求卓越，具有Geek特质，我能做事做实事，我相信您的团队正需要像我这样的人加入。
     </p>
     <h4 class="blue">Java之开发历程</h4>
     <p  style="text-indent: 2em">
曾在大学期间，参加腾讯云校园计划，获云服务器与域名，用于学习Linux
并在大三利用周末时间参加校外培训，搭建个人网站。现今在阿里云与腾讯云均有应用服务。<br />
</p>
 <blockquote style="font-size: 15px">
      <p>
       <b>在校外培训机构表现成绩优异突出，担任过小组组长</b>
      </p>
      <p>
       <b>校外培训机构结训项目获机构评优并作为后期学员的参考模板。</b>
      </p>
      <p>
       <b>在2016年公司年会上，代表公司同期入职应届生做年终工作总结</b>
      </p>
     </blockquote>
     <div class="clear"></div>
    </div>
   </div>
   <div class="page" id="portfolio">
    <!-- page portfolio -->
    <h3 class="page_title">项目展示</h3>
    <div class="page_content">
     <p style="text-indent: 2em">
     做软件开发是一个不断学习的过程，你停住脚步就会被别人超越。刚好~很幸运，我喜欢技术，也喜欢这个学习的过程。别人觉得无聊的东西，我却乐在其中。
     </p>
     <ul id="works_filter">
      <li><a href="#" data-filter="*" class="selected">Show All</a></li>
      <li><a href="#" data-filter=".css">SpringBoot</a></li>
      <li><a href="#" data-filter=".html_php">Devps</a></li>
      <li><a href="#" data-filter=".js">Apps</a></li>
     </ul>
     <div class="clear"></div>
     <div id="works">
      <!-- works -->
      <a rel="prettyPhoto[gallery]"
       href="${pageContext.request.contextPath}/m2/images/photos/01.jpg">
       <img class="work js"
       src="${pageContext.request.contextPath}/m2/images/photos/01.jpg"
       alt="" />
      </a><a rel="prettyPhoto[gallery]"
       href="${pageContext.request.contextPath}/m2/images/photos/02.jpg">
       <img class="work css"
       src="${pageContext.request.contextPath}/m2/images/photos/02.jpg"
       alt="" />
      </a><a rel="prettyPhoto[gallery]"
       href="${pageContext.request.contextPath}/m2/images/photos/04.jpg">
       <img class="work html_php"
       src="${pageContext.request.contextPath}/m2/images/photos/04.jpg"
       alt="" />
      </a><a rel="prettyPhoto[gallery]"
       href="${pageContext.request.contextPath}/m2/images/photos/05.jpg">
       <img class="work html_php"
       src="${pageContext.request.contextPath}/m2/images/photos/05.jpg"
       alt="" />
      </a><a rel="prettyPhoto[gallery]"
       href="${pageContext.request.contextPath}/m2/images/photos/06.jpg">
       <img class="work css"
       src="${pageContext.request.contextPath}/m2/images/photos/06.jpg"
       alt="" />
      </a><a rel="prettyPhoto[gallery]"
       href="${pageContext.request.contextPath}/m2/images/photos/07.jpg">
       <img class="work js"
       src="${pageContext.request.contextPath}/m2/images/photos/07.jpg"
       alt="" />
      </a><a rel="prettyPhoto[gallery]"
       href="${pageContext.request.contextPath}/m2/images/photos/08.jpg">
       <img class="work html_php"
       src="${pageContext.request.contextPath}/m2/images/photos/08.jpg"
       alt="" />
      </a><a rel="prettyPhoto[gallery]"
       href="${pageContext.request.contextPath}/m2/images/photos/09.jpg">
       <img class="work js"
       src="${pageContext.request.contextPath}/m2/images/photos/09.jpg"
       alt="" />
      </a><a rel="prettyPhoto[gallery]"
       href="${pageContext.request.contextPath}/m2/images/photos/10.jpg">
       <img class="work html_php"
       src="${pageContext.request.contextPath}/m2/images/photos/10.jpg"
       alt="" />
      </a>
     </div>
     <div class="clear"></div>
    </div>
   </div>
   <!--  -->
   <div style="margin-left: 30px">
   <div class="one_half first">
      <div class="column_content">
       <h4>Coded in Love</h4>
       <img
        src="${pageContext.request.contextPath}/m2/images/coded-with-love.png"
        class="left no_border" alt=""
        style="margin-top: 10px; margin-right: 10px" />
       <p>
        <small>
        假如多年后，我也成为那所谓的大牛时，我将会以怎样的视角与深度去看待那最初的hello world ？我有所期待
        </small>
       </p>
      </div>
     </div>
     <div class="one_half last">
      <div class="column_content">
       <h4>Learn to accumulate</h4>
       <img
        src="${pageContext.request.contextPath}/m2/images/responsive.png"
        class="left no_border" alt=""
        style="margin-top: 10px; margin-right: 10px" />
       <p>
        <small>
        不积跬步，无以至千里；不积小流，无以成江海。
        </small>
       </p>
      </div>
     </div>
     <div class="space"></div>
     <div class="one_half first">
      <div class="column_content">
       <h4>Keep good habit</h4>
       <img
        src="${pageContext.request.contextPath}/m2/images/for-portfolio.png"
        class="left no_border" alt=""
        style="margin-top: 10px; margin-right: 10px" />
       <p>
        <small>
        习惯决定性格，性格决定成败
        </small>
       </p>
      </div>
     </div>
     <div class="one_half last">
      <div class="column_content">
       <h4>Speaking is art </h4>
       <img
        src="${pageContext.request.contextPath}/m2/images/customizable.png"
        class="left no_border" alt=""
        style="margin-top: 10px; margin-right: 10px" />
       <p>
        <small>
        说话是一种艺术，学会与人交流
        </small>
       </p>
      </div>
     </div>
     <div class="space"></div>
     <div class="one_half first">
      <div class="column_content">
       <h4>Modesty,kind of virtue</h4>
       <img
        src="${pageContext.request.contextPath}/m2/images/image-gallery.png"
        class="left no_border" alt=""
        style="margin-top: 10px; margin-right: 10px" />
       <p>
        <small>
        谦虚是一种美德，懂得谦让的人讨人喜欢
        </small>
       </p>
      </div>
     </div>
     <div class="one_half last">
      <div class="column_content">
       <h4>Responsibility</h4>
       <img
        src="${pageContext.request.contextPath}/m2/images/jquery-code.png"
        class="left no_border" alt=""
        style="margin-top: 10px; margin-right: 10px" />
       <p>
        <small>
        在岗一天则职责所在，不愧于人，无愧于心
        </small>
       </p>
      </div>
     </div>
     <div class="clear"></div>
    </div>
    </div>
   <!--  -->
   
   <div class="page" id="contact">
    <!-- page contact -->
    <h3 class="page_title">Let's Get in Touch</h3>
    <div class="page_content">
     <fieldset id="contact_form">
      <div id="msgs"></div>
      <form id="cform" name="cform" method="post" action="">
       <input type="text" id="name" name="name" value="Full Name*"
        onFocus="if(this.value == 'Full Name*') this.value = ''"
        onblur="if(this.value == '') this.value = 'Full Name*'" /> <input
        type="text" id="email" name="email" value="Email Address*"
        onFocus="if(this.value == 'Email Address*') this.value = ''"
        onblur="if(this.value == '') this.value = 'Email Address*'" />
       <input type="text" id="subject" name="subject" value="Subject*"
        onFocus="if(this.value == 'Subject*') this.value = ''"
        onblur="if(this.value == '') this.value = 'Subject*'" />
       <textarea id="msg" name="message"
        onFocus="if(this.value == 'Your Message*') this.value = ''"
        onblur="if(this.value == '') this.value = 'Your Message*'">Your Message*</textarea>
       <button id="submit" class="button" onclick="window.location.href='http://wpa.qq.com/msgrd?v=3&uin=873692191'">Send Message</button>
      </form>
     </fieldset>
     <div class="clear"></div>
     <ul class="social_icons">
      <li><a href="https://github.com/chenchaoyun0" target="_bank" id="fb" original-title="Join My Fan Club">
        <img height="26px"
        src="${pageContext.request.contextPath}/m2/images/github.png"
        alt="Facebook" />
      </a></li>
      <li><a target="_bank" href="https://www.linkedin.com/in/%E8%B6%85%E5%85%81-%E9%99%88-86881a102/" id="ld" original-title="Find me on LinkedIn">
        <img
        src="${pageContext.request.contextPath}/m2/images/linkedin.png"
        alt="LinkedIn" />
      </a></li>
      <li><a href="http://blog.csdn.net/sinat_22767969" target="_bank" id="tw" original-title="Follow Me on Twitter">
        <img height="26px"
        src="${pageContext.request.contextPath}/m2/images/csdn.png"
        alt="Twitter" />
      </a></li>
     </ul>
    </div>
   </div>
   <div class="footer">
    <p>&copy; 2017 .chenchaoyun0.</p>
    <p>
     More Templates <a href="http://39.107.126.75/" target="_blank"
      title="网站主页">陈超允-网站主页</a> - Collect from <a
      href="http://39.107.126.75/" title="网站主页" target="_blank">网站主页</a>
    </p>
   </div>
  </div>
 </div>
 <a class="gotop" href="#top">Top</a>
</body>
</html>