package com.ecard.webapp.vo;

import java.util.List;

import com.ecard.core.model.TeamInfo;

public class TeamListVO {
	//TODO: pagination variables
	private List<TeamInfo> teamList;
	private List<TeamInfoVO> teamInforVoList;
	public List<TeamInfoVO> getTeamInforVoList() {
		return teamInforVoList;
	}

	public void setTeamInforVoList(List<TeamInfoVO> teamInforVoList) {
		this.teamInforVoList = teamInforVoList;
	}

	/**
	 * @return the teamList
	 */
	public List<TeamInfo> getTeamList() {
		return teamList;
	}

	/**
	 * @param teamList the teamList to set
	 */
	public void setTeamList(List<TeamInfo> teamList) {
		this.teamList = teamList;
	}
}
