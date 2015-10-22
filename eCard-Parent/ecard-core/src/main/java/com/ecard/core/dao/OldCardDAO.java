package com.ecard.core.dao;

import com.ecard.core.model.OldCard;

public interface OldCardDAO extends IGenericDao<OldCard>{
	public boolean updateCardIdWithOldCard(int cardId,int oldcard);
	public boolean insertOldCard(int card_id,int old_card_id, int card_owner_id, int seq);
}
