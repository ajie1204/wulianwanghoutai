package org.example.nacosspringcloudsysmanage.controller;

import org.example.nacosspringcloudcommonentity.DownData;
import org.example.nacosspringcloudcommonentity.LoginLog;
import org.example.nacosspringcloudsysmanage.service.feignService.DeviceManageService;
import org.example.nacosspringcloudsysmanage.service.feignService.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/logManage")
public class LogManageController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private DeviceManageService deviceManageService;

    //获得全部用户登录日志
    @GetMapping("/getAllUserLog/{pageNum}/{pageSize}")
    public Map<String,Object> getAllUserLoginLog(@PathVariable int pageNum,@PathVariable int pageSize){
        return userLoginService.getAllLog(pageNum,pageSize);
    }

    //获得某一个用户的登录日志
    @GetMapping("/getUserLog/{account}/{pageNum}/{pageSize}")
    public Map<String,Object> getUserLoginLog(@PathVariable String account,
                                              @PathVariable int pageNum,
                                              @PathVariable int pageSize){
        return userLoginService.getLog(account,pageNum,pageSize);
    }

    //获得所有用户操作日志
    @GetMapping("/getUserCommand/{pageNum}/{pageSize}")
    public Map<String,Object> getUserCommand(@PathVariable int pageNum,@PathVariable int pageSize){
        return deviceManageService.getDownData(pageNum,pageSize);
    }

    //搜索框查看登录日志
    @PostMapping("/findLogByBar")
    public Map<String,Object> getLoginLogByBar(@RequestBody LoginLog loginLog){
        return userLoginService.getLoginLogByBar(loginLog);
    }

    //删除登录日志
    @PostMapping("/delLog")
    public Map<String,Object> delLoginLog(@RequestBody LoginLog loginLog){
        return userLoginService.delLoginLog(loginLog);
    }

    //搜索框查看操作日志
    @PostMapping("/findByBar")
    public  Map<String,Object> getByBar(@RequestBody DownData downData){
        return deviceManageService.getByBar(downData);
    }

    //删除操作日志
    @PostMapping("/delData")
    public Map<String,Object> delById(@RequestBody DownData downData){
        return deviceManageService.delById(downData);
    }


}
