package cn.appinfodb.dao;

import cn.appinfodb.pojo.User;

public interface UserMapper {
    User getLoginUser(String userCode);
}