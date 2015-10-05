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
public class UpdatePassword {
    private String password;
    private String newPassword;
    private String newPasswordRetype;

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the newPasswordRetype
     */
    public String getNewPasswordRetype() {
        return newPasswordRetype;
    }

    /**
     * @param newPasswordRetype the newPasswordRetype to set
     */
    public void setNewPasswordRetype(String newPasswordRetype) {
        this.newPasswordRetype = newPasswordRetype;
    }
}
