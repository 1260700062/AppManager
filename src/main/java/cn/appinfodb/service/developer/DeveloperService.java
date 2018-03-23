package cn.appinfodb.service.developer;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.appinfodb.pojo.AppInfo;

public interface DeveloperService {
	public List<AppInfo> getAppInfo();
}
