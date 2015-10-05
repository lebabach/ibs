/*
 * CardInfoAndPosCard
 */
package com.ecard.core.vo;

import java.util.Date;

/**
 *
 * @author admin
 */
public class CardInfoAndPosCard {
    private Integer cardId;
    private Integer approvalStatus;
    private String imageFile;
    private Date createDate;
    
    public CardInfoAndPosCard(){}
    
    public CardInfoAndPosCard(Integer cardId, Integer approvalStatus, String imageFile, Date createDate){
        this.cardId = cardId;
        this.approvalStatus = approvalStatus;
        this.imageFile = imageFile;
        this.createDate = createDate;
    }

    /**
     * @return the cardId
     */
    public Integer getCardId() {
        return cardId;
    }

    /**
     * @param cardId the cardId to set
     */
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    /**
     * @return the approvalStatus
     */
    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    /**
     * @param approvalStatus the approvalStatus to set
     */
    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    /**
     * @return the imageFile
     */
    public String getImageFile() {
        return imageFile;
    }

    /**
     * @param imageFile the imageFile to set
     */
    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
