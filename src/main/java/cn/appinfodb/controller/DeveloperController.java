package cn.appinfodb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.appinfodb.pojo.AppInfo;

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
	 * 跳转addApp页面
	 * 
	 */
	@RequestMapping("/addAppPage")
	public String addAppPage() {
		
		return "developer/addApp";
	}
	
	@RequestMapping("/addApp")
	public String addApp(AppInfo appInfo) {
		System.out.println("appName ==========="+appInfo.getSoftwarename());
		return "";
	}
}
