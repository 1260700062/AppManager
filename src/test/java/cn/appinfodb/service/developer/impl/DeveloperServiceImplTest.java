package cn.appinfodb.service.developer.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
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

}
