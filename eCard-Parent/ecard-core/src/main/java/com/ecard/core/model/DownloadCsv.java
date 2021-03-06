package com.ecard.core.model;
// Generated Aug 31, 2015 5:27:39 PM by Hibernate Tools 3.2.4.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * DownloadCsv generated by hbm2java
 */
@Entity
@Table(name="download_csv"
)
public class DownloadCsv  implements java.io.Serializable {


     private Integer csvId;
     private UserInfo userInfo;
     private int csvType;
     private Date requestDate;
     private int csvApprovalStatus;
     private Date approvalDate;
     private String csvUrl;
     private int operaterId;

    public DownloadCsv() {
    }

	
    public DownloadCsv(int csvType, Date requestDate, int csvApprovalStatus, Date approvalDate) {
        this.csvType = csvType;
        this.requestDate = requestDate;
        this.csvApprovalStatus = csvApprovalStatus;
        this.approvalDate = approvalDate;
    }
    public DownloadCsv(Integer csvId, UserInfo userInfo, int csvType, Date requestDate, int csvApprovalStatus, Date approvalDate, String csvUrl, int operaterId) {
       this.csvId = csvId;
       this.userInfo = userInfo;
       this.csvType = csvType;
       this.requestDate = requestDate;
       this.csvApprovalStatus = csvApprovalStatus;
       this.approvalDate = approvalDate;
       this.csvUrl = csvUrl;
       this.operaterId = operaterId;
    }
   
    public DownloadCsv(Integer csvId, UserInfo userInfo, int csvType, Date requestDate, int csvApprovalStatus, Date approvalDate, String csvUrl) {
        this.csvId = csvId;
        this.userInfo = userInfo;
        this.csvType = csvType;
        this.requestDate = requestDate;
        this.csvApprovalStatus = csvApprovalStatus;
        this.approvalDate = approvalDate;
        this.csvUrl = csvUrl;
     }
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="csv_id", unique=true, nullable=false)
    public Integer getCsvId() {
        return this.csvId;
    }
    
    public void setCsvId(Integer csvId) {
        this.csvId = csvId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    public UserInfo getUserInfo() {
        return this.userInfo;
    }
    
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    
    @Column(name="csv_type", nullable=false)
    public int getCsvType() {
        return this.csvType;
    }
    
    public void setCsvType(int csvType) {
        this.csvType = csvType;
    }

    
    @Column(name="request_date", nullable=false, length=19)
    public Date getRequestDate() {
        return this.requestDate;
    }
    
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    
    @Column(name="csv_approval_status", nullable=false)
    public int getCsvApprovalStatus() {
        return this.csvApprovalStatus;
    }
    
    public void setCsvApprovalStatus(int csvApprovalStatus) {
        this.csvApprovalStatus = csvApprovalStatus;
    }

    
    @Column(name="approval_date", nullable=false, length=19)
    public Date getApprovalDate() {
        return this.approvalDate;
    }
    
    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    
    @Column(name="csv_url", length=65535)
    public String getCsvUrl() {
        return this.csvUrl;
    }
    
    public void setCsvUrl(String csvUrl) {
        this.csvUrl = csvUrl;
    }

    @Column(name="operater_id", nullable=true)
    public int getOperaterId() {
        return this.operaterId;
    }
    
    public void setOperaterId(int operaterId) {
        this.operaterId = operaterId;
    }



}


