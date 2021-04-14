package com.bvtech.registration.portal.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import com.bvtech.registration.portal.bean.RegistrationCode;
import com.bvtech.registration.portal.service.IntegrationService;
import com.bvtech.registration.portal.service.RegistrationPortalService;
import com.bvtech.sender.mail.MAILMessage;
import com.bvtech.sender.mail.SendMail;

@Component("purgerUserData")
public class PurgerUserData implements MessageSourceAware {
	@Autowired
	IntegrationService integrationService;

	@Autowired
	RegistrationPortalService registrationPortalService;

	@Autowired
	SendMail sendMail;

	Log log = LogFactory.getLog(this.getClass());

	private MessageSource messageSource;

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void execute() throws JobExecutionException {
		log.info("PurgerUserData - TASK ESXECUTION START");
		
		log.info("Expiration Code");
		// CODE EXPIRED
		List<RegistrationCode> regCodExpired = integrationService.getListCodeExpired();
		integrationService.updListRegCodToExpired(regCodExpired);

		log.info("Send mail - 72h");
		// SEND EMAIL
		List<Integer> regCodSendEmail = integrationService.getListCodeSendEmail();
		
		Iterator<Integer> rCSE = regCodSendEmail.iterator();
		while (rCSE.hasNext()) {

			Integer idRegCod=rCSE.next();
			if (idRegCod == null)
				continue;
			
			String emailTo=null;
			try{
				emailTo = registrationPortalService.getPersonalInformation(registrationPortalService.getIdPerInf(idRegCod).getIdPerInf()).getEmail();
			} catch (RuntimeException re){
				log.info("there is no email for if_reg_code ["+idRegCod+"]");
			}
			
			if(emailTo!=null) {
				sendMailToInterruptedRegistration(emailTo);
				integrationService.updListRegCodToSendEmail(idRegCod);
			}
		}

		log.info("Delete after interruption");
		// DEL DATA AFTER INTERRUPTED REG
		List<Integer> regCodInter = integrationService.getListCodeInterruptedReg();
		integrationService.delListRegCod(regCodInter);

		log.info("Delete partially refused");
		// DEL PARTIALLY REFUSED USERS
		List<Integer> regCodPartRef = integrationService.getListCodePartiallyRefused();
		integrationService.delListRegCod(regCodPartRef);
		
		log.debug("delete orphans personals_informations");
		integrationService.deleteOrphanPersonalInformation();
		
		log.info("PurgerUserData - TASK EXECUTION END");
	}
	
	private void sendMailToInterruptedRegistration(String to){
		String emailSubject = messageSource.getMessage("email.notCompleted.subject", null, Locale.GERMAN);
		String emailMessage = messageSource.getMessage("email.notCompleted.message", null, Locale.GERMAN);
		
		MAILMessage mm = new MAILMessage();
		mm.setTo(to);
		mm.setSubject(emailSubject);
		mm.setMessage(emailMessage);

		sendMail.send(mm);
	}
}
