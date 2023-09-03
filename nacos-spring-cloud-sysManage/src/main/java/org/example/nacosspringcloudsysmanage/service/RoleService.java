package org.example.nacosspringcloudsysmanage.service;

import org.example.nacosspringcloudcommonentity.Role;

import java.util.List;

public interface RoleService {
    int add(Role role);
    Role find(String roleName);
    List<Role> findAll();
    void del(Role role);
    int updateRole(Role role);
}
