/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.service;

import com.ecard.core.model.UserNotification;
import com.ecard.core.vo.NotificationList;
import com.ecard.core.vo.NotificationListManager;
import java.util.List;

/**
 *
 * @author HienTu
 */
public interface NotificationInfoService {
    public List<NotificationList> listAllNofiticationUser(Integer userId);
    
    public void updateListAllNotificationUser(Integer userId);
    
    public List<NotificationListManager> listAllNofiticationManager();
    
    public int updateReadFlgById(UserNotification notify);
}
