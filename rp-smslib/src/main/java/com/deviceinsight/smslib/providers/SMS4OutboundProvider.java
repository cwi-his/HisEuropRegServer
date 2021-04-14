package com.deviceinsight.smslib.providers;

import java.io.StringReader;
import java.net.URLEncoder;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.deviceinsight.smslib.SMSMessage;
import com.deviceinsight.smslib.SMSMessageProperties;
import com.deviceinsight.smslib.log.SMSLogger;
import com.deviceinsight.smslib.outbound.ISMSOutboundProvider;
import com.deviceinsight.smslib.outbound.SMSOutboundException;

public class SMS4OutboundProvider extends AbstractOutboundProvider implements ISMSOutboundProvider{

	final static Logger logger = Logger.getLogger(SMS4OutboundProvider.class);
	final static SMSLogger smsLogger = SMSLogger.getLogger();
	
	public void sendMessage(SMSMessage message, Properties properties) throws SMSOutboundException {
		
		try {
			if (message.getTo() == null) throw new SMSOutboundException("no message target");
			if (message.getMessage() == null) throw new SMSOutboundException("no message text");
			
			String id = properties.getProperty(SMSMessageProperties.ID, this.id);
			if (id == null) throw new SMSOutboundException("no customer id defined");
			
			String userId = properties.getProperty(SMSMessageProperties.USER, this.user);
			if (userId == null) throw new SMSOutboundException("no user defined");
			
			String password = properties.getProperty(SMSMessageProperties.PASSWORD, this.password);
			if (password == null) throw new SMSOutboundException("no password defined");

			String targetUrl = properties.getProperty(SMSMessageProperties.TARGET_URL, this.targetUrl);
			if (targetUrl == null) throw new SMSOutboundException("no target URL defined");

			StringBuffer urlBuffer = new StringBuffer(targetUrl);
			urlBuffer.append("?").append("handynr=").append(URLEncoder.encode(formatPhoneNr(message.getTo()),"ISO8859_1"));
			urlBuffer.append("&").append("user=").append(URLEncoder.encode(userId,"ISO8859_1"));
			urlBuffer.append("&").append("pwd=").append(URLEncoder.encode(password,"ISO8859_1"));
			urlBuffer.append("&").append("kdnr=").append(URLEncoder.encode(id,"ISO8859_1"));
			urlBuffer.append("&").append("tarif=").append(URLEncoder.encode(mapSendClass(message),"ISO8859_1"));
			urlBuffer.append("&").append("text=").append(URLEncoder.encode(message.getMessage(),"ISO8859_1"));
			if (StringUtils.hasLength(message.getFrom())) {
				urlBuffer.append("&").append("absender=").append(URLEncoder.encode(message.getFrom()));
			}
			
			HttpClientParams params = new HttpClientParams();
			params.setParameter(HttpClientParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(2, false));
			HttpClient http = new HttpClient(params);
			
			logger.info("method: "+urlBuffer.toString());
			GetMethod method = new GetMethod(urlBuffer.toString());
			
			int responseCode = http.executeMethod(method);
			logger.info("responseCode: "+responseCode);
			
			String response = method.getResponseBodyAsString();
			boolean success = checkStatus(response);
			if (success) smsLogger.log(message, "sms4");
			
		} catch (Exception exc) {
			logger.error(exc);
			exc.printStackTrace();
			throw new SMSOutboundException(exc.toString());
		}
		
	}
	
	/**
	 * Map the class specified by message to a SMS4 tarif.
	 * @param message
	 * @return
	 */
	protected String mapSendClass (SMSMessage message) {
		if (StringUtils.hasLength(message.getFrom())) {
			return "1";
		} else {
			return "3";
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
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(response)));
			NodeList statusList = document.getDocumentElement().getChildNodes();
			String status = statusList.item(0).getChildNodes().item(0).getNodeValue();
			String statusText = statusList.item(1).getChildNodes().item(0).getNodeValue();			
			logger.info("status: " + status);
			logger.info("stautsText: " + statusText);
			if (status != null && status.equals("0")) return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return false;
	}
}
