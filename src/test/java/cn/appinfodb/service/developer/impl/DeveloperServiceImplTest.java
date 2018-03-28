package cn.appinfodb.service.developer.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.pojo.DataDictionary;
import cn.appinfodb.service.AppInfoService;
import cn.appinfodb.service.DataDictionaryService;
import cn.appinfodb.service.developer.DeveloperService;

public class DeveloperServiceImplTest {
	private Logger log = LoggerFactory.getLogger(DeveloperServiceImplTest.class);
	@Test
	public void testGetAppInfo() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		DeveloperService ds = (DeveloperService)ac.getBean("developerService");
		List<AppInfo> appInfo = ds.getAppInfo();
		for(AppInfo a : appInfo) {
			log.info("name===={}",a.getApkname());
		}
	}
	
	
	@Test
	public void testAddApp() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		DeveloperService ds = (DeveloperService)ac.getBean("developerService");
		AppInfo appInfo = new AppInfo();
		appInfo.setSoftwarename("aaaaa");
		appInfo.setCreationdate(new Date());
		int flag = ds.addApp(appInfo);
		System.out.println(flag);
	}
	
	@Test
	public void testAddgetCategoryByParentId() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		DeveloperService ds = (DeveloperService)ac.getBean("developerService");
		Long parendId =  (long) 1;
		List<AppCategory> categorys = ds.getCategoryByParentId(parendId);
		for(AppCategory a:categorys ) {
			log.info("id {}, ====name: {}",a.getId(),a.getCategoryname());
		}
	}
	
	@Test
	public void testgetAppInfoByAPKName() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		DeveloperService ds = (DeveloperService)ac.getBean("developerService");
		String apkname = "";
		 ds.getAppInfoByAPKName(apkname );
	}
	
	@Test
	public void testmodifyById() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		AppInfoService ds = (AppInfoService)ac.getBean("appInfoService");
		
		AppInfo appInfo = new AppInfo();
		appInfo.setId(65L);
		appInfo.setSoftwarename("bbb");
		appInfo.setAppinfo("b");
		appInfo.setCategorylevel1(2L);
		appInfo.setCategorylevel2(2L);
		appInfo.setFlatformid(2L);
		appInfo.setInterfacelanguage("��������");
		appInfo.setLogolocpath("bb");
		BigDecimal b = new BigDecimal(11);
		appInfo.setSoftwaresize(b);
		appInfo.setCategorylevel3(2L);
		appInfo.setSupportrom("2.2�����߰汾");
		appInfo.setUpdatedate(new Date());
		int modifyAppById = ds.modifyAppById(appInfo );
		log.info("==============={}==============",modifyAppById);
	}
	
	@Test
	public void testgetAllDataDictionaryFlatform() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		DataDictionaryService ds = (DataDictionaryService)ac.getBean("dataDictionaryService");
		List<DataDictionary> all = ds.getAllDataDictionaryFlatform();
		for(DataDictionary a : all) {
			
			log.info("valueid:  {}=====valuename {}===",a.getValueid(), a.getValuename());
		}
	}
	@Test
	public void testFlatform() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		DataDictionaryService ds = (DataDictionaryService)ac.getBean("dataDictionaryService");
		Long l = new Long(3);
			String nameByFlatformid = ds.getNameByFlatformid(3L);
			log.info("valueid:  {}=====valuename",nameByFlatformid);
	}
}
