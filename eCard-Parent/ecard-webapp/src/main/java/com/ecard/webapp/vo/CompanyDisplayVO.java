package com.ecard.webapp.vo;

import java.util.Date;

public class CompanyDisplayVO {
	private Integer groupCompanyId;
	private String groupCompanyIdIndex;
    private String groupCompanyName;
    private String groupCompanyNameKana;
    private Date createDate;
    private Date updateDate;
    private Integer operaterId;
	

	public CompanyDisplayVO(Integer groupCompanyId, String groupCompanyIdIndex, String groupCompanyName,
			String groupCompanyNameKana, Date createDate, Date updateDate, Integer operaterId) {
		super();
		this.groupCompanyId = groupCompanyId;
		this.groupCompanyIdIndex = groupCompanyIdIndex;
		this.groupCompanyName = groupCompanyName;
		this.groupCompanyNameKana = groupCompanyNameKana;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.operaterId = operaterId;
		
	}


	public Integer getGroupCompanyId() {
		return groupCompanyId;
	}


	public void setGroupCompanyId(Integer groupCompanyId) {
		this.groupCompanyId = groupCompanyId;
	}


	public String getGroupCompanyIdIndex() {
		return groupCompanyIdIndex;
	}


	public void setGroupCompanyIdIndex(String groupCompanyIdIndex) {
		this.groupCompanyIdIndex = groupCompanyIdIndex;
	}


	public String getGroupCompanyName() {
		return groupCompanyName;
	}


	public void setGroupCompanyName(String groupCompanyName) {
		this.groupCompanyName = groupCompanyName;
	}


	public String getGroupCompanyNameKana() {
		return groupCompanyNameKana;
	}


	public void setGroupCompanyNameKana(String groupCompanyNameKana) {
		this.groupCompanyNameKana = groupCompanyNameKana;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Date getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public Integer getOperaterId() {
		return operaterId;
	}


	public void setOperaterId(Integer operaterId) {
		this.operaterId = operaterId;
	}
	
	
	
}
