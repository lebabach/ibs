package com.ecard.webapp.vo;

import java.util.List;

import com.ecard.core.vo.SearchInfo;

public class ObjectListSearchUsers {
	private boolean hasData;
	private List<SearchInfo> userSearchs;
	public boolean isHasData() {
		return hasData;
	}
	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}
	public List<SearchInfo> getUserSearchs() {
		return userSearchs;
	}
	public void setUserSearchs(List<SearchInfo> userSearchs) {
		this.userSearchs = userSearchs;
	}
	
}
