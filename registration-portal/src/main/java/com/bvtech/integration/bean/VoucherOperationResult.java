package com.bvtech.integration.bean;

import java.io.Serializable;
import java.util.List;


public class VoucherOperationResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String code;
	public String message;
	public List<String> duplicatedCodes;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public VoucherOperationResult(String code, String message, List<String> duplicatedCodes) {
		super();
		this.code = code;
		this.message = message;
		this.duplicatedCodes= duplicatedCodes;
	}
	public List<String> getDuplicatedCodes() {
		return duplicatedCodes;
	}
	public void setDuplicatedCodes(List<String> duplicatedCodes) {
		this.duplicatedCodes = duplicatedCodes;
	}
	
	
}
