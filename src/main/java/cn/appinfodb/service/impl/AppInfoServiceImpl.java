package cn.appinfodb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.appinfodb.dao.AppInfoMapper;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.service.AppInfoService;
@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {

	@Autowired
	private AppInfoMapper appInfoMapper;

	@Override
	public List<AppInfo> getApp(String name,Long level3,Long flatformid) {
		List<AppInfo> list = appInfoMapper.selectApp(name, level3, flatformid);
		return list;
	}

	
	@Override
	public List<AppInfo> getAllApp() {
		List<AppInfo> list = appInfoMapper.selectAllApp();
		return list;
	}

	@Transactional
	@Override
	public int modifyAppById(AppInfo appInfo) {
		// TODO Auto-generated method stub
		int flag = -1;
		try {
			flag = appInfoMapper.modifyApp(appInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
