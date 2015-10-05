/*
 * CardInfoServiceImpl class
 */
package com.ecard.core.service.impl;

import com.ecard.core.dao.MyCardDAO;
import com.ecard.core.model.CardInfo;
import com.ecard.core.service.MyCardService;
import com.ecard.core.vo.MyCardAndCardInfo;
import com.ecard.core.vo.MyCardAndUserInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vinhla
 */
@Service("myCardService")
@Transactional
public class MyCardServiceImpl implements MyCardService{
    
    @Autowired
    MyCardDAO myCardDAO;
    
    public List<com.ecard.core.vo.CardInfo> listCardRecent(Integer userId) {
        return myCardDAO.listCardRecent(userId);
    }
    
    public List<MyCardAndCardInfo> listAllMyCard(Integer userId) {
        return myCardDAO.listAllMyCard(userId);
    }
    
    public int updateMyCardSeq(Integer cardId, Integer seq) {
        return myCardDAO.updateMyCardSeq(cardId, seq);
    }
    
    public void registerMyCard(CardInfo cardInfo){
        myCardDAO.registerMyCard(cardInfo);
    }
    
    public int updateCardIdForPosCard(Integer cardId){
        return myCardDAO.updateCardIdForPosCard(cardId);
    }
    
    public int updateCardIdForPrusalHis(Integer cardId){
        return myCardDAO.updateCardIdForPrusalHis(cardId);
    }

    public int updateCardIdForCardTag(Integer cardId){
        return myCardDAO.updateCardIdForCardTag(cardId);
    }
    
    public int updateOldCardFlg(Integer cardId){
        return myCardDAO.updateOldCardFlg(cardId);
    }
    
    public int updateNewCardFlg(Integer cardId)
    {
        return myCardDAO.updateNewCardFlg(cardId);
    }
    
    public List<MyCardAndUserInfo> getListMyCardById(Integer userId, Integer cardId){
        return myCardDAO.getListMyCardById(userId, cardId);
    }
    
    public int deleteMyCard(Integer userId, Integer cardId){
        return myCardDAO.deleteMyCard(userId, cardId);
    }
}
