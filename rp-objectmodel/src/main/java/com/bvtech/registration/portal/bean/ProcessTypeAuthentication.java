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
@Table(name = "processes_types_authentications")
@NamedQueries({
	@NamedQuery(name = "GetAllProTypAutByCode", query = "SELECT pta FROM ProcessTypeAuthentication pta WHERE pta.idProInf = :idProInf"),
	@NamedQuery(name = "UpdateProTypAutAttempts", query = "UPDATE ProcessTypeAuthentication pta SET attempts = :attempts WHERE pta.idProInf = :idProInf"),
	@NamedQuery(name = "UpdateProTypAutAuthenticate", query = "UPDATE ProcessTypeAuthentication pta SET authenticationDate =:authenticationDate WHERE pta.idProInf = :idProInf")})

public class ProcessTypeAuthentication implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String NQ_GET_ALL_PRO_TYP_AUT_BY_CODE = "GetAllProTypAutByCode";
	public static final String NQ_UPD_PRO_TYP_AUT_ATTEMPTS = "UpdateProTypAutAttempts";
	public static final String NQ_UPD_PRO_TYP_AUT_AUTHENT = "UpdateProTypAutAuthenticate";
	

	private Integer idProTypAut;
	private Integer idProInf;
	private Integer idTypAutPro;
	private String phoneNumber;
	private Integer attempts;
	private Date authenticationDate;
	private Integer codeSmsGen;
	// R.L. - 25.01.2017 - used to validate mail in 10 minute
	private Date insertDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_pro_typ_aut", unique = true, nullable = false)
	
	public Integer getIdProTypAut() {
		return idProTypAut;
	}
	public void setIdProTypAut(Integer idProTypAut) {
		this.idProTypAut = idProTypAut;
	}
	
	@Column(name = "id_pro_inf")
	public Integer getIdProInf() {
		return idProInf;
	}
	public void setIdProInf(Integer idProInf) {
		this.idProInf = idProInf;
	}
	
	@Column(name = "id_typ_aut_pro")
	public Integer getIdTypAutPro() {
		return idTypAutPro;
	}
	public void setIdTypAutPro(Integer idTypAutPro) {
		this.idTypAutPro = idTypAutPro;
	}
	
	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Column(name = "attempts")
	public Integer getAttempts() {
		return attempts;
	}
	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}
	
	@Column(name = "authentication_date")
	public Date getAuthenticationDate() {
		return authenticationDate;
	}
	public void setAuthenticationDate(Date authenticationDate) {
		this.authenticationDate = authenticationDate;
	}
	
	@Column(name = "insert_time", insertable=false)
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
	@Column(name = "code_sms_gen")
	public Integer getCodeSmsGen() {
		return codeSmsGen;
	}
	public void setCodeSmsGen(Integer codeSmsGen) {
		this.codeSmsGen = codeSmsGen;
	}
 
}