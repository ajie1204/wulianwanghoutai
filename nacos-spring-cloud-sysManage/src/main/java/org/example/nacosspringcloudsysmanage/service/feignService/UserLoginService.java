package org.example.nacosspringcloudsysmanage.service.feignService;

import org.example.nacosspringcloudcommonentity.LoginLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Service
@FeignClient(name = "auth")
public interface UserLoginService {
    //得到所有用户的登录数据
    @GetMapping("/log/getAll/{pageNum}/{pageSize}")
    Map<String,Object> getAllLog(@PathVariable int pageNum, @PathVariable int pageSize);
    //得到当前用户的登录数据
    @GetMapping("/log/{account}/{pageNum}/{pageSize}")
    Map<String,Object> getLog(@PathVariable String account, @PathVariable int pageNum, @PathVariable int pageSize);
    //搜索框查看登录日志
    @PostMapping("/log/findByBar")
     Map<String,Object> getLoginLogByBar(@RequestBody LoginLog loginLog);
    //删除登录日志
    @PostMapping("/log/delLog")
    Map<String,Object> delLoginLog(@RequestBody LoginLog loginLog);
}
