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

public class MobilantOutboundProvider extends AbstractOutboundProvider implements ISMSOutboundProvider{

	final static Logger logger = Logger.getLogger(MobilantOutboundProvider.class);
	final static SMSLogger smsLogger = SMSLogger.getLogger();
	
	public void sendMessage(SMSMessage message, Properties properties) throws SMSOutboundException {
		
		try {
			if (message.getTo() == null) throw new SMSOutboundException("no message target");
			if (message.getMessage() == null) throw new SMSOutboundException("no message text");
						
			String id = properties.getProperty(SMSMessageProperties.ID, this.id);
			if (id == null) throw new SMSOutboundException("no key defined");

			String targetUrl = properties.getProperty(SMSMessageProperties.TARGET_URL, this.targetUrl);
			if (targetUrl == null) throw new SMSOutboundException("no target URL defined");

			StringBuffer urlBuffer = new StringBuffer(targetUrl);
			urlBuffer.append("?").append("handynr=").append(URLEncoder.encode(formatPhoneNr(message.getTo()),"ISO8859_1"));
			urlBuffer.append("&").append("key=").append(URLEncoder.encode(id,"ISO8859_1"));
			
			String from = message.getFrom();
			if (from==null) {
				from = getDefaultSender();
			}
			if (from != null) urlBuffer.append("&").append("kennung=").append(URLEncoder.encode(from,"ISO8859_1"));
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
			if (success) {
				smsLogger.log(message, "mobilant");
			} else {
				throw new SMSOutboundException("cannot send message, response="+response);
			}
			
		} catch (Exception exc) {
			logger.error(exc);
			exc.printStackTrace();
			throw new SMSOutboundException(exc.toString());
		}
		
	}

	protected String formatPhoneNr(String phoneNr) {
		
		if (phoneNr == null) return null;
		else if (phoneNr.startsWith("+")) phoneNr = "00" + phoneNr.substring(1);
		else if (phoneNr.startsWith("00")) ;
		else if (phoneNr.startsWith("0")) phoneNr = "0049" + phoneNr.substring(1);
		phoneNr = phoneNr.replaceAll("[^\\d^+]", "");
		
		return phoneNr;
	}

	boolean checkStatus(String response) {
		try {
			String statusText;
			switch (Integer.parseInt(response)) {
				case 100: statusText = "Die Nachricht wurde erfolgreich an den Carrier übergeben"; break;
				case 200: statusText = "Die Zielrufnummer hat nicht das korrkete Format"; break;
				case 201: statusText = "Die Absenderkennung hat nicht das korrekte Format"; break;
				case 300: statusText = "Fehler bei Autorisierung (kein Guthaben, Key falsch, Account falsch, etc.)"; break;
				case 400: statusText = "Fehler bei der Übertragung an Carrier, bitte später erneut versuchen"; break;
				case 600: statusText = "Tageslimit erreicht"; break;
				case 700: statusText = "Monatslimit erreicht"; break;
				case 800: statusText = "IP-Adresse gesperrt"; break;
				case 900: statusText = "Zielrufnummer im Kunden-Center gesperrt"; break;
				case 1000: statusText = "Nachricht zu lang, maximal 1600 Zeichen"; break;
				case 1100: statusText = "Es wird eine 0190 oder 0137 Rufnummer in der SMS beworben, Zustellung verweigert"; break;
				case 1200: statusText = "Verdacht auf Spam, Zustellung verweigert"; break;
				case 1300: statusText = "MMS größer als 300kb"; break;
				case 1401: statusText = "MMS: pic01 nicht im richtigen Format / konnte nicht gefunden werden"; break;
				case 1402: statusText = "MMS: pic02 nicht im richtigen Format / konnte nicht gefunden werden"; break;
				case 1403: statusText = "MMS: pic03 nicht im richtigen Format / konnte nicht gefunden werden"; break;
				case 1500: statusText = "MMS-Text zu lang, maximal 500 Zeichen"; break;
				case 1600: statusText = "Massenversand: keine Zielrufnummer angegeben"; break;
				case 1700: statusText = "Internen Massenversandfehler - Technik informiert"; break;
				default: statusText = "";
			}
			logger.info("status: " + response);
			logger.info("statusText: " + statusText);
			if (response != null && response.equals("100")) return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return false;
	}
}
