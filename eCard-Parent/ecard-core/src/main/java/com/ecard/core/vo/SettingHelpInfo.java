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
public class SettingHelpInfo {
    private String title;
    private String mainText;
    private Date noticeDate;

    
    public SettingHelpInfo(){}
    
    public SettingHelpInfo(String title, String mainText, Date noticeDate){
        this.title = title;
        this.mainText = mainText;
        this.noticeDate = noticeDate;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the mainText
     */
    public String getMainText() {
        return mainText;
    }

    /**
     * @param mainText the mainText to set
     */
    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    /**
     * @return the noticeDate
     */
    public Date getNoticeDate() {
        return noticeDate;
    }

    /**
     * @param noticeDate the noticeDate to set
     */
    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }
}
