package com.ecard.core.model;
// Generated Aug 22, 2015 3:13:51 PM by Hibernate Tools 3.2.4.GA


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
 * GroupCompanyDepartment generated by hbm2java
 */
@Entity
@Table(name="group_company_department"
)
public class GroupCompanyDepartment  implements java.io.Serializable {


     private GroupCompanyDepartmentId id;
     private GroupCompanyInfo groupCompanyInfo;

    public GroupCompanyDepartment() {
    }

    public GroupCompanyDepartment(GroupCompanyDepartmentId id, GroupCompanyInfo groupCompanyInfo) {
       this.id = id;
       this.groupCompanyInfo = groupCompanyInfo;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="groupCompanyId", column=@Column(name="group_company_id", nullable=false) ), 
        @AttributeOverride(name="groupCompanyDepartmentName", column=@Column(name="group_company_department_name", nullable=false) ), 
        @AttributeOverride(name="seq", column=@Column(name="seq", nullable=false) ) } )
    public GroupCompanyDepartmentId getId() {
        return this.id;
    }
    
    public void setId(GroupCompanyDepartmentId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="group_company_id", nullable=false, insertable=false, updatable=false)
    public GroupCompanyInfo getGroupCompanyInfo() {
        return this.groupCompanyInfo;
    }
    
    public void setGroupCompanyInfo(GroupCompanyInfo groupCompanyInfo) {
        this.groupCompanyInfo = groupCompanyInfo;
    }




}


