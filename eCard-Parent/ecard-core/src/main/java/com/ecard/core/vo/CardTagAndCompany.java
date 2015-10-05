/*
 * CardTagAndCompany
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class CardTagAndCompany {
    private List<CardTagAndCardInfo> cardInfo;
    private String tagName;
    
    
    CardTagAndCompany () {}
    
    public CardTagAndCompany (List<CardTagAndCardInfo> cardInfo, String tagName) {
        this.cardInfo = cardInfo;
        this.tagName = tagName;
             
    }

    /**
     * @return the cardInfo
     */
    public List<CardTagAndCardInfo> getCardInfo() {
        return cardInfo;
    }

    /**
     * @param cardInfo the cardInfo to set
     */
    public void setCardInfo(List<CardTagAndCardInfo> cardInfo) {
        this.cardInfo = cardInfo;
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
