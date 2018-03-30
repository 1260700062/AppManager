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
	public List<AppInfo> getApp(Long devId,String name,Long level3,Long flatformid) {
		List<AppInfo> list = appInfoMapper.selectApp(devId, name, level3, flatformid);
		return list;
	}

	
	@Override
	public List<AppInfo> getAllApp(Long devId) {
		List<AppInfo> list = appInfoMapper.selectAllApp(devId);
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


	@Override
	public int modifyStatus(Long status,Long id) {
		return appInfoMapper.modifyStatus(status,id);
	}


	@Override
	public int deleteAppById(Long id) {
		return appInfoMapper.deleteAppById(id);
	}


	@Override
	public int modifyVersionId(Long versionId, Long id) {
		return appInfoMapper.modifyVersionId(versionId, id);
	}
	

}
