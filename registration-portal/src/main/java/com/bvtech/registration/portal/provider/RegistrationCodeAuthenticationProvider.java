package com.bvtech.registration.portal.provider;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bvtech.registration.portal.bean.PersonalInformation;
import com.bvtech.registration.portal.bean.ProcessInformation;
import com.bvtech.registration.portal.bean.RegistrationCode;
import com.bvtech.registration.portal.enumeration.RegistrationCodeStatus;
import com.bvtech.registration.portal.service.RegistrationPortalService;

public class RegistrationCodeAuthenticationProvider implements AuthenticationProvider,MessageSourceAware {

	private static String ACCEPT_DEFAULT_SUFFIX_VOUCHER="_3579";
	private static String REJECT_DEFAULT_SUFFIX_VOUCHER="_7534";
	
	Log log = LogFactory.getLog(this.getClass());
	private MessageSource messageSource;
	
	@Value("${rest.user.voucher.username}")
	private String VOUCHER_USERNAME; // = "VoucherGenerator";
	@Value("${rest.user.voucher.password}")
	private String VOUCHER_PWD; //= "VoucherGenerator";
	
	@Value("${rest.user.delivery.username}")
	private String LOGISTIC_USERNAME; // = "LogisticTollUsr";
	@Value("${rest.user.delivery.password}")
	private String LOGISTIC_PWD; // = "LogisticToolPwd";
	
	
	@Value("${rest.user.termscond.username}")
	private String TERMSCOND_USERNAME; // = "LogisticTollUsr";
	@Value("${rest.user.termscond.password}")
	private String TERMSCOND_PWD; // = "LogisticToolPwd";
	
	@Value("${security.client_id}")
    private String clientId;
	
	@Value("${security.authority}")
    private String authority;
	
	@Autowired
	private RegistrationPortalService registrationPortalService;
	

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//return skipAuthentication();
		
		RegistrationCode registrationCode = null;
		String code = authentication.getName();
		
		String ipAddress = getCurrentUserIp(authentication); 
        log.info("IP ADDRESS:"+ipAddress);

        try {
			DecodedJWT decoded = JWT.decode(authentication.getName());

			if (clientId != null && !clientId.trim().isEmpty()) {
				List<String> tokenClientId = decoded.getAudience();
				if (!tokenClientId.contains(clientId)) {
					throw new BadCredentialsException(messageSource.getMessage("login.code.error", null, Locale.GERMAN));
				}
			}
			
			if (authority != null && !authority.trim().isEmpty()) {
				String tokenAuthority = decoded.getIssuer();
				if (!tokenAuthority.equals(authority)) {
					throw new BadCredentialsException(messageSource.getMessage("login.code.error", null, Locale.GERMAN));
				}
			}
			
			String attendanceId = decoded.getClaim("attendanceId").asString();
			String vnr = decoded.getClaim("extension_VNR").asString();
			code = attendanceId;
			
			if (attendanceId == null || attendanceId.trim().isEmpty()) {
				throw new BadCredentialsException(messageSource.getMessage("login.code.error", null, Locale.GERMAN));
			}

			registrationCode = registrationPortalService.getInfo(attendanceId);
			
			if (registrationCode == null) {
				log.info("NEW USER CREATED");
				
				PersonalInformation pi = new PersonalInformation();
				pi.setAddress(decoded.getClaim("streetAddress").asString());
				pi.setCity(decoded.getClaim("city").asString());
				pi.setDateBirth(new Date(new Timestamp(decoded.getClaim("extension_DateOfBirth_datetime").asInt()).getTime()*1000));
				/*long timeStamp = decoded.getClaim("extension_DateOfBirth_datetime").asLong();
				Instant instant = Instant.ofEpochSecond( timeStamp );
				pi.setDateBirth(Date.from( instant ));*/
				pi.setEmail(decoded.getClaim("email").asString());
				pi.setName(decoded.getClaim("given_name").asString());
				pi.setPostalCode(decoded.getClaim("postalCode").asString());
				pi.setSurname(decoded.getClaim("family_name").asString());
				pi.setTelephoneNumber(decoded.getClaim("extension_Phonenumber").asString());

				registrationCode = registrationPortalService.CreateRegistrationCode(attendanceId, vnr, pi);
			}
			
			code = registrationCode.getCode();
		} catch (Exception e) {
			// If it's a voucher registration code	
			registrationCode = registrationPortalService.getInfo(code);
			
			if (registrationCode == null) {
				if((authentication.getName().equals(VOUCHER_USERNAME)) && (authentication.getCredentials().equals(VOUCHER_PWD)))
					return voucherManagerUser(authentication);
		        if((authentication.getName().equals(LOGISTIC_USERNAME)) && (authentication.getCredentials().equals(LOGISTIC_PWD)))
					return deliveryManagerUser(authentication);
		        if((authentication.getName().equals(TERMSCOND_USERNAME)) && (authentication.getCredentials().equals(TERMSCOND_PWD)))
					return termsCondManagerUser(authentication);
				
		        //  Check to see if the code used is ReRegistrationCode or RejectedCode
		        if(code.endsWith(ACCEPT_DEFAULT_SUFFIX_VOUCHER))
		        	return reRegistrationCodeManager(code);
		        
		        if(code.endsWith(REJECT_DEFAULT_SUFFIX_VOUCHER))
		        	return rejectedCodeManager(code);
		        
				throw new BadCredentialsException(messageSource.getMessage("login.code.error", null, Locale.GERMAN));
			}
		}
		
		if (registrationCode == null || !registrationCode.getCode().equalsIgnoreCase(code)) {
			log.info("Registration Code ["+code+"] not found");
			registrationPortalService.insertLoginAttempts(ipAddress);
			if (!registrationPortalService.checkLoginAttempts(ipAddress))  throw new BadCredentialsException(messageSource.getMessage("login.code.attempts", null, Locale.GERMAN));
			throw new BadCredentialsException(messageSource.getMessage("login.code.error", null, Locale.GERMAN));
		}

		if (registrationCode.getIdStaPro()==RegistrationCodeStatus.EXPIRED.getValue()) {
			log.info("Registration Code ["+code+"] expired");
			throw new BadCredentialsException(messageSource.getMessage("login.code.expired", null, Locale.GERMAN));
		}

		if (registrationCode.getIdStaPro()==RegistrationCodeStatus.USED.getValue() || registrationCode.getIdStaPro()==RegistrationCodeStatus.COACH_USED.getValue()) {
			log.info("Registration Code ["+code+"] already used");
			throw new BadCredentialsException(messageSource.getMessage("login.code.used", null, Locale.GERMAN));
		}
	
		log.info("Registration Code ["+code+"] found!");
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		GrantedAuthority authorityUser = new GrantedAuthorityImpl("ROLE_USER");
		authorities.add(authorityUser);

		return new RegistrationCodeAuthenticationObj(registrationCode.getCode(), registrationCode.getIdRegCod(),  authorities);
		
	}
	// For testing 
	Authentication skipAuthentication(){
				
		PersonalInformation pi = new PersonalInformation();
		pi.setAddress("Muster Platz 1");
		pi.setCity("MusterStadt");
		pi.setDateBirth(new Date());
		pi.setEmail("Musterman@musterdomain.de");
		pi.setName("Hennry");
		pi.setPostalCode("879955");
		pi.setSurname("Musterman");
		pi.setTelephoneNumber("089 6677554433");
		String attendanceId="DIA_191121_0002";
		String vnr="201119001";
		String extension_PNR ="11";
		
		
		
		RegistrationCode registrationCode = registrationPortalService.CreateRegistrationCode(attendanceId,vnr,pi);
		String code = registrationCode.getCode();
		
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		GrantedAuthority authorityUser = new GrantedAuthorityImpl("ROLE_USER");
		authorities.add(authorityUser);

		return new RegistrationCodeAuthenticationObj(registrationCode.getCode(), registrationCode.getIdRegCod(),  authorities);
	}
	
	private Authentication rejectedCodeManager(String rejectCode) {
		// Not accepted user
		RegistrationCode registrationCode = registrationPortalService.getRejectCodeInfo(rejectCode);
		if(registrationCode==null) 
			throw new BadCredentialsException(messageSource.getMessage("rejected.code.inexistent", null, Locale.GERMAN));
		// processes_informations update Status to "Rejected by Coach" 
		// and update last_update in Registrations_codes table
		ProcessInformation pi = registrationPortalService.getIdPerInf(registrationCode.getIdRegCod());
		pi.setIdStaUse(2); // rereject
		registrationPortalService.update(pi);
		
		registrationCode.setIdStaPro(5); // usato
		registrationCode.setLastUpdate(new Date());
		registrationCode.setSubmissionTerms(new Date());
		registrationPortalService.update(registrationCode);
		
		log.info("Coach reject code inserted");
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		GrantedAuthority authorityUser = new GrantedAuthorityImpl("ROLE_COACH");
		authorities.add(authorityUser);
		
		return new RegistrationCodeAuthenticationObj(rejectCode, registrationCode.getCode(), authorities);
	}


	private Authentication reRegistrationCodeManager(String reregisterCode) {
		// re registration user
		RegistrationCode registrationCode = registrationPortalService.getReRegistrationInfo(reregisterCode);
		if(registrationCode==null) 
			throw new BadCredentialsException(messageSource.getMessage("reregister.code.inexistent", null, Locale.GERMAN));
		// processes_informations update Status to "Accepted by Coach" and update last_update in Registrations_codes table
		// BISOGNA AGGIUNGERE GLI STATI SULL TABELLA states_users
		
		ProcessInformation pi = registrationPortalService.getIdPerInf(registrationCode.getIdRegCod());
		pi.setIdStaUse(3); // accept
		registrationPortalService.update(pi);
		
		registrationCode.setIdStaPro(5); // usato
		registrationCode.setLastUpdate(new Date());
		registrationCode.setSubmissionTerms(new Date());
		registrationPortalService.update(registrationCode);
		
//		// insert a patient to send and shipping to send
//		registrationPortalService.useReRegisterCodeByCoach(registrationCode.getIdRegCod());
		
		log.info("Coach reregister code inserted");
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		GrantedAuthority authorityUser = new GrantedAuthorityImpl("ROLE_COACH");
		authorities.add(authorityUser);
		
		return new RegistrationCodeAuthenticationObj(reregisterCode, registrationCode.getCode(), authorities);
	}

	public Authentication voucherManagerUser(Authentication authentication){
		log.info("Voucher Manager Login Successul");
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		GrantedAuthority authorityUser = new GrantedAuthorityImpl("ROLE_VOUCHER_MANAGER");
		authorities.add(authorityUser);
		
		return new RegistrationCodeAuthenticationObj(authentication.getName(), authentication.getCredentials().toString(), authorities);
	}

	public Authentication deliveryManagerUser(Authentication authentication){
		log.info("Voucher Manager Login Successul");
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		GrantedAuthority authorityUser = new GrantedAuthorityImpl("ROLE_DELIVERY_MANAGER");
		authorities.add(authorityUser);
		
		return new RegistrationCodeAuthenticationObj(authentication.getName(), authentication.getCredentials().toString(), authorities);
	}
	
	public Authentication termsCondManagerUser(Authentication authentication){
		log.info("Voucher Manager Login Successul");
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		GrantedAuthority authorityUser = new GrantedAuthorityImpl("ROLE_TERMSCOND_MANAGER");
		authorities.add(authorityUser);
		
		return new RegistrationCodeAuthenticationObj(authentication.getName(), authentication.getCredentials().toString(), authorities);
	}
	
	
	public boolean supports(Class authentication) {
		return true;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public static String getCurrentUserIp(Authentication authentication) {
		if (authentication == null) {
			return "";
		}

		Object details = authentication.getDetails();
		if (!(details instanceof WebAuthenticationDetails)) {
			return "";
		}

		WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
		return webDetails.getRemoteAddress();
	}

}
