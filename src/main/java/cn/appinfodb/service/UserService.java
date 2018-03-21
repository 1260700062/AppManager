package cn.appinfodb.service;

import java.util.List;

import cn.appinfodb.pojo.User;


public interface UserService {
	/**
	 * 用户登录
	 * @param userCode
	 * @param userPassword
	 * @return
	 */
	public User login(String userCode, String userPassword);
}
