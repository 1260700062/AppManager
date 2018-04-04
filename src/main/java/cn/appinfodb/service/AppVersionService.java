package cn.appinfodb.service;

import java.util.List;

import cn.appinfodb.pojo.AppVersion;

public interface AppVersionService {
	public String getAppVersionByVersionId(long id);
	public int addAppVersion(AppVersion appVersion);
	public AppVersion getAppVersion(String versionNo,Long appId);
	public AppVersion getAppVersionById(Long id);
	public List<AppVersion> getAppVersionByAppId(Long appId);
	public AppVersion getNewVersion(Long appId);
	
	
	public int modifyAppVersion(AppVersion appVersion);
	
	int deleteVersionByAppId(Long appid);
}
