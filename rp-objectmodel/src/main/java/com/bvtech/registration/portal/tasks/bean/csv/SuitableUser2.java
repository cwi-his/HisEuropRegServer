package com.bvtech.registration.portal.tasks.bean.csv;

import java.io.Serializable;

public class SuitableUser2 implements Serializable{

	private static final long serialVersionUID = 1L;
	// Integrazione con OLE System Management
	private String hisPartecipantID;
	private String hisNodeID;
	private PartecipantStatusEnum partecipantstatus; // Enum: Angenommen/Abgelehnt/Bedingtabgelehnt/RegistrierungUnvollständig 
													 // (trad.: accepted/rejected /partly rejected /registration uncompleted)
	private RegistrationProcedureEnum registrationProcedure; // Enum: Selbstregistriert/Fremdregistriert/ReregCodeFreischaltung
										  // (trad.: self-registered /assisted registered/ re registered with code)
	private String partecipantVoucherCode;
	private String reRegistrationCode; // voucher code_3579
	private String rejectedVoucherCode; //  voucher code_7534
	private String assistedVoucherCode;
	private boolean answerParticipationTerms1; // always true
	private boolean answerParticipationTerms2; // always true
	private boolean answerParticipationTerms3; // always true
	private boolean answerParticipationTerms4; // always true
	private String timestampAnswerParticipationTerms; // data formatted YYYY_MM_DD_HH_MM_SS
	private DiabetesTypeEnum diabetestype; // Enum: DiabtetesTyp1 / DiabetesTyp2 / Sonstigen / Weiss Nicht
	
	//Peronal Data	
	private GenderEnum gender;  // Enum: Mann / Frau 
	private String lastName;
	private String firstName;
	private String streetName;
	private String houseNumber;
	private String addressAdditional;
	private String zipCode;
	private String city;
	private String additionalHintForDelivery;
	private String email;
	private TypePhoneEnum typePhone; //Enum: Festnetz/Mobil
	private String phoneNumber;
	private String alternativePhoneNumber;
	private ParticipantAvailabilityEnum participantAvailability; // Enum: morgens/ vormittags/ mittags/ nachmittags/ abends
																 // (trad.:in the morning/before noon/noon/after noon/ in the evening)
	private QuestionnaireAnswer2ValuesEnum participantAvailabilitytoBeContact; // Enum: Ja/Nein
	private String participantAvailabilityDate; // data formatted YYYY_MM_DD_HH_MM_SS
	private QuestionnaireAnswer2ValuesEnum differentDeliveryAddress; // Enum: Ja / Nein
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
	private AuthenticationMethodEnum authenticationMethod; // Enum: SMS/ Email Link
	private QuestionnaireAnswer2ValuesEnum alternativePhoneNumber2; //Enum: Ja/Nein
	private String alternativePhonrNumber;
	private String timestampSubmissionSMS; // data formatted YYYY_MM_DD_HH_MM_SS
	private String timestampAuthenticationEmail; // data formatted YYYY_MM_DD_HH_MM_SS
	
	// Questionnaire case history 	
	private QuestionnaireAnswer3sValueEnum answerQuestion1; // Enum: Ja / Nein / Weiss_nicht
	private QuestionnaireAnswer3sValueEnum answerQuestion2; // Enum: Ja / Nein / Weiss_nicht
	private QuestionnaireAnswer3sValueEnum answerQuestion3; // Enum: Ja / Nein / Weiss_nicht
	
	// Questionnaire body mass index
	private String bodyWeight; // data Unit Kg
	private String bodyHeight; // Data Unit cm
 	private String bmi; // float XX,Y - Range [80:30] 
	private QuestionnaireAnswer2ValuesEnum flagBMI; // Enum: ja/nein --> "ja" bmi <25, "nein" >= 25 
	
	// Insuline questions
	private QuestionnaireAnswer2ValuesEnum answerInsuline1;
	private AnswerInsuline2Enum answerInsuline2; // Enum: weniger als Monate  / 6-12 Monate / mehr als ein Jahr
	
	// Completion of registration and order
	private QuestionnaireAnswer2ValuesEnum	correctBasicData; // Enum: ja/nein
	private QuestionnaireAnswer2ValuesEnum	correctDeliveryAddress; // Enum: ja/nein
	private String timestampEndRegisstration; // data formatted YYYY_MM_DD_HH_MM_SS
	
	public SuitableUser2(String hisPartecipantID, String hisNodeID, PartecipantStatusEnum partecipantstatus,
			RegistrationProcedureEnum registrationProcedure, String partecipantVoucherCode, String reRegistrationCode,
			String rejectedVoucherCode, String assistedVoucherCode, boolean answerParticipationTerms1,
			boolean answerParticipationTerms2, boolean answerParticipationTerms3, boolean answerParticipationTerms4,
			String timestampAnswerParticipationTerms, DiabetesTypeEnum diabetestype, GenderEnum gender, String lastName,
			String firstName, String streetName, String houseNumber, String addressAdditional, String zipCode,
			String city, String additionalHintForDelivery, String email, TypePhoneEnum typePhone, String phoneNumber,
			String alternativePhoneNumber, ParticipantAvailabilityEnum participantAvailability,
			QuestionnaireAnswer2ValuesEnum participantAvailabilitytoBeContact, String participantAvailabilityDate,
			QuestionnaireAnswer2ValuesEnum differentDeliveryAddress, String timestampSubmission,
			String deliveryLastName, String deliveryFirstName, String deliveryStreetName, String deliveryHouseNumber,
			String deliveryAddressAdditions, String deliveryZipCode, String deliveryCity,
			String deliveryAdditionalHints, AuthenticationMethodEnum authenticationMethod,
			QuestionnaireAnswer2ValuesEnum alternativePhoneNumber2, String alternativePhonrNumber,
			String timestampSubmissionSMS, String timestampAuthenticationEmail,
			QuestionnaireAnswer3sValueEnum answerQuestion1, QuestionnaireAnswer3sValueEnum answerQuestion2,
			QuestionnaireAnswer3sValueEnum answerQuestion3, String bodyWeight, String bodyHeight, String bmi,
			QuestionnaireAnswer2ValuesEnum flagBMI, QuestionnaireAnswer2ValuesEnum answerInsuline1,
			AnswerInsuline2Enum answerInsuline2, QuestionnaireAnswer2ValuesEnum correctBasicData,
			QuestionnaireAnswer2ValuesEnum correctDeliveryAddress, String timestampEndRegisstration) {
		super();
		this.hisPartecipantID = hisPartecipantID;
		this.hisNodeID = hisNodeID;
		this.partecipantstatus = partecipantstatus;
		this.registrationProcedure = registrationProcedure;
		this.partecipantVoucherCode = partecipantVoucherCode;
		this.reRegistrationCode = reRegistrationCode;
		this.rejectedVoucherCode = rejectedVoucherCode;
		this.assistedVoucherCode = assistedVoucherCode;
		this.answerParticipationTerms1 = answerParticipationTerms1;
		this.answerParticipationTerms2 = answerParticipationTerms2;
		this.answerParticipationTerms3 = answerParticipationTerms3;
		this.answerParticipationTerms4 = answerParticipationTerms4;
		this.timestampAnswerParticipationTerms = timestampAnswerParticipationTerms;
		this.diabetestype = diabetestype;
		this.gender = gender;
		this.lastName = lastName;
		this.firstName = firstName;
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.addressAdditional = addressAdditional;
		this.zipCode = zipCode;
		this.city = city;
		this.additionalHintForDelivery = additionalHintForDelivery;
		this.email = email;
		this.typePhone = typePhone;
		this.phoneNumber = phoneNumber;
		this.alternativePhoneNumber = alternativePhoneNumber;
		this.participantAvailability = participantAvailability;
		this.participantAvailabilitytoBeContact = participantAvailabilitytoBeContact;
		this.participantAvailabilityDate = participantAvailabilityDate;
		this.differentDeliveryAddress = differentDeliveryAddress;
		this.timestampSubmission = timestampSubmission;
		this.deliveryLastName = deliveryLastName;
		this.deliveryFirstName = deliveryFirstName;
		this.deliveryStreetName = deliveryStreetName;
		this.deliveryHouseNumber = deliveryHouseNumber;
		this.deliveryAddressAdditions = deliveryAddressAdditions;
		this.deliveryZipCode = deliveryZipCode;
		this.deliveryCity = deliveryCity;
		this.deliveryAdditionalHints = deliveryAdditionalHints;
		this.authenticationMethod = authenticationMethod;
		this.alternativePhoneNumber2 = alternativePhoneNumber2;
		this.alternativePhonrNumber = alternativePhonrNumber;
		this.timestampSubmissionSMS = timestampSubmissionSMS;
		this.timestampAuthenticationEmail = timestampAuthenticationEmail;
		this.answerQuestion1 = answerQuestion1;
		this.answerQuestion2 = answerQuestion2;
		this.answerQuestion3 = answerQuestion3;
		this.bodyWeight = bodyWeight;
		this.bodyHeight = bodyHeight;
		this.bmi = bmi;
		this.flagBMI = flagBMI;
		this.answerInsuline1 = answerInsuline1;
		this.answerInsuline2 = answerInsuline2;
		this.correctBasicData = correctBasicData;
		this.correctDeliveryAddress = correctDeliveryAddress;
		this.timestampEndRegisstration = timestampEndRegisstration;
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
	public PartecipantStatusEnum getPartecipantstatus() {
		return partecipantstatus;
	}
	public void setPartecipantstatus(PartecipantStatusEnum partecipantstatus) {
		this.partecipantstatus = partecipantstatus;
	}
	public RegistrationProcedureEnum getRegistrationProcedure() {
		return registrationProcedure;
	}
	public void setRegistrationProcedure(RegistrationProcedureEnum registrationProcedure) {
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
	public boolean getAnswerParticipationTerms1() {
		return answerParticipationTerms1;
	}
	public void setAnswerParticipationTerms1(boolean answerParticipationTerms1) {
		this.answerParticipationTerms1 = answerParticipationTerms1;
	}
	public boolean getAnswerParticipationTerms2() {
		return answerParticipationTerms2;
	}
	public void setAnswerParticipationTerms2(boolean answerParticipationTerms2) {
		this.answerParticipationTerms2 = answerParticipationTerms2;
	}
	public boolean getAnswerParticipationTerms3() {
		return answerParticipationTerms3;
	}
	public void setAnswerParticipationTerms3(boolean answerParticipationTerms3) {
		this.answerParticipationTerms3 = answerParticipationTerms3;
	}
	public boolean getAnswerParticipationTerms4() {
		return answerParticipationTerms4;
	}
	public void setAnswerParticipationTerms4(boolean answerParticipationTerms4) {
		this.answerParticipationTerms4 = answerParticipationTerms4;
	}
	public String getTimestampAnswerParticipationTerms() {
		return timestampAnswerParticipationTerms;
	}
	public void setTimestampAnswerParticipationTerms(String timestampAnswerParticipationTerms) {
		this.timestampAnswerParticipationTerms = timestampAnswerParticipationTerms;
	}
	public DiabetesTypeEnum getDiabetestype() {
		return diabetestype;
	}
	public void setDiabetestype(DiabetesTypeEnum diabetestype) {
		this.diabetestype = diabetestype;
	}
	public GenderEnum getGender() {
		return gender;
	}
	public void setGender(GenderEnum gender) {
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
	public TypePhoneEnum getTypePhone() {
		return typePhone;
	}
	public void setTypePhone(TypePhoneEnum typePhone) {
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
	public ParticipantAvailabilityEnum getParticipantAvailability() {
		return participantAvailability;
	}
	public void setParticipantAvailability(ParticipantAvailabilityEnum participantAvailability) {
		this.participantAvailability = participantAvailability;
	}
	public QuestionnaireAnswer2ValuesEnum getParticipantAvailabilitytoBeContact() {
		return participantAvailabilitytoBeContact;
	}
	public void setParticipantAvailabilitytoBeContact(QuestionnaireAnswer2ValuesEnum participantAvailabilitytoBeContact) {
		this.participantAvailabilitytoBeContact = participantAvailabilitytoBeContact;
	}
	public String getParticipantAvailabilityDate() {
		return participantAvailabilityDate;
	}
	public void setParticipantAvailabilityDate(String participantAvailabilityDate) {
		this.participantAvailabilityDate = participantAvailabilityDate;
	}
	public QuestionnaireAnswer2ValuesEnum getDifferentDeliveryAddress() {
		return differentDeliveryAddress;
	}
	public void setDifferentDeliveryAddress(QuestionnaireAnswer2ValuesEnum differentDeliveryAddress) {
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
	public AuthenticationMethodEnum getAuthenticationMethod() {
		return authenticationMethod;
	}
	public void setAuthenticationMethod(AuthenticationMethodEnum authenticationMethod) {
		this.authenticationMethod = authenticationMethod;
	}
	public QuestionnaireAnswer2ValuesEnum getAlternativePhoneNumber2() {
		return alternativePhoneNumber2;
	}
	public void setAlternativePhoneNumber2(QuestionnaireAnswer2ValuesEnum alternativePhoneNumber2) {
		this.alternativePhoneNumber2 = alternativePhoneNumber2;
	}
	public String getAlternativePhonrNumber() {
		return alternativePhonrNumber;
	}
	public void setAlternativePhonrNumber(String alternativePhonrNumber) {
		this.alternativePhonrNumber = alternativePhonrNumber;
	}
	public String getTimestampSubmissionSMS() {
		return timestampSubmissionSMS;
	}
	public void setTimestampSubmissionSMS(String timestampSubmissionSMS) {
		this.timestampSubmissionSMS = timestampSubmissionSMS;
	}
	public String getTimestampAuthenticationEmail() {
		return timestampAuthenticationEmail;
	}
	public void setTimestampAuthenticationEmail(String timestampAuthenticationEmail) {
		this.timestampAuthenticationEmail = timestampAuthenticationEmail;
	}
	public QuestionnaireAnswer3sValueEnum getAnswerQuestion1() {
		return answerQuestion1;
	}
	public void setAnswerQuestion1(QuestionnaireAnswer3sValueEnum answerQuestion1) {
		this.answerQuestion1 = answerQuestion1;
	}
	public QuestionnaireAnswer3sValueEnum getAnswerQuestion2() {
		return answerQuestion2;
	}
	public void setAnswerQuestion2(QuestionnaireAnswer3sValueEnum answerQuestion2) {
		this.answerQuestion2 = answerQuestion2;
	}
	public QuestionnaireAnswer3sValueEnum getAnswerQuestion3() {
		return answerQuestion3;
	}
	public void setAnswerQuestion3(QuestionnaireAnswer3sValueEnum answerQuestion3) {
		this.answerQuestion3 = answerQuestion3;
	}
	public String getBodyWeight() {
		return bodyWeight;
	}
	public void setBodyWeight(String bodyWeight) {
		this.bodyWeight = bodyWeight;
	}
	public String getBodyHeight() {
		return bodyHeight;
	}
	public void setBodyHeight(String bodyHeight) {
		this.bodyHeight = bodyHeight;
	}
	public String getBmi() {
		return bmi;
	}
	public void setBmi(String bmi) {
		this.bmi = bmi;
	}
	public QuestionnaireAnswer2ValuesEnum getFlagBMI() {
		return flagBMI;
	}
	public void setFlagBMI(QuestionnaireAnswer2ValuesEnum flagBMI) {
		this.flagBMI = flagBMI;
	}
	public QuestionnaireAnswer2ValuesEnum getAnswerInsuline1() {
		return answerInsuline1;
	}
	public void setAnswerInsuline1(QuestionnaireAnswer2ValuesEnum answerInsuline1) {
		this.answerInsuline1 = answerInsuline1;
	}
	public AnswerInsuline2Enum getAnswerInsuline2() {
		return answerInsuline2;
	}
	public void setAnswerInsuline2(AnswerInsuline2Enum answerInsuline2) {
		this.answerInsuline2 = answerInsuline2;
	}
	public QuestionnaireAnswer2ValuesEnum getCorrectBasicData() {
		return correctBasicData;
	}
	public void setCorrectBasicData(QuestionnaireAnswer2ValuesEnum correctBasicData) {
		this.correctBasicData = correctBasicData;
	}
	public QuestionnaireAnswer2ValuesEnum getCorrectDeliveryAddress() {
		return correctDeliveryAddress;
	}
	public void setCorrectDeliveryAddress(QuestionnaireAnswer2ValuesEnum correctDeliveryAddress) {
		this.correctDeliveryAddress = correctDeliveryAddress;
	}
	public String getTimestampEndRegisstration() {
		return timestampEndRegisstration;
	}
	public void setTimestampEndRegisstration(String timestampEndRegisstration) {
		this.timestampEndRegisstration = timestampEndRegisstration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalHintForDelivery == null) ? 0 : additionalHintForDelivery.hashCode());
		result = prime * result + ((answerQuestion3 == null) ? 0 : answerQuestion3.hashCode());
		result = prime * result + ((addressAdditional == null) ? 0 : addressAdditional.hashCode());
		result = prime * result + ((alternativePhoneNumber == null) ? 0 : alternativePhoneNumber.hashCode());
		result = prime * result + ((alternativePhoneNumber2 == null) ? 0 : alternativePhoneNumber2.hashCode());
		result = prime * result + ((alternativePhonrNumber == null) ? 0 : alternativePhonrNumber.hashCode());
		result = prime * result + ((answerInsuline1 == null) ? 0 : answerInsuline1.hashCode());
		result = prime * result + ((answerInsuline2 == null) ? 0 : answerInsuline2.hashCode());
		result = prime * result + ((answerQuestion1 == null) ? 0 : answerQuestion1.hashCode());
		result = prime * result + ((answerQuestion2 == null) ? 0 : answerQuestion2.hashCode());
		result = prime * result + ((assistedVoucherCode == null) ? 0 : assistedVoucherCode.hashCode());
		result = prime * result + ((authenticationMethod == null) ? 0 : authenticationMethod.hashCode());
		result = prime * result + ((bmi == null) ? 0 : bmi.hashCode());
		result = prime * result + ((bodyHeight == null) ? 0 : bodyHeight.hashCode());
		result = prime * result + ((bodyWeight == null) ? 0 : bodyWeight.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((correctBasicData == null) ? 0 : correctBasicData.hashCode());
		result = prime * result + ((correctDeliveryAddress == null) ? 0 : correctDeliveryAddress.hashCode());
		result = prime * result + ((deliveryAdditionalHints == null) ? 0 : deliveryAdditionalHints.hashCode());
		result = prime * result + ((deliveryAddressAdditions == null) ? 0 : deliveryAddressAdditions.hashCode());
		result = prime * result + ((deliveryCity == null) ? 0 : deliveryCity.hashCode());
		result = prime * result + ((deliveryFirstName == null) ? 0 : deliveryFirstName.hashCode());
		result = prime * result + ((deliveryHouseNumber == null) ? 0 : deliveryHouseNumber.hashCode());
		result = prime * result + ((deliveryLastName == null) ? 0 : deliveryLastName.hashCode());
		result = prime * result + ((deliveryStreetName == null) ? 0 : deliveryStreetName.hashCode());
		result = prime * result + ((deliveryZipCode == null) ? 0 : deliveryZipCode.hashCode());
		result = prime * result + ((diabetestype == null) ? 0 : diabetestype.hashCode());
		result = prime * result + ((differentDeliveryAddress == null) ? 0 : differentDeliveryAddress.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((flagBMI == null) ? 0 : flagBMI.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((hisNodeID == null) ? 0 : hisNodeID.hashCode());
		result = prime * result + ((hisPartecipantID == null) ? 0 : hisPartecipantID.hashCode());
		result = prime * result + ((houseNumber == null) ? 0 : houseNumber.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((partecipantVoucherCode == null) ? 0 : partecipantVoucherCode.hashCode());
		result = prime * result + ((partecipantstatus == null) ? 0 : partecipantstatus.hashCode());
		result = prime * result + ((participantAvailability == null) ? 0 : participantAvailability.hashCode());
		result = prime * result + ((participantAvailabilityDate == null) ? 0 : participantAvailabilityDate.hashCode());
		result = prime * result
				+ ((participantAvailabilitytoBeContact == null) ? 0 : participantAvailabilitytoBeContact.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((reRegistrationCode == null) ? 0 : reRegistrationCode.hashCode());
		result = prime * result + ((registrationProcedure == null) ? 0 : registrationProcedure.hashCode());
		result = prime * result + ((rejectedVoucherCode == null) ? 0 : rejectedVoucherCode.hashCode());
		result = prime * result + ((streetName == null) ? 0 : streetName.hashCode());
		result = prime * result
				+ ((timestampAnswerParticipationTerms == null) ? 0 : timestampAnswerParticipationTerms.hashCode());
		result = prime * result
				+ ((timestampAuthenticationEmail == null) ? 0 : timestampAuthenticationEmail.hashCode());
		result = prime * result + ((timestampEndRegisstration == null) ? 0 : timestampEndRegisstration.hashCode());
		result = prime * result + ((timestampSubmission == null) ? 0 : timestampSubmission.hashCode());
		result = prime * result + ((timestampSubmissionSMS == null) ? 0 : timestampSubmissionSMS.hashCode());
		result = prime * result + ((typePhone == null) ? 0 : typePhone.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SuitableUser2 other = (SuitableUser2) obj;
		if (additionalHintForDelivery == null) {
			if (other.additionalHintForDelivery != null)
				return false;
		} else if (!additionalHintForDelivery.equals(other.additionalHintForDelivery))
			return false;
		if (answerQuestion3 != other.answerQuestion3)
			return false;
		if (addressAdditional == null) {
			if (other.addressAdditional != null)
				return false;
		} else if (!addressAdditional.equals(other.addressAdditional))
			return false;
		if (alternativePhoneNumber == null) {
			if (other.alternativePhoneNumber != null)
				return false;
		} else if (!alternativePhoneNumber.equals(other.alternativePhoneNumber))
			return false;
		if (alternativePhoneNumber2 != other.alternativePhoneNumber2)
			return false;
		if (alternativePhonrNumber == null) {
			if (other.alternativePhonrNumber != null)
				return false;
		} else if (!alternativePhonrNumber.equals(other.alternativePhonrNumber))
			return false;
		if (answerInsuline1 != other.answerInsuline1)
			return false;
		if (answerInsuline2 != other.answerInsuline2)
			return false;
		if (answerQuestion1 != other.answerQuestion1)
			return false;
		if (answerQuestion2 != other.answerQuestion2)
			return false;
		if (assistedVoucherCode == null) {
			if (other.assistedVoucherCode != null)
				return false;
		} else if (!assistedVoucherCode.equals(other.assistedVoucherCode))
			return false;
		if (authenticationMethod != other.authenticationMethod)
			return false;
		if (bmi == null) {
			if (other.bmi != null)
				return false;
		} else if (!bmi.equals(other.bmi))
			return false;
		if (bodyHeight == null) {
			if (other.bodyHeight != null)
				return false;
		} else if (!bodyHeight.equals(other.bodyHeight))
			return false;
		if (bodyWeight == null) {
			if (other.bodyWeight != null)
				return false;
		} else if (!bodyWeight.equals(other.bodyWeight))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (correctBasicData != other.correctBasicData)
			return false;
		if (correctDeliveryAddress != other.correctDeliveryAddress)
			return false;
		if (deliveryAdditionalHints == null) {
			if (other.deliveryAdditionalHints != null)
				return false;
		} else if (!deliveryAdditionalHints.equals(other.deliveryAdditionalHints))
			return false;
		if (deliveryAddressAdditions == null) {
			if (other.deliveryAddressAdditions != null)
				return false;
		} else if (!deliveryAddressAdditions.equals(other.deliveryAddressAdditions))
			return false;
		if (deliveryCity == null) {
			if (other.deliveryCity != null)
				return false;
		} else if (!deliveryCity.equals(other.deliveryCity))
			return false;
		if (deliveryFirstName == null) {
			if (other.deliveryFirstName != null)
				return false;
		} else if (!deliveryFirstName.equals(other.deliveryFirstName))
			return false;
		if (deliveryHouseNumber == null) {
			if (other.deliveryHouseNumber != null)
				return false;
		} else if (!deliveryHouseNumber.equals(other.deliveryHouseNumber))
			return false;
		if (deliveryLastName == null) {
			if (other.deliveryLastName != null)
				return false;
		} else if (!deliveryLastName.equals(other.deliveryLastName))
			return false;
		if (deliveryStreetName == null) {
			if (other.deliveryStreetName != null)
				return false;
		} else if (!deliveryStreetName.equals(other.deliveryStreetName))
			return false;
		if (deliveryZipCode == null) {
			if (other.deliveryZipCode != null)
				return false;
		} else if (!deliveryZipCode.equals(other.deliveryZipCode))
			return false;
		if (diabetestype != other.diabetestype)
			return false;
		if (differentDeliveryAddress != other.differentDeliveryAddress)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (flagBMI != other.flagBMI)
			return false;
		if (gender != other.gender)
			return false;
		if (hisNodeID == null) {
			if (other.hisNodeID != null)
				return false;
		} else if (!hisNodeID.equals(other.hisNodeID))
			return false;
		if (hisPartecipantID == null) {
			if (other.hisPartecipantID != null)
				return false;
		} else if (!hisPartecipantID.equals(other.hisPartecipantID))
			return false;
		if (houseNumber == null) {
			if (other.houseNumber != null)
				return false;
		} else if (!houseNumber.equals(other.houseNumber))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (partecipantVoucherCode == null) {
			if (other.partecipantVoucherCode != null)
				return false;
		} else if (!partecipantVoucherCode.equals(other.partecipantVoucherCode))
			return false;
		if (partecipantstatus != other.partecipantstatus)
			return false;
		if (participantAvailability != other.participantAvailability)
			return false;
		if (participantAvailabilityDate == null) {
			if (other.participantAvailabilityDate != null)
				return false;
		} else if (!participantAvailabilityDate.equals(other.participantAvailabilityDate))
			return false;
		if (participantAvailabilitytoBeContact != other.participantAvailabilitytoBeContact)
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (reRegistrationCode == null) {
			if (other.reRegistrationCode != null)
				return false;
		} else if (!reRegistrationCode.equals(other.reRegistrationCode))
			return false;
		if (registrationProcedure == null) {
			if (other.registrationProcedure != null)
				return false;
		} else if (!registrationProcedure.equals(other.registrationProcedure))
			return false;
		if (rejectedVoucherCode == null) {
			if (other.rejectedVoucherCode != null)
				return false;
		} else if (!rejectedVoucherCode.equals(other.rejectedVoucherCode))
			return false;
		if (streetName == null) {
			if (other.streetName != null)
				return false;
		} else if (!streetName.equals(other.streetName))
			return false;
		if (timestampAnswerParticipationTerms == null) {
			if (other.timestampAnswerParticipationTerms != null)
				return false;
		} else if (!timestampAnswerParticipationTerms.equals(other.timestampAnswerParticipationTerms))
			return false;
		if (timestampAuthenticationEmail == null) {
			if (other.timestampAuthenticationEmail != null)
				return false;
		} else if (!timestampAuthenticationEmail.equals(other.timestampAuthenticationEmail))
			return false;
		if (timestampEndRegisstration == null) {
			if (other.timestampEndRegisstration != null)
				return false;
		} else if (!timestampEndRegisstration.equals(other.timestampEndRegisstration))
			return false;
		if (timestampSubmission == null) {
			if (other.timestampSubmission != null)
				return false;
		} else if (!timestampSubmission.equals(other.timestampSubmission))
			return false;
		if (timestampSubmissionSMS == null) {
			if (other.timestampSubmissionSMS != null)
				return false;
		} else if (!timestampSubmissionSMS.equals(other.timestampSubmissionSMS))
			return false;
		if (typePhone != other.typePhone)
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SuitableUser [hisPartecipantID=" + hisPartecipantID + ", hisNodeID=" + hisNodeID
				+ ", partecipantstatus=" + partecipantstatus + ", registrationProcedure=" + registrationProcedure
				+ ", partecipantVoucherCode=" + partecipantVoucherCode + ", reRegistrationCode=" + reRegistrationCode
				+ ", rejectedVoucherCode=" + rejectedVoucherCode + ", assistedVoucherCode=" + assistedVoucherCode
				+ ", answerParticipationTerms1=" + answerParticipationTerms1 + ", answerParticipationTerms2="
				+ answerParticipationTerms2 + ", answerParticipationTerms3=" + answerParticipationTerms3
				+ ", answerParticipationTerms4=" + answerParticipationTerms4 + ", timestampAnswerParticipationTerms="
				+ timestampAnswerParticipationTerms + ", diabetestype=" + diabetestype + ", gender=" + gender
				+ ", lastName=" + lastName + ", firstName=" + firstName + ", streetName=" + streetName
				+ ", houseNumber=" + houseNumber + ", addressAdditional=" + addressAdditional + ", zipCode=" + zipCode
				+ ", city=" + city + ", AdditionalHintForDelivery=" + additionalHintForDelivery + ", email=" + email
				+ ", typePhone=" + typePhone + ", phoneNumber=" + phoneNumber + ", alternativePhoneNumber="
				+ alternativePhoneNumber + ", participantAvailability=" + participantAvailability
				+ ", participantAvailabilitytoBeContact=" + participantAvailabilitytoBeContact
				+ ", participantAvailabilityDate=" + participantAvailabilityDate + ", differentDeliveryAddress="
				+ differentDeliveryAddress + ", timestampSubmission=" + timestampSubmission + ", deliveryLastName="
				+ deliveryLastName + ", deliveryFirstName=" + deliveryFirstName + ", deliveryStreetName="
				+ deliveryStreetName + ", deliveryHouseNumber=" + deliveryHouseNumber + ", deliveryAddressAdditions="
				+ deliveryAddressAdditions + ", deliveryZipCode=" + deliveryZipCode + ", deliveryCity=" + deliveryCity
				+ ", deliveryAdditionalHints=" + deliveryAdditionalHints + ", authenticationMethod="
				+ authenticationMethod + ", alternativePhoneNumber2=" + alternativePhoneNumber2
				+ ", alternativePhonrNumber=" + alternativePhonrNumber + ", timestampSubmissionSMS="
				+ timestampSubmissionSMS + ", timestampAuthenticationEmail=" + timestampAuthenticationEmail
				+ ", answerQuestion1=" + answerQuestion1 + ", answerQuestion2=" + answerQuestion2 + ", AnswerQuestion3="
				+ answerQuestion3 + ", bodyWeight=" + bodyWeight + ", bodyHeight=" + bodyHeight + ", bmi=" + bmi
				+ ", flagBMI=" + flagBMI + ", answerInsuline1=" + answerInsuline1 + ", answerInsuline2="
				+ answerInsuline2 + ", correctBasicData=" + correctBasicData + ", correctDeliveryAddress="
				+ correctDeliveryAddress + ", timestampEndRegisstration=" + timestampEndRegisstration + "]";
	}
	
	
	public String getCsvLine(char csvSeparated) {
		return hisPartecipantID.replace(";","\\;") + csvSeparated + hisNodeID.replace(";","\\;")
				+ csvSeparated + partecipantstatus + csvSeparated + registrationProcedure
				+ csvSeparated+ partecipantVoucherCode.replace(";","\\;") + csvSeparated+ reRegistrationCode.replace(";","\\;")
				+ csvSeparated+ rejectedVoucherCode.replace(";","\\;") + csvSeparated+ assistedVoucherCode.replace(";","\\;")
				+ csvSeparated+ answerParticipationTerms1 + csvSeparated
				+ answerParticipationTerms2 + csvSeparated + answerParticipationTerms3
				+ csvSeparated + answerParticipationTerms4 + csvSeparated
				+ timestampAnswerParticipationTerms.replace(";","\\;") + csvSeparated + diabetestype + csvSeparated + gender
				+ csvSeparated + lastName.replace(";","\\;") + csvSeparated+ firstName.replace(";","\\;") + csvSeparated+ streetName.replace(";","\\;")
				+ csvSeparated + houseNumber + csvSeparated + addressAdditional.replace(";","\\;") + csvSeparated + zipCode.replace(";","\\;")
				+ csvSeparated + city.replace(";","\\;") + csvSeparated + additionalHintForDelivery.replace(";","\\;") + csvSeparated + email.replace(";","\\;")
				+ csvSeparated + typePhone + csvSeparated + phoneNumber.replace(";","\\;") + csvSeparated
				+ alternativePhoneNumber.replace(";","\\;") + csvSeparated + participantAvailability
				+ csvSeparated + participantAvailabilitytoBeContact
				+ csvSeparated + participantAvailabilityDate.replace(";","\\;") + csvSeparated
				+ differentDeliveryAddress + csvSeparated + timestampSubmission.replace(";","\\;") + csvSeparated
				+ deliveryLastName.replace(";","\\;") + csvSeparated + deliveryFirstName.replace(";","\\;") + csvSeparated
				+ deliveryStreetName.replace(";","\\;") + csvSeparated + deliveryHouseNumber.replace(";","\\;") + csvSeparated
				+ deliveryAddressAdditions.replace(";","\\;") + csvSeparated + deliveryZipCode.replace(";","\\;") + csvSeparated + deliveryCity.replace(";","\\;")
				+ csvSeparated + deliveryAdditionalHints.replace(";","\\;") + csvSeparated
				+ authenticationMethod + csvSeparated+ alternativePhoneNumber2
				+ csvSeparated + alternativePhonrNumber.replace(";","\\;") + csvSeparated
				+ timestampSubmissionSMS + csvSeparated + timestampAuthenticationEmail.replace(";","\\;")
				+ csvSeparated + answerQuestion1 + csvSeparated + answerQuestion2 + csvSeparated
				+ answerQuestion3 + csvSeparated+ bodyWeight.replace(";","\\;") + csvSeparated+ bodyHeight.replace(";","\\;") + csvSeparated+ bmi.replace(";","\\;")
				+ csvSeparated + flagBMI + csvSeparated + answerInsuline1 + csvSeparated
				+ answerInsuline2 + csvSeparated + correctBasicData + csvSeparated
				+ correctDeliveryAddress + csvSeparated + timestampEndRegisstration.replace(";","\\;");
	}
}
