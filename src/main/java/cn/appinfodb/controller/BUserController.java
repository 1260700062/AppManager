package cn.appinfodb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BUserController {
	@RequestMapping("/appSearch")
	public String appSearch() {
		
		
		
		return "bUser/appList";
	}
}
