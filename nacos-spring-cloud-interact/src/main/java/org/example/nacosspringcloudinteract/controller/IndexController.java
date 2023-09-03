package org.example.nacosspringcloudinteract.controller;

import org.apache.commons.lang3.StringUtils;
import org.example.nacosspringcloudcommonentity.Device;
import org.example.nacosspringcloudcommonentity.Place;
import org.example.nacosspringcloudcommonentity.Scene;
import org.example.nacosspringcloudcommonentity.User;
import org.example.nacosspringcloudinteract.service.DeviceService;
import org.example.nacosspringcloudinteract.service.PlaceService;
import org.example.nacosspringcloudinteract.service.SceneService;
import org.example.nacosspringcloudinteract.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private SceneService sceneService;

    @Autowired
    private UserService userService;


    //用户登录或注册,跳转到首页，返回设备与场所数据
    @GetMapping("/{account}")
    public Map<String,Object> getIndexPage(@PathVariable String account){
        Map<String,Object> map = new HashMap<>();
        //用户数据
        User userMap = userService.getUserMap(account);
        int userId = userMap.getUserId();
        //设备数据
        List<Device> deviceList = deviceService.getIndexMap(userId);
        //场所数据
        List<Place> placeList = placeService.getAll(userId);
        map.put("userId",userId);
        map.put("deviceList",deviceList);
        map.put("placeList",placeList);
        return map;
    }

    //web端，首页，返回设备、场所、场景数据
    @GetMapping("/web/{account}")
    public Map<String,Object> getWebIndex(@PathVariable String account){
        Map<String,Object> map = new HashMap<>();
        //用户数据
        User userMap = userService.getUserMap(account);
        int userId = userMap.getUserId();
        //设备数据
        List<Device> deviceList = deviceService.getIndexMap(userId);
        //场所数据
        List<Place> placeList = placeService.getAll(userId);
        //场景数据
        List<Scene> sceneList = sceneService.load(userId);
        map.put("userId",userId);
        map.put("deviceList",deviceList);
        map.put("placeList",placeList);
        map.put("sceneList",sceneList);
        return map;
    }



}
