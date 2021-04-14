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
@Table(name = "shipping_to_send")
@NamedQueries({
	@NamedQuery(name = "GetAllShipToSend", query = "SELECT ps FROM ShippingToSend ps WHERE ps.status = :status and ps.idPatient is not null"),
	@NamedQuery(name = "UpdateShipStatus", query = "UPDATE ShippingToSend ps SET status = :status  WHERE ps.idPatient = :idPatient"),
	@NamedQuery(name = "UpdateShipIdPatient", query = "UPDATE ShippingToSend ps SET idPatient = :idPatient  WHERE ps.idRegCod = :idRegCod")})

public class ShippingToSend implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String NQ_GET_SHIPPING_TO_SEND = "GetAllShipToSend";
	public static final String NQ_UPD_SHIPPING_STATUS = "UpdateShipStatus";
	public static final String NQ_UPD_SHIPPING_PATIENT = "UpdateShipIdPatient";
	
	private Integer idShipping;
	private Integer idRegCod;
	private String idPatient;
	private String firstname;
	private String lastname;
	private String address;
	private String houseNumber;
	private String city;
	private String postalCode;
	private String additions;
	private String additionalHints;
	private String status;
	
	// Raffaele - 05.01.2017 - Christian W. asks me to add this two field (skype on 05.01.2017 at 15:50)
	private String  code;
	private String  typeCode;
	
	// Raffaele - 12.01.2017 - Christian W. asks me to add one field (mail 12.01.2017 at 9:50)
	private Date registrationCompleted;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_shipping", unique = true, nullable = false)
	public Integer getIdShipping() {
		return idShipping;
	}
	public void setIdShipping(Integer idShipping) {
		this.idShipping = idShipping;
	}

	@Column(name = "id_reg_cod")
	public Integer getIdRegCod() {
		return idRegCod;
	}
	public void setIdRegCod(Integer idRegCod) {
		this.idRegCod = idRegCod;
	}
	
	@Column(name = "id_patient")
	public String getIdPatient() {
		return idPatient;
	}
	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}
	
	@Column(name = "firstname")
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@Column(name = "lastname")
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	
	@Column(name = "additions")
	public String getAdditions() {
		return additions;
	}
	public void setAdditions(String additions) {
		this.additions = additions;
	}
	
	@Column(name = "additional_hints")
	public String getAdditionalHints() {
		return additionalHints;
	}
	public void setAdditionalHints(String additionalHints) {
		this.additionalHints = additionalHints;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name = "type_code")
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	@Column(name = "registration_completed")
	public Date getRegistrationCompleted() {
		return registrationCompleted;
	}
	public void setRegistrationCompleted(Date registrationCompleted) {
		this.registrationCompleted = registrationCompleted;
	}
	
}