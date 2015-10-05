package com.ecard.webapp.vo;

import java.math.BigInteger;
import java.util.List;

import com.ecard.core.vo.ContactNotification;

public class ContactNotificationVO {
	
	List<ContactNotification> listContactNotification;
	BigInteger numberMessageUnread;
	public ContactNotificationVO(List<ContactNotification> listContactNotification, BigInteger numberMessageUnread) {
		super();
		this.listContactNotification = listContactNotification;
		this.numberMessageUnread = numberMessageUnread;
	}
	public List<ContactNotification> getListContactNotification() {
		return listContactNotification;
	}
	public void setListContactNotification(List<ContactNotification> listContactNotification) {
		this.listContactNotification = listContactNotification;
	}
	public BigInteger getNumberMessageUnread() {
		return numberMessageUnread;
	}
	public void setNumberMessageUnread(BigInteger numberMessageUnread) {
		this.numberMessageUnread = numberMessageUnread;
	}
	

}
