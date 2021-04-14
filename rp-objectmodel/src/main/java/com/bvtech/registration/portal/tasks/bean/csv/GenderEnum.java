package com.bvtech.registration.portal.tasks.bean.csv;

public enum GenderEnum {

	Mann('M'), Frau('F');
	
	private char value;

	private GenderEnum(char value) {
		this.value = value;
	}

	public char getValue() {
		return this.value;
	}

	public static GenderEnum parse(int id) {
		GenderEnum rc = null; // Default
		for (GenderEnum item : GenderEnum.values()) {
			if (item.getValue() == id) {
				rc = item;
				break;
			}
		}
		return rc;
	}
}
