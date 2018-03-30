package cn.appinfodb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.AppVersion;
import cn.appinfodb.pojo.AppVersionExample;

public interface AppVersionMapper {
    long countByExample(AppVersionExample example);

    int deleteByExample(AppVersionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    List<AppVersion> selectByExample(AppVersionExample example);

    AppVersion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppVersion record, @Param("example") AppVersionExample example);

    int updateByExample(@Param("record") AppVersion record, @Param("example") AppVersionExample example);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);
    
    

    String selectVersionNoByVersionId(long id);
    
    int addVersion(AppVersion appVersion);
    
    AppVersion selectVersion(@Param("versionNo")String versionno,@Param("appId")Long appid);
    /**
     * 通过参数数据库唯一id，获取APPversion的一个完整的实体信息
     * @param id
     * @return
     */
	AppVersion getAppVersionById(Long id);

	/**
	 * APPversion有多个版本，通过APPId获取该APP的所有历史版本
	 * @param appId
	 * @return
	 */
	List<AppVersion> getAppVersionByAppId(Long appId);
}