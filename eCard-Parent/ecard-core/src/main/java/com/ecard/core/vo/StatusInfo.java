/**
 * 
 */
package com.ecard.core.vo;

/**
 * @author vinhla
 *
 */
public class StatusInfo {
    private Integer status;
    private String code;
    private String message;
    private String token;

    public StatusInfo(){}
    public StatusInfo(Integer status, String code, String message, String token){
    	this.status = status;
    	this.code = code;
    	this.message = message;
    	this.token = token;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return message;
    }
    public void setMsg(String message) {
        this.message = message;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
}
