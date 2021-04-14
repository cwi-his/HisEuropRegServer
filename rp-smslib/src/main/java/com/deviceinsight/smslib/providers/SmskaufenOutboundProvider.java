package com.deviceinsight.smslib.providers;

import java.net.URLEncoder;
import java.util.Properties;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.log4j.Logger;

import com.deviceinsight.smslib.SMSMessage;
import com.deviceinsight.smslib.SMSMessageProperties;
import com.deviceinsight.smslib.log.SMSLogger;
import com.deviceinsight.smslib.outbound.ISMSOutboundProvider;
import com.deviceinsight.smslib.outbound.SMSOutboundException;

public class SmskaufenOutboundProvider extends AbstractOutboundProvider implements ISMSOutboundProvider{

	final static Logger logger = Logger.getLogger(SmskaufenOutboundProvider.class);
	final static SMSLogger smsLogger = SMSLogger.getLogger();
	
	public void sendMessage(SMSMessage message, Properties properties) throws SMSOutboundException {
		
		try {
			if (message.getTo() == null) throw new SMSOutboundException("no message target");
			if (message.getMessage() == null) throw new SMSOutboundException("no message text");
						
			String id = properties.getProperty(SMSMessageProperties.ID, this.id);
			if (id == null) throw new SMSOutboundException("no id defined");

			String password = properties.getProperty(SMSMessageProperties.PASSWORD, this.password);
			if (password == null) throw new SMSOutboundException("no password defined");
			
			String targetUrl = properties.getProperty(SMSMessageProperties.TARGET_URL, this.targetUrl);
			if (targetUrl == null) throw new SMSOutboundException("no target URL defined");

			StringBuffer urlBuffer = new StringBuffer(targetUrl);
			urlBuffer.append("?").append("empfaenger=").append(URLEncoder.encode(formatPhoneNr(message.getTo()),"ISO8859_1"));
			urlBuffer.append("&").append("id=").append(URLEncoder.encode(id,"ISO8859_1"));
			urlBuffer.append("&").append("pw=").append(URLEncoder.encode(password,"ISO8859_1"));
			urlBuffer.append("&").append("type=").append(getType(message.getTo()));
			
			String from = message.getFrom();
			if (from==null) {
				from = getDefaultSender();
			}
			if (from != null) urlBuffer.append("&").append("absender=").append(URLEncoder.encode(from,"ISO8859_1"));
			urlBuffer.append("&").append("text=").append(URLEncoder.encode(message.getMessage(),"ISO8859_1"));
			
			HttpClientParams params = new HttpClientParams();
			params.setParameter(HttpClientParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(2, false));
			HttpClient http = new HttpClient(params);
			
			logger.info("method: "+urlBuffer.toString());
			GetMethod method = new GetMethod(urlBuffer.toString());
			
			int responseCode = http.executeMethod(method);
			logger.info("responseCode: "+responseCode);
			
			String response = method.getResponseBodyAsString();
			boolean success = checkStatus(response);
			if (success) smsLogger.log(message, "smskaufen");
			
		} catch (Exception exc) {
			logger.error(exc);
			exc.printStackTrace();
			throw new SMSOutboundException(exc.toString());
		}
		
	}

	protected String formatPhoneNr(String phoneNr) {
		
		if (phoneNr == null) return null;
		phoneNr = phoneNr.replaceAll("[^\\d^+]", "");
		
		if (phoneNr.startsWith("+")) phoneNr = "00" + phoneNr.substring(1);
		else if (phoneNr.startsWith("00")) ;
		else if (phoneNr.startsWith("0")) phoneNr = "0049" + phoneNr.substring(1);
				
		return phoneNr;
	}

	boolean checkStatus(String response) {
		try {
			String statusText;
			switch (Integer.parseInt(response)) {
				case 100: statusText = "SMS verschickt"; break;
				case 101: statusText = "MMS verschickt"; break;
				case 111: statusText = "IP-Sperre aktiv"; break;
				case 112: statusText = "Falsche Userdaten"; break;
				case 120: statusText = "Absender fehlt"; break;
				case 121: statusText = "Parameter Typ fehlt"; break;
				case 122: statusText = "Parameter Text fehlt"; break;
				case 123: statusText = "Parameter Empfänger fehlt"; break;
				case 129: statusText = "Falscher Absender"; break;
				case 130: statusText = "Gateway-Problem"; break;
				default: statusText = "";
			}				

			logger.info("stauts: " + response);
			logger.info("stautsText: " + statusText);
			if (response != null && response.equals("100")) return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return false;
	}
	
	// 4 = high-quality directroute de, 8 = high quality directroute int
	int getType(String phoneNr) {
		
		int type = 4;
		
		if (phoneNr.startsWith("+")) {
			if (phoneNr.startsWith("+49")) type = 4;
			else type = 8;
		} else if (phoneNr.startsWith("00")) {
			if (phoneNr.startsWith("0049")) type = 4;
			else type = 8;			
		} else {
			type = 4;
		}
		
		return type;
	}
}
