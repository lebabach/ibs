package com.ecard.core.vo;

import java.util.Date;

public class CSVDownloadInfo {
	private Integer userId;
	private Integer csvId;
	private String userName;
	private String companyName;
	private String departmentName;
	private String positionName;    
    private int csvType;
    private Date requestDate;
    private int csvApprovalStatus;
    private Date approvalDate;
    private String csvUrl;
    private int operaterId;
    private String operaterName;
    
    public CSVDownloadInfo(){}
    
	public CSVDownloadInfo(Integer userId, Integer csvId, String userName, String companyName, String departmentName,
			String positionName, int csvType, Date requestDate, int csvApprovalStatus, Date approvalDate, String csvUrl, int operaterId, String operaterName) {		
		this.userId = userId;
		this.csvId = csvId;
		this.userName = userName;
		this.companyName = companyName;
		this.departmentName = departmentName;
		this.positionName = positionName;
		this.csvType = csvType;
		this.requestDate = requestDate;
		this.csvApprovalStatus = csvApprovalStatus;
		this.approvalDate = approvalDate;
		this.csvUrl = csvUrl;
		this.operaterId = operaterId;
		this.operaterName = operaterName;
	}
    
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCsvId() {
		return csvId;
	}

	public void setCsvId(Integer csvId) {
		this.csvId = csvId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public int getCsvType() {
		return csvType;
	}
	public void setCsvType(int csvType) {
		this.csvType = csvType;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public int getCsvApprovalStatus() {
		return csvApprovalStatus;
	}
	public void setCsvApprovalStatus(int csvApprovalStatus) {
		this.csvApprovalStatus = csvApprovalStatus;
	}
	public Date getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getCsvUrl() {
		return csvUrl;
	}

	public void setCsvUrl(String csvUrl) {
		this.csvUrl = csvUrl;
	}

	public int getOperaterId() {
		return operaterId;
	}

	public void setOperaterId(int operaterId) {
		this.operaterId = operaterId;
	}

	public String getOperaterName() {
		return operaterName;
	}

	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}

    
}
