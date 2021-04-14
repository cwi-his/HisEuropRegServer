package com.bvtech.registration.portal.validation;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.bvtech.registration.portal.bean.Screen8;

@Component
public class Screen8Validator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Screen8.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "typesSection2a", "screen8.question2a");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "typesSection2b", "screen8.question2b");
		
	}
	
	

}
