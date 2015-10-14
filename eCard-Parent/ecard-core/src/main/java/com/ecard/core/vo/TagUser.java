package com.ecard.core.vo;

public class TagUser {
	Integer cardId;
	String tagName;

	public TagUser(Integer cardId, String tagName) {

		this.cardId = cardId;
		this.tagName = tagName;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	

}
