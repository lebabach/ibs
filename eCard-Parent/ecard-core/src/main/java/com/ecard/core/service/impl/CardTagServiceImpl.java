/*
 * CardTagDAOServiceImpl
 */
package com.ecard.core.service.impl;

import com.ecard.core.dao.CardTagDAO;
import com.ecard.core.model.CardTagId;
import com.ecard.core.service.CardTagService;
import com.ecard.core.vo.CardTagAndCompany;
import com.ecard.core.vo.TagForCard;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vinhla
 */
@Service("cardTagService")
@Transactional
public class CardTagServiceImpl implements CardTagService{
    @Autowired
    CardTagDAO cardTagDAO;
    
    public void addCardTag(CardTagId cardTag) {
        cardTagDAO.addCardTag(cardTag);
    }
    
    public List<TagForCard> listCardTagByCardId(Integer userId) {
        return cardTagDAO.listCardTagByCardId(userId);
    }
    
    public CardTagAndCompany listCardWhereTag(Integer tagId) {
        return cardTagDAO.listCardWhereTag(tagId);
    }
    
    public int deleteCardTag(Integer cardId, Integer tagId){
        return cardTagDAO.deleteCardTag(cardId, tagId);
    }
    
    public int deleteCardTagByTagId(Integer tagId) {
        return cardTagDAO.deleteCardTagByTagId(tagId);
    }
    
    public int registerCardTag(Integer cardId, Integer tagId){
        return cardTagDAO.registerCardTag(cardId, tagId);
    }
    
    public List<TagForCard> listUserTagByUserId(Integer userId){
        return cardTagDAO.listUserTagByUserId(userId);
    }
    
    public List<Integer> listCardIdByTagId(Integer tagId){
        return cardTagDAO.listCardIdByTagId(tagId);
    }
}
