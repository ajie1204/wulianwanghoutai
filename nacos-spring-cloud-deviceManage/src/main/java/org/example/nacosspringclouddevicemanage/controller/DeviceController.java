package org.example.nacosspringclouddevicemanage.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.nacosspringcloudcommonentity.Device;
import org.example.nacosspringcloudcommonentity.Place;
import org.example.nacosspringcloudcommonentity.util.TimeUtil;
import org.example.nacosspringclouddevicemanage.service.CheckDeviceService;
import org.example.nacosspringclouddevicemanage.service.DeviceService;
import org.example.nacosspringclouddevicemanage.service.water_service.Device1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.*;


@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private CheckDeviceService checkDeviceService;

    @Autowired
    private Device1Service device1Service;

    //查找设备所属系统
    @GetMapping("/systemName/{deviceId}")
    public String systemName(@PathVariable String deviceId){
        String name = deviceService.getSystemName(deviceId);
        return name;
    }
    

    //查找该用户下的所有设备
    @GetMapping("/getAllDevice/{userId}")
    public List<Device> findAll(@PathVariable int userId){
        List<Device> deviceList = deviceService.findAll(userId);
        return deviceList;
    }

    //分页查找所有设备
    @GetMapping("/getDevicePage/{pageNum}/{pageSize}")
    public Map<String,Object> getDeviceMap(@PathVariable int pageNum,@PathVariable int pageSize){
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Device> deviceList = deviceService.findByPage();
        //计算剩余时间
        for(Device d:deviceList){
            d.remains= TimeUtil.getDays(365, d.activationTime, new Date());
        }
        PageInfo<Device> pageInfo = new PageInfo<>(deviceList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("deviceList",pageInfo.getList());
        return map;
    }

    @GetMapping("/findForIndex/{userId}")
    public List<Device> getIndexMap(@PathVariable Integer userId){
        List<Device> deviceList = deviceService.findForIndex(userId);
        return deviceList;
    }

    //根据设备id查找设备
    @GetMapping("/getDevice/{deviceId}")
    public Device getDevice(@PathVariable String deviceId){
        return  deviceService.findByDeviceId(deviceId);
    }



    //用户手动激活设备

    //通过蓝牙激活设备

    //搜索框查找设备
    @PostMapping("/findByBar")
    public Map<String,Object> getDeviceByBar(@RequestBody Device device){
        Map<String,Object> map = new HashMap<>();
//        PageHelper.startPage(device.getPageNum(),device.getPageSize());
        List<Device> deviceList = deviceService.findBySearchBar(device);
        PageInfo<Device> pageInfo = new PageInfo<>(deviceList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("deviceList",pageInfo.getList());
        return map;
    }

    //后台管理系统新增设备
    @PostMapping("/addDevice")
    public Map<String,Object> addDevice(@RequestBody Device device){
        Map<String,Object> map = new HashMap<>();
        deviceService.add(device);
        map.put("result","添加成功");
//        PageHelper.startPage(device.getPageNum(),device.getPageSize());
        List<Device> deviceList = deviceService.findBySearchBar(device);
        PageInfo<Device> pageInfo = new PageInfo<>(deviceList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("deviceList",pageInfo.getList());
        return map;
    }

    //后台管理系统修改设备
    @PostMapping("/updateDevice")
    public Map<String,Object> updateDevice(@RequestBody Device device){
        Map<String,Object> map = new HashMap<>();
        deviceService.updateDevice(device);
        map.put("result","修改成功");
//        PageHelper.startPage(device.getPageNum(),device.getPageSize());
        List<Device> deviceList = deviceService.findByPage();
        PageInfo<Device> pageInfo = new PageInfo<>(deviceList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("deviceList",pageInfo.getList());
        return map;
    }

    //后台管理系统删除设备
    @PostMapping("/delDevice")
    public Map<String,Object> delDevice(@RequestBody Device device){
        Map<String,Object> map = new HashMap<>();
        deviceService.delDevice(device);
        map.put("result","删除成功");
//        PageHelper.startPage(device.getPageNum(),device.getPageSize());
        List<Device> deviceList = deviceService.findBySearchBar(device);
        PageInfo<Device> pageInfo = new PageInfo<>(deviceList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("deviceList",pageInfo.getList());
        return map;
    }

    //鉴权时查询deviceId
    @GetMapping("/checkDevice")
    public String checkDevice() {
        return checkDeviceService.check();
    }
    //鉴权分配deviceId更新数据库
    @PostMapping("/updataCheckDevice")
    public void updataCheckDevice(@RequestBody Device device){
        checkDeviceService.upData(device);
    }


    //用户手动激活设备
    @PostMapping("/activate")
    public int activateDevice(@RequestParam String deviceId,@RequestParam Integer userId,@RequestParam String deviceName) throws Exception {
        System.out.println("设备激活方法被调用");

        //处理设备名称问题
        // 将前端发来的添加设备数据存入数据库
        Device device1 = new Device();
        device1.setDeviceId(deviceId);
        // 判断设备名称是否重复
        List<Device> devices = deviceService.selectDeviceName(userId);
        if(devices!=null && !devices.isEmpty()){
            for (Device existingDeviceName:devices) {
                if(deviceName.equals(existingDeviceName.getDeviceName())){
                    throw new Exception("设备名重复");
                }
            }
        }
        device1.setDeviceName(deviceName);

        device1.setUserId(userId);
        deviceService.add(device1);

        Device device = deviceService.findByDeviceId(deviceId);
        if (device.getUserId()!=null){
            return -1;
        }

        return deviceService.activateDevice(deviceId,userId,deviceName);

    }

    //注销设备
    @PostMapping("/unActivate")
    public int unActivate(@RequestParam String deviceId){
        Device device = deviceService.findByDeviceId(deviceId);
        if (device!=null){
            return  deviceService.unActivate(deviceId);
        }
        return 0;
    }



}
