package com.ecard.core.model;
// Generated Aug 26, 2015 6:25:29 PM by Hibernate Tools 3.2.4.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * UserNotification generated by hbm2java
 */
@Entity
@Table(name="user_notification"
)
public class UserNotification  implements java.io.Serializable {


     private Integer noticeId;
     private UserInfo userInfo;
     private int noticeType;
     private Integer cardId;
     private Integer changeParamType;
     private Integer readFlg;
     private String notifyMessage;
     private Date noticeDate;

    public UserNotification() {
    }

	
    public UserNotification(UserInfo userInfo, int noticeType, String notifyMessage, Date noticeDate) {
        this.userInfo = userInfo;
        this.noticeType = noticeType;
        this.notifyMessage = notifyMessage;
        this.noticeDate = noticeDate;
    }
    public UserNotification(UserInfo userInfo, int noticeType, Integer cardId, Integer changeParamType, Integer readFlg, String notifyMessage, Date noticeDate) {
       this.userInfo = userInfo;
       this.noticeType = noticeType;
       this.cardId = cardId;
       this.changeParamType = changeParamType;
       this.readFlg = readFlg;
       this.notifyMessage = notifyMessage;
       this.noticeDate = noticeDate;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="notice_id", unique=true, nullable=false)
    public Integer getNoticeId() {
        return this.noticeId;
    }
    
    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    public UserInfo getUserInfo() {
        return this.userInfo;
    }
    
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    
    @Column(name="notice_type", nullable=false)
    public int getNoticeType() {
        return this.noticeType;
    }
    
    public void setNoticeType(int noticeType) {
        this.noticeType = noticeType;
    }

    
    @Column(name="card_id")
    public Integer getCardId() {
        return this.cardId;
    }
    
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    
    @Column(name="change_param_type")
    public Integer getChangeParamType() {
        return this.changeParamType;
    }
    
    public void setChangeParamType(Integer changeParamType) {
        this.changeParamType = changeParamType;
    }

    
    @Column(name="read_flg")
    public Integer getReadFlg() {
        return this.readFlg;
    }
    
    public void setReadFlg(Integer readFlg) {
        this.readFlg = readFlg;
    }

    
    @Column(name="notify_message", nullable=false, length=65535)
    public String getNotifyMessage() {
        return this.notifyMessage;
    }
    
    public void setNotifyMessage(String notifyMessage) {
        this.notifyMessage = notifyMessage;
    }

    
    @Column(name="notice_date", nullable=false, length=19)
    public Date getNoticeDate() {
        return this.noticeDate;
    }
    
    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }




}


