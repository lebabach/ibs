package com.ecard.webapp.vo;

import java.math.BigInteger;
import java.util.List;

public class CardInfoLoadMoreVO {
	private List<com.ecard.core.vo.CardInfo> cardInfo;
	private BigInteger count;
	
	public BigInteger getCount() {
		return count;
	}

	public void setCount(BigInteger count) {
		this.count = count;
	}

	public List<com.ecard.core.vo.CardInfo> getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(List<com.ecard.core.vo.CardInfo> cardInfo) {
		this.cardInfo = cardInfo;
	}
}
