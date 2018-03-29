package cn.appinfodb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			System.out.println("userPassword==="+du.getDevpassword());
			if(du.getDevpassword() .equals(userPassword) ) {
				i=1;
			}
		}
		System.out.println("i===="+i);
		return i;
	}

	@Override
	public DevUser selectByuserCode(String userCode) {
		DevUser du = new DevUser();
		du = dum.selectByuserCode(userCode);
		return du;
	}

	@Override
	public int addDevUser(DevUser du) {
		int result = dum.addDevUser(du);
		return result;
	}

	@Override
	public DevUser getDevUserBydevCode(String userCode) {
		DevUser du = dum.getDevUserBydevCode(userCode);
		return du;
	}

}
