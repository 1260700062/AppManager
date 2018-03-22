<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>APP信息管理平台</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/statics/css/style.css" />
    <script type="text/javascript">
	/* if(top.location!=self.location){
	      top.location=self.location;
	 } */
    </script>
</head>
<body class="login_bg" style="background-color: lightblue;">
    <section class="loginBox">
        <header class="loginHeader">
            <h1>APP信息管理系统</h1>
        </header>
        <section class="loginCont">
	        <form class="loginForm" action="${pageContext.request.contextPath }/login"  name="actionForm" id="actionForm"  method="post" >
				<div style="margin:0 0 5px 180px;">
				<a href="#">没有账号，注册！</a>
				</div>
				<div class="inputbox">
                    <label for="user">用户名：</label>
					<input type="text" class="input-text" id="userCode" name="userCode" placeholder="请输入用户名" required/>
				</div>	
				<div class="inputbox">
                    <label for="mima">密码：</label>
                    <input type="password" id="userPassword" name="userPassword" placeholder="请输入密码" required/>
                </div>	
                <div class="info" style="margin-left:100px;">${error }</div>
				<div class="subBtn" style="margin-top:15px;">					
                    <input type="submit" value="登录" style="margin-right:60px;border:0;"/>
                    <input type="reset" value="重置" style="border:0;"/>
                </div>	
			</form>
        </section>
    </section>
</body>
</html>
