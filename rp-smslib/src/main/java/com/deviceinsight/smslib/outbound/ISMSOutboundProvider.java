package com.deviceinsight.smslib.outbound;

import java.util.Properties;

import com.deviceinsight.smslib.SMSMessage;

/**
 * Main interface for sending outbound sms to mobile devices.
 * 
 * @author tst
 *
 */
public interface ISMSOutboundProvider {

	void sendMessage (SMSMessage message) throws SMSOutboundException;
	
	void sendMessage (SMSMessage message, Properties properties) throws SMSOutboundException;
	
}
