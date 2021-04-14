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

import com.bvtech.registration.portal.bean.Screen5;

@Component
public class Screen5Validator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Screen5.class.isAssignableFrom(clazz);
	}

	
	public void validate(Object target, Errors errors) {
		
		Screen5 screen5 = (Screen5)target;
		String dateFrom = screen5.getFromDate();
		Boolean sendNow = screen5.getSendNow();
		Boolean sendFromDate = screen5.getSendFromDate();
		
		//option
		Boolean differentAddress = screen5.getDifferentAddress();
		// option
		if (differentAddress){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "optName", "common.message.emptyValue");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "optSurname", "common.message.emptyValue");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "optAddress", "common.message.emptyValue");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "optHouseNumber", "common.message.emptyValue");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "optPostalCode", "common.message.emptyValue");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "optCity", "common.message.emptyValue");
		}
		
		if ((!sendNow && !sendFromDate) || (sendFromDate && dateFrom == "")) {
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
