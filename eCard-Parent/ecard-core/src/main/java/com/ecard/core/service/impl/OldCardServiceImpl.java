package com.ecard.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecard.core.dao.OldCardDAO;
import com.ecard.core.model.OldCard;
import com.ecard.core.service.OldCardService;
import com.ecard.core.service.UserInfoService;

@Service("oldCardServiceImpl")
public class OldCardServiceImpl  implements OldCardService {
	@Autowired
    OldCardDAO oldCardDAO;
	
	public boolean insertOldCard(int card_id,int old_card_id, int card_owner_id, int seq){
		return oldCardDAO.insertOldCard(card_id, old_card_id, card_owner_id, seq);
	}
	public boolean updateCardIdWithOldCard(int cardId,int oldcard){
		return oldCardDAO.updateCardIdWithOldCard(cardId, oldcard);
	}
	
	public OldCard insertOldCard(OldCard oldCard){
		return oldCardDAO.insertOldCard(oldCard);
	}
	
}
