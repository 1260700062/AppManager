package cn.appinfodb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.accessibility.AccessibleRelation;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
		int i= 1;
        System.out.println("jinr======"+ i);
		return "login";
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login2(HttpSession session,String userCode,String userPassword,Model model) {
		return "login";
		
	}
}
