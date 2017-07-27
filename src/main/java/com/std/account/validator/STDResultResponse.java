package com.std.account.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class STDResultResponse {
	private boolean errors;
	private Map<String,Object> values= new HashMap<>();
	public boolean isErrors() {
		return errors;
	}
	public void setErrors(boolean errors) {
		this.errors = errors;
	}
	public Map<String, Object> getValues() {
		return values;
	}
	public void setValues(Map<String, Object> values) {
		this.values = values;
	}

}
