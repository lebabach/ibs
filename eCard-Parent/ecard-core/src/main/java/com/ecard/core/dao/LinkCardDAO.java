package com.ecard.core.dao;

import java.util.List;

import com.ecard.core.model.UserInfo;

/**
*
* @author vinhla
*/
public interface LinkCardDAO {
	
	public int saveLinkCard(List<UserInfo> userInfoList);
	
	public int cleanLinkCardData();
}
