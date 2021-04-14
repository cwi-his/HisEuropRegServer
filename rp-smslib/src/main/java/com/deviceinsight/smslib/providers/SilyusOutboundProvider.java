package com.deviceinsight.smslib.providers;

import com.deviceinsight.smslib.SMSMessage;
import com.deviceinsight.smslib.SMSMessageProperties;
import com.deviceinsight.smslib.log.SMSLogger;
import com.deviceinsight.smslib.outbound.ISMSOutboundProvider;
import com.deviceinsight.smslib.outbound.SMSOutboundException;
import java.io.StringWriter;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.util.StringUtils;

public class SilyusOutboundProvider extends AbstractOutboundProvider implements ISMSOutboundProvider{

	final static Logger logger = Logger.getLogger(SilyusOutboundProvider.class);
	final static SMSLogger smsLogger = SMSLogger.getLogger();

    public SilyusOutboundProvider() {
        super();
        this.targetUrl = "http://xms.silyus.com/sendxms.aspx";
    }

    public void sendMessage(SMSMessage message, Properties properties) throws SMSOutboundException {
		PostMethod method = null;
		try {

			HttpClientParams params = new HttpClientParams();
			params.setParameter(HttpClientParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(2, false));
			HttpClient http = new HttpClient(params);

            String xml = makeXmlPayload(message,properties);
            logger.debug("XML: "+xml);
			
			logger.info("method: "+targetUrl);
			method = new PostMethod(targetUrl);
			method.setRequestEntity(new StringRequestEntity(xml, "text/xml", "UTF-8"));
			int responseCode = http.executeMethod(method);
			logger.info("responseCode: "+responseCode);
			if (responseCode!=HttpStatus.SC_OK) {
				throw new SMSOutboundException("Unexpected http response code: "+responseCode);
			}
			
			String response = method.getResponseBodyAsString();
			Pattern pErrCode = Pattern.compile("<ErrorCode>(\\d+)</ErrorCode>",Pattern.MULTILINE);
			Pattern pErrMsg = Pattern.compile("<ErrorDescription>([^<]*)</ErrorDescription>",Pattern.MULTILINE);
			Matcher mCode = pErrCode.matcher(response);
			Matcher mMsg  = pErrMsg.matcher(response);
			
			if (mCode.find()) {
				int errCode = Integer.valueOf(mCode.group(1));
				if (errCode==1)
					smsLogger.log(message, "silyus");
				else {
					String errorMsg = "";
					if (mMsg.find()) {
						errorMsg = mMsg.group(1);
					}
					throw new SMSOutboundException("Error code: "+errCode+" ("+errorMsg+")");
				}
				
			} else {
					throw new SMSOutboundException("Unexpected http response: "+response);
			}
			
			
		} catch (Exception exc) {
			logger.error("Cannot send sms", exc);
			throw new SMSOutboundException(exc.toString());
		} finally {
            if (method!=null)
                method.releaseConnection();
        }
		
	}

    public String makeXmlPayload(SMSMessage message, Properties properties) throws SMSOutboundException {
        if (message.getTo() == null) throw new SMSOutboundException("no message target");
        if (message.getMessage() == null) throw new SMSOutboundException("no message text");

        String targetUrl = properties.getProperty(SMSMessageProperties.TARGET_URL, this.targetUrl);
        if (targetUrl == null) throw new SMSOutboundException("no target URL defined");

        String password = properties.getProperty(SMSMessageProperties.PASSWORD, this.password);
        if (password == null) throw new SMSOutboundException("no password defined");

        String user = properties.getProperty(SMSMessageProperties.USER, this.user);
        if (user == null) throw new SMSOutboundException("no user defined");

        StringWriter stringWriter = new StringWriter();
        try {
            XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newFactory().createXMLStreamWriter(stringWriter);
            xmlStreamWriter.writeStartDocument("UTF-8", "1.0");
            xmlStreamWriter.writeStartElement("xms");

            xmlStreamWriter.writeStartElement("Userkey");
            xmlStreamWriter.writeCharacters(user);
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("Password");
            xmlStreamWriter.writeCharacters(password);
            xmlStreamWriter.writeEndElement();

            if (StringUtils.hasLength(message.getFrom())) {
                xmlStreamWriter.writeStartElement("Originator");
                xmlStreamWriter.writeCharacters(message.getFrom());
                xmlStreamWriter.writeEndElement();
            }

            xmlStreamWriter.writeStartElement("Recipient");
            xmlStreamWriter.writeStartElement("PhoneNumber");
            xmlStreamWriter.writeCharacters(formatPhoneNr(message.getTo()));
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("MessageData");
            xmlStreamWriter.writeCharacters(message.getMessage());
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.flush();
        } catch (XMLStreamException e) {
            logger.error("XML Exception: ",e);
            throw new SMSOutboundException("XML Exception");
        }
        return stringWriter.toString();
    }

    protected String formatPhoneNr(String phoneNr) {
		
		if (phoneNr == null) return null;
		else if (phoneNr.startsWith("+")) phoneNr = "00"+phoneNr.substring(1);
		else if (phoneNr.startsWith("00")) {}
		else if (phoneNr.startsWith("0")) phoneNr = "0049" + phoneNr.substring(1);
		phoneNr = phoneNr.replaceAll("[^\\d]", "");
		return phoneNr;
	}

}
