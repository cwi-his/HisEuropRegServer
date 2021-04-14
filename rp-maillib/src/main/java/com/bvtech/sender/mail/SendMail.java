package com.bvtech.sender.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;

import com.bvtech.sender.mail.log.MAILLogger;

public class SendMail {

	final static Log log = LogFactory.getLog(SendMail.class);
	final static MAILLogger mailLogger = MAILLogger.getLogger();

	@Value("${mail.smtp.username}")
	String USERNAME = "username@gmail.com";
	
	@Value("${mail.smtp.password}")
	String PASSWORD = "password";
	
	@Value("${mail.smtp.host}")
	String HOST;
	
	@Value("${mail.smtp.port}")
	String PORT;
	
	@Value("${mail.smtp.starttls}")
	String START_TLS; // true/false
	
	@Value("${mail.sender.name}")
	String fromMail; // true/false
	
	
	public boolean send(MAILMessage mailMessage) {
		log.info("Send mail to ["+mailMessage.to+"] - subject ["+mailMessage.subject+"] - code ["+mailMessage.to.hashCode()+"]");
	
		log.debug("Configuration - USERNAME ["+USERNAME+"]- HOST ["+HOST+"] - PORT ["+PORT+"]- START_TLS ["+START_TLS+"]");
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true"); //false
		props.put("mail.smtp.starttls.enable", START_TLS);
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.port", PORT);

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromMail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailMessage.getTo()));
			message.setSubject(mailMessage.getSubject());
			message.setText(mailMessage.getMessage());

			Transport.send(message);

			log.info("mail sent - code ["+mailMessage.to.hashCode()+"]");

		} catch (MessagingException e) {
			log.error(e);
			return false;
		}
		return true;
	}

}
