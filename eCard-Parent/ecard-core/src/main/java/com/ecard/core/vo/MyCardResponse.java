/*
 * MyCardResponse class
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class MyCardResponse extends AbstractCommonRes {
    private List<MyCard> listCardInfo;

    /**
     * @return the listCardInfo
     */
    public List<MyCard> getListCardInfo() {
        return listCardInfo;
    }

    /**
     * @param listCardInfo the listCardInfo to set
     */
    public void setListCardInfo(List<MyCard> listCardInfo) {
        this.listCardInfo = listCardInfo;
    }
}
