package cn.appinfodb.service.developer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.AppInfoMapper;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.service.developer.DeveloperService;

@Service("developerService")
public class DeveloperServiceImpl implements DeveloperService {

	private AppInfoMapper appInfoMapper;
	@Autowired
	public void setAppInfoMapper(AppInfoMapper appInfoMapper) {
		this.appInfoMapper = appInfoMapper;
	}

	
	//获取所用APP的信息
	@Override
	public List<AppInfo> getAppInfo() {
		List<AppInfo> appInfo = null;
		try {
			appInfo = appInfoMapper.getAppInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appInfo;
	}


	@Override
	public int addApp(AppInfo appInfo) {
		int flag = 0;
		try {
			flag = appInfoMapper.addApp(appInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
