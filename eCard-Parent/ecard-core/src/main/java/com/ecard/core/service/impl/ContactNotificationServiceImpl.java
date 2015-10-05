package com.ecard.core.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.ContactNotificationDAO;
import com.ecard.core.model.HistorySendEmail;
import com.ecard.core.service.ContactNotificationService;
import com.ecard.core.vo.ContactNotification;

@Service("contactNotificationService")
@Transactional
public class ContactNotificationServiceImpl implements ContactNotificationService{
   
	@Autowired
	ContactNotificationDAO contactNotificationDAO;
	@Override
	public List<ContactNotification> getAllContactNotification() {
		return contactNotificationDAO.getAllContactNotification();
	}
	@Override
	public int delete(int inquiryId) {
		return contactNotificationDAO.delete(inquiryId);
	}
	@Override
	public ContactNotification getContactNotification(int inquiryId) {
		return contactNotificationDAO.getContactNotification(inquiryId);
	}
	@Override
	public boolean replyMessage(ContactNotification contactNotification) {
		return contactNotificationDAO.replyMessage(contactNotification);
	}
	@Override
	public int sendContactMail(HistorySendEmail historySendEmail) {
		// TODO Auto-generated method stub
		return contactNotificationDAO.sendContactMail(historySendEmail);
	}
	@Override
    public BigInteger getTotalMailNotRead() {
	   return contactNotificationDAO.getTotalMailNotRead();

	}

}
