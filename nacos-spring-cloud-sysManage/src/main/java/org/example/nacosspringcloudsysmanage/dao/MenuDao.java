package org.example.nacosspringcloudsysmanage.dao;

import org.example.nacosspringcloudcommonentity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {
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
