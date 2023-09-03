package org.example.nacosspringclouddevicemanage.controller.water_controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.nacosspringcloudcommonentity.DownData;
import org.example.nacosspringcloudcommonentity.TKData;
import org.example.nacosspringcloudcommonentity.water.WaterDeviceStatus;
import org.example.nacosspringcloudcommonentity.water.WaterFilter;
import org.example.nacosspringcloudcommonentity.water.WaterLife;
import org.example.nacosspringcloudcommonentity.water.WaterQuality;
import org.example.nacosspringclouddevicemanage.dao.water_dao.WaterCommandDao;
import org.example.nacosspringclouddevicemanage.service.water_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/readWaterData")
public class WaterReadController {

    @Autowired
    private WaterQualityService waterQualityService;

    @Autowired
    private WaterTempService waterTempService;

    @Autowired
    private DeviceStatusService deviceStatusService;

    @Autowired
    private WaterLifeService waterLifeService;

    @Autowired
    private WaterFilterService waterFilterService;

    @Autowired
    private WaterCommandService waterCommandService;

    @Autowired
    private WaterCommandDao waterCommandDao;

    @Autowired
    private RedisTemplate redisTemplate;

    //返回饮水界面的所有数据 状态数据 水温 水质 剩余使用天数与滤芯寿命
    @GetMapping("/{deviceId}")
    public Map<String, Object> getDeviceData(@PathVariable String deviceId) {

        Map<String, Object> map = new HashMap<>();

        WaterDeviceStatus deviceStatus = deviceStatusService.findLately(deviceId);

        String temp = waterTempService.findLately(deviceId);

        WaterQuality waterQuality = waterQualityService.findLately(deviceId);

//        Date time = waterFilterService.findTime(deviceId);
//        WaterLife waterLife1 = waterLifeService.countLife(time);

        WaterFilter filter = waterFilterService.find(deviceId);
        WaterLife life = waterLifeService.count(filter);

        map.put("water_status", deviceStatus);
        map.put("water_temp", temp);
        map.put("water_quality", waterQuality);
//        map.put("water_life", waterLife1);
        map.put("water_life", life);
        return map;
    }

    //查找控制饮水设备的所有指令
    @GetMapping("/getWaterDataList/{pageNum}/{pageSize}")
    public Map<String, Object> getDownData(@PathVariable int pageNum, @PathVariable int pageSize) {
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(pageNum, pageSize);
        List<DownData> dataList = waterCommandService.findByPage();

        PageInfo<DownData> pageInfo = new PageInfo<>(dataList);
        map.put("总页数", pageInfo.getPages());
        map.put("总记录数", pageInfo.getTotal());
        map.put("当前页数", pageInfo.getPageNum());
        map.put("当前页面记录数", pageInfo.getSize());
        map.put("dataList", pageInfo.getList());
        return map;

    }

    //根据搜索框搜索
    @PostMapping("/findDataByBar")
    public Map<String, Object> getByBar(@RequestBody DownData downData) {
        Map<String, Object> map = new HashMap<>();
//        PageHelper.startPage(downData.getPageNum(),downData.getPageSize());
        List<DownData> dataList = waterCommandService.findByBar(downData);
        PageInfo<DownData> pageInfo = new PageInfo<>(dataList);
        map.put("总页数", pageInfo.getPages());
        map.put("总记录数", pageInfo.getTotal());
        map.put("当前页数", pageInfo.getPageNum());
        map.put("当前页面记录数", pageInfo.getSize());
        map.put("dataList", pageInfo.getList());
        return map;
    }


}
