package cn.appinfodb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class Hellocontroller {
	@RequestMapping("/hello")
	public String hello() {
		return "bUser/bUserLoginIn";
	}

	@RequestMapping("/list")
	public String list() {
		return "bUser/appList";
	}
}
