package com.bvtech.registration.portal.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "types_diabetes")
public class TypeDiabetes implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idTypDia;
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_typ_dia", unique = true, nullable = false)
	public int getIdTypDia() {
		return idTypDia;
	}

	public void setIdTypDia(int idTypDia) {
		this.idTypDia = idTypDia;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}