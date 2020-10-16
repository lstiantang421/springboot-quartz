package com.group.ls.quartz;

import com.group.ls.quartz.job.QuartzJobOne;
import com.group.ls.quartz.job.QuartzJobTwo;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfigure {

    @Bean
    public JobDetail oneTaskDetail() {
        return JobBuilder.newJob(QuartzJobOne.class).withIdentity("oneTaskDetail").storeDurably().build();
    }

    @Bean
    public Trigger oneTaskTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
        return TriggerBuilder.newTrigger().withIdentity("oneTaskTrigger").forJob(oneTaskDetail())
                .withSchedule(scheduleBuilder)
                .build();
    }


    @Bean
    public JobDetail twoTaskDetail() {
        return JobBuilder.newJob(QuartzJobTwo.class).withIdentity("twoTaskDetail").storeDurably().build();
    }

    @Bean
    public Trigger twoTaskTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
        return TriggerBuilder.newTrigger().withIdentity("twoTaskTrigger1").forJob(twoTaskDetail())
                .withSchedule(scheduleBuilder)
                .build();
    }
}
