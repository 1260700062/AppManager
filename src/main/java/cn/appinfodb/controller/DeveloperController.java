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
	
	//app查询
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
	public String addVersionPage(@PathVariable Long id,Model model) {
		model.addAttribute("appId", id);
		return "developer/addVersion";
	}
	
	@RequestMapping(value="/addVersion", method=RequestMethod.POST)
	public String addVersion(AppVersion appVersion,HttpSession session,Model model,HttpServletRequest request,
			@RequestParam(value="apk",required = false) MultipartFile apk,
			@RequestParam(value="appId",required = false) Long appId) {
		DevUser devUser = (DevUser) session.getAttribute("DevUser");
		File file = null;
		int flag = -1;
		if(apk.isEmpty()) {
			return "developer/addVersion";
		}
		String fileName = apk.getOriginalFilename();
		String suffix = FilenameUtils.getExtension(fileName);
		System.out.println(fileName);
		if(apk.getSize() >= 500000000) {
			model.addAttribute("imgError","apk文件不得大于500Mb");
			return "developer/addVersion";
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
		}else {
			model.addAttribute("imgError", "请上传正确文件");
			
			return "developer/addVersion";
		}
		appVersion.setAppid(appId);
		appVersion.setCreationdate(new Date());
		appVersion.setApkfilename(fileName);
		appVersion.setDownloadlink(request.getContextPath()+"/statics/img/"+fileName);
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
	
	/**
	 * 
	 * @return
	 * 
	 * 閻犲搫鐤囧ù鍝竏dApp濡炪倗鏁诲锟�
	 * 
	 */
	@RequestMapping(value="/addAppPage", method=RequestMethod.GET)
	public String addAppPage() {
		
		return "developer/addApp";
	}
	
	/**
	 * 闁兼儳鍢茶ぐ鍢篸dApp濡炪倗鏁诲浼存儍閸曨亙绻嗛柟顓у灲缁辨繈鐛捄鐑樺�婚柡浣哄瀹撲焦鎯旈幘鏉戞綉闁告梻濮虫穱濠囧箒閿燂拷
	 * @param appInfo
	 * @param session
	 * @param model
	 * @param image
	 * @return
	 */
	
	@RequestMapping(value="/addApp", method=RequestMethod.POST)
	public String addApp(AppInfo appInfo, HttpSession session, Model model, 
			@RequestParam(value="picture",required = false) MultipartFile image) {
		File file = null;
		int flag = -1;
		DevUser devUser = (DevUser) session.getAttribute("DevUser");
		if(image.isEmpty()) {
			return "developer/addApp";
		}
		String fileName = image.getOriginalFilename();
		String suffix = FilenameUtils.getExtension(fileName);
		if(image.getSize() >= 500000) {
			model.addAttribute("imgError","图片不得大于500k");
			return "developer/addApp";
		}else if(suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png") 
        		|| suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("pneg")) {
			String path = session.getServletContext().getRealPath("statics"+File.separator+"img");
			System.out.println("====path==========: "+path);
			
			file = new File(path, fileName);
			String logolocpath = path + File.separator+fileName;
			appInfo.setLogolocpath(logolocpath);
			String logopicpath = session.getServletContext().getContextPath()+"/statics/img/"+fileName;
			appInfo.setLogopicpath(logopicpath);
			
			
		}else {
			model.addAttribute("imgError", "请上传正确文件");
			
			return "developer/addApp";
		}
		
		appInfo.setCreationdate(new Date());
		appInfo.setDevid(devUser.getId());
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
	/**
	 * addApp濡炪倗鏁诲鏉壳庣拠鎻掝潱APP濞ｅ洠鍓濇导鍛村籍鐠佸湱绀夊ù锝堟硶閺侇棫jax闁兼儳鍢茶ぐ鍥ㄧ▔閿熺晫鐥閿熸垝妞掔花鈺冪棯瑜嬮敓鎴掓缁椾胶鐥閸ㄥ海鐚炬导娆戠闁烩偓鍔嬬花顒勬焻婢跺顏ラ柛鎺戞鐞氼偆鐥閸╋拷
	 * @param id
	 * @return
	 */
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
	
	/**
	 * 跳转modifyAPP页面，显示所选APP的相关信息
	 * @param id
	 * @param session
	 * @param model
	 * @return
	 */
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
	
	@RequestMapping(value = "/modifyApp", method= RequestMethod.POST)
	public String modifyApp(AppInfo appInfo, HttpSession session, Model model, 
			@RequestParam(value="picture",required = false) MultipartFile picture) {
		System.out.println("==========modifyApp ===========");
		System.out.println("appInfo:    ===="+appInfo.getCategorylevel1());
		File file = null;
		int flag = -1;
		
		if(picture == null || picture.isEmpty()) {
			appInfo.setLogopicpath(null);
			appInfo.setLogolocpath(null);
		}else {
			String fileName = picture.getOriginalFilename();
			String suffix = FilenameUtils.getExtension(fileName);
			if(picture.getSize() >= 500000) {
				model.addAttribute("imgError","文件不能超过500k");
				return "forward:/modifyAppPage?id="+appInfo.getId();
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
				return "forward:/modifyAppPage?id="+appInfo.getId();
			}
		}
		
		appInfo.setUpdatedate(new Date());
		flag = appInfoService.modifyAppById(appInfo);
		
		if(flag > 0) {
			return "redirect:/appList";
		}else {
			System.out.println("error=================查询出错");
			return "forward:/modifyAppPageid="+appInfo.getId();
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
	
	@RequestMapping("/canGoToModifyAppVersionPage")
	public String CanGoToModifyAppVersionPage(Long id) {
		
		System.out.println("==================canGoToModifyAppVersionPage=============="+id);
		AppInfo appInfo = developerService.getAppInfoById(id);
		Long status = appInfo.getStatus();
		Long versionid = appInfo.getVersionid();
		if(status == 1 || status == 3) {
			if(versionid != null) {
				return "redirect:/modifyAppVersionPage?id="+versionid;
			}
		};
		return "forward:/appList";
	}
	
	
	
	/**
	 * 需要传进来一个appVersion的id，然后，根据id查出APPversion的所有信息，保存在APPversion实体中
	 * @param id
	 * @return
	 */
	@RequestMapping("/modifyAppVersionPage")
	public String modifyAppVersionPage(String id, Model model) {
		Long sid = null;
		if(id != null) {
			sid = Long.parseLong(id);
		}
		AppVersion appVersion = appVersionService.getAppVersionById(sid);
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
		return "developer/modifyAppVersion";
	}
	

	@RequestMapping(value="/changeStatus/{id}",method=RequestMethod.GET)
	public String changeStatus(@PathVariable Long id) {
		AppInfo appInfo = developerService.getAppInfoById(id);
		
		if(appInfo.getStatus() == 2) {
			int i = appInfoService.modifyStatus(4l,appInfo.getId());
		}else {
			int i = appInfoService.modifyStatus(5l,appInfo.getId());
		}
		return "redirect:/appList";
	}

	@RequestMapping(value="/deleteApp",method=RequestMethod.GET)
	public String deleteApp(long id,HttpSession session,Model model ) {
		System.out.println("删除AppInfo的id："+id);
		int result = appInfoService.deleteAppById(id);
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
	
	/*@RequestMapping(value="/showAppPage/{id}",method=RequestMethod.GET)
	public String showAppPage(@PathVariable Long id,Model model) {
		AppInfo appInfo = developerService.getAppInfoById(id);
		AppCategory level1 = developerService.getAppCategoryById(appInfo.getCategorylevel1());
		AppCategory level2 = developerService.getAppCategoryById(appInfo.getCategorylevel2());
		AppCategory level3 = developerService.getAppCategoryById(appInfo.getCategorylevel3());
		String statusName = developerService.getNameByStatusValue(appInfo.getStatus());
		List<DataDictionary> allFolatform = dataDictionaryService.getAllDataDictionaryFlatform();
		model.addAttribute("level1",level1);
		model.addAttribute("level2",level2);
		model.addAttribute("level3",level3);
		model.addAttribute("statusName",statusName);
		model.addAttribute("allFolatform",allFolatform);
		model.addAttribute("appInfo", appInfo);
		return "developer/showAPPInfo";
	}*/
	@RequestMapping("/downloadApk")
	@ResponseBody
	public String downloadApk(Long id) {
		AppVersion appVersion = appVersionService.getAppVersionById(id);
		String localPath = appVersion.getApklocpath();
		String[] p = localPath.split("/");
		String sPath = "";
		for (int i = 0; i < p.length; i++) {
			sPath += p[i] + "/";
		}
		BufferedInputStream bis = null;
		FileOutputStream out = null;
		File file = new File(sPath);
		String dPath = "E:\\Resource" + File.separator + appVersion.getApkfilename();
		File dFile = new File(dPath);

		try {
			bis = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
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
			while ((len = bis.read(b)) != -1) {
				out.write(b, 0, len);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return "-2";
		} finally {
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
				return "forward:/modifyAppVersionPage?id="+appVersion.getId();
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
			}else {
				model.addAttribute("modifyApkError", "请上传正确文件");
				System.out.println("请上传正确文件");
				System.out.println(appVersion.getId());
				return "forward:/modifyAppVersionPage?id="+appVersion.getId();
			}
		}
		
		
		return "redirect:/appList";
	}
}
