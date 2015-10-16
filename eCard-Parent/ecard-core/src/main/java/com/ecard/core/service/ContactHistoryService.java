package com.ecard.core.service;

import java.util.List;

import com.ecard.core.model.ContactHistory;

/**
*
* @author vinhla
*/
public interface ContactHistoryService {
	public ContactHistory saveContactHistory(ContactHistory contactHistory);
	
	public List<com.ecard.core.vo.ContactHistory> getListContactHistoryById(Integer cardId);
	
	public int deleteContactHistory(Integer contactHistoryId);
}
