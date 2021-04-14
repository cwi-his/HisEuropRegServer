package com.bvtech.registration.portal.mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bvtech.registration.portal.bean.PersonalInformation;
import com.bvtech.registration.portal.bean.PopupAlert;
import com.bvtech.registration.portal.bean.ProcessInformation;
import com.bvtech.registration.portal.bean.RegistrationCode;
import com.bvtech.registration.portal.bean.Screen10;
import com.bvtech.registration.portal.bean.Screen5;
import com.bvtech.registration.portal.bean.Screen5b;
import com.bvtech.registration.portal.bean.Screen7;
import com.bvtech.registration.portal.bean.Screen8;
import com.bvtech.registration.portal.bean.Screen9;
import com.bvtech.registration.portal.enumeration.RegistrationCodeStatus;
import com.bvtech.registration.portal.enumeration.UserStatus;
import com.bvtech.registration.portal.service.IntegrationService;
import com.bvtech.registration.portal.service.RegistrationPortalService;
import com.bvtech.registration.portal.validation.CustomerValidator;
import com.bvtech.registration.portal.validation.Screen5Validator;
import com.bvtech.registration.portal.validation.Screen5bValidator;
import com.bvtech.registration.portal.validation.Screen7Validator;
import com.bvtech.registration.portal.validation.Screen8Validator;
import com.bvtech.registration.portal.validation.Screen9Validator;

@Controller
@Scope("request")
public class RegistrationPortalController implements MessageSourceAware {

	@Autowired
	RegistrationPortalService registrationPortalService;

	@Autowired
	IntegrationService integrationService;

	@Autowired
	CustomerValidator customerValidator;

	@Autowired
	Screen5Validator screen5Validator;
	
	@Autowired
	Screen5bValidator screen5bValidator;

	@Autowired
	Screen7Validator screen7Validator;

	@Autowired
	Screen8Validator screen8Validator;

	@Autowired
	Screen9Validator screen9Validator;

	final static Log log = LogFactory.getLog(RegistrationPortalController.class);

	@Value("${registration.portal.url}")
	private String webUrl;
	
	@Value("${ghs.redirecturl}")
    private String redirectUrl;
	
	public String redirectToPartner() {
		return "redirect:" + redirectUrl;
	}

	// Message for Coach
	@RequestMapping(value = "/coach/inserted-reregister", method = RequestMethod.GET)
	public String reregisterCode(@RequestParam("code") String code, ModelMap model, Locale locale) {
		model.addAttribute("viewMsg", messageSource.getMessage("reregister.code.submitted", null, locale).replace("?", code));
		
		RegistrationCode registrationCode = registrationPortalService.getInfo(code);
		
		// insert a patient to send and shipping to send
		registrationPortalService.useReRegisterCodeByCoach(registrationCode.getIdRegCod());
		
		return "welcome";
	}
	@RequestMapping(value = "/coach//inserted-reject", method = RequestMethod.GET)
	public String rejectCode(@RequestParam("code") String code, ModelMap model, Locale locale) {
		model.addAttribute("viewMsg", messageSource.getMessage("rejected.code.submitted", null, locale).replace("?", code));
		return "welcome";
	}
	// End message for Coach
	
	@RequestMapping(value = "/operator", method = RequestMethod.GET)
	public String enterOperatorCode(Model model, Locale locale, @RequestParam(required = false) String id_token) {
		model.addAttribute("token", id_token);
		
		return "operator";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showRoot(ModelMap model) {
		return "index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showIndex(ModelMap model) {
		return "index";
	}
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String showIndexHtml(ModelMap model) {
		return "index";
	}

	@RequestMapping(value = "/operator.html?error=true", method = RequestMethod.GET)
	public String enterOperatorCodeError(ModelMap model) {
		return "operator";
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String securedInsertCode(Model model, Locale locale, @RequestParam(required = false) String id_token, @RequestParam(required = false) String error) {
		if (error != null && error.equalsIgnoreCase("true")) {
			return "code_used";
		}
		
		if (id_token == null || id_token.isEmpty()) {
			return redirectToPartner();
		}
		
		model.addAttribute("token", id_token);
		
		return "welcome";
	}

	@RequestMapping(value = "/welcome.html?error=true", method = RequestMethod.GET)
	public String securedInsertCodeError(ModelMap model) {
		return "code_used";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String errorPage(ModelMap model) {
		return redirectToPartner();
	}
	
	private boolean getPagePermission(String url) {

		ProcessInformation pi = registrationPortalService.getProcessInformation(getIdRegCod());
		if (pi.getTargetUrl().substring(pi.getTargetUrl().lastIndexOf("/secure/"))
				.equals(url.substring(url.lastIndexOf("/secure/")))) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/secure/screen3", method = RequestMethod.GET)
	public String viewScreen3(Model model, HttpServletRequest request, Locale locale) {
		if (!checkPagePermission(model, request, locale)) return redirectToPartner();
		return "screen3";
	}

	@RequestMapping(value = "/secure/screen3", method = RequestMethod.POST)
	public String viewScreen3POST(Model model, Locale locale) {
		Integer idStaPro = RegistrationCodeStatus.PARTIALLY_USED.getValue();
		
		ProcessInformation procinf = registrationPortalService.getIdPerInf(getIdRegCod());
		PersonalInformation pi = registrationPortalService.getPersonalInformation(procinf.getIdPerInf());

		Screen5 screen5 = new Screen5();
		
		model.addAttribute("screen5", screen5);
		model.addAttribute("address1", pi.getName() + " " + pi.getSurname());
		model.addAttribute("address2", pi.getAddress());
		model.addAttribute("address3", pi.getPostalCode() + " " + pi.getCity());
		
		//refreshScreen5List(model);

		registrationPortalService.insertScreen3(getIdRegCod(), idStaPro, 2, UserStatus.IN_EVALUATION.getValue());
		
		return "screen5";
	}

	@RequestMapping(value = "/secure/screen5", method = RequestMethod.GET)
	public String viewScreen5(Model model, HttpServletRequest request, Locale locale) {
		/*if (!getPagePermission(request.getRequestURI())) return redirectToPartner();*/
		if (!checkPagePermission(model, request, locale)) return redirectToPartner();
		
		ProcessInformation procinf = registrationPortalService.getIdPerInf(getIdRegCod());
		PersonalInformation pi = registrationPortalService.getPersonalInformation(procinf.getIdPerInf());
		
		Screen5 screen5 = new Screen5();

		model.addAttribute("screen5", screen5);
		model.addAttribute("address1", pi.getName() + " " + pi.getSurname());
		model.addAttribute("address2", pi.getAddress());
		model.addAttribute("address3", pi.getPostalCode() + " " + pi.getCity());

		//refreshScreen5List(model);

		return "screen5";
	}

	@RequestMapping(value = "/secure/screen5", method = RequestMethod.POST)
	public String viewScreen5POST(@Valid @ModelAttribute("screen5") Screen5 screen5, BindingResult result, Model model)
			throws Exception {

		screen5Validator.validate(screen5, result);

		if (result.hasErrors()) {
			ProcessInformation procinf = registrationPortalService.getIdPerInf(getIdRegCod());
			PersonalInformation pi = registrationPortalService.getPersonalInformation(procinf.getIdPerInf());
			
			model.addAttribute("screen5", screen5);
			model.addAttribute("address1", pi.getName() + " " + pi.getSurname());
			model.addAttribute("address2", pi.getAddress());
			model.addAttribute("address3", pi.getPostalCode() + " " + pi.getCity());

			return "screen5";
		} else {
			registrationPortalService.insertScreen5(getPersonalInformationData(screen5), getIdRegCod());
			
			ProcessInformation pi = registrationPortalService.getIdPerInf(getIdRegCod());
			Integer idProInf = pi.getIdProInf();
			Integer idPerInf = pi.getIdPerInf();

			
			PersonalInformation personalinfo = registrationPortalService.getPersonalInformation(pi.getIdPerInf());
			
		
			Screen5b screen5b = new Screen5b();
			model.addAttribute("screen5b", screen5b);
			model.addAttribute("phone", personalinfo.getTelephoneNumber());

			return "screen5b";
		}
	}
	
	@RequestMapping(value = "/secure/screen5b", method = RequestMethod.GET)
	public String viewScreen5b(Model model, HttpServletRequest request, Locale locale) {
		/*if (!getPagePermission(request.getRequestURI())) return redirectToPartner();*/
		if (!checkPagePermission(model, request, locale)) return redirectToPartner();
		
		ProcessInformation procinf = registrationPortalService.getIdPerInf(getIdRegCod());
		PersonalInformation pi = registrationPortalService.getPersonalInformation(procinf.getIdPerInf());
		
		Screen5b screen5b = new Screen5b();
		
		model.addAttribute("screen5b", screen5b);
		model.addAttribute("phone", pi.getTelephoneNumber());
		
		return "screen5b";
	}

	@RequestMapping(value = "/secure/screen5b", method = RequestMethod.POST)
	public String viewScreen5bPOST(@Valid @ModelAttribute("screen5b") Screen5b screen5b, BindingResult result, Model model,Locale locale)
			throws Exception {
		log.info("screen5b - RequestMethod.POST RequestMethod.POST");
		
		screen5bValidator.validate(screen5b, result);

		if (result.hasErrors()) {
			ProcessInformation procinf = registrationPortalService.getIdPerInf(getIdRegCod());
			PersonalInformation pi = registrationPortalService.getPersonalInformation(procinf.getIdPerInf());
			
			model.addAttribute("screen5b", screen5b);
			model.addAttribute("phone", pi.getTelephoneNumber());
			log.info("hasErrors - RequestMethod.POST RequestMethod.POST");
			return "screen5b";
		} else {
			registrationPortalService.insertScreen5b(getPersonalInformationData(screen5b), getIdRegCod());
			
			ProcessInformation pi = registrationPortalService.getIdPerInf(getIdRegCod());

			//GHS modification all Participants will participate to the program
			//mark the registration Process as positive 
			registrationPortalService.endProcess(getIdRegCod(),UserStatus.ACCEPTED.getValue());
			// Show registration Complete Message
			model.addAttribute("message", messageSource.getMessage("screen10.endScreen3.value", null, locale));
			model.addAttribute("message2", messageSource.getMessage("screen10.endScreen4.value", null, locale));

			
			return "end_process";
			
			/*Screen7 screen7 = new Screen7();
			model.addAttribute("screen7", screen7);
			refreshScreen7List(model);
			return "screen7";*/
		}
	}

	@RequestMapping(value = "/secure/screen7", method = RequestMethod.GET)
	public String viewScreen7(Model model, HttpServletRequest request, Locale locale) {
		if (!checkPagePermission(model, request, locale)) return redirectToPartner();
		
		Screen7 screen7 = new Screen7();
		model.addAttribute("screen7", screen7);

		refreshScreen7List(model);

		return "screen7";
	}
	
	@RequestMapping(value = "/secure/screen7", method = RequestMethod.POST)
	public String viewScreen7POST(@Valid @ModelAttribute("screen7") Screen7 screen7, BindingResult result,
			Model model) {

		screen7Validator.validate(screen7, result);

		if (result.hasErrors()) {
			refreshScreen7List(model);
			return "screen7";
		} else {
			// validate ok
			// insert personal process
			
			registrationPortalService.insertScreen7(getIdRegCod(), screen7.getTypesSection1a(), screen7.getTypesSection1b());

			Screen8 screen8 = new Screen8();
			model.addAttribute("screen8", screen8);
			refreshScreen8List(model);
			return "screen8";
		}
	}

	@RequestMapping(value = "/secure/screen8", method = RequestMethod.GET)
	public String viewScreen8(Model model, HttpServletRequest request, Locale locale) {
		if (!checkPagePermission(model, request, locale)) return redirectToPartner();

		Screen8 screen8 = new Screen8();
		model.addAttribute("screen8", screen8);

		refreshScreen8List(model);

		return "screen8";
	}

	@RequestMapping(value = "/secure/screen8", method = RequestMethod.POST)
	public String viewScreen8POST(@Valid @ModelAttribute("screen8") Screen8 screen8, BindingResult result,
			Model model) {

		screen8Validator.validate(screen8, result);

		if (result.hasErrors()) {
			refreshScreen8List(model);
			return "screen8";
		} else {
			// validate ok
			// insert personal process
			
			registrationPortalService.insertScreen8(getIdRegCod(), screen8.getTypesSection2a(), screen8.getTypesSection2b());

			Screen9 screen9 = new Screen9();
			model.addAttribute("screen9", screen9);
			return "screen9";
		}
	}

	@RequestMapping(value = "/secure/screen9", method = RequestMethod.GET)
	public String viewScreen9(Model model, HttpServletRequest request, Locale locale) {
		if (!checkPagePermission(model, request, locale)) return redirectToPartner();

		Screen9 screen9 = new Screen9();
		model.addAttribute("screen9", screen9);
		return "screen9";
	}

	@RequestMapping(value = "/secure/screen9", method = RequestMethod.POST)
	public String viewScreen9POST(@Valid @ModelAttribute("screen9") Screen9 screen9, BindingResult result,
			Model model) {

		screen9Validator.validate(screen9, result);

		if (result.hasErrors()) {
			model.addAttribute("screen9", screen9);
			return "screen9";
		} else {
			// validate ok
			// insert personal process
			// calcolo bmi
			// (question_weight) / (question_height/100)²
			Double bmi;
			Double height;
			height = ((double) screen9.getBmiHeight() / 100);
			height = height * height;
			bmi = screen9.getBmiWeight() / height;
			registrationPortalService.insertScreen9(getIdRegCod(), screen9.getBmiWeight(), screen9.getBmiHeight(), bmi);

			Screen10 screen10 = new Screen10();
			refreshScreen10List(model);
			model.addAttribute("screen10", screen10);
			return "screen10";
		}
	}

	private Boolean checkPagePermission(Model model, HttpServletRequest request,Locale locale) {

		if (!getPagePermission(request.getRequestURI())){
			model.addAttribute("title", messageSource.getMessage("errorPage.title.value", null, locale));
			model.addAttribute("message", messageSource.getMessage("errorPage.message.value", null, locale));
			model.addAttribute("link", messageSource.getMessage("errorPage.link.value", null, locale));
			return false;
		}
		return true;
	}
	
	@RequestMapping(value = "/secure/screen10", method = RequestMethod.GET)
	public String viewScreen10(Model model, HttpServletRequest request,Locale locale) {

		if (!checkPagePermission(model, request, locale)) return redirectToPartner();

		Screen10 screen10 = new Screen10();
		refreshScreen10List(model);
		model.addAttribute("screen10", screen10);
		return "screen10";
	}

	@RequestMapping(value = "/secure/screen10", method = RequestMethod.POST)
	public String viewScreen10POST(@Valid @ModelAttribute("screen10") Screen10 screen10, BindingResult result,
			Model model, Locale locale) {

		// screen10Validator.validate(screen9, result);

		// validate ok
		// insert personal process

		registrationPortalService.insertScreen10(getIdRegCod(), screen10.getQuestionInsuline(), screen10.getQuestionInsulineB());

		ProcessInformation pi = registrationPortalService.getIdPerInf(getIdRegCod());

		// check if process continue
		if (evaluationQuestionnaires(pi)) {
			registrationPortalService.endProcess(getIdRegCod(),UserStatus.ACCEPTED.getValue());

			model.addAttribute("message", messageSource.getMessage("screen10.endScreen3.value", null, locale));
			model.addAttribute("message2", messageSource.getMessage("screen10.endScreen4.value", null, locale));

			return "end_process";
		}
		else {
			// gen reregister and reject code

			registrationPortalService.insertGenCode(genReregisterCode(), genRejectCode(), getIdRegCod());
			registrationPortalService.endProcess(getIdRegCod(),UserStatus.CONDITIONALLY_DISQUALIFIED.getValue());

			model.addAttribute("message", messageSource.getMessage("screen10.endScreen.value", null, locale));
			model.addAttribute("message2", messageSource.getMessage("screen10.endScreen2.value", null, locale));

			return "end_process";
		}
	}

	private boolean evaluationQuestionnaires(ProcessInformation pi) {
		Integer countYes = 0;
		if (pi.getQuestionSection1A() == 1) {
			countYes += 1;
		}
		if (pi.getQuestionSection1B() == 1) {
			countYes += 1;
		}
		if (pi.getQuestionSection2A() == 1) {
			countYes += 1;
		}
		if (pi.getQuestionSection2B() == 1) {
			countYes += 1;
		}
		if (pi.getBmi() < 25) {
			countYes += 1;
		}
		if ((countYes < 2) && (pi.getIdStaUse() != UserStatus.CONDITIONALLY_DISQUALIFIED.getValue())) {
			return true;
		}
		return false;
	}

	public Integer getIdRegCod() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context.getAuthentication();
//		if(auth==null || auth.getDetails()==null)
//			return null;
		try{
			return (Integer) auth.getDetails();// Get the id
		} catch (ClassCastException cce){
			return null;
		}
	}

	private void refreshScreen7List(Model model) {
		Screen7.initSection1aList(model);
		Screen7.initSection1bList(model);
	}

	private void refreshScreen8List(Model model) {
		Screen8.initSection2aList(model);
		Screen8.initSection2bList(model);
	}

	private void refreshScreen10List(Model model) {
		Screen10.initInsulineList(model);
		Screen10.initInsulineBList(model);
	}

	private PersonalInformation getPersonalInformationData(Screen5 s5) {
		ProcessInformation procinf = registrationPortalService.getIdPerInf(getIdRegCod());
		PersonalInformation pi = registrationPortalService.getPersonalInformation(procinf.getIdPerInf());

		// different delivery address
		pi.setOptSurname(s5.getOptSurname());
		pi.setOptName(s5.getOptName());
		pi.setOptAddress(s5.getOptAddress());
		pi.setOptHouseNumber(s5.getOptHouseNumber());
		pi.setOptAddressAdditional(s5.getOptAddressAdditional());
		pi.setOptPostalCode(s5.getOptPostalCode());
		pi.setOptCity(s5.getOptCity());
		pi.setOptNote(s5.getOptNote());
		
		pi.setSendNow(s5.getSendNow() ? 1 : 0);
		log.info("pi.setSendFromDate(s5.getFromDate() "+s5.getFromDate());
		
		pi.setSendFromDate(s5.getFromDate() == "" ? null : getDateFromString(s5.getFromDate()));
		
		pi.setInsertDate(new Date());

		return pi;
	}
	
	private PersonalInformation getPersonalInformationData(Screen5b s5) {
		ProcessInformation procinf = registrationPortalService.getIdPerInf(getIdRegCod());
		PersonalInformation pi = registrationPortalService.getPersonalInformation(procinf.getIdPerInf());

		pi.setTelephoneNumber2(s5.getTelephoneNumber2());
		pi.setAvailability(s5.getAvailability());
		pi.setAvailability2(s5.getAvailability2());
		pi.setAvailability3(s5.getAvailability3());
		pi.setAvailability4(s5.getAvailability4());
		
		pi.setContactNow(s5.getContactNow() ? 1 : 0);
		pi.setFromDate(s5.getFromDate() == "" ? null : getDateFromString(s5.getFromDate()));
		
		pi.setInsertDate(new Date());

		return pi;
	}

	@RequestMapping(value = "/secure/popup", method = RequestMethod.GET)
	public void doDownload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pdf", required = false) String pdf) throws IOException {

		String filename = integrationService.readFileTermsConditions(pdf);


		if (filename != "") {
			File downloadFile = new File(filename);
			FileInputStream inputStream = new FileInputStream(downloadFile);

			response.setContentType("application/pdf");

			// get output stream of the response
			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[100];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outStream.close();
		}

	}
	
	@RequestMapping(value = "/secure/popupAlert", method = RequestMethod.GET)
	public String viewAlertPopup(Model model, HttpServletRequest request,Locale locale,
			@RequestParam(value = "alert", required = false) String alert) throws IOException {

		PopupAlert popupAlert= new PopupAlert();
		model.addAttribute("popupAlert", popupAlert);
		switch (alert) {
		case "1":
			model.addAttribute("alert",messageSource.getMessage("screen3.message.value", null, locale));
			break;

		case "2":
			model.addAttribute("alert", messageSource.getMessage("screen3b.message.value", null, locale));
			break;
			
		case "3":
			model.addAttribute("alert", messageSource.getMessage("screen3c.message.value", null, locale));
			break;
		}
		
		return "popupAlert";

	}
	
	
	@RequestMapping(value = "/impressum", method = RequestMethod.GET)
	public String viewImpressumPage(Model model, HttpServletRequest request,Locale locale) throws IOException {
		return "impressum";
	}
	
	@RequestMapping(value = "/datenschutz", method = RequestMethod.GET)
	public String viewDatenschutzPage(Model model, HttpServletRequest request,Locale locale) throws IOException {
		return "datenschutz";
	}
	

	private String genReregisterCode() {

//		StringBuilder b = new StringBuilder();
//		Random r = new Random();
//		String subset = "0123456789";
//
//		for (int i = 0; i < 10; i++) {
//			int index = r.nextInt(subset.length());
//			char c = subset.charAt(index);
//			b.append(c);
//		}
//
//		return b.toString() + "_3579";
		return "_3579";
	}

	private String genRejectCode() {
//
//		StringBuilder b = new StringBuilder();
//		Random r = new Random();
//		String subset = "0123456789";
//
//		for (int i = 0; i < 10; i++) {
//			int index = r.nextInt(subset.length());
//			char c = subset.charAt(index);
//			b.append(c);
//		}
//
//		return b.toString() + "_7534";
		return "_7534";
	}

	private MessageSource messageSource;

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	private Date getDateFromString(String date) {
		if(date!=null) {
			if((date.length()>0)&(date!="")) {
				try {
		
					DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
					Date startDate = df.parse(date);
		
					return startDate;
		
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		} 
		return null;

	}
}
