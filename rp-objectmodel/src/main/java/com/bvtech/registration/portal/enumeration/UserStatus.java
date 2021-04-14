package com.bvtech.registration.portal.enumeration;

public enum UserStatus {
	
	CONDITIONALLY_DISQUALIFIED(1), DISQUALIFIED_IMMEDIATELY(2), ACCEPTED(3), IN_EVALUATION(4);

	private int value;

	private UserStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
