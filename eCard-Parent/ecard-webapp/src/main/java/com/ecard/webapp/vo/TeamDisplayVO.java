package com.ecard.webapp.vo;

import java.util.List;


import com.ecard.core.model.TeamInfo;
import com.ecard.core.model.UserInfo;

public class TeamDisplayVO {
	//old 
	private List<UserInfo> userInfos;
	//private List<OperaterActionHistory> operaterActionHistories;
	private TeamInfo teamInfo;
	private List<UserInfo> usersInTeam;
	public List<UserInfo> getUsersInTeam() {
		return usersInTeam;
	}
	public void setUsersInTeam(List<UserInfo> usersInTeam) {
		this.usersInTeam = usersInTeam;
	}
	public List<UserInfo> getUserInfos() {
		return userInfos;
	}
	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}
	/*public List<OperaterActionHistory> getOperaterActionHistories() {
		return operaterActionHistories;
	}
	public void setOperaterActionHistories(List<OperaterActionHistory> operaterActionHistories) {
		this.operaterActionHistories = operaterActionHistories;
	}*/
	public TeamInfo getTeamInfo() {
		return teamInfo;
	}
	public void setTeamInfo(TeamInfo teamInfo) {
		this.teamInfo = teamInfo;
	}
	
	//bach.le new team display
	private String action;
	private String name;
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
