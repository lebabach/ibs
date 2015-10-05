package com.ecard.core.model.enums;

public enum Roles {
	Operator(4);

	private int statusCode;

	private Roles(int s) {
		statusCode = s;
	}

	public int getStatusCode() {
		return statusCode;
	}

}