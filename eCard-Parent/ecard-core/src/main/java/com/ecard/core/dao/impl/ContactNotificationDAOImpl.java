package com.ecard.core.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.ContactNotificationDAO;
import com.ecard.core.model.HistorySendEmail;
import com.ecard.core.vo.ContactNotification;


@Repository("contactNotificationDAO")
@Transactional
public class ContactNotificationDAOImpl extends GenericDao implements ContactNotificationDAO {

	@Override
	public List<ContactNotification> getAllContactNotification() {
		// TODO Auto-generated method stub
		  Query query = getEntityManager().createNativeQuery("Select u.name,u.first_name,u.last_name,u.company_name,u.department_name,u.position_name,inq.inquiry_id ,inq.user_id,inq.inquiry_type,inq.title,inq.inquiry_text,inq.answer_flg,inq.answer_text, " 
				  + " inq.create_date,inq.update_date,u.email from inquiry_info inq inner join user_info u on  inq.user_id = u.user_id " +
                    " where u.leave_flg = 0 and u.delete_flg = 0 order by inq.create_date DESC");
	        List<Object[]> rows = query.getResultList();
	        List<ContactNotification> result = new ArrayList<>(rows.size());
	        for (Object[] row : rows) {
	            result.add(new ContactNotification((String)row[0],(String)row[1],(String)row[2],(String)row[3],(String)row[4],(String)row[5],(Integer)row[6],(Integer)row[7],(Integer)row[8],(String)row[9],(String)row[10],(Integer)row[11],(String)row[12],(Date)row[13],(Date)row[14],(String)row[15]));
	        }
	        return result;
	}

	@Override
	public int delete(int inquiryId) {
		// TODO Auto-generated method stub
		 Query query = getEntityManager().createNativeQuery("delete from inquiry_info  where inquiry_id =:inquiryId");
		 query.setParameter("inquiryId", inquiryId);
			if(query.executeUpdate()>0){
				return 0;
			}
		
		return 1;
	
	}

	@Override
	public ContactNotification getContactNotification(int inquiryId) {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createNativeQuery("Select u.name,u.first_name,u.last_name,u.company_name,u.department_name,u.position_name,inq.inquiry_id ,inq.user_id,inq.inquiry_type,inq.title,inq.inquiry_text,inq.answer_flg,inq.answer_text, " 
				  + " inq.create_date,inq.update_date,u.email from inquiry_info inq inner join user_info u on  inq.user_id = u.user_id " 
				  + " where u.leave_flg = 0 and u.delete_flg = 0 and inq.inquiry_id =:inquiry_id ");
		query.setParameter("inquiry_id", inquiryId);
		Object[] row = (Object[])query.getResultList().get(0);
		ContactNotification contactNotification = new ContactNotification((String)row[0],(String)row[1],(String)row[2],(String)row[3],(String)row[4],(String)row[5],(Integer)row[6],(Integer)row[7],(Integer)row[8],(String)row[9],(String)row[10],(Integer)row[11],(String)row[12],(Date)row[13],(Date)row[14],(String)row[15]);
		
		return contactNotification;
	}

	@Override
	public boolean replyMessage(ContactNotification contactNotification) {
		// TODO Auto-generated method stub
		 Query query = getEntityManager().createNativeQuery("UPDATE inquiry_info inq SET inq.answer_flg = :answer_flg ,inq.answer_text = :answer_text WHERE inq.inquiry_id =:inquiry_id");
		 query.setParameter("answer_flg", 1);
		 query.setParameter("answer_text", contactNotification.getAnswerText());
		 query.setParameter("inquiry_id", contactNotification.getInquiryId());
		 if(query.executeUpdate() > 0){
			 return true;
		 }
		return false;
	}

	@Override
	public int sendContactMail(HistorySendEmail historySendEmail) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		em.persist(historySendEmail);
		em.close();
		return 0;
	}
	
	@Override
 	public BigInteger getTotalMailNotRead() {
 		String sqlQuery = " SELECT COUNT(*) FROM inquiry_info inq inner join user_info u on  inq.user_id = u.user_id " 
                     + " where u.leave_flg = 0 and u.delete_flg = 0 and inq.answer_flg = 0 ";
 	Query query = getEntityManager().createNativeQuery(sqlQuery);
 	return (BigInteger)query.getSingleResult();
 }

}
