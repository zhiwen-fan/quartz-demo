package com.bruce.quartz;

import org.quartz.*;

/**
 * Created by bruce on 2018/5/8.
 */
public class HelloJob implements Job{
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        System.out.println(jobDataMap.toString());

        System.out.println("say hello to : " + jobDataMap.get("name"));
    }
}
