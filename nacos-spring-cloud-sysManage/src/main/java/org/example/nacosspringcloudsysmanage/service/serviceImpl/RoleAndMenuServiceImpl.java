package org.example.nacosspringcloudsysmanage.service.serviceImpl;

import org.example.nacosspringcloudcommonentity.RoleAndMenu;
import org.example.nacosspringcloudsysmanage.dao.RoleAndMenuDao;
import org.example.nacosspringcloudsysmanage.service.RoleAndMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleAndMenuServiceImpl implements RoleAndMenuService {
    @Autowired
    private RoleAndMenuDao roleAndMenuDao;

    @Override
    public int add(RoleAndMenu rm) {
        return roleAndMenuDao.add(rm);
    }

    @Override
    public List<RoleAndMenu> findByRid(int roleId) {
        return roleAndMenuDao.findByRid(roleId);
    }

    @Override
    public void delByRoleId(Integer roleId) {
        roleAndMenuDao.delByRoleId(roleId);
    }

    @Override
    public void delByMenuId(Integer menuId) {
        roleAndMenuDao.delByMenuId(menuId);
    }
}
