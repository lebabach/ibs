package com.ecard.webapp.vo;

import java.util.List;

import com.ecard.core.model.UserInfo;

public class OperatorSearchVO {
	private String criteriaSearch;
	private List<UserInfo> userInfos;
	
	public OperatorSearchVO(String criteriaSearch, List<UserInfo> userInfos) {
		super();
		this.criteriaSearch = criteriaSearch;
		this.userInfos = userInfos;
	}
	public String getCriteriaSearch() {
		return criteriaSearch;
	}
	public void setCriteriaSearch(String criteriaSearch) {
		this.criteriaSearch = criteriaSearch;
	}
	public List<UserInfo> getUserInfos() {
		return userInfos;
	}
	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}
}
