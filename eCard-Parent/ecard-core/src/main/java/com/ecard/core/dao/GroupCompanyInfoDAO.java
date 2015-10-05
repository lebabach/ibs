package com.ecard.core.dao;

import java.util.List;

import com.ecard.core.model.GroupCompanyDepartment;
import com.ecard.core.model.GroupCompanyInfo;
import com.ecard.core.vo.GroupDepartmentVO;

public interface GroupCompanyInfoDAO {
	public List<GroupCompanyInfo> getListCompany() ;
	public boolean delete(int companyId);
	public boolean deleteDepartment(int companyId);
	public int add(GroupCompanyInfo companyInfo);
	public int addDepartment(GroupCompanyDepartment groupCompanyDepartment);
	public GroupCompanyInfo getCompanyById(int id);
	public List<GroupDepartmentVO> getCompanyDepartmentById(int id);
	public int update(GroupCompanyInfo companyInfo);
	public List<GroupCompanyInfo> getListCompanyOfUser(int groupCompanyInfoId);
}
