package org.example.nacosspringcloudauth.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.nacosspringcloudcommonentity.LoginLog;
import org.example.nacosspringcloudauth.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("log")
public class LoginLogController {

    @Autowired
    private LoginLogService logService;

    @GetMapping("/{account}/{pageNum}/{pageSize}")
    public Map<String,Object> getLog(@PathVariable String account,
                                     @PathVariable int pageNum,
                                     @PathVariable int pageSize){
        Map<String,Object> map =new HashMap<>();
        PageHelper.startPage(pageNum,pageSize);
        List<LoginLog> logList = logService.findByAccount(account);
        PageInfo<LoginLog> pageInfo = new PageInfo<>(logList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("logList",pageInfo.getList());
        return map;

    }

    @GetMapping("/getAll/{pageNum}/{pageSize}")
    public Map<String,Object> getAllLog(@PathVariable int pageNum,@PathVariable int pageSize){
        Map<String,Object> map =new HashMap<>();
        PageHelper.startPage(pageNum,pageSize);
        List<LoginLog> logList = logService.findAll();
        PageInfo<LoginLog> pageInfo = new PageInfo<>(logList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("logList",pageInfo.getList());
        return map;
    }
}
