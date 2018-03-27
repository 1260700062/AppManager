package cn.appinfodb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.AppInfoMapper;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.service.AppInfoService;
@Service
public class AppInfoServiceImpl implements AppInfoService {

	@Autowired
	private AppInfoMapper appInfoMapper;

	@Override
	public List<AppInfo> getApp(String name,Long level3,Long flatformid) {
		List<AppInfo> list = appInfoMapper.selectAppByName(name,level3,flatformid);
		return list;
	}

	@Override
	public List<AppInfo> getAllApp() {
		List<AppInfo> list = appInfoMapper.selectAllApp();
		return list;
	}

}
