package com.bvtech.registration.portal.test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.bvtech.registration.portal.bean.PatientToSend;
import com.bvtech.registration.portal.bean.RowsCsvView;
import com.bvtech.registration.portal.idao.IRegistrationCodeDao;
import com.bvtech.registration.portal.idao.IRowsCsvViewDao;
import com.bvtech.registration.portal.service.IntegrationService;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:**/application-context-bl-test.xml",
		"classpath*:**/application-context-datasource-test.xml", "classpath*:**/application-context-dao-test.xml" })
public class IntegrationServiceTest {

	@Autowired
	IntegrationService integrationService;

	@Autowired
	IRegistrationCodeDao registrationCodeDao;
	
	@Autowired
	IRowsCsvViewDao rowsCsvViewDao;
	
	@Test
	public void createCSV(){
//		List<RowsCsvView> users = makeFakeUsers();
		List<RowsCsvView> users = rowsCsvViewDao.namedQuery(RowsCsvView.RETRIEVE_CSV_ROWS);
		integrationService.createCSVFile(users,"/home/raffaele/csv/tosend/CPohl-test.csv");
	}

	private List<RowsCsvView> makeFakeUsers() {
		List<RowsCsvView> users = new ArrayList<RowsCsvView>(3);

		// Simplest scenario. Will delegate to Podam all decisions
		PodamFactory factory = new PodamFactoryImpl();

		// This will use constructor with maximum arguments and
		// then setters to populate POJO
		RowsCsvView user1 = factory.manufacturePojoWithFullData(RowsCsvView.class);
		RowsCsvView user2 = factory.manufacturePojoWithFullData(RowsCsvView.class);
		RowsCsvView user3 = factory.manufacturePojoWithFullData(RowsCsvView.class);

		System.out.println(user1.toString());
		System.out.println(user2.toString());
		System.out.println(user3.toString());

		users.add(user1);
		users.add(user2);
		users.add(user3);

		return users;
	}
	
	// NON PIU' USATO
	@Test
	public void testModulo(){
		Calendar rightNow = Calendar.getInstance();
		int fileSuffix = rightNow.get(Calendar.HOUR_OF_DAY) % 4; // this suffix is a number module 4  to append to file name
		System.out.println(fileSuffix);
		System.out.println("TEST For HOUR od Day");
		
		for(int i=0; i<24; i++){
			System.out.println(i/4);
		}
		
		
		String prefix= ""+rightNow.get(Calendar.YEAR)+(rightNow.get(Calendar.MONTH)+1)+rightNow.get(Calendar.DAY_OF_MONTH);
		
		System.out.println(prefix);
	}
	
	

	@Test
	public void startSendUserDataExecute() throws MalformedURLException, URISyntaxException{
		PatientToSend pts = new PatientToSend();
		pts.setFirstname("firstname");
		pts.setLastname("lastname");
		pts.setBirthDate(new Date());
		pts.setGender("M");
		pts.setBodyWeight(80+"");
		pts.setBodyHeight(176+"");
	
		
		//String uri = new String("http://:8080/springmvc-resttemplate-test/api/{id}");
		
		sendMessage(pts);
		
//		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
//		map.add("RequestId", "asd");
//		map.add("count", "42");
//		map.add("someValue", "complex");
//		map.add("intValue", "69");
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization", "Basic " + passwd);
//		
//		RestTemplate restTemplate = new RestTemplate();
//        String uuid = restTemplate.postForObject(url, map, String.class);
//        System.out.println("UUID: "+uuid);
//	        
//		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("POST");
//        con.setRequestProperty("User-Agent", "");
//        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//        con.setRequestProperty("Authorization", pwd);
//        
//  
//        HttpWebResponse response = (HttpWebResponse)request.GetResponse();
//         
//        if (response.StatusCode == HttpStatusCode.OK) {
//            Stream responseStream = response.GetResponseStream();
//            DecodeResource(responseStream);
//        }
//        response.Close()
//        
//		uuId = rt.postForObject(uri, map, String.class);

		
		


		//uuId = rt.postForObject(uri, u, User.class, vars);
		
		
	}
	
	
	
	
	private void sendMessage(PatientToSend pts) {
		
		
//		System.setProperty("javax.net.debug", "ssl:handshake:verbose");
//		System.setProperty("javax.net.ssl.trustStore","/usr/lib/jvm/java-7-oracle/jre/lib/security/cacerts");
		
		
		String nodeIdToCreatePatient = "29";
			
//		String CREATE_PATIENT_URL = "https://santigo-portal.de/api/createPatient";
		String CREATE_PATIENT_URL = "his.patient.create.url{}";
		
		String UPDATE_PATIENT_URL = "https://santigo-portal.de/api/updatePatient";
		String GET_NODEID_URL = "https://santigo-portal.de/api/getPatientNodeId";
		
		String autentication_base64Creds = "que$rte66;990";
		
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.add("Authorization", "Basic " + autentication_base64Creds);

			RestTemplate rt = new RestTemplate();
			rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			rt.getMessageConverters().add(new StringHttpMessageConverter());

			MultiValueMap<String, Object> mapPatient = new LinkedMultiValueMap<String, Object>();
			mapPatient.add("firstname", " ");
			mapPatient.add("lastname", " ");
			mapPatient.add("gender", "m");
			mapPatient.add("nodeId", nodeIdToCreatePatient);
			HttpEntity<MultiValueMap<String, String>> requestCreatePatient = new HttpEntity(mapPatient, headers);
			ResponseEntity<String> responseToCreatePatient = rt.postForEntity(CREATE_PATIENT_URL, requestCreatePatient, String.class);
			String patientUuId = getInfoFromXmlResult(responseToCreatePatient.getBody());
			System.out.println("UUID: "+patientUuId); // 	UUID: <ok uuid="0b0d556125614d8ba24d81b29a05e794"/>
			
			MultiValueMap<String, Object> mapUpdatePatient = new LinkedMultiValueMap<String, Object>();
			mapUpdatePatient.add("UUID", patientUuId);
			mapUpdatePatient.add("lastname", patientUuId);
			HttpEntity<MultiValueMap<String, String>> requestUpdatePatient = new HttpEntity(mapUpdatePatient, headers);
			ResponseEntity<String> responseToUpdatePatient = rt.postForEntity(UPDATE_PATIENT_URL, requestUpdatePatient, String.class);
			String uuId = getInfoFromXmlResult(responseToUpdatePatient.getBody());
			System.out.println("UUID: "+uuId);
			
			MultiValueMap<String, Object> mapGetNodeId = new LinkedMultiValueMap<String, Object>();
			mapGetNodeId.add("UUID", patientUuId);
			HttpEntity<MultiValueMap<String, String>> requestNodeId = new HttpEntity(mapGetNodeId, headers);
			ResponseEntity<String> responseNodeId = rt.postForEntity(GET_NODEID_URL, requestNodeId, String.class);
			String nodeId = getInfoFromXmlResult(responseNodeId.getBody());
			System.out.println("nodeId: "+nodeId); // 	UUID: <ok uuid="0b0d556125614d8ba24d81b29a05e794"/>
			
			System.out.println(new Date());
	}
	
	private String getInfoFromXmlResult(String value){
		return value.substring(value.indexOf("=\"")+2,value.lastIndexOf("\""));
	}
	
	@Test
	public void sendmailTest(){
		  	String user = "user";   // Newly created user on JAMES Server
	        String password = "password"; // user password

	        String fromAddress = "info@europassistance-test.com"; // newlycreateduser@localhost
	        String toAddress = "r.landi@bv-tech.it";


	        // Create a mail session
	        Properties properties = new Properties();
	        properties.put("mail.transport.protocol", "smtp");
	        properties.put("mail.smtp.host", "europassistance-demo.com");
	        properties.put("mail.smtp.port", "468");
//	        properties.put("mail.smtp.username", user);
//	        properties.put("mail.smtp.password", password);
	        Session session = Session.getDefaultInstance(properties, null);

	        try 
	        {
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(fromAddress));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));

	            message.setSubject("Email From my Own Server");
	            message.setText("Test Mail sent from My Apache James Server!!");
	            Transport.send(message);

	            System.out.println("Email sent successfully");
	        }
	        catch (MessagingException e) 
	        {
	            e.printStackTrace();
	        }
	    }
}
