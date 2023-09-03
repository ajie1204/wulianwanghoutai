package org.example.nacosspringcloudauth.service.serviceImpl;

import org.example.nacosspringcloudauth.dao.LoginLogDao;
import org.example.nacosspringcloudcommonentity.LoginLog;
import org.example.nacosspringcloudauth.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogDao loginLogDao;

    @Override
    public int add(LoginLog log) {
        return loginLogDao.add(log);
    }

    @Override
    public List<LoginLog> findByAccount(String account) {
        return loginLogDao.findByAccount(account);
    }

    @Override
    public List<LoginLog> findAll() {
        return loginLogDao.findAll();
    }

}
