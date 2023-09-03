package org.example.nacosspringcloudsysmanage.service;

import org.example.nacosspringcloudcommonentity.SysUser;

public interface SysUserService {
    int add(SysUser sysUser);
    SysUser find(String account);
}
