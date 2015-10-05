/*
 * CardConnectResponse
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class CardConnectResponse extends AbstractCommonRes{
    private List<CardConnectModel> cardList;

    /**
     * @return the cardList
     */
    public List<CardConnectModel> getCardList() {
        return cardList;
    }

    /**
     * @param cardList the cardList to set
     */
    public void setCardList(List<CardConnectModel> cardList) {
        this.cardList = cardList;
    }
}
