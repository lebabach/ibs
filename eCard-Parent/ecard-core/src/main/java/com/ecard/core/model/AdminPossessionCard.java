package com.ecard.core.model;
// Generated Sep 11, 2015 9:57:46 AM by Hibernate Tools 3.2.4.GA


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AdminPossessionCard generated by hbm2java
 */
@Entity
@Table(name="admin_possession_card"
)
public class AdminPossessionCard  implements java.io.Serializable {


     private AdminPossessionCardId id;
     private UserInfo userInfo;
     private CardInfo cardInfo;

    public AdminPossessionCard() {
    }

	
    public AdminPossessionCard(AdminPossessionCardId id, CardInfo cardInfo) {
        this.id = id;
        this.cardInfo = cardInfo;
    }
    public AdminPossessionCard(AdminPossessionCardId id, UserInfo userInfo, CardInfo cardInfo) {
       this.id = id;
       this.userInfo = userInfo;
       this.cardInfo = cardInfo;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="userId", column=@Column(name="user_id") ), 
        @AttributeOverride(name="cardId", column=@Column(name="card_id", nullable=false) ), 
        @AttributeOverride(name="createDate", column=@Column(name="create_date", length=19) ) } )
    public AdminPossessionCardId getId() {
        return this.id;
    }
    
    public void setId(AdminPossessionCardId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", insertable=false, updatable=false)
    public UserInfo getUserInfo() {
        return this.userInfo;
    }
    
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="card_id", nullable=false, insertable=false, updatable=false)
    public CardInfo getCardInfo() {
        return this.cardInfo;
    }
    
    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }




}


