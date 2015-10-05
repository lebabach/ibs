package com.ecard.webapp.vo;

import java.util.Map;

import com.ecard.core.model.UserInfo;

public class OperatorEditVO {
private UserInfo user;
private Map<Integer,String> company;
private Map<Integer,String> department;
public UserInfo getUser() {
	return user;
}
public void setUser(UserInfo user) {
	this.user = user;
}
public Map<Integer, String> getCompany() {
	return company;
}
public void setCompany(Map<Integer, String> company) {
	this.company = company;
}
public Map<Integer, String> getDepartment() {
	return department;
}
public void setDepartment(Map<Integer, String> department) {
	this.department = department;
}
}
