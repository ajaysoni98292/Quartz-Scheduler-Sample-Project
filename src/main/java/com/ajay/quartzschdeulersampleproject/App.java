package com.ajay.quartzschdeulersampleproject;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author ajay
 *
 */
class App implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("============testing ============");
        /**
         * write code whatever you want to do. This behave as a Job class for Quartz Scheduler
         */
        System.out.println("========== Quartz Scheduler Job class =======");

    }
}
