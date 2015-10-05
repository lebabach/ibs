package com.ecard.webapp.vo;

public class UserVo {
	private Integer userId;
	private Integer userId1;
	private Integer userId2;
	private String name;
	private String name1;
	private String name2;
	private int currentCount;
	private int  targetCount;
	private int totalCardTeam;
	private Integer teamdivide;
	private Integer teamdivide1;
	private Integer teamdivide2;
	public UserVo(String name, String name1, String name2,Integer userId,Integer userId1,Integer userId2,int currentCount,int targetCount,int totalCardTeam, 
				Integer teamdivide, Integer teamdivide1, Integer teamdivide2) {
		super();
		this.name = name;
		this.name1 = name1;
		this.name2 = name2;
		this.userId = userId;
		this.userId1 = userId1;
		this.userId2 = userId2;
		this.currentCount = currentCount;
		this.targetCount = targetCount;
		this.totalCardTeam = totalCardTeam;
		this.teamdivide = teamdivide;
		this.teamdivide1 = teamdivide1;
		this.teamdivide2 = teamdivide2;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserId1() {
		return userId1;
	}
	public void setUserId1(Integer userId1) {
		this.userId1 = userId1;
	}
	public Integer getUserId2() {
		return userId2;
	}
	public void setUserId2(Integer userId2) {
		this.userId2 = userId2;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	public int getTargetCount() {
		return targetCount;
	}
	public void setTargetCount(int targetCount) {
		this.targetCount = targetCount;
	}
	public int getTotalCardTeam() {
		return totalCardTeam;
	}
	public void setTotalCardTeam(int totalCardTeam) {
		this.totalCardTeam = totalCardTeam;
	}

	public Integer getTeamdivide() {
		return teamdivide;
	}

	public void setTeamdivide(Integer teamdivide) {
		this.teamdivide = teamdivide;
	}

	public Integer getTeamdivide1() {
		return teamdivide1;
	}

	public void setTeamdivide1(Integer teamdivide1) {
		this.teamdivide1 = teamdivide1;
	}

	public Integer getTeamdivide2() {
		return teamdivide2;
	}

	public void setTeamdivide2(Integer teamdivide2) {
		this.teamdivide2 = teamdivide2;
	}

	
	
	
	
	
	
	

}
