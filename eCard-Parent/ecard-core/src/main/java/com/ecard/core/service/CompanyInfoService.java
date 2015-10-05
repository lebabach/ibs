package com.ecard.core.service;

import java.util.List;

import com.ecard.core.model.CompanyInfo;

public interface CompanyInfoService {
	List<CompanyInfo> getAll();
	boolean add(CompanyInfo companyInfo);
	boolean delete(int companyID);
}
