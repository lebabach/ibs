/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author admin
 */
public class CardInfoPosCardResponse extends AbstractCommonRes{
    private List<CardInfoAndPosCard> cardList;

    /**
     * @return the cardList
     */
    public List<CardInfoAndPosCard> getCardList() {
        return cardList;
    }

    /**
     * @param cardList the cardList to set
     */
    public void setCardList(List<CardInfoAndPosCard> cardList) {
        this.cardList = cardList;
    }
}
