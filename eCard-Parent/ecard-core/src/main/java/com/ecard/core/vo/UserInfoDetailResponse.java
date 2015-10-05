/*
 * UserInfoDetailResponse
 */
package com.ecard.core.vo;

/**
 *
 * @author vinhla
 */
public class UserInfoDetailResponse extends AbstractCommonRes{
    private UserInfoDetail userInfo;

    /**
     * @return the userInfo
     */
    public UserInfoDetail getUserInfo() {
        return userInfo;
    }

    /**
     * @param userInfo the userInfo to set
     */
    public void setUserInfo(UserInfoDetail userInfo) {
        this.userInfo = userInfo;
    }
}
