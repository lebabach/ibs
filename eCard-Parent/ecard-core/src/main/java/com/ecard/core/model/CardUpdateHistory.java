package com.ecard.core.model;
// Generated Oct 14, 2015 9:55:15 AM by Hibernate Tools 3.2.4.GA


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
 * CardUpdateHistory generated by hbm2java
 */
@Entity
@Table(name="card_update_history"
)
public class CardUpdateHistory  implements java.io.Serializable {


     private CardUpdateHistoryId id;
     private CardInfo cardInfo;

    public CardUpdateHistory() {
    }

    public CardUpdateHistory(CardUpdateHistoryId id, CardInfo cardInfo) {
       this.id = id;
       this.cardInfo = cardInfo;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="cardId", column=@Column(name="card_id", nullable=false) ), 
        @AttributeOverride(name="paramType", column=@Column(name="param_type", nullable=false) ), 
        @AttributeOverride(name="oldData", column=@Column(name="old_data", length=65535) ), 
        @AttributeOverride(name="newData", column=@Column(name="new_data", length=65535) ), 
        @AttributeOverride(name="createDate", column=@Column(name="create_date", nullable=false, length=19) ), 
        @AttributeOverride(name="updateDate", column=@Column(name="update_date", nullable=false, length=19) ), 
        @AttributeOverride(name="operaterId", column=@Column(name="operater_id", nullable=false) ) } )
    public CardUpdateHistoryId getId() {
        return this.id;
    }
    
    public void setId(CardUpdateHistoryId id) {
        this.id = id;
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


