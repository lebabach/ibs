package com.ecard.core.model.enums;

public enum IndexTypeEnum {
	CardInfor(1), UserInfor(2),Company(3);

	private int statusCode;

	private IndexTypeEnum(int s) {
		statusCode = s;
	}

	public int getStatusCode() {
		return statusCode;
	}

}