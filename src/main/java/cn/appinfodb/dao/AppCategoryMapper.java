package cn.appinfodb.dao;

import java.util.List;

import cn.appinfodb.pojo.AppCategory;

public interface AppCategoryMapper {
    List<AppCategory> selectAppByParentId(int parentId);
}