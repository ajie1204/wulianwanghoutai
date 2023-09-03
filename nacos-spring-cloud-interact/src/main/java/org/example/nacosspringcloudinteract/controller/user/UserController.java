package org.example.nacosspringcloudinteract.controller.user;


import org.example.nacosspringcloudcommonentity.User;
import org.example.nacosspringcloudinteract.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //修改密码
    @PostMapping("/updatePassword")
    public Map<String,Object> updatePassword(@RequestParam String account,
                                             @RequestParam String oldPassword,
                                             @RequestParam String newPassword,
                                             @RequestParam String cNewPassword){
        return  userService.updatePassword(account, oldPassword, newPassword, cNewPassword);
    }

    //注销账号
    @PostMapping("/deleteAccount")
    public Map<String,Object> deleteAccount(@RequestBody User user){
        return  userService.deleteAccount(user);
    }
}
