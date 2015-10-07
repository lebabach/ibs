package com.ecard.webapp.vo;

public class UserInfoVO {
	private Integer userId;
	private String lastName;
    private String firstName;
    private String companyName;
    private String departmentName;
    private String positionName;
    private String name;
    private String email;
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

	public UserInfoVO(Integer userId,String lastName, String firstName, String companyName, String departmentName,
			String positionName) {
		super();
		this.userId=userId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.companyName = companyName;
		this.departmentName = departmentName;
		this.positionName = positionName;
	}

	public UserInfoVO() {
		// TODO Auto-generated constructor stub
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	
    
    
	
    
    
}
