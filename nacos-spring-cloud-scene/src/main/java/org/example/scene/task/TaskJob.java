package org.example.scene.task;

import org.example.nacosspringcloudcommonentity.DownData;
import org.example.nacosspringcloudcommonentity.Message;
import org.example.nacosspringcloudcommonentity.Scene;
import org.example.nacosspringcloudcommonentity.Task;
import org.example.scene.dao.SceneDao;
import org.example.scene.dao.TaskDao;
import org.example.scene.service.feiginService.CommandService;
import org.example.scene.service.feiginService.MessageService;
import org.example.scene.service.feiginService.WebsocketService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class TaskJob extends QuartzJobBean {
    @Resource
    private SceneDao sceneDao;

    @Resource
    private TaskDao taskDao;

    @Resource
    private CommandService commandService;

    @Resource
    private MessageService messageService;
    @Resource
    private WebsocketService websocketService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        Integer taskId = Integer.parseInt(key.getName());
        Task task1 = taskDao.selectById(taskId);
        Scene scene = sceneDao.queryById(task1.getScenesId());
        Integer userId = scene.getUserId();
        String deviceId = task1.getDeviceId();


        if(task1.getCmd() == 121){
            long t = System.currentTimeMillis();//获得当前时间的毫秒数
            Random rd = new Random(t);//作为种子数传入到Random的构造器中
            int r = rd.nextInt(1000000);
            String serialNumber = r + deviceId;
            DownData downData = new DownData();
            String data1 = "{}";
            downData.setData(data1);
            downData.setCmd(102);
            downData.setProtocol("tcp");
            downData.setDeviceId(deviceId);
            downData.setUserId(userId);
            downData.setSerialNumber(serialNumber);
            Date d = new Date();
            downData.setTime(d);
            System.out.println(downData);
            commandService.downForward(downData);


            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }




            long millis = System.currentTimeMillis();//获得当前时间的毫秒数
            Random random = new Random(millis);//作为种子数传入到Random的构造器中
            int r1 = random.nextInt(1000000);
            String serialNumber1 = r1 + deviceId;
            DownData downData1 = new DownData();
            String data = task1.getData().toString();
            downData1.setData(data);
            downData1.setProtocol("tcp");
            downData1.setDeviceId(deviceId);
            downData1.setUserId(userId);
            downData1.setSerialNumber(serialNumber1);
            downData1.setCmd(114);
            Date d1 = new Date();
            downData1.setTime(d1);
            System.out.println(downData1);
            commandService.downForward(downData1);

            try {
                Thread.sleep(4000);
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
            downData2.setDeviceId(deviceId);
            downData2.setUserId(userId);
            downData2.setSerialNumber(serialNumber2);
            downData2.setCmd(112);
            Date d2 = new Date();
            downData2.setTime(d2);
            System.out.println(downData2);
            commandService.downForward(downData2);
            if(task1.getTaskCycle()==null){
                taskDao.deleteById(taskId);
            }


        }else {

            /*生成随机序列*/
            long t = System.currentTimeMillis();//获得当前时间的毫秒数
            Random rd = new Random(t);//作为种子数传入到Random的构造器中
            int i = rd.nextInt(1000000);
            String serialNumber = i + task1.getDeviceId();
            //向下发送指令
            DownData downData = new DownData();
            downData.setCmd(task1.getCmd());
            downData.setDeviceId(task1.getDeviceId());
            downData.setProtocol("tcp");
            downData.setUserId(userId);
            downData.setData(task1.getData().toString());
            downData.setSerialNumber(serialNumber);
            Date d = new Date();
            downData.setTime(d);
            commandService.downForward(downData);
            if(task1.getTaskCycle()==null){
                taskDao.deleteById(taskId);
            }



      /*  //数据库中加入消息记录   并向前端推送消息
        Message message = new Message();
        message.setDeviceId(task1.getDeviceId());
        message.setMalfunction(0);
        message.setTime(d);
        message.setContext("定时任务正在运行");
        int messageId = messageService.insert(message);
        message.setMessageId(messageId);
        websocketService.pushMessage(userId,message);*/
        }

    }
}
