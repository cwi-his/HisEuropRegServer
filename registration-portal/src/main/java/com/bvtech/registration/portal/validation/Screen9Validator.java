package com.bvtech.registration.portal.validation;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bvtech.registration.portal.bean.Screen5;
import com.bvtech.registration.portal.bean.Screen9;

@Component
public class Screen9Validator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Screen9.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Screen9 screen9 = (Screen9)target;
		Integer bmiWeight = screen9.getBmiWeight();
		Integer bmiHeight = screen9.getBmiHeight();
		
		// bmiWeight
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bmiWeight", "common.message.emptyValue");
		if(bmiWeight!=null)
			if(bmiWeight<40 || bmiWeight>300)
				errors.rejectValue("bmiWeight","screen5.messageError.bmiWeight");
		
		//bmiHeight
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bmiHeight", "common.message.emptyValue");
		if(bmiHeight!=null)
			if(bmiHeight<110 || bmiHeight>290)
				errors.rejectValue("bmiHeight","screen5.messageError.bmiHeight");
	}
	
	

}
