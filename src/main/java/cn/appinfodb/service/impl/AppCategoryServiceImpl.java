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
	private AppCategoryMapper acm;
	
	public void setAcm(AppCategoryMapper acm) {
		this.acm = acm;
	}

	@Override
	public List<AppCategory> getAppByParentId(int parentId) {
		List<AppCategory> appList = acm.selectAppByParentId(parentId);
		return appList;
	}

}
