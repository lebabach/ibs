/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.dao.impl;

import com.ecard.core.dao.SettingsInfoDAO;
import com.ecard.core.model.InquiryInfo;
import com.ecard.core.model.PushInfoId;
import com.ecard.core.model.enums.EmailType;
import com.ecard.core.vo.MailSettings;
import com.ecard.core.vo.NotificationList;
import com.ecard.core.vo.SettingHelpInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HienTu
 */
@Repository("settingsInfoDAO")
public class SettingsInfoDAOImpl  extends GenericDao implements SettingsInfoDAO{
    @Value("${record.maxResult}")
    private Integer maxResult;
    
    public Boolean updateMailSendInfo(Integer userId, MailSettings mailSettings){
        String strQuery = new String();
        EmailType mailType = EmailType.valueOf(mailSettings.getType().name());

        switch(mailType.getValue()){
            case 1:
                strQuery = "UPDATE user_info ui SET ui.mail_news_flg= :sendFlg"
                        + " WHERE ui.user_id = :userId";
                break;
            case 2:
                strQuery = "UPDATE user_info ui SET ui.mail_use_assist_flg= :sendFlg "
                        + " WHERE ui.user_id = :userId";
                break;
            case 3:
                strQuery = "UPDATE user_info ui SET ui.mail_notice_flg= :sendFlg "
                        + " WHERE ui.user_id = :userId";
                break;
            case 4:
                strQuery = "UPDATE user_info ui SET ui.mail_send_flg= :sendFlg "
                        + " WHERE ui.user_id = :userId";
                break;
            default :
                strQuery = "UPDATE user_info ui SET ui.mail_send_flg= :sendFlg "
                        + " WHERE ui.user_id = :userId";
                break;
        }
        Query query = getEntityManager().createNativeQuery(strQuery);
               
        query.setParameter("userId", userId);
        query.setParameter("sendFlg", mailSettings.getSendFlg());
        
        int result = query.executeUpdate();
        if(result == 1)
            return true;
        else
            return false;
    }

    public Boolean registerPushId(PushInfoId pushInfo){
        Query query = getEntityManager().createNativeQuery("UPDATE push_info AS pi"
                + " SET pi.device_token = :deviceToken, pi.device_type = :deviceType, pi.update_date = NOW() "
                + " WHERE pi.user_id = :userId");
               
        query.setParameter("userId", pushInfo.getUserId());
        query.setParameter("deviceToken", pushInfo.getDeviceToken());
        query.setParameter("deviceType", pushInfo.getDeviceType());
        int result = query.executeUpdate();
        if(result == 1)
            return true;
        else
            return false;
    }
    
    public void createPushId(PushInfoId pushInfo){
        Query query = getEntityManager().createNativeQuery("INSERT INTO push_info (user_id, device_token, device_type, create_date, update_date )"
                + " VALUES(:userId, :deviceToken, :deviceType, :createDate, :updateDate)");
               
        query.setParameter("userId", pushInfo.getUserId());
        query.setParameter("deviceToken", pushInfo.getDeviceToken());
        query.setParameter("deviceType", pushInfo.getDeviceType());
        query.setParameter("createDate", pushInfo.getCreateDate());
        query.setParameter("updateDate", pushInfo.getUpdateDate());
        query.executeUpdate();

//        getEntityManager().merge(pushInfo);
//        getEntityManager().flush();
    }

    public List<SettingHelpInfo> getHelpInfo(){
         Query query = getEntityManager().createNativeQuery("SELECT hi.title, hi.main_text, hi.notice_date " 
                + " FROM help_info hi " 
                + " WHERE hi.notice_date >= CURDATE()" 
                + " ORDER BY hi.seq");
        
        List<Object[]> rows = query.getResultList();
        List<SettingHelpInfo> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new SettingHelpInfo((String)row[0],(String)row[1], (Date)row[2]));
        }
        return result;
    }
    
    public List<NotificationList> getSyncInfo(Integer userId){
        Query query = getEntityManager().createNativeQuery("SELECT un.notice_id, un.notice_type, un.card_id, un.change_param_type, un.read_flg, un.notice_date, un.notify_message  " 
                    + " FROM user_notification AS un " 
                    + " WHERE un.user_id = :userId");
        query.setParameter("userId", userId);
        query.setFirstResult(0);
        query.setMaxResults(this.maxResult);
        List<Object[]> rows = query.getResultList();
        List<NotificationList> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new NotificationList((Integer)row[0], (Integer)row[1], (Integer)row[2], (Integer)row[3], (Integer)row[4], (Date)row[5], (String)row[6]));
        }
        return result;
    }
    
    public void sendInquiry(InquiryInfo inquiryInfo){
        getEntityManager().persist(inquiryInfo);
        getEntityManager().flush();  
    }
    
    public Integer getPushId(Integer userId){
        Query query = getEntityManager().createNativeQuery("SELECT push_id "
                + " FROM push_info "
                + " WHERE user_id = :userId");
               
        query.setParameter("userId", userId);
        //List<Object[]> rows = query.getResultList();       
        return (Integer)getOrNull(query);

    }
}
