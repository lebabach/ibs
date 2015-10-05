/*
 * PermissionType
 */
package com.ecard.core.model.enums;

/**
 *
 * @author vinhla
 */
public enum PermissionType {
    ROLE_USER(1),
    ROLE_LEADER(2),
    ROLE_OPERATOR(3),
    ROLE_SUPERVISOR(4),
    ROLE_ADMIN(5),
    ROLE_AUTHORITY_USER(6),
    ROLE_OPERATOR_MANAGER(7);

    private int value;

    private PermissionType(int value) {
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
