package cn.appinfodb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.AppCategoryMapper;
import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.service.AppCategoryService;
@Service
public class AppCategoryServiceImpl implements AppCategoryService {
	@Autowired
	private AppCategoryMapper appCategoryMapper;
	
	@Override
	public List<AppCategory> getAppByParentId(Long parentId) {
		List<AppCategory> appList = null;
		try {
			appList = appCategoryMapper.getAppCategoryByParentId(parentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appList;
	}

	@Override
	public List<String> getAppByLevel(Long level) {
		List<String> list = appCategoryMapper.selectAppByLevel(level);
		return list;
	}


}
