/*
 * SearchConditions class
 */
package com.ecard.core.model.enums;

/**
 *
 * @author vinhla
 */
public enum SearchConditions {
    NAME(1),
    COMPANY(2),
    POSITION(3),
    UPDATE_DATE(4),
    CONTACT(5),
    TAG(6)
    ;
    
    private int value;

    private SearchConditions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getEnumNameForValue(int value) {
        SearchConditions[] values = SearchConditions.values();
        String enumValue = null;
        for (SearchConditions eachValue : values) {
            if (eachValue.getValue() == value) {
                return eachValue.name();
            }
        }
        return enumValue;
    }
}
