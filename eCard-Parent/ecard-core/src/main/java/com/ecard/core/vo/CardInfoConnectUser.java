/*
 * CardInfoConnectUser
 */
package com.ecard.core.vo;

/**
 *
 * @author vinhla
 */
public class CardInfoConnectUser {
    private Integer cardId;
    private String name;
    private String lastName;
    private String firstName;
    private String nameKana;
    private String lastNameKana;
    private String firstNameKana;       
    private String companyName;
    private String departmentName;
    private String positionName;
    private String imageFile;
    private String telNumberCompany;
    private String email;
    
    public CardInfoConnectUser(){}
    
    public CardInfoConnectUser(Integer cardId, String name, String lastName, String firstName,
            String nameKana, String lastNameKana, String firstNameKana,
            String companyName, String departmentName, String positionName, String imageFile){
        this.cardId = cardId;
        this.name = name;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nameKana = nameKana;
        this.lastNameKana = lastNameKana;
        this.firstNameKana = firstNameKana;        
        this.companyName = companyName;
        this.departmentName = departmentName;
        this.positionName = positionName;
        this.imageFile = imageFile;
    }
    public CardInfoConnectUser(Integer cardId, String name, String lastName, String firstName,
            String nameKana, String lastNameKana, String firstNameKana,
            String companyName, String departmentName, String positionName, String imageFile,String telNumberCompany,String email){
        this.cardId = cardId;
        this.name = name;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nameKana = nameKana;
        this.lastNameKana = lastNameKana;
        this.firstNameKana = firstNameKana;        
        this.companyName = companyName;
        this.departmentName = departmentName;
        this.positionName = positionName;
        this.imageFile = imageFile;
        this.telNumberCompany = telNumberCompany;
        this.email = email;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the nameKana
     */
    public String getNameKana() {
        return nameKana;
    }

    /**
     * @param nameKana the nameKana to set
     */
    public void setNameKana(String nameKana) {
        this.nameKana = nameKana;
    }

    /**
     * @return the lastNameKana
     */
    public String getLastNameKana() {
        return lastNameKana;
    }

    /**
     * @param lastNameKana the lastNameKana to set
     */
    public void setLastNameKana(String lastNameKana) {
        this.lastNameKana = lastNameKana;
    }

    /**
     * @return the firstNameKana
     */
    public String getFirstNameKana() {
        return firstNameKana;
    }

    /**
     * @param firstNameKana the firstNameKana to set
     */
    public void setFirstNameKana(String firstNameKana) {
        this.firstNameKana = firstNameKana;
    }

	public String getTelNumberCompany() {
		return telNumberCompany;
	}

	public void setTelNumberCompany(String telNumberCompany) {
		this.telNumberCompany = telNumberCompany;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}
