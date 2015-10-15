/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.service.impl;

import com.ecard.core.dao.NotificationListDAO;
import com.ecard.core.model.UserNotification;
import com.ecard.core.service.NotificationInfoService;
import com.ecard.core.vo.NotificationList;
import com.ecard.core.vo.NotificationListManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HienTu
 */

@Service("notificationInfoService")
@Transactional
public class NotificationInfoServiceImpl implements NotificationInfoService {
    @Autowired
    NotificationListDAO notificationListDAO;
    
//    @Autowired
//    NotifactionListManagerDAO notifactionListManagerDAO;
    public List<NotificationList> listAllNofiticationUser(Integer userId){
        return notificationListDAO.getListNotification(userId);
    }
    
    public void updateListAllNotificationUser(Integer userId){
        notificationListDAO.updateListNotification(userId);
    }
    
    public List<NotificationListManager> listAllNofiticationManager(){
        return notificationListDAO.listAllNofiticationManager();
    }
    
    public int updateReadFlgById(UserNotification notify){
        return notificationListDAO.updateReadFlgById(notify);
    }
    
    public boolean deleteAllNotify(List<NotificationList> notifies){
    	UserNotification notify = null;
    	for(NotificationList item:notifies){
			notify = new UserNotification();
			notify.setNoticeId(item.getNotice_id());
			notify.setReadFlg(1);
			this.updateReadFlgById(notify);
		}
    	return true;
    }
}
