package cn.appinfodb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.DataDictionaryMapper;
import cn.appinfodb.pojo.DataDictionary;
import cn.appinfodb.service.DataDictionaryService;

@Service("dataDictionaryService")
public class DataDictionaryServiceImpl implements DataDictionaryService {

	private DataDictionaryMapper dataDictionaryMapper;
	
	@Autowired
	public void setDataDictionaryMapper(DataDictionaryMapper dataDictionaryMapper) {
		this.dataDictionaryMapper = dataDictionaryMapper;
	}


	@Override
	public List<DataDictionary> getAllDataDictionaryFlatform() {
		// TODO Auto-generated method stub
		List<DataDictionary> flatform = dataDictionaryMapper.getFlatformName();
		return flatform;
	}


	@Override
	public String getNameByFlatformid(Long valueId) {
		// TODO Auto-generated method stub
		String flatformName = dataDictionaryMapper.getNameByFlatformid(valueId);
		return flatformName;
	}

}
