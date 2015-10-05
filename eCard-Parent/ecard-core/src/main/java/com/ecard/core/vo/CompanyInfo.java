/*
 * CompanyInfo class
 */
package com.ecard.core.vo;

/**
 *
 * @author vinhla
 */
public class CompanyInfo extends AbstractCommonRes{
    private int cardId;
    private String companyName;
    private Long cardCnt;

    /**
     * @return the cardId
     */
    public int getCardId() {
        return cardId;
    }

    /**
     * @param cardId the cardId to set
     */
    public void setCardId(int cardId) {
        this.cardId = cardId;
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
    public Long getCardCnt() {
        return cardCnt;
    }

    /**
     * @param cardCnt the cardCnt to set
     */
    public void setCardCnt(Long cardCnt) {
        this.cardCnt = cardCnt;
    }

}
