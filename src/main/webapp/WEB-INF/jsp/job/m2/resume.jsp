
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
    <li><a href="${pageContext.request.contextPath}/indexHome"
     class="active">查看首页</a></li>
    <li><a href="#home" class="active">最近成果</a></li>
    <li><a href="#about">关于我</a></li>
    <li><a href="#portfolio">项目展示</a></li>
    <li><a href="#skills">技能与特长</a></li>
    <li><a href="#industries">工作经历</a></li>
    <li><a href="#myclients">Our Clients</a></li>
    <li><a href="#contact">联系我</a></li>
   </ul>
  </div>
  <div id="container">
   <!-- page container -->
   <div class="page" id="home">
    <!-- page home -->
    <div class="page_content">
     <div class="gf-slider">
      <!-- slider -->
      <ul class="slides">
       <li><img
        src="${pageContext.request.contextPath}/m2/images/01.jpg" alt="" />
        <p class="flex-caption">工作之余学习，搭建的gerrit+jenkins 持续集成系统</p></li>
       <li><img
        src="${pageContext.request.contextPath}/m2/images/02.jpg" alt="" />
        <p class="flex-caption">实习期练手，基于Spring、Springmvc、Mybatis开发的图书馆管理系统
         JavaWeb</p></li>
       <li><img
        src="${pageContext.request.contextPath}/m2/images/03.jpg" alt="" />
        <p class="flex-caption">大学时期，手机电子商城JavaWeb项目</p></li>
       <li><img
        src="${pageContext.request.contextPath}/m2/images/04.jpg" alt="" />
        <p class="flex-caption">实习期项目，Zookeeper 节点管理系统</p></li>
       <li><img
        src="${pageContext.request.contextPath}/m2/images/05.jpg" alt="" />
        <p class="flex-caption">工作之余学习，搭建的gerrit+jenkins 持续集成系统</p></li>
      </ul>
     </div>
     <div class="space"></div>
     <div class="clear"></div>
     <!-- services -->
     <div class="one_half first">
      <div class="column_content">
       <h4>Coded with Love!</h4>
       <img
        src="${pageContext.request.contextPath}/m2/images/coded-with-love.png"
        class="left no_border" alt=""
        style="margin-top: 10px; margin-right: 10px" />
       <p>
        <small>Lorem ipsum dolor sit amet, consectetur
         adipiscing elit. Maecenas at feugiat felis. Ut faucibus
         molestie turpis, sit amet scelerisque ipsum scelerisque quis.
         Quisque suscipit fermentum sodales.</small>
       </p>
      </div>
     </div>
     <div class="one_half last">
      <div class="column_content">
       <h4>Responsive Layout</h4>
       <img
        src="${pageContext.request.contextPath}/m2/images/responsive.png"
        class="left no_border" alt=""
        style="margin-top: 10px; margin-right: 10px" />
       <p>
        <small>Lorem ipsum dolor sit amet, consectetur
         adipiscing elit. Maecenas at feugiat felis. Ut faucibus
         molestie turpis, sit amet scelerisque ipsum scelerisque quis.
         Quisque suscipit fermentum sodales.</small>
       </p>
      </div>
     </div>
     <div class="space"></div>
     <div class="one_half first">
      <div class="column_content">
       <h4>Perfect for Portfolios</h4>
       <img
        src="${pageContext.request.contextPath}/m2/images/for-portfolio.png"
        class="left no_border" alt=""
        style="margin-top: 10px; margin-right: 10px" />
       <p>
        <small>Lorem ipsum dolor sit amet, consectetur
         adipiscing elit. Maecenas at feugiat felis. Ut faucibus
         molestie turpis, sit amet scelerisque ipsum scelerisque quis.
         Quisque suscipit fermentum sodales.</small>
       </p>
      </div>
     </div>
     <div class="one_half last">
      <div class="column_content">
       <h4>Easily Customizable</h4>
       <img
        src="${pageContext.request.contextPath}/m2/images/customizable.png"
        class="left no_border" alt=""
        style="margin-top: 10px; margin-right: 10px" />
       <p>
        <small>Lorem ipsum dolor sit amet, consectetur
         adipiscing elit. Maecenas at feugiat felis. Ut faucibus
         molestie turpis, sit amet scelerisque ipsum scelerisque quis.
         Quisque suscipit fermentum sodales.</small>
       </p>
      </div>
     </div>
     <div class="space"></div>
     <div class="one_half first">
      <div class="column_content">
       <h4>Image Gallery</h4>
       <img
        src="${pageContext.request.contextPath}/m2/images/image-gallery.png"
        class="left no_border" alt=""
        style="margin-top: 10px; margin-right: 10px" />
       <p>
        <small>Lorem ipsum dolor sit amet, consectetur
         adipiscing elit. Maecenas at feugiat felis. Ut faucibus
         molestie turpis, sit amet scelerisque ipsum scelerisque quis.
         Quisque suscipit fermentum sodales.</small>
       </p>
      </div>
     </div>
     <div class="one_half last">
      <div class="column_content">
       <h4>jQuery Powered</h4>
       <img
        src="${pageContext.request.contextPath}/m2/images/jquery-code.png"
        class="left no_border" alt=""
        style="margin-top: 10px; margin-right: 10px" />
       <p>
        <small>Lorem ipsum dolor sit amet, consectetur
         adipiscing elit. Maecenas at feugiat felis. Ut faucibus
         molestie turpis, sit amet scelerisque ipsum scelerisque quis.
         Quisque suscipit fermentum sodales.</small>
       </p>
      </div>
     </div>
     <div class="clear"></div>
    </div>
   </div>
   <div class="page" id="about">
    <!-- page about -->
    <h3 class="page_title">About Us</h3>
    <div class="page_content">
     <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
      sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
      Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
      nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
      reprehenderit in voluptate velit esse cillum dolore eu fugiat
      nulla pariatur. Excepteur sint occaecat cupidatat non proident,
      sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
     <h4 class="blue">Why Choose Us</h4>
     <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
      sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
      Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
      nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
      reprehenderit in voluptate velit esse cillum dolore eu fugiat
      nulla pariatur. Excepteur sint occaecat cupidatat non proident,
      sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
     <blockquote>
      Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
      eiusmod tempor incididunt ut labore et dolore magna aliqua.
      <p>
       <small><b>Sarfraz Shoukat</b> - Owner <a href="#">Greepit.com</a></small>
      </p>
     </blockquote>
     <div class="clear"></div>
    </div>
   </div>
   <div class="page" id="portfolio">
    <!-- page portfolio -->
    <h3 class="page_title">Portfolio</h3>
    <div class="page_content">
     <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
      sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
      Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
      nisi ut aliquip ex ea commodo consequat.</p>
     <ul id="works_filter">
      <li><a href="#" data-filter="*" class="selected">Show All</a></li>
      <li><a href="#" data-filter=".css">CSS</a></li>
      <li><a href="#" data-filter=".html_php">HTML / PHP</a></li>
      <li><a href="#" data-filter=".js">JavaScript</a></li>
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
       href="${pageContext.request.contextPath}/m2/images/photos/03.jpg">
       <img class="work css"
       src="${pageContext.request.contextPath}/m2/images/photos/03.jpg"
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
   <div class="page" id="skills">
    <!-- page skills -->
    <h3 class="page_title">Our Skills</h3>
    <div class="page_content">

     <div class="one_fourth first">
      <div class="column_content">
       <h4 class="blue">Photoshop</h4>
       <input class="knob" data-readonly="true" data-width="120"
        data-min="0" data-angleoffset="0" data-displayprevious="true"
        data-fgcolor="#cfdee7" data-bgcolor="#0d4667" value="65">
      </div>
     </div>
     <div class="one_fourth">
      <div class="column_content">
       <h4 class="blue">HTML5</h4>
       <input class="knob" data-readonly="true" data-width="120"
        data-min="0" data-angleoffset="0" data-displayprevious="true"
        value="45" data-fgcolor="#cfdee7" data-bgcolor="#0d4667">
      </div>
     </div>
     <div class="one_fourth">
      <div class="column_content">
       <h4 class="blue">jQuery</h4>
       <input class="knob" data-readonly="true" data-width="120"
        data-min="0" data-angleoffset="0" data-displayprevious="true"
        value="85" data-fgcolor="#cfdee7" data-bgcolor="#0d4667">
      </div>
     </div>
     <div class="one_fourth last">
      <div class="column_content">
       <h4 class="blue">CSS3</h4>
       <input class="knob" data-readonly="true" data-width="120"
        data-min="0" data-angleoffset="0" data-displayprevious="true"
        value="95" data-fgcolor="#cfdee7" data-bgcolor="#0d4667">
      </div>
     </div>
     <div class="clear"></div>
    </div>
   </div>
   <div class="copyrights">
    Collect from <a href="http://www.cssmoban.com/">企业网站模板</a>
   </div>
   <div class="page" id="industries">
    <!-- page industries -->
    <h3 class="page_title">Industries We Serve!</h3>
    <div class="page_content">
     <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
      sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
      Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
      nisi ut aliquip ex ea commodo consequat.</p>
     <div class="space"></div>
     <div class="clear"></div>
     <ul class="sublist">
      <li><a href="#">Freebies</a></li>
      <li><a href="#">Category Names</a></li>
      <li><a href="#">Graphic Design</a></li>
      <li><a href="#">Akay Akagunduz</a></li>
      <li><a href="#">News</a></li>
      <li><a href="#">Themeforest</a></li>
      <li><a href="#">Reviews</a></li>
      <li><a href="#">Links</a></li>
      <li><a href="#">Tutorials</a></li>
      <li><a href="#">Others</a></li>
      <li><a href="#">Web Development</a></li>
     </ul>
     <div class="clear"></div>
    </div>
   </div>
   <div class="page" id="myclients">
    <!-- page clients -->
    <h3 class="page_title">Our Clients</h3>
    <div class="page_content">
     <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
      sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
      Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
      nisi ut aliquip ex ea commodo consequat.</p>
     <div class="space"></div>
     <div class="clear"></div>
     <ul id="clients" class="grid">
      <li class="altoids"><a href="#" rel="altoids"> <img
        src="${pageContext.request.contextPath}/m2/images/clients/Altoids.png"
        alt="Altoids"></a></li>
      <li class="facebook"><a href="#" rel="facebook"> <img
        src="${pageContext.request.contextPath}/m2/images/clients/Facebook.png"
        alt="Facebook"></a></li>
      <li class="ge"><a href="#" rel="general-electric"> <img
        src="${pageContext.request.contextPath}/m2/images/clients/GE.png"
        alt="General Electric"></a></li>
      <li class="orbit"><a href="#" rel="orbit"> <img
        src="${pageContext.request.contextPath}/m2/images/clients/orbitlogo.png"
        alt="Orbit"></a></li>
      <li class="skittles"><a href="#" rel="skittles"> <img
        src="${pageContext.request.contextPath}/m2/images/clients/Skittles.png"
        alt="Skittles"></a></li>
      <li class="jameson"><a href="#" rel="jameson"> <img
        src="${pageContext.request.contextPath}/m2/images/clients/Jameson.png"
        alt="Jameson"></a></li>
      <li class="juicy_fruit"><a href="#" rel="juicy-fruit"> <img
        src="${pageContext.request.contextPath}/m2/images/clients/Juicy-Fruit.png"
        alt="Juicy Fruit"></a></li>
      <li class="microsoft"><a href="#" rel="microsoft"> <img
        src="${pageContext.request.contextPath}/m2/images/clients/Microsoft.png"
        alt="Microsoft"></a></li>
      <li class="a_e"><a href="#" rel="ae"> <img
        src="${pageContext.request.contextPath}/m2/images/clients/AE.png"
        alt="A&amp;E"></a></li>
      <li class="zynga"><a href="#" rel="zynga"> <img
        src="${pageContext.request.contextPath}/m2/images/clients/Zynga.png"
        alt="Zynga"></a></li>
      <li class="smuin"><a href="#" rel="smuin"> <img
        src="${pageContext.request.contextPath}/m2/images/clients/Smuin.png"
        alt="Smuin"></a></li>
      <li class="westfield"><a href="#" rel="westfield"> <img
        src="${pageContext.request.contextPath}/m2/images/clients/Westfield.png"
        alt="Westfield"></a></li>
     </ul>
     <div class="clear"></div>
    </div>
   </div>
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
       <button id="submit" class="button">Send Message</button>
      </form>
     </fieldset>
     <div class="clear"></div>
     <ul class="social_icons">
      <li><a href="#" id="fb" original-title="Join My Fan Club">
        <img
        src="${pageContext.request.contextPath}/m2/images/facebook.png"
        alt="Facebook" />
      </a></li>
      <li><a href="#" id="tw" original-title="Follow Me on Twitter">
        <img
        src="${pageContext.request.contextPath}/m2/images/twitter.png"
        alt="Twitter" />
      </a></li>
      <li><a href="#" id="ld" original-title="Find me on LinkedIn">
        <img
        src="${pageContext.request.contextPath}/m2/images/linkedin.png"
        alt="LinkedIn" />
      </a></li>
     </ul>
    </div>
   </div>
   <div class="footer">
    <p>&copy; 2017 .chenchaoyun0.</p>
    <p>
     More Templates <a href="http://39.108.0.229/" target="_blank"
      title="网站主页">陈超允-网站主页</a> - Collect from <a
      href="http://39.108.0.229/" title="网站主页" target="_blank">网站主页</a>
    </p>
   </div>
  </div>
 </div>
 <a class="gotop" href="#top">Top</a>
</body>
</html>