package cn.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.appinfodb.dao.DevUserMapper;
import cn.appinfodb.pojo.BackendUser;
import cn.appinfodb.pojo.DevUser;
import cn.appinfodb.service.BackendUserService;
import cn.appinfodb.service.DevUserService;
import cn.appinfodb.service.impl.DevUserServiceImpl;

public class testLogin {
	
	@Test
	public void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		/*DevUserService dus = ac.getBean(DevUserService.class);
		int i = dus.DevUserLogin("test001", "123456");*/
		BackendUserService bus = ac.getBean(BackendUserService.class);
		int i = bus.BackendUserLogin("admin", "123456");
		System.out.println("i=="+i);
	}

}
