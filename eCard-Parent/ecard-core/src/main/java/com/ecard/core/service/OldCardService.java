package com.ecard.core.service;

import com.ecard.core.dao.IGenericDao;
import com.ecard.core.model.OldCard;

public interface OldCardService{
	public boolean insertOldCard(int card_id,int old_card_id, int card_owner_id, int seq);
	public boolean updateCardIdWithOldCard(int cardId,int oldcard);
	public OldCard insertOldCard(OldCard oldCard);
}
