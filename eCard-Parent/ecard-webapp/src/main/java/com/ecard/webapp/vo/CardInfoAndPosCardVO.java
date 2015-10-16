package com.ecard.webapp.vo;

import java.util.Date;

public class CardInfoAndPosCardVO {
	private Integer cardId;
    private Integer approvalStatus;
    private String imageFile;
    private String createDate;
	public CardInfoAndPosCardVO(Integer cardId, Integer approvalStatus, String imageFile, String createDate) {
		this.cardId = cardId;
		this.approvalStatus = approvalStatus;
		this.imageFile = imageFile;
		this.createDate = createDate;
	}
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public Integer getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
    
	
    
}
