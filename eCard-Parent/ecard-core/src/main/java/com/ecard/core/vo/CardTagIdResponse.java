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
public class CardTagIdResponse  extends AbstractCommonRes {
    private List<TagGroup> tagList;

    /**
     * @return the tagList
     */
    public List<TagGroup> getTagList() {
        return tagList;
    }

    /**
     * @param tagList the tagList to set
     */
    public void setTagList(List<TagGroup> tagList) {
        this.tagList = tagList;
    }
    
    
}
