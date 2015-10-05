package com.ecard.core.model.enums;

public enum StatusCard {
	approved(1), inputWating(2), editWating(3), approvedPending(4), recognitioning(5), uploadImageFail(-1);
	private int value;

	private StatusCard(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static String getEnumNameForValue(int value) {
		StatusCard[] values = StatusCard.values();
		String enumValue = null;
		for (StatusCard eachValue : values) {
			if (eachValue.getValue() == value) {
				return eachValue.name();
			}
		}
		return enumValue;
	}
}
