package com.ecard.core.vo;

import java.math.BigInteger;

import org.hibernate.sql.Template;

public class TeamDisInfo {
	private String teamName;
	private Integer processNumber;
	private BigInteger memberNumber;
	private Integer stateProgress;
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Integer getProcessNumber() {
		return processNumber;
	}
	public void setProcessNumber(Integer processNumber) {
		this.processNumber = processNumber;
	}
	public BigInteger getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(BigInteger memberNumber) {
		this.memberNumber = memberNumber;
	}
	public Integer getStateProgress() {
		return stateProgress;
	}
	public void setStateProgress(Integer stateProgress) {
		this.stateProgress = stateProgress;
	}


	public TeamDisInfo(){}
	public TeamDisInfo(String teamName,Integer processNumber,BigInteger memberNumber,Integer stateProgress){
		this.teamName= teamName;
		this.processNumber = processNumber;
		this.memberNumber = memberNumber;
		this.stateProgress = stateProgress;
	}
	
	
}
