package com.ecard.core.vo;

import com.ecard.core.model.CardInfo;

public class CardInfoUserVo {
	String sortType;
	CardInfo cardInfo;
	public CardInfoUserVo(String sortType, CardInfo cardInfo) {
		super();
		this.sortType = sortType;
		this.cardInfo = cardInfo;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public CardInfo getCardInfo() {
		return cardInfo;
	}
	public void setCardInfo(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}
	

}
