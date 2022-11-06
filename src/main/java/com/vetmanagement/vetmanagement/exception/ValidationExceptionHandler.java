package com.vetmanagement.vetmanagement.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.TransactionalException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationExceptionHandler.class);
	
	public List<String> handleConstraintViolation(Exception ex){
		List<String> errors = new ArrayList<>();
		Throwable cause = ((TransactionalException) ex).getCause();
		if(cause instanceof ConstraintViolationException) {
			Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
			for (ConstraintViolation<?> constraintViolation : constraintViolations) {
				errors.add(constraintViolation.getMessage() + "<br/>"); 
			}
		}
		
		LOGGER.error(ex.getMessage());
		return errors;
	}
	
}
