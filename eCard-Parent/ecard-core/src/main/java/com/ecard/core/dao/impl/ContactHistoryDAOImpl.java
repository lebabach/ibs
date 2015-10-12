package com.ecard.core.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ecard.core.dao.ContactHistoryDAO;
import com.ecard.core.model.ContactHistory;
import com.ecard.core.model.ContactHistoryId;

/**
*
* @author vinhla
*/
@Repository("contactHistoryDAO")
public class ContactHistoryDAOImpl extends GenericDao implements ContactHistoryDAO {
	
	public ContactHistory saveContactHistory(ContactHistory contactHistory) {
		getEntityManager().persist(contactHistory);
		getEntityManager().flush();
		
		return contactHistory;
	}
	
	public List<com.ecard.core.vo.ContactHistory> getListContactHistoryById(Integer cardId){
		Query query = getEntityManager().createNativeQuery("SELECT ct.card_id, ct.user_id, ct.contact_date, ct.contact_memo "
				+ "FROM contact_history ct WHERE ct.card_id = :cardId ORDER BY ct.contact_date DESC");
		query.setParameter("cardId", cardId);
		
		List<Object[]> rows = query.getResultList();
        List<com.ecard.core.vo.ContactHistory> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new com.ecard.core.vo.ContactHistory((Integer)row[0], (Integer)row[1], (Date)row[2], (String)row[3]));
        }
        return result;
	}
	
	public int deleteContactHistory(ContactHistoryId contactHistoryId){
		Query query = getEntityManager().createQuery("DELETE FROM ContactHistory ct WHERE ct.id.cardId = :cardId AND ct.id.userId = :userId");
		query.setParameter("cardId", contactHistoryId.getCardId());
		query.setParameter("userId", contactHistoryId.getUserId());
		
		return query.executeUpdate();
	}
}
