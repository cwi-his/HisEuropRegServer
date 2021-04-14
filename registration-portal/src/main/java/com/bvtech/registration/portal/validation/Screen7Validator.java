package com.bvtech.registration.portal.validation;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.bvtech.registration.portal.bean.Screen7;

@Component
public class Screen7Validator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Screen7.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "typesSection1a", "screen7.question1a");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "typesSection1b", "screen7.question1b");
		
	}
	
	

}
