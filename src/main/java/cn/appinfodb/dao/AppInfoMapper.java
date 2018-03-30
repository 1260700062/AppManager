package cn.appinfodb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.pojo.AppInfoExample;

public interface AppInfoMapper {
    long countByExample(AppInfoExample example);

    int deleteByExample(AppInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppInfo record);

    int insertSelective(AppInfo record);

    List<AppInfo> selectByExample(AppInfoExample example);

    AppInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppInfo record, @Param("example") AppInfoExample example);

    int updateByExample(@Param("record") AppInfo record, @Param("example") AppInfoExample example);

    int updateByPrimaryKeySelective(AppInfo record);

    int updateByPrimaryKey(AppInfo record);
    
    //閺屻儴顕梐pp_info閿涘矁骞忛崣鏍ㄥ閺堝牽PP娣団剝浼呴敍灞肩箽鐎涙ê鍩宭ist娑擄拷
    public List<AppInfo> getAppInfo() throws Exception;
    //濞ｈ濮濧PP娣団剝浼�
    public int addApp(AppInfo appInfo) throws Exception;
    //閺嶈宓乸arendid閼惧嘲褰嘋ategoryName
    public List<AppCategory> getAppCategoryByParentId(Long parentId) throws Exception;
    //闁俺绻僡pkname閼惧嘲褰嘇PP娣団剝浼�
    public AppInfo getAppInfoByAPKName(String apkname) throws Exception;
    
    public List<AppInfo> selectApp(@Param("devId")Long devId,@Param("name")String softwareName,@Param("level3")Long categorylevel3,@Param("flatformId")Long flatformid);
    
  //通过APP id 获取APP信息
    public AppInfo getAppInfoById(Long id) throws Exception;
    
    public int modifyApp(AppInfo appInfo) throws Exception;

    public List<AppInfo> selectAllApp(@Param("devId")Long devId);

    public int modifyStatus(@Param("status")Long status,@Param("id")Long id);

    public int modifyVersionId(@Param("versionId")Long versionId,@Param("id")Long id);
    
    public int deleteAppById(@Param("id")Long id);
}