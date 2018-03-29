package cn.appinfodb.service;

import java.util.List;

import cn.appinfodb.pojo.BackendUser;

public interface BackendUserService {
	public int BackendUserLogin(String userCode,String userPassword);
	public BackendUser getBackendUserByBackendCode(String userCode);
}
