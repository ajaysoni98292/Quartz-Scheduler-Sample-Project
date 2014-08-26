package com.ajay.quartzschdeulersampleproject;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


/**
 * Created by ajay on 26/8/14.
 */
public class QuartzScheduler {


    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzScheduler.class);

    public static void main(String args[]) {

        try {
//            LoggerContext context = (LoggerContext)
//                    LoggerFactory.getILoggerFactory();
//
//            if (args.length > 0) {
//
//                final String log4jFilePath = args[0];
//                configureLogger(context, log4jFilePath);
//
//            }


            // Grab the Scheduler instance from the Factory
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler sched = sf.getScheduler();

// define the job and tie it to our HelloJob class
            JobDetail job = newJob(App.class)
                    .withIdentity("job1", "group1")
                    .build();
            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(40)
                            .repeatForever())
                    .build();

// Tell quartz to schedule the job using our trigger
            sched.scheduleJob(job, trigger);

            sched.start();
        } catch (SchedulerException se) {
            LOGGER.info("Exception occurred in starting the scheduler", se);
        } catch (Exception e) {
            LOGGER.info("Exception occurred in starting the scheduler", e);
        }

    }


    private static void configureLogger(final LoggerContext context, final String log4jFilePath) throws Exception {
        final File log4jFile = new File(log4jFilePath);

        if (!log4jFile.exists()) {
            throw new Exception("** " + log4jFilePath + " is not a valid path");
        }
        try {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            context.reset();

            configurator.doConfigure(log4jFile);

        } catch (JoranException e) {
            throw new Exception(e);
        }
    }

}
