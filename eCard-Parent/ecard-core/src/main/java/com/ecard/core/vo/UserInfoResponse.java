/**
 * UserInfoResponse class
 */
package com.ecard.core.vo;

import java.util.List;

/**
 * @author vinhla
 *
 */
public class UserInfoResponse extends AbstractCommonRes {
	private List<UserInfo> userInfoList;

	public List<UserInfo> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}

}
