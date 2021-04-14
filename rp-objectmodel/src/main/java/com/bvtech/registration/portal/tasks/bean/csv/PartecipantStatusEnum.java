package com.bvtech.registration.portal.tasks.bean.csv;

public enum PartecipantStatusEnum {
	Bedingtabgelehnt(1), Abgelehnt(2), Angenommen(3), RegistrierungUnvollständig(4); 
//	Angenommen=ACCEPTED --> 3
//	Abgelehnt=DISQUALIFIED_IMMEDIATELY --> 2
//	Bedingtabgelehnt=CONDITIONALLY_DISQUALIFIED -->  1
//	RegistrierungUnvollständig=IN_EVALUATION --> 4 
	
	private int value;

	private PartecipantStatusEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static PartecipantStatusEnum parse(int id) {
		PartecipantStatusEnum rc = null; // Default
		for (PartecipantStatusEnum item : PartecipantStatusEnum.values()) {
			if (item.getValue() == id) {
				rc = item;
				break;
			}
		}
		return rc;
	}
}
