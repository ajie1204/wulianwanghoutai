package org.example.nacosspringcloudsysmanage.dao;

import org.example.nacosspringcloudcommonentity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    int add(Role role);
    Role find(String roleName);
    List<Role> findAll();
    void del(Role role);
    int updateRole(Role role);



}
