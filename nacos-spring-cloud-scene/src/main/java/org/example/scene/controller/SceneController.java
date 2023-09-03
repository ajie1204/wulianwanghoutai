package org.example.scene.controller;

import org.example.nacosspringcloudcommonentity.Scene;
import org.example.nacosspringcloudcommonentity.Task;
import org.example.scene.service.SceneService;
import org.example.scene.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Scene)表控制层
 *
 * @author makejava
 * @since 2022-04-11 18:50:59
 */
@RestController
@RequestMapping("/scene")
public class SceneController {
    /**
     * 服务对象
     */
    @Resource
    private SceneService sceneService;

    @Resource
    private TaskService taskService;


    /**
     * 根据用户id找用户的场景
     * @param userId
     * @return
     */
    @GetMapping("/load/{userId}")
    public List<Scene> load(@PathVariable("userId") Integer userId){
        return sceneService.selectByUser(userId);
    }




    /**
     * 分页查询
     *
     * @param scene 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Scene>> queryByPage(Scene scene, PageRequest pageRequest) {
        return ResponseEntity.ok(this.sceneService.queryByPage(scene, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Scene> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.sceneService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param scene 实体
     * @return 新增结果
     */
    @PostMapping("/addScene")
    public Map<String,Object> add(@RequestBody  Scene scene) {
        Map<String, Object> map = new HashMap<>();
        Scene scene1 = this.sceneService.queryByName(scene.getUserId(), scene.getScenesName());
        if(scene1!=null){
            map.put("result","添加失败，该场景已存在");
        }else if (scene.getScenesName()==null||scene.getUserId()==null){
            map.put("result","添加失败，请填写场景的完整信息");
        }else {
            this.sceneService.insert(scene);
            map.put("result","添加成功");
        }
        return map;
    }

    /**
     * 编辑数据
     *
     * @param scene 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Scene> edit(Scene scene) {
        return ResponseEntity.ok(this.sceneService.update(scene));
    }

    /**
     * 删除数据，并删除场景下对应的任务
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @GetMapping("/delScene/{id}")
    public Map<String,Object> deleteById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        List<Task> taskList = taskService.selectTaskList(id);
        if (taskList!=null){
            taskService.deleteByScenesId(id);
        }
        sceneService.deleteById(id);
        map.put("result", "删除成功");
        return map;
    }


}

