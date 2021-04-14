package com.bvtech.registration.portal.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
@NamedQueries({
	@NamedQuery(name = "UpdateTasksToken", query = "update Tasks SET token = :token  WHERE idTask = :idTask")
})
public class Tasks implements Serializable {
	
	public static String UPDATE_TASK="UpdateTasksToken";

	private static final long serialVersionUID = 1L;

	private String idTask;
	private String token;

	@Id
	@Column(name = "id_task", unique = true, nullable = false)
	public String getIdTask() {
		return idTask;
	}
	public void setIdTask(String idTask) {
		this.idTask = idTask;
	}

	@Column(name = "token")
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}