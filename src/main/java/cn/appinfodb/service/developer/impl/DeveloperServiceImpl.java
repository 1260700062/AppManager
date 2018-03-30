package cn.appinfodb.service.developer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.AppCategoryMapper;
import cn.appinfodb.dao.AppInfoMapper;
import cn.appinfodb.dao.DataDictionaryMapper;
import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.service.developer.DeveloperService;

@Service("developerService")
public class DeveloperServiceImpl implements DeveloperService {

	private AppInfoMapper appInfoMapper;
	
	private AppCategoryMapper appCategoryMapper;
	
	private DataDictionaryMapper dataDictionaryMapper;

	@Autowired
	public void setDataDictionaryMapper(DataDictionaryMapper dataDictionaryMapper) {
		this.dataDictionaryMapper = dataDictionaryMapper;
	}
	
	@Autowired
	public void setAppInfoMapper(AppInfoMapper appInfoMapper) {
		this.appInfoMapper = appInfoMapper;
	}

	
	@Autowired
	public void setAppCategoryMapper(AppCategoryMapper appCategoryMapper) {
		this.appCategoryMapper = appCategoryMapper;
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


	@Override
	public List<AppCategory> getCategoryByParentId(Long parentId) {
		// TODO Auto-generated method stub
		List<AppCategory> appCategorys = null;
		try {
			appCategorys = appCategoryMapper.getAppCategoryByParentId(parentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appCategorys;
	}


	@Override
	public AppInfo getAppInfoByAPKName(String apkname) {
		AppInfo appInfo = null;
		try {
			appInfo = appInfoMapper.getAppInfoByAPKName(apkname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appInfo;
	}
	
	@Override
	public AppInfo getAppInfoById(Long id) {
		AppInfo appInfo = null;
		try {
			appInfo = appInfoMapper.getAppInfoById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appInfo;
	}


	@Override
	public AppCategory getAppCategoryById(Long id) {
		// TODO Auto-generated method stub
		AppCategory appCategory = null;
		try {
			appCategory = appCategoryMapper.getAppCategoryById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appCategory;
	}


	@Override
	public String getNameByStatusValue(Long status) {
		String statusName = null;
		try {
			statusName = dataDictionaryMapper.getNameByStatus(status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return statusName;
	}


}
