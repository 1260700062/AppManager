package cn.appinfodb.service;

import cn.appinfodb.pojo.DevUser;

public interface DevUserService {
	public int DevUserLogin(String userCode,String userPassword);
	public DevUser selectByuserCode(String userCode);
	public int addDevUser(DevUser du);
}
