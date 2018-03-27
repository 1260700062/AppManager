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
  </head>
<body class="login">
<%
	/* Object bu = session.getAttribute("BackendUser");
	Object du = session.getAttribute("DevUser"); */
	String identify = null;
	Object idt = session.getAttribute("identify");		
	if(idt==null){
		Object id = request.getParameter("identify");		
		if(id == null){
			response.sendRedirect("beforeLogin");
		}else{
			identify = id.toString();
			session.setAttribute("identify", identify);
		} 
	}else{
		identify = idt.toString();
	}
	
	
%>
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form action="${pageContext.request.contextPath }/login" method="post">
              <h1>欢迎<%=identify==null?"":identify.equals("manager")?"管理员":"开发者" %>登陆</h1>
              <div>
                <input type="text" class="form-control" placeholder="Username" required name="userCode"/>
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" required name="userPassword"/><div class="info" style="display:inline;color:red;">${error }</div>
              </div>
              <div>
                <input type="submit" class="btn btn-default submit" value="登陆" style="margin-left:150px;"/>
                <a class="reset_pass" href="#">忘记密码?</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">新用户?
                  <a href="${pageContext.request.contextPath }/register" class="to_register"> 创建账号 </a>
                </p>

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
  </body>
</html>
