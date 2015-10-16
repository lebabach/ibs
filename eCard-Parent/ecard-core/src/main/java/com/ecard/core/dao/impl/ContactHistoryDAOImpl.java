package com.ecard.core.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ecard.core.dao.ContactHistoryDAO;
import com.ecard.core.model.ContactHistory;

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
		Query query = getEntityManager().createNativeQuery("SELECT ct.contact_history_id, ct.card_id, ct.user_id, ct.contact_date, ct.contact_memo "
				+ "FROM contact_history ct WHERE ct.card_id = :cardId ORDER BY ct.contact_date DESC");
		query.setParameter("cardId", cardId);
		
		List<Object[]> rows = query.getResultList();
        List<com.ecard.core.vo.ContactHistory> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new com.ecard.core.vo.ContactHistory((Integer)row[0], (Integer)row[1], (Integer)row[2], (Date)row[3], (String)row[4], (String)row[5], (String)row[6]));
        }
        return result;
	}
	
	public int deleteContactHistory(Integer contactHistoryId){
		Query query = getEntityManager().createQuery("DELETE FROM ContactHistory ct WHERE ct.contactHistoryId = :contactHistoryId");
		query.setParameter("contactHistoryId", contactHistoryId);
		
		return query.executeUpdate();
	}
	
	
}
