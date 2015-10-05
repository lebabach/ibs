/*
 * CardUpdateHisAndUserInfo
 */
package com.ecard.core.vo;

import java.util.Date;

/**
 *
 * @author vinhla
 */
public class CardUpdateHisAndUserInfo {
    private Integer cardId;
    private String paramText;
    private String oldData;
    private String newData;
    private Date createDate;
    private Integer operaterId;
    private String operaterName;
    
    public CardUpdateHisAndUserInfo(){}
    
    public CardUpdateHisAndUserInfo(Integer cardId, String paramText, String oldData, String newData, 
                        Date createDate, Integer operaterId, String operaterName)
    {
        this.cardId = cardId;
        this.paramText = paramText;
        this.oldData = oldData;
        this.newData = newData;
        this.createDate = createDate;
        this.operaterId = operaterId;
        this.operaterName = operaterName;
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
     * @return the paramText
     */
    public String getParamText() {
        return paramText;
    }

    /**
     * @param paramText the paramText to set
     */
    public void setParamText(String paramText) {
        this.paramText = paramText;
    }

    /**
     * @return the oldData
     */
    public String getOldData() {
        return oldData;
    }

    /**
     * @param oldData the oldData to set
     */
    public void setOldData(String oldData) {
        this.oldData = oldData;
    }

    /**
     * @return the newData
     */
    public String getNewData() {
        return newData;
    }

    /**
     * @param newData the newData to set
     */
    public void setNewData(String newData) {
        this.newData = newData;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the operaterId
     */
    public Integer getOperaterId() {
        return operaterId;
    }

    /**
     * @param operaterId the operaterId to set
     */
    public void setOperaterId(Integer operaterId) {
        this.operaterId = operaterId;
    }

    /**
     * @return the operaterName
     */
    public String getOperaterName() {
        return operaterName;
    }

    /**
     * @param operaterName the operaterName to set
     */
    public void setOperaterName(String operaterName) {
        this.operaterName = operaterName;
    }
}
