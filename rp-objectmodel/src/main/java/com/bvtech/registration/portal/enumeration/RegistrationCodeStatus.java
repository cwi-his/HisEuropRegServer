package com.bvtech.registration.portal.enumeration;

public enum RegistrationCodeStatus {
	OK(1), EXPIRED(2), USED(3), PARTIALLY_USED(4), COACH_USED(5);

	private int value;

	private RegistrationCodeStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	// Da aggiungere per mancaza di mapping automatico nelle JPA (tra String/integer)
	public static RegistrationCodeStatus parse(int id) {
		RegistrationCodeStatus rc = null; // Default
		for (RegistrationCodeStatus item : RegistrationCodeStatus.values()) {
			if (item.getValue() == id) {
				rc = item;
				break;
			}
		}
		return rc;
	}
}
