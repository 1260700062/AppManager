package cn.appinfodb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.DataDictionary;
import cn.appinfodb.pojo.DataDictionaryExample;

public interface DataDictionaryMapper {
    long countByExample(DataDictionaryExample example);

    int deleteByExample(DataDictionaryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DataDictionary record);

    int insertSelective(DataDictionary record);

    List<DataDictionary> selectByExample(DataDictionaryExample example);

    DataDictionary selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DataDictionary record, @Param("example") DataDictionaryExample example);

    int updateByExample(@Param("record") DataDictionary record, @Param("example") DataDictionaryExample example);

    int updateByPrimaryKeySelective(DataDictionary record);

    int updateByPrimaryKey(DataDictionary record);
    //根据APPinfo的status获取平台name
    public String getNameByStatus(Long valueId) throws Exception;
    //获取所有的平台name
    public List<DataDictionary> getFlatformName();
    //根据flatformID获取flatformname
    public String getNameByFlatformid(Long valueId);

	String getPublishStatusNameById(Long publishstatus);
	/**
	 * 获取发布状态的所有值，放入到List
	 * @return
	 */
	List<DataDictionary> getAllPublishName();
}