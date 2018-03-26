package cn.appinfodb.service;

import java.util.List;

import cn.appinfodb.pojo.AppInfo;

public interface AppInfoService {
    public List<AppInfo> getAppByName(String name,Long level3,Long flatformid);
}
