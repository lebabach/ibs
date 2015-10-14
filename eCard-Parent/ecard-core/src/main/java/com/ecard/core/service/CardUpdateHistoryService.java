/*
 * CardInfoService class
 */
package com.ecard.core.service;

import com.ecard.core.model.CardInfo;
import com.ecard.core.model.CardUpdateHistory;
import com.ecard.core.vo.CardUpdateHisAndUserInfo;
import java.util.List;

/**
 *
 * @author vinhla
 */
public interface CardUpdateHistoryService {
   
   public List<CardInfo> listAllCardHistory(String sort);
   
   public List<CardUpdateHisAndUserInfo> getListCardUpdateHistory(Integer cardId);
   
   public void registerCardUpdateHistory(CardUpdateHistory cardUpdateHis);
}
