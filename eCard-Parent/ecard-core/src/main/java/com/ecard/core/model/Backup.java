package com.ecard.core.model;
// Generated Aug 4, 2015 10:15:08 AM by Hibernate Tools 3.2.4.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * Backup generated by hbm2java
 */
@Entity
@Table(name="backup"
)
public class Backup  implements java.io.Serializable {


     private Integer backupId;
     private String fileName;
     private String memo;
     private Date deletDate;
     private int deleteFlg;
     private Date createDate;
     private Date updateDate;
     private int operaterId;

    public Backup() {
    }

    public Backup(String fileName, String memo, Date deletDate, int deleteFlg, Date createDate, Date updateDate, int operaterId) {
       this.fileName = fileName;
       this.memo = memo;
       this.deletDate = deletDate;
       this.deleteFlg = deleteFlg;
       this.createDate = createDate;
       this.updateDate = updateDate;
       this.operaterId = operaterId;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="backup_id", unique=true, nullable=false)
    public Integer getBackupId() {
        return this.backupId;
    }
    
    public void setBackupId(Integer backupId) {
        this.backupId = backupId;
    }

    
    @Column(name="file_name", nullable=false, length=65535)
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
    @Column(name="memo", nullable=false, length=65535)
    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }

    
    @Column(name="delet_date", nullable=false, length=19)
    public Date getDeletDate() {
        return this.deletDate;
    }
    
    public void setDeletDate(Date deletDate) {
        this.deletDate = deletDate;
    }

    
    @Column(name="delete_flg", nullable=false)
    public int getDeleteFlg() {
        return this.deleteFlg;
    }
    
    public void setDeleteFlg(int deleteFlg) {
        this.deleteFlg = deleteFlg;
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




}

