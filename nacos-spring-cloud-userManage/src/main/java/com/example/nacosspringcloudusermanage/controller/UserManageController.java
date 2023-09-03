package com.example.nacosspringcloudusermanage.controller;

import com.example.nacosspringcloudusermanage.service.UserManageService;
import com.example.nacosspringcloudusermanage.service.feign.DeviceManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.example.nacosspringcloudcommoncore.constant.UserConstants;
import org.example.nacosspringcloudcommonentity.Device;
import org.example.nacosspringcloudcommonentity.User;
import org.example.nacosspringcloudcommonentity.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userManage")
public class UserManageController {

    @Autowired
    private UserManageService userManageService;

    @Autowired
    private DeviceManageService deviceManageService;

    

    /**
     * 新增用户
     * @return
     */
    @PostMapping("/addUser")
    public Map<String,Object> addUser(@RequestBody User user){
        Map<String,Object> map =new HashMap<>();
        String account = user.getAccount();
        String password = user.getPassword();
        User byAccount = userManageService.findByAccount(user.getAccount());
        if (StringUtils.isAnyBlank(account,password)) {
            map.put("result","用户账号/密码必须填写");
            return map;
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            map.put("result","用户密码不在指定范围(5-20)");
            return map;
        }
        if (account.length()!=11) {
           map.put("result","请输入正确的手机号");
            return map;
        }
        if(byAccount!=null){
            map.put("result","该账号已注册");
            return map;
        }
        userManageService.add(user);
        map.put("result","添加成功");
        PageHelper.startPage(user.getPageNum(),user.getPageSize());
        List<User> userList = userManageService.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("userList",pageInfo.getList());
        return map;
    }

    /**
     * 删除用户，并返回新的数据
     * @param user
     * @return
     */
    @PostMapping("/delUser")
    public Map<String,Object> delUser(@RequestBody User user){
        Map<String,Object> map =new HashMap<>();
        userManageService.del(user);
        map.put("result","删除成功");
        PageHelper.startPage(user.getPageNum(),user.getPageSize());
        List<User> userList = userManageService.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("userList",pageInfo.getList());
        return map;
    }

    /**
     * 查询所有数据
     * @return
     */
    @GetMapping("/findAll/{pageNum}/{pageSize}")
    public Map<String,Object> findAll(@PathVariable int pageNum,@PathVariable int pageSize){
        Map<String,Object> map =new HashMap<>();
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = userManageService.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("userList",pageInfo.getList());
        return map;
    }

    /**
     * 修改用户数据
     * @param user
     * @return
     */
    @PostMapping("/update")
    public Map<String,Object> update(@RequestBody User user){
        Map<String,Object> map =new HashMap<>();
        userManageService.update(user);
        map.put("result","修改成功");
        PageHelper.startPage(user.getPageNum(),user.getPageSize());
        List<User> userList = userManageService.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("userList",pageInfo.getList());
        return map;
    }

    /**
     * 用户自己修改用户名
     * @param user
     * @return
     */
    @PostMapping("/updateUserName")
    public Map<String,Object> updateUserName(@RequestBody User user){
        Map<String,Object> map =new HashMap<>();
        userManageService.updateUserName(user);
        map.put("result","修改成功");
        return map;
    }

    /**
     * 根据手机号查找用户信息
     * @return user
     */
    @GetMapping("/findUserByAccount/{account}")
    public User getUserMap(@PathVariable String account) {
        User user = userManageService.findByAccount(account);
        return user;
    }

    @GetMapping("/findUserByName/{userName}/{pageNum}/{pageSize}")
    public Map<String,Object> getUserByName(@PathVariable String userName,
                                            @PathVariable int pageNum,
                                            @PathVariable int pageSize){
        Map<String,Object> map =new HashMap<>();
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = userManageService.findByName(userName);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("userList",pageInfo.getList());
        return map;
    }

    @PostMapping("/findUserByBar")
    public Map<String,Object> getUserByBar(@RequestBody User user){
        Map<String,Object> map =new HashMap<>();
        PageHelper.startPage(user.getPageNum(),user.getPageSize());
        List<User> userList = userManageService.findBySearchBar(user.getAccount(), user.getUserName(), user.getStartTime(), user.getEndTime());
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("userList",pageInfo.getList());
        return map;
    }

    //修改密码
    @PostMapping("/updatePassword")
    public Map<String,Object> updatePassword(@RequestParam String account,
                                             @RequestParam String oldPassword,
                                             @RequestParam String newPassword,
                                             @RequestParam String cNewPassword) {
        Map<String, Object> map = new HashMap<>();

        User user = userManageService.find(account);
        if (!user.getPassword().equals(oldPassword)) {
            map.put("result", "旧密码不正确");
            return map;
        } else {
            // 新密码为空 错误
            if (StringUtils.isAnyBlank(newPassword, cNewPassword)) {
                map.put("result", "密码必须填写");
                return map;
            }
            // 新密码如果不在指定范围内 错误
            if (newPassword.length() < UserConstants.PASSWORD_MIN_LENGTH
                    || newPassword.length() > UserConstants.PASSWORD_MAX_LENGTH) {
                map.put("result", "新密码不在指定范围(5-20)");
                return map;
            }
            //两次输入的密码不一样 错误
            if (!newPassword.equals(cNewPassword)) {
                map.put("result", "两次输入的密码不一样");
                return map;
            }

            user.setPassword(newPassword);
            userManageService.updatePassword(user);
            map.put("success", "用户密码修改成功");
            return map;
        }

    }


    //注销账号
    @PostMapping("/deleteAccount")
    public Map<String,Object> deleteAccount(@RequestBody User user){
        Map<String, Object> map = new HashMap<>();
        String account = user.getAccount();
        String password = user.getPassword();
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            map.put("result","用户密码不在指定范围(5-20)");
            return map;
        }
        User user1 = userManageService.findByAccount(account);
        Integer userId = user1.getUserId();
        if (user1.getPassword()!=null&&!user1.getPassword().equals(password)){
            map.put("result","密码错误");
            return map;
        }
        List<Device> list = deviceManageService.findAll(userId);
        for(Device d:list){
            String deviceId = d.getDeviceId();
            deviceManageService.unActivate(deviceId);
        }
        userManageService.del(user1);
        map.put("result","注销成功");
        return map;

    }

}
