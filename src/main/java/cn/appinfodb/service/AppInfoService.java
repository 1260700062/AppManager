package cn.appinfodb.service;

import java.util.List;

import cn.appinfodb.pojo.AppInfo;


public interface AppInfoService {
    public List<AppInfo> getApp(String name,Long level3,Long flatformid);
    public List<AppInfo> getAllApp();
    public int modifyAppById(AppInfo appInfo);
}
