package com.ecard.core.service;

import java.util.List;

import com.ecard.core.model.UserInfo;

/**
*
* @author vinhla
*/
public interface LinkCardService {

	public int saveLinkCard(List<UserInfo> userInfoList);
	
	public int cleanLinkCardData();
}
