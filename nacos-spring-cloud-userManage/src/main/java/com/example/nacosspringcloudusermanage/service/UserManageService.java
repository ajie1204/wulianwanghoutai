package com.example.nacosspringcloudusermanage.service;

import org.example.nacosspringcloudcommonentity.User;

import java.util.Date;
import java.util.List;

public interface UserManageService {
    //新增用户
    int add(User user);
    //删除用户
    void del(User user);
    //修改信息（用户名称、手机号、密码）
    int update(User user);
    //用户修改姓名
    int updateUserName(User user);
    //查询所有用户
    List<User> findAll();
    //根据用户名查询用户
    List<User> findByName(String name);
    //根据手机号查询用户
    User findByAccount(String account);
    //搜索框查询
    List<User> findBySearchBar(String account, String userName, Date startTime, Date endTime);

    User find(String account);

    int updatePassword(User user);


}
