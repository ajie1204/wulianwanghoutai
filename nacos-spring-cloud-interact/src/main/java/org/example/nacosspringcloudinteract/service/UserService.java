package org.example.nacosspringcloudinteract.service;

import org.example.nacosspringcloudcommonentity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Service
@FeignClient(name = "userManage")
public interface UserService {

    @GetMapping("/userManage/findUserByAccount/{account}")
    User getUserMap(@PathVariable String account);

    @PostMapping("/userManage/updatePassword")
    Map<String,Object> updatePassword(@RequestParam String account,
                                      @RequestParam String oldPassword,
                                      @RequestParam String newPassword,
                                      @RequestParam String cNewPassword);

    @PostMapping("/userManage/deleteAccount")
    Map<String,Object> deleteAccount(@RequestBody User user);

}
