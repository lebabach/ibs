/*
 * PublishStatus
 */
package com.ecard.core.model.enums;

/**
 *
 * @author vinhla
 */
public enum PublishStatus {
    PRIVATE(1),
    PUBLIC(2);
    
    private int value;

    private PublishStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getEnumNameForValue(int value) {
        PublishStatus[] values = PublishStatus.values();
        String enumValue = null;
        for (PublishStatus eachValue : values) {
            if (eachValue.getValue() == value) {
                return eachValue.name();
            }
        }
        return enumValue;
    }
}
