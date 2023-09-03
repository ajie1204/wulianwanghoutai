package org.example.nacosspringcloudsysmanage.service;

import org.example.nacosspringcloudcommonentity.Menu;

import java.util.List;

public interface MenuService {
    int add(Menu menu);
    List<Menu> findAll();
    //根据用户id查找菜单
    List<Menu> findByUser(int userId);
    //根据角色查找菜单
    List<Menu> findByRole(int roleId);
    //修改菜单
    int updateMenu(Menu menu);
    //删除菜单
    void delMenu(Menu menu);
}
