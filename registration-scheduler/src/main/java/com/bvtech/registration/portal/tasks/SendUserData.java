package com.bvtech.registration.portal.tasks;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.bvtech.registration.portal.bean.PatientToSend;
import com.bvtech.registration.portal.service.IntegrationService;

@Component("sendUserData")
public class SendUserData {

	@Autowired
	private IntegrationService integrationService;
	
	Log log = LogFactory.getLog(this.getClass());
	
	@Value("${his.patient.create.url}")
	private String CREATE_PATIENT_URL;
	
	@Value("${his.patient.update.url}")
	private String UPDATE_PATIENT_URL;
	
	@Value("${his.retrieve.nodeid.url}")
	private String GET_NODE_ID_URL;
	
	@Value("${his.europassistance.project.nodeId}")
	private String NODE_ID;
	
	@Value("${his.basic.authentication}")
	private String AUTHENTICATION_BASE64CREDS;
	
	public void execute() throws JobExecutionException, Exception {
		log.info("SendUserData - TASK ESXECUTION START");

		List <PatientToSend> pt=  integrationService.getPatientToSend("S"); // All users Should be sent
		if (pt==null) {
			log.info("SendUserData - nothing to do");
			return;
		}
		
		log.info("SendUserData - Find ["+pt.size()+"] to send");
		
		// Header message
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Authorization", "Basic " + AUTHENTICATION_BASE64CREDS);
		
		// Rest full Client
		RestTemplate rt = new RestTemplate();
		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		rt.getMessageConverters().add(new StringHttpMessageConverter());
		
		Iterator<PatientToSend> it = pt.iterator();
		while (it.hasNext()) {
			String uuId = null;
			String nodeId = null;
			
			PatientToSend ps = (PatientToSend) it.next();

			Integer idRegCod = ps.getIdRegCod();
			log.debug("SendUserData - Id user ["+idRegCod+"]");
			
			MultiValueMap<String, Object> mapPatient = new LinkedMultiValueMap<String, Object>();
			mapPatient.add("firstname", " ");
			mapPatient.add("lastname", " ");
			mapPatient.add("gender", "m");
			mapPatient.add("nodeId", NODE_ID);
			HttpEntity<MultiValueMap<String, String>> requestCreatePatient = new HttpEntity(mapPatient, headers);
			ResponseEntity<String> responseToCreatePatient = rt.postForEntity(CREATE_PATIENT_URL, requestCreatePatient, String.class);
			uuId = getInfoFromXmlResult(responseToCreatePatient.getBody());
			log.debug("SendUserData - Create Patient with uuid [" + uuId + "]"); // 	UUID: <ok uuid="0b0d556125614d8ba24d81b29a05e794"/>
			
			
			MultiValueMap<String, Object> mapUpdatePatient = new LinkedMultiValueMap<String, Object>();
			mapUpdatePatient.add("UUID", uuId);
			mapUpdatePatient.add("lastname", uuId);
			HttpEntity<MultiValueMap<String, String>> requestUpdatePatient = new HttpEntity(mapUpdatePatient, headers);
			ResponseEntity<String> responseToUpdatePatient = rt.postForEntity(UPDATE_PATIENT_URL, requestUpdatePatient, String.class);
			String patientUuId = getInfoFromXmlResult(responseToUpdatePatient.getBody());
			log.debug("SendUserData - Update Patient with uuid [" + patientUuId + "]");
			
			MultiValueMap<String, Object> mapGetNodeId = new LinkedMultiValueMap<String, Object>();
			mapGetNodeId.add("UUID", uuId);
			HttpEntity<MultiValueMap<String, String>> requestNodeId = new HttpEntity(mapGetNodeId, headers);
			ResponseEntity<String> responseNodeId = rt.postForEntity(GET_NODE_ID_URL, requestNodeId, String.class);
			nodeId = getInfoFromXmlResult(responseNodeId.getBody());
			log.debug("SendUserData - NodeId retrieved ["+nodeId+" for patientUUID [" + uuId + "]"); // 	UUID: <ok uuid="0b0d556125614d8ba24d81b29a05e794"/>
						
			// aggiorno process_info con uuid e nodeid
			integrationService.insertExternalCode(idRegCod, uuId, nodeId);
			// aggiorno shipping con uuid 
			integrationService.updateShippingPatientId(idRegCod, uuId);
			// aggiorno reg_code last_update
			integrationService.updateRegCodLastUpd(idRegCod);
			// update tab pat status to D = done
			integrationService.updatePatientStatus(idRegCod, "D");
				
			log.debug("SendUserData - User ["+ uuId +"] correctly sent");
		}

		log.info("SendUserData - TASK EXECUTION END");
	}
	
	private String getInfoFromXmlResult(String value){
		return value.substring(value.indexOf("=\"")+2,value.lastIndexOf("\""));
	}

}
