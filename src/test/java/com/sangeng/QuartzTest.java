//package com.sangeng;
//
//
//import org.junit.jupiter.api.Test;
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class QuartzTest {
//
//    @Test
//    public  void test1(){
//        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
//                .withIdentity("job1","group1")
//                .build();
//
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("trigger1","trigger1")
//                //设置触发器的启动时间
//                .startNow()
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                        .withIntervalInSeconds(1)
//                        .repeatForever())
//                       .build();
//
//        //调度器
//        try {
//            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//            //将jobDetail和trigger放到调度器中
//            scheduler.scheduleJob(jobDetail,trigger);
//            scheduler.start();
//        } catch (SchedulerException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
