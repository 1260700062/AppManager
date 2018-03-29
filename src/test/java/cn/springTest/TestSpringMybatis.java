package cn.springTest;


import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.pojo.AppVersion;
import cn.appinfodb.service.AppCategoryService;
import cn.appinfodb.service.AppInfoService;
import cn.appinfodb.service.AppVersionService;


public class TestSpringMybatis {
	private Logger log = LoggerFactory.getLogger(TestSpringMybatis.class);
	@Test
	public void test1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		/*UserService us = (UserService)ac.getBean(UserService.class);
		User user = us.login("admin", "1234567");
		*/
		/*log.info(user.getUserName());*/
	}

	@Test
	public void test2() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		AppCategoryService acs = ac.getBean(AppCategoryService.class);
		List<AppCategory> list = acs.getAppByParentId(null);
		System.out.println(list.size());
		for(AppCategory l:list) {
			System.out.println(l.getCategoryname());
		}
	}

	/*@Test
	public void test3() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		AppInfoService acs = ac.getBean(AppInfoService.class);
		List<AppInfo> list = acs.getAppByName(null, null, 1l);
		System.out.println(list.size());
		for(AppInfo l:list) {
			System.out.println(l.getSoftwarename());
		}
	}*/

	/*@Test
	public void test4() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		AppVersionService acs = ac.getBean(AppVersionService.class);
		AppVersion appVersion = acs.getAppVersionByVersionNo("v1.1.1",55l);
		System.out.println(appVersion.getApkfilename());
	}*/
}
