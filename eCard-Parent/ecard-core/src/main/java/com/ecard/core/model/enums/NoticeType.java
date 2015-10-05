/*
 * NoticeType
 */
package com.ecard.core.model.enums;

/**
 *
 * @author vinhla
 */
public enum NoticeType {
    TERMINATE_NOTIFICATION_CARD_DATA(1),
    TERMINATE_NOTIFICATION_CARD_DATA_REGISTER(2),
    NOTIFICATION_TO_OTHER_USER(3),
    REGISTRATION_CARD_CHANGE(4),
    REGISTRATION_CARD_OVERLOAD(5);
    
    private int value;

    private NoticeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getEnumNameForValue(int value) {
        NoticeType[] values = NoticeType.values();
        String enumValue = null;
        for (NoticeType eachValue : values) {
            if (eachValue.getValue() == value) {
                    return eachValue.name();
            }
        }
        return enumValue;
    }
}
