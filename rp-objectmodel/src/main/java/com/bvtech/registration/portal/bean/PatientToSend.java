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
@Table(name = "patient_to_send")
@NamedQueries({
	@NamedQuery(name = "GetAllPatToSend", query = "SELECT ps FROM PatientToSend ps WHERE ps.status = :status"),
	@NamedQuery(name = "UpdateStatus", query = "UPDATE PatientToSend ps SET status = :status  WHERE ps.idRegCod = :idRegCod")})

public class PatientToSend implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String NQ_GET_PATIENT_STATUS = "GetAllPatToSend";
	public static final String NQ_UPD_PATIENT_STATUS = "UpdateStatus";
	
	private Integer idPatient;
	private Integer idRegCod;
	private String firstname;
	private String lastname;
	private String gender;
	private Date birthDate;
	private String bodyHeight;
	private String bodyWeight;
	private Integer attempts;
	private String error;
	private String status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_patient", unique = true, nullable = false)
	public Integer getIdPatient() {
		return idPatient;
	}
	public void setIdPatient(Integer idPatient) {
		this.idPatient = idPatient;
	}

	@Column(name = "id_reg_cod")
	public Integer getIdRegCod() {
		return idRegCod;
	}
	public void setIdRegCod(Integer idRegCod) {
		this.idRegCod = idRegCod;
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
	@Column(name = "gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name = "birthDate")
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "bodyHeight")
	public String getBodyHeight() {
		return bodyHeight;
	}
	public void setBodyHeight(String bodyHeight) {
		this.bodyHeight = bodyHeight;
	}
	
	@Column(name = "bodyWeight")
	public String getBodyWeight() {
		return bodyWeight;
	}
	public void setBodyWeight(String bodyWeight) {
		this.bodyWeight = bodyWeight;
	}
	
	@Column(name = "attempts")
	public Integer getAttempts() {
		return attempts;
	}
	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}
	
	@Column(name = "error")
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}