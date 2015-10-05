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
public class NotificationListManagerResponse extends AbstractCommonRes {
    private List<NotificationListManager> updateInfoList;

    /**
     * @return the updateInfoList
     */
    public List<NotificationListManager> getUpdateInfoList() {
        return updateInfoList;
    }

    /**
     * @param updateInfoList the updateInfoList to set
     */
    public void setUpdateInfoList(List<NotificationListManager> updateInfoList) {
        this.updateInfoList = updateInfoList;
    }  
}
