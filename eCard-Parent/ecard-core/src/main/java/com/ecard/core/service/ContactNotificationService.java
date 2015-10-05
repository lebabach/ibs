package com.ecard.core.service;

import java.math.BigInteger;
import java.util.List;

import com.ecard.core.model.HistorySendEmail;
import com.ecard.core.vo.ContactNotification;

public interface ContactNotificationService {
	
	public List<ContactNotification> getAllContactNotification();
	public int delete(int inquiryId);
	public ContactNotification getContactNotification(int inquiryId);
	public boolean replyMessage(ContactNotification contactNotification);
	public int sendContactMail(HistorySendEmail historySendEmail);
	public BigInteger getTotalMailNotRead();
	

}
