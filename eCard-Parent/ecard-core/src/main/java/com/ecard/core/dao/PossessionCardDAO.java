/*
 * PossessionCardDAO
 */
package com.ecard.core.dao;

import com.ecard.core.model.PossessionCard;
import com.ecard.core.vo.PosessionCardInfo;
import java.util.List;

/**
 *
 * @author vinhla
 */
public interface PossessionCardDAO{
    
    public void registerPosCard(PossessionCard possessionCard);
    
    List<PossessionCard> listPossesionCard(String searchText, String sort);
    
    List<PosessionCardInfo> listCardConnect(Integer userId, Integer cardId);
    
    List<PossessionCard> getListPossessionCardById(Integer userId, Integer cardId);
    
    public int deletePossessionCard(Integer userId, Integer cardId);
    
    public String getUserEmailByCardId(Integer cardId);
    
    public Integer getUserIdByCardId(Integer cardId);
}
