package com.ecard.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.DataIndexIdDAO;
import com.ecard.core.dao.GroupCompanyInfoDAO;
import com.ecard.core.model.GroupCompanyDepartment;
import com.ecard.core.model.GroupCompanyInfo;
import com.ecard.core.service.GroupCompanyInfoService;
import com.ecard.core.vo.GroupDepartmentVO;

@Service("groupCompanyInfoService")
@Transactional
public class GroupCompanyInfoServiceImpl implements GroupCompanyInfoService {
    @Autowired
	GroupCompanyInfoDAO groupCompanyInfoDAO;
	
    @Autowired
    DataIndexIdDAO dataIndexIdDAO;
    
	@Override
	public List<GroupCompanyInfo> getListCompany() {
		return groupCompanyInfoDAO.getListCompany();
	}

	@Override
	public boolean delete(int companyId) {
		return groupCompanyInfoDAO.delete(companyId);
	}

	@Override
	public int add(GroupCompanyInfo companyInfo) {
		/*int i=groupCompanyInfoDAO.add(companyInfo);
		dataIndexIdDAO.insertDataIndexBy(IndexTypeEnum.Company, ActionTypeEnum.Insert, TableTypeEnum.Company, PropertyCodeEnum.ManualPC);*/
		return groupCompanyInfoDAO.add(companyInfo);
	}

	@Override
	public GroupCompanyInfo getCompanyById(int id) {
		return groupCompanyInfoDAO.getCompanyById(id);
	}

	@Override
	public int addDepartment(GroupCompanyDepartment groupCompanyDepartment) {
		return groupCompanyInfoDAO.addDepartment(groupCompanyDepartment);
	}

	@Override
	public boolean deleteDepartment(int companyId) {
		return groupCompanyInfoDAO.deleteDepartment(companyId);
	}

	@Override
	public List<GroupDepartmentVO> getCompanyDepartmentById(int id) {
		return groupCompanyInfoDAO.getCompanyDepartmentById(id);
	}

	@Override
	public int update(GroupCompanyInfo companyInfo) {
		/*int i=groupCompanyInfoDAO.update(companyInfo);
		dataIndexIdDAO.insertDataIndexBy(IndexTypeEnum.Company, ActionTypeEnum.Update, TableTypeEnum.Company, PropertyCodeEnum.ManualPC);*/
		 return groupCompanyInfoDAO.update(companyInfo);
	}

	@Override
	public List<GroupCompanyInfo> getListCompanyOfUser(int groupCompanyInfoId) {
		return groupCompanyInfoDAO.getListCompanyOfUser(groupCompanyInfoId);
	}

}
