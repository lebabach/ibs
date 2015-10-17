package com.ecard.core.vo;

import java.util.Date;
import java.util.List;


public class UserInfoVo {
	private Integer userId;
    private Integer roleId ;
    private Integer teamId;
    private Integer teamDivideCnt;
    //private GroupComposition groupComposition;
    private String email;
    private String password;
    private int mailSendFlg;
    private Integer mailNoticeFlg;
    private Integer mailUseAssistFlg;
    private Integer mailNewsFlg;
    private int groupDataDlFlg;
    private int allDataDlFlg;
    private int sfManualLinkFlg;
    private String name;
    private String lastName;
    private String firstName;
    private String lastNameKana;
    private String firstNameKana;
    private String companyName;
    private String companyNameKana;
    private String departmentName;
    private String positionName;
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
    private String coverImage;
    private String memo1;
    private String memo2;
    private Integer leaveFlg;
    private Integer useStopFlg;
    private Date lastLoginDate;
    private Date logoutDate;
    private Date createDate;
    private Date updateDate;
    private Integer operaterId;
    private Date deleteDate;
    private Integer deleteFlg;
    private String userIndexNo;
   
    public String getUserIndexNo() {
		return userIndexNo;
	}
	public void setUserIndexNo(String userIndexNo) {
		this.userIndexNo = userIndexNo;
	}
	public UserInfoVo(Integer userId,String lastName, String firstName, String companyName, String departmentName, String positionName) {		
		this.userId=userId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.companyName = companyName;
		this.departmentName = departmentName;
		this.positionName = positionName;
	}
	
    public UserInfoVo(Integer userId,String name,String lastName, String firstName, String companyName, String departmentName, String positionName) {		
		this.userId=userId;
		this.name = name;
		this.lastName = lastName;
		this.firstName = firstName;
		this.companyName = companyName;
		this.departmentName = departmentName;
		this.positionName = positionName;
	}
    public UserInfoVo(Integer userId,String name,String lastName, String firstName, String companyName, String departmentName, String positionName,String email) {		
		this.userId=userId;
		this.name = name;
		this.lastName = lastName;
		this.firstName = firstName;
		this.companyName = companyName;
		this.departmentName = departmentName;
		this.positionName = positionName;
		this.email = email;
	}
	
	public UserInfoVo(Integer userId, Integer teamDivideCnt, String name,String lastName, String firstName, String companyName, String departmentName, String positionName) {		
		this.userId=userId;
		this.teamDivideCnt = teamDivideCnt;
		this.name = name;
		this.lastName = lastName;
		this.firstName = firstName;
		this.companyName = companyName;
		this.departmentName = departmentName;
		this.positionName = positionName;
	}
    
	public UserInfoVo(Integer userId, Integer roleId, Integer teamId, String email, String name, String lastName,
			String firstName, String lastNameKana, String firstNameKana, String companyName, String companyNameKana,
			String departmentName, String positionName, String zipCode, String addressFull, String address1,
			String address2, String address3, String telNumberCompany, String telNumberDepartment,
			String telNumberDirect, String faxNumber, String mobileNumber, Integer leaveFlg, Date createDate,
			Date updateDate, Date deleteDate, Integer deleteFlg,String userIndexNo) {
		super();
		this.userId = userId;
		this.roleId = roleId;
		this.teamId = teamId;
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.firstName = firstName;
		this.lastNameKana = lastNameKana;
		this.firstNameKana = firstNameKana;
		this.companyName = companyName;
		this.companyNameKana = companyNameKana;
		this.departmentName = departmentName;
		this.positionName = positionName;
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
		this.leaveFlg = leaveFlg;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.deleteDate = deleteDate;
		this.deleteFlg = deleteFlg;
		this.userIndexNo=userIndexNo;
		
	}
	public UserInfoVo(Integer userId, Integer roleId, Integer teamId, String email, String name, String lastName,
			String firstName, String lastNameKana, String firstNameKana, String companyName, String companyNameKana,
			String departmentName, String positionName, String zipCode, String addressFull, String address1,
			String address2, String address3, String telNumberCompany, String telNumberDepartment,
			String telNumberDirect, String faxNumber, String mobileNumber, Integer leaveFlg, Date createDate,
			Date updateDate, Date deleteDate, Integer deleteFlg,String userIndexNo,Integer useStopFlg ) {
		super();
		this.userId = userId;
		this.roleId = roleId;
		this.teamId = teamId;
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.firstName = firstName;
		this.lastNameKana = lastNameKana;
		this.firstNameKana = firstNameKana;
		this.companyName = companyName;
		this.companyNameKana = companyNameKana;
		this.departmentName = departmentName;
		this.positionName = positionName;
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
		this.leaveFlg = leaveFlg;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.deleteDate = deleteDate;
		this.deleteFlg = deleteFlg;
		this.userIndexNo=userIndexNo;
		this.useStopFlg = useStopFlg;
		
	}
	public UserInfoVo(Integer userId, Integer roleId, Integer teamId, String email, String name, String lastName,
			String firstName, String lastNameKana, String firstNameKana, String companyName, String companyNameKana,
			String departmentName, String positionName, String zipCode, String addressFull, String address1,
			String address2, String address3, String telNumberCompany, String telNumberDepartment,
			String telNumberDirect, String faxNumber, String mobileNumber, Integer leaveFlg, Date createDate,
			Date updateDate, Date deleteDate, Integer deleteFlg) {
		super();
		this.userId = userId;
		this.roleId = roleId;
		this.teamId = teamId;
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.firstName = firstName;
		this.lastNameKana = lastNameKana;
		this.firstNameKana = firstNameKana;
		this.companyName = companyName;
		this.companyNameKana = companyNameKana;
		this.departmentName = departmentName;
		this.positionName = positionName;
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
		this.leaveFlg = leaveFlg;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.deleteDate = deleteDate;
		this.deleteFlg = deleteFlg;
		
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public Integer getTeamDivideCnt() {
		return teamDivideCnt;
	}
	public void setTeamDivideCnt(Integer teamDivideCnt) {
		this.teamDivideCnt = teamDivideCnt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getCompanyUrl() {
		return companyUrl;
	}
	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}
	public Integer getLeaveFlg() {
		return leaveFlg;
	}
	public void setLeaveFlg(Integer leaveFlg) {
		this.leaveFlg = leaveFlg;
	}
	
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	public Integer getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(Integer deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	public Integer getUseStopFlg() {
		return useStopFlg;
	}
	public void setUseStopFlg(Integer useStopFlg) {
		this.useStopFlg = useStopFlg;
	}
    
    
}
