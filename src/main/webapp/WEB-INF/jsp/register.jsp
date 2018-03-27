<%@page import="org.springframework.web.context.annotation.SessionScope"%>
<%@page import="org.apache.coyote.Adapter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>AppManager! | </title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath }/statics/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath }/statics/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${pageContext.request.contextPath }/statics/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="https://colorlib.com/polygon/gentelella/css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath }/statics/build/css/custom.min.css" rel="stylesheet">
  	<style type="text/css">
  	form > div{
  	
  	}
  	.info{
  		height:20px;
  		
  	}
  	</style>
  </head>
<body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form" >
          <section class="login_content">
            <form action="${pageContext.request.contextPath }/register" method="post">
              <h1>${idtf }注册</h1>
              <div >
               	<b style="display:inline-block;width:72px;">用户编码：</b>
               	<input type="text"  placeholder="用户编码" required name="userCode" id="userCode" style="width:200px;margin-bottom: 0;"/>   
               	<div class="info"></div>    	
              </div>
             
              <div>
              <b style="display:inline-block;width:72px;">用户名：</b>
               <input type="text" placeholder="用户名" required name="userName" id="userName" style="width:200px;margin-bottom: 0;"/>
				<div class="info"></div> 
              </div>
              <div>
              <b style="display:inline-block;width:72px;">密码：</b>
                <input type="password" placeholder="密码" required name="userPassword" id="userPassword" style="width:200px;margin-bottom: 0;"/>
              	<div class="info"></div>
              </div>
              <div>
              	<b style="display:inline-block;width:72px;">确认密码：</b>
                <input type="password" placeholder="确认密码" required name="confirmUserPassword" id="confirmUserPassword" style="width:200px;margin-bottom: 0;"/>
              	<div class="info"></div>
              </div>
              
              <div>
              <b style="display:inline-block;width:72px;">邮箱地址：</b>
                <input type="email"  placeholder="邮箱" required name="email" id="email" style="width:200px;margin-bottom: 0;"/>
              	<div class="info"></div>
              </div>
              <div>
              <b style="display:inline-block;width:72px;">用户信息：</b>
                <input type="text" placeholder="备注信息" required name="info" id="info" style="width:200px;margin-bottom: 0;"/>
              <div class="info"></div>
              </div>
              <div>
              
                <input type="submit" class="btn btn-default submit" value="提交" style="margin-left:150px;"/>
                
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-paw"></i> App信息管理平台!</h1>
                  <p>©2018 All Rights Reserved. AppManager is a system to manage applications.</p>
                </div>
              </div>
            </form>
          </section>
        </div>

      </div>
    </div>
    
  	<script src="${pageContext.request.contextPath }/statics/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath }/statics/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="${pageContext.request.contextPath }/statics/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="${pageContext.request.contextPath }/statics/vendors/nprogress/nprogress.js"></script>
    <!-- validator -->
    <!--<script src="../vendors/validator/validator.js"></script>-->
<!--自定义js表单验证-->
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/statics/build/js/addAppForm.js" ></script> --%>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath }/statics/build/js/custom.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/statics/build/js/addUser.js"></script>  
  </body>
</html>
