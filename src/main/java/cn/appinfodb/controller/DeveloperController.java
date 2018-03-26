package cn.appinfodb.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.service.AppCategoryService;
import cn.appinfodb.service.AppInfoService;
import cn.appinfodb.service.AppVersionService;
import cn.appinfodb.service.developer.DeveloperService;

@Controller
public class DeveloperController {
	private DeveloperService developerService;
	@Autowired
	private AppCategoryService appCategoryService;
	@Autowired
	private AppInfoService appInfoService;
	@Autowired
	private AppVersionService appVersionService;
	
	@Resource(name="developerService")
//	@Autowired
	public void setDeveloperService(DeveloperService developerService) {
		this.developerService = developerService;
	}
	
	//测试用
	@RequestMapping("/appList")
	public String appList(Model model) {
		List<AppCategory> appLevel1 = developerService.getCategoryByParentId(null);
		model.addAttribute("appLevel1", appLevel1);
		return "developer/appList";
	}
	
	//ajax获取category
	@RequestMapping(value="/getAppList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAppList1(Long parentId) {
		System.out.println("=================="+parentId+"====================");
		List<AppCategory> appList2 = appCategoryService.getAppByParentId(parentId);
		for(AppCategory app:appList2) {
			System.out.println(app.getCategoryname()+app.getId());
		}
		String json = JSONArray.toJSONString(appList2);
		System.out.println(json);
		return json;
	}

	@RequestMapping("/appSearch")
	public String appSearch(Model model,
			@RequestParam(value="appName",required=false)String appName,
			@RequestParam(value="category3",required=false)long level3,
			@RequestParam(value="flatform",required=false)long flatform) {
		List<AppCategory> appLevel1 = appCategoryService.getAppByParentId(null);
		List<AppInfo> appList = appInfoService.getAppByName(appName, level3,flatform);
		for(AppInfo app:appList) {
			model.addAttribute("level1", appCategoryService.getAppByLevel(app.getCategorylevel1()));
			model.addAttribute("level2", appCategoryService.getAppByLevel(app.getCategorylevel2()));
			model.addAttribute("level3", appCategoryService.getAppByLevel(app.getCategorylevel3()));
			if(app.getVersionid() == null) {
				model.addAttribute("version", "暂无版本信息");
			} else {
				String appVersion = appVersionService.getAppVersionByVersionId(app.getVersionid());
				model.addAttribute("version", appVersion);
			}
		}
		model.addAttribute("appList", appList);
		model.addAttribute("appLevel1", appLevel1);
		return "developer/appList";
	}
	
	/**
	 * 
	 * @return
	 * 
	 * 鐠哄疇娴哸ddApp妞ょ敻娼�
	 * 
	 */
	@RequestMapping(value="/addAppPage", method=RequestMethod.GET)
	public String addAppPage() {
		
		return "developer/addApp";
	}
	
	/**
	 * 閼惧嘲褰嘺ddApp妞ょ敻娼伴惃鍕繆閹垽绱濋獮璺烘倻閺佺増宓佹惔鎾村潑閸旂姳淇婇幁锟�
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
			model.addAttribute("imgError","閺傚洣娆㈡径褍鐨稉宥堝厴鐡掑懓绻�500k閿涳拷");
			return "developer/addApp";
		}else if(suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png") 
        		|| suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("pneg")) {
			String path = session.getServletContext().getRealPath("statics"+File.separator+"img");
			System.out.println("====path==========: "+path);
			
			file = new File(path, fileName);
			String logoPicPath = path + File.separator+fileName;
			appInfo.setLogopicpath(logoPicPath);
			
		}else {
			model.addAttribute("imgError", "閺傚洣娆㈤弽鐓庣础娑撳秵顒滅涵顕嗙磼閿涗簥~");
			
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
	/**
	 * addApp妞ょ敻娼板ǎ璇插APP娣団剝浼呴弮璁圭礉娴ｈ法鏁jax閼惧嘲褰囨稉锟界痪褋锟戒椒绨╃痪褋锟戒椒绗佺痪褍鍨庣猾浼欑礉閻€劋绨柅澶嬪閸掑棛琚痪褍鍩�
	 * @param id
	 * @return
	 */
	@RequestMapping("/categoryLevel")
	@ResponseBody
	public Object getCategoryLevel(String id) {
		Long parentId = null;
		if(id != null) {
			parentId = Long.parseLong(id);
		}else {
			
		}
		
		List<AppCategory> AppCategorys = developerService.getCategoryByParentId(parentId);
		return AppCategorys;
	}
	
	@RequestMapping("/virafyApkName")
	@ResponseBody
	public String virafyApkName(@RequestParam("apkname")String apkname) {
		System.out.println("virafyApkName======================");
		System.out.println(apkname);
		AppInfo appInfo = developerService.getAppInfoByAPKName(apkname);
		if(appInfo == null) {
			return "true";
		}
		return "false";
	}
}
