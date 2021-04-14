package com.bvtech.registration.portal.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "states_processes")
public class StatusProcess implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idStaPro;
	private String name;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_sta_pro", unique = true, nullable = false)
	public Integer getIdStaPro() {
		return idStaPro;
	}
	public void setIdStaPro(Integer idStaPro) {
		this.idStaPro = idStaPro;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	



	
}