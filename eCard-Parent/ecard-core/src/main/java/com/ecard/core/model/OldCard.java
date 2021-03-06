package com.ecard.core.model;
// Generated Aug 4, 2015 10:15:08 AM by Hibernate Tools 3.2.4.GA


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
 * OldCard generated by hbm2java
 */
@Entity
@Table(name="old_card"
)
public class OldCard  implements java.io.Serializable {


     private Integer oldCardId;
     private CardInfo cardInfo;
     private int cardOwnerId;
     private int seq;

    public OldCard() {
    }

    public OldCard(CardInfo cardInfo, int cardOwnerId, int seq) {
       this.cardInfo = cardInfo;
       this.cardOwnerId = cardOwnerId;
       this.seq = seq;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="old_card_id", unique=true, nullable=false)
    public Integer getOldCardId() {
        return this.oldCardId;
    }
    
    public void setOldCardId(Integer oldCardId) {
        this.oldCardId = oldCardId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="card_id", nullable=false)
    public CardInfo getCardInfo() {
        return this.cardInfo;
    }
    
    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    
    @Column(name="card_owner_id", nullable=false)
    public int getCardOwnerId() {
        return this.cardOwnerId;
    }
    
    public void setCardOwnerId(int cardOwnerId) {
        this.cardOwnerId = cardOwnerId;
    }

    
    @Column(name="seq", nullable=false)
    public int getSeq() {
        return this.seq;
    }
    
    public void setSeq(int seq) {
        this.seq = seq;
    }




}


