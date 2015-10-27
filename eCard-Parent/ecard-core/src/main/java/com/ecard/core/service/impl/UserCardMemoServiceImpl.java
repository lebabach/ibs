package com.ecard.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecard.core.dao.OldCardDAO;
import com.ecard.core.dao.UserCardMemoDAO;
import com.ecard.core.service.OldCardService;
import com.ecard.core.service.UserCardMemoService;
@Service("userCardMemoServiceImpl")
public class UserCardMemoServiceImpl implements UserCardMemoService {
	@Autowired
	UserCardMemoDAO userCardMemoDAO;
	@Override
	public boolean updateUserCardMemo(int cardId1, int userId, int cardId2) {
		// TODO Auto-generated method stub
		userCardMemoDAO.updateUserCardMemo(cardId1, userId, cardId2);
		return false;
	}

}
