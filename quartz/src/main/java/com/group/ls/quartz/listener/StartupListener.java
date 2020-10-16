package com.group.ls.quartz.listener;

import com.group.ls.quartz.job.QuartzJobTwo;
import com.group.ls.quartz.job.QuartzJobOne;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 初始化方法，添加了一个定时任务
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            ApplicationContext ctx = event.getApplicationContext();
            
            // 添加一个定时任务
            QuartzUtil qu = ctx.getBean(QuartzUtil.class);
            JobKey jobKeyOne = new JobKey("testJobOne");
            JobDetail jobDetailOne = JobBuilder.newJob(QuartzJobOne.class).withIdentity(jobKeyOne).build();
            Trigger triggerOne = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            qu.addJob(jobDetailOne, triggerOne);

            JobKey jobKeyTwo = new JobKey("testJobTwo");
            JobDetail jobDetailTwo = JobBuilder.newJob(QuartzJobTwo.class).withIdentity(jobKeyTwo).build();
            Trigger triggerTwo = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            qu.addJob(jobDetailTwo, triggerTwo);
        } catch (Exception e) {
            logger.error("", e);
        }
    }
}