/*
 * CardTagResponse
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class CardTagResponse extends AbstractCommonRes{
    private List<CardTag> cardList;
    private List<com.ecard.core.model.CardTag> tagList;

    /**
     * @return the cardList
     */
    public List<CardTag> getCardList() {
        return cardList;
    }

    /**
     * @param cardList the cardList to set
     */
    public void setCardList(List<CardTag> cardList) {
        this.cardList = cardList;
    }

    /**
     * @return the tagList
     */
    public List<com.ecard.core.model.CardTag> getTagList() {
        return tagList;
    }

    /**
     * @param tagList the tagList to set
     */
    public void setTagList(List<com.ecard.core.model.CardTag> tagList) {
        this.tagList = tagList;
    }

}
