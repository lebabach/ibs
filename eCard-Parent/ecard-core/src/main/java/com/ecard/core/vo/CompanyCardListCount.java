/*
 * CompanyCardListCountResponse
 */
package com.ecard.core.vo;

import java.math.BigInteger;

/**
 *
 * @author vinhla
 */
public class CompanyCardListCount {
    private Integer cardId;
    private Integer companyId;
    private String companyName;
    private BigInteger cardCnt;
    private String companyNameKana;
    
    public CompanyCardListCount(){}
    
    public CompanyCardListCount(Integer cardId, Integer companyId, String companyName, String companyNameKana, BigInteger cardCnt){
        this.cardId = cardId;
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyNameKana = companyNameKana;
        this.cardCnt = cardCnt;
    }
    
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

    /**
     * @return the companyNameKana
     */
    public String getCompanyNameKana() {
        return companyNameKana;
    }

    /**
     * @param companyNameKana the companyNameKana to set
     */
    public void setCompanyNameKana(String companyNameKana) {
        this.companyNameKana = companyNameKana;
    }
}
