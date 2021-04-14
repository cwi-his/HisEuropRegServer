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
@Table(name = "csv_to_send")
public class CsvToSend implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private Integer idCsvToSend;
	private String fileName;
	private String status;
	private String sendError;
	private Integer sendAttempt;
	private Date insertDate;
	private Date sendDate;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_csv_to_send", unique = true, nullable = false)
	public Integer getIdCsvToSend() {
		return idCsvToSend;
	}

	public void setIdCsvToSend(Integer idCsvToSend) {
		this.idCsvToSend = idCsvToSend;
	}

	@Column(name = "file_name")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "send_error")
	public String getSendError() {
		return sendError;
	}

	public void setSendError(String sendError) {
		this.sendError = sendError;
	}
	@Column(name = "send_attempt")
	public Integer getSendAttempt() {
		return sendAttempt;
	}

	public void setSendAttempt(Integer sendAttempt) {
		this.sendAttempt = sendAttempt;
	}
	@Column(name = "insert_date")
	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	@Column(name = "send_date")
	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
}