package org.example.nacosspringcloudinteract.controller.place;

import org.example.nacosspringcloudcommonentity.Device;
import org.example.nacosspringcloudcommonentity.Place;
import org.example.nacosspringcloudcommonentity.vo.Response;
import org.example.nacosspringcloudinteract.service.DeviceService;
import org.example.nacosspringcloudinteract.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    PlaceService placeService;

    @Autowired
    DeviceService deviceService;


    /*新增场所*/
    @RequestMapping(value = "insert/{userId}/{placeName}", method = RequestMethod.GET)
    public Response<Place> insert(@PathVariable("userId") Integer userId, @PathVariable("placeName") String placeName ) {



        Place place = new Place();
        place.setPlaceName(placeName);
        place.setUserId(userId);
        int result = placeService.insertPlace(place);
        if (result > 0) {
           return Response.createSuccessResponse("新增成功");
        }else if (result == -1){
        return Response.createErrorResponse("场景名已经存在");}

        return Response.createErrorResponse("新增失败");

    }


    /**
     * 根据用户Id查找场所
     * @param userId
     * @return
     */
    @RequestMapping(value = "/findPlaceById/{userId}", method = RequestMethod.GET)
    public Map<String,Object> getAll(@PathVariable("userId") Integer userId) {

        /*验证token*/
        Map<String,Object> map = new HashMap<>();
        List<Place> result = placeService.getAll(userId);
        if (!result.isEmpty()) {
            map.put("placeList",result);
            return map;
        }
        map.put("result","加载失败");
        return map;

    }


    /*删除场所*/
    @GetMapping("/delete/{placeId}")
    public Response<String> delete(@PathVariable("placeId") Integer placeId) {


        deviceService.unbound(placeId);

            int result = placeService.delete(placeId);
            if (result>0) {
                return Response.createSuccessResponse("删除场所成功");
            }else {
                return  Response.createErrorResponse("删除场所失败");
            }
       

    }


    /**
     * 查询场所下的设备
     * @param placeId
     * @return
     */
    @GetMapping("/open/{placeId}")
    public Map<String,Object> openPlace(@PathVariable("placeId") Integer placeId) {
        
        Map<String,Object> map = new HashMap<>();
        List<Device> result = deviceService.openPlace(placeId);
        if (!result.isEmpty()){
           map.put("deviceList",result);
           return map;
        }
        map.put("result","场所下设备为空");
        return map;

    }


    /*添加设备*/
    @GetMapping("/insertDevice/{userId}")
    public Response<HashMap<String, List<Device>>> insertDevice(@PathVariable("userId") Integer userId) {

        HashMap<String, List<Device>> map = new HashMap<>();
        List<Device> devices = deviceService.selectUnbound(userId);
        if (!devices.isEmpty()){
            map.put("deviceList",devices);
            return Response.createSuccessResponse(map);
        }

        return Response.createErrorResponse("无设备可添加");
    }


    /*设备绑定场所*/
    @GetMapping("/bind/{deviceId}/{placeId}")
    public Response<String> bindPlace(@PathVariable("deviceId") String deviceId,@PathVariable("placeId") Integer placeId) {

        int result = deviceService.bindPlace(deviceId, placeId);

        return Response.createErrorResponse("无设备可添加");
    }


    /*场所删除设备*/
    @GetMapping("/deleteDevice/{deviceId}")
    public Response<String> deleteDevice(@PathVariable("deviceId") String deviceId) {

        int result = deviceService.unboundOne(deviceId);
        if (result>0){
            return Response.createSuccessResponse("删除设备成功");
        }
        return Response.createErrorResponse("删除失败");
    }







}
