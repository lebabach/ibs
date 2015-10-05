/*
 * CompanyInfoResponse class
 */
package com.ecard.core.vo;

/**
 *
 * @author vinhla
 */
public class CompanyInfoResponse extends AbstractCommonRes {
    private String cardList;

    /**
     * @return the cardList
     */
    public String getCardList() {
        return cardList;
    }

    /**
     * @param cardList the cardList to set
     */
    public void setCardList(String cardList) {
        this.cardList = cardList;
    }

}
