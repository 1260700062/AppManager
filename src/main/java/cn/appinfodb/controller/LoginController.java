package cn.appinfodb.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.ORBPackage.InconsistentTypeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import cn.appinfodb.pojo.BackendUser;
import cn.appinfodb.pojo.DevUser;
import cn.appinfodb.service.BackendUserService;
import cn.appinfodb.service.DevUserService;
import cn.appinfodb.tools.Constants;

@Controller
public class LoginController {
	
	/*@Resource
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}*/
	@Resource
	private DevUserService dus;
	private BackendUserService bus;
	

	public void setDus(DevUserService dus) {
		this.dus = dus;
	}

	public void setBus(BackendUserService bus) {
		this.bus = bus;
	}

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login2(HttpSession session,String userCode,String userPassword,Model model) {
		int a=0,b=0;
		DevUser du = new DevUser();
		BackendUser bu = new BackendUser();
		a = dus.DevUserLogin(userCode, userPassword);
		b = bus.BackendUserLogin(userCode, userPassword);
		System.out.println("a=="+a+"B=="+b);
		if(a==0&&b==0) {
			return "login";
		}else if(a==1){
			du.setDevname(userCode);
			session.setAttribute("devUser", du);
			return "frame";
		}else if(b==1) {
			bu.setUsername(userCode);
			session.setAttribute("BackendUser", bu);
			return "frame";
		}else {
			return "login";
		}
		
	}

}
