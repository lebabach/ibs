/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.service.impl;

import com.ecard.core.dao.SettingsInfoDAO;
import com.ecard.core.model.InquiryInfo;
import com.ecard.core.model.PushInfoId;
import com.ecard.core.service.SettingsInfoService;
import com.ecard.core.vo.MailSettings;
import com.ecard.core.vo.NotificationList;
import com.ecard.core.vo.SettingHelpInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HienTu
 */
@Service("settingsInfoService")
@Transactional
public class SettingsInfoServiceImpl implements SettingsInfoService {
    @Autowired
    SettingsInfoDAO settingsInfoDAO;
    
    public Boolean updateMailSendInfo(Integer userId, MailSettings mailSettings){
        return settingsInfoDAO.updateMailSendInfo(userId, mailSettings);
    }
    
    public Boolean registerPushId(PushInfoId pushInfo){
        return settingsInfoDAO.registerPushId(pushInfo);
    }
    
    public Integer getPushId(Integer userId){
        return settingsInfoDAO.getPushId(userId);
    }
    public void createPushId(PushInfoId pushInfo){
        settingsInfoDAO.createPushId(pushInfo);
    }
    
    public List<SettingHelpInfo> getHelpInfo(){
        return settingsInfoDAO.getHelpInfo();
    }
    
    public List<NotificationList> getSyncInfo(Integer userId){
        return settingsInfoDAO.getSyncInfo(userId);
    }
    
    public void sendInquiry(InquiryInfo inquiryInfo){
        settingsInfoDAO.sendInquiry(inquiryInfo);
    }
    
  
            
}
