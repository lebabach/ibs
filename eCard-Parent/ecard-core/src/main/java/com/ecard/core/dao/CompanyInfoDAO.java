package com.ecard.core.dao;

import java.util.List;

import com.ecard.core.model.CompanyInfo;

public interface CompanyInfoDAO {
	public List<CompanyInfo> findAll();

	public boolean add(CompanyInfo companyInfo);

	public boolean delete(int companyID);
}
