/*
 * BatchLinkCardTasklet
 */
package com.ecard.core.batch.tasklet;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecard.core.model.UserInfo;
import com.ecard.core.service.LinkCardService;
import com.ecard.core.service.UserInfoService;

/**
 *
 * @author vinhla
 */
public class BatchLinkCardTasklet implements Tasklet {
    private static final Logger logger = LoggerFactory.getLogger(BatchLinkCardTasklet.class);
    
    private DataSource dataSource;
    
    public DataSource getDataSource() {
        return dataSource;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Autowired
	UserInfoService userInfoService;
    
    @Autowired
    LinkCardService linkCardService;
    
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.debug("execute method", BatchLinkCardTasklet.class);
        
        try{
        	List<UserInfo> userInfoList = userInfoService.getAllListUser();
        	System.out.println("userInfoList : " + userInfoList);
        	
        	//Clean all link card data
        	System.out.println("======= CLEAN LINK CARD DATA =========");
        	linkCardService.cleanLinkCardData();
        	System.out.println("======= END CLEAN LINK CARD DATA =========");
        	
        	linkCardService.saveLinkCard(userInfoList);
        }
        catch(Exception ex){
            logger.debug("Exception : ", ex.getMessage());
        }

        return RepeatStatus.FINISHED;
    }
    
}
