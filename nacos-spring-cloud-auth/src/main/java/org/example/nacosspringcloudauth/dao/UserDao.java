package org.example.nacosspringcloudauth.dao;

import org.example.nacosspringcloudcommonentity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    int add(User user);
    int updateLoginTime(User user);
    int updatePassword(User user);
    User find(String account);
    List<User> findAll();
}
