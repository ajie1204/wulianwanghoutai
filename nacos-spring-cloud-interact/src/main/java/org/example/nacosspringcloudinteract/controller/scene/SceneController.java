package org.example.nacosspringcloudinteract.controller.scene;

import org.example.nacosspringcloudcommonentity.Device;
import org.example.nacosspringcloudcommonentity.Scene;
import org.example.nacosspringcloudcommonentity.Task;
import org.example.nacosspringcloudcommonentity.WaterDeviceTask;
import org.example.nacosspringcloudcommonentity.vo.Response;
import org.example.nacosspringcloudinteract.service.DeviceService;
import org.example.nacosspringcloudinteract.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scene")
public class SceneController {


    @Autowired
    private SceneService sceneService;
    @Autowired
    private DeviceService deviceService;

    /*打开场景页面*/
    @GetMapping("/load/{userId}")
    public Map<String,Object> loadScene(@PathVariable("userId") Integer userId) {



        Map<String,Object> map = new HashMap<>();
        List<Scene> result = sceneService.load(userId);
        if (!result.isEmpty()){
            map.put("sceneList",result);
            return map;
        }
        map.put("result","打开场景失败");
        return map;

    }

    /*添加场景*/
    @PostMapping("/addScene")
    public Map<String,Object> addScene(@RequestBody Scene scene){
        return sceneService.add(scene);
    }

    /*删除场景，并删除该场景下的任务*/
    @GetMapping("/delScene/{scenesId}")
    public Map<String,Object> delScene(@PathVariable Integer scenesId){
        return sceneService.deleteById(scenesId);
    }


    /*添加任务*/
    @PostMapping("/insertTask")
    public Response<Task> insertTask(@RequestBody Task task) {
        System.out.println(task);
        return sceneService.insertTask(task);

    }

    /*删除单个任务*/
    @GetMapping("/delTask/{taskId}")
    public Response<Task> delete(@PathVariable Integer taskId){
        return sceneService.delete(taskId);
    }

    /*查看场景下的任务*/
    @GetMapping("/selectTask/{scenesId}")
    public Map<String,Object> findTaskList(@PathVariable Integer scenesId){
        Map<String,Object> map = new HashMap<>();
        return sceneService.selectByScenesId(scenesId);
    }

    /*添加场景任务，查看用户设备*/
    @GetMapping("/selectDevice/{userId}")
    public Map<String,Object> findDevice(@PathVariable Integer userId){
        Map<String,Object> map = new HashMap<>();
        List<Device> deviceList = deviceService.findAll(userId);
        map.put("result",deviceList);
        return map;
    }

    /*选择设备，选择任务*/
    @GetMapping("/selectWaterDeviceTask")
    public List<WaterDeviceTask> findWaterDeviceTask(){
        return sceneService.findWaterDeviceTask();
    }

    //通过TaskId查找任务
    @GetMapping("/selectById/{taskId}")
    public Task findWaterDeviceTask(@PathVariable Integer taskId){
        return sceneService.selectById(taskId);
    }

    //修改任务
    @PostMapping("/task/update")
    Response<Task> update(@RequestBody Task task){
        return sceneService.update(task);
    }

    //暂停、继续任务
    @PostMapping("/task/updateStatus")
    public Response<Task> updateStatus(@RequestBody Task task){
        return sceneService.updateStatus(task);
    }

}
