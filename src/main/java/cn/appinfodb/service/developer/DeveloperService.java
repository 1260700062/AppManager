package cn.appinfodb.service.developer;

import java.util.List;

import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;

public interface DeveloperService {
	//获取所有APP的信息
	public List<AppInfo> getAppInfo();
	
	//添加APP
	public int addApp(AppInfo appInfo);
	
	public List<AppCategory> getCategoryByParentId(Long parendId);
}
