/*
 * CardTagDAOService
 */
package com.ecard.core.service;

import com.ecard.core.model.CardTagId;
import com.ecard.core.vo.CardTagAndCompany;
import com.ecard.core.vo.TagForCard;
import java.util.List;

/**
 *
 * @author vinhla
 */
public interface CardTagService {
    public void addCardTag(CardTagId cardTag);
    
    public List<TagForCard> listCardTagByCardId(Integer userId);
    
    public CardTagAndCompany listCardWhereTag(Integer tagId);
    
    public int deleteCardTag(Integer cardId, Integer tagId);
    
    public int deleteCardTagByTagId(Integer tagId);
    
    public int registerCardTag(Integer cardId, Integer tagId);
    
    public List<TagForCard> listUserTagByUserId(Integer userId);
    
    public List<Integer> listCardIdByTagId(Integer tagId);
}
