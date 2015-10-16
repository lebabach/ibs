package com.ecard.core.model;
// Generated Oct 16, 2015 2:05:20 PM by Hibernate Tools 3.2.4.GA


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
 * ContactHistory generated by hbm2java
 */
@Entity
@Table(name="contact_history"
)
public class ContactHistory  implements java.io.Serializable {


     private Integer contactHistoryId;
     private CardInfo cardInfo;
     private int userId;
     private Date contactDate;
     private String title;
     private String place;
     private String contactMemo;

    public ContactHistory() {
    }

	
    public ContactHistory(CardInfo cardInfo, int userId) {
        this.cardInfo = cardInfo;
        this.userId = userId;
    }
    public ContactHistory(CardInfo cardInfo, int userId, Date contactDate, String title, String place, String contactMemo) {
       this.cardInfo = cardInfo;
       this.userId = userId;
       this.contactDate = contactDate;
       this.title = title;
       this.place = place;
       this.contactMemo = contactMemo;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="contact_history_id", unique=true, nullable=false)
    public Integer getContactHistoryId() {
        return this.contactHistoryId;
    }
    
    public void setContactHistoryId(Integer contactHistoryId) {
        this.contactHistoryId = contactHistoryId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="card_id", nullable=false)
    public CardInfo getCardInfo() {
        return this.cardInfo;
    }
    
    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    
    @Column(name="user_id", nullable=false)
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    @Column(name="contact_date", length=19)
    public Date getContactDate() {
        return this.contactDate;
    }
    
    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }

    
    @Column(name="title", length=65535)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    
    @Column(name="place", length=65535)
    public String getPlace() {
        return this.place;
    }
    
    public void setPlace(String place) {
        this.place = place;
    }

    
    @Column(name="contact_memo", length=65535)
    public String getContactMemo() {
        return this.contactMemo;
    }
    
    public void setContactMemo(String contactMemo) {
        this.contactMemo = contactMemo;
    }




}


