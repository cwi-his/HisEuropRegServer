package com.bvtech.registration.portal.provider;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class RegistrationCodeAuthenticationObj implements Authentication {

	private static final long serialVersionUID = 1L;
	
	private String registrationCode;
	private Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	private Integer registrationCodeId; // PKey on DB for table registration_codes
	private String password=null; 
	
	public String getName() {
		return registrationCode;
	}

	public RegistrationCodeAuthenticationObj() {
		super();
	}

	public RegistrationCodeAuthenticationObj(String registrationCode,int registrationCodeId, Collection<GrantedAuthority> authorities) {
		super();
		this.registrationCode = registrationCode;
		this.registrationCodeId = registrationCodeId;
		this.authorities = authorities;
		this.password=null;
		this.setAuthenticated(true);
	}

	public RegistrationCodeAuthenticationObj(String registrationCode, Collection<GrantedAuthority> authorities) {
		super();
		this.registrationCode = registrationCode;
		this.authorities = authorities;
		this.password=null;
		this.setAuthenticated(true);
	}
	
	// nel caso di utenti loggati non su secure (e quindi hanno username e pwd)
	public RegistrationCodeAuthenticationObj(String username, String password, Collection<GrantedAuthority> authorities) {
		super();
		this.registrationCode = username;
		this.password = password;
		this.authorities = authorities;
		this.setAuthenticated(true);
	}
	
	public Collection getAuthorities() {
		return authorities;
	}

	public Object getCredentials() {
		if(password!=null) return password;
		return registrationCode;
	}

	public Object getDetails() {
		return registrationCodeId; // In details you can find registrationCodeId field of DB (table registration_codes)
	}

	public Object getPrincipal() {
		return null;
	}

	public boolean isAuthenticated() {
		return false;
	}

	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
	}

}
