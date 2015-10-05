/*
 * MyCardService class
 */
package com.ecard.core.service;

import com.ecard.core.model.CardInfo;
import com.ecard.core.vo.MyCardAndCardInfo;
import com.ecard.core.vo.MyCardAndUserInfo;
import java.util.List;

/**
 *
 * @author vinhla
 */
public interface MyCardService {
   public List<com.ecard.core.vo.CardInfo> listCardRecent(Integer userId); 
   
   public List<MyCardAndCardInfo> listAllMyCard(Integer userId); 
   
   public int updateMyCardSeq(Integer cardId, Integer seq);
 
   public void registerMyCard(CardInfo cardInfo);
   
   public int updateCardIdForPosCard(Integer cardId);
    
    public int updateCardIdForPrusalHis(Integer cardId);

    public int updateCardIdForCardTag(Integer cardId);
    
    public int updateOldCardFlg(Integer cardId);
    
    public int updateNewCardFlg(Integer cardId);
    
    public List<MyCardAndUserInfo> getListMyCardById(Integer userId, Integer cardId);
    
    public int deleteMyCard(Integer userId, Integer cardId);
}
