package com.ecard.core.model;
// Generated Oct 14, 2015 9:32:56 AM by Hibernate Tools 3.2.4.GA


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * LinkCard generated by hbm2java
 */
@Entity
@Table(name="link_card"
)
public class LinkCard  implements java.io.Serializable {


     private LinkCardId id;

    public LinkCard() {
    }

    public LinkCard(LinkCardId id) {
       this.id = id;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="cardId", column=@Column(name="card_id", nullable=false) ), 
        @AttributeOverride(name="cardOwnerId", column=@Column(name="card_owner_id") ), 
        @AttributeOverride(name="imageFile", column=@Column(name="image_file", nullable=false, length=65535) ), 
        @AttributeOverride(name="companyName", column=@Column(name="company_name", nullable=false, length=65535) ), 
        @AttributeOverride(name="departmentName", column=@Column(name="department_name", nullable=false, length=65535) ), 
        @AttributeOverride(name="name", column=@Column(name="name", nullable=false, length=65535) ), 
        @AttributeOverride(name="createDate1", column=@Column(name="create_date_1", nullable=false, length=19) ), 
        @AttributeOverride(name="createDate2", column=@Column(name="create_date_2", nullable=false, length=19) ) } )
    public LinkCardId getId() {
        return this.id;
    }
    
    public void setId(LinkCardId id) {
        this.id = id;
    }




}

