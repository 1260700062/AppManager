var userCode = null;
var userName = null;
var userPassword = null;
var confirmUserPassword = null;
var email = null;
var info = null;

$(function(){
	var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
	userCode = $("#userCode");
	userName = $("#userName");
	userPassword = $("#userPassword");
	confirmUserPassword = $("#confirmUserPassword");
	email = $("#email");
	info = $("info");
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	
	/*userName.next().html("*");
	userPassword.next().html("*");
	confirmUserPassword.next().html("*");
	email.next().html("*");
	info.next().html("*");*/
	

	/*$.ajax({
		type:"GET",//请求类型
		url:path+"/jsp/user.do",//请求的url
		data:{method:"getrolelist"},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			if(data != null){
				userRole.html("");
				var options = "<option value=\"0\">请选择</option>";
				for(var i = 0; i < data.length; i++){
					//alert(data[i].id);
					//alert(data[i].roleName);
					options += "<option value=\""+data[i].id+"\">"+data[i].roleName+"</option>";
				}
				userRole.html(options);
			}
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			validateTip(userRole.next(),{"color":"red"},imgNo+" 获取用户角色列表error",false);
		}
	});*/
	
	
	
	/*
	 * 验证
	 * 失焦\获焦
	 * jquery的方法传递
	 */
	userCode.bind("blur",function(){
		//ajax后台验证--userCode是否已存在
		$.ajax({
			type:"GET",//请求类型
			url:"registeryz",//请求的url
			data:{userCode:userCode.val()},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				console.log(data);
				if(data.msg=="false"){//账号已存在，错误提示					
					userCode.next().html("该用户账号已存在").css("color","red");
				}else if(data.msg=="true"){
					userCode.next().html("该账号可以使用").css("color","green");
					//validateTip(userCode.next(),{"color":"green"}," 该账号可以使用",true);
				}else{
					userCode.next().html("用户编码不能为空").css("color","red");
				}
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				//validateTip(userCode.next(),{"color":"red"},imgNo+" 您访问的页面不存在",false);
			}
		});
	}).bind("focus",function(){
		userCode.next().html("用户编码用于账号登陆").css("color","black");
	});
	
	userName.bind("focus",function(){
		userName.next().html("大于1小于10个字符").css("color","black");
		//validateTip(userName.next(),{"color":"#666666"},"* 用户名长度必须是大于1小于10的字符",false);
	}).bind("blur",function(){
		if(userName.val() == null){
			userName.next().html("用户名不能为空").css("color","red");
			//validateTip(userName.next(),{},imgYes,true);
		}else if(userName.val().length <2||userName.val().length>10){
			userName.next().html("长度必须大于1小于10").css("color","red");
		}else{
			userName.next().html("用户名正确").css("color","green");
		}
		
	});
	
	userPassword.bind("focus",function(){
		userPassword.next().html("长度必须大于6小于20").css("color","black");
		//validateTip(userPassword.next(),{"color":"#666666"},"* 密码长度必须是大于6小于20",false);
	}).bind("blur",function(){
		if(userPassword.val() != null && userPassword.val().length > 6
				&& userPassword.val().length < 20 ){
			userPassword.next().html("请记住您的登陆密码").css("color","green");
			//validateTip(userPassword.next(),{"color":"green"},imgYes,true);
		}else{
			userPassword.next().html("请按规范重新输入").css("color","red");
			//validateTip(userPassword.next(),{"color":"red"},imgNo + " 密码输入不符合规范，请重新输入",false);
		}
	});
	
	confirmUserPassword.bind("focus",function(){
		confirmUserPassword.next().html("请重复您的密码").css("color","black");
		//validateTip(confirmUserPassword.next(),{"color":"#666666"},"* 请输入与上面一只的密码",false);
	}).bind("blur",function(){
		if(confirmUserPassword.val() != null && confirmUserPassword.val().length > 6
				&& confirmUserPassword.val().length < 20 && userPassword.val() == confirmUserPassword.val()){
			confirmUserPassword.next().html("密码正确").css("color","green");
			//validateTip(confirmUserPassword.next(),{"color":"green"},imgYes,true);
		}else{
			confirmUserPassword.next().html("两次密码不一致").css("color","red");
			//validateTip(confirmUserPassword.next(),{"color":"red"},imgNo + " 两次密码输入不一致，请重新输入",false);
		}
	});
	
	email.bind("blur",function(){
		
		if(email.val()!=null && reg.test(email.val())){
			email.next().html("邮箱通过").css("color","green");
		}else{
			email.next().html("邮箱不符合规范").css("color","red");
		}
	});
	
});