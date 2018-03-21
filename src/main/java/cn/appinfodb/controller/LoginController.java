package cn.appinfodb.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.appinfodb.pojo.User;
import cn.appinfodb.service.UserService;
import cn.appinfodb.service.UserServiceImpl;
import cn.appinfodb.tools.Constants;

@Controller
public class LoginController {
	
	@Resource
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login2(HttpSession session,String userCode,String userPassword,Model model) {
				User user = userService.login(userCode,userPassword);
				if(null != user){//登录成功
					session.setAttribute(Constants.USER_SESSION, user);
					return "frame";
				}else{
					model.addAttribute("error", "用户名或密码不正确");
					return "login";
				}
	}

}
