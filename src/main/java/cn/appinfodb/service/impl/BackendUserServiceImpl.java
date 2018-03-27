package cn.appinfodb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.BackendUserMapper;
import cn.appinfodb.pojo.BackendUser;
import cn.appinfodb.pojo.DevUser;
import cn.appinfodb.service.BackendUserService;

@Service("BackendUserService")
public class BackendUserServiceImpl implements BackendUserService {
	@Autowired
	private BackendUserMapper bum;
	
	public void setBum(BackendUserMapper bum) {
		this.bum = bum;
	}

	@Override
	public int BackendUserLogin(String userCode, String userPassword) {
		int i = 0;
		BackendUser bku = bum.getBackendUserByBackendCode(userCode);
		if(bku != null) {
			if(bku.getUserpassword() .equals(userPassword) ) {
				i=1;
			}
		}
		return i;
	}

}
