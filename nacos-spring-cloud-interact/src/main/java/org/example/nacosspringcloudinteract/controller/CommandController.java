package org.example.nacosspringcloudinteract.controller;


import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.example.nacosspringcloudcommonentity.DownData;
import org.example.nacosspringcloudcommonentity.Place;
import org.example.nacosspringcloudcommonentity.TKData;
import org.example.nacosspringcloudcommonentity.UserOrder;
import org.example.nacosspringcloudcommonentity.vo.Response;
import org.example.nacosspringcloudinteract.service.DeviceService;
import org.example.nacosspringcloudinteract.service.PlaceService;
import org.example.nacosspringcloudinteract.service.UpDataService;
import org.example.nacosspringcloudinteract.service.WaterService;
import org.example.nacosspringcloudinteract.websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import java.util.*;


/**
 * 命令控制器
 *
 * @author 31477
 * @date 2023/02/21
 */
@Slf4j
@RestController
@RequestMapping("/interact")
public class CommandController {


    /**
     * 下客户端交互
     */
    @Resource
    InteractDownClient interactDownClient;
    /**
     * 设备服务
     */
    @Autowired
    DeviceService deviceService;
    /**
     * 场所服务
     */
    @Autowired
    PlaceService placeService;
    /**
     * 水服务
     */
    @Autowired
    WaterService waterService;

    /**
     * 网络套接字
     */
    @Autowired
    WebSocket webSocket;

    /**
     * 数据服务
     */
    @Autowired
    UpDataService upDataService;

    /**
     * 下行链路数据
     */
    @Resource
    DownLinkData downLinkData;

    @Resource


    @Autowired
    RedisTemplate redisTemplate;


    /**
     * 集水热
     *
     * @param userOrder 用户订单
     *//*给设备下发指令*/
    @PostMapping("/set")
    public void setWaterHeat(@RequestBody UserOrder userOrder) {
        DownData downData = new DownData();
        String deviceId = userOrder.getDeviceId();



        //1、调用用户管理平台和设备管理平台获取设备和用户的信息，封装后传给自定义协议网关
        String protocol = deviceService.systemName(deviceId);
        /*生成随机序列*/
        long t = System.currentTimeMillis();//获得当前时间的毫秒数
        Random rd = new Random(t);//作为种子数传入到Random的构造器中
        int i = rd.nextInt(1000000);
        String serialNumber = i + deviceId;
        /*封装数据传递类*/
        downData.setCmd(userOrder.getCmd());
        downData.setDeviceId(userOrder.getDeviceId());
        downData.setProtocol(protocol);
        downData.setUserId(userOrder.getUserId());
        Object jsonData = userOrder.getJsonData();

        downData.setData(jsonData.toString().replaceAll("=", ":"));

        downData.setSerialNumber(serialNumber);

        Date d = new Date();
 /*       SimpleDateFormat sbf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        downData.setTime(sbf.format(d));*/
        downData.setTime(d);
        System.out.println("success1");


        interactDownClient.downForward(downData);
        //1、用户指令存储到数据库
        downLinkData.getDownData(downData);
        //2、接收设备平台返回的异步信息
    }

    @PostMapping("/set2")
    public void setWaterHeat2(@RequestBody UserOrder userOrder){

        // 从前端解析出 设备ID和cmd
        DownData downData = new DownData();

        String deviceId = userOrder.getDeviceId();
        System.out.println("设备Id：" + deviceId);

        Integer cmd = userOrder.getCmd();
        System.out.println("接受指令：" + cmd);

        Object jsonData = userOrder.getJsonData();

        String data = JSONObject.toJSONString(jsonData);
        System.out.println("接收温度：" + data);

        String msg = " ";

        // 处理message中的值，生成HashMap对象
        Map<String, Object> jsMap = new HashMap<>();
        jsMap.put("deviceID", deviceId);
        jsMap.put("cmd", cmd);
        jsMap.put("data", data);
        jsMap.put("msg", msg);

        // 将HashMap对象转化为string
        String message = JSONObject.toJSONString(jsMap);

        // 构建下指令需要的数据的hashMap
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("qos", downData.getQos());
        map.put("topic", downData.getTopic());
        map.put("retain", downData.isRetain());

        // 把HashMap转成JSONObj格式
        JSONObject json1 = new JSONObject();
        json1.put("jsonTest", JSONObject.toJSONString(map));

        String jsonTest = json1.getString("jsonTest");
        String requestBody = JSONUtil.toJsonStr(jsonTest);
        System.out.println("requestBody:" + requestBody);

        String url = "http://111.47.28.118:7301/broker/publish/sendMessage";
        String access_token = (String) redisTemplate.opsForValue().get("access_token");
        String res = HttpUtil.createPost(url)
                .header("Authorization", "Bearer " + access_token)
                .body(requestBody)
                .execute()
                .body();

        System.out.println("接收回应：" + res);

        //4、用户指令存储到数据库
        downLinkData.getDownData(downData);
    }


    /**
     * 获取设备数据
     *
     * @param userId   用户id
     * @param deviceId 设备id
     * @return {@link Map}<{@link String},{@link Object}>
     */
    @GetMapping("/getDeviceDetail/{userId}/{deviceId}")
    public Map<String, Object> getDeviceData(@PathVariable String userId, @PathVariable String deviceId) throws JsonProcessingException {
//        Map<String, Object> map = new HashMap<>();
//        return waterService.getDeviceData(deviceId);


        // 处理获取设备影子的数据，生成HashMap
        Map<String, Object> map = new HashMap<>();
        TKData tkData = new TKData();
        map.put("ids", tkData.getIds());
        map.put("startTime", tkData.getStartTime());
        map.put("endTime", tkData.getEndTime());

        // 把HashMap转成json格式
        JSONObject json1 = new JSONObject();
        json1.put("jsonTest", JSONObject.toJSONString(map));

        String jsonTest = json1.getString("jsonTest");
        String requestBody = JSONUtil.toJsonStr(jsonTest);

        // 调用接口获取设备影子
        String url = "http://111.47.28.118:7301/link/deviceInfo/getDeviceInfoShadow";
        String access_token = (String) redisTemplate.opsForValue().get("access_token");
        String res = HttpUtil.createPost(url)
                .header("Authorization", "Bearer " + access_token)
                .body(requestBody)
                .execute()
                .body();
        System.out.println("返回的设备影子：" + res);


        // 处理res 在返回的res中加入"last(deviceId)", deviceId
        // 解析 JSON字符串 为 JsonNode对象
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(res);
        // 找到包含所需数据的键
        JsonNode dataNode = jsonNode.get("data");
        String targetKey = null;
        Iterator<String> fieldNames = dataNode.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            if (dataNode.get(fieldName).isArray()) {
                targetKey = fieldName;
                break;
            }
        }
        // 更新数组中第一个项的"last(deviceId)"字段
        if (targetKey != null) {
            JsonNode dataArray = dataNode.get(targetKey);
            if (dataArray.isArray() && dataArray.size() > 0) {
                ((ObjectNode) dataArray.get(0)).put("last(deviceId)", deviceId);
            }
        }

        
        String updatedJsonStr = objectMapper.writeValueAsString(jsonNode);
        // 将处理好的updatedJsonStr转化为jsonObject
        JSONObject jsonObject = JSON.parseObject(updatedJsonStr);
        // 将得到的jsonObject调用方法写入数据库
        waterService.getUpData(jsonObject);
        return waterService.getDeviceData(deviceId);

//        // 解析获得某个设备的指令
//        Map<String, Object> map1 = new HashMap<>();
//        map1.put("pageNum", tkData.getPageNum());
//        map1.put("pageSize", tkData.getPageSize());
//        map1.put("serviceId", tkData.getServiceId());
//
//        // 把map转成json格式
//        JSONObject json2 = new JSONObject();
//        json2.put("jsonTest", JSONObject.toJSONString(map1));
//
//        String jsonTest1 = json2.getString("jsonTest");
//        String requestBody1 = JSONUtil.toJsonStr(jsonTest1);
//
//        String url1 = "http://111.47.28.118:7301/link/productCommands/list";
//        String res1 = HttpUtil.createGet(url1)
//                .header("Authorization", "Bearer " + access_token)
//                .body(requestBody1)
//                .execute()
//                .body();
//        System.out.println("返回的设备指令：" + res1);
    }


    /**
     * 了命令
     *
     * @param json json
     */
    @PostMapping("/up")
    public void upCommand(@RequestBody String json) {

        log.info("interact的up被调用，数据为" + json);
        JSONObject jsonObject = JSON.parseObject(json);
        if (jsonObject.containsKey("upload_code")) {
            /*调用设备管理平台处理上传数据*/
            waterService.getUpData(jsonObject);
            /*将数据推给前端*/
            upDataService.pushData(jsonObject);
        }

        /*应答帧*/
        if (jsonObject.containsKey("cmd_answer")) {
            System.out.println("应答帧到达");
            upDataService.pushAnswer(jsonObject);
            waterService.getAnswerData(jsonObject);

        }

    }

    /**
     * 激活
     * @param jsonObject json对象
     * @return {@link Response}<{@link Object}>
     *激活设备*/
    @PostMapping("/activate")
    public Response<Object> activate(@RequestBody JSONObject jsonObject) throws Exception {

        String deviceId = jsonObject.getString("deviceId");
        Integer userId = jsonObject.getInteger("userId");
        String deviceName = jsonObject.getString("deviceName");

        // 处理场所问题
        String place = jsonObject.getString("place");
        // 根据userId查找库里已经存在的场所
        List<Place> placeList = placeService.selectPlace(userId);
        // 将库里所有的场所输出到placeNames
        List<String> placeNames = new ArrayList<>();
        for (Place place1 : placeList) {
            placeNames.add(place1.getPlaceName());
        }
        System.out.println(placeNames);

        // 将用户输入的场所名与库里的作比较
        boolean isNotExist = true;
        for (String place1 : placeNames) {
            if (place1.equals(place)) {
                isNotExist = false;
                break;
            }
        }
        if (isNotExist) {
            throw new Exception("错误：添加的场所名不存在");
        } else {
            System.out.println("添加的场所名可用");
        }


        //
        int i = deviceService.activateDevice(deviceId, userId, deviceName);
        if (i == -1) {
            return Response.createErrorResponse("设备已经被绑定");
        }

        if (i > 0) {
           /* DownData downData = new DownData();
            downData.setCmd(101);
            downData.setUserId(userId);
            downData.setDeviceId(deviceId);
            downData.setProtocol("mqtt");


            *//*生成随机序列*//*
            long t = System.currentTimeMillis();//获得当前时间的毫秒数
            Random rd = new Random(t);//作为种子数传入到Random的构造器中
            int r = rd.nextInt(1000000);
            String serialNumber = r + deviceId;

 *//*           JSONObject jsonObj = new JSONObject();
//            Map<String, String> ingredients = new HashMap<String, String>();

            jsonObj.put("rech_days", 100);

            jsonObj.put("filter_lifes", "240;360");

            jsonObj.put("filter_max_lifes", "240;360");

            jsonObj.put("max", 100);
            jsonObj.put("min",10);

            String data = jsonObj.toJSONString();*//*
            String data1 = "{rech_days:365, filter_lifes:240;360, filter_max_lifes:240;360, max:100, min:10}";
            downData.setData(data1);
            downData.setSerialNumber(serialNumber);

            Date d = new Date();
 *//*       SimpleDateFormat sbf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        downData.setTime(sbf.format(d));*//*
            downData.setTime(d);

            System.out.println(downData);
            interactDownClient.downForward(downData);*/


            return Response.createSuccessResponse("激活成功");
        }
        return Response.createErrorResponse("激活失败");

    }


    /**
     * 测试
     *
     * @param jsonObject json对象
     * @return {@link Response}<{@link String}>
     */
    @PostMapping("/test")
    public Response<String> test(@RequestBody JSONObject jsonObject) {
        String deviceId = jsonObject.getString("deviceId");
        Integer userId = jsonObject.getInteger("userId");


        long t = System.currentTimeMillis();//获得当前时间的毫秒数
        Random rd = new Random(t);//作为种子数传入到Random的构造器中
        int r = rd.nextInt(1000000);
        String serialNumber = r + deviceId;
        DownData downData = new DownData();
        String data1 = "{}";
        downData.setData(data1);
        downData.setCmd(102);
        downData.setProtocol("tcp");
        downData.setDeviceId("00000044");
        downData.setUserId(18);
        downData.setSerialNumber(serialNumber);
        Date d = new Date();
        downData.setTime(d);
        System.out.println(downData);
        interactDownClient.downForward(downData);


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long millis2 = System.currentTimeMillis();//获得当前时间的毫秒数
        Random random2 = new Random(millis2);//作为种子数传入到Random的构造器中
        int r2 = random2.nextInt(1000000);
        String serialNumber2 = r2 + deviceId;
        DownData downData2 = new DownData();
        String data2 = "{}";
        downData2.setData(data2);
        downData2.setProtocol("tcp");
        downData2.setDeviceId("00000044");
        downData2.setUserId(18);
        downData2.setSerialNumber(serialNumber);
        downData2.setCmd(112);
        Date d2 = new Date();
        downData2.setTime(d2);
        System.out.println(downData2);
        interactDownClient.downForward(downData2);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long millis = System.currentTimeMillis();//获得当前时间的毫秒数
        Random random = new Random(millis);//作为种子数传入到Random的构造器中
        int r1 = random.nextInt(1000000);
        String serialNumber1 = r1 + deviceId;
        DownData downData1 = new DownData();
        String data = "{maxTemp:65}";
        downData1.setData(data);
        downData1.setProtocol("tcp");
        downData1.setDeviceId("00000044");
        downData1.setUserId(18);
        downData1.setSerialNumber(serialNumber);
        downData1.setCmd(114);
        Date d1 = new Date();
        downData1.setTime(d1);
        System.out.println(downData1);
        interactDownClient.downForward(downData1);


        return Response.createSuccessResponse("test成功");
    }


    /**
     * 下客户端交互
     *
     * @author 31477
     * @date 2023/02/21
     */
    @FeignClient(name = "custom-protocol-gateway")
    public interface InteractDownClient {
        /**
         * 下前进
         *
         * @param downData 下来数据
         */
        @PostMapping("/gateway/down")
        void downForward(@RequestBody DownData downData);
    }

    /**
     * 下行链路数据
     *
     * @author 31477
     * @date 2023/02/21
     */
    @FeignClient(name = "deviceManage")
    public interface DownLinkData {
        /**
         * 下来数据
         *
         * @param downData 下来数据
         */
        @PostMapping("/writeWaterData/downLinkData")
        void getDownData(@RequestBody DownData downData);
    }

}
