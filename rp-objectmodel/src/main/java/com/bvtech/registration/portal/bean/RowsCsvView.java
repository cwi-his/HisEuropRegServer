package com.bvtech.registration.portal.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.bvtech.registration.portal.tasks.bean.csv.AnswerInsuline2Enum;
import com.bvtech.registration.portal.tasks.bean.csv.AuthenticationMethodEnum;
import com.bvtech.registration.portal.tasks.bean.csv.DiabetesTypeEnum;
import com.bvtech.registration.portal.tasks.bean.csv.GenderEnum;
import com.bvtech.registration.portal.tasks.bean.csv.PartecipantStatusEnum;
import com.bvtech.registration.portal.tasks.bean.csv.ParticipantAvailabilityEnum;
import com.bvtech.registration.portal.tasks.bean.csv.QuestionnaireAnswer2ValuesEnum;
import com.bvtech.registration.portal.tasks.bean.csv.QuestionnaireAnswer3sValueEnum;
import com.bvtech.registration.portal.tasks.bean.csv.RegistrationProcedureEnum;
import com.bvtech.registration.portal.tasks.bean.csv.TypePhoneEnum;

/**
 * The persistent class for the rows_csv_view database table.
 *
 * @NamedQuery(name = "RetrieveCsvRows", query = "SELECT r FROM RowsCsvView r, Tasks t WHERE (t.idTask='CREATE_CSV_JOB' and r.lastUpdate > STR_TO_DATE(t.token, '%Y-%m-%d %H:%i:%s')) ")
 */
@Entity
@Table(name = "rows_csv_view")
@NamedQuery(name = "RetrieveCsvRows", query = "SELECT r FROM RowsCsvView r, Tasks t WHERE (t.idTask='CREATE_CSV_JOB' and r.lastUpdate > STR_TO_DATE(t.token, '%Y-%m-%d %H:%i:%s')) ")
public class RowsCsvView implements Serializable {
	private static String TYPE_CODE_USER = "U";
	private static String TYPE_CODE_COACH="C";
	private static String DATE_FORMAT="yyyy_MM_dd_HH_mm_ss";
	
	private static final long serialVersionUID = 1L;

	public static String RETRIEVE_CSV_ROWS = "RetrieveCsvRows";

	@Id
	@Column(name = "code")
	private String code;

	@Column(name = "address")
	private String address;
	
	@Column(name="type_diabetes")
	private Integer typeDiabates;

	@Column(name = "address_additional")
	private String addressAdditional;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "authentication_date")
	private Date authenticationDate;

	@Column(name = "availability")
	private Integer availability;
	
	@Column(name = "availability2")
	private Integer availability2;
	
	@Column(name = "availability3")
	private Integer availability3;
	
	@Column(name = "availability4")
	private Integer availability4;

	@Column(name = "bmi")
	private Integer bmi;

	@Column(name = "city")
	private String city;

	@Column(name = "contact_now")
	private Integer contactNow;

	@Column(name = "email")
	private String email;

	@Column(name = "external_nodeid")
	private String externalNodeid;

	@Column(name = "external_uuid")
	private String externalUuid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "from_date")
	private Date fromDate;

	@Column(name = "id_pro_typ_aut")
	private Integer idProTypAut;

	@Column(name = "id_sta_use")
	private Integer idStaUse;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insert_date")
	private Date insertDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update")
	private Date lastUpdate;

	@Column(name = "name")
	private String name;

	@Column(name = "note")
	private String note;

	@Column(name = "opt_address")
	private String optAddress;

	@Column(name = "opt_address_additional")
	private String optAddressAdditional;

	@Column(name = "opt_city")
	private String optCity;

	@Column(name = "opt_name")
	private String optName;

	@Column(name = "opt_note")
	private String optNote;

	@Column(name = "opt_postal_code")
	private String optPostalCode;

	@Column(name = "opt_surname")
	private String optSurname;

	@Column(name = "phone_landline")
	private Integer phoneLandline;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "question_height")
	private Integer questionHeight;

	@Column(name = "question_insuline")
	private Integer questionInsuline;

	@Column(name = "question_insuline_b")
	private Integer questionInsulineB;

	@Column(name = "question_section1_a")
	private Integer questionSection1A;

	@Column(name = "question_section1_b")
	private Integer questionSection1B;

	@Column(name = "question_section2_a")
	private Integer questionSection2A;

	@Column(name = "question_section2_b")
	private Integer questionSection2B;

	@Column(name = "question_weight")
	private Integer questionWeight;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registration_completed")
	private Date registrationCompleted;

	@Column(name = "reject_code")
	private String rejectCode;

	@Column(name = "reregister_code")
	private String reregisterCode;

	@Column(name = "sex")
	private String sex;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "submission_terms")
	private Date submissionTerms;

	@Column(name = "surname")
	private String surname;

	@Column(name = "telephone_number")
	private String telephoneNumber;

	@Column(name = "type_code")
	private String typeCode;
	
	@Column(name = "house_number")
	private String houseNumber;
	
	@Column(name = "opt_house_number")
	private String optHouseNumber;

	@Column(name = "correction_basic_data")
	private Integer correctionBasicData;
	
	@Column(name = "correction_delivery_address")
	private Integer correctionDeliveryAddress;
	
	// CR 03.02.2017: ChristianW. say add birthdate to csv (skype)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_birth")
	private Date dateBirth;
	
	
	@Column(name = "vnr")
	private String vnr;
	
	public RowsCsvView() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressAdditional() {
		return this.addressAdditional;
	}

	public void setAddressAdditional(String addressAdditional) {
		this.addressAdditional = addressAdditional;
	}

	public Date getAuthenticationDate() {
		return this.authenticationDate;
	}

	public void setAuthenticationDate(Date authenticationDate) {
		this.authenticationDate = authenticationDate;
	}

	public Integer getAvailability() {
		return this.availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public Integer getBmi() {
		return this.bmi;
	}

	public void setBmi(Integer bmi) {
		this.bmi = bmi;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getContactNow() {
		return this.contactNow;
	}

	public void setContactNow(Integer contactNow) {
		this.contactNow = contactNow;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExternalNodeid() {
		return this.externalNodeid;
	}

	public void setExternalNodeid(String externalNodeid) {
		this.externalNodeid = externalNodeid;
	}

	public String getExternalUuid() {
		return this.externalUuid;
	}

	public void setExternalUuid(String externalUuid) {
		this.externalUuid = externalUuid;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Integer getIdProTypAut() {
		return this.idProTypAut;
	}

	public void setIdProTypAut(Integer idProTypAut) {
		this.idProTypAut = idProTypAut;
	}

	public Integer getIdStaUse() {
		return this.idStaUse;
	}

	public void setIdStaUse(Integer idStaUse) {
		this.idStaUse = idStaUse;
	}

	public Date getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOptAddress() {
		return this.optAddress;
	}

	public void setOptAddress(String optAddress) {
		this.optAddress = optAddress;
	}

	public String getOptAddressAdditional() {
		return this.optAddressAdditional;
	}

	public void setOptAddressAdditional(String optAddressAdditional) {
		this.optAddressAdditional = optAddressAdditional;
	}

	public String getOptCity() {
		return this.optCity;
	}

	public void setOptCity(String optCity) {
		this.optCity = optCity;
	}

	public String getOptName() {
		return this.optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public String getOptNote() {
		return this.optNote;
	}

	public void setOptNote(String optNote) {
		this.optNote = optNote;
	}

	public String getOptPostalCode() {
		return this.optPostalCode;
	}

	public void setOptPostalCode(String optPostalCode) {
		this.optPostalCode = optPostalCode;
	}

	public String getOptSurname() {
		return this.optSurname;
	}

	public void setOptSurname(String optSurname) {
		this.optSurname = optSurname;
	}

	public Integer getPhoneLandline() {
		return this.phoneLandline;
	}

	public void setPhoneLandline(Integer phoneLandline) {
		this.phoneLandline = phoneLandline;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Integer getQuestionHeight() {
		return this.questionHeight;
	}

	public void setQuestionHeight(Integer questionHeight) {
		this.questionHeight = questionHeight;
	}

	public Integer getQuestionInsuline() {
		return questionInsuline;
	}

	public void setQuestionInsuline(Integer questionInsuline) {
		this.questionInsuline = questionInsuline;
	}

	public Integer getQuestionInsulineB() {
		return this.questionInsulineB;
	}

	public void setQuestionInsulineB(Integer questionInsulineB) {
		this.questionInsulineB = questionInsulineB;
	}

	public Integer getQuestionSection1A() {
		return this.questionSection1A;
	}

	public void setQuestionSection1A(Integer questionSection1A) {
		this.questionSection1A = questionSection1A;
	}

	public Integer getQuestionSection1B() {
		return this.questionSection1B;
	}

	public void setQuestionSection1B(Integer questionSection1B) {
		this.questionSection1B = questionSection1B;
	}

	public Integer getQuestionSection2A() {
		return this.questionSection2A;
	}

	public void setQuestionSection2A(Integer questionSection2A) {
		this.questionSection2A = questionSection2A;
	}

	public Integer getQuestionSection2B() {
		return this.questionSection2B;
	}

	public void setQuestionSection2B(Integer questionSection2B) {
		this.questionSection2B = questionSection2B;
	}

	public Integer getQuestionWeight() {
		return this.questionWeight;
	}

	public void setQuestionWeight(Integer questionWeight) {
		this.questionWeight = questionWeight;
	}

	public Date getRegistrationCompleted() {
		return this.registrationCompleted;
	}

	public void setRegistrationCompleted(Date registrationCompleted) {
		this.registrationCompleted = registrationCompleted;
	}

	public String getRejectCode() {
		return this.rejectCode;
	}

	public void setRejectCode(String rejectCode) {
		this.rejectCode = rejectCode;
	}

	public String getReregisterCode() {
		return this.reregisterCode;
	}

	public void setReregisterCode(String reregisterCode) {
		this.reregisterCode = reregisterCode;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getSubmissionTerms() {
		return this.submissionTerms;
	}

	public void setSubmissionTerms(Date submissionTerms) {
		this.submissionTerms = submissionTerms;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getOptHouseNumber() {
		return optHouseNumber;
	}

	public void setOptHouseNumber(String optHouseNumber) {
		this.optHouseNumber = optHouseNumber;
	}
	

	public Integer getTypeDiabates() {
		return typeDiabates;
	}

	public void setTypeDiabates(Integer typeDiabates) {
		this.typeDiabates = typeDiabates;
	}

	public Integer getAvailability2() {
		return availability2;
	}

	public void setAvailability2(Integer availability2) {
		this.availability2 = availability2;
	}

	public Integer getAvailability3() {
		return availability3;
	}

	public void setAvailability3(Integer availability3) {
		this.availability3 = availability3;
	}

	public Integer getAvailability4() {
		return availability4;
	}

	public void setAvailability4(Integer availability4) {
		this.availability4 = availability4;
	}

	public Integer getCorrectionBasicData() {
		return correctionBasicData;
	}

	public void setCorrectionBasicData(Integer correctionBasicData) {
		this.correctionBasicData = correctionBasicData;
	}

	public Integer getCorrectionDeliveryAddress() {
		return correctionDeliveryAddress;
	}

	public void setCorrectionDeliveryAddress(Integer correctionDeliveryAddress) {
		this.correctionDeliveryAddress = correctionDeliveryAddress;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	
	public String getVnr() {
		return this.vnr;
	}
	
	public void setVnr(String vnr) {
		this.vnr = vnr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((addressAdditional == null) ? 0 : addressAdditional.hashCode());
		result = prime * result + ((authenticationDate == null) ? 0 : authenticationDate.hashCode());
		result = prime * result + availability;
		result = prime * result + bmi;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + contactNow;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((externalNodeid == null) ? 0 : externalNodeid.hashCode());
		result = prime * result + ((externalUuid == null) ? 0 : externalUuid.hashCode());
		result = prime * result + ((fromDate == null) ? 0 : fromDate.hashCode());
		result = prime * result + idProTypAut;
		result = prime * result + idStaUse;
		result = prime * result + ((insertDate == null) ? 0 : insertDate.hashCode());
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((optAddress == null) ? 0 : optAddress.hashCode());
		result = prime * result + ((optAddressAdditional == null) ? 0 : optAddressAdditional.hashCode());
		result = prime * result + ((optCity == null) ? 0 : optCity.hashCode());
		result = prime * result + ((optName == null) ? 0 : optName.hashCode());
		result = prime * result + ((optNote == null) ? 0 : optNote.hashCode());
		result = prime * result + ((optPostalCode == null) ? 0 : optPostalCode.hashCode());
		result = prime * result + ((optSurname == null) ? 0 : optSurname.hashCode());
		result = prime * result + phoneLandline;
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime * result + questionHeight;
		result = prime * result + questionInsuline;
		result = prime * result + questionInsulineB;
		result = prime * result + questionSection1A;
		result = prime * result + questionSection1B;
		result = prime * result + questionSection2A;
		result = prime * result + questionSection2B;
		result = prime * result + questionWeight;
		result = prime * result + ((registrationCompleted == null) ? 0 : registrationCompleted.hashCode());
		result = prime * result + ((rejectCode == null) ? 0 : rejectCode.hashCode());
		result = prime * result + ((reregisterCode == null) ? 0 : reregisterCode.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((submissionTerms == null) ? 0 : submissionTerms.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((telephoneNumber == null) ? 0 : telephoneNumber.hashCode());
		result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
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
		RowsCsvView other = (RowsCsvView) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (addressAdditional == null) {
			if (other.addressAdditional != null)
				return false;
		} else if (!addressAdditional.equals(other.addressAdditional))
			return false;
		if (authenticationDate == null) {
			if (other.authenticationDate != null)
				return false;
		} else if (!authenticationDate.equals(other.authenticationDate))
			return false;
		if (availability != other.availability)
			return false;
		if (bmi != other.bmi)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (contactNow != other.contactNow)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (externalNodeid == null) {
			if (other.externalNodeid != null)
				return false;
		} else if (!externalNodeid.equals(other.externalNodeid))
			return false;
		if (externalUuid == null) {
			if (other.externalUuid != null)
				return false;
		} else if (!externalUuid.equals(other.externalUuid))
			return false;
		if (fromDate == null) {
			if (other.fromDate != null)
				return false;
		} else if (!fromDate.equals(other.fromDate))
			return false;
		if (idProTypAut != other.idProTypAut)
			return false;
		if (idStaUse != other.idStaUse)
			return false;
		if (insertDate == null) {
			if (other.insertDate != null)
				return false;
		} else if (!insertDate.equals(other.insertDate))
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (optAddress == null) {
			if (other.optAddress != null)
				return false;
		} else if (!optAddress.equals(other.optAddress))
			return false;
		if (optAddressAdditional == null) {
			if (other.optAddressAdditional != null)
				return false;
		} else if (!optAddressAdditional.equals(other.optAddressAdditional))
			return false;
		if (optCity == null) {
			if (other.optCity != null)
				return false;
		} else if (!optCity.equals(other.optCity))
			return false;
		if (optName == null) {
			if (other.optName != null)
				return false;
		} else if (!optName.equals(other.optName))
			return false;
		if (optNote == null) {
			if (other.optNote != null)
				return false;
		} else if (!optNote.equals(other.optNote))
			return false;
		if (optPostalCode == null) {
			if (other.optPostalCode != null)
				return false;
		} else if (!optPostalCode.equals(other.optPostalCode))
			return false;
		if (optSurname == null) {
			if (other.optSurname != null)
				return false;
		} else if (!optSurname.equals(other.optSurname))
			return false;
		if (phoneLandline != other.phoneLandline)
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		if (questionHeight != other.questionHeight)
			return false;
		if (questionInsuline != other.questionInsuline)
			return false;
		if (questionInsulineB != other.questionInsulineB)
			return false;
		if (questionSection1A != other.questionSection1A)
			return false;
		if (questionSection1B != other.questionSection1B)
			return false;
		if (questionSection2A != other.questionSection2A)
			return false;
		if (questionSection2B != other.questionSection2B)
			return false;
		if (questionWeight != other.questionWeight)
			return false;
		if (registrationCompleted == null) {
			if (other.registrationCompleted != null)
				return false;
		} else if (!registrationCompleted.equals(other.registrationCompleted))
			return false;
		if (rejectCode == null) {
			if (other.rejectCode != null)
				return false;
		} else if (!rejectCode.equals(other.rejectCode))
			return false;
		if (reregisterCode == null) {
			if (other.reregisterCode != null)
				return false;
		} else if (!reregisterCode.equals(other.reregisterCode))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (submissionTerms == null) {
			if (other.submissionTerms != null)
				return false;
		} else if (!submissionTerms.equals(other.submissionTerms))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (telephoneNumber == null) {
			if (other.telephoneNumber != null)
				return false;
		} else if (!telephoneNumber.equals(other.telephoneNumber))
			return false;
		if (typeCode == null) {
			if (other.typeCode != null)
				return false;
		} else if (!typeCode.equals(other.typeCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RowsCsvView [code=" + code + ", address=" + address + ", addressAdditional=" + addressAdditional
				+ ", authenticationDate=" + authenticationDate + ", availability=" + availability + ", bmi=" + bmi
				+ ", city=" + city + ", contactNow=" + contactNow + ", email=" + email + ", externalNodeid="
				+ externalNodeid + ", externalUuid=" + externalUuid + ", fromDate=" + fromDate + ", idProTypAut="
				+ idProTypAut + ", idStaUse=" + idStaUse + ", insertDate=" + insertDate + ", lastUpdate=" + lastUpdate
				+ ", name=" + name + ", note=" + note + ", optAddress=" + optAddress + ", optAddressAdditional="
				+ optAddressAdditional + ", optCity=" + optCity + ", optName=" + optName + ", optNote=" + optNote
				+ ", optPostalCode=" + optPostalCode + ", optSurname=" + optSurname + ", phoneLandline=" + phoneLandline
				+ ", phoneNumber=" + phoneNumber + ", postalCode=" + postalCode + ", questionHeight=" + questionHeight
				+ ", questionInsuline=" + questionInsuline + ", questionInsulineB=" + questionInsulineB
				+ ", questionSection1A=" + questionSection1A + ", questionSection1B=" + questionSection1B
				+ ", questionSection2A=" + questionSection2A + ", questionSection2B=" + questionSection2B
				+ ", questionWeight=" + questionWeight + ", registrationCompleted=" + registrationCompleted
				+ ", rejectCode=" + rejectCode + ", reregisterCode=" + reregisterCode + ", sex=" + sex
				+ ", submissionTerms=" + submissionTerms + ", surname=" + surname + ", telephoneNumber="
				+ telephoneNumber + ", typeCode=" + typeCode + "]";
	}

	public String getCsvLine(char csvSeparated) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		
		String registrationProcedureValue;
		if(typeCode.equalsIgnoreCase(TYPE_CODE_COACH)) registrationProcedureValue=RegistrationProcedureEnum.Fremdregistriert.toString(); // assisted
		else registrationProcedureValue=RegistrationProcedureEnum.Selbstregistriert.toString(); // self registration
		if(reregisterCode!=null) registrationProcedureValue = RegistrationProcedureEnum.ReregCodeFreischaltung.toString(); // reregistration code used
		
		String participantAvailability="";
		if(availability!=null && availability==1) participantAvailability=ParticipantAvailabilityEnum.morgens.toString();
		if(availability2!=null && availability2==1) participantAvailability+=(participantAvailability.equals("")?ParticipantAvailabilityEnum.vormittags.toString():"/"+ParticipantAvailabilityEnum.vormittags.toString());
		if(availability3!=null && availability3==1) participantAvailability+=(participantAvailability.equals("")?ParticipantAvailabilityEnum.nachmittags.toString():"/"+ParticipantAvailabilityEnum.nachmittags.toString());
		if(availability4!=null && availability4==1) participantAvailability+=(participantAvailability.equals("")?ParticipantAvailabilityEnum.abends.toString():"/"+ParticipantAvailabilityEnum.abends.toString());
			
		return  (externalUuid==null?"":externalUuid) + csvSeparated + 
				(externalNodeid==null?"":externalNodeid) + csvSeparated + 
				PartecipantStatusEnum.parse(idStaUse) + csvSeparated + 
				registrationProcedureValue + csvSeparated + 
				(typeCode.equalsIgnoreCase(TYPE_CODE_USER)?code:"") + csvSeparated + 
				(reregisterCode==null?"":reregisterCode) + csvSeparated +
				(rejectCode==null?"":rejectCode) + csvSeparated + 
				(typeCode.equalsIgnoreCase(TYPE_CODE_COACH)?code:"")+ csvSeparated + //this is assistedVoucherCode	 
				(submissionTerms!=null) + csvSeparated + //this is answerParticipationTerms1
				(submissionTerms!=null) + csvSeparated + //this is answerParticipationTerms2
				(submissionTerms!=null) + csvSeparated + //this is answerParticipationTerms3
				(submissionTerms!=null?sdf.format(submissionTerms):"") + csvSeparated + 
				(typeDiabates==null?"":DiabetesTypeEnum.parse(typeDiabates).toString()) + csvSeparated + // this is diabetestype  
				(sex==null?"":(sex.equalsIgnoreCase("F")?GenderEnum.Frau:GenderEnum.Mann)) + csvSeparated + 
				getCSVStringField(surname) + csvSeparated + 
				getCSVStringField(name) + csvSeparated + 
				(dateBirth!=null?sdf.format(dateBirth):"") + csvSeparated +
				getCSVStringField(address) + csvSeparated + 
				getCSVStringField(houseNumber) + csvSeparated + 
				getCSVStringField(addressAdditional) + csvSeparated + 
				getCSVStringField(postalCode) + csvSeparated + 
				getCSVStringField(city) + csvSeparated + 
				getCSVStringField(note) + csvSeparated + 
				getCSVStringField(email) + csvSeparated + 
				(phoneLandline==null?"":phoneLandline==1?TypePhoneEnum.Festnetz:TypePhoneEnum.Mobil) + csvSeparated + 
				getCSVStringField(telephoneNumber) + csvSeparated +
				getCSVStringField(phoneNumber) + csvSeparated + 
				participantAvailability + csvSeparated + 
				(contactNow==null?"":(contactNow==0?QuestionnaireAnswer2ValuesEnum.Nein:QuestionnaireAnswer2ValuesEnum.Ja))+ csvSeparated + 
				(fromDate==null?"":sdf.format(fromDate)) + csvSeparated +
				(optName==null && name==null?"":(optName!=null?QuestionnaireAnswer2ValuesEnum.Ja:QuestionnaireAnswer2ValuesEnum.Nein)) + csvSeparated + 
				(insertDate==null?"":sdf.format(insertDate)) + csvSeparated + // timestampSubmission
				(optSurname==null?getCSVStringField(surname):optSurname) + csvSeparated + 
				(optName==null?getCSVStringField(name):optName) + csvSeparated +
				(optAddress==null?getCSVStringField(address):optAddress)+ csvSeparated + 
				(optHouseNumber==null?getCSVStringField(houseNumber):optHouseNumber) + csvSeparated +
				(optAddressAdditional==null?getCSVStringField(addressAdditional):optAddressAdditional)+ csvSeparated + 
				(optPostalCode==null?getCSVStringField(postalCode):optPostalCode)+ csvSeparated + 
				(optCity==null?getCSVStringField(city):optCity) + csvSeparated + 
				(optNote==null?getCSVStringField(note):optNote) + csvSeparated +
				(idProTypAut==null?"":(idProTypAut==1?AuthenticationMethodEnum.Email_Link:AuthenticationMethodEnum.SMS)) + csvSeparated + // this is AuthenticationMethod
				(phoneNumber==null?"":(phoneNumber.equals(telephoneNumber)?QuestionnaireAnswer2ValuesEnum.Nein:QuestionnaireAnswer2ValuesEnum.Ja))	+ csvSeparated + 
				(phoneNumber!=null?phoneNumber:"") + csvSeparated +
				(idProTypAut!=null && idProTypAut==1?sdf.format(authenticationDate):"") + csvSeparated + // this is authentication_date for sms
				(idProTypAut!=null && idProTypAut==2?sdf.format(authenticationDate):"")+ csvSeparated +  // this is authentication_date for email
				(questionSection1A==null?"": QuestionnaireAnswer3sValueEnum.parse(questionSection1A))+ csvSeparated + 
				(questionSection1B==null?"": QuestionnaireAnswer3sValueEnum.parse(questionSection1B))+ csvSeparated +
				(questionSection2A==null?"": QuestionnaireAnswer3sValueEnum.parse(questionSection2A)) + csvSeparated +
				(questionSection2B==null?"": QuestionnaireAnswer3sValueEnum.parse(questionSection2B)) + csvSeparated +
				(questionWeight==null?"":questionWeight) + csvSeparated + 
				(questionHeight==null?"":questionHeight) + csvSeparated + 
				(bmi==null?"":bmi) + csvSeparated + 
				(bmi==null?"":(bmi!=null && bmi.intValue()<25?QuestionnaireAnswer2ValuesEnum.Ja:QuestionnaireAnswer2ValuesEnum.Nein)) /* flagBMI */ + csvSeparated + 
				(questionInsuline==null?"":QuestionnaireAnswer2ValuesEnum.parse(questionInsuline)) + csvSeparated +
				(questionInsulineB==null?"":AnswerInsuline2Enum.parse(questionInsulineB.intValue())) + csvSeparated + 
				(correctionBasicData==null?"":correctionBasicData==1?QuestionnaireAnswer2ValuesEnum.Ja:QuestionnaireAnswer2ValuesEnum.Nein) + csvSeparated +
				(correctionDeliveryAddress==null?"":(correctionDeliveryAddress==1?QuestionnaireAnswer2ValuesEnum.Ja:QuestionnaireAnswer2ValuesEnum.Nein)) + csvSeparated +
				sdf.format(registrationCompleted) + csvSeparated +
				getCSVStringField(code) + csvSeparated + // attendance_id
				getCSVStringField(vnr);
	}
	
	private String getCSVStringField(String value){
		if(value==null) return "";
		return value;
	}

}