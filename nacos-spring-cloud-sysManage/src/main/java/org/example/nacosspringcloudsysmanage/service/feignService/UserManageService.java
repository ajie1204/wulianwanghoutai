package org.example.nacosspringcloudsysmanage.service.feignService;

import org.example.nacosspringcloudcommonentity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Service
@FeignClient(name = "userManage")
public interface UserManageService {

    //分页查找所有用户
    @GetMapping("/userManage/findAll/{pageNum}/{pageSize}")
    Map<String,Object> findAll(@PathVariable int pageNum, @PathVariable int pageSize);

    //新增用户
    @PostMapping("/userManage/addUser")
    Map<String,Object> addUser(@RequestBody User user);

    //根据搜索框查询用户
    @PostMapping("/userManage/findUserByBar")
    Map<String,Object> getUserByBar(@RequestBody User user);

    //修改用户信息
    @PostMapping("/userManage/update")
    Map<String,Object> update(@RequestBody User user);

    //删除用户信息
    @PostMapping("/userManage/delUser")
    Map<String,Object> delUser(@RequestBody User user);



}
