package cn.appinfodb.service;

import java.util.List;

import cn.appinfodb.pojo.AppCategory;

public interface AppCategoryService {
	public List<AppCategory> getAppByParentId(int parentId);
}
