package com.ecard.core.dao;

import com.ecard.core.model.OldCard;
import com.ecard.core.model.UserCardMemo;
import com.ecard.core.model.enums.IndexTypeEnum;

public interface UserCardMemoDAO extends IGenericDao<UserCardMemo>{
	public boolean updateUserCardMemo(int cardId1,int userId,int cardId2);
}
