package com.ecard.webapp.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class ObjectTeamVO implements Serializable{
	private static final long serialVersionUID = 1L;
	ArrayList<ObjectMembers> listUser;
	ArrayList<ObjectCards> listCardId;
	int teamId;
	int target_count;
	int current_count;
	ArrayList<ObjectMembers> listNotExitedUser;
	String teamName;
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public ArrayList<ObjectMembers> getListNotExitedUser() {
		return listNotExitedUser;
	}
	public void setListNotExitedUser(ArrayList<ObjectMembers> listNotExitedUser) {
		this.listNotExitedUser = listNotExitedUser;
	}
	public ArrayList<ObjectMembers> getListUser() {
		return listUser;
	}
	public void setListUser(ArrayList<ObjectMembers> listUser) {
		this.listUser = listUser;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getTarget_count() {
		return target_count;
	}
	public void setTarget_count(int target_count) {
		this.target_count = target_count;
	}
	public int getCurrent_count() {
		return current_count;
	}
	public void setCurrent_count(int current_count) {
		this.current_count = current_count;
	}
	public ArrayList<ObjectCards> getListCardId() {
		return listCardId;
	}
	public void setListCardId(ArrayList<ObjectCards> listCardId) {
		this.listCardId = listCardId;
	}
	
	
}
