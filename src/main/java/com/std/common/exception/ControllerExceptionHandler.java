package com.std.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	@ExceptionHandler(CustomerNotFoundException.class)
	public void handleNotFound(CustomerNotFoundException ex) {
		log.error("Resource not found",ex);
		//ex.printStackTrace();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(InvalidCustomerRequestException.class)
	public void handleBadRequest(InvalidCustomerRequestException ex) {
		log.error("Invalid Fund Request",ex);
		//throw ex;
		//ex.printStackTrace();
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
	@ExceptionHandler(Exception.class)
	public void handleGeneralError(Exception ex) {
		log.error("An error occurred procesing request", ex);
		//ex.printStackTrace();
	}
}
