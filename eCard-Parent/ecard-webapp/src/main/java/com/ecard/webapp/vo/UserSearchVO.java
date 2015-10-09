package com.ecard.webapp.vo;


public class UserSearchVO {
    public String getFreeText() {
		return freeText;
	}
	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String freeText;
    private String owner;
    private String company;
    private String department;
    private String position;
    private String name;
    private int parameterFlg;
    
    private int page;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getParameterFlg() {
		return parameterFlg;
	}
	public void setParameterFlg(int parameterFlg) {
		this.parameterFlg = parameterFlg;
	}
	
}
