/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.dao.impl;

import com.ecard.core.dao.NotificationListDAO;
import com.ecard.core.model.UserNotification;
import com.ecard.core.vo.NotificationList;
import com.ecard.core.vo.NotificationListManager;
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

@Repository("notificationListDAO")
public class NotificationListDAOImpl extends GenericDao implements NotificationListDAO {
    @Value("${record.maxResult}")
    private Integer maxResult;
    
    public List<NotificationList> getListNotification(Integer userId){
        // Get user update info
//        Query query = getEntityManager().createQuery("SELECT NEW com.ecard.core.vo.NotificationList(un.noticeId, un.noticeType, un.cardId, un.changeParamType, un.readFlg, un.noticeDate) "       
//                + " FROM UserNotification AS un"
//                + " WHERE un.userInfo.userId= :userId AND un.readFlg = 0");
        Query query = getEntityManager().createNativeQuery("SELECT un.notice_id, un.notice_type, un.card_id, un.change_param_type, un.read_flg, un.notice_date, un.notify_message " 
                    + " FROM user_notification AS un " 
                    + " WHERE un.user_id = :userId "
                    + " ORDER BY un.notice_date DESC");
        query.setParameter("userId", userId);        
        List<Object[]> rows = query.getResultList();
        List<NotificationList> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new NotificationList((Integer)row[0], (Integer)row[1], (Integer)row[2], (Integer)row[3], (Integer)row[4], (Date)row[5], (String)row[6]));
        }
        return result;
    }
    
    public List<NotificationList> getListNotificationPaging(Integer userId, Integer page){
        // Get user update info
//        Query query = getEntityManager().createQuery("SELECT NEW com.ecard.core.vo.NotificationList(un.noticeId, un.noticeType, un.cardId, un.changeParamType, un.readFlg, un.noticeDate) "       
//                + " FROM UserNotification AS un"
//                + " WHERE un.userInfo.userId= :userId AND un.readFlg = 0");
        Query query = getEntityManager().createNativeQuery("SELECT un.notice_id, un.notice_type, un.card_id, un.change_param_type, un.read_flg, un.notice_date, un.notify_message " 
                    + " FROM user_notification AS un " 
                    + " WHERE un.user_id = :userId "
                    + " ORDER BY un.notice_date DESC");
        query.setParameter("userId", userId);
        query.setFirstResult(page* this.maxResult);
        query.setMaxResults(this.maxResult);
        List<Object[]> rows = query.getResultList();
        List<NotificationList> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new NotificationList((Integer)row[0], (Integer)row[1], (Integer)row[2], (Integer)row[3], (Integer)row[4], (Date)row[5], (String)row[6]));
        }
        return result;
    }
    
    public void updateListNotification(Integer userId){
        // Set user update info from flag 0 -> 1
        Query query = getEntityManager().createNativeQuery("UPDATE user_notification AS un "
                + "SET un.read_flg = 1 "
                + "WHERE un.user_id= :userId AND un.read_flg = 0");
        query.setParameter("userId", userId);        
        query.executeUpdate();
    }
    
    public List<NotificationListManager> listAllNofiticationManager(){
        Query query = getEntityManager().createQuery("SELECT new com.ecard.core.vo.NotificationListManager(mn.managerNoticeId, mn.noticeTitle, mn.noticeText, mn.noticeDate)"
                + " FROM ManagerNotice AS mn "
                + " WHERE mn.publishStatus = 1 AND mn.noticeDate >= CURDATE()"
                + " ORDER BY mn.noticeDate DESC");
        query.setFirstResult(0);
        query.setMaxResults(this.maxResult);        
        
        return query.getResultList();       
        
    }
    
    public int updateReadFlgById(UserNotification notify){
        Query query = getEntityManager().createQuery("UPDATE UserNotification u SET u.readFlg = :readFlg WHERE u.noticeId = :noticeId");
        query.setParameter("noticeId", notify.getNoticeId());
        query.setParameter("readFlg", notify.getReadFlg());
        return (int)query.executeUpdate();
    }
}
