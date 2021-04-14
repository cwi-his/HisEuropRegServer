package com.bvtech.registration.portal.tasks.bean.csv;

public enum TypePhoneEnum {
	Festnetz(1), Mobil(2); 
	 // (trad.: linea fissa/mobile)
	
	private int value;

	private TypePhoneEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static TypePhoneEnum parse(int id) {
		TypePhoneEnum rc = null; // Default
		for (TypePhoneEnum item : TypePhoneEnum.values()) {
			if (item.getValue() == id) {
				rc = item;
				break;
			}
		}
		return rc;
	}
}
