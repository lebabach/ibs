/**
 * 
 */
package com.ecard.core.service.converter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;

import com.ecard.core.vo.UserInfo;
import com.ecard.core.vo.UserInfoDetail;

/**
 * @author Windows
 *
 */
public final class UserInfoConverter {
    public static List<UserInfo>  convertUserInforList(List<com.ecard.core.model.UserInfo> userInfoModelList) throws IllegalAccessException, InvocationTargetException{
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        if (CollectionUtils.isNotEmpty(userInfoModelList)){
            UserInfo userInfo;
            for (com.ecard.core.model.UserInfo userInfoModel : userInfoModelList) {
                userInfo = new UserInfo();
                BeanUtils.copyProperties(userInfo, userInfoModel);
                userInfoList.add(userInfo);
            }
        }
        return userInfoList;
    }
    
    public static UserInfoDetail convertUserInforDetailList(com.ecard.core.model.UserInfo userInfoModel) throws IllegalAccessException, InvocationTargetException{
        UserInfoDetail userInfo = new UserInfoDetail();
        if (userInfoModel != null){
            userInfo.setGroupCompanyId(userInfoModel.getGroupCompanyId());
            userInfo.setLastName(userInfoModel.getLastName());
            userInfo.setFirstName(userInfoModel.getFirstName());
            userInfo.setFirstNameKana(userInfoModel.getFirstNameKana());
            userInfo.setLastNameKana(userInfoModel.getLastNameKana());
            userInfo.setCompanyName(userInfoModel.getCompanyName());
            userInfo.setCompanyNameKana(userInfoModel.getCompanyNameKana());
            userInfo.setDepartmentName(userInfoModel.getDepartmentName());
            userInfo.setPositionName(userInfoModel.getPositionName());
            userInfo.setEmail(userInfoModel.getEmail());
            userInfo.setTelNumberCompany(userInfoModel.getTelNumberCompany());
            userInfo.setTelNumberDepartment(userInfoModel.getTelNumberDepartment());
            userInfo.setTelNumberDirect(userInfoModel.getTelNumberDirect());
            userInfo.setFaxNumber(userInfoModel.getFaxNumber());
            userInfo.setMobileNumber(userInfoModel.getMobileNumber());
            userInfo.setZipCode(userInfoModel.getZipCode());
            userInfo.setAddress1(userInfoModel.getAddress1());
            userInfo.setAddress2(userInfoModel.getAddress2());
            userInfo.setAddress3(userInfoModel.getAddress3());
            userInfo.setCompanyUrl(userInfoModel.getCompanyUrl());
            userInfo.setMemo1(userInfoModel.getMemo1());
            userInfo.setMemo2(userInfoModel.getMemo2());
            userInfo.setCreateDate(userInfoModel.getCreateDate());
        }
        return userInfo;
    }
    
}
