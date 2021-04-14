package com.bvtech.registration.portal.tasks.bean.csv;

public enum ParticipantAvailabilityEnum {
	// The value 'mittags (noon)' at position '3' there isn't in the GUI interface
	morgens(1), vormittags(2), nachmittags(3), abends (4);
	 // (trad.:in the morning/before noon/noon/after noon/ in the evening)
	 // (trad.: linea fissa/mobile)
	
	private int value;

	private ParticipantAvailabilityEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static ParticipantAvailabilityEnum parse(int id) {
		ParticipantAvailabilityEnum rc = null; // Default
		for (ParticipantAvailabilityEnum item : ParticipantAvailabilityEnum.values()) {
			if (item.getValue() == id) {
				rc = item;
				break;
			}
		}
		return rc;
	}
}
