package com.ecard.core.vo;

public class UserDownloadPermission {
	private Integer downloadGroup;
	private Integer downloadAll;
	private Integer groupDataDlRequestFlg;
	private Integer allDataDlRequestFlg;
	
	public UserDownloadPermission(){}
	
	public UserDownloadPermission(Integer downloadGroup, Integer downloadAll, Integer groupDataDlRequestFlg, Integer allDataDlRequestFlg) {		
		this.downloadGroup = downloadGroup;
		this.downloadAll = downloadAll;
		this.groupDataDlRequestFlg = groupDataDlRequestFlg;
		this.allDataDlRequestFlg = allDataDlRequestFlg;
	}
	
	public Integer getDownloadGroup() {
		return downloadGroup;
	}
	public void setDownloadGroup(Integer downloadGroup) {
		this.downloadGroup = downloadGroup;
	}
	public Integer getDownloadAll() {
		return downloadAll;
	}
	public void setDownloadAll(Integer downloadAll) {
		this.downloadAll = downloadAll;
	}

	public Integer getGroupDataDlRequestFlg() {
		return groupDataDlRequestFlg;
	}

	public void setGroupDataDlRequestFlg(Integer groupDataDlRequestFlg) {
		this.groupDataDlRequestFlg = groupDataDlRequestFlg;
	}

	public Integer getAllDataDlRequestFlg() {
		return allDataDlRequestFlg;
	}

	public void setAllDataDlRequestFlg(Integer allDataDlRequestFlg) {
		this.allDataDlRequestFlg = allDataDlRequestFlg;
	}
	
	
}
