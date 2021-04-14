package com.bvtech.registration.portal.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "types_authentications_processes")

public class TypeAuthenticationProcess implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idTypAutPro;
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_typ_aut_pro", unique = true, nullable = false)
	public int getIdTypAutPro() {
		return idTypAutPro;
	}

	public void setIdTypAutPro(int idTypAutPro) {
		this.idTypAutPro = idTypAutPro;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}