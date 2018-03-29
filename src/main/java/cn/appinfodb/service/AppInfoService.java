package cn.appinfodb.service;

import java.util.List;

import cn.appinfodb.pojo.AppInfo;

public interface AppInfoService {

	public List<AppInfo> getApp(Long devId,String name,Long level3,Long flatformid);
	public List<AppInfo> getAllApp(Long devId);
    
    public int modifyAppById(AppInfo appInfo);
    

}
