package com.ecard.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.ContactHistoryDAO;
import com.ecard.core.model.ContactHistory;
import com.ecard.core.service.ContactHistoryService;

@Service("contactHistoryService")
@Transactional
public class ContactHistoryServiceImpl implements ContactHistoryService {
	
	@Autowired
	ContactHistoryDAO contactHistoryDAO;
	
	public ContactHistory saveContactHistory(ContactHistory contactHistory){
		return contactHistoryDAO.saveContactHistory(contactHistory);
	}
	
	public List<com.ecard.core.vo.ContactHistory> getListContactHistoryById(Integer cardId){
		return contactHistoryDAO.getListContactHistoryById(cardId);
	}
	
	public int deleteContactHistory(Integer contactHistoryId){
		return contactHistoryDAO.deleteContactHistory(contactHistoryId);
	}

	@Override
	public boolean updateContactHistory(int cardId1, int userId, int cardId2) {
		// TODO Auto-generated method stub
		contactHistoryDAO.updateContactHistory(cardId1, userId, cardId2);
		return false;
	}
	
}
