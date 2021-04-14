package com.bvtech.registration.portal.provider;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.bvtech.registration.portal.bean.ProcessInformation;
import com.bvtech.registration.portal.service.RegistrationPortalService;

public class RegistrationCodeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static String ACCEPT_DEFAULT_SUFFIX_VOUCHER="_3579";
	private static String REJECT_DEFAULT_SUFFIX_VOUCHER="_7534";
	
	protected Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private RegistrationPortalService registrationPortalService;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		handle(request, response, authentication);
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			log.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}

		request.getSession().setMaxInactiveInterval(30*60); // 30*60 second
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/**
	 * Builds the target URL according to the logic defined in specifications doc
	 * 
	 */
	protected String determineTargetUrl(Authentication authentication) {
		log.info("Determine target url for code ["+authentication.getName()+"]");

		if(authentication.getName().endsWith(ACCEPT_DEFAULT_SUFFIX_VOUCHER))
	        	return "/coach/inserted-reregister?code="+authentication.getCredentials();
	        
	    if(authentication.getName().endsWith(REJECT_DEFAULT_SUFFIX_VOUCHER))
	        	return "/coach/inserted-reject?code="+authentication.getCredentials();
	        
		ProcessInformation pi = registrationPortalService.getProcessInformation((Integer) authentication.getDetails());
		
		if(pi==null || pi.getTargetUrl()==null) {
			Object details = authentication.getDetails();
			
			registrationPortalService.insertSecuredInsertCode((Integer) details);
			return "/secure/screen3.html";
		}
		else return pi.getTargetUrl();
//		if (isUser) {
//			return "/homepage.html";
//		} else if (isAdmin) {
//			return "/console.html";
//		} else {
//			throw new IllegalStateException();
//		}
//		return "/secure/register.html";
	}
}
