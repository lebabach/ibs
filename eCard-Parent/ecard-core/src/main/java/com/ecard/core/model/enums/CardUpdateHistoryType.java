package com.ecard.core.model.enums;

public enum CardUpdateHistoryType {
	APPROVAL(1),
    INPUT_WAITING(2),
    FIX_WAITING(3),
    APPROVAL_WAITING(4),
    DELETE(99)
    ;
    
    private int value;

    private CardUpdateHistoryType(int value) {
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
