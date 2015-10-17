package com.ecard.core.vo;

import java.util.Date;

public class CardInfoCSV {
	
	private String cardIndexNo;	
	private String companyName;
	private String companyNameKana;	
	private String departmentName;
	private String positionName;
    private String lastName;
    private String firstName;
    private String lastNameKana;
    private String firstNameKana;
    private String email;
    private String zipCode;
    private String addressFull;
    private String address1;
    private String address2;
    private String address3;
    private String telNumberCompany;
    private String telNumberDepartment;
    private String telNumberDirect;
    private String faxNumber;
    private String mobileNumber;
    private String companyUrl;
    private String subAddressFull;
    private String subZipCode;
    private String subAddress1;
    private String subAddress2;
    private String subAddress3;
    private String subTelNumberCompany;
    private String subTelNumberDepartment;
    private String subTelNumberDirect;
    private String subFaxNumber;    
    private Date createDate;
    private Date updateDate;
    private Date contactDate;
    
    public CardInfoCSV(){}
    
    public CardInfoCSV(String cardIndexNo, String companyName, String companyNameKana, String departmentName, String positionName, String lastName, String firstName, 
    		String lastNameKana, String firstNameKana, String email, String zipCode, String addressFull,
			String address1, String address2, String address3, String telNumberCompany, String telNumberDepartment,
			String telNumberDirect, String faxNumber, String mobileNumber, String companyUrl, String subAddressFull,
			String subZipCode, String subAddress1, String subAddress2, String subAddress3, String subTelNumberCompany,
			String subTelNumberDepartment, String subTelNumberDirect, String subFaxNumber, String memo1, String memo2, Date createDate, Date updateDate, Date contactDate) {
		
		this.cardIndexNo = cardIndexNo;
		this.companyName = companyName;
		this.companyNameKana = companyNameKana;
		this.departmentName = departmentName;
		this.positionName = positionName;
		this.lastName = lastName;
		this.firstName = firstName;
		this.lastNameKana = lastNameKana;
		this.firstNameKana = firstNameKana;
		this.email = email;
		this.zipCode = zipCode;
		this.addressFull = addressFull;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.telNumberCompany = telNumberCompany;
		this.telNumberDepartment = telNumberDepartment;
		this.telNumberDirect = telNumberDirect;
		this.faxNumber = faxNumber;
		this.mobileNumber = mobileNumber;
		this.companyUrl = companyUrl;
		this.subAddressFull = subAddressFull;
		this.subZipCode = subZipCode;
		this.subAddress1 = subAddress1;
		this.subAddress2 = subAddress2;
		this.subAddress3 = subAddress3;
		this.subTelNumberCompany = subTelNumberCompany;
		this.subTelNumberDepartment = subTelNumberDepartment;
		this.subTelNumberDirect = subTelNumberDirect;
		this.subFaxNumber = subFaxNumber;		
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.contactDate = contactDate;
		
	}
  
    
    
	public String getCardIndexNo() {
		return cardIndexNo;
	}

	public void setCardIndexNo(String cardIndexNo) {
		this.cardIndexNo = cardIndexNo;
	}

	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyNameKana() {
		return companyNameKana;
	}
	public void setCompanyNameKana(String companyNameKana) {
		this.companyNameKana = companyNameKana;
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
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastNameKana() {
		return lastNameKana;
	}
	public void setLastNameKana(String lastNameKana) {
		this.lastNameKana = lastNameKana;
	}
	public String getFirstNameKana() {
		return firstNameKana;
	}
	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddressFull() {
		return addressFull;
	}
	public void setAddressFull(String addressFull) {
		this.addressFull = addressFull;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getTelNumberCompany() {
		return telNumberCompany;
	}
	public void setTelNumberCompany(String telNumberCompany) {
		this.telNumberCompany = telNumberCompany;
	}
	public String getTelNumberDepartment() {
		return telNumberDepartment;
	}
	public void setTelNumberDepartment(String telNumberDepartment) {
		this.telNumberDepartment = telNumberDepartment;
	}
	public String getTelNumberDirect() {
		return telNumberDirect;
	}
	public void setTelNumberDirect(String telNumberDirect) {
		this.telNumberDirect = telNumberDirect;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getCompanyUrl() {
		return companyUrl;
	}
	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}
	public String getSubAddressFull() {
		return subAddressFull;
	}
	public void setSubAddressFull(String subAddressFull) {
		this.subAddressFull = subAddressFull;
	}
	public String getSubZipCode() {
		return subZipCode;
	}
	public void setSubZipCode(String subZipCode) {
		this.subZipCode = subZipCode;
	}
	public String getSubAddress1() {
		return subAddress1;
	}
	public void setSubAddress1(String subAddress1) {
		this.subAddress1 = subAddress1;
	}
	public String getSubAddress2() {
		return subAddress2;
	}
	public void setSubAddress2(String subAddress2) {
		this.subAddress2 = subAddress2;
	}
	public String getSubAddress3() {
		return subAddress3;
	}
	public void setSubAddress3(String subAddress3) {
		this.subAddress3 = subAddress3;
	}
	public String getSubTelNumberCompany() {
		return subTelNumberCompany;
	}
	public void setSubTelNumberCompany(String subTelNumberCompany) {
		this.subTelNumberCompany = subTelNumberCompany;
	}
	public String getSubTelNumberDepartment() {
		return subTelNumberDepartment;
	}
	public void setSubTelNumberDepartment(String subTelNumberDepartment) {
		this.subTelNumberDepartment = subTelNumberDepartment;
	}
	public String getSubTelNumberDirect() {
		return subTelNumberDirect;
	}
	public void setSubTelNumberDirect(String subTelNumberDirect) {
		this.subTelNumberDirect = subTelNumberDirect;
	}
	public String getSubFaxNumber() {
		return subFaxNumber;
	}
	public void setSubFaxNumber(String subFaxNumber) {
		this.subFaxNumber = subFaxNumber;
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

	public Date getContactDate() {
		return contactDate;
	}

	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}	
}