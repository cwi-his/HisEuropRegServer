package com.bvtech.registration.portal.enumeration;

public enum DiabetesTypes {
	DIABETES_TYP_1(1), DIABETES_TYP_2(2), OTHER(3), I_DONT_KNOW(4);

	private int value;

	private DiabetesTypes(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	// Da aggiungere per mancaza di mapping automatico nelle JPA (tra String/integer)
	public static DiabetesTypes parse(int id) {
		DiabetesTypes rc = null; // Default
		for (DiabetesTypes item : DiabetesTypes.values()) {
			if (item.getValue() == id) {
				rc = item;
				break;
			}
		}
		return rc;
	}
}
