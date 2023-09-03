package org.example.nacosspringcloudsysmanage.controller;

import org.example.nacosspringcloudcommonentity.User;
import org.example.nacosspringcloudsysmanage.service.feignService.DeviceManageService;
import org.example.nacosspringcloudsysmanage.service.feignService.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Map;

@RestController
@RequestMapping("/userManageMenu")
public class UserManageMenuController {

    @Autowired
    private UserManageService userManageService;

    @PostMapping("/addUser")
    public Map<String,Object> addUser(@RequestBody User user){
        return userManageService.addUser(user);
    }

    //获得所有的用户
    @GetMapping("/getAllUser/{pageNum}/{pageSize}")
    public Map<String,Object> getAllUser(@PathVariable int pageNum,@PathVariable int pageSize){
        Map<String, Object> map = userManageService.findAll(pageNum, pageSize);
        return map;
    }


    @PostMapping("/findUserByBar")
    public Map<String,Object> getUserByBar(@RequestBody User user){
        //string转date
        //new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse()
        return userManageService.getUserByBar(user);
    }

    @PostMapping("/update")
    public Map<String,Object> update(@RequestBody User user){
        return userManageService.update(user);
    }

    @PostMapping("/delUser")
    public Map<String,Object> delUser(@RequestBody User user){
        return userManageService.delUser(user);
    }
}
