package com.deviceinsight.smslib.test;

import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

import com.deviceinsight.smslib.SMSMessage;
import com.deviceinsight.smslib.SMSMessageProperties;
import com.deviceinsight.smslib.outbound.ISMSOutboundProvider;
import com.deviceinsight.smslib.outbound.SMSOutboundProviderFactory;
import com.deviceinsight.smslib.providers.SMS4OutboundProvider;

import junit.framework.TestCase;

public class MobilantTest extends TestCase {

	public void testMobilantSimple () throws Exception {
		
		ISMSOutboundProvider provider = new SMSOutboundProviderFactory().getProviderByClasspath("/com/deviceinsight/smslib/sms-providers.xml", "mobilant");
		
		SMSMessage msg = new SMSMessage();
		msg.setFrom("DITEST");
		//msg.setTo("+491735686914");
		msg.setMessage("A Test");
		
		Properties props = new Properties();
		
		provider.sendMessage(msg, props);
				
	}

	protected void setUp() throws Exception {
		PropertyConfigurator.configure(this.getClass().getResource("/com/deviceinsight/smslib/test/log4j.properties"));
		
	}
	
}
