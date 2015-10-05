/*
 * CardTagDAO
 */
package com.ecard.core.dao;

import com.ecard.core.model.CardTagId;
import com.ecard.core.vo.CardTagAndCompany;
import com.ecard.core.vo.TagForCard;
import java.util.List;

/**
 *
 * @author vinhla
 */
public interface CardTagDAO {
    void addCardTag(CardTagId cardTag);
    
    List<TagForCard> listCardTagByCardId(Integer userId);
    
    CardTagAndCompany listCardWhereTag(Integer tagId);
    
    public int deleteCardTag(Integer cardId, Integer tagId);
    
    public int deleteCardTagByTagId(Integer tagId);
    
    public int registerCardTag(Integer cardId, Integer tagId);
    
    List<TagForCard> listUserTagByUserId(Integer userId);
    
    public List<Integer> listCardIdByTagId(Integer tagId);
}
