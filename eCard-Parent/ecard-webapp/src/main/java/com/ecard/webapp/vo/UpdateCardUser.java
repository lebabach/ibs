package com.ecard.webapp.vo;

import java.util.List;

public class UpdateCardUser {
	String userLeave;
	String userAssign;
	boolean checkAll;
	List<Integer> listCardId;

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

}
