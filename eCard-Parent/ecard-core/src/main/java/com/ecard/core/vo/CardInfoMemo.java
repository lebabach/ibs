/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.vo;

import java.util.Date;

/**
 *
 * @author admin
 */
public class CardInfoMemo {
    private String memo;
    private Integer seq;
    private Integer userId;
    private Date create_date;
    
    public CardInfoMemo (){}
    
    public CardInfoMemo (String memo, Integer seq){
        this.memo = memo;
        this.seq = seq;
    }
    
    public CardInfoMemo (String memo, Integer seq, Date create_date){
        this.memo = memo;
        this.seq = seq;
        this.create_date = create_date;
    }
    
    public CardInfoMemo (Integer userId, String memo, Integer seq, Date create_date){
        this.userId = userId;
        this.memo = memo;
        this.seq = seq;
        this.create_date = create_date;
    }
    

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return the seq
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * @param seq the seq to set
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * @return the create_date
     */
    public Date getCreate_date() {
        return create_date;
    }

    /**
     * @param create_date the create_date to set
     */
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
}
