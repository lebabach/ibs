/*
 * CardInfo class
 */
package com.ecard.core.vo;

import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.ecard.core.util.StringUtilsHelper;

/**
 *
 * @author vinhla
 */
public class CardInfo {
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
    private Date createDate;
    private Integer company_id;
    private int cardType;
    private String cardBackImgFile;
    private String companyNameKana;
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
    private Integer fileOutputFlg;
    private String handMemo;
    private String autoMemo;
    private String memo1;
    private String memo2;
    private Integer cardOwnerId;
    private Integer publishStatus;
    private Integer approvalStatus;
    private Integer oldCardFlg;
    private Date updateDate;
    private Integer operaterId;
    private Date deletDate;
    private Integer deleteFlg;
    private String indexId;
    private Integer groupCompanyId;
    private int is_editting;
    private Date contactDate;
    private BigInteger count;   
    private String tagName;
private String fullName;
    private String contactDateString;
    public int getIs_editting() {
		return is_editting;
	}

	public void setIs_editting(int is_editting) {
		this.is_editting = is_editting;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public CardInfo(){}
	
	public CardInfo(Date contactDate, String companyName, String departmentName){
		this.contactDate = contactDate;
		this.companyName = companyName;
        this.departmentName = departmentName;
    }
	
	public CardInfo(Integer cardId, String name, String lastName, String firstName, String nameKana, String lastNameKana, String firstNameKana,
            String companyName, String departmentName, String positionName, String imageFile, Integer approvalStatus ,Date createDate){
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
        this.approvalStatus = approvalStatus;
        this.createDate = createDate;
    }
	
	public CardInfo(String companyName, BigInteger count, Integer cardId){
		this.companyName = companyName;
		this.count = count;
		this.cardId = cardId;
	}
	
	public CardInfo(Integer cardId, String name){
		this.cardId = cardId;
		this.name = name;
	}
    
    public CardInfo(Integer cardId, String companyName, String departmentName, String name, BigInteger count){
        this.cardId = cardId;
        this.companyName = companyName;
        this.departmentName = departmentName;
        this.name = name;
        this.count = count;
    }
    public CardInfo(Integer cardId, String name, String lastName, String firstName, String nameKana, String lastNameKana, String firstNameKana,
            String companyName, String departmentName, String positionName, String imageFile, Integer approvalStatus ,Date createDate,String email,String telNumberCompany ){
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
        this.approvalStatus = approvalStatus;
        this.createDate = createDate;
        this.email = email;
        this.telNumberCompany = telNumberCompany;
    }
    
    public CardInfo(Integer cardId, String name, String firstName, String lastName, String nameKana, String firstNameKana, String lastNameKana, 
            String companyName, String departmentName, String imageFile, String positionName, Date createDate, Integer approvalStatus, String telNumberCompany, String email,
            Integer companyId, Integer groupCompanyId){
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
        this.approvalStatus = approvalStatus;
        this.createDate = createDate;
        this.telNumberCompany = telNumberCompany;
        this.email = email;
        this.company_id = companyId;
        this.groupCompanyId = groupCompanyId;
    }
    
    public CardInfo(Integer cardId, String name, String firstName, String lastName, String nameKana, String firstNameKana, String lastNameKana, 
            String companyName, String companyNameKana, String departmentName, String imageFile, String positionName, Date createDate, Integer approvalStatus, 
            String telNumberCompany, String email){
        this.cardId = cardId;
        this.name = name;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nameKana = nameKana;
        this.lastNameKana = lastNameKana;
        this.firstNameKana = firstNameKana;
        this.companyName = companyName;
        this.companyNameKana = companyNameKana;
        this.departmentName = departmentName;
        this.positionName = positionName;    
        this.imageFile = imageFile;
        this.approvalStatus = approvalStatus;
        this.createDate = createDate;
        this.telNumberCompany = telNumberCompany;
        this.email = email;
    
    }
    public CardInfo(Integer cardId, String name, String firstName, String lastName, String nameKana, String firstNameKana, String lastNameKana, 
            String companyName, String companyNameKana, String departmentName, String imageFile, String positionName, Date createDate, Integer approvalStatus, 
            String telNumberCompany, String email,String tagName){
        this.cardId = cardId;
        this.name = name;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nameKana = nameKana;
        this.lastNameKana = lastNameKana;
        this.firstNameKana = firstNameKana;
        this.companyName = companyName;
        this.companyNameKana = companyNameKana;
        this.departmentName = departmentName;
        this.positionName = positionName;    
        this.imageFile = imageFile;
        this.approvalStatus = approvalStatus;
        this.createDate = createDate;
        this.telNumberCompany = telNumberCompany;
        this.email = email;
        this.tagName = tagName;
    }
    
    public CardInfo(Integer cardId, String name, String firstName, String lastName, String nameKana, String firstNameKana, String lastNameKana, 
            String companyName, String departmentName, String imageFile, String positionName, Date createDate, Integer approvalStatus, String telNumberCompany, String email){
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
        this.approvalStatus = approvalStatus;
        this.createDate = createDate;
        this.telNumberCompany = telNumberCompany;
        this.email = email;
    }
    
    public CardInfo(String name, String lastName, String firstName, String lastNameKana, String firstNameKana, String nameKana, 
            String companyName, String companyNameKana, String departmentName, String positionName, String telNumberCompany, String addressFull, String email){
        this.name = name;
        this.lastName = lastName;
        this.firstName = firstName;
        this.lastNameKana = lastNameKana;
        this.firstNameKana = firstNameKana;
        this.nameKana = nameKana;
        this.companyName = companyName;
        this.companyNameKana = companyNameKana;
        this.departmentName = departmentName;
        this.positionName = positionName;
        this.telNumberCompany = telNumberCompany;
        this.addressFull = addressFull;
        this.email = email;
    }

    public CardInfo(Integer cardId, String name, String lastName, String firstName, String nameKana, String lastNameKana,
			String firstNameKana, String companyName, String departmentName, String positionName, String imageFile,
			Date createDate, Integer company_id, Integer cardType, String companyNameKana, String email, String zipCode,
			String addressFull, String address1, String address2, String address3, String telNumberCompany,
			String telNumberDepartment, String telNumberDirect, String faxNumber, String mobileNumber,
			String companyUrl, String subAddressFull, Integer cardOwnerId, Integer publishStatus, Integer approvalStatus,
			Date updateDate, Integer operaterId, Date deletDate,Integer deleteFlg, String indexId) {
		super();
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
		this.createDate = createDate;
		this.company_id = company_id;
		this.cardType = cardType;
		this.companyNameKana = companyNameKana;
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
		this.cardOwnerId = cardOwnerId;
		this.publishStatus = publishStatus;
		this.approvalStatus = approvalStatus;
		this.updateDate = updateDate;
		this.operaterId = operaterId;
		this.deletDate = deletDate;
	    this.deleteFlg = deleteFlg;
	    this.indexId = indexId;
	}
    
    public CardInfo(Integer cardId, String name, String lastName, String firstName, String nameKana, String lastNameKana,
			String firstNameKana, String companyName, String departmentName, String positionName, String imageFile,
			Date createDate, Integer company_id, Integer cardType, String companyNameKana, String email, String zipCode,
			String addressFull, String address1, String address2, String address3, String telNumberCompany,
			String telNumberDepartment, String telNumberDirect, String faxNumber, String mobileNumber,
			String companyUrl, String subAddressFull, Integer cardOwnerId, Integer publishStatus, Integer approvalStatus,
			Date updateDate, Integer operaterId, Date deletDate,Integer deleteFlg, String indexId, int is_editting) {
		super();
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
		this.createDate = createDate;
		this.company_id = company_id;
		this.cardType = cardType;
		this.companyNameKana = companyNameKana;
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
		this.cardOwnerId = cardOwnerId;
		this.publishStatus = publishStatus;
		this.approvalStatus = approvalStatus;
		this.updateDate = updateDate;
		this.operaterId = operaterId;
		this.deletDate = deletDate;
	    this.deleteFlg = deleteFlg;
	    this.indexId = indexId;
	    this.is_editting=is_editting;
	}
    
    public CardInfo(Integer cardId, String name, String firstName, String lastName, String nameKana, String firstNameKana, String lastNameKana, 
            String companyName, String departmentName, String imageFile, String positionName, Date createDate, Integer approvalStatus, String telNumberCompany, String email,
            Integer companyId, Integer groupCompanyId,String addressFul, Date contactDate, Integer ownerId){
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
        this.approvalStatus = approvalStatus;
        this.createDate = createDate;
        this.telNumberCompany = telNumberCompany;
        this.email = email;
        this.company_id = companyId;
        this.groupCompanyId = groupCompanyId;
        this.addressFull = addressFul;
        this.contactDate=contactDate;
        this.cardOwnerId=ownerId;
    }
    
    public CardInfo(Integer cardId, String name, String firstName, String lastName, String nameKana, String firstNameKana, String lastNameKana, 
            String companyName, String departmentName, String imageFile, String positionName, Date createDate, Integer approvalStatus, String telNumberCompany, String email,
            Integer companyId, Integer groupCompanyId,String addressFul, Date contactDate){
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
        this.approvalStatus = approvalStatus;
        this.createDate = createDate;
        this.telNumberCompany = telNumberCompany;
        this.email = email;
        this.company_id = companyId;
        this.groupCompanyId = groupCompanyId;
        this.addressFull = addressFul;
        this.contactDate=contactDate;
    }

	/**
     * @return the name
     */
    public String getName() {
    	
    	name = StringUtilsHelper.mergerStringEitherAWord(lastName, firstName, " ");
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getCardBackImgFile() {
		return cardBackImgFile;
	}

	public void setCardBackImgFile(String cardBackImgFile) {
		this.cardBackImgFile = cardBackImgFile;
	}

	public String getCompanyNameKana() {
		return companyNameKana;
	}

	public void setCompanyNameKana(String companyNameKana) {
		this.companyNameKana = companyNameKana;
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

	public Integer getFileOutputFlg() {
		return fileOutputFlg;
	}

	public void setFileOutputFlg(Integer fileOutputFlg) {
		this.fileOutputFlg = fileOutputFlg;
	}

	public String getHandMemo() {
		return handMemo;
	}

	public void setHandMemo(String handMemo) {
		this.handMemo = handMemo;
	}

	public String getAutoMemo() {
		return autoMemo;
	}

	public void setAutoMemo(String autoMemo) {
		this.autoMemo = autoMemo;
	}

	public String getMemo1() {
		return memo1;
	}

	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}

	public String getMemo2() {
		return memo2;
	}

	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}

	public Integer getCardOwnerId() {
		return cardOwnerId;
	}

	public void setCardOwnerId(Integer cardOwnerId) {
		this.cardOwnerId = cardOwnerId;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public int getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(int approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Integer getOldCardFlg() {
		return oldCardFlg;
	}

	public void setOldCardFlg(Integer oldCardFlg) {
		this.oldCardFlg = oldCardFlg;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getOperaterId() {
		return operaterId;
	}

	public void setOperaterId(Integer operaterId) {
		this.operaterId = operaterId;
	}

	public Date getDeletDate() {
		return deletDate;
	}

	public void setDeletDate(Date deletDate) {
		this.deletDate = deletDate;
	}

	public Integer getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(Integer deleteFlg) {
		this.deleteFlg = deleteFlg;
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

    /**
     * @return the contactDate
     */
    public Date getContactDate() {
        return contactDate;
    }

    /**
     * @param contactDate the contactDate to set
     */
    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }

	public BigInteger getCount() {
		return count;
	}

	public void setCount(BigInteger count) {
		this.count = count;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = this.fullName;
	}

	public String getContactDateString() {
		return contactDateString;
	}

	public void setContactDateString(String contactDateString) {
		this.contactDateString = contactDateString;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
    
    
}
