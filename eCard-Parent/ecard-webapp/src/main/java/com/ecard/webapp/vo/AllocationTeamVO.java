package com.ecard.webapp.vo;

import java.util.List;

import com.ecard.core.model.TeamInfo;
import com.ecard.core.vo.UserInfoVo;

public class AllocationTeamVO {
	List<TeamInfo> teamList;
	List<UserInfoVo> listUser;
	List<UserVo> lstUserVo;
	List<Integer> listCard ;
	long totalCard;
	public AllocationTeamVO(List<TeamInfo> teamList, long totalCard,List<UserInfoVo> listUser,List<UserVo> lstUserVo,List<Integer> listCard ) {
		super();
		this.teamList = teamList;
		this.totalCard = totalCard;
		this.listUser = listUser;
		this.lstUserVo = lstUserVo;
		this.listCard = listCard;
	}
	public List<TeamInfo> getTeamList() {
		return teamList;
	}
	public void setTeamList(List<TeamInfo> teamList) {
		this.teamList = teamList;
	}
	public long getTotalCard() {
		return totalCard;
	}
	public void setTotalCard(long totalCard) {
		this.totalCard = totalCard;
	}
	public List<UserInfoVo> getListUser() {
		return listUser;
	}
	public void setListUser(List<UserInfoVo> listUser) {
		this.listUser = listUser;
	}
	public List<UserVo> getLstUserVo() {
		return lstUserVo;
	}
	public void setLstUserVo(List<UserVo> lstUserVo) {
		this.lstUserVo = lstUserVo;
	}
	public List<Integer> getListCard() {
		return listCard;
	}
	public void setListCard(List<Integer> listCard) {
		this.listCard = listCard;
	}
	
	
	
	
}
