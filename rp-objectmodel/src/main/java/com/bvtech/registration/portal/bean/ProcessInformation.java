package com.bvtech.registration.portal.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "processes_informations")
@NamedQueries({
	@NamedQuery(name = "GetProInfByCode", query = "SELECT pi FROM ProcessInformation pi WHERE pi.idRegCod = :registrationCodeId"), 
	@NamedQuery(name = "GetIdRegCodfByIdProInf", query = "SELECT pi FROM ProcessInformation pi WHERE pi.idProInf = :idProInf"), 
	@NamedQuery(name = "UpdateProcess", query = "UPDATE ProcessInformation pi SET targetUrl = :targetUrl  WHERE pi.idRegCod = :idRegCod"),
	@NamedQuery(name = "UpdateExternalCode", query = "UPDATE ProcessInformation pi SET externalUuid = :externalUuid, externalNodeid = :externalNodeid WHERE pi.idRegCod = :idRegCod")})
public class ProcessInformation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// STATIC VARIABLES
	public static final String NQ_GET_CODE_BY_PRO_INF = "GetIdRegCodfByIdProInf";
	public static final String NQ_GET_PRO_INF_BY_CODE = "GetProInfByCode";
	public static final String NQ_UPD_EXTERNAL_CODE = "UpdateExternalCode";
		
	private Integer idProInf;
	private Integer idRegCod;
	private Integer idPerInf;
	private Integer idTypDia;
	private Integer idStaUse;
	private Integer questionSection1A;
	private Integer questionSection1B;
	private Integer questionSection2A;
	private Integer questionSection2B;
	private Integer questionHeight;
	private Integer questionWeight;
	private Integer questionInsuline;
	private Integer questionInsulineB;
	private String targetUrl;
    private Integer bmi;
    private Integer acceptanceA;
    private Integer acceptanceB;
    private Integer acceptanceC;
	private String externalUuid;
    private String externalNodeid;
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_pro_inf", unique = true, nullable = false)
	public Integer getIdProInf() {
		return idProInf;
	}

	public void setIdProInf(Integer idProInf) {
		this.idProInf = idProInf;
	}

	@Column(name = "id_reg_cod")
	public Integer getIdRegCod() {
		return idRegCod;
	}

	public void setIdRegCod(Integer idRegCod) {
		this.idRegCod = idRegCod;
	}

	@Column(name = "id_per_inf")
	public Integer getIdPerInf() {
		return idPerInf;
	}

	public void setIdPerInf(Integer idPerInf) {
		this.idPerInf = idPerInf;
	}

	@Column(name = "id_typ_dia")
	public Integer getIdTypDia() {
		return idTypDia;
	}

	public void setIdTypDia(Integer idTypDia) {
		this.idTypDia = idTypDia;
	}

	@Column(name = "id_sta_use")
	public Integer getIdStaUse() {
		return idStaUse;
	}

	public void setIdStaUse(Integer idStaUse) {
		this.idStaUse = idStaUse;
	}

	@Column(name = "question_section1_a")
	public Integer getQuestionSection1A() {
		return questionSection1A;
	}

	public void setQuestionSection1A(Integer questionSection1A) {
		this.questionSection1A = questionSection1A;
	}

	@Column(name = "question_section1_b")
	public Integer getQuestionSection1B() {
		return questionSection1B;
	}

	public void setQuestionSection1B(Integer questionSection1B) {
		this.questionSection1B = questionSection1B;
	}

	@Column(name = "question_section2_a")
	public Integer getQuestionSection2A() {
		return questionSection2A;
	}

	public void setQuestionSection2A(Integer questionSection2A) {
		this.questionSection2A = questionSection2A;
	}

	@Column(name = "question_section2_b")
	public Integer getQuestionSection2B() {
		return questionSection2B;
	}

	public void setQuestionSection2B(Integer questionSection2B) {
		this.questionSection2B = questionSection2B;
	}

	@Column(name = "question_height")
	public Integer getQuestionHeight() {
		return questionHeight;
	}

	public void setQuestionHeight(Integer questionHeight) {
		this.questionHeight = questionHeight;
	}

	@Column(name = "question_weight")
	public Integer getQuestionWeight() {
		return questionWeight;
	}

	public void setQuestionWeight(Integer questionWeight) {
		this.questionWeight = questionWeight;
	}

	@Column(name = "question_insuline")
	public Integer getQuestionInsuline() {
		return questionInsuline;
	}

	public void setQuestionInsuline(Integer questionInsuline) {
		this.questionInsuline = questionInsuline;
	}

	@Column(name = "question_insuline_b")
	public Integer getQuestionInsulineB() {
		return questionInsulineB;
	}

	public void setQuestionInsulineB(Integer questionInsulineB) {
		this.questionInsulineB = questionInsulineB;
	}

	@Column(name = "target_url")
	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	@Column(name = "BMI")
	public Integer getBmi() {
		return bmi;
	}

	public void setBmi(Integer bmi) {
		this.bmi = bmi;
	}
	
	@Column(name = "acceptance_a")
	public Integer getAcceptanceA() {
		return acceptanceA;
	}

	public void setAcceptanceA(Integer acceptanceA) {
		this.acceptanceA = acceptanceA;
	}

	@Column(name = "acceptance_b")
	public Integer getAcceptanceB() {
		return acceptanceB;
	}

	public void setAcceptanceB(Integer acceptanceB) {
		this.acceptanceB = acceptanceB;
	}

	@Column(name = "acceptance_c")
	public Integer getAcceptanceC() {
		return acceptanceC;
	}

	public void setAcceptanceC(Integer acceptanceC) {
		this.acceptanceC = acceptanceC;
	}
    
	@Column(name = "external_uuid")
    public String getExternalUuid() {
		return externalUuid;
	}

	public void setExternalUuid(String externalUuid) {
		this.externalUuid = externalUuid;
	}

	@Column(name = "external_nodeid")
	public String getExternalNodeid() {
		return externalNodeid;
	}

	public void setExternalNodeid(String externalNodeid) {
		this.externalNodeid = externalNodeid;
	}

}