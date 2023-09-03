package org.example.nacosspringcloudauth.service.serviceImpl;


import org.example.nacosspringcloudauth.dao.UserDao;
import org.example.nacosspringcloudauth.service.UserService;
import org.example.nacosspringcloudcommonentity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int add(User user) {

        return userDao.add(user);
    }

    @Override
    public int updateLoginTime(User user) {
        return userDao.updateLoginTime(user);
    }

    @Override
    public int updatePassword(User user) {
        return userDao.updatePassword(user);
    }

    @Override
    public User find(String account) {
        return userDao.find(account);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
