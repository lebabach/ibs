/*
 * BatchCollectNameTaskScheduler
 */
package com.ecard.core.batch.tasklet.scheduler;

import java.util.Map;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 *
 * @author vinhla
 */
public class BatchLinkCardTaskScheduler extends QuartzJobBean {
    private static final Logger LOG = LoggerFactory.getLogger(BatchLinkCardTaskScheduler.class);
    
    private String jobName;
    private JobLauncher jobLauncher;
    private JobLocator jobLocator;

    public JobLauncher getJobLauncher() {
            return jobLauncher;
    }
    public void setJobLauncher(JobLauncher jobLauncher) {
            this.jobLauncher = jobLauncher;
    }
    public JobLocator getJobLocator() {
            return jobLocator;
    }
    public void setJobLocator(JobLocator jobLocator) {
            this.jobLocator = jobLocator;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        LOG.debug("executeInternal : ", BatchLinkCardTaskScheduler.class);
        
        Map<String,Object> mapData = context.getMergedJobDataMap();

        jobName = (String) mapData.get("jobName");

        try{			
            //JobParametersBuilder parameters = new JobParametersBuilder();
            //parameters.addString(BatchConstants.EXPIRATION_DATE, formatter.format(expirationDate));
            //JobParameters jobParameters = parameters.toJobParameters();

            //LOG.debug("Launching job with parameters {}", jobParameters);

            JobExecution execution = jobLauncher.run(jobLocator.getJob(jobName), new JobParameters());
            System.out.println("Execution Status: "+ execution.getStatus());
            LOG.debug("Execution Status : ", execution.getStatus());
        }catch(Exception e){
            LOG.debug("Encountered job execution exception : ", e.getMessage());
        }
    }
}
