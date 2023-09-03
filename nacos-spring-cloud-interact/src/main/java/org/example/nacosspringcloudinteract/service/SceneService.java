package org.example.nacosspringcloudinteract.service;

import org.example.nacosspringcloudcommonentity.Scene;
import org.example.nacosspringcloudcommonentity.Task;
import org.example.nacosspringcloudcommonentity.WaterDeviceTask;
import org.example.nacosspringcloudcommonentity.vo.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Service
@FeignClient("sceneManage")
public interface SceneService {


    @GetMapping("/scene/load/{userId}")
    List<Scene> load(@PathVariable("userId") Integer userId);


    @PostMapping("/task/insert")
    Response<Task> insertTask(@RequestBody Task task);

    @GetMapping("/task/selectByScenesId/{scenesId}")
    Map<String,Object> selectByScenesId(@PathVariable  Integer scenesId);

    @PostMapping("/scene/addScene")
     Map<String,Object> add(@RequestBody Scene scene);

    //删除场景
    @GetMapping("/scene/delScene/{id}")
    Map<String,Object> deleteById(@PathVariable Integer id);

    //删除单个任务
    @DeleteMapping("/task/delete/{taskId}")
    Response<Task> delete(@PathVariable Integer taskId);

    //查看设备可选任务
    @GetMapping("/task/selectWaterDeviceTask")
    List<WaterDeviceTask> findWaterDeviceTask();

    //通过TaskId查找任务
    @GetMapping("/task/selectById/{taskId}")
    Task selectById(@PathVariable Integer taskId);

    //修改任务
    @PostMapping("/task/update")
    Response<Task> update(@RequestBody Task task);

    //暂停、继续任务
    @PostMapping("/task/updateStatus")
    public Response<Task> updateStatus(@RequestBody Task task);

}
