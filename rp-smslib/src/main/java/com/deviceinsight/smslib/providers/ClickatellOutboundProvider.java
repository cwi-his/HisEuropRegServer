package com.deviceinsight.smslib.providers;

import java.net.URLEncoder;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class ClickatellOutboundProvider extends AbstractOutboundProvider implements ISMSOutboundProvider{

	final static Logger logger = Logger.getLogger(ClickatellOutboundProvider.class);
	final static SMSLogger smsLogger = SMSLogger.getLogger();
	
	public void sendMessage(SMSMessage message, Properties properties) throws SMSOutboundException {
		
		try {
			if (message.getTo() == null) throw new SMSOutboundException("no message target");
			if (message.getMessage() == null) throw new SMSOutboundException("no message text");

			String targetUrl = properties.getProperty(SMSMessageProperties.TARGET_URL, this.targetUrl);
			if (targetUrl == null) throw new SMSOutboundException("no target URL defined");

			String password = properties.getProperty(SMSMessageProperties.PASSWORD, this.password);
			if (password == null) throw new SMSOutboundException("no password defined");

			String user = properties.getProperty(SMSMessageProperties.USER, this.user);
			if (user == null) throw new SMSOutboundException("no user defined");
			
			String id = properties.getProperty(SMSMessageProperties.ID, this.id);
			if (id == null) throw new SMSOutboundException("no api id defined");
						
			StringBuffer urlBuffer = new StringBuffer(targetUrl);
			urlBuffer.append("?").append("to=").append(URLEncoder.encode(formatPhoneNr(message.getTo()),"ISO8859_1"));
			urlBuffer.append("&").append("text=").append(URLEncoder.encode(message.getMessage(),"ISO8859_1"));
			urlBuffer.append("&").append("password=").append(URLEncoder.encode(password,"ISO8859_1"));
			urlBuffer.append("&").append("user=").append(URLEncoder.encode(user,"ISO8859_1"));
			urlBuffer.append("&").append("api_id=").append(URLEncoder.encode(id,"ISO8859_1"));

			HttpClientParams params = new HttpClientParams();
			params.setParameter(HttpClientParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(2, false));
			HttpClient http = new HttpClient(params);
			
			logger.info("method: "+urlBuffer.toString());
			GetMethod method = new GetMethod(urlBuffer.toString());
			
			int responseCode = http.executeMethod(method);
			logger.info("responseCode: "+responseCode);
			
			String response = method.getResponseBodyAsString();
			boolean success = checkStatus(response);
			if (success) smsLogger.log(message, "clickatell");
	
		} catch (Exception exc) {
			logger.error(exc);
			exc.printStackTrace();
			throw new SMSOutboundException(exc.toString());
		}
		
	}

	protected String formatPhoneNr(String phoneNr) {
		
		if (phoneNr == null) return null;
		else if (phoneNr.startsWith("+")) phoneNr = phoneNr.substring(1);
		else if (phoneNr.startsWith("00")) phoneNr = phoneNr.substring(2);
		else if (phoneNr.startsWith("0")) phoneNr = "49" + phoneNr.substring(1);
		phoneNr = phoneNr.replaceAll("[^\\d]", "");
		
		return phoneNr;
	}
	
	boolean checkStatus(String response) {
		try {
			logger.info("stauts: " + response);

			Pattern retPattern = Pattern.compile("\\s*[iI][dD]\\s*:\\s*(\\w+)");
			Matcher m = retPattern.matcher(response);
		    if (m.find()) {
		        //String id = m.group(1);
		        return true;
		    }
					
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return false;
	}
}
