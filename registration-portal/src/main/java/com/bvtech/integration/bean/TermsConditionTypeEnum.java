package com.bvtech.integration.bean;

public enum TermsConditionTypeEnum {
	USER('U'), COACH('C');

	private char cvalue;

	private TermsConditionTypeEnum(char cvalue) {
		this.cvalue = cvalue;
	}

	public char getCvalue() {
		return cvalue;
	}

	public void setCvalue(char cvalue) {
		this.cvalue = cvalue;
	}

	public static TermsConditionTypeEnum parse(char id) {
		for (TermsConditionTypeEnum item : TermsConditionTypeEnum.values()) {
			if (item.getCvalue() == id) {
				return item;
			}
		}
		throw new EnumConstantNotPresentException(TermsConditionTypeEnum.class, id+"");
	}
}
