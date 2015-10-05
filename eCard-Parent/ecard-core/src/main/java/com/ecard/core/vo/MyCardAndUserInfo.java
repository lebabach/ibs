/*
 * MyCardAndUserInfo class
 */
package com.ecard.core.vo;

/**
 *
 * @author vinhla
 */
public class MyCardAndUserInfo {
    private Integer cardId;
    private Integer userId;
    private Integer seq;
    
    public MyCardAndUserInfo(){}
    
    public MyCardAndUserInfo(Integer cardId, Integer userId, Integer seq){
        this.cardId = cardId;
        this.userId = userId;
        this.seq = seq;
    }

    /**
     * @return the cardId
     */
    public int getCardId() {
        return cardId;
    }

    /**
     * @param cardId the cardId to set
     */
    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the seq
     */
    public int getSeq() {
        return seq;
    }

    /**
     * @param seq the seq to set
     */
    public void setSeq(int seq) {
        this.seq = seq;
    }

}
