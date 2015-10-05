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
public class UserTagAndCardTagResponse extends AbstractCommonRes {
    private List<UserTagAndCardTag> tagList;

    /**
     * @return the tagList
     */
    public List<UserTagAndCardTag> getTagList() {
        return tagList;
    }

    /**
     * @param tagList the tagList to set
     */
    public void setTagList(List<UserTagAndCardTag> tagList) {
        this.tagList = tagList;
    }    
}
