package com.deviceinsight.smslib.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

import com.deviceinsight.smslib.SMSMessage;

public class SMSLogger {
	
	final static Logger logger = Logger.getLogger(SMSLogger.class);
	
	final String LOGFILE = "/log/sms.log";
	
	private static SMSLogger smsLogger;
	
	private SMSLogger() {	
	}
	
	public static SMSLogger getLogger() {
		if (smsLogger == null) smsLogger = new SMSLogger();
		return smsLogger;
	}
	
	public void log(SMSMessage message, String provider) {
				
		StringBuffer text = new StringBuffer();
		
		if (message.getCustomer() != null) text.append(message.getCustomer()); 
		text.append(";");
		if (message.getUserLogin () != null) text.append(message.getUserLogin()); 
		text.append(";");
		if (provider != null) text.append(provider); 
		text.append(";");
		if (message.getTo() != null) text.append(message.getTo());
		text.append(";");
		if (message.getFrom() != null) text.append(message.getFrom()); 
		text.append(";");
		if (message.getMessage() != null) {
			String messageText = message.getMessage();
			messageText = messageText.replaceAll("\\\\", "\\\\\\\\");
			messageText = messageText.replaceAll("\n", "\\\\n");
			messageText = messageText.replaceAll(";", "\\\\;");
			text.append(messageText); 
		}

		logger.info(text.toString());
	}
	
	public int getSmsCounter(String customer) {
		
		int counter = 0;

		try {
			String FS = System.getProperty("file.separator");
			String serverDir = System.getProperty("jboss.server.home.dir", ".");
			String filePath = serverDir + FS + "log" + FS + "sms.log";
			InputStream is = new FileInputStream(filePath);
			CSVReader csvReader = new CSVReader(new InputStreamReader(is), ';');
					
			String[] fields = null;
			while ((fields = csvReader.readNext())!=null){
				if (customer.equals(fields[0])) counter++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		return counter;
	}
	
}
