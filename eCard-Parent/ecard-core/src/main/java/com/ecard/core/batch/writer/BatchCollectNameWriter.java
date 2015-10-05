/*
 * BatchCollectNameWriter
 */
package com.ecard.core.batch.writer;

import com.ecard.core.batch.beans.CardInfo;
import java.util.List;
import org.springframework.batch.item.ItemWriter;

/**
 *
 * @author vinhla
 */
public class BatchCollectNameWriter implements ItemWriter<CardInfo> {
    
    public void write(List<? extends CardInfo> cardInfoList) throws Exception {
        System.out.println("Call BatchCollectNameWriter....... => "+cardInfoList.size());
        for(CardInfo cardInfo : cardInfoList){
            try{
                System.out.println("cardId : " + cardInfo.getCardId() + "email : "+ cardInfo.getEmail());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
