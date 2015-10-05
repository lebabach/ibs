package com.ecard.core.model.enums;

public enum PropertyLimitEnum {
	Limit("Limit"),NoLimit("NoLimit");

	private String statusCode;

	private PropertyLimitEnum(String s) {
		statusCode = s;
	}

	public String getStatusCode() {
		return statusCode;
	}
	public static PropertyLimitEnum findByName(String name){
	    for(PropertyLimitEnum v : values()){
	        if( v.getStatusCode().equals(name)){
	            return v;
	        }
	    }
	    return null;
	}

}
