/*
 * CardInfoConnectUserResponse
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class CardInfoConnectUserResponse extends AbstractCommonRes{
    private List<CardInfoConnectUser> cardList;

    /**
     * @return the cardList
     */
    public List<CardInfoConnectUser> getCardList() {
        return cardList;
    }

    /**
     * @param cardList the cardList to set
     */
    public void setCardList(List<CardInfoConnectUser> cardList) {
        this.cardList = cardList;
    }
}
