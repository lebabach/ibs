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
public class HelpInfoResponse extends AbstractCommonRes {
    private List<SettingHelpInfo> helpList;

    /**
     * @return the helpList
     */
    public List<SettingHelpInfo> getHelpList() {
        return helpList;
    }

    /**
     * @param helpList the helpList to set
     */
    public void setHelpList(List<SettingHelpInfo> helpList) {
        this.helpList = helpList;
    }
    
}
