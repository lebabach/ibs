/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.dao;

import com.ecard.core.model.UserNotification;
import com.ecard.core.vo.NotificationList;
import com.ecard.core.vo.NotificationListManager;
import java.util.List;

/**
 *
 * @author HienTu
 */
public interface NotificationListDAO {
    public List<NotificationList> getListNotification(Integer userId, Integer page);
    
    public void updateListNotification(Integer userId);
    
    public List<NotificationListManager> listAllNofiticationManager();
    
    public int updateReadFlgById(UserNotification notify);
}
