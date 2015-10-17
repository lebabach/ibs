package com.ecard.core.dao;

import com.ecard.core.model.OldCard;

public interface OldCardDAO extends IGenericDao<OldCard>{
	public boolean updateCardIdWithOldCard(int cardId,int oldcard);
}
