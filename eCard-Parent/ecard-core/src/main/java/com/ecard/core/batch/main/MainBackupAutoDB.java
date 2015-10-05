package com.ecard.core.batch.main;

import com.ecard.core.batch.tasklet.BatchBackupDBAutoTasklet;
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

public class MainBackupAutoDB {
    private static final Logger logger = LoggerFactory.getLogger(BatchBackupDBAutoTasklet.class);
    @SuppressWarnings("resource")
    public static void main(String areg[]){
        String[] springConfig = {"classpath:batch/batch-backup-auto-database.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

        //get the quartzFactory bean
        Scheduler scheduler = (Scheduler) context.getBean("scheduler");

        //get the task bean
        BatchBackupDBAutoTasklet myTask = (BatchBackupDBAutoTasklet) context.getBean("myTask");

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
            e.printStackTrace();
        } 
    }

}
