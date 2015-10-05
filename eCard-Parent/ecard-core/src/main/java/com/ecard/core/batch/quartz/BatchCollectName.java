/*
 * BatchCollectName
 */
package com.ecard.core.batch.quartz;

import com.ecard.core.dao.CardInfoDAO;
import com.ecard.core.model.CardInfo;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author vinhla
 */
public class BatchCollectName {
    
    @Autowired
    private CardInfoDAO cardInfoDAO;
    
    public void startBatch()
    {
        System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
//        List<CardInfo> cardList = cardInfoDAO.getListPossesionCardRecent(2);
//        for(CardInfo cardInfo : cardList) {
//            System.out.println("email : " + cardInfo.getEmail());
//        }
    }
}
