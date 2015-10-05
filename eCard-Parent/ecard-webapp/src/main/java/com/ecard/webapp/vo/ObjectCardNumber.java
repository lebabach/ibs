package com.ecard.webapp.vo;

import java.math.BigInteger;

public class ObjectCardNumber {
	BigInteger totalCard;
	BigInteger totalCardTeam ;
	public ObjectCardNumber(BigInteger totalCard, BigInteger totalCardTeam) {
		super();
		this.totalCard = totalCard;
		this.totalCardTeam = totalCardTeam;
	}
	public BigInteger getTotalCard() {
		return totalCard;
	}
	public void setTotalCard(BigInteger totalCard) {
		this.totalCard = totalCard;
	}
	public BigInteger getTotalCardTeam() {
		return totalCardTeam;
	}
	public void setTotalCardTeam(BigInteger totalCardTeam) {
		this.totalCardTeam = totalCardTeam;
	}
	
	
}
