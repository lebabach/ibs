/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.batch.writer;

import com.ecard.core.batch.tasklet.BatchBackupDBAutoTasklet;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

/**
 *
 * @author vinhla
 */
public class BatchBackupAutoWriter {
    private static final Logger logger = LoggerFactory.getLogger(BatchBackupDBAutoTasklet.class);
    
    private static void backupAutoDatabase(Date date){
        String[] springConfig = {"classpath:batch/batch-backup-auto-database.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

        //get the quartzFactory bean
        Scheduler scheduler = (Scheduler) context.getBean("scheduler");

        //get the task bean
        BatchBackupDBAutoTasklet myTask = (BatchBackupDBAutoTasklet) context.getBean("myTask");

        String DATE_FORMAT = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {
            // create JOB
            MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
            jobDetail.setTargetObject(myTask);
            jobDetail.setTargetMethod("performAction");
            jobDetail.setName("jobDetailAutoBackup");
            jobDetail.setConcurrent(false);
            jobDetail.afterPropertiesSet();

            String myGroup = "backupDB";
            String name = "backupTrigger";
            String expresion = "0/3 * * * * ?";

            Trigger trigger = TriggerBuilder
                            .newTrigger()
                            .withIdentity(name, myGroup)
                            .withSchedule(CronScheduleBuilder.cronSchedule(expresion))
                            .build();

            scheduler.scheduleJob((JobDetail) jobDetail.getObject(), trigger);

            // Start Scheduler        
            scheduler.start();

        } catch (Exception e) {                      
            logger.debug("Exception ", e.getMessage());
        } 
    }
}
