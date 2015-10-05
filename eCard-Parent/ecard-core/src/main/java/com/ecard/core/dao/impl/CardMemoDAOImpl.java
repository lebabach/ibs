/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.dao.impl;

import com.ecard.core.dao.CardMemoDAO;
import com.ecard.core.model.CardInfo;
import com.ecard.core.model.UserCardMemo;
import com.ecard.core.model.UserCardMemoId;
import com.ecard.core.model.UserInfo;
import com.ecard.core.vo.CardInfoMemo;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HienTu
 */


@Repository("cardMemoDAO")
public class CardMemoDAOImpl extends GenericDao implements CardMemoDAO {
    public List<CardInfoMemo> getMemoCard(Integer cardId){
    Query query = getEntityManager().createQuery("SELECT new com.ecard.core.vo.CardInfoMemo(m.id.userId, m.id.memo, m.id.seq, m.id.createDate) FROM UserCardMemo m "
            + "WHERE m.id.cardId = :cardId "
            + "ORDER BY m.id.seq");
    query.setParameter("cardId", cardId);
    return query.getResultList();
    }
    
    public Integer registerCardMemo(UserCardMemoId cardMemo){
       Query query = getEntityManager().createQuery("UPDATE UserCardMemo m "
               + " SET  m.id.memo= :memo"
               + " WHERE m.id.cardId = :cardId AND  m.id.seq = :seq");
       query.setParameter("memo", cardMemo.getMemo());
       query.setParameter("cardId", cardMemo.getCardId());
       query.setParameter("seq", cardMemo.getSeq());       
       int result = query.executeUpdate();
       return result;
    }
    
    public void createCardMemo(UserCardMemoId cardMemo){       
        UserCardMemo userCardMemo = new UserCardMemo();
        UserInfo userInfo = new UserInfo();
        CardInfo cardInfo = new CardInfo();
        userInfo.setUserId(cardMemo.getUserId());
        cardInfo.setCardId(cardMemo.getCardId());
        
        userCardMemo.setId(cardMemo);
        userCardMemo.setCardInfo(cardInfo);
        userCardMemo.setUserInfo(userInfo);
        
        getEntityManager().persist(userCardMemo);
        getEntityManager().flush();
    }
    public void deleteCardMemo(UserCardMemoId cardMemo){
       Query query = getEntityManager().createQuery("DELETE FROM UserCardMemo m "
               + " WHERE m.id.cardId = :cardId AND  m.id.seq = :seq");       
       query.setParameter("cardId", cardMemo.getCardId());
       query.setParameter("seq", cardMemo.getSeq());       
       query.executeUpdate();
    } 
}
