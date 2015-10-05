package com.ecard.webapp.vo;

import java.util.Date;
import java.util.List;

public class ShowHistoryMailVO {
    private Integer id;
    private String userSendTo;
    private List<Integer> userId;
    private String mailTitle;
    private String mailDetail;
    private int sendType;
    private Date createDate;
    
    public ShowHistoryMailVO(){}
	public ShowHistoryMailVO(Integer id, String userSendTo, List<Integer> userId, String mailTitle, String mailDetail,
			int sendType, Date createDate) {		
		this.id = id;
		this.userSendTo = userSendTo;
		this.userId = userId;
		this.mailTitle = mailTitle;
		this.mailDetail = mailDetail;
		this.sendType = sendType;
		this.createDate = createDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserSendTo() {
		return userSendTo;
	}
	public void setUserSendTo(String userSendTo) {
		this.userSendTo = userSendTo;
	}
	public List<Integer> getUserId() {
		return userId;
	}
	public void setUserId(List<Integer> userId) {
		this.userId = userId;
	}
	public String getMailTitle() {
		return mailTitle;
	}
	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}
	public String getMailDetail() {
		return mailDetail;
	}
	public void setMailDetail(String mailDetail) {
		this.mailDetail = mailDetail;
	}
	public int getSendType() {
		return sendType;
	}
	public void setSendType(int sendType) {
		this.sendType = sendType;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
}
