package com.std.account.validator;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

public class BindingResultImpl extends BeanPropertyBindingResult implements BindingResult{

	public BindingResultImpl(Object target, String objectName) {
		super(target, objectName);
	}

}
