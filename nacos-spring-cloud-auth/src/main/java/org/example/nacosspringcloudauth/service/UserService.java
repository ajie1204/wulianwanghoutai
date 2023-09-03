package org.example.nacosspringcloudauth.service;

import org.example.nacosspringcloudcommonentity.User;

import java.util.List;


public interface UserService {
    int add(User user);
    int updateLoginTime(User user);
    int updatePassword(User user);
    User find(String account);
    List<User> findAll();
}
