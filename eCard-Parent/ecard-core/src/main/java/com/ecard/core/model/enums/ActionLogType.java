/*
 * ActionLogType
 */
package com.ecard.core.model.enums;

/**
 *
 * @author vinhla
 */
public enum ActionLogType {
    LOGIN(1),
    DOWNLOAD(2);
    
    private int value;

    private ActionLogType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getEnumNameForValue(int value) {
        PermissionType[] values = PermissionType.values();
        String enumValue = null;
        for (PermissionType eachValue : values) {
            if (eachValue.getValue() == value) {
                return eachValue.name();
            }
        }
        return enumValue;
    }
}
