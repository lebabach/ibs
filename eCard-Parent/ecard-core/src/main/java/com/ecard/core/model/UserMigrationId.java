package com.ecard.core.model;
// Generated Sep 15, 2015 11:29:28 AM by Hibernate Tools 3.2.4.GA


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserMigrationId generated by hbm2java
 */
@Embeddable
public class UserMigrationId  implements java.io.Serializable {


     private Integer userId;
     private String sansanUserId;

    public UserMigrationId() {
    }

    public UserMigrationId(Integer userId, String sansanUserId) {
       this.userId = userId;
       this.sansanUserId = sansanUserId;
    }
   


    @Column(name="user_id")
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    @Column(name="sansan_user_id", length=65535)
    public String getSansanUserId() {
        return this.sansanUserId;
    }
    
    public void setSansanUserId(String sansanUserId) {
        this.sansanUserId = sansanUserId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserMigrationId) ) return false;
		 UserMigrationId castOther = ( UserMigrationId ) other; 
         
		 return ( (this.getUserId()==castOther.getUserId()) || ( this.getUserId()!=null && castOther.getUserId()!=null && this.getUserId().equals(castOther.getUserId()) ) )
 && ( (this.getSansanUserId()==castOther.getSansanUserId()) || ( this.getSansanUserId()!=null && castOther.getSansanUserId()!=null && this.getSansanUserId().equals(castOther.getSansanUserId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserId() == null ? 0 : this.getUserId().hashCode() );
         result = 37 * result + ( getSansanUserId() == null ? 0 : this.getSansanUserId().hashCode() );
         return result;
   }   


}


