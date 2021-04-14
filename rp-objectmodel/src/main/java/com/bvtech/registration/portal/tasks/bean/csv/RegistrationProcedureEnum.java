package com.bvtech.registration.portal.tasks.bean.csv;

public enum RegistrationProcedureEnum {
	Selbstregistriert(1), Fremdregistriert(2), ReregCodeFreischaltung(3);
	  // self-registered /assisted registered/ re registered with code)
	
	private int value;

	private RegistrationProcedureEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static RegistrationProcedureEnum parse(int id) {
		RegistrationProcedureEnum rc = null; // Default
		for (RegistrationProcedureEnum item : RegistrationProcedureEnum.values()) {
			if (item.getValue() == id) {
				rc = item;
				break;
			}
		}
		return rc;
	}
}
