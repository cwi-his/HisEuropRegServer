package com.bvtech.registration.portal.validation;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bvtech.registration.portal.bean.Customer;

@Component
public class CustomerValidator implements Validator {


	public void validate(Object target, Errors errors) {
		Customer customer = (Customer)target;
		int age = customer.getAge();
		String password = customer.getPassword();
		String confPassword = customer.getConfPassword();
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "customer.age.empty");
		
		//Business validation
		if(!password.equals(confPassword)){
			errors.rejectValue("password","customer.password.missMatch");
		}
		if(age < 18 || age > 60){
			errors.rejectValue("age", "customer.age.range.invalid");
		}
		
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Customer.class.isAssignableFrom(clazz);
	}

	
	

}
