package com.deviceinsight.smslib.test;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import com.deviceinsight.smslib.SMSMessage;
import com.deviceinsight.smslib.outbound.ISMSOutboundProvider;
import com.deviceinsight.smslib.outbound.SMSOutboundProviderFactory;

import junit.framework.TestCase;

public class ClickatellTest extends TestCase {

	public void testSMS4Simple () throws Exception {
		
		ISMSOutboundProvider provider = new SMSOutboundProviderFactory().getProviderByClasspath("/com/deviceinsight/smslib/sms-providers.xml", "clickatell");
		
		SMSMessage msg = new SMSMessage();
		msg.setFrom("+3912345");
		msg.setTo("+393933391692");
		msg.setMessage("blablabalbla");
		
		Properties props = new Properties();	
		
		provider.sendMessage(msg, props);
		
	}

	protected void setUp() throws Exception {
		PropertyConfigurator.configure(this.getClass().getResource("log4j.properties"));
		
	}
	
	
	
}
