package org.example.nacosspringcloudsysmanage.service.serviceImpl;

import org.example.nacosspringcloudcommonentity.Role;
import org.example.nacosspringcloudsysmanage.dao.RoleDao;
import org.example.nacosspringcloudsysmanage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public int add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Role find(String roleName) {
        return roleDao.find(roleName);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void del(Role role) {
        roleDao.del(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleDao.updateRole(role);
    }
}
