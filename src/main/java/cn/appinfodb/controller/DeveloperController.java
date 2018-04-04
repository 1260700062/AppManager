package cn.appinfodb.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.pojo.AppVersion;
import cn.appinfodb.pojo.DataDictionary;
import cn.appinfodb.pojo.DevUser;
import cn.appinfodb.service.AppCategoryService;
import cn.appinfodb.service.AppInfoService;
import cn.appinfodb.service.AppVersionService;
import cn.appinfodb.service.DataDictionaryService;
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
	
	private DataDictionaryService dataDictionaryService;
	
	@Resource(name="dataDictionaryService")
	public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
		this.dataDictionaryService = dataDictionaryService;
	}

	@Resource(name="developerService")
//	@Autowired
	public void setDeveloperService(DeveloperService developerService) {
		this.developerService = developerService;
	}
	/**
	 * app列表
	 * @param path
	 * @param session
	 * @return
	 */
	@RequestMapping("/appList")
	public String appList(HttpSession session,Model model) {
		DevUser devUser = (DevUser) session.getAttribute("DevUser");
		List<AppCategory> appLevel1 = developerService.getCategoryByParentId(null);
		List<AppInfo> appList = appInfoService.getAllApp(devUser.getId());
		Map<Long,String> map = new HashMap<Long,String>();
		for(AppInfo app:appList) {
			model.addAttribute("level1", appCategoryService.getAppByLevel(app.getCategorylevel1()));
			model.addAttribute("level2", appCategoryService.getAppByLevel(app.getCategorylevel2()));
			model.addAttribute("level3", appCategoryService.getAppByLevel(app.getCategorylevel3()));
			if(app.getVersionid() == null) {
				map.put(null, "暂无版本信息");
			} else {
				String appVersion = appVersionService.getAppVersionByVersionId(app.getVersionid());
				map.put(app.getVersionid(), appVersion);
			}
		}
		model.addAttribute("map", map);
		model.addAttribute("appLevel1", appLevel1);
		model.addAttribute("appList", appList);
		
		return "developer/appList";
	}
	/**
	 * ajax获取category
	 * @param path
	 * @param session
	 * @return
	 */
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
	/**
	 * app查询
	 * @param path
	 * @param session
	 * @return
	 */
	@RequestMapping("/appSearch")
	public String appSearch(HttpSession session,Model model,
			@RequestParam(value="appName",required=false)String appName,
			@RequestParam(value="category3",required=false)Long level3,
			@RequestParam(value="flatform",required=false)Long flatform) {
		DevUser devUser = (DevUser) session.getAttribute("DevUser");
		List<AppCategory> appLevel1 = appCategoryService.getAppByParentId(null);
		List<AppInfo> appList = appInfoService.getApp(devUser.getId(),appName, level3,flatform);
		Map<Long,String> map = new HashMap<Long,String>();
		for(AppInfo app:appList) {
			model.addAttribute("level1", appCategoryService.getAppByLevel(app.getCategorylevel1()));
			model.addAttribute("level2", appCategoryService.getAppByLevel(app.getCategorylevel2()));
			model.addAttribute("level3", appCategoryService.getAppByLevel(app.getCategorylevel3()));
			if(app.getVersionid() == null) {
				model.addAttribute("version", "暂无版本信息");
			} else {
				String appVersion = appVersionService.getAppVersionByVersionId(app.getVersionid());
				map.put(app.getVersionid(), appVersion);
			}
		}
		model.addAttribute("map", map);
		model.addAttribute("appList", appList);
		model.addAttribute("appLevel1", appLevel1);
		return "developer/appList";
	}
	
	@RequestMapping("/addVersionPage/{id}")
	public String addVersionPage(@PathVariable Long id,Model model,String imgError) {
		model.addAttribute("appId", id);
		model.addAttribute("imgError",imgError);
		return "developer/addVersion";
	}
	/**
	 * 增加版本
	 * @param path
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/addVersion", method=RequestMethod.POST)
	public String addVersion(AppVersion appVersion,HttpSession session,Model model,HttpServletRequest request,
			@RequestParam(value="apk",required = false) MultipartFile apk,
			@RequestParam(value="appId",required = false) Long appId) {
		DevUser devUser = (DevUser) session.getAttribute("DevUser");
		File file = null;
		int flag = -1;
		if(apk.isEmpty()) {
			model.addAttribute("imgError", "请添加文件");
			return "redirect:/addVersionPage/"+appId;
		}
		String fileName = apk.getOriginalFilename();
		String suffix = FilenameUtils.getExtension(fileName);
		System.out.println(fileName);
		if(suffix.equalsIgnoreCase("apk") || suffix.equalsIgnoreCase("rar") || suffix.equalsIgnoreCase("zip")) {
			String path = session.getServletContext().getRealPath("statics"+File.separator+"apk");
			file=new File(path);
			if(!file.exists()){
			file.mkdir();
			}
			System.out.println("====path==========: "+path);
			
			file = new File(path,fileName);
			System.out.println(file.getPath());
			String apklocpath = path + File.separator+fileName;
			appVersion.setApklocpath(apklocpath);
			
			if(apk.getSize() >= 500000000) {
				model.addAttribute("imgError","apk文件不得大于500Mb");
				return "developer/addVersion";
			}
		}else {
			model.addAttribute("imgError", "请上传正确文件");
			
			return "redirect:/addVersionPage/"+appId;
		}
		appVersion.setAppid(appId);
		appVersion.setCreationdate(new Date());
		appVersion.setApkfilename(fileName);
		appVersion.setDownloadlink(request.getContextPath()+"/statics/apk/"+fileName);
		appVersion.setCreatedby(devUser.getId());
		flag = appVersionService.addAppVersion(appVersion);
		System.out.println("=======1========"+flag+"================");
		
		try {
			file.createNewFile();
		}  catch (IOException e) {
			flag = -1;
			e.printStackTrace();
		}
		Long versionId = appVersionService.getNewVersion(appId).getId();
		appInfoService.modifyVersionId(versionId, appId);
		if(flag > 0) {
			return "redirect:/appList";
		}else {
			return "forward:/addVersionPage";
		}
	}
	

	
	@RequestMapping("/virafyVersionNo")
	@ResponseBody
	public Object virafyVersionNo(Long id,String versionNo) {
		AppVersion appVersion = appVersionService.getAppVersion(versionNo, id);
		return appVersion;
	}
	
	
	@RequestMapping(value="/addAppPage")
	public String addAppPage() {
		
		return "developer/addApp";
	}
	
	
	
	@RequestMapping(value="/addApp", method=RequestMethod.POST)
	public String addApp(AppInfo appInfo, HttpSession session, Model model, 
			@RequestParam(value="picture",required = false) MultipartFile image) {
		File file = null;
		int flag = -1;
		DevUser devUser = (DevUser) session.getAttribute("DevUser");
		if(image.isEmpty()) {
			model.addAttribute("imgError", "上传文件不能为空");
			return "developer/addApp";
		}
		String fileName = image.getOriginalFilename();
		String suffix = FilenameUtils.getExtension(fileName);
		if(suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png") 
        		|| suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("pneg")) {
			String path = session.getServletContext().getRealPath("statics"+File.separator+"img");
			System.out.println("====path==========: "+path);
			
			file = new File(path, fileName);
			String logolocpath = path + File.separator+fileName;
			appInfo.setLogolocpath(logolocpath);
			String logopicpath = session.getServletContext().getContextPath()+"/statics/img/"+fileName;
			appInfo.setLogopicpath(logopicpath);
			
			if(image.getSize() >= 500000) {
				model.addAttribute("imgError","图片不得大于500k");
				return "developer/addApp";
			}
			
		}else {
			model.addAttribute("imgError", "请上传正确文件");
			
			return "developer/addApp";
		}
		
		appInfo.setCreationdate(new Date());
		appInfo.setDevid(devUser.getId());
		System.out.println(appInfo.getDevid()+"===========");
		appInfo.setCreatedby(devUser.getId());
		flag = developerService.addApp(appInfo);
		
		try {
			image.transferTo(file);
		}  catch (IOException e) {
			flag = -1;
			e.printStackTrace();
		}
		
		if(flag > 0) {
			return "redirect:/appList";
		}else {
			return "forward:/addAppPage";
		}
	}
	
	@RequestMapping("/categoryLevel")
	@ResponseBody
	public Object getCategoryLevel(String id) {
		System.out.println(id);
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
	
	
	@RequestMapping("/modifyAppPage/{id}")
	public String modifyAppPage(@PathVariable Long id, HttpSession session, Model model) {
		System.out.println("=========modifyAppPage==========");
		System.out.println("id : "+id);
		AppInfo appInfo = developerService.getAppInfoById(id);
		System.out.println(appInfo.getApkname());
		AppCategory level1 = developerService.getAppCategoryById(appInfo.getCategorylevel1());
		AppCategory level2 = developerService.getAppCategoryById(appInfo.getCategorylevel2());
		AppCategory level3 = developerService.getAppCategoryById(appInfo.getCategorylevel3());
		String statusName = developerService.getNameByStatusValue(appInfo.getStatus());
		List<DataDictionary> allFolatform = dataDictionaryService.getAllDataDictionaryFlatform();
		model.addAttribute("level1",level1);
		model.addAttribute("level2",level2);
		model.addAttribute("level3",level3);
		System.out.println(level1.getCategoryname());
		System.out.println(level2.getCategoryname());
		System.out.println(level3.getCategoryname());
		model.addAttribute("statusName",statusName);
		model.addAttribute("allFolatform",allFolatform);
		model.addAttribute("appInfo", appInfo);
		return "developer/modifyApp";
	}
	
	/**
	 * 通过Ajax选择图片并在jsp显示
	 * @param path
	 * @param session
	 * @return
	 */
	@RequestMapping("/modifyPic")
	@ResponseBody
	public Object modifyPic(String path, HttpSession session) {
		System.out.println("path : ==== "+ path);
		if(path.isEmpty()) {
			
		}
		
		String[] split = path.split("\\\\");
    	String filename = split[split.length-1];
    	System.out.println(filename);
    	
	        
		return "";
	}
	
	@RequestMapping(value = "/modifyApp/{id}", method= RequestMethod.POST)
	public String modifyApp(AppInfo appInfo, HttpSession session, Model model, 
			@RequestParam(value="picture",required = false) MultipartFile picture) {
		System.out.println("==========modifyApp ===========");
		System.out.println("appInfo:    ===="+appInfo.getCategorylevel1());
		File file = null;
		int flag = -1;
		
		if(picture == null || picture.isEmpty()) {
			appInfo.setLogopicpath(null);
			appInfo.setLogolocpath(null);
			model.addAttribute("imgError", "上传文件不能为空");
		}else {
			String fileName = picture.getOriginalFilename();
			String suffix = FilenameUtils.getExtension(fileName);
			if(picture.getSize() >= 500000) {
				model.addAttribute("imgError","文件不能超过500k");
				return "forward:/modifyAppPage/"+appInfo.getId();
			}else if(suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png") 
	        		|| suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("pneg")) {
				String path = session.getServletContext().getRealPath("statics"+File.separator+"img");
				System.out.println("====path==========: "+path);
				
				file = new File(path, fileName);
				String logolocpath = path + File.separator+fileName;
				appInfo.setLogolocpath(logolocpath);
				String logopicpath = session.getServletContext().getContextPath()+"/statics/img/"+fileName;
				appInfo.setLogopicpath(logopicpath);
				appInfo.setUpdatedate(new Date());
				flag = appInfoService.modifyAppById(appInfo);
				try {
					picture.transferTo(file);
				}  catch (IOException e) {
					flag = -1;
					e.printStackTrace();
				}
				
			}else {
				model.addAttribute("imgError", "文件格式不正确");
				System.out.println("文件格式不正确");
				return "forward:/modifyAppPage/"+appInfo.getId();
			}
		}
		if(flag > 0) {
			return "redirect:/appList";
		}else {
			System.out.println("error=================查询出错");
			return "forward:/modifyAppPage/"+appInfo.getId();
		}
	}
	
	@RequestMapping("/showAppInfo/{id}")
	public String showAppInfo(@PathVariable Long id, Model model) {
		AppInfo appInfo = developerService.getAppInfoById(id);
		AppCategory level1 = developerService.getAppCategoryById(appInfo.getCategorylevel1());
		AppCategory level2 = developerService.getAppCategoryById(appInfo.getCategorylevel2());
		AppCategory level3 = developerService.getAppCategoryById(appInfo.getCategorylevel3());
		String statusName = developerService.getNameByStatusValue(appInfo.getStatus());
		List<DataDictionary> allFolatform = dataDictionaryService.getAllDataDictionaryFlatform();
		String flatformName = dataDictionaryService.getNameByFlatformid(appInfo.getFlatformid());
		model.addAttribute("level1",level1);
		model.addAttribute("level2",level2);
		model.addAttribute("level3",level3);
		model.addAttribute("statusName",statusName);
		model.addAttribute("allFolatform",allFolatform);
		model.addAttribute("appInfo", appInfo);
		model.addAttribute("flatformName", flatformName);
		return "developer/showAPPInfo";
	}
	@RequestMapping(value="/appPublish",method=RequestMethod.GET)
	public String appPublish() {
		
		return "developer/appPublish";
	}
	
	
	
	
	
	/**
	 * 需要传进来一个appVersion的id，然后，根据id查出APPversion的所有信息，保存在APPversion实体中
	 * @param id
	 * @return
	 */
	@RequestMapping("/modifyAppVersionPage/{id}")
	public String modifyAppVersionPage(@PathVariable Long id,Model model) {
		AppVersion appVersion = appVersionService.getAppVersionById(id);
		List<AppVersion> appVersions = appVersionService.getAppVersionByAppId(appVersion.getAppid());
//		AppVersion appVersion = appVersionService.getAppVersion(versionNo, appId);
		String publishStatusName = dataDictionaryService.getPublishStatusNameById(appVersion.getPublishstatus());
		List<DataDictionary> publish = dataDictionaryService.getAllPublishName();
		AppInfo appInfo = developerService.getAppInfoById(appVersion.getAppid());
		Map<Long, String> map = new HashMap<Long, String>();
		for(DataDictionary d : publish) {
			map.put(d.getValueid(), d.getValuename());
		}
		model.addAttribute("modifyAppVersion",appVersion);
		model.addAttribute("publishStatusName",publishStatusName);
		model.addAttribute("appVersions",appVersions);
		model.addAttribute("publishMap",map);
		model.addAttribute("appInfo",appInfo);
		
		System.out.println(appVersion.getModifydate()+"---------------------");
		
		return "developer/modifyAppVersion";
	}
	

	@RequestMapping(value="/changeStatus/{id}",method=RequestMethod.GET)
	public String changeStatus(@PathVariable Long id) {
		AppInfo appInfo = developerService.getAppInfoById(id);
		AppVersion appVersion = appVersionService.getNewVersion(id);
		
		if(appInfo.getStatus() == 2) {
			int i = appInfoService.modifyStatus(4l,appInfo.getId());
			appVersion.setPublishstatus(2l);
			int a = appVersionService.modifyAppVersion(appVersion);
			System.out.println(a+"======================");
		}else {
			int i = appInfoService.modifyStatus(5l,appInfo.getId());
		}
		return "redirect:/appList";
	}

	@RequestMapping(value="/deleteApp",method=RequestMethod.GET)
	public String deleteApp(long id,HttpSession session,Model model ) {
		System.out.println("删除AppInfo的id："+id);
		int result = appInfoService.deleteAppById(id);
		appVersionService.deleteVersionByAppId(id);
		System.out.println("删除结果："+result);
		DevUser devUser = (DevUser) session.getAttribute("DevUser");
		List<AppCategory> appLevel1 = developerService.getCategoryByParentId(null);
		List<AppInfo> appList = appInfoService.getAllApp(devUser.getId());
		Map<Long,String> map = new HashMap<Long,String>();
		for(AppInfo app:appList) {
			model.addAttribute("level1", appCategoryService.getAppByLevel(app.getCategorylevel1()));
			model.addAttribute("level2", appCategoryService.getAppByLevel(app.getCategorylevel2()));
			model.addAttribute("level3", appCategoryService.getAppByLevel(app.getCategorylevel3()));
			if(app.getVersionid() == null) {
				map.put(null, "暂无版本信息");
			} else {
				String appVersion = appVersionService.getAppVersionByVersionId(app.getVersionid());
				map.put(app.getVersionid(), appVersion);
			}
		}
		model.addAttribute("map", map);
		model.addAttribute("appLevel1", appLevel1);
		model.addAttribute("appList", appList);
		
		return "developer/appList";
		
		
	}
	
	@RequestMapping("/downloadApk")
	@ResponseBody
	public String downloadApk(Long id) {
	AppVersion appVersion = appVersionService.getAppVersionById(id);
	String localPath = appVersion.getApklocpath();
	String[] p = localPath.split("/");
	String sPath = "";
	for(int i=0 ; i< p.length; i++) {
		sPath += p[i]+"/"; 
	}
	BufferedInputStream bis = null;
	FileOutputStream out = null;
	File file = new File(sPath);
	String dPath = "E:\\apk"+File.separator+appVersion.getApkfilename();
	File dFile = new File(dPath);
	File dir = new File("E:\\apk");
	if(!dir.exists()) {
		dir.mkdir();
	}
	
	try {
		bis = new BufferedInputStream(new FileInputStream(file));
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
		return "-1";
	}
	
	try {
		out = new FileOutputStream(dFile);
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		return "1";
	}
	try {
		
		int len = 0;
		byte[] b = new byte[1024];
		while((len = bis.read(b)) != -1) {
			out.write(b, 0, len);
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		
		e.printStackTrace();
		return "-2";
	}finally {
		try {
			bis.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "2";
		}
	}
	return "0";
}
	
	/**
	 * 修改APPversion信息，保存，更新数据库
	 * @param appVersion
	 * @param model
	 * @param session
	 * @param apk
	 * @return
	 */
	
	@RequestMapping("/modifyAppVersion")
	public String modifyAppVersion(AppVersion appVersion, Model model,HttpSession session,
			@RequestParam(value="apk", required=false)MultipartFile apk) {
		DevUser devUser = (DevUser) session.getAttribute("DevUser");
		System.out.println("==================modifyAppVersion=============="+appVersion.getId());
		if(apk == null || apk.isEmpty()) {
			appVersion.setApklocpath(null);
			appVersion.setDownloadlink(null);
			appVersion.setApkfilename(null);
		}else {
			File file = null;
			String fileName = apk.getOriginalFilename();
			String suffix = FilenameUtils.getExtension(fileName);
			if(apk.getSize() >= 500000000) {
				model.addAttribute("modifyApkError","apk文件不得大于500Mb");
				return "forward:/modifyAppVersionPage/"+appVersion.getId();
			}else if(suffix.equalsIgnoreCase("apk") || suffix.equalsIgnoreCase("rar") || suffix.equalsIgnoreCase("zip")) {
				String path = session.getServletContext().getRealPath("statics"+File.separator+"apk");
				file=new File(path);
				if(!file.exists()){//如果不存在该文件夹
				file.mkdir();//新建
				}
				System.out.println("====path==========: "+path);
				
				file = new File(path,fileName);
				System.out.println(file.getPath());
				String apklocpath = path + File.separator+fileName;
				appVersion.setApklocpath(apklocpath);
				appVersion.setApkfilename(fileName);
				String contextPath = session.getServletContext().getContextPath();
				String downloadlink = contextPath+"/statics/apk/" + fileName;
				appVersion.setDownloadlink(downloadlink);
			}else {
				model.addAttribute("modifyApkError", "请上传正确文件");
				System.out.println("请上传正确文件");
				System.out.println(appVersion.getId());
				return "forward:/modifyAppVersionPage/"+appVersion.getId();
			}
		}
		appVersion.setModifydate(new Date());
		appVersion.setModifyby(devUser.getId());
		int flag = appVersionService.modifyAppVersion(appVersion);
		System.out.println(appVersion.getModifydate()+"---------------------");
		
		return "redirect:/appList";
	}
	
	@RequestMapping("/canGoToModifyAppVersionPage")
	public String CanGoToModifyAppVersionPage(String id) {
		
		System.out.println("==================canGoToModifyAppVersionPage=============="+id);
		Long sid = Long.parseLong(id);
		AppInfo appInfo = developerService.getAppInfoById(sid);
		Long status = appInfo.getStatus();
		Long versionid = appInfo.getVersionid();
		if(status == 1 || status == 3) {
			if(versionid != null) {
				return "redirect:/modifyAppVersionPage/"+versionid;
			}
		};
		return "forward:/appList";
	}
}
