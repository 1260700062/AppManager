package cn.appinfodb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeveloperController {
	@RequestMapping("/appSearch")
	public String appSearch() {
		
		
		
		return "bUser/appList";
	}
	
	/**
	 * 
	 * @return
	 * 
	 * 实现app开发者添加新的APP
	 * 
	 */
	@RequestMapping("/addApp")
	public String addApp() {
		
		return "developer/addApp";
	}
}
