package com.ecard.core.vo;

import java.util.Date;

public class CardInfoNotifyChange {
	    private Integer cardId;	    
		private String name;	   
	    private String companyName;
	    private String companyNameKana;
	    private String departmentName;
	    private String positionName;
	    private String email;
	    private String telNumberCompany;
	    private String mobileNumber;	    
	    private String addressFull;
	    private String companyUrl;
	    private Integer cardOwnerId;	    
	    private Integer groupCompanyId;
	    private Date contactDate;
	    public CardInfoNotifyChange(Integer cardId, String name, String companyName, String companyNameKana,
				String departmentName, String positionName, String email, String telNumberCompany, String mobileNumber,
				String addressFull, String companyUrl, Integer cardOwnerId, Integer groupCompanyId, Date contactDate) {			
			this.cardId = cardId;
			this.name = name;
			this.companyName = companyName;
			this.companyNameKana = companyNameKana;
			this.departmentName = departmentName;
			this.positionName = positionName;
			this.email = email;
			this.telNumberCompany = telNumberCompany;
			this.mobileNumber = mobileNumber;
			this.addressFull = addressFull;
			this.companyUrl = companyUrl;
			this.cardOwnerId = cardOwnerId;
			this.groupCompanyId = groupCompanyId;
			this.contactDate = contactDate;
		}
	    
		public Integer getCardId() {
			return cardId;
		}
		public void setCardId(Integer cardId) {
			this.cardId = cardId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getTelNumberCompany() {
			return telNumberCompany;
		}
		public void setTelNumberCompany(String telNumberCompany) {
			this.telNumberCompany = telNumberCompany;
		}
		public String getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		public String getAddressFull() {
			return addressFull;
		}
		public void setAddressFull(String addressFull) {
			this.addressFull = addressFull;
		}
		public String getCompanyUrl() {
			return companyUrl;
		}
		public void setCompanyUrl(String companyUrl) {
			this.companyUrl = companyUrl;
		}
		public Integer getCardOwnerId() {
			return cardOwnerId;
		}
		public void setCardOwnerId(Integer cardOwnerId) {
			this.cardOwnerId = cardOwnerId;
		}
		public Integer getGroupCompanyId() {
			return groupCompanyId;
		}
		public void setGroupCompanyId(Integer groupCompanyId) {
			this.groupCompanyId = groupCompanyId;
		}

		public Date getContactDate() {
			return contactDate;
		}

		public void setContactDate(Date contactDate) {
			this.contactDate = contactDate;
		}
	    
	    

}
