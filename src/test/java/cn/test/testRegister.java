package cn.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.appinfodb.pojo.DevUser;
import cn.appinfodb.service.DevUserService;

public class testRegister {

	@Test
	public void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		DevUserService dus = ac.getBean(DevUserService.class);
		DevUser du = new DevUser();
		du.setdevcode("test");
		du.setDevname("测试");
		du.setDevpassword("123456");
		du.setDevemail("test666");
		du.setDevinfo("test");
		int result = dus.addDevUser(du);
		System.out.println("result=="+result);
	}

}
