package com.ecard.core.vo;

import java.util.Date;

public class CardUpdateHistoryDownloadVo {
    private int cardId;
    private int paramType;
    private String oldData;
    private String newData;
    private Date createDate;
    private Date updateDate;
    private int operaterId;
    
	public CardUpdateHistoryDownloadVo(int cardId, int paramType, String oldData, String newData, Date createDate,
			Date updateDate, int operaterId) {
		this.cardId = cardId;
		this.paramType = paramType;
		this.oldData = oldData;
		this.newData = newData;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.operaterId = operaterId;
	}
	
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public int getParamType() {
		return paramType;
	}
	public void setParamType(int paramType) {
		this.paramType = paramType;
	}
	public String getOldData() {
		return oldData;
	}
	public void setOldData(String oldData) {
		this.oldData = oldData;
	}
	public String getNewData() {
		return newData;
	}
	public void setNewData(String newData) {
		this.newData = newData;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getOperaterId() {
		return operaterId;
	}
	public void setOperaterId(int operaterId) {
		this.operaterId = operaterId;
	}

}
