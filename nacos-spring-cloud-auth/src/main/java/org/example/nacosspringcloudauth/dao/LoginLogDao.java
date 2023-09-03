package org.example.nacosspringcloudauth.dao;

import org.example.nacosspringcloudcommonentity.LoginLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginLogDao {
    int add(LoginLog log);
    List<LoginLog> findByAccount(String account);
    List<LoginLog> findAll();
}
