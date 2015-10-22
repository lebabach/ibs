package com.ecard.core.batch.tasklet;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

import com.ecard.core.batch.util.UploadLostImageUtil;

public class BatchUploadLostImageTasklet implements Tasklet {
	private static final Logger logger = LoggerFactory.getLogger(BatchUploadLostImageTasklet.class);
    
    private DataSource dataSource;
    
    public DataSource getDataSource() {
        return dataSource;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Value("${scp.hostname}")
    private String scpHostName;
    
    @Value("${scp.user}")
    private String scpUser;
    
    @Value("${scp.password}")
    private String scpPassword;
    
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.debug("execute method", BatchUploadLostImageTasklet.class);
        
        try{
        	UploadLostImageUtil uploadLostImageUtil = new UploadLostImageUtil(scpHostName, scpUser, scpPassword);
        	uploadLostImageUtil.uploadLostImageFile();
        }
        catch(Exception ex){
            logger.debug("Exception : ", ex.getMessage());
        }

        return RepeatStatus.FINISHED;
    }
}
