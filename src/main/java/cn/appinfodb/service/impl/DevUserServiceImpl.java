package cn.appinfodb.service.impl;

import static org.hamcrest.CoreMatchers.instanceOf;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.json.DupDetector;

import cn.appinfodb.dao.DevUserMapper;
import cn.appinfodb.pojo.DevUser;
import cn.appinfodb.service.DevUserService;
@Service("DevUserService")
public class DevUserServiceImpl implements DevUserService {
	@Autowired
	private DevUserMapper dum;

	public void setDum(DevUserMapper dum) {
		this.dum = dum;
	}

	@Override
	public int DevUserLogin(String userCode, String userPassword) {
		int i = 0;
		DevUser du = dum.getDevUserBydevCode(userCode);
		if(du != null) {
			if(du.getDevpassword() == userPassword) {
				i=1;
			}
		}
		return i;
	}




}
