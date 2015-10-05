/*
 * CardInfoResponse class
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class CardUpdateHistoryResponse extends AbstractCommonRes {
    private List<CardUpdateHistory> listCardUpdateHistory;

    /**
     * @return the listCardUpdateHistory
     */
    public List<CardUpdateHistory> getListCardInfo() {
        return listCardUpdateHistory;
    }

    /**
     * @param listCardUpdateHistory the listCardUpdateHistory to set
     */
    public void setListCardInfo(List<CardUpdateHistory> listCardUpdateHistory) {
        this.listCardUpdateHistory = listCardUpdateHistory;
    }
}
