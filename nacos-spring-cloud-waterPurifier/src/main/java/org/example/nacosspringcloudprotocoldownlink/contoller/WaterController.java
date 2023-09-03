package org.example.nacosspringcloudprotocoldownlink.contoller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.example.nacosspringcloudcommonentity.DownCommand;
import org.example.nacosspringcloudcommonentity.DownData;
import org.example.nacosspringcloudcommonentity.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/water")
public class WaterController {

    @Resource
    DownClient downClient;

    @Resource
    UpClient upClient;

    @Resource
    UpDevice upDevice;

    @Autowired
    private RedisTemplate redisTemplate;


    @PostMapping("/down")
    public String downConvert(@RequestBody DownData downData) {

        DownCommand downCommand = new DownCommand();
        /*打包底层所需的完整帧发送给netty服务器*/
        downCommand.setCmd(downData.getCmd());
        downCommand.setData(downData.getData());
        downCommand.setDeviceId(downData.getDeviceId());
        downCommand.setSerialNumber(downData.getSerialNumber());
        downCommand.setTime(downData.getTime().toString());

        System.out.println("success3");
        downClient.down(downData);
        return "success";
    }







    @PostMapping("/up")
    public void upLinkData(@RequestBody String json) {
        /*根据具体系统的协议来解析上传的json数据*/

        JSONObject jsonObject = JSON.parseObject(json);
        upClient.upLink(json);

        /*//发送给设备管理平台
        if (jsonObject.containsKey("cmd_answer")){
            upDevice.getAnswer(jsonObject);
        }else {
            upDevice.getUpData(jsonObject);
        }
        */

        /* *//*上传数据处理*//*
        if (jsonObject.containsKey("upload_code")){
            *//*将数据上传至物联网平台*//*
            upClient.upLink(json);
        }

        *//*应答帧*//*
        if (jsonObject.containsKey("cmd_answer")){
            System.out.println("应答帧到达");
            upClient.upLink(json);
        }*/
    }


    @FeignClient(name = "nettyServer")
    public interface DownClient {
        @PostMapping("/netty/down")
        void down(@RequestBody DownData downData);
    }

    @FeignClient(name = "interact")
    public interface UpClient {
        @PostMapping("/interact/up")
        void upLink(@RequestBody String json);
    }

    @FeignClient(name = "deviceManage")
    public interface UpDevice {
        @PostMapping("/writeWaterData/upLinkData")
        void getUpData(@RequestBody JSONObject jsonObject);

        @PostMapping("/writeWaterData/upAnswerData")
        void getAnswer(@RequestBody JSONObject jsonObject);
    }

}
