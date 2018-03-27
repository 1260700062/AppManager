package cn.appinfodb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.eclipse.jdt.internal.compiler.env.IGenericField;
import org.junit.runner.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.log.Log;

import cn.appinfodb.pojo.DevUser;
import cn.appinfodb.service.DevUserService;

@Controller
public class RegisterController {
	private Logger log = LoggerFactory.getLogger(RegisterController.class);
	@Autowired
	private DevUserService dus;
	
	public void setDus(DevUserService dus) {
		this.dus = dus;
	}

	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register(HttpSession session,Model model) {
		String identify = (String)session.getAttribute("identify");
		if(identify.equals("developper")) {
			identify = "开发者";
			model.addAttribute("idtf", identify);
			return "register";
		}else {
			model.addAttribute("error", "抱歉，您无法注册管理员！");
			return "login";
		}		
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register2(String userCode,
							String userName,
							String userPassword,
							String email,
							String info,
							HttpSession session,
							Model model) {
		DevUser du = new DevUser();
		du.setdevcode(userCode);
		du.setDevname(userName);
		du.setDevpassword(userPassword);
		du.setDevemail(email);
		du.setDevinfo(info);
		int result = dus.addDevUser(du);
		log.info("result==="+result);
		return "frame";
	}
	@ResponseBody
	@RequestMapping(value="/registeryz",method=RequestMethod.GET)
	public String register3(HttpServletRequest request) {
		System.out.println("进入");
		String userCode = request.getParameter("userCode");
		if( dus.selectByuserCode(userCode)!=null) {
			System.out.println("false");
			return "{\"msg\":\"false\"}";//json{"msg":"false"}
		}else if(userCode==""){
			return "{\"msg\":\"empty\"}";
		}else {
			System.out.println("true");
			return "{\"msg\":\"true\"}";
		}
		
	}
	
}
