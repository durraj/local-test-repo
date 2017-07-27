package com.std.account.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.std.account.service.UserService;
import com.std.boot.model.UserProfile;


@Component
public class UserValidator implements Validator {
	 	@Autowired
	    private UserService userService;
	 	@Autowired
	 	private STDResultResponse errors;
	    @Override
	    public boolean supports(Class<?> aClass) {
	        return UserProfile.class.equals(aClass);
	    }

	    @Override
	    public void validate(Object o, Errors errors) {
	    	UserProfile user = (UserProfile) o;

	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
	        if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
	            errors.rejectValue("username", "Size.userForm.username");
	        }
	        if (userService.findByEmail(user.getEmail()) != null) {
	            errors.rejectValue("username", "Duplicate.userForm.username");
	        }

	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
	        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
	            errors.rejectValue("password", "Size.userForm.password");
	        }

	        if (!user.getPasswordConfirm().equals(user.getPassword())) {
	            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
	        }
	    }

		public STDResultResponse validate(UserProfile user) {
			Map<String,Object> errorsMap= new HashMap<>();
			
			if(StringUtils.isEmpty(user.getEmail()))
			{
				errorsMap.put("email", "NotEmpty");
			}
	        if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
	           // errors.rejectValue("username", "Size.userForm.username");
	            errorsMap.put("email", "Size.userForm.username");
	        }
	        if (userService.findByEmail(user.getEmail()) != null) {
	            //errors.rejectValue("username", "Duplicate.userForm.username");
	            errorsMap.put("email", "Duplicate.userForm.username");
	        }
	        if(!errorsMap.isEmpty()){
	        	errors.setErrors(true);
	        	errors.setValues(errorsMap);
	        }
			return errors;

			
		}
}
