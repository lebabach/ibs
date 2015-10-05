/*
 * CardUpdateHisAndUserInfoResponse
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class CardUpdateHisAndUserInfoResponse extends AbstractCommonRes{
    private List<CardUpdateHisAndUserInfo> cardList;

    /**
     * @return the cardList
     */
    public List<CardUpdateHisAndUserInfo> getCardList() {
        return cardList;
    }

    /**
     * @param cardList the cardList to set
     */
    public void setCardList(List<CardUpdateHisAndUserInfo> cardList) {
        this.cardList = cardList;
    }
}
