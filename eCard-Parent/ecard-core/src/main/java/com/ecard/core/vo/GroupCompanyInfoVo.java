/*
 * GroupCompanyInfoVo
 */
package com.ecard.core.vo;

import java.util.Date;

/**
 *
 * @author vinhla
 */
public class GroupCompanyInfoVo {
    private Integer groupCompanyId;
    private String groupCompanyName;
    private String groupCompanyNameKana;
    private Date createDate;
    private Date updateDate;
    private Integer operaterId;

    /**
     * @return the groupCompanyName
     */
    public String getGroupCompanyName() {
        return groupCompanyName;
    }

    /**
     * @param groupCompanyName the groupCompanyName to set
     */
    public void setGroupCompanyName(String groupCompanyName) {
        this.groupCompanyName = groupCompanyName;
    }

    /**
     * @return the groupCompanyNameKana
     */
    public String getGroupCompanyNameKana() {
        return groupCompanyNameKana;
    }

    /**
     * @param groupCompanyNameKana the groupCompanyNameKana to set
     */
    public void setGroupCompanyNameKana(String groupCompanyNameKana) {
        this.groupCompanyNameKana = groupCompanyNameKana;
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
     * @return the updateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
     * @return the groupCompanyId
     */
    public Integer getGroupCompanyId() {
        return groupCompanyId;
    }

    /**
     * @param groupCompanyId the groupCompanyId to set
     */
    public void setGroupCompanyId(Integer groupCompanyId) {
        this.groupCompanyId = groupCompanyId;
    }
}
