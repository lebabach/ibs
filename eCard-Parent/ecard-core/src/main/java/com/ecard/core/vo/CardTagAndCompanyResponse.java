/*
 * CardTagAndCompanyResponse
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class CardTagAndCompanyResponse extends AbstractCommonRes{
    private String tagName;
    private List<CardTagAndCardInfo> cardList;
    

    /**
     * @return the cardList
     */
    public List<CardTagAndCardInfo> getCardList() {
        return cardList;
    }

    /**
     * @param cardList the cardList to set
     */
    public void setCardList(List<CardTagAndCardInfo> cardList) {
        this.cardList = cardList;
    }

    /**
     * @return the tagName
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * @param tagName the tagName to set
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
