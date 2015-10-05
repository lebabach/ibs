package com.ecard.webapp.vo;

import java.util.List;

import com.ecard.core.model.GroupCompanyInfo;
import com.ecard.core.vo.GroupDepartmentVO;

public class GroupCompanyDepartmentVO {
	GroupCompanyInfo  groupCompanyInfo;
	List<GroupDepartmentVO> lstGroupCompanyDepartmentVo;
	public GroupCompanyDepartmentVO(GroupCompanyInfo groupCompanyInfo,
			List<GroupDepartmentVO> lstGroupCompanyDepartmentVo) {
		super();
		this.groupCompanyInfo = groupCompanyInfo;
		this.lstGroupCompanyDepartmentVo = lstGroupCompanyDepartmentVo;
	}
	public GroupCompanyInfo getGroupCompanyInfo() {
		return groupCompanyInfo;
	}
	public void setGroupCompanyInfo(GroupCompanyInfo groupCompanyInfo) {
		this.groupCompanyInfo = groupCompanyInfo;
	}
	public List<GroupDepartmentVO> getLstGroupCompanyDepartmentVo() {
		return lstGroupCompanyDepartmentVo;
	}
	public void setLstGroupCompanyDepartmentVo(List<GroupDepartmentVO> lstGroupCompanyDepartmentVo) {
		this.lstGroupCompanyDepartmentVo = lstGroupCompanyDepartmentVo;
	}
	
	

}
