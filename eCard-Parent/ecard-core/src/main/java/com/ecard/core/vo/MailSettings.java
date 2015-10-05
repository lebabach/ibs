/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.vo;

import com.ecard.core.model.enums.EmailType;

/**
 *
 * @author HienTu
 */
public class MailSettings {
    private EmailType type;
    private Integer sendFlg;

    /**
     * @return the sendFlg
     */
    public Integer getSendFlg() {
        return sendFlg;
    }

    /**
     * @param sendFlg the sendFlg to set
     */
    public void setSendFlg(Integer sendFlg) {
        this.sendFlg = sendFlg;
    }

    /**
     * @return the type
     */
    public EmailType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(EmailType type) {
        this.type = type;
    }
    
    
}
