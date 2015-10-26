package com.ecard.webapp.vo;

import java.util.Date;

public class OwnerCards {
	private Integer cardId;
	private String name;
	private String companyName;
	private String departmentName;
	private String positionName;
	private String imageFile;
	private String email;
	private String addressFull;
	private Date contactDate;
	private String contactDateString;
	private String telNumberCompany;
	private String owner;
	private String image;
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

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddressFull() {
		return addressFull;
	}

	public void setAddressFull(String addressFull) {
		this.addressFull = addressFull;
	}

	public Date getContactDate() {
		return contactDate;
	}

	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}

	public String getTelNumberCompany() {
		return telNumberCompany;
	}

	public void setTelNumberCompany(String telNumberCompany) {
		this.telNumberCompany = telNumberCompany;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getContactDateString() {
		return contactDateString;
	}

	public void setContactDateString(String contactDateString) {
		this.contactDateString = contactDateString;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else {
			OwnerCards card = (OwnerCards) obj;
			if (this.getEmail().equals(card.getEmail()) || (this.getName().equals(card.getName()) && this.getCompanyName().equals(card.getCompanyName()))) {
				return true;
			}
		}
		return false;

	}

	@Override
	public int hashCode() {
		return this.email.hashCode();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
