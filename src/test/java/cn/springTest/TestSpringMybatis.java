package cn.springTest;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


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
}
