package cn.appinfodb.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.service.developer.DeveloperService;

@Controller
public class DeveloperController {
	private DeveloperService developerService;
	
	
	@Resource(name="developerService")
//	@Autowired
	public void setDeveloperService(DeveloperService developerService) {
		this.developerService = developerService;
	}

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
	@RequestMapping(value="/addAppPage", method=RequestMethod.GET)
	public String addAppPage() {
		
		return "developer/addApp";
	}
	
	@RequestMapping(value="/addApp", method=RequestMethod.POST)
	public String addApp(AppInfo appInfo) {
		System.out.println("==========addApp ===========");
		appInfo.setCreationdate(new Date());
		int flag = developerService.addApp(appInfo);
		if(flag > 0) {
			return "redirect:/appSearch";
		}else {
			return "forward:/addAppPage";
		}
	}
}
