package org.example.nacosspringcloudsysmanage.dao;

import org.example.nacosspringcloudcommonentity.RoleAndMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleAndMenuDao {

    int add(RoleAndMenu rm);
    List<RoleAndMenu> findAll();
    List<RoleAndMenu> findByRid(int roleId);

    void delByRoleId(Integer roleId);
    void delByMenuId(Integer menuId);
}
