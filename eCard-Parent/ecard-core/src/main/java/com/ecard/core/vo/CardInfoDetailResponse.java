/*
 * CardInfoDetailResponse
 */
package com.ecard.core.vo;

/**
 *
 * @author vinhla
 */
public class CardInfoDetailResponse extends AbstractCommonRes {
    private CardInfoDetail cardInfoDetail;
    private UserInfoDetail ownerInfoDetail;

    /**
     * @return the cardInfoDetail
     */
    public CardInfoDetail getCardInfoDetail() {
        return cardInfoDetail;
    }

    /**
     * @param cardInfoDetail the cardInfoDetail to set
     */
    public void setCardInfoDetail(CardInfoDetail cardInfoDetail) {
        this.cardInfoDetail = cardInfoDetail;
    }

    /**
     * @return the ownerInfoDetail
     */
    public UserInfoDetail getOwnerInfoDetail() {
        return ownerInfoDetail;
    }

    /**
     * @param ownerInfoDetail the ownerInfoDetail to set
     */
    public void setOwnerInfoDetail(UserInfoDetail ownerInfoDetail) {
        this.ownerInfoDetail = ownerInfoDetail;
    }
    
}
