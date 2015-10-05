/*
 * CardInfoModelMap
 */
package com.ecard.core.vo;

import java.math.BigInteger;

/**
 *
 * @author vinhla
 */
public class CardInfoModel {
    private Integer cardId;
    private Integer companyId;
    private String companyName;
    private BigInteger cardCnt;

    /**
     * @return the companyId
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId the companyId to set
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the cardCnt
     */
    public BigInteger getCardCnt() {
        return cardCnt;
    }

    /**
     * @param cardCnt the cardCnt to set
     */
    public void setCardCnt(BigInteger cardCnt) {
        this.cardCnt = cardCnt;
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
