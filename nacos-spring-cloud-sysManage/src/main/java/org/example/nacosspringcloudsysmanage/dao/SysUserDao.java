package org.example.nacosspringcloudsysmanage.dao;

import org.example.nacosspringcloudcommonentity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao {
    int add(SysUser sysUser);
    SysUser find(String account);
}
