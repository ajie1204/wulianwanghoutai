package org.example.nacosspringcloudsysmanage.service.serviceImpl;

import org.example.nacosspringcloudcommonentity.SysUser;
import org.example.nacosspringcloudsysmanage.dao.SysUserDao;
import org.example.nacosspringcloudsysmanage.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public int add(SysUser sysUser) {
        return sysUserDao.add(sysUser);
    }

    @Override
    public SysUser find(String account) {
        return sysUserDao.find(account);
    }
}
