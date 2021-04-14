package com.bvtech.sender.mail.log;

import org.apache.log4j.Logger;

import com.bvtech.sender.mail.MAILMessage;

public class MAILLogger {
	
	final static Logger logger = Logger.getLogger(MAILLogger.class);
	
	final String LOGFILE = "/log/mail.log";
	
	private static MAILLogger mailLogger;
	
	private MAILLogger() {	
	}
	
	public static MAILLogger getLogger() {
		if (mailLogger == null) mailLogger = new MAILLogger();
		return mailLogger;
	}
	
	public void log(MAILMessage message, String provider) {
				
		StringBuffer text = new StringBuffer();
		
		
		if (provider != null) text.append(provider); 
		text.append(";");
		if (message.getTo() != null) text.append(message.getTo());
		text.append(";");
		if (message.getSubject() != null) text.append(message.getSubject()); 
		text.append(";");
		if (message.getMessage() != null) {
			String messageText = message.getMessage();
			messageText = messageText.replaceAll("\n", " ");
			text.append(messageText); 
		}

		logger.info(text.toString());
	}
	
}
