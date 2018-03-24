package cn.appinfodb.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.appinfodb.pojo.AppCategory;
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
	
	/**
	 * 获取addApp页面的信息，并向数据库添加信息
	 * @param appInfo
	 * @param session
	 * @param model
	 * @param image
	 * @return
	 */
	
	@RequestMapping(value="/addApp", method=RequestMethod.POST)
	public String addApp(AppInfo appInfo, HttpSession session, Model model, 
			@RequestParam(value="picture",required = false) MultipartFile image) {
		System.out.println("==========addApp ===========");
		System.out.println("appInfo"+appInfo.getFlatformid());
		File file = null;
		int flag = -1;
		if(image.isEmpty()) {
			return "developer/addApp";
		}
		String fileName = image.getOriginalFilename();
		String suffix = FilenameUtils.getExtension(fileName);
		if(image.getSize() >= 500000) {
			model.addAttribute("imgError","文件大小不能超过500k！");
			return "developer/addApp";
		}else if(suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png") 
        		|| suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("pneg")) {
			String path = session.getServletContext().getRealPath("statics"+File.separator+"img");
			System.out.println("====path==========: "+path);
			
			file = new File(path, fileName);
			String logoPicPath = path + File.separator+fileName;
			appInfo.setLogopicpath(logoPicPath);
			
		}else {
			model.addAttribute("imgError", "文件格式不正确！！~~");
			
			return "developer/addApp";
		}
		
		appInfo.setCreationdate(new Date());
		flag = developerService.addApp(appInfo);
		
		try {
			image.transferTo(file);
		}  catch (IOException e) {
			flag = -1;
			e.printStackTrace();
		}
		
		if(flag > 0) {
			return "redirect:/appSearch";
		}else {
			return "forward:/addAppPage";
		}
	}
	
	@RequestMapping("/categoryLevel")
	@ResponseBody
	public Object getCategoryLevel(String id) {
		Long parentId = null;
		if(id != null) {
			parentId = Long.parseLong(id);
		}else {
			
		}
		
		System.out.println("categoryLevel=========");
		List<AppCategory> AppCategorys = developerService.getCategoryByParentId(parentId);
		return AppCategorys;
	}
}
