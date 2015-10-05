/**
 * 
 */
package com.ecard.webapp.vo;

/**
 * @author Livepass.ThienDP
 *
 */
public class CardInfoResultVO {
	private int cardId;
	private String imageFile;
	private String name;
	private String companyName;
	private String positionName;
	private String email;
	private String mobileNumber;
	private String createDate;
	private String approvalStatus;
	private String firstName;
	private String lastName;
    private String cardIndexNo;
    private int is_editting;
	
	public int getIs_editting() {
		return is_editting;
	}
	public void setIs_editting(int is_editting) {
		this.is_editting = is_editting;
	}
	public String getCardIndexNo() {
		return cardIndexNo;
	}
	public void setCardIndexNo(String cardIndexNo) {
		this.cardIndexNo = cardIndexNo;
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
	public CardInfoResultVO(int cardId, String imageFile, String name, String companyName, String positionName,
            String email, String mobileNumber, String createDate, String approvalStatus,String fistName,String lastName,String cardIndexNo) {
		this.cardId = cardId;
		this.imageFile = imageFile;
		this.name = name;
		this.companyName = companyName;
		this.positionName = positionName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.createDate = createDate;
		this.approvalStatus = approvalStatus;
        this.cardIndexNo=cardIndexNo;

	}
	/**
	 * @return the cardId
	 */
	public int getCardId() {
		return cardId;
	}
	/**
	 * @param cardId the cardId to set
	 */
	public void setCardId(int cardId) {
		this.cardId = cardId;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the approvalStatus
	 */
	public String getApprovalStatus() {
		return approvalStatus;
	}
	/**
	 * @param approvalStatus the approvalStatus to set
	 */
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	

}
