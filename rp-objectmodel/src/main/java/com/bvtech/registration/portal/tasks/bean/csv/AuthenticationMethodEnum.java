package com.bvtech.registration.portal.tasks.bean.csv;

public enum AuthenticationMethodEnum {

	SMS(1), Email_Link(2); 
	 // (trad.: si/no)
	
	private int value;

	private AuthenticationMethodEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static AuthenticationMethodEnum parse(int id) {
		AuthenticationMethodEnum rc = null; // Default
		for (AuthenticationMethodEnum item : AuthenticationMethodEnum.values()) {
			if (item.getValue() == id) {
				rc = item;
				break;
			}
		}
		return rc;
	}
}
