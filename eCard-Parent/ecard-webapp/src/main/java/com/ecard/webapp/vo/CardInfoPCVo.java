package com.ecard.webapp.vo;

import java.util.List;

import com.ecard.core.vo.CardInfo;

public class CardInfoPCVo {
	String nameSort;
	List<CardInfo> lstCardInfo;
	
	public CardInfoPCVo(String nameSort, List<CardInfo> lstCardInfo) {
		super();
		this.nameSort = nameSort;
		this.lstCardInfo = lstCardInfo;
	}
	public String getNameSort() {
		return nameSort;
	}
	public void setNameSort(String nameSort) {
		this.nameSort = nameSort;
	}
	public List<CardInfo> getLstCardInfo() {
		return lstCardInfo;
	}
	public void setLstCardInfo(List<CardInfo> lstCardInfo) {
		this.lstCardInfo = lstCardInfo;
	}
	
	

}
