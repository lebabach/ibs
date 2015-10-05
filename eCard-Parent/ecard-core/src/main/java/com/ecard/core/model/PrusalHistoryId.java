package com.ecard.core.model;
// Generated Aug 4, 2015 10:15:08 AM by Hibernate Tools 3.2.4.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PrusalHistoryId generated by hbm2java
 */
@Embeddable
public class PrusalHistoryId  implements java.io.Serializable {


     private int userId;
     private int cardId;
     private Date prusalDate;

    public PrusalHistoryId() {
    }

    public PrusalHistoryId(int userId, int cardId, Date prusalDate) {
       this.userId = userId;
       this.cardId = cardId;
       this.prusalDate = prusalDate;
    }
   


    @Column(name="user_id", nullable=false)
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Column(name="card_id", nullable=false)
    public int getCardId() {
        return this.cardId;
    }
    
    public void setCardId(int cardId) {
        this.cardId = cardId;
    }


    @Column(name="prusal_date", nullable=false, length=19)
    public Date getPrusalDate() {
        return this.prusalDate;
    }
    
    public void setPrusalDate(Date prusalDate) {
        this.prusalDate = prusalDate;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PrusalHistoryId) ) return false;
		 PrusalHistoryId castOther = ( PrusalHistoryId ) other; 
         
		 return (this.getUserId()==castOther.getUserId())
 && (this.getCardId()==castOther.getCardId())
 && ( (this.getPrusalDate()==castOther.getPrusalDate()) || ( this.getPrusalDate()!=null && castOther.getPrusalDate()!=null && this.getPrusalDate().equals(castOther.getPrusalDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getUserId();
         result = 37 * result + this.getCardId();
         result = 37 * result + ( getPrusalDate() == null ? 0 : this.getPrusalDate().hashCode() );
         return result;
   }   


}


