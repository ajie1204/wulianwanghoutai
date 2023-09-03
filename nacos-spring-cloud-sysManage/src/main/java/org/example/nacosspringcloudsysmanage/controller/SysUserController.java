package org.example.nacosspringcloudsysmanage.controller;

import org.apache.commons.lang3.StringUtils;
import org.example.nacosspringcloudcommoncore.constant.UserConstants;
import org.example.nacosspringcloudcommonentity.SysUser;
import org.example.nacosspringcloudsysmanage.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/addUser")
    public Map<String, Object> addSysUser(@RequestBody SysUser sysUser) {
        Map<String, Object> map = new HashMap<>();
        SysUser user = sysUserService.find(sysUser.getAccount());
        if (user != null) {
            map.put("result", "该用户已存在，请勿重复添加");
        }else {
            sysUserService.add(sysUser);
            map.put("result", "添加成功");
        }
        return map;

    }

    //登录
    @PostMapping("/sysUserLogin")
    public Map<String, Object> sysUserLogin(@RequestBody SysUser sysUser) {
        Map<String, Object> map = new HashMap<>();
        String account = sysUser.getAccount();
        String password = sysUser.getPassword();
        if (StringUtils.isAnyBlank(account, password)) {
            map.put("result", "用户/密码必须填写");
            return map;
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            map.put("result", "用户密码不在指定范围(5-20)");
            return map;

        }
        if (account.length() != 11) {
            map.put("result", "用户名错误");
            return map;
        }
        SysUser user = sysUserService.find(account);
        if (user == null) {
            map.put("result", "用户未注册");
            return map;
        }
        if (user.getPassword() != null && !user.getPassword().equals(password)) {
            map.put("result", "用户密码错误");
            return map;
        }
        if (user.getRoleId()!=1){
            map.put("result", "该用户权限无法进入系统");
            return map;
        }
        map.put("result", "登录成功");
        return map;
    }
}
