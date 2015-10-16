package com.ecard.core.vo;

import java.util.Date;

public class ContactHistory {
	private int contactHistoryId;
	private int cardId;
    private int userId;
    private Date contactDate;
    private String contactMemo;
    
    public ContactHistory(){}
    
    public ContactHistory(int contactHistoryId, int cardId, int userId, Date contactDate, String contactMemo){
    	this.setContactHistoryId(contactHistoryId);
    	this.cardId = cardId;
    	this.userId = userId;
    	this.contactDate = contactDate;
    	this.contactMemo = contactMemo;
    }    
    
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getContactDate() {
		return contactDate;
	}
	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}
	public String getContactMemo() {
		return contactMemo;
	}
	public void setContactMemo(String contactMemo) {
		this.contactMemo = contactMemo;
	}

	public int getContactHistoryId() {
		return contactHistoryId;
	}

	public void setContactHistoryId(int contactHistoryId) {
		this.contactHistoryId = contactHistoryId;
	}
}
