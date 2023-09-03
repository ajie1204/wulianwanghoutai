package org.example.nacosspringcloudsysmanage.service;

import org.example.nacosspringcloudcommonentity.RoleAndMenu;

import java.util.List;

public interface RoleAndMenuService {
    int add(RoleAndMenu rm);
    List<RoleAndMenu> findByRid(int roleId);
    void delByRoleId(Integer roleId);
    void delByMenuId(Integer menuId);
}
