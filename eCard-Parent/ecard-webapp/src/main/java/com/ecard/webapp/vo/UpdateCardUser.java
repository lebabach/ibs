package com.ecard.webapp.vo;

import java.util.List;

public class UpdateCardUser {
	String userLeave;
	String userAssign;
	boolean checkAll;
	List<Integer> listCardId;
	List<Integer> listUncheckAll;
	String nameAssign;
	Integer tagId;

	public String getUserLeave() {
		return userLeave;
	}

	public void setUserLeave(String userLeave) {
		this.userLeave = userLeave;
	}

	public String getUserAssign() {
		return userAssign;
	}

	public void setUserAssign(String userAssign) {
		this.userAssign = userAssign;
	}

	public boolean isCheckAll() {
		return checkAll;
	}

	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	public List<Integer> getListCardId() {
		return listCardId;
	}

	public void setListCardId(List<Integer> listCardId) {
		this.listCardId = listCardId;
	}

	public List<Integer> getListUncheckAll() {
		return listUncheckAll;
	}

	public void setListUncheckAll(List<Integer> listUncheckAll) {
		this.listUncheckAll = listUncheckAll;
	}

	public String getNameAssign() {
		return nameAssign;
	}

	public void setNameAssign(String nameAssign) {
		this.nameAssign = nameAssign;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	
	

}
