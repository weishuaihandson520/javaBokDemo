package com.sangeng.config;

import com.sangeng.quartz.SignJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定义任务描述和具体的执行时间
 */
@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail jobDetail() {
        //指定任务描述具体的实现类
        return JobBuilder.newJob(SignJob.class)
                // 指定任务的名称
                .withIdentity("signJob")
                // 任务描述
                .withDescription("任务描述：用于删除签到表当天的信息")
                // 每次任务执行后进行存储
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger() {
     String CRON_EXPRESSION_3SECONDS = "0 00 00 * * ? ";//每天凌晨0:00:00执行一次,?用于无指定日期

        //创建触发器
        return TriggerBuilder.newTrigger()
                // 绑定工作任务
                .forJob(jobDetail())
                // 每隔 5 秒执行一次 job
//                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                .withSchedule(CronScheduleBuilder.cronSchedule(CRON_EXPRESSION_3SECONDS))
                .build();
    }
}