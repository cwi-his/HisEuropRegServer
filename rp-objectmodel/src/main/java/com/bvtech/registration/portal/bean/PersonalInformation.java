package com.bvtech.registration.portal.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "personals_informations")

@NamedQueries({
	@NamedQuery(name = "GetAllPerInfByCode", query = "SELECT pi FROM PersonalInformation pi WHERE pi.idPerInf = :idPerInf"),
	@NamedQuery(name = "UpdatePersonalInfoDeliveryAddress", query = "UPDATE PersonalInformation pi SET "
			+ "  name =:name"
			+ ", surname =:surname"
			+ ", address =:address"
			+ ", houseNumber =:houseNumber"
			+ ", postalCode =:postalCode"
			+ ", city =:city"
			+ ", addressAdditional =:addressAdditional"
			+ ", note =:note"
			+ ", optName =:optName"
			+ ", optSurname=:optSurname"
			+ ", optAddress=:optAddress"
			+ ", optAddressAdditional=:optAddressAdditional"
			+ ", optHouseNumber=:optHouseNumber"
			+ ", optCity=:optCity"
			+ ", optNote=:optNote"
			+ ", optPostalCode=:optPostalCode"
			+ ", corrBD=:corrBD"
			+ ", corrDA=:corrDA WHERE pi.idPerInf = :idPerInf")})
public class PersonalInformation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String NQ_GET_ALL_PER_INF_BY_CODE = "GetAllPerInfByCode";
	public static final String NQ_UPD_PER_INFO_DELIVERY_ADDRESS = "UpdatePersonalInfoDeliveryAddress";

	private Integer idPerInf;
	private String sex;
	private String name;
	private String surname;
	private Date dateBirth;
	private String address;
	private String addressAdditional;
	private String email;
	private String telephoneNumber;
	private String telephoneNumber2;
	private Integer phoneLandline;
	private String city;
	private String postalCode;
	private Integer availability;
	private Integer availability2;
	private Integer availability3;
	private Integer availability4;
	private String note;
	private Integer contactNow;
	private Date fromDate;
	private String optName;
	private String optSurname;    
	private String optAddress;
	private String optHouseNumber;
	private String optAddressAdditional;
	private String optNote;
	private String optCity;
	private String optPostalCode;
	private Date insertDate;
	private String houseNumber;
	
	private Integer corrBD;
	private Integer corrDA;
	
	private Integer sendNow;
	private Date	sendFromDate;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_per_inf", unique = true, nullable = false)
	public Integer getIdPerInf() {
		return idPerInf;
	}

	public void setIdPerInf(Integer idPerInf) {
		this.idPerInf = idPerInf;
	}

	@Column(name = "sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "surname")
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "date_birth")
	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "house_number")
	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	@Column(name = "address_additional")
	public String getAddressAdditional() {
		return addressAdditional;
	}

	public void setAddressAdditional(String addressAdditional) {
		this.addressAdditional = addressAdditional;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "telephone_number")
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	@Column(name = "telephone_number2")
	public String getTelephoneNumber2() {
		return telephoneNumber2;
	}

	public void setTelephoneNumber2(String telephoneNumber2) {
		this.telephoneNumber2 = telephoneNumber2;
	}
	
	@Column(name = "phone_landline")
	public Integer getPhoneLandline() {
		return phoneLandline;
	}

	public void setPhoneLandline(Integer phoneLandline) {
		this.phoneLandline = phoneLandline;
	}
	
	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "postal_code")
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	@Column(name = "availability")
	public Integer getAvailability() {
		return availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}
	
	@Column(name = "availability2")
	public Integer getAvailability2() {
		return availability2;
	}

	public void setAvailability2(Integer availability2) {
		this.availability2 = availability2;
	}

	@Column(name = "availability3")
	public Integer getAvailability3() {
		return availability3;
	}

	public void setAvailability3(Integer availability3) {
		this.availability3 = availability3;
	}

	@Column(name = "availability4")
	public Integer getAvailability4() {
		return availability4;
	}

	public void setAvailability4(Integer availability4) {
		this.availability4 = availability4;
	}
	
	@Column(name = "note")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Column(name = "contact_now")
	public Integer getContactNow() {
		return contactNow;
	}

	public void setContactNow(Integer contactNow) {
		this.contactNow = contactNow;
	}
	
	@Column(name = "from_date")
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	@Column(name = "opt_name")
	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	@Column(name = "opt_surname")
	public String getOptSurname() {
		return optSurname;
	}

	public void setOptSurname(String optSurname) {
		this.optSurname = optSurname;
	}
	
	@Column(name = "opt_address")
	public String getOptAddress() {
		return optAddress;
	}

	public void setOptAddress(String optAddress) {
		this.optAddress = optAddress;
	}

	@Column(name = "opt_house_number")
	public String getOptHouseNumber() {
		return optHouseNumber;
	}

	public void setOptHouseNumber(String optHouseNumber) {
		this.optHouseNumber = optHouseNumber;
	}
	
	@Column(name = "opt_address_additional")
	public String getOptAddressAdditional() {
		return optAddressAdditional;
	}

	public void setOptAddressAdditional(String optAddressAdditional) {
		this.optAddressAdditional = optAddressAdditional;
	}
	
	@Column(name = "opt_note")
	public String getOptNote() {
		return optNote;
	}

	public void setOptNote(String optNote) {
		this.optNote = optNote;
	}
	
	@Column(name = "opt_city")
	public String getOptCity() {
		return optCity;
	}

	public void setOptCity(String optCity) {
		this.optCity = optCity;
	}
	
	@Column(name = "opt_postal_code")
	public String getOptPostalCode() {
		return optPostalCode;
	}

	public void setOptPostalCode(String optPostalCode) {
		this.optPostalCode = optPostalCode;
	}
	
	@Column(name = "insert_date")
	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
	@Column(name = "corr_bd")
	public Integer getCorrBD() {
		return corrBD;
	}

	public void setCorrBD(Integer corrBD) {
		this.corrBD = corrBD;
	}

	@Column(name = "corr_da")
	public Integer getCorrDA() {
		return corrDA;
	}

	public void setCorrDA(Integer corrDA) {
		this.corrDA = corrDA;
	}
	
	@Column(name = "send_now")
	public Integer getSendNow() {
		return sendNow;
	}

	public void setSendNow(Integer sendNow) {
		this.sendNow = sendNow;
	}
	
	@Column(name = "send_from_date")
	public Date getSendFromDate() {
		return sendFromDate;
	}

	public void setSendFromDate(Date sendFromDate) {
		this.sendFromDate = sendFromDate;
	}
}