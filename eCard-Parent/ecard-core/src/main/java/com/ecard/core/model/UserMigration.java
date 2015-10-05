package com.ecard.core.model;
// Generated Sep 15, 2015 11:29:28 AM by Hibernate Tools 3.2.4.GA


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
 * UserMigration generated by hbm2java
 */
@Entity
@Table(name="user_migration"
)
public class UserMigration  implements java.io.Serializable {


     private UserMigrationId id;
     private UserInfo userInfo;

    public UserMigration() {
    }

	
    public UserMigration(UserMigrationId id) {
        this.id = id;
    }
    public UserMigration(UserMigrationId id, UserInfo userInfo) {
       this.id = id;
       this.userInfo = userInfo;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="userId", column=@Column(name="user_id") ), 
        @AttributeOverride(name="sansanUserId", column=@Column(name="sansan_user_id", length=65535) ) } )
    public UserMigrationId getId() {
        return this.id;
    }
    
    public void setId(UserMigrationId id) {
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




}


