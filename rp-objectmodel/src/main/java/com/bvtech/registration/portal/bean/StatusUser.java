package com.bvtech.registration.portal.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "states_users")
public class StatusUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idStaUse;
	private String name;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_sta_use", unique = true, nullable = false)
	public Integer getIdStaUse() {
		return idStaUse;
	}
	public void setIdStaUse(Integer idStaUse) {
		this.idStaUse = idStaUse;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}