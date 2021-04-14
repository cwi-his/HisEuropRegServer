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
@Table(name = "configs")
@NamedQueries({
	@NamedQuery(name = "GetConfig", query = "SELECT cn FROM Config cn WHERE cn.idCon = :idCon")})
public class Config implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String NQ_GET_ALL_CONFIG = "GetConfig";

	private String idCon;
	private String value;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_con", unique = true, nullable = false)
	public String getIdCon() {
		return idCon;
	}

	public void setIdCon(String idCon) {
		this.idCon = idCon;
	}

	@Column(name = "value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}