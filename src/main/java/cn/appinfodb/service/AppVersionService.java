package cn.appinfodb.service;

import cn.appinfodb.pojo.AppVersion;

public interface AppVersionService {
	public String getAppVersionByVersionId(long id);
	public int addAppVersion(AppVersion appVersion);
	public AppVersion getAppVersionByVersionNo(String versionNo,Long appId);
}
