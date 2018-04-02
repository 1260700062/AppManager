package cn.appinfodb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.AppVersionMapper;
import cn.appinfodb.pojo.AppVersion;
import cn.appinfodb.service.AppVersionService;
@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService {
	
	@Autowired
	private AppVersionMapper appVersionMapper;
	
	@Override
	public String getAppVersionByVersionId(long id) {
		String version = appVersionMapper.selectVersionNoByVersionId(id);
		return version;
	}

	@Override
	public int addAppVersion(AppVersion appVersion) {
		int i = appVersionMapper.addVersion(appVersion);
		return i;
	}

	@Override
	public AppVersion getAppVersion(String versionNo,Long appId) {
		AppVersion appVersion = appVersionMapper.selectVersion(versionNo,appId);
		return appVersion;
	}

	@Override
	public AppVersion getAppVersionById(Long id) {
		// TODO Auto-generated method stub
		AppVersion appVersion = appVersionMapper.getAppVersionById(id);
		return appVersion;
	}

	@Override
	public List<AppVersion> getAppVersionByAppId(Long appId) {
		// TODO Auto-generated method stub
		List<AppVersion> appVersions = appVersionMapper.getAppVersionByAppId(appId);
		return appVersions;
	}

	@Override
	public AppVersion getNewVersion(Long appId) {
		return appVersionMapper.getNewVersion(appId);
	}

	@Override
	public int modifyAppVersion(AppVersion appVersion) {
		// TODO Auto-generated method stub
		int flag = appVersionMapper.modifyAppVersion(appVersion);
		return flag;
	}

}
