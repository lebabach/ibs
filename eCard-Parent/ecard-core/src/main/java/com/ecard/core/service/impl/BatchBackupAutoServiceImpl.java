/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.service.impl;

import com.ecard.core.batch.contants.BatchConstants;
import com.ecard.core.batch.tasklet.BatchBackupDBAutoTasklet;
import com.ecard.core.batch.tasklet.BatchBackupDBManualTasklet;
import com.ecard.core.service.BatchBackupAutoService;
import java.text.Format;
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
import org.springframework.stereotype.Service;

/**
 *
 * @author Windows
 */
@Service("batchCollectNameService")
public class BatchBackupAutoServiceImpl implements BatchBackupAutoService{
    private static final Logger logger = LoggerFactory.getLogger(BatchBackupDBAutoTasklet.class);
    
    public void backupAutoDatabase(String date){
//        Path currentRelativePath = Paths.get("");
//        String currentPath = currentRelativePath.toAbsolutePath().toString();
//        String saveFolderAuto = BatchConstants.saveAutoFolder;
        
        String[] springConfig = {"classpath:batch/batch-backup-auto-database.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

        //get the quartzFactory bean
        Scheduler scheduler = (Scheduler) context.getBean("scheduler");

        //get the task bean
        BatchBackupDBAutoTasklet myTask = (BatchBackupDBAutoTasklet) context.getBean(BatchConstants.BATCH_BACKUP_DB_AUTO);


        //Get current day for run batch
        String hour = "";
        String minute = "";
        Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
        String dateFormat = formatter.format(new Date());
        //System.out.println(dateFormat);
        String[] dateBatchSplit = dateFormat.split("-");
        String day = dateBatchSplit[0];
        String month = dateBatchSplit[1];
        String year = dateBatchSplit[2];
        
        try {
            if (date.contains(":")) {
                String[] dateSplit = date.split(":");
                hour = dateSplit[0];
                minute = dateSplit[1];
            } else {
                throw new IllegalArgumentException("Date does not contain : ");
            }
            
            if(Integer.parseInt(minute) <= 10){
                minute = minute.replace("0", "");
            }
            
//            File file = new File(saveFolderAuto);
//            
//            if(!file.exists())
//                file.mkdirs();
            
            // create JOB
            MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
            jobDetail.setTargetObject(myTask);
            jobDetail.setTargetMethod("performAction");
            jobDetail.setArguments(new String[]{date, BatchConstants.saveAutoFolder});
            jobDetail.setName("jobDetailAutoBackup");
            jobDetail.setConcurrent(false);
            jobDetail.afterPropertiesSet();

            String myGroup = "backupDB";
            String name = "backupTrigger";
            //String expresion = "0/3 * * * * ?"; //for test
            String expresion = "0 0/"+ minute + " " + hour +" "+ day +" "+ month +" ? "+ year; // Run at HOURS MINUTE DAY-MONTH-YEAR

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
    
    public void backupManualDatabase(){
//        Path currentRelativePath = Paths.get("");
//        String currentPath = currentRelativePath.toAbsolutePath().toString();
//        String saveFolderManual = BatchConstants.saveManualFolder;
        
        String[] springConfig = {"classpath:batch/batch-backup-manual-database.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

        //get the quartzFactory bean
        Scheduler scheduler = (Scheduler) context.getBean("scheduler");

        //get the task bean
        BatchBackupDBManualTasklet myTask = (BatchBackupDBManualTasklet) context.getBean(BatchConstants.BATCH_BACKUP_DB_MANUAL);

        try {
//            File file = new File(saveFolderManual);
//            
//            if(!file.exists())
//                file.mkdirs();
            
            // create JOB
            MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
            jobDetail.setTargetObject(myTask);
            jobDetail.setTargetMethod("performAction");
            jobDetail.setArguments(new String[]{BatchConstants.saveManualFolder});
            jobDetail.setName("jobDetailBackup");
            jobDetail.setConcurrent(false);
            jobDetail.afterPropertiesSet();

            String myGroup = "backupDBManual";
            String name = "backupManualTrigger";
            //String expresion = "0/3 * * * * ?";

            Trigger trigger = TriggerBuilder
                            .newTrigger()
                            .withIdentity(name, myGroup)
                            //.withSchedule(CronScheduleBuilder.cronSchedule(expresion))
                            .build();

            scheduler.scheduleJob((JobDetail) jobDetail.getObject(), trigger);

            // Start Scheduler        
            scheduler.start();

        } catch (Exception e) {                      
            logger.debug("Exception ", e.getMessage());
        } 
    }
}
