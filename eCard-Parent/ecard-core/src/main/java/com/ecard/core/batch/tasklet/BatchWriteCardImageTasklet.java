/*
 * BatchWriteCardImageTasklet
 */
package com.ecard.core.batch.tasklet;

import com.ecard.core.batch.util.WriteCardImage;
import com.ecard.core.model.CardInfo;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vinhla
 */
public class BatchWriteCardImageTasklet {
    private static final Logger logger = LoggerFactory.getLogger(BatchWriteCardImageTasklet.class);
    
    public void performAction(List<CardInfo> cardInfoList) {
        try{
            WriteCardImage cardImage = new WriteCardImage();
            cardImage.writeCardImage(cardInfoList);
        }
        catch(Exception ex){
            logger.debug("Exception ", ex.getMessage());
        }
    }
}
