/*
 * CardInfoResponse class
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class CardInfoResponse extends AbstractCommonRes {
    private List<CardInfo> cardList;
    private Long totalRecord;

    /**
     * @return the cardList
     */
    public List<CardInfo> getListCardInfo() {
        return cardList;
    }

    /**
     * @param cardList the cardList to set
     */
    public void setListCardInfo(List<CardInfo> cardList) {
        this.cardList = cardList;
    }

    /**
     * @return the totalRecord
     */
    public Long getTotalRecord() {
        return totalRecord;
    }

    /**
     * @param totalRecord the totalRecord to set
     */
    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
    }
}
