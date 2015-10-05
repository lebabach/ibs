/*
 * CardParamType
 */
package com.ecard.core.model.enums;

/**
 *
 * @author vinhla
 */
public enum EmailType {
    mail_news_flg(1), 
    mail_use_assist_flg(2), 
    mail_notice_flg(3),
    mail_send_flg(4);
    
    private int value;

    private EmailType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getEnumNameForValue(int value) {
        EmailType[] values = EmailType.values();
        String enumValue = null;
        for (EmailType eachValue : values) {
            if (eachValue.getValue() == value) {
                return eachValue.name();
            }
        }
        return enumValue;
    }
}
