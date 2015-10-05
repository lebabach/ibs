/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.vo;

import java.math.BigInteger;

/**
 *
 * @author HienTu
 */
public class UserTagAndCardTag {
    private BigInteger cardCount;
    private Integer tagId;
    private String tagName;
    
    public UserTagAndCardTag(){}
        
    public UserTagAndCardTag(BigInteger cardCount, Integer tagId, String tagName){
        this.cardCount = cardCount;
        this.tagId = tagId;
        this.tagName = tagName;
    }
    /**
     * @return the cardCount
     */
    public BigInteger getCardCount() {
        return cardCount;
    }

    /**
     * @param cardCount the cardCount to set
     */
    public void setCardCount(BigInteger cardCount) {
        this.cardCount = cardCount;
    }

    /**
     * @return the tagId
     */
    public Integer getTagId() {
        return tagId;
    }

    /**
     * @param tagId the tagId to set
     */
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
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
