/*
 * UserNotifyServiceImpl
 */
package com.ecard.core.service.impl;

import com.ecard.core.dao.UserNotifyDAO;
import com.ecard.core.model.UserNotification;
import com.ecard.core.service.UserNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vinhla
 */
@Service("userNotifyService")
@Transactional
public class UserNotifyServiceImpl implements UserNotifyService{
    
    @Autowired
    UserNotifyDAO userNotifyDAO;
    
    public void createUserNotify(UserNotification userNotification) {
        userNotifyDAO.createUserNotify(userNotification);
    }
}
