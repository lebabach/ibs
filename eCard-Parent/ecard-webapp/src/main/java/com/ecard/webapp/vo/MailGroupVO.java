package com.ecard.webapp.vo;

import java.util.List;

public class MailGroupVO {

	List<UserInfoResultVO> userInfoResultVOs;
	List<CompanyDisplayVO> lstcompanyDisplayVO;
	
	
	public MailGroupVO(List<UserInfoResultVO> userInfoResultVOs, List<CompanyDisplayVO> lstcompanyDisplayVO) {
		super();
		this.userInfoResultVOs = userInfoResultVOs;
		this.lstcompanyDisplayVO = lstcompanyDisplayVO;
	}


	public List<UserInfoResultVO> getUserInfoResultVOs() {
		return userInfoResultVOs;
	}


	public void setUserInfoResultVOs(List<UserInfoResultVO> userInfoResultVOs) {
		this.userInfoResultVOs = userInfoResultVOs;
	}


	public List<CompanyDisplayVO> getLstcompanyDisplayVO() {
		return lstcompanyDisplayVO;
	}


	public void setLstcompanyDisplayVO(List<CompanyDisplayVO> lstcompanyDisplayVO) {
		this.lstcompanyDisplayVO = lstcompanyDisplayVO;
	}
	
	
	
}
