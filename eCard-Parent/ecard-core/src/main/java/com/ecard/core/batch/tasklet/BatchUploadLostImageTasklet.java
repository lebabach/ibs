package com.ecard.core.batch.tasklet;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

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
    
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.debug("execute method", BatchUploadLostImageTasklet.class);
        
        try{
        	UploadLostImageUtil uploadLostImageUtil = new UploadLostImageUtil();
        	uploadLostImageUtil.uploadLostImageFile();
        }
        catch(Exception ex){
            logger.debug("Exception : ", ex.getMessage());
        }

        return RepeatStatus.FINISHED;
    }
}
