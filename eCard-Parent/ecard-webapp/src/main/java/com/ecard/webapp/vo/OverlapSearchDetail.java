package com.ecard.webapp.vo;

import java.util.List;

public class OverlapSearchDetail {
	private String search;
	private com.ecard.core.vo.CardInfo cards;
	private boolean detail=false;
	
	public com.ecard.core.vo.CardInfo getCards() {
		return cards;
	}

	public void setCards(com.ecard.core.vo.CardInfo cards) {
		this.cards = cards;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public boolean isDetail() {
		return detail;
	}

	public void setDetail(boolean detail) {
		this.detail = detail;
	}


}
