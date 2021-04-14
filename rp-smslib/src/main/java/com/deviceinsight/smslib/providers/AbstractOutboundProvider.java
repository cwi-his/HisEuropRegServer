package com.deviceinsight.smslib.providers;

import java.util.Properties;

import com.deviceinsight.smslib.SMSMessage;
import com.deviceinsight.smslib.SMSMessageProperties;
import com.deviceinsight.smslib.outbound.SMSOutboundException;

public abstract class AbstractOutboundProvider {
		
	protected String id;
	
	protected String user;
	
	protected String password;
	
	protected String targetUrl;
	
	protected String defaultSender;
	
	public void sendMessage(SMSMessage message) throws SMSOutboundException {
				
		Properties props = new Properties();
		if (id != null) props.put(SMSMessageProperties.ID, id);
		if (user != null) props.put(SMSMessageProperties.USER, user);
		if (password != null) props.put(SMSMessageProperties.PASSWORD, password);
		if (targetUrl != null) props.put(SMSMessageProperties.TARGET_URL, targetUrl);
		sendMessage(message, props);
		
	}
	
	public abstract void sendMessage(SMSMessage message, Properties properties) throws SMSOutboundException;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDefaultSender() {
		return defaultSender;
	}

	public void setDefaultSender(String defaultSender) {
		this.defaultSender = defaultSender;
	}

	
	
}
