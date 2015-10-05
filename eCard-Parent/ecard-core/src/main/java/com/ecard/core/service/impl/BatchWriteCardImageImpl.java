/*
 * BatchWriteCardImageImpl
 */
package com.ecard.core.service.impl;

import com.ecard.core.batch.contants.BatchConstants;
import com.ecard.core.batch.tasklet.BatchWriteCardImageTasklet;
import com.ecard.core.model.CardInfo;
import com.ecard.core.service.BatchWriteCardImage;
import java.util.List;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
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
 * @author vinhla
 */
@Service("batchWriteCardImage")
public class BatchWriteCardImageImpl implements BatchWriteCardImage{
    private static final Logger logger = LoggerFactory.getLogger(BatchWriteCardImageImpl.class);
    
    public void writeCardImage(List<CardInfo> cardInfoList) throws SchedulerException{
        String[] springConfig = {"classpath:batch/batch-write-card-image.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

        //get the quartzFactory bean
        Scheduler scheduler = (Scheduler) context.getBean("scheduler");

        //get the task bean

        BatchWriteCardImageTasklet myTask = (BatchWriteCardImageTasklet) context.getBean(BatchConstants.BATCH_WRITE_CARD_IMAGE);
        try {            
            // create JOB
            MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
            jobDetail.setTargetObject(myTask);
            jobDetail.setTargetMethod("performAction");
            //jobDetail.setTargetObject(cardInfoList);
            jobDetail.setArguments(new Object[]{cardInfoList});
            jobDetail.setName("jobDetailWriteCardImage");
            jobDetail.setConcurrent(false);
            jobDetail.afterPropertiesSet();

            String myGroup = "batchWriteCardImage";
            String name = "batchWriteCardImageTrigger";
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
            if(scheduler.isStarted())
                scheduler.shutdown();
        }
    }
}
