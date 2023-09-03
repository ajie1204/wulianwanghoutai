package org.example.scene.utils;
import org.example.nacosspringcloudcommonentity.Scene;
import org.example.nacosspringcloudcommonentity.Task;
import org.example.scene.task.TaskJob;
import org.quartz.*;
import org.springframework.ui.Model;

/**
 * 定时任务工具类
 * 
 * @author ruoyi
 *
 */
public class ScheduleUtils {

        /**
         * 创建定时任务 定时任务创建之后默认启动状态
         * @param scheduler   调度器
         * @param task 定时任务信息类
         * @throws Exception
         */
        public static int createScheduleJob(Scheduler scheduler, Task task){

            try {
                // 构建定时任务信息
                JobDetail jobDetail = JobBuilder.newJob(TaskJob.class)
                                                .withIdentity(task.getTaskId().toString())
                                                .build();
                // 设置定时任务执行方式
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCronExpression());
                // 构建触发器trigger
                CronTrigger trigger = TriggerBuilder.newTrigger()
                                                    .withIdentity(task.getTaskId().toString())
                                                    .withSchedule(scheduleBuilder)
                                                    .build();
                scheduler.scheduleJob(jobDetail, trigger);
                return 1;
            } catch (SchedulerException e) {
                System.out.println("创建定时任务出错："+e.getMessage());
                return -1;
            }
        }

        /**
         * 根据任务名称暂停定时任务
         * @param scheduler  调度器
         * @param taskId    任务id
         * @throws SchedulerException
         */
        public static void pauseScheduleJob(Scheduler scheduler, Integer taskId){
            JobKey jobKey = JobKey.jobKey(taskId.toString());
            try {
                scheduler.pauseJob(jobKey);
            } catch (SchedulerException e) {
                System.out.println("暂停定时任务出错："+e.getMessage());
            }
        }

        /**
         * 根据任务名称恢复定时任务
         * @param scheduler  调度器
         * @param taskId    场景任务id
         * @throws SchedulerException
         */
        public static void resumeScheduleJob(Scheduler scheduler, Integer taskId) {
            JobKey jobKey = JobKey.jobKey(taskId.toString());
            try {
                scheduler.resumeJob(jobKey);
            } catch (SchedulerException e) {
                System.out.println("启动定时任务出错："+e.getMessage());
            }
        }

        /**
         * 根据任务名称立即运行一次定时任务
         * @param scheduler     调度器
         * @param taskId    场景任务id
         * @throws SchedulerException
         */
        public static void runOnce(Scheduler scheduler, Integer taskId){
            JobKey jobKey = JobKey.jobKey(taskId.toString());
            try {
                scheduler.triggerJob(jobKey);
            } catch (SchedulerException e) {
                System.out.println("运行定时任务出错："+e.getMessage());
            }
        }

        /**
         * 更新定时任务
         * @param scheduler   调度器
         * @param task  定时任务信息类
         * @throws SchedulerException
         */
        public static void updateScheduleJob(Scheduler scheduler, Task task)  {
            try {
                //获取到对应任务的触发器
                TriggerKey triggerKey = TriggerKey.triggerKey(task.getTaskId().toString());
                //设置定时任务执行方式
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCronExpression());
                //重新构建任务的触发器trigger
                CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                //重置对应的job
                scheduler.rescheduleJob(triggerKey, trigger);
            } catch (SchedulerException e) {
                System.out.println("更新定时任务出错："+e.getMessage());
            }
        }

        /**
         * 根据定时任务名称从调度器当中删除定时任务
         * @param scheduler 调度器
         * @param taskId   定时任务id
         * @throws SchedulerException
         */
        public static void deleteScheduleJob(Scheduler scheduler, Integer taskId) {
            JobKey jobKey = JobKey.jobKey(taskId.toString());
            try {
                scheduler.deleteJob(jobKey);
            } catch (SchedulerException e) {
                System.out.println("删除定时任务出错："+e.getMessage());
            }
        }

}
