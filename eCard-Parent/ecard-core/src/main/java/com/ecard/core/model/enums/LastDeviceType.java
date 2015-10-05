/*
 * LastDeviceType
 */
package com.ecard.core.model.enums;

/**
 *
 * @author vinhla
 */
public enum LastDeviceType {
    PC(1),
    ANDROID(2),
    IOS(3);
    
    private int value;

    private LastDeviceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getEnumNameForValue(int value) {
        LastDeviceType[] values = LastDeviceType.values();
        String enumValue = null;
        for (LastDeviceType eachValue : values) {
            if (eachValue.getValue() == value) {
                return eachValue.name();
            }
        }
        return enumValue;
    }
}
