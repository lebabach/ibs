package com.ecard.core.model;
// Generated Aug 4, 2015 10:15:08 AM by Hibernate Tools 3.2.4.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CardUpdateHistoryId generated by hbm2java
 */
@Embeddable
public class CardUpdateHistoryId  implements java.io.Serializable {


     private int cardId;
     private String positionName;
     private Date createDate;
     private Date updateDate;
     private int operaterId;

    public CardUpdateHistoryId() {
    }

	
    public CardUpdateHistoryId(int cardId, Date createDate, Date updateDate, int operaterId) {
        this.cardId = cardId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.operaterId = operaterId;
    }
    public CardUpdateHistoryId(int cardId, String positionName, Date createDate, Date updateDate, int operaterId) {
       this.cardId = cardId;
       this.positionName = positionName;
       this.createDate = createDate;
       this.updateDate = updateDate;
       this.operaterId = operaterId;
    }
   


    @Column(name="card_id", nullable=false)
    public int getCardId() {
        return this.cardId;
    }
    
    public void setCardId(int cardId) {
        this.cardId = cardId;
    }


    @Column(name="position_name", length=65535)
    public String getPositionName() {
        return this.positionName;
    }
    
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }


    @Column(name="create_date", nullable=false, length=19)
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    @Column(name="update_date", nullable=false, length=19)
    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    @Column(name="operater_id", nullable=false)
    public int getOperaterId() {
        return this.operaterId;
    }
    
    public void setOperaterId(int operaterId) {
        this.operaterId = operaterId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CardUpdateHistoryId) ) return false;
		 CardUpdateHistoryId castOther = ( CardUpdateHistoryId ) other; 
         
		 return (this.getCardId()==castOther.getCardId())
 && ( (this.getPositionName()==castOther.getPositionName()) || ( this.getPositionName()!=null && castOther.getPositionName()!=null && this.getPositionName().equals(castOther.getPositionName()) ) )
 && ( (this.getCreateDate()==castOther.getCreateDate()) || ( this.getCreateDate()!=null && castOther.getCreateDate()!=null && this.getCreateDate().equals(castOther.getCreateDate()) ) )
 && ( (this.getUpdateDate()==castOther.getUpdateDate()) || ( this.getUpdateDate()!=null && castOther.getUpdateDate()!=null && this.getUpdateDate().equals(castOther.getUpdateDate()) ) )
 && (this.getOperaterId()==castOther.getOperaterId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getCardId();
         result = 37 * result + ( getPositionName() == null ? 0 : this.getPositionName().hashCode() );
         result = 37 * result + ( getCreateDate() == null ? 0 : this.getCreateDate().hashCode() );
         result = 37 * result + ( getUpdateDate() == null ? 0 : this.getUpdateDate().hashCode() );
         result = 37 * result + this.getOperaterId();
         return result;
   }   


}

