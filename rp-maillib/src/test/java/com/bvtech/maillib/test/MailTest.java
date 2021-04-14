package com.bvtech.maillib.test;

import com.bvtech.sender.mail.MAILMessage;
import com.bvtech.sender.mail.SendMail;

import junit.framework.TestCase;

public class MailTest extends TestCase {

	public void testMail() throws Exception {
		
		SendMail provider = new SendMail();
		
		MAILMessage msg = new MAILMessage();
		msg.setTo("r.landi@bv-tech.it");
		msg.setMessage("test mail message");
				
		provider.send(msg);
		
	}	
	
}
