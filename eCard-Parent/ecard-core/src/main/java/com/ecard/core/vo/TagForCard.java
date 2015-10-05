/*
 * TagForCard
 */
package com.ecard.core.vo;

/**
 *
 * @author HienTu
 */
public class TagForCard {
    private Integer userId;
    private Integer cardId;
    private Integer tagId;
    private String tagName;
    
    public TagForCard (){}
    
    public TagForCard(Integer userId, Integer cardId, Integer tagId, String tagName){
        this.userId = userId;
        this.cardId = cardId;
        this.tagId = tagId;
        this.tagName = tagName;
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

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the cardId
     */
    public Integer getCardId() {
        return cardId;
    }

    /**
     * @param cardId the cardId to set
     */
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    
}
