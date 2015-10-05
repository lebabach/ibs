package com.ecard.core.model.enums;

/**
 *
 * @author vinhla
 */
public enum UserInfoStatus {
    ACTIVE(1),
    PENDING(2),
    SUCCESS(3),
    FAILURED(4);
    
    private int value;

    private UserInfoStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getEnumNameForValue(int value) {
        UserInfoStatus[] values = UserInfoStatus.values();
        String enumValue = null;
        for (UserInfoStatus eachValue : values) {
            if (eachValue.getValue() == value) {
                return eachValue.name();
            }
        }
        return enumValue;
    }
}
