package com.bruce.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by bruce on 2018/5/8.
 */
public class HelloQuartzTest {
    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            JobDetail helloJob = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("helloJob")
                    .usingJobData("name","bruce")
                    .build();

            JobDetail newJob = JobBuilder.newJob(NewJob.class)
                    .withIdentity("newJob")
                    .usingJobData("name","bruce")
                    .build();

            scheduler.start();

            jobWithSimpleTriger(helloJob,scheduler);
            jobWithCronExpressionTrigger(newJob,scheduler);
            Thread.sleep(10000);

            scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void jobWithSimpleTriger(JobDetail jobDetail, Scheduler scheduler) throws SchedulerException {
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simpleTrigger")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .build();
        scheduler.scheduleJob(jobDetail,trigger);
    }

    public static void jobWithCronExpressionTrigger(JobDetail jobDetail,Scheduler scheduler) throws SchedulerException {
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("cronTrigger").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
        scheduler.scheduleJob(jobDetail,trigger);
    }
}
