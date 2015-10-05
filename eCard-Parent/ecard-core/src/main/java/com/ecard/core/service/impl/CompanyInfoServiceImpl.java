package com.ecard.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.CompanyInfoDAO;
import com.ecard.core.model.CompanyInfo;
import com.ecard.core.service.CompanyInfoService;

@Service("companyInfoService")
@Transactional
public class CompanyInfoServiceImpl implements CompanyInfoService {
	@Autowired
	CompanyInfoDAO companyInfoDAO;
	
	@Override
	public List<CompanyInfo> getAll() {
		return companyInfoDAO.findAll();
	}

	@Override
	public boolean add(CompanyInfo companyInfo) {
		return companyInfoDAO.add(companyInfo);
	}

	@Override
	public boolean delete(int companyID) {
		return companyInfoDAO.delete(companyID);
	}

}
