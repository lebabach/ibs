/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author HienTu
 */
public class TagGroup {
    private  List<Integer> ListCardIds;
    private Integer tagId;
    private String tagName;
    private Integer userId;

    /**
     * @return the ListCardIds
     */
    public List<Integer> getListCardIds() {
        return ListCardIds;
    }

    /**
     * @param ListCardIds the ListCardIds to set
     */
    public void setListCardIds(List<Integer> ListCardIds) {
        this.ListCardIds = ListCardIds;
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
}
