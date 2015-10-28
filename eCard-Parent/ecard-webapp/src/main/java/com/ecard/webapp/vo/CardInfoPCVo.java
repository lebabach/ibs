package com.ecard.webapp.vo;

import java.util.List;

import com.ecard.core.vo.CardInfo;
import com.ecard.core.vo.TagGroup;

public class CardInfoPCVo {
	String nameSort;
	List<CardInfo> lstCardInfo;
	List<TagGroup> listTagGroup;
	
	public CardInfoPCVo(String nameSort, List<CardInfo> lstCardInfo,List<TagGroup> listTagGroup) {
		super();
		this.nameSort = nameSort;
		this.lstCardInfo = lstCardInfo;
		this.listTagGroup = listTagGroup;
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
	public List<TagGroup> getListTagGroup() {
		return listTagGroup;
	}
	public void setListTagGroup(List<TagGroup> listTagGroup) {
		this.listTagGroup = listTagGroup;
	}
	
	
	

}
