/*
 * MyCardAndCardInfoResponse
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class MyCardAndCardInfoResponse extends AbstractCommonRes{
    private List<MyCardAndCardInfo> cardList;

    /**
     * @return the cardList
     */
    public List<MyCardAndCardInfo> getCardList() {
        return cardList;
    }

    /**
     * @param cardList the cardList to set
     */
    public void setCardList(List<MyCardAndCardInfo> cardList) {
        this.cardList = cardList;
    }
}
