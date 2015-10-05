package com.ecard.core.service;

import java.util.List;

import com.ecard.core.model.GroupCompanyDepartment;
import com.ecard.core.model.GroupCompanyInfo;
import com.ecard.core.vo.GroupDepartmentVO;

/**
 *
 * @author PhuongNguyen
 */
public interface GroupCompanyInfoService  {
	public List<GroupCompanyInfo>getListCompany();
	public boolean delete(int companyId);
	public boolean deleteDepartment(int companyId);
	int add(GroupCompanyInfo companyInfo);
	int addDepartment(GroupCompanyDepartment groupCompanyDepartment);
	public GroupCompanyInfo getCompanyById(int id);
	public List<GroupDepartmentVO> getCompanyDepartmentById(int id);
	int update(GroupCompanyInfo companyInfo );
	public List<GroupCompanyInfo>getListCompanyOfUser(int groupCompanyInfoId );
}
