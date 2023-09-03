package org.example.nacosspringcloudauth.service;

import org.example.nacosspringcloudcommonentity.LoginLog;

import java.util.List;

public interface LoginLogService {
    int add(LoginLog log);
    List<LoginLog> findByAccount(String account);
    List<LoginLog> findAll();
}
