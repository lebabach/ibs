package com.ecard.webapp.vo;

import java.util.Date;
import java.util.List;

public class TeamInfoVO {
	 private Integer teamId;
     private String teamName;
     private Integer targetCount;
     private Date createDate;
     private Date updateDate;
     private Integer operaterId;
     private List<UserInfoVO> userInfos;
     private Integer userCount;
	public Integer getUserCount() {
		return userCount;
	}
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Integer getTargetCount() {
		return targetCount;
	}
	public void setTargetCount(Integer targetCount) {
		this.targetCount = targetCount;
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
	public Integer getOperaterId() {
		return operaterId;
	}
	public void setOperaterId(Integer operaterId) {
		this.operaterId = operaterId;
	}
	public List<UserInfoVO> getUserInfos() {
		return userInfos;
	}
	public void setUserInfos(List<UserInfoVO> userInfos) {
		this.userInfos = userInfos;
	}

}
