package com.deviceinsight.smslib.test;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import com.deviceinsight.smslib.SMSMessage;
import com.deviceinsight.smslib.SMSMessageProperties;
import com.deviceinsight.smslib.outbound.ISMSOutboundProvider;
import com.deviceinsight.smslib.outbound.SMSOutboundProviderFactory;

import junit.framework.TestCase;

public class SMS4Test extends TestCase {

	public void testSMS4Simple () throws Exception {
		
		ISMSOutboundProvider provider = new SMSOutboundProviderFactory().getProviderByClasspath("/com/deviceinsight/smslib/sms-providers.xml", "sms4");
		
		SMSMessage msg = new SMSMessage();
		//msg.setFrom("012345");
		msg.setTo("01735686914");
		msg.setMessage("blablabalbla");
		
		Properties props = new Properties();
		/*
		props.setProperty(SMSMessageProperties.ID,"DE3565");
		props.setProperty(SMSMessageProperties.USER,"Stammeier");
		props.setProperty(SMSMessageProperties.PASSWORD,"9742");
		*/
		
		provider.sendMessage(msg, props);
		
	}

	protected void setUp() throws Exception {
		PropertyConfigurator.configure(this.getClass().getResource("/com/deviceinsight/smslib/test/log4j.properties"));
		
	}
	
	
	
}
