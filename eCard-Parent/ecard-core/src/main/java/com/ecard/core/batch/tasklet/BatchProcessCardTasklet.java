package com.ecard.core.batch.tasklet;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecard.core.batch.util.WriteCardImage;
import com.ecard.core.model.CardInfo;
import com.ecard.core.service.CardInfoService;

public class BatchProcessCardTasklet implements Tasklet{
	private static final Logger logger = LoggerFactory.getLogger(BatchProcessCardTasklet.class);
    
    private DataSource dataSource;
    
    public DataSource getDataSource() {
        return dataSource;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Autowired
    CardInfoService cardInfoService;
    
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    	logger.debug("execute method", BatchProcessCardTasklet.class);
    	try{
    		List<CardInfo> cardInfoList = cardInfoService.listCardInfoByCardType(1);
    		
    		WriteCardImage cardImage = new WriteCardImage();
            cardImage.writeCardImage(cardInfoList);
            
            //Update card_type = 0
            cardInfoService.updateCardType();
    	}
    	catch(Exception ex){
            logger.debug("Exception : ", ex.getMessage());
        }

        return RepeatStatus.FINISHED;
    }
}
