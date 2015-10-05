/*
 * PossessionCardResponse
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class PossessionCardResponse extends AbstractCommonRes{
    private List<PossessionCard> cardList;
    private String cardPosList;

    /**
     * @return the cardList
     */
    public List<PossessionCard> getCardList() {
        return cardList;
    }

    /**
     * @param cardList the cardList to set
     */
    public void setCardList(List<PossessionCard> cardList) {
        this.cardList = cardList;
    }

    /**
     * @return the cardPosList
     */
    public String getCardPosList() {
        return cardPosList;
    }

    /**
     * @param cardPosList the cardPosList to set
     */
    public void setCardPosList(String cardPosList) {
        this.cardPosList = cardPosList;
    }
}
