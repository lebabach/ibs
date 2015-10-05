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
public class NotificationList {
    private Integer notice_id;
    private Integer notice_type;
    private Integer card_id;
    private Integer change_param_type;
    private Integer read_flg;
    private String notify_message;
    private Date notice_date;
    
    public NotificationList(){}
    
    public NotificationList(Integer notice_id,Integer notice_type, Integer card_id,
                            Integer change_param_type, Integer read_flg, Date notice_date, String notify_message
                            ){
        this.notice_id = notice_id;
        this.notice_type = notice_type;
        this.card_id = card_id;
        this.change_param_type = change_param_type;
        this.read_flg = read_flg;
        this.notice_date = notice_date;
        this.notify_message = notify_message;
    }

    /**
     * @return the notice_id
     */
    public Integer getNotice_id() {
        return notice_id;
    }

    /**
     * @param notice_id the notice_id to set
     */
    public void setNotice_id(Integer notice_id) {
        this.notice_id = notice_id;
    }

    /**
     * @return the notice_typel
     */
    public Integer getNotice_type() {
        return notice_type;
    }

    /**
     * @param notice_type the notice_type to set
     */
    public void setNotice_type(Integer notice_type) {
        this.notice_type = notice_type;
    }

    /**
     * @return the card_id
     */
    public Integer getCard_id() {
        return card_id;
    }

    /**
     * @param card_id the card_id to set
     */
    public void setCard_id(Integer card_id) {
        this.card_id = card_id;
    }

    /**
     * @return the change_param_type
     */
    public Integer getChange_param_type() {
        return change_param_type;
    }

    /**
     * @param change_param_type the change_param_type to set
     */
    public void setChange_param_type(Integer change_param_type) {
        this.change_param_type = change_param_type;
    }

    /**
     * @return the read_flg
     */
    public Integer getRead_flg() {
        return read_flg;
    }

    /**
     * @param read_flg the read_flg to set
     */
    public void setRead_flg(Integer read_flg) {
        this.read_flg = read_flg;
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

	public String getNotify_message() {
		return notify_message;
	}

	public void setNotify_message(String notify_message) {
		this.notify_message = notify_message;
	}
}
