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
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form>
              <h1>身份	选择</h1>
              <div>
                <a href="${pageContext.request.contextPath }/login?identify=developper">开发者</a>
              </div>
              <div>
                <a href="${pageContext.request.contextPath }/login?identify=manager">管理员</a>
              </div>
              <div>
                
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
    <script type="text/javascript">
    $(document).ready(function(){
    	alert("aaaaa");
    })
    </script>
  </body>
</html>
