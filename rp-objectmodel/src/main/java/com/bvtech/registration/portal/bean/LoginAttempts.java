package com.bvtech.registration.portal.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "login_attempts")
@NamedQueries({
	@NamedQuery(name = "GetAllByIP", query = "SELECT la FROM LoginAttempts la WHERE la.idIp = :idIp"),
	@NamedQuery(name = "UpdateIPAttempts", query = "UPDATE LoginAttempts la SET attempts = :attempts, lastUpdate = :lastUpdate  WHERE la.idIp = :idIp")})

public class LoginAttempts implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String NQ_GET_ALL_INFO_IP_ATTEMPTS = "GetAllByIP";
	public static final String NQ_UPD_IP_ATTEMPTS = "UpdateIPAttempts";
	private String idIp;
	private Integer attempts;
	private Date lastUpdate;

	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ip_address", unique = true, nullable = false)
	public String getIdIp() {
		return idIp;
	}
	public void setIdIp(String idIp) {
		this.idIp = idIp;
	}

	@Column(name = "attempts")
	public Integer getAttempts() {
		return attempts;
	}
	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}
	
	@Column(name = "last_update", insertable=false)
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
}