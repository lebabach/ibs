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
public class UpdateUserEmail {
    private String password;
    private String email;
    private String emailRetype;

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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the emailRetype
     */
    public String getEmailRetype() {
        return emailRetype;
    }

    /**
     * @param emailRetype the emailRetype to set
     */
    public void setEmailRetype(String emailRetype) {
        this.emailRetype = emailRetype;
    }
    
    
}
