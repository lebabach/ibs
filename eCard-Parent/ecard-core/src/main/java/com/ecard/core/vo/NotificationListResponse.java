/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author HienTu
 */

public class NotificationListResponse extends AbstractCommonRes {
    private List<NotificationList> updateInfoList;

    /**
     * @return the updateInfoList
     */
    public List<NotificationList> getUpdateInfoList() {
        return updateInfoList;
    }

    /**
     * @param updateInfoList the updateInfoList to set
     */
    public void setUpdateInfoList(List<NotificationList> updateInfoList) {
        this.updateInfoList = updateInfoList;
    }
    
    
}
