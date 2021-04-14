package com.bvtech.registration.portal.tasks.bean.csv;

public enum DiabetesTypeEnum {

	DiabtetesTyp1(1), DiabetesTyp2(2), Sonstigen(3), Weiss_Nicht(4);
	
	
	private int value;

	private DiabetesTypeEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static DiabetesTypeEnum parse(int id) {
		DiabetesTypeEnum rc = null; // Default
		for (DiabetesTypeEnum item : DiabetesTypeEnum.values()) {
			if (item.getValue() == id) {
				rc = item;
				break;
			}
		}
		return rc;
	}
}
