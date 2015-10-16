package com.ecard.core.vo;

import java.util.Date;

public class ContactHistory {
	private int contactHistoryId;
	private int cardId;
    private int userId;
    private Date contactDate;
    private String contactMemo;
    private String place;
    private String title;
    
    public ContactHistory(){}
    
    public ContactHistory(int contactHistoryId, int cardId, int userId, Date contactDate, String contactMemo){
    	this.setContactHistoryId(contactHistoryId);
    	this.cardId = cardId;
    	this.userId = userId;
    	this.contactDate = contactDate;
    	this.contactMemo = contactMemo;
    }   
    
    public ContactHistory(int contactHistoryId, int cardId, int userId, Date contactDate, String contactMemo,String place,String title){
    	this.setContactHistoryId(contactHistoryId);
    	this.cardId = cardId;
    	this.userId = userId;
    	this.contactDate = contactDate;
    	this.contactMemo = contactMemo;
    	this.place=place;
    	this.title=title;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
