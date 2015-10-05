/*
 * BatchRestoreServiceImpl
 */
package com.ecard.core.service.impl;

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

import com.ecard.core.batch.contants.BatchConstants;
import com.ecard.core.batch.tasklet.BatchBackupDBAutoTasklet;
import com.ecard.core.batch.tasklet.BatchRestoreDBManualTasklet;
import com.ecard.core.service.BatchRestoreService;

/**
 *
 * @author vinhla
 */
@Service("batchRestoreService")
public class BatchRestoreServiceImpl implements BatchRestoreService{
    private static final Logger logger = LoggerFactory.getLogger(BatchBackupDBAutoTasklet.class);
    
    public void restoreDB(){
        String[] springConfig = {"classpath:batch/batch-restore-manual-database.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

        //get the quartzFactory bean
        Scheduler scheduler = (Scheduler) context.getBean("scheduler");

        //get the task bean
        BatchRestoreDBManualTasklet myTask = (BatchRestoreDBManualTasklet) context.getBean(BatchConstants.BATCH_BACKUP_RESTORE_DB);

        try {
            //File file = new File(filePath);
            
            //if(!file.exists())
                //throw new FileNotFoundException();
            
            // create JOB
            MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
            jobDetail.setTargetObject(myTask);
            jobDetail.setTargetMethod("performAction");
            //jobDetail.setArguments(new String[]{file.getAbsolutePath()});
            jobDetail.setName("jobDetailRestore");
            jobDetail.setConcurrent(false);
            jobDetail.afterPropertiesSet();

            String myGroup = "restoreDBManual";
            String name = "restoreManualTrigger";
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
