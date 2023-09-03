package org.example.nacosspringcloudsysmanage.service.feignService;

import org.example.nacosspringcloudcommonentity.Device;
import org.example.nacosspringcloudcommonentity.DownData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Service
@FeignClient(name = "deviceManage")
public interface DeviceManageService {

    //分页查询所有设备
    @GetMapping("/device/getDevicePage/{pageNum}/{pageSize}")
    Map<String,Object> getDeviceMap(@PathVariable int pageNum, @PathVariable int pageSize);

    //分页获得饮水操作数据
    @GetMapping("/readWaterData/getWaterDataList/{pageNum}/{pageSize}")
    Map<String,Object> getDownData(@PathVariable int pageNum, @PathVariable int pageSize);

    //搜索栏查找命令日志
    @PostMapping("readWaterData/findDataByBar")
    Map<String,Object> getByBar(@RequestBody DownData downData);

    //删除命令日志
    @PostMapping("writeWaterData/delData")
    Map<String,Object> delById(@RequestBody DownData downData);

    //搜索栏查找设备
    @PostMapping("/device/findByBar")
    Map<String,Object> getDeviceByBar(@RequestBody Device device);

    //新增设备
    @PostMapping("/device/addDevice")
    Map<String,Object> addDevice(@RequestBody Device device);

    //修改设备
    @PostMapping("/device/updateDevice")
    Map<String,Object> updateDevice(@RequestBody Device device);

    //删除设备
    @PostMapping("/device/delDevice")
    Map<String,Object> delDevice(@RequestBody Device device);

}
