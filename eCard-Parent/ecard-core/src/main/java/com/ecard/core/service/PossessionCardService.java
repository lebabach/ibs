/*
 * PossessionCardService
 */
package com.ecard.core.service;

import com.ecard.core.model.PossessionCard;
import com.ecard.core.vo.ImportCardInfoCsv;
import com.ecard.core.vo.PosessionCardInfo;
import java.util.List;

/**
 *
 * @author vinhla
 */
public interface PossessionCardService {
    
    public void registerPosCard(PossessionCard possessionCard);
    
    public List<PossessionCard> listPossesionCard(String searchText, String sort);
    
    public List<PosessionCardInfo> listCardConnect(Integer userId, Integer cardId);
    
    public List<PossessionCard> getListPossessionCardById(Integer userId, Integer cardId);
    
    public int deletePossessionCard(Integer userId, Integer cardId);
    
    public String getUserEmailByCardId(Integer cardId);
    
    public Integer getUserIdByCardId(Integer cardId);
    
    public int importCardInfoCsv(List<ImportCardInfoCsv> importCardInfoCsvList);
}
