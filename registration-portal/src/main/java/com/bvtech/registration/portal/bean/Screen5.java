package com.bvtech.registration.portal.bean;

public class Screen5 {
	private String optName;
	private String optSurname;
	private String optAddress;
	private String optHouseNumber;
	private String optAddressAdditional;
	private String optNote;
	private String optCity;
	private String optPostalCode;
	private Boolean differentAddress;
	private Boolean sendNow;
	private Boolean sendFromDate;
	private String fromDate;
	
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getOptName() {
		return optName;
	}
	public void setOptName(String optName) {
		this.optName = optName;
	}
	public String getOptSurname() {
		return optSurname;
	}
	public void setOptSurname(String optSurname) {
		this.optSurname = optSurname;
	}
	public String getOptAddress() {
		return optAddress;
	}
	public void setOptAddress(String optAddress) {
		this.optAddress = optAddress;
	}
	
	public String getOptHouseNumber() {
		return optHouseNumber;
	}
	public void setOptHouseNumber(String optHouseNumber) {
		this.optHouseNumber = optHouseNumber;
	}
	
	public String getOptAddressAdditional() {
		return optAddressAdditional;
	}
	public void setOptAddressAdditional(String optAddressAdditional) {
		this.optAddressAdditional = optAddressAdditional;
	}
	public String getOptNote() {
		return optNote;
	}
	public void setOptNote(String optNote) {
		this.optNote = optNote;
	}
	public String getOptCity() {
		return optCity;
	}
	public void setOptCity(String optCity) {
		this.optCity = optCity;
	}
	public String getOptPostalCode() {
		return optPostalCode;
	}
	public void setOptPostalCode(String optPostalCode) {
		this.optPostalCode = optPostalCode;
	}
	
	public Boolean getDifferentAddress() {
		return differentAddress;
	}
	public void setDifferentAddress(Boolean differentAddress) {
		this.differentAddress = differentAddress;
	}
	
	public Boolean getSendNow() {
		return sendNow;
	}
	
	public void setSendNow(Boolean s) {
		this.sendNow = s;
	}
	
	public Boolean getSendFromDate() {
		return this.sendFromDate;
	}
	
	public void setSendFromDate(Boolean s) {
		this.sendFromDate = s;
	}
}
