package com.ecard.core.vo;

public class GroupDepartmentVO {
	 Integer groupCompanyId;
	 String groupCompanyDepartmentName;
	 Integer seq; 
         
        public GroupDepartmentVO(){}
        
	public GroupDepartmentVO(Integer groupCompanyId, String groupCompanyDepartmentName, Integer seq) {
		super();
		this.groupCompanyId = groupCompanyId;
		this.groupCompanyDepartmentName = groupCompanyDepartmentName;
		this.seq = seq;
	}
	public Integer getGroupCompanyId() {
		return groupCompanyId;
	}
	public void setGroupCompanyId(Integer groupCompanyId) {
		this.groupCompanyId = groupCompanyId;
	}
	public String getGroupCompanyDepartmentName() {
		return groupCompanyDepartmentName;
	}
	public void setGroupCompanyDepartmentName(String groupCompanyDepartmentName) {
		this.groupCompanyDepartmentName = groupCompanyDepartmentName;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}
