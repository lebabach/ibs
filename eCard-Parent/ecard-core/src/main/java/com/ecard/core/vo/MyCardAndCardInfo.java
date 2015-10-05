/*
 * MyCardAndCardInfo
 */
package com.ecard.core.vo;

import java.util.Date;

/**
 *
 * @author vinhla
 */
public class MyCardAndCardInfo {
    private Integer cardId;
    private String companyName;
    private String departmentName;
    private String positionName;
    private String imageFile;
    private Date startDate;
    private Date endDate;
    private Integer approvalStatus;
    private String approvalStatusText;
    private Integer seq;
    
    public MyCardAndCardInfo(){}
    
    public MyCardAndCardInfo(Integer cardId, String companyName, 
            String departmentName, String positionName, String imageFile, 
            Date startDate, Date endDate, Integer approvalStatus, Integer seq){
        this.cardId = cardId;
        this.companyName = companyName;
        this.departmentName = departmentName;
        this.positionName = positionName;
        this.imageFile = imageFile;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approvalStatus = approvalStatus;
        switch(approvalStatus){
            case 1 : 
                this.approvalStatusText = "????";
            case 2 : 
                this.approvalStatusText = "???????";
            case 3 : 
                this.approvalStatusText = "????";
            case 4 : 
                this.approvalStatusText = "?????";
            case 5 : 
                this.approvalStatusText = "????";
            default:
                this.approvalStatusText = "??????";
        };
        
        this.seq = seq;
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
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return the positionName
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * @param positionName the positionName to set
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    /**
     * @return the imageFile
     */
    public String getImageFile() {
        return imageFile;
    }

    /**
     * @param imageFile the imageFile to set
     */
    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the approvalStatus
     */
    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    /**
     * @param approvalStatus the approvalStatus to set
     */
    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    /**
     * @return the approvalStatusText
     */
    public String getApprovalStatusText() {
        return approvalStatusText;
    }

    /**
     * @param approvalStatusText the approvalStatusText to set
     */
    public void setApprovalStatusText(String approvalStatusText) {
        this.approvalStatusText = approvalStatusText;
    }

    /**
     * @return the seq
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * @param seq the seq to set
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
