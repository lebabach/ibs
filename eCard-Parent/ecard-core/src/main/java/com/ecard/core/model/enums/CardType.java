/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.model.enums;

/**
 *
 * @author Windows
 */
public enum CardType {
    JAPAN(1),
    ENGLISH(2),
    CHINESE(3),
    OTHER(4);
    
    private int value;

    private CardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getEnumNameForValue(int value) {
        CardType[] values = CardType.values();
        String enumValue = null;
        for (CardType eachValue : values) {
            if (eachValue.getValue() == value) {
                return eachValue.name();
            }
        }
        return enumValue;
    }
}
