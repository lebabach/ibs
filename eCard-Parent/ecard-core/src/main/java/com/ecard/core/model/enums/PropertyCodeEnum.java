package com.ecard.core.model.enums;

public enum PropertyCodeEnum {
	ShootingApp("P"), ManualPC("N"), Migration("M"), Scanner("S");

	private String statusCode;

	private PropertyCodeEnum(String s) {
		statusCode = s;
	}

	public String getStatusCode() {
		return statusCode;
	}
	public static PropertyCodeEnum findByName(String name){
	    for(PropertyCodeEnum v : values()){
	        if( v.getStatusCode().equals(name)){
	            return v;
	        }
	    }
	    return null;
	}

}
