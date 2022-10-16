package com.sangeng.quartz;

import com.sangeng.mapper.SignDayMapper;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;


/**
 * 定义任务:在凌晨0点将签到表的信息删除
 */
public class SignJob extends QuartzJobBean {

    @Autowired
    private SignDayMapper signDayMapper;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("唐玮任务"+new Date());

        //清空当天签到的表
       signDayMapper.delete(null);
    }
}
