package com.ecard.core.model;
// Generated Sep 26, 2015 2:30:09 PM by Hibernate Tools 3.2.4.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * CardInfo generated by hbm2java
 */
@Entity
@Table(name="card_info"
)
public class CardInfo  implements java.io.Serializable {


     private Integer cardId;
     private CompanyInfo companyInfo;
     private int cardType;
     private String imageFile;
     private String cardBackImgFile;
     private String companyName;
     private String companyNameKana;
     private String departmentName;
     private String positionName;
     private String name;
     private String lastName;
     private String firstName;
     private String nameKana;
     private String lastNameKana;
     private String firstNameKana;
     private String email;
     private String zipCode;
     private String addressFull;
     private String address1;
     private String address2;
     private String address3;
     private String address4;
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
     private String subAddress4;
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
     private int publishStatus;
     private int approvalStatus;
     private int oldCardFlg;
     private Date createDate;
     private Date updateDate;
     private int operaterId;
     private Date deletDate;
     private int deleteFlg;
     private String cardOwnerName;
     private int groupCompanyId;
     private int newestCardFlg;
     private Date contactDate;
     private String cardIndexNo;
     private String subMobileNumber;
     private String subEmail;
     private String subCompanyUrl;
     private String importanceLevel;
     private int isEditting;
     private Date dateEditting;
     private Set<CardTag> cardTags = new HashSet<CardTag>(0);
     private Set<CardUpdateHistory> cardUpdateHistories = new HashSet<CardUpdateHistory>(0);
     private Set<AdminPossessionCard> adminPossessionCards = new HashSet<AdminPossessionCard>(0);
     private Set<ContactHistory> contactHistories = new HashSet<ContactHistory>(0);
     private Set<OldCard> oldCards = new HashSet<OldCard>(0);
     private Set<PossessionCard> possessionCards = new HashSet<PossessionCard>(0);
     private Set<PrusalHistory> prusalHistories = new HashSet<PrusalHistory>(0);
     private Set<UserCardMemo> userCardMemos = new HashSet<UserCardMemo>(0);

    public CardInfo() {
    }

	
    public CardInfo(CompanyInfo companyInfo, int cardType, int publishStatus, int approvalStatus, int oldCardFlg, int operaterId, int deleteFlg, int groupCompanyId, int newestCardFlg, int isEditting) {
        this.companyInfo = companyInfo;
        this.cardType = cardType;
        this.publishStatus = publishStatus;
        this.approvalStatus = approvalStatus;
        this.oldCardFlg = oldCardFlg;
        this.operaterId = operaterId;
        this.deleteFlg = deleteFlg;
        this.groupCompanyId = groupCompanyId;
        this.newestCardFlg = newestCardFlg;
        this.isEditting = isEditting;
    }
    public CardInfo(CompanyInfo companyInfo, int cardType, String imageFile, String cardBackImgFile, String companyName, String companyNameKana, String departmentName, String positionName, String name, String lastName, String firstName, String nameKana, String lastNameKana, String firstNameKana, String email, String zipCode, String addressFull, String address1, String address2, String address3, String address4, String telNumberCompany, String telNumberDepartment, String telNumberDirect, String faxNumber, String mobileNumber, String companyUrl, String subAddressFull, String subZipCode, String subAddress1, String subAddress2, String subAddress3, String subAddress4, String subTelNumberCompany, String subTelNumberDepartment, String subTelNumberDirect, String subFaxNumber, Integer fileOutputFlg, String handMemo, String autoMemo, String memo1, String memo2, Integer cardOwnerId, int publishStatus, int approvalStatus, int oldCardFlg, Date createDate, Date updateDate, int operaterId, Date deletDate, int deleteFlg, String cardOwnerName, int groupCompanyId, int newestCardFlg, Date contactDate, String cardIndexNo, String subMobileNumber, String subEmail, String subCompanyUrl, String importanceLevel, int isEditting, Date dateEditting, Set<CardTag> cardTags, Set<CardUpdateHistory> cardUpdateHistories, Set<AdminPossessionCard> adminPossessionCards, Set<ContactHistory> contactHistories, Set<OldCard> oldCards, Set<PossessionCard> possessionCards, Set<PrusalHistory> prusalHistories, Set<UserCardMemo> userCardMemos) {
       this.companyInfo = companyInfo;
       this.cardType = cardType;
       this.imageFile = imageFile;
       this.cardBackImgFile = cardBackImgFile;
       this.companyName = companyName;
       this.companyNameKana = companyNameKana;
       this.departmentName = departmentName;
       this.positionName = positionName;
       this.name = name;
       this.lastName = lastName;
       this.firstName = firstName;
       this.nameKana = nameKana;
       this.lastNameKana = lastNameKana;
       this.firstNameKana = firstNameKana;
       this.email = email;
       this.zipCode = zipCode;
       this.addressFull = addressFull;
       this.address1 = address1;
       this.address2 = address2;
       this.address3 = address3;
       this.address4 = address4;
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
       this.subAddress4 = subAddress4;
       this.subTelNumberCompany = subTelNumberCompany;
       this.subTelNumberDepartment = subTelNumberDepartment;
       this.subTelNumberDirect = subTelNumberDirect;
       this.subFaxNumber = subFaxNumber;
       this.fileOutputFlg = fileOutputFlg;
       this.handMemo = handMemo;
       this.autoMemo = autoMemo;
       this.memo1 = memo1;
       this.memo2 = memo2;
       this.cardOwnerId = cardOwnerId;
       this.publishStatus = publishStatus;
       this.approvalStatus = approvalStatus;
       this.oldCardFlg = oldCardFlg;
       this.createDate = createDate;
       this.updateDate = updateDate;
       this.operaterId = operaterId;
       this.deletDate = deletDate;
       this.deleteFlg = deleteFlg;
       this.cardOwnerName = cardOwnerName;
       this.groupCompanyId = groupCompanyId;
       this.newestCardFlg = newestCardFlg;
       this.contactDate = contactDate;
       this.cardIndexNo = cardIndexNo;
       this.subMobileNumber = subMobileNumber;
       this.subEmail = subEmail;
       this.subCompanyUrl = subCompanyUrl;
       this.importanceLevel = importanceLevel;
       this.isEditting = isEditting;
       this.dateEditting = dateEditting;
       this.cardTags = cardTags;
       this.cardUpdateHistories = cardUpdateHistories;
       this.adminPossessionCards = adminPossessionCards;
       this.contactHistories = contactHistories;
       this.oldCards = oldCards;
       this.possessionCards = possessionCards;
       this.prusalHistories = prusalHistories;
       this.userCardMemos = userCardMemos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="card_id", unique=true, nullable=false)
    public Integer getCardId() {
        return this.cardId;
    }
    
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="company_id", nullable=false)
    public CompanyInfo getCompanyInfo() {
        return this.companyInfo;
    }
    
    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    
    @Column(name="card_type", nullable=false)
    public int getCardType() {
        return this.cardType;
    }
    
    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    
    @Column(name="image_file", length=65535)
    public String getImageFile() {
        return this.imageFile;
    }
    
    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    
    @Column(name="card_back_img_file", length=65535)
    public String getCardBackImgFile() {
        return this.cardBackImgFile;
    }
    
    public void setCardBackImgFile(String cardBackImgFile) {
        this.cardBackImgFile = cardBackImgFile;
    }

    
    @Column(name="company_name", length=65535)
    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    
    @Column(name="company_name_kana", length=65535)
    public String getCompanyNameKana() {
        return this.companyNameKana;
    }
    
    public void setCompanyNameKana(String companyNameKana) {
        this.companyNameKana = companyNameKana;
    }

    
    @Column(name="department_name", length=65535)
    public String getDepartmentName() {
        return this.departmentName;
    }
    
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    
    @Column(name="position_name", length=65535)
    public String getPositionName() {
        return this.positionName;
    }
    
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    
    @Column(name="name", length=65535)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="last_name", length=65535)
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    @Column(name="first_name", length=65535)
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    @Column(name="name_kana", length=65535)
    public String getNameKana() {
        return this.nameKana;
    }
    
    public void setNameKana(String nameKana) {
        this.nameKana = nameKana;
    }

    
    @Column(name="last_name_kana", length=65535)
    public String getLastNameKana() {
        return this.lastNameKana;
    }
    
    public void setLastNameKana(String lastNameKana) {
        this.lastNameKana = lastNameKana;
    }

    
    @Column(name="first_name_kana", length=65535)
    public String getFirstNameKana() {
        return this.firstNameKana;
    }
    
    public void setFirstNameKana(String firstNameKana) {
        this.firstNameKana = firstNameKana;
    }

    
    @Column(name="email", length=65535)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="zip_code", length=65535)
    public String getZipCode() {
        return this.zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    
    @Column(name="address_full", length=65535)
    public String getAddressFull() {
        return this.addressFull;
    }
    
    public void setAddressFull(String addressFull) {
        this.addressFull = addressFull;
    }

    
    @Column(name="address_1", length=65535)
    public String getAddress1() {
        return this.address1;
    }
    
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    
    @Column(name="address_2", length=65535)
    public String getAddress2() {
        return this.address2;
    }
    
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    
    @Column(name="address_3", length=65535)
    public String getAddress3() {
        return this.address3;
    }
    
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    
    @Column(name="address_4", length=65535)
    public String getAddress4() {
        return this.address4;
    }
    
    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    
    @Column(name="tel_number_company", length=65535)
    public String getTelNumberCompany() {
        return this.telNumberCompany;
    }
    
    public void setTelNumberCompany(String telNumberCompany) {
        this.telNumberCompany = telNumberCompany;
    }

    
    @Column(name="tel_number_department", length=65535)
    public String getTelNumberDepartment() {
        return this.telNumberDepartment;
    }
    
    public void setTelNumberDepartment(String telNumberDepartment) {
        this.telNumberDepartment = telNumberDepartment;
    }

    
    @Column(name="tel_number_direct", length=65535)
    public String getTelNumberDirect() {
        return this.telNumberDirect;
    }
    
    public void setTelNumberDirect(String telNumberDirect) {
        this.telNumberDirect = telNumberDirect;
    }

    
    @Column(name="fax_number", length=65535)
    public String getFaxNumber() {
        return this.faxNumber;
    }
    
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    
    @Column(name="mobile_number", length=65535)
    public String getMobileNumber() {
        return this.mobileNumber;
    }
    
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    
    @Column(name="company_url", length=65535)
    public String getCompanyUrl() {
        return this.companyUrl;
    }
    
    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    
    @Column(name="sub_address_full", length=65535)
    public String getSubAddressFull() {
        return this.subAddressFull;
    }
    
    public void setSubAddressFull(String subAddressFull) {
        this.subAddressFull = subAddressFull;
    }

    
    @Column(name="sub_zip_code", length=65535)
    public String getSubZipCode() {
        return this.subZipCode;
    }
    
    public void setSubZipCode(String subZipCode) {
        this.subZipCode = subZipCode;
    }

    
    @Column(name="sub_address_1", length=65535)
    public String getSubAddress1() {
        return this.subAddress1;
    }
    
    public void setSubAddress1(String subAddress1) {
        this.subAddress1 = subAddress1;
    }

    
    @Column(name="sub_address_2", length=65535)
    public String getSubAddress2() {
        return this.subAddress2;
    }
    
    public void setSubAddress2(String subAddress2) {
        this.subAddress2 = subAddress2;
    }

    
    @Column(name="sub_address_3", length=65535)
    public String getSubAddress3() {
        return this.subAddress3;
    }
    
    public void setSubAddress3(String subAddress3) {
        this.subAddress3 = subAddress3;
    }

    
    @Column(name="sub_address_4", length=65535)
    public String getSubAddress4() {
        return this.subAddress4;
    }
    
    public void setSubAddress4(String subAddress4) {
        this.subAddress4 = subAddress4;
    }

    
    @Column(name="sub_tel_number_company", length=65535)
    public String getSubTelNumberCompany() {
        return this.subTelNumberCompany;
    }
    
    public void setSubTelNumberCompany(String subTelNumberCompany) {
        this.subTelNumberCompany = subTelNumberCompany;
    }

    
    @Column(name="sub_tel_number_department", length=65535)
    public String getSubTelNumberDepartment() {
        return this.subTelNumberDepartment;
    }
    
    public void setSubTelNumberDepartment(String subTelNumberDepartment) {
        this.subTelNumberDepartment = subTelNumberDepartment;
    }

    
    @Column(name="sub_tel_number_direct", length=65535)
    public String getSubTelNumberDirect() {
        return this.subTelNumberDirect;
    }
    
    public void setSubTelNumberDirect(String subTelNumberDirect) {
        this.subTelNumberDirect = subTelNumberDirect;
    }

    
    @Column(name="sub_fax_number", length=65535)
    public String getSubFaxNumber() {
        return this.subFaxNumber;
    }
    
    public void setSubFaxNumber(String subFaxNumber) {
        this.subFaxNumber = subFaxNumber;
    }

    
    @Column(name="file_output_flg")
    public Integer getFileOutputFlg() {
        return this.fileOutputFlg;
    }
    
    public void setFileOutputFlg(Integer fileOutputFlg) {
        this.fileOutputFlg = fileOutputFlg;
    }

    
    @Column(name="hand_memo", length=65535)
    public String getHandMemo() {
        return this.handMemo;
    }
    
    public void setHandMemo(String handMemo) {
        this.handMemo = handMemo;
    }

    
    @Column(name="auto_memo", length=65535)
    public String getAutoMemo() {
        return this.autoMemo;
    }
    
    public void setAutoMemo(String autoMemo) {
        this.autoMemo = autoMemo;
    }

    
    @Column(name="memo1", length=65535)
    public String getMemo1() {
        return this.memo1;
    }
    
    public void setMemo1(String memo1) {
        this.memo1 = memo1;
    }

    
    @Column(name="memo2", length=65535)
    public String getMemo2() {
        return this.memo2;
    }
    
    public void setMemo2(String memo2) {
        this.memo2 = memo2;
    }

    
    @Column(name="card_owner_id")
    public Integer getCardOwnerId() {
        return this.cardOwnerId;
    }
    
    public void setCardOwnerId(Integer cardOwnerId) {
        this.cardOwnerId = cardOwnerId;
    }

    
    @Column(name="publish_status", nullable=false)
    public int getPublishStatus() {
        return this.publishStatus;
    }
    
    public void setPublishStatus(int publishStatus) {
        this.publishStatus = publishStatus;
    }

    
    @Column(name="approval_status", nullable=false)
    public int getApprovalStatus() {
        return this.approvalStatus;
    }
    
    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    
    @Column(name="old_card_flg", nullable=false)
    public int getOldCardFlg() {
        return this.oldCardFlg;
    }
    
    public void setOldCardFlg(int oldCardFlg) {
        this.oldCardFlg = oldCardFlg;
    }

    
    @Column(name="create_date", length=19)
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    
    @Column(name="update_date", length=19)
    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    
    @Column(name="operater_id", nullable=false)
    public int getOperaterId() {
        return this.operaterId;
    }
    
    public void setOperaterId(int operaterId) {
        this.operaterId = operaterId;
    }

    
    @Column(name="delet_date", length=19)
    public Date getDeletDate() {
        return this.deletDate;
    }
    
    public void setDeletDate(Date deletDate) {
        this.deletDate = deletDate;
    }

    
    @Column(name="delete_flg", nullable=false)
    public int getDeleteFlg() {
        return this.deleteFlg;
    }
    
    public void setDeleteFlg(int deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    
    @Column(name="card_owner_name", length=65535)
    public String getCardOwnerName() {
        return this.cardOwnerName;
    }
    
    public void setCardOwnerName(String cardOwnerName) {
        this.cardOwnerName = cardOwnerName;
    }

    
    @Column(name="group_company_id", nullable=false)
    public int getGroupCompanyId() {
        return this.groupCompanyId;
    }
    
    public void setGroupCompanyId(int groupCompanyId) {
        this.groupCompanyId = groupCompanyId;
    }

    
    @Column(name="newest_card_flg", nullable=false)
    public int getNewestCardFlg() {
        return this.newestCardFlg;
    }
    
    public void setNewestCardFlg(int newestCardFlg) {
        this.newestCardFlg = newestCardFlg;
    }

    
    @Column(name="contact_date", length=19)
    public Date getContactDate() {
        return this.contactDate;
    }
    
    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }

    
    @Column(name="card_index_no", length=18)
    public String getCardIndexNo() {
        return this.cardIndexNo;
    }
    
    public void setCardIndexNo(String cardIndexNo) {
        this.cardIndexNo = cardIndexNo;
    }

    
    @Column(name="sub_mobile_number", length=65535)
    public String getSubMobileNumber() {
        return this.subMobileNumber;
    }
    
    public void setSubMobileNumber(String subMobileNumber) {
        this.subMobileNumber = subMobileNumber;
    }

    
    @Column(name="sub_email", length=65535)
    public String getSubEmail() {
        return this.subEmail;
    }
    
    public void setSubEmail(String subEmail) {
        this.subEmail = subEmail;
    }

    
    @Column(name="sub_company_url", length=65535)
    public String getSubCompanyUrl() {
        return this.subCompanyUrl;
    }
    
    public void setSubCompanyUrl(String subCompanyUrl) {
        this.subCompanyUrl = subCompanyUrl;
    }

    
    @Column(name="importance_level", length=65535)
    public String getImportanceLevel() {
        return this.importanceLevel;
    }
    
    public void setImportanceLevel(String importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    
    @Column(name="is_editting", nullable=false)
    public int getIsEditting() {
        return this.isEditting;
    }
    
    public void setIsEditting(int isEditting) {
        this.isEditting = isEditting;
    }

    
    @Column(name="date_editting", length=19)
    public Date getDateEditting() {
        return this.dateEditting;
    }
    
    public void setDateEditting(Date dateEditting) {
        this.dateEditting = dateEditting;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="cardInfo")
    public Set<CardTag> getCardTags() {
        return this.cardTags;
    }
    
    public void setCardTags(Set<CardTag> cardTags) {
        this.cardTags = cardTags;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="cardInfo")
    public Set<CardUpdateHistory> getCardUpdateHistories() {
        return this.cardUpdateHistories;
    }
    
    public void setCardUpdateHistories(Set<CardUpdateHistory> cardUpdateHistories) {
        this.cardUpdateHistories = cardUpdateHistories;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="cardInfo")
    public Set<AdminPossessionCard> getAdminPossessionCards() {
        return this.adminPossessionCards;
    }
    
    public void setAdminPossessionCards(Set<AdminPossessionCard> adminPossessionCards) {
        this.adminPossessionCards = adminPossessionCards;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="cardInfo")
    public Set<ContactHistory> getContactHistories() {
        return this.contactHistories;
    }
    
    public void setContactHistories(Set<ContactHistory> contactHistories) {
        this.contactHistories = contactHistories;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="cardInfo")
    public Set<OldCard> getOldCards() {
        return this.oldCards;
    }
    
    public void setOldCards(Set<OldCard> oldCards) {
        this.oldCards = oldCards;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="cardInfo")
    public Set<PossessionCard> getPossessionCards() {
        return this.possessionCards;
    }
    
    public void setPossessionCards(Set<PossessionCard> possessionCards) {
        this.possessionCards = possessionCards;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="cardInfo")
    public Set<PrusalHistory> getPrusalHistories() {
        return this.prusalHistories;
    }
    
    public void setPrusalHistories(Set<PrusalHistory> prusalHistories) {
        this.prusalHistories = prusalHistories;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="cardInfo")
    public Set<UserCardMemo> getUserCardMemos() {
        return this.userCardMemos;
    }
    
    public void setUserCardMemos(Set<UserCardMemo> userCardMemos) {
        this.userCardMemos = userCardMemos;
    }




}


