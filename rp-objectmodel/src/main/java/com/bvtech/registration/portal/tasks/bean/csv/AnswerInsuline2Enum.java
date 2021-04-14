package com.bvtech.registration.portal.tasks.bean.csv;

public enum AnswerInsuline2Enum {

	weniger_als_monate(1), _6_12_monate(2), mehr_als_ein_jahr(3); 
	 // (trad.: si/no)
	// Enum: weniger als Monate  / 6-12 Monate / mehr als ein Jahr
	
	private int value;

	private AnswerInsuline2Enum(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static String parse(int id) {
		switch(id){
			case 1: return "weniger als Monate";
			case 2: return "6-12 Monate";
			case 3: return "mehr als ein Jahr";
		}
		return "";
	}
}
