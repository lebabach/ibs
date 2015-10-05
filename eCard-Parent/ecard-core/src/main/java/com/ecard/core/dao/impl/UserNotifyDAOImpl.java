/*
 * UserNotifyDAOImpl
 */
package com.ecard.core.dao.impl;

import com.ecard.core.dao.UserNotifyDAO;
import com.ecard.core.model.UserNotification;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vinhla
 */
@Repository("userNotifyDAO")
public class UserNotifyDAOImpl extends GenericDao implements UserNotifyDAO{
    
    public void createUserNotify(UserNotification userNotification) {
        getEntityManager().persist(userNotification);
        getEntityManager().flush();
    }
}
