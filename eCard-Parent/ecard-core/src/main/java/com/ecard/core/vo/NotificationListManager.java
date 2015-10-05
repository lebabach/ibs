/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.vo;

import java.util.Date;

/**
 *
 * @author HienTu
 */
public class NotificationListManager {
    private Integer manager_notice_id;
    private String notice_title;
    private String notice_text;
    private Date notice_date;
    
    public NotificationListManager(){}
    
    public NotificationListManager(Integer manager_notice_id, String notice_title,
                                    String notice_text, Date notice_date){
        this.manager_notice_id = manager_notice_id;
        this.notice_title = notice_title;
        this.notice_text = notice_text;
        this.notice_date = notice_date;
    }

    /**
     * @return the manager_notice_id
     */
    public Integer getManager_notice_id() {
        return manager_notice_id;
    }

    /**
     * @param manager_notice_id the manager_notice_id to set
     */
    public void setManager_notice_id(Integer manager_notice_id) {
        this.manager_notice_id = manager_notice_id;
    }

    /**
     * @return the notice_title
     */
    public String getNotice_title() {
        return notice_title;
    }

    /**
     * @param notice_title the notice_title to set
     */
    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    /**
     * @return the notice_text
     */
    public String getNotice_text() {
        return notice_text;
    }

    /**
     * @param notice_text the notice_text to set
     */
    public void setNotice_text(String notice_text) {
        this.notice_text = notice_text;
    }

    /**
     * @return the notice_date
     */
    public Date getNotice_date() {
        return notice_date;
    }

    /**
     * @param notice_date the notice_date to set
     */
    public void setNotice_date(Date notice_date) {
        this.notice_date = notice_date;
    }
}
