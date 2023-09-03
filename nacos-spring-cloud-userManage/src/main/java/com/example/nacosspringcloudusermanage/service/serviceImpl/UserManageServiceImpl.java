package com.example.nacosspringcloudusermanage.service.serviceImpl;

import com.example.nacosspringcloudusermanage.dao.UserManageDao;
import com.example.nacosspringcloudusermanage.service.UserManageService;
import org.example.nacosspringcloudcommonentity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserManageServiceImpl implements UserManageService {
    @Autowired
    private UserManageDao userManageDao;

    @Override
    public int add(User user) {
        return userManageDao.add(user);
    }

    @Override
    public void del(User user) {
        userManageDao.del(user);
    }

    @Override
    public int update(User user) {
        return userManageDao.update(user);
    }

    @Override
    public int updateUserName(User user) {
        return userManageDao.updateUserName(user);
    }

    @Override
    public List<User> findAll() {
        return userManageDao.findAll();
    }

    @Override
    public List<User> findByName(String name) {
        return userManageDao.findByName(name);
    }

    @Override
    public User findByAccount(String account) {
        return userManageDao.findByAccount(account);
    }

    @Override
    public List<User> findBySearchBar(String account, String userName, Date startTime, Date endTime) {
        return userManageDao.findBySearchBar(account, userName, startTime, endTime);
    }

    @Override
    public User find(String account) {
        return userManageDao.find(account);
    }

    @Override
    public int updatePassword(User user) {
        return userManageDao.updatePassword(user);
    }


}
