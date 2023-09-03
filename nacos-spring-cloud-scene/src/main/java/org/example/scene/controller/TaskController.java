package org.example.scene.controller;
 
import com.alibaba.nacos.shaded.io.opencensus.tags.TagKey;
import org.example.nacosspringcloudcommonentity.Task;
import org.example.nacosspringcloudcommonentity.WaterDeviceTask;
import org.example.nacosspringcloudcommonentity.constant.Const;
import org.example.nacosspringcloudcommonentity.vo.Response;
import org.example.scene.service.TaskService;
import org.example.scene.service.WaterDeviceTaskService;
import org.example.scene.utils.CronExpressionUtils;
import org.example.scene.utils.CronUtils;
import org.example.scene.utils.ScheduleUtils;
import org.quartz.Scheduler;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
 
/**
 * @author makejava
 * @since 2022-04-12 10:55:39
 */

@RestController
@RequestMapping("/task")
public class TaskController {
    /**
     * 服务对象
     */
    @Resource
    private TaskService taskService;

    @Resource
    private WaterDeviceTaskService waterDeviceTaskService;

    @Resource
    private Scheduler scheduler;
 

    @RequestMapping(value = "selectOne", method = RequestMethod.GET)
    public Response<Task> selectOne(Integer taskId) {
        if (taskId == null) {
            return Response.createErrorResponse("参数错误");
        }
        Task result = taskService.selectById(taskId);
        if(result != null){
            return Response.createSuccessResponse("查询成功", result);
        }
        return Response.createErrorResponse("查询失败");
    }
    

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Response<Task> insert(@RequestBody Task task) {
        //查看该任务是否已经存在
        List<Task> taskList = taskService.selectTaskList(task.getScenesId());
        for (Task task1:taskList){
            if (task1.getDeviceId().equals(task.getDeviceId())&&task1.getCmd().equals(task.getCmd())){
                return Response.createErrorResponse("该任务已存在，请勿重复添加");
            }
        }

        Object jsonData = task.getData();
        String data = "{}";
        if (!jsonData.equals("{}")) {
             data = jsonData.toString().replaceAll("=", ":");
        }
        task.setData(data);



        String time = task.getTiming();
        String cycle = task.getTaskCycle();

        if (task.getCmd() == 121) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(df.parse(time));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(Calendar.SECOND, -5);//这里是对天数进行增加
            time = df.format(calendar.getTime());
            System.out.println(df.format(calendar.getTime()));

        }

        //将用户定时字符串yyyy-MM-dd HH:mm转化为cronExpression
        String cronExpression = null;
        try {
            cronExpression = CronUtils.createCronExpression(time,cycle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        task.setCronExpression(cronExpression);
        System.out.println(cronExpression);

        int result1 = taskService.insert(task) ;
        if (result1 >0) {
            int result = ScheduleUtils.createScheduleJob(scheduler, task);
            if (result>0){
                return Response.createSuccessResponse("新增成功", task);
            }
        }
        return Response.createErrorResponse("新增失败",task);
    }
 

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Response<Task> update(@RequestBody Task task) {
//        List<Task> taskList = taskService.selectTaskList(task.getScenesId());
//        for (Task task1:taskList){
//            if (task1.getDeviceId().equals(task.getDeviceId())&&task1.getCmd().equals(task.getCmd())){
//                return Response.createErrorResponse("该任务已存在");
//            }
//        }
        ScheduleUtils.pauseScheduleJob(scheduler,task.getTaskId());
        ScheduleUtils.deleteScheduleJob(scheduler,task.getTaskId());

        Object jsonData = task.getData();
        String data = "{}";
        if (!jsonData.equals("{}")) {
            data = jsonData.toString().replaceAll("=", ":");
        }
        task.setData(data);

        String time = task.getTiming();
        String cycle = task.getTaskCycle();

        if (task.getCmd() == 121) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(df.parse(time));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(Calendar.SECOND, -5);//这里是对天数进行增加
            time = df.format(calendar.getTime());
            System.out.println(df.format(calendar.getTime()));

        }

        //将用户定时字符串yyyy-MM-dd HH:mm转化为cronExpression
        String cronExpression = null;
        try {
            cronExpression = CronUtils.createCronExpression(time,cycle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        task.setCronExpression(cronExpression);
        System.out.println(cronExpression);

        int result = taskService.update(task);
        ScheduleUtils.createScheduleJob(scheduler,task);
        if (result != -1) {
            return Response.createSuccessResponse("修改成功", null);
        }
        return Response.createErrorResponse("修改失败");
    }
 

    @DeleteMapping("/delete/{taskId}")
    public Response<Task> delete(@PathVariable Integer taskId) {
        if (taskId == null) {
            return Response.createErrorResponse("参数错误");
        }
        ScheduleUtils.pauseScheduleJob(scheduler,taskId);
        ScheduleUtils.deleteScheduleJob(scheduler,taskId);
        int result = taskService.deleteById(taskId);
        if (result > 0) {
            return Response.createSuccessResponse("删除成功", null);
        }
        return Response.createErrorResponse("删除失败");
    }

    //暂停、继续任务
    @PostMapping("/updateStatus")
    public Response<Task> updateStatus(@RequestBody Task task){

//        System.out.println(task);
        if (task.getTaskId()==0||task.getStatus()==null){
            return Response.createErrorResponse("参数错误");
        }
        if (task.getStatus()==1){
            ScheduleUtils.pauseScheduleJob(scheduler,task.getTaskId());
            task.setStatus(-1);
            taskService.update(task);
        }else {
            ScheduleUtils.resumeScheduleJob(scheduler,task.getTaskId());
            task.setStatus(1);
            taskService.update(task);
        }
        return Response.createSuccessResponse("更改成功", null);
    }

 

    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    public Response<List<Task>> selectAll() {
        List<Task> taskList = taskService.selectAll();

        if (taskList != null) {
            for (Task task : taskList) {
                task.setTaskName("task");
            }
            return Response.createSuccessResponse("查询成功", taskList);
        }
        return Response.createErrorResponse("查询失败");
    }
 

    @RequestMapping(value = "selectPage", method = RequestMethod.GET)
    public Response<Page<Task>> selectPage(Integer start, Integer limit) {
        if(start < 0 || limit <= 0){
            return Response.createErrorResponse("分页参数错误");
        }
        List<Task> taskList = taskService.selectPage(start, limit);
        Page<Task> page = new Page<>((start/limit)+1,limit);
        page.setRecords(taskList);
        page.setTotal(taskService.count());
        if (taskList != null) {
            return Response.createSuccessResponse("查询成功", page);
        }
        return Response.createErrorResponse("查询失败");
    }

    @GetMapping("/selectByScenesId/{scenesId}")
    public Map<String,Object> selectByScenesId(@PathVariable  Integer scenesId){
        Map<String, Object> map = new HashMap<>();
        List<Task> taskList = taskService.selectTaskList(scenesId);
        for (Task task : taskList) {
            task.setTaskName(task.getTaskName());
        }
        map.put("result",taskList);
        return map;
    }

    //查看设备可选任务
    @GetMapping("/selectWaterDeviceTask")
    public List<WaterDeviceTask> findWaterDeviceTask(){
        return waterDeviceTaskService.findAllTask();
    }

    @GetMapping("/selectById/{taskId}")
    public Task selectById(@PathVariable Integer taskId){
        return taskService.selectById(taskId);
    }
}    



