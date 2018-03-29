package cn.appinfodb.service.developer;

import java.util.List;

import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;

public interface DeveloperService {
	//鑾峰彇鎵�鏈堿PP鐨勪俊鎭�
	public List<AppInfo> getAppInfo();
	
	//娣诲姞APP
	public int addApp(AppInfo appInfo);
	//閫氳繃鐖秈d 涓嬬殑鎵�鏈夊瓙鍒嗙被
	public List<AppCategory> getCategoryByParentId(Long parendId);
	//閫氳繃apkname鑾峰彇APP淇℃伅
	public AppInfo getAppInfoByAPKName(String apkname);
	//通过id获取APP信息
	public AppInfo getAppInfoById(Long id);
	//通过id获取APP分类级别的信息
	public AppCategory getAppCategoryById(Long id);
	
	public String getNameByStatusValue(Long status);
}
