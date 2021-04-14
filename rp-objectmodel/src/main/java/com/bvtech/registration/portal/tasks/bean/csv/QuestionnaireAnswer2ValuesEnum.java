package com.bvtech.registration.portal.tasks.bean.csv;

public enum QuestionnaireAnswer2ValuesEnum {

		Ja(1), Nein(2); 
		 // (trad.: si/no)
		
		private int value;

		private QuestionnaireAnswer2ValuesEnum(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}

		public static QuestionnaireAnswer2ValuesEnum parse(int id) {
			QuestionnaireAnswer2ValuesEnum rc = null; // Default
			for (QuestionnaireAnswer2ValuesEnum item : QuestionnaireAnswer2ValuesEnum.values()) {
				if (item.getValue() == id) {
					rc = item;
					break;
				}
			}
			return rc;
		}

}
