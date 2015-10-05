/*
 * ApprovalStatus
 */
package com.ecard.core.model.enums;

/**
 *
 * @author Windows
 */
public enum ApprovalStatus {
    WAITING(1),
    ACCEPT(2);
//    WAITINGIMAGE(0),
//    WAITINGDATA(1),
//    NONAME(2),    
//    WAITINTACCEPT(3),
//    WAITINGCOLLECT(4),
//    APPROVAL(5),
//    NOEXTRACTION(6);    

    private int value;

    private ApprovalStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getEnumNameForValue(int value) {
        ApprovalStatus[] values = ApprovalStatus.values();
        String enumValue = null;
        for (ApprovalStatus eachValue : values) {
            if (eachValue.getValue() == value) {
                return eachValue.name();
            }
        }
        return enumValue;
    }
}
