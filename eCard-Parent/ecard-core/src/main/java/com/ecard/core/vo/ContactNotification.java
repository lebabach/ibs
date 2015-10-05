package com.ecard.core.vo;

import java.util.Date;



public class ContactNotification {
	private Integer inquiryId;
    private int inquiryType;
    private String title;
    private String inquiryText;
    private int answerFlg;
    private String answerText;
    private Date createDate;
    private Date updateDate;
    private int operaterId;
    private int userId;
    private String name;
    private String firstName;
    private String lastName;
    private String companyName;
    private String departmentName;
    private String positionName;
    private String email;
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ContactNotification(){}

	public ContactNotification(String name,
			String firstName, String lastName, String companyName, String departmentName, String positionName,Integer inquiryId, int userId, int inquiryType, String title, String inquiryText, int answerFlg,
			String answerText, Date createDate, Date updateDate ) {
		super();
		this.inquiryId = inquiryId;
		this.inquiryType = inquiryType;
		this.title = title;
		this.inquiryText = inquiryText;
		this.answerFlg = answerFlg;
		this.answerText = answerText;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.userId = userId;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.departmentName = departmentName;
		this.positionName = positionName;
	}
	public ContactNotification(String name,
			String firstName, String lastName, String companyName, String departmentName, String positionName,Integer inquiryId, int userId, int inquiryType, String title, String inquiryText, int answerFlg,
			String answerText, Date createDate, Date updateDate,String email ) {
		super();
		this.inquiryId = inquiryId;
		this.inquiryType = inquiryType;
		this.title = title;
		this.inquiryText = inquiryText;
		this.answerFlg = answerFlg;
		this.answerText = answerText;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.userId = userId;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.departmentName = departmentName;
		this.positionName = positionName;
		this.email = email;
	}

	public Integer getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(Integer inquiryId) {
		this.inquiryId = inquiryId;
	}

	public int getInquiryType() {
		return inquiryType;
	}

	public void setInquiryType(int inquiryType) {
		this.inquiryType = inquiryType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInquiryText() {
		return inquiryText;
	}

	public void setInquiryText(String inquiryText) {
		this.inquiryText = inquiryText;
	}

	public int getAnswerFlg() {
		return answerFlg;
	}

	public void setAnswerFlg(int answerFlg) {
		this.answerFlg = answerFlg;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getOperaterId() {
		return operaterId;
	}

	public void setOperaterId(int operaterId) {
		this.operaterId = operaterId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	
    
}
