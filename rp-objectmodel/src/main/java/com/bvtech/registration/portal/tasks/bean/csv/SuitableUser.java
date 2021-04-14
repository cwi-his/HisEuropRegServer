package com.bvtech.registration.portal.tasks.bean.csv;

import java.io.Serializable;
import java.util.Date;

public class SuitableUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// to understand last update date (no in CSV)
	private Date lastUpdateDate;
	
	// Integrazione con OLE System Management
	private String hisPartecipantID;
	private String hisNodeID;
	private Integer partecipantstatus; // Enum: Angenommen/Abgelehnt/Bedingtabgelehnt/RegistrierungUnvollständig 
													 // (trad.: accepted/rejected /partly rejected /registration uncompleted)
	private String registrationProcedure; // Enum: Selbstregistriert/Fremdregistriert/ReregCodeFreischaltung
										  // (trad.: self-registered /assisted registered/ re registered with code)
	private String partecipantVoucherCode;
	private String reRegistrationCode; // voucher code_3579
	private String rejectedVoucherCode; //  voucher code_7534
	private String assistedVoucherCode;
	private String answerParticipationTerms1; // always true
	private String answerParticipationTerms2; // always true
	private String answerParticipationTerms3; // always true
	private String answerParticipationTerms4; // always true
	private Date timestampAnswerParticipationTerms; // data formatted YYYY_MM_DD_HH_MM_SS
	private String diabetestype; // Enum: DiabtetesTyp1 / DiabetesTyp2 / Sonstigen / Weiss Nicht
	
	//Peronal Data	
	private String gender;  // Enum: Mann / Frau 
	private String lastName;
	private String firstName;
	private String streetName;
	private String houseNumber;
	private String addressAdditional;
	private String zipCode;
	private String city;
	private String additionalHintForDelivery;
	private String email;
	private Integer typePhone; //Enum: Festnetz/Mobil
	private String phoneNumber;
	private String alternativePhoneNumber;
	private Integer participantAvailability; // Enum: morgens/ vormittags/ mittags/ nachmittags/ abends
																 // (trad.:in the morning/before noon/noon/after noon/ in the evening)
	private Integer participantAvailabilitytoBeContact; // Enum: Ja/Nein
	private Date participantAvailabilityDate; // data formatted YYYY_MM_DD_HH_MM_SS
	private Date differentDeliveryAddress; // Enum: Ja / Nein
	private String timestampSubmission; // data formatted YYYY_MM_DD_HH_MM_SS
	
	// DeliveryAddress - If there is no different delivery address the participant address from the	base data will be copied into those fields.
	private String deliveryLastName;
	private String deliveryFirstName;
	private String deliveryStreetName;
	private String deliveryHouseNumber;
	private String deliveryAddressAdditions;
	private String deliveryZipCode;
	private String deliveryCity;
	private String deliveryAdditionalHints;
	
	// Authentication procedure
	private Integer authenticationMethod; // Enum: SMS/ Email Link
	private String alternativePhoneNumber2; //Enum: Ja/Nein
	private String alternativePhonrNumber;
	private Date timestampSubmissionSMS; // data formatted YYYY_MM_DD_HH_MM_SS
	private Date timestampAuthenticationEmail; // data formatted YYYY_MM_DD_HH_MM_SS
	
	// Questionnaire case history 	
	private Integer answerQuestion1; // Enum: Ja / Nein / Weiss_nicht
	private Integer answerQuestion2; // Enum: Ja / Nein / Weiss_nicht
	private Integer answerQuestion3; // Enum: Ja / Nein / Weiss_nicht
	
	// Questionnaire body mass index
	private Integer bodyWeight; // data Unit Kg
	private Integer bodyHeight; // Data Unit cm
 	private Integer bmi; // float XX,Y - Range [80:30] 
	private String flagBMI; // Enum: ja/nein --> "ja" bmi <25, "nein" >= 25 
	
	// Insuline questions
	private Integer answerInsuline1;
	private Integer answerInsuline2; // Enum: weniger als Monate  / 6-12 Monate / mehr als ein Jahr
	
	// Completion of registration and order
	private String	correctBasicData; // Enum: ja/nein
	private String	correctDeliveryAddress; // Enum: ja/nein
	private Date timestampEndRegisstration; // data formatted YYYY_MM_DD_HH_MM_SS
	
	public SuitableUser() {
		super();
	}



	public SuitableUser(Date lastUpdateDate, String hisPartecipantID, String hisNodeID, Integer partecipantstatus, String registrationProcedure,
			String partecipantVoucherCode, String reRegistrationCode, String rejectedVoucherCode,
			String assistedVoucherCode, String answerParticipationTerms1, String answerParticipationTerms2,
			String answerParticipationTerms3, String answerParticipationTerms4, Date timestampAnswerParticipationTerms,
			String diabetestype, String gender, String lastName, String firstName, String streetName,
			String houseNumber, String addressAdditional, String zipCode, String city, String additionalHintForDelivery,
			String email, Integer typePhone, String phoneNumber, String alternativePhoneNumber, Integer participantAvailability,
			Integer participantAvailabilitytoBeContact, Date participantAvailabilityDate, Date differentDeliveryAddress,
			String timestampSubmission, String deliveryLastName, String deliveryFirstName, String deliveryStreetName,
			String deliveryHouseNumber, String deliveryAddressAdditions, String deliveryZipCode, String deliveryCity,
			String deliveryAdditionalHints, Integer authenticationMethod, String alternativePhoneNumber2,
			String alternativePhonrNumber, Date timestampSubmissionSMS, Date timestampAuthenticationEmail,
			Integer answerQuestion1, Integer answerQuestion2, Integer answerQuestion3, Integer bodyWeight, Integer bodyHeight, Integer bmi,
			String flagBMI, Integer answerInsuline1, Integer answerInsuline2, String correctBasicData,
			String correctDeliveryAddress, Date timestampEndRegisstration) {
		super();
		this.lastUpdateDate = lastUpdateDate;
		this.hisPartecipantID = checkStringFormat(hisPartecipantID);
		this.hisNodeID = checkStringFormat(hisNodeID);
		this.partecipantstatus = partecipantstatus;
		this.registrationProcedure = checkStringFormat(registrationProcedure);
		this.partecipantVoucherCode = checkStringFormat(partecipantVoucherCode);
		this.reRegistrationCode = checkStringFormat(reRegistrationCode);
		this.rejectedVoucherCode = checkStringFormat(rejectedVoucherCode);
		this.assistedVoucherCode = checkStringFormat(assistedVoucherCode);
		this.answerParticipationTerms1 = checkStringFormat(answerParticipationTerms1);
		this.answerParticipationTerms2 = checkStringFormat(answerParticipationTerms2);
		this.answerParticipationTerms3 = checkStringFormat(answerParticipationTerms3);
		this.answerParticipationTerms4 = checkStringFormat(answerParticipationTerms4);
		if(timestampAnswerParticipationTerms != null)
			this.timestampAnswerParticipationTerms = timestampAnswerParticipationTerms;
		this.diabetestype = checkStringFormat(diabetestype);
		this.gender = checkStringFormat(gender);
		this.lastName = checkStringFormat(lastName);
		this.firstName = checkStringFormat(firstName);
		this.streetName = checkStringFormat(streetName);
		this.houseNumber = checkStringFormat(houseNumber);
		this.addressAdditional = checkStringFormat(addressAdditional);
		this.zipCode = checkStringFormat(zipCode);
		this.city = checkStringFormat(city);
		this.additionalHintForDelivery = checkStringFormat(additionalHintForDelivery);
		this.email = checkStringFormat(email);
		this.typePhone = typePhone;
		this.phoneNumber = checkStringFormat(phoneNumber);
		this.alternativePhoneNumber = checkStringFormat(alternativePhoneNumber);
		this.participantAvailability = participantAvailability;
		this.participantAvailabilitytoBeContact = participantAvailabilitytoBeContact;
		if(participantAvailabilityDate!=null)
			this.participantAvailabilityDate = participantAvailabilityDate;
		this.differentDeliveryAddress = differentDeliveryAddress;
		if(timestampSubmission!=null)
			this.timestampSubmission = timestampSubmission;
		this.deliveryLastName = checkStringFormat(deliveryLastName);
		this.deliveryFirstName = checkStringFormat(deliveryFirstName);
		this.deliveryStreetName = checkStringFormat(deliveryStreetName);
		this.deliveryHouseNumber = checkStringFormat(deliveryHouseNumber);
		this.deliveryAddressAdditions = checkStringFormat(deliveryAddressAdditions);
		this.deliveryZipCode = checkStringFormat(deliveryZipCode);
		this.deliveryCity = checkStringFormat(deliveryCity);
		this.deliveryAdditionalHints = checkStringFormat(deliveryAdditionalHints);
		this.authenticationMethod = authenticationMethod;
		this.alternativePhoneNumber2 = checkStringFormat(alternativePhoneNumber2);
		this.alternativePhonrNumber = checkStringFormat(alternativePhonrNumber);
		if(timestampSubmissionSMS!=null)
			this.timestampSubmissionSMS = timestampSubmissionSMS;
		if(timestampAuthenticationEmail!=null)
			this.timestampAuthenticationEmail = timestampAuthenticationEmail;
		this.answerQuestion1 = answerQuestion1;
		this.answerQuestion2 = answerQuestion2;
		this.answerQuestion3 = answerQuestion3;
		this.bodyWeight = bodyWeight;
		this.bodyHeight = bodyHeight;
		this.bmi = bmi;
		this.flagBMI = checkStringFormat(flagBMI);
		this.answerInsuline1 = answerInsuline1;
		this.answerInsuline2 = answerInsuline2;
		this.correctBasicData = checkStringFormat(correctBasicData);
		this.correctDeliveryAddress = checkStringFormat(correctDeliveryAddress);
		if(timestampEndRegisstration!=null)
			this.timestampEndRegisstration = timestampEndRegisstration;
	}

	private String checkStringFormat(String value){
		if(value==null) return value;
		return value.replace(";","\\;");
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getHisPartecipantID() {
		return hisPartecipantID;
	}

	public void setHisPartecipantID(String hisPartecipantID) {
		this.hisPartecipantID = hisPartecipantID;
	}



	public String getHisNodeID() {
		return hisNodeID;
	}



	public void setHisNodeID(String hisNodeID) {
		this.hisNodeID = hisNodeID;
	}



	public Integer getPartecipantstatus() {
		return partecipantstatus;
	}



	public void setPartecipantstatus(Integer partecipantstatus) {
		this.partecipantstatus = partecipantstatus;
	}



	public String getRegistrationProcedure() {
		return registrationProcedure;
	}



	public void setRegistrationProcedure(String registrationProcedure) {
		this.registrationProcedure = registrationProcedure;
	}



	public String getPartecipantVoucherCode() {
		return partecipantVoucherCode;
	}



	public void setPartecipantVoucherCode(String partecipantVoucherCode) {
		this.partecipantVoucherCode = partecipantVoucherCode;
	}



	public String getReRegistrationCode() {
		return reRegistrationCode;
	}



	public void setReRegistrationCode(String reRegistrationCode) {
		this.reRegistrationCode = reRegistrationCode;
	}



	public String getRejectedVoucherCode() {
		return rejectedVoucherCode;
	}



	public void setRejectedVoucherCode(String rejectedVoucherCode) {
		this.rejectedVoucherCode = rejectedVoucherCode;
	}



	public String getAssistedVoucherCode() {
		return assistedVoucherCode;
	}



	public void setAssistedVoucherCode(String assistedVoucherCode) {
		this.assistedVoucherCode = assistedVoucherCode;
	}



	public String getAnswerParticipationTerms1() {
		return answerParticipationTerms1;
	}



	public void setAnswerParticipationTerms1(String answerParticipationTerms1) {
		this.answerParticipationTerms1 = answerParticipationTerms1;
	}



	public String getAnswerParticipationTerms2() {
		return answerParticipationTerms2;
	}



	public void setAnswerParticipationTerms2(String answerParticipationTerms2) {
		this.answerParticipationTerms2 = answerParticipationTerms2;
	}



	public String getAnswerParticipationTerms3() {
		return answerParticipationTerms3;
	}



	public void setAnswerParticipationTerms3(String answerParticipationTerms3) {
		this.answerParticipationTerms3 = answerParticipationTerms3;
	}



	public String getAnswerParticipationTerms4() {
		return answerParticipationTerms4;
	}



	public void setAnswerParticipationTerms4(String answerParticipationTerms4) {
		this.answerParticipationTerms4 = answerParticipationTerms4;
	}



	public Date getTimestampAnswerParticipationTerms() {
		return timestampAnswerParticipationTerms;
	}



	public void setTimestampAnswerParticipationTerms(Date timestampAnswerParticipationTerms) {
		this.timestampAnswerParticipationTerms = timestampAnswerParticipationTerms;
	}



	public String getDiabetestype() {
		return diabetestype;
	}



	public void setDiabetestype(String diabetestype) {
		this.diabetestype = diabetestype;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getStreetName() {
		return streetName;
	}



	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}



	public String getHouseNumber() {
		return houseNumber;
	}



	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}



	public String getAddressAdditional() {
		return addressAdditional;
	}



	public void setAddressAdditional(String addressAdditional) {
		this.addressAdditional = addressAdditional;
	}



	public String getZipCode() {
		return zipCode;
	}



	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getAdditionalHintForDelivery() {
		return additionalHintForDelivery;
	}



	public void setAdditionalHintForDelivery(String additionalHintForDelivery) {
		this.additionalHintForDelivery = additionalHintForDelivery;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Integer getTypePhone() {
		return typePhone;
	}



	public void setTypePhone(Integer typePhone) {
		this.typePhone = typePhone;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getAlternativePhoneNumber() {
		return alternativePhoneNumber;
	}



	public void setAlternativePhoneNumber(String alternativePhoneNumber) {
		this.alternativePhoneNumber = alternativePhoneNumber;
	}



	public Integer getParticipantAvailability() {
		return participantAvailability;
	}



	public void setParticipantAvailability(Integer participantAvailability) {
		this.participantAvailability = participantAvailability;
	}



	public Integer getParticipantAvailabilitytoBeContact() {
		return participantAvailabilitytoBeContact;
	}



	public void setParticipantAvailabilitytoBeContact(Integer participantAvailabilitytoBeContact) {
		this.participantAvailabilitytoBeContact = participantAvailabilitytoBeContact;
	}



	public Date getParticipantAvailabilityDate() {
		return participantAvailabilityDate;
	}



	public void setParticipantAvailabilityDate(Date participantAvailabilityDate) {
		this.participantAvailabilityDate = participantAvailabilityDate;
	}



	public Date getDifferentDeliveryAddress() {
		return differentDeliveryAddress;
	}



	public void setDifferentDeliveryAddress(Date differentDeliveryAddress) {
		this.differentDeliveryAddress = differentDeliveryAddress;
	}



	public String getTimestampSubmission() {
		return timestampSubmission;
	}



	public void setTimestampSubmission(String timestampSubmission) {
		this.timestampSubmission = timestampSubmission;
	}



	public String getDeliveryLastName() {
		return deliveryLastName;
	}



	public void setDeliveryLastName(String deliveryLastName) {
		this.deliveryLastName = deliveryLastName;
	}



	public String getDeliveryFirstName() {
		return deliveryFirstName;
	}



	public void setDeliveryFirstName(String deliveryFirstName) {
		this.deliveryFirstName = deliveryFirstName;
	}



	public String getDeliveryStreetName() {
		return deliveryStreetName;
	}



	public void setDeliveryStreetName(String deliveryStreetName) {
		this.deliveryStreetName = deliveryStreetName;
	}



	public String getDeliveryHouseNumber() {
		return deliveryHouseNumber;
	}



	public void setDeliveryHouseNumber(String deliveryHouseNumber) {
		this.deliveryHouseNumber = deliveryHouseNumber;
	}



	public String getDeliveryAddressAdditions() {
		return deliveryAddressAdditions;
	}



	public void setDeliveryAddressAdditions(String deliveryAddressAdditions) {
		this.deliveryAddressAdditions = deliveryAddressAdditions;
	}



	public String getDeliveryZipCode() {
		return deliveryZipCode;
	}



	public void setDeliveryZipCode(String deliveryZipCode) {
		this.deliveryZipCode = deliveryZipCode;
	}



	public String getDeliveryCity() {
		return deliveryCity;
	}



	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}



	public String getDeliveryAdditionalHints() {
		return deliveryAdditionalHints;
	}



	public void setDeliveryAdditionalHints(String deliveryAdditionalHints) {
		this.deliveryAdditionalHints = deliveryAdditionalHints;
	}



	public Integer getAuthenticationMethod() {
		return authenticationMethod;
	}



	public void setAuthenticationMethod(Integer authenticationMethod) {
		this.authenticationMethod = authenticationMethod;
	}



	public String getAlternativePhoneNumber2() {
		return alternativePhoneNumber2;
	}



	public void setAlternativePhoneNumber2(String alternativePhoneNumber2) {
		this.alternativePhoneNumber2 = alternativePhoneNumber2;
	}



	public String getAlternativePhonrNumber() {
		return alternativePhonrNumber;
	}



	public void setAlternativePhonrNumber(String alternativePhonrNumber) {
		this.alternativePhonrNumber = alternativePhonrNumber;
	}



	public Date getTimestampSubmissionSMS() {
		return timestampSubmissionSMS;
	}



	public void setTimestampSubmissionSMS(Date timestampSubmissionSMS) {
		this.timestampSubmissionSMS = timestampSubmissionSMS;
	}



	public Date getTimestampAuthenticationEmail() {
		return timestampAuthenticationEmail;
	}



	public void setTimestampAuthenticationEmail(Date timestampAuthenticationEmail) {
		this.timestampAuthenticationEmail = timestampAuthenticationEmail;
	}



	public Integer getAnswerQuestion1() {
		return answerQuestion1;
	}



	public void setAnswerQuestion1(Integer answerQuestion1) {
		this.answerQuestion1 = answerQuestion1;
	}



	public Integer getAnswerQuestion2() {
		return answerQuestion2;
	}



	public void setAnswerQuestion2(Integer answerQuestion2) {
		this.answerQuestion2 = answerQuestion2;
	}



	public Integer getAnswerQuestion3() {
		return answerQuestion3;
	}



	public void setAnswerQuestion3(Integer answerQuestion3) {
		this.answerQuestion3 = answerQuestion3;
	}



	public Integer getBodyWeight() {
		return bodyWeight;
	}



	public void setBodyWeight(Integer bodyWeight) {
		this.bodyWeight = bodyWeight;
	}



	public Integer getBodyHeight() {
		return bodyHeight;
	}



	public void setBodyHeight(Integer bodyHeight) {
		this.bodyHeight = bodyHeight;
	}



	public Integer getBmi() {
		return bmi;
	}



	public void setBmi(Integer bmi) {
		this.bmi = bmi;
	}



	public String getFlagBMI() {
		return flagBMI;
	}



	public void setFlagBMI(String flagBMI) {
		this.flagBMI = flagBMI;
	}



	public Integer getAnswerInsuline1() {
		return answerInsuline1;
	}



	public void setAnswerInsuline1(Integer answerInsuline1) {
		this.answerInsuline1 = answerInsuline1;
	}



	public Integer getAnswerInsuline2() {
		return answerInsuline2;
	}



	public void setAnswerInsuline2(Integer answerInsuline2) {
		this.answerInsuline2 = answerInsuline2;
	}



	public String getCorrectBasicData() {
		return correctBasicData;
	}



	public void setCorrectBasicData(String correctBasicData) {
		this.correctBasicData = correctBasicData;
	}



	public String getCorrectDeliveryAddress() {
		return correctDeliveryAddress;
	}



	public void setCorrectDeliveryAddress(String correctDeliveryAddress) {
		this.correctDeliveryAddress = correctDeliveryAddress;
	}



	public Date getTimestampEndRegisstration() {
		return timestampEndRegisstration;
	}



	public void setTimestampEndRegisstration(Date timestampEndRegisstration) {
		this.timestampEndRegisstration = timestampEndRegisstration;
	}



	public String getCsvLine(char csvSeparated) {
		return  hisPartecipantID + csvSeparated + 
				hisNodeID + csvSeparated + 
				partecipantstatus + csvSeparated + 
				registrationProcedure + csvSeparated + 
				partecipantVoucherCode + csvSeparated + 
				reRegistrationCode + csvSeparated +
				rejectedVoucherCode + csvSeparated + 
				assistedVoucherCode	+ csvSeparated + 
				answerParticipationTerms1 + csvSeparated +
				answerParticipationTerms2 + csvSeparated + 
				answerParticipationTerms3 + csvSeparated + 
				answerParticipationTerms4 + csvSeparated +
				timestampAnswerParticipationTerms + csvSeparated + 
				diabetestype + csvSeparated + 
				gender + csvSeparated + 
				lastName + csvSeparated + 
				firstName + csvSeparated + 
				streetName + csvSeparated + 
				houseNumber + csvSeparated + 
				addressAdditional + csvSeparated + 
				zipCode + csvSeparated + 
				city + csvSeparated + 
				additionalHintForDelivery + csvSeparated + 
				email + csvSeparated + 
				typePhone + csvSeparated + 
				phoneNumber + csvSeparated +
				alternativePhoneNumber + csvSeparated + 
				participantAvailability	+ csvSeparated + 
				participantAvailabilitytoBeContact + csvSeparated + 
				participantAvailabilityDate + csvSeparated +
				differentDeliveryAddress + csvSeparated + 
				timestampSubmission + csvSeparated +
				deliveryLastName + csvSeparated + 
				deliveryFirstName + csvSeparated +
				deliveryStreetName + csvSeparated + 
				deliveryHouseNumber + csvSeparated +
				deliveryAddressAdditions + csvSeparated + 
				deliveryZipCode + csvSeparated + 
				deliveryCity + csvSeparated + 
				deliveryAdditionalHints + csvSeparated +
				authenticationMethod + csvSeparated + 
				alternativePhoneNumber2	+ csvSeparated + 
				alternativePhonrNumber + csvSeparated +
				timestampSubmissionSMS + csvSeparated + 
				timestampAuthenticationEmail + csvSeparated + 
				answerQuestion1 + csvSeparated + 
				answerQuestion2 + csvSeparated +
				answerQuestion3 + csvSeparated + 
				bodyWeight + csvSeparated + 
				bodyHeight + csvSeparated + 
				bmi + csvSeparated + 
				(bmi.intValue()<25?true:false) /* flagBMI */ + csvSeparated + 
				answerInsuline1 + csvSeparated +
				answerInsuline2 + csvSeparated + 
				correctBasicData + csvSeparated +
				correctDeliveryAddress + csvSeparated + 
				timestampEndRegisstration;
	}
}
