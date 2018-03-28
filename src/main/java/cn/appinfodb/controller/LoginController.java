package cn.appinfodb.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.jdbc.log.Log;

import cn.appinfodb.pojo.BackendUser;
import cn.appinfodb.pojo.DevUser;
import cn.appinfodb.service.BackendUserService;
import cn.appinfodb.service.DevUserService;

@Controller
public class LoginController {
	private Logger log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private DevUserService dus;
	public void setDus(DevUserService dus) {
		this.dus = dus;
	}
	@Autowired
	private BackendUserService bus;
	public void setBus(BackendUserService bus) {
		this.bus = bus;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login2(HttpSession session,String userCode,String userPassword,Model model) {
		String identify = session.getAttribute("identify").toString();
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		
		int confirm=0;
		if(identify.equals("manager")) {
			BackendUserService bus =ac.getBean(BackendUserService.class);
			 confirm = bus.BackendUserLogin(userCode, userPassword);
			if (confirm==1) {
				BackendUser bu = new BackendUser();
				bu.setUsername(userCode);
				bu.setUserpassword(userPassword);
				session.setAttribute("BackendUser", bu);
				session.setMaxInactiveInterval(10*60);
				return "manager";
			}else {
				System.out.println("用户名或密码不正确");
				model.addAttribute("error", "用户名或密码不正确");
				return "login";
			} 
		}
		if(identify.equals("developper")){
			DevUserService dus = ac.getBean(DevUserService.class);
			confirm = dus.DevUserLogin(userCode, userPassword);
			System.out.println("confirm===="+confirm);
			if(confirm==1) {
				DevUser du = new DevUser();
				du.setDevname(userCode);
				du.setDevpassword(userPassword);
				session.setAttribute("DevUser", du);
				session.setMaxInactiveInterval(10*60);
				return "developer/dUserLoginIn";
			}else {
				System.out.println("用户名或密码不正确");
				model.addAttribute("error", "用户名或密码不正确");
				return "login";
			}
		}
		return "beforeLogin";				
	}
	
	@RequestMapping(value="/beforeLogin",method=RequestMethod.GET)
	public String login3(HttpSession session) {
		
		//session.removeAttribute("identify");
		return "beforeLogin";
	}
	@RequestMapping(value="/beforeLogin",method=RequestMethod.POST)
	public String login4() {		
		return "beforeLogin";
	}
	
	@RequestMapping(value="/loginIdChange",method=RequestMethod.GET)
	public String login5(HttpSession session) {
		String identify =  (String)session.getAttribute("identify");
		identify = identify.equals("manager")?"developper":"manager";
		session.setAttribute("identify", identify);
		System.out.println("identify===Controller==="+identify);
		return "login";
	}
	
	@RequestMapping(value="/logOut",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("identify");
		session.removeAttribute("DevUser");
		System.out.println("logOut===Controller===");
		return "login";
	}
}
