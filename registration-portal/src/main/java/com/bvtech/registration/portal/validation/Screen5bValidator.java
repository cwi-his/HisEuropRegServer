package com.bvtech.registration.portal.validation;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bvtech.registration.portal.bean.Screen5b;

@Component
public class Screen5bValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Screen5b.class.isAssignableFrom(clazz);
	}

	
	public void validate(Object target, Errors errors) {
		
		Screen5b screen5b = (Screen5b)target;
		String dateFrom = screen5b.getFromDate();
		Boolean contactNow = screen5b.getContactNow();
		Integer availability = screen5b.getAvailability();
		Integer availability2 = screen5b.getAvailability2();
		Integer availability3 = screen5b.getAvailability3();
		Integer availability4 = screen5b.getAvailability4();

		if (availability.equals(0)
				&& availability2.equals(0)
				&& availability3.equals(0)
				&& availability4.equals(0)) {
			errors.rejectValue("availability","common.message.emptyValue");
		}
		
		if (dateFrom == "" && !contactNow) {
			errors.rejectValue("fromDate","common.message.emptyValue");
		}
		
		
		if (dateFrom=="") return;
		if((dateFrom.length()>0)&(dateFrom!="")){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
				Date dateF = sdf.parse(dateFrom);
				Date dateNow = sdf.parse(sdf.format(new Date()));
				if (!dateFrom.equals(null)) {
					 if(dateF.before(dateNow))
					 	errors.rejectValue("fromDate","screen5.messageError.dateFrom");
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
}
