package cn.appinfodb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.AppCategory;

public interface AppCategoryMapper {
    List<AppCategory> selectAppByParentId(int parentId);
    
  //根据parendid获取CategoryName
    public List<AppCategory> getAppCategoryByParentId(@Param("parentid")Long parentId) throws Exception;
}