/*
 * CardParamType
 */
package com.ecard.core.model.enums;

/**
 *
 * @author vinhla
 */
public enum CardParamType {
    NAME(1),
    COMPANY_NAME(2),
    POST(3),
    DIVISION_NAME(3),
    PHONE_NUMBER(4),
    STREET_ADDRESS(5),
    EMAIL_ADDRESS(6);
    
    private int value;

    private CardParamType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getEnumNameForValue(int value) {
        CardParamType[] values = CardParamType.values();
        String enumValue = null;
        for (CardParamType eachValue : values) {
            if (eachValue.getValue() == value) {
                return eachValue.name();
            }
        }
        return enumValue;
    }
}
