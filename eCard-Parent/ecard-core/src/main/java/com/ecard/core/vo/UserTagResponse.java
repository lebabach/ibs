/*
 * UserTagResponse class
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class UserTagResponse extends AbstractCommonRes {
    private List<UserTag> tagList;

    /**
     * @return the tagList
     */
    public List<UserTag> getListUserTag() {
        return tagList;
    }

    /**
     * @param tagList the tagList to set
     */
    public void setListUserTag(List<UserTag> tagList) {
        this.tagList = tagList;
    }
}
