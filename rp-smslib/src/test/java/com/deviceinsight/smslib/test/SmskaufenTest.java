package com.deviceinsight.smslib.test;

import java.util.Properties;

import junit.framework.TestCase;

import org.apache.log4j.PropertyConfigurator;

import com.deviceinsight.smslib.SMSMessage;
import com.deviceinsight.smslib.outbound.ISMSOutboundProvider;
import com.deviceinsight.smslib.outbound.SMSOutboundProviderFactory;

public class SmskaufenTest extends TestCase {

	public void testMobilantSimple () throws Exception {
		
		ISMSOutboundProvider provider = new SMSOutboundProviderFactory().
			getProviderByClasspath("/com/deviceinsight/smslib/sms-providers.xml", "smskaufen");
		
		SMSMessage msg = new SMSMessage();
		msg.setFrom("01733619141");
		msg.setTo("017336191412");
		msg.setMessage("EuropAssistance Compile Test CWi)");
		
		Properties props = new Properties();
		
		provider.sendMessage(msg, props);
				
	}

	protected void setUp() throws Exception {
		PropertyConfigurator.configure(this.getClass().getResource("/com/deviceinsight/smslib/test/log4j.properties"));
		
	}
	
}
