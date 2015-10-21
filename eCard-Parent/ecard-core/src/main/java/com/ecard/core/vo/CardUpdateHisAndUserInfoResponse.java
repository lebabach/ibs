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
    //private List<CardUpdateHisAndUserInfo> cardList;
    
    private List<com.ecard.core.vo.CardInfo> cards;

    public List<com.ecard.core.vo.CardInfo> getCards() {
		return cards;
	}

	public void setCards(List<com.ecard.core.vo.CardInfo> cards) {
		this.cards = cards;
	}

	/**
     * @return the cardList
     */
    /*public List<CardUpdateHisAndUserInfo> getCardList() {
        return cardList;
    }

    *//**
     * @param cardList the cardList to set
     *//*
    public void setCardList(List<CardUpdateHisAndUserInfo> cardList) {
        this.cardList = cardList;
    }*/
}
