package com.ecard.core.vo;

import java.util.Date;

public class ActionLogDownloadVo {
	private int userId;
    private int actionType;
    private String actionMessage;
    private Date actionDate;
    
    
	public ActionLogDownloadVo(int userId, int actionType, String actionMessage, Date actionDate) {		
		this.userId = userId;
		this.actionType = actionType;
		this.actionMessage = actionMessage;
		this.actionDate = actionDate;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getActionType() {
		return actionType;
	}
	public void setActionType(int actionType) {
		this.actionType = actionType;
	}
	public String getActionMessage() {
		return actionMessage;
	}
	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}
	public Date getActionDate() {
		return actionDate;
	}
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
}
