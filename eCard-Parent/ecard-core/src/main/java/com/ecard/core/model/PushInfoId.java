package com.ecard.core.model;
// Generated Aug 24, 2015 11:39:59 AM by Hibernate Tools 3.2.4.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PushInfoId generated by hbm2java
 */
@Embeddable
public class PushInfoId  implements java.io.Serializable {


     private int userId;
     private String deviceToken;
     private String deviceType;
     private Date createDate;
     private Date updateDate;

    public PushInfoId() {
    }

    public PushInfoId(int userId, String deviceToken, String deviceType, Date createDate, Date updateDate) {
       this.userId = userId;
       this.deviceToken = deviceToken;
       this.deviceType = deviceType;
       this.createDate = createDate;
       this.updateDate = updateDate;
    }
   


    @Column(name="user_id", nullable=false)
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Column(name="device_token", nullable=false, length=65535)
    public String getDeviceToken() {
        return this.deviceToken;
    }
    
    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }


    @Column(name="device_type", nullable=false, length=65535)
    public String getDeviceType() {
        return this.deviceType;
    }
    
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PushInfoId) ) return false;
		 PushInfoId castOther = ( PushInfoId ) other; 
         
		 return (this.getUserId()==castOther.getUserId())
 && ( (this.getDeviceToken()==castOther.getDeviceToken()) || ( this.getDeviceToken()!=null && castOther.getDeviceToken()!=null && this.getDeviceToken().equals(castOther.getDeviceToken()) ) )
 && ( (this.getDeviceType()==castOther.getDeviceType()) || ( this.getDeviceType()!=null && castOther.getDeviceType()!=null && this.getDeviceType().equals(castOther.getDeviceType()) ) )
 && ( (this.getCreateDate()==castOther.getCreateDate()) || ( this.getCreateDate()!=null && castOther.getCreateDate()!=null && this.getCreateDate().equals(castOther.getCreateDate()) ) )
 && ( (this.getUpdateDate()==castOther.getUpdateDate()) || ( this.getUpdateDate()!=null && castOther.getUpdateDate()!=null && this.getUpdateDate().equals(castOther.getUpdateDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getUserId();
         result = 37 * result + ( getDeviceToken() == null ? 0 : this.getDeviceToken().hashCode() );
         result = 37 * result + ( getDeviceType() == null ? 0 : this.getDeviceType().hashCode() );
         result = 37 * result + ( getCreateDate() == null ? 0 : this.getCreateDate().hashCode() );
         result = 37 * result + ( getUpdateDate() == null ? 0 : this.getUpdateDate().hashCode() );
         return result;
   }   


}


