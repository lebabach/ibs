package com.ecard.core.model;
// Generated Sep 10, 2015 2:34:27 PM by Hibernate Tools 3.2.4.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * TeamInfo generated by hbm2java
 */
@Entity
@Table(name="team_info"
)
public class TeamInfo  implements java.io.Serializable {


     private Integer teamId;
     private String teamName;
     private Integer targetCount;
     private Date createDate;
     private Date updateDate;
     private Integer operaterId;
     private int currentCount;
     private Set<UserInfo> userInfos = new HashSet<UserInfo>(0);

    public TeamInfo() {
    }

	
    public TeamInfo(String teamName, Date createDate, Date updateDate, int currentCount) {
        this.teamName = teamName;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.currentCount = currentCount;
    }
    public TeamInfo(String teamName, Integer targetCount, Date createDate, Date updateDate, Integer operaterId, int currentCount, Set<UserInfo> userInfos) {
       this.teamName = teamName;
       this.targetCount = targetCount;
       this.createDate = createDate;
       this.updateDate = updateDate;
       this.operaterId = operaterId;
       this.currentCount = currentCount;
       this.userInfos = userInfos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="team_id", unique=true, nullable=false)
    public Integer getTeamId() {
        return this.teamId;
    }
    
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    
    @Column(name="team_name", nullable=false, length=65535)
    public String getTeamName() {
        return this.teamName;
    }
    
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    
    @Column(name="target_count")
    public Integer getTargetCount() {
        return this.targetCount;
    }
    
    public void setTargetCount(Integer targetCount) {
        this.targetCount = targetCount;
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

    
    @Column(name="operater_id")
    public Integer getOperaterId() {
        return this.operaterId;
    }
    
    public void setOperaterId(Integer operaterId) {
        this.operaterId = operaterId;
    }

    
    @Column(name="current_count", nullable=false)
    public int getCurrentCount() {
        return this.currentCount;
    }
    
    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="teamInfo")
    public Set<UserInfo> getUserInfos() {
        return this.userInfos;
    }
    
    public void setUserInfos(Set<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }




}


