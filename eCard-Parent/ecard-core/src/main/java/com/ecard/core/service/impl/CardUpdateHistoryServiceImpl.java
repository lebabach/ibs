/*
 * CardInfoServiceImpl class
 */
package com.ecard.core.service.impl;

import com.ecard.core.dao.CardUpdateHistoryDAO;
import com.ecard.core.model.CardInfo;
import com.ecard.core.service.CardUpdateHistoryService;
import com.ecard.core.vo.CardUpdateHisAndUserInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vinhla
 */
@Service("cardUpdateHistoryService")
@Transactional
public class CardUpdateHistoryServiceImpl implements CardUpdateHistoryService{
    
    @Autowired
    CardUpdateHistoryDAO cardUpdateHistoryDAO;
    
    public List<CardInfo> listAllCardHistory(String sort){
        return cardUpdateHistoryDAO.listAllCardHistory(sort);
    }
    
    public List<CardUpdateHisAndUserInfo> getListCardUpdateHistory(Integer cardId){
        return cardUpdateHistoryDAO.getListCardUpdateHistory(cardId);
    }
}
