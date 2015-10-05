/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.vo;

/**
 *
 * @author HienTu
 */
public class UserProfile extends AbstractCommonRes {
    private Integer userId;
    private Integer mailNoticeFlg;
    private Integer mailUseAssistFlg;
    private Integer mailNewsFlg;
    private Integer mailSendFlg;
    private Integer sfManualLinkFlg;
    private Integer firstLoginF;
    private Integer resetPassword;
    private CardInfoName cardInfoName;
    
    public UserProfile(){}
    
    public UserProfile(Integer mailNoticeFlg, Integer mailUseAssistFlg, Integer mailNewsFlg, Integer mailSendFlg, Integer sfManualLinkFlg){
        this.mailUseAssistFlg = mailUseAssistFlg;
        this.mailNoticeFlg = mailNoticeFlg;
        this.mailNewsFlg = mailNewsFlg;
        this.mailSendFlg = mailSendFlg;
        this.sfManualLinkFlg = sfManualLinkFlg;
    }
    
    public UserProfile(CardInfoName cardInfoName){
        this.cardInfoName = cardInfoName;
    }

    /**
     * @return the mailNoticeFlg
     */
    public Integer getMailNoticeFlg() {
        return mailNoticeFlg;
    }

    /**
     * @param mailNoticeFlg the mailNoticeFlg to set
     */
    public void setMailNoticeFlg(Integer mailNoticeFlg) {
        this.mailNoticeFlg = mailNoticeFlg;
    }

    /**
     * @return the mailUseAssistFlg
     */
    public Integer getMailUseAssistFlg() {
        return mailUseAssistFlg;
    }

    /**
     * @param mailUseAssistFlg the mailUseAssistFlg to set
     */
    public void setMailUseAssistFlg(Integer mailUseAssistFlg) {
        this.mailUseAssistFlg = mailUseAssistFlg;
    }

    /**
     * @return the mailNewsFlg
     */
    public Integer getMailNewsFlg() {
        return mailNewsFlg;
    }

    /**
     * @param mailNewsFlg the mailNewsFlg to set
     */
    public void setMailNewsFlg(Integer mailNewsFlg) {
        this.mailNewsFlg = mailNewsFlg;
    }

    /**
     * @return the mailSendFlg
     */
    public Integer getMailSendFlg() {
        return mailSendFlg;
    }

    /**
     * @param mailSendFlg the mailSendFlg to set
     */
    public void setMailSendFlg(Integer mailSendFlg) {
        this.mailSendFlg = mailSendFlg;
    }

    /**
     * @return the cardInfoName
     */
    public CardInfoName getCardInfoName() {
        return cardInfoName;
    }

    /**
     * @param cardInfoName the cardInfoName to set
     */
    public void setCardInfoName(CardInfoName cardInfoName) {
        this.cardInfoName = cardInfoName;
    }

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

    /**
     * @return the sfManualLinkFlg
     */
    public Integer getSfManualLinkFlg() {
        return sfManualLinkFlg;
    }

    /**
     * @param sfManualLinkFlg the sfManualLinkFlg to set
     */
    public void setSfManualLinkFlg(Integer sfManualLinkFlg) {
        this.sfManualLinkFlg = sfManualLinkFlg;
    }

    /**
     * @return the firstLoginF
     */
    public Integer getFirstLoginF() {
        return firstLoginF;
    }

    /**
     * @param firstLoginF the firstLoginF to set
     */
    public void setFirstLoginF(Integer firstLoginF) {
        this.firstLoginF = firstLoginF;
    }

    /**
     * @return the resetPassword
     */
    public Integer getResetPassword() {
        return resetPassword;
    }

    /**
     * @param resetPassword the resetPassword to set
     */
    public void setResetPassword(Integer resetPassword) {
        this.resetPassword = resetPassword;
    }
}
