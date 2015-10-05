package com.ecard.core.model.enums;

public enum ActionTypeEnum {
	Delete("D"), Update("U"),Insert("I");

	private String statusCode;

	private ActionTypeEnum(String s) {
		statusCode = s;
	}

	public String getStatusCode() {
		return statusCode;
	}

}
