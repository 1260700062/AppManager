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
     * 閫氳繃鍙傛暟鏁版嵁搴撳敮涓�id锛岃幏鍙朅PPversion鐨勪竴涓畬鏁寸殑瀹炰綋淇℃伅
     * @param id
     * @return
     */
	AppVersion getAppVersionById(Long id);

	/**
	 * APPversion鏈夊涓増鏈紝閫氳繃APPId鑾峰彇璇PP鐨勬墍鏈夊巻鍙茬増鏈�
	 * @param appId
	 * @return
	 */
	List<AppVersion> getAppVersionByAppId(Long appId);
	
	AppVersion getNewVersion(Long appId);
	
}