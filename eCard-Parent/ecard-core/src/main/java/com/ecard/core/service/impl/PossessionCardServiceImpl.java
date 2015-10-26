/*
 * PossessionCardServiceImpl
 */
package com.ecard.core.service.impl;

import com.ecard.core.dao.CardInfoDAO;
import com.ecard.core.dao.PossessionCardDAO;
import com.ecard.core.model.PossessionCard;
import com.ecard.core.service.PossessionCardService;
import com.ecard.core.vo.ImportCardInfoCsv;
import com.ecard.core.vo.PosessionCardInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vinhla
 */
@Service("possessionCardService")
@Transactional
public class PossessionCardServiceImpl implements PossessionCardService{
    @Autowired
    private PossessionCardDAO posCardDAO;
    
    @Autowired
    private CardInfoDAO cardInfoDAO;
    
    public void registerPosCard(PossessionCard possessionCard) {
        posCardDAO.registerPosCard(possessionCard);
    }
    
    public List<PossessionCard> listPossesionCard(String searchText, String sort) {
        return posCardDAO.listPossesionCard(searchText, sort);
    }
    
    public List<PosessionCardInfo> listCardConnect(Integer userId, Integer cardId){
        return posCardDAO.listCardConnect(userId, cardId);
    }
    
    public List<PossessionCard> getListPossessionCardById(Integer userId, Integer cardId){
        return posCardDAO.getListPossessionCardById(userId, cardId);
    }
    
    public int deletePossessionCard(Integer userId, Integer cardId){
        int finalResult = 0;
//        int result = posCardDAO.deletePossessionCard(userId, cardId);
        //if(result == 1){
            if(cardInfoDAO.updateCardDeleted(cardId) == 1)
                finalResult = 1;
        //}
        return finalResult;
    }
    
    public String getUserEmailByCardId(Integer cardId){
        return posCardDAO.getUserEmailByCardId(cardId);
    }
    
    public Integer getUserIdByCardId(Integer cardId){
        return posCardDAO.getUserIdByCardId(cardId);
    }

	@Override
	public int importCardInfoCsv(List<ImportCardInfoCsv> importCardInfoCsvList) {
		return 0;
	}
}
