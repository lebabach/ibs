package com.ecard.core.model.enums;

public enum TableTypeEnum {
	User("U"), Company("C"),CardInfor("I"),ImageInfor("P") ;

	private String statusCode;

	private TableTypeEnum(String s) {
		statusCode = s;
	}

	public String getStatusCode() {
		return statusCode;
	}

}
