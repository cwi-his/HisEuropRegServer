package com.bvtech.registration.portal.tasks.bean.csv;

public enum QuestionnaireAnswer3sValueEnum {

	Ja(1), Nein(2), Weiss_nicht(3); 
	 // (trad.: si/no/non so)
	
	private int value;

	private QuestionnaireAnswer3sValueEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static QuestionnaireAnswer3sValueEnum parse(int id) {
		QuestionnaireAnswer3sValueEnum rc = null; // Default
		for (QuestionnaireAnswer3sValueEnum item : QuestionnaireAnswer3sValueEnum.values()) {
			if (item.getValue() == id) {
				rc = item;
				break;
			}
		}
		return rc;
	}
}
