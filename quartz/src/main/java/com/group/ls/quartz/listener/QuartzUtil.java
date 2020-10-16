package com.group.ls.quartz.listener;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Quartz定时任务工具类，包含添加定时任务、删除定时任务的操作。
 */
@Service
public class QuartzUtil {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 注入springboot自动配置的调度器
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * 添加定时任务
     * @param job
     * @param trigger
     */
    public void addJob(JobDetail job, Trigger trigger) {
        try {
            if (scheduler.checkExists(job.getKey())) {
                scheduler.deleteJob(job.getKey());
            }
            scheduler.scheduleJob(job, trigger);
            logger.info("add job: " + job.getKey());
        } catch (Exception e) {
            logger.error("", e);
        }
    }
    
    /**
     * 删除定时任务
     * @param jobName
     */
    public void deleteJob(String jobName) {
        try {
            JobKey jobKey = new JobKey(jobName);
            if (scheduler.checkExists(jobKey)) {
                scheduler.deleteJob(jobKey);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }
}