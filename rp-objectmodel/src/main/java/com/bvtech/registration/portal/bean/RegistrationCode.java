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
@Table(name = "registrations_codes")	
@NamedQueries({
		@NamedQuery(name = "GetAllInfoByCode.userCode", query = "SELECT rc FROM RegistrationCode rc WHERE rc.code = :registrationCode"),
		@NamedQuery(name = "GetAllInfoByReRegistrationCode.coachCode", query = "SELECT rc FROM RegistrationCode rc WHERE rc.reregisterCode = :reregisterCode and rc.idStaPro<>5"),
		@NamedQuery(name = "GetAllInfoByRejectCode.coachCode", query = "SELECT rc FROM RegistrationCode rc WHERE rc.rejectCode = :rejectCode and rc.idStaPro<>5"),
		@NamedQuery(name = "UpdateState", query = "UPDATE RegistrationCode rc SET idStaPro = :idStaPro, lastUpdate = :lastUpdate WHERE rc.idRegCod = :idRegCod"),
		@NamedQuery(name = "UpdateSubmissionTerms", query = "UPDATE RegistrationCode rc SET submissionTerms = :submissionTerms WHERE rc.idRegCod = :idRegCod"),
		@NamedQuery(name = "UpdateSendEmail", query = "UPDATE RegistrationCode rc SET sendEmail = :sendEmail WHERE rc.idRegCod = :idRegCod"),
		@NamedQuery(name = "UpdateRegCompleted", query = "UPDATE RegistrationCode rc SET registrationCompleted = :registrationCompleted WHERE rc.idRegCod = :idRegCod"),
		@NamedQuery(name = "UpdateGenCode", query = "UPDATE RegistrationCode rc SET rc.reregisterCode = concat(rc.code, :reregisterCode), rc.rejectCode = concat(rc.code, :rejectCode)  WHERE rc.idRegCod = :idRegCod"),
		@NamedQuery(name = "deleteByCode", query = "DELETE RegistrationCode rc WHERE rc.code = :code")
//		@NamedQuery(name = "GetAllUserDataForCSV", query = "select new com.bvtech.registration.portal.tasks.bean.csv.SuitableUser(rc.lastUpdate, pri.externalUuid, pri.externalNodeid, pri.idStaUse, '',  rc.code, rc.reregisterCode,rc.rejectCode, '', '','', '', '', rc.submissionTerms, '', "+ 
//			"pei.sex, pei.surname, pei.name, pei.address, '', pei.addressAdditional, pei.postalCode, pei.city, pei.note, pei.email, pei.phoneLandline, pei.telephoneNumber, '', "+
//			"pei.availability, pei.contactNow, pei.fromDate, pei.insertDate,	pei.optSurname, pei.optName, pei.optAddress, '', pei.optAddressAdditional, pei.optPostalCode, "+
//			"pei.optCity, pei.optNote, pei.optAddressAdditional, pta.idProTypAut, '', pta.phoneNumber, pta.authenticationDate, pta.authenticationDate, pri.questionSection1A, "+
//			"pri.questionSection2A, pri.questionSection2A, pri.questionWeight, pri.questionHeight, pri.bmi, '', pri.questionInsulineA, pri.questionInsulineB, '','', rc.registrationCompleted) "+ 
//			"from RegistrationCode rc, ProcessInformation pri, PersonalInformation pei, ProcessTypeAuthentication pta, Tasks ts "+ 
//			"where rc.idRegCod = pri.idRegCod and pri.idPerInf = pei.idPerInf and pri.idProInf = pta.idProInf "+
//			"and pri.idStaUse in ('1','2','3','4','5','6') " +
//			"and rc.lastUpdate >= STR_TO_DATE(ts.token,'%Y-%m-%d %H:%i:%s') and ts.idTask = 'CREATE_CSV_JOB' ")
		})

public class RegistrationCode implements Serializable {

	private static final long serialVersionUID = 1L;

	// STATIC VARIABLES
	public static final String NQ_GET_ALL_INFO_BY_CODE = "GetAllInfoByCode.userCode";
	public static final String NQ_GET_ALL_INFO_BY_REREGISTRATION_CODE = "GetAllInfoByReRegistrationCode.coachCode";
	public static final String NQ_GET_ALL_INFO_BY_REJECT_CODE = "GetAllInfoByRejectCode.coachCode";
	public static final String NQ_UPD_STA = "UpdateState";
	public static final String NQ_UPD_SEND_EMAIL = "UpdateSendEmail";
	public static final String NQ_UPD_SUBM_TERMS = "UpdateSubmissionTerms";
	public static final String NQ_UPD_REG_COMPLETED = "UpdateRegCompleted";
	public static final String NQ_UPD_GEN_CODE = "UpdateGenCode";
//	public static final String NQ_GET_ALL_USERDATA_FOR_CSV = "GetAllUserDataForCSV"; 
	public static final String DELETE_BY_CODE="deleteByCode";

	private Integer idRegCod;
	private String code;
	private String typeCode;
	private Date insertDate;
	private Date lastUpdate;
	private int idStaPro;
	private Date submissionTerms;
	private Date registrationCompleted;
	private String reregisterCode;
	private String rejectCode;
	private Integer sendEmail;
	private String vnr;


	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_reg_cod", unique = true, nullable = false)
	public Integer getIdRegCod() {
		return idRegCod;
	}

	public void setIdRegCod(Integer idRegCod) {
		this.idRegCod = idRegCod;
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
	
	@Column(name = "insert_date")
	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	@Column(name = "last_update")
	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	
	@Column(name = "id_sta_pro")
	public int getIdStaPro() {
		return idStaPro;
	}

	public void setIdStaPro(int idStaPro) {
		this.idStaPro = idStaPro;
	}
	
	@Column(name = "submission_terms")
	public Date getSubmissionTerms() {
		return submissionTerms;
	}

	public void setSubmissionTerms(Date submissionTerms) {
		this.submissionTerms = submissionTerms;
	}

	
	@Column(name = "registration_completed")
	public Date getRegistrationCompleted() {
		return registrationCompleted;
	}

	public void setRegistrationCompleted(Date registrationCompleted) {
		this.registrationCompleted = registrationCompleted;
	}

	@Column(name = "reregister_code")
	public String getReregisterCode() {
		return reregisterCode;
	}

	public void setReregisterCode(String reregisterCode) {
		this.reregisterCode = reregisterCode;
	}

	@Column(name = "reject_code")
	public String getRejectCode() {
		return rejectCode;
	}

	public void setRejectCode(String rejectCode) {
		this.rejectCode = rejectCode;
	}
	
	@Column(name = "send_email")
	public Integer getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(Integer sendEmail) {
		this.sendEmail = sendEmail;
	}
	
	@Column(name = "vnr")
	public String getVnr() {
		return this.vnr;
	}
	
	public void setVnr(String vnr) {
		this.vnr = vnr;
	}
}