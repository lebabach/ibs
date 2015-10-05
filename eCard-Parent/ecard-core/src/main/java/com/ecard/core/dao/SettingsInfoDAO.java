/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.dao;

import com.ecard.core.model.InquiryInfo;
import com.ecard.core.model.PushInfoId;
import com.ecard.core.vo.MailSettings;
import com.ecard.core.vo.NotificationList;
import com.ecard.core.vo.SettingHelpInfo;
import java.util.List;

/**
 *
 * @author HienTu
 */
public interface SettingsInfoDAO {
    public Boolean updateMailSendInfo(Integer userId, MailSettings mailSettings);
    
    public Boolean registerPushId(PushInfoId pushInfo);
    
    public void createPushId(PushInfoId pushInfo);
    
    public List<SettingHelpInfo> getHelpInfo();
    
    public List<NotificationList> getSyncInfo(Integer userId);
    
    public void sendInquiry(InquiryInfo inquiryInfo);
    
    public Integer getPushId(Integer userId);
}

