/**
 * 
 */
package com.ecard.webapp.vo;

/**
 * @author Livepass.ThienDP
 *
 */
public class UserInfoResultVO {
	private int userId;
	private String name;
	private String companyName;
	private String positionName;
	private String email;
	private String mobileNumber;
	private String createDate;
	private String firstName;
	private String lastName;
	private String firstNameKana;
	private String lastNameKana;
	private String departmentName;
	private String userIndexNo;
	private int leaveFlg;
    private int useStopFlg;
	public String getUserIndexNo() {
		return userIndexNo;
	}
	public void setUserIndexNo(String userIndexNo) {
		this.userIndexNo = userIndexNo;
	}
	/**
	 * @param userId
	 * @param name
	 * @param companyName
	 * @param positionName
	 * @param email
	 * @param mobileNumber
	 * @param createDate
	 */
	public UserInfoResultVO(int userId, String name, String companyName, String positionName, String email,
			String mobileNumber, String createDate,String firstName ,String lastName,String firstNameKana,String lastNameKana ,String departmentName,String userIndexNo) {
		super();
		this.userId = userId;
		this.name = name;
		this.companyName = companyName;
		this.positionName = positionName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.createDate = createDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.firstNameKana = firstNameKana;
		this.lastNameKana = lastNameKana;
		this.departmentName = departmentName;
		this.userIndexNo=userIndexNo;
	}
	
	public UserInfoResultVO(int userId, String name, String companyName, String positionName, String email,
			String mobileNumber, String createDate,String firstName ,String lastName,String firstNameKana,String lastNameKana ,String departmentName,String userIndexNo,int leaveFlg,int useStopFlg  ) {
		super();
		this.userId = userId;
		this.name = name;
		this.companyName = companyName;
		this.positionName = positionName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.createDate = createDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.firstNameKana = firstNameKana;
		this.lastNameKana = lastNameKana;
		this.departmentName = departmentName;
		this.userIndexNo=userIndexNo;
		this.leaveFlg = leaveFlg;
		this.useStopFlg = useStopFlg;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getFirstNameKana() {
		return firstNameKana;
	}
	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}
	public String getLastNameKana() {
		return lastNameKana;
	}
	public void setLastNameKana(String lastNameKana) {
		this.lastNameKana = lastNameKana;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public int getLeaveFlg() {
		return leaveFlg;
	}
	public void setLeaveFlg(int leaveFlg) {
		this.leaveFlg = leaveFlg;
	}
	public int getUseStopFlg() {
		return useStopFlg;
	}
	public void setUseStopFlg(int useStopFlg) {
		this.useStopFlg = useStopFlg;
	}
	
	
	
	
	
}
