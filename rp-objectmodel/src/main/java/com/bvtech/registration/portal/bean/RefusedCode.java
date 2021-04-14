package com.bvtech.registration.portal.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "refused_codes")
public class RefusedCode implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idRefCod;
	private String code;
	private String typeCode;
	private Date insertDate;
	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_ref_cod", unique = true, nullable = false)
	public Integer getIdRefCod() {
		return idRefCod;
	}

	public void setIdRefCod(Integer idRefCod) {
		this.idRefCod = idRefCod;
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

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}




	
}