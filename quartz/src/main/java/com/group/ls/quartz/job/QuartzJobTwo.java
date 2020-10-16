package com.group.ls.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

//持久化
@PersistJobDataAfterExecution
//禁止并发执行
@DisallowConcurrentExecution
public class QuartzJobTwo extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) {
        System.out.println("QuartzJobTwo" + context.getJobDetail().getKey());
    }
}
